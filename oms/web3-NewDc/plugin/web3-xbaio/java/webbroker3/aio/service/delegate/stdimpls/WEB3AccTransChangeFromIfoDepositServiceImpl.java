head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金から振替サービスImpl(WEB3AccTransChangeFromIfoDepositServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/22 屈陽 (中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
Revesion History : 2007/07/12 趙林鵬(中訊) モデルNo.732
Revision History : 2007/07/28 孟亜南(中訊) 仕様変更モデル743
Revesion History : 2007/08/23 武波 (中訊) 仕様変更・モデル752
Revesion History : 2009/03/16 車進 (中訊) 仕様変更・モデル1140
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証拠金から振替サービスImpl)<BR>
 * 証拠金から振替サービス実装クラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AccTransChangeFromIfoDepositService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeFromIfoDepositServiceImpl.class);

    /**
     * 証拠金から振替サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型によりvalidate注文()、<BR>
     *  またはsubmit注文()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41352FED007E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AccTransChangeFromIfoDepositConfirmRequest)
        {
            // validate注文()
            l_response =
                this.validateOrder(
                    (WEB3AccTransChangeFromIfoDepositConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AccTransChangeFromIfoDepositCompleteRequest)
        {
            // submit注文()
            l_response =
                this.submitOrder(
                    (WEB3AccTransChangeFromIfoDepositCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3AccTransChangeFromIfoDepositConfirmRequest "
                    + " と WEB3AccTransChangeFromIfoDepositCompleteRequest以外である, but is " + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)<BR>
     * 振替注文の発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（証拠金から振替）validate注文」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図( 「(証拠金振替サービスモデル) / 証拠金から振替」<BR>
     * （証拠金から振替）validate注文  )<BR>
     * 　@　@　@:  1.11.calc証拠金振替可能額( )<BR>   
     *     リクエストデータ.振替金額 > calc証拠金振替可能額()の戻り値の場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AccTransChangeFromIfoDepositConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135302102B1
     */
    protected WEB3AccTransChangeFromIfoDepositConfirmResponse validateOrder(
        WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(" +
                "WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1) リクエストデータの整合性をチェックする
        l_request.validate();
        
        // 1.2)補助口座オブジェクトを取得する。 
        SubAccount l_subAccount = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_TradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_TradingModule.getOrderManager();
        
        // 1.3)　@validate注文(SubAccount)
        // －受付時間チェック 
        // －システム停止中チェック 
        // －顧客のチェック（Ｙ客、管理ロック等）
        l_orderManager.validateOrder(l_subAccount);
        
        // 1.4) 先物取引口座を開設しているかをチェックする
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        // 1.5) get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        // 1.6) 振替回数が、振替可能回数を超えてないかチェックする
        l_orderManager.validateTransferPossibleCount(l_subAccount,l_datBizDate, OrderCategEnum.CASH_TRANSFER);

        //=============remain zhou-yong NO.2 begin ===========
        
        //1.9) get証拠金計算(補助口座 : 補助口座)
        //アイテムの定義
        //証拠金計算オブジェクトを取得する。
        //[引数] 
        //補助口座： get補助口座()の戻り値
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);

        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(l_gentradeSubAccount);

        //証拠金振替の指定日を取得する。
        int l_intBizDate =
            l_orderManager.getMarginTransferDesignatedDate(l_datBizDate);
        
        //1.10)  calc証拠金残高(指定日 : int)
        //証拠金残高を取得する。
        //[引数] 
        //指定日： get証拠金振替指定日()の戻り値
        double l_dblCalcIfoDepositBalance = 
            l_ifoDepositCalc.calcIfoDepositBalance(l_intBizDate);
        
        //1.11) calc証拠金振替余力額( )
        //アイテムの定義
        //証拠金の振替可能額を取得する。 
        double l_dblCalcIfoDepositTransferableAmount = l_ifoDepositCalc.calcIfoDepositTransferableAmount();
        
        //リクエストデータ.振替金額 > calc証拠金振替余力額()の戻り値
        //の場合、例外をスローする。
        if(Double.parseDouble(l_request.changeAmt) > l_dblCalcIfoDepositTransferableAmount)
        {
            log.debug("振替金額が可能額を超えています。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00761,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振替金額[" + l_request.changeAmt + "] > calc証拠金振替余力額()の戻り値[" 
                + l_dblCalcIfoDepositTransferableAmount + "]");
        }
        // =============remain zhou-yong NO.2 end ===========
        // 1.12) 補助口座オブジェクトを取得する。 （補助口座②）
        SubAccount l_subAccount2 = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //=========remain zhou-yong NO.2 begin ==========
        
        // 1.13) get出金可能額(補助口座 : 補助口座, 受渡日 : Date) 
        //出金可能額を取得する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値（補助口座②） 
        //受渡日： get発注日()の戻り値 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount2 = (WEB3GentradeSubAccount)l_subAccount2;
        
        double l_dblCashoutPossiblePrice = l_tPTradingPowerService.getPaymentTradingPower(
            l_gentradeSubAccount2, l_datBizDate);
        
        //=========remain zhou-yong NO.2 begin ==========
        
        // 1.14) 新規の注文IDを取得する。 
        long l_lngNewOrderID = l_orderManager.createNewOrderId();
        
        // 1.15) レスポンスデータを生成する
        WEB3AccTransChangeFromIfoDepositConfirmResponse l_response = 
            (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_request.createResponse();
        
        // 1.16) プロパティセット
        //レスポンス.振替後振替可能額 = 証拠金計算.caｌｃ証拠金振替余力額()の戻り値 - リクエストデータ.振替金額
        l_response.aftChangePossAmt = WEB3StringTypeUtility.formatNumber(
            l_dblCalcIfoDepositTransferableAmount 
                - Double.parseDouble(l_request.changeAmt));
        
        //レスポンス.振替後証拠金 = 証拠金計算.calc証拠金残高()の戻り値 - リクエストデータ.振替金額
        l_response.aftIfoDeposit =  
            WEB3StringTypeUtility.formatNumber(l_dblCalcIfoDepositBalance
                - Double.parseDouble(l_request.changeAmt));
        
        //レスポンス.振替後お預かり金残高 = 取引余力サービス.get出金可能額()の戻り値 + リクエストデータ.振替金額
        l_response.aftDepositBal =  WEB3StringTypeUtility.formatNumber(
            l_dblCashoutPossiblePrice
                + Double.parseDouble(l_request.changeAmt));

        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        
        //レスポンス.注文ID = AIO注文マネージャ.createNewOrderId()の戻り値
        l_response.orderId = l_lngNewOrderID + "";
        
        // リターン           
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }

    /**
     * (submit注文)<BR>
     * 振替注文の登録を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（証拠金から振替）submit注文」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * 
     * シーケンス図(「(証拠金振替サービスモデル) / <BR>
     *    証拠金から振替」（証拠金から振替）submit注文 )<BR>
     * 　@　@　@　@　@　@ : 1.8.calc証拠金振替可能額( )<BR>   
     *     リクエストデータ.振替金額 > calc証拠金振替可能額()の戻り値の場合、<BR>
     *     例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00761<BR>
     * <BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 
     * @@return WEB3AccTransChangeFromIfoDepositCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4135308D012A
     */
    protected WEB3AccTransChangeFromIfoDepositCompleteResponse submitOrder(
        WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(" +
            "WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1) リクエストデータの整合性をチェックする
        l_request.validate();
        
        // 1.2)補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 7（証拠金口座） 
        SubAccount l_subAccount = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_TradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager) l_TradingModule.getOrderManager();
        
        // 1.3)　@validate注文(SubAccount)
        // －受付時間チェック 
        // －システム停止中チェック 
        // －顧客のチェック（Ｙ客、管理ロック等）
        l_orderManager.validateOrder(l_subAccount);
        
        // 1.4) 先物取引口座を開設しているかをチェックする
        l_orderManager.validateOpenFuturesTradedAccount(l_subAccount);
        
        // 1.5) get発注日()
        //      [引数] 
        //      確認時発注日： リクエストデータ.確認時発注日
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(
            l_request.checkDate);
        
        // 1.6) 振替回数が、振替可能回数を超えてないかチェックする
        l_orderManager.validateTransferPossibleCount(l_subAccount,l_datBizDate, OrderCategEnum.CASH_TRANSFER);

        //============remain zhou-yong NO.1 begin ===============
        
        //1.7) get証拠金計算(補助口座 : 補助口座)
        //アイテムの定義
        //証拠金計算インスタンスを生成する。
        //[引数] 
        //補助口座： get補助口座()の戻り値
        WEB3IfoDepositCalcService l_service =
            (WEB3IfoDepositCalcService) Services.getService(
                WEB3IfoDepositCalcService.class);
        
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        WEB3IfoDepositCalc l_ifoDepositCalc = l_service.getIfoDepositCalc(l_gentradeSubAccount);
        
        // 1.8) calc証拠金振替余力額( )
        //アイテムの定義
        //振替可能額を算出する。
        double l_dblCalcIfoDepositTransferableAmount = 
            l_ifoDepositCalc.calcIfoDepositTransferableAmount();
        
        //リクエストデータ.振替金額 > calc証拠金振替可能額()の戻り値　@の場合です
        if(Double.parseDouble(l_request.changeAmt) > l_dblCalcIfoDepositTransferableAmount)
        {
            log.debug("振替金額が可能額を超えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00761,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振替金額[" + l_request.changeAmt + "] > calc証拠金振替可能額()の戻り値[" 
                + l_dblCalcIfoDepositTransferableAmount + "]");            
        }

        // 1.9) 代理入力者オブジェクトを取得する
        Trader l_trader = this.getTrader();
        
        // 1.10) 新規の識別コードを取得する。
        // [引数] 
        // 証券会社コード： 補助口座.get取引店().getInstitution().getInstitutionCode()の戻り値 
        // 部店コード： 補助口座.get取引店().getBranchCode()の戻り値 
        // 銘柄タイプ： 5（現金）
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        
        String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
            l_subAccount.getInstitution().getInstitutionCode(), 
            l_subAccount.getMainAccount().getBranch().getBranchCode(), 
            ProductTypeEnum.CASH);

        //1.11) get商品ID(Institution)(AIO注文マネージャ::get商品ID)
        //アイテムの定義
        //商品ID（銘柄ID）を取得する。
        //[引数] 
        //証券会社： 補助口座.get取引店().getInstitution()の戻り値 
        long l_lngProductId = l_orderManager.getProductId(l_subAccount.getInstitution());
        
        // 1.12) 補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount2 = this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //get口座( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();

        // is信用口座開設(弁済区分 : String)
        //弁済区分：　@"0"（指定無し）
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //顧客が信用口座を開設している（is信用口座開設()==TRUE）場合、処理を行う
        if (l_blnIsMarginAccountEstablished)
        {
            // submit保証金振替(顧客, Date, double, String)
            //顧客：　@get口座()の戻り値
            //受渡日：　@get発注日()の戻り値
            //入金額：　@リクエストデータ.振替金額
            //暗証番号：　@リクエスストデータ.暗証番号
            //代理入力者：　@get代理入力者()の戻り値
            WEB3MarginTransferService l_marginTransferService =
                (WEB3MarginTransferService)Services.getService(WEB3MarginTransferService.class);

            l_marginTransferService.submitMarginTransfer(
                l_mainAccount,
                l_datBizDate,
                Double.parseDouble(l_request.changeAmt),
                l_request.password,
                l_trader);
        }

        // 1.13) 入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, 
        //long, double, String, Date, String, Long) (振替元注文)
        //アイテムの定義
        //入出金注文内容インスタンスを生成する。
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //注文種別： 1008（振替注文（株先証拠金から預り金） 
        //振替タイプ： 2（出金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： null 
        //振替予定日： 引数.発注日 
        //決済機@関ID： null 
        //注文ID： null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec1 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            AssetTransferTypeEnum.CASH_OUT,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_request.checkDate,
            null,
            null); 
        
        //1.14)  振替注文更新インタセプタ
        //アイテムの定義
        //振替注文更新インタセプタのインスタンスを生成する。
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト（振替元注文） 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor1 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec1);
        
        //1.15) (*1)プロパティセット
        //(*1)(*2) 以下のとおりに、プロパティをセットする。
        //インタセプタ.発注日 = get発注日()の戻り値
        //インタセプタ.受渡日 = get発注日()の戻り値
        //インタセプタ.識別コード = get新規識別コード()の戻り値
        l_transferOrderUpdateInterceptor1.setBizDate(l_datBizDate);
        l_transferOrderUpdateInterceptor1.setDeliveryDate(l_datBizDate);
        l_transferOrderUpdateInterceptor1.setOrderRequestNumber(l_strNewNumber);
        l_transferOrderUpdateInterceptor1.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
        
        //1.16) 入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double,
        //String, Date, String, Long) 振替先注文） 
        //アイテムの定義
        //入出金注文内容インスタンスを生成する。
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //注文種別： 1008（振替注文（株先証拠金から預り金） 
        //振替タイプ： 1（入金） 
        //商品ID： get商品ID()の戻り値 
        //金額： リクエストデータ.振替金額 
        //記述： null 
        //振替予定日： 引数.発注日 
        //決済機@関ID： null 
        //注文ID： null 
        WEB3AioNewOrderSpec l_aioNewOrderSpec2 = new WEB3AioNewOrderSpec(
            l_trader,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            Double.parseDouble(l_request.changeAmt),
            null,
            l_request.checkDate,
            null,
            null); 
        
        //1.17)  振替注文更新インタセプタ(入出金注文内容)
        //アイテムの定義
        //振替注文更新インタセプタのインスタンスを生成する。
        //[引数] 
        //入出金注文内容： 入出金注文内容オブジェクト（振替先注文） 
        WEB3AioTransferOrderUpdateInterceptor l_transferOrderUpdateInterceptor2 =
            new WEB3AioTransferOrderUpdateInterceptor(l_aioNewOrderSpec2);
        
        //1.18) (*8)プロパティセット
        //(*1)(*2) 以下のとおりに、プロパティをセットする。
        //インタセプタ.発注日 = get発注日()の戻り値
        //インタセプタ.受渡日 = get発注日()の戻り値
        //インタセプタ.識別コード = get新規識別コード()の戻り値
        l_transferOrderUpdateInterceptor2.setBizDate(l_datBizDate);
        l_transferOrderUpdateInterceptor2.setDeliveryDate(l_datBizDate);
        l_transferOrderUpdateInterceptor2.setOrderRequestNumber(l_strNewNumber);
        l_transferOrderUpdateInterceptor2.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);
                
        //1.19) submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum, NewOrderSpec,
        //AioOrderManagerPersistenceInterceptor, long, String)
        //アイテムの定義
        //振替元の振替注文を登録する。
        //[引数] 
        //補助口座： get補助口座()の戻り値（証拠金口座） 
        //銘柄タイプ： 5（現金） 
        //注文種別： 1008（振替注文（株先証拠金から預り金） 
        //注文内容： 振替元の入出金注文内容オブジェクト 
        //インタセプタ： 振替元の振替注文更新インタセプタオブジェクト 
        //注文ID： リクエストデータ.注文ID 
        //パスワード： リクエストデータ.暗証番号 
        l_orderManager.submitTransferOrder(
            l_subAccount,
            ProductTypeEnum.CASH,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            l_aioNewOrderSpec1,
            l_transferOrderUpdateInterceptor1,
            Long.parseLong(l_request.orderId),
            l_request.password);
        
        //1.20) createNewOrderId( )
        //アイテムの定義
        //反対注文用の注文IDを取得する。
        long l_lngCreateNewOrderId = l_orderManager.createNewOrderId();
        
        //1.21) submit振替注文(SubAccount, ProductTypeEnum, OrderTypeEnum,
        //NewOrderSpec, AioOrderManagerPersistenceInterceptor, long, String)
        //アイテムの定義
        //振替先の振替注文を登録する。
        //[引数] 
        //補助口座： get補助口座()の戻り値（預り金口座） 
        //銘柄タイプ： 5（現金） 
        //注文種別： 1008（振替注文（株先証拠金から預り金） 
        //注文内容： 振替先の入出金注文内容オブジェクト 
        //インタセプタ： 振替先の振替注文更新インタセプタオブジェクト 
        //注文ID： createNewOrderId()の戻り値 
        //パスワード： リクエストデータ.暗証番号 
        l_orderManager.submitTransferOrder(
            l_subAccount2,
            ProductTypeEnum.CASH,
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
            l_aioNewOrderSpec2,
            l_transferOrderUpdateInterceptor2,
            l_lngCreateNewOrderId,
            l_request.password);
        
        //1.22) 余力再計算(補助口座 : 補助口座)
        //余力の更新を行う。 
        //[引数] 
        //補助口座： get補助口座()の戻り値（預り金口座） 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        l_tPTradingPowerService.reCalcTradingPower(
                (WEB3GentradeSubAccount)l_subAccount2);

        //1.23) getOrder(long)
        //アイテムの定義
        //注文を取得する。
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        Order l_order = null;
        try
        {
            l_order = l_orderManager.getOrder(Long.parseLong(l_request.orderId));
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文を取得する: ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 1.24) レスポンスデータを生成する
        WEB3AccTransChangeFromIfoDepositCompleteResponse l_response = 
            (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_request.createResponse();
            
        // 18) レスポンスのプロパティセット
        l_response.lastUpdatedTimestamp = 
            ((AioOrderRow)l_order.getDataSourceObject()).getLastUpdatedTimestamp();
        l_response.orderId = l_request.orderId;

        //===========remain zhou-yong NO.1 end ==============
        
        // リターン           
        log.exiting(STR_METHOD_NAME);     
        return l_response;
    }
}
@
