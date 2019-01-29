head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽImpl(WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽ実装クラス<BR>
 * 
 * @@author　@彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoCommissionRegistAccountInquiryService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl.class);
    
    /**
     * @@roseuid 418F3A07032C
     */
    public WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl() 
    {
     
    }
    
    /**
     * 手数料変更申込顧客問合せ表示処理を行う。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料変更申込顧客<BR>
     * 問合せ入力ﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。<BR> 
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料変更申込顧客<BR>
     * 問合せﾘｸｴｽﾄの場合 <BR>
     * 　@－get変更申込一覧()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4151406101E1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者お客様情報手数料変更申込顧客問合せ入力ﾘｸｴｽﾄデータの場合
        if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報手数料変更申込顧客問合せﾘｸｴｽﾄデータの場合
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)
        {
            l_response = this.getRegistList((WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)l_request);
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
     * 手数料変更申込顧客問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（手数料変更申込顧客問合せ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客問合せ入力ﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B616A01D0
     */
    protected WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse getInputScreen(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)l_request.createResponse();
    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    

    /**
     * (get変更申込一覧)<BR>
     * 手数料変更申込顧客問合せ表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者お客様情報（手数料変更申込顧客問合せ）get変更申込一覧」参照。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客問合せﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse
     * @@roseuid 4151406101F1
     */
    protected WEB3AdminAccInfoCommissionChangeAccountInquiryResponse getRegistList(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRegistList(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        //validate()
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate権限(String, boolean)   
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //get顧客
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        try
        {
            l_gentradeMainAccount = l_gentradeAccountManager.getMainAccount(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            if (WEB3ErrorCatalog.SYSTEM_ERROR_80005.equals(l_ex.getErrorInfo()))
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
            else
            {
                throw l_ex;
            }
        }
        
            
            
        // validate部店権限(String)
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //create検索条件文字列( )
        String l_strQueryString = this.createQueryString();
        
        //create検索条件データコンテナ(String, String, String)
        String[] l_strQueryContainer = 
            this.createQueryContainer(
                l_strInstitutionCode, 
                l_request.branchCode, 
                l_request.accountCode);
        
        //createソート条件( )
        String l_strSortCond = this.createSortCond();
        
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
        else if (l_lisCommissionCourseRegist != null)
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
        WEB3AdminAccInfoCommissionChangeAccountInquiryResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)l_request.createResponse();
            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//ページ内表示行数
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//要求ページ番号
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_commissionCourseRegistInfo, l_intPageIndex, l_intPageSize);
                    
        //変更申込顧客一覧
        l_response.changeApplyInfoList = 
            (WEB3AccInfoCommissionCourseChangeInfo[])l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoCommissionCourseChangeInfo.class);
                
        //顧客名（漢字） 
        l_response.accountName = l_gentradeMainAccount.getDisplayAccountName();
        
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
     * ３）　@顧客条件追加 <BR>
     * 　@顧客条件を追加する。<BR>
     * <BR>
     * 　@" and account_id = ? "<BR>
     * <BR>
     * ４）　@削除フラグ条件追加<BR>
     *　@削除フラグ条件を追加する。<BR>
     *<BR>
     *　@" and  delete_flag = 0 "<BR>
     * 5）　@文字列インスタンスを返却 <BR>
     * @@return String
     * @@roseuid 4151434403C6
     */
    protected String createQueryString() 
    {
        final String STR_METHOD_NAME = " createQueryString() ";
        log.entering(STR_METHOD_NAME);
        String l_strSearchCond;
        l_strSearchCond 
            = " institution_code = ? ";
            
        l_strSearchCond
            += " and account_id = ? ";
        l_strSearchCond
            += " and  delete_flag = ? ";
            
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
     * ３）　@顧客コード条件追加 <BR>
     * 　@口座ＩＤ文字列をリストに追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@口座ＩＤ※<BR>
     * <BR>
     * 　@※アカウントマネージャ.get顧客(証券会社コード，部店コード，<BR>
     * 顧客コード).getAccountId()にて取得する。<BR>
     * <BR>
     * ４）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@return String[]
     * @@roseuid 4151434403CA
     */
    protected String[] createQueryContainer(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(String, String, String)";
        log.entering(STR_METHOD_NAME);
        //* １）ArrayListを生成する。<BR>
        List l_lisQueryContainer = new ArrayList();  
        
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        long l_lngAccountId = 
            l_accMgr.getMainAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode).getAccountId();
        String l_strAccountId = l_lngAccountId + "";
        l_lisQueryContainer.add(l_strAccountId);
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
     * 　@委託手数料変更申込テーブル.申込日時（昇順：asc）<BR>
     * @@return String
     * @@roseuid 4164CACA00E7
     */
    protected String createSortCond() 
    {
        final String STR_METHOD_NAME =" createSortCond()";
        log.entering(STR_METHOD_NAME);
        StringBuffer l_strReturn = new StringBuffer();  
        l_strReturn.append("regist_timestamp ASC");

        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();

    }
}
@
