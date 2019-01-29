head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqTradedProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;

public class WEB3FeqTradedProductForMock extends WEB3FeqTradedProduct
{

    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqTradedProductForMock(TradedProductRow l_trow)
        throws DataFindException
    {
        super(l_trow);
    }

    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqTradedProductForMock(FeqTradedProductRow l_row)
        throws DataFindException
    {
        super(l_row);
    }
}
@
