head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenAskingRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�݈˗����N�G�X�g(WEB3FXAccOpenAskingRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                   2006/2/9 �]�V�q(���u) �d�l�ύX�E���f��457
                   2006/2/23 �ʉ�(SRA) �d�l�ύX�E���f��499
                   2006/3/27 �ʉ�(SRA) �d�l�ύX�E���f��523
Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.865
Revesion History : 2009/10/09 �����F(���u) �d�l�ύX ���f��No.1236
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�����J�݈˗����N�G�X�g) <BR>
 * FX�����J�݈˗����N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAccOpenAskingRequest extends WEB3FXAskingCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_asking";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (FX���[���A�h���X) <BR>
     * �ב֕ۏ؋�����p�ɓo�^���郁�[���A�h���X <BR>
     */
    public String fxMailAddress;

    /**
     * (FX�Ïؔԍ�) <BR>
     * �ב֕ۏ؋�����p�ɓo�^����Ïؔԍ� <BR>
     */
    public String fxPassword;

    /**
     * (FX������ӎ�����ꗗ) <BR>
     * FX������ӎ�����̈ꗗ <BR>
     */
    public WEB3FXTradeAgreementUnit[] fxTradeAgreementList;

    /**
     * (�Ïؔԍ�) <BR>
     * WEB3�Ïؔԍ� <BR>
     */
    public String password;

    /**
     * (������敪)<BR>
     * ������敪<BR>
     * <BR>
     * 0�F �����M<BR>
     * 1�F ���M��<BR>
     * 2�F ��̍�<BR>
     */
    public String agreementDiv;

    /**
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78299000F
     */
    public WEB3FXAccOpenAskingRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenAskingRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j �X�[�p�[�N���X��validate���\�b�h���Ăяo���B <BR>
     * <BR>
     * �Q�j FX���[���A�h���X�`�F�b�N <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * <BR>
     * �Q�|�P�j this.FX���[���A�h���X��null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01700 <BR>
     * <BR>
     * �Q�|�Q�j this.FX���[���A�h���X<BR>
     *   ���K�؂ȓ��e�iWEB3StringTypeUtility.isMailAddress ()��false�j <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00777 <BR>
     * <BR>
     * �R�j FX�Ïؔԍ��`�F�b�N <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * <BR>
     * �R�|�P�j this.FX�Ïؔԍ���null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01090 <BR>
     * <BR>
     * �R�|�Q�j this.FX�Ïؔԍ������p�p���� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * �R�|�R�j this.FX�Ïؔԍ���4�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �R�|�S�j this.FX�Ïؔԍ���12�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �S�j�@@������敪�`�F�b�N<BR>
     * �@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�E�h�����M�h<BR>
     * �@@�@@�E�h���M�ρh<BR>
     * �@@�@@�E�h��̍ρh<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag: BUSINESS_ERROR_02348<BR>
     * <BR>
     * �T�j FX������ӎ�����`�F�b�N <BR>
     * this.FX������ӎ�����ꗗ��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �U�j FX������ӎ�������e�`�F�b�N <BR>
     * thisFX������ӎ�����ꗗ�̗v�f���ƂɈȉ��̃`�F�b�N���s���B <BR>
     * <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * <BR>
     * �EFX������ӎ�����.����ԍ���null�̏ꍇ <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * �EFX������ӎ�����.����񓚁�null�̏ꍇ <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * 
     * @@roseuid 41C95A9E033D
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j �X�[�p�[�N���X��validate���\�b�h���Ăяo���B 
        super.validate();
 
        // �Q�j FX���[���A�h���X�`�F�b�N 
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B  
        // �Q�|�P�j this.FX���[���A�h���X��null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01700 
        if (WEB3StringTypeUtility.isEmpty(this.fxMailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01700,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX���[���A�h���X = null"); 
        }
         
        // �Q�|�Q�j this.FX���[���A�h���X
        //   ���K�؂ȓ��e�iWEB3StringTypeUtility.isMailAddress ()��false�j  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00777
        if (!WEB3StringTypeUtility.isMailAddress(this.fxMailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX���[���A�h���X���K�؂ȓ��e" + 
                "���N�G�X�g�f�[�^.FX���[���A�h���X = " + this.fxMailAddress); 
        }
 
        // �R�j FX�Ïؔԍ��`�F�b�N 
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B  
        // �R�|�P�j this.FX�Ïؔԍ���null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01090
        if (WEB3StringTypeUtility.isEmpty(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ���null"); 
        } 
 
        // �R�|�Q�jthis.FX�Ïؔԍ������p�p���� 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01302 
        if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.this.FX�Ïؔԍ������p�p���� " + 
                "���N�G�X�g�f�[�^.FX�Ïؔԍ� = " + this.fxPassword); 
        }
 
        // �R�|�R�j this.FX�Ïؔԍ���4�� 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01784 
        if (this.fxPassword.length() < 4)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ���4��" +
                "���N�G�X�g�f�[�^.FX�Ïؔԍ� = " + this.fxPassword); 
        }
 
        // �R�|�S�j this.FX�Ïؔԍ���12��  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01784 
        if (this.fxPassword.length() > 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ���12��" + 
                "���N�G�X�g�f�[�^.FX�Ïؔԍ� = " + this.fxPassword); 
        }
 
        //�S�j�@@������敪�`�F�b�N
        //�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�E�h�����M�h
        //�E�h���M�ρh
        //�E�h��̍ρh
        if(!(WEB3AioAgreementDivDef.NOT_SEND.equals(this.agreementDiv)
           || WEB3AioAgreementDivDef.SENDED.equals(this.agreementDiv)
           || WEB3AioAgreementDivDef.RECIEVED.equals(this.agreementDiv)))           
        {
            log.debug("������敪�̒l���s���ł��B");
            log.exiting(l_strMethodName);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02348,
                this.getClass().getName() + "." + l_strMethodName, 
                "������敪�̒l���s���ł��B");
        }
        
        // �T�j FX������ӎ�����`�F�b�N 
        // this.FX������ӎ�����ꗗ��null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309 
        if (fxTradeAgreementList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX������ӎ�����ꗗ��null"); 
        }

        // �U�j FX������ӎ�������e�`�F�b�N 
        // thisFX������ӎ�����ꗗ�̗v�f���ƂɈȉ��̃`�F�b�N���s���B  
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B 
        for (int i = 0; i < fxTradeAgreementList.length; i++)
        {
            // �EFX������ӎ�����.����ԍ���null�̏ꍇ 
            // class: WEB3BusinessLayerException 
            // tag: BUSINESS_ERROR_01309
            if (WEB3StringTypeUtility.isEmpty(fxTradeAgreementList[i].questionNumber))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX������ӎ�����ꗗ.����ԍ���null"); 
            }
            
            // �EFX������ӎ�����.����񓚁�null�̏ꍇ  
            // class: WEB3BusinessLayerException 
            // tag: BUSINESS_ERROR_01309
            if (WEB3StringTypeUtility.isEmpty(fxTradeAgreementList[i].questionAnswer))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX������ӎ�����ꗗ.����񓚁�null"); 
            }
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenAskingResponse(this);
    }
}@
