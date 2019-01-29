head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�ֈꗗ���X�|���X(WEB3AioSecurityTransferListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�،��U�ֈꗗ���X�|���X)<BR>
 * �،��U�ֈꗗ���X�|���X�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412070931L; 
    
    /**
     * (�a��،��ꗗ)<BR>
     * �a��،����ׂ̔z��
     */
    public WEB3AioSecurityTransferUnit[] securityTransfer;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ԍ�
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
     * (�����ꗗ)<BR>
     * �����p�����̃v���_�E�����j���[�Ɏg�p����ۗL�������ׂ̔z��
     */
    public WEB3AioSecurityTransferProductCodeNameUnit[] productCodeNames;
    
    /**
     * @@roseuid 41B031A100CB
     */
    public WEB3AioSecurityTransferListResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioSecurityTransferListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
