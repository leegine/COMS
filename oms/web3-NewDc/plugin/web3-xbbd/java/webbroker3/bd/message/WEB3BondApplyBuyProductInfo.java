head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : Âå/tÁ¿îñ(WEB3BondApplyBuyProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 «ô (u) VKì¬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (Âå/tÁ¿îñ)<BR>
 * Âå/tÁ¿îñ<BR>
 * <BR>
 * @@author «ô
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductInfo extends Message
{
    
    /**
     * (Á¿ID)<BR>
     * Á¿ID<BR>
     */
    public String productId;
    
    /**
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;
    
    /**
     * (íÊR[h)<BR>
     * íÊR[h<BR>
     */
    public String bondCategCode;
    
    /**
     * (S&P)<BR>
     * S&P<BR>
     */
    public String sAndP;
    
    /**
     * (Moody's)<BR>
     * Moody's<BR>
     */
    public String moodys;
    
    /**
     * (¦)<BR>
     * ¦<BR>
     */
    public String coupon;
    
    /**
     * (NÔ¥ñ)<BR>
     * NÔ¥ñ<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (¥ú1)<BR>
     * ¥ú1<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (¥ú2)<BR>
     * ¥ú2<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (ÊÝR[h)<BR>
     * ÊÝR[h<BR>
     */
    public String currencyCode;
    
    /**
     * (\PÊ)<BR>
     * \PÊ<BR>
     */
    public String tradeUnit;
    
    /**
     * (Åá\Ê)<BR>
     * Åá\Ê<BR>
     */
    public String minOrderQuantity;
    
    /**
     * (Å\Ê)<BR>
     * Å\Ê<BR>
     */
    public String maxOrderQuantity;
    
    /**
     * (åJnú)<BR>
     * åJnú<BR>
     */
    public Date recruitStartDate;
    
    /**
     * (åI¹ú)<BR>
     * åI¹ú<BR>
     */
    public Date recruitEndDate;
    
    /**
     * (tP¿)<BR>
     * tP¿<BR>
     */
    public String buyPrice;
    
    /**
     * (­sú)<BR>
     * ­sú<BR>
     */
    public Date issueDate;
    
    /**
     * (Òú)<BR>
     * Òú<BR>
     */
    public Date maturityDate;
    
    /**
     * (æøÂ\æª)<BR>
     * æøÂ\æª<BR>
     * <BR>
     * 1Få<BR>
     * 2Ft<BR>
     */
    public String posibleDiv;
    
    /**
     * (Âå/tÁ¿îñ)<BR>
     * RXgN^<BR>
     * @@roseuid 44BDBBAE0176
     */
    public WEB3BondApplyBuyProductInfo() 
    {
     
    }
}
@
