head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherSearchInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : ()åa¤ Ø\[VVXeæñ
File Name           : ÇÒEÚqîño^`[õüÍNGXg(WEB3AdminDirSecAccRegVoucherSearchInpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12  ½¶q (u) VKì¬ dlÏX fNo.098
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒEÚqîño^`[õüÍNGXg)<BR>
 * ÇÒEÚqîño^`[õüÍNGXgNXB<BR>
 * <BR>
 * @@author ½¶q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherSearchInpRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_search_inp";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * @@roseuid 466E0B6B01E0
     */
    public WEB3AdminDirSecAccRegVoucherSearchInpRequest()
    {

    }

    /**
     * YNGXgÉÎ·éX|XIuWFNgðÔ·B<BR>
     * <BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherSearchInpResponse(this);
    }
}
@
