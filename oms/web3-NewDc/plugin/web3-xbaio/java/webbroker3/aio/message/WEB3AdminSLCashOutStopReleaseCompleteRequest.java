head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLCashOutStopReleaseCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ØSÛ[oàâ~ð®¹NGXg(WEB3AdminSLCashOutStopReleaseCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 ·^] (u) VKì¬ dlÏXf764 f772
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ØSÛ[oàâ~ð®¹NGXg)<BR>
 * ØSÛ[oàâ~ð®¹NGXgNX<BR>
 *
 * @@author ·^]
 * @@version 1.0
 */
public class WEB3AdminSLCashOutStopReleaseCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_cash_out_stop_release_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131743L;

    /**
     * (ûÀID)<BR>
     */
    public long accountId;

    /**
     * (ÃØÔ)<BR>
     */
    public String password;

    public WEB3AdminSLCashOutStopReleaseCompleteRequest()
    {

    }

    /**
     * YNGXgÉÎ·éX|XIuWFNgðÔ·B<BR>
     * <BR>
     * @@return X|XIuWFNg
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLCashOutStopReleaseCompleteResponse(this);
    }
}
@
