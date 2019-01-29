head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���N�G�X�g(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
Revesion History : 2008/08/21  �k�v�u (���u) �d�l�ύX�E���f��No.245
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommissionCourseDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���N�G�X�g)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���N�G�X�g<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeConfirmRequest extends WEB3GenRequest
{

    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082159L;

    /**
     * (�萔���R�[�X)<BR>
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
    public String commissionCourse;

    /**
     * @@roseuid 418F39F101B5
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoEquityCommissionCourseChangeConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�萔���R�[�X�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01095<BR>
     * �@@�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01096<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E9EEE0024
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�萔���R�[�X�̃`�F�b�N
          //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
          //         class: WEB3BusinessLayerException<BR>
          //         tag:   BUSINESS_ERROR_01095<BR>
        if (this.commissionCourse == null || "".equals(this.commissionCourse))
        {             
            //��O
            log.debug("[�萔���R�[�X] = " + commissionCourse);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01095,
                this.getClass().getName() + STR_METHOD_NAME, "�萔���R�[�X�����͂̏ꍇ");
        }
          //�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����
        if (!(WEB3CommissionCourseDivDef.FIXED_RATE_COMMISSION_STANDARD).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EXECUTED_TURNOVER_COUNT).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EXECUTED_TIMES).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.FIXED_AMOUNT).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.SMALL_AMOUNT_BOX).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EQUITY_ONE_DAY_TOTAL_ADD_MARGIN_ONE_DAY_ORDER).equals(
                this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EQUITY_ONE_DAY_ORDER_ADD_MARGIN_ONE_DAY_TOTAL).equals(
                this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.SMALL_AMOUNT_BOX_CAMPAIGN).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.OTHER).equals(this.commissionCourse))
        {
            //��O
            log.debug("[�萔���R�[�X] = " + commissionCourse);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01096,
                this.getClass().getName() + STR_METHOD_NAME,"�萔���R�[�X�s���ȃR�[�h�l�̏ꍇ");
        }
        log.exiting(STR_METHOD_NAME);

    }
    
}
@
