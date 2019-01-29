head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountMngStateChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��X�e�[�^�X�X�V���ʃ��N�G�X�g(WEB3AdminFEqConAccountMngStateChangeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioAccountOpenCompleteDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������J�݊Ǘ��X�e�[�^�X�X�V���ʃ��N�G�X�g)<BR>
 * �O�������J�݊Ǘ��X�e�[�^�X�X�V���ʃ��N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountMngStateChangeCommonRequest extends WEB3GenRequest 
{
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h
     */
    public String requestNumber;
    
    /**
     * (�X�V��X�e�[�^�X�敪)<BR>
     * �X�V��X�e�[�^�X�敪<BR>
     * <BR>
     * 1�F�����J�݊���<BR>
     * 9�F�폜
     */
    public String updatedStatusDiv;
    
    /**
     * @@roseuid 423552EA0280
     */
    public WEB3AdminFEqConAccountMngStateChangeCommonRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountMngStateChangeCommonRequest.class);
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j ���X�R�[�h<BR>
     * �@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@���ʃR�[�h�̃`�F�b�N<BR>
     * �@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00829<BR>
     * <BR>
     * �R�j�@@�X�V��X�e�[�^�X�敪�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�R�|�Q�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@       �E"�����J�݊���"<BR>
     * �@@�@@       �E"�폜"
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01749<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01750<BR>
     * <BR>
     * @@roseuid 41E606A40230
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j ���X�R�[�h 
        //�����͂̏ꍇ�A��O���X���[����B
        if(WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        
        //�Q�j�@@���ʃR�[�h�̃`�F�b�N 
        //�����͂̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʃR�[�h�����w��ł��B");
        }
        
        //�R�j�@@�X�V��X�e�[�^�X�敪�̃`�F�b�N 
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        //�R�|�Q�j�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //�E"�����J�݊���" 
        //�E"�폜" 
        if(WEB3StringTypeUtility.isEmpty(this.updatedStatusDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01749,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�V��X�e�[�^�X�敪�����w��ł��B");
        }
        
        if(!(WEB3AioAccountOpenCompleteDef.OPEN_COMPLETE.equals(this.updatedStatusDiv) 
            || WEB3AioAccountOpenCompleteDef.DELETE.equals(this.updatedStatusDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01750,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�V��X�e�[�^�X�敪[" + this.updatedStatusDiv + "]�B");
            
        }
         
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 423552EA02DE
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
