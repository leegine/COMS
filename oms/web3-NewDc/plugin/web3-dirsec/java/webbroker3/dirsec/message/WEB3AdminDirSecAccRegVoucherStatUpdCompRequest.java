head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g(WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
Revision History    : 2007/06/15 ���G�� (���u) �d�l�ύX ���f��No.100
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g)<BR>
 * �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�������N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdCompRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_stat_upd_comp";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * (�X�V_�`�[�쐬��)<BR>
     * �C����̓`�[�쐬��<BR>
     * <BR>
     * 3�F��t����<BR>
     * 4�F��t�G���[<BR>
     */
    public String updateVoucherMakeStatus;

    /**
     * (�X�V_�G���[���R�R�[�h)<BR>
     * �C����̃G���[���R�R�[�h<BR>
     */
    public String updateErrorReasonCode;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�ڋq���o�^�`�[���R�[�h)<BR>
     * �ڋq���o�^�`�[���R�[�h<BR>
     */
    public WEB3AdminDirSecAccVoucherRecordDetail[] accVoucherRecord;

    /**
     * @@roseuid 466E0B6B00F6
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdCompRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * <BR>
     * �P�j �ڋq���o�^�`�[���R�[�h�ڍהz�� != null�̏ꍇ�A<BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍהz��`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�ڋq���o�^�`�[���R�[�h�ڍהz�� == null<BR>
     * �@@�@@or �ڋq���o�^�`�[���R�[�h�ڍהz��̒��� == 0 �̏ꍇ�A<BR>
     * �@@�@@�u�X�V�Ώۂ̃��R�[�h���s���ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02838<BR>
     * <BR>
     * �@@�P-�P�j �،���ЃR�[�h�`�F�b�N<BR>
     * �@@�@@�P-�P-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�،���ЃR�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�،���ЃR�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_01023<BR>
     * <BR>
     * �@@�P-�Q�j ���X�R�[�h�`�F�b�N<BR>
     * �@@�@@�P-�Q-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].���X�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u���X�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00779<BR>
     * �@@�P-�R�j �ڋq�R�[�h�`�F�b�N<BR>
     * �@@�@@�P-�R-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�ڋq�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�ڋq�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00780<BR>
     * �@@�P-�S�j �f�[�^�R�[�h�`�F�b�N<BR>
     * �@@�@@�P-�S-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�f�[�^�R�[�h == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�f�[�^�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02828<BR>
     * �@@�P-�T�j ���ʃR�[�h�`�F�b�N<BR>
     * �@@�@@�P-�T-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].���ʃR�[�h == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u���ʃR�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02829<BR>
     * �@@�P-�U�j �A����ʁA�`�[�ʔԃ`�F�b�N<BR>
     * �@@�@@�P-�U-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == FALSE<BR>
     * �@@�@@�@@�@@AND �ڋq���o�^�`�[���R�[�h�ڍ�[index]�A����� == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�A����ʂ��s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02830<BR>
     * <BR>
     * �@@�@@�P-�U-�Q�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == TRUE<BR>
     * �@@�@@�@@�@@AND �ڋq���o�^�`�[���R�[�h�ڍ�[index].�`�[�ʔ� == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�`�[�ʔԂ��s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02831<BR>
     * <BR>
     * �Q�j �`�[�쐬�󋵃`�F�b�N<BR>
     * �@@�Q-�P�j this.�X�V_�`�[�쐬�� == null �̏ꍇ�A<BR>
     * �@@�@@�@@�u�`�[�쐬�󋵂���͂��Ă��������B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02825<BR>
     * <BR>
     * �R�j �G���[���R�R�[�h�`�F�b�N<BR>
     * �@@�R-�P�j this.�X�V_�G���[���R�R�[�h == null �̏ꍇ�A<BR>
     * �@@�@@�@@�u�G���[���R�R�[�h����͂��Ă��������B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02826<BR>
     * <BR>
     * �@@�R-�Q�j this.�X�V_�G���[���R�R�[�h�̒��� > 4 OR<BR>
     * �@@�@@�@@this.�X�V_�G���[���R�R�[�h�����p�p���ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�u�G���[���R�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02827<BR>
     * <BR>
     * �S�j�@@�Ïؔԍ��`�F�b�N<BR>
     * �@@�S-�P�j�@@this.�Ïؔԍ� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�u�Ïؔԍ����s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02832<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4655278700F2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //        �P�j �ڋq���o�^�`�[���R�[�h�ڍהz�� != null�̏ꍇ�A
        //        �ڋq���o�^�`�[���R�[�h�ڍהz��`�F�b�N���s���B
        //
        //        �ڋq���o�^�`�[���R�[�h�ڍהz�� == null
        //         or �ڋq���o�^�`�[���R�[�h�ڍהz��̒��� == 0 �̏ꍇ�A
        //        �u�X�V�Ώۂ̃��R�[�h���s���ł��B�v��O���X���[����B
        if (accVoucherRecord == null || accVoucherRecord.length == 0)
        {
            log.debug("�X�V�Ώۂ̃��R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02838,
                this.getClass().getName() + STR_METHOD_NAME,
                "�X�V�Ώۂ̃��R�[�h���s���ł��B");
        }

        for (int i = 0; i < accVoucherRecord.length; i++)
        {
            //�P-�P�j �،���ЃR�[�h�`�F�b�N
            //       �P-�P-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�،���ЃR�[�h == null�̏ꍇ
            //               �u�،���ЃR�[�h���s���ł��B�v�̗�O���X���[����B
            if (accVoucherRecord[i].institutionCode == null)
            {
                log.debug("�،���ЃR�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01023,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�،���ЃR�[�h���s���ł��B");
            }

            //     �P-�Q�j ���X�R�[�h�`�F�b�N
            //       �P-�Q-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].���X�R�[�h == null�̏ꍇ�A
            //               �u���X�R�[�h���s���ł��B�v�̗�O���X���[����B
            if (accVoucherRecord[i].branchCode == null)
            {
                log.debug("���X�R�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���X�R�[�h���s���ł��B");
            }

            //     �P-�R�j �ڋq�R�[�h�`�F�b�N
            //       �P-�R-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�ڋq�R�[�h == null�̏ꍇ�A
            //               �u�ڋq�R�[�h���s���ł��B�v�̗�O���X���[����B
            if (accVoucherRecord[i].accountCode == null)
            {
                log.debug("�ڋq�R�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�ڋq�R�[�h���s���ł��B");
            }

            //     �P-�S�j �f�[�^�R�[�h�`�F�b�N
            //       �P-�S-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�f�[�^�R�[�h == null �̏ꍇ�A
            //               �u�f�[�^�R�[�h���s���ł��B�v�̗�O���X���[����B
            if (accVoucherRecord[i].dataCode == null)
            {
                log.debug("�f�[�^�R�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�f�[�^�R�[�h���s���ł��B");
            }

            //     �P-�T�j ���ʃR�[�h�`�F�b�N
            //       �P-�T-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].���ʃR�[�h == null �̏ꍇ�A
            //               �u���ʃR�[�h���s���ł��B�v�̗�O���X���[����B
            if (accVoucherRecord[i].requestNumber == null)
            {
                log.debug("���ʃR�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02829,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���ʃR�[�h���s���ł��B");
            }

            //     �P-�U�j �A����ʁA�`�[�ʔԃ`�F�b�N
            //       �P-�U-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == FALSE
            //                   AND �ڋq���o�^�`�[���R�[�h�ڍ�[index]�A����� == null �̏ꍇ�A
            //                  �u�A����ʂ��s���ł��B�v�̗�O���X���[����B
            if (!accVoucherRecord[i].voucherFlag && (accVoucherRecord[i].infoType == null))
            {
                log.debug("�A����ʂ��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02830,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�A����ʂ��s���ł��B");
            }

            //       �P-�U-�Q�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == TRUE
            //                   AND �ڋq���o�^�`�[���R�[�h�ڍ�[index].�`�[�ʔ� == null �̏ꍇ�A
            //                 �u�`�[�ʔԂ��s���ł��B�v�̗�O���X���[����B
            if (accVoucherRecord[i].voucherFlag && (accVoucherRecord[i].voucherNumber == null))
            {
                log.debug("�`�[�ʔԂ��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02831,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�`�[�ʔԂ��s���ł��B");
            }

        }

        //   �Q�j �`�[�쐬�󋵃`�F�b�N
        //     �Q-�P�j this.�X�V_�`�[�쐬�� == null �̏ꍇ�A
        //             �u�`�[�쐬�󋵂���͂��Ă��������B�v�̗�O���X���[����B
        if (this.updateVoucherMakeStatus == null)
        {
            log.debug("�`�[�쐬�󋵂���͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02825,
                this.getClass().getName() + STR_METHOD_NAME,
                "�`�[�쐬�󋵂���͂��Ă��������B");
        }

        //   �R�j �G���[���R�R�[�h�`�F�b�N
        //     �R-�P�j this.�X�V_�G���[���R�R�[�h == null �̏ꍇ�A
        //              �u�G���[���R�R�[�h����͂��Ă��������B�v�̗�O���X���[����B
        if (this.updateErrorReasonCode == null)
        {
            log.debug("�G���[���R�R�[�h����͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02826,
                this.getClass().getName() + STR_METHOD_NAME,
                "�G���[���R�R�[�h����͂��Ă��������B");
        }

        //     �R-�Q�j this.�X�V_�G���[���R�R�[�h�̒��� > 4 OR
        //          this.�X�V_�G���[���R�R�[�h�����p�p���ȊO�̏ꍇ�A
        //           �u�G���[���R�R�[�h���s���ł��B�v�̗�O���X���[����B
        if (this.updateErrorReasonCode.length() > 4
            || !WEB3StringTypeUtility.isLetterOrDigit(this.updateErrorReasonCode))
        {
            log.debug("�G���[���R�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02827,
                this.getClass().getName() + STR_METHOD_NAME,
                "�G���[���R�R�[�h���s���ł��B");
        }

        //   �S�j�@@�Ïؔԍ��`�F�b�N
        //   �@@�S-�P�j�@@this.�Ïؔԍ� == null�̏ꍇ�A
        //   �@@�@@�@@�@@�@@�u�Ïؔԍ����s���ł��B�v�̗�O���X���[����B
        if (this.password == null)
        {
            log.debug("�Ïؔԍ����s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02832,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ïؔԍ����s���ł��B");
        }

       log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherStatUpdCompResponse(this);
    }
}
@
