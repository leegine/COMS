head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���N�G�X�g(WEB3AdminAccInfoAccEstablishSearchListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  �����q(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;

import webbroker3.accountinfo.define.WEB3AccInfoDataContentDef;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoLoginLockDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���N�G�X�g)<BR>
 * �Ǘ��҂��q�l���V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�����ꗗ���N�G�X�g<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishSearchListRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccEstablishSearchListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082163L;

    /**
     * @@roseuid 418F39EF0290
     */
    public WEB3AdminAccInfoAccEstablishSearchListRequest()
    {

    }

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     */
    public String traderCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountNameKana;

    /**
     * (�������)<BR>
     * �������<BR>
     * <BR>
     * 0�F�@@�S��<BR>
     * 1�F�@@�l�q<BR>
     * 2�F�@@�@@�l�q<BR>
     */
    public String accountTypeCode;

    /**
     * (�f�[�^���e�ԍ�)<BR>
     * �f�[�^���e�ԍ� <BR>
     * <BR>
     * 00�F�@@�f�[�^���e���I��<BR>
     * 01�F�@@�V�K�����J�݈ē��p�f�[�^<BR>
     * 02�F�@@�U���݃J�[�h�p�f�[�^<BR>
     * 03�F�@@�����ڊǈē��p�f�[�^<BR>
     */
    public String dataContentDiv;

    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR>
     */
    public Date accountOpenDateFrom;

    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR>
     */
    public Date accountOpenDateTo;

    /**
     * (���O�C�����b�N�敪)<BR>
     * ���O�C�����b�N�敪<BR>
     * <BR>
     * 0�F�@@�S��<BR>
     * 1�F�@@���O�C�����b�N�q<BR>
     */
    public String loginLockDiv;

    /**
     * (�\�[�g�L�[)<BR>
     * ���q�l���\�[�g�L�[[]<BR>
     * �Ώۍ��ځF���X�R�[�h�A���҃R�[�h�A�ڋq�R�[�h�A�����J�ݓ�<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;

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
     * (MAX��������)<BR>
     * MAX��������<BR>
     */
    public String maxCount;

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j �u���X�R�[�h�v�`�F�b�N<BR>
     * �P�|�P�j ���X�R�[�h�̗v�f�� = 0 or ���X�R�[�h = null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_1757<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00833<BR>
     * �P�|�Q�j �e�v�f�ɐ����ȊO�̕���������ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q�j �u���҃R�[�h�v�`�F�b�N<BR>
     * �Q�|�P�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_01913<BR>
     * <BR>
     * �R�j �u�ڋq�R�[�h �v�`�F�b�N<BR>
     * �R�|�P�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * �S�j �u�ڋq���i�J�i�j�v�`�F�b�N<BR>
     * �S�|�P�j �S�p�ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_01691<BR>
     * <BR>
     * �T�j �u������ʁv�`�F�b�N<BR>
     * �T�|�P�j ������� != �i0�A1�A2�j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_02668<BR>
     * <BR>
     * �U�j �u�f�[�^���e�ԍ��v�`�F�b�N<BR>
     * �U�|�P�j �f�[�^���e�ԍ� != �i00�A01�A02�A03�j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_02669<BR>
     * <BR>
     * �V�j �u���O�C�����b�N�敪�v�`�F�b�N<BR>
     * �V�|�P�j ���O�C�����b�N�敪 != �i0�A1�j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_02670<BR>
     * <BR>
     * �W�j �u�v���y�[�W�ԍ��v�`�F�b�N<BR>
     * �W�|�P�j �v���y�[�W�ԍ� = null or �v���y�[�W�ԍ� <= 0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00089<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00616<BR>
     * �W�|�Q�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �X�j�y�[�W���\���s���`�F�b�N<BR>
     * �X�|�P�j �y�[�W���\���s�� = null or �y�[�W���\���s�� <= 0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_02224<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00617<BR>
     * �X�|�Q�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �P�O�j�u�\�[�g�L�[�v�`�F�b�N<BR>
     * �P�O�|�P�j �\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00231<BR>
     * �P�O�|�Q�j �i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00232<BR>
     * �P�O�|�R�j �\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * �@@�@@�P�O�|�R�|�P�j �\�[�g�L�[.validate()���R�[������B<BR>
     * �@@�@@�P�O�|�R�|�Q�j �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E���҃R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�����J�ݓ� <BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@ �@@�@@�@@tag:   BUSINESS_ERROR_00086<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j �u���X�R�[�h�v�`�F�b�N
        // �P�|�P�j ���X�R�[�h�̗v�f�� = 0 or ���X�R�[�h = null �̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.debug("�u���X�R�[�h�v = " + this.branchCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName()  + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        if (this.branchCode.length == 0)
        {
            //��O
            log.debug("�u���X�R�[�h�v = " + this.branchCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName()  + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f�����O�ł��B");
        }

        //�P�|�Q�j �e�v�f�ɐ����ȊO�̕���������ꍇ�A��O���X���[����B
        for (int i = 0; i < this.branchCode.length; i ++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("�u���X�R�[�h�v = " + this.branchCode[i]);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }

        // �Q�j �u���҃R�[�h�v�`�F�b�N
        // �Q�|�P�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (this.traderCode != null && !WEB3StringTypeUtility.isDigit(this.traderCode))
        {
            log.debug("�u���҃R�[�h�v = " + this.traderCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01913,
                this.getClass().getName()  + STR_METHOD_NAME,
                "���҃R�[�h�i������j�̕����킪�s���ł��B");
        }

        // �R�j �u�ڋq�R�[�h �v�`�F�b�N
        // �R�|�P�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�u�ڋq�R�[�h �v = " + this.accountCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }

        // �S�j �u�ڋq���i�J�i�j�v�`�F�b�N
        // �S�|�P�j �S�p�ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (this.accountNameKana != null && !WEB3StringTypeUtility.isMulti(this.accountNameKana))
        {
            log.debug("�u�ڋq���i�J�i�j�v = " + this.accountNameKana);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01691,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�ڋq�����S�p�����ł͂���܂���B");
        }

        // �T�j �u������ʁv�`�F�b�N
        // �T�|�P�j ������� != �i0�A1�A2�j�̏ꍇ�A��O���X���[����B
        String l_strOther = MainAccountTypeEnum.OTHER.intValue() + "";
        String l_strIndividual = MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "";
        String l_strCorporate = MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "";
        if (!l_strOther.equals(this.accountTypeCode)
            && !l_strIndividual.equals(this.accountTypeCode)
            && !l_strCorporate.equals(this.accountTypeCode))
        {
            log.debug("�u������ʁv = " + this.accountTypeCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02668,
                this.getClass().getName()  + STR_METHOD_NAME,
                "������ʂ����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �U�j �u�f�[�^���e�ԍ��v�`�F�b�N
        // �U�|�P�j �f�[�^���e�ԍ� != �i00�A01�A02�A03�j�̏ꍇ�A��O���X���[����B
        if (!WEB3AccInfoDataContentDef.DATA_CONTENT_NOT_SELECT.equals(this.dataContentDiv)
                && !WEB3AccInfoDataContentDef.NEW_ACC_OPEN_GUIDANCE_DATA.equals(this.dataContentDiv)
                && !WEB3AccInfoDataContentDef.TRANSFER_CARD_DATA.equals(this.dataContentDiv)
                && !WEB3AccInfoDataContentDef.ACCOUNT_TRANSFER_GUIDANCE_DATA.equals(this.dataContentDiv))
        {
            log.debug("�u�f�[�^���e�v = " + this.dataContentDiv);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02669,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�f�[�^���e�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �V�j �u���O�C�����b�N�敪�v�`�F�b�N
        // �V�|�P�j ���O�C�����b�N�敪 != �i0�A1�j�̏ꍇ�A��O���X���[����B
        if (!WEB3AccInfoLoginLockDivDef.ALL.equals(this.loginLockDiv)
                && !WEB3AccInfoLoginLockDivDef.LOGIN_LOCK_ACCOUNT.equals(this.loginLockDiv))
        {
            log.debug("�u���O�C�����b�N�敪�v = " + this.loginLockDiv);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02670,
                this.getClass().getName()  + STR_METHOD_NAME,
                "���O�C�����b�N�敪�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �W�j �u�v���y�[�W�ԍ��v�`�F�b�N
        // �W�|�P�j �v���y�[�W�ԍ� = null �̏ꍇ�A��O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�u�v���y�[�W�ԍ��v = " + this.pageIndex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        // �W�|�Q�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�u�v���y�[�W�ԍ��v = " + this.pageIndex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        // �v���y�[�W�ԍ� <= 0 �̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�u�v���y�[�W�ԍ��v = " + this.pageIndex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        // �X�j�y�[�W���\���s���`�F�b�N
        // �X�|�P�j �y�[�W���\���s�� = null ��O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�u�y�[�W���\���s���`�v = " + this.pageSize);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }

        // �X�|�Q�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�u�y�[�W���\���s���`�v = " + this.pageSize);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        // �y�[�W���\���s�� <= 0 �̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�u�y�[�W���\���s���`�v = " + this.pageSize);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        // �P�O�j�u�\�[�g�L�[�v�`�F�b�N

        //�P�O�|�P�j �\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�u�\�[�g�L�[�v = " + this.sortKeys);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        // �P�O�|�Q�j �i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�u�\�[�g�L�[�v = " + this.sortKeys);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        // �P�O�|�R�j �\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        // �@@�@@�P�O�|�R�|�P�j �\�[�g�L�[.validate()���R�[������B
        //�@@ �@@�P�O�|�R�|�Q�j �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
        //�@@�@@ �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h
        //�@@�@@�@@ �@@�@@�@@�@@�@@�@@�@@�@@�E���҃R�[�h
        //�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@�@@�E�ڋq�R�[�h
        //�@@�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@�E�����J�ݓ�
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            this.sortKeys[i].validate();

            if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.TRADER_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE.equals(this.sortKeys[i].keyItem))
            {
                log.debug("�u�\�[�g�L�[�v = " + this.sortKeys);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoAccEstablishSearchListResponse(this);
    }
}
@
