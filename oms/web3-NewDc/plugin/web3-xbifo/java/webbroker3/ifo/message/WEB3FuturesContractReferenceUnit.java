head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¿wæ¨ÊÆï¾×(WEB3FuturesContractReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 Q (u) VKì¬
Revesion History : 2007/06/08 Ðì (u) dlÏXfNo.682
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (¿wæ¨ÊÆï¾×)<BR>
 * ¿wæ¨ÊÆïsNX<BR>
 *                                                                     
 * @@author Q
 * @@version 1.0
 */
public class WEB3FuturesContractReferenceUnit extends Message
{
    
    /**
     * (hc)<BR>
     * Êhc<BR>
     */
    public String id;
    
    /**
     * (Á¿R[h)<BR>
     */
    public String futProductCode;
    
    /**
     * (Á¿¼)<BR>
     */
    public String futProductName;
    
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
     * (æøsê)<BR>
     * 1F@@2Fåã<BR>
     */
    public String marketCode;
    
    /**
     * (æª)<BR>
     * 1F@@2F<BR>
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
     * 0FÏÏ@@1F¢Ï@@2FÏ<BR>
     */
    public String settlementState;
    
    /**
     * (ãà)<BR>
     */
    public String contractExecPrice;
    
    /**
     * (è¿)<BR>
     * è¿(ÁïÅ)
     */
    public String contractCommission;
    
    /**
     * (æøÅIú)<BR>
     */
    public Date lastTradingDate;
    
    /**
     * (¹v)<BR>
     */
    public String income;
    
    /**
     * (¹v(oï))
     */
    public String incomeCost;

    /**
     * (§ïæª)<BR>
     * 1F[êi[êÀ{·éïÐÌ[êÔÑÌÝÝèj@@NULLF»Ì¼<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40C0754900AB
     */
    public WEB3FuturesContractReferenceUnit() 
    {
     
    }
}
@
