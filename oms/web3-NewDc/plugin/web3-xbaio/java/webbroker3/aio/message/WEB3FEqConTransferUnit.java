head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : OUÖ¾×(WEB3FEqConTransferUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 üE(u) VKì¬
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (OUÖ¾×)<BR>
 * OUÖ¾×NX
 * 
 * @@author üE(u)
 * @@version 1.0
 */
public class WEB3FEqConTransferUnit extends Message 
{
    
    /**
     * (¶ID)<BR>
     * ¶ID
     */
    public String orderId;
    
    /**
     * (ótú)<BR>
     * ótú
     */
    public Date receptionDate;
    
    /**
     * (UÖàz)<BR>
     * UÖàz
     */
    public String changeAmt;
    
    /**
     * (ón\èú)<BR>
     * ón\èú
     */
    public Date deliveryScheduledDate;
    
    /**
     * (óµ)<BR>
     * óµ<BR>
     * <BR>
     * 0F UÖ<BR>
     * 1F UWGÏ<BR>
     * 2F UWGÏ®¹<BR>
     * 3F UWGÏG[<BR>
     * 4F æÁÏ
     */
    public String transactionStateType;
    
    /**
     * (æÁÂ\tO)<BR>
     * æÁÂ\tO<BR>
     * <BR>
     * æÁÂ\F true<BR>
     * æÁsÂF false
     */
    public boolean cancelFlag;
    
    /**
     * (OUÖ¾×)<BR>
     * RXgN^
     * @@roseuid 41CBF4D60343
     */
    public WEB3FEqConTransferUnit() 
    {
     
    }
}
@
