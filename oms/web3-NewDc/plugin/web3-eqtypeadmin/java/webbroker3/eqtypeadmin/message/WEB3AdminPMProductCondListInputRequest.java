head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������������\��ꗗ���̓��N�G�X�g(WEB3AdminPMProductCondListInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������������\��ꗗ���̓��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE�������������\��ꗗ���̓��N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminPMProductCondListInputRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondListInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_list_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondListInputRequest.class);

    /**
     * @@roseuid 41FD91F1035B
     */
    public WEB3AdminPMProductCondListInputRequest()
    {

    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMProductCondListInputResponse(this);
    }
}
@
