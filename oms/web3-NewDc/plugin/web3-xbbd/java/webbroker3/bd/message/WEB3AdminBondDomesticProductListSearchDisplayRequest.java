head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListSearchDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒàÂÁ¿êõæÊ\¦NGXg(WEB3AdminBondDomesticProductListSearchDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ünm (u) VKì¬ f192
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒàÂÁ¿êõæÊ\¦NGXg)<BR>
 * ÇÒàÂÁ¿êõæÊ\¦NGXg<BR>
 *
 * @@author ünm
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListSearchDisplayRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_list_search_display";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * @@roseuid 4691C5EC01EC
     */
    public WEB3AdminBondDomesticProductListSearchDisplayRequest()
    {

    }

    /**
     * (createX|X)<BR>
     * (createResponseÀ)<BR>
     * <BR>
     * X|XIuWFNgð¶¬µÄÔ·B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondDomesticProductListSearchDisplayResponse(this);
    }

}
@
