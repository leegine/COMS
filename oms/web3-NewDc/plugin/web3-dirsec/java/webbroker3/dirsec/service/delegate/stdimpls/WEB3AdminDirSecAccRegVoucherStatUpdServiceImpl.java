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
filename	WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者顧客情報登録伝票ステータス更新サービスImpl(WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
Revision History    : 2007/06/15 徐宏偉 (中訊) 仕様変更 モデルNo.099,ＤＢ更新仕様No.008
Revision History    : 2007/06/18 柴双紅 (中訊) 仕様変更 モデルNo.101,No.109,No.110,No.111,No.112
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.define.WEB3AdminDirMapKeyDef;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccVoucherRecordDetail;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAccRegVoucherStatUpdService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者顧客情報登録伝票ステータス更新サービスImpl)<BR>
 * 管理者顧客情報登録伝票ステータス更新サービスImplクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl
    implements WEB3AdminDirSecAccRegVoucherStatUpdService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class);

    /**
     * @@roseuid 466E0B6A026D
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl()
    {

    }

    /**
     * 管理者・顧客情報登録伝票ステータス更新処理を開始する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。 <BR>
     * <BR>
     * ○管理者・顧客情報登録伝票検索入力リクエストの場合<BR>
     * 　@this.get検索画面()をコールする。<BR>
     * <BR>
     * ○管理者・顧客情報登録伝票検索結果リクエストの場合<BR>
     * 　@this.get検索結果画面()をコールする。<BR>
     * <BR>
     * ○管理者・顧客情報登録伝票ステータス更新確認リクエストの場合<BR>
     * 　@this.get更新確認画面()をコールする。<BR>
     * <BR>
     * ○管理者・顧客情報登録伝票ステータス更新完了リクエストの場合<BR>
     * 　@this.get更新完了画面()をコールする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 465408AF01E6
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

        if (l_request instanceof WEB3AdminDirSecAccRegVoucherSearchInpRequest)
        {
            //○管理者・顧客情報登録伝票検索入力リクエストの場合
            //　@this.get検索画面()をコールする。
            l_response =
                this.getSearchScreen(
                    (WEB3AdminDirSecAccRegVoucherSearchInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecAccRegVoucherSearchResRequest)
        {
            //○管理者・顧客情報登録伝票検索結果リクエストの場合
            //　@this.get検索結果画面()をコールする。
            l_response =
                this.getSearchResultScreen(
                    (WEB3AdminDirSecAccRegVoucherSearchResRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecAccRegVoucherStatUpdConfRequest)
        {
            //○管理者・顧客情報登録伝票ステータス更新確認リクエストの場合
            //　@this.get更新確認画面()をコールする。
            l_response =
                this.getUpdateConfirmScreen(
                    (WEB3AdminDirSecAccRegVoucherStatUpdConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminDirSecAccRegVoucherStatUpdCompRequest)
        {
            //○管理者・顧客情報登録伝票ステータス更新完了リクエストの場合
            //　@this.get更新完了画面()をコールする。
            l_response =
                this.getUpdateCompleteScreen(
                    (WEB3AdminDirSecAccRegVoucherStatUpdCompRequest)l_request);
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
     * (get検索画面)<BR>
     * 顧客情報登録伝票レコード検索画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者）get検索画面」参照。<BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 1.4 DIR管理者以外の場合（isDIR管理者()==false）<BR>
     * 　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票検索入力リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherSearchInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AE90278
     */
    protected WEB3AdminDirSecAccRegVoucherSearchInpResponse getSearchScreen(
        WEB3AdminDirSecAccRegVoucherSearchInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchScreen(WEB3AdminDirSecAccRegVoucherSearchInpRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："Z0101" （システム管理 キューテーブルステータス更新）
        //  ※同機@能の為流用
        //  is更新：TRUE
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //createResponse( )
        WEB3AdminDirSecAccRegVoucherSearchInpResponse l_searchInpResponse =
            (WEB3AdminDirSecAccRegVoucherSearchInpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_searchInpResponse;
    }

    /**
     * (get検索結果画面)<BR>
     * 顧客情報登録伝票レコード検索結果画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者）get検索結果画面」参照。<BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 1.5 DIR管理者以外の場合（isDIR管理者()==false）<BR>
     * 　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 1.14 ArrayListの長さ == 0の場合、例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02837<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票検索結果リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherSearchResResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AEB02F5
     */
    protected WEB3AdminDirSecAccRegVoucherSearchResResponse getSearchResultScreen(
        WEB3AdminDirSecAccRegVoucherSearchResRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchResultScreen(WEB3AdminDirSecAccRegVoucherSearchResRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        // 機@能カテゴリコード："Z0101" （システム管理 キューテーブルステータス更新）
        //   ※同機@能の為流用
        // is更新：TRUE
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //ArrayList( )
        ArrayList l_lisReturns = new ArrayList();

        //get証券会社コード( )
        String  l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get各種連絡テーブルレコード(String, String, String, String, String, String, String)
        //[get各種連絡テーブルレコード()に指定する引数]
        //証券会社コード：管理者.get証券会社コード()の戻り値
        //連絡種別：null
        //識別コード：null
        //部店コード：管理者・顧客情報登録伝票検索結果リクエスト.部店コード
        //顧客コード：管理者・顧客情報登録伝票検索結果リクエスト.顧客コード
        //データコード：管理者・顧客情報登録伝票検索結果リクエスト.データコード
        //伝票送信日時：管理者・顧客情報登録伝票検索結果リクエスト.伝票送信日
        List l_lisInformRecords = this.getVariousInformRecord(
            l_strInstitutionCode,
            null,
            null,
            l_request.branchCode,
            l_request.accountCode,
            l_request.dataCode,
            l_request.voucherSendDate);

        //this.get各種連絡テーブルレコード ()の戻り値長さ > 0 の場合、長さ分Loop
        if (l_lisInformRecords != null && !l_lisInformRecords.isEmpty())
        {
            //create顧客情報登録伝票(List, boolean, String, String, ArrayList)
            //[this.create顧客情報登録伝票()に指定する引数]
            //  レコードList：this.get各種連絡テーブルレコード()の戻り値
            //  口座開設伝票フラグ：FALSE
            //　@部店コード：null
            //　@顧客コード：null
            //　@返却値用ArrayList：「顧客情報登録伝票レコード」格納用ArrayList
            this.createAccInfoRegVoucher(
                l_lisInformRecords,
                false,
                null,
                null,
                l_lisReturns);
        }

        //get識別コード(管理者・顧客情報登録伝票検索結果リクエスト, String)
        //[get識別コードに指定する引数]
        // l_request：管理者・顧客情報登録伝票検索結果リクエスト
        // 証券会社コード：管理者.get証券会社コード()の戻り値
        Object[] l_requestNumbers = this.getRequestNumber(l_request, l_strInstitutionCode);

        //get識別コード() == null AND 「顧客情報登録伝票レコード」
        //  格納用ArrayListの長さ ==0 の場合、
        //「レコードが存在しません。」の例外をスローする。
        if (l_requestNumbers == null && l_lisReturns.isEmpty())
        {
            log.debug("レコードが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "レコードが存在しません。");
        }

        //get識別コード() != nullの場合、処理を行う。
        if (l_requestNumbers != null)
        {
            //get口座開設伝票ステータスレコード(String, Object[], String, String, String)
            //[this.get口座開設伝票ステータスレコード()に指定する引数]
            // 証券会社コード：管理者.get証券会社コード()の戻り値
            // 識別コード：this.get識別コード()の戻り値
            // データコード：管理者・顧客情報登録伝票検索結果リクエスト.データコード
            // 伝票通番：null
            // 伝票送信日時：管理者・顧客情報登録伝票検索結果リクエスト.伝票送信日
            List l_lisStatusRecords = this.getAccOpenVoucherStatusRecord(
                l_strInstitutionCode,
                l_requestNumbers,
                l_request.dataCode,
                null,
                l_request.voucherSendDate);

            //this.get口座開設伝票ステータスレコード ()の戻り値長さ > 0 の場合、長さ分Loop
            if (l_lisStatusRecords != null && !l_lisStatusRecords.isEmpty())
            {
                //create顧客情報登録伝票(List, boolean, String, String, ArrayList)
                //[this.create顧客情報登録伝票()に指定する引数]
                // レコードList：this.get口座開設伝票ステータスレコード()の戻り値
                // 口座開設伝票フラグ：TRUE
                // 部店コード：管理者・顧客情報登録伝票検索結果リクエスト.部店コード
                // 顧客コード：管理者・顧客情報登録伝票検索結果リクエスト.顧客コード
                // 返却値用ArrayList：「顧客情報登録伝票レコード」格納用ArrayList
                this.createAccInfoRegVoucher(
                    l_lisStatusRecords,
                    true,
                    l_request.branchCode,
                    l_request.accountCode,
                    l_lisReturns);
            }
        }

        //ArrayListの長さ == 0の場合、例外をスローする。
        if (l_lisReturns.isEmpty())
        {
            log.debug("レコードが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "レコードが存在しません。");
        }

        //createResponse( )
        WEB3AdminDirSecAccRegVoucherSearchResResponse l_searchResResponse =
            (WEB3AdminDirSecAccRegVoucherSearchResResponse)l_request.createResponse();

        WEB3AdminDirSecAccVoucherRecordDetail[] l_recordDetails =
            new WEB3AdminDirSecAccVoucherRecordDetail[l_lisReturns.size()];
        l_lisReturns.toArray(l_recordDetails);

        l_searchResResponse.accVoucherRecord = l_recordDetails;

        log.exiting(STR_METHOD_NAME);
        return l_searchResResponse;
    }

    /**
     * (get更新確認画面)<BR>
     * 顧客情報登録伝票ステータス更新確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者）get更新確認画面」参照。<BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 1.5 DIR管理者以外の場合（isDIR管理者()==false）<BR>
     * 　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票ステータス更新確認リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherStatUpdConfResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AED0268
     */
    protected WEB3AdminDirSecAccRegVoucherStatUpdConfResponse getUpdateConfirmScreen(
        WEB3AdminDirSecAccRegVoucherStatUpdConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getUpdateConfirmScreen(WEB3AdminDirSecAccRegVoucherStatUpdConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："Z0101" （システム管理 キューテーブルステータス更新）
        //  ※同機@能の為流用
        //is更新：TRUE
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //isDIR管理者( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //createResponse
        WEB3AdminDirSecAccRegVoucherStatUpdConfResponse l_response =
            (WEB3AdminDirSecAccRegVoucherStatUpdConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get更新完了画面)<BR>
     * 顧客情報登録伝票ステータス更新完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者）get更新完了画面」参照。<BR>
     * =============================================== <BR>
     * 　@　@具体位置 : 1.5 DIR管理者以外の場合（isDIR管理者()==false）<BR>
     * 　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_00857<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・顧客情報登録伝票ステータス更新完了リクエストクラス。<BR>
     * @@return WEB3AdminDirSecAccRegVoucherStatUpdCompResponse
     * @@throws WEB3BaseException
     * @@roseuid 46540AEF015F
     */
    protected WEB3AdminDirSecAccRegVoucherStatUpdCompResponse getUpdateCompleteScreen(
        WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getUpdateCompleteScreen(WEB3AdminDirSecAccRegVoucherStatUpdCompRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："Z0101" （システム管理 キューテーブルステータス更新）
        //  ※同機@能の為流用
        //  is更新：TRUE
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.HOST_STATUS_UPDATE, true);

        //isDIR管理者( )
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //validate取引パスワード(パスワード : String)
        // [validate取引パスワード()に指定する引数]
        // パスワード　@：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //ArrayList( )
        ArrayList l_lisReturns = new ArrayList();

        //顧客情報登録伝票レコードの長さの回数ループ
        //顧客情報登録伝票レコード[index]が各種連絡テーブルレコードの場合
        for (int i = 0; i < l_request.accVoucherRecord.length; i++)
        {
            WEB3AdminDirSecAccVoucherRecordDetail l_recordDetail =
                l_request.accVoucherRecord[i];
            if (!l_recordDetail.voucherFlag)
            {
                //update各種連絡テーブル(顧客情報登録伝票レコード詳細, String, String)
                this.updateVariousInform(
                    l_recordDetail,
                    l_request.updateVoucherMakeStatus,
                    l_request.updateErrorReasonCode);

                //get各種連絡テーブルレコード(String, String, String, String, String, String, String)
                //[get各種連絡テーブルレコード()に指定する引数]
                // 証券会社コード：顧客情報登録伝票レコード[index].証券会社コード
                // 連絡種別：顧客情報登録伝票レコード[index].連絡種別
                // 識別コード：顧客情報登録伝票レコード[index].識別コード
                // 部店コード：顧客情報登録伝票レコード[index].部店コード
                // 顧客コード：null
                // データコード：null
                // 伝票送信日時：null
                List l_lisInformRecords = this.getVariousInformRecord(
                    l_recordDetail.institutionCode,
                    l_recordDetail.infoType,
                    l_recordDetail.requestNumber,
                    l_recordDetail.branchCode,
                    null,
                    null,
                    null);

                //create顧客情報登録伝票(List, boolean, String, String, ArrayList)
                //[this.create顧客情報登録伝票()に指定する引数]
                // レコードList：this.get各種連絡テーブルレコード()の戻り値
                // 口座開設伝票フラグ：FALSE
                // 部店コード：null
                // 顧客コード：null
                // 返却値用ArrayList：「顧客情報登録伝票レコード」格納用ArrayList
                this.createAccInfoRegVoucher(
                    l_lisInformRecords,
                    false,
                    null,
                    null,
                    l_lisReturns);
            }
            else
            {
                //update口座開設伝票ステータス(顧客情報登録伝票レコード詳細, String, String)
                //[this.update口座開設伝票ステータス()に指定する引数]
                // 顧客情報：顧客情報登録伝票レコード[index]
                // 伝票作成状況：管理者・顧客情報登録伝票ステータス更新完了リクエスト.更新_伝票作成状況
                // エラー理由コード：管理者・顧客情報登録伝票ステータス更新完了リクエスト.更新_エラー理由コード
                this.updateAccOpenVoucherStatus(
                    l_recordDetail,
                    l_request.updateVoucherMakeStatus,
                    l_request.updateErrorReasonCode);

                //get口座開設伝票ステータスレコード(String, Object[], String, String, String)
                //[this.get口座開設伝票ステータスレコード()に指定する引数]
                // 証券会社コード：顧客情報登録伝票レコード[index].証券会社コード
                // 識別コード：顧客情報登録伝票レコード[index].識別コード()を要素とした長さ1のObject配列
                // データコード：顧客情報登録伝票レコード[index].データコード
                // 伝票通番：顧客情報登録伝票レコード[index].伝票通番
                // 伝票送信日時：null
                List l_lisInformRecords = this.getAccOpenVoucherStatusRecord(
                    l_recordDetail.institutionCode,
                    new Object[]{l_recordDetail.requestNumber},
                    l_recordDetail.dataCode,
                    l_recordDetail.voucherNumber,
                    null);

                //create顧客情報登録伝票(List, boolean, String, String, ArrayList)
                //[this.create顧客情報登録伝票()に指定する引数]
                // レコードList：this.get口座開設伝票ステータスレコード()の戻り値
                // 口座開設伝票フラグ：TRUE
                // 部店コード：顧客情報登録伝票レコード[index].部店コード
                // 顧客コード：顧客情報登録伝票レコード[index].顧客コード
                // 返却値用ArrayList：「顧客情報登録伝票レコード」格納用ArrayList
                this.createAccInfoRegVoucher(
                    l_lisInformRecords,
                    true,
                    l_recordDetail.branchCode,
                    l_recordDetail.accountCode,
                    l_lisReturns);
            }
        }

        //createResponse( )
        WEB3AdminDirSecAccRegVoucherStatUpdCompResponse l_compResponse =
            (WEB3AdminDirSecAccRegVoucherStatUpdCompResponse)l_request.createResponse();

        WEB3AdminDirSecAccVoucherRecordDetail[] l_accVoucherRecords =
            new WEB3AdminDirSecAccVoucherRecordDetail[l_lisReturns.size()];
        l_lisReturns.toArray(l_accVoucherRecords);
        l_compResponse.accVoucherRecord = l_accVoucherRecords;

        log.exiting(STR_METHOD_NAME);
        return l_compResponse;
    }

    /**
     * (get識別コード)<BR>
     * 口座開設見込客テーブルより、識別コードを取得する。<BR>
     * 取得出来ない場合はnullを返却する。<BR>
     * <BR>
     * １） 検索文字列の作成<BR>
     * 　@１-１） 検索条件文字列を生成<BR>
     * <BR>
     * 　@　@　@"institution_code = ? and branch_code= ? and account_code = ?"<BR>
     * <BR>
     * ２） 検索条件コンテナの作成<BR>
     * 　@２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     *   ２-２） 検索必須条件の追加<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）証券会社コード<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）l_request.部店コード<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）l_request.顧客コード<BR>
     * <BR>
     * 　@２-３） ArrayListインスタンスをObject配列に変換<BR>
     * <BR>
     * ３） 口座開設見込客テーブルよりレコードを取得<BR>
     * 　@３-１） QueryProcessor#doFindAllQuery()メソッドをコールする。<BR>
     * 　@　@　@　@[doFindAllQuery()に指定する引数]<BR>
     * 　@　@　@　@　@　@arg0：　@口座開設見込客RowType<BR>
     * 　@　@　@　@　@　@arg1：　@１） で作成した文字列<BR>
     * 　@　@　@　@　@　@arg2：　@２） で作成した配列<BR>
     * <BR>
     * 　@３-２） ３-１） の戻り値（口座開設見込客テーブルList）長さ > 0 の場合、<BR>
     * 　@　@３-２-１） ArrayListを生成<BR>
     * 　@　@３-２-２） 口座開設見込客テーブルListの長さの回数Loop<BR>
     * 　@　@　@３-２-２-１） 識別コードの追加<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@口座開設見込客テーブルList[index].get識別コード()<BR>
     * <BR>
     * 　@　@３-２-３） ArrayList#toArray()を返却する。<BR>
     * <BR>
     * 　@３-３） の戻り値長さ == 0 の場合nullを返却する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 465A36F302F8
     */
    private Object[] getRequestNumber(
        WEB3AdminDirSecAccRegVoucherSearchResRequest l_request, String l_strInstitutionCode)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getRequestNumber(WEB3AdminDirSecAccRegVoucherSearchResRequest, String)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        //１） 検索文字列の作成
        //１-１） 検索条件文字列を生成
        //      "institution_code = ? and branch_code= ? and account_code = ?"
        String l_strQueryString = " institution_code = ? and branch_code= ? and account_code = ? ";
        //２） 検索条件コンテナの作成
        // ２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成
        List l_lisValue = new ArrayList();

        // ２-２） 検索必須条件の追加
        //   [add()に指定する引数]
        //  　@  （引数）証券会社コード
        //   [add()に指定する引数]
        //      （引数）l_request.部店コード
        //   [add()に指定する引数]
        //       引数）l_request.顧客コード
        l_lisValue.add(l_strInstitutionCode);
        l_lisValue.add(l_request.branchCode);
        l_lisValue.add(l_request.accountCode);

        // ２-３） ArrayListインスタンスをObject配列に変換
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //３） 口座開設見込客テーブルよりレコードを取得
        // ３-１） QueryProcessor#doFindAllQuery()メソッドをコールする。
        //   [doFindAllQuery()に指定する引数]
        //     　@arg0：　@口座開設見込客RowType
        //    　@ arg1：　@１） で作成した文字列
        //  　@   arg2：　@２） で作成した配列
        List l_lisExpAccountOpenRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisExpAccountOpenRecord = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_queryDataContainers);
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

        // ３-２） ３-１） の戻り値（口座開設見込客テーブルList）長さ > 0 の場合、
        Object[] l_sccOpenRequestNumbers = null;
        if (l_lisExpAccountOpenRecord.size() > 0)
        {
            //  ３-２-１） ArrayListを生成
            List l_lisTemp = new ArrayList();

            //  ３-２-２） 口座開設見込客テーブルListの長さの回数Loop
            for (int i = 0; i < l_lisExpAccountOpenRecord.size(); i++)
            {
                //  ３-２-２-１） 識別コードの追加
                //     [add()に指定する引数]
                //        口座開設見込客テーブルList[index].get識別コード()
                l_lisTemp.add(((ExpAccountOpenRow)
                    l_lisExpAccountOpenRecord.get(i)).getAccOpenRequestNumber());
            }

            //  ３-２-３） ArrayList#toArray()を返却する。
            l_sccOpenRequestNumbers = new Object[l_lisTemp.size()];
            l_lisTemp.toArray(l_sccOpenRequestNumbers);
        }

        //３-３） の戻り値長さ == 0 の場合nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sccOpenRequestNumbers;
    }

    /**
     * 口座開設伝票作成ステータステーブルレコード（<BR>
     * 　@（引数）伝票作成状況変換フラグ == TRUE）の時、<BR>
     * 各種連絡テーブルの伝票作成状況仕様の（引数）statusを<BR>
     * 口座開設伝票作成ステータスの伝票作成ステータス仕様に変換する。<BR>
     * <BR>
     * １） （引数）伝票作成状況変換フラグ == TRUE の場合<BR>
     * 　@１-１） （引数）status == 3（受付完了） の時<BR>
     * 　@　@　@　@　@4 （受信済）を返却する。<BR>
     * <BR>
     * 　@１-２） （引数）status == 4（受付エラー） の時<BR>
     * 　@　@　@　@　@6（受信エラー）を返却する。<BR>
     * <BR>
     * 　@１-３） １-１）、１-２）以外の場合、<BR>
     * 　@　@　@　@　@（引数）statusを返却する。<BR>
     * <BR>
     * ２） （引数）伝票作成状況変換フラグ == FALSE の場合<BR>
     * 　@　@　@（引数）statusを返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_strStatus - (status)<BR>
     * 各種連絡テーブルの伝票作成状況<BR>
     * 又は<BR>
     * 口座開設伝票作成ステータスの伝票作成ステータス<BR>
     * @@param l_blnIsVoucherStatusChangeFlag - (伝票作成状況変換フラグ)<BR>
     * 伝票作成状況変換フラグ<BR>
     * <BR>
     * TRUE：口座開設伝票作成ステータスレコード<BR>
     * <BR>
     * FALSE：各種連絡テーブルレコード。<BR>
     * <BR>
     * @@return String
     * @@roseuid 465A88B30384
     */
    private String changeStatus(String l_strStatus, boolean l_blnIsVoucherStatusChangeFlag)
    {
        final String STR_METHOD_NAME =
            "changeStatus(String, boolean)";
        log.entering(STR_METHOD_NAME);

        //    １） （引数）伝票作成状況変換フラグ == TRUE の場合
        if (l_blnIsVoucherStatusChangeFlag)
        {
            //    １-１） （引数）status == 3（受付完了） の時
            //            4 （受信済）を返却する。
            if (WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strStatus))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3VoucherStatusDef.RECEIVE_COMPLETE;
            }
            //    １-２） （引数）status == 4（受付エラー） の時
            //            6（受信エラー）を返却する。
            else if (WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strStatus))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3VoucherStatusDef.RECEIVE_ERROR;
            }
            //    １-３） １-１）、１-２）以外の場合、
            //            （引数）statusを返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_strStatus;
            }
        }
        else
        {
            //  ２） （引数）伝票作成状況変換フラグ == FALSE の場合
            //       （引数）statusを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_strStatus;
        }
    }

    /**
     * (create顧客情報登録伝票)<BR>
     * 引数を元に返却値用ArrayListにプロパティを設定する。<BR>
     * <BR>
     * １） （引数）レコードListの長さの回数以下を行う。<BR>
     * 　@１-１） 顧客情報登録伝票レコード詳細オブジェクトを生成。<BR>
     * <BR>
     * 　@１-２） プロパティの設定を行う。<BR>
     * 　@　@１-２-１） （引数）口座開設伝票フラグ == FALSE の場合、<BR>
     * <BR>
     * 　@　@顧客情報登録伝票レコード詳細.証券会社コード =<BR>
     * 　@　@　@（引数）レコードList[index].証券会社コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.部店コード =<BR>
     * 　@　@　@（引数）レコードList[index].部店コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.顧客コード =<BR>
     * 　@　@　@（引数）レコードList[index].顧客コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.データコード =<BR>
     * 　@　@　@（引数）レコードList[index].データコード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.識別コード =<BR>
     * 　@　@　@（引数）レコードList[index].識別コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票作成状況 =<BR>
     * 　@　@　@（引数）レコードList[index].伝票作成状況<BR>
     * 　@　@顧客情報登録伝票レコード詳細.エラー理由コード =<BR>
     * 　@　@　@（引数）レコードList[index].エラー理由コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票送信日時  =<BR>
     * 　@　@　@（引数）レコードList[index].伝票送信日時<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票受信日時  =<BR>
     * 　@　@　@（引数）レコードList[index].伝票受信日時<BR>
     * 　@　@顧客情報登録伝票レコード詳細.口座開設伝票フラグ =<BR>
     * 　@　@　@（引数）口座開設伝票フラグ<BR>
     * 　@　@顧客情報登録伝票レコード詳細.連絡種別 =<BR>
     * 　@　@　@（引数）レコードList[index].連絡種別<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票通番 = null<BR>
     * <BR>
     * 　@　@１-２-２） （引数）口座開設伝票フラグ == TRUE の場合、<BR>
     * <BR>
     * 　@　@顧客情報登録伝票レコード詳細.証券会社コード =<BR>
     * 　@　@　@（引数）レコードList[index].証券会社コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.部店コード = （引数）部店コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.顧客コード = （引数）顧客コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.データコード =<BR>
     * 　@　@　@（引数）レコードList[index].データコード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.識別コード = <BR>
     * 　@　@　@（引数）レコードList[index].識別コード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票作成状況 = <BR>
     * 　@　@　@（引数）レコードList[index].伝票作成ステータス<BR>
     * 　@　@顧客情報登録伝票レコード詳細.エラー理由コード = <BR>
     * 　@　@　@（引数）レコードList[index].エラーコード<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票送信日時  = <BR>
     * 　@　@　@（引数）レコードList[index].送信日時<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票受信日時  = <BR>
     * 　@　@　@（引数）レコードList[index].受信日時<BR>
     * 　@　@顧客情報登録伝票レコード詳細.口座開設伝票フラグ = <BR>
     * 　@　@　@（引数）口座開設伝票フラグ<BR>
     * 　@　@顧客情報登録伝票レコード詳細.連絡種別 = null<BR>
     * 　@　@顧客情報登録伝票レコード詳細.伝票通番 = <BR>
     * 　@　@　@（引数）レコードList[index].伝票通番<BR>
     * <BR>
     * 　@１-３） 戻り値用ArrayListに追加する。<BR>
     * 　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@１-２）でプロパティセット済みの顧客情報登録伝票レコード詳細オブジェクト<BR>
     * <BR>
     * ２） 返却値用ArrayListを返却する。<BR>
     * <BR>
     * @@param l_lisRecordList - (レコードList)<BR>
     * 各種連絡テーブルレコードのList、又は<BR>
     * 口座開設伝票ステータスレコードのList。<BR>
     * @@param l_blnIsAccOpenVoucherFlag - (口座開設伝票フラグ)<BR>
     * 口座開設伝票フラグ<BR>
     * <BR>
     * TRUE：Listのレコードが口座開設伝票ステータスレコード<BR>
     * FALSE：Listのレコードが各種連絡テーブルレコード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。<BR>
     * @@param l_lisRecords - (返却値用ArrayList)<BR>
     * 返却値用ArrayListオブジェクト。<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException
     * @@roseuid 465AA4FD0085
     */
    private ArrayList createAccInfoRegVoucher(
        List l_lisRecordList,
        boolean l_blnIsAccOpenVoucherFlag,
        String l_strBranchCode,
        String l_strAccountCode,
        ArrayList l_lisRecords) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createAccInfoRegVoucher(List, boolean, String, String, ArrayList)";
        log.entering(STR_METHOD_NAME);

        if (l_lisRecordList == null || l_lisRecords == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        //    １） （引数）レコードListの長さの回数以下を行う。
        for (int i = 0; i < l_lisRecordList.size(); i++)
        {
            // １-１） 顧客情報登録伝票レコード詳細オブジェクトを生成。
            WEB3AdminDirSecAccVoucherRecordDetail l_dirSecAccVoucherRecordDetail =
                new WEB3AdminDirSecAccVoucherRecordDetail();

            //    １-２） プロパティの設定を行う。
            //      １-２-１） （引数）口座開設伝票フラグ == FALSE の場合、
            if (!l_blnIsAccOpenVoucherFlag)
            {
                //顧客情報登録伝票レコード詳細.証券会社コード = （引数）レコードList[index].証券会社コード
                VariousInformRow l_variousInformRow = (VariousInformRow)l_lisRecordList.get(i);

                l_dirSecAccVoucherRecordDetail.institutionCode =
                    l_variousInformRow.getInstitutionCode();

                //  　@　@顧客情報登録伝票レコード詳細.部店コード = （引数）レコードList[index].部店コード
                l_dirSecAccVoucherRecordDetail.branchCode =
                    l_variousInformRow.getBranchCode();

                //  　@　@顧客情報登録伝票レコード詳細.顧客コード = （引数）レコードList[index].顧客コード
                l_dirSecAccVoucherRecordDetail.accountCode = l_variousInformRow.getAccountCode();

                //  　@　@顧客情報登録伝票レコード詳細.データコード = （引数）レコードList[index].データコード
                l_dirSecAccVoucherRecordDetail.dataCode = l_variousInformRow.getRequestCode();

                //  　@　@顧客情報登録伝票レコード詳細.識別コード = （引数）レコードList[index].識別コード
                l_dirSecAccVoucherRecordDetail.requestNumber = l_variousInformRow.getRequestNumber();

                //  　@　@顧客情報登録伝票レコード詳細.伝票作成状況 = （引数）レコードList[index].伝票作成状況
                l_dirSecAccVoucherRecordDetail.voucherMakeStatus = l_variousInformRow.getStatus();

                //  　@　@顧客情報登録伝票レコード詳細.エラー理由コード = （引数）レコードList[index].エラー理由コード
                l_dirSecAccVoucherRecordDetail.errorReasonCode = l_variousInformRow.getErrorReasonCode();

                //  　@　@顧客情報登録伝票レコード詳細.伝票送信日時  = （引数）レコードList[index].伝票送信日時
                l_dirSecAccVoucherRecordDetail.voucherSendTimestamp = l_variousInformRow.getSendTimestamp();

                //  　@　@顧客情報登録伝票レコード詳細.伝票受信日時  = （引数）レコードList[index].伝票受信日時
                l_dirSecAccVoucherRecordDetail.voucherRecvTimestamp = l_variousInformRow.getReceiptTimestamp();

                //  　@　@顧客情報登録伝票レコード詳細.口座開設伝票フラグ = （引数）口座開設伝票フラグ
                l_dirSecAccVoucherRecordDetail.voucherFlag = l_blnIsAccOpenVoucherFlag;

                //  　@　@顧客情報登録伝票レコード詳細.連絡種別 = （引数）レコードList[index].連絡種別
                l_dirSecAccVoucherRecordDetail.infoType = l_variousInformRow.getInformDiv();

                //  　@　@顧客情報登録伝票レコード詳細.伝票通番 = null
                l_dirSecAccVoucherRecordDetail.voucherNumber = null;
            }
            else
            {
                //      １-２-２） （引数）口座開設伝票フラグ == TRUE の場合、
                //  　@　@顧客情報登録伝票レコード詳細.証券会社コード = （引数）レコードList[index].証券会社コード
                AccOpenVoucherStatusRow l_accOpenVoucherStatusRow =
                    (AccOpenVoucherStatusRow)l_lisRecordList.get(i);
                l_dirSecAccVoucherRecordDetail.institutionCode =
                    l_accOpenVoucherStatusRow.getInstitutionCode();

                //  　@　@顧客情報登録伝票レコード詳細.部店コード = （引数）部店コード
                l_dirSecAccVoucherRecordDetail.branchCode = l_strBranchCode;

                //  　@　@顧客情報登録伝票レコード詳細.顧客コード = （引数）顧客コード
                l_dirSecAccVoucherRecordDetail.accountCode = l_strAccountCode;

                //  　@　@顧客情報登録伝票レコード詳細.データコード = （引数）レコードList[index].データコード
                l_dirSecAccVoucherRecordDetail.dataCode = l_accOpenVoucherStatusRow.getRequestCode();

                //  　@　@顧客情報登録伝票レコード詳細.識別コード = （引数）レコードList[index].識別コード
                l_dirSecAccVoucherRecordDetail.requestNumber =
                    l_accOpenVoucherStatusRow.getAccOpenRequestNumber();

                //  　@　@顧客情報登録伝票レコード詳細.伝票作成状況 = （引数）レコードList[index].伝票作成ステータス
                l_dirSecAccVoucherRecordDetail.voucherMakeStatus =
                    l_accOpenVoucherStatusRow.getVoucherStatus();

                //  　@　@顧客情報登録伝票レコード詳細.エラー理由コード = （引数）レコードList[index].エラーコード
                l_dirSecAccVoucherRecordDetail.errorReasonCode =
                    l_accOpenVoucherStatusRow.getErrorCode();

                //  　@　@顧客情報登録伝票レコード詳細.伝票送信日時  = （引数）レコードList[index].送信日時
                l_dirSecAccVoucherRecordDetail.voucherSendTimestamp =
                    l_accOpenVoucherStatusRow.getSendTimestamp();

                //  　@　@顧客情報登録伝票レコード詳細.伝票受信日時  = （引数）レコードList[index].受信日時
                l_dirSecAccVoucherRecordDetail.voucherRecvTimestamp =
                    l_accOpenVoucherStatusRow.getRecvTimestamp();

                //  　@　@顧客情報登録伝票レコード詳細.口座開設伝票フラグ = （引数）口座開設伝票フラグ
                l_dirSecAccVoucherRecordDetail.voucherFlag = l_blnIsAccOpenVoucherFlag;

                //  　@　@顧客情報登録伝票レコード詳細.連絡種別 = null
                l_dirSecAccVoucherRecordDetail.infoType = null;

                //  　@　@顧客情報登録伝票レコード詳細.伝票通番 = （引数）レコードList[index].伝票通番
                l_dirSecAccVoucherRecordDetail.voucherNumber =
                    l_accOpenVoucherStatusRow.getSerialNo();
            }

            //    １-３） 戻り値用ArrayListに追加する。
            //          [add()に指定する引数]
            //              １-２）でプロパティセット済みの顧客情報登録伝票レコード詳細オブジェクト
            l_lisRecords.add(l_dirSecAccVoucherRecordDetail);
        }

        //  ２） 返却値用ArrayListを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (update各種連絡テーブル)<BR>
     * 更新対象レコード存在チェック後、各種連絡テーブルの更新を行う。<BR>
     * <BR>
     * １） 検索文字列の作成<BR>
     * <BR>
     * 　@　@"institution_code = ? and inform_div = ? and request_number = ? and<BR>
     * 　@　@　@branch_code = ?"<BR>
     * <BR>
     * ２） 検索条件コンテナの作成<BR>
     * 　@２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     * 　@２-２） 検索条件の追加<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@　@（引数）顧客情報.証券会社コード<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@　@（引数）顧客情報.連絡種別<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@　@（引数）顧客情報.識別コード<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@　@（引数）顧客情報.部店コード<BR>
     * <BR>
     * 　@２-３） ArrayListインスタンスをObject配列に変換<BR>
     * <BR>
     * <BR>
     * ３） 各種連絡テーブルへ該当レコードの存在チェックを行う。<BR>
     * 　@３-１） 各種連絡テーブルよりレコードを取得<BR>
     * 　@　@　@　@　@QueryProcessor#doFindAllQuery()メソッドをコールする。<BR>
     * 　@　@　@　@　@[doFindAllQuery()に指定する引数]<BR>
     * 　@　@　@　@　@　@arg0：　@各種連絡テーブルRowType<BR>
     * 　@　@　@　@　@　@arg1：　@１） で作成した文字列<BR>
     * 　@　@　@　@　@　@arg2：　@２） で作成した配列<BR>
     * <BR>
     * 　@３-２） ３-１） の戻り値長さ == 0<BR>
     * 　@　@　@　@　@　@の場合、「レコードが存在しません。」の例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02837<BR>
     * <BR>
     * ４） 各種連絡テーブルの更新を行う。<BR>
     * 　@４-１） 更新内容格納用インスタンス（：Map）を生成<BR>
     * <BR>
     * 　@４-２） 更新内容の追加<BR>
     * 　@　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@　@key："status"<BR>
     * 　@　@　@　@　@value：（引数）伝票作成状況<BR>
     * 　@　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@　@key："error_reason_code"<BR>
     * 　@　@　@　@　@value：（引数）エラー理由コード<BR>
     * 　@　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@　@key："receipt_timestamp"<BR>
     * 　@　@　@　@　@value：現在日時<BR>
     * <BR>
     * 　@４-３） 各種連絡テーブルの更新<BR>
     * 　@　@　@　@　@QueryProcessor#doUpdateQuery()メソッドをコールする。<BR>
     * 　@　@　@　@　@[doUpdateQuery()に指定する引数]<BR>
     * 　@　@　@　@　@　@arg0：　@各種連絡テーブルRowType<BR>
     * 　@　@　@　@　@　@arg1：　@１） で作成した文字列<BR>
     * 　@　@　@　@　@　@arg2：　@２） で作成した配列<BR>
     * 　@　@　@　@　@　@arg3：  ４-２） で作成したMap<BR>
     * <BR>
     * <BR>
     * @@param l_accountInfo - (顧客情報)<BR>
     * 顧客情報登録伝票レコード詳細クラス。<BR>
     * @@param l_strVoucherCreateStatus - (伝票作成状況)<BR>
     * 伝票作成状況。<BR>
     * @@param l_strErrorReasonCode - (エラー理由コード)<BR>
     * エラー理由コード。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 465BD68902FE
     */
    private void updateVariousInform(
        WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo,
        String l_strVoucherCreateStatus,
        String l_strErrorReasonCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " updateVariousInform(WEB3AdminDirSecAccVoucherRecordDetail, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_accountInfo == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }
        //１） 検索文字列の作成
        //"institution_code = ? and inform_div = ? and request_number = ? and branch_code = ?"
        String l_strQueryString =
            " institution_code = ? and inform_div = ? and request_number = ? and branch_code = ? ";

        //２） 検索条件コンテナの作成
        //  ２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成
        List l_lisValue = new ArrayList();

        //  ２-２） 検索条件の追加
        //        [add()に指定する引数]
        //　@         （引数）顧客情報.証券会社コード
        //        [add()に指定する引数]
        //           （引数）顧客情報.連絡種別
        //        [add()に指定する引数]
        //　@         （引数）顧客情報.識別コード
        //        [add()に指定する引数]
        //           （引数）顧客情報.部店コード
        l_lisValue.add(l_accountInfo.institutionCode);
        l_lisValue.add(l_accountInfo.infoType);
        l_lisValue.add(l_accountInfo.requestNumber);
        l_lisValue.add(l_accountInfo.branchCode);

        //  ２-３） ArrayListインスタンスをObject配列に変換
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //３） 各種連絡テーブルへ該当レコードの存在チェックを行う。
        //  ３-１） 各種連絡テーブルよりレコードを取得
        //          QueryProcessor#doFindAllQuery()メソッドをコールする。
        //          [doFindAllQuery()に指定する引数]
        //   　@        arg0：　@各種連絡テーブルRowType
        //  　@　@       arg1：　@１） で作成した文字列
        //　@  　@       arg2：　@２） で作成した配列
        List l_lisVariousInformRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisVariousInformRecord =
                l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
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

        //  ３-２） ３-１） の戻り値長さ == 0 の場合、「レコードが存在しません。」の例外をスローする。
        if (l_lisVariousInformRecord.isEmpty())
        {
            log.debug("レコードが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "レコードが存在しません。");
        }

        //４） 各種連絡テーブルの更新を行う。
        //  ４-１） 更新内容格納用インスタンス（：Map）を生成
        Map l_map = new HashMap();

        //  ４-２） 更新内容の追加
        //        [put()に指定する引数]
        //          key："status"
        //      value：（引数）伝票作成状況
        //    [put()に指定する引数]
        //      key："error_reason_code"
        //      value：（引数）エラー理由コード
        //    [put()に指定する引数]
        //      key："receipt_timestamp"
        //          value：現在日時
        l_map.put(WEB3AdminDirMapKeyDef.STATUS, l_strVoucherCreateStatus);
        l_map.put(WEB3AdminDirMapKeyDef.ERROR_REASON_CODE, l_strErrorReasonCode);
        l_map.put(WEB3AdminDirMapKeyDef.RECEIPT_TIMESTAMP, GtlUtils.getSystemTimestamp());

        //  ４-３） 各種連絡テーブルの更新
        //          QueryProcessor#doUpdateQuery()メソッドをコールする。
        //          [doUpdateQuery()に指定する引数]
        //   　@        arg0：　@各種連絡テーブルRowType
        //  　@　@       arg1：　@１） で作成した文字列
        //　@  　@       arg2：　@２） で作成した配列
        //             arg3：  ４-２） で作成したMap
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                VariousInformRow.TYPE,
                l_strQueryString,
                l_queryDataContainers,
                l_map);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update口座開設伝票ステータス)<BR>
     * 更新対象レコード存在チェック後、口座開設作成伝票ステータスの更新を行う。<BR>
     * <BR>
     * １） 検索文字列の作成<BR>
     * <BR>
     * 　@"institution_code = ? and acc_open_request_number = ?<BR>
     * 　@　@　@and request_code = ? and serial_no = ?"<BR>
     * <BR>
     * ２） 検索条件コンテナの作成<BR>
     * 　@２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     * 　@２-２） 検索条件の追加<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）顧客情報.証券会社コード<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）顧客情報.識別コード<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）顧客情報.データコード<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）顧客情報.伝票通番<BR>
     * <BR>
     * 　@２-３） ArrayListインスタンスをObject配列に変換<BR>
     * <BR>
     * ３） 口座開設作成伝票ステータスへ該当レコードの存在チェックを行う。<BR>
     * 　@３-１） 口座開設伝票ステータスよりレコードを取得<BR>
     * 　@　@　@　@　@QueryProcessor#doFindAllQuery()メソッドをコールする。<BR>
     * 　@　@　@　@　@[doFindAllQuery()に指定する引数]<BR>
     * 　@　@　@　@　@　@arg0：　@口座開設伝票ステータスRowType<BR>
     * 　@　@　@　@　@　@arg1：　@１） で作成した文字列<BR>
     * 　@　@　@　@　@　@arg2：　@２） で作成した配列<BR>
     * <BR>
     * 　@３-２） ３-１） の戻り値長さ == 0 の場合、「レコードが存在しません。」<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@: BUSINESS_ERROR_02837<BR>
     * <BR>
     * ４） 口座開設作成伝票ステータスの更新を行う。<BR>
     * 　@４-１） 伝票作成状況の変換を行う。<BR>
     * 　@　@　@　@[this.changeStatus()に指定する引数]<BR>
     * 　@　@　@　@　@status：（引数）伝票作成状況<BR>
     * 　@　@　@　@　@伝票作成状況変換フラグ：（引数）顧客情報.口座開設伝票フラグ<BR>
     * <BR>
     * 　@４-２） 更新内容格納用インスタンス（：Map）を生成<BR>
     * <BR>
     * 　@４-３） 更新内容の追加<BR>
     * 　@　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@　@key："voucher_status"<BR>
     * 　@　@　@　@　@value： ４-１）で変換済みの伝票作成状況<BR>
     * 　@　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@　@key："error_code"<BR>
     * 　@　@　@　@　@value：（引数）エラー理由コード<BR>
     * 　@　@　@　@[put()に指定する引数]<BR>
     * 　@　@　@　@　@key："recv_timestamp"<BR>
     * 　@　@　@　@　@value：現在日時<BR>
     * <BR>
     * 　@４-４） 口座開設伝票作成ステータスの更新<BR>
     * 　@　@　@　@　@QueryProcessor#doUpdateAllQuery()メソッドをコールする。 <BR>
     * 　@　@　@　@　@[doUpdateQuery()に指定する引数]<BR>
     * 　@　@　@　@　@　@arg0：　@口座開設伝票ステータスRowType<BR>
     * 　@　@　@　@　@　@arg1：　@１） で作成した文字列<BR>
     * 　@　@　@　@　@　@arg2：　@２） で作成した配列<BR>
     * 　@　@　@　@　@　@arg3：  ４-３） で作成したMap<BR>
     * <BR>
     * <BR>
     * @@param l_accountInfo - (顧客情報)<BR>
     * 顧客情報登録伝票レコード詳細クラス。<BR>
     * @@param l_strVoucherCreateStatus - (伝票作成状況)<BR>
     * 伝票作成状況。<BR>
     * @@param l_strErrorReasonCode - (エラー理由コード)<BR>
     * エラー理由コード。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 465BE35C01CF
     */
    private void updateAccOpenVoucherStatus(
        WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo,
        String l_strVoucherCreateStatus,
        String l_strErrorReasonCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " updateAccOpenVoucherStatus(WEB3AdminDirSecAccVoucherRecordDetail, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_accountInfo == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }
        //１） 検索文字列の作成
        //"institution_code = ? and acc_open_request_number = ?
        //   and request_code = ? and serial_no = ?"
        String l_strQueryString =
            "institution_code = ? and acc_open_request_number = ?"
            + " and request_code = ? and serial_no = ? ";

        //２） 検索条件コンテナの作成
        //  ２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成
        List l_lisValue = new ArrayList();

        //  ２-２） 検索条件の追加
        //        [add()に指定する引数]
        //　@         （引数）顧客情報.証券会社コード
        //        [add()に指定する引数]
        //　@         （引数）顧客情報.識別コード
        //        [add()に指定する引数]
        //           （引数）顧客情報.データコード
        //        [add()に指定する引数]
        //           （引数）顧客情報.伝票通番
        l_lisValue.add(l_accountInfo.institutionCode);
        l_lisValue.add(l_accountInfo.requestNumber);
        l_lisValue.add(l_accountInfo.dataCode);
        l_lisValue.add(l_accountInfo.voucherNumber);

        //  ２-３） ArrayListインスタンスをObject配列に変換
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //３） 口座開設作成伝票ステータスへ該当レコードの存在チェックを行う。
        //  ３-１） 口座開設作成伝票ステータスよりレコードを取得
        //          QueryProcessor#doFindAllQuery()メソッドをコールする。
        //          [doFindAllQuery()に指定する引数]
        //   　@        arg0：　@口座開設伝票ステータスRowType
        //  　@　@       arg1：　@１） で作成した文字列
        //　@  　@       arg2：　@２） で作成した配列
        //
        List l_lisAccOpenVoucherStatusRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenVoucherStatusRecord =
                l_queryProcessor.doFindAllQuery(
                    AccOpenVoucherStatusRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
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

        // ３-２） ３-１） の戻り値長さ == 0 の場合、「レコードが存在しません。」例外をスローする
        if (l_lisAccOpenVoucherStatusRecord.isEmpty())
        {
            log.debug("レコードが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + STR_METHOD_NAME,
                "レコードが存在しません。");
        }

        //４） 口座開設作成伝票ステータスの更新を行う
        //  ４-１） 伝票作成状況の変換を行う。
        //        [this.changeStatus()に指定する引数]
        //          status：（引数）伝票作成状況
        //          伝票作成状況変換フラグ：（引数）顧客情報.口座開設伝票フラグ
        String l_strChangeStatus =
            this.changeStatus(l_strVoucherCreateStatus, l_accountInfo.voucherFlag);

        //  ４-２） 更新内容格納用インスタンス（：Map）を生成
        Map l_map = new HashMap();

        //  ４-３） 更新内容の追加
        //        [put()に指定する引数]
        //          key："voucher_status"
        //      value： ４-１）で変換済みの伝票作成状況
        //    [put()に指定する引数]
        //      key："error_code"
        //      value：（引数）エラー理由コード
        //    [put()に指定する引数]
        //      key："recv_timestamp"
        //          value：現在日時
        l_map.put(WEB3AdminDirMapKeyDef.VOUCHER_STATUS, l_strChangeStatus);
        l_map.put(WEB3AdminDirMapKeyDef.ERROR_CODE, l_strErrorReasonCode);
        l_map.put(WEB3AdminDirMapKeyDef.RECV_TIMESTAMP, GtlUtils.getSystemTimestamp());

        //  ４-４） 口座開設作成伝票テーブルの更新
        //          QueryProcessor#doUpdateAllQuery()メソッドをコールする。
        //          [doUpdateQuery()に指定する引数]
        //   　@        arg0：　@口座開設伝票ステータスRowType
        //  　@　@       arg1：　@１） で作成した文字列
        //　@  　@       arg2：　@２） で作成した配列
        //             arg3：  ４-３） で作成したMap

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                AccOpenVoucherStatusRow.TYPE,
                l_strQueryString,
                l_queryDataContainers,
                l_map);
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get各種連絡テーブルレコード)<BR>
     * 各種連絡テーブルよりレコードを取得する。<BR>
     * 取得出来ない場合はnullを返却する。<BR>
     * <BR>
     * <BR>
     * １） 検索文字列の作成<BR>
     * 　@１-１） 検索条件文字列編集用インスタンス（：StringBuffer）を生成<BR>
     * <BR>
     * 　@１-２） 証券会社コード（検索必須条件）の追加<BR>
     * <BR>
     * 　@　@　@　@　@"institution_code = ?"<BR>
     * <BR>
     * 　@１-３） 連絡種別条件の追加<BR>
     * 　@　@１-３-１） （引数）連絡種別 != null の場合<BR>
     * <BR>
     * 　@　@　@　@　@" and inform_div = ?"<BR>
     * <BR>
     * 　@１-４） 識別コード条件追加<BR>
     * 　@　@１-４-１） （引数）識別コード != null の場合<BR>
     * <BR>
     * 　@　@　@　@　@" and request_number = ?"<BR>
     * <BR>
     * 　@１-５） 部店コード（検索必須条件）の追加<BR>
     * <BR>
     * 　@　@　@　@　@" and branch_code = ?"<BR>
     * <BR>
     * 　@１-６） 顧客コード（検索必須条件）の追加<BR>
     * 　@　@１-６-１） （引数）顧客コード != null AND （引数）顧客コードの長さ < 7 の場合<BR>
     *   <BR>
     * 　@　@　@　@　@" and account_code like ?"<BR>
     * <BR>
     * 　@　@１-６-２） （引数）顧客コード != null AND （引数）顧客コードの長さ == 7 の場合<BR>
     * <BR>
     * 　@　@　@　@　@" and account_code = ?"<BR>
     * <BR>
     * 　@１-７） データコード条件追加<BR>
     * 　@　@１-７-１） （引数）データコード != null の場合<BR>
     * <BR>
     * 　@　@　@　@　@" and request_code = ?"<BR>
     * <BR>
     * 　@　@１-７-２） （引数）データコード == null の場合<BR>
     * <BR>
     * 　@　@　@　@　@" request_code is not null"<BR>
     * <BR>
     * 　@１-８） 伝票送信日時条件追加<BR>
     * 　@　@１-８-１） （引数）伝票送信日時 != null の場合<BR>
     * <BR>
     * 　@　@　@　@　@" and to_char(send_timestamp, 'YYYYMMDD') = ?"<BR>
     * <BR>
     * 　@１-９） 検索条件文字列インスタンスをStringに変換<BR>
     * <BR>
     * <BR>
     * ２） 検索条件コンテナの作成<BR>
     * 　@２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     * 　@２-２） 証券会社コード（検索必須条件）の追加<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）証券会社コード<BR>
     * <BR>
     * 　@２-３） 連絡種別条件追加<BR>
     * 　@　@２-３-１） （引数）連絡種別 != null の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）連絡種別<BR>
     * <BR>
     * 　@２-４） 識別コード条件追加<BR>
     * 　@　@２-４-１） （引数）識別コード != null の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）識別コード<BR>
     * <BR>
     * 　@２-５） 部店コード（検索必須条件）の追加<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）部店コード<BR>
     * <BR>
     * 　@２-６） 顧客コード（検索必須条件）の追加<BR>
     * 　@　@２-６-１） （引数）顧客コード != null AND （引数）顧客コードの長さ < 7 の場合<BR>
     * <BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）顧客コード + "%"<BR>
     * <BR>
     * 　@　@２-６-２） （引数）顧客コード != null AND （引数）顧客コードの長さ == 7 の場合<BR>
     * <BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）顧客コード<BR>
     * <BR>
     * 　@２-７） データコード条件追加<BR>
     * 　@　@２-７-１） （引数）データコード != null の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）データコード<BR>
     * <BR>
     * 　@２-８） 伝票送信日時条件追加<BR>
     * 　@　@２-８-１） （引数）伝票送信日時 != null の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）伝票送信日時<BR>
     * <BR>
     * 　@２-９） ArrayListインスタンスをObject配列に変換<BR>
     * <BR>
     * <BR>
     * ３） 各種連絡テーブルよりレコードを取得<BR>
     * 　@３-１） QueryProcessor#doFindAllQuery()メソッドをコールする。<BR>
     * 　@　@　@　@[doFindAllQuery()に指定する引数]<BR>
     * 　@　@　@　@　@arg0：　@各種連絡テーブルRowType<BR>
     * 　@　@　@　@　@arg1：　@１） で作成した文字列<BR>
     * 　@　@　@　@　@arg2：　@２） で作成した配列<BR>
     * <BR>
     * ４） ３） の戻り値を返却する。戻り値の長さ==0の場合はnullを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strInformDiv - (連絡種別)<BR>
     * 連絡種別。<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード。<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード。<BR>
     * @@param l_strDataCode - (データコード)<BR>
     * データコード。<BR>
     * @@param l_strSendTimestamp - (伝票送信日時)<BR>
     * 伝票送信日時。<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 465CDED8002F
     */
    private List getVariousInformRecord(
        String l_strInstitutionCode,
        String l_strInformDiv,
        String l_strRequestNumber,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strDataCode,
        String l_strSendTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getVariousInformRecord(String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 検索文字列の作成
        //１-１） 検索条件文字列編集用インスタンス（：StringBuffer）を生成
        StringBuffer l_sbQueryString = new StringBuffer();

        //検索条件コンテナ編集用インスタンス（：ArrayList）を生成
        List l_lisValue = new ArrayList();

        //１-２） 証券会社コード（検索必須条件）の追加
        //      "institution_code = ?"
        //     （引数）証券会社コード
        l_sbQueryString.append(" institution_code = ? ");
        l_lisValue.add(l_strInstitutionCode);

        //１-３） 連絡種別条件の追加
        //  １-３-１） （引数）連絡種別 != null の場合
        //      " and inform_div = ?"
        //     （引数）連絡種別
        if (l_strInformDiv != null)
        {
            l_sbQueryString.append(" and inform_div = ? ");
            l_lisValue.add(l_strInformDiv);
        }

        //１-４） 識別コード条件追加
        //  １-４-１） （引数）識別コード != null の場合
        //      " and request_number = ?"
        //      （引数）識別コード
        if (l_strRequestNumber != null)
        {
            l_sbQueryString.append(" and request_number = ? ");
            l_lisValue.add(l_strRequestNumber);
        }

        //１-５） 部店コード（検索必須条件）の追加
        //      " and branch_code = ?"
        //       （引数）部店コード
        l_sbQueryString.append(" and branch_code = ? ");
        l_lisValue.add(l_strBranchCode);

        //１-６） 顧客コード（検索必須条件）の追加
        //  １-６-１） （引数）顧客コード != null AND （引数）顧客コードの長さ < 7 の場合
        //      " and account_code like ?"
        //      （引数）顧客コード + "%"
        if (l_strAccountCode != null && l_strAccountCode.length() < 7)
        {
            l_sbQueryString.append(" and account_code like ? ");
            l_lisValue.add(l_strAccountCode + "%");
        }

        //  １-６-２） （引数）顧客コード != null AND （引数）顧客コードの長さ == 7 の場合
        //      " and account_code = ?"
        //      （引数）顧客コード
        if (l_strAccountCode != null && l_strAccountCode.length() == 7)
        {
            l_sbQueryString.append(" and account_code = ? ");
            l_lisValue.add(l_strAccountCode);
        }

        //１-７） データコード条件追加
        //  １-７-１） （引数）データコード != null の場合
        //      " and request_code = ?"
        //      （引数）データコード
        //  １-７-２） （引数）データコード == null の場合
        //      " request_code is not null"
        if (l_strDataCode != null)
        {
            l_sbQueryString.append(" and request_code = ? ");
            l_lisValue.add(l_strDataCode);
        }
        else
        {
            l_sbQueryString.append(" and request_code is not null ");
        }

        //１-８） 伝票送信日時条件追加
        //  １-８-１） （引数）伝票送信日時 != null の場合
        //      " and to_char(send_timestamp, 'YYYYMMDD') = ?"
        //      （引数）伝票送信日時
        if (l_strSendTimestamp != null)
        {
            l_sbQueryString.append(" and to_char(send_timestamp, 'YYYYMMDD') = ? ");
            l_lisValue.add(l_strSendTimestamp);
        }

        //    １-９） 検索条件文字列インスタンスをStringに変換
        String l_strQueryString = l_sbQueryString.toString();

        //    ２-９） ArrayListインスタンスをObject配列に変換
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];
        l_lisValue.toArray(l_queryDataContainers);

        //  ３） 各種連絡テーブルよりレコードを取得
        //    ３-１） QueryProcessor#doFindAllQuery()メソッドをコールする。
        //          [doFindAllQuery()に指定する引数]
        //     　@      arg0：　@各種連絡テーブルRowType
        //    　@　@     arg1：　@１） で作成した文字列
        //  　@  　@     arg2：　@２） で作成した配列
        //  ４） ３） の戻り値を返却する。戻り値の長さ==0の場合はnullを返却する。
        List l_lisVariousInformRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisVariousInformRecord =
                l_queryProcessor.doFindAllQuery(
                    VariousInformRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
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

        //３-１） の戻り値を返却する。戻り値の長さ==0の場合はnullを返却する
        if (l_lisVariousInformRecord.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisVariousInformRecord;
    }

    /**
     * (get口座開設伝票ステータスレコード)<BR>
     * 口座開設伝票作成ステータスよりレコードを取得する。<BR>
     * 取得出来ない場合はnullを返却する。<BR>
     * <BR>
     * １） 検索文字列の作成<BR>
     * 　@１-１） 検索条件文字列編集用インスタンス（：StringBuffer）を生成<BR>
     * <BR>
     * 　@１-２） 証券会社コード（検索必須条件）の追加<BR>
     * <BR>
     * 　@　@　@　@"institution_code = ?"<BR>
     * <BR>
     * 　@１-３） 識別コード（検索必須条件）の追加<BR>
     * 　@　@１-３-１） 識別コード[]の長さ == 1 の場合<BR>
     * 　@　@　@　@" and acc_open_request_number = ?"<BR>
     * <BR>
     * 　@　@１-３-２） 識別コード[]の長さ > 1 の場合<BR>
     * 　@　@　@　@" and acc_open_request_number in (?, ?, ?,…)"<BR>
     * 　@　@　@　@　@　@　@　@　@ ※"?, "は識別コード[]の長さの回数繰り返し<BR>
     * <BR>
     * 　@１-４） データコード条件追加<BR>
     * 　@　@１-４-１） （引数）データコード != null の場合<BR>
     * <BR>
     * 　@　@　@　@" and request_code = ?"<BR>
     * <BR>
     * 　@１-５） 伝票通番条件追加<BR>
     * 　@　@１-５-１） （引数）伝票通番 != null の場合<BR>
     * <BR>
     * 　@　@　@　@" and serial_no = ?"<BR>
     * <BR>
     * 　@１-６） 伝票送信日時条件追加<BR>
     * 　@　@１-６-１） （引数）伝票送信日時 != null の場合<BR>
     * <BR>
     * 　@　@　@　@" and to_char(send_timestamp, 'YYYYMMDD') = ?"<BR>
     * <BR>
     * 　@１-７） 検索条件文字列インスタンスをStringに変換<BR>
     * <BR>
     * <BR>
     * ２） 検索条件コンテナの作成<BR>
     * 　@２-１） 検索条件コンテナ編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     * 　@２-２） 証券会社コード（検索必須条件）の追加<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）証券会社コード<BR>
     * <BR>
     * 　@２-３） 識別コード（検索必須条件）の追加<BR>
     * 　@　@２-３-１） 識別コード[]の長さ == 1 の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）識別コード[0]<BR>
     * <BR>
     * 　@　@２-３-２） 識別コード[]の長さ > 1 の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）識別コード[index]<BR>
     * 　@　@　@　@　@　@　@　@　@　@※長さの回数追加する<BR>
     * <BR>
     * 　@２-４） データコード条件追加<BR>
     * 　@　@２-４-１） （引数）データコード != null の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）データコード<BR>
     * <BR>
     * 　@２-５） 伝票通番条件追加<BR>
     * 　@　@２-５-１） （引数）伝票通番 != null の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）伝票通番<BR>
     * <BR>
     * 　@２-６） 伝票送信日時条件追加<BR>
     * 　@　@２-６-１） （引数）伝票送信日時 != null の場合<BR>
     * 　@　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@　@　@（引数）伝票送信日時<BR>
     * <BR>
     * 　@２-７） ArrayListインスタンスをObject配列に変換<BR>
     * <BR>
     * <BR>
     * ３） 口座開設伝票作成ステータステーブルよりレコードを取得<BR>
     * 　@３-１） QueryProcessor#doFindAllQuery()メソッドをコールする。<BR>
     * 　@　@　@　@[doFindAllQuery()に指定する引数]<BR>
     * 　@　@　@　@　@　@arg0：　@口座開設伝票作成ステータスRowType<BR>
     * 　@　@　@　@　@　@arg1：　@１） で作成した文字列<BR>
     * 　@　@　@　@　@　@arg2：　@２） で作成した配列<BR>
     * <BR>
     * 　@３-２） ３-１）の戻り値を返却する。戻り値の長さ==0の場合はnullを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_requestNumbers - (識別コード)<BR>
     * 識別コード。<BR>
     * @@param l_strDataCode - (データコード)<BR>
     * データコード。<BR>
     * @@param l_strSerialNo - (伝票通番)<BR>
     * 伝票通番。<BR>
     * @@param l_strSendTimestamp - (伝票送信日時)<BR>
     * 伝票送信日時。<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 465CF74C0230
     */
    private List getAccOpenVoucherStatusRecord(
        String l_strInstitutionCode,
        Object[] l_requestNumbers,
        String l_strDataCode,
        String l_strSerialNo,
        String l_strSendTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getAccOpenVoucherStatusRecord(String, Object[], String, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_requestNumbers == null)
        {
            log.debug("パラメータ値がNULL！");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL！");
        }

        //１） 検索文字列の作成
        //１-１） 検索条件文字列編集用インスタンス（：StringBuffer）を生成
        StringBuffer l_sbQueryString = new StringBuffer();

        //検索条件コンテナ編集用インスタンス（：ArrayList）を生成
        List l_lisValue = new ArrayList();

        //１-２） 証券会社コード（検索必須条件）の追加
        //      "institution_code = ?"
        //      （引数）証券会社コード
        l_sbQueryString.append(" institution_code = ? ");
        l_lisValue.add(l_strInstitutionCode);

        //１-３） 識別コード（検索必須条件）の追加
        //  １-３-１） 識別コード[]の長さ == 1 の場合
        //      " and acc_open_request_number = ?"
        //      （引数）識別コード[0]
        if (l_requestNumbers.length == 1)
        {
            l_sbQueryString.append(" and acc_open_request_number = ? ");
            l_lisValue.add(l_requestNumbers[0]);
        }

        //  １-３-２） 識別コード[]の長さ > 1 の場合
        //      " and acc_open_request_number in (?, ?, ?,…)"
        //                 ※"?, "は識別コード[]の長さの回数繰り返し
        //      ※長さの回数追加する
        else if (l_requestNumbers.length > 1)
        {
            l_sbQueryString.append(" and acc_open_request_number in (");
            for (int i = l_requestNumbers.length - 1; i >= 0; i--)
            {
                l_sbQueryString.append("?");
                l_lisValue.add(l_requestNumbers[i]);
                if (i > 0)
                {
                    l_sbQueryString.append(", ");
                }
            }
            l_sbQueryString.append(") ");
        }

        //１-４） データコード条件追加
        //  １-４-１） （引数）データコード != null の場合
        //      " and request_code = ?"
        //      （引数）データコード
        if (l_strDataCode != null)
        {
            l_sbQueryString.append(" and request_code = ? ");
            l_lisValue.add(l_strDataCode);
        }

        //１-５） 伝票通番条件追加
        //  １-５-１） （引数）伝票通番 != null の場合
        //      " and serial_no = ?"
        //      （引数）伝票通番
        if (l_strSerialNo != null)
        {
            l_sbQueryString.append(" and serial_no = ? ");
            l_lisValue.add(l_strSerialNo);
        }

        //１-６） 伝票送信日時条件追加
        //  １-６-１） （引数）伝票送信日時 != null の場合
        //      " and to_char(send_timestamp, 'YYYYMMDD') = ?"
        //      （引数）伝票送信日時
        if (l_strSendTimestamp != null)
        {
            l_sbQueryString.append(" and to_char(send_timestamp, 'YYYYMMDD') = ? ");
            l_lisValue.add(l_strSendTimestamp);
        }

        //    １-７） 検索条件文字列インスタンスをStringに変換
        String l_strQueryString = l_sbQueryString.toString();

        //    ２-７） ArrayListインスタンスをObject配列に変換
        Object[] l_queryDataContainers = new Object[l_lisValue.size()];

        l_lisValue.toArray(l_queryDataContainers);

        //  ３） 口座開設伝票作成ステータステーブルよりレコードを取得
        //    ３-１） QueryProcessor#doFindAllQuery()メソッドをコールする。
        //          [doFindAllQuery()に指定する引数]
        //     　@      arg0：　@口座開設伝票作成ステータスRowType
        //    　@　@     arg1：　@１） で作成した文字列
        //  　@  　@     arg2：　@２） で作成した配列
        //    ３-２） ３-１） の戻り値を返却する。戻り値の長さ==0の場合はnullを返却する
        List l_lisAccOpenVoucherStatusRecord = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAccOpenVoucherStatusRecord =
                l_queryProcessor.doFindAllQuery(
                    AccOpenVoucherStatusRow.TYPE,
                    l_strQueryString,
                    l_queryDataContainers);
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

        //３-１） の戻り値を返却する。戻り値の長さ==0の場合はnullを返却する
        if (l_lisAccOpenVoucherStatusRecord.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisAccOpenVoucherStatusRecord;
    }
}
@
