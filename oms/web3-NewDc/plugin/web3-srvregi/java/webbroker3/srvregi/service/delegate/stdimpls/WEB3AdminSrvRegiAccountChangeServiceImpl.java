head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更サービスImpl(WEB3AdminSrvRegiAccountChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
Revesion History : 2008/02/26 武波 仕様変更 モデル323
Revesion History : 2008/03/03 武波 仕様変更 モデル333
Revesion History : 2008/03/19 武波 仕様変更 モデル355,358
Revesion History : 2008/03/28 武波 仕様変更 モデル364
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
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
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeGroup;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客変更サービスImpl)<BR>
 * サービス利用管理者顧客変更サービス実装クラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountChangeServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountChangeService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountChangeServiceImpl.class);

    /**
     * @@roseuid 416F39280167
     */
    public WEB3AdminSrvRegiAccountChangeServiceImpl()
    {

    }

    /**
     * サービス利用管理者顧客変更処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate変更( )または、<BR>
     * submit変更( )メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E36001C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3AdminSrvRegiCustomerChangeConfirmRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerChangeConfirmRequest");
            WEB3AdminSrvRegiCustomerChangeConfirmResponse l_srvRegiCustomerRegistConfirmResponse =
                validateChange((WEB3AdminSrvRegiCustomerChangeConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiCustomerChangeCompleteRequest)
        {
            log.debug("WEB3AdminSrvRegiCustomerChangeCompleteRequest");
            WEB3AdminSrvRegiCustomerChangeCompleteResponse l_srvRegiCustomerRegistCompleteResponse =
                submitChange((WEB3AdminSrvRegiCustomerChangeCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_srvRegiCustomerRegistCompleteResponse;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate変更)<BR>
     * サービス利用管理者顧客変更審査処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客変更審査」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客変更確認リクエスト　@オブジェクト<BR>
     * @@return WEB3AdminSrvRegiCustomerChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E3780233
     */
    protected WEB3AdminSrvRegiCustomerChangeConfirmResponse validateChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest)";
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

        //1.3 validate注文受付可能( )
        //WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);

        //1.5 証券会社コード
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6<繰り返し処理>
        WEB3AdminSrvRegiCustomerChangeGroup[] chgCustomerList = l_request.chgCustomerList;

        int l_intArrayLengh = chgCustomerList.length;

        for (int i = 0; i < l_intArrayLengh; i++)
        {
            //部店コード
            String l_strBranchCode = l_request.chgCustomerList[i].branchCode;

            //顧客コード
            String l_strAccountCode = l_request.chgCustomerList[i].accountCode;

            //拡張アカウントマネージャ
            //６桁のリクエストデータ.顧客コードから７桁の顧客コードを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //1.6.1 get顧客(String, String, String)
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

            //1.6.2 getAccountCode()
            String l_strDbAccountCode = l_mainAccount.getAccountCode();

            //適用開始日
            Timestamp l_tsTrialStartDate = new Timestamp(l_request.chgCustomerList[i].trialStartDate.getTime());

            //適用終了日
            Timestamp l_tsTrialEndDate = new Timestamp(l_request.chgCustomerList[i].trialEndDate.getTime());

            //申込日
            Timestamp l_tsApplyDate = null;
            if (l_request.chgCustomerList[i].applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.chgCustomerList[i].applyDate.getTime());
            }

            //利用料金
            Double l_dblChargeAmt = null;
            if(l_request.chgCustomerList[i].chargeAmt != null)
            {
                l_dblChargeAmt = new Double(l_request.chgCustomerList[i].chargeAmt);
            }

            //1.7.1 createサービス利用変更申込内容
            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec = new WEB3SrvRegiChangeAppliSpec();

            l_srvRegiChangeAppliSpec = WEB3SrvRegiChangeAppliSpec.createSrvRegiChangeAppliSpec(
                Long.parseLong(l_request.chgCustomerList[i].applyRegId), l_strInstitutionCode,
                l_request.serviceDiv, l_request.chgCustomerList[i].branchCode,
                l_strDbAccountCode, l_tsTrialStartDate,
                l_tsTrialEndDate, l_request.chgCustomerList[i].applyLotteryDiv,
                l_tsApplyDate, l_request.chgCustomerList[i].registDiv,
                l_dblChargeAmt);

            //1.7.2 validate申込変更(サービス利用変更申込内容)
            validateAppliChange(l_srvRegiChangeAppliSpec, l_admin);
        }

        //1.8 createレスポンス( )
        WEB3AdminSrvRegiCustomerChangeConfirmResponse l_srvRegiCustomerChangeConfirmResponse =
                (WEB3AdminSrvRegiCustomerChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_srvRegiCustomerChangeConfirmResponse;
    }

    /**
     * (submit変更)<BR>
     * サービス利用管理者顧客変更処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客変更」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客変更完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5E37D0168
     */
    protected WEB3AdminSrvRegiCustomerChangeCompleteResponse submitChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest)";
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

        //1.5 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);

        //1.6 証券会社コード
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7 <繰り返し処理>
        WEB3AdminSrvRegiCustomerChangeGroup[] l_chgCustomerList = l_request.chgCustomerList;

        int l_intArrayLengh = l_chgCustomerList.length;

        for (int i = 0; i < l_intArrayLengh; i++)
        {
            //部店コード
            String l_strBranchCode = l_request.chgCustomerList[i].branchCode;

            //顧客コード
            String l_strAccountCode = l_request.chgCustomerList[i].accountCode;

            //getサービスマスター(String, String, boolean)
            //　@証券会社コード=管理者オブジェクトより取得した証券会社コード 
            //サービス区分=リクエストデータ.サービス区分 
            //is行ロック=false
            WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
                new WEB3SrvRegiServiceInfoManagement();
            WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
                l_srvRegiServiceInfoManagement.getSrvMaster(
                    l_strInstitutionCode, l_request.serviceDiv, false);
            //拡張アカウントマネージャ
            //６桁のリクエストデータ.顧客コードから７桁の顧客コードを取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            //1.7.1 get顧客(String, String, String)
            WEB3GentradeMainAccount l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);

            //1.7.2 getAccountCode()
            String l_strDbAccountCode = l_mainAccount.getAccountCode();
            
			//口座をロックする。 
			//－拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。  
			WEB3GentradeAccountManager l_gentradeAccMgr = 
				(WEB3GentradeAccountManager) l_finApp.getAccountManager();
				
			l_gentradeAccMgr.lockAccount(
				l_strInstitutionCode,
			    l_strBranchCode,
			    l_strDbAccountCode);

            //適用開始日
            Timestamp l_tsTrialStartDate = new Timestamp(l_request.chgCustomerList[i].trialStartDate.getTime());

            //適用終了日
            Timestamp l_tsTrialEndDate = new Timestamp(l_request.chgCustomerList[i].trialEndDate.getTime());

            //申込日
            Timestamp l_tsApplyDate = null;
            if (l_request.chgCustomerList[i].applyDate != null)
            {
                l_tsApplyDate = new Timestamp(l_request.chgCustomerList[i].applyDate.getTime());
            }

            //利用料金
            Double l_dblChargeAmt = null;
            if(l_request.chgCustomerList[i].chargeAmt != null)
            {
                l_dblChargeAmt = new Double(l_request.chgCustomerList[i].chargeAmt);
            }

            //1.7.3 createサービス利用変更申込内容
            WEB3SrvRegiChangeAppliSpec l_srvRegiChangeAppliSpec = new WEB3SrvRegiChangeAppliSpec();

            l_srvRegiChangeAppliSpec = WEB3SrvRegiChangeAppliSpec.createSrvRegiChangeAppliSpec(
                Long.parseLong(l_request.chgCustomerList[i].applyRegId), l_strInstitutionCode,
                l_request.serviceDiv, l_request.chgCustomerList[i].branchCode,
                l_strDbAccountCode, l_tsTrialStartDate,
                l_tsTrialEndDate, l_request.chgCustomerList[i].applyLotteryDiv,
                l_tsApplyDate, l_request.chgCustomerList[i].registDiv,
                l_dblChargeAmt);

            //1.7.4 validate申込変更(サービス利用変更申込内容)
            validateAppliChange(l_srvRegiChangeAppliSpec, l_admin);

            //サービス利用申込登録サービス
            WEB3SrvRegiRegistService l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

            //1.7.5 getサービス申込登録
            //WEB3GentradeSrvRegiApplication
            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode,
                l_request.chgCustomerList[i].branchCode,
                l_request.serviceDiv, l_strDbAccountCode,
                Long.parseLong(l_request.chgCustomerList[i].applyRegId), false);

            //1.7.5.1 get注文ID
            Long l_orderId = l_gentradeSrvRegiApplication.getOrderId();

			//障害対応 NO_2060
			//障害対応 NO_2082
            //1.7.6 <分岐処理 *1>
            if (((WEB3AppliLotDivDef.APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
				|| (WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())))
                && WEB3AppliLotDivDef.DEFEAT.equals(l_request.chgCustomerList[i].applyLotteryDiv))
                || (WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_gentradeSrvRegiApplication.getAppliLotDiv())
                && WEB3DateUtility.compareToDay(l_gentradeSrvRegiApplication.getCancelLimitDate(), GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0
                && WEB3AppliLotDivDef.DEFEAT.equals(l_request.chgCustomerList[i].applyLotteryDiv)))
            {
                if (l_orderId != null)
                {
                    try
                    {
                        //1.7.6.1.1 getSubAccount
                        SubAccount l_subAccount = l_gentradeAccountManager.getSubAccount(
                            l_mainAccount.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException

                        //補助口座
                        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
                        
                        // get取引パスワード（補助口座, リクエストデータ.暗証番号）
                        String l_strTradingPassword = l_srvRegiRegistService.getTradingPassword(l_subAccount, l_request.password); 

                        //1.7.6.1.2 submit余力解放
                        l_srvRegiRegistService.submitRemainingPowerRelease(l_gentradeSubAccount, l_orderId.longValue(),
                            l_strTradingPassword);
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

            }

            //1.7.7 submitサービス申込変更
            l_srvRegiRegistService.submitServiceRegistChange(l_srvRegiChangeAppliSpec);

            //分岐処理＊2　@サービスマスター.特殊処理区分 = 1の場合
            SrvRegiMasterRow l_srvRegiMasterRow =
                (SrvRegiMasterRow)l_srvRegiServiceMaster.getDataSourceObject();
            String l_strSpecialProcessDiv = l_srvRegiMasterRow.getSpecialProcessDiv();
            if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
            {
                //submit外部連携情報(String, String, String, String, String, Timestamp, Timestamp, boolean)
                //証券会社コード = get証券会社コード()の戻り値
                //部店コード = リクエストデータ.部店コード
                //口座コード = getAccountCode( )の戻り値
                //申込抽選区分 = リクエスト.変更顧客一覧.申込抽選区分
                //サービス区分 = リクエストデータ.サービス区分
                //適用開始日 = リクエストデータ適用開始日
                //適用終了日 = リクエストデータ適用終了日
                //新規申込区分 = false
                WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                    (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                l_srvRegiOtherOrgService.submitOtherOrgInfo(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strDbAccountCode,
                    l_request.chgCustomerList[i].applyLotteryDiv,
                    l_request.serviceDiv,
                    l_tsTrialStartDate,
                    l_tsTrialEndDate,
                    false);
            }
        }

        //1.8 createレスポンス( )
        WEB3AdminSrvRegiCustomerChangeCompleteResponse l_srvRegiCustomerChangeCompleteResponse =
                (WEB3AdminSrvRegiCustomerChangeCompleteResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_srvRegiCustomerChangeCompleteResponse;
    }

    /**
     * (validate申込変更)<BR>
     * 顧客変更処理の発注審査を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）validate申込変更」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込変更」): <BR>
     *         1.1.getServiceRegist(String, String, String, String, long, )<BR>
     *           サービス申込登録を取得する。<BR>
     *           取得できなかった場合、例外をスロー。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00908<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込変更」): <BR>
     *         1.3.<申込抽選区分チェック><BR>
     *             <申込抽選区分チェック><BR>
     *             ●get抽選設定( )の戻り値="無"の場合<BR>
     *          　@　@リクエストデータ.注文内容.申込抽選区分が、<BR>
     *             以下の値以外の場合例外をスローする。<BR>
     *        　@　@　@「当選／本申込」「試用」<BR>
     *              class:WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_01007<BR>
     * <BR>
     *             ●get抽選設定( )の戻り値="無"以外の場合<BR>
     *           　@　@リクエストデータ.注文内容.申込抽選区分が、<BR>
     *              以下の値以外の場合例外をスローする。<BR>
     *         　@　@　@「当選／本申込」「落選」<BR>
     *              class:WEB3BusinessLayerException<BR>
     *              tag:   BUSINESS_ERROR_01008<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込変更」): <BR>
     *         1.4.1.getSrvLotInfo(String, String, Timestamp, int)<BR>
     *          引数.注文内容.申込日が、抽選情報の申込期間内にあるかをチェックする。<BR>
     *          （getサービス抽選情報( )の戻り値==nullの場合、例外をスローする。）<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01003<BR>
     * ==========================================================<BR>
     * @@param l_orderSpec - (注文内容)<BR>
     * @@roseuid 413E60F20307
     */
    private void validateAppliChange(WEB3SrvRegiChangeAppliSpec l_orderSpec, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAppliChange(WEB3SrvRegiChangeAppliSpec, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        //証券会社コード
        String l_strInstitutionCode = l_orderSpec.getInstitutionCode();

        //部店コード
        String l_strBranchCode = l_orderSpec.getBranchCode();

        //サービス区分
        String l_strSrvDiv = l_orderSpec.getSrvDiv();

        //口座コード
        String l_strAccountCode = l_orderSpec.getAccountCode();

        //申込登録ID
        long l_lngRegistId = Long.parseLong(l_orderSpec.getRegistId());

        //1.1 getサービス申込登録(String, String, String, String, long,)
        //サービス利用申込登録サービス
        WEB3SrvRegiRegistService l_srvRegiRegistService = (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        WEB3GentradeSrvRegiApplication l_gtradeSrvRegiApplication = null;

        l_gtradeSrvRegiApplication = l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode,
            l_strBranchCode, l_strSrvDiv, l_strAccountCode, l_lngRegistId, false);

        if (l_gtradeSrvRegiApplication == null)
        {
            //サービス申込登録データを取得できません。
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00908,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.2 validate部店権限(String[])
        l_admin.validateBranchPermission(l_gtradeSrvRegiApplication.getBranchCode());

        //1.3 getサービスマスター(String, String, boolean)
        //サービス情報管理
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();

        //サービスマスター
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.3.1 get申込要サービス(boolean)
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

        //1.3.1.1 get抽選設定()
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //1.5<分岐処理>
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //証券会社コード
            String l_strAdminInstitutionCode = l_admin.getInstitutionCode();

            //1.5.1 getサービス抽選情報(String, String, Timestamp, int)
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strAdminInstitutionCode,
                l_srvRegiServiceMaster.getSrvDiv(),
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
        }

        //1.6 validate適用期間(String, String, String, String, Timestamp, Timestamp, Long)
        //適用開始日
        Timestamp l_tsAppliStartDate = l_orderSpec.getAppliStartDate();

        //適用終了日
        Timestamp l_tsAppliEndDate = l_orderSpec.getAppliEndDate();
        try
        {
            l_srvRegiRegistService.validateAppliPeriod(l_strInstitutionCode, l_strSrvDiv,
                l_strBranchCode, l_strAccountCode, l_tsAppliStartDate, l_tsAppliEndDate,
                new Long(l_lngRegistId));//WEB3BaseException
        }
        catch(WEB3BaseException l_ex)
        {
            //1.7 <例外がスローされた場合>
            log.debug(getClass().getName() + STR_METHOD_NAME);

            throw new WEB3BaseException(
                 l_ex.getErrorInfo(),
                 this.getClass().getName() + STR_METHOD_NAME,
                 l_strAccountCode,
                 l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
