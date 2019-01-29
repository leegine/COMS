head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiServiceRegistCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiServiceRegistCommonRequestTest extends TestBaseForMock
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceRegistCommonRequestTest.class);
    WEB3AdminSrvRegiServiceRegistCommonRequest l_request = null;
    public WEB3AdminSrvRegiServiceRegistCommonRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminSrvRegiServiceRegistCommonRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateT_01()
    {
        final String STR_METHOD_NAME = "testValidateT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.hashCalOrderDiv = "7";
            l_request.applyDiv = "1";
            l_request.serviceDiv = "10";
            l_request.serviceName = "jiddk";
            l_request.summary = "1001";
            l_request.lotteryDiv = "1";
            l_request.confirmMailDiv = "1";
            l_request.noticeMailDiv = "1";
            l_request.hashCalHowToDiv = "1";
            l_request.sendHowToDiv = "4";
            l_request.sendParamDiv = "1";
            l_request.cryptAccountCodeDiv = "1";
            l_request.paramList = null;
            l_request.hashList = null;
            l_request.url = "djkfdsjfkldjf";
            l_request.url2 = "djkfdsjfkldjf";
            l_request.validate();
            
        }
        catch(Exception l_exc)
        {
            log.error(STR_METHOD_NAME, l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateT_02()
    {
        final String STR_METHOD_NAME = "testValidateT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.hashCalOrderDiv = "9";
            l_request.applyDiv = "1";
            l_request.serviceDiv = "10";
            l_request.serviceName = "jiddk";
            l_request.summary = "1001";
            l_request.lotteryDiv = "1";
            l_request.confirmMailDiv = "1";
            l_request.noticeMailDiv = "1";
            l_request.hashCalHowToDiv = "1";
            l_request.sendHowToDiv = "4";
            l_request.sendParamDiv = "1";
            l_request.cryptAccountCodeDiv = "1";
            l_request.paramList = null;
            l_request.hashList = null;
            l_request.url = "djkfdsjfkldjf";
            l_request.url2 = "djkfdsjfkldjf";
            l_request.validate();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01844, l_ex.getErrorInfo());
        }
        catch(Exception l_exc)
        {
            log.error(STR_METHOD_NAME, l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateT_03()
    {
        final String STR_METHOD_NAME = "testValidateT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.hashCalOrderDiv = "8";
            l_request.applyDiv = "1";
            l_request.serviceDiv = "10";
            l_request.serviceName = "jiddk";
            l_request.summary = "1001";
            l_request.lotteryDiv = "1";
            l_request.confirmMailDiv = "1";
            l_request.noticeMailDiv = "1";
            l_request.hashCalHowToDiv = "1";
            l_request.sendHowToDiv = "4";
            l_request.sendParamDiv = "1";
            l_request.cryptAccountCodeDiv = "1";
            l_request.paramList = null;
            l_request.hashList = null;
            l_request.url = "djkfdsjfkldjf";
            l_request.url2 = "djkfdsjfkldjf";
            l_request.validate();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01844, l_ex.getErrorInfo());
        }
        catch(Exception l_exc)
        {
            log.error(STR_METHOD_NAME, l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    
//    public void testValidateT_03()
//    {
//        final String STR_METHOD_NAME = "testValidateT_03()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            l_request.hashCalOrderDiv = "6";
//            l_request.applyDiv = "1";
//            l_request.serviceDiv = "10";
//            l_request.serviceName = "jiddk";
//            l_request.summary = "1001";
//            l_request.lotteryDiv = "1";
//            l_request.confirmMailDiv = "1";
//            l_request.noticeMailDiv = "1";
//            l_request.hashCalHowToDiv = "1";
//            l_request.sendHowToDiv = "4";
//            l_request.sendParamDiv = "1";
//            l_request.cryptAccountCodeDiv = "1";
//            l_request.paramList = null;
//            l_request.hashList = null;
//            l_request.url = "djkfdsjfkldjf";
//            l_request.url2 = "djkfdsjfkldjf";
//            l_request.validate();
//            
//        }
//        catch(WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01855, l_ex.getErrorInfo());
//        }
//        catch(Exception l_exc)
//        {
//            log.error(STR_METHOD_NAME, l_exc);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
    public void testFortest()
    {
        
    }

}
@
