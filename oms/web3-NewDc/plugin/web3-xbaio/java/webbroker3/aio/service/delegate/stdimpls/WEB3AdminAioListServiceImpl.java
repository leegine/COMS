head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者入出金一覧サービスImpl(WEB3AdminAioListServiceImpl)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 車進 (中訊) 新規作成 仕様変更・モデル694 701 702 704 705
Revision History : 2007/02/19 長瀬亜紀 (SCS) 仕様変更・実装の問題 No.008
Revision History : 2009/03/11 王志葵 (中訊) 仕様変更・モデル No.1118,1138,1153,1155
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AdminAioListDownloadCSV;
import webbroker3.aio.define.WEB3AIOAccountTypeDef;
import webbroker3.aio.define.WEB3AdminAioCashStatusDef;
import webbroker3.aio.define.WEB3AdminAioDivDef;
import webbroker3.aio.define.WEB3AdminAioOrderTypeDef;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListResponse;
import webbroker3.aio.message.WEB3AioAdminCashTransferListUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferRangeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者入出金一覧サービスImpl)<BR>
 * 管理者入出金一覧サービス実装クラス<BR>
 *
 * @@author 車進(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioListServiceImpl implements WEB3AdminAioListService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioListServiceImpl.class);

    /**
     * @@roseuid 45C425950295
     */
    public WEB3AdminAioListServiceImpl()
    {

    }

    /**
     * 管理者入出金一覧処理を行う。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B8122A0296
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminAioCashTransferListRequest)
        {
            l_response =
                this.getListScreen((WEB3AdminAioCashTransferListRequest)l_request);

        }
        else if (l_request instanceof WEB3AdminAioCashTransferListInputRequest)
        {
            l_response =
                this.getInputScreen((WEB3AdminAioCashTransferListInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAioCashTransferListDownloadRequest)
        {
            l_response =
                this.getDownloadFile((WEB3AdminAioCashTransferListDownloadRequest)l_request);
        }
        else
        {
           log.exiting(STR_METHOD_NAME);
           log.debug("パラメータタイプ不正");
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80018,
               this.getClass().getName() + "." + STR_METHOD_NAME);
         }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 入出金一覧条件入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者入出金一覧）get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 入出金一覧入力リクエストオブジェクト<BR>
     * @@return WEB3AdminAioCashTransferListInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B8122E02D5
     */
    protected WEB3AdminAioCashTransferListInputResponse getInputScreen(
        WEB3AdminAioCashTransferListInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAioCashTransferListInputRequest)";
        log.entering(STR_METHOD_NAME);

        //管理者オブジェクトを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //管理者の権限チェックを行う。
        //機@能カテゴリーコード = 機@能カテゴリコード：　@"B0101"
        //is更新 = false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //レスポンスデータを生成する。
        WEB3AdminAioCashTransferListInputResponse l_response =
            (WEB3AdminAioCashTransferListInputResponse)l_request.createResponse();

        //レスポンスデータプロパティに値をセットする。
        l_response.deliveryDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get一覧画面)<BR>
     * 入出金一覧結果画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者入出金一覧）get一覧画面」参照<BR>
     * ========================================================== <BR>
     * シーケンス図(「（管理者入出金一覧）get一覧画面」) <BR>
     * （get一覧画面）getListScreen <BR>
     * :1.9.1 顧客マスタから、レコードを取得なしの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01987<BR>
     * <BR>
     * :1.13 注文単位テーブルから、レコードを取得なしの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 入出金一覧結果リクエストオブジェクト<BR>
     * @@return WEB3AdminAioCashTransferListResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B8128603B0
     */
    protected WEB3AdminAioCashTransferListResponse getListScreen(
        WEB3AdminAioCashTransferListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminAioCashTransferListRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //管理者オブジェクトを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //管理者の権限チェックを行う。
        //機@能カテゴリーコード = 機@能カテゴリコード：　@"B0101"
        //is更新 = false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //該当の管理者が指定した部店に対する処理権限があるかチェックする。
        l_administrator.validateBranchPermission(l_request.branchCode);

        //リストのインスタンスを生成する。
        List l_lisBranchIds = new ArrayList();

        //証券会社オブジェクトを取得する。
        Institution l_institution = l_administrator.getInstitution();

        //リクエストデータ.部店コードの各要素のlength
        int l_intSize = l_request.branchCode.length;

        try
        {
            //リクエストデータ.部店コードの各要素ごとにLOOP処理
            for (int i = 0; i < l_intSize; i++)
            {
               // 部店インスタンスを生成する。
               //［引数］
               //証券会社： get証券会社()の戻り値
               //部店コード： リクエストデータ.部店コードの要素
               BranchImpl l_branchImpl = new BranchImpl(l_institution, l_request.branchCode[i]);

               //部店IDを取得する。
               long l_lngBranchId = l_branchImpl.getBranchId();

               //部店IDをリストに追加する。
               //[引数]
               //arg0： 部店ID
               l_lisBranchIds.add(new Long(l_lngBranchId));

            }

        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //部店IDの配列を取得する。
        int l_intListSize = l_lisBranchIds.size();
        Long[] l_lonBranchIds = new Long [l_intListSize];
        l_lisBranchIds.toArray(l_lonBranchIds);
        long[] l_lngBranchIds = new long[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_lngBranchIds[i] = l_lonBranchIds[i].longValue();
        }

        MainAccount l_mainAccount = null;
        //リクエストデータ.顧客コード != nul の場合、以下の処理を行う。
        if (l_request.accountCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)
                l_finApp.getAccountManager();
            //証券会社コード
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            //部店コード：　@リクエスト.部店コード[0]
            String l_strBranceCode = l_request.branchCode[0];
            //顧客コード：　@リクエスト.顧客コード
            String l_strAccountCode = l_request.accountCode;

            try
            {
                l_mainAccount =
                    l_accountManager.getMainAccount(
                        l_strInstitutionCode, l_strBranceCode, l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("顧客コードに対応する顧客は登録されていません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        // データ検索条件文字列を生成する。
        //［引数］
        // 部店ID： 部店IDの配列
        // 顧客： 顧客
        // 受渡日： リクエストデータ.受渡日
        // 注文種別： リクエストデータ.注文種別
        // ステータス：　@リクエストデータ.ステータス
        String l_strCondition =
            this.createQueryString(
                l_lngBranchIds,
                l_mainAccount,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        log.debug("create検索条件文字列 = " + l_strCondition);

        //検索条件データコンテナを生成する。
        //［引数］
        //部店ID： 部店IDの配列
        //顧客ID： 顧客.getAccountId()
        //受渡日： リクエストデータ.受渡日
        //注文種別： リクエストデータ.注文種別
        //ステータス：　@リクエストデータ.ステータス
        Object[] l_strContainer =
            this.createQueryContainer(
                l_lngBranchIds,
                l_mainAccount,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        //クエリプロセッサを取得する。
        //注文単位テーブルから、レコードを取得する。
        //［引数］
        //arg0： 注文単位Row.TYPE
        //arg1： create検索条件文字列()の戻り値
        //arg2： "branch_id asc , received_date_time desc"
        //arg3： null
        //arg4： create検索条件データコンテナ()の戻り値
        List l_lisRows = null;
        String l_strSort = "branch_id asc , received_date_time desc";
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strCondition,
                    l_strSort,
                    null,
                    l_strContainer);

            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.debug("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }

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

        //レスポンスデータを生成する。
        WEB3AdminAioCashTransferListResponse l_response =
            (WEB3AdminAioCashTransferListResponse)l_request.createResponse();

        //リストのインスタンスを生成する。
        List l_lisDetails = new ArrayList();

        //注文単位レコードの要素分、LOOP処理を行う。
        int l_intLength = l_lisRows.size();
        for (int i = 0; i < l_intLength; i++)
        {
            //注文種別毎のサマリ計算処理を行う。
            //[引数]
            //注文単位Params： 注文単位レコードの要素
            //レスポンスデータ： 入出金一覧結果レスポンス
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(i);
            AioOrderUnitParams l_orderUnitParams = new AioOrderUnitParams(l_aioOrderUnitRow);
            this.calTotalAmount(l_orderUnitParams, l_response);

            //入出金一覧明細オブジェクトを生成する。
            //[引数]
            //注文単位Params： 注文単位Params
            WEB3AioAdminCashTransferListUnit l_tranList =
                this.createAioListDetails(l_orderUnitParams);

            //「入出金一覧明細」をリストに追加する。
            //[引数]
            //Object： 入出金一覧明細オブジェクト
            l_lisDetails.add(l_tranList);

        }

        //入出金一覧明細の配列を取得する。
        WEB3AioAdminCashTransferListUnit[] l_TransferListUnit =
            new WEB3AioAdminCashTransferListUnit[l_lisDetails.size()];
        l_lisDetails.toArray(l_TransferListUnit);

        //要求ページで表示対象となる明細を取得する。
        //［引数］
        //明細： 入出金一覧明細の配列
        //fromIndex： (*6)－１）の計算結果
        //toIndex： (*6)－２）の計算結果

        //ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        //要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_TransferListUnit, l_intPageIndex, l_intPageSize);

        WEB3AioAdminCashTransferListUnit[] l_cashTransferListUnits  = 
            (WEB3AioAdminCashTransferListUnit[])l_pageIndexInfo.getArrayReturned(WEB3AioAdminCashTransferListUnit.class);
        //プロパティセット
        l_response.cashTransferListUnits = l_cashTransferListUnits;
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getダウンロードファ@イル)<BR>
     * 入出金一覧ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者入出金一覧）getダウンロードファ@イル」参照<BR>
     * ========================================================== <BR>
     * シーケンス図(「（管理者入出金一覧）getダウンロードファ@イル」) <BR>
     * （getダウンロードファ@イル）getDownloadFile <BR>
     * :1.9.1 顧客マスタから、レコードを取得なしの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01987<BR>
     * <BR>
     * :1.13 注文単位テーブルから、レコードを取得なしの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * <BR>
     * ========================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 入出金一覧ダウンロードリクエストオブジェクト<BR>
     * @@return WEB3AdminAioCashTransferListDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B813C100B8
     */
    protected WEB3AdminAioCashTransferListDownloadResponse getDownloadFile(
        WEB3AdminAioCashTransferListDownloadRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDownloadFile(WEB3AdminAioCashTransferListDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //管理者オブジェクトを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //管理者の権限チェックを行う。
        //機@能カテゴリーコード = 機@能カテゴリコード：　@"B0101"
        //is更新 = false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //該当の管理者が指定した部店に対する処理権限があるかチェックする。
        l_administrator.validateBranchPermission(l_request.branchCode);

        //リストのインスタンスを生成する。
        List l_lisBranchIds = new ArrayList();

        //証券会社オブジェクトを取得する。
        Institution l_institution = l_administrator.getInstitution();
        //リクエストデータ.部店コードの各要素のlength
        int l_intSize = l_request.branchCode.length;

        try
        {
            //リクエストデータ.部店コードの各要素ごとにLOOP処理
            for (int i = 0; i < l_intSize; i++)
            {
               // 部店インスタンスを生成する。
               //［引数］
               //証券会社： get証券会社()の戻り値
               //部店コード： リクエストデータ.部店コードの要素
               BranchImpl l_branchImpl = new BranchImpl(l_institution, l_request.branchCode[i]);

               //部店IDを取得する。
               long l_lngBranchId = l_branchImpl.getBranchId();

               //部店IDをリストに追加する。
               //[引数]
               //arg0： 部店ID
               l_lisBranchIds.add(new Long(l_lngBranchId));
            }
        }

        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //部店IDの配列を取得する。
        int l_intListSize = l_lisBranchIds.size();
        Long[] l_lonBranchIds = new Long [l_intListSize];
        l_lisBranchIds.toArray(l_lonBranchIds);
        long[] l_lngBranchIds = new long[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_lngBranchIds[i] = l_lonBranchIds[i].longValue();
        }

        MainAccount l_mainAccountTemp = null;
        //リクエストデータ.顧客コード != nul の場合、以下の処理を行う。
        if (l_request.accountCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)
                l_finApp.getAccountManager();
            //証券会社コード
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            //部店コード：　@リクエスト.部店コード[0]
            String l_strBranceCode = l_request.branchCode[0];
            //顧客コード：　@リクエスト.顧客コード
            String l_strAccountCode = l_request.accountCode;

            try
            {
                l_mainAccountTemp =
                    l_accountManager.getMainAccount(
                        l_strInstitutionCode, l_strBranceCode, l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("顧客コードに対応する顧客は登録されていません。", l_ex);
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        // データ検索条件文字列を生成する。
        //［引数］
        // 部店ID： 部店IDの配列
        // 顧客ID： 顧客.getAccountId()
        // 受渡日： リクエストデータ.受渡日
        // 注文種別： リクエストデータ.注文種別
        // ステータス：　@リクエストデータ.ステータス
        String l_strCondition =
            this.createQueryString(
                l_lngBranchIds,
                l_mainAccountTemp,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        log.debug("create検索条件文字列 = " + l_strCondition);

        //検索条件データコンテナを生成する。
        //［引数］
        //部店ID： 部店IDの配列
        //顧客ID： 顧客.getAccountId()
        //受渡日： リクエストデータ.受渡日
        //注文種別： リクエストデータ.注文種別
        //ステータス：　@リクエストデータ.ステータス
        Object[] l_strContainer =
            this.createQueryContainer(
                l_lngBranchIds,
                l_mainAccountTemp,
                l_request.deliveryDate,
                l_request.orderType,
                l_request.cashinoutStatus);

        //クエリプロセッサを取得する。
        //注文単位テーブルから、レコードを取得する。
        //［引数］
        //arg0： 注文単位Row.TYPE
        //arg1： create検索条件文字列()の戻り値
        //arg2： "branch_id asc , received_date_time desc"
        //arg3： null
        //arg4： create検索条件データコンテナ()の戻り値
        List l_lisRows = null;
        String l_strSort = "branch_id asc , received_date_time desc";
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strCondition,
                    l_strSort,
                    null,
                    l_strContainer);

            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.debug("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }

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

        //コンストラクタ。
        WEB3AdminAioListDownloadCSV l_aioListDownLoadCsv =
            new WEB3AdminAioListDownloadCSV();

        //注文単位レコードの要素分、LOOP処理を行う。
        int l_intLength = l_lisRows.size();

        try
        {
            for (int i = 0; i < l_intLength; i++)
            {
               AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(i);
               //明細行を追加する。
               l_aioListDownLoadCsv.addRow();

               //部店オブジェクトを取得する。
               //［引数］
               //部店ID： 注文単位Row.get部店ID()
               BranchImpl l_branchImpl = new BranchImpl(l_aioOrderUnitRow.getBranchId());

               //部店コードを取得する。
               String l_strBranchCode = l_branchImpl.getBranchCode();

               //アカウントマネージャ取得する
               FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
               AccountManager l_accMgr = l_finApp.getAccountManager();
               WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)
                   l_accMgr.getMainAccount(l_aioOrderUnitRow.getAccountId());
               //顧客コード（表示用）を取得する。
               String l_strAccountCode = l_mainAccount.getDisplayAccountCode();
               //顧客の名称を取得する
               String l_strAccountName = l_mainAccount.getDisplayAccountName();

               //注文種別をセットする。
               //[set注文種別()に指定する引数]
               //行番号：　@処理対象index
               //注文種別：　@注文単位Row.get注文種別( )
               //注文経路区分：　@注文単位Row.get注文経路区分( )
               //　@.comデビット決済取引番号：注文単位Row.get.comデビット決済取引番号( )
               l_aioListDownLoadCsv.setOrderType(i,
                   l_aioOrderUnitRow.getOrderType().intValue() + "",
                   l_aioOrderUnitRow.getOrderRootDiv(),
                   l_aioOrderUnitRow.getComDebitNumber());

               //受渡日をセットする。
               //[set受渡日()に指定する引数]
               // 行番号：　@処理対象index
               // 受渡日：　@注文単位Row.get受渡日( )
               l_aioListDownLoadCsv.setDeliveryDate(i, l_aioOrderUnitRow.getDeliveryDate());

               //部店コードをセットする。
               //行番号：　@処理対象index
               //部店ＩＤ：　@部店.getBranchCode()の戻り値
               l_aioListDownLoadCsv.setBranchCode(i, l_strBranchCode);

               //顧客コードをセットする。
               //行番号：　@処理対象index
               //口座ＩＤ：　@顧客.get表示顧客コード()の戻り値
               l_aioListDownLoadCsv.setAccountCode(i, l_strAccountCode);

               //顧客名をセットする。
               //行番号：　@処理対象index
               //口座ＩＤ：　@顧客.get顧客表示名()の戻り値
               l_aioListDownLoadCsv.setAccountName(i, l_strAccountName);

               //注文日時をセットする。
               //行番号：　@処理対象index
               //注文日時：　@注文単位Row.get受注日時( )
               l_aioListDownLoadCsv.setOrderDate(i, l_aioOrderUnitRow.getReceivedDateTime());

               //ステータスをセットする。
               //行番号：　@処理対象index
               //ステータス：　@注文単位Row.getステータス( )
               //注文取消区分：　@注文単位Row.get注文取消区分( )
               l_aioListDownLoadCsv.setStatus(i,
                   l_aioOrderUnitRow.getOrderStatus().intValue() + "",
                   l_aioOrderUnitRow.getCancelType());

               //引数.注文種別から入出金を判別し、返却する。
               //［引数］
               //顧客：　@注文単位Row.get注文種別( )
               String l_strAioDev = this.getAioDev(
                   String.valueOf(l_aioOrderUnitRow.getOrderType().intValue()));

               //入金金額、出金金額をセットする。
               //[set入金金額()に指定する引数]
               //行番号：　@処理対象index
               //金額：　@注文単位Row.get注文数量( )
               //入出金区分：　@get入出金区分()の戻り値
               l_aioListDownLoadCsv.setCash(i,
                   WEB3StringTypeUtility.formatNumber(l_aioOrderUnitRow.getQuantity()),
                   l_strAioDev);

               //入力者をセットする。
               //行番号：　@処理対象index
               //・注文単位Row.get注文経路区分() == "1(コールセンター)"の場合、 
               //入力者：　@注文単位Row.get取引者ＩＤ() 
               //・注文単位Row.get注文経路区分() == "9(HOST)"の場合、 
               //入力者：　@注文単位Row.get扱者コード() 
               //・上記以外は、各項目にnullをセット
               String l_strTrader = null;
               if (WEB3OrderRootDivDef.CALLCENTER.equals(
                   l_aioOrderUnitRow.getOrderRootDiv()))
               {
                   l_strTrader = l_aioOrderUnitRow.getTraderId() + "";
               }
               else if (WEB3OrderRootDivDef.HOST.equals(
                   l_aioOrderUnitRow.getOrderRootDiv()))
               {
                   l_strTrader = l_aioOrderUnitRow.getSonarTraderCode() + "";
               }
               l_aioListDownLoadCsv.setTrader(i,l_strTrader);

               //入力経路をセットする。
               //行番号：　@処理対象index
               //入力経路：　@注文単位Row.get入力経路( )
               l_aioListDownLoadCsv.setInputRoot(i,
                   l_aioOrderUnitRow.getOrderRootDiv());

               String l_strBrankCode = null;
               String l_strBranchCd = null;
               String l_strAccountType = null;
               String l_strAccountNumber = null;

               List l_lisFinIns = new ArrayList();
               //注文単位Row.get注文種別 == "1001(出金注文)"の場合、以下の処理を行う。
               if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitRow.getOrderType()))
               {
                   //検索条件から取得した振込先金融機@関レコードをList型で返却する。
                   //［引数］
                   //顧客：　@MainAccountImpl（）で生成した顧客オブジェクト
                   l_lisFinIns = this.getTransferedFinInstitutionRecord(l_mainAccount);
               }

               //(*7)以下の条件の場合、処理を行う。
               //( 注文単位Row.注文種別 == "1011(為替保証金振替注文(預り金から為替保証金))"
               //or 注文単位Row.注文種別 == "1012(為替保証金振替(為替保証金から預り金))" )
               //and 注文単位Row.摘要名 != null
               if ((OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                   l_aioOrderUnitRow.getOrderType())
                   || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                       l_aioOrderUnitRow.getOrderType()))
                   && l_aioOrderUnitRow.getRemarkName() != null)
               {
                   //set口座情報(int)
                   //[set口座情報()に指定する引数]
                   //行番号：　@処理対象index
                   l_aioListDownLoadCsv.setAccountInfo(i);
               }
               //（*7）以外の場合、以下の処理を行う
               else
               {
                   //注文単位Row.get注文種別() == "1002(入金注文)"且つ、
                   //注文単位Row.get注文経路区分 == "D(入金連携)"の場合
                   if (OrderTypeEnum.CASH_IN.equals(l_aioOrderUnitRow.getOrderType()) &&
                       WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_aioOrderUnitRow.getOrderRootDiv()))
                   {
                       String l_strDescription = l_aioOrderUnitRow.getDescription();
                       if (WEB3StringTypeUtility.isNotEmpty(l_strDescription))
                       {
                           //銀行コード：　@注文単位Row.get振替記述()の1桁目～4桁目(substring(0,4))
                           l_strBrankCode = l_strDescription.substring(0, 4);

                           //支店コード：　@注文単位Row.get振替記述()の5桁目～7桁目(substring(4,7))
                           l_strBranchCd = l_strDescription.substring(4, 7);

                           //口座種別：　@注文単位Row.get振替記述()の8桁目(substring(7,8))
                           l_strAccountType = l_strDescription.substring(7, 8);

                           //口座番号：　@注文単位Row.get振替記述()の9桁目～15桁目(substring(8,15))
                           l_strAccountNumber = l_strDescription.substring(8, 15);
                       }
                   }

                   //注文単位Row.get注文種別() == "1001(出金注文)"且つ、
                   //get振込先金融機@関()の戻り値の要素数 != 0 の場合、
                   if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitRow.getOrderType()) &&
                       l_lisFinIns.size() != 0)
                   {
                       TransferedFinInstitutionRow l_transferedFinInstitutioRow
                               = (TransferedFinInstitutionRow)l_lisFinIns.get(0);
                       //銀行コード：　@get振込先金融機@関レコードの戻り値.get(0).get銀行コード
                       l_strBrankCode = l_transferedFinInstitutioRow.getFinInstitutionCode();

                       //支店コード：　@get振込先金融機@関レコードの戻り値.get(0).get支店コード
                       l_strBranchCd = l_transferedFinInstitutioRow.getFinBranchCode();

                       //口座種別：　@get振込先金融機@関レコードの戻り値.get(0).get預金区分
                       l_strAccountType = l_transferedFinInstitutioRow.getFinSaveDiv();

                       //口座番号：　@get振込先金融機@関レコードの戻り値.get(0).get口座番号
                       l_strAccountNumber = l_transferedFinInstitutioRow.getFinAccountNo();
                   }

                   l_aioListDownLoadCsv.setAccountInfo(
                       i,
                       l_strBrankCode,
                       l_strBranchCd,
                       l_strAccountType,
                       l_strAccountNumber);
               }

            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //CSVファ@イル行を取得する。
        String[] l_strFileLines = l_aioListDownLoadCsv.getCsvFileLines();

        //レスポンスデータを生成する。
        WEB3AdminAioCashTransferListDownloadResponse l_response =
            (WEB3AdminAioCashTransferListDownloadResponse)l_request.createResponse();

        //プロパティセット
       l_response.downloadFile = l_strFileLines;
       l_response.currentDate = GtlUtils.getSystemTimestamp();

       log.exiting(STR_METHOD_NAME);
       return l_response;
    }

    /**
     * (create入出金一覧明細)<BR>
     * 入出金一覧明細オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者入出金一覧）create入出金一覧明細」参照<BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位Paramsオブジェクト<BR>
     * @@return WEB3AioAdminCashTransferListUnit
     * @@throws WEB3BaseException
     * @@roseuid 45BD54910278
     */
    protected WEB3AioAdminCashTransferListUnit createAioListDetails(
        AioOrderUnitParams l_orderUnitParams)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAioListDetails(AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //インスタンスを生成する。
        WEB3AioAdminCashTransferListUnit l_aioAdminCashTransferListUnit =
            new WEB3AioAdminCashTransferListUnit();

        BranchImpl l_branchImpl = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        String l_strBranchCode = null;
        try
        {
            //部店オブジェクトを取得する。
            //［引数］
            //部店ID： 引数.注文単位Params.部店ID
            l_branchImpl = new BranchImpl(l_orderUnitParams.getBranchId());

            //部店コードを取得する
            l_strBranchCode = l_branchImpl.getBranchCode();

            //アカウントマネージャ取得する
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(l_orderUnitParams.getAccountId());

        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //顧客コード（表示用）を取得する。
        String l_strAccountCode = l_mainAccount.getDisplayAccountCode();
        //顧客の名称を取得する
        String l_strAccountName = l_mainAccount.getDisplayAccountName();

        //引数.注文種別から入出金を判別し、返却する。
        //顧客：　@引数.注文単位Params.注文種別
        String l_strAioDev = this.getAioDev(
            String.valueOf(l_orderUnitParams.getOrderType().intValue()));
        List l_lisFinIns = new ArrayList();
        //注文単位Row.get注文種別 == "1001(出金注文)"の場合、以下の処理を行う。
        if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitParams.getOrderType()))
        {
            //検索条件から取得した振込先金融機@関レコードをList型で返却する。
            //［引数］
            //顧客：　@MainAccountImpl（）で生成した顧客オブジェクト
            l_lisFinIns = this.getTransferedFinInstitutionRecord(l_mainAccount);

        }

        //入出金一覧データプロパティに値をセットする。
        String l_strOrderType = null;
        String l_strStatus = null;
        String l_strInCash = null;
        String l_strOutCash = null;
        String l_strOperatorCode = null;
        String l_strBrankCode = null;
        String l_strBranchCd = null;
        String l_strAccountType = null;
        String l_strAccountNumber = null;
        if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()))
        {
            if (WEB3OrderRootDivDef.HOST.equals(l_orderUnitParams.getOrderRootDiv()))
            {
                l_strOrderType = WEB3AdminAioOrderTypeDef.SONAR_CASHIN;
            }
            else if (WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(
                l_orderUnitParams.getOrderRootDiv()))
            {
                l_strOrderType = WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN;
            }
            else if (l_orderUnitParams.getComDebitNumber() != null)
            {
                l_strOrderType = WEB3AdminAioOrderTypeDef.NET_CASHIN;
            }
        }
        else if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHIN_TRANSFER_DEPOSIT_TO_MARGIN;
        }
        else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHIN_FX_GUARANTY_TO_DESPOSIT;
        }
        else if (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHIN_OTHER_X_TO_ACCOUNT_BALANCE;
        }
        else if (OrderTypeEnum.CASH_OUT.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT;
        }
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN;
        }
        else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT_FX_GUARANTY_TO_DESPOSIT;
        }
        else if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(
            l_orderUnitParams.getOrderType()))
        {
            l_strOrderType = WEB3AdminAioOrderTypeDef.CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE;
        }

        OrderStatusEnum l_strOrderStatus = l_orderUnitParams.getOrderStatus();
        String l_strOrderCancelDiv = l_orderUnitParams.getCancelType();
        if (OrderStatusEnum.ORDERED.equals(l_strOrderStatus) &&
            WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strStatus = WEB3AdminAioCashStatusDef.COMPLETE;
        }
        else if ((OrderStatusEnum.ACCEPTED.equals(l_strOrderStatus)  ||
                OrderStatusEnum.ORDERING.equals(l_strOrderStatus))&&
            WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strStatus = WEB3AdminAioCashStatusDef.NOT_TRANSACTION;
        }
        else if (OrderStatusEnum.NOT_ORDERED.equals(l_strOrderStatus))
        {
            l_strStatus = WEB3AdminAioCashStatusDef.ERROR;
        }

        if (WEB3AdminAioDivDef.CASH_IN.equals(l_strAioDev))
        {
            l_strInCash = WEB3StringTypeUtility.formatNumber(l_orderUnitParams.getQuantity());
        }

        if (WEB3AdminAioDivDef.CASH_OUT.equals(l_strAioDev))
        {
            l_strOutCash = WEB3StringTypeUtility.formatNumber(l_orderUnitParams.getQuantity());
        }

        if (WEB3OrderRootDivDef.CALLCENTER.equals(l_orderUnitParams.getOrderRootDiv()))
        {
            l_strOperatorCode = l_orderUnitParams.getTraderId() + "";
        }
        else if ((WEB3OrderRootDivDef.HOST.equals(l_orderUnitParams.getOrderRootDiv())))
        {
            l_strOperatorCode = l_orderUnitParams.getSonarTraderCode();
        }

        if (WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(l_strOrderType))
        {
            String l_strDescription = l_orderUnitParams.getDescription();
            if (WEB3StringTypeUtility.isNotEmpty(l_strDescription))
            {
                //銀行コード：　@注文単位Row.get振替記述()の1桁目～4桁目(substring(0,4))
                l_strBrankCode = l_strDescription.substring(0, 4);

                //支店コード：　@注文単位Row.get振替記述()の5桁目～7桁目(substring(4,7))
                l_strBranchCd = l_strDescription.substring(4, 7);

                //口座種別：　@注文単位Row.get振替記述()の8桁目(substring(7,8))
                l_strAccountType = l_strDescription.substring(7, 8);

                //口座番号：　@注文単位Row.get振替記述()の9桁目～15桁目(substring(8,15))
                l_strAccountNumber = l_strDescription.substring(8, 15);
            }

        }

        //注文種別() == "201且つ、
        //get振込先金融機@関()の戻り値の要素数 != 0 の場合、
        if (WEB3AdminAioOrderTypeDef.CASHOUT.equals(l_strOrderType) &&
            l_lisFinIns.size() != 0)
        {
            TransferedFinInstitutionRow l_transferedFinInstitutioRow
                = (TransferedFinInstitutionRow)l_lisFinIns.get(0);
            //銀行コード：　@get振込先金融機@関レコードの戻り値.get(0).get銀行コード
            l_strBrankCode = l_transferedFinInstitutioRow.getFinInstitutionCode();

            //支店コード：　@get振込先金融機@関レコードの戻り値.get(0).get支店コード
            l_strBranchCd = l_transferedFinInstitutioRow.getFinBranchCode();

            //口座種別：　@get振込先金融機@関レコードの戻り値.get(0).get預金区分
            l_strAccountType = l_transferedFinInstitutioRow.getFinSaveDiv();

            //口座番号：　@get振込先金融機@関レコードの戻り値.get(0).get口座番号
            l_strAccountNumber = l_transferedFinInstitutioRow.getFinAccountNo();
        }

        //注文単位params.注文種別 == "1011(為替保証金振替注文(預り金から為替保証金))"
        //または、注文単位params.注文種別 == "1012(為替保証金振替(為替保証金から預り金))"
        //・引数.注文単位params.摘要名 != null
        //口座種別：　@5:大証FX
        if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_orderUnitParams.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                l_orderUnitParams.getOrderType()))
        {
            if (l_orderUnitParams.getRemarkName() != null)
            {
                l_strAccountType = WEB3AIOAccountTypeDef.OSAKA_FX;
            }
        }

        l_aioAdminCashTransferListUnit.orderType = l_strOrderType;
        l_aioAdminCashTransferListUnit.deliveryDate = l_orderUnitParams.getDeliveryDate();
        l_aioAdminCashTransferListUnit.branchCode = l_strBranchCode;
        l_aioAdminCashTransferListUnit.accountCode = l_strAccountCode;
        l_aioAdminCashTransferListUnit.accountName = l_strAccountName;
        l_aioAdminCashTransferListUnit.orderDate = l_orderUnitParams.getReceivedDateTime();
        l_aioAdminCashTransferListUnit.cashinoutStatus = l_strStatus;

        l_aioAdminCashTransferListUnit.cashinAmt = l_strInCash;
        l_aioAdminCashTransferListUnit.cashoutAmt = l_strOutCash;
        l_aioAdminCashTransferListUnit.operatorCode = l_strOperatorCode;
        l_aioAdminCashTransferListUnit.orderRoutDiv = l_orderUnitParams.getOrderRootDiv();
        l_aioAdminCashTransferListUnit.financialInstitutionCode = l_strBrankCode;
        l_aioAdminCashTransferListUnit.financialBranchCode = l_strBranchCd;
        l_aioAdminCashTransferListUnit.accountTypeCode = l_strAccountType;
        l_aioAdminCashTransferListUnit.financialAccountCode = l_strAccountNumber;

        log.exiting(STR_METHOD_NAME);
        return l_aioAdminCashTransferListUnit;
    }

    /**
     * (create検索条件文字列)<BR>
     * リクエストデータから、データ検索条件文字列を生成する。<BR>
     * <BR>
     * １）戻り値生成  <BR>
     * 　@・戻り値の検索条件文字列インスタンス（：String）を生成。 <BR>
     * <BR>
     * ２）部店ID<BR>
     * 　@・部店条件を追加する。<BR>
     * <BR>
     * 　@２－１）引数.部店IDの要素数 = 1 の場合<BR>
     * <BR>
     * 　@  "branch_id=?"<BR>
     * <BR>
     * 　@２－２）引数.部店IDの要素数 > 1 の場合<BR>
     * <BR>
     * 　@"branch_id in(?,?,? ... ?)"<BR>
     *   ※括弧内の「?」の数が要素数分あるようにする<BR>
     * <BR>
     * ３）口座ID<BR>
     * 　@・引数.顧客オブジェクト != null の場合、口座ID条件を追加する。<BR>
     * <BR>
     * 　@  " and account_id=?"<BR>
     * <BR>
     * ４）受渡日<BR>
     *   ・受渡日条件を追加する。<BR>
     * <BR>
     * 　@  " and delivery_date=?"<BR>
     * <BR>
     * ５）注文種別<BR>
     * 　@・注文種別指定の場合（引数.注文種別 != null）、注文種別条件を追加する。<BR>
     * <BR>
     * 　@５－１）引数.注文種別 = "000(全て)" の場合<BR>
     * 　@　@" and ((order_type=? and order_root_div=?) or <BR>
     * 　@　@　@　@　@ (order_type=? and order_root_div=?) or <BR>
     * 　@　@　@　@　@ (order_type=? and com_debit_number is not null) or <BR>
     * 　@　@　@　@　@ order_type in(?,?,?,?,?,?,?))"<BR>
     * <BR>
     * 　@５－２）引数.注文種別 = "100(入金_全て)" の場合<BR>
     * 　@  " and ((order_type=? and order_root_div=?) or <BR>
     * 　@　@　@　@　@ (order_type=? and order_root_div=?) or <BR>
     * 　@　@　@　@　@ (order_type=? and com_debit_number is not null) or <BR>
     * 　@　@　@　@　@ order_type in(?,?,?))"<BR>
     * <BR>
     * 　@５－３）引数.注文種別 = "101(SONAR入金)" or "102(バーチャル入金)" の場合<BR>
     * 　@  " and order_type=? and order_root_div=?"<BR>
     * <BR>
     * 　@５－４）引数.注文種別 = "103(ネット入金)"  の場合<BR>
     * 　@  " and order_type=? and com_debit_number is not null"<BR>
     * <BR>
     * 　@５－５）引数.注文種別 = "200(出金_全て)" の場合<BR>
     * 　@  " and order_type in(?,?,?,?)"<BR>
     * <BR>
     * 　@５－６）上記以外 の場合<BR>
     * 　@  " and order_type=?"<BR>
     * <BR>
     * ６）ステータス<BR>
     * 　@・引数.ステータス != null の場合、ステータス条件を追加する。<BR>
     * <BR>
     * 　@６－１）引数.ステータス = "0(全て)" の場合<BR>
     * <BR>
     * 　@  " and ((order_status in(?,?,?) and cancel_type=?) or order_status=?")<BR>
     * <BR>
     * 　@６－２）引数.ステータス = "1(未処理)" の場合<BR>
     * 　@  " and order_status in(?,?) and cancel_type=?"<BR>
     * <BR>
     * 　@６－３）引数.ステータス = "2(完了)" の場合<BR>
     * 　@  " and order_status=? and cancel_type=?"<BR>
     * <BR>
     * 　@６－４）引数.ステータス = "9(エラー)" の場合<BR>
     * 　@  " and order_status=?"<BR>
     * <BR>
     * ７）　@文字列インスタンスを返却<BR>
     * @@param l_lngBranchIds - (部店ID)<BR>
     * 部店IDの配列<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_strOrderType - (注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * @@param l_strOrderStatus - (ステータス)<BR>
     * ステータス<BR>
     * <BR>
     * @@return String
     * @@roseuid 45B83D82013A
     */
    protected String createQueryString(long[] l_lngBranchIds,
        MainAccount l_mainAccount,
        Date l_datDeliveryDate,
        String l_strOrderType,
        String l_strOrderStatus)
    {
        final String STR_METHOD_NAME = "createQueryString(long[], MainAccount, Date, String, String)";
        log.entering(STR_METHOD_NAME);

        // リクエストデータから、データ検索条件文字列を生成する。
        StringBuffer l_strBuffer = new StringBuffer();

        // ２－１）引数.部店IDの要素数 = 1 の場合
        //  " branch_id=?"
        if (l_lngBranchIds.length == 1)
        {
            l_strBuffer.append(" branch_id=?");
        }

        // 　@２－２）引数.部店IDの要素数 > 1 の場合
        //　@" and branch_id in(?,?,? ... ?)"
        if (l_lngBranchIds.length > 1)
        {
            l_strBuffer.append(" branch_id in ( ?");
            for (int i = 1; i < l_lngBranchIds.length; i++)
            {
                l_strBuffer.append(", ? ");
            }
            l_strBuffer.append(")");
        }

        // ３）口座ID
        // ・引数.顧客オブジェクト != null の場合、口座ID条件を追加する
        // " account_id=?"
        if (l_mainAccount != null)
        {
            l_strBuffer.append(" and account_id=?");
        }      
        //   ・受渡日条件を追加する。
        // " and delivery_date=?"
        l_strBuffer.append(" and delivery_date=?");

        // 注文種別指定の場合（引数.注文種別 != null）、注文種別条件を追加する。
        if (l_strOrderType != null)
        {
            // ５－１）引数.注文種別 = "000(全て)" の場合
            // " and ((order_type=? and order_root_div=?) or
            // (order_type=? and order_root_div=?) or
            // (order_type=? and com_debit_number is not null) or
            // order_type in(?,?,?,?,?,?,?))"
            if (WEB3AdminAioOrderTypeDef.ALL.equals(l_strOrderType))
            {
                l_strBuffer.append(" and ((order_type=? and order_root_div=?) or" +
                    "  (order_type=? and order_root_div=?) or" +
                    "  (order_type=? and com_debit_number is not null) or" +
                    "  order_type in(?,?,?,?,?,?,?))");
            }
            // ５－２）引数.注文種別 = "100(入金_全て)" の場合
            // " and ((order_type=? and order_root_div=?) or
            // (order_type=? and order_root_div=?) or
            // (order_type=? and com_debit_number is not null) or
            // order_type in(?,?,?))"
            else if (WEB3AdminAioOrderTypeDef.CASHIN_ALL.equals(l_strOrderType))
            {
                l_strBuffer.append(" and ((order_type=? and order_root_div=?) or" +
                    "  (order_type=? and order_root_div=?) or" +
                    "  (order_type=? and com_debit_number is not null) or" +
                    "  order_type in(?,?,?))");
            }
            // ５－３）引数.注文種別 = "101(SONAR入金)" or "102(バーチャル入金)" の場合
            // " and order_type=? and order_root_div=?"
            else if (WEB3AdminAioOrderTypeDef.SONAR_CASHIN.equals(l_strOrderType) ||
                WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(l_strOrderType))
            {
                l_strBuffer.append(" and order_type=? and order_root_div=?");
            }
            // ５－４）引数.注文種別 = "103(ネット入金)"  の場合
            // " and order_type=? and com_debit_number is not null"
            else if (WEB3AdminAioOrderTypeDef.NET_CASHIN.equals(l_strOrderType))
            {
                l_strBuffer.append(" and order_type=? and com_debit_number is not null");
            }
            // ５－５）引数.注文種別 = "200(出金_全て)" の場合
            // " and order_type in(?,?,?,?)"
            else if (WEB3AdminAioOrderTypeDef.CASHOUT_ALL.equals(l_strOrderType))
            {
                l_strBuffer.append(" and order_type in(?,?,?,?)");
            }
            // ５－６）上記以外 の場合
            // " and order_type=?"
            else
            {
                l_strBuffer.append(" and order_type=?");
            }
        }

        // ・引数.ステータス != null の場合、ステータス条件を追加する。
        if (l_strOrderStatus != null)
        {
            // ６－１）引数.ステータス = "0(全て)" の場合
            // " and ((order_status in(?,?,?) and cancel_type=?) or order_status=?")
            if (WEB3AdminAioCashStatusDef.ALL.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and ((order_status in(?,?,?) and cancel_type=?) or order_status=?)");
            }
            // ６－２）引数.ステータス = "2(未処理)" の場合
            // " and order_status in(?,?) and cancel_type=?"
            else if (WEB3AdminAioCashStatusDef.NOT_TRANSACTION.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and order_status in(?,?) and cancel_type=?");
            }
            // ６－３）引数.ステータス = "1(完了)" の場合
            // " and order_status=? and cancel_type=?"
            else if (WEB3AdminAioCashStatusDef.COMPLETE.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and order_status=? and cancel_type=?");
            }
            // ６－４）引数.ステータス = "9(エラー)" の場合
            // " and order_status=?"
            else if (WEB3AdminAioCashStatusDef.ERROR.equals(l_strOrderStatus))
            {
                l_strBuffer.append(" and order_status=?");
            }
        }

        // ７）　@文字列インスタンスを返却
        log.exiting(STR_METHOD_NAME);
        return l_strBuffer.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータリストを生成する。<BR>
     * <BR>
     * １）戻り値生成  <BR>
     * 　@・戻り値編集用インスタンス（：ArrayList）を生成  <BR>
     * <BR>
     * ２）「部店ID」条件追加 <BR>
     * 　@・引数.部店ID[]内の部店IDをすべてリストに追加する。 <BR>
     * <BR>
     * 　@　@[add()に指定する引数]　@ <BR>
     * 　@　@部店ID<BR>
     * <BR>
     * ３）「口座ID」条件追加 <BR>
     * 　@・口座ＩＤ指定の場合（引数.口座ID != null）、口座IDをリストに追加する。<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@引数.顧客.getAccountId()<BR>
     * <BR>
     * ４）「受渡日」条件追加  <BR>
     * 　@・受渡日をリストに追加する。 <BR>
     * <BR>
     * 　@　@[add()に指定する引数]<BR>
     * 　@　@引数.受渡日<BR>
     * <BR>
     * ５）「注文種別」条件追加  <BR>
     * 　@・注文種別指定の場合（引数.注文種別 != null）、注文種別をリストに追加する。<BR>
     * <BR>
     * <BR>
     * 　@５－１）引数.注文種別 = "000(全て)" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.9：HOST、<BR>
     * 　@　@OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.D:入金連携、<BR>
     * 　@　@OrderTypeEnum.1002：入金注文、<BR>
     * 　@　@OrderTypeEnum.1008：振替注文(株先証拠金から預り金)、<BR>
     * 　@　@OrderTypeEnum.1012：為替保証金振替注文（為替保証金から預り金）、<BR>
     * 　@　@OrderTypeEnum.1018：その他振替注文（Xから預り金）、<BR>
     * 　@　@OrderTypeEnum.1001：出金注文、<BR>
     * 　@　@OrderTypeEnum.1007：振替注文(預り金から株先証拠金)、<BR>
     * 　@　@OrderTypeEnum.1011：為替保証金振替注文（預り金から為替保証金）、<BR>
     * 　@　@OrderTypeEnum.1017：その他振替注文（預り金からX）<BR>
     * <BR>
     * 　@５－２）引数.注文種別 = "100(入金_全て)" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.9：HOST、<BR>
     * 　@　@OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.D:入金連携、<BR>
     * 　@　@OrderTypeEnum.1002：入金注文、<BR>
     * 　@　@OrderTypeEnum.1008：振替注文(株先証拠金から預り金)、<BR>
     * 　@　@OrderTypeEnum.1012：為替保証金振替注文（為替保証金から預り金）、<BR>
     * 　@　@OrderTypeEnum.1018：その他振替注文（Xから預り金）<BR>
     * <BR>
     * 　@５－３）引数.注文種別 = "101(SONAR入金)" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.9：HOST<BR>
     * <BR>
     * 　@５－４）引数.注文種別 = "102(バーチャル入金)"  の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.D:入金連携<BR>
     * <BR>
     * 　@５－５）引数.注文種別 = "103(ネット入金)"  の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1002：入金注文<BR>
     * <BR>
     * 　@５－６）引数.注文種別 = "104(振替(株先証拠金から預り金))"  の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1008：振替注文(株先証拠金から預り金)<BR>
     * <BR>
     * 　@５－７）引数.注文種別 = "105(為替保証金振替(為替保証金から預り金))"
     * の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1012：為替保証金振替注文（為替保証金から預り金）<BR>
     * <BR>
     * 　@５－８）引数.注文種別 = "106(その他振替(Xから預り金))"  の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1018：その他振替注文（Xから預り金）<BR>
     * <BR>
     * 　@５－９）引数.注文種別 = "200(出金_全て)"  の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1001：出金注文、<BR>
     * 　@　@OrderTypeEnum.1007：振替注文(預り金から株先証拠金)、<BR>
     * 　@　@OrderTypeEnum.1011：為替保証金振替注文（預り金から為替保証金）、<BR>
     * 　@　@OrderTypeEnum.1017：その他振替注文（預り金からX）<BR>
     * <BR>
     * 　@５－１０）引数.注文種別 = "201(出金)"  の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1001：出金注文<BR>
     * <BR>
     * 　@５－１１）引数.注文種別 = "202(振替(預り金から株先証拠金))" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1007：振替注文(預り金から株先証拠金)<BR>
     * <BR>
     * 　@５－１２）引数.注文種別 = "203(為替保証金振替(預り金から為替保証金))"
     * の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1011：為替保証金振替注文（預り金から為替保証金）<BR>
     * <BR>
     * 　@５－１３）引数.注文種別 = "204(その他振替(預り金からX))" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderTypeEnum.1017：その他振替注文（預り金からX）<BR>
     * <BR>
     * ６）「ステータス」条件追加 <BR>
     * 　@・ステータス指定の場合（引数.ステータス != <BR>
     * null）、ステータスをリストに追加する。 <BR>
     * <BR>
     * 　@６－１）引数.ステータス = "0(全て)" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@　@OrderStatusEnum.1:受付済（新規注文）、OrderStatusEnum.2:発注中（新規注文）、<BR>
     * OrderStatusEnum.3:発注済（新規注文）、0:初期値、OrderStatusEnum.6:発注失敗<BR>
     * （新規注文）<BR>
     * <BR>
     * 　@６－２）引数.ステータス = "1(未処理)" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * <BR>
     * OrderStatusEnum.1:受付済（新規注文）、OrderStatusEnum.2:発注中（新規注文）、
     * 0:初期値<BR>
     * <BR>
     * 　@６－３）引数.ステータス = "2(完了)" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@  OrderStatusEnum.3:発注済（新規注文）、0:初期値<BR>
     * <BR>
     * 　@６－４）引数.ステータス = "9(エラー)" の場合<BR>
     * <BR>
     * 　@　@[add()に指定する引数] <BR>
     * 　@  OrderStatusEnum.6:発注失敗（新規注文）<BR>
     * <BR>
     * ７）配列を返却<BR>
     * 　@・戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>
     * @@param l_lngBranchIds - (部店ID)<BR>
     * 部店IDの配列<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_strOrderType - (注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * @@param l_strOrderStatus - (ステータス)<BR>
     * ステータス<BR>
     * <BR>
     * @@return Object[]
     * @@roseuid 45B83D86006F
     */
    protected Object[] createQueryContainer(
        long[] l_lngBranchIds,
        MainAccount l_mainAccount,
        Date l_datDeliveryDate,
        String l_strOrderType,
        String l_strOrderStatus)
    {
        final String STR_METHOD_NAME = "createQueryContainer(long[], MainAccount, Date, String, String)";
        log.entering(STR_METHOD_NAME);

        // 戻り値編集用インスタンス（：ArrayList）を生成
        List l_lisValue = new ArrayList();

        // 引数.部店ID[]内の部店IDをすべてリストに追加する。
        for (int i = 0; i < l_lngBranchIds.length; i++)
        {
            Long l_lngBranchId = new Long(l_lngBranchIds[i]);
            l_lisValue.add(l_lngBranchId);
        }

        // 口座ＩＤ指定の場合（引数.口座ID != null）、口座IDをリストに追加する。
        if (l_mainAccount!= null)
        {
            // [add()に指定する引数]
            // 引数.口座ID
            l_lisValue.add(new Long(l_mainAccount.getAccountId()));
        }

        // 受渡日をリストに追加する。
        l_lisValue.add(l_datDeliveryDate);

        // 注文種別指定の場合（引数.注文種別 != null）、注文種別条件を追加する。
        if (l_strOrderType != null)
        {
            // 引数.注文種別 = "000(全て)" の場合
            if (WEB3AdminAioOrderTypeDef.ALL.equals(l_strOrderType))
            {
                // [add()に指定する引数]
                // OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.9：HOST、
                // OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.D:入金連携、
                // OrderTypeEnum.1002：入金注文、
                // OrderTypeEnum.1008：振替注文(株先証拠金から預り金)、
                // OrderTypeEnum.1012：為替保証金振替注文（為替保証金から預り金）、
                // OrderTypeEnum.1018：その他振替注文（Xから預り金）、
                // OrderTypeEnum.1001：出金注文、
                // OrderTypeEnum.1007：振替注文(預り金から株先証拠金)、
                // OrderTypeEnum.1011：為替保証金振替注文（預り金から為替保証金）、
                // OrderTypeEnum.1017：その他振替注文（預り金からX）
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.HOST);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
                l_lisValue.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
                l_lisValue.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
                l_lisValue.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            }

            // ５－２）引数.注文種別 = "100(入金_全て)" の場合
            if (WEB3AdminAioOrderTypeDef.CASHIN_ALL.equals(l_strOrderType))
            {
                // [add()に指定する引数]
                // OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.9：HOST、
                // OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.D:入金連携、
                // OrderTypeEnum.1002：入金注文、
                // OrderTypeEnum.1008：振替注文(株先証拠金から預り金)、
                // OrderTypeEnum.1012：為替保証金振替注文（為替保証金から預り金）、
                // OrderTypeEnum.1018：その他振替注文（Xから預り金）
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.HOST);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
                l_lisValue.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
            }

            // ５－３）引数.注文種別 = "101(SONAR入金)" の場合
            if (WEB3AdminAioOrderTypeDef.SONAR_CASHIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.9：HOST
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.HOST);
            }

            // ５－４）引数.注文種別 = "102(バーチャル入金)"  の場合
            if (WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1002：入金注文、WEB3OrderRootDivDef.D:入金連携
                l_lisValue.add(OrderTypeEnum.CASH_IN);
                l_lisValue.add(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
            }

            // ５－５）引数.注文種別 = "103(ネット入金)"  の場合
            if (WEB3AdminAioOrderTypeDef.NET_CASHIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1002：入金注文
                l_lisValue.add(OrderTypeEnum.CASH_IN);
            }

            // ５－６）引数.注文種別 = "104(振替(株先証拠金から預り金))"  の場合
            if (WEB3AdminAioOrderTypeDef.CASHIN_TRANSFER_DEPOSIT_TO_MARGIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1008：振替注文(株先証拠金から預り金)
                l_lisValue.add(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            }

            // 引数.注文種別 = "105(為替保証金振替(為替保証金から預り金))"  の場合
            if (WEB3AdminAioOrderTypeDef.CASHIN_FX_GUARANTY_TO_DESPOSIT.equals(l_strOrderType))
            {
                // OrderTypeEnum.1012：為替保証金振替注文（為替保証金から預り金）
                l_lisValue.add(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            }

            // 引数.注文種別 = "106(その他振替(Xから預り金))"  の場合
            if (WEB3AdminAioOrderTypeDef.CASHIN_OTHER_X_TO_ACCOUNT_BALANCE.equals(l_strOrderType))
            {
                // OrderTypeEnum.1018：その他振替注文（Xから預り金）
                l_lisValue.add(OrderTypeEnum.FROM_OTHER_TRANSFER);
            }

            // ５－９）引数.注文種別 = "200(出金_全て)"  の場合
            if (WEB3AdminAioOrderTypeDef.CASHOUT_ALL.equals(l_strOrderType))
            {
                // OrderTypeEnum.1001：出金注文、
                // OrderTypeEnum.1007：振替注文(預り金から株先証拠金)、
                // OrderTypeEnum.1011：為替保証金振替注文（預り金から為替保証金）、
                // OrderTypeEnum.1017：その他振替注文（預り金からX）
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
                l_lisValue.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
                l_lisValue.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
                l_lisValue.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            }

            // ５－１０）引数.注文種別 = "201(出金)"  の場合
            if (WEB3AdminAioOrderTypeDef.CASHOUT.equals(l_strOrderType))
            {
                // OrderTypeEnum.1001：出金注文
                l_lisValue.add(OrderTypeEnum.CASH_OUT);
            }

            // ５－１１）引数.注文種別 = "202(振替(預り金から株先証拠金))" の場合
            if (WEB3AdminAioOrderTypeDef.CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN.equals(l_strOrderType))
            {
                // OrderTypeEnum.1007：振替注文(預り金から株先証拠金)
                l_lisValue.add(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            }

            // ５－１２）引数.注文種別 = "203(為替保証金振替(預り金から為替保証金))" の場合
            if (WEB3AdminAioOrderTypeDef.CASHOUT_FX_GUARANTY_TO_DESPOSIT.equals(l_strOrderType))
            {
                // OrderTypeEnum.1011：為替保証金振替注文（預り金から為替保証金）
                l_lisValue.add(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            }

            // ５－１３）引数.注文種別 = "204(その他振替(預り金からX))" の場合
            if (WEB3AdminAioOrderTypeDef.CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE.equals(l_strOrderType))
            {
                // OrderTypeEnum.1017：その他振替注文（預り金からX）
                l_lisValue.add(OrderTypeEnum.TO_OTHER_TRANSFER);
            }
        }

        // ・ステータス指定の場合（引数.ステータス != null）、ステータスをリストに追加する。
        if (l_strOrderStatus != null)
        {
            // ６－１）引数.ステータス = "0(全て)" の場合
            if (WEB3AdminAioCashStatusDef.ALL.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.1:受付済（新規注文）、OrderStatusEnum.2:発注中（新規注文）、
                // OrderStatusEnum.3:発注済（新規注文）、0:初期値、OrderStatusEnum.6:発注失敗（新規注文）
                l_lisValue.add(OrderStatusEnum.ACCEPTED);
                l_lisValue.add(OrderStatusEnum.ORDERING);
                l_lisValue.add(OrderStatusEnum.ORDERED);
                l_lisValue.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
                l_lisValue.add(OrderStatusEnum.NOT_ORDERED);
            }

            // ６－２）引数.ステータス = "1(完了)" の場合
            if (WEB3AdminAioCashStatusDef.COMPLETE.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.3:発注済（新規注文）、0:初期値
                l_lisValue.add(OrderStatusEnum.ORDERED);
                l_lisValue.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            }

            // ６－３）引数.ステータス = "2(未処理)" の場合
            if (WEB3AdminAioCashStatusDef.NOT_TRANSACTION.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.1:受付済（新規注文）、OrderStatusEnum.2:発注中（新規注文）、0:初期値
                l_lisValue.add(OrderStatusEnum.ACCEPTED);
                l_lisValue.add(OrderStatusEnum.ORDERING);
                l_lisValue.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            }

            // ６－４）引数.ステータス = "9(エラー)" の場合
            if (WEB3AdminAioCashStatusDef.ERROR.equals(l_strOrderStatus))
            {
                // OrderStatusEnum.6:発注失敗（新規注文）
                l_lisValue.add(OrderStatusEnum.NOT_ORDERED);
            }
        }

        // 戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。
        Object[] l_objValue = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_objValue);

        log.exiting(STR_METHOD_NAME);
        return l_objValue;
    }

    /**
     * (calc合計金額)<BR>
     * 注文種別毎のサマリ計算処理を行う。<BR>
     * <BR>
     * １）　@引数:注文単位Params.注文種別 == "1002(入金注文)"　@且つ　@<BR>
     * 　@　@　@引数:注文単位Params.注文経路区分 == "9(HOST)"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.SONAR入金合計金額に引数:注文単位Params.注文数量を加算<BR>
     * <BR>
     * ２）　@引数:注文単位Params.注文種別 == "1002(入金注文)"　@且つ<BR>
     * 　@　@　@引数:注文単位Params.注文経路区分 == "D(入金連携)"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.バーチャル入金合計金額に引数:注文単位Params.注文数量を加算<BR>
     * <BR>
     * ３）　@引数:注文単位Params.注文種別 == "1002(入金注文)"　@且つ<BR>
     * 　@　@　@引数:注文単位Params.comデビット決済取引番号 is not nullの場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.ネット入金合計金額に引数:注文単位Params.注文数量を加算<BR>
     * <BR>
     * ４）　@引数:注文単位Params.注文種別 =="1008(振替(株先証拠金から預り金))"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.振替(株先証拠金から預り金)合計金額に引数:注文単位Params<BR>
     *       .注文数量を加算<BR>
     * <BR>
     * ５）　@引数:注文単位Params.注文種別 == "1012(為替保証金振替(為替保証金から預り金))"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.為替保証金振替(為替保証金から預り金)合計金額に引数:注文<BR>
     *       単位Params.注文数量を加算<BR>
     * <BR>
     * ６）　@引数:注文単位Params.注文種別 == "1018（その他振替(Xから預り金))"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.その他振替(Xから預り金)合計金額に引数:注文単位Params.注<BR>
     *       文数量を加算<BR>
     * <BR>
     * ７）　@引数:注文単位Params.注文種別 == "1001(出金注文)"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.出金合計金額に引数:注文単位Params.注文数量を加算<BR>
     * <BR>
     * ８）　@引数:注文単位Params.注文種別 == "1007(振替(預り金から株先証拠金))"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.振替(預り金から株先証拠金)合計金額に引数:注文単位Params<BR>
     *       .注文数量を加算<BR>
     * <BR>
     * ９）　@引数:注文単位Params.注文種別 == "1011(為替保証金振替(預り金から為替保証金))"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.為替保証金振替(預り金から為替保証金)合計金額に引数:注文<BR>
     *       単位Params.注文数量を加算<BR>
     * <BR>
     * １０）引数:注文単位Params.注文種別 == "1017(その他振替(預り金からX))"の場合、<BR>
     * <BR>
     * 　@　@　@－レスポンスデータ.その他振替(預り金からX)合計金額に引数:注文単位Params.注<BR>
     *       文数量を加算<BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     * 注文単位Paramsオブジェクト<BR>
     * @@param l_response - (レスポンスデータ)<BR>
     * レスポンスデータ<BR>
     * @@return WEB3AdminAioCashTransferListResponse
     * @@roseuid 45B888CD019D
     */
    private WEB3AdminAioCashTransferListResponse calTotalAmount(
        AioOrderUnitParams l_orderUnitParams,
        WEB3AdminAioCashTransferListResponse l_response)
    {
        final String STR_METHOD_NAME = "calTotalAmount(AioOrderUnitParams, " +
            "WEB3AdminAioCashTransferListResponse)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数:注文単位Params.注文種別 == "1002(入金注文)"　@且つ
        //　@引数:注文単位Params.注文経路区分 == "9(HOST)"の場合
        //　@－レスポンスデータ.SONAR入金合計金額に引数:注文単位Params.注文数量を加算
        if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()) &&
            WEB3OrderRootDivDef.HOST.equals(l_orderUnitParams.getOrderRootDiv()))
        {
            double l_dblSonarCashinTotal = 0.0D;
            
            if(WEB3StringTypeUtility.isNotEmpty(l_response.sonarCashinTotal))
            {
                l_dblSonarCashinTotal = Double.parseDouble(l_response.sonarCashinTotal);
            }
            l_dblSonarCashinTotal = l_dblSonarCashinTotal + l_orderUnitParams.getQuantity();

            l_response.sonarCashinTotal = WEB3StringTypeUtility.formatNumber(l_dblSonarCashinTotal);
        }

        //２）　@引数:注文単位Params.注文種別 == "1002(入金注文)"　@且つ
        //引数:注文単位Params.注文経路区分 == "D(入金連携)"の場合
        //－レスポンスデータ.バーチャル入金合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()) &&
            WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_orderUnitParams.getOrderRootDiv()))
        {
            double l_dblVirtualCashinTotal = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.virtualCashinTotal))
            {
                l_dblVirtualCashinTotal = Double.parseDouble(l_response.virtualCashinTotal);
            }
            l_dblVirtualCashinTotal = l_dblVirtualCashinTotal + l_orderUnitParams.getQuantity();

            l_response.virtualCashinTotal = WEB3StringTypeUtility.formatNumber(l_dblVirtualCashinTotal);
        }

        //３）　@引数:注文単位Params.注文種別 == "1002(入金注文)"　@且つ
        //　@　@引数:注文単位Params..comデビット決済取引番号 is not nullの場合
        //　@　@－レスポンスデータ.ネット入金合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.CASH_IN.equals(l_orderUnitParams.getOrderType()) &&
            l_orderUnitParams.getComDebitNumber() != null)
        {
            double l_dblNetCashinTotal = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.netCashinTotal))
            {
                l_dblNetCashinTotal = Double.parseDouble(l_response.netCashinTotal);
            }
            l_dblNetCashinTotal = l_dblNetCashinTotal + l_orderUnitParams.getQuantity();

            l_response.netCashinTotal = WEB3StringTypeUtility.formatNumber(l_dblNetCashinTotal);
        }

        //４）　@引数:注文単位Params.注文種別 == "1008(振替(株先証拠金から預り金))"の場合
        // －レスポンスデータ.振替(株先証拠金から預り金)合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblTransferTotalMarginToDeposit = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.transferTotalMarginToDeposit))
            {
                l_dblTransferTotalMarginToDeposit =
                    Double.parseDouble(l_response.transferTotalMarginToDeposit);
            }

            l_dblTransferTotalMarginToDeposit = l_dblTransferTotalMarginToDeposit +
                l_orderUnitParams.getQuantity();

            l_response.transferTotalMarginToDeposit =
                WEB3StringTypeUtility.formatNumber(l_dblTransferTotalMarginToDeposit);
        }

        //５）　@引数:注文単位Params.注文種別 == "1012(為替保証金振替(為替保証金から預り金))"の場合、
        // －レスポンスデータ.為替保証金振替(為替保証金から預り金)合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblFxTotalGuarantyToDeposit = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.fxTotalGuarantyToDeposit))
            {
                l_dblFxTotalGuarantyToDeposit =
                    Double.parseDouble(l_response.fxTotalGuarantyToDeposit);
            }

            l_dblFxTotalGuarantyToDeposit = l_dblFxTotalGuarantyToDeposit +
                l_orderUnitParams.getQuantity();

            l_response.fxTotalGuarantyToDeposit =
                WEB3StringTypeUtility.formatNumber(l_dblFxTotalGuarantyToDeposit);
        }

        //６）　@引数:注文単位Params.注文種別 == "1018（その他振替(Xから預り金))"の場合、
        //－レスポンスデータ.その他振替(Xから預り金)合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblOtherTotalXToAccountBalance = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.otherTotalXToAccountBalance))
            {
                l_dblOtherTotalXToAccountBalance =
                    Double.parseDouble(l_response.otherTotalXToAccountBalance);
            }

            l_dblOtherTotalXToAccountBalance = l_dblOtherTotalXToAccountBalance +
                l_orderUnitParams.getQuantity();

            l_response.otherTotalXToAccountBalance =
                WEB3StringTypeUtility.formatNumber(l_dblOtherTotalXToAccountBalance);
        }

        //７）　@引数:注文単位Params.注文種別 == "1001(出金注文)"の場合
        //－レスポンスデータ.出金合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.CASH_OUT.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblCashoutTotal = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.cashoutTotal))
            {
                l_dblCashoutTotal =
                    Double.parseDouble(l_response.cashoutTotal);
            }

            l_dblCashoutTotal = l_dblCashoutTotal +
                l_orderUnitParams.getQuantity();

            l_response.cashoutTotal = WEB3StringTypeUtility.formatNumber(l_dblCashoutTotal);
        }

        //８）　@引数:注文単位Params.注文種別 == "1007(振替(預り金から株先証拠金))"の場合
        //－レスポンスデータ.振替(預り金から株先証拠金)合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblTransferTotalDepositToMargin = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.transferTotalDepositToMargin))
            {
                l_dblTransferTotalDepositToMargin =
                    Double.parseDouble(l_response.transferTotalDepositToMargin);
            }

            l_dblTransferTotalDepositToMargin = l_dblTransferTotalDepositToMargin +
                l_orderUnitParams.getQuantity();

            l_response.transferTotalDepositToMargin =
                WEB3StringTypeUtility.formatNumber(l_dblTransferTotalDepositToMargin);
        }

        //９）　@引数:注文単位Params.注文種別 == "1011(為替保証金振替(預り金から為替保証金))"の場合
        //－レスポンスデータ.為替保証金振替(預り金から為替保証金)合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblFxTotalDepositToGuaranty = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.fxTotalDepositToGuaranty))
            {
                l_dblFxTotalDepositToGuaranty =
                    Double.parseDouble(l_response.fxTotalDepositToGuaranty);
            }

            l_dblFxTotalDepositToGuaranty = l_dblFxTotalDepositToGuaranty +
                l_orderUnitParams.getQuantity();

            l_response.fxTotalDepositToGuaranty =
                WEB3StringTypeUtility.formatNumber(l_dblFxTotalDepositToGuaranty);
        }

        //１０）引数:注文単位Params.注文種別 == "1017(その他振替(預り金からX))"の場合
        //－レスポンスデータ.その他振替(預り金からX)合計金額に引数:注文単位Params.注文数量を加算
        else if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_orderUnitParams.getOrderType()))
        {
            double l_dblOtherTotalAccountBalanceToX = 0.0D;
            if (WEB3StringTypeUtility.isNotEmpty(l_response.otherTotalAccountBalanceToX))
            {
                l_dblOtherTotalAccountBalanceToX =
                    Double.parseDouble(l_response.otherTotalAccountBalanceToX);
            }

            l_dblOtherTotalAccountBalanceToX = l_dblOtherTotalAccountBalanceToX +
                l_orderUnitParams.getQuantity();

            l_response.otherTotalAccountBalanceToX =
                WEB3StringTypeUtility.formatNumber(l_dblOtherTotalAccountBalanceToX);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get振込先金融機@関レコード)<BR>
     * 検索条件から取得した振込先金融機@関レコードをList型で返却する。<BR>
     * <BR>
     * １）　@doFindAllQuery()を使用して振込先金融機@関テーブルを以下の条件で検索。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@振込先金融機@関テーブル.証券会社コード = <BR>
     *   顧客.getInstitution().getInstitutionCode() <BR>
     * 　@振込先金融機@関テーブル.部店コード = 顧客.getBranch().getBranchCode() <BR>
     * 　@振込先金融機@関テーブル.顧客コード = 顧客.getAccountCode() <BR>
     * 　@振込先金融機@関テーブル.指定区分 = 'A' <BR>
     * <BR>
     * ２）検索結果を返却<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 45B9B1C80313
     */
    protected List getTransferedFinInstitutionRecord(MainAccount l_mainAccount)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getTransferedFinInstitutionRecord(MainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        List l_lisFinInstitutio = null;

        String l_strCondition = "institution_code=? and branch_code=? and account_code=? and designate_div=?";

        Object[] l_objContainer = new Object[]{l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_mainAccount.getAccountCode(),
            WEB3TransferRangeDef.SALE_TURNOVER };

        try
        {
            l_lisFinInstitutio = Processors.getDefaultProcessor().doFindAllQuery(
                TransferedFinInstitutionRow.TYPE,
                l_strCondition,
                null,
                l_objContainer);
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

        log.exiting(STR_METHOD_NAME);
        return l_lisFinInstitutio;
    }

    /**
     * (get表示明細)<BR>
     * 入出金一覧明細の配列から要求ページ内に<BR>
     * 表示される明細の配列を取得して返却する。<BR>
     * <BR>
     * １）空のArrayListインスタンスを生成する。<BR>
     * <BR>
     * ２）入出金一覧明細[引数.fromIndex]から<BR>
     * 　@　@入出金一覧明細［引数.toIndex]の要素を、１）のArrayListに追加する。<BR>
     * <BR>
     * ３）ArrayListから配列を取得する。<BR>
     * <BR>
     * ４）生成した配列を返却する。<BR>
     * <BR>
     * @@param l_details - (明細)<BR>
     * 出金申込問合せ明細の配列<BR>
     * <BR>
     * @@param l_intFromIndex - 表示対象の開始位置のインデックス番号<BR>
     * <BR>
     * @@param l_intToIndex - 表示対象の終了位置のインデックス番号<BR>
     * <BR>
     * @@return WEB3AioAdminCashTransferListUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 45BD986D0284
     */
    protected WEB3AioAdminCashTransferListUnit[] getIndicationDetails(
        WEB3AioAdminCashTransferListUnit[] l_details,
        int l_intFromIndex,
        int l_intToIndex) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIndicationDetails(" +
            "WEB3AioAdminCashTransferListUnit[], l_intFromIndex, l_intToIndex)";
        log.entering(STR_METHOD_NAME);

        if (l_details == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_details.length == 0 || (l_intFromIndex > l_intToIndex) ||
            l_intToIndex >= l_details.length)
        {
            log.debug(" パラメータError ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //空のArrayListインスタンスを生成する。
        List l_lisArrayList = new ArrayList();

        //  入出金一覧明細[引数.fromIndex]から
        //　@入出金一覧明細［引数.toIndex]の要素を、１）のArrayListに追加する。
        for (int i = l_intFromIndex; i <= l_intToIndex; i++)
        {
            l_lisArrayList.add(l_details[i]);
        }

        //ArrayListから配列を取得する。
        WEB3AioAdminCashTransferListUnit[] l_l_detailArrays=
            new WEB3AioAdminCashTransferListUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_l_detailArrays);

        log.exiting(STR_METHOD_NAME);
        //生成した配列を返却する。
        return l_l_detailArrays;
    }

    /**
     * (get入出金区分)<BR>
     * 引数.注文種別から入出金を判別し、返却する。<BR>
     * <BR>
     * １）　@引数.注文種別が以下の場合、"0(入金)"を返却する。<BR>
     * 　@　@　@・1002(入金注文)<BR>
     * 　@　@　@・1008(振替注文(株先証拠金から預り金))<BR>
     * 　@　@　@・1012(為替保証金振替注文(為替保証金から預り金))<BR>
     * 　@　@　@・1018(その他振替注文(Xから預り金)<BR>
     * <BR>
     * ２）　@引数.注文種別が以下の場合、"1(出金)"を返却する。<BR>
     * 　@　@　@・1001(出金注文)<BR>
     * 　@　@　@・1007(振替注文(預り金から株先証拠金))<BR>
     * 　@　@　@・1011(為替保証金振替注文(預り金から為替保証金))<BR>
     * 　@　@　@・1017(その他振替注文(預り金からX))<BR>
     * @@param l_strOrderType - (注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * @@return String
     * @@roseuid 45BED17C02B2
     */
    private String getAioDev(String l_strOrderType)
    {
        String STR_METHOD_NAME = " getAioDev(String)";
        log.entering(STR_METHOD_NAME);
        
        if ((OrderTypeEnum.CASH_IN.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.FROM_OTHER_TRANSFER.intValue() + "").equals(l_strOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioDivDef.CASH_IN;
        }
        else if ((OrderTypeEnum.CASH_OUT.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue() + "").equals(l_strOrderType) ||
            (OrderTypeEnum.TO_OTHER_TRANSFER.intValue() + "").equals(l_strOrderType))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioDivDef.CASH_OUT;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
