head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadHandlerTest.java;


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
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdDownloadService;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdDownloadHandlerTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdDownloadHandlerTest.class);

    private static WEB3AdminSrvRegiOtherOrgIdDownloadHandler l_handler=
        new WEB3AdminSrvRegiOtherOrgIdDownloadHandler();

    boolean isException = false;

    public WEB3AdminSrvRegiOtherOrgIdDownloadHandlerTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testOtherOrgIdDownload_C0001()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdDownload_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();
            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                l_handler.otherOrgIdDownload(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
                new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOtherOrgIdDownload_C0002()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdDownload_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
                new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest());
            isException = true;
            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();
            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                l_handler.otherOrgIdDownload(l_request);
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
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
                new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOtherOrgIdDownload_C0003()
    {
        final String STR_METHOD_NAME = "testOtherOrgIdDownload_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
                new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest());
            WEB3AdminSrvRegiOtherOrgIdDownloadRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();
            WEB3AdminSrvRegiOtherOrgIdDownloadResponse l_response =
                l_handler.otherOrgIdDownload(l_request);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
                new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
 
    class WEB3AdminSrvRegiOtherOrgIdDownloadServiceImplTest
        extends WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl
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
            if (l_request instanceof WEB3AdminSrvRegiOtherOrgIdDownloadRequest)
            {
                //管理者PTS口座開設状況変更検索画面の取得を行う。
                l_response = new WEB3AdminSrvRegiOtherOrgIdDownloadResponse();
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
