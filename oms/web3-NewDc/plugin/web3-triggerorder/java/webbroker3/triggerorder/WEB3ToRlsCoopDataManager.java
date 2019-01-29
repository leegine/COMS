head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToRlsCoopDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ルールエンジン連携データマネージャ(WEB3ToRlsCoopDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/2/18 沈迪(中訊) 新規作成
                 : 2006/8/24 唐性峰(中訊) モデルNo.158 對應
                   2006/8/24 柴双紅(中訊) モデルNo.175 No.178
                   2006/11/14 肖志偉(中訊) モデルNo.188
                   2006/11/20 黄建(中訊) モデルNo.181
*/

package webbroker3.triggerorder;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyRow;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (ルールエンジン連携データマネージャ)<BR>
 * ルールエンジン連携データマネージャ<BR>
 *
 * @@author 沈迪
 * @@version 1.0
 */
public class WEB3ToRlsCoopDataManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToRlsCoopDataManager.class);

    /**
     * @@roseuid 43F4933F03D8
     */
    public WEB3ToRlsCoopDataManager()
    {

    }

    /**
     * (validate手動発注注文)<BR>
     * 引数の注文単位が手動発注対象か判定する。<BR>
     * 判定結果を手動発注エラーコードで返却する。<BR>
     * <BR>
     * １）　@パラメータ.条件注文種別＝"逆指値"の場合、<BR>
     * 　@　@this.get逆指値手動発注エラーコード()をコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ２）　@パラメータ.条件注文種別＝"W指値"の場合、<BR>
     * 　@　@this.getW指値手動発注エラーコード()をコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_strTriggerOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43EBF7F403C5
     */
    public static String validateManualOrder(OrderUnit l_orderUnit, String l_strTriggerOrderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateManualOrder(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderType))
        {
            String l_strManualOrder = getStopManualOrderErrCode(l_orderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_strManualOrder;
        }
        else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderType))
        {
            String l_strManualOrder = getWLimitManualOrderErrorCode(l_orderUnit);

            log.exiting(STR_METHOD_NAME);
            return l_strManualOrder;
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get逆指値手動発注エラーコード)<BR>
     * パラメータ.注文単位より<BR>
     * 逆指値注文が有効か判定し、手動発注エラーコードを返却する。<BR>
     * <BR>
     * １）　@リクエストタイプの取得<BR>
     * <BR>
     * 　@１－１）　@[パラメータ．注文単位の型が株式注文単位の場合]<BR>
     * 　@　@　@　@　@　@株式注文単位ROWから、リクエストタイプを取得する。<BR>
     * <BR>
     * 　@１－２）　@[パラメータ．注文単位の型が先物OP注文単位の場合]<BR>
     * 　@　@　@　@　@　@先物OP注文単位ROWから、リクエストタイプを取得する。<BR>
     * <BR>
     * ２）　@逆指値手動発注有効注文の判定<BR>
     * <BR>
     * 　@２－１）　@パラメータ.注文単位.注文状態＝”14：発注済(取消注文)”の場合、<BR>
     * 　@　@　@　@　@　@"取消済エラー"を返却する。<BR>
     * <BR>
     * 　@２－２）　@１）で取得したリクエストタイプ＝”時価サーバ”の場合<BR>
     * 　@　@　@　@　@　@"発注済エラー"を返却する。<BR>
     * <BR>
     * 　@２－３）　@１）で取得したリクエストタイプ＝"発注失敗"の場合、<BR>
     * 　@　@　@　@　@　@"発注失敗"を返却する。<BR>
     * <BR>
     * 　@２－４）　@パラメータ.注文単位．注文有効状態＝"CLOSED(クローズ)"の場合、<BR>
     * 　@　@　@　@　@　@"その他エラー"を返却する。<BR>
     * <BR>
     * 　@２－５）　@上記以外の場合、"正常"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43EC68E302F7
     */
    public static String getStopManualOrderErrCode(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getStopManualOrderErrCode(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）リクエストタイプの取得
        String l_strRequestType = "";
        //パラメータ．注文単位の型が株式注文単位の場合
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            EqtypeOrderUnitRow l_eqOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            //株式注文単位ROWから、リクエストタイプを取得する。
            l_strRequestType = l_eqOrderUnitRow.getRequestType();
        }
        //パラメータ．注文単位の型が先物OP注文単位の場合
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            //先物OP注文単位ROWから、リクエストタイプを取得する。
            l_strRequestType = l_ifoOrderUnitRow.getRequestType();
        }
        else
        {
            log.debug("パラメータの値が株式／先物OP注文単位以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                "パラメータの値が株式／先物OP注文単位以外です。");
        }

        //２）　@逆指値手動発注有効注文の判定
        //２－１）注文単位.注文状態＝”14：発注済(取消注文)”の場合
        //　@　@　@　@"取消済エラー"を返却する。
        if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.CANCELED;
        }
        //２－２）１）で取得したリクエストタイプ＝”時価サーバ”の場合
        //　@　@　@　@"発注済エラー"を返却する。
        else if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.ORDERED;
        }
        //２－３）１）で取得したリクエストタイプ＝”発注失敗”の場合
        //　@　@　@　@"発注失敗"を返却する。
        else if (WEB3RequestTypeDef.ORDER_FAILURE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.ORDER_FAILURE;
        }
        //２－４）パラメータ.注文単位.注文有効状態＝”CLOSED(クローズ)”の場合
        //　@　@　@　@"その他エラー"を返却する。
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        //２－５）上記以外の場合、"正常"を返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.NORMAL;
        }
    }

    /**
     * (get連続注文手動発注エラーコード)<BR>
     * パラメータ.注文単位より<BR>
     * 連続注文が有効か判定し、手動発注エラーコードを返却する。<BR>
     * <BR>
     * １）　@連続注文手動発注有効注文の判定<BR>
     * <BR>
     * 　@１－１）　@注文単位.注文状態＝”14：発注済(取消注文)”の場合、<BR>
     * 　@　@　@　@　@　@"取消済エラー"を返却する。<BR>
     * <BR>
     * 　@１－２）　@注文単位.注文状態＝”3：発注済(新規注文)”の場合、<BR>
     * 　@　@　@　@　@　@"発注済エラー"を返却する。<BR>
     * <BR>
     * 　@１－３）　@注文単位.注文状態＝”6：発注失敗(新規注文)”の場合、<BR>
     * 　@　@　@　@　@　@"発注失敗"を返却する。<BR>
     * <BR>
     * 　@１－４）　@注文単位.注文有効状態＝”CLOSED(クローズ)”の場合、<BR>
     * 　@　@　@　@　@　@"その他エラー"を返却する。<BR>
     * <BR>
     * 　@１－５）　@上記以外の場合、"正常"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getToSuccManualOrderErrCode(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getToSuccManualOrderErrCode(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）　@連続注文手動発注有効注文の判定
        String l_strToSuccManualOrderErrCode = null;

        //１－１）　@注文単位.注文状態＝”14：発注済(取消注文)”の場合、
        //　@　@　@　@　@"取消済エラー"を返却する。
        if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.CANCELED;
        }
        //１－２）　@注文単位.注文状態＝”3：発注済(新規注文)”の場合
        //　@　@　@　@　@"発注済エラー"を返却する。
        else if (OrderStatusEnum.ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.ORDERED;
        }
        //１－３）　@注文単位.注文状態＝”6：発注失敗(新規注文)”の場合、
        //　@　@　@　@　@"発注失敗"を返却する。
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnit.getOrderStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.ORDER_FAILURE;
        }
        //１－４）　@注文単位.注文有効状態＝”CLOSED(クローズ)”の場合、
        //　@　@　@　@　@"その他エラー"を返却する。
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus()))
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.OTHER;
        }
        //１－５）　@上記以外の場合、"正常"を返却する。
        else
        {
            l_strToSuccManualOrderErrCode = WEB3ToManualOrderErrorCodeDef.NORMAL;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strToSuccManualOrderErrCode;
    }

    /**
     * (getW指値手動発注エラーコード)<BR>
     * パラメータ.注文単位より<BR>
     * W指値注文が有効か判定し、手動発注エラーコードを返却する。<BR>
     * <BR>
     * １）　@未発注遅延注文かどうかの判別 <BR>
     * <BR>
     * 　@１－１）　@[パラメータ.注文単位の型が株式注文単位の場合]<BR>
     * 　@　@　@　@　@　@拡張株式注文マネージャ.is未発注遅延注文()をコールする。<BR>
     * <BR>
     * 　@１－２）　@[パラメータ.注文単位の型が先物OP注文単位の場合]<BR>
     *   　@　@　@　@　@OP注文マネージャ.is未発注遅延注文()をコールする。<BR>
     * <BR>
     * ２）　@W指値手動発注有効注文の判定<BR>
     * 　@２－１）　@パラメータ.注文単位.注文状態が以下の値の場合、<BR>
     * 　@　@　@　@　@　@"取消済エラー"を返却する。<BR>
     * <BR>
     * 　@　@　@　@　@　@・"受付済（取消注文）"<BR>
     * 　@　@　@　@　@　@・"発注中（取消注文）"<BR>
     * 　@　@　@　@　@　@・"発注済（取消注文）"<BR>
     * <BR>
     * 　@２－２）　@パラメータ.注文単位.isFullyExecuted( ) == trueの場合、<BR>
     * 　@　@　@　@　@　@"約定済みエラー"を返却する。<BR>
     * <BR>
     * 　@２－３）　@パラメータ.注文単位.注文有効状態＝”CLOSED（クローズ）”かつ<BR>
     * 　@　@　@　@　@　@注文単位.失効区分＝”INVALIDATED_BY_MKT（マーケット拒否）”の場合、<BR>
     * 　@　@　@　@　@　@"失効済エラー"を返却する。<BR>
     * <BR>
     * 　@２－４）　@パラメータ.注文単位.注文有効状態＝”CLOSED(クローズ)”<BR>
     * 　@　@　@　@　@　@または、未発注遅延注文（１）の戻り値 == false）の場合<BR>
     * 　@　@　@　@　@　@"その他エラー"を返却する。<BR>
     * <BR>
     * 　@２－５）　@上記以外の場合、"正常"を返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getWLimitManualOrderErrorCode(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitManualOrderErrorCode(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        //１）　@未発注遅延注文かどうかの判別
        boolean l_blnIsNotOrderedDelay = true;
        // 　@１－１）　@[パラメータ.注文単位の型が株式注文単位の場合]
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            //拡張株式注文マネージャ.is未発注遅延注文()をコールする。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            l_blnIsNotOrderedDelay =
                l_equityOrderMgr.isNotOrderedDelayOrder((EqTypeOrderUnit)l_orderUnit);
        }
        // 　@１－２）　@[パラメータ.注文単位の型が先物OP注文単位の場合]
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            //OP注文マネージャ.is未発注遅延注文()をコールする。
            l_blnIsNotOrderedDelay = l_orderManager.isNotOrderedDelay(l_ifoOrderUnit);
        }
        else
        {
            log.debug("パラメータの値が株式／先物OP注文単位以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                "パラメータの値が株式／先物OP注文単位以外です。");
        }
        // ２）　@W指値手動発注有効注文の判定
        // 　@２－１）　@パラメータ.注文単位.注文状態が以下の値の場合、
        // 　@　@　@　@　@　@"取消済エラー"を返却する。
        // 　@　@　@　@　@　@・"受付済（取消注文）"
        //  　@　@　@　@　@ ・"発注中（取消注文）"
        // 　@　@　@　@　@　@・"発注済（取消注文）"
        if (OrderStatusEnum.CANCEL_ACCEPTED .equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.CANCELLING.equals(l_orderUnit.getOrderStatus())
            || OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.CANCELED;
        }
        // 　@２－２）　@パラメータ.注文単位.isFullyExecuted( ) == trueの場合、
        // 　@　@　@　@　@　@"約定済みエラー"を返却する。
        else if (l_orderUnit.isFullyExecuted())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.EXECUTED;
        }

        // 　@２－３）　@パラメータ.注文単位.注文有効状態＝”CLOSED（クローズ）”かつ
        // 　@　@　@　@　@　@注文単位.失効区分＝”INVALIDATED_BY_MKT（マーケット拒否）”の場合、
        // 　@　@　@　@　@　@"失効済エラー"を返却する。
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            && OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_orderUnit.getExpirationStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.EXPIREED;
        }

        // 　@２－４）　@パラメータ.注文単位.注文有効状態＝”CLOSED(クローズ)”
        // 　@　@　@　@　@　@または、未発注遅延注文（１）の戻り値 == false）の場合
        // 　@　@　@　@　@　@"その他エラー"を返却する。
        else if (OrderOpenStatusEnum.CLOSED.equals(l_orderUnit.getOrderOpenStatus())
            || !l_blnIsNotOrderedDelay)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ToManualOrderErrorCodeDef.OTHER;
        }

        // 　@２－５）　@上記以外の場合、"正常"を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3ToManualOrderErrorCodeDef.NORMAL;
    }


    /**
     * (getルールエンジンからの通知データ)<BR>
     * 引数の条件に該当するルールエンジンからの通知Paramsを返却する。 <BR>
     * <BR>
     * １）　@ルールエンジンからの通知テーブル(rls_con_order_hit_notify)を <BR>
     * 引数の条件で検索した結果を返却する。 <BR>
     * <BR>
     * [検索条件]<BR>
     * 口座ID：　@パラメータ.注文単位.口座ID<BR>
     * 補助口座ID：　@パラメータ.注文単位.補助口座ID<BR>
     * 条件付き注文タイプ：　@パラメータ.条件注文種別<BR>
     * 注文ID：　@パラメータ.注文単位.注文ID<BR>
     * 注文銘柄タイプ：　@パラメータ.銘柄タイプ<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * ※検索結果が取得できなかった場合、nullを返却する。<BR>
     * ※検索結果のレコードが複数件の場合は、データ不整合の例外をスローする。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位
     * @@param l_strTriggerOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@return RlsConOrderHitNotifyParams
     * @@throws WEB3BaseException
     */
    public static RlsConOrderHitNotifyParams getRLSConOrderHitNotifyData(
        OrderUnit l_orderUnit,
        String l_strTriggerOrderType,
        ProductTypeEnum l_productType
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRLSConOrderHitNotifyData("
            + "OrderUnit l_orderUnit,"
            + "String l_strTriggerOrderType,"
            + "ProductTypeEnum l_productType) ";
        log.entering(STR_METHOD_NAME);

        String l_strQueryString = "";
        ArrayList l_lstBind = new ArrayList();

        //[検索条件]口座ID
        l_strQueryString += " account_id = ? ";
        l_lstBind.add(new Long(l_orderUnit.getAccountId()));

        //[検索条件]補助口座ID
        l_strQueryString += " and sub_account_id = ? ";
        l_lstBind.add(new Long(l_orderUnit.getSubAccountId()));

        //[検索条件]条件付き注文タイプ
        l_strQueryString += " and order_type = ? ";
        l_lstBind.add(l_strTriggerOrderType);

        //[検索条件]注文ID
        l_strQueryString += " and order_id = ? ";
        l_lstBind.add(new Long(l_orderUnit.getOrderId()));

        //[検索条件]注文銘柄タイプ
        l_strQueryString += " and product_type = ? ";
        l_lstBind.add(l_productType);

        //１）ルールエンジンからの通知テーブル(rls_con_order_hit_notify)を
        //    引数の条件で検索した結果を返却する。
        QueryProcessor l_queryProcessor;
		try
        {
		    l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRow = l_queryProcessor.doFindAllQuery(
                RlsConOrderHitNotifyRow.TYPE,
                l_strQueryString,
                l_lstBind.toArray()
                );

            log.debug("ルールエンジンからの通知テーブル 該当件数 : " + l_lisRow.size());

            if (l_lisRow.isEmpty())
            {
                //※検索結果が取得出来なかった場合、nullを返却する。
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            else if (l_lisRow.size() > 1)
            {
                //※検索結果のレコードが複数件の場合は、データ不整合の例外をスローする。
                log.error("ルールエンジンからの通知テーブル検索結果が複数件存在");
                //例外処理
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    WEB3ToRlsCoopDataManager.class.getName()
                    + "."
                    + STR_METHOD_NAME);
            }

            //２）　@検索結果を返却する。
            RlsConOrderHitNotifyParams l_returnParams =
                new RlsConOrderHitNotifyParams((RlsConOrderHitNotifyRow)l_lisRow.get(0));

            log.exiting(STR_METHOD_NAME);
            return l_returnParams;

        }
        catch (DataFindException l_ex)
        {
            //※検索結果が取得出来なかった場合、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;

        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3ToRlsCoopDataManager.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

		}
    }

}
@
