head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消サービスImpl(WEB3AioCashoutCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 黄建 (中訊) 新規作成
                   2004/10/25 屈陽 (中訊) レビュー
                   2004/12/09 周勇 (中訊) 残対応
Revesion History : 2010/02/02 武波 (中訊)モデルNo.1261
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import webbroker3.aio.WEB3AioCashoutCancelUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金取消サービスImpl)<BR>
 * 出金取消サービス実装クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelServiceImpl extends WEB3ClientRequestService 
    implements WEB3AioCashoutCancelService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelServiceImpl.class);
    
    /**
     * 出金取消サービス処理を実施する。 <BR>
     * <BR>
     * リクエストデータの型によりvalidate注文()、<BR>
     * またはsubmit注文()メソッドをコールする。 <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F73EFC00DC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AioCashoutCancelConfirmRequest)
        {
            //validate注文()メソッド
            l_response =
                this.validateOrder(
                    (WEB3AioCashoutCancelConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AioCashoutCancelCompleteRequest)
        {
            //submit注文()メソッド
            l_response =
                this.submitOrder(
                    (WEB3AioCashoutCancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3AioCashoutCancelConfirmRequest "
                    + " と WEB3AioCashoutCancelCompleteRequest以外である, but is " + l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 出金取消の発注審査を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出金取消）validate注文」参照。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioCashoutCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F73EFC00DE
     */
    protected WEB3AioCashoutCancelConfirmResponse validateOrder(
        WEB3AioCashoutCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3AioCashoutCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //１.１） validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //１.２）get発注日( )
        //発注日を取得する。
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("発注日を取得する ========" + l_datOrderBizDate);     
        
        //１.３）get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //１.４）取消注文内容インスタンスを生成する。
        //[引数]
        //注文ID： リクエストデータ.注文ID
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(Long.parseLong(l_request.orderId));
        
        //１.５）取消注文チェックを実施する。 
        //以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等） 
        //  －該当注文が取消可能かどうかのチェック
        //[引数]
        //補助口座： get補助口座()の戻り値
        //取消注文内容： 取消注文内容オブジェクト
        
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        
        //チェックを行う
        l_aioOrderManager.validateCancelOrder(l_subAccount, l_cancelOrderSpec);

        //1.6）getOrderUnits(注文ID : long)
        //注文単位オブジェクトを取得する。 
        //※リストの1番目の要素を取得する。 
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        //===================NotFoundException=================
        OrderUnit[] l_orderUnits = 
            l_aioOrderManager.getOrderUnits(
                Long.parseLong(l_request.orderId));
        if (l_orderUnits.length <= 0)
        {
          log.debug("注文単位オブジェクトを取得する");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0];         
        
        //1.7）getQuantity( )
        //金額を取得する。 
        String l_strQuantity = 
            WEB3StringTypeUtility.formatNumber(l_aioOrderUnit.getQuantity());
        log.debug("金額を取得する:" + l_strQuantity);    
        
        //1.8）getEstimatedTransferDate( )
        //振込予定日を取得する。
        Date l_datEstimatedTransfer = l_aioOrderUnit.getEstimatedTransferDate();
        log.debug("振込予定日を取得する:" + l_datEstimatedTransfer);    
        
        //1.9）get出金可能額～出金入力画面表示用～(補助口座 : 補助口座, 受渡日 : Date) 
        //振込予定日の余力を取得する。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //受渡日： getEstimatedTransferDate()の戻り値 
        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
     
        double l_dblCashoutPossiblePrice =  
            l_tPTradingPowerService.getPaymentTradingPowerAioCashoutInput(
                (WEB3GentradeSubAccount)l_subAccount, l_datEstimatedTransfer);

        //1.10） createResponse( ) レスポンスデータを生成する。
        WEB3AioCashoutCancelConfirmResponse 
            l_aioCashoutCancelConfirmResponse =
                (WEB3AioCashoutCancelConfirmResponse) l_request.createResponse();      
        
        //1.11）(*) プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。

        //レスポンス.注文ID = リクエストデータ.注文ID
        l_aioCashoutCancelConfirmResponse.orderId = l_request.orderId;  
        
        //レスポンス.出金余力 = 取引余力サービス.get出金可能額の戻り値 
        l_aioCashoutCancelConfirmResponse.paymentPower = 
            WEB3StringTypeUtility.formatNumber(l_dblCashoutPossiblePrice);
            
        //レスポンス.出金金額 = 注文単位.getQuantity()の戻り値
        l_aioCashoutCancelConfirmResponse.cashoutAmt = l_strQuantity;
        
        //レスポンス.振込予定日 = 注文単位.getEstimatedTransferDate()の戻り値
        l_aioCashoutCancelConfirmResponse.transScheduledDate = l_datEstimatedTransfer;
        
        //レスポンス.確認時発注日 = 取引時間管理.get発注日()の戻り値
        l_aioCashoutCancelConfirmResponse.checkDate = l_datOrderBizDate;
                
        //生成した出金取消確認レスポンスを返す。
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutCancelConfirmResponse;                
    }
    
    /**
     * (submit注文)<BR>
     * 出金取消の登録を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出金取消）submit注文」参照。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioCashoutCancelCompleteResponse
     * @@roseuid 40F73EFC00E0
     */
    protected WEB3AioCashoutCancelCompleteResponse submitOrder(
        WEB3AioCashoutCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3AioCashoutCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //１.１）validate( )
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //１.２）get発注日(Date)
        //発注日の取得と確認時の発注日とのチェックを行う。 
        //[引数] 
        //確認時発注日： リクエストデータ.確認時発注日 
        Date l_datOrderBizDate =  
            WEB3GentradeTradingTimeManagement.getOrderBizDate(
                l_request.checkDate); 
        log.debug("発注日の取得" + l_datOrderBizDate);       
                
        //１.３）get補助口座(SubAccountTypeEnum)
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //１.４）CancelOrderSpec(long)
        //取消注文内容インスタンスを生成する。 
        //[引数] 
        //注文ID： リクエストデータ.注文ID     
        CancelOrderSpec l_cancelOrderSpec = 
            new CancelOrderSpec(Long.parseLong(l_request.orderId));
        
        //１.５） validate取消注文(SubAccount, CancelOrderSpec)
        //取消注文チェックを実施する。 
        //以下のチェックを行う。 
        //  －受付時間チェック 
        //  －システム停止中チェック 
        //  －顧客のチェック（Ｙ客、管理ロック等） 
        //  －該当注文が取消可能かどうかのチェック 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //取消注文内容： 取消注文内容オブジェクト 
        
        //============================FinApp==============================
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
        //入出金注文マネージャクラスを取得する。
        WEB3AioOrderManager l_aioOrderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        //取消注文チェック
        OrderValidationResult l_orderValidationResult =
            l_aioOrderManager.validateCancelOrder(
                l_subAccount,
                l_cancelOrderSpec);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("validate取消注文 Error " +
                l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }      
            
        //１.６）出金取消更新インタセプタ( )
        //インタセプタを生成する。 
        WEB3AioCashoutCancelUpdateInterceptor 
            l_aioCashoutCancelUpdateInterceptor =
                new WEB3AioCashoutCancelUpdateInterceptor();

        //１.7） getOrderUnits(注文ID : long)
        //※リストの1番目の要素を取得する。 
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        OrderUnit[] l_orderUnits = 
            l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        if (l_orderUnits.length <= 0)
        {
          log.debug("Error in 注文単位オブジェクトを取得する");
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0]; 
        
        //１.8 ）setThreadLocalPersistenceEventInterceptor(
        //出金取消更新インタセプタ : AioOrderManagerPersistenceEventInterceptor)
        //インタセプタをセットする。 
        //[引数] 
        //出金取消更新インタセプタ：　@生成した出金取消更新インタセプタ 
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_aioCashoutCancelUpdateInterceptor);

        //１.9）submitCancelOrder(補助口座 : SubAccount, 
        //取消注文内容 : CancelOrderSpec, パスワード : String, isSkip発注審査 : boolean)
        //取消を実行する。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //取消注文内容：　@取消注文内容オブジェクト 
        //パスワード：　@リクエストデータ.暗証番号 
        //isSkip発注審査：　@true 
        //OrderSubmissionResult
        OrderSubmissionResult l_submitCancelOrderResult =
            l_aioOrderManager.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_request.password,
                true);
            
        if (l_submitCancelOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitCancelOrder" +
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitCancelOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  

        //1.10)メッセージ 余力再計算(補助口座 : 補助口座)
        //アイテムの定義
        //余力の更新をする。
        //[引数] 
        //補助口座：　@補助口座オブジェクト 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        // ReGet OrderUnit
        AioOrderUnit l_aioOrderUnitCanceled = null;
        try
        {
            l_aioOrderUnitCanceled = 
                (AioOrderUnit)l_aioOrderManager.getOrderUnit(l_aioOrderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("Error in 注文単位オブジェクトを取得する", l_ex);
            throw new WEB3SystemLayerException( 
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage());
        }
        
        
       //１.１1）getQuantity( )
       //金額を取得する。   
       String l_strQuantity = 
           WEB3StringTypeUtility.formatNumber(l_aioOrderUnitCanceled.getQuantity()); 
       log.debug("金額を取得する" + l_strQuantity);         
      
       //１.１2）getEstimatedTransferDate( )
       //振込予定日を取得する　@ 
       Date l_datEstimatedTransfer =
           l_aioOrderUnitCanceled.getEstimatedTransferDate();
       log.debug("振込予定日を取得する " + l_datEstimatedTransfer);              
       
       //１.１3）getDataSourceObject( ) 
       //注文単位の行オブジェクトを取得する。  
       AioOrderUnitRow l_aioOrderUnitRow = 
           (AioOrderUnitRow) l_aioOrderUnitCanceled.getDataSourceObject();  
       
       //１.１4）getLastUpdatedTimestamp( )
       //更新日付を取得する
       Date l_datLastUpdatedTimestamp = l_aioOrderUnitRow.getLastUpdatedTimestamp();     
       log.debug("更新日付を取得する " + l_datLastUpdatedTimestamp); 
       
       //１.１5）createResponse( ) レスポンスデータを生成する。       
       WEB3AioCashoutCancelCompleteResponse 
           l_aioCashoutCancelCompleteResponse = 
               (WEB3AioCashoutCancelCompleteResponse) l_request.createResponse();   
      
      //）１.１6）(*) 以下のとおりに、プロパティをセットする。
      
      //レスポンス.出金金額 = 注文単位.getQuantity()の戻り値
      l_aioCashoutCancelCompleteResponse.cashoutAmt = l_strQuantity;
      
      //レスポンス.振込予定日 = 注文単位.getEstimatedTransferDate()の戻り値
      l_aioCashoutCancelCompleteResponse.transScheduledDate = 
          l_datEstimatedTransfer;
           
      //レスポンス.更新時間 = 注文単位Params.getLastUpdateedTimestamp()の戻り値
      l_aioCashoutCancelCompleteResponse.lastUpdatedTimestamp = 
          l_datLastUpdatedTimestamp;
           
      //レスポンス.注文ID = リクエストデータ.注文ID     
      l_aioCashoutCancelCompleteResponse.orderId = l_request.orderId;
      log.debug("リクエストデータ.注文ID " + l_aioCashoutCancelCompleteResponse.orderId );
      
      //生成した出金取消完了レスポンスを返す。
      log.exiting(STR_METHOD_NAME);
      return l_aioCashoutCancelCompleteResponse;
    }
}@
