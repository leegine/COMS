head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : aèØ¾×(WEB3AioSecurityTransferUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 üz (u) VKì¬   
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (aèØ¾×)<BR>
 * aèØ¾×NX
 * 
 * @@author üz(u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferUnit extends Message
{    
    /**
     * (¤i^Cv)<BR>
     * ¤i^Cv<BR>
     * <BR>
     * 1F ®<BR>
     * 2F Â<BR>
     * 3F Mõ<BR>
     */
    public String instrumentsType;
    
    /**
     * (Á¿R[h)<BR>
     * Á¿R[h
     */
    public String productCode;
    
    /**
     * (Á¿¼)<BR>
     * Á¿¼
     */
    public String productName;
    
    /**
     * (ûÀæª)<BR>
     * ûÀæªi®AMÌêj<BR>
     * <BR>
     * 0F êÊ<BR>
     * 1F Áè<BR>
     * <BR>
     * ¦®AÈOÌêAnull
     */
    public String taxType;
    
    /**
     * (Ê)<BR>
     * ÛLÊ
     */
    public String changePossQuantity;
    
    /**
     * (]¿z)<BR>
     * ]¿z
     */
    public String marketValue;
    
    /**
     * (aèæª)<BR>
     * aèæª<BR>
     * <BR>
     * 1F Ûì<BR>
     * 2F ãp
     */
    public String depositDiv;
    
    /**
     * (aèØ¾×)<BR>
     * RXgN^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit
     * @@roseuid 416D1BA80187
     */
    public WEB3AioSecurityTransferUnit() 
    {
     
    }
}
@
