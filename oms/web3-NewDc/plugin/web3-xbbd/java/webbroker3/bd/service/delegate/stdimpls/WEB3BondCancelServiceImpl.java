head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取消サービスImpl(WEB3BondCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/22 趙林鵬 (中訊) 新規作成
                 : 2006/09/29 趙林鵬 (中訊) モデル 094 ＤＢ更新仕様No.013
Revesion History : 2007/07/25 謝旋 (中訊) 仕様変更・モデル222
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.bd.WEB3BondCancelUpdateInterceptor;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelCompleteResponse;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券取消サービスImpl)<BR>
 * 債券取消サービスImpl
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondCancelServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondCancelService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondCancelServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A0128
     */
    public WEB3BondCancelServiceImpl()
    {

    }

    /**
     * 債券取消サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * validate売却注文、submit売却付注文<BR>
     * のいずれかのメソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E93E610353
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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

        WEB3GenResponse l_response = null;

        //validate売却注文
        if (l_request instanceof WEB3BondCancelConfirmRequest)
        {
            l_response = this.validateCancelOrder(
                (WEB3BondCancelConfirmRequest) l_request);
        }
        //submit売却注文
        else if (l_request instanceof WEB3BondCancelCompleteRequest)
        {
            l_response = this.submitCancelOrder(
                (WEB3BondCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate取消注文)<BR>
     * 債券取消注文発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate取消注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E9470401FC
     */
    protected WEB3BondCancelConfirmResponse validateCancelOrder(
        WEB3BondCancelConfirmRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancelOrder(WEB3BondCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2get債券注文単位By注文ID(long)]
        //[get債券注文単位By注文ID()に渡す引数]
        //　@注文ID：　@リクエストデータ.注文ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = (
            WEB3BondOrderUnit)l_orderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));

        //1.3get債券銘柄(long)
        //[get債券銘柄()に渡す引数]
        //銘柄ID：　@債券注文単位.銘柄ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = (
            WEB3BondProduct) l_bondProductManager.getBondProduct(
                l_bondOrderUnit.getProductId());

        //1.4validate注文受付可能(String)
        //受付時間チェック、緊急停止チェック、バッチ処理中チェックを行なう。
        //[validate注文受付可能()に渡す引数]
        //債券銘柄：　@取得した債券銘柄
        WEB3BondTradingTimeManagement.validateOrderAccept(
            l_bondProduct);

        //1.5get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate取引可能顧客(SubAccount)
        //[validate取引可能顧客()に渡す引数]
        //補助口座：　@get補助口座()の戻り値
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //1.7get取引区分( )
        String l_strDealDiv = l_bondOrderUnit.getDealDiv();

        //債券銘柄.債券タイプ == "外国債券"の場合
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            //validate顧客取扱可能銘柄(債券銘柄, String)
            //[validate顧客取扱可能銘柄()に渡す引数]
            //　@債券銘柄：　@get債券銘柄()の戻り値
            //　@取引区分：　@get取引区分()の戻り値
            l_orderManager.validateAccountHandlingPossibleProduct(
                l_bondProduct,
                l_strDealDiv);
        }
        else
        {
            //債券銘柄.債券タイプ ≠ "外国債券"の場合
            //validate顧客取扱可能銘柄<国内債券>(債券銘柄, String)
            //[validate顧客取扱可能銘柄<国内債券>()に渡す引数]
            //　@債券銘柄：　@get債券銘柄()の戻り値
            //　@取引区分：　@get取引区分()の戻り値
            l_orderManager.validateAccountHandlingPossibleProductBondDomestic(
                l_bondProduct,
                l_strDealDiv);
        }

        //1.9validate注文取消可能状態(拡張債券注文単位)
        //[validate注文取消可能状態()に渡す引数]
        //　@債券注文単位：　@取得した債券注文単位
        l_orderManager.validateOrderCancelPossibleStatus(l_bondOrderUnit);

        //1.10createResponse( )
        WEB3BondCancelConfirmResponse l_response =
            (WEB3BondCancelConfirmResponse)l_request.createResponse();

        //1.11注文単位紹介区分(long, ProductTypeEnum)
        //[コンストラクタに渡す引数]
        //注文単位ID：　@債券注文単位.注文単位ID
        //銘柄タイプ：　@債券注文単位.銘柄タイプ
        boolean l_blnIntroduceDiv = true;
        WEB3GentradeOrderUnitIntroduceDiv l_orderUnitIntroduceDiv = null;
        try
        {
            l_orderUnitIntroduceDiv =
                new WEB3GentradeOrderUnitIntroduceDiv(
                    l_bondOrderUnit.getOrderUnitId(),
                    l_bondOrderUnit.getProductType());
        }
        catch(WEB3BaseException l_ex)
        {
            l_blnIntroduceDiv = false;
        }

        BondOrderUnitRow l_bondOrderUnitRow =
            (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();

        //1.12プロパティ・セット
        //以下の通り、プロパティをセットする。
        //銘柄名         = 債券銘柄.銘柄名
        l_response.productName = l_bondProduct.getProductName();

        //通貨コード           = 債券銘柄.通貨コード
        l_response.currencyCode = l_bondProduct.getCurrencyCode();

        //売買単価            = 債券注文単位.指値
        if(!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_response.buySellPrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getLimitPrice());
        }

        //利率          = 債券銘柄.利率
        l_response.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //発行日         = 債券銘柄.発行日
        l_response.issueDate = l_bondProduct.getIssueDate();

        //年間利払回数      = 債券銘柄.年間利払回数
        l_response.yearlyInterestPayments =
            l_bondProduct.getYearlyInterestPayments() + "";

        //利払日１            = 債券銘柄.利払日１
        l_response.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();

        //利払日２            = 債券銘柄.利払日２
        l_response.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();

        //償還日         = 債券銘柄.償還日
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //取引区分            = get取引区分（）の戻り値
        l_response.stateDiv = l_strDealDiv;

        //決済区分            = 債券注文単位.決済区分
        l_response.settleDiv = l_bondOrderUnit.getSettlementDiv();

        //為替レート           = 債券注文単位.基準為替レート
        if(!l_bondOrderUnitRow.getBaseFxRateIsNull())
        {
            l_response.fxRate =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getBaseFxRate());
        }

        //額面金額            = 債券注文単位.注文数量
        l_response.faceAmount= WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getQuantity());

        //売買代金（外貨）        = 債券注文単位.売買代金（外貨）
        if(!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_response.foreignTradePrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignTradingPrice());
        }

        //売買代金（円貨）        = 債券注文単位.売買代金（円貨）
        if(!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_response.yenTradePrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getTradingPrice());
        }

        //経過利子（外貨）        = 債券注文単位.経過利子（外貨）
        if(!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_response.foreignAccruedInterest =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignAccruedInterest());
        }

        //経過利子（円貨）        = 債券注文単位.経過利子（円貨）
        if(!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_response.yenAccruedInterest =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getAccruedInterest());
        }

        //受渡代金（外貨）        = 債券注文単位.受渡代金（外貨）
        if(!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_response.foreignDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignEstimatedPrice());
        }

        //受渡代金（円貨）        = 債券注文単位.受渡代金（円貨）
        if(!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_response.yenDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getEstimatedPrice());
        }

        //注文日時            = 債券注文単位.受注日時
        l_response.orderDate = l_bondOrderUnit.getReceivedDateTime();

        //発注日         = 債券注文単位.発注日
        l_response.orderBizDate =
            WEB3DateUtility.getDate(l_bondOrderUnit.getBizDate(), "yyyyMMdd");

        //約定日         = 債券注文単位.約定日
        l_response.executionUpdateDate = l_bondOrderUnit.getExecDate();

        //受渡日         = 債券注文単位.受渡日
        l_response.deliveryDate = l_bondOrderUnit.getDeliveryDate();

        if (l_blnIntroduceDiv)
        {
            //紹介区分            = 注文単位紹介区分.get紹介区分()(*1)
            l_response.introduceStoreDiv = l_orderUnitIntroduceDiv.getIntroduceBranchDiv();

            //紹介店コード      = 注文単位紹介区分.get紹介店コード()(*1)
            l_response.introduceStoreCode = l_orderUnitIntroduceDiv.getIntroduceBranchCode();
        }
        else
        {
            //(*1)注文単位紹介区分が取得できなかった場合、nullをセットする。
            l_response.introduceStoreDiv = null;
            l_response.introduceStoreCode = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (submit取消注文)<BR>
     * 債券取消注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit取消注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3BondCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E947700315
     */
    protected WEB3BondCancelCompleteResponse submitCancelOrder(WEB3BondCancelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancelOrder(WEB3BondCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2get債券注文単位By注文ID(long)]
        //[get債券注文単位By注文ID()に渡す引数]
        //　@注文ID：　@リクエストデータ.注文ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_orderManager =
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondOrderUnit l_bondOrderUnit = (
            WEB3BondOrderUnit)l_orderManager.getBondOrderUnitByOrderId(
                Long.parseLong(l_request.orderId));

        //1.3get債券銘柄(long)
        //[get債券銘柄()に渡す引数]
        //銘柄ID：　@債券注文単位.銘柄ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = (
            WEB3BondProduct) l_bondProductManager.getBondProduct(
                l_bondOrderUnit.getProductId());

        //1.4validate注文受付可能(String)
        //受付時間チェック、緊急停止チェック、バッチ処理中チェックを行なう。
        //[validate注文受付可能()に渡す引数]
        //債券銘柄：　@取得した債券銘柄
        WEB3BondTradingTimeManagement.validateOrderAccept(
            l_bondProduct);

        //1.5get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate取引可能顧客(SubAccount)
        //[validate取引可能顧客()に渡す引数]
        //補助口座：　@get補助口座()の戻り値
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //1.7get取引区分( )
        String l_strDealDiv = l_bondOrderUnit.getDealDiv();

        //債券銘柄.債券タイプ == "外国債券"の場合
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondProduct.getBondType()))
        {
            //validate顧客取扱可能銘柄(債券銘柄, String)
            //[validate顧客取扱可能銘柄()に渡す引数]
            //　@債券銘柄：　@get債券銘柄()の戻り値
            //　@取引区分：　@get取引区分()の戻り値
            l_orderManager.validateAccountHandlingPossibleProduct(
                l_bondProduct,
                l_strDealDiv);
        }
        else
        {
            //債券銘柄.債券タイプ ≠ "外国債券"の場合
            //validate顧客取扱可能銘柄<国内債券>(債券銘柄, String)
            //[validate顧客取扱可能銘柄<国内債券>()に渡す引数]
            //　@債券銘柄：　@get債券銘柄()の戻り値
            //　@取引区分：　@get取引区分()の戻り値
            l_orderManager.validateAccountHandlingPossibleProductBondDomestic(
                l_bondProduct,
                l_strDealDiv);
        }

        //1.9validate注文取消可能状態(拡張債券注文単位)
        //[validate注文取消可能状態()に渡す引数]
        //　@債券注文単位：　@取得した債券注文単位
        l_orderManager.validateOrderCancelPossibleStatus(l_bondOrderUnit);

        //1.10CancelOrderSpec(arg0 : long)
        //注文ID： 注文単位.注文ID
        CancelOrderSpec l_cancelOrderSpec =
            new CancelOrderSpec(Long.parseLong(l_request.orderId));

        //1.11get代理入力者( )
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader)this.getTrader();

        //1.12債券取消更新インタセプタ( )
        WEB3BondCancelUpdateInterceptor l_bondCancelUpdateInterceptor =
            new WEB3BondCancelUpdateInterceptor();

        //1.13プロパティ・セット
        //代理入力者：　@get代理入力者（）の戻り値
        //※取得できなかった場合nullをセットする）
        l_bondCancelUpdateInterceptor.setTrader(l_trader);

        //1.14setThreadLocalPersistenceEventInterceptor(
        //arg0 : BondOrderManagerPersistenceEventInterceptor)
        //arg0： 債券取消更新インタセプタ
        l_orderManager.setThreadLocalPersistenceEventInterceptor(
            l_bondCancelUpdateInterceptor);


        //1.15submitCancelOrder(arg0 : SubAccount, arg1 : CancelOrderSpec, arg2 : String, arg3 : boolean)
        //補助口座： get補助口座()の戻り値
        //注文内容： CancelOrderSpec
        //パスワード： リクエストデータ.暗証番号
        //isSkip発注審査： true
        OrderSubmissionResult l_submitNewOrderResult = 
            l_orderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
        
        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.16余力再計算(補助口座 : 補助口座)
        //補助口座： get補助口座()の戻り値
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);

        //1.17createResponse( )
        WEB3BondCancelCompleteResponse l_response =
            (WEB3BondCancelCompleteResponse)l_request.createResponse();

        //1.18プロパティ・セット
        //以下の通り、プロパティをセットする。
        //更新日時　@＝　@現在日時
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }


}
@
