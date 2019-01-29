head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplicationInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込入力サービスImpl(WEB3SrvRegiApplicationInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28 李頴淵 新規作成
Revesion History : 2007/06/05 張騰宇 (中訊) モデル242,243
Revesion History : 2007/06/26 崔遠鵬 (中訊) モデル272
Revesion History : 2007/11/01 金傑 (中訊) モデル304
Revesion History : 2008/02/18 周墨洋 (中訊) 仕様変更・モデル311,325
Revesion History : 2008/03/03 武波 (中訊) 仕様変更 モデル331
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiConsDoc;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiApplyKindDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiConsentRequest;
import webbroker3.srvregi.message.WEB3SrvRegiConsentResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiApplicationInputService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用申込入力サービスImpl)<BR>
 * サービス利用申込入力サービス実装クラス<BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiApplicationInputServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiApplicationInputService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiApplicationInputServiceImpl.class);

    /**
     * @@roseuid 416F3925000F
     */
    public WEB3SrvRegiApplicationInputServiceImpl()
    {

    }

    /**
     * サービス利用申込入力処理を行う。<BR>
     * <BR>
     * リクエストデータのクラスによって、同意書画面リクエスト( )メソッド、<BR>
     * または利用申込入力リクエスト( )メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F20D032D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request instanceof WEB3SrvRegiConsentRequest)
        {
            WEB3SrvRegiConsentResponse l_response =
                docScreenRequest((WEB3SrvRegiConsentRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3SrvRegiApplyInputRequest)
        {
            WEB3SrvRegiApplyInputResponse l_response =
                useAppliInputRequest((WEB3SrvRegiApplyInputRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (同意書画面リクエスト)<BR>
     * サービス利用申込同意書画面照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）同意書画面リクエスト」参照<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.13.リクエストデータ.無料属性申込区分 = '1' の場合<BR>
     * 　@　@　@　@1.13.1.以下の条件の場合、例外をスローする。<BR>
     * 　@　@　@　@getサービス申込属性情報() == null の場合、又は、<BR>
     * 　@　@　@　@getサービス申込属性情報().申込属性区分 == '2'(申込不可)<BR>
     * 　@　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.13.3.get無料対象期間( ) == null<BR>　@
     * 　@　@　@　@の場合例外をスローする<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.14.リクエストデータ.無料属性申込区分 == null の場合<BR>
     * 　@　@　@　@1.14.1.getサービス申込属性情報().申込属性区分 == <BR>
     * 　@　@　@　@'1'(無料対象)　@若しくは<BR>
     * 　@　@　@　@'2'(申込不可) の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用同意書リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiConsentResponse
     * @@roseuid 412567DC02D1
     */
    protected WEB3SrvRegiConsentResponse docScreenRequest(WEB3SrvRegiConsentRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " docScreenRequest(WEB3SrvRegiConsentRequest)";
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
        log.debug("validate注文受付可能");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate取引可能顧客
        log.debug("validate取引可能顧客");
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiConsentResponse l_response = (WEB3SrvRegiConsentResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.6 getサービスマスター
        log.debug("getサービスマスター");
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.7  is顧客申込可能
        boolean l_blnAccountAppliPossible =
            l_srvRegiServiceInfoManagement.isAccountAppliPossible((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster);
        log.debug("is顧客申込可能");
        if (!l_blnAccountAppliPossible)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.8 validate電子鳩同意( )
        l_srvRegiServiceInfoManagement.validateBatoAgreement(l_srvRegiServiceMaster);//WEB3BaseException

        //サービスマスタ.特殊処理区分 = null（通常サービス） 以外の場合
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        if (l_strSpecialProcessDiv != null)
        {
            //取得した補助口座オブジェクト
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            //is新規申込(補助口座, String)
            //[引数]
            //補助口座 = 取得した補助口座オブジェクト
            //サービス区分 = リクエストデータ.ＩＤ
            boolean l_blnIsNewApply =
                l_srvRegiServiceInfoManagement.isNewApply(
                    l_gentradeSubAccount,
                    l_request.serviceDiv);

            //validate特殊申込(サービスマスター, String, String, String, boolean)
            //[引数]
            //サービスマスタ = 取得したサービスマスターオブジェクト
            //証券会社コード = 補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
            //部店コード = 補助口座オブジェクト.getBranch( ).getBranchCode( )
            //口座コード = 補助口座オブジェクト.getMainAccount( ).getAccountCode( )
            //新規申込区分 = is新規申込( ) の戻り値
            l_srvRegiServiceInfoManagement.validateSpecialApply(
                l_srvRegiServiceMaster,
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode(),
                l_blnIsNewApply);
        }

        //U00859  start
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();

        if (WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strProvidModel))
        {
            if (!l_srvRegiServiceInfoManagement.isCommCond((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster))
            {
                log.debug("申込種別区分指定エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        //U00859  end

        //getサービス申込属性情報(String, String, String, String, String)
        List l_lisServiceAppliAttributeInfo =
            l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                l_strInstitutionCode,
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_request.serviceDiv,
                null);

        WEB3SrvRegiConsentResponse l_response = (WEB3SrvRegiConsentResponse)l_request.createResponse();

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;

        //リクエストデータ.無料属性申込区分 = '1' の場合
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //getサービス申込属性情報() == null の場合、
            //又は、
            //getサービス申込属性情報().申込属性区分 == '2'(申込不可) の場合、
            //例外をスローする。
            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(
                    ((SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0)).getAppliAttribute()))
            {
                log.debug("サービス申込属性情報がnull、または申込属性区分が'2'(申込不可)です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "サービス申込属性情報がnull、または申込属性区分が'2'(申込不可)です。");
            }

            //get無料対象期間( )
            String l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get無料対象期間( ) == null　@の場合例外をスローする。
            if (l_strFreeTargetPeriod == null)
            {
                log.debug("無料対象期間が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "無料対象期間が未指定です。");
            }
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
            //プロパティセット
            //申込属性区分 = getサービス申込属性情報().申込属性区分
            l_response.applyAttributeDiv = l_srvAppliAttributeRow.getAppliAttribute();
            //申込属性期間From = getサービス申込属性情報().適用期間From
            l_response.applyAttributePeriodFrom = l_srvAppliAttributeRow.getAppliStartDate();
            //申込属性期間To = getサービス申込属性情報().適用期間To
            l_response.applyAttributePeriodTo = l_srvAppliAttributeRow.getAppliEndDate();
            //無料属性申込区分 = '1'（無料属性申込）
            l_response.freeAttributeApplyDiv =
                WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
        }

        //1.8 get同意書文言
        log.debug("get同意書文言");
        WEB3SrvRegiConsDoc l_srvRegiConsDoc = l_srvRegiServiceMaster.getConsDoc(false);

        //1.9 createレスポンス
        l_response.consentSentence = l_srvRegiConsDoc.getCons();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (利用申込入力リクエスト)<BR>
     * サービス利用申込入力画面照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）利用申込入力リクエスト」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）利用申込入力リクエスト」): <BR>
     *         1.7.isMainAccountAppli(補助口座, WEB3SrvRegiServiceMaster) <BR>
     *          is顧客申込可能( )=falseの場合、<BR>
     *          例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）利用申込入力リクエスト」): <BR>
     *         1.8.2.2  <申込種別区分="無料申込"であり、かつis手数料条件( )=falseの場合、<BR>
     *       （申込種別区分指定エラー）として例外をスローする。><BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01179<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）利用申込入力リクエスト」): <BR>
     *         1.8.3.1  get提供形式( )=null であり、かつ<BR>
     *        リクエストデータ.申込種別区分="無料申込"の場合、<BR>
     *        申込種別区分指定エラーとして例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01179<BR>
     * ==========================================================<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.10.リクエストデータ.無料属性申込区分 = '1' の場合<BR>
     * 　@　@　@　@1.10.1.以下の条件の場合、例外をスローする。<BR>
     * 　@　@　@　@getサービス申込属性情報() == null の場合、又は、<BR>
     * 　@　@　@　@getサービス申込属性情報().申込属性区分 == '2'(申込不可)<BR>
     * 　@　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.10.3.get無料対象期間( ) == null<BR>　@
     * 　@　@　@　@の場合例外をスローする<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.11.リクエストデータ.無料属性申込区分 == null の場合<BR>
     * 　@　@　@　@1.11.1.getサービス申込属性情報().申込属性区分 == <BR>
     * 　@　@　@　@'1'(無料対象)　@若しくは<BR>
     * 　@　@　@　@'2'(申込不可) の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用申込入力リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyInputResponse
     * @@roseuid 4125674A01A9
     */
    protected WEB3SrvRegiApplyInputResponse useAppliInputRequest(WEB3SrvRegiApplyInputRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " useAppliInputRequest(WEB3SrvRegiApplyInputRequest)";
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
        log.debug("validate注文受付可能");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate取引可能顧客
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        log.debug("validate取引可能顧客");
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiApplyInputResponse l_response = (WEB3SrvRegiApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.6 getサービスマスター
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.7  is顧客申込可能
        boolean l_blnAccountAppliPossible =
            l_srvRegiServiceInfoManagement.isAccountAppliPossible((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster);
        log.debug("is顧客申込可能");
        if (!l_blnAccountAppliPossible)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.7.1 get申込要サービス
        log.debug(" get申込要サービス");
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //1.7.1.1 get抽選設定
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        
        //1.8 validate電子鳩同意( )
        l_srvRegiServiceInfoManagement.validateBatoAgreement(l_srvRegiServiceMaster);//WEB3BaseException

        //サービスマスタ.特殊処理区分 = null（通常サービス） 以外の場合
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        if (l_strSpecialProcessDiv != null)
        {
            //取得した補助口座オブジェクト
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            //is新規申込(補助口座, String)
            //[引数]
            //補助口座 = 取得した補助口座オブジェクト
            //サービス区分 = リクエストデータ.ＩＤ
            boolean l_blnIsNewApply =
                l_srvRegiServiceInfoManagement.isNewApply(
                    l_gentradeSubAccount,
                    l_request.serviceDiv);

            //validate特殊申込(サービスマスター, String, String, String, boolean)
            //[引数]
            //サービスマスタ = 取得したサービスマスターオブジェクト
            //証券会社コード = 補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
            //部店コード = 補助口座オブジェクト.getBranch( ).getBranchCode( )
            //口座コード = 補助口座オブジェクト.getMainAccount( ).getAccountCode( )
            //新規申込区分 = is新規申込( ) の戻り値
            l_srvRegiServiceInfoManagement.validateSpecialApply(
                l_srvRegiServiceMaster,
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode(),
                l_blnIsNewApply);
        }

        //getサービス申込属性情報(String, String, String, String, String)
        List l_lisServiceAppliAttributeInfo =
            l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                l_strInstitutionCode,
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_request.serviceDiv,
                null);

        //無料対象期間
        String l_strFreeTargetPeriod = null;

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        if (l_lisServiceAppliAttributeInfo != null)
        {
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
        }

        //リクエストデータ.無料属性申込区分 = '1'　@の場合
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //getサービス申込属性情報() == null の場合、
            //又は、
            //getサービス申込属性情報().申込属性区分 == '2'(申込不可) の場合、
            //例外をスローする。
            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(
                    l_srvAppliAttributeRow.getAppliAttribute()))
            {
                log.debug("サービス申込属性情報がnull、または申込属性区分が'2'(申込不可)です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "サービス申込属性情報がnull、または申込属性区分が'2'(申込不可)です。");
            }
            //get無料対象期間( )
            l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get無料対象期間( ) == null　@の場合例外をスローする。
            if (l_strFreeTargetPeriod == null)
            {
                log.debug("無料対象期間が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "無料対象期間が未指定です。");
            }
        }

        //1.8  <分岐処理 *1>
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = null;
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfoBefore = null;
        WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfo = null;
        boolean l_blnTrialAppliPossible = true;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("//1.8.1 get提供形式");
            //1.8.1 get提供形式
            String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();

            //1.8.2  <get提供形式!=nullの場合>
            boolean l_blnCommCond = false;
            if (l_strProvidModel != null)
            {
                log.debug("<get提供形式!=nullの場合>");
                //1.8.2.1 is手数料条件
                l_blnCommCond =
                    l_srvRegiServiceInfoManagement.isCommCond((WEB3GentradeSubAccount)l_subAccount, l_srvRegiServiceMaster);

                //1.8.2.2  <申込種別区分="無料申込"であり、かつis手数料条件( )=falseの場合、（申込種別区分指定エラー）として例外をスローする。>
                if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv) &&
                    !l_blnCommCond)
                {
                    log.debug("申込種別区分指定エラー");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            
            //障害対応 NO_U1570
            // 以下の条件を満たしている場合、以下の処理をスキップする。
            // ①@is手数料条件() = true
            // ②リクエスト.申込種別区分 != 1(継続申込)
            if (!l_blnCommCond || (WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_request.applyKindDiv)))
            {
                //get提供形式( )=null であり、かつリクエストデータ.申込種別区分="無料申込"の場合
                if (l_strProvidModel == null && WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv))
                {
                    log.debug("無料申込");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //リクエストデータ.無料属性申込区分 == null の場合
                if (l_request.freeAttributeApplyDiv == null)
                {
                    //1.8.1  is試用申込可能
                    log.debug("is試用申込可能");
                    l_blnTrialAppliPossible = l_srvRegiServiceInfoManagement.isTrialAppliPossible
                        (l_strInstitutionCode, l_strBranchCode, l_request.serviceDiv, l_strAccountCode);
                    
                    //1.8.2 getサービス利用期間料金一覧
                    WEB3SrvRegiServiceUsePeriodAmt[] l_srvRegiServiceUsePeriodAmt = l_srvRegiServiceMaster.getSrvUseTermAmtList();
                    
                    //1.8.3  <繰り返し処理 *2>
                    //1.8.3.1 サービス利用期間料金情報
                    int l_intLength = l_srvRegiServiceUsePeriodAmt.length;
                    l_srvRegiChargeInfo = new WEB3SrvRegiChargeInfo[l_intLength];
                    for (int i = 0; i < l_intLength; i++)
                    {
                        log.debug("//1.8.3.2  <プロパティ・セット>");
                        l_srvRegiChargeInfo[i] = new WEB3SrvRegiChargeInfo();
                        //1.8.3.2  <プロパティ・セット>
                        //○利用期間ID=取得したサービス利用期間料金オブジェクト.get通番( )
                        l_srvRegiChargeInfo[i].chargeId =
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt[i].getConsecutiveNumbers());
                        //○利用期間単位区分=取得したサービス利用期間料金オブジェクト.get利用期間区分( )
                        l_srvRegiChargeInfo[i].chargeDiv = l_srvRegiServiceUsePeriodAmt[i].getSrvUsePeriodDiv();
                        //○利用期間=取得したサービス利用期間料金オブジェクト.get利用期間( )
                        l_srvRegiChargeInfo[i].chargePeriod =
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt[i].getSrvUsePeriod());
                        //○利用料金=取得したサービス利用期間料金オブジェクト.get利用料金( )
                        l_srvRegiChargeInfo[i].chargeAmt =
                            WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt[i].getUseAmt());
                        //○無効区分="有効"
                        l_srvRegiChargeInfo[i].invalidDiv = false;
                    }
                }
            }
        }
        //1.9 <分岐処理 *3>
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("//1.9.1 getサービス抽選情報");
            //1.9.1 getサービス抽選情報
            Timestamp l_ts = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_ts, 0);

            //1.9.2 getサービス抽選情報
            l_srvRegiServiceLotInfoBefore =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_ts, -1);
        }
        //1.10 createレスポンス
        WEB3SrvRegiApplyInputResponse l_response = (WEB3SrvRegiApplyInputResponse)l_request.createResponse();

        //1.11 <レスポンス・セット>
        //○抽選設定=取得した申込要サービスオブジェクト.get抽選設定( )
        l_response.lotteryDiv = l_srvRegiApplicationRequiredService.getLotDiv();
        //○サービス名称=取得したサービスマスターオブジェクト.getサービス名称( )
        l_response.serviceName = l_srvRegiServiceMaster.getSrvName();
        //○サービス内容=取得した申込要サービスオブジェクト.getサービス内容( )
        l_response.serviceContent = l_srvRegiApplicationRequiredService.getSrvContents();
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
        {
            log.debug("//○運用区分=(*1) ");
            //○運用区分=(*1)
            //(*1-1) 申込要サービスオブジェクト.get抽選設定( )="無"の場合、nulｌをセットする。
            l_response.useDiv = null;
            // *---<抽選設定="無"の場合>---*
            if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv)
                || (l_lisServiceAppliAttributeInfo != null && l_strFreeTargetPeriod != null))
            {
                log.debug("<抽選設定=無の場合");
                l_response.chargeInfo = null;
                l_response.trialDiv = null;
                l_response.trialPeriod = null;
            }
            else
            {
                log.debug("○利用期間料金情報 =<*2 繰り返し処理>で作成した配列");
                //○利用期間料金情報 =<*2 繰り返し処理>で作成した配列
                l_response.chargeInfo = l_srvRegiChargeInfo;
                
                //仕様変更対応 NO_201
				String l_trialDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();
				Integer l_trialPeriod = l_srvRegiApplicationRequiredService.getTrialPeriod();
				
				//サービス申込要テーブルに試用申込区分･期間が設定されている場合
				if(WEB3StringTypeUtility.isNotEmpty(l_trialDiv) &&
					(l_trialPeriod != null) && !(l_trialPeriod.equals("")))
				{
					log.debug("trialDiv != null && trialPeriod != null の場合 ");
					//サービス情報管理.is試用申込可能( )=trueの場合
					if (l_blnTrialAppliPossible)
					{
						log.debug("is試用申込可能( )=trueの場合 ");
						l_response.trialDiv = l_trialDiv;
						l_response.trialPeriod = l_trialPeriod.toString();
					}
					//サービス情報管理.is試用申込可能( )=falseの場合
					else
					{
						log.debug("is試用申込可能( )=falseの場合 ");
						l_response.trialDiv = l_trialDiv;
						l_response.trialPeriod = null;
					}
				}
				//サービス申込要テーブルに試用申込区分･期間が設定されていない場合
				else
				{
					log.debug("trialDiv = null && trialPeriod = null の場合 ");
					l_response.trialDiv = null;
					l_response.trialPeriod = null;
				}
            }

            //○募集枠　@　@　@　@　@　@　@ =null
            l_response.applyMax = null;
            //○申込期間（自）　@　@　@ =null
            l_response.applyStartDate = null;
            //○申込期間（至）　@　@　@ =null
            l_response.applyEndDate = null;
            //○抽選日　@　@　@　@　@　@　@ =null
            l_response.lotteryDate = null;
            //○適用開始日　@　@　@　@ =null
            l_response.trialStartDate = null;
            //○適用終了日　@　@　@　@ =null
            l_response.trialEndDate = null;
            //○利用料金　@　@　@　@　@　@ =null
            l_response.chargeAmt = null;
            //○入札単位　@　@　@　@　@　@ =null
            l_response.biddingPriceUnit = null;
            //○前回の最高落札額 =null
            l_response.maxSuccBidding = null;
            //○前回の最低落札額 =null
            l_response.minSuccBidding = null;
            //○前回の加重平均額 =null
            l_response.weightedAverage = null;

            //[リクエストデータ.無料属性申込区分 == '1'(無料属性申込)の場合]
            if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
                l_request.freeAttributeApplyDiv))
            {
                //○申込属性区分     = getサービス申込属性情報().申込属性区分
                l_response.applyAttributeDiv = l_srvAppliAttributeRow.getAppliAttribute();
                //○無料対象期間     = get無料対象期間()の戻り値
                l_response.freeTargetPeriod = l_strFreeTargetPeriod;
                //○無料属性申込区分 = '1'
                l_response.freeAttributeApplyDiv =
                    WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
            }
        }
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_srvRegiApplicationRequiredService.getLotDiv()))
        {
            log.debug("//(*1-2) 申込要サービスオブジェクト.get抽選設定( )=有の場合");
            //(*1-2) 申込要サービスオブジェクト.get抽選設定( )="有"の場合
            // 取得したサービス抽選情報オブジェクト.get運用区分( )をセットする。
            l_response.useDiv = l_srvRegiServiceLotInfo.getInvestDiv();

            // *---<抽選設定="有"の場合>---*
            //○利用期間料金情報 =null
            l_response.chargeInfo = null;
            //○試用期間単位区分 =null
            l_response.trialDiv = null;
            //○試用期間　@　@　@　@　@　@ =null
            l_response.trialPeriod = null;
            //○募集枠　@　@　@　@　@　@　@ =取得した申込対象となるサービス抽選情報.get募集枠( )
            log.debug("募集枠");
            if (l_srvRegiServiceLotInfo.getPublicOfferingQty() == null)
            {
                l_response.applyMax = null;
            }
            else
            {
                l_response.applyMax = l_srvRegiServiceLotInfo.getPublicOfferingQty().toString();
            }

            //○申込期間（自）　@　@　@ =取得した申込対象となるサービス抽選情報.get申込期間（自）( )
            l_response.applyStartDate = l_srvRegiServiceLotInfo.getAppliDateFrom();
            //○申込期間（至）　@　@　@ =取得した申込対象となるサービス抽選情報.get申込期間（至）( )
            l_response.applyEndDate = l_srvRegiServiceLotInfo.getAppliDateTo();
            //○抽選日　@　@　@　@　@　@　@ =取得した申込対象となるサービス抽選情報.get抽選日( )
            l_response.lotteryDate = l_srvRegiServiceLotInfo.getLotDate();
            //○適用開始日　@　@　@　@ =取得した申込対象となるサービス抽選情報.get適用開始日( )
            l_response.trialStartDate = l_srvRegiServiceLotInfo.getAppliStartDate();
            //○適用終了日　@　@　@　@ =取得した申込対象となるサービス抽選情報.get適用終了日( )
            l_response.trialEndDate = l_srvRegiServiceLotInfo.getAppliEndDate();
            //○利用料金　@　@　@　@　@　@ =取得した申込対象となるサービス抽選情報.get利用料金( )
            if (l_srvRegiServiceLotInfo.isAuctionSetting() &&
                l_srvRegiServiceLotInfo.getUseAmt() > 0)
            {
                //税率オブジェクトを生成する。
                log.debug("証券会社コード = " + l_subAccount.getInstitution().getInstitutionCode() + ", 発注日 = " + l_srvRegiServiceLotInfo.getPaymentDate());
                WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.CONSUMPTION_TAX,
                    l_srvRegiServiceLotInfo.getPaymentDate());

                //WEB3-SRVREGI-A-ＦＴ-0136
                //「（レコードの利用料金 / （１＋税率オブジェクト.get税率()の戻り値／100）の計算結果の小数部を四捨五入したもの」
                l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(Math.rint(l_srvRegiServiceLotInfo.getUseAmt() / (1 + l_taxRate.getTaxRate() / 100)));
            }
            else
            {
                log.debug("　@(*3-2) サービス抽選情報.isオークション設定( )==falseの場合－取得した申込対象となるサービス抽選情報.get利用料金( )をセットする。 ");
                l_response.chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
            }
            //○入札単位　@　@　@　@　@　@ =取得した申込対象となるサービス抽選情報.get入札単位( )
            if (l_srvRegiServiceLotInfo.getBiddingPrice() == null)
            {
                l_response.biddingPriceUnit = null;
            }
            else
            {
                l_response.biddingPriceUnit = l_srvRegiServiceLotInfo.getBiddingPrice().toString();
            }
            //○申込属性区分   = null
            l_response.applyAttributeDiv = null;
            //○無料対象期間  = null
            l_response.freeTargetPeriod = null;
            //○無料属性申込区分 = null
            l_response.freeAttributeApplyDiv = null;

            //*---<前回分のサービス抽選情報=nullの場合>---*
            if (l_srvRegiServiceLotInfoBefore == null)
            {
                log.debug("前回分のサービス抽選情報=nullの場合");
                //○前回の最高落札額 =null
                l_response.maxSuccBidding = null;
                //○前回の最低落札額 =null
                l_response.minSuccBidding = null;
                //○前回の加重平均額 =null
                l_response.weightedAverage = null;
            }
            //*---<前回分のサービス抽選情報!=nullの場合>---*
            else
            {
                log.debug("前回分のサービス抽選情報!=nullの場合");
                //○前回の最高落札額 =取得した前回分のサービス抽選情報.get最高落札額( )
                if (l_srvRegiServiceLotInfoBefore.getHighSuccessBid() == null)
                {
                    l_response.maxSuccBidding = null;
                }
                else
                {
                    l_response.maxSuccBidding = l_srvRegiServiceLotInfoBefore.getHighSuccessBid().toString();
                }

                //○前回の最低落札額 =取得した前回分のサービス抽選情報.get最低落札額( )
                if (l_srvRegiServiceLotInfoBefore.getLowSuccessBid() == null)
                {
                    l_response.minSuccBidding = null;
                }
                else
                {
                    l_response.minSuccBidding = l_srvRegiServiceLotInfoBefore.getLowSuccessBid().toString();
                }

                //○前回の加重平均額 =取得した前回分のサービス抽選情報.get加重平均額( )
                if (l_srvRegiServiceLotInfoBefore.getAverageSuccessBid() == null)
                {
                    l_response.weightedAverage = null;
                }
                else
                {
                    l_response.weightedAverage = l_srvRegiServiceLotInfoBefore.getAverageSuccessBid().toString();
                }

            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
