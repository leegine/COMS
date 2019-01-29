head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoPasswordChangeAccountCsv;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoPasswordChangeAccountDownloadService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl.class);   
        
    /**
     * @@roseuid 418F3A06005D
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * 暗証番号変更顧客ダウンロード処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報暗証番号変更顧客<BR>
     * 問合せﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報暗証番号変更顧客<BR>
     * ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロード画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報暗証番号変更顧客<BR>
     * ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロードファ@イル()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70318
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoPasswordChangeAccountInquiryRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoPasswordChangeAccountInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoPasswordChangeAccountDownloadRequest)
        {
            l_response = getDownloadScreen((WEB3AdminAccInfoPasswordChangeAccountDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest)
        {
            l_response = getDownloadFile((WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 暗証番号変更顧客ダウンロード問合せ画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（暗証番号変更顧客ＤＬ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号変更顧客問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70338
     */
    protected WEB3AdminAccInfoPasswordChangeAccountInquiryResponse getInputScreen(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者の権限チェックを行う。 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
               
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoPasswordChangeAccountInquiryResponse l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_request.createResponse();
        
        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        
        //前営業日：　@TradingSystem.getSystemTimestamp()の前営業日
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //前日：　@TradingSystem.getSystemTimestamp()の前日
        l_response.previousDate = WEB3DateUtility.addDay(l_tsCurrentDate, -1);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロード画面)<BR>
     * 暗証番号変更顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（暗証番号変更顧客ＤＬ）getダウンロード画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70357
     */
    protected WEB3AdminAccInfoPasswordChangeAccountDownloadResponse getDownloadScreen(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者の権限チェックを行う。 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
        
        //部店権限をチェックする。 
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //証券会社コードを取得する。 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //ダウンロード対象データを取得する。
        WEB3AccInfoPasswordChangeAccountInfo[] l_passwordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //証券会社コード
                l_request.branchCode,       //部店コード[]
                l_request.startDate,        //開始日
                l_request.endDate,          //終了日
                l_request.sortKeys          //ソートキー
                ); 
                
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoPasswordChangeAccountDownloadResponse l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_request.createResponse();
        
        //レスポンスデータプロパティにパスワード変更顧客情報一覧，総ページ数，総レコード数，表示ページ番号をセットする。
        int l_intpageIndex = Integer.parseInt(l_request.pageIndex); 
        int l_intpageSize = Integer.parseInt(l_request.pageSize); 
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_passwordChangeAccountInfoes, l_intpageIndex, l_intpageSize); 

        //レスポンスデータ.パスワード変更顧客情報一覧：　@getダウンロードデータ()の戻り値のうち、表示対象行(fromIndex～toIndex)の要素。
        l_response.passwordChangeAccountList = (WEB3AccInfoPasswordChangeAccountInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoPasswordChangeAccountInfo.class);

        //レスポンスデータ.総ページ数：　@（総レコード数 / リクエストデータ.ページ内表示行数）※計算結果は小数点以下1位を切り上げた整数値とする。
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();

        //レスポンスデータ.総レコード数：　@getダウンロードデータ()の戻り値.length()
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        
        //レスポンスデータ.表示ページ番号：　@（toIndex / リクエストデータ.ページ内表示行数）※計算結果は小数点以下1位を切り上げた整数値とする。
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * 暗証番号変更顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（暗証番号変更顧客ＤＬ）getダウンロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8BC70376
     */
    protected WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse getDownloadFile(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者の権限チェックを行う。 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
        
        //部店権限をチェックする。 
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //証券会社コードを取得する。 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //ダウンロード対象データを取得する。
        WEB3AccInfoPasswordChangeAccountInfo[] l_passwordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //証券会社コード
                l_request.branchCode,       //部店コード[]
                l_request.startDate,        //開始日
                l_request.endDate,          //終了日
                l_request.sortKeys          //ソートキー
                ); 
 
        WEB3AdminAccInfoPasswordChangeAccountCsv l_passwordChangeAccountCsv = new WEB3AdminAccInfoPasswordChangeAccountCsv();
        
        //取得したダウンロードデータ（getダウンロードデータ()の戻り値）各要素ごとのLOOP
        for (int i = 0; i < l_passwordChangeAccountInfoes.length; i++)
        {
            //明細行を追加する。 
            int l_intLineNum = l_passwordChangeAccountCsv.addRow();
            
            //証券会社コードを明細行にセットする。 
            l_passwordChangeAccountCsv.setInstitutionCode(l_intLineNum, l_passwordChangeAccountInfoes[i].institutionCode);

            //部店コードを明細行にセットする。 
            l_passwordChangeAccountCsv.setBranchCode(l_intLineNum, l_passwordChangeAccountInfoes[i].branchCode);

            //顧客コードを明細行にセットする。 
            l_passwordChangeAccountCsv.setAccountCode(l_intLineNum, l_passwordChangeAccountInfoes[i].accountCode);
            
            //顧客名を明細行にセットする。 
            l_passwordChangeAccountCsv.setAccountName(l_intLineNum, l_passwordChangeAccountInfoes[i].accountName);

            //暗証番号更新日を明細行にセットする。 
            l_passwordChangeAccountCsv.setPasswordUpdatedDate(l_intLineNum, l_passwordChangeAccountInfoes[i].updateDate);

            //暗証番号更新者コードを明細行にセットする。 
            l_passwordChangeAccountCsv.setPasswordUpdaterCode(l_intLineNum, l_passwordChangeAccountInfoes[i].updaterCode);            
        }
        
        //CSVファ@イル行を取得する。
        String[] l_strCscFileLines = l_passwordChangeAccountCsv.getCsvFileLines();
        
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
 
        //レスポンスデータ.ダウンロードファ@イル：　@getCSVファ@イル行()の戻り値
        l_response.downloadFile = l_strCscFileLines;
        
        //レスポンスデータ.現在日時：　@TradingSystem.getSystemTimestamp()
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードデータ)<BR>
     * 顧客マスタテーブルより、ダウンロード対象データを取得する。<BR>
     * <BR>
     * １）　@顧客マスタテーブル検索<BR>
     * 　@顧客マスタテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@顧客マスタ.証券会社コード = 証券会社コード And<BR>
     * 　@顧客マスタ.部店コード in （部店コード[0]，部店コード[1]，，） And <BR>
     * ※ 引数の部店コード[]の要素を列挙する。<BR>
     * 　@顧客マスタ.取引パスワード更新日時 >= 開始日　@And<BR>
     * 　@顧客マスタ.取引パスワード更新日時 < 終了日の翌日<BR>
     * <BR>
     * 　@[取得順（order by）]<BR>
     * 　@※　@引数のソートキー.キー項目が示す項目／順序にて取得する。<BR>
     * 　@ソートキー.キー項目に対応する顧客マスタの項目は以下の通りとする。<BR>
     * <BR>
     * 　@（ソートキー.キー項目 == 部店コード）：　@顧客マスタ.部店コード<BR>
     * 　@（ソートキー.キー項目 == 顧客コード）：　@顧客マスタ.口座コード<BR>
     * 　@（ソートキー.キー項目 == 更新日）：　@顧客マスタ.取引パスワード更新日時<BR>
     * <BR>
     * ２）　@顧客暗証番号情報一覧List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@顧客メールアドレス情報生成<BR>
     * 　@１）で取得した各行オブジェクト（：顧客マスタParams）毎に、<BR>
     * ３－１）～３－２）の処理を行う。<BR>
     * <BR>
     * 　@３－１）　@顧客メールアドレス情報を生成し、以下の通りプロパティをセットする。<BR>
     * 　@　@顧客暗証番号情報.証券会社コード = 顧客マスタ行.証券会社コード<BR>
     * 　@　@顧客暗証番号情報.部店コード = 顧客マスタ行.部店コード<BR>
     * 　@　@顧客暗証番号情報.顧客コード = 顧客マスタ行.口座コードの左6byte<BR>
     * 　@　@顧客暗証番号情報.顧客名 = 顧客マスタ行.名前（苗字）　@<BR>
     * ※顧客名（漢字）として使用<BR>
     * 　@　@顧客暗証番号情報.更新日 = 顧客マスタ行.取引パスワード更新日時<BR>
     * 　@　@顧客暗証番号情報.更新者コード = (*1)<BR>
     * <BR>
     * 　@　@　@(*1) 更新者コード<BR>
     * 　@　@　@（顧客マスタ行.取引パスワード更新者コード == 顧客マスタ行.口座コード）の場合、<BR>
     * 口座コードの左6byte。<BR>
     * 　@　@　@以外、顧客マスタ行.取引パスワード更新者コード、<BR>
     * <BR>
     * 　@３－２）　@顧客暗証番号情報一覧List（：ArrayList）にオブジェクトを追加する。<BR>
     * 　@　@３－１）で生成したオブジェクトを顧客暗証番号情報一覧List<BR>
     * （：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ４）　@ダウンロードデータ返却<BR>
     * 　@顧客暗証番号情報一覧List（：ArrayList）を配列に変換（toArray()）し、<BR>
     * 返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * 
     * @@param l_datStartDate - 開始日
     * @@param l_datEndDate - 終了日
     * @@param l_sortKeys - ソートキー
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeAccountInfo[]
     * @@roseuid 416B8BC70395
     */
    protected WEB3AccInfoPasswordChangeAccountInfo[] getDownloadData(String l_strInstitutionCode, String[] l_strBranchCodes, Date l_datStartDate, Date l_datEndDate, WEB3AccInfoSortKey[] l_sortKeys)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadData(String, String[], Date, Date, WEB3AccInfoSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //顧客マスタテーブルを以下の条件で検索する。
            //[条件]
            //顧客マスタ.証券会社コード = 証券会社コード And
            //顧客マスタ.部店コード in （部店コード[0]，部店コード[1]，，） And ※ 引数の部店コード[]の要素を列挙する。
            //顧客マスタ.取引パスワード更新日時 >= 開始日　@And
            //顧客マスタ.取引パスワード更新日時 < 終了日の翌日
            
            int l_intBranchCodesCnt = l_strBranchCodes.length;
            
            Object[] l_queryContainer = new Object[l_intBranchCodesCnt + 3];
            String l_strQuery = "institution_code = ? ";
            l_queryContainer[0] = l_strInstitutionCode;            
            
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
                
                    l_queryContainer[i + 1] = l_strBranchCodes[i]; 
                }
                
                l_strQuery += " and branch_code in (" + l_sbQueryBranchCodes.toString() + ")";             
            }
                        
            l_strQuery += " and tr_pwd_last_update_timestamp >= ?  and tr_pwd_last_update_timestamp < ?";
            l_queryContainer[l_intBranchCodesCnt + 1] = l_datStartDate;
            l_queryContainer[l_intBranchCodesCnt + 2] = WEB3DateUtility.addDay(l_datEndDate, 1);
            
            //[取得順（order by）] 
            //（ソートキー.キー項目 == 部店コード）：　@顧客マスタ.部店コード 
            //（ソートキー.キー項目 == 顧客コード）：　@顧客マスタ.口座コード 
            //（ソートキー.キー項目 == 更新日）：　@顧客マスタ.取引パスワード更新日時 
            StringBuffer l_sbOrderBy = new StringBuffer();
            for (int i = 0; i < l_sortKeys.length; i++)
            {
                String l_strKey = null;
                if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_strKey = "account_code";
                }
                else if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_strKey = "branch_code";
                }
                else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_sortKeys[i].keyItem))
                {
                    l_strKey = "tr_pwd_last_update_timestamp";
                }
                
                String l_strAscDesc = null;
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strAscDesc = "asc";
                }
                else
                {
                    l_strAscDesc = "desc";
                }
                
                if (l_strKey != null)
                {
                    if (l_sbOrderBy.length() != 0)
                    {
                        l_sbOrderBy.append(", ");
                    }
                    l_sbOrderBy.append(l_strKey + " " + l_strAscDesc);
                }
            }
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strQuery,
                l_sbOrderBy.toString(),
                null,
                l_queryContainer
                );
               
            //顧客暗証番号情報一覧List（：ArrayList）生成
            List l_lisPsswordChangeAccountInfoes = new ArrayList();
            
            int l_intSize = l_lisRecords.size();            
            for (int i = 0; i < l_intSize; i++)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(i);
                
                //顧客暗証番号情報を生成し、以下の通りプロパティをセットする。
                WEB3AccInfoPasswordChangeAccountInfo l_passwordChangeAccountInfo = new WEB3AccInfoPasswordChangeAccountInfo();
                
                //顧客暗証番号情報.証券会社コード = 顧客マスタ行.証券会社コード
                l_passwordChangeAccountInfo.institutionCode = l_mainAccountRow.getInstitutionCode();
                
                //顧客暗証番号情報.部店コード = 顧客マスタ行.部店コード
                l_passwordChangeAccountInfo.branchCode = l_mainAccountRow.getBranchCode();
                
                //顧客暗証番号情報.顧客コード = 顧客マスタ行.口座コードの左6byte
                l_passwordChangeAccountInfo.accountCode = l_mainAccountRow.getAccountCode().substring(0, 6);
                
                //顧客暗証番号情報.顧客名 = 顧客マスタ行.名前（苗字）　@※顧客名（漢字）として使用
                l_passwordChangeAccountInfo.accountName = l_mainAccountRow.getFamilyName();
                
                //顧客暗証番号情報.更新日 = 顧客マスタ行.取引パスワード更新日時
                l_passwordChangeAccountInfo.updateDate = l_mainAccountRow.getTrPwdLastUpdateTimestamp();
                
                //顧客暗証番号情報.更新者コード = 更新者コード
                //（顧客マスタ行.取引パスワード更新者コード == 顧客マスタ行.口座コード）の場合、口座コードの左6byte。
                //以外、顧客マスタ行.取引パスワード更新者コード、 
                
                if (l_mainAccountRow.getAccountCode().equals(l_mainAccountRow.getTradingPasswordUpdater()))
                {
                    l_passwordChangeAccountInfo.updaterCode = l_mainAccountRow.getAccountCode().substring(0, 6);
                }
                else
                {
                    l_passwordChangeAccountInfo.updaterCode = l_mainAccountRow.getTradingPasswordUpdater();
                }
                
                l_lisPsswordChangeAccountInfoes.add(l_passwordChangeAccountInfo);
            }
            
            WEB3AccInfoPasswordChangeAccountInfo[] l_passwordChangeAccountInfoes =
                new WEB3AccInfoPasswordChangeAccountInfo[l_lisPsswordChangeAccountInfoes.size()];
            l_lisPsswordChangeAccountInfoes.toArray(l_passwordChangeAccountInfoes);
                    
            log.exiting(STR_METHOD_NAME);
            
            return l_passwordChangeAccountInfoes;
            
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
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

    }
}
@
