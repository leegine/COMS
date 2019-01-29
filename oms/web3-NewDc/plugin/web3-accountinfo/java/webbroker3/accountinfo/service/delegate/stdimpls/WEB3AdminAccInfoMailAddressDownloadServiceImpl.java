head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminAccInfoMailAddressDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
Revesion History : 2010/02/22 張騰宇 (中訊) モデル261 モデル271
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoMailAddressCsv;
import webbroker3.accountinfo.define.WEB3AccountOpenMailFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3MailAssortmentDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.gentrade.data.MailAssortmentRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailAddressDownloadService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressDownloadServiceImpl.class);
    /**
     * @@roseuid 418F3A030148
     */
    public WEB3AdminAccInfoMailAddressDownloadServiceImpl() 
    {
     
    }
    
    /**
     * ﾒｰﾙｱﾄﾞﾚｽ全件ダウンロード処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せﾘｸｴｽﾄの場合 <BR>
　@   *   －get入力画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件<BR>
     * ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロード画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件<BR>
     * ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロードファ@イル()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E3800143
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoMailAddressFileDownloadRequest)
        {
            l_response = this.getDownloadFile((WEB3AdminAccInfoMailAddressFileDownloadRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressDownloadRequest)
        {           
            l_response = this.getDownloadScreen((WEB3AdminAccInfoMailAddressDownloadRequest)l_request) ;
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressInquiryRequest)
        {           
            l_response = this.getInputScreen((WEB3AdminAccInfoMailAddressInquiryRequest)l_request) ;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME
                );            
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * メールアドレス全件ダウンロード入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレス全件ＤＬ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せﾘｸｴｽﾄデータオブジェクト 
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A56ED003D
     */
    protected WEB3AdminAccInfoMailAddressInquiryResponse getInputScreen(WEB3AdminAccInfoMailAddressInquiryRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoMailAddressInquiryRequest)";
        log.entering(STR_METHOD_NAME );
        //1.1getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.2validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        WEB3AdminAccInfoMailAddressInquiryResponse l_response = 
            (WEB3AdminAccInfoMailAddressInquiryResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }    
    /**
     * (getダウンロード画面)<BR>
     * メールアドレス全件ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレス全件ＤＬ）getダウンロード画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E389001A
     */
    protected WEB3AdminAccInfoMailAddressDownloadResponse getDownloadScreen(WEB3AdminAccInfoMailAddressDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadScreen(WEB3AdminAccInfoMailAddressDownloadRequest)";
        log.entering(STR_METHOD_NAME );
        
        //1.1validate( )
        l_request.validate();
        //1.2getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        //1.4validate部店権限(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        //1.5get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get部店コード( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get複数メールアドレス対応実施(
        //部店コード : String, 証券会社コード : String, プリファ@レンス名 : String, プリファ@レンス名の連番 : int)
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_administrator.getInstitution(), l_strBranchCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        String l_strMultiMailAddressFlag = l_branch.getMultiMailAddressEnforcement(
            l_strBranchCode, l_strInstitutionCode, WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG, 1);

        //getダウンロードデータ(String, String[], String, String, String, String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos =
            this.getDownloadData(l_strInstitutionCode, l_request.branchCode, l_request.accountCodeFrom,
                l_request.accountCodeTo, l_request.sendFlag, l_strMultiMailAddressFlag);

        //1.8プロパティセット
        
        //ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        //要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_accountMailAddressInfos, l_intPageIndex, l_intPageSize);
        
        //1.7管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminAccInfoMailAddressDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressDownloadResponse)l_request.createResponse();
        //(表示ページ番号)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //総ページ数
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //総レコード数
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + ""; 
        //明細のリスト
        l_response.accountMailAddressList = 
            (WEB3AccInfoAccountMailAddressInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoAccountMailAddressInfo.class);        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * メールアドレス全件ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレス全件ＤＬ）getダウンロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E389001C
     */
    protected WEB3AdminAccInfoMailAddressFileDownloadResponse getDownloadFile(WEB3AdminAccInfoMailAddressFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAccInfoMailAddressFileDownloadRequest)";
        log.entering(STR_METHOD_NAME );
        //1.1validate( )
        l_request.validate();
        //1.2getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        //1.4validate部店権限(String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        //1.5get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        //get部店コード( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get複数メールアドレス対応実施(部店コード : String, 証券会社コード : String, プリファ@レンス名 : String, プリファ@レンス名の連番 : int)
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = new WEB3GentradeBranch(l_administrator.getInstitution(), l_strBranchCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        String l_strMultiMailAddressFlag = l_branch.getMultiMailAddressEnforcement(
            l_strBranchCode, l_strInstitutionCode, WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG, 1);

        //getダウンロードデータ(String, String[], String, String, String, String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos =
            this.getDownloadData(l_strInstitutionCode, l_request.branchCode, l_request.accountCodeFrom,
                l_request.accountCodeTo, l_request.sendFlag, l_strMultiMailAddressFlag);
        //1.7メールアドレスCSV( )
        WEB3AdminAccInfoMailAddressCsv l_mailAddressCsv = new WEB3AdminAccInfoMailAddressCsv();
        //1.8
        for (int i = 0; i < l_accountMailAddressInfos.length; i++)
        {
            int l_intLineNumber = l_mailAddressCsv.addRow();
            l_mailAddressCsv.setInstitutionCode(l_intLineNumber, l_accountMailAddressInfos[i].institutionCode);
            l_mailAddressCsv.setBranchCode(l_intLineNumber, l_accountMailAddressInfos[i].branchCode);
            l_mailAddressCsv.setAccountCode(l_intLineNumber, l_accountMailAddressInfos[i].accountCode);
            l_mailAddressCsv.setAccountName(l_intLineNumber, l_accountMailAddressInfos[i].accountName);
            l_mailAddressCsv.setMailAddress(l_intLineNumber, l_accountMailAddressInfos[i].mailAddress);
            l_mailAddressCsv.setSendFlag(l_intLineNumber,l_accountMailAddressInfos[i].sendFlag);
            l_mailAddressCsv.setMailAddressUpdatedDate(l_intLineNumber, l_accountMailAddressInfos[i].updateDate);
            l_mailAddressCsv.setMailAddressUpdaterCode(l_intLineNumber, l_accountMailAddressInfos[i].updaterCode);
        }
        //1.9getCSVファ@イル行( )
        String[] l_cvsFileLines = l_mailAddressCsv.getCsvFileLines();
        
        WEB3AdminAccInfoMailAddressFileDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_request.createResponse();
        l_response.downloadFile = l_cvsFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();    
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードデータ)<BR>
     * 顧客マスタテーブルより、ダウンロード対象データを取得する。<BR>
     * <BR>
     * １）　@顧客マスタテーブル又は顧客メールアドレステーブル検索<BR>
     * 　@１－１）　@引数.複数メールアドレス対応実施フラグ！= "２" の場合、以下処理を行う。<BR>
     * 　@顧客マスタテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@顧客マスタ.証券会社コード = 証券会社コード And<BR>
     * 　@顧客マスタ.部店コード in （部店コード[0]，部店コード[1]，，）（※１）　@And<BR>
     * 　@顧客マスタ.口座コード >= 顧客コード（自） And<BR>
     * 　@顧客マスタ.口座コード <= 顧客コード（至） And<BR>
     * 　@顧客マスタ.案内メール送信フラグ = 送信フラグ（※２）<BR>
     * <BR>
     *  ※１　@引数の部店コード[]の要素を列挙する。<BR>
     *  ※２　@送信フラグ = 要 の場合 true<BR>
     * 　@　@　@　@送信フラグ = 不要の場合 false<BR>
     * 　@　@　@　@送信フラグ = 指定なしの場合は検索条件として含まない。<BR>
     * <BR>
     * 　@[取得順（order by）]<BR>
     * 　@顧客マスタ.部店コード<BR>
     * 　@顧客マスタ.口座コード<BR>
     * <BR>
     * 　@１－２）　@引数.複数メールアドレス対応実施フラグ == "２"の場合、以下処理を行う。<BR>
     * 　@　@１－２－１）　@顧客メールアドレステーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@顧客メールアドレス.証券会社コード = 証券会社コード And<BR>
     * 　@　@顧客メールアドレス.部店コード in （部店コード[0]，部店コード[1]，，）（※１）　@And<BR>
     * 　@　@顧客メールアドレス.口座コード >= 顧客コード（自） And    <BR>
     * 　@　@顧客メールアドレス.口座コード <= 顧客コード（至）    <BR>
     * 　@　@※１　@引数の部店コード[]の要素を列挙する。 <BR>
     *  <BR>
     * 　@　@[取得順（order by）]    <BR>
     * 　@　@顧客メールアドレス.部店コード    <BR>
     * 　@　@顧客メールアドレス.口座コード    <BR>
     * 　@　@顧客メールアドレステーブルメールアドレス区分     <BR>
     *  <BR>
     * 　@　@１－２－２）　@　@案内メール送信フラグにより メール種別テーブルを検索する。  <BR>
     *  <BR>
     * 　@　@　@１－２－２－１）　@引数.送信フラグ = 指定なしの場合、２）の処理を行う。 <BR>
     *  <BR>
     * 　@　@　@１－２－２－２）　@引数.送信フラグ = 要 の場合、１－２－１）で取得した顧客メールアドレス行Loop処理を行う。 <BR>
     * 　@　@　@　@メール種別テーブルを以下の条件で検索する。<BR>
     *  <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード = 顧客メールアドレス行.証券会社コード<BR>
     * 　@　@　@部店コード = 顧客メールアドレス行.部店コード<BR>
     * 　@　@　@顧客コード = 顧客メールアドレス行.顧客コード<BR>
     * 　@　@　@メール種別区分=6:案内メール<BR>
     * 　@　@　@レコードを取得できない場合、該当顧客メールアドレス行を削除し、２）の処理を行う。<BR>
     *  <BR>
     * 　@　@　@１－２－２－３）　@引数.送信フラグ = 不要 の場合、１－２－１）で取得した顧客メールアドレス行Loop処理を行う。    <BR>
     * 　@　@　@　@メール種別テーブルを以下の条件で検索する。<BR>
     *  <BR>
     * 　@　@　@　@[条件]<BR>
     * 　@　@　@　@証券会社コード = 顧客メールアドレス行.証券会社コード<BR>
     * 　@　@　@　@部店コード = 顧客メールアドレス行.部店コード<BR>
     * 　@　@　@　@顧客コード = 顧客メールアドレス行.顧客コード<BR>
     * 　@　@　@　@メール種別区分=6:案内メール<BR>
     * 　@　@　@　@レコードを取得できる場合、該当顧客メールアドレス行を削除し、２）の処理を行う。<BR>
     * <BR>
     * ２）　@顧客メールアドレス情報一覧List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@顧客メールアドレス情報生成<BR>
     * <BR>
     * 　@３－１）　@引数.複数メールアドレス対応実施フラグ！= "２" の場合、<BR>
     * 　@１）で取得した各行オブジェクト（：顧客マスタParams）毎に、４）の処理を行う。<BR>
     * 　@顧客メールアドレス情報を生成し、以下の通りプロパティをセットする。<BR>
     * <BR>
     * 　@顧客メールアドレス情報.証券会社コード = 顧客マスタ行.証券会社コード<BR>
     * 　@顧客メールアドレス情報.部店コード = 顧客マスタ行.部店コード<BR>
     * 　@顧客メールアドレス情報.顧客コード = 顧客マスタ行.口座コードの左6byte<BR>
     * 　@顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）　@※顧客名（漢字）として使用<BR>
     * 　@顧客メールアドレス情報.メールアドレス = 顧客マスタ行.emailアドレス<BR>
     * 　@顧客メールアドレス情報.更新日 = 顧客マスタ行.emailアドレス更新日時<BR>
     * 　@顧客メールアドレス情報.更新者コード = (*1)<BR>
     * 　@顧客メールアドレス情報.送信フラグ = （*2）<BR>
     * <BR>
     * 　@(*1) 更新者コード<BR>
     * 　@（顧客マスタ行.emailアドレス更新者コード == 顧客マスタ行.口座コード）の場合、口座コードの左6byte。<BR>
     * 　@以外、顧客マスタ行.emailアドレス更新者コード。<BR>
     * <BR>
     * 　@(*2)送信フラグ<BR>
     * 　@顧客マスタ行.案内メール送信フラグ = 要 の場合 1<BR>
     * 　@顧客マスタ行.案内メール送信フラグ = 不要の場合 0<BR>
     * <BR>
     * 　@３－２） 引数.複数メールアドレス対応実施フラグ == "２"の場合、<BR>
     * 　@１）で取得した各行オブジェクト（：顧客メールアドレスParams）毎に、４）の処理を行う。 <BR>
     * 　@顧客メールアドレス情報を追加、以下の通りプロパティをセットする。    <BR>
     *  <BR>
     * 　@顧客メールアドレス情報.証券会社コード = 顧客メールアドレス行.証券会社コード        <BR>
     * 　@顧客メールアドレス情報.部店コード = 顧客メールアドレス行.部店コード    <BR>
     * 　@顧客メールアドレス情報.顧客コード = 顧客メールアドレス行.口座コードの左6byte     <BR>
     * 　@顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）　@※顧客名（漢字）として使用       <BR>
     * 　@顧客メールアドレス情報.メールアドレス = 顧客メールアドレス行.メールアドレス    <BR>
     * 　@顧客メールアドレス情報.更新日 = 顧客メールアドレス行.メールアドレス更新日時        <BR>
     * 　@顧客メールアドレス情報.更新者コード = 顧客メールアドレス.メールアドレス更新者コード        <BR>
     * 　@顧客メールアドレス情報.送信フラグ = （*2）        <BR>
     *      <BR>
     * 　@(*2)送信フラグ       <BR>
     * 　@取得した顧客メールアドレス行.証券会社コード、部店コード、顧客コード<BR>
     *   及びメール種別区分が”6:案内メール”であることに該当するメール種別オブジェクトを取得する。     <BR>
     * 　@レコードが存在 の場合 1       <BR>
     * 　@レコードが存在しない場合 0<BR>
     * <BR>
     * ４）　@顧客メールアドレス情報一覧List（：ArrayList）にオブジェクトを追加する。<BR>
     * 　@３）で生成したオブジェクトを顧客メールアドレス情報一覧List（：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ５）　@ダウンロードデータ返却<BR>
     * 　@顧客メールアドレス情報一覧List（：ArrayList）を配列に変換（toArray()）し、返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * @@param l_strAccountCodeFrom - String 顧客コード（自）
     * @@param l_strAccountCodeTo - String 顧客コード（至）
     * @@param l_strSendFlag - String 送信フラグ
     * @@param l_strMultiMailAddressFlag - 複数メールアドレス対応実施フラグ
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo[]
     * @@roseuid 4147E389002A
     */
    protected WEB3AccInfoAccountMailAddressInfo[] getDownloadData
	(String l_strInstitutionCode, String[] l_strBranchCodes, String l_strAccountCodeFrom,
	String l_strAccountCodeTo, String l_strSendFlag,
    String l_strMultiMailAddressFlag) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getDownloadData(String, String[], String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null; 

        List l_listResults = new ArrayList();

        int l_intBranchCodesCnt = l_strBranchCodes.length;
        StringBuffer l_sbQueryBranchCodes = new StringBuffer();
        for (int i = 0; i < l_intBranchCodesCnt; i++)
        {
            if (l_sbQueryBranchCodes.length() != 0)
            {
                l_sbQueryBranchCodes.append(", ");
            }
            l_sbQueryBranchCodes.append("?");
            
        }           

        //引数.複数メールアドレス対応実施フラグ！= "２" の場合、以下処理を行う
        //顧客マスタテーブルを以下の条件で検索する。
        if (!WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strMultiMailAddressFlag))
        {
            List l_lisRecords = null;
            //顧客マスタテーブルを以下の条件で検索する
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");        
            //部店条件を追加する。部店コード[]の要素数分"?"を編集する。
            
            if (l_intBranchCodesCnt > 0)
            {             
                l_sbWhere.append(" and branch_code in (" + l_sbQueryBranchCodes.toString() + ")") ;             
            }  
            //顧客マスタ.口座コード >= 顧客コード（自） And 
            l_sbWhere.append(" and SubStr(account_code,0,6) >= ?");
    		//顧客マスタ.口座コード <= 顧客コード（至） And 
            l_sbWhere.append(" and SubStr(account_code,0,6) <= ?");
    		//顧客マスタ.案内メール送信フラグ = 送信フラグ（※２）
            if (WEB3AccountOpenMailFlagDef.sendFlag.equals(l_strSendFlag)
            		|| WEB3AccountOpenMailFlagDef.unSendFlag.equals(l_strSendFlag))
            {
                l_sbWhere.append(" and information_mail_flag = ?");
            }

            //取得順（order by）
            StringBuffer l_sbOrderBy = new StringBuffer();
            l_sbOrderBy.append(" branch_code, ");
            l_sbOrderBy.append(" account_code ");

            //顧客メールアドレス情報一覧List（：ArrayList）生成ArrayListを生成する。
            List l_listWhere = new ArrayList();
            l_listWhere.add(l_strInstitutionCode);
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                l_listWhere.add(l_strBranchCodes[i]);
            }
            l_listWhere.add(l_strAccountCodeFrom);
            l_listWhere.add(l_strAccountCodeTo);
            //送信フラグ = 要 の場合 true
            //送信フラグ = 不要の場合 false
            //送信フラグ = 指定なしの場合は検索条件として含まない。
            if (WEB3AccountOpenMailFlagDef.sendFlag.equals(l_strSendFlag))
            {
            	l_listWhere.add(BooleanEnum.TRUE);
            }
            else if (WEB3AccountOpenMailFlagDef.unSendFlag.equals(l_strSendFlag))
            {
            	l_listWhere.add(BooleanEnum.FALSE);
            }

            Object[] l_objWhere = l_listWhere.toArray();
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    MainAccountRow.TYPE,
                    l_sbWhere.toString(),
                    l_sbOrderBy.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataQueryException l_ex) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            for (int i = 0 ; i <l_lisRecords.size(); i++)
            {
                MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(i);
                WEB3AccInfoAccountMailAddressInfo l_accountMailAddressInfo = new WEB3AccInfoAccountMailAddressInfo();

                //顧客メールアドレス情報.証券会社コード = 顧客マスタ行.証券会社コード
                l_accountMailAddressInfo.institutionCode = l_mainAccountRow.getInstitutionCode();

                //顧客メールアドレス情報.部店コード = 顧客マスタ行.部店コード
                l_accountMailAddressInfo.branchCode = l_mainAccountRow.getBranchCode();

                //顧客メールアドレス情報.顧客コード = 顧客マスタ行.口座コードの左6byte
                l_accountMailAddressInfo.accountCode = l_mainAccountRow.getAccountCode().substring(0, 6);

                //顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）
                l_accountMailAddressInfo.accountName = l_mainAccountRow.getFamilyName();

                //顧客メールアドレス情報.メールアドレス = 顧客マスタ行.emailアドレス
                l_accountMailAddressInfo.mailAddress = l_mainAccountRow.getEmailAddress();
                //顧客メールアドレス情報.更新日 = 顧客マスタ行.emailアドレス更新日時
                l_accountMailAddressInfo.updateDate = l_mainAccountRow.getEmailLastUpdatedTimestamp();

                //顧客メールアドレス情報.更新者コード = (*1)
                if (l_mainAccountRow.getEmailLastUpdater().equals(l_mainAccountRow.getAccountCode()))
                {
                    l_accountMailAddressInfo.updaterCode = l_mainAccountRow.getAccountCode().substring(0, 6);
                }
                else
                {
                    l_accountMailAddressInfo.updaterCode = l_mainAccountRow.getEmailLastUpdater();
                }
                //顧客マスタ行.案内メール送信フラグ = 要 の場合 1 
                //顧客マスタ行.案内メール送信フラグ = 不要の場合 0 
                if (BooleanEnum.TRUE.equals(l_mainAccountRow.getInformationMailFlag()))
                {
                    l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.sendFlag;
                }
                else if (BooleanEnum.FALSE.equals(l_mainAccountRow.getInformationMailFlag()))
                {
                    l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.unSendFlag;
                }

                //で生成したオブジェクトを顧客メールアドレス情報一覧List（：ArrayList）に追加（add）する。
                l_listResults.add(l_accountMailAddressInfo);
            }
        }
        //引数.複数メールアドレス対応実施フラグ == "２"の場合、以下処理を行う。
        //　@顧客メールアドレステーブルとメール種別テーブルを以下の条件で検索する。
        else
        {
            
            StringBuffer l_sbWhereAccountMailAddress = new StringBuffer();
            //顧客メールアドレス.証券会社コード = 証券会社コード
            l_sbWhereAccountMailAddress.append(" institution_code = ? ");
            //顧客メールアドレス.部店コード in （部店コード[0]，部店コード[1]，，）（※１）
            if (l_intBranchCodesCnt > 0)
            {
                l_sbWhereAccountMailAddress.append(" and branch_code in (" + l_sbQueryBranchCodes.toString() + ")") ;
            }
            //顧客メールアドレス.口座コード >= 顧客コード（自）  And
            l_sbWhereAccountMailAddress.append(" and SubStr(account_code,0,6) >= ?");
            //顧客メールアドレス.口座コード <= 顧客コード（至） And
            l_sbWhereAccountMailAddress.append(" and SubStr(account_code,0,6) <= ?");

            //取得順（order by）
            StringBuffer l_sbOrderByAccountMailAddress = new StringBuffer();
            l_sbOrderByAccountMailAddress.append(" branch_code, ");
            l_sbOrderByAccountMailAddress.append(" account_code, ");
            l_sbOrderByAccountMailAddress.append(" address_div ");

            //顧客メールアドレス情報一覧List（：ArrayList）生成ArrayListを生成する。
            List l_listWhereAccountMailAddress = new ArrayList();

            l_listWhereAccountMailAddress.add(l_strInstitutionCode);
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {
                l_listWhereAccountMailAddress.add(l_strBranchCodes[i]);
            }
            l_listWhereAccountMailAddress.add(l_strAccountCodeFrom);
            l_listWhereAccountMailAddress.add(l_strAccountCodeTo);

            Object[] l_objWhereAccountMailAddress = l_listWhereAccountMailAddress.toArray();
            List l_lisAccountMailAddressRecords = new ArrayList();
            try 
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisAccountMailAddressRecords = l_queryProcessor.doFindAllQuery(
                    AccountMailAddressRow.TYPE,
                    l_sbWhereAccountMailAddress.toString(),
                    l_sbOrderByAccountMailAddress.toString(),
                    null,
                    l_objWhereAccountMailAddress);
            }
            catch (DataQueryException l_ex) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataNetworkException l_ex) 
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            int l_intSize = 0;
            if (l_lisAccountMailAddressRecords != null && l_lisAccountMailAddressRecords.size() != 0)
            {
                l_intSize = l_lisAccountMailAddressRecords.size();
            }
            AccountMailAddressRow[] l_accountMailAddressRows = new AccountMailAddressRow[l_intSize];
            l_lisAccountMailAddressRecords.toArray(l_accountMailAddressRows);
            int l_intFlag = 0;
            for (int i = 0; i < l_intSize; i++)
            {
                AccountMailAddressRow l_accountMailAddressRow = l_accountMailAddressRows[i];
                
                //メール種別テーブルを検索する
                StringBuffer l_sbWhereMailAssortment = new StringBuffer();
                l_sbWhereMailAssortment.append(" institution_code = ? ");
                l_sbWhereMailAssortment.append(" and branch_code = ? ");
                l_sbWhereMailAssortment.append(" and account_code = ?");
                l_sbWhereMailAssortment.append(" and mail_assortment_div = ? ");
                List l_listWhereMailAssortment = new ArrayList();
                l_listWhereMailAssortment.add(l_accountMailAddressRow.getInstitutionCode());
                l_listWhereMailAssortment.add(l_accountMailAddressRow.getBranchCode());
                l_listWhereMailAssortment.add(l_accountMailAddressRow.getAccountCode());
                l_listWhereMailAssortment.add(WEB3MailAssortmentDivDef.GUIDE_MAIL);
                Object[] l_objWhereMailAssortment = l_listWhereMailAssortment.toArray();

                List l_lisMailAssortmentRecords = new ArrayList();
                try 
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                    l_lisMailAssortmentRecords = l_queryProcessor.doFindAllQuery(
                        MailAssortmentRow.TYPE,
                        l_sbWhereMailAssortment.toString(),
                        l_objWhereMailAssortment);
                }
                catch (DataQueryException l_ex) 
                {
                    log.error("DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex) 
                {
                    log.error("DBへのアクセスに失敗しました");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                if ((WEB3AccountOpenMailFlagDef.sendFlag.equals(l_strSendFlag)
                        && (l_lisMailAssortmentRecords == null || l_lisMailAssortmentRecords.size() == 0))
                    || (WEB3AccountOpenMailFlagDef.unSendFlag.equals(l_strSendFlag)
                        && (l_lisMailAssortmentRecords != null && l_lisMailAssortmentRecords.size() != 0)))
                {
                    l_lisAccountMailAddressRecords.remove(l_intFlag);
                }
                else
                {
                    l_intFlag++;
                    WEB3AccInfoAccountMailAddressInfo l_accountMailAddressInfo = new WEB3AccInfoAccountMailAddressInfo();

                    //顧客メールアドレス情報.証券会社コード = 顧客メールアドレス行.証券会社コード
                    l_accountMailAddressInfo.institutionCode = l_accountMailAddressRow.getInstitutionCode();

                    //顧客メールアドレス情報.部店コード = 顧客メールアドレス行.部店コード
                    l_accountMailAddressInfo.branchCode = l_accountMailAddressRow.getBranchCode();

                    //顧客メールアドレス情報.顧客コード = 顧客メールアドレス行.口座コードの左6byte
                    l_accountMailAddressInfo.accountCode = l_accountMailAddressRow.getAccountCode().substring(0, 6);

                    //顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）
                    MainAccountRow l_accountRow = null;
                    try
                    {
                        l_accountRow = MainAccountDao.findRowByInstitutionCodeBranchCodeAccountCode(
                            l_accountMailAddressRow.getInstitutionCode(),
                            l_accountMailAddressRow.getBranchCode(),
                            l_accountMailAddressRow.getAccountCode());
                    }
                    catch (DataFindException l_ex)
                    {
                        log.error("テーブルに該当するデータがありません。");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataNetworkException l_ex)
                    {
                        log.error("DBへのアクセスに失敗しました");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error("DBへのアクセスに失敗しました");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    l_accountMailAddressInfo.accountName = l_accountRow.getFamilyName();

                    //顧客メールアドレス情報.メールアドレス = 顧客メールアドレス行.メールアドレス
                    l_accountMailAddressInfo.mailAddress = l_accountMailAddressRow.getEmailAddress();
                    //顧客メールアドレス情報.更新日 = 顧客メールアドレス行.メールアドレス更新日時
                    l_accountMailAddressInfo.updateDate = l_accountMailAddressRow.getEmailLastUpdatedTimestamp();

                    //顧客メールアドレス情報.更新者コード = 顧客メールアドレス.メールアドレス更新者コード
                    l_accountMailAddressInfo.updaterCode = l_accountMailAddressRow.getEmailLastUpdater();

                    //取得した顧客メールアドレス行.証券会社コード、部店コード、顧客コード
                    //及びメール種別区分が”6:案内メール”であることに該当するメール種別オブジェクトを取得する。
                    //レコードが存在 の場合 1
                    //レコードが存在しない場合 0
                    if ( l_lisMailAssortmentRecords != null && l_lisMailAssortmentRecords.size() != 0)
                    {
                        l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.sendFlag;
                    }
                    else if (l_lisMailAssortmentRecords == null || l_lisMailAssortmentRecords.size() == 0)
                    {
                        l_accountMailAddressInfo.sendFlag = WEB3AccountOpenMailFlagDef.unSendFlag;
                    }

                    //生成したオブジェクトを顧客メールアドレス情報一覧List（：ArrayList）に追加（add）する。
                    l_listResults.add(l_accountMailAddressInfo);
                }
            }
        }

        //顧客メールアドレス情報一覧List（：ArrayList）を配列に変換（toArray()）
        WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = new WEB3AccInfoAccountMailAddressInfo[l_listResults.size()];
        l_listResults.toArray(l_mailAddressInfos);

        log.exiting(STR_METHOD_NAME);    
        return l_mailAddressInfos;
    }
}
@
