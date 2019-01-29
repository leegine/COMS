head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.21.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderCarryOverMainServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : êÊï®OPíçï∂åJâzÉÅÉCÉìÉTÅ[ÉrÉXImplTEST(WEB3IfoOrderCarryOverMainServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/26 Ê‚ó—ñQ(íÜêu) êVãKçÏê¨
Revision History : 2007/07/11 Ê‚ó—ñQ(íÜêu) ÉÇÉfÉãNo.774
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OnlineRunStatusDao;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainRequest;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoOrderCarryOverMainServiceImplTest extends TestBaseForMock
{
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3IfoOrderCarryOverMainServiceImplTest.class);

    public WEB3IfoOrderCarryOverMainServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testGetCarryoverProcessTypeC0001()
    {
        final String STR_METHOD_NAME = " testGetCarryoverProcessTypeC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            String l_strCarryoverProcessType =
                l_serviceImplForTest.getCarryoverProcessType();
            
            assertEquals("íçï∂åJâz", l_strCarryoverProcessType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testGetCarryoverProcessTypeC0002()
    {
        final String STR_METHOD_NAME = " testGetCarryoverProcessTypeC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            String l_strCarryoverProcessType =
                l_serviceImplForTest.getCarryoverProcessType();
            
            assertEquals("ó[èÍëOíçï∂åJâz", l_strCarryoverProcessType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    public void testGetOnlineServiceDivC0001()
    {
        final String STR_METHOD_NAME = " testGetOnlineServiceDivC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            OrderexecutionEndParams l_params = TestDBUtility.getOrderexecutionEndRow();
            l_params.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_params.setOrderexecutionEndType("1");
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDel(l_params);
            
            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            String l_strOnlineServiceDiv =
                l_serviceImplForTest.getOnlineServiceDiv("0D", "ó[èÍëOíçï∂åJâz");
            
            assertEquals("8", l_strOnlineServiceDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testGetOnlineServiceDivC0002()
    {
        final String STR_METHOD_NAME = " testGetOnlineServiceDivC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            OrderexecutionEndParams l_params = TestDBUtility.getOrderexecutionEndRow();
            l_params.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_params.setOrderexecutionEndType("0");
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDel(l_params);
            
            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            String l_strOnlineServiceDiv =
                l_serviceImplForTest.getOnlineServiceDiv("0D", "íçï∂åJâz");
            
            assertEquals("2", l_strOnlineServiceDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testGetOnlineServiceDivC0003()
    {
        final String STR_METHOD_NAME = " testGetOnlineServiceDivC0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);

            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            String l_strOnlineServiceDiv =
                l_serviceImplForTest.getOnlineServiceDiv("0D", "ó[èÍëOíçï∂åJâz");
            
            assertNull(l_strOnlineServiceDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testExecIfoOrderCarryOverC0001()
    {
        final String STR_METHOD_NAME = " testExecIfoOrderCarryOverC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
            "expireCarryOverOriginOrder",
            new Class[] {OrderUnit.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
            "createOpenContractNextOrder",
            new Class[] {OrderUnit.class, List.class},
            null);

        try
        {
            WEB3IfoOrderCarryOverMainRequest l_request =
                new WEB3IfoOrderCarryOverMainRequest();
            
            l_request.institutionCode = "0D";
            l_request.rangeFrom = 101001010001L;
            l_request.rangeTo = 101001010020L;
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("0");
            l_context.setTradingTimeType("11");
            l_context.setOrderAcceptProduct(null);
            l_context.setOrderAcceptTransaction(null);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            OrderexecutionEndParams l_params = TestDBUtility.getOrderexecutionEndRow();
            l_params.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_params.setOrderexecutionEndType("1");
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_params);
            
            OnlineRunStatusParams l_onlineRunStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_onlineRunStatusParams.setAccountIdFrom(101001010001L);
            l_onlineRunStatusParams.setAccountIdTo(101001010020L);
            l_onlineRunStatusParams.setFutureOptionDiv("2");
            l_onlineRunStatusParams.setOnlineServiceDiv("8");
            l_onlineRunStatusParams.setInstitutionCode("0D");
            l_onlineRunStatusParams.setRunStatusDiv("0");
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSessionType(null);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            WEB3OptionOrderCarryOverService l_serviceImplForTest =
                (WEB3OptionOrderCarryOverService)Services.getService(WEB3OptionOrderCarryOverService.class);
            
            l_serviceImplForTest.execute(l_request);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                "expireCarryOverOriginOrder",
                new Class[] {OrderUnit.class});
            assertEquals("1001", ((OrderUnit)l_paramsValue1.getFirstCalled()[0]).getOrderUnitId() + "");
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                "createOpenContractNextOrder",
                new Class[] {OrderUnit.class, List.class});
            assertEquals("1001", ((OrderUnit)l_paramsValue2.getFirstCalled()[0]).getOrderUnitId() + "");
            
            OnlineRunStatusRow l_row =
                OnlineRunStatusDao.findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(
                    "0D", ProductTypeEnum.IFO, "2", "8", 101001010001L);
            
            assertEquals("1",l_row.getRunStatusDiv());
 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    } 
    
    public void testExecIfoOrderCarryOverC0002()
    {
        final String STR_METHOD_NAME = " testExecIfoOrderCarryOverC0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoOrderCarryOverMainRequest l_request =
                new WEB3IfoOrderCarryOverMainRequest();
            
            l_request.institutionCode = "0D";
            l_request.rangeFrom = 101001010001L;
            l_request.rangeTo = 101001010020L;
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("0");
            l_context.setTradingTimeType("11");
            l_context.setOrderAcceptProduct(null);
            l_context.setOrderAcceptTransaction(null);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);

            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            l_serviceImplForTest.execIfoOrderCarryOver(l_request);
 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    } 
    
    public void testExecIfoOrderCarryOverC0003()
    {
        final String STR_METHOD_NAME = " testExecIfoOrderCarryOverC0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoOrderCarryOverMainRequest l_request =
                new WEB3IfoOrderCarryOverMainRequest();
            
            l_request.institutionCode = "0D";
            l_request.rangeFrom = 101001010001L;
            l_request.rangeTo = 101001010020L;
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("0");
            l_context.setTradingTimeType("11");
            l_context.setOrderAcceptProduct(null);
            l_context.setOrderAcceptTransaction(null);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            OrderexecutionEndParams l_params = TestDBUtility.getOrderexecutionEndRow();
            l_params.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_params.setOrderexecutionEndType("1");
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_params);
            
            OnlineRunStatusParams l_onlineRunStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_onlineRunStatusParams.setAccountIdFrom(101001010001L);
            l_onlineRunStatusParams.setAccountIdTo(101001010020L);
            l_onlineRunStatusParams.setFutureOptionDiv("2");
            l_onlineRunStatusParams.setOnlineServiceDiv("8");
            l_onlineRunStatusParams.setInstitutionCode("0D");
            l_onlineRunStatusParams.setRunStatusDiv("5");
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);

            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            l_serviceImplForTest.execIfoOrderCarryOver(l_request);
 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    } 
    
    public void testExecIfoOrderCarryOverC0004()
    {
        final String STR_METHOD_NAME = " testExecIfoOrderCarryOverC0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
            "expireCarryOverOriginOrder",
            new Class[] {OrderUnit.class},
            null);
        
        WEB3SystemLayerException l_sle = 
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02216, 
                "createOpenContractNextOrder");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
            "createOpenContractNextOrder",
            new Class[] {OrderUnit.class, List.class},
            l_sle);

        try
        {
            WEB3IfoOrderCarryOverMainRequest l_request =
                new WEB3IfoOrderCarryOverMainRequest();
            
            l_request.institutionCode = "0D";
            l_request.rangeFrom = 101001010001L;
            l_request.rangeTo = 101001010020L;
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("0");
            l_context.setTradingTimeType("11");
            l_context.setOrderAcceptProduct(null);
            l_context.setOrderAcceptTransaction(null);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            OrderexecutionEndParams l_params = TestDBUtility.getOrderexecutionEndRow();
            l_params.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_params.setOrderexecutionEndType("1");
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_params);
            
            OnlineRunStatusParams l_onlineRunStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_onlineRunStatusParams.setAccountIdFrom(101001010001L);
            l_onlineRunStatusParams.setAccountIdTo(101001010020L);
            l_onlineRunStatusParams.setFutureOptionDiv("2");
            l_onlineRunStatusParams.setOnlineServiceDiv("8");
            l_onlineRunStatusParams.setInstitutionCode("0D");
            l_onlineRunStatusParams.setRunStatusDiv("0");
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSessionType(null);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(l_ifoOrderUnitParams.getOrderId());
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams);

            WEB3OptionOrderCarryOverService l_serviceImplForTest =
                (WEB3OptionOrderCarryOverService)Services.getService(WEB3OptionOrderCarryOverService.class);
            
            l_serviceImplForTest.execute(l_request);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                "expireCarryOverOriginOrder",
                new Class[] {OrderUnit.class});
            assertEquals("1001", ((OrderUnit)l_paramsValue1.getFirstCalled()[0]).getOrderUnitId() + "");
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                "createOpenContractNextOrder",
                new Class[] {OrderUnit.class, List.class});
            assertEquals("1001", ((OrderUnit)l_paramsValue2.getFirstCalled()[0]).getOrderUnitId() + "");
            
            OnlineRunStatusRow l_row =
                OnlineRunStatusDao.findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(
                    "0D", ProductTypeEnum.IFO, "2", "8", 101001010001L);
            
            assertEquals("1",l_row.getRunStatusDiv());
 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    } 
    
    public void testExecIfoOrderCarryOverC0005()
    {
        final String STR_METHOD_NAME = " testExecIfoOrderCarryOverC0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
            "expireCarryOverOriginOrder",
            new Class[] {OrderUnit.class},
            null);
        
        WEB3SystemLayerException l_sle = 
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02216, 
                "createOpenContractNextOrder");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
            "createOpenContractNextOrder",
            new Class[] {OrderUnit.class, List.class},
            l_sle);

        try
        {
            WEB3IfoOrderCarryOverMainRequest l_request =
                new WEB3IfoOrderCarryOverMainRequest();
            
            l_request.institutionCode = "0D";
            l_request.rangeFrom = 101001010001L;
            l_request.rangeTo = 101001010020L;
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("0");
            l_context.setTradingTimeType("11");
            l_context.setOrderAcceptProduct(null);
            l_context.setOrderAcceptTransaction(null);
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            OrderexecutionEndParams l_params = TestDBUtility.getOrderexecutionEndRow();
            l_params.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_params.setOrderexecutionEndType("1");
            TestDBUtility.deleteAllAndCommit(OrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_params);
            
            OnlineRunStatusParams l_onlineRunStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_onlineRunStatusParams.setAccountIdFrom(101001010001L);
            l_onlineRunStatusParams.setAccountIdTo(101001010020L);
            l_onlineRunStatusParams.setFutureOptionDiv("2");
            l_onlineRunStatusParams.setOnlineServiceDiv("8");
            l_onlineRunStatusParams.setInstitutionCode("0D");
            l_onlineRunStatusParams.setRunStatusDiv("0");
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(101001010009L);
            l_mainAccountParams1.setIfoAccOpenDivTokyo("3");
            l_mainAccountParams1.setAccountCode("2512245");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams1);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSessionType(null);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            l_ifoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams1.setOrderUnitId(1000);
            l_ifoOrderUnitParams1.setFutureOptionDiv("2");
            l_ifoOrderUnitParams1.setAccountId(101001010009L);
            l_ifoOrderUnitParams1.setSessionType(null);
            l_ifoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams1.setBizDate(WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams1);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(l_ifoOrderUnitParams.getOrderId());
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams);

            WEB3OptionOrderCarryOverService l_serviceImplForTest =
                (WEB3OptionOrderCarryOverService)Services.getService(WEB3OptionOrderCarryOverService.class);
            
            l_serviceImplForTest.execute(l_request);

            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                "expireCarryOverOriginOrder",
                new Class[] {OrderUnit.class});
            assertEquals("1000", ((OrderUnit)l_paramsValue1.getFirstCalled()[0]).getOrderUnitId() + "");
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl",
                "createOpenContractNextOrder",
                new Class[] {OrderUnit.class, List.class});
            assertEquals("1001", ((OrderUnit)l_paramsValue2.getFirstCalled()[0]).getOrderUnitId() + "");
            
            OnlineRunStatusRow l_row =
                OnlineRunStatusDao.findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(
                    "0D", ProductTypeEnum.IFO, "2", "8", 101001010001L);
            
            assertEquals("9",l_row.getRunStatusDiv());
 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    public void testExecuteC0001()
    {
        final String STR_METHOD_NAME = " testExecuteC0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            l_serviceImplForTest.execute(null);
            fail();

        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testExecuteC0002()
    {
        final String STR_METHOD_NAME = " testExecuteC0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesChangeCancelNotifyRequest l_request = new WEB3FuturesChangeCancelNotifyRequest();
            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            l_serviceImplForTest.execute(l_request);
            fail();

        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),  WEB3ErrorCatalog.SYSTEM_ERROR_80018);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    //ÅyIFOÅzòAë±íçï∂ëŒâû
    public void testCreateNextOrderCase001()
    {
        final String STR_METHOD_NAME = " testCreateNextOrderCase001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3IfoOrderCarryOverMainServiceImplForTest l_serviceImplForTest =
                new WEB3IfoOrderCarryOverMainServiceImplForTest();
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderUnitId(1001L);
            l_rsvIfoOrderUnitParams.setParentOrderId(1001L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_orderMgr =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_orderMgr.getMainAccount(101001010010L);
            
            l_serviceImplForTest.createNextOrder(l_mainAccount, "1", "íçï∂åJâz");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    private class WEB3IfoOrderCarryOverMainServiceImplForTest extends WEB3IfoOrderCarryOverMainServiceImpl
    {
        public  WEB3IfoOrderCarryOverMainServiceImplForTest()
        {

        }

        protected String getFutureOptionDiv()
        {
            return WEB3FuturesOptionDivDef.OPTION;
        }

        protected void expireCarryOverOriginOrder(OrderUnit l_orderUnit) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
        }

        protected void reCalcTradingPower(MainAccount l_mainAccount) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
        }

        protected void submitNextOrder(IfoOrderUnit l_ifoOrderUnit, List l_lisRsvIfoOrderUnits) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
        }

        protected void updateCarryOverOriginOrder(OrderUnit l_orderUnit, String l_strOrderErrorReasonCode) 
            throws WEB3BaseException
        {
            // TODO Auto-generated method stub
        }
    }
}
@
