head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : วาเค@@๐t{o^ฎนNGXg(WEB3AdminFPTRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 g (u) VK์ฌ
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (วาเค@@๐t{o^ฎนNGXg)<BR>
 * วาเค@@๐t{o^ฎนNGXgNX<BR>
 *
 * @@author g
 * @@version 1.0
 */
public class WEB3AdminFPTRegistCompleteRequest extends WEB3AdminFPTUpdateCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_regist_complete";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291424L;

    /**
     * O[eBeB<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTRegistCompleteRequest.class);

    /**
     * (รุิ)<BR>
     * รุิ<BR>
     */
    public String password;

    /**
     * @@roseuid 46FDDD3A0203
     */
    public WEB3AdminFPTRegistCompleteRequest()
    {

    }

    /**
     * (createX|X)<BR>
     * (createResponseภ)<BR>
     * <BR>
     * X|XIuWFNg๐ถฌตฤิทB<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTRegistCompleteResponse(this);
    }

    /**
     * NGXgf[^ฬฎซ๐`FbNท้B<BR>
     * <BR>
     * super.validate()๐R[ท้B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46F3B78003DB
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
