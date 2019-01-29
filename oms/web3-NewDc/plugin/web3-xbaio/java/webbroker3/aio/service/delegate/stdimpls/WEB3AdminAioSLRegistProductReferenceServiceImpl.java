head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLRegistProductReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保登録銘柄照会サービスImplクラス(WEB3AdminAioSLRegistProductReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 張騰宇 (中訊) 新規作成 モデル760 766 769
Revision History : 2007/10/18 孟亞南 (中訊) 仕様変更 モデル808
Revision History : 2007/10/31 張騰宇 (中訊) 仕様変更 モデル819
Revision History : 2007/11/08 趙林鵬 (中訊) 仕様変更 モデルNo.821
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.define.WEB3AioTargetPeriodDivDef;
import webbroker3.aio.define.WEB3SLSortKeyDef;
import webbroker3.aio.message.WEB3AdminSLProductRegiListRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.aio.service.delegate.WEB3AdminAioSLRegistProductReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (担保登録銘柄照会サービスImpl)<BR>
 * 担保登録銘柄照会サービス実装クラス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminAioSLRegistProductReferenceServiceImpl implements
    WEB3AdminAioSLRegistProductReferenceService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLRegistProductReferenceServiceImpl.class);

    /**
     * 担保登録銘柄一覧照会処理を実施する。 <BR>
     * <BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * １－１） 引数のリクエストデータが、担保銘柄登録一覧リクエストの場合<BR>
     * 　@－get担保登録銘柄一覧画面()をコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１－１） 引数のリクエストデータが、担保銘柄登録一覧リクエストの場合
        //－get担保登録銘柄一覧画面()をコールする。
        if (l_request instanceof WEB3AdminSLProductRegiListRequest)
        {
            l_response =
                this.getSLProductRegiList((WEB3AdminSLProductRegiListRequest)l_request);
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
     * (get担保登録銘柄一覧)<BR>
     * 担保登録銘柄一覧画面表示処理を行う。  <BR>
     * <BR>
     * シーケンス図  <BR>
     * 「get担保登録銘柄一覧」参照。 <BR>
     * ========================================================== <BR>
     * シーケンス図 ：(担保登録銘柄照会 / get担保登録銘柄一覧) <BR>
     * 具体位置：(検索対象レコードが存在しない場合、例外をスローする。)<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00398<BR>
     * ========================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 担保銘柄登録一覧リクエストクラス<BR>
     * @@return WEB3AdminSLProductRegiListResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegiListResponse getSLProductRegiList(
        WEB3AdminSLProductRegiListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLProductRegiList(WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductRegiListResponse l_response = null;

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード：B0602
        //is更新：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, false);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //create検索条件文字列(担保銘柄登録一覧リクエスト)
        String l_strQueryString = this.createQueryString(l_request);

        //create検索条件データコンテナ
        Object[] l_objQueryDataContainer =
            this.createQueryDataContainer(l_strInstitutionCode, l_request);

        //createソートキー
        String l_sortKey = this.createSortKey(l_request.sortKeys);

        List l_lisRows = null;
        try
        {
            //getDefaultProcessor
            //Rowタイプ： 担保銘柄Row.TYPE
            //Where： create取得条件文字列()の戻り値
            //orderBy： createソートキー()の戻り値
            //condition： null
            //リスト： create検索条件データコンテナ()の戻り値
            //ページサイズ： リクエスト.ページ内表示行数
            //ページ番号： リクエスト.要求ページ番号 - 1
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                SecurityProductRow.TYPE,
                l_strQueryString,
                l_sortKey,
                null,
                l_objQueryDataContainer,
                Integer.parseInt(l_request.pageSize),
                (Integer.parseInt(l_request.pageIndex) - 1));
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRows.isEmpty())
        {
            //検索対象レコードが存在しない場合、例外をスローする
            log.debug("該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        //WEB3PageIndexInfo(l_list : List,
        //           l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo（）に指定する引数]
        //l_list : doFindAllQuery（）の戻り値
        //l_intRequestPageIndex :　@リクエスト.要求ページ番号をint型に変換した値
        //l_intRequestPageSize :　@リクエスト.ページ内表示行数をint型に変換した値
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_lisRows, Integer.parseInt(l_request.pageIndex), Integer.parseInt(l_request.pageSize));

        //getListReturned( )
        List l_lisReturned = l_pageIndexInfo.getListReturned();

        //ArrayList( )
        List l_lisArrayList = new ArrayList();

        int l_intSize = l_lisReturned.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //create担保登録銘柄情報(担保銘柄Params)
            SecurityProductRow l_securityProduceRow = (SecurityProductRow)l_lisReturned.get(i);
            WEB3SLProductInfoUnit l_productInfoUnit =
                this.createSLProductInfoUnit(new SecurityProductParams(l_securityProduceRow));
            //add(arg0 : Object)
            l_lisArrayList.add(l_productInfoUnit);
        }

        //getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //createResponse( )
        l_response = (WEB3AdminSLProductRegiListResponse)l_request.createResponse();

        //銘柄登録情報一覧：　@ArrayList（）.toArrayで変換した値
        WEB3SLProductInfoUnit[] l_productInfoUnits =
            new WEB3SLProductInfoUnit[l_lisArrayList.size()];
        l_response.stockLoanProductInfoList =
            (WEB3SLProductInfoUnit[])l_lisArrayList.toArray(l_productInfoUnits);
        //総ページ数： WEB3StringTypeUtility.formatNumber(getTotalPages()の戻り値)
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_intTotalPages);
        //総レコード数： WEB3StringTypeUtility.formatNumber(getTotalRecords()の戻り値)
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_intTotalRecords);
        //表示ページ番号：リクエスト.要求ページ番号
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 担保銘柄テーブルの検索条件文字列を作成する。 <BR>
     * <BR>
     * <BR>
     * １）　@検索条件文字列インスタンスを生成する。   <BR>
     * <BR>
     * ２）　@証券会社コードを検索条件文字列に追加する。  <BR>
     * <BR>
     * 　@"institution_code = ? "  <BR>
     * <BR>
     * ３）　@引数:検索情報.銘柄タイプ != nullの場合、   <BR>
     * 銘柄タイプを検索条件文字列に追加する。  <BR>
     * <BR>
     * 検索条件文字列 += "and product_type = ? "   <BR>
     * <BR>
     * ４）　@引数:検索情報.銘柄コード != nullの場合、   <BR>
     * 　@　@ 銘柄コードを検索条件文字列に追加する。   <BR>
     * <BR>
     * 検索条件文字列 += "and product_code like ? %"   <BR>
     * <BR>
     * ５）　@引数:検索情報.適格区分 != nullの場合、   <BR>
     * 　@　@ 適格区分を検索条件文字列に追加する。   <BR>
     * <BR>
     * 検索条件文字列 += "and fit_flg = ? "   <BR>
     * <BR>
     * ６）　@引数:検索情報.適用期間区分 != nullの場合、   <BR>
     * <BR>
     * ６－１）　@適用期間区分が0：適用期間中の場合、 <BR>
     * 　@検索条件文字列 += "and　@apply_term_from <= ? "   <BR>
     * 　@検索条件文字列 += "and (apply_term_to >= ?  or apply_term_to = null)" <BR>
     * <BR>
     * <BR>
     * ６－２）　@適用期間区分が1：適用期間外の場合、 <BR>
     * 　@検索条件文字列 += "and (apply_term_from > ? "   <BR>
     * 　@検索条件文字列 += "or apply_term_to < ? )"   <BR>
     * <BR>
     * <BR>
     * ７）　@作成した検索条件文字列インスタンスを返却する。<BR>
     * @@param l_request - (検索情報)<BR>
     * 担保銘柄登録一覧リクエストオブジェクト<BR>
     * @@return String
     */
    private String createQueryString(WEB3AdminSLProductRegiListRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        //１）　@検索条件文字列インスタンスを生成する。
        StringBuffer l_sbWhere = new StringBuffer();

        //２）　@証券会社コードを検索条件文字列に追加する。
        //"institution_code = ? "
        l_sbWhere.append("institution_code = ? ");

        //３）　@引数:検索情報.銘柄タイプ != nullの場合、
        //銘柄タイプを検索条件文字列に追加する。
        //検索条件文字列 += "and product_type = ? "
        if (l_request.productType != null)
        {
            l_sbWhere.append("and product_type = ? ");
        }

        //４）　@引数:検索情報.銘柄コード != nullの場合、
        //　@　@ 銘柄コードを検索条件文字列に追加する。
        //検索条件文字列 += "and product_code like ? %"
        if (l_request.productCode != null)
        {
            l_sbWhere.append("and product_code like ? || '%' ");
        }

        //５）　@引数:検索情報.適格区分 != nullの場合、
        //　@　@ 適格区分を検索条件文字列に追加する。
        //検索条件文字列 += "and fit_flg = ? "
        if (l_request.qualifiedDiv != null)
        {
            l_sbWhere.append("and fit_flg = ? ");
        }

        //６）　@引数:検索情報.適用期間区分 != nullの場合、
        if (l_request.targetPeriodDiv != null)
        {
            //６－１）　@適用期間区分が0：適用期間中の場合、
            //　@検索条件文字列 += "and　@apply_term_from <= ? "
            //　@検索条件文字列 += "and (apply_term_to >= ?  or apply_term_to = null) "
            if (WEB3AioTargetPeriodDivDef.TARGET_PERIODING.equals(l_request.targetPeriodDiv))
            {
                l_sbWhere.append("and apply_term_from <= ? ");
                l_sbWhere.append("and (apply_term_to >= ? or apply_term_to is null)");
            }
            //６－２）　@適用期間区分が1：適用期間外の場合、
            //　@検索条件文字列 += "and (apply_term_from > ? "
            //　@検索条件文字列 += "or apply_term_to < ? )"
            else if (WEB3AioTargetPeriodDivDef.TARGET_PERIOD_OUT.equals(l_request.targetPeriodDiv))
            {
                l_sbWhere.append("and (apply_term_from > ? ");
                l_sbWhere.append("or apply_term_to < ? ) ");
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbWhere.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータリストを生成する。  <BR>
     * <BR>
     * <BR>
     * １）　@空のArrayListを生成する。  <BR>
     * <BR>
     * ２）　@証券会社コード <BR>
     * <BR>
     * 引数証券会社コードを１）のリストに追加する。  <BR>
     * <BR>
     * ３）　@銘柄タイプ <BR>
     * <BR>
     * 引数.検索情報.銘柄タイプ != null の場合  <BR>
     * <BR>
     * 引数.検索情報.銘柄タイプを１）のリストに追加する。  <BR>
     * <BR>
     * ４）　@銘柄コード <BR>
     * <BR>
     * 引数.検索情報.銘柄コード != null の場合  <BR>
     * <BR>
     * 引数.検索情報.銘柄コードを１）のリストに追加する。  <BR>
     * <BR>
     * <BR>
     * ５）　@適格区分 <BR>
     * <BR>
     * 引数.検索情報.適格区分 != null の場合  <BR>
     * <BR>
     * 引数.検索情報.適格区分を１）のリストに追加する。  <BR>
     * <BR>
     * <BR>
     * ６）　@適用期間区分 <BR>
     * <BR>
     * 引数.検索情報.適用期間区分 != null の場合  <BR>
     * <BR>
     * ・TradingSystem.getSystemTimestamp()の戻り値を <BR>
     * 　@Date型に変換した値を１）のリストに2回追加する。  <BR>
     * <BR>
     * <BR>
     * ７）　@リストから配列を取得し、返却する。  <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_request - (検索情報)<BR>
     * 担保銘柄登録一覧リクエストクラス<BR>
     * @@return Object[]
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode, WEB3AdminSLProductRegiListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        //１）　@空のArrayListを生成する。
        List l_lisQueryContainers = new ArrayList();

        //２）　@証券会社コード 引数証券会社コードを１）のリストに追加する。
        l_lisQueryContainers.add(l_strInstitutionCode);

        //３）　@銘柄タイプ
        //引数.検索情報.銘柄タイプ != null の場合
        //引数.検索情報.銘柄タイプを１）のリストに追加する。
        if (l_request.productType != null)
        {
            l_lisQueryContainers.add(l_request.productType);
        }

        //４）　@銘柄コード
        //引数.検索情報.銘柄コード != null の場合
        //引数.検索情報.銘柄コードを１）のリストに追加する。
        if (l_request.productCode != null)
        {
            l_lisQueryContainers.add(l_request.productCode);
        }

        //５）　@適格区分
        //引数.検索情報.適格区分 != null の場合
        //引数.検索情報.適格区分を１）のリストに追加する。
        if (l_request.qualifiedDiv != null)
        {
            l_lisQueryContainers.add(l_request.qualifiedDiv);
        }

        //６）　@適用期間区分
        //引数.検索情報.適用期間区分 != null の場合
        //・TradingSystem.getSystemTimestamp()の戻り値を
        //　@Date型に変換した値を１）のリストに2回追加する。
        if (l_request.targetPeriodDiv != null)
        {
            l_lisQueryContainers.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            l_lisQueryContainers.add(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisQueryContainers.toArray();
    }

    /**
     * (createソートキー)<BR>
     * ソート条件文字列を編集する。   <BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、<BR>
     * ソート条件文字列（order by句）を編集する。  <BR>
     * <BR>
     * １）パラメータ.ソートキーの要素数分以下の処理を繰り返し、   <BR>
     * 　@　@　@ソート条件文字列を作成する。  <BR>
     * 　@１－１）ソートキーを編集  <BR>
     * 　@　@１－１－１）ソートキー = 銘柄タイプの場合  <BR>
     * 　@　@　@　@　@　@　@　@担保銘柄テーブル.product_type <BR>
     * <BR>
     * 　@　@１－１－２）ソート条件 = 銘柄コードの場合  <BR>
     * 　@　@　@　@　@　@　@　@担保銘柄テーブル.product_code <BR>
     * <BR>
     * 　@　@１－１－３）ソート条件 = 適用期間fromの場合  <BR>
     * 　@　@　@　@　@　@　@　@担保銘柄テーブル.apply_term_from <BR>
     * <BR>
     * <BR>
     * 　@１－２）ソート条件に該当するソート条件を編集する。  <BR>
     * 　@　@　@　@　@昇順：asc  <BR>
     * 　@　@　@　@　@降順：desc  <BR>
     * <BR>
     * ２） 作成したソート条件文字列を返却する。 <BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * 担保ローンソートキーオブジェクト<BR>
     * @@return String
     */
    private String createSortKey(WEB3SLSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortKey(WEB3SLSortKey[])";
        log.entering(STR_METHOD_NAME);

        String l_strSortKey = "";

        //パラメータ.ソートキーの要素数分以下の処理を繰り返し、
        //ソート条件文字列を作成する。
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //１－１－１）ソートキー = 銘柄タイプの場合
            //　@　@　@　@担保銘柄テーブル.product_type
            if (WEB3SLSortKeyDef.PRODUCT_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strSortKey += " product_type ";
            }
            else if (WEB3SLSortKeyDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSortKey += " product_code ";
            }
            else if (WEB3SLSortKeyDef.TARGET_PERIOD_FORM.equals(l_sortKeys[i].keyItem))
            {
                l_strSortKey += " apply_term_from ";
            }

            //　@１－２）ソート条件に該当するソート条件を編集する。
            //　@　@　@　@　@昇順：asc
            //　@　@　@　@　@降順：desc
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortKey += "asc,";
            }
            else
            {
                l_strSortKey += "desc,";
            }
        }
        l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 1);
        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }

    /**
     * (create担保登録銘柄情報)<BR>
     * 担保銘柄登録情報インスタンスを生成する。  <BR>
     * <BR>
     * <BR>
     * １）　@担保銘柄登録情報インスタンスを生成する。 <BR>
     * <BR>
     * ２）　@拡張プロダクトマネージャ.getProduct(arg0 )で、<BR>
     * 　@　@　@銘柄オブジェクトを取得する。 <BR>
     * 　@[引数] <BR>
     * 　@arg0：　@引数:担保銘柄Params.銘柄ID <BR>
     * <BR>
     * ３）　@以下のように、担保銘柄登録情報インスタンスに値をセットする。 <BR>
     * <BR>
     * 　@担保銘柄登録情報.銘柄ID = 引数:担保銘柄Params.銘柄ID <BR>
     * 　@担保銘柄登録情報.銘柄コード = 引数:担保銘柄Params.銘柄コード <BR>
     * 　@担保銘柄登録情報.銘柄タイプ = <BR>
     * 　@　@　@引数:担保銘柄Params.銘柄タイプをString型に変換した値 <BR>
     * 　@担保銘柄登録情報.銘柄コード = 引数:担保銘柄Params.銘柄コード <BR>
     * 　@担保銘柄登録情報.銘柄名 = getProduct（）の戻り値.銘柄名 <BR>
     * 　@担保銘柄登録情報.適格区分 = 引数:担保銘柄Params.適格区分 <BR>
     * 　@担保銘柄登録情報.掛目 = <BR>
     * 　@　@　@引数:担保銘柄Params.評価掛目をString型に変換した値 <BR>
     * 　@担保銘柄登録情報.適用期間from = 引数:担保銘柄Params.適用期間from <BR>
     * 　@担保銘柄登録情報.適用期間to = 引数:担保銘柄Params.適用期間to <BR>
     * 　@担保銘柄登録情報.理由 = 引数:担保銘柄Params.理由 <BR>
     * <BR>
     * ４）　@担保銘柄登録情報インスタンスを返却する。<BR>
     * @@param l_securityProductParams - (担保銘柄Params)<BR>
     * 担保銘柄Paramsオブジェクト<BR>
     * @@return WEB3SLProductInfoUnit
     * @@throws WEB3BaseException
     */
    private WEB3SLProductInfoUnit createSLProductInfoUnit(SecurityProductParams l_securityProductParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSLProductInfoUnit(SecurityProductParams)";
        log.entering(STR_METHOD_NAME);

        //１）　@担保銘柄登録情報インスタンスを生成する。
        WEB3SLProductInfoUnit l_slProductInfoUnit = new WEB3SLProductInfoUnit();

        //２）　@拡張プロダクトマネージャ.getProduct(arg0 )で、銘柄オブジェクトを取得する。
        //　@[引数]
        //　@arg0：　@引数:担保銘柄Params.銘柄ID
        Product l_product = null;
        try
        {
            TradingModule l_tradingModule =
                GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            ProductManager l_productMgr = l_tradingModule.getProductManager();
            l_product = l_productMgr.getProduct(l_securityProductParams.getProductId());
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

        //３）　@以下のように、担保銘柄登録情報インスタンスに値をセットする。
        //　@担保銘柄登録情報.銘柄ID = 引数:担保銘柄Params.銘柄ID
        l_slProductInfoUnit.productId = l_securityProductParams.getProductId();
        //　@担保銘柄登録情報.銘柄コード = 引数:担保銘柄Params.銘柄コード
        l_slProductInfoUnit.productCode = l_securityProductParams.getProductCode();
        //　@担保銘柄登録情報.銘柄タイプ = 引数:担保銘柄Params.銘柄タイプをString型に変換した値
        l_slProductInfoUnit.productType = l_securityProductParams.getProductType().intValue() + "";
        //　@担保銘柄登録情報.銘柄名 = getProduct（）の戻り値.銘柄名
        l_slProductInfoUnit.productName = l_product.getStandardName();
        //　@担保銘柄登録情報.適格区分 = 引数:担保銘柄Params.適格区分
        l_slProductInfoUnit.qualifiedDiv = l_securityProductParams.getFitFlg();
        //　@担保銘柄登録情報.掛目 = 引数:担保銘柄Params.評価掛目をString型に変換した値
        l_slProductInfoUnit.weight = l_securityProductParams.getEstimationRatio() + "";
        //　@担保銘柄登録情報.適用期間from = 引数:担保銘柄Params.適用期間from
        l_slProductInfoUnit.targetPeriodFrom = l_securityProductParams.getApplyTermFrom();
        //　@担保銘柄登録情報.適用期間to = 引数:担保銘柄Params.適用期間to
        l_slProductInfoUnit.targetPeriodTo = l_securityProductParams.getApplyTermTo();
        //　@担保銘柄登録情報.理由 = 引数:担保銘柄Params.理由
        l_slProductInfoUnit.reason = l_securityProductParams.getReason();

        //４）　@担保銘柄登録情報インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_slProductInfoUnit;
    }
}
@
