head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客一覧問合せｻｰﾋﾞｽImpl(WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountListInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報手数料変更申込顧客一覧問合せｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報手数料変更申込顧客一覧問合せｻｰﾋﾞｽ実装クラス<BR>
 * 
 * @@author　@彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoCommissionRegistAccountListInquiryService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl.class);
    
    /**
     * @@roseuid 418F3A080138
     */
    public WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl() 
    {
     
    }
    
    /**
     * 手数料変更申込顧客一覧問合せ処理を行う。 <BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料変更申込<BR>
     * 顧客一覧問合せ入力ﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料変更申込<BR>
     * 顧客一覧問合せﾘｸｴｽﾄの場合 <BR>
     * 　@－get変更申込顧客一覧()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41510350032A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾘｸｴｽﾄデータの場合
        if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報手数料変更申込顧客一覧問合せﾘｸｴｽﾄデータの場合
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest)
        {
            l_response = this.getRegistAccountList((WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest)l_request);
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
     * 手数料変更申込顧客一覧問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（手数料変更申込顧客一覧問合せ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse
     * @@roseuid 41510AF8024F
     */
    protected WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse getInputScreen(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        // get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        // get取扱可能委託手数料コース(String, String)
        WEB3AccInfoCommissionCourseMaster[] l_possibleCommissionCourse = 
            WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
                l_strInstitutionCode, WEB3CommisionProductCodeDef.LISTING_STOCK);
        int l_intSize = 0;
        if(l_possibleCommissionCourse != null)  
        {
            l_intSize = l_possibleCommissionCourse.length;
        }        
         
        //管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        
        WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse)l_request.createResponse();
            
        l_response.commissionCourseList =
            new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {

            l_response.commissionCourseList[i] = l_possibleCommissionCourse[i].getCommissionCourseCode();
        }   
         
        Date l_datTrialStartDate = GtlUtils.getSystemTimestamp(); 
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_datTrialStartDate); 
        l_calendar.add(Calendar.MONTH, 1);
        l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));
        l_response.trialStartDate = WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0);        

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get変更申込顧客一覧)<BR>
     * 手数料変更申込顧客一覧表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（手数料変更申込顧客一覧問合せ）get変更申込顧客一覧」参照。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客一覧問合せﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse
     * @@roseuid 415103500339
     */
    protected WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse getRegistAccountList(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRegistAccountList(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        //validate()
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
             
        //validate部店権限(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //create検索条件文字列(String, String[], String, Date)
        String l_strQueryString = this.createQueryString(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.commissionCourse,
            l_request.trialStartDate);
            
       //create検索条件データコンテナ(String, String[], String, Date)
        String[] l_strQueryContainer = this.createQueryContainer(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.commissionCourse,
            l_request.trialStartDate);
            
        //createソート条件( )
        String l_strSortCond = this.createSortCond();
        
        //get委託手数料コース変更申込(String, String[], String)
        List l_lisCommissionCourseRegist = 
            WEB3AccInfoCommissionCourseRegist.getCommissionCourseRegist(
                l_strQueryString,
                l_strQueryContainer,
                l_strSortCond);
 
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseRegistInfo = null;             
        if (l_lisCommissionCourseRegist == null || l_lisCommissionCourseRegist.size() == 0)
        {
            l_commissionCourseRegistInfo = new WEB3AccInfoCommissionCourseChangeInfo[0];  
        } 
        else 
        {
            int l_intSize = l_lisCommissionCourseRegist.size();
            
            WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegist = 
                new WEB3AccInfoCommissionCourseRegist[l_intSize];         
            l_lisCommissionCourseRegist.toArray(l_commissionCourseRegist);
        
            //create株式手数料コース変更申込情報(委託手数料コース変更申込[])        
            WEB3AccInfoCommissionCourseRegistInfoCreatedService 
                l_accInfoCommissionCourseRegistInfoCreatedService = 
                    (WEB3AccInfoCommissionCourseRegistInfoCreatedService) Services.getService(
                        WEB3AccInfoCommissionCourseRegistInfoCreatedService.class);
            l_commissionCourseRegistInfo = 
                l_accInfoCommissionCourseRegistInfoCreatedService.createEquityCommissionCourseRegistInfo(l_commissionCourseRegist);                        
        }  
                        
        //createResponse( )
        WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse)l_request.createResponse();
            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//ページ内表示行数
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//要求ページ番号
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_commissionCourseRegistInfo, l_intPageIndex, l_intPageSize);
        
        //変更申込顧客一覧
        l_response.changeApplyAccountList =             
            (WEB3AccInfoCommissionCourseChangeInfo[])l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoCommissionCourseChangeInfo.class);
  
        //総ページ数
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //(表示ページ番号)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //総レコード数
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + ""; 
        
        log.exiting(STR_METHOD_NAME);       
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値の検索条件文字列インスタンス（：String）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード条件を追加する。<BR>
     * <BR>
     * 　@" institution_code =  ? "<BR>
     * <BR>
     * ３）　@部店条件追加<BR>
     * 　@部店条件を追加する。部店コード[]の要素数分"?"を編集する。<BR>
     * <BR>
     * 　@" and branch_id in (?, ?,,,) "<BR>
     * <BR>
     * ４）　@手数料コースコード条件追加 <BR>
     * 　@手数料コース指定の場合（手数料コースコード != null）、<BR>
     * 手数料コースコード指定文字列を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@" and commission_course_div = ? "<BR>
     * <BR>
     * ５）　@適用開始日条件追加<BR>
     * 　@適用開始日条件を追加する。<BR>
     * <BR>
     * 　@" and appli_start_datetime like ? "<BR>
     * <BR>
     * ６）　@削除フラグ条件追加<BR>
     *　@削除フラグ条件を追加する。<BR>
     *　@" and  delete_flag = 0 "<BR>
     * ７）　@文字列インスタンスを返却 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * 
     * @@param l_strCommissionCourseCode - 手数料コースコード<BR>
     * <BR>
     * 02：　@定額手数料（スタンダード）<BR>
     * 12：　@定額手数料（ハイパーボックス）<BR>
     * 03：　@約定代金合計<BR>
     * 04：　@約定回数<BR>
     * 05：　@一日定額制<BR>
     * 
     * @@param l_datAppliStartDate - 適用開始日
     * @@return String
     * @@roseuid 41510350033B
     */
    protected String createQueryString(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strCommissionCourseCode, Date l_datAppliStartDate) 
    { 
        final String STR_METHOD_NAME = 
            " createQueryString(String, String[], String, Date)";
        log.entering(STR_METHOD_NAME);
        
        //戻り値の検索条件文字列インスタンス（：String）を生成 
        String l_strSearchCond;
        
        //　@証券会社コード条件を追加する。
        l_strSearchCond 
            = " institution_code =  ? ";
            
        //部店条件を追加する。部店コード[]の要素数分"?"を編集する。
        int l_intBranchCodesCnt = l_strBranchCodes.length;
        
        if (l_intBranchCodesCnt > 0)
        {
            StringBuffer l_sbQueryBranchCodes = new StringBuffer();
            
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                if (l_sbQueryBranchCodes.length() != 0)
                {
                    l_sbQueryBranchCodes.append(", ");
                }
                l_sbQueryBranchCodes.append("?");
                
            }                
            l_strSearchCond += " and branch_id in (" + l_sbQueryBranchCodes.toString() + ")";             
        }
       
        //手数料コース指定の場合（手数料コースコード != null）、<BR>
        // 手数料コースコード指定文字列を検索条件文字列に追加する。
        if (l_strCommissionCourseCode != null)
        {
            l_strSearchCond
                += " and commission_course_div = ? "; 
        }
        
        //適用開始日条件を追加する。
        l_strSearchCond
            += " and to_char(appli_start_datetime, 'YYYYMMDD') = ? ";
        //６）　@削除フラグ条件追加
        //  削除フラグ条件を追加する。
        //
        //" and  delete_flag = 0 "
        //l_strSearchCond
        //    += " and delete_flag = 0 ";
        l_strSearchCond
            += " and delete_flag = ? ";
        log.exiting(STR_METHOD_NAME);  
        return l_strSearchCond;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード文字列を追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@証券会社コード<BR>
     * <BR>
     * ３）　@部店条件追加<BR>
     * 　@部店コード[]に該当する部店ＩＤをすべて追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]　@<BR>
     * 　@部店ＩＤ※<BR>
     * <BR>
     * 　@※アカウントマネージャ.getBranch(証券会社，部店コード)にて取得する。<BR>
     * 　@※証券会社オブジェクトは、アカウントマネージャ.getInstitution(証券会社コード)<BR>
     * にて取得する。<BR>
     * <BR>
     * ４）　@手数料コースコード条件追加 <BR>
     * 　@手数料コース指定の場合（手数料コースコード != null）、手数料コースコード<BR>
     * 指定文字列をリストに追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@手数料コースコード<BR>
     * <BR>
     * ５）　@適用開始日条件追加<BR>
     * 　@適用開始日条件を追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@適用開始日のYYYYMMDD + '%'<BR>
     * <BR>
     * ６）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * 
     * @@param l_strCommissionCourseCode - 手数料コースコード<BR>
     * <BR>
     * 02：　@定額手数料（スタンダード）<BR>
     * 12：　@定額手数料（ハイパーボックス）<BR>
     * 03：　@約定代金合計<BR>
     * 04：　@約定回数<BR>
     * 05：　@一日定額制<BR>
     * 
     * 
     * @@param l_datAppliStartDate - 適用開始日
     * @@return String[]
     * @@roseuid 415103500340
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strCommissionCourseCode, 
        Date l_datAppliStartDate)  throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(" +
                "String l_strInstitutionCode, " +
                "String[] l_strBranchCodes, " +
                "String l_strCommissionCourseCode, " +
                "Date l_datAppliStartDate) ";
        log.entering(STR_METHOD_NAME);
                
        //* １）ArrayListを生成する。<BR>
        List l_lisQueryContainer = new ArrayList();  
        
        //証券会社コード文字列を追加する。
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        //部店コード[]に該当する部店ＩＤをすべて追加する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        long l_lngBranchId = 0;
        Institution l_institution;
        try
        {
            l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
            
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                Branch l_branch = l_accMgr.getBranch(l_institution, l_strBranchCodes[i]);
                l_lngBranchId = l_branch.getBranchId();
                String l_strBranchId = l_lngBranchId + "";
                l_lisQueryContainer.add(l_strBranchId);
         
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in 部店条件追加....... ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
      
        // 　@手数料コース指定の場合（手数料コースコード != null）、手数料コースコード<BR>
        // 指定文字列をリストに追加する。
        if (l_strCommissionCourseCode != null)
        {
            l_lisQueryContainer.add(l_strCommissionCourseCode);
        }
        
        //適用開始日条件を追加する
        l_lisQueryContainer.add(WEB3DateUtility.formatDate(l_datAppliStartDate, "yyyyMMdd"));
        
        l_lisQueryContainer.add(Integer.toString(BooleanEnum.FALSE.intValue()));
        
        String[] l_strQueryDataContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryDataContainers;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。 <BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を<BR>
     * 編集する。<BR>
     * <BR>
     * 　@委託手数料変更申込テーブル.部店ＩＤ（昇順：asc）<BR>
     * 　@substr(委託手数料変更申込テーブル.口座ＩＤ, 9, 6)（昇順：asc）<BR>
     * @@return String
     * @@roseuid 4164C934006A
     */
    protected String createSortCond() 
    {
        final String STR_METHOD_NAME =" createSortCond()";
        log.entering(STR_METHOD_NAME);
        StringBuffer l_strReturn = new StringBuffer();  
        
        l_strReturn.append(" branch_id ASC,");
       
        l_strReturn.append(" substr(account_id , 9 , 6) ASC");

        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
    }
}
@
