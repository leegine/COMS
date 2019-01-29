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
filename	WEB3BondRecruitBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付入力サービスImpl(WEB3BondRecruitBuyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 唐性峰 (中訊) 新規作成
                 : 2006/10/09 張騰宇 (中訊) モデルNo.094,098
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondApplyBuyInputRequest;
import webbroker3.bd.message.WEB3BondApplyBuyInputResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AutoExecDivDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券応募/買付入力サービスImpl)<BR>
 * 債券応募/買付入力サービスImpl<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondRecruitBuyInputServiceImpl extends WEB3BondClientRequestService implements WEB3BondRecruitBuyInputService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyInputServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A006D
     */
    public WEB3BondRecruitBuyInputServiceImpl()
    {

    }

    /**
     * 債券応募/買付入力サービス処理を実施する。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C453040084
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (! (l_request instanceof WEB3BondApplyBuyInputRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3BondApplyBuyInputRequest l_inputRequest = (WEB3BondApplyBuyInputRequest)l_request;

        //1.1 validate( )
        //リクエストデータの整合性をチェックする。
        l_inputRequest.validate();

        //1.2 get債券銘柄(long)
        //債券銘柄を取得する。
        //[get債券銘柄（）に渡す引数]
        //　@銘柄ID：　@リクエストデータ.銘柄ID
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager) l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_inputRequest.productId));

        //1.3 validate注文受付可能(String)
        //受付時間チェック、緊急停止チェック、バッチ処理中チェックを行なう。
        //[validate注文受付可能()に渡す引数]
        //債券銘柄：　@取得した債券銘柄
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.4 get補助口座( )
        //補助口座オブジェクトを取得する。
        SubAccount l_subAccount = this.getSubAccount();

        //1.5 validate取引可能顧客(補助口座 : SubAccount)
        //顧客別取引停止属性チェックをする
        //［validate取引可能顧客()に渡す引数]
        //　@　@　@補助口座：　@get補助口座( )の戻り値
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引可能顧客チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "取引可能顧客チェックエラー");
        }

        //1.6 validate外国証券口座開設(SubAccount, 債券銘柄)
        //顧客が外国証券口座を開設しているかチェックする。
        //[validate外国証券口座開設(）に渡す引数]
        //　@補助口座：　@get補助口座()の戻り値
        //　@債券銘柄：　@get債券銘柄()の戻り値
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        l_bondOrderManager.validateFeqAccountOpen(l_subAccount, l_bondProduct);

        //1.7 validate顧客取扱可能銘柄(債券銘柄, String)
        //顧客取扱可能チェック、取引可能チェックを行なう。
        //[validate顧客取扱可能銘柄()に渡す引数]
        //　@債券銘柄：　@get債券銘柄()の戻り値
        //　@取引区分：　@リクエストデータ.取引区分
        l_bondOrderManager.validateAccountHandlingPossibleProduct(
            l_bondProduct,
            l_inputRequest.tradeDiv);

        //1.8 get自動約定区分( )
        //債券銘柄の自動約定区分を取得する。
        String l_strAutoExecDiv = l_bondProduct.getAutoExecDiv();

        //1.9 ＜分岐処理＞get自動約定区分()の戻り値 == ”自動約定”の場合
        if (WEB3AutoExecDivDef.AUTO_EXECUTE.equals(l_strAutoExecDiv))
        {
            //1.9.1 validate自動約定枠(証券会社, 債券銘柄, String)
            //注文数量が自動約定枠の範囲内であるかチェックする。
            //[validate自動約定枠()に渡す引数]
            //　@証券会社：　@補助口座.getInstitution()の戻り値
            //　@債券銘柄：　@取得した債券銘柄
            //　@注文数量：　@0
            l_bondOrderManager.validateAutoExecLimit(
                l_subAccount.getInstitution(),
                l_bondProduct,
                "0");
        }

        //1.10  is外貨建( )
        //債券銘柄が外貨建てかどうかチェックする。
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.11 ＜分岐処理＞is外貨建()の戻り値 == true の場合
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.11.1 get通貨コード( )
            //債券銘柄の通貨コードを取得する。
            String l_strCurrencyCode = l_bondProduct.getCurrencyCode();

            //1.11.2 （共通）通貨(証券会社コード : String, 通貨コード : String)
            //（共通）通貨のインスタンスを生成する。
            //[コンストラクタに渡す引数]
            //　@証券会社コード：　@取得した補助口座.getInstitution().getInstitutionCode()の戻り値
            //　@通貨コード：　@債券銘柄.get通貨コード()の戻り値
            WEB3GentradeCurrency l_currency =
                WEB3GentradeCurrency.genCurrency(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_strCurrencyCode);

            //1.11.3 get為替レート(is買付 : boolean, is約定計算 : boolean, 入力為替レート : double)
            //為替レートを取得する。
            //[get為替レート()に渡す引数]
            //　@is買付：　@true
            //　@is約定計算：　@false
            //　@入力為替レート：　@0
            l_dblExchangeRate = l_currency.getExchangeRate(true, false, 0);
        }

        //1.12 get発注日( )
        //発注日を取得する。
        Date l_datOrderBizDate = WEB3BondTradingTimeManagement.getOrderBizDate();

        //1.13 債券注文種別判定(注文種別, String)
        //債券注文種別判定を作成する。
        //[コンストラクタに渡す引数]
        //　@注文種別：　@OrderTypeEnum.債券買注文
        //　@取引：　@リクエストデータ.取引区分 == "買付"の場合、92：国内仕切取引。
        //　@　@　@　@　@　@リクエストデータ.取引区分 == "応募"の場合、35：募集取引。
        String l_strDealType = null;
        if (WEB3BondDealDivDef.BUY.equals(l_inputRequest.tradeDiv))
        {
            l_strDealType = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        }
        else if (WEB3BondDealDivDef.RECRUIT.equals(l_inputRequest.tradeDiv))
        {
            l_strDealType =WEB3DealTypeDef.RECRUIT_TRADING;
        }
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_BUY,
                l_strDealType);

        //create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄, String, Branch)
        WEB3BondExecuteDateInfo l_execDateInfo = l_bondOrderManager.createBondExecutionDateInfo(
            l_datOrderBizDate,
            l_bondOrderTypeJudge,
            l_bondProduct,
            WEB3SettlementDivDef.JAPANESE_CURRENCY,
            l_subAccount.getMainAccount().getBranch());

        //1.15 getその他商品買付可能額(補助口座 : 補助口座, 受渡日 : Date)
        //買付可能額を取得する。
        //[引数]
        //　@補助口座：　@get補助口座（）の戻り値
        //　@受渡日：　@債券約定日情報.get入金日（）の戻り値
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblOtherTradingPower =
            l_tpTradingPowerService.getOtherTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_execDateInfo.getPaymentDate());

        //1.16 createResponse( )
        WEB3BondApplyBuyInputResponse l_response =
            new WEB3BondApplyBuyInputResponse();

        //1.17 プロパティ・セット
        //買付可能額 = getその他商品買付可能額()の戻り値
        l_response.tradingPower =
            WEB3StringTypeUtility.formatNumber(l_dblOtherTradingPower);

        //銘柄ID      = 債券銘柄.銘柄ID
        l_response.productId =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getProductId());

        //銘柄名       = 債券銘柄.銘柄名
        l_response.productName = l_bondProduct.getProductName();

        //種別コード     = 債券銘柄.種別コード
        l_response.bondCategCode = l_bondProduct.getBondCategCode();

        //S&P       = 債券銘柄.S&P
        l_response.sAndP = l_bondProduct.getSAndP();

        //Moody's       = 債券銘柄.MOODY'S
        l_response.moodys = l_bondProduct.getMoodys();

        //利率        = 債券銘柄.利率
        l_response.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //年間利払回数    = 債券銘柄.年間利払回数
        l_response.yearlyInterestPayments =
            WEB3StringTypeUtility.formatNumber(l_bondProduct .getYearlyInterestPayments());

        //利払日1      = 債券銘柄.利払日1
        l_response.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();

        //利払日2      = 債券銘柄.利払日2
        l_response.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();

        //通貨コード     = 債券銘柄.通貨コード
        l_response.currencyCode = l_bondProduct.getCurrencyCode();

        //申込単位      = 債券銘柄.申込単位
        l_response.tradeUnit =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());

        //最低申込数量    = 債券銘柄.最低額面
        l_response.minOrderQuantity =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());

        //最高申込数量    = 債券銘柄.最高額面
        BondProductRow l_bondProductRow = (BondProductRow)l_bondProduct.getDataSourceObject();
        if (l_bondProductRow.getMaxFaceAmountIsNull())
        {
            l_response.maxOrderQuantity = null;
        }
        else
        {
            l_response.maxOrderQuantity =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMaxFaceAmount());
        }

        //(*1)リクエストデータ.取引区分 == ”応募”の場合のみセットする。それ以外の場合、null。
        if (WEB3BondDealDivDef.RECRUIT.equals(l_inputRequest.tradeDiv))
        {
            //応募開始日 = 債券銘柄.応募開始日(*1)
            l_response.recruitStartDate = l_bondProduct.getRecruitStartDate();

            //応募終了日 = 債券銘柄.応募終了日(*1)
            l_response.recruitEndDate = l_bondProduct.getRecruitEndDate();
        }
        else
        {
            //応募開始日
            l_response.recruitStartDate = null;

            //応募終了日
            l_response.recruitEndDate = null;
        }

        //買付単価      = 債券銘柄.買付単価
        if (l_bondProductRow.getBuyPriceIsNull())
        {
            l_response.buyPrice = null;
        }
        else
        {
            l_response.buyPrice =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());            
        }

        //発行日       = 債券銘柄.発行日
        l_response.issueDate = l_bondProduct.getIssueDate();

        //償還日       = 債券銘柄.償還日
        l_response.maturityDate = l_bondProduct.getMaturityDate();

        //(*2)is外貨建()の戻り値 == false の場合、nullをセットする。
        //買付基準為替レート = get為替レート()の戻り値(*2)
        if (l_blnIsForeignCurrency)
        {
            l_response.buyBaseFxRate =
                WEB3StringTypeUtility.formatNumber(l_dblExchangeRate);
        }
        else
        {
            l_response.buyBaseFxRate = null;
        }

        //決済区分一覧    = is外貨建()の戻り値 == falseの場合、”円貨”をセットする。
        //         is外貨建()の戻り値 == trueの場合、”円貨”、”外貨”をセットする。
        if (l_blnIsForeignCurrency)
        {
            l_response.settleDivList =
                new String[] {WEB3SettlementDivDef.JAPANESE_CURRENCY, WEB3SettlementDivDef.FOREIGN_CURRENCY};
        }
        else
        {
            l_response.settleDivList =
                new String[] {WEB3SettlementDivDef.JAPANESE_CURRENCY};
        }

        //仕入時の為替レート = 債券銘柄.仕入時の為替レート
        if (!l_bondProductRow.getBuyingFxRateIsNull())
        {
            l_response.fxRateAtStock = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyingFxRate());
        }

        //1.18
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
