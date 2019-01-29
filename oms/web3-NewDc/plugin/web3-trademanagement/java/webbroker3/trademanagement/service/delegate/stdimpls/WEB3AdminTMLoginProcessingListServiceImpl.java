head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginProcessingListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン処理一覧サービスImpl (WEB3AdminTMLoginProcessingListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 張少傑 (中訊) 新規作成 モデルNo.005,007,009
Revision History : 2008/10/06 張少傑 (中訊) 仕様変更 モデルNo.012,No.014,No.015
Revision History : 2008/10/09 張少傑 (中訊) 仕様変更 モデルNo.016
Revision History : 2008/10/17 肖志偉 (中訊) 仕様変更 モデル018
*/

package webbroker3.trademanagement.service.delegate.stdimpls;
import java.util.ArrayList;
import java.util.List;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.trademanagement.define.WEB3AdminTMKeyItemDef;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryReferenceUnit;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistorySortKey;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (ログイン処理一覧サービスImpl)<BR>
 * 管理者ログイン処理一覧サービス実装クラス。<BR>
 * <BR>
 * @@version 1.0
 * @@author 張少傑
 */
public class WEB3AdminTMLoginProcessingListServiceImpl implements WEB3AdminTMLoginProcessingListService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginProcessingListServiceImpl.class);

    /**
     * @@roseuid 48D74D2502B3
     */
    public WEB3AdminTMLoginProcessingListServiceImpl()
    {

    }

    /**
     * 管理者・ログイン処理一覧を開始する。 <BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・ログイン処理一覧検索入力リクエストの場合 <BR>
     * 　@this.get検索入力画面()をコールする。 <BR>
     * <BR>
     * ○管理者・ログイン処理一覧検索結果リクエストの場合 <BR>
     * 　@this.get検索結果画面()をコールする。 <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48C5C6D203C7
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
        if (l_request instanceof WEB3AdminTraderAdminLoginHistoryInputRequest)
        {
            //管理者・ログイン処理一覧検索入力リクエストの場合
            //this.get検索入力画面()をコールする。
            l_response = getSearchInputScreen(
                (WEB3AdminTraderAdminLoginHistoryInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminTraderAdminLoginHistoryListRequest)
        {
            //管理者・ログイン処理一覧検索結果リクエストの場合
            //this.get検索結果画面()をコールする。
            l_response = getSearchResultScreen(
                (WEB3AdminTraderAdminLoginHistoryListRequest)l_request);
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
     * ログイン処理一覧の検索入力画面の表示を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者・ログイン処理一覧)get検索入力」参照。 <BR>
     * @@param l_request - (リクエスト)<BR>
     * 管理者・ログイン処理一覧表示入力リクエストクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE0DC0025E
     */
    protected WEB3AdminTraderAdminLoginHistoryInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSearchInputScreen(WEB3AdminTraderAdminLoginHistoryInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginHistoryInputResponse l_response = null;

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, false);

        l_response = (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索結果画面)<BR>
     * ログイン処理一覧の検索結果画面の表示を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(管理者・ログイン処理一覧)get検索結果」参照。 <BR>
     * @@param l_request - (リクエスト)<BR>
     * 管管理者・ログイン処理一覧検索結果レスポンスクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE0E6D02BF
     */
    protected WEB3AdminTraderAdminLoginHistoryListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginHistoryListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSearchResultScreen(WEB3AdminTraderAdminLoginHistoryListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限
        l_administartor.validateAuthority(WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, false);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administartor.getInstitutionCode();

        //IPアドレス
        String l_strIpAddress = l_request.ipAddress;
        //顧客コード
        String l_strAccountCode = l_request.accountCode;
        //部店コード
        String l_strBranchCode = l_request.branchCode;
        //日付
        String l_strSearchDate = l_request.searchDate;
        //時間(自)
        String l_strStartTime = l_request.startTime;
        //時間(至)。
        String l_strEndTime = l_request.endTime;

        //create検索条件文字列()
        String l_strQueryString = this.createQueryString(l_strIpAddress,
           l_strAccountCode, l_strBranchCode);

        //create検索条件データコンテナ()
        Object[] l_strQueryDataContainers =
            this.createQueryDataContainer(
                l_strInstitutionCode,
                l_strSearchDate,
                l_strIpAddress,
                l_strBranchCode,
                l_strAccountCode,
                l_strStartTime,
                l_strEndTime);

        WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys = l_request.sortKeys;
        //createソートキー()
        String l_strSortCond = this.createSortCond(l_sortKeys);

        //getログイン処理一覧()
        List l_lisLoginProessInfoList = this.getLoginProessInfoList(
            l_strQueryString,
            l_strSortCond,
            l_strQueryDataContainers);

        //WEB3PageIndexInfo
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisLoginProessInfoList,
            l_intRequestPageIndex,
            l_intRequestPageSize);

        //getListReturned( )
        List l_lisReturn = l_pageIndexInfo.getListReturned();

        //createログイン処理情報一覧()
        WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_adminTraderAdminLoginHistoryReferenceUnit =
            this.createLoginProessInfoList(l_lisReturn);

        //getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        //getPageIndex( )
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();
        //getTotalPages( )
        int l_intTotalPageSize = l_pageIndexInfo.getTotalPages();
        //createResponse( )
        l_response =
            (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
        //プロパティセット
        l_response.pageIndex = String.valueOf(l_intPageIndex);
        l_response.totalPages = String.valueOf(l_intTotalPageSize);
        l_response.totalRecords = String.valueOf(l_intTotalRecords);
        l_response.loginHistoryList = l_adminTraderAdminLoginHistoryReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * データを取得する為の条件を生成する。 <BR>
     * <BR>
     * １）条件文格納変数を用意する。 <BR>
     * <BR>
     * ２）条件文の作成 <BR>
     * <BR>
     * 　@２－１） １）の変数に下記の文字列を追加する。<BR>
     * 　@　@　@　@　@　@"institution_code = ? and <BR>
     * 　@　@　@　@　@　@　@login_timestamp >= to_date(' ? || ? || 00 ', 'yyyymmddhh24miss') and<BR>
     * 　@　@　@　@　@　@　@login_timestamp <= to_date(' ? || ? || 59 ', 'yyyymmddhh24miss')<BR>
     * <BR>
     * 　@２－２） 引数.IPアドレス != null の場合<BR>
     * 　@　@　@　@　@　@１）の変数に下記の文字列を追加する。<BR>
     * 　@　@　@　@　@　@"and ip_address = ?"<BR>
     * <BR>
     * 　@２－３） 引数.部店コード != null の場合<BR>
     * 　@　@　@　@　@　@１）の変数に下記の文字列を追加する。<BR>
     * 　@　@　@　@　@　@"and branch_code like ? || '%'"<BR>
     * <BR>
     * 　@２－４） 引数.顧客コード != null の場合<BR>
     * 　@　@　@　@　@　@１）の変数に下記の文字列を追加する。<BR>
     * 　@　@　@　@　@　@"and account_code like ? || '%' "<BR>
     * <BR>
     * ３） 文字列を返却する。 <BR>
     * @@param l_strIpAddress - (IPアドレス)<BR>
     * 画面から入力されたIPアドレス。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード。<BR>
     * @@return String
     * @@roseuid 48BF458E03D8
     */
    private String createQueryString(
        String l_strIpAddress,
        String l_strAccountCode,
        String l_strBranchCode)
    {
        final String STR_METHOD_NAME =
            "createQueryString(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //条件文格納変数を用意する。
        StringBuffer l_sbQueryString = new StringBuffer();
        //条件文の作成
        String l_strQueryString = "institution_code = ? and " +
            "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
            "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') ";
        l_sbQueryString.append(l_strQueryString);
        if (l_strIpAddress != null)
        {
            l_sbQueryString.append(" and ip_address = ?");
        }
        if (l_strBranchCode != null)
        {
            l_sbQueryString.append(" and branch_code like ? || '%'");
        }
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
        }
        log.exiting(STR_METHOD_NAME);

        //文字列を返却する。
        return l_sbQueryString.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 取得条件データコンテナ作成。 <BR>
     * <BR>
     * １） 空のArrayListを生成する。 <BR>
     * 　@　@ List lst = new ArrayList();<BR>
     * <BR>
     * ２） １）のArrayListに以下の情報を追加する。<BR>
     * 　@　@ 　@lst.add((引数)証券会社コード);<BR>
     * 　@　@ 　@lst.add((引数)日付);<BR>
     * 　@　@ 　@lst.add((引数)時間(自));<BR>
     * 　@　@ 　@lst.add((引数)日付);<BR>
     * 　@　@ 　@lst.add((引数)時間(至));<BR>
     * <BR>
     * ３） 入力引数チェック。<BR>
     * <BR>
     * 　@　@３－１） 引数.IPアドレス != null の場合、下記の処理を実行。<BR>
     * 　@　@　@　@　@　@lst.add((引数)IPアドレス);<BR>
     * <BR>
     * 　@　@３－２） 引数.部店コード != null の場合、下記の処理を実行。<BR>
     * 　@　@　@　@　@　@lst.add((引数)部店コード);<BR>
     * <BR>
     * 　@　@３－３） 引数.顧客コード != null の場合、下記の処理を実行。<BR>
     * 　@　@　@　@　@　@lst.add((引数)顧客コード);<BR>
     * <BR>
     * ４） 追加したArrayListに対して、toArray()を実行。 <BR>
     * 　@　@ 配列を取得し、返却する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strSearchDate - (日付)<BR>
     * 画面入力された日付。'yyyymmdd' 形式。<BR>
     * @@param l_strIpAddress - (IPアドレス)<BR>
     * 画面から入力されたIPアドレス。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。(6桁)<BR>
     * @@param l_strStartTime - (時間(自))<BR>
     * 画面入力された時間(自)。'hh24mi'形式。<BR>
     * @@param l_strEndTime - (時間(至))<BR>
     * 画面入力された時間(至)。'hh24mi'形式。<BR>
     * @@return Object[]
     * @@roseuid 48BF459B01C1
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        String l_strSearchDate,
        String l_strIpAddress,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strStartTime,
        String l_strEndTime)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        Object[] l_queryDatas = null;
        //空のArrayListを生成する。
        List l_lisQueryData = new ArrayList();

        //のArrayListに情報を追加する。
        //lst.add((引数)証券会社コード);
        l_lisQueryData.add(l_strInstitutionCode);

        //lst.add((引数)日付);
        l_lisQueryData.add(l_strSearchDate);

        //lst.add((引数)時間(自));
        l_lisQueryData.add(l_strStartTime);

        //lst.add((引数)日付)
        l_lisQueryData.add(l_strSearchDate);

        //lst.add((引数)時間(至));
        l_lisQueryData.add(l_strEndTime);

        //入力引数チェック。
        if (l_strIpAddress != null)
        {
            l_lisQueryData.add(l_strIpAddress);
        }
        if (l_strBranchCode != null)
        {
            l_lisQueryData.add(l_strBranchCode);
        }
        if (l_strAccountCode != null)
        {
            l_lisQueryData.add(l_strAccountCode);
        }

        l_queryDatas = new Object[l_lisQueryData.size()];
        l_lisQueryData.toArray(l_queryDatas);
        log.exiting(STR_METHOD_NAME);

        return l_queryDatas;
    }

    /**
     * (createソート条件)<BR>
     * ソート条件文字列を編集する。<BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を編集する。<BR>
     * <BR>
     * １） (引数)ソートキーの要素数分以下の処理を繰り返し、 <BR>
     * 　@　@　@ソート条件文字列を作成する。<BR>
     * <BR>
     * 　@１－１） (引数)ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@１－１－１） ソートキー.キー項目＝「IPアドレス」の場合、以下の文字列を作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@"NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') || <BR>
     * 　@　@　@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1, <BR>
     * 　@　@　@ INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') || <BR>
     * 　@　@　@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1, <BR>
     * 　@　@　@ INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') || <BR>
     * 　@　@　@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')" <BR>
     * <BR>
     * 　@　@１－１－２）　@１－１－１）以外の場合、ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。 <BR>
     * <BR>
     * 　@　@　@・「日時」 →　@ログイン(過去)履歴テーブル.日時 <BR>
     * 　@　@　@・「顧客コード」 →　@ログイン(過去)履歴テーブル.顧客コード <BR>
     * <BR>
     * 　@１－２） ソート条件に該当するソート条件を編集する。<BR>
     * 　@　@　@　@　@ 昇順：asc <BR>
     * 　@　@　@　@ 　@降順：desc <BR>
     * <BR>
     * ２） 作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー保持。<BR>
     * @@return String
     * @@roseuid 48BF45AC029C
     */
    private String createSortCond(WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCond(String, WEB3AdminTraderAdminLoginHistorySortKey[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortCond = new StringBuffer();
        String l_strSortCond = " NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS," +
            " '.', 1, 1) -1), 3, '0'), '000') ||" +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR" +
            "( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') || " +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR" +
            "( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') || " +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000') ";
        //(引数)ソートキーの要素数分以下の処理を繰り返し、
        //ソート条件文字列を作成する。
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //１－１） (引数)ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する。
            //１－１－１） ソートキー.キー項目＝「IPアドレス」の場合、以下の文字列を作成したソート条件文字列に追加する。
            if (WEB3AdminTMKeyItemDef.IP_ADDRESS.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append(l_strSortCond);
            }
            //１－１－２）　@１－１－１）以外の場合、ソートキー.キー項目を対応する列物理名に変換し、
            //作成したソート条件文字列に追加する。
            //「日時」 →　@ログイン(過去)履歴テーブル.日時
            else if (WEB3AdminTMKeyItemDef.LOGIN_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append(" login_timestamp ");
            }
            //「顧客コード」 →　@ログイン(過去)履歴テーブル.顧客コード
            else if (WEB3AdminTMKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCond.append(" account_code ");
            }
            //ソート条件に該当するソート条件を編集する。
            //     昇順：asc
            //　@ 　@降順：desc
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append("ASC");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append("DESC");
            }
            if (i < l_intLength - 1)
            {
                l_sbSortCond.append(", ");
            }
        }
        log.exiting(STR_METHOD_NAME);

        //作成したソート条件文字列を返却する
        return l_sbSortCond.toString();
    }

    /**
     * (getログイン処理情報一覧)<BR>
     * ログイン履歴 又は ログイン過去履歴からレコードを取得する。<BR>
     * <BR>
     * １） ログインテーブルデータマネージャ.getRowType()メソッドをコールする。<BR>
     * 　@[getRowType()にセットするパラメータ] <BR>
     * 　@　@日付       ： (引数)データコンテナ　@にセットした日付<BR>
     * 　@　@時間(自) ： (引数)データコンテナ　@にセットした時間(自) <BR>
     * <BR>
     * ２） Processors.getDefaultProcessor()メソッドをコールする。<BR>
     * <BR>
     * ３） QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@ rowType   ：　@１） の戻り値<BR>
     * 　@　@ where      ：　@(引数)検索条件<BR>
     * 　@　@ orderBy    ：　@(引数)検索ソート条件<BR>
     * 　@　@ conditions ：　@null<BR>
     * 　@　@ bindVars   ：　@(引数)データコンテナ<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037））<BR>
     * <BR>
     * ２） １） の戻り値を返却する。<BR>
     * @@param l_strSearchCondition - (検索条件)<BR>
     * 検索条件。<BR>
     * @@param l_strSearchSortCondtion - (検索ソート条件)<BR>
     * 検索ソート条件。<BR>
     * @@param l_dataContainers - (データコンテナ)<BR>
     * デーテコンテナ。<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 48BF45BA03B3
     */
    private List getLoginProessInfoList(
        String l_strSearchCondition,
        String l_strSearchSortCondtion,
        Object[] l_dataContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLoginProessInfoList(String, String, Object[])";
        log.entering(STR_METHOD_NAME);

        //ログインテーブルデータマネージャ.getRowType()メソッドをコールする。
        WEB3TradeManagementLoginTableDataManager l_tradeManagementLoginTableDataManager =
            new WEB3TradeManagementLoginTableDataManager();
        RowType l_rowType =
            l_tradeManagementLoginTableDataManager.getRowType((String)l_dataContainers[1], (String)l_dataContainers[2]);
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords  = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_strSearchCondition,
                l_strSearchSortCondtion,
                null,
                l_dataContainers);
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
        if (l_lisRecords.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (createログイン処理情報一覧)<BR>
     * ログイン処理情報Paramsより、一覧情報を作成する。 <BR>
     * <BR>
     * １）　@ArrayListオブジェクトの生成。 <BR>
     * <BR>
     * ２）　@(引数)ページ毎ログイン処理情報の要素分、Loop処理を行う。 <BR>
     * <BR>
     * 　@２－１）　@ログイン履歴情報クラスのオブジェクトを生成。 <BR>
     * <BR>
     * 　@２－２）　@ページ毎ログイン処理情報からログイン(過去)履歴テーブルRowを取り出す。 <BR>
     * <BR>
     * 　@２－３）　@２－１）で生成したオブジェクトに以下の内容をセットする。 <BR>
     * <BR>
     * 　@　@・ ログイン履歴情報オブジェクト.ログイン日時    = <BR>
     * 　@　@　@　@　@　@　@ログイン(過去)履歴テーブルRow.getログイン日時<BR>
     * 　@　@・ ログイン履歴情報オブジェクト.IPアドレス       = <BR>
     * 　@　@　@　@　@　@　@ログイン(過去)履歴テーブルRow.getIPアドレス<BR>
     * 　@　@・ ログイン履歴情報オブジェクト.注文経路区分 = <BR>
     * 　@　@　@　@　@　@　@ログイン(過去)履歴テーブルRow.get注文経路区分<BR>
     * 　@　@・ ログイン履歴情報オブジェクト.ログイン成否    = <BR>
     * 　@　@　@　@　@　@　@ログイン(過去)履歴テーブルRow.getログイン成否<BR>
     * 　@　@・ ログイン履歴情報オブジェクト.部店コード      = <BR>
     * 　@　@　@　@　@　@　@ログイン(過去)履歴テーブルRow.get部店コード<BR>
     * 　@　@・ ログイン履歴情報オブジェクト.顧客コード       = <BR>
     * 　@　@　@　@　@　@　@ログイン(過去)履歴テーブルRow.get顧客コード<BR>
     * <BR>
     * 　@２－４）　@１）で生成したArrayListオブジェクトにログイン履歴情報オブジェクトをadd()する。 <BR>
     * <BR>
     * ３）　@１）で生成したArrayListオブジェクトを配列に変換して返却する。<BR>
     * <BR>
     * @@param l_lisPagePrevLoginProcessInfos - (ページ毎ログイン処理情報)<BR>
     * ページ毎ログイン処理情報。<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryReferenceUnit[]
     * @@roseuid 48C0B719017B
     */
    private WEB3AdminTraderAdminLoginHistoryReferenceUnit[] createLoginProessInfoList(
        List l_lisPagePrevLoginProcessInfos)
    {
        final String STR_METHOD_NAME = "createLoginProessInfoList(List)";
        log.entering(STR_METHOD_NAME);

        //ArrayListオブジェクトの生成。
        List l_lisAdminTraderAdminLoginHistoryReferenceUnits = new ArrayList();
        int l_intLength = l_lisPagePrevLoginProcessInfos.size();
        //(引数)ページ毎ログイン処理情報の要素分、Loop処理を行う。
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3AdminTraderAdminLoginHistoryReferenceUnit
                l_adminTraderAdminLoginHistoryReferenceUnit =
                    new WEB3AdminTraderAdminLoginHistoryReferenceUnit();
            if (l_lisPagePrevLoginProcessInfos.get(i) instanceof LoginHistoryRow)
            {
                LoginHistoryRow l_loginHistoryRow =
                    (LoginHistoryRow)l_lisPagePrevLoginProcessInfos.get(i);
                //　@・ ログイン履歴情報オブジェクト.ログイン日時 = ログイン履歴テーブルRow.getログイン日時
                l_adminTraderAdminLoginHistoryReferenceUnit.loginDate =
                    l_loginHistoryRow.getLoginTimestamp();
                //ログイン履歴情報オブジェクト.IPアドレス = ログイン履歴テーブルRow.getIPアドレス
                l_adminTraderAdminLoginHistoryReferenceUnit.ipAddress =
                    l_loginHistoryRow.getIpAddress();
                //ログイン履歴情報オブジェクト.注文経路区分 = ログイン履歴テーブルRow.get注文経路区分
                l_adminTraderAdminLoginHistoryReferenceUnit.orderRootDiv =
                    l_loginHistoryRow.getOrderRootDiv();
                //ログイン履歴情報オブジェクト.ログイン成否 = ログイン履歴テーブルRow.getログイン成否
                l_adminTraderAdminLoginHistoryReferenceUnit.loginResult =
                    l_loginHistoryRow.getLoginFailure();
                //ログイン履歴情報オブジェクト.部店コード = ログイン履歴テーブルRow.get部店コード
                l_adminTraderAdminLoginHistoryReferenceUnit.branchCode =
                    l_loginHistoryRow.getBranchCode();
                //ログイン履歴情報オブジェクト.顧客コード =
                //　@　@　@ログイン履歴テーブルRow.get顧客コード
                l_adminTraderAdminLoginHistoryReferenceUnit.accountCode =
                    l_loginHistoryRow.getAccountCode();
            }
            else if (l_lisPagePrevLoginProcessInfos.get(i) instanceof LoginHistoryPastRow)
            {
                LoginHistoryPastRow l_loginHistoryPastRow =
                    (LoginHistoryPastRow)l_lisPagePrevLoginProcessInfos.get(i);
                //　@・ ログイン履歴情報オブジェクト.ログイン日時 = ログイン(過去)履歴テーブルRow.getログイン日時
                l_adminTraderAdminLoginHistoryReferenceUnit.loginDate =
                    l_loginHistoryPastRow.getLoginTimestamp();
                //ログイン履歴情報オブジェクト.IPアドレス = ログイン(過去)履歴テーブルRow.getIPアドレス
                l_adminTraderAdminLoginHistoryReferenceUnit.ipAddress =
                    l_loginHistoryPastRow.getIpAddress();
                //ログイン履歴情報オブジェクト.注文経路区分 = ログイン(過去)履歴テーブルRow.get注文経路区分
                l_adminTraderAdminLoginHistoryReferenceUnit.orderRootDiv =
                    l_loginHistoryPastRow.getOrderRootDiv();
                //ログイン履歴情報オブジェクト.ログイン成否 = ログイン(過去)履歴テーブルRow.getログイン成否
                l_adminTraderAdminLoginHistoryReferenceUnit.loginResult =
                    l_loginHistoryPastRow.getLoginFailure();
                //ログイン履歴情報オブジェクト.部店コード = ログイン(過去)履歴テーブルRow.get部店コード
                l_adminTraderAdminLoginHistoryReferenceUnit.branchCode =
                    l_loginHistoryPastRow.getBranchCode();
                //ログイン履歴情報オブジェクト.顧客コード =
                //　@　@　@ログイン(過去)履歴テーブルRow.get顧客コード
                l_adminTraderAdminLoginHistoryReferenceUnit.accountCode =
                    l_loginHistoryPastRow.getAccountCode();
            }
            //生成したArrayListオブジェクトにログイン履歴情報オブジェクトをadd()する。
            l_lisAdminTraderAdminLoginHistoryReferenceUnits.add(l_adminTraderAdminLoginHistoryReferenceUnit);
        }

        //生成したArrayListオブジェクトを配列に変換して返却する。
        int l_intUnitsLength = l_lisAdminTraderAdminLoginHistoryReferenceUnits.size();
        WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_adminTraderAdminLoginHistoryReferenceUnits =
            new WEB3AdminTraderAdminLoginHistoryReferenceUnit[l_intUnitsLength];
        for (int i = 0; i < l_intUnitsLength; i++)
        {
            l_adminTraderAdminLoginHistoryReferenceUnits[i] =
                (WEB3AdminTraderAdminLoginHistoryReferenceUnit)
                    l_lisAdminTraderAdminLoginHistoryReferenceUnits.get(i);
        }

        log.exiting(STR_METHOD_NAME);

        return l_adminTraderAdminLoginHistoryReferenceUnits;
    }
}
@
