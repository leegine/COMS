head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccReservationIfoOrderUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP予約注文更新サービスImpl(WEB3ToSuccReservationIfoOrderUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/11 趙林鵬(中訊) 新規作成 モデルNo.254, ＤＢ更新仕様NO.44,49,57,60
*/

package webbroker3.triggerorder.base.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物OP予約注文更新サービスImpl)<BR>
 * 先物OP予約注文更新サービスの実装クラス。<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3ToSuccReservationIfoOrderUpdateServiceImpl implements WEB3ToSuccReservationIfoOrderUpdateService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccReservationIfoOrderUpdateServiceImpl.class);

    /**
     * @@roseuid 4348D9800280
     */
    public WEB3ToSuccReservationIfoOrderUpdateServiceImpl()
    {

    }

    /**
     * (insert予約注文履歴)<BR>
     * 最新の先物OP予約注文単位テーブルの内容より、<BR>
     * 先物OP予約注文履歴を１レコード作成し登録する。<BR>
     * <BR>
     * １）　@先物OP予約注文単位テーブルから該当レコードを取得する。<BR>
     * <BR>
     * 　@先物OP予約注文単位テーブルより、引数の注文IDに該当するレコードを取得する。<BR>
     * <BR>
     * ２）　@取得した先物OP予約注文単位オブジェクトより、<BR>
     * 　@　@　@先物OP予約注文履歴を１レコード登録する。<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「（連続）注文登録_先物OP予約注文履歴テーブル.xls」<BR>
     * 　@を参照。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （先物OP予約注文単位.注文IDをセット）<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43378D0602A2
     */
    public void insertReserveOrderAction(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertReserveOrderAction(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１）　@先物OP予約注文単位テーブルから該当レコードを取得する。
            //先物OP予約注文単位テーブルより、引数の注文IDに該当するレコードを取得する。
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(l_lngOrderId);

            //２）　@取得した先物OP予約注文単位オブジェクトより、先物OP予約注文履歴を１レコード登録する。
            RsvIfoOrderActionParams l_rsvIfoOrderActionParams = new RsvIfoOrderActionParams();

            //口座ＩＤ: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setAccountId(l_rsvIfoOrderUnitRow.getAccountId());

            //補助口座ＩＤ: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setSubAccountId(l_rsvIfoOrderUnitRow.getSubAccountId());

            //注文ＩＤ: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setOrderId(l_rsvIfoOrderUnitRow.getOrderId());

            //注文数量: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setQuantity(l_rsvIfoOrderUnitRow.getQuantity());

            //指値: 先物OP予約注文単位テーブルの同項目
            if (!l_rsvIfoOrderUnitRow.getLimitPriceIsNull())
            {
                l_rsvIfoOrderActionParams.setLimitPrice(l_rsvIfoOrderUnitRow.getLimitPrice());
            }

            //単価調整値: 先物OP予約注文単位テーブルの同項目
            if (!l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
            {
                l_rsvIfoOrderActionParams.setPriceAdjustValue(l_rsvIfoOrderUnitRow.getPriceAdjustValue());
            }

            //注文失効日付: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setExpirationDate(l_rsvIfoOrderUnitRow.getExpirationDate());

            //注文状態: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setOrderStatus(l_rsvIfoOrderUnitRow.getOrderStatus());

            //注文有効状態: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setOrderOpenStatus(l_rsvIfoOrderUnitRow.getOrderOpenStatus());

            //失効区分: 先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setExpirationStatus(l_rsvIfoOrderUnitRow.getExpirationStatus());

            //注文履歴番号: 先物OP予約注文単位テーブル.注文履歴最終通番
            l_rsvIfoOrderActionParams.setOrderActionSerialNo(l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());

            //概算受渡代金: 先物OP予約注文単位テーブルの同項目
            if (!l_rsvIfoOrderUnitRow.getEstimatedPriceIsNull())
            {
                l_rsvIfoOrderActionParams.setEstimatedPrice(l_rsvIfoOrderUnitRow.getEstimatedPrice());
            }

            //注文経路区分: 株式予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());

            //取引者ＩＤ: 先物OP予約注文単位テーブルの同項目
            if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
            {
                l_rsvIfoOrderActionParams.setTraderId(l_rsvIfoOrderUnitRow.getTraderId());
            }

            //扱者コード（SONAR）:先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());

            //注文期限区分 :先物OP予約注文単位テーブルの同項目
            l_rsvIfoOrderActionParams.setExpirationDateType(l_rsvIfoOrderUnitRow.getExpirationDateType());

            //作成日付: 現在時刻（SystemTimestamp）
            l_rsvIfoOrderActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //更新日付: 現在時刻（SystemTimestamp）
            l_rsvIfoOrderActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderActionParams);

            log.exiting(STR_METHOD_NAME);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (invalidate予約注文単位)<BR>
     * （invalidateOrderUnit）<BR>
     * <BR>
     * 先物OP予約注文単位行を失効させる。<BR>
     * <BR>
     * １）　@引数の先物OP予約注文単位行を失効させる。（updateする）<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「連続注文発注（NG）_先物OP予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ２）　@先物OP予約注文履歴を作成する。<BR>
     * <BR>
     * 　@this.insert先物OP予約注文履歴(引数の先物OP予約注文単位行.注文ID)を<BR>
     * 　@コールする。<BR>
     * @@param l_rsvIfoOrderUnitRow - (予約注文単位行)<BR>
     * 先物OP予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。
     * @@param l_strErrorCode - (発注エラーコード)<BR>
     * 発注エラーコード。<BR>
     * （エラー原因の特定が可能なErrorInfo.error_codeをセット、<BR>
     * 発注エラー以外で失効する場合、nullをセット）
     * @@throws WEB3BaseException
     * @@roseuid 4337904800BE
     */
    public void invalidateOrderUnit(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow,
        String l_strErrorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "invalidateOrderUnit(RsvIfoOrderUnitRow, String)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnitRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@引数の先物OP予約注文単位行を失効させる。（updateする）
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        //DB更新仕様「連続注文発注（NG）_先物OP予約注文単位テーブル.xls」を参照。
        //注文履歴最終通番: （既存値）＋１
        int l_intLastOrderActionSerialNo = l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo() + 1;
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(l_intLastOrderActionSerialNo);

        //注文状態:
        //引数の発注エラーコードが指定されている場合：
        //6:発注失敗（新規注文）
        // （OrderStatusEnumにて定義）
        //引数の発注エラーコードが指定されていない場合：（既存値）
        if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode))
        {
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
        }

        //注文有効状態:
        //2:クローズ（OrderOpenStatusEnumにて定義）
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        //失効区分:
        //3:マーケット拒否（OrderExpirationStatusEnumにて定義）
        l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);

        //発注エラーコード:
        //引数の発注エラーコードが指定されている場合：
        //発注エラーコードをセット
        //（* エラー原因の特定が可能なErrorInfo.error_codeをセット）
        // 引数の発注エラーコードが指定されていない場合：（既存値）
        if (!WEB3StringTypeUtility.isEmpty(l_strErrorCode))
        {
            l_rsvIfoOrderUnitParams.setOrderErrorCode(l_strErrorCode);
        }

        //更新日付: 現在日時
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);

            //２）　@先物OP予約注文履歴を作成する。
            //this.insert先物OP予約注文履歴(引数の先物OP予約注文単位行.注文ID)をコールする。
            this.insertReserveOrderAction(l_rsvIfoOrderUnitParams.getOrderId());

            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (invalidateAll予約注文単位)<BR>
     * （invalidateAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * ※失効処理が行われなかった（有効な予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * １）　@有効な先物OP予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * 　@２－１）　@this.invalidate予約注文単位(処理対象の要素, null)をコールする。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4344AA59016F
     */
    public boolean invalidateAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "invalidateAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@有効な先物OP予約注文単位レコードを取得する。
        //this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。
        List l_lisOrderUnitRows = this.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //該当データなしの場合、falseを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）　@１）の戻り値の要素数分、以下の処理を行う。
        //２－１）　@this.invalidate予約注文単位(処理対象の要素, null)をコールする。
        int l_intCnt = l_lisOrderUnitRows.size();

        for (int i = 0; i < l_intCnt; i++)
        {
            this.invalidateOrderUnit((RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i), null);
        }

        //３）　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (cancel予約注文単位)<BR>
     * （cancelOrderUnit）<BR>
     * 引数の先物OP予約注文単位行を取消する。<BR>
     * <BR>
     * １）　@引数の先物OP予約注文単位行を取消する。（updateする）<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「（連続）注文取消_先物OP予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ２）　@先物OP予約注文履歴を作成する。<BR>
     * <BR>
     * 　@this.insert予約注文履歴(引数の先物OP予約注文単位行.注文ID)をコールする。<BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A1D6802AF
     */
    public void cancelOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelOrderUnit(RsvIfoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnitRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@引数の先物OP予約注文単位行を取消する。（updateする）
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        try
        {
            //DB更新仕様「（連続）注文取消_先物OP予約注文単位テーブル.xls」を参照。
            //取引者ＩＤ(trader_id) :
            //ログインセキュリティサービスが取得可能な場合：
            //セッションから取得したログインIDに該当する扱者.取引者ID
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            long l_loginId = l_opLoginSecurityService.getLoginInfo().getLoginId();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

            try
            {
                Trader l_trader = l_finObjMgr.getTraderByLoginId(l_loginId);
                l_rsvIfoOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            catch (NotFoundException l_ex)
            {
                //※取得できなかった場合は、nullをセット
                l_rsvIfoOrderUnitParams.setTraderId(null);
            }

            //注文経路区分(order_root_div) :
            //ログインセキュリティサービスが取得可能な場合：セッションから取得した注文経路区分
            l_rsvIfoOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
        }
        catch (IllegalSessionStateException l_ex)
        {
            //取引者ＩＤ(trader_id) :
            //ログインセキュリティサービスが取得不可な場合：（既存値）
            //注文経路区分(order_root_div) :
            //ログインセキュリティサービスが取得不可な場合：（既存値）
        }

        //注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo() + 1);

        //注文状態(order_status) :14:発注済（取消注文）
        l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);

        //注文有効状態(order_open_status) :2:クローズ
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        //更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);

            //２）　@先物OP予約注文履歴を作成する。
            //　@this.insert予約注文履歴(引数の先物OP予約注文単位行.注文ID)をコールする。
            this.insertReserveOrderAction(l_rsvIfoOrderUnitRow.getOrderId());

            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (cancelAll予約注文単位)<BR>
     * （cancelAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て取消する。<BR>
     * ※取消処理が行われなかった（有効な予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * １）　@有効な先物OP予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * 　@２－１）　@this.cancel予約注文単位(処理対象の要素)をコールする。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A18D20109
     */
    public boolean cancelAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@有効な先物OP予約注文単位レコードを取得する。
        //this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。
        List l_lisOrderUnitRows = this.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //該当データなしの場合、falseを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）　@１）の戻り値の要素数分、以下の処理を行う。
        //２－１）　@this.cancel予約注文単位(処理対象の要素)をコールする。
        int l_intCnt = l_lisOrderUnitRows.size();

        for (int i = 0; i < l_intCnt; i++)
        {
            this.cancelOrderUnit((RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i));
        }

        //３）　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (expire予約注文単位)<BR>
     * （expireOrderUnit）<BR>
     * 引数の先物OP予約注文単位行を失効させる。<BR>
     * <BR>
     * １）　@引数の先物OP予約注文単位行を失効させる。（updateする）<BR>
     * <BR>
     * 　@　@DB更新仕様<BR>
     * 　@　@「先OP出来終了_先物OP予約注文単位テーブル.xls」<BR>
     * 　@　@を参照。<BR>
     * <BR>
     * ２）　@先物OP予約注文履歴を作成する。<BR>
     * <BR>
     * 　@　@　@this.insert予約注文履歴(引数の先物OP予約注文単位行.注文ID)をコールする。<BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成される。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A20720399
     */
    public void expireOrderUnit(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireOrderUnit(RsvIfoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnitRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        try
        {
            //１）　@引数の先物OP予約注文単位行を失効させる。（updateする）
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

            //DB更新仕様「先OP出来終了_先物OP予約注文単位テーブル.xls」を参照。
            //注文履歴最終通番(last_order_action_serial_no) :（既存値）＋１
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo() + 1);

            //注文有効状態(order_open_status) : 2:クローズ（OrderOpenStatusEnumにて定義）
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

            //失効区分(expiration_status) : 2:終了（OrderExpirationStatusEnumにて定義）
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);

            //更新日付(last_updated_timestamp) : 現在日時（GtlUtils.getSystemTimestamp()）
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);

            //２）　@先物OP予約注文履歴を作成する。
            //this.insert予約注文履歴(引数の先物OP予約注文単位行.注文ID)をコールする。
            this.insertReserveOrderAction(l_rsvIfoOrderUnitRow.getOrderId());

            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (expireAll予約注文単位)<BR>
     * （expireAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文単位を<BR>
     * 全て失効させる。<BR>
     * ※失効処理が行われなかった（有効な予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * <BR>
     * １）　@有効な先物OP予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * 　@２－１）　@this.expire予約注文単位(処理対象の要素)をコールする。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 433A2072039B
     */
    public boolean expireAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@有効な先物OP予約注文単位レコードを取得する。
        //this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。
        List l_lisOrderUnitRows = this.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //該当データなしの場合、falseを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）　@１）の戻り値の要素数分、以下の処理を行う。
        //２－１）　@this.expire予約注文単位(処理対象の要素)をコールする。
        int l_intCnt = l_lisOrderUnitRows.size();

        for (int i = 0; i < l_intCnt; i++)
        {
            this.expireOrderUnit((RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i));
        }

        //３）　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get有効予約注文単位一覧)<BR>
     * （getOpenReserveIfoOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、有効な先物OP予約注文単位行の配列を返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@-------------------------------<BR>
     * 　@　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@　@親注文の注文ID = 引数.親注文の注文ID<BR>
     * 　@　@かつ　@注文有効状態 = "オープン"<BR>
     * <BR>
     * 　@　@※「親注文内連番」で昇順ソート指定する。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * 　@　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 433A1C640157
     */
    public List getOpenReserveIfoOrderUnits(long l_lngParentOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOpenReserveIfoOrderUnits(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１）　@DB検索
            //以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。
            //＜検索条件＞
            //親注文の注文ID = 引数.親注文の注文ID かつ　@注文有効状態 = "オープン"
            //※「親注文内連番」で昇順ソート指定する。
            String l_strWhere = " parent_order_id = ? and order_open_status = ? ";
            Object[] l_objs = {new Long(l_lngParentOrderId), OrderOpenStatusEnum.OPEN};

            String l_strSort = "serial_no_in_parent asc";
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_objs);

            if (l_lisRsvIfoOrderUnitRows != null && !l_lisRsvIfoOrderUnitRows.isEmpty())
            {
                //２）　@検索結果を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_lisRsvIfoOrderUnitRows;
            }

            //検索結果が取得できなかった場合、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (deleteAll予約注文単位)<BR>
     * （deleteAllOrderUnit）<BR>
     * 引数の（親注文）注文IDに該当する予約注文データを<BR>
     * 全て削除する。<BR>
     * ※削除処理が行われなかった（予約注文なし）場合、<BR>
     * 　@falseを返却する。以外、trueを返却する。<BR>
     * ※データベースdeleteは、全てクエリプロセッサを使用し、<BR>
     * 　@SQL文を発行することで行う。<BR>
     * <BR>
     * １）　@先物OP予約注文単位レコードを取得する。<BR>
     * 　@　@　@this.get予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。<BR>
     * <BR>
     * 　@２－１）　@該当予約注文が既存建に対する返済注文の場合<BR>
     * 　@　@　@　@　@　@（処理対象の要素.連続注文取引区分=="先物返済（既存残）"<BR>
     * 　@　@　@　@　@　@　@または、処理対象の要素.連続注文取引区分=="OP返済（既存残）"）、<BR>
     * <BR>
     * 　@　@　@　@　@　@予約建玉返済指定情報データを<BR>
     * 　@　@　@　@　@　@【先物OP予約建玉返済指定情報テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@　@削除キーには、処理対象の要素.注文ID を指定する。<BR>
     * <BR>
     * 　@２－２）　@予約注文履歴データを<BR>
     * 　@　@　@　@　@　@【先物OP予約注文履歴テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@　@削除キーには、処理対象の要素.注文ID を指定する。<BR>
     * <BR>
     * 　@２－３）　@予約注文単位のデータを<BR>
     * 　@　@　@　@　@　@【先物OP予約注文単位テーブル】よりdeleteする。<BR>
     * 　@　@　@　@　@　@削除キーには、処理対象の要素.注文ID を指定する。<BR>
     * <BR>
     * ３）　@trueを返却する。<BR>
     * @@param l_lngParentOrderId - （親注文の注文ID）<BR>
     * 親注文の注文ID。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4355E2000186
     */
    public boolean deleteAllOrderUnit(long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteAllOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        //１）　@先物OP予約注文単位レコードを取得する。
        //this.get予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。
        List l_lisOrderUnitRows = this.getReserveIfoOrderUnits(l_lngParentOrderId);

        //該当データなしの場合、falseを返却する。
        if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        try
        {
            //２）　@１）の戻り値の要素数分、以下の処理を行う。
            int l_intCnt = l_lisOrderUnitRows.size();

            for (int i = 0; i < l_intCnt; i++)
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_lisOrderUnitRows.get(i);

                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                String l_strWhere = " order_id = ? ";
                Object[] l_objs = {new Long(l_rsvIfoOrderUnitRow.getOrderId())};

                //２－１）　@該当予約注文が既存建に対する返済注文の場合
                //（処理対象の要素.連続注文取引区分=="先物返済（既存残）"
                //または、処理対象の要素.連続注文取引区分=="OP返済（既存残）"）、
                if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(
                        l_rsvIfoOrderUnitRow.getReserveOrderTradingType())
                    || WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
                        l_rsvIfoOrderUnitRow.getReserveOrderTradingType()))
                {
                    //予約建玉返済指定情報データを
                    //【先物OP予約建玉返済指定情報テーブル】よりdeleteする。
                    //削除キーには、処理対象の要素.注文ID を指定する。
                    l_queryProcessor.doDeleteAllQuery(
                        RsvIfoClosingContractSpecRow.TYPE,
                        l_strWhere,
                        l_objs);
                }

                //２－２）　@予約注文履歴データを
                //【先物OP予約注文履歴テーブル】よりdeleteする。
                //削除キーには、処理対象の要素.注文ID を指定する。
                l_queryProcessor.doDeleteAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    l_strWhere,
                    l_objs);

                //２－３）　@予約注文単位のデータを
                //【先物OP予約注文単位テーブル】よりdeleteする。
                //削除キーには、処理対象の要素.注文ID を指定する。
                l_queryProcessor.doDeleteAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objs);
            }

            //３）　@trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get予約注文単位一覧)<BR>
     * （getReserveIfoOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、先物OP予約注文単位行の配列を返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@-------------------------------<BR>
     * 　@　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@　@親注文の注文ID = 引数.親注文の注文ID<BR>
     * <BR>
     * 　@　@※「親注文内連番」で昇順ソート指定する。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * 　@　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getReserveIfoOrderUnits(long l_lngParentOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getReserveIfoOrderUnits(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１）　@DB検索
            //以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。
            //＜検索条件＞
            //親注文の注文ID = 引数.親注文の注文ID
            //※「親注文内連番」で昇順ソート指定する。
            String l_strWhere = " parent_order_id = ?";
            Object[] l_objs = {new Long(l_lngParentOrderId)};
            String l_strSort = "serial_no_in_parent asc";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_objs);

            if (l_lisRsvIfoOrderUnitRows != null && !l_lisRsvIfoOrderUnitRows.isEmpty())
            {
                //２）　@検索結果を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_lisRsvIfoOrderUnitRows;
            }

            //検索結果が取得できなかった場合、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (update予約注文データ)<BR>
     * 指定された注文オブジェクトを使用し、<BR>
     * QueryProcessorにより予約注文データ類の更新を行う。<BR>
     * <BR>
     * １）　@引数の先物OP予約注文単位Rowオブジェクトの内容で<BR>
     * 　@先物OP予約注文単位テーブルをupdateする。<BR>
     * <BR>
     * ２）　@引数の先物OP予約注文履歴Rowが"null"でない場合のみ、<BR>
     * 　@引数の先物OP予約注文履歴Rowオブジェクトの内容で<BR>
     * 　@先物OP予約注文履歴テーブルにinsertする。<BR>
     * <BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行<BR>
     * @@param l_rsvIfoOrderActionRow - (先物OP予約注文履歴行)<BR>
     * 先物OP予約注文履歴行<BR>
     * @@throws WEB3BaseException
     */
    public void updateReserveOrderData(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow,
        RsvIfoOrderActionRow l_rsvIfoOrderActionRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateReserveOrderData(RsvIfoOrderUnitRow, RsvIfoOrderActionRow)";
        log.entering(STR_METHOD_NAME);
        try
        {
            // データを更新する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // １）　@引数の先物OP予約注文単位Rowオブジェクトの内容で
            //先物OP予約注文単位テーブルをupdateする。
            String l_strWhere = "order_id = ? ";
            Object[] l_objUpdWhere = {new Long(l_rsvIfoOrderUnitRow.getOrderId())};

            l_queryProcessor.doUpdateQuery(
                l_rsvIfoOrderUnitRow,
                l_strWhere,
                l_objUpdWhere);

            // ２）　@引数の先物OP予約注文履歴Rowが"null"でない場合のみ、
            //引数の先物OP予約注文履歴Rowオブジェクトの内容で
            //先物OP予約注文履歴テーブルにinsertする。
            if (l_rsvIfoOrderActionRow != null)
            {
                l_queryProcessor.doInsertQuery(l_rsvIfoOrderActionRow);
            }
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@
