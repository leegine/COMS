head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityFinTransaction.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ฎฺqจ่NX(WEB3EquityFinTransaction.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 ๖@@๕F(SRA) VK์ฌ
                   2005/01/06 ชบaพ(SRA) JavaDocCณ
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeFinTransactionImpl;

/**
 * iฎฺqจ่jB<BR>
 * <BR>
 * ๑่ูฎพื๐\ปท้B<BR>
 * xTradeฬEqTypeFinTransaction๐gฃตฝNXB
 * @@author ๖@@๕F(SRA)
 * @@version 1.0
 */
public class WEB3EquityFinTransaction extends EqTypeFinTransactionImpl
{

    /**
     * (RXgN^B)<BR>
     *<BR> 
     * @@param l_lngFinTransactionId
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3EquityFinTransaction(long l_lngFinTransactionId)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngFinTransactionId);
    }

    /**
     * (RXgN^B)<BR>
     *<BR>
     * @@param l_row EqtypeFinTransactionRowIuWFNg
     */
    public WEB3EquityFinTransaction(EqtypeFinTransactionRow l_row)
    {
        super(l_row);
    }
}
@
