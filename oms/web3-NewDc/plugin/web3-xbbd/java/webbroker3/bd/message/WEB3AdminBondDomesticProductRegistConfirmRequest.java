head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒàÂÁ¿o^mFNGXg(WEB3AdminBondDomesticProductRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ünm (u) VKì¬ f192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒàÂÁ¿o^mFNGXg)<BR>
 * ÇÒàÂÁ¿o^mFNGXg<BR>
 *
 * @@author ünm
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistConfirmRequest extends WEB3AdminBondDomesticProductRegistCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_regist_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * @@roseuid 4691C5EC0111
     */
    public WEB3AdminBondDomesticProductRegistConfirmRequest()
    {

    }

    /**
     * (createX|X)<BR>
     * (createResponseÀ)<BR>
     * <BR>
     * X|XIuWFNgð¶¬µÄÔ·B
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondDomesticProductRegistConfirmResponse(this);
    }

}
@
