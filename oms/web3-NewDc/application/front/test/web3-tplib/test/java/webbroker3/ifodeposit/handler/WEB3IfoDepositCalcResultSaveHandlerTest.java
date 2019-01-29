head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcResultSaveHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoDepositCalcResultSaveHandlerTest
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/25 陸文靜（中訊）新規作成
*/
package webbroker3.ifodeposit.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcResultSaveHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility l_log = WEB3LogUtility.getInstance(
            WEB3IfoDepositCalcResultSaveHandlerTest.class);

    public WEB3IfoDepositCalcResultSaveHandlerTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    WEB3IfoDepositCalcResultSaveHandler l_ifoDepositCalcResultSaveHandler = null;
    private WEB3IfoDepositCalcResultSaveService l_service = null;  
    protected void setUp() throws Exception
    {
        super.setUp();
        l_ifoDepositCalcResultSaveHandler = new WEB3IfoDepositCalcResultSaveHandler();
    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //証拠金計算結果保存サービスを取得できませんでした。
    public void testIfoDepositCalcResultSaveRequest_C0001()
    {
        final String STR_METHOD_NAME = "testdocumentDeliverHistoryRegist_C0001()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();
        try
        {
            l_service =
                (WEB3IfoDepositCalcResultSaveService)Services.getService(WEB3IfoDepositCalcResultSaveService.class);
            Services.unregisterService(WEB3IfoDepositCalcResultSaveService.class);
            WEB3IfoDepositCalcResultSaveResponse l_response = l_ifoDepositCalcResultSaveHandler.ifoDepositCalcResultSaveRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            l_log.error(l_ex.getMessage(), l_ex);
            l_log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3IfoDepositCalcResultSaveService.class, l_service);
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //証拠金計算結果保存に失敗しました。
    public void testIfoDepositCalcResultSaveRequest_C0002()
    {

        final String STR_METHOD_NAME = "testIfoDepositCalcResultSaveRequest_C0002()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseException l_Exception=new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"処理対象外。");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);

            WEB3IfoDepositCalcResultSaveResponse l_response =
                l_ifoDepositCalcResultSaveHandler.ifoDepositCalcResultSaveRequest(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    //証拠金計算結果保存に失敗しました。
    public void testIfoDepositCalcResultSaveRequest_C0003()
    {

        final String STR_METHOD_NAME = "testIfoDepositCalcResultSaveRequest_C0003()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3BaseRuntimeException l_Exception=new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80025,null,"処理対象外。");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Exception);

            WEB3IfoDepositCalcResultSaveResponse l_response =
                l_ifoDepositCalcResultSaveHandler.ifoDepositCalcResultSaveRequest(l_request);
                assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIfoDepositCalcResultSaveRequest_C0004()
    {

        final String STR_METHOD_NAME = "testIfoDepositCalcResultSaveRequest_C0004()";
        l_log.debug(TEST_START + STR_METHOD_NAME);
        WEB3IfoDepositCalcResultSaveRequest l_request = new WEB3IfoDepositCalcResultSaveRequest();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3IfoDepositCalcResultSaveResponse l_Response=new WEB3IfoDepositCalcResultSaveResponse();
            l_Response.errorMessage = "123";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    l_Response);
            WEB3IfoDepositCalcResultSaveResponse l_response = l_ifoDepositCalcResultSaveHandler.ifoDepositCalcResultSaveRequest(l_request);
                assertEquals("123", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            l_log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        l_log.debug(TEST_END + STR_METHOD_NAME);
    }

}
@
