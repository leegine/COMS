head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�ֈꗗ���N�G�X�g(WEB3AioSecurityTransferListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.define.WEB3AioMessageCommodityDef;
import webbroker3.aio.define.WEB3AioTransferSortkeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�،��U�ֈꗗ���N�G�X�g)<BR>
 * �،��U�ֈꗗ���N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_list";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071004L;     
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferListRequest.class);
    
    /**
     * (���i�^�C�v)<BR>
     * �����p�̏����ƂȂ�����̏��i�^�C�v<BR>
     * �����R�[�h�w�薳���̏ꍇ�́Anull<BR>
     * <BR>
     * 1�F ����<BR>
     * 2�F ��<BR>
     * 3�F �����M��<BR>
     */
    public String instrumentsType;
    
    /**
     * (�����R�[�h)<BR>
     * �����p�̏����ƂȂ�����R�[�h<BR>
     * �w�薳���̏ꍇ�́Anull
     */
    public String productCode;
    
    /**
     * (�a��敪)<BR>
     * �����p�̏����ƂȂ�a��敪<BR>
     * <BR>
     * 0�F �w�薳��<BR>
     * 1�F �ی�<BR>
     * 2�F ��p
     */
    public String depositDiv;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\���������y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���ɕ\������s��
     */
    public String pageSize;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �،��U�փ\�[�g�L�[�I�u�W�F�N�g�̔z��
     */
    public WEB3AioSecurityTransferSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 41B031A0034B
     */
    public WEB3AioSecurityTransferListRequest() 
    {

    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���i�^�C�v<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.���i�^�C�v != (null, 1, 2, 3)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01295<BR>
     * <BR>
     * �Q�j�����R�[�h<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�����R�[�h != null and<BR>
     *   ���N�G�X�g�f�[�^.�����R�[�h != ���p����<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00815<BR>
     * <BR>
     * �R�j�a��敪<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�a��敪 != (0, 1, 2)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01297<BR>
     * <BR>
     * �S�j�v���y�[�W�ԍ�<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null or <BR>
     *   ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0 or<BR>
     *   ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� != ���p���� <BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00089<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00616<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �T�j�y�[�W���\���s��<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�y�[�W���\���s�� = null or <BR>
     *   ���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0 or<BR>
     *   ���N�G�X�g�f�[�^.�y�[�W���\���s�� != ���p���� <BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00091<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �U�j�\�[�g�L�[<BR>
     *  <BR>
     * �U�|�P�j<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�\�[�g�L�[ = null<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �U�|�Q�j<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�\�[�g�L�[�̗v�f�� = 0<BR>
     *  <BR>
     *   �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �U�|�R�j�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B <BR>
     * <BR>
     * �U�|�R�|�P�j<BR>
     * <BR>
     *   �\�[�g�L�[.�L�[���� = null<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * �U�|�R�|�Q�j<BR>
     * <BR>
     *   �\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l���������ꍇ�A��O���X���[����B<BR> 
     * <BR>
     *     �E���i�^�C�v<BR>
     *     �E�����R�[�h<BR>
     *     �E�����敪<BR>
     *     �E����<BR>
     *     �E�]���z<BR>
     *     �E�a��敪<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �U�|�R�|�R�j<BR>
     * <BR>
     *   �\�[�g�L�[.�����^�~�� = null<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00087<BR>
     * <BR>
     * �U�|�R�|�S�j<BR>
     * <BR>
     *   �\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *     �E�hA�F�����h <BR>
     *     �E�hD�F�~���h 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4149458F02DF
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()"; 
        log.entering(l_strMethodName);
        
        //�P�j���i�^�C�v
        //  ���N�G�X�g�f�[�^.���i�^�C�v != (null, 1, 2, 3)
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01295
        if (!(this.instrumentsType == null || 
            WEB3AioMessageCommodityDef.EQUITY.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.MUTUAL_FUND.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.BOND.equals(this.instrumentsType))) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01295,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.���i�^�C�v != (null, 1, 2, 3)," +
                "���N�G�X�g�f�[�^.���i�^�C�v = " + this.instrumentsType);               
        }
       
        //�Q�j�����R�[�h
        //  ���N�G�X�g�f�[�^.�����R�[�h != null and
        //  ���N�G�X�g�f�[�^.�����R�[�h != ���p����
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00815
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode) && 
            (!WEB3StringTypeUtility.isNumber(this.productCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�����R�[�h != null and " +
                "���N�G�X�g�f�[�^.�����R�[�h != ���p����," +
                "���N�G�X�g�f�[�^.�����R�[�h = " + this.productCode);                    
        }
        
        //�R�j�a��敪
        //  ���N�G�X�g�f�[�^.�a��敪 != (0, 1, 2)
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01297
        if (!(WEB3AioDepositTypeDivDef.DEFAULT.equals(this.depositDiv) ||
            WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(this.depositDiv) ||
            WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(this.depositDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01297,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�a��敪 != (0, 1, 2)," +
                "���N�G�X�g�f�[�^.�a��敪 = " + this.depositDiv);                    
            
        }
        
        //�S�j�v���y�[�W�ԍ�
        //  ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null or 
        //  ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0 or
        //  ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� != ���p���� 
        //  �̏ꍇ�A��O���X���[����B 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00089
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00616
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00090
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = null");             
        }        
        else if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + l_strMethodName,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł���, " +
                "�v���y�[�W�ԍ� = " + this.pageIndex);            
        }
        else if (Double.parseDouble(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� <= 0," +
                "���N�G�X�g�f�[�^.�v���y�[�W�ԍ� = " + this.pageIndex);             
        }
        
        //�T�j�y�[�W���\���s��
        //  ���N�G�X�g�f�[�^.�y�[�W���\���s�� = null or 
        //  ���N�G�X�g�f�[�^.�y�[�W���\���s�� <= 0 or
        //  ���N�G�X�g�f�[�^.�y�[�W���\���s�� != ���p���� 
        //  �̏ꍇ�A��O���X���[����B 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00091
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00092
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�y�[�W���\���s��, " +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = " + this.pageSize);             
        }
        else if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + l_strMethodName,
                "�y�[�W���\���s�� != ���p����," +
                "�y�[�W���\���s�� = " + this.pageSize);             
        }
        else if (Double.parseDouble(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�y�[�W���\���s��, " +
                "���N�G�X�g�f�[�^.�y�[�W���\���s�� = " + this.pageSize);   
        }

        //�U�j�\�[�g�L�[
        //�U�|�P�j
        //  ���N�G�X�g�f�[�^.�\�[�g�L�[ = null
        //  �̏ꍇ�A��O���X���[����B 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00231
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�\�[�g�L�[ = null");                  
        }

        //�U�|�Q�j
        //  ���N�G�X�g�f�[�^.�\�[�g�L�[�̗v�f�� = 0
        //  �̏ꍇ�A��O���X���[����B 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�\�[�g�L�[�̗v�f�� = 0");                  
            
        }

        //�U�|�R�j�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B         
        //�U�|�R�|�P�j
        //  �\�[�g�L�[.�L�[���� = null
        //  �̏ꍇ�A��O���X���[����B 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00085
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + l_strMethodName,
                    "�\�[�g�L�[.�L�[����[" + i + "] = null");    
            }            
        }
        
        //�U�|�R�|�Q�j
        //  �\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l���������ꍇ�A��O���X���[����B 
        //���i�^�C�v
        //�����R�[�h
        //�����敪
        //����
        //�]���z
        //�a��敪
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00086
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (!(WEB3AioTransferSortkeyDef.INSTRUMENTS_TYPE.equals(this.sortKeys[i].keyItem) || 
                WEB3AioTransferSortkeyDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.TAX_TYPE.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.CHANGE_POSS_QUANTITY.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.MARKET_VALUE.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.DEPOSIT_DIV.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + l_strMethodName,
                    "�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ�:" +
                    " [���i�^�C�v], [�����R�[�h], [�����敪], [����], [�]���z], [�a��敪]" +
                    "�̍��ږ��ȊO�̒l���������ꍇ, �\�[�g�L�[.�L�[����[" + i + "] = "
                    + this.sortKeys[i].keyItem);                        
            }
        }
                        
        //�U�|�R�|�R�j
        //  �\�[�g�L�[.�����^�~�� = null
        //  �̏ꍇ�A��O���X���[����B 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00087
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + l_strMethodName,
                    "�\�[�g�L�[.�����^�~�� = null");                       
            }
        }

        //�U�|�R�|�S�j
        //  �\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //    �E�hA�F�����h 
        //    �E�hD�F�~���h 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00088 
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) || 
                WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + l_strMethodName,
                    "�\�[�g�L�[.�����^�~�����ȉ�: [A�F����], [D�F�~��]�̒l�ȊO," +
                    "�\�[�g�L�[.�����^�~��[" + i + "] = " + this.sortKeys[i].ascDesc);                       
            }
        }               
        
        log.exiting(l_strMethodName);
    }

    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �،��U�ֈꗗ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioSecurityTransferListResponse(this);
    }
}
@
