head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsContractReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¿wIvVÊÆï¾×(WEB3OptionsContractReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 £Ð (u) VKì¬
Revesion History : 2007/06/08 Ðì (u) dlÏXfNo.682
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (¿wIvVÊÆï¾×)<BR>
 * ¿wIvVÊÆïsNX<BR>
 * @@author £Ð
 * @@version 1.0
 */
public class WEB3OptionsContractReferenceUnit extends Message
{
    
    /**
     * (hc)<BR>
     * Êhc<BR>
     */
    public String id;
    
    /**
     * (Á¿R[h)<BR>
     */
    public String opProductCode;
    
    /**
     * (Á¿¼)<BR>
     */
    public String opProductName;
    
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
     * PFvbgIvV CFR[IvV<BR>
     */
    public String opProductType;
    
    /**
     * (sg¿i)
     */
    public String strikePrice;
    
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
     * (ú)
     */
    public Date openDate;
    
    /**
     * (Ê)
     */
    public String contractOrderQuantity;
    
    /**
     * (P¿)
     */
    public String contractPrice;
    
    /**
     * (ÏóÔæª)<BR>
     * 0FÏÏ@@1F¢Ï@@2FÏ<BR>
     */
    public String settlementState;
    
    /**
     * (ñèàz)
     */
    public String contractExecPrice;
    
    /**
     * (è¿)
     * è¿(ÁïÅ)
     */
    public String contractCommission;
    
    /**
     * (æøÅIú)
     */
    public Date lastTradingDate;
    
    /**
     * (¹v)
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
    public WEB3OptionsContractReferenceUnit() 
    {
     
    }
}
@
