head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityTransactionChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ����ϓ�(WEB3TPSecurityTransactionChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 �R�c�@@��i (FLJ) �V�K�쐬
 Revision History : 2008/04/01 �����Q(���u) ���f��No.273
 Revision History : 2008/04/07 �����Q(���u) ���f��No.274
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.util.WEB3DateUtility;

/**
 * (����ϓ�)
 *
 * �����ȍ~����̗a��c���ϓ���\������B
 */
public class WEB3TPSecurityTransactionChange
    extends WEB3TPSecurityChange
{

    /**
     * (�����^�C�v)
     */
    private ProductTypeEnum productType;

    /**
     * (�����敪)
     */
    private SideEnum side;

    /**
     * (���敪)
     */
    private String execType;

    /**
     * (�����J�e�S��)
     */
    private OrderCategEnum orderCateg;

    /**
     * @@roseuid 41087DAD00CE
     */
    public WEB3TPSecurityTransactionChange()
    {

    }

    /**
     * (create����ϓ�)<BR>
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityTransactionChange
     * @@roseuid 40D80DC103B7
     */
    public static WEB3TPSecurityTransactionChange create()
    {
        return new WEB3TPSecurityTransactionChange();
    }

    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v���擾����B
     * @@return �����^�C�v
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set�����^�C�v)<BR>
     * �����^�C�v��ݒ肷��B
     * @@param l_productType �����^�C�v
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get�����敪)<BR>
     * �����敪���擾����B
     * @@return SideEnum
     * @@roseuid 40B2D92E02A7
     */
    public SideEnum getSide()
    {
        return side;
    }

    /**
     * (set�����敪)<BR>
     * �����敪��ݒ肷��B
     * @@param l_side - (�����敪)
     * @@roseuid 40B2D96A0315
     */
    public void setSide(SideEnum l_side)
    {
        side = l_side;
    }

    /**
     * (get���敪)<BR>
     * ���敪���擾����B
     * @@return String
     * @@roseuid 40B2D93702A7
     */
    public String getExecType()
    {
        return execType;
    }

    /**
     * (set���敪)<BR>
     * ���敪��ݒ肷��B
     * @@param l_strExecType - (���ϋ敪)
     * @@roseuid 40B2D96A0343
     */
    public void setExecType(String l_strExecType)
    {
        execType = l_strExecType;
    }

    /**
     * (get�����J�e�S��)<BR>
     * �����J�e�S�����擾����B
     * @@return OrderCategEnum
     * @@roseuid 40DA41F50025
     */
    public OrderCategEnum getOrderCateg()
    {
        return orderCateg;
    }

    /**
     * (set�����J�e�S��)<BR>
     * �����J�e�S����ݒ肷��B
     * @@param l_orderCateg - (�����J�e�S��)
     * @@roseuid 40DA42000381
     */
    public void setOrderCateg(OrderCategEnum l_orderCateg)
    {
        orderCateg = l_orderCateg;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * <BR>
     * �ϓ����f�J�n���A�ϓ����f�I�������v�Z���Z�b�g����B<BR>
     * <BR>
     * �P�j�h�ϓ����f�J�n���h���v�Z����B<BR>
     * �@@[a.�ݓ����t�܂��͌������t�i�ی�c�����j�̏ꍇ]<BR>
     * �@@((this.�����^�C�v() == 7�F�ݐϓ��� && this.get�����敪 == 1�F���t)<BR>
     * �@@�@@||<BR>
     * �@@�@@(this.�����^�C�v() == 1�F���� && this.get�����敪 == 1�F���t && this.get�a��敪 == 0�F�ی�))<BR>
     * <BR>
     * �@@�@@[b.PTS�o���I�������ς̏ꍇ]<BR>
     * �@@�@@(this.get�]�͌v�Z����().isPTS�o���I���敪() == true && this.get���敪 == 2�F����)<BR>
     * <BR>
     * �@@�@@�@@�h�ϓ����f�J�n���h = ����.��n��<BR>
     * <BR>
     * �@@�@@[b.�ȊO�̏ꍇ]�@@<BR>
     * <BR>
     * �@@�@@�@@�h�ϓ����f�J�n���h = this.get�]�͌v�Z����().roll�c�Ɠ�(:Date = ����.��n��, :int = 1)<BR>
     * <BR>
     * �@@[a.�ȊO�̏ꍇ]<BR>
     * <BR>
     * �@@�@@�h�ϓ����f�J�n���h = ����.��n��<BR>
     * <BR>
     * �Q�j�P�j�Ōv�Z�����h�ϓ����f�J�n���h��␳����B<BR>
     * <BR>
     * �@@[�h�ϓ����f�J�n���h���c�Ɠ�(T+5)��薢�����t�̏ꍇ]<BR>
     * �@@(�h�ϓ����f�J�n���h > �c�Ɠ�(T+5)(*))<BR>
     * <BR>
     * �@@�@@�h�ϓ����f�J�n���h = �c�Ɠ�(T+5)(*)<BR>
     * <BR>
     * �R�j�h�ϓ����f�J�n���h���Z�b�g����B<BR>
     * �@@-this.set�ϓ����f�J�n��()���R�[��<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@Date = �h�ϓ����f�J�n���h<BR>
     * <BR>
     * �S�j�h�ϓ����f�I�����h���Z�b�g����B<BR>
     * �@@-this.set�ϓ����f�I����()���R�[��<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@Date = �c�Ɠ�(T+5)(*)<BR>
     * <BR>
     * �T�j��n�����Z�b�g����B<BR>
     * �@@-this.set��n��()���R�[��<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@Date = ����.��n��<BR>
     * <BR>
     * ----<BR>
     * (*)<BR>
     * �c�Ɠ�(T+5) = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 5)�̖߂�l<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {       
        if ( (ProductTypeEnum.RUITO.equals(getProductType())
              && SideEnum.BUY.equals(getSide()))
            || (ProductTypeEnum.EQUITY.equals(getProductType())
                && SideEnum.BUY.equals(getSide())
                && WEB3TPDepositTypeDef.TRUST.equals(getDepositType())))
        {
            //PTS�o���I�������ς̏ꍇ
            if (getCalcCondition().isPtsOrderExecutionEndType() &&
                WEB3TPExecTypeDef.EXECUTED.equals(this.getExecType()))
            {
                setReflectStartDay(l_datDeliveryDate);
            }
            else {
                    setReflectStartDay(getCalcCondition().rollBizDate(l_datDeliveryDate, 1));                   
            }
        }
        else
        {
            setReflectStartDay(l_datDeliveryDate);
        }
 
        
        //��L�̌��ʁA�ϓ����f�J�n����T+5�𒴂���ꍇ
		//�s+5�ɂ܂Ƃ߂�        
		Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
		if (WEB3DateUtility.compareToDay(l_datT5, this.getReflectStartDay()) < 0) 
		{
		    //�����ȊO�̏ꍇ�A�␳����
            if (!ProductTypeEnum.EQUITY.equals(getProductType())) 
            {
                setReflectStartDay(l_datT5);
            }
        }
        
        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));

        setDeliveryDate(l_datDeliveryDate);    
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ�
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("productType", getProductType())
            .append("side", getSide())
            .append("execType", getExecType())
            .append("orderCateg", getOrderCateg())
            .toString();
    }

}
@
