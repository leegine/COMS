head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueReferenceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������SEND�L���[�Ɖ���̓��X�|���X(WEB3AdminFeqSendQueueReferenceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
Revesion History : 2007/02/02 ������ (���u) ���f�� No.341
Revesion History : 2007/02/07 ������ (���u) ���f�� No.342
Revesion History : 2008/02/01 �g�C�� (���u) ���f�� No.393
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҊO������SEND�L���[�Ɖ���̓��X�|���X)<BR>
 * �Ǘ��ҊO������SEND�L���[�Ɖ���̓��X�|���X�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminFeqSendQueueReferenceInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_send_queue_reference_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqSendQueueReferenceInputResponse() 
    {
     
    }
    
    /**
     * (�����敪�ꗗ)<BR>
     * �����敪�̔z��<BR>
     * <BR>
     * 0�F�����҂�<BR>
     * 1�F�����ς�<BR>
     * 2�F�đ��M�҂�<BR>
     * 6�F���M�������<BR>
     * 7�F�����M<BR>
     * 8�F�����ȗ�<BR>
     * 9�F�����G���[<BR>
     */
    public String[] transactionDivList;

    /**
     * (�������ꗗ)<BR>
     * �������ꗗ<BR>
     * <BR>
     * �������̔z�� <BR>
     * �������t<BR>
     * �O���c�Ɠ� <BR>
     * �O�X���c�Ɠ�<BR>
     * �O�X�X���c�Ɠ�<BR>
     */
    public Date[] orderDateList;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̔z��
     */
    public String[] marketList;
    
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqSendQueueReferenceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
    
}
@
