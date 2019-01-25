// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProductManager.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.dbind.Row;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotFoundException, Product, Market, TradedProduct, 
//            Institution, ProductTypeEnum, PaginationQueryParamsSpec, SortKeySpec

public interface ProductManager
{

    public abstract Product getProduct(long l)
        throws NotFoundException;

    public abstract TradedProduct getTradedProduct(Product product, Market market)
        throws NotFoundException;

    public abstract TradedProduct getTradedProduct(long l, long l1)
        throws NotFoundException;

    public abstract TradedProduct getTradedProduct(long l)
        throws NotFoundException;

    public abstract Product toProduct(Row row);

    public abstract TradedProduct toTradedProduct(Row row);

    public abstract List searchProducts(Institution institution, ProductTypeEnum producttypeenum, String s, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract List searchProductsBeginningWith(Institution institution, ProductTypeEnum producttypeenum, String s, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract List searchProducts(ProductTypeEnum producttypeenum, Market market, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);
}
