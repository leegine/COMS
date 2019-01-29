head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���
                       (WEB3AdminAccInfoCampaignAccOpenListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 �Ј��� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenListRequest extends WEB3GenRequest 
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312035L;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �L�����y�[������<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;
    
    /**
     * �\�[�g�L�[
     */
    public WEB3AccInfoSortKey sortKeys[];
    
    /**
     * �L�����y�[����������
     */
    public WEB3AccInfoCampaignSearchCondition campaignSearchItem;
    
    /**
     * @@roseuid 45C08760019B
     */
    public WEB3AdminAccInfoCampaignAccOpenListRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignAccOpenListResponse(this);
    }
        
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j �L�����y�[�����̂̃`�F�b�N<BR>
     *   �P-�P�j �L�����y�[������101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02709<BR>
     * <BR>
     * �Q�j �������̃`�F�b�N<BR>
     *   �Q-�P�j �������� 0 �` 100 �̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02082<BR>
     * <BR>
     * �R�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00090<BR>
     * �@@�R�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00616<BR>
     * <BR>
     * �S�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02224<BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00092<BR>
     * �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00617<BR>
     * <BR>
     * �T�j�@@�\�[�g�L�[�̃`�F�b�N  <BR>
     * �@@�T�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00231<BR>
     * �@@�T�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00232<BR>
     * �@@�T�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B  <BR>
     * �@@�@@�@@�T�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�T�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E���҃R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�����J�ݓ�From<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�����J�ݓ�To<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�o�^��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�X�V��<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00086<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 45A7568B034D
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j �L�����y�[�����̂̃`�F�b�N
        //    �P-�P�j �L�����y�[������101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B
        if (this.campaignSearchItem.campaignName != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.campaignSearchItem.campaignName) >= 101)
            {
                log.debug("�L�����y�[�����̌����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �L�����y�[������ = " + this.campaignSearchItem.campaignName);
            }
        }
        
        //�Q�j �������̃`�F�b�N
        //  �Q-�P�j �������� 0 �` 100 �̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B
        if (this.campaignSearchItem.collectRate != null)
        {
            if (WEB3StringTypeUtility.isInteger(this.campaignSearchItem.collectRate))
            {
                if (Integer.parseInt(this.campaignSearchItem.collectRate) > 100 
                        || Integer.parseInt(this.campaignSearchItem.collectRate) < 0)
                {
                    log.debug("�������̒l���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ������ = " + this.campaignSearchItem.collectRate);
                }
            }
            else
            {
                log.debug("�������̒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " ������ = " + this.campaignSearchItem.collectRate);
            }
        }
        
        //�R�j�@@�v���y�[�W�ԍ��`�F�b�N 
        //  �R�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        
        //  �R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                " �v���y�[�W�ԍ� = " + this.pageIndex);
        }
        
        //  �R�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isMinus(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                " �v���y�[�W�ԍ� = " + this.pageIndex);
        }
        
        //�S�j�@@�y�[�W���\���s���`�F�b�N 
        //  �S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s�� = " + this.pageSize);
        }
        
        //  �S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s�� = " + this.pageSize);
        }
        
        //  �S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isMinus(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s�� = " + this.pageSize);
        }
        
        //�T�j�@@�\�[�g�L�[�̃`�F�b�N  
        //  �T�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[ = " + this.sortKeys);
        }
        
        //  �T�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[ = " + this.sortKeys);
        }
        
        //  �T�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B  
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i ++)
        {
            WEB3AccInfoSortKey l_key = sortKeys[i];
            if (l_key != null)
            {
                //  �T�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B 
                l_key.validate();
                
                //  �T�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
                //              �E������                              
                //              �E���X�R�[�h
                //              �E���҃R�[�h
                //              �E�����J�ݓ�From
                //              �E�����J�ݓ�To
                //              �E�o�^��
                //              �E�X�V��
                if (!(WEB3AccInfoKeyItemDef.COLLECT_RATE.equals(l_key.keyItem) 
                        || WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.TRADER_CODE.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_FROM.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_TO.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.REGIST_DATE.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_key.keyItem)))
                {
                    log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " �\�[�g�L�[.�L�[���� = " + l_key.keyItem);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
