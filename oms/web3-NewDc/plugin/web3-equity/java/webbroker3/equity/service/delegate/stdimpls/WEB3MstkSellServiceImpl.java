head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付注文サービス実装クラス(WEB3MstkSellServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 李海波 (中訊) 新規作成
                 : 2006/11/14 唐性峰 (中訊)　@モデルNo.1026
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MiniClientRequestService;
import webbroker3.equity.WEB3MiniStockOrderUpdateInterceptor;
import webbroker3.equity.WEB3MstkSellUnitComparator;
import webbroker3.equity.WEB3NewMiniStockOrderSpec;
import webbroker3.equity.define.WEB3EquityRepaymentDivOtherDef;
import webbroker3.equity.message.WEB3MstkCommissionInfoUnit;
import webbroker3.equity.message.WEB3MstkProductCodeNameUnit;
import webbroker3.equity.message.WEB3MstkSellCompleteRequest;
import webbroker3.equity.message.WEB3MstkSellCompleteResponse;
import webbroker3.equity.message.WEB3MstkSellConfirmRequest;
import webbroker3.equity.message.WEB3MstkSellConfirmResponse;
import webbroker3.equity.message.WEB3MstkSellInputRequest;
import webbroker3.equity.message.WEB3MstkSellInputResponse;
import webbroker3.equity.message.WEB3MstkSellListRequest;
import webbroker3.equity.message.WEB3MstkSellListResponse;
import webbroker3.equity.message.WEB3MstkSellUnit;
import webbroker3.equity.service.delegate.WEB3MstkSellService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ミニ投資売付注文サービスImpl）。<br>
 * <br>
 * 株式ミニ投資売付注文サービス実装クラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellServiceImpl extends WEB3MiniClientRequestService implements WEB3MstkSellService 
{
    
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellServiceImpl.class);
    
    /**
     * 
     */
    public WEB3MstkSellServiceImpl() 
    {
     
    }
    
    /**
     * （execute）。<br>
     * <br>
     * 株式ミニ投資売付注文処理を実施する。<BR>
     * <br>
     * リクエストデータの型により、以下の通りメソッドをコールする。 <br>
     * <br>
     * ○ 引数のリクエストデータが、株式ミニ投資売付一覧リクエストの場合 <br>
     * 　@－get売付一覧()をコールする。 <br>
     * <br>
     * ○ 引数のリクエストデータが、株式ミニ投資売付注文入力リクエストの場合 <br>
     * 　@－get入力画面()をコールする。 <br>
     * <br>
     * ○ 引数のリクエストデータが、株式ミニ投資売付注文確認リクエストの場合 <br>
     * 　@－validate注文()をコールする。 <br>
     * <br>
     * ○ 引数のリクエストデータが、株式ミニ投資売付注文完了リクエストの場合 <br>
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
        //引数のリクエストデータが、株式ミニ投資売付一覧リクエストの場合
        if(l_request instanceof WEB3MstkSellListRequest)
        {  
            l_response = this.getSellList((WEB3MstkSellListRequest)l_request);
        }
        //引数のリクエストデータが、株式ミニ投資売付注文入力リクエストの場合
        else if(l_request instanceof WEB3MstkSellInputRequest)
        {
            l_response = this.getInputScreen((WEB3MstkSellInputRequest)l_request);      
        }
        //引数のリクエストデータが、株式ミニ投資売付注文確認リクエストの場合
        else if(l_request instanceof WEB3MstkSellConfirmRequest)
        {
            l_response = this.validateOrder((WEB3MstkSellConfirmRequest)l_request); 
        }
        //引数のリクエストデータが、株式ミニ投資売付注文完了リクエストの場合
        else if(l_request instanceof WEB3MstkSellCompleteRequest)
        {
            l_response = this.submitOrder((WEB3MstkSellCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * （get売付一覧）。<br>
     * <br>
     * 株式ミニ投資売付一覧画面表示処理を実施する。 <br>
     * <br>
     * シーケンス図 <br>
     * 「（ミニ株売付サービス）get売付一覧」参照。
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資売付一覧リクエストデータオブジェクト
     * @@return WEB3MstkSellListResponse
     */
    protected WEB3MstkSellListResponse getSellList(WEB3MstkSellListRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getSellList(WEB3MstkSellListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //2) validate()
        l_request.validate();
        
        //3) get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //4) validateミニ株注文(補助口座)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.validateMiniStockOrder(l_subAccount);
        
        //5) isミニ株取引終了警告(部店)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);
        
        //6) createResponse()
        WEB3MstkSellListResponse l_response = (WEB3MstkSellListResponse)l_request.createResponse();
        
        //7) create銘柄コード名称(株式ミニ投資売付一覧レスポンス, 補助口座, 株式ミニ投資売付一覧リクエスト)
        this.createProductCodeName(l_response, l_subAccount, l_request);
        
        //8) create売付明細(株式ミニ投資売付一覧レスポンス, 補助口座, 株式ミニ投資売付一覧リクエスト)
        this.createSellUnit(l_response, l_subAccount, l_request);
        
        //9) プロパティセット
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * （get入力画面）。<br>
     * <br>
     * 株式ミニ投資売付入力画面表示処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株売付サービス）get入力画面」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(株式ミニ投資売付 / （ミニ株売付サービス）get入力画面): <BR>
     * 売付可能株数 == 0の場合（getミニ株保有株数() - getミニ株注文中株数()）、<BR>
     * 例外をスローする<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00167<BR>
     * ==========================================================
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資売付注文入力リクエストデータオブジェクト
     * @@return WEB3MstkSellInputResponse
     */
    protected WEB3MstkSellInputResponse getInputScreen(WEB3MstkSellInputRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getInputScreen(WEB3MstkSellInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate()
        l_request.validate();
        
        //2) get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //3) validateミニ株注文(補助口座)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        l_orderManager.validateMiniStockOrder(l_subAccount);
        
        //4) validate取引銘柄（ミニ株）(補助口座, String, boolean)
        WEB3EquityTradedProduct l_tradedProduct = 
            l_orderManager.validateMiniStockTradedProduct(l_subAccount, l_request.productCode, true);
            
        //5) getProduct()
        Product l_product = l_tradedProduct.getProduct();
        
        //6) getMarket()
        Market l_market = l_tradedProduct.getMarket();
        
        //7) validateミニ株重複注文(補助口座, 取引銘柄)
        l_orderManager.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
        //8) getミニ株保有株数(long, long, long)
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        double l_dblQuantity = l_positionManager.getMiniStockQuantity(
            l_subAccount.getAccountId(), 
            l_subAccount.getSubAccountId(), 
        l_product.getProductId());
            
        //9) getミニ株注文中株数(long, long, long, boolean)
        double l_dblOrderingQuantity = l_orderManager.getMiniStockOrderingQuantity(
            l_subAccount.getAccountId(), 
            l_subAccount.getSubAccountId(), 
            l_product.getProductId(),
            true);
            
        //10) 売付可能株数 == 0の場合（getミニ株保有株数() - getミニ株注文中株数()）、例外をスローする
        double l_dblSellQuantity = l_dblQuantity - l_dblOrderingQuantity; 
        if(l_dblSellQuantity == 0)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00167,STR_METHOD_NAME);
                
        }
        //11)  isミニ株取引終了警告(部店)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);

        //12)isインサイダー警告表示(補助口座 : 補助口座, 銘柄ID : long)
        boolean l_insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());
        
        //13) createResponse()
        WEB3MstkSellInputResponse l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
        
        //14) (*2) プロパティセット
        l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellQuantity);
        EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
        l_response.productName = l_productRow.getStandardName();
        l_response.marketCode = l_market.getMarketCode();
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * （validate注文）。<BR>
     * <br>
     * 株式ミニ投資売付注文発注審査を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株売付サービス）validate注文」参照。<BR>
     * @@param l_request (リクエストデータ)
     * 株式ミニ投資売付注文確認リクエストデータオブジェクト
     * @@return WEB3MstkSellConfirmResponse
     */
    protected WEB3MstkSellConfirmResponse validateOrder(WEB3MstkSellConfirmRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateOrder(WEB3MstkSellConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate()
        l_request.validate();
        
        //2) get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //3) get代理入力者()
        Trader l_trader = this.getTrader();
        
        //4) validate取引銘柄（ミニ株）(補助口座, String, boolean)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingMod.getOrderManager();
        WEB3EquityTradedProduct l_tradedProduct = 
            l_orderManager.validateMiniStockTradedProduct(l_subAccount, l_request.productCode, true);
        
        //5) create株式ミニ投資注文内容(Trader, boolean, String, String, double)
        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_request.orderQuantity);
        double l_dblOrderQuantity;
        if(!l_blnIsNumber)
        {
            l_dblOrderQuantity = 0;
        }
        else
        {
            l_dblOrderQuantity = Double.parseDouble(l_request.orderQuantity);
        }
        WEB3NewMiniStockOrderSpec l_orderSpec = WEB3NewMiniStockOrderSpec.createNewMiniStockOrderSpec(
            l_trader, 
            false, 
            l_request.productCode, 
            l_tradedProduct.getMarket().getMarketCode(),
            l_dblOrderQuantity);
        //6) validateミニ株売付注文(補助口座, 株式ミニ投資注文内容)
        NewOrderValidationResult l_validationResult = l_orderManager.validateMiniStockSellOrder(l_subAccount, l_orderSpec);
        if(l_validationResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(l_validationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }
        
        //7) getログインチャネル()
        String l_strLoginChannel = this.getLoginChannel();
        
        //8) get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //9) 手数料()
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        
        //10)  (*1) プロパティセット
        l_commission.setOrderChannel(l_strLoginChannel);
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.MINI_STOCK);
        l_commission.setPayType(WEB3EquityRepaymentDivOtherDef.OTHER);
        
        //11) calc概算受渡代金（ミニ株）(手数料 : 手数料, 補助口座 : SubAccount, 取引銘柄 :
        //    取引銘柄, 株数 : double, is売注文 : boolean, 計算単価 : double, is拘束考慮 : boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = l_orderManager.calcMiniStockEstimatedDeliveryAmount(
            l_commission,
            l_subAccount,
            l_tradedProduct,
            l_dblOrderQuantity,
            true,
            Double.NaN,
            true);

        //14) isミニ株取引終了警告(部店)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        boolean l_blnMessageSuspensionFlag = WEB3GentradeTradingTimeManagement.isMiniStockSuspension(l_branch);
            
        
        //------------
        //時価情報取得
        //------------
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager) l_tradingMod.getProductManager();
        WEB3EquityProductQuote l_equityProductQuote = l_productManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);
        String l_strQuoteTypeDiv = l_equityProductQuote.getQuoteTypeDiv();
        double l_dblCurrentPrice = l_equityProductQuote.getQuote();
        double l_dblChange = l_equityProductQuote.getComparedPreviousDay();
        Timestamp l_tsQuoteTime = l_equityProductQuote.getQuoteTime();        
         
        //19) createNewOrderId()
        long l_lngOrderId = l_orderManager.createNewOrderId();
        //20)isインサイダー警告表示(補助口座 : 補助口座, 銘柄ID : long)
        boolean l_insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());
        //21) createResponse()
        WEB3MstkSellConfirmResponse l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
        
        //22)  (*3) プロパティセット
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());
        l_response.commissionInfo = new WEB3MstkCommissionInfoUnit();
        l_response.commissionInfo.commissionCourse = null;
        l_response.commissionInfo.commission = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
        l_response.commissionInfo.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCalcUnitPrice());
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        l_response.orderId = "" + l_lngOrderId;
        l_response.messageSuspensionFlag = l_blnMessageSuspensionFlag;
        l_response.currentPriceDiv = l_strQuoteTypeDiv;
        l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);
        l_response.currentPriceTime = l_tsQuoteTime;
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * （submit注文）。<BR>
     * <br>
     * 株式ミニ投資売付注文登録処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（ミニ株売付サービス）submit注文」参照。
     * @@param l_request (リクエストデータ)<BR>
     * 株式ミニ投資売付注文完了リクエストデータオブジェクト
     * @@return WEB3MstkSellCompleteResponse
     */
    protected WEB3MstkSellCompleteResponse submitOrder(WEB3MstkSellCompleteRequest l_request) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " submitOrder(WEB3MstkSellCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate()
        l_request.validate();
        
        //2) get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //3) get代理入力者()
        Trader l_trader = this.getTrader();
        
        //4) validate取引銘柄（ミニ株）(補助口座, String, boolean)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        WEB3EquityTradedProduct l_tradedProduct = 
            l_orderManager.validateMiniStockTradedProduct(l_subAccount, l_request.productCode, true);
        
        //5) create株式ミニ投資注文内容(Trader, boolean, String, String, double)
        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_request.orderQuantity);
        double l_dblOrderQuantity;
        if(!l_blnIsNumber)
        {
            l_dblOrderQuantity = 0;
        }
        else
        {
            l_dblOrderQuantity = Double.parseDouble(l_request.orderQuantity);   
        }
        WEB3NewMiniStockOrderSpec l_orderSpec = WEB3NewMiniStockOrderSpec.createNewMiniStockOrderSpec(
            l_trader, 
            false, 
            l_request.productCode, 
            l_tradedProduct.getMarket().getMarketCode(),
            l_dblOrderQuantity);
        //6) validateミニ株売付注文(補助口座, 株式ミニ投資注文内容)
        NewOrderValidationResult l_validationResult = l_orderManager.validateMiniStockSellOrder(l_subAccount, l_orderSpec);
        if(l_validationResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(l_validationResult.getProcessingResult().getErrorInfo(), STR_METHOD_NAME);
        }
        
        //7) getログインチャネル()
        String l_strLoginChannel = this.getLoginChannel();
        
        //8) get発注日()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        
        //9) 手数料()
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
        
        //10)  (*1) プロパティセット
        l_commission.setOrderChannel(l_strLoginChannel);
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.MARKET_TRADING);
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.MINI_STOCK);
        l_commission.setPayType(WEB3EquityRepaymentDivOtherDef.OTHER);
        
        //11) calc概算受渡代金（ミニ株）(手数料 : 手数料, 補助口座 : SubAccount, 取引銘柄 : 取引銘柄, 株数 : double, is売注文 : boolean, 計算単価 : double, is拘束考慮 : boolean)
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = l_orderManager.calcMiniStockEstimatedDeliveryAmount(
            l_commission,
            l_subAccount,
            l_tradedProduct,
            l_dblOrderQuantity,
            true,
            Double.parseDouble(l_request.checkPrice),
            true);
        
        //12) 株式ミニ投資注文更新インタセプタ(株式ミニ投資注文内容, 手数料, 概算受渡代金計算結果)
        WEB3MiniStockOrderUpdateInterceptor l_intercepter = new WEB3MiniStockOrderUpdateInterceptor(
            l_orderSpec, 
            l_commission, 
            l_estimatedDeliveryPrice);

        //14) setThreadLocalPersistenceEventInterceptor(EqTypeOrderManagerPersistenceEventInterceptor)
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_intercepter);
        
        //15) submitNewMiniStockOrder(SubAccount, EqTypeNewMiniStockOrderSpec, long, String, boolean)
        boolean l_blnOrderId = WEB3StringTypeUtility.isInteger(l_request.orderId);
        long l_lngOrderId;
        if(!l_blnOrderId)
        {
            l_lngOrderId = 0;
        }
        else
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);    
        }
        EqTypeOrderSubmissionResult l_orderSubmissionResult = 
            l_orderManager.submitNewMiniStockOrder(l_subAccount, l_orderSpec, l_lngOrderId, l_request.password, true);
        
        if(l_orderSubmissionResult.getProcessingResult().isFailedResult())
        {
            throw new WEB3BaseException(l_orderSubmissionResult.getProcessingResult().getErrorInfo(),STR_METHOD_NAME);
        }

        //16) isインサイダー警告表示(補助口座 : 補助口座, 銘柄ID : long)
        boolean l_insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_tradedProduct.getProduct().getProductId());
       
        //17) createResponse()
        WEB3MstkSellCompleteResponse l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
        
        //18) (*3) プロパティセット
        l_response.lastUpdatedTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
        l_response.orderActionId = l_request.orderId;
        l_response.insiderWarningFlag = l_insiderWarningFlag;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * （create銘柄コード名称）。<br>
     * <br>
     * 　@指定口座の保持するミニ株保有資産の銘柄コードと銘柄名の<br>
     * 一覧を作成し、レスポンスデータにセットする。 <br>
     * 該当データが存在しない場合にはnullをセットする。 <br>
     * <br>
     * シーケンス図 <br>
     * 「（ミニ株売付サービス）create銘柄コード名称」参照。
     * @@param l_response (レスポンスデータ)<br>
     * 株式ミニ投資売付一覧レスポンスデータオブジェクト
     * @@param l_subAccount (補助口座)<br>
     * 補助口座オブジェクト
     * @@param l_request (リクエストデータ)
     * 株式ミニ投資売付一覧リクエストデータオブジェクト
     */
    protected void createProductCodeName(
        WEB3MstkSellListResponse l_response, 
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MstkSellListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createProductCodeName()";
        log.entering(STR_METHOD_NAME);
        
        //2) getミニ株保有資産一覧(補助口座, ProductTypeEnum)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        List l_lisAssets = l_positionManager.getMiniStockAssets(l_subAccount, ProductTypeEnum.EQUITY);
        
        //3) HashMap()
        Map l_hashMap = new HashMap();
        List l_listNewAssets = new ArrayList();
        int l_intLength = l_lisAssets.size();
        
        //4) get保有資産一覧()の戻り値の数分LOOP処理
        for(int i = 0; i < l_intLength; i++)
        {
            Asset l_asset = (Asset)l_lisAssets.get(i);
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_asset.getProduct();
            if(!l_hashMap.containsKey("" + l_product.getProductId()))
            {
                //8) 株式ミニ投資銘柄コード名称()
                WEB3MstkProductCodeNameUnit l_productCodeNameUnit = new WEB3MstkProductCodeNameUnit();
                //9) (*1.2) プロパティセット
                l_productCodeNameUnit.productCode = l_product.getProductCode();
                EqtypeProductRow l_productRow = (EqtypeProductRow) l_product.getDataSourceObject();
                l_productCodeNameUnit.productName = l_productRow.getStandardName();
                //10) put(arg0（=保有資産.銘柄ＩＤ.toString()） : Object（=株式ミニ投資銘柄コード名称オブジェクト）, arg1 : Object)
                l_hashMap.put("" + l_product.getProductId(), l_productCodeNameUnit);
                l_listNewAssets.add(l_productCodeNameUnit);
            }  
        }
        if(l_intLength != 0)
        {
        	WEB3MstkProductCodeNameUnit[] l_productCodeNameUnit = new WEB3MstkProductCodeNameUnit[l_listNewAssets.size()];
        	l_listNewAssets.toArray(l_productCodeNameUnit);
        	l_response.productCodeNames = l_productCodeNameUnit;
		}
		else
		{
			l_response.productCodeNames = null;
		}
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （create売付明細）。<br>
     * <br>
     * 　@指定口座の保持するミニ株保有資産より、売付明細を作成し、<br>
     * レスポンスデータにセットする。 <br>
     * 該当データが存在しない場合にはnullをセットする。 <br>
     * <br>
     * シーケンス図 <br>
     * 「（ミニ株売付サービス）create売付明細」参照。
     * @@param l_response (レスポンスデータ)<br>
     * 株式ミニ投資売付一覧レスポンスデータオブジェクト
     * @@param l_subAccount (補助口座)<br>
     * 補助口座オブジェクト
     * @@param l_request (リクエストデータ)<br>
     * 株式ミニ投資売付一覧リクエストデータオブジェクト
     */
    protected void createSellUnit(
        WEB3MstkSellListResponse l_response, 
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MstkSellListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSellUnit()";
        log.entering(STR_METHOD_NAME);
        
        //2) getInstitution()
        Institution l_institution = l_subAccount.getInstitution();
        
        //3) ArrayList()
        List l_list = new ArrayList();
        int l_intLength = 0;
        if(l_request.productCode != null)
        {
            l_intLength = 1;
        }
        else if(l_response.productCodeNames != null)
        {
            l_intLength = l_response.productCodeNames.length;
        }
        else
        {
			l_intLength = 0;
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingMod.getProductManager();
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        
        for(int i = 0; i < l_intLength; i++)
        {
            String l_strProductCode;
            if(l_request.productCode != null)
            {
                l_strProductCode = l_request.productCode;
            }
            else
            {
                
                l_strProductCode = l_response.productCodeNames[i].productCode;
            }
            //5) getProduct(証券会社 : Institution, 銘柄コード : String)
            WEB3EquityProduct l_equityProduct;
            try
            {
                l_equityProduct = (WEB3EquityProduct)l_productManager.getProduct(l_institution, l_strProductCode);
            }
            catch(NotFoundException l_ex)
            {
				if(l_request.productCode != null)
				{
                	throw new WEB3BusinessLayerException(
                		WEB3ErrorCatalog.BUSINESS_ERROR_00717,
                		this.getClass().getName() + "." + STR_METHOD_NAME);
				}
				else
				{
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80005,
						this.getClass().getName() + "." + STR_METHOD_NAME);
					
				}
            }
            //6) getProductId()
            long l_lngProductId = l_equityProduct.getProductId();
            //7) getStandardName()
            EqtypeProductRow l_productRow = (EqtypeProductRow) l_equityProduct.getDataSourceObject();
            String l_strStandardName = l_productRow.getStandardName();
            //8) getミニ株市場()
            Market l_market = l_equityProduct.getMiniStockMarket();
            //9) getミニ株保有株数(long, long, long)
            double l_dblMstkQuantity = 
                l_positionManager.getMiniStockQuantity(l_subAccount.getAccountId(), l_subAccount.getSubAccountId(), l_lngProductId);
            
            //10) getTradedProduct(arg0（=getProduct()） : Product, arg1（=getミニ株市場()） : Market)
            WEB3EquityTradedProduct l_tradedProduct = null;
            if (l_market != null)
            {
                try
                {
                    l_tradedProduct = 
                        (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_equityProduct, l_market);
                }
                catch(NotFoundException l_nfe) {}
            }
            
            //14) 株式ミニ投資売付明細()
            WEB3MstkSellUnit l_unit = new WEB3MstkSellUnit();
            
            //12) getミニ株注文中株数(long, long, long, boolean)
            double l_dblBuyOrderQuantity = l_orderManager.getMiniStockOrderingQuantity(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                false);
                
            //13) getミニ株注文中株数(long, long, long, boolean)
            double l_dblSellOrderQuantity = l_orderManager.getMiniStockOrderingQuantity(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                true); 
            
            // プロパティ初期化
			l_unit.sellPossFlag = true;
			
            //15) プロパティセット
            l_unit.productCode = l_equityProduct.getProductCode();
            l_unit.productName = l_strStandardName;
            if (l_market == null)
            {
				l_unit.marketCode = null;
				l_unit.sellPossFlag = false;
            }
            else
            {
				l_unit.marketCode = l_market.getMarketCode();
            }
            l_unit.balanceQuantity = WEB3StringTypeUtility.formatNumber(l_dblMstkQuantity);
            l_unit.buyOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblBuyOrderQuantity);
            l_unit.sellOrderedQuantity = WEB3StringTypeUtility.formatNumber(l_dblSellOrderQuantity);
            
            //11) validateミニ株重複注文(補助口座, 取引銘柄)
            double l_dblDeledQuantity = 0;
            if (l_tradedProduct != null)
            {
                try
                {
                    l_orderManager.validateMiniStockDuplicateOrder(l_subAccount,l_tradedProduct);
                    l_dblDeledQuantity = l_dblMstkQuantity - l_dblSellOrderQuantity;                
                }
                catch(WEB3BaseException l_ex) {}
            }
            l_unit.sellPossQuantity = WEB3StringTypeUtility.formatNumber(l_dblDeledQuantity);
            if (l_dblDeledQuantity == 0)
            {
				l_unit.sellPossFlag = false;
            }
            
            //16) add(arg0（=株式ミニ投資売付明細オブジェクト）
            l_list.add(l_unit);
        }
        //17) toArray()
        //18) sort(obj（売付一覧List.toArray()） : Object[], com（=株式ミニ投資売付明細Comparator[]） : Comparator[])
        int l_intKeysLength = l_request.sortKeys.length;
        WEB3MstkSellUnitComparator[] l_comparator = new WEB3MstkSellUnitComparator[l_intKeysLength];
        for(int i = 0; i < l_intKeysLength; i++)
        {
            l_comparator[i] = new WEB3MstkSellUnitComparator(l_request.sortKeys[i].ascDesc, l_request.sortKeys[i].keyItem);
        }
        WEB3MstkSellUnit[] l_units = new WEB3MstkSellUnit[l_list.size()];
        l_list.toArray(l_units);
        WEB3ArraysUtility.sort(l_units, l_comparator);
        int l_intPageSize1 = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex1 = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_units, l_intPageIndex1, l_intPageSize1);
        
        //プロパティセット
        if(l_intLength != 0)
        {
			l_response.sellList = (WEB3MstkSellUnit[])l_pageIndexInfo.getArrayReturned(WEB3MstkSellUnit.class);
        }
        else
        {
			l_response.sellList = null;
        }
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();
        
    }
}
@
