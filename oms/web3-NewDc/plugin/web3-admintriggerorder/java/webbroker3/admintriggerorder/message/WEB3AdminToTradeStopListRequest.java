head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�戵��~�ꗗ���N�G�X�g(WEB3AdminToTradeStopListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 ���q��(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTradeStopKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�戵��~�ꗗ���N�G�X�g)<BR>
 * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ���N�G�X�g�N���X<BR>
 * 
 * @@author ���q��
 * @@version 1.0 
 */
public class WEB3AdminToTradeStopListRequest extends WEB3AdminToCommonRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_list";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 1�F�@@���i<BR>
     * 2�F�@@�s��<BR>
     * 3�F�@@����<BR>
     */
    public String tradeStopDiv;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode = null;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ� <BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     * ���y�[�W���O���s���K�v������ꍇ�̂݃Z�b�g�B<BR>
     */
    public String pageIndex = null;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s�� <BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     * ���y�[�W���O���s���K�v������ꍇ�̂݃Z�b�g�B<BR>
     */
    public String pageSize = null;
    
    /**
     * (�\�[�g�L�[)<BR>
     */
    public WEB3AdminToTradeStopSortKey[] sortKeys;
    
    /**
     * @@roseuid 4430D2C000AB
     */
    public WEB3AdminToTradeStopListRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����敪�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�����敪 == null�̏ꍇ�A<BR>
     * �@@�@@�u�����敪�������́v�̗�O���X���[����B<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_01249<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�����敪�Ɉȉ��̒l�ȊO���ݒ肳��Ă���<BR>
     * �@@�@@�ꍇ�A�u�����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E"���i"<BR>
     * �@@�@@�@@�E"�s��"<BR>
     * �@@�@@�@@�E"����"<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_01250<BR>
     * <BR>
     * �R�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�R�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�E�����R�[�h != ����<BR>
     * �@@�@@�E�����R�[�h.length != 5<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �S�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@this.�\�[�g�L�[ != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������s���B<BR>
     * �@@�@@�S�|�P�|�P�j�@@�\�[�g�L�[.�L�[���ڂɈȉ��̒l�ȊO��<BR>
     * �@@�@@�@@�ݒ肳��Ă����ꍇ�A<BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E"�����R�[�h"<BR>
     * �@@�@@�@@�@@�E"�L������From"<BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * �@@�@@�S�|�P�|�Q�j�@@�\�[�g�L�[.validate()���\�b�h���R�[������B<BR>
     * <BR>
     * �T�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@this.�v���y�[�W�ԍ� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T�|�P�j�@@this.�v���y�[�W�ԍ� != �����̏ꍇ�A <BR>
     * �@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * �U�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@this.�y�[�W���\���s�� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�U�|�P�j�@@this.�y�[�W���\���s�� != �����̏ꍇ�A <BR>
     * �@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00092<BR>
     * �@@ <BR>
     * �@@�U�|�Q�j�@@this.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B <BR>
     *      class : WEB3BusinessLayerException<BR>
     *      tag : BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406B75701B6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@super.validate()���R�[������B
        super.validate();
        
        // �Q�j�@@�����敪�`�F�b�N
        //�@@�Q�|�P�j�@@this.�����敪 == null�̏ꍇ�A
        //�@@�@@�u�����敪�������́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.tradeStopDiv))
        {
            log.debug("�����敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�����w��ł��B");
        }      

        //�@@�Q�|�Q�j�@@this.�����敪�Ɉȉ��̒l�ȊO���ݒ肳��Ă���
        //�@@�@@�ꍇ�A�u�����敪������`�̒l�v�̗�O���X���[����B
        //�@@�@@�@@�E"���i"
        //�@@�@@�@@�E"�s��"
        //�@@�@@�@@�E"����"
        if (!WEB3TargetTypeDef.COMMODITY.equals(this.tradeStopDiv)
            && !WEB3TargetTypeDef.MARKET.equals(this.tradeStopDiv)
            && !WEB3TargetTypeDef.PRODUCT.equals(this.tradeStopDiv))
        {
            log.debug("�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }  
        
        // �R�j�@@�����R�[�h�`�F�b�N
        //�@@this.�����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�R�|�P�j�@@this.�����R�[�h���ȉ��̏����ɊY������ꍇ�A
        // �@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B
        // �@@�@@�E�����R�[�h != ����
        //�@@�@@ �E�����R�[�h.length != 5
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode)
            && (!WEB3StringTypeUtility.isDigit(this.productCode) 
                || this.productCode.length() != 5))
        {
            log.debug("�����R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�̓��͂��s���ł��B");
        } 
        
        // �S�j�@@�\�[�g�L�[�`�F�b�N
        //�@@this.�\�[�g�L�[ != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (this.sortKeys != null && this.sortKeys.length > 0)
        {
            //�@@�S�|�P�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������s���B
            int l_intSortKeysLen = this.sortKeys.length;
            for (int i = 0; i < l_intSortKeysLen; i++)
            {
                //�@@�@@�S�|�P�|�P�j�@@�\�[�g�L�[.�L�[���ڂɈȉ��̒l�ȊO��
                //�@@�@@�@@�ݒ肳��Ă����ꍇ�A
                //�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
                //�@@�@@�@@�@@�E"�����R�[�h"
                //�@@�@@�@@�@@�E"�L������From"
                if (this.sortKeys[i] != null
                    && !WEB3AdminToTradeStopKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem) 
                    && !WEB3AdminToTradeStopKeyItemDef.EXPIRATION_START_DATE.equals(this.sortKeys[i].keyItem))  
                {
                    log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                }
                
                //�@@�@@�S�|�P�|�Q�j�@@�\�[�g�L�[.validate()���\�b�h���R�[������B
                this.sortKeys[i].validate();
            }            
        }
        
        // �T�j�@@�v���y�[�W�ԍ��`�F�b�N 
        //�@@this.�v���y�[�W�ԍ� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.pageIndex))
        {
            //�@@�T�|�P�j�@@this.�v���y�[�W�ԍ� != �����̏ꍇ�A 
            //�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
            {
                log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            }
    
            //�@@�T�|�Q�j�@@this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A 
            //�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
            if (Integer.parseInt(this.pageIndex) <= 0)
            {
                log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            }            
        }
        
        // �U�j�@@�y�[�W���\���s���`�F�b�N 
        //�@@this.�y�[�W���\���s�� != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (WEB3StringTypeUtility.isNotEmpty(this.pageSize))
        {
            //�@@�U�|�P�j�@@this.�y�[�W���\���s�� != �����̏ꍇ�A 
            //�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B 
            if (!WEB3StringTypeUtility.isInteger(this.pageSize))
            {
                log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�y�[�W���\���s���������ȊO�̒l�ł��B");
            }
            
            //�@@�U�|�Q�j�@@this.�y�[�W���\���s�� <= 0�ł������ꍇ�A 
            //�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B 
            if (Integer.parseInt(this.pageSize) <= 0)
            {
                log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�y�[�W���\���s���̒l��0�ȉ��ł��B");
            }    
        }
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (createResponse�̎���)<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�戵��~�ꗗ���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopListResponse(this);
    }
}@
