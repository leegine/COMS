head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÂpmFX|X(WEB3BondSellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 «ô (u) VKì¬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÂpmFX|X)<BR>
 * ÂpmFX|X<BR>
 * <BR>
 * @@author «ô
 * @@version 1.0
 */
public class WEB3BondSellConfirmResponse extends WEB3GenResponse 
{
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellConfirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;  
    
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
     * (TZónãàiOÝj)<BR>
     * TZónãàiOÝj<BR>
     */
    public String foreignEstDeliveryPrice;
    
    /**
     * (TZónãài~Ýj)<BR>
     * TZónãài~Ýj<BR>
     */
    public String yenEstDeliveryPrice;
    
    /**
     * (¶ID)<BR>
     * ¶hc<BR>
     */
    public String orderId;
    
    /**
     * (mF­ú)<BR>
     * mF­ú<BR>
     */
    public Date checkDate;
    
    /**
     * (ÂpmFX|X)<BR>
     * RXgN^<BR>
     * @@roseuid 44D7FE880269
     */
    public WEB3BondSellConfirmResponse() 
    {
     
    }
    
    /**
     * RXgN^B<BR>
     * wè³ê½NGXgIuWFNgÉÎ·éX|XIuWFNgð¶¬·éB<BR>
     * @@param l_request - NGXgIuWFNg
     */
    public WEB3BondSellConfirmResponse(WEB3GenRequest l_request)
    {   
        super(l_request);   
    }
}
@
