head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.46.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ���\�����X�|���X(WEB3AdminBondExecRefReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 �����q(���u) �V�K�쐬   
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���Ǘ��Ғ������Ɖ���\�����X�|���X)<BR>
 * ���Ǘ��Ғ������Ɖ���\�����X�|���X�N���X<BR>
 *   
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefReferenceResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_or_bond_exec_ref_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608091104L;
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
     * (���Ǘ��Ғ������Ɖ�s�ꗗ)<BR>
     * ���Ǘ��Ғ������Ɖ�s�ꗗ
     */
    public WEB3AdminORBondExecRefUnit[] orderList;
    
    /**
     * ���Ǘ��Ғ������Ɖ���\�����X�|���X<BR>
     * �R���X�g���N�^�B
     * @@roseuid 44B738A70138
     */
    public  WEB3AdminORBondExecRefReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminORBondExecRefReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
