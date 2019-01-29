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
filename	WEB3BondDomesticApplyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募入力サービスImpl(WEB3BondDomesticApplyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.225
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderManagerReusableValidationsCheck;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3ProspectusCheckDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.WEB3BondTradingTimeManagement;

/**
 * (国内債券応募入力サービスImpl)<BR>
 * 国内債券応募入力サービスImpl<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputServiceImpl
    extends WEB3BondClientRequestService
    implements WEB3BondDomesticApplyInputService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputServiceImpl.class);

    /**
     * @@roseuid 46A473FC0399
     */
    public WEB3BondDomesticApplyInputServiceImpl()
    {

    }

    /**
     * 国内債券応募入力サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「input国内債券応募注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466CCFC70301
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        if (!(l_request instanceof WEB3BondDomesticApplyInputRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3BondDomesticApplyInputRequest l_inputRequest = (WEB3BondDomesticApplyInputRequest)l_request;

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_inputRequest.validate();

        //validate注文受付可能( )
        //受付時間チェック、緊急停止チェック、バッチ処理中チェックを行なう。
        WEB3BondTradingTimeManagement.validateOrderAccept();

        //get補助口座( )
        //補助口座オブジェクトを取得する。
        SubAccount l_subAccount = this.getSubAccount();

        //get債券銘柄(long)
        //債券銘柄を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
         (WEB3BondProductManager)l_finApp.getTradingModule(
             ProductTypeEnum.BOND).getProductManager();
        long l_lngProductId = Long.parseLong(l_inputRequest.productId);
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(l_lngProductId);

        //validate取引可能顧客
        //顧客別取引停止属性チェックをする
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

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

        //validate法@人顧客(補助口座, 債券銘柄)
        //顧客が法@人かどうかをチェックする。
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        l_bondOrderManager.validateCorporationAccount(l_subAccount, l_bondProduct);

        //validate顧客取扱可能銘柄<国内債券>(債券銘柄, String)
        //顧客取扱可能チェック、取引可能チェックを行なう。
        l_bondOrderManager.validateAccountHandlingPossibleProductBondDomestic(
            l_bondProduct,
            WEB3BondDealDivDef.RECRUIT);

        //validate国内債券応募枠(long, 債券銘柄, double)
        //注文数量が国内債券応募枠の範囲内であるかどうかチェックする。
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck =
            new WEB3BondOrderManagerReusableValidationsCheck();
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)(l_subAccount.getMainAccount().getDataSourceObject());
        l_validationsCheck.validateDomesticBondRecruitLimit(
            l_mainAccountRow.getBranchId(),
            l_bondProduct,
            0);

        //＜分岐処理＞目論見書閲覧チェックを実施する。
        //  リクエスト.電子鳩チェックフラグ   == True かつ
        //債券銘柄.目論見書閲覧チェック区分 == ｢目論見書をチェックする｣場合
        WEB3GentradeProspectusResult l_validateBataResult = null;
        if(l_inputRequest.batoCheckFlag &&
            WEB3ProspectusCheckDivDef.PROSPECTUS_CHECK.equals(l_bondProduct.getProspectusCheckDiv()))
        {
            //1.91 validate目論見書閲覧(種別コード : String, 銘柄コード : String)
            //目論見書閲覧チェックを実施する。
            WEB3GentradeBatoClientService l_bataService =
                (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

            l_validateBataResult =
                l_bataService.validateProspectus(
                    l_inputRequest.typeCode,
                    l_bondProduct.getProductCode());
        }

        //税率(証券会社コード : String, 税種類 : String, 発注日 : Date)
        WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
            l_bondProduct.getInstitution().getInstitutionCode(),
            WEB3DutyTypeDef.DOMESTIC_BOND_WITHHOLDING_TAX,
            new Timestamp(l_bondProduct.getBondDomesticBizDate().getTime()));

        //getその他商品買付可能額(補助口座 : 補助口座, 受渡日 : Date)
        //買付可能額を取得する。
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        double l_dblOtherTradingPower =
            l_tpTradingPowerService.getOtherTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_bondProduct.getDeliveryDate());

        //createResponse( )
        WEB3BondDomesticApplyInputResponse l_response =
            (WEB3BondDomesticApplyInputResponse)l_inputRequest.createResponse();

        //プロパティ・セット
        //以下の通り、プロパティをセットする。
        //買付可能額   = getその他商品買付可能額()の戻り値
        l_response.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblOtherTradingPower);
        //銘柄ID        = 債券銘柄.銘柄ID
        l_response.productId = String.valueOf(l_bondProduct.getProductId());
        //銘柄名     = 債券銘柄.銘柄名
        l_response.productName = l_bondProduct.getProductName();
        //応募開始日   = 債券銘柄.取扱開始日時
        l_response.recruitStartDate = l_bondProduct.getTradeStartDate();
        //応募終了日   = 債券銘柄.取扱終了日時
        l_response.recruitEndDate = l_bondProduct.getTradeEndDate();
        //利率      = 債券銘柄.利率
        l_response.coupon = WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());
        //利率(課税後) = 債券銘柄.利率 × ( 1 - (税率.get税率×0.01))
        BigDecimal l_bdCoupon = new BigDecimal(String.valueOf(l_bondProduct.getCoupon()));
        BigDecimal l_dbTaxRate = new BigDecimal(String.valueOf(l_taxRate.getTaxRate()));
        l_response.couponAfterTax =
            WEB3StringTypeUtility.formatNumber(
                l_bdCoupon.multiply(
                    (new BigDecimal(String.valueOf("1")).subtract(
                        l_dbTaxRate.multiply(
                            new BigDecimal(String.valueOf("0.01")))))).doubleValue());
        //年間利払回数  = 債券銘柄.年間利払回数
        l_response.yearlyInterestPayments =
            String.valueOf(l_bondProduct.getYearlyInterestPayments());
        //利払日1        = 債券銘柄.利払日1
        l_response.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();
        //利払日2        = 債券銘柄.利払日2
        l_response.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();
        //応募単価        = 債券銘柄.買付単価
        l_response.applyPrice = WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        //申込単位        = 債券銘柄.申込単位
        l_response.applyUnit = WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());
        //発行日     = 債券銘柄.発行日
        l_response.issueDate = l_bondProduct.getIssueDate();
        //償還日     = 債券銘柄.償還日
        l_response.maturityDate = l_bondProduct.getMaturityDate();
        //目論見書閲覧チェック結果 = リクエスト.電子鳩チェックフラグ   == True かつ
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@ 　@ 債券銘柄.目論見書閲覧チェック区分 == ｢目論見書をチェックする｣場合、
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@validate目論見書閲覧()の結果オブジェクトをセット。
        //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ 　@ 上記以外の場合、nullをセットする。
        l_response.prospectusResult = l_validateBataResult;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
