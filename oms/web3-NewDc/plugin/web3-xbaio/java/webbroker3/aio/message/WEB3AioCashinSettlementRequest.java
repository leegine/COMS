head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettlementRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ψ˗����N�G�X�g(WEB3AioCashinSettlementRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬    
                   2004/10/22 ���� (���u) ���r���[                   
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���ψ˗����N�G�X�g)<BR>
 * ���ψ˗����N�G�X�g�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettlementRequest extends WEB3AioCashinCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_settlement";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (�������z)<BR>
     * ��ʂɂē��͂��ꂽ�����z<BR>
     */
    public String cashinAmt;
    
    /**
     * (�Ïؔԍ�)<BR>
     * ��ʂɂē��͂��ꂽ�Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * (�m�F��������)<BR>
     * �m�F�������̔�����<BR>
     * �i��ʕ\���Ȃ��j<BR>
     */
    public Date checkDate;
    
    /**
     * (PR�w�ێ����)<BR>
     * �i��ʕ\���Ȃ��j<BR>
     */
    public WEB3AioPrSessionUnit prSessionUnit ;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettlementRequest.class);
    
    /**
     * @@roseuid 4158EB3401B9
     */
    public WEB3AioCashinSettlementRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinSettlementResponse(this);
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
     * �R�j�m�F���������`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�m�F�������� = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * <BR>
     * �S�jPR�w�ێ����`�F�b�N<BR>
     *    �S�|�P�j
     *   ���N�G�X�g�f�[�^.PR�w�ێ���� = null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01739<BR>
     * <BR>
     * <BR>
     *    �S�|�Q�j 
     *   PR�w�ێ����̊e���� = null�̏ꍇ�A��O���X���[����B 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01740<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E2633702E6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate���\�b�h���R�[������
        super.validate();
        
        //���N�G�X�g�f�[�^.�������z = null
        if(WEB3StringTypeUtility.isEmpty(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�������z = null ");
        }
        
        //�Q�j�������z�`�F�b�N 
        //���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂��
        if(!WEB3StringTypeUtility.isNumber(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�������z�ɐ����ȊO�̕������܂܂��, " +
                "���N�G�X�g�f�[�^.�������z = " + this.cashinAmt);
        }
            
        //���N�G�X�g�f�[�^.�������z.length() > 12 
        if(this.cashinAmt.length() > 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�������z.length() > 12, " +
                "���N�G�X�g�f�[�^.�������z.length() = " + this.cashinAmt.length());
        }        

        //�R�j�m�F���������`�F�b�N 
        //���N�G�X�g�f�[�^.�m�F�������� = null
        if(this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�m�F�������� = null");
        }
        
        //=====remain zhou-yong NO.1 begin =========
        
        //�S�jPR�w�ێ����`�F�b�N
        //�S�|�P�j 
        //���N�G�X�g�f�[�^.PR�w�ێ���� = null 
        //�̏ꍇ�A��O���X���[����B
        if(this.prSessionUnit == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01739,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.PR�w�ێ���� = null");
            
        }
        //�S�|�Q�j 
        //PR�w�ێ����̊e���� = null 
        //�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.prSessionUnit.regetServiceId)
            && WEB3StringTypeUtility.isEmpty(this.prSessionUnit.wolfAid)
            && WEB3StringTypeUtility.isEmpty(this.prSessionUnit.wolfSession)
            && WEB3StringTypeUtility.isEmpty(this.prSessionUnit.wolfSsid))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01740,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PR�w�ێ����̊e���� = null");
            
        }

        //=====remain zhou-yong NO.1 end =========
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
