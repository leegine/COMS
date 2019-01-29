head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込サービスImpl(WEB3InformAccSwElecDeliApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 張騰宇 (中訊) 新規作成 仕様変更モデル098 ＤＢ更新仕様 014
Revision History : 2007/08/13 孫洪江 (中訊) 仕様変更モデル104
Revision History : 2007/08/30 孫洪江 (中訊) 仕様変更モデル107 モデル108
Revision History : 2007/09/19 孫洪江 (中訊) 仕様変更モデル111
Revision History : 2007/10/11 長瀬 (SCS) 仕様変更モデル120
Revision History : 2007/10/23 張騰宇 (中訊) モデル121
Revision History : 2009/02/12 SCS大嶋 仕様変更モデル136
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.WEB3InformClientRequestService;
import webbroker3.inform.WEB3InformConditionRegVoucher;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (口座切替・電子交付申込サービスImpl)<BR>
 * 口座切替・電子交付申込サービス実装クラス<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyServiceImpl extends WEB3InformClientRequestService
    implements WEB3InformAccSwElecDeliApplyService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformAccSwElecDeliApplyServiceImpl.class);

    /**
     * 口座切替・電子交付申込サービス処理を行う。<BR>
     * @@param l_request - リクエストデータ)<BR>
     * リクエストデータ<BR>
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

        //口座切替・電子交付申込入力リクエスト
        if (l_request instanceof WEB3InformAccSwElecDeliApplyInpRequest)
        {
            l_response = this.getInputScreen((WEB3InformAccSwElecDeliApplyInpRequest)l_request);
        }
        //口座切替・電子交付申込確認リクエスト
        else if (l_request instanceof WEB3InformAccSwElecDeliApplyConfRequest)
        {
            l_response = this.validateApply((WEB3InformAccSwElecDeliApplyConfRequest)l_request);
        }
        //口座切替・電子交付申込完了リクエスト
        else if (l_request instanceof WEB3InformAccSwElecDeliApplyCmpRequest)
        {
            l_response = this.submitApply((WEB3InformAccSwElecDeliApplyCmpRequest)l_request);
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
     * (get入力画面)<BR>
     * 入力画面の取得を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（口座切替・電子交付申込）get入力画面」 参照。<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@　@:get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3InformAccSwElecDeliApplyInpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3InformAccSwElecDeliApplyInpResponse getInputScreen(
        WEB3InformAccSwElecDeliApplyInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3InformAccSwElecDeliApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate( )(口座切替・電子交付申込入力リクエスト::validate)
        l_request.validate();

        //getAccountId( )
        OpLoginSecurityService l_service =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_lngAccountId = l_service.getAccountId();

        //get顧客()にて該当データが取得できない場合、例外をthrowする
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する顧客が存在しません。");
        }

        //getDataSourceObject
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        WEB3InformAccSwElecDeliApplyCommonService l_accSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);

        //create口座切替・電子交付申込情報(顧客)
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //createResponse
        WEB3InformAccSwElecDeliApplyInpResponse l_response =
            (WEB3InformAccSwElecDeliApplyInpResponse)l_request.createResponse();

        //プロパティセット
        //(*1)レスポンスデータプロパティに以下の通り値をセットする。
        //　@ レスポンスデータ.部店コード  ＝ 顧客行.部店コード
        l_response.branchCode = l_mainAccountRow.getBranchCode();
        // 　@レスポンスデータ.顧客コード  ＝ 顧客行.口座コード
        l_response.accountCode = l_mainAccountRow.getAccountCode();
        // 　@レスポンスデータ.顧客名    ＝ 顧客行.名前（苗字）
        l_response.accountName = l_mainAccountRow.getFamilyName();
        // 　@レスポンスデータ.変更前情報  ＝ create口座切替・電子交付申込情報()の戻り値
        l_response.beforeInfo = l_applyInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate申込)<BR>
     * 口座切替・電子交付申込確認処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（口座切替・電子交付申込）validate申込」 参照。<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@　@:get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3InformAccSwElecDeliApplyConfResponse
     * @@throws WEB3BaseException
     */
    protected WEB3InformAccSwElecDeliApplyConfResponse validateApply(
        WEB3InformAccSwElecDeliApplyConfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateApply(WEB3InformAccSwElecDeliApplyConfRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // validate( )(口座切替・電子交付申込確認リクエスト::validate)
        l_request.validate();

        //getAccountId( )
        OpLoginSecurityService l_service =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_lngAccountId = l_service.getAccountId();

        //get顧客()にて該当データが取得できない場合、例外をthrowする
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する顧客が存在しません。");
        }

        //getDataSourceObject( )
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //get各種連絡一覧(String, String, String, String)
        //各種連絡一覧を取得する。
        //[引数]
        //証券会社コード：　@顧客行.証券会社コード
        //部店コード：　@顧客行.部店コード
        //連絡種別：　@リクエストデータ.連絡種別
        //顧客コード：　@顧客行.口座コード
        WEB3InformAccSwElecDeliApplyCommonService l_accSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);
        List l_lisVariousInforms = l_accSwElecDeliApplyCommonService.getVariousInformList(
            l_mainAccountRow.getInstitutionCode(),
            l_mainAccountRow.getBranchCode(),
            l_request.informType,
            l_mainAccountRow.getAccountCode());

        //get各種連絡一覧の戻り値() != null の場合、以下の処理を行う。
        if (l_lisVariousInforms != null)
        {
            //isトリガ発行(発注条件 : String)
            //SONARへトリガを発行できる時間帯かを判定する。
            //[引数]
            //発注条件： 0（DEFAULT）
            boolean l_blnIsSubmitMarketTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);

            //validate伝票作成(String, boolean)
            //伝票作成が可能かチェックを行う。
            //[引数]
            //作成状況：　@get各種連絡一覧.get(0).get作成状況()の戻り値
            //トリガー発行区分：　@isトリガー発行()の戻り値
            l_accSwElecDeliApplyCommonService.validateVoucherMake(
                ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus(),
                l_blnIsSubmitMarketTrigger);
        }

        //create口座切替・電子交付申込情報(顧客)
        //口座切替・電子交付申込情報メッセージデータを作成する。
        //[引数]
        //顧客：　@get顧客()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 口座切替・電子交付申込情報)
        //[引数]
        //変更前情報：　@create口座切替・電子交付申込情報()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        l_accSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_applyInfo,
            l_request.changedInfo);

        WEB3InformAccSwElecDeliApplyConfResponse l_response =
            (WEB3InformAccSwElecDeliApplyConfResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit申込)<BR>
     * 口座切替・電子交付申込確認処理を行う。  <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（口座切替・電子交付申込）submit申込」 参照。 <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@　@:get顧客()にて該当データが取得できない場合、<BR>
     * 　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01035<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3InformAccSwElecDeliApplyCmpResponse
     * @@throws WEB3BaseException
     */
    protected WEB3InformAccSwElecDeliApplyCmpResponse submitApply(
        WEB3InformAccSwElecDeliApplyCmpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitApply(WEB3InformAccSwElecDeliApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        //validate注文受付可能
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //validate
        l_request.validate();

        //getAccountId( )
        OpLoginSecurityService l_service =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        long l_lngAccountId = l_service.getAccountId();

        //get顧客()にて該当データが取得できない場合、例外をthrowする
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する顧客が存在しません。");
        }

        //getDataSourceObject
        MainAccountParams l_mainAccountParams = new MainAccountParams(
            (MainAccountRow)l_mainAccount.getDataSourceObject());
        
        //lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        l_accountManager.lockAccount(
                l_mainAccountParams.getInstitutionCode(),
                l_mainAccountParams.getBranchCode(),
                l_mainAccountParams.getAccountCode());

        //get補助口座
        SubAccount l_subAccount = this.getSubAccount();

        //get代理入力者
        Trader l_trader = this.getTrader();

        //validate取引パスワード(代理入力者 : Trader, 補助口座 : SubAccount, パスワード : String)
        WEB3GentradeOrderValidator l_validator = new WEB3GentradeOrderValidator();
        OrderValidationResult l_result =
            l_validator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME + l_result.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //get各種連絡一覧(String, String, String, String)
        //[引数]
        //証券会社コード：　@顧客行.証券会社コード
        //部店コード：　@顧客行.部店コード
        //連絡種別：　@リクエストデータ.連絡種別
        //顧客コード：　@顧客行.口座コード
        WEB3InformAccSwElecDeliApplyCommonService l_accSwElecDeliApplyCommonService =
            (WEB3InformAccSwElecDeliApplyCommonService)Services.getService(
                WEB3InformAccSwElecDeliApplyCommonService.class);
        List l_lisVariousInforms = l_accSwElecDeliApplyCommonService.getVariousInformList(
            l_mainAccountParams.getInstitutionCode(),
            l_mainAccountParams.getBranchCode(),
            l_request.informType,
            l_mainAccountParams.getAccountCode());

        //isトリガ発行(発注条件 : String)
        //SONARへトリガを発行できる時間帯かを判定する。
        //[引数]
        //発注条件： 0（DEFAULT）
        boolean l_blnIsSubmitMarketTrigger = WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
            WEB3OrderingConditionDef.DEFAULT);

        //get各種連絡一覧の戻り値() != null の場合、以下の処理を行う。
        if (l_lisVariousInforms != null)
        {
            //validate伝票作成(String, boolean)
            //伝票作成が可能かチェックを行う。
            //[引数]
            //作成状況：　@get各種連絡一覧.get(0).get作成状況()の戻り値
            //トリガー発行区分：　@isトリガー発行()の戻り値
            l_accSwElecDeliApplyCommonService.validateVoucherMake(
                ((VariousInformRow)l_lisVariousInforms.get(0)).getStatus(),
                l_blnIsSubmitMarketTrigger);
        }

        //create口座切替・電子交付申込情報(顧客)
        //[引数]
        //顧客：　@get顧客()の戻り値
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_applyInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliApplyInfo(l_mainAccount);

        //validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 口座切替・電子交付申込情報)
        //口座切替・電子交付申込情報の変更内容をチェックする。
        //[引数]
        //変更前情報：　@create口座切替・電子交付申込情報()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        l_accSwElecDeliApplyCommonService.validateAccSwitchElecDeliApplyInfo(
            l_applyInfo,
            l_request.changedInfo);

        //create口座切替・電子交付申込日付情報(String, String)
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_appDtInfo =
            l_accSwElecDeliApplyCommonService.createAccSwitchElecDeliAppDtInfo(
                l_request.changedInfo.taxType, l_request.changedInfo.marginTaxType);

        //create各種連絡(顧客, 口座切替・電子交付申込情報, 口座切替・電子交付申込日付情報, String)
        //[引数]
        //顧客：　@get顧客()の戻り値
        //変更後情報：　@リクエストデータ.変更後情報
        //日付情報：　@create口座切替・電子交付申込日付情報()の戻り値
        //連絡種別：　@リクエストデータ.連絡種別
        WEB3Inform l_inform =
            l_accSwElecDeliApplyCommonService.createVariousInform(
                l_mainAccount,
                l_request.changedInfo,
                l_appDtInfo,
                l_request.informType);

        //get新規識別コード(String, String)
        WEB3InformHostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3InformHostReqOrderNumberManageService)Services.getService(
                WEB3InformHostReqOrderNumberManageService.class);
        String l_strNewOrderRequestCode =
            l_hostReqOrderNumberManageService.getNewOrderRequestCode(
                l_mainAccountParams.getInstitutionCode(), l_request.informType);

        //saveNew各種連絡(String, String, String)
        //[引数]
        //更新者コード：　@（以下のとおり）
        //  －代理入力の場合、代理入力者.扱者コード
        //  －以外の場合、null
        //識別コード：　@get新規識別コード()の戻り値
        //作成状況：　@3:受付完了
        String l_traderCode = null;
        if (l_trader != null)
        {
            l_traderCode = l_trader.getTraderCode();
        }
        l_inform.saveNewInform(
            l_traderCode,
            l_strNewOrderRequestCode,
            WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);

        // get新規識別コード(証券会社コード : String, 部店コード : String, 銘柄タイプ : ProductTypeEnum)
        //SONAR通知キューに書き込む際に必要な識別コードを採番する。
        //[引数]
        //証券会社コード： 顧客行.証券会社コード
        //部店コード： 顧客行.部店コード
        //銘柄タイプ： ProductTypeEnum.その他
        WEB3HostReqOrderNumberManageService l_reqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);

        String l_strNewNumber = l_reqOrderNumberManageService.getNewNumber(
            l_mainAccountParams.getInstitutionCode(),
            l_mainAccountParams.getBranchCode(),
            ProductTypeEnum.OTHER);

        //create取報・取残電子交付・特定口座登録行(顧客, 各種連絡params)
        //取報・取残電子交付・特定口座登録新規行を生成する。
        //[引数]
        //顧客：　@get顧客()の戻り値
        //各種連絡行：　@各種連絡オブジェクト.getDataSourceObject()の戻り値
        VariousInformParams l_variousInformParams = (VariousInformParams)l_inform.getDataSourceObject();

        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            l_accSwElecDeliApplyCommonService.createHostConditionRegVoucherParams(
                l_mainAccount,
                l_variousInformParams);

        //取報・取残電子交付・特定口座登録(取報・取残電子交付・特定口座登録Params, String)
        //コンストラクタ
        //[引数]
        //取報・取残電子交付・特定口座登録行 : create取報・取残電子交付・特定口座登録行()の戻り値
        //伝票識別コード : 注文識別コード採番サービス.get新規識別コード()の戻り値
        WEB3InformConditionRegVoucher l_informConditionRegVoucher = new WEB3InformConditionRegVoucher(
            l_hostConditionRegVoucherParams,
            l_strNewNumber);

        // is伝票作成
        boolean l_blnIsVouchMake = l_informConditionRegVoucher.isVoucherMake();

        Map l_map = new HashMap();
        //is伝票作成()の戻り値がtrueの場合
        if (l_blnIsVouchMake)
        {
            //save取報・取残電子交付・特定口座登録伝票キュー( )
            //取報・取残電子交付・特定口座登録（GI311）キューテーブルにレコードを作成する。
            l_informConditionRegVoucher.saveHostConditionRegVoucher();

            // isトリガ発行 == true の場合、以下の処理を実行
            if (l_blnIsSubmitMarketTrigger)
            {
                //トリガ発行(String, String)
                //トリガを発行する。
                //[引数]
                //証券会社コード：　@顧客行.証券会社コード
                //データコード：　@”GI843”
                l_accSwElecDeliApplyCommonService.submitMarketTrigger(
                    l_mainAccountParams.getInstitutionCode(),
                    WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
            }

            //MAPオブジェクトの生成
            //各種連絡テーブルを更新する為のMapオブジェクトを生成する。
            //以下の内容のMapオブジェクトを生成する
            //伝票識別コード：    注文識別コード採番サービス.get新規識別コード（）で取得した値
            //更新者コード： 代理入力の場合、代理入力者.扱者コード ※以外の場合、null
            //伝票作成状況： 1：作成済
            //更新日時：   処理日時
            //データコード： “GI843”
            l_map.put("order_request_number", l_strNewNumber);
            l_map.put("last_updater", l_traderCode);
            l_map.put("status", WEB3VoucherCreateStatusDef.CREATE_COMPLETE);
            l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_map.put("request_code", WEB3HostRequestCodeDef.ACCOPEN_CONDITION_REGIST);
        }
        //is伝票作成()の戻り値がfalseの場合
        else
        {
            //MAPオブジェクトの生成
            //各種連絡テーブルを更新する為のMapオブジェクトを生成する。
            //以下の内容のMapオブジェクトを生成する
            //伝票識別コード：    null
            //更新者コード： 代理入力の場合、代理入力者.扱者コード ※以外の場合、null
            //伝票作成状況： 3：受付完了
            //更新日時：   処理日時
            //データコード： null
            l_map.put("order_request_number", null);
            l_map.put("last_updater", l_traderCode);
            l_map.put("status", WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);
            l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
            l_map.put("request_code", null);
        }

        // update各種連絡(Map)(各種連絡::update各種連絡)
        //各種連絡テーブルのレコードを更新する。
        //[引数]
        //生成したMapオブジェクト
        l_inform.updateInform(l_map);

        //モバイル専用口座開設区分に変更がある場合
        //（リクエストデータ.変更後情報.モバイル専用口座開設区分 !=
        //create口座切替・電子交付申込情報()の戻り値.モバイル専用口座開設区分）、以下の処理を行う。
        if (!WEB3Toolkit.isEquals(l_request.changedInfo.mobileAccoutDiv,
            l_applyInfo.mobileAccoutDiv))
        {
            try
            {
                //getDefaultProcessor
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //doUpdateQuery(arg0 : PrimaryKey, arg1 : Map)
                //顧客マスタテーブルを更新する。
                //[doUpdateQuery()に指定する引数]
                //arg0（primaryKey）：　@顧客.get顧客行.口座ID
                //arg1（changes）：
                //　@　@【ｘTrade】補足資料.DB更新「口座切替・電子交付申込_顧客マスター」参照。
                //モバイル専用口座開設区分
                l_mainAccountParams.setOnlyMobileOpenDiv(l_request.changedInfo.mobileAccoutDiv);

                //モバイル専用口座開設区分更新者コード
                //顧客入力の場合、顧客マスタテーブル.口座コード
                l_mainAccountParams.setOnlyMblOpnDivLastUpdater(l_mainAccountParams.getAccountCode());

                //モバイル専用口座開設区分更新日時
                l_mainAccountParams.setOnlyMblOpnDivTimestamp(GtlUtils.getSystemTimestamp());

                l_queryProcessor.doUpdateQuery(l_mainAccountParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3Inform.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //createResponse
        WEB3InformAccSwElecDeliApplyCmpResponse l_response =
            (WEB3InformAccSwElecDeliApplyCmpResponse)l_request.createResponse();

        //(*1)レスポンスデータプロパティに以下の通り値をセットする。
        //　@レスポンスデータ.日付情報   ＝ create口座切替・電子交付申込日付情報()の戻り値
        l_response.dateInfo = l_appDtInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
