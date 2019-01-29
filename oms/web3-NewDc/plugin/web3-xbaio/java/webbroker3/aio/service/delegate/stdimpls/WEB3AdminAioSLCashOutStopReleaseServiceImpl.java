head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLCashOutStopReleaseServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン出金拘束金解除サービス実装クラス(WEB3AdminAioSLCashOutStopReleaseServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 金傑（中訊）新規作成 モデルNo.764
Revision History : 2007/09/18 金傑（中訊）モデルNo.768
Revision History : 2007/09/18 金傑（中訊）モデルNo.772
Revision History : 2007/09/20 金傑（中訊）モデルNo.779
Revision History : 2007/10/30 金傑（中訊）ＤＢ更新仕様No.159
Revision History : 2007/10/31 張騰宇 (中訊) 仕様変更 モデルNo.818
Revision History : 2007/11/08 趙林鵬 (中訊) 仕様変更 モデルNo.820
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.define.WEB3SLSortKeyDef;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListRequest;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListResponse;
import webbroker3.aio.message.WEB3SLCashOutStopInfoUnit;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.aio.service.delegate.WEB3AdminAioSLCashOutStopReleaseService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AmountLockFlgDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SecurityCashoutRestraintDao;
import webbroker3.gentrade.data.SecurityCashoutRestraintParams;
import webbroker3.gentrade.data.SecurityCashoutRestraintRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (担保ローン出金拘束金解除サービスImpl)<BR>
 * 担保ローン出金拘束金解除サービス実装クラス<BR>
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioSLCashOutStopReleaseServiceImpl implements WEB3AdminAioSLCashOutStopReleaseService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLCashOutStopReleaseServiceImpl.class);

    /**
     * 証券担保ローン出金停止解除処理を開始する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○証券担保ローン出金停止解除確認リクエストの場合<BR>
     * this.validate出金停止解除確認画面()をコールする。<BR>
     * <BR>
     * ○証券担保ローン出金停止解除完了リクエストの場合 <BR>
     * this.submit出金停止解除完了画面()をコールする。<BR>
     * <BR>
     * ○証券担保ローン出金拘束金一覧リクエストの場合<BR>
     * this.get担保ローン出金拘束金一覧画面()をコールする。<BR>
     *
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
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

        // 証券担保ローン出金停止解除確認リクエストの場合
        if (l_request instanceof WEB3AdminSLCashOutStopReleaseConfirmRequest)
        {
            // this.get出金拘束金解除確認画面()をコールする
            l_response = this.validateSLCashOutStopReleaseConfirmScreen(
                (WEB3AdminSLCashOutStopReleaseConfirmRequest)l_request);
        }

        // 証券担保ローン出金停止解除完了リクエストの場合
        else if (l_request instanceof WEB3AdminSLCashOutStopReleaseCompleteRequest)
        {
            // this.get出金拘束金解除完了画面()をコールする
            l_response = this.submitSLCashOutStopCompleteScreen(
                (WEB3AdminSLCashOutStopReleaseCompleteRequest)l_request);
        }

        // 証券担保ローン出金拘束金一覧リクエストの場合
        else if (l_request instanceof WEB3AdminSLRestraintMoneyListRequest)
        {
            // this.get担保ローン出金拘束金一覧画面()をコールする
            l_response = this.getSLRestraintMoneyListScreen(
                (WEB3AdminSLRestraintMoneyListRequest)l_request);
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
     * (get担保ローン出金拘束金一覧画面)<BR>
     * 担保ローン出金拘束金一覧画面表示処理<BR>
     * <BR>
     * シーケンス図<BR>
     *「get担保ローン出金拘束金一覧」参照。<BR>
     * ========================================================== <BR>
     * シーケンス図 ：(担保ローン出金拘束金解除 / get担保ローン出金拘束金一覧) <BR>
     * 具体位置：(検索対象レコードが存在しない場合、例外をスローする。)<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00398 <BR>
     * ========================================================== <BR>
     * @@param l_request - (証券担保ローン出金拘束金一覧リクエストデータ)<BR>
     * 証券担保ローン出金拘束金一覧リクエストデータ
     * @@return WEB3AdminSLRestraintMoneyListResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLRestraintMoneyListResponse getSLRestraintMoneyListScreen(
        WEB3AdminSLRestraintMoneyListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLRestraintMoneyListScreen(WEB3AdminSLRestraintMoneyListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLRestraintMoneyListResponse l_response =
            (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        // 管理者オブジェクトを取得する。
        // [validate権限()に指定する引数]
        // 機@能カテゴリコード："B0603"(システム管理 証券担保ローン(出金可能額制御管理))

        // is更新：FALSE
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE, false);

        // create取得条件文字列
        // 部店コード：リクエストデータ.部店コード
        // 顧客コード：リクエストデータ.顧客コード
        // 出金停止区分：リクエストデータ.出金停止区分
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        String l_strGetCondCharacter = this.createGetCondCharacterString(l_request.branchCode,
            l_request.accountCode, l_request.cashOutStopDiv);

        // create取得条件データコンテナ
        // 担保ローン出金拘束テーブル取得条件データコンテナ作成。
        // [this.create取得条件データコンテナ()に指定する引数]
        //　@証券会社コード：管理者.get証券会社コード()の戻り値
        //  部店コード：リクエストデータ.部店コード 
        //　@顧客コード：リクエストデータ.顧客コード
        //　@出金停止区分：リクエストデータ.出金停止区分

        Object[] l_bindVars = this.createQueryDataContainer(l_strInstitutionCode, l_request.branchCode,
            l_request.accountCode, l_request.cashOutStopDiv);

        // ソート条件文字列を編集する。
        // [this.createソートキー()に指定する引数]
        //　@ソートキー：引数:リクエストデータ.ローンソートキー
        String l_strSortKey = this.createSortKey(l_request.sortKeys);

        List l_lisSearchResults = null;

        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        try
        {
            // 担保ローン出金拘束金テーブルからレコードを取得する。
            // Rowタイプ：　@担保ローン出金拘束金Row.TYPE
            // Where：　@create取得条件文字列()の戻り値
            // orderBy：　@createソートキー()の戻り値
            // condition：　@null
            // リスト：　@create検索条件データコンテナ()の戻り値
            // ページサイズ：　@リクエストデータ.ページ内表示数 をint型に変換した値
            // ページ番号：　@リクエストデータ.要求ページ番号 - 1 をint型に変換した値

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisSearchResults = l_queryProcessor.doFindAllQuery(
                SecurityCashoutRestraintRow.TYPE,
                l_strGetCondCharacter,
                l_strSortKey,
                null,
                l_bindVars,
                l_intPageSize,
                l_intPageIndex - 1);
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

        // 検索対象レコードが存在しない場合、例外をスローする
        if (l_lisSearchResults == null || l_lisSearchResults.isEmpty())
        {
            log.debug("該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        // l_list : doFindAllQuery（）の戻り値
        // l_intRequestPageIndex :　@リクエストデータ.要求ページ番号をint型に変換した値
        // l_intRequestPageSize :　@リクエストデータ.ページ内表示行数をint型に変換した値
        WEB3PageIndexInfo l_web3PageIndexInfo =
            new WEB3PageIndexInfo(l_lisSearchResults, l_intPageIndex, l_intPageSize);

        List l_lisReturneds = l_web3PageIndexInfo.getListReturned();

        List l_lisCashOutStopInfos = new ArrayList();
        int l_intCntReturned = l_lisReturneds.size();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;

        for (int i = 0; i < l_intCntReturned; i++)
        {
            SecurityCashoutRestraintRow l_securityCashoutRestraintRow =
                (SecurityCashoutRestraintRow)l_lisReturneds.get(i);

            // 顧客を取得する
            // [get顧客（）に指定する引数]
            //　@証券会社コード：担保ローン出金拘束金Row.get証券会社コード()の戻り値
            //　@部店コード：担保ローン出金拘束金Row.get部店コード()の戻り値
            //　@口座コード：担保ローン出金拘束金Row.get顧客コード()の戻り値

            l_mainAccount = l_accountManager.getMainAccount(
                l_securityCashoutRestraintRow.getInstitutionCode(),
                l_securityCashoutRestraintRow.getBranchCode(),
                l_securityCashoutRestraintRow.getAccountCode());

            // create出金停止情報
            WEB3SLCashOutStopInfoUnit l_slCashOutStopInfoUnit =
                this.createCashOutStopInfoUnit(l_securityCashoutRestraintRow, l_mainAccount.getDisplayAccountName());

            l_lisCashOutStopInfos.add(l_slCashOutStopInfoUnit);
        }

        // 出金停止情報一覧：
        l_response.cashOutStopInfoList = (WEB3SLCashOutStopInfoUnit[])l_lisCashOutStopInfos.toArray(
            new WEB3SLCashOutStopInfoUnit[l_lisCashOutStopInfos.size()]);

        // 総ページ
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalPages());

        // 総レコー
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_web3PageIndexInfo.getTotalRecords());

        // 表示ページ番号：リクエスト.要求ページ番号
        l_response.pageIndex = l_request.pageIndex;

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }

    /**
     * (validate出金停止解除確認画面)<BR>
     * 出金停止解除確認画面表示処理<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate担保ローン出金拘束金解除」参照。<BR>
     * =============================================== <BR>
     * 具体位置　@　@:担保ローン出金拘束金テーブルRowにて該当データが取得できない場合、<BR>
     * 例外をthrowする。<BR>
     * class　@:　@WEB3BusinessLayerException  <BR>
     * tag　@　@:　@BUSINESS_ERROR_01037<BR>
     * =============================================== <BR>
     *
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLCashOutStopReleaseConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLCashOutStopReleaseConfirmResponse validateSLCashOutStopReleaseConfirmScreen(
        WEB3AdminSLCashOutStopReleaseConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSLCashOutStopReleaseConfirmScreen(WEB3AdminSLCashOutStopReleaseConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        // 管理者オブジェクトを取得する。
        // [validate権限()に指定する引数]
        // 機@能カテゴリコード："B0603"(システム管理 証券担保ローン(出金可能額制御管理))

        // is更新：TRUE
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE, true);

        // 担保ローン出金拘束金テーブルRowを取得する
        SecurityCashoutRestraintRow l_securityCashoutRestraintRow =
            this.getSecurityCashoutRestraintRow(l_request.accountId);

        if (l_securityCashoutRestraintRow == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        // 顧客を取得する。
        // [get顧客（）に指定する引数]
        //　@証券会社コード：担保ローン出金拘束金Row.get証券会社コード()の戻り値
        //　@部店コード：担保ローン出金拘束金Row.get部店コード()の戻り値
        //　@口座コード：担保ローン出金拘束金Row.get顧客コード()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_mainAccount = null;

        l_mainAccount = l_accountManager.getMainAccount(
            l_securityCashoutRestraintRow.getInstitutionCode(),
            l_securityCashoutRestraintRow.getBranchCode(),
            l_securityCashoutRestraintRow.getAccountCode());

        // create出金停止情報
        WEB3SLCashOutStopInfoUnit l_slCashOutStopInfoUnit =
            this.createCashOutStopInfoUnit(l_securityCashoutRestraintRow, l_mainAccount.getDisplayAccountName());

        WEB3AdminSLCashOutStopReleaseConfirmResponse l_response =
            (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();

        l_response.cashOutStopInfo = l_slCashOutStopInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit出金停止解除完了画面)<BR>
     * 出金停止解除完了画面表示処理<BR>
     * <BR>
     * シーケンス図<BR>
     *「submit担保ローン出金拘束金解除」参照。<BR>
     *
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLCashOutStopReleaseCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLCashOutStopReleaseCompleteResponse submitSLCashOutStopCompleteScreen(
        WEB3AdminSLCashOutStopReleaseCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitSLCashOutStopCompleteScreen(WEB3AdminSLCashOutStopReleaseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        // 管理者オブジェクトを取得する。
        // [validate権限()に指定する引数]
        // 機@能カテゴリコード："B0603"(システム管理 証券担保ローン(出金可能額制御管理))

        // is更新：TRUE
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE, true);

        // 暗証番号のチェックを行う
        // [validate取引パスワード()に指定する引数]
        // パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        // 管理者コードを取得する
        String l_strAdminCode = l_administrator.getAdministratorCode();

        // 担保ローン出金拘束金テーブルを更新する
        // [update担保ローン出金拘束金テーブル（）に指定する引数]
        //　@口座ID：リクエストデータ.口座ID
        //　@管理者コード：管理者.get管理者コード()の戻り値
        //　@現在時刻：TradingSystem.getSystemTimestamp()の戻り値

        Timestamp l_tsNowTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        this.updateSecurityCashoutRestraintRow(
            l_request.accountId,
            l_strAdminCode,
            l_tsNowTime);

        WEB3AdminSLCashOutStopReleaseCompleteResponse l_response =
            (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get担保ローン出金拘束金テーブルレコード)<BR>
     * 担保ローン出金拘束金Rowオブジェクトを取得する。<BR>
     * <BR>
     * 担保ローン出金拘束金テーブルから、（引数）口座IDを主キーとして検索を行い、<BR>
     * 取得したRowオブジェクトを返却する。<BR>
     * <BR>
     * ※)取得出来ない場合はnullを返却する。<BR>
     *
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID
     * @@return SecurityCashoutRestraintRow
     * @@throws WEB3BaseException
     */
      private SecurityCashoutRestraintRow getSecurityCashoutRestraintRow(long l_lngAccountId)
            throws WEB3BaseException
      {
        final String STR_METHOD_NAME =
            "getSecurityCashoutRestraintRow(long)";
        log.entering(STR_METHOD_NAME);

        SecurityCashoutRestraintRow l_securityCashoutRestraintRow = null;
        // 担保ローン出金拘束金テーブルから、（引数）口座IDを主キーとして検索を行い、
        // 取得したRowオブジェクトを返却する。
        try
        {
            l_securityCashoutRestraintRow = SecurityCashoutRestraintDao.findRowByPk(l_lngAccountId);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

          log.exiting(STR_METHOD_NAME);
          return l_securityCashoutRestraintRow;
      }

    /**
     * (update担保ローン出金拘束金テーブル)<BR>
     * 主キーで担保ローン出金拘束金テーブルの更新を行う。<BR>
     * <BR>
     * （引数)口座IDを主キーとして担保ローン出金拘束金テーブルの <BR>
     * 金額ロックフラグ(出金停止状況)を出金停止状態から解除状態にUPDATEを行う。 <BR>
     * <BR>
     * １）更新項目<BR>
     * 金額ロックフラグ： 0 (通常)<BR>
     * 更新者コード： (引数)管理者コード [管理者.get管理者コード()の戻り値] <BR>
     * 更新日時： (引数)現在時刻 [TradingSystem.getSystemTimestamp()の戻り値]<BR>
     * <BR>
     * ※）「DB更新仕様」を参照。<BR>
     *
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID
     * @@param l_strAdminCode - (管理者コード)<BR>
     * 管理者コード
     * @@param l_datNowTime - (現在時刻)<BR>
     * 現在時刻
     * @@throws WEB3BaseException
     */
    private void updateSecurityCashoutRestraintRow(
        long l_lngAccountId, String l_strAdminCode, Date l_datNowTime) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            "updateSecurityCashoutRestraintRow(long, String, Date)";
        log.entering(STR_METHOD_NAME);
        // １）更新項目
        // 金額ロックフラグ：　@0 (通常)
        // 更新者コード：　@  (引数)管理者コード　@[管理者.get管理者コード()の戻り値]
        // 更新日時：　@      (引数)現在時刻　@[TradingSystem.getSystemTimestamp()の戻り値]

        try
        {
            SecurityCashoutRestraintRow l_securityCashoutRestraintRow =
                this.getSecurityCashoutRestraintRow(l_lngAccountId);
            if (l_securityCashoutRestraintRow == null)
            {
                log.debug("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }

            SecurityCashoutRestraintParams l_securityCashoutRestraintParams =
                new SecurityCashoutRestraintParams(l_securityCashoutRestraintRow);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_securityCashoutRestraintParams.setAmountLockFlg(WEB3AmountLockFlgDef.NORMAL);
            l_securityCashoutRestraintParams.setLastUpdater(l_strAdminCode);
            l_securityCashoutRestraintParams.setLastUpdatedTimestamp(l_datNowTime);

            l_queryProcessor.doUpdateQuery(l_securityCashoutRestraintParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create出金停止情報)<BR>
     * 出金停止情報を作成する。<BR>
     * <BR>
     * 出金停止情報オブジェクトにプロパティをセットし、オブジェクトを返却する。<BR>
     * <BR>
     * １）出金停止情報オブジェクトを生成する。<BR>
     * <BR>
     * ２）出金停止情報オブジェクトにプロパティをセットする。<BR>
     * 口座ID：　@　@　@　@　@　@(引数)担保ローン出金拘束金Row.口座ID<BR>
     * 部店コード：　@　@　@　@(引数)担保ローン出金拘束金Row.部店コード <BR>
     * 顧客コード：　@　@　@　@(引数)担保ローン出金拘束金Row.顧客コード <BR>
     * 顧客名：　@　@　@　@　@　@(引数)顧客名 <BR>
     * 利用可能枠：　@　@　@  (引数)担保ローン出金拘束金Row.利用可能枠 <BR>
     * 出金拘束金：　@　@　@  (引数)担保ローン出金拘束金Row.出金拘束金 <BR>
     * 出金可能額：　@　@　@  (引数)担保ローン出金拘束金Row.出金可能額 <BR>
     * 出金停止区分：　@    (引数)担保ローン出金拘束金Row.金額ロックフラグ <BR>
     * <BR>
     * ３）出金停止情報オブジェクトを返却する。<BR>
     *
     * @@param l_securityCashoutRestraintRow - (担保ローン出金拘束金Row)<BR>
     * 担保ローン出金拘束金Row
     * @@param l_strAccountName - (顧客名)<BR>
     * 顧客名
     * @@return WEB3SLCashOutStopInfoUnit
     */
    private WEB3SLCashOutStopInfoUnit createCashOutStopInfoUnit(
        SecurityCashoutRestraintRow l_securityCashoutRestraintRow, String l_strAccountName)
    {
        final String STR_METHOD_NAME =
            "createCashOutStopInfoUnit(SecurityCashoutRestraintRow, String)";
        log.entering(STR_METHOD_NAME);

        // １）出金停止情報オブジェクトを生成する。
        WEB3SLCashOutStopInfoUnit l_slCashOutStopInfoUnit = new WEB3SLCashOutStopInfoUnit();

        // ２）出金停止情報オブジェクトにプロパティをセットする

        // (引数)担保ローン出金拘束金Row.口座ID
        l_slCashOutStopInfoUnit.accountId = l_securityCashoutRestraintRow.getAccountId();

        // (引数)担保ローン出金拘束金Row.部店コード
        l_slCashOutStopInfoUnit.branchCode = l_securityCashoutRestraintRow.getBranchCode();

        // (引数)担保ローン出金拘束金Row.顧客コード
        l_slCashOutStopInfoUnit.accountCode = l_securityCashoutRestraintRow.getAccountCode();

        // (引数)顧客名
        l_slCashOutStopInfoUnit.accountName = l_strAccountName;

        // (引数)担保ローン出金拘束金Row.利用可能枠
        l_slCashOutStopInfoUnit.cashoutLimit = String.valueOf(l_securityCashoutRestraintRow.getUseEnableLimit());

        // (引数)担保ローン出金拘束金Row.出金拘束金
        l_slCashOutStopInfoUnit.cashoutRestraint = String.valueOf(l_securityCashoutRestraintRow.getCashoutRestraint());

        // (引数)担保ローン出金拘束金Row.出金可能額
        l_slCashOutStopInfoUnit.cashoutPossAmt = String.valueOf(l_securityCashoutRestraintRow.getCashoutEnablieAmount());

        // (引数)担保ローン出金拘束金Row.金額ロックフラグ
        l_slCashOutStopInfoUnit.cashoutStopDiv = l_securityCashoutRestraintRow.getAmountLockFlg();

        log.exiting(STR_METHOD_NAME);
        return l_slCashOutStopInfoUnit;
    }

    /**
     * (create取得条件文字列)<BR>
     * 担保ローン出金拘束金テーブルからデータを取得する際の条件を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）証券会社コード <BR>
     * 　@２－１） "institution_code=?"　@を１）の文字列に追加する。<BR>
     *  <BR>
     * ３）部店コード  <BR>
     * 　@３－１）(引数)部店コード != null　@の場合  <BR>
     * 　@　@" and branch_code=?"　@を１）の文字列に追加する。<BR>
     *  <BR>
     * ４）顧客コード<BR>
     * 　@４－１）(引数)顧客コード != null　@の場合<BR>
     * 　@　@" and account_code like ? %"　@を１）の文字列に追加する。<BR>
     *  <BR>
     * ５）金額ロックフラグ<BR>
     * 　@５－１）(引数)出金停止区分 !=null　@の場合 <BR>
     * 　@　@" and amount_lock_flg=?"　@を１）の文字列に追加する。<BR>
     *  <BR>
     * ６）文字列を返却する。<BR>
     *
     * @@param l_strBranchCode - (部店コード)<BR>
     * リクエストデータ.部店コード
     * @@param l_strAccountCode - (顧客コード) <BR>
     * リクエストデータ.顧客コード
     * @@param l_strCashOutStopDiv - (出金停止区分) <BR>
     * リクエストデータ.出金停止区分
     * @@return String
     */
    private String createGetCondCharacterString(String l_strBranchCode,
        String l_strAccountCode, String l_strCashOutStopDiv)
    {
        final String STR_METHOD_NAME =
            "createGetCondCharacterString(String, String, String)";
        log.entering(STR_METHOD_NAME);

        // １）空の文字列を生成する。
        StringBuffer l_sbGetCondCharacter = new StringBuffer();

        //２）証券会社コード
        //　@２－１） "institution_code=?"　@を１）の文字列に追加する。

        l_sbGetCondCharacter.append("institution_code=? ");

       //３）部店コード
       //　@３－１）(引数)部店コード != null　@の場合
       //　@　@" and branch_code=?"　@を１）の文字列に追加する。
        if (l_strBranchCode != null)
        {
            l_sbGetCondCharacter.append(" and branch_code=? ");
        }

       // ４）顧客コード
       //　@４－１）(引数)顧客コード != null　@の場合
       //    " and account_code like ? %"　@を１）の文字列に追加する。
        if (l_strAccountCode != null)
        {
            l_sbGetCondCharacter.append(" and account_code like ? || '%'");
        }

        // ５）金額ロックフラグ
       // 　@５－１）(引数)出金停止区分 !=null　@の場合
       //　@　@" and amount_lock_flg=?"　@を１）の文字列に追加する。
        if (l_strCashOutStopDiv != null)
        {
            l_sbGetCondCharacter.append(" and amount_lock_flg=?");
        }

        log.exiting(STR_METHOD_NAME);
        // ６）文字列を返却する。
        return l_sbGetCondCharacter.toString();
    }

    /**
     * (create取得条件データコンテナ)<BR>
     * 担保ローン出金拘束金テーブルからデータを取得する際の条件のデータコンテナを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * 　@２－１）(引数)証券会社コードを１）のListに追加する。<BR>
     * <BR>
     * ３）部店コード <BR>
     * 　@３－１）(引数)部店コード != null　@の場合<BR>
     *   (引数)部店コードを１）のListに追加する。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * 　@４－１）(引数)顧客コード != null　@の場合<BR>
     *   (引数)顧客コードを１）のListに追加する。<BR>
     * <BR>
     * ５）出金停止区分<BR>
     * 　@５－１）(引数)出金停止区分 != null　@の場合<BR>
     * 　@(引数)出金停止区分を１）のListに追加する。<BR>
     * <BR>
     * ６）Listから配列を取得して、返却する。<BR>
     *
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 管理者.get証券会社コード()の戻り値
     * @@param l_strBranchCode - (部店コード)<BR>
     * リクエストデータ.部店コード
     * @@param l_strAccountCode - (顧客コード) <BR>
     * リクエストデータ.顧客コード
     * @@param l_strCashOutStopDiv - (出金停止区分) <BR>
     * リクエストデータ.出金停止区分
     * @@return Object[]
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountCode, String l_strCashOutStopDiv)
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // １）空のArrayListを生成する。
        List l_lisQueryDataContainers = new ArrayList();

        // ２）証券会社コード
         // ２－１）(引数)証券会社コードを１）のListに追加する。
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        // ３）部店コード
        // ３－１）(引数)部店コード != null　@の場合
        //　@(引数)部店コードを１）のListに追加する。
        if (l_strBranchCode != null)
        {
            l_lisQueryDataContainers.add(l_strBranchCode);
        }

        // ４）顧客コード
        //  ４－１）(引数)顧客コード != null　@の場合
        //   (引数)顧客コードを１）のListに追加する。
        if (l_strAccountCode != null)
        {
            l_lisQueryDataContainers.add(l_strAccountCode);
        }

        //５）出金停止区分
        // ５－１）(引数)出金停止区分 != null　@の場合
        //  (引数)出金停止区分を１）のListに追加する。
        if (l_strCashOutStopDiv != null)
        {
            l_lisQueryDataContainers.add(l_strCashOutStopDiv);
        }

        // ６）Listから配列を取得して、返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisQueryDataContainers.toArray(new Object[l_lisQueryDataContainers.size()]);
    }

    /**
     * (createソートキー)<BR>
     * ソート条件文字列を編集する。<BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、ソート条件文字列（order by句）を編集する。<BR>
     * <BR>
     * １）パラメータ.ソートキーの要素数分以下の処理を繰り返し、<BR>
     * 　@　@　@ソート条件文字列を作成する。<BR>
     * <BR>
     * 　@１－１）ソートキー.キー項目に対応するテーブル列物理名をソート条件に追加する。<BR>
     * <BR>
     * 　@　@[ソートキー.キー項目 = 部店コード の場合] <BR>
     * 　@　@　@　@　@担保ローン出金拘束金テーブル.branch_code<BR>
     * <BR>
     * 　@　@[ソートキー.キー項目 = 顧客コード の場合] <BR>
     * 　@　@　@　@　@担保ローン出金拘束金テーブル.account_code<BR>
     * <BR>
     * 　@１－２）ソート条件に該当するソート条件を編集する。<BR>
     * 　@　@　@　@昇順：asc <BR>
     * 　@　@　@　@降順：desc <BR>
     * <BR>
     * ２） 作成したソート条件文字列を返却する。<BR>
     *
     * @@param  l_sortKeys - (ソートキー) <BR>
     * 担保ローンソートキーオブジェクト
     * @@return String
     */
    private String createSortKey(WEB3SLSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createSortKey(WEB3SLSortKey[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortKey = new StringBuffer();
        // １）パラメータ.ソートキーの要素数分以下の処理を繰り返し、
        int l_intSortKeyCnt = l_sortKeys.length;

        for (int i = 0; i < l_intSortKeyCnt; i++)
        {
            // １－１）ソートキーを編集
           // ソートキー.キー項目 = 顧客コード の場合
            if (WEB3SLSortKeyDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortKey.append(" account_code ");
            }
            else
            {
                l_sbSortKey.append(" branch_code ");
            }

            // １－２）ソート条件に該当するソート条件を編集する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortKey.append(" asc ,");
            }
            else
            {
                l_sbSortKey.append(" desc ,");
            }

        }

        // ２） 作成したソート条件文字列を返却する。
        l_sbSortKey = l_sbSortKey.deleteCharAt(l_sbSortKey.length()-1);
        log.exiting(STR_METHOD_NAME);
        return l_sbSortKey.toString();
    }

}
@
