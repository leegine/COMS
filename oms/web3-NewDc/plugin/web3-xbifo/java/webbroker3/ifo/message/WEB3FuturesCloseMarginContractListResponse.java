head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginContractListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæņ
File Name        : ŋwæ¨ÔĪĘęX|X(WEB3FuturesCloseMarginContractListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 įžs (u) VKėŦ
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ŋwæ¨ÔĪĘęX|X)<BR>
 * ŋwæ¨ÔĪĘęX|XNX<BR>
 * @@author įžs
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginContractListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginContractList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191514L;

    /**
     * (æøsę)<BR>
     * 1F@@2Fåã<BR>
     */
    public String marketCode;

    /**
     * (Áŋŧ)<BR>
     */
    public String productName;

    /**
     * (æĒ)<BR>
     * 1F@@2F<BR>
     */
    public String contractType;

    /**
     * (Ī)<BR>
     * 0F_@@1FPŋv@@2FPŋš@@3Fú<BR>
     */
    public String closingOrder;

    /**
     * (ÔĪĘęž×s)<BR>
     */
    public WEB3FuturesCloseMarginContractGroup[] closeMarginContractGroups;

    /**
     * @@roseuid 40F7AE1701F4
     */
    public WEB3FuturesCloseMarginContractListResponse()
    {

    }

    /**
     * RXgN^BøÅ^ĻįęŊNGXgIuWFNgđîÉ<BR>
     * X|XIuWFNgđļŦˇéB<BR>
     *<BR>
     * @@param l_request NGXgIuWFNg
         */
    protected WEB3FuturesCloseMarginContractListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
