head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;


import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoCommissionChangeAccountCsv;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * 
 * @@author　@彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl.class);
    
    /**
     * (サービス区分_ハイパーボックス オークション)<BR>
     * 定数定義プロパティ　@サービス区分　@”ハイパーボックス オークション”<BR>
     */
    private static  final String SERVICE_DIV_HYPER_BOX_OPTION = "33";
    
    /**
     * @@roseuid 418F3A0001A5
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl() 
    {
     
    }
    
    /**
     * ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞ処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料<BR>
     * 変更ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料<BR>
     * 変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロード画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料<BR>
     * 変更ﾃﾞｰﾀﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロードファ@イル()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A544008F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄの場合
        if(l_request instanceof WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest)
        {  
            l_response = this.getInputScreen((WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータの場合
        else if(l_request instanceof WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest)
        {
            l_response = this.getDownloadScreen((WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータの場合
        else if(l_request instanceof WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest)
        {
            l_response = this.getDownloadFile((WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest)l_request);
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
     * ハイパーボックス手数料変更ダウンロード入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ＤＬ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 41665B6F0097
     */
    protected WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse getInputScreen(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest)";
        log.entering(STR_METHOD_NAME);
      //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_HYPERBOX_COMMISSION, false);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse l_response = 
            (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロード画面)<BR>
     * ハイパーボックス手数料変更ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ＤＬ）getダウンロード画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A555031F
     */
    protected WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse getDownloadScreen(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate( )
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
          //validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_HYPERBOX_COMMISSION, false);
          
          //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //getダウンロードデータ(String, Date, Date, String, String)
        WEB3AccInfoCommissionChangeAccountInfo[] l_getDownloadData = 
            this.getDownloadData(
                l_strInstitutionCode,
                l_request.trialStartDate,
                l_request.trialEndDate,
                l_request.commissionNo,
                l_request.collectRate
                );
            
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse l_response = 
            (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)l_request.createResponse();

            
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//ページ内表示行数
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//要求ページ番号
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_getDownloadData, l_intPageIndex, l_intPageSize);
            
        l_response.hyperBoxCommissionList = 
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
     * ハイパーボックス手数料変更ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ＤＬ）getダウンロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A5F103CB
     */
    protected WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse getDownloadFile(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_HYPERBOX_COMMISSION, false);
        
        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        WEB3AccInfoCommissionChangeAccountInfo[] l_getDownloadData = 
            this.getDownloadData(
                l_strInstitutionCode,
                l_request.trialStartDate,
                l_request.trialEndDate,
                l_request.commissionNo,
                l_request.collectRate
                );
        int l_intSize = 0;
        if(l_getDownloadData != null)  
        {
            l_intSize = l_getDownloadData.length;
        }          
                
        WEB3AdminAccInfoCommissionChangeAccountCsv l_commissionChangeAccountCsv
            = new WEB3AdminAccInfoCommissionChangeAccountCsv();
        
        for (int i = 0; i < l_intSize; i++)
        {
            int l_intLineNumber = l_commissionChangeAccountCsv.addRow();
            l_commissionChangeAccountCsv.setBranchCode(
                l_intLineNumber, 
                l_getDownloadData[i].branchCode); 
                
            l_commissionChangeAccountCsv.setAccountCode(
                l_intLineNumber, 
                l_getDownloadData[i].accountCode); 
                
            l_commissionChangeAccountCsv.setProductCode(
                l_intLineNumber, 
                l_getDownloadData[i].instrumentsCode); 
                
            l_commissionChangeAccountCsv.setAppliStartDate(
                l_intLineNumber, 
                l_getDownloadData[i].trialStartDate); 
                
            l_commissionChangeAccountCsv.setCommissionNo(
                l_intLineNumber, 
                l_getDownloadData[i].commissionNo);
                
            l_commissionChangeAccountCsv.setChargeRatio(
                l_intLineNumber, 
                l_getDownloadData[i].collectRate);
                
            l_commissionChangeAccountCsv.setAppliEndDate(
                l_intLineNumber, 
                l_getDownloadData[i].trialEndDate);  
        } 
        String[] l_strCvsFileLines = l_commissionChangeAccountCsv.getCsvFileLines(); 
        
        WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse l_response = 
            (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)l_request.createResponse();
            
        l_response.downloadFile = l_strCvsFileLines; 
        l_response.currentDate = GtlUtils.getSystemTimestamp();   
          
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードデータ)<BR>
     * サービス申込登録テーブルより、ダウンロード対象データを取得する。<BR>
     * サービス申込登録一行について、上場株式，店頭株式の2件作成する。<BR>
     * <BR>
     * １）　@サービス申込登録テーブル検索<BR>
     * 　@サービス申込登録テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     *　@サービス申込登録テーブル.証券会社コード = 証券会社コード And<BR>
     *　@サービス申込登録テーブル.サービス区分 = サービス区分_ハイパーボックス オークション※ And<BR>
     *　@サービス申込登録テーブル.有効区分 = 0：有効　@And<BR>
     *　@サービス申込登録テーブル.取消区分 = 0：通常　@And<BR>
     *　@サービス申込登録テーブル.申込抽選区分 in (2：当選/本申込 , 5：自動当選) And<BR>
     *　@サービス申込登録テーブル.適用終了日 ≧ 適用終了日<BR>
     *<BR>
     *　@※サービス区分_ハイパーボックス オークション<BR>
     *　@本サービスクラスに定数定義している値。   <BR> 
     * 　@[取得順（order by）]<BR>
     * 　@サービス申込登録テーブル.部店コード<BR>
     * 　@サービス申込登録テーブル.口座コード<BR>
     * <BR>
     * ２）　@手数料変更顧客情報一覧List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@手数料変更顧客情報生成<BR>
     * 　@１）で取得した各行オブジェクト（：サービス申込登録Params）毎に、<BR>
     * ３－１）～３－３）の処理を行う。<BR>
     * <BR>
     * 　@３－１）　@手数料変更顧客情報を生成し、以下の通りプロパティをセットする。<BR>
     * 　@　@手数料変更顧客情報.部店コード = サービス申込登録行.部店コード<BR>
     * 　@　@手数料変更顧客情報.顧客コード = サービス申込登録行.口座コードの左6byte<BR>
     * 　@　@手数料変更顧客情報.商品コード = 手数料商品コード.”上場株式”<BR>
     * 　@　@手数料変更顧客情報.適用開始日 = 適用開始日<BR>
     * 　@　@手数料変更顧客情報.手数料No. = 手数料No.<BR>
     * 　@　@手数料変更顧客情報.徴収率 = 徴収率<BR>
     * 　@　@手数料変更顧客情報.適用終了日 = 適用終了日<BR>
     * <BR>
     * 　@３－２）　@手数料変更顧客情報一覧List（：ArrayList）にオブジェクトを追加する。<BR>
     * 　@　@３－１）で生成したオブジェクトを手数料変更顧客情報一覧List<BR>
     * （：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ４）　@ダウンロードデータ返却<BR>
     * 　@手数料変更顧客情報一覧List（：ArrayList）を配列に変換（toArray()）し、<BR>
     * 返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_datAppliStartDate - 適用開始日
     * @@param l_datAppliEndDate - 適用終了日
     * @@param l_strCommissionNo - 手数料No.
     * @@param l_strChargeRatio - 徴収率
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionChangeAccountInfo[]
     * @@roseuid 4146ADAF03AC
     */
    protected WEB3AccInfoCommissionChangeAccountInfo[] getDownloadData(
        String l_strInstitutionCode, 
        Date l_datAppliStartDate, 
        Date l_datAppliEndDate, 
        String l_strCommissionNo, 
        String l_strChargeRatio) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadData(String, Date, Date, String, String)";
        log.entering(STR_METHOD_NAME);
       
        StringBuffer l_sbWhere = new StringBuffer();

        //サービス申込登録テーブル.証券会社コード = 証券会社コード And<BR>
        l_sbWhere.append(" institution_code = ? "); 
        
        //サービス申込登録テーブル.サービス区分 = サービス区分_ハイパーボックス <BR>
        //オークション※ And<BR>
        //l_sbWhere.append(" and srv_div = '"+ SERVICE_DIV_HYPER_BOX_OPTION +"'");
        l_sbWhere.append(" and srv_div = ?");  
        
        //サービス申込登録テーブル.有効区分 = 0：有効　@And
        //l_sbWhere.append(" and effective_div = '"+ WEB3EffectiveDivDef.EFFECTIVE +"'");
        l_sbWhere.append(" and effective_div = ?");
        //サービス申込登録テーブル.取消区分 = 0：通常　@And
        l_sbWhere.append(" and cancel_div = ?");
        //l_sbWhere.append(" and cancel_div = '"+ WEB3SrvRegiCancelDivDef.USUAL_DEFAULT +"'");
        // サービス申込登録テーブル.申込抽選区分 in (2：当選/本申込 , 5：自動当選) And
        //l_sbWhere.append(" and appli_lot_div in ('"+ WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI + "', '"+ WEB3AppliLotDivDef.AUTO_ELECTION +"')");
        l_sbWhere.append(" and appli_lot_div in (?,?)");
        
        // サービス申込登録テーブル.適用終了日 ≧ 適用終了日
        l_sbWhere.append("and appli_end_date >= ?");
        //取得順（order by）
        StringBuffer l_sbOrderBy = new StringBuffer();
        l_sbOrderBy.append(" branch_code, ");
        l_sbOrderBy.append(" account_code ");
        
        List l_listWhere = new Vector();      
        l_listWhere.add(l_strInstitutionCode);

        l_listWhere.add(SERVICE_DIV_HYPER_BOX_OPTION);
        //     *　@サービス申込登録テーブル.サービス区分 = サービス区分_ハイパーボックス オークション※ And<BR>
        l_listWhere.add(WEB3EffectiveDivDef.EFFECTIVE);
        //　@サービス申込登録テーブル.有効区分 = 0：有効　@And<BR>
        l_listWhere.add(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);
        //　@サービス申込登録テーブル.取消区分 = 0：通常　@And<BR>
        //　@サービス申込登録テーブル.申込抽選区分 in (2：当選/本申込 , 5：自動当選) And<BR>
        l_listWhere.add(WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI);
        l_listWhere.add(WEB3AppliLotDivDef.AUTO_ELECTION);
        l_listWhere.add(l_datAppliEndDate);
        
        Object[] l_objWhere = l_listWhere.toArray();

        List l_lisSrvRegiApplicationRow = null;
        QueryProcessor l_QueryProcessor = null; 
        try 
        {
            l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisSrvRegiApplicationRow = l_QueryProcessor.doFindAllQuery(
                SrvRegiApplicationRow.TYPE,
                l_sbWhere.toString(),
                l_sbOrderBy.toString(),
                null,
                l_objWhere);
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
        List lisCommissionChangeAccountInfo = new Vector();
        for (int i = 0 ; i <l_lisSrvRegiApplicationRow.size(); i++)
        {
            SrvRegiApplicationRow l_applicationRow = (SrvRegiApplicationRow)l_lisSrvRegiApplicationRow.get(i);
            WEB3AccInfoCommissionChangeAccountInfo l_accInfoCommissionChangeAccountInfo =
                new WEB3AccInfoCommissionChangeAccountInfo();
                
            //* 　@　@手数料変更顧客情報.部店コード = サービス申込登録行.部店コード<BR>
            l_accInfoCommissionChangeAccountInfo.branchCode = l_applicationRow.getBranchCode();
            
            //* 　@　@手数料変更顧客情報.顧客コード = サービス申込登録行.口座コードの左6byte<BR>
            l_accInfoCommissionChangeAccountInfo.accountCode = l_applicationRow.getAccountCode().substring(0, 6);
            
            // * 　@　@手数料変更顧客情報.商品コード = 手数料商品コード.”上場株式”<BR>
            l_accInfoCommissionChangeAccountInfo.instrumentsCode = WEB3CommisionProductCodeDef.LISTING_STOCK;
            
            // * 　@　@手数料変更顧客情報.適用開始日 = 適用開始日<BR>
            l_accInfoCommissionChangeAccountInfo.trialStartDate = l_datAppliStartDate;
            
            // * 　@　@手数料変更顧客情報.手数料No. = 手数料No.<BR>
            l_accInfoCommissionChangeAccountInfo.commissionNo = l_strCommissionNo;
            
            // * 　@　@手数料変更顧客情報.徴収率 = 徴収率<BR>
            l_accInfoCommissionChangeAccountInfo.collectRate = l_strChargeRatio;
            
            //* 　@　@手数料変更顧客情報.適用終了日 = 適用終了日<BR>
            l_accInfoCommissionChangeAccountInfo.trialEndDate = l_datAppliEndDate;
            
            
            //手数料変更顧客情報一覧List（：ArrayList）にオブジェクトを追加する
            lisCommissionChangeAccountInfo.add(l_accInfoCommissionChangeAccountInfo);
 
        }         
              
        //手数料変更顧客情報一覧List（：ArrayList）を配列に変換（toArray()）
        WEB3AccInfoCommissionChangeAccountInfo[] l_accInfoCommissionChangeAccountInfos =
            new WEB3AccInfoCommissionChangeAccountInfo[lisCommissionChangeAccountInfo.size()];
        lisCommissionChangeAccountInfo.toArray(l_accInfoCommissionChangeAccountInfos);
        
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCommissionChangeAccountInfos;
    }
}
@
