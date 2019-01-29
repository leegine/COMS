head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMultiMailAddressInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�h���X���(WEB3AccInfoMultiMailAddressInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.217
Revision History : 2007/08/30 ���g (���u) �d�l�ύX�Ǘ�No.221
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ImportantConnectionMailFlagDef;
import webbroker3.common.define.WEB3InformationMail2FlagDef;
import webbroker3.common.define.WEB3OrderExeFlagDef;
import webbroker3.common.define.WEB3OrderUnexeFlagDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����A�h���X���)<BR>
 * �����A�h���X���N���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccInfoMultiMailAddressInfo extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMultiMailAddressInfo.class);

    /**
     * (���[���A�h���X�Q)<BR>
     * ���[���A�h���X�Q<BR>
     */
    public String mailAddress2;

    /**
     * (���[���A�h���X�R)<BR>
     * ���[���A�h���X�R<BR>
     */
    public String mailAddress3;

    /**
     * (���ʒm���M�t���O)<BR>
     * ���ʒm���M�t���O<BR>
     * 0�F�����M<BR>
     * 1�F��{���[���A�h���X<BR>
     * 2�F���[���A�h���X�Q<BR>
     * 3�F���[���A�h���X�R<BR>
     * 4�F�S�Ẵ��[���A�h���X<BR>
     */
    public String execMailFlag;

    /**
     * (�����ʒm���M�t���O)<BR>
     * �����ʒm���M�t���O<BR>
     * 0�F�����M<BR>
     * 1�F��{���[���A�h���X<BR>
     * 2�F���[���A�h���X�Q<BR>
     * 3�F���[���A�h���X�R<BR>
     * 4�F�S�Ẵ��[���A�h���X<BR>
     */
    public String unExecMailFlag;

    /**
     * (�d�v�A�����[�����M�t���O)<BR>
     * �d�v���[�����M�t���O<BR>
     * 1�F��{���[���A�h���X<BR>
     * 2�F���[���A�h���X�Q<BR>
     * 3�F���[���A�h���X�R<BR>
     * 4�F�S�Ẵ��[���A�h���X<BR>
     */
    public String importantMailFlag;

    /**
     * (�ē����[���Q���M�t���O)<BR>
     * �ē����[���Q���M�t���O<BR>
     * 0�F�����M<BR>
     * 1�F��{���[���A�h���X<BR>
     * 2�F���[���A�h���X�Q<BR>
     * 3�F���[���A�h���X�R<BR>
     * 4�F�S�Ẵ��[���A�h���X<BR>
     */
    public String guidanceMailFlag2;

    /**
     * (���[���A�h���X�Q�폜�t���O)<BR>
     * ���[���A�h���X�Q�폜�t���O<BR>
     * true�F�@@�폜<BR>
     * false�F�@@�폜�łȂ�<BR>
     */
    public boolean mailAddressDelFlag2;

    /**
     * (���[���A�h���X�R�폜�t���O)<BR>
     * ���[���A�h���X�R�폜�t���O<BR>
     * true�F�@@�폜<BR>
     * false�F�@@�폜�łȂ�<BR>
     */
    public boolean mailAddressDelFlag3;

    /**
     * (�����A�h���X���)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo()
    {

    }
    /**
     * (validate�����A�h���X���M�t���O)<BR>
     * ���[���A�h���X�A���M�t���O�Ԃ̐��������`�F�b�N����B<BR>
     * <BR>
     * <BR>
     * �P�j ��{���[���A�h���X�L���`�F�b�N<BR>
     * �@@�@@�@@�ȉ��A�����ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�E �i�����j��{���[���A�h���X == null<BR>
     * �@@�@@�@@�@@�E �i�����j��{���[���A�h���X == ""<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02892<BR>
     * <BR>
     * <BR>
     * �Q�j this.���ʒm���M�t���O�̐ݒ�l�������`�F�b�N<BR>
     * <BR>
     * �@@�Q-�P�j this.���ʒm���M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[�ݒ�l] "0", "1", "2", "3", "4"<BR>
     * �@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02893<BR>
     * <BR>
     * �@@�Q-�Q�j this.���ʒm���M�t���O == "2"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == null<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == ""<BR>
     * �@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02894<BR>
     * <BR>
     * �@@�Q-�R�j this.���ʒm���M�t���O == "3"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == null<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == ""<BR>
     * �@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02895<BR>
     * <BR>
     * <BR>
     * �R�j this.�����ʒm���M�t���O�̐ݒ�l�������`�F�b�N<BR>
     * <BR>
     * �@@�R-�P�j this.�����ʒm���M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[�ݒ�l] "0", "1", "2", "3", "4"<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02896<BR>
     * <BR>
     * �@@�R-�Q�j this.�����ʒm���M�t���O == "2"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == null<BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == ""<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02894<BR>
     * <BR>
     * �@@�R-�R�j this.�����ʒm���M�t���O == "3"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == null<BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == ""<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02895<BR>
     * <BR>
     * <BR>
     * �S�j this.�d�v�A�����[�����M�t���O�̐ݒ�l�������`�F�b�N<BR>
     * <BR>
     * �@@�S-�P�j this.�d�v�A�����[�����M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[�ݒ�l] "1", "2", "3", "4"<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02897<BR>
     * <BR>
     * �@@�S-�Q�j this.�d�v�A�����[�����M�t���O == "2"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == null<BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == ""<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02894<BR>
     * <BR>
     * �@@�S-�R�j this.�d�v�A�����[�����M�t���O == "3"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == null<BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == ""<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02895<BR>
     * <BR>
     * <BR>
     * �T�j this.�ē����[���Q���M�t���O�̐ݒ�l�������`�F�b�N<BR>
     * <BR>
     * �@@�T-�P�j this.�ē����[���Q���M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[�ݒ�l] "0", "1", "2", "3", "4"<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02898<BR>
     * <BR>
     * �@@�T-�Q�j this.�ē����[���Q���M�t���O == "2"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == null<BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�Q == ""<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02894<BR>
     * <BR>
     * �@@�T-�R�j this.�ē����[���Q���M�t���O == "3"�̎��A<BR>
     * �@@�@@�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == null<BR>
     * �@@�@@�@@�@@�@@�@@�E this.���[���A�h���X�R == ""<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02895<BR>
     * @@param l_strBasicMailAddress - (��{���[���A�h���X)<BR>
     * ��{���[���A�h���X�B<BR>
     * @@throws WEB3BaseException
     */
    public void validateMultiSendMailAddressInfoFlag(
        String l_strBasicMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMultiSendMailAddressInfoFlag(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j ��{���[���A�h���X�L���`�F�b�N
        //�ȉ��A�����ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E �i�����j��{���[���A�h���X == null
        //�E �i�����j��{���[���A�h���X == ""
        if (WEB3StringTypeUtility.isEmpty(l_strBasicMailAddress))
        {
            log.debug("��{���[���A�h���X�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02892,
                this.getClass().getName() + STR_METHOD_NAME,
                "��{���[���A�h���X�����w��ł��B");
        }

        //�Q�j this.���ʒm���M�t���O�̐ݒ�l�������`�F�b�N
        //�Q-�P�j this.���ʒm���M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B
        //[�ݒ�l] "0", "1", "2", "3", "4"
        if (!(WEB3OrderExeFlagDef.NOT_SEND_MAIL.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.BASE_MAIL_ADDRESS.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.MAIL_ADDRESS_2.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.MAIL_ADDRESS_3.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.ALL_MAIL_ADDRESS.equals(this.execMailFlag)))
        {
            log.debug("���ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02893,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
        }
        //�Q-�Q�j this.���ʒm���M�t���O == "2"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�Q == null
        //�E this.���[���A�h���X�Q == ""
        if (WEB3OrderExeFlagDef.MAIL_ADDRESS_2.equals(this.execMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("���[���A�h���X�Q�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�Q�����w��ł��B");
            }
        }
        //�Q-�R�j this.���ʒm���M�t���O == "3"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�R == null
        //�E this.���[���A�h���X�R == ""
        if (WEB3OrderExeFlagDef.MAIL_ADDRESS_3.equals(this.execMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("���[���A�h���X�R�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�R�����w��ł��B");
            }
        }

        //�R�j this.�����ʒm���M�t���O�̐ݒ�l�������`�F�b�N
        //�R-�P�j this.�����ʒm���M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B
        //[�ݒ�l] "0", "1", "2", "3", "4"
        if (!(WEB3OrderUnexeFlagDef.NOT_SEND_MAIL.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.BASE_MAIL_ADDRESS.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.MAIL_ADDRESS_2.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.MAIL_ADDRESS_3.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.ALL_MAIL_ADDRESS.equals(this.unExecMailFlag)))
        {
            log.debug("�����ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02896,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ʒm���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
        }
        //�R-�Q�j this.�����ʒm���M�t���O == "2"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�Q == null
        //�E this.���[���A�h���X�Q == ""
        if (WEB3OrderUnexeFlagDef.MAIL_ADDRESS_2.equals(this.unExecMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("���[���A�h���X�Q�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�Q�����w��ł��B");
            }
        }
        //�R-�R�j this.�����ʒm���M�t���O == "3"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�R == null
        //�E this.���[���A�h���X�R == ""
        if (WEB3OrderUnexeFlagDef.MAIL_ADDRESS_3.equals(this.unExecMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("���[���A�h���X�R�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�R�����w��ł��B");
            }
        }

        //�S�j this.�d�v�A�����[�����M�t���O�̐ݒ�l�������`�F�b�N
        //�S-�P�j this.�d�v�A�����[�����M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B
        //[�ݒ�l] "1", "2", "3", "4"
        if (!(WEB3ImportantConnectionMailFlagDef.BASE_MAIL_ADDRESS.equals(this.importantMailFlag)
            || WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_2.equals(this.importantMailFlag)
            || WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_3.equals(this.importantMailFlag)
            || WEB3ImportantConnectionMailFlagDef.ALL_MAIL_ADDRESS.equals(this.importantMailFlag)))
        {
            log.debug("�d�v�A�����[�����M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02897,
                this.getClass().getName() + STR_METHOD_NAME,
                "�d�v�A�����[�����M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
        }
        //�S-�Q�j this.�d�v�A�����[�����M�t���O == "2"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�Q == null
        //�E this.���[���A�h���X�Q == ""
        if (WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_2.equals(this.importantMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("���[���A�h���X�Q�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�Q�����w��ł��B");
            }
        }
        //�S-�R�j this.�d�v�A�����[�����M�t���O  == "3"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�R == null
        //�E this.���[���A�h���X�R == ""
        if (WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_3.equals(this.importantMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("���[���A�h���X�R�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�R�����w��ł��B");
            }
        }

        //�T�j this.�ē����[���Q���M�t���O�̐ݒ�l�������`�F�b�N
        //�T-�P�j this.�ē����[���Q���M�t���O�̐ݒ�l���ȉ��ȊO�̏ꍇ�A��O���X���[����B
        //[�ݒ�l] "0", "1", "2", "3", "4"
        if (!(WEB3InformationMail2FlagDef.NOT_SEND_MAIL.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.BASE_MAIL_ADDRESS.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.MAIL_ADDRESS_2.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.MAIL_ADDRESS_3.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.ALL_MAIL_ADDRESS.equals(this.guidanceMailFlag2)))
        {
            log.debug("�ē����[���Q���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02898,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ē����[���Q���M�t���O�����݂��Ȃ��R�[�h�l�ł��B");
        }
        //�T-�Q�j this.�ē����[���Q���M�t���O == "2"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�Q == null
        //�E this.���[���A�h���X�Q == ""
        if (WEB3InformationMail2FlagDef.MAIL_ADDRESS_2.equals(this.guidanceMailFlag2))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("���[���A�h���X�Q�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�Q�����w��ł��B");
            }
        }
        //�T -�R�j this.�ē����[���Q���M�t���O  == "3"�̎��A�ȉ������ꂩ�ɊY���̏ꍇ�A��O���X���[����B
        //�E this.���[���A�h���X�R == null
        //�E this.���[���A�h���X�R == ""
        if (WEB3InformationMail2FlagDef.MAIL_ADDRESS_3.equals(this.guidanceMailFlag2))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("���[���A�h���X�R�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�R�����w��ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�������[���A�h���X)<BR>
     * ���[���A�h���X�Q�A���[���A�h���X�R�ɓ��͒l������ꍇ�A<BR>
     * �ݒ�l�����[���A�h���X�Ƃ��ēK�؂Ȓl�����`�F�b�N����B<BR>
     * <BR>
     * <BR>
     * �P�j ���[���A�h���X�Q�ɓ��͒l������ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     * �@@�P-�P�j this.���[���A�h���X�Q�폜�t���O == true �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02899<BR>
     * <BR>
     * �@@�P-�Q�j this.���[���A�h���X�Q�폜�t���O == false �̏ꍇ�A<BR>
     * �@@�@@�P-�Q-�P�j ���[���A�h���X�Ƃ��ēK�؂Ȓl���𔻒肷��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[WEB3StringTypeUtility.isMailAddress() �Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@l_str�F this.���[���A�h���X�Q<BR>
     * <BR>
     * �@@�@@�P-�Q-�Q�j WEB3StringTypeUtility.isMailAddress()�̖߂�l�� false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_00777<BR>
     * <BR>
     * �Q�j ���[���A�h���X�R�ɓ��͒l������ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     * �@@�Q-�P�j this.���[���A�h���X�R�폜�t���O == true �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02900<BR>
     * <BR>
     * �@@�Q-�Q�j this.���[���A�h���X�R�폜�t���O == false �̏ꍇ�A<BR>
     * �@@�@@�Q-�Q-�P�j ���[���A�h���X�Ƃ��ēK�؂Ȓl���𔻒肷��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[WEB3StringTypeUtility.isMailAddress() �Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@l_str�F this.���[���A�h���X�R<BR>
     * <BR>
     * �@@�@@�Q-�Q-�Q�j WEB3StringTypeUtility.isMailAddress()�̖߂�l�� false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_00777<BR>
     * @@throws WEB3BaseException
     */
    public void validateMultiMailAddressInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMultiMailAddressInfo()";
        log.entering(STR_METHOD_NAME);

        //�P�j ���[���A�h���X�Q�ɓ��͒l������ꍇ�A�ȉ��������s���B
        if (!WEB3StringTypeUtility.isEmpty(this.mailAddress2))
        {
            //�P-�P�j this.���[���A�h���X�Q�폜�t���O == true �̏ꍇ�A
            //��O���X���[����B
            if (this.mailAddressDelFlag2)
            {
                log.debug("���[���A�h���X�Q�폜�̏ꍇ�́A���[���A�h���X�Q���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02899,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�Q�폜�̏ꍇ�́A���[���A�h���X�Q���w��s�ł��B");
            }

            //�P-�Q�j this.���[���A�h���X�Q�폜�t���O == false �̏ꍇ�A
            if (!this.mailAddressDelFlag2)
            {
                //�P-�Q-�P�j ���[���A�h���X�Ƃ��ēK�؂Ȓl���𔻒肷��B
                //[WEB3StringTypeUtility.isMailAddress() �Ɏw�肷�����]
                //l_str�F this.���[���A�h���X�Q
                boolean l_blnIsMailAddress = WEB3StringTypeUtility.isMailAddress(this.mailAddress2);

                //�P-�Q-�Q�j WEB3StringTypeUtility.isMailAddress()�̖߂�l�� false �̏ꍇ�A
                //��O���X���[����B
                if (!l_blnIsMailAddress)
                {
                    log.debug("���[���A�h���X�`�F�b�N�G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���[���A�h���X�`�F�b�N�G���[�B");
                }
            }
        }

        //�Q�j ���[���A�h���X�R�ɓ��͒l������ꍇ�A�ȉ��������s���B
        if (!WEB3StringTypeUtility.isEmpty(this.mailAddress3))
        {
            //�Q-�P�j this.���[���A�h���X�R�폜�t���O == true �̏ꍇ�A
            //��O���X���[����B
            if (this.mailAddressDelFlag3)
            {
                log.debug("���[���A�h���X�R�폜�̏ꍇ�́A���[���A�h���X�R���w��s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02900,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���[���A�h���X�R�폜�̏ꍇ�́A���[���A�h���X�R���w��s�ł��B");
            }

            //�Q-�Q�j this.���[���A�h���X�R�폜�t���O == false �̏ꍇ�A
            if (!this.mailAddressDelFlag3)
            {
                //�Q-�Q-�P�j ���[���A�h���X�Ƃ��ēK�؂Ȓl���𔻒肷��B
                //[WEB3StringTypeUtility.isMailAddress() �Ɏw�肷�����]
                //l_str�F this.���[���A�h���X�R
                boolean l_blnIsMailAddress = WEB3StringTypeUtility.isMailAddress(this.mailAddress3);

                //�Q-�Q-�Q�j WEB3StringTypeUtility.isMailAddress()�̖߂�l�� false �̏ꍇ�A
                //��O���X���[����B
                if (!l_blnIsMailAddress)
                {
                    log.debug("���[���A�h���X�`�F�b�N�G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���[���A�h���X�`�F�b�N�G���[�B");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
