head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��X�|���X(WEB3AccInfoEquityCommissionCourseChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
Revesion History : 2008/08/22 ������ (���u) �d�l�ύX�E���f��No.248
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��X�|���X)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��X�|���X<BR>
 * <BR>
 * �� �ȉ��̂ǂ��炩�̏����ɓ��Ă͂܂�ꍇ�́A<BR>
 * �ύX�\�����̓t�H�[����\�����Ȃ��B<BR>
 * <BR>
 * �@@[����]<BR>
 * �@@�E�i�萔���R�[�X�ύX�\�����ꗗ == null�j�̏ꍇ<BR>
 * �@@�܂��́A<BR>
 * �@@�E�萔���R�[�X�ύX�\�����ꗗ�ɁA�i����\�t���O == true�j<BR>
 * �̍s���P�����Ȃ��ꍇ<BR>
 * <BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeInputResponse extends WEB3GenResponse
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
     * (�ύX�\�萔���R�[�X�ꗗ)<BR>
     * �ύX�\�萔���R�[�X�ꗗ<BR>
     * ���@@�،���Ђ���舵���Ă���萔���R�[�X�R�[�h�̔z��<BR>
     * <BR>
     * �萔���R�[�X�R�[�h<BR>
     * <BR>
     * 02�F�@@�藦�萔���i�X�^���_�[�h�j <BR>
     * �i����1���������{�M�p1���������@@�����e���̂݁j<BR> 
     * 03�F�@@��������v <BR>
     * �i����1�����v�{�M�p1�����v�@@�����e���̂݁j<BR> 
     * 04�F�@@����<BR> 
     * 05�F�@@�����z��<BR> 
     * 06�F�@@���z�{�b�N�X<BR> 
     * 07�F�@@����1�����v�{�M�p1��������<BR> 
     * 08�F�@@����1���������{�M�p1�����v<BR> 
     * 16�F�@@���z�{�b�N�X�i�L�����y�[���j<BR> 
     * 99�F�@@��L�ȊO�i���e���E���̂݁j<BR>
     * <BR>
     */
    public String[] changeAbleCommissionCourseList;

    /**
     * (�K�p�J�n���ꗗ)<BR>
     * �K�p�J�n���ꗗ<BR>
     * �� �萔���R�[�X�ꗗ�ƘA������B<BR>
     * ���@@�،���Ђ���舵���Ă���e�萔���R�[�X�̓K�p�J�n���z��B<BR>
     */
    public Date[] applyStartDateList;

    /**
     * (�ύX�\�����؎w����ꗗ)<BR>
     * �ύX�\�����؎w����B<BR>
     * <BR>
     * �� �萔���R�[�X�ꗗ�ƘA������B<BR>
     * ���@@�،���Ђ���舵���Ă���e�萔���R�[�X��<BR>
     * �ύX�\�����؎w����z��B<BR>
     * <BR>
     * 00�F����<BR>
     * 00�ȊO�̏ꍇ�A���t�w��B�����A�w��������ؓ��ƂȂ�B<BR>
     * <BR>
     */
    public String[] changeOfferDeadlineDateList;

    /**
     * (�ύX�\�����؎��Ԉꗗ)<BR>
     * �ύX�\�����؎���<BR>
     * <BR>
     * �� �萔���R�[�X�ꗗ�ƘA������B<BR>
     * ���@@�،���Ђ���舵���Ă���e�萔���R�[�X��<BR>
     * �ύX�\�����؎��Ԕz��B<BR>
     * <BR>
     * HHMMSS�i24h�j<BR>
     * <BR>
     */
    public String[] changeOfferDeadlineTimeList;

    /**
     * (�ύX�K�p�J�n���w��敪�ꗗ)<BR>
     * �ύX�K�p�J�n���w��敪<BR>
     * <BR>
     * �� �萔���R�[�X�ꗗ�ƘA������B<BR>
     * ���@@�،���Ђ���舵���Ă���e�萔���R�[�X��<BR>
     * �ύX�K�p�J�n���w��敪�z��B<BR>
     * <BR>
     * 1�F�@@�\�����̗����i�����c�Ɠ��j<BR>
     * 2�F�@@�\�����̗��X���i�����c�Ɠ��j<BR>
     * 9�F�@@�����w�� <BR>
     * �@@�@@�� �\��������A�ύX�K�p�J�n�����Ŏw�肵��������<BR>
     *�@@   �� �����w��̏ꍇ�A�F�ɑ����ē����l���Z�b�g<BR>
     * <BR>
     */
    public String[] changeApplyStartDateDivList;

    /**
     * (�萔���R�[�X�ύX�\�����ꗗ)<BR>
     * �萔���R�[�X�ύX�\�����ꗗ<BR>
     * <BR>
     * ���@@���Y���ڂ�null�̏ꍇ�́A<BR>
     * �h�\���ςݎ萔���R�[�X�h�t�H�[���͕\�����Ȃ��B<BR>
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] commissionCourceChangeList;

    /**
     * @@roseuid 418F39F2006D
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputResponse()
    {

    }

    /**
     * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse
     * @@roseuid 41368E0B0006
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
