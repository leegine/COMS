head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o�����͋��ʃ��N�G�X�g(WEB3AdminFeqExecutionCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO�������o�����͋��ʃ��N�G�X�g)<BR>
 * �Ǘ��ҊO�������o�����͋��ʃ��N�G�X�g�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionCommonRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionCommon";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;   
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String orderId;
    
    /**
     * (���בփ��[�g)<BR>
     * ���בփ��[�g
     */
    public String execExchangeRate;
    
    /**
     * (����)<BR>
     * ����
     */
    public Date executionDate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��
     */
    public Date localDeliveryDate;
    
    /**
     * (�o�����ꗗ)<BR>
     * ��ʂœ��͂��ꂽ�O�����������̔z��
     */
    public WEB3FeqExecuteUnit[] executeList;
    
    /**
     * @@roseuid 42CE39FD0261
     */
    public WEB3AdminFeqExecutionCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����h�c�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * �Q�j�@@���בփ��[�g�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02036<BR>
     *   �Q�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02220<BR>
     * �@@�Q�|�R�j�@@���l�ɕϊ��������̗L���������A�������R���C<BR>
     *   �������S���͈̔͊O�ł���΁A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02037<BR>
     *   �Q�|�S�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B
     *   class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02196<BR>
     * <BR>
     * �R�j�@@�o�����`�F�b�N<BR>
     * �@@�R�|�P�j�@@�o�����ꗗ[] �������͂̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02038<BR>
     * �@@�R�|�Q�j�@@�o�����ꗗ[] �̊e�v�f.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 428D8A8E03A9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����h�c�̃`�F�b�N
        // �P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        // class: WEB3BusinessLayerException
        // tag:   BUSINESS_ERROR_00600
        if (WEB3StringTypeUtility.isEmpty(orderId)) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                " �����h�c�������͂̏ꍇ�`�F�b�N"); 
        } 

        //�Q�j�@@���בփ��[�g�̃`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02036
        if (WEB3StringTypeUtility.isEmpty(execExchangeRate)) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02036,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���בփ��[�g�������͂̏ꍇ�`�F�b�N"); 
        } 

        //�Q�|�Q�j�@@���l�łȂ��ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02220
        if (!WEB3StringTypeUtility.isNumber(execExchangeRate))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���בփ��[�g�����l�łȂ��̏ꍇ�`�F�b�N"); 
        }
        
        //�Q�|�R�j�@@���l�ɕϊ��������̗L���������A�������R���C
        //�������S���͈̔͊O�ł���΁A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02037
        else if (!WEB3StringTypeUtility.isNumber(execExchangeRate) 
            || WEB3StringTypeUtility.getIntegerDigits(execExchangeRate) > 3
            || WEB3StringTypeUtility.getFractionDigits(execExchangeRate) > 4) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���l�ɕϊ��������̗L�������`�F�b�N"); 
        }         
        //�Q�|�S�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02196
        else if (Double.parseDouble(execExchangeRate) <= 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���l�ɕϊ������l <= 0," + execExchangeRate); 
        }

        //�R�j�@@�o�����`�F�b�N
        // �R�|�P�j�@@�o�����ꗗ[] �������͂̏ꍇ�A��O���X���[����B
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_02038
        if (executeList == null || executeList.length == 0) 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02038,
                this.getClass().getName() + STR_METHOD_NAME,
                " �o�����ꗗ[] �������͂̏ꍇ�`�F�b�N"); 
        }
        
        //�R�|�Q�j�@@�o�����ꗗ[] �̊e�v�f.validate()���R�[������B
        for (int i = 0; i < executeList.length; i++) 
        {
            executeList[i].validate();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
