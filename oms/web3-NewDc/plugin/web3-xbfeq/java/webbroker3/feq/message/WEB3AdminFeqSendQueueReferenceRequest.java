head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������SEND�L���[�Ɖ�ꗗ���N�G�X�g(WEB3AdminFeqSendQueueReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
Revesion History : 2007/02/03 ������ (���u) ���f�� No.341
Revesion History : 2007/02/07 ������ (���u) ���f�� No.342
Revesion History : 2008/02/01 �g�C�� (���u) ���f�� No.393
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO������SEND�L���[�Ɖ�ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҊO������SEND�L���[�Ɖ�ꗗ���N�G�X�g�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminFeqSendQueueReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_send_queue_reference";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3AdminFeqSendQueueReferenceRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFeqSendQueueReferenceRequest.class);
    
    /**
     * (�����敪)<BR>
     * 0�F�����҂�<BR>
     * 1�F�����ς�<BR> 
     * 2�F�đ��M�҂�<BR>
     * 6�F���M�������<BR>
     * 7�F�����M<BR>
     * 8�F�����ȗ�<BR>
     * 9�F�����G���[<BR>
     */
    public String transactionDiv;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     */
    public String managementCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderDate;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (���[�����M����)<BR>
     * �h�d�q���[�����M���� is null�h�����������ɒǉ�����ꍇ�ɂ�true���A<BR>
     * �����łȂ��ꍇ��false��ݒ肷��B<BR>
     */
    public boolean sendMailDateFlag;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �O�������\�[�g�L�[�I�u�W�F�N�g�̔z��<BR>
     */
    public WEB3ForeignSortKey[] sortKeys;
       
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR> 
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s�� <BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�����敪�`�F�b�N<BR>
     * �@@this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�|�P�jthis.�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E0�F�����҂�<BR>
     * �@@�@@�@@�E1�F�����ς�<BR>
     * �@@�@@�@@�E2�F�đ��M�҂�<BR>
     * �@@�@@  �E6�F���M�������<BR>
     * �@@�@@�@@�E7�F�����M<BR>  
     * �@@�@@�@@�E8�F�����ȗ� <BR>
     * �@@�@@�@@�E9�F�����G���[<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01250<BR> 
     * <BR>
     * �Q�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ<BR> 
     * �@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR> 
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00231<BR> 
     * <BR>  
     * �@@�Q�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ<BR>
     * �@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR> 
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���<BR> 
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR> 
     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>  
     * <BR> 
     * �@@�@@�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR>  
     * �@@�@@�@@�ݒ肳��Ă�����A<BR> 
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>  
     * �@@�@@�@@�E�u�^�p�R�[�h�v<BR> 
     * �@@�@@�@@�E�u���X�R�[�h�v<BR> 
     * �@@�@@�@@�E�u�ڋq�R�[�h�v<BR> 
     * �@@�@@�@@�E�u�����敪�v<BR> 
     * �@@�@@�@@�E�u�쐬���t�v<BR> 
     * �@@�@@�@@�E�u�X�V���t�v<BR> 
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�j�v���y�[�W�ԍ��`�F�b�N<BR>  
     * �@@�R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR> 
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00089<BR> 
     * <BR> 
     * �@@�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00090<BR> 
     * <BR> 
     * �@@�R�|�R�jthis.�v���y�[�W�ԍ� �� 0�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00616<BR> 
     * <BR> 
     *  �S�j�y�[�W���\���s���`�F�b�N<BR>  
     *  �@@�S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR> 
     *  �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     *  �@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@tag:   BUSINESS_ERROR_02224<BR> 
     * <BR> 
     *  �@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR> 
     *  �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     *  �@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@tag:   BUSINESS_ERROR_00092<BR> 
     * <BR> 
     *  �@@�S�|�R�jthis.�y�[�W���\���s�� �� 0�ł������ꍇ�A<BR> 
     *  �@@�@@�@@�@@�u�y�[�W���\���s�����}�C�i�X�l�v�̗�O���X���[����B<BR>
     *  �@@�@@class: WEB3BusinessLayerException<BR>
     *  �@@�@@tag:   BUSINESS_ERROR_00617<BR> 
     *  @@throws WEB3BaseException
     */  
    
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // this.�����敪 != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
        // �P�|�P�jthis.�����敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
        //�@@�@@�u�����敪������`�̒l�v�̗�O���X���[����B 
        //�@@�@@�E0�F�����҂� 
        //�@@�@@�E1�F�����ς� 
        //�@@�@@�E2�F�đ��M�҂�
        //�@@�@@�E6�F���M������� 
        //�@@�@@�E7�F�����M 
        //�@@�@@�E8�F�����ȗ� 
        // �@@ �E9�F�����G���[
        if(this.transactionDiv != null)
        {
            String l_strTodo = SleSendqProcStatusEnum.TODO.intValue() + "";
            String l_strProcessed = SleSendqProcStatusEnum.PROCESSED.intValue() + "";
            String l_strBatProced = SleSendqProcStatusEnum.BAT_PROCED.intValue() + "";
            String l_strCanNotProcessed = SleSendqProcStatusEnum.PREPARE_PROCESSED.intValue() + "";
            String l_strNotProcessed = SleSendqProcStatusEnum.NOT_PROCESSED.intValue() + "";
            String l_strSkipProcessingLocal = SleSendqProcStatusEnum.SKIP_PROCESSING_LOCAL.intValue() + "";
            String l_strSkipProcessedError = SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR.intValue() + "";

            if ((!l_strTodo.equals(this.transactionDiv))
                &&(!l_strProcessed.equals(this.transactionDiv))
                &&(!l_strBatProced.equals(this.transactionDiv))
                &&(!l_strCanNotProcessed.equals(this.transactionDiv))
                &&(!l_strNotProcessed.equals(this.transactionDiv))
                &&(!l_strSkipProcessingLocal.equals(this.transactionDiv))
                &&(!l_strSkipProcessedError.equals(this.transactionDiv)))
            {  
                log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                   this.getClass().getName() + STR_METHOD_NAME,
                    "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B" + this.transactionDiv);
            }
        }
        
        // �Q�j�@@�\�[�g�L�[�`�F�b�N 
        // �Q�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ 
        //�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if(this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B" + this.sortKeys);      
        }
            
        // �Q�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ 
        //�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        if(this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[.�v�f����0�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[.�v�f����0�B" + this.sortKeys.length); 
        }
        
        // �Q�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��� 
        // �@@���L�̃`�F�b�N���s���B 
        //   �Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B 
        //
        //   �Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO�� 
        //     �ݒ肳��Ă�����A 
        //    �u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B 
        //    �@@�E�u�^�p�R�[�h�v 
        //    �@@�E�u���X�R�[�h�v 
        //  �@@�@@�E�u�ڋq�R�[�h�v 
        //    �@@�E�u�����敪�v 
        //    �@@�E�u�쐬���t�v 
        //    �@@�E�u�X�V���t�v
        int l_intSortKeysLength = 0;
        l_intSortKeysLength = this.sortKeys.length;
        
        for(int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();           
            if((!WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.TRANSACTION_DIV.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.CREATED_TIMESTAMP.equals(this.sortKeys[i].keyItem))
                &&(!WEB3FeqSortKeyItemNameDef.LAST_UPDATED_TIMESTAMP.equals(this.sortKeys[i].keyItem)))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B" + this.sortKeys[i].keyItem); 
            }
        }
    
        // �R�j�v���y�[�W�ԍ��`�F�b�N 
        //   �R�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A 
        //     �u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if(this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B" + this.pageIndex); 
        }
        
        //�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A 
        //�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if(!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�B" + this.pageIndex); 
        }
        
        //�R�|�R�jthis.�v���y�[�W�ԍ� �� 0�ł������ꍇ�A 
        //�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
        if(Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ���0�ȉ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ���0�ȉ��B" + this.pageIndex); 
        }
        
        // �S�j�y�[�W���\���s���`�F�b�N 
        // �S�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A 
        //�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if(this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B" + this.pageSize); 
        }
        
        //�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A 
        //�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if(!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�B" + this.pageSize); 
        }
        
        //�S�|�R�jthis.�y�[�W���\���s�� �� 0�ł������ꍇ�A 
        //�@@�@@�u�y�[�W���\���s�����}�C�i�X�l�v�̗�O���X���[����B
        if(Integer.parseInt(this.pageSize) < 0)
        {
            log.debug("�y�[�W���\���s�����}�C�i�X�l�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s�����}�C�i�X�l�B" + this.pageSize); 
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
        return new WEB3AdminFeqSendQueueReferenceResponse(this);
    }
}
@
