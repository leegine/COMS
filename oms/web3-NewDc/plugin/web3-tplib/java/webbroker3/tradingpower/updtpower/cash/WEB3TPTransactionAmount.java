head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionAmount.java;


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
                    2006/09/12 ���G�� (���u) ���f��No.021�`028
                    2006/09/25 ���G�� (���u) ���f��No.054�`057
                    2007/04/10 �{�{�ē�(SCS) ���f��No.101
 Revision History : 2007/07/25 �Ј���(���u) ���f��No.133 No.135
 Revision History : 2009/12/15 �����F ���f��No.412 No.413 No.416 No.431 No.434
 Revision History : 2010/01/11 ���g�@@  (���u)���f��No.418,446
 */

package webbroker3.tradingpower.updtpower.cash;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3IpoOfferTradingpowerCheckDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.define.WEB3TPIPOHostAcceptStatusDef;
import webbroker3.tradingpower.define.WEB3TPPaymentApplicationDivDef;
import webbroker3.tradingpower.define.WEB3TPServiceChargeRestraintDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (������)<BR>
 * �������̐��ڂ�\������B
 */
public class WEB3TPTransactionAmount extends WEB3TPAssetValuation
{
    
    /**
     * (������ꗗ<����>)<BR>
     */
    private List todaysTransactions;
    
    /**
     * (������ꗗ<�m��>)<BR>
     */
    private List fixedTransactions;
    
    /**
     * (�����M�������ꗗ)<BR>
     */
    private List mutualFundTransactions;

    /**
     * (�o�������ꗗ<�]�͌v�Z���ʏڍחp>)<BR>
     */
    private List displayCashOutTransactions;
    
    /**
     * (�����Ώۃg�����U�N�V�����^�C�v)<BR>
     */
    private static final FinTransactionType[] EQUITY_TRANSACTION_TYPES =
    {
            FinTransactionType.EQTYPE_EQUITY_BUY,
            FinTransactionType.EQTYPE_EQUITY_SELL,
            FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG,
            FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT,
            FinTransactionType.EQTYPE_SWAP_MARGIN_LONG,
            FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT,
    };
    
    /** 
     * ���O�@@���[�e�B���e�B�@@
     */
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPTransactionAmount.class);
    
    /**
     * @@roseuid 4104B2440114
     */
    public WEB3TPTransactionAmount()
    {
        todaysTransactions = new ArrayList();
        fixedTransactions = new ArrayList();
        mutualFundTransactions = new ArrayList();
        displayCashOutTransactions = new ArrayList();
    }
    
    /**
     * (create������)<BR>
     * �������𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X�𐶐�<BR>
     * �Q�j�@@�l��ݒ�<BR>
     * �@@�ڋq��񁁈���.������.get�ڋq���()<BR>
     * �@@�v�Z����������.������.get�v�Z����()<BR>
     * �@@���������e������.������.get���������e()<BR> 
     * �R�j�@@�C���X�^���X��ԋp<BR>
     *
     * @@param l_valuation (������)
     * @@return WEB3TPTransactionAmount
     */
    public static WEB3TPTransactionAmount create(WEB3TPCashValuation l_valuation)
    {
        WEB3TPTransactionAmount l_instance = new WEB3TPTransactionAmount();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        return l_instance;
    }
    
    /**
     * (get������ꗗ<����>)<BR>
     * ������ꗗ<����>��Ԃ��B<BR>
     * @@return List
     * @@roseuid 40EDF0F203C7
     */
    public List getTodaysTransactions()
    {
        return todaysTransactions;
    }
    
    /**
     * (get������ꗗ<�m��>)<BR>
     * ������ꗗ<�m��>��Ԃ��B<BR>
     * @@return List
     * @@roseuid 40F7AFEF02C8
     */
    public List getFixedTransactions()
    {
        return fixedTransactions;
    }
    
    /**
     * (get�����M�������ꗗ)<BR>
     * �����M�������ꗗ��Ԃ��B<BR>
     * @@return List
     */
    public List getMutualFundTransactions()
    {
        return mutualFundTransactions;
    }
    
    /**
     * (get�o�������ꗗ<�]�͌v�Z���ʏڍחp>)<BR>
     * �o�������ꗗ<�]�͌v�Z���ʏڍחp>��Ԃ��B<BR>
     * @@return List
     */
    public List getDisplayCashOutTransactions()
    {
        return displayCashOutTransactions;
    }
    
    /**
     * (calc�����������)<BR>
     * �w����ɂ����関��������W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>���擾����B<BR>
     * �@@������ꗗ<����>�E�E�E����]��. get������ꗗ<����>()<BR>
     * <BR>
     * �Q�j�@@����������W�v����B<BR>
     * �@@�������(n) = ��(*)������.get�������()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * ������ꗗ<����>�ɂ��������<BR>
     * ���@@������.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@�������(n)��ԋp����B<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40E107E1004F
     */
    public double calcTodaysUnexecutedTotal(Date l_datDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            if (l_tranRef.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_tranRef.getUnexecutedAmount();
            }
        }
        
        //����
        //���t�\�z�v�Z���ɒl���}�C�i�X����̂ŁA�{-�𔽓]�����ĕԂ��B
        return (l_dblTotal == 0.0d) ? l_dblTotal : (l_dblTotal * -1); 
        
    }
    
    /**
     * (calc�������ϑ��)<BR>
     * <BR>
     * �w����ɂ�������ϑ�����W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>���擾����B<BR>
     * �@@������ꗗ<����>�E�E�E����]��. get������ꗗ<����>()<BR>
     * <BR>
     * �Q�j�@@���ϑ�����W�v����B<BR>
     * �@@���ϑ��(n) = ��(*)������.get���ϑ��()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * ������ꗗ<����>�ɂ��������<BR>
     * ���@@������.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@���ϑ��(n)��ԋp����B<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40E107EB0149
     */
    public double calcTodaysExecutedTotal(Date l_datDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            if (l_tranRef.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_tranRef.getExecutedAmount();
            }
        }
        
        //����
        //���t�\�z�v�Z���ɒl���}�C�i�X����̂ŁA�{-�𔽓]�����ĕԂ��B       
        return (l_dblTotal == 0.0d) ? l_dblTotal : (l_dblTotal * -1); 
    }
    
    /**
     * (calc�o���z)<BR>
     * <BR>
     * �w����ɂ�����o���z���W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>���擾����B<BR>
     * �@@������ꗗ<����>�E�E�E����]��.get������ꗗ<����>()<BR>
     * <BR>
     * �Q�j�@@���ϑ�����W�v����B<BR>
     * �@@���ϑ��(n) = ��(*)������.get���ϑ��()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * ������ꗗ<����>�ɂ��������<BR>
     * ���@@������.get�g�����U�N�V�����^�C�v=�o��<BR>
     * ���@@������.get��n��=����.�w���<BR>
     * <BR>
     * �R�j�@@���ϑ��(n)���Βl�ŕԋp����B<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     */
    public double calcCashOut(Date l_datDate)
    {
        double l_dblTotal = 0.0d;
        
        Date l_datDate0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);        
        
        for (Iterator l_iter = displayCashOutTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            //�g�����U�N�V�����^�C�v=�o��
            if(FinTransactionType.DEBIT.equals(l_tranRef.getFinTransactionType()))
            {
                //T+5�擾
                Date l_datDate5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
                
                //�w����Ɣ�r�����n��
                Date l_datTmpDelivery = null;
                
                //��n����T+6�ȍ~�̏ꍇ�AT+5�Ƃ݂Ȃ�
                if(WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDate5) > 0)
                {
                    l_datTmpDelivery = l_datDate5;
                }
                else
                {
                    l_datTmpDelivery = l_tranRef.getDeliveryDate();
                }
                
                //�w���=��n��
                if (WEB3DateUtility.compareToDay(l_datTmpDelivery, l_datDate) == 0)
                {
                    l_dblTotal += l_tranRef.getExecutedAmount(); 
                }
            }
        }
        return Math.abs(l_dblTotal); 
    }
    
    /**
     * (get�����v�f�W�v)<BR>
     * �w����ɂ���������v�f���W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * get�����v�f�W�v<����>(�w���) �{ get�����v�f�W�v<�m��>(�w���)�@@��ԋp�B<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40F4E6360196
     */
    public double getPlusTotal(Date l_datDate)
    {
        return getTodaysPlusTotal(l_datDate) + getFixedPlusTotal(l_datDate);
    }
    
    /**
     * (get�����v�f�W�v<����>)<BR>
     * �w����ɂ���������v�f<����>���W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>���擾�B<BR>
     * <BR>
     * �Q�j�@@������ꗗ<����>�̂����A������.get��n���z() > 0�̂��̂��W�v�B<BR>
     * <BR>
     * �R�j�@@�W�v���ʂ�ԋp�B<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 40F7A1150151
     */
    protected double getTodaysPlusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            
            //�ۏ؋�����a����ւ̎�����͌v�サ�Ȃ��B
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() > 0) &&
                   (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                      FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return l_dblTotal;
    }
    
    /**
     * (get�����v�f�W�v<�m��>)<BR>
     * �w����ɂ���������v�f<�m��>���W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<�m��>���擾�B<BR>
     * <BR>
     * �Q�j�@@������ꗗ<�m��>�̂����A������.get��n���z() > 0�̂��̂��W�v�B<BR>
     * <BR>
     * �R�j�@@�W�v���ʂ�ԋp�B<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 40F7A13C0141
     */
    public double getFixedPlusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = fixedTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            
            //�ۏ؋�����a����ւ̎�����͌v�サ�Ȃ��B
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() > 0) &&
                    (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                            FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return l_dblTotal;
    }
    
    /**
     * (get�o���v�f�W�v)<BR>
     * �w����ɂ�����o���v�f���W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * get�o���v�f�W�v<����>(�w���) �{ get�o���v�f�W�v<�m��>(�w���)�@@��ԋp�B<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40F4E6480138
     */
    public double getMinusTotal(Date l_datDate)
    {
        return getTodaysMinusTotal(l_datDate) + getFixedMinusTotal(l_datDate);
    }
    
    /**
     * (get�o���v�f�W�v<����>)<BR>
     * �w����ɂ�����o���v�f<����>���W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>���擾�B<BR>
     * <BR>
     * �Q�j�@@������ꗗ<����>�̂����A������.get��n���z() �� 0�̂��̂��W�v�B<BR>
     * <BR>
     * �R�j�@@�W�v���ʂ�ԋp�B<BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@return double
     * @@roseuid 40F7A14603A2
     */
    protected double getTodaysMinusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            //�a�������M�p�ۏ؋��ւ̎�����͌v�サ�Ȃ��B
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() < 0) &&
                    (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                            FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return Math.abs(l_dblTotal);
    }
    
    /**
     * (get�o���v�f�W�v<�m��>)<BR>
     * �w����ɂ�����o���v�f<�m��>���W�v�����ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<�m��>���擾�B<BR>
     * <BR>
     * �Q�j�@@������ꗗ<�m��>�̂����A������.get��n���z()�@@�� 0�̂��̂��W�v�B<BR>
     * <BR>
     * �R�j�@@�W�v���ʂ�ԋp�B<BR>
     * @@param l_datDeliveryDate - (��n��)
     * @@roseuid 40F7A15503E1
     */
    public double getFixedMinusTotal(Date l_datDeliveryDate)
    {
        double l_dblTotal = 0.0d;
        
        for (Iterator l_iter = fixedTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            //�a�������M�p�ۏ؋��ւ̎�����͌v�サ�Ȃ��B
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) && (l_tranRef.getAmount() < 0) &&
                    (!(FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_tranRef.getFinTransactionType()) ||  
                            FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_tranRef.getFinTransactionType()))))
            {
                l_dblTotal += l_tranRef.getAmount();
            }
        }
        return Math.abs(l_dblTotal);
    }
    
    /**
     * (get����������)<BR>
     * �w�������n���ł��銔��������̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>�A������ꗗ<�m��>����<BR>
     *�@@
     * �ȉ��̏����ɍ��������񂪑��݂���ꍇ���[�J���ϐ��iArrayList�j�ɕۑ�����<BR>
     * <BR>
     *     �E������.get�����^�C�v()���� OR �O��<BR>
     *     �E������.get�g�����U�N�V�����^�C�v()���g�����U�N�V�����^�C�v (*)<BR>
     *     �E������.get��n��������.��n��<BR>
     * <BR>
     *     (*)�����̔z��v�f<BR>
     *     �g�����U�N�V�����^�C�v��null�̏ꍇ<BR>
     *     �o70�F����������@@80�F���������<BR>
     *     110�F�����ԍώ���i���ԍρj�@@120�F�����ԍώ���i���ԍρj<BR>
     *     130�F��������@@140�F���n����p<BR>
     *     �������Ƃ���B<BR>
     * <BR>
     * �Q�j�@@���o���ۑ������������z��Ƃ��ĕԋp�B<BR>
     * <BR>
     * @@param transactionType - (�g�����U�N�V�����^�C�v)
     * @@param l_datDate - (��n��)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector[]
     * @@roseuid 40DBCFC3029F
     */
    public WEB3TPTransactionReflector[] getEquityTransactions(FinTransactionType[]
                                                                                 l_transactionTypes, Date l_datDeliveryDate)
    {
        
        if (l_transactionTypes == null)
        {
            l_transactionTypes = EQUITY_TRANSACTION_TYPES;
        }
        
        List l_allTransactions = new ArrayList();
        l_allTransactions.addAll(todaysTransactions);
        l_allTransactions.addAll(fixedTransactions);
        
        WEB3TPTransactionReflector[] l_transactionReflectorsEquity =
            searchTransactions(
                l_allTransactions, ProductTypeEnum.EQUITY,
                l_transactionTypes, l_datDeliveryDate);

        WEB3TPTransactionReflector[] l_transactionReflectorsFeq =
            searchTransactions(
                l_allTransactions, ProductTypeEnum.FOREIGN_EQUITY,
                l_transactionTypes, l_datDeliveryDate);

        List l_lisTransactionReflectors = new ArrayList();
        int l_intEquityLength = 0;
        if (l_transactionReflectorsEquity != null)
        {
            l_intEquityLength = l_transactionReflectorsEquity.length;
        }
        for (int i = 0; i < l_intEquityLength; i++)
        {
            l_lisTransactionReflectors.add(l_transactionReflectorsEquity[i]);
        }

        int l_intFeqLength = 0;
        if (l_transactionReflectorsFeq != null)
        {
            l_intFeqLength = l_transactionReflectorsFeq.length;
        }
        for (int i = 0; i < l_intFeqLength; i++)
        {
            l_lisTransactionReflectors.add(l_transactionReflectorsFeq[i]);
        }

        WEB3TPTransactionReflector[] l_transactionReflectors =
            new WEB3TPTransactionReflector[l_lisTransactionReflectors.size()];
        l_lisTransactionReflectors.toArray(l_transactionReflectors);
        return l_transactionReflectors;
    }
    
    /**
     * (get���������񌏐�<����>)<BR>
     * �w�������n���ł��铖���ȍ~���������񌏐���ԋp����B<BR>
     * �������������̖������܂܂Ȃ������Ƃ���B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>����A<BR>
     *     �ȉ��̏����Ŏ�����̌������J�E���g����B<BR>
     *     �E������.get�����^�C�v����<BR>
     *     �E������.get�g�����U�N�V�����^�C�v()�@@���@@�g�����U�N�V�����^�C�v (*1)<BR>
     *     �E������.is�ϓ����f���Ԓ�(�w���)<BR>
     * <BR>
     *     (*1)�����̔z��v�f<BR>
     *     �g�����U�N�V�����^�C�v��null�̏ꍇ<BR>
     *     �o70�F����������@@80�F���������<BR>
     *     110�F�����ԍώ���i���ԍρj�@@120�F�����ԍώ���i���ԍρj<BR>
     *     130�F��������@@140�F���n����p<BR>
     *     �������Ƃ���B<BR>
     * <BR>
     * �Q�j�@@�J�E���g����������ԋp<BR>
     * @@param l_transactionTypes �g�����U�N�V�����^�C�v
     * @@param l_datDeliveryDate - (��n��)
     * @@return int
     * @@roseuid 40DBC71A0349
     */
    public int getTodaysEquityTransactionsCount(FinTransactionType[] l_transactionTypes,
            Date l_datDeliveryDate)
    {
        if (l_transactionTypes == null)
        {
            l_transactionTypes = EQUITY_TRANSACTION_TYPES;
        }
        
        int l_intCount = 0;
        for (Iterator l_iter = todaysTransactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();          
            
            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDeliveryDate) == 0) &&
                    (ProductTypeEnum.EQUITY.equals(l_tranRef.getProductType())))
            {
                for (int j = 0; j < l_transactionTypes.length; j++)
                {
                    FinTransactionType l_tranType = l_tranRef.getFinTransactionType();
                    if (l_transactionTypes[j].equals(l_tranType))
                    {
                        //��������̏ꍇ�͖���肪�܂܂�Ă��邽�ߖ�肵�Ă�����̂������J�E���g����
                        if (FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_tranType))
                        {
                            if (l_tranRef.getExecutedQuantity() > 0)
                            {
                                l_intCount++;
                            }
                        }
                        else
                        {
                            l_intCount++;
                        }
                    }
                }
            }
        }
        return l_intCount;
        
    }
    
    /**
     * (get�~�j�������񌏐�<����>)<BR>
     * �w�������n���ł��铖���ȍ~���������񌏐���ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>����A<BR>
     *     �ȉ��̏�����������̌������J�E���g����B<BR>
     *     �E������.get�����^�C�v()����<BR>
     *     �E������.get�g�����U�N�V�����^�C�v()���~�j��<BR>
     *     �E������.is�ϓ����f���Ԓ�(�w���)<BR>
     * <BR>
     * �Q�j�@@�J�E���g����������ԋp<BR>
     * @@param l_datDate - (�w���)
     * @@return int
     * @@roseuid 40DBC6D202CD
     */
    public int getTodaysMiniStockTransactionsCount(Date l_datDate)
    {
        return searchTransactions(todaysTransactions, ProductTypeEnum.EQUITY,
                new FinTransactionType[]
                                       {FinTransactionType.EQTYPE_MINI_STOCK_BUY, FinTransactionType.EQTYPE_MINI_STOCK_SELL,}
        , l_datDate).length;
    }
    
    /**
     * (get�I�v�V���������񌏐�<����>)<BR>
     * �w�������n���ł��铖���ȍ~���������񌏐���ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>����A<BR>
     *     �ȉ��̏����Ŏ�����̌������J�E���g����B<BR>
     *     �E������.get�����^�C�v���I�v�V����<BR>
     *     �E������.is�ϓ����f���Ԓ�(�w���)<BR>
     * <BR>
     * �Q�j�@@�J�E���g����������ԋp<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return int
     * @@roseuid 40DBC49600DC
     */
    public int getTodaysOptionTransactionsCount(Date l_datDate)
    {
        return searchTransactions(todaysTransactions, ProductTypeEnum.IFO, null,
                l_datDate).length;
    }
    
    /**
     * (get���M�����񌏐�<����>)<BR>
     * �w�������n���ł��铖���ȍ~���������񌏐���ԋp����B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>����A<BR>
     *     �ȉ��̏�����������̌������J�E���g����B<BR>
     *     �E������.get�����^�C�v�����M<BR>
     *     �E������.is�ϓ����f���Ԓ�(�w���)<BR>
     * <BR>
     * �Q�j�@@�J�E���g����������ԋp<BR>
     * @@param l_datDate - (�w���)
     * @@return int
     * @@roseuid 40DBCD0B0301
     */
    public int getTodaysMutualFundTransactionsCount(Date l_datDate)
    {
        return searchTransactions(todaysTransactions, ProductTypeEnum.MUTUAL_FUND, null,
                l_datDate).length;
    }
    
    /**
     * (get�O�������񌏐�<����>)<BR>
     * �w�������n���ł��铖���ȍ~���������񌏐���ԋp����B<BR>
     * �������O�����̖������܂܂Ȃ������Ƃ���B<BR>
     * <BR>
     * �P�j�@@������ꗗ<����>����A<BR>
     *     �ȉ��̏�����������̌������J�E���g����B<BR>
     *     �E������.get�����^�C�v���O��<BR>
     *     �E������.is�ϓ����f���Ԓ�(�w���)<BR>
     *     �E�i(������.�g�����U�N�V�����^�C�v=�O���� ����<BR>
     *     ������. get���ϐ���() > 0 )<BR>
     *     ����<BR>
     *     (������.�g�����U�N�V�����^�C�v=�O����)�j<BR>
     * <BR>
     * �Q�j�@@�J�E���g����������ԋp<BR>
     * <
     * @@param l_datDate - (�w���)
     * @@return int
     * @@roseuid 40DBCDF50179
     */
    public int getTodaysForeignEquityTransactionsCount(Date l_datDate)
    {
        WEB3TPTransactionReflector[] l_transactionReflector =
            searchTransactions(todaysTransactions, ProductTypeEnum.FOREIGN_EQUITY, null,
                l_datDate);
        int l_intCount = 0;
        for (int i = 0;i < l_transactionReflector.length; i++)
        {
            WEB3TPTransactionReflector l_tranRef = l_transactionReflector[i];

            if ((WEB3DateUtility.compareToDay(l_tranRef.getDeliveryDate(), l_datDate) == 0)
                && (ProductTypeEnum.FOREIGN_EQUITY.equals(l_tranRef.getProductType())))
            {

                FinTransactionType l_tranType = l_tranRef.getFinTransactionType();
                //������.�g�����U�N�V�����^�C�v=�O����
                if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_tranType))
                {
                    //������. get���ϐ���() > 0
                    if (l_tranRef.getExecutedQuantity() > 0)
                    {
                        l_intCount++;
                    }
                }
                else
                {
                    l_intCount++;
                }
            }
        }
        return l_intCount;
    }
    
    /**
     *�@@����.���X�g�Ɋ܂܂�������̂����A�ȉ��̂悤�ɏ������������𔻒f��<BR>
     *�@@�}�b�`�����������z��ŕԂ��B<BR>
     *�@@1�����}�b�`���Ȃ��ꍇ0�̔z���Ԃ��B<BR>
     *�@@�P�j�@@����.��n���Ǝ�n������v<BR>
     *�@@�Q�j�@@�����ŗ^����ꂽ�����^�C�v����v<BR>
     *�@@�R�j�@@�i�����Ŏw�肳��Ă���ꍇ�A�j�g�����U�N�V�����^�C�v�i�z��̗v�f�j����v<BR>
     *
     * @@param l_transactions ������̏W��
     * @@param l_productType�@@�����^�C�v
     * @@param l_transactionTypes�@@�g�����U�N�V�����^�C�v
     * @@param l_datDeliveryDate�@@(��n��)
     * @@return WEB3TPTransactionReflector[]
     */
    protected WEB3TPTransactionReflector[] searchTransactions(
            List l_transactions,
            ProductTypeEnum l_productType,
            FinTransactionType[] l_transactionTypes,
            Date l_datDeliveryDate)
    {
        List l_targetTrans = new ArrayList();
        
        //��n��T+6�ȍ~�Ή�
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        for (Iterator l_iter = l_transactions.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = (WEB3TPTransactionReflector) l_iter.
            next();
            
            Date l_tranDeliveryDate = l_tranRef.getDeliveryDate();            
            
            //��n��T+6�ȍ~�Ή�
            if(WEB3DateUtility.compareToDay(l_datT5, l_tranRef.getDeliveryDate()) < 0)
            {
                l_tranDeliveryDate = l_datT5;
            }
            
            if ((WEB3DateUtility.compareToDay(l_tranDeliveryDate, l_datDeliveryDate) == 0) &&
                    (l_productType.equals(l_tranRef.getProductType())))
            {
                //�w��Ȃ����Key�Ƃ��ă`�F�b�N���Ȃ��B
                if (l_transactionTypes == null)
                {
                    l_targetTrans.add(l_tranRef);
                }
                //�w�肠���Key�Ƃ��ă`�F�b�N����B
                else
                {
                    for (int j = 0; j < l_transactionTypes.length; j++)
                    {
                        if (l_transactionTypes[j].equals(l_tranRef.getFinTransactionType()))
                        {
                            l_targetTrans.add(l_tranRef);
                        }
                    }
                }
            }
        }
        
        return (WEB3TPTransactionReflector[]) l_targetTrans.toArray(new
                WEB3TPTransactionReflector[l_targetTrans.size()]);
        
    }
    
    /**
     * (do�����񃍁[�h)<BR>
     * do�m������񃍁[�h()<BR>
     * do�����ȍ~�����񃍁[�h()<BR>
     * ���ĂԁB<BR>
     * @@roseuid 40DAC4190180
     */
    public void load()
    {
        mutualFundTransactions = 
            WEB3TPPersistentDataManager.getInstance().getMutualFundOrders(this);
        this.loadTodaysTransactions();
        this.loadFixedTransactions();
    }
    
    /**
     * (do�����񃍁[�h<�m��>)<BR>
     * �m�������ׂ�������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@�m�������ׂ��ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     * �@@�����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�m��������()<BR>
     * <BR>
     * <BR>
     * �ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * �E�����^�C�v        ��  �m��������.�����^�C�v<BR>
     * �E����ID            ��  �m��������.����ID<BR>
     * �E�g�����U�N�V�����^�C�v        ��  �m��������.�g�����U�N�V�����^�C�v<BR>
     * �E�g�����U�N�V����������    ��  �m��������.�g�����U�N�V����������<BR>
     * �E����萔��        ��  ���w��i0�j<BR>
     * �E�������        ��  ���w��i0�j<BR>
     * �E���ϐ���        ��  �m��������.��萔��<BR>
     * �E���ϑ��        ��  �m��������.��n���z<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i�m��������.��n���j<BR>
     * <BR>
     * �R�j�@@������ꗗ<�m��>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40D9700E0323
     */
    protected void loadFixedTransactions()
    {
        List l_fixedFinTranRows = WEB3TPPersistentDataManager.getInstance().getFixedFinTransactions(
                this);
        
        for (Iterator l_iter = l_fixedFinTranRows.iterator(); l_iter.hasNext(); )
        {
            WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                    FixedFinTransactionRow) l_iter.next());
            addFixedTransaction(l_tranRef);
            
        }
    }
    
    /**
     * (do�����񃍁[�h<����>)<BR>
     * <BR>
     * ����������֘A�f�[�^�����[�h����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(������)do�����񃍁[�h<����>�v�Q��<BR>
     * <BR>
     */
    protected void loadTodaysTransactions()
    {
        loadTodaysCashBasedEquityTransactions();
        loadTodaysCloseMarginTransactions();
        loadTodaysSwapMarginTransactions();
        loadTodaysMiniStorckTransactions();
        loadTodaysOptionTransactions();
        loadMutualFundTransactions();
        loadTodaysGPTransactions();
        loadTodaysForeignEquityTransactions();
        loadTodaysCashInOutTransactions();
        loadTranferCashTransactions();
        loadBondTransactions();
        loadTodaysIpoTransactions();
    }
    
    /**
     * (do�����������񃍁[�h<����>)<BR>
     * ���������P��<������>��������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@���������P��<������>���ȉ��̃��\�b�h���Ăю擾����B<BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~����������()<BR>
     * <BR>
     * �ȍ~�̏����́AloadTodaysEqtypeTransactions()���Ă��<BR>
     * �������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * �E�����^�C�v        ��  ��������.�����^�C�v<BR>
     * �E����ID            ��  ��������.����ID<BR>
     * �E�g�����U�N�V�����^�C�v        �� ��������.�������.toFinTransactionType()<BR>
     * �E�g�����U�N�V����������    ��  ��������.������<BR>
     * �E����萔��        ��  ��������.���ʁ@@�|�@@��������.���ϐ���<BR>
     * �E�������        ���@@��������.�T�Z��n���<BR>
     * �E���ϐ���        ��  ��������.���ϐ���<BR>
     * �E���ϑ��        ���@@�����g�����U�N�V����.��n���(*)<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i��������.��n���j<BR>
     * �E�ϓ����f�J�n��        ��  ����.��n��<BR>
     * �E�ϓ����f�I����        ��  ����.��n��<BR>
     * <BR>
     * (*)
     * ������������肵�Ă���ꍇ�A�����g�����U�N�V�������ȉ��̃��\�b�h���Ăю擾����B
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����g�����U�N�V����()<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BFDF2602CE
     */
    protected void loadTodaysCashBasedEquityTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCashBasedEquityOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
    }
    
    /**
     * (do�M�p�ԍώ����񃍁[�h<����>)<BR>
     * ���������P��<�M�p�ԍ�>��������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@���������P��<�M�p�ԍ�>���ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *  �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~�M�p�ԍϒ���()<BR>
     * <BR>
     * �ȍ~�̏����́AloadTodaysEqtypeTransactions()���Ă��<BR>
     * �������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * �@@do�����ȍ~�����������񃍁[�h()�̂Q�j�Q�ƁB<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40DF8D390384
     */
    protected void loadTodaysCloseMarginTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCloseMarginOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
    }
    
    /**
     * (do�������n�����񃍁[�h<����>)<BR>
     * ���������P��<�������n>��������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@���������P��<�������n>���ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *  �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~�������n����()<BR>
     * <BR>
     * �ȍ~�̏����́AloadTodaysEqtypeTransactions()���Ă��<BR>
     * �������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * �@@do�����ȍ~�����������񃍁[�h()�̂Q�j�Q�ƁB<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40DF8D4C0375
     */
    protected void loadTodaysSwapMarginTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysSwapMarginOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
    }
    
    /**
     * (do�~�j�������񃍁[�h<����>)<BR>
     * �~�j��������������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@�~�j���������ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~�~�j������()<BR>
     * <BR>
     * �ȍ~�̏����́AloadTodaysEqtypeTransactions()���Ă��<BR>
     * �������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * �@@do�����ȍ~�����������񃍁[�h()�̂Q�j�Q�ƁB<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BFDF5703C8
     */
    protected void loadTodaysMiniStorckTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysMiniStockOrders(this);
        loadTodaysEqtypeTransactions(l_rows);
        
    }
    
    /**
     * (do���������񃍁[�h)<BR>
     * ��������(EqtypeOrderUnit)��������Ƀ��[�h����B<BR>
     *
     * @@param l_eqtypeOrderUnitRows (���������P�ʂ̃��X�g)
     */
    protected void loadTodaysEqtypeTransactions(List l_rows)
    {
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                
                WEB3TPTransactionReflector[] l_tranRefs = createWEB3TPTransactionReflector( (
                        EqtypeOrderUnitRow) l_iter.next());               
                
                for(int i = 0; i < l_tranRefs.length; i++)
                {    
                    log.debug("�ǉ�����TodaysTransaction=" + l_tranRefs[i]);
                    addTodaysTransaction(l_tranRefs[i]);
                }
                
            }
        }
    }
    
    /**
     * (do�I�v�V���������񃍁[�h<����>)<BR>
     * �I�v�V����������������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@�I�v�V�����������ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~�I�v�V��������()<BR>
     * <BR>
     * �ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * �E�����^�C�v        ��  �I�v�V��������.�����^�C�v<BR>
     * �E����ID            ��  �I�v�V��������.����ID<BR>
     * �E�g�����U�N�V�����^�C�v        �� �I�v�V��������.�������.toFinTransactionType()<BR>
     * �E�g�����U�N�V����������    ��  �I�v�V��������.������<BR>
     * �E����萔��        ��  �I�v�V��������.���ʁ@@�|�@@�I�v�V��������.���ϐ���<BR>
     * �E���ϐ���        ��  �I�v�V��������.���ϐ���<BR>
     * �E�������        ���I�v�V��������.�T�Z��n���<BR>
     * �E�����          ���I�v�V�����g�����U�N�V����.��n���<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i�I�v�V��������.��n���j<BR>
     * �E�ϓ����f�J�n��        ��  ����.��n��<BR>
     * �E�ϓ����f�I����        ��  ����.��n��<BR>
     * <BR>
     * (*)
     * �I�v�V������������肵�Ă���ꍇ�A�I�v�V�����g�����U�N�V�������ȉ��̃��\�b�h����
     * �ю擾����B<BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�I�v�V�����g�����U�N�V����()<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BFDF55028F
     */
    protected void loadTodaysOptionTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysOptionOrders(this);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector[] l_tranRefs = createWEB3TPTransactionReflector( (
                        IfoOrderUnitRow) l_iter.next());
                
                for(int i = 0; i < l_tranRefs.length; i++)
                {    
                    addTodaysTransaction(l_tranRefs[i]);
                }                
            }
        }
        
    }
    
    /**
     * (do���M�����񃍁[�h)<BR>
     * ���M������������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@���M�������ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����M������<������><BR>
     * <BR>
     * �ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * <BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BFDF5601B4
     */
    protected void loadMutualFundTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getMutualFundOrdersAmount(this.mutualFundTransactions, this);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                        MutualFundOrderUnitRow) l_iter.next());
                addTodaysTransaction(l_tranRef);
            }
        }
        
    }
    
    /**
     * (do���o�������񃍁[�h<����>)<BR>
     * ���o��������������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@���o���������ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~���o������()<BR>
     * <BR>
     * �ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * �E�����^�C�v        ��  ���o������.�����^�C�v<BR>
     * �E����ID            ��  ���o������.����ID<BR>
     * �E�g�����U�N�V�����^�C�v        �� ���o������.�������.toFinTransactionType()<BR>
     * �E�g�����U�N�V����������    ��  ���o������.������<BR>
     * �E����萔��        ��  �O<BR>
     * �E���ϐ���        ��  ���o������.����<BR>
     * �E�������        ��  �O<BR>
     * �E���ϑ��        ��  ���o������.����<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i���o������.��n���j<BR>
     * �E�ϓ����f�J�n��        ��  ����.��n��<BR>
     * �E�ϓ����f�I����        ��  ����.��n��<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40CEE0430055
     */
    protected void loadTodaysCashInOutTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysCashInOutOrders(this);
        Date l_datT5 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        
        //�v�Z�������T�[�r�X���p�S�����ǂ����擾
        //�l���ݒ肳��Ă��Ȃ��ꍇ�͍S���Ƃ݂Ȃ�
        String l_strVal = this.getCalcCondition().getInstBranCalcCondition(WEB3TPCalcCondition.SERVICE_CHARGE_RESTRAINT);       
        boolean isServiceChargeCheckBranch =
            (WEB3TPServiceChargeRestraintDef.EXCEPT.equals(l_strVal)) ?
                    false : true;
        
        log.debug("** �T�[�r�X���p�S���L��=" + isServiceChargeCheckBranch);	                    
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {    
                AioOrderUnitRow l_row = (AioOrderUnitRow)l_iter.next();
                
                //�T�[�r�X���p�ɂ��o���A���A��n����T+5����ł������ꍇ��
                //�T�[�r�X���p�S�����镔�X�̏ꍇ��                
                //����������Ƃ��Čv�サ�Ȃ��B
                //(�T�[�r�X���p�S�����Ȃ����X�̏ꍇ�A�������ϑ���Ɍv��)
                
                if(!((OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType())) &&
                        (l_row.getPaymentApplicationDiv() != null) && 
                        (! WEB3TPPaymentApplicationDivDef.MF_SELL_WITH_CASH_OUT.equals(l_row.getPaymentApplicationDiv())) && 
                        (WEB3DateUtility.compareToDay(l_row.getDeliveryDate(), l_datT5) > 0) &&
                        isServiceChargeCheckBranch))
                {
                    WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector(l_row);
                    addTodaysTransaction(l_tranRef);	                
                    log.debug("�ǉ��������o���g�����U�N�V�����F" + l_tranRef + " ===>aio_order_unit_id=" + l_row.getOrderUnitId());                                        
                }                
                
                //�L�����ȊO�̏o��������
                //calc�o���z(calcCashOut)�̑Ώے����Ƃ���B�i�]�͌v�Z���ʏڍׂɔ��f�j        
                if(OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType()) &&
                        ((l_row.getPaymentApplicationDiv() == null) ||
                        (WEB3TPPaymentApplicationDivDef.MF_SELL_WITH_CASH_OUT.equals(l_row.getPaymentApplicationDiv()))))
                {
                    WEB3TPTransactionReflector l_cashOutTranRef = createWEB3TPTransactionReflector(l_row);
                    addDisplayCashOutTransaction(l_cashOutTranRef);	                
                    log.debug("�ǉ������]�͌v�Z���ʏڍחp�o���g�����U�N�V�����F" + l_cashOutTranRef + " ===>aio_order_unit_id=" + l_row.getOrderUnitId());                                                            
                }                
            }
        }
        
        /*                if((OrderTypeEnum.CASH_OUT.equals(l_row.getOrderType())) &&
         (l_row.getPaymentApplicationDiv() != null) && 
         (WEB3DateUtility.compareToDay(l_row.getDeliveryDate(), l_datT5) > 0))
         {
         if(!isServiceChargeCheckBranch)
         {
         WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector(l_row);
         addTodaysTransaction(l_tranRef);
         log.debug("** �T�[�r�X���p�S������::��n����T+5����̗L����� **");
         log.debug("�ǉ��������o���g�����U�N�V�����F" + l_tranRef + " ���o�������P��=" + l_row);
         
         }
         
         //�T�[�r�X���p�S�����镔�X�̏ꍇ�͓������ϑ���Ɍv�サ�Ȃ��B
          } 
          
          //��L�ȊO�̓��o������
           else
           {
           WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector(l_row);
           addTodaysTransaction(l_tranRef);
           log.debug("�ǉ��������o���g�����U�N�V�����F" + l_tranRef + " ���o�������P��=" + l_row);
           
           }                
           }
           */        
        
    }        
    
    /**
     * (do�U�֎����񃍁[�h<����>)<BR> 
     *<BR>
     * �U�֎�����֘A�f�[�^�����[�h����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(������)do�U�֎����񃍁[�h<����>�v�Q��<BR> 
     */
    protected void loadTranferCashTransactions()
    {
        final String STR_METHOD_NAME = "loadTranferCashTransactions";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get�����ȍ~�U�֒���(���Y�]��)
        //���[�h�ΏۂƂȂ�AIO�����P��Row�I�u�W�F�N�g�̃��X�g���擾����B
        //[����]
        //���Y�]�� = this
        List l_transferOrderUnitRows = WEB3TPPersistentDataManager.getInstance().
        getTodaysCashTransferOrders(this);       
        
        //1.2.(*)��LOOP�����Fget�����ȍ~�U�֒���()�̖߂�l�̗v�f����
        for (Iterator l_iter = l_transferOrderUnitRows.iterator(); l_iter.hasNext(); )
        {
            //1.2.1.get���v����(AssetTransferTypeEnum)
            //1.2.2.(*)"���ϑ��"���v�Z����B
            //"���ϑ��" = (*)AIO�����P��Row.�������� * get���v����()
            //(*)
            //AIO�����P��Row = get�����ȍ~�U�֒���()�̖߂�l�̗v�f�I�u�W�F�N�g
            AioOrderUnitRow l_row = (AioOrderUnitRow) l_iter.next();

            double l_dblTransferAmount = l_row.getQuantity() * WEB3TPTransactionReflector.getCashDir(
                l_row.getTransferType());
            
            //1.2.3.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType, Date, 
            //double, double, double, double, Date, TaxTypeEnum)
            //������I�u�W�F�N�g�𐶐�����B
            //[����]
            //�]�͌v�Z���� = this.get�]�͌v�Z����()
            //ProductTypeEnum = (*)AIO�����P��Row.�����^�C�v
            //long = (*)AIO�����P��Row.����ID
            //FinTransactionType = (*)AIO�����P��Row.�������.toFinTransactionType()
            //Date = (*)AIO�����P��Row.������
            //double = 0
            //double = 0
            //double = 0
            //double = "���ϑ��"
            //Date = (*)AIO�����P��Row.��n��
            //TaxTypeEnum = (*)AIO�����P��Row.�ŋ敪
            //(*)AIO�����P��Row = get�����ȍ~�U�֒���()�̖߂�l�̗v�f�I�u�W�F�N�g
            WEB3TPTransactionReflector l_tranRef = WEB3TPTransactionReflector.create(
                getCalcCondition(), 
                l_row.getProductType(), 
                l_row.getProductId(),
                l_row.getOrderType().toFinTransactionType(), 
                WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd"), 0, 0, 0, 
                l_dblTransferAmount,
                l_row.getDeliveryDate(),
                l_row.getTaxType());
            
            //1.2.4.add������<����>(������)
            //�擾����������I�u�W�F�N�g���Athis.������ꗗ<����>�ɒǉ�����B
            //[����]
            //������ = (*)create������()�̖߂�l
            addTodaysTransaction(l_tranRef);    
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (doIPO�����񃍁[�h<����>)<BR>
     * IPO������֘A�f�[�^�����[�h����B<BR>
     * <BR>
     * �V�[�P���X�}�u(������)doIPO�����񃍁[�h<����>�v�Q��<BR>
     * <BR>
     * @@roseuid 40BFDF55028F
     */
    protected void loadTodaysIpoTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysIPOOrders(this);
        Date l_datT0 = getCalcCondition().getBizDate(0);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
            	IpoOrderRow l_orderRow = (IpoOrderRow)l_iter.next();
            	
            	//IPO�������̎擾
            	IpoProductRow l_productRow = WEB3TPPersistentDataManager.getInstance().getIPOProduct(
            			l_orderRow.getIpoProductId());

                //get�]�͌v�Z����
                WEB3TPCalcCondition l_calcCondition = getCalcCondition();

                //get��Е��X�ʗ]�͌v�Z����
                String l_strValue = l_calcCondition.getInstBranCalcCondition(
                    WEB3TPCalcCondition.IPO_OFFER_TRADINGPOWER_CHECK);

            	//(*)����t���[
                if ((WEB3IpoOfferTradingpowerCheckDef.CHECK.equals(l_strValue) &&
                    WEB3LotResultDef.SUPPLEMENT.equals(l_orderRow.getLotResult()) &&
                    !WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_orderRow.getOfferingDiv())) ||
                    WEB3TPIPOHostAcceptStatusDef.PROCESSED.equals(l_orderRow.getAcceptStatus()) ||
                    l_productRow.getOfferEndDateProspec() == null ||
                    l_datT0.compareTo(l_productRow.getOfferEndDateProspec()) > 0 ||
                    BooleanEnum.TRUE.equals(l_productRow.getDeleteFlag()))
                {
                    continue;
                }

            	WEB3TPTransactionReflector l_tranRefs = createWEB3TPTransactionReflector(l_orderRow,l_productRow);
                
                addTodaysTransaction(l_tranRefs);
            }
        }
        
    }
    
    /**
     * (add������<�m��>)<BR>
     *�@@������������ꗗ<�m��>�ɒǉ�����B<BR>
     * @@param transaction - (������)
     * @@roseuid 40F7B01601CE
     */
    protected void addFixedTransaction(WEB3TPTransactionReflector l_transaction)
    {
        if (l_transaction instanceof WEB3TPTransactionReflector)
        {
            fixedTransactions.add(l_transaction);
        }
        
    }
    
    /**
     * (add������<����>)<BR>
     *�@@������������ꗗ<����>�ɒǉ�����B<BR>
     * @@param transaction - (������)
     * @@roseuid 40DFC17103B3
     */
    protected void addTodaysTransaction(WEB3TPTransactionReflector l_transaction)
    {
        if (l_transaction instanceof WEB3TPTransactionReflector)
        {
            todaysTransactions.add(l_transaction);
        }
    }

    /**
     * (add�o�������ꗗ<�]�͌v�Z���ʏڍחp>)<BR>
     *�@@�������o�������ꗗ<�]�͌v�Z���ʏڍחp>�ɒǉ�����B<BR>
     * @@param transaction - (������)
     * @@roseuid 40DFC17103B3
     */
    protected void addDisplayCashOutTransaction(WEB3TPTransactionReflector l_transaction)
    {
        if (l_transaction instanceof WEB3TPTransactionReflector && 
                FinTransactionType.DEBIT.equals(l_transaction.getFinTransactionType()))
        {
            displayCashOutTransactions.add(l_transaction);
        }
    }
    
    /**
     * (create������) <BR>
     *<BR>
     * ����.�����������A������I�u�W�F�N�g���쐬���ԋp����B<BR> 
     *<BR>
     * ���V�[�P���X�}�u(������)create������(EqtypeOrderUnitRow)�v�Q�� <BR>
     * <BR>
     * @@param l_orderUnitRow - (��������)
     * @@return WEB3TPTransactionReflector[]
     * @@roseuid 40DBEE37031E
     */
    protected WEB3TPTransactionReflector[] createWEB3TPTransactionReflector(
        EqtypeOrderUnitRow l_orderUnitRow)
    {    	
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.(*)"����萔��"�A"���ϐ���"���v�Z����B
        //����萔��, ���ϐ���
        double l_dblUnexecutedQuantity, l_dblExecutedQuantity;
        //�������,���ϑ��
        double l_dblUnexecutedAmount, l_dblExecutedAmount;
        
        //"����萔��" = (*1)
        //"���ϐ���" = ����.��������.get��萔��()
        //(*1)
        //[a.����.��������.get�����L�����() == 2:�N���[�Y �̏ꍇ]
        //�@@"����萔��" = 0
        if(OrderOpenStatusEnum.CLOSED.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = 0.0d;
        }
        //[a.�ȊO�̏ꍇ]
        //�@@"����萔��" =�@@����.��������.get��������() - ����.��������.get��
        else
        {
            //�s��m�F�ϒl�Ή�
            l_dblUnexecutedQuantity = l_orderUnitRow.getQuantity() - l_orderUnitRow.getExecutedQuantity();
        }
        
        l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        
        //����̎擾
        
        //1.2.get���v����(FinTransactionType)
        //�g�����U�N�V�����^�C�v�ʂ̑��v�������擾����B
        //[����]
        //FinTransactionType = ����.��������.get�������().toFinTransactionType()�̖߂�l
        Date l_tranDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        Date l_deliveryDate = l_orderUnitRow.getDeliveryDate();
        FinTransactionType l_tranType = l_orderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.3.get���ώ�n������v(EqtypeOrderUnitRow)
        //���������P�ʂɕR�t����n������W�v����B
        //[����]
        //EqtypeOrderUnitRow = ����.��������
        
        //1.4.(*)����t���[
        //�S�����̏ꍇ
        //("����萔�� == 0")
        if(l_dblUnexecutedQuantity == 0)
        {
            //1.4.1(*)"�������"�A"���ϑ��"���v�Z����B
            //"�������" = 0
            //"���ϑ��" = get���ώ�n������v()�̖߂�l
            l_dblUnexecutedAmount = 0;
            l_dblExecutedAmount = getNetAmountTotal(l_orderUnitRow);                        
        }
        
        //1.5.(*)����t���[
        //�ȊO�i�ꕔ���E�����j�̏ꍇ
        else
        {
            double l_dblEstimatedPrice = l_orderUnitRow.getEstimatedPrice();           
            
            //[a.�����E���n�̏ꍇ]
            // �i����.��������.get�������().toFinTransactionType() == 130�F���� or 140�F���n�j
            // �@@"���ϑ��" = ����.��������.get�T�Z��n���() * get���v����()�̖߂�l
            // �@@"�������" = 0      
            if (FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_tranType) ||
                    FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_tranType))
            {
                l_dblUnexecutedAmount = 0;
                l_dblExecutedAmount = l_dblEstimatedPrice * l_intCashDir;  
            }
            
            //
            // [a.�~�j�����̏ꍇ]
            // �i����.��������.get�������().toFinTransactionType() == 201�F�~�j�����j
            // �@@"���ϑ��" = 0
            // �@@"�������" = ����.��������.get�T�Z��n���() * get���v����()�̖߂�l
            else if(FinTransactionType.EQTYPE_MINI_STOCK_BUY.equals(l_tranType))
            {
                l_dblUnexecutedAmount = l_dblEstimatedPrice * l_intCashDir; 
                l_dblExecutedAmount = 0;
            }
            
            // [a.�ȊO�i�������E�������E�M�p�ԍρE�~�j�����j�̏ꍇ]
            // �@@"���ϑ��" = get���ώ�n������v()�̖߂�l
            else 
            {    
                // �@@[b.�������̏ꍇ]
                // �@@�i����.��������.get�������().toFinTransactionType() == 70�F�������j
                // �@@�@@"�������" = (����.��������.get�T�Z��n���() - ABS("���ϑ��")) * get���v����()�̖߂�l
                l_dblExecutedAmount = getNetAmountTotal(l_orderUnitRow);
                if(FinTransactionType.EQTYPE_EQUITY_BUY.equals(l_tranType))
                {
                    log.debug("l_dblEstimatedPrice=" + l_dblEstimatedPrice);
                    
                    l_dblUnexecutedAmount = 
                        (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice * l_intCashDir :
                            (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount)) * l_intCashDir;
                    
                    log.debug("l_dblUnexecutedAmount=" + l_dblUnexecutedAmount);
                    
                    //2���Ή��ۗ����Č�
                    /*
                     //�o���I����̏ꍇ 
                      if(getCalcCondition().isEquityExecutionDiv())
                      {
                      Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);                                                       
                      
                      //�o���I���㒍���J�z�������̌J�z��������蕪�̎�n�����J�z��Ɠ���ɂ���Ή�        
                       
                       //�����J�z�������̌J�z�����̏ꍇ
                        //�i���������@@T+0�@@���@@�����������@@���@@�������j
                         //�����J�z�����ώ��Ɠ��l�̍S�����s��
                          
                          List l_tranRefs = new ArrayList();
                          
                          if((WEB3DateUtility.compareToDay(l_datT0, l_tranDate) == 0) && 
                          (WEB3DateUtility.compareToDay(l_row.getExpirationDate(), l_tranDate) > 0))
                          {
                          //�ꕔ���̏ꍇ
                           //������蕪�̎������I�u�W�F�N�g�𐶐�����
                            if(l_dblExecutedQuantity > 0) 
                            {                                                  
                            WEB3TPTransactionReflector l_todayExecTranRef = 
                            WEB3TPTransactionReflector.create
                            (
                            getCalcCondition(),
                            l_row.getProductType(),
                            l_row.getProductId(),
                            l_tranType,
                            l_tranDate,            
                            0, 0,//��������͌J�z�������Ƃ��Čv�シ��̂�0�Ƃ���,
                            l_dblExecutedQuantity, l_dblExecutedAmount,//�������ϑ���𔽉f
                            l_deliveryDate//��n��
                            );
                            
                            l_tranRefs.add(l_todayExecTranRef);
                            }
                            
                            
                            //�J�z����関��蕪�̎������I�u�W�F�N�g�����z�I�ɐ�������
                             //��n���̎擾===�]�͍X�V�œ���I�Ɏ�n���v�Z��Utility���R�[������悤�ɏC���\��
                              //������������
                               
                               WEB3TPTransactionReflector l_carryOverTranRef =  
                               WEB3TPTransactionReflector.create
                               (
                               getCalcCondition(),
                               l_row.getProductType(),
                               l_row.getProductId(),
                               l_tranType,
                               l_tranDate,            
                               l_dblUnexecutedQuantity, l_dblUnexecutedAmount,//��������̂ݔ��f
                               0, 0,//���ϑ����0
                               getCalcCondition().rollBizDate(l_deliveryDate, 1)//��n���͈����փV�t�g
                               );
                               
                               l_tranRefs.add(l_carryOverTranRef);
                               
                               return (WEB3TPTransactionReflector[])l_tranRefs.toArray(new WEB3TPTransactionReflector[l_tranRefs.size()]);
                               
                               }
                               
                               }
                               
                               */
                }
                
                // �@@[b.�ȊO�i�������E�M�p�ԍρE�~�j�����j�̏ꍇ]
                // �@@�i����.��������.get�������().toFinTransactionType() == 70�F�������j
                // �@@�@@"�������" = 0
                else
                {
                    l_dblUnexecutedAmount = 0.0d;
                }
            }            
        }
        
        //1.6.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //������I�u�W�F�N�g�𐶐�����B
        //
        //[����]
        //�]�͌v�Z���� = this.get�]�͌v�Z����()
        //ProductTypeEnum = ����.��������.get�����^�C�v()
        //long = ����.��������.get����ID()
        //FinTransactionType = ����.��������.get�������().toFinTransactionType()
        //Date = ����.��������.get������()
        //double = �v�Z����"����萔��"
        //double = �v�Z����"�������"
        //double = �v�Z����"���ϐ���"
        //double = �v�Z����"���ϑ��"
        //Date = ����.��������.get��n��()
        //TaxTypeEnum = ����.��������.get�ŋ敪()
        //1.7.(*)�ԋp�l�F������[]{create������()�̖߂�l}
        log.exiting(STR_METHOD_NAME);
        return new WEB3TPTransactionReflector[] {
                WEB3TPTransactionReflector.create
                (
                        getCalcCondition(),
                        l_orderUnitRow.getProductType(),
                        l_orderUnitRow.getProductId(),
                        l_tranType,
                        l_tranDate,            
                        l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                        l_dblExecutedQuantity, l_dblExecutedAmount,
                        l_deliveryDate,
                        l_orderUnitRow.getTaxType()
                )
        };
    }
    
    /**
     * (doGP�����񃍁[�h<����>)<BR>
     * GP������������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@GP�������ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~GP����()<BR>
     * <BR>
     * �ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * �E�����^�C�v        ��  GP����.�����^�C�v<BR>
     * �E����ID            ��  GP����.����ID<BR>
     * �E�g�����U�N�V�����^�C�v        �� GP����.�������.toFinTransactionType()<BR>
     * �E�g�����U�N�V����������    ��  GP����.������<BR>
     * �E����萔��        ��  GP����.���ʁ@@�|�@@GP����.���ϐ���<BR>
     * �E���ϐ���        ��  GP����.���ϐ���<BR>
     * �E�������        ��  0<BR>
     * �E���ϑ��        ��  GP����.�T�Z��n���z<BR>
     * �E�ϓ����f���A�ϓ��J�n���ݒ�Fcalc�ϓ����f���iGP����.��n���j<BR>
     * �E�ϓ����f�J�n��        ��  ����.��n��<BR>
     * �E�ϓ����f�I����        ��  ����.��n��<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BFDF5700CA
     */
    protected void loadTodaysGPTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysGPOrders(this);
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                        RuitoOrderUnitRow) l_iter.next());
                addTodaysTransaction(l_tranRef);
                
            }
        }
        
        
    }
    
    /**
     * (do�O�������񃍁[�h<����>)<BR>
     * �O��������������I�u�W�F�N�g�Ƀ��[�h����B<BR>
     * <BR>
     * �P�j�@@�O���������ȉ��̃��\�b�h���Ăю擾����B<BR>
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~�O������()<BR>
     * <BR>
     * �ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�@@������𐶐�����B<BR>
     * <BR>
     * <BR>
     * �|�ݒ荀��<BR>
     * �E�����^�C�v        ��  �O������.�����^�C�v<BR>
     * �E����ID            ��  �O������.����ID<BR>
     * �E�g�����U�N�V�����^�C�v        �� �O������.�������.toFinTransactionType()<BR>
     * �E�g�����U�N�V����������    ��  �O������.������<BR>
     * �E����萔��        ��  �O������.���ʁ@@�|�@@�O������.���ϐ���<BR>
     * �E���ϐ���        ��  �O������.���ϐ���<BR>
     * �E�������        ���@@�O������.�T�Z��n���<BR>
     * �E���ϑ��        ���@@�O���g�����U�N�V����.��n���<BR>
     * �E�ϓ����f�J�n���A�ϓ����f�I�����ݒ�Fcalc�ϓ����f���i�O������.��n���j<BR>
     * �E�ϓ����f�J�n��        ��  ����.��n��<BR>
     * �E�ϓ����f�I����        ��  ����.��n��<BR>
     * <BR>
     * (*)
     * �O����������肵�Ă���ꍇ�A�O���g�����U�N�V�������ȉ��̃��\�b�h���Ăю擾����B
     * <BR>
     *     �����֘A�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�O���g�����U�N�V����()<BR>
     * <BR>
     * �R�j�@@������ꗗ<����>�ɐ��������������ǉ�����B<BR>
     * <BR>
     * @@roseuid 40BFDF590389
     */
    protected void loadTodaysForeignEquityTransactions()
    {
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysFeqOrders(this);
        
        for(int i = 0; i < l_rows.size(); i++)
        {
            log.debug("�I�����ꂽ�s[" + i + "]=" + (FeqOrderUnitRow)l_rows.get(i));                
        }
        
        
        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext(); )
            {
                WEB3TPTransactionReflector l_tranRef = createWEB3TPTransactionReflector( (
                        FeqOrderUnitRow) l_iter.next());
                addTodaysTransaction(l_tranRef);
                
                log.debug("�ǉ����ꂽ������=" + l_tranRef);
                
            }
        }
        
    }
    
    /**
     * (do�������񃍁[�h) <BR>
     * <BR>
     * ��������֘A�f�[�^�����[�h����B <BR>
     * <BR>
     * ���V�[�P���X�u(������)do�������񃍁[�h�v�Q��<BR>
     */
    protected void loadBondTransactions()
    {
        final String STR_METHOD_NAME = "loadBondTransactions()";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get������<������>(���Y�]��)
        //���[�h�ΏۂƂȂ�������P�ʃe�[�u���s�I�u�W�F�N�g�̃��X�g���擾����B 
        //[����] 
        //���Y�]�� = this
        List l_lisBondOrdersAmountRows = 
            WEB3TPPersistentDataManager.getInstance().getBondOrdersAmount(this);    
        
        //1.2.(*)����t���[
        //get������<������>( )�̖߂�l != null�@@�̏ꍇ
        if (l_lisBondOrdersAmountRows != null)
        {
            //1.2.1.(*)��LOOP�����Fget������<������>()�̖߂�l�̗v�f����
            int l_intSize = l_lisBondOrdersAmountRows.size();
            for (int i = 0 ; i < l_intSize; i ++)
            {
                //1.2.1.1.create������(BondOrderUnitRow)
                //������I�u�W�F�N�g���擾����B 
                //[����] 
                //BondOrderUnitRow = (*)get������<������>()�̖߂�l�̗v�f�I�u�W�F�N�g 
                WEB3TPTransactionReflector l_tpTransactionReflector = 
                    createWEB3TPTransactionReflector(
                        (BondOrderUnitRow)l_lisBondOrdersAmountRows.get(i));
                
                //1.2.1.2.add������<����>(������)
                //�擾����������I�u�W�F�N�g���Athis.������ꗗ<����>�ɒǉ�����B 
                //[����] 
                //������ = (*)create������()�̖߂�l 
                addTodaysTransaction(l_tpTransactionReflector);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create������) <BR>
     * <BR>
     * ����.�m�������ׂ��A������I�u�W�F�N�g���쐬���ԋp����B <BR>
     * <BR>
     * ���V�[�P���X�}�u(������)create������(FixedFinTransactioRow)�v�Q�� <BR>
     * <BR>
     * @@param l_row - (�m��������)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40D91576037D
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        FixedFinTransactionRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(FixedFinTransactionRow)";
        log.entering(STR_METHOD_NAME);
        
        FinTransactionType l_tranType = l_row.getFinTransactionType();
        
        //1.1.(*)"����萔��"�A"���ϐ���"�A"�������"�A"���ϑ��"���v�Z����B
        //"����萔��" = 0
        //"�������" = 0
        //"���ϐ���" = ����.�m��������.��萔��
        //"���ϑ��" = ����.�m��������.��
        //����萔�ʁi�m��Ȃ̂�0�j
        double l_dblUnexecutedQuantity = 0;
        //��萔��
        double l_dblExecutedQuantity = l_row.getQuantity();
        //��������i�m��Ȃ̂�0�j
        double l_dblUnexecutedAmount = 0;
        //���ϑ��
        double l_dblExecutedAmount = l_row.getNetAmount();
        
        Date l_tranDate = l_row.getFinTransactionTimestamp();
        
        //1.2.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //������I�u�W�F�N�g�𐶐�����B
        //[����]
        //�]�͌v�Z���� = this.get�]�͌v�Z����()
        //ProductTypeEnum = ����.�m��������.get�����^�C�v()
        //long = ����.�m��������.get����ID()
        //FinTransactionType = ����.�m��������.get�g�����U�N�V�����^�C�v()
        //Date = ����.�m��������.get�g�����U�N�V������������()
        //double = "����萔��"
        //double = "�������"
        //double = "���ϐ���"
        //double = "���ϑ��"
        //Date = ����.�m��������.get��n��()
        //TaxTypeEnum = ����.�m��������.get�ŋ敪()
        log.exiting(STR_METHOD_NAME);
        return WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_row.getDeliveryDate(),
                l_row.getTaxType()
        );
    }
    
    /**
     * (create������)<BR>
     *<BR>
     * ����.�I�v�V�����������A������I�u�W�F�N�g���쐬���ԋp����B<BR> 
     *<BR>
     * ���V�[�P���X�}�u(������)create������(IfoOrderUnitRow)�v�Q��<BR> 
     * <BR>
     * @@param l_row - (�I�v�V��������)
     * @@return WEB3TPTransactionReflector[]
     * @@roseuid 40DFB7F601AF
     */
    protected WEB3TPTransactionReflector[] createWEB3TPTransactionReflector(
        IfoOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(IfoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.(*)"����萔��"�A"���ϐ���"���v�Z����B
        double l_dblUnexecutedQuantity, l_dblExecutedQuantity;
        //�������,���ϑ��
        double l_dblUnexecutedAmount, l_dblExecutedAmount;
        
        //���ʂ̎擾
        
        //"����萔��" = (*1)
        //"���ϐ���" = ����.�I�v�V��������.get��萔��()
        //(*1)
        //[a.����.�I�v�V��������.get�����L�����() == 2:�N���[�Y �̏ꍇ]
        //�@@"����萔��" = 0
        //[a.�ȊO�̏ꍇ]
        //�@@"����萔��" =�@@����.�I�v�V��������.get��������() - ����.�I�v
        if(OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = 0.0d;
        }
        else
        {
            //�s��m�F�ϒl�Ή�
            l_dblUnexecutedQuantity = l_row.getQuantity() - l_row.getExecutedQuantity();
        }
        
        l_dblExecutedQuantity = l_row.getExecutedQuantity();
        
        //1.2.get���v����(FinTransactionType)
        //�g�����U�N�V�����^�C�v�ʂ̑��v�������擾����B
        //[����]
        //FinTransactionType = ����.�I�v�V��������.get�������().toFinTransactionType()�̖߂�l
        //����̎擾
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        Date l_deliveryDate = l_row.getDeliveryDate();
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.3.get���ώ�n������v(IfoOrderUnitRow)
        //�I�v�V���������P�ʂɕR�t����n������W�v����B
        //[����]
        //IfoOrderUnitRow = ����.�I�v�V��������
        //�S���̏ꍇ
        //1.4.(*)����t���[
        //�S�����̏ꍇ
        //("����萔�� == 0")
        if(l_dblUnexecutedQuantity == 0)
        {
            //1.4.1.(*)"�������"�A"���ϑ��"���v�Z����B
            //"�������" = 0
            //"���ϑ��" = get���ώ�n������v()�̖߂�l
            l_dblUnexecutedAmount = 0;
            l_dblExecutedAmount = getNetAmountTotal(l_row);                        
        }
        
        //1.5.(*)����t���[
        //(*)����t���[
        //�ȊO�i�ꕔ���E�����j�̏ꍇ
        //����肠�邢�͈ꕔ���̏ꍇ
        else
        {
            double l_dblEstimatedPrice = l_row.getEstimatedPrice();           
            
            //��蕪�i�I�v�V�����g�����U�N�V����.��n����j�������ϑ���@@
            l_dblExecutedAmount = getNetAmountTotal(l_row);

            //1.5.1.(*)"�������"�A"���ϑ��"���v�Z����B
            //"���ϑ��" = get���ώ�n������v()�̖߂�l
            //[a.�I�v�V�����V�K�����̏ꍇ]
            //�i����.�I�v�V��������.get�������().toFinTransactionType() == 605�FOP�V�K���������j
            //�@@"�������" = (����.�I�v�V��������.get�T�Z��n���() - ABS("���ϑ��")) * get���v����()�̖߂�l
            if(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN.equals(l_tranType))
            {
                
                l_dblUnexecutedAmount = 
                    (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice * l_intCashDir :
                        (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount)) * l_intCashDir;
                
                
                //�]��2���Ή��ۗ����Č�
                /*                //�o���I����̏ꍇ 
                 if(getCalcCondition().isOptionExecutionDiv())
                 {
                 Date l_datT0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);                                                       
                 
                 //�����J�z�������̌J�z�����̏ꍇ
                  //�i���������@@T+0�@@���@@�����������@@���@@�������j
                   //�����J�z�����ώ��Ɠ��l�̍S�����s��
                    
                    List l_tranRefs = new ArrayList();
                    
                    if((WEB3DateUtility.compareToDay(l_datT0, l_tranDate) == 0) && 
                    (WEB3DateUtility.compareToDay(l_row.getExpirationDate(), l_tranDate) > 0))
                    {
                    //�ꕔ���̏ꍇ
                     //������蕪�̎������I�u�W�F�N�g�𐶐�����
                      if(l_dblExecutedQuantity > 0) 
                      {                                                  
                      WEB3TPTransactionReflector l_todayExecTranRef = 
                      WEB3TPTransactionReflector.create
                      (
                      getCalcCondition(),
                      l_row.getProductType(),
                      l_row.getProductId(),
                      l_tranType,
                      l_tranDate,            
                      0, 0,//��������͌J�z�������Ƃ��Čv�シ��̂�0�Ƃ���,
                      l_dblExecutedQuantity, l_dblExecutedAmount,//�������ϑ���𔽉f,
                      l_deliveryDate//��n��
                      );
                      
                      l_tranRefs.add(l_todayExecTranRef);
                      }
                      
                      
                      //�J�z����関��蕪�̎������I�u�W�F�N�g�����z�I�ɐ�������
                       //��n���̎擾===�]�͍X�V�œ���I�Ɏ�n���v�Z��Utility���R�[������悤�ɏC���\��
                        //�������I�v�V�����͑��̂Ƃ���ŌĂ΂�Ȃ��̂ł���΂��̂܂܂̎�����OK
                         //������������
                          
                          WEB3TPTransactionReflector l_carryOverTranRef =  
                          WEB3TPTransactionReflector.create
                          (
                          getCalcCondition(),
                          l_row.getProductType(),
                          l_row.getProductId(),
                          l_tranType,
                          l_tranDate,            
                          l_dblUnexecutedQuantity, l_dblUnexecutedAmount,//��������̂ݔ��f
                          0, 0,//���ϑ����0,
                          getCalcCondition().rollBizDate(l_deliveryDate, 1)//��n���͈����փV�t�g
                          );
                          
                          l_tranRefs.add(l_carryOverTranRef);
                          
                          return (WEB3TPTransactionReflector[])l_tranRefs.toArray(new WEB3TPTransactionReflector[l_tranRefs.size()]);
                          
                          }
                          
                          }
                          */            
            }
            //[a.�ȊO�̏ꍇ]
            //�@@"�������" = 0
            else
            {
                l_dblUnexecutedAmount = 0.0d;
            } 
            
        }          
        //1.6.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //������I�u�W�F�N�g�𐶐�����B
        //[����]
        //�]�͌v�Z���� = this.get�]�͌v�Z����()
        //ProductTypeEnum = ����.�I�v�V��������.get�����^�C�v()
        //long = ����.��������.get����ID()
        //FinTransactionType = ����.�I�v�V��������.get�������().toFinTransactionType()
        //Date = ����.�I�v�V��������.get������()
        //double = �v�Z����"����萔��"
        //double = �v�Z����"�������"
        //double = �v�Z����"���ϐ���"
        //double = �v�Z����"���ϑ��"
        //Date = ����.�I�v�V��������.get��n��()
        //TaxTypeEnum = ����.�I�v�V��������.get�ŋ敪()
        log.exiting(STR_METHOD_NAME);
        return new WEB3TPTransactionReflector[]{
                WEB3TPTransactionReflector.create
                (
                        getCalcCondition(),
                        l_row.getProductType(),
                        l_row.getProductId(),
                        l_tranType,
                        l_tranDate,            
                        l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                        l_dblExecutedQuantity, l_dblExecutedAmount,
                        l_deliveryDate,
                        l_row.getTaxType()
                )};
    }
    
    /**
     * (create������)<BR>
     *<BR>
     * ����.���M�����iMutualFundOrderUnitRow�j�����������쐬����B<BR> 
     *<BR>
     * ���V�[�P���X�}�u(������)create������(MutualFundOrderUnitRow)�v�Q��<BR> 
     * <BR>
     * @@param l_row - (���M����)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40DFB7F700A5
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        MutualFundOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(MutualFundOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get���v����(FinTransactionType)
        //�g�����U�N�V�����^�C�v�ʂ̑��v�������擾����B
        //[����]
        //FinTransactionType = ����.���M����.get�������().toFinTransactionType()�̖߂�l
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        
        //1.2.(*)"����萔��"�A"���ϐ���"�A"�������"�A"���ϑ��"���v�Z����B
        //����萔��
        double l_dblUnexecutedQuantity = 0;
        
        //�������
        double l_dblUnexecutedAmount = 0;
        
        //��萔��
        double l_dblExecutedQuantity = 0;
        
        //���ϑ��
        double l_dblExecutedAmount = 0;
        
        //[a.���M���t�̏ꍇ]
        //    �i����.���M����.get�������().toFinTransactionType() == 302�F���M���t�j
        //
        //   �@@"����萔��" = 0
        //   �@@"�������" = 0
        //   �@@"���ϐ���" = "����"(*1) - ����.���M����.���ϐ���
        //   �@@"���ϑ��" = (����.���M����.�T�Z��n���z - ����.���M����.���v�����) * get���v����()�̖߂�l
        if(FinTransactionType.EQTYPE_MF_SELL.equals(l_tranType))
        {
            //����萔��
            l_dblUnexecutedQuantity = 0;
            
            //�������
            l_dblUnexecutedAmount = 0;
            
            //��萔��
            l_dblExecutedQuantity =
                l_row.getConfirmedQuantityIsNull() ? 
                    (l_row.getQuantity() - l_row.getExecutedQuantity()):
                        (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
                        
            //���ϑ��
            l_dblExecutedAmount = (l_row.getEstimatedPrice() - l_row.getExecutedAmount()) * l_intCashDir;
        }
        //���M���t OR ���M��W�̏ꍇ
        //[a.�ȊO�i���M���tor���M��W�j�̏ꍇ]
        //�@@"����萔��" = "����"(*1) - ����.���M����.���ϐ���
        //�@@"�������" = (����.���M����.�T�Z��n���z - ����.���M����.���v�����) * get���v����()�̖߂�l
        //�@@"���ϐ���" = ����.���M����.���ϐ��ʁi�l��0�j
        //�@@"���ϑ��" = ����.���M����.���v������i�l��0�j
        //(*1)
        //[����.���M����.�s�ꂩ��m�F�ς݂̐��� != null �̏ꍇ]
        //�@@"����" = ����.���M����.�s�ꂩ��m�F�ς݂̐���
        //[�ȊO �̏ꍇ]
        //�@@"����" = ����.��������
        else
        {
            //����萔��
            l_dblUnexecutedQuantity =
                l_row.getConfirmedQuantityIsNull() ? 
                    (l_row.getQuantity() - l_row.getExecutedQuantity()):
                        (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
                    
            //�������
            l_dblUnexecutedAmount = (l_row.getEstimatedPrice() - l_row.getExecutedAmount()) * l_intCashDir;
            
            //��萔�ʁi0�j
            l_dblExecutedQuantity = l_row.getExecutedQuantity();
            
            //���ϑ���i0�j
            l_dblExecutedAmount = l_row.getExecutedAmount();
        }
        
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        
        Date l_deliveryDate = null;
        
        //1.3.(*)"��n��"���v�Z����B
        //���M��W�̏ꍇ�A����������n���Ƃ݂Ȃ�
        //[a.���M��W�̏ꍇ]
        //�i����.���M����.get�������().toFinTransactionType() == 303�F���M��W�j
        //�@@[b.����.���M����.������ == null �̏ꍇ]
        //�@@�@@"��n��" = T+0(*1)
        //�@@[b.�ȊO �̏ꍇ]
        //�@@�@@"��n��" = ����.���M����.������
        if(FinTransactionType.EQTYPE_MF_RECRUIT.equals(l_tranType))
        {
            //���������擾
            Date l_datPayment = l_row.getPaymentDate();
            
            //��������null�̏ꍇ
            if(l_datPayment == null)
            {
                //�c�Ɠ�(T+0)����n���Ƃ���
                l_deliveryDate = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
            }
            else
            {
                l_deliveryDate = l_datPayment;
            }
        }
        //[a.�ȊO�̏ꍇ]
        //�@@�@@"��n��" = ����.���M����.��n��
        //(*1)
        //T+0 = this.get�]�͌v�Z����().get�c�Ɠ�(:int = 0)
        else
        {
            l_deliveryDate = l_row.getDeliveryDate();
        }
        
        //1.4.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //������I�u�W�F�N�g�𐶐�����B
        //[����]
        //�]�͌v�Z���� = this.get�]�͌v�Z����()
        //ProductTypeEnum = ����.���M����.get�����^�C�v()
        //long = ����.���M����.get����ID()
        //FinTransactionType = ����.���M����.get�������().toFinTransactionType()
        //Date = ����.���M����.get������()
        //double = "����萔��"
        //double = "�������"
        //double = "���ϐ���"
        //double = "���ϑ��"
        //Date = "��n��"
        //TaxTypeEnum = ����.���M����.get�ŋ敪()
        //1.5.(*)�ԋp�l�Fcreate������()�̖߂�l
        log.exiting(STR_METHOD_NAME);
        return WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_deliveryDate,
                l_row.getTaxType()
        );
    }
    
    /**
     * (create������)<BR>
     *<BR>
     * ����.GP�������A������I�u�W�F�N�g���쐬���ԋp����B<BR> 
     *<BR>
     * ���V�[�P���X�}�u(������)create������(RuitoOrderUnitRow)�v�Q��<BR> 
     *<BR>
     * @@param l_row GP����
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        RuitoOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(RuitoOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get���v����(FinTransactionType)
        //�g�����U�N�V�����^�C�v�ʂ̑��v�������擾����B
        //[����]
        //FinTransactionType = ����.GP����.get�������().toFinTransactionType()�̖߂�l
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.2.(*)"����萔��"�A"���ϐ���"�A"�������"�A"���ϑ��"���v�Z����B
        //����萔��
        //"����萔��" = (*1)
        //"�������" = 0
        //"���ϐ���" = ����.GP����.��萔��
        //"���ϑ��" = "����萔��" * get���v����()�̖߂�l
        //(*1)
        //[����.GP����.�s�ꂩ��m�F�ς݂̐��� == null]
        //�@@"����萔��" = ����.GP����.��������
        //[�ȊO�̏ꍇ]
        //�@@"����萔��" = ����.GP����.�s�ꂩ��m�F�ς݂̐��� - ����.GP����.��萔��
        double l_dblUnexecutedQuantity =
            l_row.getConfirmedQuantityIsNull() ? l_row.getQuantity() :
                (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
            
            //��萔��(0�̂͂�)
            double l_dblExecutedQuantity = l_row.getExecutedQuantity();
            
            //���������0�Ƃ���
            double l_dblUnexecutedAmount = 0;
            
            //����萔��(=���z)����ϑ���Ƃ��Čv��
            double l_dblExecutedAmount = l_dblUnexecutedQuantity * l_intCashDir;
            
            Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            
            //1.3.create������(�]�͌v�Z����, ProductTypeEnum, long, 
            //FinTransactionType, Date, double, double, double, double, Date, TaxTypeEnum)
            //������I�u�W�F�N�g�𐶐�����B
            //[����]
            //�]�͌v�Z���� = this.get�]�͌v�Z����()
            //ProductTypeEnum = ����.GP����.get�����^�C�v()
            //long = ����.GP����.get����ID()
            //FinTransactionType = ����.GP����.get�������().toFinTransactionType()
            //Date = ����.GP����.get������()
            //double = "����萔��"
            //double = "�������"
            //double = "���ϐ���"
            //double = "���ϑ��"
            //Date = ����.GP����.get��n��()
            //TaxTypeEnum = ����.GP����.get�ŋ敪()
            
            log.exiting(STR_METHOD_NAME);
            return WEB3TPTransactionReflector.create
            (
                    getCalcCondition(),
                    l_row.getProductType(),
                    l_row.getProductId(),
                    l_tranType,
                    l_tranDate,
                    l_dblUnexecutedQuantity, 
                    l_dblUnexecutedAmount,
                    l_dblExecutedQuantity, 
                    l_dblExecutedAmount,
                    l_row.getDeliveryDate(),
                    l_row.getTaxType()
            );
    }
    
    /**
     * (create������)<BR>
     *<BR>
     * ����.�O���������A������I�u�W�F�N�g���쐬���ԋp����B<BR>
     *<BR>
     * ���V�[�P���X�}�u(������)create������(FeqOrderUnitRow)�v�Q�� <BR>
     * <BR>
     * @@param l_row - (�O������)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40DFB7F702D8
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        FeqOrderUnitRow l_row)
    {   
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�O�������P��=" + l_row);
        
        //1.1.get���v����(FinTransactionType)
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.2.(*)"����萔��"�A"���ϐ���"���v�Z����B
        //����萔��
        double l_dblUnexecutedQuantity;
        
        //(*1)
        //[a.����.�O������.get�����L�����() == 2:�N���[�Y �̏ꍇ]
        //�@@"����萔��" = 0
        if(OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = 0.0d;
        }
        else
        {
            //[a.�ȊO�̏ꍇ]
            //�@@[b.����.�O������.�s�ꂩ��m�F�ς݂̐��� == null �̏ꍇ]
            //�@@�@@"����萔��" =�@@����.�O������.get��������()
            //�@@[b.�ȊO�̏ꍇ]
            //�@@�@@"����萔��" =�@@����.�O������.get�s�ꂩ��m�F�ς݂̐���() 
            l_dblUnexecutedQuantity = l_row.getConfirmedQuantityIsNull() ? l_row.getQuantity() :
                (l_row.getConfirmedQuantity() - l_row.getExecutedQuantity());
        }               
        
        //��萔��
        double l_dblExecutedQuantity = l_row.getExecutedQuantity();        
        
        log.debug("l_dblUnexecutedQuantity" + l_dblUnexecutedQuantity);
        log.debug("l_dblExecutedQuantity" + l_dblUnexecutedQuantity);
        
        
        //1.3.get���ώ�n������v(FeqOrderUnitRow)
        //�O�������P�ʂɕR�t����n������W�v����B
        //[����]
        //FeqOrderUnitRow = ����.�O������

        double l_dblExecutedAmount = this.getNetAmountTotal(l_row);        
        //�������
        double l_dblUnexecutedAmount = 0; 
        
        //(*)����t���[
        //�ȊO�i�ꕔ���E�����j�̏ꍇ

        //1.4.1.(*)"�������"�A"���ϑ��"���v�Z����B
        //[a.�O�����t�̏ꍇ]
        //�i����.�O������.get�������() == 701�F�O�����t�j
        if(OrderTypeEnum.FEQ_BUY.equals(l_row.getOrderType()))
        {
            //  [b.�ꕔ���܂��͖����̏ꍇ]
            //   �i"����萔��" > 0�j
            //  "�������" = (����.�O������.get�T�Z��n���() 
            //      - ABS("���ϑ��")) * get���v����()�̖߂�l
            //�@@[b.�ȊO�̏ꍇ]
            //�@@�@@"�������" = 0
            //[a.�ȊO�̏ꍇ]
            //�@@"�������" = 0
            if(l_dblUnexecutedQuantity > 0)
            {
                double l_dblEstimatedPrice = l_row.getEstimatedPrice();
                
                l_dblUnexecutedAmount = 
                    (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice * l_intCashDir :
                        (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount)) * l_intCashDir;                
                
                log.debug("l_dblEstimatedPrice=" + l_dblEstimatedPrice);
            }            
        }
        
        log.debug("l_dblUnexecutedAmount=" + l_dblUnexecutedAmount);
        log.debug("l_dblExecutedAmount=" + l_dblExecutedAmount);                
        
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        
        //1.5.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType,
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //������I�u�W�F�N�g�𐶐�����B
        //
        //[����]
        //�]�͌v�Z���� = this.get�]�͌v�Z����()
        //ProductTypeEnum = ����.�O������.get�����^�C�v()
        //long = ����.�O������.get����ID()
        //FinTransactionType = ����.�O������.get�������().toFinTransactionType()
        //Date = ����.�O������.get������()
        //double = �v�Z����"����萔��"
        //double = �v�Z����"�������"
        //double = �v�Z����"���ϐ���"
        //double = �v�Z����"���ϑ��"
        //Date = ����.�O������.get��n��()
        //TaxTypeEnum = ����.�O������.get�ŋ敪()
        log.exiting(STR_METHOD_NAME);
        return  WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,            
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_row.getDeliveryDate(),
                l_row.getTaxType()
        );
    }
    
    /**
     * (create������) <BR>
     *<BR>
     * ����.���o���������A��������쐬���ԋp����B <BR>
     *<BR>
     * �V�[�P���X�}�u(������)create������(AioOrderUnitRow)�v�Q�� <BR>
     * <BR>
     * @@param l_orderUnitRow - (���o������)
     * @@return WEB3TPTransactionReflector
     * @@roseuid 40DFB7F800C5
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        AioOrderUnitRow l_orderUnitRow)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(AioOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get���v����(FinTransactionType)
        FinTransactionType l_tranType = l_orderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //1.2.(*)"����萔��"�A"���ϐ���"���v�Z����
        //����萔��
        double l_dblUnexecutedQuantity = 0;
        //��萔��
        double l_dblExecutedQuantity = 0;
        
        //[a.����.���o������.get�����L�����() == "1�F�I�[�v��"�̏ꍇ]
        //�@@"����萔��" = ����.���o������.get��������()
        //�@@"���ϐ���" = 0�@@�@@

        if (OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus()))
        {
            l_dblUnexecutedQuantity = l_orderUnitRow.getQuantity();
            l_dblExecutedQuantity = 0;
        }
        //[a.�ȊO�̏ꍇ]
        //�@@"����萔��" = 0
        //�@@"���ϐ���" = ���o������.get��������()�@@
        else
        {
            l_dblUnexecutedQuantity = 0;
            l_dblExecutedQuantity = l_orderUnitRow.getQuantity();
            
        }
        
        
        //1.3.(*)"�������"�A"���ϑ��"���v�Z����B
        //"�������" = 0 
        //"���ϑ��" = ����.���o������.get��������() * get���v����()
        double l_dblUnexecutedAmount = 0;
        double l_dblExecutedAmount = l_orderUnitRow.getQuantity() * l_intCashDir;
        Date l_tranDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        
        //1.4.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //������I�u�W�F�N�g�𐶐�����B
        //[����]
        //�]�͌v�Z���� = this.get�]�͌v�Z����()
        //ProductTypeEnum = ����.���o������.get�����^�C�v()
        //long = ����.���o������.get����ID()
        //FinTransactionType = ����.���o������.get�������().toFinTransactionType()
        //Date = ����.���o������.get������()
        //double = "����萔��"
        //double = "�������"
        //double = "���ϐ���"
        //double = "���ϑ��"
        //Date = ����.���o������.get��n��()
        //TaxTypeEnum = ����.���o������.get�ŋ敪()
        //1.5.(*)�ԋp�l�Fcreate������()�̖߂�l
        log.exiting(STR_METHOD_NAME);
        return WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_orderUnitRow.getProductType(),
                l_orderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity, l_dblUnexecutedAmount,
                l_dblExecutedQuantity, l_dblExecutedAmount,
                l_orderUnitRow.getDeliveryDate(),
                l_orderUnitRow.getTaxType()
        );
    }
    
    /**
     * (create������)<BR> 
     * <BR>
     * ����.�������iBondOrderUnitRow�j�����������쐬����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(������)create������(BondOrderUnitRow)�v�Q�� <BR>
     * <BR>
     * @@param l_bondOrderUnitRow - (������)
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
        BondOrderUnitRow l_bondOrderUnitRow)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(BondOrderUnitRow)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.get���v����(FinTransactionType)
        //�g�����U�N�V�����^�C�v�ʂ̑��v�������擾����B
        //[����]
        //FinTransactionType = ����.������.get�������().toFinTransactionType()�̖߂�l
        FinTransactionType l_tranType = l_bondOrderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);
        
        //����萔��
        double l_dblUnexecutedQuantity = 0.0d;
        
        //��萔��
        double l_dblExecutedQuantity = 0.0d;
        
        //������� = 0
        double l_dblUnexecutedAmount = 0.0d;
        
        // �@@"���ϑ��" = 0
        double l_dblExecutedAmount = 0.0d;
        
        //1.2.(*)����t���[
        //�����̏ꍇ
        //�i����.������.get�������敪() == 0�F�����j
        if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()))
        {
            //1.2.1.(*)"����萔��"�A"���ϐ���"�A"�������"�A"���ϑ��"���v�Z����B
            //[a.�����������̏ꍇ]
            // �i����.������.get�������() == 401�F�����������j
            // �@@"����萔��" = ����.������.get��������()
            // �@@"�������" = ����.������.get��n���(�~��)() * get���v����()�̖߂�l
            // �@@"���ϐ���" = 0
            // �@@"���ϑ��" = 0
            if (OrderTypeEnum.BOND_BUY.equals(l_bondOrderUnitRow.getOrderType()))
            {
                l_dblUnexecutedQuantity = l_bondOrderUnitRow.getQuantity();
                l_dblUnexecutedAmount = l_bondOrderUnitRow.getEstimatedPrice() * l_intCashDir;
            }
            // [a.�ȊO�i�����蒍���j�̏ꍇ]
            // �@@"����萔��" = 0
            // �@@"�������" = 0
            // �@@"���ϐ���" = 0
            // �@@"���ϑ��" = 0
        }
        
        //1.3.(*)����t���[
        //���ς̏ꍇ
        //�i����.������.get�������敪() == 1�F���ρj
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()))
        {
            //1.3.1.(*)"����萔��"�A"���ϐ���"�A"�������"�A"���ϑ��"���v�Z����B
            //"����萔��" = 0
            //"�������" = 0
            //"���ϐ���" = ����.������.get��萔��()
            //"���ϑ��" = ����.������.get��n���(�~��)() * get���v����()�̖߂�l
            l_dblExecutedQuantity = l_bondOrderUnitRow.getExecutedQuantity();
            l_dblExecutedAmount = l_bondOrderUnitRow.getEstimatedPrice() * l_intCashDir;
        }
        
        //1.4.(*)"��n��"���v�Z����B
        //[a.��W����̏ꍇ]
        // �i����.������.get�������() == 401�F����������
        // �@@�@@&&
        // �@@�@@�@@����.������.get���() == 35:��W����j
        Date l_deliveryDate = null;
        if (OrderTypeEnum.BOND_BUY.equals(l_bondOrderUnitRow.getOrderType()) 
            && WEB3DealTypeDef.RECRUIT_TRADING.equals(l_bondOrderUnitRow.getDealType()))
        {
            // �@@"��n��" = ����.������.get������()
            l_deliveryDate = l_bondOrderUnitRow.getPaymentDate();
        }
        
        // [a.�ȊO�̏ꍇ]
        // �@@"��n��" = ����.������.get��n��()
        else
        {
            l_deliveryDate = l_bondOrderUnitRow.getDeliveryDate();
        }
        
        Date l_tranDate = WEB3DateUtility.getDate(l_bondOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        //1.5.create������(�]�͌v�Z����, ProductTypeEnum, long, FinTransactionType, 
        //Date, double, double, double, double, Date, TaxTypeEnum)
        //������I�u�W�F�N�g�𐶐�����B
        //[����]
        //�]�͌v�Z���� = this.get�]�͌v�Z����()
        //ProductTypeEnum = ����.������.get�����^�C�v()
        //long = ����.������.get����ID()
        //FinTransactionType = ����.������.get�������().toFinTransactionType()
        //Date = ����.������.get������()
        //double = "����萔��"
        //double = "�������"
        //double = "���ϐ���"
        //double = "���ϑ��"
        //Date = "��n��"
        //TaxTypeEnum = ����.������.get�ŋ敪()
        WEB3TPTransactionReflector l_tpTransaction =  WEB3TPTransactionReflector.create
        (
            getCalcCondition(),
            l_bondOrderUnitRow.getProductType(),
            l_bondOrderUnitRow.getProductId(),
            l_tranType,
            l_tranDate,
            l_dblUnexecutedQuantity, 
            l_dblUnexecutedAmount,
            l_dblExecutedQuantity, 
            l_dblExecutedAmount,
            l_deliveryDate,
            l_bondOrderUnitRow.getTaxType()
        );
        
        //1.6.(*)�ԋp�l�Fcreate������()�̖߂�l
        log.exiting(STR_METHOD_NAME);
        return l_tpTransaction;
    }

    /**
     * (create������)<BR> 
     * <BR>
     * ����.IPO�\���iIpoOrderRow�j�����������쐬����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(������)create������(IpoOrderRow,IpoProductRow)�v�Q�� <BR>
     * <BR>
     * @@param l_ipoOrderRow - (IPO�\��)
     * @@param l_ipoProductRow - (IPO�������)
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
            IpoOrderRow l_ipoOrderRow,IpoProductRow l_ipoProductRow)
    {
        final String STR_METHOD_NAME = "createWEB3TPTransactionReflector(IpoOrderRow,IpoProductRow)";
        log.entering(STR_METHOD_NAME);
        
        //�����̎擾
        Date l_datT0 = getCalcCondition().getBizDate(0);
        //T+5�̎擾
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(FinTransactionType.OTHER);

        //1.1 (*)"����萔��","���ϐ���"���v�Z����B
        //����萔��
        double l_dblUnexecutedQuantity = 0.0d;
        
        //��萔��
        double l_dblExecutedQuantity = 0.0d;
        
        //������� = 0
        double l_dblUnexecutedAmount = 0.0d;
        
        //���ϑ�� = 0
        double l_dblExecutedAmount = 0.0d;
    	
        //�������̏ꍇ
        //�i����.IPO����.get��t���() == 0�F�������j
        // �@@"����萔��" = ����.IPO����.get�w���\������()
        // �@@"�������" = ����.IPO����.get�w���\������() * ����.IPO����.get�w���\�����()
        // �@@"���ϐ���" = 0
        // �@@"���ϑ��" = 0
        if (WEB3TPIPOHostAcceptStatusDef.DEFAULT.equals(l_ipoOrderRow.getAcceptStatus()))
        {
        	l_dblUnexecutedQuantity = l_ipoOrderRow.getApplicationQuantity();
        	l_dblUnexecutedAmount = l_ipoOrderRow.getPayAmount() * l_intCashDir;
        	l_dblExecutedQuantity = 0.0d;
        	l_dblExecutedAmount = 0.0d;
        }
        //SONAR��t�ς̏ꍇ
        //�i����.IPO����.get��t���() == 0�F�������j
        // �@@"����萔��" = 0
        // �@@"�������" = 0
        // �@@"���ϐ���" = ����.IPO����.get�w���\������()
        // �@@"���ϑ��" = ����.IPO����.get�w���\������() * ����.IPO����.get�w���\�����()
        else if(WEB3TPIPOHostAcceptStatusDef.ACCEPTED.equals(l_ipoOrderRow.getAcceptStatus()))
        {
        	l_dblUnexecutedAmount = 0.0d;
        	l_dblUnexecutedQuantity = 0.0d;
        	l_dblExecutedQuantity = l_ipoOrderRow.getApplicationQuantity();
        	l_dblExecutedAmount = l_ipoOrderRow.getPayAmount() * l_intCashDir;
        }
        
        //��n���̎Z�o
        Date l_dblOfferEndDateProspec = l_ipoProductRow.getOfferEndDateProspec();

        //��n����T+5�ȍ~�̏ꍇ�͎�n����T+5�ɐݒ肷��B
        if(WEB3DateUtility.compareToDay(l_datT5, l_dblOfferEndDateProspec) < 0)
        {
        	l_dblOfferEndDateProspec = l_datT5;
        }

    	WEB3TPTransactionReflector l_tpTransaction =  WEB3TPTransactionReflector.create
        (
                getCalcCondition(),
                l_ipoProductRow.getProductType(),
                l_ipoOrderRow.getIpoProductId(),
                FinTransactionType.OTHER,
                l_datT0,
                l_dblUnexecutedQuantity, 
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity, 
                l_dblExecutedAmount,
                l_dblOfferEndDateProspec,
                l_ipoOrderRow.getTaxType()
        );
        
        //1.6.(*)�ԋp�l�Fcreate������()�̖߂�l
        log.exiting(STR_METHOD_NAME);
        return l_tpTransaction;    		
    }
    
    /**
     * (get���ώ�n������v)<BR>
     * ����.���������ɑ΂��銔���g�����U�N�V�����̎�n��������v���ԋp����B<BR>
     * @@param l_row�@@��������
     * @@return double
     */
    protected double getNetAmountTotal(EqtypeOrderUnitRow l_row)
    {   
        double l_dblExecutedAmount = 0.0d;
        
        //�����̏ꍇDB�������Ȃ��B
        if(l_row.getExecutedQuantity() == 0)
        {
            return l_dblExecutedAmount;
        }
        
        List l_finTranRows = WEB3TPPersistentDataManager.getInstance().getEqtypeFinTransactions(l_row);
        if (l_finTranRows != null)
        {
            for (Iterator l_iter = l_finTranRows.iterator(); l_iter.hasNext(); )
            {
                EqtypeFinTransactionRow l_finTranRow = (EqtypeFinTransactionRow) l_iter.
                next();
                l_dblExecutedAmount += l_finTranRow.getNetAmount();
                
            }
        }
        
        return l_dblExecutedAmount;
        
    }
    
    /**
     * (get���ώ�n������v)<BR>
     * ����.�I�v�V���������ɑ΂���I�v�V�����g�����U�N�V�����̎�n��������v���ԋp����B<BR>
     * @@param l_row�@@�I�v�V��������
     * @@return double
     */
    protected double getNetAmountTotal(IfoOrderUnitRow l_row)
    {
        double l_dblExecutedAmount = 0.0d;
        
        //�����̏ꍇDB�������Ȃ��B
        if(l_row.getExecutedQuantity() == 0)
        {
            return l_dblExecutedAmount;
        }
        
        List l_finTranRows = WEB3TPPersistentDataManager.getInstance().getIfoFinTransactions(l_row);
        if (l_finTranRows != null)
        {
            for (Iterator l_iter = l_finTranRows.iterator(); l_iter.hasNext(); )
            {
                IfoFinTransactionRow l_finTranRow = (IfoFinTransactionRow) l_iter.next();
                l_dblExecutedAmount += l_finTranRow.getNetAmount();
            }
        }
        return l_dblExecutedAmount;
        
    }
    
    /**
     * (get���ώ�n������v)<BR>
     * ����.�O�������ɑ΂���O���g�����U�N�V�����̎�n��������v���ԋp����B<BR>
     * @@param l_row�@@�O������
     * @@return double
     */
    protected double getNetAmountTotal(FeqOrderUnitRow l_row)
    {
        double l_dblExecutedAmount = 0.0d;
        
        //�����̏ꍇDB�������Ȃ��B
        if(l_row.getExecutedQuantity() == 0)
        {
            return l_dblExecutedAmount;
        }
        
        List l_finTranRows = WEB3TPPersistentDataManager.getInstance().getFeqFinTransactions(l_row);
        if (l_finTranRows != null)
        {
            for (Iterator l_iter = l_finTranRows.iterator(); l_iter.hasNext(); )
            {
                FeqFinTransactionRow l_finTranRow = (FeqFinTransactionRow) l_iter.next();
                l_dblExecutedAmount += l_finTranRow.getNetAmount();
            }
        }
        return l_dblExecutedAmount;
        
    }

    /**
     * (calc�����o���z)<BR>
     * �����̏o���z���W�v���ԋp���� <BR>
     * <BR>
     * �P�j�o�������ꗗ<�]�͌v�Z���ʏڍחp>���A���ϑ�����W�v����B <BR>
     * <BR>
     * �@@<LOOP�����Fthis.�o�������ꗗ<�]�͌v�Z���ʏڍחp>�̗v�f����> <BR>
     * <BR>
     * �@@�@@�@@�o�������ꗗ<�]�͌v�Z���ʏڍחp>���A������I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�@@-this.�o�������ꗗ<�]�͌v�Z���ʏڍחp>.get(index)���R�[�� <BR>
     * �@@�@@ <BR>
     * �@@�@@�A�擾����������I�u�W�F�N�g�����ϑ�����W�v����B <BR>
     * <BR>
     * �@@�@@�@@[������I�u�W�F�N�g.get�g�����U�N�V�����^�C�v() = "20�F�o��" ���� <BR>
     * �@@�@@�@@������I�u�W�F�N�g.get��n��() = T+1�@@ ���� <BR>
     * �@@�@@�@@������I�u�W�F�N�g.get�g�����U�N�V����������()�@@= T+0 <BR>
     * �@@�@@�@@�̏ꍇ] <BR>
     * �@@�@@�@@�@@ <BR>
     * �@@�@@�@@�@@���ϑ���W�v += ������I�u�W�F�N�g.get���ϑ��() <BR>
     * <BR>
     * <BR>
     * �Q�j�W�v�������ϑ����ԋp����B <BR>
     * @@return double
     */
    public double clacNextBizDateCashoutAmount()
    {
        final String STR_METHOD_NAME = "clacNextBizDateCashoutAmount()";
        log.entering(STR_METHOD_NAME);
        double l_dblExecutedAmount = 0;
        //�P�j�o�������ꗗ<�]�͌v�Z���ʏڍחp>���A���ϑ�����W�v����B
        List l_lisDisplayCashOutTransactions = getDisplayCashOutTransactions();
        for (Iterator l_iter = l_lisDisplayCashOutTransactions.iterator(); l_iter.hasNext();)
        {
            //<LOOP�����Fthis.�o�������ꗗ<�]�͌v�Z���ʏڍחp>�̗v�f����>
            //-this.�o�������ꗗ<�]�͌v�Z���ʏڍחp>.get(index)���R�[��
            WEB3TPTransactionReflector l_transactionReflector = (WEB3TPTransactionReflector)l_iter.next();

            Date l_datBizDate1 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
            Date l_datBizDate0 = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
            //�A�擾����������I�u�W�F�N�g�����ϑ�����W�v����B
            //[������I�u�W�F�N�g.get�g�����U�N�V�����^�C�v() = "20�F�o��" ����
            //�@@�@@�@@������I�u�W�F�N�g.get��n��() = T+1�@@ ����
            //�@@�@@�@@������I�u�W�F�N�g.get�g�����U�N�V����������()�@@= T+0 �̏ꍇ]
            //�@@�@@�@@�@@���ϑ���W�v += ������I�u�W�F�N�g.get���ϑ��()
            if (FinTransactionType.DEBIT.equals(l_transactionReflector.getFinTransactionType())
                && WEB3DateUtility.compareToDay(
                    l_transactionReflector.getDeliveryDate(), l_datBizDate1) == 0
                && WEB3DateUtility.compareToDay(
                    l_transactionReflector.getFinTransactionDate(), l_datBizDate0) == 0)
            {
                l_dblExecutedAmount = l_dblExecutedAmount + l_transactionReflector.getExecutedAmount();
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblExecutedAmount;
    }
    
    /**
     * (calc�����ȍ~�a����ۏ؋��Ԃ̐U�֊z)<BR>
     * �A�C�e���̒�`<BR>
     * �a����ۏ؋��Ԃ̐U�֊z���W�v�����ʂ�ԋp����B <BR>
     * �P�j�@@�����ȍ~�U�֒������擾����B <BR>
     * �@@�����ȍ~�U�֒����E�E�E�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~�U�֒���(���Y�]��) <BR>
     * <BR>
     * [����] <BR>
     * ���Y�]�� = this <BR>
     * <BR>
     * �Q�j�@@���ϑ�����W�v����B <BR>
     *  �W�v�����F<BR>
     *  AIO�����P��Row.�⏕����ID = this.get�ڋq����.get�⏕����ID(�⏕�����^�C�v�F1:������������i�a����j) ���� <BR>
     *  AIO�����P��Row.��n�� <= �V�X�e�����t�i���c�Ɠ��j ���� <BR>
     *  AIO�����P��Row.�������.toFinTransactionType() in ( ( <BR>
     *  1005�F�U�֒���(�a�������M�p�ۏ؋�)�@@, <BR>
     *  1006�F�U�֒���(�M�p�ۏ؋�����a���)�@@) <BR>
     *  ���ϑ�� = ���ϑ�� + AIO�����P��Row.�������� * ���v����<BR>
     * <BR>
     *      (*)<BR>
     *      ���v�����@@= ������.get���v����(�U�փ^�C�v�FAIO�����P��Row.�U�փ^�C�v)<BR>
     *      AIO�����P��Row�@@= get�����ȍ~�U�֒���()�̖߂�l�̗v�f�I�u�W�F�N�g<BR>
     * <BR>
     * �R�j�@@���ϑ��(n)��ԋp����B<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcTodayMarginDepositTransferAmount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcTodayMarginDepositTransferAmount()";
        log.entering(STR_METHOD_NAME);
        double l_dblTodayMarginDepositTransferAmount = 0;
        //�P�j�@@�����ȍ~�U�֒������擾����B
        //�@@�����ȍ~�U�֒����E�E�E�]�̓f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�����ȍ~�U�֒���(���Y�]��)
        List l_lisTodaysTransactions =
            WEB3TPPersistentDataManager.getInstance().getTodaysCashTransferOrders(this);
        for (Iterator l_iter = l_lisTodaysTransactions.iterator(); l_iter.hasNext();)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_iter.next();
            //�Q�j�@@���ϑ�����W�v����B
            //���ϑ��(n) = ��(*)������.get���ϑ��()
            //�W�v�����F
            //AIO�����P��Row.�⏕����ID = this.get�ڋq����.get�⏕����ID(�⏕�����^�C�v�F1:������������i�a����j) ����
            //AIO�����P��Row.��n�� <= �V�X�e�����t�i���c�Ɠ��j ����
            //AIO�����P��Row.�������.toFinTransactionType() in (
            //1005�F�U�֒���(�a�������M�p�ۏ؋�)�@@,
            //1006�F�U�֒���(�M�p�ۏ؋�����a���)�@@)
            //���ϑ�� = ���ϑ�� + AIO�����P��Row.�������� * ���v����
            //���v�����@@= ������.get���v����(�U�փ^�C�v�FAIO�����P��Row.�U�փ^�C�v)
            //AIO�����P��Row�@@= get�����ȍ~�U�֒���()�̖߂�l�̗v�f�I�u�W�F�N�g
            Date l_datOrderDate =
                this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
            FinTransactionType l_finTransactionType =
                l_aioOrderUnitRow.getOrderType().toFinTransactionType();
            long l_lngSubAccountId =
                this.getAccountInfo().getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            if(l_lngSubAccountId == l_aioOrderUnitRow.getSubAccountId()
                && (FinTransactionType.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(l_finTransactionType)
                || FinTransactionType.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(l_finTransactionType))
                && WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(), l_datOrderDate) <= 0)
            {
                l_dblTodayMarginDepositTransferAmount =
                    l_dblTodayMarginDepositTransferAmount
                    + l_aioOrderUnitRow.getQuantity() * WEB3TPTransactionReflector.getCashDir(
                        l_aioOrderUnitRow.getTransferType());
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblTodayMarginDepositTransferAmount;
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
        .append("todaysTransactions", getTodaysTransactions())
        .append("fixedTransactions", getFixedTransactions())
        .append("mutualFundTransactions", getMutualFundTransactions())
        .append("displayCashOutTransactions", getDisplayCashOutTransactions())
        .appendSuper(super.toString())
        .toString();
    }
    
    
    public boolean isCashOutOverT5Loaded()
    {
        
        return true;        
    }
    
}@
