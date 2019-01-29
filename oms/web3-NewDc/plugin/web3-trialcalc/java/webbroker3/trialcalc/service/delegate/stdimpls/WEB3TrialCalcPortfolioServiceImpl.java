head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオサービスImpl(WEB3TrialCalcPortfolioServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;

import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ListRangeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;

import webbroker3.trialcalc.WEB3TrialCalcAppraisalPriceComparator;
import webbroker3.trialcalc.WEB3TrialCalcAppraisalProfitLossComparator;
import webbroker3.trialcalc.WEB3TrialCalcAppraisalProfitLossRateComparator;
import webbroker3.trialcalc.WEB3TrialCalcBuyAmountComparator;
import webbroker3.trialcalc.WEB3TrialCalcBuyPriceComparator;
import webbroker3.trialcalc.WEB3TrialCalcCurrentPriceComparator;
import webbroker3.trialcalc.WEB3TrialCalcInputSpecificDivisionComparator;
import webbroker3.trialcalc.WEB3TrialCalcMarketCodeComparator;
import webbroker3.trialcalc.WEB3TrialCalcProductCodeComparator;
import webbroker3.trialcalc.WEB3TrialCalcQuantityComparator;
import webbroker3.trialcalc.data.AccountPortfolioParams;
import webbroker3.trialcalc.data.AccountPortfolioRow;
import webbroker3.trialcalc.data.AccountPortfolioDao;
import webbroker3.trialcalc.data.AccountPortfolioProductParams;
import webbroker3.trialcalc.data.AccountPortfolioProductRow;
import webbroker3.trialcalc.define.WEB3TrialCalcDisplayTargetDef;
import webbroker3.trialcalc.define.WEB3TrialCalcInputCapitalGainDivDef;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayInputProductUnit;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayProductUnit;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditProductInputUnit;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditProductUnit;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioErrorProductUnit;
import webbroker3.trialcalc.message.WEB3TrialCalcSortKeyUnit;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcClientRequestService;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcPortfolioService;

/**
 * （計算サービスポートフォリオサービスImpl）<BR>
 * <BR>
 * ポートフォリオサービス実装クラス。<BR>
 * <BR>
 * @@author Dixit Deepankar
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioServiceImpl
    extends WEB3TrialCalcClientRequestService
    implements WEB3TrialCalcPortfolioService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioServiceImpl.class);

    /**
     * @@roseuid 41C7F1570025
     */
    public WEB3TrialCalcPortfolioServiceImpl()
    {

    }

    /**
     * ポートフォリオサービス処理を実施する。<BR>
     * <BR>
     * @@param l_request - リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 419332DB02E0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3TrialCalcPortfolioServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3TrialCalcPortfolioDisplayRequest)
            {
                l_response =
                    this.getPortfolioDisplay((WEB3TrialCalcPortfolioDisplayRequest) l_request);
            }
            else if (l_request instanceof WEB3TrialCalcPortfolioEditRequest)
            {
                l_response =
                    this.updatePortfolioEdit((WEB3TrialCalcPortfolioEditRequest) l_request);
            }
            else if (l_request instanceof WEB3TrialCalcPortfolioEditInputRequest)
            {
                l_response =
                    this.getPortfolioEditInput((WEB3TrialCalcPortfolioEditInputRequest) l_request);
            }
        }
        catch (DataQueryException l_dataQueryException)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dataQueryException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataQueryException.toString(),
                l_dataQueryException);
        }
        catch (DataNetworkException l_dataNetworkException)
        {
            String l_strMsg = "Error while aquiring the Data";
            log.error(l_strMsg, l_dataNetworkException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dataNetworkException.toString(),
                l_dataNetworkException);

        }
        catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(), l_notFoundException);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （getポートフォリオ表示）<BR>
     * <BR>
     * ポートフォリオ表示画面用に、<BR>
     * ポートフォリオを構成する銘柄毎に各種計算を行い一覧を作成し返す。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポートフォリオサービス）getポートフォリオ表示」参照。<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayResponse
     * @@exception NotFoundException NotFoundException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 419332DB02E2
     */
    protected WEB3TrialCalcPortfolioDisplayResponse getPortfolioDisplay(
        WEB3TrialCalcPortfolioDisplayRequest l_request)
        throws NotFoundException, DataQueryException, DataNetworkException, WEB3BaseException
    {
		final String STR_METHOD_NAME =
			"getPortfolioDisplay(WEB3TrialCalcPortfolioEditRequest l_request)";

		final BigDecimal HUNDRED = new BigDecimal(100);
		final int WEB3_NUMBER_LENGTH = 12;
		final int APPRAISAL_PROFIT_LOSS_RATE_SCALE = 2;

        log.entering(STR_METHOD_NAME);

        // 1.1 Validate the order.
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // 1.2 Validate All portfolioEditProductUnit
        l_request.validate();

        /* 1.3
         * If l_request.displayTarget == null
         *  get last Display Target of AccountPortfolio(l_strPortfolioCode)
         * else portfolioRegistrationOnly
         */
		String l_strDisplayTarget = l_request.displayTarget;
		AccountPortfolioRow l_accountPortfolioRow =
		    this.getAccountPortfolio(l_request.portfolioCode);
        if (l_request.displayTarget == null)
        {
            if (l_accountPortfolioRow != null)
            {
                l_strDisplayTarget =
                    l_accountPortfolioRow.getListRange();
            }
            else
            {
                l_strDisplayTarget = WEB3TrialCalcDisplayTargetDef.ONLY_PORTFOLIO;
            }
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityProductManager l_productManager =
		    (WEB3EquityProductManager) l_tradingModule.getProductManager();
		WEB3EquityBizLogicProvider l_equityBizLogicProvider =
            (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();

        // Get Sub Account and Institution.
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        Institution l_institution = l_subAccount.getInstitution();

        // Get TrialCalcPortfolioDisplayInputProductUnit from Request.
		WEB3TrialCalcPortfolioDisplayInputProductUnit[] l_arrDisplayInputProductUnit =
		    l_request.portfolioDisplayInputProductUnit;
		int l_arrDisplayInputProductUnitCnt = 0;
        if (l_arrDisplayInputProductUnit != null)
        {
            l_arrDisplayInputProductUnitCnt = l_arrDisplayInputProductUnit.length;
        }

		List l_arrTrialCalcPortfolioDisplayProductUnit = new ArrayList();
		WEB3TrialCalcPortfolioDisplayProductUnit l_displayProductUnit;

        // 1.5 DisplayTarget is PORTFOLIO_REGISTRATION_ONLY or BOTH
        if (WEB3TrialCalcDisplayTargetDef.ONLY_PORTFOLIO.equals(l_strDisplayTarget)
            || WEB3TrialCalcDisplayTargetDef.BOTH.equals(l_strDisplayTarget))
        {
            // 1.5.1 Get AccountPortfolioProduct.
			AccountPortfolioProductRow[] l_accountPortfolioProductRow =
			    this.getAccountPortfolioProduct(l_request.portfolioCode);

            if (l_accountPortfolioProductRow != null)
            {
                int l_accountPortfolioProductRowCnt = l_accountPortfolioProductRow.length;
                for (int i = 0; i < l_accountPortfolioProductRowCnt; i++)
                {
                    // 1.5.1.1 Create Instance of WEB3TrialCalcPortfolioDisplayProductUnit
					l_displayProductUnit = new WEB3TrialCalcPortfolioDisplayProductUnit();

                    // 1.5.1.2 Get Product
					WEB3EquityProduct l_product = (WEB3EquityProduct)
                        l_productManager.getProduct(l_accountPortfolioProductRow[i].getProductId());
                    String l_strProductCode = l_product.getProductCode();

					String l_strMarketCode = l_accountPortfolioProductRow[i].getMarketCode();
					BigDecimal l_dbBuyPrice = 
					    new BigDecimal(l_accountPortfolioProductRow[i].getBuyPrice());

                    // 1.5.1.3 Search in l_request.portfolioDisplayInputProductUnit
                    for (int j = 0; j < l_arrDisplayInputProductUnitCnt; j++)
                    {
						BigDecimal l_dbRequestBuyPrice = null;
                        if (l_arrDisplayInputProductUnit[j].buyPrice == null)
                        {
                        	// リクエスト.入力銘柄明細.買付単価指定なし時は、
                        	// 顧客別ポートフォリオ構成銘柄の設定に合わせ
                        	// 買付単価に0をセットし、DBのレコードとマッチングする
							l_dbRequestBuyPrice =
							    new BigDecimal(0);
                        }
                        else
                        {
							l_dbRequestBuyPrice =
								new BigDecimal(l_arrDisplayInputProductUnit[j].buyPrice);
                        }
						l_displayProductUnit.buyPrice = l_dbRequestBuyPrice.toString();

                        if (l_strProductCode.equals(l_arrDisplayInputProductUnit[j].productCode)
                            && l_strMarketCode.equals(
                                l_arrDisplayInputProductUnit[j].marketCode)
                            && l_dbBuyPrice.equals(l_dbRequestBuyPrice)
                            && l_arrDisplayInputProductUnit[j].inputCapitalGainDiv.equals(
                                WEB3TrialCalcInputCapitalGainDivDef.INPUT))
                        {
                            l_displayProductUnit.evaluationPrice =
                                l_arrDisplayInputProductUnit[j].evaluationPrice;
                            break;
                        }
                    }

                    // 1.5.2.4 Set All The property.
                    l_displayProductUnit.productCode = l_strProductCode;
                    EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_product.getDataSourceObject();
                    l_displayProductUnit.productName = l_eqtypeProductRow.getStandardName();
                    l_displayProductUnit.marketCode = l_strMarketCode;
                    if (l_accountPortfolioProductRow[i].getQuantityIsNull() == true)
                    {
						l_displayProductUnit.orderQuantity = null;
                    }
                    else
                    {
						l_displayProductUnit.orderQuantity =
							WEB3StringTypeUtility.formatNumber(l_accountPortfolioProductRow[i].getQuantity());
                    }
                    if (l_dbBuyPrice.doubleValue() == 0.0D)
                    {
						l_displayProductUnit.buyPrice = null;
                    }
                    else
                    {
						l_displayProductUnit.buyPrice = 
							WEB3StringTypeUtility.formatNumber(l_dbBuyPrice.doubleValue());
                    }
                    l_displayProductUnit.inputCapitalGainDiv =
                        WEB3TrialCalcInputCapitalGainDivDef.INPUT;
                    l_displayProductUnit.errorCode = null;

                    l_arrTrialCalcPortfolioDisplayProductUnit.add(l_displayProductUnit);
                }
            }
        }

        /* 1.6
         * DisplayTarget is SPECIFIC_ONLY or BOTH
         */
        if (WEB3TrialCalcDisplayTargetDef.ONLY_SPECIAL_ACCOUNT.equals(l_strDisplayTarget)
            || WEB3TrialCalcDisplayTargetDef.BOTH.equals(l_strDisplayTarget))
        {
            // 1.6.2 Get Assets will return List.
			WEB3EquityPositionManager l_positionManager =
			    (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

            String [] l_arrSearchStrContainer = {
                String.valueOf(TaxTypeEnum.IntValues.SPECIAL),
                String.valueOf(TaxTypeEnum.IntValues.SPECIAL_WITHHOLD)};
            String l_strSearchString = " and tax_type IN (?, ?) ";
            List l_lisAssets =
                l_positionManager.getAssets(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    l_strSearchString,
                    l_arrSearchStrContainer,
                    null);
            long l_lngProductId = 0;
            double l_dblBuyPrice = 0;
            int l_lisAssetsCnt = l_lisAssets.size();
            for (int i = 0; i < l_lisAssetsCnt; i++)
            {
				WEB3EquityAsset l_equityAsset = (WEB3EquityAsset)l_lisAssets.get(i);
                Product l_assetsProduct = l_equityAsset.getProduct();

                l_lngProductId = l_equityAsset.getProduct().getProductId();
				WEB3EquityProduct l_product = (WEB3EquityProduct)
                    l_productManager.getProduct(l_lngProductId);

                l_displayProductUnit = new WEB3TrialCalcPortfolioDisplayProductUnit();

                // 1.6.3.3 Calc EstimatedBookPrice
                l_dblBuyPrice =
                    l_equityBizLogicProvider.calcEstimatedBookPrice(
                        l_lngProductId,
                        l_subAccount,
                        l_equityAsset.getTaxType());

                String l_strProductCode = l_product.getProductCode();

                // 1.6.3.4 Search in l_request.portfolioDisplayInputProductUnit
                for (int j = 0; j < l_arrDisplayInputProductUnitCnt; j++)
                {
                    if (l_strProductCode.equals(l_arrDisplayInputProductUnit[j].productCode)
                        && l_arrDisplayInputProductUnit[j].inputCapitalGainDiv.equals(
                        WEB3TrialCalcDisplayTargetDef.ONLY_SPECIAL_ACCOUNT))
                    {
                        l_displayProductUnit.evaluationPrice =
                            l_arrDisplayInputProductUnit[j].evaluationPrice;
                        break;
                    }
                }

                // 1.6.3.5 Set All the property.
                l_displayProductUnit.productCode = l_strProductCode;
				EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_product.getDataSourceObject();
                l_displayProductUnit.productName = l_eqtypeProductRow.getStandardName();
				
				// 数量=保有資産.数量+保有資産.売付不能数量をセット。
				AssetParams l_assetParams = 
					(AssetParams)l_equityAsset.getDataSourceObject(); 
				double l_quantity = 
					l_equityAsset.getQuantity() + l_assetParams.getQuantityCannotSell(); 
                l_displayProductUnit.orderQuantity = 
                	WEB3StringTypeUtility.formatNumber(l_quantity);

                l_displayProductUnit.buyPrice =
                    WEB3StringTypeUtility.formatNumber(l_dblBuyPrice);
                l_displayProductUnit.inputCapitalGainDiv =
                    WEB3TrialCalcDisplayTargetDef.ONLY_SPECIAL_ACCOUNT;
                l_displayProductUnit.errorCode = null;

                l_arrTrialCalcPortfolioDisplayProductUnit.add(l_displayProductUnit);
            }
        }

        // Display 2
		BigDecimal l_dbTotalBuyAmount = new BigDecimal(0);
		BigDecimal l_dbTotalAppraisalPrice = new BigDecimal(0);
		BigDecimal l_dbTotalAppraisalProfitLoss = new BigDecimal(0);

        int l_arrTrialCalcPortfolioDisplayProductUnitCnt =
            l_arrTrialCalcPortfolioDisplayProductUnit.size();
        for (int i = 0; i < l_arrTrialCalcPortfolioDisplayProductUnitCnt; i++)
        {
            l_displayProductUnit = (WEB3TrialCalcPortfolioDisplayProductUnit)
                l_arrTrialCalcPortfolioDisplayProductUnit.get(i);
            String l_strProductCode = l_displayProductUnit.productCode;
            String l_strMarketCode = l_displayProductUnit.marketCode;

            // 1.2.1 If market code is not null
            if (l_strMarketCode != null)
            {
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
                
				WEB3EquityProductQuote l_equityProductQuote = null;
                String l_errCode = null;
				try
				{
					// 1.2.1.1 Get TradedProduct
					WEB3EquityTradedProduct l_equityTradedProduct =
					    (WEB3EquityTradedProduct) l_productManager.getTradedProduct(
					        l_institution,
					        l_strProductCode,
					        l_strMarketCode);
					
					// 1.2.1.2 Get ProductQuote.
					l_equityProductQuote =
					    l_equityTradedProduct.getProductQuote(l_subAccount);
				}
				catch (NotFoundException e)
				{
					l_errCode = "0007";
				}
				catch (WEB3BaseException e)
				{
                    // 指定市場は非上場
                    if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01367))
                    {
                        l_errCode = "0001";
                    }
                    // 時価取得エラー
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005))
                    {
                        l_errCode = "0004";
                    }
                    // その他
                    else
                    {
                        throw e;
                    }
				}
                if (l_errCode != null) {
                    l_displayProductUnit.errorCode = l_errCode;
                    continue;
                }

                // 1.2.1.3.1 If ProductQuote not null
                if (l_equityProductQuote != null)
                {
                    l_displayProductUnit.currentPrice =
					    WEB3StringTypeUtility.formatNumber(l_equityProductQuote.getQuote());
                    l_displayProductUnit.currentPriceTime = l_equityProductQuote.getQuoteTime();
                    l_displayProductUnit.currentPriceGetDiv = l_equityProductQuote.getQuoteFromDiv();
                }
            }
            else
            {
				WEB3EquityProductQuote l_equityProductQuote = null;
                String l_errCode = null;
				try
				{
					// 1.2.2.1 Get Product
					WEB3EquityProduct l_product =
					    (WEB3EquityProduct) l_productManager.getProduct(
					        l_institution, l_strProductCode);
					
					// 1.2.2.2 Get PrimaryMarketProductQuote
					l_equityProductQuote =
						l_product.getPrimaryMarketProductQuote(
					        (WEB3GentradeSubAccount) l_subAccount);
				}
				catch (NotFoundException e)
				{
                    l_errCode = "0006";
				}
				catch (WEB3BaseException e)
				{
                    // 指定市場は非上場
                    if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01367))
                    {
                        l_errCode = "0001";
                    }
                    // 優先市場なし && 取引銘柄なし
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01365))
                    {
                        l_errCode = "0001";
                    }
                    // 優先市場一覧に取扱市場なし
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01366))
                    {
                        l_errCode = "0003";
                    }
                    // 時価取得エラー
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005))
                    {
                        l_errCode = "0004";
                    }
                    // その他
                    else
                    {
                        throw e;
                    }
				}
                if (l_errCode != null) {
                    l_displayProductUnit.errorCode = l_errCode;
                    continue;
                }

                // 1.2.2.3 If PrimaryMarketProductQuote is not null
                if (l_equityProductQuote != null)
                {
                    // 1.2.2.3.1 Set All the Property
                    l_displayProductUnit.currentPrice =
					    WEB3StringTypeUtility.formatNumber(l_equityProductQuote.getQuote());
                    l_displayProductUnit.currentPriceTime = l_equityProductQuote.getQuoteTime();
                    l_displayProductUnit.currentPriceGetDiv = l_equityProductQuote.getQuoteFromDiv();

                    // 1.2.2.3.2 Set marketCode
                    l_displayProductUnit.marketCode = l_equityProductQuote.getMarketCode();
                }
            }
            // 1.2.4 If evaluationPrice is null then set CurrentPrice
            if (l_displayProductUnit.evaluationPrice == null)
            {
                l_displayProductUnit.evaluationPrice = l_displayProductUnit.currentPrice;
            }

            // 1.2.5 Wehn orderQuantity and currentPrice are not null
            if ((l_displayProductUnit.orderQuantity != null)
                && (l_displayProductUnit.currentPrice != null))
            {
                if (!WEB3StringTypeUtility.isNumber(
                	l_displayProductUnit.evaluationPrice))
                {
                    l_displayProductUnit.errorCode = "1007";
                    continue;
                }
                else
                {
					double l_dEvPrice = 
						Double.parseDouble(l_displayProductUnit.evaluationPrice);
	                if (l_dEvPrice <= 0)
	                {
	                    l_displayProductUnit.errorCode = "1008";
	                    continue;
	                }
	                else if (l_dEvPrice >= 100000000)
	                {
	                    l_displayProductUnit.errorCode = "1009";
	                    continue;
	                }
                }
                // 1.2.5.2 Reset MarketCode
                WEB3GentradeTradingTimeManagement.resetMarketCode(l_displayProductUnit.marketCode);

                // 1.2.5.3 Get OrderBizDate
                Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

                // 1.2.5.4 Create Commission
				WEB3GentradeCommission l_gentradeCommission =
                    l_equityBizLogicProvider.createCommission(
                        l_subAccount,
				        l_displayProductUnit.marketCode,
                        l_datOrderBizDate,
                        l_request.orderForm,
                        WEB3MarginTradingDivDef.DEFAULT,
                        0,
                        OrderCategEnum.ASSET
                        );

                // 1.2.5.5 Set IsLimitPrice with true
                l_gentradeCommission.setIsLimitPrice(true);

				// 1.2.5.8 Calculate BuyAmount
				BigDecimal l_dbOrderQuantity = 
					new BigDecimal(l_displayProductUnit.orderQuantity);
				BigDecimal l_dbBuyPrice = null;
				if (l_displayProductUnit.buyPrice == null)
				{
					// 買付単価設定なし時は、時価を使用
					l_dbBuyPrice =
					    new BigDecimal(l_displayProductUnit.currentPrice);
				}
				else
				{
					l_dbBuyPrice = 
						new BigDecimal(l_displayProductUnit.buyPrice);
                    if (l_dbBuyPrice.doubleValue() == 0D)
                    {
                        l_displayProductUnit.buyPrice = null;
                    }
				}
				BigDecimal l_dbBuyAmount =
                	(l_dbOrderQuantity).multiply(l_dbBuyPrice);

                // 入力分のみ買付分に対する委託手数料を加算
                // （特定分は簿価に含まれているので加算不要）
                if (l_dbBuyAmount.doubleValue() != 0D)
                {
                    if (l_displayProductUnit.inputCapitalGainDiv.equals(
                    	WEB3TrialCalcInputCapitalGainDivDef.INPUT)) 
                	{
    					// 諸費用計算用代金（買付代金 = 株数×買付単価）セット
    					l_gentradeCommission.setExpensesCalcAmount(
    						l_dbBuyAmount.doubleValue()
    						);
    
                        double l_dblCommisionTax = 0.0D;
    					try
    					{
    						// 1.2.5.7 Calculate Commission Fee.
    						l_equityBizLogicProvider.calcCommission(
    							l_gentradeCommission, 
    							l_subAccount
    							);
    						l_dblCommisionTax = l_equityBizLogicProvider.calcSalesTax(
    								l_gentradeCommission.getCommission(),
    								new Timestamp(l_datOrderBizDate.getTime()),
    								l_subAccount
    								);
    					}
    					catch (WEB3BaseException l_baseException)
    					{
    						// 0005 : Brokerage acquisition error
    						l_displayProductUnit.errorCode = "0005";
    						continue;
    					}
    
    					l_dbBuyAmount = 
    						l_dbBuyAmount.add(
    						    new BigDecimal(l_gentradeCommission.getCommission())).add(
    						    new BigDecimal(l_dblCommisionTax));
                    }
                    l_displayProductUnit.buyAmount =
                        WEB3StringTypeUtility.formatNumber(l_dbBuyAmount.doubleValue());
                }

                // 1.2.5.9 Calculate OrderQuantity X EvaluationPrice
				BigDecimal l_dbEvaluationPrice = 
					new BigDecimal(l_displayProductUnit.evaluationPrice);
				BigDecimal l_dbOrderQuantityXEvaluationPrice = 
					l_dbOrderQuantity.multiply(l_dbEvaluationPrice);
                l_gentradeCommission.setExpensesCalcAmount(
                    l_dbOrderQuantityXEvaluationPrice.doubleValue());

                double l_dblCommisionTax = 0.0D;
                try
                {
                    // 1.2.5.10 Calculate Commission Fee.
                    l_equityBizLogicProvider.calcCommission(
                    	l_gentradeCommission, 
                    	l_subAccount
                    	);
                    l_dblCommisionTax =
                        l_equityBizLogicProvider.calcSalesTax(
							l_gentradeCommission.getCommission(),
							new Timestamp(l_datOrderBizDate.getTime()),
                            l_subAccount
                            );
                }
                catch (WEB3BaseException l_baseException)
                {
                    // 0005 : Brokerage acquisition error
                    l_displayProductUnit.errorCode = "0005";
                    continue;
                }

                // 1.2.5.11 Calculate AppraisalPrice
                BigDecimal l_dbAppraisalPrice =
                    l_dbOrderQuantityXEvaluationPrice
                        .subtract(new BigDecimal(l_gentradeCommission.getCommission()))
                        .subtract(new BigDecimal(l_dblCommisionTax));
                l_displayProductUnit.appraisalPrice = 
					WEB3StringTypeUtility.formatNumber(l_dbAppraisalPrice.doubleValue());

                // 1.2.5.12 Calculate AppraisalProfitLoss
                if (l_dbBuyAmount.doubleValue() != 0D)
                {
                    BigDecimal l_dbAppraisalProfitLoss = l_dbAppraisalPrice.subtract(l_dbBuyAmount);
                    l_displayProductUnit.appraisalProfitLoss = 
    				    WEB3StringTypeUtility.formatNumber(l_dbAppraisalProfitLoss.doubleValue());
    
                    // 1.2.5.13 Calculate AppraisalProfitLossRate
                    // 評価額（％）
    				BigDecimal l_dbAppraisalProfitLossRate =
                        l_dbAppraisalProfitLoss
                        	.multiply(HUNDRED)
                            .divide(
                            	l_dbBuyAmount, 
                            	APPRAISAL_PROFIT_LOSS_RATE_SCALE,
                            	BigDecimal.ROUND_DOWN
                            	);
                    l_displayProductUnit.appraisalProfitLossRate =
    				WEB3StringTypeUtility.formatNumber(l_dbAppraisalProfitLossRate.doubleValue());
                    
                    l_dbTotalBuyAmount = l_dbTotalBuyAmount.add(l_dbBuyAmount);
                    l_dbTotalAppraisalProfitLoss =
                        l_dbTotalAppraisalProfitLoss.add(l_dbAppraisalProfitLoss);
                }

                l_dbTotalAppraisalPrice = l_dbTotalAppraisalPrice.add(l_dbAppraisalPrice);
            }
        }

        WEB3TrialCalcPortfolioDisplayProductUnit[] l_displayInputProductUnit = null;
        if (l_arrTrialCalcPortfolioDisplayProductUnit != null)
        {
            l_displayInputProductUnit = new WEB3TrialCalcPortfolioDisplayProductUnit[
                l_arrTrialCalcPortfolioDisplayProductUnitCnt];
            l_arrTrialCalcPortfolioDisplayProductUnit.toArray(l_displayInputProductUnit);
        }

        // Sort DisplayProductUnit
        this.sortDisplayProductUnit(l_displayInputProductUnit, l_request.sortKeys);

        // Create Response.
		WEB3TrialCalcPortfolioDisplayResponse l_trialCalcPortfolioDisplayResponse =
            (WEB3TrialCalcPortfolioDisplayResponse) l_request.createResponse();

        if (!l_dbTotalBuyAmount.equals(new BigDecimal(0)))
        {
			BigDecimal l_dbTotalAppraisalProfitLossRate =
                l_dbTotalAppraisalProfitLoss
					.divide(
						l_dbTotalBuyAmount,
			            WEB3_NUMBER_LENGTH, 
						BigDecimal.ROUND_DOWN)
					.multiply(HUNDRED)
					.divide(
					    new BigDecimal(1),
						APPRAISAL_PROFIT_LOSS_RATE_SCALE,
						BigDecimal.ROUND_DOWN);

            l_trialCalcPortfolioDisplayResponse.totalBuyAmount = 
				WEB3StringTypeUtility.formatNumber(l_dbTotalBuyAmount.doubleValue());
            l_trialCalcPortfolioDisplayResponse.totalAppraisalPrice =
			    WEB3StringTypeUtility.formatNumber(l_dbTotalAppraisalPrice.doubleValue());
            l_trialCalcPortfolioDisplayResponse.totalAppraisalProfitLoss =
			    WEB3StringTypeUtility.formatNumber(l_dbTotalAppraisalProfitLoss.doubleValue());
            l_trialCalcPortfolioDisplayResponse.totalAppraisalProfitLossRate =
			    WEB3StringTypeUtility.formatNumber(l_dbTotalAppraisalProfitLossRate.doubleValue());
        }

        l_trialCalcPortfolioDisplayResponse.displayTarget = l_strDisplayTarget;
        l_trialCalcPortfolioDisplayResponse.portfolioDisplayProductUnit = l_displayInputProductUnit;

        if (l_accountPortfolioRow != null)
        {
			this.updateAccountPortfolio(l_request.portfolioCode, l_strDisplayTarget);
        }

        log.exiting(STR_METHOD_NAME);
        return l_trialCalcPortfolioDisplayResponse;
    }

    /**
     * （updateポートフォリオ編集）<BR>
     * <BR>
     * ポートフォリオを構成する銘柄の一覧をDBに登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポートフォリオサービス）updateポートフォリオ編集」参照。<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditResponse
     * @@exception NotFoundException NotFoundException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 419332DB02F0
     */
    protected WEB3TrialCalcPortfolioEditResponse
        updatePortfolioEdit(WEB3TrialCalcPortfolioEditRequest l_request)
        throws DataQueryException, DataNetworkException, WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "updatePortfolioEdit(WEB3TrialCalcPortfolioEditRequest l_request)";

        log.entering(STR_METHOD_NAME);

        // Validate the order.
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // Validate All portfolioEditProductUnit
        l_request.validate();

        // Acquire a supplementary account from the login security service.
        SubAccount l_subAccount = this.getSubAccount();
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityProductManager l_productManager =
		    (WEB3EquityProductManager) l_tradingModule.getProductManager();
		Institution l_institution = l_subAccount.getInstitution();

        // Check all the WEB3TrialCalcPortfolioEditProductUnit of request.
		int l_intEditUnitCnt = 0;
		int l_intErrorUnitCnt = 0;
		WEB3TrialCalcPortfolioEditProductUnit[] l_arrEditUnit =
		    l_request.portfolioEditProductUnit;
		WEB3TrialCalcPortfolioErrorProductUnit[] l_arrErrorUnit = null;
        if (l_arrEditUnit != null)
        {
            l_intEditUnitCnt = l_arrEditUnit.length;

            // Add WEB3TrialCalcPortfolioErrorProductUnit object
			l_arrErrorUnit =
                new WEB3TrialCalcPortfolioErrorProductUnit[l_intEditUnitCnt];
        }

        for (int i = 0; i < l_intEditUnitCnt; i++)
        {

			WEB3TrialCalcPortfolioEditProductUnit l_curEditUnit =
                (WEB3TrialCalcPortfolioEditProductUnit) l_arrEditUnit[i];
			l_curEditUnit.validate();

            /*
             * Create WEB3TrialCalcPortfolioErrorProductUnit and set null to errorCode.
             * If errorCode is null means there is no error.
             */
			WEB3TrialCalcPortfolioErrorProductUnit l_trialCalcPortfolioErrorProductUnit =
			    new WEB3TrialCalcPortfolioErrorProductUnit();
            l_trialCalcPortfolioErrorProductUnit.productCode =
                l_curEditUnit.productCode;
            l_trialCalcPortfolioErrorProductUnit.orderQuantity =
                l_curEditUnit.orderQuantity;
            l_trialCalcPortfolioErrorProductUnit.buyPrice = l_curEditUnit.buyPrice;
            l_trialCalcPortfolioErrorProductUnit.marketCode = l_curEditUnit.marketCode;

            /*
             * If l_strOrderQuantity is inputted and not proper then set errorCode with
             * 1001 : orderQuantity is not a number
             * 1002 : orderQuantity is 0 or less
             * 1003 : orderQuantity has more than eight digits
             */
            String l_strOrderQuantity = l_curEditUnit.orderQuantity;
            if (l_strOrderQuantity != null)
            {
                if (!WEB3StringTypeUtility.isNumber(l_strOrderQuantity))
                {
                    l_trialCalcPortfolioErrorProductUnit.errorCode = "1001";
                    l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
                    l_intErrorUnitCnt++;
                    continue;
                }
                else if (Double.parseDouble(l_strOrderQuantity) <= 0)
                {
                    l_trialCalcPortfolioErrorProductUnit.errorCode = "1002";
                    l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
                    l_intErrorUnitCnt++;
                    continue;
                }
                else if (Double.parseDouble(l_strOrderQuantity) >= 100000000)
                {
                    l_trialCalcPortfolioErrorProductUnit.errorCode = "1003";
                    l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
                    l_intErrorUnitCnt++;
                    continue;
                }
            }

            /*
             * If l_strBuyPrice is inputted and not proper then set errorCode with
             * 1004 : buyPrice is not a number
             * 1005 : buyPrice is 0 or less
             * 1006 : buyPrice has more than eight digits
             */
            String l_strBuyPrice = l_curEditUnit.buyPrice;
            if (l_strBuyPrice != null)
            {
                if (!WEB3StringTypeUtility.isNumber(l_strBuyPrice))
                {
                    l_trialCalcPortfolioErrorProductUnit.errorCode = "1004";
                    l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
                    l_intErrorUnitCnt++;
                    continue;
                }
                else if (Double.parseDouble(l_strBuyPrice) <= 0)
                {
                    l_trialCalcPortfolioErrorProductUnit.errorCode = "1005";
                    l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
                    l_intErrorUnitCnt++;
                    continue;
                }
                else if (Double.parseDouble(l_strBuyPrice) >= 100000000)
                {
                    l_trialCalcPortfolioErrorProductUnit.errorCode = "1006";
                    l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
                    l_intErrorUnitCnt++;
                    continue;
                }
            }

            /*
             * Get the product information.
             * If there is no product related to given product code and Institution
             * then set the error code with
             * 0006 : The corresponding product doesn't exist.
             */
            String l_strProductCode = l_curEditUnit.productCode;
			WEB3EquityProduct l_product = null;
            try
            {
				l_product =
                    (WEB3EquityProduct) l_productManager.getProduct(
                        l_institution,
                        l_strProductCode);
            }
            catch (NotFoundException l_notFoundException)
            {
                l_trialCalcPortfolioErrorProductUnit.errorCode = "0006";
                l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
                l_intErrorUnitCnt++;
                continue;
            }

            /*
             * Get the TradedProduct information.
             * If the there is no TradedProduct related to given
             * productCode, institution and marketCode then set the error code with
             * 0007 : The corresponding dealings product doesn't exist.
             */

            String l_strMarketCode = l_curEditUnit.marketCode;
            if (l_strMarketCode != null && 
                !l_strMarketCode.equals(WEB3MarketCodeDef.DEFAULT)) {
				try
				{
					WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
					l_productManager.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
				}
				catch (NotFoundException l_notFoundException)
				{
					l_trialCalcPortfolioErrorProductUnit.errorCode = "0007";
					l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
					l_intErrorUnitCnt++;
					continue;
				}
            }
            else
            {
            	// 市場コード指定なし時は、優先市場の市場コードをセット。
            	// 銘柄テーブル.優先市場設定なし時は、
            	// 東証＞大証＞名証＞ＪＡＳＤＡＱ＞ＮＮＭの評価順で
            	// 上場されている市場を優先市場としてセット。
				Market l_primaryMarket = l_product.getPrimaryMarket();
				if (l_primaryMarket != null)
				{
					l_curEditUnit.marketCode = l_primaryMarket.getMarketCode();
					l_trialCalcPortfolioErrorProductUnit.marketCode =
					    l_curEditUnit.marketCode;
				}
				else
				{
					Market[] l_markets = l_product.getTradedMarkets();
					if (l_markets == null)
					{
						l_trialCalcPortfolioErrorProductUnit.errorCode = "0003";
						l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
						l_intErrorUnitCnt++;
						continue;
					}
					Vector l_vecMarkets = new Vector();
					boolean l_blnNNM = false;
					for (int m = 0; m < l_markets.length; m++)
					{
						if (WEB3MarketCodeDef.TOKYO.equals(l_markets[m].getMarketCode()) ||
							WEB3MarketCodeDef.OSAKA.equals(l_markets[m].getMarketCode()) ||
							WEB3MarketCodeDef.NAGOYA.equals(l_markets[m].getMarketCode()) ||
							WEB3MarketCodeDef.JASDAQ.equals(l_markets[m].getMarketCode()))
						{
							l_vecMarkets.add(l_markets[m].getMarketCode());
						}
						else if (WEB3MarketCodeDef.NNM.equals(l_markets[m].getMarketCode()))
						{
							l_blnNNM = true;
						}
					}
					if (l_blnNNM == true)
					{
						l_vecMarkets.add(WEB3MarketCodeDef.NNM);
					}
					if (l_vecMarkets.size() < 1)
					{
						l_trialCalcPortfolioErrorProductUnit.errorCode = "0003";
						l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
						l_intErrorUnitCnt++;
						continue;
					}
					l_curEditUnit.marketCode = (String)l_vecMarkets.firstElement();
					l_trialCalcPortfolioErrorProductUnit.marketCode =
					    l_curEditUnit.marketCode;
				}
            }

			/*
			 * （銘柄コード＋買単価＋市場コード）が重複する明細がある場合は、エラー明細を追加する。
			 * 2001 : 指定が重複（銘柄コード、市場コード、買単価が同一の明細あり）
			 * ※買付単価指定なし（==null）の場合は、DB上で指定なしを表す0に読み替えて比較する。
			 */
			boolean l_blnDuplicate = false;
			for (int j = 0; j < i; j++)
			{
				if (l_arrErrorUnit[j].errorCode != null)
				{
					continue;
				}
				WEB3TrialCalcPortfolioEditProductUnit l_portfolioUnit =
					(WEB3TrialCalcPortfolioEditProductUnit) l_arrEditUnit[j];

				double l_dblCurPrice;
				if (l_curEditUnit.buyPrice == null)
				{
					l_dblCurPrice = 0.0D;
				}
				else
				{
					l_dblCurPrice = Double.parseDouble(l_curEditUnit.buyPrice);
				}
				double l_dblPortPrice;
				if (l_portfolioUnit.buyPrice == null)
				{
					l_dblPortPrice = 0.0D;
				}
				else
				{
					l_dblPortPrice = Double.parseDouble(l_portfolioUnit.buyPrice);
				}
				boolean l_blnProductCodeEquals =
					l_curEditUnit.productCode.equals(l_portfolioUnit.productCode);
				boolean l_blnMarketCodeEquals =
					l_curEditUnit.marketCode.equals(l_portfolioUnit.marketCode);
				boolean l_blnBuyPriceEquals = false;
				if (l_dblCurPrice == l_dblPortPrice)
				{
					l_blnBuyPriceEquals = true;
				}

				if ((l_blnProductCodeEquals == true) &&
				    (l_blnMarketCodeEquals == true) &&
				    (l_blnBuyPriceEquals == true))
				{
					l_blnDuplicate = true;
					break;
				}
			}
			if (l_blnDuplicate == true)
			{
				l_trialCalcPortfolioErrorProductUnit.errorCode = "2001";
				l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
				l_intErrorUnitCnt++;
				continue;
			}

            // If there is no error then Add then instance WEB3TrialCalcPortfolioErrorProductUnit
            l_arrErrorUnit[i] = l_trialCalcPortfolioErrorProductUnit;
        }

        // Create Response.
		WEB3TrialCalcPortfolioEditResponse l_trialCalcPortfolioEditResponse =
            (WEB3TrialCalcPortfolioEditResponse) l_request.createResponse();

        // If Number of Errors are more then 0 then return the Error.
        if (l_intErrorUnitCnt > 0)
        {
        	for (int i = 0; i < l_arrErrorUnit.length; i++)
        	{
				WEB3EquityProduct l_equityProduct;
				try
				{
					l_equityProduct =
						(WEB3EquityProduct) l_productManager.getProduct(
							l_institution,
							l_arrErrorUnit[i].productCode);
				}
				catch (NotFoundException l_notFoundException)
				{
					continue;
        	    }
				EqtypeProductRow l_eqtypeProductRow =
				    (EqtypeProductRow)l_equityProduct.getDataSourceObject();
				l_arrErrorUnit[i].productName = l_eqtypeProductRow.getStandardName();
        	}
            l_trialCalcPortfolioEditResponse.portfolioErrorProductUnit =
                l_arrErrorUnit;
			log.exiting(STR_METHOD_NAME);
            return l_trialCalcPortfolioEditResponse;
        }

        /*
         * Retrive All records based on PortfolioCode and find out DisplayTarget in the record
         * If the record is not found in the List then insert that record.
         */
        AccountPortfolioRow l_accountPortfolioRow =
            this.getAccountPortfolio(l_request.portfolioCode);
        if (l_accountPortfolioRow == null)
        {
			this.insertAccountPortfolio(l_request.portfolioCode,
			    WEB3ListRangeDef.ONLY_PORTFOLIO);
        }
        else
        {
			this.updateAccountPortfolio(l_request.portfolioCode, null);
        }

		this.deleteAccountPortfolioProduct(l_request.portfolioCode);
		if (l_intEditUnitCnt == 0)
		{
			log.exiting(STR_METHOD_NAME);
			return l_trialCalcPortfolioEditResponse;
		}

        for (int i = 0; i < l_intEditUnitCnt; i++)
        {
            this.insertAccountPortfolioProduct(
			    l_request.portfolioCode,
                l_arrEditUnit[i]);
        }

        log.exiting(STR_METHOD_NAME);
        return l_trialCalcPortfolioEditResponse;
    }

    /**
     * （getポートフォリオ編集入力）<BR>
     * <BR>
     * ポートフォリオ編集画面用に、ポートフォリオを構成する銘柄の一覧取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポートフォリオサービス）getポートフォリオ編集入力」参照。<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputResponse
     * @@exception NotFoundException NotFoundException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 41987EB501F6
     */
    protected WEB3TrialCalcPortfolioEditInputResponse
        getPortfolioEditInput(WEB3TrialCalcPortfolioEditInputRequest l_request)
        throws WEB3BaseException, DataNetworkException, DataQueryException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "getPortfolioEditInput(WEB3TrialCalcPortfolioEditInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        // Validate the order.
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // Validate All portfolioEditProductUnit
        l_request.validate();

		AccountPortfolioProductRow[] l_arrAccountPortfolioProductRow =
            this.getAccountPortfolioProduct(l_request.portfolioCode);

		WEB3TrialCalcPortfolioEditInputResponse l_trialCalcPortfolioEditInputResponse =
            (WEB3TrialCalcPortfolioEditInputResponse) l_request.createResponse();

        if (l_arrAccountPortfolioProductRow == null)
        {
			log.exiting(STR_METHOD_NAME);
            return l_trialCalcPortfolioEditInputResponse;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityProductManager l_productManager =
		    (WEB3EquityProductManager)l_tradingModule.getProductManager();

		Institution l_institution = this.getSubAccount().getInstitution();
        int l_arrAccountPortfolioProductRowCnt = l_arrAccountPortfolioProductRow.length;
		WEB3TrialCalcPortfolioEditProductInputUnit[] l_arrPortfolioEditProductInputUnit =
		    new WEB3TrialCalcPortfolioEditProductInputUnit[
		    l_arrAccountPortfolioProductRowCnt];
        for (int i = 0; i < l_arrAccountPortfolioProductRowCnt; i++)
        {
			WEB3EquityProduct l_product =
                (WEB3EquityProduct) l_productManager.getProduct(
			    l_arrAccountPortfolioProductRow[i].getProductId());
			EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_product.getDataSourceObject();

            Market[] l_arrMarket = l_product.getTradedMarkets();
            
            int l_arrMarketCnt = l_arrMarket.length;
            String[] l_arrMarketCode = new String[l_arrMarketCnt];
            for (int j = 0; j < l_arrMarketCnt; j ++)
            {
                l_arrMarketCode[j] = l_arrMarket[j].getMarketCode();
            }

            // Set the Data in PortfolioEditProductInputUnit
			WEB3TrialCalcPortfolioEditProductInputUnit l_portfolioEditProductInputUnit =
			    new WEB3TrialCalcPortfolioEditProductInputUnit();
            l_portfolioEditProductInputUnit.productCode = l_product.getProductCode();
			l_portfolioEditProductInputUnit.productName = l_eqtypeProductRow.getStandardName();
			if (l_arrAccountPortfolioProductRow[i].getQuantityIsNull() == true)
			{
				l_portfolioEditProductInputUnit.orderQuantity = null;
			}
			else
			{
				l_portfolioEditProductInputUnit.orderQuantity =
				    WEB3StringTypeUtility.formatNumber(
				        l_arrAccountPortfolioProductRow[i].getQuantity());
			}
			if (l_arrAccountPortfolioProductRow[i].getBuyPrice() == 0.0D)
			{
				l_portfolioEditProductInputUnit.buyPrice = null;
			}
			else
			{
				l_portfolioEditProductInputUnit.buyPrice =
					WEB3StringTypeUtility.formatNumber(
					    l_arrAccountPortfolioProductRow[i].getBuyPrice());
			}
            l_portfolioEditProductInputUnit.marketCode =
                l_arrAccountPortfolioProductRow[i].getMarketCode();
            l_portfolioEditProductInputUnit.marketCodeList = l_arrMarketCode;

            l_arrPortfolioEditProductInputUnit[i] = l_portfolioEditProductInputUnit;
        }

        l_trialCalcPortfolioEditInputResponse.portfolioEditProductInputUnit =
            l_arrPortfolioEditProductInputUnit;

        log.exiting(STR_METHOD_NAME);
        return l_trialCalcPortfolioEditInputResponse;
    }

    /**
     * （get顧客別ポートフォリオ構成銘柄）<BR>
     * <BR>
     * 【顧客別ポートフォリオ構成銘柄テーブル】より、ポートフォリオを構成する銘柄の一覧<BR>
     * を取得する。<BR>
     * <BR>
     * １）　@【顧客別ポートフォリオ構成銘柄テーブル】から、以下の条件に該当する銘柄の一<BR>
     * 覧を取得する。<BR>
     * <BR>
     * 　@　@＜取得条件＞<BR>
     *     証券会社コード = this.get補助口座( ).証券会社コード<BR>
     *     かつ　@部店ID = this.get補助口座( ).部店ID<BR>
     *     かつ　@口座ID = this.get補助口座( ).口座ID<BR>
     * 　@　@かつ　@ポートフォリオコード = 引数のポートフォリオコード<BR>
     * <BR>
     * ２）　@取得した顧客別ポートフォリオ構成銘柄Rowの配列を返す。<BR>
     * <BR>
     * @@param l_portfolioCode - （ポートフォリオコード）<BR>
     * @@return webbroker3.trialcalc.data.AccountPortfolioProductRow
     * @@exception WEB3BaseException WEB3BaseException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception IllegalStateException IllegalStateException
     * @@roseuid 41944A1B011E
     */
    protected AccountPortfolioProductRow[] getAccountPortfolioProduct(String l_portfolioCode)
        throws WEB3BaseException, DataQueryException, DataNetworkException, IllegalStateException
    {
        final String STR_METHOD_NAME = "getAccountPortfolioProduct(String l_portfolioCode)";

        log.entering(STR_METHOD_NAME);
        
		SubAccountRow l_subAccountRow =
			(SubAccountRow)this.getSubAccount().getDataSourceObject();

        String l_strWhere =
            " institution_code = ? "
                + " and branch_id = ? "
                + " and account_id = ? "
                + " and portfolio_code = ? ";

        Object[] l_objWhere =
            {
                l_subAccountRow.getInstitutionCode(),
                new Long(l_subAccountRow.getBranchId()),
                new Long(l_subAccountRow.getAccountId()),
                l_portfolioCode };

		AccountPortfolioProductRow[] l_accountPortfolioProductRows = null;
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
		List l_lisSearchResult =
            l_queryProcessor.doFindAllQuery(
                AccountPortfolioProductRow.TYPE,
                l_strWhere,
                null,
                l_objWhere);

        if (!l_lisSearchResult.isEmpty())
        {
            l_accountPortfolioProductRows =
                new AccountPortfolioProductRow[l_lisSearchResult.size()];
            l_lisSearchResult.toArray(l_accountPortfolioProductRows);
        }

        log.exiting(STR_METHOD_NAME);
        return l_accountPortfolioProductRows;
    }

    /**
     * （sort表示銘柄明細一覧）<BR>
     * 指定されたソートキー、昇降順に基づいて<BR>
     * 計算サービスポートフォリオ表示銘柄明細の配列のソートを行う。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@引数.ソートキーの配列数分Loopする。<BR>
     * 　@２－１）　@引数.ソートキー.キー項目を取得する。<BR>
     * <BR>
     * 　@２－２）　@引数.ソートキー.昇順/降順を取得する。<BR>
     * <BR>
     * 　@２－３）　@キー項目による分岐処理<BR>
     * 　@　@・キー項目が銘柄コードであった場合、計算サービス銘柄コードComparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が市場コードであった場合、計算サービス市場コードComparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が株数であった場合、計算サービス株数Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が時価であった場合、計算サービス時価Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が買付単価であった場合、計算サービス買付単価Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が買付代金であった場合、計算サービス買付代金Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が評価額であった場合、計算サービス評価額Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が評価損益であった場合、計算サービス評価損益Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が評価損益率であった場合、計算サービス評価損益率Comparatorを生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@・キー項目が入力／特定区分であった場合、計算サービス入力／特定区分Comparator<BR>
     * を生成<BR>
     * 　@　@[コンストラクタの引数＝２－２）で取得した昇順/降順]<BR>
     * <BR>
     * 　@２－４）　@２－３）にて作成したComparatorをArrayListに追加する。<BR>
     * <BR>
     * ３）　@ArrayListからComparatorの配列を作成する。<BR>
     * <BR>
     * ４）　@Comparatorの配列順に引数.表示銘柄明細一覧をソートする。<BR>
     * (web3-common)WEB3ArraysUtility.sort(引数.表示銘柄明細一覧、<BR>
     * ３）で作成したComparator[])<BR>
     * <BR>
     * @@param l_portfolioDisplayInputProductUnit - （表示銘柄明細一覧）<BR>
     * 計算サービスポートフォリオ表示銘柄明細の配列<BR>
     * @@param l_sortKeys - （ソートキー）<BR>
     * 計算サービスソートキーの一覧<BR>
     * @@roseuid 41986D000244
     */
    protected void sortDisplayProductUnit(WEB3TrialCalcPortfolioDisplayProductUnit[]
        l_portfolioDisplayInputProductUnit, WEB3TrialCalcSortKeyUnit[] l_sortKeys)
    {
		final String STR_METHOD_NAME =
            "sortDisplayProductUnit(WEB3TrialCalcPortfolioDisplayProductUnit[],WEB3TrialCalcSortKeyUnit[])";

        List l_lstComparator = new ArrayList();
        char l_chKeyItem = ' ';

		log.entering(STR_METHOD_NAME);

        if (l_sortKeys != null)
        {
            int l_sortKeysCnt = l_sortKeys.length;
            for (int i = 0; i < l_sortKeysCnt; i++)
            {
				String l_strAscDesc = l_sortKeys[i].ascDesc;
				String l_strKeyItem = l_sortKeys[i].keyItem;
                l_chKeyItem = (l_strKeyItem != null) ? l_strKeyItem.charAt(0) : ' ';

                switch (l_chKeyItem)
                {
                    case 'p' :
                        if ("productCode".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(
                                new WEB3TrialCalcProductCodeComparator(l_strAscDesc));
                        }
                        break;

                    case 'm' :
                        if ("marketCode".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(
                                new WEB3TrialCalcMarketCodeComparator(l_strAscDesc));
                        }
                        break;

                    case 'o' :
                        if ("orderQuantity".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(new WEB3TrialCalcQuantityComparator(l_strAscDesc));
                        }
                        break;

                    case 'c' :
                        if ("currentPrice".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(
                                new WEB3TrialCalcCurrentPriceComparator(l_strAscDesc));
                        }
                        break;

                    case 'b' :
                        if ("buyPrice".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(new WEB3TrialCalcBuyPriceComparator(l_strAscDesc));
                            break;
                        }
                        if ("buyAmount".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(new WEB3TrialCalcBuyAmountComparator(l_strAscDesc));
                        }
                        break;

                    case 'a' :
                        if ("appraisalPrice".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(
                                new WEB3TrialCalcAppraisalPriceComparator(l_strAscDesc));
                            break;
                        }
                        if ("appraisalProfitLoss".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(
                                new WEB3TrialCalcAppraisalProfitLossComparator(l_strAscDesc));
                            break;
                        }
                        if ("appraisalProfitLossRate".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(
                                new WEB3TrialCalcAppraisalProfitLossRateComparator(l_strAscDesc));
                        }
                        break;

                    case 'i' :
                        if ("inputCapitalGainDiv".equals(l_strKeyItem))
                        {
                            l_lstComparator.add(
                                new WEB3TrialCalcInputSpecificDivisionComparator(l_strAscDesc));
                        }
                        break;
                }
            }
        }

		Comparator[] l_arrComparator = new Comparator[l_lstComparator.size()];
        l_lstComparator.toArray(l_arrComparator);

        WEB3ArraysUtility.sort(l_portfolioDisplayInputProductUnit, l_arrComparator);
		log.exiting(STR_METHOD_NAME);
    }

    /**
     * （delete顧客別ポートフォリオ構成銘柄）<BR>
     * <BR>
     * 【顧客別ポートフォリオ構成銘柄テーブル】から、<BR>
     * 引数のポートフォリオを構成する銘柄の一覧を削除する。<BR>
     * <BR>
     * １）　@【顧客別ポートフォリオ構成銘柄テーブル】から、以下の条件に該当する銘柄の<BR>
     * 一覧を削除する。<BR>
     * <BR>
     * 　@　@＜削除条件＞<BR>
     * 　@　@　@証券会社コード = this.get補助口座( ).証券会社コード<BR>
     * 　@　@かつ　@部店ID = this.get補助口座( ).部店ID<BR>
     * 　@　@かつ　@口座ID = this.get補助口座( ).口座ID<BR>
     * 　@　@かつ　@ポートフォリオコード = 引数のポートフォリオコード<BR>
     * <BR>
     * ２）　@returnする。<BR>
     * <BR>
     * @@param l_portfolioCode - （ポートフォリオコード）<BR>
     * @@exception WEB3BaseException WEB3BaseException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@roseuid 4198A75C0394
     */
    protected void deleteAccountPortfolioProduct(String l_portfolioCode)
        throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "deleteAccountPortfolioProduct(String l_portfolioCode)";
        log.entering(STR_METHOD_NAME);

        // Build Where clause.
        String l_strWhere =
            " institution_code = ? "
                + " and branch_id = ? "
                + " and account_id = ? "
                + " and portfolio_code = ? ";

        SubAccountRow l_subAccountRow =
            (SubAccountRow)this.getSubAccount().getDataSourceObject();

        // Add parameters in Where clause.
        Object[] l_objWhere =
            {
                l_subAccountRow.getInstitutionCode(),
                new Long(l_subAccountRow.getBranchId()),
                new Long(l_subAccountRow.getAccountId()),
                l_portfolioCode };

		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
		int l_intDeleteResult =
		    l_queryProcessor.doDeleteAllQuery(
		        AccountPortfolioProductRow.TYPE,
		        l_strWhere,
		        l_objWhere);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （insert顧客別ポートフォリオ構成銘柄）<BR>
     * <BR>
     * 【顧客別ポートフォリオ構成銘柄テーブル】へ、<BR>
     * 引数で指定されたポートフォリオを構成する銘柄明細を登録する。<BR>
     * <BR>
     * １）　@【顧客別ポートフォリオ構成銘柄テーブル】に、以下の１レコードを登録する。<BR>
     * <BR>
     * 　@　@＜プロパティセット＞<BR>
     * 　@　@　@証券会社コード = this.get補助口座( ).証券会社コード<BR>
     * 　@　@　@部店ID = this.get補助口座( ).部店ID<BR>
     * 　@　@　@口座ID = this.get補助口座( ).口座ID<BR>
     * 　@　@　@ポートフォリオコード = 引数のポートフォリオコード<BR>
     * 　@　@　@銘柄ID = 引数の銘柄明細.銘柄コードに該当する株式銘柄.銘柄ID(*1)<BR>
     * 　@　@　@市場コード = 引数の銘柄明細.市場コード<BR>
     * 　@　@　@株数 = 引数の銘柄明細.株数(*2)<BR>
     * 　@　@　@買付単価 = 引数の銘柄明細.買単価<BR>
     * 　@　@　@作成日付 = GtlUtils.getSystemTimestamp( )<BR>
     * 　@　@　@更新日付 = GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * 　@　@(*1)拡張プロダクトマネージャ.getProduct(this.get補助口座().getInstitution()<BR>
     * 　@　@　@　@引数の銘柄明細.銘柄コード)にて取得した株式銘柄オブジェクトを使用する。<BR>
     * 　@　@(*2)nullの場合は、DBにnull値を登録する。<BR>
     * <BR>
     * ２）　@returnする。<BR>
     * <BR>
     * @@param l_portfolioCode - （ポートフォリオコード）<BR>
     * @@param l_productDetails - （銘柄明細）<BR>
     * 登録対象の銘柄明細。<BR>
     * @@exception NotFoundException NotFoundException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 4198A8B20311
     */
    protected void insertAccountPortfolioProduct(String l_portfolioCode,
        WEB3TrialCalcPortfolioEditProductUnit l_productDetails)
        throws WEB3BaseException, DataQueryException, DataNetworkException, NotFoundException
    {
        final String STR_METHOD_NAME =
            "insertAccountPortfolioProduct("
                + "String l_portfolioCode, WEB3TrialCalcPortfolioEditProductUnit l_productDetails)";
        log.entering(STR_METHOD_NAME);

        // Create an Object of SubAccountRow.
        SubAccount l_subAccount = this.getSubAccount();
        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();

        // Build AccountPortfolioParams Object to be inserted.
		AccountPortfolioProductParams l_accountPortfolioProductParams =
		    new AccountPortfolioProductParams();
        l_accountPortfolioProductParams.setInstitutionCode(l_subAccountRow.getInstitutionCode());
        l_accountPortfolioProductParams.setBranchId(l_subAccountRow.getBranchId());
        l_accountPortfolioProductParams.setAccountId(l_subAccountRow.getAccountId());
        l_accountPortfolioProductParams.setPortfolioCode(l_portfolioCode);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager) l_tradingModule.getProductManager();

        Institution l_institution = l_subAccount.getInstitution();
        String l_strProductCode = l_productDetails.productCode;
		WEB3EquityProduct l_product =
            (WEB3EquityProduct) l_productManager.getProduct(l_institution, l_strProductCode);
        l_accountPortfolioProductParams.setProductId(l_product.getProductId());

		l_accountPortfolioProductParams.setMarketCode(l_productDetails.marketCode);
		if (l_productDetails.orderQuantity == null)
		{
			l_accountPortfolioProductParams.setQuantity(null);
		}
		else
		{
			l_accountPortfolioProductParams.setQuantity(
				Double.parseDouble(l_productDetails.orderQuantity));
		}

        if (l_productDetails.buyPrice == null)
        {
			l_accountPortfolioProductParams.setBuyPrice(0);
        }
        else
        {
			l_accountPortfolioProductParams.setBuyPrice(
			    Double.parseDouble(l_productDetails.buyPrice));
        }

        l_accountPortfolioProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_accountPortfolioProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doInsertQuery(l_accountPortfolioProductParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （update顧客別ポートフォリオ）<BR>
     * <BR>
     * 【顧客別ポートフォリオテーブル】への更新を行う。<BR>
     * <BR>
     * １）　@【顧客別ポートフォリオテーブル】を更新する。<BR>
     * <BR>
     * 　@　@＜更新条件＞<BR>
     * 　@　@　@証券会社コード = this.get補助口座( ).証券会社コード<BR>
     * 　@　@かつ　@部店ID = this.get補助口座( ).部店ID<BR>
     * 　@　@かつ　@口座ID = this.get補助口座( ).口座ID<BR>
     * 　@　@かつ　@ポートフォリオコード = 引数のポートフォリオコード<BR>
     * <BR>
     * 　@　@＜更新対象プロパティ＞<BR>
     * 　@　@　@表示対象 = 引数の「表示対象」(*1)<BR>
     * 　@　@　@更新日付 = GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * 　@　@　@(*1)引数の「表示対象」≠nullの場合のみ、更新対象プロパティとする。<BR>
     * <BR>
     * ２）　@returnする。<BR>
     * <BR>
     * @@param l_portfolioCode - （ポートフォリオコード）
     * @@param l_displayTarget - （表示対象）<BR>
     * 表示対象の指定。<BR>
     * （1：ポートフォリオ登録分のみ　@2：特定分のみ　@3：両方合せる）<BR>
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 4199BE5D0398
     */
    protected void updateAccountPortfolio(String l_portfolioCode, String l_displayTarget)
        throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
            "updateAccountPortfolio(String l_portfolioCode, String l_displayTarget)";
        log.entering(STR_METHOD_NAME);
        
		AccountPortfolioRow l_row = this.getAccountPortfolio(l_portfolioCode);
		if (l_row == null)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				null,
				null);
		}
		AccountPortfolioParams l_params = new AccountPortfolioParams(l_row);
		if (l_displayTarget != null)
		{
			l_params.setListRange(l_displayTarget);
		}
		l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doUpdateQuery(l_params);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （get顧客別ポートフォリオ）<BR>
     * <BR>
     * 【顧客別ポートフォリオテーブル】から該当するデータを取得する。<BR>
     * <BR>
     * １）　@【顧客別ポートフォリオテーブル】から、以下の条件に該当するデータを取得する。<BR>
     * <BR>
     * 　@　@＜取得条件＞<BR>
     * 　@　@　@証券会社コード = this.get補助口座( ).証券会社コード<BR>
     * 　@　@かつ　@部店ID = this.get補助口座( ).部店ID<BR>
     * 　@　@かつ　@口座ID = this.get補助口座( ).口座ID<BR>
     * 　@　@かつ　@ポートフォリオコード = 引数のポートフォリオコード<BR>
     * <BR>
     * ２）　@取得した顧客別ポートフォリオRowを返却する。<BR>
     * 　@　@　@該当データなし時はnullを返却する。<BR>
     * <BR>
     * @@param l_portfolioCode - （ポートフォリオコード）<BR>
     * @@return AccountPortfolioRow
     * @@exception IllegalStateException IllegalStateException
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 4199C3E00359
     */
    protected AccountPortfolioRow getAccountPortfolio(String l_portfolioCode)
        throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "getAccountPortfolio(String l_portfolioCode)";
        log.entering(STR_METHOD_NAME);
        
		SubAccountRow l_subAccountRow = (SubAccountRow)this.getSubAccount().getDataSourceObject();

		AccountPortfolioRow l_accountPortfolioRow =
		    AccountPortfolioDao.findRowByInstitutionCodeBranchIdAccountIdPortfolioCode(
		    l_subAccountRow.getInstitutionCode(),
			l_subAccountRow.getBranchId(),
			l_subAccountRow.getAccountId(),
		    l_portfolioCode
		);
		log.exiting(STR_METHOD_NAME);
        return l_accountPortfolioRow;
    }

    /**
     * （insert顧客別ポートフォリオ）<BR>
     * <BR>
     * 【顧客別ポートフォリオテーブル】への登録を行う。<BR>
     * <BR>
     * １）　@　@【顧客別ポートフォリオテーブル】への登録を行う。<BR>
     * <BR>
     * 　@　@＜プロパティセット＞<BR>
     * 　@　@　@証券会社コード = this.get補助口座( ).証券会社コード<BR>
     * 　@　@　@部店ID = this.get補助口座( ).部店ID<BR>
     * 　@　@　@口座ID = this.get補助口座( ).口座ID<BR>
     * 　@　@　@ポートフォリオコード = 引数のポートフォリオコード<BR>
     * 　@　@　@ポートフォリオ名 = 引数のポートフォリオコード<BR>
     * 　@　@　@表示対象 = 引数の「表示対象」<BR>
     * 　@　@　@作成日付 = GtlUtils.getSystemTimestamp( )<BR>
     * 　@　@　@更新日付 = GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * ２）　@returnする。<BR>
     * <BR>
     * @@param l_portfolioCode - （ポートフォリオコード）<BR>
     * @@param l_displayTarget - （表示対象）<BR>
     * 表示対象の指定。<BR>
     * （1：ポートフォリオ登録分のみ　@2：特定分のみ　@3：両方合せる）<BR>
     * @@exception DataQueryException DataQueryException
     * @@exception DataNetworkException DataNetworkException
     * @@exception WEB3BaseException WEB3BaseException
     * @@roseuid 419C3111033A
     */
    protected void insertAccountPortfolio(String l_portfolioCode, String l_displayTarget)
        throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME =
            "insertAccountPortfolio(String l_portfolioCode, String l_displayTarget)";
        AccountPortfolioParams l_accountPortfolioParams = null;
        QueryProcessor l_queryProcessor = null;
        log.entering(STR_METHOD_NAME);

        // Create an Object of SubAccountRow.
        SubAccountRow l_subAccountRow =
            (SubAccountRow)this.getSubAccount().getDataSourceObject();

        // Build AccountPortfolioParams Object to be inserted.
        l_accountPortfolioParams = new AccountPortfolioParams();
        l_accountPortfolioParams.setInstitutionCode(l_subAccountRow.getInstitutionCode());
        l_accountPortfolioParams.setBranchId(l_subAccountRow.getBranchId());
        l_accountPortfolioParams.setAccountId(l_subAccountRow.getAccountId());
        l_accountPortfolioParams.setPortfolioCode(l_portfolioCode);
        l_accountPortfolioParams.setPortfolioName(l_portfolioCode);
        l_accountPortfolioParams.setListRange(l_displayTarget);
        l_accountPortfolioParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_accountPortfolioParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        l_queryProcessor = Processors.getDefaultProcessor();
        l_queryProcessor.doInsertQuery(l_accountPortfolioParams);

        log.exiting(STR_METHOD_NAME);
    }
}@
