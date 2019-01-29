head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先一覧サービス実装クラス(WEB3AdminInformProfDistSellTransSrcListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.046
Revision History    : 2007/06/12 謝旋(中訊) 仕様変更 モデルNo.080
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.DirectDebitRow;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.inform.define.WEB3InformProductFirstDef;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcCondition;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcSortKey;
import webbroker3.inform.message.WEB3AdminInformProfDistTransferInfo;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistSellTransSrcListService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (利金・分配金・売却代金振込先一覧サービス実装クラス)<BR>
 * 利金・分配金・売却代金振込先一覧サービス実装クラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcListServiceImpl
    implements WEB3AdminInformProfDistSellTransSrcListService
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListServiceImpl.class);

    /**
     * @@roseuid 4655937403D6
     */
    public WEB3AdminInformProfDistSellTransSrcListServiceImpl()
    {

    }

    /**
     * 利金・分配金・売却代金振込先一覧サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     * ・get入力画面()<BR>
     * ・get一覧画面()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 461F4E530134
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminInformProfDistSellTransSrcInpRequest)
        {
            //入力画面
            l_response = getInputScreen((WEB3AdminInformProfDistSellTransSrcInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformProfDistSellTransSrcListRequest)
        {
            //一覧画面
            l_response = getListScreen((WEB3AdminInformProfDistSellTransSrcListRequest)l_request);
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
     * 「（利金・分配金・売却代金振込先一覧）get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistSellTransSrcInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 461F4ED60214
     */
    protected WEB3AdminInformProfDistSellTransSrcInpResponse getInputScreen(
        WEB3AdminInformProfDistSellTransSrcInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminInformProfDistSellTransSrcInpRequest)";
        log.entering(STR_METHOD_NAME);

        //入力画面の取得を行う。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //権限チェックを行う。
        //[引数]
        //  機@能カテゴリコード： A0105：顧客登録管理メニュー
        //  is更新： false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, false);

        //レスポンスデータを生成する。
        WEB3AdminInformProfDistSellTransSrcInpResponse l_response =
            (WEB3AdminInformProfDistSellTransSrcInpResponse)l_request.createResponse();

        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();

        //レスポンスデータ.登録日（自） = 現在日時の前営業日
        l_response.registDateFrom = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //レスポンスデータ.登録日（至） = 現在日時の前日
        l_response.registDateTo = WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面)<BR>
     * 一覧画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（利金・分配金・売却代金振込先一覧）get一覧画面」参照<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:　@管理者お客様情報（内部者情報変更）validate変更<BR>
     * 　@具体位置　@　@　@:　@1.5.2  全部店を取り扱う権限がない場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@class　@　@　@　@:　@WEB3BusinessLayerException<BR>
     * 　@tag　@　@　@　@　@:　@BUSINESS_ERROR_01380<BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:　@管理者お客様情報（内部者情報変更）validate変更<BR>
     * 　@具体位置　@　@　@:　@1.11  レコードを取得できない場合、例外の<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@「該当なしエラー」をthrowする。<BR>
     * 　@class　@　@　@　@:　@WEB3BusinessLayerException<BR>
     * 　@tag　@　@　@　@　@:　@BUSINESS_ERROR_00398<BR>
     * ===============================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminInformProfDistSellTransSrcListResponse
     * @@throws WEB3BaseException
     * @@roseuid 461F4EE1027D
     */
    protected WEB3AdminInformProfDistSellTransSrcListResponse getListScreen(
        WEB3AdminInformProfDistSellTransSrcListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminInformProfDistSellTransSrcListRequest)";
        log.entering(STR_METHOD_NAME);

        //整合性チェックを行う。
        l_request.validate();

        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //権限チェックを行う。
        //[引数]
        //  機@能カテゴリコード： A0105：顧客登録管理メニュー
        //  is更新： false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_REGISTER_MANAGE_MENU, false);

        //リクエストデータ.検索条件.部店コードを取得する。
        String l_branchCode = l_request.searchCondition.branchCode;
        //部店コードがnullでない場合
        if (l_branchCode != null)
        {
            //当該管理者が、指定の部店を取り扱えるかをチェックする。
            l_administrator.validateBranchPermission(l_branchCode);
        }
        //リクエストデータ.検索条件.部店コードがnullの場合
        else
        {
            //該当管理者に全部店データを扱う権限があるかを判定する。
            //全部店を取り扱う権限がない場合、例外をthrowする。
            if (!l_administrator.isAllBranchsPermission())
            {
                log.debug("全部店許可の管理者でない場合は、操作不可。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //create検索条件文字列(利金・分配金・売却代金振込先検索条件)
        //[引数]
        //  検索条件： リクエストデータ.検索条件
        String l_strQueryString = createQueryString(l_request.searchCondition);

        //取得条件にセットする値の配列を生成する。
        //[引数]
        //  証券会社コード： 管理者.get証券会社コード()
        //  検索条件： リクエストデータ.検索条件
        Object[] l_queryContainer = createQueryDataContainer(
            l_administrator.getInstitutionCode(),
            l_request.searchCondition);

        //ソート条件の文字列を生成する。
        //[引数]
        //  ソートキー： リクエストデータ.ソートキー
        String l_strSortCond = createSortCondString(l_request.sortKeys);

        //リクエストデータ.ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);

        //リクエストデータ.要求ページ番号 - 1
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex) - 1;
        ListPage l_lisRecords = null;
        try
        {
            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //自動振替登録マスタテーブルからレコードを取得する。
            //[引数]
            //  rowType： 自動振替登録マスタRow.TYPE
            //  where： create取得条件文字列()の戻り値
            //  orderBy： createソート条件文字列()の戻り値
            //  condition： null
            //  リスト： create取得条件データコンテナ()の戻り値
            //  ページサイズ： リクエストデータ.ページ内表示行数
            //  ページ番号： リクエストデータ.要求ページ番号 - 1
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                DirectDebitRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer,
                l_intPageSize,
                l_intPageIndex);

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

        //レコードを取得できない場合、例外の「該当なしエラー」をthrowする。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.debug("該当するデータが存在しません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisRecords,
                Integer.parseInt(l_request.pageIndex), 
                l_intPageSize);

        //空のArrayListを生成する。
        List l_lisInfoUnit = new ArrayList();

        //取得したレコード分ループ
        int l_lisRecordsSize = l_lisRecords.size();
        for (int i = 0; i < l_lisRecordsSize; i++)
        {
            //振込先情報を生成する。
            //[引数]
            //  自動振替登録マスタRow： 自動動振替登録マスタRow
            //リストに振込先情報を追加する。
            //[引数]
            //  arg0： create振込先情報()の戻り値
            l_lisInfoUnit.add(createTransferInfo((DirectDebitRow)l_lisRecords.get(i)));
        }

        //配列を取得する。
        WEB3AdminInformProfDistTransferInfo[] l_informInfoUnits =
            new WEB3AdminInformProfDistTransferInfo[l_lisRecordsSize];
        l_lisInfoUnit.toArray(l_informInfoUnits);

        //レスポンスデータを生成する。
        WEB3AdminInformProfDistSellTransSrcListResponse l_response =
            (WEB3AdminInformProfDistSellTransSrcListResponse)l_request.createResponse();

        //レスポンスデータ.振込先情報 = 振込先情報の配列
        l_response.transferInfo = l_informInfoUnits;

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
     * (create検索条件文字列)<BR>
     * 取得条件の文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 　@　@"institution_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * 　@３－１）引数.検索条件.部店コードがnullでない場合<BR>
     * <BR>
     * 　@　@" and branch_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ４）扱者コード<BR>
     * <BR>
     * 　@４－１）引数.検索条件.扱者コードがnullでない場合<BR>
     * <BR>
     * 　@　@" and trader_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ５）顧客コード<BR>
     * <BR>
     * 　@５－１）引数.検索条件.顧客コードがnullでない場合<BR>
     * <BR>
     * 　@　@" and account_code like '?%'"を１）の文字列に追加する。<BR>
     * <BR>
     * ６）指定区分<BR>
     * <BR>
     * 　@６－１）引数.検索条件.指定区分がnullでない場合<BR>
     * <BR>
     * 　@　@" and designate_div=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ７）商品<BR>
     * <BR>
     * 　@７－１）引数.検索条件.商品がnullでない場合<BR>
     * <BR>
     * 　@　@" and comodity like '?%'"を１）の文字列に追加する。<BR>
     * <BR>
     * ８）振替区分<BR>
     * <BR>
     * 　@８－１）引数.検索条件.振替区分がnullでない場合<BR>
     * <BR>
     * 　@　@" and transfer_div=?"を１）の文字列に追加する。<BR>
     * <BR>
     * ９）銘柄コード<BR>
     * <BR>
     * 　@９－１）引数.検索条件.銘柄コードがnullでない場合<BR>
     * <BR>
     * 　@　@" and fund_code=?"を１）の文字列に追加する。<BR>
     * <BR>
     * １０）登録日（自）<BR>
     * <BR>
     * 　@１０－１）引数.検索条件.登録日（自）がnullでない場合<BR>
     * <BR>
     * 　@　@" and sonar_created_timestamp>=?"を１）の文字列に追加する。<BR>
     * <BR>
     * １１）登録日（至）<BR>
     * <BR>
     * 　@１１－１）引数.検索条件.登録日（至）がnullでない場合<BR>
     * <BR>
     * 　@　@" and sonar_created_timestamp<?"を１）の文字列に追加する。<BR>
     * <BR>
     * １２）生成された文字列を返却する。<BR>
     * @@param l_profDistSellTransSrcCondition - (検索条件)<BR>
     * 検索条件
     * @@return String
     * @@roseuid 461F4F24032B
     */
    private String createQueryString(
        WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition)
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminInformProfDistSellTransSrcCondition)";
        log.entering(STR_METHOD_NAME);

        //１）空の文字列を生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        //２）証券会社コード
        //"institution_code=?"を１）の文字列に追加する。
        l_sbQueryString.append("institution_code=?");

        //３）部店コード
        //３－１）引数.検索条件.部店コードがnullでない場合
        //" and branch_code=?"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.branchCode != null)
        {
            l_sbQueryString.append(" and branch_code=?");
        }

        //４）扱者コード
        //４－１）引数.検索条件.扱者コードがnullでない場合
        //" and trader_code=?"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.traderCode != null)
        {
            l_sbQueryString.append(" and trader_code=?");
        }

        //５）顧客コード
        //５－１）引数.検索条件.顧客コードがnullでない場合
        //" and account_code like '?%'"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%'");
        }

        //６）指定区分
        //６－１）引数.検索条件.指定区分がnullでない場合
        //" and designate_div=?"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.specifyDiv != null)
        {
            l_sbQueryString.append(" and designate_div=?");
        }

        //７）商品
        //７－１）引数.検索条件.商品がnullでない場合
        //" and comodity like '?%'"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.product != null)
        {
            l_sbQueryString.append(" and comodity like ? || '%'");
        }

        //８）振替区分
        //８－１）引数.検索条件.振替区分がnullでない場合
        //" and transfer_div=?"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.transferDiv != null)
        {
            l_sbQueryString.append(" and transfer_div=?");
        }

        //９）銘柄コード
        //９－１）引数.検索条件.銘柄コードがnullでない場合
        //" and fund_code=?"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.productCode != null)
        {
            l_sbQueryString.append(" and fund_code=?");
        }

        //１０）登録日（自）
        //１０－１）引数.検索条件.登録日（自）がnullでない場合
        //" and sonar_created_timestamp>=?"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.registDateFrom != null)
        {
            l_sbQueryString.append(" and sonar_created_timestamp>=?");
        }

        //１１）登録日（至）
        //１１－１）引数.検索条件.登録日（至）がnullでない場合
        //" and sonar_created_timestamp<=?"を１）の文字列に追加する。
        if (l_profDistSellTransSrcCondition.registDateTo != null)
        {
            l_sbQueryString.append(" and sonar_created_timestamp<?");
        }

        //１２）生成された文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 　@引数.証券会社コードを１）のListに追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * 　@３－１）引数.検索条件.部店コードがnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.部店コードを１）のListに追加する。<BR>
     * <BR>
     * ４）扱者コード<BR>
     * <BR>
     * 　@４－１）引数.検索条件.扱者コードがnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.扱者コードを１）のListに追加する。<BR>
     * <BR>
     * ５）顧客コード<BR>
     * <BR>
     * 　@５－１）引数.検索条件.顧客コードがnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.顧客コードを１）のListに追加する。<BR>
     * <BR>
     * ６）指定区分<BR>
     * <BR>
     * 　@６－１）引数.検索条件.指定区分がnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.指定区分を１）のListに追加する。<BR>
     * <BR>
     * ７）商品<BR>
     * <BR>
     * 　@７－１）引数.検索条件.商品がnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.商品の頭1桁を１）のListに追加する。<BR>
     * <BR>
     * ８）振替区分<BR>
     * <BR>
     * 　@８－１）引数.検索条件.振替区分がnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.振替区分を１）のListに追加する。<BR>
     * <BR>
     * ９）銘柄コード<BR>
     * <BR>
     * 　@９－１）引数.検索条件.銘柄コードがnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.銘柄コードを１）のListに追加する。<BR>
     * <BR>
     * １０）登録日（自）<BR>
     * <BR>
     * 　@１０－１）引数.検索条件.登録日（自）がnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.登録日（自）を１）のListに追加する。<BR>
     * <BR>
     * １１）登録日（至）<BR>
     * <BR>
     * 　@１１－１）引数.検索条件.登録日（至）がnullでない場合<BR>
     * <BR>
     * 　@　@引数.検索条件.登録日（至）の翌日を１）のListに追加する。<BR>
     * <BR>
     * １２）生成されたListから配列を取得し、返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_profDistSellTransSrcCondition - (検索条件)<BR>
     * 検索条件
     * @@return Object[]
     * @@roseuid 461F4F27005F
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformProfDistSellTransSrcCondition l_profDistSellTransSrcCondition)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String, WEB3AdminInformProfDistSellTransSrcCondition)";
        log.entering(STR_METHOD_NAME);

        //１）空のArrayListを生成する。
        List l_lstQueryContainer = new ArrayList();

        //２）証券会社コード
        //引数.証券会社コードを１）のListに追加する。
        l_lstQueryContainer.add(l_strInstitutionCode);

        //３）部店コード
        //３－１）引数.検索条件.部店コードがnullでない場合
        //引数.検索条件.部店コードを１）のListに追加する。
        if (l_profDistSellTransSrcCondition.branchCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.branchCode);
        }

        //４）扱者コード
        //４－１）引数.検索条件.扱者コードがnullでない場合
        //引数.検索条件.扱者コードを１）のListに追加する。
        if (l_profDistSellTransSrcCondition.traderCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.traderCode);
        }

        //５）顧客コード
        //５－１）引数.検索条件.顧客コードがnullでない場合
        //引数.検索条件.顧客コードを１）のListに追加する。
        if (l_profDistSellTransSrcCondition.accountCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.accountCode);
        }

        //６）指定区分
        //６－１）引数.検索条件.指定区分がnullでない場合
        //引数.検索条件.指定区分を１）のListに追加する。
        if (l_profDistSellTransSrcCondition.specifyDiv != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.specifyDiv);
        }

        //７）商品
        //７－１）引数.検索条件.商品がnullでない場合
        //引数.検索条件.商品の頭1桁を１）のListに追加する。
        if (!WEB3StringTypeUtility.isEmpty(l_profDistSellTransSrcCondition.product))
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.product.substring(0, 1));
        }

        //８）振替区分
        //８－１）引数.検索条件.振替区分がnullでない場合
        //引数.検索条件.振替区分を１）のListに追加する。
        if (l_profDistSellTransSrcCondition.transferDiv != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.transferDiv);
        }

        //９）銘柄コード
        //９－１）引数.検索条件.銘柄コードがnullでない場合
        //引数.検索条件.銘柄コードを１）のListに追加する。
        if (l_profDistSellTransSrcCondition.productCode != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.productCode);
        }

        //１０）登録日（自）
        //１０－１）引数.検索条件.登録日（自）がnullでない場合
        //引数.検索条件.登録日（自）を１）のListに追加する。
        if (l_profDistSellTransSrcCondition.registDateFrom != null)
        {
            l_lstQueryContainer.add(l_profDistSellTransSrcCondition.registDateFrom);
        }

        //１１）登録日（至）
        //１１－１）引数.検索条件.登録日（至）がnullでない場合
        //引数.検索条件.登録日（至）の翌日を１）のListに追加する。
        if (l_profDistSellTransSrcCondition.registDateTo != null)
        {
            l_lstQueryContainer.add(
                WEB3DateUtility.addDay(l_profDistSellTransSrcCondition.registDateTo, 1));
        }

        //１２）生成されたListから配列を取得し、返却する。
        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainer;
    }

    /**
     * (createソート条件文字列)<BR>
     * ソート条件の文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）ソートキーの各要素分、以下の処理を繰り返す。<BR>
     * <BR>
     * 　@２－１）ソートキー.キー項目が"部店コード"の場合<BR>
     * <BR>
     * 　@　@２－１－１）ソートキー.昇順/降順がA：昇順の場合<BR>
     * <BR>
     * 　@　@　@"branch_code"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@２－１－２）ソートキー.昇順/降順がD：降順の場合<BR>
     * <BR>
     * 　@　@　@"branch_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@２－２）ソートキー.キー項目が"扱者コード"の場合<BR>
     * <BR>
     * 　@　@２－２－１）ソートキー.昇順/降順がA：昇順の場合<BR>
     * <BR>
     * 　@　@　@"trader_code"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@２－２－２）ソートキー.昇順/降順がD：降順の場合<BR>
     * <BR>
     * 　@　@　@"trader_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@２－３）ソートキー.キー項目が"顧客コード"の場合<BR>
     * <BR>
     * 　@　@２－３－１）ソートキー.昇順/降順がA：昇順の場合<BR>
     * <BR>
     * 　@　@　@"account_code"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@２－３－２）ソートキー.昇順/降順がD：降順の場合<BR>
     * <BR>
     * 　@　@　@"account_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@２－４）ソートキー.キー項目が"銘柄コード"の場合<BR>
     * <BR>
     * 　@　@２－４－１）ソートキー.昇順/降順がA：昇順の場合<BR>
     * <BR>
     * 　@　@　@"fund_code"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@２－４－２）ソートキー.昇順/降順がD：降順の場合<BR>
     * <BR>
     * 　@　@　@"fund_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@２－５）ソートキー.キー項目が"登録日"の場合<BR>
     * <BR>
     * 　@　@２－５－１）ソートキー.昇順/降順がA：昇順の場合<BR>
     * <BR>
     * 　@　@　@"sonar_created_timestamp"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@２－５－２）ソートキー.昇順/降順がD：降順の場合<BR>
     * <BR>
     * 　@　@　@"sonar_created_timestamp desc"を１）の文字列に追加する。<BR>
     * <BR>
     * 　@２－６）対象のソートキーが配列の最後の要素でない場合<BR>
     * <BR>
     * 　@　@", "を１）の文字列に追加する。<BR>
     * <BR>
     * ３）生成された文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキーの配列
     * @@return String
     * @@roseuid 461F4F290320
     */
    private String createSortCondString(WEB3AdminInformProfDistSellTransSrcSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortCondString(WEB3AdminInformProfDistSellTransSrcSortKey[])";
        log.entering(STR_METHOD_NAME);

        //１）空の文字列を生成する。
        StringBuffer l_sbSortCond = new StringBuffer();

        //２）ソートキーの各要素分、以下の処理を繰り返す。
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            String l_strSubCond;
            //２－１）ソートキー.キー項目が"部店コード"の場合
            if (WEB3InformKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "branch_code";
            }
            //２－２）ソートキー.キー項目が"扱者コード"の場合
            else if (WEB3InformKeyItemDef.TRADER_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "trader_code";
            }
            //２－３）ソートキー.キー項目が"顧客コード"の場合
            else if (WEB3InformKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "account_code";
            }
            //２－４）ソートキー.キー項目が"銘柄コード"の場合
            else if (WEB3InformKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "fund_code";
            }
            //２－５）ソートキー.キー項目が"登録日"の場合
            else if (WEB3InformKeyItemDef.REGIST_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strSubCond = "sonar_created_timestamp";
            }
            else
            {
                continue;
            }
            //２－６）対象のソートキーが配列の最後の要素でない場合
            //", "を１）の文字列に追加する。
            if (l_sbSortCond.length() != 0)
            {
                l_sbSortCond.append(", ");
            }
            l_sbSortCond.append(l_strSubCond);

            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append(" desc");
            }
        }

        //３）生成された文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }

    /**
     * (create振込先情報)<BR>
     * 振込先情報を生成する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（利金・分配金・売却代金振込先一覧）create振込先情報」参照<BR>
     * @@param l_directDebitRow - (自動振替登録マスタRow)<BR>
     * 自動振替登録マスタRow
     * @@return WEB3InformProfDistTransferInfo
     * @@throws WEB3BaseException
     * @@roseuid 4640298E029B
     */
    private WEB3AdminInformProfDistTransferInfo createTransferInfo(DirectDebitRow l_directDebitRow)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createTransferInfo(DirectDebitRow)";
        log.entering(STR_METHOD_NAME);

        //利金・分配金・売却代金振込先情報(自動振替登録マスタRow)
        //(利金・分配金・売却代金振込先情報::利金・分配金・売却代金振込先情報)
        WEB3AdminInformProfDistTransferInfo l_adminInformProfDistSellTransSrcInfo =
            new WEB3AdminInformProfDistTransferInfo(l_directDebitRow);

        //set顧客情報(String, String, String, 利金・分配金・売却代金振込先情報)
        //(利金・分配金・売却代金振込先一覧サービスImpl::set顧客情報)
        this.setAccountInfo(l_directDebitRow.getInstitutionCode(),
            l_adminInformProfDistSellTransSrcInfo.branchCode,
            l_adminInformProfDistSellTransSrcInfo.accountCode,
            l_adminInformProfDistSellTransSrcInfo);

        //set銘柄情報(String, String, String, 利金・分配金・売却代金振込先情報)
        //(利金・分配金・売却代金振込先一覧サービスImpl::set銘柄情報)
        this.setProductInfo(l_directDebitRow.getInstitutionCode(),
            l_adminInformProfDistSellTransSrcInfo.product,
            l_adminInformProfDistSellTransSrcInfo.productCode,
            l_adminInformProfDistSellTransSrcInfo);

        log.exiting(STR_METHOD_NAME);
        return l_adminInformProfDistSellTransSrcInfo;
    }

    /**
     * (set顧客情報)<BR>
     * 顧客情報を取得し、振込先情報に設定する。<BR>
     * <BR>
     * １）拡張アカウントマネージャ.get顧客()をコールし、<BR>
     * 　@　@　@　@顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@証券会社コード： 引数.証券会社コード<BR>
     * 　@部店コード： 引数.部店コード<BR>
     * 　@口座コード： 引数.口座コード<BR>
     * <BR>
     * 　@※顧客オブジェクトを取得できない場合、リターンする。<BR>
     * <BR>
     * ２）顧客.getDataSourceObject()をコールし、顧客Rowを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@なし<BR>
     * <BR>
     * ３）以下のとおりに、プロパティをセットする。<BR>
     * <BR>
     * 　@引数.振込先情報.顧客名（漢字） = 顧客Row.名前（苗字）<BR>
     * 　@引数.振込先情報.顧客名（カナ） = 顧客Row.名前（苗字１）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード
     * @@param l_transferInfo - (振込先情報)<BR>
     * 振込先情報
     * @@throws WEB3BaseException
     * @@roseuid 464A5BB90322
     */
    private void setAccountInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        WEB3AdminInformProfDistTransferInfo l_transferInfo)
    {
        String STR_METHOD_NAME = "setAccountInfo(String, String, String, WEB3AdminInformProfDistSellTransSrcInfo)";
        log.entering(STR_METHOD_NAME);

        //１）拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する。
        //　@[引数]
        //　@証券会社コード： 引数.証券会社コード
        //　@部店コード： 引数.部店コード
        //　@口座コード： 引数.口座コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //２）顧客.getDataSourceObject()をコールし、顧客Rowを取得する。
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //３）以下のとおりに、プロパティをセットする。
        //　@引数.振込先情報.顧客名（漢字） = 顧客Row.名前（苗字）
        //　@引数.振込先情報.顧客名（カナ） = 顧客Row.名前（苗字１）
        l_transferInfo.accountName = l_mainAcountRow.getFamilyName();
        l_transferInfo.accountNameKana = l_mainAcountRow.getFamilyNameAlt1();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set銘柄情報)<BR>
     * 銘柄情報を取得し、振込先情報に設定する。<BR>
     * <BR>
     * １）引数.銘柄コードがnullでない場合<BR>
     * <BR>
     * 　@１－１）拡張アカウントマネージャ.getInstitution()をコールし、証券会社オブジェクトを取得する。<BR> 
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@証券会社コード： 引数.証券会社コード<BR> 
     * <BR>
     * 　@１－２）this.is投信銘柄()をコールし、商品が投信銘柄か否かを判定する。<BR> 
     * <BR>
     * 　@　@[引数]<BR> 
     * 　@　@商品： 引数.商品
     * <BR>
     * 　@１－３）投信銘柄の場合（is投信銘柄()==true）<BR>
     * <BR>
     * 　@　@１－３－１）拡張投信銘柄マネージャ.get投信銘柄()をコールし、<BR>
     * 　@　@　@　@拡張投信銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@証券会社コード： 証券会社<BR>
     * 　@　@　@銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * 　@　@　@※拡張投信銘柄オブジェクトを取得できない場合、リターンする。<BR>
     * <BR>
     * 　@　@１－３－２）以下のとおりに、プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@引数.振込先情報.銘柄名 = 拡張投信銘柄.get銘柄名()<BR>
     * <BR>
     * 　@１－４）その他の場合<BR>
     * <BR>
     * 　@　@１－４－１）債券銘柄マネージャ.get債券銘柄()をコールし、<BR>
     * 　@　@　@　@債券銘柄オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@証券会社コード： 証券会社<BR>
     * 　@　@　@銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * 　@　@　@※拡張投信銘柄オブジェクトを取得できない場合、リターンする。<BR>
     * <BR>
     * 　@　@１－４－２）以下のとおりに、プロパティをセットする。<BR>
     * <BR>
     * 　@　@　@引数.振込先情報.銘柄名 = 債券銘柄.get銘柄名()<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strProduct - (商品)<BR>
     * 商品
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード
     * @@param l_transferInfo - (振込先情報)<BR>
     * 振込先情報
     * @@throws WEB3BaseException
     * @@roseuid 464A5BBC0082
     */
    private void setProductInfo(
        String l_strInstitutionCode,
        String l_strProduct,
        String l_strProductCode,
        WEB3AdminInformProfDistTransferInfo l_transferInfo) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " setProductInfo(String, String, String, WEB3AdminInformProfDistSellTransSrcInfo)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsMutualProduct = false;
        //１）引数.銘柄コードがnullでない場合
        if (l_strProductCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //１－１）拡張アカウントマネージャ.getInstitution()をコールし、証券会社オブジェクトを取得する。
            Institution l_institution = null;
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            try
            {
                l_institution = (Institution)l_accountManager.getInstitution(l_strInstitutionCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //１－２）this.is投信銘柄()をコールし、商品が投信銘柄か否かを判定する。
            l_blnIsMutualProduct = this.isMutualProduct(l_strProduct);

            //１－３）投信銘柄の場合（is投信銘柄()==true）
            if (l_blnIsMutualProduct)
            {
                //１－３－１）拡張投信銘柄マネージャ.get投信銘柄()をコールし、拡張投信銘柄オブジェクトを取得する。
                WEB3MutualFundProduct l_mutualFundProduct = null;

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
                WEB3MutualFundProductManager l_mutualFundProductManager =
                    (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
                try
                {
                    //拡張投信銘柄オブジェクトを取得
                    l_mutualFundProduct =
                        (WEB3MutualFundProduct)l_mutualFundProductManager.getMutualFundProduct(
                            l_institution,
                            l_strProductCode);

                    //１－３－２）以下のとおりに、プロパティをセットする。
                    l_transferInfo.productName = l_mutualFundProduct.getMutualProductName();
                }
                catch (NotFoundException l_ex)
                {
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
            else
            {
                //１－４）その他の場合
                //１－４－１）債券銘柄マネージャ.get債券銘柄()をコールし、債券銘柄オブジェクトを取得する。
                WEB3BondProduct l_bondProduct = null;

                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
                WEB3BondProductManager l_bondProductManager =
                    (WEB3BondProductManager)l_tradingModule.getProductManager();
                try
                {
                    //債券銘柄オブジェクトを取得する
                    l_bondProduct =
                        (WEB3BondProduct)l_bondProductManager.getBondProduct(l_institution, l_strProductCode);

                    //１－４－２）以下のとおりに、プロパティをセットする。
                    l_transferInfo.productName = l_bondProduct.getProductName();
                }
                catch (NotFoundException l_ex)
                {
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is投信銘柄)<BR>
     * 商品が投信銘柄か否かを判定する。<BR>
     * <BR>
     * true：投信銘柄の場合<BR>
     * false：その他の場合<BR>
     * <BR>
     * １）引数.商品がnullでない場合<BR>
     * <BR>
     * 　@１－１）引数.商品の頭1桁が2：投資信託、または、<BR>
     * 　@　@　@　@　@R：オープン株投コースの場合、trueを返却する。<BR>
     * <BR>
     * ２）falseを返却する。<BR>
     * @@param l_strProduct - (商品)<BR>
     * 商品
     * @@return boolean
     * @@roseuid 465270F70183
     */
    private boolean isMutualProduct(String l_strProduct)
    {
        String STR_METHOD_NAME = "isMutualProduct(String)";
        log.entering(STR_METHOD_NAME);

        //１）引数.商品がnullでない場合
        if (l_strProduct != null && l_strProduct.length() > 0)
        {
            //引数.商品の頭1桁を取得
            String l_strFont = l_strProduct.substring(0, 1);
            if (WEB3InformProductFirstDef.MUTUAL_FUND.equals(l_strFont) ||
                WEB3InformProductFirstDef.OPEN_KABUTOU_COURSE.equals(l_strFont))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
