head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@ /**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧サービスImpl(WEB3AdminBondDomesticProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 何文敏(中訊) 新規作成 仕様変更・モデルNo.193,No.199,No.209
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondSortKeyDef;
import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchConditionUnit;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayResponse;
import webbroker3.bd.message.WEB3BondDomesticProductRefInfo;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者国内債券銘柄一覧サービスImpl)<BR>
 * 管理者国内債券銘柄一覧サービスImpl<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListServiceImpl
    implements WEB3AdminBondDomesticProductListService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListServiceImpl.class);

    /**
     * @@roseuid 4691D3AE017E
     */
    public WEB3AdminBondDomesticProductListServiceImpl()
    {

    }

    /**
     * 管理者国内債券銘柄一覧サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * get検索画面表示()、get銘柄一覧画面表示()<BR>
     * のいずれかのメソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4663A03601F3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        // get検索画面表示
        if (l_request instanceof WEB3AdminBondDomesticProductListSearchDisplayRequest)
        {
            l_response =
                this.getSearchScreenDisplay(
                    (WEB3AdminBondDomesticProductListSearchDisplayRequest)l_request);
        }

        // get銘柄一覧画面表示
        else if (l_request instanceof WEB3AdminBondDomesticProductListDisplayRequest)
        {
            l_response =
                this.getProductListScreenDisplay(
                    (WEB3AdminBondDomesticProductListDisplayRequest)l_request);
        }

        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索画面表示)<BR>
     * 管理者国内債券銘柄一覧検索画面表示処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券銘柄一覧）get検索画面表示」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticProductListSearchDisplayResponse
     * @@throws WEB3BaseException
     * @@roseuid 4663AD29037A
     */
    protected WEB3AdminBondDomesticProductListSearchDisplayResponse getSearchScreenDisplay(
        WEB3AdminBondDomesticProductListSearchDisplayRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSearchScreenDisplay(WEB3AdminBondDomesticProductListSearchDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFromログイン情報( )
        WEB3Administrator l_adminstrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        // 機@能カテゴリーコード　@：機@能カテゴリコード.債券銘柄管理
        // is更新　@：false
        l_adminstrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);

        // createResponse( )
        WEB3AdminBondDomesticProductListSearchDisplayResponse l_domesticProductListResponse =
            (WEB3AdminBondDomesticProductListSearchDisplayResponse)l_request.createResponse();

        // プロパティ・セット
        String[] l_strBondTypeList = new String[]{
            WEB3BondTypeListDef.INDIVIDUAL_GOVERNMENT_BOND,
            WEB3BondTypeListDef.CORPORATE_BOND};
        l_domesticProductListResponse.bondTypeList = l_strBondTypeList;

        String[] l_strTradeHandleDivList =
            new String[]{WEB3TradeHandleDivDef.DISABLED, WEB3TradeHandleDivDef.MANAGER_CUSTOMER};
        l_domesticProductListResponse.tradeHandleDivList = l_strTradeHandleDivList;

        log.exiting(STR_METHOD_NAME);
        return l_domesticProductListResponse;
    }

    /**
     * (get銘柄一覧画面表示)<BR>
     * 管理者国内債券銘柄一覧検索処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券銘柄一覧）get銘柄一覧画面表示」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticProductListDisplayResponse
     * @@throws WEB3BaseException
     * @@roseuid 4663B960008C
     */
    protected WEB3AdminBondDomesticProductListDisplayResponse getProductListScreenDisplay(
        WEB3AdminBondDomesticProductListDisplayRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "getProductListScreenDisplay(WEB3AdminBondDomesticProductListDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);

        // get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // create検索条件文字列(管理者国内債券銘柄一覧検索条件)
        // 検索条件：　@リクエストデータ.管理者国内債券銘柄一覧検索条件
        String l_strCreateQueryString = this.createQueryString(l_request.searchCondition);

        // create検索条件データコンテナ(管理者国内債券銘柄一覧検索条件)
        // 検索条件：　@リクエストデータ.管理者国内債券銘柄一覧検索条件
        Object[] l_createQueryDataContainers =
            this.createQueryDataContainer(l_request.searchCondition);

        // createソート条件文字列(債券ソートキー[])
        // ソートキー一覧　@：　@リクエストデータ.ソートキー
        String l_strCreateSortCond = this.createSortCond(l_request.sortKeys);

        // get債券銘柄リスト(String, String, Object[], String)
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 検索条件文字列：　@create検索条件文字列()の戻り値
        // 検索条件データコンテナ：　@create検索条件データコンテナ()の戻り値
        // ソート条件文字列：　@createソート条件文字列()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();
        List l_lisBondProducts = l_bondProductManager.getBondProductList(
            l_strInstitutionCode,
            l_strCreateQueryString,
            l_createQueryDataContainers,
            l_strCreateSortCond);

        // WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        // l_list：　@get債券銘柄リスト()の戻り値
        // l_intRequestPageIndex：　@リクエストデータ.要求ページ番号
        // l_intRequestPageSize：　@リクエストデータ.ページ内表示行数
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_lisBondProducts,
                l_intRequestPageIndex,
                l_intRequestPageSize);

        // getArrayReturned(l_classType : Class)
        // 表示対象ページに該当するオブジェクト配列を取得する。
        // [引数]
        // _classType：　@債券銘柄Row.class
        BondProductRow[] l_bondProductRows =
            (BondProductRow[])l_pageIndexInfo.getArrayReturned(
                BondProductRow.class);

        // getArrayReturned()の戻り値の要素数分Loop処理
        int l_intLength = l_bondProductRows.length;
        List l_lisContainers = new ArrayList();

        for (int i = 0; i < l_intLength; i++)
        {
            // 国内債券銘柄照会情報( )
            WEB3BondDomesticProductRefInfo l_domesticProductRefInfo =
                new WEB3BondDomesticProductRefInfo();

            BondProductRow l_bondProductRow = l_bondProductRows[i];

            // プロパティ・セット
            // 銘柄ID         ：債券銘柄.銘柄ID
            l_domesticProductRefInfo.productId = l_bondProductRow.getProductId() + "";

            // 銘柄コード           ：債券銘柄.銘柄コード（SONAR)
            l_domesticProductRefInfo.productCode = l_bondProductRow.getHostProductCode();

            // 回号コード           ：債券銘柄.回号コード（SONAR）
            l_domesticProductRefInfo.productIssueCode = l_bondProductRow.getHostProductIssueCode();

            // 銘柄名（HOST)       ：債券銘柄.HOST銘柄名１
            l_domesticProductRefInfo.productNameHost = l_bondProductRow.getHostProductName1();

            // 銘柄名（WEB3)       ：債券銘柄.銘柄名
            l_domesticProductRefInfo.productNameWEB3 = l_bondProductRow.getProductName();

            // 応募単価            ：債券銘柄.買付単価
            if (!l_bondProductRow.getBuyPriceIsNull())
            {
                l_domesticProductRefInfo.applyPrice =
                    WEB3StringTypeUtility.formatNumber(l_bondProductRow.getBuyPrice());
            }
            else
            {
                l_domesticProductRefInfo.applyPrice = null;
            }

            // 取扱区分            ：債券銘柄.取扱区分
            l_domesticProductRefInfo.tradeHandleDiv = l_bondProductRow.getTradeHandleDiv();

            // 利率          ：債券銘柄.利率
            l_domesticProductRefInfo.coupon =
                WEB3StringTypeUtility.formatNumber(l_bondProductRow.getCoupon());

            // 発行日         ：債券銘柄.発行日
            l_domesticProductRefInfo.issueDate = WEB3DateUtility.toDay(l_bondProductRow.getIssueDate());

            // 償還日         ：債券銘柄.償還日
            l_domesticProductRefInfo.maturityDate = WEB3DateUtility.toDay(l_bondProductRow.getMaturityDate());

            // 年間利払回数      ：債券銘柄.年間利払回数
            l_domesticProductRefInfo.yearlyInterestPayments =
                l_bondProductRow.getYearlyInterestPayments() + "";

            // 利払日１            ：債券銘柄.利払日１
            l_domesticProductRefInfo.couponPaymentDate1 =
                l_bondProductRow.getInterestPaymentDay1st();

            // 利払日２            ：債券銘柄.利払日２
            l_domesticProductRefInfo.couponPaymentDate2 =
                l_bondProductRow.getInterestPaymentDay2nd();

            l_lisContainers.add(l_domesticProductRefInfo);
        }

        // getPageIndex( )
        // 表示ページ番号を取得する。
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();

        // getTotalPages( )
        // 総ページ数を取得する。
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        // getTotalRecords( )
        // 総レコード数を取得する。
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        // createResponse( )
        // レスポンスを生成する。
        WEB3AdminBondDomesticProductListDisplayResponse l_domesticProductListDisplayResponse =
            (WEB3AdminBondDomesticProductListDisplayResponse)l_request.createResponse();

        // プロパティ・セット
        // 表示ページ番号          ：getPageIndex()の戻り値
        l_domesticProductListDisplayResponse.pageIndex = l_intPageIndex + "";
        // 総ページ数           ：getTotalPages()の戻り値
        l_domesticProductListDisplayResponse.totalPages = l_intTotalPages + "";
        // 総レコード数          ：getTotalRecords()の戻り値
        l_domesticProductListDisplayResponse.totalRecords = l_intTotalRecords + "";
        // 銘柄照会情報一覧            ：国内債券債銘柄照会情報の配列
        WEB3BondDomesticProductRefInfo[] l_domesticProductRefInfos =
            new WEB3BondDomesticProductRefInfo[l_lisContainers.size()];
        l_lisContainers.toArray(l_domesticProductRefInfos);
        l_domesticProductListDisplayResponse.productRefInfoList = l_domesticProductRefInfos;

        log.exiting(STR_METHOD_NAME);
        return l_domesticProductListDisplayResponse;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）以下の文字列を作成する。<BR>
     * <BR>
     * 　@　@"債券タイプ != ?"<BR>
     * <BR>
     * ２）引数.検索条件.債券タイプ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@"and 債券タイプ = ?"<BR>
     * <BR>
     * ３）引数.検索条件.銘柄コード ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 銘柄コード（SONAR） = ? "<BR>
     * <BR>
     * ４）引数.検索条件.回号コード ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 回号コード（SONAR） = ? "<BR>
     * <BR>
     * ５）引数.検索条件.銘柄名（HOST） ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and HOST銘柄名１ like ? "<BR>
     * <BR>
     * ６）引数.検索条件.銘柄名（WEB3） ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 銘柄名 like ? "<BR>
     * <BR>
     * ７）引数.検索条件.発行日 ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 発行日 = ? "<BR>
     * <BR>
     * ８）引数.検索条件.償還日 ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@"and 償還日 = ? "<BR>
     * <BR>
     * ９）引数.検索条件利払日 ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and ( 利払日1 = ?　@or 利払日2 = ? ) " <BR>
     * <BR>
     * １０）引数.検索条件.取扱区分 ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 取扱区分 = ? "<BR>
     * <BR>
     * １１）作成した文字列を返却する。<BR>
     * <BR>
     * @@param l_searchCondition - (検索条件)<BR>
     * 検索条件<BR>
     * @@return String
     * @@roseuid 4663D208005D
     */
    protected String createQueryString(
        WEB3AdminBondDomesticProductListSearchConditionUnit l_searchCondition)
    {
        final String STR_METHOD_NAME =
            "createQueryString(WEB3AdminBondDomesticProductListSearchConditionUnit)";
        log.entering(STR_METHOD_NAME);

        // １）以下の文字列を作成する。
        // "債券タイプ != ?"
        String l_strQueryString = " bond_type != ? ";

        // ２）引数.検索条件.債券タイプ≠ null の場合、以下の文字列を最後尾に追加する。
        // "and 債券タイプ = ?"
        if (l_searchCondition.bondType != null)
        {
            l_strQueryString += " and bond_type = ? ";
        }

        // ３）引数.検索条件.銘柄コード ≠ null の場合、以下の文字列を最後尾に追加する。
        //  " and 銘柄コード（SONAR） = ? "
        if (l_searchCondition.productCode != null)
        {
            l_strQueryString += " and host_product_code = ? ";
        }

        // ４）引数.検索条件.回号コード ≠ null の場合、以下の文字列を最後尾に追加する。
        // " and 回号コード（SONAR） = ? "
        if (l_searchCondition.productIssueCode != null)
        {
            l_strQueryString += " and host_product_issue_code = ? ";
        }

        // ５）引数.検索条件.銘柄名（HOST） ≠ null の場合、以下の文字列を最後尾に追加する。
        // " and HOST銘柄名１ like ? "
        if (l_searchCondition.productNameHost != null)
        {
            l_strQueryString += " and host_product_name_1 like ? ";
        }

        // ６）引数.検索条件.銘柄名（WEB3） ≠ null の場合、以下の文字列を最後尾に追加する。
        //  and 銘柄名 like ? "
        if (l_searchCondition.productNameWEB3 != null)
        {
            l_strQueryString += " and product_name like ? ";
        }

        // ７）引数.検索条件.発行日 ≠ null の場合、以下の文字列を最後尾に追加する。
        // " and 発行日 = ? "
        if (l_searchCondition.issueDate != null)
        {
            l_strQueryString += " and issue_date = ? ";
        }

        // ８）引数.検索条件.償還日 ≠ null の場合、以下の文字列を最後尾に追加する。
        // " and 償還日 = ? "
        if (l_searchCondition.maturityDate != null)
        {
            l_strQueryString += " and maturity_date = ? ";
        }

        // ９）引数.検索条件利払日 ≠ null の場合、以下の文字列を最後尾に追加する。
        // " and ( 利払日1 = ?　@or 利払日2 = ? ) "
        if (l_searchCondition.interestPaymentDay != null)
        {
            l_strQueryString += " and ( interest_payment_day_1st = ?　@or interest_payment_day_2nd = ? ) ";
        }

        // １０）引数.検索条件.取扱区分 ≠ null の場合、以下の文字列を最後尾に追加する。
        // " and 取扱区分 = ? "
        if (l_searchCondition.tradeHandleDiv != null)
        {
            l_strQueryString += " and trade_handle_div = ? ";
        }

        // １１）作成した文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データのObjectの配列を作成する。<BR>
     * <BR>
     * １）Objectの配列を作成する。<BR>
     * <BR>
     * ２）配列に以下を追加する。<BR>
     * <BR>
     * 　@　@"外国債券"（※債券タイプ）<BR>
     * <BR>
     * ３）引数.検索条件.債券タイプ ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@引数.検索条件.債券タイプ<BR>
     * <BR>
     * ４）引数.検索条件.銘柄コード ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@引数.検索条件.銘柄コード<BR>
     * <BR>
     * ５）引数.検索条件.回号コード ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@引数.検索条件.回号コード<BR>
     * <BR>
     * ６）引数.検索条件.銘柄名（HOST） ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@% + 引数.検索条件.銘柄名（HOST) + %<BR>
     * <BR>
     * ７）引数.検索条件.銘柄名（WEB3） ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@% + 引数.検索条件.銘柄名（WEB3) + %<BR>
     * <BR>
     * ８）引数.検索条件.発行日 ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@引数.検索条件.発行日<BR>
     * <BR>
     * ９）引数.検索条件.償還日 ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@引数.検索条件.償還日<BR>
     * <BR>
     * １０）引数.検索条件.利払日 ≠ null の場合、配列に以下を２回追加する。<BR>
     * <BR>
     * 　@　@引数.検索条件.利払日<BR>
     * <BR>
     * １１）引数.検索条件.取扱区分 ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@引数.検索条件.取扱区分<BR>
     * <BR>
     * １２）作成した配列を返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_searchCondition - (検索条件)<BR>
     * 検索条件<BR>
     * @@return Object[]
     * @@roseuid 4663D2D80251
     */
    protected Object[] createQueryDataContainer(
        WEB3AdminBondDomesticProductListSearchConditionUnit l_searchCondition)
    {
        final String STR_METHOD_NAME =
            "createQueryData(WEB3AdminBondDomesticProductListSearchConditionUnit)";
        log.entering(STR_METHOD_NAME);

        // １）Objectの配列を作成する。
        List l_lisCreateQueryDataContainers = new ArrayList();
        // ２）配列に以下を追加する。
        // "外国債券"（※債券タイプ）
        l_lisCreateQueryDataContainers.add(new Integer(BondTypeEnum.FOREIGN_BOND.intValue()));

        // ３）引数.検索条件.債券タイプ ≠ null の場合、配列に以下を追加する。
        // 引数.検索条件.債券タイプ
        if (l_searchCondition.bondType != null)
        {
            l_lisCreateQueryDataContainers.add(new Integer(l_searchCondition.bondType));
        }

        // ４）引数.検索条件.銘柄コード ≠ null の場合、配列に以下を追加する。
        //  引数.検索条件.銘柄コード
        if (l_searchCondition.productCode != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.productCode);
        }

        // ５）引数.検索条件.回号コード ≠ null の場合、配列に以下を追加する。
        // 引数.検索条件.回号コード
        if (l_searchCondition.productIssueCode != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.productIssueCode);
        }

        // ６）引数.検索条件.銘柄名（HOST） ≠ null の場合、配列に以下を追加する。
        // % + 引数.検索条件.銘柄名（HOST) + %
        if (l_searchCondition.productNameHost != null)
        {
            l_lisCreateQueryDataContainers.add("%" + l_searchCondition.productNameHost + "%");
        }

        // ７）引数.検索条件.銘柄名（WEB3） ≠ null の場合、配列に以下を追加する。
        //  % + 引数.検索条件.銘柄名（WEB3) + %
        if (l_searchCondition.productNameWEB3 != null)
        {
            l_lisCreateQueryDataContainers.add("%" + l_searchCondition.productNameWEB3 + "%");
        }

        // ８）引数.検索条件.発行日 ≠ null の場合、配列に以下を追加する。
        // 引数.検索条件.発行日
        if (l_searchCondition.issueDate != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.issueDate);
        }

        // ９）引数.検索条件.償還日 ≠ null の場合、配列に以下を追加する。
        // 引数.検索条件.償還日
        if (l_searchCondition.maturityDate != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.maturityDate);
        }

        // １０）引数.検索条件.利払日 ≠ null の場合、配列に以下を２回追加する。
        // 引数.検索条件.利払日
        if (l_searchCondition.interestPaymentDay != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.interestPaymentDay);
            l_lisCreateQueryDataContainers.add(l_searchCondition.interestPaymentDay);
        }

        // １１）引数.検索条件.取扱区分 ≠ null の場合、配列に以下を追加する。
        // 引数.検索条件.取扱区分
        if (l_searchCondition.tradeHandleDiv != null)
        {
            l_lisCreateQueryDataContainers.add(l_searchCondition.tradeHandleDiv);
        }

        // １２）作成した配列を返却する。
        Object[] l_objReturns = new Object[l_lisCreateQueryDataContainers.size()];
        l_lisCreateQueryDataContainers.toArray(l_objReturns);

        log.exiting(STR_METHOD_NAME);
        return l_objReturns;
    }

    /**
     * (createソート条件文字列)<BR>
     * ソート条件文字列を作成する。<BR>
     * <BR>
     * １）引数.ソートキー一覧の要素数分、キー項目に対応するテーブルの列物理名と<BR>
     * 　@　@昇順／降順指定を付加したソート条件文字列を作成する。<BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り。<BR>
     * 　@　@　@※キー項目文字列（シンボル名）は、メッセージ定義書を参照。<BR>
     * 　@　@　@※テーブルの列物理名は、テーブルレイアウトを参照。<BR>
     * <BR>
     * 　@　@　@　@　@銘柄コード　@　@　@　@　@　@　@　@　@：　@債券銘柄.銘柄コード（SONAR)<BR>
     * 　@　@　@　@　@回号コード　@　@　@　@　@　@　@　@　@：　@債券銘柄.回号コード（SONAR）<BR>
     * 　@　@　@　@　@発行日　@　@　@　@　@　@　@　@ 　@　@ ：　@債券銘柄.発行日<BR>
     * 　@　@　@　@　@償還日　@　@　@　@　@　@　@　@ 　@　@ ：　@債券銘柄.償還日<BR>
     * 　@　@　@　@　@利率　@　@　@　@　@　@　@　@　@　@　@　@：　@債券銘柄.利率<BR>
     * <BR>
     * 　@　@・昇順／降順指定は、債券ソートキー.昇順／降順 に従い設定する。<BR>
     * <BR>
     * ２）作成したソート条件文字列を返却する。<BR>
     * <BR>
     * @@param l_sortKeys - (ソートキー一覧)<BR>
     * ソートキー一覧<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4663C18F03A9
     */
    protected String createSortCond(WEB3BondSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3BondSortKey[])";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）引数のソートキーが示すキー項目を、口座開設審査待ち列物理名に変換する
        //ソートキーの指定の通り、ソート条件文字列（order by句）を編集し返却する。
        //※　@ソートキーに指定される項目は以下の通り。
        String l_strSortCond = "";
        int l_intSortKeyLength = l_sortKeys.length;

        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            WEB3BondSortKey l_sortKey = l_sortKeys[i];

            //銘柄コード　@　@　@　@　@　@　@　@　@：　@債券銘柄.銘柄コード（SONAR)
            if (WEB3BondSortKeyDef.PRODUCT_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " host_product_code ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            //回号コード　@　@　@　@　@　@　@　@　@：　@債券銘柄.回号コード（SONAR）
            else if (WEB3BondSortKeyDef.PRODUCT_ISSUE_CODE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " host_product_issue_code ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            // 発行日　@　@　@　@　@　@　@　@ 　@　@ ：　@債券銘柄.発行日
            else if (WEB3BondSortKeyDef.ISSUE_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " issue_date ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            // 償還日　@　@　@　@　@　@　@　@ 　@　@ ：　@債券銘柄.償還日
            else if (WEB3BondSortKeyDef.MATURITY_DATE.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " maturity_date ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
            // 利率　@　@　@　@　@　@　@　@　@　@　@　@：　@債券銘柄.利率
            else if (WEB3BondSortKeyDef.COUPON.equals(l_sortKey.keyItem))
            {
                l_strSortCond += " coupon ";
                if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                {
                    l_strSortCond += " asc, ";
                }
                else
                {
                    l_strSortCond += " desc, ";
                }
            }
        }

        l_strSortCond =
            l_strSortCond.substring(0, l_strSortCond.length() - 2) + " ";

        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
}
@
