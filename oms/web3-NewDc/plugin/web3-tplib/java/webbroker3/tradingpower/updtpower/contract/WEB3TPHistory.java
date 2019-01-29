head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPHistory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʕϓ�(WEB3TPHistory.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
Revesion History : 2008/01/31 �g�E�N�|�@@(���u)�@@�d�l�ύX�@@���f��No.253
Revesion History : 2008/10/21 ���z�@@(���u)�@@�d�l�ύX�@@���f��No325
Revesion History : 2008/10/31 �������@@(���u)�@@�d�l�ύX�@@���f��No352
Revesion History : 2009/12/03 �����F�@@(���u)�@@�d�l�ύX�@@���f��No400
*/
package webbroker3.tradingpower.updtpower.contract;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPContractAmountApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPRestraintDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPSwapContractDepositRestraintDef;
import webbroker3.tradingpower.define.WEB3TPSwapContractProfitlossRestraintDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;

/**
 * (���ʕϓ�)
 */
public class WEB3TPHistory extends WEB3TPAssetReflector 
{
    
    /**
     * (�g�����U�N�V�����J�e�S��)
     */
    private FinTransactionCateg transactionCateg;
    
    /**
     * (���σt���O)
     */
    private boolean executedFlag;
    
    /**
     * (�g�����U�N�V����������)
     */
    private Date transactionDate;
    
    /**
     * (�P��)
     */
    private double price;
    
    /**
     * (����)
     */
    private double quantity;
    
    /**
     * (��n���)
     */
    private double netAmount;
    
    /**
     * (��n��)
     */
    private Date deliveryDate;
    
    /**
     * (�Ώی���)
     */
    private WEB3TPTargetContract targetContract;
  
    /**
     * (�����P��ID)
     */
    private long orderUnitId;

    /**
     * (���ʏ��o��)
     */
    private double contractTotalCost = 0;

    /**
     * (�ۏ؋����̕���)
     */
    private static final double depositDenominator = 100;
    
    /**
     * @@roseuid 4104AB46030D
     */
    public WEB3TPHistory() 
    {
     
    }
    
    /**
     * (create���ʕϓ�)<BR>
     * ���ʕϓ��𐶐�����B<BR>
     * @@param l_targetContract - (�Ώی���)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPHistory
     * @@roseuid 40DC0C290151
     */
    public static WEB3TPHistory create(WEB3TPTargetContract l_targetContract, WEB3TPCalcCondition l_calcCondition) 
    {
        WEB3TPHistory l_thisInstance = new WEB3TPHistory();
        l_thisInstance.setTargetContract(l_targetContract);
        l_thisInstance.setCalcCondition(l_calcCondition);
        return l_thisInstance;
    }
    
    /**
     * (get�g�����U�N�V�����J�e�S��)<BR>
     * �g�����U�N�V�����J�e�S�����擾����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg
     * @@roseuid 40DBE42D030D
     */
    public FinTransactionCateg getTransactionCateg() 
    {
        return transactionCateg;
    }
    
    /**
     * (set�g�����U�N�V�����J�e�S��)<BR>
     * �����̃g�����U�N�V�����J�e�S�����Z�b�g����B<BR>
     * @@param l_transactionCateg - (�g�����U�N�V�����J�e�S��)
     * @@roseuid 40DBE4300271
     */
    public void setTransactionCateg(FinTransactionCateg l_transactionCateg) 
    {
        transactionCateg = l_transactionCateg;
    }
    
    /**
     * (is����)<BR>
     * ���ς����肷��B<BR>
     * @@return boolean
     * @@roseuid 40DBE43E00EA
     */
    public boolean isExecuted() 
    {
        return executedFlag;
    }
    
    /**
     * (set���σt���O)<BR>
     * �����̖��σt���O���Z�b�g����B<BR>
     * @@param l_isExecuted - (���σt���O)
     * @@roseuid 40DBE44002DE
     */
    public void setExecuted(boolean l_isExecuted) 
    {
        executedFlag = l_isExecuted;
    }
    
    /**
     * (get�g�����U�N�V����������)<BR>
     * �g�����U�N�V�������������擾����B<BR>
     * @@return java.util.Date
     * @@roseuid 40DBE44B008C
     */
    public Date getTransactionDate() 
    {
        return transactionDate;
    }
    
    /**
     * (set�g�����U�N�V����������)<BR>
     * �����̃g�����U�N�V�������������Z�b�g����B<BR>
     * @@param l_datTransaction - (�g�����U�N�V����������)
     * @@roseuid 40DBE44E005D
     */
    public void setTransactionDate(Date l_datTransaction) 
    {
        transactionDate = l_datTransaction;
    }
    
    /**
     * (get�P��)<BR>
     * �P�����擾����B<BR>
     * @@return double
     * @@roseuid 40DBE45402FD
     */
    public double getPrice() 
    {
        return price;
    }
    
    /**
     * (set�P��)<BR>
     * �����̒P�����Z�b�g����B<BR>
     * @@param l_dblPrice - (�P��)
     * @@roseuid 40DBE456031C
     */
    public void setPrice(double l_dblPrice) 
    {
        price = l_dblPrice;
    }
    
    /**
     * (get����)<BR>
     * �������擾����B<BR>
     * @@return double
     * @@roseuid 40DC0DF402F7
     */
    public double getQuantity() 
    {
        return quantity;
    }
    
    /**
     * (set����)<BR>
     * �����̊������Z�b�g����B<BR>
     * @@param l_dblQuantity - (����)
     * @@roseuid 40DC0DF70316
     */
    public void setQuantity(double l_dblQuantity) 
    {
        quantity = l_dblQuantity;
    }
    
    /**
     * (get��n���)<BR>
     * ��n������擾����B<BR>
     * @@return double
     * @@roseuid 40DBF1B202DE
     */
    public double getNetAmount() 
    {
        return netAmount;
    }
    
    /**
     * (set��n���)<BR>
     * �����̎�n������Z�b�g����B<BR>
     * @@param l_dblNetAmount - (��n���)
     * @@roseuid 40DBF21100BB
     */
    public void setNetAmount(double l_dblNetAmount) 
    {
        netAmount = l_dblNetAmount;
    }
    
    /**
     * (get��n��)<BR>
     * ��n�����擾����B<BR>
     * @@return java.util.Date
     * @@roseuid 40E0237002FF
     */
    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    
    /**
     * (set��n��)<BR>
     * �����̎�n�����Z�b�g����B<BR>
     * @@param l_datDelivery - (��n��)
     * @@roseuid 40E02370032E
     */
    public void setDeliveryDate(Date l_datDelivery) 
    {
        deliveryDate = l_datDelivery;
    }

    /**
     * (get�Ώی���)<BR>
     * �Ώی��ʂ��擾����B<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     */
    public WEB3TPTargetContract getTargetContract() 
    {
        return targetContract;
    }
    
    /**
     * (set�Ώی���)<BR>
     * �����̑Ώی��ʂ��Z�b�g����B<BR>
     * @@param l_targetContract - (�Ώی���)
     */
    public void setTargetContract(WEB3TPTargetContract l_targetContract) 
    {
        targetContract = l_targetContract;
    }

    /**
     * (get�����P��ID)
     * �����P��ID���擾����B<BR>
     * @@return long
     */
    public long getOrderUnitId()
    {
        return orderUnitId;
    }

    /**
     * (set�����P��ID)
     * �����̒����P��ID���Z�b�g����B<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)
     */
    public void setOrderUnitId(long l_lngOrderUnitId)
    {
        orderUnitId = l_lngOrderUnitId;
    }

    /**
     * (get���ʏ��o��)
     * �u���ʏ��o��v��ԋp����B<BR>
     * @@return double
     */
    public double getContractTotalCost()
    {
        return contractTotalCost;
    }

    /**
     * (set���ʏ��o��)
     * ����.���ʏ��o���this.���ʏ��o��ɃZ�b�g����B<BR>
     * @@param l_dblContractTotalCost - (���ʏ��o��)
     */
    public void setContractTotalCost(double l_dblContractTotalCost)
    {
        contractTotalCost = l_dblContractTotalCost;
    }

    /**
     * (calc���ʑ��)<BR>
     * ���ʑ�����v�Z���A�v�Z���ʂ�Ԃ��B<BR>
     * <BR>
     * �P�j���ʑ�����v�Z����B<BR>
     * �@@�@@���ʑ�������ʕϓ�.�P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�~�@@���ʕϓ�.����<BR>
     * �Q�j�v�Z�������ʑ����Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʕϓ�.�P���E�E�E���ʕϓ�.get�P��()<BR>
     * �E���ʕϓ�.�����E�E�E���ʕϓ�.get����()<BR>
     * @@return double
     * @@roseuid 40DBF2210271
     */
    public double calcContractAmount() 
    {
        return getPrice() * getQuantity();
    }
    
    /**
     * (calc�K�v�ۏ؋�)<BR>
     * �K�v�ۏ؋����v�Z���A�v�Z���ʂ�Ԃ��B<BR>
     * <BR>
     * �P�j�K�v�ۏ؋����v�Z����B<BR>
     * �@@�@@�K�v�ۏ؋������ʕϓ�.�P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�~�@@���ʕϓ�.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�~�@@����.�ۏ؋��� / 100<BR>
     * <BR>
     * �Q�j�v�Z�����K�v�ۏ؋���Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʕϓ�.�P���E�E�E���ʕϓ�.get�P��()<BR>
     * �E���ʕϓ�.�����E�E�E���ʕϓ�.get����()<BR>
     * @@param l_dblMarginDepositRate - (�ۏ؋���)
     * @@return double
     * @@roseuid 40DBF22C0109
     */
    public double calcMarginDeposit(double l_dblMarginDepositRate) 
    {
        return calcContractAmount() * l_dblMarginDepositRate / depositDenominator;
    }
    
    /**
     * (calc�����K�v�ۏ؋�)<BR>
     * �����K�v�ۏ؋����v�Z���A�v�Z���ʂ�Ԃ��B<BR>
     * <BR>
     * �P�j�����K�v�ۏ؋����v�Z����B<BR>
     * �@@�@@�����K�v�ۏ؋������ʕϓ�.�P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@���ʕϓ�.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@����.�����ۏ؋��� / 100<BR>
     * <BR>
     * �Q�j�v�Z���������K�v�ۏ؋���Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʕϓ�.�P���E�E�E���ʕϓ�.get�P��()<BR>
     * �E���ʕϓ�.�����E�E�E���ʕϓ�.get����()<BR>
     * @@param l_dblCashMarginDepositRate - (�����ۏ؋���)
     * @@return double
     * @@roseuid 40DBF23502CE
     */
    public double calcCashMarginDeposit(double l_dblCashMarginDepositRate) 
    {
        return calcContractAmount() * l_dblCashMarginDepositRate / depositDenominator;
    }
    
    /**
     * (calc���ωv)<BR>
     * ���ωv���v�Z���A�v�Z���ʂ�Ԃ��B<BR>
     * <BR>
     * �P�j���ωv���v�Z����B<BR>
     * �@@�|���ʕϓ�.��n�����0 �̏ꍇ<BR>
     * �@@�@@�E���ωv�����ʕϓ�.��n���<BR>
     * �@@�|���ʕϓ�.��n�������0 �̏ꍇ<BR>
     * �@@�@@�E���ωv��0<BR>
     * <BR>
     * �Q�j�v�Z�������ωv��Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʕϓ�.��n����E�E�E���ʕϓ�.get��n���()<BR>
     * @@return double
     * @@roseuid 40E134A100AD
     */
    public double calcContractProfit() 
    {
        double l_dblContractProfit;
        if(getNetAmount() > 0)
        {
            l_dblContractProfit = getNetAmount();
        }
        else
        {
            l_dblContractProfit = 0;
        }
        
        return l_dblContractProfit;
    }
    
    /**
     * (calc���ϑ�) <BR>
     * ���ϑ����v�Z���A�v�Z���ʂ�Ԃ��B<BR>
     * <BR>
     * �P�j���ϑ����v�Z����B<BR>
     * �@@�|���ʕϓ�.��n�����0 �̏ꍇ<BR>
     * �@@�@@�E���ϑ������ʕϓ�.��n��� �~ -1<BR>
     * �@@�|���ʕϓ�.��n�������0 �̏ꍇ<BR>
     * �@@�@@�E���ϑ���0<BR>
     * <BR>
     * �Q�j�v�Z�������ϑ���Ԃ��B<BR>
     * <BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʕϓ�.��n����E�E�E���ʕϓ�.get��n���()<BR>
     * @@return double
     * @@roseuid 40E134AB02DF
     */
    public double calcContractLoss() 
    {
        double l_dblContractLoss;
        if(getNetAmount() < 0)
        {
            l_dblContractLoss = getNetAmount() * -1;
        }
        else
        {
            l_dblContractLoss = 0;
        }
        
        return l_dblContractLoss;
    }
    
    /**
     * (is����n����<����v��ԍρE�������n>)<BR>
     * ����n����<����v��ԍρE�������n>�����肷��B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɊY������ꍇ��true��Ԃ��B<BR>
     * �@@�@@�Y�����Ȃ��ꍇ��false��Ԃ��B<BR>
     * �@@�|�����P�E�E�E����v��ԍ�<BR>
     * �@@�@@�E�g�����U�N�V�����J�e�S�����ԍ�<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���σt���O����<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E��n�����]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E!( �]�͌v�Z����.����������t�J�n�敪<��������>����<BR>
     * �@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�Ώی��ʏ��.�������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�@@ )<BR>
     * <BR>
     * �@@�|�����Q�E�E�E�񓖓����ό������n<BR>
     * �@@�@@�E�g�����U�N�V�����J�e�S�����������n<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���σt���O����<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E��n�����]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E(<BR>
     * �@@�@@�@@�@@( �g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z����.����������t�J�n�敪<��������>���I<BR>
     * �@@�@@�@@�@@ )<BR>
     * �@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@ )<BR>
     * �@@�@@����<BR>
     * �@@�@@�E�i<BR>
     * �@@�@@�@@�@@����.�S���敪���u0�FDEFAULT�v<BR>
     * �@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�i����.�S���敪���u1�F�K�v�ۏ؋��v<BR>
     * �@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z�����Dget��Е��X�ʗ]�͌v�Z�����i<BR>
     * �@@�@@�@@�@@�@@�@@:String = �u�������n���ʕK�v�ۏ؋��S���敪�v�j<BR>
     * �@@�@@�@@�@@�@@���u1 �F �������n�̖�������A��n���O���܂ōS������B�v�ȊO<BR>
     * �@@�@@�@@�@@�j<BR>
     * �@@�@@�@@�j<BR>
     * @@param l_strRestraintDiv - (�S���敪)<BR>
     * �u0�FDEFAULT�v<BR>
     * �u1�F�K�v�ۏ؋��v<BR>
     * @@return boolean
     */
    public boolean isUndeliveredContractNotDayTradeSwap(String l_strRestraintDiv) 
    {
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //�Ώی��ʏڍׂ��擾
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
        
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //����v��ԍ�
        //�J�e�S�����ԍ�
        if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(getTransactionCateg()))
        {
            //����
            if(isExecuted())
            {
                //�c�Ɠ�(T+0) < ��n��
                if(WEB3DateUtility.compareToDay(l_datBizDate0, getDeliveryDate()) < 0)
                {
                    //! (����������t���ԑ�=�� ���� �c�Ɠ�(T+0)=���� ���� �c�Ɠ�(T+0)=�g�����U�N�V����������)
                    if(! (
                        l_calcCondition.isEquityNextDayOrderStartDiv() == false
                        && WEB3DateUtility.compareToDay(l_datBizDate0, l_targetContractDetail.getOpenDate()) == 0
                        && WEB3DateUtility.compareToDay(l_datBizDate0,getTransactionDate()) == 0))
                    {
                        return true;
                    }
                }
            }
        }
        
        //�񓖓����ό������n
        //�J�e�S�����������n
        if(FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(getTransactionCateg()))
        {
            //����
            if(isExecuted())
            {
                //�c�Ɠ�(T+0) < ��n��
                if(WEB3DateUtility.compareToDay(l_datBizDate0, getDeliveryDate()) < 0)
                {
                    //�c�Ɠ�(T+0)=�g�����U�N�V�������������]�͌v�Z����.����������t�J�n�敪<��������>���I
                    //���̓g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)
                    if (((WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) == 0)
                            && l_calcCondition.isEquityNextDayOrderStartDiv())
                        || (WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) != 0))
                    {
                        //����.�S���敪���u0�FDEFAULT�v
                        //����
                        //�i����.�S���敪���u1�F�K�v�ۏ؋��v ���� �]�͌v�Z�����Dget��Е��X�ʗ]�͌v�Z�����i
                        //    :String = �u�������n���ʕK�v�ۏ؋��S���敪�v�j���u1 �F �������n�̖�������A��n���O���܂ōS������B�v�ȊO
                        String l_strSwapContractDepositRestraint = l_calcCondition.getInstBranCalcCondition(
                                WEB3BranchPreferencesNameDef.SWAP_CONTRACT_DEPOSIT_RESTRAINT);
                        if (WEB3TPRestraintDivDef.DEFAULT.equals(l_strRestraintDiv)
                            || (WEB3TPRestraintDivDef.MARGIN_DEPOSIT.equals(l_strRestraintDiv)
                                && !WEB3TPSwapContractDepositRestraintDef.THREE_DAYS.equals(l_strSwapContractDepositRestraint)))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * (is����n����<���v��ԍ�>)<BR>
     * ���v��ԍς����肷��B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɊY������ꍇ��true��Ԃ��B<BR>
     * �@@�@@�Y�����Ȃ��ꍇ��false��Ԃ��B<BR>
     * �@@�|�����E�E�E���v��ԍ�<BR>
     * �@@�@@�E�g�����U�N�V�����J�e�S�����ԍ�<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���σt���O����<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E�]�͌v�Z����.������t�J�n�敪<��������>����<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E�Ώی��ʏ��.�������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E�g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * @@return boolean
     */
    public boolean isUndeliveredContractDayTrade() 
    {
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //�Ώی��ʏڍׂ��擾
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
        
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //���v��ԍ�
        //�J�e�S�����ԍ�
        if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(getTransactionCateg()))
        {
            //����
            if(isExecuted())
            {
                //����������t���ԑ�=��
                if(! l_calcCondition.isEquityNextDayOrderStartDiv())
                {
                    //�c�Ɠ�(T+0)=����
                    if(WEB3DateUtility.compareToDay(l_datBizDate0, l_targetContractDetail.getOpenDate()) == 0)
                    {
                        //�c�Ɠ�(T+0)=�g�����U�N�V����������
                        if(WEB3DateUtility.compareToDay(l_datBizDate0,getTransactionDate()) == 0)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * (is���v��ԍρE�������n����)<BR>
     * ���v��ԍρE�������n���ʂ����肷��B<BR>
     * <BR>
     * �P�j�ȉ��̏����ɊY������ꍇ��true��Ԃ��B<BR>
     * �@@�@@�Y�����Ȃ��ꍇ��false��Ԃ��B<BR>
     * �@@�|�����P�E�E�E���v��ԍ�<BR>
     * �@@�@@�Eis����n����<���v��ԍ�>���R�[������B<BR>
     * <BR>
     * �@@�|�����Q�E�E�E����茻�����n<BR>
     * �@@�@@�E�g�����U�N�V�����J�e�S�����������n<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���σt���O����<BR>
     * <BR>
     * �@@�|�����R�E�E�E�������ό������n<BR>
     * �@@�@@�E�g�����U�N�V�����J�e�S�����������n<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���σt���O����<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E�]�͌v�Z����.����������t�J�n�敪<��������>����<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E�g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * <BR>
     * �@@�|�����S�E�E�E�񓖓����ό������n<BR>
     * �@@�@@�E�g�����U�N�V�����J�e�S�����������n<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���σt���O����<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E��n�����]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E(<BR>
     * �@@�@@�@@( �g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z����.����������t�J�n�敪<��������>���I<BR>
     * �@@�@@�@@�@@ )<BR>
     * �@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@)<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�@@�i<BR>
     * �@@�@@�@@�@@�i�����D�S���敪���u1�F�K�v�ۏ؋��v<BR>
     * �@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z�����Dget��Е��X�ʗ]�͌v�Z�����i<BR>
     * �@@�@@�@@�@@�@@:String = �u�������n���ʕK�v�ۏ؋��S���敪�v�j<BR>
     * �@@�@@�@@�@@�@@���@@�u1 �F �������n�̖�������A��n���O���܂ōS������B�v�j<BR>
     * �@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�i�����D�S���敪���u2�F�]�����v�v<BR>
     * �@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�@@�]�͌v�Z�����Dget��Е��X�ʗ]�͌v�Z�����i<BR>
     * �@@�@@�@@�@@�@@ :String = �u�������n���ʕ]�����v�S���敪�v�j<BR>
     * �@@�@@�@@�@@�@@���@@�u1 �F �������n�̖�������A��n���O���܂ōS������B�v�j<BR>
     * �@@�@@�@@)<BR>
     * @@param l_strRestraintDiv - (�S���敪)<BR>
     * �u0�FDEFAULT�v<BR>
     * �u1�F�K�v�ۏ؋��v<BR>
     * �u2�F�]�����v�v<BR>
     * @@return boolean
     */
    public boolean isDayTradeSwap(String l_strRestraintDiv) 
    {
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();

        //���v��ԍ�
        if(isUndeliveredContractDayTrade())
        {
            return true;
        }
        
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //�������n
        //�J�e�S�����������n
        if(FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(getTransactionCateg()))
        {
            //����
            if(isExecuted())
            {
                //����������t���ԑ�=��
                if(! l_calcCondition.isEquityNextDayOrderStartDiv())
                {
                    //�c�Ɠ�(T+0)=�g�����U�N�V����������
                    if(WEB3DateUtility.compareToDay(l_datBizDate0,getTransactionDate()) == 0)
                    {
                        return true;
                    }
                }
                //�񓖓����ό������n
                //��n�����]�͌v�Z����.�c�Ɠ�(T+0)
                if (WEB3DateUtility.compareToDay(getDeliveryDate(), l_datBizDate0) > 0)
                {
                    //�c�Ɠ�(T+0)=�g�����U�N�V���������� ���� �]�͌v�Z����.����������t�J�n�敪<��������>���I
                    //���̓g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)
                    if (((WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) == 0)
                            && l_calcCondition.isEquityNextDayOrderStartDiv())
                        || (WEB3DateUtility.compareToDay(l_datBizDate0, getTransactionDate()) != 0))
                    {
                        //�������n���ʕK�v�ۏ؋��S���敪
                        String l_strSwapContractDepositRestraint = l_calcCondition.getInstBranCalcCondition(
                                WEB3BranchPreferencesNameDef.SWAP_CONTRACT_DEPOSIT_RESTRAINT);
                        //�������n���ʕ]�����v�S���敪
                        String l_strSwapContractProfitlossRestraint = l_calcCondition.getInstBranCalcCondition(
                                WEB3BranchPreferencesNameDef.SWAP_CONTRACT_PROFITLOSS_RESTRAINT);

                        //����.�S���敪���u1�F�K�v�ۏ؋��v ���� �]�͌v�Z�����Dget��Е��X�ʗ]�͌v�Z�����i
                        //    :String = �u�������n���ʕK�v�ۏ؋��S���敪�v�j���@@�u1 �F �������n�̖�������A��n���O���܂ōS������B�v�j
                        //����
                        //�i����.�S���敪���u2�F�]�����v�v ���� �]�͌v�Z�����Dget��Е��X�ʗ]�͌v�Z�����i
                        //    :String = �u�������n���ʕ]�����v�S���敪�v�j���@@�u1 �F �������n�̖�������A��n���O���܂ōS������B�v�j
                        if ((WEB3TPRestraintDivDef.MARGIN_DEPOSIT.equals(l_strRestraintDiv)
                                &&  WEB3TPSwapContractDepositRestraintDef.THREE_DAYS.equals(l_strSwapContractDepositRestraint))
                            || (WEB3TPRestraintDivDef.PROFITLOSS.equals(l_strRestraintDiv)
                                && WEB3TPSwapContractProfitlossRestraintDef.THREE_DAYS.equals(l_strSwapContractProfitlossRestraint)))
                        {
                            return true;
                        }
                    }
                }
            }
            //�����
            else
            {
                return true;
            }
        } 

        return false;
    }

    /**
     * (calc�ϓ����f��)<BR>
     * �ϓ��J�n���ƕϓ��I������ݒ肷��B<BR>
     * <BR>
     * �P�j�������ʑ���v��J�n�����[�h�ɂ��������āA<BR>
     * �@@�@@�������ʑ���v��J�n�����0 or 1 or 2 ���Z�b�g����B<BR>
     * <BR>
     * �Q�j�g�����U�N�V�����J�e�S�����V�K���̏ꍇ<BR>
     * <BR>
     * �@@a�j�Ώی��ʏڍ�.���������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ<BR>
     * �@@�@@�E�ϓ����f�J�n���Ɍ��� + �������ʑ���v��J�n������Z�b�g����B<BR>
     * <BR>
     * �@@a�j�Ώی��ʏڍ�.�������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ<BR>
     * �@@�@@�E�ϓ����f�J�n���ɉc�Ɠ�(T+0)���Z�b�g����B<BR>
     * <BR>
     * �@@�|�ϓ����f�I�����ɗ]�͌v�Z����.�c�Ɠ�(T+5)���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �R�j�g�����U�N�V�����J�e�S�����ԍ� or �������n�̏ꍇ<BR>
     * <BR>
     * �@@a�j�Ώی��ʏڍ�.���������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ<BR>
     * �@@�@@�E�ϓ����f�J�n���Ɍ��� + �������ʑ���v��J�n������Z�b�g����B<BR>
     * <BR>
     * �@@a�j�Ώی��ʏڍ�.�������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ<BR>
     * �@@�@@�E�ϓ����f�J�n���ɉc�Ɠ�(T+0)���Z�b�g����B<BR>
     * <BR>
     * �@@�|�ϓ����f�I�����Ɏ�n��-1���Z�b�g����B<BR>
     * @@param l_datDate - (��n��)<BR>
     */
    public void calcReflectDay(Date l_datDate) 
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDate)";

        //������null�̎��́A���I�u�W�F�N�g�ɐݒ肳��Ă��n�����g��
        if(l_datDate == null)
        {
            l_datDate = getDeliveryDate();
        }

        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        WEB3TPTargetContract l_targetContract = getTargetContract();
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        Date l_datBizDate5 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);
        Date l_datOpenDate = l_targetContract.getTargetContractDetail().getOpenDate();
        String l_strContractApplyDate = l_calcCondition.getInstBranCalcCondition(WEB3TPCalcCondition.CONTRACTAMOUNT_APPLY_DATE);

        int l_intContractApplyDate = 1;
        //�P�j�������ʑ���v��J�n�����[�h�ɂ��������āA
        //�@@�������ʑ���v��J�n�����0 or 1 or 2 ���Z�b�g����B
        if (WEB3TPContractAmountApplyDateDef.DEFAULT.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 1;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_BIZ_DATE.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 0;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_T2.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 2;
        }

        //�Q�j�g�����U�N�V�����J�e�S�����V�K���̏ꍇ
        if (FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(getTransactionCateg()))
        {
            //a�j�Ώی��ʏڍ�.���������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ
            if (WEB3DateUtility.compareToDay(l_datOpenDate,
                l_calcCondition.rollBizDate(l_datBizDate0, -l_intContractApplyDate)) >= 0)
            {
                //�E�ϓ����f�J�n���Ɍ��� + �������ʑ���v��J�n������Z�b�g����B
                setReflectStartDay(l_calcCondition.rollBizDate(l_datOpenDate, l_intContractApplyDate));
            }
            //a�j�Ώی��ʏڍ�.�������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ
            else
            {
                //�E�ϓ����f�J�n���ɉc�Ɠ�(T+0)���Z�b�g����B
                setReflectStartDay(l_datBizDate0);
            }
            //�|�ϓ����f�I�����ɗ]�͌v�Z����.�c�Ɠ�(T+5)���Z�b�g����B
            setReflectEndDay(l_datBizDate5);
        }
        //�R�j�g�����U�N�V�����J�e�S�����ԍ� or �������n�̏ꍇ
        else if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(getTransactionCateg())
            || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(getTransactionCateg()))
        {
            //a�j�Ώی��ʏڍ�.���������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ
            if (WEB3DateUtility.compareToDay(l_datOpenDate,
                l_calcCondition.rollBizDate(l_datBizDate0, -l_intContractApplyDate)) >= 0)
            {
                //�E�ϓ����f�J�n���Ɍ��� + �������ʑ���v��J�n������Z�b�g����B
                setReflectStartDay(l_calcCondition.rollBizDate(l_datOpenDate, l_intContractApplyDate));
            }
            //a�j�Ώی��ʏڍ�.�������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ
            else
            {
                //�E�ϓ����f�J�n���ɉc�Ɠ�(T+0)���Z�b�g����B
                setReflectStartDay(l_datBizDate0);
            }
            //�|�ϓ����f�I�����Ɏ�n��-1���Z�b�g����B
            setReflectEndDay(l_calcCondition.rollBizDate(l_datDate, -1));
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        String l_datTransactionDate = WEB3DateUtility.formatDate(getTransactionDate(), "yyyy/MM/dd HH:mm:ss");
        String l_datDeliveryDate = WEB3DateUtility.formatDate(getDeliveryDate(), "yyyy/MM/dd");
        
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("transactionCateg", getTransactionCateg())
            .append("executedFlag", isExecuted())
            .append("transactionDate", l_datTransactionDate)
            .append("price", getPrice())
            .append("quantity", getQuantity())
            .append("netAmount", getNetAmount())
            .append("deliveryDate", l_datDeliveryDate)
            .append("orderUnitId", getOrderUnitId())
            .toString();
    }
}
@
