head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderCarryOverServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionOrderCarryOverServiceImplESTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/26 金傑 (中訊) 新規作成 仕様変更モデルNo.670
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.message.WEB3OptionsOrderCarryOverRequest;
import webbroker3.triggerorder.message.WEB3OptionsOrderCarryOverResponse;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOrderCarryOverServiceImplTest extends TestBaseForMock
{
    private WEB3OptionOrderCarryOverServiceImpl l_serviceImpl = null;
    
    private WEB3OptionsOrderCarryOverRequest l_request = null;
    
    private WEB3OptionsOrderCarryOverResponse l_response = null;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3OptionOrderCarryOverServiceImplTest.class);
    
    public WEB3OptionOrderCarryOverServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3OptionOrderCarryOverServiceImpl();
        this.l_request = new WEB3OptionsOrderCarryOverRequest();
    }
    
    protected void tearDown() throws Exception
    {
//        super.checkMethodValue();
        this.l_serviceImpl = null;
        this.l_request = null;
        super.tearDown();
    }
    
    /**
     * 
     * 抛出異常WEB3BaseException信息：BUSINESS_ERROR_00827
     *
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_response = this.l_response = (WEB3OptionsOrderCarryOverResponse) this.l_serviceImpl.execute(this.l_request);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 抛出異常WEB3BaseRuntimeException信息：SYSTEM_ERROR_80005
     *
     */
    public void testCreateNextOrdere_C0001()
    {
        final String STR_METHOD_NAME = "testCreateNextOrdere_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", new Class[] {},
                    l_branch);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME));
            
            this.l_serviceImpl.createNextOrder(l_mainAccount,null,null);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "lockAccount", 
                    new Class[] { String.class,String.class,String.class });
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * OP取引口座タイプ == "証拠金口座"の場合
     * 正常終了
     *
     */
    public void estReCalcTradingPower_C0001()
    {
        final String STR_METHOD_NAME = "testReCalcTradingPower_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3GentradeSubAccountForMock  l_subAccountForMock = new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getSubAccount", new Class[]
                    { SubAccountTypeEnum.class },
                    l_subAccountForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
            
            this.l_serviceImpl.reCalcTradingPower(l_mainAccountForMock);
            
            assertTrue(true);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getSubAccount", 
                    new Class[] { SubAccountTypeEnum.class });
            assertEquals(1,((SubAccountTypeEnum)l_paramsValue1.getFirstCalled()[0]).intValue());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "reCalcTradingPower", 
                    new Class[] {WEB3GentradeSubAccount.class});
            assertEquals(WEB3GentradeSubAccountForMock.class,l_paramsValue2.getFirstCalled()[0].getClass());
            
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 抛出WEB3SystemLayerException異常信息為：SYSTEM_ERROR_80005
     *
     */
    public void estReCalcTradingPower_C0002()
    {
        final String STR_METHOD_NAME = "testReCalcTradingPower_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            
            this.l_serviceImpl.reCalcTradingPower(l_mainAccountForMock);
            fail();
        }
        catch(WEB3SystemLayerException l_web3SystemLayerException)
        {
            log.error("", l_web3SystemLayerException);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3SystemLayerException.getErrorInfo());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * OP取引口座タイプ != "証拠金口座"の場合
     * 正常終了
     *
     */
    public void testReCalcTradingPower_C0003()
    {
        final String STR_METHOD_NAME = "testReCalcTradingPower_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("3");
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(l_mainAccountParams);
            WEB3GentradeSubAccountForMock  l_subAccountForMock = new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getSubAccount", new Class[]
                    { SubAccountTypeEnum.class },
                    l_subAccountForMock);
                        
            this.l_serviceImpl.reCalcTradingPower(l_mainAccountForMock);
            
            assertTrue(true);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getSubAccount", 
                    new Class[] { SubAccountTypeEnum.class });
            assertEquals(7,((SubAccountTypeEnum)l_paramsValue1.getFirstCalled()[0]).intValue());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * OP新規建注文"の場合
     * 
     * 抛出WEB3BaseException異常信息:BUSINESS_ERROR_00883
     *
     */
    public void testSubmitNextOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitNextOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                    "createOpenContractNextOrder",
                    new Class[] {OrderUnit.class, List.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00883, STR_METHOD_NAME));
            
            this.l_serviceImpl.submitNextOrder(l_ifoOrderUnit, null);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00883,l_web3BaseException.getErrorInfo());
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl", 
                    "createOpenContractNextOrder", 
                    new Class[] { OrderUnit.class, List.class});
            assertEquals(1001,((IfoOrderUnit)l_paramsValue1.getFirstCalled()[0]).getOrderUnitId());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * OP返済注文"の場合
     * 
     * 抛出WEB3BaseException異常信息:BUSINESS_ERROR_00883
     *
     */
    public void testSubmitNextOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitNextOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                    "createSettleContractNextOrder", new Class[]
                    { OrderUnit.class, List.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00883,STR_METHOD_NAME));
            
            this.l_serviceImpl.submitNextOrder(l_ifoOrderUnit, null);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00883,l_web3BaseException.getErrorInfo());
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl", 
                    "createSettleContractNextOrder", 
                    new Class[] { OrderUnit.class, List.class});
            assertEquals(1001,((IfoOrderUnit)l_paramsValue1.getFirstCalled()[0]).getOrderUnitId());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 既不是“OP返済注文"の場合”，也不是“OP新規建注文"の場合”
     * 
     * 正常結束
     *
     */
    public void testSubmitNextOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitNextOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
                        
            this.l_serviceImpl.submitNextOrder(l_ifoOrderUnit, null);
            assertTrue(true);
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
