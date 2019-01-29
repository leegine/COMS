head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������RCVD�L���[�Ɖ���̓��X�|���X(WEB3AdminFeqRcvdQueueReferenceInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.465
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҊO������RCVD�L���[�Ɖ���̓��X�|���X)<BR>
 * �Ǘ��ҊO������RCVD�L���[�Ɖ���̓��X�|���X�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminFeqRcvdQueueReferenceInputResponse extends WEB3GenResponse 
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
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqRcvdQueueReferenceInputResponse() 
    {
     
    }
    
    /**
     * (�X�V�҃R�[�h )<BR>
     * �X�V�҃R�[�h <BR>
     * <BR> 
     * �����O�C�������Ǘ��҂̊Ǘ��҃R�[�h���ݒ肳���B<BR>
     */
    public String updaterCode;
    
    /**
     * (�����敪�ꗗ)<BR>
     * �����敪�̔z��<BR>
     * <BR>
     * 0�F�����҂�<BR>
     * 1�F�����ς� <BR>
     * 7�F��菈����<BR>
     * 8�F�����ȗ� <BR>
     * 9�F�����G���[<BR>
     */
    public String[] transactionDivList;
    
    /**
     * (�o�H�敪�ꗗ)<BR>
     * �o�H�敪�̔z�� <BR>
     * <BR>
     * 0�F�o���ʒm<BR>
     * 1�F�o������<BR> 
     * 2�F��茋�ʈꊇ����<BR> 
     * 3�F������t<BR>
     * 4�F������t����F��<BR>
     * 5�F������t���ʈꊇ����<BR>
     */
    public String[] orderRootDivList;
    
    /**
     * (��t�敪�ꗗ)<BR>
     * ��t�敪�̔z��<BR>
     * <BR>
     * 01�F������t��<BR> 
     * 09�F������t�G���[<BR>
     * 03�F������t�ώ��<BR>
     * 11�F������<BR>
     * 19�F�����G���[<BR> 
     * 21�F����� <BR>
     * 29�F����G���[ <BR>
     * 31�F�o����<BR>
     */
    public String[] acceptDivList;
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqRcvdQueueReferenceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
    
}
@
