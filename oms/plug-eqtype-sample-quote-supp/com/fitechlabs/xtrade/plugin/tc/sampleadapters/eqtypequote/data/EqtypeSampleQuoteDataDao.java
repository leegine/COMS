// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqtypeSampleQuoteDataDao.java

package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.sampleadapters.eqtypequote.data:
//            EqtypeSampleQuoteDataPK, EqtypeSampleQuoteDataRow

public class EqtypeSampleQuoteDataDao extends DataAccessObject
{

    protected EqtypeSampleQuoteDataDao(EqtypeSampleQuoteDataRow row)
    {
        super(row);
        this.row = row;
    }

    public EqtypeSampleQuoteDataRow getEqtypeSampleQuoteDataRow()
    {
        return row;
    }

    public static EqtypeSampleQuoteDataDao forRow(EqtypeSampleQuoteDataRow row)
        throws IllegalArgumentException
    {
        return (EqtypeSampleQuoteDataDao)DataAccessObject.forRow(row);
    }

    public static long newPkValue()
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery(EqtypeSampleQuoteDataRow.TYPE);
    }

    public static EqtypeSampleQuoteDataPK newPkObject()
        throws DataNetworkException, DataQueryException
    {
        throw new UnsupportedOperationException("auto-generation of primary keys with multiple components not supported.");
    }

    public static EqtypeSampleQuoteDataRow findRowByPk(String p_productCode, String p_marketCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        EqtypeSampleQuoteDataPK pk = new EqtypeSampleQuoteDataPK(p_productCode, p_marketCode);
        return findRowByPk(pk);
    }

    public static EqtypeSampleQuoteDataRow findRowByPk(EqtypeSampleQuoteDataPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EqtypeSampleQuoteDataRow)qp.doFindByPrimaryKeyQuery(pk, null);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static EqtypeSampleQuoteDataDao findDaoByPk(String p_productCode, String p_marketCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        EqtypeSampleQuoteDataPK pk = new EqtypeSampleQuoteDataPK(p_productCode, p_marketCode);
        EqtypeSampleQuoteDataRow row = findRowByPk(pk);
        return forRow(row);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static EqtypeSampleQuoteDataDao findDaoByPk(EqtypeSampleQuoteDataPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        EqtypeSampleQuoteDataRow row = findRowByPk(pk);
        return forRow(row);
    }

    private EqtypeSampleQuoteDataRow row;
    public static final com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory FACTORY = new com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory() {

        public DataAccessObject newInstance(Row row)
        {
            if(row instanceof EqtypeSampleQuoteDataRow)
                return new EqtypeSampleQuoteDataDao((EqtypeSampleQuoteDataRow)row);
            else
                throw new IllegalArgumentException("Not a EqtypeSampleQuoteDataRow or subclass: " + row.getClass());
        }

    }
;

}
