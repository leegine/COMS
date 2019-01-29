head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX����ظ���(WEB3AdminAccInfoCampaignIndiviCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
Revision History : 2007/2/1  ���f��No.165
Revision History : 2007/2/5  ���f��No.181
Revision History : 2007/02/28 ����(SCS)���f��No.203
*/
package webbroker3.accountinfo.message;

import java.sql.Timestamp;

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
 * (�Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX����ظ���)<BR>
 * �Ǘ��҂��q�l���萔�������L�����y�[���ʌڋq�w��ύX�������N�G�X�g<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviCompleteRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignIndiviComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312038L;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

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
     * @@roseuid 45C087600341
     */
    public WEB3AdminAccInfoCampaignIndiviCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignIndiviCompleteResponse(this);
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
     * �@@�S�|�P�j�@@101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02709<BR>
     * <BR>
     * [�o�^�^�C�v = '1'(�ʌڋq�w��)�̏ꍇ�̂݁A�S�|�Q�j�̃`�F�b�N���s��] <BR>
     * �@@�S�|�Q�j�@@�����͂̏ꍇ�A�w�L�����y�[�����̖����̓G���[�x��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02712<BR>
     * <BR>
     * [�o�^�^�C�v = '2'(�����ڋq�w��)�̏ꍇ�̂݁A�S�|�R�j�̃`�F�b�N���s��]<BR>
     * �@@�S�|�R�j�@@���������͂���Ă���ꍇ�A�w�L�����y�[�����̓��̓G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02725<BR>
     * <BR>
     * <BR>
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^) or '1'(�ύX)�̏ꍇ]<BR>
     * <BR>
     * �T�j �o�^�^�C�v�̃`�F�b�N<BR>
     *   �T-�P�j �o�^�^�C�v�� '1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02711<BR>
     * <BR>
     * �U�j�@@�Ώۊ���From�ATo�̃`�F�b�N<BR>
     * �@@�U�|1�j�@@�Ώۊ���From,�Ώۊ���To�����͂��ꂽ�ꍇ�A�Ώۊ���From �� �Ώۊ���To�̏ꍇ�A<BR>
     *  �w�Ώۊ��ԃG���[�x��O���X���[����B <BR>
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
     * @@roseuid 45AED78403E3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j �X�V�����t���O�̃`�F�b�N
        //  �P-�P�j �X�V�����t���O�� '0' �� '1' �� '2'�ȊO�̏ꍇ�A��O���X���[����B
        if (this.updateFlag != null)
        {
            if (!(WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.DELETE.equals(this.updateFlag)))
            {
                log.debug("�X�V�����t���O�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02710,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�X�V�����t���O�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //[���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
        if (this.updateFlag != null)
        {
            if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag))
            {
                //�Q�j�@@���X�R�[�h�̃`�F�b�N
                //�@@�Q�|�P�j�@@�����͂̏ꍇ�A�w���X�R�[�h�����̓G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.branchCode == null)
                {
                    log.debug("�X���X�R�[�h�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "���X�R�[�h�����w��ł��B");
                }

                //�@@�Q�|�Q�j�@@�R���ȊO�̏ꍇ�A�w���X�R�[�h�����G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.branchCode.length() != 3)
                {
                    log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���X�R�[�h�̃T�C�Y���s���ł��B");
                }

                //�R�j�@@�ڋq�R�[�h�̃`�F�b�N
                //�@@�R�|�P�j�@@�����͂̏ꍇ�A�w�ڋq�R�[�h�����̓G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.accountCode == null)
                {
                    log.debug("�ڋq�R�[�h�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�ڋq�R�[�h�����w��ł��B");
                }

                //�@@�R�|�Q�j�@@������6�łȂ��ꍇ�A�w�ڋq�R�[�h�����G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.accountCode.length() != 6)
                {
                    log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                }

                //�S�j�@@�L�����y�[�����̂̃`�F�b�N
                //�@@�S�|�P�j�@@101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.campaignName != null)
                {
                    if (WEB3StringTypeUtility.getByteLength(
                        this.commissionCampaignInfo.campaignName) >= 101)
                    {
                        log.debug("�L�����y�[�����̌����G���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                            this.getClass().getName()  + STR_METHOD_NAME,
                            "�L�����y�[�����̌����G���[�B");
                    }
                }

                //[�o�^�^�C�v = '1'(�ʌڋq�w��)�̏ꍇ�̂݁A�S�|�Q�j�̃`�F�b�N���s��]
                if (this.commissionCampaignInfo.registType != null)
                {
                    if (WEB3AccInfoRegistTypeDef.INDIVIDUAL.equals(
                        this.commissionCampaignInfo.registType))
                    {
                        //�@@�S�|�Q�j�@@�����͂̏ꍇ�A�w�L�����y�[�����̖����̓G���[�x��O���X���[����B
                        if (this.commissionCampaignInfo.campaignName == null)
                        {
                            log.debug("�L�����y�[�����̖����̓G���[�B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02712,
                                this.getClass().getName()  + STR_METHOD_NAME,
                                "�L�����y�[�����̖����̓G���[�B");
                        }
                    }
                }

                if (this.commissionCampaignInfo.registType != null)
                {
                    //[�o�^�^�C�v = '2'(�����ڋq�w��)�̏ꍇ�̂݁A�S�|�R�j�̃`�F�b�N���s��]
                    if (WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(
                        this.commissionCampaignInfo.registType))
                    {
                        //�@@�S�|�R�j�@@���������͂���Ă���ꍇ�A�w�L�����y�[�����̓��̓G���[�x��O���X���[����B
                        if (this.commissionCampaignInfo.campaignName != null)
                        {
                            log.debug("�L�����y�[�����̓��̓G���[�B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02725,
                                this.getClass().getName()  + STR_METHOD_NAME,
                                "�L�����y�[�����̓��̓G���[�B");
                        }
                    }
                }
            }
        }

        //[���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^) or '1'(�ύX)�̏ꍇ]
        if (this.updateFlag != null)
        {
            if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(this.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(this.updateFlag))
            {

                //�T�j �o�^�^�C�v�̃`�F�b�N
                //  �T-�P�j �o�^�^�C�v�� '1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̏ꍇ�A��O���X���[����B
                if (this.commissionCampaignInfo != null)
                {
                    if (this.commissionCampaignInfo.registType != null)
                    {
                        if (!(WEB3AccInfoRegistTypeDef.INDIVIDUAL.equals(this.commissionCampaignInfo.registType)
                            || WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(this.commissionCampaignInfo.registType)))
                        {
                            log.debug("�o�^�^�C�v��'1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̒l�ł��B");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02711,
                                this.getClass().getName()  + STR_METHOD_NAME,
                                "�o�^�^�C�v��'1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̒l�ł��B");
                        }
                    }
                }

                //�U�j�@@�Ώۊ���From�ATo�̃`�F�b�N
                //�@@�U�|1�j�@@�Ώۊ���From,�Ώۊ���To�����͂��ꂽ�ꍇ�A
                //   �Ώۊ���From �� �Ώۊ���To�̏ꍇ�A�w�Ώۊ��ԃG���[�x��O���X���[����B
                if (this.commissionCampaignInfo.targetPeriodFrom != null
                    && this.commissionCampaignInfo.targetPeriodTo != null)
                {
                    if (WEB3DateUtility.compareToDay(
                        this.commissionCampaignInfo.targetPeriodFrom,
                        this.commissionCampaignInfo.targetPeriodTo) > 0)
                    {
                        log.debug("�Ώۊ��ԃG���[�B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02715,
                            this.getClass().getName()  + STR_METHOD_NAME,
                            "�Ώۊ��ԃG���[�B");
                    }
                }

                //�V�j�@@�������̃`�F�b�N
                //�@@�V�|�P�j�@@�����͂̏ꍇ�A�w�����������̓G���[�x��O���X���[����B
                if (this.commissionCampaignInfo.collectRate == null)
                {
                    log.debug("�������������͂ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02080,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�������������͂ł��B");
                }

                //�@@�V�|�Q�j�@@0�`100�̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B
                if (WEB3StringTypeUtility.isInteger(this.commissionCampaignInfo.collectRate))
                {
                    String l_strCollectRate = this.commissionCampaignInfo.collectRate;
                    int l_intCollectRate = Integer.parseInt(l_strCollectRate);
                    if (l_intCollectRate > 100 || l_intCollectRate < 0)
                    {
                        log.debug("�������̒l���s���ł��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                            this.getClass().getName()  + STR_METHOD_NAME,
                            "�������̒l���s���ł��B");
                    }
                }
                else
                {
                    log.debug("�������̒l���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�������̒l���s���ł��B");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
