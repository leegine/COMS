head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityMarginExecuteReferenceHandlerTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/15　@金傑(中訊) 新規作成
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceResponse;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 株式・信用注文約定照会ハンドラクラスのテスト<BR>
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceHandlerTest extends TestBaseForMock
{
	private WEB3EquityMarginExecuteReferenceRequest l_request = null;

	private WEB3EquityMarginExecuteReferenceHandler l_handler = null;

	private WEB3EquityMarginExecuteReferenceService l_service = null;
	
	private WEB3EquityMarginExecuteReferenceResponse l_response = null;
    
	/**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceHandlerTest.class);
	
    public WEB3EquityMarginExecuteReferenceHandlerTest(String name)
	{
		super(name);
	}
    
    protected void setUp() throws Exception
    {
    	super.setUp();
    	MOCK_MANAGER.setIsMockUsed(true);
    	this.l_handler = new WEB3EquityMarginExecuteReferenceHandler();
    	
    }
    
    protected void tearDown() throws Exception
    {
    	super.tearDown();
    	this.l_handler = null;
    	this.l_service = null;
    }
    
    public void test_searchExecuteReference_C0001()
	{
		final String STR_METHOD_NAME = "test_searchExecuteReference_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);
		MOCK_MANAGER.setIsMockUsed(false);
		try
		{
			l_response = this.l_handler.searchExecuteReference(l_request);
			fail();
		}
		catch (WEB3BaseRuntimeException l_expectedException)
		{
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_expectedException.getErrorInfo());
		}
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_searchExecuteReference_C0002()
	{
		final String STR_METHOD_NAME = "test_searchExecuteReference_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			l_service = (WEB3EquityMarginExecuteReferenceService)Services.getService(WEB3EquityMarginExecuteReferenceService.class);
			Services.unregisterService(WEB3EquityMarginExecuteReferenceService.class);
			l_request = new WEB3EquityMarginExecuteReferenceRequest();
			l_response = this.l_handler.searchExecuteReference(l_request);
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
		}
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
        	Services.registerService(WEB3EquityMarginExecuteReferenceService.class,l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_searchExecuteReference_C0003()
	{
		final String STR_METHOD_NAME = "test_searchExecuteReference_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);
		l_request = new WEB3EquityMarginExecuteReferenceRequest();
		WEB3EquityMarginExecuteReferenceResponse l_expectResponse = 
			new WEB3EquityMarginExecuteReferenceResponse(l_request);
		l_expectResponse.totalPages = "60";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl", 
                "execute",
                new Class[] {WEB3GenRequest.class}, 
                l_expectResponse);
		try
		{
			l_response = this.l_handler.searchExecuteReference(l_request);
			assertNotNull(l_response);
			assertEquals(WEB3EquityMarginExecuteReferenceResponse.class, l_response.getClass());
			assertEquals("60", l_response.totalPages);
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
