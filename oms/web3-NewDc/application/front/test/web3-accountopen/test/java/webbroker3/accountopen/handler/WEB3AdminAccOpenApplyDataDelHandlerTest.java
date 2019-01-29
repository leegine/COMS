head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyDataDelHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除ハンドラ(WEB3AdminAccOpenApplyDataDelHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.159
*/

package webbroker3.accountopen.handler;


import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyDataDelService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyDataDelServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設資料請求データ削除ハンドラ)<BR>
 * 管理者口座開設資料請求データ削除ハンドラ<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelHandlerTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccOpenApplyDataDelHandler.class);

    WEB3AdminAccOpenApplyDataDelHandler l_handler = new WEB3AdminAccOpenApplyDataDelHandler();

    public WEB3AdminAccOpenApplyDataDelHandlerTest(String arg0)
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
     * 異常
     * 
     * 管理者口座開設資料請求データ削除サービスの取得に失敗しました。
     * l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testDisplaySearchScreen_Case001()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminAccOpenApplyDataDelService.class);
            WEB3AdminAccOpenApplyDataDelSearchRequest l_request =
                new WEB3AdminAccOpenApplyDataDelSearchRequest();

            WEB3AdminAccOpenApplyDataDelSearchResponse l_response =
                l_handler.displaySearchScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
                
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImpl());
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80018
     */
    public void testDisplaySearchScreen_Case004()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScreen_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {

            WEB3AdminAccOpenApplyDataDelSearchRequest l_request =
                new WEB3AdminAccOpenApplyDataDelSearchRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest1());
            WEB3AdminAccOpenApplyDataDelSearchResponse l_response =
                l_handler.displaySearchScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80002
     */
    public void testDisplaySearchScreen_Case002()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {

            WEB3AdminAccOpenApplyDataDelSearchRequest l_request =
                new WEB3AdminAccOpenApplyDataDelSearchRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest2());
            WEB3AdminAccOpenApplyDataDelSearchResponse l_response =
                l_handler.displaySearchScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 正常
     */
    public void testDisplaySearchScreen_Case003()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScreen_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelSearchRequest l_request =
                new WEB3AdminAccOpenApplyDataDelSearchRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest3());
            WEB3AdminAccOpenApplyDataDelSearchResponse l_response =
                l_handler.displaySearchScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * 管理者口座開設資料請求データ削除サービスの取得に失敗しました。
     * l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testDleteConfirm_Case001()
    {
        final String STR_METHOD_NAME = "testDleteConfirm_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Services.unregisterService(WEB3AdminAccOpenApplyDataDelService.class);
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();

            WEB3AdminAccOpenApplyDataDelCnfResponse l_response =
                l_handler.deleteConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
                
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImpl());
        }
        
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80002
     */
    public void testDleteConfirm_Case002()
    {
        final String STR_METHOD_NAME = "testDleteConfirm_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {

            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest2());
            WEB3AdminAccOpenApplyDataDelCnfResponse l_response =
                l_handler.deleteConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80018
     */
    public void testDleteConfirm_Case003()
    {
        final String STR_METHOD_NAME = "testDleteConfirm_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {

            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest1());
            WEB3AdminAccOpenApplyDataDelCnfResponse l_response =
                l_handler.deleteConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    
    /*
     * 正常
     */
    public void testDleteConfirm_Case004()
    {
        final String STR_METHOD_NAME = "testDleteConfirm_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest3());
            WEB3AdminAccOpenApplyDataDelCnfResponse l_response =
                l_handler.deleteConfirm(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * 管理者口座開設資料請求データ削除サービスの取得に失敗しました。
     * l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testDeleteComplete_Case001()
    {
        final String STR_METHOD_NAME = "testDeleteComplete_Case001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminAccOpenApplyDataDelService.class);
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();

            WEB3AdminAccOpenApplyDataDelCmpResponse l_response =
                l_handler.deleteComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);

        }
        finally
        {
            Services.registerService(
                WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImpl());
        }
        
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80002
     */
    public void testDleteComplete_Case002()
    {
        final String STR_METHOD_NAME = "testDleteComplete_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {

            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest2());
            WEB3AdminAccOpenApplyDataDelCmpResponse l_response =
                l_handler.deleteComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 異常
     * 
     * execute()メソッドをコールすることが失敗しました。
     * SYSTEM_ERROR_80018
     */
    public void testDleteComplete_Case003()
    {
        final String STR_METHOD_NAME = "testDleteComplete_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {

            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest1());
            WEB3AdminAccOpenApplyDataDelCmpResponse l_response =
                l_handler.deleteComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 正常
     */
    public void testDleteComplete_Case004()
    {
        final String STR_METHOD_NAME = "testDleteConfirm_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(WEB3AdminAccOpenApplyDataDelService.class, new WEB3AdminAccOpenApplyDataDelServiceImplForTest3());
            WEB3AdminAccOpenApplyDataDelCmpResponse l_response =
                l_handler.deleteComplete(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public class WEB3AdminAccOpenApplyDataDelServiceImplForTest1 extends WEB3AdminAccOpenApplyDataDelServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("パラメータタイプ不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータタイプ不正。");
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminAccOpenApplyDataDelServiceImplForTest2 extends WEB3AdminAccOpenApplyDataDelServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3GenResponse l_response = null;
            if (true)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "予期しないシステムエラーが発生しました。");
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }

    public class WEB3AdminAccOpenApplyDataDelServiceImplForTest3 extends WEB3AdminAccOpenApplyDataDelServiceImpl
    {

        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3GenResponse l_response = null;

            if (l_request instanceof WEB3AdminAccOpenApplyDataDelSearchRequest)
            {
                l_response =
                    (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminAccOpenApplyDataDelCnfRequest)
            {
                l_response =
                (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();
            }
            else if (l_request instanceof WEB3AdminAccOpenApplyDataDelCmpRequest)
            {
                l_response =
                (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
}
@
