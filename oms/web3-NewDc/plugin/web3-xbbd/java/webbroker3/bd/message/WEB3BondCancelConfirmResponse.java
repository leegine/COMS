head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÂæÁmFX|X(WEB3BondCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ü· (u) VKì¬
Revesion History : 2007/07/25 Óù (u) fNo.220
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ÂæÁmFX|X)<BR>
 * ÂæÁmFX|X
 * 
 * @@author ü·
 * @@version 1.0
 */
public class WEB3BondCancelConfirmResponse extends WEB3GenResponse 
{

    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_cancel_confirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;
    
    /**
     * (ÊÝR[h)<BR>
     * ÊÝR[h<BR>
     */
    public String currencyCode;
    
    /**
     * (P¿)<BR>
     * P¿<BR>
     */
    public String buySellPrice;
    
    /**
     * (¦)<BR>
     * ¦<BR>
     */
    public String coupon;
    
    /**
     * (­sú)<BR>
     * ­sú<BR>
     */
    public Date issueDate;
    
    /**
     * (NÔ¥ñ)<BR>
     * NÔ¥ñ<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (¥úP)<BR>
     * ¥úP<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (¥úQ)<BR>
     * ¥úQ<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (Òú)<BR>
     * Òú<BR>
     */
    public Date maturityDate;
    
    /**
     * (æøæª)<BR>
     * æøæª <BR>
     *<BR>
     *1Få <BR>
     *2Ft <BR>
     *3Fp <BR>
     */
    public String stateDiv;
    
    /**
     * (Ïæª)<BR>
     * Ïæª <BR>
     * <BR>
     * 1F~Ý <BR>
     * 2FOÝ<BR>
     */
    public String settleDiv;
    
    /**
     * (×Ö[g)<BR>
     * ×Ö[g <BR>
     */
    public String fxRate;
    
    /**
     * (zÊàz)<BR>
     * zÊàz<BR>
     */
    public String faceAmount;
    
    /**
     * (ãàiOÝj)<BR>
     * ãàiOÝj<BR>
     */
    public String foreignTradePrice;
    
    /**
     * (ãài~Ýj)<BR>
     * ãài~Ýj<BR>
     */
    public String yenTradePrice;
    
    /**
     * (oßqiOÝj)<BR>
     * oßqiOÝj<BR>
     */
    public String foreignAccruedInterest;
    
    /**
     * (oßqi~Ýj)<BR>
     * oßqi~Ýj<BR>
     */
    public String yenAccruedInterest;
    
    /**
     * (ónãàiOÝj)<BR>
     * ónãàiOÝj<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (ónãài~Ýj)<BR>
     * ónãài~Ýj<BR>
     */
    public String yenDeliveryPrice;
    
    /**
     * (¶ú)<BR>
     * ¶ú<BR>
     */
    public Date orderDate;
    
    /**
     * (­ú)<BR>
     * ­ú<BR>
     */
    public Date orderBizDate;

    /**
     * (ñèú)<BR>
     * ñèú<BR>
     */
    public Date executionUpdateDate;

    /**
     * (ónú)<BR>
     * ónú<BR>
     */
    public Date deliveryDate;

    /**
     * (Ðîæª)<BR>
     * Ðîæª <BR>
     * <BR>
     * PF¼Úæø <BR>
     * QFPÐî <BR>
     * RF¤iÐî <BR>
     * SFîæø<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (ÐîXR[h)<BR>
     * ÐîXR[h<BR>
     */
    public String introduceStoreCode;
    
    /**
     * (ÂæÁmFX|X)<BR>
     * RXgN^<BR> 
     */
    public WEB3BondCancelConfirmResponse()
    {
    	
    }
    
    /**
     * RXgN^B<BR>
     * wè³ê½NGXgIuWFNgÉÎ·éX|XIuWFNgð¶¬·éB<BR>
     * @@param l_request - NGXgIuWFNg
     */
    public WEB3BondCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
    
}
@
