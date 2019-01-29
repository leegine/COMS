head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderDLHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文DLハンドラTest（WEB3AdminEquityForcedSettleOrderDLHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/19 新規作成モデル171
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderDLService;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderDLHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLHandlerTest.class);
    
    WEB3AdminEquityForcedSettleOrderDLService l_service = null;

    public WEB3AdminEquityForcedSettleOrderDLHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public class WEB3AdminForcedSettleDownloadInputRequestFortest 
        extends WEB3AdminForcedSettleDownloadInputRequest
    {
        public void validate()
        {
            
        }
    }
    
    public class WEB3AdminForcedSettleDownloadRequestFortest 
    extends WEB3AdminForcedSettleDownloadRequest
{
    public void validate()
    {
        
    }
}

    /*
     * Test method for 'webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderDLHandler.getInputScreen(WEB3AdminForcedSettleDownloadInputRequest)'
     */
    //管理者・強制決済注文DLサービスを取得に失敗しました
    public void testGetInputScreen1()
    {
        final String STR_METHOD_NAME = "testGetInputScreen1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminEquityForcedSettleOrderDLHandler l_handler = new WEB3AdminEquityForcedSettleOrderDLHandler();
            l_service =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderDLService.class);

            Services.unregisterService(WEB3AdminEquityForcedSettleOrderDLService.class);

            WEB3AdminForcedSettleDownloadInputRequest l_request = new WEB3AdminForcedSettleDownloadInputRequest();

            WEB3AdminForcedSettleDownloadInputResponse l_response = l_handler.getInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityForcedSettleOrderDLService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //強制決済注文ダウンロード入力画面表示処理が失敗しました
    public void testGetInputScreen2()
    {
        final String STR_METHOD_NAME = "testGetInputScreen2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_context);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "33");
            
            WEB3AdminEquityForcedSettleOrderDLHandler l_handler = new WEB3AdminEquityForcedSettleOrderDLHandler();
            l_service =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderDLService.class);

            WEB3AdminForcedSettleDownloadInputRequest l_request = new WEB3AdminForcedSettleDownloadInputRequest();

            WEB3AdminForcedSettleDownloadInputResponse l_response = l_handler.getInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常
    public void testGetInputScreen3()
    {
        final String STR_METHOD_NAME = "testGetInputScreen3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_AdministratorParasms = TestDBUtility.getAdministratorRow();
            l_AdministratorParasms.setLoginId(0);
            TestDBUtility.insertWithDel(l_AdministratorParasms);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("331");
            l_AdminPermissionParams.setTransactionCategory("C0108");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_context);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "33");
            LoginInfoImplForMock l_LoginInfo = new LoginInfoImplForMock();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_LoginInfo);
            WEB3AdminForcedSettleDownloadInputResponse l_response =  null;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_response);
            
            WEB3AdminEquityForcedSettleOrderDLHandler l_handler = new WEB3AdminEquityForcedSettleOrderDLHandler();
            l_service =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderDLService.class);

            WEB3AdminForcedSettleDownloadInputRequestFortest l_request = new WEB3AdminForcedSettleDownloadInputRequestFortest();
            String[] l_strBranchCodes = new String[2];
            l_strBranchCodes[0] = "381";
            l_strBranchCodes[1] = "382";
            l_request.branchCodeList =  l_strBranchCodes;
            
            l_response = l_handler.getInputScreen(l_request);


        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderDLHandler.getDownloadFile(WEB3AdminForcedSettleDownloadRequest)'
     */
    public void testGetDownloadFile1()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminEquityForcedSettleOrderDLHandler l_handler = new WEB3AdminEquityForcedSettleOrderDLHandler();
            l_service =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderDLService.class);

            Services.unregisterService(WEB3AdminEquityForcedSettleOrderDLService.class);

            WEB3AdminForcedSettleDownloadRequest l_request = new WEB3AdminForcedSettleDownloadRequest();

            WEB3AdminForcedSettleDownloadResponse l_response = l_handler.getDownloadFile(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminEquityForcedSettleOrderDLService.class, l_service);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //強制決済注文ダウンロード入力画面表示処理が失敗しました
    public void testGetDownloadFile2()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_context);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "33");
            WEB3AdminForcedSettleDownloadResponse l_response = null;
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_01429, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
            
            WEB3AdminEquityForcedSettleOrderDLHandler l_handler = new WEB3AdminEquityForcedSettleOrderDLHandler();
            l_service =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderDLService.class);

            WEB3AdminForcedSettleDownloadRequestFortest l_request = new WEB3AdminForcedSettleDownloadRequestFortest();

            l_response = l_handler.getDownloadFile(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDownloadFile3()
    {
        final String STR_METHOD_NAME = "testGetDownloadFile3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchId(33);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_context);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "33");
            WEB3AdminForcedSettleDownloadResponse l_response = null;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl",
                    "execute", new Class[]
                    { WEB3GenRequest.class },
                    l_response);
            
            WEB3AdminEquityForcedSettleOrderDLHandler l_handler = new WEB3AdminEquityForcedSettleOrderDLHandler();
            l_service =
                (WEB3AdminEquityForcedSettleOrderDLService)Services.getService(
                        WEB3AdminEquityForcedSettleOrderDLService.class);

            WEB3AdminForcedSettleDownloadRequestFortest l_request = new WEB3AdminForcedSettleDownloadRequestFortest();

            l_response = l_handler.getDownloadFile(l_request);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
