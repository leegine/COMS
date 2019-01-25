// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseProductManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            GtlQueryUtils, GtlDbUtils

public abstract class BaseProductManagerImpl
    implements ProductManager
{

    public BaseProductManagerImpl()
    {
    }

    public Product getProduct(long productId)
        throws NotFoundException
    {
        ProductRow productRow = ProductDao.findRowByPk(productId);
        return toProduct(productRow);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No product found with id : " + productId);
        DataException dne;
        dne;
        m_log.error(dne.getMessage(), dne);
        throw new RuntimeSystemException("System exception while getting product with id : " + productId);
    }

    public List searchProductsBeginningWith(Institution inst, ProductTypeEnum productType, String namePrefix, PaginationQueryParamsSpec pageSpec, SortKeySpec sortSpec)
    {
        if(namePrefix == null)
            throw new IllegalArgumentException("namePrefix should not be null");
        List rows;
        String where = "institution_code = ? and (product_type = ?) and ( (standard_name like ?) or(name_alt1 like ?) or(name_alt2 like ?) )";
        String prefixedSearchString = namePrefix + "%";
        Object bindVars[] = {
            inst.getInstitutionCode(), new Long(productType.intValue()), prefixedSearchString, prefixedSearchString, prefixedSearchString
        };
        String orderBy = sortSpec != null && !sortSpec.isSortKeyNull() ? sortSpec.getSortKeySpec() : null;
        int pageSize = pageSpec.getPageSize();
        int pageNumber = pageSpec.getPageNumber();
        rows = Processors.getDefaultProcessor().doFindAllQuery(ProductRow.TYPE, "institution_code = ? and (product_type = ?) and ( (standard_name like ?) or(name_alt1 like ?) or(name_alt2 like ?) )", orderBy, null, bindVars, pageSize, pageNumber);
        return toProductList(rows);
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException("System exception while searching product with namePrefix :" + namePrefix, de);
    }

    public List searchProducts(Institution inst, ProductTypeEnum productType, String nameSubString, PaginationQueryParamsSpec pageSpec, SortKeySpec sortSpec)
    {
        if(nameSubString == null)
            throw new IllegalArgumentException("nameSubString should not be null");
        List rows;
        String where = "institution_code = ? and (product_type = ?) and ( (standard_name like ?) or(name_alt1 like ?) or(name_alt2 like ?) ) ";
        Object bindVars[] = {
            inst.getInstitutionCode(), new Long(productType.intValue()), GtlQueryUtils.addSubStringSearchModifier(nameSubString), GtlQueryUtils.addSubStringSearchModifier(nameSubString), GtlQueryUtils.addSubStringSearchModifier(nameSubString)
        };
        String orderBy = sortSpec != null && !sortSpec.isSortKeyNull() ? sortSpec.getSortKeySpec() : null;
        int pageSize = pageSpec.getPageSize();
        int pageNumber = pageSpec.getPageNumber();
        rows = Processors.getDefaultProcessor().doFindAllQuery(ProductRow.TYPE, "institution_code = ? and (product_type = ?) and ( (standard_name like ?) or(name_alt1 like ?) or(name_alt2 like ?) ) ", orderBy, null, bindVars, pageSize, pageNumber);
        return toProductList(rows);
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException("System exception while searching product with nameSubString :" + nameSubString, de);
    }

    public List searchProducts(ProductTypeEnum productType, Market market, PaginationQueryParamsSpec pageSpec, SortKeySpec sortSpec)
    {
        if(market == null)
            throw new IllegalArgumentException("market should not be null");
        List rows;
        String where = "institution_code = ? and product_type = ? and (product_id in (select product_id from traded_product where market_id =  ?  ) )";
        Object bindVars[] = {
            market.getInstitution().getInstitutionCode(), new Long(productType.intValue()), new Long(market.getMarketId())
        };
        String orderBy = sortSpec != null && !sortSpec.isSortKeyNull() ? sortSpec.getSortKeySpec() : null;
        int pageSize = pageSpec.getPageSize();
        int pageNumber = pageSpec.getPageNumber();
        rows = Processors.getDefaultProcessor().doFindAllQuery(ProductRow.TYPE, "institution_code = ? and product_type = ? and (product_id in (select product_id from traded_product where market_id =  ?  ) )", orderBy, null, bindVars, pageSize, pageNumber);
        return toProductList(rows);
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException("System exception while searching product with market id :" + market.getMarketId(), de);
    }

    public TradedProduct getTradedProduct(long productId, long marketId)
        throws NotFoundException
    {
        TradedProductRow tradedProductRow = GtlDbUtils.getTradedProductRow(productId, marketId);
        if(tradedProductRow == null)
            throw new NotFoundException("No TradedProduct found with product id, market id : " + productId + "," + marketId);
        else
            return toTradedProduct(tradedProductRow);
    }

    public TradedProduct getTradedProduct(Product product, Market market)
        throws NotFoundException
    {
        return getTradedProduct(product.getProductId(), market.getMarketId());
    }

    public TradedProduct getTradedProduct(long id)
        throws NotFoundException
    {
        TradedProductRow tradedProductRow = GtlDbUtils.getTradedProductRow(id);
        return toTradedProduct(tradedProductRow);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No TradedProduct found with id : " + id);
        DataException dne;
        dne;
        m_log.error(dne.getMessage(), dne);
        throw new RuntimeSystemException("System exception while getting TradedProduct with id : " + id);
    }

    private List toProductList(List productRows)
        throws DataException
    {
        int size = productRows.size();
        List products = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            ProductRow productRow = (ProductRow)productRows.get(i);
            ProductManager pm = GtlUtils.getTradingModule(productRow.getProductType()).getProductManager();
            Product p = pm.toProduct(productRow);
            products.add(p);
        }

        return products;
    }

    public Product toProduct(Row row)
    {
        if(!(row instanceof ProductRow))
        {
            throw new IllegalArgumentException("Expected ProductRow. But the given Row is the type of: " + row.getClass());
        } else
        {
            ProductRow prow = (ProductRow)row;
            return toTradingModuleSpecificProduct(prow);
        }
    }

    public TradedProduct toTradedProduct(Row row)
    {
        if(!(row instanceof TradedProductRow))
        {
            throw new IllegalArgumentException("Expected TradedProductRow. But the given Row is the type of: " + row.getClass());
        } else
        {
            TradedProductRow tprow = (TradedProductRow)row;
            return toTradingModuleSpecificTradedProduct(tprow);
        }
    }

    protected abstract Product toTradingModuleSpecificProduct(ProductRow productrow);

    protected abstract TradedProduct toTradingModuleSpecificTradedProduct(TradedProductRow tradedproductrow);

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BaseProductManagerImpl.class);
        DBG = m_log.ison();
    }
}
