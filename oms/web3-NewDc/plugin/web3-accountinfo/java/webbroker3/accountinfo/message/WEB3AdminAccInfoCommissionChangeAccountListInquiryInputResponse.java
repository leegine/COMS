head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ڽ��ݽ(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
Revesion History : 2008/08/22 ������ (���u) �d�l�ύX�E���f��No.247
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ڽ��ݽ)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ڽ��ݽ<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountListInquiryInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082143L;

    /**
     * (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * ���@@��ʂɕ\�����鏉���l�B<BR>
     */
    public Date trialStartDate;

    /**
     * (�萔���R�[�X�ꗗ)<BR>
     * �ύX�\�萔���R�[�X�ꗗ<BR>
     * <BR>
     * ���@@�،���Ђ���舵���Ă���萔���R�[�X�R�[�h�̔z�� <BR>
     * <BR>
     * �萔���R�[�X�R�[�h <BR>
     * <BR>
     * 02�F�@@�藦�萔���i�X�^���_�[�h�j <BR>
     * �i����1���������{�M�p1���������@@�����e���̂݁j <BR>
     * 03�F�@@��������v <BR>
     * �i����1�����v�{�M�p1�����v�@@�����e���̂݁j<BR> 
     * 04�F�@@���� <BR>
     * 05�F�@@�����z�� <BR>
     * 06�F�@@���z�{�b�N�X <BR>
     * 07�F�@@����1�����v�{�M�p1��������<BR> 
     * 08�F�@@����1���������{�M�p1�����v<BR>
     * 16�F�@@���z�{�b�N�X�i�L�����y�[���j <BR>
     * 99�F�@@��L�ȊO�i���e���E���̂݁j <BR>
     * <BR>
     */
    public String[] commissionCourseList;

    /**
     * @@roseuid 418F386A003E
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse()
    {

    }

    /**
     * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse
     * @@roseuid 4151103000B9
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
