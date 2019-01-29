head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧サービスImpl(WEB3CCOperatorAccountListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成・モデルNo.039、No.042
*/

package webbroker3.login.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.data.TraderAccountInfoParams;
import webbroker3.gentrade.data.TraderAccountInfoRow;
import webbroker3.login.define.WEB3TraderAccountInfosSortKeyDef;
import webbroker3.login.message.WEB3CCOperatorAccountListRequest;
import webbroker3.login.message.WEB3CCOperatorAccountListResponse;
import webbroker3.login.message.WEB3TraderAccountInfo;
import webbroker3.login.message.WEB3TraderAccountInfosSortKey;
import webbroker3.login.service.delegate.WEB3CCOperatorAccountListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (CCオペレータ対象顧客一覧サービスImpl)<BR>
 * CCオペレータ対象顧客一覧サービスImpl<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListServiceImpl extends WEB3LoginServiceBaseImpl
    implements WEB3CCOperatorAccountListService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3CCOperatorAccountListServiceImpl.class);

    /**
     * (CCオペレータ対象顧客一覧サービスImpl)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46A45A2100CB
     */
    public WEB3CCOperatorAccountListServiceImpl()
    {

    }

    /**
     * 引数で与えられたリクエストを基に業務処理を行い、処理結果をレスポンス<BR>
     * に設定して返す。<BR>
     * <BR>
     * リクエストデータの型が、<BR>
     * 　@[CCオペレータ対象顧客一覧リクエストの場合]<BR>
     * 　@　@this.get対象顧客一覧()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>   
     * リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4694957A036E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        // CCオペレータ対象顧客一覧リクエストの場合
        if (l_request instanceof WEB3CCOperatorAccountListRequest)
        {
            // this.get対象顧客一覧()メソッドをコールする。
            l_response =
                this.getAccountList(
                    (WEB3CCOperatorAccountListRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (get対象顧客一覧)<BR>
     * 対象顧客一覧を取得する。<BR>
     * <BR>
     * シーケンス図「（対象顧客一覧）get対象顧客一覧処理」 参照。<BR>
     * <BR>
     * ========================================================<BR>
     * 　@シーケンス図(「（対象顧客一覧）get対象顧客一覧処理」)<BR>
     * 　@　@取得できないの場合、予期しないシステムエラーが発生しました。<BR>
     * 　@　@　@例外をスローする。<BR>
     * 　@　@　@class:　@WEB3SystemLayerException<BR>
     * 　@　@　@tag　@:　@SYSTEM_ERROR_80002<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * CCオペレータ対象顧客一覧リクエスト<BR>
     * @@return WEB3CCOperatorAccountListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4694993E0032
     */
    protected WEB3CCOperatorAccountListResponse getAccountList(
        WEB3CCOperatorAccountListRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " getAccountList(WEB3CCOperatorAccountListRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        l_request.validate();

        // getSessionProperty(arg0 : String)
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strCCOperatorID =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.CCOPERATOR_ID);
        if (l_strCCOperatorID == null)
        {
            log.debug("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                getClass().getName() + STR_METHOD_NAME,
                "予期しないシステムエラーが発生しました。");
        }

        // create条件文字列(String, String, String)
        // 顧客コード：リクエスト.顧客コード
        // 顧客名（漢字）：リクエスト.お名前（漢字）
        // 顧客名（カナ）：リクエスト.お名前（カナ）
        String l_strCreateQueryString = this.createQueryString(
            l_request.acceptCode,
            l_request.nameKanji,
            l_request.nameKana);

        // create条件コンテナ(long, String, String, String)
        // 顧客コード：リクエスト.顧客コード
        // 顧客名（漢字）：リクエスト.お名前（漢字）
        // 顧客名（カナ）：リクエスト.お名前（カナ）
        Object[] l_createQueryDataContainers = this.createQueryDataContainer(
            Long.parseLong(l_strCCOperatorID),
            l_request.acceptCode,
            l_request.nameKanji,
            l_request.nameKana);

        // createソート条件(対象顧客ソートキー[])
        // ソートキー：リクエスト.ソートキー
        String l_strCreateSortCond = this.createSortCond(l_request.sortKeys);

        // doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : String, arg3 : String, arg4 : Object[])
        // 行タイプ：扱者別顧客情報テーブルRow.TYPE
        // 検索条件文字列： 生成した検索条件文字列
        // ソート条件： 生成したソート条件
        // コンディション： null
        // 検索条件データコンテナ： 生成した検索条件データコンテナ
        List l_lisResults = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                TraderAccountInfoRow.TYPE,
                l_strCreateQueryString,
                l_strCreateSortCond,
                null,
                l_createQueryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // createResponse( )
        // レスポンスデータを生成する。
        WEB3CCOperatorAccountListResponse l_operatorAccountListResponse =
            (WEB3CCOperatorAccountListResponse)l_request.createResponse();

        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;
        int l_intPageIndex = 0;
        // （分岐フロー：データ取得できた場合）
        if (!l_lisResults.isEmpty())
        {
            // WEB3PageIndexInfo(List, int, int)
            // 明細リスト： 取得した扱者別顧客情報テーブルのリスト
            // 要求ページ番号： リクエスト.要求ページ番号
            // 要求ページ内表示行数： リクエスト.ページ内表示行数
            int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
            WEB3PageIndexInfo l_pageIndexInfo =
                new WEB3PageIndexInfo(
                    l_lisResults,
                    l_intRequestPageIndex,
                    l_intRequestPageSize);

            // getArrayReturned(Class)
            // class：扱者別顧客情報テーブルParams.class
            TraderAccountInfoParams[] l_traderAccountInfoParams =
                (TraderAccountInfoParams[])l_pageIndexInfo.getArrayReturned(
                    TraderAccountInfoParams.class);

            // 取得した表示対象の扱者別顧客情報テーブルParamsの配列の件数分、処理を繰り返す。
            int l_intLength = l_traderAccountInfoParams.length;
            List l_lisContainers = new ArrayList();

            for (int i = 0; i < l_intLength; i++)
            {
                // 紐付顧客情報( )
                // 紐付顧客情報オブジェクトを生成する。
                WEB3TraderAccountInfo l_traderAccountInfo = new WEB3TraderAccountInfo();

                // １．紐付顧客情報を以下の通り編集する
                // 顧客コード：扱者別顧客情報テーブルParams.口座コード
                l_traderAccountInfo.acceptCode = l_traderAccountInfoParams[i].getAccountCode();
                // 名前（漢字）：扱者別顧客情報テーブルParams.名前（苗字）
                l_traderAccountInfo.nameKanji = l_traderAccountInfoParams[i].getFamilyName();
                // 名前（カナ）：扱者別顧客情報テーブルParams.名前（苗字1）
                l_traderAccountInfo.nameKana = l_traderAccountInfoParams[i].getFamilyNameAlt1();
                // 上記以外は扱者別顧客情報テーブルParams.同項目
                l_traderAccountInfo.accountID = l_traderAccountInfoParams[i].getAccountId();
                l_traderAccountInfo.branchCode = l_traderAccountInfoParams[i].getBranchCode();
                // ２．リストを生成し、編集した紐付顧客情報を順次追加
                l_lisContainers.add(l_traderAccountInfo);
            }

            // getTotalPages( )
            l_intTotalPages = l_pageIndexInfo.getTotalPages();

            // getTotalRecords( )
            l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

            // getPageIndex( )
            l_intPageIndex = l_pageIndexInfo.getPageIndex();

            // プロパティセット
            // 対象顧客一覧：紐付顧客情報のリスト
            WEB3TraderAccountInfo[] l_traderAccountInfos =
                new WEB3TraderAccountInfo[l_lisContainers.size()];
            l_lisContainers.toArray(l_traderAccountInfos);
            l_operatorAccountListResponse.traderAccoutInfos = l_traderAccountInfos;
            // 総ページ数：getTotalPages()の戻り値
            l_operatorAccountListResponse.totalPages = l_intTotalPages + "";
            // 総レコード数：getTotalRecords()の戻り値
            l_operatorAccountListResponse.totalRecords = l_intTotalRecords + "";
            // 表示ページ番号：getPageIndex()の戻り値
            l_operatorAccountListResponse.pageIndex = l_intPageIndex + "";
        }
        else
        {
            // 対象顧客一覧：null
            l_operatorAccountListResponse.traderAccoutInfos = null;
            // 総ページ数：0
            l_operatorAccountListResponse.totalPages = l_intTotalPages + "";
            // 総レコード数：0
            l_operatorAccountListResponse.totalRecords = l_intTotalRecords + "";
            // 表示ページ番号：0
            l_operatorAccountListResponse.pageIndex = l_intPageIndex + "";
        }

        log.exiting(STR_METHOD_NAME);
        return l_operatorAccountListResponse;
    }

    /**
     * (create条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）パラメタ.扱者IDを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "trader_id = ? "<BR>
     * <BR>
     * ３）パラメタ.顧客コード != nullの場合、<BR>
     * 　@口座ードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and account_code = ? "<BR>
     * <BR>
     * ４）パラメタ.顧客名（漢字） != nullの場合、<BR>
     * 　@名前（苗字）を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and family_name like ? "<BR>
     * <BR>
     * ５）パラメタ.顧客名顧客名（カナ） != nullの場合、<BR>
     * 　@名前（苗字1）を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and family_name_alt1 like ? "<BR>
     * <BR>
     * ６）作成した検索条件文字列を返却する。<BR>
     * <BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 入力された６桁の顧客コード<BR>
     * @@param l_strFamilyName - (顧客名（漢字）)<BR>
     * 入力された顧客名（漢字）<BR>
     * @@param l_strFamilyNameAlt1 - (顧客名（カナ）)<BR>
     * 入力された顧客名（カナ）<BR>
     * @@return String
     * @@roseuid 4695E61D0044
     */
    protected String createQueryString(
        String l_strAccountCode,
        String l_strFamilyName,
        String l_strFamilyNameAlt1)
    {

        final String STR_METHOD_NAME = " createQueryString(String, String, String)";
        log.entering(STR_METHOD_NAME);

        // １）検索条件文字列インスタンス(：String)を生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        // ２）パラメタ.扱者IDを検索条件文字列に追加する。
        // 　@検索条件文字列 += "trader_id = ? "
        l_sbQueryString.append("trader_id = ? ");

        // ３）パラメタ.顧客コード != nullの場合、
        // 　@口座ードを検索条件文字列に追加する。
        // 　@検索条件文字列 += "and account_code = ? "
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append("and account_code = ? ");
        }

        // ４）パラメタ.顧客名（漢字） != nullの場合、
        // 　@名前（苗字）を検索条件文字列に追加する。
        // 　@検索条件文字列 += "and family_name like ? "
        if (l_strFamilyName != null)
        {
            l_sbQueryString.append("and family_name like ? ");
        }

        // ５）パラメタ.顧客名顧客名（カナ） != nullの場合、
        // 　@名前（苗字1）を検索条件文字列に追加する。
        // 　@検索条件文字列 += "and family_name_alt1 like ? "
        if (l_strFamilyNameAlt1 != null)
        {
            l_sbQueryString.append("and family_name_alt1 like ? ");
        }

        // ６）作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create条件コンテナ)<BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメタ.扱者IDを生成したArrayListに追加する。<BR>
     * <BR>
     * ３）パラメタ.顧客コード != nullの場合、<BR>
     * 　@顧客コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ４）パラメタ.顧客名（漢字） != nullの場合、<BR>
     * 　@'%' + 顧客名（漢字） + '%'を生成したArrayListに追加する。<BR>
     * <BR>
     * ５）パラメタ.顧客名（カナ） != nullの場合、<BR>
     * 　@'%' + 顧客名（カナ） + '%'を生成したArrayListに追加する。<BR>
     * <BR>
     * ６）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_lngTradeId - (扱者ID)<BR>
     * セッションから取得した扱者ID<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 入力された６桁の顧客コード<BR>
     * @@param l_strFamilyName - (顧客名（漢字）)<BR>
     * 入力された顧客名（漢字）<BR>
     * @@param l_strFamilyNameAlt1 - (顧客名（カナ）)<BR>
     * 入力された顧客名（カナ）<BR>
     * @@return Object[]
     * @@roseuid 4695E89C0286
     */
    protected Object[] createQueryDataContainer(
        long l_lngTradeId,
        String l_strAccountCode,
        String l_strFamilyName,
        String l_strFamilyNameAlt1)
    {

        final String STR_METHOD_NAME = " createQueryDataContainer(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // １）ArrayListを生成する。
        List l_lisQuerys = new ArrayList();

        // ２）パラメタ.扱者IDを生成したArrayListに追加する。
        l_lisQuerys.add(new Long(l_lngTradeId));

        // ３）パラメタ.顧客コード != nullの場合、
        // 　@顧客コードを生成したArrayListに追加する。
        if (l_strAccountCode != null)
        {
            l_lisQuerys.add(l_strAccountCode);
        }

        // ４）パラメタ.顧客名（漢字） != nullの場合、
        // 　@'%' + 顧客名（漢字） + '%'を生成したArrayListに追加する。
        if (l_strFamilyName != null)
        {
            l_lisQuerys.add("%" + l_strFamilyName + "%");
        }

        // ５）パラメタ.顧客名（カナ） != nullの場合、
        // 　@'%' + 顧客名（カナ） + '%'を生成したArrayListに追加する。
        if (l_strFamilyNameAlt1 != null)
        {
            l_lisQuerys.add("%" + l_strFamilyNameAlt1 + "%");
        }

        // ６）生成したArrayList.toArray()の戻り値を返却する。
        Object[] l_results = new Object[l_lisQuerys.size()];
        l_lisQuerys.toArray(l_results);

        log.exiting(STR_METHOD_NAME);
        return l_results;
    }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を作成する。<BR>
     * <BR>
     * １）ソート条件文字列(：String)を作成する。<BR>
     * <BR>
     * ２）パラメタ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@２－１）パラメタ.ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「顧客コード」　@→　@扱者別顧客情報テーブル.口座コード<BR>
     * 　@　@・「名前」　@→　@扱者別顧客情報テーブル.名前（苗字1）<BR>
     * <BR>
     * 　@２－２）パラメタ.ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ３）作成したソート条件文字列を返却する。<BR>
     * <BR>
     * @@param l_sortKeys - 対象顧客ソートキーの配列
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4696E6E302A4
     */
    protected String createSortCond(WEB3TraderAccountInfosSortKey[] l_sortKeys)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createSortCond(WEB3TraderAccountInfosSortKey[])";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）ソート条件文字列(：String)を作成する。
        StringBuffer l_sbSortCond = new StringBuffer();

        // ２）パラメタ.ソートキーの要素数分以下の処理を繰り返す。
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            // ２－１）パラメタ.ソートキー.キー項目を対応する列物理名に変換し、
            // 作成したソート条件文字列に追加する。
            // 「顧客コード」　@→　@扱者別顧客情報テーブル.口座コード
            if (WEB3TraderAccountInfosSortKeyDef.ACCEPT_CODE.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("account_code");
            }
            // 「名前」　@→　@扱者別顧客情報テーブル.名前（苗字1）
            else if (WEB3TraderAccountInfosSortKeyDef.NAME_KANA.equals(
                l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append("family_name_alt1");
            }

            // ２－２）パラメタ.ソートキー.昇順／降順に対応する取得順序
            // (asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbSortCond.append(" ASC , ");
                }
                else
                {
                    l_sbSortCond.append(" ASC ");
                }
            }
            else
            {
                if (i != l_sortKeys.length - 1)
                {
                    l_sbSortCond.append(" DESC , ");
                }
                else
                {
                    l_sbSortCond.append(" DESC ");
                }
            }
        }

        // ３）作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }

}
@
