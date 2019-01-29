head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����z�m�F���N�G�X�g(WEB3AioCashinConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬      
                   2004/10/22 ���� (���u) ���r���[                 
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����z�m�F���N�G�X�g)<BR>
 * �����z�m�F���N�G�X�g�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinConfirmRequest extends WEB3AioCashinCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;       
    
    /**
     * (�������z)<BR>
     * ��ʂɂē��͂��ꂽ�����z<BR>
     */
    public String cashinAmt;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinConfirmRequest.class);
    
    /**
     *  �f�t�H���g�R���X�g���N
     * @@roseuid 4158E9B603AE
     */
    public WEB3AioCashinConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinConfirmResponse(this);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�������z�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂�� or<BR>
     *   ���N�G�X�g�f�[�^.�������z = null or<BR>
     *   ���N�G�X�g�f�[�^.�������z.length() > 12<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00766<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E258140140
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate���\�b�h���R�[������
        super.validate();
        
        //�Q�j�������z�`�F�b�N        
        //���N�G�X�g�f�[�^.�������z = null
        if(WEB3StringTypeUtility.isEmpty(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�������z = null");              
        }
        
        //���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂��        
        if(!WEB3StringTypeUtility.isNumber(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂��, " +
                "���N�G�X�g�f�[�^.�������z = " + this.cashinAmt);              
        }

        //���N�G�X�g�f�[�^.�������z.length()
        if(this.cashinAmt.length() > 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�������z.length() > 12 , " +
                "���N�G�X�g�f�[�^.�������z.length() = " + this.cashinAmt.length());              
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
}
@
