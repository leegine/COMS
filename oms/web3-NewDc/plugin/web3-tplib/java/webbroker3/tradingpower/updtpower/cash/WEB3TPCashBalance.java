head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashBalance.java;


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
Revision History : 2004/08/02 �x�� �a��(FLJ)  �V�K�쐬
                   2006/09/25 �Ԑi�@@   (���u) ���f��No.059
                   2006/09/25 �Ԑi�@@   (���u) ���f��No.060
                   2006/09/25 �Ԑi�@@   (���u) ���f��No.061  
Revision History : 2009/12/15 �����F ���f��No.409 410 429
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�a���)<BR>
 * �a����c���̐��ڂ�\������B<BR>
 */
public class WEB3TPCashBalance extends WEB3TPAssetValuation
{

    /** ���O�@@���[�e�B���e�B�@@*/
    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPCashBalance.class);

    /**
     * (�a����ꗗ<�m��>)<BR>
     */
    private List fixedCashBalances;

    /**
     * (�ߓ���������ꗗ)<BR>
     * <BR>
     * �ߓ���������I�u�W�F�N�g�̃��X�g<BR>
     */
    private List elapsedDayExecuteCancelInfoList;

    /**
     * @@roseuid 4104B09E0337
     */
    public WEB3TPCashBalance()
    {
        fixedCashBalances = new ArrayList();
        elapsedDayExecuteCancelInfoList = new ArrayList();
    }

    /**
     * (create�a���)<BR>
     * �a����C���X�^���X�𐶐����A����.����������e�l��ϐ��ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X�𐶐�<BR>
     * �Q�j�@@�l��ݒ�<BR>
     * �@@�ڋq��񁁈���.������.get�ڋq���()<BR>
     * �@@�v�Z����������.������.get�v�Z����()<BR>
     * �@@���������e������.������.get���������e()<BR> 
     * �R�j�@@�C���X�^���X��ԋp<BR>
     *
     * @@param l_valuation (������)
     * @@return WEB3TPCashBalance
     */
    public static WEB3TPCashBalance create(WEB3TPCashValuation l_valuation)
    {
        WEB3TPCashBalance l_instance = new WEB3TPCashBalance();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        return l_instance;
    }

    /**
     * (calc�a����c��)<BR>
     * <BR>
     * ����.�w����̗a����c����ԋp����B<BR>
     * <BR>
     * �P�j����.�w����̊m��a������擾����B<BR>
     * <BR>
     * �@@"�m��a���" = this.calc�m��a���(:Date = ����.�w���)<BR>
     * <BR>
     * �Q�j����.�w����̉ߓ������������擾����B<BR>
     * <BR>
     * �@@"�ߓ���������" = this.calc�ߓ���������(:Date = ����.�w���)<BR>
     * <BR>
     * �R�j"�a���"���v�Z����B<BR>
     * <BR>
     * �@@"�a���" = "�m��a���"�@@+�@@"�ߓ���������"<BR>
     * <BR>
     * �S�j�v�Z����"�a���"��ԋp����B<BR>
     * <BR>
     * �@@�ԋp�l�F"�a���"<BR>
     * <BR>
     * @@param l_datDate (�w���)
     * @@return double
     */
    public double calcCashBalance(Date l_datDate)
    {
        //"�m��a���" = this.calc�m��a���(:Date = ����.�w���)
        double l_dblFixedBalance = calcFixedCashBalance(l_datDate);
        
        //����.�w����̉ߓ������������擾����B
        //"�ߓ���������" = this.calc�ߓ���������(:Date = ����.�w���)
        double l_dblElapsedDayExecuteCancelAmount = calcElapsedDayExecuteCancelPrice(l_datDate);
        
        //"�a���" = "�m��a���"�@@+�@@"�ߓ���������" 
        double l_dblBanlance = l_dblFixedBalance + l_dblElapsedDayExecuteCancelAmount;
        
        //�v�Z����"�a���"��ԋp����B 
        return l_dblBanlance;
    }

    /**
     * (calc�m��a���)<BR>
     * �����ɂĎw���(T+0�`T+5)���󂯎��A�w����ɑΉ�����<BR>
     * �a����c�����W�v���ԋp����B<BR>
     * <BR>
     * �P�j�@@�m��a����ꗗ���擾����B<BR>
     * <BR>
     * �Q�j�@@�a����c�����W�v����B<BR>
     * �a����c��(n) = ��(*)�m��a���.get�a���()<BR>
     * <BR>
     * (*)�W�v�����F<BR>
     * �m��a����ꗗ�ɂ���m��a���<BR>
     * ���@@�m��a���.is�ϓ����Ԓ�(n)=true<BR>
     * <BR>
     * �R�j�@@�a����c��(n)��ԋp����B<BR>
     * <BR>
     * @@param l_datDate - �w���
     * @@return double
     * @@roseuid 40CD31CD03A3
     */
    public double calcFixedCashBalance(Date l_datDate)
    {
        double l_dblTotal = 0.0d;

        for (Iterator l_iter = fixedCashBalances.iterator(); l_iter.hasNext(); )
        {
            WEB3TPCashBalanceReflector l_ref = (WEB3TPCashBalanceReflector) l_iter.next();
            if (l_ref.isDuringReflectTime(l_datDate))
            {
                l_dblTotal += l_ref.getAmount();
            }

        }
        return l_dblTotal;
    }

    /**    
     * (calc�ߓ���������)<BR>
     * <BR>
     * ����.�w����̉ߓ������������W�v���ĕԋp����B<BR> 
     * <BR>
     * �P�j�ߓ������������W�v����B<BR> 
     * <BR>
     * �@@��LOOP�����Fthis.�ߓ���������ꗗ�̗v�f����<BR> 
     * <BR>
     * �@@�@@�@@�ߓ���������ꗗ���A�v�f�I�u�W�F�N�g(="�ߓ���������")���擾����B<BR> 
     * <BR>
     * �@@�@@�@@"�ߓ���������" = this.�ߓ���������ꗗ.get(:int = LOOP����Index����)<BR> 
     * <BR>
     * �@@�@@�A"�ߓ���������"���W�v����B<BR> 
     * <BR>
     * �@@�@@�@@[a."�ߓ���������".is�ϓ����f���Ԓ�(:Date = ����.�w���) == true]<BR> 
     * <BR>
     * �@@�@@�@@�@@"�ߓ���������" += "�ߓ���������".get������()<BR> 
     * <BR>
     * �Q�j�W�v����"�ߓ�����������ԋp����B<BR> 
     * <BR>
     * �@@�ԋp�l�F"�ߓ���������<BR>
     * <BR>
     * @@param l_datDate - �w���
     * @@return double
     */
    public double calcElapsedDayExecuteCancelPrice(Date l_datDate)
    {
        double l_dblCancelAmount = 0.0d;

        for (Iterator l_iter = elapsedDayExecuteCancelInfoList.iterator(); l_iter.hasNext(); )
        {
            //�@@�ߓ���������ꗗ���A�v�f�I�u�W�F�N�g(="�ߓ���������")���擾����B 
            //"�ߓ���������" = this.�ߓ���������ꗗ.get(:int = LOOP����Index����)
            WEB3TPElapsedDayExecuteCancelReflector l_elapsedDayExcuteCancelInfo = 
                (WEB3TPElapsedDayExecuteCancelReflector)l_iter.next();

            //�A"�ߓ���������"���W�v����B
            //[a."�ߓ���������".is�ϓ����f���Ԓ�(:Date = ����.�w���) == true]
            //"�ߓ���������" += "�ߓ���������".get������()
            if (l_elapsedDayExcuteCancelInfo.isDuringReflectTime(l_datDate))
            {
                l_dblCancelAmount += l_elapsedDayExcuteCancelInfo.getCancelAmount();
            }

        }

        //�Q�j�W�v����"�ߓ�����������ԋp����B
        return l_dblCancelAmount;
    }

    /**
     * (get�m��a����ꗗ)<BR>
     * �m��a����ꗗ��Ԃ��B<BR>
     * <BR>
     * @@return List
     * @@roseuid 40ED041B03E7
     */
    public List getFixedCashBalances()
    {
        return fixedCashBalances;
    }

    /**
     * (get�ߓ���������ꗗ)<BR>
     * <BR>
     * this.�ߓ���������ꗗ��ԋp����B<BR>
     * <BR>
     * @@return List
     */
    public List getElapsedDayExecuteCancelInfoList()
    {
        //this.�ߓ���������ꗗ��ԋp����B
        return elapsedDayExecuteCancelInfoList;
    }
    
    /**
     * (do�a������[�h)<BR> 
     * <BR>
     * �a����֘A�f�[�^�����[�h����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(�a���)do�a������[�h�v�Q��<BR> 
     * <BR>
     */
    public void load()
    {
        //1.1do�a���<�m��>���[�h()
        loadFixedCashBalances();
        
        //1.2do�������ߓ��������񃍁[�h()
        loadBondOrderElapsedDayExecuteCancelInfo();
    }

    /**
     * (do�m��a������[�h)<BR>
     * <BR>
     * �m��a����֘A�f�[�^�����[�h����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(�a���)do�a���<�m��>���[�h�v�Q��<BR>
     * <BR>
     * @@roseuid 40BC5E6F0132
     */
    private void loadFixedCashBalances()
    {
        List l_tpCashBalanceRows = WEB3TPPersistentDataManager.getInstance().getTpCashBalances(this);

        for (Iterator l_iter = l_tpCashBalanceRows.iterator(); l_iter.hasNext(); )
        {
            TpCashBalanceRow l_row = (TpCashBalanceRow) l_iter.next();
            //get�ڋq����( )
            WEB3TPAccountInfo l_accountInfo = getAccountInfo();
            //get�⏕����ID(�⏕�����^�C�v : SubAccountTypeEnum)
            //�⏕�����^�C�v = 1:������������i�a����j
            long l_lngSubAccountId = 0;
            if (l_accountInfo != null)
            {
                l_lngSubAccountId = l_accountInfo.getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            boolean l_blnIsDeposit = false;
            if (l_lngSubAccountId == l_row.getSubAccountId())
            {
                l_blnIsDeposit = false;
            }
            else
            {
                l_blnIsDeposit = true;
            }
            WEB3TPCashBalanceReflector l_cashBalanceT0 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance0(), false,
                       getCalcCondition().getBizDate(0), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT0);
            WEB3TPCashBalanceReflector l_cashBalanceT1 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance1(), false,
                       getCalcCondition().getBizDate(1), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT1);
            WEB3TPCashBalanceReflector l_cashBalanceT2 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance2(), false,
                       getCalcCondition().getBizDate(2), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT2);
            WEB3TPCashBalanceReflector l_cashBalanceT3 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance3(), false,
                       getCalcCondition().getBizDate(3), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT3);
            WEB3TPCashBalanceReflector l_cashBalanceT4 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance4(), false,
                       getCalcCondition().getBizDate(4), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT4);
            WEB3TPCashBalanceReflector l_cashBalanceT5 = WEB3TPCashBalanceReflector.
                create(getCalcCondition(), l_row.getCashBalance5(), false,
                       getCalcCondition().getBizDate(5), l_blnIsDeposit);
            addFixedCashBalance(l_cashBalanceT5);
        }

    }

    /**
     * (do�������ߓ��������񃍁[�h)<BR>
     * <BR>
     * �������̉ߓ�������������[�h����B<BR> 
     * <BR>
     * ���V�[�P���X�}�u(�a���)do�������ߓ��������񃍁[�h�v�Q��<BR> 
     * <BR>
     */
    private void loadBondOrderElapsedDayExecuteCancelInfo()
    {
        //���[�h�ΏۂƂȂ�������P�ʃe�[�u���s�I�u�W�F�N�g�̃��X�g���擾����B
        List l_bondOrderUnitRows = WEB3TPPersistentDataManager.getInstance().getBondOrdersCash(this);
        
        //get������<�a���>(���Y�]��)�̖߂�l�@@!= null�̏ꍇ
        if (l_bondOrderUnitRows != null)
        {
           // LOOP�����Fget������<�a���>()�̖߂�l�̗v�f����  
            for (Iterator l_iter = l_bondOrderUnitRows.iterator(); l_iter.hasNext(); )
            {
                //�g�����U�N�V�����^�C�v�ʂ̑��v�������擾����B                
                BondOrderUnitRow l_row = (BondOrderUnitRow) l_iter.next();
                int l_intCashDir = WEB3TPTransactionReflector.getCashDir(
                    l_row.getOrderType().toFinTransactionType());
                
                //�������"�A"������"���v�Z����
                //"�������" = ����.������.get��萔��()
                double l_dblCancelQuantity = l_row.getExecutedQuantity();
                
                //"������" = "�����"(*1) �~ -1
                //(*1)"�����" = ����.������.get��n���(�~��)() �~ get���v����()�̖߂�l
                //�i������̏ꍇ�A���̔��Ύd��ƂȂ�B����āA�������-1���悶��B�j
                double l_dbExecutedAmount = l_row.getEstimatedPrice() * l_intCashDir;
                double l_dbCancelAmount = l_dbExecutedAmount * -1;
                Date l_datDeliveryDate = null;
                
                //��n��"���v�Z����B
                //��W����̏ꍇ
                //�i����.������.get�������() == 401�F����������&&
                //����.������.get���() == 35:��W����j
                //"��n��" = ����.������.get������()
                if (OrderTypeEnum.BOND_BUY.equals(l_row.getOrderType()) && 
                    WEB3DealTypeDef.RECRUIT_TRADING.equals(l_row.getDealType()))
                {
                    l_datDeliveryDate = l_row.getPaymentDate();
                }
                //[a.�ȊO�̏ꍇ]
                //"��n��" = ����.������.get��n��()
                else
                {
                    l_datDeliveryDate = l_row.getDeliveryDate();
                }

                //�ߓ���������I�u�W�F�N�g�𐶐�����B
                //�]�͌v�Z���� = this.get�]�͌v�Z����()
                //ProductTypeEnum = ����.������.get�����^�C�v() 
                //long = ����.������.get����ID() 
                //FinTransactionType = ����.������.get�������().toFinTransactionType() 
                //Date = ����.������.get������() 
                //double = "�������" 
                //double = "������" 
                //Date = "��n��" 
                //TaxTypeEnum = ����.������.get�ŋ敪()
                WEB3TPElapsedDayExecuteCancelReflector l_ElapsedDayExecuteCancelInfo = 
                    WEB3TPElapsedDayExecuteCancelReflector.createWEB3TPElapsedDayExecuteCancelReflector(
                        getCalcCondition(), 
                        l_row.getProductType(), 
                        l_row.getProductId(), 
                        l_row.getOrderType().toFinTransactionType(), 
                        WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd"), 
                        l_dblCancelQuantity, 
                        l_dbCancelAmount, 
                        l_datDeliveryDate, 
                        l_row.getTaxType());

                //�擾�����ߓ���������I�u�W�F�N�g���A this.�ߓ���������ꗗ�ɒǉ�����B 
                addElapsedDayExecuteCancelReflector(l_ElapsedDayExecuteCancelInfo);
            }
        }
    }

    /**
     * (add�m��a���)<BR>
     * �m��a����ꗗ�Ɉ�����ǉ�����B<BR>
     * @@param WEB3TPCashBalance - (�m��a���)
     * @@roseuid 40E113FB031E
     */
    protected void addFixedCashBalance(WEB3TPCashBalanceReflector l_cashBalance)
    {
        if (l_cashBalance instanceof WEB3TPCashBalanceReflector)
        {
            fixedCashBalances.add(l_cashBalance);
        }
    }

    /**
     * (add�ߓ���������)<BR>
     * <BR>
     * ����.�ߓ�����������Athis�ߓ���������ꗗ�ɒǉ�����B<BR> 
     * -this.�ߓ���������ꗗ.add(:Object = ����.�ߓ���������)���R�[��<BR>
     * <BR>
     * @@param l_cancelInfo - (�ߓ���������)
     * @@roseuid 40E113FB031E
     */
    protected void addElapsedDayExecuteCancelReflector(WEB3TPElapsedDayExecuteCancelReflector l_cancelInfo)
    {
        if (l_cancelInfo instanceof WEB3TPElapsedDayExecuteCancelReflector)
        {
            elapsedDayExecuteCancelInfoList.add(l_cancelInfo);
        }
    }

    /**
     * (calc�m��ۏ؋�)<BR>
     * (calc�m��ۏ؋�) <BR>
     * 
     * ����.�w����̊m��ۏ؋����W�v���ĕԋp����B <BR>
     * <BR>
     * �P�j�m��ۏ؋����W�v����B <BR>
     * �@@ <BR>
     * �@@��LOOP�����Fthis.�m��a����ꗗ�̗v�f���� <BR>
     * <BR>
     * �@@�@@�@@�m��a����ꗗ���A�v�f�I�u�W�F�N�g(="�m��a���")���擾����B <BR>
     * <BR>
     * �@@�@@�@@"�m��a���" = this.�m��a����ꗗ.get(:int = LOOP����Index����) <BR>
     * <BR>
     * �@@�@@�A"�m��ۏ؋�"���W�v����B <BR>
     * <BR>
     * �@@�@@�@@[a."�m��a���".is�ϓ����f���Ԓ�(:Date = ����.�w���) == true ���@@"�m��a���".is�ۏ؋�() == true] <BR>
     * <BR>
     * �@@�@@�@@�@@"�m��ۏ؋�" += "�m��a���".get�a����c��() <BR>
     * <BR>
     * �Q�j�W�v����"�m��ۏ؋�"��ԋp����B <BR>
     * <BR>
     * �@@�ԋp�l�F"�m��ۏ؋�" <BR>
     * @@param l_datDate -   (�w���)<BR>
     * �w���<BR>
     * @@return double
     */
    public double calcFixedDeposit(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcFixedDeposit(Date)";
        log.entering(STR_METHOD_NAME);

        double l_dblFixedDeposit = 0;
        //�P�j�m��ۏ؋����W�v����B
        //��LOOP�����Fthis.�m��a����ꗗ�̗v�f����
        List l_lisFixedCashBalances = this.getFixedCashBalances();
        for (Iterator l_iterFixedCashBalances = l_lisFixedCashBalances.iterator(); l_iterFixedCashBalances.hasNext();)
        {
            //�@@�m��a����ꗗ���A�v�f�I�u�W�F�N�g(="�m��a���")���擾����B
            //"�m��a���" = this.�m��a����ꗗ.get(:int = LOOP����Index����) 
            WEB3TPCashBalanceReflector l_cashBalanceReflector =
                (WEB3TPCashBalanceReflector)l_iterFixedCashBalances.next();
            //�A"�m��ۏ؋�"���W�v����B
            //[a."�m��a���".is�ϓ����f���Ԓ�(:Date = ����.�w���) == true ���@@"�m��a���".is�ۏ؋�() == true]
            if (l_cashBalanceReflector.isDuringReflectTime(l_datDate) && l_cashBalanceReflector.isDeposit())
            {
                l_dblFixedDeposit = l_dblFixedDeposit + l_cashBalanceReflector.getAmount();
            }
        }
        //�Q�j�W�v����"�m��ۏ؋�"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblFixedDeposit;
    }
    

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("fixedCashBalances", this.getFixedCashBalances())
            .append("ElapsedDayExecuteCancelInfoList", this.getElapsedDayExecuteCancelInfoList())
            .appendSuper(super.toString())
            .toString();

    }

}
@
