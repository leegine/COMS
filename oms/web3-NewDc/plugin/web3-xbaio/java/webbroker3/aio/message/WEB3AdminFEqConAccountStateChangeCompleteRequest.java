head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountStateChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : OûÀJÝóµÏX®¹NGXg(WEB3AdminFEqConAccountStateChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 üE(u) VKì¬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (OûÀJÝóµÏX®¹NGXg)<BR>
 * OûÀJÝóµÏX®¹NGXgNX
 * 
 * @@author üE(u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountStateChangeCompleteRequest extends WEB3AdminFEqConAccountStateChangeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_state_change_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (ÃØÔ)<BR>
     * ÃØÔ
     */
    public String password;
    
    /**
     * @@roseuid 423554FE03A9
     */
    public WEB3AdminFEqConAccountStateChangeCompleteRequest() 
    {
     
    }
    
    /**
     * icreateResponseÌÀj<BR>
     * <BR>
     * OûÀJÝóµÏX®¹X|XIuWFNgðÔp·éB
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountStateChangeCompleteResponse(this);
    }
}
@
