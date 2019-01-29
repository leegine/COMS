head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformAccSwElecDeliApplyHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3InformAccSwElecDeliApplyHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/22 金傑（中訊）新規作成
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3ComplianceInfoUnit;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformAccSwElecDeliApplyHandlerTest extends TestBaseForMock
{

    private WEB3InformAccSwElecDeliApplyHandler l_handler = null;
    
    private WEB3InformAccSwElecDeliApplyService l_service = null;
    

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3InformAccSwElecDeliApplyHandlerTest.class);

    public WEB3InformAccSwElecDeliApplyHandlerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_handler = new WEB3InformAccSwElecDeliApplyHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_handler = null;
    }
    
    /**
     * 口座切替・電子交付申込サービスを取得に失敗しましの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testDisplayInputScreen_C0001()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
        WEB3InformAccSwElecDeliApplyInpResponse l_response = null;
        
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService) Services.getService(WEB3InformAccSwElecDeliApplyService.class);
            Services.unregisterService(WEB3InformAccSwElecDeliApplyService.class);
            
            l_response = l_handler.displayInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {

            Services.registerService(WEB3InformAccSwElecDeliApplyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 検索入力が失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testDisplayInputScreen_C0002()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
        WEB3InformAccSwElecDeliApplyInpResponse l_response = null;
        try
        {           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,"検索入力が失敗しました"));
            
            l_response = l_handler.displayInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常結束
     *
     */
    public void testDisplayInputScreen_C0003()
    {
        String STR_METHOD_NAME = "testDisplayInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
        WEB3InformAccSwElecDeliApplyInpResponse l_response = null;
        try
        {           
            
            WEB3InformAccSwElecDeliApplyInpResponse l_expectedResponse = new WEB3InformAccSwElecDeliApplyInpResponse();
            
            l_expectedResponse.accountCode = "1234567";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.displayInputScreen(l_request);
            assertEquals(WEB3InformAccSwElecDeliApplyInpResponse.class, l_response.getClass());
            assertEquals("1234567", l_response.accountCode);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 口座切替・電子交付申込サービスを取得に失敗しましの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testApplyConfirm_C0001()
    {
        String STR_METHOD_NAME = "testApplyConfirm_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        WEB3InformAccSwElecDeliApplyConfResponse l_response = null;
        WEB3InformAccSwElecDeliApplyConfRequest l_request = new WEB3InformAccSwElecDeliApplyConfRequest();
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService) Services.getService(WEB3InformAccSwElecDeliApplyService.class);
            Services.unregisterService(WEB3InformAccSwElecDeliApplyService.class);
            
            l_response = l_handler.applyConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {

            Services.registerService(WEB3InformAccSwElecDeliApplyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 申込確認が失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testApplyConfirm_C0002()
    {
        String STR_METHOD_NAME = "testApplyConfirm_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyConfRequest l_request = new WEB3InformAccSwElecDeliApplyConfRequest();
        WEB3InformAccSwElecDeliApplyConfResponse l_response = null;
        try
        {           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,"申込確認が失敗しました。"));
            
            l_response = l_handler.applyConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常結束
     *
     */
    public void testApplyConfirm_C0003()
    {
        String STR_METHOD_NAME = "testApplyConfirm_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyConfRequest l_request = new WEB3InformAccSwElecDeliApplyConfRequest();
        WEB3InformAccSwElecDeliApplyConfResponse l_response = null;
        try
        {           
            
            WEB3InformAccSwElecDeliApplyConfResponse l_expectedResponse = new WEB3InformAccSwElecDeliApplyConfResponse();
            
            WEB3ComplianceInfoUnit l_unit = new WEB3ComplianceInfoUnit();
            l_unit.branchCode = "381";
            
            l_expectedResponse.complianceInfo = l_unit;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.applyConfirm(l_request);
            assertEquals(WEB3InformAccSwElecDeliApplyConfResponse.class, l_response.getClass());
            assertEquals("381", l_response.complianceInfo.branchCode);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 口座切替・電子交付申込サービスを取得に失敗しましの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80002
     *
     */
    public void testApplyComplete_C0001()
    {
        String STR_METHOD_NAME = "testApplyComplete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(false);
        WEB3InformAccSwElecDeliApplyCmpResponse l_response = null;
        WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService) Services.getService(WEB3InformAccSwElecDeliApplyService.class);
            Services.unregisterService(WEB3InformAccSwElecDeliApplyService.class);
            
            l_response = l_handler.applyComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        finally
        {

            Services.registerService(WEB3InformAccSwElecDeliApplyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 申込完了が失敗しましたの場合
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testApplyComplete_C0002()
    {
        String STR_METHOD_NAME = "testApplyComplete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();
        WEB3InformAccSwElecDeliApplyCmpResponse l_response = null;
        try
        {           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,"申込完了が失敗しました。"));
            
            l_response = l_handler.applyComplete(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常結束
     *
     */
    public void testApplyComplete_C0003()
    {
        String STR_METHOD_NAME = "testApplyComplete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();
        WEB3InformAccSwElecDeliApplyCmpResponse l_response = null;
        try
        {           
            
            WEB3InformAccSwElecDeliApplyCmpResponse l_expectedResponse = new WEB3InformAccSwElecDeliApplyCmpResponse();
            
            WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dateInfo = new WEB3AdminInformAccSwitchElecDeliAppDtInfo();
            l_dateInfo.taxTypeOpenDate = "2000822";
            
            l_expectedResponse.dateInfo = l_dateInfo;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl", 
                    "execute",
                    new Class[]{ WEB3GenRequest.class },
                    l_expectedResponse);
            
            l_response = l_handler.applyComplete(l_request);
            assertEquals(WEB3InformAccSwElecDeliApplyCmpResponse.class, l_response.getClass());
            assertEquals("2000822", l_response.dateInfo.taxTypeOpenDate);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
}
@
