head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundTradedProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : g£MζψΑΏForMock(WEB3MutualFundTradedProductForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 GΜ (u) VKμ¬
*/
package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import webbroker3.util.WEB3LogUtility;

/**
 * g£MζψΑΏForMock
 *
 * @@author GΜ(u)
 * @@version 1.0
 */
public class WEB3MutualFundTradedProductForMock extends WEB3MutualFundTradedProduct
{
    /**
     * O[eBeB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradedProductForMock.class);

    /**
     * (g£MζψΑΏ(Mock))<BR>
     * RXgN^B<BR>
     * @@param l_tradedProductRow - ζψΑΏRow
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3MutualFundTradedProductForMock(TradedProductRow l_tradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_tradedProductRow);
    }

    /**
     * (g£MζψΑΏ(Mock))<BR>
     * RXgN^B<BR>
     * @@param l_mutualFundTradedProductRow - MζψΑΏRow
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3MutualFundTradedProductForMock(MutualFundTradedProductRow l_mutualFundTradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundTradedProductRow);
    }
}
@
