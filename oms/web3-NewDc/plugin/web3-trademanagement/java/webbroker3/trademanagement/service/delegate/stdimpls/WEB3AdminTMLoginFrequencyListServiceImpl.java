head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginFrequencyListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IP別ログイン回数一覧サービスImpl(WEB3AdminTMLoginFrequencyListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 劉剣(中訊) 新規作成 モデル 005,007,008,009,011
Revision History : 2008/10/17 肖志偉 (中訊) 仕様変更 モデル018
*/

package webbroker3.trademanagement.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountReferenceUnit;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (IP別ログイン回数一覧サービスImpl)<BR>
 * 管理者IP別ログイン回数一覧実装クラス。<BR>
 *
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminTMLoginFrequencyListServiceImpl implements WEB3AdminTMLoginFrequencyListService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminTMLoginFrequencyListServiceImpl.class);

    /**
     * (IPアドレス別ログイン処理回数)<BR>
     * IPアドレス別毎のログイン処理回数を保持。<BR>
     * <BR>
     * key   ： ログイン処理回数<BR>
     * value ： IPアドレス<BR>
     * <BR>
     * ※値のセット方法@については「setIP別ログイン処理回数()」を参照。<BR>
     */
    private HashMap ipAddressLoginProcessingFrequency;

    /**
     * (ログイン処理回数一覧)<BR>
     * ログイン処理回数の一覧を保持。<BR>
     */
    private ArrayList loginProcessingFrequencyList;

    /**
     * (重複カウンター)<BR>
     * 重複カウンター。<BR>
     */
    private double duplicateCounter;

    /**
     * @@roseuid 48D75CD80127
     */
    public WEB3AdminTMLoginFrequencyListServiceImpl()
    {

    }

    /**
     * 管理者・IP別ログイン回数一覧を開始する。 <BR>
     * <BR>
     * リクエストデータの型により、 <BR>
     * 以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・IP別ログイン回数一覧検索入力リクエストの場合 <BR>
     * 　@this.get検索入力画面()をコールする。 <BR>
     * <BR>
     * ○管理者・IP別ログイン回数一覧検索結果リクエストの場合 <BR>
     * 　@this.get検索結果画面()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C5C5C002AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //リクエストデータの型により、
        //以下のメソッドを呼び分ける。
        WEB3GenResponse l_response = null;

        //○管理者・IP別ログイン回数一覧検索入力リクエストの場合
        //this.get検索入力画面()をコールする。
        if (l_request instanceof WEB3AdminTraderAdminLoginCountInputRequest)
        {
            l_response = this.getSearchInputScreen((WEB3AdminTraderAdminLoginCountInputRequest)l_request);
        }
        //○管理者・IP別ログイン回数一覧検索結果リクエストの場合
        //this.get検索結果画面()をコールする。
        else if (l_request instanceof WEB3AdminTraderAdminLoginCountListRequest)
        {
            l_response = this.getSearchResultScreen((WEB3AdminTraderAdminLoginCountListRequest)l_request);
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
     * (get検索入力画面)<BR>
     * IP別ログイン回数一覧の検索入力画面の表示を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者・IP別ログイン回数一覧)get検索入力」参照。 <BR>
     * @@param l_request - (リクエスト)<BR>
     * 管理者・IP別ログイン回数一覧入力リクエストクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginCountInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C0EFB201C1
     */
    protected WEB3AdminTraderAdminLoginCountInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginCountInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchInputScreen(WEB3AdminTraderAdminLoginCountInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報()
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： ”C1301”
        //is更新： false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST,
            false);

        //createResponse()
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminLoginCountInputResponse l_response =
            (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索結果画面)<BR>
     * IP別ログイン回数一覧の検索結果画面の表示を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者・IP別ログイン回数一覧)get検索結果」参照。 <BR>
     * @@param l_request - (リクエスト)<BR>
     * 管理者・IP別ログイン回数一覧確認リクエストクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginCountListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C0EEF000A1
     */
    protected WEB3AdminTraderAdminLoginCountListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginCountListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchResultScreen(WEB3AdminTraderAdminLoginCountListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        //当リクエストデータの整合性チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報()
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： ”C1301”
        //is更新： false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST,
            false);

        //get証券会社コード()
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get対象IPアドレス取得処理(String, String, String, String)
        //[get対象IPアドレス取得処理()にセットするパラメータ]
        //証券会社コード ： get証券会社コード() の戻り値
        //日付 ： リクエストデータ.日付
        //時間(自) ： リクエストデータ.時間(自)
        //時間(至) ： リクエストデータ.時間(至)
        HashSet l_hsObjectIPAddressProcesses =
            this.getObjectIPAddressProcess(
                l_strInstitutionCode,
                l_request.searchDate,
                l_request.startTime,
                l_request.endTime);

        //declarationフィールド変数(HashSet)
        //[declarationフィールド変数()にセットするパラメータ]
        //IPアドレス一覧 ： get対象IPアドレス取得処理() の戻り値
        this.declarationFieldVariable(l_hsObjectIPAddressProcesses);

        //createIP別ログイン処理回数(String, String, String, String, HashSet)
        //[createIP別ログイン処理回数()にセットするパラメータ]
        //証券会社コード ： get証券会社コード() の戻り値
        //日付 ： リクエストデータ.日付
        //時間(自) ： リクエストデータ.時間(自)
        //時間(至) ： リクエストデータ.時間(至)
        //IPアドレス一覧 ： get対象IPアドレス取得処理() の戻り値
        this.createIPLoginProcessingFrequency(
            l_strInstitutionCode,
            l_request.searchDate,
            l_request.startTime,
            l_request.endTime,
            l_hsObjectIPAddressProcesses);

        //getIP別ログイン処理回数()
        //IPアドレスと対になるログイン処理回数をセットで保存するHashMapを取得する。
        HashMap l_hmIPLoginProcessingFrequencys = this.getIPLoginProcessingFrequency();

        //sortログイン処理回数(Set)
        //[sortログイン処理回数()にセットするパラメータ]
        //IPアドレス別ログイン処理回数コレクション ： getIP別ログイン処理の戻り値.keySet();
        double[] l_dblLoginProcessingFrequencys =
            this.sortLoginProcessingFrequency(l_hmIPLoginProcessingFrequencys.keySet());

        //getIP別ログイン回数明細一覧(double[], String)
        //[getIP別ログイン回数明細一覧()にセットするパラメータ]
        //昇順IP別ログイン処理回数 ： sortログイン処理回数()の戻り値
        //ランク ： リクエストデータ.ランク
        List l_lisIPLoginFrequencyUnitList =
            this.getIPLoginFrequencyUnitList(
                l_dblLoginProcessingFrequencys,
                l_request.rank);

        //WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //[WEB3PageIndexInfo（）に指定する引数]
        //l_list ： getIP別ログイン回数明細一覧()の戻り値
        //l_intRequestPageIndex ：　@リクエストデータ.表示ページ番号をint型に変換した値
        //l_intRequestPageSize ：　@リクエストデータ.ページ内表示行数をint型に変換した値
        WEB3PageIndexInfo l_lisViewPageIndexInfos = new WEB3PageIndexInfo(
            l_lisIPLoginFrequencyUnitList,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //getListReturned()
        //明細データ一覧のリストを取得する。
        List l_lisReturneds = l_lisViewPageIndexInfos.getListReturned();

        //getTotalRecords()
        //総レコード数を取得。
        int l_intTotalRecords = l_lisViewPageIndexInfos.getTotalRecords();

        //getPageIndex()
        //表示ページ番号を取得する。
        int l_intPageIndex = l_lisViewPageIndexInfos.getPageIndex();

        //getTotalPages()
        //ページ内表示行数を取得する。
        int l_intTotalPages = l_lisViewPageIndexInfos.getTotalPages();

        //createResponse()
        WEB3AdminTraderAdminLoginCountListResponse l_response =
            (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();

        //プロパティセット
        //レスポンスデータに以下の通りプロパティをセットする。
        //レスポンスデータ.表示ページ番号    ＝　@getPageIndex()の戻り値
        l_response.pageIndex = String.valueOf(l_intPageIndex);

        //レスポンスデータ.総ページ数   ＝　@getPageSize()の戻り値
        l_response.totalPages = String.valueOf(l_intTotalPages);

        //レスポンスデータ.総レコード数     ＝　@getTotalRecords()の戻り値
        l_response.totalRecords = String.valueOf(l_intTotalRecords);

        //レスポンスデータ.IP別ログイン回数情報    ＝　@getListReturned()の戻り値.toArray()
        WEB3AdminTraderAdminLoginCountReferenceUnit[] l_loginCountReferenceUnits =
            new WEB3AdminTraderAdminLoginCountReferenceUnit[l_lisReturneds.size()];
        l_lisReturneds.toArray(l_loginCountReferenceUnits);
        l_response.loginCountList = l_loginCountReferenceUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get対象IPアドレス取得処理)<BR>
     * 入力した日付、時間(自)、時間(至)の範囲でアクセス元のIPアドレスを取得。<BR>
     * <BR>
     * １)検索条件文字列の作成。<BR>
     * 　@　@String型変数を用意し、下記の文字を格納する。<BR>
     * 　@　@"institution_code = ? and <BR>
     * 　@　@　@login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and<BR>
     * 　@　@　@login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')<BR>
     * <BR>
     * ２)データコンテナの作成。<BR>
     * <BR>
     * 　@２－１) 空のArrayListを生成する。<BR>
     * 　@　@　@　@　@　@List lst = new ArrayList();<BR>
     * <BR>
     * 　@２－２) ２－１)のArrayListに以下の情報を追加する。<BR>
     * 　@　@　@　@　@　@lst.add((引数)証券会社コード);<BR>
     * 　@　@　@　@　@　@lst.add((引数)日付);<BR>
     * 　@　@　@　@　@　@lst.add((引数)時間(自));<BR>
     * 　@　@　@　@　@　@lst.add((引数)日付);<BR>
     * 　@　@　@　@　@　@lst.add((引数)時間(至));<BR>
     * <BR>
     * 　@２－３) Object型の配列を用意し、<BR>
     * 　@　@　@　@　@　@２－２)のArrayListに対して、toArray() の戻り値を格納する。<BR>
     * <BR>
     * ３)ログインテーブルデータマネージャ.getRowType()メソッドをコールする。<BR>
     * 　@[getRowType()にセットするパラメータ] <BR>
     * 　@　@日付       ： (引数)日付<BR>
     * 　@　@時間(自) ： (引数)時間(自) <BR>
     * <BR>
     * ４） Processors.getDefaultProcessor()メソッドをコールする。<BR>
     * <BR>
     * ５） QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@ rowType ：  ３) の戻り値<BR>
     * 　@　@ where     ： １)で作成したString<BR>
     * 　@　@ bindVars ： ２－３) のObject配列<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037）<BR>
     * <BR>
     * ６） IPアドレスの取得。<BR>
     * <BR>
     * 　@　@６－１) HashSetオブジェクトを生成する。<BR>
     * 　@　@　@　@　@　@　@HashSet set = new HashSet();<BR>
     * <BR>
     * 　@　@６－２) ５)で取得したListの要素数だけ、下記の処理をLoopする。<BR>
     * <BR>
     * 　@　@　@　@　@６－２－１) Listから、３） の戻り値に対応したRowを取り出す。<BR>
     * <BR>
     * 　@　@　@　@　@６－２－２) ６－１) のHashSetに以下の内容をセットする。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@set.add(ログイン(過去)履歴テーブルRow.getIPアドレス)<BR>
     * <BR>
     * 　@　@６－３) ６－１)のHashSetオブジェクトを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strSearchDate - (日付)<BR>
     * 日付。(yyyymmdd 形式)<BR>
     * @@param l_strStartTime - (時間(自))<BR>
     * 時間(自)。(hh24mi 形式)<BR>
     * @@param l_strEndTime - (時間(至))<BR>
     * 時間(至)。(hh24mi 形式)<BR>
     * @@return HashSet
     * @@throws WEB3BaseException
     * @@roseuid 48C4AF2600B8
     */
    private HashSet getObjectIPAddressProcess(
        String l_strInstitutionCode,
        String l_strSearchDate,
        String l_strStartTime,
        String l_strEndTime) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getObjectIPAddressProcess(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１)検索条件文字列の作成。
        //String型変数を用意し、下記の文字を格納する。
        //"institution_code = ? and
        //login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and
        //login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')
        String l_strWhere = "institution_code = ? "
            + "and login_timestamp >= to_date( ? || ? || '00', 'yyyymmddhh24miss') "
            + "and login_timestamp <= to_date( ? || ? || '59', 'yyyymmddhh24miss') ";

        //２)データコンテナの作成。
        //２－１)空のArrayListを生成する。
        List l_lisBindVars = new ArrayList();

        //２－２) ２－１)のArrayListに以下の情報を追加する。
        //lst.add((引数)証券会社コード);
        l_lisBindVars.add(l_strInstitutionCode);

        //lst.add((引数)日付);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((引数)時間(自));
        l_lisBindVars.add(l_strStartTime);

        //lst.add((引数)日付);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((引数)時間(至));
        l_lisBindVars.add(l_strEndTime);

        //２－３) Object型の配列を用意し、
        //２－２)のArrayListに対して、toArray() の戻り値を格納する。
        Object[] l_objBindVars = l_lisBindVars.toArray();

        //３)ログインテーブルデータマネージャ.getRowType()メソッドをコールする。
        //[getRowType()にセットするパラメータ]
        //日付 ： (引数)日付
        WEB3TradeManagementLoginTableDataManager l_loginTableDataManager =
            new WEB3TradeManagementLoginTableDataManager();
        RowType l_rowType = l_loginTableDataManager.getRowType(l_strSearchDate, l_strStartTime);

        List l_lisRows = null;
        try
        {
            //４）Processors.getDefaultProcessor()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //５）QueryProcessor.doFindAllQuery()メソッドをコールする。
            //[doFindAllQuery()にセットするパラメータ]
            //rowType ： ３) の戻り値
            //where ： １)で作成したString
            //bindVars ： ２－３) のObject配列
            l_lisRows = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_strWhere,
                l_objBindVars);
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

        //※検索結果が0件の場合、エラーを返却する。
        //エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037）
        if (l_lisRows.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //６）IPアドレスの取得。
        //６－１) HashSetオブジェクトを生成する。
        HashSet l_hsIpAddress = new HashSet();

        //６－２) ５)で取得したListの要素数だけ、下記の処理をLoopする。
        int l_intSize = l_lisRows.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //６－２－１) Listから、３） の戻り値に対応したRowを取り出す。
            //６－２－２) ６－１) のHashSetに以下の内容をセットする。
            Object l_objectRow = l_lisRows.get(i);
            if (l_objectRow instanceof LoginHistoryRow)
            {
                //set.add(ログイン履歴テーブルRow.getIPアドレス)
                l_hsIpAddress.add(((LoginHistoryRow)l_objectRow).getIpAddress());
            }
            else if (l_objectRow instanceof LoginHistoryPastRow)
            {
                //set.add(ログイン過去履歴テーブルRow.getIPアドレス)
                l_hsIpAddress.add(((LoginHistoryPastRow)l_objectRow).getIpAddress());
            }
        }

        log.exiting(STR_METHOD_NAME);

        //６－３) ６－１)のHashSetオブジェクトを返却する。
        return l_hsIpAddress;
    }

    /**
     * (declarationフィールド変数)<BR>
     * IP別ログイン回数一覧サービスImplのフィールド変数の定義。下記の処理を実施する。<BR>
     * <BR>
     * 　@※変数宣言は「IP別ログイン回数一覧サービスImpl」が<BR>
     * 　@　@　@始まった直後にしておく事。<BR>
     * <BR>
     * 　@１) IPアドレスと対になるログイン処理回数をセットで保存するHashMap<BR>
     *   　@　@HashMap IPアドレス別ログイン処理回数 = new HashMap();<BR>
     * <BR>
     * 　@２) ログイン処理回数は別途,ArrayListに格納する。<BR>
     *   　@　@List ログイン処理回数一覧 = new ArrayList( (引数)IPアドレス一覧.size() );<BR>
     * <BR>
     * 　@３) 重複回数カウント変数を定義。<BR>
     * 　@　@　@double   重複カウンター  = 0.000001;<BR>
     * 　@　@　@…setIP別ログイン処理回数()メソッドにて使用<BR>
     * @@param l_hsIPAddressList - (IPアドレス一覧)<BR>
     * IPアドレス一覧。<BR>
     * @@roseuid 48C4F05D0231
     */
    private void declarationFieldVariable(HashSet l_hsIPAddressList)
    {
        final String STR_METHOD_NAME = "declarationFieldVariable(HashSet)";
        log.entering(STR_METHOD_NAME);

        //　@１) IPアドレスと対になるログイン処理回数をセットで保存するHashMap
        //　@　@HashMap IPアドレス別ログイン処理回数 = new HashMap();
        ipAddressLoginProcessingFrequency = new HashMap();

        //　@２) ログイン処理回数は別途,ArrayListに格納する。
        //　@　@List ログイン処理回数一覧 = new ArrayList( (引数)IPアドレス一覧.size() );
        loginProcessingFrequencyList = new ArrayList(l_hsIPAddressList.size());

        //　@３) 重複回数カウント変数を定義。
        //　@　@　@double 重複カウンター = 0.000001;
        //　@　@　@…setIP別ログイン処理回数()メソッドにて使用
        duplicateCounter = 0.000001;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createIP別ログイン処理回数)<BR>
     * IPアドレス毎のログイン処理回数の作成、IPアドレス毎に紐付けを行う。<BR>
     * <BR>
     * １) 検索条件文字列の作成。<BR>
     *  　@　@String型変数を用意し、下記の文字を格納する。<BR>
     * 　@　@"institution_code = ? and <BR>
     * 　@　@　@ip_address = ? and <BR>
     * 　@　@　@login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and<BR>
     * 　@　@　@login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')<BR>
     * <BR>
     * ２)データコンテナの作成前準備。<BR>
     * <BR>
     * 　@２－１) 空のArrayListを生成する。<BR>
     * 　@　@　@　@　@　@List lst = new ArrayList();<BR>
     * <BR>
     * 　@２－２) ２－１)のArrayListに以下の情報を追加する。<BR>
     * 　@　@　@　@　@　@lst.add((引数)証券会社コード);<BR>
     * 　@　@　@　@　@　@lst.add(null);<BR>
     * 　@　@　@　@　@　@lst.add((引数)日付);<BR>
     * 　@　@　@　@　@　@lst.add((引数)時間(自));<BR>
     * 　@　@　@　@　@　@lst.add((引数)日付);<BR>
     * 　@　@　@　@　@　@lst.add((引数)時間(至));<BR>
     * <BR>
     * ３)ログインテーブルデータマネージャ.getRowType()メソッドをコールする。<BR>
     * 　@[getRowType()にセットするパラメータ] <BR>
     * 　@　@日付       ： (引数)日付<BR>
     * 　@　@時間(自) ： (引数)時間(自) <BR>
     * <BR>
     * ４） Processors.getDefaultProcessor()メソッドをコールする。<BR>
     * <BR>
     * ５) IPアドレス毎のログイン処理回数取得の前準備処理。<BR>
     * 　@　@Iterator it = (引数)IPアドレス一覧.Iterator();<BR>
     * <BR>
     * ６) ５)で生成したオブジェクトの要素が存在する限り、下記の処理を実施。 <BR>
     * 　@　@(IPアドレスと対になるログイン処理回数を取得するまで)<BR>
     * <BR>
     * 　@　@６－１) ２－１)で作成したArrayListに対して下記の処理を実施。<BR>
     * 　@　@　@　@　@　@　@また、String型変数を用意し、そちらにも同じ値(IPアドレス)を格納する。<BR>
     * 　@　@　@　@　@　@　@String s = it.next().toString();<BR>
     * 　@　@　@　@　@　@　@lst.set(1, s);<BR>
     * <BR>
     * 　@　@６－２) QueryProcessor.doGetCountQuery()メソッドをコールする。<BR>
     * 　@　@　@[doGetCountQuery()にセットするパラメータ]<BR>
     * 　@　@　@　@　@ rowType  ：  ３) の戻り値<BR>
     * 　@　@　@　@　@ where     ：  １)で作成したString<BR>
     * 　@　@ 　@　@　@bindVars ：  ６－１) 処理後のArrayListオブジェクト.toArray(); の戻り値<BR>
     * <BR>
     * 　@　@６－３) setIP別ログイン処理回数()メソッドをコールする。<BR>
     * 　@　@　@　@　@　@　@※ ６－２) の処理は、画面入力した指定時間内で<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@アクセスのあったIPアドレスのレコード総数をDBから取得。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@→この値が「ログイン処理回数」となる。<BR>
     * <BR>
     * 　@　@　@[setIP別ログイン処理回数()にセットするパラメータ]<BR>
     * 　@　@　@　@　@ IPアドレス毎ログイン処理回数：　@６－２) の戻り値<BR>
     * 　@　@　@　@　@ IPアドレス：　@　@　@　@　@　@　@　@　@　@　@　@  ６－１) で定義したString<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strSearchDate - (日付)<BR>
     * 日付。(yyyymmdd 形式)<BR>
     * @@param l_strStartTime - (時間(自))<BR>
     * 時間(自)。(hh24mi 形式)<BR>
     * @@param l_strEndTime - (時間(至))<BR>
     * 時間(至)。(hh24mi 形式)<BR>
     * @@param l_hsIPAddressList - (IPアドレス一覧)<BR>
     * IPアドレス一覧。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 48C4EF680384
     */
    private void createIPLoginProcessingFrequency(
        String l_strInstitutionCode,
        String l_strSearchDate,
        String l_strStartTime,
        String l_strEndTime,
        HashSet l_hsIPAddressList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIPLoginProcessingFrequency(String, String, String, String, HashSet)";
        log.entering(STR_METHOD_NAME);

        //１) 検索条件文字列の作成。
        //String型変数を用意し、下記の文字を格納する。
        //"institution_code = ? and
        //ip_address = ? and
        //login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and
        //login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')
        String l_strWhere = "institution_code = ? and ip_address = ? "
            + "and login_timestamp >= to_date( ? || ? || '00', 'yyyymmddhh24miss') "
            + "and login_timestamp <= to_date( ? || ? || '59', 'yyyymmddhh24miss') ";

        //２)データコンテナの作成前準備。
        //２－１) 空のArrayListを生成する。
        List l_lisBindVars = new ArrayList();

        //２－２) ２－１)のArrayListに以下の情報を追加する。
        //lst.add((引数)証券会社コード);
        l_lisBindVars.add(l_strInstitutionCode);

        //lst.add(null);
        l_lisBindVars.add(null);

        //lst.add((引数)日付);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((引数)時間(自));
        l_lisBindVars.add(l_strStartTime);

        //lst.add((引数)日付);
        l_lisBindVars.add(l_strSearchDate);

        //lst.add((引数)時間(至));
        l_lisBindVars.add(l_strEndTime);

        //３)ログインテーブルデータマネージャ.getRowType()メソッドをコールする。
        //[getRowType()にセットするパラメータ]
        //日付 ： (引数)日付
        WEB3TradeManagementLoginTableDataManager l_loginTableDataManager =
            new WEB3TradeManagementLoginTableDataManager();
        RowType l_rowType = l_loginTableDataManager.getRowType(l_strSearchDate, l_strStartTime);

        int l_intCount = 0;
        try
        {
            //４）Processors.getDefaultProcessor()メソッドをコールする。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //５) IPアドレス毎のログイン処理回数取得の前準備処理。
            //Iterator it = (引数)IPアドレス一覧.Iterator();
            Iterator l_ipAddressIterator = l_hsIPAddressList.iterator();

            //６) ５)で生成したオブジェクトの要素が存在する限り、下記の処理を実施。
            //(IPアドレスと対になるログイン処理回数を取得するまで)
            while (l_ipAddressIterator.hasNext())
            {
                //６－１) ２－１)で作成したArrayListに対して下記の処理を実施。
                //また、String型変数を用意し、そちらにも同じ値(IPアドレス)を格納する。
                String l_strIPAddress = l_ipAddressIterator.next().toString();
                l_lisBindVars.set(1, l_strIPAddress);

                //６－２) QueryProcessor.doGetCountQuery()メソッドをコールする。
                //[doGetCountQuery()にセットするパラメータ]
                //rowType ： ３) の戻り値
                //where ： １)で作成したString
                //bindVars ： ６－１) 処理後のArrayListオブジェクト.toArray(); の戻り値
                l_intCount = l_queryProcessor.doGetCountQuery(
                    l_rowType,
                    l_strWhere,
                    l_lisBindVars.toArray());

                //６－３) setIP別ログイン処理回数()メソッドをコールする。
                //※ ６－２) の処理は、画面入力した指定時間内で
                //アクセスのあったIPアドレスのレコード総数をDBから取得。
                //→この値が「ログイン処理回数」となる。
                //[setIP別ログイン処理回数()にセットするパラメータ]
                //IPアドレス毎ログイン処理回数：　@６－２) の戻り値
                //IPアドレス：　@　@　@　@　@　@　@　@　@　@　@　@ ６－１) で定義したString
                this.setIPLoginProcessingFrequency(l_intCount, l_strIPAddress);
            }
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setIP別ログイン処理回数)<BR>
     * createログイン処理回数保持変数() で定義したHashMapに<BR>
     * 「ログイン処理回数」と「IPアドレス」が対になるようにセットする処理。<BR>
     * <BR>
     * １) 下記の内部変数を定義。<BR>
     * 　@　@//重複key保持変数(Double型)<BR>
     * 　@　@Double   dbl        = null;<BR>
     * 　@　@//重複key保持変数+重複カウンター(プリミティブ型double)<BR>
     * 　@　@double   dblkey    = 0.0;<BR>
     * 　@　@//重複フラグ(重複あり/重複なし：true/false)<BR>
     * 　@　@boolean  overlaps = false;<BR>
     * <BR>
     * ２) (引数)IPアドレス毎ログイン処理回数 をStringに変換しておく。<BR>
     * 　@　@String strCnt = new Integer(IPアドレス毎ログイン処理回数).toString();<BR>
     * <BR>
     * ３) ログイン処理回数重複チェック。<BR>
     * 　@　@HashMapクラスのkeyにあたる部分に重複が存在した場合に<BR>
     * 　@　@小数点を付与し、重複回避を行う処理。<BR>
     * <BR>
     * 　@３－１) declarationフィールド変数() で定義した<BR>
     * 　@　@　@　@　@　@「ログイン処理回数一覧」ArrayListのsizeについて<BR>
     * <BR>
     * 　@　@　@　@　@　@・ArrayList > 0 の場合, ３－１－１) を実施。<BR>
     * <BR>
     * 　@　@　@　@　@　@・上記以外の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@key  ： new Double( ２) で定義したString変数 )<BR>
     * 　@　@　@　@　@　@　@　@　@　@value： (引数)IPアドレス<BR>
     * 　@　@　@　@　@　@　@　@　@　@と、「IPアドレス別ログイン処理回数」HashMapにputする。<BR>
     * 　@　@　@　@　@　@　@ さらに、ログイン処理回数一覧.add( ２)で定義した変数 )　@を実施する。<BR>
     * <BR>
     * 　@　@３－１－１) 「ログイン処理回数一覧」ArrayListのsizeだけ<BR>
     * 　@　@　@　@　@　@　@　@　@　@下記処理をループする。<BR>
     * <BR>
     * 　@　@　@３－１－１－１) ログイン処理回数一覧の要素と ２) の変数を比較し<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@値が同じだった(重複した)場合、下記の処理を実施。<BR>
     * <BR>
     * 　@　@　@　@３－１－１－１－１) (引数)IPアドレス毎ログイン処理回数をDouble型に変換。<BR>
     * <BR>
     * 　@　@　@　@３－１－１－１－２) ３－１－１－１－１)で変換したDouble型変数に<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@declarationフィールド変数() メソッドで<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@定義した「重複カウンター」を足す。<BR>
     * <BR>
     * 　@　@　@　@３－１－１－１－３) 「IPアドレス別ログイン処理回数」HashMapに<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@key  ： new Double(３－１－１－１－２) の値)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@value： (引数)IPアドレス<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@をputする。<BR>
     * <BR>
     * 　@　@　@　@３－１－１－１－４) 「ログイン処理回数一覧」ArrayListに<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@new Double(３－１－１－１－２) の値).toString()<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@をaddする。<BR>
     * <BR>
     * 　@　@　@　@３－１－１－１－５) 重複カウンターのインクリメントを行う。<BR>
     * <BR>
     * 　@　@　@　@３－１－１－１－６) 重複フラグ = true(重複あり) とする。<BR>
     * <BR>
     * 　@　@　@　@３－１－１－１－７) ３－１－１)の処理を抜ける。<BR>
     * <BR>
     * <BR>
     * 　@　@３－１－２) ３－１－１) のループ処理終了後に重複フラグチェックする。<BR>
     * 　@　@　@　@　@　@　@　@　@false(重複なし)の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@key  ： new Double( ２) で定義したString変数 )<BR>
     * 　@　@　@　@　@　@　@　@　@　@value： (引数)IPアドレス<BR>
     * 　@　@　@　@　@　@　@　@　@と、「IPアドレス別ログイン処理回数」HashMapにputする。<BR>
     * 　@　@　@　@　@　@　@　@　@さらに、ログイン処理回数一覧.add( ２)で定義した変数 )　@を実施する。<BR>
     * <BR>
     * @@param l_intIPAddressEveryLoginProcessingFrequency - <BR>
     * (IPアドレス毎ログイン処理回数)<BR>
     * IPアドレス毎ログイン処理回数。<BR>
     * @@param l_strIPAddress - (IPアドレス)<BR>
     * IPアドレス。<BR>
     * @@roseuid 48C50A0A01CF
     */
    private void setIPLoginProcessingFrequency(
        int l_intIPAddressEveryLoginProcessingFrequency,
        String l_strIPAddress)
    {
        final String STR_METHOD_NAME = "setIPLoginProcessingFrequency(int, String)";
        log.entering(STR_METHOD_NAME);

        //１) 下記の内部変数を定義。
        //重複key保持変数(Double型)
        Double l_key = null;
        //重複key保持変数+重複カウンター(プリミティブ型double)
        double l_dblkey = 0.0;
        //重複フラグ(重複あり/重複なし：true/false)
        boolean l_blnIsOverlaps = false;
        //２) (引数)IPアドレス毎ログイン処理回数 をStringに変換しておく。
        //String strCnt = new Integer(IPアドレス毎ログイン処理回数).toString();
        String l_strCnt = new Integer(l_intIPAddressEveryLoginProcessingFrequency).toString();

        //３) ログイン処理回数重複チェック。
        //HashMapクラスのkeyにあたる部分に重複が存在した場合に
        //小数点を付与し、重複回避を行う処理。
        //３－１) declarationフィールド変数() で定義した
        //「ログイン処理回数一覧」ArrayListのsizeについて
        //・ArrayList > 0 の場合, ３－１－１) を実施。
        int l_intSize = loginProcessingFrequencyList.size();
        if (l_intSize > 0)
        {
            //３－１－１) 「ログイン処理回数一覧」ArrayListのsizeだけ下記処理をループする。
            for (int i = 0; i < l_intSize; i++)
            {
                //３－１－１－１) ログイン処理回数一覧の要素と ２) の変数を比較し
                //値が同じだった(重複した)場合、下記の処理を実施。
                if (l_strCnt.equals(loginProcessingFrequencyList.get(i)))
                {
                    //３－１－１－１－１) (引数)IPアドレス毎ログイン処理回数をDouble型に変換。
                    l_key = Double.valueOf(l_strCnt);
                    //３－１－１－１－２) ３－１－１－１－１)で変換したDouble型変数に
                    //declarationフィールド変数() メソッドで
                    //定義した「重複カウンター」を足す。
                    l_dblkey = l_key.doubleValue() + duplicateCounter;
                    //３－１－１－１－３) 「IPアドレス別ログイン処理回数」HashMapに
                    //key ： new Double(３－１－１－１－２) の値)
                    //value： (引数)IPアドレス をputする。
                    ipAddressLoginProcessingFrequency.put(new Double(l_dblkey), l_strIPAddress);
                    //３－１－１－１－３) 「IPアドレス別ログイン処理回数」HashMapに
                    //new Double(３－１－１－１－２) の値).toString()をaddする。
                    loginProcessingFrequencyList.add(new Double(l_dblkey).toString());
                    //３－１－１－１－５) 重複カウンターのインクリメントを行う。
                    duplicateCounter = new BigDecimal(
                        duplicateCounter + "").add(
                        new BigDecimal("0.000001")).doubleValue();
                    //３－１－１－１－６) 重複フラグ = true(重複あり) とする。
                    l_blnIsOverlaps = true;
                    //３－１－１－１－７) ３－１－１)の処理を抜ける。
                    break;
                }
            }

            //３－１－２) ３－１－１) のループ処理終了後に重複フラグチェックする。
            //false(重複なし)の場合
            //key ： new Double( ２) で定義したString変数 )
            //value： (引数)IPアドレス
            //と、「IPアドレス別ログイン処理回数」HashMapにputする。
            //さらに、ログイン処理回数一覧.add( ２)で定義した変数 )　@を実施する。
            if (!l_blnIsOverlaps)
            {
                ipAddressLoginProcessingFrequency.put(new Double(l_strCnt), l_strIPAddress);
                loginProcessingFrequencyList.add(l_strCnt);
            }
        }
        else
        {
            //・上記以外の場合、
            //key  ： new Double( ２) で定義したString変数 )
            //value： (引数)IPアドレス
            //と、「IPアドレス別ログイン処理回数」HashMapにputする。
            //さらに、ログイン処理回数一覧.add( ２)で定義した変数 )　@を実施する。
            ipAddressLoginProcessingFrequency.put(new Double(l_strCnt), l_strIPAddress);
            loginProcessingFrequencyList.add(l_strCnt);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getIP別ログイン処理回数)<BR>
     * declarationフィールド変数() で定義した<BR>
     * 「IPアドレス別ログイン処理回数」HashMapを返却する。<BR>
     * @@return HashMap
     * @@roseuid 48C6088D0329
     */
    private HashMap getIPLoginProcessingFrequency()
    {
        //declarationフィールド変数() で定義した
        //「IPアドレス別ログイン処理回数」HashMapを返却する。
        return this.ipAddressLoginProcessingFrequency;
    }

    /**
     * (sortログイン処理回数)<BR>
     * ログイン処理回数をソート(昇順)したオブジェクトを取得する。<BR>
     * <BR>
     * １) ローカル変数の定義<BR>
     * 　@　@・繰返し処理用<BR>
     * 　@　@Iterator ite = (引数)IPアドレス別ログイン処理回数コレクション.iterator();<BR>
     * <BR>
     * 　@　@・昇順ソートされたログイン処理回数を保持する変数<BR>
     * 　@　@double[] priAryDbl = <BR>
     * 　@　@　@new double[(引数)IPアドレス別ログイン処理回数コレクション.size()];<BR>
     * <BR>
     * 　@　@・ログイン処理回数を格納する変数<BR>
     * 　@　@Object   objValue  = null;<BR>
     * <BR>
     * 　@　@・カウンター変数<BR>
     * 　@　@int i = 0;<BR>
     * <BR>
     * ２) １)で定義したIteratorオブジェクトの中に要素が存在する間<BR>
     * 　@　@下記の処理をループする。<BR>
     * <BR>
     * 　@２－１) １)で定義したObject変数に<BR>
     * 　@　@　@　@　@　@Iteratorオブジェクトの中の要素を格納する<BR>
     * <BR>
     * 　@２－２) １)で定義したdouble型配列[k]に<BR>
     * 　@　@　@　@　@　@２－１) で定義した変数をdouble型に変換して格納する。<BR>
     * <BR>
     * 　@２－３) int型変数 iのインクリメントを行う(+1)<BR>
     * <BR>
     * ３) ソート処理(昇順)を実施する<BR>
     * 　@　@Arrays.sort(priAryDbl); <BR>
     * <BR>
     * ４) double型配列を返却する。<BR>
     * <BR>
     * @@param l_ipAddressLoginProcessingFrequencyCollection - <BR>
     * (IPアドレス別ログイン処理回数コレクション)<BR>
     * IPアドレス別ログイン処理回数コレクション。<BR>
     * @@return double[]
     * @@roseuid 48C60DCE0120
     */
    private double[] sortLoginProcessingFrequency(Set l_ipAddressLoginProcessingFrequencyCollection)
    {
        final String STR_METHOD_NAME = "sortLoginProcessingFrequency(Set)";
        log.entering(STR_METHOD_NAME);

        //１) ローカル変数の定義
        //・繰返し処理用
        Iterator l_ipFrequencyIterator = l_ipAddressLoginProcessingFrequencyCollection.iterator();

        //・昇順ソートされたログイン処理回数を保持する変数
        double[] l_dblPriAryDbls = new double[l_ipAddressLoginProcessingFrequencyCollection.size()];

        //・ログイン処理回数を格納する変数
        Object l_objValue = null;

        //・カウンター変数
        int i = 0;
        //２) １)で定義したIteratorオブジェクトの中に要素が存在する間
        //下記の処理をループする。
        while (l_ipFrequencyIterator.hasNext())
        {
            //２－１) １)で定義したObject変数に
            //Iteratorオブジェクトの中の要素を格納する
            l_objValue = l_ipFrequencyIterator.next();            

            //２－２) １)で定義したdouble型配列[k]に
            //２－１) で定義した変数をdouble型に変換して格納する。
            Double l_priAryDbl = new Double(l_objValue.toString());
            l_dblPriAryDbls[i] = l_priAryDbl.doubleValue();

            //２－３) int型変数 iのインクリメントを行う(+1)
            i++;
        }

        //３) ソート処理(昇順)を実施する
        Arrays.sort(l_dblPriAryDbls);

        log.exiting(STR_METHOD_NAME);

        //４) double型配列を返却する。
        return l_dblPriAryDbls;
    }

    /**
     * (getIP別ログイン回数明細一覧)<BR>
     * レスポンスデータとして渡すIP別ログイン回数の一覧を取得する処理。<BR>
     * <BR>
     * １) 下記の内部変数を定義<BR>
     * <BR>
     * 　@・順位                           (int型 初期値：1)<BR>
     * 　@・【ログイン処理回数(降順)】 (double型 初期値：0.0)<BR>
     * 　@・前回ログイン処理回数      (int型 初期値：0)<BR>
     * 　@・同着カウンター                (int型 初期値：0)<BR>
     * 　@・ログイン明細一覧リスト      (List ログイン明細一覧リスト = new ArrayList();)<BR>
     * <BR>
     * ２) (引数)昇順IP別ログイン処理回数.length 回だけ下記の処理をループ。<BR>
     * 　@　@繰返し回数を i (初期値：0) とし、 i, 順位 に+1加算しながらループする。<BR>
     * <BR>
     * 　@２－１) 【ログイン処理回数(降順)】に<BR>
     * 　@　@　@　@　@(引数)昇順IP別ログイン処理回数[( <BR>
     * 　@　@　@　@　@　@(引数)昇順IP別ログイン処理回数.length - i - 1)] を代入<BR>
     * <BR>
     * 　@２－２) i != 0回目以外の場合は、下記処理を実施。<BR>
     * <BR>
     * 　@　@２－２－１) 同着順位判定処理。<BR>
     * 　@　@　@　@　@　@　@　@①@(小数点以下を切捨てた)【ログイン処理回数(降順)】<BR>
     * 　@　@　@　@　@　@　@　@　@　@と 前回ログイン処理回数 が等しかった場合<BR>
     * 　@　@　@　@　@　@　@　@・同着カウンターに +1　@追加する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@②(小数点以下を切捨てた)【ログイン処理回数(降順)】<BR>
     * 　@　@　@　@　@　@　@　@　@　@と 前回ログイン処理回数 が等しくなかった場合<BR>
     * 　@　@　@　@　@　@　@　@・同着カウンターに 0 を代入する。(値のリセット)<BR>
     * <BR>
     * 　@２－３) ログイン明細一覧 ランク範囲での取得処理。<BR>
     * 　@　@　@　@　@　@①@( 順位 - 同着カウンター ) > (引数)ランク の場合<BR>
     * 　@　@　@　@　@　@　@　@２） のループをbreakする。<BR>
     * <BR>
     * 　@　@　@　@　@　@② !( ( 順位 - 同着カウンター ) > (引数)ランク) の場合<BR>
     * 　@　@　@　@　@　@　@　@　@下記の処理を実施する。<BR>
     * <BR>
     * 　@　@２－３－１) IP別ログイン回数情報オブジェクト を生成する。<BR>
     * <BR>
     * 　@　@２－３－２) IPアドレス、ログイン処理回数、ランクの取得。<BR>
     * 　@　@　@　@　@　@　@　@　@　@①@同着カウンター != 0 の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・IP別ログイン回数情報オブジェクト.IPアドレス = <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@IPアドレス別ログイン処理回数.get(<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@new Double(【ログイン処理回数(降順)】));<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・IP別ログイン回数情報オブジェクト.ランク = <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@new Integer(順位 - 同着カウンター ).toString();<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・IP別ログイン回数情報オブジェクト.ログイン処理回数 = <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@(小数点以下を切捨てた)【ログイン処理回数(降順)】<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@②同着カウンター == 0 の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・IP別ログイン回数情報オブジェクト.IPアドレス = <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@IPアドレス別ログイン処理回数.get(<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@new Double(【ログイン処理回数(降順)】));<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・IP別ログイン回数情報オブジェクト.ランク = <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@new Integer(順位).toString();<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・IP別ログイン回数情報オブジェクト.ログイン処理回数 = <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@(小数点以下を切捨てた)【ログイン処理回数(降順)】<BR>
     * <BR>
     * 　@　@　@２－３－３) ログイン明細一覧リストに、IP別ログイン回数情報オブジェクトを追加。<BR>
     * <BR>
     * 　@２－４) <BR>
     * 前回ログイン処理回数に(小数点以下を切捨てた)【ログイン処理回数(降順)】を格納する。<BR>
     * <BR>
     * ３) ログイン明細一覧リストを返却する。<BR>
     * @@param l_dblAscIPLoginProcessingFrequencys - (昇順IP別ログイン処理回数)<BR>
     * 昇順ソートされたIP別ログイン処理回数。<BR>
     * @@param l_strRank - (ランク)<BR>
     * @@return List
     * @@roseuid 48C62ACE02B3
     */
    private List getIPLoginFrequencyUnitList(double[] l_dblAscIPLoginProcessingFrequencys, String l_strRank)
    {
        final String STR_METHOD_NAME = "getIPLoginFrequencyUnitList(double[], String)";
        log.entering(STR_METHOD_NAME);

        //１) 下記の内部変数を定義
        //・順位 (int型 初期値：1)
        int l_intNumber = 1;

        //・【ログイン処理回数(降順)】 (double型 初期値：0.0)
        double l_dblLoginProcessingFrequency = 0.0;

        //・前回ログイン処理回数 (int型 初期値：0)
        int l_intPastLoginProcessingFrequency = 0;

        //・同着カウンター (int型 初期値：0)
        int l_intCounter = 0;

        //・ログイン明細一覧リスト (List ログイン明細一覧リスト = new ArrayList();)
        List l_lisLoginUnitList = new ArrayList();

        //２) (引数)昇順IP別ログイン処理回数.length 回だけ下記の処理をループ。
        //繰返し回数を i (初期値：0) とし、 i, 順位 に+1加算しながらループする。
        int l_intSize = l_dblAscIPLoginProcessingFrequencys.length;
        for (int i = 0; i < l_intSize; i++, l_intNumber++)
        {
            //２－１) 【ログイン処理回数(降順)】に
            //(引数)昇順IP別ログイン処理回数[( (引数)昇順IP別ログイン処理回数.length - i - 1)] を代入
            l_dblLoginProcessingFrequency = l_dblAscIPLoginProcessingFrequencys[l_intSize - i - 1];

            //２－２) i != 0回目以外の場合は、下記処理を実施。
            if (i != 0)
            {
                //２－２－１) 同着順位判定処理。
                //①@(小数点以下を切捨てた)【ログイン処理回数(降順)】
                //と 前回ログイン処理回数 が等しかった場合
                if ((int)l_dblLoginProcessingFrequency == l_intPastLoginProcessingFrequency)
                {
                    //・同着カウンターに +1　@追加する。
                    l_intCounter++;
                }
                //②(小数点以下を切捨てた)【ログイン処理回数(降順)】
                //と 前回ログイン処理回数 が等しくなかった場合
                else
                {
                    //・同着カウンターに 0 を代入する。(値のリセット)
                    l_intCounter = 0;
                }
            }

            //２－３) ログイン明細一覧 ランク範囲での取得処理。
            //①@( 順位 - 同着カウンター ) > (引数)ランク の場合
            if ((l_intNumber - l_intCounter) > Integer.parseInt(l_strRank))
            {
                //２） のループをbreakする。
                break;
            }
            //② !( ( 順位 - 同着カウンター ) > (引数)ランク) の場合
            //下記の処理を実施する。
            else
            {
                //２－３－１) IP別ログイン回数情報オブジェクト を生成する。
                WEB3AdminTraderAdminLoginCountReferenceUnit l_loginCountReferenceUnits =
                    new WEB3AdminTraderAdminLoginCountReferenceUnit();

                //２－３－２) IPアドレス、ログイン処理回数、ランクの取得。
                //①@同着カウンター != 0 の場合
                if (l_intCounter != 0)
                {
                    //・IP別ログイン回数情報オブジェクト.IPアドレス =
                    //IPアドレス別ログイン処理回数.get(new Double(【ログイン処理回数(降順)】));
                    l_loginCountReferenceUnits.ipAddress =
                        ipAddressLoginProcessingFrequency.get(new Double(l_dblLoginProcessingFrequency)).toString();

                    //・IP別ログイン回数情報オブジェクト.ランク =
                    //new Integer(順位 - 同着カウンター ).toString();
                    l_loginCountReferenceUnits.rank = new Integer(l_intNumber - l_intCounter).toString();

                    //・IP別ログイン回数情報オブジェクト.ログイン処理回数 =
                    //(小数点以下を切捨てた)【ログイン処理回数(降順)】
                    l_loginCountReferenceUnits.loginCount = String.valueOf((int)l_dblLoginProcessingFrequency);
                }
                //②同着カウンター == 0 の場合
                else
                {
                    //・IP別ログイン回数情報オブジェクト.IPアドレス =
                    //IPアドレス別ログイン処理回数.get(new Double(【ログイン処理回数(降順)】));
                    l_loginCountReferenceUnits.ipAddress =
                        ipAddressLoginProcessingFrequency.get(new Double(l_dblLoginProcessingFrequency)).toString();

                    //・IP別ログイン回数情報オブジェクト.ランク = new Integer(順位).toString();
                    l_loginCountReferenceUnits.rank = new Integer(l_intNumber).toString();

                    //・IP別ログイン回数情報オブジェクト.ログイン処理回数 =
                    //(小数点以下を切捨てた)【ログイン処理回数(降順)】
                    l_loginCountReferenceUnits.loginCount = String.valueOf((int)l_dblLoginProcessingFrequency);
                }

                //２－３－３) ログイン明細一覧リストに、IP別ログイン回数情報オブジェクトを追加。
                l_lisLoginUnitList.add(l_loginCountReferenceUnits);
            }

            //２－４) 前回ログイン処理回数に(小数点以下を切捨てた)【ログイン処理回数(降順)】を格納する。
            l_intPastLoginProcessingFrequency = (int)l_dblLoginProcessingFrequency;
        }

        log.exiting(STR_METHOD_NAME);

        //３) ログイン明細一覧リストを返却する。
        return l_lisLoginUnitList;
    }
}@
