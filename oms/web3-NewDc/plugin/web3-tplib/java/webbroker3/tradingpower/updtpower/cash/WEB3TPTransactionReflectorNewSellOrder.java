head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionReflectorNewSellOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���񔄕t������������(WEB3TPTransactionReflectorNewSellOrder.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) �V�K�쐬
                    2006/09/15 �Ԑi�@@  (���u)   ���f��No.31
 */
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (���񔄕t������������)
 * ��������N���X�̊g���N���X
 */
public class WEB3TPTransactionReflectorNewSellOrder extends WEB3TPTransactionReflector
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTransactionReflectorNewSellOrder.class);

    /**
     * (�����P��)
     */
    private double price;

    /**
     * (�f�t�H���g�R���X�g���N�^)
     */
    public WEB3TPTransactionReflectorNewSellOrder()
    {
        super();
    }

    /**
     * (static���\�b�h)(create���񔄕t������������)<BR>
     * �����񔄕t�����������𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j���񔄕t������������C���X�^���X�𐶐�����B<BR>
     * �@@-�f�t�H���g�R���X�g���N�^���R�[��<BR>
     * <BR>
     * �Q�j�P�j�Ő��������C���X�^���X�̑����ɃZ�b�g����B<BR>
     * <BR>
     * �@@���񔄕t������������.�v�Z���� = ����.�v�Z����<BR>
     * �@@���񔄕t������������.�����^�C�v = ����.�����^�C�v<BR> 
     * �@@���񔄕t������������.����ID =  ����.����ID<BR>
     * �@@���񔄕t������������.�g�����U�N�V�����^�C�v =  ����.�g�����U�N�V�����^�C�v<BR>
     * �@@���񔄕t������������.�g�����U�N�V���������� =  ����.�g�����U�N�V����������<BR>
     * �@@���񔄕t������������.��n�� =  ����.��n��<BR>
     * �@@���񔄕t������������.����萔�� =  ����.����萔��<BR>
     * �@@���񔄕t������������.������� =  ����.�������<BR>
     * �@@���񔄕t������������.��萔�� =  ����.��萔��<BR>
     * �@@���񔄕t������������.����� =  ����.�����<BR>
     * �@@���񔄕t������������.�����P�� =  ����.�����P��<BR>
     * <BR>
     * �R�j�ϓ����f�J�n��/�ϓ����f�I�������Z�b�g����B<BR>
     * �@@-���񔄕t������������.calc�ϓ����f��()���R�[��<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@��n���F����.��n��<BR>
     * <BR>
     * �S�j���񔄕t������������C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_calcCondition - (�v�Z����)
     * @@param l_productType - (�����^�C�v)
     * @@param l_lngProductId - (����ID)
     * @@param l_finTransactionType - (�g�����U�N�V�����^�C�v)
     * @@param l_tranDate - (�g�����U�N�V����������)
     * @@param l_dblUnexecutedQuantity - (����萔��)
     * @@param l_dblUnexecutedAmount - (�������)
     * @@param l_dblExecutedQuantity - (���ϐ���)
     * @@param l_dblExecutedAmount - (���ϑ��)
     * @@param l_deliveryDate - (��n��)
     * @@param l_taxType - (�ŋ敪)
     * @@param l_dblPrice - (�����P��)
     * @@return 
     * webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorNewSellOrder
     */
    public static WEB3TPTransactionReflectorNewSellOrder createWEB3TPTransactionReflectorNewSellOrder(
        WEB3TPCalcCondition l_calcCondition,
        ProductTypeEnum l_productType,
        long l_lngProductId,
        FinTransactionType l_finTransactionType,
        Date l_finTransactionDate,
        double l_dblUnexecutedQuantity,
        double l_dblUnexecutedAmount,
        double l_dblExecutedQuantity,
        double l_dblExecutedAmount,
        Date l_deliveryDate,
        TaxTypeEnum l_taxType,
        double l_dblPrice)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionReflectorNewSellOrder.createWEB3TPTransactionReflectorNewSellOrder()";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionReflectorNewSellOrder l_instance = new WEB3TPTransactionReflectorNewSellOrder();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setProductType(l_productType);
        l_instance.setProductId(l_lngProductId);
        l_instance.setFinTransactionType(l_finTransactionType);
        l_instance.setFinTransactionDate(l_finTransactionDate);
        l_instance.setUnexecutedQuantity(l_dblUnexecutedQuantity);
        l_instance.setUnexecutedAmount(l_dblUnexecutedAmount);
        l_instance.setExecutedQuantity(l_dblExecutedQuantity);
        l_instance.setExecutedAmount(l_dblExecutedAmount);
        l_instance.setDeliveryDate(l_deliveryDate);
        l_instance.setTaxType(l_taxType);
        l_instance.setPrice(l_dblPrice);

        l_instance.calcReflectDay(l_deliveryDate);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (get�����P��)
     * 
     * this.�����P����ԋp����B
     * 
     * @@return double
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * (set�����P��)
     * 
     * ����.�����P����this.�����P���ɃZ�b�g����B
     * 
     * @@param l_dblPrice - (�����P��)
     */
    public void setPrice(double l_dblPrice)
    {
        this.price = l_dblPrice;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.appendSuper(super.toString());
        l_builder.append("price", this.getPrice());

        return l_builder.toString();
    }
}@
