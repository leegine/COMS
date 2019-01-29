head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔����������߰ݓo�^�ڋq�Ɖ�ظ���(WEB3AdminAccInfoCampaignRegistAccListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  ꎉ�(���u) �V�K�쐬
Revision History : 2007/2/1  ���f��No.165
Revision History : 2007/2/3  ���f��No.178
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���萔����������߰ݓo�^�ڋq�Ɖ�ظ���)<BR>
 * �Ǘ��҂��q�l��� �萔�������L�����y�[���o�^�ڋq�Ɖ�N�G�X�g<BR>
 * <BR>
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignRegistAccListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignRegistAccList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312045L;
    
    /**
     * (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * <BR>
     * 10 �F ��ꊔ��<BR>
     * 11 �F JASDAQ<BR>
     * 12 �F �~�j���� <BR>
     * 30 �F �� <BR>
     * 31 �F ���i�X���j <BR>
     * 40 �F �O������ <BR>
     * 50 �F �敨 <BR>
     * 51 �F �����w���n�o<BR>
     */
    public String itemCode;
    
    /**
     * (�L�����y�[������)<BR>
     * �L�����y�[������<BR>
     */
    public String campaignName;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (���҃R�[�h)<BR>
     * ���҃R�[�h<BR>
     */
    public String traderCode;
    
    /**
     * (�����J�݋敪)<BR>
     * �����J�݋敪<BR>
     * <BR>
     * 1 �F ��������<BR>
     * 2 �F �M�p����<BR>
     * 3 �F �敨OP����<BR>
     * 4 �F FX����<BR>
     * 5 �F ����������<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String collectRate;
    
    /**
     * (�Ώۓ�)<BR>
     * �Ώۓ�<BR>
     */
    public Date targetDate;
    
    /**
     * (�o�^�^�C�v)<BR>
     * �o�^�^�C�v<BR>
     * <BR>
     * 0 �F �����J�ݏ����w��<BR>
     * 1 �F �ʌڋq�w��<BR>
     * 2 �F �����ʌڋq�w��<BR>
     */
    public String registType;
    
    /**
     * (�L���敪)<BR>
     * �L���敪<BR>
     * <BR>
     * 0 �F ����<BR>
     * 1 �F �L��<BR>
     */
    public String activeDiv;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
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
     * @@roseuid 45C08762018B
     */
    public WEB3AdminAccInfoCampaignRegistAccListRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignRegistAccListResponse(this);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�u�L�����y�[�����́v�`�F�b�N<BR>
     * �@@�@@�E�L�����y�[������ != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �P-�P�j�L�����y�[������101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02709<BR>
     * <BR>
     * <BR>
     * �Q�j�u���X�R�[�h�v�`�F�b�N<BR>
     * �@@�@@�E���X�R�[�h != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �Q-�P�j���X�R�[�h��3���ȊO�̏ꍇ�A�w���X�R�[�h�����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     * <BR>
     * �R�j�u�ڋq�R�[�h�v�`�F�b�N<BR>
     * �@@�@@�E�ڋq�R�[�h != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �R-�P�j�ڋq�R�[�h��7���ȏ�̏ꍇ�A�w�ڋq�R�[�h�����G���[�x��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00836<BR>
     * <BR>
     * <BR>
     * �S�j�u���҃R�[�h�v�`�F�b�N<BR>
     * �@@�@@�E���҃R�[�h != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �S-�P�j���҃R�[�h��6���ȏ�̏ꍇ�A�w���҃R�[�h�����G���[�x��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_01912<BR>
     * <BR>
     * <BR>
     * �T�j�u�������v�`�F�b�N<BR>
     * �@@�@@�E������ != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �T-�P�j�������� 0�`100 �̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02082<BR>
     * <BR>
     * �U�j�u�v���y�[�W�ԍ��v�`�F�b�N<BR>
     * �U-�P�j�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B<BR>
     * <BR>
     * �U-�Q�j�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �U-�Q�j�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00616<BR>
     * <BR>
     * <BR>
     * �V�j�u�y�[�W���\���s���v�`�F�b�N<BR>
     * �V-�P�j�����͂̏ꍇ�A��O���X���[����B  <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02224<BR>
     * <BR>
     * �V-�Q�j�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B  <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00092<BR>
     * <BR>
     * �V-�R�j�}�C�i�X�l�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * �W�j�u�\�[�g�L�[�v�`�F�b�N<BR>
     * �W-�P�j�\�[�g�L�[ = null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �W-�Q�j�\�[�g�L�[�̗v�f�� = 0 �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �W-�R�j�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * �@@�@@�W-�R-�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * �@@�@@�W-�R-�Q�j�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E���҃R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E�Ώۊ���From<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E�Ώۊ���To<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00086<BR>
     * @@roseuid 45A5CE770240
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�u�L�����y�[�����́v�`�F�b�N 
        //�@@�@@�E�L�����y�[������ != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B 
        if (this.campaignName != null)
        {
            //�P-�P�j�L�����y�[������101�o�C�g�ȏ�̏ꍇ�A�w�L�����y�[�����̌����G���[�x��O���X���[����B  
            if (this.campaignName.getBytes().length >= 101)
            {
                log.debug("�L�����y�[�����̌����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�L�����y�[�����̌����G���[�B");
            }
        }
        
        //�Q�j�u���X�R�[�h�v�`�F�b�N 
        //�@@�@@�E���X�R�[�h != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B 
        if (this.branchCode != null)
        {
            //�Q-�P�j���X�R�[�h��3���ȊO�̏ꍇ�A�w���X�R�[�h�����G���[�x��O���X���[����B 
            if (this.branchCode.length() != 3)
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
        }
        
        //�R�j�u�ڋq�R�[�h�v�`�F�b�N 
        //�@@�@@�E�ڋq�R�[�h != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B 
        if (this.accountCode != null)
        {
            //�R-�P�j�ڋq�R�[�h��7���ȏ�̏ꍇ�A�w�ڋq�R�[�h�����G���[�x��O���X���[����B  
            if (this.accountCode.length() >= 7)
            {
                log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            }    
        }
        
        //�S�j�u���҃R�[�h�v�`�F�b�N 
        //�@@�@@�E���҃R�[�h != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B 
        if (this.traderCode != null)
        {
            //�S-�P�j���҃R�[�h��6���ȏ�̏ꍇ�A�w���҃R�[�h�����G���[�x��O���X���[����B 
            if (this.traderCode.length() >= 6)
            {
                log.debug("���҃R�[�h�i������j�̒������s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���҃R�[�h�i������j�̒������s���ł��B");
            }
        }

        //�T�j�u�������v�`�F�b�N 
        //�@@�@@�E������ != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B 
        if (this.collectRate != null)
        {
            //�T-�P�j�������� 0�`100 �̐����ȊO�̏ꍇ�A�w�������G���[�x��O���X���[����B
            if (WEB3StringTypeUtility.isInteger(this.collectRate))
            {
                String l_strCollectRate = this.collectRate;
                int l_intCollectRate = Integer.parseInt(l_strCollectRate);
                if (l_intCollectRate > 100 || l_intCollectRate < 0)
                {
                    log.debug("�������̒l���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�������̒l���s���ł��B");  
                }
            }
            else
            {
                log.debug("�������̒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�������̒l���s���ł��B"); 
            }
        }
        
        //�U�j�u�v���y�[�W�ԍ��v�`�F�b�N 
        //�U-�P�j�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (this.pageIndex == null)
        {
            this.pageIndex = "1";
        }
        
        //�U-�Q�j�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B 
        if (this.pageIndex != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
            {
                log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");  
            }
        }

        //�U-�Q�j�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        if (this.pageIndex != null)
        {
            if (WEB3StringTypeUtility.isMinus(this.pageIndex))
            {
                log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");  
            }
        }

        //�V�j�u�y�[�W���\���s���v�`�F�b�N 
        //�V-�P�j�����͂̏ꍇ�A��O���X���[����B  
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");   
        }
        
        //�V-�Q�j�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B   
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");   
        }
        
        //�V-�R�j�}�C�i�X�l�̏ꍇ�A��O���X���[����B  
        if (WEB3StringTypeUtility.isMinus(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");  
        }
        
        //�W�j�u�\�[�g�L�[�v�`�F�b�N 
        //�W-�P�j�\�[�g�L�[ = null �̏ꍇ�A��O���X���[����B 
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");  
        }
        
        //�W-�Q�j�\�[�g�L�[�̗v�f�� = 0 �̏ꍇ�A��O���X���[����B 
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName()  + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");  
        }
        
        //�W-�R�j�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B 
        //�@@�@@�W-�R-�P�j�\�[�g�L�[.validate()���R�[������B 
        int l_intSortKeysLength = this.sortKeys.length;
        
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
            
            //�@@�@@�W-�R-�Q�j�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A��O���X���[����B 
            //�@@�E���X�R�[�h 
            //�@@�E�ڋq�R�[�h 
            //�@@�E���҃R�[�h 
            //�@@�E������ 
            //�@@�E�Ώۊ���From  
            //�@@�E�Ώۊ���To  
            if (this.sortKeys[i].keyItem != null)
            {
                if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.TRADER_CODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.COLLECT_RATE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.TARGETPERIOD_FROM.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.TARGETPERIOD_TO.equals(this.sortKeys[i].keyItem))
                {
                    log.debug("�u�\�[�g�L�[�v = " + this.sortKeys);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
