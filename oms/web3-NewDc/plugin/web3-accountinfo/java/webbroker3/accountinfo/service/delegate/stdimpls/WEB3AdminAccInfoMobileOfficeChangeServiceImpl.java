head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更サービスImpl(WEB3AdminAccInfoMobileOfficeChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
Revesion History : 2006/02/23 呉艶飛 (中訊) モデルNo.086
Revesion History : 2006/03/20 呉艶飛 (中訊) モデルNo.098  
Revesion History : 2006/10/9  齊珂   (中訊) モデルNo.124    
Revesion History : 2006/10/30 齊珂   (中訊) モデルNo.139
Revesion History : 2006/11/22 周捷  (中訊) ＤＢ更新仕様No.034 実装の問題No.004
Revesion History : 2006/11/28 周捷  (中訊) モデルNo.146 ＤＢ更新仕様No.039
Revesion History : 2007/01/18 何文敏 (中訊) モデルNo.160,No.161,ＤＢ更新仕様No.042  
Revesion History : 2007/02/10 謝旋 (中訊) 仕様変更モデル188                
Revesion History : 2009/02/12 SCS大嶋 仕様変更モデル254                
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMaster;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.WEB3AccInfoOccupationChangeRegistVoucherCreated;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistPK;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountInfoAcceptStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3OccupationcodeUpdateDef;
import webbroker3.common.define.WEB3RealnameUpdateDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstPK;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報携帯番号・勤務先情報変更サービスImpl)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更サービス実装クラス<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMobileOfficeChangeService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeChangeServiceImpl.class);

    
    /**
     * @@roseuid 418F3A05003E
     */
    public WEB3AdminAccInfoMobileOfficeChangeServiceImpl() 
    {
     
    }
    
    /**
     * 携帯番号・勤務先情報変更申込処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報<BR>
     * 変更入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。<BR> 
     * ○ 引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報<BR>
     * 変更確認リクエストの場合 <BR>
     * 　@－validate変更()をコールする。<BR> 
     * ○ 引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報<BR>
     * 変更完了リクエストの場合 <BR>
     * 　@－submit変更()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4148FC3000FD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        //引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更入力リクエストの場合
        if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistInputRequest)
        {
            
            l_response = this.getInputScreen((WEB3AdminAccInfoMobileOfficeRegistInputRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更確認リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistConfirmRequest)
        {
            
            l_response = this.validateChange((WEB3AdminAccInfoMobileOfficeRegistConfirmRequest) l_request);
        }
        
        //引数のリクエストデータが、管理者お客様情報携帯番号・勤務先情報変更完了リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoMobileOfficeRegistCompleteRequest)
        {
            
            l_response = this.submitChange((WEB3AdminAccInfoMobileOfficeRegistCompleteRequest) l_request);
        }
        else
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
 
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 携帯番号・勤務先情報変更入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（携帯番号・勤務先情報変更）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更入力リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse
     * @@roseuid 4148FC30011D
     */
    protected WEB3AdminAccInfoMobileOfficeRegistInputResponse getInputScreen(WEB3AdminAccInfoMobileOfficeRegistInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.4) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);     
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount = 
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);        
        
        //1.6) メッセージ validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.7) get携帯番号・勤務先情報変更申込(顧客)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist = 
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);    

        //1.10) お客様情報携帯番号・勤務先情報変更申込入力レスポンス(l_request : WEB3GenRequest)
        WEB3AdminAccInfoMobileOfficeRegistInputResponse l_response = 
            (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
        
        //1.8) 変更申込情報がある場合（get携帯番号・勤務先情報変更申込() != null）、変更後情報を生成する   
        if(l_accInfoMobileOfficeInfoRegist != null)
        {    
            
            //1.8.1) getDataSourceObject( )
            MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow = 
                (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();
                
            //1.8.2) get申込状況区分( )
             String l_strAccInfoRegistStateDiv = 
                 l_accInfoMobileOfficeInfoRegist.getRegistStateDiv();
                 
            //1.8.3) 携帯番号・勤務先情報( )
            WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo =
                new WEB3AccInfoMobileOfficeInfo();    
            
            //1.8.4) プロパティセット                
            //携帯番号・勤務先情報.携帯番号 = 変更申込情報※.連絡先電話番号（携帯）
            l_accInfoMobileOfficeInfo.mobileTelephone = l_mobileOfficeInfoRegistRow.getMobile();
            
            //携帯番号・勤務先情報.勤務先名称 = 変更申込情報※.勤務先名称
            l_accInfoMobileOfficeInfo.officeName = l_mobileOfficeInfoRegistRow.getOffice();
            
            //携帯番号・勤務先情報.勤務先郵便番号 = 変更申込情報※.勤務先郵便番号
            l_accInfoMobileOfficeInfo.officeZipCode = l_mobileOfficeInfoRegistRow.getOfficeZipCode();
            
            //携帯番号・勤務先情報.勤務先住所 = 変更申込情報※.勤務先住所
            l_accInfoMobileOfficeInfo.officeAdress = l_mobileOfficeInfoRegistRow.getOfficeAddress();
            
            //携帯番号・勤務先情報.勤務先電話番号 = 変更申込情報※.勤務先電話番号
            l_accInfoMobileOfficeInfo.officeTelephone = l_mobileOfficeInfoRegistRow.getOfficeTelephone();
            
            //携帯番号・勤務先情報.役職名 = 変更申込情報※.役職
            l_accInfoMobileOfficeInfo.position = l_mobileOfficeInfoRegistRow.getPost();   
            
            //携帯番号・勤務先情報.連絡先優先順位 1位 = 変更申込情報※.連絡先優先順位 1位
            l_accInfoMobileOfficeInfo.contactPriority1 = l_mobileOfficeInfoRegistRow.getContactPriority1();
            
            //携帯番号・勤務先情報.連絡先優先順位 2位 = 変更申込情報※.連絡先優先順位 2位
            l_accInfoMobileOfficeInfo.contactPriority2 = l_mobileOfficeInfoRegistRow.getContactPriority2();
            
            //携帯番号・勤務先情報.連絡先優先順位 3位 = 変更申込情報※.連絡先優先順位 3位
            l_accInfoMobileOfficeInfo.contactPriority3 = l_mobileOfficeInfoRegistRow.getContactPriority3();
            
            //携帯番号・勤務先情報.正式名称１ = 変更申込情報※.正式名称１  
            l_accInfoMobileOfficeInfo.accountRealName1 = l_mobileOfficeInfoRegistRow.getRealName1();
            
            //携帯番号・勤務先情報.正式名称２ = 変更申込情報※.正式名称２ 
            l_accInfoMobileOfficeInfo.accountRealName2 = l_mobileOfficeInfoRegistRow.getRealName2();
            
            //携帯番号・勤務先情報.職業区分 = 変更申込情報※.職業
            l_accInfoMobileOfficeInfo.occupationDiv = l_mobileOfficeInfoRegistRow.getOccupationDiv();
            
            //携帯番号・勤務先情報.所屬 = 変更申込情報※.所屬
            l_accInfoMobileOfficeInfo.department = l_mobileOfficeInfoRegistRow.getDepartment();
            
            //携帯番号・勤務先情報.国籍 = 変更申込情報※.国籍  
            l_accInfoMobileOfficeInfo.nationality = l_mobileOfficeInfoRegistRow.getNationality();
            
            //携帯番号・勤務先情報.国籍その他 = 変更申込情報※.国籍その他 
            l_accInfoMobileOfficeInfo.nationalityOther = l_mobileOfficeInfoRegistRow.getNationalityOther();
            
            //携帯番号・勤務先情報.代表者名（漢字）姓 = 変更申込情報※.代表者名（漢字）姓
            l_accInfoMobileOfficeInfo.representFamilyName = l_mobileOfficeInfoRegistRow.getRepresentFamilyName();
            
            //携帯番号・勤務先情報.代表者名（漢字）名 = 変更申込情報※.代表者名（漢字）名
            l_accInfoMobileOfficeInfo.representName = l_mobileOfficeInfoRegistRow.getRepresentGivenName();
            
            //携帯番号・勤務先情報.代表者名（カナ）姓 = 変更申込情報※.代表者名（カナ）姓
            l_accInfoMobileOfficeInfo.representFamilyNameKana = l_mobileOfficeInfoRegistRow.getRepresentFamilyNameAlt1();
            
            //携帯番号・勤務先情報.代表者名（カナ）名 = 変更申込情報※.代表者名（カナ）名  
            l_accInfoMobileOfficeInfo.representNameKana = l_mobileOfficeInfoRegistRow.getRepresentGivenNameAlt1();
            
            //携帯番号・勤務先情報.代表者権 = 変更申込情報※.代表者権
            l_accInfoMobileOfficeInfo.representPower = l_mobileOfficeInfoRegistRow.getRepresentPower();
            
            //携帯番号・勤務先情報.取引責任者名（漢字）姓 = 変更申込情報※.取引先責任者名（漢字）姓
            l_accInfoMobileOfficeInfo.directorFamilyName = l_mobileOfficeInfoRegistRow.getDirectorFamilyName();
            
            //携帯番号・勤務先情報.取引責任者名（漢字）名 = 変更申込情報※.取引先責任者名（漢字）名
            l_accInfoMobileOfficeInfo.directorName = l_mobileOfficeInfoRegistRow.getDirectorGivenName();
            
            //携帯番号・勤務先情報.取引責任者名（カナ）姓 = 変更申込情報※.取引先責任者名（カナ）姓
            l_accInfoMobileOfficeInfo.directorFamilyNameKana = l_mobileOfficeInfoRegistRow.getDirectorFamilyNameAlt1();
            
            //携帯番号・勤務先情報.取引責任者名（カナ）名 = 変更申込情報※.取引先責任者名（カナ）名
            l_accInfoMobileOfficeInfo.directorNameKana = l_mobileOfficeInfoRegistRow.getDirectorGivenNameAlt1();
            
            //携帯番号・勤務先情報.取引責任者　@所属部署 = 変更申込情報※.取引責任者所属部署
            l_accInfoMobileOfficeInfo.directorDepartment = l_mobileOfficeInfoRegistRow.getDirectorDepartment();
            
            //携帯番号・勤務先情報.取引責任者　@役職 = 変更申込情報※.取引責任者役職
            l_accInfoMobileOfficeInfo.directorPosition = l_mobileOfficeInfoRegistRow.getDirectorPost();
            
            //携帯番号・勤務先情報.取引責任者郵便番号 = 変更申込情報※.取引先責任者郵便番号
            l_accInfoMobileOfficeInfo.directorZipCode = l_mobileOfficeInfoRegistRow.getDirectorZipCode();
            
            //携帯番号・勤務先情報.取引責任者住所１ = 変更申込情報※.取引先責任者住所１
            l_accInfoMobileOfficeInfo.directorAddress1 = l_mobileOfficeInfoRegistRow.getDirectorAddress1();

            //携帯番号・勤務先情報.取引責任者住所２ = 変更申込情報※.取引先責任者住所２
            l_accInfoMobileOfficeInfo.directorAddress2 = l_mobileOfficeInfoRegistRow.getDirectorAddress2();
            
            //携帯番号・勤務先情報.取引責任者住所３ = 変更申込情報※.取引先責任者住所３
            l_accInfoMobileOfficeInfo.directorAddress3 = l_mobileOfficeInfoRegistRow.getDirectorAddress3();
            
            //携帯番号・勤務先情報.取引責任者生年月日　@年号 = 変更申込情報※.取引先責任者生年月日年号
            l_accInfoMobileOfficeInfo.directorEraBorn = l_mobileOfficeInfoRegistRow.getDirectorEraBorn();
            
            //携帯番号・勤務先情報.取引責任者生年月日 = 変更申込情報※.取引先責任者生年月日
            l_accInfoMobileOfficeInfo.directorBornDate = l_mobileOfficeInfoRegistRow.getDirectorBornDate();
            
            //携帯番号・勤務先情報.取引責任者会社直通番号 = 変更申込情報※.取引先責任者会社直通番号
            l_accInfoMobileOfficeInfo.directorCorpDirect = l_mobileOfficeInfoRegistRow.getDirectorCorpTelephone();
            
            //携帯番号・勤務先情報.その他連絡先（携帯、自宅等） = 変更申込情報※.その他の連絡先（携帯、自宅等）  
            l_accInfoMobileOfficeInfo.directorOtherContact = l_mobileOfficeInfoRegistRow.getOtherContact();
            
            //携帯番号・勤務先情報.FAX番号 = 変更申込情報※.FAX番号
            l_accInfoMobileOfficeInfo.faxTelephone = l_mobileOfficeInfoRegistRow.getFax();
            
            //携帯番号・勤務先情報.年収 = 変更申込情報※.年収
            l_accInfoMobileOfficeInfo.annualIncomeDiv = l_mobileOfficeInfoRegistRow.getAnnualIncomeDiv();
            
            //携帯番号・勤務先情報.金融資産額 = 変更申込情報※.金融資産額
            l_accInfoMobileOfficeInfo.assetValueDiv = l_mobileOfficeInfoRegistRow.getAssetValueDiv();
            
            //携帯番号・勤務先情報.運用予定額 = 変更申込情報※.運用予定額
            l_accInfoMobileOfficeInfo.fundBundgetAmountDiv = l_mobileOfficeInfoRegistRow.getFundBudgetAmountDiv();
            
            //携帯番号・勤務先情報.投資目的 = 変更申込情報※.投資目的
            l_accInfoMobileOfficeInfo.investPurposeDiv = l_mobileOfficeInfoRegistRow.getInvestPurposeDiv();
            
            //携帯番号・勤務先情報.投資予定期間 = 変更申込情報※.投資予定期間
            l_accInfoMobileOfficeInfo.investPlanPeriodDiv = l_mobileOfficeInfoRegistRow.getInvestPlanPeriodDiv();
            
            
            //携帯番号・勤務先情報.投資経験の有無（１） = 変更申込情報※.投資経験の有無（１）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag1IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag1= l_mobileOfficeInfoRegistRow.getExperienceFlag1() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（２） = 変更申込情報※.投資経験の有無（２）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag2IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag2= l_mobileOfficeInfoRegistRow.getExperienceFlag2() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（３） = 変更申込情報※.投資経験の有無（３）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag3IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag3= l_mobileOfficeInfoRegistRow.getExperienceFlag3() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（４） = 変更申込情報※.投資経験の有無（４）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag4IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag4= l_mobileOfficeInfoRegistRow.getExperienceFlag4() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（５） = 変更申込情報※.投資経験の有無（５）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag5IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag5= l_mobileOfficeInfoRegistRow.getExperienceFlag5() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（６） = 変更申込情報※.投資経験の有無（６）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag6IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag6= l_mobileOfficeInfoRegistRow.getExperienceFlag6() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（７） = 変更申込情報※.投資経験の有無（７）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag7IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag7= l_mobileOfficeInfoRegistRow.getExperienceFlag7() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（８） = 変更申込情報※.投資経験の有無（８）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag8IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag8= l_mobileOfficeInfoRegistRow.getExperienceFlag8() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（９） = 変更申込情報※.投資経験の有無（９）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag9IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag9= l_mobileOfficeInfoRegistRow.getExperienceFlag9() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（１０） = 変更申込情報※.投資経験の有無（１０）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag10IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag10= l_mobileOfficeInfoRegistRow.getExperienceFlag10() + "";
            }
            
            //携帯番号・勤務先情報.投資経験（１） = 変更申込情報※.投資経験（１）
            l_accInfoMobileOfficeInfo.experienceDiv1= l_mobileOfficeInfoRegistRow.getExperienceDiv1();
            
            //携帯番号・勤務先情報.投資経験（２） = 変更申込情報※.投資経験（２）
            l_accInfoMobileOfficeInfo.experienceDiv2= l_mobileOfficeInfoRegistRow.getExperienceDiv2();
            
            //携帯番号・勤務先情報.投資経験（３） = 変更申込情報※.投資経験（３）
            l_accInfoMobileOfficeInfo.experienceDiv3= l_mobileOfficeInfoRegistRow.getExperienceDiv3();
            
            //携帯番号・勤務先情報.投資経験（４） = 変更申込情報※.投資経験（４）
            l_accInfoMobileOfficeInfo.experienceDiv4= l_mobileOfficeInfoRegistRow.getExperienceDiv4();
            
            //携帯番号・勤務先情報.投資経験（５） = 変更申込情報※.投資経験（５）
            l_accInfoMobileOfficeInfo.experienceDiv5= l_mobileOfficeInfoRegistRow.getExperienceDiv5();
            
            //携帯番号・勤務先情報.投資経験（６） = 変更申込情報※.投資経験（６）
            l_accInfoMobileOfficeInfo.experienceDiv6= l_mobileOfficeInfoRegistRow.getExperienceDiv6();
            
            //携帯番号・勤務先情報.投資経験（７） = 変更申込情報※.投資経験（７）
            l_accInfoMobileOfficeInfo.experienceDiv7= l_mobileOfficeInfoRegistRow.getExperienceDiv7();
            
            //携帯番号・勤務先情報.投資経験（８） = 変更申込情報※.投資経験（８）
            l_accInfoMobileOfficeInfo.experienceDiv8= l_mobileOfficeInfoRegistRow.getExperienceDiv8();
            
            //携帯番号・勤務先情報.投資経験（９） = 変更申込情報※.投資経験（９）
            l_accInfoMobileOfficeInfo.experienceDiv9= l_mobileOfficeInfoRegistRow.getExperienceDiv9();
            
            //携帯番号・勤務先情報.投資経験（１０） = 変更申込情報※.投資経験（１０）
            l_accInfoMobileOfficeInfo.experienceDiv10= l_mobileOfficeInfoRegistRow.getExperienceDiv10();
            
            //携帯番号・勤務先情報.取引の種類（１） = 変更申込情報※.取引の種類（１）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.interest1= l_mobileOfficeInfoRegistRow.getInterestFlag1() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（２） = 変更申込情報※.取引の種類（２）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.interest2= l_mobileOfficeInfoRegistRow.getInterestFlag2() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（３） = 変更申込情報※.取引の種類（３）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.interest3= l_mobileOfficeInfoRegistRow.getInterestFlag3() + "";
            }

            //携帯番号・勤務先情報.取引の種類（４） = 変更申込情報※.取引の種類（４）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.interest4= l_mobileOfficeInfoRegistRow.getInterestFlag4() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（５） = 変更申込情報※.取引の種類（５）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.interest5= l_mobileOfficeInfoRegistRow.getInterestFlag5() + "";
            }

            //携帯番号・勤務先情報.取引の種類（６） = 変更申込情報※.取引の種類（６）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.interest6= l_mobileOfficeInfoRegistRow.getInterestFlag6() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（７） = 変更申込情報※.取引の種類（７）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.interest7= l_mobileOfficeInfoRegistRow.getInterestFlag7() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（８） = 変更申込情報※.取引の種類（８）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.interest8= l_mobileOfficeInfoRegistRow.getInterestFlag8() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（９） = 変更申込情報※.取引の種類（９）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.interest9= l_mobileOfficeInfoRegistRow.getInterestFlag9() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（１０） = 変更申込情報※.取引の種類（１０）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.interest10= l_mobileOfficeInfoRegistRow.getInterestFlag10() + "";
            }
            
            //携帯番号・勤務先情報.口座開設の動機@ = 変更申込情報※.口座開設の動機@
            l_accInfoMobileOfficeInfo.appliMotivatDiv= l_mobileOfficeInfoRegistRow.getAppliMotivatDiv();
            
            //携帯番号・勤務先情報.口座開設の動機@の詳細 = 変更申込情報※.口座開設の動機@の詳細
            l_accInfoMobileOfficeInfo.appliMotivatDetail= l_mobileOfficeInfoRegistRow.getAppliMotivatDivDetail();
            
            //携帯番号・勤務先情報.現在利用している証券会社 = 変更申込情報※.現在利用している証券会社
            l_accInfoMobileOfficeInfo.useInstitutionDiv= l_mobileOfficeInfoRegistRow.getUseInstitutionDiv();
            
            //携帯番号・勤務先情報.インターネット取引区分 = 変更申込情報※.インターネット取引区分
            l_accInfoMobileOfficeInfo.internetTradeDiv= l_mobileOfficeInfoRegistRow.getInternetTradeDiv();
            
            //携帯番号・勤務先情報.紹介支店 = 変更申込情報※.紹介支店取引区分
            l_accInfoMobileOfficeInfo.introduceBranch= l_mobileOfficeInfoRegistRow.getIntroduceBranchCode();
            
            //1.11) プロパティセット                
            l_response.changedMobileOfficeInfo = l_accInfoMobileOfficeInfo;
            
            //以外、get申込状況区分()
            l_response.applyStateDiv = l_strAccInfoRegistStateDiv;
            if (l_request.judgementFlag)
            {
            	MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams(l_mobileOfficeInfoRegistRow);
            	//判定確認中フラグ 1：TRUE（判定確認中）
            	l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.TRUE);
            	//更新者コード 管理者.管理者コード
            	l_mobileOfficeInfoRegistParams.setLastUpdater(l_administrator.getAdministratorCode());
            	//更新日時 処理日時　@※TradingSystem.getSystemTimestamp() 
            	l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                QueryProcessor l_queryProcessor;
                try 
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
                } 
                catch (DataFindException l_e) 
                {
                    log.error("テーブルに該当するデータがありません。");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }  
                catch (DataQueryException l_e) 
                {
                    log.error("DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                } 
                catch (DataNetworkException l_e) 
                {
                    log.error("DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_e.getMessage(),
                        l_e);
                }
            }          
        }        
        else
        {
            
            //変更申込情報がない場合（get携帯番号・勤務先情報変更申込() == null）、null
            l_response.changedMobileOfficeInfo = null;
            
            //レスポンスデータ.申込状況区分
            //－変更申込情報がない場合（get携帯番号・勤務先情報変更申込() == null）、null。
            l_response.applyStateDiv = null;
        }

        //1.9) create顧客基本情報(顧客 : 顧客)
        WEB3AccInfoAccountBaseInfoCreatedService l_service = 
            (WEB3AccInfoAccountBaseInfoCreatedService)Services.getService(WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoAccountBaseInfo l_accInfoAccountBaseInfo = 
            l_service.createAccountBaseInfo(l_mainAccount); 
            
        //レスポンスデータ.変更前情報：　@create顧客基本情報().携帯番号・勤務先情報
        l_response.preMobileOfficeInfo = l_accInfoAccountBaseInfo.mobileOfficeInfo;
        //レスポンスデータ.口座タイプ：　@create顧客基本情報().口座タイプ
        l_response.accountType = l_accInfoAccountBaseInfo.accountType;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate変更)<BR>
     * 携帯番号・勤務先情報変更確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（携帯番号・勤務先情報変更）validate変更」参照。 <BR>
     * ===================================================================== <BR>
     * シーケンス図 「管理者お客様情報（携帯番号・勤務先情報変更）validate変更」<BR>
     * (validate変更)<BR>
     * 1.2 変更項目無の場合（is変更項目（）==FALSE）、「変更項目無しエラー」例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02688 <BR>
     * ===================================================================== <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse
     * @@roseuid 4148FC30012C
     */
    protected WEB3AdminAccInfoMobileOfficeRegistConfirmResponse validateChange(
        WEB3AdminAccInfoMobileOfficeRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1  is変更項目(携帯番号・勤務先情報, 携帯番号・勤務先情報)
        boolean l_blnIsChangedItem = WEB3AccInfoMobileOfficeInfoRegist.isChangedItem(
            l_request.preMobileOfficeInfo,
            l_request.changedMobileOfficeInfo);
        
        //1.2 変更項目無の場合（is変更項目（）== FALSE）、「変更項目無しエラー」例外をスローする。 
        if (!l_blnIsChangedItem)
        {
            log.debug("変更項目無しエラー");
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更項目無しエラー");
        }
        
        //1.3) validate( )
        l_request.validate();
        
        //1.4) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.5) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.6) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.7) get顧客(String, String, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
               
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);
        
        //1.8) is連絡先優先順位
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.9) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.10) get携帯番号・勤務先情報変更申込(顧客)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.11) 変更申込情報がある場合（get携帯番号・勤務先情報変更申込() != null）、判定確認中フラグを更新する
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.11.1) getDataSourceObject( )
            MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());
            
            //1.11.2) doUpdateQuery(PrimaryKey, String, Object[], Map)
            l_mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.TRUE);
            l_mobileOfficeInfoRegistParams.setLastUpdater(l_administrator.getAdministratorCode());
            l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();              
                l_queryProcessor.doUpdateQuery(l_mobileOfficeInfoRegistParams);             
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }          
        }
   
        //1.12) createResponse( )
        WEB3AdminAccInfoMobileOfficeRegistConfirmResponse l_response = 
            (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
         
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (submit変更)<BR>
     * 携帯番号・勤務先情報変更完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（携帯番号・勤務先情報変更）submit変更」参照。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse
     * @@roseuid 4148FC30014B
     */
    protected WEB3AdminAccInfoMobileOfficeRegistCompleteResponse submitChange(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        
        //1.4) get顧客(String, String, String)
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount =
            l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);
        
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //部店コード
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //顧客コード
        String l_accountCode = l_mainAccount.getAccountCode();
        
        //1.5) lock口座             
        l_accManager.lockAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_accountCode);
        
        
        //1.6) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, true);
        
        //1.7) validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);
         
        //1.8) is連絡先優先順位
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.9) validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.10) get携帯番号・勤務先情報変更申込(顧客)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.11) get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams;
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParamsTemp;
        //1.12) 変更申込情報がある場合（get携帯番号・勤務先情報変更申込() != null）、判定情報を更新する
        if(l_accInfoMobileOfficeInfoRegist != null)
        {        
            //1.12.1) createForUpdateParams( )
            l_accInfoMobileOfficeInfoRegist.createForUpdateParams();
            
            //1.12.2) set判定(String, String)
            l_accInfoMobileOfficeInfoRegist.setDecision(l_strAdministratorCode,l_request.judgmentResultDiv);
            
            //1.12.3) getDataSourceObject( )
            l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());

            l_mobileOfficeInfoRegistParamsTemp = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());

            l_mobileOfficeInfoRegistParams.setMobile(l_request.changedMobileOfficeInfo.mobileTelephone);
            l_mobileOfficeInfoRegistParams.setOffice(l_request.changedMobileOfficeInfo.officeName);
            l_mobileOfficeInfoRegistParams.setOfficeZipCode(l_request.changedMobileOfficeInfo.officeZipCode);
            l_mobileOfficeInfoRegistParams.setOfficeAddress(l_request.changedMobileOfficeInfo.officeAdress);
            l_mobileOfficeInfoRegistParams.setOfficeTelephone(l_request.changedMobileOfficeInfo.officeTelephone);
            l_mobileOfficeInfoRegistParams.setPost(l_request.changedMobileOfficeInfo.position);
            l_mobileOfficeInfoRegistParams.setContactPriority1(l_request.changedMobileOfficeInfo.contactPriority1);
            l_mobileOfficeInfoRegistParams.setContactPriority2(l_request.changedMobileOfficeInfo.contactPriority2);
            l_mobileOfficeInfoRegistParams.setContactPriority3(l_request.changedMobileOfficeInfo.contactPriority3);
            
            //DB更新仕様.: 管理者・携帯番号・勤務先情報変更_携帯番号・勤務先情報変更申込テーブル②(CITI版)
            //顧客正式名称１: 携帯番号・勤務先情報.顧客正式名称１（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setRealName1(l_request.changedMobileOfficeInfo.accountRealName1);
            //顧客正式名称２: 携帯番号・勤務先情報.顧客正式名称２（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setRealName2(l_request.changedMobileOfficeInfo.accountRealName2);
            //職業区分: 携帯番号・勤務先情報.職業区分（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setOccupationDiv(l_request.changedMobileOfficeInfo.occupationDiv);
            //所属: 携帯番号・勤務先情報.所属（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDepartment(l_request.changedMobileOfficeInfo.department);
            //国籍: 携帯番号・勤務先情報.国籍（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setNationality(l_request.changedMobileOfficeInfo.nationality);
            //国籍その他: 携帯番号・勤務先情報.国籍その他（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setNationalityOther(l_request.changedMobileOfficeInfo.nationalityOther);
            //代表者名（漢字）姓: 携帯番号・勤務先情報.代表者名（漢字）姓（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setRepresentFamilyName(l_request.changedMobileOfficeInfo.representFamilyName);
            //代表者名（漢字）名: 携帯番号・勤務先情報.代表者名（漢字）名（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setRepresentGivenName(l_request.changedMobileOfficeInfo.representName);
            //代表者名（カナ）姓: 携帯番号・勤務先情報.代表者名（カナ）姓（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setRepresentFamilyNameAlt1(l_request.changedMobileOfficeInfo.representFamilyNameKana);
            //代表者名（カナ）名: 携帯番号・勤務先情報.代表者名（カナ）名（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setRepresentGivenNameAlt1(l_request.changedMobileOfficeInfo.representNameKana);
            //代表者権: 携帯番号・勤務先情報.代表者権（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setRepresentPower(l_request.changedMobileOfficeInfo.representPower);
            //取引責任者名（漢字）姓: 携帯番号・勤務先情報.取引責任者名（漢字）姓（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorFamilyName(l_request.changedMobileOfficeInfo.directorFamilyName);
            //取引責任者名（漢字）名: 携帯番号・勤務先情報.取引責任者名（漢字）名（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorGivenName(l_request.changedMobileOfficeInfo.directorName);
            //取引責任者名（カナ）姓: 携帯番号・勤務先情報.取引責任者名（カナ）姓（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorFamilyNameAlt1(l_request.changedMobileOfficeInfo.directorFamilyNameKana);
            //取引責任者名（カナ）名: 携帯番号・勤務先情報.取引責任者名（カナ）名（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorGivenNameAlt1(l_request.changedMobileOfficeInfo.directorNameKana);
            //取引責任者　@所属部署: 携帯番号・勤務先情報.取引責任者　@所属部署（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorDepartment(l_request.changedMobileOfficeInfo.directorDepartment);
            //取引責任者　@役職: 携帯番号・勤務先情報.取引責任者　@役職（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorPost(l_request.changedMobileOfficeInfo.directorPosition);
            //取引責任者郵便番号: 携帯番号・勤務先情報.取引責任者郵便番号（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorZipCode(l_request.changedMobileOfficeInfo.directorZipCode);
            //取引責任者住所１: 携帯番号・勤務先情報.取引責任者住所１（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorAddress1(l_request.changedMobileOfficeInfo.directorAddress1);
            //取引責任者住所２: 携帯番号・勤務先情報.取引責任者住所２（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorAddress2(l_request.changedMobileOfficeInfo.directorAddress2);
            //取引責任者住所３: 携帯番号・勤務先情報.取引責任者住所３（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorAddress3(l_request.changedMobileOfficeInfo.directorAddress3);
            //取引責任者生年月日　@年号: 携帯番号・勤務先情報.取引責任者生年月日　@年号（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorEraBorn(l_request.changedMobileOfficeInfo.directorEraBorn);
            //取引責任者生年月日: 携帯番号・勤務先情報.取引責任者生年月日（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorBornDate(l_request.changedMobileOfficeInfo.directorBornDate);
            //取引責任者会社直通番号: 携帯番号・勤務先情報.取引責任者会社直通番号（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setDirectorCorpTelephone(l_request.changedMobileOfficeInfo.directorCorpDirect);
            //その他連絡先（携帯、自宅等）: 携帯番号・勤務先情報.その他連絡先（携帯、自宅等）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setOtherContact(l_request.changedMobileOfficeInfo.directorOtherContact);
            //FAX番号: 携帯番号・勤務先情報.FAX番号（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setFax(l_request.changedMobileOfficeInfo.faxTelephone);
            //年収: 携帯番号・勤務先情報.年収（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setAnnualIncomeDiv(l_request.changedMobileOfficeInfo.annualIncomeDiv);
            //金融資産額: 携帯番号・勤務先情報.金融資産額（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setAssetValueDiv(l_request.changedMobileOfficeInfo.assetValueDiv);
            //運用予定額: 携帯番号・勤務先情報.運用予定額（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setFundBudgetAmountDiv(l_request.changedMobileOfficeInfo.fundBundgetAmountDiv);
            //投資目的: 携帯番号・勤務先情報.投資目的（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setInvestPurposeDiv(l_request.changedMobileOfficeInfo.investPurposeDiv);
            //投資予定期間: 携帯番号・勤務先情報.投資予定期間（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setInvestPlanPeriodDiv(l_request.changedMobileOfficeInfo.investPlanPeriodDiv);
            //投資経験の有無（１）: 携帯番号・勤務先情報.投資経験の有無（１）（* 画面入力値）
            Integer l_intexperienceFlag1 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag1 != null)
            {
                l_intexperienceFlag1 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag1);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag1(l_intexperienceFlag1);
            //投資経験の有無（２）: 携帯番号・勤務先情報.投資経験の有無（２）（* 画面入力値）
            Integer l_intexperienceFlag2 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag2 != null)
            {
                l_intexperienceFlag2 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag2);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag2(l_intexperienceFlag2);
            //投資経験の有無（３）: 携帯番号・勤務先情報.投資経験の有無（３）（* 画面入力値）
            Integer l_intexperienceFlag3 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag3 != null)
            {
                l_intexperienceFlag3 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag3);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag3(l_intexperienceFlag3);
            //投資経験の有無（４）: 携帯番号・勤務先情報.投資経験の有無（４）（* 画面入力値）
            Integer l_intexperienceFlag4 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag4 != null)
            {
                l_intexperienceFlag4 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag4);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag4(l_intexperienceFlag4);
            //投資経験の有無（５）: 携帯番号・勤務先情報.投資経験の有無（５）（* 画面入力値）
            Integer l_intexperienceFlag5 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag5 != null)
            {
                l_intexperienceFlag5 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag5);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag5(l_intexperienceFlag5);
            //投資経験の有無（６）: 携帯番号・勤務先情報.投資経験の有無（６）（* 画面入力値）
            Integer l_intexperienceFlag6 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag6 != null)
            {
                l_intexperienceFlag6 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag6);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag6(l_intexperienceFlag6);
            //投資経験の有無（７）: 携帯番号・勤務先情報.投資経験の有無（７）（* 画面入力値）
            Integer l_intexperienceFlag7 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag7 != null)
            {
                l_intexperienceFlag7 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag7);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag7(l_intexperienceFlag7);
            //投資経験の有無（８）: 携帯番号・勤務先情報.投資経験の有無（８）（* 画面入力値）
            Integer l_intexperienceFlag8 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag8 != null)
            {
                l_intexperienceFlag8 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag8);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag8(l_intexperienceFlag8);
            //投資経験の有無（９）: 携帯番号・勤務先情報.投資経験の有無（９）（* 画面入力値）
            Integer l_intexperienceFlag9 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag9 != null)
            {
                l_intexperienceFlag9 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag9);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag9(l_intexperienceFlag9);
            //投資経験の有無（１０）: 携帯番号・勤務先情報.投資経験の有無（１０）（* 画面入力値）
            Integer l_intexperienceFlag10 = null;
            if (l_request.changedMobileOfficeInfo.experienceFlag10 != null)
            {
                l_intexperienceFlag10 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag10);
            }
            l_mobileOfficeInfoRegistParams.setExperienceFlag10(l_intexperienceFlag10);
            //投資経験（１）: 携帯番号・勤務先情報.投資経験（１）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv1(l_request.changedMobileOfficeInfo.experienceDiv1);
            //投資経験（２）: 携帯番号・勤務先情報.投資経験（２）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv2(l_request.changedMobileOfficeInfo.experienceDiv2);
            //投資経験（３）: 携帯番号・勤務先情報.投資経験（３）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv3(l_request.changedMobileOfficeInfo.experienceDiv3);
            //投資経験（４）: 携帯番号・勤務先情報.投資経験（４）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv4(l_request.changedMobileOfficeInfo.experienceDiv4);
            //投資経験（５）: 携帯番号・勤務先情報.投資経験（５）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv5(l_request.changedMobileOfficeInfo.experienceDiv5);
            //投資経験（６）: 携帯番号・勤務先情報.投資経験（６）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv6(l_request.changedMobileOfficeInfo.experienceDiv6);
            //投資経験（７）: 携帯番号・勤務先情報.投資経験（７）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv7(l_request.changedMobileOfficeInfo.experienceDiv7);
            //投資経験（８）: 携帯番号・勤務先情報.投資経験（８）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv8(l_request.changedMobileOfficeInfo.experienceDiv8);
            //投資経験（９）: 携帯番号・勤務先情報.投資経験（９）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv9(l_request.changedMobileOfficeInfo.experienceDiv9);
            //投資経験（１０）: 携帯番号・勤務先情報.投資経験（１０）（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setExperienceDiv10(l_request.changedMobileOfficeInfo.experienceDiv10);
            //取引の種類（１）: 携帯番号・勤務先情報.取引の種類（１）（* 画面入力値）
            Integer l_intInterest1 = null;
            if (l_request.changedMobileOfficeInfo.interest1 != null)
            {
                l_intInterest1 = new Integer(l_request.changedMobileOfficeInfo.interest1);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag1(l_intInterest1);
            //取引の種類（２）: 携帯番号・勤務先情報.取引の種類（２）（* 画面入力値）
            Integer l_intInterest2 = null;
            if (l_request.changedMobileOfficeInfo.interest2 != null)
            {
                l_intInterest2 = new Integer(l_request.changedMobileOfficeInfo.interest2);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag2(l_intInterest2);
            //取引の種類（３）: 携帯番号・勤務先情報.取引の種類（３）（* 画面入力値）
            Integer l_intInterest3 = null;
            if (l_request.changedMobileOfficeInfo.interest3 != null)
            {
                l_intInterest3 = new Integer(l_request.changedMobileOfficeInfo.interest3);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag3(l_intInterest3);
            //取引の種類（４）: 携帯番号・勤務先情報.取引の種類（４）（* 画面入力値）
            Integer l_intInterest4 = null;
            if (l_request.changedMobileOfficeInfo.interest4 != null)
            {
                l_intInterest4 = new Integer(l_request.changedMobileOfficeInfo.interest4);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag4(l_intInterest4);
            //取引の種類（５）: 携帯番号・勤務先情報.取引の種類（５）（* 画面入力値）
            Integer l_intInterest5 = null;
            if (l_request.changedMobileOfficeInfo.interest5 != null)
            {
                l_intInterest5 = new Integer(l_request.changedMobileOfficeInfo.interest5);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag5(l_intInterest5);
            //取引の種類（６）: 携帯番号・勤務先情報.取引の種類（６）（* 画面入力値）
            Integer l_intInterest6 = null;
            if (l_request.changedMobileOfficeInfo.interest6 != null)
            {
                l_intInterest6 = new Integer(l_request.changedMobileOfficeInfo.interest6);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag6(l_intInterest6);
            //取引の種類（７）: 携帯番号・勤務先情報.取引の種類（７）（* 画面入力値）
            Integer l_intInteres7 = null;
            if (l_request.changedMobileOfficeInfo.interest7 != null)
            {
                l_intInteres7 = new Integer(l_request.changedMobileOfficeInfo.interest7);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag7(l_intInteres7);
            //取引の種類（８）: 携帯番号・勤務先情報.取引の種類（８）（* 画面入力値）
            Integer l_intInteres8 = null;
            if (l_request.changedMobileOfficeInfo.interest8 != null)
            {
                l_intInteres8 = new Integer(l_request.changedMobileOfficeInfo.interest8);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag8(l_intInteres8);
            //取引の種類（９）: 携帯番号・勤務先情報.取引の種類（９）（* 画面入力値）
            Integer l_intInteres9 = null;
            if (l_request.changedMobileOfficeInfo.interest9 != null)
            {
                l_intInteres9 = new Integer(l_request.changedMobileOfficeInfo.interest9);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag9(l_intInteres9);
            //取引の種類（１０）: 携帯番号・勤務先情報.取引の種類（１０）（* 画面入力値）
            Integer l_intInteres10 = null;
            if (l_request.changedMobileOfficeInfo.interest10 != null)
            {
                l_intInteres10 = new Integer(l_request.changedMobileOfficeInfo.interest10);
            } 
            l_mobileOfficeInfoRegistParams.setInterestFlag10(l_intInteres10);
            //口座開設の動機@: 携帯番号・勤務先情報.口座開設の動機@（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setAppliMotivatDiv(l_request.changedMobileOfficeInfo.appliMotivatDiv);
            //口座開設の動機@の詳細: 携帯番号・勤務先情報.口座開設の動機@の詳細（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setAppliMotivatDivDetail(l_request.changedMobileOfficeInfo.appliMotivatDetail);
            //現在利用している証券会社: 携帯番号・勤務先情報.現在利用している証券会社（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setUseInstitutionDiv(l_request.changedMobileOfficeInfo.useInstitutionDiv);
            //インターネット取引区分: 携帯番号・勤務先情報.インターネット取引区分（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setInternetTradeDiv(l_request.changedMobileOfficeInfo.internetTradeDiv);
            //紹介支店: 携帯番号・勤務先情報.紹介支店（* 画面入力値）
            l_mobileOfficeInfoRegistParams.setIntroduceBranchCode(l_request.changedMobileOfficeInfo.introduceBranch);
            
            //1.12.4) doUpdateQuery(Row)
            try
            {
                
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doUpdateQuery(l_mobileOfficeInfoRegistParams);
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }     
        }
            
        //1.13) 変更申込情報がない場合（get携帯番号・勤務先情報変更申込() == null）、変更申込情報を新規作成する
        else
        {
            
            //1.13.1) createNew携帯番号・勤務先情報変更申込(顧客, 携帯番号・勤務先情報, String)
            WEB3AccInfoMobileOfficeInfoRegist l_createNewMobileOfficeInfoRegist =
                WEB3AccInfoMobileOfficeInfoRegist.createNewMobileOfficeInfoRegist(
                    l_mainAccount,
                    l_request.changedMobileOfficeInfo,
                    l_strAdministratorCode);
            
            //1.13.2) set判定(String, String)
            l_createNewMobileOfficeInfoRegist.setDecision(l_strAdministratorCode,l_request.judgmentResultDiv);
            
            //1.13.3) getDataSourceObject()
            l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_createNewMobileOfficeInfoRegist.getDataSourceObject());

            l_mobileOfficeInfoRegistParamsTemp = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_createNewMobileOfficeInfoRegist.getDataSourceObject());
            
            //1.13.4) doInsertQuery(String, Row)
            try
            {
                
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doInsertQuery(l_mobileOfficeInfoRegistParams);
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(" DBへのアクセスに失敗しました! ", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
        } 
        
        if(WEB3JudgmentResultDivDef.CONSENT.equals(l_request.judgmentResultDiv))
        {
        
            //1.14.1) getDataSourceObject( )
            MainAccountParams l_mainAccountParams = 
                new MainAccountParams((MainAccountRow)l_mainAccount.getDataSourceObject());
        
            //1.14.2) doUpdateQuery(PrimaryKey, String, Object[], Map)
            Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
        
            l_mainAccountParams.setMobile(l_mobileOfficeInfoRegistParams.mobile);
            l_mainAccountParams.setOffice(l_mobileOfficeInfoRegistParams.office);
            l_mainAccountParams.setOfficeZipCode(l_mobileOfficeInfoRegistParams.office_zip_code);
            l_mainAccountParams.setOfficeAddress(l_mobileOfficeInfoRegistParams.office_address);
            l_mainAccountParams.setOfficeTelephone(l_mobileOfficeInfoRegistParams.office_telephone);
            l_mainAccountParams.setPost(l_mobileOfficeInfoRegistParams.post);
            l_mainAccountParams.setMbOffLastUpdater(l_strAdministratorCode);
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(l_systemTimestamp);
            l_mainAccountParams.setLastUpdatedTimestamp(l_systemTimestamp);
        
            try
            {
            
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doUpdateQuery(l_mainAccountParams);
            }
            catch (DataFindException l_ex)
            {
            
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
            
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
            
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  

            // get補助口座(口座ID : , 補助口座タイプ : )
            // 補助口座を取得
            // [get補助口座（）に指定する引数]
            // 口座ID ： 顧客オブジェクト.getAccountId()
            // 補助口座タイプ ： SubAccountTypeEnum EQUITY_SUB_ACCOUNT
            SubAccountTypeEnum l_subAccountTyoeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            long l_lngAccountId = l_mainAccount.getAccountId();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    l_gentradeAccountManager.getSubAccount(l_lngAccountId,l_subAccountTyoeEnum);
            }
            catch (NotFoundException l_ex)
            {
                log.error(" DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // get部店プリファ@レンス(補助口座)
            // 顧客オブジェクトより、部店用プリファ@レンステーブルから
            // 顧客正式名称更新、職業コード更新チェックを取得する。
            // [get部店プリファ@レンス()に指定する引数]
            // 補助口座 ： get補助口座（）の戻り値
            String[] l_intBranchPreferences = this.getBranchPreferences(l_subAccount);

            //1.14.3)口座情報マスタデータを取得する。
            WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
            WEB3AccInfoMaster l_accInfoMasterTemp = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);
            
            //1.15)分岐フロー
            //口座情報マスタ情報がある場合（get口座情報マスタ() != null）、口座情報マスタ情報を更新する。
            AccountInfoMstParams l_accountInfoMstParams = null;
            if (l_accInfoMaster != null)
            {
                //1.15.1)口座情報マスタ行オブジェクトを取得する。
                l_accountInfoMstParams = 
                    new AccountInfoMstParams((AccountInfoMstParams)l_accInfoMaster.getDataSourceObject());
                //1.15.2)口座情報マスタテーブルを更新する。
                //(*4) 口座情報マスタテーブル更新
                //口座情報マスタ情報を更新（DB Update）する。
                //　@更新内容は【ｘTrade】補足資料.DB更新「口座情報マスタテーブル更新仕様.xls（「口座情報マスタ」更新仕様（Update）シート）」参照。
                
                //更新日時 = 処理日時　@※TradingSystem.getSystemTimestamp()
                l_accountInfoMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //更新者コード = 管理者.管理者コード
                l_accountInfoMstParams.setLastUpdater(l_strAdministratorCode);
                
                //this.getプリファ@レンス()の戻り値.index(1)が"1"の場合、
                //携帯番号・勤務先情報変更申込.正式名称１
                //それ以外、既存値
                if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                {
                    l_accountInfoMstParams.setRealName1(l_request.changedMobileOfficeInfo.accountRealName1);
                }

                //this.getプリファ@レンス()の戻り値.index(1)が"1"の場合、
                //携帯番号・勤務先情報変更申込.正式名称２
                //それ以外、既存値
                if (WEB3RealnameUpdateDef.EXECUTE.equals(l_intBranchPreferences[1]))
                {
                    l_accountInfoMstParams.setRealName2(l_request.changedMobileOfficeInfo.accountRealName2);
                }

                //this.getプリファ@レンス()の戻り値.index(0)が"1"の場合、
                //携帯番号・勤務先情報変更申込.職業
                //それ以外、既存値
                if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                {
                    l_accountInfoMstParams.setOccupationDiv(l_request.changedMobileOfficeInfo.occupationDiv);
                }

                //代表者名（漢字）姓 = 携帯番号・勤務先情報変更申込.代表者名（漢字）姓
                l_accountInfoMstParams.setRepresentFamilyName(l_request.changedMobileOfficeInfo.representFamilyName);
                
                //代表者名（漢字）名 = 携帯番号・勤務先情報変更申込.代表者名（漢字）名
                l_accountInfoMstParams.setRepresentGivenName(l_request.changedMobileOfficeInfo.representName);
                
                //代表者名（カナ）姓 = 携帯番号・勤務先情報変更申込.代表者名（カナ）姓
                l_accountInfoMstParams.setRepresentFamilyNameAlt1(l_request.changedMobileOfficeInfo.representFamilyNameKana);
                
                //代表者名（カナ）名 = 携帯番号・勤務先情報変更申込.代表者名（カナ）名
                l_accountInfoMstParams.setRepresentGivenNameAlt1(l_request.changedMobileOfficeInfo.representNameKana);
                
                //代表者権 = 携帯番号・勤務先情報変更申込.代表者権
                l_accountInfoMstParams.setRepresentPower(l_request.changedMobileOfficeInfo.representPower);
                
                //取引責任者名（漢字）姓 = 携帯番号・勤務先情報変更申込.取引先責任者名（漢字）姓
                l_accountInfoMstParams.setDirectorFamilyName(l_request.changedMobileOfficeInfo.directorFamilyName);
                
                //取引責任者名（漢字）名 = 携帯番号・勤務先情報変更申込.取引先責任者名（漢字）名
                l_accountInfoMstParams.setDirectorGivenName(l_request.changedMobileOfficeInfo.directorName);
                
                //取引責任者名（カナ）姓 = 携帯番号・勤務先情報変更申込.取引先責任者名（カナ）姓
                l_accountInfoMstParams.setDirectorFamilyNameAlt1(l_request.changedMobileOfficeInfo.directorFamilyNameKana);
                
                //取引責任者名（カナ）名 = 携帯番号・勤務先情報変更申込.取引先責任者名（カナ）名
                l_accountInfoMstParams.setDirectorGivenNameAlt1(l_request.changedMobileOfficeInfo.directorNameKana);
                
                //取引責任者　@所属部署 = 携帯番号・勤務先情報変更申込.取引責任者所属部署
                l_accountInfoMstParams.setDirectorDepartment(l_request.changedMobileOfficeInfo.directorDepartment);
                
                //取引責任者　@役職 = 携帯番号・勤務先情報変更申込.取引責任者役職
                l_accountInfoMstParams.setDirectorPost(l_request.changedMobileOfficeInfo.directorPosition);
                
                //取引責任者郵便番号 = 携帯番号・勤務先情報変更申込.取引先責任者郵便番号
                l_accountInfoMstParams.setDirectorZipCode(l_request.changedMobileOfficeInfo.directorZipCode);
                
                //取引責任者住所１ = 携帯番号・勤務先情報変更申込.取引先責任者住所１
                l_accountInfoMstParams.setDirectorAddress1(l_request.changedMobileOfficeInfo.directorAddress1);
                
                //取引責任者住所２ = 携帯番号・勤務先情報変更申込.取引先責任者住所２
                l_accountInfoMstParams.setDirectorAddress2(l_request.changedMobileOfficeInfo.directorAddress2);
                
                //取引責任者住所３ = 携帯番号・勤務先情報変更申込.取引先責任者住所３
                l_accountInfoMstParams.setDirectorAddress3(l_request.changedMobileOfficeInfo.directorAddress3);
                
                //取引責任者生年月日　@年号 = 携帯番号・勤務先情報変更申込.取引先責任者生年月日年号
                l_accountInfoMstParams.setDirectorEraBorn(l_request.changedMobileOfficeInfo.directorEraBorn);
                
                //取引責任者生年月日 = 携帯番号・勤務先情報変更申込.取引先責任者生年月日
                l_accountInfoMstParams.setDirectorBornDate(l_request.changedMobileOfficeInfo.directorBornDate);
                
                //取引責任者会社直通番号 = 携帯番号・勤務先情報変更申込.取引先責任者会社直通番号
                l_accountInfoMstParams.setDirectorCorpTelephone(l_request.changedMobileOfficeInfo.directorCorpDirect);
                
                //その他連絡先（携帯、自宅等） = 携帯番号・勤務先情報変更申込.その他の連絡先
                l_accountInfoMstParams.setOtherContact(l_request.changedMobileOfficeInfo.directorOtherContact);
                
                //所属 = 携帯番号・勤務先情報変更申込.所属
                l_accountInfoMstParams.setDepartment(l_request.changedMobileOfficeInfo.department);
                
                //連絡先優先順位 1位 = 携帯番号.勤務先情報変更申込．連絡先優先順位 1位
                l_accountInfoMstParams.setContactPriority1(l_request.changedMobileOfficeInfo.contactPriority1);
                
                //連絡先優先順位 2位 = 携帯番号.勤務先情報変更申込．連絡先優先順位 2位
                l_accountInfoMstParams.setContactPriority2(l_request.changedMobileOfficeInfo.contactPriority2);
                
                //連絡先優先順位 3位 = 携帯番号.勤務先情報変更申込．連絡先優先順位 3位
                l_accountInfoMstParams.setContactPriority3(l_request.changedMobileOfficeInfo.contactPriority3);
                
                //国籍 = 携帯番号.勤務先情報変更申込．国籍
                l_accountInfoMstParams.setNationality(l_request.changedMobileOfficeInfo.nationality);
                
                //国籍その他 = 携帯番号.勤務先情報変更申込．国籍その他
                l_accountInfoMstParams.setNationalityOther(l_request.changedMobileOfficeInfo.nationalityOther);
                
                //口座情報マスタParams.FAX番号 = 携帯番号・勤務先情報.FAX番号      
                l_accountInfoMstParams.setFax(l_request.changedMobileOfficeInfo.faxTelephone);      
                        
                //   　@口座情報マスタParams.年収 = 携帯番号・勤務先情報.年収 
                l_accountInfoMstParams.setAnnualIncomeDiv(l_request.changedMobileOfficeInfo.annualIncomeDiv);       
                        
                //   　@口座情報マスタParams.金融資産額 = 携帯番号・勤務先情報.金融資産額 
                l_accountInfoMstParams.setAssetValueDiv(l_request.changedMobileOfficeInfo.assetValueDiv);       
                        
                //   　@口座情報マスタParams.運用予定額 = 携帯番号・勤務先情報.運用予定額 
                l_accountInfoMstParams.setFundBudgetAmountDiv(l_request.changedMobileOfficeInfo.fundBundgetAmountDiv);      
                        
                //   　@口座情報マスタParams.投資目的 = 携帯番号・勤務先情報.投資目的 
                l_accountInfoMstParams.setInvestPurposeDiv(l_request.changedMobileOfficeInfo.investPurposeDiv);     
                        
                //   　@口座情報マスタParams.投資予定期間 = 携帯番号・勤務先情報.投資予定期間 
                l_accountInfoMstParams.setInvestPlanPeriodDiv(l_request.changedMobileOfficeInfo.investPlanPeriodDiv);       
                        
                //   　@口座情報マスタParams.投資経験の有無（１） = 携帯番号・勤務先情報.投資経験の有無（１） 
                Integer l_intExperienceFlag1 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag1 != null)      
                {       
                    l_intExperienceFlag1 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag1);      
                }       
                l_accountInfoMstParams.setExperienceFlag1(l_intExperienceFlag1);        
                        
                        
                //   　@口座情報マスタParams.投資経験の有無（２） = 携帯番号・勤務先情報.投資経験の有無（２）
                Integer l_intExperienceFlag2 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag2 != null)      
                {       
                    l_intExperienceFlag2 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag2);      
                }       
                l_accountInfoMstParams.setExperienceFlag2(l_intExperienceFlag2);        
                        
                //   　@口座情報マスタParams.投資経験の有無（３） = 携帯番号・勤務先情報.投資経験の有無（３）
                Integer l_intExperienceFlag3 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag3 != null)      
                {       
                    l_intExperienceFlag3 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag3);      
                }       
                l_accountInfoMstParams.setExperienceFlag3(l_intExperienceFlag3);        
                        
                //   　@口座情報マスタParams.投資経験の有無（４） = 携帯番号・勤務先情報.投資経験の有無（４）
                Integer l_intExperienceFlag4 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag4 != null)      
                {       
                    l_intExperienceFlag4 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag4);      
                }       
                l_accountInfoMstParams.setExperienceFlag4(l_intExperienceFlag4);        
                        
                //   　@口座情報マスタParams.投資経験の有無（５） = 携帯番号・勤務先情報.投資経験の有無（５） 
                Integer l_intExperienceFlag5 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag5 != null)      
                {       
                    l_intExperienceFlag5 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag5);      
                }       
                l_accountInfoMstParams.setExperienceFlag5(l_intExperienceFlag5);        
                        
                //   　@口座情報マスタParams.投資経験の有無（６） = 携帯番号・勤務先情報.投資経験の有無（６） 
                Integer l_intExperienceFlag6 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag6 != null)      
                {       
                    l_intExperienceFlag6 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag6);      
                }       
                l_accountInfoMstParams.setExperienceFlag6(l_intExperienceFlag6);        
                        
                //   　@口座情報マスタParams.投資経験の有無（７） = 携帯番号・勤務先情報.投資経験の有無（７）
                Integer l_intExperienceFlag7 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag7 != null)      
                {       
                    l_intExperienceFlag7 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag7);      
                }       
                l_accountInfoMstParams.setExperienceFlag7(l_intExperienceFlag7);        
                        
                //   　@口座情報マスタParams.投資経験の有無（８） = 携帯番号・勤務先情報.投資経験の有無（８）
                Integer l_intExperienceFlag8 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag8 != null)      
                {       
                    l_intExperienceFlag8 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag8);      
                }       
                l_accountInfoMstParams.setExperienceFlag8(l_intExperienceFlag8);        
                        
                //   　@口座情報マスタParams.投資経験の有無（９） = 携帯番号・勤務先情報.投資経験の有無（９） 
                Integer l_intExperienceFlag9 = null;        
                if (l_request.changedMobileOfficeInfo.experienceFlag9 != null)      
                {       
                    l_intExperienceFlag9 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag9);      
                }       
                l_accountInfoMstParams.setExperienceFlag9(l_intExperienceFlag9);        
                        
                //   　@口座情報マスタParams.投資経験の有無（１０） = 携帯番号・勤務先情報.投資経験の有無（１０）
                Integer l_intExperienceFlag10 = null;       
                if (l_request.changedMobileOfficeInfo.experienceFlag10 != null)     
                {       
                    l_intExperienceFlag10 = new Integer(l_request.changedMobileOfficeInfo.experienceFlag10);        
                }       
                l_accountInfoMstParams.setExperienceFlag10(l_intExperienceFlag10);      
                        
                //   　@口座情報マスタParams.投資経験（１） = 携帯番号・勤務先情報.投資経験（１） 
                l_accountInfoMstParams.setExperienceDiv1(l_request.changedMobileOfficeInfo.experienceDiv1);     
                        
                //   　@口座情報マスタParams.投資経験（２） = 携帯番号・勤務先情報.投資経験（２）
                l_accountInfoMstParams.setExperienceDiv2(l_request.changedMobileOfficeInfo.experienceDiv2);     
                        
                //   　@口座情報マスタParams.投資経験（３） = 携帯番号・勤務先情報.投資経験（３）
                l_accountInfoMstParams.setExperienceDiv3(l_request.changedMobileOfficeInfo.experienceDiv3);     
                        
                //   　@口座情報マスタParams.投資経験（４） = 携帯番号・勤務先情報.投資経験（４）
                l_accountInfoMstParams.setExperienceDiv4(l_request.changedMobileOfficeInfo.experienceDiv4);     
                        
                //   　@口座情報マスタParams.投資経験（５） = 携帯番号・勤務先情報.投資経験（５） 
                l_accountInfoMstParams.setExperienceDiv5(l_request.changedMobileOfficeInfo.experienceDiv5);     
                        
                //   　@口座情報マスタParams.投資経験（６） = 携帯番号・勤務先情報.投資経験（６）
                l_accountInfoMstParams.setExperienceDiv6(l_request.changedMobileOfficeInfo.experienceDiv6);     
                        
                //   　@口座情報マスタParams.投資経験（７） = 携帯番号・勤務先情報.投資経験（７） 
                l_accountInfoMstParams.setExperienceDiv7(l_request.changedMobileOfficeInfo.experienceDiv7);     
                        
                //   　@口座情報マスタParams.投資経験（８） = 携帯番号・勤務先情報.投資経験（８）
                l_accountInfoMstParams.setExperienceDiv8(l_request.changedMobileOfficeInfo.experienceDiv8);     
                        
                //   　@口座情報マスタParams.投資経験（９） = 携帯番号・勤務先情報.投資経験（９）
                l_accountInfoMstParams.setExperienceDiv9(l_request.changedMobileOfficeInfo.experienceDiv9);     
                        
                //   　@口座情報マスタParams.投資経験（１０） = 携帯番号・勤務先情報.投資経験（１０） 
                l_accountInfoMstParams.setExperienceDiv10(l_request.changedMobileOfficeInfo.experienceDiv10);       
                        
                //   　@口座情報マスタParams.取引の種類（１） = 携帯番号・勤務先情報.取引の種類（１）
                Integer l_intInterest1 = null;      
                if (l_request.changedMobileOfficeInfo.interest1 != null)        
                {       
                    l_intInterest1 = new Integer(l_request.changedMobileOfficeInfo.interest1);      
                }       
                l_accountInfoMstParams.setInterestFlag1(l_intInterest1);        
                        
                //   　@口座情報マスタParams.取引の種類（２） = 携帯番号・勤務先情報.取引の種類（２）
                Integer l_intInterest2 = null;      
                if (l_request.changedMobileOfficeInfo.interest2 != null)        
                {       
                    l_intInterest2 = new Integer(l_request.changedMobileOfficeInfo.interest2);      
                }       
                l_accountInfoMstParams.setInterestFlag2(l_intInterest2);        
                        
                        
                //   　@口座情報マスタParams.取引の種類（３） = 携帯番号・勤務先情報.取引の種類（３）
                Integer l_intInterest3 = null;      
                if (l_request.changedMobileOfficeInfo.interest3 != null)        
                {       
                    l_intInterest3 = new Integer(l_request.changedMobileOfficeInfo.interest3);      
                }       
                l_accountInfoMstParams.setInterestFlag3(l_intInterest3);        
                        
                        
                //   　@口座情報マスタParams.取引の種類（４） = 携帯番号・勤務先情報.取引の種類（４）
                Integer l_intInterest4 = null;      
                if (l_request.changedMobileOfficeInfo.interest4 != null)        
                {       
                    l_intInterest4 = new Integer(l_request.changedMobileOfficeInfo.interest4);      
                }       
                l_accountInfoMstParams.setInterestFlag4(l_intInterest4);        
                        
                //   　@口座情報マスタParams.取引の種類（５） = 携帯番号・勤務先情報.取引の種類（５）
                Integer l_intInterest5 = null;      
                if (l_request.changedMobileOfficeInfo.interest5 != null)        
                {       
                    l_intInterest5 = new Integer(l_request.changedMobileOfficeInfo.interest5);      
                }       
                l_accountInfoMstParams.setInterestFlag5(l_intInterest5);        
                        
                //   　@口座情報マスタParams.取引の種類（６） = 携帯番号・勤務先情報.取引の種類（６） 
                Integer l_intInterest6 = null;      
                if (l_request.changedMobileOfficeInfo.interest6 != null)        
                {       
                    l_intInterest6 = new Integer(l_request.changedMobileOfficeInfo.interest6);      
                }       
                l_accountInfoMstParams.setInterestFlag6(l_intInterest6);        
                        
                //   　@口座情報マスタParams.取引の種類（７） = 携帯番号・勤務先情報.取引の種類（７） 
                Integer l_intInterest7 = null;      
                if (l_request.changedMobileOfficeInfo.interest7 != null)        
                {       
                    l_intInterest7 = new Integer(l_request.changedMobileOfficeInfo.interest7);      
                }       
                l_accountInfoMstParams.setInterestFlag7(l_intInterest7);        
                        
                //   　@口座情報マスタParams.取引の種類（８） = 携帯番号・勤務先情報.取引の種類（８）
                Integer l_intInterest8 = null;      
                if (l_request.changedMobileOfficeInfo.interest8 != null)        
                {       
                    l_intInterest8 = new Integer(l_request.changedMobileOfficeInfo.interest8);      
                }       
                l_accountInfoMstParams.setInterestFlag8(l_intInterest8);        
                        
                //   　@口座情報マスタParams.取引の種類（９） = 携帯番号・勤務先情報.取引の種類（９） 
                Integer l_intInterest9 = null;      
                if (l_request.changedMobileOfficeInfo.interest9 != null)        
                {       
                    l_intInterest9 = new Integer(l_request.changedMobileOfficeInfo.interest9);      
                }       
                l_accountInfoMstParams.setInterestFlag9(l_intInterest9);        
                        
                //   　@口座情報マスタParams.取引の種類（１０） = 携帯番号・勤務先情報.取引の種類（１０） 
                Integer l_intInterest10 = null;     
                if (l_request.changedMobileOfficeInfo.interest10 != null)       
                {       
                    l_intInterest10 = new Integer(l_request.changedMobileOfficeInfo.interest10);        
                }       
                l_accountInfoMstParams.setInterestFlag10(l_intInterest10);      
                        
                //   　@口座情報マスタParams.口座開設の動機@ = 携帯番号・勤務先情報.口座開設の動機@ 
                l_accountInfoMstParams.setAppliMotivatDiv(l_request.changedMobileOfficeInfo.appliMotivatDiv);       
                        
                //   　@口座情報マスタParams.口座開設の動機@の詳細 = 携帯番号・勤務先情報.口座開設の動機@の詳細 
                l_accountInfoMstParams.setAppliMotivatDivDetail(l_request.changedMobileOfficeInfo.appliMotivatDetail);      
                        
                //   　@口座情報マスタParams.現在利用している証券会社 = 携帯番号・勤務先情報.現在利用している証券会社 
                l_accountInfoMstParams.setUseInstitutionDiv(l_request.changedMobileOfficeInfo.useInstitutionDiv);       
                        
                //   　@口座情報マスタParams.インターネット取引区分 = 携帯番号・勤務先情報.インターネット取引区分 
                l_accountInfoMstParams.setInternetTradeDiv(l_request.changedMobileOfficeInfo.internetTradeDiv);     
                        
                //   　@口座情報マスタParams.紹介支店 = 携帯番号・勤務先情報.紹介支店 
                l_accountInfoMstParams.setIntroduceBranchCode(l_request.changedMobileOfficeInfo.introduceBranch);       
                
                try
                {
                
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    l_processor.doUpdateQuery(l_accountInfoMstParams);
                }
                catch (DataFindException l_ex)
                {
                
                    log.error(" DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                
                    log.error(" DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                
                    log.error(" DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }  
            }
            //1.16)(*5) 口座情報マスタ情報がない場合（get口座情報マスタ() == null）、口座情報マスタ情報を新規作成する。
            else
            {
                //1.16.1)口座情報マスタオブジェクトを新規作成する。
                l_accInfoMaster = WEB3AccInfoMaster.createNewAccInfoMaster(
                    l_mainAccount, 
                    l_request.changedMobileOfficeInfo, 
                    l_strAdministratorCode);

                //1.16.2)口座情報マスタ行オブジェクトを取得する。
                l_accountInfoMstParams = (AccountInfoMstParams)l_accInfoMaster.getDataSourceObject();
                try
                {
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    //1.16)口座情報マスタテーブルに新規行を挿入する。
                    l_processor.doInsertQuery(l_accountInfoMstParams);
                }
                catch (DataFindException l_ex)
                {
                
                    log.error(" DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                
                    log.error(" DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                
                    log.error(" DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }                
            }

            boolean l_blnVoucherCreated = 
                WEB3AccInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(l_mobileOfficeInfoRegistParamsTemp, l_accInfoMasterTemp);

            // is伝票作成()がtrueの場合
            if (l_blnVoucherCreated)
            {
                //get部店プリファ@レンス（）の戻り値index[0]の値 = "1" の場合、以下の処理を実行
                if (WEB3OccupationcodeUpdateDef.EXECUTE.equals(l_intBranchPreferences[0]))
                {
                    //職業変更申込伝票作成( )
                    WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
                        new WEB3AccInfoOccupationChangeRegistVoucherCreated();

                    // create職業変更伝票(long, String)
                    // 職業変更に伴う取残・電子交付・特定口座伝票（GI844）を作成する。
                    // [create職業変更伝票（）に指定する引数]
                    // 変更申込ID ： 携帯番号・勤務先情報変更申込params.携帯番号・勤務先情報変更申込ID
                    // 職業コード ： リクエストデータ.携帯番号・勤務先情報.職業
                    //顧客オブジェクト ： get顧客（）の戻り値
                    long l_lngChangeRegistID = l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId();
                    String l_strOccupationCode = l_request.changedMobileOfficeInfo.occupationDiv;
                    l_accInfoOccupationChangeRegistVoucherCreated.createOccupationChangeVoucher(
                        l_lngChangeRegistID, l_strOccupationCode, l_mainAccount);
                    
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();
                        //口座伝票作成の場合、携帯番号.勤務先情報変更申込テーブルの受付結果区分を 
                        //"0：受付未済"に更新する。 
                        Map l_updateMap = new HashMap(); 
                        l_updateMap.put("accept_status", WEB3AccountInfoAcceptStatusDef.NOT_ACCEPT);
                        l_processor.doUpdateQuery(
                            new MobileOfficeInfoRegistPK(l_mobileOfficeInfoRegistParams.getMobileOfficeInfoRegistId()), 
                            l_updateMap);

                        //口座情報マスタの職業更新日時を更新する。
                        Map l_updateMap1 = new HashMap();
                        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                        l_updateMap1.put("occupation_updated_timestamp", l_tradingSys.getSystemTimestamp());
                        l_processor.doUpdateQuery(
                            new AccountInfoMstPK(l_mobileOfficeInfoRegistParams.getAccountId()),
                            l_updateMap1);
                    }
                    catch (DataFindException l_ex)
                    {
                    
                        log.error(" DBへのアクセスに失敗しました");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataNetworkException l_ex)
                    {
                    
                        log.error(" DBへのアクセスに失敗しました");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataQueryException l_ex)
                    {
                    
                        log.error(" DBへのアクセスに失敗しました");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }

                }
            }
        }
        
        //1.17) createResponse( )
        WEB3AdminAccInfoMobileOfficeRegistCompleteResponse l_response = 
            (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get部店プリファ@レンス )<BR>  
     * 顧客オブジェクトより、部店用プリファ@レンステーブルから<BR>
     * 顧客正式名称更新、職業コード更新チェックを取得する。<BR>
     * <BR>
     * １）　@以下の要素の配列を生成し、返却する。<BR>
     * <BR>
     * １－１）　@DB検索 （職業コード更新）<BR>
     * 　@部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID = 補助口座.getBranch().getBranchId() And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.お客様情報職業コード更新 And<BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、""(空文字)をセットする。<BR>
     * <BR>
     * １－２）　@DB検索 （顧客正式名称更新）<BR>
     * 　@部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID = 補助口座.getBranch().getBranchId() And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.お客様情報顧客正式名称更新 And<BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、""(空文字)をセットする。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BaseException
     * @@return l_strBranchPerferences
     * 
     */
    private String[] getBranchPreferences(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchPreferences(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        String[] l_strBranchPerferences = new String[2];
        try
        {
            // １－１）DB検索 （職業コード更新）
            // 部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する。
            // 部店ID = 補助口座.getBranch().getBranchId() And
            // プリファ@レンス名 = プリファ@レンス名.お客様情報職業コード更新 And
            // プリファ@レンス名の連番 = 1
            BranchPreferencesRow l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.OCCUPATIONCODE_UPDATE,
                    1);

            //検索結果が取得できなかった場合、""(空文字)をセットする。
            if (l_branchReferencesRow == null)
            {
                l_strBranchPerferences[0] = "";
            }
            else
            {
                l_strBranchPerferences[0] = l_branchReferencesRow.getValue();
            }

            // １－２）　@DB検索 （顧客正式名称更新）
            // 部店プリファ@レンステーブルを以下の条件で検索しプリファ@レンスの値を取得する
            // 部店ID = 補助口座.getBranch().getBranchId() And
            // プリファ@レンス名 = プリファ@レンス名.お客様情報顧客正式名称更新 And
            // プリファ@レンス名の連番 = 1
            BranchPreferencesRow l_branchReferenceRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccount.getMainAccount().getBranch().getBranchId(),
                    WEB3BranchPreferencesNameDef.REALNAME_UPDATE,
                    1);

            //検索結果が取得できなかった場合、""(空文字)をセットする。
            if (l_branchReferenceRow == null)
            {
                l_strBranchPerferences[1] = "";
            }
            else
            {
                l_strBranchPerferences[1] = l_branchReferenceRow.getValue();
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBranchPerferences;
    }
}
@
