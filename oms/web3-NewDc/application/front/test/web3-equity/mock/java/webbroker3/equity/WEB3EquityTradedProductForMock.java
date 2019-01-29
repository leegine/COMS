head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityTradedProductForMock.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

public class WEB3EquityTradedProductForMock extends WEB3EquityTradedProduct
{

    public WEB3EquityTradedProductForMock(TradedProductRow l_row) throws DataQueryException, DataNetworkException
    {
        super(l_row);
    }

}
@
