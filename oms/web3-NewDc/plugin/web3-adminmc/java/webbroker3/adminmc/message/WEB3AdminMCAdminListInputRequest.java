head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��N�G�X�g(WEB3AdminMCAdminListInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  �� �� �@@ (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��҈ꗗ���̓��N�G�X�g<BR>
 * @@author �����@@
 * @@version 1.0
 */

public class WEB3AdminMCAdminListInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminListInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * @@roseuid 419864190109
     */
    public WEB3AdminMCAdminListInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 419864190119
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminListInputResponse(this);
    }
}
@
