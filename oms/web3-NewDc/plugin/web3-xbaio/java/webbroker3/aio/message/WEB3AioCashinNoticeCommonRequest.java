head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�����ʃ��N�G�X�g�N���X(WEB3AioCashinNoticeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[
                   2005/1/11 ���E (���u) �c�Ή�   
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����A�����ʃ��N�G�X�g)<BR>
 * �����A�����ʃ��N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCommonRequest extends WEB3GenRequest 
{
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410111316L;     
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeCommonRequest.class);
    
    /**
     * (�U����)<BR>
     * ��ʂɂē��͂��ꂽ�U����
     */
    public Date transferDate;
    
    /**
     * (�U������Z�@@�փR�[�h)<BR>
     * ��ʂɂđI�����ꂽ�U������Z�@@�ւ̃R�[�h
     */
    public String finInstitutionCode;
    
    /**
     * (�����z)<BR>
     * ��ʂɂē��͂��ꂽ�����z
     */
    public String cashinAmt;
    
    /**
     * (���[���A�h���X)<BR>
     * ��ʂɂē��͂��ꂽ���[���A�h���X
     */
    public String emailAddress;
    
    /**
     * @@roseuid 4158E9B70229
     */
    public WEB3AioCashinNoticeCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�U�����`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�U���� = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00775<BR>
     * <BR>
     * <BR>
     * �Q�j�U������Z�@@�փR�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h = null or<BR>
     *   ���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h.length() != 15<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00776<BR>
     * <BR>
     * <BR>
     * �R�j�������z�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂�� or<BR>
     *   ���N�G�X�g�f�[�^.�������z = null or<BR>
     *   ���N�G�X�g�f�[�^.�������z <= 0 or<BR>
     *   ���N�G�X�g�f�[�^.�������z.length() > 10<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00766<BR>
     * <BR>
     * @@roseuid 40E2504801AE
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //�P�j�U�����`�F�b�N
        //���N�G�X�g�f�[�^.�U���� = null  �̏ꍇ�A��O���X���[����
        if (this.transferDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00775,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U���� = null");
        }
        //�Q�j�U������Z�@@�փR�[�h�`�F�b�N
        //���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h = null or
        //���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h.length() != 15
        //�̏ꍇ�A��O���X���[����    
        if (WEB3StringTypeUtility.isEmpty(this.finInstitutionCode) || 
            this.finInstitutionCode.length() != 15)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00776,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h = null or " +
                "���N�G�X�g�f�[�^.�U������Z�@@�փR�[�h.length() != 15");
        }
        //�R�j�������z�`�F�b�N
        //���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂�� or
        //���N�G�X�g�f�[�^.�������z = null or
        //���N�G�X�g�f�[�^.�������z <= 0 or<BR>
        //���N�G�X�g�f�[�^.�������z.length() > 10
        //�̏ꍇ�A��O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.cashinAmt) ||
            !WEB3StringTypeUtility.isNumber(this.cashinAmt) ||              
            Double.parseDouble(this.cashinAmt) <= 0 || 
            this.cashinAmt.length() > 10)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂�� or " +
                "���N�G�X�g�f�[�^.�������z = null or " +
                "���N�G�X�g�f�[�^.�������z <= 0 or " +
                "���N�G�X�g�f�[�^.�������z.length() > 10");
        }
        log.exiting(l_strMethodName);     
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �����A�����ʃ��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B70247
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinNoticeCommonResponse(this);
    }
}
@
