head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuationAfterBuy.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �l�b�g�g���[�h�V�X�e���J����
 File Name        : ���t��،��]���z(WEB3TPSecurityValuationAfterBuy.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/04/03 nakazato(DIR-ST) �V�K�쐬
                    2006/09/15 �Ԑi�@@  (���u)   ���f��No.43�ANo.44
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.updtpower.contract.WEB3TPClosingContractSpec;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.util.WEB3LogUtility;

/**
 * (���t��،��]���z)<BR>
 * <BR>
 * ���N���X�u�،��]���z�v���p��<BR>
 */
public class WEB3TPSecurityValuationAfterBuy extends WEB3TPSecurityValuation
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPSecurityValuationAfterBuy.class);

    /**
     * (�f�t�H���g�R���X�g���N�^)
     */
    public WEB3TPSecurityValuationAfterBuy()
    {
        super();
    }

    /**
     * (static���\�b�h)(create���t��،��]��)<BR>
     * �����t��،��]���C���X�^���X���쐬���ԋp����B<BR>
     * <BR>
     * 1)���t��،��]���I�u�W�F�N�g�𐶐�����B<BR>
     * �@@-�f�t�H���g�R���X�g���N�^���R�[��<BR>
     * <BR>
     * 2)�����������t��،��]���I�u�W�F�N�g�̑����ɒl���Z�b�g����B<BR>
     * �@@2-1)����.�ڋq�������Z�b�g<BR>
     * �@@�@@-this.set�ڋq����()���R�[��<BR>
     * <BR>
     * �@@2-2)����.�v�Z�������Z�b�g<BR>
     * �@@�@@-this.set�]�͌v�Z����()���R�[��<BR>
     * <BR>
     * �@@2-3)����.���������e���Z�b�g<BR>
     * �@@�@@-this.set���������e()���R�[��<BR>
     * <BR>
     * �@@2-4)����.���ʏ����Z�b�g<BR>
     * �@@�@@-this.set���ʏ��()���R�[��<BR>
     * <BR>
     * 3)���t��،��]���I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param accountInfo - (�ڋq����)
     * @@param calcCondition - (�v�Z����)
     * @@param newOrderSpecs - (���������e)
     * @@param contractInfo - (���ʏ��)
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationAfterBuy
     */
    public static WEB3TPSecurityValuationAfterBuy createWEB3TPSecurityValuationAfterBuy(
            WEB3TPAccountInfo accountInfo,
            WEB3TPCalcCondition calcCondition,
            WEB3TPNewOrderSpec[] newOrderSpecs,
            WEB3TPContractInfo contractInfo)
    {
        WEB3TPSecurityValuationAfterBuy l_valuation = new WEB3TPSecurityValuationAfterBuy();
        l_valuation.setAccountInfo(accountInfo);
        l_valuation.setCalcCondition(calcCondition);
        l_valuation.setNewOrderSpecs(newOrderSpecs);
        l_valuation.setContractInfo(contractInfo);
        return l_valuation;
    }

    /**
     * 
     * (do����ϓ�<�����ȍ~>���[�h)<BR>
     * ��Super�N���X�̃I�[�o�[���C�h���\�b�h<BR>
     * <BR>
     * ����ϓ�<�����ȍ~>�����[�h����B<BR>
     * <BR>
     * 1)����ϓ�<�����ȍ~>���[�h(GP)�����[�h����B<BR>
     * �@@�V�[�P���X�}�udo����ϓ�<�����ȍ~>���[�h(GP)�v�Q��<BR>
     * <BR>
     * 2)do����ϓ�<�����ȍ~>���[�h�i�����j�����[�h����B<BR>
     * �@@�V�[�P���X�}�u(���t��،��]���z)do����ϓ�<�����ȍ~>���[�h(����)�v�Q��<BR>
     */
    protected void doTransactionChangesLoad()
    {
        //�]���P��Callback���擾
        WEB3TPUnitPriceCallback l_unitPriceCallback = getCalcCondition().getUnitPriceCallback();

        // 1.����ϓ�<�����ȍ~>(GP)�����[�h����B
        // 1.1 �a��،��f�[�^���\�[�X�A�N�Z�X�Ǘ�.getGP������R�[�h(���Y�ϓ�)
        List l_ruitoOrderUnits = dataMgr.getRuitoOrderUnits(this);
        
        // 1.2 �ݓ������P�ʃe�[�u���Ƀ��R�[�h�����݂���ꍇ
        //�igetGP������R�[�h()�@@!= nu
        if (l_ruitoOrderUnits != null)
        {
            log.debug(l_ruitoOrderUnits.size() + " ruito order unit row found.");

            //1.2.1 LOOP�����FgetGP������R�[�h()�̖߂�l�̗v�f����
            for (Iterator l_it = l_ruitoOrderUnits.iterator(); l_it.hasNext(); )
            {
                RuitoOrderUnitRow l_ruitoOrderUnit = (RuitoOrderUnitRow) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.2.1.1 is���݃��[�h�ςݑΏۖ���(����ID�j
                //   �����̖���ID�͗ݓ������P�ʃe�[�u��.����ID
                boolean isLoadedProduct = this.isLoadedProduct(l_ruitoOrderUnit.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.2.1.2 is���݃��[�h�ςݑΏۖ���(����ID) = false�̏ꍇ�A
                if (!isLoadedProduct)
                {
                    // 1.2.1.2.1 load�Ώۖ���(����ID)
                    //   �����̖���ID�͗ݓ������P�ʃe�[�u��.����ID
                    //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        loadProduct(l_ruitoOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.2.2 �������Ə،��]��.create�������Ə،��]��(�Ώۖ���)
                    //   �����̑Ώۖ����́A�u4.1.�v�̖߂�l
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add�������Ə،��]���i�Ώۖ���, �������Ə،��]��)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.2.1.3 is���݃��[�h�ςݑΏۖ���(����ID) = true�̏ꍇ�A
                else
                {
                    // 1.2.1.3.1 get�Ώۖ���(����ID)
                    //   �����̖���ID�͗ݓ������P�ʃe�[�u��.����ID
                    //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        getProduct(l_ruitoOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.3.2 get�������Ə،��]��(�Ώۖ���)
                    //   �����̑Ώۖ����́u2.3.1�v�̖߂�l
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.2.1.4 ����ϓ�.create����ϓ�()
                WEB3TPSecurityTransactionChange l_change =
                    WEB3TPSecurityTransactionChange.create();
                
                // 1.2.1.5 ����ϓ�.set�]�͌v�Z����(�]�͌v�Z����)
                l_change.setCalcCondition(getCalcCondition());
                
                // 1.2.1.6 ����ϓ�. set�����^�C�v(ProductTypeEnum)
                l_change.setProductType(l_product.getProductType());

                // 1.2.1.7����ϓ�.set�a��敪(�a��敪)
                // �����̗a��敪�́Aget�ڋq����().get�a��敪(�ݓ������P�ʃe�[�u��.�⏕����ID)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_ruitoOrderUnit.getSubAccountId()));

                // 1.2.1.8 ����ϓ�.set�ŋ敪 (�ŋ敪)
                // �ݓ������P�ʃe�[�u��.�ŋ敪
                l_change.setTaxType(l_ruitoOrderUnit.getTaxType());
                
                // 1.2.1.9  to��������敪(TaxTypeEnum)
                // 1.2.1.10 ����ϓ�.set��������敪(��������敪)
                //   �����̓�������敪�́A�ݓ������P�ʃe�[�u��.�ŋ敪
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(
                    l_ruitoOrderUnit.getTaxType()));

                // 1.2.1.11 ����ϓ�.set�����J�e�S��(�����J�e�S��)
                //   �����̒����J�e�S���́A�ݓ������P�ʃe�[�u��.�����J�e�S��
                l_change.setOrderCateg(l_ruitoOrderUnit.getOrderCateg());

                // 1.2.1.12 ����ϓ�.set���敪(���敪)
                //   �����̖��敪�́A�����
                l_change.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                // 1.2.1.13����ϓ�.set�����敪(�����敪)
                //   �����̔����敪�́ASideEnum.get�����敪(�ݓ������P�ʃe�[�u��.�������)
                l_change.setSide(SideEnum.getSide(l_ruitoOrderUnit.getOrderType()));

                // 1.2.1.14 ����ϓ�.set�ϓ�����(�ϓ�����)
                //   �����̕ϓ����ʂ́A�ݓ������P�ʃe�[�u��.����
                l_change.setQuantity(l_ruitoOrderUnit.getQuantity());

                // 1.2.1.15 ����ϓ�.set�]���P��(�]���P��)
                //   �����̕]���P���́A�Ώۖ���.get�]���P��()�~�Ώۖ���.get�v�Z�P��()
                l_change.setUnitPrice(
                        l_product.getUnitPrice() * l_product.getUnitSize());                	

                // 1.2.1.16 ����ϓ�.set�]���|��(�]���|��)
                //   �����̕]���|�ڂ́A�Ώۖ���.get�،��]���|��()
                l_change.setValuationRatio(
                    l_product.getValuationRatio(WEB3TPDepositTypeDef.TRUST));

                // 1.2.1.17 ����ϓ�.calc�ϓ����f��(��n��)
                //   �����̎�n���́A�ݓ������P�ʃe�[�u��.��n��
                l_change.calcReflectDay(l_ruitoOrderUnit.getDeliveryDate());

                // 1.2.1.18 ����ϓ�.set�]���z(�]���z)
                //   �����̕]���z�́A�ϓ����ʁ~�]���P���~�]���|��
                l_change.setValuationPrice(
                    l_change.getQuantity()
                    * l_change.getUnitPrice());

                // 1.2.1.19 �������Ə،��]���z.add�،��ϓ�(�،��ϓ�)
                //   �������Ə،��]���z�́A�2.2.2��܂��͢2.3.2��̖߂�l
                //   �����̏،��ϓ��́A�2.4��̖߂�l
                l_valuation.addSecurityChange(l_change);

            }
        }
        else
        {
            log.debug(" ruito order unit row not found.");
        }

        // ����ϓ�<�����ȍ~>(����)�����[�h����B
        // 1.3 �a��،��f�[�^���\�[�X�A�N�Z�X�Ǘ�.get����������R�[�h(���Y�]���j
        List l_equityOrderUnits = dataMgr.getEqTypeOrderUnits(this);

        /*
         * 1.4 ���񒍕��P��ID
         */
        Long l_newOrderUnitId = null;
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = this.getNewOrderSpecs();

        if(l_newOrderSpecs != null)
        {
            l_newOrderUnitId = new Long(l_newOrderSpecs[0].getOrderUnitId());
        }
        
        /*
         * 1.5 �]�͌v�Z�p��p�|��
         */
        double l_dblSubRate = this.getCalcCondition().getSubstituteRate();
        l_dblSubRate = l_dblSubRate / 100;
        
        // 1.6 ���������P�ʃe�[�u���Ƀ��R�[�h�����݂���ꍇ
        //�iget����������R�[�h()�@@!= null�j
        if (l_equityOrderUnits != null)
        {
            log.debug(l_equityOrderUnits.size() + " eqtype order unit row found.");

            // 1.6.1 LOOP�����FgetGP������R�[�h()�̖߂�l�̗v�f����
            for (Iterator l_it = l_equityOrderUnits.iterator(); l_it.hasNext(); )
            {
                EqtypeOrderUnitRow l_equityOrderUnit = (EqtypeOrderUnitRow) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.6.1.1 is���݃��[�h�ςݑΏۖ���(����ID�j
                //   �����̖���ID�͊��������P�ʃe�[�u��.����ID
                //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                boolean isLoadedProduct = this.isLoadedProduct(l_equityOrderUnit.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.6.1.2 is���݃��[�h�ςݑΏۖ���(����ID) = false�̏ꍇ�A
                if (!isLoadedProduct)
                {
                    // 1.6.1.2.1 load�Ώۖ���(����ID)
                    //   �����̖���ID�͊��������P�ʃe�[�u��.����ID
                    //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        loadProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.6.1.2.2 �������Ə،��]��.create�������Ə،��]��(�Ώۖ���)
                    //   �����̑Ώۖ����́A�u2.2.1�v�̖߂�l
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.6.1.2.3 add�������Ə،��]���i�Ώۖ���, �������Ə،��]��)
                    //   �����̑Ώۖ����́u2.2.1�v�̖߂�l
                    //   �����̖������Ə،��]���́u2.2.2�v�̖߂�l
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.6.1.3is���݃��[�h�ςݑΏۖ���(����ID) = true�̏ꍇ�A
                else
                {
                    // 1.6.1.3.1 get�Ώۖ���(����ID)
                    //   �����̖���ID�͊��������P�ʃe�[�u��.����ID
                    //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        getProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.6.1.3.2 get�������Ə،��]��(�Ώۖ���)
                    //   �����̑Ώۖ����́u2.3.1�v�̖߂�l
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.6.1.4 ����ϓ�.create����ϓ�()--��������ϓ�
                WEB3TPSecurityTransactionChange l_unExecChange =
                    WEB3TPSecurityTransactionChange.create();

                // 1.6.1.4.5 ��������ϓ�.create����ϓ�()
                l_unExecChange.setCalcCondition(getCalcCondition());

                // 1.6.1.4.6 ��������ϓ�.set�����^�C�v()
                l_unExecChange.setProductType(l_product.getProductType());

                // 1.6.1.4.7 ��������ϓ�.set�a��敪(�a��敪)
                // �����̗a��敪�́Aget�ڋq����().get�a��敪(���������P�ʃe�[�u��.�⏕����ID)
                l_unExecChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));

                // 1.6.1.4.8 ��������ϓ�.set�ŋ敪(�ŋ敪)
                // ���������P�ʃe�[�u��.�ŋ敪
                l_unExecChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.6.1.9 to��������敪(TaxTypeEnum)
                // 1.6.1.10 ��������ϓ�.set��������敪(��������敪)
                l_unExecChange.setSpecialAccountFlag(l_unExecChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.6.1.11 ��������ϓ�.set�����J�e�S��(�����J�e�S��)
                //   �����̒����J�e�S���́A���������P�ʃe�[�u��.�����J�e�S��
                l_unExecChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.6.1.12 ��������ϓ�.set�����敪(�����敪)
                l_unExecChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));
                
                /*
                 * �]���P��
                 */
                double l_dblValuationRatio;

                // 1.6.1.13 ��p���A���񒍕��̏ꍇ �a��敪 == ��p ���� ���������P�ʃe�[�u��.�����P��ID == ���񒍕��P��ID �̏ꍇ
                if(WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_unExecChange.getDepositType()) == true
                        && l_newOrderUnitId != null
                        && l_newOrderUnitId.longValue() == l_equityOrderUnit.getOrderUnitId())
                {
                    // 1.6.1.13.1 �]���P�� = MIN(�Ώۖ����̊|��, �]�͌v�Z�p��p�|��)
                    l_dblValuationRatio = Math.min(
                            l_product.getValuationRatio(l_unExecChange.getDepositType()),
                            l_dblSubRate);
                }
                //1.6.1.14 �ȊO�̏ꍇ
                else
                {
                    // 1.6.1.14.1 �]���P�� = �Ώۖ����̊|��
                    l_dblValuationRatio = l_product.getValuationRatio(l_unExecChange.getDepositType());
                }

                l_unExecChange.setValuationRatio(l_dblValuationRatio);
                
                // 1.6.1.15 ��������ϓ�.calc�ϓ����f��(��n��)
                //   �����̎�n���́A���������P�ʃe�[�u��.��n��
                l_unExecChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.6.1.16 ����ϓ�.create����ϓ�()--������ϓ�
                WEB3TPSecurityTransactionChange l_execChange =
                    WEB3TPSecurityTransactionChange.create();
                l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                // 1.6.1.17 ������ϓ�.set�v�Z����()
                l_execChange.setCalcCondition(getCalcCondition());

                // 1.6.1.18  ������ϓ�.set�����^�C�v
                l_execChange.setProductType(l_product.getProductType());

                // 1.6.1.19 ������ϓ�.set�a��敪(�a��敪)
                // �����̗a��敪�́Aget�ڋq����().get�a��敪(���������P�ʃe�[�u��.�⏕����ID)
                l_execChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));

                // 1.6.1.20 ������ϓ�.set�ŋ敪(�ŋ敪)
                // ���������P�ʃe�[�u��.�ŋ敪
                l_execChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.6.1.21  to��������敪(TaxTypeEnum)
                // 1.6.1.22 ����ϓ�.set��������敪(��������敪)
                l_execChange.setSpecialAccountFlag(l_execChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.6.1.23 ����ϓ�.set�����J�e�S��(�����J�e�S��)
                //   �����̒����J�e�S���́A���������P�ʃe�[�u��.�����J�e�S��
                l_execChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.6.1.24 ��������ϓ�.set�����敪(�����敪)
                l_execChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));

                // 1.6.1.25 ����ϓ�.set�]���|��(�]���|��)
                l_execChange.setValuationRatio(
                    l_product.getValuationRatio(l_unExecChange.getDepositType()));

                // 1.6.1.26 ��������ϓ�.calc�ϓ����f��(��n��)
                //   �����̎�n���́A���������P�ʃe�[�u��.��n��
                l_execChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.6.1.27
                //  �����̏ꍇ�F
                //      ���������P�ʃe�[�u��.�������� - ���������P�ʃe�[�u��.��萔�� > 0
                //       ���A���������P�ʃe�[�u��.�����^�C�v = ���n�̏ꍇ
                //���邢��
                //  ���t�̏ꍇ
                //  ���������P�ʃe�[�u��.�������� - ���������P�ʃe�[�u��.��萔�� > 0
                if ( (SideEnum.SELL.equals(l_execChange.getSide())
                      && (
                    l_equityOrderUnit.getQuantity() -
                    l_equityOrderUnit.getExecutedQuantity() > 0 &&
                    OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_equityOrderUnit.getOrderType())
                    )
                      )

                    || (SideEnum.BUY.equals(l_execChange.getSide())
                        && (
                    l_equityOrderUnit.getQuantity() -
                    l_equityOrderUnit.getExecutedQuantity() > 0
                    )
                        )
                    )
                {
                    // 1.6.1.27.1.����ϓ�.set���敪(���敪)
                    //   �����̖��敪�́A�����
                    l_unExecChange.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                    // 1.6.1.27.2 �����E���n�łȂ��ꍇ
                    //(���������P��Row.�����J�e�S�� != 7:�����E���n����)
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_equityOrderUnit.
                        getOrderCateg()))
                    {
                        // 1.6.1.27.2.2 ����ϓ�.set�ϓ�����(�ϓ�����)
                        //   �����̕ϓ����ʂ́A���������P�ʃe�[�u��.���� -
                        // ���������P�ʃe�[�u��.��萔��
                        l_unExecChange.setQuantity(l_equityOrderUnit.getQuantity() -
                            l_equityOrderUnit.getExecutedQuantity());

                        //  1.6.1.27.2.3 ����ϓ�.set�]���P��(�]���P��)
                        //   �����̕]���P���́A���������P�ʃe�[�u��.�����P�� �~ �Ώۖ���.get�v�Z�P��()
                        l_unExecChange.setUnitPrice(
                            l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderUnit.getPrice(), l_product) * 
                            l_product.getUnitSize());                                                

                        // 1.6.1.27.2.4 ����ϓ�.set�]���z(�]���z)
                        //
                        // �����̕]���z�́A����ϓ�.get�ϓ�����()�~����ϓ�.get�]���P��()
                        //����������v�Z���邽�߁A�|�ڂ��|���Ȃ�
                        l_unExecChange.setValuationPrice(
                            l_unExecChange.getQuantity()
                            * l_unExecChange.getUnitPrice()
                            );
                    }
                    // 1.6.1.27.3 �ȊO�i�����E���n�j�̏ꍇ
                    else
                    {
                        // �o�OY00019�F�M�p���������̖�������v�Z�Ɏg�p���钍���P����null������
                        // �ԍϏ�񂩂猚�P���ŕ]������
                        //�ꊇ�ԍς̏ꍇ���Ή�����B
                        List l_closingContractSpecs = this.getContractInfo().
                            getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                        double l_quantity = 0.0;
                        double l_amount = 0.0;
                        for (int i = 0; i < l_closingContractSpecs.size(); i++)
                        {
                            WEB3TPClosingContractSpec l_closingContractSpec = (
                                WEB3TPClosingContractSpec)
                                l_closingContractSpecs.get(i);
                            l_quantity +=
                                (l_closingContractSpec.getQuantity() -
                                 l_closingContractSpec.getExecutedQuantity());
                            l_amount += (l_closingContractSpec.getQuantity() -
                                         l_closingContractSpec.getExecutedQuantity()) *
                                l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) *                                          
                                l_product.getUnitSize();
                            l_unExecChange.setQuantity(l_quantity);
                            l_unExecChange.setValuationPrice(l_amount);
                            if (l_quantity > 0)
                            {
                                l_unExecChange.setUnitPrice(l_amount / l_quantity);
                            }
                        }
                    }

                    // 1.6.1.27.3.6 �������Ə،��]���z.add�،��ϓ�(�،��ϓ�)
                    l_valuation.addSecurityChange(l_unExecChange);

                }

                // 1.6.1.28 ���������P�ʃe�[�u��.��萔�� > 0
                if (l_equityOrderUnit.getExecutedQuantity() > 0)
                {

                    // 1.6.1.28.1 ����ϓ�.set���敪(���敪)
                    //   �����̖��敪�́A����
                    l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                    // 1.6.1.28.2 ����ϓ�.get�����J�e�S��() != �u�����E���n�v�̏ꍇ
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_unExecChange.getOrderCateg()))
                    {
                        // 1.6.1.28.2.1 �a��،��f�[�^���\�[�X�A�N�Z�X�Ǘ�.get������背�R�[�h(�����P��ID)
                        //   �����̒����P��ID�͊��������P�ʃe�[�u��.�����P��ID
                        List l_equityOrderExecutions =
                            dataMgr.
                            getEqTypeOrderExecutions(l_equityOrderUnit);

                        // 1.6.1.28.2.2 �擾�������R�[�h���W�v���ȉ��̒l���Z�o����
                        //   �ϓ����� += �����������e�[�u��.��萔��
                        //   �]���z += �����������e�[�u��.��萔�ʁ~Min�i�������e�[�u��.���P��,
                        // �Ώۖ���.get�]���P��()�~ �Ώۖ���.get�v�Z�P��()
                        double l_quantity = 0.0;
                        double l_amount = 0.0;
                        for (int i = 0; i < l_equityOrderExecutions.size(); i++)
                        {
                            EqtypeOrderExecutionRow l_equityOrderExecution = (
                                EqtypeOrderExecutionRow)
                                l_equityOrderExecutions.get(i);
                            //������Ή��̂��߁A������ꂽ����ΏۊO�ɂ���
                            if (BooleanEnum.TRUE.equals(l_equityOrderExecution.
                                getDeleteFlag()))
                            {
                                continue;
                            }
                            l_quantity += l_equityOrderExecution.getExecQuantity();
                            l_amount += l_equityOrderExecution.getExecQuantity() *
                                l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderExecution.getExecPrice(), l_product) *                                          
                                l_product.getUnitSize();
                        }
                        // 1.6.1.28.2.3 ����ϓ�.set�ϓ�����(�ϓ�����)
                        //   �����̕ϓ����ʂ́A�2.14.2.2��ŎZ�o�����ϓ�����
                        l_execChange.setQuantity(l_quantity);

                        // 1.6.1.28.2.4 ����ϓ�.set�]���z(�]���z)
                        // �@@�����̕]���z�́A�Z�o�����]���z
                        ////����������v�Z���邽�߁A�|�ڂ��|���Ȃ�
                        l_execChange.setValuationPrice(l_amount);
                        
                        // �@@�����̕]���P���́A 2.12.2.2�Z�o�����]���z / ����ϓ�.get�ϓ�����()
                        if (l_quantity > 0)
                        {
                            l_execChange.setUnitPrice(l_amount / l_quantity);
                        }

                        //�������Ə،��]���z.add�،��ϓ�(�،��ϓ�)

                        l_valuation.addSecurityChange(l_execChange);
                    }
                    // 1.6.1.28.3����ϓ�.get�����J�e�S��() =�u�����E���n�v�̏ꍇ
                    else
                    {
                        if (this.getContractInfo() != null)
                        {
                            // 1.6.1.28.3.1 ���ʏ��.get�ԍώw����(�����P�ʃe�[�u��.�����P��ID)�Ō��ʕԍώw����
                            // ���擾����B
                            List l_closingContractSpecs = this.getContractInfo().
                                getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                            // �擾�������ʕԍώw������W�v���ȉ��̒l���Z�o����
                            // �@@�ϓ����� += ���ʕԍώw����.��萔��
                            // �@@�]���z + = ���ʕԍώw����.�ԍϖ�萔��
                            // �@@�@@�@@�@@�@@�@@�@@�~ Min(���ʕԍώw����.���P��, �Ώۖ���.get�]���P��()) �~ �Ώۖ���.get�v�Z�P��()
                            // �@@�@@�@@�@@�@@�@@�@@
                            double l_quantity = 0.0;
                            double l_amount = 0.0;
                            for (int i = 0; i < l_closingContractSpecs.size(); i++)
                            {
                                WEB3TPClosingContractSpec l_closingContractSpec = (
                                    WEB3TPClosingContractSpec)
                                    l_closingContractSpecs.get(i);
                                l_quantity += l_closingContractSpec.getExecutedQuantity();
                                l_amount += l_closingContractSpec.getExecutedQuantity() *
                                    l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) * 
                                    l_product.getUnitSize();
                            }

                            // 1.6.1.29 ����ϓ�.set�ϓ�����(�ϓ�����)
                            l_execChange.setQuantity(l_quantity);

                            
                            // 1.6.1.31 ����ϓ�.set�]���z(�]���z�j
                            // �@@�����̕]���z�͎Z�o�����]���z
                            //  ����������v�Z���邽�߁A�|�ڂ��|���Ȃ�
                            l_execChange.setValuationPrice(l_amount);

                            //1.6.1.30 ����ϓ�.set�]���P��(�]���P��)
                            // �@@�����̕]���P���́A�Z�o�����]���z / ����ϓ�.get�ϓ�����()
                            if (l_quantity > 0)
                            {
                                l_execChange.setUnitPrice(l_amount / l_quantity);
                            }

                            // 1.6.1.32 �������Ə،��]���z.add�،��ϓ�(�،��ϓ�)
                            //   �������Ə،��]���z�́A�2.2.2��܂��͢2.3.2��̖߂�l
                            //   �����̏،��ϓ��́A�2.4��̖߂�l
                            l_valuation.addSecurityChange(l_execChange);
                        }

                    }
                }

            }
        }
        else
        {
            log.debug(" equity order unit row not found.");
        }

    }

}
@
