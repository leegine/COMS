head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客登録サービスImpl(WEB3AdminSrvRegiAccountRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
Revesion History : 2008/02/26 武波 仕様変更 モデル322
Revesion History : 2008/03/03 武波 仕様変更 モデル332
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiNewAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客登録サービスImpl)<BR>
 * サービス利用管理者顧客登録サービス実装クラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountRegistServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountRegistService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountRegistServiceImpl.class);

    /**
     * @@roseuid 416F3928029F
     */
    public WEB3AdminSrvRegiAccountRegistServiceImpl()
    {

    }

    /**
     * サービス利用管理者顧客登録サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate登録( )または、<BR>
     * submit登録( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E04D00CC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3AdminSrvRegiCustomerRegistConfirmRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerRegistConfirmRequest");
            WEB3AdminSrvRegiCustomerRegistConfirmResponse l_srvRegiCustomerRegistConfirmResponse =
                this.validateRegist((WEB3AdminSrvRegiCustomerRegistConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiCustomerRegistCompleteRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerRegistCompleteRequest");
            WEB3AdminSrvRegiCustomerRegistCompleteResponse l_srvRegiCustomerRegistCompleteResponse =
                this.submitRegist((WEB3AdminSrvRegiCustomerRegistCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistCompleteResponse;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate登録)<BR>
     * サービス利用管理者顧客登録審査処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客登録審査」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客登録確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E11B02C0
     */
    protected WEB3AdminSrvRegiCustomerRegistConfirmResponse validateRegist(WEB3AdminSrvRegiCustomerRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminSrvRegiCustomerRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_request.validate();

        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //1.5 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);

        //1.6 get証券会社コード()
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //部店コード
        String l_strBranchCode = l_request.branchCode;

        //顧客コード
        String l_strAccountCode = l_request.accountCode;

        //拡張アカウントマネージャ
        //６桁のリクエストデータ.顧客コードから７桁の顧客コードを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //1.7 get顧客(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

        //1.8 getAccountCode()
        String l_strDbAccountCode = l_mainAccount.getAccountCode();

        //1.9 createサービス利用新規申込内容(String, String, String, String,
        //   Timestamp, Timestamp, Timestamp, String, Double, Timestamp)
        //サービス区分
        String l_strServiceDiv  = l_request.serviceDiv;

        //適用開始日
        Timestamp l_tsTrialStartDate = new Timestamp(l_request.trialStartDate.getTime());

        //適用終了日
        Timestamp l_tsTrialEndDate = new Timestamp(l_request.trialEndDate.getTime());

        //申込日
        //get抽選設定()="無"であり、リクエストデータ.申込日==nullの場合、リクエストデータ.適用開始日をセットする。
        //上記以外の場合、リクエストデータ.申込日をセットする。
        Timestamp l_tsApplyDate = null;

        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv())
            && l_request.applyDate == null)
        {
            l_tsApplyDate = new Timestamp(l_request.trialStartDate.getTime());
        }
        else
        {
            if (l_request.applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.applyDate.getTime());
            }
        }

        //登録区分
        String l_strRegistDiv = l_request.registDiv;

        //利用料金
        String l_strChargeAmt = l_request.chargeAmt;
        Double l_dblChargeAmt = null;

        if (l_strChargeAmt  != null)
        {
            l_dblChargeAmt = Double.valueOf(l_strChargeAmt);
        }

        //出金日
        Timestamp l_tsPaymentDate = null;
        if(l_request.paymentDate != null)
        {
            l_tsPaymentDate = new Timestamp(l_request.paymentDate.getTime());
        }

        WEB3SrvRegiNewAppliSpec l_srvRegiNewAppliSpec=
            WEB3SrvRegiNewAppliSpec.createSrvRegiNewAppliSpec(l_strInstitutionCode, l_strServiceDiv,
            l_strBranchCode, l_strDbAccountCode, l_tsTrialStartDate, l_tsTrialEndDate, l_tsApplyDate,
            l_strRegistDiv, l_dblChargeAmt, l_tsPaymentDate, WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI);

        //1.10 validate申込登録(サービス利用新規申込内容)
        this.validateAppliRegist(l_srvRegiNewAppliSpec);

        //1.11 createレスポンス( )
        WEB3AdminSrvRegiCustomerRegistConfirmResponse l_srvRegiCustomerRegistConfirmResponse =
            (WEB3AdminSrvRegiCustomerRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_srvRegiCustomerRegistConfirmResponse;
    }

    /**
     * (submit登録)<BR>
     * サービス利用管理者顧客登録処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客登録」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客登録完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E16202A1
     */
    protected WEB3AdminSrvRegiCustomerRegistCompleteResponse submitRegist(WEB3AdminSrvRegiCustomerRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminSrvRegiCustomerRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_request.validate();

        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);

        //1.5 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //1.6 validate取引パスワード
        l_admin.validateTradingPassword(l_request.password);

        //1.7 get証券会社コード
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //部店コード
        String l_strBranchCode = l_request.branchCode;

        //顧客コード
        String l_strAccountCode = l_request.accountCode;

        // 拡張アカウントマネージャ
        //６桁のリクエストデータ.顧客コードから７桁の顧客コードを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //1.8 get顧客(String, String, String)
        WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

        //1.9 getAccountCode()
        String l_strDbAccountCode = l_mainAccount.getAccountCode();

        //1.10 createサービス利用新規申込内容(String, String, String, String, Timestamp, Timestamp, Timestamp, String, Double, Timestamp)
        //サービス区分
        String l_strServiceDiv  = l_request.serviceDiv;

        //適用開始日
        Timestamp l_tsTrialStartDate = new Timestamp(l_request.trialStartDate.getTime());

        //適用終了日
        Timestamp l_tsTrialEndDate = new Timestamp(l_request.trialEndDate.getTime());

        //申込日
        //get抽選設定()="無"であり、リクエストデータ.申込日==nullの場合、リクエストデータ.適用開始日をセットする。
        //上記以外の場合、リクエストデータ.申込日をセットする。
        Timestamp l_tsApplyDate = null;

        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_srvRegiApplicationRequiredService.getLotDiv())
            && l_request.applyDate == null)
        {
            l_tsApplyDate = new Timestamp(l_request.trialStartDate.getTime());
        }
        else
        {
            if (l_request.applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.applyDate.getTime());
            }
        }

        //登録区分
        String l_strRegistDiv = l_request.registDiv;

        //利用料金
        String l_strChargeAmt = l_request.chargeAmt;
        Double l_dblChargeAmt = null;

        if (l_strChargeAmt  != null)
        {
            l_dblChargeAmt = Double.valueOf(l_strChargeAmt);
        }

        //出金日
        Timestamp l_tsPaymentDate = null;
        if(l_request.paymentDate != null)
        {
            l_tsPaymentDate = new Timestamp(l_request.paymentDate.getTime());
        }

        WEB3SrvRegiNewAppliSpec l_srvRegiNewAppliSpec=
            WEB3SrvRegiNewAppliSpec.createSrvRegiNewAppliSpec(l_strInstitutionCode, l_strServiceDiv,
            l_strBranchCode, l_strDbAccountCode, l_tsTrialStartDate, l_tsTrialEndDate, l_tsApplyDate,
            l_strRegistDiv, l_dblChargeAmt, l_tsPaymentDate, WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI);

        //1.11 validate申込登録(サービス利用新規申込内容)
        this.validateAppliRegist(l_srvRegiNewAppliSpec);

        //1.12 分岐処理 *1
        WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

        Long l_orderId = null;
        if (WEB3PaymentDivDef.CHARGE.equals(l_request.registDiv) && Double.parseDouble(l_strChargeAmt) > 0)
        {
            SubAccount l_subAccount;
            try
            {
                //1.12.2 getSubAccount(arg0 : SubAccountTypeEnum)
                //[引数]  補助口座タイプ="株式取引口座（預り金）"
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT); //NotFoundException

                //補助口座
                WEB3GentradeSubAccount l_GentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
                
                //get取引パスワード(補助口座, リクエストデータ.暗証番号）
                String l_strTradingPassword = l_srvRegiRegistService.getTradingPassword(l_subAccount, l_request.password);
                
                //1.12.3  submit余力拘束(補助口座, Trader, double, Timestamp, String, String, String)
                l_orderId = new Long(l_srvRegiRegistService.submitRemainingPowerRestraint(
                    l_GentradeSubAccount, null, l_dblChargeAmt.doubleValue(),
                    l_tsPaymentDate, l_strServiceDiv, null,
                    l_strTradingPassword));

            }
            catch(NotFoundException l_nfd)
            {
             log.error(getClass().getName() + STR_METHOD_NAME);
             log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                 this.getClass().getName() + STR_METHOD_NAME);

            }

        }

        //1.13 submitサービス申込登録
        l_srvRegiRegistService.submitServiceRegist(l_srvRegiNewAppliSpec,l_orderId);

        //分岐処理＊2　@サービスマスター.特殊処理区分 = 1の場合
        SrvRegiMasterRow l_srvRegiMasterRow =
            (SrvRegiMasterRow)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterRow.getSpecialProcessDiv();
        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            //submit外部連携情報(String, String, String, String, Timestamp, Timestamp, boolean)
            //証券会社コード = get証券会社コード()の戻り値
            //部店コード = リクエストデータ.部店コード
            //口座コード = getAccountCode( )の戻り値
            //サービス区分 = リクエストデータ.サービス区分
            //適用開始日 = リクエストデータ適用開始日
            //適用終了日 = リクエストデータ適用終了日
            //新規申込区分 = true
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
            l_srvRegiOtherOrgService.submitOtherOrgInfo(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strDbAccountCode,
                l_strServiceDiv,
                l_tsTrialStartDate,
                l_tsTrialEndDate,
                true);
        }

        //1.14 createレスポンス( )
        WEB3AdminSrvRegiCustomerRegistCompleteResponse l_srvRegiCustomerRegistCompleteResponse =
            (WEB3AdminSrvRegiCustomerRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_srvRegiCustomerRegistCompleteResponse;
    }

    /**
     * (validate申込登録)<BR>
     * 顧客登録処理の発注審査を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）validate申込登録」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.1getSrvMaster(String, String, boolean)<BR>
     *         getサービスマスター( )の戻り値==nullの場合、<BR>
     *         例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00982<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.2.isAppliPossible( )<BR>
     *        <登録可否チェック><BR>
     *        申込が可能なサービスかをチェックする。<BR>
     *        （is申込可能の戻り値==falseの場合、例外をスローする。）<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.3.1.getLotDiv( )<BR>
     *        ≪各日付の論理チェック≫<BR>
     *       ○get抽選設定( )=="無"、かつ、引数.注文内容.申込日!=nullの場合、例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01001<BR>
     *       ○get抽選設定( )!="無"、かつ、引数.注文内容.申込日==nullの場合、例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00847<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.4.1getServiceRegist(String, String, String, String, String, String, )<BR>
     *         getサービス申込登録( )の戻り値 != null、かつ<BR>
     *         サービス申込登録オブジェクト.get申込抽選区分( )=="当選／本申込"<BR>
     *         の場合、例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01002<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.5.1getSrvLotInfo(String, String, Timestamp, int)<BR>
     *        引数.注文内容.申込日が、抽選情報の申込期間内にあるかをチェックする。<BR>
     *        （getサービス抽選情報( )の戻り値==nullの場合、例外をスローする。）<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01003<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.6.1<引数論理チェック><BR>
     *         <引数論理チェック><BR>
     *         以下の場合、例外をスローする。 <BR>
     *          ○引数.注文内容.利用料金==0の場合<BR>
     *          ○引数.注文内容.出金日==nullの場合<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01004<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.6.2.1isBizDate(Timestamp)<BR>
     *         is営業日( )==falseの場合、例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00990<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）validate申込登録」): <BR>
     *         1.6.2.2 get発注日( )<BR>
     *         get発注日( )≧引数.注文内容.出金日の場合、<BR>
     *         例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01005<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「管理者顧客登録 / （サービス利用管理者）validate申込登録」): <BR>
     *        1.6.2.4. 出金日＜get発注日()の場合、例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_01835<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「管理者顧客登録 / （サービス利用管理者）validate申込登録」): <BR>
     *        1.6.2.5 get発注日()の翌営業日（roll()の戻り値）≧引数.注文内容.出金日の場合、<BR>
     *        例外をスローする。<BR>
     *        class: WEB3BusinessLayerException<BR>
     *        tag:   BUSINESS_ERROR_01836<BR>
     * ==========================================================<BR>
     * @@param l_orderSpec - (注文内容)<BR>
     * @@roseuid 413E60AE0394
     */
    private void validateAppliRegist(WEB3SrvRegiNewAppliSpec l_orderSpec) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " validateAppliRegist(WEB3SrvRegiNewAppliSpec)";
        log.entering(STR_METHOD_NAME);

        if (l_orderSpec == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //1.1 getサービスマスター
        //証券会社コード
        String l_strInstitutionCode = l_orderSpec.getInstitutionCode();

        //サービス区分
        String l_strServiceDiv = l_orderSpec.getSrvDiv();

        // サービス情報管理
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strServiceDiv, false);

        if (l_srvRegiServiceMaster == null)
        {
            //サービスマスターデータを取得できません。
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00982,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.2 is申込可能( )
        if (!l_srvRegiServiceMaster.isAppliPossible())
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 get申込要サービス(boolean)
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        if(l_srvRegiApplicationRequiredService == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 getClass().getName() + STR_METHOD_NAME);
        }

        //validate特殊申込(サービスマスター, String, String, String, boolean)
        //サービスマスター = getサービスマスター（）の戻り値
        //証券会社コード = 引数.注文内容.証券会社コード
        //部店コード = 引数.注文内容.部店コード
        //口座コード = 引数.注文内容.口座コード
        //新規申込区分 = ture
        l_srvRegiServiceInfoManagement.validateSpecialApply(
            l_srvRegiServiceMaster,
            l_orderSpec.getInstitutionCode(),
            l_orderSpec.getBranchCode(),
            l_orderSpec.getAccountCode(),
            true);

        //1.3.1 get抽選設定( )
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //get抽選設定( )!="無"、かつ、引数.注文内容.申込日==nullの場合、例外をスローする。
        if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) && l_orderSpec.getAppliDate() == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00847,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.4<分岐処理 *1>
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
        WEB3SrvRegiRegistService l_srvRegiRegistService = null;

        //WEB3AdminSrvRegiAccountRegistService
        //サービス利用管理者顧客登録サービス
        l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            //1.4.1 getサービス申込登録(String, String, String, String, String, String, )
            l_gentradeSrvRegiApplication =
                l_srvRegiRegistService.getServiceRegist(l_orderSpec.getInstitutionCode(),
                l_orderSpec.getBranchCode(), l_orderSpec.getSrvDiv(),
                l_orderSpec.getAccountCode(), WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
                WEB3EffectiveDivDef.EFFECTIVE, false);

            // getサービス申込登録( )の戻り値 != null、かつ
            // サービス申込登録オブジェクト.get申込抽選区分( )=="当選／本申込"
            // の場合、例外をスローする。
            if (l_gentradeSrvRegiApplication != null
                && WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()))
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

        }

        //1.5<分岐処理 *2>
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //1.5.1 getサービス抽選情報
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            String l_strAdminInstitutionCode = l_admin.getInstitutionCode();
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = l_srvRegiServiceInfoManagement.getSrvLotInfo(
                l_strAdminInstitutionCode, l_srvRegiServiceMaster.getSrvDiv(),
            new Timestamp(WEB3DateUtility.toDay(l_orderSpec.getAppliDate()).getTime()), 0);

            //getサービス抽選情報( )の戻り値==nullの場合、例外をスローする。
            if (l_srvRegiServiceLotInfo == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01003,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.5.2 validate適用期間(String, String, String, String, Timestamp, Timestamp, Long)
            l_srvRegiRegistService.validateAppliPeriod(l_orderSpec.getInstitutionCode(),
                l_orderSpec.getSrvDiv(),l_orderSpec.getBranchCode(), l_orderSpec.getAccountCode(),
                new Timestamp(WEB3DateUtility.toDay(l_orderSpec.getAppliStartDate()).getTime()),
                new Timestamp(WEB3DateUtility.toDay(l_orderSpec.getAppliEndDate()).getTime()), null);

        }

        //1.6 <分岐処理 *3>
        if (WEB3PaymentDivDef.CHARGE.equals(l_orderSpec.getPaymentDiv()))
        {
            //1.6.1<引数論理チェック>
            if (l_orderSpec.getUseAmt() == null ||
                l_orderSpec.getUseAmt().doubleValue() == 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01030,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            if (l_orderSpec.getPaymentDate() == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01032,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6.2 <出金日のチェック>
            //1.6.2.1 is営業日(Timestamp)
            if (!WEB3SrvRegiTradingTimeManagement.isBizDate(l_orderSpec.getPaymentDate()))
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00990,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6.2.2 get発注日( )
            log.debug("WEB3SrvRegiTradingTimeManagement.getOrderBizDate()==="+WEB3SrvRegiTradingTimeManagement.getOrderBizDate());

            Date l_date = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

            //get発注日()≧引数.注文内容.出金日の場合、例外をスローする。
            if(WEB3DateUtility.compareToSecond(l_orderSpec.getPaymentDate(), l_date) <= 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01835,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6.3 get顧客(String, String, String)
            //拡張アカウントマネージャ
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //部店コード
            String l_strBranchCode = l_orderSpec.getBranchCode();

            //口座コード
            String l_strAccountCode = l_orderSpec.getAccountCode();

            //顧客
            WEB3GentradeMainAccount l_gentradeMainAccount = null;

            //顧客
            SubAccount l_subAccount;

            WEB3GentradeSubAccount l_gentradeSubAccount = null;
            try
            {
                //1.6.3 get顧客(String, String, String)
                l_gentradeMainAccount = l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

                //1.6.4 getSubAccount(arg0 : SubAccountTypeEnum)
                //[引数]  補助口座タイプ="株式取引口座（預り金）"
                l_subAccount = l_gentradeMainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT); //NotFoundException

                //補助口座
                l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            }
            catch(NotFoundException l_nfd)
            {
                log.error(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //利用料金
            Double l_useAmt = l_orderSpec.getUseAmt();

            //出金日
            Timestamp l_tsPaymentDate = l_orderSpec.getPaymentDate();

            //1.6.6 validate取引余力(補助口座, Trader, double, Timestamp, String, String, String)
            l_srvRegiRegistService.validateTradingPower(
                l_gentradeSubAccount, null, l_useAmt.doubleValue(), l_tsPaymentDate,
                l_srvRegiServiceMaster.getSrvDiv(), null);
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
