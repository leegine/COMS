head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替サービスImpl(WEB3FXTransToFXServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/21 屈陽 (中訊) 新規作成   
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル460、484
                 : 2006/04/27 周捷(中訊) 仕様変更 NO.536、NO.541、NO.544、NO.548
                 : 2006/06/05 鈴木（SCS） 仕様変更 No.590・DB更新仕様 091
                 : 2006/07/12 丁昭奎 (中訊) 仕様変更・モデルNo.600
                 : 2006/08/01 鈴木(SCS)　@モデルNo.609対応
                 : 2006/08/23 鈴木(SCS)　@モデルNo.630、NO.631対応
                 : 2006/08/28 鈴木(SCS)　@モデルNo.632・DB更新仕様 108対応
                 : 2006/10/12 何文敏 (中訊) 仕様変更・モデルNo.666
                 : 2006/11/08 鈴木(SCS)　@モデルNo.685対応
Revesion History : 2007/07/12 孫洪江(中訊) 仕様変更モデルNo.730
Revesion History : 2008/04/08 武波 (中訊) 仕様変更・モデルNo.834,No.835,No.836,No.844
Revesion History : 2008/04/23 王志葵 (中訊) 仕様変更モデル.845
Revesion History : 2008/04/28 王志葵 (中訊) 仕様変更モデル.847
Revesion History : 2008/05/20 柴双紅 (中訊) 仕様変更・モデルNo.859,No.869
                 : 2008/05/23 三島 (SCS) 
Revesion History : 2008/06/18 柴双紅 (中訊) 仕様変更・モデルNo901
Revesion History : 2008/09/09 王志葵 (中訊) 仕様変更・モデルNo974,975,984,985,1001
Revesion History : 2008/10/02 竹村 (SCS) 仕様変更・モデル1047
Revesion History : 2008/10/07 馮海濤 (中訊) 仕様変更・モデルNo997、1042、1063、1064、1067
Revesion History : 2008/11/19 SCS大嶋 仕様変更モデル.1086
Revesion History : 2009/03/15 柴双紅 (中訊) 仕様変更・モデルNo1113、1145、1149
Revesion History : 2009/04/20 車進 (中訊) 仕様変更・モデル1163
Revesion History : 2009/09/16 孟亞南 (中訊) 仕様変更・モデル1198 1216 1220 1234
Revesion History : 2009/10/29 張騰宇 (中訊) 仕様変更・モデル1249 1250 1251
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrder;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXTransConnection;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXConnCommonService;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.WEB3FXTransferOrderUpdateInterceptor;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioCashInOutAmountDivDef;
import webbroker3.aio.define.WEB3AioTransferDetailMessageDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTransToFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransToFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransToFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransToFXConfirmResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioSoapConnectDivDef;
import webbroker3.common.define.WEB3AioTransferDivDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExtConnectSystemCodeDef;
import webbroker3.common.define.WEB3FxDeliveryDateInsertCheckDef;
import webbroker3.common.define.WEB3FxSystemCodeDef;
import webbroker3.common.define.WEB3FxTransferMasterRemarkCodeDef;
import webbroker3.common.define.WEB3GftErrorReasonCodeDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.common.define.WEB3GftSoapResultCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3SoapConnectDivDef;
import webbroker3.common.define.WEB3SoapResultCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcDao;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FXへの振替サービスImpl) <BR>
 * FXへの振替サービス実装クラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXServiceImpl extends WEB3ClientRequestService
    implements WEB3FXTransToFXService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3FXTransToFXServiceImpl()
    {
    }

    /**
     * FXへの振替サービス処理を行う。 <BR>
     * <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。 <BR>
     * ・validate注文() <BR>
     * ・start注文() <BR>
     * ・submit注文() <BR>
     * ・submit注文()（SOAP接続）
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@roseuid 41C7B2080071
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
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
        
        //リクエストデータの型により、以下のいずれかのメソッドをコールする。 
        // validate注文() 
        // start注文() 
        // submit注文() 
        // submit注文()（SOAP接続）
        if (l_request instanceof WEB3FXTransToFXConfirmRequest)
        {
            l_response = 
                validateOrder((WEB3FXTransToFXConfirmRequest)l_request);   
        }
        else if (l_request instanceof WEB3FXTransToFXCompleteSoapRequest)
        {
            l_response =
                submitOrder((WEB3FXTransToFXCompleteSoapRequest) l_request);
        }
        else if (l_request instanceof WEB3FXTransToFXAskingRequest)
        {
            l_response =
                startOrder((WEB3FXTransToFXAskingRequest)l_request);
        }
        else if (l_request instanceof WEB3FXTransToFXCompleteRequest)
        {
            l_response =
                submitOrder((WEB3FXTransToFXCompleteRequest)l_request);
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
     * (validate注文) <BR>
     * 振替注文の発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXへの振替）validate注文」参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FXへの振替 」<BR>
     * （FXへの振替）validate注文 )<BR>
     * 1.13 戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00761 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXConfirmResponse
     * @@roseuid 41C7B2080090
     */
    protected WEB3FXTransToFXConfirmResponse validateOrder(
        WEB3FXTransToFXConfirmRequest l_request)
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "validateOrder(WEB3FXTransToFXConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate注文(SubAccount)
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
        
        //1.4 get会社別FXシステム条件(String, String, String)
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード
        //(1)produce FXデータ制御サービス 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        CompFxConditionParams l_params;
        try
        {
            l_params =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get会社別FXシステム条件", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX振替条件マスタ(long, String)
        //【引数】
        //FXシステム条件ID　@= 会社別FXシステム条件Params.FXシステム条件ID
        //振替区分 = 1：出金
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_params.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        //1.5 validate外部システム受付可能(String)
        //[引数の設定] 
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        l_orderManager.validateOtherSystemAcceptPossible(l_params.getFxSystemCode());

        //validate振替可能(SubAccount, 会社別FXシステム条件Params)
        //[引数の設定]
        //補助口座：　@get補助口座()の戻り値
        //会社別FXシステム条件Params：　@　@get会社別ＦＸシステム条件()の戻り値
        l_dataControlService.validateChangePoss(
            l_subAccount,
            l_params);

        //1.7 get発注日()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get受渡日(Date, 補助口座, String)
        //[引数の設定]
        //発注日：　@get発注日()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //受渡日設定区分：　@getFX振替条件マスタ().受渡日設定区分
        Date l_datDeliveryDate = l_dataControlService.getDeliveryDate(
            l_datOrderBizDate,
            l_subAccount,
            l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9. validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ：　@15（為替保証金振替）
        l_orderManager.validateTransferPossibleCount(
            l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);

        //1.10. 商品ID（銘柄ID）を取得する。 
        //[引数] 
        //証券会社： 補助口座.get取引店().getInstitution()の戻り値 
        long l_lngProductId = 
            l_orderManager.getProductId(l_subAccount.getInstitution());        
        
        //1.11. 入出金注文内容インスタンスを生成する。 
        //[引数] 
        //代理入力者：　@null 
        //注文種別：FX振替条件マスタ.注文種別
        //振替タイプ：　@2（出金） 
        //商品ID：　@get商品ID()の戻り値 
        //金額：　@リクエストデータ.振替金額 
        //記述：　@null 
        //振替予定日：　@get受渡日()の戻り値
        //決済機@関ID：　@null 
        //注文ID：　@null
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null, 
                l_fxTransferMasterParams.getOrderType(),
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.transferAmount), 
                null, 
                l_datDeliveryDate, 
                null, 
                null);
        
        //1.12. FX振替注文更新インタセプタを生成する。 
        //[引数の設定] 
        //入出金注文内容：　@入出金注文内容  
        WEB3FXTransferOrderUpdateInterceptor l_orderUpdateInterceptor =
            new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.13.(*1)プロパティセット 
        //発注日：get発注日()の戻り値
        l_orderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);

        //受渡日：get受渡日()の戻り値
        l_orderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
        
        //注文内容インタセプタの配列
        WEB3FXTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_orderUpdateInterceptor};
            
        //注文内容の配列
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec}; 
        
        //1.14. validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 
        //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //注文内容インタセプタ： FX振替注文更新インタセプタを要素とした配列 
        //注文内容： 入出金注文内容を要素とした配列 
        //注文種別： FX振替条件マスタ.注文種別
        //余力更新フラグ： false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
        	l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                l_fxTransferMasterParams.getOrderType(),
                false);
        
        //取引余力結果.get取引可能額()の戻り値
        double l_dblTradingPower = l_powerResult.getTradingPower();
        
		//戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
		if (l_powerResult.isResultFlg() == false)
		{
			log.debug("振替金額が可能額を超えています"); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00761,
				this.getClass().getName() + "." + l_strMethodName,
				"振替金額が可能額を超えています");     
		}
        
        //1.15. createResponse()
        WEB3FXTransToFXConfirmResponse l_response = 
            (WEB3FXTransToFXConfirmResponse)l_request.createResponse();
            
        //1.16. (*2)レスポンスデータにプロパティをセットする。
        //振替可能額 = 取引余力結果.get取引可能額()の戻り値
        //受渡日：get受渡日()の戻り値
        l_response.transferableAmt = 
            WEB3StringTypeUtility.formatNumber(l_dblTradingPower);
        l_response.deliveryDate = l_datDeliveryDate;
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (start注文) <BR>
     * 振替注文の依頼・登録処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXへの振替）start注文」参照。 <BR>
     * ======================================================== <BR>
     * 1.16 戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00761 <BR>
     * <BR>
     * ==========================================================
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXAskingResponse
     * @@roseuid 41C7B20800AF
     */
    protected WEB3FXTransToFXAskingResponse startOrder(
        WEB3FXTransToFXAskingRequest l_request)
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "startOrder(WEB3FXTransToFXAskingRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 get補助口座(SubAccountTypeEnum)
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount =
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate注文(SubAccount)
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
        
        //1.4 get会社別FXシステム条件(String, String, String)
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店.getBranchCode()
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        //(1)produce FXデータ制御サービス 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        CompFxConditionParams l_comConditionParams;
        try
        {
            l_comConditionParams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get会社別FXシステム条件", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        //getFX振替条件マスタ(long, String)
        //【引数】
        //FXシステム条件ID　@= 会社別FXシステム条件Params.FXシステム条件ID
        //振替区分 = 1：出金
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_comConditionParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        //1.5 validate外部システム受付可能(String)
        //[引数の設定] 
        //システムコード：　@会社別FXシステム条件Params.FXシステムコード
        l_orderManager.validateOtherSystemAcceptPossible(l_comConditionParams.getFxSystemCode());

        //validate振替可能(SubAccount, 会社別FXシステム条件Params)
        //[引数の設定]
        //補助口座：　@get補助口座()の戻り値
        //会社別FXシステム条件Params：　@　@get会社別ＦＸシステム条件()の戻り値
        l_dataControlService.validateChangePoss(
            l_subAccount,
            l_comConditionParams);

        //1.7 get発注日()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //　@発注日の翌営業日
        WEB3GentradeBizDate l_gentradeBizDate = 
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));            
        Date l_datNextDate = l_gentradeBizDate.roll(1); 

        //get受渡日(Date, 補助口座, String)
        //[引数]
        //発注日：　@get発注日()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //受渡日設定区分：　@getFX振替条件マスタ().受渡日設定区分
        Date l_datDeliveryDate =
            l_dataControlService.getDeliveryDate(
                l_datOrderBizDate,
                l_subAccount,
                l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.9 validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ：　@15（為替保証金振替）
        l_orderManager.validateTransferPossibleCount(l_subAccount, l_datOrderBizDate, OrderCategEnum.FX);
        
        //1.10 get代理入力者()
        Trader l_trader = this.getTrader();
        
        //1.11 validate取引パスワード(Trader, SubAccount, String)
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //補助口座： get補助口座()の戻り値 
        //パスワード： リクエストデータ.暗証番号
        //(1)
        WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
        //(2)
        l_orderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        
        //1.12 get新規識別コード(String, String, ProductTypeEnum)
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //銘柄タイプ： 5（現金）
        //(1)produce 注文識別コード採番サービス 
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        //(2)
        String l_strNewNumber1 = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                ProductTypeEnum.CASH);
                
        //1.13 get商品ID(Institution)
        //[引数] 
        //証券会社： 補助口座.get取引店().getInstitution()の戻り値
        long l_lngProductId = 
            l_orderManager.getProductId(l_subAccount.getInstitution());
        
        WEB3GentradeBranch l_genBranch = 
            ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();        

        //注文種別： FX振替条件マスタ.注文種別
        OrderTypeEnum l_orderTypeEnum = l_fxTransferMasterParams.getOrderType();

        //入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long,
        // double, String, Date, String, Long, String, String)
        //[引数の設定] 
        // 代理入力者：　@get代理入力者()の戻り値 
        // 注文種別： FX振替条件マスタ.注文種別
        // 振替タイプ：　@2（出金） 
        // 商品ID：　@get商品ID()の戻り値 
        // 金額：　@リクエストデータ.振替金額 
        // 記述： null
        // 振替予定日：　@get受渡日()の戻り値 
        // 決済機@関ID：　@null 
        // 注文ID：　@null
        // 摘要コード： FX振替条件マスタ.摘要コード
        // 摘要名：　@FX振替条件マスタ.摘要名
        WEB3AioNewOrderSpec l_aioWEB3AioNewOrderSpec1 =
            new WEB3AioNewOrderSpec(
                l_trader,
                l_orderTypeEnum,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.transferAmount),
                null,
                l_datDeliveryDate,
                null,
                null,
                l_fxTransferMasterParams.getRemarkCode(),
                l_fxTransferMasterParams.getRemarkName());

        //1.15 FX振替注文更新インタセプタ(入出金注文内容)
        //[引数の設定] 
        //入出金注文内容：入出金注文内容（振替注文①@）
        WEB3FXTransferOrderUpdateInterceptor l_interceptor1 =
            new WEB3FXTransferOrderUpdateInterceptor(l_aioWEB3AioNewOrderSpec1);
            
        //1.16 (*)以下のとおりにプロパティをセットする。
        //(1)発注日：get発注日()の戻り値
        l_interceptor1.setOrderBizDate(l_datOrderBizDate);
        
        //(2)受渡日:get発注日()の戻り値
        l_interceptor1.setDeliveryDate(l_datDeliveryDate);

        //(3)識別コード：get新規識別コード()の戻り値（識別コード①@）
        l_interceptor1.setOrderRequestNumber(l_strNewNumber1);
        
        //(4)MQステータス：0(未送信)
        l_interceptor1.setMQStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
        
        //（補助口座.getMainAccount().is信用口座開設（弁済区分（＝”指定なし”）の戻り値＝true）  
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        WEB3FXTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_interceptor1};
        
        //注文内容の配列
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioWEB3AioNewOrderSpec1}; 
    
        //1.17 validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
        // 注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean) 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //注文内容インタセプタ： FX振替注文更新インタセプタを要素とした配列 
        //注文内容： 入出金注文内容を要素とした配列 
        //注文種別： FX振替条件マスタ.注文種別
        //余力更新フラグ： false 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tPTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                l_orderTypeEnum, 
                false);
        
		//戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
		if (l_powerResult.isResultFlg() == false)
		{
			log.debug("振替金額が可能額を超えています"); 
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00761,
				this.getClass().getName() + "." + l_strMethodName,
				"振替金額が可能額を超えています");     
		}

        double l_dblTransferAmount = 0;
        SubAccount l_subAccount2 = null;
        //信用口座開設済の場合、実施。
        //  補助口座.getMainAccount().is信用口座開設（弁済区分（=”指定なし”）の戻り値 == true
        //  and get発注日()の戻り値 = get受渡日()の戻り値
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)
            && WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0)
        {
            //1.19.1 get補助口座(SubAccountTypeEnum)
            //[引数] 
            //補助口座タイプ： 2（株式信用取引口座（保証金））
            l_subAccount2 = 
                this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);  
                
            //1.19.2 get預り金への振替額(補助口座 : 補助口座, 必要現金 : double, 受渡日 : Date)
            //[引数] 
            //補助口座： get補助口座()の戻り値（保証金口座） 
            //当日必要現金： リクエストデータ.振替金額 
            //受渡日：　@get発注日()の戻り値
            l_dblTransferAmount =
                l_tPTradingPowerService.getTransferAmountToEquitySubAcount(
                    (WEB3GentradeSubAccount)l_subAccount2,
                    Double.parseDouble(l_request.transferAmount),
                    l_datOrderBizDate); 
        }
        
        //1.20 createNewOrderId()
        long l_lngNewOrderId1 = l_orderManager.createNewOrderId();    
        
        //1.21 submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum, 
        //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //銘柄タイプ： 5（現金） 
        //注文種別： FX振替条件マスタ.注文種別
        //注文内容： 入出金注文内容（振替注文①@） 
        //インタセプタ： FX振替注文更新インタセプタオブジェクト（振替注文①@） 
        //注文ID： 注文ID：　@createNewOrderId()の戻り値（注文ID①@） 
        //パスワード： リクエストデータ.暗証番号
        l_orderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            l_orderTypeEnum,
            l_aioWEB3AioNewOrderSpec1,
            l_interceptor1,
            l_lngNewOrderId1,
            l_request.password);
            
        String l_strNewNumber2 = null;
        
		//　@業務日付の取得（yyyyMMdd）
		String l_strBizDate = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd");
		Date l_datBizDate = WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd");
		
        //　@発注日をyyyyMMdd型に変更
        String l_strOrderBizdat1 = WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
		Date l_datOrderBizDate1 = WEB3DateUtility.getDate(l_strOrderBizdat1, "yyyyMMdd");
		
		//1.22 is保証金振替(補助口座 l_subAccount, Date 業務日付, Date 注文単位.発注日)
		boolean l_blnIsDepositTransfer = l_orderManager.isDepositTransfer(l_subAccount, l_datBizDate, l_datOrderBizDate1);

        //信用保証金から預り金への振替要の場合
        // 補助口座.getMainAccount().is信用口座開設（弁済区分（=”指定なし”）の戻り値 == true
        // and get発注日()の戻り値 = get受渡日()の戻り値
        // and get預り金への振替額()の戻り値 > 0
        // and is保証金振替(）の戻り値 == true
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)
            && WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0
            && l_dblTransferAmount > 0
            && l_blnIsDepositTransfer)
        {
            //1.23.1 シーケンス図
            //（FXへの振替）信用振替注文登録
            
            //1.23.1.1.1
            //get新規識別コード(String, String, ProductTypeEnum)(信用振替用識別コード)
            //[引数] 
            //証券会社コード： 補助口座.証券会社コード 
            //部店コード：　@補助口座.get取引店().getBranchCode() 
            //銘柄タイプ： 5（現金）
            l_strNewNumber2 = 
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount2.getInstitution().getInstitutionCode(),
                    l_subAccount2.getMainAccount().getBranch().getBranchCode(),
                    ProductTypeEnum.CASH);
                
            //1.23.1.1.2 入出金注文内容
            //[引数] 
            //代理入力者： get代理入力者()の戻り値 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //振替タイプ： 2（出金） 
            //商品ID： get商品ID()の戻り値 
            //金額： get預り金への振替額()の戻り値 
            //記述： null 
            //振替予定日： get発注日()の戻り値 
            //決済期間ID： null 
            //注文ID： null
            WEB3AioNewOrderSpec l_aioWEB3AioNewOrderSpec2 = 
                new WEB3AioNewOrderSpec(
                    l_trader,
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    AssetTransferTypeEnum.CASH_OUT,
                    l_orderManager.getProductId(l_subAccount.getInstitution()),
                    l_dblTransferAmount,
                    null,
                    l_datOrderBizDate,
                    null,
                    null);
                    
            //1.23.1.1.3 振替注文更新インタセプタ(入出金注文内容)
            //[引数の設定] 
            //入出金注文内容：　@入出金注文内容（振替注文②）
            WEB3AioTransferOrderUpdateInterceptor l_interceptor2 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioWEB3AioNewOrderSpec2);
                
            //1.23.1.1.4 以下のとおりにプロパティをセットする。
            //(1)発注日：　@get発注日()の戻り値
            l_interceptor2.setBizDate(l_datOrderBizDate);
            
            //(2)受渡日：　@get発注日()の戻り値
            l_interceptor2.setDeliveryDate(l_datOrderBizDate);
            
            //(3)識別コード：　@get新規識別コード()の戻り値（識別コード②）
            l_interceptor2.setOrderRequestNumber(l_strNewNumber2);
            
            //(4)MQステータス：　@0(未送信)
            l_interceptor2.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //1.23.1.1.5 入出金注文内容
            //[引数] 
            //代理入力者： get代理入力者()の戻り値 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //振替タイプ： 1（入金） 
            //商品ID： get商品ID()の戻り値 
            //金額：  get預り金への振替額()の戻り値 
            //記述： null 
            //振替予定日： get発注日()の戻り値 
            //決済期間ID： null 
            //注文ID： null
            WEB3AioNewOrderSpec l_aioWEB3AioNewOrderSpec3 = 
                new WEB3AioNewOrderSpec(
                    l_trader,
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    AssetTransferTypeEnum.CASH_IN,
                    l_orderManager.getProductId(l_subAccount.getInstitution()),
                    l_dblTransferAmount,
                    null,
                    l_datOrderBizDate,
                    null,
                    null);
                    
            //1.23.1.1.6 振替注文更新インタセプタ(入出金注文内容)
            //[引数の設定] 
            //入出金注文内容：　@入出金注文内容（振替注文③）
            WEB3AioTransferOrderUpdateInterceptor l_interceptor3 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioWEB3AioNewOrderSpec3);
                
            //1.23.1.1.7 以下のとおりにプロパティをセットする。
            //(1)発注日：　@get発注日()の戻り値
            l_interceptor3.setBizDate(l_datOrderBizDate);
            
            //(2)受渡日：　@get発注日()の戻り値
            l_interceptor3.setDeliveryDate(l_datOrderBizDate);
            
            //(3)識別コード：　@get新規識別コード()の戻り値（識別コード②）
            l_interceptor3.setOrderRequestNumber(l_strNewNumber2);
            
            //(4)MQステータス：　@0(未送信)
            l_interceptor3.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //1.23.1.1.8 createNewOrderId()
            long l_lngNewOrderId2 =  l_orderManager.createNewOrderId();
            
            //get保証金口座口座(SubAccountTypeEnum)
            //[引数] 
            //補助口座タイプ： 2:株式信用取引口座（保証金）
            SubAccount l_marginSubAccount =
                this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            //1.23.1.1.9 submit振替注文
            //[引数] 
            //補助口座： get補助口座()の戻り値（保証金口座） 
            //銘柄タイプ： 5（現金） 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //注文内容： 入出金注文内容（振替注文②） 
            //インタセプタ： 振替注文更新インタセプタ（振替注文②） 
            //注文ID： createNewOrderId()の戻り値（注文ID②） 
            //パスワード： リクエストデータ.暗証番号
            l_orderManager.submitTransferOrder(
                l_marginSubAccount,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioWEB3AioNewOrderSpec2,
                l_interceptor2,
                l_lngNewOrderId2,
                l_request.password);
                
            //1.23.1.1.10 createNewOrderId()
            long l_lngNewOrderId3 =  l_orderManager.createNewOrderId();
            
            //1.23.1.1.11 submit振替注文
            //[引数] 
            //補助口座： get補助口座()の戻り値（預り金口座） 
            //銘柄タイプ： 5（現金） 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //注文内容： 入出金注文内容（振替注文③） 
            //インタセプタ： 振替注文更新インタセプタ（振替注文③） 
            //注文ID： createNewOrderId()の戻り値（注文ID③） 
            //パスワード： リクエストデータ.暗証番号                                            
            l_orderManager.submitTransferOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                l_aioWEB3AioNewOrderSpec3,
                l_interceptor3,
                l_lngNewOrderId3,
                l_request.password);                                            
        }
        
        //1.24 余力再計算(補助口座 : 補助口座)
        //[引数の設定] 
        //補助口座：　@get補助口座()(*)の戻り値 
        //預かり金の補助口座タイプを指定して取得した補助口座
        l_tPTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        
        //1.25 getFX顧客(String, String, String, String)
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //FXシステムコード：　@会社別FXシステム条件.FXシステムコード 
        //顧客コード：　@補助口座.getMainAccount().getAccountCode()
        FxAccountParams l_fxAccountParams;
        try
        {
            l_fxAccountParams =
                l_dataControlService.getFXAccount(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_comConditionParams.getFxSystemCode(),
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in FX顧客Paramsを取得する。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.26. GFT依頼電文明細()
        //GFT依頼電文明細オブジェクトを生成する
        WEB3FXGftAskingTelegramUnit l_gftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit(); 

        //1.27. get処理区分(String)
        //[get処理設区分()に指定する引数]  
        //FXシステムコード：　@リクエストデータ．FXシステムコード
        String l_strOperationDiv = getOperationDiv( l_request.fxSystemCode);

        //get変換FXログインID(long, String, String, long)
        //[引数]
        // 証券会社ID：　@補助口座.getInstitution.getInstitutionId()
        // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード
        // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字
        // FXログインID：　@FX顧客Params.FXログインID
        String l_strChangedFXLoginID = l_dataControlService.getChangedFXLoginID(
            l_subAccount.getInstitution().getInstitutionId(),
            l_comConditionParams.getFxSystemCode(),
            l_comConditionParams.getFxHeadOfLoginId(),
            l_fxAccountParams.getFxLoginId());

        //1.29. (*)プロパティセット
        //(*)GFT依頼電文明細に必要なプロパティをセットする（下記以外のプロパティは設定しない）
        //(1)DIR→GFT送信日時：現在時刻（システムタイムスタンプ）
        Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());
        l_gftAskingTelegramUnit.dirSendTime = 
            WEB3DateUtility.formatDate(l_tsSystemTimestamp, "yyyyMMddHHmmss"); 
        
        //(2)処理区分：get処理区分()
        l_gftAskingTelegramUnit.gftOperationDiv = l_strOperationDiv;     
        
        //(3)為替保証金口座番号：リクエストデータ.FX口座情報.口座番号
        l_gftAskingTelegramUnit.fxAccountCode = 
            l_request.fxAccInformationUnit.fxAccountCode;
        
        //(4)初期ログインID：get変換FXログインIDの戻り値
        l_gftAskingTelegramUnit.fxFirstLoginId = l_strChangedFXLoginID;
        
        //(5)担当区分：会社別FXシステム条件Params.担当区分
        l_gftAskingTelegramUnit.groupName = l_comConditionParams.getGroupName();
        
        //(6)入出金額：リクエストデータ.振替金額
        l_gftAskingTelegramUnit.cashinoutAmt = l_request.transferAmount;
        
        //(7)WOLFセッションキー：リクエストデータ.WOLFセッションキー
        l_gftAskingTelegramUnit.wolfSession = l_request.wolfSession;
        
        //(8)アプリケーションID：リクエストデータ.アプリケーションID
        l_gftAskingTelegramUnit.wolfAid = l_request.wolfAid;
        
        //(9)再生成サービスID：リクエストデータ.再生成サービスID
        l_gftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
        
        //(10)SSID：リクエストデータ.SSID
        l_gftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
        
        //(11)会社コード：補助口座.証券会社コード
        l_gftAskingTelegramUnit.institutionCode = 
            l_subAccount.getInstitution().getInstitutionCode();
        
        //(12)部店コード：補助口座.get取引店().getBranchCode()
        l_gftAskingTelegramUnit.branchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //(13)顧客コード：補助口座.getMainAccount().getAccountCode()
        l_gftAskingTelegramUnit.accountCode = 
            l_subAccount.getMainAccount().getAccountCode();
        
        //(14)識別コード：get新規識別コード()の戻り値（識別コード①@）
        l_gftAskingTelegramUnit.requestNumber = l_strNewNumber1;
        
        //(15)受渡日：get受渡日()の戻り値
    	l_gftAskingTelegramUnit.deliveryDate =
            WEB3DateUtility.formatDate(
                l_datDeliveryDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //1.30. createGFT電文ハッシュ値(GFT依頼電文明細)
        //[引数の設定] 
        //GFT依頼電文明細：プロパティセットを行ったGFT依頼電文明細
        //(1)produce FX電文処理サービス
        WEB3FXTelegramProcessService l_telegramProcessService =
            (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
        //(2)    
        String l_strHashValue = 
            l_telegramProcessService.createGFTTelegramHashValue(l_gftAskingTelegramUnit);
        //(3)
        //createGFT電文ハッシュ値()の戻り値のハッシュ値を
        //GFT依頼電文明細.ハッシュ値にセットする。
        l_gftAskingTelegramUnit.hashValue =  l_strHashValue;
            
        //1.31. insertGFT電文保存(GFT依頼電文明細)
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細           
        l_dataControlService.insertGFTMessage(l_gftAskingTelegramUnit);

        //insertGFT振替状況(GFT依頼電文明細, String, String, String,
        // 会社別FXシステム条件Params, String)
        //[引数の設定] 
        //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細 
        //コース区分：　@リクエストデータ.FX口座情報.コース区分 
        //受渡予定日：　@get受渡日()の戻り値 
        //信用振替用識別コード：　@(*) 
        //会社別FXシステム条件Params： 会社別FXシステム条件Params
        //入出金一覧取引区分:　@　@FX振替条件マスタParams.入出金一覧取引区分
        //(*)信用口座開設済 && 保証金から預かり金への振替が必要な場合、
        //採番した識別コード②をセット。以外、nullをセット。
        boolean l_blnMarginTransferAccountOpenCheck = false;
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT)
            && WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datDeliveryDate) == 0
            && l_dblTransferAmount > 0
            && l_blnIsDepositTransfer)
        {
            l_blnMarginTransferAccountOpenCheck = true;
        }

		if (l_blnMarginTransferAccountOpenCheck)
        {
            l_dataControlService.insertGFTTransferStatus(
                l_gftAskingTelegramUnit,
                l_request.fxAccInformationUnit.fxCourseDiv,
                WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"),
                l_strNewNumber2,
                l_comConditionParams,
                l_fxTransferMasterParams.getIoListTradeDiv());
        }
        else
        {
            l_dataControlService.insertGFTTransferStatus(
                l_gftAskingTelegramUnit,
                l_request.fxAccInformationUnit.fxCourseDiv,
                WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"),
                null,
                l_comConditionParams,
                l_fxTransferMasterParams.getIoListTradeDiv());
        }
        
        //1.33. createResponse()
        WEB3FXTransToFXAskingResponse l_response = 
            (WEB3FXTransToFXAskingResponse)l_request.createResponse();
            
        //1.34. レスポンスデータにプロパティをセットする。
        //URL： 会社別FXシステム条件Params.URL
        l_response.fxUrl = l_comConditionParams.getUrl();
        
        //GFT依頼電文明細：　@(*上記で編集を行ったGFT依頼電文明細)
        l_response.fxGftAskingTelegramUnit = l_gftAskingTelegramUnit;
        
        //注文ID：createNewOrderId()の戻り値（注文ID①@）
        l_response.orderId = String.valueOf(l_lngNewOrderId1);
        
        //確認時発注日：　@get発注日()の戻り値    
        l_response.checkDate = l_datOrderBizDate;        
                                     
        log.exiting(l_strMethodName);            
            
        return l_response;
    }

    /**
     * (submit注文) <BR>
     * 振替注文の完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（FXへの振替）submit注文」参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図(「(為替保証金サービスモデル) / FXへの振替 」<BR>
     * （FXから振替）submit注文) <BR>
     * : 1.3 getGFT振替状況(String, String, String) <BR>
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
     * : 1.7.5 (*)例外をthrow <BR>
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
     * 00000205（為替口座の純資産残高不足）:「純資産残高が不足しています」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02672 <BR>
     * <BR>
     * 00000206（為替口座の現金残高不足）：「現金残高が不足しています」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02673 <BR>
     * <BR>
     * 00000207（為替口座にマイナス通貨あり）：「為替口座における通貨現金残高で、マイナス通貨があリます。<BR>
     * 為替口座でコンバージヨン後に再度振替を依賴してださい。」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02674 <BR>
     * <BR>
     * 00000801(2重送信エラー)の場合 ：「2重送信エラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01799 <BR>
     * <BR>
     * 00000501（該当する証拠金口座が存在しない）の場合　@：[証拠金口座未開設エラー]<BR>
     * <BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02436<BR>
     * <BR>
     * 00000503（該当する為替保証金口座が存在しない）：「為替口座ヘの振替です。<BR>
     * この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた <BR>
     * お客樣しかご利用いただくことはどきすせん」<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02675 <BR>
     * <BR>
     * 以外の受付結果の場合 ：「その他のFXシステムエラー」 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01800 <BR>
     * <BR>
     * ========================================================== 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXCompleteResponse
     * @@roseuid 41C7B20800BF
     */
    protected WEB3FXTransToFXCompleteResponse submitOrder(
        WEB3FXTransToFXCompleteRequest l_request)
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "submitOrder(WEB3FXTransToFXCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate() 
        l_request.validate();
        
        //(1)FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        //1.2. get補助口座(補助口座タイプ : SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3. insertGFT電文保存(GFT依頼電文明細)
        //[引数の設定] 
        //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
        //(1)produce FXデータ制御サービス 
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
        //(2)
        l_dataControlService.insertGFTMessage(l_request.fxGftResultNoticeTelegramUnit);
        
        //1.4. getGFT振替状況(String, String, String)
        //[引数の設定] 
        //証券会社コード：　@リクエストデータ.GFT結果通知電文明細.会社コード 
        //部店コード：　@リクエストデータ.GFT結果通知電文明細.部店コード 
        //識別コード：　@リクエストデータ.GFT結果通知電文明細.識別コード
        GftTransferStatusParams l_transferStatusParams = 
            l_dataControlService.getGFTTransferStatus(
                l_request.fxGftResultNoticeTelegramUnit.institutionCode,
                l_request.fxGftResultNoticeTelegramUnit.branchCode,
                l_request.fxGftResultNoticeTelegramUnit.requestNumber);
            
        if (l_transferStatusParams == null)
        {
            log.debug("GFT振替状況取得エラー");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                "GFT振替状況取得エラー");
        }
        
        //produce FX電文処理サービス
        WEB3FXTelegramProcessService l_telegramProcessService =
            (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);
        
        //1.5. get会社別FXシステム条件(String, String, String)
        //会社別FXシステム条件Paramsを取得する。 
        //[引数の設定] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //FXシステムコード：　@リクエストデータ.FXシステムコード
        CompFxConditionParams l_compFxCondParams = null;
        try
        {
            l_compFxCondParams =
                l_dataControlService.getCompFxCondition(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_request.fxSystemCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in 会社別FXシステム条件Paramsを取得する。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }        

        //getFX振替条件マスタ(long, String)
        //【引数】
        //FXシステム条件ID　@= 会社別FXシステム条件Params.FXシステム条件ID
        //振替区分 = 1：出金
        FxTransferMasterParams l_fxTransferMasterParams =
            l_dataControlService.getFxTransferMasterParams(
                l_compFxCondParams.getFxSystemId(),
                WEB3AioTransferDivDef.CASHOUT);

        boolean l_blnTelegramSet = true;
        boolean l_blnPropSame = true;
        boolean l_blnMailSame = true;
        //会社別FXシステム条件Params.SOAP接続実施区分== 0：SOAP接続未実施 or 2：口座開設のみ実施の場合
        if (WEB3AioSoapConnectDivDef.SOAP_CONNECT_NOT_ENFORCEMENT.equals(l_compFxCondParams.getSoapConnectDiv())
            || WEB3AioSoapConnectDivDef.TRANSFER_ENFORCEMENT.equals(l_compFxCondParams.getSoapConnectDiv()))
        {
            //1.61. isGFT電文項目設定(GFT結果通知電文明細)
            //[引数の設定] 
            //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
            l_blnTelegramSet = 
                l_telegramProcessService.isGFTTelegramSet(l_request.fxGftResultNoticeTelegramUnit);
            
            //1.62. isGFT電文桁数属性一致(GFT結果通知電文明細)
            //[引数の設定] 
            //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
            l_blnPropSame = 
                l_telegramProcessService.isGFTTelegramLengthPropSame(l_request.fxGftResultNoticeTelegramUnit);

            //isGFT電文項目設定()=true and isGFT電文桁数属性一致()=true の場合、実施
            if (l_blnTelegramSet && l_blnPropSame)
            {
                //1.63. isGFT電文送受信項目一致(GFT結果通知電文明細)
                //[引数の設定] 
                //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
                l_blnMailSame = 
                    l_telegramProcessService.isGFTTelegramSendAndReceiveValueSame(l_request.fxGftResultNoticeTelegramUnit);
            }
        }

        //以下のいずれかに当てはまる場合、実施する。
        // ※会社別FXシステム条件Params.SOAP接続実施区分== 1：SOAP接続実施の場合、
        //   ③、④は条件に含めない
        //①@送受信区分が不正の場合（2重受信エラー）
        //（getGFT振替状況()の戻り値のGFT振替状況Params.送受信区分 ＝ 2(受信済)）
        //②送受信区分が不正の場合（その他エラー）
        //（getGFT振替状況()の戻り値のGFT振替状況Params.送受信区分 != (1(送信済)、2(受信済))
        //log for test
        log.debug("GFT振替状況Params.送受信区分 = " + l_transferStatusParams.getSendRcvDiv()); 
        
        //③結果通知電文内容の妥当性が不正の場合
        //（isGFT電文項目設定()＝fasle、または、isGFT電文桁数属性一致()＝false）
        //log for test
        log.debug("isGFT電文項目設定() = " + l_blnTelegramSet);
        log.debug("isGFT電文桁数属性一致() = " + l_blnPropSame);
        
        //④送受信電文の項目が不一致の場合
        //（isGFT電文送受信項目一致()＝false）
        //log for test
        log.debug("isGFT電文送受信項目一致() = " + l_blnMailSame);

        boolean l_blnIsResultNoticeTelegramPro = false;
        boolean l_blnIsTelegramSendAndReceiveSame = false;
        if (!WEB3AioSoapConnectDivDef.SOAP_CONNECT_ENFORCEMENT.equals(
            l_compFxCondParams.getSoapConnectDiv()))
        {
            if (!l_blnTelegramSet || !l_blnPropSame)
            {
                l_blnIsResultNoticeTelegramPro = true;
            }

            if (!l_blnMailSame)
            {
                l_blnIsTelegramSendAndReceiveSame = true;
            }
        }

        //⑤振替が正常に受付られなかった場合
        //(リクエストデータ.GFT結果通知電文明細.受付結果 != 00000000（正常））
        log.debug("リクエストデータ.GFT結果通知電文明細.受付結果 = " + l_request.fxGftResultNoticeTelegramUnit.resultCode);
        
        //produce 入出金・入出庫リクエスト送信サービス   
        WEB3AioMarketRequestSenderServiceImpl l_aioMarketSenderService = 
            (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();
                        
        //受渡日
        Date l_datDeliveryDate = null;
        
        if (WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
            !(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
            WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv())) ||
            l_blnIsResultNoticeTelegramPro ||
            l_blnIsTelegramSendAndReceiveSame ||
            !WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                l_request.fxGftResultNoticeTelegramUnit.resultCode))
        {
            //1.9.1. ①@以外の場合、実施する
            if (!WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()))
            {
                //1.9.1.1. updateGFT振替状況(GFT振替状況Params, GFT結果通知電文明細, String, String)
                //[引数の設定] 
                //GFT振替状況Params：　@getGFT振替状況()の戻り値 
                //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細 
                //更新後受渡予定日：　@null 
                //エラー理由コード：　@ 
                String l_strCode = null;
                //②の場合：0001（送受信区分チェックエラー） 
                if (!(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
                    WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv())))
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.SENDRCV_ERROR;
                }
                //③の場合：0002（パラメータ妥当性チェックエラー） 
                else if (!(l_blnTelegramSet && l_blnPropSame))
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.PARAM_VALIDITY_ERROR;
                }
                //④の場合：0003（パラメータ一致チェックエラー） 
                else if (!l_blnMailSame)
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.PARAM_MISMATCH_ERROR;
                }
                //⑤の場合：0004（受付結果コードチェックエラー）
                else 
                {
                    l_strCode = WEB3GftErrorReasonCodeDef.RESULT_CODE_ERROR;
                }
                
                l_dataControlService.updateGFTTransferStatus(
                    l_transferStatusParams,
                    l_request.fxGftResultNoticeTelegramUnit,
                    null,
                    l_strCode);

            }
            
            //1.9.2. 初回受信で(getGFT振替状況()の戻り値のGFT振替状況Params.送受信区分 ＝ 1(送信済)） かつ
            //振替が正常に受付られなかった(リクエストデータ.GFT結果通知電文明細.受付結果 != 00000000(正常))
            //場合、実施する。
            //(*)ただし、受付結果が2重送信エラー(00000801)、またはGFT接続エラー(00000990)の場合は、実施しない。
            if (WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) &&
            	!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
            		l_request.fxGftResultNoticeTelegramUnit.resultCode) &&
            	!(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(
            		l_request.fxGftResultNoticeTelegramUnit.resultCode)
                    || WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990.equals(
                        l_request.fxGftResultNoticeTelegramUnit.resultCode)))
            {
                //1.9.2.1. submit取消注文()
                //登録した振替注文の取消処理を行う。 
                //[引数の設定] 
                //証券会社コード：　@GFT振替状況Params.証券会社コード 
                //部店コード：　@GFT振替状況Params.部店コード 
                //顧客コード：　@GFT振替状況Params.顧客コード 
                //識別コード：　@GFT振替状況Params.識別コード 
                //信用振替用識別コード：　@GFT振替状況Params.信用振替用識別コード 
                l_dataControlService.submitCancelOrder(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    l_transferStatusParams.getMrgTrnOrderRequestNumber());
            }
            
            //1.9.3. 例外をthrowする。　@　@
            // ①@の場合：「2重受信エラー」
            if (WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()))
            {
                log.debug("2重受信エラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01972,
                    this.getClass().getName() + "." + l_strMethodName);    
            }
            // ②③④の場合：「その他のFXシステムエラー」
            if (!(WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv()) ||
                WEB3SendRcvDivDef.RECEIVE_COMPLETE.equals(l_transferStatusParams.getSendRcvDiv())) ||
                !l_blnTelegramSet ||
                !l_blnPropSame ||
                !l_blnMailSame)
            {
                log.debug("その他のFXシステムエラー");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                    this.getClass().getName() + "." + l_strMethodName);    
            }
            // ⑤の場合：GFT結果通知電文明細.受付結果に応じて以下の例外となる
            if (!WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000.equals(
                    l_request.fxGftResultNoticeTelegramUnit.resultCode))
            {
                // 00000105(ホスト処理時間外)の場合    ：「受付時間外エラー」
                if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105.equals(
                        l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("受付時間外エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01801,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                // 00000199(ホストシステムエラー)の場合  ：「通信エラー」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("通信エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01802,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                // 00000204(残高不足エラー)の場合 ：「残高不足エラー」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("残高不足エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                //00000205(為替口座の純資産残高不足)の場合    ：「純資産残高が不足しています」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000205.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("純資産残高不足しています。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02672,
                        this.getClass().getName() + "." + l_strMethodName,
                        "純資産残高不足しています。");
                }
                //  00000206(為替口座の現金残高不足)の場合    ：「現金残高が不足しています」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000206.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("現金残高が不足しています。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02673,
                        this.getClass().getName() + "." + l_strMethodName,
                        "現金残高が不足しています。");
                }
                //  00000207(為替口座にマイナス通貨あり)の場合    ：「為替口座における通貨現金残高で、マイナス通貨があリます。
                //  為替口座でコンバージヨン後に再度振替を依賴してださい。」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000207.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("為替口座における通貨現金残高で、マイナス通貨があリます" +
                            "為替口座でコンバージヨン後に再度振替を依賴してださい。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02674,
                        this.getClass().getName() + "." + l_strMethodName,
                        "為替口座における通貨現金残高で、マイナス通貨があリます" +
                        "為替口座でコンバージヨン後に再度振替を依賴してださい。");
                }
                // 00000801(2重送信エラー)の場合 ：「2重送信エラー」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("2重送信エラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01799,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
               //00000501(入出金時に該当する証拠金口座が存在しない)の場合 ：「口座未開設エラー」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                {
                    log.debug("該当する証拠金口座が存在しない");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02436,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
                //00000503（該当する為替保証金口座が存在しない）：「為替口座ヘの振替です。
                // この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた 
                // お客樣しかご利用いただくことはどきすせん」
                else if (WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000503.equals(
                            l_request.fxGftResultNoticeTelegramUnit.resultCode))
                 {
                    log.debug("為替口座ヘの振替です。" +
                            "この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた " +
                            "お客樣しかご利用いただくことはどきすせん。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02675,
                        this.getClass().getName() + "." + l_strMethodName,
                        "為替口座ヘの振替です。" +
                        "この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付くいただいた " +
                        "お客樣しかご利用いただくことはどきすせん。");
                 }
                // 以外の受付結果の場合 ：「その他のFXシステムエラー」
                else 
                {
                    log.debug("その他のFXシステムエラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01800,
                        this.getClass().getName() + "." + l_strMethodName);    
                }
            }
        }
        
        //1.10. get発注日()
        Date l_datOrderBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate();  

        //get受渡日(Date, 補助口座, String)
        //[引数の設定]
        //発注日：　@get発注日()の戻り値
        //補助口座：　@get補助口座()の戻り値
        //受渡日設定区分：　@getFX振替条件マスタ().受渡日設定区分
        l_datDeliveryDate = l_dataControlService.getDeliveryDate(
            l_datOrderBizDate,
            l_subAccount,
            l_fxTransferMasterParams.getDeliveryDateDiv());

        //1.12. get注文単位(String, String, String, String, SubAccountTypeEnum)
        //[引数の設定] 
        //証券会社コード：　@GFT振替状況Params.証券会社コード 
        //部店コード：　@GFT振替状況Params.部店コード 
        //顧客コード：　@GFT振替状況Params.顧客コード 
        //識別コード：　@GFT振替状況Params.識別コード 
        //補助口座タイプ： 1（株式取引口座（預り金））
        AioOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = 
                l_orderManager.getOrderUnit(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
        catch (NotFoundException l_ex)
        {
            log.error("get注文単位", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }           
         
        //1.13. get入出金額区分(GFT結果通知電文明細)
        //[引数] 
        //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細
        String l_strCashInOutAmountDiv = 
            l_dataControlService.getCashInOutAmountDiv(l_request.fxGftResultNoticeTelegramUnit);        
        
        //1.14. (*)get入出金額区分()の戻り値が1の場合
        if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT.equals(l_strCashInOutAmountDiv))
        {
            //1.14.1 update振替処理区分(String, String, String, String, long, String, String)
            //（預かり金からFX）振替注文の処理区分の更新を行う。 

            //[引数の設定] 
            //証券会社コード：　@GFT振替状況Params.証券会社コード 
            //部店コード：　@GFT振替状況Params.部店コード 
            //顧客コード：　@GFT振替状況Params.顧客コード 
            //識別コード：　@GFT振替状況Params.識別コード 
            //注文単位ID：　@get注文単位()の戻り値の注文単位.注文単位ID 
            //更新後発注日：　@(*) 
            //更新後受渡日：　@(*)

            //(*)リクエストデータ.確認時発注日 == get発注日()の戻り値の場合、null 
            //   それ以外の場合、 
            //      更新後発注日： get発注日()の戻り値をセット。 
            //      更新後受渡日： get受渡日()の戻り値をセット。 

            //   ※部店オブジェクトは、拡張アカウントマネージャ.get部店()にて取得。 
            //      [拡張アカウントマネージャ.get部店()にセットする引数] 
            //      証券会社コード：　@GFT振替状況Params.証券会社コード 
            //      部店コード：　@GFT振替状況Params.部店コード 
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)
            {
                l_aioMarketSenderService.updateTransferProcessDiv(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    l_orderUnit.getOrderUnitId(),
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"));
            }
            else 
            {
                l_aioMarketSenderService.updateTransferProcessDiv(
                    l_transferStatusParams.getInstitutionCode(),
                    l_transferStatusParams.getBranchCode(),
                    l_transferStatusParams.getAccountCode(),
                    l_transferStatusParams.getOrderRequestNumber(),
                    l_orderUnit.getOrderUnitId(),
                    null,
                    null);
            }
        }

        //get入出金額区分()の戻り値が2の場合
        else if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {        
            //update振替処理区分(String, String, String, String, long,
            // String, String, String, String)
            //（預かり金からFX）振替注文の処理区分の更新を行う。 
    
            //[引数の設定] 
            //証券会社コード：　@GFT振替状況Params.証券会社コード 
            //部店コード：　@GFT振替状況Params.部店コード 
            //顧客コード：　@GFT振替状況Params.顧客コード 
            //識別コード：　@GFT振替状況Params.識別コード 
            //注文単位ID：　@get注文単位()の戻り値の注文単位.注文単位ID 
            //更新後発注日：　@(*) 
            //更新後受渡日：　@(*) 
            //入出金額：　@リクエストデータ.GFT結果通知電文明細.入出金額2
            //摘要コード：　@71（株先証拠金：東証）
            //(*)リクエストデータ.確認時発注日 == get発注日()の戻り値の場合、null 
            //   それ以外の場合、 
            //      更新後発注日： get発注日()の戻り値をセット。 
            //      更新後受渡日： get受渡日()の戻り値をセット。 
    
            //   ※部店オブジェクトは、拡張アカウントマネージャ.get部店()にて取得。 
            //      [拡張アカウントマネージャ.get部店()にセットする引数] 
            //      証券会社コード：　@GFT振替状況Params.証券会社コード 
            //      部店コード：　@GFT振替状況Params.部店コード 
            String l_strUpdatedBizDate = null; 
            String l_strUpdatedDeliveryDate = null;
            if (WEB3DateUtility.compare(l_datOrderBizDate, l_request.checkDate) != 0)
            {
                l_strUpdatedBizDate = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strUpdatedDeliveryDate = 
                    WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd");
            }           
            
            l_aioMarketSenderService.updateTransferProcessDiv(
                l_transferStatusParams.getInstitutionCode(),
                l_transferStatusParams.getBranchCode(),
                l_transferStatusParams.getAccountCode(),
                l_transferStatusParams.getOrderRequestNumber(),
                l_orderUnit.getOrderUnitId(),
                l_strUpdatedBizDate, 
                l_strUpdatedDeliveryDate, 
                l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO);
        }

        //1.16. (*)get入出金額区分()の戻り値が3の場合
        if (WEB3AioCashInOutAmountDivDef.CASHIN_OUT_AMOUNT_AND_AMOUNT2.equals(l_strCashInOutAmountDiv))
        {        
            Trader l_trader = this.getTrader();

            //入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum,
            // long, double, String, Date, String, Long, String, String)
            //[引数の設定]  
            //代理入力者：　@get代理入力者()の戻り値  
            //注文種別： FX振替条件マスタ.注文種別
            //振替タイプ：　@　@2（出金） 
            //商品ID：　@get商品ID()の戻り値  
            //金額：　@リクエストデータ.GFT結果通知電文明細.入出金額2  
            //記述：　@null
            //振替予定日：　@get受渡日()の戻り値
            //決済機@関ID：　@null
            //注文ID：　@null
            //摘要コード：　@71（株先証拠金：東証）
            //摘要名：　@FX振替条件マスタ.摘要名
            WEB3AioNewOrderSpec l_aioNewOrderSpec =
                new WEB3AioNewOrderSpec(
                    l_trader,
                    l_fxTransferMasterParams.getOrderType(),
                    AssetTransferTypeEnum.CASH_OUT,
                    l_orderManager.getProductId(l_subAccount.getInstitution()),
                    Double.parseDouble(l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt2),
                    null,
                    l_datDeliveryDate,
                    null,
                    null,
                    WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO,
                    l_fxTransferMasterParams.getRemarkName());

            //1.16.2 FX振替注文更新インタセプタ(入出金注文内容)
            //[引数の設定]  
            //入出金注文内容：　@入出金注文内容オブジェクト
            WEB3FXTransferOrderUpdateInterceptor l_fxTransferOrderUpdateInterceptor =
                new WEB3FXTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
            
            //1.16.3 get新規識別コード(証券会社コード : String, 
            //部店コード : String, 銘柄タイプ : ProductTypeEnum)
            //[引数]  
            //証券会社コード： 補助口座.証券会社コード  
            //部店コード：　@補助口座.get取引店().getBranchCode()  
            //銘柄タイプ： 5（現金）
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class); 
            
            String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                ProductTypeEnum.CASH);
            
            //1.16.4 (*)プロパティセット
            //発注日：　@get発注日()の戻り値
            l_fxTransferOrderUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
            
            //受渡日：　@get受渡日()の戻り値 
            l_fxTransferOrderUpdateInterceptor.setDeliveryDate(l_datDeliveryDate);
            
            //識別コード：　@get新規識別コード()の戻り値
            l_fxTransferOrderUpdateInterceptor.setOrderRequestNumber(l_strNewNumber);
            
            //MQステータス：　@1(送信済み)
            l_fxTransferOrderUpdateInterceptor.setMQStatus(WEB3MqStatusDef.MAIL_SENDED);
            
            //1.16.5 createNewOrderId()
            long l_lngNewOrderId = l_orderManager.createNewOrderId();        

            //update振替処理区分(String, String, String, String, long,
            //  String, String, String, String)
            //[引数の設定]
            //証券会社コード：　@GFT振替状況Params.証券会社コード
            //部店コード：　@GFT振替状況Params.部店コード
            //顧客コード：　@GFT振替状況Params.顧客コード
            //識別コード：　@GFT振替状況Params.識別コード
            //注文単位ID：　@get注文単位()の戻り値の注文単位.注文単位ID
            //更新後発注日：　@(*)
            //更新後受渡日：　@(*)
            //入出金額：　@リクエストデータ.GFT結果通知電文明細.入出金額
            //摘要コード： 72（株先証拠金：大証）
            //(*)リクエストデータ.確認時発注日 == get発注日()の戻り値の場合、null
            //それ以外の場合、
            //更新後発注日： get発注日()の戻り値をセット。
            //更新後受渡日： get受渡日()の戻り値をセット。
            //※部店オブジェクトは、拡張アカウントマネージャ.get部店()にて取得。
            //[拡張アカウントマネージャ.get部店()にセットする引数]
            //証券会社コード：　@GFT振替状況Params.証券会社コード
            //部店コード：　@GFT振替状況Params.部店コード
            String l_strUpdatedBizDate = null;
            String l_strUpdatedDeliveryDate = null;
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)
            {
                l_strUpdatedBizDate =
                    WEB3DateUtility.formatDate(
                        l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_strUpdatedDeliveryDate =
                    WEB3DateUtility.formatDate(
                        l_datDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            }

            l_aioMarketSenderService.updateTransferProcessDiv(
                l_transferStatusParams.getInstitutionCode(),
                l_transferStatusParams.getBranchCode(),
                l_transferStatusParams.getAccountCode(),
                l_transferStatusParams.getOrderRequestNumber(),
                l_orderUnit.getOrderUnitId(),
                l_strUpdatedBizDate,
                l_strUpdatedDeliveryDate,
                l_request.fxGftResultNoticeTelegramUnit.cashinoutAmt,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_OSAKA);

            //1.16.6 submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec, 
            //AioOrderManagerPersistenceInterceptor, long, String)
            //[引数]  
            //補助口座： get補助口座()の戻り値  
            //銘柄タイプ： 5（現金）  
            //注文種別： FX振替条件マスタ.注文種別
            //注文内容： 入出金注文内容 
            //インタセプタ： FX振替注文更新インタセプタオブジェクト 
            //注文ID： 　@createNewOrderId()の戻り値 
            //パスワード： リクエストデータ.暗証番号
            l_orderManager.submitTransferOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_fxTransferMasterParams.getOrderType(),
                l_aioNewOrderSpec,
                l_fxTransferOrderUpdateInterceptor,
                l_lngNewOrderId,
                l_request.password);            
        }       
        
        //1.17 (*4)
        if (l_transferStatusParams.getMrgTrnOrderRequestNumber() != null)
        {
        
            //1.17.1 get振替注文単位(String, String, String, String)
            //[引数の設定] 
            //証券会社コード：　@GFT振替状況Params.証券会社コード 
            //部店コード：　@GFT振替状況Params.部店コード 
            //顧客コード：　@GFT振替状況Params.顧客コード 
            //識別コード：　@GFT振替状況Params.信用振替用識別コード
            AioOrderUnit[] l_orderUnits;
            try
            {
                l_orderUnits =
                    l_orderManager.getTransferOrderUnit(
                        l_transferStatusParams.getInstitutionCode(),
                        l_transferStatusParams.getBranchCode(),
                        l_transferStatusParams.getAccountCode(),
                        l_transferStatusParams.getMrgTrnOrderRequestNumber());
            }
            catch (NotFoundException l_ex)
            {
                log.error("Not found 振替注文単位", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
                      
            //1.17.2 取得した注文単位の要素毎にLoop処理
            int l_intSize = 0;
            if (l_orderUnits != null)
            {
                l_intSize = l_orderUnits.length;
            }
                
            for (int i = 0; i < l_intSize; i++)
            {
                //1.17.2.1 update振替処理区分(String, String, String, String, long, String, String)    
                //[引数の設定] 
                //証券会社コード：　@GFT振替状況Params.証券会社コード 
                //部店コード：　@GFT振替状況Params.部店コード 
                //顧客コード：　@GFT振替状況Params.顧客コード 
                //識別コード：　@GFT振替状況Params.信用振替用識別コード 
                //注文単位ID：　@get振替注文単位()の戻り値の注文単位.注文単位ID 
                //更新後発注日：　@(*) 
                //更新後受渡日：　@(*) 
                //(*)リクエストデータ.確認時発注日 != get発注日()の戻り値の場合、get発注日()の戻り値をセット。以外、nullをセット 
                if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)
                {
                    l_aioMarketSenderService.updateTransferProcessDiv(
                        l_transferStatusParams.getInstitutionCode(),
                        l_transferStatusParams.getBranchCode(),
                        l_transferStatusParams.getAccountCode(),
                        l_transferStatusParams.getMrgTrnOrderRequestNumber(),
                        l_orderUnits[i].getOrderUnitId(),
                        WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"),
                        WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd"));
                }
                else 
                {
                    l_aioMarketSenderService.updateTransferProcessDiv(
                        l_transferStatusParams.getInstitutionCode(),
                        l_transferStatusParams.getBranchCode(),
                        l_transferStatusParams.getAccountCode(),
                        l_transferStatusParams.getMrgTrnOrderRequestNumber(),
                        l_orderUnits[i].getOrderUnitId(),
                        null,
                        null);
                }
            }
        }
		//1.18. isトリガ発行(String)
        //[引数] 
        //発注条件：　@”DEFAULT”
        boolean l_blnIsSubmitTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                WEB3OrderingConditionDef.DEFAULT);
        
        //1.19. (*5) isトリガ発行()の戻り値 == true の場合)
        if (l_blnIsSubmitTrigger)
        {        
            log.debug("現在時刻がトリガ発行を実施する時間帯の場合、トリガー発行する。");
            //1.19.1 トリガ発行(String, String)
            //[引数] 
            //証券会社コード：　@GFT振替状況Params.証券会社コード 
            //データコード：　@GI806（保証金振替請求）            
            l_aioMarketSenderService.submitTrigger(
                l_transferStatusParams.getInstitutionCode(),
                WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ORDER + "T");
        }
            
        //1.20. updateGFT振替状況(GFT振替状況Params, GFT結果通知電文明細, String, String)
        //[引数の設定] 
        //GFT振替状況Params：　@getGFT振替状況()の戻り値 
        //GFT結果通知電文明細：　@リクエストデータ.GFT結果通知電文明細 
        //更新後受渡予定日：　@(*) 
        //エラー理由コード：　@0000（正常） 
        //(*)以下のとおり  
        //リクエストデータ.確認時発注日 == get発注日()の場合、null。  
        //それ以外の場合、get受渡日()の戻り値。               
        if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_request.checkDate) != 0)         
        {
            l_dataControlService.updateGFTTransferStatus(
            	l_transferStatusParams,
                l_request.fxGftResultNoticeTelegramUnit,
                WEB3DateUtility.formatDate(l_datDeliveryDate,"yyyyMMdd"),
                WEB3ErrorReasonCodeDef.NORMAL);
        }   
        else
        {
            l_dataControlService.updateGFTTransferStatus(
                l_transferStatusParams,
                l_request.fxGftResultNoticeTelegramUnit,
                null,
                WEB3ErrorReasonCodeDef.NORMAL);
        }          
        //1.21. getOrder(long)
        //[引数の設定] 
        //arg0：　@get注文単位()の戻り値の注文単位.注文ID
        AioOrder l_order;
        try
        {            
            l_order = (AioOrder) l_orderManager.getOrder(l_orderUnit.getOrderId());            
        }
        catch (NotFoundException l_ex)
        {   
            log.error("getOrder", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
            
        //1.22. createResponse()
        WEB3FXTransToFXCompleteResponse l_response = 
            (WEB3FXTransToFXCompleteResponse)l_request.createResponse();    
            
        //1.23. レスポンスデータにプロパティをセットする。
        //更新時間：注文.更新日時
        
        l_response.lastUpdatedTimestamp = 
            ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        
        //識別番号：get注文単位()の戻り値の注文単位.注文ID
        l_response.orderActionId = String.valueOf(l_orderUnit.getOrderId());
        
        //受渡日：get受渡日()の戻り値
        l_response.deliveryDate = l_datDeliveryDate;
      
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (createパラメータリスト) <BR>
     * パラメータリストを生成する。 <BR>
     *  <BR>
     * １）引数.接続区分 == ”（FX）東京金融先物取引所 振替登録” の場合 <BR>
     *  <BR>
     *   以下の要素の配列を生成し、返却する。 <BR>
     *  <BR>
     *   ・引数.電文明細.FXログインID.substring(1) <BR>
     *   ・"1"（預入金） <BR>
     *   ・引数.電文明細.入出金額 <BR>
     *   ・引数.電文明細.FXログインID.substring(1) + <BR>
     * 　@　@引数.文明細.DIR→GFT送信日時.substring(2) <BR>
     *  <BR>
     * ※該当する接続区分がない場合は、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag  : BUSINESS_ERROR_02408<BR>
     * @@param l_strConnectDiv - (接続区分)<BR>
     * @@param l_fxGftAskingTelegramUnit - (GFT依頼電文明細)<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected String[] createParamList(String l_strConnectDiv,
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " createParamList(String, WEB3FXGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);

        String[] l_strParamList = new String[4];
        if (l_fxGftAskingTelegramUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）引数.接続区分 == ”（FX）東京金融先物取引所 振替登録” の場合
        if (WEB3SoapConnectDivDef.FX_TOKYO_TRANSFER_SUBMIT.equals(l_strConnectDiv))
        {
            //・引数.電文明細.FXログインID.substring(1)
            l_strParamList[0] = l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1);
            //・"1"（預入金）
            l_strParamList[1] = "1";
            //・引数.電文明細.入出金額
            l_strParamList[2] = l_fxGftAskingTelegramUnit.cashinoutAmt;
            //・引数.電文明細.FXログインID.substring(1) + 引数.文明細.DIR→GFT送信日時.substring(2)
            l_strParamList[3] = l_fxGftAskingTelegramUnit.fxFirstLoginId.substring(1) +
                l_fxGftAskingTelegramUnit.dirSendTime.substring(2);
        }
        //※該当する接続区分がない場合は、例外をスローする。
        else
        {
            log.debug("該当する接続区分がないエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02408,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当する接続区分がないエラー。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_strParamList;
    }

    /**
     * (getGFT受付結果コード) <BR>
     * GFT結果通知電文明細にセットするGFT受付結果コードを取得する。 <BR>
     *  <BR>
     * １）引数.接続区分 == ”（FX）東京金融先物取引所 振替登録” の場合 <BR>
     *  <BR>
     *   引数.受付結果コードが <BR>
     *  <BR>
     *   ・”正常終了” の場合、受付結果コード.”処理完了” <BR>
     *   ・”パラメタエラー” の場合、受付結果コード.”上記以外で電文書式に起因するエラー” <BR>
     *   ・”顧客コード不正” の場合、受付結果コード.”入出金時に該当する証拠金口座が存在しない” <BR>
     *   ・”重複登録エラー” の場合、受付結果コード.”２重処理エラー” <BR>
     *   ・”入金額不正” の場合、受付結果コード.”入出金額が金額制限内でない” <BR>
     *   ・”稼動時間外エラー” の場合、受付結果コード.”ホスト処理時間外” <BR>
     *   ・上記以外の場合、受付結果コード.”上記、及び下記以外のエラー” <BR>
     *  <BR>
     * ※該当する接続区分がない場合は、引数.受付結果コードをそのまま返却する。 <BR>
     * @@param l_strConnectDiv - (接続区分)<BR>
     * @@param l_strResultCode - (受付結果コード)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected String getGFTResultCode(String l_strConnectDiv, String l_strResultCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getGFTResultCode(String, String)";
        log.entering(STR_METHOD_NAME);

        
        String l_strGFTResultCode = null;

        //１）引数.接続区分 == ”（FX）東京金融先物取引所 振替登録” の場合
        if (WEB3SoapConnectDivDef.FX_TOKYO_TRANSFER_SUBMIT.equals(l_strConnectDiv))
        {
            //・”正常終了” の場合、受付結果コード.”処理完了”
            if (WEB3SoapResultCodeDef.NORMAL.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            }
            //・”パラメタエラー” の場合、受付結果コード.”上記以外で電文書式に起因するエラー”
            else if (WEB3SoapResultCodeDef.PARAM_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000609;
            }
            //・”顧客コード不正” の場合、受付結果コード.”入出金時に該当する証拠金口座が存在しない”
            else if (WEB3SoapResultCodeDef.ACCOUNT_CODE_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501;
            }
            //・”重複登録エラー” の場合、受付結果コード.”２重処理エラー”
            else if (WEB3SoapResultCodeDef.DUP_SUBMIT_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801;
            }
            //・”入金額不正” の場合、受付結果コード.”入出金額が金額制限内でない”
            else if (WEB3SoapResultCodeDef.IN_AMOUNT_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000502;
            }
            //・”稼動時間外エラー” の場合、受付結果コード.”ホスト処理時間外”
            else if (WEB3SoapResultCodeDef.WORK_TIME_OUT_ERROR.equals(l_strResultCode))
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105;
            }
            //・上記以外の場合、受付結果コード.”上記、及び下記以外のエラー”
            else
            {
                l_strGFTResultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000901;
            }
        }
        //該当する接続区分がない場合は、引数.受付結果コードをそのまま返却する。
        else
        {
            l_strGFTResultCode = l_strResultCode;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strGFTResultCode;
    }

    /**
     * (submit注文) <BR>
     * 振替注文の完了処理を行う。 <BR>
     * ※SOAP接続にて行う。 <BR>
     * シーケンス図 <BR>
     * 「（FXへの振替）submit注文（SOAP接続）」参照。 <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：以下の条件で、外部システムSOAPプリファ@レンス（RPC形式）からレコードを取得<BR>
     * 　@　@　@※レコードが取得できなかった場合は例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_03075 <BR>
     * ======================================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3FXTransToFXCompleteSoapResponse
     * @@throws WEB3BaseException
     * @@roseuid 41C7B20800BF
     */
    protected WEB3FXTransToFXCompleteSoapResponse submitOrder(
        WEB3FXTransToFXCompleteSoapRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitOrder(WEB3FXTransToFXCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_fxDataControlService =
            (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);

        //1.1      会社別FXシステム条件Paramsを取得する。 
        //
        //[引数の設定] 
        //証券会社コード： get口座.getInstitution().getInstitutionCode() 
        //部店コード：　@get口座.getBranch().getBranchCode() 
        //FXシステムコード：　@リクエストデータ.FXシステムコード 
        CompFxConditionParams l_compFxConditionParams = null;
        try
        {
            l_compFxConditionParams = l_fxDataControlService.getCompFxCondition(
                this.getMainAccount().getInstitution().getInstitutionCode(),
                this.getMainAccount().getBranch().getBranchCode(),
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

        SoapConnectPrefRpcRow l_soapConnectPrefRpcRow = null;
        try
        {
            long l_lngBranchId = this.getMainAccount().getBranch().getBranchId();
            //(※)以下の条件で外部システムSOAPプリファ@レンス（RPC形式）からレコード取得
            //[条件]
            //部店ID = this.get口座().getBranch().getBranchId()の戻り値
            //接続区分 = 会社別FXシステム条件Params.FXシステムコード
            l_soapConnectPrefRpcRow =
                SoapConnectPrefRpcDao.findRowByPk(l_lngBranchId, l_compFxConditionParams.getFxSystemCode());
        }
        catch (DataFindException l_ex)
        {
            //※レコードが取得できなかった場合は例外をスローする。
            log.error("外部システムSOAPプリファ@レンス（RPC形式）のレコードが取得できません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外部システムSOAPプリファ@レンス（RPC形式）のレコードが取得できません。",
                l_ex);
        }
        catch (DataQueryException l_dqex)
        {
            log.error("テーブルに該当するデータがありません。", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DBアクセスエラー", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }

        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = 
        	new SoapConnectPrefRpcParams(l_soapConnectPrefRpcRow);

        WEB3FXTransToFXAskingRequest l_askRequest = new WEB3FXTransToFXAskingRequest();
        l_askRequest.wolfSession = l_request.wolfSession;
        l_askRequest.wolfAid = l_request.wolfAid;
        l_askRequest.regetServiceId = l_request.regetServiceId;
        l_askRequest.wolfSsid = l_request.wolfSsid;

        l_askRequest.fxAccInformationUnit = l_request.fxAccInformationUnit;
        l_askRequest.transferAmount = l_request.transferAmount;
        l_askRequest.password = l_request.password;
        l_askRequest.fxSystemCode = l_request.fxSystemCode;

        WEB3FXTransToFXAskingResponse l_fxTransToFXAskingResponse = null;
        try
        {
            //1.1 FXへの振替TransactionCallback(FXへの振替依頼リクエスト)
            WEB3FXTransToFXTransactionCallback l_transactionCallback =
                new WEB3FXTransToFXTransactionCallback(l_askRequest);
    
            //1.2 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //1.3 doTransaction(トランザクション属性 : int, トランザクションコールバック : TransactionCallback)
            //[引数]
            //トランザクション属性：　@TX_CREATE_NEW
            //トランザクションコールバック：　@FXへの振替TransactionCallbackインスタンス
            l_fxTransToFXAskingResponse = (WEB3FXTransToFXAskingResponse) l_queryProcessor.doTransaction(
                TransactionalInterceptor.TX_CREATE_NEW, l_transactionCallback);
        }
        catch (DataNetworkException l_ex)
        {
            log.error( "DBアクセスエラー。" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスエラー" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        String l_strReturnValue = null;
        String l_strResultCode = null;
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit = null;
        WEB3FXTransConnection l_aioTransChangeConnection =
            (WEB3FXTransConnection)Services.getService(WEB3FXTransConnection.class);
        //会社別FXシステム条件Params.外部接続システムコード = '02：TFX'の場合
        if (WEB3ExtConnectSystemCodeDef.TFX.equals(l_compFxConditionParams.getExtConnectSystemCode()))
        {
            boolean l_blnFlag = true;
            try
            {
                //validate接続準備
                l_fxDataControlService.validateSetup(l_soapConnectPrefRpcParams);
            }
            catch(WEB3BaseException l_ex)
            {
                //validate接続準備でエラーとなった場合、以下をセットしてTFXの接続処理を終了する。
                //GFT結果通知電文明細の受付結果コード.”GFTシステム起因エラー”(00000199)とする。
                l_strResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000199;
                //受付結果コードを”SOAP接続確認エラー”(9995)とする
                l_strReturnValue = WEB3GftSoapResultCodeDef.SOAP_CONN_CONFIRM_ERROR;
                l_blnFlag = false;
            }
            if (l_blnFlag)
            {
                //sendSOAPメッセージ(GFT依頼電文明細, SoapConnectPrefRpcParams)
                //[引数]
                //電文明細：FXへの振替依頼レスポンス.GFT依頼電文明細
                //SOAPプリファ@レンス：(※)で取得した外部システムSOAPプリファ@レンス（RPC形式）params
                try
                {
                    l_strReturnValue =
                        l_fxDataControlService.sendSoapMessage(
                            l_fxTransToFXAskingResponse.fxGftAskingTelegramUnit,
                            l_soapConnectPrefRpcParams);

                    //getSOAPTFX受付結果コード(String)
                    //[引数]
                    //受付結果コード： sendSOAPメッセージ()の戻り値
                    l_strResultCode =
                        l_fxDataControlService.getSoapTFXAcceptResultCode(
                            l_strReturnValue);
                }
                catch(WEB3BaseException l_ex)
                {
                    //・”外部システム接続エラー”の例外をスローした場合は、
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_02398.equals(l_ex.getErrorInfo()))
                    {
                        //GFT結果通知電文明細の受付結果コード.”GFT接続エラー”とする。
                        l_strResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                        //受付結果コードを”接続エラー（システムエラー）”とする
                        l_strReturnValue = WEB3GftSoapResultCodeDef.CONNECT_ERROR;
                    }
                    //・”予期しないシステムエラー”の例外をスローした場合は、
                    else if(WEB3ErrorCatalog.SYSTEM_ERROR_80002.equals(l_ex.getErrorInfo()))
                    {
                        //GFT結果通知電文明細の受付結果コード.”GFT接続エラー”とする。
                        l_strResultCode = WEB3AioTransferDetailMessageDef.ACCEPT_RESULT_CODE_00000990;
                        //受付結果コードを”接続エラー（システムエラー）”とする
                        l_strReturnValue = WEB3GftSoapResultCodeDef.CONNECT_ERROR;
                    }
                }
            }

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit = l_fxTransToFXAskingResponse.fxGftAskingTelegramUnit;
            //updateGFT振替状況(String, String, String, String)
            //証券会社コード： FXへの振替依頼レスポンス.GFT依頼電文明細.会社コード 
            //部店コード： FXへの振替依頼レスポンス.GFT依頼電文明細.部店コード 
            //識別コード： FXへの振替依頼レスポンス.GFT依頼電文明細.識別コード
            //受付結果コード：
            //　@　@validate接続準備でエラーが発生していない場合、sendSOAP
            //    メッセージ()の戻り値の受付結果コード
            //　@　@validate接続準備でエラーとなった場合、validate接続準備でセットする受付結果コード
            l_aioTransChangeConnection.updateGFTTransferStatus(
                l_fxGftAskingTelegramUnit.institutionCode,
                l_fxGftAskingTelegramUnit.branchCode,
                l_fxGftAskingTelegramUnit.requestNumber,
                l_strReturnValue);
            
            //createGFT結果通知電文明細
            WEB3FXConnCommonService l_fxExtConnCommonProcessService =
                (WEB3FXConnCommonService)Services.getService(WEB3FXConnCommonService.class);
            //[引数] 
            //GFT依頼電文明細： FXへの振替依頼レスポンス.GFT依頼電文明細
            //FX口座情報一覧： null
            //受付結果コード：
            //　@　@validate接続準備でエラーが発生していない場合、 getSOAPTFX受付結果コード()の戻り値
            // 　@　@validate接続準備でエラーとなった場合、validate接続準備でセットする受付結果コード
            l_fxGftResultNoticeTelegramUnit =
                l_fxExtConnCommonProcessService.createGftResultNoticeTelegramUnit(l_fxGftAskingTelegramUnit, null, l_strResultCode);
        }
        //会社別FXシステム条件Params.外部接続システムコード = '01：GFT' の場合
        else if (WEB3ExtConnectSystemCodeDef.GFT.equals(l_compFxConditionParams.getExtConnectSystemCode()))
        {
            //do振替実行
            //[引数]
            //会社別FXシステム条件Params： get会社別FXシステム条件()の戻り値
            //GFT依頼電文明細： FXへの振替依頼レスポンス.GFT依頼電文明細
            l_fxGftResultNoticeTelegramUnit =
                l_aioTransChangeConnection.doTransfer(l_compFxConditionParams, l_fxTransToFXAskingResponse.fxGftAskingTelegramUnit);
        }

        //1.15 (*4)インスタンス生成
        WEB3FXTransToFXCompleteRequest l_fxTransToFXCompleteRequest = new WEB3FXTransToFXCompleteRequest();

        //1.16 (*5)プロパティセット
        //GFT結果通知電文明細： 生成した結果通知電文明細
        l_fxTransToFXCompleteRequest.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
        //注文ID： FXへの振替依頼レスポンス.注文ID
        l_fxTransToFXCompleteRequest.orderId = l_fxTransToFXAskingResponse.orderId;
        //確認時発注日： FXへの振替依頼レスポンス.確認時発注日
        l_fxTransToFXCompleteRequest.checkDate = l_fxTransToFXAskingResponse.checkDate;
        //暗証番号：FXへの振替完了リクエスト（SOAP接続）.暗証番号
        l_fxTransToFXCompleteRequest.password = l_request.password;
        //FXシステムコード：FXへの振替完了リクエスト（SOAP接続）.FXシステムコード
        l_fxTransToFXCompleteRequest.fxSystemCode = l_request.fxSystemCode;

        //1.17 submit注文(FXへの振替完了リクエスト)
        WEB3FXTransToFXCompleteResponse l_fxTransToFXCompleteResponse = this.submitOrder(l_fxTransToFXCompleteRequest);

        //1.18 createResponse( )
        WEB3FXTransToFXCompleteSoapResponse l_response =
            (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();

        //1.19 (*6)プロパティセット
        //更新時間： FXへの振替完了レスポンス.更新時間
        l_response.lastUpdatedTimestamp = l_fxTransToFXCompleteResponse.lastUpdatedTimestamp;
        //識別番号： FXへの振替完了レスポンス.識別番号
        l_response.orderActionId = l_fxTransToFXCompleteResponse.orderActionId;
        //受渡日： FXへの振替完了レスポンス.受渡日
        l_response.deliveryDate = l_fxTransToFXCompleteResponse.deliveryDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (FXへの振替TransactionCallbackクラス)<BR>
     */
    public class WEB3FXTransToFXTransactionCallback implements TransactionCallback
    {
        /**
         * ログユーティリティ<BR>
         */
        private WEB3LogUtility l_log = WEB3LogUtility.getInstance(WEB3FXTransToFXTransactionCallback.class);

        /**
         * デフォルトコンストラクタ<BR>
         * 引数.リクエストデータを該当の変数に保存する。<BR>
         * @@param l_request - (リクエストデータ)<BR>
         * @@return WEB3FXTransToFXTransactionCallback
         * @@roseuid 415A6F3F0270
         */
        public WEB3FXTransToFXTransactionCallback(WEB3FXTransToFXAskingRequest l_request)
        {
            this.l_fxTransToFXAskingRequest = l_request;
        }

        /**
         * (FXへの振替依頼リクエス) <BR>
         * FXへの振替依頼リクエス <BR>
         */
        public WEB3FXTransToFXAskingRequest l_fxTransToFXAskingRequest;
        
        /**
         * 振替依頼・登録処理を行う。 <BR>
         *  <BR>
         * １）口座をロックする。 <BR>
         *  <BR>
         *    拡張アカウントマネージャ.lock口座()をコールする。 <BR>
         *  <BR>
         *    ※引数はOpLoginSecurityServiceより編集。 <BR>
         *  <BR>
         * ２）FXへの振替サービスImpl.start注文()をコールする。 <BR>
         *  <BR>
         *    [引数] <BR>
         *    リクエストデータ： this.リクエストデータ <BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 413C1FDC003F
         */
        public Object process() throws 
            DataNetworkException,
            DataQueryException,
            DataCallbackException
        {
            String STR_METHOD_NAME = " process()";
            l_log.entering(STR_METHOD_NAME);

            WEB3FXTransToFXAskingResponse l_fxTransToFXAskingResponse = null;
            //振替依頼・登録処理を行う。
            //１）口座をロックする。
            //   拡張アカウントマネージャ.lock口座()をコールする。
            //   ※引数はOpLoginSecurityServiceより編集。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
            OpLoginSecurityService l_opLoginSecurityService = 
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            
            long l_lngAccountId = l_opLoginSecurityService.getAccountId();
            try
            {
                MainAccount l_mainAccount = l_accountManager.getMainAccount(l_lngAccountId);
                //証券会社コードを取得する
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                //部店コードを取得する
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                //口座コードを取得する
                String l_accountCode = l_mainAccount.getAccountCode(); 
                
                l_accountManager.lockAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_accountCode);
            
                //２）FXへの振替サービスImpl.start注文()をコールする。
                //   [引数]
                //   リクエストデータ： this.リクエストデータ
                l_fxTransToFXAskingResponse = startOrder(this.l_fxTransToFXAskingRequest);
            }
            catch (NotFoundException l_ex)
            {
                log.error("error in l_accountManager.getMainAccount", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_log.entering(STR_METHOD_NAME);
            return l_fxTransToFXAskingResponse;
        }
    }
    
    /**
     * (get処理区分)<BR>
     * 処理区分にセットする値を設定する。 
     * <BR>
     * [戻り値] <BR> 
     * String：処理区分<BR> 
     * <BR>
     * １）以下の手順で処理区分を設定する。<BR> 
     * 引数．FXシステムコード：06（Hits先OP振替）の場合<BR> 
     * 　@・FXへの振替の場合、06（出金：先OP）を返却する。<BR> 
     * <BR>
     * ２）それ以外の場合は、04（出金：FX）を返却する。<BR> 
     * ※引数がnullの場合も含む。<BR>  
     * @@param l_strFxSystemCode - (FXシステムコード)<BR>
     * FXシステムコード
     * @@return string
     * @@throws WEB3BaseException 
     * 
     */
    public String getOperationDiv(String l_strFxSystemCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOperationDiv(String l_strFxSystemCode)";
        log.entering(STR_METHOD_NAME);        
        
        //引数．FXシステムコード：06（先OP）の場合 
        //　@・FXへの振替の場合、06（出金：先OP）を返却する。  
        if (WEB3FxSystemCodeDef.HITS_FUOP_TRANSFER.equals(l_strFxSystemCode))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GftMessageOperationDef.CASH_OUT_FUOP;        
        }
        //それ以外の場合は、04（出金：FX）を返却する。 
        //※引数がnullの場合も含む。 
        log.exiting(STR_METHOD_NAME);
        return WEB3GftMessageOperationDef.CASH_OUT_FX;
    }

    /**
     * (is受渡日)<BR>
     * 受渡日セットを判定する。 <BR> 
     * <BR>
     * [戻り値] <BR> 
     * true：受渡日をセットする。 <BR>
     * false：受渡日をセットしない。 <BR>
     * <BR>
     * 以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。<BR>  
     * <BR>
     * [条件]  <BR>
     * 部店ID = 引き数.部店ID <BR> 
     * プリファ@レンス名 = "fx.deliverydate.insert.check"<BR>  
     * プリファ@レンス名の連番 = 1  <BR>
     * <BR>
     * ２）取得したレコード.プリファ@レンスの値 == ”受渡日をセットする” の場合、true を返却する。<BR>  
     * <BR>
     * ３）それ以外の場合は、falseを返却する。<BR>  
     * ※レコードが取得できなかった場合も含む。<BR>
     * <BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isDeliveryDate(long l_lngBranchId) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = 
            "isDeliveryDate(long l_lngBranchId)";
        log.entering(STR_METHOD_NAME);
        
        //以下の条件で、部店用プリファ@レンステーブルからレコードを取得する。  
        //[条件]  
        //部店ID = 引き数.部店ID  
        //プリファ@レンス名 = "fx.deliverydate.insert.check"  
        //プリファ@レンス名の連番 = 1 
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(              
                l_lngBranchId,
                WEB3BranchPreferencesNameDef.FX_DELIVERY_DATE_INSERT_CHECK,
                1);
        } 
        catch (DataNetworkException l_dqex) 
        {
            log.error("DBへのアクセスに失敗しました:", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataQueryException l_ex) 
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //取得したレコードの値 == null の場合、falseを返却する。
        if(l_branchReferencesRow == null)
        {
        	log.exiting(STR_METHOD_NAME);
        	return false;
        }
        
        //２）取得したレコード.プリファ@レンスの値 == ”受渡日をセットする” の場合、true を返却する。
        if (WEB3FxDeliveryDateInsertCheckDef.CHECK.equals(l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //３）それ以外の場合は、falseを返却する。
        //※レコードが取得できなかった場合も含む。
        log.exiting(STR_METHOD_NAME);
        return false;        
    }
}
@
