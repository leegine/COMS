head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¿wæ¨VK¶Á¿IðæÊNGXgNX(WEB3FuturesProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 àò (u) VKì¬
                 : 2006/08/18 sp (u) fNo.536
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (¿wæ¨VK¶Á¿IðæÊX|X)<BR>
 * ¿wæ¨VK¶Á¿IðæÊX|XNX<BR>
 * 
 * @@author àò
 * @@version 1.0 
 */
public class WEB3FuturesProductSelectResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_ProductSelect";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201858L;
    
    /**
     * (VKÂ\z)<BR>
     */
    public String futTradingPower;
    
    /**
     * (æøsêê)<BR>
     * 1F@@2Fåã@@3F¼Ã®<BR>
     */
    public String[] marketList;
    
    /**
     * (wíÊê)<BR>
     * 0005FTOPIX@@0018Fúo225@@0016Fúo300@@0019F~júo225 <BR>
     */
    public String[] targetProductList;
    
    /**
     * (Àê)<BR>
     * elÍYYYYMM`®<BR>
     */
    public String[] delivaryMonthList;
    
    /**
     * (æøI¹x¶¾)<BR>
     * ÂÇÔßÌsêª êÎA»ÌR[hði[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 40F7AE0C0177
     */
    public WEB3FuturesProductSelectResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
