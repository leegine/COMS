head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.56.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : วาเยXสๅgวmFNGXg(WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 g (u) VK์ฌ dlฯXEfNo.215
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (วาเยXสๅgวmFNGXg)<BR>
 * วาเยXสๅgวmFNGXg<BR>
 * <BR>
 * @@author g
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageConfirmRequest
    extends WEB3AdminBondDomesticRecruitLimitManageCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_recruit_limit_manage_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231848L;

    /**
     * (วาเยXสๅgวmFNGXg)<BR>
     * RXgN^
     * @@roseuid 4684ABAF0033
     */
    public WEB3AdminBondDomesticRecruitLimitManageConfirmRequest()
    {

    }

    /**
     * (createX|X)<BR>
     * (createResponseภ)<BR>
     * <BR>
     * วาเยXสๅgวmFX|X๐ถฌติท
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondDomesticRecruitLimitManageConfirmResponse(this);
    }
}
@
