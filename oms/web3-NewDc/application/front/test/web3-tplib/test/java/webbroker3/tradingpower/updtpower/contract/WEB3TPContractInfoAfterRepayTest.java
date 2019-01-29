head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPContractInfoAfterRepayTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �iWEB3TPContractInfoAfterRepayTest.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/02 �g�E�N�| (���u) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPContractInfoAfterRepayTest extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPContractInfoAfterRepayTest.class);

    WEB3TPContractInfoAfterRepay l_contractInfoAfterRepay = null;
    
    WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();

    public WEB3TPContractInfoAfterRepayTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_contractInfoAfterRepay = new WEB3TPContractInfoAfterRepayForTest();
    }

    protected void tearDown() throws Exception
    {
        l_contractInfoAfterRepay = null;
        super.tearDown();
    }

    /**
     * do���ʕϓ����<����>���[�h<BR>
     * this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����(<BR>
     * �@@"eqtype.swap.margin.cost.undelivered.contract.loss.div") = "1"�̏ꍇ<BR>
     * �@@���@@�Ώی��ʏڍ�.���敪 = "����"�̏ꍇ<BR>
     */
    public void testLoadTodaysHistories_0001()
    {
        final String STR_METHOD_NAME = "testLoadTodaysHistories_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_hsSubAccountIds = new Hashtable();
            l_hsSubAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hsSubAccountIds);

            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }

            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition(
                "eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");

            l_contractInfoAfterRepay.setAccountInfo(l_accountInfo);
            l_contractInfoAfterRepay.setCalcCondition(l_calcCondition);
            
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            EqtypeFinTransactionParams l_finTransactionParams = TestDBUtility.getEqtypeFinTransactionParams();
            l_finTransactionParams.setAccountId(333812512203L);
            l_finTransactionParams.setSubAccountId(33381251220301L);
            l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080202", "yyyyMMdd"));
            l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_finTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080202","yyyyMMdd"));
            l_finTransactionParams.setImportedSetupFee(10);
            l_finTransactionParams.setImportedSetupFeeTax(10);
            l_finTransactionParams.setImportedNameTransferFee(10);
            l_finTransactionParams.setImportedNameTransferFeeTax(10);
            l_finTransactionParams.setImportedManagementFee(10);
            l_finTransactionParams.setImportedManagementFeeTax(10);
            l_finTransactionParams.setImportedInterestFee(10);
            l_finTransactionParams.setImportedPayInterestFee(20);
            TestDBUtility.insertWithDel(l_finTransactionParams);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContract.setContractExecuted(true);
            l_targetContractDetail.setContractId(12347);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            
            l_contractInfoAfterRepay.addHistoryPerContract(l_targetContract, l_historyPerContract);

            l_contractInfoAfterRepay.loadTodaysHistories();
            
            WEB3TPHistoryPerContract l_historyPerContractResult =
                l_contractInfoAfterRepay.getHistoryPerContract(l_targetContract);
            WEB3TPHistory l_history = (WEB3TPHistory)l_historyPerContractResult.getHistories().get(0);
            assertEquals("70.0", "" + l_history.getContractTotalCost());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * do���ʕϓ����<����>���[�h<BR>
     * this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����(<BR>
     *   "eqtype.swap.margin.cost.undelivered.contract.loss.div") = "1"�̏ꍇ<BR>
     *   ���@@�Ώی��ʏڍ�.���敪 = "����"�̏ꍇ<BR>
     */
    public void testLoadTodaysHistories_0002()
    {
        final String STR_METHOD_NAME = "testLoadTodaysHistories_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_hsSubAccountIds = new Hashtable();
            l_hsSubAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hsSubAccountIds);

            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }

            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition(
                "eqtype.swap.margin.cost.undelivered.contract.loss.div", "1");

            l_contractInfoAfterRepay.setAccountInfo(l_accountInfo);
            l_contractInfoAfterRepay.setCalcCondition(l_calcCondition);
            
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            EqtypeFinTransactionParams l_finTransactionParams = TestDBUtility.getEqtypeFinTransactionParams();
            l_finTransactionParams.setAccountId(333812512203L);
            l_finTransactionParams.setSubAccountId(33381251220301L);
            l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080202", "yyyyMMdd"));
            l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_finTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080202","yyyyMMdd"));
            l_finTransactionParams.setImportedSetupFee(10);
            l_finTransactionParams.setImportedSetupFeeTax(10);
            l_finTransactionParams.setImportedNameTransferFee(10);
            l_finTransactionParams.setImportedNameTransferFeeTax(10);
            l_finTransactionParams.setImportedManagementFee(10);
            l_finTransactionParams.setImportedManagementFeeTax(10);
            l_finTransactionParams.setImportedInterestFee(10);
            l_finTransactionParams.setImportedPayInterestFee(20);
            l_finTransactionParams.setImportedLoanEquityFee(30);
            
            TestDBUtility.insertWithDel(l_finTransactionParams);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContract.setContractExecuted(true);
            l_targetContractDetail.setContractId(12347);
            l_targetContractDetail.setContractType(ContractTypeEnum.SHORT);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            
            l_contractInfoAfterRepay.addHistoryPerContract(l_targetContract, l_historyPerContract);

            l_contractInfoAfterRepay.loadTodaysHistories();
            
            WEB3TPHistoryPerContract l_historyPerContractResult =
                l_contractInfoAfterRepay.getHistoryPerContract(l_targetContract);
            WEB3TPHistory l_history = (WEB3TPHistory)l_historyPerContractResult.getHistories().get(0);
            assertEquals("110.0", "" + l_history.getContractTotalCost());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * do���ʕϓ����<����>���[�h<BR>
     * this.get�]�͌v�Z����.get��Е��X�ʗ]�͌v�Z����(<BR>
     *   "eqtype.swap.margin.cost.undelivered.contract.loss.div") != "1"�̏ꍇ<BR>
     */
    public void testLoadTodaysHistories_0003()
    {
        final String STR_METHOD_NAME = "testLoadTodaysHistories_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            Hashtable l_hsSubAccountIds = new Hashtable();
            l_hsSubAccountIds.put(new Long(33381251220301L), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_accountInfo.setSubAccountIds(l_hsSubAccountIds);

            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            Date[] l_datBizDates = new Date[8];
            for (int i = 1; i <= 8; i++)
            {
                l_datBizDates[i - 1] = WEB3DateUtility.getDate("2008020" + i, "yyyyMMdd");
            }

            l_calcCondition.setBizDate(l_datBizDates);
            l_calcCondition.addInstBranCalcCondition(
                "eqtype.swap.margin.cost.undelivered.contract.loss.div", "0");

            l_contractInfoAfterRepay.setAccountInfo(l_accountInfo);
            l_contractInfoAfterRepay.setCalcCondition(l_calcCondition);
            
            TestDBUtility.deleteAll(EqtypeFinTransactionRow.TYPE);
            EqtypeFinTransactionParams l_finTransactionParams = TestDBUtility.getEqtypeFinTransactionParams();
            l_finTransactionParams.setAccountId(333812512203L);
            l_finTransactionParams.setSubAccountId(33381251220301L);
            l_finTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_SWAP_MARGIN);
            l_finTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20080202", "yyyyMMdd"));
            l_finTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_finTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20080202","yyyyMMdd"));
            l_finTransactionParams.setImportedSetupFee(10);
            l_finTransactionParams.setImportedSetupFeeTax(10);
            l_finTransactionParams.setImportedNameTransferFee(10);
            l_finTransactionParams.setImportedNameTransferFeeTax(10);
            l_finTransactionParams.setImportedManagementFee(10);
            l_finTransactionParams.setImportedManagementFeeTax(10);
            l_finTransactionParams.setImportedInterestFee(10);
            l_finTransactionParams.setImportedPayInterestFee(20);
            l_finTransactionParams.setImportedLoanEquityFee(30);
            
            TestDBUtility.insertWithDel(l_finTransactionParams);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContract.setContractExecuted(true);
            l_targetContractDetail.setContractId(12347);
            l_targetContractDetail.setContractType(ContractTypeEnum.LONG);
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            
            l_contractInfoAfterRepay.addHistoryPerContract(l_targetContract, l_historyPerContract);

            l_contractInfoAfterRepay.loadTodaysHistories();
            
            WEB3TPHistoryPerContract l_historyPerContractResult =
                l_contractInfoAfterRepay.getHistoryPerContract(l_targetContract);
            WEB3TPHistory l_history = (WEB3TPHistory)l_historyPerContractResult.getHistories().get(0);
            assertEquals("0.0", "" + l_history.getContractTotalCost());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        protected void addInstBranCalcCondition(String l_strName, String l_strValue)
        {
            super.addInstBranCalcCondition(l_strName, l_strValue);
        }
    }
    
    private class WEB3TPContractInfoAfterRepayForTest extends WEB3TPContractInfoAfterRepay
    {
        public List getTargetContracts() 
        {
            List l_lisTargetContracts = new ArrayList();
            l_lisTargetContracts.add(l_targetContract);
            return l_lisTargetContracts;
        }
    }
}
@
