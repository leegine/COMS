head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAttributeRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountCsv;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator;
import webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3Toolkit;

/**
 * (管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl.class); 
    
    /**
     * @@roseuid 418F3A010177
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * パスワード変更顧客ダウンロード処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報パスワード変更顧客<BR>
     * 問合せﾘｸｴｽﾄの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * 
     * ○ 引数のリクエストデータが、管理者お客様情報パスワード変更顧客<BR>
     * ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロード画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報パスワード変更顧客<BR>
     * ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロードファ@イル()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146D68F0254
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest)
        {
            l_response = getInputScreen((WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest)
        {
            l_response = getDownloadScreen((WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest)
        {
            l_response = getDownloadFile((WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest)l_request);
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
     * パスワード変更顧客ダウンロード問合せ画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（パスワード変更顧客ＤＬ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5ADA032B
     */
    protected WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse getInputScreen(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者の権限チェックを行う。 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_PASSWORD, false);
               
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_request.createResponse();
        
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
     * パスワード変更顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（パスワード変更顧客ＤＬ）getダウンロード画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146D68F0264
     */
    protected WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse getDownloadScreen(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request)";
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
        WEB3AccInfoLoginPasswordChangeAccountInfo[] l_loginPasswordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //証券会社コード
                l_request.branchCode,       //部店コード[]
                l_request.startDate,        //開始日
                l_request.endDate,          //終了日
                l_request.sortKeys          //ソートキー
                ); 
                
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_request.createResponse();
        
        //レスポンスデータプロパティにパスワード変更顧客情報一覧，総ページ数，総レコード数，表示ページ番号をセットする。
        int l_intpageIndex = Integer.parseInt(l_request.pageIndex); 
        int l_intpageSize = Integer.parseInt(l_request.pageSize); 
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_loginPasswordChangeAccountInfoes, l_intpageIndex, l_intpageSize); 

        //レスポンスデータ.パスワード変更顧客情報一覧：　@getダウンロードデータ()の戻り値のうち、表示対象行(fromIndex～toIndex)の要素。
        l_response.loginPasswordChangeAccountList = (WEB3AccInfoLoginPasswordChangeAccountInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoLoginPasswordChangeAccountInfo.class);

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
     * パスワード変更顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（パスワード変更顧客ＤＬ）getダウンロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146D68F0266
     */
    protected WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse getDownloadFile(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request)";
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
        WEB3AccInfoLoginPasswordChangeAccountInfo[] l_loginPasswordChangeAccountInfoes = getDownloadData(
                l_strInstitutionCode,       //証券会社コード
                l_request.branchCode,       //部店コード[]
                l_request.startDate,        //開始日
                l_request.endDate,          //終了日
                l_request.sortKeys          //ソートキー
                ); 
 
        WEB3AdminAccInfoLoginPasswordChangeAccountCsv l_loginPasswordChangeAccountCsv = new WEB3AdminAccInfoLoginPasswordChangeAccountCsv();
        
        //取得したダウンロードデータ（getダウンロードデータ()の戻り値）各要素ごとのLOOP
        for (int i = 0; i < l_loginPasswordChangeAccountInfoes.length; i++)
        {
            //明細行を追加する。 
            int l_intLineNum = l_loginPasswordChangeAccountCsv.addRow();
            
            //証券会社コードを明細行にセットする。 
            l_loginPasswordChangeAccountCsv.setInstitutionCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].institutionCode);

            //部店コードを明細行にセットする。 
            l_loginPasswordChangeAccountCsv.setBranchCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].branchCode);

            //顧客コードを明細行にセットする。 
            l_loginPasswordChangeAccountCsv.setAccountCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].accountCode);
            
            //顧客名を明細行にセットする。 
            l_loginPasswordChangeAccountCsv.setAccountName(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].accountName);

            //パスワード更新日を明細行にセットする。 
            l_loginPasswordChangeAccountCsv.setLoginPasswordUpdatedDate(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].updateDate);

            //パスワード更新者コードを明細行にセットする。 
            l_loginPasswordChangeAccountCsv.setLoginPasswordUpdaterCode(l_intLineNum, l_loginPasswordChangeAccountInfoes[i].updaterCode);
            
        }
        
        //CSVファ@イル行を取得する。
        String[] l_strCscFileLines = l_loginPasswordChangeAccountCsv.getCsvFileLines();
        
        //レスポンスデータを生成する。 
        WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
 
        //レスポンスデータ.ダウンロードファ@イル：　@getCSVファ@イル行()の戻り値
        l_response.downloadFile = l_strCscFileLines;
        
        //レスポンスデータ.現在日時：　@TradingSystem.getSystemTimestamp()
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードデータ)<BR>
     * ログイン属性テーブル，ログインテーブル，顧客マスタテーブルより、<BR>
     * ダウンロード対象データを取得する。<BR>
     * <BR>
     * １）　@ログイン属性テーブル検索<BR>
     * 　@ログイン属性テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@ログイン属性.ログイン属性名 = ログイン属性名.前回パスワード変更日付<BR>
     * （LAST_PASSWORDCHANGE_DATE）　@And<BR>
     * 　@ログイン属性.ログイン属性値 >= 開始日※　@And<BR>
     * 　@ログイン属性.ログイン属性値 < 終了日の翌日※<BR>
     * <BR>
     * 　@※ 開始日，終了日の日付書式は以下の通り。時間部分は、<BR>
     * 0時0分0秒で編集する。<BR>
     * 　@YYYY.MM.DD HH24:MI:SS<BR>
     * <BR>
     * ２）　@パスワード変更顧客情報一覧List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@パスワード変更顧客情報生成<BR>
     * 　@１）で取得した各行オブジェクト（：ログイン属性Params）毎に、<BR>
     * ３－１）～３－４）の処理を行う。<BR>
     * <BR>
     * 　@３－１）　@顧客属性取得<BR>
     * 　@　@ログイン属性行.ログインＩＤに該当する行をログインテーブルより取得する。<BR>
     * 　@　@取得したログイン行.口座ＩＤに該当する顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@顧客が取得できなかった場合、または、<BR>
     * 　@　@取得した顧客の証券会社コードが引数の証券会社コードに一致しない場合，<BR>
     * 　@　@または、引数の部店コード[]に含まれない部店コードの場合は、<BR>
     * 　@　@該当要素に関して３－２），３－３），３－４）の処理を行わない。（continue;）<BR>
     * <BR>
     * 　@３－２）　@ログインＩＤに該当するログイン属性取得<BR>
     * 　@　@OpLoginAdminService.getLoginAttributes()にて、<BR>
     * 　@　@ログイン属性行.ログインＩＤに該当する、前回パスワード変更日付以外の<BR>
     * ログイン属性（：Map）を取得する。<BR>
     * <BR>
     * 　@３－３）　@パスワード変更顧客情報を生成し、以下の通りプロパティをセットする。<BR>
     * 　@　@パスワード変更顧客情報.証券会社コード = 顧客.証券会社コード<BR>
     * 　@　@パスワード変更顧客情報.部店コード = 顧客.部店コード<BR>
     * 　@　@パスワード変更顧客情報.顧客コード = 顧客.get表示顧客コード()<BR>
     * 　@　@パスワード変更顧客情報.顧客名 = 顧客.名前（苗字）　@<BR>
     * ※顧客名（漢字）として使用<BR>
     * 　@　@パスワード変更顧客情報.更新日 = <BR>
     * 　@　@　@前回パスワード変更日付（LAST_PASSWORDCHANGE_DATE）に<BR>
     * 対応するログイン属性値※<BR>
     * 　@　@パスワード変更顧客情報.更新者コード = <BR>
     * 　@　@　@前回パスワード更新者コード（LAST_PASSWORDCHANGE_UPDATER）<BR>
     * に対応するログイン属性値※<BR>
     * 　@　@　@但し、（ログイン属性値 == 顧客コード）の場合、<BR>
     * 顧客.get表示顧客コード()。<BR>
     * <BR>
     * 　@　@※ ３－２）で取得したログイン属性（：Map）のログイン属性値。<BR>
     * <BR>
     * 　@３－４）　@パスワード変更顧客情報一覧List（：ArrayList）にオブジェクトを<BR>
     * 追加する。<BR>
     * 　@　@３－３）で生成したオブジェクトをパスワード変更顧客情報一覧List<BR>
     * （：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ４）　@返却値生成<BR>
     * 　@パスワード変更顧客情報一覧List（：ArrayList）を、<BR>
     * WEB3ArraysUtility.sort()にてsortする。 <BR>
     * 　@sort後の配列を返却する。<BR>
     * <BR>
     * 　@[sort()に指定する引数]<BR>
     * 　@Object[]：　@パスワード変更顧客情報一覧List（：ArrayList）.toArray()<BR>
     * 　@Comparator[]：　@ ※<BR>
     * <BR>
     * 　@　@※　@引数のソートキー.キー項目が示すComparatorオブジェクトを配列の<BR>
     * 順序にて指定する。<BR>
     * 　@　@ﾊﾟｽﾜｰﾄﾞ変更顧客情報.部店コードComparator(ソートキー.昇順／降順)<BR>
     * 　@　@ﾊﾟｽﾜｰﾄﾞ変更顧客情報.顧客コードComparator(ソートキー.昇順／降順)<BR>
     * 　@　@ﾊﾟｽﾜｰﾄﾞ変更顧客情報.更新日Comparator(ソートキー.昇順／降順)<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * 
     * @@param l_datStartDate - 開始日
     * @@param l_datEndDate - 終了日
     * @@param l_sortKeys - ソートキー
     * @@return webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo[]
     * @@roseuid 4146D68F0268
     */
    protected WEB3AccInfoLoginPasswordChangeAccountInfo[] getDownloadData(String l_strInstitutionCode, String[] l_strBranchCodes, Date l_datStartDate, Date l_datEndDate, WEB3AccInfoSortKey[] l_sortKeys)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadData(String, String[], Date, Date, WEB3AccInfoSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //ログイン属性テーブル検索
            //[条件] 
            //ログイン属性.ログイン属性名 = ログイン属性名.前回パスワード変更日付（LAST_PASSWORDCHANGE_DATE）　@And 
            //ログイン属性.ログイン属性値 >= 開始日※　@And 
            //ログイン属性.ログイン属性値 < 終了日の翌日※ 
            
            String l_strQuery = "attribute_name = ? ";
            l_strQuery += " and attribute_value >= ?  and attribute_value < ?";
        
            Object[] l_queryContainer = new Object[] {
                WEB3LoginAttributeKeyDef.LAST_PWDCHANGE,
                WEB3DateUtility.formatDate(l_datStartDate, "yyyy.MM.dd HH:mm:ss"),
                WEB3DateUtility.formatDate(WEB3DateUtility.addDay(l_datEndDate, 1), "yyyy.MM.dd HH:mm:ss")
                };
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRecords = l_queryProcessor.doFindAllQuery(
                LoginAttributeRow.TYPE,
                l_strQuery,
                l_queryContainer
                );
                
            List l_lisLoginPasswordChangeAccountInfoes = new ArrayList();
            
            OpLoginAdminService l_opLoginAdminService =
                (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            
            int l_intSize = l_lisRecords.size();            
            for (int i = 0; i < l_intSize; i++)
            {
                LoginAttributeRow l_loginAttrRow = (LoginAttributeRow)l_lisRecords.get(i);
                
                long l_lngLoginId = l_loginAttrRow.getLoginId();
                
                //３－１）　@顧客属性取得 
                WEB3GentradeMainAccount l_mainAccount = null;
                try
                {
                    //ログイン属性行.ログインＩＤに該当する行をログインテーブルより取得する。
                    LoginRow l_loginRow = LoginDao.findRowByPk(l_lngLoginId);
                     
                    //取得したログイン行.口座ＩＤに該当する顧客オブジェクトを取得する。
                    l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_loginRow.getAccountId());
                    
                }
                catch (DataFindException l_ex)
                {
                    l_mainAccount = null;                    
                }
                catch (NotFoundException l_ex)
                {
                    l_mainAccount = null;
                } 
                
                if (l_mainAccount == null 
                    || !l_strInstitutionCode.equals(l_mainAccount.getInstitution().getInstitutionCode())
                    || !WEB3Toolkit.contain(l_strBranchCodes, l_mainAccount.getBranch().getBranchCode()))
                {
                    //顧客が取得できなかった場合、または、 
                    //取得した顧客の証券会社コードが引数の証券会社コードに一致しない場合， 
                    //または、引数の部店コード[]に含まれない部店コードの場合
                    
                    continue;
                }
                
                //　@３－２）　@ログインＩＤに該当するログイン属性取得 
                Map l_loginAttr = l_opLoginAdminService.getLoginAttributes(l_lngLoginId);

                //パスワード更新日時
                Date l_loginPasswordUpdatedDate = null;
                String l_strDate = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE);
                if (l_strDate != null)
                {
                    try
                    {
                        l_loginPasswordUpdatedDate = WEB3PasswordUtility.loginAttributeDateFormat.parse(l_strDate);
                    }
                    catch (ParseException l_ex)
                    {
                        log.error("予期しないシステムエラーが発生しました。", l_ex);

                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                }

                MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

                //パスワード変更顧客情報を生成し、以下の通りプロパティをセットする。
                WEB3AccInfoLoginPasswordChangeAccountInfo l_loginPasswordChangeAccountInfo = new WEB3AccInfoLoginPasswordChangeAccountInfo();
                
                //パスワード変更顧客情報.証券会社コード = 顧客.証券会社コード
                l_loginPasswordChangeAccountInfo.institutionCode = l_mainAccountRow.getInstitutionCode();
                
                //パスワード変更顧客情報.部店コード = 顧客.部店コード
                l_loginPasswordChangeAccountInfo.branchCode = l_mainAccountRow.getBranchCode();
                
                //パスワード変更顧客情報.顧客コード = 顧客.get表示顧客コード() 
                l_loginPasswordChangeAccountInfo.accountCode = l_mainAccount.getDisplayAccountCode();
                
                //パスワード変更顧客情報.顧客名 = 顧客.名前（苗字）　@※顧客名（漢字）として使用
                l_loginPasswordChangeAccountInfo.accountName = l_mainAccountRow.getFamilyName();
                
                //パスワード変更顧客情報.更新日 = 前回パスワード変更日付（LAST_PASSWORDCHANGE_DATE）に対応するログイン属性値
                l_loginPasswordChangeAccountInfo.updateDate = l_loginPasswordUpdatedDate;
                 
                //パスワード変更顧客情報.更新者コード = 前回パスワード更新者コード（LAST_PASSWORDCHANGE_UPDATER）に対応するログイン属性値
                //但し、（ログイン属性値 == 顧客コード）の場合、顧客.get表示顧客コード()。 
                String l_strUpdaterCode = (String)l_loginAttr.get(WEB3LoginAttributeKeyDef.LAST_PASSWORDCHANGE_UPDATER);
                if (l_mainAccount.getAccountCode().equals(l_strUpdaterCode))
                {
                    l_loginPasswordChangeAccountInfo.updaterCode = l_mainAccount.getDisplayAccountCode();
                }
                else
                {
                    l_loginPasswordChangeAccountInfo.updaterCode = l_strUpdaterCode;
                }

                l_lisLoginPasswordChangeAccountInfoes.add(l_loginPasswordChangeAccountInfo);
            }
            

            WEB3AccInfoLoginPasswordChangeAccountInfo[] l_loginPasswordChangeAccountInfoes =
                new WEB3AccInfoLoginPasswordChangeAccountInfo[l_lisLoginPasswordChangeAccountInfoes.size()];
            l_lisLoginPasswordChangeAccountInfoes.toArray(l_loginPasswordChangeAccountInfoes);
        
            List l_lisComparators = new ArrayList();
            for (int i = 0; i < l_sortKeys.length; i++)
            {
                if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_lisComparators.add(new WEB3AdminAccInfoLoginPasswordChangeAccountInfoAccountCodeComparator(l_sortKeys[i].ascDesc));                
                }
                else if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_lisComparators.add(new WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator(l_sortKeys[i].ascDesc));
                }
                else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_sortKeys[i].keyItem))
                {
                    l_lisComparators.add(new WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator(l_sortKeys[i].ascDesc));
                }
            }
            Comparator[] l_comparator = new Comparator[l_lisComparators.size()];
            l_lisComparators.toArray(l_comparator);
        
            WEB3ArraysUtility.sort(l_loginPasswordChangeAccountInfoes, l_comparator);
            
            log.exiting(STR_METHOD_NAME);
            
            return l_loginPasswordChangeAccountInfoes;
            
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
