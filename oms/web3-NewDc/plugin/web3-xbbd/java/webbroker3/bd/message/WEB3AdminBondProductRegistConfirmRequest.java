head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : วายมฟo^mFNGXg(WEB3AdminBondProductRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ๆโัQ (u) VK์ฌ
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (วายมฟo^mFNGXg)<BR>
 * วายมฟo^mFNGXgNX
 * 
 * @@author ๆโัQ
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistConfirmRequest extends WEB3AdminBondProductRegistCommonRequest
{
    /**
     *@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_confirm";

    /**
     *@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * @@roseuid 44E3363C035B
     */
    public WEB3AdminBondProductRegistConfirmRequest() 
    {
     
    }

    /**
     * (createX|X)<BR>
     * (createResponseภ)<BR>
     * <BR>
     * ยมฟo^mFX|X๐ถฌติท
     * @@return WEB3GenResponse
     * @@roseuid 44B61F0E00B9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondProductRegistConfirmResponse(this);
    }
}
@
