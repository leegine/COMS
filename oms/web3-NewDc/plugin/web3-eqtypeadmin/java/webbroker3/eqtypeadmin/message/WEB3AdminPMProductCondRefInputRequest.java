head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������Ɖ�������̓��N�G�X�g(WEB3AdminPMProductCondRefInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������������Ɖ�������̓��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�������������Ɖ�������̓��N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondRefInputRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondRefInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_ref_input";
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondRefInputRequest.class);

    /**
     * @@roseuid 41FD8E74032C
     */
    public WEB3AdminPMProductCondRefInputRequest()
    {

    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMProductCondRefInputResponse(this);
    }
}
@
