head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptCancelInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t����F�ؓ��̓��X�|���X(WEB3AdminFeqOrderAcceptCancelInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO������������t����F�ؓ��̓��X�|���X)<BR>
 * �Ǘ��ҊO������������t����F�ؓ��̓��X�|���X�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptCancelInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�����ꗗ)<BR>
     * �O�������������ׁi�Ǘ��ҁj�̔z��
     */
    public WEB3AdminFeqExecuteGroup[] executeGroups;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��
     */
    public String totalRecords;
    
    /**
     * @@roseuid 42CE39FB02BF
     */
    public WEB3AdminFeqOrderAcceptCancelInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqOrderAcceptCancelInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
