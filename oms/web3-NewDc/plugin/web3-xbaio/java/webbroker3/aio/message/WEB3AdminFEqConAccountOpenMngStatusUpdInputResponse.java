head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��X�e�[�^�X�X�V���̓��X�|���X(WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������J�݊Ǘ��X�e�[�^�X�X�V���̓��X�|���X)<BR>
 * �O�������J�݊Ǘ��X�e�[�^�X�X�V���̓��X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_status_upd_input";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (���O�i���j)<BR>
     * �O����������p�̖��O�i���j
     */
    public String accountFamilyName;
    
    /**
     * (���O�i���j)<BR>
     * �O����������p�̖��O�i���j
     */
    public String accountName;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X
     */
    public String mailAddress;
    
    /**
     * (�O�������ԍ�)<BR>
     * �O����������p�̌����ԍ�<BR>
     * <BR>
     * �����J�ݏ󋵋敪��0(�����J�ݒ�)�܂���2(�����J�݃G���[�j�̏ꍇ��null
     */
    public String fstkAccountCode;
    
    /**
     * (�X�e�[�^�X�敪)<BR>
     * 0�F�����J�ݒ�<BR>
     * 1�F�����J�݊���<BR>
     * 2�F�����J�݃G���[<BR>
     * 9�F�폜
     */
    public String statusDiv;
    
    /**
     * @@roseuid 423552E9007D
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
