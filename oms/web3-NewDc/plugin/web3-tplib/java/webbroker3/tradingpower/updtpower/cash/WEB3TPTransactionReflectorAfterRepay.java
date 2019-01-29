head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionReflectorAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό������(WEB3TPTransactionReflectorAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
                   2006/09/15 �Ԑi�@@  (���u)���f��No.29
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό������)
 */
public class WEB3TPTransactionReflectorAfterRepay extends WEB3TPTransactionReflector
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTransactionReflectorAfterRepay.class);

    /**
     * (����蕪�ԍό��ϑ��v)
     */
    private double unexecutedRepaySettleProfitLoss;

    /**
     * (���񔭒����ԍό��ϑ��v)
     */
    private double currOrderRepaySettleProfitLoss;

    /**
     * @@roseuid 41E383D6022C
     */
    public WEB3TPTransactionReflectorAfterRepay()
    {

    }

    /**
     * (static)(create�ԍό������)<BR>
     * <BR>
     * �ԍό��������쐬���A�ԋp����B<BR>
     * <BR>
     * 1)�ԍό������C���X�^���X(="�ԍό������")�𐶐�����B<BR> 
     * �@@-�f�t�H���g�R���X�g���N�^���R�[�� <BR>
     * <BR>
     * 2)���������ԍό������C���X�^���X�̑����ɒl���Z�b�g<BR> 
     * <BR>
     * �@@�|"�ԍό������".set�]�͌v�Z����(:�]�͌v�Z���� = ����.�]�͌v�Z����)<BR> 
     * �@@�|"�ԍό������".set�����^�C�v(:ProductionType = ����.�����^�C�v)<BR>
     * �@@�|"�ԍό������".set����ID(:long = ����.����ID)<BR>
     * �@@�|"�ԍό������".set�g�����U�N�V�����^�C�v(:FinTransactionType = ����.�g�����U�N�V�����^�C�v)<BR> 
     * �@@�|"�ԍό������".set�g�����U�N�V����������(:Date = ����.�g�����U�N�V����������)<BR>
     * �@@�|"�ԍό������".set��n��(:Date = ����.��n��)<BR>
     * �@@�|"�ԍό������".set����萔��(:double = ����.����萔��)<BR> 
     * �@@�|"�ԍό������".set�������(:double = ����.�������)<BR>
     * �@@�|"�ԍό������".set���ϐ���(:double = ����.���ϐ���)<BR>
     * �@@�|"�ԍό������".set���ϑ��(:double = ����.���ϑ��)<BR>
     * �@@�|"�ԍό������".set�ŋ敪(:TaxTypeEnum = ����.�ŋ敪)<BR>
     * �@@�|"�ԍό������".calc�ϓ����f��(:Date = ����.��n��)<BR>
     * <BR>
     * �@@�|"�ԍό������".set�����ԍό��ϑ��v(:double = ����.�����ԍό��ϑ��v)<BR> 
     * �@@�|"�ԍό������".set���񔭒����ԍό��ϑ��v(:double = ����.���񔭒����ԍό��ϑ��v)<BR> 
     * <BR>
     * 3)�ԍό������C���X�^���X��ԋp����B<BR> 
     * <BR>
     * <BR>
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_productType - (�����^�C�v)
     * @@param l_lngProductId - (����ID)
     * @@param l_finTransactionType - (�g�����U�N�V�����^�C�v)
     * @@param l_finTransactionDate - (�g�����U�N�V����������)
     * @@param l_dblUnexecutedQuantity - (����萔��)
     * @@param l_dblUnexecutedAmount - (�������)
     * @@param l_dblExecutedQuantity - (���ϐ���)
     * @@param l_dblExecutedAmount - (���ϑ��)
     * @@param l_datDeliveryDate - (��n��)
     * @@param l_taxType - (�ŋ敪)
     * @@param l_dblUnexecutedRepaySettleProfitLoss - (�����ԍό��ϑ��v)
     * @@param l_dblCurrOrderRepaySettleProfitLoss - (���񔭒����ԍό��ϑ��v)
     * @@return 
     * webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorAfterRepay
     * @@roseuid 41C95EA00020
     */
    public static WEB3TPTransactionReflectorAfterRepay createWEB3TPTransactionReflectorAftreRepay(
        WEB3TPCalcCondition l_calcCondition,
        ProductTypeEnum l_productType,
        long l_lngProductId,
        FinTransactionType l_finTransactionType,
        Date l_finTransactionDate,
        double l_dblUnexecutedQuantity,
        double l_dblUnexecutedAmount,
        double l_dblExecutedQuantity,
        double l_dblExecutedAmount,
        Date l_datDeliveryDate,
        TaxTypeEnum l_taxType,
        double l_dblUnexecutedRepaySettleProfitLoss,
        double l_dblCurrOrderRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPTransactionReflectorAftreRepay(WEB3TPCalcCondition, ProductTypeEnum, long, FinTransactionType, Date, double, double, double, double, Date, double, double)";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionReflectorAfterRepay l_instance =
            new WEB3TPTransactionReflectorAfterRepay();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setProductType(l_productType);
        l_instance.setProductId(l_lngProductId);
        l_instance.setFinTransactionType(l_finTransactionType);
        l_instance.setFinTransactionDate(l_finTransactionDate);
        l_instance.setUnexecutedQuantity(l_dblUnexecutedQuantity);
        l_instance.setUnexecutedAmount(l_dblUnexecutedAmount);
        l_instance.setExecutedQuantity(l_dblExecutedQuantity);
        l_instance.setExecutedAmount(l_dblExecutedAmount);
        l_instance.setDeliveryDate(l_datDeliveryDate);
        l_instance.setTaxType(l_taxType);
        l_instance.calcReflectDay(l_datDeliveryDate);

        l_instance.setUnexecutedRepaySettleProfitLoss(l_dblUnexecutedRepaySettleProfitLoss);
        l_instance.setCurrOrderRepaySettleProfitLoss(l_dblCurrOrderRepaySettleProfitLoss);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (get����蕪�ԍό��ϑ��v)
     * @@return double
     * @@roseuid 41C94E6E00FB
     */
    public double getUnexecutedRepaySettleProfitLoss()
    {
        final String STR_METHOD_NAME = "getUnexecutedRepaySettleProfitLoss()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.unexecutedRepaySettleProfitLoss;
    }

    /**
     * (set����蕪�ԍό��ϑ��v)
     * @@param l_dblUnexecutedRepaySettleProfitLoss - (����蕪�ԍό��ϑ��v)
     * @@roseuid 41C94E8302E0
     */
    public void setUnexecutedRepaySettleProfitLoss(double l_dblUnexecutedRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME = "setUnexecutedRepaySettleProfitLoss(double)";
        log.entering(STR_METHOD_NAME);

        this.unexecutedRepaySettleProfitLoss = l_dblUnexecutedRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���񔭒����ԍό��ϑ��v)
     * @@return double
     * @@roseuid 41C94E83039B
     */
    public double getCurrOrderRepaySettleProfitLoss()
    {
        final String STR_METHOD_NAME = "getCurrOrderRepaySettleProfitLoss()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.currOrderRepaySettleProfitLoss;
    }

    /**
     * (set���񔭒����ԍό��ϑ��v)
     * @@param l_dblCurrOrderRepaySettleProfitLoss - (����ԍϕ��ԍό��ϑ��v)
     * @@roseuid 41C94E84008E
     */
    public void setCurrOrderRepaySettleProfitLoss(double l_dblCurrOrderRepaySettleProfitLoss)
    {
        final String STR_METHOD_NAME = "setCurrOrderRepaySettleProfitLoss(double)";
        log.entering(STR_METHOD_NAME);

        this.currOrderRepaySettleProfitLoss = l_dblCurrOrderRepaySettleProfitLoss;

        log.exiting(STR_METHOD_NAME);
    }
}
@
