head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcEstimatedAmountCalServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス受渡代金計算サービスImpl(WEB3TrialCalcEstimatedAmountCalServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate.stdimpls;

import java.text.DecimalFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.quoteadaptor.RealType;
import webbroker3.trialcalc.define.WEB3TrialCalcDealingTypeDef;
import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputResponse;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcClientRequestService;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcEstimatedAmountCalService;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;

/**
 * （計算サービス受渡代金計算サービスImpl）<BR>
 * <BR>
 * 受渡代金計算サービス実装クラス。<BR>
 * <BR>
 * WEB3TrialCalcEstimatedAmountCalServiceImpl<BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public class WEB3TrialCalcEstimatedAmountCalServiceImpl
    extends WEB3TrialCalcClientRequestService
    implements WEB3TrialCalcEstimatedAmountCalService
{
    /**
     * log WEB3LogUtility.
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcEstimatedAmountCalServiceImpl.class);

    /**
     * @@roseuid 41C813F503C9
     */
    public WEB3TrialCalcEstimatedAmountCalServiceImpl()
    {

    }

    /**
    * 受渡代金計算サービス処理を実施する。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Delivery price calculation service processing is executed. <BR>
    * @@param l_request - リクエストデータ。
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 4190A60201D5
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3TrialCalcEstimatedAmountCalServiceImpl.execute()";
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3TrialCalcDeliveryPriceCalcInputRequest)
        {
            l_response =
                this.getInputDisplay((WEB3TrialCalcDeliveryPriceCalcInputRequest) l_request);
        } else if (l_request instanceof WEB3TrialCalcDeliveryPriceCalcRequest)
        {
            l_response =
                this.calcEstimatedAmount((WEB3TrialCalcDeliveryPriceCalcRequest) l_request);
        }

        return l_response;
    }

    /**
     * （get入力画面）<BR>
     * <BR>
     * 受渡代金計算入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（受渡代金計算サービス）get入力画面」参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getInputDisplay<BR>
     * Refer to sequence chart "(Delivery price calculation service) getInputDisplay".
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4192E7C1039C
     */
    protected WEB3TrialCalcDeliveryPriceCalcInputResponse
        getInputDisplay(WEB3TrialCalcDeliveryPriceCalcInputRequest l_request)
        throws WEB3BaseException
        {
        final String STR_METHOD_NAME =
            "getInputDisplay(WEB3TrialCalcDeliveryPriceCalcInputRequest)";

        WEB3TrialCalcDeliveryPriceCalcInputResponse l_response = null;
        WEB3GentradeBranch l_gentradeBranch = null;
        WEB3GentradeSubAccount l_subAccount = null;

        //1.1 validateOrderAccept()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.2 getSubAccount()
        l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 getWeb3GenBranch()
        //WEB3GentradeSubAccount l_subAccount = null;
        l_gentradeBranch = l_subAccount.getWeb3GenBranch();

        // 1.4 getHandlingPossibleMarket()
        String[] l_marketCodeList =
            WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                l_gentradeBranch,
                ProductTypeEnum.EQUITY);

        //1.5 createResponse()
        l_response = (WEB3TrialCalcDeliveryPriceCalcInputResponse) l_request.createResponse();

        //1.6 setPropety
        l_response.marketCodeList = l_marketCodeList;

        return l_response;
    }

    /**
     * （calc受渡代金）<BR>
     * <BR>
     * 受渡代金計算サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（受渡代金計算サービス）calc受渡代金」参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * calcEstimatedAmount<BR>
     * Refer to sequence chart "(Delivery price calculation service)
     * calcEstimatedAmount".<BR>
     * @@param     l_request リクエストデータ - リクエストデータ
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4192E7C103AB
     */
    protected WEB3TrialCalcDeliveryPriceCalcResponse
        calcEstimatedAmount(WEB3TrialCalcDeliveryPriceCalcRequest l_request)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = 
            "calcEstimatedAmount(WEB3TrialCalcDeliveryPriceCalcRequest)";
        log.entering(STR_METHOD_NAME);

        // (U01343，U01357) 2005.03.30
        // 全部、コーディングし直しました。

        // 取引時間チェック
        WEB3GentradeTradingTimeManagement.validateOrderAccept();


        // リクエストデータチェック
        l_request.validate();


        // 補助口座取得
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)this.getSubAccount();


        // 取引モジュール取得		
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityProduct l_product = null; 
        
		try {
			l_product = (WEB3EquityProduct)l_productManager.getProduct(
			        l_subAccount.getInstitution(),
			        l_request.productCode
			        );
		} catch (NotFoundException e) {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "（株式銘柄）銘柄コード[" + l_request.productCode + "]"
                );
		}
                
        
        // 市場コード，取引銘柄の取得
        // 　@－市場指定ありの場合は、入力市場／入力銘柄で取引銘柄を取得。
        // 　@－市場指定なしの場合は、優先市場／入力銘柄で取引銘柄を取得。
        // 　@－ミニ株の場合は、ミニ市場／入力銘柄で取引銘柄を取得。
        // 　@－ミニ株で市場指定無しの場合は、ミニ市場／入力銘柄で取引銘柄を取得。
        String l_marketCode = null;
        WEB3EquityTradedProduct l_tradedProduct = null;
        WEB3EquityProductQuote l_quote = null; 
        
        try 
        {
            if (l_request.equityMiniDiv.equals(
                WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION)
                )
            {
                Market l_ministockMarket = l_product.getMiniStockMarket();
                if (l_ministockMarket == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00718,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "銘柄コード[" + l_request.productCode + "]"
                        );
                }
                l_marketCode = l_ministockMarket.getMarketCode();
				l_tradedProduct = (WEB3EquityTradedProduct) 
                    l_productManager.getTradedProduct(
    				    l_product, 
    				    l_ministockMarket
    				    );
                if (l_request.marketCode == null)
                {
                    MainAccountRow l_accountRow 
                        = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
                    WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(l_accountRow);
                    RealType l_realType;
                    if (l_account.isRealCustomer())
                    {
                        l_realType = RealType.REAL;
                    }
                    else
                    {
                        l_realType = RealType.DELAY;
                    }
                    l_quote = l_productManager.getProductQuote(
                            l_tradedProduct, l_realType, true);
                }
            } else
            {
                if (l_request.marketCode == null)
                {
                    l_quote = l_product.getPrimaryMarketProductQuote(l_subAccount);
                    l_marketCode = l_quote.getMarketCode();
                } else
                {
                    l_marketCode = l_request.marketCode;
                }
				WEB3GentradeTradingTimeManagement.resetMarketCode(l_marketCode);
				l_tradedProduct = (WEB3EquityTradedProduct)
                    l_productManager.getTradedProduct(
    				    l_subAccount.getInstitution(),
    				    l_request.productCode,
    				    l_marketCode
    				    );
            }
        } catch (NotFoundException e) {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00638,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コード[" + l_request.productCode + "]" +
                "市場コード[" + l_marketCode + "]"
                );
        }


        // 計算単価の取得
        // 　@－単価指定ありの場合は、入力単価を使用。
        // 　@－単価指定なしの場合は、時価を使用。
        double l_orderPrice = 0;

		if (l_request.orderPrice == null)
		{
            if (l_quote == null) {
                l_quote = l_tradedProduct.getProductQuote(l_subAccount);
            }
            l_orderPrice = l_quote.getQuote();
		} else
		{
            l_orderPrice = Double.parseDouble(l_request.orderPrice);
		}


        // 手数料オブジェクト生成
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
                l_subAccount,
                l_marketCode,
                WEB3GentradeTradingTimeManagement.getOrderBizDate(),
                l_request.orderForm,
                WEB3MarginTradingDivDef.DEFAULT,
                0,
                OrderCategEnum.UNDEFINED
                );


        // 概算受渡代金計算
		WEB3EquityOrderManager l_orderManager 
			= (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = null;

        boolean isSellorder = false;
        if (WEB3TrialCalcDealingTypeDef.SELL.equals(l_request.dealingType))
        {
            isSellorder = true;
        }


        if (l_request.equityMiniDiv.equals(
            WEB3TrialCalcEquityMiniDivDef.MINI_STOCK_DIVISION)
            )
        {
            l_commission.setIsLimitPrice(false);
            l_commission.setCommissionProductCode(
                WEB3CommisionProductCodeDef.MINI_STOCK
                );
            l_deliveryPrice = l_orderManager.calcMiniStockEstimatedDeliveryAmount(
                    l_commission,
                    l_subAccount,
                    l_tradedProduct,
                    Double.parseDouble(l_request.orderQuantity),
                    isSellorder,
                    l_orderPrice,
                    false);
        } else
        {
            l_commission.setIsLimitPrice(true);
            l_deliveryPrice = l_orderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_orderPrice,
                    l_subAccount,
                    l_tradedProduct,
                    Double.parseDouble(l_request.orderQuantity),
                    isSellorder,
                    0,
                    0,
                    false,
                    false);
        }
        
        
        // レスポンスデータ編集
        WEB3TrialCalcDeliveryPriceCalcResponse l_response = 
            new WEB3TrialCalcDeliveryPriceCalcResponse(l_request);
        DecimalFormat l_dFormat = new DecimalFormat("0");
        
        l_response.productName = l_product.getStandardName();
        if (l_quote != null)
        {
            l_response.currentPriceGetDiv = l_quote.getQuoteFromDiv();
            l_response.currentPriceTime = l_quote.getQuoteTime();
            l_response.currentPrice = l_dFormat.format(l_quote.getQuote());
        }
        if (l_request.marketCode == null)
        {
            l_response.marketCode = l_marketCode;   
        }
        l_response.commission = l_dFormat.format(
            l_deliveryPrice.getCommissionFee()
            );
        l_response.commissionTax = l_dFormat.format(
            l_deliveryPrice.getCommissionFeeTax()
            );
        l_response.commissionCourse = l_commission.getCommissionCourseDiv();
        l_response.deliveryPrice = l_dFormat.format( 
            l_deliveryPrice.getEstimateDeliveryAmount()
            );

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
