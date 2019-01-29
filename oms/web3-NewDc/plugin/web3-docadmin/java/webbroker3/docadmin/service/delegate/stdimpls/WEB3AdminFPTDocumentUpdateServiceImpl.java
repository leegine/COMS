head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTDocumentUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面更新サービスImpl(WEB3AdminFPTDocumentUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.039
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTBatoProductCodeManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.define.WEB3AdminFPTProcessFlagDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付書面更新サービスImpl)<BR>
 * 管理者金商法@交付書面更新サービス実装クラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentUpdateServiceImpl implements WEB3AdminFPTDocumentUpdateService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateServiceImpl.class);

    /**
     * @@roseuid 47CBC5AD00CB
     */
    public WEB3AdminFPTDocumentUpdateServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 金商法@交付書面更新処理を実施する。<BR>
     * <BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付書面更新入力リクエストの場合  <BR>
     * 　@－get入力画面()をコールする。  <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付書面更新確認リクエストの場合  <BR>
     * 　@－validate変更画面()をコールする。  <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付書面更新完了リクエストの場合  <BR>
     * 　@－submit変更画面()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C25F800354
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
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者金商法@交付書面更新入力リクエストの場合 
        if (l_request instanceof WEB3AdminFPTDocumentUpdateInputRequest)
        {
            l_response = this.getInputScreen(
                (WEB3AdminFPTDocumentUpdateInputRequest)l_request);
        }
        //引数のリクエストデータが、管理者金商法@交付書面更新確認リクエストの場合
        else if (l_request instanceof WEB3AdminFPTDocumentUpdateConfirmRequest)
        {
            l_response = this.validateChangedScreen(
                (WEB3AdminFPTDocumentUpdateConfirmRequest)l_request);
        }
        //引数のリクエストデータが、管理者金商法@交付書面更新完了リクエストの場合
        else if (l_request instanceof WEB3AdminFPTDocumentUpdateCompleteRequest)
        {
            l_response = this.submitChangedScreen(
                (WEB3AdminFPTDocumentUpdateCompleteRequest)l_request);
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
     * (get入力画面)<BR>
     * 金商法@交付書面更新入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「get入力画面」参照。 <BR>
     * @@param l_request - 管理者金商法@交付書面更新入力リクエストデータオブジェクト
     * @@return WEB3AdminFPTDocumentUpdateInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26DA3002A
     */
    protected WEB3AdminFPTDocumentUpdateInputResponse getInputScreen(
        WEB3AdminFPTDocumentUpdateInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminFPTDocumentUpdateInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0104"(金商法@交付書面更新照会)
        //is更新：true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, true);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get部店コード( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //書面区分管理(String, String, String, String)(
        //[指定する引数]
        //証券会社コード ： 管理者.get証券会社コード() の戻り値
        //部店コード ： 管理者.get部店コード() の戻り値
        //書面区分 ： null
        //書面種類コード ： null
        WEB3AdminFPTDocDivManagement l_adminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(l_strInstitutionCode, l_strBranchCode, null, null);

       //get書面区分管理一覧()
        WEB3FPTDocumentDivAdminInfoUnit[] l_fptDocumentDivAdminInfoUnits =
            l_adminFPTDocDivManagement.getDocDivManagementLists();

        //createResponse( )
        WEB3AdminFPTDocumentUpdateInputResponse l_response =
            (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();

        //プロパティセット
        l_response.documentDivList = l_fptDocumentDivAdminInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更画面)<BR>
     * 金商法@交付書面更新確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「validate変更画面」参照。<BR>
     * @@param l_request - 管理者金商法@交付書面更新確認リクエストデータオブジェクト
     * @@return WEB3AdminFPTDocumentUpdateConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26DA6004D
     */
    protected WEB3AdminFPTDocumentUpdateConfirmResponse validateChangedScreen(
        WEB3AdminFPTDocumentUpdateConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangedScreen(WEB3AdminFPTDocumentUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )(管理者金商法@交付書面更新確認リクエスト)
        l_request.validate();

        //getInstanceFromログイン情報( )
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0104"(金商法@交付書面更新照会)
        //is更新：true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, true);

        //電子鳩銘柄コード管理オブジェクトを生成する。
        //電子鳩銘柄コード管理(管理者, 書面更新情報[], String)
        //[指定する引数]
        //管理者 = 管理者.getInstanceFromログイン情報()の戻り値
        //書面情報 = リクエストデータ.書面更新情報
        //処理フラグ = リクエストデータ.更新処理フラグ
        WEB3AdminFPTBatoProductCodeManagement l_adminFPTBatoProductCodeManagement =
            new WEB3AdminFPTBatoProductCodeManagement(
                l_administrator,
                l_request.documentUpdateList,
                l_request.updateProcessFlag);

        //電子鳩銘柄コード管理レコードの存在チェックを行う。
        //validate電子鳩銘柄コード管理行( )
        l_adminFPTBatoProductCodeManagement.validateBatoProductManagementParams();

        WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
            (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();

        if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(l_request.updateProcessFlag))
        {
            l_response.batoProductCode = l_request.documentUpdateList[0].documentCategory +
                l_request.documentUpdateList[0].documentNumber;
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit変更画面)<BR>
     * 金商法@交付書面更新完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「submit変更画面」参照。<BR>
     * @@param l_request - 管理者金商法@交付書面更新完了リクエストデータオブジェクト
     * @@return WEB3AdminFPTDocumentUpdateCompleteResponse
     * @@tthrows WEB3BaseException
     * @@roseuid 47C26DA800CB
     */
    protected WEB3AdminFPTDocumentUpdateCompleteResponse submitChangedScreen(
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_MENTHOD_NAME = "submitChangedScreen(WEB3AdminFPTRegistCompleteRequest)";

        log.entering(STR_MENTHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックをする。
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0104"(金商法@交付書面更新照会)
        //is更新：true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, true);

        //validate取引パスワード(パスワード : String)
        // 取引パスワードのチェックを行う。
        // [引数]
        // パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //電子鳩銘柄コード管理(管理者, 書面更新情報[], String)(
        //電子鳩銘柄コード管理オブジェクトを生成する。
        //[指定する引数]
        //管理者 = 管理者.getInstanceFromログイン情報()の戻り値
        //書面情報 = リクエストデータ.書面更新情報
        //処理フラグ = リクエストデータ.更新処理フラグ
        WEB3AdminFPTBatoProductCodeManagement l_adminFPTBatoProductCodeManagement =
            new WEB3AdminFPTBatoProductCodeManagement(
                l_administrator,
                l_request.documentUpdateList,
                l_request.updateProcessFlag);

        //電子鳩銘柄コード管理レコードの存在チェックを行う。
        //validate電子鳩銘柄コード管理行( )
        l_adminFPTBatoProductCodeManagement.validateBatoProductManagementParams();

        //登録処理（リクエストデータ.更新処理フラグ == 0）の場合

        if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(l_request.updateProcessFlag))
        {
            //insert電子鳩銘柄コード管理( )(
            l_adminFPTBatoProductCodeManagement.insertBatoProductManagement();
        }

        //更新処理（リクエストデータ.更新処理フラグ == 1）の場合
        if (WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(l_request.updateProcessFlag))
        {
            //update電子鳩銘柄コード管理( )
            l_adminFPTBatoProductCodeManagement.updateBatoProductManagement();
        }

        //更新処理（リクエストデータ.更新処理フラグ == 2）の場合
        if (WEB3AdminFPTProcessFlagDivDef.DELETE.equals(l_request.updateProcessFlag))
        {
            //delete電子鳩銘柄コード管理( )
            l_adminFPTBatoProductCodeManagement.deleteBatoProductCodeAdmin();
        }

        //レスポンスデータを生成する。
        WEB3AdminFPTDocumentUpdateCompleteResponse l_response =
            (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();

        log.exiting(STR_MENTHOD_NAME);
        return l_response;
    }
}
@
