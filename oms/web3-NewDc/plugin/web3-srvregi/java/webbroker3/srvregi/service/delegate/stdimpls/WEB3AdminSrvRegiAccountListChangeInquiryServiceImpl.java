head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客一覧変更照会サービスImpl(WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
Revesion History : 2007/06/19 崔遠鵬(中訊) 仕様変更モデルNo.249 No.264
Revesion History : 2007/06/21 崔遠鵬(中訊) 仕様変更モデルNo.270
Revesion History : 2007/07/24 金傑(中訊) 仕様変更モデルNo.292
Revesion History : 2007/07/30 金傑(中訊) 仕様変更モデルNo.298
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiKeyItemDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountListChangeInquiryService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者顧客一覧変更照会サービスImpl)<BR>
 * サービス利用管理者顧客一覧変更照会サービス実装クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountListChangeInquiryService 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl.class);
    
    /**
     * @@roseuid 416F392900AB
     */
    public WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl() 
    {
     
    }
    
    /**
     * サービス利用管理者顧客一覧変更照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客一覧照会」<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）顧客一覧照会」): <BR>
     *         1.6.1.1.:getLotDiv( )<BR>
     *        <補足入力チェック>サービス利用ソートキーのチェック<BR>
     *        (1)get抽選設定( )の戻り値が"無"の場合<BR>
     *      　@①@リクエストデータ.サービス利用ソートキーの要素数分、以下を繰り返す。<BR>
     *      　@　@リクエストデータ.サービス利用ソートキー.キー項目が以下の値以外の場合、<BR>
     *          例外をスローする。<BR>
     *      　@　@「部店」「顧客」「申込属性区分」「申込抽選区分」「適用開始日」「適用終了日」<BR>
     *      　@　@「登録区分」「利用料金」「最終更新日」「最終更新者」<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     *      　@②リクエストデータ.申込抽選区分が以下の値以外の場合、例外をスローする。<BR>
     *      　@　@「試用」「当選／本申込」「取消」「全て」「無料対象」「申込不可」「全て（属性用）」<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00995<BR>
     * <BR>
     *       (2)get抽選設定( )の戻り値が"有"の場合<BR>
     *      　@①@リクエストデータ.サービス利用ソートキーの要素数分、以下を繰り返す。<BR>
     *      　@　@リクエストデータ.サービス利用ソートキー.キー項目が以下の値以外の場合、<BR>
     *          例外をスローする。<BR>
     *      　@　@「部店」「顧客」「申込抽選区分」「申込日」「適用開始日」「適用終了日」<BR>
     *      　@　@「登録区分」「利用料金」「最終更新日」「最終更新者」<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     *      　@②リクエストデータ.申込抽選区分が以下の値以外の場合、例外をスローする。<BR>
     *      　@　@「申込」「当選／本申込」「落選」「取消」「全て」<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00997<BR>
     * ==========================================================<BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）顧客一覧照会」): <BR>
     *         1.10: <ページング制御><BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01080<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F51DF703C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME );
        
        WEB3AdminSrvRegiCustomerReferenceRequest l_customerReferenceRequest = null;

        
        if (l_request instanceof WEB3AdminSrvRegiCustomerReferenceRequest)
        {
            l_customerReferenceRequest = (WEB3AdminSrvRegiCustomerReferenceRequest)l_request;
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正。";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);            
        }

        
        //1.1 validate( )
        l_customerReferenceRequest.validate();
        
        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //障害対応 NO_1990
        //1.4 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false);
        
        //1.5 validate部店権限(String[])
        l_admin.validateBranchPermission(l_customerReferenceRequest.branchCode);
        
        //1.6 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode(); 
        
        //1.7 getサービスマスター
        WEB3SrvRegiServiceInfoManagement l_serviceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_serviceMaster = l_serviceInfoManagement.getSrvMaster(
            l_strInstitutionCode,
            l_customerReferenceRequest.serviceDiv,
            false);;
            
        WEB3SrvRegiApplicationRequiredService l_reqSrv = l_serviceMaster.getAppliRequiredSrv(false);
        
        if (l_reqSrv == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        String l_strLotDiv = l_reqSrv.getLotDiv();
        
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            //sortkeys' check
            if (l_customerReferenceRequest.sortKeys != null)
            {
                int l_intKeyLenth = l_customerReferenceRequest.sortKeys.length;
                final String[] l_strValidKeys = new String[]
                {
                    WEB3SrvRegiKeyItemDef.BRANCH_CODE,
                    WEB3SrvRegiKeyItemDef.ACCOUNT_CODE,
                    WEB3SrvRegiKeyItemDef.APPLI_ATTRIBUTE,
                    WEB3SrvRegiKeyItemDef.APPLI_LOT_DIV,
                    WEB3SrvRegiKeyItemDef.APPLI_START_DATE,
                    WEB3SrvRegiKeyItemDef.APPLI_END_DATE,
                    WEB3SrvRegiKeyItemDef.PAYMENT_DIV,
                    WEB3SrvRegiKeyItemDef.USE_AMT,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATER
                };
                for (int i = 0; i < l_intKeyLenth; i++)
                {
                    boolean l_blnValidate = false;
                    for (int j = 0; j < l_strValidKeys.length; j++)
                    {
                        if (l_strValidKeys[j].equals(l_customerReferenceRequest.sortKeys[i].keyItem))
                        {
                            l_blnValidate = true;
                            break;
                        }
                    }
                    if (!l_blnValidate)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            "リクエストデータ.サービス利用ソートキー.キー項目が「部店」「顧客」「申込属性区分」「申込抽選区分」「適用開始日」「適用終了日」「登録区分」「利用料金」「最終更新日」"
                            + "「最終更新者」の値以外の場合、例外をスローする。");
                    }
                }
            }
            
            //applilotdiv' check
            if (!WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(l_customerReferenceRequest.applyLotteryDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00995, 
                    this.getClass().getName() + STR_METHOD_NAME);           
            }
        }
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //sortkeys' check
            if (l_customerReferenceRequest.sortKeys != null)
            {
                int l_intKeyLenth = l_customerReferenceRequest.sortKeys.length;
                final String[] l_strValidKeys = new String[]
                {
                    WEB3SrvRegiKeyItemDef.BRANCH_CODE,
                    WEB3SrvRegiKeyItemDef.ACCOUNT_CODE,
                    WEB3SrvRegiKeyItemDef.APPLI_LOT_DIV,
                    WEB3SrvRegiKeyItemDef.APPLI_DATE,
                    WEB3SrvRegiKeyItemDef.APPLI_START_DATE,
                    WEB3SrvRegiKeyItemDef.APPLI_END_DATE,
                    WEB3SrvRegiKeyItemDef.PAYMENT_DIV,
                    WEB3SrvRegiKeyItemDef.USE_AMT,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATED_TIMESTAMP,
                    WEB3SrvRegiKeyItemDef.LAST_UPDATER
                };
                for (int i = 0; i < l_intKeyLenth; i++)
                {
                    boolean l_blnValidate = false;
                    for (int j = 0; j < l_strValidKeys.length; j++)
                    {
                        if (l_strValidKeys[j].equals(l_customerReferenceRequest.sortKeys[i].keyItem))
                        {
                            l_blnValidate = true;
                            break;
                        }
                    }
                    if (!l_blnValidate)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                            this.getClass().getName() + STR_METHOD_NAME);           
                    }
                }
            }
            
            //applilotdiv' check
            if (!WEB3SrvRegiAppliLotDivDef.APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANCEL.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(l_customerReferenceRequest.applyLotteryDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00995, 
                    this.getClass().getName() + STR_METHOD_NAME);           
            }
        }
        
        String l_strAccountCodeInDb = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //1.8 ＜リクエストデータ.顧客コード != nullの場合＞
        if (l_customerReferenceRequest.accountCode != null)
        {
        	//障害対応 NO_2052
			int l_cntAccount = 0;
			
            for (int i = 0; i < l_customerReferenceRequest.branchCode.length; i++)
            {
                WEB3GentradeMainAccount l_mainAccount = null;
                try
                {
                    //1.8.1 get顧客(String, String, String)
                    l_mainAccount = l_accountManager.getMainAccount(l_strInstitutionCode, l_customerReferenceRequest.branchCode[i], l_customerReferenceRequest.accountCode);
        
                    //1.8.2 getAccountCode( )
                    l_strAccountCodeInDb = l_mainAccount.getAccountCode();
                    
                    //顧客マスターに存在する数をcount
                    l_cntAccount++;
                    
					log.debug("【顧客コード存在チェックOK】 : " + l_customerReferenceRequest.accountCode);
                    break;
                }
                catch (WEB3BaseException l_e)
                {
                    continue;
                }
            }
            
            //入力された顧客コードが顧客マスターに存在しない場合
            if(l_cntAccount == 0){
            	log.debug("【顧客コード存在チェックNG】 : " + l_customerReferenceRequest.accountCode);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01987,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"顧客マスタテーブルで顧客オブジェクトを取得できない場合");
            }
            
        }

        //1.9 getサービス申込登録一覧
        WEB3SrvRegiRegistService l_appliRegiService = 
            (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);
        
        Timestamp l_tsTrialStartFrom = null;
        Timestamp l_tsTrialStartTo = null;
        Timestamp l_tsApplyDateFrom = null;
        Timestamp l_tsApplyDateTo = null;
        if (l_customerReferenceRequest.trialStartFrom != null)
        {
            l_tsTrialStartFrom = new Timestamp(l_customerReferenceRequest.trialStartFrom.getTime());
        }
        if (l_customerReferenceRequest.trialStartTo != null)
        {
            l_tsTrialStartTo = new Timestamp(l_customerReferenceRequest.trialStartTo.getTime());
        }
        if (l_customerReferenceRequest.applyDateFrom != null)
        {
            l_tsApplyDateFrom = new Timestamp(l_customerReferenceRequest.applyDateFrom.getTime());
        }
        if (l_customerReferenceRequest.applyDateTo != null)
        {
            l_tsApplyDateTo = new Timestamp(l_customerReferenceRequest.applyDateTo.getTime());        
        }

        SrvRegiApplicationParams[] l_appParams = null;
        SrvAppliAttributeParams[] l_srvAppliAttributeParams = null;

        int l_intRowCount = 0;
        if (!WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
            && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
            && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(l_customerReferenceRequest.applyLotteryDiv))
        {
            //getサービス申込登録一覧(String, String[], String, String, String, String, Timestamp, Timestamp, Timestamp,
            // Timestamp, サービス利用ソートキー[ ])
            l_appParams = l_appliRegiService.getServiceRegistLists(
                l_strInstitutionCode,
                l_customerReferenceRequest.branchCode,
                l_customerReferenceRequest.serviceDiv,
                l_strAccountCodeInDb,
                l_customerReferenceRequest.registDiv,
                l_customerReferenceRequest.applyLotteryDiv,
                l_tsTrialStartFrom,
                l_tsTrialStartTo,
                l_tsApplyDateFrom,
                l_tsApplyDateTo,
                l_customerReferenceRequest.sortKeys);
            l_intRowCount = l_appParams.length;
        }
        else
        {
            //getサービス申込属性一覧(String, String[], String, String, String, Timestamp, サービス利用ソートキー[ ])
            l_srvAppliAttributeParams = l_appliRegiService.getServiceAttributeLists(
                l_strInstitutionCode,
                l_customerReferenceRequest.branchCode,
                l_customerReferenceRequest.serviceDiv,
                l_strAccountCodeInDb,
                l_customerReferenceRequest.applyLotteryDiv,
                l_tsTrialStartFrom,
                l_customerReferenceRequest.sortKeys);
            l_intRowCount = l_srvAppliAttributeParams.length;
        }


		//障害対応 顧客存在チェックNG対応
        //1.10 <繰り返し処理>
		ArrayList l_customerReferenceList = new ArrayList();
		
        for (int i = 0; i < l_intRowCount; i++)
        {
            SrvRegiApplicationParams l_params = null;
            SrvAppliAttributeParams l_srvAppliAttrParams = null;
            
			WEB3GentradeMainAccount l_mainAccount = null;
            
            try
            {
				//1.10.1 get顧客(String, String, String)
                if (!WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(
                        l_customerReferenceRequest.applyLotteryDiv))
                {
                    l_params = l_appParams[i];
                    //（リクエスト.申込抽選区分が '7', '8', '9' ではない場合）
                    l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_strInstitutionCode,
                            l_params.getBranchCode(),
                            l_params.getAccountCode());
                }
                else
                {
                    l_srvAppliAttrParams = l_srvAppliAttributeParams[i];
                    //（リクエスト.申込抽選区分が '7', '8', '9' の場合）
                    l_mainAccount = 
                        l_accountManager.getMainAccount(
                            l_strInstitutionCode,
                            l_srvAppliAttrParams.getBranchCode(),
                            l_srvAppliAttrParams.getAccountCode());
                }
            }
            //顧客存在チェックNG対応
            catch(WEB3BaseException ex)
            {
            	//次の明細へスキップ
            	continue;
            }

            //1.10.2 サービス利用顧客一覧変更照会明細一覧行( )
            WEB3AdminSrvRegiCustomerReferenceGroup l_group = new WEB3AdminSrvRegiCustomerReferenceGroup();

            //1.10.3 <プロパティ・セット>
            if (!WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(l_customerReferenceRequest.applyLotteryDiv)
                && !WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(l_customerReferenceRequest.applyLotteryDiv))
            {
                //(申込抽選区分=リクエストデータ.申込抽選区分 != '7' ～ '9' の場合）
                //○申込登録ID=サービス申込登録オブジェクト.get申込登録ID( )
                l_group.applyRegId = WEB3StringTypeUtility.formatNumber(l_params.getRegistId());
                
                //○抽選区分=申込要サービスオブジェクト.get抽選設定( ) 
                l_group.lotteryDiv = l_strLotDiv;
                
                //○部店コード=サービス申込登録オブジェクト.get部店コード( )
                l_group.branchCode = l_params.getBranchCode();
                
                //○顧客コード=取得した顧客オブジェクト.get表示顧客コード( )
                l_group.accountCode = l_mainAccount.getDisplayAccountCode();
                
                //○申込抽選区分=(*1)
                if (WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_params.getCancelDiv()))
                {
                    //(*1-1) サービス申込登録オブジェクト.get取消区分( )="通常"の場合
                    if (WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_params.getAppliLotDiv()))
                    {
                        //障害対応 NO_2082
                        //(*1-1-1) サービス申込登録オブジェクト.get申込抽選区分( )="自動当選"の場合 
                        if (WEB3DateUtility.compareToDay(l_params.getCancelLimitDate(),
                            GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0)
                        { 
                            //(*1-1-1-1) サービス申込登録オブジェクト.get自動当選取消期限日( )≧現在日付の場合"申込"をセットする。　@　@            
                            l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.APPLI;
                        }
                        else
                        {
                            //(*1-1-1-2) サービス申込登録オブジェクト.get自動当選取消期限日( )＜現在日付の場合"当選／本申込"をセットする。 　@　@            
                            l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI;                        
                        }
                    }
                    else
                    {
                        //(*1-1-2) サービス申込登録オブジェクト.get申込抽選区分( )="自動当選"以外の場合 
                        //　@　@            サービス申込登録オブジェクト.get申込抽選区分( )をセットする。 
                        l_group.applyLotteryDiv = l_params.getAppliLotDiv();
                    }
                }
                else if (WEB3SrvRegiCancelDivDef.CANCEL.equals(l_params.getCancelDiv()))
                {
                    //(*1-2) サービス申込登録オブジェクト.get取消区分( )="取消"の場合"取消"をセットする。
                    l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.CANCEL;
                }
                
                //○申込日=サービス申込登録オブジェクト.get申込日( )
                l_group.applyDate = l_params.getAppliDate();
                
                //○適用開始日=サービス申込登録オブジェクト.get適用開始日( )
                l_group.trialStartDate = l_params.getAppliStartDate();
                
                //○適用終了日=サービス申込登録オブジェクト.get適用終了日( )
                l_group.trialEndDate = l_params.getAppliEndDate();
                 
                //○登録区分=サービス申込登録オブジェクト.get登録区分( )
                l_group.registDiv = l_params.getPaymentDiv();
                
                //○利用料金=サービス申込登録オブジェクト.get利用料金( )
                if (!l_params.getUseAmtIsNull())
                {
                    l_group.chargeAmt = WEB3StringTypeUtility.formatNumber(l_params.getUseAmt());
                } 
                 
                //○最終更新日=サービス申込登録オブジェクト.get最終更新日( )
                l_group.lastUpdateTime = l_params.getLastUpdatedTimestamp();
                 
                //○最終更新者=サービス申込登録オブジェクト.get最終更新者( )
                l_group.lastUpdater = l_params.getLastUpdater();
            }
            else
            {
                //(申込抽選区分=リクエストデータ.申込抽選区分 == '7' ～ '9' の場合）
                //○部店コード=サービス申込属性オブジェクト.get部店コード( )
                l_group.branchCode = l_srvAppliAttrParams.getBranchCode();

                //○顧客コード=取得した顧客オブジェクト.get表示顧客コード( )
                l_group.accountCode = l_mainAccount.getDisplayAccountCode();

                //○申込抽選区分=(*1)
                if (WEB3AppliAttributeDef.FREE_OBJECT.equals(l_srvAppliAttrParams.getAppliAttribute()))
                {
                    //サービス申込属性オブジェクト.get申込属性区分( )="無料対象"の場合
                    //'7' をセットする。
                    l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.FREE_OBJECT;
                }
                else if(WEB3AppliAttributeDef.CANNOT_APPLI.equals(l_srvAppliAttrParams.getAppliAttribute()))
                {
                    //サービス申込属性オブジェクト.get申込属性区分( )="申込不可"の場合
                    //'8' をセットする。
                    l_group.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI;
                }

                //○適用開始日=サービス申込属性オブジェクト.get適用期間From( ) 
                l_group.trialStartDate = l_srvAppliAttrParams.getAppliStartDate();

                //○適用終了日=サービス申込属性オブジェクト.get適用期間To( )
                l_group.trialEndDate = l_srvAppliAttrParams.getAppliEndDate();

                //○処理区分=サービス申込属性オブジェクト.get処理区分( )
                l_group.transactionDiv = l_srvAppliAttrParams.getProcDiv();

                //○最終更新日=サービス申込属性オブジェクト.get最終更新日( )
                l_group.lastUpdateTime = l_srvAppliAttrParams.getLastUpdatedTimestamp();

                //○最終更新者=サービス申込属性オブジェクト.get最終更新者( )
                l_group.lastUpdater = l_srvAppliAttrParams.getLastUpdater();
            }
            
            //明細をリストへ
			l_customerReferenceList.add(l_group);
        }
        
        //リスト数分の配列を作成
		WEB3AdminSrvRegiCustomerReferenceGroup[] l_customerReferenceGroups = new 
			WEB3AdminSrvRegiCustomerReferenceGroup[l_customerReferenceList.size()];
		
		//リストから配列へ変換
		l_customerReferenceList.toArray(l_customerReferenceGroups);
        
        //1.11 createレスポンス( )
        WEB3AdminSrvRegiCustomerReferenceResponse l_response = 
            (WEB3AdminSrvRegiCustomerReferenceResponse)l_customerReferenceRequest.createResponse();
        
        //1.12 <ページング制御>
        //１）レスポンスの以下の項目を設定する。 
        //○レスポンス.総ページ数＝顧客一覧変更照会明細の要素数÷リクエスト.ページ内表示行数 
        //　@　@　@        ※余りが出る場合は、＋１した値を設定。 
        //　@　@　@        ※顧客一覧変更照会明細の要素数＝0(表示対象データなし)の場合、0をセット。
        int l_intPages = l_customerReferenceGroups.length / Integer.parseInt(l_customerReferenceRequest.pageSize);
        if (l_customerReferenceGroups.length % Integer.parseInt(l_customerReferenceRequest.pageSize) != 0)
        { 
            l_intPages++;
        }
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_intPages);
        
        //○レスポンス.総レコード数:　@顧客一覧変更照会明細の要素数 
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_customerReferenceGroups.length);
        
        //○レスポンス.表示ページ番号(表示が何ページ目にあたるか): 
        //　@　@　@　@        以下の条件に合致するのであれば、リクエスト.要求ページ番号。 
        //[顧客一覧変更照会明細の要素数 > (リクエスト.ページ内表示行数×(リクエスト.要求ページ番号-1) )] 
        //　@　@　@　@        上記以外の場合は、レスポンス.総ページ数をそのまま設定。 
        //　@　@　@        ※検索結果がPR層からの要求ページ番号に満たない場合は、最終ページに該当するデータをレスポンスに設定する。
        int l_intRequestPageIndex = Integer.parseInt(l_customerReferenceRequest.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_customerReferenceRequest.pageSize);
        
        if (l_customerReferenceGroups.length > (l_intRequestPageIndex - 1) * l_intRequestPageSize)
        {
            l_response.pageIndex = l_customerReferenceRequest.pageIndex;
        }
        else
        {
            l_response.pageIndex = l_response.totalPages;
        }
         
        //２）設定後、レスポンス.総ページ数＝0 の場合は、レスポンス.顧客一覧変更照会明細行 
        //(サービス利用顧客一覧変更照会明細一覧行[ ])にnullをセットし例外をスローする。 
        if ("0".equals(l_response.totalPages))
        {
            l_response.customerList = null;
            String l_strErrorMessage = "レスポンス.総ページ数＝0エラー.";
            log.debug(l_strErrorMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01080, 
                this.getClass().getName() + STR_METHOD_NAME);           
        }

        //【確定した顧客一覧変更照会明細のうち、レスポンスに設定する明細を決める。】 
        //１)　@(リクエスト.ページ内表示行数×(レスポンス.表示ページ番号-1)数分、確定した顧客一覧変更照会明細の要素を 
        //スキップする。 
        //２)　@上記で決定した顧客一覧変更照会明細の要素番号～ 
        //(顧客一覧変更照会明細の要素番号＋リクエスト.ページ内表示行数) 
        //までに該当する顧客一覧変更照会明細を、レスポンスデータ.顧客一覧変更照会明細行セットする。
        
        //save not displayed records
        WEB3AdminSrvRegiCustomerReferenceGroup[] l_validCustomerReferenceGroups = null;
        
        int l_intResponsePageIndex = Integer.parseInt(l_response.pageIndex);
        
        //save records count that alredy displayed.
        int l_intRolledCount = l_intRequestPageSize * (l_intResponsePageIndex - 1);
        
        int l_intValidRecordCount = l_customerReferenceGroups.length - l_intRolledCount;
        if (l_intValidRecordCount > l_intRequestPageSize)
        {
            l_intValidRecordCount = l_intRequestPageSize;
        }
        
        l_validCustomerReferenceGroups = new WEB3AdminSrvRegiCustomerReferenceGroup[l_intValidRecordCount];
        
        //copy the record that have not displayed.
        for (int i = 0; i < l_intValidRecordCount; i++)
        { 
            l_validCustomerReferenceGroups[i] = 
                l_customerReferenceGroups[l_intRolledCount + i];
        }
        
        //【レスポンスの設定】 
        //○レスポンス.顧客一覧変更照会明細行＝ページング制御を行って確定させた顧客一覧変更照会明細の配列
        l_response.customerList = l_validCustomerReferenceGroups;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
