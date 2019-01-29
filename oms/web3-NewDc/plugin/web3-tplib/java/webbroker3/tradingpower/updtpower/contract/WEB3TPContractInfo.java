head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPContractInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʏ��(WEB3TPContractInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
Revesion History : 2007/07/28 �Ј��� (���u)���f��No.116
Revesion History : 2008/02/01 �g�E�N�|�@@(���u)�@@�d�l�ύX�@@���f��No.254�A258
Revesion History : 2008/02/01 �����Q�@@(���u)�@@�d�l�ύX�@@���f��No.255�A256
Revesion History : 2008/11/07 �И���@@(���u)�@@�d�l�ύX�@@���f��No.363
Revesion History : 2008/11/07 ���L���E�@@(���u)�@@�d�l�ύX�@@���f��No.382
*/
package webbroker3.tradingpower.updtpower.contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.define.WEB3GentradeNumberConstDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceCallback;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.tradingpower.data.EqtypeFixedContractRow;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.tradingpower.define.WEB3TPContractAmountApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ʏ��)
 */
public class WEB3TPContractInfo extends WEB3TPAssetValuation 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPContractInfo.class);
    
    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();

    /**
     * (yyyyMMdd�t�H�[�}�b�g)
     */
    private static final String format_yyyyMMdd = "yyyyMMdd";

    /**
     * (�Ώی���)
     */
    private List targetContracts;
    
    /**
     * (���ʂ��ƕϓ����Map)
     */
    private Map contractHistories;
    
    /**
     * (���ʕԍώw����)
     */
    private Map closingContractSpecs;
    
    /**
     * @@roseuid 4104AB49004E
     */
    public WEB3TPContractInfo() 
    {
        targetContracts = new ArrayList();
        contractHistories = new HashMap();
        closingContractSpecs = new HashMap();
    }
    
    /**
     * (create���ʏ��)<BR>
     * ���ʏ��𐶐�����B<BR>
     * @@param accountInfo - (�ڋq����)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param newOrderSpecs - (���������e)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo
     * @@roseuid 40F3CC0502C4
     */
    public static WEB3TPContractInfo create(WEB3TPAccountInfo l_accountInfo, WEB3TPCalcCondition l_calcCondition, WEB3TPNewOrderSpec[] l_newOrderSpecs) 
    {
        WEB3TPContractInfo l_thisInstance = new WEB3TPContractInfo();
        l_thisInstance.setAccountInfo(l_accountInfo);
        l_thisInstance.setCalcCondition(l_calcCondition);
        l_thisInstance.setNewOrderSpecs(l_newOrderSpecs);
        return l_thisInstance;
    }
    
    /**
     * (get�����ό��ʂ̏W�v)<BR>
     * �����Ŏw�肵�����̖����ό��ʂ̏W�v������B<BR>
     * <BR>
     * �P�j�Ώی��ʈꗗ���擾����B<BR>
     * <BR>
     * �Q�j�Ώی��ʂ��猚�ʂ��ƕϓ������擾����B<BR>
     * <BR>
     * �R�j���ʂ��ƕϓ�����P�ʂƂ��Ė����ό��ʂ̏W�v������B<BR>
     * �@@�|�W�v���ځF<BR>
     * �@@�@@�E���ʑ��(n)�@@�@@�@@�@@�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).���ʑ��<BR>
     * �@@�@@�E�K�v�ۏ؋�(n)�@@�@@�@@�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�K�v�ۏ؋�<BR>
     * �@@�@@�E�����K�v�ۏ؋�(n)�@@�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�����K�v�ۏ؋�<BR>
     * �@@�@@�E���������ʑ��(n)�@@�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).���������ʑ��<BR>
     * �@@�@@�E�������K�v�ۏ؋�(n)�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�������K�v�ۏ؋�<BR>
     * �@@�@@�E�����������K�v�ۏ؋�(n)�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�����������K�v�ۏ؋�<BR>
     * �@@�@@�E�����ό��ʕ]����(n)�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�����ό��ʕ]����<BR>
     * �@@�@@�E�����ό��ʕ]���v(n)�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�����ό��ʕ]���v<BR>
     * �@@�@@�E���萔��(n)�@@�@@�@@�@@�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).���萔��<BR>
     * �@@�@@�E�����E�t������(n)�@@�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�����E�t������<BR>
     * �@@�@@�E�����E�t�����v(n)�@@�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).�����E�t�����v<BR>
     * �@@�@@�E���̑����ʏ��o��(n)�@@�@@�@@�������ʂ��Ɩ����ό��ʂ̏W�v(n).���̑����ʏ��o��<BR>
     * <BR>
     * �S�j�����ό��ʂ̏W�v���ʂ�Ԃ��B<BR>
     * <BR>
     * ��n�́A�����̎w����B<BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n)�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �@@�E�E�E���ʂ��ƕϓ����.get�����ό��ʂ̏W�v(n)<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).���ʑ���@@�@@�@@�@@�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get���ʑ��()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�K�v�ۏ؋��@@�@@�@@�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get�K�v�ۏ؋�()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�����K�v�ۏ؋��@@�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get�����K�v�ۏ؋�()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).���������ʑ���@@�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get���������ʑ��()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�������K�v�ۏ؋��@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get�������K�v�ۏ؋�()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�����������K�v�ۏ؋��@@�E�E�E�����ό��ʂ̏W�v(n).get�����������K�v�ۏ؋�()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�����ό��ʕ]�����@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get�����ό��ʕ]����()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�����ό��ʕ]���v�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get�����ό��ʕ]���v()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).���萔���@@�@@�@@�@@�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get���萔��()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�����E�t�������@@�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get�����E�t������()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).�����E�t�����v�@@�@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get�����E�t�����v()<BR>
     * �E���ʂ��Ɩ����ό��ʂ̏W�v(n).���̑����ʏ��o��@@�@@�@@�E�E�E�����ό��ʂ̏W�v(n).get���̑����ʏ��o��()<BR>
     * @@param l_datDate - (�w���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract
     * @@roseuid 40E0DEFC039B
     */
    public WEB3TPSummaryOpenContract getSummaryOpenContract(Date l_datDate) 
    {
        if(DBG)
        {
            log.debug("getSummaryOpenContract(" + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd") + ")");
        }
            
        //create�����ό��ʂ̏W�v
        WEB3TPSummaryOpenContract l_sumOpenContract = WEB3TPSummaryOpenContract.create();

        //�Ώی��ʈꗗ���擾
        List l_targetContracts = getTargetContracts();

        //�Ώی��ʈꗗ�̃T�C�Y�Ń��[�v
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            if(DBG)
            {
                log.debug("WEB3TPSummaryOpenContract Per Contract[" + i + "]");
                log.debug("contract_id=" + l_targetContract.getTargetContractDetail().getContractId());
                log.debug("is_executed=" + l_targetContract.isContractExecuted());
            }
            
            //�����ό��ʂ̏W�v(���ʂ���)
            WEB3TPSummaryOpenContract l_sumOpenContractPerContract = l_historyPerContract.getSummaryOpenContract(l_datDate);

            if(DBG)
            {
                log.debug(l_sumOpenContractPerContract.toString());
            }

            //���ʑ��
            double l_dblContractAmount = l_sumOpenContract.getContractAmount() + l_sumOpenContractPerContract.getContractAmount();
            l_sumOpenContract.setContractAmount(l_dblContractAmount);
            
            //�K�v�ۏ؋�
            double l_dblMarginDeposit = l_sumOpenContract.getMarginDeposit() + l_sumOpenContractPerContract.getMarginDeposit();
            l_sumOpenContract.setMarginDeposit(l_dblMarginDeposit);

            //�����K�v�ۏ؋�
            double l_dblCashMarginDeposit = l_sumOpenContract.getCashMarginDeposit() + l_sumOpenContractPerContract.getCashMarginDeposit();
            l_sumOpenContract.setCashMarginDeposit(l_dblCashMarginDeposit);

            //���������ʑ��
            double l_dblUnExecContractAmount = l_sumOpenContract.getUnExecContractAmount() + l_sumOpenContractPerContract.getUnExecContractAmount();
            l_sumOpenContract.setUnExecContractAmount(l_dblUnExecContractAmount);
            
            //�������K�v�ۏ؋�
            double l_dblUnExecMarginDeposit = l_sumOpenContract.getUnExecMarginDeposit() + l_sumOpenContractPerContract.getUnExecMarginDeposit();
            l_sumOpenContract.setUnExecMarginDeposit(l_dblUnExecMarginDeposit);

            //�����������K�v�ۏ؋�
            double l_dblUnExecCashMarginDeposit = l_sumOpenContract.getUnExecCashMarginDeposit() + l_sumOpenContractPerContract.getUnExecCashMarginDeposit();
            l_sumOpenContract.setUnExecCashMarginDeposit(l_dblUnExecCashMarginDeposit);
            
            //�����ό��ʕ]����
            double l_dblAssetLoss = l_sumOpenContract.getAssetLoss() + l_sumOpenContractPerContract.getAssetLoss();
            l_sumOpenContract.setAssetLoss(l_dblAssetLoss);
            
            //�����ό��ʕ]���v
            double l_dblAssetProfit = l_sumOpenContract.getAssetProfit() + l_sumOpenContractPerContract.getAssetProfit();
            l_sumOpenContract.setAssetProfit(l_dblAssetProfit);
            
            //���萔��
            double l_dblSetupFee = l_sumOpenContract.getSetupFee() + l_sumOpenContractPerContract.getSetupFee();
            l_sumOpenContract.setSetupFee(l_dblSetupFee);
            
            //�����E�t������
            double l_dblInterestLoss = l_sumOpenContract.getInterestLoss() + l_sumOpenContractPerContract.getInterestLoss();
            l_sumOpenContract.setInterestLoss(l_dblInterestLoss);
            
            //�����E�t�����v
            double l_dblInterestProfit = l_sumOpenContract.getInterestProfit() + l_sumOpenContractPerContract.getInterestProfit();
            l_sumOpenContract.setInterestProfit(l_dblInterestProfit);
            
            //���̑����ʏ��o��
            double l_dblOtherCost = l_sumOpenContract.getOtherCost() + l_sumOpenContractPerContract.getOtherCost();
            l_sumOpenContract.setOtherCost(l_dblOtherCost);
        }

        if(DBG)
        {
            log.debug("WEB3TPSummaryOpenContract Total:" + l_sumOpenContract.toString());
        }        
        return l_sumOpenContract;
    }
    
    /**
     * (get����n���ʂ̏W�v) <BR>
     * �����Ŏw�肵�����̖���n���ʂ̏W�v������B <BR>
     * <BR>
     * �P�j�Ώی��ʈꗗ���擾����B <BR>
     * <BR>
     * �Q�j�Ώی��ʂ��猚�ʂ��ƕϓ������擾����B <BR>
     * <BR>
     * �R�j���ʂ��ƕϓ�����P�ʂƂ��Ė���n���ʂ̏W�v������B <BR>
     * �|�W�v���ځF <BR>
     * �E���ʑ��(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ʑ�� <BR>
     * �E�K�v�ۏ؋�(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).�K�v�ۏ؋� <BR>
     * �E�����K�v�ۏ؋�(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).�����K�v�ۏ؋� <BR>
     * �E���ϑ�(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ϑ� <BR>
     * �E���ωv(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ωv <BR>
     * �E���ϑ� <����>(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ϑ� <����><BR>
     * �E���ωv <����>(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ωv <����><BR>
     * �E���ό��ʑO�����i�]�� <����>(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ό��ʑO�����i�]�� <����><BR>
     * �E���ϑ� <�w���>(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ϑ� <�w���><BR>
     * �E���ωv <�w���>(n) �������ʂ��Ɩ���n���ʂ̏W�v(n).���ωv <�w���><BR>
     * <BR>
     * �S�j����n���ʂ̏W�v���ʂ�Ԃ��B <BR>
     * <BR>
     * ��n�́A�����̎w����B<BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n)�@@�@@�@@�@@�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@�E�E�E���ʂ��ƕϓ����.get����n���ʂ̏W�v(n)<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ʑ���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get���ʑ��()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).�K�v�ۏ؋��@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get�K�v�ۏ؋�()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).�����K�v�ۏ؋��@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get�����K�v�ۏ؋�()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ϑ��@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get���ϑ�()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ωv�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get���ωv()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ϑ�<����>�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get���ϑ�<����>()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ωv<����>�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get���ωv<����>()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ό��ʑO�����i�]��<����>�@@�E�E�E����n���ʂ̏W�v(n).get���ό��ʑO�����i�]��<����>()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ϑ�<�w���>�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get���ϑ�<�w���>()<BR>
     * �E���ʂ��Ɩ���n���ʂ̏W�v(n).���ωv<�w���>�@@�@@�@@�@@�@@�@@�@@�E�E�E����n���ʂ̏W�v(n).get���ωv<�w���>()<BR>
     * @@param l_datDate - (�w���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract
     * @@roseuid 40E0DEFC03CA
     */
    public WEB3TPSummaryUndeliveredContract getSummaryUndeliveredContract(Date l_datDate) 
    {
        if(DBG)
        {
            log.debug("getSummaryUndeliveredContract(" + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd") + ")");
        }
            
        //create����n���ʂ̏W�v
        WEB3TPSummaryUndeliveredContract l_sumUndeliveredContract = WEB3TPSummaryUndeliveredContract.create();

        //�Ώی��ʈꗗ���擾
        List l_targetContracts = getTargetContracts();

        //�Ώی��ʈꗗ�̃T�C�Y�Ń��[�v
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            if(DBG)
            {
                log.debug("WEB3TPSummaryUndeliveredContract Per Contract[" + i + "]");
                log.debug("contract_id=" + l_targetContract.getTargetContractDetail().getContractId());
                log.debug("is_executed=" + l_targetContract.isContractExecuted());
            }
            
            //����n���ʂ̏W�v(���ʂ���)
            WEB3TPSummaryUndeliveredContract l_sumUndeliveredContractPerContract = l_historyPerContract.getSummaryUndeliveredContract(l_datDate);

            if(DBG)
            {
                log.debug(l_sumUndeliveredContractPerContract.toString());
            }

            //���ʑ��
            double l_dblContractAmount = l_sumUndeliveredContract.getContractAmount() + l_sumUndeliveredContractPerContract.getContractAmount();
            l_sumUndeliveredContract.setContractAmount(l_dblContractAmount);
            
            //�K�v�ۏ؋�
            double l_dblMarginDeposit = l_sumUndeliveredContract.getMarginDeposit() + l_sumUndeliveredContractPerContract.getMarginDeposit();
            l_sumUndeliveredContract.setMarginDeposit(l_dblMarginDeposit);

            //�����K�v�ۏ؋�
            double l_dblCashMarginDeposit = l_sumUndeliveredContract.getCashMarginDeposit() + l_sumUndeliveredContractPerContract.getCashMarginDeposit();
            l_sumUndeliveredContract.setCashMarginDeposit(l_dblCashMarginDeposit);
            
            //���ωv
            double l_dblContractProfit = l_sumUndeliveredContract.getContractProfit() + l_sumUndeliveredContractPerContract.getContractProfit();
            l_sumUndeliveredContract.setContractProfit(l_dblContractProfit);
            
            //���ϑ�
            double l_dblContractLoss = l_sumUndeliveredContract.getContractLoss() + l_sumUndeliveredContractPerContract.getContractLoss();
            l_sumUndeliveredContract.setContractLoss(l_dblContractLoss);
            
            //���ωv<����>
            double l_dblTodayRepayContractProfit = l_sumUndeliveredContract.getTodayRepayContractProfit() + l_sumUndeliveredContractPerContract.getTodayRepayContractProfit();
            l_sumUndeliveredContract.setTodayRepayContractProfit(l_dblTodayRepayContractProfit);
            
            //���ϑ�<����>
            double l_dblTodayRepayContractLoss = l_sumUndeliveredContract.getTodayRepayContractLoss() + l_sumUndeliveredContractPerContract.getTodayRepayContractLoss();
            l_sumUndeliveredContract.setTodayRepayContractLoss(l_dblTodayRepayContractLoss);
            
            //���ό��ʑO�����i�]��<����>
            double l_dblTodayRepayContractPrevAsset = l_sumUndeliveredContract.getTodayRepayContractPrevAsset() + l_sumUndeliveredContractPerContract.getTodayRepayContractPrevAsset();
            l_sumUndeliveredContract.setTodayRepayContractPrevAsset(l_dblTodayRepayContractPrevAsset);
            
            //���ωv<�w���>
            double l_dblDesignateDateContractProfit = l_sumUndeliveredContract.getDesignateDateContractProfit() + l_sumUndeliveredContractPerContract.getDesignateDateContractProfit();
            l_sumUndeliveredContract.setDesignateDateContractProfit(l_dblDesignateDateContractProfit);
            
            //���ϑ�<�w���>
            double l_dblDesignateDateContractLoss = l_sumUndeliveredContract.getDesignateDateContractLoss() + l_sumUndeliveredContractPerContract.getDesignateDateContractLoss();
            l_sumUndeliveredContract.setDesignateDateContractLoss(l_dblDesignateDateContractLoss);
        }

        if(DBG)
        {
            log.debug("WEB3TPSummaryUndeliveredContract Total:" + l_sumUndeliveredContract.toString());
        }
        return l_sumUndeliveredContract;
    }

    /**
     * (get���v��ԍρE�������n���ʂ̏W�v) <BR>
     * <BR>
     * ����.�w����̓��v��ԍρE�������n���ʂ��W�v����B <BR>
     * <BR>
     * 1)���v��ԍρE�������n���ʂ̏W�v�I�u�W�F�N�g���쐬����B<BR>
     * �@@-���v��ԍρE�������n���ʂ̏W�v.create���v��ԍρE�������n���ʂ̏W�v()���R�[�� <BR>
     * <BR>
     * 2)�Ώی��ʈꗗ���擾����B<BR>
     * �@@-this.get�Ώی��ʈꗗ()���R�[�� <BR>
     * <BR>
     * 3)���v��ԍρE�������n���ʂ̏W�v������B <BR>
     * <BR>
     * �@@���擾�����Ώی��ʈꗗ�̗v�f����LOOP������ <BR>
     * �@@�@@3-1)���ʂ��ƕϓ������擾����B <BR>
     * �@@�@@�|this.get���ʂ��ƕϓ����(:�Ώی��� = (*)�Ώی��ʃI�u�W�F�N�g)���R�[�� <BR>
     * <BR>
     * �@@�@@�@@(*)�Ώی��ʈꗗ���擾�����Ώی��ʃI�u�W�F�N�g <BR>
     * <BR>
     * �@@�@@3-2)���ʂ��Ƃ̓��v��ԍρE�������n���ʂ̏W�v�I�u�W�F�N�g���擾����B <BR>
     * �@@�@@�|���ʂ��ƕϓ����.get���v��ԍρE�������n���ʂ̏W�v(:Date = ����.�w���) <BR>
     * <BR>
     * �@@�@@3-3)�e�l���W�v����B <BR>
     * �@@�@@�|���ʑ�� = ���ʑ�� + 3-2)�Ŏ擾�������v��ԍρE�������n���ʂ̏W�v.get���ʑ��() <BR>
     * �@@�@@�|�K�v�ۏ؋� = �K�v�ۏ؋� + 3-2)�Ŏ擾�������v��ԍρE�������n���ʂ̏W�v.get�K�v�ۏ؋�() <BR>
     * �@@�@@�|�����K�v�ۏ؋� = �����K�v�ۏ؋� + 3-2)�Ŏ擾�������v��ԍρE�������n���ʂ̏W�v.get�����K�v�ۏ؋�() <BR>
     * <BR>
     * �@@�@@�|�������n���ʕ]���� = �������n���ʕ]���� + 3-2)�Ŏ擾�������v��ԍρE�������n���ʂ̏W�v.get�������n���ʕ]����() <BR>
     * �@@�@@�|�������n���ʕ]���v = �������n���ʕ]���v + 3-2)�Ŏ擾�������v��ԍρE�������n���ʂ̏W�v.get�������n���ʕ]���v() <BR>
     * �@@�@@�|�������n���ʌ��ϑ� = �������n���ʌ��ϑ� + 3-2)�Ŏ擾�������v��ԍρE�������n���ʂ̏W�v.get�������n���ʌ��ϑ�() <BR>
     * <BR>
     * 4)���v��ԍρE�������n���ʂ̏W�v�I�u�W�F�N�g�ɒl���Z�b�g���ԋp����B <BR>
     * <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.���ʑ�� = 3)�ŏW�v���ꂽ���ʑ�� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�K�v�ۏ؋� = 3)�ŏW�v���ꂽ�K�v�ۏ؋� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�����K�v�ۏ؋� = 3)�ŏW�v���ꂽ�����K�v�ۏ؋� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�������n���ʕ]���� = 3)�ŏW�v���ꂽ�������n���ʕ]���� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�������n���ʕ]���v = 3)�ŏW�v���ꂽ�������n���ʕ]���v <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�������n���ʌ��ϑ� = 3)�ŏW�v���ꂽ�������n���ʌ��ϑ� <BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract
     */
    public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
    {
        if(DBG)
        {
            log.debug("getSummaryDayTradeSwapContract(" + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd") + ")");
        }
            
        //create���v��ԍρE�������n���ʂ̏W�v
        WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract = WEB3TPSummaryDayTradeSwapContract.create();

        //�Ώی��ʈꗗ���擾
        List l_targetContracts = getTargetContracts();

        //�Ώی��ʈꗗ�̃T�C�Y�Ń��[�v
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            if(DBG)
            {
                log.debug("WEB3TPSummaryDayTradeSwapContract Per Contract[" + i + "]");
                log.debug("contract_id=" + l_targetContract.getTargetContractDetail().getContractId());
                log.debug("is_executed=" + l_targetContract.isContractExecuted());
            }
           
            //���v��ԍρE�������n���ʂ̏W�v(���ʂ���)
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContractPerContract = l_historyPerContract.getSummaryDayTradeSwapContract(l_datDate);

            if(DBG)
            {
                log.debug(l_sumDayTradeSwapContractPerContract.toString());
            }
            
            //���ʑ��
            double l_dblContractAmount = l_sumDayTradeSwapContract.getContractAmount() + l_sumDayTradeSwapContractPerContract.getContractAmount();
            l_sumDayTradeSwapContract.setContractAmount(l_dblContractAmount);
            
            //�K�v�ۏ؋�
            double l_dblMarginDeposit = l_sumDayTradeSwapContract.getMarginDeposit() + l_sumDayTradeSwapContractPerContract.getMarginDeposit();
            l_sumDayTradeSwapContract.setMarginDeposit(l_dblMarginDeposit);

            //�����K�v�ۏ؋�
            double l_dblCashMarginDeposit = l_sumDayTradeSwapContract.getCashMarginDeposit() + l_sumDayTradeSwapContractPerContract.getCashMarginDeposit();
            l_sumDayTradeSwapContract.setCashMarginDeposit(l_dblCashMarginDeposit);

            //�������n���ʕ]����
            double l_dblSwapContractAssetLoss = l_sumDayTradeSwapContract.getSwapContractAssetLoss() + l_sumDayTradeSwapContractPerContract.getSwapContractAssetLoss();
            l_sumDayTradeSwapContract.setSwapContractAssetLoss(l_dblSwapContractAssetLoss);
            
            //�������n���ʕ]���v
            double l_dblSwapContractAssetProfit = l_sumDayTradeSwapContract.getSwapContractAssetProfit() + l_sumDayTradeSwapContractPerContract.getSwapContractAssetProfit();
            l_sumDayTradeSwapContract.setSwapContractAssetProfit(l_dblSwapContractAssetProfit);

            //�������n���ʌ��ϑ�
            BigDecimal l_bdSwapContractSettleLoss = new BigDecimal(Double.toString(
                l_sumDayTradeSwapContract.getSwapContractSettleLoss()));

            BigDecimal l_bdSwapContractSettleLossPerContract = new BigDecimal(Double.toString(
                l_sumDayTradeSwapContractPerContract.getSwapContractSettleLoss()));

            double l_dblSwapContractSettleLoss =
                l_bdSwapContractSettleLoss.add(l_bdSwapContractSettleLossPerContract).doubleValue();
            l_sumDayTradeSwapContract.setSwapContractSettleLoss(l_dblSwapContractSettleLoss);
        }

        if(DBG)
        {
            log.debug("WEB3TPSummaryDayTradeSwapContract Total:" + l_sumDayTradeSwapContract.toString());
        }
        return l_sumDayTradeSwapContract;
    }

    /**
     * (get���ʖ����ꗗ)<BR>
     * ����.�w����ۗ̕L���ʖ���ID�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�Ώی��ʈꗗ���擾����B<BR>
     * �@@�E���ʏ��.get�Ώی��ʈꗗ()���R�[��<BR>
     * <BR>
     * �Q�j����.�w����ۗ̕L���ʖ���ID�ꗗ���쐬����B<BR>
     * �@@�E�ȉ��̏������A�擾�����Ώی��ʈꗗ�̗v�f����LOOP����<BR>
     * <BR>
     * �@@�Q�|�P�j�Ώی��ʈꗗ���A�Ώی��ʃI�u�W�F�N�g���擾<BR>
     * <BR>
     * �@@�Q�|�Q�j�Ώی��ʃI�u�W�F�N�g�ɕR�t�����ʂ��ƕϓ����I�u�W�F�N�g���擾<BR>
     * �@@�@@�@@�E���ʏ��.get���ʂ��ƕϓ����()���R�[��<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�Ώی��ʁF�Q�|�P�j�Ŏ擾�����Ώی��ʃI�u�W�F�N�g<BR>
     * <BR>
     *   �Q�|�R�j���ʂ��ƕϓ����I�u�W�F�N�g���A�����ό��ʂ̏W�v�I�u�W�F�N�g���擾<BR>
     * �@@�@@�@@�E���ʂ��ƕϓ����.get�����ό��ʂ̏W�v()���R�[��<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�w����F����.�w���<BR>
     * <BR>
     * �@@�Q�|�S�j���ʂ��ƕϓ����I�u�W�F�N�g���A���v��ԍρE�������n���ʂ̏W�v�I�u�W�F�N�g���擾<BR>
     * �@@�@@�@@�E���ʂ��ƕϓ����.get���v��ԍρE�������n���ʂ̏W�v���R�[��<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�w����F����.�w���<BR>
     * <BR>
     * �@@�Q�|�T�j�����ό��ʑ�����v�Z����B<BR>
     * �@@�@@�@@�@@[�v�Z��]<BR>
     * �@@�@@�@@�@@�@@�����ό��ʑ�������ʑ���{���������ʑ���{���v��ԍρE�������n���ʑ��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*)���ʑ���F�����ό��ʂ̏W�v.get���ʑ��()<BR>
     * �@@�@@�@@�@@�@@(*)���������ʑ���F�����ό��ʂ̏W�v.get���������ʑ��()<BR>
     * �@@�@@�@@�@@�@@(*)���v��ԍρE�������n���ʑ���F���v��ԍρE�������n���ʂ̏W�v.get���ʑ��()<BR>
     * <BR>
     * �@@�Q�|�U�j�ȉ��̏����ɊY������ꍇ�A�ۗL���ʖ���ID�ꗗ�ɒǉ�����B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�Q�|�T�j�Ōv�Z���������ό��ʑ�� > 0 ���@@<BR>
     * �@@�@@�@@�@@�@@�ۗL���ʖ���ID�ꗗ�ɁA�Ώی���.get�Ώی��ʏڍ�().get����ID() �����݂��Ȃ��ꍇ<BR>
     * 
     * �R�j�ۗL���ʖ���ID�ꗗ��z��ɕϊ����ĕԋp����B<BR>
     * �@@���j�ۗL���ʖ���ID�ꗗ=null�܂��́A�ۗL���ʖ���ID�ꗗ�̗v�f��=0�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@param l_contractType - ���敪
     * @@return long[]
     */
    public long[] getContractProducts(Date l_datDate, ContractTypeEnum l_contractType)
    {
        if(DBG)
        {
            log
                .debug("getContractProducts("
                        + WEB3DateUtility.formatDate(l_datDate, "yyyy/MM/dd")
                        + ")");
        }
    
        //�ۗL���ʖ����ꗗ
        List l_lisContractPruducts = new ArrayList();
    
        //�Ώی��ʈꗗ���擾
        List l_targetContracts = getTargetContracts();
    
        //�Ώی��ʈꗗ�̃T�C�Y�Ń��[�v
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract) l_targetContracts
                .get(i);
            
            //�Ώی��ʂ̌��敪���擾
            ContractTypeEnum l_contType = l_targetContract.getTargetContractDetail().getContractType();
            

            //���敪 != ����.���敪�̏ꍇ
            if(l_contType.equals(l_contractType) == false)
            {
                //���̏�����
                continue;
            }
            
            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
    
            if(DBG)
            {
                log.debug("WEB3TPTargetContract[" + i + "]");
                log.debug("contract_id="
                        + l_targetContract
                            .getTargetContractDetail().getContractId());
                log.debug("product_id="
                        + l_targetContract
                            .getTargetContractDetail().getProductId());
                log.debug("is_executed="
                        + l_targetContract.isContractExecuted());
            }
    
            //�����ό��ʂ̏W�v(���ʂ���)
            WEB3TPSummaryOpenContract l_sumOpenContractPerContract = l_historyPerContract
                .getSummaryOpenContract(l_datDate);
            //���v��ԍρE�������n���ʂ̏W�v(���ʂ���)
            WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContractPerContract = l_historyPerContract
                .getSummaryDayTradeSwapContract(l_datDate);
    
            if(DBG)
            {
                log.debug(l_sumOpenContractPerContract.toString());
                log.debug(l_sumDayTradeSwapContractPerContract.toString());
            }
    
            //�����ό��ʑ��
            double l_dblContractAmount = l_sumOpenContractPerContract
                .getContractAmount()
                    + l_sumOpenContractPerContract.getUnExecContractAmount()
                    + l_sumDayTradeSwapContractPerContract.getContractAmount();
    
            
            //�����ό��ʂ����݂���(�����ό��ʑ�� > 0)�ꍇ
            if(l_dblContractAmount > 0)
            {
                Long l_bufProductId = new Long(l_targetContract
                    .getTargetContractDetail().getProductId());
    
                if(l_lisContractPruducts.contains(l_bufProductId) == false)
                {
                    l_lisContractPruducts.add(l_bufProductId);
                }
            }
        }
    
        if(l_lisContractPruducts == null || l_lisContractPruducts.size() == 0)
        {
            return null;
        }
        
        long[] l_productIds = new long[l_lisContractPruducts.size()];
        for(int i = 0; i < l_lisContractPruducts.size(); i++)
        {
            Long l_productId = (Long) l_lisContractPruducts.get(i);
    
            l_productIds[i] = l_productId.longValue();
        }
    
        return l_productIds;
    }
    
    /**
     * (do���ʏ�񃍁[�h)<BR>
     * �W�v�ɕK�v�Ȍ��ʏ������[�h����B<BR>
     * <BR>
     * �P�j���ʏ������[�h���邩���肷��B<BR>
     * �@@�|�M�p�ڋq�����肵�A�M�p�ڋq�łȂ��ꍇ�̓��[�h�������I������B<BR>
     * <BR>
     * �Q�jdo���ʏ��<�m��>���[�h���R�[������B<BR>
     * <BR>
     * �R�jdo���ʏ��<����>���[�h���R�[������B<BR>
     * <BR>
     * �S�jdo���ʕԍώw���񃍁[�h���R�[������B<BR>
     * <BR>
     * �T�jdo�ϓ����<�m��>���[�h���R�[������B<BR>
     * <BR>
     * �U�jdo�ϓ����<����>���[�h���R�[������B<BR>
     * <BR>
     * �V�jdo�����ϓ���񃍁[�h���R�[������B<BR>
     * @@roseuid 40BE83BF030C
     */
    public void loadContractInfo() 
    {
        try
        {
            //�ڋq�������擾
            WEB3TPAccountInfo l_accountInfo = getAccountInfo();
            
            //�M�p�ڋq�t���O���擾
            boolean l_isEquityMargin = l_accountInfo.isMarginCustomer();
            
            if(DBG)
            {
                log.debug("isMarginAccount:" + l_isEquityMargin);
            }

            //�M�p�ڋq�łȂ��ꍇ�A���[�h�����I��
            if(l_isEquityMargin == false)
            {
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
        }
        finally
        {
            if(DBG)
            {
                log.debug("loadContractInfo finished:" + this.toString());
            }
        }
    }
    
    /**
     * (get�Ώی��ʈꗗ)<BR>
     * �Ώی��ʈꗗ���擾����B<BR>
     * @@return java.util.List
     * @@roseuid 40F2427C0222
     */
    public List getTargetContracts() 
    {
        return targetContracts;
    }
    
    /**
     * (get���ʕԍώw����ꗗ)<BR>
     * ���ʕԍώw����ꗗ���擾����B<BR>
     * <BR>
     * �P�j�����̒����P��ID�Ɋ֘A���錚�ʕԍώw����ꗗ��Ԃ��B<BR>
     * �Q�j���݂��Ȃ��ꍇ��null��Ԃ��B<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * @@return java.util.List
     * @@roseuid 40EE0E04026A
     */
    public List getClosingContractSpecs(long l_lngOrderUnitId) 
    {
        //Long�^�ɕϊ�
        Long l_orderUnitId = new Long(l_lngOrderUnitId);
        
        //�����P��ID���L�[�ɂ��āA���ʕԍώw������擾        
        List l_closingContractSpecs = (List)closingContractSpecs.get(l_orderUnitId);
        
        if(l_closingContractSpecs == null)
        {
            l_closingContractSpecs = new ArrayList();
        }
        
        return l_closingContractSpecs;
    }
    
    /**
     * (do���ʏ��<�m��>���[�h)<BR>
     * �m��̌��ʏ������[�h����B<BR>
     * <BR>
     * �P�j�m��̌��ʏ����擾����B<BR>
     * �@@�@@�f�[�^�x�[�X�A�N�Z�X�Ǘ�.get���ʏ��<�m��>���R�[������B<BR>
     * �@@�@@�ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * �@@�@@�f�[�^�x�[�X�A�N�Z�X�Ǘ�.get��������������R�[������B<BR>
     * <BR>
     * �Q�j�Ώی��ʏڍׂ𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E����ID�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.����ID<BR>
     * �@@�@@�@@�E����ID�@@�@@�@@�@@�@@�@@���ڋq����.����ID<BR>
     * �@@�@@�@@�E�⏕����ID�@@�@@�@@�@@���ڋq����.�⏕����ID<BR>
     * �@@�@@�@@�E�s��ID�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.�s��ID<BR>
     * �@@�@@�@@�E����ID�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.����ID<BR>
     * �@@�@@�@@�E���敪�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.���敪<BR>
     * �@@�@@�@@�E���P���@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.���P��<BR>
     * �@@�@@�@@�E���������@@�@@�@@�@@�@@���m��̌����e�[�u��.��������<BR>
     * �@@�@@�@@�E�������@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.������<BR>
     * �@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.����<BR>
     * �@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.����<BR>
     * �@@�@@�@@�E���萔���@@�@@�@@�@@�@@���m��̌����e�[�u��.���萔��<BR>
     * �@@�@@�@@�E���萔������Ł@@�@@���m��̌����e�[�u��.���萔�������<BR>
     * �@@�@@�@@�E���`�������@@�@@�@@�@@���m��̌����e�[�u��.���`������<BR>
     * �@@�@@�@@�E���`����������Ł@@���m��̌����e�[�u��.���`�����������<BR>
     * �@@�@@�@@�E�Ǘ���@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.�Ǘ���<BR>
     * �@@�@@�@@�E�Ǘ������Ł@@�@@�@@���m��̌����e�[�u��.�Ǘ�������<BR>
     * �@@�@@�@@�E�������@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.������<BR>
     * �@@�@@�@@�E�t�����@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.�t����<BR>
     * �@@�@@�@@�E�݊����@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.�݊���<BR>
     * �@@�@@�@@�E�ۏ؋����@@�@@�@@�@@�@@���m��̌����e�[�u��.�ۏ؋���<BR>
     * �@@�@@�@@�E�����ۏ؋����@@�@@�@@���m��̌����e�[�u��.�����ۏ؋���<BR>
     * �@@�@@�@@�E�ŋ敪�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.�ŋ敪<BR>
     * �@@�@@�@@�E�ٍϋ敪�@@�@@�@@�@@�@@���m��̌����e�[�u��.�ٍϋ敪<BR>
     * �@@�@@�@@�E�ٍϊ����l�@@�@@�@@�@@���m��̌����e�[�u��.�ٍϊ����l<BR>
     * �@@�@@�@@�E�O���I�l�@@�@@�@@�@@�@@��������������e�[�u��.��l(�O���I�l)<BR>
     * �@@�@@�@@�E�]���P���@@�@@�@@�@@�@@���]���P��Callback.get�]���P��<����><BR>
     * <BR>
     * �R�j�Ώی��ʂ𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E�V�K�����σt���O�@@��true<BR>
     * �@@�@@�@@�E�Ώی��ʏڍׁ@@�@@�@@�@@�����������Ώی��ʏڍ�<BR>
     * <BR>
     * �S�j���ʂ��ƕϓ����𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E���ʏ��@@��this<BR>
     * �@@�@@�@@�E�Ώی��ʁ@@�����������Ώی���<BR>
     * <BR>
     * �T�j�Ώی��ʂƌ��ʂ��ƕϓ������֘A�Â���B<BR>
     * <BR>
     * �U�j���ʕϓ��𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E�g�����U�N�V�����J�e�S���@@���V�K��<BR>
     * �@@�@@�@@�E���σt���O�@@�@@�@@�@@�@@�@@�@@��true<BR>
     * �@@�@@�@@�E�g�����U�N�V�����������@@�@@���m��̌����e�[�u��.����<BR>
     * �@@�@@�@@�E�P���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.���P��<BR>
     * �@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���m��̌����e�[�u��.��������<BR>
     * �@@�@@�@@�E�ϓ����f�J�n���@@�@@�@@�@@�@@�@@���c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�E�ϓ����f�I�����@@�@@�@@�@@�@@�@@���c�Ɠ�(T+5)<BR>
     * �@@�@@�@@���ϓ����f�J�n���A�ϓ����f�I�����͌��ʕϓ�.calc�ϓ����f���Őݒ肷��B<BR>
     * <BR>
     * �V�j���ʂ��ƕϓ����Ɍ��ʕϓ���ǉ�����B<BR>
     * @@roseuid 40BE83CC004D
     */
    protected void loadFixedContracts() 
    {
        final String STR_METHOD_NAME = "loadFixedContracts()";

        //�v�Z�������擾
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //�]���P��Callback���擾
        WEB3TPUnitPriceCallback l_unitPriceCallback = l_calcCondition.getUnitPriceCallback();

        //�m��̌��ʃ��R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getFixedContracts(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_fixed_contract row found.");

        //�擾�s�̃T�C�Y�Ń��[�v
        for(int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            EqtypeFixedContractRow l_row = (EqtypeFixedContractRow)l_rows.get(i);
            
            //��������������擾
            EqtypeTradedProductRow l_eqTradedProduct = 
                WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProduct(this,
                                                                                l_row.getProductId(),
                                                                                l_row.getMarketId(),
                                                                                WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));

            //create�Ώی��ʏڍ�
            WEB3TPTargetContractDetail l_targetContractDetail = WEB3TPTargetContractDetail.create();
            
            //�Y���f�[�^�����݂��Ȃ��ꍇ�̓f�o�b�O���O���o�͂��āA��l���Z�b�g���Ȃ��B(�����l0�̂܂�)
            if(l_eqTradedProduct == null)
            {
                if(DBG)
                {
                    StringBuffer l_strBuf = new StringBuffer("�f�[�^��������Ȃ��̂Ŋ�l(�O���I�l)=0 ������������}�X�^�[�e�[�u���̌�������:");
                    l_strBuf.append("institution_code=");
                    l_strBuf.append(this.getAccountInfo().getInstitutionCode());
                    l_strBuf.append(" product_id=");
                    l_strBuf.append(l_row.getProductId());
                    l_strBuf.append(" market_id=");
                    l_strBuf.append(l_row.getMarketId());
                    l_strBuf.append(" valid_until_biz_date=");
                    l_strBuf.append(WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));
                    String l_strMsg = l_strBuf.toString();
                    log.debug(l_strMsg);
                }
            }
            //��l(�O���I�l)���Z�b�g
            else
            {
                //��l(�O���I�l)
                l_targetContractDetail.setLastClosingPrice(l_eqTradedProduct.getLastClosingPrice());                
            }
            
            //����ID
            l_targetContractDetail.setContractId(l_row.getFixedContractId());
            //����ID
            l_targetContractDetail.setMainAccountId(getAccountInfo().getAccountId());
            //�⏕����ID
            l_targetContractDetail.setSubAccountId(getAccountInfo().getSubAccountId(true));
            //�s��ID
            l_targetContractDetail.setMarketId(l_row.getMarketId());
            //����ID
            l_targetContractDetail.setProductId(l_row.getProductId());
            //���敪
            l_targetContractDetail.setContractType(l_row.getContractType());
            //���P��
            l_targetContractDetail.setContractPrice(l_row.getContractPrice());
            //��������
            l_targetContractDetail.setOriginalQuantity(l_row.getOriginalQuantity());
            //������
            l_targetContractDetail.setQuantity(l_row.getQuantity());
            //����
            l_targetContractDetail.setOpenDate(l_row.getOpenDate());
            //����
            l_targetContractDetail.setCloseDate(l_row.getCloseDate());
            //���萔��
            l_targetContractDetail.setSetupFee(l_row.getSetupFee());
            //���萔�������
            l_targetContractDetail.setSetupFeeTax(l_row.getSetupFeeTax());
            //���`������
            l_targetContractDetail.setNameTransferFee(l_row.getNameTransferFee());
            //���`�����������
            l_targetContractDetail.setNameTransferFeeTax(l_row.getNameTransferFeeTax());
            //�Ǘ���
            l_targetContractDetail.setManagementFee(l_row.getManagementFee());
            //�Ǘ�������
            l_targetContractDetail.setManagementFeeTax(l_row.getManagementFeeTax());
            //������
            l_targetContractDetail.setInterestFee(l_row.getInterestFee());
            //�t����
            l_targetContractDetail.setPayInterestFee(l_row.getPayInterestFee());
            //�݊���
            l_targetContractDetail.setLoanEquityFee(l_row.getLoanEquityFee());
            //�ۏ؋���
            l_targetContractDetail.setMarginDepositRate(l_row.getMarginDepositRate());
            //�����ۏ؋���
            l_targetContractDetail.setCashMarginDepositRate(l_row.getCashMarginDepositRate());
            //�ŋ敪
            l_targetContractDetail.setTaxType(l_row.getTaxType());
            //�ٍϋ敪
            l_targetContractDetail.setRepaymentType(l_row.getRepaymentType());
            //�ٍϊ����l
            l_targetContractDetail.setRepaymentNum(l_row.getRepaymentNum());

            //��������
            l_targetContractDetail.setFirstOpenDate(l_row.getFirstOpenDate());

            //�]���P�������߂�
            double l_dblUnitPrice = l_unitPriceCallback.getUnitPrice(l_targetContractDetail);
            //�]���P��
            l_targetContractDetail.setUnitPrice(l_dblUnitPrice);
            
            //load�Ώی���
            WEB3TPTargetContract l_targetContract = loadTargetContract(true, l_targetContractDetail);
            
            //�Ώی��ʈꗗ�ɑΏی��ʂ�ǉ�
            List l_targetContracts = getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            //create���ʂ��ƕϓ����
            WEB3TPHistoryPerContract l_historyPerContract = WEB3TPHistoryPerContract.create(this, l_targetContract);
            
            //�Ώی��ʂƌ��ʂ��ƕϓ����̊֘A�Â�
            addHistoryPerContract(l_targetContract, l_historyPerContract);
            
            //create���ʕϓ�
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
            //�g�����U�N�V�����J�e�S��
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_OPEN_MARGIN);
            //���σt���O
            l_history.setExecuted(true);
            //�g�����U�N�V����������
            l_history.setTransactionDate(l_row.getOpenDate());
            //�P��
            l_history.setPrice(l_row.getContractPrice());
            //����
            l_history.setQuantity(l_row.getOriginalQuantity());
            //calc�ϓ����f��
            l_history.calcReflectDay(null);
            
            //���ʕϓ������ʂ��ƕϓ����ɒǉ�
            l_historyPerContract.addHistory(l_history);
        }
    }
    
    /**
     * (do���ʏ��<����>���[�h)<BR>
     * �����̌��ʏ������[�h����B<BR>
     * <BR>
     * �P�j�����̌��ʏ����擾����B<BR>
     * �@@�@@�f�[�^�x�[�X�A�N�Z�X�Ǘ�.get���ʏ��<����>���R�[������B<BR>
     * �@@�@@�ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�Ώی��ʏڍׂ𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E����ID�@@�@@�@@�@@�@@�������e�[�u��.����ID<BR>
     * �@@�@@�@@�E����ID�@@�@@�@@�@@�@@���ڋq����.����ID<BR>
     * �@@�@@�@@�E�⏕����ID�@@�@@�@@���ڋq����.�⏕����ID<BR>
     * �@@�@@�@@�E�s��ID�@@�@@�@@�@@�@@�������e�[�u��.�s��ID<BR>
     * �@@�@@�@@�E����ID�@@�@@�@@�@@�@@�������e�[�u��.����ID<BR>
     * �@@�@@�@@�E���敪�@@�@@�@@�@@�@@�������e�[�u��.���敪<BR>
     * �@@�@@�@@�E���P���@@�@@�@@�@@�@@�������e�[�u��.���P��<BR>
     * �@@�@@�@@�E���������@@�@@�@@�@@�������e�[�u��.��������<BR>
     * �@@�@@�@@�E�������@@�@@�@@�@@�@@�������e�[�u��.������<BR>
     * �@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�������e�[�u��.����<BR>
     * �@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�������e�[�u��.����<BR>
     * �@@�@@�@@�E���萔���@@�@@�@@�@@�������e�[�u��.���萔��<BR>
     * �@@�@@�@@�E���萔������Ł@@�������e�[�u��.���萔�������<BR>
     * �@@�@@�@@�E�ۏ؋����@@�@@�@@�@@�������e�[�u��.�ۏ؋���<BR>
     * �@@�@@�@@�E�����ۏ؋����@@�@@�������e�[�u��.�����ۏ؋���<BR>
     * �@@�@@�@@�E�ŋ敪�@@�@@�@@�@@�@@�������e�[�u��.�ŋ敪<BR>
     * �@@�@@�@@�E�ٍϋ敪�@@�@@�@@�@@�������e�[�u��.�ٍϋ敪<BR>
     * �@@�@@�@@�E�ٍϊ����l�@@�@@�@@�������e�[�u��.�ٍϊ����l<BR>
     * �@@�@@�@@�E�]���P���@@�@@�@@�@@���I�l�e�[�u��.�I�l(�Y���s��)<BR>
     * <BR>
     * �R�j�Ώی��ʏ��𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E�V�K�����σt���O�@@��true<BR>
     * �@@�@@�@@�E�Ώی��ʏڍׁ@@�@@�@@�@@�����������Ώی��ʏڍ�<BR>
     * <BR>
     * �S�j���ʂ��ƕϓ����𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E���ʏ��@@��this<BR>
     * �@@�@@�@@�E�Ώی��ʁ@@�����������Ώی���<BR>
     * <BR>
     * �T�j�Ώی��ʂƌ��ʂ��ƕϓ������֘A�Â���B<BR>
     * <BR>
     * �U�j���ʕϓ��𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E�g�����U�N�V�����J�e�S���@@���V�K��<BR>
     * �@@�@@�@@�E���σt���O�@@�@@�@@�@@�@@�@@�@@��true<BR>
     * �@@�@@�@@�E�g�����U�N�V�����������@@�@@�������e�[�u��.����<BR>
     * �@@�@@�@@�E�P���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������e�[�u��.���P��<BR>
     * �@@�@@�@@�E�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������e�[�u��.������<BR>
     * �@@�@@�@@�E�ϓ����f�J�n���@@�@@�@@�@@�@@�@@���c�Ɠ�(T+1)<BR>
     * �@@�@@�@@�E�ϓ����f�I�����@@�@@�@@�@@�@@�@@���c�Ɠ�(T+5)<BR>
     * �@@�@@�@@���ϓ����f�J�n���A�ϓ����f�I�����͌��ʕϓ�.calc�ϓ����f���Őݒ肷��B<BR>
     * <BR>
     * �V�j���ʂ��ƕϓ����Ɍ��ʕϓ���ǉ�����B<BR>
     * @@roseuid 40BE83F102DE
     */
    protected void loadTodaysContracts() 
    {
        final String STR_METHOD_NAME = "loadTodaysContracts()";

        //�v�Z�������擾
        WEB3TPCalcCondition l_calcCondition = getCalcCondition();
        
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //�]���P��Callback���擾
        WEB3TPUnitPriceCallback l_unitPriceCallback = l_calcCondition.getUnitPriceCallback();

        //�����̌��ʃ��R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysContracts(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_contract row found.");
        
        //�擾�s�̃T�C�Y�Ń��[�v
        for(int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            EqtypeContractRow l_row = (EqtypeContractRow)l_rows.get(i);
            
            //��������������擾
            EqtypeTradedProductRow l_eqTradedProduct = 
                WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProduct(this,
                                                                                l_row.getProductId(),
                                                                                l_row.getMarketId(),
                                                                                WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));

            //create�Ώی��ʏڍ�
            WEB3TPTargetContractDetail l_targetContractDetail = WEB3TPTargetContractDetail.create();
            
            //�Y���f�[�^�����݂��Ȃ��ꍇ�̓f�o�b�O���O���o�͂��āA��l���Z�b�g���Ȃ��B(�����l0�̂܂�)
            if(l_eqTradedProduct == null)
            {
                if(DBG)
                {
                    StringBuffer l_strBuf = new StringBuffer("�f�[�^��������Ȃ��̂Ŋ�l(�O���I�l)=0 ������������}�X�^�[�e�[�u���̌�������:");
                    l_strBuf.append("institution_code=");
                    l_strBuf.append(this.getAccountInfo().getInstitutionCode());
                    l_strBuf.append(" product_id=");
                    l_strBuf.append(l_row.getProductId());
                    l_strBuf.append(" market_id=");
                    l_strBuf.append(l_row.getMarketId());
                    l_strBuf.append(" valid_until_biz_date=");
                    l_strBuf.append(WEB3DateUtility.formatDate(l_datBizDate0, format_yyyyMMdd));
                    String l_strMsg = l_strBuf.toString();
                    log.debug(l_strMsg);
                }
            }
            //��l(�O���I�l)���Z�b�g
            else
            {
                //��l(�O���I�l)
                l_targetContractDetail.setLastClosingPrice(l_eqTradedProduct.getLastClosingPrice());                
            }

            //����ID
            l_targetContractDetail.setContractId(l_row.getContractId());
            //����ID
            l_targetContractDetail.setMainAccountId(getAccountInfo().getAccountId());
            //�⏕����ID
            l_targetContractDetail.setSubAccountId(getAccountInfo().getSubAccountId(true));
            //�s��ID
            l_targetContractDetail.setMarketId(l_row.getMarketId());
            //����ID
            l_targetContractDetail.setProductId(l_row.getProductId());
            //���敪
            l_targetContractDetail.setContractType(l_row.getContractType());
            //���P��
            l_targetContractDetail.setContractPrice(l_row.getContractPrice());
            //��������
            l_targetContractDetail.setOriginalQuantity(l_row.getOriginalQuantity());
            //������
            l_targetContractDetail.setQuantity(l_row.getQuantity());
            //����
            l_targetContractDetail.setOpenDate(l_row.getOpenDate());
            //����
            l_targetContractDetail.setCloseDate(l_row.getCloseDate());
            //���萔��
            l_targetContractDetail.setSetupFee(l_row.getSetupFee());
            //���萔�������
            l_targetContractDetail.setSetupFeeTax(l_row.getSetupFeeTax());
            
            //���`������
            l_targetContractDetail.setNameTransferFee(l_row.getNameTransferFee());
            //���`�����������
            l_targetContractDetail.setNameTransferFeeTax(l_row.getNameTransferFeeTax());
            //�Ǘ���
            l_targetContractDetail.setManagementFee(l_row.getManagementFee());
            //�Ǘ�������
            l_targetContractDetail.setManagementFeeTax(l_row.getManagementFeeTax());
            //������
            l_targetContractDetail.setInterestFee(l_row.getInterestFee());
            //�t����
            l_targetContractDetail.setPayInterestFee(l_row.getPayInterestFee());
            //�݊���
            l_targetContractDetail.setLoanEquityFee(l_row.getLoanEquityFee());

            //�ۏ؋���
            l_targetContractDetail.setMarginDepositRate(l_row.getMarginDepositRate());
            //�����ۏ؋���
            l_targetContractDetail.setCashMarginDepositRate(l_row.getCashMarginDepositRate());
            //�ŋ敪
            l_targetContractDetail.setTaxType(l_row.getTaxType());
            //�ٍϋ敪
            l_targetContractDetail.setRepaymentType(l_row.getRepaymentType());
            //�ٍϊ����l
            l_targetContractDetail.setRepaymentNum(l_row.getRepaymentNum());

            //��������
            l_targetContractDetail.setFirstOpenDate(l_row.getFirstOpenDate());
            
            //�]���P�������߂�
            double l_dblUnitPrice = l_unitPriceCallback.getUnitPrice(l_targetContractDetail);
            //�]���P���Ƃ��ăZ�b�g
            l_targetContractDetail.setUnitPrice(l_dblUnitPrice);

            //load�Ώی���
            WEB3TPTargetContract l_targetContract = loadTargetContract(true, l_targetContractDetail);
            
            //�Ώی��ʈꗗ�ɑΏی��ʂ�ǉ�
            List l_targetContracts = getTargetContracts();
            l_targetContracts.add(l_targetContract);
            
            //create���ʂ��ƕϓ����
            WEB3TPHistoryPerContract l_historyPerContract = WEB3TPHistoryPerContract.create(this, l_targetContract);
            
            //�Ώی��ʂƌ��ʂ��ƕϓ����̊֘A�Â�
            addHistoryPerContract(l_targetContract, l_historyPerContract);
            
            //create���ʕϓ�
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
            //�g�����U�N�V�����J�e�S��
            l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_OPEN_MARGIN);
            //���σt���O
            l_history.setExecuted(true);
            //�g�����U�N�V����������
            l_history.setTransactionDate(l_row.getOpenDate());
            //�P��
            l_history.setPrice(l_row.getContractPrice());
            //����
            l_history.setQuantity(l_row.getQuantity());
            //calc�ϓ����f��
            l_history.calcReflectDay(null);
            
            //���ʕϓ������ʂ��ƕϓ����ɒǉ�
            l_historyPerContract.addHistory(l_history);
        }
    }
    
    /**
     * (do���ʕԍώw���񃍁[�h)<BR>
     * ���ʕԍώw��������[�h����B<BR>
     * <BR>
     * �P�j���ʕԍώw������擾����B<BR>
     * �@@�@@�f�[�^�x�[�X�A�N�Z�X�Ǘ�.get���ʕԍώw������R�[������B<BR>
     * �@@�@@�ȍ~�̏����́A�������ʂ̊e�s�ɑ΂��Ď��{����B<BR>
     * <BR>
     * �Q�j�Ώی��ʂ��擾����B<BR>
     * <BR>
     * �R�j�Ώی��ʏ����擾����B<BR>
     * <BR>
     * �S�j���ʕԍώw����𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E����ID�@@�@@�@@�@@�����ʕԍώw����e�[�u��.����ID<BR>
     * �@@�@@�@@�E���P���@@�@@�@@�@@���Ώی��ʏڍ�.���P��<BR>
     * �@@�@@�@@�E�ԍϒ������ʁ@@�����ʕԍώw����e�[�u��.�ԍϒ�������<BR>
     * �@@�@@�@@�E�ԍϖ�萔�ʁ@@�����ʕԍώw����e�[�u��.�ԍϖ�萔��<BR>
     * <BR>
     * �T�j���[�h�ς݂̒��������肵�A������U�蕪����B<BR>
     * �@@�@@�|���[�h�ς݂̏ꍇ<BR>
     * �@@�@@�@@�E���ʕԍώw����ꗗ���擾����B<BR>
     * <BR>
     * �@@�@@�|���[�h�ς݂łȂ��ꍇ<BR>
     * �@@�@@�@@�E���ʕԍώw����ꗗ�𐶐�����B<BR>
     * <BR>
     * �U�j���ʕԍώw����ꗗ�ɐ����������ʕԍώw�����ǉ�����B<BR>
     * <BR>
     * �V�j�����ƌ��ʕԍώw����ꗗ���֘A�Â���B<BR>
     * @@roseuid 40EE04910141
     */
    protected void loadClosingContractSpecs() 
    {
        //���ʕԍώw���񃌃R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getClosingContractSpecs(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_closing_contract_spec row found.");
        
        //�擾�s�̃T�C�Y�Ń��[�v
        for(int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            EqtypeClosingContractSpecRow l_row = (EqtypeClosingContractSpecRow)l_rows.get(i);
            
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getContractId());
            
            //�Ώی��ʏڍׂ��擾
            WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();
            
            //create���ʕԍώw����
            WEB3TPClosingContractSpec l_closingContractSpec = WEB3TPClosingContractSpec.create();
            //����ID
            l_closingContractSpec.setContractId(l_row.getContractId());
            //���P��
            l_closingContractSpec.setContractPrice(l_targetContractDetail.getContractPrice());
            //�ԍϒ�������
            l_closingContractSpec.setQuantity(l_row.getQuantity());
            //�ԍϖ�萔��
            l_closingContractSpec.setExecutedQuantity(l_row.getExecutedQuantity());

            //���ʕԍώw����ꗗ�Ɍ��ʕԍώw�����ǉ�
            List l_closingContractSpecs = getClosingContractSpecs(l_row.getOrderUnitId());

            l_closingContractSpecs.add(l_closingContractSpec);
            
            //�����P��ID�ƌ��ʕԍώw����ꗗ�̊֘A�Â�
            addClosingContractSpecsPerOrderUnit(l_row.getOrderUnitId(), l_closingContractSpecs);
        }
    }
    
    /**
     * (do���ʕϓ����<�m��>���[�h)<BR>
     * ���ʕϓ����<�m��>�����[�h���� <BR>
     * <BR>
     * �V�[�P���X�}�u(���ʏ��)do���ʕϓ����<�m��>���[�h�v�Q��<BR>
     * @@roseuid 40C843C50251
     */
    protected void loadFixedHistories() 
    {
        //�m��̌��ʕϓ����R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getFixedHistories(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " fixed_fin_transaction row found.");

        //�擾�s�̃T�C�Y�Ń��[�v
        for(int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            FixedFinTransactionRow l_row = (FixedFinTransactionRow)l_rows.get(i);
            
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getFixedContractId());
            
            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
            
            //���������̕���
            WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
            double l_dblRestoreQuantity = l_targetContractDetail.getOriginalQuantity() + l_row.getQuantity();
            l_targetContractDetail.setOriginalQuantity(l_dblRestoreQuantity);
            
            //create���ʕϓ�
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
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
            //�ԍς̏ꍇ
            if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                //��n���
                l_history.setNetAmount(l_row.getNetAmount());
            }

            //�m��g�����U�N�V�����e�[�u��.�g�����U�N�V�����J�e�S�� = 60:�������n
            //�@@�ȍ~�̏��������s
            if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                String l_strInstBranCalcCondition =
                    this.getCalcCondition().getInstBranCalcCondition(
                         WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);
                //this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����
                //  �i�heqtype.swap.margin.cost.undelivered.contract.loss.div�h�j = �h1�h�̏ꍇ
                //�@@�@@�ȍ~�̏��������s
                if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(
                    l_strInstBranCalcCondition))
                {
                    //���萔��
                    BigDecimal l_bdImportedSetupFee =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFee()));

                    //���萔�������
                    BigDecimal l_bdImportedSetupFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFeeTax()));

                    //���`������
                    BigDecimal l_bdImportedNameTransferFee =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFee()));

                    //���`�����������
                    BigDecimal l_bdImportedNameTransferFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFeeTax()));

                    //�Ǘ���
                    BigDecimal l_bdImportedManagementFee =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFee()));

                    //�Ǘ�������
                    BigDecimal l_bdImportedManagementFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFeeTax()));

                    //������
                    BigDecimal l_bdImportedInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedInterestFee()));

                    //�t����
                    BigDecimal l_bdImportedPayInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedPayInterestFee()));

                    //�݊���
                    BigDecimal l_bdImportedLoanEquityFee =
                        new BigDecimal(Double.toString(l_row.getImportedLoanEquityFee()));

                    //�Ώی��ʏڍ�.���敪 = �h�����h�̏ꍇ
                    if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                    {
                        //���ʏ��o��@@=�@@�m��g�����U�N�V�����e�[�u��.���萔���@@+�@@�m��g�����U�N�V�����e�[�u��.���萔�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.���`�������@@+�@@�m��g�����U�N�V�����e�[�u��.���`�����������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ���@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.������
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                            l_bdImportedSetupFeeTax).add(l_bdImportedNameTransferFee).add(
                                l_bdImportedNameTransferFeeTax).add(l_bdImportedManagementFee).add(
                                    l_bdImportedManagementFeeTax).add(l_bdImportedInterestFee);

                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                    //�Ώی��ʏڍ�.���敪 = �h�����h�̏ꍇ
                    else if (ContractTypeEnum.SHORT.equals(l_targetContractDetail.getContractType()))
                    {
                        //���ʏ��o��@@=�@@�m��g�����U�N�V�����e�[�u��.���萔���@@+�@@�m��g�����U�N�V�����e�[�u��.���萔�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.���`�������@@+�@@�m��g�����U�N�V�����e�[�u��.���`�����������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ���@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.�t�����@@+�@@�m��g�����U�N�V�����e�[�u��.�݊���
                        BigDecimal l_bdContractTotalCost = l_bdImportedSetupFee.add(
                             l_bdImportedSetupFeeTax).add(l_bdImportedNameTransferFee).add(
                                l_bdImportedNameTransferFeeTax).add(l_bdImportedManagementFee).add(
                                    l_bdImportedManagementFeeTax).add(l_bdImportedPayInterestFee).add(
                                        l_bdImportedLoanEquityFee);

                        l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                    }
                }
                //this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z�����i
                //   �heqtype.swap.margin.cost.undelivered.contract.loss.div�h�j = NULL
                //�@@�@@�@@�@@���́Athis.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z�����i
                //   �heqtype.swap.margin.cost.undelivered.contract.loss.div�h�j != �h1�h�̏ꍇ
                //�@@�@@���ʏ��o�0
                else
                {
                    l_history.setContractTotalCost(0.0D);
                }
            }
            //��L�ȊO�̏ꍇ
            //�@@���ʏ��o�0
            else
            {
                l_history.setContractTotalCost(0.0D);
            }

            //���ʕϓ������ʂ��ƕϓ����ɒǉ�
            l_historyPerContract.addHistory(l_history);
        }

        //�Ώی��ʈꗗ���擾
        List l_targetContracts = getTargetContracts();
        
        //�Ώی��ʈꗗ�̃T�C�Y�Ń��[�v
        int l_intContractSize = l_targetContracts.size();
        for(int i = 0; i < l_intContractSize; i++)
        {
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //�V�K������
            if(l_targetContract.isContractExecuted())
            {
                //�Ώی��ʏڍׂ��擾
                WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
                
                Date l_datOpenDate = l_targetContractDetail.getOpenDate();
                Date l_datDate0 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
                double l_dblOriginalQuantity = l_targetContractDetail.getOriginalQuantity();
                double l_dblQuantity = l_targetContractDetail.getQuantity();
                
                long l_lngOriginalQuantity = new Double(l_dblOriginalQuantity).longValue();
                long l_lngQuantity = new Double(l_dblQuantity).longValue();

                //����<�c�Ɠ�(T+0) ���� ��������!=������
                if((WEB3DateUtility.compareToDay(l_datOpenDate,l_datDate0) < 0)
                    && (l_lngOriginalQuantity != l_lngQuantity))
                {
                    //������=0�́ADB�̎萔�����̍��ڂ�0�ɂȂ��Ă���̂ŁA������
                    if(l_lngQuantity > 0)
                    {
                        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
                        BigDecimal l_bdOriginalQuantity = new BigDecimal(Long.toString(l_lngOriginalQuantity));
                        BigDecimal l_bdQuantity = new BigDecimal(Long.toString(l_lngQuantity));
                        //����߂��䗦
                        double l_dblRate = l_bdOriginalQuantity.divide(l_bdQuantity, l_intScale, BigDecimal.ROUND_HALF_UP).doubleValue();

                        if(DBG)
                        {
                            StringBuffer l_sbLog = new StringBuffer("contract_id=");
                            l_sbLog.append(l_targetContractDetail.getContractId());
                            l_sbLog.append(" reverse_proportional_rate=");
                            l_sbLog.append(l_dblRate);
                            log.debug(l_sbLog.toString());
                        }
                        
                        BigDecimal l_bdRate = new BigDecimal(Double.toString(l_dblRate));
                        //���萔��
                        double l_dblSetupFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getSetupFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setSetupFee(l_dblSetupFee);
                    
                        //���萔�������
                        double l_dblSetupFeeTax =
                            new BigDecimal(Double.toString(l_targetContractDetail.getSetupFeeTax())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setSetupFeeTax(l_dblSetupFeeTax);
                    
                        //���`������
                        double l_dblNameTransferFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setNameTransferFee(l_dblNameTransferFee);
                    
                        //���`�����������
                        double l_dblNameTransferFeeTax =
                            new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFeeTax())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setNameTransferFeeTax(l_dblNameTransferFeeTax);
                    
                        //�Ǘ���
                        double l_dblManagementFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getManagementFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setManagementFee(l_dblManagementFee);
                    
                        //�Ǘ�������
                        double l_dblManagementFeeTax =
                            new BigDecimal(Double.toString(l_targetContractDetail.getManagementFeeTax())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setManagementFeeTax(l_dblManagementFeeTax);
                    
                        //������
                        double l_dblInterestFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getInterestFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setInterestFee(l_dblInterestFee);
                    
                        //�t����
                        double l_dblPayInterestFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getPayInterestFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setPayInterestFee(l_dblPayInterestFee);
                    
                        //�݊���
                        double l_dblLoanEquityFee =
                            new BigDecimal(Double.toString(l_targetContractDetail.getLoanEquityFee())).multiply(l_bdRate).doubleValue();
                        l_targetContractDetail.setLoanEquityFee(l_dblLoanEquityFee);
                    }

                    //���ʂ��ƕϓ������擾
                    WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
                    
                    //���ʕϓ��ꗗ���擾
                    List l_histories = l_historyPerContract.getHistories();
                    
                    //���ʕϓ��ꗗ�̃T�C�Y�Ń��[�v
                    int l_intHistorySize = l_histories.size();
                    for(int j = 0; j < l_intHistorySize; j++)
                    {
                        //���ʕϓ����擾
                        WEB3TPHistory l_history =  (WEB3TPHistory)l_histories.get(j);
                        
                        //�V�K��
                        if(FinTransactionCateg.EQTYPE_OPEN_MARGIN.equals(l_history.getTransactionCateg()))
                        {
                            l_history.setQuantity(l_dblOriginalQuantity);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * (do���ʕϓ����<����>���[�h)<BR>
     * ���ʕϓ����<����>�����[�h����<BR>
     * <BR>
     * �V�[�P���X�}�u(���ʏ��)do���ʕϓ����<����>���[�h�v�Q��<BR>
     * @@roseuid 40DBBCCD0196
     */
    protected void loadTodaysHistories() 
    {
        //�����̌��ʕϓ����R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getTodaysHistories(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_fin_transaction row found.");

        //�擾�s�̃T�C�Y�Ń��[�v
        for(int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow)l_rows.get(i);
            
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = getTargetContract(true, l_row.getContractId());
            
            //���ʂ��ƕϓ������擾
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
            
            //�Ώی��ʏڍׂ��擾
            WEB3TPTargetContractDetail l_targetContractDetail =  l_targetContract.getTargetContractDetail();
            
            //create���ʕϓ�
            WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
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
            //�����P��ID
            l_history.setOrderUnitId(l_row.getOrderUnitId());
            //�ԍς̏ꍇ
            if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                //��n���
                l_history.setNetAmount(l_row.getNetAmount());
            }

            if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_row.getFinTransactionCateg()))
            {
                //�m��g�����U�N�V�����e�[�u��.�g�����U�N�V�����J�e�S�� = 60:�������n
                //�ȍ~�̏��������s
                String l_strInstBranCalcCondition =
                    this.getCalcCondition().getInstBranCalcCondition(
                         WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);

                if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(
                    l_strInstBranCalcCondition))
                {
                    //this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����
                    //�i�heqtype.swap.margin.cost.undelivered.contract.loss.div�h�j = �h1�h�̏ꍇ
                    //�ȍ~�̏��������s

                    //���萔��
                    BigDecimal l_bdImportedSetupFee =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFee()));

                    //���萔�������
                    BigDecimal l_bdImportedSetupFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedSetupFeeTax()));

                    //���`������
                    BigDecimal l_bdImportedNameTransferFee =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFee()));

                    //���`�����������
                    BigDecimal l_bdImportedNameTransferFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedNameTransferFeeTax()));

                    //�Ǘ���
                    BigDecimal l_bdImportedManagementFee =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFee()));

                    //�Ǘ�������
                    BigDecimal l_bdImportedManagementFeeTax =
                        new BigDecimal(Double.toString(l_row.getImportedManagementFeeTax()));

                    //������
                    BigDecimal l_bdImportedInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedInterestFee()));

                    //�t����
                    BigDecimal l_bdImportedPayInterestFee =
                        new BigDecimal(Double.toString(l_row.getImportedPayInterestFee()));

                    //�݊���
                    BigDecimal l_bdImportedLoanEquityFee =
                        new BigDecimal(Double.toString(l_row.getImportedLoanEquityFee()));

                    if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                    {
                        //�Ώی��ʏڍ�.���敪 = �h�����h�̏ꍇ

                        //���ʏ��o��@@=�@@�m��g�����U�N�V�����e�[�u��.���萔���@@+�@@�m��g�����U�N�V�����e�[�u��.���萔�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.���`�������@@+�@@�m��g�����U�N�V�����e�[�u��.���`�����������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ���@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.������
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
                        //�Ώی��ʏڍ�.���敪 = �h�����h�̏ꍇ

                        //���ʏ��o��@@=�@@�m��g�����U�N�V�����e�[�u��.���萔���@@+�@@�m��g�����U�N�V�����e�[�u��.���萔�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.���`�������@@+�@@�m��g�����U�N�V�����e�[�u��.���`�����������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ���@@+�@@�m��g�����U�N�V�����e�[�u��.�Ǘ�������
                        //�@@�@@�@@�@@�@@�@@+�@@�m��g�����U�N�V�����e�[�u��.�t�����@@+�@@�m��g�����U�N�V�����e�[�u��.�݊���
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
                    //��L�ȊO�̏ꍇ
                    l_history.setContractTotalCost(0.0D);
                }
            }
            else
            {
                //��L�ȊO�̏ꍇ
                l_history.setContractTotalCost(0.0D);
            }

            //���ʕϓ������ʂ��ƕϓ����ɒǉ�
            l_historyPerContract.addHistory(l_history);
        }
    }
    
    /**
     * (do�����ϓ���񃍁[�h)<BR>
     * �����ϓ����������[�h����<BR>
     * <BR>
     * �V�[�P���X�}�u(���ʏ��)do�����ϓ���񃍁[�h�v�Q��<BR>
     * @@roseuid 40DBBD46030D
     */
    protected void loadUnexecutedOrderSpecs() 
    {
        final String STR_METHOD_NAME = "loadUnexecutedOrderSpecs()";

        //�����ϓ���񃌃R�[�h�Z�b�g���擾
        List l_rows = WEB3TPPersistentDataManager.getInstance().getUnExecutedOrders(this);
        
        int l_intSize = l_rows.size();
        
        log.debug(l_intSize + " eqtype_order_unit row found.");

        //�擾�s�̃T�C�Y�Ń��[�v
        for(int i = 0; i < l_intSize; i++)
        {
            //���R�[�h���擾
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_rows.get(i);
            
            //�������n�̏ꍇ
            if(OrderCategEnum.SWAP_MARGIN.equals(l_row.getOrderCateg()))
            {
                //���ʕԍώw����ꗗ���擾
                List l_closingContractSpecs = getClosingContractSpecs(l_row.getOrderUnitId());
                
                //���ʕԍώw����ꗗ�̃T�C�Y�Ń��[�v
                int l_intClosingSize = l_closingContractSpecs.size();
                for(int j = 0; j < l_intClosingSize; j++)
                {
                    //���ʕԍώw������擾
                    WEB3TPClosingContractSpec l_closingContractSpec = (WEB3TPClosingContractSpec)l_closingContractSpecs.get(j);
                    
                    //�Ώی��ʂ��擾
                    WEB3TPTargetContract l_targetContract = getTargetContract(true, l_closingContractSpec.getContractId());
                    
                    //���ʂ��ƕϓ������擾
                    WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
                    
                    //create���ʕϓ�
                    WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
                    //�g�����U�N�V�����J�e�S��
                    l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
                    //���σt���O
                    l_history.setExecuted(false);
                    //�g�����U�N�V����������
                    l_history.setTransactionDate(WEB3DateUtility.getDate(l_row.getBizDate(), format_yyyyMMdd));
                    //�P��
                    l_history.setPrice(l_targetContract.getTargetContractDetail().getContractPrice());
                    //����
                    l_history.setQuantity(l_closingContractSpec.getQuantity() - l_closingContractSpec.getExecutedQuantity());
                    //��n��
                    l_history.setDeliveryDate(l_row.getDeliveryDate());

                    String l_strInstBranCalcCondition =
                        this.getCalcCondition().getInstBranCalcCondition(
                            WEB3TPCalcCondition.EQTYPE_SWAP_MARGIN_COST_UNDELIVERED_CONTRACT_LOSS_DIV);

                    if (WEB3TPEqtypeSwapMarginCostUndeliveredContractLossDivDef.EXECUTE.equals(
                        l_strInstBranCalcCondition))
                    {
                        //this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����
                        //�i�heqtype.swap.margin.cost.undelivered.contract.loss.div�h�j = �h1�h�̏ꍇ
                        //�ȍ~�̏��������s

                        //�Ώی��ʏڍ�
                        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

                        //�Ώی��ʏڍ�.��������
                        double l_dblOriginalQuantity = l_targetContractDetail.getOriginalQuantity();

                        long l_lngOriginalQuantity = new Double(l_dblOriginalQuantity).longValue();
                        if (l_lngOriginalQuantity > 0)
                        {
                            //�Ώی��ʏڍ�.�������� > 0�̏ꍇ
                            //�ȍ~�̏��������s

                            BigDecimal l_bdRatio = new BigDecimal("0.0");
                            BigDecimal l_bdContractQuantity = new BigDecimal(Double.toString(l_closingContractSpec.getQuantity()));
                            BigDecimal l_bdExecutedQuantity =
                                new BigDecimal(Double.toString(l_closingContractSpec.getExecutedQuantity()));
                            BigDecimal l_bdQuantity = l_bdContractQuantity.subtract(l_bdExecutedQuantity);
                            long l_lngQuantity = l_bdQuantity.longValue();

                            if (l_lngQuantity == l_lngOriginalQuantity)
                            {
                                //�Ώی��ʏڍ�.�������� = ��L�Ōv�Z���������̏ꍇ
                                //���䗦 = 1
                                l_bdRatio = new BigDecimal("1.0");
                            }
                            else
                            {
                                //��L�ȊO�̏ꍇ
                                //���䗦 = ��L�Ōv�Z�������� / ��������
                                //(���Z���ʂ̊ۂ߂��s��Ȃ�)
                                BigDecimal l_bdOriginalQuantity = new BigDecimal(Double.toString(l_lngOriginalQuantity));

                                int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
                                l_bdRatio =
                                    l_bdQuantity.divide(l_bdOriginalQuantity, l_intScale, BigDecimal.ROUND_HALF_UP);
                            }

                            //���萔��
                            BigDecimal l_bdSetupFee = new BigDecimal(Double.toString(l_targetContractDetail.getSetupFee()));

                            //���萔�������
                            BigDecimal l_bdSetupFeeTax = new BigDecimal(Double.toString(l_targetContractDetail.getSetupFeeTax()));

                            //���`������
                            BigDecimal l_bdNameTransferFee =
                                new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFee()));

                            //���`�����������
                            BigDecimal l_bdNameTransferFeeTax =
                                new BigDecimal(Double.toString(l_targetContractDetail.getNameTransferFeeTax()));

                            //�Ǘ���
                            BigDecimal l_bdManagementFee =
                                new BigDecimal(Double.toString(l_targetContractDetail.getManagementFee()));

                            //�Ǘ�������
                            BigDecimal l_bdManagementFeeTax =
                                new BigDecimal(Double.toString(l_targetContractDetail.getManagementFeeTax()));

                            //������
                            BigDecimal l_bdInterestFee = new BigDecimal(Double.toString(l_targetContractDetail.getInterestFee()));

                            //�t����
                            BigDecimal l_bdPayInterestFee =
                                new BigDecimal(Double.toString(l_targetContractDetail.getPayInterestFee()));

                            //�݊���
                            BigDecimal l_bdLoanEquityFee = new BigDecimal(Double.toString(
                                l_targetContractDetail.getLoanEquityFee()));

                            //Math.floor�i�Ώی��ʏڍ�.���萔���@@�~�@@���䗦�j
                            BigDecimal l_bdSetupFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdSetupFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.���萔������Ł@@�~�@@���䗦�j
                            BigDecimal l_bdSetupFeeTaxRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdSetupFeeTax.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.���`�������@@�~�@@���䗦�j
                            BigDecimal l_bdNameTransferFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdNameTransferFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.���`����������Ł@@�~�@@���䗦�j
                            BigDecimal l_bdNameTransferFeeTaxRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdNameTransferFeeTax.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.�Ǘ���@@�~�@@���䗦�j
                            BigDecimal l_bdManagementFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdManagementFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.�Ǘ������Ł@@�~�@@���䗦�j
                            BigDecimal l_bdManagementFeeTaxRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdManagementFeeTax.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.�������@@�~�@@���䗦�j
                            BigDecimal l_bdInterestFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdInterestFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.�t�����@@�~�@@���䗦�j
                            BigDecimal l_bdPayInterestFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdPayInterestFee.multiply(l_bdRatio).doubleValue())));

                            //Math.floor�i�Ώی��ʏڍ�.�݊����@@�~�@@���䗦�j
                            BigDecimal l_bdLoanEquityFeeRatio = new BigDecimal(Double.toString(
                                Math.floor(l_bdLoanEquityFee.multiply(l_bdRatio).doubleValue())));

                            if (ContractTypeEnum.LONG.equals(l_targetContractDetail.getContractType()))
                            {
                                //�Ώی��ʏڍ�.���敪 = "����"�̏ꍇ

                                BigDecimal l_bdContractTotalCost =
                                    l_bdSetupFeeRatio.add(
                                    l_bdSetupFeeTaxRatio).add(
                                    l_bdNameTransferFeeRatio).add(
                                    l_bdNameTransferFeeTaxRatio).add(
                                    l_bdManagementFeeRatio).add(
                                    l_bdManagementFeeTaxRatio).add(
                                    l_bdInterestFeeRatio);
                                l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                            }
                            else if (ContractTypeEnum.SHORT.equals(l_targetContractDetail.getContractType()))
                            {
                                //�Ώی��ʏڍ�.���敪 = "����"�̏ꍇ

                                BigDecimal l_bdContractTotalCost =
                                    l_bdSetupFeeRatio.add(
                                    l_bdSetupFeeTaxRatio).add(
                                    l_bdNameTransferFeeRatio).add(
                                    l_bdNameTransferFeeTaxRatio).add(
                                    l_bdManagementFeeRatio).add(
                                    l_bdManagementFeeTaxRatio).add(
                                    l_bdPayInterestFeeRatio).add(
                                    l_bdLoanEquityFeeRatio);
                                l_history.setContractTotalCost(l_bdContractTotalCost.doubleValue());
                            }
                        }
                        else
                        {
                            //�Ώی��ʏڍ�.�������� <= 0�̏ꍇ
                            //���ʏ��o�� = 0
                            l_history.setContractTotalCost(0.0D);
                        }
                    }
                    else
                    {
                        //this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����
                        //�i�heqtype.swap.margin.cost.undelivered.contract.loss.div�h�j != �h1�h�̏ꍇ
                        //���ʏ��o�� = 0
                        l_history.setContractTotalCost(0.0D);
                    }

                    //calc�ϓ����f��
                    l_history.calcReflectDay(l_row.getDeliveryDate());
                    
                    //���ʕϓ������ʂ��ƕϓ����ɒǉ�
                    l_historyPerContract.addHistory(l_history);
                }
                
                //�ԍώw����ꗗ�̃T�C�Y=0�̏ꍇ(��������̏ꍇ)
                if(l_intClosingSize == 0)
                {
                    if(GtlUtils.Double.isZero(l_row.getPrice()))
                    {
                        if(GtlUtils.Double.isZero(l_row.getQuantity()))
                        {
                            //�Ώی��ʈꗗ���擾
                            List l_targetContracts = getTargetContracts();

                            //�Ώی��ʈꗗ�̃T�C�Y�Ń��[�v
                            int l_intContractSize = l_targetContracts.size();
                            for(int j = 0; j < l_intContractSize; j++)
                            {
                                //�Ώی��ʂ��擾
                                WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(j);
                                
                                //���ʂ��ƕϓ������擾
                                WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);
                                
                                //�ϓ������擾
                                List l_lisHistorys = l_historyPerContract.getHistories();
                                
                                Iterator l_iter = l_lisHistorys.iterator();
                                while(l_iter.hasNext())
                                {
                                    WEB3TPHistory l_history = (WEB3TPHistory)l_iter.next();
                                    
                                    //�����P��ID�����������́A�㏑������(�P���Ɛ���=0�ɂȂ�)
                                    if(l_history.getOrderUnitId() == l_row.getOrderUnitId())
                                    {
                                        l_history.setPrice(l_row.getPrice());
                                        l_history.setQuantity(l_row.getQuantity());
                                        
                                        if(DBG)
                                        {
                                            StringBuffer l_sbLog = new StringBuffer("swap cancel complete:contract_id=");
                                            l_sbLog.append(l_targetContract.getTargetContractDetail().getContractId());
                                            l_sbLog.append(" history=");
                                            l_sbLog.append(l_history.toString());
                                            log.debug(l_sbLog.toString());
                                        }
                                    }
                                }
                           }
                        }
                    }
                }
            }
            //�V�K���̏ꍇ
            else if(OrderCategEnum.OPEN_MARGIN.equals(l_row.getOrderCateg()))
            {
                //��������������擾
                EqtypeTradedProductRow l_eqTradedProduct = 
                    WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProduct(this, l_row.getProductId(), l_row.getMarketId(), l_row.getBizDate());

                //create�Ώی��ʏڍ�
                WEB3TPTargetContractDetail l_targetContractDetail = WEB3TPTargetContractDetail.create();
                //����ID
                l_targetContractDetail.setContractId(l_row.getOrderUnitId());
                //����ID
                l_targetContractDetail.setMainAccountId(getAccountInfo().getAccountId());
                //�⏕����ID
                l_targetContractDetail.setSubAccountId(getAccountInfo().getSubAccountId(true));
                //�s��ID
                l_targetContractDetail.setMarketId(l_row.getMarketId());
                //����ID
                l_targetContractDetail.setProductId(l_row.getProductId());
                //���敪
                l_targetContractDetail.setContractType(toContractTypeEnum(l_row.getOrderType()));
                //���P��
                l_targetContractDetail.setContractPrice(l_row.getPrice());
                //��������
                l_targetContractDetail.setOriginalQuantity(l_row.getQuantity() - l_row.getExecutedQuantity());
                //����
                l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(l_row.getBizDate(), format_yyyyMMdd));
                if(OrderTypeEnum.MARGIN_LONG.equals(l_row.getOrderType()))
                {
                    if(l_eqTradedProduct != null)
                    {
                        //�ۏ؋���
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProduct.getLongMarginDepositRate());
                        //�����ۏ؋���
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProduct.getLongCashMarginDepositRate());                    
                    }else
                    {                        
                        log.debug("search eqtype_traded_product_updq because eqtype_traded_product is not found");
                        
                        //�����������Updq���擾
                        EqtypeTradedProductUpdqRow l_eqTradedProductUpdq = 
                            WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProductUpdq(l_row.getProductId(), l_row.getMarketId(), l_row.getBizDate());

                        //�Y���f�[�^�����݂��Ȃ��ꍇ�͗�O
                        if(l_eqTradedProductUpdq == null)
                        {
                            StringBuffer l_strErrorBuf = new StringBuffer("������������}�X�^�[Updq�e�[�u���̌�������:");
                            l_strErrorBuf.append("product_id=");
                            l_strErrorBuf.append(l_row.getProductId());
                            l_strErrorBuf.append(" market_id=");
                            l_strErrorBuf.append(l_row.getMarketId());
                            l_strErrorBuf.append(" valid_until_biz_date=");
                            l_strErrorBuf.append(l_row.getBizDate());
                            String l_strErrorMsg = l_strErrorBuf.toString();
                            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErrorMsg);
                        }
                        
                        //�ۏ؋���
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProductUpdq.getLongMarginDepositRate());
                        //�����ۏ؋���
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProductUpdq.getLongCashMarginDepositRate());                    
                    }
                }
                else if(OrderTypeEnum.MARGIN_SHORT.equals(l_row.getOrderType()))
                {
                    if(l_eqTradedProduct != null)
                    {
                        //�ۏ؋���
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProduct.getShortMarginDepositRate());
                        //�����ۏ؋���
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProduct.getShortCashMarginDepositRate());
                    }else
                    {
                        log.debug("search eqtype_traded_product_updq because eqtype_traded_product is not found");

                        //�����������Updq���擾
                        EqtypeTradedProductUpdqRow l_eqTradedProductUpdq = 
                            WEB3TPPersistentDataManager.getInstance().getEqtypeTradedProductUpdq(l_row.getProductId(), l_row.getMarketId(), l_row.getBizDate());

                        //�Y���f�[�^�����݂��Ȃ��ꍇ�͗�O
                        if(l_eqTradedProductUpdq == null)
                        {
                            StringBuffer l_strErrorBuf = new StringBuffer("������������}�X�^�[Updq�e�[�u���̌�������:");
                            l_strErrorBuf.append("product_id=");
                            l_strErrorBuf.append(l_row.getProductId());
                            l_strErrorBuf.append(" market_id=");
                            l_strErrorBuf.append(l_row.getMarketId());
                            l_strErrorBuf.append(" valid_until_biz_date=");
                            l_strErrorBuf.append(l_row.getBizDate());
                            String l_strErrorMsg = l_strErrorBuf.toString();
                            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErrorMsg);
                        }
                        
                        //�ۏ؋���
                        l_targetContractDetail.setMarginDepositRate(l_eqTradedProductUpdq.getShortMarginDepositRate());
                        //�����ۏ؋���
                        l_targetContractDetail.setCashMarginDepositRate(l_eqTradedProductUpdq.getShortCashMarginDepositRate());
                    }
                }
                //�ŋ敪
                l_targetContractDetail.setTaxType(l_row.getTaxType());
                //�ٍϋ敪
                l_targetContractDetail.setRepaymentType(l_row.getRepaymentType());
                //�ٍϊ����l
                l_targetContractDetail.setRepaymentNum(l_row.getRepaymentNum());
            
                 //load�Ώی���
                WEB3TPTargetContract l_targetContract = loadTargetContract(false, l_targetContractDetail);
            
                //�Ώی��ʈꗗ�ɑΏی��ʂ�ǉ�
                List l_targetContracts = getTargetContracts();
                l_targetContracts.add(l_targetContract);
            
                //create���ʂ��ƕϓ����
                WEB3TPHistoryPerContract l_historyPerContract = WEB3TPHistoryPerContract.create(this, l_targetContract);
            
                //�Ώی��ʂƌ��ʂ��ƕϓ����̊֘A�Â�
                addHistoryPerContract(l_targetContract, l_historyPerContract);
            
                //create���ʕϓ�
                WEB3TPHistory l_history = WEB3TPHistory.create(l_targetContract, getCalcCondition());
                //�g�����U�N�V�����J�e�S��
                l_history.setTransactionCateg(FinTransactionCateg.EQTYPE_OPEN_MARGIN);
                //���σt���O
                l_history.setExecuted(false);
                //�g�����U�N�V����������
                l_history.setTransactionDate(WEB3DateUtility.getDate(l_row.getBizDate(), format_yyyyMMdd));
                //�P��
                l_history.setPrice(l_row.getPrice());
                //����
                l_history.setQuantity(l_row.getQuantity() - l_row.getExecutedQuantity());
                //calc�ϓ����f��
                l_history.calcReflectDay(l_row.getDeliveryDate());
            
                //���ʕϓ������ʂ��ƕϓ����ɒǉ�
                l_historyPerContract.addHistory(l_history);
            }
        }
    }
    
    /**
     * (load�Ώی���)<BR>
     * �Ώی��ʂ𐶐����A�Ώی��ʂ�Ԃ��B<BR>
     * <BR>
     * �P�j�Ώی��ʂ𐶐�����B<BR>
     * �@@�@@�|�ݒ荀��<BR>
     * �@@�@@�@@�E�V�K�����σt���O�@@�������̐V�K�����σt���O<BR>
     * �@@�@@�@@�E�Ώی��ʏڍׁ@@�@@�@@�@@�������̑Ώی��ʏڍ�<BR>
     * <BR>
     * �Q�j�Ώی��ʂ�Ԃ��B<BR>
     * @@param l_isNewContractExecuted - (�V�K�����σt���O)
     * @@param l_targetContractDetail - (�Ώی��ʏڍ�)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40C843F803A9
     */
    protected WEB3TPTargetContract loadTargetContract(boolean l_isNewContractExecuted, WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        //�Ώی��ʃI�u�W�F�N�g
        WEB3TPTargetContract l_targetContract = WEB3TPTargetContract.create();
        
        //�V�K�����σt���O���Z�b�g
        l_targetContract.setContractExecuted(l_isNewContractExecuted);
        
        //�Ώی��ʏڍ׃I�u�W�F�N�g���Z�b�g
        l_targetContract.setTargetContractDetail(l_targetContractDetail);
        
        return l_targetContract;
    }
    
    /**
     * (get�Ώی���)<BR>
     * �Ώی��ʂ��擾����B<BR>
     * <BR>
     * �P�j�Ώی��ʈꗗ���擾����B<BR>
     * <BR>
     * �Q�j�Ώی��ʂ���������B<BR>
     * �@@�|��������<BR>
     * �@@�@@�E�����̐V�K�����σt���O<BR>
     * �@@�@@�E�����̌���ID<BR>
     * <BR>
     * �R�j�����̌���ID�ƈ�v����Ώی��ʂ����݂���ꍇ�́A�Y������Ώی��ʂ�Ԃ��B<BR>
     * �@@�@@���݂��Ȃ��ꍇ��null��Ԃ��B<BR>
     * @@param l_isNewContractExecuted - (�V�K�����σt���O)
     * @@param l_lngContractId - (����ID)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40DBBF8E035B
     */
    protected WEB3TPTargetContract getTargetContract(boolean l_isNewContractExecuted, long l_lngContractId) 
    {
        final String STR_METHOD_NAME = "getTargetContract(boolean l_isNewContractExecuted, long l_lngContractId)";

        //�Ώی��ʈꗗ���擾
        List l_targetContracts = getTargetContracts();
        
        //�ꗗ�̃T�C�Y�Ń��[�v
        int l_intSize = l_targetContracts.size();
        for(int i = 0; i < l_intSize; i++)
        {
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_targetContracts.get(i);
            
            //�V�K�����σt���O��������
            if(l_isNewContractExecuted == l_targetContract.isContractExecuted())
            {
                //����ID��������
                if(l_lngContractId == l_targetContract.getTargetContractDetail().getContractId())
                {
                    return l_targetContract;
                }
            }
        }
        
        //�����ʂ����݂��Ȃ���ԂɂȂ�̂ŗ�O
        String l_strErroMsg = "�Ώی��ʂ̌�������:contractExecutedFlag=" + l_isNewContractExecuted
                            + " contract_id=" + l_lngContractId;
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErroMsg);
    }
    
    /**
     * (add���ʂ��ƕϓ����)<BR>
     * �Ώی��ʂƌ��ʂ��ƕϓ������֘A�Â���B<BR>
     * <BR>
     * HashMap�ɓo�^����B<BR>
     * �|�L�[�F�����̑Ώی���<BR>
     * �|�l�@@�F�����̌��ʂ��ƕϓ����<BR>
     * @@param l_targetContract - (�Ώی���)
     * @@param l_historyPerContract - (���ʂ��ƕϓ����)
     * @@roseuid 40DBBF8E03A9
     */
    protected void addHistoryPerContract(WEB3TPTargetContract l_targetContract, WEB3TPHistoryPerContract l_historyPerContract) 
    {
        contractHistories.put(l_targetContract, l_historyPerContract);
    }
    
    /**
     * (get���ʂ��ƕϓ����)<BR>
     * �����̑Ώی��ʂɊ֘A���錚�ʂ��ƕϓ������擾����B<BR>
     * @@param l_targetContract - (�Ώی���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryPerContract
     * @@roseuid 40DC09E50289
     */
    protected WEB3TPHistoryPerContract getHistoryPerContract(WEB3TPTargetContract l_targetContract) 
    {
        return (WEB3TPHistoryPerContract)contractHistories.get(l_targetContract);
    }
    
    /**
     * (add�����P�ʂ��ƌ��ʕԍώw����ꗗ)<BR>
     * �����P��ID�ƌ��ʕԍώw����ꗗ���֘A�Â���B<BR>
     * <BR>
     * HashMap�ɓo�^����B<BR>
     * �|�L�[�F�����̒����P��ID��Long�I�u�W�F�N�g<BR>
     * �|�l�@@�F�����̌��ʕԍώw����ꗗ<BR>
     * @@param l_orderUnitId - (�����P��ID)
     * @@param l_closingContractSpecs - (���ʕԍώw����)
     * @@roseuid 40EE0E1703E1
     */
    protected void addClosingContractSpecsPerOrderUnit(long l_lngOrderUnitId, List l_closingContractSpecs) 
    {
        Long l_orderUnitId = new Long(l_lngOrderUnitId);
        closingContractSpecs.put(l_orderUnitId, l_closingContractSpecs);
    }
    
    /**
     * (to���敪)<BR>
     * ������ʂ����敪�ɕϊ�����B<BR>
     * <BR>
     * �P�j�ȉ��̑Ή��ŕϊ����s���B<BR>
     * �@@�|������ʁ@@�@@�@@�@@�@@�@@�@@�@@�@@���敪<BR>
     *�@@�@@�E�����M�p�V�K���������@@���@@����<BR>
     *�@@�@@�E�����M�p�V�K���������@@���@@����<BR>
     * <BR>
     * �Q�j���敪��Ԃ��B<BR>
     * @@param l_orderType - (�������)
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum
     * @@roseuid 
     */
    protected ContractTypeEnum toContractTypeEnum(OrderTypeEnum l_orderType) 
    {
        final String STR_METHOD_NAME = "toContractTypeEnum(OrderTypeEnum l_orderType)";

        ContractTypeEnum l_contractTypeEnum = null;
        if(OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_contractTypeEnum = ContractTypeEnum.LONG;
        }
        else if(OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_contractTypeEnum = ContractTypeEnum.SHORT;
        }else
        {
            String l_strErroMsg = "���敪�ɕϊ�������OrderTypeEnum=" + l_orderType;
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME, l_strErroMsg);
        }
        
        return l_contractTypeEnum;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.appendSuper(super.toString());
        
        //�Ώی���
        List l_targetContracts = this.getTargetContracts();
        int i = 0;
        //���ʂ��ƕϓ����őΏی��ʂ�toString���Ă邽�߁A�s�v�Ƃ���
