head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityProductManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityProductManagerForMock extends WEB3EquityProductManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityProductManagerForMock.class);

    public EqTypeProduct getProduct(Institution l_institution, String l_strProductCode) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityProductManager", "getProduct",
                new Class[]
                { Institution.class, String.class }, new Object[]
                { l_institution, l_strProductCode });

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityProductManager", "getProduct",
                new Class[]
                { Institution.class, String.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityProductManager.getProduct(Institution, String)");

            return (EqTypeProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getProduct", new Class[]
                    { Institution.class, String.class }).asObject();
        }
        return super.getProduct(l_institution, l_strProductCode);
    }

    public Product toProduct(Row l_row)
    {
        Product l_product = super.toProduct(l_row);
        if (l_product instanceof WEB3EquityProduct)
        {
            try
            {
                return new WEB3EquityProductForMock((EqtypeProductRow) l_product.getDataSourceObject());
            }
            catch (DataException l_dqe)
            {
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + "."
                        + "toProduct(Row)", l_dqe);
            }
        }
        return l_product;
    }

    public WEB3EquityProductQuote getDisplayEquityProductQuote(EqTypeTradedProduct l_eqtypeTradedProduct,
            WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityProductManager",
                "getDisplayEquityProductQuote", new Class[]
                { EqTypeTradedProduct.class, WEB3GentradeSubAccount.class }, new Object[]
                { l_eqtypeTradedProduct, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityProductManager",
                "getDisplayEquityProductQuote", new Class[]
                { EqTypeTradedProduct.class, WEB3GentradeSubAccount.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityProductManagerForMock.getDisplayEquityProductQuote()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getDisplayEquityProductQuote", new Class[]
                    { EqTypeTradedProduct.class, WEB3GentradeSubAccount.class }).asWEB3BaseException();
            return (WEB3EquityProductQuote) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getDisplayEquityProductQuote", new Class[]
                    { EqTypeTradedProduct.class, WEB3GentradeSubAccount.class }).asObject();
        }
        return super.getDisplayEquityProductQuote(l_eqtypeTradedProduct, l_subAccount);
    }

    public TradedProduct toTradedProduct(Row l_row)
    {
        TradedProductRow l_tradedProductRow = (TradedProductRow) l_row;

        try
        {
            return new WEB3EquityTradedProductForMock(l_tradedProductRow);
        }
        catch (DataException l_dqe)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + "."
                    + "toProduct(Row)", l_dqe);
        }
    }

    public TradedProduct getTradedProduct(Product l_lngProductId, Market l_lngMarketId) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityProductManager",
                "getTradedProduct", new Class[]
                { Product.class, Market.class }, new Object[]
                { l_lngProductId, l_lngMarketId });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityProductManager", "getTradedProduct",
                new Class[]
                { Product.class, Market.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityProductManagerForMock.getTradedProduct()");
            return (TradedProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getTradedProduct", new Class[]
                    { Product.class, Market.class }).asObject();
        }
        return super.getTradedProduct(l_lngProductId, l_lngMarketId);
    }
    
    public TradedProduct getTradedProduct(long l_lngProductId, long l_lngMarketId) throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityProductManager",
                "getTradedProduct", new Class[]
                { long.class, long.class }, new Object[]
                { new Long(l_lngProductId), new Long(l_lngMarketId) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityProductManager", "getTradedProduct",
                new Class[]
                { long.class, long.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityProductManagerForMock.getTradedProduct()");
            return (TradedProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getTradedProduct", new Class[]
                    { long.class, long.class }).asObject();
        }
        return super.getTradedProduct(l_lngProductId, l_lngMarketId);
    }
    
    public double getTickValue(WEB3EquityTradedProduct l_tradedProduct, double l_dblBasePrice) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityProductManager", "getTickValue",
                new Class[]
                { WEB3EquityTradedProduct.class, double.class }, new Object[]
                { l_tradedProduct, new Double(l_dblBasePrice) });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityProductManager", "getTickValue",
                new Class[]
                { WEB3EquityTradedProduct.class, double.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityProductManagerForMock.getTickValue()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class }).asDouble();
        }
        return super.getTickValue(l_tradedProduct, l_dblBasePrice);
    }

    public EqTypeTradedProduct getTradedProduct(
            Institution l_institution,
            String l_strProductCode,
            String l_strMarketCode)
                throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityProductManager",
                "getTradedProduct", new Class[]
                { Institution.class, String.class, String.class }, new Object[]
                { l_institution, l_strProductCode, l_strMarketCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityProductManager", "getTradedProduct",
                new Class[]
                {Institution.class, String.class, String.class  }))
        {
            log.debug("webbroker3.equity.WEB3EquityProductManagerForMock.getTradedProduct()");
            return (EqTypeTradedProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getTradedProduct", new Class[]
                    { Institution.class, String.class, String.class  }).asObject();
        }
        return super.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
    }
}
@
