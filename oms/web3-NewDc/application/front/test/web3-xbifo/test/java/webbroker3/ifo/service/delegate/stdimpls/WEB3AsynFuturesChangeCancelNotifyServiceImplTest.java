head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynFuturesChangeCancelNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : (WEB3AsynFuturesChangeCancelNotifyServiceImplTest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2007/02/06　@何文敏(中訊)
Revesion History : 2010/07/16 趙天月(中訊) 大証次期デリバティブシステム対応
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynFuturesChangeCancelNotifyServiceImpl.WEB3FuturesChangeCancelNotifyTransactionCallback;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 何文敏
 * @@version 1.0
 *
 */
public class WEB3AsynFuturesChangeCancelNotifyServiceImplTest extends TestBaseForMock
{
    WEB3FuturesChangeCancelNotifyRequest l_request = new WEB3FuturesChangeCancelNotifyRequest();
    WEB3AsynFuturesChangeCancelNotifyServiceImpl l_impl = 
        new WEB3AsynFuturesChangeCancelNotifyServiceImpl(l_request);
    WEB3FuturesChangeCancelNotifyTransactionCallback l_callBack =
        l_impl.new WEB3FuturesChangeCancelNotifyTransactionCallback();
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AsynFuturesChangeCancelNotifyServiceImplTest.class);

    public WEB3AsynFuturesChangeCancelNotifyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testProcess1()
    {
        final String STR_METHOD_NAME = "testProcess1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setAccountCode("2512246");
            l_hostFotypeOrderClmdNotifyParams.setRequestCode("EI816");
            l_hostFotypeOrderClmdNotifyParams.setStatus("0");
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("1");
            l_hostFotypeOrderClmdNotifyParams.setModSubmitOrderRouteDiv("");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
    
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractOpenOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            
//            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyChange",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    null);
            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcess2()
    {
        final String STR_METHOD_NAME = "testProcess2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setAccountCode("2512246");
            l_hostFotypeOrderClmdNotifyParams.setRequestCode("EI816");
            l_hostFotypeOrderClmdNotifyParams.setStatus("0");
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("1");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("3");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
    
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyChange",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    null);

            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcess3()
    {
        final String STR_METHOD_NAME = "testProcess3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("1");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("4");
            l_hostFotypeOrderClmdNotifyParams.setModSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
    
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyChange",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    null);

            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcess4()
    {
        final String STR_METHOD_NAME = "testProcess4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("1");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("7");
            l_hostFotypeOrderClmdNotifyParams.setModSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
    
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFYING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyChange",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    null);

            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcess5()
    {
        final String STR_METHOD_NAME = "testProcess5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("1");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("7");
            l_hostFotypeOrderClmdNotifyParams.setModSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
    
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyChange",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    null);

            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcess6()
    {
        final String STR_METHOD_NAME = "testProcess6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("2");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("7");
            l_hostFotypeOrderClmdNotifyParams.setModSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
    
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyChange",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    null);

            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProcess7()
    {
        final String STR_METHOD_NAME = "testProcess7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("3");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("7");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
    
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyChange",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    null);

            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcess8()
    {
        final String STR_METHOD_NAME = "testProcess8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        String l_str = "00";
        String[] l_strs = new String[1];
        l_strs[0] = l_str;
        l_request.orderRequestNumberPrefixGroup = l_strs;
        l_request.orderRequestNumberPrefixGroup[0] = "00"; 
        //注文単位オブジェクト
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("3");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("7");
            l_hostFotypeOrderClmdNotifyParams.setModSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
    
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedQuantity(2);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            OrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnit",
                    new Class[]
                    {String.class, String.class, ProductTypeEnum.class, String.class},
                    l_orderUnit);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch",
                    new Class[]{long.class},
                    l_branch);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl", 
                    "notifyCancel",
                    new Class[]{OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class},
                    WEB3StatusDef.ADMIN_MANUAL_EXPIRED);

            l_callBack.process();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess9()
    {
        final String STR_METHOD_NAME = "testProcess9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.訂正取消通知区分 != 1：訂正完了  2：訂正失敗  3：取消完了  4：取消失敗
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("5");
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.処理区分 = 0
            l_hostFotypeOrderClmdNotifyParams.setStatus("0");
            l_hostFotypeOrderClmdNotifyParams.setRequestCode("EI816");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
    
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            l_callBack.process();
            
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisResultClmd =
                l_QueryProcessor.doFindAllQuery(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyRow l_clmdResultRow = (HostFotypeOrderClmdNotifyRow)l_lisResultClmd.get(0);
            
            assertEquals("9", l_clmdResultRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderClmdNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess10()
    {
        final String STR_METHOD_NAME = "testProcess10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setAccountCode("2512246");
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.訂正取消通知区分 != 1：訂正完了  2：訂正失敗  3：取消完了  4：取消失敗
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("5");
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.処理区分 = 0
            l_hostFotypeOrderClmdNotifyParams.setStatus("0");
            l_hostFotypeOrderClmdNotifyParams.setRequestCode("EI816");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);

            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            l_callBack.process();
            
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisResultClmd =
                l_QueryProcessor.doFindAllQuery(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyRow l_clmdResultRow = (HostFotypeOrderClmdNotifyRow)l_lisResultClmd.get(0);
            
            assertEquals("9", l_clmdResultRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderClmdNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess11()
    {
        final String STR_METHOD_NAME = "testProcess11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setAccountCode("2512246");
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.訂正取消通知区分 != 1：訂正完了  2：訂正失敗  3：取消完了  4：取消失敗
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("5");
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.処理区分 = 0
            l_hostFotypeOrderClmdNotifyParams.setStatus("0");
            l_hostFotypeOrderClmdNotifyParams.setRequestCode("EI816");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("X");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);

            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            l_callBack.process();
            
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisResultClmd =
                l_QueryProcessor.doFindAllQuery(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyRow l_clmdResultRow = (HostFotypeOrderClmdNotifyRow)l_lisResultClmd.get(0);
            
            assertEquals("9", l_clmdResultRow.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderClmdNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess12()
    {
        final String STR_METHOD_NAME = "testProcess12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setAccountCode("2512246");
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.訂正取消通知区分 != 1：訂正完了  2：訂正失敗  3：取消完了  4：取消失敗
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType("5");
            // 先物OP訂正取消通知ｷｭｰﾃｰﾌﾞﾙ.処理区分 = 0
            l_hostFotypeOrderClmdNotifyParams.setStatus("0");
            l_hostFotypeOrderClmdNotifyParams.setRequestCode("EI816");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);

            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            l_callBack.process();
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderClmdNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
}
@
