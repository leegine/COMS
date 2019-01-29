head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g(WEB3AdminFXAccOpenApplyListRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                    2006/02/09 �]�V�q(���u) �d�l�ύX�E���f��458
                    2006/02/09 �A����(���u) �d�l�ύX�E���f��475
                    2006/02/22 ���(SRA) �d�l�ύX�E���f��500
 Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.866
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.aio.define.WEB3AioFxAccountOpenDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g) <BR>
 * �Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyListRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_list";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (���X�R�[�h) <BR>
     * ��ʂɂđI�����ꂽ���X�R�[�h <BR>
     * �������͂̏ꍇ�́APR�w�ŕێ����Ă��� <BR>
     * �Ǘ��Ҏ戵�\���X�̈ꗗ���Z�b�g�B
     */
    public String[] branchCodeList;

    /**
     * (�X�e�[�^�X�敪) <BR>
     * ��ʂɂđI�����ꂽ�X�e�[�^�X <BR>
     * <BR>
     * 0�F�����J�ݒ� <BR>
     * 1�F�����J�݊��� <BR>
     * 2�F�����J�݃G���[ <BR>
     * 3�F�_�E�����[�h�� <BR>
     * 9�F�폜<BR>
     * <BR>
     * ���S�X�e�[�^�X�̏ꍇ�́Anull���Z�b�g�B
     */
    public String statusDiv;

    /**
     * (MRF�����󋵋敪) <BR>
     * ��ʂɂđI�����ꂽMRF������ <BR>
     * <BR>
     * 1�F�J�� <BR>
     * 2�F���J�� <BR>
     * <BR>
     * ���S�Ă̏ꍇ�́Anull���Z�b�g�B
     */
    public String mrfAccountStatusDiv;

    /**
     * (�\�����i���j) <BR>
     * ��ʂɂē��͂��ꂽ�\�����i���j <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String applyHourFrom;

    /**
     * (�\�����i���j) <BR>
     * ��ʂɂē��͂��ꂽ�\�����i���j <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String applyHourTo;

    /**
     * (�v���y�[�W�ԍ�) <BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��) <BR>
     * �y�[�W���\���s��
     */
    public String pageSize;

    /**
     * (������敪)<BR>
     * ��ʂɂđI�����ꂽ������敪<BR>
     * <BR>
     * 0�F�����M<BR>
     * 1�F���M��<BR>
     * 2�F��̍�<BR>
     * <BR>
     * ���S�Ă̏ꍇ�́Anull���Z�b�g�B<BR>
     */
    public String agreementDiv;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78FB70177
     */
    public WEB3AdminFXAccOpenApplyListRequest()
    {
    }
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenApplyListRequest.class);
        
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
     * �P�|�Q�j �v�f����0�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01757 <BR>
     * <BR>
     * �P�|�R�j �v�f�����ȉ��̃`�F�b�N���s���B <BR>
     * �P�|�R�|�P�j�ȉ��̏����ɊY������ꍇ�A��O���X���[����B <BR>
     * �E���X�R�[�h != ���l <BR>
     * �E���X�R�[�h�̌��� != 3�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01729 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00834 <BR>
     * <BR>
     * �Q�j �X�e�[�^�X�敪�̃`�F�b�N <BR>
     * �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �Q�|�P�j �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �E"�����J�ݒ�" <BR>
     * �E"�����J�݊���" <BR>
     * �E"�����J�݃G���[" <BR>
     * �E"�_�E�����[�h��" <BR>
     * �E"�폜" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01758 <BR>
     * <BR>
     * �R�j MRF�����󋵋敪�̃`�F�b�N <BR>
     * �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �R�|�P�j �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �E"�J��" <BR>
     * �E"���J��" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01759 <BR>
     * <BR>
     * �S�j �\����(��)�̃`�F�b�N <BR>
     * �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �S�|�P�j �\�����i���j�̓��t����(YYYYMMDD)�� <BR>
     * Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01760 <BR>
     * <BR>
     * �T�j �\����(��)�̃`�F�b�N <BR>
     * �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �T�|�P�j �\�����i���j�̓��t����(YYYYMMDD)�� <BR>
     * Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01761 <BR>
     * <BR>
     * �U�j �\����(���`��)�������̃`�F�b�N <BR>
     * �\����(��)�A�\����(��)�����ɖ����͂łȂ��ꍇ�A <BR>
     * �ȉ��̃`�F�b�N���s���B <BR>
     * �U�|�P�j �\����(��) > �\����(��)�̏ꍇ�A��O���X���[����B <BR>
     * ����L��r�́A�������r�ł悢�B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01762 <BR>
     * <BR>
     * �V�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �V�|�P�jthis.�v���y�[�W�ԍ���null�ł������ꍇ�A <BR>
     * �u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00089 <BR>
     * <BR>
     * �V�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A <BR>
     * �u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00090 <BR>
     * <BR>
     * �V�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A <BR>
     * �u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00616 <BR>
     * <BR>
     * �W�j�y�[�W���\���s���`�F�b�N <BR>
     * �W�|�P�jthis.�y�[�W���\���s����null�ł������ꍇ�A <BR>
     * �u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00091 <BR>
     * <BR>
     * �W�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
     * �u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00092 <BR>
     * <BR>
     * �W�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A <BR>
     * �u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00617 <BR>
     * <BR>
     * �X�j������敪�̃`�F�b�N<BR>
     * �@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�X�|�P�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E"�����M"<BR>
     * �@@�@@�E"���M��"<BR>
     *     �E"��̍�"<BR> 
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02348<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N 
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCodeList == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        
        //�P�|�Q�j�@@�v�f����0�̏ꍇ�A��O���X���[����B 
        if (this.branchCodeList.length == 0)
        {
            log.debug("���X�R�[�h�̗v�f�����O�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̗v�f�����O�ł��B");
        }
        
        //�P�|�R�j�@@�v�f�����ȉ��̃`�F�b�N���s���B 
        for (int i = 0; i < this.branchCodeList.length; i++)
        {
            //�P�|�R�|�P�j�ȉ��̏����ɊY������ꍇ�A��O���X���[����B 
            // �E���X�R�[�h != ���l 
            if (!WEB3StringTypeUtility.isNumber(this.branchCodeList[i]))
            {
                log.debug("���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̒l�����l�ȊO�̒l�ł��B");
            }
            
            // �E���X�R�[�h�̌��� != 3�� 
            if (this.branchCodeList[i].getBytes().length != 3)
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
        }
        
        //�Q�j�@@�X�e�[�^�X�敪�̃`�F�b�N 
        // �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (!WEB3StringTypeUtility.isEmpty(this.statusDiv))
        {
            //�Q�|�P�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
            // �E"�����J�ݒ�" 
            // �E"�����J�݊���" 
            // �E"�����J�݃G���[" 
            // �E"�_�E�����[�h��" 
            // �E"�폜" 
            if (!(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_COMPLETE.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.ACCOUNT_OPEN_ERROR.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.DOWNLOAD_COMPLETE.equals(this.statusDiv)
                || WEB3AccountOpenStatusDivDef.DELETE.equals(this.statusDiv)))
            {
                log.debug("�X�e�[�^�X�敪�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�e�[�^�X�敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //�R�j�@@MRF�����󋵋敪�̃`�F�b�N 
        // �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (!WEB3StringTypeUtility.isEmpty(this.mrfAccountStatusDiv))
        {
            //�R�|�P�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
            // �E"�J��" 
            // �E"���J��" 
            if (!(WEB3AioFxAccountOpenDivDef.OPEN.equals(this.mrfAccountStatusDiv)
                || WEB3AioFxAccountOpenDivDef.NOT_OPEN.equals(this.mrfAccountStatusDiv)))
            {
                log.debug("MRF�����󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01759,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "MRF�����󋵋敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //�S�j�@@�\����(��)�̃`�F�b�N 
        // �����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (!WEB3StringTypeUtility.isEmpty(this.applyHourFrom))
        {
            //�S�|�P�j�@@�\�����i���j�̓��t����(YYYYMMDD)�� 
            // Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B 
            if (!WEB3StringTypeUtility.isDateStr(
                (this.applyHourFrom.length() > 8) ? this.applyHourFrom.substring(0, 8) : this.applyHourFrom, "yyyyMMdd"))
            {
                log.debug("�\�����i���j�̓��͒l���s���ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01760,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����i���j�̓��͒l���s���ł��B");
            }
        }
        
        //�T�j�@@�\����(��)�̃`�F�b�N 
        //�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B 
        if (!WEB3StringTypeUtility.isEmpty(this.applyHourTo))
        {
            //�T�|�P�j�@@�\�����i���j�̓��t����(YYYYMMDD)�� 
            // Date�^�ɕϊ��ł��Ȃ������ꍇ�A��O���X���[����B 
            if (!WEB3StringTypeUtility.isDateStr(
                (this.applyHourTo.length() > 8) ? this.applyHourTo.substring(0, 8) : this.applyHourTo, "yyyyMMdd"))
            {
                log.debug("�\�����i���j�̓��͒l���s���ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01761,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����i���j�̓��͒l���s���ł��B");
            }
        }
        
        //�U�j�@@�\����(���`��)�������̃`�F�b�N 
        // �\����(��)�A�\����(��)�����ɖ����͂łȂ��ꍇ�A
        // �ȉ��̃`�F�b�N���s���B 
        if (!WEB3StringTypeUtility.isEmpty(this.applyHourFrom) && 
            !WEB3StringTypeUtility.isEmpty(this.applyHourTo))
        {
            //�U�|�P�j�@@�\����(��) > �\����(��)�̏ꍇ�A��O���X���[����B 
            // ����L��r�́A�������r�ł悢�B 
            if (this.applyHourFrom.compareTo(this.applyHourTo) > 0)
            {
                log.debug("�\�����i���j�͐\�����i���j���傫���ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01762,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����i���j�͐\�����i���j���傫���ł��B");
            }
        }

        //�V�j�v���y�[�W�ԍ��`�F�b�N 
        //�V�|�P�jthis.�v���y�[�W�ԍ���null�ł������ꍇ�A 
        // �u�v���y�[�W�ԍ���null�v�̗�O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //�V�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A 
        //�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�V�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A 
        //�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        
        //�W�j�y�[�W���\���s���`�F�b�N 
        //�W�|�P�jthis.�y�[�W���\���s����null�ł������ꍇ�A 
        // �u�y�[�W���\���s����null�v�̗�O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        
        //�W�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A 
        // �u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B 
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //�W�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A 
        //�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B 
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        
        //�X�j������敪�̃`�F�b�N
        //�@@�����͂łȂ��ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�X�|�P�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�@@�@@�E"�����M"
        //�@@�@@�E"���M��"
        //�@@�@@�E"��̍�"
        if(this.agreementDiv != null)
        {
            if(!(WEB3AioAgreementDivDef.NOT_SEND.equals(this.agreementDiv) 
                ||WEB3AioAgreementDivDef.SENDED.equals(this.agreementDiv)
                ||WEB3AioAgreementDivDef.RECIEVED.equals(this.agreementDiv)))
            {
                log.debug("������敪�̒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02348,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "������敪�̒l���s���ł��B");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }


    /**
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �Ǘ��ҁEFX�����J�ݐ\���ꗗ���X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E78FB70213
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccOpenApplyListResponse(this);
    }
}@
