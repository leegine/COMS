head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuationPerProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �������Ə،��]���z(WEB3TPSecurityValuationPerProduct.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 �R�c�@@��i (FLJ) �V�K�쐬
                    2006/09/14 �Ԑi�@@     (���u)���f��No.42
 Revesion History : 2007/07/28 �Ј���     (���u)���f��No.117
 Revesion History : 2007/07/31 �Ј���     (���u)���f��No.145
 Revesion History : 2007/10/16 �g�E�N�|   (���u)���f��No.195
 Revesion History : 2008/01/22 �И���     (���u)���f��No.238
 Revesion History : 2008/11/21 �O���~��Y (SCS)���f��No.370, 371
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (�������Ə،��]���z)
 *
 * �������Ə،��]���z�̏���\������
 */
public class WEB3TPSecurityValuationPerProduct
    extends WEB3TPAssetValuation
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final Logit log =
        Logit.getInstance(WEB3TPSecurityValuationPerProduct.class);

    /**
     * (�Ώۖ���)
     */
    private WEB3TPSecurityValuationProduct product;

    /**
     * (�������Ƒ�p�،��]���z)
     * �v�Z�ςݖ������Ƒ�p�،��]���z
     */
    private double[] substituteValuationPrices;

    /**
     * (�������Ƒ�p�،��]���z(�O���P���]��))
     */
    private double[] prevPriceSubstituteValuation;

    /**
     * (�������Ɨa��،��]���z)
     * �v�Z�ςݖ������Ɨa��،��]���z
     */
    private double[] valuationPrices;

    /**
     * (�������Ɨa�莑�Y�]���z)
     * �v�Z�ςݖ������Ɨa�莑�Y�]���z
     */
    private double[] assetValuationPrices;

    /**
     * (�������Ɣ�������p�،��]���z)
     * �v�Z�ςݖ������Ɣ�������p�،��]���z
     */
    private double[] unExecSubstituteValuationPrices;

    /**
     * (�������Ɣ������a��،��]���z)
     * �v�Z�ςݖ������Ɣ������a��،��]���z
     */
    private double[] unExecValuationPrices;

    /**
     * (�����ۗL�ϓ�)
     */
    private List assetChanges;

    /**
     * (�U�֕ϓ�)
     */
    private List transferChanges;

    /**
     * (�o�ɕϓ�)
     */
    private List deliveryChanges;

    /**
     * (����ϓ�)
     */
    private List transactionChanges;

    /**
     * @@roseuid 41087D6100DD
     */
    public WEB3TPSecurityValuationPerProduct()
    {
        substituteValuationPrices = null;
        valuationPrices = null;
        assetValuationPrices = null;
        unExecSubstituteValuationPrices = null;
        unExecValuationPrices = null;
        assetChanges = new ArrayList();
        transferChanges = new ArrayList();
        deliveryChanges = new ArrayList();
        transactionChanges = new ArrayList();
    }

    /**
     * (create�������Ə،��]���z)<BR>
     * �������Ə،��]���z�C���X�^���X���쐬����B
     * @@param l_product - (�Ώۖ���)
     * @@param l_valuation - (�،��]��)
     * @@roseuid 40D7E2B103D6
     */
    public static WEB3TPSecurityValuationPerProduct create(
        WEB3TPSecurityValuationProduct l_product,
        WEB3TPSecurityValuation l_valuation)
    {
        WEB3TPSecurityValuationPerProduct l_vpp =
            new WEB3TPSecurityValuationPerProduct();
        l_vpp.setAccountInfo(l_valuation.getAccountInfo());
        l_vpp.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        l_vpp.setCalcCondition(l_valuation.getCalcCondition());
        l_vpp.setProduct(l_product);
        return l_vpp;
    }

    /**
     * (get�،��]���z)<BR>
     * �����̎w����ɂ����邠������̏،��]���z���擾����B<BR>
     * 1.�����̎w����ɂ����邠������̏،��]���z���擾����<BR>
     *   �ma. �������Ə،��]��.get�v�Z�ςݖ������Ə،��]���z()! = null �̏ꍇ�n<BR>
     *      (�Y�������̏،��]���z(T+0�`T+5)���łɌv�Z�ς�)<BR>
     *      �����̎w����ɂ����邠������̏،��]���z���擾����<BR>
     *      �،��]���z(����.�w���) =
     * �������Ə،��]��.get�v�Z�ςݖ������Ə،��]���z(����.�a��敪)<BR>
     * <BR>
     *   �mb. �������Ə،��]��.get�v�Z�ςݖ������Ə،��]���z() = null�̏ꍇ�n<BR>
     *     (�Y�������̏،��]���z(T+0�`T+5)���v�Z)<BR>
     *
     * �܂��Acalc�،��]���z(�a��敪)���\�b�h���R�[�����āA
     * �W�v�A�v�Z���ʂ�ۑ�����B<BR>
     *     �����̎w����ɂ����邠������̏،��]���z���擾����<BR>
     *     �،��]���z(����.�w���) =
     * �������Ə،��]��.get�v�Z�ςݖ������Ə،��]���z(����.�a��敪)<BR>
     * <BR>
     * 2.�،��]���z(����.�w���)��Ԃ�<BR>
     * <BR>
     * �� n�́A�����̎w����B<BR>
     *
     * @@param l_dblDate - (�w���)
     * @@param l_strDepositType - (�a��敪)
     * @@return double
     * @@roseuid 40E51A1500BD
     */
    public double getValuationPrice(Date l_datDate, String l_strDepositType)
    {
        // �]���z���v�Z����Ă��邩�H
        if (getValuationPrices(l_strDepositType) == null)
        {
            // ���v�Z�̏ꍇ�́A�]���z���v�Z����
            calcValuationPrice(l_strDepositType);
        }
        // �w����̕]���z��Ԃ�
        int l_intIndex = getCalcCondition().calcSpecifiedPoint(l_datDate);
        //�o�O Y00011  �،��]���z�̒[�����؂�̂đΉ�
        return Math.floor(getValuationPrices(l_strDepositType)[l_intIndex]);
    }

    /**
     * (get��p�،��]���z(�O���P���]��))<BR>
     * <BR>
     * �Y�������̑�p�،��]���z��ԋp����B<BR>
     * <BR>
     * �P�j�h�]���z[T+0..T+5]�h���擾����B <BR>
     * <BR>
     * �@@�|�h�]���z[T+0..T+5]�h = this.�������Ƒ�p�،��]���z(�O���P���]��)<BR>
     * <BR>
     * [a.�v�Z���ς݂̏ꍇ] <BR>
     * �@@�i�h�]���z[T+0..T+5]�h == null�j <BR>
     * <BR>
     * �@@�@@�E�h�]���z[T+0..T+5]�h���v�Z����B <BR>
     * �@@�@@�|this.calc�،��]���z(String = 1�F��p)���R�[�� <BR>
     * <BR>
     * �@@�@@�E�h�]���z[T+0..T+5]�h���擾����B <BR>
     * �@@�@@�@@�|�h�]���z[T+0..T+5]�h = this.�������Ƒ�p�،��]���z(�O���P���]��) <BR>
     * <BR>
     * �Q�j�Y�������̑�p�،��]���z��ԋp����B <BR>
     * <BR>
     * �@@�ԋp�l�F�h�]���z[T+0]�h<BR>
     * <BR>
     * @@return double
     */
    public double getPrevPriceSubstituteValuation()
    {
        // �]���z[T+0..T+5]�h = this.�������Ƒ�p�،��]���z(�O���P���]��)
        double[] l_dblPrices = this.prevPriceSubstituteValuation;

        // �v�Z���ς݂̏ꍇ
        if (l_dblPrices == null)
        {
            // this.calc�،��]���z(String = 1�F��p)���R�[��
            this.calcValuationPrice(WEB3TPDepositTypeDef.SUBSTITUTE);

            // �]���z[T+0..T+5]�h = this.�������Ƒ�p�،��]���z(�O���P���]��)
            l_dblPrices = this.prevPriceSubstituteValuation;
        }

        // �Y�������̑�p�،��]���z��ԋp����
        return Math.floor(l_dblPrices[0]);
    }

    /**
     * (get�a�莑�Y�]���z) <BR>
     * <BR>
     * ����.�w����ɂ�����A�Y�������̗a�莑�Y�]���z��ԋp����B<BR>
     * <BR>
     * �P�j�h�����̗a�莑�Y�]���z[T+0�`T+5]�h���v�Z����B <BR>
     * <BR>
     * �@@[a.�v�Z���ς݂̏ꍇ] <BR>
     * �@@�ithis.�����̗a�莑�Y�]���z == null�j<BR>
     * <BR>
     * �@@�@@�E�h�����̗a�莑�Y�]���z[T+0�`T+5]�h���v�Z����B<BR>
     * �@@�@@�|�h�����̗a�莑�Y�]���z[T+0�`T+5]�h = this.calc�����ۗL�]���z<�a�莑�Y>()<BR>
     * <BR>
     * �@@�@@�E�v�Z�����h�����̗a�莑�Y�]���z[T+0�`T+5]�h���A�v���p�e�B�ɃZ�b�g����B<BR>
     * �@@�@@�|this.�����̗a�莑�Y�]���z = �h�����̗a�莑�Y�]���z[T+0�`T+5]�h<BR>
     * <BR>
     * <BR>
     * �Q�j����.�w����ɂ�����A�Y�������̗a�莑�Y�]���z��ԋp����B<BR>
     * <BR>
     * �@@�ԋp�l�Fthis.�����̗a�莑�Y�]���z[(�w���(*)] <BR>
     * <BR>
     * �@@(*)�w��� = this.get�]�͌v�Z����().calc�w���(:Date = ����.�w���)<BR>
     * <BR>
     * �@@���߂�l�͏����_�ȉ���؂�̂Ă�B<BR>
     * <BR>
     * @@param l_dblDate - (�w���)
     * @@return double
     */
    public double getAssetValuationPrice(Date l_datDate)
    {
        // �]���z���v�Z����Ă��邩�H
        if (assetValuationPrices == null)
        {
            //�z��錾
            double[] l_valuationPrices = new double[6];

            // �����ۗL�]���z�擾
            double[] l_assetValuationPrices = calcDepositAssetValuationPrice();

            // �������Ɨa�莑�Y�]���z���W�v
            for (int i = 0; i < l_valuationPrices.length; i++)
            {
                //�W�v���s��
                l_valuationPrices[i] = l_assetValuationPrices[i];

                log.debug(
                    " DepositAssetValuationPrice["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);
            }

            //�W�v���ʂ�ۑ�
            assetValuationPrices = l_valuationPrices;

        }
        // �����ɑ΂���w�����Ԃ�
        int l_intIndex = getCalcCondition().calcSpecifiedPoint(l_datDate);
        //�a�莑�Y�]���z��ݒ肷��
        return Math.floor(assetValuationPrices[l_intIndex]);
    }

    /**
     * (get�������،��]���z)<BR>
     * �����̎w����ɂ����邠������̔������،��]���z���擾����B<BR>
     * 1.�����̎w����ɂ����邠������̔������،��]���z���擾����<BR>
     *   �ma. �������Ə،��]��.get�v�Z�ςݖ������Ɣ������،��]���z()! = null �̏ꍇ�n<BR>
     *      (�Y�������̔������،��]���z(T+0�`T+5)���łɌv�Z�ς�)<BR>
     *      �����̎w����ɂ����邠������̔������،��]���z���擾����<BR>
     *      �،��]���z(����.�w���) =
     * �������Ə،��]��.get�v�Z�ςݖ������Ɣ������،��]���z(����.�a��敪)<BR>
     * <BR>
     *   �mb. �������Ə،��]��.get�v�Z�ςݖ������Ɣ������،��]���z() = null�̏ꍇ�n<BR>
     *     (�Y�������̔������،��]���z(T+0�`T+5)���v�Z)<BR>
     *
     * �܂��Acalc����]���z<�����ȍ~>(�a��敪)���\�b�h���R�[�����āA
     * �W�v�A�v�Z���ʂ�ۑ�����B<BR>
     *     �����̎w����ɂ����邠������̔������،��]���z���擾����<BR>
     *     �������،��]���z(����.�w���) =
     * �������Ə،��]��.get�v�Z�ςݖ������Ɣ������،��]���z(����.�a��敪)<BR>
     * <BR>
     * 2.�������،��]���z(����.�w���)��Ԃ�<BR>
     * <BR>
     * �� n�́A�����̎w����B<BR>
     *
     * @@param l_datDate - (�w���)
     * @@param l_strDepositType - (�a��敪)
     * @@return double
     */
    public double getUnExecValuationPrice(Date l_datDate, String l_strDepositType)
    {
        // �������]���z���v�Z����Ă��邩�H
        if (getUnExecValuationPrices(l_strDepositType) == null)
        {
            // ���v�Z�̏ꍇ�́A�]���z���v�Z����

            // ����蔃�t���(�|�ڍl���ς�)
            double[] l_unexecutedValuationPrices =
                this.calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.BUY);

            if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
            {
                unExecSubstituteValuationPrices = l_unexecutedValuationPrices;
            }
            else
            {
                unExecValuationPrices = l_unexecutedValuationPrices;
            }
        }
        // �w����̕]���z��Ԃ�
        int l_intIndex = getCalcCondition().calcSpecifiedPoint(l_datDate);

        return Math.floor(getUnExecValuationPrices(l_strDepositType)[l_intIndex]);
    }

    /**
     * (set�Ώۖ���)<BR>
     * �Ώۖ�����ݒ肷��B
     * @@param l_product - (�Ώۖ���)
     * @@roseuid 40B449E302D2
     */
    public void setProduct(WEB3TPSecurityValuationProduct l_product)
    {
        product = l_product;
    }

    /**
     * (add�،��ϓ�)<BR>
     * �،��ϓ���ǉ�����B
     * @@param l_securityChange - (�،��ϓ�)
     * @@roseuid 40D7E397032A
     */
    public void addSecurityChange(WEB3TPSecurityChange l_securityChange)
    {
        final String STR_METHOD_NAME = "addSecurityChange(WEB3TPSecurityChange l_securityChange)";

        if (l_securityChange instanceof WEB3TPSecurityAssetChange)
        {
            assetChanges.add(l_securityChange);
        }
        else if (l_securityChange instanceof WEB3TPSecurityTransferChange)
        {
            transferChanges.add(l_securityChange);
        }
        else if (l_securityChange instanceof WEB3TPSecurityDeliveryChange)
        {
            deliveryChanges.add(l_securityChange);
        }
        else if (l_securityChange instanceof WEB3TPSecurityTransactionChange)
        {
            transactionChanges.add(l_securityChange);
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (calc�����ۗL����<�m��>) <BR>
     * <BR>
     * �����̊����ۗL����<�m��>���A����.�w������ƏW�v����B <BR>
     * <BR>
     * 1.�����ۗL�ϓ��ꗗ<�m��>�擾�B <BR>
     * <BR>
     * 2.�ꗗ�ɂ�������ۗL�ϓ��̐��ʂ��ȉ������ŏW�v���� <BR>
     * <BR>
     *   ��<BR>�ʏW�v =��(*)�����ۗL�ϓ�.���� <BR>
     *    (*)�W�v�����F <BR>
     *       �����ۗL�ϓ��ꗗ<�m��>�ɂ�������ۗL�ϓ� <BR>
     * �@@ �@@ ���A�����ۗL�ϓ�.is�ϓ����Ԓ�(n)=true <BR>
     * <BR>
     *    (*)n�̐ݒ�l<BR>
     * �@@�@@ [a.�����ۗL�ϓ�.�a��敪 == "0:�ی�" ���� �����ۗL�ϓ�.is�����V�����敪 == true]<BR>
     * �@@�@@ �@@n = ����.�w���+1 ���w���==T+5�̏ꍇ�ˎw���+1=T+5�Ƃ���<BR>
     * �@@�@@ [a.�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@ n = ����.�w���<BR>
     * <BR>
     * 3 ���ʏW�v��Ԃ��B<BR> 
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@ <BR>
     * �E�a��敪�E�E�E����.�a��敪 <BR>
     * �E�����ۗL�ϓ��ꗗ<�m��>�E�E�E�������Ə،��]��.get�����ۗL�ϓ��ꗗ<�m��>(�a��敪) <BR>
     * <BR>
     * @@param l_strDepositType - �a��敪
     * @@param l_dblDate - �w���
     * @@return double
     */
    public double calcAssetQuantity(Date l_datDate, String l_strDepositType)
    {
        log.debug(
            "Calculating asset quantities, condition=[DepositType="
            + l_strDepositType
            + "],"
            + "Date=[" + l_datDate
            + "]");

        double l_dblQty = 0.0d;
        List l_changes = getAssetChanges(l_strDepositType);

        /*
         * �w���+1���擾����B
         * ���w���>=T+5�̏ꍇ�ˎw���+1=T+5�Ƃ���B
         */
        //�w���+1
        Date l_datNextDate;

        //�w���>=T+5�̏ꍇ
        if(l_datDate.getTime() >= getCalcCondition().getBizDate(
            WEB3TPSpecifiedPointDef.T_5).getTime())
        {
            l_datNextDate = l_datDate;
        }
        //�ȊO(�w���<T+5)�̏ꍇ
        else
        {
            l_datNextDate = this.getCalcCondition().rollBizDate(l_datDate, 1);
        }

        for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityAssetChange l_change =
                (WEB3TPSecurityAssetChange) l_it.next();

            //[a.�����ۗL�ϓ�.�a��敪 == "0:�ی�" ���� �����ۗL�ϓ�.is�����V�����敪 == true]
            if(WEB3TPDepositTypeDef.TRUST.equals(l_change.getDepositType()) == true
                && l_change.isSplitNewStock() == true)
            {
                //�����ۗL�ϓ�.is�ϓ����Ԓ�(�w���+1)=true�̏ꍇ
                if (l_change.isDuringReflectTime(l_datNextDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }
            //�ȊO�̏ꍇ
            else
            {
                //�����ۗL�ϓ�.is�ϓ����Ԓ�(�w���)=true�̏ꍇ
                if (l_change.isDuringReflectTime(l_datDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }
        }

        return l_dblQty;

    }

    /**
     * (calc�m��������<�����ȍ~>)<BR>
     * ����.�w����̊m��������<�����ȍ~>���W�v����B<BR>
     * <BR>
     * �P�j�������ϐ��ʂ��W�v����B<BR>
     * �@@�P�|�P�j����ϓ��ꗗ<�����ȍ~>���擾�B<BR>
     * �@@�@@���������Ə،��]��. ����ϓ��ꗗ<�����ȍ~><BR>
     * <BR>
     * �@@�P�|�Q�j�ꗗ�ɂ������ϓ��̐��ʂ��ȉ������ŏW�v����<BR>
     *   �@@�@@�@@�����̎w������ƏW�v�̏���(n =�w���)<BR>
     * <BR>
     * �@@�@@�@@�@@[����ϓ�.�����敪=1:BUY�̏ꍇ]<BR>
     * �@@  �@@�@@�@@�������ϐ��� =��(*)����ϓ�.���� <BR>
     * <BR>
     * �@@�@@�@@�@@[����ϓ�.�����敪=2:BUY�̏ꍇ]<BR>
     * �@@  �@@�@@�@@�������ϐ��� =��(*)�i�|����ϓ�.���ʁj <BR>
     * <BR>
     * �@@�@@�@@�@@(*)�W�v�����F<BR>
     * �@@�@@�@@�@@�@@����ϓ�.get��n��() <= ����.�w����@@���A<BR>
     * �@@�@@�@@�@@�@@�i�@@<BR>
     * �@@�@@�@@�@@�@@�@@�i����ϓ�.���敪=���ρj�@@�܂��́@@<BR>
     * �@@�@@�@@�@@�@@�@@�i����ϓ�.�����J�e�S��=7�F�����E���n�j�j<BR>
     * �@@�@@�@@�@@�@@���A <BR>
     * �@@�@@�@@�@@�@@����ϓ�.�ŋ敪��5�F�X�g�b�N�I�v�V���� <BR>
     * <BR>
     * �Q�j�m��������<�����ȍ~>��ԋp����B<BR>
     * �@@[�ԋp�l]�@@�������ϐ���<BR>
     * <BR>
     * @@param l_dblDate - �w���
     * @@return double
     */
    public double calcExecutedTransactionQuantity(Date l_datDate)
    {
        log.debug(
            "Calculating Executed Transaction quantities, "
            + "Date=[" + l_datDate
            + "]");
        
        /*
         * �������ϐ��ʂ��W�v����B
         */
        
        //�m��������<�����ȍ~>
        double l_dblQty = 0.0d;
        for(Iterator l_it = this.transactionChanges.iterator(); l_it.hasNext();)
        {
            WEB3TPSecurityTransactionChange l_change = (WEB3TPSecurityTransactionChange) l_it
                .next();

            //����ϓ�.get��n��() <= ����.�w��� ���A
            //�i�i����ϓ�.���敪=���ρj �܂��� �i����ϓ�.�����J�e�S��=7�F�����E���n�j�j
            // ���A ����ϓ�.�ŋ敪��5�F�X�g�b�N�I�v�V���� 
            if(l_change.getDeliveryDate().getTime() <= l_datDate.getTime()&& 
                (l_change.getExecType().equals(WEB3TPExecTypeDef.EXECUTED) || 
                OrderCategEnum.SWAP_MARGIN.equals(l_change.getOrderCateg())) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                //���t�̏ꍇ
                //(����ϓ�.�����敪 == 1:���t)
                if(l_change.getSide().equals(SideEnum.BUY))
                {
                    //�m��������<�����ȍ~>�ɕϓ����ʂ����Z����B
                    //(�m��������<�����ȍ~> = �m��������<�����ȍ~> + ����ϓ�.�ϓ�����)
                    l_dblQty += l_change.getQuantity();

                }
                //���t�̏ꍇ
                //(����ϓ�.�����敪 == 2:���t)
                else if(l_change.getSide().equals(SideEnum.SELL))
                {
                    //�m��������<�����ȍ~>�ɕϓ����ʂ����Z����B
                    //(�m��������<�����ȍ~> = �m��������<�����ȍ~> - ����ϓ�.�ϓ�����)
                    l_dblQty -= l_change.getQuantity();

                }
            }
        }
        //�m��������<�����ȍ~>��ԋp����B
        return l_dblQty;
    }
    
    /**
     * (calc�،��]���z)<BR>
     * <BR>
     * �����̏،��]���z�iT+0�`T+5�j���v�Z����B<BR>
     * <BR>
     * �P�j�،��]���z[T+0..5]�A�������،��]���z[T+0..5]�A<BR>
     * �@@�@@�،��]���z�i�O���P���]���j[T+0..5]���v�Z����B<BR> 
     * <BR>
     * �@@[a.�Ώۖ���.�����^�C�v=�����̏ꍇ]<BR>
     * �@@�ithis.�Ώۖ���.get�����^�C�v == 1:�����j<BR>
     * <BR>
     * �@@�@@�@@�����ۗL����[T+0..5]�A�U�֐���[T+0..5]�A�o�ɐ���[T+0..5]���擾����<BR>
     * <BR>
     * �@@�@@�@@�|�����ۗL����[T+0..5] = this.calc�����ۗL����<�m��>(:String = ����.�a��敪)<BR>
     * �@@�@@�@@�|�U�֐���[T+0..5] = this.calc�U�֐���<�����ȍ~>(:String = ����.�a��敪)<BR>
     * �@@�@@�@@�|�o�ɐ���[T+0..5] = this.calc�o�ɐ���<�����ȍ~>(:String = ����.�a��敪)<BR>
     * <BR>
     * �@@�@@�A���ϔ��t����[T+0..5]�A���ϔ��t���[T+0..5]�A����蔃�t����[T+0..5]�A<BR>
     * �@@�@@�@@�@@����蔃�t���[T+0..5] �A����蔄�t����[T+0..5]�A���ϔ��t����[T+0..5]���擾����<BR>
     * <BR>
     * �@@�@@�@@�|���ϔ��t����[T+0..5] = this.calc�������<�����ȍ~>(:String = ����.�a��敪, :String = 2:����, :SideEnum = 1:���t)<BR>
     * �@@�@@�@@�|���ϔ��t����[T+0..5] = this.calc�������<�����ȍ~>(:String = ����.�a��敪, :String = 2:����, :SideEnum = 2:���t)<BR>
     * �@@�@@�@@�|����ϔ��t����[T+0..5] = this.calc�������<�����ȍ~>(:String = ����.�a��敪, :String = 1:�����, :SideEnum = 2:���t)<BR>
     * <BR>
     * �@@�@@�@@�|���ϔ��t���[T+0..5] = this.calc����]���z<�����ȍ~>(:String = ����.�a��敪, :String = 2:����, :SideEnum = 1:���t)<BR>
     * �@@�@@�@@�|����ϔ��t���[T+0..5] = this.calc����]���z<�����ȍ~>(:String = ����.�a��敪, :String = 1:�����, :SideEnum = 1:���t)<BR>
     * <BR>
     * �@@�@@�B���t����[T+0...5]�A�ۗL����[T+0...5]�A���t���ʗ]�蕪[T+0...5]�A��������ϔ��t����[T+0...5]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�|���t����[T+0...5] = ���ϔ��t����[T+0..5] + ����ϔ��t����[T+0..5]<BR>
     * �@@�@@�@@�|�ۗL����[T+0...5] = �����ۗL����[T+0..5] + �U�֐���[T+0..5] - �o�ɐ���[T+0..5] - ���t����[T+0...5]<BR>
     * �@@�@@�@@�|���t���ʗ]�蕪[T+0...5] = Abs(MIN(�ۗL����[T+0...5],0))<BR>
     * �@@�@@�@@�|��������ϔ��t����[T+0...5] = Max(���ϔ��t����[T+0...5] - ���t���ʗ]�蕪[T+0...5], 0)<BR>
     * <BR>
     * �@@�@@�C�]���|�ځA�]���P���A�O���P�����擾����B<BR>
     * <BR>
     * �@@�@@�@@�|�]���|�� = this.�Ώۖ���.get�،��]���|��(:String = ����.�a��敪)<BR>
     * �@@�@@�@@�|�]���P�� = this.�Ώۖ���.get�]���P��()<BR>
     * �@@�@@�@@�|�O���P�� = this.�Ώۖ���.get�O���P��()<BR>
     * <BR>
     * �@@�@@�D�،��]���z[T+0..2]�A�،��]���z�i�O���P���]���j[T+0..2]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�|�،��]���z[T+0..2] = MAX(�ۗL����[T+0..2], 0) �~ �]���|�� �~ �]���P��<BR>
     * �@@�@@�@@�|�،��]���z�i�O���P���]���j[T+0..2] = MAX(�ۗL����[T+0..2], 0) �~ �]���|�� �~ �O���P��<BR>
     * <BR>
     * �@@�@@�E�،��]���z[T+3..5]�A�،��]���z�i�O���P���]���j[T+3..5]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@[b.�����������ԑт̏ꍇ]<BR>
     * �@@�@@�@@�ithis.get�]�͌v�Z����().is����������t�J�n�敪<��������>() == false�j<BR>
     * <BR>
     * �@@�@@�@@�@@�|�،��]���z[T+3..5] = MAX(�ۗL����[T+3..5], 0) �~ �]���|�� �~ �]���P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ���ϔ��t���[T+3..5] �~ ��������ϔ��t����[T+3..5] �� ���ϔ��t����[T+3..5]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ����ϔ��t���[T+3..5]<BR> 
     * <BR>
     * �@@�@@�@@�@@�|�،��]���z�i�O���P���]���j[T+3..5] = MAX(�ۗL����[T+3..5], 0) �~ �]���|�� �~ �O���P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ���ϔ��t���[T+3..5] �~ ��������ϔ��t����[T+3..5] �� ���ϔ��t����[T+3..5]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ����ϔ��t���[T+3..5]<BR>
     * <BR>
     * �@@�@@�@@[b.�����������ԑт̏ꍇ]<BR>
     * �@@�@@�@@�ithis.get�]�͌v�Z����().is����������t�J�n�敪<��������>() == true�j<BR> 
     * <BR>
     * �@@�@@�@@�@@�|�،��]���z[T+3..5] = (MAX(�ۗL����[T+3..5], 0) + ��������ϔ��t����[T+3..5]) �~ �]���|�� �~ �]���P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ����ϔ��t���[T+3..5]<BR>
     * <BR>
     * �@@�@@�@@�@@�|�،��]���z�i�O���P���]���j[T+3..5] = (MAX(�ۗL����[T+3..5], 0) + ��������ϔ��t����[T+3..5]) �~ �]���|�� �~ �O���P��<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ ����ϔ��t���[T+3..5]<BR>
     * <BR>
     * �@@�@@�F�������،��]���z[T+0..5]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�|�������،��]���z[T+0..5] = ����ϔ��t���[T+0..5]<BR>
     * <BR>
     * <BR>
     * �@@[a.�Ώۖ���.�����^�C�v=�ݐϓ����̏ꍇ]<BR>
     * �@@�ithis.�Ώۖ���.get�����^�C�v == 7:�ݐϓ��M�j<BR>
     * <BR>
     * �@@�@@�@@�����ۗL�]���z[T+0..5]�AGP�����[T+0..5]�AGP���t���[T+0..5]<BR>
     * <BR>
     * �@@�@@�@@�|�����ۗL�]���z[T+0..5] = this.calc�����ۗL�]���z<�m��>(:String = ����.�a��敪)<BR>
     * �@@�@@�@@�|GP�����[T+0..5] = this.calc����]���z<�����ȍ~>(:String = ����.�a��敪, :String = 1:�����, :SideEnum = 2:���t)<BR>
     * �@@�@@�@@�|GP���t���[T+0..5] = this.calc����]���z<�����ȍ~>(:String = ����.�a��敪, :String = 1:�����, :SideEnum = 1:���t)<BR>
     * <BR>
     * �@@�@@�A�،��]���z[T+0..5]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�|�،��]���z[T+0..5] = MAX((�����ۗL�]���z[T+0..5] - GP�����[T+0..5] + GP���t���[T+0..5]), 0)<BR>
     * <BR>
     * �@@�@@�B�������،��]���z[T+0..5]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�|�������،��]���z[T+0..5] = 0<BR>
     * <BR>
     * <BR>
     * �@@[a.�Ώۖ���.�����^�C�v=���M,��,���̑��،��̏ꍇ]<BR>
     * �@@�ithis.�Ώۖ���.get�����^�C�v == 2:�� ||<BR>
     * �@@�@@�@@this.�Ώۖ���.get�����^�C�v == 3:�����M�� ||<BR>
     * �@@�@@�@@�@@this.�Ώۖ���.get�����^�C�v == 0:���̑��j<BR>
     * <BR>
     * �@@�@@�@@�����ۗL�]���z[T+0..5]�A�U�֕]���z[T+0..5]<BR>
     * <BR>
     * �@@�@@�@@�|�����ۗL�]���z[T+0..5] = this.calc�����ۗL�]���z<�m��>(:String = ����.�a��敪)<BR>
     * �@@�@@�@@�|�U�֕]���z[T+0..5] = this.calc�U�֕]���z<�����ȍ~>(:String = ����.�a��敪)<BR>
     * <BR>
     * �@@�@@�A�،��]���z[T+0..5]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�|�،��]���z[T+0..5] = MAX((�����ۗL�]���z[T+0..5] + �U�֕]���z[T+0..5]), 0)<BR>
     * �@@�@@�@@�|�،��]���z�i�O���P���]���j[T+0..5] = MAX((�����ۗL�]���z[T+0..5] + �U�֕]���z[T+0..5]), 0)<BR>
     * <BR>
     * �@@�@@�B�������،��]���z[T+0..5]���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�|�������،��]���z[T+0..5] = 0<BR>
     * <BR>
     * <BR>
     * �Q�j�v�Z�����A�،��]���z[T+0..5]�A�������،��]���z[T+0..5]�A�،��]���z�i�O���P���]���j[T+0..5]���v���p�e�B�ɃZ�b�g����B<BR> 
     * <BR>
     * �@@[a.��p�̏ꍇ]<BR>
     * �@@�i����.�a��敪 == 1:��p�j<BR>
     * <BR>
     * �@@�@@�|this.�������Ƒ�p�،��]���z = �،��]���z[T+0..5]<BR>
     * �@@�@@�|this.�������Ɣ�������p�،��]���z = �������،��]���z[T+0..5]<BR>
     * �@@�@@�|this.�������Ƒ�p�،��]���z�i�O���P���]���j = �،��]���z�i�O���P���]���j[T+0..5]<BR>
     * <BR>
     * �@@[a.�ȊO�i�ی�́j�ꍇ]<BR>
     * <BR>
     * �@@�@@�|this.�������Ɨa��،��]���z = �،��]���z[T+0..5]<BR>
     * �@@�@@�|this.�������Ɣ������a��،��]���z = �������،��]���z[T+0..5]<BR>
     * <BR>
     * @@param l_strDepositType - (�a��敪)
     */
    private void calcValuationPrice(String l_strDepositType)
    {
        final String STR_METHOD_NAME = "calcValuationPrice(String l_strDepositType)";

        log.debug(
            ">>>>> Calculationg valuation prices. condition=[DepositType="
            + l_strDepositType
            + "] <<<<<");

        //�،��]���z
        double[] l_valuationPrices = new double[6];

        //�،��]���z�i�O���P���]���j
        double[] l_dblValuationPrices1 = new double[6];

        // �������،��]���p
        double[] l_unexecutedValuationPrices = new double[6];
        
        ProductTypeEnum l_productType = product.getProductType();

        log.debug(" product type=" + l_productType);

        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {

            // �����ۗL����
            double[] l_assetQuantities = calcAssetQuantity(l_strDepositType);
            // �U�֐���
            double[] l_transferQuantities =
                calcTransferQuantity(l_strDepositType);
            // �o�ɐ���
            double[] l_deliveryQuantities =
                calcDeliveryQuantity(l_strDepositType);
            // ���ϔ��t����
            double[] l_executedSellOrderQuantities =
                calcTransactionQuantity(
                l_strDepositType,
                WEB3TPExecTypeDef.EXECUTED,
                SideEnum.SELL);
            // ����蔄�t����
            double[] l_unexecutedSellOrderQuantities =
                calcTransactionQuantity(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.SELL);
            // ���ϔ��t����
            double[] l_executedBuyOrderQuantities =
                this.calcTransactionQuantity(
                l_strDepositType,
                WEB3TPExecTypeDef.EXECUTED,
                SideEnum.BUY);
            // ����蔃�t���
            double[] l_unexecutedBuyOrderPrices =
                this.calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.BUY);
            // ���ϔ��t���
            double[] l_executedBuyOrderPrices =
                this.calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.EXECUTED,
                SideEnum.BUY);

            // ���t����
            double[] l_sellOrderQuantities = new double[6];
            for (int i = 0; i < l_sellOrderQuantities.length; i++)
            {
                // ���t���� = ���ϔ��t���� - ����蔄�t����
                l_sellOrderQuantities[i] =
                    l_executedSellOrderQuantities[i]
                    + l_unexecutedSellOrderQuantities[i];

                log.debug(
                    " SellOrderQuantities["
                    + i
                    + "]="
                    + l_sellOrderQuantities[i]);

            }

            // �ۗL����
            double[] l_adjustedAssetQuantities = new double[6];
            for (int i = 0; i < l_adjustedAssetQuantities.length; i++)
            {
                // �ۗL���� = �����ۗL���� + �U�֐��� - �o�ɐ��� - ���t����
                l_adjustedAssetQuantities[i] =
                    l_assetQuantities[i]
                    + l_transferQuantities[i]
                    - l_deliveryQuantities[i]
                    - l_sellOrderQuantities[i];

                log.debug(
                    " AdjustedAssetQuantities["
                    + i
                    + "]="
                    + l_adjustedAssetQuantities[i]);

            }

            // ���t���ʗ]�蕪�i�f�t�H���g=0�j
            double l_dblSellOrderSurplus[] = new double[6];
            // �ۗL����[T+5] < 0 �̏ꍇ
            for (int i = 0; i < 6; i++)
            {
                if (l_adjustedAssetQuantities[i] < 0)
                {
                    // ���t���ʗ]�蕪 = Abs(�ۗL����[T+5])
                    l_dblSellOrderSurplus[i] = Math.abs(l_adjustedAssetQuantities[i]);
                }
            }

            log.debug(" SellOrderSurplus=" + l_dblSellOrderSurplus);

            // �ۗL���� = Max(�ۗL����, 0)
            for (int i = 0; i < l_adjustedAssetQuantities.length; i++)
            {
                l_adjustedAssetQuantities[i] =
                    Math.max(l_adjustedAssetQuantities[i], 0);

                log.debug(
                    " AdjustedAssetQuantities["
                    + i
                    + "]="
                    + l_adjustedAssetQuantities[i]);

            }

            // �]���|��
            double l_dblValuationRatio =
                product.getValuationRatio(l_strDepositType);
            // �]���P��
            double l_dblUnitPrice = product.getUnitPrice();

            // �O���P��
            double l_dblPrePrice = product.getPrePrice();

            log.debug(" ValuationRatio=" + l_dblValuationRatio);
            log.debug(" UnitPrice=" + l_dblUnitPrice);

            // �،��]���z[T+0..2]�A�،��]���z�i�O���P���]���j[T+0..2]���v�Z����
            for (int i = 0; i < 3; i++)
            {
                //�،��]���z[T+0..2] = MAX(�ۗL����[T+0..2], 0) �~ �]���|�� �~ �]���P��
                l_valuationPrices[i] =
                    l_adjustedAssetQuantities[i]
                    * l_dblValuationRatio
                    * l_dblUnitPrice;

                //�،��]���z�i�O���P���]���j[T+0..2] = MAX(�ۗL����[T+0..2], 0) �~ �]���|�� �~ �O���P��
                l_dblValuationPrices1[i] =
                    l_adjustedAssetQuantities[i]
                    * l_dblValuationRatio
                    * l_dblPrePrice;

                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

            }

            boolean l_isEquityExecutionDiv =
                getCalcCondition().isEquityNextDayOrderStartDiv();
            log.debug(" isEquityExecutionDiv=" + l_isEquityExecutionDiv);

            // �،��]���z[T+3..5]�A�،��]���z�i�O���P���]���j[T+3..5]���v�Z����
            for (int i = 3; i < l_valuationPrices.length; i++)
            {
                // ��������ϔ��t���� = Max(���ϔ��t���� - ���t���ʗ]�蕪, 0)
                double l_dblAdjustedExecutedBuyOrderQuantity =
                    Math.max(
                    (l_executedBuyOrderQuantities[i]
                     - l_dblSellOrderSurplus[i]),
                    0.0);

                log.debug(
                    " AdjustedExecutedBuyOrderQuantities["
                    + i
                    + "]="
                    + l_dblAdjustedExecutedBuyOrderQuantity);

                if (!l_isEquityExecutionDiv)
                {

                    log.debug(
                        " ExecutedBuyOrderPrices["
                        + i
                        + "]"
                        + l_executedBuyOrderPrices[i]);
                    log.debug(
                        " ExecutedBuyOrderQuantities["
                        + i
                        + "]"
                        + l_executedBuyOrderQuantities[i]);
                    log.debug(
                        " UnexecutedBuyOrderPrices["
                        + i
                        + "]"
                        + l_unexecutedBuyOrderPrices[i]);

                    // �]���z = �ۗL���� * �]���P�� * �]���|��
                    //          + (���ϔ��t��� * ��������ϔ��t���� / ���ϔ��t����
                    //             + ����蔃�t���) * �]���|��
                    l_valuationPrices[i] =
                        (l_adjustedAssetQuantities[i]
                         * l_dblUnitPrice
                         * l_dblValuationRatio)
                        + ( ( (l_executedBuyOrderQuantities[i] == 0
                               ? 0
                               : l_executedBuyOrderPrices[i]
                               * l_dblAdjustedExecutedBuyOrderQuantity
                               / l_executedBuyOrderQuantities[i])
                             + l_unexecutedBuyOrderPrices[i])
                           );
//                           * l_dblValuationRatio);

                    //�،��]���z�i�O���P���]���j[T+3..5] = MAX(�ۗL����[T+3..5], 0) �~ �]���|�� �~ �O���P��
                    //         + ���ϔ��t���[T+3..5] �~ ��������ϔ��t����[T+3..5] �� ���ϔ��t����[T+3..5]
                    //         + ����ϔ��t���[T+3..5]
                    l_dblValuationPrices1[i] =
                        (l_adjustedAssetQuantities[i]
                         * l_dblPrePrice
                         * l_dblValuationRatio)
                        + ( ( (l_executedBuyOrderQuantities[i] == 0
                               ? 0
                               : l_executedBuyOrderPrices[i]
                               * l_dblAdjustedExecutedBuyOrderQuantity
                               / l_executedBuyOrderQuantities[i])
                             + l_unexecutedBuyOrderPrices[i])
                           );
                }
                else
                {
                    // �ۗL���� = �ۗL���� - ��������ϔ��t����
                    double l_dblAdjustedAssetQuantity =
                        l_adjustedAssetQuantities[i]
                        + l_dblAdjustedExecutedBuyOrderQuantity;

                    log.debug(
                        " AdjustedAssetQuantities["
                        + i
                        + "]="
                        + l_adjustedAssetQuantities[i]);

                    log.debug(
                        " UnexecutedBuyOrderPrices["
                        + i
                        + "]"
                        + l_unexecutedBuyOrderPrices[i]);

//                    // �]���z = (�ۗL���� * �]���P�� + ����蔃�t���) * �]���|��
//                    l_valuationPrices[i] =
//                        (l_dblAdjustedAssetQuantity * l_dblUnitPrice
//                         + l_unexecutedBuyOrderPrices[i])
//                        * l_dblValuationRatio;
                        
                    // �]���z = (�ۗL���� * �]���P�� * �]���|��) + ����蔃�t���
                    l_valuationPrices[i] =
                        (l_dblAdjustedAssetQuantity * l_dblUnitPrice * l_dblValuationRatio)
                         + l_unexecutedBuyOrderPrices[i];

                    // �،��]���z�i�O���P���]���j[T+3..5] = (MAX(�ۗL����[T+3..5], 0)
                    //         + ��������ϔ��t����[T+3..5]) �~ �]���|�� �~ �O���P��
                    //         + ����ϔ��t���[T+3..5]
                    l_dblValuationPrices1[i] =
                        l_dblAdjustedAssetQuantity
                        * l_dblValuationRatio
                        * l_dblPrePrice
                        + l_unexecutedBuyOrderPrices[i];
                }

                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

                // �������،��]��
                l_unexecutedValuationPrices[i] = l_unexecutedBuyOrderPrices[i];

                log.debug(" UnExecValuationPrices[" + i + "]=" + l_unexecutedValuationPrices[i]);
            }
        }
        else if (ProductTypeEnum.RUITO.equals(l_productType))
        {

            // �����ۗL�]���z
            double[] l_assetValuationPrices =
                calcAssetValuationPrice(l_strDepositType);

            // GP�����
            double[] l_gpSellTransactionPrices =
                calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.SELL);

            // GP���t���
            double[] l_gpBuyTransactionPrices =
                calcTransactionValuationPrice(
                l_strDepositType,
                WEB3TPExecTypeDef.UNEXECUTED,
                SideEnum.BUY);

            // �������Ə،��]���z���W�v
            for (int i = 0; i < l_valuationPrices.length; i++)
            {

                l_valuationPrices[i] = Math.max(
                    l_assetValuationPrices[i]
                    - l_gpSellTransactionPrices[i]
                    + l_gpBuyTransactionPrices[i]
                    , 0);

                log.debug(
                    " AssetValuationPrices["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(
                    " GPSellPrices[" + i + "]=" + l_gpSellTransactionPrices[i]);
                log.debug(
                    " GPBuyPrices[" + i + "]=" + l_gpBuyTransactionPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

            }

        }
        else if (
            ProductTypeEnum.BOND.equals(l_productType)
            || ProductTypeEnum.MUTUAL_FUND.equals(l_productType)
            || ProductTypeEnum.OTHER.equals(l_productType))
        {

            // �����ۗL�]���z
            double[] l_assetValuationPrices =
                calcAssetValuationPrice(l_strDepositType);

            // �U�֕]���z
            double[] l_transferValuationPrices =
                calcTransferValuationPrice(l_strDepositType);

            // �������Ə،��]���z���W�v
            for (int i = 0; i < l_valuationPrices.length; i++)
            {

                l_valuationPrices[i] =
                    Math.max(l_assetValuationPrices[i] + l_transferValuationPrices[i], 0);

                log.debug(
                    " AssetValuationPrices["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(
                    " TransferValuationPrices["
                    + i
                    + "]="
                    + l_transferValuationPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_valuationPrices[i]);

            }

            // �،��]���z�i�O���P���]���j���W�v
            for (int i = 0; i < l_dblValuationPrices1.length; i++)
            {

                l_dblValuationPrices1[i] =
                    Math.max(l_assetValuationPrices[i] + l_transferValuationPrices[i], 0);

                log.debug(
                    " AssetValuationPrices["
                    + i
                    + "]="
                    + l_assetValuationPrices[i]);
                log.debug(
                    " TransferValuationPrices["
                    + i
                    + "]="
                    + l_transferValuationPrices[i]);
                log.debug(" ValuationPrices[" + i + "]=" + l_dblValuationPrices1[i]);

            }

        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            substituteValuationPrices = l_valuationPrices;
            unExecSubstituteValuationPrices = l_unexecutedValuationPrices;
            prevPriceSubstituteValuation = l_dblValuationPrices1;
        }
        else
        {
            valuationPrices = l_valuationPrices;
            unExecValuationPrices = l_unexecutedValuationPrices;
        }

        log.debug(
            "<<<<<  Valuation prices calculated. >>>>>");

    }

    /**
     * (get�������Ə،��]���z)<BR>
     * �v�Z�ςݖ������Ə،��]���z�،��]���z�iT+0�`T+5�j���擾����B
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     * @@roseuid 40E5728D02E4
     */
    private double[] getValuationPrices(String l_strDepositType)
    {
        if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            return substituteValuationPrices;
        }
        else
        {
            return valuationPrices;
        }
    }

    /**
     * (get�������Ɣ������،��]���z)<BR>
     * �v�Z�ςݖ������Ɣ������،��]���z�iT+0�`T+5�j���擾����B
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     */
    private double[] getUnExecValuationPrices(String l_strDepositType)
    {
        if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            return unExecSubstituteValuationPrices;
        }
        else
        {
            return unExecValuationPrices;
        }
    }

    /**
>>>>>>> 1.28
     * (calc�����ۗL�]���z<�m��>)<BR>
     * ��������̊����ۗL�]���z<�m��>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.�����ۗL�ϓ��ꗗ<�m��>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ�������ۗL�ϓ��̕]���z���ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�` T+5)<BR>
     * <BR>
     *   �]���z�W�v(n) = ��(*)�����ۗL�ϓ�.�]���z <BR>
     *    (*)�W�v�����F<BR>
     *       �����ۗL�ϓ��ꗗ<�m��>�ɂ�������ۗL�ϓ�<BR>
     * �@@ �@@ ���A�����ۗL�ϓ�.is�ϓ����Ԓ�(n) = true<BR>
     * <BR>
     * 3.�z��: �]���z�W�v[T+0 ...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E�����ۗL�ϓ��ꗗ<�m��>�E�E�E�������Ə،��]��.get�����ۗL�ϓ��ꗗ<�m��>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     * @@roseuid 40B2E56002F5
     */
    private double[] calcAssetValuationPrice(String l_strDepositType)
    {

        log.debug(
            "Calculating asset change prices, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getAssetChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //�a�莑�Y�Ή��i�����Ŋ|�ڂ����Z����悤�ɏC���j
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc�����ۗL����<�m��>)<BR>
     * ��������̊����ۗL����<�m��>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.�����ۗL�ϓ��ꗗ<�m��>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ�������ۗL�ϓ��̕]���z���ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�` T+5)<BR>
     * <BR>
     *   ���ʏW�v(n) =��(*)�����ۗL�ϓ�.���� <BR>
     *    (*)�W�v�����F<BR>
     *       �����ۗL�ϓ��ꗗ<�m��>�ɂ�������ۗL�ϓ�<BR>
     * �@@ �@@ ���A�����ۗL�ϓ�.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * 3.�z��: ���ʏW�v[T+0...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E�����ۗL�ϓ��ꗗ<�m��>�E�E�E�������Ə،��]��.
     * get�����ۗL�ϓ��ꗗ<�m��>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     * @@roseuid 40B2EC000269
     */
    private double[] calcAssetQuantity(String l_strDepositType)
    {

        log.debug(
            "Calculating asset change quantities, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getAssetChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc�U�֕]���z<�����ȍ~>)<BR>
     * ��������̐U�֕]���z<�����ȍ~>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.�U�֕ϓ��ꗗ<�����ȍ~>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ���U�֕ϓ��̕]���z���ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n�̎�蓾��͈͂�T+0�`T+5)<BR>
     * <BR>
     *   �]���z�W�v(n) = ��(*)�U�֕ϓ�.�]���z <BR>
     *     (*)�W�v�����F<BR>
     *        �U�֕ϓ��ꗗ<�����ȍ~>�ɂ���U�֕ϓ�<BR>
     * �@@  �@@ ���A�U�֕ϓ�.is�ϓ����Ԓ�(n) = true<BR>
     * <BR>
     * 3.�z��: �]���z�W�v[T+0...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E�U�֕ϓ��ꗗ<�����ȍ~>�E�E�E�������Ə،��]��.
     * get�U�֕ϓ��ꗗ<�����ȍ~>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     * @@roseuid 40B2E664017E
     */
    private double[] calcTransferValuationPrice(String l_strDepositType)
    {

        log.debug(
            "Calculating transfer change prices, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getTransferChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //�a�莑�Y�Ή��i�����Ŋ|�ڂ����Z����悤�ɏC���j
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc�U�֐���<�����ȍ~>)<BR>
     * ��������̐U�֐���<�����ȍ~>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.�U�֕ϓ��ꗗ<�����ȍ~>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ���U�֕ϓ��̐��ʂ��ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�`T+5)<BR>
     * <BR>
     *   ���ʏW�v(n) = ��(*)�U�֕ϓ�.����<BR>
     *     (*)�W�v�����F<BR>
     *        �U�֕ϓ��ꗗ<�����ȍ~>�ɂ���U�֕ϓ�<BR>
     * �@@ �@@  ���A�U�֕ϓ�.is�ϓ����Ԓ�(n) = true<BR>
     * <BR>
     * 3.�z��: ���ʏW�v[T+0 ...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E�U�֕ϓ��ꗗ<�����ȍ~>�E�E�E�������Ə،��]��.
     * get�U�֕ϓ��ꗗ<�����ȍ~>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     * @@roseuid 40B2EC000278
     */
    private double[] calcTransferQuantity(String l_strDepositType)
    {

        log.debug(
            "Calculating transfer change quantities, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getTransferChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc�o�ɕ]���z<�����ȍ~>)<BR>
     * ��������̏o�ɕ]���z<�����ȍ~>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.�o�ɕϓ��ꗗ<�����ȍ~>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ���o�ɕϓ��̕]���z���ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�` T+5)<BR>
     * <BR>
     *   �]���z�W�v(n) = ��(*)�o�ɕϓ�.�]���z<BR>
     *     (*)�W�v�����F<BR>
     *        �o�ɕϓ��ꗗ<�����ȍ~>�ɂ���o�ɕϓ�<BR>
     * �@@ �@@  ���A�o�ɕϓ�.is�ϓ����Ԓ�(n) = true<BR>
     * <BR>
     * 3.�z��: �]���z�W�v[T+0...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E�o�ɕϓ��ꗗ<�����ȍ~>�E�E�E�������Ə،��]��.
     * get�o�ɕϓ��ꗗ<�����ȍ~>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     * @@roseuid 40E5672E02C0
     */
    private double[] calcDeliveryValuationPrice(String l_strDepositType)
    {

        log.debug(
            "Calculating delivery change prices, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getDeliveryChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //�a�莑�Y�Ή��i�����Ŋ|�ڂ����Z����悤�ɏC���j
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc�o�ɐ���<�����ȍ~>)<BR>
     * ��������̏o�ɐ���<�����ȍ~>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.�o�ɕϓ��ꗗ<�����ȍ~>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ���o�ɕϓ��̐��ʂ��ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�`T+5)<BR>
     * <BR>
     *   ���ʏW�v(n) = ��(*)�o�ɕϓ�.����<BR>
     *     (*)�W�v�����F<BR>
     *        �o�ɕϓ��ꗗ<�����ȍ~>�ɂ���o�ɕϓ�<BR>
     * �@@ �@@  ���A�o�ɕϓ�.is�ϓ����Ԓ�(n) = true<BR>
     * <BR>
     * 3.�z��: ���ʏW�v[T+0 ...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E�o�ɕϓ��ꗗ<�����ȍ~>�E�E�E�������Ə،��]��.
     * get�o�ɕϓ��ꗗ<�����ȍ~>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return double[]
     * @@roseuid 40B2EC000298
     */
    private double[] calcDeliveryQuantity(String l_strDepositType)
    {

        log.debug(
            "Calculating delivery change quantities, condition=[DepositType="
            + l_strDepositType
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getDeliveryChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc����]���z<�����ȍ~>)<BR>
     * ��������̎���ϓ��]���z<�����ȍ~>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.����ϓ��ꗗ<�����ȍ~>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ������ϓ��̕]���z���ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�`T+5)<BR>
     * <BR>
     *   �]���z�W�v(n) =��(*)����ϓ�.�]���z<BR>
     *     (*)�W�v�����F<BR>
     *        ����ϓ��ꗗ<�����ȍ~>�ɂ������ϓ�<BR>
     *        ���A����ϓ�.is�ϓ����Ԓ�(n) =true<BR>
     *        ���A����ϓ�.�����敪=����.�����敪<BR>
     *        ���A����ϓ�.���敪=����.���敪<BR>
     * <BR>
     * 3.�z��: �]���z�W�v[T+0...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E����ϓ��ꗗ<�����ȍ~>�E�E�E�������Ə،��]��.
     * get����ϓ��ꗗ<�����ȍ~>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@param l_strExecType - (���敪)
     * @@param l_side - (�����敪)
     * @@return double[]
     * @@roseuid 40B30FDC0298
     */
    private double[] calcTransactionValuationPrice(
        String l_strDepositType,
        String l_strExecType,
        SideEnum l_side)
    {

        log.debug(
            "Calculating transaction change prices, condition=[DepositType="
            + l_strDepositType
            + ", ExecType="
            + l_strExecType
            + ", Side="
            + l_side
            + "]");

        double[] l_dblPrices = new double[6];
        List l_changes = getTransactionChanges(l_strDepositType);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityTransactionChange l_change =
                    (WEB3TPSecurityTransactionChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate)
                    && l_change.getExecType().equals(l_strExecType)
                    && l_change.getSide().equals(l_side))
                {
                    //�a�莑�Y�Ή��i�����Ŋ|�ڂ�ǉ�����悤�ɏC���j
                    l_dblPrice += l_change.getValuationPrice() * l_change.getValuationRatio();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (calc�������<�����ȍ~>)<BR>
     * ��������̎���ϓ�����<�����ȍ~>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.����ϓ��ꗗ<�����ȍ~>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ������ϓ��̐��ʂ��ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�`T+5)<BR>
     * <BR>
     *   ���ʏW�v(n) = ��(*)����ϓ�.����<BR>
     *     (*)�W�v�����F<BR>
     *        ����ϓ��ꗗ<�����ȍ~>�ɂ������ϓ�<BR>
     *        ���A����ϓ�.is�ϓ����Ԓ�(n) = true<BR>
     *        ���A����ϓ�.�����敪=����.�����敪<BR>
     *        ���A����ϓ�.���敪=����.���敪<BR>
     * <BR>
     * 3.�z��: ���ʏW�v[T+0...5]��Ԃ��B<BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E����.�a��敪<BR>
     * �E����ϓ��ꗗ<�����ȍ~>�E�E�E�������Ə،��]��.
     * get����ϓ��ꗗ<�����ȍ~>(�a��敪)<BR>
     * @@param l_strDepositType - (�a��敪)
     * @@param l_strExecType - (���敪)
     * @@param l_side - (�����敪)
     * @@return double[]
     * @@roseuid 40B30FCD015F
     */
    private double[] calcTransactionQuantity(
        String l_strDepositType,
        String l_strExecType,
        SideEnum l_side)
    {

        log.debug(
            "Calculating transaction change quantities, condition=[DepositType="
            + l_strDepositType
            + ", ExecType="
            + l_strExecType
            + ", Side="
            + l_side
            + "]");

        double[] l_dblQtys = new double[6];
        List l_changes = getTransactionChanges(l_strDepositType);

        for (int i = 0; i < l_dblQtys.length; i++)
        {

            double l_dblQty = 0.0d;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityTransactionChange l_change =
                    (WEB3TPSecurityTransactionChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate)
                    && l_change.getExecType().equals(l_strExecType)
                    && l_change.getSide().equals(l_side))
                {
                    l_dblQty += l_change.getQuantity();
                }
            }

            l_dblQtys[i] = l_dblQty;

        }

        return l_dblQtys;

    }

    /**
     * (calc�����ۗL�]���z<�a�莑�Y>)<BR>
     * ��������̊����ۗL�]���z<�a�莑�Y>(T+0�`T+5)���v�Z����B<BR>
     * <BR>
     * 1.�����ۗL�ϓ��ꗗ<�m��>�擾�B<BR>
     * <BR>
     * 2.�ꗗ�ɂ�������ۗL�ϓ��̕]���z���ȉ������ŏW�v����<BR>
     *   �e�c�Ɠ����Ƃ̏���(n �� ��蓾��͈͂�T+0�` T+5)<BR>
     * <BR>
     *   �]���z�W�v(n) = ��(*)�����ۗL�ϓ�.�]���z <BR>
     *    (*)�W�v�����F<BR>
     *       �����ۗL�ϓ��ꗗ<�m��>�ɂ�������ۗL�ϓ�<BR>
     * �@@ �@@ ���A�����ۗL�ϓ�.is�ϓ����Ԓ�(n) = true<BR>
     * <BR>
     * 3.�z��: �]���z�W�v[T+0 ...5]��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�a��敪�E�E�E'�ی�'<BR>
     * �E�����ۗL�ϓ��ꗗ<�m��>�E�E�E�������Ə،��]��.get�����ۗL�ϓ��ꗗ<�m��>(�a��敪)<BR>
     * @@return double[]
     */
    private double[] calcDepositAssetValuationPrice()
    {

        double[] l_dblPrices = new double[6];
        List l_changes = getAssetChanges(WEB3TPDepositTypeDef.TRUST);

        for (int i = 0; i < l_dblPrices.length; i++)
        {

            double l_dblPrice = 0.0;
            Date l_datBizDate = getCalcCondition().getBizDate(i);

            for (Iterator l_it = l_changes.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityChange l_change =
                    (WEB3TPSecurityChange) l_it.next();
                if (l_change.isDuringReflectTime(l_datBizDate))
                {
                    //�|�ڂ͉��Z���Ȃ�
                    l_dblPrice += l_change.getValuationPrice();
                }
            }

            l_dblPrices[i] = l_dblPrice;

        }

        return l_dblPrices;

    }

    /**
     * (get�����ۗL�ϓ��ꗗ<�m��>)<BR>
     * <BR>
     * ����.�a��敪�ɊY����������ۗL�ϓ��ꗗ<�m��>��ԋp����B<BR>
     * ���j�ŋ敪=5�F�X�g�b�N�I�v�V�����ɂ��ď،��]���̑Ώۂ��珜�O����B<BR> 
     * ����āA�ԋp���X�g�Ɋ܂߂Ȃ��B <BR>
     * <BR>
     * �P�jthis.�����ۗL�ϓ����X�g�̊����ۗL�ϓ��I�u�W�F�N�g�̓��A<BR> 
     * �@@����.�a��敪�ɊY�����郊�X�g�𐶐�����B <BR>
     * <BR>
     * �@@[LOOP�����Fn=this.�����ۗL�ϓ����X�g�̗v�f����]<BR> 
     * <BR>
     * �@@�@@�@@this.�����ۗL�ϓ����X�g�������ۗL�ϓ��I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@�@@�@@�|�����ۗL�ϓ� = this.�����ۗL�ϓ����X�g.get(n)<BR> 
     * <BR>
     * �@@�@@�A�����ۗL�ϓ����A�����ۗL�ϓ��ꗗ<�m��>�ɒǉ�����B<BR> 
     * <BR>
     * �@@�@@�@@[a.�����ۗL�ϓ�.get�a��敪()=����.�a��敪<BR>  
     * �@@�@@�@@�@@�@@&&  <BR>
     * �@@�@@�@@�@@�@@�@@�����ۗL�ϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]<BR> 
     * <BR>
     * �@@�@@�@@�@@�|�����ۗL�ϓ��ꗗ<�m��>.add(:Object = �����ۗL�ϓ�)<BR> 
     * <BR>
     * �Q�j�����ۗL�ϓ��ꗗ<�m��>��ԋp����B<BR> 
     * <BR>
     * <BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return List
     * @@roseuid 40B5C6010062
     */
    protected List getAssetChanges(String l_strDepositType)
    {
        //�P�jthis.�����ۗL�ϓ����X�g�̊����ۗL�ϓ��I�u�W�F�N�g�̓��A
        //����.�a��敪�ɊY�����郊�X�g�𐶐�����B
        //[LOOP�����Fn=this.�����ۗL�ϓ����X�g�̗v�f����]
        //�@@this.�����ۗL�ϓ����X�g�������ۗL�ϓ��I�u�W�F�N�g���擾����B
        //�|�����ۗL�ϓ� = this.�����ۗL�ϓ����X�g.get(n)
        //�A�����ۗL�ϓ����A�����ۗL�ϓ��ꗗ<�m��>�ɒǉ�����B
        //[a.�����ۗL�ϓ�.get�a��敪()=����.�a��敪
        //&&�����ۗL�ϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]
        //�|�����ۗL�ϓ��ꗗ<�m��>.add(:Object = �����ۗL�ϓ�)
        List l_list = new ArrayList();
        for (Iterator l_it = assetChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //�Q�j�����ۗL�ϓ��ꗗ<�m��>��ԋp����B
        return l_list;
    }

    /**
     * (get�U�֕ϓ��ꗗ<�����ȍ~>)<BR>
     * <BR>
     * ����.�a��敪�ɊY������U�֕ϓ��ꗗ<�����ȍ~>��ԋp����B<BR> 
     * ���j�ŋ敪=5�F�X�g�b�N�I�v�V�����ɂ��ď،��]���̑Ώۂ��珜�O����B<BR> 
     * ����āA�ԋp���X�g�Ɋ܂߂Ȃ��B <BR>
     * <BR>
     * �P�jthis.�U�֕ϓ����X�g�̐U�֕ϓ��I�u�W�F�N�g�̓��A<BR> 
�@@   * ����.�a��敪�ɊY�����郊�X�g�𐶐�����B <BR>
     * <BR>
�@@   * [LOOP�����Fn=this.�U�֕ϓ����X�g�̗v�f����]<BR> 
     * <BR>
     * �@@�@@�@@this.�U�֕ϓ����X�g���U�֕ϓ��I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@�@@�@@�|�U�֕ϓ� = this.�U�֕ϓ����X�g.get(n)<BR> 
     * <BR>
�@@�@@ * �A�U�֕ϓ����A�U�֕ϓ��ꗗ<�����ȍ~>�ɒǉ�����B<BR> 
     * <BR>
     * �@@�@@�@@[a.�U�֕ϓ�.get�a��敪()=����.�a��敪<BR> 
     * �@@�@@�@@�@@�@@&&  <BR>
     * �@@�@@�@@�@@�@@�U�֕ϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]<BR> 
     * <BR>
     * �@@�@@�@@�@@�|�U�֕ϓ��ꗗ<�����ȍ~>.add(:Object = �U�֕ϓ�)<BR> 
     * <BR>
     * �Q�j�U�֕ϓ��ꗗ<�����ȍ~>��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return List
     * @@roseuid 40B5C6010091
     */
    protected List getTransferChanges(String l_strDepositType)
    {
        // �P�jthis.�U�֕ϓ����X�g�̐U�֕ϓ��I�u�W�F�N�g�̓��A
        //����.�a��敪�ɊY�����郊�X�g�𐶐�����B
        //[LOOP�����Fn=this.�U�֕ϓ����X�g�̗v�f����]
        //�@@this.�U�֕ϓ����X�g���U�֕ϓ��I�u�W�F�N�g���擾����B
        //�|�U�֕ϓ� = this.�U�֕ϓ����X�g.get(n)
        //�A�U�֕ϓ����A�U�֕ϓ��ꗗ<�����ȍ~>�ɒǉ�����B
        //�@@�@@�@@[a.�U�֕ϓ�.get�a��敪()=����.�a��敪
        //&&�U�֕ϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]
        //�|�U�֕ϓ��ꗗ<�����ȍ~>.add(:Object = �U�֕ϓ�)
        List l_list = new ArrayList();
        for (Iterator l_it = transferChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //�Q�j�U�֕ϓ��ꗗ<�����ȍ~>��ԋp����B
        return l_list;
    }

    /**
     * (get�o�ɕϓ��ꗗ<�����ȍ~>)<BR>
     * <BR>
     * ����.�a��敪�ɊY������o�ɕϓ��ꗗ<�����ȍ~>��ԋp����B<BR> 
     * ���j�ŋ敪=5�F�X�g�b�N�I�v�V�����ɂ��ď،��]���̑Ώۂ��珜�O����B<BR> 
     * ����āA�ԋp���X�g�Ɋ܂߂Ȃ��B <BR>
     * <BR>
     * �P�jthis.�o�ɕϓ����X�g�̏o�ɕϓ��I�u�W�F�N�g�̓��A<BR> 
     * �@@����.�a��敪�ɊY�����郊�X�g�𐶐�����B <BR>
     * <BR>
     * �@@[LOOP�����Fn=this.�o�ɕϓ����X�g�̗v�f����]<BR> 
     * <BR>
     * �@@�@@�@@this.�o�ɕϓ����X�g���o�ɕϓ��I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@�@@�@@�|�o�ɕϓ� = this.�o�ɕϓ����X�g.get(n)<BR> 
     * <BR>
     * �@@�@@�A�o�ɕϓ����A�o�ɕϓ��ꗗ<�����ȍ~>�ɒǉ�����B<BR> 
     * <BR>
     * �@@�@@�@@[a.�o�ɕϓ�.get�a��敪()=����.�a��敪<BR> 
     * �@@�@@�@@�@@�@@&&  <BR>
     * �@@�@@�@@�@@�@@�@@�o�ɕϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]<BR> 
     * <BR>
     * �@@�@@�@@�@@�|�o�ɕϓ��ꗗ<�����ȍ~>.add(:Object = �o�ɕϓ�)<BR> 
     * <BR>
     * �Q�j�o�ɕϓ��ꗗ<�����ȍ~>��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_strDepositType - (�a��敪)
     * @@return List
     * @@roseuid 40B5C60100BF
     */
    protected List getDeliveryChanges(String l_strDepositType)
    {
        //�P�jthis.�o�ɕϓ����X�g�̏o�ɕϓ��I�u�W�F�N�g�̓��A
        //����.�a��敪�ɊY�����郊�X�g�𐶐�����B
        //[LOOP�����Fn=this.�o�ɕϓ����X�g�̗v�f����]
        //�@@this.�o�ɕϓ����X�g���o�ɕϓ��I�u�W�F�N�g���擾����B
        //�|�o�ɕϓ� = this.�o�ɕϓ����X�g.get(n)
        //�A�o�ɕϓ����A�o�ɕϓ��ꗗ<�����ȍ~>�ɒǉ�����B
        //[a.�o�ɕϓ�.get�a��敪()=����.�a��敪
        //&&�o�ɕϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]
        //�|�o�ɕϓ��ꗗ<�����ȍ~>.add(:Object = �o�ɕϓ�)
        List l_list = new ArrayList();
        for (Iterator l_it = deliveryChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //�Q�j�o�ɕϓ��ꗗ<�����ȍ~>��ԋp����B
        return l_list;
    }

    /**
     * (get����ϓ��ꗗ<�����ȍ~>)<BR>
     * <BR>
     * ����.�a��敪�ɊY���������ϓ��ꗗ<�����ȍ~>��ԋp����B<BR> 
     * ���j�ŋ敪=5�F�X�g�b�N�I�v�V�����ɂ��ď،��]���̑Ώۂ��珜�O����B<BR> 
     * ����āA�ԋp���X�g�Ɋ܂߂Ȃ��B <BR>
     * <BR>
     * �P�jthis.����ϓ����X�g�̎���ϓ��I�u�W�F�N�g�̓��A<BR> 
     * �@@����.�a��敪�ɊY�����郊�X�g�𐶐�����B <BR>
     * <BR>
     * �@@[LOOP�����Fn=this.����ϓ����X�g�̗v�f����]<BR> 
     * <BR>
     * �@@�@@�@@this.����ϓ����X�g������ϓ��I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �@@�@@�@@�|����ϓ� = this.����ϓ����X�g.get(n)<BR> 
     * <BR>
     * �@@�@@�A����ϓ����A����ϓ��ꗗ<�����ȍ~>�ɒǉ�����B<BR> 
     * <BR>
     * �@@�@@�@@[a.����ϓ�.get�a��敪()=����.�a��敪<BR>
     * �@@�@@�@@�@@�@@&&<BR>
     * �@@�@@�@@�@@�@@�@@����ϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]<BR> 
     * <BR>
     * �@@�@@�@@�@@�|����ϓ��ꗗ<�����ȍ~>.add(:Object = ����ϓ�)<BR> 
     * <BR>
     * �Q�j����ϓ��ꗗ<�����ȍ~>��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_depositType - (�a��敪)
     * @@return List
     * @@roseuid 40B5C7370040
     */
    protected List getTransactionChanges(String l_strDepositType)
    {
        //�P�jthis.����ϓ����X�g�̎���ϓ��I�u�W�F�N�g�̓��A
        //����.�a��敪�ɊY�����郊�X�g�𐶐�����B
        //[LOOP�����Fn=this.����ϓ����X�g�̗v�f����] 
        //�@@this.����ϓ����X�g������ϓ��I�u�W�F�N�g���擾����B
        //�|����ϓ� = this.����ϓ����X�g.get(n)
        //�A����ϓ����A����ϓ��ꗗ<�����ȍ~>�ɒǉ�����B
        //[a.����ϓ�.get�a��敪()=����.�a��敪
        //&&����ϓ�.get�ŋ敪()��5�F�X�g�b�N�I�v�V����]
        //�|����ϓ��ꗗ<�����ȍ~>.add(:Object = ����ϓ�)
        List l_list = new ArrayList();
        for (Iterator l_it = transactionChanges.iterator(); l_it.hasNext(); )
        {
            WEB3TPSecurityChange l_change = (WEB3TPSecurityChange) l_it.next();
            if (l_change.getDepositType().equals(l_strDepositType) && 
                !TaxTypeEnum.STOCK_OPTION.equals(l_change.getTaxType()))
            {
                l_list.add(l_change);
            }
        }
        
        //�Q�j����ϓ��ꗗ<�����ȍ~>��ԋp����B
        return l_list;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        String l_str = "\n�����ۗL:";
        List l_list = new ArrayList();
        for (Iterator l_it = assetChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        l_str = l_str + "�U��:";
        l_list = new ArrayList();
        for (Iterator l_it = transferChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        l_str = l_str + "�o��:";
        l_list = new ArrayList();
        for (Iterator l_it = deliveryChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        l_str = l_str + "���:";
        l_list = new ArrayList();
        for (Iterator l_it = transactionChanges.iterator(); l_it.hasNext(); )
        {
            l_str = l_str + l_it.next();
        }

        return ToStringUtils
            .newToStringBuilder(this)
            .append("product", product)
            .append("securityChanges", l_str)
            .toString();
    }
}
@
