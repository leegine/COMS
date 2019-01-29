head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 個別顧客指定変更サービスImpl(WEB3AdminAccInfoCampaignIndiviChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
Revision History : 2007/2/1  モデルNo.180
Revision History : 2007/2/1  モデルNo.189
Revision History : 2007/3/2  松井(SCS)モデルNo.205
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon;
import webbroker3.accountinfo.WEB3AdminAccInfoCampaignSearchCondition;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (個別顧客指定変更サービスImpl)<BR>
 * 個別顧客指定変更サービス実装クラス<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviChangeServiceImpl
    implements WEB3AdminAccInfoCampaignIndiviChangeService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoCampaignIndiviChangeServiceImpl.class);
    
    /**
     * @@roseuid 45C08B520242
     */
    public WEB3AdminAccInfoCampaignIndiviChangeServiceImpl() 
    {
     
    }
    
    /**
     * 個別顧客指定変更処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン<BR>
     * 個別顧客指定入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン<BR>
     * 個別顧客指定確認リクエストの場合 <BR>
     * 　@－validate変更()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン<BR>
     * 個別顧客指定完了リクエストの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@roseuid 45B0709B00AC
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
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        //１）　@リクエストデータの型により、以下の通りメソッドをコールする。  
        // ○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン個別顧客指定入力リクエストの場合
        //　@－get入力画面()をコールする。  
        if (l_request instanceof WEB3AdminAccInfoCampaignIndiviInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminAccInfoCampaignIndiviInputRequest)l_request);
        }

        //○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン個別顧客指定確認リクエストの場合
        //　@－validate変更()をコールする。  
        else if (l_request instanceof WEB3AdminAccInfoCampaignIndiviConfirmRequest)
        {
            l_response =
                this.validateChange(
                    (WEB3AdminAccInfoCampaignIndiviConfirmRequest)l_request);
        }

        //○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン個別顧客指定完了リクエストの場合
        //　@－submit変更()をコールする。 
        else if (l_request instanceof WEB3AdminAccInfoCampaignIndiviCompleteRequest)
        {
            l_response =
                this.submitChange(
                    (WEB3AdminAccInfoCampaignIndiviCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 個別顧客指定変更入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「個別顧客指定変更get入力画面」参照。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報手数料割引キャンペーン<BR>
     * 個別顧客指定入力リクエスト<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviInputResponse
     * @@roseuid 45A7197300C6
     */
    protected WEB3AdminAccInfoCampaignIndiviInputResponse getInputScreen(
        WEB3AdminAccInfoCampaignIndiviInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCampaignIndiviInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoCampaignInfo l_accInfoCampaignInfo = null;
        //validate
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);
        
        //リクエストデータ.更新フラグ = '1'(変更)の場合
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            //getキャンペーン条件(String)
            //リクエストデータ.手数料割引キャンペーン条件ID
            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
                WEB3AdminAccInfoCampaignCommon.getInstance();
            
            l_accInfoCampaignInfo = l_accInfoCampaignCommon.getCampaignCondition(l_request.campaignId);
            
            //validate部店権限(部店コード : String)
            //部店コード：　@getキャンペーン条件.部店コード 
            l_administrator.validateBranchPermission(l_accInfoCampaignInfo.branchCode);
        }
        
        //レスポンスデータを生成する。
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response =
            (WEB3AdminAccInfoCampaignIndiviInputResponse)l_request.createResponse();
        
        //プロパティセット
        l_response.commissionCampaignInfo = l_accInfoCampaignInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate変更)<BR>
     * 個別顧客指定変更確認画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「個別顧客指定変更validate変更」参照。 <BR>
     *  ========================================================<BR>
     *   シーケンス図(「個別顧客指定変更validate変更」) : <BR>   
     *   get重複キャンペーン条件()で戻り値がnullでは無い場合、例外をスローする<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02726<BR>
     *  ========================================================<BR>
     * <BR>  
     *  ========================================================<BR>
     *   シーケンス図(「個別顧客指定変更validate変更」) : <BR>   
     *   is変更情報(変更後情報 : 手数料割引キャンペーン条件情報) が FALSE の場合、
     *   例外をスローする<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02723<BR>
     *   =======================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報手数料割引キャンペーン<BR>
     * 個別顧客指定確認リクエスト<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 45AC32D7013A
     */
    protected WEB3AdminAccInfoCampaignIndiviConfirmResponse validateChange(
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoCampaignIndiviConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        WEB3AccInfoCampaignInfo l_info = null;
        String l_strComparePeriod = null;
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        
        //validate()
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);
        
		String l_strUpdateFlag = l_request.updateFlag;

        //リクエストデータ.更新フラグ != '2'（削除）の場合
        if (!WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
			//validate部店権限(部店コード : String)
			//部店コード：　@手数料割引キャンペーン条件情報.部店コード
			l_administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);
        }
             
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition = 
            new WEB3AdminAccInfoCampaignSearchCondition();
        
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
            WEB3AdminAccInfoCampaignCommon.getInstance();
        
        //リクエストデータ.更新フラグ = '0'(登録)の場合
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag))
        {
            //validate入力対象期間チェック(手数料割引キャンペーン条件情報 ： WEB3AccInfoCampaignInfo, 更新処理フラグ ： String)
        	l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);        	
        	        	
            //set証券会社コード(String)
            l_campaignSearchCondition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);
            
            //set部店コード(String)
            l_campaignSearchCondition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
            
            //set顧客コード(String)
            l_campaignSearchCondition.setAccountCode(l_request.commissionCampaignInfo.accountCode);
            
            //set商品コード(String[])
            l_campaignSearchCondition.setItemCode(l_request.commissionCampaignInfo.itemCode);
            
			//set対象期間From(Date)
			if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
			{
				l_campaignSearchCondition.setTargetPeriodFrom(
					l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
			}
			else
			{
				l_campaignSearchCondition.setTargetPeriodFrom("");
			}

            //set登録タイプ(String[])                                            
            String registTypes[] = {l_request.commissionCampaignInfo.registType};
            l_campaignSearchCondition.setRegisterType(registTypes);              

            //get重複キャンペーン条件(キャンペーン検索条件)
            //重複検索条件： キャンペーン検索条件 
            l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_campaignSearchCondition);
            
            //get重複キャンペーン条件()で戻り値がnullでは無い場合、且つ
            //リクエストデータ.手数料割引キャンペーン条件情報.対象期間Fromがnullでは無い場合、例外をスローする。
            if (l_campaignInfos != null && l_request.commissionCampaignInfo.targetPeriodFrom != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02726,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "キャンペーン条件は既に登録済みです。");
            }
            
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            l_gentradeMainAccount =
                l_accountManager.getMainAccount(
                    l_request.commissionCampaignInfo.institutionCode, 
                    l_request.commissionCampaignInfo.branchCode, 
                    l_request.commissionCampaignInfo.accountCode);

        }
        
        //リクエストデータ.更新フラグ = '1'(変更) 若しくは '2'(削除)の場合
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            l_strComparePeriod = l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
        }
        
        //リクエストデータ.更新フラグ = '1'(変更)の場合
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            boolean l_blnIsChangeInfo = l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);
            
            if (!l_blnIsChangeInfo)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "情報に変更がない。");
            }
        }
                
        //リクエストデータ.更新フラグ = '2'(削除)の場合
        if (WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            //getキャンペーン条件(String)
            l_info = l_accInfoCampaignCommon.getCampaignCondition(l_request.commissionCampaignInfo.campaignId);

			//validate部店権限(部店コード : String)
			//部店コード：　@手数料割引キャンペーン条件情報.部店コード
			l_administrator.validateBranchPermission(l_info.branchCode);
        }
        
        //レスポンスデータを生成する。
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response =
            (WEB3AdminAccInfoCampaignIndiviConfirmResponse)l_request.createResponse();
        
        //プロパティセット
        l_response.alertFlag = l_strComparePeriod;
        l_response.commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag)
            || WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo.campaignId = null;
            l_response.commissionCampaignInfo.campaignName = null;
            l_response.commissionCampaignInfo.institutionCode = null;
            l_response.commissionCampaignInfo.branchCode = null;
            l_response.commissionCampaignInfo.accountCode = null;
            l_response.commissionCampaignInfo.itemCode = null;
            l_response.commissionCampaignInfo.targetPeriodFrom = null;
            l_response.commissionCampaignInfo.targetPeriodTo = null;
            l_response.commissionCampaignInfo.collectRate = null;
            l_response.commissionCampaignInfo.registType = null;
            l_response.commissionCampaignInfo.deleteFlag = null;
            l_response.commissionCampaignInfo.transactionDiv = null;
            l_response.commissionCampaignInfo.registrant = null;
            l_response.commissionCampaignInfo.registDate = null;
            l_response.commissionCampaignInfo.updateDate = null;
            l_response.commissionCampaignInfo.accopenPassPeriodMonth = null;
            l_response.commissionCampaignInfo.accopenPassPeriodDay = null;
            l_response.commissionCampaignInfo.traderCode = null;
            l_response.commissionCampaignInfo.accountOpenDiv = null;
            l_response.commissionCampaignInfo.accountOpenDateFrom = null;
            l_response.commissionCampaignInfo.accountOpenDateTo = null;
        }
        
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo.accountName = 
                l_gentradeMainAccount.getDisplayAccountName();
        }
        else if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo.accountName = null;
        }
        
        if (WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            l_response.commissionCampaignInfo = l_info;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit変更)<BR>
     * 個別顧客指定変更完了画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「個別顧客指定変更submit変更」参照。 <BR>
     *  ========================================================<BR>
     *   シーケンス図(「個別顧客指定変更submit変更」) : <BR>   
     *   get重複キャンペーン条件()で戻り値がnullでは無い場合、例外をスローする<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02726<BR>
     *  ========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *   シーケンス図(「個別顧客指定変更submit変更」) : <BR>   
     *   is変更情報(変更後情報 : 手数料割引キャンペーン条件情報) が FALSE の場合、
     *   例外をスローする<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02723<BR>
     *   =======================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報手数料割引キャンペーン<BR>
     * 個別顧客指定完了リクエスト<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 45AED5AC02AB
     */
    protected WEB3AdminAccInfoCampaignIndiviCompleteResponse submitChange(
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoCampaignIndiviCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
            WEB3AdminAccInfoCampaignCommon.getInstance();
                
        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        String l_strTargetPeriod = null;
        
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition = 
            new WEB3AdminAccInfoCampaignSearchCondition();
        
        //validate()
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);
        
        //validate部店権限(部店コード : String)
        //部店コード：　@手数料割引キャンペーン条件情報.部店コード
        l_administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);
        
        //validate取引パスワード(パスワード : String)
        l_administrator.validateTradingPassword(l_request.password);
        
        //リクエストデータ.更新フラグ = '1'(変更) 若しくは '2'(削除)の場合
        String l_strUpdateFlag = l_request.updateFlag;
        
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            //validate対象期間(手数料割引キャンペーン条件情報)
            l_strTargetPeriod = 
                l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
        }
        
        //get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        //リクエストデータ.更新フラグ = '0'(登録)の場合
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_strUpdateFlag))
        {
        	//validate入力対象期間チェック(手数料割引キャンペーン条件情報 ： WEB3AccInfoCampaignInfo, 更新処理フラグ ： String)
        	l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);        	
        	
        	//set証券会社コード(String)
            l_campaignSearchCondition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);
            
            //set部店コード(String)
            l_campaignSearchCondition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
            
            //set顧客コード(String)
            l_campaignSearchCondition.setAccountCode(l_request.commissionCampaignInfo.accountCode);
            
            //set商品コード(String[])
            l_campaignSearchCondition.setItemCode(l_request.commissionCampaignInfo.itemCode);
            
			//set対象期間From(Date)
			if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
			{
				l_campaignSearchCondition.setTargetPeriodFrom(
					l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
			}
			else
			{
				l_campaignSearchCondition.setTargetPeriodFrom("");
			}
			
            //set登録タイプ(String[])                                            
            String registTypes[] = {l_request.commissionCampaignInfo.registType};
            l_campaignSearchCondition.setRegisterType(registTypes);              
            
            //get重複キャンペーン条件(キャンペーン検索条件)
            //重複検索条件： キャンペーン検索条件 
            l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_campaignSearchCondition);
            
            //get重複キャンペーン条件()で戻り値がnullでは無い場合、且つ
            //リクエストデータ.手数料割引キャンペーン条件情報.対象期間Fromがnullでは無い場合、例外をスローする。
            if (l_campaignInfos != null && l_request.commissionCampaignInfo.targetPeriodFrom != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02726,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "キャンペーン条件は既に登録済みです。");
            }
            
            //insertキャンペーン条件(手数料割引キャンペーン条件情報, String)
            //登録情報： リクエスト.手数料割引キャンペーン条件情報オブジェクト 
            //更新者コード： get管理者コード()の戻り値
            l_accInfoCampaignCommon.insertCampaignCondition(
                l_request.commissionCampaignInfo, l_strAdministratorCode);
        }
        
        //リクエストデータ.更新フラグ = '1'(変更)の場合
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_strUpdateFlag))
        {
            //is変更情報(手数料割引キャンペーン条件情報)
            boolean l_blnIsChangeInfo = 
                l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);
            
            //is変更情報(変更後情報 : 手数料割引キャンペーン条件情報) が FALSE の場合、例外をスローする。
            if (!l_blnIsChangeInfo)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "情報に変更がありません。");
            }
            
            //updateキャンペーン条件(手数料割引キャンペーン条件情報, String)
            l_accInfoCampaignCommon.updateCampaignCondition(
                l_request.commissionCampaignInfo, l_strAdministratorCode);
        }
        
        //リクエストデータ.更新フラグが ' 2 '(削除)の場合
        if (WEB3AccInfoUpdateFlagDef.DELETE.equals(l_strUpdateFlag))
        {
            //deleteキャンペーン条件(String, String)
            //手数料割引キャンペーン条件ID： 
            //         リクエスト.手数料割引キャンペーン条件情報.手数料割引キャンペーン条件ID 
            //更新者コード： get管理者コード()の戻り値
            l_accInfoCampaignCommon.deleteCampaignCondition(
                l_request.commissionCampaignInfo.campaignId, 
                l_strAdministratorCode); 
        }
        
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response =
            (WEB3AdminAccInfoCampaignIndiviCompleteResponse)l_request.createResponse();
        
        //プロパティセット
        l_response.alertFlag = l_strTargetPeriod;

        log.exiting(STR_METHOD_NAME);
        return l_response;  
    }
}
@
