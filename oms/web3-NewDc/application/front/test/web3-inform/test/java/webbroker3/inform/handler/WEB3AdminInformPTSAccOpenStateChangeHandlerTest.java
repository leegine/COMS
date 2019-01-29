head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformPTSAccOpenStateChangeHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccOpenStateChangeService;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccOpenStateChangeServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformPTSAccOpenStateChangeHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformPTSAccOpenStateChangeHandlerTest.class);
    WEB3AdminInformPTSAccOpenStateChangeHandler l_handler =
        new WEB3AdminInformPTSAccOpenStateChangeHandler();
    
    boolean isException = false;
    
    public WEB3AdminInformPTSAccOpenStateChangeHandlerTest(String arg0)
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

    public void testDisplaySearchScrean_T01()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScrean_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeSrcRequest();
            WEB3AdminInformPTSAccOpenStateChangeSrcResponse l_response =
                l_handler.displaySearchScrean(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplaySearchScrean_T02()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScrean_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            isException = true;
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeSrcRequest();
            WEB3AdminInformPTSAccOpenStateChangeSrcResponse l_response =
                l_handler.displaySearchScrean(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplaySearchScrean_T03()
    {
        final String STR_METHOD_NAME = "testDisplaySearchScrean_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeSrcRequest();
            WEB3AdminInformPTSAccOpenStateChangeSrcResponse l_response =
                l_handler.displaySearchScrean(l_request);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplayInputScrean_T01()
    {
        final String STR_METHOD_NAME = "testDisplayInputScrean_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            WEB3AdminInformPTSAccOpenStateChangeInpResponse l_response =
                l_handler.displayInputScrean(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testDisplayInputScrean_T02()
    {
        final String STR_METHOD_NAME = "testDisplayInputScrean_T02()";
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            isException = true;
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            WEB3AdminInformPTSAccOpenStateChangeInpResponse l_response =
                l_handler.displayInputScrean(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testDisplayInputScrean_T03()
    {
        final String STR_METHOD_NAME = "testDisplayInputScrean_T03()";
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeInpRequest();
            WEB3AdminInformPTSAccOpenStateChangeInpResponse l_response =
                l_handler.displayInputScrean(l_request);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testChangeConfirm_T01()
    {
        final String STR_METHOD_NAME = "testChangeConfirm_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            WEB3AdminInformPTSAccOpenStateChangeCnfResponse l_response =
                l_handler.changeConfirm(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testChangeConfirm_T02()
    {
        final String STR_METHOD_NAME = "testChangeConfirm_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            isException = true;
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            WEB3AdminInformPTSAccOpenStateChangeCnfResponse l_response =
                l_handler.changeConfirm(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testChangeConfirm_T03()
    {
        final String STR_METHOD_NAME = "testChangeConfirm_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCnfRequest();
            WEB3AdminInformPTSAccOpenStateChangeCnfResponse l_response =
                l_handler.changeConfirm(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testChangeComplete_T01()
    {
        final String STR_METHOD_NAME = "testChangeComplete_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            WEB3AdminInformPTSAccOpenStateChangeCmpResponse l_response =
                l_handler.changeComplete(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testChangeComplete_T02()
    {
        final String STR_METHOD_NAME = "testChangeComplete_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            isException = true;
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            WEB3AdminInformPTSAccOpenStateChangeCmpResponse l_response =
                l_handler.changeComplete(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testChangeComplete_T03()
    {
        final String STR_METHOD_NAME = "testChangeComplete_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImplTest());
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request =
                new WEB3AdminInformPTSAccOpenStateChangeCmpRequest();
            WEB3AdminInformPTSAccOpenStateChangeCmpResponse l_response =
                l_handler.changeComplete(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminInformPTSAccOpenStateChangeService.class);
            Services.registerService(WEB3AdminInformPTSAccOpenStateChangeService.class,
                new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3AdminInformPTSAccOpenStateChangeServiceImplTest
        extends WEB3AdminInformPTSAccOpenStateChangeServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
        {
            if (isException)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "fffffffffffff",
                    "dddddddddd");
            }

            WEB3GenResponse l_response = null;
            if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeSrcRequest)
            {
                //管理者PTS口座開設状況変更検索画面の取得を行う。
                l_response = new WEB3AdminInformPTSAccOpenStateChangeSrcResponse();
            }
            else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeInpRequest)
            {
                //管理者PTS口座開設状況変更入力画面の取得を行う。
                l_response = new WEB3AdminInformPTSAccOpenStateChangeInpResponse();
            }
            else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeCnfRequest)
            {
                //管理者PTS口座開設状況変更確認処理を行う。
                l_response = new WEB3AdminInformPTSAccOpenStateChangeCnfResponse();
            }
            else if (l_request instanceof WEB3AdminInformPTSAccOpenStateChangeCmpRequest)
            {
                //管理者PTS口座開設状況変更完了処理を行う。
                l_response = new WEB3AdminInformPTSAccOpenStateChangeCmpResponse();
            }
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                    this.getClass().getName(),
                    "パラメータタイプ不正。");
            }
            return l_response;
        }
    }
}
@
