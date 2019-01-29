head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderManagerTest_T05151EQ.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderManagerTest_T05151EQ extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderManagerTest_T05151EQ.class);


    public WEB3EquityOrderManagerTest_T05151EQ(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "calcExpenses",
                new Class[] {double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        double.class,
                        ContractTypeEnum.class},
                new Double(100));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityOrderManager.calcEstimatedSwapPrice(EqTypeSettleContractOrderEntry[], double, EqTypeOrderUnit)'
     */
    //現渡注文の場合（建株.isShort == true）
    public void testCalcEstimatedSwapPriceC1()
    {
        String STR_METHOD_NAME = "testCalcEstimatedSwapPriceC1()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityContract",
                "getManagementFee",
                new Class[]{double.class, long.class},
                new Double(1234));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityContract",
                "getManagementFeeTax",
                new Class[]{double.class, long.class},
                new Double(5678));
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(111);
        l_eqtypeContractParams.setManagementFee(50322.43212);
        l_eqtypeContractParams.setManagementFeeTax(60322.4312);
        l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderId(11);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3EquityOrderManager  l_manager = new WEB3EquityOrderManager();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

            EqTypeSettleContractOrderEntry l_contractOrderEntry1 =
                new EqTypeSettleContractOrderEntry(111, 100);
            ArrayList l_list = new ArrayList();
            l_list.add(l_contractOrderEntry1);
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
                new EqTypeSettleContractOrderEntry[l_list.size()];
            l_list.toArray(l_settleContractOrderEntrys);
            
            double l_dblQuantity = 0;
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(11);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            
            WEB3EquityContractForMock l_mock = new WEB3EquityContractForMock(l_eqtypeContractParams);

            l_manager.calcEstimatedSwapPrice(l_settleContractOrderEntrys, l_dblQuantity, l_eqtypeOrderUnit, l_mock);
            
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.equity.WEB3EquityBizLogicProvider",
                        "calcExpenses",
                        new Class[] {double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                ContractTypeEnum.class});

              assertEquals(new Double(1234), l_paramsValue.getFirstCalled()[6]);
              assertEquals(new Double(5678), l_paramsValue.getFirstCalled()[7]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //現引注文の場合（建株.isShort == false）
    //管理費（管理費消費税）=未決済分+決済済分
    //(未決済分=EqtypeContractRow中的管理費+EqtypeFinTransactionRow中的管理費)
    public void testCalcEstimatedSwapPriceC2()
    {
        String STR_METHOD_NAME = "testCalcEstimatedSwapPriceC2()";
        log.entering(STR_METHOD_NAME);
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(111);
        l_eqtypeContractParams.setManagementFee(50322.43212);
        l_eqtypeContractParams.setManagementFeeTax(60322.4312);
        l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderId(11);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3EquityOrderManager  l_manager = new WEB3EquityOrderManager();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
            
            EqTypeSettleContractOrderEntry l_contractOrderEntry1 =
                new EqTypeSettleContractOrderEntry(111, 100);
            ArrayList l_list = new ArrayList();
            l_list.add(l_contractOrderEntry1);
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
                new EqTypeSettleContractOrderEntry[l_list.size()];
            l_list.toArray(l_settleContractOrderEntrys);
            
            double l_dblQuantity = 0;
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(11);
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            
            WEB3EquityContract l_contract = 
                (WEB3EquityContract) l_positionManager.getContract(111);
            
            l_manager.calcEstimatedSwapPrice(l_settleContractOrderEntrys, l_dblQuantity, l_eqtypeOrderUnit, l_contract);
            
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.equity.WEB3EquityBizLogicProvider",
                        "calcExpenses",
                        new Class[] {double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                double.class,
                                ContractTypeEnum.class});
  
              assertEquals(new Double(50322.43212), l_paramsValue.getFirstCalled()[6]);
              assertEquals(new Double(60322.4312), l_paramsValue.getFirstCalled()[7]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate機@構預託同意
     * 株式発注審査チェック.validate機@構預託同意(補助口座)に委譲する
     */
    public void testValidateMechanismDepositAgreeCase0001()
    {
        final String STR_METHOD_NAME = "testValidateMechanismDepositAgreeCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        { 
            WEB3EquityOrderManager  l_manager = new WEB3EquityOrderManager();
        
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            l_manager.validateMechanismDepositAgree(l_subAccount);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
