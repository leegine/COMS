head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondBalanceReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.bd.WEB3BondBalanceReferenceServiceInterceptor;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.bd.service.delegate.WEB3BondBalanceReferenceService;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondBalanceReferenceServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券残高照会ハンドラ)<BR>
 * 債券残高照会ハンドラクラス
 * 
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceHandlerTest.class);

    public WEB3BondBalanceReferenceHandlerTest(String arg0)
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

    public void testGetBalanceTotal_0001()
    {
        final String STR_METHOD_NAME = "testgetInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3BondBalanceReferenceService.class);
        
        WEB3BondBalanceReferenceHandler l_handler = new WEB3BondBalanceReferenceHandler();
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        WEB3BondBalanceReferenceTotalResponse l_response = new WEB3BondBalanceReferenceTotalResponse();
        l_response = l_handler.getBalanceTotal(l_request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        
        //債券残高照会サービス
        Services.registerService(WEB3BondBalanceReferenceService.class,
            new WEB3BondBalanceReferenceServiceImpl());
        //債券残高照会サービス
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        //債券残高照会サービス
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new WEB3BondBalanceReferenceServiceInterceptor());
        //債券残高照会サービス
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetBalanceTotal_0002()
    {
        final String STR_METHOD_NAME = "testgetInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.overrideService(WEB3BondBalanceReferenceService.class, new WEB3BondBalanceReferenceServiceImplForTest());
        
        WEB3BondBalanceReferenceHandler l_handler = new WEB3BondBalanceReferenceHandler();
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        WEB3BondBalanceReferenceTotalResponse l_response = new WEB3BondBalanceReferenceTotalResponse();
        l_response = l_handler.getBalanceTotal(l_request);
        
        assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        
        Services.overrideService(WEB3BondBalanceReferenceService.class, new WEB3BondBalanceReferenceServiceImpl());        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetBalanceTotal_0003()
    {
        final String STR_METHOD_NAME = "testgetInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.overrideService(WEB3BondBalanceReferenceService.class, new WEB3BondBalanceReferenceServiceImplForTest1());
        
        WEB3BondBalanceReferenceHandler l_handler = new WEB3BondBalanceReferenceHandler();
        WEB3BondBalanceReferenceTotalRequest l_request = new WEB3BondBalanceReferenceTotalRequest();
        WEB3BondBalanceReferenceTotalResponse l_response = new WEB3BondBalanceReferenceTotalResponse();
        l_response = l_handler.getBalanceTotal(l_request);
        
        assertEquals(l_response.getClass(), WEB3BondBalanceReferenceTotalResponse.class);
        
        Services.overrideService(WEB3BondBalanceReferenceService.class, new WEB3BondBalanceReferenceServiceImpl());        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public class WEB3BondBalanceReferenceServiceImplForTest extends WEB3BondBalanceReferenceServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
    }
    
    public class WEB3BondBalanceReferenceServiceImplForTest1 extends WEB3BondBalanceReferenceServiceImpl
    {
        public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
        {
                return new WEB3BondBalanceReferenceTotalResponse();
        }
    }
}
@
