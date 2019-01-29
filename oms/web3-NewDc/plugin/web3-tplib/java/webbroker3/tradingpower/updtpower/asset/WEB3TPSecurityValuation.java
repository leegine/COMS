head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �،��]���z(WEB3TPSecurityValuation.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 �R�c�@@��i (FLJ) �V�K�쐬
                    2006/09/14 �Ԑi�@@�@@�@@ (���u)���f��No.37�ANo.38�ANo.39�ANo.40�ANo.41
 Revesion History : 2007/07/28 �����Q     (���u)���f��No.117
 Revesion History : 2008/01/09 �g�E�N�| (���u) �d�l�ύX�@@���f��No.244
 Revesion History : 2008/01/10 �g�E�N�| (���u) �d�l�ύX�@@���f��No.245,246
 Revesion History : 2008/01/22 �����F (���u) �d�l�ύX�@@���f��No.234�A235
 */
package webbroker3.tradingpower.updtpower.asset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.tradingpower.define.WEB3TPSplitNewStockDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.updtpower.contract.WEB3TPClosingContractSpec;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�،��]���z�j
 *
 * �S�،��]��
 */
public class WEB3TPSecurityValuation
    extends WEB3TPAssetValuation
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPSecurityValuation.class);

    /**
     * �u�����N�Ώۖ����z��
     */
    private final WEB3TPSecurityValuationProduct[] EMPTY_PRODUCTS =
        new WEB3TPSecurityValuationProduct[0];

    /**
     * �f�[�^�x�[�X�A�N�Z�X�Ǘ�
     */
    protected WEB3TPPersistentDataManager dataMgr = WEB3TPPersistentDataManager.
        getInstance();

    /**
     * (���ʏ��)
     */
    private WEB3TPContractInfo contractInfo;

    /**
     * (�Ώۖ������X�g)
     */
    private List products;

    /**
     * (�����^�C�v���ƑΏۖ������X�g�j<BR>
     * �����^�C�v���Ƃɕ�����ꂽ�Ώۖ����̃��X�g��ێ�����}�b�v<BR>
     * �L�[�F�����^�C�v<BR>
     * �l�F�Ώۖ�����ێ��������X�g<BR>
     */
    private Map classifiedProducts;

    /**
     * (�������Ə،��]���z�}�b�v)<BR>
     * �L�[�F�Ώۖ���<BR>
     * �l�F�������Ə،��]���z<BR>
     */
    private Map valuationPerProducts;

    /**
     * @@roseuid 41087CA2035E
     */
    public WEB3TPSecurityValuation()
    {
        products = new ArrayList();
        classifiedProducts = new HashMap();
        valuationPerProducts = new HashMap();
    }

    /**
     * (create�،��]��)<BR>
     * �،��]���C���X�^���X���쐬����B
     * @@param accountInfo - (�ڋq����)
     * @@param calcCondition - (�v�Z����)
     * @@param newOrderSpecs - (���������e)
     * @@param contractInfo - (���ʏ��)
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation
     * @@roseuid 40F3C85201DB
     */
    public static WEB3TPSecurityValuation create(
        WEB3TPAccountInfo accountInfo,
        WEB3TPCalcCondition calcCondition,
        WEB3TPNewOrderSpec[] newOrderSpecs,
        WEB3TPContractInfo contractInfo)
    {
        WEB3TPSecurityValuation l_valuation = new WEB3TPSecurityValuation();
        l_valuation.setAccountInfo(accountInfo);
        l_valuation.setCalcCondition(calcCondition);
        l_valuation.setNewOrderSpecs(newOrderSpecs);
        l_valuation.setContractInfo(contractInfo);
        return l_valuation;
    }

    /**
     * (calc��p�،��]���z)<BR>
     * �����̎w����ɂ�����S�]���Ώۖ����̑�p�]���z���W�v����B<BR>
     * <BR>
     * 1.��p�،��̕]���z���W�v����B<BR>
     *  ��p�،��]���z(n) �E�E�E ������p�،��]���z(n)<BR>
     *                           + ����p�،��]���z(n)<BR>
     *                           + ���M��p�،��]���z(n)<BR>
     *                           + ���̑���p�،��]���z(n)<BR>
     * 2.�v�Z������p�،��]���z(n)��Ԃ��B<BR>
     * <BR>
     * �� n�́A�����̎w���<BR>
     * �� �v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     *  �E������p�،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z(n, ��p,
     * ����)<BR>
     *  �E����p�،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z(n, ��p,
     * ��)<BR>
     *  �E���M��p�،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z(n, ��p,
     * ���M)<BR>
     *  �E���̑���p�،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z(n, ��p,
     * ���̑�)<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40ADDB9103E1
     */
    public double calcSubstituteValuationPrice(Date l_datDate)
    {

        // ������p�،��]���z
        double l_dblEquityVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.EQUITY,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�) 

        // ����p�،��]���z
        double l_dblBondVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.BOND,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�)

        // ���M��p�،��]���z
        double l_dblMFVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.MUTUAL_FUND,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�) 

        // ���̑���p�،��]���z
        double l_dblOtherVP =
            this.calcValuationPrice(
            l_datDate,
            ProductTypeEnum.OTHER,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�)

        // ��p�،��]���z = ������p�،��]���z
        //                  + ����p�،��]���z
        //                  + ���M��p�،��]���z
        //                  + ����p�،��]���z
        return l_dblEquityVP + l_dblBondVP + l_dblMFVP + l_dblOtherVP;

    }

    /**
     * (calc��p�،��]���z(�O���P���]��)) <BR>
     * <BR>
     * �O���P���ɂ�����S�]���Ώۖ����̑�p�]���z���W�v����B
     * <BR>
     * �P�j�����^�C�v���Ƃ̑�p�،��]���z���擾����B
     * <BR>
     * �@@�h������p�،��]���z�h = this.calc���i���Ƒ�p�،��]���z(�O���P���]��)<BR>
     * �@@�@@�@@�@@(ProductTypeEnum = 1�F����) <BR>
     * <BR>
     * �@@�h����p�،��]���z�h = this.calc���i���Ƒ�p�،��]���z(�O���P���]��)<BR>
     * �@@�@@�@@�@@(ProductTypeEnum = 2�F��) <BR>
     * <BR>
     * �@@�h���M��p�،��]���z�h = this.calc���i���Ƒ�p�،��]���z(�O���P���]��)<BR>
     * �@@�@@�@@�@@(ProductTypeEnum = 3�F�����M��) <BR>
     * <BR>
     * �@@�h���̑���p�،��]���z�h = this.calc���i���Ƒ�p�،��]���z(�O���P���]��)<BR>
     * �@@�@@�@@�@@(ProductTypeEnum = 0�F���̑�) <BR>
     * <BR>
     * �Q�j�O���P���ɂďW�v������p�،��]���z��ԋp����B<BR>
     * �@@�ԋp�l�F�h������p�،��]���z�h + �h����p�،��]���z�h + <BR>
     * �@@�@@�@@�@@�@@�h���M��p�،��]���z�h + �h���̑���p�،��]���z�h<BR>
     * <BR>
     * @@return double
     */
    public double calcPrevPriceSubstituteValuation()
    {
        final String STR_METHOD_NAME = "calcPrevPriceSubstituteValuation()";
        log.entering(STR_METHOD_NAME);

        //������p�،��]���z
        double l_dblEquity = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.EQUITY);
        //����p�،��]���z
        double l_dblBond = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.BOND);
        //���M��p�،��]���z
        double l_dblMutualFund = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.MUTUAL_FUND);
        //���̑���p�،��]���z
        double l_dblOther = this.calcPrevPricePerProductSubstituteValuation(ProductTypeEnum.OTHER);

        BigDecimal l_bdEquity = new BigDecimal("" + l_dblEquity);
        BigDecimal l_bdBond = new BigDecimal("" + l_dblBond);
        BigDecimal l_bdMutualFund = new BigDecimal("" + l_dblMutualFund);
        BigDecimal l_bdOther = new BigDecimal("" + l_dblOther);
        BigDecimal l_bdValuation = l_bdEquity.add(l_bdBond);
        l_bdValuation = l_bdValuation.add(l_bdMutualFund);
        l_bdValuation = l_bdValuation.add(l_bdOther);

        //�ԋp�l�F�h������p�،��]���z�h + �h����p�،��]���z�h + �h���M��p�،��]���z�h + �h���̑���p�،��]���z�h
        log.debug(" �O���P���ɂďW�v������p�،��]���z = " + l_bdValuation.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdValuation.doubleValue();
    }

    /**
     * �icalc�a��،��]���z�j<BR>
     * �����Ŏw����ɂ�����S�]���Ώۖ����̗a��،��]���z���W�v����B<BR>
     * 1. �a��،��̕]���z���W�v����B<BR>
     *  �a��،��]���z(n) = �����a����،��]���z(n)<BR>
     *                      + ���a����،��]���z(n)<BR>
     *                      + ���M�a����،��]���z(n)<BR>
     *                      + GP�a����،��]���z(n)<BR>
     * 2. �a��،��]�����x�z�Ƃ̔�r<BR>
     *  �a��،��]���z(n) = Min(�a��،��]���z(n), �a��،��]�����x�z�j<BR>
     * 3. �v�Z�����a��،��]���z�in)��Ԃ��B<BR>
     * <BR>
     * �� n�͈����Ŏw�肳�ꂽ�w���<BR>
     * �� �v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     *  �Eis�����a����،��]�� �E�E�E �v�Z����.is�a��،��]�����v�Z�Ώہi�����j<BR>
     *  �Eis���a����،��]�� �E�E�E �v�Z����.is�a��،��]�����v�Z�Ώہi���j<BR>
     *  �Eis���M�a����،��]�� �E�E�E �v�Z����.is�a��،��]�����v�Z�Ώہi���M�j<BR>
     *  �EisGP�a����،��]�� �E�E�E �v�Z����.is�a��،��]�����v�Z�ΏہiGP�j<BR>
     *  �E�����a����،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z�in, �ی�,
     * ����)<BR>
     *
     * is�����a����،��]��=false�̏ꍇ�A�����a����،��]���z(n)=0<BR>
     *  �E���a����،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z�in, �ی�,
     * ��)<BR>
     *
     * is���a����،��]��=false�̏ꍇ�A���a����،��]���z(n)=0<BR>
     *  �E���M�a����،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z�in, �ی�,
     * ���M)<BR>
     *
     * is���M�a����،��]��=false�̏ꍇ�A���M�a����،��]���z(n)=0<BR>
     *  �EGP�a����،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z�in, �ی�,
     * GP)<BR>
     *
     * isGP�a����،��]��=false�̏ꍇ�AGP�a����،��]���z(n)=0<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40ADDBA502C8
     */
    public double calcValuationPrice(Date l_datDate)
    {

        // �����a��،��]���z
        double l_dblEquityVP = 0.0;
        if (getCalcCondition().isEquityEvalDiv())
        {
            l_dblEquityVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.EQUITY,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�) 
        }

        // ���a��،��]���z
        double l_dblBondVP = 0.0;
        if (getCalcCondition().isBondEvalDiv())
        {
            l_dblBondVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.BOND,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�) 
        }

        // ���M�a��،��]���z
        double l_dblMFVP = 0.0;
        if (getCalcCondition().isFundEvalDiv())
        {
            l_dblMFVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.MUTUAL_FUND,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�) 
        }

        // GP�ؗa�茔�]���z
        double l_dblGPVP = 0.0;
        if (getCalcCondition().isGpEvalDiv())
        {
            l_dblGPVP =
                calcValuationPrice(
                l_datDate,
                ProductTypeEnum.RUITO,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);   //�a�莑�Y�Ή�(�~�j�����܂܂Ȃ����ߋ敪�̒ǉ�)
        }

        // �a��،��]���z = �����a��،��]���z
        //                  + ���a��،��]���z
        //                  + ���M�a��،��]���z
        //                  + GP�ؗa�茔�]���z
        double l_dblVP = l_dblEquityVP + l_dblBondVP + l_dblMFVP + l_dblGPVP;

        // �a��،��]���z = Min(�a��،��]���z, �a��،��]�����x�z)
        return Math.min(l_dblVP, getCalcCondition().getMaxAssessment());

    }

    /**
     * (calc��������p�،��]���z)<BR>
     * �����̎w����ɂ�����]���Ώۖ����̔�������p�]���z���W�v����B<BR>
     * <BR>
     * 1.��������p�،��̕]���z���W�v����B<BR>
     *  ��������p�،��]���z(n) �E�E�E ������������p�،��]���z(n)<BR>
     * 2.�v�Z������������p�،��]���z(n)��Ԃ��B<BR>
     * <BR>
     * �� n�́A�����̎w���<BR>
     * �� �v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     *  �E������p�،��]���z(n) �E�E�E �،��]��.calc���i���Ə،��]���z(n, ��p,
     * ����,�~�j���łȂ�)<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     */
    public double calcUnExecSubstituteValuationPrice(Date l_datDate)
    {

        // ������p�،��]���z
        double l_dblUnExecEquityVP =
            this.calcUnExecValuationPrice(
            l_datDate,
            ProductTypeEnum.EQUITY,
            WEB3TPDepositTypeDef.SUBSTITUTE,
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        
        return l_dblUnExecEquityVP;

    }

    /**
     * �icalc�������a��،��]���z�j<BR>
     * �����Ŏw����ɂ�����]���Ώۖ����̔������a��،��]���z���W�v����B<BR>
     * 1. �������a��،��̕]���z���W�v����B<BR>
     *  �������a��،��]���z(n) = �����������a����،��]���z(n)<BR>

     * 2. �a��،��]�����x�z�Ƃ̔�r<BR>
     *  �a��،��]���z(n) = Min(�������a��،��]���z(n), �a��،��]�����x�z�j<BR>
     * 3. �v�Z�����������a��،��]���z�in)��Ԃ��B<BR>
     * <BR>
     * �� n�͈����Ŏw�肳�ꂽ�w���<BR>
     * �� �v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     *  �Eis�����a����،��]�� �E�E�E �v�Z����.is�a��،��]�����v�Z�Ώہi�����j<BR>
     *  �E�����������a����،��]���z(n) �E�E�E �،��]��.calc���i���Ɣ������،��]���z�in, �ی�,
     * ����)<BR>
     *
     * is�����a����،��]��=false�̏ꍇ�A�����������a����،��]���z(n)=0<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     */
    public double calcUnExecValuationPrice(Date l_datDate)
    {

        // �����������a��،��]���z
        double l_dblUnExecEquityVP = 0.0;
        if (getCalcCondition().isEquityEvalDiv())
        {
            l_dblUnExecEquityVP =
                calcUnExecValuationPrice(
                l_datDate,
                ProductTypeEnum.EQUITY,
                WEB3TPDepositTypeDef.TRUST,
                WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        }

        // �a��،��]���z = Min(�������a��،��]���z, �a��،��]�����x�z)
        return Math.min(l_dblUnExecEquityVP, getCalcCondition().getMaxAssessment());

    }

    /**
     * (do�������Ə،��]���z���[�h)<BR>
     * �W�v�Ώۃ��[�h����B<BR>
     * 1.�،��]���z.do�����ۗL�ϓ����m�聄���[�h<BR>
     * 2.�،��]���z.do�U�֕ϓ��������ȍ~�����[�h<BR>
     * 3.�،��]���z.do�o�ɕϓ��������ȍ~>���[�h<BR>
     * 4.�،��]���z.do����ϓ��������ȍ~�����[�h<BR>
     * @@roseuid 40ADDE670047
     */
    public void doSecurityValuationPerProductLoad()
    {
        // 1.�،��]���z.do�����ۗL�ϓ����m�聄���[�h
        this.doAssetChangesLoad();
        // 2.�،��]���z.do�U�֕ϓ��������ȍ~�����[�h
        this.doTransferChangesLoad();
        // 3.�،��]���z.do�o�ɕϓ��������ȍ~>���[�h
        this.doDeliveryChangesLoad();
        // 4.�،��]���z.do����ϓ��������ȍ~�����[�h
        this.doTransactionChangesLoad();

    }

    /**
     * (get�������Ə،��]���z)<BR>
     * �����Ŏw�肳�ꂽ�Ώۖ������L�[�Ƃ��āA�������Ə،��]���z��ێ�����Map��<BR>
     * �������AMap���擾�����������Ə،��]���z��Ԃ��B<BR>
     * �Y������������Ə،��]���z��Map�ɑ��݂��Ȃ��ꍇ��<code>null</code>��Ԃ��B<BR>
     * @@param l_product - (�Ώۖ���)
     * @@return WEB3TPSecurityValuationPerProduct
     * @@roseuid 40D7E3330165
     */
    public WEB3TPSecurityValuationPerProduct getSecurityValuationPerProduct(
        WEB3TPSecurityValuationProduct l_product)
    {
        WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
            (WEB3TPSecurityValuationPerProduct) valuationPerProducts.get(
            l_product);

        return l_valuationPerProduct;
    }

    /**
     * (calc���i���Ɨa�莑�Y�]���z)<BR>
     * �����̎w����ɂ�����]���Ώۖ����̗a�莑�Y�]���z���W�v����B<BR>
     * <BR>
     * 1.�Ώۖ����ꗗ���擾<BR>
     * 2.�����Ɏw����iT+0�`T+5�j���󂯎��A�ꗗ���̑Ώۖ����̎w����ɑΉ�����������
     * ��<BR>
     *  �،��]�����W�v����<BR>
     *  ���i���Ə،��]���z(n) = ��(*)�Ώۖ���.get�������Ə،��]��.get�،��]���z(n,
     * �a��敪�j<BR>
     *  (*) �W�v����<BR>
     *      �ꗗ�ɂ���S�Ă̑Ώۖ���<BR>
     * 3.�v�Z�������i���Ə،��]���z(n)��Ԃ�<BR>
     * <BR>
     * �� n�́A�����̎w���<BR>
     * �� �v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     *  �E�a��敪 �E�E�E ����.�a��敪<BR>
     *  �E�Ώۖ����ꗗ �E�E�E �،��]��.get�Ώۖ����ꗗ(�����^�C�v)<BR>
     * @@param l_datDate - (�w���)
     * @@param l_productType - (�����^�C�v)
     * @@param l_strMiniStockDivDef - (�~�j���敪)
     * @@return double
     */
    public double calcAssetValuationPrice(
        Date l_datDate,
        ProductTypeEnum l_productType,
        String l_strMiniStockDivDef)
    {

        // �����^�C�v���ƑΏۖ������X�g���擾
        WEB3TPSecurityValuationProduct[] l_products =
            getProducts(l_productType, l_strMiniStockDivDef);

        // �擾�����Ώۖ����ɂ��ĕ]���z���W�v
        double l_dblValuationPrice = 0.0;
        for (int i = 0; i < l_products.length; i++)
        {

            // �������Ɨa�莑�Y�]���z���擾
            WEB3TPSecurityValuationPerProduct l_valuation =
                (WEB3TPSecurityValuationPerProduct) valuationPerProducts.get(
            l_products[i]);

            // �w����ň����ɐݒ�
            l_dblValuationPrice
                += l_valuation.getAssetValuationPrice(l_datDate);

        }

        return l_dblValuationPrice;
    }

    /**
     * (calc���i���Ə،��]���z)<BR>
     * �����̎w����ɂ����鏤�i���ƕ]���Ώۖ����̏،��]���z���W�v����B<BR>
     * <BR>
     * 1.�Ώۖ����ꗗ���擾<BR>
     * 2.�����ɒ�w����iT+0�`T+5�j���󂯎��A�ꗗ���̑Ώۖ����̎w����ɑΉ�����������
     * ��<BR>
     *  �،��]�����W�v����<BR>
     *  ���i���Ə،��]���z(n) = ��(*)�Ώۖ���.get�������Ə،��]��.get�،��]���z(n,
     * �a��敪�j<BR>
     *  (*) �W�v����<BR>
     *      �ꗗ�ɂ���S�Ă̑Ώۖ���<BR>
     * 3.�v�Z�������i���Ə،��]���z(n)��Ԃ�<BR>
     * <BR>
     * �� n�́A�����̎w���<BR>
     * �� �v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     *  �E�a��敪 �E�E�E ����.�a��敪<BR>
     *  �E�Ώۖ����ꗗ �E�E�E �،��]��.get�Ώۖ����ꗗ(�����^�C�v)<BR>
     * @@param l_datDate - (�w���)
     * @@param l_productType - (�����^�C�v)
     * @@param l_strDepositType - (�a��敪)
     * @@param l_strMiniStockDivDef - (�~�j���敪) �a�莑�Y�Ή��ǉ�
     * @@return double
     * @@roseuid 40ADD82D0306
     */
    protected double calcValuationPrice(
        Date l_datDate,
        ProductTypeEnum l_productType,
        String l_strDepositType,
        String l_strMiniStockDivDef)
    {

        // �����^�C�v���ƑΏۖ������X�g���擾
        WEB3TPSecurityValuationProduct[] l_products =
            getProducts(l_productType, l_strMiniStockDivDef);

        // �擾�����Ώۖ����ɂ��ĕ]���z���W�v
        double l_dblValuationPrice = 0.0;
        for (int i = 0; i < l_products.length; i++)
        {

            // �������Ə،��]���z���擾
            WEB3TPSecurityValuationPerProduct l_valuation =
                getSecurityValuationPerProduct(l_products[i]);
            l_dblValuationPrice
                += l_valuation.getValuationPrice(l_datDate, l_strDepositType);

        }

        return l_dblValuationPrice;
    }

    /**
     * (calc���i���Ƒ�p�،��]���z(�O���P���]��)) <BR>
     * <BR>
     * �]���Ώۖ����̑�p�،��]���z���W�v����B<BR>
     * <BR>
     * �P�j�����^�C�v���Ƃ̑�p�،��]���z���擾����B<BR>
     * �@@�h�Ώۖ����ꗗ[]�h = this.get�Ώۖ����ꗗ(:ProductTypeEnum = ����.�����^�C�v,<BR>
     * �@@:String = 0�FDEFAULT(�~�j���łȂ�)) <BR>
     * <BR>
     * �Q�j��p�،��]���z���W�v����B<BR>
     * <BR>
     * [LOOP�����Fn=�P�j�Ŏ擾�����h�Ώۖ����ꗗ[]�h�̗v�f����]<BR>
     * <BR>
     * �@@this.�������Ə،��]���z�}�b�v���Ώۖ����I�u�W�F�N�g�ɑΉ�����<BR>
     * �������Ə،��]���z�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �|�h�������Ə،��]���z�h = this.�������Ə،��]���z�}�b�v.get(<BR>
     * �@@:Object = �h�Ώۖ����ꗗ[n]�h) <BR>
     * <BR>
     * �A��p�،��]���z���W�v����B<BR>
     * <BR>
     * �|�h��p�،��]���z�h = �h��p�،��]���z�h + �h�������Ə،��]���z�h.<BR>
     * �@@get��p�،��]���z(�O���P���]��)() <BR>
     * <BR>
     * �R�j�W�v�����h��p�،��]���z�h��ԋp����B<BR>
     * <BR>
     * �ԋp�l�F�h��p�،��]���z�h<BR>
     * <BR>
     * @@param l_productType - �����^�C�v<BR>
     * (�����^�C�v)
     * @@return double
     */
    protected double  calcPrevPricePerProductSubstituteValuation(ProductTypeEnum l_productType)
    {
        final String STR_METHOD_NAME = "calcPrevPricePerProductSubstituteValuation(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�Ώۖ����ꗗ���擾����B
        WEB3TPSecurityValuationProduct[] l_valuationProducts =
            this.getProducts(l_productType, WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

        int l_intLength = l_valuationProducts.length;

        //�h��p�،��]���z�h
        double l_dblPrevPriceSubstituteValuation = 0.0D;
        BigDecimal l_bdPrevPriceSubstituteValuation =
            new BigDecimal("" + l_dblPrevPriceSubstituteValuation);

        for (int i = 0; i < l_intLength; i++)
        {
            //this.�������Ə،��]���z�}�b�v���Ώۖ����I�u�W�F�N�g�ɑΉ�����
            //�������Ə،��]���z�I�u�W�F�N�g���擾����B
            WEB3TPSecurityValuationPerProduct l_valuationPerProduct =
                (WEB3TPSecurityValuationPerProduct)this.valuationPerProducts.get(l_valuationProducts[i]);

            //�h�������Ə،��]���z�h.get��p�،��]���z(�O���P���]��)()
            double l_dblPrevPrice = l_valuationPerProduct.getPrevPriceSubstituteValuation();
            BigDecimal l_bdPrevPrice = new BigDecimal("" + l_dblPrevPrice);

            //��p�،��]���z���W�v����
            l_bdPrevPriceSubstituteValuation =
                l_bdPrevPriceSubstituteValuation.add(l_bdPrevPrice);
        }

        //�W�v�����h��p�،��]���z�h��ԋp����B
        log.debug(" ��p�،��]���z = " + l_bdPrevPriceSubstituteValuation.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdPrevPriceSubstituteValuation.doubleValue();
    }

    /**
     * (calc���i���Ɣ������،��]���z)<BR>
     * �����̎w����ɂ����鏤�i���ƕ]���Ώۖ����̔������،��]���z���W�v����B<BR>
     * <BR>
     * 1.�Ώۖ����ꗗ���擾<BR>
     * 2.�����ɂĎw����iT+0�`T+5�j���󂯎��A�ꗗ���̑Ώۖ����̎w����ɑΉ�����������
     * ��<BR>
     *  �������،��]�����W�v����<BR>
     *  ���i���Ɣ������،��]���z(n) = ��(*)�Ώۖ���.get�������Ɣ������،��]��.get�������،��]���z(n,
     * �a��敪�j<BR>
     *  (*) �W�v����<BR>
     *      �ꗗ�ɂ���S�Ă̑Ώۖ���<BR>
     * 3.�v�Z�������i���Ɣ������،��]���z(n)��Ԃ�<BR>
     * <BR>
     * �� n�́A�����̎w���<BR>
     * �� �v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     *  �E�a��敪 �E�E�E ����.�a��敪<BR>
     *  �E�Ώۖ����ꗗ �E�E�E �،��]��.get�Ώۖ����ꗗ(�����^�C�v)<BR>
     * @@param l_datDate - (�w���)
     * @@param l_productType - (�����^�C�v)
     * @@param l_strDepositType - (�a��敪)
     * @@param l_strMiniStockDivDef - (�~�j���敪) �a�莑�Y�Ή��ǉ�
     * @@return double
     */
    protected double calcUnExecValuationPrice(
        Date l_datDate,
        ProductTypeEnum l_productType,
        String l_strDepositType,
        String l_strMiniStockDivDef)
    {

        // �����^�C�v���ƑΏۖ������X�g���擾
        WEB3TPSecurityValuationProduct[] l_products =
            getProducts(l_productType, l_strMiniStockDivDef);

        // �擾�����Ώۖ����ɂ��ĕ]���z���W�v
        double l_dblUnExecValuationPrice = 0.0;
        for (int i = 0; i < l_products.length; i++)
        {

            // �������Ɣ������،��]���z���擾
            WEB3TPSecurityValuationPerProduct l_valuation =
                getSecurityValuationPerProduct(l_products[i]);
            l_dblUnExecValuationPrice
                += l_valuation.getUnExecValuationPrice(l_datDate, l_strDepositType);

        }

        return l_dblUnExecValuationPrice;
    }

    /**
     * (get�Ώۖ����ꗗ)<BR>
     * �]���Ώۖ����ꗗ���擾����B<BR>
     * �����^�C�v���ƑΏۖ�����������Ŏw�肳�ꂽ�����^�C�v�̑Ώۖ������擾����B<BR>
     * @@param l_productType - (�����^�C�v)
     * @@param l_strMiniStockDivDef - (�~�j���敪)
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct[]
     * @@roseuid 40B5A1A303BA
     */
    public WEB3TPSecurityValuationProduct[] getProducts(ProductTypeEnum l_productType, String l_strMiniStockDivDef)
    {
        List l_list = (List) classifiedProducts.get(l_productType);
        if (l_list != null)
        {

            List l_target = new ArrayList();
            
            //�~�j���敪�������Ɠ���̈ꗗ�̂݁A�z��ɐݒ�                
            for (int i = 0; i < l_list.size(); i++)
            {
                WEB3TPSecurityValuationProduct l_product = null;
                l_product = (WEB3TPSecurityValuationProduct) l_list.get(i);
                
                //���ꂩ�ǂ����`�F�b�N
                if (l_product.getMiniStockDivDef().equals(l_strMiniStockDivDef))
                {
                    l_target.add(l_product);    
                }

            }

            if (l_target.size() != 0)
            {
                WEB3TPSecurityValuationProduct[] l_products =
                    new WEB3TPSecurityValuationProduct[l_target.size()];     
                
                return (WEB3TPSecurityValuationProduct[])l_target.toArray( l_products );
            }
            else
            {
                return EMPTY_PRODUCTS;
            }

        }
        else
        {
            return EMPTY_PRODUCTS;
        }
    }

    /**
     * (load�Ώۖ���)<BR>
     * <BR>
     * �Ώۖ��������[�h����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(�،��]���z)load�Ώۖ����v�Q��<BR>
     * @@param l_lngProductId - (����ID)
     * @@param l_strMiniStockDivDef - (�~�j���敪)
     * @@return WEB3TPSecurityValuationProduct
     * @@roseuid 40D7DDF803A7
     */
    protected WEB3TPSecurityValuationProduct loadProduct(long l_lngProductId, String l_strMiniStockDivDef)
    {

        final String STR_METHOD_NAME = "loadProduct(long l_lngProductId, String l_strMiniStockDivDef)";

        // ���������擾
        ProductParams l_pRow =
            dataMgr.getProduct(
            l_lngProductId);

        //�v�Z�������擾
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();
        
        //�]���P��Callback���擾
        WEB3TPUnitPriceCallback l_unitPriceCallback = l_calcCondition.getUnitPriceCallback();
        
        // �Ώۖ����̃C���X�^���X�𐶐�
        WEB3TPSecurityValuationProduct l_product =
            WEB3TPSecurityValuationProduct.create();
        l_product.setProductId(l_lngProductId);
        l_product.setProductType(l_pRow.getProductType());
        l_product.setUnitSize(l_pRow.getCalcSize());
        l_product.setSubstituteValuationRatio(l_pRow.getMarginRatio() / 100);
        l_product.setValuationRatio(l_pRow.getSecuritiesEstimationRatio() / 100);
        l_product.setPrimaryMarketId(l_pRow.getPrimaryMarketId());
//            l_product.setNotRequiredFlag(false);
        l_product.setMiniStockDivDef(l_strMiniStockDivDef); //�a�莑�Y�Ή��i�~�j���敪�ǉ��j
        //�]���P�������߂�
        double l_dblUnitPrice = l_unitPriceCallback.getUnitPrice(l_pRow);
        l_product.setUnitPrice(l_dblUnitPrice);
        l_product.setPrePrice(l_pRow.getEstimationPrice());
        
//            // �����̏ꍇ
//            if (ProductTypeEnum.EQUITY.equals(l_product.getProductType()))
//            {
//                // ����������擾
//                List l_tpRows =
//                    dataMgr
//                    .getEqTypeTradedProduct(
//                    l_lngProductId,
//                    this);
//                if (l_tpRows != null && l_tpRows.size() > 0)
//                {
//                    if (l_tpRows.get(0) instanceof EqtypeTradedProductRow)
//                    {
//                        for (Iterator l_it = l_tpRows.iterator(); l_it.hasNext(); )
//                           {
//                            // ������������敪=�X�����A�X�����J�敪=�Ǘ��̏ꍇ
//                            EqtypeTradedProductRow tpRow =
//                                (EqtypeTradedProductRow) l_it.next();
//                            if (WEB3ListTypeDef.OTC.equals(tpRow.getListType())
//                                && WEB3OpenOtcDivDef.MANAGEMENT_PRODUCT.equals(
//                                tpRow.getOpenOtcDiv()))
//                            {
//                                // �]���ΏۊO�t���O=true�ɐݒ�
//                                l_product.setNotRequiredFlag(true);
//                                break;
//                            }
//                        }
//                    }
//                    else if (l_tpRows.get(0) instanceof EqtypeTradedProductUpdqRow)
//                    {
//                        for (Iterator l_it = l_tpRows.iterator(); l_it.hasNext(); )
//                        {
//                            // ������������敪=�X�����A�X�����J�敪=�Ǘ��̏ꍇ
//                            EqtypeTradedProductUpdqRow tpRow =
//                                (EqtypeTradedProductUpdqRow) l_it.next();
//                            if (WEB3ListTypeDef.OTC.equals(tpRow.getListType())
//                                && WEB3OpenOtcDivDef.MANAGEMENT_PRODUCT.equals(
//                                tpRow.getOpenOtcDiv()))
//                            {
//                                // �]���ΏۊO�t���O=true�ɐݒ�
//                                l_product.setNotRequiredFlag(true);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }

        // ���������Ώۖ����̃C���X�^���X��Ԃ�
        return l_product;

    }

    /**
     * (do�����ۗL�ϓ�<�m��>���[�h)<BR>
     * <BR>
     * �����ۗL�ϓ�<�m��>���f�[�^���[�h����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(�،��]���z)do�����ۗL�ϓ�<�m��>���[�h�v�Q��<BR>
     * <BR>
     * @@roseuid 40B6F6D4004D
     */
    protected void doAssetChangesLoad()
    {

        String ls_MiniStockDiv = null;

        // 1.�����ۗL�ϓ����m�聄�iGP�ȊO�j�����[�h����
        // 1.1�a��،��֘A�f�[�^���\�[�X�A�N�Z�X�Ǘ�.get������n���ʕۗL���Y���R�[�h(�ڋq����)
        List l_dayAssets = dataMgr.getDayAssets(getAccountInfo());
        
        // 1.2��n���ʕۗL���Y�e�[�u���Ƀ��R�[�h�����݂���ꍇ
        if (l_dayAssets != null)
        {
            log.debug(l_dayAssets.size() + " day asset row found.");
            // 1.2.1 LOOP�����Fget������n���ʕۗL���Y���R�[�h()�̖߂�l�̗v�f����
            for (Iterator l_it = l_dayAssets.iterator(); l_it.hasNext(); )
            {

                DailyAssetRow l_dayAsset = (DailyAssetRow) l_it.next();

                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                //�~�j���敪���擾(�a�莑�Y�Ή��ɂ��ǉ�) 
                if (l_dayAsset.getMiniStockDiv().equals(BooleanEnum.TRUE))
                {
                    //�~�j���̏ꍇ
                    ls_MiniStockDiv = WEB3MiniStockDivDef.MINI_STOCK;
                }
                else
                {
                    //�~�j���ł͂Ȃ��ꍇ
                    ls_MiniStockDiv = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;
                }

                // 1.2.1.1 ���[�h�Ώۖ��������[�h�ς݂��ǂ������肷��B
                boolean isLoadedProduct = this.isLoadedProduct(l_dayAsset.
                    getProductId(), ls_MiniStockDiv);

                // 1.2.1.2 ���[�h�Ώۖ��������[�h���ς̏ꍇ
                // (is���[�h�ςݑΏۖ���() == false)
                if (!isLoadedProduct)
                {
                    // 1.2.1.2.1 load�Ώۖ���(����ID)
                    // �����̖���ID�͊�����n���ʕۗL���Y.����ID
                    // �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        loadProduct(l_dayAsset.getProductId(), ls_MiniStockDiv);

                    // 1.2.1.2.2 �������Ə،��]��.create�������Ə،��]���i�Ώۖ����j
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add�������Ə،��]��(�Ώۖ���, �������Ə،��]��)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.2.1.3 �ȊO�i���[�h�Ώۖ��������[�h�ρj�̏ꍇ
                else
                {
                    // 1.2.1.3.1 get�Ώۖ���(����ID)
                    // �����̖���ID�͎��Y�ړ��e�[�u��.����ID
                    // �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        getProduct(l_dayAsset.getProductId(), ls_MiniStockDiv);

                    // 1.2.1.3.2 get�������Ə،��]��(�Ώۖ���)
                    // �����̑Ώۖ����� get�Ώۖ���()�̖߂�l 
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.2.1.4.�����ۗL�ϓ�.create�����ۗL�ϓ�()
                WEB3TPSecurityAssetChange l_change =
                    WEB3TPSecurityAssetChange.create();
                
                // 1.2.1.5 �]�͌v�Z�������Z�b�g����B
                // �]�͌v�Z���� = this.get�]�͌v�Z����()�̖߂�l
                l_change.setCalcCondition(getCalcCondition());

                // 1.2.1.6 ��n���ʕۗL���Y�e�[�u��.�����V���� = "1:�����V����"�̏ꍇ             
                if(WEB3TPSplitNewStockDef.SPLIT_NEW_STOCK.equals(l_dayAsset.getSplitNewStockDiv()) == true)
                {
                    // 1.2.1.6.1 �����ۗL�ϓ�.set�����V�����敪(boolean)
                    l_change.setSplitNewStock(true);
                }
                // 1.2.1.7�ȊO�́i�����V�����łȂ��j�ꍇ
                else
                {
                    // 1.2.1.7.1 �����ۗL�ϓ�.set�����V�����敪(boolean)
                    l_change.setSplitNewStock(false);
                }
                
                // 1.2.1.8 �����ۗL�ϓ�.set�a��敪(�a��敪)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_dayAsset.getSubAccountId()));
                
                // 1.2.1.9 �����ۗL�ϓ�.set������(Timestamp)
                l_change.setOriginalExecDate(l_dayAsset.getOriginalExecDate());
                
                // 1.2.1.10 �����ۗL�ϓ�.calc�ϓ����f��(��n��)
                // �����̎�n���́A��n���ʎc���e�[�u��.��n��
                l_change.calcReflectDay(l_dayAsset.getDeliveryDate());

                // 1.2.1.11 �����ۗL�ϓ�.set�ŋ敪(�ŋ敪)
                // �����̐ŋ敪�́A��n���ʕۗL���YRow.�ŋ敪
                l_change.setTaxType(l_dayAsset.getTaxType());
                
                // 1.2.1.12 �ŋ敪��ϊ�����B 
                // 1.2.1.13 �����ۗL�ϓ�.set��������敪(�ŋ敪)
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(l_dayAsset.
                    getTaxType()));

                // 1.2.1.14 �����ۗL�ϓ�.set�ϓ�����(����)
                l_change.setQuantity(l_dayAsset.getQuantity());

                // 1.2.1.15.�����ۗL�ϓ�.set�]���|��(�]���|��)
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));
                
                // 1.2.1.16 ���M�M���̏ꍇ
                //���M�M���̏ꍇ
                //(�Ώۖ���.�����^�C�v == �R�F�����M��
                //�O�������̏ꍇ
                //�Ώۖ���.�����^�C�v == �S�F�O����
                if(ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType())
                    || ProductTypeEnum.FOREIGN_EQUITY.equals(l_product.getProductType()))
                {
                    // 1.2.1.16.1 �]���P�����Z�b�g����B
                    // ����double = �Ώۖ���.get�]���P��()�̖߂�l 
                    l_change.setUnitPrice(
                            l_product.getUnitPrice());                	
                }
                // �ȊO�i���M�M���łȂ��A���A�O�������łȂ��j�̏ꍇ
                else
                {
                    // 1.2.1.17.1 �]���P�����Z�b�g����B
                    // ����double = �Ώۖ���.get�]���P��() �~ �Ώۖ���.get�v�Z�P��() 
                    l_change.setUnitPrice(
                            l_product.getUnitPrice() * l_product.getUnitSize());                	
                }                
                
                //1.2.1.16 ���M�M���̏ꍇ
                if(ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    // 1.2.1.16.2 ���M�M�����Y�]���z���v�Z����B 
                    // 1.2.1.16.3 �]���z���Z�b�g����B
                	l_change.setValuationPrice(
                            calcMutualFundValuationPricePerChange(l_product, l_change)
                            );                	                	                	
                }
                //�O�������̏ꍇ
                //�Ώۖ���.�����^�C�v == �S�F�O����
                else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_product.getProductType()))
                {
                    //set�]���z
                    //[����]
                    //�]���z = calc�O�������]���z()�̖߂�l
                    l_change.setValuationPrice(calcFeqValuation(l_product, l_change));
                }
                else
                {
                    // 1.2.1.17.2 �]���P�����Z�b�g����B
                    // ���� double = �����ۗL�ϓ�.get�ϓ�����() * �����ۗL�ϓ�.get�]���P��() 
                	l_change.setValuationPrice(
                    l_change.getQuantity()
                    * l_change.getUnitPrice());                	                	
                }
                
                // 1.2.1.18 �������Ə،��ϓ�.add�،��ϓ�(�،��ϓ�)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug(" day asset row not found.");
        }

        // 1.3 �ۗL���Y�e�[�u��Row�I�u�W�F�N�g�̃��X�g���擾����B 
        // ����:�ڋq���� = this.get�ڋq����()�̖߂�l 
        List l_gpAssets =
            dataMgr.getAssets(
            getAccountInfo());
        
        // 1.4 �ۗL���Y�e�[�u���Ƀ��R�[�h�����݂���ꍇ
        //�iget�����ۗL���Y���R�[�h()�@@!= null�j
        if (l_gpAssets != null)
        {
            log.debug(l_gpAssets.size() + " GP asset row found.");

            // 1.4.1 LOOP�����Fget�����ۗL���Y���R�[�h()�̖߂�l�̗v�f����
            for (Iterator l_it = l_gpAssets.iterator(); l_it.hasNext(); )
            {
                AssetRow l_gpAsset = (AssetRow) l_it.next();

                // 1.4.1.1 load�Ώۖ���(����ID)
                //     �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                WEB3TPSecurityValuationProduct l_product =
                    loadProduct(l_gpAsset.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.4.1.2 �������Ə،��]��.create�������Ə،��]���i�Ώۖ����j
                WEB3TPSecurityValuationPerProduct l_valuation =
                    WEB3TPSecurityValuationPerProduct.create(l_product, this);

                // 1.4.1.3 add�������Ə،��]��(�Ώۖ���, �������Ə،��]��)
                addSecurityValuationPerProduct(l_product, l_valuation);

                // 1.4.1.4 �����ۗL�ϓ�.create�����ۗL�ϓ�()
                WEB3TPSecurityAssetChange l_change =
                    WEB3TPSecurityAssetChange.create();
                
                // 1.4.1.5 �����ۗL�ϓ�.set�]�͌v�Z����(�]�͌v�Z����)
                l_change.setCalcCondition(getCalcCondition());

                // 1.4.1.6 �����ۗL�ϓ�.calc�ϓ����f��(T+0))
                l_change.calcReflectDay(getCalcCondition().getBizDate(0));

                // 1.4.1.7 �����ۗL�ϓ�.set�a��敪(�a��敪)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_gpAsset.getSubAccountId()));

                // 1.4.1.8 �����ۗL�ϓ�.set�ŋ敪(�ŋ敪)
                l_change.setTaxType(l_gpAsset.getTaxType());
                
                // 1.4.1.9  �����ۗL�ϓ�.to��������敪(�ŋ敪)
                // 1.4.1.10 �����ۗL�ϓ�.set��������敪(�ŋ敪)
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(l_gpAsset.
                    getTaxType()));

                // 1.4.1.11 �����ۗL�ϓ�.set�ϓ�����(����) ����=�m��ۗL����-�y�i���e�B�󂯑O�ۗL����*0.001 �����_�ȉ���
                l_change.setQuantity(l_gpAsset.getQuantity() -
                                     Math.floor(l_gpAsset.getCountBeforePenalty() * 0.001));

                // 1.4.1.12 �����ۗL�ϓ�.set�]���P��(�P��)
                l_change.setUnitPrice(
                        l_product.getUnitPrice() * l_product.getUnitSize());                	                

                // 1.4.1.13 �����ۗL�ϓ�.set�]���|��(�]���|��)
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));

                // 1.4.1.14 �����ۗL�ϓ�.set�]���z(�]���z)
                l_change.setValuationPrice(
                    l_change.getQuantity()
                    * l_change.getUnitPrice());                

                // 1.4.1.15 �������Ə،��ϓ�.add�،��ϓ�(�،��ϓ�)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug("GP asset row not found.");
        }
    }

    /**
     * (do�U�֕ϓ�<�����ȍ~>���[�h)<BR>
     * �U�֕ϓ�<�����ȍ~>�����[�h����B<BR>
     * <BR>
     * ��{�݌v�V�[�P���X�udo�U�֕ϓ�<�����ȍ~>���[�h�v���Q�Ƃ���
     * <BR>
     */    
    protected void doTransferChangesLoad()
    {
        // 1.1 �a��،��֘A�f�[�^���\�[�X�A�N�Z�X�Ǘ�.get���Y�ړ����R�[�h<�U��>()
        List l_assetTransfers = dataMgr.getAssetTransferAioOrderUnits(this);
        
        // 1.2 AIO�����P�ʃe�[�u���ɐU�փ��R�[�h�����݂���ꍇ
        //�iget���Y�ړ�<�U��>���R�[�h()�@@!= null�j
        if (l_assetTransfers != null)
        {
            log.debug(l_assetTransfers.size() + " asset Transfer row found.");

            // 1.2.1 �擾�������Y�ړ��e�[�u���̊e���R�[�h�ɑ΂��Ĉȉ��̏������J��Ԃ��B
            for (Iterator l_it = l_assetTransfers.iterator(); l_it.hasNext(); )
            {
                AioOrderUnitParams l_assetTransfer = (AioOrderUnitParams) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.2.1.1 is���݃��[�h�ςݑΏۖ���(����ID
                //  �����̖���ID�͎��Y�ړ��e�[�u��.����ID
                boolean isLoadedProduct = this.isLoadedProduct(l_assetTransfer.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.2.1.2 is���݃��[�h�ςݑΏۖ���(����ID) = false�̏ꍇ
                if (!isLoadedProduct)
                {
                    // 1.2.1.2.1 load�Ώۖ���(����ID)
                    // �����̖���ID�͎��Y�ړ��e�[�u��.����ID
                    // �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        loadProduct(l_assetTransfer.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.2.2 �������Ə،��]��.create�������Ə،��]��(�Ώۖ���)
                    // �����̑Ώۖ����́A�u2.2.1�v�̖߂�l
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add�������Ə،��]���i�Ώۖ���, �������Ə،��]��)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.2.1.3 is���݃��[�h�ςݑΏۖ���(����ID) = true�̏ꍇ
                else
                {
                    // 1.2.1.3.1 get�Ώۖ���(����ID)
                    // �����̖���ID�͎��Y�ړ��e�[�u��.����ID
                    // �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        getProduct(l_assetTransfer.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.3.2 get�������Ə،��]��(�Ώۖ���)
                    // �����̑Ώۖ����́u5.1�v�̖߂�l
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.2.1.4  �U�֕ϓ�.create�U�֕ϓ�()
                WEB3TPSecurityTransferChange l_change =
                    WEB3TPSecurityTransferChange.create();
                
                // 1.2.1.5 �U�֕ϓ�.set�]�͌v�Z����(�]�͌v�Z����)
                l_change.setCalcCondition(getCalcCondition());

                //  1.2.1.6 �U�֕ϓ�.set�a��敪(�a��敪)
                // �����̗a��敪�́A�،��]���z.�ڋq����.get�a��敪�i���Y�ړ��e�[�u��.�⏕����ID�j
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_assetTransfer.getSubAccountId()));

                //  1.2.1.7 �U�֕ϓ�.calc�ϓ����f��(��n��)
                //   �U�֕ϓ��́u2.4�v�̖߂�l
                // �@@�����̎�n���́Anull
                l_change.calcReflectDay(null);

                //  1.2.1.8 �U�֕ϓ�.set�ŋ敪(�ŋ敪)
                l_change.setTaxType(TaxTypeEnum.UNDEFINED);
                
                // 1.2.1.9 to��������敪(TaxTypeEnum)
                // 1.2.1.10 �U�֕ϓ�.set��������敪(��������敪)
                //  �����̐ŋ敪�́A���Y�ړ��e�[�u��.�ŋ敪
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(TaxTypeEnum.
                    UNDEFINED));

                // 1.2.1.11 �U�֕ϓ�.set�ϓ�����(�ϓ�����)
                l_change.setQuantity(l_assetTransfer.getQuantity());


                // �����̒P���́A�Ώۖ���.�]���P���~�Ώۖ���.�v�Z�P��
                //���M�Ή�(20050401)�F�v�Z�P�ʂ͐��ʂɑ΂��č�p����̂�
                //�]���P���ɏ�Z���Ȃ� 
                //1.2.1.13���M�M���̏ꍇ
                //(�Ώۖ���.�����^�C�v == �R�F�����M��)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.1 �U�֕ϓ�.set�]���P��(�]���P��)
                    l_change.setUnitPrice(l_product.getUnitPrice());
                }
                //1.2.1.14 �ȊO�i���M�M���łȂ��j�̏ꍇ
                else
                {
                    l_change.setUnitPrice(l_product.getUnitPrice() * l_product.getUnitSize());
                }
                log.debug("ProductType=" + l_product.getProductType() + " UnitPrice=" + l_change.getUnitPrice());

                // 1.2.1.12  �U�֕ϓ�.set�]���|��(�]���|��)
                //   �����̕]���|�ڂ́u8.�v�̗a��敪���u��p�v�̏ꍇ�A�Ώۖ���.��p�|��
                //   �u�ی�v�̏ꍇ�A�Ώۖ���.�a��،��]���|��
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));

                //   �����̕]���z�́A�ϓ����ʁ~�]���P���~�]���|��
                //���M�Ή�(20050401)�F�v�Z�P�ʂ͐��ʂɑ΂��č�p����
                //1.2.1.13���M�M���̏ꍇ
                //(�Ώۖ���.�����^�C�v == �R�F�����M��)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.3 �U�֕ϓ�.set�]���P��(�]���P��)
                    l_change.setValuationPrice(
                            calcMutualFundValuationPricePerChange(l_product, l_change)
                            );
                }
                //1.2.1.14 �ȊO�i���M�M���łȂ��j�̏ꍇ
                else
                {
                    l_change.setValuationPrice(l_change.getQuantity() * l_change.getUnitPrice());
                }
           	                
                // 1.2.1.15 �������Ə،��]��.add�،��ϓ�(�،��ϓ�)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug(" asset Transfer row not found.");
        }
    }

    /**
     * (do�o�ɕϓ�<�����ȍ~>���[�h)<BR>
     * �o�ɕϓ�<�����ȍ~>�����[�h����B<BR>
     * <BR>
     * ��{�݌v�V�[�P���X�udo�o�ɕϓ�<�����ȍ~>���[�h�v���Q�Ƃ���
     * <BR>
     */
    protected void doDeliveryChangesLoad()
    {
        // 1.1 �a��،��֘A�f�[�^���\�[�X�A�N�Z�X�Ǘ�.get���Y�ړ����R�[�h<�o��>(),
        List l_assetOuts = dataMgr.getAssetOutAioOrderUnits(this);

        // 1.2 AIO�����P�ʃe�[�u���ɏo�Ƀ��R�[�h�����݂���ꍇ
        //�iget���Y�ړ�<�o��>���R�[�h()�@@!
        if (l_assetOuts != null)
        {
            log.debug(l_assetOuts.size() + " asset out row found.");

            // 1.2.1 LOOP�����Fget���Y�ړ�<�o��>���R�[�h()�̖߂�l�̗v�f����
            for (Iterator l_it = l_assetOuts.iterator(); l_it.hasNext(); )
            {
                AioOrderUnitParams l_assetOut = (AioOrderUnitParams) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.2.1.1 is���݃��[�h�ςݑΏۖ���(����ID�j
                //  �����̖���ID�͎��Y�ړ��e�[�u��.����ID
                boolean isLoadedProduct = this.isLoadedProduct(l_assetOut.getProductId(), 
                                                                 WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.2.1.2 is���݃��[�h�ςݑΏۖ���(����ID) = false�̏ꍇ
                if (!isLoadedProduct)
                {
                    //1.2.1.2.1 load�Ώۖ���(����ID)
                    // �����̖���ID�͎��Y�ړ��e�[�u��.����ID
                    //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        loadProduct(l_assetOut.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.2.2 �������Ə،��]��.create�������Ə،��]��(�Ώۖ���)
                    // �����̑Ώۖ����́A�u4.1.�v�̖߂�l
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.2.1.2.3 add�������Ə،��]���i�Ώۖ���, �������Ə،��]��)
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                //1.2.1.3 is���݃��[�h�ςݑΏۖ���(����ID) = true�̏ꍇ
                else
                {
                    // 1.2.1.3.1 get�Ώۖ���(����ID)
                    // �����̖���ID�͎��Y�ړ��e�[�u��.����ID
                    // �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        getProduct(l_assetOut.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.2.1.3..2 get�������Ə،��]��(�Ώۖ���)
                    // �����̑Ώۖ����́u5.1�v�̖߂�l
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }
                // 1.2.1.4 �o�ɕϓ�.create�o�ɕϓ�()
                WEB3TPSecurityDeliveryChange l_change =
                    WEB3TPSecurityDeliveryChange.create();
                
                // 1.2.1.5 �o�ɕϓ�. set�]�͌v�Z����(�]�͌v�Z����)
                l_change.setCalcCondition(getCalcCondition());

                // 1.2.1.6 �o�ɕϓ�.set�a��敪(�a��敪)
                // �����̗a��敪�́A�،��]���z.�ڋq����.get�a��敪�i���Y�ړ��e�[�u��.�⏕����ID�j
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_assetOut.getSubAccountId()));

                // 1.2.1.7 �o�ɕϓ�.calc�ϓ����f��(��n��)
                //   �o�ɕϓ��́u6.�v�̖߂�l
                // �@@�����̎�n���́Anull
                l_change.calcReflectDay(null);

                //  1.2.1.8 �o�ɕϓ�.set�ŋ敪 (�ŋ敪)
                l_change.setTaxType(TaxTypeEnum.UNDEFINED);
                
                // 1.2.1.9 to��������敪(TaxTypeEnum)
                // 1.2.1.10 �o�ɕϓ�.set��������敪(��������敪)
                //  �����̐ŋ敪�́A���Y�ړ��e�[�u��.�ŋ敪
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(TaxTypeEnum.
                    UNDEFINED));

                // 1.2.1.11�o�ɕϓ�.set�ϓ�����(�ϓ�����)
                l_change.setQuantity(l_assetOut.getQuantity());

                // �����̒P���́A�Ώۖ���.�]���P���~�Ώۖ���.�v�Z�P��
                //���M�Ή�(20050401)�F�v�Z�P�ʂ͐��ʂɑ΂��č�p����̂�
                //�]���P���ɏ�Z���Ȃ�  
                // 1.2.1.13���M�M���̏ꍇ(�Ώۖ���.�����^�C�v == �R�F�����M��)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.1 �o�ɕϓ�.set�]���P��(�]���P��)
                    l_change.setUnitPrice(l_product.getUnitPrice());
                }
                //1.2.1.14�ȊO�i���M�M���łȂ��j�̏ꍇ
                else
                {
                    // 1.2.1.14.1 �o�ɕϓ�.set�]���P��(�]���P��)
                    l_change.setUnitPrice(l_product.getUnitPrice() * l_product.getUnitSize());
                }
                
                // 1.2.1.12 �o�ɕϓ�.set�]���|��(�]���|��)
                l_change.setValuationRatio(
                    l_product.getValuationRatio(l_change.getDepositType()));

                //   �����̕]���z�́A�ϓ����ʁ~�]���P���~�]���|��
                //���M�Ή�(20050401)�F�v�Z�P�ʂ͐��ʂɑ΂��č�p����
                // 1.2.1.13���M�M���̏ꍇ(�Ώۖ���.�����^�C�v == �R�F�����M��)
                if (ProductTypeEnum.MUTUAL_FUND.equals(l_product.getProductType()))
                {
                    //1.2.1.13.3 �o�ɕϓ�.set�]���z(�]���z)
                    l_change.setValuationPrice(
                            calcMutualFundValuationPricePerChange(l_product, l_change)
                            );
                }
                //1.2.1.14�ȊO�i���M�M���łȂ��j�̏ꍇ
                else
                {
                    // 1.2.1.14.2 �o�ɕϓ�.set�]���z(�]���z)
                    l_change.setValuationPrice(l_change.getQuantity() * l_change.getUnitPrice());
                }
                
                // 1.2.1.15 �������Ə،��ϓ�.add�،��ϓ�(�،��ϓ�)
                l_valuation.addSecurityChange(l_change);
            }
        }
        else
        {
            log.debug(" asset out row not found.");
        }

    }

    /**
     * (do����ϓ�<�����ȍ~>���[�h)<BR>
     * <BR>
     * ����ϓ�<�����ȍ~>���f�[�^���[�h����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(�،��]���z)do����ϓ�<�����ȍ~>���[�h�`GP�`�v�Q�� <BR>
     * ���V�[�P���X�}�u(�،��]���z)do����ϓ�<�����ȍ~>���[�h�`�����`�v�Q��<BR>
     * <BR>
     * @@roseuid 40B6FADE032B
     */
    protected void doTransactionChangesLoad()
    {
        //�]���P��Callback���擾
        WEB3TPUnitPriceCallback l_unitPriceCallback = getCalcCondition().getUnitPriceCallback();

        // 1.1 �a��،��f�[�^���\�[�X�A�N�Z�X�Ǘ�.getGP������R�[�h(���Y�ϓ�)
        List l_ruitoOrderUnits = dataMgr.getRuitoOrderUnits(this);
        
        // 1.2 �ݓ������P�ʃe�[�u���Ƀ��R�[�h�����݂���ꍇ
        //�igetGP������R�[�h()�@@!= null�j
        if (l_ruitoOrderUnits != null)
        {
            log.debug(l_ruitoOrderUnits.size() + " ruito order unit row found.");

            // 1.2.1 LOOP�����FgetGP������R�[�h()�̖߂�l�̗v�f����
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
                
                // 1.2.1.6 ����ϓ�.set�����^�C�v(ProductTypeEnum)
                // ����:ProductTypeEnum = �Ώۖ���.get�����^�C�v() 
                l_change.setProductType(l_product.getProductType());

                // 1.2.1.7 ����ϓ�.set�a��敪(�a��敪)
                //   ����ϓ��́A�1.6��̖߂�l
                // �����̗a��敪�́Aget�ڋq����().get�a��敪(�ݓ������P�ʃe�[�u��.�⏕����ID)
                l_change.setDepositType(
                    getAccountInfo().getDepositType(
                    l_ruitoOrderUnit.getSubAccountId()));

                // 1.2.1.8 ����ϓ�.set�ŋ敪(�ŋ敪)
                l_change.setTaxType(l_ruitoOrderUnit.getTaxType());
                
                // 1.2.1.9 ����ϓ�.to��������敪(TaxTypeEnum)
                //   �����̓�������敪�́A�ݓ������P�ʃe�[�u��.�ŋ敪
                // 1.2.1.10 ����ϓ�.set��������敪(��������敪)
                l_change.setSpecialAccountFlag(l_change.toSpecialAccountFlag(
                    l_ruitoOrderUnit.getTaxType()));

                // 1.2.1.11 ����ϓ�.set�����J�e�S��(�����J�e�S��)
                //   �����̒����J�e�S���́A�ݓ������P�ʃe�[�u��.�����J�e�S��
                l_change.setOrderCateg(l_ruitoOrderUnit.getOrderCateg());

                // 1.2.1.12 ����ϓ�.set���敪(���敪)
                //   �����̖��敪�́A�����
                l_change.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                // 1.2.1.13 ����ϓ�.set�����敪(�����敪)
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
                //   �������Ə،��]���z�ɐ�����������ϓ��I�u�W�F�N�g��ǉ�����B 
                //   �،��ϓ� = create����ϓ�()�̖߂�l
                l_valuation.addSecurityChange(l_change);

            }
        }
        else
        {
            log.debug(" ruito order unit row not found.");
        }

        // 1.3 ����ϓ�<�����ȍ~>(����)�����[�h����B
        // �a��،��f�[�^���\�[�X�A�N�Z�X�Ǘ�.get����������R�[�h(���Y�]���j
        List l_equityOrderUnits = dataMgr.getEqTypeOrderUnits(this);
        
        // 1.4 ���������P�ʃe�[�u���Ƀ��R�[�h�����݂���ꍇ
        //�iget����������R�[�h()�@@!= null�j
        if (l_equityOrderUnits != null)
        {
            log.debug(l_equityOrderUnits.size() + " eqtype order unit row found.");

            // 1.4.1 LOOP�����FgetGP������R�[�h()�̖߂�l�̗v�f����
            for (Iterator l_it = l_equityOrderUnits.iterator(); l_it.hasNext(); )
            {
                EqtypeOrderUnitRow l_equityOrderUnit = (EqtypeOrderUnitRow) l_it.next();
                WEB3TPSecurityValuationProduct l_product = null;
                WEB3TPSecurityValuationPerProduct l_valuation = null;

                // 1.4.1.1 is���݃��[�h�ςݑΏۖ���(����ID�j
                //   �����̖���ID�͊��������P�ʃe�[�u��.����ID
                //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                boolean isLoadedProduct = this.isLoadedProduct(l_equityOrderUnit.
                    getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                // 1.4.1.2 is���݃��[�h�ςݑΏۖ���(����ID) = false�̏ꍇ�A
                if (!isLoadedProduct)
                {
                    // 1.4.1.2.1 load�Ώۖ���(����ID)
                    //   �����̖���ID�͊��������P�ʃe�[�u��.����ID
                    //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        loadProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.4.1.2.2 �������Ə،��]��.create�������Ə،��]��(�Ώۖ���)
                    //   �����̑Ώۖ����́A�u2.2.1�v�̖߂�l
                    l_valuation =
                        WEB3TPSecurityValuationPerProduct.create(l_product, this);

                    // 1.4.1.2.3 add�������Ə،��]���i�Ώۖ���, �������Ə،��]��)
                    //   �����̑Ώۖ����́u2.2.1�v�̖߂�l
                    //   �����̖������Ə،��]���́u2.2.2�v�̖߂�l
                    addSecurityValuationPerProduct(l_product, l_valuation);
                }
                // 1.4.1.3 is���݃��[�h�ςݑΏۖ���(����ID) = true�̏ꍇ�A
                else
                {
                    // 1.4.1.3.1 get�Ώۖ���(����ID)
                    //   �����̖���ID�͊��������P�ʃe�[�u��.����ID
                    //   �~�j���敪�ǉ��i�a�莑�Y�Ή��j
                    l_product =
                        getProduct(l_equityOrderUnit.getProductId(), WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

                    // 1.4.1.3.2 get�������Ə،��]��(�Ώۖ���)
                    //   �����̑Ώۖ�����get�Ώۖ���()�̖߂�l
                    l_valuation =
                        this.getSecurityValuationPerProduct(l_product);
                }

                // 1.4.1.4 ����ϓ�.create����ϓ�()--��������ϓ�
                WEB3TPSecurityTransactionChange l_unExecChange =
                    WEB3TPSecurityTransactionChange.create();

                // 1.4.1.5 set�]�͌v�Z����(�]�͌v�Z����)
                l_unExecChange.setCalcCondition(getCalcCondition());

                // 1.4.1.6 ��������ϓ�.set�����^�C�v()
                l_unExecChange.setProductType(l_product.getProductType());

                // 1.4.1.7 ��������ϓ�.set�a��敪(�a��敪)
                // �����̗a��敪�́Aget�ڋq����().get�a��敪(���������P�ʃe�[�u��.�⏕����ID)
                l_unExecChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));
                
                // 1.4.1.8 ��������ϓ�.set�ŋ敪(�ŋ敪)
                l_unExecChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.4.1.9 to��������敪(TaxTypeEnum)
                // 1.4.1.10 ��������ϓ�.set��������敪(��������敪)
                l_unExecChange.setSpecialAccountFlag(l_unExecChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.4.1.11 ��������ϓ�.set�����J�e�S��(�����J�e�S��)
                //   �����̒����J�e�S���́A���������P�ʃe�[�u��.�����J�e�S��
                l_unExecChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.4.1.12 ��������ϓ�.set�����敪(�����敪)
                l_unExecChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));

                // 1.4.1.13 ��������ϓ�.set�]���|��(�]���|��)
                l_unExecChange.setValuationRatio(
                    l_product.getValuationRatio(l_unExecChange.getDepositType()));

                // 1.4.1.14 ��������ϓ�.calc�ϓ����f��(��n��)
                //   �����̎�n���́A���������P�ʃe�[�u��.��n��
                l_unExecChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.4.1.15 ����ϓ�.create����ϓ�()--������ϓ�
                WEB3TPSecurityTransactionChange l_execChange =
                    WEB3TPSecurityTransactionChange.create();
                l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                // 1.4.1.16 ������ϓ�.set�v�Z����()
                l_execChange.setCalcCondition(getCalcCondition());

                // 1.4.1.17 ������ϓ�.set�����^�C�v
                l_execChange.setProductType(l_product.getProductType());

                // 1.4.1.18 ������ϓ�.set�a��敪(�a��敪)
                // �����̗a��敪�́Aget�ڋq����().get�a��敪(���������P�ʃe�[�u��.�⏕����ID)
                l_execChange.setDepositType(
                    getAccountInfo().getDepositType(
                    l_equityOrderUnit.getSubAccountId()));

                // 1.4.1.19 ����ϓ�.set�ŋ敪(�ŋ敪)
                l_execChange.setTaxType(l_equityOrderUnit.getTaxType());
                
                // 1.4.1.20  to��������敪(TaxTypeEnum)
                // 1.4.1.21 ����ϓ�.set��������敪(��������敪)
                l_execChange.setSpecialAccountFlag(l_execChange.toSpecialAccountFlag(
                    l_equityOrderUnit.getTaxType()));

                // 1.4.1.22 ����ϓ�.set�����J�e�S��(�����J�e�S��)
                //   �����̒����J�e�S���́A���������P�ʃe�[�u��.�����J�e�S��
                l_execChange.setOrderCateg(l_equityOrderUnit.getOrderCateg());

                // 1.4.1.23 ��������ϓ�.set�����敪(�����敪)
                l_execChange.setSide(SideEnum.getSide(l_equityOrderUnit.getOrderType()));

                // 1.4.1.24 ����ϓ�.set�]���|��(�]���|��)
                l_execChange.setValuationRatio(
                    l_product.getValuationRatio(l_unExecChange.getDepositType()));

                // 1.4.1.25 ��������ϓ�.calc�ϓ����f��(��n��)
                //   �����̎�n���́A���������P�ʃe�[�u��.��n��
                l_execChange.calcReflectDay(l_equityOrderUnit.getDeliveryDate());

                // 1.4.1.26
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
                    // 1.4.1.26.1 ����ϓ�.set���敪(���敪)
                    //   �����̖��敪�́A�����
                    l_unExecChange.setExecType(WEB3TPExecTypeDef.UNEXECUTED);

                    // 1.4.1.26.2 �����E���n�łȂ��ꍇ
                    // (���������P��Row.�����J�e�S�� != 7:�����E���n����)
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_equityOrderUnit.
                        getOrderCateg()))
                    {
                        // 1.4.1.26.2.2  ����ϓ�.set�ϓ�����(�ϓ�����)
                        //   �����̕ϓ����ʂ́A���������P�ʃe�[�u��.���� -
                        // ���������P�ʃe�[�u��.��萔��
                        l_unExecChange.setQuantity(l_equityOrderUnit.getQuantity() -
                            l_equityOrderUnit.getExecutedQuantity());

                        // 1.4.1.26.2.3  ����ϓ�.set�]���P��(�]���P��)
                        //   �����̕]���P���́A���������P�ʃe�[�u��.�����P�� �~ �Ώۖ���.get�v�Z�P��()
                        l_unExecChange.setUnitPrice(
//                            Math.min(l_equityOrderUnit.getPrice(), l_product.getUnitPrice()) * �݌v���̊ԈႢ
                            l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderUnit.getPrice(), l_product) * 
                            l_product.getUnitSize());                                                

                        // 1.4.1.26.2.4 ����ϓ�.set�]���z(�]���z)
                        //
                        // �����̕]���z�́A����ϓ�.get�ϓ�����()�~����ϓ�.get�]���P��()
                        //����������v�Z���邽�߁A�|�ڂ��|���Ȃ�
                        l_unExecChange.setValuationPrice(
                            l_unExecChange.getQuantity()
                            * l_unExecChange.getUnitPrice()
                            );
                    }
                    
                    // 1.4.1.26.3 �ȊO�i�����E���n�j�̏ꍇ
                    else
                    {
                        // �o�OY00019�F�M�p���������̖�������v�Z�Ɏg�p���钍���P����null������
                        // �ԍϏ�񂩂猚�P���ŕ]������
                        //�ꊇ�ԍς̏ꍇ���Ή�����B
                        // 1.4.1.26.3.1 ���ʕԍώw����I�u�W�F�N�g�̃��X�g���擾����B 
                        List l_closingContractSpecs = this.contractInfo.
                            getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                        double l_quantity = 0.0;
                        double l_amount = 0.0;
                        
                        //1.4.1.26.3.2 LOOP�����Fget���ʕԍώw����ꗗ()�̖߂�l�̗v�f����
                        for (int i = 0; i < l_closingContractSpecs.size(); i++)
                        {
                            //1.4.1.26.3.2.1 �ԍϒ������ʂ��擾����B 
                            WEB3TPClosingContractSpec l_closingContractSpec = (
                                WEB3TPClosingContractSpec)
                                l_closingContractSpecs.get(i);
                            l_quantity +=
                                (l_closingContractSpec.getQuantity() -
                                 l_closingContractSpec.getExecutedQuantity());
                            l_amount += (l_closingContractSpec.getQuantity() -
                                         l_closingContractSpec.getExecutedQuantity()) *
//                                Math.min(l_closingContractSpec.getContractPrice(),
//                                         l_product.getUnitPrice()) *
                                l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) *                                          
                                l_product.getUnitSize();
                            
                            // 1.4.1.26.3.3 �ϓ����ʂ��Z�b�g���� 
                            l_unExecChange.setQuantity(l_quantity);
                            // 1.4.1.26.3.5 �]���z���Z�b�g���� 
                            l_unExecChange.setValuationPrice(l_amount);
                            // 1.4.1.26.3.4 �]���P�����Z�b�g���� 
                            //����:double = �W�v�����h�]���z�h�@@�^�@@�W�v�����h�ϓ����ʁh 
                            //���h�ϓ����ʁh = 0 �̏ꍇ�́A0���Z�b�g 
                            if (l_quantity > 0)
                            {
                                l_unExecChange.setUnitPrice(l_amount / l_quantity);
                            }
                        }
                    }

                    // 1.4.1.26.4 �������Ə،��]���z.add�،��ϓ�(�،��ϓ�)
                    l_valuation.addSecurityChange(l_unExecChange);

                }

                // 1.4.1.27 ���������P�ʃe�[�u��.��萔�� > 0
                if (l_equityOrderUnit.getExecutedQuantity() > 0)
                {

                    // 1.4.1.27.1 ����ϓ�.set���敪(���敪)
                    //   �����̖��敪�́A����
                    l_execChange.setExecType(WEB3TPExecTypeDef.EXECUTED);

                    // 1.4.1.27.2 ����ϓ�.get�����J�e�S��() != �u�����E���n�v�̏ꍇ
                    if (!OrderCategEnum.SWAP_MARGIN.equals(l_unExecChange.getOrderCateg()))
                    {
                        // �a��،��f�[�^���\�[�X�A�N�Z�X�Ǘ�.get������背�R�[�h(�����P��ID)
                        //   �����̒����P��ID�͊��������P�ʃe�[�u��.�����P��ID
                        List l_equityOrderExecutions =
                            dataMgr.
                            getEqTypeOrderExecutions(l_equityOrderUnit);

                        // 1.4.1.27.2.2 �擾�������R�[�h���W�v���ȉ��̒l���Z�o����
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
//                                l_equityOrderExecution.getExecPrice() *
                                l_unitPriceCallback.getUnitPriceNotCompare(l_equityOrderExecution.getExecPrice(), l_product) *                                          
                                l_product.getUnitSize();
                        }
                        // 1.4.1.27.2.3 ����ϓ�.set�ϓ�����(�ϓ�����)
                        l_execChange.setQuantity(l_quantity);

                        // 1.4.1.27.2.4 ����ϓ�.set�]���z(�]���z)
                        // �@@�����̕]���z�́A�Z�o�����]���z
                        ////����������v�Z���邽�߁A�|�ڂ��|���Ȃ�
                        l_execChange.setValuationPrice(l_amount);
                        // 1.4.1.27.2.5 ����ϓ�.set�]���P��(�]���P��)
                        // �@@�����̕]���P���́A 2.12.2.2�Z�o�����]���z / ����ϓ�.get�ϓ�����()
                        if (l_quantity > 0)
                        {
                            l_execChange.setUnitPrice(l_amount / l_quantity);
                        }

                        //   �������Ə،��]���z.add�،��ϓ�(�،��ϓ�)
                        l_valuation.addSecurityChange(l_execChange);
                    }
                    // 1.4.1.27.3 ����ϓ�.get�����J�e�S��() =�u�����E���n�v�̏ꍇ
                    else
                    {
                        if (this.contractInfo != null)
                        {
                            // 1.4.1.27.3.1 ���ʏ��.get�ԍώw����(�����P�ʃe�[�u��.�����P��ID)�Ō��ʕԍώw����
                            // ���擾����B
                            List l_closingContractSpecs = this.contractInfo.
                                getClosingContractSpecs(l_equityOrderUnit.getOrderUnitId());
                            // �擾�������ʕԍώw������W�v���ȉ��̒l���Z�o����
                            // �@@�ϓ����� += ���ʕԍώw����.��萔��
                            // �@@�]���z + = ���ʕԍώw����.�ԍϖ�萔��
                            // �@@�@@�@@�@@�@@�@@�@@�~ Min(���ʕԍώw����.���P��, �Ώۖ���.get�]���P��()) �~ �Ώۖ���.get�v�Z�P��()
                            // �@@�@@�@@�@@�@@�@@�@@
                            double l_quantity = 0.0;
                            double l_amount = 0.0;
                            // 1.4.1.27.3.2 LOOP�����Fget���ʕԍώw����ꗗ()�̖߂�l�̗v�f����
                            for (int i = 0; i < l_closingContractSpecs.size(); i++)
                            {
                                WEB3TPClosingContractSpec l_closingContractSpec = (
                                    WEB3TPClosingContractSpec)
                                    l_closingContractSpecs.get(i);
                                l_quantity += l_closingContractSpec.getExecutedQuantity();
                                l_amount += l_closingContractSpec.getExecutedQuantity() *
//                                    Math.min(l_closingContractSpec.getContractPrice(),
//                                             l_product.getUnitPrice()) *
                                    l_unitPriceCallback.getUnitPrice(l_closingContractSpec.getContractPrice(), l_product) * 
                                    l_product.getUnitSize();
                            }

                            // 1.4.1.27.3.3 ����ϓ�.set�ϓ�����(�ϓ�����)
                            l_execChange.setQuantity(l_quantity);

                            // 1.4.1.27.3.4 ����ϓ�.set�]���z(�]���z�j
                            // �@@�����̕]���z�͎Z�o�����]���z
                            //  ����������v�Z���邽�߁A�|�ڂ��|���Ȃ�
                            l_execChange.setValuationPrice(l_amount);

                            // 1.4.1.27.3.5 ����ϓ�.set�]���P��(�]���P��)
                            // �@@�����̕]���P���́A�Z�o�����]���z / ����ϓ�.get�ϓ�����()
                            if (l_quantity > 0)
                            {
                                l_execChange.setUnitPrice(l_amount / l_quantity);
                            }

                            // 1.4.1.27.4 �������Ə،��]���z.add�،��ϓ�(�،��ϓ�)
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

    /**
     * (is���[�h�ςݑΏۖ���)<BR>
     * ���[�h�ς݂̑Ώۖ������ǂ������`�F�b�N����B<BR>
     * �Ώۖ������X�g�ɁA�����Ŏw�肳�ꂽ����ID�ƈ�v�������ID������<BR>
     * �Ώۖ��������݂��邩�`�F�b�N����B<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)
     * @@param l_strMiniStockDivDef - (�~�j���敪)
     * @@return boolean
     * @@roseuid 40D7D816033A
     */
    protected boolean isLoadedProduct(long l_lngProductId, String l_strMiniStockDivDef)
    {
        if (this.products != null)
        {
            for (int i = 0; i < products.size(); i++)
            {
                WEB3TPSecurityValuationProduct l_product =
                    (WEB3TPSecurityValuationProduct) products.get(i);
                //�a�莑�Y�Ή��ɂ��A�~�j���敪���Ώ̖����`�F�b�N�ɒǉ�����
                if (l_lngProductId == l_product.getProductId() && 
                    l_strMiniStockDivDef.equals(l_product.getMiniStockDivDef()) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * (get�Ώۖ���)<BR>
     * �����h�c�ɂ��Ώۖ������擾����B<BR>
     * �Ώۖ������X�g�������ID���L�[�Ƃ��đΏۖ������擾����B<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)
     * @@param l_strMiniStockDivDef - (�~�j���敪)
     * @@return WEB3TPSecurityValuationProduct
     * @@roseuid 40D7DDF803A7
     */
    public WEB3TPSecurityValuationProduct getProduct(long l_lngProductId, String l_strMiniStockDivDef)
    {
        if (this.products != null)
        {
            for (int i = 0; i < products.size(); i++)
            {
                WEB3TPSecurityValuationProduct l_product =
                    (WEB3TPSecurityValuationProduct) products.get(i);
                //�a�莑�Y�Ή��ɂ��A�~�j���敪���Ώ̖����`�F�b�N�ɒǉ�����
                if (l_lngProductId == l_product.getProductId() && 
                    l_strMiniStockDivDef.equals(l_product.getMiniStockDivDef()) )
                {
                    return l_product;
                }
            }
        }
        return null;
    }

    /**
     * (add�������Ə،��]���z)<BR>
     * �������Ə،��]���z��ǉ�����B<BR>
     * 1.�Ώۖ������X�g.add(����.�Ώۖ���)<BR>
     * 2.�������Ə،��]���z�}�b�v.put(����.�Ώۖ���, ����.�������Ə،��]���z)<BR>
     * <BR>
     * @@param l_product - (�Ώۖ���)
     * @@param l_valuation - (�������Ə،��]���z)
     * @@roseuid 40D7E40903C6
     */
    protected void addSecurityValuationPerProduct(
        WEB3TPSecurityValuationProduct l_product,
        WEB3TPSecurityValuationPerProduct l_valuation)
    {
        // �����^�C�v���ƑΏۖ������X�g���擾
        List l_list = (List) classifiedProducts.get(l_product.getProductType());
        if (l_list == null)
        {
            l_list = new ArrayList();
            classifiedProducts.put(l_product.getProductType(), l_list);
        }
        // �����^�C�v���ƑΏۖ������X�g�ɒǉ�
        l_list.add(l_product);
        // �Ώۖ������X�g�ɒǉ�
        products.add(l_product);
        // �������Ə،��]���z�}�b�v�ɒǉ�
        valuationPerProducts.put(l_product, l_valuation);
    }

    /**
     * (calc�����M�����Y�]���z)<BR>
     * <BR>
     * �����M���̕]���z���v�Z����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(�،��]���z)calc�����M�����Y�]���z�v�Q��<BR>
     * <BR>
     * @@param l_product - (�Ώۖ���)
     * @@param l_change - (�،��ϓ�)
     */
    protected double calcMutualFundValuationPricePerChange(
        WEB3TPSecurityValuationProduct l_product,
        WEB3TPSecurityChange l_change)
    {
        //�v�Z�P�ʂ��擾
        BigDecimal l_bdUnitSize = new BigDecimal("" + l_product.getUnitSize());

        //�ϓ����ʂ��擾
        BigDecimal l_bdQuantity = new BigDecimal("" + l_change.getQuantity());

        //�]���P�����擾
        BigDecimal l_bdUnitPrice = new BigDecimal("" + l_change.getUnitPrice());

        //�h�]���z�h���v�Z����B
        //�������_��7�ʂ��l�̌ܓ�����B
        BigDecimal l_bdValuationPrice =
            l_bdUnitPrice.multiply(l_bdQuantity).divide(
                l_bdUnitSize, 6, BigDecimal.ROUND_HALF_UP);
        
        //����ID�擾
        long l_lngProductId = l_product.getProductId();
        
        //���M�����}�X�^�[���擾
        MutualFundProductRow l_mutualFundProductRow = dataMgr.getMutualFundProduct(l_lngProductId);
        
        //���M���� != null
        if(l_mutualFundProductRow != null)
        {
            //�ʉ݃R�[�h
            String l_strCurrencyCode = l_mutualFundProductRow.getCurrencyCode();
            
            //�ʉ݃R�[�h != null
            if(l_strCurrencyCode != null)
            {
                //�،���ЃR�[�h���擾
                String l_strInstitutionCode = this.getAccountInfo().getInstitutionCode();
                
                //�בփ��[�g���擾
                ExchangeRateRow l_exchangeRateRow = dataMgr.getExchangeRate(l_strInstitutionCode, l_mutualFundProductRow.getCurrencyCode());

                //�בփ��[�g != null
                if(l_exchangeRateRow != null)
                {
                    //TTB
                    BigDecimal l_bdTTB = new BigDecimal("" + l_exchangeRateRow.getTtBuyingRate());

                    //�בփ��[�g�v�Z�P��
                    BigDecimal l_bdExchangeCalcUnit = new BigDecimal("" + l_exchangeRateRow.getExchangeCalcUnit());

                    //�]���z�v�Z(�~�݂Ɋ��Z)
                    l_bdValuationPrice =
                        l_bdValuationPrice.multiply(l_bdTTB).divide(
                            l_bdExchangeCalcUnit, 6, BigDecimal.ROUND_HALF_UP);
                }
            }
        }

        //"�]���z"�̏����_�ȉ����l�̌ܓ�����
        return l_bdValuationPrice.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * (get���ʏ��)<BR>
     * <BR>
     * ���ʏ����擾����B<BR>
     * @@return  WEB3TPContractInfo
     */
    public WEB3TPContractInfo getContractInfo()
    {
        return this.contractInfo;
    }

    /**
     * (set���ʏ��)<BR>
     * <BR>
     * ���ʏ����Z�b�g����B<BR>
     * @@param l_contractInfo - ���ʏ��
     */
    public void setContractInfo(WEB3TPContractInfo l_contractInfo)
    {
        this.contractInfo = l_contractInfo;
    }

    /**
     * (calc�O�������]���z)<BR>
     * �icalc�O�������]���z) <BR>
     * <BR>
     * �O�������̕]���z���v�Z����B<BR>
     * @@param l_securityValuationProduct - (�Ώۖ���)<BR>
     * (�Ώۖ���)<BR>
     * @@param l_securityChange - (�،��ϓ�)<BR>
     * (�،��ϓ�)<BR>
     * @@return double
     */
    protected double calcFeqValuation(
        WEB3TPSecurityValuationProduct l_securityValuationProduct,
        WEB3TPSecurityChange l_securityChange)
    {
        final String STR_METHOD_NAME =
            "calcFeqValuation(WEB3TPSecurityValuationProduct, WEB3TPSecurityChange)";
        log.entering(STR_METHOD_NAME);

        //get�v�Z�P��
        double l_dblUnitSize = l_securityValuationProduct.getUnitSize();

        //get�ϓ�����
        double l_dblQuantity = l_securityChange.getQuantity();

        // get�]���P��
        double l_dblUnitPrice = l_securityChange.getUnitPrice();

        //get�����h�c
        long l_lngProductId = l_securityValuationProduct.getProductId();

        //get�O������(long)
        //[����]
        //����ID = get����ID()�̖߂�l
        FeqProductRow l_feqPeoductRow = dataMgr.getFeqProduct(l_lngProductId);

        //�ڋq�������擾
        WEB3TPAccountInfo l_accountInfo = getAccountInfo();
        //get�،���ЃR�[�h
        String l_strInstitutionCode = l_accountInfo.getInstitutionCode();

        //get�i���ʁj�ʉ�
        //[����]
        //�،���ЃR�[�h = get�،���ЃR�[�h()�̖߂�l
        //�ʉ݃R�[�h = �O������Row.�ʉ݃R�[�h
        GenCurrencyRow l_genCurrencyRow =
            dataMgr.getGenCurrency(l_strInstitutionCode, l_feqPeoductRow.getCurrencyCode());

        //(*)"�]���z"���v�Z����
        //����
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity + "");
        //�]���P��
        BigDecimal l_bdUnitPrice = new BigDecimal(l_dblUnitPrice + "");
        //�בփ��[�g
        BigDecimal l_bdCurrentSellExecRate = new BigDecimal(l_genCurrencyRow.getCurrentSellExecRate() + "");
        //�v�Z�P��
        BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize + "");

        //�O�������]���z
        BigDecimal l_bdFeqValuation =
            l_bdQuantity.multiply(l_bdUnitPrice).multiply(l_bdCurrentSellExecRate).multiply(l_bdUnitSize);

        log.debug("�O�������̕]���z = " + l_bdFeqValuation.doubleValue());

        log.exiting(STR_METHOD_NAME);
        return l_bdFeqValuation.doubleValue();
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {

        ProductTypeEnum[] l_types = new ProductTypeEnum[]
            {
            ProductTypeEnum.EQUITY,
            ProductTypeEnum.BOND,
            ProductTypeEnum.MUTUAL_FUND,
            ProductTypeEnum.RUITO,
            ProductTypeEnum.OTHER};

        String l_strProductAll = "\n";
        String l_strWEB3TPSecurityValuationPerProductAll = "";
        for (int k = 0; k < l_types.length; k++)
        {
            List list = (List) classifiedProducts.get(l_types[k]);
            if (list == null)
            {
                continue;
            }
            int i = 0;
            String l_strProducts = l_types[k].stringValue() + ":\n";
            //�Ώۖ���
            for (Iterator l_it = list.iterator(); l_it.hasNext(); )
            {
                l_strProducts += "[" + i + "]" + l_it.next();
                i += 1;
            }
            l_strProductAll = l_strProductAll + l_strProducts;
            //�������ƕ]��
            i = 0;
            String l_strWEB3TPSecurityValuationPerProduct = l_types[k].stringValue() +
                ":\n";
            for (Iterator l_it = list.iterator(); l_it.hasNext(); )
            {
                WEB3TPSecurityValuationProduct l_product = (
                    WEB3TPSecurityValuationProduct)
                    l_it.next();
                l_strWEB3TPSecurityValuationPerProduct += "[" + i + "]" +
                    this.getSecurityValuationPerProduct(l_product); ;
                i += 1;
            }
            l_strWEB3TPSecurityValuationPerProductAll =
                l_strWEB3TPSecurityValuationPerProductAll +
                l_strWEB3TPSecurityValuationPerProduct;
        }

        return ToStringUtils
            .newToStringBuilder(this)
            .append("super", super.toString())
            .append("products", l_strProductAll)
            .append("valuationPerProducts", l_strWEB3TPSecurityValuationPerProductAll)
            .append("contractInfo", contractInfo)
            .toString();
    }

}
@
