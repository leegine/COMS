head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®ë¿P¿o^mFX|X(WEB3FeqBookPriceConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/16 Äog(u) VKì¬ fNo.373
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (O®ë¿P¿o^mFX|X)<BR>
 * O®ë¿P¿o^mFX|X<BR>
 * <BR>
 * @@author Äog
 * @@version 1.0
 */
public class WEB3FeqBookPriceConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "feq_book_price_confirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200801151256L;

    public WEB3FeqBookPriceConfirmResponse()
    {

    }

    /**
     * (ÏXãTZë¿P¿)<BR>
     * ÏXãTZë¿P¿<BR>
     */
    public String aftBookPrice;

    public WEB3FeqBookPriceConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
