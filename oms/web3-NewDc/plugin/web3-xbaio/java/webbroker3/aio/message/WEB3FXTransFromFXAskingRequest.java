head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXAskingRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX����U�ֈ˗����N�G�X�g(WEB3FXTransFromFXAskingRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��533
                 : 2008/05/21 �O���~��Y (SCS) �����`�F�b�N�C��
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX����U�ֈ˗����N�G�X�g) <BR>
 * FX����U�ֈ˗����N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransFromFXAskingRequest extends WEB3FXAskingCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_asking";

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     */
    public String fxSystemCode;
    
    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX�������) <BR>
     * �U�ւ��s��FX������� <BR>
     */
    public WEB3FXAccInformationUnit fxAccInformationUnit;

    /**
     * (�U�֋��z) <BR>
     * ��ʂ�����͂��ꂽ�U�֋��z <BR>
     */
    public String transferAmount;

    /**
     * (�Ïؔԍ�) <BR>
     * ��ʂ�����͂��ꂽ�Ïؔԍ� <BR>
     */
    public String password;

    /**
     * @@roseuid 41E788110138
     */
    public WEB3FXTransFromFXAskingRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXAskingRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j �X�[�p�[�N���X��validate���\�b�h���Ăяo���B <BR>
     * <BR>
     * �Q�j FX�������`�F�b�N <BR>
     * �Q�|�P�j this.FX�������null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �Q�|�Q�j this.FX�������.validate���\�b�h���Ăяo���B <BR>
     * <BR>
     * �R�j �U�֋��z�`�F�b�N <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * �R�|�P�j this.�U�֋��z��null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00759 <BR>
     * <BR>
     * �R�|�Q�j this.�U�֋��z������ <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00811 <BR>
     * <BR>
     * �R�|�R�j this.�U�֋��z��0 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00809 <BR>
     * <BR>
     * �R�|�S�j this.�U�֋��z��9�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00810 <BR>
     * <BR>
     * 
     * @@roseuid 41BEC94A00EA
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j �X�[�p�[�N���X��validate���\�b�h���Ăяo���B 
        super.validate();
 
        // �Q�j FX�������`�F�b�N 
        // �Q�|�P�j this.FX�������null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309 
        if (this.fxAccInformationUnit == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX������� = null"); 
        }
 
        // �Q�|�Q�j this.FX�������.validate���\�b�h���Ăяo���B 
        this.fxAccInformationUnit.validate();

        // �R�j �U�֋��z�`�F�b�N 
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B 
        // �R�|�P�j this.�U�֋��z��null 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00759 
        if (WEB3StringTypeUtility.isEmpty(this.transferAmount))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00759,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֋��z��null"); 
        }
 
        // �R�|�Q�j this.�U�֋��z������  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00811 
        if (!WEB3StringTypeUtility.isNumber(this.transferAmount))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00811,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ�������" + 
                "���N�G�X�g�f�[�^.FX�Ïؔԍ� = " + this.transferAmount); 
        }
 
        // �R�|�R�j this.�U�֋��z��0  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00809 
        if (Double.parseDouble(this.transferAmount) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00809,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֋��z��0" + 
                "���N�G�X�g�f�[�^.�U�֋��z = " + this.transferAmount); 
        }

        // �R�|�S�j this.�U�֋��z��9�� 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00810 
        if (this.transferAmount.length() > 9)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00810,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֋��z��9��" + 
                "���N�G�X�g�f�[�^.�U�֋��z = " + this.transferAmount.length()); 
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransFromFXAskingResponse(this);
    }
}@
