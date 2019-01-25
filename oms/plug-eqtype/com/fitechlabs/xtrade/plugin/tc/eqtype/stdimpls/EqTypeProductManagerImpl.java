// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeProductManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BaseProductManagerImpl;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeProductImpl, CashProductImpl, EqTypeTradedProductImpl, EqTypeProductManagerUtils

public class EqTypeProductManagerImpl extends BaseProductManagerImpl
    implements EqTypeProductManager
{

    public EqTypeProductManagerImpl()
    {
    }

    public EqTypeProduct getProduct(Institution inst, String productCode)
        throws NotFoundException
    {
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow row;
        row = EqTypeProductManagerUtils.getEqtypeProductRow(inst, productCode);
        if(row == null)
            throw new NotFoundException("No product found with institution_code,product_code : " + inst.getInstitutionCode() + "," + productCode);
        return new EqTypeProductImpl(row);
        DataFindException dfe;
        dfe;
        m_log.error(dfe.getMessage(), dfe);
        throw new NotFoundException("No product found with institutionCode, productCode : " + inst.getInstitutionCode() + "," + productCode);
        DataException dne;
        dne;
        m_log.error(dne.getMessage(), dne);
        throw new RuntimeSystemException("System exception while getting product with institutionCode, productCode : " + inst.getInstitutionCode() + "," + productCode);
    }

    public EqTypeTradedProduct getTradedProduct(Institution inst, String productCode, String marketCode)
        throws NotFoundException
    {
        Product p = getProduct(inst, productCode);
        com.fitechlabs.xtrade.plugin.tc.gentrade.Market m = GtlUtils.getFinObjectManager().getMarket(inst, marketCode);
        TradedProduct tproduct = getTradedProduct(p, m);
        if(!(tproduct instanceof EqTypeTradedProduct))
            throw new NotFoundException("No TradedProduct found with institutionCode, productCode,marketCode : " + inst.getInstitutionCode() + "," + productCode + "," + marketCode);
        else
            return (EqTypeTradedProduct)tproduct;
    }

    public Product toTradingModuleSpecificProduct(ProductRow prow)
    {
        ProductTypeEnum productType = prow.getProductType();
        TradingModule eqtypeTm = GtlUtils.getTradingModule("eqtype");
        if(!eqtypeTm.canHandle(productType))
            break MISSING_BLOCK_LABEL_51;
        if(ProductTypeEnum.CASH.equals(productType))
            return new CashProductImpl(prow);
        return new EqTypeProductImpl(prow);
        return GtlUtils.getTradingModule(productType).getProductManager().toProduct(prow);
        DataException dex;
        dex;
        String msg = "Cannot generate EqTypeProduct with product type, product id: " + prow.getProductType().stringValue() + "," + prow.getProductId();
        m_log.error(msg, dex);
        throw new RuntimeSystemException(msg, dex);
    }

    public TradedProduct toTradingModuleSpecificTradedProduct(TradedProductRow tprow)
    {
        ProductTypeEnum productType = tprow.getProductType();
        TradingModule eqtypeTm = GtlUtils.getTradingModule("eqtype");
        if(eqtypeTm.canHandle(productType))
            return new EqTypeTradedProductImpl(tprow);
        return GtlUtils.getTradingModule(productType).getProductManager().toTradedProduct(tprow);
        DataException dex;
        dex;
        String msg = "Cannot generate EqTypeTradedProduct with product type, traded product id: " + tprow.getProductType() + "," + tprow.getTradedProductId();
        m_log.error(msg, dex);
        throw new RuntimeSystemException(msg, dex);
    }

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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductManagerImpl.class);
        DBG = m_log.ison();
    }
}
