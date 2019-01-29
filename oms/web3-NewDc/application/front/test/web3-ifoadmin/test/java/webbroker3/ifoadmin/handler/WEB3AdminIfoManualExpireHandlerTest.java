head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効ハンドラ(WEB3AdminIfoManualExpireHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.ifoadmin.WEB3AdminIfoManualExpireServiceInterceptor;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireService;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効ハンドラ)<BR>
 * 管理者・先物OP手動失効ハンドラクラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireHandlerTest.class);

    public WEB3AdminIfoManualExpireHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testgetInputScreen_0001()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminIfoManualExpireService.class);
            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseInputResponse response = handler.getInputScreen(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testgetInputScreen_0002()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock1());

            
            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseInputResponse response = handler.getInputScreen(request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174 , response.errorInfo);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testgetInputScreen_0003()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock());

            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseInputResponse response = handler.getInputScreen(request);

            assertEquals(WEB3AdminIfoManualLapseInputResponse.class , response.getClass());

            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testconfirmManualExpire_0001()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminIfoManualExpireService.class);
            WEB3AdminIfoManualLapseConfirmRequest request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseConfirmResponse response = handler.confirmManualExpire(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testconfirmManualExpire_0002()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock1());

            
            WEB3AdminIfoManualLapseConfirmRequest request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseConfirmResponse response = handler.confirmManualExpire(request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174 , response.errorInfo);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testconfirmManualExpire_0003()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock());

            WEB3AdminIfoManualLapseConfirmRequest request = new WEB3AdminIfoManualLapseConfirmRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseConfirmResponse response = handler.confirmManualExpire(request);

            assertEquals(WEB3AdminIfoManualLapseConfirmResponse.class , response.getClass());
            
            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testrunManualExpire_0001()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminIfoManualExpireService.class);
            WEB3AdminIfoManualLapseRunRequest request = new WEB3AdminIfoManualLapseRunRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseRunResponse response = handler.runManualExpire(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testrunManualExpire_0002()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorRow);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);

            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock1());

            
            WEB3AdminIfoManualLapseRunRequest request = new WEB3AdminIfoManualLapseRunRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseRunResponse response = handler.runManualExpire(request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174 , response.errorInfo);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testrunManualExpire_0003()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock());

            WEB3AdminIfoManualLapseRunRequest request = new WEB3AdminIfoManualLapseRunRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseRunResponse response = handler.runManualExpire(request);

            assertEquals(WEB3AdminIfoManualLapseRunResponse.class , response.getClass());
            
            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testconfirmStatus_0001()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminIfoManualExpireService.class);
            WEB3AdminIfoManualLapseStatusRequest request = new WEB3AdminIfoManualLapseStatusRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseStatusResponse response = handler.confirmStatus(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminIfoManualExpireService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testconfirmStatus_0002()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock1());
           
            WEB3AdminIfoManualLapseStatusRequest request = new WEB3AdminIfoManualLapseStatusRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseStatusResponse response = handler.confirmStatus(request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174 , response.errorInfo);

            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testconfirmStatus_0003()
    {
        String STR_METHOD_NAME = "testgetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImplForMock());

            WEB3AdminIfoManualLapseStatusRequest request = new WEB3AdminIfoManualLapseStatusRequest();
            WEB3AdminIfoManualExpireHandler handler = new WEB3AdminIfoManualExpireHandler();
            WEB3AdminIfoManualLapseStatusResponse response = handler.confirmStatus(request);

            assertEquals(WEB3AdminIfoManualLapseStatusResponse.class , response.getClass());

            Services.overrideService(WEB3AdminIfoManualExpireService.class,
                new WEB3AdminIfoManualExpireServiceImpl());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    class WEB3AdminIfoManualExpireServiceImplForMock extends WEB3ClientRequestService implements WEB3AdminIfoManualExpireService
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
        {
            WEB3GenResponse response = null;
            if (l_request instanceof WEB3AdminIfoManualLapseInputRequest)
            {
                response = new WEB3AdminIfoManualLapseInputResponse();
            }
            
            //管理者・先物OP手動失効確認リクエストの場合
            else if (l_request instanceof WEB3AdminIfoManualLapseConfirmRequest)
            {
                //this.validate手動失効()をコールする。
                response = new WEB3AdminIfoManualLapseConfirmResponse();
            }
            
            //管理者・先物OP手動失効処理起動リクエストの場合
            else if (l_request instanceof WEB3AdminIfoManualLapseRunRequest)
            {
                //this.run手動失効()をコールする。
                response = new WEB3AdminIfoManualLapseRunResponse();
            }
            
            //管理者・先物OP手動失効処理ステータス確認リクエストの場合
            else if (l_request instanceof WEB3AdminIfoManualLapseStatusRequest)
            {
                //this.validate処理ステータス()をコールする。
                response = new WEB3AdminIfoManualLapseStatusResponse();
            }
            else 
            {
                fail();
            }
            return response;
        }
    }
    
    class WEB3AdminIfoManualExpireServiceImplForMock1 extends WEB3ClientRequestService implements WEB3AdminIfoManualExpireService
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName(),
                "部店コードがnullです。");
        }
    }

    /**
     * 管理者テーブルRowを作成.<BR>
     */
    public AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();

        l_administratorParams.setAdministratorId(110120119L);
        l_administratorParams.setAdministratorCode("123456789");
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setInstitutionId(33);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setLoginId(3338111123L);
        l_administratorParams.setPermissionLevel("123");

        return l_administratorParams;
    }
}
@
