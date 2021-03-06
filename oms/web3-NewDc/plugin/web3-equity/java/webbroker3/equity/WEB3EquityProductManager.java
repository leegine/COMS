head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : g£v_Ng}l[W(WEB3EquityProductManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/20 ûü´@@Ça(SRA) VKì¬
Revesion History : 2006/07/04 ü· (u) dlÏXÇNo.935
Revesion History : 2006/07/19 ü· (u) dlÏXÇNo.956
Revesion History : 2007/02/10 æâÑQ(u) f No.1122
Revesion History : 2007/11/12 £«F(u) f No.1203
Revesion History : 2007/11/19 £«F(u) f No.1214
Revesion History : 2007/11/22 £«F(u) f No.1223
Revesion History : 2009/09/21 Ôi  (u) f No.1336 No.1337 No.1340 No.1342 No.1346 No.1347 vZ® No.035 No.039
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.CashProductImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.TradingModuleImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProcessIdDef;
import webbroker3.common.define.WEB3QuoteFromDivDef;
import webbroker3.common.define.WEB3QuoteTypeDivDef;
import webbroker3.equity.data.EquityLimitPriceRangeMstRow;
import webbroker3.equity.data.EquityTickValuesMstRow;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.data.OffFloorOrderProductRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.LastClosingPriceParams;
import webbroker3.gentrade.data.LastClosingPriceRow;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3EqTypeQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ig£v_Ng}l[WjB<BR>
 * <BR>
 * ¤iÉÎ·éìð\»µÜ·B<BR>
 * xTradeÌEqTypeProductManagerðg£µ½NXB
 * @@author ûü´@@Ça(SRA)
 * @@version 1.0
 */
public class WEB3EquityProductManager extends EqTypeProductManagerImpl
{

    /**
     * O[eBeB
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityProductManager.class);

    /**
     * @@roseuid 4042EC8902B7
     */
    public WEB3EquityProductManager()
    {

    }

    /**
     * (ñ Javadoc)
     * @@see 
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager#getProduct(Institution, String)
     * @@param l_institution
     * @@param l_strProductCode
     * @@return EqTypeProduct
     * @@throws NotFoundException
     * @@roseuid 4042EC8902C4
     */
    public EqTypeProduct getProduct(
        Institution l_institution,
        String l_strProductCode)
        throws NotFoundException
    {
        try
        {
            EqtypeProductRow l_eqtypeProductRow =
                EqtypeProductDao.findRowByInstitutionCodeProductCode(
                    l_institution.getInstitutionCode(),
                    l_strProductCode);
            if (l_eqtypeProductRow == null)
                throw new NotFoundException(
                    "No product found with institution_code,product_code : "
                        + l_institution.getInstitutionCode()
                        + ","
                        + l_strProductCode);
            try
            {
                return new WEB3EquityProduct(l_eqtypeProductRow);
            }
            catch (DataFindException dfe)
            {
                log.error(dfe.getMessage(), dfe);
                throw new NotFoundException(
                    "No product found with institutionCode, productCode : "
                        + l_institution.getInstitutionCode()
                        + ","
                        + l_strProductCode);
            }
            catch (DataException de)
            {
                log.error(de.getMessage(), de);
                throw new RuntimeSystemException(
                    "System exception while getting product with "
                        + "institutionCode, productCode : "
                        + l_institution.getInstitutionCode()
                        + ","
                        + l_strProductCode);
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while fetching eqtypeProduct table with "
                    + "product code, institution code :"
                    + l_strProductCode
                    + ","
                    + l_institution.getInstitutionCode();
            log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    /**
     * (ñ Javadoc)
     * @@see 
     * ProductManager#toProduct(com.fitechlabs.dbind.Row)<BR>
     * @@param l_row
     * @@return Product
     * @@roseuid 4042EC8A0341
     */
    public Product toProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toProduct(Row)";
        if ((l_row instanceof ProductRow) == false)
        {
            throw new IllegalArgumentException(
                "Expected ProductRow. But the given Row is the type of: "
                    + l_row.getClass());
        }
        ProductRow l_productRow = (ProductRow)l_row;
        TradingModule l_tradingModule =
            GtlUtils.getTradingModule(l_productRow.getProductType());
        if (l_tradingModule instanceof TradingModuleImpl)
        {
            try
            {
                ProductTypeEnum l_productType = l_productRow.getProductType();
                if (ProductTypeEnum.CASH.equals(l_productType))
                {
                    return new CashProductImpl(l_productRow);
                }
                else
                {
                    return new WEB3EquityProduct(l_productRow);
                }
            }
            catch (DataFindException l_dfe)
            {
                String msg =
                    "Cannot generate EqTypeProduct with "
                        + "product type, product id: "
                        + l_productRow.getProductType().stringValue()
                        + ","
                        + l_productRow.getProductId();
                        
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    msg, l_dfe
                );
            }
            catch (DataNetworkException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne
                );
            }
            catch (DataQueryException l_dqe)
            {   
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe
                );
            }
        }
        else
        {
            return l_tradingModule.getProductManager().toProduct(l_productRow);
        }
    }

    /**
     * (ñ Javadoc)
     * @@see 
     * ProductManager#toTradedProduct(com.fitechlabs.dbind.Row)<BR>
     * @@param l_row
     * @@return TradedProduct
     * @@roseuid 4042EC8A0380
     */
    public TradedProduct toTradedProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toTradedProduct(Row)";
        if ((l_row instanceof TradedProductRow) == false)
        {
            throw new IllegalArgumentException(
                "Expected TradedProductRow. "
                    + "But the given Row is the type of: "
                    + l_row.getClass());
        }
        TradedProductRow l_tradedProductRow = (TradedProductRow)l_row;
        TradingModule l_tradingModule =
            GtlUtils.getTradingModule(l_tradedProductRow.getProductType());
        if (l_tradingModule instanceof TradingModuleImpl)
        {
            try
            {
                return new WEB3EquityTradedProduct(l_tradedProductRow);
            }
            catch (DataFindException l_dfe)
            {
                String msg = 
                    "Cannot generate EqTypeTradedProduct with "
                    + "product type, traded product id: "
                    + l_tradedProductRow.getProductType()
                    + ","
                    + l_tradedProductRow.getTradedProductId();
                    
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    msg, l_dfe
                );
            }
            catch (DataNetworkException l_dne)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne
                );
            }
            catch (DataQueryException l_dqe)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe
                );
            }
        }
        else
        {
            return l_tradingModule.getProductManager().toTradedProduct(
                l_tradedProductRow);
        }
    }

    /**
     * (ñ Javadoc)
     * @@see 
     * EqTypeProductManager#searchProductsBeginningWith(com.fitechlabs.xtrade.plugin.tc.gentrade.Institution, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     * @@param l_institution
     * @@param l_productType
     * @@param l_strNamePrefix
     * @@param l_pageSpec
     * @@param l_sortSpec
     * @@return java.util.List
     * @@roseuid 4050212203D7
     */
    public List searchProductsBeginningWith(
        Institution l_institution,
        ProductTypeEnum l_productType,
        String l_strNamePrefix,
        PaginationQueryParamsSpec l_pageSpec,
        SortKeySpec l_sortSpec)
    {
        if (l_strNamePrefix == null)
        {
            throw new IllegalArgumentException("namePrefix should not be null");
        }
        try
        {
            String l_strWhere =
                "institution_code = ? and (product_type = ?) and ( "
                    + "(standard_name like ?) or (name_alt1 like ?) or "
                    + "(name_alt2 like ?) )";
            String l_strPrefixedSearchString = l_strNamePrefix + "%";
            Object l_bindVars[] =
                {
                    l_institution.getInstitutionCode(),
                    new Long(l_productType.intValue()),
                    l_strPrefixedSearchString,
                    l_strPrefixedSearchString,
                    l_strPrefixedSearchString };
            String l_strOrderBy = null;
            if (l_sortSpec != null && (l_sortSpec.isSortKeyNull() == false))
            {
                l_strOrderBy = l_sortSpec.getSortKeySpec();
            }
            int l_intPageSize = l_pageSpec.getPageSize();
            int l_intPageNumber = l_pageSpec.getPageNumber();
            List l_lisRows =
                Processors
                    .getDefaultProcessor()
                    .doFindAllQuery(ProductRow.TYPE,
                //"institution_code = ? and (product_type = ?) and "
        //    + "( (standard_name like ?) or (name_alt1 like ?) or "
        //    + "(name_alt2 like ?) )",
    l_strWhere, l_strOrderBy, null, l_bindVars, l_intPageSize, l_intPageNumber);
            return toProductList(l_lisRows);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new RuntimeSystemException(
                "System exception while searching product with namePrefix :"
                    + l_strNamePrefix,
                de);
        }
    }

    /**
     * (ñ Javadoc)
     * @@see 
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager#searchProducts(com.f
     * itechlabs.xtrade.plugin.tc.gentrade.Institution, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, java.lang.String, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     * @@param l_institution
     * @@param l_productType
     * @@param l_strNameSubString
     * @@param l_pageSpec
     * @@param l_sortSpec
     * @@return java.util.List
     * @@roseuid 40502123006C
     */
    public List searchProducts(
        Institution l_institution,
        ProductTypeEnum l_productType,
        String l_strNameSubString,
        PaginationQueryParamsSpec l_pageSpec,
        SortKeySpec l_sortSpec)
    {
        if (l_strNameSubString == null)
            throw new IllegalArgumentException("nameSubString should not be null");
        try
        {
            String l_strWhere =
                "institution_code = ? and (product_type = ?) and "
                    + "( (standard_name like ?) or (name_alt1 like ?) or "
                    + "(name_alt2 like ?) ) ";

            if (l_strNameSubString != null)
            {
                l_strNameSubString = "%" + l_strNameSubString + "%";
            }

            Object[] l_bindVars =
                {
                    l_institution.getInstitutionCode(),
                    new Long(l_productType.intValue()),
                    l_strNameSubString,
                    l_strNameSubString,
                    l_strNameSubString };

            String l_strOrderBy = null;
            if (l_sortSpec != null && (l_sortSpec.isSortKeyNull() == false))
            {
                l_strOrderBy = l_sortSpec.getSortKeySpec();
            }
            int l_intPageSize = l_pageSpec.getPageSize();
            int l_intPageNumber = l_pageSpec.getPageNumber();
            List l_lisRows =
                Processors
                    .getDefaultProcessor()
                    .doFindAllQuery(ProductRow.TYPE,
                //"institution_code = ? and (product_type = ?) and "
        //    + "( (standard_name like ?) or (name_alt1 like ?) or "
        //    + "(name_alt2 like ?) ) ",
    l_strWhere, l_strOrderBy, null, l_bindVars, l_intPageSize, l_intPageNumber);
            return toProductList(l_lisRows);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new RuntimeSystemException(
                "System exception while searching product with nameSubString :"
                    + l_strNameSubString,
                de);
        }
    }

    /**
     * (ñ Javadoc)
     * @@see 
     * com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager#searchProducts(com.f
     * itechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.Market, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec, 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec)
     * @@param l_productType
     * @@param l_market
     * @@param l_pageSpec
     * @@param l_sortSpec
     * @@return java.util.List
     * @@roseuid 4050212300F9
     */
    public List searchProducts(
        ProductTypeEnum l_productType,
        Market l_market,
        PaginationQueryParamsSpec l_pageSpec,
        SortKeySpec l_sortSpec)
    {
        if (l_market == null)
        {
            throw new IllegalArgumentException("market should not be null");
        }
        try
        {
            String l_strWhere =
                "institution_code = ? and product_type = ? and "
                    + "(product_id in (select product_id from traded_product "
                    + "where market_id =  ?  ) )";
            Object l_bindVars[] =
                {
                    l_market.getInstitution().getInstitutionCode(),
                    new Long(l_productType.intValue()),
                    new Long(l_market.getMarketId())};
            String l_strOrderBy = null;
            if (l_sortSpec != null && (l_sortSpec.isSortKeyNull() == false))
            {
                l_strOrderBy = l_sortSpec.getSortKeySpec();
            }
            int l_intPageSize = l_pageSpec.getPageSize();
            int l_intPageNumber = l_pageSpec.getPageNumber();
            List rows =
                Processors
                    .getDefaultProcessor()
                    .doFindAllQuery(ProductRow.TYPE,
                //"institution_code = ? and product_type = ? and (product_id in "
        //    + "(select product_id from traded_product where market_id =  ?) )",
    l_strWhere, l_strOrderBy, null, l_bindVars, l_intPageSize, l_intPageNumber);
            return toProductList(rows);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new RuntimeSystemException(
                "System exception while searching product with market id :"
                    + l_market.getMarketId(),
                de);
        }
    }

    /**
     * ProductRow(Q)ðîÉAWEB3EquityProduct(Q)ð¶¬µÔµÜ·B<BR>
     * @@param l_lisProductRows
     * @@return List WEB3EquityProduct(Q)
     * @@throws DataException
     * @@throws DataException
     * @@roseuid 40502123029F
     */
    private List toProductList(List l_lisProductRows) throws DataException
    {
        int l_intSize = l_lisProductRows.size();
        List l_lisProducts = new ArrayList(l_intSize);
        for (int i = 0; i < l_intSize; i++)
        {
            ProductRow l_productRow = (ProductRow)l_lisProductRows.get(i);
            l_lisProducts.add(toProduct(l_productRow));
        }

        return l_lisProducts;
    }

    /**
     * (get§Àl)<BR>
     * yle[uzæèAY·ésêÌ§Àlðæ¾µÔ·B<BR>
     * yle[uz©çÌæ¾ÉÍAwè³ê½îlAyÑ<BR>
     * iüÍjæøÁ¿DsêIDÅysêe[uzðõµAæ¾µ½sêR[hðgp·éB<BR>
     * <BR>
     * \bhÌÚ×Í<BR>
     * uî{ÝvvZ®iGNCeBj.docvÌu[¤Ê2]lZovðQÆB<BR>
     * <BR>
     * @@param l_tradedProduct - (æøÁ¿)<BR>
     * yle[uzõÉAsêR[hÌwèÉgp·éB<BR>
     * Ü½Aysêe[uzõÌsêIDwèÉàgp·éB
     * @@param l_dblBasePrice - (îl)<BR>
     * yle[uz©ç§Àlðæ¾·éÛÉAîlÆµÄgp·éB
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40502124006C
     */
    public double getDeregPriceRange(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblBasePrice)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getDeregPriceRange(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
 
        // sêR[hÌæ¾
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        
        // §ÀlÌæ¾
        double l_dblDeregPriceRange = 0.0D;
        String l_strWhere =
            " market_code = ? and low_price <= ? and cap_price > ?";
        Object l_bindVars[] =
        {
            l_strMarketCode,
            new Double(l_dblBasePrice),
            new Double(l_dblBasePrice)
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    EquityLimitPriceRangeMstRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows.size() < 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_dblDeregPriceRange = ((EquityLimitPriceRangeMstRow)l_lisRows.get(0)).getRange();
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblDeregPriceRange;
    }

    /**
     * (getÝl)<BR>
     * Äle[u©çAwè³ê½îlðgpµÄY·ésêÌÝlðæ¾·éB<BR>
     * ÝlÌæ¾ÍAwè³ê½îlAyÑ<BR>
     * iüÍjæøÁ¿DsêIDÅysêe[uzðõµAæ¾µ½sêR[hÅs¤B<BR>
     * æ¾µ½ÝlÍÄl`FbNÉgp·éB<BR>
     * <BR>
     * \bhÌÚ×Í<BR>
     * uî{ÝvvZ®iGNCeBj.docvÌu[¤Ê1]Ýlæ¾vðQÆB<BR>
     * <BR>
     * @@param l_tradedProduct - æøÁ¿ <BR>
     *     yÄle[uzõÌAsêR[hwèÉgp·éB<BR>
     * @@param l_dblBasePrice - îl <BR>
     *     yÄle[uzõÉAÎÛR[hÁèÌ½ßÌîlÆµÄgp·éB<BR>
     * <BR>
     * @@throws WEB3BaseException <BR>
     * @@return double
     * @@roseuid 405021240108
     */
    public double getTickValue(
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblBasePrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTickValue(WEB3EquityTradedProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tradedProduct == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
 
        // sêR[hÌæ¾
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        
        // ÝlÌæ¾
        double l_dblTickValue = 0.0D;
        String l_strWhere =
            " market_code = ? and low_price < ? and cap_price >= ?";
        Object l_bindVars[] =
        {
            l_strMarketCode,
            new Double(l_dblBasePrice),
            new Double(l_dblBasePrice)
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    EquityTickValuesMstRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows.size() < 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_dblTickValue = ((EquityTickValuesMstRow)l_lisRows.get(0)).getTickValue();
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblTickValue;
    }

    /**
     * (getæøÁ¿)<BR>
     * ®Á¿AsêIuWFNgæèæøÁ¿CX^Xðæ¾·éB<BR>
     * igetTradedProduct(Product,Market)ÌI[o[Chj<BR>
     * @@param l_product - (®Á¿)<BR>
     * @@param l_market - (sê)<BR>
     * @@return WEB3EquityTradedProduct<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413AA92301AD
     */
    public TradedProduct getTradedProduct(
        Product l_product,
        Market l_market)
        throws NotFoundException
    {

        TradedProduct l_tradedProduct = null;

        l_tradedProduct =
            super.getTradedProduct(l_product, l_market);

        return l_tradedProduct;

    }

    /**
     * (getæøÁ¿)<BR>
     *  <BR>
     * ØïÐIuWFNgAÁ¿R[hAsêR[hæèæøÁ¿ <BR>
     * CX^Xðæ¾µÜ·B <BR>
     * igetTradedProduct(Institution, String, String)ÌI[o[Chj <BR>
     *  <BR>
     * @@param l_gentradeInstitution - (ØïÐ)<BR>
     * @@param l_strProductCode - (Á¿R[h)<BR>
     * @@param l_strMarketCode - (sêR[h)<BR>
     * @@return WEB3EquityTradedProduct<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A4120B01C5
     */
    public EqTypeTradedProduct getTradedProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strMarketCode)
            throws NotFoundException
    {

        EqTypeTradedProduct l_tradedProduct = null;

        l_tradedProduct =
            super.getTradedProduct(
                l_institution,
                l_strProductCode,
                l_strMarketCode);


        return l_tradedProduct;

    }
    /**
     * (get¿)<BR>
     * <BR>
     * ¿ðæ¾·éBæ¾ÍÈºÌÊèB <BR>
     * ----------------------------------------------- <BR>
     * êF@@@@¿T[o®æøÁ¿e[u <BR>
     * ø¯ãorIle[u³ðreadwèF<BR>
     * @@@@@@@@@@@@Ile[u¿T[o®æøÁ¿e[u<BR>
     * ----------------------------------------------- <BR>
     * <BR>
     * Pj@@this.get¿îñ( )ðR[·éB <BR>
     * <BR>
     * @@@@@@---------------------------------------------------------- <BR>
     * @@@@@@get¿îñ( )FøÝèdl <BR>
     * <BR>
     * @@@@@@æøÁ¿F@@øÌæøÁ¿ <BR>
     * @@@@@@RealTypeF@@"A" <BR>
     * @@@@@@isIle[u³ðreadF@@øÌisIle[u³ðread<BR>
     * @@@@@@---------------------------------------------------------- <BR>
     * <BR>
     * Qj@@ßèlIuWFNg.¿ ðÔp·éB <BR>
     * <BR>
     * @@param l_tradedProduct - (æøÁ¿)<BR>
     * æøÁ¿IuWFNg
     * @@param l_isLastClosingPriceUnconditionalyRead - (isIle[u³ðread)<BR>
     * Ile[uð³ðÉread·é©Ç¤©ÌtO
     * @@return double
     * @@roseuid 40D16BAA0221
     */
    public double getCurrentPrice(EqTypeTradedProduct l_tradedProduct, boolean l_isLastClosingPriceUnconditionalyRead)
        throws WEB3BaseException
    {
        String METHOD_NAME =
            "getCurrentPrice(EqTypeTradedProduct, boolean)";

        log.entering(METHOD_NAME);

        if (l_tradedProduct == null)
        {
            log.error("parameter is null type");
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);
        }

        WEB3EquityProductQuote l_productQuote = this.getProductQuote(l_tradedProduct, RealType.REAL, l_isLastClosingPriceUnconditionalyRead);
        
        if (l_productQuote == null)
        {
	        log.exiting(METHOD_NAME);
            return 0.0D;
        }
        else
        {
	        log.exiting(METHOD_NAME);
	        return l_productQuote.getQuote();
        }
    }

    /**
     * (getæøÁ¿ê)<BR>
     * <BR>
     * øÌðÉY·é®æøÁ¿Ìêð<BR>
     * æ¾µAÔp·éB<BR>
     * ¦ÇÒE®Á¿ðÝèÅgpB<BR>
     * <BR>
     * PjÈºÌõðð\·Aõð¶ñÆ<BR>
     * @@ArrayList(p[^Zbg)ðì¬·éB<BR>
     * <BR>
     * @@[õð]<BR>
     * @@@@Á¿ID = p[^.Á¿ID<BR>
     * @@@@ØïÐR[h = p[^.ØïÐR[h<BR>
     * @@@@Løú = (p[^.ÎÛútæªÌlÉæé)<BR>
     * <BR>
     * @@P|PjãLõððîÉAõð¶ñðì¬·éB<BR>
     * <BR>
     * @@@@õð¶ñ = " product_id = ? "<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@+ "and institution_code = ? "<BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@+ "and valid_until_biz_date = ?"<BR>
     * <BR>
     * @@P|Qj"?"ÉZbg·é½ßÌp[^Zbgðì¬·éB<BR>
     * @@@@ArrayListð¶¬µAÈºÌlðZbg·éB<BR>
     * @@@@@@Ep[^.Á¿ID<BR>
     * @@@@@@Ep[^.ØïÐR[h<BR>
     * @@@@@@[p[^.ÎÛútæª == "0Fú"Ìê]<BR>
     * @@@@@@@@EÆ±(ob`)út(*1)ðZbgB<BR>
     * @@@@@@[p[^.ÎÛútæª == "1FcÆú"Ìê]<BR>
     * @@@@@@@@EÆ±útÌPcÆúãÌútð¶ñÏ·µÄZbgB<BR>
     * @@@@@@@@@@(tH[}bg YYYYMMDD)<BR>
     * @@@@@@[ãLÈOÌê]<BR>
     * @@@@@@@@EÆ±útÌQcÆúãÌútð¶ñÏ·µÄZbgB<BR>
     * @@@@@@@@@@(tH[}bg YYYYMMDD)<BR>
     * <BR>
     * QjõÎÛe[u¼ðè·éB<BR>
     * @@[p[^.ÎÛútæª == "0Fú"Ìê]<BR>
     * @@@@e[u¼ = "®æøÁ¿e[u"(eqtype_traded_product)<BR>
     * @@[ãLÈO]<BR>
     * @@@@e[u¼ = "®æøÁ¿êe[u"(eqtype_traded_product_updq)<BR>
     * <BR>
     * RjQueryProcessor.doFindAllQuery()\bhðR[·éB<BR>
     * <BR>
     * @@[doFindAllQuery()ÉZbg·ép[^]<BR>
     * @@@@arg0F@@QjÉÄèµ½e[u¼<BR>
     * @@@@arg1F@@ì¬µ½õð¶ñ<BR>
     * @@@@arg2F@@¶¬µ½ArrayList.toArray()<BR>
     * <BR>
     * @@õÊªæ¾Å«È©Á½êÍAnullðÔp·éB<BR>
     * <BR>
     * SjHashMapð¶¬·éB<BR>
     * <BR>
     * TjRjÌõÊÌªÈºÌðJèÔ·B<BR>
     * @@@@T|Pjì¬µ½HashMap.put()\bhÉÄA®æøÁ¿ParamsðÇÁ·<BR>
     * éB<BR>
     * @@@@@@@@¦®æøÁ¿updqParamsÌêÍA®æøÁ¿ParamsÉÏ·µÄZb<BR>
     * g·é±ÆB<BR>
     * @@@@@@@@[put()ÉZbg·ép[^]<BR>
     * @@@@@@@@@@keyF@@®æøÁ¿Params.sêIDÉY·ésêR[h<BR>
     * @@@@@@@@@@valueF@@®æøÁ¿Params<BR>
     * <BR>
     * Uj¶¬µ½HashMapðÔp·éB<BR>
     * <BR>
     * (*1)Æ±(ob`)útEEETradingSystem.getBizDate()ÉÄæ¾µ½Æ±(ob<BR>
     * `)útB<BR>
     * <BR>
     * @@param l_lngProductId - (Á¿ID)<BR>
     * Á¿ID
     * @@param l_strInstitutionCode - (ØïÐR[h)<BR>
     * ØïÐR[h
     * @@param l_strDateDiv - (ÎÛútæª)<BR>
     * ÎÛútæª
     * <BR>
     * 0F@@ú<BR>
     * 1F@@cÆú<BR>
     * 2F@@XcÆú<BR>
     * @@throws WEB3BaseException
     * @@return HashMap
     */
    public HashMap getEqtypeTradedProducts(
        long l_lngProductId,
        String l_strInstitutionCode,
        String l_strDateDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getEqtypeTradedProducts(long, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisTradedProducts = null;
        
        // P|Pjõð¶ñðì¬·éB
        String l_strWhere = "product_id = ? and institution_code = ? and valid_until_biz_date = ? and list_flag != ?";
        
        // P|Qj"?"ÉZbg·é½ßÌp[^Zbgðì¬·éB
        List l_lisBindVars = new ArrayList();
        l_lisBindVars.add(Long.toString(l_lngProductId));
        l_lisBindVars.add(l_strInstitutionCode);
        Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strDateDiv))
        {
            l_lisBindVars.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsBizDate));
        }
        else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strDateDiv))
        {
            WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBizDate);
            l_lisBindVars.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_gentradeBizDate.roll(1)));
        }
        else
        {
            WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsBizDate);
            l_lisBindVars.add(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_gentradeBizDate.roll(2)));
        }
        l_lisBindVars.add(BooleanEnum.FALSE);
        
        // QjõÎÛe[u¼ðè·éB
        RowType l_rowType = null;
        if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strDateDiv))
        {
            l_rowType = EqtypeTradedProductRow.TYPE;
        }
        else
        {
            l_rowType = EqtypeTradedProductUpdqRow.TYPE;
        }
        
        // RjQueryProcessor.doFindAllQuery()\bhðR[·éB
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisTradedProducts = l_processor.doFindAllQuery(
                l_rowType,
                l_strWhere,
                l_lisBindVars.toArray());
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        // õÊªæ¾Å«È©Á½êÍAnullðÔp·éB
        if (l_lisTradedProducts == null || l_lisTradedProducts.isEmpty())
        {
            return null;
        }
        
        // SjHashMapð¶¬·éB
        HashMap l_tradedProductsMap = new HashMap();
        
        // TjRjÌõÊÌªÈºÌðJèÔ·B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        int l_intSize = l_lisTradedProducts.size();
        for (int i = 0;i < l_intSize;i++)
        {
            // T|Pjì¬µ½HashMap.put()\bhÉÄA®æøÁ¿ParamsðÇÁ·éB
            Row l_row = (Row)l_lisTradedProducts.get(i);
            EqtypeTradedProductParams l_tradedProductParams = null;
            if (l_row instanceof EqtypeTradedProductUpdqParams)
            {
                l_tradedProductParams = new EqtypeTradedProductParams();
                GtlUtils.copyRow2Params(l_row, l_tradedProductParams);
            }
            else
            {
                l_tradedProductParams = (EqtypeTradedProductParams)l_row;
            }
            Market l_market = null;
            try
            {
                l_market = l_finObjectManager.getMarket(l_tradedProductParams.getMarketId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.toString(),
                    l_nfe);
            }
            l_tradedProductsMap.put(l_market.getMarketCode(), l_tradedProductParams);
        }
        
        // Uj¶¬µ½HashMapðÔp·éB
        log.exiting(STR_METHOD_NAME);
        return l_tradedProductsMap;
    }

    /**
     * (updateÁ¿)<BR>
     * <BR>
     * Á¿e[uðXV·éB<BR>
     * <BR>
     * PjÁ¿PKCX^Xð¶¬·éB<BR>
     * <BR>
     * @@[RXgN^ÉZbg·ép[^]<BR>
     * @@@@arg0F@@p[^.Á¿ID<BR>
     * <BR>
     * QjQueryProcessor.doUpdateQuery()ðR[·éB<BR>
     * <BR>
     * @@[doUpdateQuery()ÉZbg·ép[^]<BR>
     * @@@@arg0F@@PjÉÄì¬µ½Á¿PK<BR>
     * @@@@arg1F@@p[^.ÏXf[^<BR>
     * <BR>
     * @@param l_lngProductId - (Á¿ID)<BR>
     * Á¿ID<BR>
     * @@param l_changeMap - (ÏXf[^)<BR>
     * ÏXf[^<BR>
     * @@throws WEB3BaseException
     */
    public void updateProduct(
        long l_lngProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // PjÁ¿PKCX^Xð¶¬·éB
        ProductPK l_productPK = new ProductPK(l_lngProductId);
        
        // QjQueryProcessor.doUpdateQuery()ðR[·éB
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_productPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update®Á¿)<BR>
     * <BR>
     * ®Á¿e[uðXV·éB<BR>
     * <BR>
     * Pj®Á¿PKCX^Xð¶¬·éB<BR>
     * <BR>
     * @@[RXgN^ÉZbg·ép[^]<BR>
     * @@@@arg0F@@p[^.Á¿ID<BR>
     * <BR>
     * QjQueryProcessor.doUpdateQuery()ðR[·éB<BR>
     * <BR>
     * @@[doUpdateQuery()ÉZbg·ép[^]<BR>
     * @@@@arg0F@@PjÉÄì¬µ½®Á¿PK<BR>
     * @@@@arg1F@@p[^.ÏXf[^<BR>
     * <BR>
     * @@param l_lngProductId - (Á¿ID)<BR>
     * Á¿ID<BR>
     * @@param l_changeMap - (ÏXf[^)<BR>
     * ÏXf[^<BR>
     * @@throws WEB3BaseException
     */
    public void updateEqtypeProduct(
        long l_lngProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // Pj®Á¿PKCX^Xð¶¬·éB
        EqtypeProductPK l_eqtypeProductPK = new EqtypeProductPK(l_lngProductId);
        
        // QjQueryProcessor.doUpdateQuery()ðR[·éB
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_eqtypeProductPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateæøÁ¿)<BR>
     * <BR>
     * Á¿e[uðXV·éB<BR>
     * <BR>
     * Pj®æøÁ¿PKCX^Xð¶¬·éB<BR>
     * <BR>
     * @@[RXgN^ÉZbg·ép[^]<BR>
     * @@@@arg0F@@p[^.æøÁ¿ID<BR>
     * <BR>
     * QjQueryProcessor.doUpdateQuery()ðR[·éB<BR>
     * <BR>
     * @@[doUpdateQuery()ÉZbg·ép[^]<BR>
     * @@@@arg0F@@PjÉÄì¬µ½®æøÁ¿PK<BR>
     * @@@@arg1F@@p[^.ÏXf[^<BR>
     * <BR>
     * @@param l_lngTradedProductId - (æøÁ¿ID)<BR>
     * Á¿ID<BR>
     * @@param l_changeMap - (ÏXf[^)<BR>
     * ÏXf[^<BR>
     * @@throws WEB3BaseException
     */
    public void updateEqtypeTradedProduct(
        long l_lngTradedProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeTradedProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // Pj®æøÁ¿PKCX^Xð¶¬·éB
        EqtypeTradedProductPK l_eqtypeTradedProductPK = new EqtypeTradedProductPK(l_lngTradedProductId);
        
        // QjQueryProcessor.doUpdateQuery()ðR[·éB
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_eqtypeTradedProductPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateæøÁ¿updq)<BR>
     * <BR>
     * Á¿e[uðXV·éB<BR>
     * <BR>
     * Pj®æøÁ¿updqPKCX^Xð¶¬·éB<BR>
     * <BR>
     * @@[RXgN^ÉZbg·ép[^]<BR>
     * @@@@arg0F@@p[^.æøÁ¿ID<BR>
     * @@@@arg1F@@p[^.Løú<BR>
     * <BR>
     * QjQueryProcessor.doUpdateQuery()ðR[·éB<BR>
     * <BR>
     * @@[doUpdateQuery()ÉZbg·ép[^]<BR>
     * @@@@arg0F@@PjÉÄì¬µ½®æøÁ¿updqPK<BR>
     * @@@@arg1F@@p[^.ÏXf[^<BR>
     * @@param l_lngTradedProductId - (æøÁ¿ID)<BR>
     * Á¿ID
     * @@param l_strBizDate (Løú)<BR>
     * Løú
     * @@param l_changeMap - (ÏXf[^)<BR>
     * ÏXf[^
     * @@throws WEB3BaseException
     */
    public void updateEqtypeTradedProductUpdq(
        long l_lngTradedProductId,
        String l_strBizDate,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeTradedProductUpdq(long, String, HashMap)";
        log.entering(STR_METHOD_NAME);

        // Pj®æøÁ¿PKCX^Xð¶¬·éB
        EqtypeTradedProductUpdqPK l_eqtypeTradedProductUpdqPK =
            new EqtypeTradedProductUpdqPK(
                l_lngTradedProductId,
                l_strBizDate);
        
        // QjQueryProcessor.doUpdateQuery()ðR[·éB
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_eqtypeTradedProductUpdqPK, l_changeMap);
        }
        catch (DataException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get~jæµæøÁ¿)<BR>
     * <BR>
     * ~jðæèµÁÄ¢éæøÁ¿ðæ¾µAÔp·éB<BR>
     * <BR>
     * PjÈºÌðp[^.æøÁ¿êÌvfª<BR>
     * @@JèÔ·B<BR>
     * @@P|Pjp[^.æøÁ¿ê[index].~jæµ == "~jæµ"ÌêA<BR>
     * @@@@@@@@p[^.æøÁ¿ê[index]ðÔpµÄI¹·éB<BR>
     * <BR>
     * QjnullðÔp·éB@@¦~jæµæøÁ¿ªæ¾Å«È©Á½×B<BR>
     * @@param - l_tradedProducts - (æøÁ¿ê)<BR>
     * æøÁ¿ParamsIuWFNgÌzñ
     * @@throws WEB3BaseException
     * @@return EqtypeTradedProductParams
     */
    public EqtypeTradedProductParams getMiniStockTradedProduct(
        EqtypeTradedProductParams[] l_tradedProducts)
    {
        final String STR_METHOD_NAME =
            "getMiniStockTradedProduct(EqtypeTradedProductParams[])";
        log.entering(STR_METHOD_NAME);
        
        for (int i = 0;i < l_tradedProducts.length;i++)
        {
            EqtypeTradedProductParams l_tradedProductParams = l_tradedProducts[i];
            if (BooleanEnum.TRUE.equals(l_tradedProductParams.getMiniStockCanDealt()))
            {
                return l_tradedProducts[i];
            }
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

	/**
	 * (get~jæµæøÁ¿)<BR>
	 * <BR>
	 * wèÁ¿ÌA~jæµsêÌæøÁ¿IuWFNgðæ¾µÔp·éB <BR>
	 * <BR>
	 * Pj@@~jsêæ¾<BR>
	 *  ®Á¿.get~jsê()ÉÄAsêIuWFNgðæ¾·éB <BR>
	 *  ßèlªnulli==~jæµÈµÁ¿jÌêÍAnullðÔp·éB <BR>
	 * <BR>
	 * Qj@@æøÁ¿æ¾ <BR>
	 *  v_Ng}l[W.getTradedProduct()ÉÄAæøÁ¿IuWFNgðæ¾·éB <BR>
	 * <BR>
	 * [getTradedProduct()Éwè·éø] <BR>
	 * productF@@®Á¿ <BR>
	 * marketF@@sê<BR>
	 * <BR>
	 * Rj@@æøÁ¿IuWFNgðÔp·éB<BR>
	 * <BR>
	 * @@param - l_product - (®Á¿)<BR>
	 * ®Á¿IuWFNg
	 * @@throws WEB3BaseException
	 * @@return WEB3EquityTradedProduct
	 */
	public WEB3EquityTradedProduct getMiniStockTradedProduct(
		WEB3EquityProduct l_product) throws WEB3BaseException
	{
		final String STR_METHOD_NAME =
			"getMiniStockTradedProduct(WEB3EquityProduct)";
		log.entering(STR_METHOD_NAME);
        
		if (l_product == null)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + STR_METHOD_NAME);
		}
		Market l_market;
		WEB3EquityTradedProduct l_equityTradedProduct;
		try 
		{
			l_market = l_product.getMiniStockMarket();
			if (l_market == null)
			{
				return null;
			}
			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			TradingModule l_tradingModule =
				l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
			WEB3EquityProductManager l_eqTypeProductManager =
				(WEB3EquityProductManager)l_tradingModule.getProductManager();

			l_equityTradedProduct =
				(WEB3EquityTradedProduct)l_eqTypeProductManager.getTradedProduct(
				l_product, l_market);
		}
		catch (NotFoundException l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
			    STR_METHOD_NAME);
		}

		log.exiting(STR_METHOD_NAME);
		return l_equityTradedProduct;
	}

    /**
     * (get§ïOªÁ¿)<BR>
     * <BR>
     * y§ïOªÁ¿e[uzæèA<BR>
     * wè³ê½ªÁ¿ÌR[hðæ¾·éB<BR>
     * iDBCAEgu§ïOªÁ¿e[udl.xlsvQÆj<BR>
     * <BR>
     * Pj@@DBõ<BR>
     * @@§ïOªÁ¿e[uðÈºÌðÅõ·éB<BR>
     * @@[ð]<BR>
     * @@ØïÐR[h = ø.ØïÐR[h<BR>
     * @@©Â@@Á¿R[h = ø.Á¿R[h<BR>
     * @@©Â@@sêR[h = ø.sêR[h<BR>
     * @@©Â@@ótI¹úÌYYYYMMDD = ø.ótI¹úÌYYYYMMDD<BR>
     * <BR>
     * Qj@@æ¾µ½R[hðÔ·B<BR>
     * @@@@@@¦Y·éR[hª¶ÝµÈ¢êÍnullðÔ·B<BR>
     * @@@@@@¦¡R[h¶Ýµ½êÍAáOðthrow·éB<BR>
     * <BR>
     * @@param l_strInstitutionCode - (ØïÐR[h)<BR>
     * ØïÐR[hB<BR>
     * @@param l_strProductCode - (Á¿R[h)<BR>
     * Á¿R[hB<BR>
     * @@param l_strMarketCode - (sêR[h)<BR>
     * sêR[hB<BR>
     * @@param l_datOrderEndDatetime - (ótI¹ú)<BR>
     * ótI¹úB<BR>
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams getOffFloorOrderProduct(
        String l_strInstitutionCode,
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderEndDatetime)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOffFloorOrderProduct(String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        OffFloorOrderProductParams l_params = null;
        
        String l_strWhere = "institution_code = ? and "
                          + "product_code = ? and "
                          + "market_code = ? and "
                          + "to_char(order_end_datetime,'yyyymmdd') = ?";
        Object[] l_bindVars =
        {
            l_strInstitutionCode,
            l_strProductCode,
            l_strMarketCode,
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datOrderEndDatetime)
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    OffFloorOrderProductRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows != null)
            {
                int l_intSize = l_lisRows.size();
                if (l_intSize == 1)
                {
                    l_params = (OffFloorOrderProductParams)l_lisRows.get(0);
                }
                else if (l_intSize > 1)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }

    /**
     * (get¶Â\§ïOªÁ¿)<BR>
     * <BR>
     * y§ïOªÁ¿e[uzæèA<BR>
     * wè³ê½ªÁ¿Ì¶Â\ÈR[hðæ¾·éB<BR>
     * iDBCAEgu§ïOªÁ¿e[udl.xlsvQÆj<BR>
     * <BR>
     * Pj@@DBõ<BR>
     * @@§ïOªÁ¿e[uðÈºÌðÅõ·éB<BR>
     * @@[ð]<BR>
     * @@ØïÐR[h = ø.ØïÐR[h<BR>
     * @@©Â@@Á¿R[h = ø.Á¿R[h<BR>
     * @@©Â@@sêR[h = ø.sêR[h<BR>
     * @@©Â@@ótJnú  »Ýú(*1)<BR>
     * @@©Â@@ótI¹ú  »Ýú(*1)<BR>
     * @@©Â@@ª¿i != null<BR>
     * @@©Â@@\ãÀ != null<BR>
     * <BR>
     * @@(*1)»ÝúFGtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * Qj@@æ¾µ½R[hðÔ·B<BR>
     * <BR>
     * @@@@@@Y·éR[hª¶ÝµÈ¢êÍnullðÔ·B<BR>
     * @@@@@@¡R[h¶Ý·éêÍu§ïOªÂ\R[hªÁèsÂvÌáOðthrow·éB<BR>
     * <BR>
     * @@param l_strInstitutionCode - (ØïÐR[h)<BR>
     * ØïÐR[hB<BR>
     * @@param l_strProductCode - (Á¿R[h)<BR>
     * Á¿R[hB<BR>
     * @@param l_strMarketCode - (sêR[h)<BR>
     * sêR[hB<BR>
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams getCanOrderOffFloorOrderProduct(
        String l_strInstitutionCode,
        String l_strProductCode,
        String l_strMarketCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCanOrderOffFloorOrderProduct(String, String, String)";
        log.entering(STR_METHOD_NAME);
        OffFloorOrderProductParams l_params = null;
        
        String l_strWhere =
            " institution_code = ? and product_code = ? and market_code = ?"
            + " and order_start_datetime <= ? and order_end_datetime > ?"
            + " and off_floor_order_price is not null and max_apply_quantity is not null";
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        Object l_bindVars[] =
        {
            l_strInstitutionCode,
            l_strProductCode,
            l_strMarketCode,
            l_tsSystemTimestamp,
            l_tsSystemTimestamp
        };
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows =
                l_queryProcesser.doFindAllQuery(
                OffFloorOrderProductRow.TYPE,
                    l_strWhere,
                    l_bindVars);
            if (l_lisRows != null)
            {
                int l_intSize = l_lisRows.size();
                if (l_intSize == 1)
                {
                    l_params = (OffFloorOrderProductParams)l_lisRows.get(0);
                }
                else if (l_intSize > 1)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01368,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (isXVíÂ\§ïOªÁ¿)<BR>
     * <BR>
     * wè³ê½ªÁ¿ªA»ÝXV^íÂ\©Ç¤©ð»èµA<BR>
     * XV^íªÂ\ÈêÍtrueðAXV^íªsÂÈêÍfalseðÔ·B<BR>
     * <BR>
     * øÌ§ïOªÁ¿IuWFNgªÈºÌ[ðP]Ü½Í[ðQ]Év·éêÍA<BR>
     * XV^íªÂ\Æ»èµtrueðÔ·B<BR>
     * ------------------------------------------------<BR>
     * [ðP]FótJnOÅ é<BR>
     * @@ótJnú  »Ýú(*1)<BR>
     * <BR>
     * [ðQ]Fótio^¢®¹Ìf[^j<BR>
     * @@ótJnú  »Ýú(*1)<BR>
     * ©Â@@ótI¹ú  »Ýú(*1)<BR>
     * ©Â@@iª¿i == null@@Ü½Í@@\ãÀ == nullj<BR>
     * <BR>
     * (*1)»ÝúFGtlUtils.getSystemTimestamp( )<BR>
     * ------------------------------------------------<BR>
     * <BR>
     * ãLÌ[ðP][ðQ]Ì¢¸êÉàvµÈ¢êÍA<BR>
     * XV^íªsÂÆ»èµfalseðÔ·B<BR>
     * <BR>
     * @@param l_params - (§ïOªÁ¿)<BR>
     * §ïOªÁ¿IuWFNgB<BR>
     * iy§ïOªÁ¿e[uzÌPR[hj<BR>
     * @@throws WEB3BaseException
     */
    public boolean isCanUpdateDeleteOffFloorOrderProduct(
        OffFloorOrderProductRow l_row)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCanOrderOffFloorOrderProduct(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_isCanUpdateDeleteOffFloorOrderProduct = false;
        
        // øÌ§ïOªÁ¿IuWFNgªÈºÌ[ðP]Ü½Í[ðQ]Év·éêÍA
        // XV^íªÂ\Æ»èµtrueðÔ·B
        Timestamp l_tsSysDate = GtlUtils.getSystemTimestamp();
        // [ðP]FótJnOÅ é
        // @@ótJnú  »Ýú(*1)
        if (l_row.getOrderStartDatetime().compareTo(l_tsSysDate) > 0)
        {
            l_isCanUpdateDeleteOffFloorOrderProduct = true;
        }
        // [ðQ]Fótio^¢®¹Ìf[^j
        // @@ótJnú  »Ýú(*1)
        // ©Â@@ótI¹ú  »Ýú(*1)
        // ©Â@@iª¿i == null@@Ü½Í@@\ãÀ == nullj
        else
        {
            if (l_row.getOrderEndDatetime().compareTo(l_tsSysDate) > 0 &&
                (l_row.getOffFloorOrderPriceIsNull() || l_row.getMaxApplyQuantityIsNull()))
            {
                l_isCanUpdateDeleteOffFloorOrderProduct = true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_isCanUpdateDeleteOffFloorOrderProduct;
    }
    
    /**
     * (getIl)<BR>
     * <BR>
     * yIle[uz©çA <BR>
     * wè³ê½Á¿IDAîúÉY·éR[hðæ¾µÔ·B <BR>
     * <BR>
     * Pj@@DBõ <BR>
     * @@Ile[uðÈºÌðÅõ·éB <BR>
     * @@[ð] <BR>
     * @@Á¿ID = ø.Á¿ID <BR>
     * @@©Â@@îú = ø.îúÌYYYYMMDD <BR>
     * <BR>
     * ¦ø.îú==nullÌêÍAîúðð©çO·B<BR>
     * <BR>
     * Qj@@æ¾µ½R[hðÔ·B <BR>
     * @@@@@@¦Y·éR[hª¶ÝµÈ¢êÍnullðÔ·B <BR>
     * @@@@@@¦Y·éR[hª¡¶Ý·éêÍAîúªÅålÌR[hðÔ·B<BR>
     * <BR>
     * @@params l_lngProductId  (Á¿ID)<BR>
     * Á¿IDB<BR>
     * @@params l_datBaseDate  (îú)<BR>
     * îú(Æ±út)B<BR>
     * @@return LastClosingPriceParams<BR>
     * @@throws WEB3BaseException<BR>
     */ 
    public LastClosingPriceParams getLastClosingPrice(long l_lngProductId, Date l_datBaseDate)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastClosingPrice(long, Date)";
        log.entering(STR_METHOD_NAME);
        
        // PjDBõ
        String l_strWhere = null;
        Object[] l_objWhere = null;
        List l_lisSearchResult = null;
        if (l_datBaseDate != null)
        {
            l_strWhere = "product_id = ? and to_char(base_date, 'YYYYMMDD') = ?";
            l_objWhere = new Object[2];
            l_objWhere[0] = new Long(l_lngProductId);
            l_objWhere[1] = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBaseDate);
        }
        else
        {
            l_strWhere = "product_id = ?";
            l_objWhere = new Object[1];
            l_objWhere[0] = new Long(l_lngProductId);
        }
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                LastClosingPriceRow.TYPE,
                l_strWhere,
                null,
                null,
                l_objWhere);
        }
        catch (DataException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBANZXÉ¸sµÜµ½B");
        }
        
        // Qj@@æ¾µ½R[hðÔ·B
        log.exiting(STR_METHOD_NAME);
        if (l_lisSearchResult == null || l_lisSearchResult.isEmpty())
        {
            return null;
        }
        else
        {
            int l_intSize = l_lisSearchResult.size();
            LastClosingPriceRow l_lastClosingPriceRow = null;
            if (l_intSize > 1)
            {
                Timestamp l_tsBaseDate = null;
                LastClosingPriceRow l_row = null;
                for (int i = 0;i < l_intSize;i++)
                {
                    l_row = (LastClosingPriceRow)l_lisSearchResult.get(i);
                    if (l_tsBaseDate == null)
                    {
                        l_tsBaseDate = l_row.getBaseDate();
                        l_lastClosingPriceRow = l_row;
                    }
                    else
                    {
                        if (l_row.getBaseDate().compareTo(l_tsBaseDate) > 0)
                        {
                            l_tsBaseDate = l_row.getBaseDate();
                            l_lastClosingPriceRow = l_row; 
                        }
                    }
                }
            }
            else
            {
                l_lastClosingPriceRow = (LastClosingPriceRow)l_lisSearchResult.get(0);
            }
            return new LastClosingPriceParams(l_lastClosingPriceRow);
        }
    }
    
    /**
     * (getIl)<BR>
     * <BR>
     * yIle[uz©çA <BR>
     * wè³ê½Á¿IDAsêR[hAîúÉY·élðæ¾µÔ·B <BR>
     * <BR>
     * Pj@@DBõ <BR>
     * @@I[o[[h\bhðR[·éB <BR>
     * <BR>
     * @@this.getIl(øÌÁ¿ID, øÌîú) <BR>
     * <BR>
     * @@Yf[^ÈµÍ0ðÔ·B <BR>
     * <BR>
     * Qj@@æ¾µ½R[hÌAY·ésêÚÌP¿Ýèl(*)ðÔ·B <BR>
     * <BR>
     * @@(*)Y·ésêÚ <BR>
     * @@sêR[h==F@@Il.ØIl <BR>
     * @@sêR[h==åãF@@Il.åØIl <BR>
     * @@sêR[h==¼Ã®F@@Il.¼ØIl <BR>
     * @@sêR[hªãLÈOF@@Il.»Ì¼sêIl<BR>
     * <BR>
     * @@params l_lngProductId  (Á¿ID)<BR>
     * Á¿IDB<BR>
     * @@params l_strMarketCode  (sêR[h)<BR>
     * sêR[hB<BR>
     * @@params l_datBaseDate  (îú)<BR>
     * îú(Æ±út)B<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getLastClosingPrice(long l_lngProductId, String l_strMarketCode, Date l_datBaseDate)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastClosingPrice(long, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        // this.getIl()
        LastClosingPriceParams l_lastClosingPriceParams =
            this.getLastClosingPrice(l_lngProductId, l_datBaseDate);
        
        if (l_lastClosingPriceParams == null)
        {
            return 0D;
        }
        
        // æ¾µ½R[hÌAY·ésêÚÌP¿ÝèlðÔ·B
        double l_dblLastClosingPrice = 0D;
        if (l_strMarketCode.equals(WEB3MarketCodeDef.TOKYO))
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getTokyoClosingPrice();
        }
        else if (l_strMarketCode.equals(WEB3MarketCodeDef.OSAKA))
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getOosakaClosingPrice();
        }
        else if (l_strMarketCode.equals(WEB3MarketCodeDef.NAGOYA))
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getNagoyaClosingPrice();
        }
        else
        {
            l_dblLastClosingPrice = l_lastClosingPriceParams.getOtherClosingPrice();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblLastClosingPrice;
    }
    
    /**
     * (get\¦p¿îñ)<BR>
     * <BR>
     * üÍæÊÉ\¦·é¿îñðæ¾·éB <BR>
     * <BR>
     * Pj@@¿T[rXiWEB3DefaultQuoteDataSupplierServicejæèA <BR>
     * @@@@@@Úq®«É¶½¿îñiWEB3EqTypeQuoteDatajðæ¾·éB <BR>
     * <BR>
     * @@@@@@AqiøÌâûÀÌÚq.isAÚq( )==truejÌêF <BR>
     * @@@@@@@@WEB3DefaultQuoteDataSupplierService.getEqTypeQuote(øÌæøÁ¿, "A"); <BR>
     * <BR>
     * @@@@@@fBCqiøÌâûÀÌÚq.isAÚq( )==falsejÌêF <BR>
     * @@@@@@@@WEB3DefaultQuoteDataSupplierService.getEqTypeQuote(øÌæøÁ¿, "20ªfBC"); <BR>
     * <BR>
     * Qj@@æ¾µ½¿îñðÈºÌDæÊÅ]¿µA <BR>
     * @@@@@@lªt¢Ä¢éi==0ÅÈ¢jP¿ð¿ÆµÄAßèlIuWFNg.¿ ÉZbg·éB <BR>
     * <BR>
     * @@@@PD»ÝliWEB3EqTypeQuoteData.getCurrentPrice( )j <BR>
     * @@@@QDCzliWEB3EqTypeQuoteData.getBidPrice( )j <BR>
     * @@@@RDCzliWEB3EqTypeQuoteData.getAskPrice( )j <BR>
     * @@@@SDæøÁ¿.îliIljiøÌæøÁ¿.getLastClosingPrice( )j <BR>
     * <BR>
     * @@@@Ü½A¿Æµ½P¿ÉY·é¿æªi»Ýl^Czl^Czl^OúIljðA <BR>
     * @@@@ßèlIuWFNg.¿æª ÉZbg·éB <BR>
     * <BR>
     * Rj@@ßèlIuWFNg.OúäÉAWEB3EqTypeQuoteData.getChange( )ðZbg·éB <BR>
     * <BR>
     * Sj@@ßèlIuWFNg.¿­\ÔÉAQjÅ¿Æµ½P¿ÉÎ·é¿­\Ôð <BR>
     * @@@@@@Zbg·éB <BR>
     * <BR>
     * @@@@@@¦áF@@»Ýlð¿Æ·éêAWEB3EqTypeQuoteData.getCurrentPriceTime( )ðZbg·éB <BR>
     * @@@@@@¦nullªÔ³ê½êAnullð»ÌÜÜZbg·éB<BR>
     * <BR>
     * Tj@@ßèlIuWFNg.¿æ¾æªÉA"¿"ðZbg·éB <BR>
     * <BR>
     * Uj@@ßèlIuWFNg.sêR[hÉAøÌæøÁ¿.sêIDÉY·ésê.sêR[hðZbg·éB <BR>
     * <BR>
     * Vj@@ÈºAPjÅæ¾ÏÌ¿îñ©çAÂîñÌZbgðs¤B <BR>
     * <BR>
     * @@@@ßèlIuWFNg.»ÝlÉAWEB3EqTypeQuoteData.getCurrentPrice( )ðZbg·éB(*1) <BR>
     * @@@@ßèlIuWFNg.»ÝlÉAWEB3EqTypeQuoteData.getCurrentPriceTime( )ðZbg·éB <BR>
     * @@@@ßèlIuWFNg.»ÝlæªÉAWEB3EqTypeQuoteData.getCurrentPriceFlag( )ðZbg·éB(*3) <BR>
     * @@@@ßèlIuWFNg.»ÝlOúäÉAWEB3EqTypeQuoteData.getChange( )ðZbg·éB(*2) <BR>
     * <BR>
     * @@@@ßèlIuWFNg.oÉAWEB3EqTypeQuoteData.getVolume( )ðZbg·éB(*1) <BR>
     * @@@@ßèlIuWFNg.oÉAWEB3EqTypeQuoteData.getVolumeTime( )ðZbg·éB <BR>
     * <BR>
     * @@@@ßèlIuWFNg.Czl^CgæªÉAWEB3EqTypeQuoteData.getAskPriceTitle( )ðZbg·éB(*4) <BR>
     * @@@@ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getAskPrice( )ðZbg·éB(*1) <BR>
     * @@@@ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getAskPriceTime( )ðZbg·éB <BR>
     * <BR>
     * @@@@ßèlIuWFNg.Czl^CgæªÉAWEB3EqTypeQuoteData.getBidPriceTitle( )ðZbg·éB(*5) <BR> 
     * @@@@ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getBidPrice( )ðZbg·éB(*1) <BR>
     * @@@@ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getBidPriceTime( )ðZbg·éB <BR>
     * <BR>
     * @@@@ßèlIuWFNg.îliÉAWEB3EqTypeQuoteData.getBasePrice( )ðZbg·éB(*1) <BR>
     * <BR>
     * Wj@@ì¬µ½ßèlIuWFNgðÔ·B<BR>
     * <BR>
     * (*1)0ÌêÍAnullðZbg·éB<BR> 
     * (*2)Double.NaNÌêÍAnullðZbg·éB<BR> 
     * (*3)WEB3EqTypeQuoteData.getCurrentPrice( )==0ÌêÍAnullðZbg·éB<BR> 
     * (*4)WEB3EqTypeQuoteData.getAskPrice( )==0ÌêÍAnullðZbg·éB<BR>
     * (*5)WEB3EqTypeQuoteData.getBidPrice( )==0ÌêÍAnullðZbg·éB<BR>
     * @@params l_eqtypeTradedProduct  (æøÁ¿)<BR>
     * æøÁ¿IuWFNg<BR>
     * @@params l_subAccount  (âûÀ)<BR>
     * âûÀB
     * @@return WEB3EquityProductQuote
     * @@throws WEB3BaseException
     */
    public WEB3EquityProductQuote getDisplayEquityProductQuote(EqTypeTradedProduct l_eqtypeTradedProduct, WEB3GentradeSubAccount l_subAccount)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDisplayEquityProductQuote(EqtypeTradedProduct, WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //Pj@@¿T[rXiWEB3DefaultQuoteDataSupplierServicejæèA
        //     Úq®«É¶½¿îñiWEB3EqTypeQuoteDatajðæ¾·éB
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3QuoteDataSupplierService l_quoteDataSupplier =
            (WEB3QuoteDataSupplierService)l_tradingModule.getQuoteDataSupplierService();
            
        WEB3EqTypeQuoteData l_eqtypeQuoteData = null;
        boolean l_isRealAccount = l_mainAccount.isRealCustomer();
        if (l_isRealAccount == true)
        {
            try
            {
                l_eqtypeQuoteData =
                    (WEB3EqTypeQuoteData)l_quoteDataSupplier.getEqTypeQuote(l_eqtypeTradedProduct, RealType.REAL);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "®¿æ¾T[rXªæ¾oÜ¹ñÅµ½BAæªF[" + RealType.REAL + "]");
            }
        }
        else
        {
            try
            {
                l_eqtypeQuoteData =
                    (WEB3EqTypeQuoteData)l_quoteDataSupplier.getEqTypeQuote(l_eqtypeTradedProduct, RealType.DELAY);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "®¿æ¾T[rXªæ¾oÜ¹ñÅµ½BAæªF[" + RealType.DELAY + "]");
            }
        }
        
        // ßèlIuWFNgÌ¶¬
        WEB3EquityProductQuote l_equityProductQuote = new WEB3EquityProductQuote();

        //Qj@@æ¾µ½¿îñðÈºÌDæÊÅ]¿µA 
        // @@@@lªt¢Ä¢éi==0ÅÈ¢jP¿ð¿ÆµÄAßèlIuWFNg.¿ ÉZbg·éB
        // @@@@PD»ÝliWEB3EqTypeQuoteData.getCurrentPrice( )j <BR>
        // @@@@QDCzliWEB3EqTypeQuoteData.getBidPrice( )j <BR>
        // @@@@RDCzliWEB3EqTypeQuoteData.getAskPrice( )j <BR>
        // @@@@SDæøÁ¿.îliIljiøÌæøÁ¿.getLastClosingPrice( )j
        l_equityProductQuote.setQuote(l_eqtypeQuoteData.getCurrentPrice());
        l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.CURRENT_PRICE);
        
        //Sj@@ßèlIuWFNg.¿­\ÔÉAQjÅ¿Æµ½P¿ÉÎ·é¿­\Ôð 
        // @@@@Zbg·éB 
        l_equityProductQuote.setQuoteTime(l_eqtypeQuoteData.getCurrentPriceTime());
        
        if (l_equityProductQuote.getQuote() == 0.0D)
        {
            l_equityProductQuote.setQuote(l_eqtypeQuoteData.getBidPrice());
            l_equityProductQuote.setQuoteTime(l_eqtypeQuoteData.getBidPriceTime());
            l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.BID_PRICE);
        }
        if (l_equityProductQuote.getQuote() == 0.0D)
        {
            l_equityProductQuote.setQuote(l_eqtypeQuoteData.getAskPrice());
            l_equityProductQuote.setQuoteTime(l_eqtypeQuoteData.getAskPriceTime());
            l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.ASK_PRICE);
        }
        if (l_equityProductQuote.getQuote() == 0.0D)
        {
            l_equityProductQuote.setQuote(l_eqtypeTradedProduct.getLastClosingPrice());
            l_equityProductQuote.setQuoteTime(null);
            l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.LAST_CLOSING_PRICE);
        }
        
        //Rj@@ßèlIuWFNg.OúäÉAWEB3EqTypeQuoteData.getChange( )ðZbg·éB
        l_equityProductQuote.setComparedPreviousDay(l_eqtypeQuoteData.getChange());
        
        //Tj@@ßèlIuWFNg.¿æ¾æªÉA"¿"ðZbg·éB
        l_equityProductQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
        
        //Uj@@ßèlIuWFNg.sêR[hÉAøÌæøÁ¿.sêIDÉY·ésê.sêR[hðZbg·éB
        l_equityProductQuote.setMarketCode(l_eqtypeTradedProduct.getMarket().getMarketCode());
        
        //Vj@@ÈºAPjÅæ¾ÏÌ¿îñ©çAÂîñÌZbgðs¤B 
        //ßèlIuWFNg.»ÝlÉAWEB3EqTypeQuoteData.getCurrentPrice( )ðZbg·éB(*1) 
        if (l_eqtypeQuoteData.getCurrentPrice() == 0.0D)
        {
            l_equityProductQuote.setBoardCurrentPrice(null);
        }
        else
        {
            l_equityProductQuote.setBoardCurrentPrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getCurrentPrice()));
        }
        
        //ßèlIuWFNg.»ÝlÉAWEB3EqTypeQuoteData.getCurrentPriceTime( )ðZbg·éB 
        l_equityProductQuote.setBoardCurrentPriceTime(
            l_eqtypeQuoteData.getCurrentPriceTime());
        
        //ßèlIuWFNg.»ÝlæªÉAWEB3EqTypeQuoteData.getCurrentPriceFlag( )ðZbg·éB
        //WEB3EqTypeQuoteData.getCurrentPrice()==0ÌêÍAnullðZbgB
        if (l_eqtypeQuoteData.getCurrentPrice() == 0.0D)
        {
            l_equityProductQuote.setBoardCurrentPriceDiv(null);
        }
        else
        {
            String l_strCurrentPriceFlag = l_eqtypeQuoteData.getCurrentPriceFlag().intValue() + "";
            l_equityProductQuote.setBoardCurrentPriceDiv(l_strCurrentPriceFlag);
        }
        
        //ßèlIuWFNg.»ÝlOúäÉAWEB3EqTypeQuoteData.getChange( )ðZbg·éB(*2)         
        if (Double.isNaN(l_eqtypeQuoteData.getChange()))
        {
            l_equityProductQuote.setBoardChange(null);
        }
        else
        {
            l_equityProductQuote.setBoardChange(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getChange()));
        }
        
        //ßèlIuWFNg.oÉAWEB3EqTypeQuoteData.getVolume( )ðZbg·éB(*1)        
        if (l_eqtypeQuoteData.getVolume() == 0.0D)
        {
            l_equityProductQuote.setVolume(null);
        }
        else
        {
            l_equityProductQuote.setVolume(WEB3StringTypeUtility.formatNumber(
                l_eqtypeQuoteData.getVolume()));
        }
        
        //ßèlIuWFNg.oÉAWEB3EqTypeQuoteData.getVolumeTime( )ðZbg·éB 
        l_equityProductQuote.setVolumeTime(l_eqtypeQuoteData.getVolumeTime());
        
        //ßèlIuWFNg.Czl^CgæªÉAWEB3EqTypeQuoteData.getAskPriceTitle( )ðZbg·éB
        //WEB3EqTypeQuoteData.getAskPrice()==0ÌêÍAnullðZbg
        if (l_eqtypeQuoteData.getAskPrice()== 0.0D)
        {
            l_equityProductQuote.setAskPriceTitle(null);  
        }
        else
        {
            String l_strAskPriceTitle = l_eqtypeQuoteData.getAskPriceTitle().intValue() + "";
            l_equityProductQuote.setAskPriceTitle(l_strAskPriceTitle); 
        }
        
        //ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getAskPrice( )ðZbg·éB(*1) 
        if (l_eqtypeQuoteData.getAskPrice() == 0.0D)
        {
            l_equityProductQuote.setAskPrice(null);
        } 
        else
        {
            l_equityProductQuote.setAskPrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getAskPrice()));
        }
        
        //ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getAskPriceTime( )ðZbg·éB 
        l_equityProductQuote.setAskPriceTime(l_eqtypeQuoteData.getAskPriceTime());
        
        //ßèlIuWFNg.Czl^CgæªÉAWEB3EqTypeQuoteData.getBidPriceTitle( )ðZbg·éB
        //WEB3EqTypeQuoteData.getBidPrice()==0ÌêÍAnullðZbg
        if (l_eqtypeQuoteData.getBidPrice() == 0.0D)
        {
            l_equityProductQuote.setBidPriceTitle(null);
        }
        else
        {
            String l_strBidPriceTitle = l_eqtypeQuoteData.getBidPriceTitle().intValue() + "";
            l_equityProductQuote.setBidPriceTitle(l_strBidPriceTitle);
        }
        
        //ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getBidPrice( )ðZbg·éB(*1) 
        if (l_eqtypeQuoteData.getBidPrice() == 0.0D)
        {
            l_equityProductQuote.setBidPrice(null);
        } 
        else
        {
            l_equityProductQuote.setBidPrice(WEB3StringTypeUtility.formatNumber(
                l_eqtypeQuoteData.getBidPrice()));
        }
        
        //ßèlIuWFNg.CzlÉAWEB3EqTypeQuoteData.getBidPriceTime( )ðZbg·éB 
        l_equityProductQuote.setBidPriceTime(l_eqtypeQuoteData.getBidPriceTime());
        
        //ßèlIuWFNg.îliÉAWEB3EqTypeQuoteData.getBasePrice( )ðZbg·éB(*1)    
        if (l_eqtypeQuoteData.getBasePrice() == 0.0D)
        {
            l_equityProductQuote.setBasePrice(null);
        } 
        else
        {
            l_equityProductQuote.setBasePrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getBasePrice()));
        }
        
        log.exiting(STR_METHOD_NAME);
        //Wj@@ì¬µ½ßèlIuWFNgðÔ·B
        return l_equityProductQuote;
    }
    
    /**
     * (getÁ¿FromåWÁ¿)<BR>
     * <BR>
     * øÌåWÁ¿æè®Á¿ðæ¾·éB <BR>
     * <BR>
     * PjÁ¿R[hÏ· <BR>
     * @@p[^.åWÁ¿.Á¿R[hÌ5byteÚð0Éu··éB <BR>
     * <BR>
     * Qj®Á¿æ¾ <BR>
     * @@this.getProduct()\bhðR[µA®Á¿ðæ¾·éB <BR>
     * <BR>
     * @@[getProduct()ÉZbg·ép[^] <BR>
     * @@@@ØïÐF@@p[^.åWÁ¿.getInstitution()Ìßèl <BR>
     * @@@@Á¿R[hF@@PjÉÄÏ·µ½Á¿R[h <BR>
     * <BR>
     * RjQjÉÄæ¾µ½®®Á¿ðÔp·éB <BR>
     * <BR>
     * @@params l_subscriptionProduct åWÁ¿<BR>
     * åWÁ¿Ì®Á¿IuWFNg<BR>
     * <BR>
     * @@return EqTypeProduct<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3EquityProduct getProductFromSubscriptionProduct(WEB3EquityProduct l_subscriptionProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductFromSubscription(EqTypeProduct)";
        log.entering(STR_METHOD_NAME);
        
        // Á¿R[hÏ·
        String l_strChangedProductCode =
            l_subscriptionProduct.getProductCode().substring(0, 4) + "0";
        
        // ®Á¿æ¾
        WEB3EquityProduct l_newEqtypeProduct = null;
        try
        {
            l_newEqtypeProduct =
                (WEB3EquityProduct)this.getProduct(l_subscriptionProduct.getInstitution(),
                    l_strChangedProductCode);
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "Á¿R[h:[" + l_strChangedProductCode + "]ÉY·é®Á¿ªæ¾Å«Ü¹ñB",
                l_ex);
        }
        
        return l_newEqtypeProduct;
    }
    
    /**
     * (get¿îñ)<BR>
     * <BR>
     * wèàeÉ¶½¿îñðæ¾·éBæ¾ÍÈºÌÊèB<BR>
     * -----------------------------------------------------------------<BR>
     * êF@@@@¿T[o®æøÁ¿e[u<BR>
     * ø¯ãorIle[u³ðreadwèF<BR>
     * @@@@@@@@@@@@Ile[u¿T[o®æøÁ¿e[u<BR>
     * -----------------------------------------------------------------<BR>
     * <BR>
     * Pj@@øÌisIle[u³ðread==trueAÜ½Í<BR>
     * @@@@ ø¯ãiæøÔÇ.issêJÇÔÑ( )==falsejÌêÌÝAúIlðæ¾·éB<BR>
     * @@@@@@ithis.getIl(Á¿ID, sêR[h, îú)Åæ¾·éj<BR>
     * <BR>
     * @@@@@@---------------------------------------------------------- <BR>
     * @@@@@@getIl( )FøÝèdl <BR>
     * <BR>
     * @@@@@@Á¿IDF@@øÌæøÁ¿.Á¿ID <BR>
     * @@@@@@sêR[hF@@øÌæøÁ¿.sêIDÉY·ésêR[h <BR>
     * @@@@@@îúF@@nulliwèÈµj<BR>
     * @@@@@@---------------------------------------------------------- <BR>
     * <BR>
     * @@@@æ¾µ½l > 0iIl èjÌê <BR>
     * @@@@this.getæøÁ¿(®Á¿AsêAîú)ÉæèæøÁ¿ðæ¾·éB <BR>
     * <BR>
     * @@@@@@---------------------------------------------------------- <BR>
     * @@@@@@getæøÁ¿ijFøÝè <BR>
     * <BR>
     * @@@@@@®Á¿F@@øÌæøÁ¿.getProduct()Ìßèlð®Á¿ÉLXg <BR>
     * @@@@@@sêF@@øÌæøÁ¿.getMarket()Ìßèl <BR>
     * @@@@@@îúF@@æøÔÇ.get­ú( )Åæ¾µ½­úÌOcÆú <BR>
     * @@@@@@---------------------------------------------------------- <BR>
     * <BR>
     * @@@@ßèlIuWFNgÌvpeBðÈºÌÊèÉÝèµAÔp·éB 
     * @@@@------------------------------------------------------------------ <BR>
     * @@@@ßèlvpeBÝè <BR>
     * <BR>
     * @@@@®Á¿¿îñ.¿ = æ¾µ½l <BR>
     * @@@@®Á¿¿îñ.Oúä = æ¾µ½úIl - getæøÁ¿Ìßèl.îliIlj<BR>
     * @@@@@@¦ãLAîl(Il) == 0 ÌêÍDouble.NaNðZbgB<BR>
     * @@@@®Á¿¿îñ.¿­\Ô = null <BR>
     * @@@@®Á¿¿îñ.¿æ¾æª = "úIl" <BR>
     * @@@@®Á¿¿îñ.¿æª = "»Ýl" <BR>
     * @@@@®Á¿¿îñ.sêR[h = øÌæøÁ¿.sêIDÉY·ésêR[h <BR>
     * @@@@------------------------------------------------------------------ <BR>
     * @@@@ãLÈOÌêÍAÈ~Ìðs¤B <BR>
     * <BR>
     * Qj@@¿T[o©ç¿ðæ¾·éB <BR>
     * <BR>
     * Q|Pj@@¿T[rXiWEB3QuoteDataSupplierServicejæèA <BR>
     * @@@@@@øÌRealTypeÉ¶½¿îñiEqTypeQuoteDatajðæ¾·éB <BR>
     * <BR>
     * @@@@@@AwèiøÌRealType == "A"jÌêF <BR>
     * @@@@@@@@WEB3QuoteDataSupplierService.getEqTypeQuote(øÌæøÁ¿, "A"); <BR>
     * <BR>
     * @@@@@@fBCwèiøÌRealType != "A"jÌêF <BR>
     * @@@@@@@@WEB3QuoteDataSupplierService.getEqTypeQuote(øÌæøÁ¿, "20ªfBC"); <BR>
     * <BR>
     * Q|Qj@@æ¾µ½¿îñðÈºÌDæÊÅ]¿µA <BR>
     * @@@@@@lªt¢Ä¢éi==0ÅÈ¢jP¿ª éêÍAßèlIuWFNgÌvpeBðÈºÌÊèÉÝèµA <BR>
     * @@@@Ôp·éB <BR>
     * <BR>
     * @@@@------------------------------------------------------------------ <BR>
     * @@@@]¿Ê <BR>
     * <BR>
     * @@@@PD»ÝliEqTypeQuoteData.getCurrentPrice( )j <BR>
     * @@@@QDCzliEqTypeQuoteData.getBidPrice( )j <BR>
     * @@@@RDCzliEqTypeQuoteData.getAskPrice( )j <BR>
     * @@@@------------------------------------------------------------------ <BR>
     * @@@@ßèlvpeBÝè <BR>
     * <BR>
     * @@@@®Á¿¿îñ.¿ = lªt¢Ä¢élÌ¤¿A]¿ÊªÅà¢àÌ <BR>
     * @@@@®Á¿¿îñ.Oúä = EqTypeQuoteData.getChange( ) <BR>
     * @@@@®Á¿¿îñ.¿­\Ô = ¿Æµ½P¿ÉÎ·é¿­\Ô <BR>
     * @@@@@@¦áF@@»Ýlð¿Æ·éêAEqTypeQuoteData.getCurrentPriceTime( )ðZbg·éB <BR>
     * @@@@@@¦nullªÔ³ê½êAnullð»ÌÜÜZbg·éB <BR>
     * @@@@®Á¿¿îñ.¿æ¾æª = "¿" <BR>
     * @@@@®Á¿¿îñ.¿æª = ¿Æµ½P¿ÉY·é¿æª <BR>
     * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@i»Ýl^Czl^Czlj <BR>
     * @@@@®Á¿¿îñ.sêR[h = øÌæøÁ¿.sêIDÉY·ésêR[h <BR>
     * @@@@------------------------------------------------------------------ <BR>
     * @@@@ãLÈOÌêÍAÈ~Ìðs¤B <BR>
     * <BR>
     * Rj@@æøÁ¿©çAOúIlðæ¾·éB <BR>
     * <BR>
     * @@@@ßèlIuWFNgÌvpeBðÈºÌÊèÉÝèµAÔp·éB <BR>
     * @@@@------------------------------------------------------------------ <BR>
     * @@@@ßèlvpeBÝè <BR>
     * <BR>
     * @@@@®Á¿¿îñ.¿ = øÌæøÁ¿.getLastClosingPrice( )iæøÁ¿.îliIljj <BR>
     * @@@@®Á¿¿îñ.Oúä = Double.NaN<BR>
     * @@@@®Á¿¿îñ.¿­\Ô = null <BR>
     * @@@@®Á¿¿îñ.¿æ¾æª = "OúIl" <BR>
     * @@@@®Á¿¿îñ.¿æª = "OúIl" <BR>
     * @@@@®Á¿¿îñ.sêR[h = øÌæøÁ¿.sêIDÉY·ésêR[h <BR>
     * @@@@------------------------------------------------------------------ <BR>
     * <BR>
     * @@param l_eqtypeTradedProduct<BR>
     * (æøÁ¿)<BR>
     * @@param l_realType<BR>
     * (RealType)<BR>
     * @@param l_isLastClosingPriceUnconditionalyRead - (isIle[u³ðread)<BR>
     * Ile[uð³ðÉread·é©Ç¤©ÌtO
     * @@return WEB3EquityProduct<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3EquityProductQuote getProductQuote(EqTypeTradedProduct l_eqtypeTradedProduct, RealType l_realType, boolean l_isLastClosingPriceUnconditionalyRead)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductQuote(EqtypeTradedProduct, RealType, boolean)";
        log.entering(STR_METHOD_NAME);
        
        String l_strMarketCode = l_eqtypeTradedProduct.getMarket().getMarketCode();
        
        // êø¯ã(æøÔÇ.issêJÇÔÑ() == false)ÌêAúIlðæ¾·éB
        if (l_isLastClosingPriceUnconditionalyRead ||
            WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() == false)
        {
            double l_dblTodayLastClosingPrice =
                this.getLastClosingPrice(l_eqtypeTradedProduct.getProduct().getProductId(),
                    l_strMarketCode,
                    null);
            
            // Il èÌê
            if (l_dblTodayLastClosingPrice > 0)
            {
                // ­úðæ¾
                Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                WEB3GentradeBizDate l_genBizDate =
                    new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
                //æ¾µ½l > 0iIl èjÌê
                //@@@@this.getæøÁ¿(®Á¿AsêAîú)ÉæèæøÁ¿ðæ¾·éB
                EqtypeTradedProductParams l_tradeProduct = this.getTradedProduct(
                    (WEB3EquityProduct)l_eqtypeTradedProduct.getProduct(),
                    l_eqtypeTradedProduct.getMarket(),
                    l_genBizDate.roll(-1));

                // ßèlIuWFNg¶¬
                WEB3EquityProductQuote l_productQuote = new WEB3EquityProductQuote();
                
                l_productQuote.setQuote(l_dblTodayLastClosingPrice);
                // Oúä
                if (l_tradeProduct.getLastClosingPrice() == 0.0D)
                {
                    l_productQuote.setComparedPreviousDay(Double.NaN);
                }
                else
                {
                    l_productQuote.setComparedPreviousDay(l_dblTodayLastClosingPrice - l_tradeProduct.getLastClosingPrice());
                }
                l_productQuote.setQuoteTime(null);
                l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.CLOSING_PRICE);
                l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.CURRENT_PRICE);
                l_productQuote.setMarketCode(l_strMarketCode);
                    
                return l_productQuote;
            }
        }
        
        // ¿T[o©ç¿ðæ¾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3QuoteDataSupplierService l_quoteDataSupplierService =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService();
            
        WEB3EqTypeQuoteData l_quoteData = null;
        try
        {
            l_quoteData = l_quoteDataSupplierService.getEqTypeQuote(l_eqtypeTradedProduct, l_realType);
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ØïÐR[hF[" + l_eqtypeTradedProduct.getInstitution().getInstitutionCode()
                + "] Á¿IDF[" + l_eqtypeTradedProduct.getProduct().getProductId()
                + "] sêR[hF[" + l_strMarketCode
                + "ÉÎ·é¿îñª©Â©èÜ¹ñB",
                l_exp);
        }
        
        // 1.5. »ÝlACzlACzlÌÅ¿iîñðæ¾·é
        //     ilªt¢Ä¢éiæ¾µ½l > 0jÌ_ÅAÈ~Ì¿iîñÍæ¾µÈ¢jB
        double l_dblMarketPrice = 0.0D;
        Timestamp l_tsQuoteTime = null;
        String l_strQuoteTypeDiv = null;
        
        // 1.5.1. getCurrentPrice
        l_dblMarketPrice = l_quoteData.getCurrentPrice();
        l_tsQuoteTime = l_quoteData.getCurrentPriceTime();
        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.CURRENT_PRICE;
        
        // 1.5.3. getBidPriceTime
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getBidPrice();
            l_tsQuoteTime = l_quoteData.getBidPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.BID_PRICE;
        }
        // 1.5.5. getAskPrice
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getAskPrice();
            l_tsQuoteTime = l_quoteData.getAskPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.ASK_PRICE;
        }
        
        WEB3EquityProductQuote l_productQuote = null;
        // 1.6. »ÝlACzlACzlÌ¢¸ê©ðæ¾Å«½êiæ¾µ½l > 0Ìêj
        if (l_dblMarketPrice > 0.0D)
        {
            l_productQuote = new WEB3EquityProductQuote();
            l_productQuote.setQuote(l_dblMarketPrice);
            l_productQuote.setComparedPreviousDay(l_quoteData.getChange());
            l_productQuote.setQuoteTime(l_tsQuoteTime);
            l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
            l_productQuote.setQuoteTypeDiv(l_strQuoteTypeDiv);
            l_productQuote.setMarketCode(l_strMarketCode);
        }
        else
        {
            // 1.7. getLastClosingPrice
            double l_dblLastClosingPrice = l_eqtypeTradedProduct.getLastClosingPrice();
            // 1.8. OúIlðæ¾Å«½êiæ¾µ½l > 0Ìêj
            if (l_dblLastClosingPrice > 0.0D)
            {
	            l_productQuote = new WEB3EquityProductQuote();
                l_productQuote.setQuote(l_dblLastClosingPrice);
                l_productQuote.setComparedPreviousDay(Double.NaN);
                l_productQuote.setQuoteTime(null);
                l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setMarketCode(l_strMarketCode);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
    
    /**
     * (get¿)<BR>
     * ¿ðæ¾·éBæ¾ÍÈºÌÊèB<BR>
     * -----------------------------------------------<BR>
     * êF@@@@¿T[o®æøÁ¿e[u<BR>
     * ø¯ãF@@Ile[u¿T[o®æøÁ¿e[u<BR>
     * -----------------------------------------------<BR>
     * <BR>
     * Pj@@this.get¿(æøÁ¿. boolean)Édelegate·éB<BR>
     * <BR>
     * @@@@@@----------------------------------------------------------<BR>
     * @@@@@@get¿( )FøÝèdl<BR>
     * <BR>
     * @@@@@@æøÁ¿F@@øÌæøÁ¿<BR>
     * @@@@@@isIle[u³ðreadF@@falseireadÍðt«j<BR>
     * @@@@@@----------------------------------------------------------<BR>
     * <BR>
     * @@param l_tradedProduct - (æøÁ¿)<BR>
     * æøÁ¿IuWFNg
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getCurrentPrice(EqTypeTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getCurrentPrice(EqTypeTradedProduct)";

        log.entering(STR_METHOD_NAME);
        return this.getCurrentPrice(l_tradedProduct, false);
    }
    
    /**
     * (get¿îñ)<BR>
     * wèàeÉ¶½¿îñðæ¾·éBæ¾ÍÈºÌÊèB<BR>
     * -----------------------------------------------<BR>
     * êF@@@@¿T[o®æøÁ¿e[u<BR>
     * ø¯ãF@@Ile[u¿T[o®æøÁ¿e[u<BR>
     * -----------------------------------------------<BR>
     * <BR>
     * Pj@@this.get¿îñ(æøÁ¿, RealType, boolean)Édelegate·éB<BR>
     * <BR>
     * @@@@@@----------------------------------------------------------<BR>
     * @@@@@@get¿îñ( )FøÝèdl<BR>
     * <BR>
     * @@@@@@æøÁ¿F@@øÌæøÁ¿<BR>
     * @@@@@@RealTypeF@@øÌRealType<BR>
     * @@@@@@isIle[u³ðreadF@@falseireadÍðt«j<BR>
     * @@@@@@----------------------------------------------------------<BR>
     * <BR>
     * @@param l_tradedProduct - (æøÁ¿)<BR>
     * æøÁ¿IuWFNg
     * @@param l_realType - (RealType)<BR>
     * A^fBCæª
     * @@return WEB3EquityProductQuote
     * @@throws WEB3BaseException
     */
    public WEB3EquityProductQuote getProductQuote(
        EqTypeTradedProduct l_tradedProduct,
        RealType l_realType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductQuote(EqtypeTradedProduct, RealType)";
        log.entering(STR_METHOD_NAME);
        return this.getProductQuote(l_tradedProduct, l_realType, false);
    }
    
    /**
     * (isSÛK§ÎÛÁ¿)<BR>
     * wèÁ¿ªSÛK§ÎÛÅ é©Ç¤©ð»è·éB<BR>
     * |wèÌisiæªjÉY·éK§L³ðÔp·éB<BR>
     * @@æªwèÈµÍA^Ì¢¸ê©ÅK§ª©©ÁÄ¢éêAK§ èÆ»è·éB<BR>
     * |ñMpÁ¿ÌêàAÛØà¦Ýèlðgpµê¥Å»è·éB<BR>
     * <BR>
     * Pj@@ÛØà¦(*1)A»àÛØà¦(*2)ÌXÝèlðæ¾·éB<BR>
     * <BR>
     * @@@@øÌâûÀ.getæøX()ÅAXIuWFNgðæ¾·éB<BR>
     * @@@@ÈºAXIuWFNgÌ¯¼vpeBðgp·éB<BR>
     * <BR>
     * Qj@@wèÌisi^jÉÎµSÛK§ªKp³êÄ¢é©Ç¤©ð`FbNµA<BR>
     * @@@@@@`FbNÊðÔp·éB<BR>
     * <BR>
     * Q|Pj@@øÌis==trueijÌê<BR>
     * <BR>
     * @@@@@@ÈºÌ¢¸ê©ÉY·éêAtrueðÔp·éB<BR>
     * @@@@@@@@|øÌæøÁ¿.ÛØà¦ > ÛØà¦(*1)<BR>
     * @@@@@@@@|øÌæøÁ¿.»àÛØà¦ > »àÛØà¦(*2)<BR>
     * <BR>
     * @@@@@@ãLÌ¢¸êÉàYµÈ¢êÍAfalseðÔp·éB<BR>
     * <BR>
     * Q|Qj@@øÌis==falseijÌê<BR>
     * <BR>
     * @@@@@@ÈºÌ¢¸ê©ÉY·éêAtrueðÔp·éB<BR>
     * @@@@@@@@|øÌæøÁ¿.ÛØà¦ > ÛØà¦(*1)<BR>
     * @@@@@@@@|øÌæøÁ¿.»àÛØà¦ > »àÛØà¦(*2)<BR>
     * <BR>
     * @@@@@@ãLÌ¢¸êÉàYµÈ¢êÍAfalseðÔp·éB<BR>
     * <BR>
     * Q|Rj@@øÌis==nulliæª¢wèjÌê<BR>
     * <BR>
     * @@@@@@ÈºÌ¢¸ê©ÉY·éêAtrueðÔp·éB<BR>
     * @@@@@@@@|øÌæøÁ¿.ÛØà¦ > ÛØà¦(*1)<BR>
     * @@@@@@@@|øÌæøÁ¿.»àÛØà¦ > »àÛØà¦(*2)<BR>
     * @@@@@@@@|øÌæøÁ¿.ÛØà¦ > ÛØà¦(*1)<BR>
     * @@@@@@@@|øÌæøÁ¿.»àÛØà¦ > »àÛØà¦(*2)<BR>
     * <BR>
     * @@@@@@ãLÌ¢¸êÉàYµÈ¢êÍAfalseðÔp·éB<BR>
     * @@param l_subAccount - (âûÀ)<BR>
     * âûÀIuWFNgB
     * @@param l_tradedProduct - (æøÁ¿)<BR>
     * ®æøÁ¿IuWFNgB
     * @@param isBuyContract - (is)<BR>
     * tOB<BR>
     * itrueFAfalseFAnullFæª¢èj
     * @@return boolean
     */
    public boolean isAdditionalCollateralRegulateProduct(
        WEB3GentradeSubAccount l_subAccount,
        WEB3EquityTradedProduct l_tradedProduct,
        Boolean isBuyContract)
    {
        final String STR_METHOD_NAME =
            "isAdditionalCollateralRegulateProduct(WEB3GentradeSubAccount, WEB3EquityTradedProduct, Boolean)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_isAdditionalCollateralRegulateProduct = false;
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        double l_dblMarginDepositRate = l_branchRow.getMarginDepositRate();
        double l_dblCashMarginDepositRate = l_branchRow.getCashMarginDepositRate();
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        
        if (isBuyContract != null)
        {
            if (Boolean.TRUE.equals(isBuyContract))
            {
                if (l_tradedProductRow.getLongMarginDepositRate() > l_dblMarginDepositRate ||
                    l_tradedProductRow.getLongCashMarginDepositRate() > l_dblCashMarginDepositRate)
                {
                    l_isAdditionalCollateralRegulateProduct = true;
                }
            }
            else
            {
                if (l_tradedProductRow.getShortMarginDepositRate() > l_dblMarginDepositRate ||
                    l_tradedProductRow.getShortCashMarginDepositRate() > l_dblCashMarginDepositRate)
                {
                    l_isAdditionalCollateralRegulateProduct = true;
                }
            }
        }
        else
        {
            if (l_tradedProductRow.getLongMarginDepositRate() > l_dblMarginDepositRate ||
                l_tradedProductRow.getLongCashMarginDepositRate() > l_dblCashMarginDepositRate ||
                l_tradedProductRow.getShortMarginDepositRate() > l_dblMarginDepositRate ||
                l_tradedProductRow.getShortCashMarginDepositRate() > l_dblCashMarginDepositRate)
            {
                l_isAdditionalCollateralRegulateProduct = true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isAdditionalCollateralRegulateProduct;
    }

    /**
     * (getæøÁ¿)<BR>
     * øÌðÉY·éæøÁ¿Rowðæ¾µAæøÁ¿CX^XðÔp·éB <BR>
     * <BR>
     * Pj@@DBõ  <BR>
     * @@®æøÁ¿e[uðÈºÌðÅõ·éB  <BR>
     * @@[ð]  <BR>
     * @@Á¿ID = ø.®Á¿.Á¿ID ©Â <BR>
     * @@sêID = ø.sê.sêID ©Â <BR>
     * @@Løú = ø.îú@@itH[}bgFYYYYMMDDj <BR>
     * <BR>
     * @@¦®æøÁ¿e[uÌõÊªæ¾Å«È©Á½êA <BR>
     * @@@@¯ðÅ®æøÁ¿updqe[uðõ·éB <BR>
     * <BR>
     * Qj@@æ¾µ½R[hðÔp·éB<BR>
     * @@param l_equityProduct - (®Á¿)<BR>
     * ®Á¿IuWFNg<BR>
     * @@param l_market - (sê)<BR>
     * sêIuWFNg<BR>
     * @@param l_datBaseDate - (îú)<BR>
     * îú<BR>
     * @@return EqtypeTradedProductParams
     * @@throws WEB3SystemLayerException
     */
    public EqtypeTradedProductParams getTradedProduct(
        WEB3EquityProduct l_equityProduct,
        Market l_market,
        Date l_datBaseDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getTradedProduct(WEB3EquityProduct, Market, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_equityProduct == null || l_market == null)
        {
            log.debug("p[^ls³B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "p[^ls³B");
        }

        //@@[ð]
        //@@Á¿ID = ø.®Á¿.Á¿ID ©Â
        //@@sêID = ø.sê.sêID ©Â
        //@@Løú = ø.îú@@itH[}bgFYYYYMMDDj
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" product_id = ? ");
        l_sbWhere.append(" and market_id = ? ");
        l_sbWhere.append(" and valid_until_biz_date = ? ");

        List l_lisWheres = new ArrayList();
        l_lisWheres.add(new Long(l_equityProduct.getProductId()));
        l_lisWheres.add(new Long(l_market.getMarketId()));
        l_lisWheres.add(WEB3DateUtility.formatDate(l_datBaseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD));
        Object[] l_objWhere =  new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWhere);

        List l_lisRows = null;
        EqtypeTradedProductParams l_eqtypeTradedProductParams = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //®æøÁ¿e[uðÈºÌðÅõ
            l_lisRows = l_processor.doFindAllQuery(
                EqtypeTradedProductRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);

            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                //®æøÁ¿e[uÌõÊªæ¾Å«È©Á½ê
                //¯ðÅ®æøÁ¿updqe[uðõ·é
                l_lisRows = l_processor.doFindAllQuery(
                    EqtypeTradedProductUpdqRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
                if (l_lisRows == null || l_lisRows.size() == 0)
                {
                    log.debug("e[uÉY·éf[^ª èÜ¹ñB");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "e[uÉY·éf[^ª èÜ¹ñB");
                }
                EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow =
                    (EqtypeTradedProductUpdqRow)l_lisRows.get(0);
                l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
                GtlUtils.copyRow2Params(l_eqtypeTradedProductUpdqRow, l_eqtypeTradedProductParams);
            }
            else
            {
                l_eqtypeTradedProductParams =
                    new EqtypeTradedProductParams((EqtypeTradedProductRow)l_lisRows.get(0));
            }

            //æ¾µ½R[hðÔp·éB
            log.exiting(STR_METHOD_NAME);
            return l_eqtypeTradedProductParams;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBÖÌANZXÉ¸sµÜµ½B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (isúîlóM)<BR>
     * <BR>
     * úîlóM®¹©Ç¤©Ì`FbNðs¤B <BR>
     * <BR>
     * ÈºÌðÅAvZXÇe[uðõ·éB<BR>
     * e[u©çê½Í¡ÌR[hªæ¾Å«½êAtrueðÔp·éB<BR>
     * æ¾Å«È©Á½êAfalseðÔp·éB<BR>
     * <BR>
     * [ð]<BR>
     * vZXhc = "0011Fúîl¬ñóM" <BR>
     * ØïÐR[h = ø.ØïÐR[h<BR>
     * <BR>
     * @@params l_strInstitutionCode - (ØïÐR[h)<BR>
     * ØïÐR[h<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException<BR>
     */
    public boolean isNextDayBasePriceMail(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNextDayBasePriceMail(String)";
        log.entering(STR_METHOD_NAME);

        String l_strWhere =
            " process_id = ? and institution_code = ? ";
        Object l_bindVars[] =
        {
    		WEB3ProcessIdDef.NEXT_DAY_BASE_PRICE_QUICK_REPORT_REC,
            l_strInstitutionCode
        };

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    ProcessManagementRow.TYPE,
                    l_strWhere,
                    l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.entering(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //æ¾Å«È©Á½ê
        if (l_lisRows.size() < 1)
        {
            log.entering(STR_METHOD_NAME);
            return false;
        }
        //e[u©çê½Í¡ÌR[hªæ¾Å«½êAtrueðÔp·éB
        else
        {
            log.entering(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (isåø¯îlóM®¹)<BR>
     * úîlóM®¹©Ç¤©Ì`FbNðs¤B<BR>
     * <BR>
     * ÈºÌðÅAvZXÇe[uðõ·éB<BR>
     * e[u©çê½Í¡ÌR[hªæ¾Å«½êAtrueðÔp·éB<BR>
     * æ¾Å«È©Á½êAfalseðÔp·éB<BR>
     * <BR>
     * [ð]<BR>
     * vZXhc = "0012F®Il¬ñóM"<BR>
     * ØïÐR[h = ø.ØïÐR[h<BR>
     * @@params l_strInstitutionCode - (ØïÐR[h)<BR>
     * ØïÐR[h<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException<BR>
     */
    public boolean isBasePriceRecCompleted(String l_strInstitutionCode)
        throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "isBasePriceRecCompleted(String)";
        log.entering(STR_METHOD_NAME);

        String l_strWhere =
            " process_id = ? and institution_code = ? ";
        Object l_bindVars[] =
        {
    		WEB3ProcessIdDef.EQ_LAST_PRICE_QUICK_REPORT_REC,
            l_strInstitutionCode
        };

        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRows =
                l_queryProcesser.doFindAllQuery(
                    ProcessManagementRow.TYPE,
                    l_strWhere,
                    l_bindVars);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.entering(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //æ¾Å«È©Á½ê
        if (l_lisRows.size() < 1)
        {
            log.entering(STR_METHOD_NAME);
            return false;
        }
        //e[u©çê½Í¡ÌR[hªæ¾Å«½êAtrueðÔp·éB
        else
        {
            log.entering(STR_METHOD_NAME);
            return true;
        }
    }
}
@
