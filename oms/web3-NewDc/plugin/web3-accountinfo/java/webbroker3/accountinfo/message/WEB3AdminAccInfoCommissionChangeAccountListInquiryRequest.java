head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ظ���(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
Revesion History : 2008/08/21  �k�v�u (���u) �d�l�ύX�E���f��No.245
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommissionCourseDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ظ���)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ظ���<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest extends WEB3GenRequest
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.class);
 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountListInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082142L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     */
    public Date trialStartDate;

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
     * ���@@null�̏ꍇ�́A���ׂĂ̎萔���R�[�X���ΏۂƂ���B<BR>
     */
    public String commissionCourse;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 418F386B002E
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�K�p�J�n���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * �R�j�@@�萔���R�[�X�̃`�F�b�N<BR>
     * �@@�R�|�P�jthis.�萔���R�[�X��null�ł��A�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01096<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41510A770368
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //* <BR>
        //* �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        //* �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00833<BR>
        //* <BR>
        if (this.branchCode == null)
        {
            //��O
            log.debug("[���X�R�[�h] = " + branchCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME, "���X�R�[�h�̖����͂̏ꍇ");
        }
        
        //* �Q�j�@@�K�p�J�n���̃`�F�b�N<BR>
        //* �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00837<BR>
        //* <BR>
        if (this.trialStartDate == null)
        {
            //��O
            log.debug("[�K�p�J�n��] = " + trialStartDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                this.getClass().getName() + STR_METHOD_NAME, "�K�p�J�n�������͂̏ꍇ");
        }
        
        //* �R�j�@@�萔���R�[�X�̃`�F�b�N<BR>
        //* �@@�R�|�P�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01096<BR>
        //* <BR>
        if (this.commissionCourse != null && !(WEB3CommissionCourseDivDef.FIXED_RATE_COMMISSION_STANDARD).equals(this.commissionCourse) &&
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
                this.getClass().getName() + STR_METHOD_NAME, "�萔���R�[�X�̕s���ȃR�[�h�l�̏ꍇ");
        }
        //* �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
        //* �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
            this.pageIndex = "1"; 
        }
        //* �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00090<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
           //��O
           log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00090,
               this.getClass().getName() + STR_METHOD_NAME,"�v���y�[�W�ԍ������ȊO�̕������܂܂��ꍇ");
        }
        //* �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00616<BR>
        //* <BR>
        double l_dblPageIndex = Double.parseDouble(this.pageIndex);
        if (l_dblPageIndex <= 0)
        {
            //��O
            log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,"�v���y�[�W�ԍ������}�C�i�X�l�̏ꍇ");        
        }
        
        //* �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
        //* �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00091<BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
           //��O
           log.debug("[�y�[�W���\���s��] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091,
               this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s�������͂̏ꍇ");
        }
        
        //* �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00092<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
           //��O
           log.debug("[�y�[�W���\���s��] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s�������ȊO�̕������܂܂��ꍇ");
        }
        //* �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00617<BR>
        //* <BR>
        double l_dblPageSize = Double.parseDouble(this.pageSize);
        if (l_dblPageSize <= 0)
        {
            //��O
            log.debug("[�y�[�W���\���s��] = " + pageSize);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���}�C�i�X�l�̏ꍇ");        
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
