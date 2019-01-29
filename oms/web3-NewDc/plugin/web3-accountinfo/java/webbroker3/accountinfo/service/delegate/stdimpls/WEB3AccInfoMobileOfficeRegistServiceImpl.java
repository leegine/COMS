head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報携帯番号・勤務先情報変更申込サービスImpl(WEB3AccInfoMobileOfficeRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/02/23 呉艶飛 (中訊) モデルNo.086
                   2006/03/20 呉艶飛 (中訊) モデルNo.098    
                   2006/10/9  齊珂   (中訊) モデルNo.124      
                   2006/10/30 徐大方 (中訊) 仕様変更モデルNo.139
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMobileOfficeRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報携帯番号・勤務先情報変更申込サービスImpl)<BR>
 * お客様情報携帯番号・勤務先情報変更申込サービス実装クラス<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AccInfoMobileOfficeRegistServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoMobileOfficeRegistService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeRegistServiceImpl.class);
    
    /**
     * @@roseuid 418F3A070128
     */
    public WEB3AccInfoMobileOfficeRegistServiceImpl() 
    {
     
    }
    
    /**
     * 携帯番号・勤務先情報変更申込処理を実施する。<BR>
     * <BR>
     * 株式委託手数料コース変更申込処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、お客様情報携帯番号・勤務先情報<BR>
     * 変更申込入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、お客様情報携帯番号・勤務先情報<BR>
     * 変更申込確認リクエストの場合 <BR>
     * 　@－validate変更申込()をコールする。<BR> 
     * ○ 引数のリクエストデータが、お客様情報携帯番号・勤務先情報<BR>
     * 変更申込完了リクエストの場合 <BR>
     * 　@－submit変更申込()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413FEDFC0070
     */ 
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        //引数のリクエストデータが、お客様情報携帯番号・勤務先情報変更申込入力リクエストの場合
        if(l_request instanceof WEB3AccInfoMobileOfficeRegistInputRequest)
        {  
            
            l_response = this.getInputScreen((WEB3AccInfoMobileOfficeRegistInputRequest)l_request);
        }
        
        //引数のリクエストデータが、お客様情報携帯番号・勤務先情報変更申込確認リクエストの場合
        else if(l_request instanceof WEB3AccInfoMobileOfficeRegistConfirmRequest)
        {
            
            l_response = this.validateRegist((WEB3AccInfoMobileOfficeRegistConfirmRequest)l_request);
        }
        
        //引数のリクエストデータが、お客様情報携帯番号・勤務先情報変更申込完了リクエストの場合
        else if(l_request instanceof WEB3AccInfoMobileOfficeRegistCompleteRequest)
        {
            
            l_response = this.submitRegist((WEB3AccInfoMobileOfficeRegistCompleteRequest)l_request);
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
     * 携帯番号・勤務先情報変更申込入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（携帯番号・勤務先情報変更申込）get入力画面」参照。 <BR>
     *  <BR>
     *  ===============================================     <BR>
     *          シーケンス図 :  お客様情報（携帯番号・勤務先情報変更申込）get入力画面             <BR>
     *          具体位置     :  1.4  判定確認中の場合（is判定確認中() == true）、 <BR>
     *                                     例外をスローする。                     <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01167                    <BR>
     *  ===============================================     <BR> 
     *  <BR>
     * @@param l_request - お客様情報携帯番号・勤務先情報変更申込入力リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse
     * @@roseuid 413FEECA0274
     */
    protected WEB3AccInfoMobileOfficeRegistInputResponse getInputScreen(WEB3AccInfoMobileOfficeRegistInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate注文受付可能 ()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.2) get顧客 ()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //1.3) get携帯番号・勤務先情報変更申込(顧客)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.7) お客様情報携帯番号・勤務先情報変更申込入力レスポンス(WEB3GenRequest)
        WEB3AccInfoMobileOfficeRegistInputResponse l_response = 
            (WEB3AccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            
        //1.6) 変更申込情報がある場合（get携帯番号・勤務先情報変更申込() != null）、変更後情報を生成する
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.4) is判定確認中( )
            boolean l_blnIsDeciding = l_accInfoMobileOfficeInfoRegist.isDeciding();
        
            //1.5)  判定確認中の場合（is判定確認中() == true）、例外をスローする
            if(l_blnIsDeciding)
            {
            
                log.error(" 判定確認中です");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01167,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 判定確認中です");
            }
            
            //1.6.1) 携帯番号・勤務先情報( )
            WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();
            
            //1.6.2) プロパティセット
            MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow = 
                (MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject();
                
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
            
            //携帯番号・勤務先情報.顧客正式名称1 = 変更申込情報※.正式名称1
            l_accInfoMobileOfficeInfo.accountRealName1 = l_mobileOfficeInfoRegistRow.getRealName1();
            
            //携帯番号・勤務先情報.顧客正式名称2 = 変更申込情報※.正式名称2
            l_accInfoMobileOfficeInfo.accountRealName2 = l_mobileOfficeInfoRegistRow.getRealName2();
            
            //携帯番号・勤務先情報.職業区分 = 変更申込情報※.職業
            l_accInfoMobileOfficeInfo.occupationDiv = l_mobileOfficeInfoRegistRow.getOccupationDiv();
            
            //携帯番号・勤務先情報.所属 = 変更申込情報※.所属
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
            
            //携帯番号・勤務先情報.取引責任者住所1 = 変更申込情報※.取引先責任者住所1
            l_accInfoMobileOfficeInfo.directorAddress1 = l_mobileOfficeInfoRegistRow.getDirectorAddress1();
            
            //携帯番号・勤務先情報.取引責任者住所2 = 変更申込情報※.取引先責任者住所2
            l_accInfoMobileOfficeInfo.directorAddress2 = l_mobileOfficeInfoRegistRow.getDirectorAddress2();
            
            //携帯番号・勤務先情報.取引責任者住所3 = 変更申込情報※.取引先責任者住所3
            l_accInfoMobileOfficeInfo.directorAddress3 = l_mobileOfficeInfoRegistRow.getDirectorAddress3();
            
            //携帯番号・勤務先情報.取引責任者生年月日　@年号 = 変更申込情報※.取引先責任者生年月日年号
            l_accInfoMobileOfficeInfo.directorEraBorn = l_mobileOfficeInfoRegistRow.getDirectorEraBorn();
            
            //携帯番号・勤務先情報.取引責任者生年月日 = 変更申込情報※.取引先責任者生年月日
            l_accInfoMobileOfficeInfo.directorBornDate = l_mobileOfficeInfoRegistRow.getDirectorBornDate();
            
            //携帯番号・勤務先情報.取引責任者会社直通番号 = 変更申込情報※.取引先責任者会社直通番号
            l_accInfoMobileOfficeInfo.directorCorpDirect = l_mobileOfficeInfoRegistRow.getDirectorCorpTelephone();
            
            //携帯番号・勤務先情報.その他連絡先(携帯、自宅等) = 変更申込情報※.その他連絡先(携帯、自宅等)
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
            	l_accInfoMobileOfficeInfo.experienceFlag1 = l_mobileOfficeInfoRegistRow.getExperienceFlag1() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（２） = 変更申込情報※.投資経験の有無（２）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag2IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag2 = l_mobileOfficeInfoRegistRow.getExperienceFlag2() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（３） = 変更申込情報※.投資経験の有無（３）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag3IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag3 = l_mobileOfficeInfoRegistRow.getExperienceFlag3() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（４） = 変更申込情報※.投資経験の有無（４）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag4IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag4 = l_mobileOfficeInfoRegistRow.getExperienceFlag4() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（５） = 変更申込情報※.投資経験の有無（５）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag5IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag5 = l_mobileOfficeInfoRegistRow.getExperienceFlag5() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（６） = 変更申込情報※.投資経験の有無（６）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag6IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag6 = l_mobileOfficeInfoRegistRow.getExperienceFlag6() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（７） = 変更申込情報※.投資経験の有無（７）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag7IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag7 = l_mobileOfficeInfoRegistRow.getExperienceFlag7() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（８） = 変更申込情報※.投資経験の有無（８）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag8IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag8 = l_mobileOfficeInfoRegistRow.getExperienceFlag8() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（９） = 変更申込情報※.投資経験の有無（９）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag9IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag9 = l_mobileOfficeInfoRegistRow.getExperienceFlag9() + "";
            }
            
            //携帯番号・勤務先情報.投資経験の有無（１０） = 変更申込情報※.投資経験の有無（１０）
            if (!l_mobileOfficeInfoRegistRow.getExperienceFlag10IsNull())
            {
            	l_accInfoMobileOfficeInfo.experienceFlag10 = l_mobileOfficeInfoRegistRow.getExperienceFlag10() + "";
            }
            
            //携帯番号・勤務先情報.投資経験（１） = 変更申込情報※.投資経験（１）
            l_accInfoMobileOfficeInfo.experienceDiv1 = l_mobileOfficeInfoRegistRow.getExperienceDiv1();
            
            //携帯番号・勤務先情報.投資経験（２） = 変更申込情報※.投資経験（２）
            l_accInfoMobileOfficeInfo.experienceDiv2 = l_mobileOfficeInfoRegistRow.getExperienceDiv2();
            
            //携帯番号・勤務先情報.投資経験（３） = 変更申込情報※.投資経験（３）
            l_accInfoMobileOfficeInfo.experienceDiv3 = l_mobileOfficeInfoRegistRow.getExperienceDiv3();
            
            //携帯番号・勤務先情報.投資経験（４） = 変更申込情報※.投資経験（４）
            l_accInfoMobileOfficeInfo.experienceDiv4 = l_mobileOfficeInfoRegistRow.getExperienceDiv4();
            
            //携帯番号・勤務先情報.投資経験（５） = 変更申込情報※.投資経験（５）
            l_accInfoMobileOfficeInfo.experienceDiv5 = l_mobileOfficeInfoRegistRow.getExperienceDiv5();
            
            //携帯番号・勤務先情報.投資経験（６） = 変更申込情報※.投資経験（６）
            l_accInfoMobileOfficeInfo.experienceDiv6 = l_mobileOfficeInfoRegistRow.getExperienceDiv6();
            
            //携帯番号・勤務先情報.投資経験（７） = 変更申込情報※.投資経験（７）
            l_accInfoMobileOfficeInfo.experienceDiv7 = l_mobileOfficeInfoRegistRow.getExperienceDiv7();
            
            //携帯番号・勤務先情報.投資経験（８） = 変更申込情報※.投資経験（８）
            l_accInfoMobileOfficeInfo.experienceDiv8 = l_mobileOfficeInfoRegistRow.getExperienceDiv8();
            
            //携帯番号・勤務先情報.投資経験（９） = 変更申込情報※.投資経験（９）
            l_accInfoMobileOfficeInfo.experienceDiv9 = l_mobileOfficeInfoRegistRow.getExperienceDiv9();
            
            //携帯番号・勤務先情報.投資経験（１０） = 変更申込情報※.投資経験（１０）
            l_accInfoMobileOfficeInfo.experienceDiv10 = l_mobileOfficeInfoRegistRow.getExperienceDiv10();
            
            //携帯番号・勤務先情報.取引の種類（１） = 変更申込情報※.取引の種類（１）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.interest1 = l_mobileOfficeInfoRegistRow.getInterestFlag1() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（２） = 変更申込情報※.取引の種類（２）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.interest2 = l_mobileOfficeInfoRegistRow.getInterestFlag2() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（３） = 変更申込情報※.取引の種類（３）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.interest3 = l_mobileOfficeInfoRegistRow.getInterestFlag3() + "";
            }

            //携帯番号・勤務先情報.取引の種類（４） = 変更申込情報※.取引の種類（４）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.interest4 = l_mobileOfficeInfoRegistRow.getInterestFlag4() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（５） = 変更申込情報※.取引の種類（５）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.interest5 = l_mobileOfficeInfoRegistRow.getInterestFlag5() + "";
            }

            //携帯番号・勤務先情報.取引の種類（６） = 変更申込情報※.取引の種類（６）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.interest6 = l_mobileOfficeInfoRegistRow.getInterestFlag6() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（７） = 変更申込情報※.取引の種類（７）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.interest7 = l_mobileOfficeInfoRegistRow.getInterestFlag7() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（８） = 変更申込情報※.取引の種類（８）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.interest8 = l_mobileOfficeInfoRegistRow.getInterestFlag8() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（９） = 変更申込情報※.取引の種類（９）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.interest9 = l_mobileOfficeInfoRegistRow.getInterestFlag9() + "";
            }
            
            //携帯番号・勤務先情報.取引の種類（１０） = 変更申込情報※.取引の種類（１０）
            if (!l_mobileOfficeInfoRegistRow.getInterestFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.interest10 = l_mobileOfficeInfoRegistRow.getInterestFlag10() + "";
            }
            
            //携帯番号・勤務先情報.口座開設の動機@ = 変更申込情報※.口座開設の動機@
            l_accInfoMobileOfficeInfo.appliMotivatDiv = l_mobileOfficeInfoRegistRow.getAppliMotivatDiv();
            
            //携帯番号・勤務先情報.口座開設の動機@の詳細 = 変更申込情報※.口座開設の動機@の詳細
            l_accInfoMobileOfficeInfo.appliMotivatDetail = l_mobileOfficeInfoRegistRow.getAppliMotivatDivDetail();
            
            //携帯番号・勤務先情報.現在利用している証券会社 = 変更申込情報※.現在利用している証券会社
            l_accInfoMobileOfficeInfo.useInstitutionDiv = l_mobileOfficeInfoRegistRow.getUseInstitutionDiv();
            
            //携帯番号・勤務先情報.インターネット取引区分 = 変更申込情報※.インターネット取引区分
            l_accInfoMobileOfficeInfo.internetTradeDiv = l_mobileOfficeInfoRegistRow.getInternetTradeDiv();
            
            //携帯番号・勤務先情報.紹介支店 = 変更申込情報※.紹介支店取引区分
            l_accInfoMobileOfficeInfo.introduceBranch = l_mobileOfficeInfoRegistRow.getIntroduceBranchCode();
            
            //1.8) プロパティセット
            //　@レスポンスデータ.変更後情報
            //－変更申込情報がある場合（get携帯番号・勤務先情報変更申込() != null）、生成した変更後情報（：携帯番号・勤務先情報）。        
            l_response.changedMobileOfficeInfo = l_accInfoMobileOfficeInfo;            
        }
                    
        //－変更申込情報がない場合（get携帯番号・勤務先情報変更申込() == null）、null。
        else
        {
            
            l_response.changedMobileOfficeInfo = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate変更申込)<BR>
     * 携帯番号・勤務先情報変更申込確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（携帯番号・勤務先情報変更申込）validate変更申込」参照。 <BR>
     * ===============================================     <BR>
     *          シーケンス図 : お客様情報（携帯番号・勤務先情報変更申込）validate変更申込<BR>
     *          具体位置　@　@ : 1.2 変更項目無の場合（is変更項目（）==FALSE）、例外をスローする。<BR>
     *          class           : WEB3BusinessLayerException<BR>
     *          tag             : BUSINESS_ERROR_02688<BR>  
     * ===============================================<BR>
     * <BR>
     * ===============================================     <BR>
     *          シーケンス図 : お客様情報（携帯番号・勤務先情報変更申込）validate変更申込             <BR>
     *          具体位置     :  1.9  判定確認中の場合（is判定確認中() == true）、 <BR>
     *                                     例外をスローする。                     <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01167                    <BR>  
     * ===============================================     <BR>
     *  <BR>
     * @@param l_request - お客様情報携帯番号・勤務先情報変更申込確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413FEECA0293
     */
    protected WEB3AccInfoMobileOfficeRegistConfirmResponse validateRegist(WEB3AccInfoMobileOfficeRegistConfirmRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateRegist(WEB3AccinfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)is変更項目(携帯番号・勤務先情報, 携帯番号・勤務先情報)
        //変更前情報と変更後情報の比較を行い、
        //変更項目が存在するかを判定する。
        //[is変更項目()に指定する引数]
        //変更前情報：リクエストデータ.変更前情報
        //変更後情報：リクエストデータ.変更後情報
        boolean l_blnIsChangedItem = 
            WEB3AccInfoMobileOfficeInfoRegist.isChangedItem(
                l_request.preMobileOfficeInfo, 
                l_request.changedMobileOfficeInfo);

        //1.2)変更項目無の場合（is変更項目（）==FALSE）、例外をスローする。
        //分岐フロー  
        //変更項目無の場合（is変更項目（）==FALSE）、  
        //「変更項目無しエラー」例外をスローする。 
        if (!l_blnIsChangedItem)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "項目が変更されていないです。");
        }
        
        //1.3) validate( )
        l_request.validate();
               
        //1.4) validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.5) get顧客( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //1.6) is連絡先優先順位
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.7) get携帯番号・勤務先情報変更申込(顧客)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
         
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.8) is判定確認中( )
            boolean l_blnIsDeciding = l_accInfoMobileOfficeInfoRegist.isDeciding();
        
            //1.9) 判定確認中の場合（is判定確認中() == true）、例外をスローする
            if(l_blnIsDeciding)
            {
            
                log.error(" 判定確認中です");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01167,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 判定確認中です");            
            }
        }
        
        //1.10) createResponse( )
        WEB3AccInfoMobileOfficeRegistConfirmResponse l_response = 
            (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit変更申込)<BR>
     * 携帯番号・勤務先情報変更申込完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（携帯番号・勤務先情報変更申込）submit変更申込」参照。 <BR>
     *  ===============================================     <BR>
     *          シーケンス図 : お客様情報（携帯番号・勤務先情報変更申込）submit変更申込             <BR>
     *          具体位置     :  1.7  判定確認中の場合（is判定確認中() == true）、 <BR>
     *                                     例外をスローする。                     <BR>
     *          class           : WEB3BusinessLayerException                <BR>
     *          tag              : BUSINESS_ERROR_01167                    <BR>
     * ===============================================     <BR> 
     * <BR>
     * @@param l_request - お客様情報携帯番号・勤務先情報変更申込完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413FEECA02A3
     */
    protected WEB3AccInfoMobileOfficeRegistCompleteResponse submitRegist(WEB3AccInfoMobileOfficeRegistCompleteRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " submitRegist(WEB3AccinfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3) get顧客( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        
        //1.4) is連絡先優先順位
        WEB3AccInfoMobileOfficeInfoRegist.isContactPriority(l_mainAccount, l_request.changedMobileOfficeInfo);
        
        //1.5) getCommonOrderValidator( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        //1.6)validate取引パスワード()
        OrderValidationResult l_validationResult = 
            l_gentradeOrderValidator.validateTradingPassword(
                getTrader(),
                getSubAccount(), 
                l_request.password
                );
        if(l_validationResult.getProcessingResult().isFailedResult())
        {
            
            log.debug(" チェックエラーの場合はを例外をスローする");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " チェックエラーの場合はを例外をスローする");
        }
        
        //1.7) get携帯番号・勤務先情報変更申込(顧客)
        WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.getMobileOfficeInfoRegist(l_mainAccount);
        
        //1.10) 変更申込情報がある場合（get携帯番号・勤務先情報変更申込() != null）、既存申込情報を無効にする。
        if(l_accInfoMobileOfficeInfoRegist != null)
        {
            
            //1.8) is判定確認中( )
            boolean l_blnIsDeciding = l_accInfoMobileOfficeInfoRegist.isDeciding();
        
            //1.9) 判定確認中の場合（is判定確認中() == true）、例外をスローする。
            if(l_blnIsDeciding)
            {
            
                log.error(" 判定確認中です");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01167,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 判定確認中です");
            }
            
            //1.10.1) getDataSourceObject( )
            MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = 
                new MobileOfficeInfoRegistParams((MobileOfficeInfoRegistRow)l_accInfoMobileOfficeInfoRegist.getDataSourceObject());
            
            //1.10.2) doUpdateQuery(PrimaryKey, Map)
            l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.TRUE);
            l_mobileOfficeInfoRegistParams.setLastUpdater(l_mainAccount.getAccountCode());
            l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();              
                l_queryProcessor.doUpdateQuery(l_mobileOfficeInfoRegistParams);             
            }
            catch (DataFindException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました! ", l_ex);
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
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                
                log.error(" DBへのアクセスに失敗しました! ", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
        }
        
        //1.11) createNew携帯番号・勤務先情報変更申込(顧客, 携帯番号・勤務先情報, String)
        WEB3AccInfoMobileOfficeInfoRegist l_createNewMobileOfficeInfoRegist =
            WEB3AccInfoMobileOfficeInfoRegist.createNewMobileOfficeInfoRegist(
                l_mainAccount,
                l_request.changedMobileOfficeInfo,
                l_mainAccount.getAccountCode());
      
        //1.12) getDataSourceObject( )
        MobileOfficeInfoRegistRow l_mobileOfficeInfoRegistRow = 
            (MobileOfficeInfoRegistRow)l_createNewMobileOfficeInfoRegist.getDataSourceObject();
            
        //1.13) doInsertQuery(Row)
        try
        {
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_mobileOfficeInfoRegistRow);
        }
        catch (DataFindException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました! ", l_ex);
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            
            log.error(" DBへのアクセスに失敗しました! ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }   
        
        //1.14) createResponse( )
        WEB3AccInfoMobileOfficeRegistCompleteResponse l_response = 
            (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
