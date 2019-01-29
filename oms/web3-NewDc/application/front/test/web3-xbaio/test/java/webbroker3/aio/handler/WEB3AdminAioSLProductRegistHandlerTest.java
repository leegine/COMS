head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductRegistHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3AdminAioSLProductRegistHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/25 ã‡åÜÅiíÜêuÅjêVãKçÏê¨
*/
package webbroker3.aio.handler;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductRegistService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImplForMock;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductRegistHandlerTest extends TestBaseForMock
{
    private WEB3AdminAioSLProductRegistHandler l_handler = null;
    
    private WEB3AdminAioSLProductRegistService l_service = null;
    
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminAioSLProductRegistHandlerTest.class);

    public WEB3AdminAioSLProductRegistHandlerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3AdminAioSLProductRegistHandler();
        Services.overrideService(WEB3AdminAioSLProductRegistService.class,
            new WEB3AdminAioSLProductRegistServiceImplForMock());
    }
    
    protected void tearDown() throws Exception
    {
        this.l_handler = null;
        super.tearDown();
    }
    
    /**
     * íSï€ñ¡ïøìoò^ÉTÅ[ÉrÉXÇéÊìæÇ…é∏îsÇµÇ‹ÇµÇΩÅB
     * 
     * ùeèoàŸèÌÅFSYSTEM_ERROR_80002
     *
     */
    public void testGetSLProductInput_C0001()
    {
        String STR_METHOD_NAME = "testGetSLProductInput_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            this.l_service = (WEB3AdminAioSLProductRegistService)Services.getService(
                    WEB3AdminAioSLProductRegistService.class);
            Services.unregisterService(WEB3AdminAioSLProductRegistService.class);
            
            WEB3AdminSLProductRegistInputRequest l_request = new WEB3AdminSLProductRegistInputRequest();
            WEB3AdminSLProductRegistInputResponse l_response = this.l_handler.getSLProductInput(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductRegistService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * íSï€ñ¡ïøìoò^ì¸óÕâÊñ ï\é¶èàóùÇ™é∏îsÇµÇ‹ÇµÇΩÅB
     * 
     * ùeèoàŸèÌÅFSYSTEM_ERROR_80017
     *
     */
    public void testGetSLProductInput_C0002()
    {
        String STR_METHOD_NAME = "testGetSLProductInput_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            
            WEB3AdminSLProductRegistInputRequest l_request = new WEB3AdminSLProductRegistInputRequest();
            WEB3AdminSLProductRegistInputResponse l_response = this.l_handler.getSLProductInput(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ê≥èÌï‘âÒ
     * 
     * ùeèoàŸèÌÅFSYSTEM_ERROR_80017
     *
     */
    public void testGetSLProductInput_C0003()
    {
        String STR_METHOD_NAME = "testGetSLProductInput_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            WEB3AdminSLProductRegistInputResponse l_expectedResponse = new WEB3AdminSLProductRegistInputResponse();
            l_expectedResponse.nextBizDate = new Date();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            WEB3AdminSLProductRegistInputRequest l_request = new WEB3AdminSLProductRegistInputRequest();
            WEB3AdminSLProductRegistInputResponse l_response = this.l_handler.getSLProductInput(l_request);
            assertEquals(0,WEB3DateUtility.compareToDay(new Date(),l_response.nextBizDate));
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * íSï€ñ¡ïøìoò^ÉTÅ[ÉrÉXÇéÊìæÇ…é∏îsÇµÇ‹ÇµÇΩÅB
     * 
     * ùeèoàŸèÌÅFSYSTEM_ERROR_80002
     *
     */
    public void testValidateSLProductRegist_C0001()
    {
        String STR_METHOD_NAME = "testValidateSLProductRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            this.l_service = (WEB3AdminAioSLProductRegistService)Services.getService(
                    WEB3AdminAioSLProductRegistService.class);
            Services.unregisterService(WEB3AdminAioSLProductRegistService.class);
            
            WEB3AdminSLProductRegistConfirmRequest l_request = new WEB3AdminSLProductRegistConfirmRequest();
            WEB3AdminSLProductRegistConfirmResponse l_response = this.l_handler.validateSLProductRegist(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductRegistService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * íSï€ñ¡ïøìoò^ì¸óÕämîFâÊñ ï\é¶èàóùÇ™é∏îsÇµÇ‹ÇµÇΩÅB
     * 
     * ùeèoàŸèÌÅFSYSTEM_ERROR_80017
     *
     */
    public void testValidateSLProductRegist_C0002()
    {
        String STR_METHOD_NAME = "testValidateSLProductRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            
            WEB3AdminSLProductRegistConfirmRequest l_request = new WEB3AdminSLProductRegistConfirmRequest();
            WEB3AdminSLProductRegistConfirmResponse l_response = this.l_handler.validateSLProductRegist(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ê≥èÌï‘âÒ
     *
     */
    public void testValidateSLProductRegist_C0003()
    {
        String STR_METHOD_NAME = "testValidateSLProductRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            WEB3AdminSLProductRegistConfirmResponse l_expectedResponse = new WEB3AdminSLProductRegistConfirmResponse();
            WEB3SLProductInfoUnit stockLoanProductInfo = new WEB3SLProductInfoUnit();
            stockLoanProductInfo.productCode = "3258";
            
            
            l_expectedResponse.stockLoanProductInfo = stockLoanProductInfo;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            WEB3AdminSLProductRegistConfirmRequest l_request = new WEB3AdminSLProductRegistConfirmRequest();
            WEB3AdminSLProductRegistConfirmResponse l_response = this.l_handler.validateSLProductRegist(l_request);
            assertEquals("3258", l_response.stockLoanProductInfo.productCode);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * íSï€ñ¡ïøìoò^ÉTÅ[ÉrÉXÇéÊìæÇ…é∏îsÇµÇ‹ÇµÇΩÅB
     * 
     * ùeèoàŸèÌÅFSYSTEM_ERROR_80002
     *
     */
    public void testSubmitSLProductRegist_C0001()
    {
        String STR_METHOD_NAME = "testSubmitSLProductRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            this.l_service = (WEB3AdminAioSLProductRegistService)Services.getService(
                    WEB3AdminAioSLProductRegistService.class);
            Services.unregisterService(WEB3AdminAioSLProductRegistService.class);
            
            WEB3AdminSLProductRegistCompleteRequest l_request = new WEB3AdminSLProductRegistCompleteRequest();
            WEB3AdminSLProductRegistCompleteResponse l_response = this.l_handler.submitSLProductRegist(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminAioSLProductRegistService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * íSï€ñ¡ïøìoò^ì¸óÕäÆóπâÊñ ï\é¶èàóùÇ™é∏îsÇµÇ‹ÇµÇΩÅB
     * 
     * ùeèoàŸèÌÅFSYSTEM_ERROR_80017
     *
     */
    public void testSubmitSLProductRegist_C0002()
    {
        String STR_METHOD_NAME = "testSubmitSLProductRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,STR_METHOD_NAME));
            
            WEB3AdminSLProductRegistCompleteRequest l_request = new WEB3AdminSLProductRegistCompleteRequest();
            WEB3AdminSLProductRegistCompleteResponse l_response = this.l_handler.submitSLProductRegist(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ê≥èÌï‘âÒ
     *
     */
    public void testSubmitSLProductRegist_C0003()
    {
        String STR_METHOD_NAME = "testSubmitSLProductRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            WEB3AdminSLProductRegistCompleteResponse l_expectedResponse = new  WEB3AdminSLProductRegistCompleteResponse();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            WEB3AdminSLProductRegistCompleteRequest l_request = new WEB3AdminSLProductRegistCompleteRequest();
            WEB3AdminSLProductRegistCompleteResponse l_response = this.l_handler.submitSLProductRegist(l_request);
            assertEquals(null, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
