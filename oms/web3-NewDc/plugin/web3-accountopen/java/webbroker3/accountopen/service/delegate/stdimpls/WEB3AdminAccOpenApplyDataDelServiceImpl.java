head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除サービスImpl(WEB3AdminAccOpenApplyDataDelServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.159、No.161
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyDataDelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設資料請求データ削除サービスImpl)<BR>
 * 管理者口座開設資料請求データ削除サービス実装クラス<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelServiceImpl implements WEB3AdminAccOpenApplyDataDelService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelServiceImpl.class);

    /**
     * 口座開設資料請求データ削除処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設資料請求データ削除検索リクエストの場合<BR>
     * 　@－get検索画面()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設資料請求データ削除確認リクエストの場合<BR>
     * 　@－validate削除()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者口座開設資料請求データ削除完了リクエストの場合<BR>
     * 　@－submit削除()をコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C2665A0340
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

        WEB3GenResponse l_response;

        //引数のリクエストデータが、管理者口座開設資料請求データ削除検索リクエストの場合
        if (l_request instanceof WEB3AdminAccOpenApplyDataDelSearchRequest)
        {
            l_response =
                this.getSearchScreen(
                    (WEB3AdminAccOpenApplyDataDelSearchRequest)l_request);
        }
        //引数のリクエストデータが、管理者口座開設資料請求データ削除確認リクエストの場合
        else if (l_request instanceof WEB3AdminAccOpenApplyDataDelCnfRequest)
        {
            l_response =
                this.validateDelete(
                    (WEB3AdminAccOpenApplyDataDelCnfRequest)l_request);
        }
        //引数のリクエストデータが、管理者口座開設資料請求データ削除完了リクエストの場合
        else if (l_request instanceof WEB3AdminAccOpenApplyDataDelCmpRequest)
        {
            l_response =
                this.submitDelete(
                    (WEB3AdminAccOpenApplyDataDelCmpRequest)l_request);
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
     * 口座開設資料請求データ削除検索画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（資料請求データ削除）get検索画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設資料請求データ削除検索リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyDataDelSearchResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminAccOpenApplyDataDelSearchResponse getSearchScreen(
        WEB3AdminAccOpenApplyDataDelSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminAccOpenApplyDataDelSearchRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："A0404"(口座開設資料請求データ削除)
        //is更新：　@true
        //※機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_DATA_DEL, true);

        //createResponse()
        WEB3AdminAccOpenApplyDataDelSearchResponse l_response =
            (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate削除)<BR>
     * 口座開設資料請求データ削除確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（資料請求データ削除）validate削除」参照。<BR>
     * ==========================================================<BR>
     * 　@　@具体位置 : 口座開設見込客オブジェクトが生成できない場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_01318<BR>
     * ==========================================================<BR>
     * 　@　@具体位置 : (*)削除不可の場合（is削除可能() == false）、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_03142<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設資料請求データ削除確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCnfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminAccOpenApplyDataDelCnfResponse validateDelete(
        WEB3AdminAccOpenApplyDataDelCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDelete(WEB3AdminAccOpenApplyDataDelCnfRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："A0404"(口座開設資料請求データ削除)
        //is更新：　@true
        //※機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_DATA_DEL, true);

        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //口座開設見込客オブジェクトを生成する。
        WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;

        try
        {
            l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_strInstitutionCode, l_request.requestNumber);
        }
        catch (NotFoundException l_ex)
        {
            //分岐フロー
            //口座開設見込客オブジェクトが生成できない場合、例外をスローする。
            log.error("口座開設見込客既存データが存在しない。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //口座開設見込客データが削除可能を判定する。
        boolean l_blnIsDeletePossible = l_accOpenExpAccountOpen.isDeletePossible();
        // 削除不可の場合（is削除可能() == false）、例外をスローする。
        if (!l_blnIsDeletePossible)
        {
            log.debug("口座開設見込客データは削除できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設見込客データは削除できません。");
        }

        //createResponse( )
        WEB3AdminAccOpenApplyDataDelCnfResponse l_response =
            (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();

        //レスポンスデータプロパティに値をセットする。
        //顧客姓（漢字）：口座開設見込客オブジェクト.get顧客姓（漢字）（）
        l_response.accountFamilyName = l_accOpenExpAccountOpen.getAccountFamilyName();
        //顧客名（漢字）：口座開設見込客オブジェクト.get顧客名（漢字）（）
        l_response.accountName = l_accOpenExpAccountOpen.getAccountName();
        //顧客姓（カナ）：口座開設見込客オブジェクト.get顧客姓（カナ）（）
        l_response.accountFamilyNameKana = l_accOpenExpAccountOpen.getFamilyNameAlt1();
        //顧客名（カナ）：口座開設見込客オブジェクト.get顧客名（カナ）（）
        l_response.accountNameKana = l_accOpenExpAccountOpen.getGivenNameAlt1();
        //住所１：口座開設見込客オブジェクト.get住所１（）
        l_response.address1 = l_accOpenExpAccountOpen.getAddressLine1();
        //住所２：口座開設見込客オブジェクト.get住所２（）
        l_response.address2 = l_accOpenExpAccountOpen.getAddressLine2();
        //住所３：口座開設見込客オブジェクト.get住所３（）
        l_response.address3 = l_accOpenExpAccountOpen.getAddressLine3();
        //住所１（カナ）：口座開設見込客オブジェクト.get住所１（カナ）（）
        l_response.addressKana1 = l_accOpenExpAccountOpen.getAddressKana1();
        //住所２（カナ）：口座開設見込客オブジェクト.get住所２（カナ）（）
        l_response.addressKana2 = l_accOpenExpAccountOpen.getAddressKana2();
        //住所３（カナ）：口座開設見込客オブジェクト.get住所３（カナ）（）
        l_response.addressKana3 = l_accOpenExpAccountOpen.getAddressKana3();
        //電話番号：口座開設見込客オブジェクト.get電話番号（）
        l_response.telephone = l_accOpenExpAccountOpen.getTelephone();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit削除)<BR>
     * 口座開設資料請求データ削除完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「口座開設（資料請求データ削除）submit削除」参照。<BR>
     * ==========================================================<BR>
     * 　@　@具体位置 : (*)口座開設見込客オブジェクトが生成できない場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_01318<BR>
     * ==========================================================<BR>
     * 　@　@具体位置 : 削除不可の場合（is削除可能() == false）、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_03142<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設資料請求データ削除完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminAccOpenApplyDataDelCmpResponse submitDelete(
        WEB3AdminAccOpenApplyDataDelCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitDelete(WEB3AdminAccOpenApplyDataDelCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性をチェックする。
        l_request.validate();

        //ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]
        //機@能カテゴリコード："A0404"(口座開設資料請求データ削除)
        //is更新：　@true
        //※機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACC_OPEN_APPLY_DATA_DEL, true);

        //証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        //validate取引パスワード(パスワード : String)
        //暗証番号の照合を行う。
        //[validate取引パスワード()に指定する引数]
        //パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //口座開設見込客オブジェクトを生成する。
        //[コンストラクタの引数]
        //証券会社コード：　@get証券会社コード()
        //識別コード：　@リクエストデータ.識別コード
        WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = null;

        try
        {
            l_accOpenExpAccountOpen =
                new WEB3AccOpenExpAccountOpen(l_strInstitutionCode, l_request.requestNumber);
        }
        catch (NotFoundException l_ex)
        {
            //分岐フロー
            //口座開設見込客オブジェクトが生成できない場合、例外をスローする。
            log.error("口座開設見込客既存データが存在しない。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01318,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //口座開設見込客データが削除可能を判定する。
        boolean l_blnIsDeletePossible = l_accOpenExpAccountOpen.isDeletePossible();
        //削除不可の場合（is削除可能() == false）、例外をスローする
        if (!l_blnIsDeletePossible)
        {
            log.debug("口座開設見込客データは削除できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03142,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座開設見込客データは削除できません。");
        }

        //口座開設見込客テーブルのレコードを物理削除する。
        l_accOpenExpAccountOpen.deleteAccOpenExpAccountOpen();

        //createResponse( )
        WEB3AdminAccOpenApplyDataDelCmpResponse l_response =
            (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
