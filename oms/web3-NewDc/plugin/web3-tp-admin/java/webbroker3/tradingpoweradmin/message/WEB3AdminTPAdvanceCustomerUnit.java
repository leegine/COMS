head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAdvanceCustomerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : §ÖàÚqjbgNX(WEB3AdminTPAdvanceCustomerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/08 asano(SCS) VKì¬
 */
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (§ÖàÚqjbg)<BR>
 * §ÖàÚqjbg<BR>
 */
public class WEB3AdminTPAdvanceCustomerUnit extends Message
{

    /**
     * (]ÍvZÊID)
     */
    public String calcResultId;
    
    /**
     * (XR[h)
     */
    public String branchCode;
    
    /**
     * (ÚqR[h)
     */
    public String accountCode;
    
    /**
     * (Úq¼)
     */
    public String accountName;
    
    /**
     * (µÒR[h)
     */
    public String traderCode;
    
    /**
     * (aèØÚqæª)
     */
    public String depositDiv;
    
    /**
     * (§ÖàÚq¾×ê)
     */
    public WEB3AdminTPAdvanceCustomerDetailUnit[] advanceCustomerDetailUnits;
    
    /**
     * (RXgN^)
     */
    public WEB3AdminTPAdvanceCustomerUnit()
    {
    }
    
    /**
     * toString
     *
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer("WEB3AdminTPAdvanceCustomerUnit[");
        l_sb.append("branchCode=" + this.branchCode);
        l_sb.append(",accountCode=" + this.accountCode);
        l_sb.append(",accountName=" + this.accountName);
        l_sb.append(",calcResultId=" + this.calcResultId);
        l_sb.append(",depositDiv=" + this.depositDiv);
        l_sb.append(",traderCode=" + this.traderCode);
        for(int i = 0; i < this.advanceCustomerDetailUnits.length; i++)
        {
            l_sb.append(",advanceCustomerDetailUnits[" + i + "]=" + this.advanceCustomerDetailUnits[i]);            
        }
        l_sb.append("]");
        
        return l_sb.toString();
    }
    
}
@
