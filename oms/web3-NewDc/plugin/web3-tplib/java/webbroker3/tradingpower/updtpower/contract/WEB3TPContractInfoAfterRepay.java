head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractInfoAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό㌚�ʏ��(WEB3TPContractInfoAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
Revesion History : 2008/02/01 �����Q(���u) ���f��No.260
*/

package webbroker3.tradingpower.updtpower.contract;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό㌚�ʏ��)
 */
public class WEB3TPContractInfoAfterRepay extends WEB3TPContractInfo
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPContractInfoAfterRepay.class);

    /**
     * @@roseuid 41E383A20076
     */
    public WEB3TPContractInfoAfterRepay()
    {

    }

    /**
     * (static���\�b�h)(create�ԍό㌚�ʏ��)<BR>
     * <BR>
     * �ԍό㌚�ʏ��𐶐�����<BR>
     * <BR>
     * @@param l_accountInfo - (�ڋq����)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_newOrderSpecs - (���������e)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfoAfterRepay
     * @@roseuid 41C7E6380112
     */
    public static WEB3TPContractInfoAfterRepay createWEB3TPContractInfoAfterRepay(
        WEB3TPAccountInfo l_accountInfo,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPContractInfoAfterRepay(WEB3TPAccountInfo, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
        log.entering(STR_METHOD_NAME);

        WEB3TPContractInfoAfterRepay l_instance = new WEB3TPContractInfoAfterRepay();
        l_instance.setAccountInfo(l_accountInfo);
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (do�ԍό㌚�ʏ�񃍁[�h)<BR>
     * <BR>
     * �ԍό㌚�ʏ������[�h����
     * <BR>
     * �V�[�P���X�}�u�ԍό㌚�ʏ��)do�ԍό㌚�ʏ�񃍁[�h�v�Q��<BR>
     * <BR>
     * @@roseuid 41C7CDEE021C
     */
    public void loadContractInfoAfterRepay()
    {
        final String STR_METHOD_NAME = "loadContractInfoAfterRepay()";
        log.entering(STR_METHOD_NAME);

        //�ڋq�������擾
        WEB3TPAccountInfo l_accountInfo = getAccountInfo();
        //�M�p�ڋq�t���O���擾
        boolean l_isEquityMargin = l_accountInfo.isMarginCustomer();

        //�M�p�ڋq�łȂ��ꍇ�A���[�h�����I��
        if (l_isEquityMargin == false)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�m��̌��ʏ������[�h
        loadFixedContracts();
        //�����̌��ʏ������[�h
        loadTodaysContracts();
        //���ʕԍώw��������[�h
        loadClosingContractSpecs();
        //�m��̕ϓ��������[�h
        loadFixedHistories();
        //�����̕ϓ��������[�h
        loadTodaysHistories();
        //�����ϓ��������[�h
        loadUnexecutedOrderSpecs();
        //�����ԍϕϓ��������[�h
        loadUnexecutedRepayOrderSpecs();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�I�[�o�[���C�h)(do���ʕϓ����<����>���[�h)<BR>
     * <BR>
     * ���ʕϓ����<����>�����[�h����<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό㌚�ʏ��)do���ʕϓ����<����>���[�h�v�Q��<BR>
     * <BR>
     * @@roseuid 41C7CCA301AF
     */
    protected void loadTodaysHistories()
    {
        final String STR_METHOD_NAME = "loadTodaysHistories()";
        log.entering(STR_METHOD_NAME);

        //�����̌��ʕϓ����R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysHistories(this);

        //�擾�s�̃T�C�Y�Ń��[�v
        int l_intSize = l_rows.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow)l_rows.get(i);

            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getContractId());

            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            //�Ώی��ʏڍׂ��擾
            WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
            
            //�g�����U�N�V�����e�[�u��.�g�����U�N�V�����J�e�S�� = �M�p�ԍς̏ꍇ
            if (FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_row.getFinTransactionCateg())
                == true)
            {
                //create�ԍό㌚�ʕϓ�
                WEB3TPHistoryAfterRepay l_history =
                    WEB3TPHistoryAfterRepay.createWEB3TPHistoryAfterRepay(
                        l_targetContract,
                        getCalcCondition());
                //�����P��ID
                l_history.setOrderUnitId(l_row.getOrderUnitId());
                //�g�����U�N�V�����J�e�S��
                l_history.setTransactionCateg(l_row.getFinTransactionCateg());
                //���σt���O
                l_history.setExecuted(true);
                //�g�����U�N�V����������
                l_history.setTransactionDate(l_row.getFinTransactionTimestamp());
                //�P��
                l_history.setPrice(l_targetContractDetail.getContractPrice());
                //����
                l_history.setQuantity(l_row.getQuantity());
                //��n��
                l_history.setDeliveryDate(l_row.getDeliveryDate());
                //calc�ϓ����f��
                l_history.calcReflectDay(l_row.getDeliveryDate());
                //��n���
                l_history.setNetAmount(l_row.getNetAmount());

                //�ԍό㌚�ʕϓ������ʂ��ƕϓ����ɒǉ�
                l_historyPerContract.addHistory(l_history);
            }
            //�ȊO�̏ꍇ(�g�����U�N�V�����e�[�u��.�g�����U�N�V�����J�e�S�� = �������n)�̏ꍇ
            else
            {
                //create���ʕϓ�
                WEB3TPHistory l_history =
                    WEB3TPHistory.create(l_targetContract, getCalcCondition());
                //�g�����U�N�V�����J�e�S��
                l_history.setTransactionCateg(l_row.getFinTransactionCateg());
                //���σt���O
                l_history.setExecuted(true);
                //�g�����U�N�V����������
                l_history.setTransactionDate(l_row.getFinTransactionTimestamp());
                //�P��
                l_history.setPrice(l_targetContractDetail.getContractPrice());
                //����
                l_history.setQuantity(l_row.getQuantity());
                //��n��
                l_history.setDeliveryDate(l_row.getDeliveryDate());

                String l_strInstBranCalaCondition = this.getCalcCondition().getInstBranCalcCondition(
                    WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);
                if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(l_strInstBranCalaCondition))
                {
                    //get��Е��X�ʗ]�͌v�Z����("eqtype.swap.margin.cost.undelivered.contract.loss.div") = "1"�̏ꍇ
                    //���萔��
                    BigDecimal l_bdImportedSetupFee = new BigDecimal(Double.toString(l_row.getImportedSetupFee()));

                    //���萔�������
                    BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(Double.toString(l_row.getImportedSetupFeeTax()));

                    //���`������
                    BigDecimal l_bdImportedNameTransferFee = new BigDecimal(Double.toString(l_row.getImportedNameTransferFee()));

                    //���`�����������
                    BigDecimal l_bdImportedNameTransferFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFeeTax()));

                    //�Ǘ���
                    BigDecimal l_bdImportedManagementFee = new BigDecimal(Double.toString(l_row.getImportedManagementFee()));

                    //�Ǘ�������
                    BigDecimal l_bdImportedManagementFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFeeTax()));

                    if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                    {
                        //�Ώی��ʏڍ�.���敪 = "����"�̏ꍇ
                        //������
                        BigDecimal l_bdImportedInterestFee = new BigDecimal(Double.toString(l_row.getImportedInterestFee()));

                        //�v�Z�������ʏ��o���ݒ肷��B
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(
                            l_bdImportedNameTransferFee).add(
                            l_bdImportedNameTransferFeeTax).add(
                            l_bdImportedManagementFee).add(
                            l_bdImportedManagementFeeTax).add(
                            l_bdImportedInterestFee);
                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                    else if (ContractTypeEnum.SHORT.equals(l_targetContractDetail.getContractType()))
                    {
                        //�Ώی��ʏڍ�.���敪 = "����"�̏ꍇ
                        //�t����
                        BigDecimal l_bdImportedPayInterestFee =
                            new BigDecimal(Double.toString(l_row.getImportedPayInterestFee()));

                        //�݊���
                        BigDecimal l_bdImportedLoanEquityFee =
                            new BigDecimal(Double.toString(l_row.getImportedLoanEquityFee()));

                        //�v�Z�������ʏ��o���ݒ肷��B
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(
                            l_bdImportedNameTransferFee).add(
                            l_bdImportedNameTransferFeeTax).add(
                            l_bdImportedManagementFee).add(
                            l_bdImportedManagementFeeTax).add(
                            l_bdImportedPayInterestFee).add(
                            l_bdImportedLoanEquityFee);
                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                }
                else
                {
                    //get��Е��X�ʗ]�͌v�Z����("eqtype.swap.margin.cost.undelivered.contract.loss.div") != "1"�̏ꍇ
                    l_history.setContractTotalCost(0.0D);
                }

                //calc�ϓ����f��
                l_history.calcReflectDay(l_row.getDeliveryDate());

                //���ʕϓ������ʂ��ƕϓ����ɒǉ�
                l_historyPerContract.addHistory(l_history);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (do�����ԍϕϓ���񃍁[�h)
     * <BR>
     * �V�[�P���X�}�u(�ԍό㌚�ʏ��)do�����ԍϕϓ���񃍁[�h�v�Q��<BR>
     * <BR>
     * @@roseuid 41C7D9A40299
     */
    protected void loadUnexecutedRepayOrderSpecs()
    {
        final String STR_METHOD_NAME = "loadUnexecutedRepayOrderSpecs()";
        log.entering(STR_METHOD_NAME);

        final String l_strYYYYMMDDFormat = "yyyyMMdd";
        
        //�]�͌v�Z�������擾
        WEB3TPCalcCondition l_calcCondition = this.getCalcCondition();
    
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //�����ԍϕϓ���񃌃R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getUnExecutedOrdersAfterRepay(this);

        //�擾�s�̃T�C�Y�Ń��[�v
        int l_intSize = l_rows.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_rows.get(i);

            //���ʕԍώw����ꗗ���擾
            List l_closingContractSpecs = getClosingContractSpecs(l_row.getOrderUnitId());

            //�������ƌ��ϑ��v���擾
            double l_dblProfitLoss =
                getRepaySettleProfitLossPerOrder(l_row.getOrderUnitId(), l_closingContractSpecs);

            //���ʕԍώw����ꗗ�̃T�C�Y�Ń��[�v
            int l_intClosingSize = l_closingContractSpecs.size();
            for (int j = 0; j < l_intClosingSize; j++)
            {
                //���ʕԍώw������擾
                WEB3TPClosingContractSpec l_closingContractSpec =
                    (WEB3TPClosingContractSpec)l_closingContractSpecs.get(j);

                //�Ώی��ʂ��擾
                WEB3TPTargetContract l_targetContract =
                    getTargetContract(true, l_closingContractSpec.getContractId());

                //�Ώی��ʏڍׂ��擾
                WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
            
                //�������擾
                Date l_datOpenDate = l_targetContractDetail.getOpenDate();

                //����=�c�Ɠ�(T+0)�̏ꍇ
                if(WEB3DateUtility.compareToDay(l_datOpenDate, l_datBizDate0) == 0)
                {
                    //���������擾
                    double l_dblQuantity = l_targetContractDetail.getQuantity();
                    
                    //�ԍό�̌��������Z�o
                    double l_dblAfterQuantity = l_dblQuantity - (l_closingContractSpec.getQuantity() - l_closingContractSpec.getExecutedQuantity());
                    
                    //�������Z�o
                    double l_dblRate = l_dblAfterQuantity / l_dblQuantity;
                    
                    //���萔��
                    double l_dblSetupFee = Math.floor(l_targetContractDetail.getSetupFee() * l_dblRate);
                    
                    //���萔�������
                    double l_dblSetupFeeTax = Math.floor(l_targetContractDetail.getSetupFeeTax() * l_dblRate);
                    
                    //�Ώی��ʏڍׂɕԍό�̌������𔽉f
                    l_targetContractDetail.setQuantity(l_dblAfterQuantity);
                    
                    //�Ώی��ʏڍׂɕԍό�̌��萔���𔽉f
                    l_targetContractDetail.setSetupFee(l_dblSetupFee);
                    
                    //�Ώی��ʏڍׂɕԍό�̌��萔������ł𔽉f
                    l_targetContractDetail.setSetupFeeTax(l_dblSetupFeeTax);
                }
                                
                //���ʂ��ƕϓ������擾
                WEB3TPHistoryPerContract l_historyPerContract =
                    getHistoryPerContract(l_targetContract);

                //create�ԍό㌚�ʕϓ�
                WEB3TPHistoryAfterRepay l_history =
                    WEB3TPHistoryAfterRepay.createWEB3TPHistoryAfterRepay(
                        l_targetContract,
                        getCalcCondition());

                //�����P��ID
                l_history.setOrderUnitId(l_row.getOrderUnitId());
                //�g�����U�N�V�����J�e�S��
                l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_CLOSE_MARGIN);
                //���σt���O
                l_history.setExecuted(true);
                //�g�����U�N�V����������
                l_history.setTransactionDate(
                    WEB3DateUtility.getDate(l_row.getBizDate(), l_strYYYYMMDDFormat));
                //�P��
                l_history.setPrice(l_targetContract.getTargetContractDetail().getContractPrice());
                //����
                l_history.setQuantity(
                    l_closingContractSpec.getQuantity()
                        - l_closingContractSpec.getExecutedQuantity());
                //��n��
                l_history.setDeliveryDate(l_row.getDeliveryDate());

                /*
                 * ���o������n������A���ʕԍώw����ꗗ�̐擪�v�f�Ɋ񂹂�B
                 */
                //INDEX�J�[�\��==0�̏ꍇ
                if (j == 0)
                {
                    //��n���
                    l_history.setNetAmount(l_row.getEstimatedPrice() - l_dblProfitLoss);
                }
                //�ȊO�̏ꍇ
                else
                {
                    //��n���
                    l_history.setNetAmount(0);
                }

                //calc�ϓ����f��
                l_history.calcReflectDay(l_row.getDeliveryDate());

                //���ʕϓ������ʂ��ƕϓ����ɒǉ�
                l_historyPerContract.addHistory(l_history);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�������ƌ��ϑ��v)<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό㌚�ʏ��)get�������ƌ��ϑ��v�v�Q��<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)
     * @@param l_closingContractSpecs - (���ʕԍώw����ꗗ)
     * @@return double
     * @@roseuid 41C7E32E02F7
     */
    protected double getRepaySettleProfitLossPerOrder(
        long l_lngOrderUnitId,
        List l_closingContractSpecs)
    {
        final String STR_METHOD_NAME = "getRepaySettleProfitLossPerOrder(long, List)";
        log.entering(STR_METHOD_NAME);

        //����.���ʕԍώw����ꗗ==null�̏ꍇ
        if(l_closingContractSpecs == null || l_closingContractSpecs.size() == 0)
        {
            //0��ԋp            
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //��n������v
        double l_dblNetAmount = 0;

        //����.���ʕԍώw����ꗗ�̗v�f����LOOP����
        for (Iterator l_iterClose = l_closingContractSpecs.iterator(); l_iterClose.hasNext();)
        {
            //���ʕԍώw������擾
            WEB3TPClosingContractSpec l_closingContractSpec =
                (WEB3TPClosingContractSpec)l_iterClose.next();

            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract =
                getTargetContract(true, l_closingContractSpec.getContractId());

            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            //���ʕϓ��ꗗ���擾
            List l_lisHistorys = l_historyPerContract.getHistories();

            //����.���ʕϓ��̗v�f����LOOP����
            for (Iterator l_iter = l_lisHistorys.iterator(); l_iter.hasNext();)
            {
                //���X�g���v�f���擾
                Object l_element = (Object)l_iter.next();

                //�v�f�̃C���X�^���X���A�ԍό㌚�ʕϓ��������ꍇ
                if (l_element instanceof WEB3TPHistoryAfterRepay)
                {
                    WEB3TPHistoryAfterRepay l_historyAftreRepay =
                        (WEB3TPHistoryAfterRepay)l_element;

                    //����.�����P��ID = �ԍό㌚�ʕϓ�.get�����P��ID()�̏ꍇ
                    if (l_lngOrderUnitId == l_historyAftreRepay.getOrderUnitId())
                    {

                        /*
                         * ��n����̏W�v
                         * ��n������v = SUM(�ԍό㌚�ʕϓ�.get��n���())
                         */
                        l_dblNetAmount = l_dblNetAmount + l_historyAftreRepay.getNetAmount();
                    }
                }
            }
        }

        //��n������v��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_dblNetAmount;
    }
}
@
