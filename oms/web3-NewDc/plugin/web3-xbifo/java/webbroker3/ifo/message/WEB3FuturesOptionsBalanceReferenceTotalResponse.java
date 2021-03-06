head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¿wæ¨IvVcÆïcvX|XNX(WEB3FuturesOptionsBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 àò VKì¬   
                 : 2006/07/24 © dlÏXfNo.526A531 
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (¿wæ¨IvVcÆïcvX|X)<BR>
 * ¿wæ¨IvVcÆïcvX|XNX<BR>
 * @@author àò
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceTotalResponse extends WEB3GenResponse 
{
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReferenceTotal";
    
    /**
     * vbgÊ¿z
     */
    public String putBuyCurrentPrice = "0";
    
    /**
     * R[Ê¿z
     */
    public String callBuyCurrentPrice = "0";
    
    /**
     * Ê¿z
     */
    public String buyCurrentPrice = "0";
    
    /**
     * vbgÊ¿z
     */
    public String putSellCurrentPrice = "0";
    
    /**
     * R[Ê¿z
     */
    public String callSellCurrentPrice = "0";
    
    /**
     * Ê¿z
     */
    public String sellCurrentPrice = "0";
    
    /**
     * vbgÊÊ
     */
    public String putBuyTotalQuantity = "0";
    
    /**
     * R[ÊÊ
     */
    public String callBuyTotalQuantity = "0";
    
    /**
     * ÊÊ
     */
    public String buyTotalQuantity = "0";
    
    /**
     * vbgÊÊ
     */
    public String putSellTotalQuantity = "0";
    
    /**
     * R[ÊÊ
     */
    public String callSellTotalQuantity = "0";
    
    /**
     * ÊÊ
     */
    public String sellTotalQuantity = "0";
    
    /**
     * ÊÊ
     */
    public String totalQuantity = "0";
    
    /**
     * vbgÊ]¿¹vv
     */
    public String putBuyAssetProfitLoss = "0";
    
    /**
     * R[Ê]¿¹vv
     */
    public String callBuyAssetProfitLoss = "0";
    
    /**
     * Ê]¿¹vv
     */
    public String buyAssetProfitLoss = "0";
    
    /**
     * vbgÊ]¿¹vv
     */
    public String putSellAssetProfitLoss = "0";
    
    /**
     * R[Ê]¿¹vv
     */
    public String callSellAssetProfitLoss = "0";
    
    /**
     * Ê]¿¹vv
     */
    public String sellAssetProfitLoss = "0";
    
    /**
     * ]¿¹vv
     */
    public String appraisalProfitLoss = "0";
    
    /**
     * vbgÊ]¿¹vv(oï)
     */
    public String putBuyAssetProfitLossCost = "0";
    
    /**
     * R[Ê]¿¹vv(oï)
     */
    public String callBuyAssetProfitLossCost = "0";
    
    /**
     * Ê]¿¹vv(oï)
     */
    public String buyAssetProfitLossCost = "0";
    
    /**
     * vbgÊ]¿¹vv(oï)
     */
    public String putSellAssetProfitLossCost = "0";
    
    /**
     * R[Ê]¿¹vv(oï)
     */
    public String callSellAssetProfitLossCost = "0";
    
    /**
     * Ê]¿¹vv(oï)
     */
    public String sellAssetProfitLossCost = "0";
    
    /**
     * ]¿¹vv(oï)
     */
    public String appraisalProfitLossCost = "0";
    
    /**
     * (wÊcv)<BR>
     * ¿wæ¨IvVwÊcvÌzñ<BR>
     */
    public WEB3FuturesOptionsBalRefTotalParIndexUnit[] futuresOptionsBalRefTotalParIndexUnits;
    
    /**
     * RXgN^BøÅ^¦çê½NGXgIuWFNgðîÉ<BR>
     * X|XIuWFNgð¶¬·éB<BR>
     *<BR>
     * @@param l_request NGXgIuWFNg
     */
    protected WEB3FuturesOptionsBalanceReferenceTotalResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
