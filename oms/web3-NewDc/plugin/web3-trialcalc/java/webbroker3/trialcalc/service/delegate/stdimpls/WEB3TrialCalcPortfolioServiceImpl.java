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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�XImpl(WEB3TrialCalcPortfolioServiceImpl.java)
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
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�XImpl�j<BR>
 * <BR>
 * �|�[�g�t�H���I�T�[�r�X�����N���X�B<BR>
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
     * �|�[�g�t�H���I�T�[�r�X���������{����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
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
     * �iget�|�[�g�t�H���I�\���j<BR>
     * <BR>
     * �|�[�g�t�H���I�\����ʗp�ɁA<BR>
     * �|�[�g�t�H���I���\������������Ɋe��v�Z���s���ꗗ���쐬���Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�[�g�t�H���I�T�[�r�X�jget�|�[�g�t�H���I�\���v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
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
                        	// ���N�G�X�g.���͖�������.���t�P���w��Ȃ����́A
                        	// �ڋq�ʃ|�[�g�t�H���I�\�������̐ݒ�ɍ��킹
                        	// ���t�P����0���Z�b�g���ADB�̃��R�[�h�ƃ}�b�`���O����
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
				
				// ����=�ۗL���Y.����+�ۗL���Y.���t�s�\���ʂ��Z�b�g�B
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
                    // �w��s��͔���
                    if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01367))
                    {
                        l_errCode = "0001";
                    }
                    // �����擾�G���[
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005))
                    {
                        l_errCode = "0004";
                    }
                    // ���̑�
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
                    // �w��s��͔���
                    if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01367))
                    {
                        l_errCode = "0001";
                    }
                    // �D��s��Ȃ� && ��������Ȃ�
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01365))
                    {
                        l_errCode = "0001";
                    }
                    // �D��s��ꗗ�Ɏ戵�s��Ȃ�
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01366))
                    {
                        l_errCode = "0003";
                    }
                    // �����擾�G���[
                    else if (e.getErrorInfo().equals(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005))
                    {
                        l_errCode = "0004";
                    }
                    // ���̑�
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
					// ���t�P���ݒ�Ȃ����́A�������g�p
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

                // ���͕��̂ݔ��t���ɑ΂���ϑ��萔�������Z
                // �i���蕪�͕뉿�Ɋ܂܂�Ă���̂ŉ��Z�s�v�j
                if (l_dbBuyAmount.doubleValue() != 0D)
                {
                    if (l_displayProductUnit.inputCapitalGainDiv.equals(
                    	WEB3TrialCalcInputCapitalGainDivDef.INPUT)) 
                	{
    					// ����p�v�Z�p����i���t��� = �����~���t�P���j�Z�b�g
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
                    // �]���z�i���j
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
     * �iupdate�|�[�g�t�H���I�ҏW�j<BR>
     * <BR>
     * �|�[�g�t�H���I���\����������̈ꗗ��DB�ɓo�^����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�[�g�t�H���I�T�[�r�X�jupdate�|�[�g�t�H���I�ҏW�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
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
            	// �s��R�[�h�w��Ȃ����́A�D��s��̎s��R�[�h���Z�b�g�B
            	// �����e�[�u��.�D��s��ݒ�Ȃ����́A
            	// ���؁���؁����؁��i�`�r�c�`�p���m�m�l�̕]������
            	// ��ꂳ��Ă���s���D��s��Ƃ��ăZ�b�g�B
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
			 * �i�����R�[�h�{���P���{�s��R�[�h�j���d�����閾�ׂ�����ꍇ�́A�G���[���ׂ�ǉ�����B
			 * 2001 : �w�肪�d���i�����R�[�h�A�s��R�[�h�A���P��������̖��ׂ���j
			 * �����t�P���w��Ȃ��i==null�j�̏ꍇ�́ADB��Ŏw��Ȃ���\��0�ɓǂݑւ��Ĕ�r����B
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
     * �iget�|�[�g�t�H���I�ҏW���́j<BR>
     * <BR>
     * �|�[�g�t�H���I�ҏW��ʗp�ɁA�|�[�g�t�H���I���\����������̈ꗗ�擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�|�[�g�t�H���I�T�[�r�X�jget�|�[�g�t�H���I�ҏW���́v�Q�ƁB<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
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
     * �iget�ڋq�ʃ|�[�g�t�H���I�\�������j<BR>
     * <BR>
     * �y�ڋq�ʃ|�[�g�t�H���I�\�������e�[�u���z���A�|�[�g�t�H���I���\����������̈ꗗ<BR>
     * ���擾����B<BR>
     * <BR>
     * �P�j�@@�y�ڋq�ʃ|�[�g�t�H���I�\�������e�[�u���z����A�ȉ��̏����ɊY����������̈�<BR>
     * �����擾����B<BR>
     * <BR>
     * �@@�@@���擾������<BR>
     *     �،���ЃR�[�h = this.get�⏕����( ).�،���ЃR�[�h<BR>
     *     ���@@���XID = this.get�⏕����( ).���XID<BR>
     *     ���@@����ID = this.get�⏕����( ).����ID<BR>
     * �@@�@@���@@�|�[�g�t�H���I�R�[�h = �����̃|�[�g�t�H���I�R�[�h<BR>
     * <BR>
     * �Q�j�@@�擾�����ڋq�ʃ|�[�g�t�H���I�\������Row�̔z���Ԃ��B<BR>
     * <BR>
     * @@param l_portfolioCode - �i�|�[�g�t�H���I�R�[�h�j<BR>
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
     * �isort�\���������׈ꗗ�j<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â���<BR>
     * �v�Z�T�[�r�X�|�[�g�t�H���I�\���������ׂ̔z��̃\�[�g���s���B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����.�\�[�g�L�[�̔z�񐔕�Loop����B<BR>
     * �@@�Q�|�P�j�@@����.�\�[�g�L�[.�L�[���ڂ��擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@����.�\�[�g�L�[.����/�~�����擾����B<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�L�[���ڂɂ�镪�򏈗�<BR>
     * �@@�@@�E�L�[���ڂ������R�[�h�ł������ꍇ�A�v�Z�T�[�r�X�����R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��s��R�[�h�ł������ꍇ�A�v�Z�T�[�r�X�s��R�[�hComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ������ł������ꍇ�A�v�Z�T�[�r�X����Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ������ł������ꍇ�A�v�Z�T�[�r�X����Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ����t�P���ł������ꍇ�A�v�Z�T�[�r�X���t�P��Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ����t����ł������ꍇ�A�v�Z�T�[�r�X���t���Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��]���z�ł������ꍇ�A�v�Z�T�[�r�X�]���zComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��]�����v�ł������ꍇ�A�v�Z�T�[�r�X�]�����vComparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ��]�����v���ł������ꍇ�A�v�Z�T�[�r�X�]�����v��Comparator�𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂ����́^����敪�ł������ꍇ�A�v�Z�T�[�r�X���́^����敪Comparator<BR>
     * �𐶐�<BR>
     * �@@�@@[�R���X�g���N�^�̈������Q�|�Q�j�Ŏ擾��������/�~��]<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�Q�|�R�j�ɂč쐬����Comparator��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList����Comparator�̔z����쐬����B<BR>
     * <BR>
     * �S�j�@@Comparator�̔z�񏇂Ɉ���.�\���������׈ꗗ���\�[�g����B<BR>
     * (web3-common)WEB3ArraysUtility.sort(����.�\���������׈ꗗ�A<BR>
     * �R�j�ō쐬����Comparator[])<BR>
     * <BR>
     * @@param l_portfolioDisplayInputProductUnit - �i�\���������׈ꗗ�j<BR>
     * �v�Z�T�[�r�X�|�[�g�t�H���I�\���������ׂ̔z��<BR>
     * @@param l_sortKeys - �i�\�[�g�L�[�j<BR>
     * �v�Z�T�[�r�X�\�[�g�L�[�̈ꗗ<BR>
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
     * �idelete�ڋq�ʃ|�[�g�t�H���I�\�������j<BR>
     * <BR>
     * �y�ڋq�ʃ|�[�g�t�H���I�\�������e�[�u���z����A<BR>
     * �����̃|�[�g�t�H���I���\����������̈ꗗ���폜����B<BR>
     * <BR>
     * �P�j�@@�y�ڋq�ʃ|�[�g�t�H���I�\�������e�[�u���z����A�ȉ��̏����ɊY�����������<BR>
     * �ꗗ���폜����B<BR>
     * <BR>
     * �@@�@@���폜������<BR>
     * �@@�@@�@@�،���ЃR�[�h = this.get�⏕����( ).�،���ЃR�[�h<BR>
     * �@@�@@���@@���XID = this.get�⏕����( ).���XID<BR>
     * �@@�@@���@@����ID = this.get�⏕����( ).����ID<BR>
     * �@@�@@���@@�|�[�g�t�H���I�R�[�h = �����̃|�[�g�t�H���I�R�[�h<BR>
     * <BR>
     * �Q�j�@@return����B<BR>
     * <BR>
     * @@param l_portfolioCode - �i�|�[�g�t�H���I�R�[�h�j<BR>
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
     * �iinsert�ڋq�ʃ|�[�g�t�H���I�\�������j<BR>
     * <BR>
     * �y�ڋq�ʃ|�[�g�t�H���I�\�������e�[�u���z�ցA<BR>
     * �����Ŏw�肳�ꂽ�|�[�g�t�H���I���\������������ׂ�o�^����B<BR>
     * <BR>
     * �P�j�@@�y�ڋq�ʃ|�[�g�t�H���I�\�������e�[�u���z�ɁA�ȉ��̂P���R�[�h��o�^����B<BR>
     * <BR>
     * �@@�@@���v���p�e�B�Z�b�g��<BR>
     * �@@�@@�@@�،���ЃR�[�h = this.get�⏕����( ).�،���ЃR�[�h<BR>
     * �@@�@@�@@���XID = this.get�⏕����( ).���XID<BR>
     * �@@�@@�@@����ID = this.get�⏕����( ).����ID<BR>
     * �@@�@@�@@�|�[�g�t�H���I�R�[�h = �����̃|�[�g�t�H���I�R�[�h<BR>
     * �@@�@@�@@����ID = �����̖�������.�����R�[�h�ɊY�����銔������.����ID(*1)<BR>
     * �@@�@@�@@�s��R�[�h = �����̖�������.�s��R�[�h<BR>
     * �@@�@@�@@���� = �����̖�������.����(*2)<BR>
     * �@@�@@�@@���t�P�� = �����̖�������.���P��<BR>
     * �@@�@@�@@�쐬���t = GtlUtils.getSystemTimestamp( )<BR>
     * �@@�@@�@@�X�V���t = GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * �@@�@@(*1)�g���v���_�N�g�}�l�[�W��.getProduct(this.get�⏕����().getInstitution()<BR>
     * �@@�@@�@@�@@�����̖�������.�����R�[�h)�ɂĎ擾�������������I�u�W�F�N�g���g�p����B<BR>
     * �@@�@@(*2)null�̏ꍇ�́ADB��null�l��o�^����B<BR>
     * <BR>
     * �Q�j�@@return����B<BR>
     * <BR>
     * @@param l_portfolioCode - �i�|�[�g�t�H���I�R�[�h�j<BR>
     * @@param l_productDetails - �i�������ׁj<BR>
     * �o�^�Ώۂ̖������ׁB<BR>
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
     * �iupdate�ڋq�ʃ|�[�g�t�H���I�j<BR>
     * <BR>
     * �y�ڋq�ʃ|�[�g�t�H���I�e�[�u���z�ւ̍X�V���s���B<BR>
     * <BR>
     * �P�j�@@�y�ڋq�ʃ|�[�g�t�H���I�e�[�u���z���X�V����B<BR>
     * <BR>
     * �@@�@@���X�V������<BR>
     * �@@�@@�@@�،���ЃR�[�h = this.get�⏕����( ).�،���ЃR�[�h<BR>
     * �@@�@@���@@���XID = this.get�⏕����( ).���XID<BR>
     * �@@�@@���@@����ID = this.get�⏕����( ).����ID<BR>
     * �@@�@@���@@�|�[�g�t�H���I�R�[�h = �����̃|�[�g�t�H���I�R�[�h<BR>
     * <BR>
     * �@@�@@���X�V�Ώۃv���p�e�B��<BR>
     * �@@�@@�@@�\���Ώ� = �����́u�\���Ώہv(*1)<BR>
     * �@@�@@�@@�X�V���t = GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * �@@�@@�@@(*1)�����́u�\���Ώہv��null�̏ꍇ�̂݁A�X�V�Ώۃv���p�e�B�Ƃ���B<BR>
     * <BR>
     * �Q�j�@@return����B<BR>
     * <BR>
     * @@param l_portfolioCode - �i�|�[�g�t�H���I�R�[�h�j
     * @@param l_displayTarget - �i�\���Ώہj<BR>
     * �\���Ώۂ̎w��B<BR>
     * �i1�F�|�[�g�t�H���I�o�^���̂݁@@2�F���蕪�̂݁@@3�F����������j<BR>
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
     * �iget�ڋq�ʃ|�[�g�t�H���I�j<BR>
     * <BR>
     * �y�ڋq�ʃ|�[�g�t�H���I�e�[�u���z����Y������f�[�^���擾����B<BR>
     * <BR>
     * �P�j�@@�y�ڋq�ʃ|�[�g�t�H���I�e�[�u���z����A�ȉ��̏����ɊY������f�[�^���擾����B<BR>
     * <BR>
     * �@@�@@���擾������<BR>
     * �@@�@@�@@�،���ЃR�[�h = this.get�⏕����( ).�،���ЃR�[�h<BR>
     * �@@�@@���@@���XID = this.get�⏕����( ).���XID<BR>
     * �@@�@@���@@����ID = this.get�⏕����( ).����ID<BR>
     * �@@�@@���@@�|�[�g�t�H���I�R�[�h = �����̃|�[�g�t�H���I�R�[�h<BR>
     * <BR>
     * �Q�j�@@�擾�����ڋq�ʃ|�[�g�t�H���IRow��ԋp����B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ�����null��ԋp����B<BR>
     * <BR>
     * @@param l_portfolioCode - �i�|�[�g�t�H���I�R�[�h�j<BR>
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
     * �iinsert�ڋq�ʃ|�[�g�t�H���I�j<BR>
     * <BR>
     * �y�ڋq�ʃ|�[�g�t�H���I�e�[�u���z�ւ̓o�^���s���B<BR>
     * <BR>
     * �P�j�@@�@@�y�ڋq�ʃ|�[�g�t�H���I�e�[�u���z�ւ̓o�^���s���B<BR>
     * <BR>
     * �@@�@@���v���p�e�B�Z�b�g��<BR>
     * �@@�@@�@@�،���ЃR�[�h = this.get�⏕����( ).�،���ЃR�[�h<BR>
     * �@@�@@�@@���XID = this.get�⏕����( ).���XID<BR>
     * �@@�@@�@@����ID = this.get�⏕����( ).����ID<BR>
     * �@@�@@�@@�|�[�g�t�H���I�R�[�h = �����̃|�[�g�t�H���I�R�[�h<BR>
     * �@@�@@�@@�|�[�g�t�H���I�� = �����̃|�[�g�t�H���I�R�[�h<BR>
     * �@@�@@�@@�\���Ώ� = �����́u�\���Ώہv<BR>
     * �@@�@@�@@�쐬���t = GtlUtils.getSystemTimestamp( )<BR>
     * �@@�@@�@@�X�V���t = GtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * �Q�j�@@return����B<BR>
     * <BR>
     * @@param l_portfolioCode - �i�|�[�g�t�H���I�R�[�h�j<BR>
     * @@param l_displayTarget - �i�\���Ώہj<BR>
     * �\���Ώۂ̎w��B<BR>
     * �i1�F�|�[�g�t�H���I�o�^���̂݁@@2�F���蕪�̂݁@@3�F����������j<BR>
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
