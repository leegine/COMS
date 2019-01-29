head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.18.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginRejectIPManagementServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ログイン拒否IP管理サービスImpl(WEB3AdminTMLoginRejectIPManagementServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 安陽(中訊) 新規作成 モデル No.004 006
Revision History : 2008/09/24 張騰宇(中訊) DB更新仕様 002
Revision History : 2008/10/10 安陽(中訊) 仕様変更 モデル No.016
*/

package webbroker3.trademanagement.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import webbroker3.common.define.WEB3LoginRegistDivDef;
import webbroker3.common.define.WEB3LoginRejectIpStatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3UpdatedDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.LoginRejectIpDao;
import webbroker3.gentrade.data.LoginRejectIpParams;
import webbroker3.gentrade.data.LoginRejectIpRow;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginRejectIPManagementService;
import webbroker3.trademanagement.define.WEB3AdminTMKeyItemDef;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlReferenceUnit;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlSortKey;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (管理者ログイン拒否IP管理サービスImpl)<BR>
 * 管理者ログイン拒否IP管理サービス実装クラス<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3AdminTMLoginRejectIPManagementServiceImpl implements WEB3AdminTMLoginRejectIPManagementService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginRejectIPManagementServiceImpl.class);

    /**
     * @@roseuid 48D75CD80250
     */
    public WEB3AdminTMLoginRejectIPManagementServiceImpl()
    {

    }

    /**
     * 管理者ログイン拒否IP管理サービス処理を行う。<BR>
     * <BR>
     * 引数のリクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP一覧リクエストの場合<BR>
     * 　@this.get一覧画面()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録リクエストの場合<BR>
     * 　@this.get登録画面()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録確認リクエストの場合<BR>
     * 　@this.validate登録()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録完了リクエストの場合<BR>
     * 　@this.submit登録()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録情報変更リクエストの場合<BR>
     * 　@this.get変更画面()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録情報変更確認リクエストの場合<BR>
     * 　@this.validate変更()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録情報変更完了リクエストの場合<BR>
     * 　@this.submit変更()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録情報削除確認リクエストの場合<BR>
     * 　@this.validate削除()をコールする。<BR>
     * <BR>
     * ○管理者・ログイン拒否IP登録情報削除完了リクエストの場合<BR>
     * 　@this.submit削除()をコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE15EA00F2
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

        WEB3GenResponse l_response = null;

        //引数のリクエストデータの型により、以下のメソッドをコールする。
        //○管理者・ログイン拒否IP一覧リクエストの場合
        if (l_request instanceof WEB3AdminTraderAdminIPControlListRequest)
        {
            //this.get一覧画面()をコールする。
            l_response = getListScreen((WEB3AdminTraderAdminIPControlListRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlRegistInputRequest)
        {
            //this.get登録画面()をコールする。
            l_response = getRegistScreen((WEB3AdminTraderAdminIPControlRegistInputRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録確認リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlRegistConfirmRequest)
        {
            //this.validate登録()をコールする。
            l_response = validateRegist((WEB3AdminTraderAdminIPControlRegistConfirmRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録完了リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlRegistCompleteRequest)
        {
            //this.submit登録()をコールする。
            l_response = submitRegist((WEB3AdminTraderAdminIPControlRegistCompleteRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録情報変更リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlUpdateInputRequest)
        {
            //this.get変更画面()をコールする。
            l_response = getChangedScreen((WEB3AdminTraderAdminIPControlUpdateInputRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録情報変更確認リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlUpdateConfirmRequest)
        {
            //this.validate変更()をコールする。
            l_response = validateChange((WEB3AdminTraderAdminIPControlUpdateConfirmRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録情報変更完了リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlUpdateCompleteRequest)
        {
            //this.submit変更()をコールする。
            l_response = submitChange((WEB3AdminTraderAdminIPControlUpdateCompleteRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録情報削除確認リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlDeleteConfirmRequest)
        {
            //this.validate削除()をコールする。
            l_response = validateDelete((WEB3AdminTraderAdminIPControlDeleteConfirmRequest)l_request);
        }
        //○管理者・ログイン拒否IP登録情報削除完了リクエストの場合
        else if (l_request instanceof WEB3AdminTraderAdminIPControlDeleteCompleteRequest)
        {
            //this.submit削除()をコールする。
            l_response = submitDelete((WEB3AdminTraderAdminIPControlDeleteCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面)<BR>
     * ログイン拒否IP一覧画面表示処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）get一覧画面」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP一覧リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlListResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE281102E4
     */
    protected WEB3AdminTraderAdminIPControlListResponse getListScreen(
        WEB3AdminTraderAdminIPControlListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getListScreen(WEB3AdminTraderAdminIPControlListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //当リクエストデータの整合性チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新： false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, false);

        //get証券会社コード( )
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //createQuerySortCond(ソートキー[])
        //ログイン拒否IP一覧ソート条件を作成する。
        //[create検索ソート条件()に指定する引数]
        //ソートキー　@：　@リクエストデータ.ソートキー
        String l_strQuerySortCond = createQuerySortCond(l_request.sortKeys);

        //getLoginRejectIPList(String, String, String, String)
        //ログイン拒否IPテーブルからレコードを取得する。
        //[getログイン拒否IPレコード()に指定する引数]
        //検索ソート条件 ： create検索ソート条件()の戻り値
        //証券会社コード ： get証券会社コード()の戻り値
        //ページ内表示行数 ： リクエストデータ.ページ内表示行数
        //要求ページ番号 ： リクエストデータ.要求ページ番号
        List l_lisLoginRejectIPs = getLoginRejectIPList(
            l_strQuerySortCond,
            l_strInstitutionCode,
            l_request.pageSize,
            l_request.pageIndex);

        //ArrayList( )
        //空のArrayListを生成。
        List l_lisAdminIPControlReferenceUnits = new ArrayList();

        //取得したレコード分LOOP処理
        int l_intLength = l_lisLoginRejectIPs.size();
        for (int i = 0; i < l_intLength; i++)
        {
            LoginRejectIpRow l_loginRejectIPRow =
                (LoginRejectIpRow)l_lisLoginRejectIPs.get(i);

            LoginRejectIpParams l_loginRejectIPParams =
                new LoginRejectIpParams(l_loginRejectIPRow);

            //createLoginRejectIPList(ログイン拒否IPParams)
            //ログイン拒否IP情報メッセージデータを作成する。
            //[createログイン拒否IP情報()に指定する引数]
            //ログイン拒否IP行 ： getログイン拒否IP一覧()にて取得したログイン拒否IPParams
            WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
                createLoginRejectIPList(l_loginRejectIPParams);

            //add(arg0 : Object)
            //リストにログイン拒否IP情報オブジェクトを追加する。
            //[引数]
            //arg0： createログイン拒否IP情報()の戻り値
            l_lisAdminIPControlReferenceUnits.add(l_adminIPControlReferenceUnit);
        }

        //toArray( )
        //リストから配列を取得する。
        WEB3AdminTraderAdminIPControlReferenceUnit[] l_adminIPControlReferenceUnits =
            new WEB3AdminTraderAdminIPControlReferenceUnit[l_lisAdminIPControlReferenceUnits.size()];
        l_lisAdminIPControlReferenceUnits.toArray(l_adminIPControlReferenceUnits);

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisLoginRejectIPs,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //getPageIndex( )
        //表示ページ番号を取得する。
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();

        //getTotalPages( )
        //総ページ数を取得する。
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //getTotalRecords( )
        //総レコード数を取得する。
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlListResponse l_response =
            (WEB3AdminTraderAdminIPControlListResponse)l_request.createResponse();

        //(*)プロパティセット
        //(*)レスポンスデータに以下の通りプロパティをセットする。
        //　@レスポンスデータ.表示ページ番号    ＝　@getPageIndex()の戻り値
        l_response.pageIndex = l_intPageIndex + "";
        //　@レスポンスデータ.総ページ数      ＝　@getTotalPages()の戻り値
        l_response.totalPages = l_intTotalPages + "";
        //　@レスポンスデータ.総レコード数     ＝　@getTotalRecords()の戻り値
        l_response.totalRecords = l_intTotalRecords + "";
        //　@レスポンスデータ.ログイン拒否IP一覧 ＝　@ログイン拒否IP情報の配列
        l_response.ipControlList = l_adminIPControlReferenceUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get登録画面)<BR>
     * ログイン拒否IP登録画面表示処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）get登録画面」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE28EE01D7
     */
    protected WEB3AdminTraderAdminIPControlRegistInputResponse getRegistScreen(
        WEB3AdminTraderAdminIPControlRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getRegistScreen(WEB3AdminTraderAdminIPControlRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlRegistInputResponse l_response =
            (WEB3AdminTraderAdminIPControlRegistInputResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate登録)<BR>
     * ログイン拒否IP登録確認処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）validate登録」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録確認リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE28EF0264
     */
    protected WEB3AdminTraderAdminIPControlRegistConfirmResponse validateRegist(
        WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateRegist(WEB3AdminTraderAdminIPControlRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response =
            (WEB3AdminTraderAdminIPControlRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit登録)<BR>
     * ログイン拒否IP登録完了処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）submit登録」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録完了リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29800362
     */
    protected WEB3AdminTraderAdminIPControlRegistCompleteResponse submitRegist(
        WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitRegist(WEB3AdminTraderAdminIPControlRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかのチェックを行う。
        //[validate取引パスワード()に指定する引数]
        //パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //get証券会社コード( )
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get管理者コード( )
        //管理者コードを取得する。
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //saveLoginRejectIP(管理者・ログイン拒否IP登録完了リクエスト, String, String)
        //ログイン拒否IPテーブルにレコードを作成する。
        //[saveログイン拒否IP()に指定する引数]
        //リクエストデータ：　@リクエストデータ
        //証券会社コード：　@get証券会社コード()の戻り値
        //管理者コード：　@get管理者コード()の戻り値
        saveLoginRejectIP(l_request, l_strInstitutionCode, l_strAdministratorCode);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response =
            (WEB3AdminTraderAdminIPControlRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get変更画面)<BR>
     * ログイン拒否IP登録情報変更画面表示処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）get変更画面」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報変更リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29D802FC
     */
    protected WEB3AdminTraderAdminIPControlUpdateInputResponse getChangedScreen(
        WEB3AdminTraderAdminIPControlUpdateInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getChangedScreen(WEB3AdminTraderAdminIPControlUpdateInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //getLoginRejectIPParams(String, boolean)
        //条件に該当するログイン拒否IPParamsを返却する。
        //[getログイン拒否IP行()に指定する引数]
        //ログイン拒否ID： リクエストデータ.ログイン拒否ID
        //ロックフラグ： false
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, false);

        //createLoginRejectIPList(ログイン拒否IPParams)
        //ログイン拒否IP情報メッセージデータを作成する。
        //[createログイン拒否IP情報()に指定する引数]
        //ログイン拒否IP行 ： getログイン拒否IP行()の戻り値
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlUpdateInputResponse l_response =
            (WEB3AdminTraderAdminIPControlUpdateInputResponse)l_request.createResponse();

        //(*)プロパティセット
        //(*)レスポンスデータに以下の通りプロパティをセットする。
        //　@レスポンスデータ.更新前情報　@＝　@createログイン拒否IP情報()の戻り値
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * ログイン拒否IP登録情報変更確認処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）validate変更」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報変更確認リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29D8030C
     */
    protected WEB3AdminTraderAdminIPControlUpdateConfirmResponse validateChange(
        WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChange(WEB3AdminTraderAdminIPControlUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //ggetログイン拒否IP行(String, boolean)
        //条件に該当するログイン拒否IPParamsを返却する。
        //[getログイン拒否IP行()に指定する引数]
        //ログイン拒否ID： リクエストデータ.ログイン拒否ID
        //ロックフラグ： false
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, false);

        //validate変更有無(ログイン拒否IPParams, String, Date)
        //入力内容に変更があるかチェックを行う。
        //[validate変更有無()に指定する引数]
        //ログイン拒否IP行： getログイン拒否IP行()の戻り値
        //ステータス： リクエストデータ.ステータス
        //適用終了日時： リクエストデータ.適用終了日時
        validateIsChange(l_loginRejectIpParams, l_request.status, l_request.endDate);

        //createログイン拒否IP情報(ログイン拒否IPParams)
        //ログイン拒否IP情報メッセージデータを作成する。
        //[createログイン拒否IP情報()に指定する引数]
        //ログイン拒否IP行 ： getログイン拒否IP行()の戻り値
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response =
            (WEB3AdminTraderAdminIPControlUpdateConfirmResponse)l_request.createResponse();

        //(*)プロパティセット
        //(*)レスポンスデータに以下の通りプロパティをセットする。
        //　@レスポンスデータ.更新前情報　@＝　@createログイン拒否IP情報()の戻り値
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更)<BR>
     * ログイン拒否IP登録情報変更完了処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）submit変更」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報変更完了リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE29D8031C
     */
    protected WEB3AdminTraderAdminIPControlUpdateCompleteResponse submitChange(
        WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitChange(WEB3AdminTraderAdminIPControlUpdateCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかのチェックを行う。
        //[validate取引パスワード()に指定する引数]
        //パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //get管理者コード( )
        //管理者コードを取得する。
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //ggetログイン拒否IP行(String, boolean)
        //条件に該当するログイン拒否IPParamsを返却する。
        //[getログイン拒否IP行()に指定する引数]
        //ログイン拒否ID： リクエストデータ.ログイン拒否ID
        //ロックフラグ： true
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, true);

        //validate変更有無(ログイン拒否IPParams, String, Date)
        //入力内容に変更があるかチェックを行う。
        //[validate変更有無()に指定する引数]
        //ログイン拒否IP行： getログイン拒否IP行()の戻り値
        //ステータス： リクエストデータ.ステータス
        //適用終了日時： リクエストデータ.適用終了日時
        validateIsChange(l_loginRejectIpParams, l_request.status, l_request.endDate);

        //createログイン拒否IP情報(ログイン拒否IPParams)
        //ログイン拒否IP情報メッセージデータを作成する。
        //[createログイン拒否IP情報()に指定する引数]
        //ログイン拒否IP行 ： getログイン拒否IP行()の戻り値
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //Mapオブジェクトを生成する（内容は下記を参照）。
        //DB更新仕様「ログイン拒否IP登録情報変更_ログイン拒否IPDB更新仕様.xls」
        Map l_mapUpdateContents = new HashMap();
        //ステータス
        //リクエストデータ.ステータス
        l_mapUpdateContents.put("status", l_request.status);
        //適用終了日時
        //リクエストデータ.適用終了日時
        l_mapUpdateContents.put("appli_end_timestamp", l_request.endDate);
        //更新区分
        //"1"（管理者）
        l_mapUpdateContents.put("updated_div", WEB3UpdatedDivDef.MANAGER);
        //更新者コード
        //get管理者コード()の戻り値
        l_mapUpdateContents.put("last_updater", l_strAdministratorCode);
        //更新日付
        //処理日時
        l_mapUpdateContents.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //updateログイン拒否IP(String, Map)
        //ログイン拒否IPテーブルのレコードを更新する。
        //[updateログイン拒否IP()に指定する引数]
        //ログイン拒否ID： リクエストデータ.ログイン拒否ID
        //更新内容： 生成したMapオブジェクト
        updateLoginRejectIP(l_request.denyLoginID, l_mapUpdateContents);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response =
            (WEB3AdminTraderAdminIPControlUpdateCompleteResponse)l_request.createResponse();

        //(*)プロパティセット
        //(*)レスポンスデータに以下の通りプロパティをセットする。
        //　@レスポンスデータ.更新前情報　@＝　@createログイン拒否IP情報()の戻り値
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate削除)<BR>
     * ログイン拒否IP登録情報削除確認処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）validate削除」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報削除確認リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlDeleteConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE2A890000
     */
    protected WEB3AdminTraderAdminIPControlDeleteConfirmResponse validateDelete(
        WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateDelete(WEB3AdminTraderAdminIPControlDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //ggetログイン拒否IP行(String, boolean)
        //条件に該当するログイン拒否IPParamsを返却する。
        //[getログイン拒否IP行()に指定する引数]
        //ログイン拒否ID： リクエストデータ.ログイン拒否ID
        //ロックフラグ： false
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, false);

        //createログイン拒否IP情報(ログイン拒否IPParams)
        //ログイン拒否IP情報メッセージデータを作成する。
        //[createログイン拒否IP情報()に指定する引数]
        //ログイン拒否IP行 ： getログイン拒否IP行()の戻り値
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response =
            (WEB3AdminTraderAdminIPControlDeleteConfirmResponse)l_request.createResponse();

        //(*)プロパティセット
        //(*)レスポンスデータに以下の通りプロパティをセットする。
        //　@レスポンスデータ.更新前情報　@＝　@createログイン拒否IP情報()の戻り値
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit削除)<BR>
     * ログイン拒否IP登録情報削除完了処理を行う。<BR>
     * <BR>
     * 以下のシーケンス図を参照<BR>
     * 「（管理者ログイン拒否IP管理）submit削除」<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情削除消完了リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlDeleteCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 48BE2A89000F
     */
    protected WEB3AdminTraderAdminIPControlDeleteCompleteResponse submitDelete(
        WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitDelete(WEB3AdminTraderAdminIPControlDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： "C1301"
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.IP_LOGIN_FREQUENCY_LIST, true);

        //validate取引パスワード(パスワード : String)
        //取引パスワードが正しいかのチェックを行う。
        //[validate取引パスワード()に指定する引数]
        //パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //get管理者コード( )
        //管理者コードを取得する。
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //ggetログイン拒否IP行(String, boolean)
        //条件に該当するログイン拒否IPParamsを返却する。
        //[getログイン拒否IP行()に指定する引数]
        //ログイン拒否ID： リクエストデータ.ログイン拒否ID
        //ロックフラグ： true
        LoginRejectIpParams l_loginRejectIpParams =
            getLoginRejectIPParams(l_request.denyLoginID, true);

        //createログイン拒否IP情報(ログイン拒否IPParams)
        //ログイン拒否IP情報メッセージデータを作成する。
        //[createログイン拒否IP情報()に指定する引数]
        //ログイン拒否IP行 ： getログイン拒否IP行()の戻り値
        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            createLoginRejectIPList(l_loginRejectIpParams);

        //Mapオブジェクトを生成する（内容は下記を参照）。
        //DB更新仕様「ログイン拒否IP登録情報削除_ログイン拒否IPDB更新仕様.xls」
        Map l_mapUpdateContents = new HashMap();
        //ステータス
        //"1"（無効)
        l_mapUpdateContents.put("status", WEB3LoginRejectIpStatusDef.INEFFECTIVE);
        //更新区分
        //"1"（管理者）
        l_mapUpdateContents.put("updated_div", WEB3UpdatedDivDef.MANAGER);
        //更新者コード
        //get管理者コード()の戻り値
        l_mapUpdateContents.put("last_updater", l_strAdministratorCode);
        //更新日付
        //処理日時
        l_mapUpdateContents.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //updateログイン拒否IP(String, Map)
        //ログイン拒否IPテーブルのレコードを更新する。
        //[updateログイン拒否IP()に指定する引数]
        //ログイン拒否ID： リクエストデータ.ログイン拒否ID
        //更新内容： 生成したMapオブジェクト
        updateLoginRejectIP(l_request.denyLoginID, l_mapUpdateContents);

        //createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response =
            (WEB3AdminTraderAdminIPControlDeleteCompleteResponse)l_request.createResponse();

        //(*)プロパティセット
        //(*)レスポンスデータに以下の通りプロパティをセットする。
        //　@レスポンスデータ.更新前情報　@＝　@createログイン拒否IP情報()の戻り値
        l_response.beforeUpdateInfo = l_adminIPControlReferenceUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索ソート条件)<BR>
     * ログイン拒否IP一覧ソート条件を作成する。<BR>
     * <BR>
     * １）　@ソート条件文字列オブジェクト(：String)を作成する。<BR>
     * <BR>
     * ２）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * <BR>
     * 　@２－１）　@ソートキー.キー項目＝「IPアドレス」の場合、<BR>
     * 　@　@以下の文字列を作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@"NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') ||<BR>
     * 　@　@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1,<BR>
     * 　@　@　@INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') ||<BR>
     * 　@　@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1,<BR>
     * 　@　@　@INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') ||<BR>
     * 　@　@ NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')"<BR>
     * <BR>
     * 　@２－２）　@２－１）以外の場合、ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「適用開始日時」 →　@ログイン拒否IP.適用開始日時<BR>
     * 　@　@・「ステータス」 →　@ログイン拒否IP.ステータス<BR>
     * <BR>
     * 　@２－３）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ３）　@作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキークラス。<BR>
     * @@return String
     * @@roseuid 48BE549C01A9
     */
    private String createQuerySortCond(WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createQuerySortCond(WEB3AdminTraderAdminIPControlSortKey[])";
        log.entering(STR_METHOD_NAME);

        //１）　@ソート条件文字列オブジェクト(：String)を作成する。
        StringBuffer l_sbQuerySortCond = new StringBuffer();

        String l_strSortCond =
            " NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') ||" +
            " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') ||" +
            " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') ||" +
            " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')";

        //２）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {

            //２－１）　@ソートキー.キー項目＝「IPアドレス」の場合、
            if (WEB3AdminTMKeyItemDef.IP_ADDRESS.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuerySortCond.append(l_strSortCond);
            }
            //・「適用開始日時」 →　@ログイン拒否IP.適用開始日時
            else if (WEB3AdminTMKeyItemDef.START_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuerySortCond.append(" appli_start_timestamp ");
            }
            //・「ステータス」 →　@ログイン拒否IP.ステータス
            else if (WEB3AdminTMKeyItemDef.STATUS.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuerySortCond.append(" status ");
            }

            //２－３）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbQuerySortCond.append("Asc");
            }
            else
            {
                l_sbQuerySortCond.append("Desc");
            }

            if (i < l_intLength - 1)
            {
                l_sbQuerySortCond.append(",");
            }
        }

        //３）　@作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQuerySortCond.toString();
    }

    /**
     * (getログイン拒否IP一覧)<BR>
     * ログイン拒否IPテーブルからレコードを取得する。<BR>
     * <BR>
     * １） QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@ arg0：　@ログイン拒否IPRowType<BR>
     * 　@　@ arg1：　@"institution_code=? and status in (0, 2)"<BR>
     * 　@　@ arg2：　@(引数)検索ソート条件<BR>
     * 　@　@ arg3：　@null<BR>
     * 　@　@ arg4：　@(引数)証券会社コード<BR>
     * 　@　@ arg5：　@(引数)ページ内表示行数<BR>
     * 　@　@ arg6：　@(引数)要求ページ番号 - 1<BR>
     * <BR>
     * ※検索結果が0件の場合、エラーを返却する。<BR>
     * エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037））<BR>
     * <BR>
     * ２） １） の戻り値を返却する。<BR>
     * @@param l_strQuerySortCond - (検索ソート条件)<BR>
     * 検索ソート条件<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPageSize - (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     * @@param l_strPageIndex - (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 48BE5A8002FD
     */
    private List getLoginRejectIPList(
        String l_strQuerySortCond,
        String l_strInstitutionCode,
        String l_strPageSize,
        String l_strPageIndex) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getLoginRejectIPList(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisLoginRejectIpRows = null;

        try
        {
            //１） QueryProcessor.doFindAllQuery()メソッドをコールする。
            //[doFindAllQuery()にセットするパラメータ]
            //　@ arg0：　@ログイン拒否IPRowType
            //　@ arg1：　@"institution_code=? and status in (0, 2)"
            //　@ arg2：　@(引数)検索ソート条件
            //　@ arg3：　@null
            //　@ arg4：　@(引数)証券会社コード
            //　@ arg5：　@(引数)ページ内表示行数
            //　@ arg6：　@(引数)要求ページ番号 - 1
            String l_strWhere = "institution_code=? and status in (0, 2)";
            Object[] l_objVars = {l_strInstitutionCode};
            l_lisLoginRejectIpRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    LoginRejectIpRow.TYPE,
                    l_strWhere,
                    l_strQuerySortCond,
                    null,
                    l_objVars,
                    Integer.parseInt(l_strPageSize),
                    Integer.parseInt(l_strPageIndex) - 1);
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

        //※検索結果が0件の場合、エラーを返却する。
        //エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037））
        if (l_lisLoginRejectIpRows == null || l_lisLoginRejectIpRows.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //２） １） の戻り値を返却する。
        return l_lisLoginRejectIpRows;
    }

    /**
     * (getログイン拒否IP行)<BR>
     * 条件に該当するログイン拒否IPParamsを返却する。<BR>
     * <BR>
     * １） QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * 　@　@※（引数）ロックフラグ==trueの場合は"FOR UPDATE"を付加する。<BR>
     * <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@ arg0：　@ログイン拒否IPRowType<BR>
     * 　@　@ arg1：　@"login_reject_id=? and status in (0, 2)"<BR>
     * 　@　@ arg2：　@"FOR UPDATE"（（引数）ロックフラグ==trueの場合追加する。）<BR>
     * 　@　@ arg3：　@(引数)ログイン拒否ID<BR>
     * <BR>
     * ※検索結果が取得できなかった場合、エラーを返却する。<BR>
     * 　@エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037）<BR>
     * <BR>
     * ２） １） の戻り値を返却する。<BR>
     * @@param l_strDenyLoginID - (ログイン拒否ID)<BR>
     * ログイン拒否ID<BR>
     * @@param l_blnLockFlag - (ロックフラグ)<BR>
     * ロックフラグ<BR>
     * <BR>
     * true：　@行ロックを行う。<BR>
     * false：　@行ロックを行わない。<BR>
     * @@return LoginRejectIpParams
     * @@throws WEB3BaseException
     * @@roseuid 48C5CF32011D
     */
    private LoginRejectIpParams getLoginRejectIPParams(
        String l_strDenyLoginID,
        boolean l_blnLockFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLoginRejectIPParams(String, boolean)";
        log.entering(STR_METHOD_NAME);

        List l_lisLoginRejectIpRows = null;

        try
        {
            String l_strConditions = null;
            //１） QueryProcessor.doFindAllQuery()メソッドをコールする。
            //　@※（引数）ロックフラグ==trueの場合は"FOR UPDATE"を付加する。
            if (l_blnLockFlag)
            {
                l_strConditions = "FOR UPDATE";
            }
            //[doFindAllQuery()にセットするパラメータ]
            //　@ arg0：　@ログイン拒否IPRowType
            //　@ arg1：　@"login_reject_id=? and status in (0, 2)"
            //　@ arg2：　@"FOR UPDATE"（（引数）ロックフラグ==trueの場合追加する。）
            //　@ arg3：　@(引数)ログイン拒否ID
            String l_strWhere = "login_reject_id=? and status in (0, 2)";
            Object[] l_objVars = {l_strDenyLoginID};
            l_lisLoginRejectIpRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    LoginRejectIpRow.TYPE,
                    l_strWhere,
                    l_strConditions,
                    l_objVars);
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

        //※検索結果が取得できなかった場合、エラーを返却する。
        //　@エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037）
        if (l_lisLoginRejectIpRows == null || l_lisLoginRejectIpRows.isEmpty())
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //２） １） の戻り値を返却する。
        LoginRejectIpRow l_loginRejectIPRow =
            (LoginRejectIpRow)l_lisLoginRejectIpRows.get(0);

        LoginRejectIpParams l_loginRejectIPParams =
            new LoginRejectIpParams(l_loginRejectIPRow);

        return l_loginRejectIPParams;
    }

    /**
     * (createログイン拒否IP情報)<BR>
     * ログイン拒否IP情報メッセージデータを作成する。<BR>
     * <BR>
     * １）　@ログイン拒否IP情報オブジェクトの生成<BR>
     * <BR>
     * ２）　@以下の通り、プロパティをセットする。<BR>
     * <BR>
     * 　@　@・　@ログイン拒否IP情報.ログイン拒否ID　@ ＝　@(引数)ログイン拒否IP行.ログイン拒否ID<BR>
     * 　@　@・　@ログイン拒否IP情報.IPアドレス　@   ＝　@(引数)ログイン拒否IP行.IPアドレス<BR>
     * 　@　@・　@ログイン拒否IP情報.ステータス　@    ＝　@(引数)ログイン拒否IP行.ステータス<BR>
     * 　@　@・　@ログイン拒否IP情報.適用開始日時　@   ＝　@(引数)ログイン拒否IP行.適用開始日時<BR>
     * 　@　@・　@ログイン拒否IP情報.適用終了日時　@   ＝　@(引数)ログイン拒否IP行.適用終了日時<BR>
     * 　@　@・　@ログイン拒否IP情報.登録区分　@ ＝　@(引数)ログイン拒否IP行.登録区分<BR>
     * 　@　@・　@ログイン拒否IP情報.更新区分　@ ＝　@(引数)ログイン拒否IP行.更新区分<BR>
     * 　@　@・　@ログイン拒否IP情報.更新者コード　@   ＝　@以下の通り<BR>
     * 　@　@　@　@－(引数)ログイン拒否IP行.更新区分 == "0"(デーモン)の場合、NULL<BR>
     * 　@　@　@　@－(引数)ログイン拒否IP行.更新区分 == "1"(管理者)の場合、<BR>
     * 　@　@　@　@　@　@(引数)ログイン拒否IP行.更新者コード<BR>
     * <BR>
     * ３）　@生成したログイン拒否IP情報オブジェクトを返却する。<BR>
     * @@param l_loginRejectIPParams - (ログイン拒否IP行)<BR>
     * ログイン拒否IP行<BR>
     * @@return WEB3AdminTraderAdminIPControlReferenceUnit
     * @@roseuid 48C5D3B80066
     * @@throws WEB3BaseException
     */
    private WEB3AdminTraderAdminIPControlReferenceUnit createLoginRejectIPList(
        LoginRejectIpParams l_loginRejectIPParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createLoginRejectIPList(LoginRejectIpParams, String, Date)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit =
            new WEB3AdminTraderAdminIPControlReferenceUnit();
        //　@　@・　@ログイン拒否IP情報.ログイン拒否ID　@ ＝　@(引数)ログイン拒否IP行.ログイン拒否ID
        l_adminIPControlReferenceUnit.denyLoginID = String.valueOf(l_loginRejectIPParams.getLoginRejectId());
        //　@　@・　@ログイン拒否IP情報.IPアドレス　@ ＝　@(引数)ログイン拒否IP行.IPアドレス
        l_adminIPControlReferenceUnit.ipAddress = l_loginRejectIPParams.getIpAddress();
        //　@　@・　@ログイン拒否IP情報.ステータス　@ ＝　@(引数)ログイン拒否IP行.ステータス
        l_adminIPControlReferenceUnit.status = l_loginRejectIPParams.getStatus();
        //　@　@・　@ログイン拒否IP情報.適用開始日時　@ ＝　@(引数)ログイン拒否IP行.適用開始日時
        l_adminIPControlReferenceUnit.startDate = l_loginRejectIPParams.getAppliStartTimestamp();
        //　@　@・　@ログイン拒否IP情報.適用終了日時　@ ＝　@(引数)ログイン拒否IP行.適用終了日時
        l_adminIPControlReferenceUnit.endDate = l_loginRejectIPParams.getAppliEndTimestamp();
        //　@　@・　@ログイン拒否IP情報.登録区分　@ ＝　@(引数)ログイン拒否IP行.登録区分
        l_adminIPControlReferenceUnit.registDiv = l_loginRejectIPParams.getRegistDiv();
        //　@　@・　@ログイン拒否IP情報.更新区分　@ ＝　@(引数)ログイン拒否IP行.更新区分
        l_adminIPControlReferenceUnit.updateDiv = l_loginRejectIPParams.getUpdatedDiv();
        //　@　@・　@ログイン拒否IP情報.更新者コード　@ ＝　@以下の通り
        //　@　@　@　@－(引数)ログイン拒否IP行.更新区分 == "0"(デーモン)の場合、NULL
        //　@　@　@　@－(引数)ログイン拒否IP行.更新区分 == "1"(管理者)の場合、(引数)ログイン拒否IP行.更新者コード
        if (WEB3UpdatedDivDef.DAEMON.equals(l_loginRejectIPParams.getUpdatedDiv()))
        {
            l_adminIPControlReferenceUnit.updaterCode = null;
        }
        else if (WEB3UpdatedDivDef.MANAGER.equals(l_loginRejectIPParams.getUpdatedDiv()))
        {
            l_adminIPControlReferenceUnit.updaterCode = l_loginRejectIPParams.getLastUpdater();
        }

        log.exiting(STR_METHOD_NAME);
        return l_adminIPControlReferenceUnit;
    }

    /**
     * (validate変更有無)<BR>
     * 入力内容に変更があるかチェックを行う。<BR>
     * <BR>
     * １）　@以下の全てに該当する場合、例外をthrowする。<BR>
     * 　@　@　@エラーメッセージ「入力内容に変更がありません。」<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          　@tag: BUSINESS_ERROR_03132<BR>
     * <BR>
     * １－１）　@(引数)ログイン拒否IP行.ステータス == (引数)ステータス<BR>
     * <BR>
     * １－２）　@(引数)ログイン拒否IP行.適用終了日時 == (引数)適用終了日時<BR>
     * @@param l_loginRejectIPParams - (ログイン拒否IP行)<BR>
     * ログイン拒否IP行<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@param l_datEndDate - (適用終了日時)<BR>
     * 適用終了日時<BR>
     * @@roseuid 48C626AE0001
     * @@throws WEB3BaseException
     */
    private void validateIsChange(
        LoginRejectIpParams l_loginRejectIPParams,
        String l_strStatus,
        Date l_datEndDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateIsChange(LoginRejectIpParams, String, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_loginRejectIPParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@以下の全てに該当する場合、例外をthrowする。
        //　@　@　@エラーメッセージ「入力内容に変更がありません。」
        //１－１）　@(引数)ログイン拒否IP行.ステータス == (引数)ステータス
        //１－２）　@(引数)ログイン拒否IP行.適用終了日時 == (引数)適用終了日時
        if (l_loginRejectIPParams.getStatus().equals(l_strStatus)
            && WEB3DateUtility.compareToSecond(l_loginRejectIPParams.getAppliEndTimestamp(), l_datEndDate) == 0)
        {
            log.debug("入力内容に変更がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03132,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入力内容に変更がありません。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (saveログイン拒否IP)<BR>
     * ログイン拒否IPテーブルにレコードを作成する。<BR>
     * <BR>
     * DB更新仕様「ログイン拒否IP登録_ログイン拒否IPDB更新仕様.xls」を参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@roseuid 48C0E2DB0057
     * @@throws WEB3BaseException
     */
    private void saveLoginRejectIP(
        WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request,
        String l_strInstitutionCode,
        String l_strAdministratorCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "saveLoginRejectIP(WEB3AdminTraderAdminIPControlRegistCompleteRequest, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            LoginRejectIpParams l_loginRejectIpParams = new LoginRejectIpParams();

            //ログイン拒否ID  xTradeにより自動採番された値
            l_loginRejectIpParams.setLoginRejectId(LoginRejectIpDao.newPkValue());

            //証券会社コード   引数.証券会社コード
            l_loginRejectIpParams.setInstitutionCode(l_strInstitutionCode);

            //IPアドレス        リクエストデータ.IPアドレス
            l_loginRejectIpParams.setIpAddress(l_request.ipAddress);

            //ステータス     リクエストデータ.ステータス
            l_loginRejectIpParams.setStatus(l_request.status);

            //適用開始日時    リクエストデータ.適用開始日時
            l_loginRejectIpParams.setAppliStartTimestamp(l_request.startDate);

            //適用終了日時    リクエストデータ.適用終了日時
            l_loginRejectIpParams.setAppliEndTimestamp(l_request.endDate);

            //登録区分      "1"（管理者）
            l_loginRejectIpParams.setRegistDiv(WEB3LoginRegistDivDef.MANAGER);

            //更新区分      "1"（管理者）
            l_loginRejectIpParams.setUpdatedDiv(WEB3UpdatedDivDef.MANAGER);

            //更新者コード  引数.管理者コード
            l_loginRejectIpParams.setLastUpdater(l_strAdministratorCode);

            //作成日付      処理日時
            l_loginRejectIpParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

            //更新日付      処理日時
            l_loginRejectIpParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_loginRejectIpParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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
     * (updateログイン拒否IP)<BR>
     * ログイン拒否IPテーブルのレコードを更新する。<BR>
     * <BR>
     * [条件]<BR>
     * 　@ログイン拒否ID　@＝　@(引数)ログイン拒否ID<BR>
     * @@param l_strLoginRejectId - (ログイン拒否ID)<BR>
     * ログイン拒否ID<BR>
     * @@param l_mapUpdateContents - (更新内容)<BR>
     * 更新内容<BR>
     * @@roseuid 48C6312F01E4
     * @@throws WEB3BaseException
     */
    private void updateLoginRejectIP(
        String l_strLoginRejectId,
        Map l_mapUpdateContents)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateLoginRejectIP(String, Map)";
        log.entering(STR_METHOD_NAME);

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //[条件]
            //ログイン拒否ID　@＝　@(引数)ログイン拒否ID
            String l_strWhere = "login_reject_id = ?";
            Object[] l_objWhere = {l_strLoginRejectId};
            l_processor.doUpdateAllQuery(
                LoginRejectIpRow.TYPE,
                l_strWhere,
                l_objWhere,
                l_mapUpdateContents);
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
}@
