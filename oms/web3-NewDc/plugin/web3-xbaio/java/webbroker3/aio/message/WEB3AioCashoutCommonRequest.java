head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\�����ʃ��N�G�X�g(WEB3AioCashoutCommonRequest)
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
 * (�o���\�����ʃ��N�G�X�g)<BR>
 * �o���\�����ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101115L;    
    
    /**
     * (�o�����z)<BR>
     * ��ʂɂē��͂��ꂽ�o���z
     */
    public String cashoutAmt;
    
    /**
     * (�U���\���)<BR>
     * ��ʂɂđI�����ꂽ�U���\���
     */
    public Date transScheduledDate;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCommonRequest.class);  
            
    /**
     * @@roseuid 4158EB610005
     */
    public WEB3AioCashoutCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�o�����z�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�o�����z�ɐ����ȊO�̕������܂܂�� or<BR>
     *   ���N�G�X�g�f�[�^.�o�����z = null or<BR>
     *   ���N�G�X�g�f�[�^.�o�����z <= 0 or<BR>
     *   ���N�G�X�g�f�[�^.�o�����z.length() > 9
     *     �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00771<BR>
     * <BR>
     * <BR>
     * �Q�j�U���\����`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�U���\��� = null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00772<BR>
     * <BR>
     * @@roseuid 40E249B8022B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�o�����z�`�F�b�N
          //���N�G�X�g�f�[�^.�o�����z�ɐ����ȊO�̕������܂܂�� or
          //���N�G�X�g�f�[�^.�o�����z = null or
          //���N�G�X�g�f�[�^.�o�����z <= 0 or
          //���N�G�X�g�f�[�^.�o�����z.length() > 9
          //�̏ꍇ�A��O���X���[����B
            //class: WEB3BusinessLayerException
            //tag:   BUSINESS_ERROR_00771
          if (WEB3StringTypeUtility.isEmpty(this.cashoutAmt) || 
             (WEB3StringTypeUtility.isNumber(this.cashoutAmt) == false) || 
             (Double.parseDouble(this.cashoutAmt) <= 0) ||
             (this.cashoutAmt.length() > 9))
          {
              throw new WEB3BusinessLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_00771,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  "���N�G�X�g�f�[�^.�o�����z�ɐ����ȊO�̕������܂܂�� or " +
                  "���N�G�X�g�f�[�^.�o�����z = null or " +
                  "���N�G�X�g�f�[�^.�o�����z <= 0 or " +
                  "���N�G�X�g�f�[�^.�o�����z.length() > 9");   
          }  
        
        //�Q�j�U���\����`�F�b�N
          //���N�G�X�g�f�[�^.�U���\��� = null�̏ꍇ�A��O���X���[����B
            //class: WEB3BusinessLayerException
            // tag:   BUSINESS_ERROR_00772
        if (this.transScheduledDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00772,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U���\��� = null");  
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �o���\�����ʃ��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB61002D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutCommonResponse(this);
    }
}
@
