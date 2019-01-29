head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyInputService;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyInputServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondDomesticApplyInputHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputHandlerTest.class);

    WEB3BondDomesticApplyInputHandler l_handler = null;

    public WEB3BondDomesticApplyInputHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3BondDomesticApplyInputHandler();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testBondDomesticApplyInput_T01()
    {
        final String STR_METHOD_NAME = "testBondDomesticApplyInput_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3BondDomesticApplyInputService.class);
            WEB3BondDomesticApplyInputResponse l_response =
                l_handler.bondDomesticApplyInput(new WEB3BondDomesticApplyInputRequest());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                WEB3BondDomesticApplyInputService.class,
                new WEB3BondDomesticApplyInputServiceImpl());
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testBondDomesticApplyInput_T02()
    {
        final String STR_METHOD_NAME = "testBondDomesticApplyInput_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long("123456789"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3BondDomesticApplyInputRequest l_request =
                new WEB3BondDomesticApplyInputRequest();
            WEB3BondDomesticApplyInputResponse l_response =
                l_handler.bondDomesticApplyInput(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testBondDomesticApplyInput_T03()
    {
        final String STR_METHOD_NAME = "testBondDomesticApplyInput_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3BondDomesticApplyInputService.class);
            Services.registerService(
                WEB3BondDomesticApplyInputService.class,
                new WEB3BondDomesticApplyInputServiceImplTestForMock());
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long("123456789"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3BondDomesticApplyInputRequest l_request =
                new WEB3BondDomesticApplyInputRequest();
            WEB3BondDomesticApplyInputResponse l_response =
                l_handler.bondDomesticApplyInput(l_request);
            
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3BondDomesticApplyInputService.class);
            Services.registerService(
                WEB3BondDomesticApplyInputService.class,
                new WEB3BondDomesticApplyInputServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public class WEB3BondDomesticApplyInputServiceImplTestForMock implements WEB3BondDomesticApplyInputService
    {

        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            return new WEB3BondDomesticApplyInputResponse();
        }
        
    }
}
@
