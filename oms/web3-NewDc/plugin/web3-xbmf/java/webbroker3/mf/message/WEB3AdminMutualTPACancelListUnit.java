head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : MÇÒ]Í²®æÁês(WEB3AdminMutualTPACancelListUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 © (u) VKì¬
*/

package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 *(MÇÒ]Í²®æÁês)<BR>
 *MõÇÒ]Í²®æÁêsf[^NX<BR>
 * 
 * @@author ©(u)
 * @@version 1.0   
 */

public class WEB3AdminMutualTPACancelListUnit extends Message
{
    /**
     * (¶ID)<BR>
     *  ¶ID<BR>
     */
    public String orderId;
    
    /**
     * (Á¿R[h)<BR>
     *  Á¿R[h<BR>
     */
    public String mutualProductCode;
    
    /**
     * (Á¿¼)<BR>
     *  Á¿¼<BR>
     */
    public String mutualProductName;
    
    /**
     * (¸Zàz)<BR>
     *  ¸Zàz<BR>
     */
    public String settlePrice;
    
    /**
     * (­ú)<BR>
     *  ­ú<BR>
     */
    public Date orderBizDate;
    
    /**
     * (ñèú)<BR>
     *  ñèú<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (ónú)<BR>
     *  ónú<BR>
     */
    public Date deliveryDate;
    
    /**
     * ftHgRXgN^
     * @@roseuid 4073BCCD0389<BR>
     */
    public WEB3AdminMutualTPACancelListUnit()
    {
        
    }
}
@
