head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 彭巍 (中訊) 新規作成
Revesion History : 2008/08/18 張少傑 (中訊) 仕様変更・モデルNo.241
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster;
import webbroker3.accountinfo.WEB3AdminAccInfoCommissionChangeAccountCsv;
import webbroker3.accountinfo.data.CommissionCourseRegistPK;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.CommissionCourseRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CommissionCourseDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.CommCodeChgMstRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * 
 * @@author　@彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoCommissionRegistAccountDownloadService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl.class);
    
    /**
     * (証券会社コード_リテラ証券)<BR>
     * 定数定義プロパティ　@証券会社コード　@”リテラ証券”<BR>
     */
    private static final String INSTITUTION_CODE_RETELA_SECURITIES = "07";
    
    /**
     * (証券会社コード_岩井証券)<BR>
     * 定数定義プロパティ　@証券会社コード　@”岩井証券”<BR>
     */
    private static final String INSTITUTION_CODE_IWAI_SECURITIES = "0E";
    
    /**
     * @@roseuid 418F3A0401E4
     */
    public WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * 手数料変更申込顧客ダウンロード処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料変更申込顧客<BR>
     * ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料変更申込顧客<BR>
     * ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロード画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報手数料変更申込顧客<BR>
     * ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロードファ@イル()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAB54012C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄデータの場合
        if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest)
        {  
            l_response = 
                this.getInputScreen(
                    (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest)
                        l_request);
        }
        //引数のリクエストデータが、 管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータの場合
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)
        {
            l_response = 
                this.getDownloadScreen(
                    (WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)
                        l_request);
        }
        
        //      引数のリクエストデータが、管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータの場合
        else if(l_request instanceof WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)
        {
            l_response = 
                this.getDownloadFile(
                    (WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)
                        l_request);
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
     * 手数料変更申込顧客ダウンロード問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（手数料変更申込顧客ＤＬ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5DE902BD
     */
    protected WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse getInputScreen(
        WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
          
        //validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse(
            l_strInstitutionCode, WEB3CommisionProductCodeDef.LISTING_STOCK);
        
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)l_request.createResponse();
            
        //TradingSystem.getSystemTimestamp()
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_timestamp);
        l_calendar.add(Calendar.MONTH, 1);
        l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));
        
        l_response.trialStartDate = WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロード画面)<BR>
     * 手数料変更申込顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（手数料変更申込顧客ＤＬ）getダウンロード画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAB860217
     */
    protected WEB3AdminAccInfoCommissionChangeAccountDownloadResponse getDownloadScreen(
        WEB3AdminAccInfoCommissionChangeAccountDownloadRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //getダウンロードデータ(String, Date, 管理者)
        WEB3AccInfoCommissionChangeAccountInfo[] l_commissionChangeAccountInfo =this.getDownloadData(
            l_strInstitutionCode, 
            l_request.trialStartDate, 
            l_administrator,
            false);

        //createResponse( )
        WEB3AdminAccInfoCommissionChangeAccountDownloadResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)l_request.createResponse();
            
        //レスポンスデータプロパティに変更申込顧客一覧，総ページ数，総レコード数，表示ページ番号をセットする。            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//ページ内表示行数
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//要求ページ番号
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_commissionChangeAccountInfo, l_intPageIndex, l_intPageSize);
                    
        //変更申込顧客一覧
        l_response.commissionCourseChangeList = 
            (WEB3AccInfoCommissionChangeAccountInfo[])l_pageIndexInfo.getArrayReturned(
                WEB3AccInfoCommissionChangeAccountInfo.class);
                   
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
     * (getダウンロードファ@イル)<BR>
     * 手数料変更申込顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（手数料変更申込顧客ＤＬ）getダウンロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAB860226
     */
    protected WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse getDownloadFile(
        WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest l_request) 
            throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getDownloadFile(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_COMMISSION, false);
        
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //getダウンロードデータ(String, Date, 管理者)
        WEB3AccInfoCommissionChangeAccountInfo[] l_commissionChangeAccountInfo =this.getDownloadData(
            l_strInstitutionCode, 
            l_request.trialStartDate, 
            l_administrator,
            true);
        int l_intSize = 0;    
        if(l_commissionChangeAccountInfo != null)  
        {
            l_intSize = l_commissionChangeAccountInfo.length;
        }   
        //1.7メールアドレスCSV( )
        WEB3AdminAccInfoCommissionChangeAccountCsv l_commissionChangeAccountCsv = 
            new WEB3AdminAccInfoCommissionChangeAccountCsv();
        //1.8
        for (int i = 0; i < l_intSize; i++)
        {
            //add明細行( )
            int l_intLineNumber = l_commissionChangeAccountCsv.addRow();
            
            //set部店コード(int, String)
            l_commissionChangeAccountCsv.setBranchCode(
                l_intLineNumber, 
            l_commissionChangeAccountInfo[i].branchCode);
            
            //set顧客コード(int, String)
            l_commissionChangeAccountCsv.setAccountCode(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].accountCode.substring(0, 6));
            
            //set商品コード(int, String)
            l_commissionChangeAccountCsv.setProductCode(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].instrumentsCode);
            
            //set適用開始日(int, Date)
            l_commissionChangeAccountCsv.setAppliStartDate(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].trialStartDate);
                
            //set手数料No.(int, String)
            l_commissionChangeAccountCsv.setCommissionNo(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].commissionNo);
                
            //set徴収率(int, String)
            l_commissionChangeAccountCsv.setChargeRatio(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].collectRate);
                
            //set適用終了日(int, Date)
            l_commissionChangeAccountCsv.setAppliEndDate(
                l_intLineNumber, 
                l_commissionChangeAccountInfo[i].trialEndDate);
                
        }
        
        //1.9getCSVファ@イル行( )
        String[] l_strCvsFileLines = l_commissionChangeAccountCsv.getCsvFileLines();
        
       //レスポンスデータを生成する。 
        WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse l_response = 
            (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)l_request.createResponse();
            
        l_response.downloadFile = l_strCvsFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp(); 
           
        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
    
    /**
     * (getダウンロードデータ)<BR>
     * 委託手数料コース変更申込テーブルより、ダウンロード対象データを取得する。<BR>
     * <BR>
     * １）　@委託手数料コース変更申込テーブル検索<BR>
     * 　@委託手数料コース変更申込テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@委託手数料コース変更申込.証券会社コード = 証券会社コード And<BR>
     * 　@委託手数料コース変更申込.変更申込締切日時  <BR>
     * TradingSystem.getSystemTimestamp()　@And<BR>
     * 　@委託手数料コース変更申込.適用開始日時の+（YYYYMMDD） = <BR>
     * 適用開始日<BR>
     * <BR>
     * 委託手数料コース変更申込.削除フラグ = BooleanEnum.FALSE。 <BR>
     * 　@[取得順（order by）]<BR>
     * 　@委託手数料コース変更申込.部店ＩＤ<BR>
     * 　@substr(委託手数料コース変更申込.口座ＩＤ, 9, 6)<BR>
     * <BR>
     * ２）　@手数料変更顧客情報一覧List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@手数料変更顧客情報生成<BR>
     * 　@１）で取得した各行オブジェクト（：委託手数料コース変更申込Params）毎に、<BR>
     * ３－１）～３－２）の処理を行う。<BR>
     * 　@３－１）　@ダウンロード済フラグを更新する。<BR>
     * 　@　@※ ダウンロード指示の場合（isダウンロード == true）
     * 　@　@該当要素について、QueryProcessor.doUpdateQuery()にて、<BR>
     * 以下の通りDB更新を行う。<BR>
     * <BR>
     * 　@　@委託手数料コース変更申込Params.ダウンロード済フラグ == <BR>
     * BooleanEnum.TRUE<BR>
     * 　@　@委託手数料コース変更申込Params.更新者コード == <BR>
     * 管理者.管理者コード<BR>
     * 　@　@委託手数料コース変更申込Params.更新日時 == <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * 　@　@※ DB更新仕様「手数料変更申込顧客ＤＬ_委託手数料コース<BR>
     * 変更申込テーブル.xls」参照。<BR>
     * <BR>
     * 　@３－２）　@手数料変更顧客情報を生成し、以下の通りプロパティをセットする。<BR>
     * 　@　@手数料変更顧客情報.部店コード = <BR>
     * 委託手数料コース変更申込行.部店コード<BR>
     * 　@　@手数料変更顧客情報.顧客コード = <BR>
     * 委託手数料コース変更申込行.口座ＩＤに該当する顧客.get表示顧客コード()<BR>
     * 　@　@手数料変更顧客情報.商品コード = 手数料商品コード.”上場株式”<BR>
     * 　@　@手数料変更顧客情報.適用開始日 = 適用開始日<BR>
     * 　@　@手数料変更顧客情報.手数料No. = this.get手数料No（）の戻り値。<BR>
     * <BR>
     * 　@　@　@　@[get手数料No()に指定する引数]<BR>
     * 　@　@　@　@部店ID = 委託手数料コース変更申込行.部店ID<BR>
     * 　@　@　@　@手数料商品コード = 委託手数料コース変更申込行.手数料商品コード<BR>
     * 　@　@　@　@適用開始日 = 委託手数料コース変更申込行.適用開始日<BR>
     * 　@　@　@　@手数料コースコード = 委託手数料コース変更申込行.手数料コースコード<BR>  
     * <BR>
     * 　@　@手数料変更顧客情報.徴収率 = 100<BR>
     * 　@　@手数料変更顧客情報.適用終了日 = 日付最大値（HighValue：<BR>
     * 9999/12/31 00：00：00）<BR>
     * <BR>
     * 　@３－３）　@手数料変更顧客情報一覧List（：ArrayList）にオブジェクトを<BR>
     * 追加する。<BR>
     * 　@　@３－２）で生成したオブジェクトを手数料変更顧客情報一覧List<BR>
     * （：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ４）　@ダウンロードデータ返却<BR>
     * 　@手数料変更顧客情報一覧List（：ArrayList）を配列に変換（toArray()）し、<BR>
     * 返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_datAppliStartDate - 適用開始日
     * @@param l_administrator - 管理者オブジェクト
     * @@param l_blnIsDownloadData - isダウンロード
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo[]
     * @@roseuid 414FAB860228
     */
    protected WEB3AccInfoCommissionChangeAccountInfo[] getDownloadData(
        String l_strInstitutionCode, 
        Date l_datAppliStartDate, 
        WEB3Administrator l_administrator,
        boolean l_blnIsDownloadData) throws WEB3BaseException 
        
    {       
        final String STR_METHOD_NAME = " getDownloadData(String, Date, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        try 
        {
            //顧客マスタテーブルを以下の条件で検索する
            StringBuffer l_sbWhere = new StringBuffer();
            
            //委託手数料コース変更申込.証券会社コード = 証券会社コード And<BR>
            l_sbWhere.append(" institution_code = ? ");  
            
            //* 　@委託手数料コース変更申込.変更申込締切日時 < TradingSystem.getSystemTimestamp()<BR>
            l_sbWhere.append(" And regist_end_timestamp < ? "); 
            
            //委託手数料コース変更申込.適用開始日時の+（YYYYMMDD）= 適用開始日 
            l_sbWhere.append(" And to_char(appli_start_datetime,'YYYYMMDD')  = ? "); 
            
            //委託手数料コース変更申込.削除フラグ = BooleanEnum.FALSE。(***************QA:Ft-0033) 
            l_sbWhere.append(" And delete_flag = ? ");
        
            //取得順（order by）
            StringBuffer l_sbOrderBy = new StringBuffer();
            l_sbOrderBy.append(" branch_id,");
            //substr(委託手数料コース変更申込.口座ＩＤ, 9, 6)
            l_sbOrderBy.append(" substr(account_id, 9 , 6) ");
        
            //顧客メールアドレス情報一覧List（：ArrayList）生成ArrayListを生成する。
            List l_listWhere = new Vector();  
            l_listWhere.add(l_strInstitutionCode);
            l_listWhere.add(GtlUtils.getSystemTimestamp());
            l_listWhere.add(WEB3DateUtility.formatDate(l_datAppliStartDate,"yyyyMMdd"));
            l_listWhere.add(BooleanEnum.FALSE);
            
            Object[] l_objWhere = l_listWhere.toArray();

            List l_lisCommissionCourseRegistRow = null;
            QueryProcessor l_QueryProcessor = null; 

            l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisCommissionCourseRegistRow = l_QueryProcessor.doFindAllQuery(
                CommissionCourseRegistRow.TYPE,
                l_sbWhere.toString(),
                l_sbOrderBy.toString(),
                null,
                l_objWhere);
            
            int l_intSize = 0;
            if (l_lisCommissionCourseRegistRow != null && !l_lisCommissionCourseRegistRow.isEmpty())
            {
                l_intSize = l_lisCommissionCourseRegistRow.size();
            
            }

            List lisCommissionChangeAccountInfo = new Vector();
            CommissionCourseRegistParams l_commissionCourseRegistParams = null;
                        
            for (int i = 0; i < l_intSize; i++)
            {
                l_commissionCourseRegistParams = 
                    (CommissionCourseRegistParams) l_lisCommissionCourseRegistRow.get(i);

                CommissionCourseRegistPK l_pk = new CommissionCourseRegistPK();
                l_pk.commission_course_regist_id = 
                    l_commissionCourseRegistParams.getCommissionCourseRegistId();
                Map l_map = new HashMap();
                if (l_blnIsDownloadData)
                {
                    l_map.put("download_flag", BooleanEnum.TRUE);
                    l_map.put("last_updater", l_administrator.getAdministratorCode());
                    l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    
                    QueryProcessor l_queryProcessor;
                    l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_pk, l_map);
                }
                                
                //３－２）　@手数料変更顧客情報を生成し、以下の通りプロパティをセットする
                WEB3AccInfoCommissionChangeAccountInfo l_accInfoCommissionChangeAccountInfo =
                    new WEB3AccInfoCommissionChangeAccountInfo();
                    
                //手数料変更顧客情報.部店コード = 委託手数料コース変更申込行.部店コード
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                AccountManager l_accountManager = l_finApp.getAccountManager();
                                             
                Branch l_banch =
                    l_accountManager.getBranch(l_commissionCourseRegistParams.getBranchId());
                String l_strBranchCode = l_banch.getBranchCode();
                l_accInfoCommissionChangeAccountInfo.branchCode = l_strBranchCode;

                //手数料変更顧客情報.顧客コード = 委託手数料コース変更申込行.口座ＩＤに該当する顧客.get表示顧客コード()
                String l_strAccountCode =
                    ((WEB3GentradeMainAccount)(l_accountManager.getMainAccount(
                        l_commissionCourseRegistParams.getAccountId()))).getDisplayAccountCode();
                l_accInfoCommissionChangeAccountInfo.accountCode = l_strAccountCode;

                //手数料変更顧客情報.商品コード = 手数料商品コード.”上場株式”
                l_accInfoCommissionChangeAccountInfo.instrumentsCode = 
                    WEB3CommisionProductCodeDef.LISTING_STOCK;
                
                //　@手数料変更顧客情報.適用開始日 = 適用開始日
                l_accInfoCommissionChangeAccountInfo.trialStartDate = l_datAppliStartDate;
                
                //手数料変更顧客情報.手数料No. = this.get手数料No（）の戻り値
                l_accInfoCommissionChangeAccountInfo.commissionNo = 
                    this.getCommissionNo(
                    l_commissionCourseRegistParams.getBranchId(),
                    l_commissionCourseRegistParams.getCommProductCode(),
                    l_commissionCourseRegistParams.getAppliStartDatetime(),
                    l_commissionCourseRegistParams.getCommissionCourseDiv());
                
                //手数料変更顧客情報.徴収率 = 100
                l_accInfoCommissionChangeAccountInfo.collectRate = "100";
                
                //手数料変更顧客情報.適用終了日 = 日付最大値（HighValue：9999/12/31 00：00：00）
                l_accInfoCommissionChangeAccountInfo.trialEndDate = 
                    WEB3DateUtility.getDate("9999-12-31 00:00:00", "yyyy-MM-dd HH:mm:ss");
                
                //手数料変更顧客情報一覧List（：ArrayList）にオブジェクトを追加する
                lisCommissionChangeAccountInfo.add(l_accInfoCommissionChangeAccountInfo);   
            }
                     
            WEB3AccInfoCommissionChangeAccountInfo[] l_passwordChangeAccountInfoes =
                new WEB3AccInfoCommissionChangeAccountInfo[lisCommissionChangeAccountInfo.size()];
            lisCommissionChangeAccountInfo.toArray(l_passwordChangeAccountInfoes);
                    
            log.exiting(STR_METHOD_NAME);  
            return l_passwordChangeAccountInfoes;                             

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
        catch (NotFoundException l_ex)
        {
            log.error("__an unexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }      

                 
    }

    /**
     * (get手数料No)<BR>
     * １）手数料コースコード変換マスタを検索する。<BR>   
     * <BR>
     * 　@　@下記の条件で、手数料コースコード変換マスタテーブルを検索する。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@部店ID = 引数.部店ID<BR>
     * 　@　@　@　@手数料商品コード = 引数.手数料商品コード<BR>
     * 　@　@　@　@適用開始日 ≦ 引数.適用開始日<BR>
     * 　@　@　@　@手数料コースコード = 引数.手数料コースコード<BR>
     * <BR>
     * 　@　@※上記の条件でレコードが取得できなかった場合は、下記の条件で再検索する。<BR>
     * 　@　@　@　@レコードが取得できない場合は例外をスローする。<BR>
     * 　@　@　@　@class  : WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag    : BUSINESS_ERROR_00398    <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@　@部店ID = 引数.部店ID<BR>
     * 　@　@　@　@手数料商品コード = 引数.手数料商品コード<BR>
     * 　@　@　@　@適用開始日 ≦ 引数.適用開始日<BR>
     * 　@　@　@　@手数料コースコード = "99"<BR>　@
     * <BR>
     * ２）取得した手数料コースコード変換マスタオブジェクト.手数料Noを返却する。<BR>
     * @@param l_lngBranchId - 部店ID
     * @@param l_strCommissionProductCode - 手数料商品コード
     * @@param l_tsAppliStartDate - 適用開始日
     * @@param l_strCommissionCourseCode - 手数料コースコード<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4150DF4E0280
     */
    protected String getCommissionNo(long l_lngBranchId, String l_strCommissionProductCode,
        Timestamp l_tsAppliStartDate, String l_strCommissionCourseCode) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "getCommissionNo(long, String, Timestamp, String)";
        log.entering(STR_METHOD_NAME); 

        //１） レコードを取得
        //手数料コースコード変換マスタテーブルから以下の条件のレコードを取得する。 
        //[条件] 
        StringBuffer l_strWhere = new StringBuffer();
        //部店ID = 引数.部店ID
        l_strWhere.append(" branch_id = ? ");
        //手数料商品コード = 引数.手数料商品コード
        l_strWhere.append(" and comm_product_code = ? ");
        //適用開始日 ≦ 引数.適用開始日
        l_strWhere.append(" and appli_start_date <= ? ");

        String l_strAppliStartDate =
            WEB3DateUtility.formatDate(l_tsAppliStartDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //手数料コースコード = 引数.手数料コースコード
        l_strWhere.append(" and commission_course_div = ? ");

        Object[] l_objCommCodeChgMsts = {
            new Long(l_lngBranchId),
            l_strCommissionProductCode,
            l_strAppliStartDate,
            l_strCommissionCourseCode};

        List l_lisCommCodeChgMstRows = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCommCodeChgMstRows = l_queryProcessor.doFindAllQuery(
                CommCodeChgMstRow.TYPE,
                l_strWhere.toString(),
                l_objCommCodeChgMsts);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }

        //上記の条件でレコードが取得できなかった場合は、下記の条件で再検索する。
        //レコードが取得できない場合は例外をスローする。
        if (l_lisCommCodeChgMstRows == null || 
            l_lisCommCodeChgMstRows.isEmpty())
        {
            Object[] l_objCommCodeChgMstVars = {
                new Long(l_lngBranchId),
                l_strCommissionProductCode,
                l_strAppliStartDate,
                WEB3CommissionCourseDivDef.OTHER};
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisCommCodeChgMstRows = l_queryProcessor.doFindAllQuery(
                    CommCodeChgMstRow.TYPE,
                    l_strWhere.toString(),
                    l_objCommCodeChgMstVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            if (l_lisCommCodeChgMstRows == null || 
                l_lisCommCodeChgMstRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("該当するデータが存在しません。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398, 
                    this.getClass().getName() + "."  + STR_METHOD_NAME,
                    "該当するデータが存在しません。");
            }
        }

        //２）取得した手数料コースコード変換マスタオブジェクト.手数料Noを返却する
        CommCodeChgMstRow l_commCodeChgMstRow = (CommCodeChgMstRow)l_lisCommCodeChgMstRows.get(0);
        String l_strCommissionNo = l_commCodeChgMstRow.getCommissionNo();
        log.exiting(STR_METHOD_NAME);
        return l_strCommissionNo;
    }
}
@
