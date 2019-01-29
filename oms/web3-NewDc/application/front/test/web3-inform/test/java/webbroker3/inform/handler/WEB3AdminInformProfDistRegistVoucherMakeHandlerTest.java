head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.16.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistRegistVoucherMakeHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AdminInformProfDistRegistVoucherMakeHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/08 èôçGàÃ (íÜêu) êVãKçÏê¨
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.inform.WEB3AdminInformProfDistSellTransSrcListServiceInterceptor;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistRegistVoucherMakeServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author èôçGàÃ(íÜêu)
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeHandlerTest extends TestBaseForMock
{
    /**
     * (ÉçÉOèoóÕÉÜÅ[ÉeÉBÉäÉeÉB)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminInformProfDistRegistVoucherMakeHandlerTest.class);

    public WEB3AdminInformProfDistRegistVoucherMakeHandlerTest(String arg0)
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
     * ìoò^èÛãµåüçı
     */
    public void testDistStatusSearch()
    {
        String STR_METHOD_NAME = "testDistStatusSearch_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistStatusSearchInputRequest request =
                new WEB3AdminInformProfDistStatusSearchInputRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistStatusSearchInputResponse response =
                handler.registStatusSearch(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ì`ï[çÏê¨ì¸óÕ
     */
    public void testVoucherMakeInp()
    {
        String STR_METHOD_NAME = "testVoucherMakeInp";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherMakeInpRequest request =
                new WEB3AdminInformProfDistVoucherMakeInpRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherMakeInpResponse response =
                handler.voucherMakeInp(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ì`ï[çÏê¨ämîF
     */
    public void testVoucherMakeCnf()
    {
        String STR_METHOD_NAME = "testVoucherMakeCnf";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherMakeCnfRequest request =
                new WEB3AdminInformProfDistVoucherMakeCnfRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherMakeCnfResponse response =
                handler.voucherMakeCnf(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ì`ï[çÏê¨äÆóπ
     */
    public void testVoucherMakeCmp()
    {
        String STR_METHOD_NAME = "testVoucherMakeCmp";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherMakeCmpRequest request =
                new WEB3AdminInformProfDistVoucherMakeCmpRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherMakeCmpResponse response =
                handler.voucherMakeCmp(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ìoò^å˚ç¿éQè∆
     */
    public void testDistAccountInfoRef()
    {
        String STR_METHOD_NAME = "testDistAccountInfoRef";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherInfoRefRequest request =
                new WEB3AdminInformProfDistVoucherInfoRefRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherInfoRefResponse response =
                handler.registAccountRef(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ìoò^å˚ç¿ïœçXämîF
     */
    public void testDistAccountInfoChgeCnf()
    {
        String STR_METHOD_NAME = "testDistAccountInfoRef";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherChgCnfRequest request =
                new WEB3AdminInformProfDistVoucherChgCnfRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherChgCnfResponse response =
                handler.registAccountChgeCnf(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ìoò^å˚ç¿ïœçXäÆóπ
     */
    public void testDistAccountInfoChgeCmp()
    {
        String STR_METHOD_NAME = "testDistAccountInfoChgeCmp";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherChgCmpRequest request =
                new WEB3AdminInformProfDistVoucherChgCmpRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherChgCmpResponse response =
                handler.registAccountChgeCmp(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ìoò^å˚ç¿éÊè¡ämîF
     */
    public void testDistAccountInfoCancCnf()
    {
        String STR_METHOD_NAME = "testDistAccountInfoChgeCmp";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherCancCnfRequest request =
                new WEB3AdminInformProfDistVoucherCancCnfRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherCancCnfResponse response =
                handler.registAccountCancCnf(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

    /*
     * ìoò^å˚ç¿éÊè¡äÆóπ
     */
    public void testDistAccountInfoCancCmp()
    {
        String STR_METHOD_NAME = "testDistAccountInfoChgeCmp";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminInformProfDistRegistVoucherMakeService.class);
            WEB3AdminInformProfDistVoucherCancCmpRequest request =
                new WEB3AdminInformProfDistVoucherCancCmpRequest();
            WEB3AdminInformProfDistRegistVoucherMakeHandler handler =
                new WEB3AdminInformProfDistRegistVoucherMakeHandler();
            WEB3AdminInformProfDistVoucherCancCmpResponse response =
                handler.registAccountCancCmp(request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , response.errorInfo);

            Services.registerService(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
                new WEB3LogSysTimeInterceptor());
            Services.addInterceptor(WEB3AdminInformProfDistRegistVoucherMakeService.class,
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

}
@
