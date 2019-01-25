// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            Utils

public class EqTypeOrderImpl
    implements EqTypeOrder
{

    public EqTypeOrderImpl(long order_id)
        throws DataQueryException, DataNetworkException
    {
        this(EqtypeOrderDao.findRowByPk(order_id));
    }

    public EqTypeOrderImpl(EqtypeOrderRow row)
    {
        this(row.getAccountId(), row.getSubAccountId(), row.getOrderId(), row.getProductType());
        m_Row = row;
    }

    public EqTypeOrderImpl(OrderUnit orderUnit)
    {
        this(orderUnit.getAccountId(), orderUnit.getSubAccountId(), orderUnit.getOrderId(), orderUnit.getProduct().getProductType());
    }

    public EqTypeOrderImpl(long accountId, long subAccountId, long orderId, ProductTypeEnum productType)
    {
        m_accountId = accountId;
        m_subAccountId = subAccountId;
        m_orderId = orderId;
        m_productType = productType;
    }

    public Object getDataSourceObject()
    {
        if(m_Row == null)
            try
            {
                m_Row = EqtypeOrderDao.findRowByPk(getOrderId());
            }
            catch(DataException e)
            {
                throw new RuntimeSystemException("Order object could not be obtained for order_id : " + getOrderId(), e);
            }
        return m_Row;
    }

    public OrderUnit[] getOrderUnits()
    {
        long orderId = getOrderId();
        EqTypeOrderManager eqtypeom = Utils.getGlobalOrderManager();
        return eqtypeom.getOrderUnits(orderId);
    }

    public long getAccountId()
    {
        return m_accountId;
    }

    public long getOrderId()
    {
        return m_orderId;
    }

    public long getSubAccountId()
    {
        return m_subAccountId;
    }

    public SubAccount getSubAccount()
    {
        SubAccount subAccount = GtlUtils.getAccountManager().getSubAccount(getAccountId(), getSubAccountId());
        return subAccount;
        NotFoundException nfe;
        nfe;
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("SubAccount object could not be obtained for account_id/sub_account_id : " + getAccountId() + "/" + getSubAccountId(), nfe);
    }

    public ProductTypeEnum getProductType()
    {
        return m_productType;
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
    private final long m_accountId;
    private final long m_subAccountId;
    private final long m_orderId;
    private final ProductTypeEnum m_productType;
    protected EqtypeOrderRow m_Row;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderImpl.class);
        DBG = m_log.ison();
    }
}
