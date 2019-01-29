head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������(WEB3AdminBondOrderInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.define.WEB3BondTradingTypeDef;


/**
 * (�������)<BR>
 * �������N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondOrderInfo extends Message
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderInfo.class);
    
    /**
     * (�������)<BR>
     * ���̒������<BR>
     * <BR>
     * 401�F���������@@402�F�����蒍��
     */
    public String tradingType;
    
    /**
     * (���)<BR>
     * ���<BR>
     * <BR>
     * 35:��W����@@92:�����d�؎��
     */
    public String dealType;
    
    /**
     * (���ϋ敪)<BR>
     * ����<BR>
     * <BR>
     * 1�F�~�݁@@2�F�O��
     */
    public String settleDiv;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * *��ʂ�'�z�ʋ��z'���ڂɑΉ�����
     */
    public String orderQuantity;
    
    /**
     * (�P��)<BR>
     * �P��
     */
    public String price;
    
    /**
     * (�בփ��[�g)<BR>
     * �בփ��[�g
     */
    public String fxRate;
    
    /**
     * (�ŋ敪)<BR>
     * �ŋ敪
     */
    public String taxType;
    
    /**
     * (�������)<BR>
     * �R���X�g���N�^
     * @@roseuid 44CB0401033C
     */
    public WEB3AdminBondOrderInfo() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P)�@@������ʃ`�F�b�N  
     *  �ithis.������� == �����������@@�܂��́@@this.������� == �����蒍���j<BR>
     *   �łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01403<BR>
     * <BR>
     * �Q)�@@����`�F�b�N <BR>
     * �@@�@@�E�ithis.��� == ��W����@@�܂��́@@this.��� == �����d�؎���j <BR>
     *       �łȂ��ꍇ�A��O���X���[����B
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02642<BR>
     * <BR>
     * �@@�@@this.��� == ��W���<BR>
     * �@@�@@����<BR>
     * �@@�@@this.������� �I= ���������̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02539<BR>
     * <BR>
     * �R)�@@�������ʃ`�F�b�N <BR>
     * �@@this.��������==null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02634<BR>
     * <BR>
     * �@@this.�������ʂ������P�P���ȓ��łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02635<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02641<BR>
     * <BR>
     * �@@this.�������� <= 0�ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02636<BR>
     * <BR>
     * �S)�@@�P���`�F�b�N <BR>
     * �@@this.�P��==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02021<BR>
     * <BR>
     * �@@this.�P���������S���ȓ��i�����S���j�{�����_�{�����U���ȓ��i�����U���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02551<BR>
     * <BR>
     * �@@this.�P�������l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02022<BR>
     * <BR>
     * �@@this.�P�������O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02023<BR>
     * <BR>
     * �T)�@@�בփ��[�g�`�F�b�N <BR>
     * �@@this.�בփ��[�g!=null�̏ꍇ�A�ȉ��̃`�F�b�N������B <BR>
     * �@@this.�בփ��[�g�������R���ȓ��i�����R���j�{�����_�{�����S���ȓ��i�����S���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02037<BR>
     * <BR>
     * �@@this.�בփ��[�g�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02220<BR>
     * <BR>
     * �@@this.�בփ��[�g�����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02196
     * @@throws WEB3BaseException
     * @@roseuid 44BDED360002
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P)�@@������ʃ`�F�b�N  
        //�ithis.������� == �����������@@�܂��́@@this.������� == �����蒍���j 
        //�łȂ��ꍇ�A��O���X���[����B
        if ((!WEB3BondTradingTypeDef.BOND_BUY.equals(this.tradingType)) && 
            (!WEB3BondTradingTypeDef.BOND_SELL.equals(this.tradingType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
        //�Q)�@@����`�F�b�N  
        //�ithis.��� == ��W����@@�܂��́@@this.��� == �����d�؎���j 
        //�łȂ��ꍇ�A��O���X���[����B 
        if ((!WEB3DealTypeDef.RECRUIT_TRADING.equals(this.dealType)) && 
                (!WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING.equals(this.dealType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02642,
                this.getClass().getName() + "."+ STR_METHOD_NAME,
                "����̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
        //this.��� == ��W���
        //����
        //this.������� �I= ���������̏ꍇ�A��O���X���[����B
        
        if ((WEB3DealTypeDef.RECRUIT_TRADING.equals(this.dealType)) && 
            (!WEB3BondTradingTypeDef.BOND_BUY.equals(this.tradingType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02539,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����Ǝ�����s���Ȋ֌W�ł��B");   
        }
        
        //�R)�@@�������ʃ`�F�b�N 
        //this.��������==null�̏ꍇ�A��O���X���[����B 
        if (this.orderQuantity == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02634,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�����w��ł��B");
        }
        
        //this.�������ʂ������P�P���ȓ��łȂ��ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.getIntegerDigits(this.orderQuantity) > 11)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02635,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�̃T�C�Y���s���ł��B");
        }      
        if (!WEB3StringTypeUtility.isInteger(this.orderQuantity))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�������l�ł͂���܂���B");
        }

        //this.�������� <= 0�ꍇ�A��O���X���[����B 
        if ((Long.parseLong(this.orderQuantity) <= 0))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z��0�ȉ��̒l�ł��B");
        }
               
        //�S)�@@�P���`�F�b�N 
        //this.�P��==null�̏ꍇ�A��O���X���[����B
        if (this.price == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P���������͂ł��B");
        }
        
        //this.�P���������S���ȓ��i�����S���j�{�����_�{�����U���ȓ��i�����U���j�łȂ��ꍇ�A��O���X���[����B
        if (((WEB3StringTypeUtility.getIntegerDigits(this.price) > 4) || 
               (WEB3StringTypeUtility.getFractionDigits(this.price) > 6)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P���͐������S���C�������U���͈̔͊O�ł��B");
        }
        
        //this.�P�������l�łȂ��ꍇ�A��O���X���[����B
        if ((!WEB3StringTypeUtility.isNumber(this.price)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P�������l�ȊO�̒l�ł��B");
        }
        
        //this.�P�������O�̏ꍇ�A��O���X���[����B
        if ((Double.parseDouble(this.price) <= 0))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���P����0�ȉ��̒l�ł��B");
        }
        
        //�T)�@@�בփ��[�g�`�F�b�N 
        //this.�בփ��[�g!=null�̏ꍇ�A�ȉ��̃`�F�b�N������B
        if (this.fxRate != null)
        {    
            //this.�בփ��[�g�������R���ȓ��i�����R���j�{�����_�{�����S���ȓ��i�����S���j�łȂ��ꍇ�A��O���X���[����B
            if ((WEB3StringTypeUtility.getIntegerDigits(this.fxRate) > 3) || 
                    (WEB3StringTypeUtility.getFractionDigits(this.fxRate) > 4))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���בփ��[�g�̗L���������A�������R���C�������S���͈̔͊O�ł��B");
            }
            
            //this.�בփ��[�g�����l�łȂ��ꍇ�A��O���X���[����B
            if ((!WEB3StringTypeUtility.isNumber(this.fxRate)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���בփ��[�g�����l�ȊO�̒l�ł��B");
            }
            
            //this.�בփ��[�g�����O�̏ꍇ�A��O���X���[����B
            if (Double.parseDouble(this.fxRate) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���בփ��[�g��0�ȉ��̒l�ł��B");
            }
            log.exiting(STR_METHOD_NAME);
        }
    }
}
@
