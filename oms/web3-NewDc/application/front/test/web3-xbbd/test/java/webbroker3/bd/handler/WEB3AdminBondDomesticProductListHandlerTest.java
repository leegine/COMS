head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductListService;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImplForMock;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者国内債券銘柄一覧ハンドラテスト)<BR>
 * 管理者国内債券銘柄一覧ハンドラテストクラス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListHandlerTest.class);
    WEB3AdminBondDomesticProductListHandler l_handler =
        new WEB3AdminBondDomesticProductListHandler();
    public WEB3AdminBondDomesticProductListHandlerTest(String arg0)
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

    public void testGetSearchScreenDisplay_case0001()
    {
        final String STR_METHOD_NAME = "testGetSearchScreenDisplay_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListSearchDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListSearchDisplayRequest();
            Services.unregisterService(WEB3AdminBondDomesticProductListService.class);
            WEB3AdminBondDomesticProductListSearchDisplayResponse l_response =
                l_handler.getSearchScreenDisplay(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminBondDomesticProductListService.class,
                new WEB3AdminBondDomesticProductListServiceImplForMock());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchScreenDisplay_case0002()
    {
        final String STR_METHOD_NAME = "testGetSearchScreenDisplay_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImpl", "execute",
                    new Class[]{ WEB3GenRequest.class },
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
            
            WEB3AdminBondDomesticProductListSearchDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListSearchDisplayRequest();
            WEB3AdminBondDomesticProductListSearchDisplayResponse l_response =
                l_handler.getSearchScreenDisplay(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSearchScreenDisplay_case0003()
    {
        final String STR_METHOD_NAME = "testGetSearchScreenDisplay_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminBondDomesticProductListSearchDisplayRequest l_request =
            new WEB3AdminBondDomesticProductListSearchDisplayRequest();
        WEB3AdminBondDomesticProductListSearchDisplayResponse l_responseReturn = new WEB3AdminBondDomesticProductListSearchDisplayResponse();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class },
                l_responseReturn);
        try
        {
            WEB3AdminBondDomesticProductListSearchDisplayResponse l_response = null;
            l_response = l_handler.getSearchScreenDisplay(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3AdminBondDomesticProductListSearchDisplayResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetProductListScreenDisplay_case0001()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticProductListDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListDisplayRequest();
            Services.unregisterService(WEB3AdminBondDomesticProductListService.class);
            WEB3AdminBondDomesticProductListDisplayResponse l_response =
                l_handler.getProductListScreenDisplay(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminBondDomesticProductListService.class,
                    new WEB3AdminBondDomesticProductListServiceImplForMock());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductListScreenDisplay_case0002()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay_case0002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class },
            new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
        
        try
        {
            WEB3AdminBondDomesticProductListDisplayRequest l_request =
                new WEB3AdminBondDomesticProductListDisplayRequest();
            WEB3AdminBondDomesticProductListDisplayResponse l_response =
                l_handler.getProductListScreenDisplay(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductListScreenDisplay_case0003()
    {
        final String STR_METHOD_NAME = "testGetProductListScreenDisplay_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminBondDomesticProductListDisplayRequest l_request = new WEB3AdminBondDomesticProductListDisplayRequest();
        WEB3AdminBondDomesticProductListDisplayResponse l_responseReturn = new WEB3AdminBondDomesticProductListDisplayResponse();
        l_responseReturn.pageIndex = "1";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImpl", "execute",
                new Class[]
                { WEB3GenRequest.class },
                l_responseReturn);
        try
        {
            WEB3AdminBondDomesticProductListDisplayResponse l_response = null;
            l_response = l_handler.getProductListScreenDisplay(l_request);
            assertNotNull(l_response);
            assertEquals(WEB3AdminBondDomesticProductListDisplayResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
