head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdListServiceImpl;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdListHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdListHandlerTest.class);

    WEB3AdminSrvRegiOtherOrgIdListHandler l_handler =
        new WEB3AdminSrvRegiOtherOrgIdListHandler();

    boolean isException = false;

    public WEB3AdminSrvRegiOtherOrgIdListHandlerTest(String arg0)
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

    public void testOtherOrgIdListReference_T01()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListReference_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                l_handler.otherOrgIdListReference(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdListReference_T02()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListReference_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImplTest());
            isException = true;
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                l_handler.otherOrgIdListReference(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOtherOrgIdListReference_T03()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListReference_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                l_handler.otherOrgIdListReference(l_request);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOtherOrgIdListSearch_T01()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListSearch_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                l_handler.otherOrgIdListReference(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdListSearch_T02()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListSearch_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImplTest());
            isException = true;
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                l_handler.otherOrgIdListReference(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            isException = false;
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOtherOrgIdListSearch_T03()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdListSearch_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();
            WEB3AdminSrvRegiOtherOrgIdListReferenceResponse l_response =
                l_handler.otherOrgIdListReference(l_request);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdListService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdListService.class,
                new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3AdminSrvRegiOtherOrgIdListServiceImplTest extends WEB3AdminSrvRegiOtherOrgIdListServiceImpl
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
            if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdListReferenceRequest)
            {
                //管理者PTS口座開設状況変更検索画面の取得を行う。
                l_response = new WEB3AdminSrvRegiOtherOrgIdListReferenceResponse();
            }
            else if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdListSearchRequest)
            {
                //管理者PTS口座開設状況変更入力画面の取得を行う。
                l_response = new WEB3AdminSrvRegiOtherOrgIdListSearchResponse();
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
