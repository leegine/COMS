head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : SÛÁ¿o^mFNGXg(WEB3AdminSLProductRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 Ð±ì (u) VKì¬ fNo.760
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (SÛÁ¿o^mFNGXg)<BR>
 * SÛÁ¿o^mFNGXgNX<BR>
 *
 * @@author Ð±ì
 * @@version 1.0
 */
public class WEB3AdminSLProductRegistConfirmRequest extends WEB3AdminSLProductRegistCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_regist_confirm";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709141341L;

    /**
     * @@roseuid 46E890840210
     */
    public WEB3AdminSLProductRegistConfirmRequest()
    {

    }

    /**
     * NGXgf[^Ì®«`FbNðs¤B<BR>
     * <BR>
     * Pj@@super.validate()ðR[·éB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DF903F03DD
     */
    public void validate() throws WEB3BaseException
    {
        super.validate();
    }

    /**
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLProductRegistConfirmResponse(this);
    }
}
@
