head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�����ʃ��X�|���X�N���X(WEB3AioCashinNoticeCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����A�����ʃ��X�|���X)<BR>
 * �����A�����ʃ��X�|���X�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCommonResponse extends WEB3GenResponse 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111452L;    
    /**
     * (�ڋq����)<BR>
     * �ڋq�̖���
     */
    public String accountName;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�̌ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�U����)<BR>
     * ��ʂɂē��͂��ꂽ�U����
     */
    public String transferDate;
    
    /**
     * (�U������Z�@@�փR�[�h)<BR>
     * ��ʂɂđI�����ꂽ�U������Z�@@�ւ̃R�[�h
     */
    public String finInstitutionCode;
    
    /**
     * (�U������Z�@@�֖���)<BR>
     * ��ʂɂđI�����ꂽ�U������Z�@@�ւ̖���
     */
    public String finInstitutionName;
    
    /**
     * (�����z)<BR>
     * ��ʂɂē��͂��ꂽ�����z
     */
    public String cashinAmt;
    
    /**
     * (���[���A�h���X)<BR>
     * ��ʂɂē��͂��ꂽ���[���A�h���X
     */
    public String emailAddress;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashinNoticeCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
