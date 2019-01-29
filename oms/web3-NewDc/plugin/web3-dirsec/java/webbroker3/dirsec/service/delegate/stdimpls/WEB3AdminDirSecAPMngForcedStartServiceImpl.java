head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者下り処理強制起動サービスImpl(WEB3AdminDirSecAPMngForcedStartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/21  劉剣 (中訊) 新規作成モデル 132
Revision History : 2008/07/23  劉剣 (中訊) モデル 134
Revision History : 2008/07/23  劉剣 (中訊) モデル 135
Revision History : 2008/07/30  劉剣 (中訊) モデル 136
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

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
import webbroker3.dirsec.data.ApManagementParams;
import webbroker3.dirsec.data.ApManagementRow;
import webbroker3.dirsec.define.WEB3AdminDirSecSortKeyItemDef;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInfoUnit;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartSortKey;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者下り処理強制起動サービスImpl)<BR>
 * 管理者下り処理強制起動サービス実装クラス<BR>
 *
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartServiceImpl implements WEB3AdminDirSecAPMngForcedStartService
{

    /**
     * ログ出力ユーティリティ。
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminDirSecAPMngForcedStartServiceImpl.class);

    /**
     * @@roseuid 488437FD0288
     */
    public WEB3AdminDirSecAPMngForcedStartServiceImpl()
    {

    }

    /**
     * 管理者・下り処理強制起動を開始する。<BR>
     * <BR>
     * リクエストデータの型により、 以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・下り処理一覧リクエストの場合 <BR>
     * 　@this.get下り処理一覧()をコールする。 <BR>
     * <BR>
     * ○管理者・下り処理強制起動入力リクエストの場合 <BR>
     * 　@this.get下り処理強制起動入力()をコールする。<BR>
     * <BR>
     * ○管理者・下り処理強制起動確認リクエストの場合 <BR>
     * 　@this.validate下り処理強制起動確認()をコールする。<BR>
     * <BR>
     * ○管理者・下り処理強制起動完了リクエストの場合 <BR>
     * 　@this.submit下り処理強制起動完了()をコールする。<BR>
     * @@param l_request - リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201DF
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
        
        //○管理者・下り処理一覧リクエストの場合
        //this.get下り処理一覧()をコールする。
        if (l_request instanceof WEB3AdminDirSecAPMngListRequest)
        {
            l_response = this.getAPMngList(
                (WEB3AdminDirSecAPMngListRequest)l_request);
        }

        //○管理者・下り処理強制起動入力リクエストの場合
        //this.get下り処理強制起動入力()をコールする。
        else if (l_request instanceof WEB3AdminDirSecAPMngForcedStartInpRequest)
        {
            l_response = this.getAPMngForcedStartInp(
                (WEB3AdminDirSecAPMngForcedStartInpRequest)l_request);
        }

        //○管理者・下り処理強制起動確認リクエストの場合
        //this.validate下り処理強制起動確認()をコールする。
        else if (l_request instanceof WEB3AdminDirSecAPMngForcedStartCnfRequest)
        {
            l_response = this.validateAPMngForcedStartCnf(
                (WEB3AdminDirSecAPMngForcedStartCnfRequest)l_request);
        }

        //○管理者・下り処理強制起動完了リクエストの場合
        //this.submit下り処理強制起動完了()をコールする。
        else if (l_request instanceof WEB3AdminDirSecAPMngForcedStartCmpRequest)
        {
            l_response = this.submitAPMngForcedStartCmp(
                (WEB3AdminDirSecAPMngForcedStartCmpRequest)l_request);
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
     * (get下り処理一覧)<BR>
     * 管理者下り処理一覧画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）get下り処理一覧」参照。 <BR>
     * ======================================================== <BR>
     * シーケンス図 ：管理者・下り処理強制起動 / （管理者）get下り処理一覧 <BR>
     * 具体位置：isDIR管理者( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：管理者・下り処理強制起動 / （管理者）get下り処理一覧 <BR>
     * 具体位置：doFindAllQuery(rowType : RowType, where : String,<BR>
     * 　@　@　@　@orderBy : String, condition : String, リスト : Object[],<BR>
     * 　@　@　@　@ページサイズ : int, ページ番号 : int)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00398 <BR>
     * ======================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理一覧リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngListResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201E1
     */
    protected WEB3AdminDirSecAPMngListResponse getAPMngList(WEB3AdminDirSecAPMngListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAPMngList(WEB3AdminDirSecAPMngListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："Z0101"(システム管理.下り処理強制起動)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //ArrayListオブジェクトの生成
        List l_lisArrays = new ArrayList();

        try
        {
            //createAP下り処理管理ソート条件(ソートキー［］)
            //[createAP下り処理管理ソート条件()に指定する引数]
            //ソートキー　@：　@リクエストオブジェクト.ソートキー
            String l_strAPMngForcedStartSortKey =
                this.createAPMngManageSortCondition(l_request.sortKeys);

            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisArrays = l_queryProcessor.doFindAllQuery(
                ApManagementRow.TYPE,
                null,
                l_strAPMngForcedStartSortKey,
                null,
                null,
                Integer.parseInt(l_request.pageSize),
                Integer.parseInt(l_request.pageIndex) - 1);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
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

        //レコードが取得できない場合、例外をthrowする。
        if (l_lisArrays.size() == 0)
        {
            log.debug("該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        WEB3PageIndexInfo l_pageInfo = null;

        l_pageInfo = new WEB3PageIndexInfo(
            l_lisArrays,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        //ArrayList()
        List l_lisaPMngForcedStartInfoUnit = new ArrayList();

        int l_intLength = l_lisArrays.size();
        
        //取得したレコード分LOOP処理を行う。
        for (int i = 0; i < l_intLength; i++)
        {
            //下り処理情報()
            WEB3AdminDirSecAPMngForcedStartInfoUnit l_aPMngForcedStartInfoUnit =
                new WEB3AdminDirSecAPMngForcedStartInfoUnit();

            //(*1)プロパティセット
            //(*1)以下の通りプロパティをセット。
            ApManagementParams l_apManagementParams =
                (ApManagementParams)l_lisArrays.get(i);

            //下り処理情報.伝票コード    ＝ AP下り処理管理Params.伝票コード
            l_aPMngForcedStartInfoUnit.requestCode = l_apManagementParams.getRequestCode();
                
            //下り処理情報.PTYPE    ＝ AP下り処理管理Params.PTYPE
            l_aPMngForcedStartInfoUnit.pType = l_apManagementParams.getPtype();

            //下り処理情報.AP下り処理名  ＝ AP下り処理管理Params.AP下り処理名
            l_aPMngForcedStartInfoUnit.apName = l_apManagementParams.getApName();

            //下り処理情報.識別コード有無区分＝AP下り処理管理Params.識別コード有無区分
            l_aPMngForcedStartInfoUnit.requestNumberDiv = l_apManagementParams.getOrderRequestNumberDiv();

            //下り処理情報.スレッド番号有無区分＝AP下り処理管理Params.スレッド番号有無区分
            l_aPMngForcedStartInfoUnit.threadNumberDiv = l_apManagementParams.getThreadNumberDiv();

            //add(arg0 : Object)
            //[引数]
            //arg0： 下り処理情報オブジェクト
            l_lisaPMngForcedStartInfoUnit.add(l_aPMngForcedStartInfoUnit);
        }

        //toArray()
        WEB3AdminDirSecAPMngForcedStartInfoUnit[] l_AadminDirSecAPMngForcedStartInfoUnits =
            new WEB3AdminDirSecAPMngForcedStartInfoUnit[l_lisaPMngForcedStartInfoUnit.size()];
        l_lisaPMngForcedStartInfoUnit.toArray(l_AadminDirSecAPMngForcedStartInfoUnits);

        //getTotalPages()
        int l_intTotalPages = l_pageInfo.getTotalPages();

        //getTotalRecords()
        int l_intTotalRecords = l_pageInfo.getTotalRecords();

        //createResponse()
        WEB3AdminDirSecAPMngListResponse l_response =
            (WEB3AdminDirSecAPMngListResponse)l_request.createResponse();

        //(*2)プロパティセット
        //(*2)レスポンスデータに以下の内容をセットする。
        //総ページ数   ＝ WEB3StringTypeUtility.formatNumber(totalPages()の戻り値)
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_intTotalPages);

        //総レコード数  ＝ WEB3StringTypeUtility.formatNumber(totalSize()の戻り値)
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_intTotalRecords);

        //表示ページ番号 ＝ リクエストオブジェクト.表示ページ番号
        l_response.pageIndex = l_request.pageIndex;

        //下り処理情報一覧    ＝ 下り処理情報の配列
        l_response.apMngInfoList = l_AadminDirSecAPMngForcedStartInfoUnits;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get下り処理強制起動入力)<BR>
     * 管理者下り処理強制起動入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）get下り処理強制起動入力」参照。 <BR>
     * ======================================================== <BR>
     * シーケンス図 ：管理者・下り処理強制起動 / （管理者）get下り処理強制起動入力 <BR>
     * 具体位置：isDIR管理者( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理強制起動入力リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 487C46780308
     */
    protected WEB3AdminDirSecAPMngForcedStartInpResponse getAPMngForcedStartInp(
        WEB3AdminDirSecAPMngForcedStartInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAPMngList(WEB3AdminDirSecAPMngListRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："Z0101"(システム管理.下り処理強制起動)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //AP下り処理管理テーブルより条件に該当するレコードを検索し、
        //[validateAP下り処理管理()に指定する引数]
        //PTYPE：リクエストデータ.PTYPE
        this.validateAPMngManage(l_request.pType);

        //createResponse()
        WEB3AdminDirSecAPMngForcedStartInpResponse l_response =
            (WEB3AdminDirSecAPMngForcedStartInpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate下り処理強制起動確認)<BR>
     * 管理者下り処理強制起動確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）validate下り処理強制起動確認」参照。 <BR>
     * ======================================================== <BR>
     * シーケンス図 ：管理者・下り処理強制起動 / （管理者）submit下り処理強制起動完了 <BR>
     * 具体位置：isDIR管理者( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理強制起動確認リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201E7
     */
    protected WEB3AdminDirSecAPMngForcedStartCnfResponse validateAPMngForcedStartCnf(
        WEB3AdminDirSecAPMngForcedStartCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAPMngForcedStartCnf(WEB3AdminDirSecAPMngForcedStartCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："Z0101"(システム管理.下り処理強制起動)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //AP下り処理管理テーブルより条件に該当するレコードを検索し、
        //[validateAP下り処理管理()に指定する引数]
        //PTYPE：リクエストデータ.PTYPE
        this.validateAPMngManage(l_request.pType);

        //createResponse()
        WEB3AdminDirSecAPMngForcedStartCnfResponse l_response =
            (WEB3AdminDirSecAPMngForcedStartCnfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit下り処理強制起動完了)<BR>
     * 管理者下り処理強制起動完了画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（管理者）submit下り処理強制起動完了」参照。 <BR>
     * ======================================================== <BR>
     * シーケンス図 ：管理者・下り処理強制起動 / （管理者）submit下り処理強制起動完了 <BR>
     * 具体位置：isDIR管理者( )<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_00857 <BR>
     * ======================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・下り処理強制起動完了リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAPMngForcedStartCmpResponse
     * @@throws WEB3BaseException
     * @@roseuid 487596B201EE
     */
    protected WEB3AdminDirSecAPMngForcedStartCmpResponse submitAPMngForcedStartCmp(
        WEB3AdminDirSecAPMngForcedStartCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAPMngForcedStartCmp(WEB3AdminDirSecAPMngForcedStartCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："Z0101"(システム管理.下り処理強制起動)
        //is更新：true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者()
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //validate取引パスワード(パスワード : String)
        //[validate取引パスワード()に指定する引数]
        //パスワード　@：　@リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //AP下り処理管理テーブルより条件に該当するレコードを検索し、
        //[validateAP下り処理管理()に指定する引数]
        //PTYPE：リクエストデータ.PTYPE
        this.validateAPMngManage(l_request.pType);

        //createResponse()
        WEB3AdminDirSecAPMngForcedStartCmpResponse l_response =
            (WEB3AdminDirSecAPMngForcedStartCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (createAP下り処理管理ソート条件)<BR>
     * AP下り処理管理検索ソート条件を作成する。 <BR>
     * <BR>
     * １）　@ソート条件文字列オブジェクト(：String)を作成する。 <BR>
     * <BR>
     * ２）　@パラメータ.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * <BR>
     * 　@２－１）　@ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@　@　@作成したソート条件文字列に追加する。 <BR>
     * <BR>
     * 　@・「伝票コード」	→　@AP下り処理管理.伝票コード<BR>
     * 　@・「PTYPE」	→　@AP下り処理管理.PTYPE<BR>
     * 　@・「AP下り処理名」	→　@AP下り処理管理.AP下り処理名<BR>
     * <BR>
     * 　@２－２）　@ソートキー.昇順／降順に対応する取得順序(asc or desc)<BR>
     * 　@　@　@　@をソート条件文字列に追加する。 <BR>
     * <BR>
     * ３）　@作成したソート条件文字列を返却する。 <BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキークラス。<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 487F0087016B
     */
    private String createAPMngManageSortCondition(WEB3AdminDirSecAPMngForcedStartSortKey[] l_sortKeys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHostManageSortCondition(WEB3AdminDirSecAPMngForcedStartSortKey[])";
        log.entering(STR_METHOD_NAME);

        //ソート条件文字列オブジェクト(：String)を作成する。
        StringBuffer l_sbSortQueryString = new StringBuffer();

        //パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //ソートキー.キー項目を対応する列物理名に変換し、
            //作成したソート条件文字列に追加する。
            //・「伝票コード」  →　@AP下り処理管理.伝票コード
            if (WEB3AdminDirSecSortKeyItemDef.REQUEST_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("request_code ");
            }
            //・「PTYPE」  →　@AP下り処理管理.PTYPE
            else if (WEB3AdminDirSecSortKeyItemDef.PTYTE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("ptype ");
            }
            //・「AP下り処理名」    →　@AP下り処理管理.AP下り処理名
            else if (WEB3AdminDirSecSortKeyItemDef.AP_NAME.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortQueryString.append("ap_name ");
            }
            else 
            {
                continue;
            }

            //ソートキー.昇順／降順に対応する取得順序(asc or desc)
            //をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("asc,");
            }
            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortQueryString.append("desc,");
            }
        }

        //作成したソート条件文字列を返却する。
        String l_strSortString = l_sbSortQueryString.toString();

        if ((!"".equals(l_strSortString)) && l_strSortString.charAt(l_strSortString.length() - 1) == ',')
        {
            l_strSortString = l_strSortString.substring(0, l_strSortString.length() - 1);
        }

        log.exiting(STR_METHOD_NAME);

        return l_strSortString;
    }

    /**
     * (validateAP下り処理管理)<BR>
     * AP下り処理管理テーブルより条件に該当するレコードを検索し、<BR>
     * データが取得できない場合、例外をthrowする。<BR>
     * <BR>
     * １）　@AP下り処理管理テーブル検索 <BR>
     * 　@以下の条件で、AP下り処理管理テーブルを検索する。<BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@　@PTYPE：　@引数.PTYPE<BR>
     * <BR>
     * ２）　@レコードが取得できない場合、例外をthrowする。<BR>
     * 　@　@エラーメッセージ「BUSINESS_ERROR_00398（該当するデータが存<BR>
     * 在しません。）」<BR>
     * @@param l_strPtype - (PTYPE)<BR>
     * PTYPE。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 487716D701B8
     */
    private void validateAPMngManage(String l_strPtype) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAPMngManage(String)";
        log.entering(STR_METHOD_NAME);

        //検索条件文字列の生成
        String l_strWhere = " ptype = ? ";

        //検索条件コンテナの生成
        Object[] l_values = {l_strPtype};

        //ArrayListオブジェクトの生成
        List l_lisArrays = new ArrayList();

        try
        {
            //AP下り処理管理テーブル検索
            //以下の条件で、AP下り処理管理テーブルを検索する。
            //[検索条件]
            //PTYPE：　@引数.PTYPE
            l_lisArrays =
                Processors.getDefaultProcessor().doFindAllQuery(
                    ApManagementRow.TYPE,
                    l_strWhere,
                    l_values);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
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

        //レコードが取得できない場合、例外をthrowする。
        //エラーメッセージ「BUSINESS_ERROR_00398（該当するデータが存在しません。）」
        if (l_lisArrays.isEmpty())
        {
            log.debug("該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
