head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¿wæ¨IvVcÆï¾×NX(WEB3FuturesOptionsDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 àò VKì¬         
Revesion History : 2007/06/08 ·^] (u) dlÏXfNo.640
*/
package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (¿wæ¨IvVcÆï¾×)<BR>
 * ¿wæ¨IvVcÆï¾×NX
 * @@author àò
 * @@version 1.0
 */
public class WEB3FuturesOptionsDetailUnit extends Message
{
   
    /**
     * (hc)<BR>
     * Êhc<BR>
     */
    public String id;
    
    /**
     * (Á¿R[h)<BR>
     * æ¨OPÁ¿R[h<BR>
     */
    public String productCode;
    
    /**
     * (Á¿¼)<BR>
     * æ¨OPÁ¿¼Ì<BR>
     */
    public String productName;
    
    /**
     * (wíÊ)<BR>
     * 0005FTOPIX@@0018Fúo225@@0016Fúo300@@0019F~júo225<BR>
     */
    public String targetProductCode;
    
    /**
     * (À)<BR>
     * YYYYMM`®<BR>
     */
    public String delivaryMonth;
    
    /**
     * (IvV¤iæª)<BR>
     * FFæ¨  PFvbgIvV  CFR[IvV<BR>
     */
    public String opProductType;
 
    /**
     * (sg¿i)<BR>
     */    
    public String strikePrice;
    
    /**
     * (æøsê)<BR>
     * 1F@@2Fåã
     */
    public String marketCode;
    
    /**
     * (æª)<BR>
     * 1F@@2F
     */
    public String contractType;
    
    /**
     * (ú)<BR>
     */
    public Date openDate;
    
    /**
     * (Ê)<BR>
     */
    public String contractOrderQuantity;
    
    /**
     * (P¿)<BR>
     */
    public String contractPrice;
    
    /**
     * (ÏóÔæª)<BR>
     * 1F¢Ï@@2FÏ<BR>
     */
    public String settlementState;
    
    /**
     * (ñèàz)<BR>
     */
    public String contractExecPrice;
    
    /**
     * (è¿)<BR>
     * è¿(ÁïÅ)<BR>
     */
    public String contractCommission;
    
    /**
     * (æøÅIú)<BR>
     */
    public Date lastTradingDate;
    
    /**
     * (¿)<BR>
     */
    public String currentPrice = null;
    
    /**
     * (Oúä)<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (¿æ¾Ô)<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (¹v)<BR>
     */
    public String income = null;
    
    /**
     * (¹v(oï))<BR>
     */
    public String incomeCost = null;

    /**
     * (§ïæª)<BR>
     * 1F[êi[êÀ{·éïÐÌ[êÔÑÌÝÝèj@@NULLF»Ì¼<BR>
     */
    public String sessionType;
}
@
