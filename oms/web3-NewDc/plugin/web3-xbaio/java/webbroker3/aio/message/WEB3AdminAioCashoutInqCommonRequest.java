head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇�����ʃ��N�G�X�g�N���X(WEB3AdminAioCashoutInqCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2004/12/10 ���E (���u) �c�Ή�
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.aio.define.WEB3AioTransferDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�o���\���⍇�����ʃ��N�G�X�g)<BR>
 * �o���\���⍇�����ʃ��N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCommonRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_common";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131341L;
        
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h
     */
    public String[] branchCode;
    
    /**
     * (��n��)<BR>
     * ��ʂɂē��͂��ꂽ��n��
     */
    public Date deliveryDate;
    
    /**
     * (������t�敪)<BR>
     * ��ʂɂđI�����ꂽ������t�敪<BR>
     * <BR>
     * 0�F ��t����<BR>
     * 1�F ��t��<BR>
     * 2�F ��t�G���[<BR>
     * 3�F �S��<BR>
     */
    public String orderDiv;
    
    //===========remain zhou-yong NO.1 begin ========
    
    /**
     * (�U����敪)<BR>
     * ��ʂɂđI�����ꂽ�U����敪<BR>
     * <BR>
     * 0�F �h�S�āh<BR> 
     * 1�F �h�X���h<BR> 
     * 2�F �h���̑��h�i�X���ȊO�j 
     */
    public String transferDiv;
    
    //===========remain zhou-yong NO.1 end ========  
    
    /**
     * (�w�����X�g)<BR>
     * �w�����s������ID�̃��X�g
     */
    public String[] directionsList;
    
    /**
     * @@roseuid 4158EB640294
     */
    public WEB3AdminAioCashoutInqCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N <BR>
     * <BR>
     * �P�|�P�j���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0 or <BR>
     *        ���N�G�X�g�f�[�^.���X�R�[�h = null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * �P�|�Q�j�z��̊e�v�f�ɂ��� <BR>
     * �P�|�Q�|�P�j�e�v�f.length() != 3 �̏ꍇ�A��O���X���[����B<BR> 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834 <BR>
     * <BR>
     * �P�|�Q�|�Q�j�e�v�f�ɐ����ȊO�̕���������̏ꍇ�A��O���X���[����B<BR> 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729 <BR>
     * <BR>
     * �Q�j������t�敪�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.������t�敪 != 0�i��t���ρj and<BR>
     *   ���N�G�X�g�f�[�^.������t�敪 != 1�i��t�ρj and<BR>
     *   ���N�G�X�g�f�[�^.������t�敪 != 2�i��t�G���[�j and<BR>
     *   ���N�G�X�g�f�[�^.������t�敪 != 3�i�S�āj  �̏ꍇ�A��O���X���[����B<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00790<BR>
     * <BR>
     * <BR>
     * �R�j�U����敪�`�F�b�N <BR>
     * ���N�G�X�g�f�[�^.�U����敪 != (0, 1, 2)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01363<BR>
     * <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �S�j�w�����X�g�`�F�b�N<BR>
     *   �z��̗v�f����0   �̏ꍇ�A��O���X���[����B<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00793<BR>
     * <BR>
     * @@roseuid 4129B310019D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���X�R�[�h�`�F�b�N 

        //�P�|�P�j���N�G�X�g�f�[�^.���X�R�[�h = null �̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);   
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���X�R�[�h = null");
        }      
        
        //���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0 
        if (this.branchCode.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���N�G�X�g�f�[�^.���X�R�[�h�̗v�f�� = 0");
            }      
        
        //�P�|�Q�j�z��̊e�v�f�ɂ��� 

        //�P�|�Q�|�P�j�e�v�f.length() != 3 �̏ꍇ�A��O���X���[����B 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (this.branchCode[i].length() != 3)
            {           
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
        }

        //�P�|�Q�|�Q�j�e�v�f�ɐ����ȊO�̕���������̏ꍇ�A��O���X���[����B 
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }
        
        //�Q�j������t�敪�`�F�b�N 

        //���N�G�X�g�f�[�^.������t�敪 != 0�i��t���ρj and 
        //���N�G�X�g�f�[�^.������t�敪 != 1�i��t�ρj and 
        //���N�G�X�g�f�[�^.������t�敪 != 2�i��t�G���[�j and 
        //���N�G�X�g�f�[�^.������t�敪 != 3�i�S�āj 

        if (!WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(this.orderDiv) &&
            !WEB3AioOrderAcceptedDivDef.ALL.equals(this.orderDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00790,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.������t�敪 != (0�i��t���ρj, 1�i��t�ρj, " +
                "2�i��t�G���[�j, 3�i�S�āj), " +
                "���N�G�X�g�f�[�^.������t�敪 = " + this.orderDiv);
        }
        
        //=========remain zhou-yong NO.2 begin =============
        
        //�R�j�U����敪�`�F�b�N
        //���N�G�X�g�f�[�^.�U����敪 != (0, 1, 2)
        //�̏ꍇ�A��O���X���[����B 
        if(!(WEB3AioTransferDivDef.ALL.equals(this.transferDiv) || 
            WEB3AioTransferDivDef.POSTAL_SAVINGS.equals(this.transferDiv) || 
            WEB3AioTransferDivDef.OTHERS.equals(this.transferDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�U����敪 != (0, 1, 2), " +
                "���N�G�X�g�f�[�^.�U����敪 = " + this.transferDiv);
            
        }
        
        //=========remain zhou-yong NO.2 end =============
        
        //�S�j�w�����X�g�`�F�b�N 
        //�z��̗v�f����0 
        if (directionsList == null || directionsList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00793,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z��̗v�f����0");
        }

    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return  null;        
    }
}
@
