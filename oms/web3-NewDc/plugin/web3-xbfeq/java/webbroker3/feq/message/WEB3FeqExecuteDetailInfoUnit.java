head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®ñèÚ×îñ(WEB3FeqExecuteDetailInfoUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 © (u) VKì¬
                 : 2005/08/01 sp(u) r[ 
Revesion History : 2008/10/02 (SRA) yO®zdlÏXÇä ifjNo.464  
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (O®ñèÚ×îñ)<BR>
 * O®ñèÚ×îñNX<BR>
 * 
 * @@author ©(u)
 * @@version 1.0
 */

public class WEB3FeqExecuteDetailInfoUnit extends Message 
{
    
    /**
     * (ónú)<BR>
     * ónú<BR>
     */
    public Date deliveryDate;
    
    /**
     * (»nónú)<BR>
     * »nónú<BR>
     */
    public Date localDeliveryDate;
    
    
    /**
     * (ñèÊ)<BR>
     * ñèÊ<BR>
     */
    public String execQuantity;
    
    /**
     * (ñèP¿)<BR>
     * ñèP¿<BR>
     */
    public String execPrice;
    
    /**
     * (ñèãà)<BR>
     * ñèãà<BR>
     */
    public String execAmount;
    
    /**
     * (ñèóÔæª)<BR>
     * ñèóÔæª<BR>
     * <BR>
     * 0F¢ñè<BR>
     * 1Fê¬§<BR>
     * 2FS¬§<BR>
     * 3Fñè(ê¬§)<BR>
     * 4Fñè(S¬§)<BR>
     */
    public String execType;
    
    /**
     * (ónãà)<BR>
     * ónãà<BR>
     */
    public String deliveryPrice;
    
    /**
     * (µÒR[h)<BR>
     * µÒR[h<BR>
     */
    public String traderCode;  
    
    /**
     * (ónãàiOÝj)<BR>
     * ónãàiOÝj<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (»nè¿)<BR>
     * »nè¿<BR>
     */
    public String localCommission;
    
    /**
     * (»næøÅ)<BR>
     * »næøÅ<BR>
     */
    public String localTradingTax;
    
    /**
     * (»Ì¼RXgP)<BR>
     * »Ì¼RXgP<BR>
     */
    public String otherCost1;
    
    /**
     * (»Ì¼RXgQ)<BR>
     * »Ì¼RXgQ<BR>
     */
    public String otherCost2;
    
    /**
     * (´Zãà)<BR>
     * ´Zãà<BR>
     */
    public String clearUpPrice;
    
    /**
     * (´ZãàiOÝj)<BR>
     * ´ZãàiOÝj<BR>
     */
    public String foreignClearUpPrice;
    
    /**
     * (àè¿)<BR>
     * àè¿<BR>
     */
    public String commission;
    
    /**
     * (àè¿ÁïÅ)<BR>
     * àè¿ÁïÅ<BR>
     */
    public String commissionConsumptionTax;
    
    /**
     * (àè¿iOÝj)<BR>
     * àè¿iOÝj<BR>
     */
    public String foreignCommission;
    
    /**
     * (àè¿ÁïÅiOÝj)<BR>
     * àè¿ÁïÅiOÝj<BR>
     */
    public String foreignCommissionConsumptionTax;
    
    /**
     * (ñè¾×ê)<BR>
     * O®ñèÚ×iÇÒjÌzñ<BR>
     */
    public WEB3FeqExecDetailInfoUnit[] execDetailList;
    
    /**
     * (ñè×Ö[g)<BR>
     * ñè×Ö[g<BR>
     */
    public String execExchangeRate;
    
    /**
     * (O®ñèÚ×îñ)<BR>
     * RXgN^<BR>
     * @@roseuid 42A537780118
     */
    public WEB3FeqExecuteDetailInfoUnit() 
    {
     
    }
}
@
