head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知UnitServiceImpl(WEB3IfoExecuteEndNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/23 艾興 (中訊) 新規作成
              001: 2004/08/14 王暁傑 対応 【株価指数オプション】ソースコードチェック指摘事項(JP)20040802 
              002 : 2006/07/28 張騰宇 (中訊) 仕様変更　@モデル506
              003 : 2006/10/9 唐性峰(中訊)　@仕様変更　@モデルNo.556　@
Revesion History : 2007/06/08 趙林鵬 (中訊) モデルNo.694,704
Revesion History : 2007/11/19 孟亞南 仕様変更モデルNo.802
Revesion History : 2007/12/03 孟亞南 仕様変更モデルNo.822
Revesion History : 2008/03/14 張騰宇(中訊) モデル828 858
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoExecuteEndNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

/**
 * (先物OP出来終了通知UnitServiceImpl)<BR>
 * 先物OP出来終了通知１件サービス実装クラス<BR>
 * <BR>
 * 注文単位１件ごとの出来終了通知処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクショ<BR>
 * TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 */
public class WEB3IfoExecuteEndNotifyUnitServiceImpl
    implements WEB3IfoExecuteEndNotifyUnitService
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoExecuteEndNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40C0753002BF
     */
    public WEB3IfoExecuteEndNotifyUnitServiceImpl()
    {
    }

    /**
     * (notify出来終了)<BR>
     * 注文毎の出来終了処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP出来終了通知）notify出来終了」参照。<BR>
     * @@param l_orderUnit - (注文単位オブジェクト)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strOrderExecutionEndType - (出来終了区分)<BR>
     * 出来終了区分<BR>
     * @@roseuid 408C94750364
     */
    public void notifyExecuteEnd(OrderUnit l_orderUnit, String l_strOrderExecutionEndType)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "notifyExecuteEnd(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();

        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        IfoOrderUnitRow l_unitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3GentradeSubAccount l_gentradeSubAccount = null;
        try
        {
            l_gentradeSubAccount = new WEB3GentradeSubAccount(l_unitRow.getAccountId(),l_unitRow.getSubAccountId());
        }
        catch (DataFindException l_dataFindException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataFindException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataFindException.getMessage(),
                l_dataFindException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataNetworkException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DBへのアクセスに失敗しました。", l_dataQueryException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }

        //下り処理の処理中に、外部プロセスからのデータアクセスを防止するために口座をロックする。
        try
        {
            l_accountManager.lockAccount(
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode());
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80076))
            {
                log.error("口座ロックが発生しました。口座ロック発生顧客 ==>> " +
                            "証券会社：[ "+ l_gentradeSubAccount.getInstitution().getInstitutionCode() +
                            " ] 部店：[ " + l_gentradeSubAccount.getWeb3GenBranch().getBranchCode() + " ]" +
                            " ] 顧客：[ " + l_gentradeSubAccount.getMainAccount().getAccountCode() + " ]");

            }

            throw l_wbe;
        }

        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = null;
        l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        //getOrderUnit
        OrderUnit l_reOrderUnit = null;
        try
        {
             l_reOrderUnit = l_optionOrderManagerImpl.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        WEB3IfoExecuteEndNotifyUpdateInterceptor l_ifoExecuteEndUpdateIntercptor = null;
        l_ifoExecuteEndUpdateIntercptor = new WEB3IfoExecuteEndNotifyUpdateInterceptor();
        l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(
        l_ifoExecuteEndUpdateIntercptor);
        // 2.lock口座(String, String, String)(拡張アカウントマネージャ::lock口座)
        long l_lngBranchId;
        long l_lngAccountId;
        //long l_orderUnitId;
        l_lngBranchId = l_orderUnit.getBranchId();
        l_lngAccountId = l_orderUnit.getAccountId();
        log.debug("BranchId =" + l_lngBranchId);
        log.debug("AccountId = " + l_lngAccountId);

        try
        {
            WEB3IfoExecutedMailSendService l_web3IfoExeMailSendService =
                 (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
            l_web3IfoExeMailSendService.sendMailProcess(l_reOrderUnit, null);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(STR_METHOD_NAME, l_wbe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.toString(),
                l_wbe);

        }

        //TradingSystem l_tradingSystem = l_finApp.getTradingSystem();

        WEB3GentradeSubAccount l_subAccount = null;

        long l_lngMainAccountId = l_orderUnit.getAccountId();
        try
        {
            log.debug("Get the MainAccount and the SubAccount object.");
            //取得補助口座
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().
                getSubAccount(l_lngMainAccountId, l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
       }
        //1.6is出来終了注文単位
        boolean l_blnExecuteEndIfoOrderUnit = this.isExecuteEndIfoOrderUnit(
            l_reOrderUnit, l_strOrderExecutionEndType);
        
        //getOrderId( )
        long l_lngOrderId = l_orderUnit.getOrderId();

        //1.7
        if (l_blnExecuteEndIfoOrderUnit)
        {
            //expireOrder(注文ＩＤ : long)
            log.debug("expireOrder(" + l_lngOrderId + ")");
            ProcessingResult l_reusult = l_optionOrderManagerImpl.expireOrder(l_lngOrderId);
            if (l_reusult.isFailedResult())
            {
                throw new WEB3SystemLayerException(l_reusult.getErrorInfo(), STR_METHOD_NAME);
            }
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                    (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower(l_subAccount);
            }
        }

        //is予約注文確認要
        //注文単位：　@再取得した注文単位
        boolean l_blnIsReserveOrderExist =
            l_optionOrderManagerImpl.isReserveOrderExist((IfoOrderUnit)l_reOrderUnit);

        //is予約注文確認要 = false
        if (!l_blnIsReserveOrderExist)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //getBranch(arg0 : long)
        //部店ID：　@再取得した注文単位.部店ID
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_reOrderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //is連続注文繰越実施(銘柄タイプ : ProductTypeEnum)
        boolean l_blnIsSuccOrderCarryoverEnforcemented =
            l_branch.isSuccOrderCarryoverEnforcemented(ProductTypeEnum.IFO);

        //is出来終了注文単位 = true または (出来終了区分 = "出来終了"（最終）&& is連続注文繰越実施 = false)
        WEB3ToSuccReservationIfoOrderUpdateServiceImpl l_reservationIfoOrderUpdateServiceImpl =
            new WEB3ToSuccReservationIfoOrderUpdateServiceImpl();
        if (l_blnExecuteEndIfoOrderUnit
            || (WEB3OrderexecutionEndTypeDef.DEFAULT.equals(l_strOrderExecutionEndType)
                && !l_blnIsSuccOrderCarryoverEnforcemented))
        {
            //expireAll予約注文単位(親注文の注文ID : long)
            //親注文の注文ID：　@getOrderId()の戻り値
            l_reservationIfoOrderUpdateServiceImpl.expireAllOrderUnit(l_lngOrderId);
        }
        else
        {
            //get有効予約注文単位一覧(親注文の注文ID : long)
            List l_lisOpenReserneIfoOrderUnits =
                l_reservationIfoOrderUpdateServiceImpl.getOpenReserveIfoOrderUnits(l_lngOrderId);
            int l_intSize = l_lisOpenReserneIfoOrderUnits.size();
            //get有効予約注文単位一覧()で取得したListの要素数分Loop処理
            for (int i = 0; i < l_intSize; i++)
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                    (RsvIfoOrderUnitRow)l_lisOpenReserneIfoOrderUnits.get(i);
                //is出来終了予約注文単位(先物OP予約注文単位Row, String)
                boolean l_blnIsExecuteEndReserveIfoOrderUnit =
                    this.isExecuteEndReserveIfoOrderUnit(l_rsvIfoOrderUnitRow, l_strOrderExecutionEndType);
                if (l_blnIsExecuteEndReserveIfoOrderUnit)
                {
                    //expire予約注文単位(先物OP予約注文単位行 : 先物OP予約注文単位Row)
                    l_reservationIfoOrderUpdateServiceImpl.expireOrderUnit(l_rsvIfoOrderUnitRow);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is出来終了注文単位)<BR>
     * 指定の注文単位オブジェクトが出来終了対象かどうかを判定する。<BR>
     * （当メソッドは市場閉局後にコールされる。）<BR>
     * <BR>
     * <BR>
     * １）発注（切替）遅延の注文は失効対象<BR>
     * <BR>
     * 　@OP注文マネージャ.is未発注遅延注文(引数.注文単位) == trueの場合、 <BR>
     * 　@trueを返却する。 <BR>
     * <BR>
     * <BR>
     * ２）ストップ注文失効の注文は失効対象 <BR>
     * <BR>
     * 　@引数.注文単位.リクエストタイプ == "失効"の場合、 <BR>
     * 　@trueを返却する。 <BR>
     * <BR>
     * ３） 発注日が売買最終日の注文は失効対象<BR>
     * <BR>
     * 　@　@　@３－１） 引数.注文単位から先物OP取引銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@３－２） 上記で取得した先物OP取引銘柄.getLastTradingDate()と<BR>
     * 　@　@　@　@　@　@　@　@引数.注文単位.発注日の比較結果が同日の場合（*）、trueを返却する。<BR>
     * <BR>
     * ４）通常の失効判定 <BR>
     * <BR>
     * 　@４－１）夕場前出来終了（引数.出来終了区分==”夕場前出来終了”）の場合 <BR>
     * <BR>
     * 　@　@　@４－１－１）日中登録の当日限り注文の場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@（引数.注文単位.初回注文の注文単位ID==NULL　@かつ <BR>
     * 　@　@　@　@　@　@　@　@　@引数.注文単位.夕場前繰越対象フラグ==”夕場前繰越なし”　@かつ <BR>
     * 　@　@　@　@　@　@　@　@　@引数.注文単位.立会区分==NULL） <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@trueを返却する。 <BR>
     * <BR>
     * 　@　@　@４－１－２）　@４－１－１）以外の場合、 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@falseを返却する。 <BR>
     * <BR>
     * <BR>
     * 　@４－２）　@４－１）以外の場合（出来終了（最終）の場合）<BR>
     * <BR>
     * 　@　@　@　@４－２－１）当日失効の注文の場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@（引数.注文単位.注文失効日 == TradingSystem.getBizDate()） <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@trueを返却する。 <BR>
     * <BR>
     * <BR>
     * 　@　@　@　@４－２－２）　@４－２－１）以外の場合、 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@falseを返却する。<BR>
     * <BR>
     * （*）出来終了（最終）の場合、処理時間帯が「翌日発注時間帯」となるため取得する<BR>
     * 　@先物OP取引銘柄も翌日営業日のものとなる。<BR>
     * 　@”売買最終日”については、当日・翌日の先物OP取引銘柄とも同日の日付が<BR>
     * 　@設定されているため上記の仕様とする。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strOrderExecutionEndType - (出来終了区分)<BR>
     * 出来終了区分<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    private boolean isExecuteEndIfoOrderUnit(
        OrderUnit l_orderUnit, String l_strOrderExecutionEndType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isExecuteEndIfoOrderUnit(OrderUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //発注（切替）遅延の注文は失効対象
        //OP注文マネージャ.is未発注遅延注文(引数.注文単位) == trueの場合、
        //trueを返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

        if (l_orderManager.isNotOrderedDelay((IfoOrderUnit)l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //ストップ注文失効の注文は失効対象
        //引数.注文単位.リクエストタイプ == "失効"の場合、
        //trueを返却する。
        IfoOrderUnitRow l_ifoOrderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        if (WEB3RequestTypeDef.INVALIDATE.equals(l_ifoOrderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //発注日が売買最終日の注文は失効対象
        WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();

        //引数.注文単位.発注日
        Date l_datBizDate = WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(),
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        Date l_datLastTrading = l_tradedProduct.getLastTradingDate();

        //比較結果が同日の場合true
        if (WEB3DateUtility.compareToDay(l_datBizDate,l_datLastTrading) == 0)
        {
            return true;
        }

        //夕場前出来終了（引数.出来終了区分==”夕場前出来終了”）の場合
        if (WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(
            l_strOrderExecutionEndType))
        {
            //日中登録の当日限り注文の場合、
            //（引数.注文単位.初回注文の注文単位ID==NULL　@かつ
            //引数.注文単位.夕場前繰越対象フラグ==”夕場前繰越なし”　@かつ
            //引数.注文単位.立会区分==NULL）trueを返却する。
            if (l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull()
                && BooleanEnum.FALSE.equals(l_ifoOrderUnitRow.getEveningSessionCarryoverFlag())
                && l_ifoOrderUnitRow.getSessionType() == null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            //当日失効の注文の場合、
            //（引数.注文単位.注文失効日 == TradingSystem.getBizDate()）trueを返却する。
            int l_intCompareDate =
                WEB3DateUtility.compareToDay(
                    GtlUtils.getTradingSystem().getBizDate(), l_ifoOrderUnitRow.getExpirationDate());
            if (l_intCompareDate == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is出来終了予約注文単位)<BR>
     * 指定の予約注文単位オブジェクトが出来終了対象かどうかを判定する。 <BR>
     * （当メソッドは市場閉局後にコールされる。） <BR>
     * <BR>
     * １） 発注日が売買最終日の注文は失効対象 <BR>
     * <BR>
     * 　@　@　@１－１） 引数.先物OP予約注文単位行から先物OP取引銘柄オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@　@１－２） 上記で取得した先物OP取引銘柄.getLastTradingDate()と<BR>
     * 　@　@　@　@　@　@　@　@引数.先物OP予約注文単位行.発注日の比較結果が同日の場合（*）、 <BR>
     * 　@　@　@　@　@　@　@　@trueを返却する。 <BR>
     * <BR>
     * ２） 通常の失効判定 <BR>
     * <BR>
     * 　@　@　@２－１） 夕場前出来終了（引数.出来終了区分==”夕場前出来終了”）の場合 <BR>
     * <BR>
     * 　@　@　@　@　@２－１－１） 日中登録の当日限り注文の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@（引数.先物OP予約注文単位行.初回注文の注文単位ID==NULL かつ <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数.先物OP予約注文単位行.夕場前繰越対象フラグ==<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@”夕場前繰越なし”）、 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@trueを返却する。 <BR>
     * <BR>
     * 　@　@　@　@　@２－１－２） ２－１－１）以外の場合、 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@falseを返却する。 <BR>
     * <BR>
     * 　@　@　@２－２） ２－１）以外の場合（出来終了（最終）の場合） <BR>
     * <BR>
     * 　@　@　@　@　@２－２－１） 当日失効の注文の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@（引数.先物OP予約注文単位行.注文失効日 == <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@TradingSystem.getBizDate()）、 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@trueを返却する。 <BR>
     * <BR>
     * 　@　@　@　@　@２－２－２） ２－２－１）以外の場合、 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@falseを返却する。 <BR>
     * <BR>
     * <BR>
     * （*）出来終了（最終）の場合、処理時間帯が「翌日発注時間帯」<BR>
     * 　@　@となるため取得する先物OP取引銘柄も翌日営業日のものとなる。 <BR>
     * 　@　@”売買最終日”については、<BR>
     * 　@　@当日・翌日の先物OP取引銘柄とも同日の日付が設定されているため上記の仕様とする。<BR>
     * @@param l_rsvIfoOrderUnitRow - (先物OP予約注文単位行)<BR>
     * 先物OP予約注文単位行オブジェクト<BR>
     * @@param l_strExecuteEndDiv - (出来終了区分)<BR>
     * 出来終了区分<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isExecuteEndReserveIfoOrderUnit(
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow, String l_strExecuteEndDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "isExecuteEndReserveIfoOrderUnit(RsvIfoOrderUnitRow, String)";
        log.entering(STR_METHOD_NAME);

        //１） 発注日が売買最終日の注文は失効対象
        //１－１） 引数.先物OP予約注文単位行から先物OP取引銘柄オブジェクトを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManager =
            (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        WEB3IfoTradedProductImpl l_tradedProduct = null;
        try
        {
            l_tradedProduct = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                l_rsvIfoOrderUnitRow.getProductId(), l_rsvIfoOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //１－２） 上記で取得した先物OP取引銘柄.getLastTradingDate()と
        //引数.先物OP予約注文単位行.発注日の比較結果が同日の場合（*）、trueを返却する。
        if (l_rsvIfoOrderUnitRow.getBizDate().equals(
            WEB3DateUtility.formatDate(l_tradedProduct.getLastTradingDate(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //２） 通常の失効判定
        //２－１） 夕場前出来終了（引数.出来終了区分==”夕場前出来終了”）の場合
        if (WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(l_strExecuteEndDiv))
        {
            //２－１－１） 日中登録の当日限り注文の場合
            //（引数.先物OP予約注文単位行.初回注文の注文単位ID==NULL かつ
            //引数.先物OP予約注文単位行.夕場前繰越対象フラグ==”夕場前繰越なし”）、trueを返却する
            if (l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull()
                && BooleanEnum.FALSE.equals(l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //２－２） ２－１）以外の場合（出来終了（最終）の場合）
        else
        {
            //２－２－１） 当日失効の注文の場合
            //（引数.先物OP予約注文単位行.注文失効日 == TradingSystem.getBizDate()）、trueを返却する。
            if (WEB3DateUtility.compareToDay(GtlUtils.getTradingSystem().getBizDate(),
                l_rsvIfoOrderUnitRow.getExpirationDate()) == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
