head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��ꗗ���X�|���X(WEB3AdminFEqConAccountOpenMngListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������J�݊Ǘ��ꗗ���X�|���X)<BR>
 * �O�������J�݊Ǘ��ꗗ���X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AdminFEqConAccountOpenMngListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171814L;
    
    /**
     * (�O�������J�ݐ\���ꗗ)<BR>
     * �O�������J�ݐ\�����ׂ̔z��
     */
    public WEB3FEqConAccountOpenApplyUnit[] fstkAccountOpenApplyList;
    
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
     * @@roseuid 423552E801B5
     */
    public WEB3AdminFEqConAccountOpenMngListResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFEqConAccountOpenMngListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
