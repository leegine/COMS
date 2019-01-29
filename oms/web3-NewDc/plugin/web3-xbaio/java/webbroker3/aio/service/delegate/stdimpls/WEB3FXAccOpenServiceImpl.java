head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設サービスImpl(WEB3FXAccOpenServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/25 周勇 (中訊) 新規作成
                 : 2006/02/08 鄭徳懇 (中訊) 仕様変更・モデル470
                 : 2006/04/24 肖志偉 (中訊) 仕様変更・モデル532
                 : 2006/05/08 周捷 (中訊) 仕様変更・モデル550
Revesion History : 2008/04/09 馮海濤 (中訊) 仕様変更・モデル833 モデル842 モデル844
Revesion History : 2008/04/23 王志葵 (中訊) 仕様変更モデル.845
                 : 2008/05/08 佐藤 (SCS) 仕様変更・モデル833
                 : 2008/05/23 三島 (SCS)  
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更・モデルNo.854 No.855 No.857 No.874 No.877 No.883
Revesion History : 2008/09/22 武波 (中訊) 仕様変更・モデル1005~1009,1011,1012,1015,1052,1054
Revesion History : 2009/03/18  車進 (中訊) 仕様変更・モデル1123,1127,1128,1130,1131,1137,1160 ＤＢ更新仕様 215
Revesion History : 2009/05/31 柴双紅 (中訊) 仕様変更・モデルNo.1165
Revesion History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1196
Revesion History : 2009/10/09 張騰宇 (中訊) 仕様変更・モデル1235
Revesion History : 2009/10/27 張騰宇(中訊) 仕様変更 モデルNo.1246
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.aio.WEB3FXAccOpenConnection;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioFxSystemCodeDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.message.WEB3FXAccOpenAskingRequest;
import webbroker3.aio.message.WEB3FXAccOpenAskingResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXAccOpenConfirmRequest;
import webbroker3.aio.message.WEB3FXAccOpenConfirmResponse;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.service.delegate.WEB3FXAccOpenService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenRealUpdateDef;
import webbroker3.common.define.WEB3AccTypeDef;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3AioSoapConnectDivDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3QuestionCheckDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccOpenDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX口座開設サービスImpl) <BR>
 * FX口座開設サービス実装クラス <BR>
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FXAccOpenServiceImpl extends WEB3ClientRequestService
    implements WEB3FXAccOpenService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenServiceImpl.class);  
    
    /**
     * @@roseuid 41E7829703D8
     */
    public WEB3FXAccOpenServiceImpl()
    {
    }

    /**
     * FX口座開設サービス処理を行う。 <BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。 <BR>
     * ・validate口座開設() <BR>
     * ・start口座開設() <BR>
     * ・submit口座開設() <BR>
     * ・submit口座開設()（SOAP接続）<BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02A3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FXAccOpenCompleteSoapRequest)
        {
            l_response =
                this.submitAccountOpenSoap(
                    (WEB3FXAccOpenCompleteSoapRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenAskingRequest)
        {
            l_response =
                this.startAccountOpen(
                    (WEB3FXAccOpenAskingRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenConfirmRequest)
        {
            l_response =
                this.validateAccountOpen(
                    (WEB3FXAccOpenConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenCompleteRequest)
        {
            l_response =
                this.submitAccountOpen(
                    (WEB3FXAccOpenCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3FXAccOpenAskingRequest "
                    + " と WEB3FXAccOpenConfirmRequest"
                    + " と WEB3FXAccOpenCompleteRequest" 
                    + " と WEB3FXAccOpenCompleteSoapRequest以外である, but is "
                    + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate口座開設) <BR>
     * 口座開設の発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX口座開設）validate口座開設」参照。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02C2
     */
    protected WEB3FXAccOpenConfirmResponse validateAccountOpen(
        WEB3FXAccOpenConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountOpen(WEB3FXAccOpenConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) get補助口座(SubAccountTypeEnum)
        //アイテムの定義
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3) validate注文(SubAccount)
        //アイテムの定義
        //以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等） 
        //[引数] 
        //補助口座： get補助口座()の戻り値
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //get会社別FXシステム条件(String, String, String)
        //アイテムの定義
        //会社別FXシステム条件Paramsを取得する。
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = 
                l_fxDataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch(NotFoundException l_ex)
        {
            log.debug("__NotFoundExcepiton__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);        
        }

        //1.5) validate外部システム受付可能(String)
        //アイテムの定義
        //FXシステムの受付時間チェックを行う。
        //[引数の設定] 
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        if (!WEB3AioFxSystemCodeDivDef.TFX.equals(l_strFxSystemCode))
        {
            l_aioOrderManager.validateOtherSystemAcceptPossible(l_strFxSystemCode);
        }
        
        //1.6) validateFX口座開設可能(SubAccount, String)
        //アイテムの定義
        //FX口座開設が可能であるかをチェックする。 
        //[引数の設定] 
        //補助口座：　@get補助口座()の戻り値 
        //会社別FXシステム条件Params：　@get会社別FXシステム条件()の戻り値
        l_aioOrderManager.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
        
        //1.7) createResponse( )
        //アイテムの定義
        //レスポンスデータを生成する。
        WEB3FXAccOpenConfirmResponse l_response 
            = (WEB3FXAccOpenConfirmResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (start口座開設) <BR>
     * 口座開設の依頼処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX口座開設）start口座開設」参照。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenAskingResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02E2
     */
    protected WEB3FXAccOpenAskingResponse startAccountOpen(
        WEB3FXAccOpenAskingRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "startAccountOpen(WEB3FXAccOpenAskingRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) validate( )
        l_request.validate();

        //get補助口座(補助口座タイプ : SubAccountTypeEnum)
        //[引数]
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //get会社別FXシステム条件(String, String, String)
        //アイテムの定義
        //会社別FXシステム条件Paramsを取得する。
        //[引数の設定]
        //証券会社コード： 補助口座.証券会社コード
        //部店コード：　@補助口座.get取引店().getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();

        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fxDataControlService.getCompFxCondition(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_request.fxSystemCode);
        }
        catch(NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //get会社別FXシステム条件()の戻り値.質問同意チェック実施区分==1：チェックするの場合
        if (WEB3QuestionCheckDivDef.CHECK.equals(
            l_compFxConditionParams.getQuestionCheckDiv()))
        {
            //validateFX取引同意質問(FX取引同意質問情報[])
            //[引数の設定]
            //FX取引同意質問情報一覧：　@リクエストデータ.FX取引同意質問情報一覧
            l_fxDataControlService.validateFXTradingAgreeQuestion(l_request.fxTradeAgreementList);
        }

        //1.4) validate注文(SubAccount)
        //アイテムの定義
        //以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等） 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.validateOrder(l_subAccount);

        //1.6) validate外部システム受付可能(String)
        //アイテムの定義
        //FXシステムの受付時間チェックを行う。
        //[引数の設定] 
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        if (!WEB3AioFxSystemCodeDivDef.TFX.equals(l_strFxSystemCode))
        {
            l_aioOrderManager.validateOtherSystemAcceptPossible(l_strFxSystemCode);
        }
        
        //1.7) validateFX口座開設可能(SubAccount, String)
        //アイテムの定義
        //FX口座開設が可能であるかをチェックする。 
        //[引数の設定] 
        //補助口座：　@get補助口座()の戻り値 
        //会社別FXシステム条件Params：　@get会社別FXシステム条件()の戻り値
        l_aioOrderManager.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
        
        //1.8) get代理入力者( )
        //アイテムの定義
        //代理入力者を取得する。
        Trader l_trader = this.getTrader();
        
        //1.9) validate取引パスワード(Trader, SubAccount, String)
        //アイテムの定義
        //パスワードのチェックを行う。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //補助口座： get補助口座()の戻り値 
        //パスワード： リクエストデータ.暗証番号 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator(); 
        
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("パスワードのチェックを行う");
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);                          
        }
        
        //1.10) get新規FXログインID(String, String)
        //アイテムの定義
        //新規のFXログインIDを付番する。 
        //[引数の設定] 
        //FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字  
        //顧客コード：　@補助口座.getMainAccount().getAccountCode()
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        
        String l_strNewFXLoginID = l_fxDataControlService.getNewFXLoginID(
            l_compFxConditionParams.getFxHeadOfLoginId(),
            l_strAccountCode);
        
        //1.11) get新規識別コード(String, String, ProductTypeEnum)
        //アイテムの定義
        //新規の識別コードを取得する。 
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //銘柄タイプ： 5（現金）
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode,
                l_strBranchCode, 
                ProductTypeEnum.CASH);

        String l_strOperationDiv = WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN;
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFTの場合
        //getGFT処理区分(補助口座)
        //処理区分を設定する。
        //[get処理設区分()に指定する引数]
        //補助口座：　@get補助口座()の戻り値
        if (WEB3ExtConnectSystemCodeDef.GFT.equals(
            l_compFxConditionParams.getExtConnectSystemCode()))
        {
        	l_strOperationDiv = this.getGFTOperationDiv(l_subAccount);
        }
        
        //1.12) GFT依頼電文明細( )
        //アイテムの定義
        //GFT依頼電文明細オブジェクトを生成する。
        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
        
        //1.13) (*)プロパティセット
        //GFT依頼電文明細に必要なプロパティをセットする（下記以外のプロパティは設定しない）
        
        //DIR→GFT送信日時   ：現在時刻（システムタイムスタンプ）
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
        l_fXGftAskingTelegramUnit.dirSendTime =
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMddHHmmss");

        //処理区分:
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFTの場合
        //get処理区分()の戻り値
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFT以外の場合
        //新規開設：01
        l_fXGftAskingTelegramUnit.gftOperationDiv = l_strOperationDiv;

        //メールアドレス   ：リクエストデータ.FXメールアドレス
        l_fXGftAskingTelegramUnit.fxMailAddress = l_request.fxMailAddress;
        
        //初期ログインID  ：get新規FXログインID()の戻り値
        l_fXGftAskingTelegramUnit.fxFirstLoginId = l_strNewFXLoginID;
        
        //初期パスワード   ：リクエストデータ.FX暗証番号
        l_fXGftAskingTelegramUnit.fxFirstPassword = l_request.fxPassword;
        
        //担当区分      ：会社別FXシステム条件Params.担当区分
        l_fXGftAskingTelegramUnit.groupName = l_compFxConditionParams.getGroupName();
        
        //会社コード     ：補助口座.証券会社コード
        l_fXGftAskingTelegramUnit.institutionCode = l_strInstitutionCode;
        
        //WOLFセッションキー   ：リクエストデータ.WOLFセッションキー
        l_fXGftAskingTelegramUnit.wolfSession = l_request.wolfSession;
        
        //アプリケーションID    ：リクエストデータ.アプリケーションID
        l_fXGftAskingTelegramUnit.wolfAid = l_request.wolfAid;
        
        //再生成サービスID ：リクエストデータ.再生成サービスID
        l_fXGftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
        
        //SSID      ：リクエストデータ.SSID
        l_fXGftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
        
        //部店コード     ：補助口座.get取引店().getBranchCode()
        l_fXGftAskingTelegramUnit.branchCode = l_strBranchCode;
        
        //顧客コード     ：補助口座.getMainAccount().getAccountCode()
        l_fXGftAskingTelegramUnit.accountCode = l_strAccountCode;
        
        //識別コード     ：get新規識別コード()の戻り値
        l_fXGftAskingTelegramUnit.requestNumber = l_strNewNumber;
        
        //名前（姓）     ：顧客(*).名前（苗字）
        //(*)補助口座.getMainAccount()にて取得
        MainAccountParams l_mainAccountParams = 
            (MainAccountParams)l_subAccount.getMainAccount().getDataSourceObject();
        
        l_fXGftAskingTelegramUnit.fxLastName = l_mainAccountParams.getFamilyName();

        //（住所１、住所２、住所３の前後スペースを削除）
        //住所１：顧客（*）.住所１
        String l_strAddress1 = l_mainAccountParams.getAddressLine1();
        l_strAddress1 = l_strAddress1.replaceAll("(^(\\s|　@)+)|((\\s|　@)+$)", "");
        if (WEB3StringTypeUtility.isNotEmpty(l_strAddress1))
        {
            l_fXGftAskingTelegramUnit.address1 = l_strAddress1;
        }

        //住所２：顧客（*）.住所２
        String l_strAddress2 = l_mainAccountParams.getAddressLine2();
        if (l_strAddress2 != null)
        {
            l_strAddress2 =
                l_strAddress2.replaceAll("(^(\\s|　@)+)|((\\s|　@)+$)", "");
        }

        if (WEB3StringTypeUtility.isNotEmpty(l_strAddress2))
        {
            l_fXGftAskingTelegramUnit.address2 = l_strAddress2;
        }

        //住所３：顧客（*）.住所３
        String l_strAddress3 = l_mainAccountParams.getAddressLine3();
        if (l_strAddress3 != null)
        {
            l_strAddress3 =
                l_strAddress3.replaceAll("(^(\\s|　@)+)|((\\s|　@)+$)", "");
        }
        if (WEB3StringTypeUtility.isNotEmpty(l_strAddress3))
        {
            l_fXGftAskingTelegramUnit.address3 = l_strAddress3;
        }

        //1.14) createGFT電文ハッシュ値(GFT依頼電文明細)
        //アイテムの定義
        //GFT電文のハッシュ値を取得する。 
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
        WEB3FXTelegramProcessService l_fXTelegramProcessService = 
            (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
        
        String l_strGFTTelegramHashValue = 
            l_fXTelegramProcessService.createGFTTelegramHashValue(l_fXGftAskingTelegramUnit);
        
        //createGFT電文ハッシュ値()の戻り値のハッシュ値を
        //GFT依頼電文明細.ハッシュ値にセットする。
        l_fXGftAskingTelegramUnit.hashValue = l_strGFTTelegramHashValue;
        
        //GFT口座開設状況テーブル、および、
        //GFT電文保存テーブル、および、
        //質問回答テーブルに
        //データをinsertする。
        
        //1.15) insertGFT電文保存(GFT依頼電文明細)
        //アイテムの定義
        //GFT電文保存テーブルに行をinsertする。 
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
        l_fxDataControlService.insertGFTMessage(l_fXGftAskingTelegramUnit);
        
        //insertGFT口座開設状況(GFT依頼電文明細, String, String)
        //アイテムの定義
        //GFT口座開設状況テーブルに行をinsertする。
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
        //約諾書区分： リクエストデータ.約諾書区分
        //FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード
        l_fxDataControlService.insertGFTAccountOpenStatus(
            l_fXGftAskingTelegramUnit,
            l_request.agreementDiv,
            l_strFxSystemCode);
        
        //insert質問回答(String, String, String, FX取引同意質問情報[], String)
        //アイテムの定義
        //質問回答テーブルに行をinsertする。 
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //識別コード：　@get新規識別コード()の戻り値 
        //FX取引同意質問情報一覧：　@リクエストデータ.FX取引同意質問情報一覧
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        l_fxDataControlService.insertQuestionAnswer(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strNewNumber,
            l_request.fxTradeAgreementList,
            l_request.fxSystemCode);
        
        //1.18) createResponse( )
        //アイテムの定義
        //レスポンスデータを生成する
        WEB3FXAccOpenAskingResponse l_response = 
            (WEB3FXAccOpenAskingResponse)l_request.createResponse();
        
        //1.19) (*)プロパティセット
        //レスポンスデータにプロパティをセットする。
        //URL： 会社別FXシステム条件Params.URL
        l_response.fxUrl = l_compFxConditionParams.getUrl();

        //GFT依頼電文明細：　@(*　@上記で編集を行ったGF
        l_response.fxGftAskingTelegramUnit = l_fXGftAskingTelegramUnit;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit口座開設) <BR>
     * 口座開設の登録処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX口座開設）submit口座開設」参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FX口座開設 」<BR>
     * （FX口座開設）submit口座開設) <BR>
     * : 1.3 getGFT口座開設状況(String, String, String) <BR>
     * 戻り値がnullの場合、例外をthorwする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FX口座開設 」<BR>
     * （FX口座開設）submit口座開設) <BR>
     * : 1.7.2 (*)例外をthrow <BR>
     * ①@の場合：「2重受信エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01972 <BR>
     * <BR>
     * ②③④の場合：「その他のFXシステムエラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ⑤の場合：GFT結果通知電文明細.受付結果に応じて以下の例外となる <BR>
     * 00000105(ホスト処理時間外)の場合 ：「受付時間外エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01801 <BR>
     * <BR>
     * 00000199(ホストシステムエラー)の場合 ：「通信エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01802 <BR>
     * <BR>
     * 00000204(残高不足エラー)の場合 ：「残高不足エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01803 <BR>
     * <BR>
     * 00000801(2重送信エラー)の場合 ：「2重送信エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01799 <BR>
     * <BR>
     * 以外の受付結果の場合 ：「その他のFXシステムエラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FX口座開設 」<BR>
     * （FX口座開設）submit口座開設) <BR>
     * : 1.1.3 createFX口座情報一覧(String, String, String) <BR>
     * 戻り値がnullの場合、例外をthorwする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02F1
     */
    protected WEB3FXAccOpenCompleteResponse submitAccountOpen(
        WEB3FXAccOpenCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAccountOpen(WEB3FXAccOpenCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) validate( )
        l_request.validate();
        
        //GFT電文保存テーブルに
        //データをinsertする。
        
        //insertGFT電文保存(GFT結果通知電文明細)
        //アイテムの定義
        //GFT電文保存テーブルに行をinsertする。
        //[引数の設定] 
        //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        
        l_fxDataControlService.insertGFTMessage(l_request.fxGftResultNoticeTelegramUnit);
        
        //1.3) getGFT口座開設状況(String, String, String)
        //アイテムの定義
        //GFT口座開設状況Paramsを取得する。 
        //[引数の設定] 
        //証券会社コード：　@リクエストデータ.GFT結果通知電文明細.会社コード 
        //部店コード：　@リクエストデータ.GFT結果通知電文明細.部店コード 
        //識別コード：　@リクエストデータ.GFT結果通知電文明細.識別コード
        GftAccountOpenStatusParams l_params = 
            l_fxDataControlService.getGFTAccountOpenStatus(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                l_request.fxGftResultNoticeTelegramUnit.branchCode,
                l_request.fxGftResultNoticeTelegramUnit.requestNumber);
        
        //戻り値がnullの場合、例外をthorwする。
        if(l_params == null)
        {
            log.debug("GFT口座開設状況取得エラー");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT口座開設状況取得エラー");   
        }

        //get会社別FXシステム条件(String, String, String)
        //証券会社コード： リクエストデータ.GFT結果通知電文明細.証券会社コード
        //部店コード：　@リクエストデータ.GFT結果通知電文明細.部店コード
        //FXシステムコード：　@getGFT口座開設状況()の戻り値.FXシステムコード
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fxDataControlService.getCompFxCondition(
                    l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                    l_request.fxGftResultNoticeTelegramUnit.branchCode,
                    l_params.getFxSystemCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        boolean l_blnGFTTelegramSet = false;
        boolean l_blnGFTTelegramLengthPropSame = false;
        boolean l_blnMailSame = false;

        //get会社別FXシステム条件()の戻り値.SOAP接続実施区分==０：SOAP接続未実施の場合
        if (WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(
            l_compFxConditionParams.getSoapConnectDiv()))
        {
            // isGFT電文項目設定(GFT結果通知電文明細)
            //アイテムの定義
            //結果通知電文の必須項目に値が設定されているか判定する。
            //[引数の設定]
            //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
            WEB3FXTelegramProcessService l_fXTelegramProcessService =
                (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);

            l_blnGFTTelegramSet =
                l_fXTelegramProcessService.isGFTTelegramSet(l_request.fxGftResultNoticeTelegramUnit);

            //isGFT電文桁数属性一致(GFT結果通知電文明細)
            //アイテムの定義
            //結果通知電文の設定値のフォーマットが正しいか判定する。
            //[引数の設定]
            //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
            l_blnGFTTelegramLengthPropSame =
                l_fXTelegramProcessService.isGFTTelegramLengthPropSame(
                    l_request.fxGftResultNoticeTelegramUnit);

            //isGFT電文項目設定()=true and isGFT電文桁数属性一致()=true の場合、実施
            if (l_blnGFTTelegramSet && l_blnGFTTelegramLengthPropSame)
            {
                // isGFT電文送受信項目一致(GFT結果通知電文明細)
                //アイテムの定義
                //送受信電文の項目が一致するか判定する。
                //[引数の設定]
                //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
                l_blnMailSame =
                    l_fXTelegramProcessService.isGFTTelegramSendAndReceiveValueSame(
                        l_request.fxGftResultNoticeTelegramUnit);
            }
        }

        //get同時開設FXシステムコード(String)
        //[引数]
        //証券会社コード： リクエストデータ.GFT結果通知電文明細.証券会社コード
        String l_strSameTimeFxSystemCode =
            l_fxDataControlService.getSameTimeFxSystemCode(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode);

        //1.7 (*1)
        //(*1)以下のいずれかに当てはまる場合、実施する。
        //※get会社別FXシステム条件()の戻り値.SOAP接続実施区分 != 0：SOAP接続未実施の場合、③、④は条件に含めない
        //①@送受信区分が不正の場合（2重受信エラー）
        //（getGFT口座開設状況()の戻り値のGFT口座開設状況Params.送受信区分 ＝ 2(受信済)）
        //②送受信区分が不正の場合（その他エラー）
        //（getGFT口座開設状況()の戻り値のGFT口座開設状況Params.送受信区分 != (1(送信済)、2(受信済))
        //③結果通知電文内容の妥当性が不正の場合
        //（isGFT電文項目設定()＝fasle、または、isGFT電文桁数属性一致()＝false）
        //④送受信電文の項目が不一致の場合
        //（isGFT電文送受信項目一致()＝false）
        //⑤振替が正常に受付られなかった場合
        //(リクエストデータ.GFT結果通知電文明細.受付結果 != 00000000（正常））
        if (!WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(
            l_compFxConditionParams.getSoapConnectDiv()))
        {
            l_blnGFTTelegramSet = true;
            l_blnGFTTelegramLengthPropSame = true;
            l_blnMailSame = true;
        }
        if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv())
            || (!(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()) 
                    || WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
                )
            || (!l_blnGFTTelegramSet || !l_blnGFTTelegramLengthPropSame)
            || (!l_blnMailSame)
            || !WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode)
            )
        {
            //1.7.1 (*2)
            //(*2)
            //①@以外の場合、実施する。
            if(!WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()))
            {
                //1.7.1.1) updateGFT口座開設状況(GFT口座開設状況Params, GFT結果通知電文明細, String)
                //アイテムの定義
                //GFT口座開設状況の更新を行う。 
                //[引数の設定] 
                //　@GFT口座開設状況Params：　@getGFT口座開設状況()の戻り値 
                //　@GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細 
                //　@エラー理由コード：　@ 
                //　@　@②の場合：0001（送受信区分チェックエラー） 
                //　@　@③の場合：0002（パラメータ妥当性チェックエラー） 
                //　@　@④の場合：0003（パラメータ一致チェックエラー） 
                //　@　@⑤の場合：0004（受付結果コードチェックエラー）
                String l_strReasonCodeDef = null;
                
                if(!(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()) 
                   || WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
                   )
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.SENDRCV_ERROR;
                }
                else if(l_blnGFTTelegramSet == false || l_blnGFTTelegramLengthPropSame == false)
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.PARAM_VALIDITY_ERROR;                    
                }
                else if(l_blnMailSame ==false)
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.PARAM_MISMATCH_ERROR;                    
                    
                }
                else if(!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    l_strReasonCodeDef = WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR;
                }
                
                l_fxDataControlService.updateGFTAccountOpenStatus(
                    l_params,
                    l_request.fxGftResultNoticeTelegramUnit,
                    l_strReasonCodeDef);
            }
            
            //1.7.2) (*)例外をthrow
            //(*)例外をthrowする。　@　@
            //　@①@の場合：「2重受信エラー」
            if(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01972,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "2重受信エラー");   
            }
            
            //　@②③④の場合：「その他のFXシステムエラー」
            if( (!(WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_params.getSendRcvDiv()) 
                    || WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_params.getSendRcvDiv()))
                 )
                || (l_blnGFTTelegramSet == false || l_blnGFTTelegramLengthPropSame == false)
                || (l_blnMailSame == false))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "その他のFXシステムエラー");   
            }
            
            //　@⑤の場合：GFT結果通知電文明細.受付結果に応じて以下の例外となる
            //　@　@　@　@　@　@　@　@00000105(ホスト処理時間外)の場合 ：「受付時間外エラー」
            //　@　@　@　@　@　@　@　@00000199(ホストシステムエラー)の場合   ：「通信エラー」
            //　@　@　@　@　@　@　@　@00000204(残高不足エラー)の場合  ：「残高不足エラー」
            //　@　@　@　@　@　@　@　@00000801(2重送信エラー)の場合  ：「2重送信エラー」
            //　@　@　@　@　@　@　@　@以外の受付結果の場合        ：「その他のFXシステムエラー」
            if(!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode))
            {
                if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000105.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01801,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "受付時間外エラー");                    
                }
                else if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "通信エラー");                    
                }
                else if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000204.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "残高不足エラー");                    
                }
                else if(WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000801.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01799,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "2重送信エラー");                    
                }
                else
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "その他のFXシステムエラー");  
                }
            }
        }
        
        //FX顧客テーブル、および、
        //FX口座番号テーブルに
        //データをinsertする。
        
        //1.8) insertFX顧客(GFT結果通知電文明細)
        l_fxDataControlService.insertFXAccount(
                l_request.fxGftResultNoticeTelegramUnit, l_params);

        //get同時開設FXシステムコード()の戻り値がnullでない場合実施する。
        if (l_strSameTimeFxSystemCode != null)
        {
            //insert同時口座開設(GFT結果通知電文明細, GFT口座開設状況Params, String)
            //GFT結果通知電文明細： GFT結果通知電文明細オブジェクト
            //GFT口座開設状況Params： GFT口座開設状況行オブジェクト
            //同時口座開設FXシステムコード：　@get同時口座開設FXシステムコード()の戻り値
            l_fxDataControlService.insertSimultaneousAccountOpen(
                l_request.fxGftResultNoticeTelegramUnit,
                l_params,
                l_strSameTimeFxSystemCode);
        }

        //insertFX口座番号(GFT結果通知電文明細, String)
        //[引数]
        // GFT結果通知電文明細： GFT結果通知電文明細オブジェクト
        // FXシステムコード：　@GFT口座開設状況Params.FXシステムコード
        l_fxDataControlService.insertFXAccountCode(
            l_request.fxGftResultNoticeTelegramUnit,
            l_params.getFxSystemCode(),
            l_strSameTimeFxSystemCode);

        //1.10) get補助口座(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.11.1) get代理入力者( )
        Trader l_trader = this.getTrader();

        // get会社別FXシステム条件()の戻り値.口座種別==01：FXの場合実施する。
        if (WEB3AccTypeDef.FX.equals(l_compFxConditionParams.getAccType()))
        {
            //get会社別FXシステム条件()の戻り値.口座区分リアル更新==１：リアル更新の場合
            if (WEB3AccOpenRealUpdateDef.REAL_UPDATE.equals
                (l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                //1.11.3) updateFX口座開設区分(String, String, String, String, String)
                //アイテムの定義
                //顧客マスターのFX口座開設区分を更新する。
                //[引数の設定]
                //証券会社コード：　@リクエストデータ.GFT結果通知電文明細.会社コード
                //部店コード：　@リクエストデータ.GFT結果通知電文明細.部店コード
                //顧客コード：　@リクエストデータ.GFT結果通知電文明細.顧客コード
                //更新後FX口座開設区分：　@1(口座開設)
                //更新者コード：
                //get代理入力者()＝nullの場合、補助口座.getMainAccount().getAccountCode()
                //　@get代理入力者()!=nullの場合、get代理入力者()の戻り値の扱者.getTraderCode()
                if(l_trader == null)
                {
                    l_fxDataControlService.updateFXAccountOpenDiv(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        WEB3AccountOpenDef.OPEN,
                        l_subAccount.getMainAccount().getAccountCode());
                }
                else
                {
                    l_fxDataControlService.updateFXAccountOpenDiv(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        WEB3AccountOpenDef.OPEN,
                        l_trader.getTraderCode());
                }
            }
            //get会社別FXシステム条件()の戻り値.口座区分リアル更新==０：リアル更新しない場合
            else if (WEB3AccOpenRealUpdateDef.NOT_REAL_UPDATE.equals(
                l_compFxConditionParams.getAccOpenRealUpdate()))
            {
                //updateFX口座開設区分更新者コード(String, String, String, String)
                //アイテムの定義
                //顧客マスターのFX口座開設区分を更新する。
                //[引数の設定]
                //証券会社コード：　@リクエストデータ.GFT結果通知電文明細.会社コード
                //部店コード：　@リクエストデータ.GFT結果通知電文明細.部店コード
                //顧客コード：　@リクエストデータ.GFT結果通知電文明細.顧客コード
                //更新者コード：
                //　@get代理入力者()＝nullの場合、補助口座.getMainAccount().getAccountCode()
                //　@get代理入力者()!=nullの場合、get代理入力者()の戻り値の扱者.getTraderCode()
                if(l_trader == null)
                {
                    l_fxDataControlService.updateFXAccountOpenDivUpdaterCode(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        l_subAccount.getMainAccount().getAccountCode());
                }
                else
                {
                    l_fxDataControlService.updateFXAccountOpenDivUpdaterCode(
                        l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                        l_request.fxGftResultNoticeTelegramUnit.branchCode,
                        l_request.fxGftResultNoticeTelegramUnit.accountCode,
                        l_trader.getTraderCode());
                }
            }
        }

        //引数の内容で口座開設区分テーブル(acc_open_div)に行のinsertを行う。
        //口座ID：　@補助口座.getMainAccount().getAccountId()
        //口座種別：　@get会社別FXシステム条件()の戻り値.口座種別
        //口座開設区分：
        //１）get会社別FXシステム条件()の戻り値.口座区分リアル更新==１：リアル更新の場合
        //　@　@1:開設済
        //２）get会社別FXシステム条件()の戻り値.口座区分リアル更新==０：リアル更新しない場合
        //　@　@0:未開設
        //更新者コード：
        //　@get代理入力者()＝nullの場合、補助口座.getMainAccount().getAccountCode()
        //get代理入力者()!=nullの場合、get代理入力者()の戻り値の扱者.getTraderCode()
        WEB3GentradeAccOpenDiv l_genAccOpenDiv = new WEB3GentradeAccOpenDiv();
        long l_lonAccountId = l_subAccount.getMainAccount().getAccountId();
        String l_strAccType = l_compFxConditionParams.getAccType();
        String l_strAccOpenDiv = "";
        String l_strLastUpdater = "";
        if (WEB3AccOpenRealUpdateDef.REAL_UPDATE.equals(
            l_compFxConditionParams.getAccOpenRealUpdate()))
        {
        	l_strAccOpenDiv = WEB3AccountOpenDef.OPEN;
        }
        else if (WEB3AccOpenRealUpdateDef.NOT_REAL_UPDATE.equals(
            l_compFxConditionParams.getAccOpenRealUpdate()))
        {
        	l_strAccOpenDiv = WEB3AccountOpenDef.NOT_OPEN;
        }
        if (l_trader == null)
        {
        	l_strLastUpdater = l_subAccount.getMainAccount().getAccountCode();
        }
        else
        {
        	l_strLastUpdater = l_trader.getTraderCode();
        }
        l_genAccOpenDiv.insertAccOpenDiv(l_lonAccountId,
            l_strAccType,
            l_strAccOpenDiv,
            l_strLastUpdater);

        //get同時開設FXシステムコード()の戻り値がnullでない場合
        //引数の内容で口座開設区分テーブル(acc_open_div)に行のinsertを行う。
        if (l_strSameTimeFxSystemCode != null)
        {
        	//[引数の設定]
        	//口座ID：　@補助口座.getMainAccount().getAccountId()
        	//口座種別：　@ 02:CFD
        	//口座開設区分：
        	//１）get会社別FXシステム条件()の戻り値.口座区分リアル更新==１：リアル更新の場合
        	//1:開設済 
        	//２）get会社別FXシステム条件()の戻り値.口座区分リアル更新==０：リアル更新しない場合
        	//0:未開設
        	//更新者コード：
        	//　@get代理入力者()＝nullの場合、補助口座.getMainAccount().getAccountCode()
        	//get代理入力者()!=nullの場合、get代理入力者()の戻り値の扱者.getTraderCode()
            l_genAccOpenDiv.insertAccOpenDiv(l_lonAccountId,
                WEB3AccTypeDef.CFD,
                l_strAccOpenDiv,
                l_strLastUpdater);
        }

        //1.12) updateGFT口座開設状況(GFT口座開設状況Params, GFT結果通知電文明細, String)
        //アイテムの定義
        //GFT口座開設状況の更新を行う。 
        //[引数の設定] 
        //　@GFT口座開設状況Params：　@getGFT口座開設状況()の戻り値 
        //　@GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細 
        //　@エラー理由コード：　@0000（正常）
        l_fxDataControlService.updateGFTAccountOpenStatus(
            l_params,
            l_request.fxGftResultNoticeTelegramUnit,
            WEB3GftErrorReasonCodeDef.NORMAL);
        
        //1.14) createResponse( )
        WEB3FXAccOpenCompleteResponse l_response = 
            (WEB3FXAccOpenCompleteResponse)l_request.createResponse();
        
        //1.15) (*)プロパティセット
        //レスポンスデータにプロパティをセットする。
        //為替保証金ログインID：　@リクエストデータ.GFT結果通知電文明細.初期ログインID
        l_response.fxLoginId = l_request.fxGftResultNoticeTelegramUnit.fxFirstLoginId;
        
        //為替保証金口座情報一覧：　@GFT結果通知電文明細.為替保証金口座情報一覧
        l_response.fxAccInformationList =
            l_request.fxGftResultNoticeTelegramUnit.fxAccInformationList;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit口座開設（SOAP接続）)<BR>
     * 口座開設の完了処理を行う。<BR>
     * ※SOAP接続にて行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX口座開設）submit口座開設（SOAP接続）」参照。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenCompleteSoapResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C78E8E02E2
     */
    protected WEB3FXAccOpenCompleteSoapResponse submitAccountOpenSoap(
        WEB3FXAccOpenCompleteSoapRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitAccountOpenSoap(WEB3FXAccOpenCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        //validate( )
        l_request.validate();

        //get会社別FXシステム条件(String, String, String)
        //証券会社コード： get口座.getInstitution().getInstitutionCode()
        //部店コード：　@get口座.getBranch().getBranchCode()
        //FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード
        String l_strInstitutionCodeByMainAccount =
            this.getMainAccount().getInstitution().getInstitutionCode();
        String l_strBranchCodeByMainAccount =
            this.getMainAccount().getBranch().getBranchCode();
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams =
                l_fxDataControlService.getCompFxCondition(
                    l_strInstitutionCodeByMainAccount,
                    l_strBranchCodeByMainAccount,
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //口座開設依頼処理を行う。
        //[引数]
        //リクエストデータ： 引数.リクエストデータをFX口座開設依頼リクエストにキャストしたもの
        WEB3FXAccOpenAskingRequest l_fxAccOpenAskingRequest = new WEB3FXAccOpenAskingRequest();
        l_fxAccOpenAskingRequest.agreementDiv = l_request.agreementDiv;
        l_fxAccOpenAskingRequest.complianceInfo = l_request.complianceInfo;
        l_fxAccOpenAskingRequest.fxMailAddress = l_request.fxMailAddress;
        l_fxAccOpenAskingRequest.fxPassword = l_request.fxPassword;
        l_fxAccOpenAskingRequest.fxTradeAgreementList = l_request.fxTradeAgreementList;
        l_fxAccOpenAskingRequest.password = l_request.password;
        l_fxAccOpenAskingRequest.regetServiceId = l_request.regetServiceId;
        l_fxAccOpenAskingRequest.wolfAid = l_request.wolfAid;
        l_fxAccOpenAskingRequest.wolfSession = l_request.wolfSession;
        l_fxAccOpenAskingRequest.wolfSsid = l_request.wolfSsid;
        l_fxAccOpenAskingRequest.fxSystemCode = l_request.fxSystemCode;
        WEB3FXAccOpenAskingResponse l_fxAccOpenAskingResponse = this.startAccountOpen(l_fxAccOpenAskingRequest);
        //start口座開設の戻るFX口座開設依頼レスポンス.GFT依頼電文明細にプロパティをセットする。
        //
        //FX口座開設依頼レスポンス.GFT依頼電文明細.FX暗証番号２ = リクエストデータ.FX暗証番号２
        //（リクエストデータ.FX暗証番号２ = nullの場合、nullをセットする）
        if (l_request.fxPassword2 != null)
        {
            l_fxAccOpenAskingResponse.fxGftAskingTelegramUnit.fxPassword2 = l_request.fxPassword2;
        }
        else
        {
            l_fxAccOpenAskingResponse.fxGftAskingTelegramUnit.fxPassword2 = null;
        }

        //do口座開設実行
        WEB3FXAccOpenConnection l_controlService =
            (WEB3FXAccOpenConnection)Services.getService(WEB3FXAccOpenConnection.class);
        WEB3FXGftResultNoticeTelegramUnit  l_fxGftResultNoticeTelegramUnit =
            l_controlService.doAccountOpen(l_compFxConditionParams, l_fxAccOpenAskingResponse.fxGftAskingTelegramUnit);

        //コンストラクタにてインスタンス生成する。
        //FX口座開設完了リクエスト
        WEB3FXAccOpenCompleteRequest l_fxAccOpenCompleteRequest = new WEB3FXAccOpenCompleteRequest();

        //FX口座開設完了リクエストのプロパティをセットする。
        //GFT結果通知電文明細： 生成した結果通知電文明細
        l_fxAccOpenCompleteRequest.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;

        //submit口座開設(FX口座開設完了リクエスト)
        //口座開設完了処理を行う。
        //[引数]
        //リクエストデータ： 生成したFX口座開設完了リクエスト
        WEB3FXAccOpenCompleteResponse l_fxAccOpenCompleteResponse = this.submitAccountOpen(l_fxAccOpenCompleteRequest);

        //レスポンスデータを生成する。
        WEB3FXAccOpenCompleteSoapResponse l_response = (WEB3FXAccOpenCompleteSoapResponse)l_request.createResponse();

        //以下のとおりに、プロパティをセットする。
        //為替保証金ログインID： FX口座開設完了レスポンス.為替保証金ログインID
        //為替保証金口座情報一覧： FX口座開設完了レスポンス.為替保証金口座情報一覧
        l_response.fxLoginId = l_fxAccOpenCompleteResponse.fxLoginId;
        l_response.fxAccInformationList = l_fxAccOpenCompleteResponse.fxAccInformationList;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (getGFT処理区分)<BR>
     * 処理区分にセットする値を設定する。<BR>
     * <BR>
     * １）FXシステムコード一覧取得<BR>
     * 　@FXデータ制御サービス.getGFTFXシステムコード一覧()をコールする。<BR>
     * <BR>
     * 　@[引数の設定]<BR>
     * 　@証券会社コード：　@補助口座.証券会社コード<BR>
     * 　@部店コード：　@補助口座.get取引店.getBranchCode()<BR>
     * <BR>
     * ２）<BR>
     * 　@FXデータ制御サービス.isGFT口座開設()をコールする。<BR>
     * 　@証券会社コード： 補助口座.証券会社コード<BR>
     * 　@部店コード： 補助口座.get取引店.getBranchCode()<BR>
     * 　@顧客コード： 補助口座.getMainAccount().getAccountCode()<BR>
     * 　@FXシステムコード一覧：　@getGFTFXシステムコード一覧()の戻り値<BR>
     * <BR>
     * 　@２－１）isGFT口座開設()の戻り値 ==falseの場合<BR>
     * 　@　@　@　@　@新規開設：01を返却する。<BR>
     * <BR>
     * 　@２－２）isGFT口座開設()の戻り値 ==trueの場合<BR>
     * 　@　@　@　@　@追加開設：03を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getGFTOperationDiv(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getGFTOperationDiv(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //１）FXシステムコード一覧取得
        //FXデータ制御サービス.getGFTFXシステムコード一覧()をコールする。
        //[引数の設定]
        //証券会社コード：　@補助口座.証券会社コード
        //部店コード：　@補助口座.get取引店.getBranchCode()
        WEB3FXDataControlService l_controlService =
            (WEB3FXDataControlService) Services.getService(
                WEB3FXDataControlService.class);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        ArrayList l_lisGFTFxSystemCodeList = null;
        l_lisGFTFxSystemCodeList =
            l_controlService.getGFTFxSystemCodeLists(l_strInstitutionCode, l_strBranchCode);

        //FXデータ制御サービス.isGFT口座開設()をコールする。
        //証券会社コード： 補助口座.証券会社コード
        //部店コード： 補助口座.get取引店.getBranchCode()
        //顧客コード： 補助口座.getMainAccount().getAccountCode()
        //FXシステムコード一覧：　@getGFTFXシステムコード一覧()の戻り値
        boolean l_blnisGFTAccOpen = false;
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        l_blnisGFTAccOpen = l_controlService.isGFTAccOpen(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode,
            l_lisGFTFxSystemCodeList);

        //isGFT口座開設()の戻り値 ==falseの場合
        //新規開設：01を返却する。
        //isGFT口座開設()の戻り値 ==trueの場合
        //追加開設：03を返却する。
        if (!l_blnisGFTAccOpen)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT;
        }
    }
}@
