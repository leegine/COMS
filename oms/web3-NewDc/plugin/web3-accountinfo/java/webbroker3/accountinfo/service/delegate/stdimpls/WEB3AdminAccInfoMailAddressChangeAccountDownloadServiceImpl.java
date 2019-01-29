head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl(WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
Revesion History : 2010/02/22 張騰宇 (中訊) モデル262 267
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoMailAddressCsv;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽImpl)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ実装クラス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AdminAccInfoMailAddressChangeAccountDownloadService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl.class);
    /**
     * @@roseuid 418F3A020157
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl() 
    {
     
    }
    
    /**
     * メールアドレス変更顧客ダウンロード処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報メールアドレス変更顧客<BR>
     * 問合せﾘｸｴｽﾄの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報メールアドレス変更顧客<BR>
     * ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロード画面()をコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報メールアドレス変更顧客<BR>
     * ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合 <BR>
     * 　@－getダウンロードファ@イル()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F44102F9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)
        {
            l_response = this.getInputScreen((WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)
        {           
            l_response = this.getDownloadScreen((WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)l_request) ;
        }
        else if (l_request instanceof WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)
        {           
            l_response = this.getDownloadFile((WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)l_request);
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
     * メールアドレス変更顧客ダウンロード問合せ画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレス変更顧客ＤＬ）get入力画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更顧客問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A56ED003D
     */
    protected WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse getInputScreen(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //1.2validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);
        
        WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_request.createResponse();

        Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();
        Timestamp l_gentradeBizDate = WEB3GentradeUtils.getBizDate(l_systemTimestamp, -1);
        l_response.previousBizDate = WEB3DateUtility.toDay(l_gentradeBizDate);
        Date l_datPreviousDate = WEB3DateUtility.addDay(l_systemTimestamp, -1);
        l_response.previousDate = WEB3DateUtility.toDay(l_datPreviousDate);
        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (getダウンロード画面)<BR>
     * メールアドレス変更顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレス変更顧客ＤＬ）getダウンロード画面」参照。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F2DC01EF
     */
    protected WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse getDownloadScreen(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getDownloadScreen(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
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

        //getダウンロードデータ(String, String[], Date, Date, お客様情報ソートキー[], String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos = 
            this.getDownloadData(
                l_strInstitutionCode, l_request.branchCode, l_request.startDate,
                l_request.endDate, l_request.sortKeys, l_strMultiMailAddressFlag);

        //ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        //要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_accountMailAddressInfos, l_intPageIndex, l_intPageSize);
        
        //1.7管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ(WEB3GenRequest)
        WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_request.createResponse();
        //(表示ページ番号)
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + ""; 
        //総ページ数
        l_response.totalPages= l_pageIndexInfo.getTotalPages() + ""; 
        //総レコード数
        l_response.totalRecords= l_pageIndexInfo.getTotalRecords() + ""; 
        //明細のリスト
        l_response.mailAddressChangeAccountList = 
            (WEB3AccInfoAccountMailAddressInfo[])l_pageIndexInfo.getArrayReturned(WEB3AccInfoAccountMailAddressInfo.class);        
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * メールアドレス変更顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（メールアドレス変更顧客ＤＬ）getダウンロードファ@イル」参照。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F2DC01FF
     */
    protected WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse getDownloadFile(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
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

        //getダウンロードデータ(String, String[], Date, Date, お客様情報ソートキー[], String)
        WEB3AccInfoAccountMailAddressInfo[] l_accountMailAddressInfos = 
            this.getDownloadData(
                l_strInstitutionCode, l_request.branchCode, l_request.startDate,
                l_request.endDate, l_request.sortKeys, l_strMultiMailAddressFlag);
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
            l_mailAddressCsv.setMailAddressUpdatedDate(l_intLineNumber, l_accountMailAddressInfos[i].updateDate);
            l_mailAddressCsv.setMailAddressUpdaterCode(l_intLineNumber, l_accountMailAddressInfos[i].updaterCode);
        }
        //1.9getCSVファ@イル行( )
        String[] l_cvsFileLines = l_mailAddressCsv.getCsvFileLines();
        
        WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse l_response = 
            (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_request.createResponse();
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
     * 　@顧客マスタ.部店コード in （部店コード[0]，部店コード[1]，，） And <BR>
     * ※ 引数の部店コード[]の要素を列挙する。<BR>
     * 　@顧客マスタ.emailアドレス更新日時 >= 開始日　@And<BR>
     * 　@顧客マスタ.emailアドレス更新日時 < 終了日の翌日<BR>
     * <BR>
     * 　@[取得順（order by）]<BR>
     * 　@※　@引数のソートキー.キー項目が示す項目／順序にて取得する。<BR>
     * 　@ソートキー.キー項目に対応する顧客マスタの項目は以下の通りとする。<BR>
     * <BR>
     * 　@（ソートキー.キー項目 == 部店コード）：　@顧客マスタ.部店コード<BR>
     * 　@（ソートキー.キー項目 == 顧客コード）：　@顧客マスタ.口座コード<BR>
     * 　@（ソートキー.キー項目 == 更新日）：　@顧客マスタ.emailアドレス更新日時<BR>
     * <BR>
     * 　@１－２）　@引数.複数メールアドレス対応実施フラグ == "２"の場合、以下処理を行う。<BR>
     * 　@顧客メールアドレステーブルを以下の条件で検索する。       <BR>
     *      <BR>
     * 　@[条件]        <BR>
     * 　@顧客メールアドレス.証券会社コード = 証券会社コード And     <BR>
     * 　@顧客メールアドレス.部店コード in （部店コード[0]，部店コード[1]，，）<BR>
     * 　@ And ※ 引数の部店コード[]の要素を列挙する。<BR>
     * 　@顧客メールアドレス.emailアドレス更新日時 >= 開始日　@And      <BR>
     * 　@顧客メールアドレス.emailアドレス更新日時 < 終了日の翌日   <BR>
     *      <BR>
     * 　@[取得順（order by）]     <BR>
     * 　@※　@引数のソートキー.キー項目が示す項目／順序にて取得する。      <BR>
     * 　@ソートキー.キー項目に対応する顧客メールアドレスの項目は以下の通りとする。<BR>
     *  <BR>
     * 　@（ソートキー.キー項目 == 部店コード）：　@顧客メールアドレス.部店コード  <BR>
     * 　@（ソートキー.キー項目 == 顧客コード）：　@顧客メールアドレス.口座コード  <BR>
     * 　@（ソートキー.キー項目 == アドレス区分）：　@顧客メールアドレス.アドレス区分<BR>
     * 　@（ソートキー.キー項目 == 更新日）：　@顧客メールアドレス.メールアドレス更新日時<BR>
     * <BR>
     * ２）　@顧客メールアドレス情報一覧List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@顧客メールアドレス情報生成<BR>
     * 　@３－１）　@引数.複数メールアドレス対応実施フラグ！= "２" の場合、以下処理を行う。<BR>
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
     * <BR>
     * 　@(*1) 更新者コード<BR>
     * 　@　@（顧客マスタ行.emailアドレス更新者コード == 顧客マスタ行.口座コード）の場合、<BR>
     * 　@　@口座コードの左6byte。<BR>
     * 　@　@以外、顧客マスタ行.emailアドレス更新者コード。<BR>
     * <BR>
     * ３－２） 　@引数.複数メールアドレス対応実施フラグ == "２"の場合、以下処理を行う<BR>
     * 　@１）で取得した各行オブジェクト（：顧客メールアドレスParams）毎に、４）の処理を行う。<BR>
     * 　@取得した顧客メールアドレス行.証券会社コード、部店コード、<BR>
     * 　@顧客コードに該当する顧客オブジェクトを取得する。<BR>
     * 　@顧客メールアドレス情報を追加、以下の通りプロパティをセットする。<BR>
     * <BR>
     * 　@顧客メールアドレス情報.証券会社コード = 顧客メールアドレス行.証券会社コード<BR>
     * 　@顧客メールアドレス情報.部店コード = 顧客メールアドレス行.部店コード<BR>
     * 　@顧客メールアドレス情報.顧客コード = 顧客メールアドレス行.口座コードの左6byte<BR>
     * 　@顧客メールアドレス情報.顧客名 = 顧客マスタ行.名前（苗字）　@※顧客名（漢字）として使用<BR>
     * 　@顧客メールアドレス情報.メールアドレス = 顧客メールアドレス行.メールアドレス<BR>
     * 　@顧客メールアドレス情報.更新日 = 顧客メールアドレス行.メールアドレス更新日時<BR>
     * 　@顧客メールアドレス情報.更新者コード = 顧客メールアドレス行.メールアドレス更新者コード<BR>
     * <BR>
     * ４）　@顧客メールアドレス情報一覧List（：ArrayList）にオブジェクトを追加する。<BR>
     * 　@３）で生成したオブジェクトを顧客メールアドレス情報一覧List（：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ５）　@ダウンロードデータ返却<BR>
     * 　@顧客メールアドレス情報一覧List（：ArrayList）を配列に変換（toArray()）し、<BR>
     * 返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * 
     * @@param l_datStartDate - 開始日
     * @@param l_datEndDate - 終了日
     * @@param l_sortKeys - ソートキー
     * @@param l_strMultiMailAddressFlag - 複数メールアドレス対応実施フラグ
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountMailAddressInfo[]
     * @@roseuid 4147F2DC0201
     */
    protected WEB3AccInfoAccountMailAddressInfo[] getDownloadData(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        Date l_datStartDate, 
        Date l_datEndDate, 
        WEB3AccInfoSortKey[] l_sortKeys,
        String l_strMultiMailAddressFlag) throws WEB3BaseException 
             
    {
        final String STR_METHOD_NAME = "getDownloadData(String, String[], Date, Date, WEB3AccInfoSortKey[], String)";
        log.entering(STR_METHOD_NAME);

        //顧客マスタテーブルを以下の条件で検索する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");

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
            l_sbWhere.append(" and branch_code in (" + l_sbQueryBranchCodes.toString() + ")") ;
        }       

        l_sbWhere.append(" and email_last_updated_timestamp >= ? ");                               
        l_sbWhere.append(" and email_last_updated_timestamp < ? ");

        //顧客メールアドレス情報一覧List（：ArrayList）生成ArrayListを生成する。
        List l_lisWhere = new ArrayList();
 
        l_lisWhere.add(l_strInstitutionCode);
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            l_lisWhere.add(l_strBranchCodes[i]);           
        }        
        l_lisWhere.add(l_datStartDate);

        Date l_datEndDateNextDay = WEB3DateUtility.addDay(l_datEndDate, 1);
        l_lisWhere.add(l_datEndDateNextDay);

        Object[] l_objWhere = l_lisWhere.toArray();

        List l_lisRecords = null;
        QueryProcessor l_queryProcessor = null;
        List l_lisMailAddressInfos = new ArrayList();

        //引数.複数メールアドレス対応実施フラグ！= "２" の場合
        if (!WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strMultiMailAddressFlag))
        {
            //取得順（order by）
            StringBuffer l_sbOrderBy = new StringBuffer();
            int l_intSortKeyCnt = l_sortKeys.length;
            if (l_intSortKeyCnt > 0)
            {
                StringBuffer l_sbSortKey = new StringBuffer();

                for (int i = 0; i < l_intSortKeyCnt; i++)
                {
                    if (l_sbSortKey.length() != 0)
                    {
                        l_sbSortKey.append(", ");
                    }
                    WEB3AccInfoSortKey l_accInfoSortKey = (WEB3AccInfoSortKey)l_sortKeys[i];
                    if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" branch_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" account_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" email_last_updated_timestamp ");
                    }

                    if (WEB3AscDescDef.ASC.equals(l_accInfoSortKey.ascDesc))
                    {
                        l_sbSortKey.append(" ASC ");
                    }
                    else
                    {
                        l_sbSortKey.append(" DESC ");
                    }
                }
                l_sbOrderBy.append(l_sbSortKey.toString());
            }

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
                //で生成したオブジェクトを顧客メールアドレス情報一覧List（：ArrayList）に追加（add）する。
                l_lisMailAddressInfos.add(l_accountMailAddressInfo);
            }
        }
        //引数.複数メールアドレス対応実施フラグ == "２"の場合
        else
        {
            //取得順（order by）
            StringBuffer l_sbOrderBy = new StringBuffer();
            int l_intSortKeyCnt = l_sortKeys.length;
            if (l_intSortKeyCnt > 0)
            {
                StringBuffer l_sbSortKey = new StringBuffer();

                for (int i = 0; i < l_intSortKeyCnt; i++)
                {
                    if (l_sbSortKey.length() != 0)
                    {
                        l_sbSortKey.append(", ");
                    }
                    WEB3AccInfoSortKey l_accInfoSortKey = (WEB3AccInfoSortKey)l_sortKeys[i]; 
                    if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" branch_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" account_code ");
                    }
                    else if (WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_accInfoSortKey.keyItem))
                    {
                        l_sbSortKey.append(" email_last_updated_timestamp ");
                    }

                    if (WEB3AscDescDef.ASC.equals(l_accInfoSortKey.ascDesc))
                    {
                        l_sbSortKey.append(" ASC ");
                    }
                    else
                    {
                        l_sbSortKey.append(" DESC ");
                    }
                }
                if (l_sbSortKey.length() != 0)
                {
                    l_sbSortKey.append(", ");
                }
                l_sbSortKey.append(" address_div ASC");
                l_sbOrderBy.append(l_sbSortKey.toString());
            }

            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    AccountMailAddressRow.TYPE,
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
                AccountMailAddressRow l_accountMailAddressRow = (AccountMailAddressRow)l_lisRecords.get(i);
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

                //顧客メールアドレス情報.更新者コード = 顧客メールアドレス行.メールアドレス更新者コード
                l_accountMailAddressInfo.updaterCode = l_accountMailAddressRow.getEmailLastUpdater();
                //で生成したオブジェクトを顧客メールアドレス情報一覧List（：ArrayList）に追加（add）する。
                l_lisMailAddressInfos.add(l_accountMailAddressInfo);
            }
        }
        //顧客メールアドレス情報一覧List（：ArrayList）を配列に変換（toArray()）
        WEB3AccInfoAccountMailAddressInfo[] l_mailAddressInfos = 
            new WEB3AccInfoAccountMailAddressInfo[l_lisMailAddressInfos.size()];
        l_lisMailAddressInfos.toArray(l_mailAddressInfos);
        
        log.exiting(STR_METHOD_NAME);    
        return l_mailAddressInfos;
    }
}
@
