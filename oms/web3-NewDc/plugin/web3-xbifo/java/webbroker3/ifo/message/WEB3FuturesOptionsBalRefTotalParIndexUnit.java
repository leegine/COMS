head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalRefTotalParIndexUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : Ώwζ¨IvVwΚcv(WEB3FuturesOptionsBalRefTotalParIndexUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 ό·(u) VKμ¬
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Ώwζ¨IvVwΚcv)<BR>
 * Ώwζ¨IvVwΚcvNX<BR>
 * 
 * @@author ό·(u)
 * @@version 1.0
 */
public class WEB3FuturesOptionsBalRefTotalParIndexUnit extends Message
{
    /**
     * (wνΚ)<BR>
     * 0005FTOPIX@@0018Fϊo225@@0016Fϊo300@@0019F~jϊo225<BR>
     */
    public String targetProductCode;
    
    /**
     * (vbgΚΏz)<BR>
     * wΚvbgΚΏz<BR>
     */
    public String putBuyCurrentPrice = "0";
    
    /**
     * (R[ΚΏz)<BR>
     * wΚR[ΚΏz<BR>
     */
    public String callBuyCurrentPrice = "0";
    
    /**
     * (ΚΏz)<BR>
     * wΚΚΏz<BR>
     */
    public String buyCurrentPrice = "0";
    
    /**
     * (vbgΚΏz)<BR>
     * wΚvbgΚΏz<BR>
     */
    public String putSellCurrentPrice = "0";
    
    /**
     * (R[ΚΏz)<BR>
     * wΚR[ΚΏz<BR>
     */
    public String callSellCurrentPrice = "0";
    
    /**
     * (ΚΏz)<BR>
     * wΚΚΏz<BR>
     */
    public String sellCurrentPrice = "0";
    
    /**
     * (vbgΚΚ)<BR>
     * wΚvbgΚΚ<BR>
     */
    public String putBuyTotalQuantity = "0";
    
    /**
     * (R[ΚΚ)<BR>
     * wΚR[ΚΚ<BR>
     */
    public String callBuyTotalQuantity = "0";
    
    /**
     * (ΚΚ)<BR>
     * wΚΚΚ <BR>
     */
    public String buyTotalQuantity = "0";
    
    /**
     * (vbgΚΚ)<BR>
     * wΚvbgΚΚ<BR>
     */
    public String putSellTotalQuantity = "0";
    
    /**
     * (R[ΚΚ)<BR>
     * wΚR[ΚΚ<BR>
     */
    public String callSellTotalQuantity = "0";
    
    /**
     * (ΚΚ)<BR>
     * wΚΚΚ<BR>
     */
    public String sellTotalQuantity = "0";
    
    /**
     * (ΚΚ)<BR>
     * wΚΚΚ<BR>
     */
    public String totalQuantity = "0";
    
    /**
     * (vbgΚ]ΏΉvv)<BR>
     * wΚvbgΚ]ΏΉvv<BR>
     */
    public String putBuyAssetProfitLoss = "0";
    
    /**
     * (R[Κ]ΏΉvv)<BR>
     * wΚR[Κ]ΏΉvv<BR>
     */
    public String callBuyAssetProfitLoss = "0";
    
    /**
     * (Κ]ΏΉvv)<BR>
     * wΚΚ]ΏΉvv<BR>
     */
    public String buyAssetProfitLoss = "0";
    
    /**
     * (vbgΚ]ΏΉvv)<BR>
     * wΚvbgΚ]ΏΉvv<BR>
     */
    public String putSellAssetProfitLoss = "0";
    
    /**
     * (R[Κ]ΏΉvv)<BR>
     * wΚR[Κ]ΏΉvv<BR>
     */
    public String callSellAssetProfitLoss = "0";
    
    /**
     * (Κ]ΏΉvv)<BR>
     * wΚΚ]ΏΉvv<BR>
     */
    public String sellAssetProfitLoss = "0";
    
    /**
     * (]ΏΉvv)<BR>
     * wΚ]ΏΉvv<BR>
     */
    public String appraisalProfitLoss = "0";
    
    /**
     * (vbgΚ]ΏΉvv(oο))<BR>
     * wΚvbgΚ]ΏΉvv(oο)<BR>
     */
    public String putBuyAssetProfitLossCost = "0";
    
    /**
     * (R[Κ]ΏΉvv(oο))<BR>
     * wΚR[Κ]ΏΉvv(oο)<BR>
     */
    public String callBuyAssetProfitLossCost = "0";
    
    /**
     * (Κ]ΏΉvv(oο))<BR>
     * wΚΚ]ΏΉvv(oο)<BR>
     */
    public String buyAssetProfitLossCost = "0";
    
    /**
     * (vbgΚ]ΏΉvv(oο))<BR>
     * wΚvbgΚ]ΏΉvv(oο)<BR>
     */
    public String putSellAssetProfitLossCost = "0";
    
    /**
     * (R[Κ]ΏΉvv(oο))<BR>
     * wΚR[Κ]ΏΉvv(oο)<BR>
     */
    public String callSellAssetProfitLossCost = "0";
    
    /**
     * (Κ]ΏΉvv(oο))<BR>
     * wΚΚ]ΏΉvv(oο)<BR>
     */
    public String sellAssetProfitLossCost = "0";
    
    /**
     * (]ΏΉvv(oο))<BR>
     * wΚ]ΏΉvv(oο)<BR>
     */
    public String appraisalProfitLossCost = "0";
}
@
