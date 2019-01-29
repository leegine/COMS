head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������X�V���(WEB3BondDomesticProductUpdateInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/09 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.200
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3ProspectusCheckDivDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����������X�V���)<BR>
 * �����������X�V���<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3BondDomesticProductUpdateInfo extends Message
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticProductUpdateInfo.class);

    /**
     * (�戵�敪)<BR>
     * �戵�敪<BR>
     * <BR>
     * 0�F�s��<BR>�@@
     * 2�F�ڋq<BR>
     * <BR>
     */
    public String tradeHandleDiv;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 3�F����<BR>
     */
    public String dealingType;

    /**
     * (����J�n���iWEB3�j)<BR>
     * ����J�n���iWEB3�j<BR>
     */
    public Date recruitStartDateWEB3;

    /**
     * (����I�����iWEB3�j)<BR>
     * ����I�����iWEB3�j<BR>
     */
    public Date recruitEndDateWEB3;

    /**
     * (����J�n���i�C���^�[�l�b�g�j)<BR>
     * ����J�n���i�C���^�[�l�b�g�j<BR>
     */
    public Date recruitStartDateInterNet;

    /**
     * (����I�����i�C���^�[�l�b�g�j)<BR>
     * ����I�����i�C���^�[�l�b�g�j<BR>
     */
    public Date recruitEndDateInterNet;

    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;

    /**
     * (�������iWEB3))<BR>
     * �������iWEB3)<BR>
     */
    public String productNameWEB3;

    /**
     * (�\���P��)<BR>
     * �\���P��<BR>
     */
    public String applyUnit;

    /**
     * (�Œ�z��)<BR>
     * �Œ�z��<BR>
     */
    public String minFaceAmount;

    /**
     * (�ō��z��)<BR>
     * �ō��z��<BR>
     */
    public String maxFaceAmount;

    /**
     * (�ژ_�����{���`�F�b�N�敪)<BR>
     * �ژ_�����{���`�F�b�N�敪<BR>
     * <BR>
     * 0�F�ژ_�������`�F�b�N���Ȃ�(�v)<BR>
     * 1�F�ژ_�������`�F�b�N����(�s�v�j<BR>
     */
    public String prospectusCheckDiv;

    /**
     * (�����������X�V���)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4663798C0157
     */
    public WEB3BondDomesticProductUpdateInfo()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�戵�敪�`�F�b�N<BR>
     * �@@�@@�E�戵�敪 == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02843<BR>
     * �@@�@@�E�戵�敪�� �f�s�f or �f�ڋq�f�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02844<BR>
     * <BR>
     * �Q�j�����敪�`�F�b�N<BR>
     * �@@�@@�E�����敪 == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_01402<BR>
     * �@@�@@�E�����敪���f����f�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_01403<BR>
     * <BR>
     * �R�j����J�n���iWEB3�j�`�F�b�N<BR>
     * �@@�@@�E����J�n���iWEB3) == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02845<BR>
     * <BR>
     * �S�j����I�����iWEB3)�`�F�b�N<BR>
     * �@@�@@�E����I�����iWEB3) == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02846<BR>
     * <BR>
     * �T�j����J�n���i�C���^�[�l�b�g�j�`�F�b�N<BR>
     * �@@�@@�E����J�n���i�C���^�[�l�b�g) == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02847<BR>
     * <BR>
     * �U�j����I�����i�C���^�[�l�b�g)�`�F�b�N<BR>
     * �@@�@@�E����I�����i�C���^�[�l�b�g) == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02848<BR>
     * <BR>
     * �V�j��n���`�F�b�N<BR>
     * �@@�@@�E��n�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_01079<BR>
     * <BR>
     * �W�j�������iWEB3)�`�F�b�N<BR>
     * �@@�@@�E�������iWEB3�j == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02849<BR>
     * �@@�@@�E64�o�C�g�ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02850<BR>
     * <BR>
     * �X�j�\���P�ʃ`�F�b�N<BR>
     * �@@�@@�E�\���P�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02851<BR>
     * �@@�@@�E�\���P�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02584<BR>
     * �@@�@@�E�\���P�ʂ������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02585<BR>
     * �@@�@@�E�\���P�ʁ����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02586<BR>
     * <BR>
     * �P�O�j�Œ�z�ʃ`�F�b�N<BR>
     * �@@�@@�E�Œ�z�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02852<BR>
     * �@@�@@�E�Œ�z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02587<BR>
     * �@@�@@�E�Œ�z�ʂ������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02588<BR>
     * �@@�@@�E�Œ�z�ʂ����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02589<BR>
     * <BR>
     * �P�P�j�ō��z�ʃ`�F�b�N<BR>
     * �@@�@@�E�ō��z�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02853<BR>
     * �@@�@@�E�ō��z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02590<BR>
     * �@@�@@�E�ō��z�ʂ������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02591<BR>
     * �@@�@@�E�ō��z�ʂ����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02592<BR>
     * <BR>
     * �P�Q�j�ژ_�����{���`�F�b�N�敪�`�F�b�N<BR>
     * �@@�@@�E�ژ_�����{���`�F�b�N�敪 == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02854<BR>
     * �@@�@@�E�ژ_�����{���`�F�b�N�敪���f�ژ_�������`�F�b�N���Ȃ��f or �f�ژ_������<BR>
     * �@@�@@�`�F�b�N����f�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02855<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4663AE460148
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�戵�敪�`�F�b�N
        // �E�戵�敪 == null �̏ꍇ�A��O���X���[����B
        if (this.tradeHandleDiv == null)
        {
            log.debug("�戵�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02843,
                this.getClass().getName() + STR_METHOD_NAME,
                "�戵�敪�����w��ł��B");
        }

        // �E�戵�敪�� �f�s�f or �f�ڋq�f�łȂ��ꍇ�A��O���X���[����B
        if (!(WEB3TradeHandleDivDef.DISABLED.equals(this.tradeHandleDiv)
            || WEB3TradeHandleDivDef.MANAGER_CUSTOMER.equals(this.tradeHandleDiv)))
        {
            log.debug("�戵�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02844,
                this.getClass().getName() + STR_METHOD_NAME,
                "�戵�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �Q�j�����敪�`�F�b�N
        // �E�����敪 == null �̏ꍇ�A��O���X���[����B
        if (this.dealingType == null)
        {
            log.debug("�����敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01402,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����敪�����w��ł��B");
        }

        // �E�����敪���f����f�łȂ��ꍇ�A��O���X���[����B
        if (!WEB3BondTradeTypeDef.RECRUIT.equals(this.dealingType))
        {
            log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �R�j����J�n���iWEB3�j�`�F�b�N
        // �E����J�n���iWEB3) == null �̏ꍇ�A��O���X���[����B
        if (this.recruitStartDateWEB3 == null)
        {
            log.debug("����J�n���iWEB3)�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02845,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���iWEB3)�����w��ł��B");
        }

        // �S�j����I�����iWEB3)�`�F�b�N
        //�@@�E����I�����iWEB3) == null �̏ꍇ�A��O���X���[����B
        if (this.recruitEndDateWEB3 == null)
        {
            log.debug("����I�����iWEB3)�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02846,
                this.getClass().getName() + STR_METHOD_NAME,
                "����I�����iWEB3)�����w��ł��B");
        }

        // �T�j����J�n���i�C���^�[�l�b�g�j�`�F�b�N
        // �E����J�n���i�C���^�[�l�b�g) == null �̏ꍇ�A��O���X���[����B
        if (this.recruitStartDateInterNet == null)
        {
            log.debug("����J�n���i�C���^�[�l�b�g)�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02847,
                this.getClass().getName() + STR_METHOD_NAME,
                "����J�n���i�C���^�[�l�b�g)�����w��ł��B");
        }

        // �U�j����I�����i�C���^�[�l�b�g)�`�F�b�N
        // �E����I�����i�C���^�[�l�b�g) == null �̏ꍇ�A��O���X���[����B
        if (this.recruitEndDateInterNet == null)
        {
            log.debug("����I�����i�C���^�[�l�b�g)�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02848,
                this.getClass().getName() + STR_METHOD_NAME,
                "����I�����i�C���^�[�l�b�g)�����w��ł��B");
        }

        // �V�j��n���`�F�b�N
        // �E��n�� == null �̏ꍇ�A��O���X���[����B
        if (this.deliveryDate == null)
        {
            log.debug("��n�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + STR_METHOD_NAME,
                "��n�������w��ł��B");
        }

        // �W�j�������iWEB3)�`�F�b�N
        // �E�������iWEB3�j == null �̏ꍇ�A��O���X���[����B
        if (this.productNameWEB3 == null)
        {
            log.debug("�������iWEB3)�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02849,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������iWEB3)�����w��ł��B");
        }

        // �E64�o�C�g�ȓ��łȂ��ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.productNameWEB3) > 64)
        {
            log.debug("�������iWEB3)�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02850,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������iWEB3)�̃T�C�Y���s���ł��B");
        }

        // �X�j�\���P�ʃ`�F�b�N
        // �E�\���P�� == null �̏ꍇ�A��O���X���[����B
        if (this.applyUnit == null)
        {
            log.debug("�\���P�ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02851,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\���P�ʂ����w��ł��B");
        }

        // �E�\���P�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.applyUnit) > 11)
        {
            log.debug("�\���P�ʂ̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02584,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\���P�ʂ̃T�C�Y���s���ł��B");
        }

        // �E�\���P�ʂ������łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.applyUnit))
        {
            log.debug("�\���P�ʂ������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02585,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\���P�ʂ������ȊO�̒l�ł��B");
        }

        // �E�\���P�ʁ����O�̏ꍇ�A��O���X���[����B
        if (Long.parseLong(this.applyUnit) <= 0)
        {
            log.debug("�\���P�ʂ�0�ȉ��̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02586,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\���P�ʂ�0�ȉ��̒l�ł��B");
        }

        // �P�O�j�Œ�z�ʃ`�F�b�N
        // �E�Œ�z�� == null �̏ꍇ�A��O���X���[����B
        if (this.minFaceAmount == null)
        {
            log.debug("�Œ�z�ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02852,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Œ�z�ʂ����w��ł��B");
        }

        // �E�Œ�z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.minFaceAmount) > 11)
        {
            log.debug("�Œ�z�ʂ̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02587,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Œ�z�ʂ̃T�C�Y���s���ł��B");
        }

        // �E�Œ�z�ʂ������łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.minFaceAmount))
        {
            log.debug("�Œ�z�ʂ������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02588,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Œ�z�ʂ������ȊO�̒l�ł��B");
        }

        // �E�Œ�z�ʂ����O�̏ꍇ�A��O���X���[����B
        if (Long.parseLong(this.minFaceAmount) < 0)
        {
            log.debug("�Œ�z�ʂ�0��菬�����l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02589,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Œ�z�ʂ�0��菬�����l�ł��B");
        }

        // �P1�j�ō��z�ʃ`�F�b�N
        // �E�ō��z�� == null �̏ꍇ�A��O���X���[����B
        if (this.maxFaceAmount == null)
        {
            log.debug("�ō��z�ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02853,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ō��z�ʂ����w��ł��B");
        }

        // �E�ō��z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(this.maxFaceAmount) > 11)
        {
            log.debug("�ō��z�ʂ̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02590,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ō��z�ʂ̃T�C�Y���s���ł��B");
        }

        // �E�ō��z�ʂ������łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.maxFaceAmount))
        {
            log.debug("�ō��z�ʂ������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02591,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ō��z�ʂ������ȊO�̒l�ł��B");
        }

        // �E�ō��z�ʂ����O�̏ꍇ�A��O���X���[����B
        if (Long.parseLong(this.maxFaceAmount) < 0)
        {
            log.debug("�ō��z�ʂ�0��菬�����l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02592,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ō��z�ʂ�0��菬�����l�ł��B");
        }

        // �P�Q�j�ژ_�����{���`�F�b�N�敪�`�F�b�N
        // �E�ژ_�����{���`�F�b�N�敪 == null �̏ꍇ�A��O���X���[����B
        if (this.prospectusCheckDiv == null)
        {
            log.debug("�ژ_�����{���`�F�b�N�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02854,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ژ_�����{���`�F�b�N�敪�����w��ł��B");
        }

        // �E�ژ_�����{���`�F�b�N�敪���f�ژ_�������`�F�b�N���Ȃ��f or �f
        // �ژ_�������`�F�b�N����f�łȂ��ꍇ�A��O���X���[����B
        if (!(WEB3ProspectusCheckDivDef.PROSPECTUS_CHECK.equals(this.prospectusCheckDiv)
            || WEB3ProspectusCheckDivDef.PROSPECTUS_NOT_CHECK.equals(this.prospectusCheckDiv)))
        {
            log.debug("�ژ_�����{���`�F�b�N�敪�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02855,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ژ_�����{���`�F�b�N�敪�����݂��Ȃ��R�[�h�l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
