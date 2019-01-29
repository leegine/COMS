head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済取消サービスImpl(WEB3AioSLRepayCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 仕様変更・モデルNo.757,774,775,792,793
Revision History : 2007/12/14 柴双紅 (中訊) 仕様変更・モデルNo.828
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSLRepayCancelUpdateInterceptor;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証券担保ローン返済取消サービスImpl)<BR>
 * 証券担保ローン返済取消サービス実装クラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSLRepayCancelServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLRepayCancelService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelServiceImpl.class);

    /**
     * @@roseuid 46E89084006A
     */
    public WEB3AioSLRepayCancelServiceImpl()
    {

    }

    /**
     * 証券担保ローン返済取消サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型によりvalidate注文()、またはsubmit注文()メソッドを<BR>
     * コールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE4D0B03C7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response;
        //リクエストデータの型により、以下の処理をコールする。
        //get入力画面()
        //get件数照会画面()
        if (l_request instanceof WEB3SLRepayCancelConfirmRequest)
        {
            l_response =
                validateOrder((WEB3SLRepayCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SLRepayCancelCompleteRequest)
        {
            l_response =
                submitOrder((WEB3SLRepayCancelCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)<BR>
     * 証券担保ローン返済取消の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券担保ローン返済取消）validate注文」参照。<BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）validate注文<BR>
     * 具体位置：is証券担保ローン口座開設( )<BR>
     * 　@　@戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@　@BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE4D0B03D7
     */
    protected WEB3SLRepayCancelConfirmResponse validateOrder(WEB3SLRepayCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SLRepayCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // get補助口座(補助口座タイプ : SubAccountTypeEnum)
        // 補助口座タイプ： 1（預り金口座）
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // is証券担保ローン口座開設( )
        boolean l_blnIsSeuredLoanAccountOpen = l_mainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSeuredLoanAccountOpen)
        {
            log.debug("証券担保ローン口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券担保ローン口座が未開設です。");
        }

        // CancelOrderSpec(注文ID : long)
        // 注文ID： リクエストデータ.注文ID
        CancelOrderSpec l_cancelOrderSpec =
            new CancelOrderSpec(Long.parseLong(l_request.orderId));

        // validate取消注文(SubAccount, CancelOrderSpec)
        // 補助口座： get補助口座()の戻り値
        // 取消注文内容： 取消注文内容オブジェクト
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        OrderValidationResult l_validationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in validateCancelOrder" +
                l_validationResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate取消可能(CancelOrderSpec)
        //[引数]
        //  取消注文内容： 取消注文内容オブジェクト
        this.validateCancelAccept(l_cancelOrderSpec);

        // getOrderUnits(注文ID : long)
        OrderUnit[] l_orderUnits =
            l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));

        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        // getQuantity( )
        double l_dblQuantity = l_aioOrderUnit.getQuantity();

        // getEstimatedTransferDate( )
        Date l_datEstimatedTransgerDate = l_aioOrderUnit.getEstimatedTransferDate();

        // get担保ローン振替可能額(補助口座 : 補助口座, 受渡日 : Date)
        // 補助口座： get補助口座()の戻り値
        // 受渡日： getEstimatedTransferDate()の戻り値
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblPaymentTradingPower =
        	l_tpTradingPowerService.getSLChangePossAmt(l_subAccount, l_datEstimatedTransgerDate);

        // createResponse( )
        WEB3SLRepayCancelConfirmResponse l_response =
            (WEB3SLRepayCancelConfirmResponse)l_request.createResponse();

        // プロパティセット
        // レスポンス.注文ID = リクエストデータ.注文ID
        l_response.orderId = l_request.orderId;
        // レスポンス.返済可能額 = 取引余力サービス.get担保ローン振替可能額()の戻り値
        l_response.repayableAmt = WEB3StringTypeUtility.formatNumber(l_dblPaymentTradingPower);
        // レスポンス.返済額 = 注文単位.getQuantity()の戻り値
        l_response.repayAmt = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
        // レスポンス.返済予定日 = 注文単位.getEstimatedTransferDate()の戻り値
        l_response.repayScheduledDate = l_datEstimatedTransgerDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * 証券担保ローン返済取消の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券担保ローン返済取消）submit注文」参照。<BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）submit注文<BR>
     * 具体位置：submit注文( )<BR>
     * 　@　@戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@　@BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE4D0B03E6
     */
    protected WEB3SLRepayCancelCompleteResponse submitOrder(WEB3SLRepayCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SLRepayCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // get補助口座(補助口座タイプ : SubAccountTypeEnum)
        // 補助口座タイプ： 1（預り金口座）
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        // is証券担保ローン口座開設( )
        boolean l_blnIsSecuredLoanAccountOpen = l_mainAccount.isSecuredLoanAccountOpen();
        // 戻り値 == false の場合、例外をスローする。
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            log.debug("証券担保ローン口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券担保ローン口座が未開設です。");
        }

        // CancelOrderSpec(注文ID : long)
        // 注文ID： リクエストデータ.注文ID
        CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(Long.parseLong(l_request.orderId));

        // validate取消注文(SubAccount, CancelOrderSpec)
        // 補助口座： get補助口座()の戻り値
        // 取消注文内容： 取消注文内容オブジェクト
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        OrderValidationResult l_validationResult =
            l_orderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in validateCancelOrder" +
                l_validationResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate取消可能(CancelOrderSpec)
        //[引数]
        // 取消注文内容： 取消注文内容オブジェクト
        this.validateCancelAccept(l_cancelOrderSpec);

        // 証券担保ローン返済取消更新インタセプタ( )
        WEB3AioSLRepayCancelUpdateInterceptor l_slRepayCancelUpdateInterceptor =
            new WEB3AioSLRepayCancelUpdateInterceptor();

        // getOrderUnits(注文ID : long)
        OrderUnit[] l_orderUnits =
            l_orderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        if (l_orderUnits.length <= 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];

        // setThreadLocalPersistenceEventInterceptor(証券担保ローン返済取消更新
        // インタセプタ : AioOrderManagerPersistenceEventInterceptor)
        // 証券担保ローン返済取消更新インタセプタ：　@生成した証券担保ローン返済取消更新インタセプタ
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_slRepayCancelUpdateInterceptor);

        // submitCancelOrder(補助口座 : SubAccount, 取消注文内容 : CancelOrderSpec,
        // パスワード : String, isSkip発注審査 : boolean)
        // 補助口座：　@get補助口座()の戻り値
        // 取消注文内容：　@取消注文内容オブジェクト
        // パスワード：　@リクエストデータ.暗証番号
        // isSkip発注審査：　@true
        OrderSubmissionResult l_submitCancelOrderResult = 
            l_orderManager.submitCancelOrder(l_subAccount, l_cancelOrderSpec, l_request.password, true);

        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitCancelOrder" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 余力再計算(補助口座 : 補助口座)
        // 補助口座：　@補助口座オブジェクト
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        l_service.reCalcTradingPower(l_subAccount);

        // getOrderUnit
        AioOrderUnit l_aioOrderUnitCanceled = null;
        try
        {
            l_aioOrderUnitCanceled =
                (AioOrderUnit)l_orderManager.getOrderUnit(l_aioOrderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in 注文単位オブジェクトを取得する", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // getQuantity( )
        double l_dblQuantity = l_aioOrderUnitCanceled.getQuantity();

        // getEstimatedTransferDate( )
        Date l_datEstimatedTransferDate = l_aioOrderUnitCanceled.getEstimatedTransferDate();

        // getDataSourceObject( )
        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_aioOrderUnitCanceled.getDataSourceObject();

        // getLastUpdatedTimestamp( )
        Timestamp l_tsLastUpdatedTimestamp = l_aioOrderUnitRow.getLastUpdatedTimestamp();

        // createResponse( )
        WEB3SLRepayCancelCompleteResponse l_slRepayCancelCompleteResponse =
            (WEB3SLRepayCancelCompleteResponse)l_request.createResponse();

        // プロパティセット
        // レスポンス.返済額 = 注文単位.getQuantity()の戻り値
        l_slRepayCancelCompleteResponse.repayAmt = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
        // レスポンス.返済予定日 = 注文単位.getEstimatedTransferDate()の戻り値
        l_slRepayCancelCompleteResponse.repayScheduledDate = l_datEstimatedTransferDate;
        // レスポンス.更新時間 = 注文単位Params.getLastUpdateedTimestamp()の戻り値
        l_slRepayCancelCompleteResponse.lastUpdatedTimestamp = l_tsLastUpdatedTimestamp;
        // レスポンス.注文ID = リクエストデータ.注文ID
        l_slRepayCancelCompleteResponse.orderId = l_request.orderId;

        log.exiting(STR_METHOD_NAME);
        return l_slRepayCancelCompleteResponse;
    }

    /**
     * (validate取消可能)<BR>
     * 取消可能チェックを実施する。<BR>
     * <BR>
     * １）発注日を取得する。<BR>
     * 取引時間管理.get発注日( )メソッドをコールする。<BR>
     * <BR>
     * ２）注文取消内容妥当性チェック<BR>
     * 該当注文が取消可能かをチェックする。<BR>
     * <BR>
     * ２－１）取消注文内容から取消対象の注文オブジェクトを取得する。<BR>
     * 取消注文内容.getOrderID()<BR>
     * <BR>
     * ２－２）注文オブジェクトから、注文単位オブジェクトを取得する。<BR>
     * 注文.getOrderUnits()の1番目の要素<BR>
     * <BR>
     * ２－３）注文状態のチェック<BR>
     * <BR>
     * get発注日( )の戻り値 ＞ 注文単位オブジェクト.発注日<BR>
     * の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02965<BR>
     * <BR>
     * @@param l_cancelOrderSpec - (取消注文内容)<BR>
     * 取消注文内容オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    private void validateCancelAccept(CancelOrderSpec l_cancelOrderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelAccept(CancelOrderSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_cancelOrderSpec == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //発注日を取得する。
        //取引時間管理.get発注日( )メソッドをコールする。
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //取消注文内容から取消対象の注文オブジェクトを取得する。
        //取消注文内容.getOrderID()
        long l_lngOrderId = l_cancelOrderSpec.getOrderId();

        //注文オブジェクトから、注文単位オブジェクトを取得する。
        //注文.getOrderUnits()の1番目の要素
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        OrderManager l_orderManager = l_tradingModule.getOrderManager();
        Order l_order;
        try
        {
            l_order = l_orderManager.getOrder(l_lngOrderId);
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

        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits.length > 0)
        {
            OrderUnit l_orderUnit = l_orderUnits[0];
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject();
    
            //get発注日( )の戻り値 ＞ 注文単位オブジェクト.発注日
            //の場合、例外をスローする。
            String l_strBizDate = l_aioOrderUnitRow.getBizDate();
            Date l_datOrderUnitBizDate =
                WEB3DateUtility.getDate(l_strBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            if (WEB3DateUtility.compare(l_datOrderBizDate, l_datOrderUnitBizDate) > 0)
            {
                log.debug("get発注日( )の戻り値 ＞ 注文単位オブジェクト.発注日");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02965,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文単位の発注日が現在の発注日より小さい値です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
