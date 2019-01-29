head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.02.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBookPriceConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqBookPriceConfirmRequestTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBookPriceConfirmRequestTest.class);
    
    public WEB3FeqBookPriceConfirmRequestTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
    
    /*
     * Test method for 'webbroker3.feq.message.WEB3FeqBookPriceConfirmRequest.validate()'
     */
    public void testValidate() {

    }

    public void testValidate_T001() {
        final String STR_METHOD_NAME = "testValidate_T001()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            l_request.assetId = "";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01919,l_ex.getErrorInfo());
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testValidate_T002() {
        final String STR_METHOD_NAME = "testValidate_T002()";
        log.entering(STR_METHOD_NAME);
        
        l_request.assetId = "1";
        try
        {
            l_request.aftBookAmount = "";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02980,l_ex.getErrorInfo());
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testValidate_T003() {
        final String STR_METHOD_NAME = "testValidate_T003()";
        log.entering(STR_METHOD_NAME);

        l_request.assetId = "1";
 

        try
        {
            l_request.aftBookAmount = "A";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02981,l_ex.getErrorInfo());
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        

        log.exiting(STR_METHOD_NAME);
        
    }
    public void testValidate_T004() {
        final String STR_METHOD_NAME = "testValidate_T004()";
        log.entering(STR_METHOD_NAME);

        l_request.assetId = "1";

        try
        {
            l_request.aftBookAmount = "-2";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02981,l_ex.getErrorInfo());
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        

        log.exiting(STR_METHOD_NAME);
        
    }
    public void testValidate_T005() {
        final String STR_METHOD_NAME = "testValidate_T005()";
        log.entering(STR_METHOD_NAME);

        l_request.assetId = "1";

        try
        {
            l_request.aftBookAmount = "123456789012";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02981,l_ex.getErrorInfo());
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        

        log.exiting(STR_METHOD_NAME);
        
    }

    public void testValidate_T006() {
        final String STR_METHOD_NAME = "testValidate_T006()";
        log.entering(STR_METHOD_NAME);

        l_request.assetId = "1";
        l_request.aftBookAmount = "100";
        try
        {
            l_request.validate();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        catch (Exception l_ex)
        {
            fail();
        }
        

        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    
}
@
