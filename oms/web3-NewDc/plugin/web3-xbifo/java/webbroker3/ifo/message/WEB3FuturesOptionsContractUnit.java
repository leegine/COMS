head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : Ê¾×(WEB3FuturesOptionsContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 o£ (u) VKì¬
              001: 2004/08/05 ¤Å (u) ÎoO U00021
Revesion History : 2007/06/08 ·^] (u) dlÏXfNo.640
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (Ê¾×)<BR>
 * ÊÌ¾×ð\·NX<BR>
 * @@author o£
 * @@version 1.0
 */
public class WEB3FuturesOptionsContractUnit extends Message
{
    
    /**
     * Êhc
     */
    public String id;
    
    /**
     * (ú)
     */
    public java.util.Date openDate;
    
    /**
     * (Ê)
     */
    public String contractQuantity;
    
    /**
     * (P¿)
     */
    public String contractPrice;
    
    /**
     * (ñèàz)
     */
    public String contractExecPrice;
    
    /**
     * (è¿)<BR>
     * è¿(ÁïÅ)<BR>
     */
    public String contractCommission;
    
    /**
     * (¹v)
     */
    public String income;
    
    /**
     * (¹v(oï))
     */
    public String incomeCost;
    
    /**
     * (ÔÏÊ)
     */
    public String contractOrderQuantity;
    
    /**
     * (ÏÊ)
     */
    public String settlePriority;

    /**
     * (ÔÏñèÊ)
     */
    public String contractExecQuantity;

    /**
     * (§ïæª)<BR>
     * 1F[êi[êÀ{·éïÐÌ[êÔÑÌÝÝèj@@NULLF»Ì¼<BR>
     */
    public String sessionType;

    /**
     * (Ê¾×)
     * RXgN^
     * @@roseuid 407F6E8C008C
     */
    public WEB3FuturesOptionsContractUnit() 
    {
        
    }
}
@
