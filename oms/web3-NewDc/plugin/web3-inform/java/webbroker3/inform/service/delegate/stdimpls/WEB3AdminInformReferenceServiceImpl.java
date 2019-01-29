head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索サービス実装クラス(WEB3AdminInformReferenceServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
Revesion History    : 2007/05/14 謝旋(中訊) モデル・No.036-No.41,モデルNo.44
Revesion History    : 2007/05/22 謝旋(中訊) モデル・No.047-No.53
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.inform.WEB3AdminInformDownLoadCSV;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.inform.message.WEB3AdminInformCommonRequest;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDetailResponse;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadResponse;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformInputResponse;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.inform.message.WEB3AdminInformListResponse;
import webbroker3.inform.message.WEB3InformDetailHeaderInfoUnit;
import webbroker3.inform.message.WEB3InformSortKey;
import webbroker3.inform.service.delegate.WEB3AdminInformReferenceService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (連絡情報検索サービスImpl)<BR>
 * 連絡情報検索サービス実装クラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformReferenceServiceImpl implements WEB3AdminInformReferenceService 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceServiceImpl.class);
    
    /**
     * @@roseuid 41EE632C0157
     */
    public WEB3AdminInformReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 連絡情報検索サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * <BR>
     *    get入力画面()<BR>
     *    get一覧画面()<BR>
     *    get詳細画面()<BR>
     *    getダウンロードファ@イル()<BR>
     * <BR>
     * 上記メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@roseuid 41BD82B2014F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute()";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminInformInputRequest)
        {
            // 入力画面
            l_response = getInformInputDisplay((WEB3AdminInformInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformListRequest)
        {
            // 一覧画面
            l_response = getInformListDisplay((WEB3AdminInformListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformDetailRequest)
        {
            // 詳細画面
            l_response = getInformDetailDisplay((WEB3AdminInformDetailRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformDownLoadRequest)
        {
            // ダウンロードファ@イル
            l_response = getDownLoadFile((WEB3AdminInformDownLoadRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連絡情報検索）get入力画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.inform.message.WEB3AdminInformInputResponse
     * @@roseuid 41BD83410297
     */
    protected WEB3AdminInformInputResponse getInformInputDisplay(WEB3AdminInformInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInformInputDisplay()";
        log.entering(STR_METHOD_NAME);

        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックを行う。
        //[引数]
        //  機@能カテゴリコード： "A0303"
        //  is更新： false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //レスポンスデータを生成する。 
        WEB3AdminInformInputResponse l_response = (WEB3AdminInformInputResponse)l_request.createResponse();

        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();

        //レスポンス.前営業日 = 現在日時の前営業日日付
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //レスポンス.当日 = 現在日時の日付
        l_response.previousDate = WEB3DateUtility.toDay(l_tsCurrentDate);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get一覧画面)<BR>
     * 一覧画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連絡情報検索）get一覧画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return webbroker3.inform.message.WEB3AdminInformListResponse
     * @@roseuid 41BD839100F2
     */
    protected WEB3AdminInformListResponse getInformListDisplay(WEB3AdminInformListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformListDisplay()";
        log.entering(STR_METHOD_NAME);

        //リクエストデータのチェックを行う。 
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックを行う。
        //[引数]
        //  機@能カテゴリコード： "A0303"
        //  is更新： false 
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //部店権限をチェックする。
        l_administrator.validateBranchPermission(l_request.branchCode);

        //create取得条件文字列(WEB3AdminInformCommonRequest)
        //  (連絡情報検索サービスImpl::create取得条件文字列)
        String l_strQueryString = this.createGetCondString(l_request);

        //create取得条件データコンテナ(String, WEB3AdminInformCommonRequest)
        //  (連絡情報検索サービスImpl::create取得条件データコンテナ)
        Object[] l_queryContainer = this.createGetCondDataContainer(
            l_administrator.getInstitutionCode(),
            l_request);

        //createソート条件文字列(連絡情報検索ソートキー[])
        //  (連絡情報検索サービスImpl::createソート条件文字列)
        String l_strSortCond = this.createSortCondString(l_request.sortKeys);

        //ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
    
        //要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);  

        List l_lisRecords = null;
        try
        {
            //getDefaultProcessor( )(Processors::getDefaultProcessor)
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    
            //doFindAllQuery(RowType, String, String, String, Object[], int, int)
            //  (QueryProcessorStdImpl::doFindAllQuery)
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
        }        

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisRecords,
            l_intPageIndex, 
            l_intPageSize);

        List l_lisSelectVariousInformRows = l_pageIndexInfo.getListReturned();
        int l_intSize = l_lisSelectVariousInformRows.size();
        log.debug("l_intSize = " + l_intSize);
        
        List l_lisInfoUnit = new ArrayList();
        for (int i = 0; i < l_intSize; i++)
        {
            //各種連絡詳細情報インスタンスを生成する。 
            WEB3InformDetailHeaderInfoUnit l_infoUnit = new WEB3InformDetailHeaderInfoUnit((VariousInformParams)l_lisSelectVariousInformRows.get(i));
            
            //リストに各種連絡詳細情報オブジェクトを追加する。
            l_lisInfoUnit.add(i, l_infoUnit);
        }

        //toArray()
        WEB3InformDetailHeaderInfoUnit[] l_informInfoUnit = new WEB3InformDetailHeaderInfoUnit[l_intSize];
        l_lisInfoUnit.toArray(l_informInfoUnit);

        //レスポンスデータを生成する。
        WEB3AdminInformListResponse l_response = (WEB3AdminInformListResponse)l_request.createResponse();

        //レスポンス.連絡情報 = 各種連絡詳細情報オブジェクトの配列
        l_response.informInfoDetailUnit = l_informInfoUnit;

        //レスポンス.表示ページ番号 = pageNumber()の戻り値
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();
        log.debug("表示ページ番号:" + l_pageIndexInfo.getPageIndex());

        //レスポンス.総ページ数 = totalPages()の戻り値
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        log.debug("総ページ数:" + l_pageIndexInfo.getTotalPages());

        //レスポンス.総レコード数 = totalSize()の戻り値
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        log.debug("総レコード数:" + l_pageIndexInfo.getTotalRecords());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get詳細画面)<BR>
     * 詳細画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連絡情報検索）get詳細画面」 参照。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.inform.message.WEB3AdminInformDetailResponse
     * @@roseuid 41BD83AF00E2
     */
    protected WEB3AdminInformDetailResponse getInformDetailDisplay(WEB3AdminInformDetailRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformDetailDisplay()";
        log.entering(STR_METHOD_NAME);

        //リクエストデータのチェックを行う。 
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックを行う。
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //部店権限をチェックする。
        l_administrator.validateBranchPermission(l_request.branchCode);

        try
        {
            //(*1) DAOにて、各種連絡テーブルからレコードを取得する。
            VariousInformRow l_informRow = VariousInformDao.findRowByInstitutionCodeInformDivRequestNumberBranchCode(
                l_administrator.getInstitutionCode(),
                l_request.informType,
                l_request.requestNumber,
                l_request.branchCode);
                
            VariousInformParams l_informParams = null;
            if (l_informRow != null)
            {
                l_informParams = new VariousInformParams(l_informRow);            
            }
            else
            {
                log.debug("テーブルに該当するデータがありません。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);                        
            }

            //各種連絡詳細情報インスタンスを生成する。
            WEB3InformDetailHeaderInfoUnit l_infoUnit = new WEB3InformDetailHeaderInfoUnit(l_informParams);

            //レスポンスデータを生成する。
            WEB3AdminInformDetailResponse l_response = (WEB3AdminInformDetailResponse)l_request.createResponse();

            //レスポンス.連絡情報 = 各種連絡詳細情報オブジェクト
            l_response.informInfoDetailUnit = l_infoUnit;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * ダウンロードファ@イルの取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連絡情報検索）getダウンロードファ@イル」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.inform.message.WEB3AdminInformDownLoadResponse
     * @@roseuid 41BD83B10259
     */
    protected WEB3AdminInformDownLoadResponse getDownLoadFile(WEB3AdminInformDownLoadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformDetailDisplay()";
        log.entering(STR_METHOD_NAME);

        //リクエストデータのチェックを行う。 
        l_request.validate();
        
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックを行う。
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //部店権限をチェックする。
        l_administrator.validateBranchPermission(l_request.branchCode);

        List l_lisCsvFileLines = new ArrayList();

        int l_intBranchCodeLth = 0;
        if (l_request.branchCode != null)
        {
            l_intBranchCodeLth = l_request.branchCode.length;
        }
        log.debug("部店コードの長度：" + l_intBranchCodeLth);

        //マージした配列の要素数
        int l_intMergyCnt = 0;

        //create取得条件文字列(WEB3AdminInformCommonRequest)
        //  (連絡情報検索サービスImpl::create取得条件文字列)
        String l_strQueryString = this.createGetCondString(l_request);

        //create取得条件データコンテナ(String, WEB3AdminInformCommonRequest)
        //  (連絡情報検索サービスImpl::create取得条件データコンテナ)
        Object[] l_queryContainer = this.createGetCondDataContainer(
            l_administrator.getInstitutionCode(),
            l_request);

        //editソート条件(連絡情報検索ソートキー[])(連絡情報検索サービスImpl::editソート条件)
        WEB3InformSortKey[] l_informSortKeys = this.editSortCond(l_request.sortKeys);

        //createソート条件文字列(連絡情報検索ソートキー[])
        //  (連絡情報検索サービスImpl::createソート条件文字列)
        String l_strSortCond = this.createSortCondString(l_informSortKeys);

        //連絡情報ダウンロードCSV(証券会社コード, 部店コード, 連絡種別, isカラムヘッダ行出力)
        //証券会社コード： 管理者.get証券会社コード()の戻り値
        //部店コード： リクエストデータ.部店コードの要素
        //連絡種別： リクエストデータ.連絡種別
        //isカラムヘッダ行出力： true
        WEB3AdminInformDownLoadCSV l_downLoadCSV = null;
        l_downLoadCSV = new WEB3AdminInformDownLoadCSV(
            l_administrator.getInstitutionCode(),
            l_request.branchCode[0],
            l_request.informType,
            true);

        List l_lisRecords = null;
        try
        {
            //getDefaultProcessor( )(Processors::getDefaultProcessor)
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doFindAllQuery(RowType, String, String, String, Object[], int, int)(
            //  (QueryProcessorStdImpl::doFindAllQuery)
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
        }
    
        int l_intSize = 0;
        if (l_lisRecords != null)
        {
            l_intSize = l_lisRecords.size();
        }

        log.debug("各個条件よる、検索したレコード数：" + l_intSize);

        l_intMergyCnt = l_intMergyCnt + l_intSize;

        //(*2)取得した各種連絡テーブルのレコード毎にLoop処理
        for (int j = 0; j < l_intSize; j++)
        {
            //add明細行()
            l_downLoadCSV.addRow();
            
            log.debug("各個条件よる、項目値をセット。第 " + j + " 個レコード!");
            //set項目値(int, 各種連絡Params)(連絡情報ダウンロードCSV::set項目値)
            l_downLoadCSV.setItemValue(j, (VariousInformParams)l_lisRecords.get(j));
        }

        log.debug("各個条件よる、CSVファ@イル行を生成!");
        //getCSVファ@イル行()(連絡情報ダウンロードCSV::getCSVファ@イル行)
        String[] l_strCsvFileLinesDetail = l_downLoadCSV.getCsvFileLines();
        if (l_strCsvFileLinesDetail != null &&l_strCsvFileLinesDetail.length > 0)
        {
            log.debug("CSVファ@イル行:" + l_strCsvFileLinesDetail.length);
            for (int k = 0; k < l_strCsvFileLinesDetail.length; k++)
            {
            	if (! "".equals(l_strCsvFileLinesDetail[k].trim()))
                	l_lisCsvFileLines.add(l_strCsvFileLinesDetail[k]);
            }
        }

        //getMAX処理件数( )(連絡情報ダウンロードCSV::getMAX処理件数)
        int l_intMaxDealNumber = l_downLoadCSV.getMaxDealNumber();

        //(*3)
        //１）部店コード毎に生成されたCSVファ@イル行を1つの配列にマージする。
        String[] l_strCsvFileLines = new String[l_lisCsvFileLines.size()];
        l_lisCsvFileLines.toArray(l_strCsvFileLines);

        //２）マージした配列の要素数 == 0、例外をスローする。
        if (l_intMergyCnt == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01870,
                this.getClass().getName() + STR_METHOD_NAME);                        
        }

        //マージした配列の要素数 > getMAX処理件数()の戻り値 の場合、例外をスローする。
        if (l_intMergyCnt > l_intMaxDealNumber)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01871,
                this.getClass().getName() + STR_METHOD_NAME);                        
        }

        //レスポンスデータを生成する。
        WEB3AdminInformDownLoadResponse l_response = (WEB3AdminInformDownLoadResponse)l_request.createResponse();

        //レスポンス.ダウンロードファ@イル = (*3)でマージした配列
        l_response.downloadFile = l_strCsvFileLines;

        //レスポンス.現在日時 = 現在時刻
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create取得条件文字列)<BR>
     * 取得条件の文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）連絡種別<BR>
     * <BR>
     *    "inform_div=? and institution_code=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * ３－１）引数.リクエストデータ.部店コード.length() == 1 の場合<BR>
     * <BR>
     *    " and branch_code=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ３－２）引数.リクエストデータ.部店コード.length() > 1 の場合<BR>
     * <BR>
     *    " and (branch_code=? or branch_code=? or ... or branch_code=?)" を１）の文字列に追加する。<BR>
     * <BR>
     *    ※"branch_code=?"の数は、引数.リクエストデータ.部店コードの要素数と同じになる。<BR>
     * <BR>
     * ４）識別コード<BR>
     * <BR>
     *    引数.リクエストデータ.識別コード != null の場合<BR>
     * <BR>
     *    " and request_number like '?%'" を１）の文字列に追加する。<BR>
     * <BR>
     * ５）顧客コード<BR>
     * <BR>
     *    引数.リクエストデータ.顧客コード != null の場合<BR>
     * <BR>
     *    " and account_code like '?%'" を１）の文字列に追加する。<BR>
     * <BR>
     * ６）顧客名<BR>
     * <BR>
     *    引数.リクエストデータ.顧客名 != null の場合<BR>
     * <BR>
     *    " and account_name like '%?%'" を１）の文字列に追加する。<BR>
     * <BR>
     * ７）受付日時<BR>
     * <BR>
     * ７－１）引数.リクエストデータ.受付日時（自） != null の場合<BR>
     * <BR>
     *    " and created_timestamp>=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ７－２）引数.リクエストデータ.受付日時（至） != null の場合<BR>
     * <BR>
     *    " and created_timestamp<?" を１）の文字列に追加する。<BR>
     * <BR>
     * ８）銘柄コード<BR>
     * <BR>
     *    引数.リクエストデータ.銘柄コード != null の場合<BR>
     * <BR>
     *    " and fund_code =?" を１）の文字列に追加する。<BR>
     * <BR>
     * ９）扱者コード<BR>
     * <BR>
     *    引数.リクエストデータ.扱者コード != null の場合<BR>
     * <BR>
     *    " and sonar_trader_code =?" を１）の文字列に追加する。<BR>
     * <BR>
     * １０）伝票作成状況<BR>
     * <BR>
     * １０－１）引数.リクエストデータ.伝票作成状況 != null の場合<BR>
     * <BR>
     * １０ー１ー１）引数.リクエストデータ.伝票作成状況.length == 1 の場合<BR>
     * <BR>
     *    " and status=?" を１）の文字列に追加する。<BR>
     * <BR>
     * １０－１－２）引数.リクエストデータ.伝票作成状況.length > 1 の場合<BR>
     * <BR>
     *    " and (status =? or status =? or ... or status =?)" を１）の文字列に追加する。<BR>
     * <BR>
     *   ※"status =?"の数は、引数.リクエストデータ.伝票作成状況の要素数と同じになる。<BR>
     * <BR>
     * １１）生成された文字列を返却する。<BR>
     * @@param l_request - (連絡情報検索共通リクエスト)<BR>
     * @@return String
     * @@roseuid 41BE56DB0001
     */
    protected String createGetCondString(WEB3AdminInformCommonRequest l_request)
    {
        final String STR_METHOD_NAME = "createGetCondString()";
        log.entering(STR_METHOD_NAME);

        //１）空の文字列を生成する。<BR>
        StringBuffer l_sbQueryString = new StringBuffer();

        //２）連絡種別<BR>
        //  "inform_div=? and institution_code = ?" を１）の文字列に追加する。<BR>
        l_sbQueryString.append("inform_div = ? and institution_code = ?");

        //３）部店コード<BR>
        // ３－１）引数.リクエストデータ.部店コード.length() == 1 の場合<BR>
        //    " and branch_code=?" を１）の文字列に追加する。<BR>
        if (l_request.branchCode.length == 1)
        {
            l_sbQueryString.append(" and branch_code = ?");
        }

        // ３－２）引数.リクエストデータ.部店コード.length() > 1 の場合<BR>
        //    " and (branch_code=? or branch_code=? or ... or branch_code=?)" を１）の文字列に追加する。<BR>
        //    ※"branch_code=?"の数は、引数.検索条件.部店コードの要素数と同じになる。<BR>
        if (l_request.branchCode.length > 1)
        {
            l_sbQueryString.append(" and (branch_code = ?");

            for (int i = 1; i < l_request.branchCode.length; i++)
            {
                l_sbQueryString.append(" or branch_code = ?");
            }

            l_sbQueryString.append(")");
        }

        //４）識別コード<BR>
        //   引数.リクエストデータ.識別コード != null の場合<BR>
        //   " and request_number like '?%'" を１）の文字列に追加する。<BR>
        if (l_request.requestNumber != null)
        {
            l_sbQueryString.append(" and request_number like ? || '%'");
        }

        //５）顧客コード<BR>
        //   引数.リクエストデータ.顧客コード != null の場合<BR>
        //   " and account_code like '?%'" を１）の文字列に追加する。<BR>
        if (l_request.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%'");
        }

        //６）顧客名<BR>
        //   引数.リクエストデータ.顧客名 != null の場合<BR>
        //   " and account_name like '%?%'" を１）の文字列に追加する。<BR>
        if (l_request.accountName != null)
        {
            l_sbQueryString.append(" and account_name like '%' || ? || '%'");
        }

        //７）受付日時<BR>
        // ７－１）引数.リクエストデータ.受付日時（自） != null の場合<BR>
        //   " and created_timestamp>=?" を１）の文字列に追加する。<BR>
        if (l_request.receptionDateFrom != null)
        {
            l_sbQueryString.append(" and created_timestamp >= ?");
        }

        // ７－２）引数.リクエストデータ.受付日時（至） != null の場合<BR>
        //   " and created_timestamp<?" を１）の文字列に追加する。<BR>
        if (l_request.receptionDateTo != null)
        {
            l_sbQueryString.append(" and created_timestamp < ?");
        }

        //８）銘柄コード
        //引数.リクエストデータ.銘柄コード != null の場合
        //" and fund_code =?" を１）の文字列に追加する。
        if (l_request.productCode != null)
        {
            l_sbQueryString.append(" and fund_code = ?");
        }

        //９）扱者コード
        //引数.リクエストデータ.扱者コード != null の場合
        //" and sonar_trader_code =?" を１）の文字列に追加する。
        if (l_request.traderCode != null)
        {
            l_sbQueryString.append(" and sonar_trader_code = ?");
        }

        //１０）伝票作成状況
        //１０－１）引数.リクエストデータ.伝票作成状況 != null の場合
        //１０ー１ー１）引数.リクエストデータ.伝票作成状況.length == 1 の場合
        //" and status=?" を１）の文字列に追加する。
        //１０－１－２）引数.リクエストデータ.伝票作成状況.length > 1 の場合
        //" and (status =? or status =? or ... or status =?)" を１）の文字列に追加する。
        //※"status =?"の数は、引数.リクエストデータ.伝票作成状況の要素数と同じになる。
        if (l_request.voucherInfoList != null)
        {
            if (l_request.voucherInfoList.length == 1)
            {
                l_sbQueryString.append(" and status = ?");
            }
            else if (l_request.voucherInfoList.length > 1)
            {
                l_sbQueryString.append(" and (status = ?");

                for (int i = 1; i < l_request.voucherInfoList.length; i++)
                {
                    l_sbQueryString.append(" or status = ?");
                }

                l_sbQueryString.append(")");
            }
        }

        //１１）生成された文字列を返却する。<BR>
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create取得条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）連絡種別<BR>
     * <BR>
     *    引数.リクエストデータ.連絡種別 を１）のListに追加する。<BR>
     * <BR>
     * ３）証券会社コード<BR>
     *    引数.証券会社コード を１）のListに追加する。<BR>
     * <BR>
     * ４）部店コード<BR>
     * <BR>
     *    引数.リクエストデータ.部店コードの各要素 を１）のListに追加する。<BR>
     * <BR>
     * ５）識別コード<BR>
     * <BR>
     *    引数.リクエストデータ.識別コード != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.識別コード を１）のListに追加する。<BR>
     * <BR>
     * ６）顧客コード<BR>
     * <BR>
     *    引数.リクエストデータ.顧客コード != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.顧客コード を１）のListに追加する。<BR>
     * <BR>
     * ７）顧客名<BR>
     * <BR>
     *    引数.リクエストデータ.顧客名 != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.顧客名 を１）のListに追加する。<BR>
     * <BR>
     * ８）受付日時<BR>
     * <BR>
     * ８－１）<BR>
     *    引数.リクエストデータ.受付日時（自） != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.受付日時（自） を１）のListに追加する。<BR>
     * <BR>
     * ８－２）<BR>
     *    引数.リクエストデータ.受付日時（至） != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.受付日時（至） を１）のListに追加する。<BR>
     * <BR>
     * ９）銘柄コード<BR>
     * <BR>
     *    引数.リクエストデータ.銘柄コード != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.銘柄コード を１）のListに追加する。<BR>
     * <BR>
     * １０）扱者コード<BR>
     * <BR>
     *    引数.リクエストデータ.扱者コード != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.扱者コード を１）のListに追加する。<BR>
     * <BR>
     * １１）伝票作成状況<BR>
     * <BR>
     *    引数.リクエストデータ.伝票作成状況 != nullの場合<BR>
     * <BR>
     *    引数.リクエストデータ.伝票作成状況の各要素を１）のListに追加する。<BR>
     * <BR>
     * １２）生成されたListから配列を取得し、返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_request - (連絡情報検索共通リクエスト)<BR>
     * @@return Object[]
     * @@roseuid 41BE56EC00CD
     */
    protected Object[] createGetCondDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformCommonRequest l_request)
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer()";
        log.entering(STR_METHOD_NAME);

        //１）空のArrayListを生成する。<BR>
        List l_lstQueryContainer = new ArrayList();

        //２）連絡種別<BR>
        //   引数.連絡種別 を１）のListに追加する。<BR>
        l_lstQueryContainer.add(l_request.informType);

        //３）証券会社コード<BR>
        //   引数.証券会社コード を１）のListに追加する。<BR>
        l_lstQueryContainer.add(l_strInstitutionCode);

        //４）部店コード<BR>
        //   引数.部店コードの各要素 を１）のListに追加する。<BR>
        if (l_request.branchCode.length > 0)
        {
            for (int i = 0; i < l_request.branchCode.length; i++)
            {
                l_lstQueryContainer.add(l_request.branchCode[i]);
            }
        }

        //５）識別コード<BR>
        //   引数.識別コード != nullの場合<BR>
        //   引数.識別コード を１）のListに追加する。<BR>
        if (l_request.requestNumber != null)
        {
            l_lstQueryContainer.add(l_request.requestNumber);
        }

        //６）顧客コード<BR>
        //   引数.顧客コード != nullの場合<BR>
        //   引数.顧客コード を１）のListに追加する。<BR>
        if (l_request.accountCode != null)
        {
            l_lstQueryContainer.add(l_request.accountCode);
        }

        //７）顧客名<BR>
        //   引数.顧客名 != nullの場合<BR>
        //   引数.顧客名 を１）のListに追加する。<BR>
        if (l_request.accountName != null)
        {
            l_lstQueryContainer.add(l_request.accountName);
        }

        //８）受付日時<BR>
        // ８－１）<BR>
        //    引数.受付日時（自） != nullの場合<BR>
        //    引数.受付日時（自） を１）のListに追加する。<BR>
        if (l_request.receptionDateFrom != null)
        {
            l_lstQueryContainer.add(l_request.receptionDateFrom);
        }

        //８－２）<BR>
        //   引数.受付日時（至） != nullの場合<BR>
        //   引数.受付日時（至） を１）のListに追加する。<BR>
        if (l_request.receptionDateTo != null)
        {
            l_lstQueryContainer.add(l_request.receptionDateTo);
        }

        //９）銘柄コード
        //引数.リクエストデータ.銘柄コード != nullの場合
        //引数.リクエストデータ.銘柄コード を１）のListに追加する。
        if (l_request.productCode != null)
        {
            l_lstQueryContainer.add(l_request.productCode);
        }

        //１０）扱者コード
        //引数.リクエストデータ.扱者コード != nullの場合
        //引数.リクエストデータ.扱者コード を１）のListに追加する。
        if (l_request.traderCode != null)
        {
            l_lstQueryContainer.add(l_request.traderCode);
        }

        //１１）伝票作成状況
        //引数.リクエストデータ.伝票作成状況 != nullの場合
        //引数.リクエストデータ.伝票作成状況の各要素を１）のListに追加する。
        if (l_request.voucherInfoList != null)
        {
            for (int i = 0; i < l_request.voucherInfoList.length; i++)
            {
                l_lstQueryContainer.add(l_request.voucherInfoList[i]);
            }
        }

        //１２）生成されたListから配列を取得し、返却する。<BR>
        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainer;
    }

    /**
     * (createソート条件文字列)<BR>
     * 取得データのソート条件の文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）ソートキー配列の各要素毎に以下の処理を行う。（Loop処理）<BR>
     * <BR>
     * ２－１）キー項目 == ”識別コード” の場合<BR>
     * <BR>
     *    ・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     *        "request_number"を１）の文字列に追加する。<BR>
     * <BR>
     *    ・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     *        "request_number desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－２）キー項目 == ”部店コード” の場合<BR>
     * <BR>
     *    ・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     *        "branch_code"を１）の文字列に追加する。<BR>
     * <BR>
     *    ・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     *        "branch_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－３）キー項目 == ”顧客コード” の場合<BR>
     * <BR>
     *    ・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     *        "account_code"を１）の文字列に追加する。<BR>
     * <BR>
     *    ・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     *        "account_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－４）キー項目 == ”受付日時” の場合<BR>
     * <BR>
     *    ・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     *        "created_timestamp"を１）の文字列に追加する。<BR>
     * <BR>
     *    ・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     *        "created_timestamp desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－５）該当の要素が配列内の最後の要素ではない場合<BR>
     * <BR>
     *    ", "を１）の文字列に追加する。<BR>
     * <BR>
     * ３）生成された文字列を返却する。
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー
     * @@return String
     * @@roseuid 41BE56F7032E
     */
    protected String createSortCondString(WEB3InformSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "createSortCondString()";
        log.entering(STR_METHOD_NAME);

        //１）空の文字列を生成する。<BR>
        StringBuffer l_sbSortCond = new StringBuffer();
        
        //２）ソートキー配列の各要素毎に以下の処理を行う。（Loop処理）<BR>
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            String l_strSubCond = null;
            if (WEB3InformKeyItemDef.REQUEST_NUMBER.equals(l_sortKeys[i].keyItem))
            {
                //２－１）キー項目 == ”識別コード” の場合<BR>
                l_strSubCond = "request_number";
            }
            else if (WEB3InformKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //２－２）キー項目 == ”部店コード” の場合<BR>
                l_strSubCond = "branch_code";
            }
            else if (WEB3InformKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //２－３）キー項目 == ”顧客コード” の場合<BR>
                l_strSubCond = "account_code";
            }
            else if (WEB3InformKeyItemDef.ACCEPT_TIME.equals(l_sortKeys[i].keyItem))
            {
                //２－４）キー項目 == ”受付日時” の場合<BR>
                l_strSubCond = "created_timestamp";
            }
            else
            {
                continue;
            }
            
            if (l_sbSortCond.length() != 0)
            {
                l_sbSortCond.append(", ");
            }
            l_sbSortCond.append(l_strSubCond);
            
            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append(" desc");
            }
            else
            {
                l_sbSortCond.append(" asc");
            }
        }

        //３）生成された文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }

    /**
     * 部店コードをソート条件の先頭に追加する為、<BR>
     * ソート条件を編集する。<BR>
     * <BR>
     * １）連絡情報検索ソートキーの配列を生成する。<BR>
     * <BR>
     * 　@・要素数： 引数.ソートキーの要素数 + 1<BR>
     * <BR>
     * ２）１）のソートキーの先頭要素に部店コードをセットする。<BR>
     * <BR>
     * 　@・ソートキー.キー項目 = 部店コード<BR>
     * 　@・ソートキー.昇順/降順 = A：昇順<BR>
     * <BR>
     * ３）１）のソートキーの残りの要素に引数.ソートキーの各要素を代入する。<BR>
     * <BR>
     * ４）生成したソートキー配列を返却する。<BR> 
     * @@param l_sortKeys -- 連絡情報検索ソートキー[]<BR>
     * @@return l_informSortKeys -- 連絡情報検索ソートキー[]<BR>
     */
    public WEB3InformSortKey[] editSortCond(WEB3InformSortKey[] l_sortKeys)
    {
        if (l_sortKeys == null)
        {
            return null;
        }

        //１）連絡情報検索ソートキーの配列を生成する。 
       //　@・要素数： 引数.ソートキーの要素数 + 1 
        WEB3InformSortKey[] l_informSortKeys = new WEB3InformSortKey[l_sortKeys.length + 1];

        //２）１）のソートキーの先頭要素に部店コードをセットする。 
       //　@・ソートキー.キー項目 = 部店コード 
       //　@・ソートキー.昇順/降順 = A：昇順
       WEB3InformSortKey l_informSortKey = new WEB3InformSortKey();
       l_informSortKey.keyItem = WEB3InformKeyItemDef.BRANCH_CODE;
       l_informSortKey.ascDesc = WEB3AscDescDef.ASC;
       l_informSortKeys[0] = l_informSortKey;

       //３）１）のソートキーの残りの要素に引数.ソートキーの各要素を代入する。
       for (int i = 0; i < l_sortKeys.length; i++)
       {
           l_informSortKeys[i + 1] = l_sortKeys[i];
       }

       return l_informSortKeys;
    }
}
@
