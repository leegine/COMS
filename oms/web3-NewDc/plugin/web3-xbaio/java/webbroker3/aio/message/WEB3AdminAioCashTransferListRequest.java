head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���ꗗ���ʃ��N�G�X�g(WEB3AdminAioCashTransferListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/03 �����q (���u) �V�K�쐬�@@�d�l�ύX���f�� NO.693 NO.700
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AdminAioCashStatusDef;
import webbroker3.aio.define.WEB3AdminAioOrderTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���o���ꗗ���ʃ��N�G�X�g)<BR>
 * ���o���ꗗ���ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �����q (���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_aio_cash_transfer_list";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashTransferListRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��<BR>
     */
    public Date deliveryDate;

    /**
     * (�������)<BR>
     * ��ʂɂđI�����ꂽ�������<BR>
     * <BR>
     * �|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|<BR>
     * 000�F �S��<BR>
     * ��������<BR>
     * 100�F �����S��<BR>
     * 101�F SONAR����<BR>
     * 102�F �o�[�`��������<BR>
     * 103�F �l�b�g����<BR>
     * 104�F �U��(����؋�������a���)<BR>
     * 105�F �ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)<BR>
     * 106�F ���̑��U��(X����a���)<BR>
     * ���o����<BR>
     * 200�F �o���S��<BR>
     * 201�F �o��<BR>
     * 202�F �U��(�a������犔��؋���)<BR>
     * 203�F �ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)<BR>
     * 204�F ���̑��U��(�a�������X)<BR>
     * �|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|�|<BR>
     * <BR>
     */
    public String orderType;

    /**
     * (�X�e�[�^�X)<BR>
     * ��ʂɂđI�����ꂽ�X�e�[�^�X<BR>
     * <BR>
     * �|�|�|�|�|<BR>
     * 0�F �S��<BR>
     * 1�F ����<BR>
     * 2�F ������<BR>
     * 9�F �G���[<BR>
     * �|�|�|�|�|<BR>
     */
    public String cashinoutStatus;

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
     * @@roseuid 45C3F158001F
     */
    public WEB3AdminAioCashTransferListRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j �u���X�R�[�h�v�`�F�b�N <BR>
     * �P�|�P�j ���X�R�[�h�̗v�f�� = 0 or ���X�R�[�h = null <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01757<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00833<BR>
     * �P�|�Q�j �e�v�f�ɐ����ȊO�̕���������ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q�j �u�ڋq�R�[�h �v�`�F�b�N <BR>
     * �@@�E�ڋq�R�[�h != null �̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �Q�|�P�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01043<BR>
     * �Q�|�Q�j �U���ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00836<BR>
     * �Q�|�R�j ���X�R�[�h�̗v�f�� != 1 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02728<BR>
     * <BR>
     * �R�j �u��n���v�`�F�b�N <BR>
     * �R�|�P�j ��n�� = null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01079<BR>
     * <BR>
     * �S�j �u������ʁv�`�F�b�N <BR>
     * �S�|�P�j ������� != �i000�A100�A101�A102�A103�A104�A105�A106�A200�A201�A202�A203�A204�j�̏�<BR>
     * ���A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01439<BR>
     * <BR>
     * �T�j �u�X�e�[�^�X�v�`�F�b�N  <BR>
     * �T�|�P�j �X�e�[�^�X != �i0�A1�A2�A9�j�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * �U�j �u�v���y�[�W�ԍ��v�`�F�b�N <BR>
     * �U�|�P�j �v���y�[�W�ԍ� = null or �v���y�[�W�ԍ� <= 0
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00089<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00616<BR>
     * �U�|�Q�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �V�j �u�y�[�W���\���s���v�`�F�b�N <BR>
     * �V�|�P�j �y�[�W���\���s�� = null or �y�[�W���\���s�� <= 0
     * �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02224<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00091<BR>
     * �V�|�Q�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * @@roseuid 45B6F80500FE
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�|�P�j ���X�R�[�h�̗v�f�� = 0 or ���X�R�[�h = null �̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        if (this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f�����O�ł��B");
        }

        // �e�v�f�ɐ����ȊO�̕���������ꍇ�A��O���X���[����B
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }

        // �ڋq�R�[�h != null �̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.accountCode != null)
        {
            // �Q�|�P�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B00811
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            }

            // �Q�|�Q�j �U���ȊO�̏ꍇ�A��O���X���[����B
            if (this.accountCode.length() != 6)
            {
                log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            }

            // �Q�|�R�j ���X�R�[�h�̗v�f�� != 1 �̏ꍇ�A��O���X���[����B
            if (this.branchCode.length != 1)
            {
                log.debug("���X�R�[�h�̗v�f����1�ȊO�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02728,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̗v�f����1�ȊO�ł��B");
            }
        }

        // �R�j �u��n���v�`�F�b�N
        // �R�|�P�j ��n�� = null �̏ꍇ�A��O���X���[����B
        if (this.deliveryDate == null)
        {
            log.debug("��n�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��n�������w��ł��B");
        }

        // �S�|�P�j ������� != �i000�A100�A101�A102�A103�A104�A105�A106�A200�A201�A202�A203�A204�j�̏ꍇ�A��O���X���[����B
        if (!WEB3AdminAioOrderTypeDef.ALL.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_ALL.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_FX_GUARANTY_TO_DESPOSIT.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_OTHER_X_TO_ACCOUNT_BALANCE.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_TRANSFER_DEPOSIT_TO_MARGIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_ALL.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_FX_GUARANTY_TO_DESPOSIT.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.NET_CASHIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.SONAR_CASHIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(this.orderType))
        {
            log.debug("������ʂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01439,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������ʂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �T�|�P�j �X�e�[�^�X != �i0�A1�A2�A9�j�̏ꍇ�A��O���X���[����B
        if (!WEB3AdminAioCashStatusDef.ALL.equals(this.cashinoutStatus)
            && !WEB3AdminAioCashStatusDef.COMPLETE.equals(this.cashinoutStatus)
            && !WEB3AdminAioCashStatusDef.ERROR.equals(this.cashinoutStatus)
            && !WEB3AdminAioCashStatusDef.NOT_TRANSACTION.equals(this.cashinoutStatus))
        {
            log.debug("�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // �U�|�P�j �v���y�[�W�ԍ� = null �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        //�U�|�Q�j �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        else if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        //�v���y�[�W�ԍ� <= 0 �̏ꍇ�A��O���X���[����B
        else if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        // �y�[�W���\���s�� = null �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        // �����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        else if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        // �y�[�W���\���s�� <= 0 �̏ꍇ�A��O���X���[����B
        else if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioCashTransferListResponse(this);
    }
}
@
