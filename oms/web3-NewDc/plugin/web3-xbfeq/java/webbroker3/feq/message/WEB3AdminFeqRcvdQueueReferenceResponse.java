head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���X�|���X(WEB3AdminFeqRcvdQueueReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���X�|���X)<BR>
 * �Ǘ��ҊO������RCVD�L���[�Ɖ�ꗗ���X�|���X�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminFeqRcvdQueueReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_rcvd_queue_reference";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqRcvdQueueReferenceResponse() 
    {
     
    }
    
    /**
     * (�O������RCVD�L���[���ꗗ)<BR>
     * �O������RCVD�L���[���̔z��<BR>
     */
    public WEB3FeqRcvdQueueInfo[] feqRcvdQueueInfoList;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqRcvdQueueReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
    
}
@
