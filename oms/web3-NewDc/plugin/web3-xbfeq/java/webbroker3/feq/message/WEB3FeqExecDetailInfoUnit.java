head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®ñèÚ×iÇÒj(WEB3FeqExecDetailInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
*/
package webbroker3.feq.message;

import java.util.Date;


/**
 * (O®ñèÚ×iÇÒj)<BR>
 * O®ñèÚ×iÇÒjNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3FeqExecDetailInfoUnit extends WEB3FeqExecuteUnit 
{
    
    /**
     * (ñèID)<BR>
     * ñèID
     */
    public String execId;
    
    /**
     * (ñèÔ)<BR>
     * ñèÔ
     */
    public String execNo;
    
    /**
     * (ãà)<BR>
     * ãà
     */
    public String foreignTradePrice;
    
    /**
     * (»nè¿)<BR>
     * »nè¿
     */
    public String localCommission;
    
    /**
     * (»næøÅ)<BR>
     * »næøÅ
     */
    public String localTradingTax;
    
    /**
     * (»Ì¼RXg1)<BR>
     * »Ì¼RXg1
     */
    public String otherCost1;
    
    /**
     * (»Ì¼RXg2)<BR>
     * »Ì¼RXg2
     */
    public String otherCost2;
    
    /**
     * (´Zãài~Ýj)<BR>
     * ´Zãài~Ýj
     */
    public String clearUpPrice;
    
    /**
     * (´ZãàiOÝj)<BR>
     * ´ZãàiOÝj
     */
    public String foreignClearUpPrice;
    
    /**
     * (àè¿i~Ýj)<BR>
     * àè¿i~Ýj
     */
    public String commission;
    
    /**
     * (àè¿iOÝj)<BR>
     * àè¿iOÝj
     */
    public String foreignCommission;
    
    /**
     * (ÁïÅi~Ýj)<BR>
     * ÁïÅi~Ýj
     */
    public String consumptionTax;
    
    /**
     * (ÁïÅiOÝj)<BR>
     * ÁïÅiOÝj
     */
    public String foreignConsumptionTax;
    
    /**
     * (ónàzi~Ýj)<BR>
     * ónàzi~Ýj
     */
    public String deliveryPrice;
    
    /**
     * (ónàziOÝj)<BR>
     * ónàziOÝj
     */
    public String foreignDeliveryPrice;
    
    /**
     * (àónú)<BR>
     * àónú
     */
    public Date deliveryDate;
    
    /**
     * (O®ñèÚ×iÇÒj)<BR>
     * RXgN^
     * @@roseuid 4281E60D03A8
     */
    public WEB3FeqExecDetailInfoUnit() 
    {
     
    }
}
@