//        for(Iterator l_it = l_targetContracts.iterator();l_it.hasNext();)
//        {
//            l_builder.append("getTargetContracts" + "[" + i +"]",l_it.next());
//            i += 1;
//        }
        
        //���ʂ��ƕϓ����
        i = 0;
        for(Iterator l_it = contractHistories.keySet().iterator();l_it.hasNext();)
        {
            WEB3TPTargetContract l_key = (WEB3TPTargetContract)l_it.next();
//            l_builder.append("contractHistories" + "[" + i +"]" + ".key:",l_key);
//            l_builder.append("contractHistories" + "[" + i +"]" + ".value:",contractHistories.get(l_key));
            l_builder.append("contractHistories" + "[" + i +"]",contractHistories.get(l_key));
            i += 1;
        }
        
        //���ʕԍώw����
        i = 0;
        for(Iterator l_it = closingContractSpecs.keySet().iterator();l_it.hasNext();)
        {
            Long l_key = (Long)l_it.next();
            l_builder.append("closingContractSpecs" + "[" + i +"]" + ".key",l_key);
            l_builder.append("closingContractSpecs" + "[" + i +"]" + ".value",closingContractSpecs.get(l_key));
            i += 1;
        }
        
        return l_builder.toString();
    }
    
    /**
     * (get�����ԍό��ʑ���̏W�v)<BR>
     * (get�����ԍό��ʑ���̏W�v)<BR>
     * <BR>
     * �W�v���������ԍό��ʑ����ԋp����B<BR>
     * �i�߂�l�̌^�Fdouble�j <BR>
     * <BR>
     * �P�j�@@�������ʑ���v��J�n������擾����B<BR>
     * �@@�m�擾���@@�n<BR>
     * <BR>
     * �@@�i1�jthis.get�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i<BR>
     * �@@�@@�@@�@@�@@�@@"contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A<BR>
     * �@@�@@�@@1�FFROM_BIZ_DATE�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�Ethis.get�]�͌v�Z����().get�c�Ɠ�(T+0)<BR>
     * <BR>
     * �@@�i2�jthis.get�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i<BR>
     * �@@�@@�@@�@@�@@�@@"contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A<BR>
     * �@@�@@�@@2�FFROM_T2�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�Ethis.get�]�͌v�Z����().get�c�Ɠ�(T-2)<BR>
     * <BR>
     * �@@�i3�j�ȊO�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�Ethis.get�]�͌v�Z����().get�c�Ɠ�(T-1)<BR>
     * <BR>
     * �Q�j�@@�Ώی��ʈꗗ���擾���A������s���B<BR>
     * �m�擾���@@�n<BR>
     * �@@this.get�Ώی��ʈꗗ()<BR>
     * �@@�� this.get�Ώی��ʈꗗ()�@@==�@@null�@@����<BR>
     * �@@this.get�Ώی��ʈꗗ().size()�@@==�@@0 �̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * �R�j�@@�擾�����Ώی��ʈꗗ�̗v�f����LOOP���A�ȉ��̏������s���B<BR>
     * <BR>
     * �i1�j �Ώی��ʈꗗ���A�Ώی��ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �i2�j �Ώی��ʃI�u�W�F�N�g���A�Ώی��ʏڍ׃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �m�擾���@@�n<BR>
     * �@@�Ώی���.get�Ώی��ʏڍ�()<BR>
     * <BR>
     * �i3�j �Ώی��ʏڍ׃I�u�W�F�N�g���ȉ��̏����𖞂����ꍇ�A�i4�j�ȍ~�̏������s���B<BR>
     * �m���o�����n<BR>
     * �@@�Ώی��ʏڍ�.get����()�@@<�@@�P�j�Ŏ擾�������t<BR>
     * <BR>
     * �i4�j ���P�����擾����B<BR>
     * �m�擾���@@�n<BR>
     * �@@�Ώی��ʏڍ�.get���P��()<BR>
     * <BR>
     * �i5�j �Ώی��ʃI�u�W�F�N�g���L�[�Ƃ��āA<BR>
     * �@@���ʂ��ƕϓ����I�u�W�F�N�g���擾���A������s���B<BR>
     * �m�擾���@@�n<BR>
     * �@@���ʏ��.get���ʂ��ƕϓ����(�Ώی��ʁF�i1�j�̑Ώی��ʃI�u�W�F�N�g) <BR>
     * �@@�� ���ʏ��.get���ʂ��ƕϓ����()�̖߂�l�@@!=�@@null�̏ꍇ�A�i6�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �i6�j �����ԍό��ʊ������擾����B<BR>
     * �m�擾���@@�n<BR>
     * �@@���ʂ��ƕϓ����.get�����ԍό��ʊ����̏W�v()<BR>
     * <BR>
     * �i7�j �ԍϑ�����v�Z����B<BR>
     * �m�v�Z���n<BR>
     * �@@�ԍϑ���@@=�@@�i(4)�Ŏ擾�������P���j�@@�~�@@�i(6)�Ŏ擾���������j<BR>
     * <BR>
     * �i8�j �v�Z���ʂ��W�v����B<BR>
     * �m�v�Z���n<BR>
     * �@@�W�v���ʁ@@=�@@�W�v���ʁ@@�{�@@(7)�Ōv�Z�����ԍϑ�� <BR>
     * <BR>
     * �S�j�@@�ԍϑ���̏W�v���ʂ�ԋp����B<BR>
     * <BR>
     * @@return double
     */
    public double getSummaryTodayRepayContractAmount()
    {
        final String STR_METHOD_NAME = "getSummaryTodayRepayContractAmount()";
        log.entering(STR_METHOD_NAME);

        //this.get�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i"contractamount.apply.date"�F�������ʑ���v��J�n���j
        String l_strContractApplyDate =
            this.getCalcCondition().getInstBranCalcCondition(
                WEB3TPCalcCondition.CONTRACTAMOUNT_APPLY_DATE);

        //�������ʑ���v��J�n������擾����B
        //�@@�i1�jthis.get�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i"contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A
        // 1�FFROM_BIZ_DATE�̏ꍇ
        //�@@�Ethis.get�]�͌v�Z����().get�c�Ɠ�(T+0)
        // �i2�jthis.get�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i"contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A
        // 2�FFROM_T2�̏ꍇ
        // �Ethis.get�]�͌v�Z����().get�c�Ɠ�(T-2)
        // �i3�j�ȊO�̏ꍇ
        // �Ethis.get�]�͌v�Z����().get�c�Ɠ�(T-1)
        Date l_datBizDate = null;
        if (WEB3TPContractAmountApplyDateDef.FROM_BIZ_DATE.equals(l_strContractApplyDate))
        {
            l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_T2.equals(l_strContractApplyDate))
        {
            l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_MINUS2);
        }
        else
        {
            l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
        }

        //�Ώی��ʈꗗ���擾���A������s���B
        List l_lisTargetContracts = this.getTargetContracts();

        //�����̏W�v
        double l_dblSumAmount = 0;
        BigDecimal l_bdSumAmount = new BigDecimal(Double.toString(l_dblSumAmount));

        //�� this.get�Ώی��ʈꗗ()�@@==�@@null�@@���� this.get�Ώی��ʈꗗ().size()�@@==�@@0 �̏ꍇ�A0��ԋp����B
        if (l_lisTargetContracts == null || l_lisTargetContracts.size() == 0)
        {
            log.debug(" �W�v���� = " + l_dblSumAmount);
            log.exiting(STR_METHOD_NAME);
            return l_dblSumAmount;
        }

        int l_intSize = l_lisTargetContracts.size();

        for (int i = 0; i < l_intSize; i++)
        {
            //�Ώی��ʈꗗ���A�Ώی��ʃI�u�W�F�N�g���擾����B
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_lisTargetContracts.get(i);

            //�Ώی��ʃI�u�W�F�N�g���A�Ώی��ʏڍ׃I�u�W�F�N�g���擾����B
            WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

            Date l_datOpenDate = l_targetContractDetail.getOpenDate();

            //�Ώی��ʏڍ�.get����()�@@<�@@�P�j�Ŏ擾�������t
            //�ȍ~�̏������s���B
            if (WEB3DateUtility.compareToDay(l_datOpenDate, l_datBizDate) >= 0)
            {
                continue;
            }

            //���P�����擾����B
            double l_dblContractPrice = l_targetContractDetail.getContractPrice();

            //�Ώی��ʃI�u�W�F�N�g���L�[�Ƃ��āA���ʂ��ƕϓ����I�u�W�F�N�g���擾���A������s���B
            WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

            //�� ���ʏ��.get���ʂ��ƕϓ����()�̖߂�l�@@!=�@@null�̏ꍇ�A�i6�j�ȍ~�̏������s���B
            if (l_historyPerContract == null)
            {
                continue;
            }

            //�����ԍό��ʊ������擾����B
            double l_dblRepayContractQuantity =
                l_historyPerContract.getSummaryTodayRepayContractQuantity();

            //�ԍϑ�����v�Z����B
            BigDecimal l_bdContractPric = new BigDecimal(Double.toString(l_dblContractPrice));
            BigDecimal l_bdRepayContractQuantity = new BigDecimal(Double.toString(l_dblRepayContractQuantity));
            BigDecimal l_bdAmount = l_bdContractPric.multiply(l_bdRepayContractQuantity);

            //�v�Z���ʂ��W�v����B
            l_bdSumAmount = l_bdSumAmount.add(l_bdAmount);
        }

        //�ԍϑ���̏W�v���ʂ�ԋp����B
        log.debug(" �W�v���� = " + l_bdSumAmount.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdSumAmount.doubleValue();
    }

    /**
     * (calc�������ƕK�v�ۏ؋�) <BR>
     * ����.�w����̈���.����ID�̕K�v�ۏ؋���ԋp����B <BR>
     * <BR>
     * �P�j�Ώی��ʈꗗ���쐬����B <BR>
     * �@@�E���ʏ��.get�Ώی��ʈꗗ()���R�[�� <BR>
     * <BR>
     * �Q�j�������ƕK�v�ۏ؋����W�v����B <BR>
     * <BR>
     * �@@�E�ȉ��̏������A�擾�����Ώی��ʈꗗ�̗v�f����LOOP���� <BR>
     * �@@�@@�Q�|�P�j�Ώی��ʈꗗ���A�Ώی��ʃI�u�W�F�N�g���擾 <BR>
     * <BR>
     * �@@�@@�Q�|�Q�j�Ώی��ʃI�u�W�F�N�g���A����ID���擾����B <BR>
     * �@@�@@�@@�@@ �E�Ώی���.get�Ώی��ʏڍ�().get����ID�i�j���R�[�� <BR>
     * <BR>
     * �@@[�Ώی���.get�Ώی��ʏڍ�().get����ID�i�j== ����.����ID]�̏ꍇ�ȉ����������{ <BR>
     * <BR>
     * �@@�@@�Q�|�R�j�Ώی��ʃI�u�W�F�N�g�ɕR�t�����ʂ��ƕϓ����I�u�W�F�N�g���擾 <BR>
     * �@@�@@�@@�@@�E���ʏ��.get���ʂ��ƕϓ����()���R�[�� <BR>
     * �@@�@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�@@�Ώی��ʁF�Q�|�P�j�Ŏ擾�����Ώی��ʃI�u�W�F�N�g <BR>
     * <BR>
     * �@@�@@�Q�|�S�j���ʂ��ƕϓ����I�u�W�F�N�g���A�����ό��ʂ̏W�v�I�u�W�F�N�g���擾 <BR>
     * �@@�@@�@@�@@�E���ʂ��ƕϓ����.get�����ό��ʂ̏W�v()���R�[�� <BR>
     * �@@�@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�@@�w����F����.�w��� <BR>
     * <BR>
     * �@@�@@�Q�|�T�j���ʂ��ƕϓ����I�u�W�F�N�g���A���v��ԍρE�������n���ʂ̏W�v�I�u�W�F�N�g���擾 <BR>
     * �@@�@@�@@�@@�E���ʂ��ƕϓ����.get���v��ԍρE�������n���ʂ̏W�v���R�[�� <BR>
     * �@@�@@�@@�@@�@@[����] <BR>
     * �@@�@@�@@�@@�@@�@@�w����F����.�w��� <BR>
     * <BR>
     * �@@�@@�Q�|�U�j�����ό��ʕK�v�ۏ؋����v�Z����B <BR>
     * �@@�@@�@@�@@�@@[�v�Z��] <BR>
     * �@@�@@�@@�@@�@@�@@�����ό��ʕK�v�ۏ؋����K�v�ۏ؋��{�������K�v�ۏ؋��{���v��ԍρE�������n�K�v�ۏ؋� <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@(*)�K�v�ۏ؋��F�����ό��ʂ̏W�v.get�K�v�ۏ؋� () <BR>
     * �@@�@@�@@�@@�@@�@@(*)�������K�v�ۏ؋��F�����ό��ʂ̏W�v.get�������K�v�ۏ؋� () <BR>
     * �@@�@@�@@�@@�@@�@@(*)���v��ԍρE�������n�K�v�ۏ؋��F���v��ԍρE�������n���ʂ̏W�v.get�K�v�ۏ؋� () <BR>
     * <BR>
     * �@@�@@�Q�|�V�j�@@�������ƕK�v�ۏ؋��@@���@@�������ƕK�v�ۏ؋��@@+�@@�����ό��ʕK�v�ۏ؋� <BR>
     * <BR>
     * �R�j�������ƕK�v�ۏ؋���ԋp����B<BR>
     * @@param l_datDate - (�w���)
     * @@param l_lngProductId - (����ID)
     * @@return double
     */
    public double calcProductMarginDeposit(Date l_datDate, long l_lngProductId)
    {
        final String STR_METHOD_NAME = "calcProductMarginDeposit(Date, long)";
        log.entering(STR_METHOD_NAME);

        //�Ώی��ʈꗗ���쐬
        List l_lisTargetContracts = getTargetContracts();

        //�������ƕK�v�ۏ؋�
        BigDecimal l_bdProductMarginDeposit = new BigDecimal("0");

        //�Ώی��ʈꗗ�̗v�f����LOOP����
        int l_intSize = l_lisTargetContracts.size();
        for (int i = 0; i < l_intSize; i++)
        {
            //�Ώی��ʂ��擾
            WEB3TPTargetContract l_targetContract = (WEB3TPTargetContract)l_lisTargetContracts.get(i);

            //�Ώی��ʂ̖���ID���擾
            long l_lngContractProductId = l_targetContract.getTargetContractDetail().getProductId();
            log.debug("����ID[" + i + "] = " + l_lngContractProductId);

            //[�Ώی���.get�Ώی��ʏڍ�().get����ID�i�j== ����.����ID]�̏ꍇ�ȉ����������{
            if (l_lngContractProductId == l_lngProductId)
            {
                //���ʂ��ƕϓ����
                WEB3TPHistoryPerContract l_historyPerContract = getHistoryPerContract(l_targetContract);

                //�����ό��ʂ̏W�v()
                WEB3TPSummaryOpenContract l_summaryOpenContract =
                    l_historyPerContract.getSummaryOpenContract(l_datDate);

                //���v��ԍρE�������n���ʂ̏W�v
                WEB3TPSummaryDayTradeSwapContract l_summaryDayTradeSwapContract =
                    l_historyPerContract.getSummaryDayTradeSwapContract(l_datDate);

                //�K�v�ۏ؋�
                double l_dblMarginDeposit = l_summaryOpenContract.getMarginDeposit();

                //�������K�v�ۏ؋�
                double l_dblUnExecMarginDeposit = l_summaryOpenContract.getUnExecMarginDeposit();

                //���v��ԍρE�������n�K�v�ۏ؋�
                double l_dblContractMarginDeposit = l_summaryDayTradeSwapContract.getMarginDeposit();

                //�����ό��ʕK�v�ۏ؋����K�v�ۏ؋��{�������K�v�ۏ؋��{���v��ԍρE�������n�K�v�ۏ؋�
                BigDecimal l_bdMarginDeposit = new BigDecimal(String.valueOf(l_dblMarginDeposit));
                BigDecimal l_bdUnExecMarginDeposit = new BigDecimal(String.valueOf(l_dblUnExecMarginDeposit));
                BigDecimal l_bdContractMarginDeposit = new BigDecimal(String.valueOf(l_dblContractMarginDeposit));
                BigDecimal l_bdSummaryOpenContractMarginDeposit =
                    l_bdMarginDeposit.add(l_bdUnExecMarginDeposit).add(l_bdContractMarginDeposit);

                //�������ƕK�v�ۏ؋��@@���@@�������ƕK�v�ۏ؋��@@+�@@�����ό��ʕK�v�ۏ؋�
                l_bdProductMarginDeposit = l_bdProductMarginDeposit.add(l_bdSummaryOpenContractMarginDeposit);
            }
        }
        log.debug("�w��� = " + l_datDate);
        log.debug("����ID = " + l_lngProductId);
        log.debug("�������ƕK�v�ۏ؋� = " + l_bdProductMarginDeposit.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdProductMarginDeposit.doubleValue();
    }
}
@
