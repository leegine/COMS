head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.52.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : (�Ǘ��҃��j���[����Ǘ��ғo�^���̓��N�G�X�g(WEB3AdminMCAdminRegistInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���q (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����Ǘ��ғo�^���̓��N�G�X�g)<BR>
 * �Ǘ��҃��j���[����Ǘ��ғo�^���̓��N�G�X�g<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * @@roseuid 419864220167
     */
    public WEB3AdminMCAdminRegistInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198642201B5
     */
    public WEB3GenResponse createResponse() 
    {
         return new WEB3AdminMCAdminRegistInputResponse(this);
    }
}
@
