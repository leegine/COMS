head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֊m�F���N�G�X�g(WEB3FXTransToFXConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��533
                 : 2008/05/21 �O���~��Y (SCS) �����`�F�b�N�C��
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
 * (FX�ւ̐U�֊m�F���N�G�X�g) <BR>
 * FX�ւ̐U�֊m�F���N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;
    
    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     */
    public String fxSystemCode;

    /**
     * (�U�֋��z) <BR>
     * ��ʂ�����͂��ꂽ�U�֋��z <BR>
     */
    public String transferAmount;

    /**
     * @@roseuid 41E77F4A036B
     */
    public WEB3FXTransToFXConfirmRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXConfirmRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j �U�֋��z�`�F�b�N <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * �P�|�P�j this.�U�֋��z��null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00759 <BR>
     * <BR>
     * �P�|�Q�j this.�U�֋��z������ <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00811 <BR>
     * <BR>
     * �P�|�R�j this.�U�֋��z��0 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00809 <BR>
     * <BR>
     * �P�|�S�j this.�U�֋��z��9�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00810 <BR>
     * <BR>
     * 
     * @@roseuid 41BECA5802AF
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j �U�֋��z�`�F�b�N 
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B 
        // �P�|�P�j this.�U�֋��z��null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00759 
        if (WEB3StringTypeUtility.isEmpty(this.transferAmount))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00759,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U�֋��z = null"); 
        }

        // �P�|�Q�j this.�U�֋��z������  
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
         
        // �P�|�R�j this.�U�֋��z��0  
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
 
        // �P�|�S�j this.�U�֋��z��9�� 
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
        return new WEB3FXTransToFXConfirmResponse(this);
    }
}@
