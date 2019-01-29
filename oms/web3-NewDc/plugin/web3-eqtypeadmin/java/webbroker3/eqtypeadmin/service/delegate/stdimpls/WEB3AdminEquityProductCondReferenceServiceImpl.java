head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityProductCondReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者株式銘柄条件照会サービス実装クラス)
                        (WEB3AdminEquityProductCondReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/10 孟亞南(中訊) 仕様変更モデルNo.245
Revision History : 2009/10/20 張騰宇(中訊) 仕様変更モデルNo.246
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;
import webbroker3.eqtypeadmin.message.WEB3AdminPMCompResultInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductRegistInfoUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondReferenceService;

import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;

/**
 * （管理者株式銘柄条件照会サービス実装クラス）<BR>
 * <BR>
 * 管理者株式銘柄条件照会サービス実装クラス<BR>
 * <BR>
 * WEB3AdminEquityProductCondReferenceServiceImpl class<BR>
 * <BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public class WEB3AdminEquityProductCondReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityProductCondReferenceService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityProductCondReferenceServiceImpl.class);

    /**
     * @@roseuid 41FA2A8D035C
     */
    public WEB3AdminEquityProductCondReferenceServiceImpl()
    {

    }

    /**
     * （get銘柄入力画面）<BR>
     * <BR>
     * 株式銘柄条件照会銘柄入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者株式銘柄条件照会サービス)get銘柄入力画面」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (getProductInputScreen)<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondReferenceService process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition
     * reference service)getProductInputScreen"<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・株式銘柄条件照会銘柄入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondRefInputRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondRefInputResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4190C96301B6
     */
    protected WEB3AdminPMProductCondRefInputResponse
        getProductInputScreen(WEB3AdminPMProductCondRefInputRequest l_request)
        throws WEB3BaseException
    {
        WEB3AdminPMProductCondRefInputResponse l_response = null;
        WEB3Administrator l_admin = null;
        Date[] l_dateList = null;
        boolean l_isUpdate = false;

        // 1.1
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.2
        l_admin.validateAuthority(WEB3TransactionCategoryDef.EQTYPE_PRODUCT_SETTING, l_isUpdate);

        // 1.3
        l_dateList = this.getBizDateList();

        // 1.4
        l_response = (WEB3AdminPMProductCondRefInputResponse) l_request.createResponse();

        // 1.5
        l_response.bizDateList = l_dateList;

        return l_response;
    }

    /**
     * （get照会画面）<BR>
     * <BR>
     * 株式銘柄条件照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者株式銘柄条件照会サービス)get照会画面」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondReferenceService process<BR>
     * <BR>
     * Refer to the sequence diagram "(administrator: equity product condition
     * reference service)getReferenceService"<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・株式銘柄条件照会リクエストオブジェクト<BR>
     * <BR>
     * WEB3AdminPMProductCondRefReferenceRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@throws DataFindException DataFindException
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@roseuid 4190C78200F1
     */
    protected WEB3AdminPMProductCondRefReferenceResponse
        getReferenceScreen(WEB3AdminPMProductCondRefReferenceRequest l_request)
        throws WEB3BaseException, NotFoundException,
        DataFindException, DataNetworkException, DataQueryException
    {
        WEB3AdminPMProductCondRefReferenceResponse l_response = null;
        EqtypeProductParams l_eqtypeProductParams = null;
        EqtypeTradedProductParams l_tradedProductParams = null;
        WEB3Administrator l_admin = null;
        WEB3GentradeAccountManager l_accountManager = null;
        WEB3EquityProductManager l_equityProductManager = null;
        WEB3EquityProduct l_equityProduct = null;
        WEB3AdminPMProductRegistInfoUnit[] l_adminPMProductRegisterInfoUnitList = null;
        WEB3AdminPMProductCondInfoUnit[] l_adminPMProductCondInfoList = null;
        WEB3AdminPMCompResultInfoUnit[] l_adminPMCompResultInfoUnitList = null;
        WEB3AdminPMProductCondInfoUnit l_adminPMProductCondInfoUnit = null;
        WEB3AdminPMCompResultInfoUnit l_adminPMCompResultInfoUnit = null;
        Branch l_branch = null;
        BranchParams l_branchParams = null;
        Product l_product = null;
        ProductRow l_productRow = null;
        ProductManager l_productManager = null;
        Institution l_institution = null;
        Timestamp l_tsTimestamp = null;
        HashMap l_eqtypeTradedProductParamsList = null;
        ArrayList l_arrayListPMProductCondInfoUnit = null;
        ArrayList l_arrayListPMProductRegistInfoUnit = null;
        ArrayList l_arrayListPMCompResultInfoUnit = null;
        List l_arrSmallItemDivList = null;
        Date[] l_bizDateList = null;
        Date l_requestbizDate = null;
        Date l_bizDate = null;
        Date l_nextBizDate = null;
        Date l_next2BizDate = null;
        boolean l_isUpdate = false;
        boolean l_isInstitutionStockEvaluation = false;
        long l_lngProductId = 0;
        String[] l_strSmallItemDivList = null;
        String l_largetItemDiv = null;
        String l_strBranchCode = null;
        String l_strProductCode = null;
        String l_strInstitutionCode = null;
        String l_strDateDiv = null;
        String l_strMarketCode = null;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_productManager =  l_tradingModule.getProductManager();

        // 1.1
        l_request.validate();

        // 1.2
        l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3
        l_admin.validateAuthority(WEB3TransactionCategoryDef.EQTYPE_PRODUCT_SETTING, l_isUpdate);

        // 1.4
        l_institution = l_admin.getInstitution();

        // 1.5
        l_strBranchCode = l_admin.getBranchCode();

        // 1.6
        l_branch = l_accountManager.getBranch(l_institution, l_strBranchCode);
        l_branchParams = (BranchParams) l_branch.getDataSourceObject();

        // 1.7
        l_bizDateList = this.getBizDateList();

        // 1.8
        l_equityProductManager = (WEB3EquityProductManager) l_productManager;
        l_strProductCode = l_request.productCode;

        try
        {
            l_equityProduct =
                (WEB3EquityProduct) l_equityProductManager.getProduct(
                    l_institution,
                    l_strProductCode);
        } catch (NotFoundException l_notFoundException)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                this.getClass().getName());
        }

        // 1.9
        l_lngProductId = l_equityProduct.getProductId();
        l_product = (Product) l_productManager.getProduct(l_lngProductId);
        l_productRow = ProductDao.findRowByPk(l_product.getProductId());

        l_eqtypeProductParams = (EqtypeProductParams) l_product.getDataSourceObject();
        l_strInstitutionCode = l_institution.getInstitutionCode();

        l_requestbizDate = l_request.bizDate;
        l_bizDate = l_bizDateList[0];
        l_nextBizDate = l_bizDateList[1];
        l_next2BizDate = l_bizDateList[2];

        // To check l_request.bizDate with bizDate, nextBizDate and next2BizDate
        if (l_bizDate.equals(l_requestbizDate) || l_requestbizDate == null)
        {
            l_strDateDiv = WEB3BizDateCalcParameterDef.DAY_BIZ_DATE;
        } else if (l_nextBizDate.equals(l_requestbizDate))
        {
            l_strDateDiv = WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE;
        } else if (l_next2BizDate.equals(l_requestbizDate))
        {
            l_strDateDiv = WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE;
        }

        // 1.10
        l_eqtypeTradedProductParamsList =
            l_equityProductManager.getEqtypeTradedProducts(
                l_lngProductId,
                l_strInstitutionCode,
                l_strDateDiv);

        // 1.11
        // To check listingCodeList = null
        if (l_eqtypeTradedProductParamsList == null)
        {
            // 1.11.1
            l_response = (WEB3AdminPMProductCondRefReferenceResponse) l_request.createResponse();

            // 1.11.2
            l_tsTimestamp =
                (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

            l_response.currentDate = l_tsTimestamp;
            l_response.bizDateList = l_bizDateList;
            l_response.productCode = l_equityProduct.getProductCode();
            l_response.productName = l_eqtypeProductParams.getStandardName();
            l_response.settlementDate = l_equityProduct.getYearlyBooksClosingDate();
            
            if (l_eqtypeProductParams.trade_stop == null)
            {
            	l_response.equityDealingStatus = null;
            }else
            {
				l_response.equityDealingStatus = String.valueOf(l_eqtypeProductParams.trade_stop);
            }

            // To check Def.Enforcement = margin_sys_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div))
            {
            	if (l_eqtypeProductParams.margin_sys_trade_stop == null)
            	{
					l_response.marketMarginDealingStatus = null;
            	}else
            	{
					l_response.marketMarginDealingStatus = 
				    	String.valueOf(l_eqtypeProductParams.margin_sys_trade_stop);
            	}
            }

            // To check Def.Enforcement = margin_sys_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
            {
            	if (l_eqtypeProductParams.margin_gen_trade_stop == null)
            	{
					l_response.institutionMarginDealingStatus = null;
            	}else
            	{
					l_response.institutionMarginDealingStatus =
						String.valueOf(l_eqtypeProductParams.margin_gen_trade_stop);
            	}
            }
            
            if (l_eqtypeProductParams.capital_gain_tax_dealings_reg == null)
            {
				l_response.capitalGainRegulation = null;
            }else
            {
				l_response.capitalGainRegulation =
					String.valueOf(l_eqtypeProductParams.capital_gain_tax_dealings_reg);
            }

            // To check Def.Enforcement = margin_sys_div or Def.Enforcement = margin_gen_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div)
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
            {
                l_response.substituteAssessmentRate =
                    WEB3StringTypeUtility.formatNumber(l_productRow.getMarginRatio());
            }
            
			WEB3GentradeInstitution l_gentradeInsitution =
				(WEB3GentradeInstitution) l_product.getInstitution();
			l_isInstitutionStockEvaluation = l_gentradeInsitution.isInstitutionStockEvaluation();

			// To check l_isInstitutionStockEvaluation is true
			if (l_isInstitutionStockEvaluation)
			{
	            l_response.depositSecuritiesAssessmentRate =
	                WEB3StringTypeUtility.formatNumber(l_productRow.getSecuritiesEstimationRatio());
			}
			                
			if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div)
				|| WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div)
				|| l_isInstitutionStockEvaluation)
			{
	            l_response.substituteSecurityAssetPrice =
	                WEB3StringTypeUtility.formatNumber(l_productRow.getEstimationPrice());
			}
			                
			if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.mstk_div))
			{
				l_response.miniFlag = true;
			}else
			{
				l_response.miniFlag = false;
			}

            l_response.miniProductInfoList = null;
            l_response.compResultInfoList = null;
            l_response.marketProductCondInfoList = null;

            // 1.11.3
            return l_response;
        }

        // 1.12
        l_arrayListPMProductCondInfoUnit = new ArrayList();

        // 1.13
        Iterator l_iterator = new TreeSet(l_eqtypeTradedProductParamsList.keySet()).iterator();
        while (l_iterator.hasNext())
        {
            // 1.13.1
            l_strMarketCode = l_iterator.next().toString();
            l_tradedProductParams =
                (EqtypeTradedProductParams) l_eqtypeTradedProductParamsList.get(l_strMarketCode);

            // 1.13.2
            l_adminPMProductCondInfoUnit = new WEB3AdminPMProductCondInfoUnit();

            // 1.13.3
            l_arrayListPMProductRegistInfoUnit = new ArrayList();

            // 1.13.4
            l_largetItemDiv = WEB3AdminEquityLargeItemDivDef.BASIC_INFO;

            l_strSmallItemDivList = new String[5];
            l_strSmallItemDivList[0] = WEB3AdminEquitySmallItemDivDef.LIST_TYPE;
            l_strSmallItemDivList[1] = WEB3AdminEquitySmallItemDivDef.LOT_SIZE;
            l_strSmallItemDivList[2] = WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT;
            l_strSmallItemDivList[3] = WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV;
            l_strSmallItemDivList[4] = WEB3AdminEquitySmallItemDivDef.TODAY_DEP_FUND_REG;

            this.setProductRegisteredInfo(
                l_largetItemDiv,
                l_strSmallItemDivList,
                l_tradedProductParams,
                l_arrayListPMProductRegistInfoUnit);

            // 1.13.5
            l_largetItemDiv = WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION;

            l_arrSmallItemDivList = new ArrayList();
            l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.BUY_CASH_STOP);
            l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.SELL_CASH_STOP);

            // To check Def.Enforcement = margin_sys_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div))
            {
                l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_SYS_STOP);
                l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_SYS_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_SYS_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_SYS_STOP);
                l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_SYS_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_SYS_STOP);
            }

            // To check Def.Enforcement = margin_gen_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
            {
                l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_GEN_STOP);
                l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_GEN_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_GEN_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_GEN_STOP);
                l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_GEN_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_GEN_STOP);
            }
            l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.BUY_SPOT_MARKET_ORD_DES_STOP);
            l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.SELL_SPOT_MARKET_ORD_DES_STOP);

            // To check Def.Enforcement = margin_sys_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div))
            {
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.LONG_MS_MARKET_ORD_DES_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_MS_MARKET_ORD_DES_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.LONG_CMS_MARKET_ORD_DES_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_CMS_MARKET_ORD_DES_STOP);
            }

            // // To check Def.Enforcement = margin_gen_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
            {
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.LONG_MG_MARKET_ORD_DES_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_MG_MARKET_ORD_DES_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.LONG_CMG_MARKET_ORD_DES_STOP);
                l_arrSmallItemDivList.add(
                    WEB3AdminEquitySmallItemDivDef.SHORT_CMG_MARKET_ORD_DES_STOP);
            }

            l_strSmallItemDivList = new String[l_arrSmallItemDivList.size()];
            l_arrSmallItemDivList.toArray(l_strSmallItemDivList);

            this.setProductRegisteredInfo(
                l_largetItemDiv,
                l_strSmallItemDivList,
                l_tradedProductParams,
                l_arrayListPMProductRegistInfoUnit);

            // 1.13.6
            // To check Def.Enforcement = margin_sys_div or Def.Enforcement = margin_sys_div
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div)
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
            {
                // 1.13.6.1
                l_largetItemDiv = WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO;
                l_arrSmallItemDivList = new ArrayList();

                // To check Def.Enforcement = margin_sys_div
                if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div))
                {
                    l_arrSmallItemDivList.add(
                        WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE);
                }

				// To check Def.Enforcement = margin_gen_div
				if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
				{
					l_arrSmallItemDivList.add(
						WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE);
				}
                
				l_strSmallItemDivList = new String[l_arrSmallItemDivList.size()];
				l_arrSmallItemDivList.toArray(l_strSmallItemDivList);
                
				this.setProductRegisteredInfo(
					l_largetItemDiv,
					l_strSmallItemDivList,
					l_tradedProductParams,
					l_arrayListPMProductRegistInfoUnit);
				
								
				l_largetItemDiv = WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE;
				l_strSmallItemDivList = new String[4];
				
				l_strSmallItemDivList[0] = WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE;
				l_strSmallItemDivList[1] = WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE;
				l_strSmallItemDivList[2] = WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE;
				l_strSmallItemDivList[3] = WEB3AdminEquitySmallItemDivDef.SHORT_CASH_MARGIN_DEPOSIT_RATE;
				
				l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE);
				l_arrSmallItemDivList.add(WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE);
				l_arrSmallItemDivList.add(
					WEB3AdminEquitySmallItemDivDef.LONG_CASH_MARGIN_DEPOSIT_RATE);
				l_arrSmallItemDivList.add(
					WEB3AdminEquitySmallItemDivDef.SHORT_CASH_MARGIN_DEPOSIT_RATE);

				this.setProductRegisteredInfo(
					l_largetItemDiv,
					l_strSmallItemDivList,
					l_tradedProductParams,
					l_arrayListPMProductRegistInfoUnit);
            }
            
            // 1.13.7
            l_largetItemDiv = WEB3AdminEquityLargeItemDivDef.PRICE_INFO;
            l_strSmallItemDivList = new String[4];
            l_strSmallItemDivList[0] = WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE;
            l_strSmallItemDivList[1] = WEB3AdminEquitySmallItemDivDef.BASE_PRICE;
            l_strSmallItemDivList[2] = WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE;
            l_strSmallItemDivList[3] = WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE;

            this.setProductRegisteredInfo(
                l_largetItemDiv,
                l_strSmallItemDivList,
                l_tradedProductParams,
                l_arrayListPMProductRegistInfoUnit);

            // 1.13.8
            l_adminPMProductCondInfoUnit.marketCode = l_strMarketCode;
            WEB3AdminPMProductRegistInfoUnit[] l_productRegistInfoUnitList =
                new WEB3AdminPMProductRegistInfoUnit[l_arrayListPMProductRegistInfoUnit.size()];
            l_adminPMProductCondInfoUnit.productRegistInfoList =
                (WEB3AdminPMProductRegistInfoUnit[]) l_arrayListPMProductRegistInfoUnit.toArray(
                l_productRegistInfoUnitList);

            // 1.13.9
            l_arrayListPMProductCondInfoUnit.add(l_adminPMProductCondInfoUnit);
        }

        // 1.14
        // To check Def.Enforcement == l_branchParams.mstk_div
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.mstk_div))
        {
            // 1.14.1
            EqtypeTradedProductParams[] l_tradedProducts =
                new EqtypeTradedProductParams[l_eqtypeTradedProductParamsList.size()];
            l_tradedProductParams =
                l_equityProductManager.getMiniStockTradedProduct(
                    ((EqtypeTradedProductParams[]) l_eqtypeTradedProductParamsList.values().toArray(
                    l_tradedProducts)));

            // 1.14.2
            if (l_eqtypeTradedProductParamsList != null)
            {
	            l_adminPMProductRegisterInfoUnitList =
                this.createMiniStockProductInfoList(l_tradedProductParams);
			}
		}

        // 1.15
        l_arrayListPMCompResultInfoUnit = new ArrayList();

        // 1.16
        if (l_eqtypeTradedProductParamsList.size() > 1)
        {
	        int l_intSize = ((WEB3AdminPMProductCondInfoUnit)
	                 l_arrayListPMProductCondInfoUnit.get(0)).productRegistInfoList.length;
			for (int i = 0; i < l_intSize; i++)
			{
				// 1.16.1
				l_adminPMCompResultInfoUnit = new WEB3AdminPMCompResultInfoUnit();
	
				// 1.16.2
				int l_intSize2 = l_arrayListPMProductCondInfoUnit.size() - 1;
				for (int j = 0; j < l_intSize2; j++)
	            {
	                String l_registData1 = ((WEB3AdminPMProductCondInfoUnit)
	                    l_arrayListPMProductCondInfoUnit.get(j)).productRegistInfoList[i].registData;
	                String l_registData2 = ((WEB3AdminPMProductCondInfoUnit)
	                    l_arrayListPMProductCondInfoUnit.
	                    get(j + 1)).productRegistInfoList[i].registData;
	
	                // To check l_registData1 != null and l_registData1 = l_registData2
					if (l_registData1 != null)
					{
						if (l_registData1.equals(l_registData2))
		                {
		                    l_adminPMCompResultInfoUnit.allAgreementFlag = true;
		                } else
		                {
		                    l_adminPMCompResultInfoUnit.allAgreementFlag = false;
		                }
					}else
					{
						if (l_registData1 == l_registData2)
						{
							l_adminPMCompResultInfoUnit.allAgreementFlag = true;
						} else
						{
							l_adminPMCompResultInfoUnit.allAgreementFlag = false;
						}
						
					}
	
	                // To check allAgreementFlag = false
	                if (!l_adminPMCompResultInfoUnit.allAgreementFlag)
	                {
	                    break;
	                }
	            }

	            // 1.16.3
	            l_adminPMCompResultInfoUnit.largeItemDiv = ((WEB3AdminPMProductCondInfoUnit)
	                l_arrayListPMProductCondInfoUnit.get(0)).productRegistInfoList[i].largeItemDiv;
	            l_adminPMCompResultInfoUnit.smallItemDiv = ((WEB3AdminPMProductCondInfoUnit)
	                l_arrayListPMProductCondInfoUnit.get(0)).productRegistInfoList[i].smallItemDiv;
	
	            // 1.16.4
	            l_arrayListPMCompResultInfoUnit.add(l_adminPMCompResultInfoUnit);
			}
        }
        // 1.17
        l_response = (WEB3AdminPMProductCondRefReferenceResponse) l_request.createResponse();
        // 1.18
        l_tsTimestamp =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        l_response.currentDate = l_tsTimestamp;
        l_response.bizDateList = l_bizDateList;
        l_response.productCode = l_equityProduct.getProductCode();
        l_response.productName = l_eqtypeProductParams.getStandardName();
        
        //優先市場のセット
        if (l_productRow.getPrimaryMarketId() == 0
             || l_productRow.getPrimaryMarketIdIsNull())
        {
        	l_response.primaryMarket = null;
        }else{
			WEB3GentradeFinObjectManager l_finObjectManager =
				(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
			WEB3GentradeMarket l_market = 
				(WEB3GentradeMarket)l_finObjectManager.getMarket(l_productRow.getPrimaryMarketId());
			l_response.primaryMarket = l_market.getMarketCode();
        }
        
        l_response.settlementDate = l_equityProduct.getYearlyBooksClosingDate();
        
        if (l_eqtypeProductParams.trade_stop == null)
        {
			l_response.equityDealingStatus = null;
        }else
        {
			l_response.equityDealingStatus = String.valueOf(l_eqtypeProductParams.trade_stop);
        }

        // To check Def.Enforcement = margin_sys_div
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div))
        {
        	if (l_eqtypeProductParams.margin_sys_trade_stop == null)
        	{
				l_response.marketMarginDealingStatus = null;
        	}else
        	{
				l_response.marketMarginDealingStatus =
					String.valueOf(l_eqtypeProductParams.margin_sys_trade_stop);
        	}
        }

        // To check Def.Enforcement = margin_gen_div
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
        {
        	if (l_eqtypeProductParams.margin_gen_trade_stop == null)
        	{
				l_response.institutionMarginDealingStatus = null;
        	}else
        	{
	            l_response.institutionMarginDealingStatus =
	                String.valueOf(l_eqtypeProductParams.margin_gen_trade_stop);
        	}
        }
        
        if (l_eqtypeProductParams.capital_gain_tax_dealings_reg == null)
        {
			l_response.capitalGainRegulation = null;
        }else
        {
			l_response.capitalGainRegulation =
				String.valueOf(l_eqtypeProductParams.capital_gain_tax_dealings_reg);
        }

        // To check Def.Enforcement = margin_sys_div or Def.Enforcement = margin_sys_div
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
        {
            l_response.substituteAssessmentRate =
                WEB3StringTypeUtility.formatNumber(l_productRow.getMarginRatio());
        }

        WEB3GentradeInstitution l_gentradeInsitution =
            (WEB3GentradeInstitution) l_product.getInstitution();
        l_isInstitutionStockEvaluation = l_gentradeInsitution.isInstitutionStockEvaluation();

        // To check l_isInstitutionStockEvaluation is true
        if (l_isInstitutionStockEvaluation)
        {
            l_response.depositSecuritiesAssessmentRate =
                WEB3StringTypeUtility.formatNumber(l_productRow.getSecuritiesEstimationRatio());
        }

        // To check Def.Enforcement = margin_sys_div or Def.Enforcement = margin_sys_div
        // or l_isInstitutionStockEvaluation is true
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div)
            || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div)
            || l_isInstitutionStockEvaluation)
        {
            l_response.substituteSecurityAssetPrice =
                WEB3StringTypeUtility.formatNumber(l_productRow.getEstimationPrice());
        }
        
        if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.mstk_div))
        {
        	l_response.miniFlag = true;
        }else
        {
        	l_response.miniFlag = false;
        }

        l_response.miniProductInfoList = l_adminPMProductRegisterInfoUnitList;
        
        if (l_arrayListPMCompResultInfoUnit.size() == 0)
        {
			l_response.compResultInfoList = null;
        }else
        {
	        l_adminPMCompResultInfoUnitList =
	                    new WEB3AdminPMCompResultInfoUnit[l_arrayListPMCompResultInfoUnit.size()];
	        l_response.compResultInfoList = (WEB3AdminPMCompResultInfoUnit[])
	                l_arrayListPMCompResultInfoUnit.toArray(l_adminPMCompResultInfoUnitList);
        }

        l_adminPMProductCondInfoList =
                    new WEB3AdminPMProductCondInfoUnit[l_arrayListPMProductCondInfoUnit.size()];
        l_response.marketProductCondInfoList = (WEB3AdminPMProductCondInfoUnit[])
                            l_arrayListPMProductCondInfoUnit.toArray(l_adminPMProductCondInfoList);

        // 1.18
        return l_response;
    }

    /**
     * （set銘柄登録情報）<BR>
     * <BR>
     * 引数の小項目区分一覧に該当する値をセットした<BR>
     * 銘柄条件登録情報インスタンスの一覧を<BR>
     * 引数の銘柄登録情報一覧にセットする。<BR>
     * <BR>
     * １）以下の処理をパラメータ.小項目区分一覧について繰り返す。<BR>
     * 　@１－１）銘柄登録情報インスタンスを生成する。<BR>
     * <BR>
     * 　@１－２）銘柄登録情報インスタンスに以下のプロパティをセットする。<BR>
     * 　@　@大項目区分 = パラメータ.大項目区分<BR>
     * 　@　@小項目区分 = パラメータ.小項目区分<BR>
     * 　@　@登録値 = 商品管理(株式)データマネージャ.get取引銘柄登録値()の戻り値<BR>
     * <BR>
     * 　@　@　@[get取引銘柄登録値()にセットするパラメータ]<BR>
     * 　@　@　@　@取引銘柄：　@パラメータ.取引銘柄<BR>
     * 　@　@　@　@小項目区分：　@パラメータ.小項目区分<BR>
     * <BR>
     * 　@１－３）パラメータ.銘柄登録情報一覧.add()メソッドをコールし、<BR>
     * 　@　@　@　@　@生成した銘柄登録情報を追加する。<BR>
     * <BR>
     * 　@　@　@　@　@[add()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@obj：　@１－２）にてプロパティセットした銘柄登録情報<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Set the list of WEB3AdminPMProductRegistInfoUnit instance,<BR>
     * which sets values corresponding to the arguments of smallItemDiv,<BR>
     * to the arguments of productRegistInfoList.<BR>
     * <BR>
     * 1) Loop the process for as many times as parameter.smallItemDiv<BR>
     * 　@1-1) Create WEB3AdminPMProductRegistInfoUnit instance<BR>
     * <BR>
     * 　@1-2) Set WEB3AdminPMProductRegistInfoUnit instance into 'Property Set'.<BR>
     * 　@　@largeItemDiv = parameter.largeItemDiv<BR>
     * 　@　@smallItemDiv = parameter.smallItemDiv<BR>
     * 　@　@registData = return value of
     * WEB3AdminPMEquityDataManager.getTradedProductRegistrationValue()<BR>
     * <BR>
     * 　@　@　@[parameter set into getTradedProductRegistrationValue()]<BR>
     * 　@　@　@　@l_tradedProduct: parameter.tradedProduct<BR>
     * 　@　@　@　@l_strSmallItemDiv: parameter.smallItemDiv<BR>
     * <BR>
     * 　@1-3)Call parameter.productRegistInfoList.add() method, and<BR>
     * 　@　@　@　@　@add the created WEB3AdminPMProductRegistInfoUnit<BR>
     * <BR>
     * 　@　@　@　@　@[parameter set into add()]<BR>
     * 　@　@　@　@　@　@obj: WEB3AdminPMProductRegistInfoUnit set into 'Property Set' at
     * 1-2)<BR>
     * <BR>
     * @@param l_largeItemDiv - （大項目区分）<BR>
     * <BR>
     * ※定義値についてはDBレイアウト<BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_largeItemDiv<BR>
     * <BR>
     * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     * @@param l_strSmallItemDivList - （小項目区分一覧）<BR>
     * <BR>
     * ※小項目区分の任意の値を要素とする配列<BR>
     * <BR>
     * ※定義値についてはDBレイアウト<BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_strSmallItemDivList<BR>
     * <BR>
     * ※Array of whose elements are arbitrary values of smallItemDiv<BR>
     * <BR>
     * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     * @@param l_tradedProductParams - （取引銘柄）<BR>
     * <BR>
     * 取引銘柄オブジェクト<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * （l_tradedProduct）<BR>
     * <BR>
     * l_tradedProduct object<BR>
     * <BR>
     *
     * @@param l_marketProductCondInfoList - （銘柄登録情報一覧）<BR>
     * <BR>
     * 銘柄登録情報を格納するArrayList<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_marketProductCondInfoList<BR>
     * <BR>
     * ArrayList that stores WEB3AdminPMProductRegistInfoUnit<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41919EB90134
     */
    protected void setProductRegisteredInfo(
        String l_largeItemDiv,
        String[] l_strSmallItemDivList,
        EqtypeTradedProductParams l_tradedProductParams,
        ArrayList l_marketProductCondInfoList)
        throws WEB3BaseException, NotFoundException
    {
        WEB3AdminPMProductRegistInfoUnit l_adminPMProductRegisterInfoUnit = null;
        WEB3AdminPMEquityDataManager l_dataManager = new WEB3AdminPMEquityDataManager();
        String l_strSmallItemDiv = null;
        String l_registData = null;
        int l_listSize = l_strSmallItemDivList.length;

        for (int i = 0; i < l_listSize; i++)
        {
            // 1.1
            l_adminPMProductRegisterInfoUnit = new WEB3AdminPMProductRegistInfoUnit();

            // 1.2
            l_strSmallItemDiv = l_strSmallItemDivList[i];
            l_registData =
                l_dataManager.getTradedProductRegistrationValue(
                    l_tradedProductParams,
                    l_strSmallItemDiv);
            l_adminPMProductRegisterInfoUnit.largeItemDiv = l_largeItemDiv;
            l_adminPMProductRegisterInfoUnit.smallItemDiv = l_strSmallItemDiv;
            l_adminPMProductRegisterInfoUnit.registData = l_registData;

            // 1.3
            l_marketProductCondInfoList.add(l_adminPMProductRegisterInfoUnit);
        }

    }

    /**
     * （createミニ株銘柄情報一覧）<BR>
     * <BR>
     * ミニ株銘柄情報をセットした銘柄条件登録情報インスタンスの一覧を<BR>
     * 返却する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * ２）パラメータ.取引銘柄 == nullの場合、以下の処理を実施する。<BR> 
     *　@２－１）銘柄登録情報インスタンスを生成する。 <BR>
     *　@２－２）銘柄登録情報インスタンスに以下のプロパティをセットする。<BR> 
     *         大項目区分 = null <BR>
     *         小項目区分 = "株式ミニ投資市場"<BR> 
     *　@   　@　@登録値 = "非取扱" <BR>
     *　@２－３）プロパティセットしたインスタンスをArrayListに追加する。<BR> 
     *　@２－４）生成したArrayList.toArray()の戻り値を返却する。 <BR>
     *<BR>
     * ３）以下の処理を処理対象となる小項目区分(*1)について繰り返す。<BR>
     * 　@３－１）銘柄登録情報インスタンスを生成する。<BR>
     * <BR>
     * 　@３－２）銘柄登録情報インスタンスに以下のプロパティをセットする。<BR>
     * 　@　@大項目区分 = null<BR>
     * 　@　@小項目区分 = 処理対象の小項目区分<BR>
     * 　@　@登録値 =<BR>
     * 　@　@　@[処理対象の小項目区分 == "買ミニ株制限時間" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"売ミニ株制限時間"の場合]<BR>
     * 　@　@　@　@this.getミニ株制限時間()の戻り値をセット。<BR>
     * <BR>
     * 　@　@　@　@[getミニ株制限時間()にセットするパラメータ]<BR>
     * 　@　@　@　@　@取引銘柄：　@パラメータ.取引銘柄<BR>
     * 　@　@　@　@　@is買付：　@処理対象の小項目区分 == "買ミニ株制限時間"の場合、true。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@以外、falseをセット。<BR>
     * <BR>
     * 　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@商品管理(株式)データマネージャ.get取引銘柄登録値()の戻り値をセット。<BR>
     * <BR>
     * 　@　@　@　@[get取引銘柄登録値()にセットするパラメータ]<BR>
     * 　@　@　@　@　@取引銘柄：　@パラメータ.取引銘柄<BR>
     * 　@　@　@　@　@小項目区分：　@処理対象の小項目区分<BR>
     * <BR>
     * 　@３－３）生成したArrayList.add()メソッドをコールし、<BR>
     * 　@　@　@　@　@生成した銘柄登録情報を追加する。<BR>
     * <BR>
     * 　@　@　@　@　@[add()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@obj：　@２－２）にてプロパティセットした銘柄登録情報<BR>
     * <BR>
     * ４）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)処理対象の小項目区分<BR>
     * 　@※上から順に処理する。<BR>
     * 　@・"株式ミニ投資市場"<BR>
     * 　@・"買ミニ株停止"<BR>
     * 　@・"売ミニ株停止"<BR>
     * 　@・"買ミニ株制限時間"<BR>
     * 　@・"売ミニ株制限時間"<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * createMiniStockProductInfoList<BR>
     * <BR>
     * Return the list of WEB3AdminPMProductRegistInfoUnit instance set
     * miniProductInfo<BR>
     * <BR>
     * 1) Create ArrayList<BR>
     * 2)Loop the process for as many times as smallItemDiv(*1) to be processed<BR>
     * 　@2-1)Create WEB3AdminPMProductRegistInfoUnit instance<BR>
     * <BR>
     * 　@2-2)Set the following properties into WEB3AdminPMProductRegistInfoUnit
     * instance<BR>
     * 　@　@largeItemDiv = null<BR>
     * 　@　@smallItemDiv = smallItemDiv to be processed<BR>
     * 　@　@registData =<BR>
     * 　@　@　@[smallItemDiv to be processed== If "Def.BUY_MINI_STOCK_TIME_LIMIT" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"Def.SELL_MINI_STOCK_TIME_LIMIT"]<BR>
     * 　@　@　@　@Set the return value of this.getMiniStockTimeLimit()<BR>
     * <BR>
     * 　@　@　@　@[parameter set into getMiniStockTimeLimit()]<BR>
     * 　@　@　@　@　@l_tradedProduct: parameter.tradedProduct<BR>
     * 　@　@　@　@　@l_isAcquired: smallItemDiv to be processed == If
     * "Def.BUY_MINI_STOCK_TIME_LIMIT", set true<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@if not, set false<BR>
     * <BR>
     * 　@　@　@[For other cases]<BR>
     * 　@　@　@　@Set return value of
     * WEB3AdminPMEquityDataManager.getTradedProductRegistrationValue()<BR>
     * <BR>
     * 　@　@　@　@[parameter set into getTradedProductRegistrationValue()]<BR>
     * 　@　@　@　@　@l_tradedProduct: parameter.tradedProduct<BR>
     * 　@　@　@　@　@l_strSmallItemDiv: smallItemDiv to be processed<BR>
     * <BR>
     * 　@2-3) Call the careated ArrayList.add() method, and<BR>
     * 　@　@　@　@　@add the created WEB3AdminPMProductRegistInfoUnit<BR>
     * <BR>
     * 　@　@　@　@　@[parameter set into add()]<BR>
     * 　@　@　@　@　@　@obj: WEB3AdminPMProductRegistInfoUnit set into 'Property Set' at
     * 2-2)<BR>
     * <BR>
     * 3)Return the return value of the created ArrayList.toArray()<BR>
     * <BR>
     * (*1)smallItemDiv to be processed<BR>
     * 　@※Process the followings sequentially<BR>
     * 　@・"Def.MINI_STOCK_MARKET"<BR>
     * 　@・"Def.BUY_MINI_STOCK_STOP"<BR>
     * 　@・"Def.SELL_MINI_STOCK_STOP"<BR>
     * 　@・"Def.BUY_MINI_STOCK_TIME_LIMIT"<BR>
     * 　@・"Def.SELL_MINI_STOCK_TIME_LIMIT"<BR>
     * <BR>
     * @@param l_tradedProductParams - （取引銘柄）<BR>
     * <BR>
     * 取引銘柄オブジェクト<BR>
     * <BR>
     * l_tradedProduct<BR>
     * <BR>
     * l_tradedProduct object<BR>
     * <BR>
     * @@return WEB3AdminPMProductRegistInfoUnit[]
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 4191A42701D0
     */
    protected WEB3AdminPMProductRegistInfoUnit[]
        createMiniStockProductInfoList(EqtypeTradedProductParams l_tradedProductParams)
        throws WEB3BaseException, NotFoundException
    {
        WEB3AdminPMProductRegistInfoUnit l_adminPMProductRegisterInfoUnit = null;
        WEB3AdminPMProductRegistInfoUnit[] l_adminPMProductRegisterInfoUnitList = null;
        WEB3AdminPMEquityDataManager l_dataManager = new WEB3AdminPMEquityDataManager();
        ArrayList l_arrayList = null;
        boolean l_isAcquired = false;
        String l_strSmallItemDiv = null;
        String[] l_smallItemDivList =
            {
                WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET,
                WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP,
                WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP,
                WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_TIME_LIMIT,
                WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_TIME_LIMIT };
        int l_intSize = 0;

        // 1.1
        l_arrayList = new ArrayList();

        // 1.2
        l_intSize = l_smallItemDivList.length;
        
        //2
		
        if (l_tradedProductParams ==null)
        {
        	//2.1
			l_adminPMProductRegisterInfoUnit = new WEB3AdminPMProductRegistInfoUnit();
			
			//2.2
			l_adminPMProductRegisterInfoUnit.largeItemDiv = null;
			l_adminPMProductRegisterInfoUnit.smallItemDiv = WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET;
			l_adminPMProductRegisterInfoUnit.registData = String.valueOf(BooleanEnum.FALSE.intValue());
			
			//2.3
			l_arrayList.add(l_adminPMProductRegisterInfoUnit);
			
			//2.4
			return (WEB3AdminPMProductRegistInfoUnit[])l_arrayList.toArray(new WEB3AdminPMProductRegistInfoUnit[0]);
        }

        for (int i = 0; i < l_intSize; i++)
        {
            // 3.1
            l_adminPMProductRegisterInfoUnit = new WEB3AdminPMProductRegistInfoUnit();
            l_strSmallItemDiv = l_smallItemDivList[i];
            l_adminPMProductRegisterInfoUnit.largeItemDiv = null;
            l_adminPMProductRegisterInfoUnit.smallItemDiv = l_strSmallItemDiv;

            // 3.2
            if (WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_TIME_LIMIT.equals(l_strSmallItemDiv)
                || WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_TIME_LIMIT.equals(
                    l_strSmallItemDiv))
            {

                // To check Def.BUY_MINI_STOCK_TIME_LIMIT = l_strSmallItemDiv
                if (WEB3AdminEquitySmallItemDivDef
                    .BUY_MINI_STOCK_TIME_LIMIT
                    .equals(l_strSmallItemDiv))
                {
                    l_isAcquired = true;
                } else
                {
                    l_isAcquired = false;
                }
                l_adminPMProductRegisterInfoUnit.registData =
                    this.getMiniStockTimeLimit(l_tradedProductParams, l_isAcquired);
            } else
            {
                l_adminPMProductRegisterInfoUnit.registData =
                    l_dataManager.getTradedProductRegistrationValue(
                        l_tradedProductParams,
                        l_strSmallItemDiv);
            }

            // 3.3
            l_arrayList.add(l_adminPMProductRegisterInfoUnit);
        }

        // 4
        l_adminPMProductRegisterInfoUnitList =
            new WEB3AdminPMProductRegistInfoUnit[l_arrayList.size()];
        l_adminPMProductRegisterInfoUnitList =
            (WEB3AdminPMProductRegistInfoUnit[]) l_arrayList.toArray(
                l_adminPMProductRegisterInfoUnitList);

        // 5
        return l_adminPMProductRegisterInfoUnitList;
    }

    /**
     * （getミニ株制限時間）<BR>
     * <BR>
     * ミニ株制限時間を算出し、返却する。<BR>
     * <BR>
     * １）株式銘柄取得<BR>
     * 　@拡張プロダクトマネージャ.getProduct()メソッドをコールし、 <BR>
     * 　@株式銘柄を取得する。 <BR>
     * <BR>
     * ２）権利落日取得<BR>
     * 　@１）の戻り値.get権利落日()をコールする。<BR>
     * <BR>
     * ３）制限時間算出<BR>
     * 　@制限時間を算出し、文字列連結して返却する。<BR>
     * 　@※日付は、YYYY/MM/DDのフォーマットで文字列変換すること。<BR>
     * <BR>
     * 　@[パラメータ.is買付 == trueの場合]<BR>
     * 　@　@制限時間 = 権利落日の4営業日前<BR>
     * 　@　@　@　@　@　@　@　@　@+ "～"<BR>
     * 　@　@　@　@　@　@　@　@　@+ 権利落日の１営業日前<BR>
     * <BR>
     * 　@[上記以外(売付)の場合]<BR>
     * 　@　@制限時間 = 権利落日<BR>
     * 　@　@　@　@　@　@　@　@　@+ "～"<BR>
     * 　@　@　@　@　@　@　@　@　@+ 権利落日の３営業日後<BR>
     * <BR>
     * ４）算出した制限時間を返却する。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getMiniStockTimeLimit<BR>
     * <BR>
     * Calculate miniStockTimeLimit and return the value.<BR>
     * <BR>
     * 1)Acquire equityProduct<BR>
     * 　@Call WEB3EquityProductManager.getProduct() method<BR>
     * 　@and aquire equityProduct<BR>
     * 　@Call parameter.l_tradedProduct.getProduct()<BR>
     * <BR>
     * 2)Acquire devidentRightDate<BR>
     * 　@Call return value of 1).getDevidentRightDate()<BR>
     * <BR>
     * 3)Calculate timeLimit<BR>
     * 　@Calculate timeLimit, connect strings and return it connecting strings<BR>
     * 　@※Convert a date to string with the format, YYYY/MM/DD<BR>
     * <BR>
     * 　@[If parameter.isAcquired == true]<BR>
     * 　@　@timeLimit = 4 business days before devidentRightDate<BR>
     * 　@　@　@　@　@　@　@　@　@+ "～"<BR>
     * 　@　@　@　@　@　@　@　@　@+ ２ business day before devidentRightDate<BR>
     * <BR>
     * 　@[For other cased(If Sell)]<BR>
     * 　@　@timeLimit = devidentRightDate<BR>
     * 　@　@　@　@　@　@　@　@　@+ "～"<BR>
     * 　@　@　@　@　@　@　@　@　@+ ３ business days after devidentRightDate<BR>
     * <BR>
     * 4)Return the calculated timeLimit<BR>
     * <BR>
     * @@param l_tradedProductParams - （取引銘柄）<BR>
     * <BR>
     * 取引銘柄オブジェクト<BR>
     * <BR>
     * l_tradedProduct<BR>
     * <BR>
     * l_tradedProduct object<BR>
     * <BR>
     * @@param l_isAcquired - （is買付）<BR>
     * <BR>
     * 買付かどうかのフラグ<BR>
     * false：　@売付<BR>
     * true：　@買付<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_isAcquired<BR>
     * <BR>
     * Flag whether it is sell or not<BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws NotFoundException NotFoundException
     * @@roseuid 41986B0D03B1
     */
    protected String getMiniStockTimeLimit(
        EqtypeTradedProductParams l_tradedProductParams,
        boolean l_isAcquired)
        throws WEB3BaseException, NotFoundException
    {
        String l_timeLimit = "";
        Date l_date = null;
        Date l_devidendDate = null;
        Timestamp l_tsBaseDate = null;
        WEB3GentradeBizDate l_gentradeBizDate = null;
        WEB3EquityProduct l_equityProduct = null;
        WEB3EquityProductManager l_equityProductManager = null;
        long l_lngProductId = 0;

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        l_equityProductManager = (WEB3EquityProductManager) l_tradingModule.getProductManager();

        // 1.1
        l_lngProductId = l_tradedProductParams.getProductId();
        l_equityProduct = (WEB3EquityProduct) l_equityProductManager.getProduct(l_lngProductId);

        // 1.2
        l_devidendDate = l_equityProduct.getDevidendRightDate();

        // 1.3
        l_tsBaseDate = new Timestamp(l_devidendDate.getTime());
        l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBaseDate);

        // l_isAcquired is true
        if (l_isAcquired)
        {
            l_date = l_gentradeBizDate.roll(-4);
            l_timeLimit = WEB3DateUtility.formatDate(l_date, "yyyy/MM/dd");
            l_timeLimit += "～";
            l_date = l_gentradeBizDate.roll(-1);
            l_timeLimit += WEB3DateUtility.formatDate(l_date, "yyyy/MM/dd");
        } else
        {
            l_timeLimit = WEB3DateUtility.formatDate(l_devidendDate, "yyyy/MM/dd");
            l_timeLimit += "～";
            l_date = l_gentradeBizDate.roll(3);
            l_timeLimit += WEB3DateUtility.formatDate(l_date, "yyyy/MM/dd");
        }

        return l_timeLimit;
    }

    /**
     * 株式銘柄条件照会処理を行う。<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondReferenceService process<BR>
     * <BR>
     * @@param l_request - （リクエスト）<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4190C7450084
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            if (l_request instanceof WEB3AdminPMProductCondRefInputRequest)
            {
                l_response =
                    this.getProductInputScreen((WEB3AdminPMProductCondRefInputRequest) l_request);

            } else if (l_request instanceof WEB3AdminPMProductCondRefReferenceRequest)
            {
                l_response =
                    this.getReferenceScreen((WEB3AdminPMProductCondRefReferenceRequest) l_request);
            } else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 株式銘柄条件照会リクエスト");
            }
        } catch (NotFoundException l_notFoundException)
        {
            log.error(l_notFoundException.getMessage(), l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_notFoundException.getMessage(),
                l_notFoundException);
        } catch (DataFindException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        } catch (DataNetworkException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        } catch (DataQueryException l_dataException)
        {
            log.error(l_dataException.getMessage(), l_dataException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataException.getMessage(),
                l_dataException);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （get営業日一覧）<BR>
     * <BR>
     * 営業日の一覧を作成し、返却する。 <BR>
     * ※格納順序は、当営業日、翌営業日、翌々営業日の順 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）TradingSystem.getBizDate()メソッドをコールし業務日付を <BR>
     * 　@取得する。 <BR>
     * <BR>
     * ３）ArrayList.add()メソッドにて取得した業務日付を追加する。 <BR>
     * <BR>
     * 　@[add()にセットするパラメータ] <BR>
     * 　@　@obj：　@２）の戻り値をDate型に変換してセット。 <BR>
     * <BR>
     * ４）営業日計算インスタンスを生成する。 <BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ] <BR>
     * 　@　@基準日：　@２）の戻り値をTimestamp型に変換してセット。 <BR>
     * <BR>
     * ５）営業日計算.roll()メソッドにて翌営業日を取得する。 <BR>
     * <BR>
     * 　@[roll()にセットするパラメータ] <BR>
     * 　@　@加算／減算日数：　@1 <BR>
     * <BR>
     * ６）ArrayList.addメソッドにて５）で取得した翌営業日を追加する。 <BR>
     * <BR>
     * 　@[add()にセットするパラメータ] <BR>
     * 　@　@obj：　@５）にて取得した翌営業日をDate型にcastしてセット。 <BR>
     * <BR>
     * ７）営業日計算.roll()メソッドにて翌々営業日を取得する。 <BR>
     * <BR>
     * 　@[roll()にセットするパラメータ] <BR>
     * 　@　@加算／減算日数：　@2 <BR>
     * <BR>
     * ８）ArrayList.addメソッドにて５）で取得した翌々営業日を追加する。 <BR>
     * <BR>
     * 　@[add()にセットするパラメータ] <BR>
     * 　@　@obj：　@７）にて取得した翌々営業日をDate型にcastしてセット。 <BR>
     * <BR>
     * ９）ArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * ------<English>---------------<BR>
     * <BR>
     * getBizDateList<BR>
     * <BR>
     * Create a list of bizDate and return it.<BR>
     * ※Store bizDate, nextBizDate and next2BizDate sequientially<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Call TradingSystem.getBizDate() method and acquire bizDate<BR>
     * <BR>
     * 3)Add the acquired bizDate at ArrayList.add() method<BR>
     * <BR>
     * 　@[parameter set to add()] <BR>
     * 　@　@obj: Convert the return value from 2) to Date type and set it<BR>
     * <BR>
     * 4)Create an instance of WEB3GentradeBizDate<BR>
     * <BR>
     * 　@[parameter set into the constructor] <BR>
     * 　@　@baseDate: Convert the return vleu from 2) to Timestamp typr and set it.<BR>
     * <BR>
     * 5)Acquire nextBizDate at WEB3GentradeBizDate.roll() method<BR>
     * <BR>
     * 　@[parameter set into roll()] <BR>
     * 　@　@l_intRoll: 1 <BR>
     * <BR>
     * 6)Add nextBizDate acquired at 5) at ArrayList.add method<BR>
     * <BR>
     * 　@[parameter set into add()] <BR>
     * 　@　@obj: Convert nextBizDate acquired at 5) to Date type and set it.<BR>
     * <BR>
     * 7)Acquire next2BizDate at WEB3GentradeBizDate.roll() method<BR>
     * <BR>
     * 　@[parameter set into roll()] <BR>
     * 　@　@l_intRoll: 2 <BR>
     * <BR>
     * 8)Add the next2BizDate acquired at WEB3GentradeBizDate.roll() method<BR>
     * <BR>
     * 　@[parameter set into add()] <BR>
     * 　@　@obj: Convert next2BizDate acquired at 7) to Date type and set it.<BR>
     * <BR>
     * 9)Return the return value of ArrayList.toArray()<BR>
     * <BR>
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@return Date[]
     * @@roseuid 42144834024E
     */
    protected Date[] getBizDateList() throws WEB3SystemLayerException
    {
        TradingSystem l_tradingSystem = null;
        WEB3GentradeBizDate l_gentradeBizDate = null;
        Date[] l_dateList = null;
        Date l_bizDate = null;
        Date l_nextBizDate = null;
        Date l_next2BizDate = null;
        ArrayList l_bizDateList = null;
        Timestamp l_timestamp = null;

        // 1. Creating ArrayList
        l_bizDateList = new ArrayList();

        //Assumption to be confitrmed
        l_tradingSystem = new TradingSystemImpl();

        // 2. Calling TradingSystem.getBizDate()
        l_bizDate = l_tradingSystem.getBizDate();

        // 3. Adding the acquired bizDate at ArrayList.add()
        l_bizDateList.add(l_bizDate);

        // 4. Creating an instance of WEB3GentradeBizDate
        l_timestamp = new Timestamp(l_bizDate.getTime());
        l_gentradeBizDate = new WEB3GentradeBizDate(l_timestamp);

        // 5. Acquiring nextBizDate at WEB3GentradeBizDate.roll()
        l_timestamp = l_gentradeBizDate.roll(1);
        l_nextBizDate = new Date(l_timestamp.getTime());

        // 6. Adding nextBizDate acquired at 5) at ArrayList
        l_bizDateList.add(l_nextBizDate);

        // 7. Acquiring next2BizDate at WEB3GentradeBizDate.roll()
        l_timestamp = l_gentradeBizDate.roll(2);
        l_next2BizDate = new Date(l_timestamp.getTime());

        // 8. Adding next2BizDate acquired at 7) at ArrayList
        l_bizDateList.add(l_next2BizDate);

        l_dateList = new Date[l_bizDateList.size()];
        l_dateList = (Date[]) l_bizDateList.toArray(l_dateList);

        return l_dateList;
    }
}@
