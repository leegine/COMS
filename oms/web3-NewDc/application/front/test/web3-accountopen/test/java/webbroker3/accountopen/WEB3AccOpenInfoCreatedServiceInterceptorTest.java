head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenInfoCreatedServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenInfoCreatedServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenInfoCreatedServiceInterceptorTest.class);

    public WEB3AccOpenInfoCreatedServiceInterceptorTest(String arg0)
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

    /*
     * Test method for 'webbroker3.accountopen.WEB3AccOpenInfoCreatedServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCallCase1()
    {
        final String STR_METHOD_NAME = "testOnCallCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("101");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("22");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("");
            l_clendarContext.setEnableOrder("0");
            l_clendarContext.setOrderAcceptProduct("0");
            l_clendarContext.setOrderAcceptTransaction("");
            l_clendarContext.setSubmitMarketTrigger("");
            l_clendarContext.setTradingStopProduct("");
            l_clendarContext.setTradingStopTransaction("");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3AccOpenInfoCreatedServiceInterceptor l_interceptor =
                new WEB3AccOpenInfoCreatedServiceInterceptor();
            
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            WEB3AccOpenMailAddrRegInputRequest l_request1 =
                new WEB3AccOpenMailAddrRegInputRequest();
            l_request1.institutionCode = "1D";
            l_request1.branchCode = "102";
            l_interceptor.onCall(null, new Object[]{l_request});
            
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("101", l_context.getBranchCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testOnCallCase2()
    {
        final String STR_METHOD_NAME = "testOnCallCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1D");
            l_clendarContext.setBranchCode("102");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("22");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setBizdateCalcParameter("");
            l_clendarContext.setEnableOrder("0");
            l_clendarContext.setOrderAcceptProduct("0");
            l_clendarContext.setOrderAcceptTransaction("");
            l_clendarContext.setSubmitMarketTrigger("");
            l_clendarContext.setTradingStopProduct("");
            l_clendarContext.setTradingStopTransaction("");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3AccOpenInfoCreatedServiceInterceptor l_interceptor =
                new WEB3AccOpenInfoCreatedServiceInterceptor();
            
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            WEB3AccOpenMailAddrRegInputRequest l_request1 =
                new WEB3AccOpenMailAddrRegInputRequest();
            l_request1.institutionCode = "1D";
            l_request1.branchCode = "102";
            l_interceptor.onCall(null, new Object[]{l_request1});
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context = (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            assertEquals("1D", l_context.getInstitutionCode());
            assertEquals("102", l_context.getBranchCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
