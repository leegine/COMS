head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : MpæøÏê¾×s(WEB3MarginCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ½½ (u) VKì¬
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * iMpæøÏê¾×sjB<br>
 * <br>
 * MpæøÏê¾×sNX
 * @@author ½½
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractGroup extends Message 
{
         
    /**
     * (ú)<BR>
     * <BR>
     * YYYY/MM/DD`®Å\¦
     */
    public Date openDate;
    
    /**
     * (P¿)<BR>
     */
    public String contractPrice;
    
    /**
     * (¶)<BR>
     */
    public String orderQuantity;
    
    /**
     * (¶P¿æª)<BR>
     * 0F¬s@@1Fwl
     */
    public String orderPriceDiv;
    
    /**
     * (¶P¿)<BR>
     */
    public String limitPrice;
    
    /**
     * (ñè)
     */
    public String execQuantity;
    
    /**
     * (ñèP¿)<BR>
     */
    public String execPrice;
    
    /**
     * (è¿)<BR>
     */
    public String contractCommission;
    
    /**
     * (úà)<BR>
     * ¦Å
     */
    public String interestFee;
    
    /**
     * (túà)<BR>
     * ¦Å
     */
    public String payInterestFee;
    
    /**
     * (Ý¿)<BR>
     * ¦Å
     */
    public String loanEquityFee;
    
    /**
     * (·¿)<BR>
     * ¦Å
     */
    public String setupFee;
    
    /**
     * (Çï)<BR>
     * ¦Å
     */
    public String managementFee;
    
    /**
     * (»Ì¼)<BR>
     * ¦Å
     */
    public String otherwise;
    
    /**
     * (Ï¹v)<BR>
     */
    public String settleProfitLoss;
    
    /**
     * (ÏÊ)<BR>
     */
    public String settlePriority;
    
    /**
     * @@roseuid 414032D000F4
     */
    public WEB3MarginCloseMarginContractGroup() 
    {
     
    }
}
@
