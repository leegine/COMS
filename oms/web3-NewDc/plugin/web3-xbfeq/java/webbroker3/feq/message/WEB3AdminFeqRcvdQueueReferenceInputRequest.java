head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������RCVD�L���[�Ɖ���̓��N�G�X�g(WEB3AdminFeqRcvdQueueReferenceInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҊO������RCVD�L���[�Ɖ���̓��N�G�X�g)<BR>
 * �Ǘ��ҊO������RCVD�L���[�Ɖ���̓��N�G�X�g�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminFeqRcvdQueueReferenceInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_rcvd_queue_reference_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3AdminFeqRcvdQueueReferenceInputRequest() 
    {
     
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqRcvdQueueReferenceInputResponse(this);
    }
}
@
