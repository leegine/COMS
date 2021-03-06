head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.56.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : ()ๅaค ุ\[VVXeๆ๑
File Name          : (วาj[งไวาo^ฎนNGXg(WEB3AdminMCAdminRegistCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 คq (u) VK์ฌ
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * (วาj[งไวาo^ฎนNGXg)<BR>
 * วาj[งไวาo^ฎนNGXg<BR>
 * @@author คq
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistCompleteRequest extends WEB3GenRequest 
{
    /**
     * Ooอ[eBeBB
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (รุิ)<BR>
     * รุิ<BR>
     * <BR>
     */
    public String password;
    
    /**
     * (วาo^๎๑)<BR>
     * วาo^๎๑<BR>
     */
    public WEB3AdminMCAdminRegistUnit adminRegistUnit;
    
    /**
     * @@roseuid 419864210251
     */
    public WEB3AdminMCAdminRegistCompleteRequest() 
    {
     
    }
    
    /**
     * NGXgf[^ฬฎซ๐`FbNท้B<BR>
     * <BR>
     * Pj@@วาo^๎๑ฬ`FbN<BR>
     * @@P|Pj@@วาo^๎๑.validate()๐R[ท้B<BR>
     * @@P|Qj@@วาo^๎๑.validatepX[h()๐R[ท้B<BR>
     * <BR>
     * @@roseuid 417CB59A0180
     */
    public void validate() throws WEB3BaseException
    {
         final  String  STR_METHOD_NAME = " validate()";
         log.entering(STR_METHOD_NAME);
         //Pj@@วาo^๎๑ฬ`FbN
         adminRegistUnit.validate();
         adminRegistUnit.validatePassword();
         
         log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 419864210271
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminRegistCompleteResponse(this);
    }
}
@
