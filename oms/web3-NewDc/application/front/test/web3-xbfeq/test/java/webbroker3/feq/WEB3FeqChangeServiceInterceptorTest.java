head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqChangeServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqCancelCompleteRequest;
import webbroker3.feq.message.WEB3FeqChangeCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqChangeServiceInterceptorTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeServiceInterceptorTest.class);

    public WEB3FeqChangeServiceInterceptorTest(String arg0)
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

    public void testOnCall_case001() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "testOnCall_case001()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(33381330001L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "isAccountIdSet",
            new Class[] {},
            Boolean.FALSE);
        
        try
        {
            Object[] l_serviceParams = new Object[1];
            WEB3FeqChangeCompleteRequest l_request = new WEB3FeqChangeCompleteRequest();
            l_request.orderId = "123456789";
            l_request.checkDate = WEB3DateUtility.getDate("20070506", "yyyyMMdd");
            l_request.checkPrice = "123";
            l_serviceParams[0] = l_request;
            
            Method l_method = null;
            
            Services.overrideService(
                    OpLoginSecurityService.class,
                    new OpLoginSecurityServiceImplForMock());
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(33381330001L);
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //OrderUnit
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnit = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnit.setBranchId(33381L);
            l_feqOrderUnit.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_feqOrderUnit);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setFeqOrderRequestDiv("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            //鎖表SleSendQRow
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strWhere = " institution_code = ? and branch_code = ? and order_id = ? and status = ? ";
            Object[] l_objWhere = new Object[4];
            l_objWhere[0] = "";
            l_objWhere[1] = "";
            l_objWhere[2] = "";
            l_objWhere[3] = "";
         
            List l_lisSleSendQRow =  
                l_queryProcessor.doFindAllQuery(
                    SleSendQRow.TYPE,
                    l_strWhere,
                    "for update nowait",
                    l_objWhere);
            
            WEB3FeqChangeServiceInterceptor inter = new WEB3FeqChangeServiceInterceptor();
            inter.onCall(l_method, l_serviceParams);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003,l_ex.getErrorInfo());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOnCall_case002() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "testOnCall_case002()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(33381330001L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "isAccountIdSet",
            new Class[] {},
            Boolean.FALSE);
        Services.overrideService(
                OpLoginSecurityService.class,
                new OpLoginSecurityServiceImplForMock());
        
        
        try
        {
            Object[] l_serviceParams = new Object[1];
            WEB3FeqChangeCompleteRequest l_request = new WEB3FeqChangeCompleteRequest();
            l_request.orderId = "123456789";
            l_request.checkDate = WEB3DateUtility.getDate("20070506", "yyyyMMdd");
            l_request.checkPrice = "2";
            l_serviceParams[0] = l_request;
            
            Method l_method = null;
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(33381330001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //OrderUnit
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnit = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnit.setBranchId(33381L);
            l_feqOrderUnit.setOrderId(123456789L);

            TestDBUtility.insertWithDel(l_feqOrderUnit);
            
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrder = TestDBUtility.getFeqOrderRow();
            l_feqOrder.setOrderId(123456789L);

            TestDBUtility.insertWithDel(l_feqOrder);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setFeqOrderRequestDiv("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("10");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setTradingTimeType("39");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setMarketCode("SP");
            
            l_tradingTimeParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams2.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            WEB3FeqChangeServiceInterceptor inter = new WEB3FeqChangeServiceInterceptor();
            inter.onCall(l_method, l_serviceParams);
           
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
