head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�����J�݊m�F���N�G�X�g(WEB3FXAccOpenConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                   2006/2/23 �ʉ�(SRA) �d�l�ύX�E���f��499
                   2006/3/27 �ʉ�(SRA) �d�l�ύX�E���f��523
Revesion History : 2008/05/19 �đo�g(���u) �d�l�ύX ���f��No.865
Revesion History : 2009/09/25 �����F(���u) �d�l�ύX ���f��No.1231
Revesion History : 2009/10/27 �����F(���u) �d�l�ύX ���f��No.1244
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAgreementDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�����J�݊m�F���N�G�X�g) <BR>
 * FX�����J�݊m�F���N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXAccOpenConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_acc_open_confirm";

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
     * (FX���[���A�h���X�i�m�F�p�j) <BR>
     * �ב֕ۏ؋�����p�ɓo�^���郁�[���A�h���X�i�m�F�p�j <BR>
     */
    public String fxMailAddressCnf;

    /**
     * (FX�Ïؔԍ�) <BR>
     * �ב֕ۏ؋�����p�ɓo�^����Ïؔԍ� <BR>
     */
    public String fxPassword;

    /**
     * (FX�Ïؔԍ��i�m�F�p�j) <BR>
     * �ב֕ۏ؋�����p�ɓo�^����Ïؔԍ��i�m�F�p�j <BR>
     */
    public String fxPasswordCnf;

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
     * (FX�Ïؔԍ��Q)<BR>
     * FX�Ïؔԍ��Q<BR>
     */
    public String fxPassword2;

    /**
     * (FX�Ïؔԍ��Q(�m�F�p))<BR>
     * FX�Ïؔԍ��Q(�m�F�p)<BR>
     */
    public String fxPassword2Cnf;

    /**
     * @@roseuid 41E78298029F
     */
    public WEB3FXAccOpenConfirmRequest()
    {
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenConfirmRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j FX���[���A�h���X�`�F�b�N <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * <BR>
     * �P�|�P�j this.FX���[���A�h���X��null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01700 <BR>
     * <BR>
     * �P�|�Q�j this.FX���[���A�h���X�i�m�F�p�j��null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01788 <BR>
     * <BR>
     * �P�|�R�j this.FX���[���A�h���X��this.FX���[���A�h���X�i�m�F�p�j <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01789 <BR>
     * <BR>
     * �P�|�S�j this.FX���[���A�h���X���K�؂ȓ��e�iWEB3StringTypeUtility.isMailAddress ()��false�j <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00777 <BR>
     * <BR>
     * �Q�j FX�Ïؔԍ��`�F�b�N <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * <BR>
     * �Q�|�P�j this.FX�Ïؔԍ���null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01090 <BR>
     * <BR>
     * �Q�|�Q�j this.FX�Ïؔԍ��i�m�F�p�j��null <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01790 <BR>
     * <BR>
     * �Q�|�R�j this.FX�Ïؔԍ��� this.FX�Ïؔԍ��i�m�F�p�j <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01791 <BR>
     * <BR>
     * �Q�|�S�j this.FX�Ïؔԍ������p�p���� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * �Q�|�T�j this.FX�Ïؔԍ���4�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �Q�|�U�j this.FX�Ïؔԍ���12�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �R�j�@@������敪�`�F�b�N
     * <BR>
     * �ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �E�h�����M�h<BR>
     * �E�h���M�ρh<BR>
     * �E�h��̍ρh<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02348 <BR>
     * <BR>
     * �S�j�@@FX�Ïؔԍ��Q�`�F�b�N <BR>
     * �@@FX�Ïؔԍ��Q��null����Ȃ��ꍇ�ȉ��`�F�b�N���s���A <BR>
     * �@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B <BR>
     * <BR>
     * �@@�S�|�P�j�@@this.FX�Ïؔԍ��Q�� this.FX�Ïؔԍ��Q�i�m�F�p�j <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01791 <BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.FX�Ïؔԍ��Q�����p�p���� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * �@@�S�|�R�j�@@this.FX�Ïؔԍ��Q��8�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �@@�S�|�S�j�@@this.FX�Ïؔԍ��Q��12�� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �@@�S�|�T�j�@@this.FX�Ïؔԍ��Q��this.FX�Ïؔԍ� <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_03185 <BR>
     * <BR>
     * <BR>
     * @@roseuid 41C6584C01FD
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j FX���[���A�h���X�`�F�b�N 
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B  
        // �P�|�P�j this.FX���[���A�h���X��null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01700 
        if (WEB3StringTypeUtility.isEmpty(this.fxMailAddress))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01700,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX���[���A�h���X = null"); 
        }
        
        // �P�|�Q�j this.FX���[���A�h���X�i�m�F�p�j��null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01788 
        if (WEB3StringTypeUtility.isEmpty(this.fxMailAddressCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01788,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX���[���A�h���X�i�m�F�p�j = null"); 
        }
        
        // �P�|�R�j this.FX���[���A�h���X��this.FX���[���A�h���X�i�m�F�p�j  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01789 
        if (!this.fxMailAddress.equals(this.fxMailAddressCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01789,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX���[���A�h���X�����N�G�X�g�f�[�^.FX���[���A�h���X�i�m�F�p�j" + 
                "���N�G�X�g�f�[�^.FX���[���A�h���X = " + this.fxMailAddress +
                "���N�G�X�g�f�[�^.FX���[���A�h���X�i�m�F�p�j = " + this.fxMailAddressCnf); 
        }
 
        // �P�|�S�j this.FX���[���A�h���X���K�؂ȓ��e�iWEB3StringTypeUtility.isMailAddress ()��false�j  
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
 
        // �Q�j FX�Ïؔԍ��`�F�b�N 
        // �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B  
        // �Q�|�P�j this.FX�Ïؔԍ���null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01090 
        if (WEB3StringTypeUtility.isEmpty(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ���null"); 
        }
 
        // �Q�|�Q�j this.FX�Ïؔԍ��i�m�F�p�j��null  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01790 
        if (WEB3StringTypeUtility.isEmpty(this.fxPasswordCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01790,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ��i�m�F�p�j��null"); 
        }
 
        // �Q�|�R�j this.FX�Ïؔԍ��� this.FX�Ïؔԍ��i�m�F�p�j  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01791 
        if (!this.fxPassword.equals(this.fxPasswordCnf))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01791,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ������N�G�X�g�f�[�^.FX�Ïؔԍ��i�m�F�p�j" + 
                "���N�G�X�g�f�[�^.FX�Ïؔԍ� = " + this.fxPassword +
                "���N�G�X�g�f�[�^.FX�Ïؔԍ��i�m�F�p�j = " + this.fxPasswordCnf); 
        }
 
        // �Q�|�S�j this.FX�Ïؔԍ������p�p���� 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01302 
        if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.FX�Ïؔԍ������p�p����" + 
                "���N�G�X�g�f�[�^.FX�Ïؔԍ� = " + this.fxPassword); 
        }
 
        // �Q�|�T�j this.FX�Ïؔԍ���4��  
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
 
        // �Q�|�U�j this.FX�Ïؔԍ���12��  
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
        
        //�R�j�@@������敪�`�F�b�N
        //�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        //�E�h�����M�h
        //�E�h���M�ρh
        //�E�h��̍ρh
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_02348
        //if (this.)
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

        //�S�j�@@FX�Ïؔԍ��Q�`�F�b�N
        //�@@FX�Ïؔԍ��Q��null����Ȃ��ꍇ�ȉ��`�F�b�N���s���A
        //�@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B
        //�@@�S�|�P�j�@@this.FX�Ïؔԍ��Q�� this.FX�Ïؔԍ��Q�i�m�F�p�j
        if (this.fxPassword2 != null)
        {
            if (!this.fxPassword2.equals(this.fxPassword2Cnf))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01791,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q�����N�G�X�g�f�[�^.FX�Ïؔԍ��Q�i�m�F�p�j" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2 +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q�i�m�F�p�j = " + this.fxPassword2Cnf);
            }

            //�@@�S�|�Q�j�@@this.FX�Ïؔԍ��Q�����p�p����
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q�����p�p����" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
            }

            //�@@�S�|�R�j�@@this.FX�Ïؔԍ��Q��8��
            if (this.fxPassword2.length() < 8)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q��8��" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
            }

            //�@@�S�|�S�j�@@this.FX�Ïؔԍ��Q��12��
            if (this.fxPassword2.length() > 12)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q��12��" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
            }

            //�@@�S�|�T�j�@@this.FX�Ïؔԍ��Q��this.FX�Ïؔԍ�
            if (this.fxPassword.equals(this.fxPassword2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03185,
                    this.getClass().getName() + "." + l_strMethodName,
                    "FX�Ïؔԍ��Q��FX�Ïؔԍ�����v�ł��B" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
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
        return new WEB3FXAccOpenConfirmResponse(this);
    }
}@
