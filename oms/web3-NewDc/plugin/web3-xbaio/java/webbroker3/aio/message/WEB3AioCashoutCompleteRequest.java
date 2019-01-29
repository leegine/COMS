head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���������N�G�X�g(WEB3AioCashoutCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���\���������N�G�X�g)<BR>
 * �o���\���������N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutCompleteRequest extends WEB3AioCashoutCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101305L;    
    
    /**
     * (��ʂɂē��͂��ꂽ�Ïؔԍ�)
     */
    public String password;
    
    /**
     * (�m�F��������)<BR>
     * �m�F�������̔�����<BR>
     * �i��ʕ\���Ȃ��j
     */
    public Date checkDate;
    
    /**
     * (�m�F������ID)<BR>
     * �m�F�������̒���ID<BR>
     * �i��ʕ\���Ȃ��j
     */
    public long checkOrderID;
   
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCompleteRequest.class);  
            
    /**
     * @@roseuid 4158EB6102B8
     */
    public WEB3AioCashoutCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�m�F���������`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�m�F�������� = null �̏ꍇ�A��O���X���[����B<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * <BR>
     * �R�j�m�F������ID�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�m�F������ID = 0 �̏ꍇ�A��O���X���[����B<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00770<BR>
     * <BR>
     * @@roseuid 40E281BC022B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
          
        //�P�j�X�[�p�[�N���X��validate���\�b�h���R�[������B
        super.validate();
      
        //�Q�j�m�F���������`�F�b�N
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
           
        //�R�j�m�F������ID�`�F�b�N<BR>
        //���N�G�X�g�f�[�^.�m�F������ID = 0 �̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00770
        if (this.checkOrderID == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00770,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�m�F������ID = 0");   
        }   
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �o���\���������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutCompleteResponse(this);
    }
}
@
