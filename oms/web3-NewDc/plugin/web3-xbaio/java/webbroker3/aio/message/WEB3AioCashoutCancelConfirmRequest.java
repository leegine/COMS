head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������m�F���N�G�X�g(WEB3AioCashoutCancelConfirmRequest)
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
 * (�o������m�F���N�G�X�g)<BR>
 * �o������m�F���N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashoutCancelConfirmRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (����ΏۂƂȂ�o��������ID)
     */
    public String orderId;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCancelConfirmRequest.class);
    
    /**
     * �f�t�H���g�R���X�g���N
     * @@roseuid 4158EB5F020B
     */
    public WEB3AioCashoutCancelConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5F0229
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3AioCashoutCancelConfirmResponse(this);                 
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j����ID�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.����ID = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F3AE1E038A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����ID�`�F�b�N
        if(WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.����ID = null"); 
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
}
@
