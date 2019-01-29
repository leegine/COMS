head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者PTS申込客一覧問合せサービスImpl(WEB3AdminInformPTSAccountListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.130
Revision History    : 2008/03/26 王志葵(中訊) 新規作成 モデルNo.133,ＤＢ更新仕様No.022
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
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
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.inform.message.WEB3AdminInformPTSAccountInfoUnit;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqCondition;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInqSortKey;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccountListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者PTS申込客一覧問合せサービスImpl)<BR>
 * 管理者PTS申込客一覧問合せサービス実装クラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListServiceImpl implements WEB3AdminInformPTSAccountListService
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListServiceImpl.class);

    /**
     * @@roseuid 47C6665002B9
     */
    public WEB3AdminInformPTSAccountListServiceImpl()
    {

    }

    /**
     * 管理者PTS申込客一覧問合せサービス処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B537740302
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

        if (l_request instanceof WEB3AdminInformPTSAccountListInquiryRequest)
        {
            //get検索画面
            l_response = this.getSearchScreen((WEB3AdminInformPTSAccountListInquiryRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformPTSAccountListResultRequest)
        {
            //get検索結果画面
            l_response = this.getSearchResultScreen((WEB3AdminInformPTSAccountListResultRequest)l_request);
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
     * (get検索画面)<BR>
     * 管理者PTS申込客一覧問合せ検索画面の取得を行う。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（PTS申込客一覧問合せ）get検索画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccountListInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B537740304
     */
    protected WEB3AdminInformPTSAccountListInquiryResponse getSearchScreen(
        WEB3AdminInformPTSAccountListInquiryRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminInformPTSAccountListInquiryRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFromログイン情報()
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            false);

        //レスポンスデータを生成する。
        WEB3AdminInformPTSAccountListInquiryResponse l_response =
            (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();

        //レスポンスデータプロパティに以下の通り値をセットする。
        //　@申込日時（自）：　@現在日時の前営業日
        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
        l_response.applyDateFrom =
            WEB3DateUtility.toDay(WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1));
        //　@申込日時（至）：　@現在日時の前日
        l_response.applyDateTo =
            WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索結果画面)<BR>
     * 管理者PTS申込客一覧問合せ検索結果画面の取得を行う。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（PTS申込客一覧問合せ）get検索結果画面」 参照。<BR>
     * ==================================================
     * 　@　@具体位置 :レコードを取得できない場合、例外の「BUSINESS_ERROR_00398<BR>
     * 　@　@　@　@　@　@　@（該当するデータが存在しません。）」をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00398<BR>
     * ==================================================
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccountListResultResponse
     * @@throws WEB3BaseException
     * @@roseuid 47B537740306
     */
    protected WEB3AdminInformPTSAccountListResultResponse getSearchResultScreen(
        WEB3AdminInformPTSAccountListResultRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchResultScreen(WEB3AdminInformPTSAccountListResultRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード： "A0501"
        //is更新：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.PTS_ACCOUNT_MANAGE,
            false);

        //create取得条件文字列(管理者・PTS申込客一覧問合せ検索条件)
        String l_strQueryString = this.createQueryString(l_request.searchCondition);

        //create取得条件データコンテナ(String, 管理者・PTS申込客一覧問合せ検索条件)
        //証券会社コード： 管理者.get証券会社コード()
        //検索条件： リクエストデータ.検索条件
        Object[] l_queryDataContainers =
            this.createQueryDataContainer(l_administrator.getInstitutionCode(), l_request.searchCondition);

        //createソート条件文字列(PTS申込客一覧問合せソートキー[])
        //ソートキー： リクエストデータ.ソートキー
        String l_strSortCondString = this.createSortCondString(l_request.sortKeys);

        //レスポンスデータを生成する。
        WEB3AdminInformPTSAccountListResultResponse l_response =
            (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
        try
        {
            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doFindAllQuery
            //rowType： 各種連絡Row.TYPE
            //where： create取得条件文字列()の戻り値
            //orderBy： createソート条件文字列()の戻り値
            //condition： null
            //リスト： create取得条件データコンテナ()の戻り値
            //ページサイズ： リクエストデータ.ページ内表示行数
            //ページ番号： リクエストデータ.要求ページ番号 - 1
            ListPage l_lisVariousInformRows =
                l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_strQueryString,
                    l_strSortCondString,
                    null,
                    l_queryDataContainers,
                    Integer.parseInt(l_request.pageSize),
                    Integer.parseInt(l_request.pageIndex) - 1);

            //レコードを取得できない場合、
            //例外の「BUSINESS_ERROR_00398（該当するデータが存在しません。）」をthrowする。
            if (l_lisVariousInformRows.isEmpty())
            {
                log.debug("該当するデータが存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当するデータが存在しません。");
            }

            WEB3PageIndexInfo l_lisViewPageIndexInfo =
                new WEB3PageIndexInfo(
                    l_lisVariousInformRows,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize));

            int l_intVariousInformRowCnt = l_lisVariousInformRows.size();
            List l_lisAdminInformPTSAccountInfoUnits = new ArrayList();
            for (int i = 0; i < l_intVariousInformRowCnt; i++)
            {
                VariousInformRow l_variousInformRow = (VariousInformRow)l_lisVariousInformRows.get(i);
                WEB3AdminInformPTSAccountInfoUnit l_adminInformPTSAccountInfoUnit =
                    new WEB3AdminInformPTSAccountInfoUnit();

                //部店コード：　@各種連絡Params.部店コード
                l_adminInformPTSAccountInfoUnit.branchCode = l_variousInformRow.getBranchCode();
                //顧客コード：　@各種連絡Params.顧客コードの頭6桁
                if (l_variousInformRow.getAccountCode() != null)
                {
                    l_adminInformPTSAccountInfoUnit.accountCode =
                        l_variousInformRow.getAccountCode().substring(0, 6);
                }
                //顧客名：　@各種連絡Params.顧客名
                l_adminInformPTSAccountInfoUnit.accountName = l_variousInformRow.getAccountName();
                //申込区分：　@各種連絡Params.区分２
                l_adminInformPTSAccountInfoUnit.ptsAccOpenDiv = l_variousInformRow.getExtDiv2();
                //状態：　@各種連絡Params.区分１
                l_adminInformPTSAccountInfoUnit.status = l_variousInformRow.getExtDiv1();
                //申込日時：　@各種連絡Params.作成日時
                l_adminInformPTSAccountInfoUnit.applyDate = l_variousInformRow.getCreatedTimestamp();
                //更新者：　@各種連絡Params.更新者コード
                l_adminInformPTSAccountInfoUnit.lastUpdater = l_variousInformRow.getLastUpdater();

                //add(arg0 : Object)
                l_lisAdminInformPTSAccountInfoUnits.add(l_adminInformPTSAccountInfoUnit);
            }

            //toArray
            WEB3AdminInformPTSAccountInfoUnit[] l_adminInformPTSAccountInfoUnits =
                new WEB3AdminInformPTSAccountInfoUnit[l_lisAdminInformPTSAccountInfoUnits.size()];
            l_lisAdminInformPTSAccountInfoUnits.toArray(l_adminInformPTSAccountInfoUnits);

            //表示ページ番号を取得する。
            int l_intPageIndex = l_lisViewPageIndexInfo.getPageIndex();

            //総ページ数を取得する。
            int l_intTotalPage = l_lisViewPageIndexInfo.getTotalPages();

            //総レコード数を取得する。
            int l_intTotalRecord = l_lisViewPageIndexInfo.getTotalRecords();

            //表示ページ番号：　@pageNumber()の戻り値
            l_response.pageIndex = String.valueOf(l_intPageIndex);
            //総ページ数：　@totalPages()の戻り値
            l_response.totalPages = String.valueOf(l_intTotalPage);
            //総レコード数：　@totalSize()の戻り値
            l_response.totalRecords = String.valueOf(l_intTotalRecord);
            //PTS申込客情報一覧：　@PTS申込客情報の配列
            l_response.ptsAccountInfoList = l_adminInformPTSAccountInfoUnits;
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
        return l_response;
    }

    /**
     * (create取得条件文字列)<BR>
     * 取得条件の文字列を生成する。<BR>
     * <BR>
     * １）　@空の文字列を生成する。<BR>
     * <BR>
     * ２）　@連絡種別、証券会社コード<BR>
     * <BR>
     * 　@　@"inform_div=? and institution_code=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ３）　@部店コード<BR>
     * <BR>
     * ３－１）　@引数.検索条件.部店コード.length() == 1 の場合<BR>
     * <BR>
     * 　@　@" and branch_code=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ３－２）　@引数.検索条件.部店コード.length() > 1 の場合<BR>
     * <BR>
     * 　@　@" and (branch_code=? or branch_code=? or ... or branch_code=?)" <BR>
     * 　@　@　@　@を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@※"branch_code=?"の数は、引数.検索条件.部店コードの要素数と同じになる。<BR>
     * <BR>
     * ４）　@顧客コード<BR>
     * <BR>
     * 　@　@引数.検索条件.顧客コード != null の場合<BR>
     * <BR>
     * 　@　@" and account_code like '?%'" を１）の文字列に追加する。<BR>
     * <BR>
     * ５）　@有効フラグ、申込区分<BR>
     * <BR>
     * 　@　@引数.検索条件.申込区分 != null の場合<BR>
     * <BR>
     * 　@　@" and ext_div1='1' and ext_div2 =?" を１）の文字列に追加する。<BR>
     * <BR>
     * ６）　@申込日時<BR>
     * <BR>
     * ６－１）　@引数.検索条件.申込日時（自） != null の場合<BR>
     * <BR>
     * 　@　@" and created_timestamp>=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ６－２）　@引数.検索条件.申込日時（至） != null の場合<BR>
     * <BR>
     * 　@　@" and created_timestamp<=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ７）　@生成された文字列を返却する。<BR>
     * @@param l_queryCondition - (検索条件)<BR>
     * リクエストデータ<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 47B9446F00D9
     */
    private String createQueryString(
        WEB3AdminInformPTSAccountListInqCondition l_queryCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminInformPTSAccountListInqCondition)";
        log.entering(STR_METHOD_NAME);

        //１）　@空の文字列を生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        //２）　@連絡種別、証券会社コード
        l_sbQueryString.append(" inform_div = ? and institution_code = ? ");

        //３）　@部店コード
        if (l_queryCondition.branchCode.length == 1)
        {
            //３－１）　@引数.検索条件.部店コード.length() == 1 の場合
            //" and branch_code=?" を１）の文字列に追加する。
            l_sbQueryString.append(" and branch_code = ? ");
        }
        else if (l_queryCondition.branchCode.length > 1)
        {
            //３－２）　@引数.検索条件.部店コード.length() > 1 の場合
            //" and (branch_code=? or branch_code=? or ... or branch_code=?)" を１）の文字列に追加する。
            //※"branch_code=?"の数は、引数.検索条件.部店コードの要素数と同じになる。
            l_sbQueryString.append(" and (branch_code = ? ");
            for (int i = 0; i < (l_queryCondition.branchCode.length - 1); i++)
            {
                l_sbQueryString.append(" or branch_code = ? ");
            }
            l_sbQueryString.append(")");
        }

        //４）　@顧客コード
        //引数.検索条件.顧客コード != null の場合
        //" and account_code like '?%'" を１）の文字列に追加する。
        if (l_queryCondition.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
        }

        //５）　@有効フラグ、申込区分
        //   引数.検索条件.申込区分 != null の場合
        //   " and ext_div1='1' and ext_div2 =?" を１）の文字列に追加する。
        if (l_queryCondition.ptsAccOpenDiv != null)
        {
            l_sbQueryString.append(" and ext_div1 = '1' and ext_div2 = ? ");
        }

        //６）　@申込日時
        //６－１）　@引数.検索条件.申込日時（自） != null の場合
        //   " and created_timestamp>=?" を１）の文字列に追加する。
        if (l_queryCondition.applyDateFrom != null)
        {
            l_sbQueryString.append(" and created_timestamp >= ? ");
        }

        //６－２）　@引数.検索条件.申込日時（至） != null の場合
        //  " and created_timestamp<=?" を１）の文字列に追加する。
        if (l_queryCondition.applyDateTo != null)
        {
            l_sbQueryString.append(" and created_timestamp <= ? ");
        }

        //７）　@生成された文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create取得条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １）　@空のArrayListを生成する。<BR>
     * <BR>
     * ２）　@連絡種別<BR>
     * <BR>
     * 　@　@引数.検索条件.連絡種別 を１）のListに追加する。<BR>
     * <BR>
     * ３）　@証券会社コード<BR>
     * <BR>
     * 　@　@引数.証券会社コード を１）のListに追加する。<BR>
     * <BR>
     * ４）　@部店コード<BR>
     * <BR>
     * 　@　@引数.検索条件.部店コードの各要素 を１）のListに追加する。<BR>
     * <BR>
     * ５）　@顧客コード<BR>
     * <BR>
     * 　@　@引数.検索条件.顧客コード != nullの場合<BR>
     * <BR>
     * 　@　@引数.検索条件.顧客コード を１）のListに追加する。<BR>
     * <BR>
     * ６）　@申込区分<BR>
     * <BR>
     * 　@　@引数.検索条件.申込区分 != nullの場合<BR>
     * <BR>
     * 　@　@引数.検索条件.申込区分 を１）のListに追加する。<BR>
     * <BR>
     * ８）　@申込日時<BR>
     * <BR>
     * ８－１）　@<BR>
     * 　@　@引数.検索条件.申込日時（自） != nullの場合<BR>
     * <BR>
     * 　@　@引数.検索条件.申込日時（自） を１）のListに追加する。<BR>
     * <BR>
     * ８－２）　@<BR>
     * 　@　@引数.検索条件.申込日時（至） != nullの場合<BR>
     * <BR>
     * 　@　@引数.検索条件.申込日時（至） を１）のListに追加する。<BR>
     * <BR>
     * ９）　@生成されたListから配列を取得し、返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_queryCondition - (検索条件)<BR>
     * リクエストデータ<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 47B9446F00E9
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformPTSAccountListInqCondition l_queryCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, WEB3AdminInformPTSAccountListInqCondition)";
        log.entering(STR_METHOD_NAME);

        //１）　@空のArrayListを生成する。
        List l_lisQueryDataContainers = new ArrayList();

        //２）　@連絡種別
        //   引数.検索条件.連絡種別 を１）のListに追加する。
        l_lisQueryDataContainers.add(l_queryCondition.informType);

        //３）　@証券会社コード
        //  引数.証券会社コード を１）のListに追加する。
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        //４）　@部店コード
        //   引数.検索条件.部店コードの各要素 を１）のListに追加する。
        for (int i = 0; i < l_queryCondition.branchCode.length; i++)
        {
            l_lisQueryDataContainers.add(l_queryCondition.branchCode[i]);
        }

        //５）　@顧客コード
        //   引数.検索条件.顧客コード != nullの場合
        //  引数.検索条件.顧客コード を１）のListに追加する。
        if (l_queryCondition.accountCode != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.accountCode);
        }

        //６）　@申込区分
        //   引数.検索条件.申込区分 != nullの場合
        //   引数.検索条件.申込区分 を１）のListに追加する。
        if (l_queryCondition.ptsAccOpenDiv != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.ptsAccOpenDiv);
        }

        //８）　@申込日時
        //８－１）   引数.検索条件.申込日時（自） != nullの場合
        //  引数.検索条件.申込日時（自） を１）のListに追加する。
        if (l_queryCondition.applyDateFrom != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.applyDateFrom);
        }

        //８－２）   引数.検索条件.申込日時（至） != nullの場合
        //  引数.検索条件.申込日時（至） を１）のListに追加する。
        if (l_queryCondition.applyDateTo != null)
        {
            l_lisQueryDataContainers.add(l_queryCondition.applyDateTo);
        }

        //９）　@生成されたListから配列を取得し、返却する。
        Object[] l_queryDataContainers = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_queryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainers;
    }

    /**
     * (createソート条件文字列)<BR>
     * 取得データのソート条件の文字列を生成する。<BR>
     * <BR>
     * １）　@空の文字列を生成する。<BR>
     * <BR>
     * ２）　@ソートキー配列の各要素毎に以下の処理を行う。（Loop処理）<BR>
     * <BR>
     * ２－１）　@キー項目 == ”部店コード” の場合<BR>
     * <BR>
     * 　@　@・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     * 　@　@　@　@"branch_code"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     * 　@　@　@　@"branch_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－２）　@キー項目 == ”顧客コード” の場合<BR>
     * <BR>
     * 　@　@・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     * 　@　@　@　@"account_code"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     * 　@　@　@　@"account_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－３）　@キー項目 == ”申込日時” の場合<BR>
     * <BR>
     * 　@　@・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     * 　@　@　@　@"created_timestamp"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     * 　@　@　@　@"created_timestamp desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－４）　@該当の要素が配列内の最後の要素ではない場合<BR>
     * <BR>
     * 　@　@", "を１）の文字列に追加する。<BR>
     * <BR>
     * ３）　@生成された文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 47B9446F00EC
     */
    private String createSortCondString(
        WEB3AdminInformPTSAccountListInqSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCondString(WEB3AdminInformPTSAccountListInqSortKey[])";
        log.entering(STR_METHOD_NAME);

        //１）　@空の文字列を生成する。
        StringBuffer l_sbSortCondString = new StringBuffer();

        int l_intAddCnt = 0;
        //２）　@ソートキー配列の各要素毎に以下の処理を行う。（Loop処理）
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            if (WEB3InformKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //２－１）　@キー項目 == ”部店コード” の場合
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    //・昇順/降順 == "A"（昇順） の場合
                    l_sbSortCondString.append(" branch_code, ");
                }
                else
                {
                    //・昇順/降順 == "D"（降順） の場合
                    l_sbSortCondString.append(" branch_code desc, ");
                }

                l_intAddCnt = l_intAddCnt + 1;
            }
            else if (WEB3InformKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //２－２）　@キー項目 == ”顧客コード” の場合
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    //・昇順/降順 == "A"（昇順） の場合
                    l_sbSortCondString.append(" account_code, ");
                }
                else
                {
                    //・昇順/降順 == "D"（降順） の場合
                    l_sbSortCondString.append(" account_code desc, ");
                }

                l_intAddCnt = l_intAddCnt + 1;
            }
            else if (WEB3InformKeyItemDef.APPLY_DATE.equals(l_sortKeys[i].keyItem))
            {
                //２－３）　@キー項目 == ”申込日時” の場合
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    //・昇順/降順 == "A"（昇順） の場合
                    l_sbSortCondString.append(" created_timestamp, ");
                }
                else
                {
                    //・昇順/降順 == "D"（降順） の場合
                    l_sbSortCondString.append(" created_timestamp desc, ");
                }

                l_intAddCnt = l_intAddCnt + 1;
            }
        }

        String l_strSortCondString = l_sbSortCondString.toString();
        if (l_intAddCnt > 0)
        {
            l_strSortCondString = l_strSortCondString.substring(0, (l_strSortCondString.length() - 2));
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortCondString;
    }
}
@
