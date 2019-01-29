head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ｻｰﾋﾞｽImpl(WEB3AdminAccInfoAccEstablishSearchServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 何文敏 (中訊) 新規作成
                 : 2006/11/20 趙林鵬 (中訊) モデル No.143
                 : 2006/11/24 周捷 (中訊) モデル No.147、149
                 : 2006/12/06 周捷 (中訊) モデル No.151
                 : 2006/10/09 何文敏 (中訊) モデル No.152
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.WEB3AdminAccInfoAccEstablishDownLoadCSV;
import webbroker3.accountinfo.define.WEB3AccInfoDataContentDef;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoLoginLockDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoAccEstablishSearchInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchDLResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccEstablishSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccEstablishSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3LoginDisabledFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.ExclusiveUseAccountRow;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索ｻｰﾋﾞｽImpl)<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishSearchServiceImpl
    extends WEB3AccInfoClientRequestService
    implements WEB3AdminAccInfoAccEstablishSearchService

{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoAccEstablishSearchServiceImpl.class);

    /**
     * 新規口座開設・口座移管・ログインロック顧客検索処理を実施する。<BR>
     * <BR>
     * １）リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     *    ■ 引数のリクエストデータが、<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索<BR>
     * 入力ﾘｸｴｽﾄの場合<BR>
     *    　@・ get入力画面()をコールする。<BR>
     * <BR>
     *    ■ 引数のリクエストデータが、<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索<BR>
     * 一覧ﾘｸｴｽﾄの場合<BR>
     *    　@・ create一覧()をコールする。<BR>
     * <BR>
     *    ■ 引数のリクエストデータが、<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索<BR>
     * ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合<BR>
     *    　@・ getダウンロードファ@イル()をコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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

        //１）リクエストデータの型により、以下の通りメソッドをコールする。
        // 引数のリクエストデータが、管理者お客様情報新規口座開設・
        //口座移管・ログインロック顧客検索入力ﾘｸｴｽﾄの場合
        //      　@・ get入力画面()をコールする。
        if (l_request instanceof WEB3AdminAccInfoAccEstablishSearchInputRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminAccInfoAccEstablishSearchInputRequest)l_request);
        }

        //引数のリクエストデータが、管理者お客様情報新規口座開設・口座移管・
        //ログインロック顧客検索一覧ﾘｸｴｽﾄの場合
        //      　@・ create一覧()をコールする。
        else if (l_request instanceof WEB3AdminAccInfoAccEstablishSearchListRequest)
        {
            l_response =
                this.getListScreen(
                    (WEB3AdminAccInfoAccEstablishSearchListRequest)l_request);
        }

        //引数のリクエストデータが、管理者お客様情報新規口座開設・口座移管・
        //ログインロック顧客検索ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄの場合
        //      　@・ getダウンロードファ@イル()をコールする。
        else if (l_request instanceof WEB3AdminAccInfoAccEstablishSearchDLRequest)
        {
            l_response =
                this.getDownloadFile(
                    (WEB3AdminAccInfoAccEstablishSearchDLRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 新規口座開設・口座移管・ログインロック顧客検索入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（新規口座開設・口座移管・ログインロック顧客検索）get入力画面」<BR>
     * 参照<BR>
     * @@param l_request -（リクエストデータ)<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索入力リクエスト
     * @@return WEB3AdminAccInfoAccEstablishSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected  WEB3AdminAccInfoAccEstablishSearchInputResponse getInputScreen(
        WEB3AdminAccInfoAccEstablishSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoAccEstablishSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2) validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.3) createResponse()
        WEB3AdminAccInfoAccEstablishSearchInputResponse l_response =
            (WEB3AdminAccInfoAccEstablishSearchInputResponse)l_request.createResponse();

        //プロパティセット

        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        
        //口座開設日（自）:GtlUtils.getSystemTimestamp()の前営業日
        l_response.accountOpenDateFrom = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //口座開設日（至）:GtlUtils.getSystemTimestamp()の前日
        String l_strDate = WEB3DateUtility.formatDate(WEB3DateUtility.addDay(l_tsCurrentDate, -1), "yyyyMMdd");
        l_response.accountOpenDateTo = WEB3DateUtility.getDate(l_strDate , "yyyyMMdd");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面)<BR>
     * 新規口座開設・口座移管・ログインロック顧客検索一覧画面表示処理を行う。<BR>
     * <BR>
     *  シーケンス図 <BR>
     *  「お客様情報（新規口座開設・口座移管・ログインロック顧客検索）get一覧画面」<BR>
     *  参照<BR>
     *  ========================================================<BR>
     *  1.10.10（*5）『リスト』の要素数がMAX処理件数を超えた場合、<BR>
     *  例外（BUSINESS_ERROR_02418）をthrowする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02418<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *  1.11（*6）リストの要素数が0件の場合、<BR>
     *  例外（BUSINESS_ERROR_01037）をthrowする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧リクエスト
     * @@return WEB3AdminAccInfoAccEstablishSearchListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected  WEB3AdminAccInfoAccEstablishSearchListResponse getListScreen(
        WEB3AdminAccInfoAccEstablishSearchListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminAccInfoAccEstablishSearchListRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1  validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.5 create検索条件文字列(String, String[], String, String, String, Date, Date, String)
        String l_strQueryString =
            this.createQueryString(
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.6 create検索条件データコンテナ(String, String[], String, String, String, Date, Date, String)
        String[] l_strQueryContainers =
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.7 createソート条件(お客様情報ソートキー[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.8 get顧客(String, String[], String)
        //[引数]
        // 検索条件文字列：　@create検索条件文字列()の戻り値
        // 検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
        // ソート条件：　@createソート条件()の戻り値
        List l_lisMainAccount =
            WEB3GentradeMainAccount.getMainAccount(
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);

        //1.9 ArrayList( )
        List l_lisAccInfoOpenSearchInfo = new ArrayList();

        //1.10  (*1)get顧客()の戻り値の内、表示対象行(fromIndex ～ toIndex)の間Loop処理
        int l_intpageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intpageSize = Integer.parseInt(l_request.pageSize);

        int l_intMainAccount = 0;
        if (l_lisMainAccount != null && l_lisMainAccount.size() != 0)
        {
            l_intMainAccount = l_lisMainAccount.size();
        }
        for (int i = 0; i < l_intMainAccount; i++)
        {
            //1.10.1  isデータ内容(String, Date, String, String)
            WEB3GentradeMainAccount l_row = (WEB3GentradeMainAccount)l_lisMainAccount.get(i);           
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_row.getDataSourceObject();
            
            boolean l_blnDataCountent =
                this.isDataContent(
                    l_request.dataContentDiv,
                    l_mainAccountRow.getAccountOpenDate(),
                    l_strInstitutionCode,
                    l_mainAccountRow.getBranchCode(),
                    l_mainAccountRow.getAccountCode());

            //1.10.2 (*2) isデータ内容（）の戻り値 = falseの場合、当該要素の処理を中断（continue）
            if (!l_blnDataCountent)
            {
                continue;
            }

            //1.10.3 getログインレコード(long)
            long l_lngAccountId = l_mainAccountRow.getAccountId();
            List l_lisLoginRecord = this.getLoginRecord(l_lngAccountId);

            // (*3) getログインレコード()の戻り値の要素数が0件の場合、当該要素の処理を中断（continue）
            if (l_lisLoginRecord.isEmpty())
            {
                log.debug("getログインレコード()の戻り値の要素数が0件の場合、当該要素の処理を中断");
                continue;
            }
            log.debug("getログインレコード()の戻り値の要素数!=0件の場合");
            LoginRow l_logintRow = (LoginRow)l_lisLoginRecord.get(0);

            //1.10.4 isログインロック(String, long, long)
            //[引数]
            // ログインロック区分：　@リクエストデータ.ログインロック区分
            // ログイン有効性：　@getログインレコード().get(0).ログイン有効性
            // ログインエラー回数：　@getログインレコード().get(0).ログインエラー回数

            boolean l_blnIsLoginLock =
                this.isLoginLock(l_request.loginLockDiv,
                    l_logintRow.getDisabled(),
                    l_logintRow.getFailureCount());

            //1.10.5 (*3) isログインロック（）の戻り値 = falseの場合、当該要素の処理を中断（continue）
            if (!l_blnIsLoginLock)
            {
                continue;
            }

            //1.10.6 get専用振込先口座レコード(long)
            List l_lisExclusiveUseAccountRecord =
                this.getExclusiveUseAccountRecord(l_lngAccountId);

            int l_intExclusiveUseAccountRecord = l_lisExclusiveUseAccountRecord.size();

            //1.10.7 新規口座開設・口座移管・ログインロック顧客情報（）
            WEB3AccInfoAccEstablishSearchInfo l_accInfoOpenSearchInfo =
                new WEB3AccInfoAccEstablishSearchInfo();

            //1.10.8 (*4)プロパティセット
            l_accInfoOpenSearchInfo.branchCode = l_mainAccountRow.getBranchCode();
            l_accInfoOpenSearchInfo.traderCode = l_mainAccountRow.getSonarTraderCode();
            l_accInfoOpenSearchInfo.accountCode = l_mainAccountRow.getAccountCode().substring(0, 6);

            if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
            {
                l_accInfoOpenSearchInfo.accountTypeCode =
                    MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "";
            }
            else
            {
                l_accInfoOpenSearchInfo.accountTypeCode =
                    MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "";
            }

            l_accInfoOpenSearchInfo.accountName = l_mainAccountRow.getFamilyName();

            l_accInfoOpenSearchInfo.accountNameKana = l_mainAccountRow.getFamilyNameAlt1();

            l_accInfoOpenSearchInfo.accountOpenDate = l_mainAccountRow.getAccountOpenDate();

            if (l_intExclusiveUseAccountRecord == 0)
            {
                l_accInfoOpenSearchInfo.payFinancialInstitution = null;
                l_accInfoOpenSearchInfo.financialBranchName = null;
                l_accInfoOpenSearchInfo.item = null;
                l_accInfoOpenSearchInfo.financialInstitutionNumber = null;
                l_accInfoOpenSearchInfo.financialBranchCode = null;
                l_accInfoOpenSearchInfo.financialAccountCode = null;
            }
            else
            {
                ExclusiveUseAccountRow l_exclusiveUseAccountRow =
                    (ExclusiveUseAccountRow)l_lisExclusiveUseAccountRecord.get(0);

                l_accInfoOpenSearchInfo.payFinancialInstitution =
                    l_exclusiveUseAccountRow.getFinInstitutionName();

                l_accInfoOpenSearchInfo.financialBranchName =
                    l_exclusiveUseAccountRow.getFinBranchName();

                l_accInfoOpenSearchInfo.item =
                    l_exclusiveUseAccountRow.getFinAccountTypeName();

                l_accInfoOpenSearchInfo.financialInstitutionNumber =
                    l_exclusiveUseAccountRow.getFinInstitutionCode();

                l_accInfoOpenSearchInfo.financialBranchCode =
                    l_exclusiveUseAccountRow.getFinBranchCode();

                l_accInfoOpenSearchInfo.financialAccountCode =
                    l_exclusiveUseAccountRow.getFinAccountNo();
            }

            l_accInfoOpenSearchInfo.zipCode = l_mainAccountRow.getZipCode();
            l_accInfoOpenSearchInfo.address1 = l_mainAccountRow.getAddressLine1();
            l_accInfoOpenSearchInfo.address2 = l_mainAccountRow.getAddressLine2();
            l_accInfoOpenSearchInfo.address3 = l_mainAccountRow.getAddressLine3();

            String l_strDisabled = l_logintRow.getDisabled() + "";
            if (WEB3LoginDisabledFlagDef.ACCINFO_DISABLED.equals(l_strDisabled) && l_logintRow.getFailureCount() >= 1)
            {
                l_accInfoOpenSearchInfo.loginLockFlag = true;
            }
            else
            {
                l_accInfoOpenSearchInfo.loginLockFlag = false;
            }

            l_accInfoOpenSearchInfo.loginErrorCount =
                l_logintRow.getFailureCount() + "";

            //1.10.9 add(Object)
            l_lisAccInfoOpenSearchInfo.add(l_accInfoOpenSearchInfo);

            //1.10.10 （*5）『リスト』の要素数がMAX処理件数を超えた場合、
            //例外（BUSINESS_ERROR_02418）をthrowする。
            int l_intMaxCount = Integer.parseInt(l_request.maxCount);

            // 『リスト』.size > リクエストデータ.MAX処理件数
            // 下記條件の場合、上記のMAX処理件数チエツクは行わない。
            // リクエストデータ. ログインロック区分 = 1：　@ログインロック客 &&
            // リクエストデータ. データ内容区分 = 00：　@データ内容未選択
            if (!(WEB3AccInfoLoginLockDivDef.LOGIN_LOCK_ACCOUNT.equals(l_request.loginLockDiv)
                && WEB3AccInfoDataContentDef.DATA_CONTENT_NOT_SELECT.equals(l_request.dataContentDiv)))
            {
                if (l_lisAccInfoOpenSearchInfo.size() > l_intMaxCount)
                {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "レコード件数が処理限界値を超えています。");
                }
            }
        }
        

        //1.11  （*6）リストの要素数が0件の場合、例外（BUSINESS_ERROR_01037）をthrowする。
        if (l_lisAccInfoOpenSearchInfo.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //1.12 toArray( )
        WEB3AccInfoAccEstablishSearchInfo[] l_accOpenLockSearchList =
            new WEB3AccInfoAccEstablishSearchInfo[l_lisAccInfoOpenSearchInfo.size()];
        l_lisAccInfoOpenSearchInfo.toArray(l_accOpenLockSearchList);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_accOpenLockSearchList, l_intpageIndex, l_intpageSize);

        //1.13 createResponse( )
        WEB3AdminAccInfoAccEstablishSearchListResponse l_response =
            (WEB3AdminAccInfoAccEstablishSearchListResponse)l_request.createResponse();

        //1.14 （*7）プロパティセット
        l_response.branchCode = l_request.branchCode;
        l_response.traderCode = l_request.traderCode;
        l_response.accountCode = l_request.accountCode;
        l_response.accountNameKana = l_request.accountNameKana;
        l_response.accountTypeCode = l_request.accountTypeCode;
        l_response.dataContentDiv = l_request.dataContentDiv;
        l_response.accountOpenDateFrom = l_request.accountOpenDateFrom;
        l_response.accountOpenDateTo = l_request.accountOpenDateTo;
        l_response.loginLockDiv = l_request.loginLockDiv;

        WEB3AccInfoAccEstablishSearchInfo[] l_lstEstablishSearchInfo = 
            new WEB3AccInfoAccEstablishSearchInfo[l_pageIndexInfo.getListReturned().size()];
        l_pageIndexInfo.getListReturned().toArray(l_lstEstablishSearchInfo);
        l_response.accOpenLockSearchList = l_lstEstablishSearchInfo;
        
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getダウンロードファ@イル)<BR>
     * 新規口座開設・口座移管・ログインロック顧客検索ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（新規口座開設・口座移管・ログインロック顧客検索）getダウンロード」<BR>
     * 参照<BR>
     *  ========================================================<BR>
     *  1.10.27（*4）明細行数がMAX処理件数を超えた場合、<BR>
     *  例外（BUSINESS_ERROR_01871）をthrowする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01871<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客ダウンロードリクエスト
     * @@return WEB3AdminAccInfoAccEstablishSearchDLResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected  WEB3AdminAccInfoAccEstablishSearchDLResponse getDownloadFile(
        WEB3AdminAccInfoAccEstablishSearchDLRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getDownloadFile(WEB3AdminAccInfoAccEstablishSearchDLRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1  validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3) validate権限(String, boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_BASE_INFO, false);

        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //1.5 create検索条件文字列(String, String[], String, String, String, Date, Date, String)
        String l_strQueryString =
            this.createQueryString(
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.6 create検索条件データコンテナ(String, String[], String, String, String, Date, Date , String)
        String[] l_strQueryContainers =
            this.createQueryContainer(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.traderCode,
                l_request.accountCode,
                l_request.accountTypeCode,
                l_request.accountOpenDateFrom,
                l_request.accountOpenDateTo,
                l_request.accountNameKana);

        //1.7 createソート条件(お客様情報ソートキー[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.8 get顧客(String, String[], String)
        //[引数]
        // 検索条件文字列：　@create検索条件文字列()の戻り値
        // 検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
        // ソート条件：　@createソート条件()の戻り値
        List l_lisMainAccount =
            WEB3GentradeMainAccount.getMainAccount(
                l_strQueryString,
                l_strQueryContainers,
                l_strSortCond);

        //1.9 新規口座開設・口座移管・ログインロック顧客ダウンロードCSV( )
        WEB3AdminAccInfoAccEstablishDownLoadCSV l_accInfoAccEstablishDownLoadCSV =
            new WEB3AdminAccInfoAccEstablishDownLoadCSV();

        //1.10  (*1) 取得したダウンロードデータ(get顧客()の戻り値)の各要素毎のLOOP
        int l_intMainAccount = 0;
        if (l_lisMainAccount != null && l_lisMainAccount.size() != 0)
        {
            l_intMainAccount = l_lisMainAccount.size();
        }

        for (int i = 0; i < l_intMainAccount; i++)
        {
            //1.10.1 isデータ内容(String, Date, String, String)
            //[引数]
            // データ内容番号：　@リクエストデータ.データ内容番号
            // 口座登録日：　@get顧客()[index].口座登録日
        	// 証券会社コード：　@get証券会社()の戻り値 
            // 部店コード：　@get顧客()[index].部店コード
            // 顧客コード：　@get顧客()[index].口座コード
            WEB3GentradeMainAccount l_row = (WEB3GentradeMainAccount)l_lisMainAccount.get(i);           
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_row.getDataSourceObject();

            boolean l_blnDataCountent =
                this.isDataContent(
                    l_request.dataContentDiv,
                    l_mainAccountRow.getAccountOpenDate(),
                    l_strInstitutionCode,
                    l_mainAccountRow.getBranchCode(),
                    l_mainAccountRow.getAccountCode());

            //1.10.2 (*2) isデータ内容（）の戻り値 = falseの場合、当該要素の処理を中断（continue）
            if (!l_blnDataCountent)
            {
                continue;
            }

            //1.10.3 getログインレコード(long)
            long l_lngAccountId = l_mainAccountRow.getAccountId();
            List l_lisLoginRecord = this.getLoginRecord(l_lngAccountId);

            // getログインレコード()の戻り値の要素数が0件の場合、当該要素の処理を中断（continue）
            if (l_lisLoginRecord.isEmpty())
            {
                log.debug("getログインレコード()の戻り値の要素数が0件の場合、当該要素の処理を中断");
                continue;
            }
            log.debug("getログインレコード()の戻り値の要素数 != 0件");
            LoginRow l_logintRow = null;
            if (l_lisLoginRecord != null)
            {
                l_logintRow = (LoginRow)l_lisLoginRecord.get(0);
            }

            //1.10.4 isログインロック(String, long, long)
            //[引数]
            // ログインロック区分：　@リクエストデータ.ログインロック区分
            // ログイン有効性：　@getログインレコード().get(0).ログイン有効性
            // ログインエラー回数：　@getログインレコード().get(0).ログインエラー回数
            boolean l_blnIsLoginLock =
                this.isLoginLock(l_request.loginLockDiv,
                    l_logintRow.getDisabled(),
                    l_logintRow.getFailureCount());

            //1.10.5 (*3) isログインロック（）の戻り値 = falseの場合、当該要素の処理を中断（continue）
            if (!l_blnIsLoginLock)
            {
                continue;
            }

            //1.10.6 get専用振込先口座レコード(long)
            List l_lisExclusiveUseAccountRecord =
                this.getExclusiveUseAccountRecord(l_lngAccountId);
            
            //get部店レコード(long)
            List l_lisBranchRecords = this.getBranchRecord(l_mainAccountRow.getBranchId());

            int l_intExclusiveUseAccountRecord = l_lisExclusiveUseAccountRecord.size();

            //1.10.7 add明細行( )
            //[引数]
            // 行番号：　@add明細行()の戻り値
            // データ内容区分：　@リクエストデータ.データ内容番号
            int l_intAddRow = l_accInfoAccEstablishDownLoadCSV.addRow();

            //1.10.8 setデータ内容番号(int, String)
            l_accInfoAccEstablishDownLoadCSV.setDataContentDiv(
                l_intAddRow, l_request.dataContentDiv);

            //1.10.9 set会社コード(int, String)
            l_accInfoAccEstablishDownLoadCSV.setInstitutionCode(
                l_intAddRow, l_strInstitutionCode);

            //1.10.10 set部店コード(int, String)
            l_accInfoAccEstablishDownLoadCSV.setBranchCode(
                l_intAddRow, l_mainAccountRow.getBranchCode());

            //1.10.11 set扱者コード(int, String)
            l_accInfoAccEstablishDownLoadCSV.setTraderCode(
                l_intAddRow, l_mainAccountRow.getSonarTraderCode());

            //1.10.12 set顧客コード(int, String)
            l_accInfoAccEstablishDownLoadCSV.setAccountCode(
                l_intAddRow, l_mainAccountRow.getAccountCode());

            //1.10.13 set口座種別(int, String)
            String l_strAccountType = l_mainAccountRow.getAccountType().intValue() + "";
            l_accInfoAccEstablishDownLoadCSV.setAccountTypeCode(
                l_intAddRow, l_strAccountType);

            //1.10.14 set顧客名（漢字）(int, String)
            l_accInfoAccEstablishDownLoadCSV.setAccountName(
                l_intAddRow, l_mainAccountRow.getFamilyName());

            //1.10.15 set顧客名（カナ）(int, String)
            l_accInfoAccEstablishDownLoadCSV.setAccountNameKana(
                l_intAddRow, l_mainAccountRow.getFamilyNameAlt1());

            //1.10.16 set口座開設日(int, String)
            String l_strAccountOpenDate =
                WEB3DateUtility.formatDate(
                    l_mainAccountRow.getAccountOpenDate(), "yyyy/MM/dd");
            l_accInfoAccEstablishDownLoadCSV.setAccountOpenDate(
                l_intAddRow, l_strAccountOpenDate);

            if (l_intExclusiveUseAccountRecord == 0)
            {
                //1.10.17 set入金先銀行(int, String)
                l_accInfoAccEstablishDownLoadCSV.setPayFinancialInstitution(
                    l_intAddRow, null);

                //1.10.18 set銀行支店名(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchName(
                    l_intAddRow, null);

                //1.10.19 set科目(int, String)
                l_accInfoAccEstablishDownLoadCSV.setItem(
                    l_intAddRow, null);

                //1.10.20 set銀行番号(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialInstitutionNumber(
                    l_intAddRow, null);

                //1.10.21 set銀行支店番号(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchCode(
                    l_intAddRow, null);

                //1.10.22 set銀行口座番号(int, String)
                l_accInfoAccEstablishDownLoadCSV.setFinancialAccountCode(
                    l_intAddRow, null);
            }
            else
            {
                ExclusiveUseAccountRow l_exclusiveUseAccountRow =
                    (ExclusiveUseAccountRow)l_lisExclusiveUseAccountRecord.get(0);

                l_accInfoAccEstablishDownLoadCSV.setPayFinancialInstitution(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinInstitutionName());

                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchName(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinBranchName());

                l_accInfoAccEstablishDownLoadCSV.setItem(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinAccountTypeName());

                l_accInfoAccEstablishDownLoadCSV.setFinancialInstitutionNumber(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinInstitutionCode());

                l_accInfoAccEstablishDownLoadCSV.setFinancialBranchCode(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinBranchCode());

                l_accInfoAccEstablishDownLoadCSV.setFinancialAccountCode(
                    l_intAddRow, l_exclusiveUseAccountRow.getFinAccountNo());
            }

            //1.10.23 set顧客住所（郵便番号）(int, String)
            l_accInfoAccEstablishDownLoadCSV.setZipCode(
                l_intAddRow, l_mainAccountRow.getZipCode());

            //1.10.24 setset顧客住所（住所1）((int, String)
            l_accInfoAccEstablishDownLoadCSV.setAddress1(
                l_intAddRow, l_mainAccountRow.getAddressLine1());

            //1.10.25 set顧客住所（住所2）((int, String)
            l_accInfoAccEstablishDownLoadCSV.setAddress2(
                l_intAddRow, l_mainAccountRow.getAddressLine2());

            //1.10.26 set顧客住所（住所3）((int, String)
            l_accInfoAccEstablishDownLoadCSV.setAddress3(
                l_intAddRow, l_mainAccountRow.getAddressLine3());
            
            //set電話番号(int, String)
            //[引数]  
            // 行番号：　@add明細行()の戻り値  
            // 電話番号：　@get顧客()[index].電話番号
            l_accInfoAccEstablishDownLoadCSV.setTelephone(
                l_intAddRow, l_mainAccountRow.getTelephone());
            
            BranchRow l_branchRow = (BranchRow)l_lisBranchRecords.get(0);
            //set部店名(int, String)
            //[引数]  
            // 行番号：　@add明細行()の戻り値  
            // 部店名：　@get部店レコード().get(0).get部店名(branch_name)
            l_accInfoAccEstablishDownLoadCSV.setBranchName(
                l_intAddRow, l_branchRow.getBranchName());

            //1.10.27 （*4）明細行数がMAX処理件数を超えた場合、例外（BUSINESS_ERROR_01871）をthrowする。
            int l_intMaxCount = Integer.parseInt(l_request.maxCount);
            int l_rowCount = l_accInfoAccEstablishDownLoadCSV.getRowCount();
            
            if (l_rowCount > l_intMaxCount)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01871,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "CSVファ@イル行は最大処理件数を越えています。");
            }
        }

        //1.11 getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_accInfoAccEstablishDownLoadCSV.getCsvFileLines();

        //1.12 createResponse( )
        WEB3AdminAccInfoAccEstablishSearchDLResponse l_response =
            (WEB3AdminAccInfoAccEstablishSearchDLResponse)l_request.createResponse();

        //1.13（*5）プロパティセット
        l_response.downloadFile = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        l_response.dataContentNumber = l_request.dataContentDiv;

       log.exiting(STR_METHOD_NAME);
       return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。<BR>
     * <BR>
     * １）　@戻り値生成<BR>
     *    ・ 戻り値の検索条件文字列インスタンス（：String）を生成<BR>
     * <BR>
     * ２）　@「証券会社コード」条件追加<BR>
     *    ・ 証券会社コード条件を追加する。<BR>
     * <BR>
     *    　@　@" institution_code =  ? "<BR>
     * <BR>
     * ３）　@「部店コード」条件追加<BR>
     *    ・ 部店条件を追加する。部店コード[]の要素数分"?"を追加する。<BR>
     * <BR>
     *    　@　@" and branch_code in (?, ?,,,) " <BR>
     * <BR>
     * ４）　@「扱者コード」条件追加<BR>
     *    ・ 引数.扱者コード != nullの場合、扱者コードを追加する。<BR>
     * <BR>
     *    　@　@" and sonar_trader_code = ? " <BR>
     * <BR>
     * ５）　@「顧客コード」条件追加<BR>
     *    ・ 引数.顧客コード != nullの場合、顧客コード条件を追加する。<BR>
     * <BR>
     *    　@　@" and account_code like ? " <BR>
     * <BR>
     * ６）　@「顧客名（カナ）」条件追加 <BR>
     *    ・ 引数.顧客コード != nullの場合、顧客コード条件を追加する。<BR>
     *    　@（※前方一致検索）<BR>
     * <BR>
     *    　@　@" and account_code like ? " <BR>
     * <BR>
     * ７）　@「口座種別」条件追加<BR>
     *    ・ 引数.口座種別 != null の場合、口座種別条件を追加する。<BR>
     * <BR>
     *    -　@口座種別 = 0(全て)の場合、　@条件追加無し。<BR>
     *    -　@口座種別 = 1(個人客)の場合、　@" and account_type <> ? "<BR>
     *    -　@口座種別 = 2(法@人客)の場合、　@" and account_type = ? "<BR>
     *    　@（※個人客の場合、顧客.口座タイプが"3：法@人"以外のレコード）<BR>
     *    　@（※法@人客の場合、顧客.口座タイプが"3：法@人"のレコード）<BR>
     * <BR>
     * ８）　@「口座開設日（自）」「口座開設日（至）」条件追加<BR>
     *    ・ 引数.口座開設日（自） != null 、引数.口座開設日（至） != null の場合、<BR>
     *    　@　@口座開設日条件を追加する。<BR>
     * <BR>
     *    -　@引数.口座開設日（自） != null && 引数.口座開設日（至） != null の場合、<BR>
     * <BR>
     *     　@　@" and account_open_date >= ? " <BR>
     *     　@　@" and account_open_date <= ? " <BR>
     * <BR>
     *     -　@引数.口座開設日（自） != null && 引数.口座開設日（至） = null の場合、<BR>
     *     　@（※口座開設日（自）のみが入力されていた場合）<BR>
     * <BR>
     *     　@　@" and account_open_date >= ? "<BR>
     * <BR>
     *     -　@引数.口座開設日（自） = null && 引数.口座開設日（至） != null の場合、<BR>
     *     　@（※口座開設日（至）のみが入力されていた場合）<BR>
     * <BR>
     *     　@　@" and account_open_date <= ? "<BR>
     * <BR>
     *  ９）　@文字列インスタンスを返却<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード
     * @@param l_strTraderCode - (扱者コード)<BR>
     * 扱者コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_strAccountType - (口座種別)<BR>
     * 口座種別
     * @@param l_datAccountOpenDateFrom - (口座開設日（自)）<BR>
     * 口座開設日（自)
     * @@param l_datAccountOpenDateTo - (口座開設日（至）)<BR>
     * 口座開設日（至）
     * @@param l_strAccountNameKana - (顧客名（カナ）)<BR>
     * 顧客名（カナ）
     * @@return String
     * @@roseuid 4163B2130031
     */
    protected String createQueryString(
        String[] l_strBranchCodes,
        String l_strTraderCode,
        String l_strAccountCode,
        String l_strAccountType,
        Date l_datAccountOpenDateFrom,
        Date l_datAccountOpenDateTo,
        String l_strAccountNameKana)
    {
        final String STR_METHOD_NAME =
            " createQueryString(String, String[], String, String, Date, Date, String) ";
        log.entering(STR_METHOD_NAME);

        //１）　@戻り値生成
        //戻り値の検索条件文字列インスタンス（：String）を生成
        StringBuffer l_sbQueryString = new StringBuffer();

        //２）　@「証券会社コード」条件追加
        //証券会社コード条件を追加する。
        //" institution_code =  ? "
        l_sbQueryString.append(" institution_code = ? ");

        //３）　@「部店コード」条件追加
        //部店条件を追加する。部店コード[]の要素数分"?"を追加する。
        //" and branch_code in (?, ?,,,) "
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
        	l_sbQueryString.append(" and branch_code in ( ");

            int l_intBranchCodeSize = l_strBranchCodes.length;
            for (int i = 0; i < l_intBranchCodeSize; i++)
            {
            	l_sbQueryString.append(" ? , ");
            }

            int l_intQueryStringLength = l_sbQueryString.length();
            String l_strQueryString = l_sbQueryString.substring(0, l_intQueryStringLength - 2);
            l_sbQueryString = new StringBuffer(l_strQueryString);
            l_sbQueryString.append(" ) ");
        }

        //４）　@「扱者コード」条件追加
        //引数.扱者コード != nullの場合、扱者コードを追加する。
        //" and sonar_trader_code = ? "
        if (l_strTraderCode != null)
        {
        	l_sbQueryString.append(" and sonar_trader_code = ? ");
        }

        //５）　@「顧客コード」条件追加
        //引数.顧客コード != nullの場合、顧客コード条件を追加する
        //（※前方一致検索）
        //" and account_code like ? "
        if (l_strAccountCode != null)
        {
        	l_sbQueryString.append(" and account_code like ? ");
        }

        //６）　@「顧客名（カナ）」条件追加
        //引数.顧客名（カナ） != nullの場合、顧客名（カナ）条件を追加する。
        //（※あいまい検索）
        //" and family_name_alt1 like ? "
        if (l_strAccountNameKana != null)
        {
        	l_sbQueryString.append(" and family_name_alt1 like ? ");
        }

        //７）　@「口座種別」条件追加
        //引数.口座種別 != null の場合、口座種別条件を追加する。
        if (l_strAccountType != null)
        {
            //口座種別 = 0(全て)の場合、　@条件追加無し。
            //口座種別 = 1(個人客)の場合、　@" and account_type <> ? "
            //口座種別 = 2(法@人客)の場合、　@" and account_type = ? "
            //（※個人客の場合、顧客.口座タイプが"3：法@人"以外のレコード）
            //（※法@人客の場合、顧客.口座タイプが"3：法@人"のレコード）
            if ((MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "").equals(l_strAccountType))
            {
            	l_sbQueryString.append(" and account_type <> ? ");
            }
            else if ((MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "").equals(l_strAccountType))
            {
            	l_sbQueryString.append(" and account_type = ? ");
            }
        }

        //８）　@「口座開設日（自）」「口座開設日（至）」条件追加
        // 引数.口座開設日（自） != null 、引数.口座開設日（至） != null の場合、口座開設日条件を追加する。
        if (l_datAccountOpenDateFrom != null || l_datAccountOpenDateTo != null)
        {
            //引数.口座開設日（自） != null && 引数.口座開設日（至） != null の場合、
            //" and account_open_date >= ? "
            //" and account_open_date <= ? "
            if (l_datAccountOpenDateFrom != null && l_datAccountOpenDateTo != null)
            {
            	l_sbQueryString.append(" and account_open_date >= ? ");
            	l_sbQueryString.append(" and account_open_date <= ? ");
            }

            //引数.口座開設日（自） = null && 引数.口座開設日（至） != null の場合、
            //（※口座開設日（至）のみが入力されていた場合）
            //" and account_open_date <= ? "
            else if (l_datAccountOpenDateFrom != null)
            {
            	l_sbQueryString.append(" and account_open_date >= ? ");
            }
            else if (l_datAccountOpenDateTo != null)
            {
            	l_sbQueryString.append(" and account_open_date <= ? ");
            }
        }

        //９）　@文字列インスタンスを返却
        String l_strQueryString = l_sbQueryString.toString();
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを編集する。<BR>
     * <BR>
     * １）　@戻り値生成<BR>
     *    ・ 戻り値編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     * ２）　@「証券会社コード」条件追加<BR>
     *    ・ 証券会社コード文字列をリストに追加する。<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@引数.証券会社コード<BR>
     * <BR>
     * ３）　@「部店コード」条件追加<BR>
     *    ・ 引数.部店コード[]内の部店コードをすべてリストに追加する。<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@部店コード<BR>
     * <BR>
     * ４）　@「扱者コード」条件追加<BR>
     *    ・ 扱者コード指定の場合（引数.扱者コード != null）、<BR>
     *　@　@　@扱者コード文字列をリストに追加する。<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@引数.扱者コード<BR>
     * <BR>
     * ５）　@「顧客コード」条件追加<BR>
     *    ・ 顧客コード指定の場合（引数.顧客コード != null）、<BR>
     *    　@　@顧客コード文字列をリストに追加する。<BR>
     *    　@（※前方一致検索）<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@引数.顧客コード + "%"<BR>
     * <BR>
     * ６    ）　@「顧客名（カナ）」条件追加 <BR>
     *    ・ 顧客名（カナ）指定の場合（引数.顧客名（カナ） != null）、<BR>
     *    　@　@顧客名（カナ）文字列をリストに追加する。]<BR>
     *    　@（※あいまい検索）<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@ "%" + 引数.顧客名（カナ） + "%"<BR>
     * <BR>
     * ７）　@「口座種別」条件追加<BR>
     *    ・ 口座種別指定の場合（引数.口座種別 != null）、<BR>
     *    　@　@口座種別文字列をリストに追加する。<BR>
     * <BR>
     *    -　@口座種別 = 0(全て)の場合、条件追加無し。<BR>　@
     * <BR>
     *    -　@口座種別 = 1(個人客)の場合、または、口座種別 = 2(法@人客)の場合、<BR>
     * <BR>
     *    　@[add()に指定する引数] <BR>
     *    　@MainAccountTypeEnum.3：法@人アカウント<BR>
     * <BR>
     * ８）　@「口座開設日（自）」「口座開設日（至）」条件追加 <BR>
     *    ・ 口座開設日指定の場合（引数.口座開設日（自） != null 、<BR>
     *    　@　@引数.口座開設日（至） != null）、<BR>
     *    　@口座開設日文字列をリストに追加する。<BR>
     * <BR>
     *    -　@引数.口座開設日（自） != null && 引数.口座開設日（至） != null の場合、<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@引数.口座開設日（自）を文字列（yyyymmdd）に変換した値<BR>
     *    　@引数.口座開設日（至）を文字列（yyyymmdd）に変換した値<BR>
     * <BR>
     *    -　@引数.口座開設日（自） != null && 引数.口座開設日（至） = null の場合、<BR>
     *    　@（※口座開設日（自）のみが入力されていた場合）<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@引数.口座開設日（自）を文字列（yyyymmdd）に変換した値 <BR>
     * <BR>
     *    -　@引数.口座開設日（自） = null && 引数.口座開設日（至） != null の場合、<BR>
     *    　@（※口座開設日（至）のみが入力されていた場合）<BR>
     * <BR>
     *    　@[add()に指定する引数]<BR>
     *    　@引数.口座開設日（至）を文字列（yyyymmdd）に変換した値 <BR>
     * <BR>
     * <BR>
     * ９）　@配列を返却<BR>
     *    戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード
     * @@param l_strTraderCode - (扱者コード)<BR>
     * 扱者コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_strAccountType - (口座種別)<BR>
     * 口座種別
     * @@param l_datAccountOpenDateFrom - (口座開設日（自)）<BR>
     * 口座開設日（自)
     * @@param l_datAccountOpenDateTo - (口座開設日（至）)<BR>
     * 口座開設日（至）
     * @@param l_strAccountNameKana - (顧客名（カナ）)<BR>
     * 顧客名（カナ）
     * @@return String[]
     * @@roseuid 4163B2130031
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strTraderCode,
        String l_strAccountCode,
        String l_strAccountType,
        Date l_datAccountOpenDateFrom,
        Date l_datAccountOpenDateTo,
        String l_strAccountNameKana)
    {
        final String STR_METHOD_NAME =
            " createQueryContainer(String, String[], String, String, String, Date, Date, String) ";
        log.entering(STR_METHOD_NAME);

        //１）　@戻り値生成
        //戻り値編集用インスタンス（：ArrayList）を生成
        List l_lisQueryContainer = new ArrayList();

        //２）　@「証券会社コード」条件追加
        //証券会社コード文字列をリストに追加する。
        //[add()に指定する引数]
        //引数.証券会社コード
        l_lisQueryContainer.add(l_strInstitutionCode);

        //３）　@「部店コード」条件追加
        //引数.部店コード[]内の部店コードをすべてリストに追加する。
        //[add()に指定する引数]
        //部店コード
        int l_intBranchCodeSize = 0;
        if (l_strBranchCodes != null)
        {
            l_intBranchCodeSize = l_strBranchCodes.length;
        }

        for (int i = 0; i < l_intBranchCodeSize; i++)
        {
            l_lisQueryContainer.add(l_strBranchCodes[i]);
        }

        //４）　@「扱者コード」条件追加
        //扱者コード指定の場合（引数.扱者コード != null）、扱者コード文字列をリストに追加する。
        //[add()に指定する引数]
        //引数.扱者コード
        if (l_strTraderCode != null)
        {
            l_lisQueryContainer.add(l_strTraderCode);
        }

        //５）　@「顧客コード」条件追加
        //顧客コード指定の場合（引数.顧客コード != null）、顧客コード文字列をリストに追加する。
        //（※前方一致検索）
        //[add()に指定する引数]
        //引数.顧客コード + "%"
        if (l_strAccountCode != null)
        {
            l_lisQueryContainer.add(l_strAccountCode + "%" );
        }

        //６）　@「顧客名（カナ）」条件追加
        //顧客名（カナ）指定の場合（引数.顧客名（カナ） != null）、顧客名（カナ）文字列をリストに追加する。
        //（※あいまい検索）
        //[add()に指定する引数]
        //"%" + 引数.顧客名（カナ） + "%"
        if (l_strAccountNameKana != null)
        {
            l_lisQueryContainer.add("%" + l_strAccountNameKana + "%" );
        }

        //７）　@「口座種別」条件追加
        //口座種別指定の場合（引数.口座種別 != null）、口座種別文字列をリストに追加する。
        if (l_strAccountType != null)
        {
            //-　@口座種別 = 0(全て)の場合、条件追加無し。
            //-　@口座種別 = 1(個人客)の場合、または、口座種別 = 2(法@人客)の場合、
            //[add()に指定する引数]
            //MainAccountTypeEnum.3：法@人アカウント

            if ((MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "").equals(l_strAccountType)
                || (MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "").equals(l_strAccountType))
            {
                l_lisQueryContainer.add(MainAccountTypeEnum.CORPORATE_ACCOUNT.intValue() + "");
            }
        }

        //８）　@「口座開設日（自）」「口座開設日（至）」条件追加
        //口座開設日指定の場合（引数.口座開設日（自） != null 、引数.口座開設日（至） != null）、
        //口座開設日文字列をリストに追加する。
        if (l_datAccountOpenDateFrom != null || l_datAccountOpenDateTo != null)
        {
            //-　@引数.口座開設日（自） != null && 引数.口座開設日（至） != null の場合、
            //[add()に指定する引数]
            if (l_datAccountOpenDateFrom != null && l_datAccountOpenDateTo != null)
            {
                //引数.口座開設日（自）を文字列（yyyymmdd）に変換した値
                String l_strAccountOpenDateFrom =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateFrom, "yyyyMMdd");

                //引数.口座開設日（至）を文字列（yyyymmdd）に変換した値
                String l_strAccountOpenDateTo =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateTo, "yyyyMMdd");
                l_lisQueryContainer.add(l_strAccountOpenDateFrom);
                l_lisQueryContainer.add(l_strAccountOpenDateTo);
            }

            //-　@引数.口座開設日（自） = null && 引数.口座開設日（至） != null の場合、
            //（※口座開設日（至）のみが入力されていた場合）
            else if (l_datAccountOpenDateFrom != null)
            {
                //[add()に指定する引数]
                //引数.口座開設日（至）を文字列（yyyymmdd）に変換した値
                String l_strAccountOpenDateFrom =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateFrom, "yyyyMMdd");
                l_lisQueryContainer.add(l_strAccountOpenDateFrom);
            }
            else if (l_datAccountOpenDateTo != null)
            {
                //[add()に指定する引数]
                //引数.口座開設日（自）を文字列（yyyymmdd）に変換した値
                String l_strAccountOpenDateTo =
                    WEB3DateUtility.formatDate(
                        l_datAccountOpenDateTo, "yyyyMMdd");
                l_lisQueryContainer.add(l_strAccountOpenDateTo);
            }
        }

        //９）　@配列を返却
        //戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。
        String[] l_strQueryContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。<BR>
     * <BR>
     * テーブル列物理名を使用し、<BR>
     * 以下の通り、ソート条件文字列（order by句）を編集する。<BR>
     * <BR>
     * １） 引数.ソートキーの要素数分以下の処理を繰り返し、<BR>
     * ソート条件文字列を作成する。<BR>
     * <BR>
     *    １－１） ソートキーを編集する。<BR>
     * <BR>
     *    　@１－１－１） ソートキー = 「部店コード」の場合<BR>
     *    　@　@　@　@　@　@　@顧客マスタテーブル.部店コード<BR>
     * <BR>
     *    　@１－１－２） ソート条件 = 「扱者コード」の場合 <BR>
     *    　@　@　@　@　@　@　@顧客マスタテーブル.扱者コード（SONAR） <BR>
     * <BR>
     *    　@１－１－３） ソート条件 = 「顧客コード」の場合 <BR>
     *    　@　@　@　@　@　@　@顧客マスタテーブル.口座コード<BR>
     * <BR>
     *    　@１－１－４） ソート条件 = 「口座開設日」の場合<BR>
     *    　@　@　@　@　@　@　@顧客マスタテーブル.口座登録日<BR>
     * <BR>
     *     １－２） ソート条件に該当するソート条件を編集する。<BR>
     *     　@　@　@　@昇順 ： asc <BR>
     *     　@　@　@　@降順 ： desc <BR>
     * <BR>
     *  ２） 作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected String createSortCond(WEB3AccInfoSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createSortCond(WEB3AccInfoSortKey[]) ";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１） 引数.ソートキーの要素数分以下の処理を繰り返し、ソート条件文字列を作成する。
        String l_strSortCond = "";
        int l_intSortKeysLength = l_sortKeys.length;

        //１－１） ソートキーを編集する。
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            String l_strKeyItem = l_sortKeys[i].keyItem;
            String l_strAscDesc = l_sortKeys[i].ascDesc;
            if (i != 0)
            {
                l_strSortCond += ","; 
            }
            //１－１－１） ソートキー = 「部店コード」の場合
            //顧客マスタテーブル.部店コード
            if (WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " branch_code ASC ";
                }
                else
                {
                    l_strSortCond += " branch_code DESC ";
                }
            }

            //１－１－２） ソート条件 = 「扱者コード」の場合
            //顧客マスタテーブル.扱者コード（SONAR）
            else if (WEB3AccInfoKeyItemDef.TRADER_CODE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " sonar_trader_code ASC ";
                }
                else
                {
                    l_strSortCond += " sonar_trader_code DESC ";
                }
            }

            //１－１－３） ソート条件 = 「顧客コード」の場合
            //顧客マスタテーブル.口座コード
            else if (WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " account_code ASC ";
                }
                else
                {
                    l_strSortCond += " account_code DESC ";
                }
            }

            //１－１－４） ソート条件 = 「口座開設日」の場合
            //顧客マスタテーブル.口座登録日
            else if (WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_strSortCond += " account_open_date ASC ";
                }
                else
                {
                    l_strSortCond += " account_open_date DESC ";
                }
            }
        }

        //１－２） ソート条件に該当するソート条件を編集する。
        //昇順 ： asc
        //降順 ： desc
        //２） 作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }

    /**
     * (isデータ内容)<BR>
     * 引数.データ内容番号により、処理対象レコードかを判定する。<BR>
     * <BR>
     * ・処理対象の場合、trueを、処理対象外の場合、falseを返却する。<BR>
     * <BR>
     * １） 引数.データ内容番号 = 00：データ内容未選択 の場合 <BR>
     *    １－１） trueを返却する。<BR>
     * <BR>
     * ２） 引数.データ内容番号 = 01：新規口座開設案内用データ の場合<BR>
     *    ２－１） 引数.口座登録日 != null の時、trueを返却する。<BR>
     * <BR>
     * ３） 引数.データ内容番号 = 02：振込みカード用データ の場合<BR>
     *    ３－１） 引数.口座登録日 != null の時、trueを返却する。<BR>
     * <BR>
     * ４） 引数.データ内容番号 = 03：口座移管案内用データ の場合<BR>
     *    ４－１） 「顧客マスター（全部店）テーブル」を下記条件で検索する。<BR>
     *    　@　@　@　@　@　@　@（※レコードが取得出来なかった場合はfalseを返却する）<BR>
     * <BR>
     *    　@ [条件]　@証券会社コード = 引数.証券会社コード<BR>
     *    　@　@　@　@　@　@部店コード = 引数.部店コード<BR>
     *    　@　@　@　@　@　@顧客コード = 引数.顧客コードの1桁～6桁目<BR>
     *    　@　@　@　@　@　@顧客コードCD = 引数.顧客コードの7桁目<BR>
     * <BR>
     *    ４－２） 検索結果により判定する。<BR>
     *    　@　@　@　@　@顧客（全部店分）.[口座移管前]部店コード != null &&<BR>
     *    　@　@　@　@　@顧客（全部店分）.[口座移管前]顧客コード != null の時、<BR>
     *    　@　@　@　@　@trueを返却する。<BR>
     * <BR>
     * ５）　@falseを返却する。<BR>
     * @@param l_strDataContentDiv - (データ内容番号)<BR>
     * データ内容番号
     * @@param l_datAccountOpenDate - (口座登録日)<BR>
     * 口座登録日
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@return false
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected boolean isDataContent(
        String l_strDataContentDiv,
        Date l_datAccountOpenDate,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " isDataContent(String, Date, String, String) ";
        log.entering(STR_METHOD_NAME);

        //・処理対象の場合、trueを、処理対象外の場合、falseを返却する。
        //１） 引数.データ内容番号 = 00：データ内容未選択 の場合
        //　@１－１） trueを返却する。
        if (WEB3AccInfoDataContentDef.DATA_CONTENT_NOT_SELECT.equals(l_strDataContentDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //２） 引数.データ内容番号 = 01：新規口座開設案内用データ の場合
        //　@２－１） 引数.口座登録日 != null の時、trueを返却する。
        else if (WEB3AccInfoDataContentDef.NEW_ACC_OPEN_GUIDANCE_DATA.equals(l_strDataContentDiv))
        {
            if (l_datAccountOpenDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //３） 引数.データ内容番号 = 02：振込みカード用データ の場合
        //　@３－１） 引数.口座登録日 != null の時、trueを返却する。
        else if (WEB3AccInfoDataContentDef.TRANSFER_CARD_DATA.equals(l_strDataContentDiv))
        {
            if (l_datAccountOpenDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //４） 引数.データ内容番号 = 03：口座移管案内用データ の場合
        else if (WEB3AccInfoDataContentDef.ACCOUNT_TRANSFER_GUIDANCE_DATA.equals(l_strDataContentDiv))
        {
            //　@４－１） 「顧客マスター（全部店）テーブル」を下記条件で検索する。
            List l_lisMainAccountAll = null;
            try
            {
                //[条件]　@証券会社コード = 引数.証券会社コード
                //部店コード = 引数.部店コード
                //顧客コード = 引数.顧客コードの1桁～6桁目
                //顧客コードCD = 引数.顧客コードの7桁目
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strWhere =
                    " comp_code = ? and  br_code = ?  and cust_code = ?  and cust_code_cd = ? ";

                Object[] l_objQueryContainer = {
                	l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode.substring(0, 6),
                    l_strAccountCode.substring(6)};

                l_lisMainAccountAll = l_queryProcessor.doFindAllQuery(
                    MainAccountAllRow.TYPE,
                    l_strWhere,
                    l_objQueryContainer);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //レコードが取得出来なかった場合はfalseを返却する
            if (l_lisMainAccountAll == null
                || l_lisMainAccountAll.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            //４－２） 検索結果により判定する。
            //顧客（全部店分）.[口座移管前]部店コード != null &&
            //顧客（全部店分）.[口座移管前]顧客コード != null の時、trueを返却する。
            MainAccountAllRow l_mainAccountAllRow =
                (MainAccountAllRow)l_lisMainAccountAll.get(0);
            if (l_mainAccountAllRow.getBeforeAccTransBrCode() != null
                && l_mainAccountAllRow.getBeforeAccTransCustCode() != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //５）　@falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isログインロック)<BR>
     * 引数.ログインロック区分により、処理対象レコードかを判定する。<BR>
     * <BR>
     * ・ 処理対象の場合、trueを、処理対象外の場合、falseを返却する。<BR>
     * <BR>
     * １） 引数.ログインロック区分 = 1：ログインロック客 の場合<BR>
     *    １－１） 引数.ログイン有効性 = 1：enabled && 引数.ログインエラー回数 >= 1 の時、<BR>
     *    trueを返却する。<BR>
     * <BR>
     * ２） 引数.ログインロック区分 = 0：全て の場合<BR>
     *    ２－１） trueを返却する。<BR>
     * <BR>
     * ３） falseを返却する。<BR>
     * @@param l_strLoginLockDiv - (ログインロック区分）<BR>
     * ログインロック区分
     * @@param l_lngLoginDisabledFlag - （ログイン有効性）<BR>
     * ログイン有効性
     * @@param l_lngLoginErrorCount - （ログインエラー回数）<BR>
     * ログインエラー回数
     * @@return false
     * @@roseuid 4163B2130031
     */
    protected boolean isLoginLock(
        String l_strLoginLockDiv,
        long l_lngLoginDisabledFlag,
        long l_lngLoginErrorCount)
    {
        final String STR_METHOD_NAME = " isLoginLock(String, long, long)";
        log.entering(STR_METHOD_NAME);

        //処理対象の場合、trueを、処理対象外の場合、falseを返却する。

        //１） 引数.ログインロック区分 = 1：ログインロック客 の場合
        //　@１－１） 引数.ログイン有効性 = 1：disabled && 引数.ログインエラー回数 >= 1 の時、
        //         trueを返却する。
        if (WEB3AccInfoLoginLockDivDef.LOGIN_LOCK_ACCOUNT.equals(l_strLoginLockDiv))
        {
            if (l_lngLoginDisabledFlag == 1 && l_lngLoginErrorCount >= 1)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //２） 引数.ログインロック区分 = 0：全て の場合
        //　@２－１） trueを返却する。
        else if (WEB3AccInfoLoginLockDivDef.ALL.equals(l_strLoginLockDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //３） falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get専用振込先口座レコード)<BR>
     * 検索条件から取得した「専用振込先口座レコード」をList型で返却する。<BR>
     * <BR>
     * １） doFindAllQuery()を使用して「専用振込先口座テーブル」を以下の条件で検索。<BR>
     *    　@　@（※レコードが取得出来ない場合は例外としない）<BR>
     * <BR>
     *    [条件]<BR>
     *    専用振込先口座テーブル.口座ID = 引数.口座ID<BR>
     * <BR>
     * ２） 検索結果を返却する。<BR>
     * @@param l_lngAccountId - （口座ID）<BR>
     * 口座ID
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected List getExclusiveUseAccountRecord(long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExclusiveUseAccountRecord(long)";
        log.entering(STR_METHOD_NAME);

        //１） doFindAllQuery()を使用して「専用振込先口座テーブル」を以下の条件で検索。
        //[条件]
        //専用振込先口座テーブル.口座ID = 引数.口座ID
        List l_lisExclusiveUseAccount = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " account_id = ? ";

            Object[] l_objQueryContainer = {new Long(l_lngAccountId)};

            l_lisExclusiveUseAccount = l_queryProcessor.doFindAllQuery(
                ExclusiveUseAccountRow.TYPE,
                l_strWhere,
                l_objQueryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２） 検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisExclusiveUseAccount;
    }

    /**
     * (getログインレコード)<BR>
     * 検索条件から取得した「ログインレコード」をList型で返却する。<BR>
     * <BR>
     * １） doFindAllQuery()を使用して「ログインテーブル」を以下の条件で検索。<BR>
     * 　@　@　@ （※レコードが取得出来ない場合は例外としない）<BR>
     * <BR>
     *    [条件]<BR>
     *    ログインテーブル.ログインID = 引数.口座ID<BR>
     * <BR>
     * ２） 検索結果を返却する。<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID　@
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    protected List getLoginRecord(long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getLoginRecord(long)";
        log.entering(STR_METHOD_NAME);

        //１） doFindAllQuery()を使用して「ログインテーブル」を以下の条件で検索。
        //[条件]
        //ログインテーブル.ログインID = 引数.口座ID
        List l_lisLoginRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " login_id = ? ";

            Object[] l_objQueryContainer = {new Long(l_lngAccountId)};

            l_lisLoginRecords = l_queryProcessor.doFindAllQuery(
                LoginRow.TYPE,
                l_strWhere,
                l_objQueryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２） 検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisLoginRecords;
    }
    
    /**
     * (get部店レコード)<BR>
     * 検索条件から取得した「部店レコード」をList型で返却する。<BR>
     * <BR>
     * １） doFindAllQuery()を使用して「部店テーブル」を以下の条件で検索。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@部店テーブル.部店ID = 引数.部店ID<BR>
     * <BR>
     * ２） 検索結果を返却する。<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID
     * @@return List
     * @@throws WEB3BaseException 
     */
    protected List getBranchRecord(long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchRecord(long)";
        log.entering(STR_METHOD_NAME);

        //１） doFindAllQuery()を使用して「部店テーブル」を以下の条件で検索。
        //[条件]
        //部店テーブル.部店ID = 引数.部店ID
        List l_lisBranchRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " branch_id = ? ";

            Object[] l_objQueryContainer = {new Long(l_lngBranchId)};

            l_lisBranchRecords = l_queryProcessor.doFindAllQuery(
                BranchRow.TYPE,
                l_strWhere,
                l_objQueryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２） 検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisBranchRecords;
    }
}
@
