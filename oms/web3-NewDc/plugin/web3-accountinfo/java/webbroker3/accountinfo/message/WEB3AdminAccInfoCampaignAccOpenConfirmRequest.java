head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.56.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fظ���
                       (WEB3AdminAccInfoCampaignAccOpenConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 �Ј��� (���u) �V�K�쐬
Revision History : 2007/02/01  ���f��No.165
Revision History : 2007/02/28 Inomata(SCS)���f��No.203
Revision History : 2007/03/07 Inomata(SCS)���f��No.209
*/

package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoAccountOpenDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fظ���)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fظ���<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenConfirmRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312031L;

    /**
     * (�X�V�����t���O)<BR>
     * �X�V�����t���O<BR>
     * <BR>
     * 0�F�o�^����<BR>
     * 1�F�X�V����<BR>
     * 2�F�폜����<BR>
     */
    public String updateFlag;

    /**
     * �����J�ݏ����w�跬��߰ݍX�V���
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo;

    /**
     * @@roseuid 45C0875F0295
     */
    public WEB3AdminAccInfoCampaignAccOpenConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignAccOpenConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j �X�V�����t���O�̃`�F�b�N<BR>
     *   �P-�P�j �X�V�����t���O != (0 or 1 or 2) �̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02710<BR>
     * <BR>
     * �Q�j �X�V�����t���O�� 0 �i�o�^�j���� 1�i�X�V�j �̏ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     *   �Q-�P�j ���i�R�[�h�̃`�F�b�N<BR>
     *     �Q-�P-�P�j ���i�R�[�h�z��null�̏ꍇ�A�w���i���I���G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02718<BR>
     * <BR>
     *   �Q-�Q�j �L�����y�[�����̂̃`�F�b�N<BR>
     *     �Q-�Q-�P�j �L�����y�[�����̂������͂̏ꍇ    �A�w�L�����y�[�����̖����̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02712<BR>
     *     �Q-�Q-�Q�j �L�����y�[������101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02709<BR>
     * <BR>
     *   �Q-�R�j ���X�R�[�h�̃`�F�b�N<BR>
     *     �Q-�R-�P�j  ���X�R�[�h��3���ȊO�̏ꍇ�A�w���X�R�[�h�����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     *   �Q-�S�j ���҃R�[�h�̃`�F�b�N<BR>
     *     �Q-�S-�P�j ���҃R�[�h��6���ȏ�̏ꍇ�A�w���҃R�[�h�����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01912<BR>
     * <BR>
     *   �Q-�T�j �����J�ݓ��w�肪�I�����ꂽ�ꍇ�i�����J�݌o�ߊ��ԁi���j!=null && �����J�݌o�ߊ��ԁi���j!=null �j<BR>
     *           �ȉ��������s���B<BR>
     *     �Q-�T-�P�j �Ώۊ��ԃ`�F�b�N<BR>
     *       �Q-�T-�P-�P�j �����J�݌o�ߊ��ԁi���j�������� ���� �����J�݌o�ߊ��ԁi���j�������͂̏ꍇ�A<BR>
     *                     �w�Ώۊ��Ԗ����̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@  class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@  tag   : BUSINESS_ERROR_02713<BR>
     *       �Q-�T-�P-�Q�j �����J�݌o�ߊ��ԁi���j���O�`�P�Q�̐����ȊO�̏ꍇ�A<BR>
     *                     �w�Ώۊ��ԃG���[�x��O���X���[����B<BR>
     * �@@�@@�@@  class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@  tag   : BUSINESS_ERROR_02715<BR>
     *       �Q-�T-�P-�R�j �����J�݌o�ߊ��ԁi���j���O�`�R�P�̐����ȊO�̏ꍇ�A<BR>
     *                     �w�Ώۊ��ԃG���[�x��O���X���[����B<BR>
     * �@@�@@�@@  class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@  tag   : BUSINESS_ERROR_02715<BR>
     *       �Q-�T-�P-�S�j �����J�݌o�ߊ��ԁi���j==�O && �����J�݌o�ߊ��ԁi���j==�O �̏ꍇ�A<BR>
     *                     �w�Ώۊ��ԃG���[�x��O���X���[����B<BR>
     * �@@�@@�@@  class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@  tag   : BUSINESS_ERROR_02715<BR>
     * <BR>
     *     �Q-�T-�Q�j �����J�݋敪�̃`�F�b�N<BR>
     *       �Q-�T-�Q-�P�j �����J�݋敪�� 1:���������@@2:�M�p���� 3:�敨OP�����@@4:FX�����@@5:���������� �ȊO�̏ꍇ�A<BR>
     *                     �w�����J�݋敪�G���[�x��O���X���[����B<BR>
     * �@@�@@  �@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@  tag   : BUSINESS_ERROR_02719<BR>
     * <BR>
     *   �Q-�U�j ���Ԏw�肪�I�����ꂽ�ꍇ�i�����J�݌o�ߊ��ԁi���j==null && �����J�݌o�ߊ��ԁi���j==null �j<BR>
     *     �Q-�U-�P�j �Ώۊ��ԃ`�F�b�N<BR>
     *       �Q-�U-�P-�P�j �Ώۊ���From���͑Ώۊ���To�������͂̏ꍇ�A<BR>
     *                     �w�Ώۊ��Ԗ����̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@  class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@  tag   : BUSINESS_ERROR_02713<BR>
     *       �Q-�U-�P-�Q�j �Ώۊ���From > �Ώۊ���To�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B<BR>
     * �@@�@@�@@  class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@  tag   : BUSINESS_ERROR_02715<BR>
     *<BR>
     *     �Q-�U-�Q�j �����J�݋敪�̃`�F�b�N
     *       �Q-�U-�Q-�P�j �����J�݋敪�� 1:���������@@2:�M�p���� 3:�敨OP�����@@4:FX�����@@5:�����������@@null �ȊO�̏ꍇ�A<BR>
     *                     �w�����J�݋敪�G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02719<BR>
     * <BR>
     *   �Q-�V�j �������̃`�F�b�N<BR>
     *     �Q-�V-�P�j �������������͂̏ꍇ�A�w�����������̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02080<BR>
     * <BR>
     *     �Q-�V-�Q�j �������� 0 �` 100 �̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02082<BR>
     * <BR>
     *   �Q-�W�j �����J�ݓ��̃`�F�b�N<BR>
     *     �Q-�W-�P�j �����J�ݓ�From!=null && �����J�ݓ�To==null ���� <BR>
     *                �����J�ݓ�From==null && �����J�ݓ�To!=null�̏ꍇ�A�w�����J�ݓ��G���[�x��O���X���[����<BR>
     *        class : WEB3BusinessLayerException<BR>
     *        tag   : BUSINESS_ERROR_02720<BR>
     *     �Q-�W-�Q�j �����J�݋敪==null && �����J�ݓ�From!=null ���� �����J�݋敪!=null && �����J�ݓ�From==null �̏ꍇ�A<BR>
     *                �w�����J�ݓ��G���[�x��O���X���[����B<BR>
     *        class : WEB3BusinessLayerException<BR>
     *        tag   : BUSINESS_ERROR_02720<BR>
     *     �Q-�W-�R�j �����J�ݓ�From > �����J�ݓ�To�̏ꍇ�A�w�����J�ݓ��G���[�x��O���X���[����B<BR>
     *        class : WEB3BusinessLayerException<BR>
     *        tag   : BUSINESS_ERROR_02720<BR>
     * <BR>
     *   �Q-�X�j �o�^�^�C�v�̃`�F�b�N<BR>
     *     �Q-�X-�P�j �o�^�^�C�v != 0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02722<BR>
     * <BR>
     * �R�j �X�V�����t���O�� 1�i�X�V�j���� 2�i�폜�j �̏ꍇ�A�ȉ��������s���B<BR>
     *   �R-�P�j �萔�������L�����y�[������ID�̃`�F�b�N<BR>
     *     �R-�P-�P�j �萔�������L�����y�[������ID�������͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02716<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 45A7655202DF     * <BR>
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j �X�V�����t���O�̃`�F�b�N
        //  �P-�P�j �X�V�����t���O != (0 or 1 or 2) �̏ꍇ�A��O���X���[����B
        if (!(WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.DELETE.equals(this.updateFlag)))
        {
            log.debug("�X�V�����t���O�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02710,
                this.getClass().getName() + STR_METHOD_NAME,
                " �X�V�����t���O = " + this.updateFlag);
        }

        //�Q�j �X�V�����t���O�� 0 �i�o�^�j���� 1�i�X�V�j �̏ꍇ�A�ȉ��������s���B
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag))
        {
            //  �Q-�P�j ���i�R�[�h�̃`�F�b�N
            //      �Q-�P-�P�j ���i�R�[�h�z��null�̏ꍇ�A�w���i���I���G���[�x��O���X���[����B
            if (this.commissionCampaignInfo.itemCode == null)
            {
                log.debug("���i���I���G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02718,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���i�R�[�h = " + this.commissionCampaignInfo.itemCode);
            }

            //  �Q-�Q�j �L�����y�[�����̂̃`�F�b�N
            //      �Q-�Q-�P�j �L�����y�[�����̂������͂̏ꍇ  �A�w�L�����y�[�����̖����̓G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignName))
            {
                log.debug("�L�����y�[�����̖����̓G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02712,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �L�����y�[������ = " + this.commissionCampaignInfo.campaignName);
            }

            //      �Q-�Q-�Q�j �L�����y�[������101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.commissionCampaignInfo.campaignName) >= 101)
            {
                log.debug("�L�����y�[�����̌����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �L�����y�[������ = " + this.commissionCampaignInfo.campaignName);
            }

            //  �Q-�R�j ���X�R�[�h�̃`�F�b�N
            //      �Q-�R-�P�j  ���X�R�[�h��3���ȊO�̏ꍇ�A�w���X�R�[�h�����G���[�x��O���X���[����
            if (this.commissionCampaignInfo.branchCode != null)
            {
                if (this.commissionCampaignInfo.branchCode.length() != 3)
                {
                    log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���X�R�[�h = " + this.commissionCampaignInfo.branchCode);
                }
            }

            //  �Q-�S�j ���҃R�[�h�̃`�F�b�N
            //      �Q-�S-�P�j ���҃R�[�h��6���ȏ�̏ꍇ�A�w���҃R�[�h�����G���[�x��O���X���[����B
            if (this.commissionCampaignInfo.traderCode != null)
            {
                if (this.commissionCampaignInfo.traderCode.length() >= 6)
                {
                    log.debug("���҃R�[�h�i������j�̒������s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ���҃R�[�h = " + this.commissionCampaignInfo.traderCode);
                }
            }

            //  �Q-�T�j �����J�ݓ��w�肪�I�����ꂽ�ꍇ�i�����J�݌o�ߊ��ԁi���j!=null && �����J�݌o�ߊ��ԁi���j!=null �j�ȉ��������s���B
            if (this.commissionCampaignInfo.accopenPassPeriodMonth != null
                    && this.commissionCampaignInfo.accopenPassPeriodDay != null)
            {
                //    �Q-�T-�P�j �Ώۊ��ԃ`�F�b�N
                //    �Q-�T-�P-�P�j �����J�݌o�ߊ��ԁi���j�������� && �����J�݌o�ߊ��ԁi���j�������͂̏ꍇ�A
                //                �w�Ώۊ��Ԗ����̓G���[�x��O���X���[����B
                if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodMonth)
                        && WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodDay))
                {
                    log.debug("�Ώۊ��Ԗ����̓G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02713,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  �����J�݌o�ߊ��ԁi���j = " + this.commissionCampaignInfo.accopenPassPeriodMonth
                        + " �����J�݌o�ߊ��ԁi���j = " + this.commissionCampaignInfo.accopenPassPeriodDay);
                }

                //      �Q-�T-�P-�Q�j �����J�݌o�ߊ��ԁi���j���O�`�P�Q�̐����ȊO�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
                if (WEB3StringTypeUtility.isInteger(this.commissionCampaignInfo.accopenPassPeriodMonth))
                {
                    if (Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodMonth) > 12
                            || Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodMonth) < 0)
                    {
                        log.debug("�Ώۊ��ԃG���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "  �����J�݌o�ߊ��ԁi���j = " + this.commissionCampaignInfo.accopenPassPeriodMonth);
                    }
                }
                else if (!WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodMonth) )
                {
                    log.debug("�Ώۊ��ԃG���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  �����J�݌o�ߊ��ԁi���j = " + this.commissionCampaignInfo.accopenPassPeriodMonth);
                }

                //      �Q-�T-�P-�R�j �����J�݌o�ߊ��ԁi���j���O�`�R�P�̐����ȊO�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
                if (WEB3StringTypeUtility.isInteger(this.commissionCampaignInfo.accopenPassPeriodDay))
                {
                    if (Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodDay) > 31
                            || Integer.parseInt(this.commissionCampaignInfo.accopenPassPeriodDay) < 0)
                    {
                        log.debug("�Ώۊ��ԃG���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "  �����J�݌o�ߊ��ԁi���j = " + this.commissionCampaignInfo.accopenPassPeriodDay);
                    }
                }
                else if (!WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accopenPassPeriodDay))
                {
                    log.debug("�Ώۊ��ԃG���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  �����J�݌o�ߊ��ԁi���j = " + this.commissionCampaignInfo.accopenPassPeriodDay);
                }

                //      �Q-�T-�P-�S�j �����J�݌o�ߊ��ԁi���j==�O && �����J�݌o�ߊ��ԁi���j==�O �̏ꍇ�A
                //                    �w�Ώۊ��ԃG���[�x��O���X���[����B

                String l_strPassPeriodMonth =
                    this.commissionCampaignInfo.accopenPassPeriodMonth;
                int l_intPassPeriodMonth = Integer.parseInt(l_strPassPeriodMonth);
                String l_strPassPeriodDay =
                    this.commissionCampaignInfo.accopenPassPeriodDay;
                int l_intPassPeriodDay = Integer.parseInt(l_strPassPeriodDay);
                if (l_intPassPeriodMonth == 0 && l_intPassPeriodDay == 0)
                {
                    log.debug("�Ώۊ��ԃG���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�Ώۊ��ԃG���[�B");
                }
                //    �Q-�T-�Q�j �����J�݋敪�̃`�F�b�N
                //      �Q-�T-�Q-�P�j �����J�݋敪�� 1:���������@@2:�M�p���� 3:�敨OP�����@@4:FX�����@@5:���������� �ȊO�̏ꍇ�A�w�����J�݋敪�G���[�x��O���X���[����B
                if (!(WEB3AccInfoAccountOpenDivDef.MULTIPLE.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.MARGIN.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.IFO.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.FX.equals(this.commissionCampaignInfo.accountOpenDiv)
                        || WEB3AccInfoAccountOpenDivDef.CHINESE_EQUITY.equals(this.commissionCampaignInfo.accountOpenDiv)))
                {
                    log.debug("�����J�݋敪�G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02719,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �����J�݋敪 = " + this.commissionCampaignInfo.accountOpenDiv);
                }
            }

            //  �Q-�U�j ���Ԏw�肪�I�����ꂽ�ꍇ�i�����J�݌o�ߊ��ԁi���j==null && �����J�݌o�ߊ��ԁi���j==null �j
            if (this.commissionCampaignInfo.accopenPassPeriodMonth == null
                    && this.commissionCampaignInfo.accopenPassPeriodDay == null)
            {
                //      �Q-�U-�P�j �Ώۊ��ԃ`�F�b�N
                //      �Q-�U-�P-�P�j �Ώۊ���From���͑Ώۊ���To�������͂̏ꍇ�A�w�Ώۊ��Ԗ����̓G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.targetPeriodFrom == null
                        || this.commissionCampaignInfo.targetPeriodTo == null)
                {
                    log.debug("�Ώۊ��Ԗ����̓G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02713,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  �Ώۊ���From = " + this.commissionCampaignInfo.targetPeriodFrom
                        + " �Ώۊ���To = " + this.commissionCampaignInfo.targetPeriodTo);
                }

                //       �Q-�U-�P-�Q�j �Ώۊ���From > �Ώۊ���To�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
                if (WEB3DateUtility.compareToDay(this.commissionCampaignInfo.targetPeriodFrom,
                    this.commissionCampaignInfo.targetPeriodTo) > 0)
                {
                    log.debug("�Ώۊ��ԃG���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "  �Ώۊ���From = " + this.commissionCampaignInfo.targetPeriodFrom
                        + " �Ώۊ���To = " + this.commissionCampaignInfo.targetPeriodTo);
                }
                //      �Q-�U-�Q�j �����J�݋敪�̃`�F�b�N
                //        �Q-�U-�Q-�P�j �����J�݋敪�� 1:���������@@2:�M�p���� 3:�敨OP�����@@4:FX�����@@5:�����������@@null �ȊO�̏ꍇ�A
                //                      �w�����J�݋敪�G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.targetPeriodFrom == null
                    && this.commissionCampaignInfo.targetPeriodTo == null)
                {
                    if (!(WEB3AccInfoAccountOpenDivDef.MULTIPLE.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.MARGIN.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.IFO.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.FX.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || WEB3AccInfoAccountOpenDivDef.CHINESE_EQUITY.equals(this.commissionCampaignInfo.accountOpenDiv)
                            || this.commissionCampaignInfo.accountOpenDiv == null))
                    {
                        log.debug("�����J�݋敪�G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02719,
                            this.getClass().getName() + STR_METHOD_NAME,
                            " �����J�݋敪 = " + this.commissionCampaignInfo.accountOpenDiv);
                    }
                }
            }

            //  �Q-�V�j �������̃`�F�b�N
            //      �Q-�V-�P�j �������������͂̏ꍇ�A�w�����������̓G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.collectRate))
            {
                log.debug("�������������͂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������ = " + this.commissionCampaignInfo.collectRate);
            }

            //      �Q-�V-�Q�j �������� 0 �` 100 �̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.isInteger(this.commissionCampaignInfo.collectRate))
            {
                if (Integer.parseInt(this.commissionCampaignInfo.collectRate) > 100
                        || Integer.parseInt(this.commissionCampaignInfo.collectRate) < 0)
                {
                    log.debug("�������̒l���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ������ = " + this.commissionCampaignInfo.collectRate);
                }
            }
            else
            {
                log.debug("�������̒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������ = " + this.commissionCampaignInfo.collectRate);
            }

            //  �Q-�W�j �����J�ݓ��̃`�F�b�N
            //    �Q-�W-�P�j �����J�ݓ�From!=null && �����J�ݓ�To==null ���� �����J�ݓ�From==null && �����J�ݓ�To!=null�̏ꍇ�A�w�����J�ݓ��G���[�x��O���X���[����
            if ((this.commissionCampaignInfo.accountOpenDateFrom != null
                    && this.commissionCampaignInfo.accountOpenDateTo == null)
                    || (this.commissionCampaignInfo.accountOpenDateFrom == null
                    && this.commissionCampaignInfo.accountOpenDateTo != null))
            {
                log.debug("�����J�ݓ��G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02720,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �����J�ݓ�From = " + this.commissionCampaignInfo.accountOpenDateFrom
                    + " �����J�ݓ�To = " + this.commissionCampaignInfo.accountOpenDateTo);
            }
            //    �Q-�W-�Q�j �����J�݋敪==null && �����J�ݓ�From!=null ���� �����J�݋敪!=null && �����J�ݓ�From==null �̏ꍇ�A
            //               �w�����J�ݓ��G���[�x��O���X���[����B
            if ((this.commissionCampaignInfo.accountOpenDiv == null
                    && this.commissionCampaignInfo.accountOpenDateFrom != null)
                    || (this.commissionCampaignInfo.accountOpenDiv != null
                    && this.commissionCampaignInfo.accountOpenDateFrom == null))
            {
                log.debug("�����J�ݓ��G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02720,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �����J�݋敪 = " + this.commissionCampaignInfo.accountOpenDiv
                    + " �����J�ݓ�From = " + this.commissionCampaignInfo.accountOpenDateFrom
                    + " �����J�ݓ�To = " + this.commissionCampaignInfo.accountOpenDateTo);
            }

            //      �Q-�W-�R�j �����J�ݓ�From > �����J�ݓ�To�̏ꍇ�A�w�����J�ݓ��G���[�x��O���X���[����B
            if (this.commissionCampaignInfo.accountOpenDateFrom != null
                && this.commissionCampaignInfo.accountOpenDateTo != null)
            {
                if (WEB3DateUtility.compareToDay(this.commissionCampaignInfo.accountOpenDateFrom,
                        this.commissionCampaignInfo.accountOpenDateTo) > 0)
                {
                    log.debug("�����J�ݓ��G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02720,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �����J�ݓ�From = " + this.commissionCampaignInfo.accountOpenDateFrom
                        + " �����J�ݓ�To = " + this.commissionCampaignInfo.accountOpenDateTo);
                }
            }

            //�Q-�X�j �o�^�^�C�v�̃`�F�b�N
            //  �Q-�X-�P�j �o�^�^�C�v != 0 �̏ꍇ�A��O���X���[����B
            if (!WEB3AccInfoRegistTypeDef.ACCOPEN_CONDITION.equals(this.commissionCampaignInfo.registType))
            {
                log.debug("�o�^�^�C�v��'0'�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02722,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �o�^�^�C�v = " + this.commissionCampaignInfo.registType);
            }
        }

        //�R�j �X�V�����t���O�� 1�i�X�V�j���� 2�i�폜�j �̏ꍇ�A�ȉ��������s���B
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(this.updateFlag))
        {
            //  �R-�P�j �萔�������L�����y�[������ID�̃`�F�b�N
            //      �R-�P-�P�j �萔�������L�����y�[������ID�������͂̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignId))
            {
                log.debug("�萔�������L�����y�[������ID�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02716,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �萔�������L�����y�[������ID = " + this.commissionCampaignInfo.campaignId);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
