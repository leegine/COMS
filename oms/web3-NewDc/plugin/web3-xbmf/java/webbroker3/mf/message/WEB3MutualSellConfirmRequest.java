head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����m�F���N�G�X�g�N���X(WEB3MutualSellConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���� (���u) �V�K�쐬
Revesion History : 2004/08/25 ���E (���u) ���r���[
Revesion History : 2005/10/20 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��536
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.define.WEB3DeliveryMethodDef;
import webbroker3.common.define.WEB3PaymentMethodDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����M�����m�F���N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3MutualSellConfirmRequest 
    extends WEB3MutualCommonRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111346L;
  
    /**
     * �������@@<BR>
     * <BR>
     * 0:��񐿋��@@1:���搿��<BR>
     */
    public String sellBuyDiv;
    
    /**
     * ���ϕ��@@<BR>
     * <BR>
     * 1:�~�݁@@2:�O��<BR>
     */
    public String settleDiv;
    
    /**
     * ��n���@@<BR>
     * <BR>
     * 1:��s�U���݁@@2:�،���������<BR>
     * 
     */
    public String deliveryDiv;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualSellConfirmRequest.class);
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A89C6B0036
     */
    public WEB3MutualSellConfirmRequest() 
    {
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M���m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A89B3B016F
     */
    
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualSellConfirmResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P)�@@�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h��null�̏ꍇ�A��O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * �Q)�@@�w����@@�`�F�b�N<BR>
     * �@@�Q�|�P)�@@this.�w����@@��null�̏ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00400 <BR>
     * �@@�Q�|�Q)�@@this.�w����@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h�S���h<BR>
     * �@@�@@�@@�@@�E�h���z�h<BR>
     * �@@�@@�@@�@@�E�h�����h<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00073 <BR>
     * <BR>
     * �R�j�@@(���)���ʃ`�F�b�N <BR>
     * �@@�R�|�P�j�@@this.�w����@@���g�S���h���Athis.����!=null�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�ł���ꍇ�A ��O���X���[����B <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00093 <BR>
     * �@@�R�|�Q�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.����==null�̏ꍇ�A��O���X���[����B <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00094 <BR>
     * �@@�R�|�R�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.���ʂ����l�ȊO�ł���ꍇ�A��O���X���[����B <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00095 <BR>
     * �@@�R�|�S�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.���ʁ�0�̏ꍇ�A��O���X���[����B <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00096 <BR>
     * �@@�R�|�T�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A <BR>
     * �@@�@@�@@�@@�@@this.���ʂ̌�����10���̏ꍇ�A��O���X���[����B<BR> 
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00097 <BR>
     * <BR>
     * �S)�@@�������@@�`�F�b�N<BR>  
     * �@@�S�|�P)�@@this.�������@@���ȉ��̒l�̂����ꂩ�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>  
     * �@@�@@�@@�@@�E�h��񐿋��h  <BR>
     * �@@�@@�@@�@@�E�h���搿���h  <BR>
     * �@@�@@�@@�@@�Enull<BR>
     *            class: WEB3BusinessLayerException<BR><BR>
     *            tag:   BUSINESS_ERROR_00402 <BR><BR>
     * <BR>
     * �T)�@@���ϕ��@@�`�F�b�N<BR>
     * �@@�T�|�P)�@@this.���ϕ��@@��null�̏ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00403 <BR>
     * �@@�T�|�Q)�@@this.���ϕ��@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h�~�݁h<BR>
     * �@@�@@�@@�@@�E�h�O�݁h<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00404 <BR>
     * <BR>
     * �U)�@@��n���@@�`�F�b�N<BR>
     * �@@�U�|�P)�@@this.��n���@@��null�̏ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00405 <BR>
     * �@@�U�|�Q)�@@this.��n���@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h��s�U���݁h<BR>
     * �@@�@@�@@�@@�E�h�،����������h<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00099 <BR>
     *   �U�|�R�j this.��n���@@==�h��s�U���݁h�̏ꍇ�A <BR>
     *           this.���ϕ��@@!=�h�~�݁h�ł���΁A��O���X���[����B <BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02286 <BR>
     * <BR>
     * �V�j�@@�������̃`�F�b�N<BR>
     *    this.��������null�ł���ꍇ�A��O���X���[����B<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00406 <BR>
     * <BR>
     * �W�j�@@ID�`�F�b�N<BR> 
     * �@@this.ID==null�̏ꍇ�A��O���X���[����B<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * @@roseuid 40A89B790017
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
   
         //�P)�@@�����R�[�h�`�F�b�N
       
        //this.�����R�[�h��null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.mutualProductCode)) 
        {
            log.debug("�����R�[�h����͂��Ă��������B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h����͂��Ă��������B");
        }
       
        //�Q)�@@�w����@@�`�F�b�N  
       
        //�Q�|�P)�@@this.�w����@@��null�̏ꍇ�A��O���X���[����B                
        if (WEB3StringTypeUtility.isEmpty(this.specifyDiv)) 
        {
            log.debug("�w����@@�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00400,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@�����w��ł��B");
        }
        
        //�Q�|�Q)�@@this.�w����@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B
         //   �E�S���E���z�E����
        if (!(((WEB3SellDivDef.ALL_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv)))) 
        {
            log.debug("�w����@@�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00073,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�u�S���v�u���z�v �u�����v");    
        }
        
        // �R�j�@@(���)���ʃ`�F�b�N 
        
        //�R�|�P�jthis.�w����@@���g�S���h���A
        //this.����!=null�̏ꍇ�A ��O���X���[����B
        if ((((WEB3SellDivDef.ALL_DESIGNATE).equals(this.specifyDiv))) && 
            ((this.mutualOrderQuantity != null)))
        {
            log.debug("�w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00093,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@���g�S���h�̏ꍇ�́A�������ʎw��s�B"); 
        }
        
        //�R�|�Q�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A
        //this.����==null�̏ꍇ�A��O���X���[����B
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))) &&
            (WEB3StringTypeUtility.isEmpty(this.mutualOrderQuantity)))
        {
            log.debug("�w����@@���g���z�h�܂��́g�����h" +
                "�ł��芎�A�������ʂ����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00094,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�w����@@���g���z�h�܂��́g�����h�ł��芎�A" +
                "�������ʂ����w��ł��B"); 
        }

        //�R�|�R�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A
        //this.���ʂ����l�ȊO�ł���ꍇ�A��O���X���[����B
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) || 
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))))
        {
            // ������̕�����𔻒f����@@�\���������郆�[�e�B���e�B
            //�E�N���X(WEB3StringTypeUtility.java)
            if (WEB3StringTypeUtility.isNumber(this.mutualOrderQuantity) == false)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h" +
                    "�ł��芎�������ʂ����l�ȊO�ł��B"); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00095,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎�������ʂ����l�ȊO�ł��B"); 
            }
        }
         
        //�R�|�S�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A
        //this.���ʁ�0�̏ꍇ�A��O���X���[����B
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) ||
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))))
        {
            if (Double.parseDouble(this.mutualOrderQuantity) <= 0)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł��芎�A" +
                    "�������ʂ�0�ȉ��̒l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g���z�h�܂��́g�����h�ł��芎�A�������ʂ�0�ȉ��̒l�ł��B"); 
            }       
        }
        
        //�R�|�T�j�@@this.�w����@@���g���z�h�܂��́g�����h�ł��芎�A
        //this.���ʂ̌�����10���̏ꍇ�A��O���X���[����B  
        if ((((WEB3SellDivDef.MONEY_DESIGNATE).equals(this.specifyDiv)) ||
            ((WEB3SellDivDef.COUNT_DESIGNATE).equals(this.specifyDiv))))
        {
            if (this.mutualOrderQuantity.length() > 10)
            {
                log.debug("�w����@@���g���z�h�܂��́g�����h�ł���A���A" +
                    "�������ʂ�10���𒴂���l�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00097,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�w����@@���g���z�h�܂��́g�����h�ł���A���A" +
                    "�������ʂ�10���𒴂���l�ł��B"); 
            }       
        }  
      
        //�S)�@@�������@@�`�F�b�N        
        //�@@this.�������@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B
         // 0:��񐿋� 1:���搿���Enull 
        if (!(((WEB3ClaimDivDef.SELL).equals(this.sellBuyDiv)) || 
            ((WEB3ClaimDivDef.BUY).equals(this.sellBuyDiv))
            || this.sellBuyDiv == null))
        {
            log.debug("�������@@���h��񐿋��h�h���搿���hnull �ȊO�̏ꍇ�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00402,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������@@���h��񐿋��h�h���搿���hnull �ȊO�̏ꍇ�B"); 
        }
     
        // �T)�@@���ϕ��@@�`�F�b�N 
        
        //�T�|�P)�@@this.���ϕ��@@��null�̏ꍇ�A��O���X���[����B<BR>
        if (WEB3StringTypeUtility.isEmpty(this.settleDiv))
        {
            log.debug("���ϕ��@@�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00403,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ϕ��@@�����w��ł��B"); 
        }
        
        //�T�|�Q)�@@this.���ϕ��@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B<BR>
         //   �~�݁E�O��
        if (!(WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv) || 
            WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)))
        {
            log.debug("���ϕ��@@���h�~�݁h���́h�O�݁h�ȊO�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00404,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ϕ��@@���h�~�݁h���́h�O�݁h�ȊO�̏ꍇ"); 
        }
       
        // �U)�@@��n���@@�`�F�b�N
        
        //�U�|�P)�@@this.��n���@@��null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.deliveryDiv))
        {
            log.debug("��n���@@�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00405,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��n���@@�����w��ł��B"); 
        }
        
        //�@@�U�|�Q)�@@this.��n���@@���ȉ��̃R�[�h�ȊO�̏ꍇ�A��O���X���[����B
        //  ��s�U���݁E�،���������
        if (!(((WEB3DeliveryMethodDef.BANK_TRANSFER).equals(this.deliveryDiv)) || 
            ((WEB3DeliveryMethodDef.SECURITIES_ACCOUNT_INPUT_SELL).equals(this.deliveryDiv))))
        {
            log.debug("��n���@@���A�g��s�U���݁h�܂��́g�،����������h�ȊO�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00099,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��n���@@���A�g��s�U���݁h�܂��́g�،����������h�ȊO�ł��B"); 
        }
        
        //�U�|�R�j this.��n���@@==�h��s�U���݁h�̏ꍇ�A 
        //      this.���ϕ��@@!=�h�~�݁h�ł���΁A��O���X���[����B 
        if (WEB3PaymentMethodDef.BANK_TRANSFER.equals(this.deliveryDiv))
        {
            if (!WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv))
            {
                log.debug("��n���@@���h��s�U���݁h�̏ꍇ�A���ϕ��@@�͕K���h�~�݁h��I�����Ă��������B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02286,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n���@@���h��s�U���݁h�̏ꍇ�A���ϕ��@@�͕K���h�~�݁h��I�����Ă��������B"); 
            }
        }
        
        // �V�j�@@�������̃`�F�b�N
                
        //this.��������null�ł���ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.orderedDate, "yyyyMMdd")))
        {
            log.debug("�����������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B"); 
        }

        // �W�j�@@ID�`�F�b�N
        // �@@this.ID==null�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("���M���Y�h�c�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���M���Y�h�c�����w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
