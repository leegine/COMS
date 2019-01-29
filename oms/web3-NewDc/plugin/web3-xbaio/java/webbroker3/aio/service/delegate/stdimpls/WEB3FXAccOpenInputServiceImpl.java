head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設入力サービスImpl(WEB3FXAccOpenInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/26 周勇 (中訊) 新規作成
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更 モデルNo.850、No.853、No.876、No.882
Revesion History : 2008/09/22 武波 (中訊) 仕様変更・モデル1003
Revesion History : 2009/03/18 車進 (中訊) 仕様変更・モデル1122、1125、1126
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.define.WEB3AioFxSystemCodeDivDef;
import webbroker3.aio.message.WEB3FXAccOpenInputRequest;
import webbroker3.aio.message.WEB3FXAccOpenInputResponse;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementRequest;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementResponse;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.aio.service.delegate.WEB3FXAccOpenInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3QuestionCheckDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX口座開設入力サービスImpl) <BR>
 * FX口座開設入力サービス実装クラス <BR>
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3FXAccOpenInputServiceImpl extends WEB3ClientRequestService
    implements WEB3FXAccOpenInputService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenInputServiceImpl.class);  
    
    /**
     * @@roseuid 41E783E203B9
     */
    public WEB3FXAccOpenInputServiceImpl()
    {
    }

    /**
     * FX口座開設サービス処理を行う。 <BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。 <BR>
     * ・get取引同意画面() <BR>
     * ・get入力画面() <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C6540E024B
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
        if (l_request instanceof WEB3FXAccOpenTradeAgreementRequest)
        {
            l_response =
                this.getTradeAgreementScreen((WEB3FXAccOpenTradeAgreementRequest) l_request);
        }
        else if (l_request instanceof WEB3FXAccOpenInputRequest)
        {
            l_response =
                this.getInputScreen((WEB3FXAccOpenInputRequest) l_request);
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3FXAccOpenTradeAgreementRequest "
                    + " と WEB3FXAccOpenInputRequestt以外である, but is "
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
     * (get取引同意画面) <BR>
     * FX口座開設取引同意画面表示データの取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX口座開設入力）get取引同意画面」参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FX口座開設入力 」<BR>
     * （FX口座開設入力）get取引同意画面 )<BR>
     * : 1.6 get質問(String, String) <BR>
     * 戻り値がnullの場合、例外をthorwする。 <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenTradeAgreementResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C654E001FD
     */
    protected WEB3FXAccOpenTradeAgreementResponse getTradeAgreementScreen(
        WEB3FXAccOpenTradeAgreementRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getTradeAgreementScreen(WEB3FXAccOpenTradeAgreementRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1) get補助口座(SubAccountTypeEnum)
        //アイテムの定義
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2) validate注文(SubAccount)
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
        
        //1.3) get会社別FXシステム条件(String, String, String)
        //アイテムの定義
        //会社別FXシステム条件Paramsを取得する。 
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        WEB3FXDataControlService l_fxDataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        
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
            log.debug("__NotFoundExcepiton__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);        
        }

        //1.4) validate外部システム受付可能(String)
        //アイテムの定義
        //FXシステムの受付時間チェックを行う。
        //[引数の設定] 
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        String l_strFxSystemCode = l_compFxConditionParams.getFxSystemCode();
        if (!WEB3AioFxSystemCodeDivDef.TFX.equals(l_strFxSystemCode))
        {
            l_aioOrderManager.validateOtherSystemAcceptPossible(l_strFxSystemCode);
        }
        
        //1.5) validateFX口座開設可能(SubAccount, String)
        //アイテムの定義
        //FX口座開設が可能であるかをチェックする。 
        //[引数の設定] 
        //補助口座：　@get補助口座()の戻り値 
        //会社別FXシステム条件Params：　@get会社別FXシステム条件()の戻り値
        l_aioOrderManager.validateFXAccOpenPossible(l_subAccount, l_compFxConditionParams);
        
        //1.6) validate年齢下限値(SubAccount, String, long)
        //顧客の年齢が、会社、部店が指定する年齢下限値を 
        //超えるかのチェックを行う。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //プリファ@レンス名：　@fxaccountopen.lowlimit.age 
        //プリファ@レンス名の連番：1
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
        l_branch.validateAgeLowLimit(
            (WEB3GentradeSubAccount)l_subAccount, 
            WEB3BranchPreferencesNameDef.LOWLIMIT_AGE, 
            1);
        
        //1.7) validate年齢上限値(SubAccount, String, long)
        //顧客の年齢が、会社、部店が指定する年齢上限値を 
        //超えるかのチェックを行う。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //プリファ@レンス名：　@fxaccountopen.highlimit.age 
        //プリファ@レンス名の連番：　@1 
        l_branch.validateAgeHighLimit(
            (WEB3GentradeSubAccount)l_subAccount, 
            WEB3BranchPreferencesNameDef.HIGHLIMIT_AGE, 
            1);
        
        //1.8) get質問(String, String, String)
        //アイテムの定義
        //質問Paramsを取得する。
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        QuestionParams[] l_questionParams = 
            l_fxDataControlService.getQuestions(
                l_strInstitutionCode,
                l_strBranchCode,
                l_request.fxSystemCode);
        
        //戻り値がnullの場合、例外をthorwする。
        if(l_questionParams == null)
        {
            log.debug("質問内容取得エラー");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "質問内容取得エラー");   
        }
        
        List l_lisQuestionParams = new Vector();
        
        //1.9) get質問()の戻り値の質問Params要素数ごとのLoop処理
        for(int i = 0; i < l_questionParams.length; i++)
        {
            //1.9.1) FX取引同意質問情報( )
            //アイテムの定義
            //FX取引同意質問情報オブジェクトを生成する。
            WEB3FXTradeAgreementUnit l_fXTradeAgreementUnit = new WEB3FXTradeAgreementUnit();
            
            //FX取引同意質問情報を生成し、プロパティセットを行う。
            //　@質問番号：　@質問Params.質問番号
            //　@質問内容：　@質問Params.質問内容
            //　@※質問回答の設定は行
            QuestionParams l_questionParam = l_questionParams[i];
            l_fXTradeAgreementUnit.questionNumber = l_questionParam.getQuestionNo();
            l_fXTradeAgreementUnit.questionContents = l_questionParam.getQuestion();
            
            l_lisQuestionParams.add(l_fXTradeAgreementUnit);
        }
        
        //1.10) createResponse( )
        WEB3FXAccOpenTradeAgreementResponse l_response = 
            (WEB3FXAccOpenTradeAgreementResponse)l_request.createResponse();
        
        //1.11) (*)プロパティセット
        //レスポンスデータにプロパティをセットする。
        //顧客名：　@顧客(*1).名前（苗字）
        //取引同意質問情報一覧：　@（*上記で編集したFX取引同意質問情報の配列）
        //(*1)補助口座.getMainAccount()にて取得

        MainAccountParams l_mainAccountParams = 
            (MainAccountParams)l_subAccount.getMainAccount().getDataSourceObject();
        
        WEB3FXTradeAgreementUnit[] l_fXTradeAgreementUnitToResponse = 
            new WEB3FXTradeAgreementUnit[l_lisQuestionParams.size()];
        l_lisQuestionParams.toArray(l_fXTradeAgreementUnitToResponse);

        l_response.accountName = l_mainAccountParams.getFamilyName();
        
        l_response.fxTradeAgreementList = l_fXTradeAgreementUnitToResponse;
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面) <BR>
     * FX口座開設入力画面表示データの取得処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FX口座開設入力）get入力画面」参照。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C655740316
     */
    protected WEB3FXAccOpenInputResponse getInputScreen(
        WEB3FXAccOpenInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FXAccOpenInputRequest l_request)";
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
        
        //1.8)  （＊）電子鳩チェックを行う。
        //（リクエスト.電子鳩チェックフラグ==trueの場合）        
        String[] l_strRequestCodes = null;
            
        if (l_request.batoCheckFlag)
        {
            //1.8.1) validateFXドキュメント閲覧履歴(String, String[])
            //FXドキュメントが閲覧済みかの確認を行う。 
            //[引数の設定] 
            //種別コード：リクエスト.種別コード 
            //識別コード：リクエスト.識別コード[]
            l_strRequestCodes = 
                l_fxDataControlService.validateFxDocReadHistory(
                    l_request.typeCode,
                    l_request.requestCode);

            //1.8.2)（＊）validateFXドキュメント閲覧履歴( )の戻り値配列がnullの場合
            if (l_strRequestCodes == null)
            {
                //1.8.2.1 insert説明不要承諾履歴(String, String, String)
                //FX説明不要承諾履歴管理テーブルに行のinsertを行う。 
                //[引数の設定] 
                //証券会社コード： 補助口座.証券会社コード 
                //部店コード： 補助口座.get取引店.getBranchCode() 
                //顧客コード： 補助口座.getMainAccount().getAccountCode()
                l_fxDataControlService.insertUnnecessaryExplanation(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode());
            }
        }
        else
        {
            //1.9 （＊）リクエスト.電子鳩チェックフラグ==falseの場合
            //insert説明不要承諾履歴(String, String, String)
            //FX説明不要承諾履歴管理テーブルに行のinsertを行う。 
            //[引数の設定] 
            //証券会社コード： 補助口座.証券会社コード 
            //部店コード： 補助口座.get取引店.getBranchCode() 
            //顧客コード： 補助口座.getMainAccount().getAccountCode()
            l_fxDataControlService.insertUnnecessaryExplanation(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode());   
        }

        String l_strFxMailAddress = null;
        boolean l_blnIsGFTAccOpen = false;
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFTの場合
        if (WEB3ExtConnectSystemCodeDef.GFT.equals(
            l_compFxConditionParams.getExtConnectSystemCode()))
        {
            //getGFTFXシステムコード一覧(String, String)
            //証券会社コード： 補助口座.証券会社コード
            //部店コード： 補助口座.get取引店.getBranchCode()
            ArrayList l_arrayListGFXFxSystemCodeLists =
                l_fxDataControlService.getGFTFxSystemCodeLists(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode());

            // getGFTFXメールアドレス(SubAccount,ArreyList)
            //補助口座：　@get補助口座()の戻り値
            //FXシステムコード一覧：　@getGFTFXシステムコード一覧()の戻り値
            l_strFxMailAddress = l_fxDataControlService.getGFTFxMailAddress(
                l_subAccount,
                l_arrayListGFXFxSystemCodeLists);

            //isGFT口座開設(Stinrg, String, String, ArreyList)
            //証券会社コード： 補助口座.証券会社コード
            //部店コード： 補助口座.get取引店.getBranchCode()
            //顧客コード： 補助口座.getMainAccount().getAccountCode()
            //FXシステムコード一覧：　@getFXシステムコード一覧()の戻り値
            l_blnIsGFTAccOpen = l_fxDataControlService.isGFTAccOpen(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_arrayListGFXFxSystemCodeLists);
        }
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFT以外の場合
        else
        {
            // getFXメールアドレス(SubAccount,ArreyList)
            //補助口座：　@get補助口座()の戻り値
            l_strFxMailAddress = l_fxDataControlService.getFxMailAddress(
                l_subAccount);
        }

        //1.10) createResponse( )
        WEB3FXAccOpenInputResponse l_response = 
            (WEB3FXAccOpenInputResponse)l_request.createResponse();
        
        //1.11) (*)プロパティセット
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFTの場合
        //　@メールアドレス：　@getGFTFXメールアドレス()の戻り値
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFT以外の場合
        //　@メールアドレス：　@getFXメールアドレス()の戻り値
        l_response.mailAddress = l_strFxMailAddress;

        //識別コード：validateFXドキュメント閲覧履歴( )の戻り値配列（nullの場合、セットしない）（*1）
        //(*1)リクエスト.電子鳩チェックフラグ==falseの場合は、セットしない。
        if (l_request.batoCheckFlag && l_strRequestCodes != null)
        {
            l_response.requestCode = l_strRequestCodes;
        }

        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFTの場合
        //GFT口座開設フラグ：　@isGFT口座開設（）の戻り値
        //get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFT以外の場合
        //false
        l_response.gftAccOpenFlag = l_blnIsGFTAccOpen;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
