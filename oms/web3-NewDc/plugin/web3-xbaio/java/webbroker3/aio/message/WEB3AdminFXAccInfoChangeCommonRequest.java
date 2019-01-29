head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.56.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�������ύX���ʃ��N�G�X�g(WEB3AdminFXAccInfoChangeCommonRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                    2006/02/09 �]�V�q(���u) �d�l�ύX�E���f��458
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioFxAccountInfoDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁEFX�������ύX���ʃ��N�G�X�g) <BR>
 * �Ǘ��ҁEFX�������ύX���ʃ��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoChangeCommonRequest extends WEB3GenRequest
{
    /**
     * (���X�R�[�h) <BR>
     * �I�����ꂽ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h) <BR>
     * �I�����ꂽ�ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�����敪) <BR>
     * 0�F�������ύX <BR>
     * 1�F�����J�ݏ󋵕ύX
     */
    public String operationDiv;

    /**
     * (�X�V�チ�[���A�h���X) <BR>
     * �X�V��̃��[���A�h���X <BR>
     * <BR>
     * �����敪��0�i�������ύX�j�̏ꍇ�A�ݒ�
     */
    public String updatedMailAddress;

    /**
     * (�X�V��FX�������ꗗ) <BR>
     * �X�V���FX�������̈ꗗ <BR>
     * <BR>
     * �����敪��0�i�������ύX�j�̏ꍇ�A�ݒ�
     */
    public WEB3FXAccInformationUnit[] updatedFxAccInformationList;

    /**
     * (�X�V������J�ݏ󋵋敪) <BR>
     * �X�V��̌����J�ݏ󋵋敪<BR>
     * 1�F�J�ݍ�<BR>
     * 2�F�U�֒�~<BR>
     * 9�F����<BR>
     * <BR>
     * �����敪��1�i�����J�ݏ󋵕ύX�j�̏ꍇ�A�ݒ�
     */
    public String updatedAccountOpenStatusDiv;

    /**
     * @@roseuid 41E78FE3005D
     */
    public WEB3AdminFXAccInfoChangeCommonRequest()
    {
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccInfoChangeCommonRequest.class);
        
    /**
     * (validate) <BR>
     * �N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j ���X�R�[�h�̃`�F�b�N <BR>
     * �P�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * �P�|�P�j �ȉ��̏����ɊY������ꍇ�A��O���X���[����B <BR>
     * �E���X�R�[�h != ���l <BR>
     * �E���X�R�[�h�̌��� != 3�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01729 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00834 <BR>
     * <BR>
     * �Q�j �ڋq�R�[�h�̃`�F�b�N <BR>
     * �Q�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00835 <BR>
     * <BR>
     * �Q�|�Q�j �ȉ��̏����ɊY������ꍇ�A��O���X���[����B <BR>
     * �E�ڋq�R�[�h != ���l <BR>
     * �E�ڋq�R�[�h�̌��� != 6�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01043 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00836 <BR>
     * <BR>
     * �R�j �����敪�̃`�F�b�N <BR>
     * �R�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01249 <BR>
     * <BR>
     * �R�|�Q�j �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �E"�������ύX" <BR>
     * �E"�����J�ݏ󋵕ύX" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01250 <BR>
     * <BR>
     * �S�j �X�V�チ�[���A�h���X�̃`�F�b�N <BR>
     * this.�����敪 == "�������ύX"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �S�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01767 <BR>
     * <BR>
     * �T�j �X�V��FX�������ꗗ�̃`�F�b�N <BR>
     * this.�����敪 == "�������ύX"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �T�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01768 <BR>
     * <BR>
     * �T�|�Q�j �v�f����0�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01769 <BR>
     * <BR>
     * �T�|�R�j �X�V��FX�������ꗗ�̗v�f�����A <BR>
     * �ȉ��̏������J��Ԃ��B <BR>
     * �T�|�R�|�P�j FX�������.validate()���R�[������B <BR>
     * <BR>
     * �U�j �X�V������J�ݏ󋵋敪�̃`�F�b�N <BR>
     * this.�����敪 == "�����J�ݏ󋵕ύX"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �U�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01770 <BR>
     * <BR>
     * �U�|�Q�j �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �E"�J�ݍ�" <BR>
     * �E"�U�֒�~"<BR>
     * �E"����" <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01771 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BD4C83025D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h�̃`�F�b�N 
        //�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("���X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        
        //�P�|�P�j�@@�ȉ��̏����ɊY������ꍇ�A��O���X���[����B 
        //�E���X�R�[�h != ���l 
        if (!WEB3StringTypeUtility.isNumber(this.branchCode))
        {
            log.debug("���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
        }
        
        //�E���X�R�[�h�̌��� != 3�� 
        if (this.branchCode.getBytes().length != 3)
        {
            log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N 
        //  �Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }
        
        //�@@�Q�|�Q�j�@@�ȉ��̏����ɊY������ꍇ�A��O���X���[����B 
        //�E�ڋq�R�[�h != ���l 
        if (!WEB3StringTypeUtility.isNumber(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }
        
        //�E�ڋq�R�[�h�̌��� != 6�� 
        if (this.accountCode.getBytes().length != 6)
        {
            log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }
        
        //�R�j�@@�����敪�̃`�F�b�N 
        //  �R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.operationDiv))
        {
            log.debug("�����敪�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�����w��ł��B");
        }
        
        //�@@�R�|�Q�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //�E"�������ύX" 
        //�E"�����J�ݏ󋵕ύX" 
        if (!(WEB3AioFxAccountInfoDivDef.ACCOUNT_INFO.equals(this.operationDiv)
            || WEB3AioFxAccountInfoDivDef.ACCOUNT_OPEN_STATUS.equals(this.operationDiv)))
        {
            log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
        //�S�j�X�V�チ�[���A�h���X�̃`�F�b�N 
        //this.�����敪 == "�������ύX"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_INFO.equals(this.operationDiv))
        {
            //  �S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
            if (WEB3StringTypeUtility.isEmpty(updatedMailAddress))
            {
                log.debug("�����敪���h�������ύX�h�̏ꍇ�́A" +
                    "�X�V�チ�[���A�h���X�����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01767,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����敪���h�������ύX�h�̏ꍇ�́A" +
                    "�X�V�チ�[���A�h���X�����w��ł��B");
            }
        }
        
        //�T�j�X�V��FX�������ꗗ�̃`�F�b�N 
        //this.�����敪 == "�������ύX"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_INFO.equals(this.operationDiv))
        {
            //  �T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
            if (this.updatedFxAccInformationList == null)
            {
                log.debug("�����敪���h�������ύX�h�̏ꍇ�́A" +
                    "�X�V��FX�������ꗗ�����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01768,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����敪���h�������ύX�h�̏ꍇ�́A" +
                    "�X�V��FX�������ꗗ�����w��ł��B");
            }
            
            //�T�|�Q�j�v�f����0�̏ꍇ�A��O���X���[����B 
            if (this.updatedFxAccInformationList.length == 0)
            {
                log.debug("�����敪���h�������ύX�h�̏ꍇ�́A" +
                    "�X�V��FX�������ꗗ�̗v�f�����O�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01769,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����敪���h�������ύX�h�̏ꍇ�́A" +
                    "�X�V��FX�������ꗗ�̗v�f�����O�ł��B");
            }
            
            //  �T�|�R�j�@@�X�V��FX�������ꗗ�̗v�f�����A 
            //     �ȉ��̏������J��Ԃ��B 
            for (int i = 0; i < this.updatedFxAccInformationList.length; i++)
            {
                //�T�|�R�|�P�j�@@FX�������.validate()���R�[������B
                WEB3FXAccInformationUnit l_fXAccInformationUnit = 
                    this.updatedFxAccInformationList[i];
                l_fXAccInformationUnit.validate();
            }
        }

        //�U�j�@@�X�V������J�ݏ󋵋敪�̃`�F�b�N 
        //  this.�����敪 == "�����J�ݏ󋵕ύX"�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (WEB3AioFxAccountInfoDivDef.ACCOUNT_OPEN_STATUS.equals(this.operationDiv))
        {
            //  �U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
            if (WEB3StringTypeUtility.isEmpty(this.updatedAccountOpenStatusDiv))
            {
                log.debug("�����敪���h�����J�ݏ󋵕ύX�h�̏ꍇ�́A" +
                    "�X�V������J�ݏ󋵋敪�����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01770,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����敪���h�����J�ݏ󋵕ύX�h�̏ꍇ�́A" +
                    "�X�V������J�ݏ󋵋敪�����w��ł��B");
            }
            
            //�@@�U�|�Q�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
            //�@@ �E"�J�ݍ�" 
            //�@@�@@�E"�U�֒�~"
            //�@@�@@�E"����"
            if (!(WEB3AioAccountDivDef.OPEN_COMPLETE.equals(
                    this.updatedAccountOpenStatusDiv)
                || WEB3AioAccountDivDef.DELETE.equals(
                    this.updatedAccountOpenStatusDiv) 
                || WEB3AioAccountDivDef.TRANSFER_STOP.equals(
                    this.updatedAccountOpenStatusDiv)))
            {
                log.debug("�����敪���h�����J�ݏ󋵕ύX�h�̏ꍇ�́A" +
                    "�X�V������J�ݏ󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01771,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����敪���h�����J�ݏ󋵕ύX�h�̏ꍇ�́A" +
                    "�X�V������J�ݏ󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78FE30109
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}@
