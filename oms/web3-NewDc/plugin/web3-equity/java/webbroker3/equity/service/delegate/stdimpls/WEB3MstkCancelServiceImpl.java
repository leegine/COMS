head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文取消サービスImpl(WEB3MstkCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 カク寛新 (中訊) 新規作成
*/

package webbroker3.equity.service.delegate.stdimpls;


import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityCancelOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.WEB3MiniStockCancelUpdateInterceptor;
import webbroker3.equity.message.WEB3MstkCancelCompleteRequest;
import webbroker3.equity.message.WEB3MstkCancelCompleteResponse;
import webbroker3.equity.message.WEB3MstkCancelConfirmRequest;
import webbroker3.equity.message.WEB3MstkCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MstkCancelService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ミニ投資注文取消サービスImpl）。<BR>
 * <BR>
 * 株式ミニ投資注文取消サービス実装クラス
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MstkCancelServiceImpl extends WEB3MiniClientRequestService implements WEB3MstkCancelService 
{
    
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelServiceImpl.class);    

    /**
     * 
     */
    public WEB3MstkCancelServiceImpl() 
    {
     
    }

    /**
     * （execute）。<BR>
     * <BR>
     * 株式ミニ投資注文取消処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、株式ミニ投資注文取消確認リクエストの<BR>
     * 場合 <BR>
     * 　@－validate注文()をコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、株式ミニ投資注文取消完了リクエストの<BR>
     * 場合 <BR>
     * 　@－submit注文()をコールする。
     * @@param l_request (リクエスト)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null; 
        
        //引数のリクエストデータが、株式ミニ投資注文取消確認リクエストの場合 
        if(l_request instanceof WEB3MstkCancelConfirmRequest)
        {
            
            l_response = this.validateOrder((WEB3MstkCancelConfirmRequest)l_request);
            
        } 
        
        //引数のリクエストデータが、株式ミニ投資注文取消完了リクエストの場合 
        else if(l_request instanceof WEB3MstkCancelCompleteRequest)
        {
            
            l_response = this.submitOrder((WEB3MstkCancelCompleteRequest)l_request);   
            
        }    
        else
        {
            
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * （validate注文）。<BR>
     * <BR>
     * ミニ株注文取消発注審査を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株取消サービス）validate注文」参照。
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資注文取消確認リクエストデータオブジェクト
     * @@return WEB3MstkCancelConfirmResponse
     */
    protected WEB3MstkCancelConfirmResponse validateOrder(WEB3MstkCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateOrder(WEB3MstkCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //2) validate()
        l_request.validate();
        
        //3) get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //4)CancelOrderSpec(long)
        //arg0(注文ID)： リクエストデータ.注文ID
        String l_strOrderId = l_request.id;
        long l_lngOrderId = Long.parseLong(l_strOrderId);

        WEB3EquityCancelOrderSpec l_orderSpec = new WEB3EquityCancelOrderSpec(l_lngOrderId, this.getTrader());
        
        //5)validateミニ株注文取消(補助口座, CancelOrderSpec)
        //補助口座：　@補助口座 
        //取消注文内容：　@取消注文内容 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);                       
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        
        OrderValidationResult l_orderValidationResult = l_orderManager.validateMiniStockCancelOrder(
            l_subAccount,
            l_orderSpec);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
           
            throw new WEB3BaseException(l_orderValidationResult.getProcessingResult().getErrorInfo(),STR_METHOD_NAME);
           
        }        
        
        //6)isミニ株取引終了警告(部店)
        //補助口座.get取引店()
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);
        
        //7)getOrderUnits(long)
        //注文ID：　@リクエストデータ.ID
        OrderUnit l_orderUnit = l_orderManager.getOrderUnits(l_lngOrderId)[0];
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams = new EqtypeOrderUnitParams(l_orderUnitRow);
        
        //8)getTradedProduct()
        TradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct = l_orderUnit.getTradedProduct();
        }
        catch (RuntimeSystemException l_rse)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01966,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文単位ID=[" + Long.toString(l_orderUnit.getOrderUnitId()) + "]の注文単位に紐付く取引銘柄無し",
                l_rse);
        }
        
        //9)getProduct()
        Product l_product = l_tradedProduct.getProduct();
                       
        //10)getMarket()
        Market l_market = l_tradedProduct.getMarket();
        
        //11)getSide() 
        SideEnum l_sideEnum = l_orderUnit.getSide();
                
        //12)getQuantity()
        double l_orderQuantity = l_orderUnit.getQuantity();
        
                
        //13)createResponse()
        WEB3MstkCancelConfirmResponse l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
        
        //14) プロパティセット
        //14.1) 銘柄名：　@取引銘柄.getProduct().getStandardName()
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        
        //14.2) 市場コード：　@取引銘柄.getMarket().getMarketCode()
        l_response.marketCode = l_market.getMarketCode();
        
        //14.3)売買区分:
        //（注文単位.getSide() == SideEnum.BUY）の場合、買い
        if ((SideEnum.BUY).equals(l_sideEnum))
        {
            
            l_response.dealingType = "" + SideEnum.BUY.intValue();
            
        }
        //（注文単位.getSide() == SideEnum.SELL）の場合、売り
        if ((SideEnum.SELL).equals(l_sideEnum))
        {
            
            l_response.dealingType = "" + SideEnum.SELL.intValue();
            
        }        
        //14.4) 注文株数：　@注文単位.getQuantity()
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderQuantity);
        
        //14.5)確認時発注日：　@注文単位.発注日
        Date l_orderBizDateDate = WEB3DateUtility.getDate(l_orderUnitParams.getBizDate(), "yyyyMMdd");
        l_response.checkDate = WEB3DateUtility.toDay(l_orderBizDateDate);
        
        //14.6)取引終了警告 
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        log.debug("取引終了警告" + l_response.messageSuspensionFlag);
        //14.7)銘柄コード：　@取引銘柄.getProduct().getProductCode()
        l_response.productCode = ((WEB3EquityProduct)l_product).getProductCode();
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * （submit注文）。<BR>
     * <BR>
     * ミニ株注文取消登録を行う。<BR> 
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株取消サービス）submit注文」参照。
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資注文取消完了リクエストデータオブジェクト
     * @@return WEB3MstkCancelCompleteResponse
     */
    protected WEB3MstkCancelCompleteResponse submitOrder(WEB3MstkCancelCompleteRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " submitOrder(WEB3MstkCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //2) validate()
        l_request.validate();
        
        //3) get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //4)CancelOrderSpec(long)
        //arg0（注文ID）：　@リクエストデータ.注文ID 
        String l_strOrderId = l_request.id;
        log.debug(l_strOrderId);
        long l_lngOrderId = Long.parseLong(l_strOrderId);

        WEB3EquityCancelOrderSpec l_orderSpec = new WEB3EquityCancelOrderSpec(l_lngOrderId, this.getTrader());
        
        //5)validateミニ株注文取消(補助口座, CancelOrderSpec)
        //補助口座：　@補助口座 
        //取消注文内容：　@取消注文内容
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);        
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        
        OrderValidationResult l_orderValidationResult = l_orderManager.validateMiniStockCancelOrder(
            l_subAccount,
            l_orderSpec);
            
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            
            throw new WEB3BaseException(l_orderValidationResult.getProcessingResult().getErrorInfo(),STR_METHOD_NAME);
           
        } 
		//) get発注日()
		Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
         
        //6)株式ミニ投資取消更新インタセプタ()
            
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv = l_opLoginSec
            .getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3MiniStockCancelUpdateInterceptor l_interceptor
            = new WEB3MiniStockCancelUpdateInterceptor(l_strOrderRootDiv, (WEB3GentradeTrader) this.getTrader());
        
        //7)setThreadLocalPersistenceEventInterceptor(EqTypeOrderManagerPersistenceEventInterceptor)
        //[setThreadLocalPersistenceInterceptor()に指定する引数] 
        //arg0：　@（生成した株式ミニ投資取消更新インタセプタオブジェクト）
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //8)submitCancelOrder(SubAccount, EqTypeCancelOrderSpec, 論理ビュー::java::lang::String, boolean) 
        //arg0（補助口座）：　@補助口座 
        //arg1（取消注文内容）：　@取消注文内容 
        //arg2（取引パスワード）：　@リクエストデータ.暗証番号 
        //arg3（isSkip発注審査）：　@true 
        OrderSubmissionResult l_result = l_orderManager.submitCancelOrder(
            l_subAccount,
            (CancelOrderSpec)l_orderSpec,
            l_request.password,
            true);
        
        if (l_result.getProcessingResult().isFailedResult())
        {
            
            throw new WEB3BaseException(l_result.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }

        //9)余力再計算
        log.debug("余力再計算を行う");
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(this.getSubAccount());
        
        //10)createResponse()
        WEB3MstkCancelCompleteResponse l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
        
        //11) プロパティセット
        //11.1)更新時間：　@TradingSystem.getSystemTimestamp()
        l_response.lastUpdatedTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
        
        //識別番号11.2)：　@リクエストデータ.注文ID
        l_response.orderActionId = l_request.id;
        
        log.exiting(STR_METHOD_NAME);  
        return l_response;
        
    }
}
@
