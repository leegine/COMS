head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��N�G�X�g(WEB3AccInfoEquityCommissionCourseChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
Revesion History : 2008/08/21  �k�v�u (���u) �d�l�ύX�E���f��No.245
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��N�G�X�g)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��N�G�X�g<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AccInfoEquityCommissionCourseChangeInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082158L;

    /**
     * (�ύX�O�萔���R�[�X)<BR>
     * �萔���R�[�X�i�萔���R�[�X�R�[�h�j<BR>
     * <BR>
     * 02�F�@@�藦�萔���i�X�^���_�[�h�j<BR>
     * �i����1���������{�M�p1���������@@�����e���̂݁j<BR>
     * 03�F�@@��������v<BR>
     * �i����1�����v�{�M�p1�����v�@@�����e���̂݁j<BR>
     * 04�F�@@����<BR>
     * 05�F�@@�����z��<BR>
     * 06�F�@@���z�{�b�N�X<BR>
     * 07�F�@@����1�����v�{�M�p1��������<BR>
     * 08�F�@@����1���������{�M�p1�����v<BR>
     * 16�F�@@���z�{�b�N�X(�L�����y�[��)�@@<BR>
     * 99�F�@@��L�ȊO�i���e���E���̂݁j<BR>
     * <BR>
     * ���@@�e�R�[�h�̖��̂ɂ��ẮA�،���Ђɂ���ĈႤ�B<BR>
     * �@@�@@Web�w�ɂāA���̂ɕϊ�����B<BR>
     */
    public String beforCommissionCourse;

    /**
     * @@roseuid 418F39F1036B
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoEquityCommissionCourseChangeInputResponse(this);
    }
}
@
