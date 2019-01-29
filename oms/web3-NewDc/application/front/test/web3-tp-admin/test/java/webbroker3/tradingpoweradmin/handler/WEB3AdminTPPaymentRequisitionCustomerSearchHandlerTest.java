head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3AdminTPPaymentRequisitionCustomerSearchHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 í£è≠åÜ(íÜêu) êVãKçÏê¨
*/

package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionCustomerSearchService;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTPPaymentRequisitionCustomerSearchHandlerTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchHandlerTest.class);
    WEB3AdminTPPaymentRequisitionCustomerSearchHandler l_handler = null;
    WEB3AdminTPPaymentRequisitionCustomerSearchService l_sevice;
    public WEB3AdminTPPaymentRequisitionCustomerSearchHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3AdminTPPaymentRequisitionCustomerSearchHandler();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //ì¸ã‡êøãÅå⁄ãqåüçıÉTÅ[ÉrÉXImplÇÃéÊìæÇ…é∏îsÇµÇ‹ÇµÇΩ
    public void testGetPaymentRequisitionCustomerSearchInput_C001()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchInput_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionInputResponse l_response = null;
        WEB3AdminTPPaymentRequisitionInputRequest l_request =
            new WEB3AdminTPPaymentRequisitionInputRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            Services.unregisterService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            l_response = l_handler.getPaymentRequisitionCustomerSearchInput(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
                    new WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplForMock());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //ì¸ã‡êøãÅå⁄ãqåüçıì¸óÕï\é¶èàóùÇÃé¿é{Ç…é∏îsÇµÇ‹ÇµÇΩÅB
    public void testGetPaymentRequisitionCustomerSearchInput_C002()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchInput_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionInputResponse l_response = null;
        WEB3AdminTPPaymentRequisitionInputRequest l_request =
            new WEB3AdminTPPaymentRequisitionInputRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getPaymentRequisitionCustomerSearchInput(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //ê≥èÌ?ë©
    public void  testGetPaymentRequisitionCustomerSearchInput_C003()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchInput_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionInputResponse l_response = null;
        WEB3AdminTPPaymentRequisitionInputResponse l_expectResponse =
            new WEB3AdminTPPaymentRequisitionInputResponse();
        WEB3AdminTPPaymentRequisitionInputRequest l_request =
            new WEB3AdminTPPaymentRequisitionInputRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getPaymentRequisitionCustomerSearchInput(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //ì¸ã‡êøãÅå⁄ãqåüçıÉTÅ[ÉrÉXImplÇÃéÊìæÇ…é∏îsÇµÇ‹ÇµÇΩ
    public void testGetPaymentRequisitionCustomerSearchList_C001()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchList_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionListResponse l_response = null;
        WEB3AdminTPPaymentRequisitionListRequest l_request =
            new WEB3AdminTPPaymentRequisitionListRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            Services.unregisterService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            l_response = l_handler.getPaymentRequisitionCustomerSearchList(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
                    new WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplForMock());
        }
        log.exiting(STR_METHOD_NAME);
    }
    //ì¸ã‡êøãÅå⁄ãqåüçıàÍóóï\é¶èàóùÇÃé¿é{Ç…é∏îsÇµÇ‹ÇµÇΩÅB
    public void testGetPaymentRequisitionCustomerSearchList_C002()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchList_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionListResponse l_response = null;
        WEB3AdminTPPaymentRequisitionListRequest l_request =
            new WEB3AdminTPPaymentRequisitionListRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getPaymentRequisitionCustomerSearchList(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //ê≥èÌ?ë©
    public void  testGetPaymentRequisitionCustomerSearchList_C003()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchList_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionListResponse l_response = null;
        WEB3AdminTPPaymentRequisitionListResponse l_expectResponse =
            new WEB3AdminTPPaymentRequisitionListResponse();
        WEB3AdminTPPaymentRequisitionListRequest l_request =
            new WEB3AdminTPPaymentRequisitionListRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getPaymentRequisitionCustomerSearchList(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    //ì¸ã‡êøãÅå⁄ãqåüçıÉTÅ[ÉrÉXImplÇÃéÊìæÇ…é∏îsÇµÇ‹ÇµÇΩ
    public void testGetPaymentRequisitionCustomerSearchDetail_C001()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDetail_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDetailResponse l_response = null;
        WEB3AdminTPPaymentRequisitionDetailRequest l_request =
            new WEB3AdminTPPaymentRequisitionDetailRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            Services.unregisterService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            l_response = l_handler.getPaymentRequisitionCustomerSearchDetail(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
                    new WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplForMock());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //ì¸ã‡êøãÅå⁄ãqåüçıè⁄ç◊ï\é¶èàóùÇÃé¿é{Ç…é∏îsÇµÇ‹ÇµÇΩÅB
    public void testGetPaymentRequisitionCustomerSearchDetail_C002()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchList_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDetailResponse l_response = null;
        WEB3AdminTPPaymentRequisitionDetailRequest l_request =
            new WEB3AdminTPPaymentRequisitionDetailRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getPaymentRequisitionCustomerSearchDetail(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    //ê≥èÌ?ë©
    public void  testGetPaymentRequisitionCustomerSearchDetail_C003()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDetail_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDetailResponse l_response = null;
        WEB3AdminTPPaymentRequisitionDetailResponse l_expectResponse =
            new WEB3AdminTPPaymentRequisitionDetailResponse();
        WEB3AdminTPPaymentRequisitionDetailRequest l_request =
            new WEB3AdminTPPaymentRequisitionDetailRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getPaymentRequisitionCustomerSearchDetail(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //ì¸ã‡êøãÅå⁄ãqåüçıÉTÅ[ÉrÉXImplÇÃéÊìæÇ…é∏îsÇµÇ‹ÇµÇΩ
    public void testGetPaymentRequisitionCustomerSearchDownLoad_C001()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDownLoad_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_response = null;
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request =
            new WEB3AdminTPPaymentRequisitionDownLoadRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            Services.unregisterService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            l_response = l_handler.getPaymentRequisitionCustomerSearchDownLoad(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
                    new WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplForMock());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //ì¸ã‡êøãÅå⁄ãqåüçıÉ_ÉEÉìÉçÅ[Éhï\é¶èàóùÇÃé¿é{Ç…é∏îsÇµÇ‹ÇµÇΩÅB
    public void testGetPaymentRequisitionCustomerSearchDownLoad_C002()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDownLoad_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_response = null;
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request =
            new WEB3AdminTPPaymentRequisitionDownLoadRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            l_response = l_handler.getPaymentRequisitionCustomerSearchDownLoad(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    //ê≥èÌ?ë©
    public void  testGetPaymentRequisitionCustomerSearchDownLoad_C003()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDownLoad_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_response = null;
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_expectResponse =
            new WEB3AdminTPPaymentRequisitionDownLoadResponse();
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request =
            new WEB3AdminTPPaymentRequisitionDownLoadRequest();
        try
        {
            l_sevice =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                        WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl", 
                    "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_expectResponse);
            l_response = l_handler.getPaymentRequisitionCustomerSearchDownLoad(l_request);
            assertEquals(l_expectResponse, l_response);
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
