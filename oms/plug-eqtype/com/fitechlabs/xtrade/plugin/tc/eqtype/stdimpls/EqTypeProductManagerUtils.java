// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeProductManagerUtils.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.text.SimpleDateFormat;

public class EqTypeProductManagerUtils
{

    private EqTypeProductManagerUtils()
    {
    }

    static EqtypeProductRow getEqtypeProductRow(Institution inst, String productCode)
    {
        return EqtypeProductDao.findRowByInstitutionCodeProductCode(inst.getInstitutionCode(), productCode);
        DataException de;
        de;
        String msg = "Exception while fetching eqtypeProduct table with product code:" + productCode + ",and institution code:" + inst.getInstitutionCode();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    static EqtypeTradedProductRow getEqtypeTradedProductRow(long tradedProductId)
        throws DataFindException
    {
        String currentBizDateYYYYMMDD;
        EqtypeTradedProductRow row;
        try
        {
            row = EqtypeTradedProductDao.findRowByPk(tradedProductId);
        }
        catch(DataFindException dfe)
        {
            throw new DataFindException("No eqtype_traded_product row found with traded_product_id:" + tradedProductId);
        }
        catch(DataException de)
        {
            String msg = "Exception while fetching eqtype_traded_product table with tradedProduct ID:" + tradedProductId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
        if(row.getValidUntilBizDate() == null)
            return row;
        java.util.Date currentBizDate = GtlUtils.getFinObjectManager().getTradingCalendar(tradedProductId).getCurrentBizDate();
        currentBizDateYYYYMMDD = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(currentBizDate);
        if(row.getValidUntilBizDate().equals(currentBizDateYYYYMMDD))
            return row;
        EqtypeTradedProductParams params;
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow updqRow = EqtypeTradedProductUpdqDao.findRowByPk(tradedProductId, currentBizDateYYYYMMDD);
        params = new EqtypeTradedProductParams();
        GtlUtils.copyRow2Params(updqRow, params);
        return params;
        DataFindException dfe;
        dfe;
        String msg = "No eqtype_traded_product_updq row found with tradedProductID, bizDate:" + tradedProductId + "," + currentBizDateYYYYMMDD;
        m_log.warn(msg, dfe);
        throw new DataFindException(dfe.getMessage());
        DataException dq;
        dq;
        String msg = "Exception while fetching eqtype_traded_product_updq table with tradedProductID, bizDate:" + tradedProductId + "," + currentBizDateYYYYMMDD;
        m_log.error(msg, dq);
        throw new RuntimeSystemException(msg, dq);
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductManagerUtils.class);
        DBG = m_log.ison();
    }
}
