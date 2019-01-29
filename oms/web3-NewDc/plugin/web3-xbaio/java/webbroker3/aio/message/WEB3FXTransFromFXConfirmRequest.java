head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX����U�֊m�F���N�G�X�g(WEB3FXTransFromFXConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��533
                 : 2008/05/21 �O���~��Y (SCS) �����`�F�b�N�C��
Revesion History : 2009/06/25 �đo�g (���u) ���f��No.1166,No.1185,No.1186
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
 * (FX����U�֊m�F���N�G�X�g) <BR>
 * FX����U�֊m�F���N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransFromFXConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_confirm";

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
     * (FX�������)<BR>
     * FX�������<BR>
     */
    public WEB3FXAccInformationUnit fxAccInformationUnit;

    /**
     * @@roseuid 41E769320271
     */
    public WEB3FXTransFromFXConfirmRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXConfirmRequest.class);

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
     * �Q�j�@@FX�������`�F�b�N<BR>
     * �@@�@@�Q�|�P�j�@@this.FX������� != null�̏ꍇ�Avalidate���\�b�h���Ăяo���B<BR>
     * <BR>
     * @@roseuid 41BE503202D5
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
                "���N�G�X�g�f�[�^.�U�֋��z������"); 
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

        //FX�������`�F�b�N
        // this.FX������� != null�̏ꍇ�Avalidate���\�b�h���Ăяo���B
        if (this.fxAccInformationUnit != null)
        {
            //this.FX�������.validate���\�b�h���Ăяo��
            this.fxAccInformationUnit.validate();
        }

        log.exiting(l_strMethodName);
    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41E7693202BF
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransFromFXConfirmResponse(this);
    }
}@
