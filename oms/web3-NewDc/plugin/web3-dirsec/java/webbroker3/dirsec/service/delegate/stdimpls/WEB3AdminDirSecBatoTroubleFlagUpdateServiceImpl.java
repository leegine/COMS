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
filename	WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者電子鳩障害フラグ更新Impl(WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/25 許丹(中訊) 新規作成 モデルNo.117、No.125、No.126、No.128
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecBatoPreferenceRecordDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecBatoTroubleFlagUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoInstBranchPrefRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者電子鳩障害フラグ更新Impl)<BR>
 * 管理者電子鳩障害フラグ更新サービス実装クラス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public class WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl implements WEB3AdminDirSecBatoTroubleFlagUpdateService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class);

    /**
     * @@roseuid 481155FD01DC
     */
    public WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl()
    {

    }

    /**
     * 電子鳩障害フラグ更新処理を開始する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○管理者・稼動状況一覧リクエストの場合<BR>
     * 　@this.get一覧画面()をコールする。<BR>
     * <BR>
     * ○管理者・稼動状況変更確認リクエストの場合<BR>
     * 　@this.validate変更確認画面()をコールする。<BR>
     * <BR>
     * ○管理者・稼動状況変更完了リクエストの場合<BR>
     * 　@this.submit変更完了画面()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47CE39CA03CE
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
        //○管理者・稼動状況一覧リクエストの場合
        // 　@this.get一覧画面()をコールする。
        if (l_request instanceof WEB3AdminDirSecWorkingListRequest)
        {
            l_response =
                this.getListScreen((WEB3AdminDirSecWorkingListRequest)l_request);
        }
        // ○管理者・稼動状況変更確認リクエストの場合
        //　@this.validate変更確認画面()をコールする。
        else if (l_request instanceof WEB3AdminDirSecWorkingConfirmRequest)
        {
            l_response =
                this.validateChangeConfirmScreen((WEB3AdminDirSecWorkingConfirmRequest)l_request);
        }
        // ○管理者・稼動状況変更完了リクエストの場合
        //　@this.submit変更完了画面()をコールする。
        else if (l_request instanceof WEB3AdminDirSecWorkingCompleteRequest)
        {
            l_response =
                this.submitChangeCompleteScreen((WEB3AdminDirSecWorkingCompleteRequest)l_request);
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
     * (get一覧画面)<BR>
     * 稼動状況一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者）get一覧画面」参照。<BR>
     * ==================================================<BR>
     * 　@　@具体位置 : ログイン管理者が"証券会社管理者"（isDIR管理者()==false）の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・稼動状況一覧リクエストクラス。<BR>
     * @@return WEB3AdminDirSecWorkingListResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C24913021D
     */
    protected WEB3AdminDirSecWorkingListResponse getListScreen(
        WEB3AdminDirSecWorkingListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminDirSecWorkingListRequest)";
        log.exiting(STR_METHOD_NAME);

        //当リクエストデータの整合性チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックを行う。
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："Z0101" （システム管理 電子鳩障害フラグ更新）
        //is更新：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            false);

        //管理者が"DIR管理者"である場合、trueを返却する。
        //管理者が"証券会社管理者"である場合、falseを返却する。
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();
        //ログイン管理者が"証券会社管理者"
        //（isDIR管理者()==false）の場合、 例外をスローする。
        //DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //電子鳩システム会社部店別プリファ@レンステーブルレコード検索文字列を作成する。
        //[create検索条件文字列に指定する引数]
        //部店一覧：リクエストデータ.部店一覧
        String l_strQueryString = this.createQueryString(l_request.branchCode);

        //電子鳩システム会社部店別プリファ@レンステーブルレコード検索データコンテナを作成する。
        //[create検索条件データコンテナ()に指定する引数]
        //証券会社コード：get証券会社コード()の戻り値
        //部店一覧：リクエストデータ.部店一覧
        Object[] l_queryDataContainers = this.createQueryDataContainer(l_strInstitutionCode, l_request.branchCode);

        //create検索ソート条件( )
        //電子鳩システム会社部店別プリファ@レンステーブルレコード検索ソート条件を作成する。
        String l_strQuerySortCond = this.createQuerySortCond();

        //get電子鳩システム会社部店別プリファ@レンステーブルレコード(Object[], String, String)
        //管理者が権限を持つレコードを検索し、取得したレコードをListで返却する。
        //[get電子鳩システム会社部店別プリファ@レンステーブルレコード()に指定する引数]
        //検索条件データコンテナ：create検索条件データコンテナ()の戻り値
        //検索条件文字列：create検索条件文字列()の戻り値
        //検索ソート文字列：create検索ソート条件()の戻り値
        List l_lisBatoPreferenceRecords = this.getBatoPreferenceRecord(
            l_queryDataContainers,
            l_strQueryString,
            l_strQuerySortCond);

        //get電子鳩システム会社部店別プリファ@レンステーブルレコードの戻り値(稼動状況レコード詳細List)より、
        //電子鳩システム会社部店別プリファ@レンステーブルレコード詳細型配列を作成する。
        //[create電子鳩システム会社部店別プリファ@レンステーブルレコード詳細()に指定する引数]
        //稼動状況レコード詳細List：get電子鳩システム会社部店別プリファ@レンステーブルレコード()の戻り値
        WEB3AdminDirSecBatoPreferenceRecordDetail[] l_adminDirSecBatoPreferenceRecordDetails =
            this.createBatoPreferenceRecordDetail(l_lisBatoPreferenceRecords);

        //レスポンスデータを生成する。
        WEB3AdminDirSecWorkingListResponse l_response =
            (WEB3AdminDirSecWorkingListResponse)l_request.createResponse();

        //プロパティセット
        l_response.batoPreferenceRecord = l_adminDirSecBatoPreferenceRecordDetails;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更確認画面)<BR>
     * 稼動状況変更確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者）validate更新確認画面」参照。<BR>
     * ==================================================<BR>
     * 　@　@具体位置 : ログイン管理者<BR>
     * 　@　@　@　@　@　@　@　@が"証券会社管理者"（isDIR管理者()==false）の場合、<BR>
     * 　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * 　@　@具体位置 : リクエストデータのシステム障害フラグに変更<BR>
     * 　@　@　@　@　@　@　@　@がなかった場合は例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_02680<BR>
     * ==================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・稼動状況変更確認リクエストクラス。<BR>
     * @@return WEB3AdminDirSecWorkingConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C2680301FC
     */
    protected WEB3AdminDirSecWorkingConfirmResponse validateChangeConfirmScreen(
        WEB3AdminDirSecWorkingConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeConfirmScreen(WEB3AdminDirSecWorkingConfirmRequest)";
        log.exiting(STR_METHOD_NAME);

        //当リクエストデータの整合性チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックを行う。
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："Z0101" （システム管理 電子鳩障害フラグ更新）
        //is更新：TRUE
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //管理者が"DIR管理者"である場合、trueを返却する。
        //管理者が"証券会社管理者"である場合、falseを返却する。
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();
        
        //ログイン管理者が"証券会社管理者"
        //（isDIR管理者()==false）の場合、 例外をスローする。
        //DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //リクエストデータの要素分loop処理を実施。
        //[構成要素]
        //○リクエストデータ
        //　@リクエストデータ.電子鳩システム会社部店別プリファ@レンステーブル詳細(変更後)
        int l_intBatoPreferenceRecordCnt = l_request.batoPreferenceRecord.length;

        boolean l_blnChangeDiv = true;
        for (int i = 0; i < l_intBatoPreferenceRecordCnt; i++)
        {
            //アイテムの定義
            //電子鳩システム会社部店別プリファ@レンステーブルレコード検索文字列を作成する。
            String l_strQueryString = this.createQueryString();

            //電子鳩システム会社部店別プリファ@レンステーブルレコード検索データコンテナを作成する。
            //[create検索条件データコンテナ()に指定する引数]
            //証券会社コード：get証券会社コード()の戻り値
            //部店コード：リクエストデータ.電子鳩システム会社部店別プリファ@レンステーブルレコード詳細(変更後).部店コード
            Object[] l_queryDataContainers = this.createQueryDataContainer(
                l_strInstitutionCode, l_request.batoPreferenceRecord[i].branchCode);

            //現在の電子鳩システム会社部店別プリファ@レンステーブルレコードを取得する。
            //[get電子鳩システム会社部店別プリファ@レンステーブルレコード()に指定する引数]
            //検索条件データコンテナ：create検索条件データコンテナ()の戻り値
            //検索条件文字列：create検索条件文字列()の戻り値
            //検索ソート文字列：null
            List l_lisBatoPreferenceRecords =
                this.getBatoPreferenceRecord(l_queryDataContainers, l_strQueryString, null);

            //リクエストデータのシステム障害フラグに変更があることをチェックする。
            //[比較データ]
            //○変更前データ
            //　@get電子鳩システム会社部店別プリファ@レンステーブル()の戻り値.システム障害フラグ
            //○変更後データ
            //　@リクエストデータ.電子鳩システム会社部店別プリファ@レンステーブルレコード詳細(変更後).システム障害フラグ
            //変更が確認された段階でloop処理をbreakする。
            BatoInstBranchPrefRow l_batoInstBranchPrefRow = (BatoInstBranchPrefRow)l_lisBatoPreferenceRecords.get(0);
            //○変更前データ
            String l_strBeforeChangeFlag = l_batoInstBranchPrefRow.getSystemFailureFlag();
            //○変更後データ
            String l_strAfterChangeFlag = l_request.batoPreferenceRecord[i].systemTroubleDiv;

            if (!l_strBeforeChangeFlag.equals(l_strAfterChangeFlag))
            {
                l_blnChangeDiv = false;
                break;
            }
        }

        if (l_blnChangeDiv)
        {
            log.debug("変更項目がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02680,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更項目がありません。");
        }

        WEB3AdminDirSecWorkingConfirmResponse l_response =
            (WEB3AdminDirSecWorkingConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更完了画面)<BR>
     * 稼動状況更新完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者）submit更新完了画面」参照。<BR>
     * ==================================================<BR>
     * 　@　@具体位置 : ログイン管理者が"証券会社管理者"（isDIR管理者()==false）の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_00857<BR>
     * ==================================================<BR>
     * <BR>
     * ==================================================<BR>
     * 　@　@具体位置 : リクエストデータのシステム障害フラグに変更がなかった場合は<BR>
     * 　@　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@: BUSINESS_ERROR_02680<BR>
     * ==================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・稼動状況変更完了リクエストクラス。<BR>
     * @@return WEB3AdminDirSecWorkingCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26838011A
     */
    protected WEB3AdminDirSecWorkingCompleteResponse submitChangeCompleteScreen(
        WEB3AdminDirSecWorkingCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChangeCompleteScreen(WEB3AdminDirSecWorkingCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //当リクエストデータの整合性チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェックを行う。
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："Z0101" （システム管理 電子鳩障害フラグ更新）
        //is更新：TRUE
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
            true);

        //管理者が"DIR管理者"である場合、trueを返却する。
        //管理者が"証券会社管理者"である場合、falseを返却する。
        boolean l_blnIsDirAdmin = l_administrator.isDirAdministrator();

        //ログイン管理者が"証券会社管理者"
        //DIR管理者以外（isDIR管理者()==false）の場合、例外をスローする。
        if (!l_blnIsDirAdmin)
        {
            log.debug("DIR管理者権限チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DIR管理者権限チェックエラー。");
        }

        //validate取引パスワード()
        l_administrator.validateTradingPassword(l_request.password);

        // get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //リクエストデータの要素分loop処理を実施。
        //[構成要素]
        //○リクエストデータ
        //　@リクエストデータ.電子鳩システム会社部店別プリファ@レンステーブル詳細(変更後)
        int l_intBatoPreferenceRecordCnt = l_request.batoPreferenceRecord.length;

        boolean l_blnChangeDiv = true;
        for (int i = 0; i < l_intBatoPreferenceRecordCnt; i++)
        {

            //電子鳩システム会社部店別プリファ@レンステーブルレコード検索文字列を作成する。
            String l_strQueryString = this.createQueryString();

            //電子鳩システム会社部店別プリファ@レンステーブルレコード検索データコンテナを作成する。
            //[create検索条件データコンテナ()に指定する引数]
            //証券会社コード：get証券会社コード()の戻り値
            //部店コード：
            // リクエストデータ.電子鳩システム会社部店別プリファ@レンステーブルレコード詳細(変更後).部店コード
            Object[] l_queryDataContainers = this.createQueryDataContainer(
                l_strInstitutionCode, l_request.batoPreferenceRecord[i].branchCode);

            //現在の電子鳩システム会社部店別プリファ@レンステーブルレコードを取得し、
            // 取得したレコードをListで返却する。
            //[get電子鳩システム会社部店別プリファ@レンステーブルレコード()に指定する引数]
            //検索条件データコンテナ：create検索条件データコンテナ()の戻り値
            //検索条件文字列：create検索条件文字列()の戻り値
            //検索ソート文字列：null
            List l_lisBatoPreferenceRecords =
                this.getBatoPreferenceRecord(
                    l_queryDataContainers,
                    l_strQueryString,
                    null);

            //リクエストデータのシステム障害フラグに変更があることをチェックし、
            //比較データに差分がある場合、後続処理を実行する。
            //[比較データ]
            //○変更前データ
            //　@get電子鳩システム会社部店別プリファ@レンステーブル()の戻り値.システム障害フラグ
            //○変更後データ
            // リクエストデータ.電子鳩システム会社部店別プリファ@レンステーブルレコード詳細(変更後).システム障害フラグ
            BatoInstBranchPrefRow l_batoInstBranchPrefRow =
                (BatoInstBranchPrefRow)l_lisBatoPreferenceRecords.get(0);
            //○変更前データ
            String l_strBeforeChangeFlag = l_batoInstBranchPrefRow.getSystemFailureFlag();
            //○変更後データ
            String l_strAfterChangeFlag = l_request.batoPreferenceRecord[i].systemTroubleDiv;

            if (!l_strBeforeChangeFlag.equals(l_strAfterChangeFlag))
            {
                //該当レコードを更新する。
                //[update電子鳩システム会社部店別プリファ@レンステーブル()に指定する引数]
                //システム障害フラグ：
                // リクエストデータ.電子鳩システム会社部店別プリファ@レンステーブルレコード詳細(変更後).システム障害フラグ
                //更新条件文字列：create検索条件文字列()の戻り値
                //更新条件データコンテナ：create検索条件データコンテナ()の戻り値
                //更新者コード：get管理者コード()の戻り値
                this.updateBatoPreference(
                    l_strAfterChangeFlag,
                    l_strQueryString,
                    l_queryDataContainers,
                    l_strAdministratorCode);

                l_blnChangeDiv = false;
            }
        }

        if (l_blnChangeDiv)
        {
            log.debug("変更項目がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02680,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更項目がありません。");
        }

        WEB3AdminDirSecWorkingCompleteResponse l_response =
            (WEB3AdminDirSecWorkingCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルレコード検索文字列を作成する。<BR>
     * <BR>
     * １）空の文字列を作成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * 　@　@"institution_code=?"　@を１）の文字列に追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * 　@　@" and branch_code in (?,?,?…)" (※)　@を１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@(※　@?　@は(引数)部店コードの要素数分記述)<BR>
     * <BR>
     * ４）文字列を返却する。<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード。<BR>
     * @@return String
     * @@roseuid 47CDF643027A
     */
    private String createQueryString(String[] l_strBranchCodes)
    {
        final String STR_METHOD_NAME = "createQueryString(String[])";
        log.entering(STR_METHOD_NAME);
        //空の文字列を作成する。
        StringBuffer l_sbSql = new StringBuffer();

        //証券会社コード
        // "institution_code=?"　@を１）の文字列に追加する。
        l_sbSql.append(" institution_code = ? ");

        //部店コード
        //" and branch_code in (?,?,?…)" (※)　@を１）の文字列に追加する。
        if (l_strBranchCodes.length > 0)
        {
            l_sbSql.append(" and branch_code in ( ? ");
            for (int i = 1; i < l_strBranchCodes.length; i++)
            {
                l_sbSql.append(" , ? ");
            }
            l_sbSql.append(" ) ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbSql.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルレコード検索データコンテナを作成する。<BR>
     * <BR>
     * １）検索条件コンテナ編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     * ２）検索条件の追加<BR>
     * 　@２－１）Listオブジェクトに以下を追加<BR>
     * 　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@(引数)証券会社コード<BR>
     * <BR>
     * 　@２－２）Listオブジェクトに以下を追加<BR>
     * 　@　@　@((引数)部店コードの要素数分、以下を実施)<BR>
     * 　@　@　@[add()に指定する引数]<BR>
     * 　@　@　@(引数)部店コード<BR>
     * <BR>
     * ３）ArrayListインスタンスをObject配列に変換<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コード。<BR>
     * @@return Object[]
     * @@roseuid 47D766370122
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, String[] l_strBranchCodes)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, String[])";
        log.entering(STR_METHOD_NAME);

        //検索条件コンテナ編集用インスタンス（：ArrayList）を生成
        List l_lisQueryContainers =  new ArrayList();

        //Listオブジェクトに以下を追加
        //[add()に指定する引数]
        //(引数)証券会社コード
        l_lisQueryContainers.add(l_strInstitutionCode);

        //Listオブジェクトに以下を追加
        //((引数)部店コードの要素数分、以下を実施)
        //[add()に指定する引数]
        //(引数)部店コード
        if (l_strBranchCodes.length > 0)
        {
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                l_lisQueryContainers.add(l_strBranchCodes[i]);
            }
        }

        //ArrayListインスタンスをObject配列に変換
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (create検索ソート条件)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルレコード検索ソート条件を作成する。<BR>
     * <BR>
     * １）ソート条件文字列オブジェクト(：String)を作成する。<BR>
     * <BR>
     * ２）ソートキー項目を１）で作成した文字列に追加する。<BR>
     * 　@　@ソートキー項目：部店コード(列物理名：branch_code)<BR>
     * <BR>
     * ３）作成したソート条件文字列を返却する。<BR>
     * @@return String
     * @@roseuid 47D771A3011D
     */
    private String createQuerySortCond()
    {
        final String STR_METHOD_NAME = "createQuerySortCond()";
        log.entering(STR_METHOD_NAME);

        //ソート条件文字列オブジェクト(：String)を作成する。
        StringBuffer l_strQuerySortCond = new StringBuffer();

        //ソートキー項目を１）で作成した文字列に追加する。
        //ソートキー項目：部店コード(列物理名：branch_code)
        l_strQuerySortCond.append(" branch_code ");

        log.exiting(STR_METHOD_NAME);
        return l_strQuerySortCond.toString();
    }

    /**
     * (get電子鳩システム会社部店別プリファ@レンステーブルレコード)<BR>
     * 管理者が権限を持つレコードを検索し、取得したレコードをListで返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@電子鳩システム会社部店別プリファ@レンステーブルRowType<BR>
     * 　@　@　@arg1：　@(引数)検索条件文字列<BR>
     * 　@　@　@arg2：　@(引数)検索ソート条件<BR>
     * 　@　@　@arg3：　@null<BR>
     * 　@　@　@arg4：　@(引数)検索条件データコンテナ<BR>
     * <BR>
     * 　@　@※検索結果が0件の場合、エラーを返却する。<BR>
     * 　@　@エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037）<BR>
     * <BR>
     * ２）１） の戻り値を返却する。<BR>
     * @@param l_queryDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ。<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列。<BR>
     * @@param l_strQuerySortCond - (検索ソート条件)<BR>
     * 検索ソート条件。<BR>
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 47C4FE5E0232
     */
    private List getBatoPreferenceRecord(
        Object[] l_queryDataContainers,
        String l_strQueryString,
        String l_strQuerySortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBatoPreferenceRecord(Object[], String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisBatoPreferenceRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //QueryProcessor.doFindAllQuery()メソッドをコールする。
            //[doFindAllQuery()にセットするパラメータ]
            //arg0：　@電子鳩システム会社部店別プリファ@レンステーブルRowType
            //arg1：　@(引数)検索条件文字列
            //arg2：　@(引数)検索ソート条件
            //arg3：　@null
            //arg4：　@(引数)検索条件データコンテナ
            l_lisBatoPreferenceRecords = l_queryProcessor.doFindAllQuery(
                BatoInstBranchPrefRow.TYPE,
                l_strQueryString,
                l_strQuerySortCond,
                null,
                l_queryDataContainers);
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

        //検索結果が0件の場合、エラーを返却する。
        //エラーメッセージ「条件に該当するデータが存在しない。」（BUSINESS_ERROR_01037)
        if (l_lisBatoPreferenceRecords.size() == 0)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisBatoPreferenceRecords;
    }

    /**
     * (create電子鳩システム会社部店別プリファ@レンステーブルレコード詳細)<BR>
     * get電子鳩システム会社部店別プリファ@レンステーブルレコードの戻り値<BR>
     * (稼動状況レコード詳細List)より、アップロードテーブルレコード詳細型配列を作成する。<BR>
     * <BR>
     * １）ArrayListオブジェクトの生成。<BR>
     * <BR>
     * ２）(引数)稼動状況レコード詳細Listの要素分、Loop処理をおこなう。<BR>
     * <BR>
     * 　@２－１）電子鳩システム会社部店別プリファ@レンステーブルレコード詳細クラス<BR>
     * 　@　@　@のオブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@２－１）で生成したオブジェクトに以下の内容をセットする。<BR>
     * <BR>
     * 　@　@　@(引数)稼動状況レコード詳細List.部店コード<BR>
     * 　@　@　@(引数)稼動状況レコード詳細List.システム障害フラグ<BR>
     * <BR>
     * 　@２－３）　@１）で生成したArrayListオブジェクトに<BR>
     * 　@　@　@電子鳩システム会社部店別プリフレンステーブルレコード詳細オブジェクトをadd()する。<BR>
     * <BR>
     * ３）電子鳩システム会社部店別プリファ@レンステーブルレコード詳細クラス型<BR>
     * 　@　@の配列オブジェクトをArrayListオブジェクトのサイズで生成する。<BR>
     * <BR>
     * ４）toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。<BR>
     * <BR>
     * 　@　@ArrayListオブジェクト.toArray(電子鳩システム会社部店別プリファ@レンステーブル<BR>
     * 　@　@レコード詳細クラス型の配列オブジェクト);<BR>
     * <BR>
     * ５）変換した配列オブジェクトを返却する。<BR>
     * @@param l_lisWorkingRecordDetailLists - (稼動状況レコード詳細List)<BR>
     * 稼動状況レコード詳細List。<BR>
     * @@return WEB3AdminDirSecBatoPreferenceRecordDetail[]
     * @@roseuid 47C393060206
     */
    private WEB3AdminDirSecBatoPreferenceRecordDetail[] createBatoPreferenceRecordDetail(
        List l_lisWorkingRecordDetailLists)
    {
        final String STR_METHOD_NAME = "createBatoPreferenceRecordDetail(List)";
        log.entering(STR_METHOD_NAME);

        //１）ArrayListオブジェクトの生成。
        List l_lisBatoPreferenceRecordDetails = new ArrayList();

        //(引数)稼動状況レコード詳細Listの要素分、Loop処理をおこなう。
        Iterator l_iterator = l_lisWorkingRecordDetailLists.iterator();
        WEB3AdminDirSecBatoPreferenceRecordDetail l_adminDirSecBatoPreferenceRecordDetail = null;
        BatoInstBranchPrefRow l_batoInstBranchPrefRow = null;
        while (l_iterator.hasNext())
        {
            //２－１）電子鳩システム会社部店別プリファ@レンステーブルレコード詳細クラスのオ
            //ブジェクトを生成する。
            l_adminDirSecBatoPreferenceRecordDetail = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoInstBranchPrefRow = (BatoInstBranchPrefRow)l_iterator.next();

            //２－１）で生成したオブジェクトに以下の内容をセットする。
            //(引数)稼動状況レコード詳細List.部店コード
            //(引数)稼動状況レコード詳細List.システム障害フラグ
            l_adminDirSecBatoPreferenceRecordDetail.branchCode = l_batoInstBranchPrefRow.getBranchCode();
            l_adminDirSecBatoPreferenceRecordDetail.systemTroubleDiv =
                l_batoInstBranchPrefRow.getSystemFailureFlag();

            //１）で生成したArrayListオブジェクトに
            //電子鳩システム会社部店別プリフレンステーブルレコード詳細オブジェクトをadd()する。
            l_lisBatoPreferenceRecordDetails.add(l_adminDirSecBatoPreferenceRecordDetail);
        }

        //電子鳩システム会社部店別プリファ@レンステーブルレコード詳細クラス型
        //の配列オブジェクトをArrayListオブジェクトのサイズで生成する。
        WEB3AdminDirSecBatoPreferenceRecordDetail[] l_adminDirSecBatoPreferenceRecordDetails =
            new WEB3AdminDirSecBatoPreferenceRecordDetail[l_lisBatoPreferenceRecordDetails.size()];

        //toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。
        //ArrayListオブジェクト.toArray(
        // 電子鳩システム会社部店別プリファ@レンステーブルレコード詳細クラス型の配列オブジェクト);
        l_lisBatoPreferenceRecordDetails.toArray(l_adminDirSecBatoPreferenceRecordDetails);

        //変換した配列オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_adminDirSecBatoPreferenceRecordDetails;
    }

    /**
     * (create検索条件文字列)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルレコード検索文字列を作成する。<BR>
     * <BR>
     * １）空の文字列を作成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * 　@　@"institution_code=?"　@を１）の文字列に追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * 　@　@" and branch_code=?"　@を１）の文字列に追加する。<BR>
     * <BR>
     * ４）文字列を返却する。<BR>
     * @@return String
     * @@roseuid 47D77E4B02C1
     */
    private String createQueryString()
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //空の文字列を作成する。
        StringBuffer l_sbSql = new StringBuffer();

        //証券会社コード
        //"institution_code=?"　@を１）の文字列に追加する。
        l_sbSql.append(" institution_code = ? ");

        //部店コード
        //" and branch_code=?"　@を１）の文字列に追加する。
        l_sbSql.append(" and branch_code = ? ");

        log.exiting(STR_METHOD_NAME);
        return l_sbSql.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルレコード検索データコンテナを作成する。<BR>
     * <BR>
     * １）検索条件コンテナ編集用インスタンス（：ArrayList）を生成<BR>
     * <BR>
     * ２）検索条件の追加<BR>
     * 　@　@[add()に指定する引数]<BR>
     * 　@　@　@(引数)証券会社コード<BR>
     * 　@　@[add()に指定する引数]<BR>
     * 　@　@　@(引数)部店コード<BR>
     * <BR>
     * ３）ArrayListインスタンスをObject配列に変換<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード。<BR>
     * @@return Object[]
     * @@roseuid 47D7802B00DE
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, String)";
        log.entering(STR_METHOD_NAME);

        //検索条件コンテナ編集用インスタンス（：ArrayList）を生成
        List l_lisQueryContainers = new ArrayList();

        //検索条件の追加
        //[add()に指定する引数]
        //(引数)証券会社コード
        //[add()に指定する引数]
        //(引数)部店コード
        l_lisQueryContainers.add(l_strInstitutionCode);
        l_lisQueryContainers.add(l_strBranchCode);

        //ArrayListインスタンスをObject配列に変換
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (update電子鳩システム会社部店別プリファ@レンステーブル)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルの該当レコードを更新する。<BR>
     * <BR>
     * [where条件]<BR>
     * ○証券会社コード：(引数)更新条件データコンテナ.証券会社コード<BR>
     * ○部店コード：(引数)更新条件データコンテナ.部店コード<BR>
     * <BR>
     * [更新カラム]<BR>
     * ○システム障害フラグ：(引数)システム障害フラグ<BR>
     * <BR>
     * １）更新内容格納用インスタンス（：Map）を生成<BR>
     * <BR>
     * ２）更新内容の追加<BR>
     * 　@　@[put()に指定する引数]<BR>
     * 　@　@　@key："system_failure_flag"<BR>
     * 　@　@　@value：(引数)システム障害フラグ<BR>
     * 　@　@[put()に指定する引数]<BR>
     * 　@　@　@key："last_updater"<BR>
     * 　@　@　@value：(引数)更新者コード<BR>
     * 　@　@[put()に指定する引数]<BR>
     * 　@　@　@key："last_updated_timestamp"<BR>
     * 　@　@　@value：TradingSystem#getSystemTimestamp()の戻り値<BR>
     * <BR>
     * ３）電子鳩システム会社部店別プリファ@レンステーブルの更新<BR>
     * 　@　@QueryProcessor.doUpdateAllQuery()メソッドをコールする。<BR>
     * 　@　@[doUpdateAllQuery()に指定する引数]<BR>
     * 　@　@　@arg0：　@電子鳩システム会社部店別プリファ@レンステーブルRowType<BR>
     * 　@　@　@arg1：　@(引数)更新条件文字列<BR>
     * 　@　@　@arg2：　@(引数)更新条件データコンテナ<BR>
     * 　@　@　@arg3：　@２） で作成したMap<BR>
     * @@param l_strSystemTroubleDiv - (システム障害フラグ)<BR>
     * システム障害フラグ。<BR>
     * @@param l_strUpdateString - (更新条件文字列)<BR>
     * 更新条件文字列。<BR>
     * @@param l_updateDataContainers - (更新条件データコンテナ)<BR>
     * 更新条件データコンテナ。<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C3A45902A4
     */
    private void updateBatoPreference(
        String l_strSystemTroubleDiv,
        String l_strUpdateString,
        Object[] l_updateDataContainers,
        String l_strUpdaterCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateBatoPreference(String, String, Object[], String)";
        log.entering(STR_METHOD_NAME);

        //１）更新内容格納用インスタンス（：Map）を生成
        Map l_map = new HashMap();

        //２） 更新内容の追加
        //[put()に指定する引数]
        //  key："system_failure_flag"
        //  value：(引数)システム障害フラグ
        //[put()に指定する引数]
        //  key："last_updater"
        //  value：(引数)更新者コード
        //[put()に指定する引数]
        //  key："last_updated_timestamp"
        //  value：TradingSystem#getSystemTimestamp()の戻り値
        l_map.put("system_failure_flag", l_strSystemTroubleDiv);
        l_map.put("last_updater", l_strUpdaterCode);
        l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //３） 電子鳩システム会社部店別プリファ@レンステーブルの更新
        //QueryProcessor.doUpdateAllQuery()メソッドをコールする。
        //[doUpdateAllQuery()に指定する引数]
        //arg0：　@電子鳩システム会社部店別プリファ@レンステーブルRowType
        //arg1：　@(引数)更新条件文字列
        //arg2：　@(引数)更新条件データコンテナ
        //arg3：  ２） で作成したMap
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                BatoInstBranchPrefRow.TYPE,
                l_strUpdateString,
                l_updateDataContainers,
                l_map);
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
