head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPRestraint.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 �x�� �a��(FLJ) �V�K�쐬
                   2006/09/11 ���G�� (���u) ���f��No.013
                   2006/09/11 ���G�� (���u) ���f��No.015
                   2006/09/11 ���G�� (���u) ���f��No.016  
                   2006/09/12 ���G�� (���u) ���f��No.034 �ANo.035
                   2007/01/22 ���؎q (���u) ���f��No.093
                   2007/03/19 �{�{ �ē� (SCS) ���f��No.098
                   2007/04/10 �{�{�ē�(SCS) ���f��No.102
Revision History : 2007/07/25 �Ј���(���u) ���f��No.134�ANo.136
Revision History : 2007/11/05 ��іQ(���u) ���f��No.221�ANo.222�ANo.223�ANo.225�ANo.226�ANo.228
Revision History : 2008/05/27 �И���(���u) ���f��No.285�ANo.286
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3IpoOfferTradingpowerCheckDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3RestraintDivDef;
import webbroker3.gentrade.data.SecurityShortageAccountDao;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.data.TpOtherTempRestraintRow;
import webbroker3.tradingpower.define.WEB3TPAccountTaxTypeKeyDef;
import webbroker3.tradingpower.define.WEB3TPMutualFundAdvanceRestraintDef;
import webbroker3.tradingpower.define.WEB3TPPaymentApplicationDivDef;
import webbroker3.tradingpower.define.WEB3TPServiceChargeRestraintDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�S����)<BR>
 * ���̑��S������\������B
 *
 */
public class WEB3TPRestraint extends WEB3TPAssetValuation
{
    /**
     * (�������������S�����ꗗ)<BR>
     */
    private List todayDepFundRestraints;

    /**
     * (�L���T�[�r�X���p�S�����ꗗ)<BR>
     */
    private List serviceChargeRestraints;

    /**
     * (IPO�S�����ꗗ)<BR>
     */
    private List ipoRestraints;

    /**
     * (�����S�����ꗗ)<BR>
     */
    private List dayIntAdjustRestraints;

    /**
     * (�萔����������S�����ꗗ)<BR>
     */
    private List conCommAdjustRestraints;

    /**
     * (���n�v�ōS�����ꗗ)<BR>
     */
    private List capitalGainTaxRestraints;

    /**
     * (�����M�����n�v�ōS�����ꗗ)<BR>
     */
    private List mutualFundCapitalGainTaxRestraints;

    /**
     * (�����M���O��S�����ꗗ)<BR>
     */
    private List mutualFundAdvanceRestraints;
    
    /**
     * (�X�g�b�N�I�v�V�������t����S�����ꗗ)
     */
    private List stockOptionSellAmountRestraints;
    
    /**
     * (�a����S�ۏo���S�����ꗗ)<BR>
     */
    private List cashDepositRestraints;

    /** 
     * (������)<BR>
     */
    WEB3TPTransactionAmount transactionAmount;

    /**
     * (���S�����ꗗ)<BR>
     */
    private List tempRestraints;

    /**
     * ���t�t�H�[�}�b�g
     */
    private static String BIZDATE_FORMAT = "yyyyMMdd";

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPRestraint.class);

    /**
     * (�S����)<BR>
     * (�f�t�H���g�R���X�g���N�^)<BR>
     * <BR>
     * �P�j�ȉ��̃����o�[��ArrayList�ŏ�����<BR>
     * <BR>
     * �@@�EIPO�S�����ꗗ<BR>
     * �@@�E�T�[�r�X���p�S�����ꗗ<BR>
     * �@@�E�X�g�b�N�I�v�V�������p����S�����ꗗ<BR>
     * �@@�E�萔����������S�����ꗗ<BR>
     * �@@�E���n�v�ōS�����ꗗ<BR>
     * �@@�E�������������S�����ꗗ<BR>
     * �@@�E�����M�����n�v�ōS�����ꗗ<BR>
     * �@@�E�����M���O��S�����ꗗ<BR>
     * �@@�E�����S�����ꗗ<BR>
     * �@@�E�a����S�ۏo���S�����ꗗ<BR>
     * �@@�E���S�����ꗗ<BR>
     * @@roseuid 4104CB1F0049
     */
    public WEB3TPRestraint()
    {
        this.todayDepFundRestraints = new ArrayList();
        this.serviceChargeRestraints = new ArrayList();
        this.ipoRestraints = new ArrayList();
        this.dayIntAdjustRestraints = new ArrayList();
        this.conCommAdjustRestraints = new ArrayList();
        this.capitalGainTaxRestraints = new ArrayList();
        this.mutualFundCapitalGainTaxRestraints = new ArrayList();
        this.mutualFundAdvanceRestraints = new ArrayList();
        this.stockOptionSellAmountRestraints = new ArrayList();
        this.cashDepositRestraints = new ArrayList();
        this.tempRestraints = new ArrayList();
    }

    /**
     * (create�S����)<BR>
     * �S�����𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X�𐶐�<BR>
     * �Q�j�@@�l��ݒ�<BR>
     * �@@�ڋq��񁁈���.������.get�ڋq���()<BR>
     * �@@�v�Z����������.������.get�v�Z����()<BR>
     * �@@���������e������.������.get���������e()<BR> 
     * �R�j�@@�C���X�^���X��ԋp<BR>
     *
     * @@param l_this ������
     * @@return WEB3TPRestraint
     */
    public static WEB3TPRestraint create(WEB3TPCashValuation l_valuation)
    {
        WEB3TPRestraint l_instance = new WEB3TPRestraint();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        l_instance.setTransactionAmount(l_valuation.getTransactionAmount());
        return l_instance;

    }

    /**
     * (calc���̑��S����) <BR>
     *<BR>
     * ����.�w����ɂ����邻�̑��S�������W�v���A���ʂ�ԋp����B<BR> 
     *<BR>
     * �P�j����.�w����ɂ����邻�̑��S�������W�v����B <BR>
     * <BR>
     *  [�v�Z��] <BR>
     *�@@�@@�h���̑��S�����h = �@@this.calc�������������S����(:Date = ����.�w���) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+�@@this.calc�T�[�r�X���p�S����(:Date = ����.�w���)<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calcIPO�S����(:Date = ����.�w���)�@@ <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calc�����S����(:Date = ����.�w���) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calc�萔����������S����(:Date = ����.�w���) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calc���n�v�ōS����(:Date = ����.�w���) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calc�����M�����n�v�ōS����(:Date = ����.�w���) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calc�����M���O��S����(:Date = ����.�w���) <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calc�X�g�b�N�I�v�V�������t����S����(:Date = ����.�w���)<BR> 
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+  this.calc���S����(:Date = ����.�w���)<BR>
     *<BR>
     * �Q�j�W�v�������̑��S������ԋp����B <BR>
     *<BR>
     *�@@�ԋp�l�F�h���̑��S�����h <BR>
     * @@param l_datDate (�w���)
     * @@return double
     * @@roseuid 40DAABF203CE
     */
    public double calcOtherRestraints(Date l_datDate)
    {
        double l_dblOtherRest = 
            calcTodayDepFundRestraint(l_datDate) +
            calcServiceChargeRestraint(l_datDate) +
            calcIPORestraint(l_datDate) +
            calcDayInterestAjdustmentRestraint(l_datDate) +
            calcConsolidatedCommissionAdjustmentRestraint(l_datDate) +
            calcCapitalGainTaxRestraint(l_datDate) +
            calcMutualFundCapitalGainTaxRestraint(l_datDate) +
            calcMutualFundAdvanceRestraint(l_datDate) +
            calcStockOptionSellAmountRestraint(l_datDate) +
            calcTempRestraint(l_datDate);

        return l_dblOtherRest;

    }
    
    /**
     * (calc�a����S�ۏo���S����)<BR> 
     * <BR>
     * ����.�w����ɂ�����a����S�ۏo���S�������W�v���A���ʂ�ԋp����B<BR> 
     * <BR>
     * �P�j����.�w����ɂ�����a����S�ۏo���S�������W�v����B<BR> 
     * <BR>
     * �@@�h�a����S�ۏo���S�����h = this.calc�S�������v() <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@List = this.�a����S�ۏo���S�����ꗗ <BR>
     * �@@�@@Date = ����.�w��� <BR>
     * <BR>
     * �Q�j�W�v�����a����S�ۏo���S������ԋp����B <BR>
     * <BR>
     * �@@�ԋp�l�F�h�a����S�ۏo���S�����h<BR> 
     * @@param l_datDate (�w���)
     * @@return double 
     */
    public double calcCashDepositRestraint(Date l_datDate)
    {
        return this.calcRestraintsTotal(this.cashDepositRestraints, l_datDate);
    }

    /**
     * (calc�������������S����)<BR>
     * ����.�w����ɂ����鑦�����������S�������W�v���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@�������������S�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@�������������S�������W�v����B<BR>
     * <BR>
     * �@@�������������S����(n)  �� ��(*)�������������S����.get�S����()<BR>
     * <BR>
     *  (*)�W�v�����F<BR>
     * �������������S�����ꗗ�ɂ��鑦�����������S����<BR>
     * ���@@�������������S����.is�ϓ����Ԓ�(n)��true<BR>
     * <BR>
     * �R�j�@@�������������S����(n)��ԋp����B<BR>
     * @@param l_datDate (�w���)
     * @@return double 
     * @@roseuid 40E9FD6E00F7
     */
    public double calcTodayDepFundRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(todayDepFundRestraints, l_datDate);
    }

    /**
     * (calc�L���T�[�r�X���p�S����)<BR>
     * ����.�w����ɂ����鑦�����������S�������W�v���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���T�[�r�X���p�S�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@�L���T�[�r�X���p�S�������W�v����B<BR>
     * <BR>
     * �@@�L���T�[�r�X���p�S����(n)  �� ��(*)�L���T�[�r�X���p�S����.get�S����()<BR>
     * <BR>
     *  (*)�W�v�����F<BR>
     * �L���T�[�r�X���p�S�����ꗗ�ɂ���L���T�[�r�X���p�S����<BR>
     * ���@@���L���T�[�r�X���p�S����.is�ϓ����Ԓ�(n)��true<BR>
     * <BR>
     * �R�j�@@�L���T�[�r�X���p�S����(n)��ԋp����B<BR>
     * @@param l_datDate (�w���)
     * @@return double
     */
    public double calcServiceChargeRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(serviceChargeRestraints, l_datDate);
    }
    
    /**
     * (calcIPO�S����)<BR>
     * ����.�w����ɂ�����IPO�S�������W�v���A�l��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO�S�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@IPO�S�������W�v����B<BR>
     * <BR>
     * �@@IPO�S����(n)  = ��(*)IPO�S����.get�S����()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * IPO�S�����ꗗ�ɂ���IPO�S����<BR>
     * ���@@IPO�S����.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@IPO�S����(n)��ԋp����B<BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     * @@roseuid 40E9FD87002C
     */
    public double calcIPORestraint(Date l_datDate)
    {
        return calcRestraintsTotal(ipoRestraints, l_datDate);
    }

    /**
     * (calc�����S����)<BR>
     * ����.�w����ɂ���������S�������W�v���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j �����S�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@�����S�������W�v����B<BR>
     * �@@�����S����(n)  = ��(*)�����S����.get�S����()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * �����S�����ꗗ�ɂ�������S����<BR>
     * ���@@�����S����.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@�����S����(n)��ԋp����B<BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     * @@roseuid 40E9FD8D0397
     */
    private double calcDayInterestAjdustmentRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(dayIntAdjustRestraints, l_datDate);
    }

    /**
     * (calc�萔����������S����)<BR>
     * ����.�w����ɂ�����萔����������S�������W�v���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@�萔����������S�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@�萔����������S�������W�v����B<BR>
     * �@@�萔����������S����(n) �@@= ��(*)�萔����������S����.get�S����()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * �萔����������S�����ꗗ�ɂ���萔����������S����<BR>
     * ���@@�萔����������S����.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@�萔����������S����(n)��ԋp����B<BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     * @@roseuid 40E9FD9500C8
     */
    private double calcConsolidatedCommissionAdjustmentRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(conCommAdjustRestraints, l_datDate);
    }

    /**
     * (calc���n�v�ōS����)<BR>
     * ����.�w����ɂ�������n�v�ōS�������W�v���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@���n�v�ōS�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@���n�v�ōS�������W�v����B<BR>
     * �@@���n�v�ōS����(n)  = ��(*)���n�v�ōS����.get�S����()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * ���n�v�ōS�����ꗗ�ɂ�����n�v�S����<BR>
     * ���@@���n�v�ōS����.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@���n�v�ōS����(n)��ԋp����B<BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     * @@roseuid 40E9FD9C02FB
     */
    private double calcCapitalGainTaxRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(capitalGainTaxRestraints, l_datDate);
    }
    
    /**
     * (calc�����M�����n�v�ōS����)<BR>
     * ����.�w����ɂ����铊���M�����n�v�ōS�������W�v���A�l��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����M�����n�v�ōS�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@�����M�����n�v�ōS�������W�v����B<BR>
     * <BR>
     * �@@�����M�����n�v�ōS����(n)  = ��(*)�����M�����n�v�ōS����.get�S����()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * �����M�����n�v�ōS�����ꗗ�ɂ��铊���M�����n�v�ōS����<BR>
     * ���@@�����M�����n�v�ōS����.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@�����M�����n�v�ōS����(n)��ԋp����B<BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     */
    public double calcMutualFundCapitalGainTaxRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(mutualFundCapitalGainTaxRestraints, l_datDate);
    }
    
    /**
     * (calc�����M���O��S����)<BR>
     * ����.�w����ɂ����铊���M���O��S�������W�v���A�l��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����M���O��S�����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@�����M���O��S�������W�v����B<BR>
     * <BR>
     * �@@�����M���O��S����(n)  = ��(*)�����M���O��S����.get�S����()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * �����M���O��S�����ꗗ�ɂ��铊���M���O��S����<BR>
     * ���@@�����M���O��S����.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@�����M���O��S����(n)��ԋp����B<BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     */
    public double calcMutualFundAdvanceRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(mutualFundAdvanceRestraints, l_datDate);
    }
    
    /**
     * (calc�X�g�b�N�I�v�V�������t����S����) <BR>
     * <BR>
     * ����.�w����ɂ�����X�g�b�N�I�v�V�������t����S�������W�v���A���ʂ�ԋp����B<BR> 
     * <BR>
     * �P�j����.�w����ɂ�����X�g�b�N�I�v�V�������t����S�������W�v����B <BR>
     * <BR>
     * �@@�h�X�g�b�N�I�v�V�������t����S�����h = this.calc�S�������v() <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@List = this.�X�g�b�N�I�v�V�������t����S�����ꗗ <BR>
     * �@@�@@Date = ����.�w��� <BR>
     * <BR>
     * �Q�j�W�v�����X�g�b�N�I�v�V�������t����S������ԋp����B <BR>
     * <BR>
     * �@@�ԋp�l�F�h�X�g�b�N�I�v�V�������t����S�����h <BR>
     * 
     * @@param l_datDate (�w���)
     * @@return double
     */
    public double calcStockOptionSellAmountRestraint(Date l_datDate)
    {
        return calcRestraintsTotal(stockOptionSellAmountRestraints, l_datDate);
    }

    /**
     * (calc�S�������v)<BR>
     * ����.���X�g�Ɋ܂܂��S�����ϓ����W�v��<BR>
     * �l��ԋp����B
     * <BR>
     * @@param l_restraints�@@�S�����ϓ��̏W��
     * @@param l_datDate�@@(�w���)
     * @@return double
     */
    private double calcRestraintsTotal(List l_restraints, Date l_datDate)
    {
        double l_dblTotal = 0.0d;

        for (int i = 0; i < l_restraints.size(); i++)
        {
            Object l_object = l_restraints.get(i);

            WEB3TPRestraintReflector l_reflector = (WEB3TPRestraintReflector) l_object;

            if (l_reflector.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_reflector.getAmount();
            }
        }
        return l_dblTotal;
    }

    /**
     * (calc�O��S�����W�v<���v��S�����v�㕪>)<BR>
     * ����.�g�����U�N�V�����������ƁA���ꔭ�����̑O��S�����̓� <BR>
     * ���v��S�����Ƒ��E�\�ȍS�������W�v����B <BR>
     * <BR>
     * ���O��S����<BR> 
     * �E�������������S����<BR> 
     * �E�T�[�r�X���p�S���� <BR>
     * �EIPO�S���� <BR>
     * �E�����M���O��S����<BR> 
     * <BR>
     * 1)IPO�S�����̂����A����.�g�����U�N�V�����������Ɠ��ꔭ������ <BR>
     * �@@IPO�S�������W�v����B <BR>
     * <BR>
     * �@@��LOOP����:this.IPO�S�����ꗗ�̗v�f���� <BR>
     * �@@�@@2-1)IPO�S�����ꗗ���AIPO�S�����I�u�W�F�N�g���擾����B<BR> 
     * �@@�@@�@@-this.IPO�S�����ꗗ.get(index)���R�[�� <BR>
     * <BR>
     * �@@�@@2-2)����.�g�����U�N�V�����������Ɠ��ꔭ������IPO�S�������W�v����B <BR>
     * �@@�@@�@@[a.IPO�S����.get�g�����U�N�V����������() == ����.�g�����U�N�V���������� �̏ꍇ]<BR> 
     * <BR>
     * �@@�@@�@@�@@�O��S�����W�v<���v��S�����v�㕪> += IPO�S����.get�S����() <BR>
     * <BR>
     * 2)�������������S�����̂����A����.�g�����U�N�V�����������Ɠ��ꔭ������ <BR>
     * �@@�������������S�������W�v����B <BR>
     * <BR>
     * �@@��LOOP����:this.�������������S�����ꗗ�̗v�f���� <BR>
     * �@@�@@2-1)�������������S�����ꗗ���A�������������S�����I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@-this.�������������S�����ꗗ.get(index)���R�[�� <BR>
     * <BR>
     * �@@�@@2-2)����.�g�����U�N�V�����������Ɠ��ꔭ�����̑������������S�������W�v����B <BR>
     * <BR>
     * �@@�@@�@@[a.�������������S����.get�g�����U�N�V����������() == ����.�g�����U�N�V���������� �̏ꍇ] <BR>
     * <BR>
     * �@@�@@�@@�@@�O��S�����W�v<���v��S�����v�㕪> += �������������S����.get�S����() <BR>
     * <BR>
     * 3)�W�v�����O��S�����W�v<���v��S�����v�㕪>��ԋp����B <BR>
     * <BR>
     * @@param l_finTransactionDate(�g�����U�N�V����������)
     * @@return double
     */
    public double calcAdvanceRestraintOffset(Date l_finTransactionDate)
    {
        //�O��S�����W�v<���v��S�����v�㕪>
        double l_dblAdRestOffset = 0.0d;

        //LOOP����:this.IPO�S�����ꗗ�̗v�f����
        for(int i = 0; i < ipoRestraints.size(); i++)
        {
            //IPO�S�����ꗗ���AIPO�S�����I�u�W�F�N�g���擾����B
            WEB3TPIPORestraintReflector l_ipoRestRef = (WEB3TPIPORestraintReflector)ipoRestraints.get(i);

            /*
             * ����.�g�����U�N�V�����������Ɠ��ꔭ������IPO�S�������W�v����B
             */
            //[a.IPO�S����.get�g�����U�N�V����������() == ����.�g�����U�N�V���������� �̏ꍇ]
            if(WEB3DateUtility.compareToDay(
                    l_finTransactionDate,
                    l_ipoRestRef.getFinTransactionDate()) == 0)
            {
                //�O��S�����W�v<���v��S�����v�㕪> += IPO�S����.get�S����()
                l_dblAdRestOffset += l_ipoRestRef.getAmount();
            }
        }

        //LOOP����:this.�������������S�����ꗗ�̗v�f����
        for(int i = 0; i < todayDepFundRestraints.size(); i++)
        {
            //�������������S�����ꗗ���A�������������S�����I�u�W�F�N�g���擾����B
            WEB3TPTodayDepFundRestraintReflector l_todayDepFundRestRef = (WEB3TPTodayDepFundRestraintReflector)todayDepFundRestraints.get(i);

            /*
             * ����.�g�����U�N�V�����������Ɠ��ꔭ�����̑������������S�������W�v����B
             */
            //[a.�������������S����.get�g�����U�N�V����������() == ����.�g�����U�N�V���������� �̏ꍇ]
            if(WEB3DateUtility.compareToDay(
                    l_finTransactionDate,
                    l_todayDepFundRestRef.getFinTransactionDate()) == 0)
            {
                //�O��S�����W�v<���v��S�����v�㕪> += �������������S����.get�S����()
                l_dblAdRestOffset += l_todayDepFundRestRef.getAmount();
            }
        }

        //�W�v�����O��S�����W�v<���v��S�����v�㕪>��ԋp����B
        return l_dblAdRestOffset;
    }

    /**
     * (calc���S����)<BR>
     * calc���S����<BR>
     * <BR>
     * ����.�w����ɂ����鉼�S�������W�v���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j����.�w����ɂ����鉼�S�������W�v����B<BR>
     * <BR>
     * �@@�h���S�����h = this.calc�S�������v()<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@Date = ����.�w���<BR>
     * �@@�@@String[] = null<BR>
     * <BR>
     * �Q�j�W�v�������S������ԋp����B<BR>
     * <BR>
     * �@@�ԋp�l�F���S����<BR>
     * @@param l_datDate - (�w���)<BR>
     * �w���<BR>
     * @@return double
     */
    public double calcTempRestraint(Date l_datDate)
    {
        return this.calcTempRestraint(l_datDate, null);
    }

    /**
     * (get�������������S�����ꗗ)<BR>
     * �������������S�����ꗗ��Ԃ��B<BR>
     * @@return List
     * @@roseuid 40EB52AB0388
     */
    public List getTodayDepFundRestraints()
    {
        return todayDepFundRestraints;
    }

    /**
     * (get�L���T�[�r�X���p�S�����ꗗ)
     * �L���T�[�r�X���p�S�����ꗗ��ԋp����B
     * @@return List
     */
    public List getServiceChargeRestraints() {
        return serviceChargeRestraints;
    }
    
    /**
     * (getIPO�S�����ꗗ)<BR>
     * IPO�S�����ꗗ��Ԃ��B<BR>
     * @@return List
     * @@roseuid 40EB529E001D
     */
    public List getIPORestraints()
    {
        return ipoRestraints;
    }

    /**
     * (get�����S�����ꗗ)<BR>
     * �����S�����ꗗ��Ԃ��B<BR>
     * @@return List
     * @@roseuid 40ECABB203D7
     */
    public List getDayInterestAjdustmentRestraints()
    {
        return dayIntAdjustRestraints;
    }

    /**
     * (get�萔����������S�����ꗗ)<BR>
     * �萔����������S�����ꗗ��Ԃ��B<BR>
     * @@return List
     * @@roseuid 40ECABA80128
     */
    public List getConsolidatedCommissionAdjustmentRestraints()
    {
        return conCommAdjustRestraints;
    }

    /**
     * (get���n�v�ōS�����ꗗ)<BR>
     * ���n�v�ōS�����ꗗ��Ԃ��B<BR>
     * @@return List
     * @@roseuid 40EBAB5C0175
     */
    public List getCapitalGainTaxRestraints()
    {
        return capitalGainTaxRestraints;
    }

    /**
     * (get�����M�����n�v�ōS�����ꗗ)<BR>
     * �����M�����n�v�ōS�����ꗗ��Ԃ��B<BR>
     * @@return List
     */
    public List getMutualFundCapitalGainTaxRestraints()
    {
        return mutualFundCapitalGainTaxRestraints;
    }

    /**
     * (get�����M���O��S�����ꗗ)<BR>
     * �����M���O��S�����ꗗ��Ԃ��B<BR>
     * @@return List
     */
    public List getMutualFundAdvanceRestraints()
    {
        return mutualFundAdvanceRestraints;
    }
    
    /**
     * (get�a����S�ۏo���S�����ꗗ)<BR>
     * <BR>
     * this.get�a����S�ۏo���S�����ꗗ��ԋp����B<BR> 
     * @@return List
     */
    public List getCashDepositRestraints()
    {
        return this.cashDepositRestraints;
    }
    
    /**
     * (get�X�g�b�N�I�v�V�������t���S�����ꗗ)<BR> 
     *<BR>
     * this.�X�g�b�N�I�v�V�������t���S�����ꗗ��ԋp����B<BR> 
     * @@return List
     */
    public List getStockOptionSellAmountRestraints()
    {
        return this.stockOptionSellAmountRestraints;
    }

    /**
     * (get���S�����ꗗ)<BR>
     * get���S�����ꗗ<BR>
     * <BR>
     * this.���S�����ꗗ��ԋp����B<BR>
     * @@return List
     */
    public List getTempRestraints()
    {
        return this.tempRestraints;
    }

    /**
     * (do���̑��S�������[�h)<BR>
     *<BR>
     * ���̑��S�����Ɋ֘A����f�[�^�����[�h����B<BR> 
     *<BR>
     * ���V�[�P���X�}�u(�S����)do���̑��S�������[�h�v�Q�� <BR>
     */
    public void load()
    {
        loadTodayDepFundRestraints();
        loadServiceChargeRestraints();
        loadIPORestraints();
        loadDayInterestAdjustmentRestraints();
        loadConsolidatedCommissionAdjustmentRestraints();
        loadCapitalGainTaxRestraints();
        loadMutualFundCapitalGainTaxRestraints();
        loadMutualFundAdvanceRestraints();
        loadStockOptionSellAmountRestraints();
        loadCashDepositRestraints();
        loadTempRestraints();
    }

    /**
     * (do�������������S�������[�h)<BR>
     * �������������S���������[�h����B<BR>
     * <BR>
     * �v�Z����.�c�Ɠ��iT+1�`T+5�j�ɑ΂��āA�ȉ��̏������J��Ԃ��s���B<BR>
     * <BR>
     * �P�j�@@������̔z����擾����B<BR>
     * <BR>
     *     �����񁁎��������]��.get����������()<BR>
     * <BR>
     *     �����F<BR>
     *     �w������c�Ɠ�[n]<BR>
     *     �g�����U�N�V�����^�C�v���o70�F����������A 130�F��������p<BR>
     * <BR>
     * �擾���������񌏐����ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@������������������ǂ����`�F�b�N����B<BR>
     * <BR>
     * �@@����������������ł������ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �R�j�@@�������������S�����𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR><BR>
     * �E�S����      ��  ������.get��n���()<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i�j<BR>
     * �E�ϓ����f�J�n�����c�Ɠ�[0]<BR>
     * �E�ϓ����f�I����������.��n��-1<BR>
     * <BR>
     * �S�j�@@�������������S�����ꗗ�ɒǉ�����B<BR>
     * @@roseuid 40BD65DB0306
     */
    private void loadTodayDepFundRestraints()
    {
        FinTransactionType[] l_transactionType =
            {
            FinTransactionType.EQTYPE_EQUITY_BUY
        };
                        
        Map l_todayDepFundMap = new HashMap();
        
        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();
                
        //T+1�`T+5�܂�
        for (int i = WEB3TPSpecifiedPointDef.T_1; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            
            //��n����T+1�`T+5�̌��������邢�͌����̊���������擾����
            WEB3TPTransactionReflector[] l_tranRefs = getTransactionAmount().
                getEquityTransactions(l_transactionType, l_datBizDate);
            
            //�擾������������[�v����
            for (int j = 0; j < l_tranRefs.length; j++)
            {               
                Date l_tranDate = GtlUtils.truncateDate(l_tranRefs[j].getFinTransactionDate());
                long l_lngProductId = l_tranRefs[j].getProductId();                                                
                
                //����ID�ŁA�Y��������s���̌������ʂ��L���b�V������Ă��邩���݃`�F�b�N
                Map l_execDateMap = (Map)l_todayDepFundMap.get(new Long(l_lngProductId));
                
                //����ID�Ńq�b�g�����ꍇ                
                if(l_execDateMap != null)
                {
                    //�������ʂ��L���b�V������Ă���ꍇ����������
                    if(l_execDateMap.get(l_tranDate) != null)
                    {
                        //�������������S�����I�u�W�F�N�g�쐬�E�ǉ�
                        WEB3TPTodayDepFundRestraintReflector l_ref =
                            WEB3TPTodayDepFundRestraintReflector.create(getCalcCondition(),
                            Math.abs(l_tranRefs[j].getAmount()), l_tranDate, l_datBizDate);
                        
                        log.debug("already cashed.");
                        log.debug("addTodayFundDepRestraint ===> source = " + l_tranRefs[j]);

                        addTodayFundDepRestraint(l_ref);
                    }

                    //�������ʂ��L���b�V������Ă��Ȃ��ꍇ
                    else
                    {
                        //DB���猟��
                        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodayDepFundProducts(l_lngProductId, l_tranDate, this);                    
                        
                        //�������ꂽ�ꍇ
                        if((l_rows != null) && (l_rows.size() > 0))
                        {
                            //�������������S�����I�u�W�F�N�g�쐬�E�ǉ�
                            WEB3TPTodayDepFundRestraintReflector l_ref =
                                WEB3TPTodayDepFundRestraintReflector.create(getCalcCondition(),
                                Math.abs(l_tranRefs[j].getAmount()), l_tranDate, l_datBizDate);

                            log.debug("A. find by db.");
                            log.debug("addTodayFundDepRestraint ===> source = " + l_tranRefs[j]);
    
                            addTodayFundDepRestraint(l_ref);
                            
                            //�L���b�V���Ɏ�����s����Key,�������ʂ�Value�Ƃ��Ēǉ�
                            l_execDateMap.put(l_tranDate, l_rows);
                        }
                    }
                }

                //����ID�Ńq�b�g���Ȃ������ꍇ                
                else
                {
                    //DB���猟��
                    List l_rows = WEB3TPPersistentDataManager.getInstance().getTodayDepFundProducts(l_lngProductId, l_tranDate, this);                    
                    
                    //�������ꂽ�ꍇ
                    if((l_rows != null) && (l_rows.size() > 0))
                    {
                        //�������������S�����I�u�W�F�N�g�쐬�E�ǉ�
                        WEB3TPTodayDepFundRestraintReflector l_ref =
                            WEB3TPTodayDepFundRestraintReflector.create(getCalcCondition(),
                            Math.abs(l_tranRefs[j].getAmount()), l_tranDate, l_datBizDate);

                        log.debug("B. find by db.");
                        log.debug("addTodayFundDepRestraint ===> source = " + l_tranRefs[j]);

                        addTodayFundDepRestraint(l_ref);                    
                        
                        //�V����Map���쐬
                        //������s����Key,�������ʂ�Value�Ƃ��Ēǉ�
                        l_execDateMap = new HashMap();
                        l_execDateMap.put(l_tranDate, l_rows);
                        //�L���b�V���ɖ���ID��Key�A�쐬����������s��-�������ʂ�Map��Value�Ƃ��Ēǉ�                       
                        l_todayDepFundMap.put(new Long(l_lngProductId), l_execDateMap);

                    }
                }
            }
                
        }

    }

    /**
     * (do�L���T�[�r�X���p�S�������[�h)<BR>
     * �L���T�[�r�X���p�S���������[�h����B<BR>
     * <BR>
     *  �P�j�ΏۂƂȂ铖���ȍ~�o���������擾����B<BR>
     * �@@�o�������������֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~���o������()<BR>
     * �ȍ~�̏�����get�����ȍ~���o������()�̖߂�l�̗v�f����LOOP����B<BR>
     * �Q�j�ȉ��̏����Ƀ}�b�`�����ꍇ�A���o�������P�ʂ��<BR>
     * �L���T�[�r�X���p�S�������쐬����B<BR>
     * �����F<BR>
     * ���o�������P��.������ʁ@@= �o��<BR>
     * ���o�������P��.�o���\���敪 != null<BR>
     * ���o�������P��.�o���\���敪 != mf<BR>
     * ���o�������P��.��n�� > T+0<BR>
     * �R�j�L���T�[�r�X���p�S����.create�L���T�[�r�X���p�S����()�̈����Ɉȉ���n���B<BR>
     * �v�Z���� = this.get�v�Z����()<BR>
     * ��n��� = ���o�������P��.��������<BR>
     * �g�����U�N�V���������� = ���o�������P��.������<BR>
     * ��n�� = ���o�������P��.��n��<BR>
     * �S�j�L���T�[�r�X���p�S�����ꗗ�ɒǉ�����B<BR>
     */
    private void loadServiceChargeRestraints()
    {
        //�ΏۂƂȂ�ڋq�̕��X���T�[�r�X���p�S�������v�シ��ꍇ�̂�
        //���[�h����B
       String l_strVal = this.getCalcCondition().getInstBranCalcCondition(WEB3TPCalcCondition.SERVICE_CHARGE_RESTRAINT);       
       if(WEB3TPServiceChargeRestraintDef.EXCEPT.equals(l_strVal))
               return;
       
        
        //�P�j�ΏۂƂȂ铖���ȍ~�o���������擾����B<BR>
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCashInOutOrders(this);
        
        // �Q�j�ȉ��̏����Ƀ}�b�`�����ꍇ�A���o�������P�ʂ��<BR>
        //�L���T�[�r�X���p�S�������쐬����B<BR>
        //�����F<BR>
        //���o�������P��.������ʁ@@= �o��<BR>
        //���o�������P��.�o���\���敪 = �L�����<BR>
        //���o�������P��.��n�� > T+0<BR>
        
        Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        int i = 0;
        for(Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
        {
            AioOrderUnitRow l_row = (AioOrderUnitRow)l_iter.next();
            Date l_datDeliveryDate = l_row.getDeliveryDate();
                                    
            //�L���̔��f�F�L�����敪��null�����M���ɂ��o���\�񃌃R�[�h�łȂ��ꍇ�ɏC��
            if((OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType())) &&
                    (l_row.getPaymentApplicationDiv() != null) &&
                    (! WEB3TPPaymentApplicationDivDef.MF_SELL_WITH_CASH_OUT.equals(l_row.getPaymentApplicationDiv())))
            {
                //�o�C�L���O�萔���̏ꍇ�A����������S��
                //����ȊO�ʏ�̃T�[�r�X���p�̏ꍇT+0����S��
                Date l_datAcceptedDate = WEB3TPPaymentApplicationDivDef.BUYKING.equals(l_row.getPaymentApplicationDiv()) ?
                        WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd") : l_datT0;
                
                //��n����T+5�ȑO�̏ꍇ
                if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) >= 0)
                {
                    //��n����T+0����̂��̂����[�h
                    if((WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datT0) > 0) &&
                            (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datAcceptedDate) > 0))
                    {
                        //�R�j�T�[�r�X���p�S����.create�L���T�[�r�X���p�S����()�̈����Ɉȉ���n���B
                        WEB3TPServiceChargeRestraintReflector l_ref = WEB3TPServiceChargeRestraintReflector.create(getCalcCondition(), l_row.getQuantity() , l_datDeliveryDate, l_datAcceptedDate);                     
                        //�S�j�T�[�r�X���p�S�����ꗗ�ɒǉ�����B
                        addServiceChargeRestraint(l_ref);
                        log.debug("�T�[�r�X���p�S�����ǉ��F" + l_ref + " ���o�������P��=[" + i + "]=" + l_row);
                    }
                    //�����łȂ��ꍇ�̓��[�h���Ȃ�
                }
                //��n����T+5����̏ꍇ
                else
                {
                    //�������Ǝ�n���������̏ꍇ���T�[�r�X���p�S�����Ƃ��Čv�シ�邽�߃��[�h����B
                    //�R�j�T�[�r�X���p�S����.create�L���T�[�r�X���p�S����()�̈����Ɉȉ���n���B
                    WEB3TPServiceChargeRestraintReflector l_ref = WEB3TPServiceChargeRestraintReflector.create(getCalcCondition(), l_row.getQuantity(), l_datDeliveryDate, l_datAcceptedDate);
                    //�S�j�T�[�r�X���p�S�����ꗗ�ɒǉ�����B
                    addServiceChargeRestraint(l_ref);
                    log.debug("�T�[�r�X���p�S�����ǉ��F" + l_ref + " ���o�������P��=[" + i + "]=" + l_row);
                }                
            }
            i++;
        }
        
    }
    
    /**
     * (doIPO�S�������[�h)<BR>
     * IPO�S�����Ɋ֘A����f�[�^�����[�h����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(�S����)doIPO�S�������[�h�v�Q��<BR>
     * @@roseuid 40BEF66F014F
     */
    private void loadIPORestraints()
    {
        List l_orderRows = WEB3TPPersistentDataManager.getInstance().getTodaysIPOOrders(this);

        Date l_datT0 = getCalcCondition().getBizDate(0);

        for(Iterator l_iter = l_orderRows.iterator(); l_iter.hasNext();)
        {
            IpoOrderRow l_orderRow = (IpoOrderRow)l_iter.next();

            IpoProductRow l_productRow = WEB3TPPersistentDataManager.getInstance().getIPOProduct(
                    l_orderRow.getIpoProductId());

            //���I��
            Date l_lotDate = l_productRow.getLotDate();

            //�q�����f��
            //�q�����f���͍w���\�����ؓ��ƂȂ�
            Date l_accountReflectDate = l_productRow.getOfferEndDateProspec();

            //get�]�͌v�Z����
            WEB3TPCalcCondition l_calcCondition = getCalcCondition();

            //get��Е��X�ʗ]�͌v�Z����
            String l_strValue = l_calcCondition.getInstBranCalcCondition(
                WEB3TPCalcCondition.IPO_OFFER_TRADINGPOWER_CHECK);

            log.debug("l_orderRow = " + l_orderRow);
            log.debug("l_productRow getIpoProductId = " + l_productRow.getIpoProductId());
            log.debug("l_lotDate = " + l_lotDate);
            log.debug("l_accountReflectDate = " + l_accountReflectDate);

            //�q�����f���i�w���\�����ؓ��j��T+0�̏ꍇ�͗]�͑ΏۂƂ��Ȃ�
            //DeleteFlag�������Ă�������ւ̒����͗]�͑ΏۂƂ��Ȃ� (by�n�ӂ���)
            if ((WEB3IpoOfferTradingpowerCheckDef.CHECK.equals(l_strValue) &&
                WEB3LotResultDef.SUPPLEMENT.equals(l_orderRow.getLotResult()) &&
                !WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_orderRow.getOfferingDiv())) ||
                (l_accountReflectDate != null && l_datT0.compareTo(l_accountReflectDate) >= 0)
                || BooleanEnum.TRUE.equals(l_productRow.getDeleteFlag()))
            {
                continue;
            }

            WEB3TPIPORestraintReflector l_ref = WEB3TPIPORestraintReflector.create(
                    getCalcCondition(),
                    l_orderRow.getPayAmount(),
                    l_lotDate,
                    l_accountReflectDate);

            addIPORestraint(l_ref);
        }
    }

    /**
     * (do�����S�������[�h)<BR>
     * �����S���������[�h����B<BR>
     * <BR>
     * �v�Z����.�c�Ɠ��̔z�񌏐����ȉ��̏��������[�v����B<BR>
     * <BR>
     * �P�j�@@��n�����c�Ɠ�[n]�̕ԍϒ����̌������ȉ��̃��\�b�h���ĂсA�擾����B<BR>
     *     ���������]��.get�����ȍ~���������񌏐�()<BR>
     * <BR>
     *     �����F<BR>
     *     �g�����U�N�V�����^�C�v      ��
     * �o�M�p���ԍρA�M�p���ԍρA�M�p�����A�M�p���n�p<BR>
     *     �w������c�Ɠ�[n]<BR>
     * <BR>
     * �Q�j�@@���������z���擾����B<BR>
     * <BR>
     * ���Y�֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get���������z()<BR>
     * <BR>
     * �R�j�@@�����S�����𐶐�����B<BR>
     * <BR>
     *   �|�ݒ荀��<BR>
     *    �E��������        ��  �P�j�Ŏ擾���������ԍϒ�������<BR>
     *    �E���������z        ���@@�Q�j�Ŏ擾�������������z<BR>
     *    �E�S����             ��calc�����S����()<BR>
     *    �E��n��      ��  �c�Ɠ�[n]<BR>
     *    �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��()<BR>
     * �@@�@@�ϓ����f�J�n��  ��   ��n��<BR>
     * �@@�@@�ϓ����f�I����  ��   �c�Ɠ�[5]<BR>
     * <BR>
     * �S�j�@@�����S�����ꗗ�ɐ������������S������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BF1999023A
     */
    private void loadDayInterestAdjustmentRestraints()
    {
        FinTransactionType[] l_transactionType =
            {
            FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG,
            FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT,
            FinTransactionType.EQTYPE_SWAP_MARGIN_LONG,
            FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT
        };

        double l_dblAdjustment =
            WEB3TPPersistentDataManager.getInstance().getDayInterestAdjustment(this);
        
        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();

        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            int l_intCount = getTransactionAmount().getTodaysEquityTransactionsCount(
                l_transactionType, l_datBizDate);
            
            log.debug("����������������(getTodaysEquityTransactionsCount())=" + l_intCount);

            if (l_intCount > 0)
            {
                WEB3TPDayInterestAdjustmentRestraintReflector l_ref =
                    WEB3TPDayInterestAdjustmentRestraintReflector.create(getCalcCondition(),
                    l_intCount, l_dblAdjustment, l_datBizDate);

                log.debug("�ǉ����������S����=" +l_ref);
                addDayInterestAdjustmentRestraint(l_ref);
            }
        }

    }

    /**
     * (do�萔����������S�������[�h)<BR>
     * �萔����������S���������[�h����B<BR>
     * <BR>
     * �v�Z����.�c�Ɠ��̔z�񌏐����ȉ��̏��������[�v����B<BR>
     * <BR>
     * �P�j�@@���i�ʂɑΏۂƂȂ钍���̌������擾����B<BR>
     *     A.��������<��>�����������]��.get���������񌏐�<����>() (*)<BR>
     *     B.��������<�~�j��>�����������]��.get�~�j�������񌏐�<����>()<BR>
     *     C.��������<�I�v�V����>�����������]��.get�I�v�V���������񌏐�<����>()<BR>
     *     D.��������<���M>�����������]��.get���M�������񌏐�<����>()<BR>
     *     E.��������<�O��>�����������]��.get�O�������񌏐�<����>()<BR>
     * <BR>
     *     �����F<BR>
     *     (*)�g�����U�N�V�����^�C�v��null<BR>
     *         �w������c�Ɠ�[n]<BR>
     * <BR>
     * ���i���ɒ�������>0�̏ꍇ�A�ȍ~�̏������s���B<BR>
     * <BR>
     * �Q�j�@@�萔��������������z<���i��>���擾����B<BR>
     *  ���Y�֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�萔��������������z(�萔�����i�R�[�h)<BR>
     * <BR>
     * �R�j�@@�萔����������S�����𐶐�����B<BR>
     * <BR>
     *     �|�ݒ荀��<BR>
     *     �E�萔�����i�R�[�h���萔�����i�R�[�h<BR>
     *     �i  A---10:��ꊔ��<BR>
     *         B---12:�~�j����<BR>
     *         C---51:�����w��OP<BR>
     *         D---20:�����M��<BR>
     *         E---40:�O������ �j<BR>
     * <BR>
     *     �E�萔��������������z���Q�j�Ŏ擾�����萔��������������z<BR>
     *     �E��n��      ��  �c�Ɠ�[n]<BR>
     *    �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��()<BR>
     * �@@�@@�ϓ����f�J�n��  ��   ��n��<BR>
     * �@@�@@�ϓ����f�I����  ��   �c�Ɠ�[5]<BR>
     * <BR>
     *   �S�j�萔����������S�����ꗗ�ɐ����������萔����������S������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BEF62F01AD
     */
    private void loadConsolidatedCommissionAdjustmentRestraints()
    {
        //�����萔����������ΏۂƂȂ�g�����U�N�V�����^�C�v�i�����A���n�͊܂܂Ȃ��j
        final FinTransactionType[] l_equityTransactionTypes =
            {
                FinTransactionType.EQTYPE_EQUITY_BUY,
                FinTransactionType.EQTYPE_EQUITY_SELL,
                FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG,
                FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT                
            };

        double l_dblEquityAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.LISTING_STOCK,
                                                this);
        double l_dblMiniStockAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.MINI_STOCK,
                                                this);

        double l_dblMfAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.MUTUAL_FUND,
                                                this);
        double l_dblOptAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.INDEX_OP,
                                                this);
        double l_dblFeqAdjust = WEB3TPPersistentDataManager.getInstance().
            getConsolidatedCommissionAdjustment(WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                                                this);

        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();

        for (int i =  WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            
            //��
            int l_intEquityCount = getTransactionAmount().
                getTodaysEquityTransactionsCount(l_equityTransactionTypes, l_datBizDate);

            log.debug("l_datBizDate[" + i + "]" + l_datBizDate +
                      " = getTodaysEquityTransactionsCount" + l_intEquityCount);


            if (l_intEquityCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.LISTING_STOCK,
                    l_dblEquityAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);

            }

            //�~�j��
            int l_intMiniStockCount = getTransactionAmount().
                getTodaysMiniStockTransactionsCount(l_datBizDate);

            log.debug("l_datBizDate[" + i + "]" + l_datBizDate +
                      " = getTodaysMiniStockTransactionsCount" + l_intMiniStockCount);

            if (l_intMiniStockCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.MINI_STOCK,
                    l_dblMiniStockAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

            int l_intMfCount = getTransactionAmount().
                getTodaysMutualFundTransactionsCount(l_datBizDate);

            log.debug("l_datBizDate[" + i + "]" + l_datBizDate +
                      " = getTodaysMutualFundTransactionsCount" + l_intMfCount);

            if (l_intMfCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.MUTUAL_FUND,
                    l_dblMfAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

            //�I�v�V����
            int l_intOptCount = getTransactionAmount().getTodaysOptionTransactionsCount(
                l_datBizDate);

            if (l_intOptCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.INDEX_OP,
                    l_dblOptAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

            //�O��
            int l_intFeqCount = getTransactionAmount().
                getTodaysForeignEquityTransactionsCount(l_datBizDate);

            if (l_intFeqCount > 0)
            {
                WEB3TPConsolidatedCommissionAdjustmentRestraintReflector l_restRef =
                    WEB3TPConsolidatedCommissionAdjustmentRestraintReflector.create(
                    getCalcCondition(), WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                    l_dblFeqAdjust, l_datBizDate);

                addConsolidatedCommissionAdjustmentRestraint(l_restRef);
            }

        }
        
        
        for(int i = 0; i < conCommAdjustRestraints.size(); i++)
        {
            log.debug("conCommAdjustRestraints[" + i + "]" + conCommAdjustRestraints.get(i));
        }

    }

    /**
     * (do���n�v�ōS�������[�h)<BR>
     * <BR>
     * ���n�v�ōS���������[�h����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�S����)do���n�v�ōS�������[�h�v�Q��<BR>
     * <BR>
     */
    private void loadCapitalGainTaxRestraints()
    {
        //���ׂē���������򂠂�łȂ���΃��[�h���Ȃ�
        if((!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE)) &&
                (!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT)) &&
                (!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE)) &&
                (!getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE_NEXT)))
        {
            return;         
        }       
        
        //�N���܂����̂������擾����B
        int l_intNextYearDay = getNextYearBizDateIndex();       
        log.debug("���N�ɂȂ�c�Ɠ��̃C���f�b�N�X=" + l_intNextYearDay);

        WEB3TPPersistentDataManager l_dataMgr = WEB3TPPersistentDataManager.getInstance();

        //�ݐϏ��n���v�����[�h����B
        List l_tpCashBalanceRows = l_dataMgr.getTpCashBalances(this);

        double l_thisTermCapGain = 0.0d;
        double l_nextMonthCapGain = 0.0d;

        for (Iterator l_iter = l_tpCashBalanceRows.iterator(); l_iter.hasNext(); )
        {
            TpCashBalanceRow l_row = (TpCashBalanceRow) l_iter.next();
            l_thisTermCapGain += l_row.getCurrentTermCapitalGain();
            l_nextMonthCapGain += l_row.getNextMonthCapitalGain();
        }

        WEB3TPAccumulatedCapitalGain l_accCapGain = WEB3TPAccumulatedCapitalGain.create(
            l_thisTermCapGain, l_nextMonthCapGain);

        //�������n���v�����[�h����B
        int l_intLastBizDateIndex = getCalcCondition().getSpecifiedPointRange();
        
        WEB3TPCapitalGain[] l_capitalGains = new WEB3TPCapitalGain[l_intLastBizDateIndex + 1];
        
        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);            
            l_capitalGains[i] = WEB3TPCapitalGain.create(l_datBizDate);           
        }

        //������������n�����ɓ����ȍ~���n���v���v��
        //
        Map l_eqOrderUnitRowMap = new HashMap();
        List l_eqOrderUnitRows = l_dataMgr.getTodaysSpecialWithHoldEquityOrders(this);
        for (Iterator l_iter = l_eqOrderUnitRows.iterator(); l_iter.hasNext(); )
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow) l_iter.next();
            //���n������ɔ����Ē����P��ID�ƒ����P�ʂ�Map���쐬
            l_eqOrderUnitRowMap.put(new Long(l_row.getOrderUnitId()), l_row);
            
            //����蒍���̏ꍇ
            if(OrderOpenStatusEnum.OPEN.equals(l_row.getOrderOpenStatus()) &&
                    (l_row.getExecutedQuantity() == 0))
            {
                for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
                {
                    if(WEB3DateUtility.compareToDay(l_capitalGains[i].getDeliveryDate(), l_row.getDeliveryDate()) == 0)
                    {
                        //T+0�Ɠ��N�̏ꍇ
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("�������n���v�Ɍv��<���n>=" + l_row);
                            }
                        }
                        //���N�̏ꍇ
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());              
                                log.debug("�������n���v�Ɍv��<���n>=" + l_row);
                                
                            }                           
                        }
                    }
                }
                
            }

        }
        
        
        //���g�����U�N�V��������n�����ɓ����ȍ~���n���v���v��
        List l_eqTranRows = l_dataMgr.getTodaysSpecialWithHoldEquityFinTransactions(
            this);

        for (Iterator l_iter = l_eqTranRows.iterator(); l_iter.hasNext(); )
        {
            EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow) l_iter.next();            
            FinTransactionType l_finTransactionType = l_row.getFinTransactionType();            
                        
            for (int i = 0; i <= l_intLastBizDateIndex; i++)
            {
                if(WEB3DateUtility.compareToDay(l_capitalGains[i].getDeliveryDate(), l_row.getDeliveryDate()) == 0)
                {
                    //�ŋ敪�`�F�b�N
                    //��������蕪
                    //���n��蕪�i��������ꂽ���̂͒����̃}�b�v�ɓo�^����Ă��Ȃ��̂Ōv�サ�Ȃ��j
                    if(FinTransactionType.EQTYPE_EQUITY_SELL.equals( l_finTransactionType) 
                            || (FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_finTransactionType) &&
                                    (l_eqOrderUnitRowMap.get(new Long(l_row.getOrderUnitId())) != null)))
                    {
                        //T+0�Ɠ��N�̏ꍇ
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());           
                                log.debug("�������n���v�Ɍv��<������>=" + l_row);
                            }
                        }
                        //���N�̏ꍇ
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("�������n���v�Ɍv��<������>=" + l_row);
                            }                           
                        }
                    }
                    
                    //�M�p�ŋ敪�`�F�b�N
                    else if(FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG.equals( l_finTransactionType) 
                            || FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT.equals(l_finTransactionType))
                    {
                        //T+0�Ɠ��N�̏ꍇ
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("�������n���v�Ɍv��<���n>=" + l_row);
                            }
                        }
                        //���N�̏ꍇ
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.MARGIN_TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("�������n���v�Ɍv��<���n>=" + l_row);
                            }                           
                        }
                    }
                    
                }
            }
        }

        //�O���g�����U�N�V��������n�����ɓ����ȍ~���n���v���v��
        List l_feqTranRows = l_dataMgr.getTodaysSpecialWithHoldFeqFinTranasctions(
                this);
        
        Map l_l_feqOrderUnitMap = new HashMap();
        if(l_feqTranRows.size() > 0)
        {
            List l_feqOrderUnitRows = l_dataMgr.getTodaysFeqOrders(this); 
            for(int i = 0; i < l_feqOrderUnitRows.size(); i++)
            {
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_feqOrderUnitRows.get(i);
                l_l_feqOrderUnitMap.put(new Long(l_orderUnitRow.getOrderUnitId()), l_orderUnitRow);
            }
            
        }
        for (Iterator l_iter = l_feqTranRows.iterator(); l_iter.hasNext(); )
        {
            FeqFinTransactionRow l_row = (FeqFinTransactionRow) l_iter.next();
            //�O���g�����U�N�V�������璍���P�ʂh�c�ŕR�t���O�������P�ʂ��擾
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_l_feqOrderUnitMap.get(new Long(l_row.getOrderUnitId()));            
            //���������݂��Ă���Όv��
            if(l_orderUnitRow != null)
            {
                log.debug("�Z���N�g���ꂽ�O���g�����U�N�V����=" + l_row);
                
                for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
                {
                    if(WEB3DateUtility.compareToDay(l_capitalGains[i].getDeliveryDate(), l_row.getDeliveryDate()) == 0)
                    {
                        //T+0�Ɠ��N�̏ꍇ
                        if(i < l_intNextYearDay)
                        {   
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());         
                                log.debug("�������n���v�Ɍv��<�O��>=" + l_row);
                            }
                        }
                        //���N�̏ꍇ
                        else
                        {
                            if(getAccountInfo().isTaxSpecialWithhold(WEB3TPAccountTaxTypeKeyDef.TAX_TYPE_NEXT))
                            {
                                l_capitalGains[i].addAmount(l_row.getCapitalGain());                            
                                log.debug("���N���n���v�Ɍv��<�O��>=" + l_row);
                            }                           
                        }                   
                    }
                }                
            }
                
        }
        //���n���v�S�����𐶐�����B
        for (int i = WEB3TPSpecifiedPointDef.T_0; i <= l_intLastBizDateIndex; i++)
        {
            Date l_datBizDate = getCalcCondition().getBizDate(i);
            
            //���n�v�ŗ����擾����B
            double l_dblRate = l_dataMgr.getTaxRate(this,
                WEB3DutyTypeDef.CPITAL_GAIN_TAX, l_datBizDate);

            WEB3TPCapitalGainTaxRestraintReflector l_restRef =
                WEB3TPCapitalGainTaxRestraintReflector.create(getCalcCondition(),
                l_dblRate, l_datBizDate, l_accCapGain, l_capitalGains);
            addCapitalGainTaxRestraint(l_restRef);
            
            log.debug("�ǉ����ꂽ���n�v�ōS����=" + l_restRef);
        }

    }
    
    /**
     * (do�����M�����n�v�ōS�������[�h)<BR>
     * �����M�����n�v�ōS���������[�h����B<BR>
     * <BR>
     * �P�j�����M�������ꗗ����ΏۂƂȂ钍�����擾����B<BR>
     * �@@�����M�������������֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����M������<���n�v��><BR>
     * <BR>
     * �@@�ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR><BR>
     * <BR>
     * �Q�j�����M�����n�v�ōS�����𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR><BR>
     * �E�S����      ��  ���M����.���򒥎��S����<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��(���M����.��n��)<BR>
     *  �ϓ����f�J�n������n��<BR>
     *  �ϓ����f�I�������c�Ɠ�(T+5)<BR>
     * <BR>
     * �R�j�����M�����n�v�ōS�����ꗗ�ɐ������������M�����n�v�ōS������ǉ�����B<BR>
     */
    private void loadMutualFundCapitalGainTaxRestraints()
    {
        //�����M�������ꗗ���擾
        List l_mutualFundOrders = this.getTransactionAmount().getMutualFundTransactions();
        
        //���n�v�ōS�������܂ޒ������擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getMutualFundOrdersCapitalGainTax(l_mutualFundOrders, this);

        for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_iter.next();

            WEB3TPMutualFundCapitalGainTaxRestraintReflector l_ref = WEB3TPMutualFundCapitalGainTaxRestraintReflector.create(
                getCalcCondition(), l_row.getWithholdingTaxRestriction(), l_row.getDeliveryDate());

            addMutualFundCapitalGainTaxRestraint(l_ref);
        }
    }
    
    /**
     * (do�����M���O��S�������[�h)<BR>
     * �����M���O��S���������[�h����B<BR>
     * <BR>
     * �P�j�����M���O��S���Ȃ��̏ꍇ�A���\�b�h���I������B�S������̏ꍇ�A�ȍ~�̏����ɐi�ށB<BR>
     * <BR>
     * �Q�j�����M�������ꗗ����ΏۂƂȂ钍�����擾����B<BR>
     * �@@�����M�������������֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����M���O��S��<�����><BR>
     * <BR>
     * �@@���L �R�j�A�S�j�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR><BR>
     * <BR>
     * �R�j�����M���O��S�����𐶐�����B<BR>
     * <BR>
     * �S�j�����M���O��S�����ꗗ�ɐ������������M���O��S������ǉ�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * ������ʁ����M�抷�̏ꍇ<BR>
     * �E�S����      ��  ���M����.���򒥎��S����<BR>
     * ������ʁ����M�抷�ȊO�̏ꍇ<BR>
     * �E�S����      ��  ���M����.�T�Z��n���<BR>
     * ������ʁ����M��W�̏ꍇ<BR>
     * �E��n��      ��  ���M����.������<BR>
     * ������ʁ����M��W�ȊO�̏ꍇ<BR>
     * �E��n��      ��  ���M����.��n��<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��(��n��)<BR>
     *  �ϓ����f�J�n����������<BR>
     *  �ϓ����f�I��������n���O��<BR>
     * <BR>
     * �T�j�m��g�����U�N�V�����e�[�u������ΏۂƂȂ钍�����擾����B<BR>
     * �@@�����M�������������֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����M���O��S��<����><BR>
     * <BR>
     * �@@���L �U�j�A�V�j�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR><BR>
     * <BR>
     * �U�j�����M���O��S�����𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * �E�S����      ��  ABS(�m��g�����U�N�V����.��n���)<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f��(��n��)<BR>
     *  �ϓ����f�J�n�����c�Ɠ�(T+0)<BR>
     *  �ϓ����f�I��������n���O��<BR>
     * <BR>
     * �V�j�����M���O��S�����ꗗ�ɐ������������M���O��S������ǉ�����B<BR>
     */
    private void loadMutualFundAdvanceRestraints()
    {
        //�]�͌v�Z�������擾
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();

        //�����M���O��S���敪���擾
        String l_strMutualFundAdvanceRestraintDiv = l_calcCondition.getInstBranCalcCondition(WEB3TPCalcCondition.MUTUAL_FUND_ADVANCE_RESTRAINT);
        
        //�S���Ȃ��̏ꍇ�A���\�b�h�I��
        if(! WEB3TPMutualFundAdvanceRestraintDef.FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE.equals(l_strMutualFundAdvanceRestraintDiv))
        {
            return;
        }

        //�����M�������ꗗ���擾
        List l_mutualFundOrders = this.getTransactionAmount().getMutualFundTransactions();
        
        //�����M���O��S�������܂ޒ������擾
        List l_unExecutedRows = WEB3TPPersistentDataManager.getInstance().getMutualFundAdvanceRestraintUnExecuted(l_mutualFundOrders, this);

        for (Iterator l_iter = l_unExecutedRows.iterator(); l_iter.hasNext(); )
        {
            MutualFundOrderUnitRow l_row = (MutualFundOrderUnitRow) l_iter.next();
            
            //�S����
            double l_dblAmount = 0;
            
            //�抷�̏ꍇ
            if(OrderTypeEnum.MF_SWITCHING.equals(l_row.getOrderType()))
            {
                l_dblAmount = l_row.getWithholdingTaxRestriction();
            }
            //�抷�ȊO�̏ꍇ
            else
            {
                l_dblAmount = l_row.getEstimatedPrice();
            }
            
            //��n��
            Date l_datDeliveryDate = null;
            
            //��W�̏ꍇ
            if(OrderTypeEnum.MF_RECRUIT.equals(l_row.getOrderType()))
            {
                l_datDeliveryDate = l_row.getPaymentDate();
            }
            //��W�ȊO�̏ꍇ
            else
            {
                l_datDeliveryDate = l_row.getDeliveryDate();
            }
            
            //������
            Date l_datBizDate = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            
            WEB3TPMutualFundAdvanceRestraintReflector l_ref = WEB3TPMutualFundAdvanceRestraintReflector.create(
                getCalcCondition(), l_dblAmount, l_datBizDate, l_datDeliveryDate);

            addMutualFundAdvanceRestraint(l_ref);
        }
        
        
        //���σf�[�^���瓊���M���O��S�������܂ޒ������擾
        List l_executedRows = WEB3TPPersistentDataManager.getInstance().getMutualFundAdvanceRestraintFixed(this);
        
        for (Iterator l_iter = l_executedRows.iterator(); l_iter.hasNext(); )
        {
            FixedFinTransactionRow l_row = (FixedFinTransactionRow) l_iter.next();
            
            //�S����
            double l_dblAmount = l_row.getNetAmount() * -1;
            
            //���ςȂ̂ŁA���������c�Ɠ�(T+0)�Ƃ���
            Date l_datBizDate = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

            WEB3TPMutualFundAdvanceRestraintReflector l_ref = WEB3TPMutualFundAdvanceRestraintReflector.create(
                getCalcCondition(), l_dblAmount, l_datBizDate, l_row.getDeliveryDate());

            addMutualFundAdvanceRestraint(l_ref);
        }
    }
    
    /**
     * (do�a����S�ۏo���S�������[�h)<BR> 
     * <BR>
     * �a����S�ۏo���S�����Ɋ֘A����f�[�^�����[�h����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(�S����)do�a����S�ۏo���S�������[�h�v�Q��<BR> 
     */
    private void loadCashDepositRestraints()
    {
        final String STR_METHOD_NAME = "loadCashDepositRestraints()";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get�ڋq����()
        WEB3TPAccountInfo l_accountInfo = getAccountInfo();
        
        //1.2.get����ID()
        long l_lngAccountId = l_accountInfo.getAccountId();
        
        try
        {
            //1.3.(*)�S�ەs���ڋq�f�[�^�e�[�u�����A���Y�ڋq�̃��R�[�h���擾����B
            //[�Ώۃe�[�u��]
            // �S�ەs���ڋq�f�[�^�e�[�u��
            //[��������]
            // ����ID = �ڋq����.get����()
            SecurityShortageAccountRow l_securityShortageAccountRow = 
                (SecurityShortageAccountRow)SecurityShortageAccountDao.findRowByAccountId(l_lngAccountId);
       
            //1.4.(*)����t���[
            //�S�ەs���ڋq�f�[�^�e�[�u���̍s�I�u�W�F�N�g���擾�ł������ꕔ�o����~�̏ꍇ
            //(�S�ەs���ڋq�f�[�^Row != null 
            // &&
            //�@@�S�ەs���ڋq�f�[�^Row.�o����~�敪 == 2:�ꕔ)
            //���S�ەs���ڋq�f�[�^Row = �S�ەs���ڋq�f�[�^�e�[�u���̌������ʍs�I�u�W�F�N�g
            if (l_securityShortageAccountRow != null && WEB3PaymentStopDivDef.PART.equals(
                l_securityShortageAccountRow.getPaymentStopDiv()))
            {
                //1.4.1.create�a����S�ۏo���S����(�]�͌v�Z����, double, Date)
                //�X�g�b�N�I�v�V�������t����S�����I�u�W�F�N�g�𐶐�����B 
                //[����] 
                //�]�͌v�Z���� = this.get�]�͌v�Z����() 
                //double = �S�ەs���ڋq�f�[�^Row.�o����~�z 
                //Date = �S�ەs���ڋq�f�[�^Row.������ (*)Date�^�ɕϊ�����B 
                String l_strPaymentStopAmount = null;
                if (l_securityShortageAccountRow.getPaymentStopAmount() == null)
                {
                    l_strPaymentStopAmount = "0";
                }
                else
                {
                    l_strPaymentStopAmount = 
                        l_securityShortageAccountRow.getPaymentStopAmount();
                }
                WEB3TPCashDepositRestraintReflector l_reflector = 
                    WEB3TPCashDepositRestraintReflector.createCashDepositRestraint(
                        this.getCalcCondition(), 
                        Double.parseDouble(l_strPaymentStopAmount),
                        WEB3DateUtility.getDate(
                            l_securityShortageAccountRow.getProcDate(), "yyyyMMdd"));
                
                //1.4.2.add�a����S�ۏo���S����(�a����S�ۏo���S����)
                //���������a����S�ۏo���S�����I�u�W�F�N�g�� 
                //�a����S�ۏo���S�����ꗗ�ɒǉ�����B 
                //[����] 
                //�a����S�ۏo���S���� = create�a����S�ۏo���S����()�̖߂�l
                this.addCashDepositRestraint(l_reflector);
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (do�X�g�b�N�I�v�V�������t����S�������[�h)<BR> 
     * <BR>
     * �X�g�b�N�I�v�V�������t����S�����Ɋ֘A����f�[�^�����[�h����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(�S����)do�X�g�b�N�I�v�V�������t����S�������[�h�v�Q�� <BR>
     */
    private void loadStockOptionSellAmountRestraints()
    {
        final String STR_METHOD_NAME = "loadStockOptionSellAmountRestraints()";
        log.entering(STR_METHOD_NAME);
        
        List l_lisAllTransactions = new ArrayList();
        // 1.1.get������ꗗ<����>( )
        //������ꗗ<����>���擾
        List l_lisTodaysTransactions = this.transactionAmount.getTodaysTransactions();
        
        // 1.2.addAll(Collection)
        // �擾����������ꗗ<����>�����X�g�ɒǉ�����B 
        // [����] 
        // Collection = get������ꗗ<����>()�̖߂�l
        l_lisAllTransactions.addAll(l_lisTodaysTransactions);
        
        // 1.3.get������ꗗ<�m��>()
        // ������ꗗ<�m��>���擾����B 
        List l_lisFixedTransactions = this.transactionAmount.getFixedTransactions();
        
        // 1.4.addAll(Collection)
        // �擾����������ꗗ<�m��>�����X�g�ɒǉ�����B 
        // [����] 
        // Collection = get������ꗗ<�m��>()�̖߂�l
        l_lisAllTransactions.addAll(l_lisFixedTransactions);
        
        // 1.5.get�c�Ɠ�(int)
        // �c�Ɠ�(T-1)���擾����B 
        // [����] 
        // int = -1
        Date l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
        
        // 1.6.��LOOP�����F"������ꗗ"�̗v�f����
        for (Iterator l_iter = l_lisAllTransactions.iterator(); l_iter.hasNext();)
        {
            //1.6.1(*)"������ꗗ"���v�f�I�u�W�F�N�g(=������)���擾����
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector)l_iter.next();
            
            // 1.6.2. get�g�����U�N�V�����^�C�v()
            FinTransactionType l_finTransactionType = l_tranRef.getFinTransactionType();
            
            // 1.6.3.get�ŋ敪()
            TaxTypeEnum l_taxTypeEnum = l_tranRef.getTaxType();
            
            // 1.6.4.get�g�����U�N�V����������()
            Date l_datFinTransactionDate = l_tranRef.getFinTransactionDate();
            
            // 1.6.5.(*)����t���[
            // �X�g�b�N�I�v�V�����c���̌������t�@@���� �g�����U�N�V������������T-1�ȍ~�̏ꍇ
            // ("�g�����U�N�V�����^�C�v" = 80:��������� && 
            //"�ŋ敪" = 5:�X�g�b�N�I�v�V���� && "�g�����U�N�V����������" >= "T-1")
            // ��"�g�����U�N�V�����^�C�v" = get�g�����U�N�V�����^�C�v()�̖߂�l
            // ��"�ŋ敪" = get�ŋ敪()�̖߂�l
            // ��"�g�����U�N�V����������" = get�g�����U�N�V����������()�̖߂�l
            // ��"T-1" = get�c�Ɠ�()�̖߂�l
            if (FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_finTransactionType) 
                && TaxTypeEnum.STOCK_OPTION.equals(l_taxTypeEnum) 
                && WEB3DateUtility.compareToDay(l_datFinTransactionDate, l_datBizDate) >= 0)
            {
                // 1.6.5.1.create�X�g�b�N�I�v�V�������t����S����
                // �X�g�b�N�I�v�V�������t����S�����I�u�W�F�N�g�𐶐�����B 
                // [����] 
                // �]�͌v�Z���� = this.get�]�͌v�Z����() 
                // double = ������.get��n���() 
                // Date = ������.get��n��() 
                WEB3TPStockOptionSellAmountRestraintReflector l_reflector = 
                    WEB3TPStockOptionSellAmountRestraintReflector.createStockOptionSellAmountRestraintReflector(
                        this.getCalcCondition(), 
                        l_tranRef.getAmount(),
                        l_tranRef.getDeliveryDate());
               
                // 1.6.5.2. add�X�g�b�N�I�v�V�������t����S����
                // ���������X�g�b�N�I�v�V�������t����S�����I�u�W�F�N�g�� 
                // �X�g�b�N�I�v�V�������t����S�����ꗗ�ɒǉ�����B
                // [����] 
                // �X�g�b�N�I�v�V�������t����S���� = create�X�g�b�N�I�v�V�������t����S����()�̖߂�l
                this.addStockOptionSellAmountRestraint(l_reflector);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (do���S�������[�h)<BR>
     * do���S�������[�h<BR>
     * <BR>
     * ���S�����Ɋ֘A����f�[�^�����[�h����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(�S����)do���S�������[�h�v�Q��<BR>
     */
    private void loadTempRestraints()
    {
        final String STR_METHOD_NAME = "loadTempRestraints()";
        log.entering(STR_METHOD_NAME);

        //get�ڋq����()
        WEB3TPAccountInfo l_accountInfo = this.getAccountInfo();

        //get����ID()
        long l_lngAccountId = l_accountInfo.getAccountId();

        WEB3TPPersistentDataManager l_persistentDataManager =
            WEB3TPPersistentDataManager.getInstance();
        //get���̑��S�����i���S���j(Long)
        //[����]
        //����ID = �ڋq����.get����ID()�̖߂�l
        List l_lisTpOtherTempRestraints =
            l_persistentDataManager.getTempRestraint(new Long(l_lngAccountId));

        //get�]�͌v�Z����
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();

        //LOOP�����F�擾�������̑��S����(���S��)Row�̌�����
        int l_intTpOtherTempRestraint = l_lisTpOtherTempRestraints.size();

        for (int i = 0; i < l_intTpOtherTempRestraint; i++)
        {
            //���̑��S����(���S��)Row
            TpOtherTempRestraintRow l_tpOtherTempRestraintRow =
                (TpOtherTempRestraintRow)l_lisTpOtherTempRestraints.get(i);

            //create���S����(�]�͌v�Z����, double, Date, Date, String)
            //[����]
            // �]�͌v�Z���� = get�]�͌v�Z����()�̖߂�l
            // �S����= ���̑��S����(���S��)Row.�S����
            // �g�����U�N�V���������� = ���̑��S����(���S��)Row.�g�����U�N�V����������
            // ��n�� = ���̑��S����(���S��)Row.��n��
            // �S������� = ���̑��S����(���S��)Row.�S�������
            WEB3TPTempRestraint l_tempRestraint = WEB3TPTempRestraint.createTempRestraint(
                l_calcCondition,
                l_tpOtherTempRestraintRow.getAmount(),
                l_tpOtherTempRestraintRow.getTransactionDate(),
                l_tpOtherTempRestraintRow.getDeliveryDate(),
                l_tpOtherTempRestraintRow.getRestraintDiv());

            //add���S����(���S����)
            //this.���S�����ꗗ�ɁA����.���S������ǉ�����B
            //���S���� = create���S����()�̖߂�l
            this.addTempRestraint(l_tempRestraint);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (add�������������S����)<BR>
     * �������������S�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param todayFundDepRestraint -
     * (�������������S����)
     * @@roseuid 40ECBA3B002E
     */
    protected void addTodayFundDepRestraint(WEB3TPTodayDepFundRestraintReflector
                                            l_todayFundDepRestraint)
    {        
        if (l_todayFundDepRestraint instanceof WEB3TPTodayDepFundRestraintReflector)
        {
            todayDepFundRestraints.add(l_todayFundDepRestraint);
        }
    }

    /**
     * (add�L���T�[�r�X���p�S����)<BR>
     * �L���T�[�r�X���p�S�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param todayFundDepRestraint -
     * (�L���T�[�r�X���p�S����)
     */
    protected void addServiceChargeRestraint(WEB3TPServiceChargeRestraintReflector
                                            l_serviceChargeRestraint)
    {        
        if (l_serviceChargeRestraint instanceof WEB3TPServiceChargeRestraintReflector)
        {
            serviceChargeRestraints.add(l_serviceChargeRestraint);
        }
    }
    
    /**
     * (addIPO�S����)<BR>
     * IPO�S�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param ipoRestraint IPO�S����
     * @@roseuid 40ECBA0901F3
     */
    protected void addIPORestraint(WEB3TPIPORestraintReflector l_ipoRestraint)
    {
        if (l_ipoRestraint instanceof WEB3TPIPORestraintReflector)
        {
            ipoRestraints.add(l_ipoRestraint);
        }
    }

    /**
     * (add�����S����)<BR>
     * �����S�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param dayInterestRestraint - (�����S����)
     * @@roseuid 40ECBA2A008C
     */
    protected void addDayInterestAdjustmentRestraint(
        WEB3TPDayInterestAdjustmentRestraintReflector l_dayInterestRestraint)
    {
        if (l_dayInterestRestraint instanceof
            WEB3TPDayInterestAdjustmentRestraintReflector)
        {
            dayIntAdjustRestraints.add(l_dayInterestRestraint);
        }
    }

    /**
     * (add�萔����������S����)<BR>
     * �萔����������S�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param consolidatedCommissionAdjustmentRestraint - (�萔����������S����)
     * @@roseuid 40ECBA21030C
     */
    protected void addConsolidatedCommissionAdjustmentRestraint(
        WEB3TPConsolidatedCommissionAdjustmentRestraintReflector
        l_consolidatedCommissionAdjustmentRestraint)
    {
        if (l_consolidatedCommissionAdjustmentRestraint instanceof
            WEB3TPConsolidatedCommissionAdjustmentRestraintReflector)
        {
            conCommAdjustRestraints.add(l_consolidatedCommissionAdjustmentRestraint);
        }

    }

    /**
     * (add���n�v�ōS����)<BR>
     * ���n�v�ōS�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param capitalGainTaxRestraint - (���n�v�ōS����)
     * @@roseuid 40ECBA3101E3
     */
    protected void addCapitalGainTaxRestraint(WEB3TPCapitalGainTaxRestraintReflector
                                              l_capitalGainTaxRestraint)
    {
        if (l_capitalGainTaxRestraint instanceof WEB3TPCapitalGainTaxRestraintReflector)
        {
            capitalGainTaxRestraints.add(l_capitalGainTaxRestraint);
        }

    }

    /**
     * (add�����M�����n�v�ōS����)<BR>
     * �����M�����n�v�ōS�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param l_mutualFundCapitalGainTaxRestraint - (�����M�����n�v�ōS����)
     */
    protected void addMutualFundCapitalGainTaxRestraint(WEB3TPMutualFundCapitalGainTaxRestraintReflector
                                              l_mutualFundCapitalGainTaxRestraint)
    {
        mutualFundCapitalGainTaxRestraints.add(l_mutualFundCapitalGainTaxRestraint);
    }

    /**
     * (add�����M���O��S����)<BR>
     * �����M���O��S�����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param l_mutualFundAdvanceRestraint - (�����M���O��S����)
     */
    protected void addMutualFundAdvanceRestraint(WEB3TPMutualFundAdvanceRestraintReflector
                                              l_mutualFundAdvanceRestraint)
    {
        mutualFundAdvanceRestraints.add(l_mutualFundAdvanceRestraint);
    }
    
    /**
     * (add�a����S�ۏo���S����) <BR>
     * <BR>
     * this.�a����S�ۏo���S�����ꗗ�ɁA����.�a����S�ۏo���S������ǉ�����B<BR> 
     * <BR>
     * �|this.�a����S�ۏo���S�����ꗗ.add(:Object = ����.�a����S�ۏo���S����)���R�[�� <BR>
     * @@param l_cashDepositRestraint - (�a����S�ۏo���S����)
     */
    protected void addCashDepositRestraint(
        WEB3TPCashDepositRestraintReflector 
        l_cashDepositRestraint)
    {
        cashDepositRestraints.add(l_cashDepositRestraint);
    }
    
    /**
     * (add�X�g�b�N�I�v�V�������t����S����) <BR>
     * <BR>
     * this.�X�g�b�N�I�v�V�������t����S�����ꗗ�ɁA<BR>
     * ����.�X�g�b�N�I�v�V�������t����S������ǉ�����B<BR> 
     * <BR>
     * �|this.�X�g�b�N�I�v�V�������t����S�����ꗗ.add<BR>
     * (:Object = ����.�X�g�b�N�I�v�V�������t����S����)���R�[�� <BR>
     * <BR>
     * @@param l_stockOptionSellAmountRestraint - (�X�g�b�N�I�v�V�������t����S����)
     */
    protected void addStockOptionSellAmountRestraint(
        WEB3TPStockOptionSellAmountRestraintReflector l_stockOptionSellAmountRestraint)
    {
        this.stockOptionSellAmountRestraints.add(l_stockOptionSellAmountRestraint);
    }

    /**
     * (add���S����)<BR>
     * add���S����<BR>
     * <BR>
     * this.���S�����ꗗ�ɁA����.���S������ǉ�����B<BR>
     * �|this.���S�����ꗗ.add(:Object = ����.���S����)���R�[��<BR>
     * @@param l_tempRestraint - (���S����)<BR>
     * ���S����<BR>
     */
    protected void addTempRestraint(WEB3TPTempRestraint l_tempRestraint)
    {
        this.tempRestraints.add(l_tempRestraint);
    }

    /**
     * (get������)<BR>
     * ���������擾����<BR>
     *
     * @@return WEB3TPTransactionAmount
     */
    public WEB3TPTransactionAmount getTransactionAmount()
    {
        return transactionAmount;
    }
    
    /**
     * (set������)<BR>
     * �������������ɃZ�b�g����<BR>
     *
     * @@param WEB3TPTransactionAmount ������
     */
    public void setTransactionAmount(WEB3TPTransactionAmount l_transactionAmount)
    {
        transactionAmount = l_transactionAmount;
    }



    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("todayDepFundRestraints", getTodayDepFundRestraints())
            .append("ipoRestraints", getIPORestraints())
            .append("dayIntAdjustRestraints", getDayInterestAjdustmentRestraints())
            .append("conCommAdjustRestraints", getConsolidatedCommissionAdjustmentRestraints())
            .append("capitalGainTaxRestraints", getCapitalGainTaxRestraints())
            .append("mutualFundCapitalGainTaxRestraints", getMutualFundCapitalGainTaxRestraints())
            .append("mutualFundAdvanceRestraints", getMutualFundAdvanceRestraints())
            .append("stockOptionSellAmountRestraints", getStockOptionSellAmountRestraints())
            .append("cashDepositRestraints", getCashDepositRestraints())
            .appendSuper(super.toString())
            .toString();
    }        
    
    /**
     * (get�N�ύX��)<BR>
     * <BR>
     * �]�͌v�Z�͈͓��ŔN���܂����ꍇ�A�܂����c�Ɠ��̃C���f�b�N�X��Ԃ��B<BR>
     * �܂����Ȃ���΁A6��Ԃ��B<BR>
     * <BR>
     * 1)�c�Ɠ�(T+0)�A�c�Ɠ�(T+5)���擾����B<BR>
     * <BR>
     * �@@�c�Ɠ�(T+0) = this.get�v�Z����().get�c�Ɠ�(�w���:int = 0:T+0)<BR>
     * �@@�c�Ɠ�(T+5) = this.get�v�Z����().get�c�Ɠ�(�w���:int = 5:T+5)<BR>
     * <BR>
     * 2)�N�ύX�����v�Z����B<BR>
     * <BR>
     * �@@[a.�]�͌v�Z�͈͓�(T+0�`T+5)�ŔN���܂��� �ꍇ]<BR>
     * �@@(�c�Ɠ�(T+0)�̔N���� <  �c�Ɠ�(T+5)�̔N����)<BR>
     * <BR>
     * �@@�@@��LOOP�����F�]�͌v�Z�͈͓�(T+1�`T+5) �̊ԁ�<BR>
     * �@@�@@�@@�c�Ɠ�(index) =  this.get�v�Z����().get�c�Ɠ�(�w���:int = index)<BR>
     * <BR>
     * �@@�@@�@@[b.�c�Ɠ�(index)���N���܂��� �ꍇ]<BR>
     * �@@�@@�@@(�c�Ɠ�(T+0)�̔N���� <  �c�Ɠ�(index)�̔N����)<BR>
     * <BR>
     * �@@�@@�@@�@@�N�ύX�� = index<BR>
     * <BR>
     * �@@[a.�ȊO�i�]�͌v�Z�͈͓�(T+0�`T+5)�ŔN���܂����Ȃ� �ꍇ]<BR>
     * <BR>
     * �@@�@@�N�ύX�� = 6<BR>
     * <BR>
     * 3)�v�Z�����N�ύX����ԋp����B<BR>
     * <BR>
     * @@return int 
     */
    private int getNextYearBizDateIndex()
    {
        int nextYearBizDateIndex = WEB3TPSpecifiedPointDef.T_5 + 1;
        Date l_datT0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        int l_intLastBizDateIndex = WEB3TPSpecifiedPointDef.T_0 + WEB3TPSpecifiedPointDef.TP_CALC_RANGE;        

        //�]�͌v�Z�͈͓��ŔN���܂����ꍇ
        if(WEB3DateUtility.compareToYear(l_datT0, getCalcCondition().getBizDate(l_intLastBizDateIndex)) < 0)
        {
            //�]�͌v�Z�͈͓��ŔN���܂����ꍇ�A�ǂ��ł܂������`�F�b�N
            for(int i = WEB3TPSpecifiedPointDef.T_0 + 1; i <= WEB3TPSpecifiedPointDef.T_0 + WEB3TPSpecifiedPointDef.TP_CALC_RANGE; i++)
            {   
                Date l_compBizDate = getCalcCondition().getBizDate(i);              
                if(WEB3DateUtility.compareToYear(l_datT0, l_compBizDate) < 0)
                {           
                    return i;
                }
            }
            
        }
        
        //�]�͌v�Z�͈͓��œ��N�̏ꍇ
        //�قƂ�ǂ̏ꍇ���̃P�[�X
        return nextYearBizDateIndex;

    }
    
    /**
     * (calc���S����)<BR>
     * calc���S����<BR>
     * <BR>
     * ����.�w����ɂ����鉼�S�������W�v���A���ʂ�ԋp����B<BR>
     * <BR>
     * �P�j���S���ꗗ���擾����B<BR>
     * <BR>
     * �@@�P-�P�j����.�S������ʈꗗ !=null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@����.���S�����ꗗ���牼�S�����I�u�W�F�N�g�𒊏o����B<BR>
     * <BR>
     * �@@�@@�@@���S�����ꗗ = this.get���S�����ꗗ()<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@List = this.���S�����ꗗ<BR>
     * �@@�@@�@@�@@String[] = ����.�S������ʈꗗ<BR>
     * <BR>
     * �@@�P-�Q�j���̑��̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���S�����ꗗ = this.���S�����ꗗ<BR>
     * <BR>
     * �Q�j����.�w����ɂ����鉼�S�������W�v����B<BR>
     * <BR>
     * �@@���S���� = this.calc�S�������v()<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@List = �P�j�Ŏ擾�������S�����ꗗ<BR>
     * �@@�@@Date = ����.�w���<BR>
     * <BR>
     * �R�j�W�v�������S������ԋp����B<BR>
     * <BR>
     * �@@�ԋp�l�F���S����<BR>
     * <BR>
     * @@param l_datDate - (�w���)<BR>
     * �w���<BR>
     * @@param l_restraintDivList - (�S������ʈꗗ)<BR>
     * �S������ʈꗗ<BR>
     * @@return double
     */
    public double calcTempRestraint(Date l_datDate, String[] l_restraintDivList)
    {
        final String STR_METHOD_NAME = "calcTempRestraint(Date, String[])";
        log.entering(STR_METHOD_NAME);

        //���S���ꗗ���擾����
        List l_lisTempRestraints = null;

        //����.�S������ʈꗗ !=null �̏ꍇ
        if (l_restraintDivList != null)
        {
            //���S�����ꗗ = this.get���S�����ꗗ()
            l_lisTempRestraints = this.getTempRestraintList(this.tempRestraints, l_restraintDivList);
        }
        //���̑��̏ꍇ
        else
        {
            //���S�����ꗗ = this.���S�����ꗗ
            l_lisTempRestraints = this.tempRestraints;
        }

        //����.�w����ɂ����鉼�S�������W�v����
        //���S���� = this.calc�S�������v()
        //�W�v�������S������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return this.calcRestraintsTotal(l_lisTempRestraints, l_datDate);
    }

    /**
     * (get�ڋq����c�����f�ΏۊO�S������ʈꗗ)<BR>
     * get�ڋq����c�����f�ΏۊO�S������ʈꗗ<BR>
     * <BR>
     * �ڋq����c�����f�ΏۊO�̍S������ʈꗗ�̔z����쐬���ԋp����B<BR>
     * <BR>
     * �ȉ��̒l�ŁAString�z������������ԋp����B<BR>
     * <BR>
     * �@@1�F���M�抷���p���<BR>
     * <BR>
     * @@return String[]
     */
    public String[] getAccountCashBalanceReflectObjectRestraintDivList()
    {
        return new String[]{WEB3RestraintDivDef.MF_SWITCHING_SELL_AMOUNT};
    }

    /**
     * (get���S�����ꗗ)<BR>
     * get���S�����ꗗ<BR>
     * <BR>
     * ����.���S�����ꗗ���牼�S�����I�u�W�F�N�g�𒊏o���ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@����.�S������ʈꗗ�Ɋ܂܂��S������� == ���S����.get�S�������() <BR>
     * <BR>
     * �P�j�߂�l�̉��S���ꗗ(List)�𐶐�����B<BR>
     * <BR>
     * �Q�j���S�����ꗗ���쐬����B<BR>
     * <BR>
     * �@@��LOOP�����F����.���S�����ꗗ�̗v�f���� <BR>
     * <BR>
     * �@@�@@���S�����I�u�W�F�N�g = ����.���S�����ꗗ.get(:int = LOOP��Index����) <BR>
     * <BR>
     * �@@�@@[a.����.�S������ʈꗗ�Ɋ܂܂��S������ʂƁA<BR>
     * �@@�@@�@@���S�����I�u�W�F�N�g�̍S������ʂ���v����ꍇ]<BR>
     * �@@�@@�@@(����.�S������ʈꗗ�Ɋ܂܂��S������� == ���S����.get�S�������())<BR>
     * <BR>
     * �@@�@@�@@���S�����ꗗ�ɉ��S�����I�u�W�F�N�g��ǉ�����B<BR>
     * <BR>
     * �R�j�쐬�������S�����ꗗ��ԋp����B<BR>
     * <BR>
     * �@@�ԋp�l�F���S�����ꗗ<BR>
     * <BR>
     * @@param l_lisTempRestraints - (���S�����ꗗ)<BR>
     * ���S�����ꗗ<BR>
     * @@param l_restraintDivs - (�S������ʈꗗ)<BR>
     * �S������ʈꗗ<BR>
     * @@return List
     */
    public List getTempRestraintList(List l_lisTempRestraints, String[] l_restraintDivs)
    {
        final String STR_METHOD_NAME = "getTempRestraintList(List, String[])";
        log.entering(STR_METHOD_NAME);

        //�߂�l�̉��S���ꗗ(List)�𐶐�����B
        List l_lisTempRestraintList = new ArrayList();
        int l_intTempRestraintSize = l_lisTempRestraints.size();

        //���S�����ꗗ���쐬����B
        for (int i = 0; i < l_intTempRestraintSize; i++)
        {
            //���S�����I�u�W�F�N�g = ����.���S�����ꗗ.get(:int = LOOP��Index����)
            WEB3TPTempRestraint l_tempRestraint = (WEB3TPTempRestraint)l_lisTempRestraints.get(i);
            //����.�S������ʈꗗ�Ɋ܂܂��S������� == ���S����.get�S�������()
            boolean l_blnContain =
                WEB3Toolkit.contain(l_restraintDivs, l_tempRestraint.getRestraintDiv());
            //���S�����ꗗ�ɉ��S�����I�u�W�F�N�g��ǉ�����B
            if (l_blnContain)
            {
                l_lisTempRestraintList.add(l_tempRestraint);
            }
        }

        //�쐬�������S�����ꗗ��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisTempRestraintList;
    }
}
@
