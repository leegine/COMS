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
filename	WEB3AdminFPTDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧削除サービスImpl(WEB3AdminFPTDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 武波 (中訊) 新規作成 仕様変更・モデル No.010
Revision History : 2008/01/28 周墨洋 (中訊) 仕様変更・モデルNo.026
Revision History : 2008/03/17 馮海濤 (中訊) モデルNo.048
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagementHistory;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDeleteService;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧削除サービスImpl)<BR>
 * 管理者金商法@交付閲覧削除サービスImplクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTDeleteServiceImpl implements WEB3AdminFPTDeleteService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteServiceImpl.class);

    /**
     * @@roseuid 472FC5B400A4
     */
    public WEB3AdminFPTDeleteServiceImpl()
    {

    }

    /**
     * 金商法@交付閲覧削除処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧削除確認リクエストの場合<BR>
     * 　@－validate変更()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧削除完了リクエストの場合<BR>
     * 　@－submit変更()をコールする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4726CEE5038E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        // １）　@リクエストデータの型により、以下の通りメソッドをコールする。
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

        if (l_request instanceof WEB3AdminFPTDeleteConfirmRequest)
        {
            // 引数のリクエストデータが、管理者金商法@交付閲覧削除確認リクエストの場合
            // validate変更()をコールする。
            l_response =
                this.validateChangedScreen((WEB3AdminFPTDeleteConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTDeleteCompleteRequest)
        {
            // 引数のリクエストデータが、管理者金商法@交付閲覧削除完了リクエストの場合
            // submit変更()をコールする。
            l_response =
                this.submitChangedScreen((WEB3AdminFPTDeleteCompleteRequest)l_request);
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
     * (validate変更画面)<BR>
     * 金商法@交付閲覧削除確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate変更画面」参照。<BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 書面交付管理行が取得できない場合、例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02952<BR>
     * =============================================== <BR>
     * @@param l_request - (l_request)<BR>
     * 管理者金商法@交付閲覧削除確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminFPTDeleteConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4726CF2A01F0
     */
    protected WEB3AdminFPTDeleteConfirmResponse validateChangedScreen(
        WEB3AdminFPTDeleteConfirmRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "validateChangedScreen(WEB3AdminFPTDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        // リクエストデータの整合性をチェックする。
        l_request.validate();

        // getInstanceFromログイン情報( )
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        // 管理者権限チェックをする。
        // [validate権限（）に指定する引数]
        // 機@能カテゴリコード："G0102"(金商法@交付閲覧削除)
        // is更新：true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, true);

        // validate部店権限(部店コード : String)
        // 部店権限チェックを行う。
        // [引数]
        // リクエストデータ.金商法@交付閲覧削除行.部店コード
        l_administrator.validateBranchPermission(l_request.financialProductTradeDeleteRow.branchCode);

        // get証券会社コード()
        // 証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // create検索条件文字列( )
        // 検索条件文字列を作成する。
        String l_strQueryString = this.createQueryString();

        // 検索条件データコンテナを作成する。
        // [引数]
        // 証券会社コード： 管理者.get証券会社コード()の戻り値
        // 検索情報： リクエストデータ
        Object[] l_queryContainers =
            this.createQueryDataContainer(l_strInstitutionCode, l_request);

        // 書面交付管理( )
        // 書面交付管理オブジェクトを生成する。
        WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        // get書面交付管理(String, Object[])
        // 書面交付管理テーブルからレコードを取得する。
        // [引数]
        // 検索文字列： create検索条件文字列()の戻り値
        // 検索データコンテナ： create検索条件データコンテナ()の戻り値
        List l_lisDocDivManagements =
            l_adminFPTDocDeliveryManagement.getDocDivManagement(
                l_strQueryString,
                l_queryContainers);

        // 書面交付管理行が取得できない場合、例外をスローする。
        // 分岐処理
        // 書面交付管理行が取得できない
        // （get書面交付管理()の戻り値長さ == 0）場合、
        // 例外をスローする。
        if (l_lisDocDivManagements.size() == 0)
        {
            log.debug("書面交付管理行が取得できない。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面交付管理行が取得できない。");
        }

        // createResponse( )
        // レスポンスデータ生成。
        WEB3AdminFPTDeleteConfirmResponse l_response =
            (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更画面)<BR>
     * 金商法@交付閲覧削除完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit変更画面」参照。<BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 書面交付管理行が取得できない場合、例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02952<BR>
     * =============================================== <BR>
     * @@param l_request - (l_request)<BR>
     * 管理者金商法@交付閲覧削除完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminFPTDeleteCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4726CF2C026C
     */
    protected WEB3AdminFPTDeleteCompleteResponse submitChangedScreen(
        WEB3AdminFPTDeleteCompleteRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "submitChangedScreen(WEB3AdminFPTDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // validate( )
        // リクエストデータの整合性をチェックする。
        l_request.validate();

        // getInstanceFromログイン情報( )
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        // 管理者権限チェックをする。
        // [validate権限（）に指定する引数]
        // 機@能カテゴリコード："G0102"(金商法@交付閲覧削除)
        // is更新：true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, true);

        // validate部店権限(部店コード : String)
        // 部店権限チェックを行う。
        // [引数]
        // リクエストデータ.金商法@交付閲覧削除行.部店コード
        l_administrator.validateBranchPermission(
            l_request.financialProductTradeDeleteRow.branchCode);

        // validate取引パスワード(パスワード : String)
        // 取引パスワードのチェックを行う。
        // [引数]
        // パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        // get証券会社コード()
        // 証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // create検索条件文字列( )
        // 検索条件文字列を作成する。
        String l_strQueryString = this.createQueryString();

        // 検索条件データコンテナを作成する。
        // [引数]
        // 証券会社コード： 管理者.get証券会社コード()の戻り値
        // 検索情報： リクエストデータ
        Object[] l_queryContainers =
            this.createQueryDataContainer(l_strInstitutionCode, l_request);

        // 書面交付管理( )
        // 書面交付管理オブジェクトを生成する。
        WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        // get書面交付管理(String, Object[])
        // 書面交付管理テーブルからレコードを取得する。
        // [引数]
        // 検索文字列： create検索条件文字列()の戻り値
        // 検索データコンテナ： create検索条件データコンテナ()の戻り値
        List l_lisDocDivManagements =
            l_adminFPTDocDeliveryManagement.getDocDivManagement(
                l_strQueryString,
                l_queryContainers);

        // 書面交付管理行が取得できない場合、例外をスローする。
        // 分岐処理
        // 書面交付管理行が取得できない
        // （get書面交付管理()の戻り値長さ == 0）場合、
        // 例外をスローする。
        if (l_lisDocDivManagements.size() == 0)
        {
            log.debug("書面交付管理行が取得できない。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面交付管理行が取得できない。");
        }

        DocDeliveryManagementRow l_docDeliveryManagementRow =
            (DocDeliveryManagementRow)l_lisDocDivManagements.get(0);

        // (*)プロパティセット
        // 書面交付管理履歴Paramsを生成し、
        DocDeliveryManagementHistParams l_docDeliveryManagementHistParams =
            new DocDeliveryManagementHistParams();

        // get書面交付管理一覧()の戻り値（以下書面交付管理行）を設定する。
        // 書面交付管理履歴テーブルParams.口座ID = 書面交付管理行.get口座ID()
        l_docDeliveryManagementHistParams.setAccountId(
            l_docDeliveryManagementRow.getAccountId());

        // 書面交付管理履歴テーブルParams.証券会社コード =
        // 書面交付管理行.get証券会社コード()
        l_docDeliveryManagementHistParams.setInstitutionCode(
            l_docDeliveryManagementRow.getInstitutionCode());

        // 書面交付管理履歴テーブルParams.部店コード = 書面交付管理行.get部店コード()
        l_docDeliveryManagementHistParams.setBranchCode(
            l_docDeliveryManagementRow.getBranchCode());

        // 書面交付管理履歴テーブルParams.顧客コード = 書面交付管理行.get顧客コード()
        l_docDeliveryManagementHistParams.setAccountCode(
            l_docDeliveryManagementRow.getAccountCode());

        // 書面交付管理履歴テーブルParams.書面区分 = 書面交付管理行.get書面区分()
        l_docDeliveryManagementHistParams.setDocumentDiv(
            l_docDeliveryManagementRow.getDocumentDiv());

        // 書面交付管理履歴テーブルParams.銘柄コード = 書面交付管理行.get銘柄コード()
        l_docDeliveryManagementHistParams.setProductCode(
            l_docDeliveryManagementRow.getProductCode());

        // 書面交付管理履歴テーブルParams.書面交付日 = 書面交付管理行.get書面交付日()
        l_docDeliveryManagementHistParams.setDeliveryDate(
            l_docDeliveryManagementRow.getDeliveryDate());

        // 書面交付管理履歴テーブルParams.削除フラグ = 書面交付管理行.get削除フラグ()
        l_docDeliveryManagementHistParams.setDeleteFlag(
            l_docDeliveryManagementRow.getDeleteFlag());

        // 書面交付管理履歴テーブルParams.更新者コード =
        // 書面交付管理行.get更新者コード()
        l_docDeliveryManagementHistParams.setLastUpdater(
            l_docDeliveryManagementRow.getLastUpdater());

        // 書面交付管理履歴テーブルParams.作成日付 = 書面交付管理行.get作成日付()
        l_docDeliveryManagementHistParams.setCreatedTimestamp(
            l_docDeliveryManagementRow.getCreatedTimestamp());

        // 書面交付管理履歴テーブルParams.更新日付 = 書面交付管理行.get更新日付()
        l_docDeliveryManagementHistParams.setLastUpdatedTimestamp(
            l_docDeliveryManagementRow.getLastUpdatedTimestamp());

        // 書面交付管理履歴テーブルParams.削除者 = 管理者.get管理者コード()
        l_docDeliveryManagementHistParams.setDeleteUser(
            l_administrator.getAdministratorCode());

        // 書面交付管理履歴テーブルParams.削除日時 = 現在日時
        l_docDeliveryManagementHistParams.setDeleteTimestamp(
            GtlUtils.getSystemTimestamp());

        // 書面交付管理履歴テーブルParams.書面種類コード = 書面交付管理行.get書面種類コード()
        l_docDeliveryManagementHistParams.setDocumentCategory(
            l_docDeliveryManagementRow.getDocumentCategory());

        //書面交付管理履歴テーブルParams.みなし交付日 = 書面交付管理行.みなし交付日
        l_docDeliveryManagementHistParams.setDeemedDeliveryDate(
            l_docDeliveryManagementRow.getDeemedDeliveryDate());

        // 書面交付管理履歴( )
        // 書面交付管理履歴オブジェクトを生成する。
        WEB3AdminFPTDocDeliveryManagementHistory l_adminFPTDocDeliveryManagementHistory =
            new WEB3AdminFPTDocDeliveryManagementHistory();

        // insert書面交付管理履歴テーブル(書面交付管理履歴テーブルParams)
        // 書面交付管理履歴テーブルにInsertを実行する。
        // [引数]
        // (*)プロパティセットで作成した書面交付管理履歴テーブルParams
        l_adminFPTDocDeliveryManagementHistory.insertDocDeliveryManagementHist(
            l_docDeliveryManagementHistParams);

        // delete書面交付管理(long, String, String, Date)
        // 書面交付履歴テーブルの削除を実行する。
        // [引数]
        // 口座ID： get書面交付管理一覧()の戻り値.get口座ID()
        // 書面区分：get書面交付管理一覧()の戻り値.get書面区分()
        // 銘柄コード： get書面交付管理一覧()の戻り値.get銘柄コード()
        // 書面交付日： get書面交付管理一覧()の戻り値.get書面交付日()
        // 書面種類コード： get書面交付管理一覧()の戻り値.get書面種類コード()
        long l_lngAccountId = l_docDeliveryManagementRow.getAccountId();
        String l_strDocumentDiv = l_docDeliveryManagementRow.getDocumentDiv();
        String l_strProductCode = l_docDeliveryManagementRow.getProductCode();
        Date l_datDocuDeliDate = l_docDeliveryManagementRow.getDeliveryDate();
        String l_strDocumentCategory = l_docDeliveryManagementRow.getDocumentCategory();
        l_adminFPTDocDeliveryManagement.deleteDocDivManagement(
            l_lngAccountId,
            l_strDocumentDiv,
            l_strProductCode,
            l_datDocuDeliDate,
            l_strDocumentCategory);

        // createResponse( )
        // レスポンスデータ生成。
        WEB3AdminFPTDeleteCompleteResponse l_response =
            (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 取得条件の文字列を生成する。<BR>
     * <BR>
     * １）　@空の文字列を生成する。<BR>
     * <BR>
     * ２）　@証券会社コードを１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@　@"institution_code = ?"<BR>
     * <BR>
     * ３）　@部店コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and branch_code = ? "<BR>
     * <BR>
     * ４）　@顧客コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and account_code like ? %"<BR>
     * <BR>
     * ５）　@書面区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and document_div = ? "<BR>
     * <BR>
     * ６）　@電子鳩銘柄コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and product_code = ? "<BR>
     * <BR>
     * ７）　@書面交付日を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and delivery_date　@=　@?"<BR>
     * <BR>
     * ８）　@書面種類コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "document_category　@=　@?"<BR>
     * <BR>
     * ９）　@作成した検索条件文字列インスタンスを返却する。<BR>
     * <BR>
     * <BR>
     * @@return String
     * @@roseuid 4726EFEA0290
     */
    private String createQueryString()
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //１）　@空の文字列を生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        //２）　@証券会社コードを１）の文字列に追加する。
        //"institution_code = ?"
        l_sbQueryString.append(" institution_code = ? ");

        //３）　@部店コードを検索条件文字列に追加する。
        //検索条件文字列 += "and branch_code = ? "
        l_sbQueryString.append(" and branch_code = ? ");

        //４）　@顧客コードを検索条件文字列に追加する。
        //検索条件文字列 += "and account_code like ? %"
        l_sbQueryString.append(" and account_code like ? || '%' ");

        //５）　@書面区分を検索条件文字列に追加する。
        //検索条件文字列 += "and document_div = ? "
        l_sbQueryString.append(" and document_div = ? ");

        //６）　@電子鳩銘柄コードを検索条件文字列に追加する。
        //検索条件文字列 += "and product_code = ? "
        l_sbQueryString.append(" and product_code = ? ");

        //７）　@書面交付日を検索条件文字列に追加する。
        //検索条件文字列 += "and delivery_date　@=　@?"
        l_sbQueryString.append(" and delivery_date　@=　@? ");

        //８）　@書面種類コードを検索条件文字列に追加する。
        //検索条件文字列 += "document_category　@=　@?"
        l_sbQueryString.append(" and document_category　@=　@? ");

        //９）　@作成した検索条件文字列インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １） 空のArrayListを生成する。<BR>
     * <BR>
     * ２） 証券会社コード<BR>
     * <BR>
     * 　@　@ 引数.証券会社コード を１）のListに追加する。<BR>
     * <BR>
     * ３） 部店コード <BR>
     * <BR>
     * 　@　@ 引数:検索情報.金商法@交付閲覧削除行.部店コード を１）のListに追加する。<BR>
     * <BR>
     * ４） 顧客コード<BR>
     * <BR>
     * 　@　@ 引数:検索情報.金商法@交付閲覧削除行.顧客コード を１）のListに追加する。<BR>
     * <BR>
     * ５） 書面区分<BR>
     * <BR>
     * 　@　@ 引数:検索情報.金商法@交付閲覧削除行.書面区分 を１）のListに追加する。<BR>
     * <BR>
     * ６） 電子鳩銘柄コード<BR>
     * <BR>
     * 　@　@ 引数:検索情報.金商法@交付閲覧削除行.電子鳩銘柄コード を１）のListに追加する。<BR>
     * <BR>
     * ７） 書面交付日<BR>
     * <BR>
     * 　@　@ 引数:検索情報.金商法@交付閲覧削除行.書面交付日 を１）のListに追加する。<BR>
     * <BR>
     * ８） 書面種類コード<BR>
     * <BR>
     * 　@　@ 引数:検索情報.金商法@交付閲覧削除行.書面種類コード を１）のListに追加する。<BR>
     * <BR>
     * ９） 生成されたListから配列を取得し、返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_request - (検索情報)<BR>
     * 検索情報<BR>
     * @@return Object[]
     * @@roseuid 4726EFED0213
     */
    private Object[] createQueryDataContainer(
        String l_strInstitutionCode,
        WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //１） 空のArrayListを生成する。
        List l_lisQueryDataContainers = new ArrayList();

        //２） 証券会社コード
        //引数.証券会社コード を１）のListに追加する。
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        if (l_request instanceof WEB3AdminFPTDeleteConfirmRequest)
        {
            WEB3AdminFPTDeleteConfirmRequest l_deleteConfirmRequest =
                (WEB3AdminFPTDeleteConfirmRequest)l_request;
            //３） 部店コード
            //引数:検索情報.金商法@交付閲覧削除行.部店コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.branchCode);

            //４） 顧客コード
            //引数:検索情報.金商法@交付閲覧削除行.顧客コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.acceptCode);

            //５） 書面区分
            //引数:検索情報.金商法@交付閲覧削除行.書面区分 を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.documentDiv);

            //６） 電子鳩銘柄コード
            //引数:検索情報.金商法@交付閲覧削除行.電子鳩銘柄コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.batoProductCode);

            //７） 書面交付日
            //引数:検索情報.金商法@交付閲覧削除行.書面交付日 を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.docuDeliDate);

            //８） 書面種類コード
            //引数:検索情報.金商法@交付閲覧削除行.書面種類コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteConfirmRequest.financialProductTradeDeleteRow.documentCategory);
        }
        else if (l_request instanceof WEB3AdminFPTDeleteCompleteRequest)
        {
            WEB3AdminFPTDeleteCompleteRequest l_deleteCompleteRequest =
                (WEB3AdminFPTDeleteCompleteRequest)l_request;
            //３） 部店コード
            //引数:検索情報.金商法@交付閲覧削除行.部店コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.branchCode);

            //４） 顧客コード
            //引数:検索情報.金商法@交付閲覧削除行.顧客コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.acceptCode);

            //５） 書面区分
            //引数:検索情報.金商法@交付閲覧削除行.書面区分 を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.documentDiv);

            //６） 電子鳩銘柄コード
            //引数:検索情報.金商法@交付閲覧削除行.電子鳩銘柄コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.batoProductCode);

            //７） 書面交付日
            //引数:検索情報.金商法@交付閲覧削除行.書面交付日 を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.docuDeliDate);

            //８） 書面種類コード
            //引数:検索情報.金商法@交付閲覧削除行.書面種類コード を１）のListに追加する。
            l_lisQueryDataContainers.add(l_deleteCompleteRequest.financialProductTradeDeleteRow.documentCategory);
        }

        //８） 生成されたListから配列を取得し、返却する。
        Object[] l_queryDataContainers = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_queryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainers;
    }
}
@
