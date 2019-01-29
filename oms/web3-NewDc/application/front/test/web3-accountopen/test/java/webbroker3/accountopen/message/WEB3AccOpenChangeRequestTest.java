head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenChangeRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenChangeRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenChangeRequestTest.class);

    public WEB3AccOpenChangeRequestTest(String arg0)
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
     * Test method for 'webbroker3.accountopen.message.WEB3AccOpenChangeRequest.validate()'
     */
    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase2()
    {
        final String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();
            l_request.requestNumber = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03169, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCase3()
    {
        final String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();
            l_request.requestNumber = "1";
            l_request.updateItem = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03170, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase4()
    {
        final String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();
            l_request.requestNumber = "1";
            l_request.updateItem = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03171, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase5()
    {
        final String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();
            l_request.requestNumber = "1";
            l_request.updateItem = "1";
            l_request.updateStatus = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03172, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase6()
    {
        final String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenChangeRequest l_request =
                new WEB3AccOpenChangeRequest();
            l_request.requestNumber = "1";
            l_request.updateItem = "1";
            l_request.updateStatus = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
