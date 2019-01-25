// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqtypeSampleQuoteDataPK.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data:
//            EqtypeSampleQuoteDataRow

public class EqtypeSampleQuoteDataPK
    implements PrimaryKey
{

    public RowType getRowType()
    {
        return EqtypeSampleQuoteDataRow.TYPE;
    }

    public EqtypeSampleQuoteDataPK()
    {
        m_id = null;
    }

    public EqtypeSampleQuoteDataPK(String p_productCode, String p_marketCode)
    {
        m_id = null;
        product_code = p_productCode;
        market_code = p_marketCode;
    }

    public static EqtypeSampleQuoteDataPK fromString(String pkValueString)
        throws NumberFormatException
    {
        EqtypeSampleQuoteDataPK pk = new EqtypeSampleQuoteDataPK();
        int i = pkValueString.indexOf(',');
        pk.product_code = pkValueString.substring(0, i);
        pk.market_code = pkValueString.substring(i + 1);
        pk.m_id = pkValueString;
        return pk;
    }

    public String toString()
    {
        if(m_id == null)
            m_id = product_code + "," + market_code;
        return m_id;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof EqtypeSampleQuoteDataPK))
            return false;
        EqtypeSampleQuoteDataPK o = (EqtypeSampleQuoteDataPK)other;
        if(product_code == null)
        {
            if(o.product_code != null)
                return false;
        } else
        if(!product_code.equals(o.product_code))
            return false;
        if(market_code == null)
        {
            if(o.market_code != null)
                return false;
        } else
        if(!market_code.equals(o.market_code))
            return false;
        return true;
    }

    public int hashCode()
    {
        return (product_code == null ? 0 : product_code.hashCode()) + (market_code == null ? 0 : market_code.hashCode());
    }

    public static final String TAGNAME = "pk";
    public static final String PTYPE = "eqtype_sample_quote_data";
    public String product_code;
    public String market_code;
    private String m_id;
}
