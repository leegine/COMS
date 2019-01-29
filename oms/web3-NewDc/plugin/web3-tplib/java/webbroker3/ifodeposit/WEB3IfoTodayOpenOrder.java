head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoTodayOpenOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�����V�K���������N���X(WEB3IfoTodayOpenOrder.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/20 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit;

import java.text.ParseException;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.util.WEB3DateUtility;

/**
 * (�敨OP�����V�K���������)<BR>
 * �����̐V�K�������̏���\���N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoTodayOpenOrder
{

    /**
     * �����P��ID�B
     */
    public long orderUnitid;

    /**
     * ����ID�B
     */
    public long productId;

    /**
     * �s��ID�B
     */
    public long marketId;

    /**
     * (���敪)<BR>
     * 
     * 1�F����<BR>
     * 2�F����<BR>
     */
    public ContractTypeEnum contractType;

    /**
     * �������B
     */
    public Date orderBizDate;

    /**
     * ��n���B
     */
    public Date deliveryDate;

    /**
     * (�敨�^�I�v�V�����敪)<BR>
     * 
     * 1�F�敨<BR>
     * 2�F�I�v�V����<BR>
     */
    public String ifoProductType;

    /**
     * �������ʁB
     */
    public double quantity = 0;

    /**
     * (�I�v�V�����T�Z��n����B)<BR>
     * ���I�v�V���������̏ꍇ�̓}�C�i�X�l���Z�b�g�����B<BR>
     * ���敨�̏ꍇ�A0�i�����l�̂܂܁j<BR>
     */
    public double optionEstimatedNetAmount = 0;

    /**
     * @@roseuid 4158CAEB03E5
     */
    public WEB3IfoTodayOpenOrder()
    {

    }

    /**
     * (create�敨OP�����V�K���������)<BR>
     * 
     * �敨OP�����V�K���������𐶐�����B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder
     * @@roseuid 4112EC1500DB
     */
    public static WEB3IfoTodayOpenOrder create()
    {
        return new WEB3IfoTodayOpenOrder();
    }

    /**
     * (get�敨OP�����V�K���������)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����̒����P��Params���A�敨OP�����V�K�����������쐬����B<BR>
     * 
     * �P�j�@@�I�u�W�F�N�g�̐���<BR>
     * �@@�敨OP�����V�K���������I�u�W�F�N�g�𐶐�����B<BR>
     * 
     * �Q�j�@@�v���p�e�B�̃Z�b�g<BR>
     * �@@���������敨OP�����V�K���������ɉ��L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�E�����P��ID�F�@@����.�����P��Params.�����P��ID<BR>
     * �@@�E����ID�F�@@����.�����P��Params.����ID<BR>
     * �@@�E�s��ID�F�@@����.�����P��Params.�s��ID<BR>
     * �@@�E���敪�F�@@<BR>
     * �@@�@@�@@- ����.�����P��Params.������� == �h�敨�V�K���������h�A�܂��́A�h�@@OP�V�K���������h�̏ꍇ�A�h�����h�B<BR>
     * 
     *       - �ȊO�A�h�����h�B<BR>
     * 
     * �@@�E�敨�^�I�v�V�����敪�F�@@����.�����P��Params.�敨�^�I�v�V�����敪<BR>
     * �@@�E�������F�@@����.�����P��Params.������<BR>
     * �@@�E��n���F�@@����.�����P��Params.��n��<BR>
     * �@@�E�������ʁF<BR>
     * �@@�@@�@@- �����̏ꍇ<BR>
     * �@@�@@�@@�@@(����.�����P��Params.getExecutedQuantityIsNull == true)�A<BR>
     * �@@�@@�@@�@@�@@�@@����.�����P��Params.��������<BR>
     * 
     * �@@�@@�@@- �ꕔ���̏ꍇ<BR>
     * �@@�@@�@@�@@(����.�����P��Params.getExecutedQuantityIsNull == false)�A<BR>
     * �@@�@@�@@�@@�@@�@@����.�����P��.����Params���� - �����P��Params.��萔��<BR>
     * 
     * �@@�E�I�v�V�����T�Z��n����F�@@�I�v�V�����̏ꍇ(����.�����P��Params.�����J�e�S�� == �hOP�V�K�������h)�̂ݐݒ肷��<BR>
     * �@@�@@�@@�@@- �����̏ꍇ(���敪 == �h�����h)�A����.�����P��Params.�T�Z��n����̃}�C�i�X�l<BR>
     * 
     * �@@�@@�@@�@@- �����̏ꍇ(���敪 == �h�����h)�A����.�����P��Params.�T�Z��n���<BR>
     * 
     * �R�j�@@�v���p�e�B�Z�b�g�����敨OP�����V�K����������ԋp����B<BR>
     * 
     * @@param l_orderUnitParams - �����P��Params�B
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder
     * @@roseuid 413D68610215
     */
    public static WEB3IfoTodayOpenOrder getIfoTodayOpenOrder(IfoOrderUnitParams l_orderUnitParams)
    {

        // ���敪
        ContractTypeEnum l_contractType = ContractTypeEnum.SHORT;
        if (OrderTypeEnum
            .IDX_FUTURES_BUY_TO_OPEN
            .equals(l_orderUnitParams.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(
                l_orderUnitParams.getOrderType()))
        {
            l_contractType = ContractTypeEnum.LONG;
        }

        // ������
        Date l_orderBizDate = null;
        try
        {
            l_orderBizDate =
                GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd").parse(
                    l_orderUnitParams.getBizDate());
        } 
        catch(ParseException pe)
        {
            pe.printStackTrace();
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    "WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(IfoOrderUnitParams)",
                    pe.getMessage(), pe);
        }

        // ����
        double l_quantity = l_orderUnitParams.getQuantity();
        if (!l_orderUnitParams.getExecutedQuantityIsNull())
        {
            l_quantity -= l_orderUnitParams.getExecutedQuantity();
        }

        // �I�v�V�����T�Z��n���
        double l_estimatedNetAmount = 0.0;
        if (OrderCategEnum
            .IDX_OPTIONS_OPEN
            .equals(l_orderUnitParams.getOrderCateg()))
        {
            if (ContractTypeEnum.LONG.equals(l_contractType))
            {
                l_estimatedNetAmount = l_orderUnitParams.getEstimatedPrice() * -1;
            } 
            else if (ContractTypeEnum.SHORT.equals(l_contractType))
            {
                l_estimatedNetAmount = l_orderUnitParams.getEstimatedPrice();
            }
        }

        WEB3IfoTodayOpenOrder l_order = WEB3IfoTodayOpenOrder.create();
        l_order.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
        l_order.setProductId(l_orderUnitParams.getProductId());
        l_order.setMarketId(l_orderUnitParams.getMarketId());
        l_order.setContractType(l_contractType);
        l_order.setIfoProductType(l_orderUnitParams.getFutureOptionDiv());
        l_order.setOrderBizDate(l_orderBizDate);
        l_order.setDeliveryDate(l_orderUnitParams.getDeliveryDate());
        l_order.setQuantity(l_quantity);
        l_order.setOptionEstimatedNetAmount(l_estimatedNetAmount);

        return l_order;
    }

    /**
     * (get�敨OP�����V�K���������)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����̐敨OP���������e���A�敨OP�����V�K���������I�u�W�F�N�g���쐬����B<BR>
     * 
     * �P�j�@@�I�u�W�F�N�g�̐���<BR>
     * �@@�敨OP�����V�K���������I�u�W�F�N�g�𐶐�����B<BR>
     * 
     * �Q�j�@@�v���p�e�B�̃Z�b�g<BR>
     * �@@���������敨OP�����V�K���������ɉ��L�v���p�e�B�Z�b�g���s���B<BR>
     * �@@
     * �@@�E�����P��ID�F�@@����.�敨OP���������e.�����P��ID<BR>
     * �@@�E����ID�F�@@����.�敨OP���������e.����ID<BR>
     * �@@�E�s��ID�F�@@����.�敨OP���������e.�s��ID<BR>
     * �@@�E���敪�F�@@����.�敨OP���������e.���敪<BR>
     * �@@�E�敨�^�I�v�V�����敪�F�@@<BR>
     * �@@�@@�@@- ����.�敨OP���������e.�敨�I�v�V�������i == �h�敨�h�̏ꍇ�A�h�敨�h�B<BR>
     * �@@�@@�@@- �ȊO�A�h�I�v�V�����h�B<BR>
     * �@@�E�������F�@@����.�敨OP���������e.������<BR>
     * �@@�E��n���F�@@����.�敨OP���������e.��n��<BR>
     * �@@�E�������ʁF�@@����.�敨OP���������e.��������<BR>
     * �@@�E�I�v�V�����T�Z��n����F�@@�I�v�V�����̏ꍇ(����.�敨OP���������e.�敨�I�v�V�������i != �h�敨�h)�̂ݐݒ肷��<BR>
     * �@@�@@�@@�@@- �����̏ꍇ(���敪 == �h�����h)�A����.�敨OP���������e.�T�Z��n����̃}�C�i�X<BR>
     * �@@�@@�@@�@@- �����̏ꍇ(���敪 == �h�����h)�A����.�敨OP���������e.�T�Z��n���<BR>
     * 
     * �R�j�@@�v���p�e�B�Z�b�g�����敨OP�����V�K����������ԋp����B<BR>
     * �@@
     * @@param l_ifoNewOrderSpec - �敨OP���������e�B
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder
     * @@roseuid 413D468C0021
     */
    public static WEB3IfoTodayOpenOrder getIfoTodayOpenOrder(WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
    {
        ContractTypeEnum l_contractType = l_ifoNewOrderSpec.contractType;
        String l_ifoProductType = WEB3FuturesOptionDivDef.OPTION;
        if (IfoDerivativeTypeEnum
            .FUTURES
            .equals(l_ifoNewOrderSpec.ifoDerivativeType))
        {
            l_ifoProductType = WEB3FuturesOptionDivDef.FUTURES;
        }
        double l_dblEstimatedNetAmount = 0.0;
        if (WEB3FuturesOptionDivDef.OPTION.equals(l_ifoProductType))
        {
            if (ContractTypeEnum.LONG.equals(l_contractType))
            {
                l_dblEstimatedNetAmount = l_ifoNewOrderSpec.estimatedNetAmount * -1;
            } 
            else if (ContractTypeEnum.SHORT.equals(l_contractType))
            {
                l_dblEstimatedNetAmount = l_ifoNewOrderSpec.estimatedNetAmount;
            }
        }

        WEB3IfoTodayOpenOrder l_order = WEB3IfoTodayOpenOrder.create();
        l_order.setOrderUnitId(l_ifoNewOrderSpec.orderUnitId);
        l_order.setProductId(l_ifoNewOrderSpec.productId);
        l_order.setMarketId(l_ifoNewOrderSpec.marketId);
        l_order.setContractType(l_contractType);
        l_order.setIfoProductType(l_ifoProductType);
        l_order.setOrderBizDate(l_ifoNewOrderSpec.orderBizDate);
        l_order.setDeliveryDate(l_ifoNewOrderSpec.deliveryDate);
        l_order.setQuantity(l_ifoNewOrderSpec.quantity);
        l_order.setOptionEstimatedNetAmount(l_dblEstimatedNetAmount);

        return l_order;
    }

    /**
     * (get�I�v�V���������T�Z��n���)<BR>
     * 
     * (this.is���� == true && this.��n�� 
     * ==�@@����.��n��)�̏ꍇ�̂݁Athis.�I�v�V�����T�Z��n�����ԋp����B<BR>
     * �ȊO�A0��ԋp����B<BR>
     * @@param l_datDeliveryDate - ��n���B
     * @@return double
     * @@roseuid 4124A5A1039F
     */
    public double getOptionBuyEstimatedNetAmount(Date l_datDeliveryDate)
    {
        if (isBuy()
            && (WEB3DateUtility.compareToDay(getDeliveryDate(), l_datDeliveryDate)
                == 0))
        {
            return getOptionEstimatedNetAmount();
        } else
        {
            return 0.0;
        }
    }

    /**
     * (subtract�I�v�V�����T�Z��n���)<BR>
     * 
     * this.�I�v�V�����T�Z��n�����������̎�n��������Z����B <BR>
     * 
     * this.�I�v�V�����T�Z��n��� = this.�I�v�V�����T�Z��n��� - ����.��n���<BR>
     * @@param l_dblNetAmount - ��n���
     * @@roseuid 4129D4D3037C
     */
    public void subtractOptionEstimatedNetAmount(double l_dblNetAmount)
    {
        double l_dblSubtracted = getOptionEstimatedNetAmount() - l_dblNetAmount;
        setOptionEstimatedNetAmount(l_dblSubtracted);
    }

    /**
     * (is����)<BR>
     * 
     * �Y�������������ł��邩�𔻒肷��B<BR>
     * 
     * this.���敪==�h�����h�̏ꍇ�Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4117094701B0
     */
    public boolean isBuy()
    {
        if (ContractTypeEnum.LONG.equals(getContractType()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * (is�敨)<BR>
     * 
     * �Y���������敨�ł��邩�𔻒肷��B<BR>
     * 
     * this.�敨�^�I�v�V�����敪 == 
     * �h�敨�h�̏ꍇ�Atrue��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4129DB850298
     */
    public boolean isFutures()
    {
        if (WEB3FuturesOptionDivDef.FUTURES.equals(getIfoProductType()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * ���敪���擾����B
     * 
     * @@return�@@���敪
     */
    public ContractTypeEnum getContractType()
    {
        return contractType;
    }

    /**
     * ��n�����擾����B
     * 
     * @@return�@@��n��
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * �敨�I�v�V�����敪���擾����B
     * 
     * @@return�@@�敨�I�v�V�����敪
     */
    public String getIfoProductType()
    {
        return ifoProductType;
    }

    /**
     * �s��ID���擾����B
     * 
     * @@return�@@�s��ID
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * �I�v�V�����T�Z��n������擾����B
     * 
     * @@return�@@�I�v�V�����T�Z��n���
     */
    public double getOptionEstimatedNetAmount()
    {
        return optionEstimatedNetAmount;
    }

    /**
     * ���������擾����B
     * 
     * @@return�@@������
     */
    public Date getOrderBizDate()
    {
        return orderBizDate;
    }

    /**
     * �����P��ID���擾����B
     * 
     * @@return�@@�����P��ID
     */
    public long getOrderUnitId()
    {
        return orderUnitid;
    }

    /**
     * ����ID���擾����B
     * 
     * @@return�@@����ID
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * ���ʂ��擾����B
     * 
     * @@return�@@����
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * ���敪��ݒ肷��B
     * 
     * @@param l_contractType�@@���敪
     */
    public void setContractType(ContractTypeEnum l_contractType)
    {
        contractType = l_contractType;
    }

    /**
     * ��n����ݒ肷��B
     * 
     * @@param l_datDeliveryDate�@@��n��
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * �敨�I�v�V�����敪��ݒ肷��B
     * 
     * @@param l_strIfoProductType�@@�敨�I�v�V�����敪
     */
    public void setIfoProductType(String l_strIfoProductType)
    {
        ifoProductType = l_strIfoProductType;
    }

    /**
     * �s��ID��ݒ肷��B
     * 
     * @@param l_lngMarketId�@@�s��ID
     */
    public void setMarketId(long l_lngMarketId)
    {
        marketId = l_lngMarketId;
    }

    /**
     * �I�v�V�����T�Z��n�����ݒ肷��B
     * 
     * @@param l_dblOptionEstimatedNetAmount�@@�I�v�V�����T�Z��n���
     */
    public void setOptionEstimatedNetAmount(double l_dblOptionEstimatedNetAmount)
    {
        optionEstimatedNetAmount = l_dblOptionEstimatedNetAmount;
    }

    /**
     * ��������ݒ肷��B
     * 
     * @@param l_datOrderBizDate�@@������
     */
    public void setOrderBizDate(Date l_datOrderBizDate)
    {
        orderBizDate = l_datOrderBizDate;
    }

    /**
     * �����P��ID��ݒ肷��B
     * 
     * @@param l_lngOrderUnitId�@@�����P��ID
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitid = l_lngOrderUnitId;
    }

    /**
     * ����ID��ݒ肷��B
     * 
     * @@param l_lngProductId�@@����ID
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * ���ʂ�ݒ肷��B
     * 
     * @@param l_dblQuantity�@@����
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * WEB3IfoTodayOpenOrder�̕�����\����Ԃ��B
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoTodayOpenOrder={");
        l_sb.append("orderUnitId=").append(getOrderUnitId());
        l_sb.append(",productId=").append(getProductId());
        l_sb.append(",marketId=").append(getMarketId());
        l_sb.append(",contractType=").append(getContractType());
        l_sb.append(",orderBizDate=").append(getOrderBizDate());
        l_sb.append(",deliveryDate=").append(getDeliveryDate());
        l_sb.append(",ifoProductType=").append(getIfoProductType());
        l_sb.append(",quantity=").append(getQuantity());
        l_sb.append(",optionEstimatedNetAmount=").append(
            getOptionEstimatedNetAmount());
        l_sb.append("}");
        return l_sb.toString();
    }

}
@
