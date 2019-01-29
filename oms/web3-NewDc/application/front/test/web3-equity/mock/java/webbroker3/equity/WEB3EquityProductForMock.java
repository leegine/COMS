head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

public class WEB3EquityProductForMock extends WEB3EquityProduct
{

    public WEB3EquityProductForMock(long l_lngProductId) throws DataQueryException, DataNetworkException
    {
        super(l_lngProductId);
        // TODO Auto-generated constructor stub
    }

    public WEB3EquityProductForMock(ProductRow l_productRow) throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
        // TODO Auto-generated constructor stub
    }

    public WEB3EquityProductForMock(EqtypeProductRow l_eqtypeProductRow) throws DataQueryException,
        DataNetworkException
    {
        super(l_eqtypeProductRow);
        // TODO Auto-generated constructor stub
    }
//    public String getStandardName()
//    {
//        throw new UnsupportedOperationException("getStandardNameは利用できない！チームリーダへ連絡してください！");
//    }
}
@
