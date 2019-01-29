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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���v���_�N�g�}�l�[�W��(WEB3EquityProductManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/20 �����@@�ǘa(SRA) �V�K�쐬
Revesion History : 2006/07/04 ���� (���u) �d�l�ύX�Ǘ�No.935
Revesion History : 2006/07/19 ���� (���u) �d�l�ύX�Ǘ�No.956
Revesion History : 2007/02/10 ��іQ(���u) ���f�� No.1122
Revesion History : 2007/11/12 �����F(���u) ���f�� No.1203
Revesion History : 2007/11/19 �����F(���u) ���f�� No.1214
Revesion History : 2007/11/22 �����F(���u) ���f�� No.1223
Revesion History : 2009/09/21 �Ԑi  (���u) ���f�� No.1336 No.1337 No.1340 No.1342 No.1346 No.1347 �v�Z���� No.035 No.039
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
 * �i�g���v���_�N�g�}�l�[�W���j�B<BR>
 * <BR>
 * ���i�ɑ΂��鑀���\�����܂��B<BR>
 * xTrade��EqTypeProductManager���g�������N���X�B
 * @@author �����@@�ǘa(SRA)
 * @@version 1.0
 */
public class WEB3EquityProductManager extends EqTypeProductManagerImpl
{

    /**
     * ���O���[�e�B���e�B
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
     * (�� Javadoc)
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
     * (�� Javadoc)
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
     * (�� Javadoc)
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
     * (�� Javadoc)
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
     * (�� Javadoc)
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
     * (�� Javadoc)
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
     * ProductRow(�Q)����ɁAWEB3EquityProduct(�Q)�𐶐����Ԃ��܂��B<BR>
     * @@param l_lisProductRows
     * @@return List WEB3EquityProduct(�Q)
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
     * (get�����l��)<BR>
     * �y�l���e�[�u���z���A�Y������s��̐����l�����擾���Ԃ��B<BR>
     * �y�l���e�[�u���z����̎擾�ɂ́A�w�肳�ꂽ��l�A�y��<BR>
     * �i���́j��������D�s��ID�Ły�s��e�[�u���z���������A�擾�����s��R�[�h���g�p����B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[������2]�l���Z�o�v���Q�ƁB<BR>
     * <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * �y�l���e�[�u���z�������ɁA�s��R�[�h�̎w��Ɏg�p����B<BR>
     * �܂��A�y�s��e�[�u���z�������̎s��ID�w��ɂ��g�p����B
     * @@param l_dblBasePrice - (��l)<BR>
     * �y�l���e�[�u���z���琧���l�����擾����ۂɁA��l�Ƃ��Ďg�p����B
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
 
        // �s��R�[�h�̎擾
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        
        // �����l���̎擾
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
     * (get���ݒl)<BR>
     * �Ēl�e�[�u������A�w�肳�ꂽ��l���g�p���ĊY������s��̍��ݒl���擾����B<BR>
     * ���ݒl�̎擾�́A�w�肳�ꂽ��l�A�y��<BR>
     * �i���́j��������D�s��ID�Ły�s��e�[�u���z���������A�擾�����s��R�[�h�ōs���B<BR>
     * �擾�������ݒl�͌Ēl�`�F�b�N�Ɏg�p����B<BR>
     * <BR>
     * �����\�b�h�̏ڍׂ�<BR>
     * �u��{�݌v�v�Z�����i�G�N�C�e�B�j.doc�v�́u[������1]���ݒl�擾�v���Q�ƁB<BR>
     * <BR>
     * @@param l_tradedProduct - ������� <BR>
     *     �y�Ēl�e�[�u���z�������́A�s��R�[�h�w��Ɏg�p����B<BR>
     * @@param l_dblBasePrice - ��l <BR>
     *     �y�Ēl�e�[�u���z�������ɁA�Ώۃ��R�[�h����̂��߂̊�l�Ƃ��Ďg�p����B<BR>
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
 
        // �s��R�[�h�̎擾
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        
        // ���ݒl�̎擾
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
     * (get�������)<BR>
     * ���������A�s��I�u�W�F�N�g����������C���X�^���X���擾����B<BR>
     * �igetTradedProduct(Product,Market)�̃I�[�o�[���C�h�j<BR>
     * @@param l_product - (��������)<BR>
     * @@param l_market - (�s��)<BR>
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
     * (get�������)<BR>
     *  <BR>
     * �،���ЃI�u�W�F�N�g�A�����R�[�h�A�s��R�[�h��������� <BR>
     * �C���X�^���X���擾���܂��B <BR>
     * �igetTradedProduct(Institution, String, String)�̃I�[�o�[���C�h�j <BR>
     *  <BR>
     * @@param l_gentradeInstitution - (�،����)<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
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
     * (get����)<BR>
     * <BR>
     * �������擾����B�擾���͈ȉ��̒ʂ�B <BR>
     * ----------------------------------------------- <BR>
     * �ꒆ�F�@@�@@�����T�[�o��������������e�[�u�� <BR>
     * ������or�I�l�e�[�u��������read�w�莞�F<BR>
     * �@@�@@�@@�@@�@@�@@�I�l�e�[�u���������T�[�o��������������e�[�u��<BR>
     * ----------------------------------------------- <BR>
     * <BR>
     * �P�j�@@this.get�������( )���R�[������B <BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@��get�������( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@��������F�@@�����̎������ <BR>
     * �@@�@@�@@RealType�F�@@"���A��" <BR>
     * �@@�@@�@@is�I�l�e�[�u��������read�F�@@������is�I�l�e�[�u��������read<BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �Q�j�@@�߂�l�I�u�W�F�N�g.���� ��ԋp����B <BR>
     * <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g
     * @@param l_isLastClosingPriceUnconditionalyRead - (is�I�l�e�[�u��������read)<BR>
     * �I�l�e�[�u���𖳏�����read���邩�ǂ����̃t���O
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
     * (get��������ꗗ)<BR>
     * <BR>
     * �����̏����ɊY�����銔����������̈ꗗ��<BR>
     * �擾���A�ԋp����B<BR>
     * ���Ǘ��ҁE�������������ݒ�Ŏg�p�B<BR>
     * <BR>
     * �P�j�ȉ��̌���������\���A���������������<BR>
     * �@@ArrayList(�p�����[�^�Z�b�g)���쐬����B<BR>
     * <BR>
     * �@@[��������]<BR>
     * �@@�@@����ID = �p�����[�^.����ID<BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�L���� = (�p�����[�^.�Ώۓ��t�敪�̒l�ɂ��)<BR>
     * <BR>
     * �@@�P�|�P�j��L������������ɁA����������������쐬����B<BR>
     * <BR>
     * �@@�@@�������������� = " product_id = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and institution_code = ? "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and valid_until_biz_date = ?"<BR>
     * <BR>
     * �@@�P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B<BR>
     * �@@�@@ArrayList�𐶐����A�ȉ��̒l���Z�b�g����B<BR>
     * �@@�@@�@@�E�p�����[�^.����ID<BR>
     * �@@�@@�@@�E�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@[�p�����[�^.�Ώۓ��t�敪 == "0�F����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E�Ɩ�(�o�b�`)���t(*1)���Z�b�g�B<BR>
     * �@@�@@�@@[�p�����[�^.�Ώۓ��t�敪 == "1�F���c�Ɠ�"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E�Ɩ����t�̂P�c�Ɠ���̓��t�𕶎���ϊ����ăZ�b�g�B<BR>
     * �@@�@@�@@�@@�@@(�t�H�[�}�b�g YYYYMMDD)<BR>
     * �@@�@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E�Ɩ����t�̂Q�c�Ɠ���̓��t�𕶎���ϊ����ăZ�b�g�B<BR>
     * �@@�@@�@@�@@�@@(�t�H�[�}�b�g YYYYMMDD)<BR>
     * <BR>
     * �Q�j�����Ώۃe�[�u���������肷��B<BR>
     * �@@[�p�����[�^.�Ώۓ��t�敪 == "0�F����"�̏ꍇ]<BR>
     * �@@�@@�e�[�u���� = "������������e�[�u��"(eqtype_traded_product)<BR>
     * �@@[��L�ȊO]<BR>
     * �@@�@@�e�[�u���� = "������������ꎞ�e�[�u��"(eqtype_traded_product_updq)<BR>
     * <BR>
     * �R�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�Q�j�ɂČ��肵���e�[�u����<BR>
     * �@@�@@arg1�F�@@�쐬������������������<BR>
     * �@@�@@arg2�F�@@��������ArrayList.toArray()<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �S�jHashMap�𐶐�����B<BR>
     * <BR>
     * �T�j�R�j�̌������ʂ̌������ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�T�|�P�j�쐬����HashMap.put()���\�b�h�ɂāA�����������Params��ǉ���<BR>
     * ��B<BR>
     * �@@�@@�@@�@@�������������updqParams�̏ꍇ�́A�����������Params�ɕϊ����ăZ�b<BR>
     * �g���邱�ƁB<BR>
     * �@@�@@�@@�@@[put()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�@@key�F�@@�����������Params.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�@@�@@�@@value�F�@@�����������Params<BR>
     * <BR>
     * �U�j��������HashMap��ԋp����B<BR>
     * <BR>
     * (*1)�Ɩ�(�o�b�`)���t�E�E�ETradingSystem.getBizDate()�ɂĎ擾�����Ɩ�(�o�b<BR>
     * �`)���t�B<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strDateDiv - (�Ώۓ��t�敪)<BR>
     * �Ώۓ��t�敪
     * <BR>
     * 0�F�@@����<BR>
     * 1�F�@@���c�Ɠ�<BR>
     * 2�F�@@���X�c�Ɠ�<BR>
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
        
        // �P�|�P�j����������������쐬����B
        String l_strWhere = "product_id = ? and institution_code = ? and valid_until_biz_date = ? and list_flag != ?";
        
        // �P�|�Q�j"?"�ɃZ�b�g���邽�߂̃p�����[�^�Z�b�g���쐬����B
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
        
        // �Q�j�����Ώۃe�[�u���������肷��B
        RowType l_rowType = null;
        if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strDateDiv))
        {
            l_rowType = EqtypeTradedProductRow.TYPE;
        }
        else
        {
            l_rowType = EqtypeTradedProductUpdqRow.TYPE;
        }
        
        // �R�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
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
        // �������ʂ��擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
        if (l_lisTradedProducts == null || l_lisTradedProducts.isEmpty())
        {
            return null;
        }
        
        // �S�jHashMap�𐶐�����B
        HashMap l_tradedProductsMap = new HashMap();
        
        // �T�j�R�j�̌������ʂ̌������ȉ��̏������J��Ԃ��B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        int l_intSize = l_lisTradedProducts.size();
        for (int i = 0;i < l_intSize;i++)
        {
            // �T�|�P�j�쐬����HashMap.put()���\�b�h�ɂāA�����������Params��ǉ�����B
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
        
        // �U�j��������HashMap��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_tradedProductsMap;
    }

    /**
     * (update����)<BR>
     * <BR>
     * �����e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j����PK�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.����ID<BR>
     * <BR>
     * �Q�jQueryProcessor.doUpdateQuery()���R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�P�j�ɂč쐬��������PK<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�ύX�f�[�^<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_changeMap - (�ύX�f�[�^)<BR>
     * �ύX�f�[�^<BR>
     * @@throws WEB3BaseException
     */
    public void updateProduct(
        long l_lngProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j����PK�C���X�^���X�𐶐�����B
        ProductPK l_productPK = new ProductPK(l_lngProductId);
        
        // �Q�jQueryProcessor.doUpdateQuery()���R�[������B
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
     * (update��������)<BR>
     * <BR>
     * ���������e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j��������PK�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.����ID<BR>
     * <BR>
     * �Q�jQueryProcessor.doUpdateQuery()���R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�P�j�ɂč쐬������������PK<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�ύX�f�[�^<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_changeMap - (�ύX�f�[�^)<BR>
     * �ύX�f�[�^<BR>
     * @@throws WEB3BaseException
     */
    public void updateEqtypeProduct(
        long l_lngProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j��������PK�C���X�^���X�𐶐�����B
        EqtypeProductPK l_eqtypeProductPK = new EqtypeProductPK(l_lngProductId);
        
        // �Q�jQueryProcessor.doUpdateQuery()���R�[������B
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
     * (update�������)<BR>
     * <BR>
     * �����e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j�����������PK�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.�������ID<BR>
     * <BR>
     * �Q�jQueryProcessor.doUpdateQuery()���R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�P�j�ɂč쐬���������������PK<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�ύX�f�[�^<BR>
     * <BR>
     * @@param l_lngTradedProductId - (�������ID)<BR>
     * ����ID<BR>
     * @@param l_changeMap - (�ύX�f�[�^)<BR>
     * �ύX�f�[�^<BR>
     * @@throws WEB3BaseException
     */
    public void updateEqtypeTradedProduct(
        long l_lngTradedProductId,
        HashMap l_changeMap) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateEqtypeTradedProduct(long, HashMap)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�����������PK�C���X�^���X�𐶐�����B
        EqtypeTradedProductPK l_eqtypeTradedProductPK = new EqtypeTradedProductPK(l_lngTradedProductId);
        
        // �Q�jQueryProcessor.doUpdateQuery()���R�[������B
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
     * (update�������updq)<BR>
     * <BR>
     * �����e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j�����������updqPK�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�p�����[�^.�������ID<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�L����<BR>
     * <BR>
     * �Q�jQueryProcessor.doUpdateQuery()���R�[������B<BR>
     * <BR>
     * �@@[doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�P�j�ɂč쐬���������������updqPK<BR>
     * �@@�@@arg1�F�@@�p�����[�^.�ύX�f�[�^<BR>
     * @@param l_lngTradedProductId - (�������ID)<BR>
     * ����ID
     * @@param l_strBizDate (�L����)<BR>
     * �L����
     * @@param l_changeMap - (�ύX�f�[�^)<BR>
     * �ύX�f�[�^
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

        // �P�j�����������PK�C���X�^���X�𐶐�����B
        EqtypeTradedProductUpdqPK l_eqtypeTradedProductUpdqPK =
            new EqtypeTradedProductUpdqPK(
                l_lngTradedProductId,
                l_strBizDate);
        
        // �Q�jQueryProcessor.doUpdateQuery()���R�[������B
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
     * (get�~�j���戵�������)<BR>
     * <BR>
     * �~�j������舵���Ă������������擾���A�ԋp����B<BR>
     * <BR>
     * �P�j�ȉ��̏������p�����[�^.��������ꗗ�̗v�f����<BR>
     * �@@�J��Ԃ��B<BR>
     * �@@�P�|�P�j�p�����[�^.��������ꗗ[index].�~�j���戵 == "�~�j���戵"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�p�����[�^.��������ꗗ[index]��ԋp���ďI������B<BR>
     * <BR>
     * �Q�jnull��ԋp����B�@@���~�j���戵����������擾�ł��Ȃ������ׁB<BR>
     * @@param - l_tradedProducts - (��������ꗗ)<BR>
     * �������Params�I�u�W�F�N�g�̔z��
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
	 * (get�~�j���戵�������)<BR>
	 * <BR>
	 * �w������́A�~�j���戵�s��̎�������I�u�W�F�N�g���擾���ԋp����B <BR>
	 * <BR>
	 * �P�j�@@�~�j���s��擾<BR>
	 *  ��������.get�~�j���s��()�ɂāA�s��I�u�W�F�N�g���擾����B <BR>
	 *  �߂�l��null�i==�~�j���戵�Ȃ������j�̏ꍇ�́Anull��ԋp����B <BR>
	 * <BR>
	 * �Q�j�@@��������擾 <BR>
	 *  �v���_�N�g�}�l�[�W��.getTradedProduct()�ɂāA��������I�u�W�F�N�g���擾����B <BR>
	 * <BR>
	 * [getTradedProduct()�Ɏw�肷�����] <BR>
	 * product�F�@@�������� <BR>
	 * market�F�@@�s��<BR>
	 * <BR>
	 * �R�j�@@��������I�u�W�F�N�g��ԋp����B<BR>
	 * <BR>
	 * @@param - l_product - (��������)<BR>
	 * ���������I�u�W�F�N�g
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
     * (get����O��������)<BR>
     * <BR>
     * �y����O���������e�[�u���z���A<BR>
     * �w�肳�ꂽ���������̃��R�[�h���擾����B<BR>
     * �iDB���C�A�E�g�u����O���������e�[�u���d�l.xls�v�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@����O���������e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���@@�����R�[�h = ����.�����R�[�h<BR>
     * �@@���@@�s��R�[�h = ����.�s��R�[�h<BR>
     * �@@���@@��t�I��������YYYYMMDD = ����.��t�I��������YYYYMMDD<BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h��Ԃ��B<BR>
     * �@@�@@�@@���Y�����郌�R�[�h�����݂��Ȃ��ꍇ��null��Ԃ��B<BR>
     * �@@�@@�@@���������R�[�h���݂����ꍇ�́A��O��throw����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h�B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h�B<BR>
     * @@param l_datOrderEndDatetime - (��t�I������)<BR>
     * ��t�I�������B<BR>
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
     * (get�����\����O��������)<BR>
     * <BR>
     * �y����O���������e�[�u���z���A<BR>
     * �w�肳�ꂽ���������̒����\�ȃ��R�[�h���擾����B<BR>
     * �iDB���C�A�E�g�u����O���������e�[�u���d�l.xls�v�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@����O���������e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���@@�����R�[�h = ����.�����R�[�h<BR>
     * �@@���@@�s��R�[�h = ����.�s��R�[�h<BR>
     * �@@���@@��t�J�n���� �� ���ݓ���(*1)<BR>
     * �@@���@@��t�I������ �� ���ݓ���(*1)<BR>
     * �@@���@@�������i != null<BR>
     * �@@���@@�\��������� != null<BR>
     * <BR>
     * �@@(*1)���ݓ����FGtlUtils.getSystemTimestamp( )<BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h��Ԃ��B<BR>
     * <BR>
     * �@@�@@�@@�Y�����郌�R�[�h�����݂��Ȃ��ꍇ��null��Ԃ��B<BR>
     * �@@�@@�@@�������R�[�h���݂���ꍇ�́u����O�����\���R�[�h������s�v�̗�O��throw����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h�B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h�B<BR>
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
     * (is�X�V�폜�\����O��������)<BR>
     * <BR>
     * �w�肳�ꂽ�����������A���ݍX�V�^�폜�\���ǂ����𔻒肵�A<BR>
     * �X�V�^�폜���\�ȏꍇ��true���A�X�V�^�폜���s�ȏꍇ��false��Ԃ��B<BR>
     * <BR>
     * �����̗���O���������I�u�W�F�N�g���ȉ���[�����P]�܂���[�����Q]�ɍ��v����ꍇ�́A<BR>
     * �X�V�^�폜���\�Ɣ��肵true��Ԃ��B<BR>
     * ------------------------------------------------<BR>
     * [�����P]�F��t�J�n�O�ł���<BR>
     * �@@��t�J�n���� �� ���ݓ���(*1)<BR>
     * <BR>
     * [�����Q]�F��t���i�o�^�������̃f�[�^�j<BR>
     * �@@��t�J�n���� �� ���ݓ���(*1)<BR>
     * ���@@��t�I������ �� ���ݓ���(*1)<BR>
     * ���@@�i�������i == null�@@�܂��́@@�\��������� == null�j<BR>
     * <BR>
     * (*1)���ݓ����FGtlUtils.getSystemTimestamp( )<BR>
     * ------------------------------------------------<BR>
     * <BR>
     * ��L��[�����P][�����Q]�̂�����ɂ����v���Ȃ��ꍇ�́A<BR>
     * �X�V�^�폜���s�Ɣ��肵false��Ԃ��B<BR>
     * <BR>
     * @@param l_params - (����O��������)<BR>
     * ����O���������I�u�W�F�N�g�B<BR>
     * �i�y����O���������e�[�u���z�̂P���R�[�h�j<BR>
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
        
        // �����̗���O���������I�u�W�F�N�g���ȉ���[�����P]�܂���[�����Q]�ɍ��v����ꍇ�́A
        // �X�V�^�폜���\�Ɣ��肵true��Ԃ��B
        Timestamp l_tsSysDate = GtlUtils.getSystemTimestamp();
        // [�����P]�F��t�J�n�O�ł���
        // �@@��t�J�n���� �� ���ݓ���(*1)
        if (l_row.getOrderStartDatetime().compareTo(l_tsSysDate) > 0)
        {
            l_isCanUpdateDeleteOffFloorOrderProduct = true;
        }
        // [�����Q]�F��t���i�o�^�������̃f�[�^�j
        // �@@��t�J�n���� �� ���ݓ���(*1)
        // ���@@��t�I������ �� ���ݓ���(*1)
        // ���@@�i�������i == null�@@�܂��́@@�\��������� == null�j
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
     * (get�I�l)<BR>
     * <BR>
     * �y�I�l�e�[�u���z����A <BR>
     * �w�肳�ꂽ����ID�A����ɊY�����郌�R�[�h���擾���Ԃ��B <BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�I�l�e�[�u�����ȉ��̏����Ō�������B <BR>
     * �@@[����] <BR>
     * �@@����ID = ����.����ID <BR>
     * �@@���@@��� = ����.�����YYYYMMDD <BR>
     * <BR>
     * ������.���==null�̏ꍇ�́A�������������O���B<BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h��Ԃ��B <BR>
     * �@@�@@�@@���Y�����郌�R�[�h�����݂��Ȃ��ꍇ��null��Ԃ��B <BR>
     * �@@�@@�@@���Y�����郌�R�[�h�����������݂���ꍇ�́A������ő�l�̃��R�[�h��Ԃ��B<BR>
     * <BR>
     * @@params l_lngProductId  (����ID)<BR>
     * ����ID�B<BR>
     * @@params l_datBaseDate  (���)<BR>
     * ���(�Ɩ����t)�B<BR>
     * @@return LastClosingPriceParams<BR>
     * @@throws WEB3BaseException<BR>
     */ 
    public LastClosingPriceParams getLastClosingPrice(long l_lngProductId, Date l_datBaseDate)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastClosingPrice(long, Date)";
        log.entering(STR_METHOD_NAME);
        
        // �P�jDB����
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
                "DB�A�N�Z�X�Ɏ��s���܂����B");
        }
        
        // �Q�j�@@�擾�������R�[�h��Ԃ��B
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
     * (get�I�l)<BR>
     * <BR>
     * �y�I�l�e�[�u���z����A <BR>
     * �w�肳�ꂽ����ID�A�s��R�[�h�A����ɊY������l���擾���Ԃ��B <BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�I�[�o�[���[�h���\�b�h���R�[������B <BR>
     * <BR>
     * �@@this.get�I�l(�����̖���ID, �����̊��) <BR>
     * <BR>
     * �@@�Y���f�[�^�Ȃ�����0��Ԃ��B <BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h�́A�Y������s�ꍀ�ڂ̒P���ݒ�l(*)��Ԃ��B <BR>
     * <BR>
     * �@@(*)�Y������s�ꍀ�� <BR>
     * �@@�s��R�[�h==�����F�@@�I�l.���؏I�l <BR>
     * �@@�s��R�[�h==���F�@@�I�l.��؏I�l <BR>
     * �@@�s��R�[�h==���É��F�@@�I�l.���؏I�l <BR>
     * �@@�s��R�[�h����L�ȊO�F�@@�I�l.���̑��s��I�l<BR>
     * <BR>
     * @@params l_lngProductId  (����ID)<BR>
     * ����ID�B<BR>
     * @@params l_strMarketCode  (�s��R�[�h)<BR>
     * �s��R�[�h�B<BR>
     * @@params l_datBaseDate  (���)<BR>
     * ���(�Ɩ����t)�B<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getLastClosingPrice(long l_lngProductId, String l_strMarketCode, Date l_datBaseDate)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLastClosingPrice(long, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        // this.get�I�l()
        LastClosingPriceParams l_lastClosingPriceParams =
            this.getLastClosingPrice(l_lngProductId, l_datBaseDate);
        
        if (l_lastClosingPriceParams == null)
        {
            return 0D;
        }
        
        // �擾�������R�[�h�́A�Y������s�ꍀ�ڂ̒P���ݒ�l��Ԃ��B
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
     * (get�\���p�������)<BR>
     * <BR>
     * ���͉�ʂɕ\�����鎞�������擾����B <BR>
     * <BR>
     * �P�j�@@�����T�[�r�X�iWEB3DefaultQuoteDataSupplierService�j���A <BR>
     * �@@�@@�@@�ڋq�����ɉ������������iWEB3EqTypeQuoteData�j���擾����B <BR>
     * <BR>
     * �@@�@@�@@���A���q�i�����̕⏕�����̌ڋq.is���A���ڋq( )==true�j�̏ꍇ�F <BR>
     * �@@�@@�@@�@@WEB3DefaultQuoteDataSupplierService.getEqTypeQuote(�����̎������, "���A��"); <BR>
     * <BR>
     * �@@�@@�@@�f�B���C�q�i�����̕⏕�����̌ڋq.is���A���ڋq( )==false�j�̏ꍇ�F <BR>
     * �@@�@@�@@�@@WEB3DefaultQuoteDataSupplierService.getEqTypeQuote(�����̎������, "20���f�B���C"); <BR>
     * <BR>
     * �Q�j�@@�擾�������������ȉ��̗D�揇�ʂŕ]�����A <BR>
     * �@@�@@�@@�l���t���Ă���i==0�łȂ��j�P���������Ƃ��āA�߂�l�I�u�W�F�N�g.���� �ɃZ�b�g����B <BR>
     * <BR>
     * �@@�@@�P�D���ݒl�iWEB3EqTypeQuoteData.getCurrentPrice( )�j <BR>
     * �@@�@@�Q�D���C�z�l�iWEB3EqTypeQuoteData.getBidPrice( )�j <BR>
     * �@@�@@�R�D���C�z�l�iWEB3EqTypeQuoteData.getAskPrice( )�j <BR>
     * �@@�@@�S�D�������.��l�i�I�l�j�i�����̎������.getLastClosingPrice( )�j <BR>
     * <BR>
     * �@@�@@�܂��A�����Ƃ����P���ɊY�����鎞���敪�i���ݒl�^���C�z�l�^���C�z�l�^�O���I�l�j���A <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.�����敪 �ɃZ�b�g����B <BR>
     * <BR>
     * �R�j�@@�߂�l�I�u�W�F�N�g.�O����ɁAWEB3EqTypeQuoteData.getChange( )���Z�b�g����B <BR>
     * <BR>
     * �S�j�@@�߂�l�I�u�W�F�N�g.�������\���ԂɁA�Q�j�Ŏ����Ƃ����P���ɑΉ����鎞�����\���Ԃ� <BR>
     * �@@�@@�@@�Z�b�g����B <BR>
     * <BR>
     * �@@�@@�@@����F�@@���ݒl�������Ƃ���ꍇ�AWEB3EqTypeQuoteData.getCurrentPriceTime( )���Z�b�g����B <BR>
     * �@@�@@�@@��null���Ԃ��ꂽ�ꍇ�Anull�����̂܂܃Z�b�g����B<BR>
     * <BR>
     * �T�j�@@�߂�l�I�u�W�F�N�g.�����擾�敪�ɁA"����"���Z�b�g����B <BR>
     * <BR>
     * �U�j�@@�߂�l�I�u�W�F�N�g.�s��R�[�h�ɁA�����̎������.�s��ID�ɊY������s��.�s��R�[�h���Z�b�g����B <BR>
     * <BR>
     * �V�j�@@�ȉ��A�P�j�Ŏ擾�ς̎�����񂩂�A���̃Z�b�g���s���B <BR>
     * <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���ݒl�ɁAWEB3EqTypeQuoteData.getCurrentPrice( )���Z�b�g����B(*1) <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���ݒl�����ɁAWEB3EqTypeQuoteData.getCurrentPriceTime( )���Z�b�g����B <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���ݒl�敪�ɁAWEB3EqTypeQuoteData.getCurrentPriceFlag( )���Z�b�g����B(*3) <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���ݒl�O����ɁAWEB3EqTypeQuoteData.getChange( )���Z�b�g����B(*2) <BR>
     * <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.�o�����ɁAWEB3EqTypeQuoteData.getVolume( )���Z�b�g����B(*1) <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.�o���������ɁAWEB3EqTypeQuoteData.getVolumeTime( )���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���C�z�l�^�C�g���敪�ɁAWEB3EqTypeQuoteData.getAskPriceTitle( )���Z�b�g����B(*4) <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���C�z�l�ɁAWEB3EqTypeQuoteData.getAskPrice( )���Z�b�g����B(*1) <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���C�z�l�����ɁAWEB3EqTypeQuoteData.getAskPriceTime( )���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���C�z�l�^�C�g���敪�ɁAWEB3EqTypeQuoteData.getBidPriceTitle( )���Z�b�g����B(*5) <BR> 
     * �@@�@@�߂�l�I�u�W�F�N�g.���C�z�l�ɁAWEB3EqTypeQuoteData.getBidPrice( )���Z�b�g����B(*1) <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.���C�z�l�����ɁAWEB3EqTypeQuoteData.getBidPriceTime( )���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g.��l�i�ɁAWEB3EqTypeQuoteData.getBasePrice( )���Z�b�g����B(*1) <BR>
     * <BR>
     * �W�j�@@�쐬�����߂�l�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * (*1)0�̏ꍇ�́Anull���Z�b�g����B<BR> 
     * (*2)Double.NaN�̏ꍇ�́Anull���Z�b�g����B<BR> 
     * (*3)WEB3EqTypeQuoteData.getCurrentPrice( )==0�̏ꍇ�́Anull���Z�b�g����B<BR> 
     * (*4)WEB3EqTypeQuoteData.getAskPrice( )==0�̏ꍇ�́Anull���Z�b�g����B<BR>
     * (*5)WEB3EqTypeQuoteData.getBidPrice( )==0�̏ꍇ�́Anull���Z�b�g����B<BR>
     * @@params l_eqtypeTradedProduct  (�������)<BR>
     * ��������I�u�W�F�N�g<BR>
     * @@params l_subAccount  (�⏕����)<BR>
     * �⏕�����B
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
        
        //�P�j�@@�����T�[�r�X�iWEB3DefaultQuoteDataSupplierService�j���A
        //     �ڋq�����ɉ������������iWEB3EqTypeQuoteData�j���擾����B
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
                    "���������擾�T�[�r�X���擾�o���܂���ł����B���A���敪�F[" + RealType.REAL + "]");
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
                    "���������擾�T�[�r�X���擾�o���܂���ł����B���A���敪�F[" + RealType.DELAY + "]");
            }
        }
        
        // �߂�l�I�u�W�F�N�g�̐���
        WEB3EquityProductQuote l_equityProductQuote = new WEB3EquityProductQuote();

        //�Q�j�@@�擾�������������ȉ��̗D�揇�ʂŕ]�����A 
        // �@@�@@�l���t���Ă���i==0�łȂ��j�P���������Ƃ��āA�߂�l�I�u�W�F�N�g.���� �ɃZ�b�g����B
        // �@@�@@�P�D���ݒl�iWEB3EqTypeQuoteData.getCurrentPrice( )�j <BR>
        // �@@�@@�Q�D���C�z�l�iWEB3EqTypeQuoteData.getBidPrice( )�j <BR>
        // �@@�@@�R�D���C�z�l�iWEB3EqTypeQuoteData.getAskPrice( )�j <BR>
        // �@@�@@�S�D�������.��l�i�I�l�j�i�����̎������.getLastClosingPrice( )�j
        l_equityProductQuote.setQuote(l_eqtypeQuoteData.getCurrentPrice());
        l_equityProductQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.CURRENT_PRICE);
        
        //�S�j�@@�߂�l�I�u�W�F�N�g.�������\���ԂɁA�Q�j�Ŏ����Ƃ����P���ɑΉ����鎞�����\���Ԃ� 
        // �@@�@@�Z�b�g����B 
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
        
        //�R�j�@@�߂�l�I�u�W�F�N�g.�O����ɁAWEB3EqTypeQuoteData.getChange( )���Z�b�g����B
        l_equityProductQuote.setComparedPreviousDay(l_eqtypeQuoteData.getChange());
        
        //�T�j�@@�߂�l�I�u�W�F�N�g.�����擾�敪�ɁA"����"���Z�b�g����B
        l_equityProductQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
        
        //�U�j�@@�߂�l�I�u�W�F�N�g.�s��R�[�h�ɁA�����̎������.�s��ID�ɊY������s��.�s��R�[�h���Z�b�g����B
        l_equityProductQuote.setMarketCode(l_eqtypeTradedProduct.getMarket().getMarketCode());
        
        //�V�j�@@�ȉ��A�P�j�Ŏ擾�ς̎�����񂩂�A���̃Z�b�g���s���B 
        //�߂�l�I�u�W�F�N�g.���ݒl�ɁAWEB3EqTypeQuoteData.getCurrentPrice( )���Z�b�g����B(*1) 
        if (l_eqtypeQuoteData.getCurrentPrice() == 0.0D)
        {
            l_equityProductQuote.setBoardCurrentPrice(null);
        }
        else
        {
            l_equityProductQuote.setBoardCurrentPrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getCurrentPrice()));
        }
        
        //�߂�l�I�u�W�F�N�g.���ݒl�����ɁAWEB3EqTypeQuoteData.getCurrentPriceTime( )���Z�b�g����B 
        l_equityProductQuote.setBoardCurrentPriceTime(
            l_eqtypeQuoteData.getCurrentPriceTime());
        
        //�߂�l�I�u�W�F�N�g.���ݒl�敪�ɁAWEB3EqTypeQuoteData.getCurrentPriceFlag( )���Z�b�g����B
        //WEB3EqTypeQuoteData.getCurrentPrice()==0�̏ꍇ�́Anull���Z�b�g�B
        if (l_eqtypeQuoteData.getCurrentPrice() == 0.0D)
        {
            l_equityProductQuote.setBoardCurrentPriceDiv(null);
        }
        else
        {
            String l_strCurrentPriceFlag = l_eqtypeQuoteData.getCurrentPriceFlag().intValue() + "";
            l_equityProductQuote.setBoardCurrentPriceDiv(l_strCurrentPriceFlag);
        }
        
        //�߂�l�I�u�W�F�N�g.���ݒl�O����ɁAWEB3EqTypeQuoteData.getChange( )���Z�b�g����B(*2)         
        if (Double.isNaN(l_eqtypeQuoteData.getChange()))
        {
            l_equityProductQuote.setBoardChange(null);
        }
        else
        {
            l_equityProductQuote.setBoardChange(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getChange()));
        }
        
        //�߂�l�I�u�W�F�N�g.�o�����ɁAWEB3EqTypeQuoteData.getVolume( )���Z�b�g����B(*1)        
        if (l_eqtypeQuoteData.getVolume() == 0.0D)
        {
            l_equityProductQuote.setVolume(null);
        }
        else
        {
            l_equityProductQuote.setVolume(WEB3StringTypeUtility.formatNumber(
                l_eqtypeQuoteData.getVolume()));
        }
        
        //�߂�l�I�u�W�F�N�g.�o���������ɁAWEB3EqTypeQuoteData.getVolumeTime( )���Z�b�g����B 
        l_equityProductQuote.setVolumeTime(l_eqtypeQuoteData.getVolumeTime());
        
        //�߂�l�I�u�W�F�N�g.���C�z�l�^�C�g���敪�ɁAWEB3EqTypeQuoteData.getAskPriceTitle( )���Z�b�g����B
        //WEB3EqTypeQuoteData.getAskPrice()==0�̏ꍇ�́Anull���Z�b�g
        if (l_eqtypeQuoteData.getAskPrice()== 0.0D)
        {
            l_equityProductQuote.setAskPriceTitle(null);  
        }
        else
        {
            String l_strAskPriceTitle = l_eqtypeQuoteData.getAskPriceTitle().intValue() + "";
            l_equityProductQuote.setAskPriceTitle(l_strAskPriceTitle); 
        }
        
        //�߂�l�I�u�W�F�N�g.���C�z�l�ɁAWEB3EqTypeQuoteData.getAskPrice( )���Z�b�g����B(*1) 
        if (l_eqtypeQuoteData.getAskPrice() == 0.0D)
        {
            l_equityProductQuote.setAskPrice(null);
        } 
        else
        {
            l_equityProductQuote.setAskPrice(
                WEB3StringTypeUtility.formatNumber(l_eqtypeQuoteData.getAskPrice()));
        }
        
        //�߂�l�I�u�W�F�N�g.���C�z�l�����ɁAWEB3EqTypeQuoteData.getAskPriceTime( )���Z�b�g����B 
        l_equityProductQuote.setAskPriceTime(l_eqtypeQuoteData.getAskPriceTime());
        
        //�߂�l�I�u�W�F�N�g.���C�z�l�^�C�g���敪�ɁAWEB3EqTypeQuoteData.getBidPriceTitle( )���Z�b�g����B
        //WEB3EqTypeQuoteData.getBidPrice()==0�̏ꍇ�́Anull���Z�b�g
        if (l_eqtypeQuoteData.getBidPrice() == 0.0D)
        {
            l_equityProductQuote.setBidPriceTitle(null);
        }
        else
        {
            String l_strBidPriceTitle = l_eqtypeQuoteData.getBidPriceTitle().intValue() + "";
            l_equityProductQuote.setBidPriceTitle(l_strBidPriceTitle);
        }
        
        //�߂�l�I�u�W�F�N�g.���C�z�l�ɁAWEB3EqTypeQuoteData.getBidPrice( )���Z�b�g����B(*1) 
        if (l_eqtypeQuoteData.getBidPrice() == 0.0D)
        {
            l_equityProductQuote.setBidPrice(null);
        } 
        else
        {
            l_equityProductQuote.setBidPrice(WEB3StringTypeUtility.formatNumber(
                l_eqtypeQuoteData.getBidPrice()));
        }
        
        //�߂�l�I�u�W�F�N�g.���C�z�l�����ɁAWEB3EqTypeQuoteData.getBidPriceTime( )���Z�b�g����B 
        l_equityProductQuote.setBidPriceTime(l_eqtypeQuoteData.getBidPriceTime());
        
        //�߂�l�I�u�W�F�N�g.��l�i�ɁAWEB3EqTypeQuoteData.getBasePrice( )���Z�b�g����B(*1)    
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
        //�W�j�@@�쐬�����߂�l�I�u�W�F�N�g��Ԃ��B
        return l_equityProductQuote;
    }
    
    /**
     * (get����From��W����)<BR>
     * <BR>
     * �����̕�W������芔���������擾����B <BR>
     * <BR>
     * �P�j�����R�[�h�ϊ� <BR>
     * �@@�p�����[�^.��W����.�����R�[�h��5byte�ڂ�0�ɒu������B <BR>
     * <BR>
     * �Q�j���������擾 <BR>
     * �@@this.getProduct()���\�b�h���R�[�����A�����������擾����B <BR>
     * <BR>
     * �@@[getProduct()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�،���ЁF�@@�p�����[�^.��W����.getInstitution()�̖߂�l <BR>
     * �@@�@@�����R�[�h�F�@@�P�j�ɂĕϊ����������R�[�h <BR>
     * <BR>
     * �R�j�Q�j�ɂĎ擾������������������ԋp����B <BR>
     * <BR>
     * @@params l_subscriptionProduct ��W����<BR>
     * ��W�����̊��������I�u�W�F�N�g<BR>
     * <BR>
     * @@return EqTypeProduct<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3EquityProduct getProductFromSubscriptionProduct(WEB3EquityProduct l_subscriptionProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductFromSubscription(EqTypeProduct)";
        log.entering(STR_METHOD_NAME);
        
        // �����R�[�h�ϊ�
        String l_strChangedProductCode =
            l_subscriptionProduct.getProductCode().substring(0, 4) + "0";
        
        // ���������擾
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
                "�����R�[�h:[" + l_strChangedProductCode + "]�ɊY�����銔���������擾�ł��܂���B",
                l_ex);
        }
        
        return l_newEqtypeProduct;
    }
    
    /**
     * (get�������)<BR>
     * <BR>
     * �w����e�ɉ��������������擾����B�擾���͈ȉ��̒ʂ�B<BR>
     * -----------------------------------------------------------------<BR>
     * �ꒆ�F�@@�@@�����T�[�o��������������e�[�u��<BR>
     * ������or�I�l�e�[�u��������read�w�莞�F<BR>
     * �@@�@@�@@�@@�@@�@@�I�l�e�[�u���������T�[�o��������������e�[�u��<BR>
     * -----------------------------------------------------------------<BR>
     * <BR>
     * �P�j�@@������is�I�l�e�[�u��������read==true�A�܂���<BR>
     * �@@�@@ ������i������ԊǗ�.is�s��J�ǎ��ԑ�( )==false�j�̏ꍇ�̂݁A�����I�l���擾����B<BR>
     * �@@�@@�@@�ithis.get�I�l(����ID, �s��R�[�h, ���)�Ŏ擾����j<BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@��get�I�l( )�F�����ݒ�d�l�� <BR>
     * <BR>
     * �@@�@@�@@����ID�F�@@�����̎������.����ID <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̎������.�s��ID�ɊY������s��R�[�h <BR>
     * �@@�@@�@@����F�@@null�i�w��Ȃ��j<BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �@@�@@�擾�����l > 0�i�I�l����j�̏ꍇ <BR>
     * �@@�@@this.get�������(���������A�s��A���)�ɂ�����������擾����B <BR>
     * <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * �@@�@@�@@��get��������i�j�F�����ݒ聄 <BR>
     * <BR>
     * �@@�@@�@@���������F�@@�����̎������.getProduct()�̖߂�l�����������ɃL���X�g <BR>
     * �@@�@@�@@�s��F�@@�����̎������.getMarket()�̖߂�l <BR>
     * �@@�@@�@@����F�@@������ԊǗ�.get������( )�Ŏ擾�����������̑O�c�Ɠ� <BR>
     * �@@�@@�@@---------------------------------------------------------- <BR>
     * <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g�̃v���p�e�B���ȉ��̒ʂ�ɐݒ肵�A�ԋp����B 
     * �@@�@@------------------------------------------------------------------ <BR>
     * �@@�@@���߂�l�v���p�e�B�ݒ聄 <BR>
     * <BR>
     * �@@�@@���������������.���� = �擾�����l <BR>
     * �@@�@@���������������.�O���� = �擾���������I�l - get��������̖߂�l.��l�i�I�l�j<BR>
     * �@@�@@�@@����L�A��l(�I�l) == 0 �̏ꍇ��Double.NaN���Z�b�g�B<BR>
     * �@@�@@���������������.�������\���� = null <BR>
     * �@@�@@���������������.�����擾�敪 = "�����I�l" <BR>
     * �@@�@@���������������.�����敪 = "���ݒl" <BR>
     * �@@�@@���������������.�s��R�[�h = �����̎������.�s��ID�ɊY������s��R�[�h <BR>
     * �@@�@@------------------------------------------------------------------ <BR>
     * �@@�@@��L�ȊO�̏ꍇ�́A�ȍ~�̏������s���B <BR>
     * <BR>
     * �Q�j�@@�����T�[�o���玞�����擾����B <BR>
     * <BR>
     * �Q�|�P�j�@@�����T�[�r�X�iWEB3QuoteDataSupplierService�j���A <BR>
     * �@@�@@�@@������RealType�ɉ������������iEqTypeQuoteData�j���擾����B <BR>
     * <BR>
     * �@@�@@�@@���A���w��i������RealType == "���A��"�j�̏ꍇ�F <BR>
     * �@@�@@�@@�@@WEB3QuoteDataSupplierService.getEqTypeQuote(�����̎������, "���A��"); <BR>
     * <BR>
     * �@@�@@�@@�f�B���C�w��i������RealType != "���A��"�j�̏ꍇ�F <BR>
     * �@@�@@�@@�@@WEB3QuoteDataSupplierService.getEqTypeQuote(�����̎������, "20���f�B���C"); <BR>
     * <BR>
     * �Q�|�Q�j�@@�擾�������������ȉ��̗D�揇�ʂŕ]�����A <BR>
     * �@@�@@�@@�l���t���Ă���i==0�łȂ��j�P��������ꍇ�́A�߂�l�I�u�W�F�N�g�̃v���p�e�B���ȉ��̒ʂ�ɐݒ肵�A <BR>
     * �@@�@@�ԋp����B <BR>
     * <BR>
     * �@@�@@------------------------------------------------------------------ <BR>
     * �@@�@@���]�����ʁ� <BR>
     * <BR>
     * �@@�@@�P�D���ݒl�iEqTypeQuoteData.getCurrentPrice( )�j <BR>
     * �@@�@@�Q�D���C�z�l�iEqTypeQuoteData.getBidPrice( )�j <BR>
     * �@@�@@�R�D���C�z�l�iEqTypeQuoteData.getAskPrice( )�j <BR>
     * �@@�@@------------------------------------------------------------------ <BR>
     * �@@�@@���߂�l�v���p�e�B�ݒ聄 <BR>
     * <BR>
     * �@@�@@���������������.���� = �l���t���Ă���l�̂����A�]�����ʂ��ł��������� <BR>
     * �@@�@@���������������.�O���� = EqTypeQuoteData.getChange( ) <BR>
     * �@@�@@���������������.�������\���� = �����Ƃ����P���ɑΉ����鎞�����\���� <BR>
     * �@@�@@�@@����F�@@���ݒl�������Ƃ���ꍇ�AEqTypeQuoteData.getCurrentPriceTime( )���Z�b�g����B <BR>
     * �@@�@@�@@��null���Ԃ��ꂽ�ꍇ�Anull�����̂܂܃Z�b�g����B <BR>
     * �@@�@@���������������.�����擾�敪 = "����" <BR>
     * �@@�@@���������������.�����敪 = �����Ƃ����P���ɊY�����鎞���敪 <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���ݒl�^���C�z�l�^���C�z�l�j <BR>
     * �@@�@@���������������.�s��R�[�h = �����̎������.�s��ID�ɊY������s��R�[�h <BR>
     * �@@�@@------------------------------------------------------------------ <BR>
     * �@@�@@��L�ȊO�̏ꍇ�́A�ȍ~�̏������s���B <BR>
     * <BR>
     * �R�j�@@�����������A�O���I�l���擾����B <BR>
     * <BR>
     * �@@�@@�߂�l�I�u�W�F�N�g�̃v���p�e�B���ȉ��̒ʂ�ɐݒ肵�A�ԋp����B <BR>
     * �@@�@@------------------------------------------------------------------ <BR>
     * �@@�@@���߂�l�v���p�e�B�ݒ聄 <BR>
     * <BR>
     * �@@�@@���������������.���� = �����̎������.getLastClosingPrice( )�i�������.��l�i�I�l�j�j <BR>
     * �@@�@@���������������.�O���� = Double.NaN<BR>
     * �@@�@@���������������.�������\���� = null <BR>
     * �@@�@@���������������.�����擾�敪 = "�O���I�l" <BR>
     * �@@�@@���������������.�����敪 = "�O���I�l" <BR>
     * �@@�@@���������������.�s��R�[�h = �����̎������.�s��ID�ɊY������s��R�[�h <BR>
     * �@@�@@------------------------------------------------------------------ <BR>
     * <BR>
     * @@param l_eqtypeTradedProduct<BR>
     * (�������)<BR>
     * @@param l_realType<BR>
     * (RealType)<BR>
     * @@param l_isLastClosingPriceUnconditionalyRead - (is�I�l�e�[�u��������read)<BR>
     * �I�l�e�[�u���𖳏�����read���邩�ǂ����̃t���O
     * @@return WEB3EquityProduct<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3EquityProductQuote getProductQuote(EqTypeTradedProduct l_eqtypeTradedProduct, RealType l_realType, boolean l_isLastClosingPriceUnconditionalyRead)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductQuote(EqtypeTradedProduct, RealType, boolean)";
        log.entering(STR_METHOD_NAME);
        
        String l_strMarketCode = l_eqtypeTradedProduct.getMarket().getMarketCode();
        
        // �������(������ԊǗ�.is�s��J�ǎ��ԑ�() == false)�̏ꍇ�A�����I�l���擾����B
        if (l_isLastClosingPriceUnconditionalyRead ||
            WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() == false)
        {
            double l_dblTodayLastClosingPrice =
                this.getLastClosingPrice(l_eqtypeTradedProduct.getProduct().getProductId(),
                    l_strMarketCode,
                    null);
            
            // �I�l����̏ꍇ
            if (l_dblTodayLastClosingPrice > 0)
            {
                // ���������擾
                Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
                WEB3GentradeBizDate l_genBizDate =
                    new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
                //�擾�����l > 0�i�I�l����j�̏ꍇ
                //�@@�@@this.get�������(���������A�s��A���)�ɂ�����������擾����B
                EqtypeTradedProductParams l_tradeProduct = this.getTradedProduct(
                    (WEB3EquityProduct)l_eqtypeTradedProduct.getProduct(),
                    l_eqtypeTradedProduct.getMarket(),
                    l_genBizDate.roll(-1));

                // �߂�l�I�u�W�F�N�g����
                WEB3EquityProductQuote l_productQuote = new WEB3EquityProductQuote();
                
                l_productQuote.setQuote(l_dblTodayLastClosingPrice);
                // �O����
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
        
        // �����T�[�o���玞�����擾
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
                "�،���ЃR�[�h�F[" + l_eqtypeTradedProduct.getInstitution().getInstitutionCode()
                + "] ����ID�F[" + l_eqtypeTradedProduct.getProduct().getProductId()
                + "] �s��R�[�h�F[" + l_strMarketCode
                + "�ɑΉ����鎞����񂪌�����܂���B",
                l_exp);
        }
        
        // 1.5. ���ݒl�A���C�z�l�A���C�z�l�̏����ŉ��i�����擾����
        //     �i�l���t���Ă���i�擾�����l > 0�j�̎��_�ŁA�ȍ~�̉��i���͎擾���Ȃ��j�B
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
        // 1.6. ���ݒl�A���C�z�l�A���C�z�l�̂����ꂩ���擾�ł����ꍇ�i�擾�����l > 0�̏ꍇ�j
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
            // 1.8. �O���I�l���擾�ł����ꍇ�i�擾�����l > 0�̏ꍇ�j
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
     * (get����)<BR>
     * �������擾����B�擾���͈ȉ��̒ʂ�B<BR>
     * -----------------------------------------------<BR>
     * �ꒆ�F�@@�@@�����T�[�o��������������e�[�u��<BR>
     * ������F�@@�I�l�e�[�u���������T�[�o��������������e�[�u��<BR>
     * -----------------------------------------------<BR>
     * <BR>
     * �P�j�@@this.get����(�������. boolean)��delegate����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get����( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@��������F�@@�����̎������<BR>
     * �@@�@@�@@is�I�l�e�[�u��������read�F�@@false�iread�͏����t���j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g
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
     * (get�������)<BR>
     * �w����e�ɉ��������������擾����B�擾���͈ȉ��̒ʂ�B<BR>
     * -----------------------------------------------<BR>
     * �ꒆ�F�@@�@@�����T�[�o��������������e�[�u��<BR>
     * ������F�@@�I�l�e�[�u���������T�[�o��������������e�[�u��<BR>
     * -----------------------------------------------<BR>
     * <BR>
     * �P�j�@@this.get�������(�������, RealType, boolean)��delegate����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�������( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@��������F�@@�����̎������<BR>
     * �@@�@@�@@RealType�F�@@������RealType<BR>
     * �@@�@@�@@is�I�l�e�[�u��������read�F�@@false�iread�͏����t���j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g
     * @@param l_realType - (RealType)<BR>
     * ���A���^�f�B���C�敪
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
     * (is���S�ۋK���Ώۖ���)<BR>
     * �w����������S�ۋK���Ώۂł��邩�ǂ����𔻒肷��B<BR>
     * �|�w���is�����i�����敪�j�ɊY������K���L����ԋp����B<BR>
     * �@@���敪�w��Ȃ����́A�����^�����̂����ꂩ�ŋK�����������Ă���ꍇ�A�K������Ɣ��肷��B<BR>
     * �|��M�p�����̏ꍇ���A�ۏ؋����ݒ�l���g�p���ꗥ�Ŕ��肷��B<BR>
     * <BR>
     * �P�j�@@�ۏ؋���(*1)�A�����ۏ؋���(*2)�̕��X�ݒ�l���擾����B<BR>
     * <BR>
     * �@@�@@�����̕⏕����.get����X()�ŁA���X�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�ȉ��A���X�I�u�W�F�N�g�̓����v���p�e�B���g�p����B<BR>
     * <BR>
     * �Q�j�@@�w���is�����i�����^�����j�ɑ΂����S�ۋK�����K�p����Ă��邩�ǂ������`�F�b�N���A<BR>
     * �@@�@@�@@�`�F�b�N���ʂ�ԋp����B<BR>
     * <BR>
     * �Q�|�P�j�@@������is����==true�i�����j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�@@�@@�|�����̎������.���ۏ؋��� > �ۏ؋���(*1)<BR>
     * �@@�@@�@@�@@�|�����̎������.�������ۏ؋��� > �����ۏ؋���(*2)<BR>
     * <BR>
     * �@@�@@�@@��L�̂�����ɂ��Y�����Ȃ��ꍇ�́Afalse��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�j�@@������is����==false�i�����j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�@@�@@�|�����̎������.���ۏ؋��� > �ۏ؋���(*1)<BR>
     * �@@�@@�@@�@@�|�����̎������.�������ۏ؋��� > �����ۏ؋���(*2)<BR>
     * <BR>
     * �@@�@@�@@��L�̂�����ɂ��Y�����Ȃ��ꍇ�́Afalse��ԋp����B<BR>
     * <BR>
     * �Q�|�R�j�@@������is����==null�i���敪���w��j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̂����ꂩ�ɊY������ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�@@�@@�|�����̎������.���ۏ؋��� > �ۏ؋���(*1)<BR>
     * �@@�@@�@@�@@�|�����̎������.�������ۏ؋��� > �����ۏ؋���(*2)<BR>
     * �@@�@@�@@�@@�|�����̎������.���ۏ؋��� > �ۏ؋���(*1)<BR>
     * �@@�@@�@@�@@�|�����̎������.�������ۏ؋��� > �����ۏ؋���(*2)<BR>
     * <BR>
     * �@@�@@�@@��L�̂�����ɂ��Y�����Ȃ��ꍇ�́Afalse��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_tradedProduct - (�������)<BR>
     * ������������I�u�W�F�N�g�B
     * @@param isBuyContract - (is����)<BR>
     * �����t���O�B<BR>
     * �itrue�F�����Afalse�F�����Anull�F���敪������j
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
     * (get�������)<BR>
     * �����̏����ɊY������������Row���擾���A��������C���X�^���X��ԋp����B <BR>
     * <BR>
     * �P�j�@@DB����  <BR>
     * �@@������������e�[�u�����ȉ��̏����Ō�������B  <BR>
     * �@@[����]  <BR>
     * �@@����ID = ����.��������.����ID ���� <BR>
     * �@@�s��ID = ����.�s��.�s��ID ���� <BR>
     * �@@�L���� = ����.����@@�i�t�H�[�}�b�g�FYYYYMMDD�j <BR>
     * <BR>
     * �@@��������������e�[�u���̌������ʂ��擾�ł��Ȃ������ꍇ�A <BR>
     * �@@�@@�������Ŋ����������updq�e�[�u������������B <BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h��ԋp����B<BR>
     * @@param l_equityProduct - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
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
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�@@[����]
        //�@@����ID = ����.��������.����ID ����
        //�@@�s��ID = ����.�s��.�s��ID ����
        //�@@�L���� = ����.����@@�i�t�H�[�}�b�g�FYYYYMMDD�j
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
            //������������e�[�u�����ȉ��̏����Ō���
            l_lisRows = l_processor.doFindAllQuery(
                EqtypeTradedProductRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);

            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                //������������e�[�u���̌������ʂ��擾�ł��Ȃ������ꍇ
                //�������Ŋ����������updq�e�[�u������������
                l_lisRows = l_processor.doFindAllQuery(
                    EqtypeTradedProductUpdqRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
                if (l_lisRows == null || l_lisRows.size() == 0)
                {
                    log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�e�[�u���ɊY������f�[�^������܂���B");
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

            //�擾�������R�[�h��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_eqtypeTradedProductParams;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (is������l��M)<BR>
     * <BR>
     * ������l��M�������ǂ����̃`�F�b�N���s���B <BR>
     * <BR>
     * �ȉ��̏����ŁA�v���Z�X�Ǘ��e�[�u������������B<BR>
     * �e�[�u������ꌏ���͕������̃��R�[�h���擾�ł����ꍇ�Atrue��ԋp����B<BR>
     * �擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * [����]<BR>
     * �v���Z�X�h�c = "0011�F������l�����M" <BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * <BR>
     * @@params l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
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

        //�擾�ł��Ȃ������ꍇ
        if (l_lisRows.size() < 1)
        {
            log.entering(STR_METHOD_NAME);
            return false;
        }
        //�e�[�u������ꌏ���͕������̃��R�[�h���擾�ł����ꍇ�Atrue��ԋp����B
        else
        {
            log.entering(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (is�������l��M����)<BR>
     * ������l��M�������ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �ȉ��̏����ŁA�v���Z�X�Ǘ��e�[�u������������B<BR>
     * �e�[�u������ꌏ���͕������̃��R�[�h���擾�ł����ꍇ�Atrue��ԋp����B<BR>
     * �擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * [����]<BR>
     * �v���Z�X�h�c = "0012�F�����I�l�����M"<BR>
     * �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * @@params l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
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

        //�擾�ł��Ȃ������ꍇ
        if (l_lisRows.size() < 1)
        {
            log.entering(STR_METHOD_NAME);
            return false;
        }
        //�e�[�u������ꌏ���͕������̃��R�[�h���擾�ł����ꍇ�Atrue��ԋp����B
        else
        {
            log.entering(STR_METHOD_NAME);
            return true;
        }
    }
}
@
