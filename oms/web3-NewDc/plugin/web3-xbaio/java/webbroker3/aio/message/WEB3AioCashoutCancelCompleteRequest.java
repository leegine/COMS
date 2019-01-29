head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������������N�G�X�g(WEB3AioCashoutCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
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
 * (�o������������N�G�X�g)<BR>
 * �o������������N�G�X�g�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutCancelCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410100928L;    
    
    /**
     * (����ΏۂƂȂ�o��������ID)
     */
    public String orderId;
    
    /**
     * (�Ïؔԍ�)<BR>
     * ��ʂɂē��͂��ꂽ�Ïؔԍ�
     */
    public String password;
    
    /**
     * (�m�F�������̔�����)
     * �i��ʕ\���Ȃ��j
     */
    public Date checkDate;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelCompleteRequest.class);  
    /**
     * @@roseuid 4158EB5E01E1
     */
    public WEB3AioCashoutCancelCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j����ID�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.����ID = null �̏ꍇ�A��O���X���[����B<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * <BR>
     * �Q�j�m�F���������`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�m�F�������� = null �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * @@roseuid 40E5197000DD
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����ID�`�F�b�N
        //���N�G�X�g�f�[�^.����ID = null �̏ꍇ�A��O���X���[����B
           //class: WEB3BusinessLayerException
           //tag:   BUSINESS_ERROR_00600
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.����ID = null");   
        } 
        
        //�Q�j�m�F���������`�F�b�N<BR>
        //���N�G�X�g�f�[�^.�m�F�������� = null �̏ꍇ�A��O���X���[����B
          //class: WEB3BusinessLayerException
          //tag:   BUSINESS_ERROR_00078
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�m�F�������� = null");   
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �o������������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutCancelCompleteResponse(this);
    }
}
@
