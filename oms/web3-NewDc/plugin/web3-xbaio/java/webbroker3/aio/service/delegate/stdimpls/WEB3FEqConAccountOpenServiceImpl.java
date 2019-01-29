head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設サービスImpl(WEB3FEqConAccountOpenServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/21 屈陽 (中訊) 新規作成   
                 : 2007/1/2  何文敏 (中訊) 仕様変更・モデル668
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.message.WEB3FEqConAccountOpenAgreementRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenAgreementResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenCompleteRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenCompleteResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenConfirmRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenConfirmResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenInputRequest;
import webbroker3.aio.message.WEB3FEqConAccountOpenInputResponse;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.aio.service.delegate.WEB3FEqConAccountOpenService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.define.WEB3GentradeBatoFunctionDivDef;
import webbroker3.gentrade.define.WEB3GentradeBatoServiceRegServiceResultDef;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株口座開設サービスImpl)<BR>
 * 外株口座開設サービス実装クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenServiceImpl extends WEB3ClientRequestService 
    implements WEB3FEqConAccountOpenService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConAccountOpenServiceImpl.class);  
    
    /**
     * @@roseuid 423562E500BB
     */
    public WEB3FEqConAccountOpenServiceImpl() 
    {
     
    }
    
    /**
     * 外株口座開設処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下の処理をコールする。<BR>
     * <BR>
     *   get取引同意画面()<BR>
     *   get入力画面()<BR>
     *   validate申込()<BR>
     *   submit申込()
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E37DBE03B4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //リクエストデータの型により、以下の処理をコールする。
        //get取引同意画面()
        //get入力画面()
        //validate申込()
        //submit申込()
        if (l_request instanceof WEB3FEqConAccountOpenAgreementRequest)
        {
            l_response = 
                getAgreementScreen((WEB3FEqConAccountOpenAgreementRequest)l_request);   
        }
        else if (l_request instanceof WEB3FEqConAccountOpenInputRequest)
        {
            l_response =
                getInputScreen((WEB3FEqConAccountOpenInputRequest)l_request);
        }
        else if (l_request instanceof WEB3FEqConAccountOpenConfirmRequest)
        {
            l_response =
                validateApply((WEB3FEqConAccountOpenConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FEqConAccountOpenCompleteRequest)
        {
            l_response =
                submitApply((WEB3FEqConAccountOpenCompleteRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get取引同意画面)<BR>
     * 取引同意画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設）get取引同意画面」 参照
     * ------------------------------------------------
     * 1.4 validate電子鳩実施(機@能区分 : String)
     *  戻り値が”未同意顧客”の場合、例外をthrowする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01956<BR>
     * <BR>
     * ------------------------------------------------
     * 1.5 get質問(String, String)
     *  戻り値がnullの場合、例外をthorwする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConAccountOpenAgreementResponse
     * @@roseuid 41E38B5E026C
     */
    protected WEB3FEqConAccountOpenAgreementResponse getAgreementScreen(
        WEB3FEqConAccountOpenAgreementRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getAgreementScreen(WEB3FEqConAccountOpenAgreementRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate外株口座開設可能(SubAccount)
        //[引数] 
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate居住者(SubAccount)
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateResident(l_subAccount);

        //1.4 validate電子鳩実施
        //[引数] 
        //機@能区分： ”取引報告書実施チェック”
        WEB3GentradeBatoClientService l_batoClientService =
            (WEB3GentradeBatoClientService)Services.getService(
                WEB3GentradeBatoClientService.class);
       
        log.debug(" Real l_batoClientService = " +  l_batoClientService.getClass().getName());
        
        String l_strBato = 
            l_batoClientService.validateBato(
                WEB3GentradeBatoFunctionDivDef.BATO_TRAN_HIST_SERVICE);
        
        if (WEB3GentradeBatoServiceRegServiceResultDef.NOT_AGREEMENT.equals(l_strBato))
        {
            log.debug("validate電子鳩実施()戻り値が未同意顧客");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01956,
                this.getClass().getName() + "." + l_strMethodName,
                "validate電子鳩実施()戻り値が未同意顧客");            
        }
        
        //1.5 get質問(String, String)
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        String l_strInstitutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        //部店コード：　@補助口座.get取引店.getBranchCode()
        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        QuestionParams[] l_questionParams = 
            l_conTransferDataControlService.getQuestion(
                l_strInstitutionCode, l_strBranchCode);
        
        //get質問(String, String)戻り値がnullの場合、例外をthorwする
        if (l_questionParams == null)
        {
            log.debug("get質問(String, String)戻り値がnull");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "get質問(String, String)戻り値がnull");            
        }
        
        //1.6 ArrayList()
        List l_list = new ArrayList();
        
        //1.7 get質問()の戻り値の質問Params要素数ごとのLoop処理
        
        //外株口座開設質問情報を生成し、プロパティセットを行う。
        WEB3FEqConAccountOpenQuestionInfo l_conAccountOpenQuestionInfo = null;
            
        
        for (int i = 0; i < l_questionParams.length; i++)
        {
            //1.7.1 外株口座開設質問情報オブジェクトを生成する
            l_conAccountOpenQuestionInfo = new WEB3FEqConAccountOpenQuestionInfo();

            //質問番号：質問Params.質問番号
            l_conAccountOpenQuestionInfo.questionNumber = 
                l_questionParams[i].getQuestionNo();
            //質問内容：質問Params.質問内容
            l_conAccountOpenQuestionInfo.questionContent =
                l_questionParams[i].getQuestion();
            //質問回答の設定は行わない           
            
            //1.7.2 リストに外株口座開設質問情報オブジェクトを追加する
            l_list.add(l_conAccountOpenQuestionInfo);
        }
        
        //1.8 配列を取得する
        WEB3FEqConAccountOpenQuestionInfo[] l_conAccountOpenQuestionInfos =
            new WEB3FEqConAccountOpenQuestionInfo[l_list.size()];
        l_list.toArray(l_conAccountOpenQuestionInfos);
        
        //1.9 createResponse()
        WEB3FEqConAccountOpenAgreementResponse l_response = 
            (WEB3FEqConAccountOpenAgreementResponse)l_request.createResponse();
        
        //1.10 レスポンスデータにプロパティをセットする。
        //(*1)補助口座.getMainAccount()にて取得
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //レスポンス.顧客名：顧客(*1).get顧客表示名()の戻り値
        l_response.accountName = l_mainAccount.getDisplayAccountName();
        log.debug("レスポンス.顧客名：" + l_response.accountName);
        
        //レスポンス.質問情報一覧：（*上記で編集した外株口座開設質問情報の配列）
        l_response.questionInfoList = l_conAccountOpenQuestionInfos;

        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設）get入力画面」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConAccountOpenInputResponse
     * @@roseuid 421ADE7F03B2
     */
    protected WEB3FEqConAccountOpenInputResponse getInputScreen(
        WEB3FEqConAccountOpenInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "getInputScreen(WEB3FEqConAccountOpenInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate外株口座開設可能(SubAccount)
        //[引数] 
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate居住者(SubAccount)
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateResident(l_subAccount);

        //1.4 validate外株口座開設質問(外株口座開設質問情報[])
        //[引数] 
        //質問情報一覧： リクエストデータ.質問情報一覧
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        l_conTransferDataControlService.validateFeqAccountOpenQuestion(
            l_request.questionInfoList);
        
        //1.5 createResponse()
        WEB3FEqConAccountOpenInputResponse l_response = 
            (WEB3FEqConAccountOpenInputResponse)l_request.createResponse();
        
        //1.6 以下のとおりに、プロパティをセットする。
        //レスポンス.メールアドレス = 顧客Params(*1).emailアドレス
        //(*1)補助口座.getMainAccount().getDataSourceObject()にて取得
        l_response.mailAddress = 
            ((MainAccountParams)l_subAccount.getMainAccount().getDataSourceObject()).getEmailAddress();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (validate申込)<BR>
     * 申込の確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設）validate申込」 参照
     * <BR>
     * ------------------------------------------------
     * 1.3 validate外株口座開設可能(SubAccount)
     * (*)以下の条件のいずれかに一致する場合、例外をスローする。
     * リクエストデータ.外株用暗証番号 == null
     * リクエストデータ.外株用暗証番号（確認用） == null
     * リクエストデータ.外株用暗証番号 != リクエストデータ.外株用暗証番号（確認用）
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01963<BR>
     * <BR>
     * ------------------------------------------------
     * <BR>
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConAccountOpenConfirmRespons
     * @@roseuid 41E38B5E02AA
     */
    protected WEB3FEqConAccountOpenConfirmResponse validateApply(
        WEB3FEqConAccountOpenConfirmRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateApply(WEB3FEqConAccountOpenConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate外株口座開設可能(SubAccount)
        //[引数] 
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate居住者(SubAccount)
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateResident(l_subAccount);

        //(*)以下の条件のいずれかに一致する場合、例外をスローする。
        //リクエストデータ.外株用暗証番号 == null
        //リクエストデータ.外株用暗証番号（確認用） == null
        //リクエストデータ.外株用暗証番号 != リクエストデータ.外株用暗証番号（確認用）
        if ((l_request.feqPassword1 == null) ||
            (l_request.feqPassword2 == null) ||
            (!l_request.feqPassword1.equals(l_request.feqPassword2)))
        {
            log.debug(
                "リクエストデータ.外株用暗証番号 == null" +
                "リクエストデータ.外株用暗証番号（確認用）== null" +
                "リクエストデータ.外株用暗証番号 != リクエストデータ.外株用暗証番号（確認用）");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01963,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.外株用暗証番号 == null" +
                "リクエストデータ.外株用暗証番号（確認用）== null" +
                "リクエストデータ.外株用暗証番号 != リクエストデータ.外株用暗証番号（確認用）");
        }
        
        //1.4 createResponse()
        WEB3FEqConAccountOpenConfirmResponse l_response = 
            (WEB3FEqConAccountOpenConfirmResponse)l_request.createResponse();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (submit申込)<BR>
     * 申込の完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座開設）submit申込」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConAccountOpenCompleteResponse
     * @@roseuid 41E38B5E02BA
     */
    protected WEB3FEqConAccountOpenCompleteResponse submitApply(
        WEB3FEqConAccountOpenCompleteRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitApply(WEB3FEqConAccountOpenCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.2 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        //(2)validate
        l_orderManager.validateOrder(l_subAccount);
        
        //1.3 validate外株口座開設可能(SubAccount)
        //[引数] 
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateFeqConAccOpenPossible(l_subAccount);

        //validate居住者(SubAccount)
        //補助口座：　@get補助口座()の戻り値
        l_orderManager.validateResident(l_subAccount);

        //1.4 validate外株口座開設質問(外株口座開設質問情報[])
        //[引数] 
        //質問情報一覧： リクエストデータ.質問情報一覧
        
        //get 外株振替連携データ制御サービスImpl 
        WEB3FEqConTransferDataControlService l_conTransferDataControlService =
            (WEB3FEqConTransferDataControlService)Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        l_conTransferDataControlService.validateFeqAccountOpenQuestion(
            l_request.questionInfoList);
        
        //1.5 get代理入力者()
        Trader l_trader = this.getTrader();
        
        //1.6 validate取引パスワード(Trader, SubAccount, String)
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //補助口座： get補助口座()の戻り値 
        //パスワード： リクエストデータ.暗証番号
        //(1)
        WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
        //(2)
        OrderValidationResult result = l_orderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if(result.getProcessingResult().getErrorInfo()!=null){
                throw new WEB3BusinessLayerException(
                      result.getProcessingResult().getErrorInfo(),
                      this.getClass().getName() + "." + l_strMethodName,
                      result.getProcessingResult().getErrorInfo().getErrorMessage());
        }
        //1.7 get新規識別コード(String, String, ProductTypeEnum)
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //銘柄タイプ： 5（現金）
        //(1)get 注文識別コード採番サービス 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
        log.debug("get新規識別コード() = " + l_strNewNumber);
        
        //1.8 insertUWG口座開設状況(顧客, String)
        //[引数] 
        //顧客： 補助口座.getMainAccount()の戻り値 
        //パスワード： リクエストデータ.外株用暗証番号
        //識別コード： get新規識別コード()の戻り値
        l_conTransferDataControlService.insertUwgAccountOpenStatus(
            l_subAccount.getMainAccount(),
            l_request.feqPassword1,
            l_strNewNumber);
        
        //1.9 insert質問回答(String, String, String, 外株口座開設質問情報[])
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード： 補助口座.get取引店().getBranchCode() 
        //識別コード： get新規識別コード()の戻り値 
        //質問情報一覧： リクエストデータ.質問情報一覧
        l_conTransferDataControlService.insertQuestionAnswer(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_strNewNumber,
            l_request.questionInfoList);
        
        //1.10 getUWG口座開設状況(String, String, String)
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード： 補助口座.get取引店().getBranchCode() 
        //識別コード： get新規識別コード()の戻り値
        UwgAccountOpenStatusParams l_uwgAccountOpenStatusParams = 
            l_conTransferDataControlService.getUwgAccountOpenStatus(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_strNewNumber);
        
        //1.11 createResponse()
        WEB3FEqConAccountOpenCompleteResponse l_response =
            (WEB3FEqConAccountOpenCompleteResponse)l_request.createResponse();
        
        //1.12 レスポンスデータにプロパティをセットする。
        //受付番号： get新規識別コード()の戻り値
        l_response.receptionId = l_strNewNumber;
        //受付日時： UWG口座開設状況.作成日付
        l_response.receptionDate = l_uwgAccountOpenStatusParams.getCreatedTimestamp();
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
