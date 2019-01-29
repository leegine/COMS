head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fظ���
                       (WEB3AdminAccInfoCampaignIndiviConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 �Ј��� (���u) �V�K�쐬
Revision History : 2007/02/01  ���f��No.165
Revision History : 2007/02/28 ����(SCS)���f��No.203
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

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
 * (�Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fظ���)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fظ���<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviConfirmRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignIndiviConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312039L;

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
     * �萔�������L�����y�[���������
     */
    public WEB3AccInfoCampaignInfo commissionCampaignInfo;

    /**
     * @@roseuid 45C0876100D0
     */
    public WEB3AdminAccInfoCampaignIndiviConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignIndiviConfirmResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P�j �X�V�����t���O�̃`�F�b�N<BR>
     *   �P-�P�j �X�V�����t���O�� '0' �� '1' �� '2'�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02710<BR>
     * <BR>
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]<BR>
     * �Q�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A�w���X�R�[�h�����̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00833<BR>
     * �@@�Q�|�Q�j�@@�R���ȊO�̏ꍇ�A�w���X�R�[�h�����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     * �R�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A�w�ڋq�R�[�h�����̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00835<BR>
     * �@@�R�|�Q�j�@@������6�łȂ��ꍇ�A�w�ڋq�R�[�h�����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00836<BR>
     * <BR>
     * �S�j�@@�L�����y�[�����̂̃`�F�b�N<BR>
     *   �S�|�P�j�@@101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B<BR>
     *        class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02709<BR>
     * [�o�^�^�C�v = '1'(�ʌڋq�w��)�̏ꍇ�̂݁A�S�|�Q�j�̃`�F�b�N���s��]<BR>
     *   �S�|�Q�j�@@�����͂̏ꍇ�A�w�L�����y�[�����̖����̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02712<BR>
     * [�o�^�^�C�v = '2'(�����ڋq�w��)�̏ꍇ�̂݁A�S�|�R�j�̃`�F�b�N���s��]<BR>
     * �@@�S�|�R�j�@@���������͂���Ă���ꍇ�A�w�L�����y�[�����̓��̓G���[�x��O���X���[����B<BR>
     *       class : WEB3BusinessLayerException<BR>
     *       tag   :BUSINESS_ERROR_02725<BR>
     * <BR>
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^) or '1'(�ύX)�̏ꍇ]<BR>
     * <BR>
     * �T�j �o�^�^�C�v�̃`�F�b�N<BR>
     *  �T-�P�j �o�^�^�C�v�� '1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02711<BR>
     * <BR>
     * �U�j�@@�Ώۊ���From�ATo�̃`�F�b�N<BR>
     *�@@ �U�|�P�j�@@�Ώۊ���From,�Ώۊ���To�����͂��ꂽ�ꍇ�A<BR>
�@@�@@ *�@@�@@�@@�@@     �Ώۊ���From �� �Ώۊ���To�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02715<BR>
     * <BR>
     * �V�j�@@�������̃`�F�b�N<BR>
     * �@@�V�|�P�j�@@�����͂̏ꍇ�A�w�����������̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02080<BR>
     * �@@�V�|�Q�j�@@0�`100�̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02082<BR>
     * @@throws WEB3BaseException
     * @@roseuid 45A327C6011F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j �X�V�����t���O�̃`�F�b�N
        //    �P-�P�j �X�V�����t���O�� '0' �� '1' �� '2'�ȊO�̏ꍇ�A��O���X���[����B
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

        //[���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag))
        {
            //�Q�j�@@���X�R�[�h�̃`�F�b�N
            //  �Q�|�P�j�@@�����͂̏ꍇ�A�w���X�R�[�h�����̓G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.branchCode))
            {
                log.debug("���X�R�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���X�R�[�h = " + this.commissionCampaignInfo.branchCode);
            }

            //�@@�Q�|�Q�j�@@�R���ȊO�̏ꍇ�A�w���X�R�[�h�����G���[�x��O���X���[����B
            if (this.commissionCampaignInfo.branchCode.length() != 3)
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ���X�R�[�h = " + this.commissionCampaignInfo.branchCode);
            }

            //�R�j�@@�ڋq�R�[�h�̃`�F�b�N
            //  �R�|�P�j�@@�����͂̏ꍇ�A�w�ڋq�R�[�h�����̓G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.accountCode))
            {
                log.debug("�ڋq�R�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �ڋq�R�[�h = " + this.commissionCampaignInfo.accountCode);
            }

            //  �R�|�Q�j�@@������6�łȂ��ꍇ�A�w�ڋq�R�[�h�����G���[�x��O���X���[����B
            if (this.commissionCampaignInfo.accountCode.length() != 6)
            {
                log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �ڋq�R�[�h = " + this.commissionCampaignInfo.accountCode);
            }

            //�S�j�@@�L�����y�[�����̂̃`�F�b�N
            //  �S�|�P�j�@@101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B
            if (this.commissionCampaignInfo.campaignName != null)
            {
                if (WEB3StringTypeUtility.getByteLength(this.commissionCampaignInfo.campaignName) >= 101)
                {
                    log.debug("�L�����y�[�����̌����G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �L�����y�[������ = " + this.commissionCampaignInfo.campaignName);
                }
            }

            //[�o�^�^�C�v = '1'(�ʌڋq�w��)�̏ꍇ�̂݁A�T�|�Q�j�̃`�F�b�N���s��]
            if (WEB3AccInfoRegistTypeDef.INDIVIDUAL.equals(this.commissionCampaignInfo.registType))
            {
                //  �S�|�Q�j�@@�����͂̏ꍇ�A�w�L�����y�[�����̖����̓G���[�x��O���X���[����B
                if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignName))
                {
                    log.debug("�L�����y�[�����̖����̓G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02712,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �L�����y�[������ = " + this.commissionCampaignInfo.campaignName);
                }
            }

            //[�o�^�^�C�v = '2'(�����ڋq�w��)�̏ꍇ�̂݁A�S�|�R�j�̃`�F�b�N���s��]
            if (WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(this.commissionCampaignInfo.registType))
            {
                //  �S�|�R�j�@@���������͂���Ă���ꍇ�A�w�L�����y�[�����̓��̓G���[�x��O���X���[����B
                if (!WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.campaignName))
                {
                    log.debug("�L�����y�[�����̓��̓G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02725,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �L�����y�[������ = " + this.commissionCampaignInfo.campaignName);
                }
            }
        }

        //[���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^) or '1'(�ύX)�̏ꍇ]
        if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag))
        {

            //  �T-�P�j �o�^�^�C�v�� '1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̏ꍇ�A��O���X���[����B
            if (!(WEB3AccInfoRegistTypeDef.INDIVIDUAL.equals(this.commissionCampaignInfo.registType)
                    || WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(this.commissionCampaignInfo.registType)))
            {
                log.debug("�o�^�^�C�v��'1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02711,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �o�^�^�C�v = " + this.commissionCampaignInfo.registType);
            }

            //�U�j�@@�Ώۊ���From�ATo�̃`�F�b�N
            //  �U�|�P�j�@@�Ώۊ���From,�Ώۊ���To�����͂��ꂽ�ꍇ�A
            //          �Ώۊ���From �� �Ώۊ���To�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
            if (this.commissionCampaignInfo.targetPeriodFrom != null
                && this.commissionCampaignInfo.targetPeriodTo != null)
            {
                if (WEB3DateUtility.compareToDay(this.commissionCampaignInfo.targetPeriodFrom,
                    this.commissionCampaignInfo.targetPeriodTo) > 0)
                {
                    log.debug("�Ώۊ��ԃG���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �Ώۊ���From = " + this.commissionCampaignInfo.targetPeriodFrom
                        + " �Ώۊ���To = " + this.commissionCampaignInfo.targetPeriodTo);
                }
            }

            //�V�j�@@�������̃`�F�b�N
            //�V�|�P�j�@@�����͂̏ꍇ�A�w�����������̓G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.commissionCampaignInfo.collectRate))
            {
                log.debug("�������������͂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������ = " + this.commissionCampaignInfo.collectRate);
            }

            //�V�|�Q�j�@@0�`100�̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B
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
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
