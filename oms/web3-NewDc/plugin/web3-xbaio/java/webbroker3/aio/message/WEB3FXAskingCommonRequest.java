head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAskingCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�˗����ʃ��N�G�X�g(WEB3FXAskingCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�˗����ʃ��N�G�X�g) <BR>
 * FX�˗����ʃ��N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAskingCommonRequest extends WEB3GenRequest
{
    /**
     * (WOLF�Z�b�V�����L�[) <BR>
     * WOLF�Z�b�V�����L�[ <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String wolfSession;

    /**
     * (�A�v���P�[�V����ID) <BR>
     * �A�v���P�[�V����ID <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String wolfAid;

    /**
     * (�Đ����T�[�r�XID) <BR>
     * �Đ����T�[�r�XID <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String regetServiceId;

    /**
     * (SSID) <BR>
     * SSID <BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj <BR>
     */
    public String wolfSsid;

    /**
     * @@roseuid 41E772120196
     */
    public WEB3FXAskingCommonRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAskingCommonRequest.class);


    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j WOLF�Z�b�V�����L�[�`�F�b�N <BR>
     * this.WOLF�Z�b�V�����L�[��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �Q�j �A�v���P�[�V����ID�`�F�b�N <BR>
     * this.�A�v���P�[�V����ID��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �R�j �Đ����T�[�r�XID�`�F�b�N <BR>
     * this.�Đ����T�[�r�XID��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �S�j SSID�`�F�b�N <BR>
     * this.SSID��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * 
     * @@roseuid 41BE4AD70332
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j WOLF�Z�b�V�����L�[�`�F�b�N 
        // this.WOLF�Z�b�V�����L�[��null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (WEB3StringTypeUtility.isEmpty(this.wolfSession))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.WOLF�Z�b�V�����L�[ = null"); 
        }
 
        // �Q�j �A�v���P�[�V����ID�`�F�b�N 
        // this.�A�v���P�[�V����ID��null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (WEB3StringTypeUtility.isEmpty(this.wolfAid))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�A�v���P�[�V����ID = null"); 
        }

        // �R�j �Đ����T�[�r�XID�`�F�b�N 
        // this.�Đ����T�[�r�XID��null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309 
        if (WEB3StringTypeUtility.isEmpty(this.regetServiceId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�Đ����T�[�r�XID = null"); 
        }
 
        // �S�j SSID�`�F�b�N 
        // this.SSID��null�̏ꍇ�A��O��throw����B 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309
        if (WEB3StringTypeUtility.isEmpty(this.wolfSsid))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.SSID = null"); 
        }
        
        log.exiting(l_strMethodName);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E772120203
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAskingCommonResponse(this);
    }
}@
