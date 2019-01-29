head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���ʃ��N�G�X�g(WEB3AdminFXAccOpenStatusUpdCommonRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                    2006/02/09 �]�V�q(���u) �d�l�ύX�E���f��468
                    2006/02/22 ���(SRA) �d�l�ύX�E���f��500
 Revesion History : 2008/10/21 ���u��(���u) �d�l�ύX�E���f��1077
 Revesion History : 2009/08/25 �И���(���u) �d�l�ύX�E���f��1192
 */

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���ʃ��N�G�X�g) <BR>
 * �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���ʃ��N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdCommonRequest extends WEB3GenRequest
{
    /**
     * (FX��������) <BR>
     * FX��������
     */
    public WEB3FXSearchConditionUnit fxSearchConditionUnit;

    /**
     * (�X�V��X�e�[�^�X�敪) <BR>
     * �ȉ��̂����ꂩ�B <BR>
     * <BR>
     * 1�F�����J�݊��� <BR>
     * 9�F�폜
     */
    public String updatedStatusDiv;

    /**
     * (FX�������ꗗ) <BR>
     * FX�������̈ꗗ <BR>
     * <BR>
     * �X�V��X�e�[�^�X�敪��1�i�����J�݊����j�̏ꍇ�A�ݒ�
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (�X�V�������敪)<BR>
     * �ȉ��̂����ꂩ�B<BR>
     * <BR>
     * null�F���ݒ�<BR>
     * 0�F�����M<BR>
     * 1�F���M��<BR>
     * 2�F��̍�<BR>
     */
    public String updatedAgreementDiv;
    
    /**
     * @@roseuid 41E78F6402BF
     */
    public WEB3AdminFXAccOpenStatusUpdCommonRequest()
    {
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenStatusUpdCommonRequest.class);
        
    /**
     * (validate) <BR>
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j FX���������̃`�F�b�N <BR>
     * �P�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01748 <BR>
     * <BR>
     * �P�|�Q�j FX��������.validate()���\�b�h���R�[������B <BR>
     * <BR>
     * �Q�j �X�V��X�e�[�^�X�敪�̃`�F�b�N <BR>
     * �Q�|�P�j �����͂̏ꍇ�A�X�V�������敪�����ݒ�i=null�j�Ȃ�΁A<BR>
     * ��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01749 <BR>
     * <BR>
     * �Q�|�Q�j �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * �E"�����J�݊���" <BR>
     * �E"�폜" <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01750 <BR>
     * <BR>
     * �R�j�@@�X�V�������敪�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@���ݒ�i=null�j�łȂ��ꍇ�A�ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�E"�����M"<BR>
     * �@@�@@�E"���M��"<BR>
     * �@@�@@�E"��̍�"<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02348<BR>
     * <BR>
     * �S�j FX�������ꗗ�̃`�F�b�N <BR>
     * �@@���X�V��X�e�[�^�X�敪�����ݒ�i=null�j�̏ꍇ�̓`�F�b�N�����Ȃ��B<BR>
     * �S�|�P�j �����͂̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01751 <BR>
     * <BR>
     * �S�|�Q�j �v�f����0�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01752 <BR>
     * <BR>
     * �S�|�R�j �v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �S�|�R�|�P�j FX�������.�R�[�X�敪�������͂̏ꍇ�A ��O���X���[����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01753 <BR>
     * <BR>
     * �S�|�R�|�Q�j FX�������.�R�[�X�敪���ȉ��̒l�ȊO�̏ꍇ�A <BR>
     * ��O���X���[����B <BR>
     * �E"DEFAULT" <BR>
     * �E"1���ʉ݃R�[�X" <BR>
     * �E"10���ʉ݃R�[�X" <BR>
     * �E"CFD�R�[�X"<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01754 <BR>
     * <BR>
     * �S�|�R�|�R�j this.�X�V��X�e�[�^�X == "�����J�݊���"�̏ꍇ�A <BR>
     * ���L�̏����ƈ�v����ꍇ�A��O���X���[����B <BR>
     * �EFX�������.�����ԍ� == null  <BR>
     * �EFX�������.�����ԍ�.length() > 10 <BR>
     * �EFX�������.�����ԍ� != ����  <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01755 <BR>
     * <BR>
     * �S�|�R�|�S�j this.�X�V��X�e�[�^�X == "�폜"�̏ꍇ�A <BR>
     * ���L�̏����ƈ�v����ꍇ�A��O���X���[����B<BR>
     * FX�������.�����ԍ� != null<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01756 <BR>
     * this.�X�V�������敪 != nulll<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02349<BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@FX���������̃`�F�b�N 
        // �P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (fxSearchConditionUnit == null)
        {
            log.debug("FX�������������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01748,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX�������������w��ł��B");
        }
        
        //�P�|�Q�j�@@FX��������.validate()���\�b�h���R�[������B 
        fxSearchConditionUnit.validate();
        
        //�Q�j�@@�X�V��X�e�[�^�X�敪�̃`�F�b�N 
        if (WEB3StringTypeUtility.isEmpty(this.updatedStatusDiv))
        {
            //  �Q�|�P�j�@@�����͂̏ꍇ�A�X�V�������敪�����ݒ�i=null�j�Ȃ�΁A
            //�@@�@@�@@��O���X���[����B 
            if(this.updatedAgreementDiv == null){
                log.debug("�X�V��X�e�[�^�X�敪�����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01749,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�V��X�e�[�^�X�敪�����w��ł��B");
            }
        }
        else
        {
            //�Q�|�Q�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
            //�@@�@@ �E"�����J�݊���" 
            //�@@�@@ �E"�폜" 
            if (!(WEB3AioAccountOpenCompleteDef.DELETE.equals(
                    this.updatedStatusDiv)
                || WEB3AioAccountOpenCompleteDef.OPEN_COMPLETE.equals(
                    this.updatedStatusDiv)))
            {
                log.debug("�X�V��X�e�[�^�X�敪�����݂��Ȃ��R�[�h�l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01750,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�V��X�e�[�^�X�敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        
        //�R�j�@@�X�V�������敪�̃`�F�b�N
        //�@@�R�|�P�j�@@���ݒ�i=null�j�łȂ��ꍇ�A�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�@@�@@�E"�����M"
        //�@@�@@�E"���M��"
        //�@@�@@�E"��̍�"
        if(this.updatedAgreementDiv != null
            && !(WEB3AioAgreementDivDef.NOT_SEND.equals(this.updatedAgreementDiv)
                || WEB3AioAgreementDivDef.SENDED.equals(this.updatedAgreementDiv)
                || WEB3AioAgreementDivDef.RECIEVED.equals(this.updatedAgreementDiv)))
        {
            log.debug("������敪�̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02348,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "������敪�̒l���s���ł��B");
        }
        
        //�S�j�@@FX�������ꗗ�̃`�F�b�N 
        //�@@���X�V��X�e�[�^�X�敪�����ݒ�i=null�j�̏ꍇ�̓`�F�b�N�����Ȃ��B
        if(this.updatedStatusDiv != null)
        {
            //  �S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
            if (this.fxAccInformationList == null)
            {
                log.debug("FX�������ꗗ�����w��ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01751,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "FX�������ꗗ�����w��ł��B");
            }
            
            //�S�|�Q�j�@@�v�f����0�̏ꍇ�A��O���X���[����B 
            if (this.fxAccInformationList.length == 0)
            {
                log.debug("FX�������ꗗ�̗v�f�����O�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01752,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "FX�������ꗗ�̗v�f�����O�ł��B");
            }
            
            //�S�|�R�j�@@�v�f�����ȉ��̏������J��Ԃ��B 
            for (int i = 0; i < this.fxAccInformationList.length; i++)
            {
                //�S�|�R�|�P�j�@@FX�������.�R�[�X�敪�������͂̏ꍇ�A��O���X���[����B
                if (WEB3StringTypeUtility.isEmpty(
                        this.fxAccInformationList[i].fxCourseDiv))
                {
                    log.debug("FX�������̃R�[�X�敪�����w��ł��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01753,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "FX�������̃R�[�X�敪�����w��ł��B");
                }
                
                //�S�|�R�|�Q�j�@@FX�������.�R�[�X�敪���ȉ��̒l�ȊO�̏ꍇ�A 
                // ��O���X���[����B 
                //�@@�E"DEFAULT" 
                //�@@�E"1���ʉ݃R�[�X" 
                //�@@�E"10���ʉ݃R�[�X" 
                //�@@�E"CFD�R�[�X"
                if (!(WEB3GftTransStatusCourseDivDef.DEFAULT.equals(
                        this.fxAccInformationList[i].fxCourseDiv)
                    || WEB3GftTransStatusCourseDivDef.ONE_COSE.equals(
                        this.fxAccInformationList[i].fxCourseDiv)
                    || WEB3GftTransStatusCourseDivDef.TEN_COSE.equals(
                        this.fxAccInformationList[i].fxCourseDiv)
                    || WEB3GftTransStatusCourseDivDef.CFD_COURSE.equals(
                        this.fxAccInformationList[i].fxCourseDiv)))
                {
                    log.debug("FX�������̃R�[�X�敪�����݂��Ȃ��R�[�h�l�ł��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01754,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "FX�������̃R�[�X�敪�����݂��Ȃ��R�[�h�l�ł��B");
                }
                
                //�S�|�R�|�R�j�@@this.�X�V��X�e�[�^�X == "�����J�݊���"�̏ꍇ�A 
                // �EFX�������.�����ԍ� == null 
                // �EFX�������.�����ԍ�.length() > 10
                // �EFX�������.�����ԍ� != ����  
                if (WEB3AioAccountOpenCompleteDef.OPEN_COMPLETE.equals(
                        this.updatedStatusDiv))
                {
                    if (WEB3StringTypeUtility.isEmpty(
                        this.fxAccInformationList[i].fxAccountCode) ||
                    this.fxAccInformationList[i].fxAccountCode.length() > 10 ||
                    !WEB3StringTypeUtility.isInteger(
                        this.fxAccInformationList[i].fxAccountCode))
                    {
                        log.debug("�X�V��X�e�[�^�X���h�����J�݊����h�̏ꍇ�́A" +
                            "FX�������̌����ԍ����s���ł��B");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01755,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�X�V��X�e�[�^�X= [" + this.updatedStatusDiv +  "]," +
                                " FX�������.�����ԍ� = [" + this.fxAccInformationList[i].fxAccountCode +  "]" );
                    }
                }
                
                //�S�|�R�|�S�j�@@this.�X�V��X�e�[�^�X == "�폜"�̏ꍇ�A 
                // �@@���L�̏����ƈ�v����ꍇ�A��O���X���[����B 
                //�@@�EFX�������.�����ԍ� != null
                //�@@�Ethis.�X�V�������敪 != null
                if (WEB3AioAccountOpenCompleteDef.DELETE.equals(this.updatedStatusDiv))
                {
                    if (!WEB3StringTypeUtility.isEmpty(
                            this.fxAccInformationList[i].fxAccountCode))
                    {
                        log.debug("�X�V��X�e�[�^�X���h�폜�h�̏ꍇ�́A" +
                            "FX�������̌����ԍ����w��s�B");
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01756,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�X�V��X�e�[�^�X���h�폜�h�̏ꍇ�́A" +
                            "FX�������̌����ԍ����w��s�B");
                    }
                    
                    if(this.updatedAgreementDiv != null)
                    {
                        log.debug("�X�V��X�e�[�^�X���h�폜�h�̏ꍇ�́A" +
                            "FX�������̌����ԍ��ƍX�V�������敪�������Ɏw��ł��Ȃ��B");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_02349,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�X�V��X�e�[�^�X���h�폜�h�̏ꍇ�́A" +
                            "FX�������̌����ԍ��ƍX�V�������敪�������Ɏw��ł��Ȃ��B");
                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * �icreateResponse�̎����j<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F64036B
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}@
