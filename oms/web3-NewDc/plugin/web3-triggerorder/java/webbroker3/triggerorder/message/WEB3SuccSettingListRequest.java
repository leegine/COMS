head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSettingListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���ݒ�ꗗ���N�G�X�g(WEB3SuccSettingListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.triggerorder.define.WEB3ToSuccKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�A���ݒ�ꗗ���N�G�X�g)<BR>
 * �A���ݒ�ꗗ���N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccSettingListRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccSettingListRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_settingList";
   
    /**
     * (���i�敪�ꗗ)<BR>
     * �ȉ��̏��i�敪�̔z��<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     */
    public String[] commodityTypeList;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3SuccSortKey[] sortKeys;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w��@@<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 434896030290
     */
    public WEB3SuccSettingListRequest() 
    {
     
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���i�敪�ꗗ�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.���i�敪�ꗗ�������͂̏ꍇ�A<BR>
     * �@@�@@�u���i�敪�ꗗ�������́v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_01462<BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.���i�敪�ꗗ�̗v�f���Ɉȉ��̒l�ȊO��<BR>
     * �@@�@@�܂܂��ꍇ�A�u����`�̏��i�敪�����݁v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_01068<BR>
     * �@@�@@�@@"��������"<BR>
     * �@@�@@�@@"�M�p���"<BR>
     * �@@�@@�@@"�敨"<BR>
     * �@@�@@�@@"�I�v�V����"<BR>
     * <BR>
     * �Q�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�\�[�g�L�[�������͂̏ꍇ�A<BR>
     * �@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�\�[�g�L�[[0].�L�[���� != �A����������.���i�敪<BR>
     * �@@�@@�̏ꍇ�A�u���\�[�g�L�[�͏��i�敪�̂ݎw��v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_02241<BR>
     * <BR>
     * �@@�Q�|�R�j�@@this.�\�[�g�L�[�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�Q�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��<BR>
     * �@@�@@�@@�ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00086<BR>
     * �@@�@@�@@�@@�E�A����������.���i�敪<BR>
     * �@@�@@�@@�@@�E�A����������.�����R�[�h<BR>
     * �@@�@@�@@�@@�E�A����������.�s��R�[�h<BR>
     * �@@�@@�@@�@@�E�A����������.�����敪<BR>
     * �@@�@@�@@�@@�E�A����������.����敪<BR>
     * �@@�@@�@@�@@�E�A����������.�ٍϋ敪<BR>
     * �@@�@@�@@�@@�E�A����������.�l�i����<BR>
     * �@@�@@�@@�@@�E�A����������.���s����<BR>
     * �@@�@@�@@�@@�E�A����������.���������敪<BR>
     * �@@�@@�@@�@@�E�A����������.��������<BR>
     * �@@�@@�@@�@@�E�A����������.������<BR>
     * �@@�@@�@@�@@�E�A����������.�����L������<BR>
     * <BR>
     * �R�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�R�|�P�jthis.�v���y�[�W�ԍ��������͂ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�R�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �S�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�S�|�P�jthis.�y�[�W���\���s���������͂ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00092<BR>
     * �@@<BR>
     * �@@�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     *  �@@    class: WEB3BusinessLayerException <BR>
     *  �@@    tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 431D20750087
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@���i�敪�ꗗ�`�F�b�N
        // �@@�P�|�P�j�@@this.���i�敪�ꗗ�������͂̏ꍇ�A
        // �@@�@@�u���i�敪�ꗗ�������́v�̗�O���X���[����B
        if (this.commodityTypeList == null || this.commodityTypeList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01462,
                getClass().getName() + STR_METHOD_NAME,
                " ���i�敪�ꗗ�����w��ł��B");
        }
        //�@@�P�|�Q�j�@@this.���i�敪�ꗗ�̗v�f���Ɉȉ��̒l�ȊO��
        // �@@�@@�܂܂��ꍇ�A�u����`�̏��i�敪�����݁v�̗�O���X���[����B
        for (int i = 0; i < this.commodityTypeList.length; i++)
        {
            String l_strCommodityType = this.commodityTypeList[i];
            
            if (!WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType)
                && !WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType)
                && !WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType)
                && !WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    getClass().getName() + STR_METHOD_NAME,
                    "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        //�Q�j�@@�\�[�g�L�[�`�F�b�N
        // �@@�Q�|�P�j�@@this.�\�[�g�L�[�������͂̏ꍇ�A
        // �@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�����w��ł��B");
        }
        
        //�Q�|�Q�j�@@this.�\�[�g�L�[[0].�L�[���� != �A����������.���i�敪
        //�̏ꍇ�A�u���\�[�g�L�[�͏��i�敪�̂ݎw��v�̗�O���X���[����B
        if (!WEB3ToSuccKeyItemDef.COMMODITY_TYPE.equals(this.sortKeys[0].keyItem))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02241,
                getClass().getName() + STR_METHOD_NAME,
                "���\�[�g�L�[�͏��i�敪�̂ݎw��B");
        }
        
        //�Q�|�R�j�@@this.�\�[�g�L�[�̗v�f�����A�ȉ��̏������J��Ԃ��B        
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //�Q�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
            this.sortKeys[i].validate();
            //�Q�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��
            // �@@�@@�@@�ݒ肳��Ă�����A
            // �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
            if (!WEB3ToSuccKeyItemDef.COMMODITY_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.REPAYMENT_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.PRICE_COND_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.ORDER_COND_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    " �\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
            
        }
        
        //�R�j�@@�v���y�[�W�ԍ��`�F�b�N
        //�@@�R�|�P�jthis.�v���y�[�W�ԍ��������͂ł������ꍇ�A
        // �u�v���y�[�W�ԍ���null�v�̗�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        //�R�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        //�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        //�R�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
        //�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B" + this.pageIndex);
        }
        
        //�S�j�y�[�W���\���s���`�F�b�N
        //�S�|�P�jthis.�y�[�W���\���s���������͂ł������ꍇ�A
        //�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B" + this.pageSize);
        }
        //�S�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        //�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B" + this.pageSize);
        }
        //�S�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        //�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B" + this.pageSize);
        }
        
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccSettingListResponse(this);
    }
}
@
