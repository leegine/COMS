head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÂÁ¿Æïîñ(WEB3AdminBondProductConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 æâÑQ (u) VKì¬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ÂÁ¿Æïîñ)<BR>
 * ÂÁ¿s
 * <BR>
 * @@author æâÑQ
 * @@version 1.0
 */
public class WEB3AdminBondProductConditionUnit extends Message
{
    
    /**
     * (Á¿R[h(WEB3))<BR>
     * Á¿R[h(WEB3)
     */
    public String productCode;
    
    /**
     * (HOSTÁ¿¼1)<BR>
     * HOSTÁ¿¼1
     */
    public String hostProductName1;
    
    /**
     * (æµÁ¿¼)<BR>
     * æµÁ¿¼
     */
    public String handlingProductName;
    
    /**
     * (ÊÝR[h)<BR>
     * ÊÝR[h
     */
    public String currencyCode;
    
    /**
     * (æµæª)<BR>
     * æµæª
     */
    public String tradeHandleDiv;
    
    /**
     * (­s¿i)<BR>
     * ­s¿i
     */
    public String issuePrice;
    
    /**
     * (¦)<BR>
     * ¦
     */
    public String coupon;
    
    /**
     * (­sú)<BR>
     * ­sú
     */
    public Date issueDate;
    
    /**
     * (Òú)<BR>
     * Òú
     */
    public Date maturityDate;
    
    /**
     * (NÔ¥ñ)<BR>
     * NÔ¥ñ<BR>
     * <BR>
     * sèÍ99999999
     */
    public String yearlyInterestPayments;
    
    /**
     * (¥ú1)<BR>
     * ¥ú1<BR>
     * <BR>
     * "0000"ÌÍ\¦µÈ¢
     */
    public String interestPaymentDay1;
    
    /**
     * (¥ú2)<BR>
     * ¥ú2<BR>
     * <BR>
     * "0000"ÌÍ\¦µÈ¢
     */
    public String interestPaymentDay2;
    
    /**
     * (íÊR[h)<BR>
     * íÊR[h
     */
    public String bondCategCode;
    
    /**
     * @@roseuid 44E3363B0280
     */
    public WEB3AdminBondProductConditionUnit() 
    {
     
    }
}
@
