head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座切替・電子交付申込サービス実装クラス（WEB3AdminInformSwElecDeliApplyServiceImpl.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 金傑（中訊）新規作成 モデルNo.099
Revision History : 2007/07/19 金傑（中訊）新規作成 モデルNo.102
Revision History : 2007/08/13 金傑（中訊）仕様変更モデル104
Revision History : 2007/08/30 トウ鋒鋼（中訊）仕様変更モデル107
Revision History : 2007/09/19 趙林鵬（中訊）仕様変更モデル112
Revision History : 2007/09/19 トウ鋒鋼（中訊）仕様変更モデル112
Revision History : 2007/09/25 趙林鵬（中訊）仕様変更モデル116,ＤＢ更新仕様017
Revision History : 2007/10/03 長瀬（SCS）仕様変更 実装の問題006
Revision History : 2007/10/23 張騰宇 (中訊) モデル121 ＤＢ更新仕様 018 019
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformConditionRegVoucher;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (管理者口座切替・電子交付申込サービスImpl) <BR>
 * 管理者口座切替・電子交付申込サービス実装クラス <BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminInformSwElecDeliApplyServiceImpl implements WEB3AdminInformSwElecDeliApplyService
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformSwElecDeliApplyServiceImpl.class);

    /**
     * 管理者口座切替・電子交付申込サービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (l_request instanceof WEB3AdminInformAccSwElecDeliApplySrcRequest)
        {
            // get検索画面
            l_response = getSearchScreen((WEB3AdminInformAccSwElecDeliApplySrcRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyInpRequest)
        {
            // get入力画面
            l_response = getInputScreen((WEB3AdminInformAccSwElecDeliApplyInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyConfRequest)
        {
            // validate申込
            l_response = validateApply((WEB3AdminInformAccSwElecDeliApplyConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyCmpRequest)
        {
            // submit申込
            l_response = submitApply((WEB3AdminInformAccSwElecDeliApplyCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliApplyRefRequest)
        {
            // get照会画面
            l_response = getReferenceScreen((WEB3AdminInformAccSwElecDeliApplyRefRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliChangeConfRequest)
        {
            // validate変更
            l_response = validateChange((WEB3AdminInformAccSwElecDeliChangeConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliChangeCmpRequest)
        {
            // submit変更
            l_response = submitChange((WEB3AdminInformAccSwElecDeliChangeCmpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliDeleteConfRequest)
        {
            // validate取消
            l_response = validateCancel((WEB3AdminInformAccSwElecDeliDeleteConfRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformAccSwElecDeliDeleteCmpRequest)
        {
            // submit取消
            l_response = submitCancel((WEB3AdminInformAccSwElecDeliDeleteCmpRequest)l_request);
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
     * （get検索画面）<BR>
     * 検索画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（口座切替・電子交付申込）get検索画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込検索リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplySrcResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplySrcResponse getSearchScreen(
        WEB3AdminInformAccSwElecDeliApplySrcRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSearchScreen(WEB3AdminInformAccSwElecDeliApplySrcRequest)";
        log.entering(STR_METHOD_NAME);
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        // 権限チェックを行う。
        // [引数]
        //  機@能カテゴリコード： "A0106"
        //  is更新： false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, false);
        // レスポンスデータを生成する。
        WEB3AdminInformAccSwElecDeliApplySrcResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_request.createResponse();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （get入力画面）<BR>
     * 入力画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（口座切替・電子交付申込）get入力画面」 参照。<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@:　@get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@:　@各種連絡行が取得できない場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class　@:  WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01037<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込入力リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplyInpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplyInpResponse getInputScreen(
        WEB3AdminInformAccSwElecDeliApplyInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminInformAccSwElecDeliApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        // 管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        // 管理者権限チェックを行う。
        // [引数]
        //  機@能カテゴリコード： "A0106"
        //  is更新： false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        // 部店権限のチェックを行う。
        l_administrator.validateBranchPermission(l_request.branchCode);

        // 証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();


        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            // get顧客()
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 顧客.getDataSourceObject()をコールし、顧客Rowを取得する。
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        // 口座切替・電子交付申込共通サービを取得する。
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //get各種連絡一覧(String, String, String, String)
        //各種連絡一覧を取得する。
        //[引数]
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード：　@リクエストデータ.部店コード
        //連絡種別：　@リクエストデータ.連絡種別
        //顧客コード：　@リクエストデータ.顧客コード
        List l_lisVariousInforms = l_informAccSwElecDeliApplyCommonService.getVariousInformList(
            l_strInstitutionCode, l_request.branchCode, l_request.informType, l_request.accountCode);

        //get各種連絡一覧の戻り値() != null の場合、以下の処理を行う。
        if (l_lisVariousInforms != null)
        {
            //isトリガ発行(発注条件 : String)
            //発注条件： 0（DEFAULT）
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

            //validate伝票作成(String, boolean)
            //伝票作成が可能かチェックを行う。
            //[引数]
            //作成状況：　@get各種連絡一覧.get(0).get作成状況()の戻り値
            //トリガー発行区分：　@isトリガー発行()の戻り値
            String l_strStatus = ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus();

            l_informAccSwElecDeliApplyCommonService.validateVoucherMake(
                l_strStatus, l_blnIsSubmitMarketTrigger);
        }

        // create口座切替・電子交付申込情報(顧客)
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_elecApplyInfoBefore =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        // レスポンスデータを生成する。
        WEB3AdminInformAccSwElecDeliApplyInpResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_request.createResponse();

        // レスポンスデータ.部店コード  ＝ 顧客行.部店コード
        l_response.branchCode = l_mainAcountRow.getBranchCode();
        // レスポンスデータ.顧客コード   ＝ 顧客行.口座コード
        l_response.accountCode = l_mainAcountRow.getAccountCode();
        // レスポンスデータ.顧客名    ＝ 顧客行.名前（苗字）
        l_response.accountName = l_mainAcountRow.getFamilyName();
        // レスポンスデータ.変更前情報  ＝ create口座切替・電子交付申込情報(）の戻り値
        l_response.beforeInfo = l_elecApplyInfoBefore;
        // レスポンスデータ.変更後情報  ＝ create口座切替・電子交付申込情報(）の戻り値
        l_response.changedInfo = l_elecApplyInfoBefore;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （validate申込）<BR>
     * 口座切替・電子交付申込確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（口座切替・電子交付申込）validate申込」 参照。<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@:　@get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込確認リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplyConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplyConfResponse validateApply(
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApply(WEB3AdminInformAccSwElecDeliApplyConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        // 管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 権限チェックを行う。
        // [引数]
        //  機@能カテゴリコード： "A0106"
        //  is更新： true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        // 部店権限のチェックを行う。
        l_administrator.validateBranchPermission(l_request.branchCode);

        // 証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            // get顧客()
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 口座切替・電子交付申込共通サービを取得する。
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //get各種連絡一覧(String, String, String, String)
        //[引数]
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード：　@リクエストデータ.部店コード
        //連絡種別：　@リクエストデータ.連絡種別
        //顧客コード：　@リクエストデータ.顧客コード
        List l_lisVariousInforms = l_informAccSwElecDeliApplyCommonService.getVariousInformList(
            l_strInstitutionCode, l_request.branchCode, l_request.informType, l_request.accountCode);

        //get各種連絡一覧の戻り値() != null の場合、以下の処理を行う。
        if (l_lisVariousInforms != null)
        {
            //isトリガ発行(発注条件 : String)
            //発注条件： 0（DEFAULT）
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

            //validate伝票作成(String, boolean)
            //伝票作成が可能かチェックを行う。
            //[引数]
            //作成状況：　@get各種連絡一覧.get(0).get作成状況()の戻り値
            //トリガー発行区分：　@isトリガー発行()の戻り値
            //開始予定日：　@get各種連絡一覧.get(0).getテキスト２()の戻り値　@※Date型に変換
            String l_strStatus = ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus();
            String l_strExtText2 = ((VariousInformRow)l_lisVariousInforms.get(0)).getExtText2();
            Date l_datBegin = WEB3DateUtility.getDate(
                l_strExtText2,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

            l_informAccSwElecDeliApplyCommonService.validateVoucherMake(
                l_strStatus, l_blnIsSubmitMarketTrigger, l_datBegin);
        }

        //create口座切替・電子交付申込情報(顧客)
        //[引数]
        //顧客：　@get顧客()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 口座切替・電子交付申込情報)
        //[引数]
        //変更前情報：　@create口座切替・電子交付申込情報()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        l_informAccSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_applyInfo, l_request.changedInfo);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliApplyConfResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （submit申込）<BR>
     * 口座切替・電子交付申込確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（口座切替・電子交付申込）submit申込」 参照。<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@:　@get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込完了リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplyCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliApplyCmpResponse submitApply(
        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitApply(WEB3AdminInformAccSwElecDeliApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        //注文受付可能かをチェックする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // リクエストデータの整合性をチェックする。
        l_request.validate();

        // 管理者インスタンスを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 権限チェックを行う。
        // [引数]
        //  機@能カテゴリコード： "A0106"
        //  is更新： true
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        // 部店権限のチェックを行う。
        l_administrator.validateBranchPermission(l_request.branchCode);

        // 取引パスワードが正しいかのチェックを行う。
        l_administrator.validateTradingPassword(l_request.password);

        // 証券会社コードを取得する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        // 管理者コードを取得する。
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            // get顧客()
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // 口座切替・電子交付申込共通サービを取得する。
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //get各種連絡一覧(String, String, String, String)
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード：　@リクエストデータ.部店コード
        //連絡種別：　@リクエストデータ.連絡種別
        //顧客コード：　@リクエストデータ.顧客コード
        List l_lisVariousInformList = l_informAccSwElecDeliApplyCommonService.getVariousInformList(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.informType,
            l_request.accountCode);

        //isトリガ発行(発注条件 : String)
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        //get各種連絡一覧の戻り値() != null の場合、以下の処理を行う。
        if (l_lisVariousInformList != null)
        {
            //作成状況：　@get各種連絡一覧.get(0).get作成状況()の戻り値
            VariousInformRow l_variousInformRow =
                (VariousInformRow)l_lisVariousInformList.get(0);

            //開始予定日：　@get各種連絡一覧.get(0).getテキスト２()の戻り値　@※Date型に変換
            Date l_datStartScheduleDate = WEB3DateUtility.getDate(
                l_variousInformRow.getExtText2(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

            //validate伝票作成(String, boolean, Date)
            l_informAccSwElecDeliApplyCommonService.validateVoucherMake(
                l_variousInformRow.getStatus(),
                l_blnIsSubmitMarketTrigger,
                l_datStartScheduleDate);
        }

        //create口座切替・電子交付申込情報(顧客)
        //顧客：　@get顧客()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_adminInformAccSwitchElecDeliApplyInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 口座切替・電子交付申込情報)
        // [引数]
        //変更前情報：　@create口座切替・電子交付申込情報()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        l_informAccSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_adminInformAccSwitchElecDeliApplyInfo,
            l_request.changedInfo);

        // create口座切替・電子交付申込日付情報
        // [引数]
         // 税区分：　@リクエストデータ.税区分
         // 信用取引税区分：　@リクエストデータ.信用取引税区分
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_elecDeliAppDtInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliAppDtInfo(
                l_request.changedInfo.taxType, l_request.changedInfo.marginTaxType);

        // create各種連絡
        // [引数]
         // 顧客：　@get顧客()の戻り値
         // 変更後情報：　@リクエストデータ.変更後情報
         // 日付情報：　@create口座切替・電子交付申込日付情報()の戻り値
         // 連絡種別：　@リクエストデータ.連絡種別
        WEB3Inform l_inform = l_informAccSwElecDeliApplyCommonService.createVariousInform(
            l_mainAccount, l_request.changedInfo, l_elecDeliAppDtInfo, l_request.informType);

        // 連絡管理識別コード採番サービスを取得する。
        WEB3InformHostReqOrderNumberManageService l_informHostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);

        // get新規識別コード
        //  証券会社コード： get証券会社コード( )の戻り値
        //  連絡種別： リクエストデータ.連絡種別
        String l_strRequestNumber = l_informHostReqOrderNumberManageService.getNewOrderRequestCode(
            l_strInstitutionCode, l_request.informType);

        // saveNew各種連絡
        // 更新者コード：　@get管理者コード()の戻り値
        // 識別コード：　@get新規識別コード()の戻り値
        // 作成状況：　@3:受付完了
        l_inform.saveNewInform(
            l_strAdministratorCode, l_strRequestNumber, WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);

        //get新規識別コード(証券会社コード : String, 部店コード : String, 銘柄タイプ : ProductTypeEnum)
        //証券会社コード： get証券会社コード()の戻り値
        //部店コード： リクエストデータ.部店コード
        //銘柄タイプ： ProductTypeEnum.その他
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_strInstitutionCode,
            l_request.branchCode,
            ProductTypeEnum.OTHER);

        //create取報・取残電子交付・特定口座登録行(顧客, 各種連絡params)
        //顧客：　@get顧客()の戻り値
        //各種連絡行：　@各種連絡オブジェクト.getDataSourceObject()の戻り値
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            l_informAccSwElecDeliApplyCommonService.createHostConditionRegVoucherParams(
                l_mainAccount,
                (VariousInformParams)l_inform.getDataSourceObject());

        //取報・取残電子交付・特定口座登録(取報・取残電子交付・特定口座登録Params, String)
        //取報・取残電子交付・特定口座登録行 : create取報・取残電子交付・特定口座登録行()の戻り値
        //伝票識別コード : 注文識別コード採番サービス.get新規識別コード()の戻り値
        WEB3InformConditionRegVoucher l_informConditionRegVoucher =
            new WEB3InformConditionRegVoucher(
                l_hostConditionRegVoucherParams,
                l_strNewNumber);

        //is伝票作成
        boolean l_blnVoucherMake = l_informConditionRegVoucher.isVoucherMake();

        Map l_updateInformMap = new HashMap();
        //is伝票作成()の戻り値がtrueの場合
        if (l_blnVoucherMake)
        {
            //save取報・取残電子交付・特定口座登録伝票キュー
            //取報・取残電子交付・特定口座登録（GI311）キューテーブルにレコードを作成する。
            l_informConditionRegVoucher.saveHostConditionRegVoucher();

            //isトリガ発行 == true の場合、以下の処理を実行
            if (l_blnIsSubmitMarketTrigger)
            {
                //トリガ発行(String, String)
                //証券会社コード：　@get証券会社コード()の戻り値
                //データコード：　@”GI843”
                l_informAccSwElecDeliApplyCommonService.submitMarketTrigger(
                    l_strInstitutionCode,
                    WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
            }

            //MAPオブジェクトの生成

            //伝票識別コード：    注文識別コード採番サービス.get新規識別コード（）で取得した値
            l_updateInformMap.put("order_request_number", l_strNewNumber);

            //更新者コード： get管理者コード()の戻り値
            l_updateInformMap.put("last_updater", l_strAdministratorCode);

            //伝票作成状況： 1：作成済
            l_updateInformMap.put("status", WEB3VoucherCreateStatusDef.CREATE_COMPLETE);

            //更新日時：   処理日時
            l_updateInformMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            //データコード： “GI843”
            l_updateInformMap.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
        }
        else
        {
            //MAPオブジェクトの生成

            //伝票識別コード：  null
            l_updateInformMap.put("order_request_number", null);

            //更新者コード： get管理者コード()の戻り値
            l_updateInformMap.put("last_updater", l_strAdministratorCode);

            //伝票作成状況：3：受付完了
            l_updateInformMap.put("status", WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);

            //更新日時：   処理日時
            l_updateInformMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            //データコード：null
            l_updateInformMap.put("request_code", null);
        }

        //update各種連絡(Map)
        //DB更新仕様「分配金振替口座登録_各種連絡テーブル.xls」
        //分配金振替口座登録_update_DB更新仕様参照
        l_inform.updateInform(l_updateInformMap);

        // モバイル専用口座開設区分に変更がある場合
        // リクエストデータ.変更後情報.モバイル専用口座開設区分 !=
        //create口座切替・電子交付申込情報()の戻り値.モバイル専用口座開設区分
        if (!WEB3Toolkit.isEquals(l_adminInformAccSwitchElecDeliApplyInfo.mobileAccoutDiv,
            l_request.changedInfo.mobileAccoutDiv))
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                // [doUpdateQuery()に指定する引数]
                // arg0（primaryKey）：　@顧客.get顧客行.口座ID
                // arg1（changes）：
                //　@　@【ｘTrade】補足資料.DB更新「口座切替・電子交付申込_顧客マスター」参照。
                MainAccountPK l_mainAccountPK =
                    new MainAccountPK(l_mainAccount.getMainAccountRow().getAccountId());

                Map l_mainAccountMap = new HashMap();

                //モバイル専用口座開設区分
                l_mainAccountMap.put("only_mobile_open_div", l_request.changedInfo.mobileAccoutDiv);

                //モバイル専用口座開設区分更新者コード
                l_mainAccountMap.put("only_mbl_opn_div_last_updater", l_strAdministratorCode);

                //モバイル専用口座開設区分更新日時
                l_mainAccountMap.put(
                    "only_mbl_opn_div_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_mainAccountMap);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        // レスポンスデータを生成する。
        WEB3AdminInformAccSwElecDeliApplyCmpResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_request.createResponse();

        // 口座切替・電子交付申込日付情報  ＝　@create口座切替・電子交付申込日付情報()の戻り値
        l_response.dateInfo = l_elecDeliAppDtInfo;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * 口座切替・電子交付申込参照画面の取得を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者（口座切替・電子交付申込）get参照画面」 参照。<BR>
     * ========================================================<BR>
     * シーケンス図 (「管理者（口座切替・電子交付申込）get参照画面」)<BR>
     * 　@　@具体位置：get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * シーケンス図 (「管理者（口座切替・電子交付申込）get参照画面」)<BR>
     * 　@　@具体位置：各種連絡行が取得できない場合（戻り値 == null）、<BR>
     * 　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyRefResponse
     * @@throws WEB3BaseException
     */

    protected WEB3AdminInformAccSwElecDeliApplyRefResponse getReferenceScreen(
        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminInformAccSwElecDeliApplyRefRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： "A0106"
        //is更新： false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, false);

        //validate部店権限(部店コード : String)
        //[引数]
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社( )
        Institution l_institution = l_administrator.getInstitution();

        //get顧客()にて該当データが取得できない場合、例外をthrowする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            //[引数]
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_gentradeAccountManager.getMainAccount(
                l_institution.getInstitutionCode(),
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する顧客が存在しません。");
        }

        VariousInformParams l_variousInformParams = null;
        //get各種連絡行(String, String, String, String)
        //[get各種連絡行()に指定する引数]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 部店コード：　@リクエストデータ.部店コード
        // 識別コード：　@リクエストデータ.識別コード
        // 連絡種別：　@リクエストデータ.連絡種別
        l_variousInformParams = WEB3Inform.getVariousInform(
            l_institution.getInstitutionCode(),
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //各種連絡行が取得できない場合（戻り値 == null）、例外をスローする。
        if (l_variousInformParams == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //口座切替・電子交付申込共通サービス取得
        WEB3InformAccSwElecDeliApplyCommonService l_service =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //create口座切替・電子交付申込情報(各種連絡Params)
        //[引数]
        // 各種連絡行： get各種連絡行()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_service.createAccSwitchElecDeliApplyInfo(l_variousInformParams);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliApplyRefResponse l_response =
            (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();

        //レスポンスデータ.部店コード    ＝ 各種連絡行.部店コード
        l_response.branchCode = l_variousInformParams.getBranchCode();
        //レスポンスデータ.顧客コード    ＝ 各種連絡行.顧客コード
        l_response.accountCode = l_variousInformParams.getAccountCode();
        //レスポンスデータ.顧客名  ＝ 各種連絡行.顧客名
        l_response.accountName = l_variousInformParams.getAccountName();
        //レスポンスデータ.変更前情報    ＝ create口座切替・電子交付申込情報()の戻り値
        l_response.beforeInfo = l_applyInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * 口座切替・電子交付申込変更確認画面の取得を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者（口座切替・電子交付申込）validate変更」 参照。<BR>
     * ========================================================<BR>
     * シーケンス図 (「管理者（口座切替・電子交付申込）validate変更」)<BR>
     * 　@　@具体位置：get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * シーケンス図 (「管理者（口座切替・電子交付申込）validate変更」)<BR>
     * 　@　@具体位置：各種連絡行が取得できない場合（戻り値 == null）、<BR>
     * 　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliChangeConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliChangeConfResponse validateChange(
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminInformAccSwElecDeliChangeConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： "A0106"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        //validate部店権限(部店コード : String)
        //[引数]
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get顧客()にて該当データが取得できない場合、例外をthrowする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            //[引数]
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する顧客が存在しません。");
        }

        VariousInformParams l_variousInformParams = null;
        //get各種連絡行(String, String, String, String)
        //[get各種連絡行()に指定する引数]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 部店コード：　@リクエストデータ.部店コード
        // 識別コード：　@リクエストデータ.識別コード
        // 連絡種別：　@リクエストデータ.連絡種別
        l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //各種連絡行が取得できない場合（戻り値 == null）、例外をスローする。
        if (l_variousInformParams == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        // isトリガ発行(発注条件 : String)
        //[引数]
        // 発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

        //口座切替・電子交付申込共通サービス取得
        WEB3InformAccSwElecDeliApplyCommonService l_service =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate伝票変更(String, boolean)
        //作成状況：　@get各種連絡行().get作成状況()の戻り値
        //トリガー発行区分：　@isトリガー発行()の戻り値
        l_service.validateVoucherChange(l_variousInformParams.getStatus(), l_blnIsSubmitMarketTrigger);

        //create口座切替・電子交付申込情報(各種連絡Params)
        //[引数]
        //各種連絡行： get各種連絡行()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_service.createAccSwitchElecDeliApplyInfo(l_variousInformParams);

        //validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 口座切替・電子交付申込情報)
        //[引数]
        //変更前情報：　@create口座切替・電子交付申込情報()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        l_service.validateAccSwitchElecDeliApplyInfo(l_applyInfo, l_request.changedInfo);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliChangeConfResponse l_response =
            (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更)<BR>
     * 口座切替・電子交付申込変更完了画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（口座切替・電子交付申込）submit変更」 参照。<BR>
     * ========================================================<BR>
     * シーケンス図 (「管理者（口座切替・電子交付申込）validate変更」)<BR>
     * 　@　@具体位置：get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_01035<BR>
     * ========================================================<BR>
     * ========================================================<BR>
     * シーケンス図 (「管理者（口座切替・電子交付申込）validate変更」)<BR>
     * 　@　@具体位置：各種連絡行が取得できない場合（戻り値 == null）、<BR>
     * 　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliChangeCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliChangeCmpResponse submitChange(
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminInformAccSwElecDeliChangeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： "A0106"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY, true);

        //validate部店権限(部店コード : String)
        //[引数]
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //validate取引パスワード(パスワード : String)
        //[validate取引パスワード()に指定する引数]
        // パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get顧客()にて該当データが取得できない場合、例外をthrowする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        MainAccount l_mainAccount = null;
        try
        {
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            //[引数]
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する顧客が存在しません。");
        }

        VariousInformParams l_variousInformParams = null;
        //get各種連絡行(String, String, String, String)
        //[get各種連絡行()に指定する引数]
        // 証券会社コード：　@get証券会社コード()の戻り値
        // 部店コード：　@リクエストデータ.部店コード
        // 識別コード：　@リクエストデータ.識別コード
        // 連絡種別：　@リクエストデータ.連絡種別
        l_variousInformParams = WEB3Inform.getVariousInform(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.requestNumber,
            l_request.informType);

        //各種連絡行が取得できない場合（戻り値 == null）、例外をスローする。
        if (l_variousInformParams == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        // isトリガ発行(発注条件 : String)
        //[引数]
        // 発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);

        //口座切替・電子交付申込共通サービス取得
        WEB3InformAccSwElecDeliApplyCommonService l_service =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate伝票変更(String, boolean)
        //作成状況：　@get各種連絡行().get作成状況()の戻り値
        //トリガー発行区分：　@isトリガー発行()の戻り値
        l_service.validateVoucherChange(l_variousInformParams.getStatus(), l_blnIsSubmitMarketTrigger);

        //create口座切替・電子交付申込情報(各種連絡Params)
        //[引数]
        // 各種連絡行： get各種連絡行()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_service.createAccSwitchElecDeliApplyInfo(l_variousInformParams);

        //validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 口座切替・電子交付申込情報)
        //[引数]
        //変更前情報：　@create口座切替・電子交付申込情報()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        l_service.validateAccSwitchElecDeliApplyInfo(l_applyInfo, l_request.changedInfo);

        //create口座切替・電子交付申込日付情報(String, String)
        //[引数]
        //税区分：　@リクエストデータ.変更後情報.税区分
        //信用取引税区分：　@リクエストデータ.変更後情報.信用取引税区分
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_deliAppDtInfo =
            l_service.createAccSwitchElecDeliAppDtInfo(
                l_request.changedInfo.taxType, l_request.changedInfo.marginTaxType);

        //create各種連絡(顧客, 口座切替・電子交付申込情報, 口座切替・電子交付申込日付情報, String)
        //[引数]
        //顧客：　@get顧客()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        //日付情報：　@create口座切替・電子交付申込日付情報()の戻り値
        //連絡種別：　@リクエストデータ.連絡種別
        WEB3Inform l_inform = l_service.createVariousInform(
            l_mainAccount, l_request.changedInfo, l_deliAppDtInfo, l_request.informType);

        //get管理者コード( )
        String l_strAdminCode = l_administrator.getAdministratorCode();

        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        //get新規識別コード(証券会社コード : String, 部店コード : String, 銘柄タイプ : ProductTypeEnum)
        //[引数]
        // 証券会社コード： get証券会社コード( )の戻り値
        // 部店コード： リクエストデータ.部店コード
        // 銘柄タイプ： ProductTypeEnum.その他
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_strInstitutionCode,
            l_request.branchCode,
            ProductTypeEnum.OTHER);

        //create取報・取残電子交付・特定口座登録行(顧客, 各種連絡params)
        //[引数]
        // 顧客：　@get顧客()の戻り値
        // 各種連絡行：　@各種連絡オブジェクト.getDataSourceObject()の戻り値
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            l_service.createHostConditionRegVoucherParams(
                l_mainAccount, (VariousInformParams)l_inform.getDataSourceObject());

        //取報・取残電子交付・特定口座登録(各種連絡Params)
        //[引数]
        //各種連絡行：　@get各種連絡行()の戻り値
        WEB3InformConditionRegVoucher l_informConditionRegVoucher =
            new WEB3InformConditionRegVoucher(l_variousInformParams);

        //delete取報・取残電子交付・特定口座登録伝票キュー( )
        l_informConditionRegVoucher.deleteHostConditionRegVoucher();

        //取報・取残電子交付・特定口座登録(取報・取残電子交付・特定口座登録Params, String)
        //[引数]
        //取報・取残電子交付・特定口座登録行：　@create取報・取残電子交付・特定口座登録行()の戻り値
        //伝票識別コード：　@注文識別コード採番サービス.get新規識別コード()の戻り値
        WEB3InformConditionRegVoucher l_informConditionRegVoucherNew =
            new WEB3InformConditionRegVoucher(l_hostConditionRegVoucherParams, l_strNewNumber);

        //is伝票作成
        boolean l_blnVoucherMake = l_informConditionRegVoucherNew.isVoucherMake();

        //is伝票作成()の戻り値がtrueの場合
        if (l_blnVoucherMake)
        {
            //save取報・取残電子交付・特定口座登録伝票キュー( )
            l_informConditionRegVoucherNew.saveHostConditionRegVoucher();

            //isトリガ発行 == true の場合、以下の処理を実行
            if (l_blnIsSubmitMarketTrigger)
            {
                //トリガ発行(String, String)
                //[引数]
                // 証券会社コード：　@get証券会社コード()の戻り値
                // データコード：　@”GI843”
                l_service.submitMarketTrigger(
                    l_strInstitutionCode, WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
            }

            //update各種連絡変更情報(各種連絡Params, String, String)
            //［引数］
            //各種連絡行： create各種連絡.getDataSourceObject()の戻り値
            //識別コード： リクエストデータ.識別コード
            //伝票識別コード： 注文識別コード採番サービス.get新規識別コード()で取得した値
            //データコード：　@”GI843”
            //伝票作成状況： ”1：作成済”
            updateVariousInformChangeInfo(
                (VariousInformParams)l_inform.getDataSourceObject(),
                l_request.requestNumber,
                l_strNewNumber,
                WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST,
                WEB3VoucherCreateStatusDef.CREATE_COMPLETE);
        }
        //is伝票作成()の戻り値がfalseの場合
        else
        {
            //update各種連絡変更情報(各種連絡Params, String, String)
            //［引数］
            //各種連絡行： create各種連絡.getDataSourceObject()の戻り値
            //識別コード： リクエストデータ.識別コード
            //伝票識別コード： null
            //データコード：　@null
            //伝票作成状況： ”3：受付完了”
            updateVariousInformChangeInfo(
                (VariousInformParams)l_inform.getDataSourceObject(),
                l_request.requestNumber,
                null,
                null,
                WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);
        }

        //モバイル専用口座開設区分に変更がある場合
        //（リクエストデータ.変更後情報.モバイル専用口座開設区分 !=
        // create口座切替・電子交付申込情報()の戻り値.モバイル専用口座開設区分）、
        // 以下の処理を行う。
        if (!WEB3Toolkit.isEquals(l_request.changedInfo.mobileAccoutDiv, l_applyInfo.mobileAccoutDiv))
        {
            try
            {
                //getDefaultProcessor( )
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //顧客マスタテーブルを更新する。
                //[doUpdateQuery()に指定する引数]
                // arg0（primaryKey）：　@顧客.get顧客行.口座ID
                // arg1（changes）：
                // 　@　@【ｘTrade】補足資料.DB更新「口座切替・電子交付申込_顧客マスター」参照。
                MainAccountPK l_mainAccountPK = new MainAccountPK(l_mainAccount.getAccountId());

                Map l_map = new HashMap();
                l_map.put("only_mobile_open_div", l_request.changedInfo.mobileAccoutDiv);
                l_map.put("only_mbl_opn_div_last_updater", l_strAdminCode);
                l_map.put("only_mbl_opn_div_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_map);

            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //createResponse( )
        WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response =
            (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();

        l_response.dateInfo = l_deliAppDtInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate取消)<BR>
     * 口座切替・電子交付申込取消確認画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（口座切替・電子交付申込）validate取消」 参照。<BR>
     * =============================================== <BR>
     * 　@シーケンス図　@:validate取消<BR>
     * 　@具体位置　@:　@get顧客()にて該当データが取得できない場合、例外をthrowする。<BR>
     * 　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:validate取消<BR>
     * 　@具体位置　@　@　@:各種連絡行が取得できない場合（戻り値 == null）、<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag  :　@BUSINESS_ERROR_01037<BR>
     * ===============================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliDeleteConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliDeleteConfResponse validateCancel(
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancel(WEB3AdminInformAccSwElecDeliDeleteConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード： "A0106"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY,
            true);

        //validate部店権限(部店コード : String)
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        try
        {
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        //get顧客()にて該当データが取得できない場合、例外をthrowする。
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get各種連絡行(String, String, String, String)
        //［引数］
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード：　@リクエストデータ.部店コード
        //識別コード：　@リクエストデータ.識別コード
        //連絡種別：　@リクエストデータ.連絡種別
        VariousInformParams l_variousInformParams =
            WEB3Inform.getVariousInform(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber,
                l_request.informType);

        //各種連絡行が取得できない場合（戻り値 == null）、例外をスローする。
        if (l_variousInformParams == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //isトリガ発行(発注条件 : String)
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        // 口座切替・電子交付申込共通サービを取得する。
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate伝票取消(String, boolean)
        //作成状況：　@get各種連絡行().get作成状況()の戻り値
        //トリガー発行区分：　@isトリガー発行()の戻り値
        l_informAccSwElecDeliApplyCommonService.validateVoucherCancel(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        //create口座切替・電子交付申込情報(各種連絡Params)
        //各種連絡行： get各種連絡行()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_adminInformAccSwitchElecDeliApplyInfo =
            l_informAccSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(
                l_variousInformParams);

        //createResponse( )
        WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response =
            (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();

        //)レスポンスデータプロパティに以下の通り値をセットする。
        //レスポンスデータ.部店コード   ＝ 各種連絡行.部店コード
        l_response.branchCode = l_variousInformParams.getBranchCode();

        //レスポンスデータ.顧客コード  ＝ 各種連絡行.顧客コード
        l_response.accountCode = l_variousInformParams.getAccountCode();

        //レスポンスデータ.顧客名    ＝ 各種連絡行.顧客名
        l_response.accountName = l_variousInformParams.getAccountName();

        //レスポンスデータ.変更後情報  ＝ create口座切替・電子交付申込情報()の戻り値
        l_response.changedInfo = l_adminInformAccSwitchElecDeliApplyInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit取消)<BR>
     * 口座切替・電子交付申込取消完了画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者（口座切替・電子交付申込）submit取消」 参照。<BR>
     * =============================================== <BR>
     * 　@シーケンス図　@:submit取消<BR>
     * 　@具体位置　@:　@get顧客()にて該当データが取得できない場合、例外をthrowする。<BR>
     * 　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * ===============================================<BR>
     * 　@シーケンス図　@:submit取消<BR>
     * 　@具体位置　@　@　@:各種連絡行が取得できない場合（戻り値 == null）、<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag  :　@BUSINESS_ERROR_01037<BR>
     * ===============================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyDeleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminInformAccSwElecDeliDeleteCmpResponse submitCancel(
        WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel(WEB3AdminInformAccSwElecDeliDeleteCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //機@能カテゴリコード： "A0106"
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCOUNT_SWITCH_ELECTRON_DELI_APPLY,
            true);

        //validate部店権限(部店コード : String)
        //部店コード： リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        // validate取引パスワード(パスワード : String)
        //パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;

        try
        {
            //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
            // 証券会社コード：　@get証券会社コード()の戻り値
            // 部店コード：　@リクエストデータ.部店コード
            // 口座コード：　@リクエストデータ.顧客コード
            l_mainAccount = l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.accountCode);
        }
        //get顧客()にて該当データが取得できない場合、例外をthrowする。
        catch (WEB3BaseException l_ex)
        {
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get各種連絡行(String, String, String, String)
        //［引数］
        //証券会社コード：　@get証券会社コード()の戻り値
        //部店コード：　@リクエストデータ.部店コード
        //識別コード：　@リクエストデータ.識別コード
        //連絡種別：　@リクエストデータ.連絡種別
        VariousInformParams l_variousInformParams =
            WEB3Inform.getVariousInform(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.requestNumber,
                l_request.informType);

        //各種連絡行が取得できない場合（戻り値 == null）、例外をスローする。
        if (l_variousInformParams == null)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //isトリガ発行(発注条件 : String)
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger =
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

        // 口座切替・電子交付申込共通サービを取得する。
        WEB3InformAccSwElecDeliApplyCommonService l_informAccSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //validate伝票取消(String, boolean)
        //作成状況：　@get各種連絡行().get作成状況()の戻り値
        //トリガー発行区分：　@isトリガー発行()の戻り値
        l_informAccSwElecDeliApplyCommonService.validateVoucherCancel(
            l_variousInformParams.getStatus(),
            l_blnIsSubmitMarketTrigger);

        //取報・取残電子交付・特定口座登録(各種連絡Params)
        //各種連絡行：　@get各種連絡行()の戻り値
        WEB3InformConditionRegVoucher l_informHostConditionRegVoucher =
            new WEB3InformConditionRegVoucher(l_variousInformParams);

        // delete取報・取残電子交付・特定口座登録伝票キュー( )
        //取報・取残電子交付・特定口座登録（GI311）キューテーブルのレコードを削除する。
        l_informHostConditionRegVoucher.deleteHostConditionRegVoucher();

        //get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //各種連絡(各種連絡情報)
        //各種連絡行：get各種連絡行（）の戻り値
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        //以下の内容のMapオブジェクトを生成する
        //更新者コード： get管理者コード（）の戻り値
        //伝票作成状況： 0：未作成
        //更新日時：   処理日時
        Map l_updateInformMap = new HashMap();
        l_updateInformMap.put("last_updater", l_strAdministratorCode);
        l_updateInformMap.put("status", WEB3VoucherCreateStatusDef.NOT_CREATE);
        l_updateInformMap.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //update各種連絡(Map)
        //DB更新仕様「分配金振替口座伝票取消_各種連絡テーブル.xls」
        //分配金振替口座伝票取消_update参照
        //エラー理由コード      null
        l_updateInformMap.put("error_reason_code", null);
        //伝票識別コード  null
        l_updateInformMap.put("order_request_number", null);
        //データコード null
        l_updateInformMap.put("request_code", null);
        //伝票送信日時 null
        l_updateInformMap.put("send_timestamp", null);
        //伝票受信日時 null
        l_updateInformMap.put("receipt_timestamp", null);

        l_inform.updateInform(l_updateInformMap);

        //モバイル専用口座開設区分を初期化する。
        try
        {
            //getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //顧客マスタテーブルを更新する。
            //[doUpdateQuery()に指定する引数]
            //arg0（primaryKey）：　@顧客.get顧客行.口座ID
            //arg1（changes）：
            //【ｘTrade】補足資料.DB更新「口座切替・電子交付申込取消_顧客マスター」参照。
            MainAccountPK l_mainAccountPK =
                new MainAccountPK(l_mainAccount.getMainAccountRow().getAccountId());

            Map l_mainAccountMap = new HashMap();

            //モバイル専用口座開設区分
            l_mainAccountMap.put("only_mobile_open_div", l_variousInformParams.getExtDiv12());

            //モバイル専用口座開設区分更新者コード
            l_mainAccountMap.put("only_mbl_opn_div_last_updater", l_variousInformParams.getExtText5());

            //モバイル専用口座開設区分更新日時
            l_mainAccountMap.put(
                "only_mbl_opn_div_timestamp",
                WEB3DateUtility.getDate(
                    l_variousInformParams.getExtText6(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));

            l_queryProcessor.doUpdateQuery(l_mainAccountPK, l_mainAccountMap);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_response =
            (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (update各種連絡変更情報)<BR>
     * 各種連絡の変更情報を更新する。<BR>
     * <BR>
     * DB更新仕様「口座切替・電子交付申込変更_各種連絡テーブル.xls」参照<BR>
     * <BR>
     * ［更新条件］<BR>
     * 証券会社コード = (引数)各種連絡行.get証券会社コード（）<BR>
     * 連絡種別 = (引数)各種連絡行.get連絡種別（）<BR>
     * 識別コード = (引数)識別コード<BR>
     * 部店コード = (引数)各種連絡行.get部店コード（）<BR>
     * <BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strOrderRequestNumber - (伝票識別コード)<BR>
     * 伝票識別コード<BR>
     * @@param l_strRequestCode - (データコード)<BR>
     * データコード<BR>
     * @@param l_strVoucherMake - (伝票作成状況)<BR>
     * 伝票作成状況<BR>
     * @@throws WEB3BaseException
     */
    private void updateVariousInformChangeInfo(
        VariousInformParams l_variousInformParams,
        String l_strRequestNumber,
        String l_strOrderRequestNumber,
        String l_strRequestCode,
        String l_strVoucherMake) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateVariousInformChangeInfo(VariousInformParams, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_variousInformParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        try
        {
            //get変更前各種連絡行
            //証券会社コード = (引数)各種連絡行.get証券会社コード（）
            //連絡種別 = (引数)各種連絡行.get連絡種別（）
            //識別コード = (引数)識別コード
            //部店コード = (引数)各種連絡行.get部店コード（）
            VariousInformRow l_beforeVariousInformRow =
                VariousInformDao.findRowByPk(
                    l_variousInformParams.getInstitutionCode(),
                    l_variousInformParams.getInformDiv(),
                    l_strRequestNumber,
                    l_variousInformParams.getBranchCode());

            //各種連絡の変更情報を更新する。
            //DB更新仕様「口座切替・電子交付申込変更_各種連絡テーブル.xls」参照
            //識別コード
            //既存値
            l_variousInformParams.setRequestNumber(l_beforeVariousInformRow.getRequestNumber());

            //顧客コード
            //既存値
            l_variousInformParams.setAccountCode(l_beforeVariousInformRow.getAccountCode());

            //扱者コード
            //既存値
            l_variousInformParams.setTraderCode(l_beforeVariousInformRow.getTraderCode());

            //顧客名
            //既存値
            l_variousInformParams.setAccountName(l_beforeVariousInformRow.getAccountName());

            //顧客メールアドレス
            //既存値
            l_variousInformParams.setEmailAddress(l_beforeVariousInformRow.getEmailAddress());

            //区分１２
            //既存値
            l_variousInformParams.setExtDiv12(l_beforeVariousInformRow.getExtDiv12());

            //テキスト５
            //既存値
            l_variousInformParams.setExtText5(l_beforeVariousInformRow.getExtText5());

            //テキスト６
            //既存値
            l_variousInformParams.setExtText6(l_beforeVariousInformRow.getExtText6());

            //更新者コード
            //管理者コード
            WEB3Administrator l_administrator =
                WEB3Administrator.getInstanceFromLoginInfo();

            String l_strAdministratorCode = l_administrator.getAdministratorCode();
            l_variousInformParams.setLastUpdater(l_strAdministratorCode);

            //作成日時
            //既存値
            l_variousInformParams.setCreatedTimestamp(l_beforeVariousInformRow.getCreatedTimestamp());

            //更新日時
            //処理日時
            l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            //銘柄コード
            //既存値
            l_variousInformParams.setFundCode(l_beforeVariousInformRow.getFundCode());

            //扱者コード（SONAR）
            //既存値
            l_variousInformParams.setSonarTraderCode(l_beforeVariousInformRow.getSonarTraderCode());

            //伝票作成状況
            //1：作成済
            l_variousInformParams.setStatus(l_strVoucherMake);

            //エラー理由コード
            //null
            l_variousInformParams.setErrorReasonCode(null);

            //伝票識別コード
            //引数:伝票識別コード
            l_variousInformParams.setOrderRequestNumber(l_strOrderRequestNumber);

            //データコード
            //引数:データコード
            l_variousInformParams.setRequestCode(l_strRequestCode);

            //伝票送信日時
            //null
            l_variousInformParams.setSendTimestamp(null);

            //伝票受信日時
            //null
            l_variousInformParams.setReceiptTimestamp(null);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_variousInformParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
