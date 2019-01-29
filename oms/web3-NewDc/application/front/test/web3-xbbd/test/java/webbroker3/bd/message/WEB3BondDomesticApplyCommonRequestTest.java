head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondDomesticApplyCommonRequestTest extends TestBaseForMock
{

    public WEB3BondDomesticApplyCommonRequestTest(String arg0)
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
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputRequestTest.class);
    
    WEB3BondDomesticApplyCommonRequest l_request = 
        new WEB3BondDomesticApplyCommonRequest();

    /*
     * Test method for 'webbroker3.bd.message.WEB3BondDomesticApplyCommonRequest.validate()'
     */
    public void testValidate1()
    {
        final String STR_METHOD_MAME = " testValidate1()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = null;
        try
        {
            l_request.validate();
            fail();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }
    
    public void testValidate2()
    {
        final String STR_METHOD_MAME = " testValidate2()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = "1";
        l_request.applyAmount = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02880, l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }
    
    public void testValidate3()
    {
        final String STR_METHOD_MAME = " testValidate3()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = "1";
        l_request.applyAmount ="1.1";
        
        try
        {
            l_request.validate();
            fail();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02881, l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }
    
    public void testValidate4()
    {
        final String STR_METHOD_MAME = " testValidate4()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = "1";
        l_request.applyAmount ="0";
        
        try
        {
            l_request.validate();
            fail();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02882, l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }
    
    public void testValidate5()
    {
        final String STR_METHOD_MAME = " testValidate5()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = "1";
        l_request.applyAmount ="-1";
        
        try
        {
            l_request.validate();
            fail();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02882, l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }
    public void testValidate6()
    {
        final String STR_METHOD_MAME = " testValidate6()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = "1";
        l_request.applyAmount ="1111111111111";
        
        try
        {
            l_request.validate();
            fail();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02883, l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }
    
    public void testValidate7()
    {
        final String STR_METHOD_MAME = " testValidate7()";
        log.entering(TEST_START + STR_METHOD_MAME);
        
        l_request.productId = "1";
        l_request.applyAmount ="1";
        
        try
        {
            l_request.validate();
        }
        
        catch(WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME);
            fail();
            
        }
        catch(Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_MAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_MAME); 
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_MAME);
    }

}
@
