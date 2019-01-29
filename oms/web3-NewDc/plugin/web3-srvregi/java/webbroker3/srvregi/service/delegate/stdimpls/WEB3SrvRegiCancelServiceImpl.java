head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用取消サービスImpl(WEB3SrvRegiCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 李頴淵 新規作成
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiCancelService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用取消サービスImpl)<BR>
 * サービス利用取消サービス実装クラス<BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiCancelServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiCancelService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiCancelServiceImpl.class);

    /**
     * @@roseuid 416F39270242
     */
    public WEB3SrvRegiCancelServiceImpl()
    {

    }

    /**
     * サービス利用取消処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate取消( )または、 <BR>
     * submit取消( )メソッドをコールする。 <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EDE80010
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request instanceof WEB3SrvRegiCancelConfirmRequest)
        {
            log.debug("WEB3SrvRegiCancelConfirmRequest");
            WEB3SrvRegiCancelConfirmResponse l_response =
                validateCancel((WEB3SrvRegiCancelConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3SrvRegiCancelCompleteRequest)
        {
            log.debug("WEB3SrvRegiCancelCompleteRequest");
            WEB3SrvRegiCancelCompleteResponse l_response =
                submitCancel((WEB3SrvRegiCancelCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("パラメータタイプ不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate取消)<BR>
     * サービス利用取消審査処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）申込取消審査」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）申込取消審査」): <BR>
     *         1.6.getServiceRegist(String, String, String, String, String, String, )<BR>
     *          サービス申込登録オブジェクトが取得できなかった場合、<BR>
     *          例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00908<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）申込取消審査」): <BR>
     *          1.7.is取消可能( )<BR>
     *          取消可否判定<BR>
     *        　@falseが返却された場合、例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01010<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）申込取消審査」): <BR>
     *          1.9.1.1 get抽選設定( )<BR>
     *          get抽選設定( )="無"の場合、<BR>
     *          例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00878<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用取消確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EE0C007E
     */
    protected WEB3SrvRegiCancelConfirmResponse validateCancel(WEB3SrvRegiCancelConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateCancel(WEB3SrvRegiCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        l_request.validate();

        //1.2 validate注文受付可能
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        log.debug("get補助口座");
        //1.5 validate取引可能顧客
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate取引可能顧客");
            WEB3SrvRegiCancelConfirmResponse l_genResponse = (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();
            l_genResponse.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_genResponse.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_genResponse;
        }

        //1.6 getサービス申込登録
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        
		//障害対応 NO_U01542
		WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
			l_srvRegiRegistService.getServiceRegistCancelUnit(l_strInstitutionCode, l_strBranchCode, l_request.serviceDiv,
			l_strAccountCode, false);

        if (l_gentradeSrvRegiApplication == null)
        {
            log.debug("BUSINESS_ERROR_00908");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00908,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.7 get申込登録ID
        long l_lngRegistId = l_gentradeSrvRegiApplication.getRegistId();

        //1.8 is取消可能
        boolean l_blnCancelPossible = l_srvRegiRegistService.isCancelPossible(l_strInstitutionCode, l_strBranchCode,
            l_request.serviceDiv, l_strAccountCode, l_lngRegistId);
        if (!l_blnCancelPossible)
        {
            log.debug("BUSINESS_ERROR_01010");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.9 getサービスマスター
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.9.1 get申込要サービス
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.9.1.1 get抽選設定
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("BUSINESS_ERROR_00878");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00878,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.10 get申込日
        Timestamp l_tsAppliDate = l_gentradeSrvRegiApplication.getAppliDate();

        //1.11 getサービス抽選情報
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_tsAppliDate, 0);

        //1.12 get運用区分
        String l_strInvestDiv = l_srvRegiServiceLotInfo.getInvestDiv();

        //1.13 <分岐処理>
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfoBefore = null;
        if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv))
        {
            log.debug("getサービス抽選情報");
            //1.13.1 getサービス抽選情報
            l_srvRegiServiceLotInfoBefore =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_tsAppliDate, -1);
        }

        //1.14 createレスポンス
        WEB3SrvRegiCancelConfirmResponse l_response = (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();

        //1.15  <レスポンス・セット>
        //○抽選区分　@　@=取得したサービスマスターオブジェクト.get抽選設定( )
        l_response.lotteryDiv = l_strLotDiv;
        //○運用区分　@　@=申込対象のサービス抽選情報.get運用区分( )
        l_response.useDiv = l_srvRegiServiceLotInfo.getInvestDiv();
        //○サービス名称 =取得したサービスマスターオブジェクト.getサービス名称( )
        l_response.serviceName = l_srvRegiServiceMaster.getSrvName();
        //○サービス内容 =取得したサービスマスターオブジェクト.getサービス内容( )
        l_response.serviceContent = l_srvRegiApplicationRequiredService.getSrvContents();
        //○募集枠　@　@　@　@　@ =申込対象のサービス抽選情報.get募集枠( )
        if (l_srvRegiServiceLotInfo.getPublicOfferingQty() == null)
        {
            log.debug("l_response.applyMax = null;");
            l_response.applyMax = null;
        }
        else
        {
            log.debug("○募集枠");
            l_response.applyMax = l_srvRegiServiceLotInfo.getPublicOfferingQty().toString();
        }

        //○申込期間（自）　@=申込対象のサービス抽選情報.get申込期間（自）( )
        l_response.applyStartDate = l_srvRegiServiceLotInfo.getAppliDateFrom();
        //○申込期間（至）　@=申込対象のサービス抽選情報.get申込期間（至）( )
        l_response.applyEndDate = l_srvRegiServiceLotInfo.getAppliDateTo();
        //○抽選日　@　@　@　@　@ =申込対象のサービス抽選情報.get抽選日( )
        l_response.lotteryDate = l_srvRegiServiceLotInfo.getLotDate();
        //○適用開始日　@　@ =取得したサービス申込登録オブジェクト.get適用開始日( )
        l_response.trialStartDate = l_gentradeSrvRegiApplication.getAppliStartDate();
        //○適用終了日　@　@ =取得したサービス申込登録オブジェクト.get適用終了日( )
        l_response.trialEndDate = l_gentradeSrvRegiApplication.getAppliEndDate();
        //○入札単位　@　@　@　@=申込対象のサービス抽選情報.get入札単位( )
        if (l_srvRegiServiceLotInfo.getBiddingPrice() == null)
        {
            log.debug("l_response.biddingPriceUnit = null;");
            l_response.biddingPriceUnit = null;
        }
        else
        {
            log.debug("入札単位");
            l_response.biddingPriceUnit = l_srvRegiServiceLotInfo.getBiddingPrice().toString();
        }

        if (l_srvRegiServiceLotInfo.isAuctionSetting() &&
            l_gentradeSrvRegiApplication.getUseAmt() != null &&
            l_gentradeSrvRegiApplication.getUseAmt().doubleValue() > 0)
        {
            log.debug("(*1-1) 抽選区分='有'であり、 かつ取得したサービス抽選情報.isオークション設定( )=trueの場合");

            //税率オブジェクトを生成する。
            WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                WEB3DutyTypeDef.CONSUMPTION_TAX,
                l_srvRegiServiceLotInfo.getPaymentDate());

            //○利用料金
            double l_dblTaxRate = l_taxRate.getTaxRate();//税率

            //WEB3-SRVREGI-A-ＦＴ-0136
            l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(
                Math.rint(l_srvRegiServiceLotInfo.getUseAmt() / (1 + l_dblTaxRate / 100)));

            //○申込料金　@
            if (l_gentradeSrvRegiApplication.getUseAmt() == null)
            {
                l_response.applyAmt = null;
            }
            else
            {
                //WEB3-SRVREGI-A-ＦＴ-0136
                l_response.applyAmt = WEB3StringTypeUtility.formatNumber(
                    Math.rint(l_gentradeSrvRegiApplication.getUseAmt().doubleValue() / (1 + l_dblTaxRate / 100)));
            }
        }
        else
        {
            //○利用料金　@　@　@　@=申込対象のサービス抽選情報.get利用料金( )
            l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
            //○申込料金　@　@　@　@=取得したサービス申込登録オブジェクト.get利用料金( )
            if (l_gentradeSrvRegiApplication.getUseAmt() == null)
            {
                log.debug("l_response.applyAmt = null;");
                l_response.applyAmt = null;
            }
            else
            {
                log.debug("申込料金");
                l_response.applyAmt = WEB3StringTypeUtility.formatNumber(l_gentradeSrvRegiApplication.getUseAmt().doubleValue());
            }
        }

        //*---<前回分のサービス抽選情報=nullの場合>---*
        if (l_srvRegiServiceLotInfoBefore == null)
        {
            log.debug("前回分のサービス抽選情報=nullの場合");
            //○前回の最高落札額　@=null
            l_response.maxSuccBidding = null;
            //○前回の最低落札額　@=null
            l_response.minSuccBidding = null;
            //○前回の加重平均額　@=null
            l_response.weightedAverage = null;
        }
        //*---<前回分のサービス抽選情報!=nullの場合>---*
        else
        {
            log.debug("前回分のサービス抽選情報!=nullの場合");
            //○前回の最高落札額　@=前回分のサービス抽選情報.get最高落札額( )
            if (l_srvRegiServiceLotInfoBefore.getHighSuccessBid() == null)
            {
                log.debug("l_response.maxSuccBidding = null;");
                l_response.maxSuccBidding = null;
            }
            else
            {
                log.debug("前回の最高落札額");
                l_response.maxSuccBidding = l_srvRegiServiceLotInfoBefore.getHighSuccessBid().toString();
            }

            //○前回の最低落札額　@=前回分のサービス抽選情報.get最低落札額( )
            if (l_srvRegiServiceLotInfoBefore.getLowSuccessBid() == null)
            {
                log.debug("l_response.minSuccBidding = null;");
                l_response.minSuccBidding = null;
            }
            else
            {
                log.debug("前回の最低落札額");
                l_response.minSuccBidding = l_srvRegiServiceLotInfoBefore.getLowSuccessBid().toString();
            }

            //○前回の加重平均額　@=前回分のサービス抽選情報.get加重平均額( )
            if (l_srvRegiServiceLotInfoBefore.getAverageSuccessBid() == null)
            {
                log.debug("l_response.weightedAverage = null;");
                l_response.weightedAverage = null;
            }
            else
            {
                log.debug("前回の加重平均額");
                l_response.weightedAverage = l_srvRegiServiceLotInfoBefore.getAverageSuccessBid().toString();
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit取消)<BR>
     * サービス利用取消処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）申込取消」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）申込取消」): <BR>
     *         1.8.getServiceRegist(String, String, String, String, String, String, )  <BR>
     *          取消対象となるサービス申込登録オブジェクトが<BR>
     *          取得できなかった場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01009<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）申込取消」): <BR>
     *         1.9.is取消可能( )  <BR>
     *          取消可否判定<BR>
     *        　@falseが返却された場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01010<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）申込取消」): <BR>
     *          1.11.1.1 get抽選設定( )<BR>
     *          get抽選設定( )="無"の場合、<BR>
     *          例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00878<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用取消完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5EE0C008D
     */
    protected WEB3SrvRegiCancelCompleteResponse submitCancel(WEB3SrvRegiCancelCompleteRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitCancel(WEB3SrvRegiCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        l_request.validate();

        //1.2 validate注文受付可能
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate取引可能顧客
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate取引可能顧客");
            WEB3SrvRegiCancelCompleteResponse l_genResponse = (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_genResponse.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_genResponse.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_genResponse;
        }

        //1.6 get代理入力者
        Trader l_trader = this.getTrader();

        //1.7 validate取引パスワード
        OrderValidationResult l_orderValidationResult2 =
            l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if(l_orderValidationResult2.getProcessingResult().isFailedResult())
        {
            log.debug("validate取引パスワード");
            WEB3SrvRegiCancelCompleteResponse l_genResponse = (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_genResponse.errorInfo = l_orderValidationResult2.getProcessingResult().getErrorInfo();
            l_genResponse.errorMessage = l_orderValidationResult2.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_genResponse;
        }

        //1.8getサービス申込登録
        log.debug("getサービス申込登録");
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();

        //障害対応 NO_U01542
		WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
			l_srvRegiRegistService.getServiceRegistCancelUnit(l_strInstitutionCode, l_strBranchCode, l_request.serviceDiv,
			l_strAccountCode, true);
			
        if (l_gentradeSrvRegiApplication == null)
        {
            log.debug("BUSINESS_ERROR_01009");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01009,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.9 get申込登録ID
        long l_lngRegistId = l_gentradeSrvRegiApplication.getRegistId();

        //1.10 is取消可能
        boolean l_blnCancelPossible = l_srvRegiRegistService.isCancelPossible(l_strInstitutionCode, l_strBranchCode,
            l_request.serviceDiv, l_strAccountCode, l_lngRegistId);
        if (!l_blnCancelPossible)
        {
            log.debug("BUSINESS_ERROR_01010");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01010,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.11 getサービスマスター
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.11.1 get申込要サービス
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.11.1.1 get抽選設定
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("BUSINESS_ERROR_00878");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00878,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.12 get利用料金
        Double l_useAmt = l_gentradeSrvRegiApplication.getUseAmt();

        //1.13 <余力解放処理>
        if (!(l_useAmt == null || l_useAmt.doubleValue() == 0))
        {
            log.debug("余力解放処理");
            //1.13.1 get注文ID
            long l_lngOrderId = 0;
            if (l_gentradeSrvRegiApplication.getOrderId() != null)
            {
                l_lngOrderId = l_gentradeSrvRegiApplication.getOrderId().longValue();
            }

            //1.13.2 submit余力解放
            WEB3Crypt l_crypt = new WEB3Crypt();
            l_srvRegiRegistService.submitRemainingPowerRelease((WEB3GentradeSubAccount)l_subAccount, l_lngOrderId,
                l_crypt.decrypt(l_request.password));
        }

        //1.14 set取消区分
        l_gentradeSrvRegiApplication.setCancelDiv(WEB3SrvRegiCancelDivDef.CANCEL);

        //1.15 set最終更新者
        if (l_trader != null)
        {
            l_gentradeSrvRegiApplication.setLastUpdater(l_trader.getTraderCode());
        }
        else
        {
        	//障害対応 NO_2051
            l_gentradeSrvRegiApplication.setLastUpdater(l_strAccountCode.substring(0,6));
        }

        //1.16 saveサービス申込登録
        l_gentradeSrvRegiApplication.saveSrvRegiApplication();

        //1.17  createレスポンス
        WEB3SrvRegiCancelCompleteResponse l_response = (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_response.lastUpdatedTimestamp = l_tsSystemTimestamp;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
