head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替サービスImplクラス(WEB3FEqConTransferServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 韋念瓊 (中訊) 新規作成       
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.WEB3FEqConTransferOrderUpdateInterceptor;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.message.WEB3FEqConTransferCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferInputRequest;
import webbroker3.aio.message.WEB3FEqConTransferInputResponse;
import webbroker3.aio.service.delegate.WEB3FEqConTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株口座への振替サービスImpl)<BR>
 * 外株口座への振替サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferServiceImpl extends WEB3ClientRequestService 
    implements WEB3FEqConTransferService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferServiceImpl.class);  
    
    /**
     * @@roseuid 423559DA0251
     */
    public WEB3FEqConTransferServiceImpl() 
    {
     
    }
    
    /**
     * 外株口座への振替処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get入力画面()<BR>
     *   validate振替()<BR>
     *   submit振替()
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3863901FE
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
        
        //リクエストデータの型により、以下のいずれかのメソッドをコールする。 
        // get入力画面() 
        // validate振替() 
        // submit振替() 
        if (l_request instanceof WEB3FEqConTransferInputRequest)
        {
            l_response = 
                getInputScreen((WEB3FEqConTransferInputRequest)l_request);   
        }
        else if (l_request instanceof WEB3FEqConTransferConfirmRequest)
        {
            l_response =
                validateTransfer((WEB3FEqConTransferConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FEqConTransferCompleteRequest)
        {
            l_response =
                submitTransfer((WEB3FEqConTransferCompleteRequest)l_request);
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
     * (get入力画面)<BR>
     * 入力画面表示を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座への振替）get入力画面」 参照
     * ----------------------------------------------
     * 1.6 get外国株式顧客(String, String, String)
     * 戻り値がnullの場合、例外をthorwする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01944<BR>
     * <BR>
     * ----------------------------------------------
     * @@param l_request - リクエストデータ
     * @@throws WEB3BaseException
     * @@return WEB3FEqConTransferInputResponse
     * @@roseuid 41E3925A02F8
     */
    protected WEB3FEqConTransferInputResponse getInputScreen(
        WEB3FEqConTransferInputRequest l_request) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getInputScreen(WEB3FEqConTransferInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金））
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.2 validate(注文) 以下のチェックを行う。 
        //－受付時間チェック 
        //－システム停止中チェック 
        //－顧客のチェック（Ｙ客、管理ロック等） 
        //[引数] 
        //補助口座： get補助口座()の戻り値
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.3 validate外株振替可能()
        //外株の振替取引が可能であるかをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);
        
        //1.4 get発注日( )
        //発注日を取得する。 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.5 validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //振替可能回数のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ： 16（外国株式振替） 
        l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datBizDate, 
                OrderCategEnum.FEQ_TRANSFER);
        
        //1.6 get外国株式顧客(String, String, String)
        //外国株式顧客オブジェクトを取得する。 
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード： 補助口座.get取引店.getBranchCode() 
        //顧客コード： 補助口座.getMainAccount().getAccountCode() 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);     
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = 
                l_feqConTransferDataControlService.getFeqAccountByAccountCode(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("NotFoundException: ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.7 get出金可能額(補助口座 : 補助口座, 受渡日 : Date) 
        //出金可能額を取得する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //受渡日： 取得した発注日の翌営業日 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        //取得した発注日の翌営業日
        Date l_datBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);
        
        //出金可能額
        double l_dblCashoutPossiblePrice =  l_tPTradingPowerService.getPaymentTradingPower(
                l_gentradeSubAccount, l_datBizTomDate);
        
        //1.8) createResponse( )
        WEB3FEqConTransferInputResponse l_response = 
            (WEB3FEqConTransferInputResponse)l_request.createResponse();
        
        //1.9) (*)プロパティセット
        //レスポンスデータにプロパティをセットする。

        //取引口座番号 : 外国株式顧客.外国株式口座番号
        l_response.tradeAccountCode = l_feqAccountParams.getFeqAccountCode();
   
        //振替可能額 : get出金可能額の戻り値
        l_response.changePossAmt = WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice);        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate振替)<BR>
     * 振替の確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座への振替）validate振替」 参照
     * ======================================================== <BR>
     * 1.16 戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01306 <BR>
     * <BR>
     * ==========================================================
     * @@param l_request - リクエストデータ
     * @@throws WEB3BaseException
     * @@return WEB3FEqConTransferConfirmResponse
     * @@roseuid 41E3925A0317
     */
    protected WEB3FEqConTransferConfirmResponse validateTransfer(
        WEB3FEqConTransferConfirmRequest l_request) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "validateTransfer(WEB3FEqConTransferConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
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

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();

        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4 validate外株振替可能()
        //外株の振替取引が可能であるかをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);
        
        //1.5 get発注日( )
        //発注日を取得する。 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.6 validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //振替可能回数のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ： 16（外国株式振替） 
        l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datBizDate, 
                OrderCategEnum.FEQ_TRANSFER);
        
        //1.7 validate外株初回振替(SubAccount, double)
        //初回振替のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //振替金額： リクエストデータ.振替金額 
        l_aioOrderManager.validateFeqConFirstTransfer(
                l_subAccount, 
                Double.parseDouble(l_request.changeAmt));
      
        //商品ID（銘柄ID）を取得する。
        long l_lngProductId = 
            l_aioOrderManager.getProductId(l_subAccount.getInstitution());        
        
        //1.8 入出金注文内容を生成する。 
        //[引数] 
        //代理入力者： null 
        //注文種別： 1013（外国株式振替注文（預り金から外国株式口座）） 
        //振替タイプ： 2（出金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： null 
        //振替予定日： 取得した発注日 
        //決済機@関ID： null 
        //注文ID： null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec = 
            new WEB3AioNewOrderSpec(
                null, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                AssetTransferTypeEnum.CASH_OUT, 
                l_lngProductId, 
                Double.parseDouble(l_request.changeAmt), 
                null, 
                l_datBizDate, 
                null, 
                null);
        
        //1.9) 外株振替注文更新インタセプタを生成する。 
        //[引数] 
        //入出金注文内容：　@入出金注文内容  
        WEB3FEqConTransferOrderUpdateInterceptor l_orderUpdateInterceptor =
            new WEB3FEqConTransferOrderUpdateInterceptor(l_aioNewOrderSpec);
        
        //1.10)(*1)プロパティセット 
        l_orderUpdateInterceptor.setOrderBizDate(l_datBizDate);
        //取得した発注日の翌営業日
        Date l_datBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);
        l_orderUpdateInterceptor.setDeliveryDate(l_datBizTomDate);
        
        //注文内容インタセプタの配列
        WEB3FEqConTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_orderUpdateInterceptor};
            
        //注文内容の配列
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec}; 
        
        //1.11) 余力のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //注文内容インタセプタ： 外株振替注文更新インタセプタを要素とした配列
        //注文内容： 入出金注文内容を要素とした配列 
        //注文種別：1013（外国株式振替注文（預り金から外国株式口座）） 
        //余力更新フラグ： false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                false);
        
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("取引余力チェックエラー。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー。");     
        }
        
        //取引余力結果.get取引可能額()の戻り値
        double l_dblTradingPower = l_powerResult.getTradingPower();
        
        //1.12 createResponse( )
        WEB3FEqConTransferConfirmResponse l_response = 
            (WEB3FEqConTransferConfirmResponse)l_request.createResponse();
        
        //1.13 (*)プロパティセット
        //レスポンスデータにプロパティをセットする。

        //振替可能額 = 取引余力結果.get取引可能額()の戻り値
        l_response.changePossAmt = WEB3StringTypeUtility.formatNumber(l_dblTradingPower);
        log.debug("振替可能額 :" + l_response.changePossAmt);
        
        //確認時発注日 : get発注日()の戻り値
        l_response.checkDate = l_datBizDate;
        log.debug("確認時発注日 :" + l_response.checkDate);
        
        //受渡予定日： 発注日の翌
        Date l_datDeliveryScheduledDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
        
        l_response.deliveryScheduledDate = l_datDeliveryScheduledDate;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (submit振替)<BR>
     * 振替の完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座への振替）submit振替」 参照
     * --------------------------------------------------------
     * 1.5 get外株振替発注日( )
     * リクエストデータ.確認時発注日 != 取得した発注日 の場合、
     * 例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00205<BR>
     * <BR>
     * --------------------------------------------------------
     * 1.14 戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01306 <BR>
     * <BR>
     * --------------------------------------------------------
     * @@param l_request - リクエストデータ
     * @@throws WEB3BaseException
     * @@return WEB3FEqConTransferCompleteResponse
     * @@roseuid 41E3925A0337
     */
    protected WEB3FEqConTransferCompleteResponse submitTransfer(
        WEB3FEqConTransferCompleteRequest l_request) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "submitTransfer(WEB3FEqConTransferCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
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

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();

        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4 validate外株振替可能()
        //外株の振替取引が可能であるかをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);
        
        //1.5 get発注日(Date)        
        //発注日を取得する。 
        //[引数]  
        //確認時発注日： リクエストデータ.確認時発注日
        Date l_datBizDate = 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                    l_request.checkDate);
        
        //1.6 validate振替可能回数(SubAccount, Date, OrderCategEnum)
        //振替可能回数のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        //注文カテゴリ： 16（外国株式振替） 
        l_aioOrderManager.validateTransferPossibleCount(
                l_subAccount, 
                l_datBizDate, 
                OrderCategEnum.FEQ_TRANSFER);
        
        //1.7 validate外株初回振替(SubAccount, double)
        //初回振替のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //振替金額： リクエストデータ.振替金額 
        l_aioOrderManager.validateFeqConFirstTransfer(
                l_subAccount, 
                Double.parseDouble(l_request.changeAmt));
                        
        
        //1.8 get代理入力者( )
        //アイテムの定義
        //代理入力者オブジェクトを取得する。
        Trader l_trader = this.getTrader();
        
        //1.9 get商品ID(Institution)
        //現金に該当する銘柄ID（商品ID）を取得する。 
        //[引数] 
        //証券会社： 補助口座.get取引店().getInstitution()の戻り値 
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());
        
        //識別コード(1)
        //1.10  get新規識別コード(String, String, ProductTypeEnum)
        //新規の識別コードを取得する。
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //銘柄タイプ： 5（現金）
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class); 
        
        String l_strNewRequestNumber1 = l_hostReqOrderNumberManageService.getNewNumber(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_subAccount.getMainAccount().getBranch().getBranchCode(), 
            ProductTypeEnum.CASH);
        log.debug("識別コード(1) = " + l_strNewRequestNumber1);
        
        String l_strNewRequestNumber2 = null;  //識別コード(2)
        
        //振替注文(1)
        //1.11 入出金注文内容を生成する。 
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //注文種別： 1013（外国株式振替注文（預り金から外国株式口座）） 
        //振替タイプ： 2（出金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： ”feq_transfer” 
        //振替予定日： 取得した発注日 
        //決済機@関ID： null 
        //注文ID： null 

        WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.TRANSFER_TO_FEQ,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.changeAmt),
                WEB3AioDescriptionDef.FEQ_TRANSFER,
                l_datBizDate,
                null,
                null);
        
        //1.12 外株振替注文更新インタセプタを生成する。 
        //[引数の設定] 
        //入出金注文内容：　@入出金注文内容（振替注文(1)）
        WEB3FEqConTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
            new WEB3FEqConTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
        
        //1.13 (*)プロパティセット
        //発注日 : 取得した発注日 
        l_transferOrderUpdateInterceptor1.setOrderBizDate(l_datBizDate);
        
        //取得した発注日の翌営業日
        Date l_datBizTomDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);
        
        //受渡日：取得した発注日の翌営業日 
        l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datBizTomDate);
        
        //識別コード：get新規識別コードの戻り値(識別コード(1))
        l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewRequestNumber1);
        
        WEB3FEqConTransferOrderUpdateInterceptor[] l_orderUpdateInterceptors = 
            {l_transferOrderUpdateInterceptor1};
    
        //注文内容の配列
        WEB3AioNewOrderSpec[] l_aioNewOrderSpecs = {l_aioNewOrderSpec1}; 
    
        //1.14 余力のチェックを行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //注文内容インタセプタ： 外株振替注文更新インタセプタを要素とした配列 
        //注文内容： 入出金注文内容を要素とした配列 
        //注文種別： 1013（外国株式振替注文（預り金から外国株式口座）） 
        //余力更新フラグ： false 
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3TPTradingPowerResult l_powerResult =      
            l_tpTradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount, 
                l_orderUpdateInterceptors, 
                l_aioNewOrderSpecs, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                false);
        
        //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローす
        if (l_powerResult.isResultFlg() == false)
        {
            log.debug("取引余力チェックエラー。"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー。");     
        }
        
        //1.15 (*1)信用口座開設済の場合、実施。
        //（補助口座.getMainAccount().is信用口座開設（弁済区分（＝”指定なし”）の戻り値＝true）
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        double l_dblTransferAmount = 0;
        SubAccount l_subAccountMargin = null;
        WEB3GentradeSubAccount l_gentradeSubAccountMargin = null;
        
        if (l_booisMarginAccountEstablished)
        {
            //1.15.1 get補助口座(SubAccountTypeEnum)
            //補助口座オブジェクトを取得する。 
            //[引数] 
            //補助口座タイプ： 2（株式信用取引口座（保証金））
            l_subAccountMargin =
                this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_gentradeSubAccountMargin = 
                (WEB3GentradeSubAccount)l_subAccountMargin;
            
            //1.15.2 get預り金への振替額(補助口座 : 補助口座, 必要現金 : double, 受渡日 : Date)
            //実質顧客勘定残高を取得する。 
            //[引数] 
            //補助口座： get補助口座()の戻り値（保証金口座） 
            //必要現金： リクエストデータ.振替金額 
            //受渡日：　@取得した発注日の翌営業日             
            l_dblTransferAmount = 
                l_tpTradingPowerService.getTransferAmountToEquitySubAcount(
                    l_gentradeSubAccountMargin, 
                    Double.parseDouble(l_request.changeAmt), 
                    l_datBizTomDate);
        }
        
        //注文ID(1)
        //1.16 createNewOrderId( ) 
        //新規注文IDを採番する。
        long l_lngNewOrderId1 = l_aioOrderManager.createNewOrderId();
        log.debug("注文ID(1) = " + l_lngNewOrderId1);
        
        long l_lngNewOrderId2 = 0L; //注文ID(2)
        long l_lngNewOrderId3 = 0L; //注文ID(3)
        
        //1.17 submit振替注文() 振替注文を登録する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //銘柄タイプ： 5（現金） 
        //注文種別： 1013（外国株式振替注文（預り金から外国株式口座）） 
        //注文内容： 入出金注文内容（振替注文(1)） 
        //インタセプタ： 外株振替注文更新インタセプタオブジェクト（振替注文(1)） 
        //注文ID： createNewOrderId()の戻り値（注文ID(1)） 
        //パスワード： リクエストデータ.暗証番号 
        l_aioOrderManager.submitTransferOrder(
                l_subAccount, 
                ProductTypeEnum.CASH, 
                OrderTypeEnum.TRANSFER_TO_FEQ, 
                l_aioNewOrderSpec1, 
                l_transferOrderUpdateInterceptor1, 
                l_lngNewOrderId1,
                l_request.password);
        
		//　@業務日付の取得（yyyyMMdd）
		String l_strBizDate1 = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd");
		Date l_datBizDate1 = WEB3DateUtility.getDate(l_strBizDate1, "yyyyMMdd");
		
		//　@発注日をyyyyMMdd型に変更
		String l_strOrderBizdate1 = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
		Date l_datOrderBizDate1 = WEB3DateUtility.getDate(l_strOrderBizdate1, "yyyyMMdd");
		
		//1.18 is保証金振替(補助口座 l_subAccount, Date 業務日付, Date 注文単位.発注日)
		boolean l_blnIsDepositTransfer = l_aioOrderManager.isDepositTransfer(l_subAccount, l_datBizDate1, l_datOrderBizDate1);
        
		//1.19  (*2)信用保証金から預り金への振替要の場合
		//（補助口座.getMainAccount().is信用口座開設（弁済区分（＝”指定なし”）の戻り値＝true 
		// and get預り金への振替額()の戻り値 > 0
		// and is保証金振替()の戻り値 = true）  
		log.debug("get預り金への振替額()の戻り値 = " + l_dblTransferAmount);
		if (l_booisMarginAccountEstablished && l_dblTransferAmount > 0 && l_blnIsDepositTransfer)
        {
            log.debug("信用保証金から預り金への振替要の場合");
            //1.19.1 シーケンス図
            //（外株口座への振替）信用振替注文登録 参照
            
            //1.1) get新規識別コード(String, String, ProductTypeEnum)
            //新規の識別コードを取得する。（識別コード(2)） 
            //[引数] 
            //証券会社コード： 補助口座.証券会社コード 
            //部店コード：　@補助口座.get取引店().getBranchCode() 
            //銘柄タイプ： 5（現金）
            l_strNewRequestNumber2 = l_hostReqOrderNumberManageService.getNewNumber(
                    l_subAccount.getInstitution().getInstitutionCode(), 
                    l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                    ProductTypeEnum.CASH);
            log.debug("識別コード(2) = " + l_strNewRequestNumber2);
            
            //振替注文(2)
            //1.2) 入出金注文内容インスタンスを生成する。 
            //[引数] 
            //代理入力者： get代理入力者()の戻り値 
            //注文種別： 1006（振替注文（信用保証金から預り金）） 
            //振替タイプ： 2（出金） 
            //商品ID： get商品ID()の戻り値 
            //金額： get預り金への振替額()の戻り値 
            //記述： ”feq_transfer” 
            //振替予定日： get発注日()の戻り値 
            //決済期間ID： null 
            //注文ID： null 
            WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
                    l_trader, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, //1006
                    AssetTransferTypeEnum.CASH_OUT,
                    l_lngProductId, 
                    l_dblTransferAmount, //get預り金への振替額()の戻り値
                    WEB3AioDescriptionDef.FEQ_TRANSFER, 
                    l_datBizDate, 
                    null, 
                    null);
            
            //1.3) 振替注文更新インタセプタを生成する。 
            //[引数の設定] 
            //入出金注文内容：　@入出金注文内容（振替注文(2)）
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);

            //1.4)  (*1)プロパティセット
            //(*1)以下のとおりにプロパティをセットする。
            //発注日：　@get発注日()の戻り値
            l_transferOrderUpdateInterceptor2.setBizDate(l_datBizDate);
            //受渡日：　@発注日の翌営業日
            l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datBizTomDate);
            //識別コード：　@get新規識別コード()の戻り値（識別コード(2)）
            l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewRequestNumber2);
            //MQステータス：　@0(未送信)
            l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //振替注文(3)
            //1.5) 入出金注文内容インスタンスを生成する。
            //[引数]
            //代理入力者： get代理入力者()の戻り値
            //注文種別： 1006（振替注文（信用保証金から預り金））
            //振替タイプ： 1（入金）
            //商品ID： get商品ID()の戻り値
            //金額： get預り金への振替額()の戻り値
            //記述： ”feq_transfer” 
            //振替予定日： get発注日()の戻り値
            //決済期間ID： null
            //注文ID： null
            WEB3AioNewOrderSpec l_aioNewOrderSpec3 = new WEB3AioNewOrderSpec(
                    l_trader, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, //1006
                    AssetTransferTypeEnum.CASH_IN,  //1（入金）
                    l_lngProductId, 
                    l_dblTransferAmount, //get預り金への振替額()の戻り値
                    WEB3AioDescriptionDef.FEQ_TRANSFER, 
                    l_datBizDate, 
                    null, 
                    null);
            
            //1.6) 振替注文更新インタセプタを生成する。
            //[引数の設定]
            //入出金注文内容：　@入出金注文内容（振替注文(3)）
            WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor3 =
                new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec3);
            
            //1.7)  (*2)プロパティセット
            //(*2)以下のとおりにプロパティをセットする。
            //発注日：　@get発注日()の戻り値
            l_transferOrderUpdateInterceptor3.setBizDate(l_datBizDate);
            //受渡日：　@発注日の翌営業日
            l_transferOrderUpdateInterceptor3.setDeliveryDate(l_datBizTomDate);
            //識別コード：　@get新規識別コード()の戻り値（識別コード(2)）
            l_transferOrderUpdateInterceptor3.setOrderRequestNumber(l_strNewRequestNumber2);
            //MQステータス：　@0(未送信)
            l_transferOrderUpdateInterceptor3.setMqStatus(
                    WEB3MqStatusDef.NOT_SEND_MAIL);
            
            //注文ID(2)
            //1.8) 新規注文IDを採番する。
            l_lngNewOrderId2 = l_aioOrderManager.createNewOrderId();
            log.debug("注文ID(2) = " + l_lngNewOrderId2);
            
            //1.9) 信用振替注文（振替元）を登録する。
            //[引数]
            //補助口座： get補助口座()の戻り値（保証金口座）
            //銘柄タイプ： 5（現金）
            //注文種別： 1006（振替注文（信用保証金から預り金））
            //注文内容： 入出金注文内容（振替注文(2)）
            //インタセプタ： 振替注文更新インタセプタ（振替注文(2)）
            //注文ID： createNewOrderId()の戻り値（注文ID(2)）
            //パスワード： リクエストデータ.暗証番号
            l_aioOrderManager.submitTransferOrder(
                    l_subAccountMargin, //get補助口座()の戻り値（保証金口座）
                    ProductTypeEnum.CASH, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
                    l_aioNewOrderSpec2, 
                    l_transferOrderUpdateInterceptor2, 
                    l_lngNewOrderId2,
                    l_request.password);
            
            //注文ID(3)
            //1.10) 新規注文IDを採番する。
            l_lngNewOrderId3 = l_aioOrderManager.createNewOrderId();
            log.debug("注文ID(3) = " + l_lngNewOrderId3);
            
            //1.11) 信用振替の反対注文（振替先）を登録する。
            //[引数]
            //補助口座： get補助口座()の戻り値（預り金口座）
            //銘柄タイプ： 5（現金）
            //注文種別： 1006（振替注文（信用保証金から預り金））
            //注文内容： 入出金注文内容（振替注文(3)）
            //インタセプタ： 振替注文更新インタセプタ（振替注文(3)）
            //注文ID： createNewOrderId()の戻り値（注文ID(3)）
            //パスワード： リクエストデータ.暗証番号
            l_aioOrderManager.submitTransferOrder(
                    l_subAccount, //get補助口座()の戻り値（預り金口座）
                    ProductTypeEnum.CASH, 
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT, 
                    l_aioNewOrderSpec3, 
                    l_transferOrderUpdateInterceptor3, 
                    l_lngNewOrderId3,
                    l_request.password);            
        }
        
        //1.20 余力再計算(補助口座 : 補助口座)
        //余力更新を行う。 
        //[引数の設定] 
        //補助口座：　@get補助口座()(*)の戻り値 
        //(*)預かり金の補助口座タイプを指定して取得した補助口座
        l_tpTradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        
        //1.21  insertUWG振替状況(String, String, String, String, String, String)
        //UWG振替状況テーブルに行をinsertする。 
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード：　@補助口座.get取引店().getBranchCode() 
        //顧客コード：　@補助口座.getMainAccount().getAccountCode() 
        //受渡予定日： 取得した発注日.toString() 
        //識別コード： get新規識別コード()の戻り値（識別コード(1)） 
        //信用振替用識別コード： (*) 
        //振替金額： リクエストデータ.振替金額 
        //(*)信用口座開設済 && 保証金から預かり金への振替が必要な場合、採番した識別コード(2)をセット。
        //   以外、nullをセット。 
        WEB3FEqConTransferDataControlService l_feqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);        
        
        String l_strMrgTrnRequestNumber = null;
        if (l_booisMarginAccountEstablished && l_dblTransferAmount > 0)
        {
            l_strMrgTrnRequestNumber = l_strNewRequestNumber2;
        }
            
        l_feqConTransferDataControlService.insertUwgTransferStatus(
                l_subAccount.getInstitution().getInstitutionCode(), 
                l_subAccount.getMainAccount().getBranch().getBranchCode(), 
                l_subAccount.getMainAccount().getAccountCode(), 
                WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"), 
                l_strNewRequestNumber1, 
                l_strMrgTrnRequestNumber, 
                l_request.changeAmt);
        
        //1.22 getOrderUnits()
        //注文単位を取得する。 
        //※配列の先頭の要素を取得 
        //[引数] 
        //注文ID： 注文ID(1)
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(l_lngNewOrderId1);
                
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        AioOrderUnitRow l_orderUnitRow = 
            (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();        
        
        //1.23 createResponse( )
        WEB3FEqConTransferCompleteResponse l_response = 
            (WEB3FEqConTransferCompleteResponse)l_request.createResponse();
        
        //1.24 (*)プロパティセット
        //レスポンスデータにプロパティをセットする。
        //注文ID： 注文ID(1)
        l_response.orderId = l_lngNewOrderId1 + "";
        log.debug("注文ID : " + l_response.orderId);
        
        //受付日時 : 注文単位.作成日付
        l_response.receptionDate = l_orderUnitRow.getCreatedTimestamp();        
        log.debug("受付日時 : " + l_response.receptionDate);
        
        //受渡予定日： 発注日の翌
        Date l_datDeliveryScheduledDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
        
        l_response.deliveryScheduledDate = l_datDeliveryScheduledDate;        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
