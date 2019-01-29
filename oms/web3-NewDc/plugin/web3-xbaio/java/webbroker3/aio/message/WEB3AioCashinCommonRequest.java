head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C���������ʃ��N�G�X�g(WEB3AioCashinCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬     
                   2004/10/22 ���� (���u) ���r���[                  
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
 * (�I�����C���������ʃ��N�G�X�g)<BR>
 * �I�����C���������ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinCommonRequest extends WEB3GenRequest 
{        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;       
    
    /**
     * (���ϋ@@��ID)<BR>
     * ��ʂɂđI�����ꂽ���ϋ@@�ւ�ID<BR>
     */
    public String paySchemeId;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCommonRequest.class);
    
    /**
     * @@roseuid 4158E9B6021E
     */
    public WEB3AioCashinCommonRequest() 
    {
     
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B60232
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinCommonResponse(this);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���ϋ@@��ID�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.���ϋ@@��ID = null or<BR>
     *   ���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11 or<BR>
     *   ���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith("ComOndebi") = false<BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00767<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E51CBA01B8
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���ϋ@@��ID�`�F�b�N
        //���N�G�X�g�f�[�^.���ϋ@@��ID = null
        if(WEB3StringTypeUtility.isEmpty(this.paySchemeId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���ϋ@@��ID = null"); 
        }
        
        //���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith("ComOndebi") = false        
        if(this.paySchemeId.startsWith("ComOndebi") == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith(ComOndebi) == false"); 
        }
        
        //���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11
        if(this.paySchemeId.length() != 11)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11, " +
                "���N�G�X�g�f�[�^.���ϋ@@��ID.length() = " + this.paySchemeId.length()); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
