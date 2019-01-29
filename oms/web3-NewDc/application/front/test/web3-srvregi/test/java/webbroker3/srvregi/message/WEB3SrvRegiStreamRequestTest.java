head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiStreamRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiStreamRequestTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamRequestTest.class);
    
    
    public WEB3SrvRegiStreamRequestTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    WEB3SrvRegiStreamRequest l_request = new WEB3SrvRegiStreamRequest();
    
    /*
     * Test method for 'webbroker3.srvregi.message.WEB3SrvRegiStreamRequest.validate()'
     */
    public void testValidate_T001()
    {
        final String STR_METHOD_NAME = "testValidate_T0";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = null;

            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00758, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_T002()
    {
        final String STR_METHOD_NAME = "testValidate_T002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "12345";

            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00831, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T003()
    {
        final String STR_METHOD_NAME = "testValidate_T003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "‚P‚Q‚R‚S";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00882, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
   
    //========================================
    public void testValidate_T004()
    {
        final String STR_METHOD_NAME = "testValidate_T004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = null;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00601, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T005()
    {
        final String STR_METHOD_NAME = "testValidate_T005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            
            l_request.tradingType = "4";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00602, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //=========================================
    
    public void testValidate_T006()
    {
        final String STR_METHOD_NAME = "testValidate_T006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            
            
            l_request.productCode = null;
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T007()
    {
        final String STR_METHOD_NAME = "testValidate_T007";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fenght123456";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T008()
    {
        final String STR_METHOD_NAME = "testValidate_T008";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT_12";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01067, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //=========================
    
    public void testValidate_T009()
    {
        final String STR_METHOD_NAME = "testValidate_T009";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT12";
            
            l_request.batTypeCode = null;

            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02202, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidate_T010()
    {
        final String STR_METHOD_NAME = "testValidate_T010";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT12";
            
            l_request.batTypeCode = "1234";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03083, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T011()
    {
        final String STR_METHOD_NAME = "testValidate_T011";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "a12";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03084, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    //============================================
    
    public void testValidate_T012()
    {
        final String STR_METHOD_NAME = "testValidate_T012";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            
            l_request.orderNo = null;
            
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03085, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T013()
    {
        final String STR_METHOD_NAME = "testValidate_T013";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            
            l_request.orderNo = "12345679012";
            
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03086, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T014()
    {
        final String STR_METHOD_NAME = "testValidate_T014";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            
            l_request.orderNo = "fenght80";
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03087, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    //==============================================
    
    
    public void testValidate_T015()
    {
        final String STR_METHOD_NAME = "testValidate_T015";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
 
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            
            l_request.orderNo = "123456789";
            
            
            l_request.deliveryDate = null;
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01079, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T016()
    {
        final String STR_METHOD_NAME = "testValidate_T016";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            
            
            l_request.deliveryDate = "20080230";
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T017()
    {
        final String STR_METHOD_NAME = "testValidate_T017";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            
            
            l_request.deliveryDate = "200802";
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T018()
    {
        final String STR_METHOD_NAME = "testValidate_T018";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            
            
            l_request.deliveryDate = "2008mm32";
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //===============================
    
    public void testValidate_T019()
    {
        final String STR_METHOD_NAME = "testValidate_T018";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            
            
            l_request.deliveryDate = "20080808";
            
            l_request.orderBizDate = null;
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00406, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T020()
    {
        final String STR_METHOD_NAME = "testValidate_T018";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            l_request.deliveryDate = "20080808";
            
            
            l_request.orderBizDate = "20080231";
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02160, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T021()
    {
        final String STR_METHOD_NAME = "testValidate_T018";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            l_request.deliveryDate = "20080808";
            
            
            l_request.orderBizDate = "2008";
            
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02160, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //========================================
    
    public void testValidate_T022()
    {
        final String STR_METHOD_NAME = "testValidate_T018";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            l_request.deliveryDate = "20080808";
            l_request.orderBizDate = "20080501";
            
            l_request.amount = null;
              
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03088, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T023()
    {
        final String STR_METHOD_NAME = "testValidate_T023";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            l_request.deliveryDate = "20080808";
            l_request.orderBizDate = "20080501";
            
            l_request.amount = "1234567890123";
              
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03089, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T024()
    {
        final String STR_METHOD_NAME = "testValidate_T024";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            l_request.deliveryDate = "20080808";
            l_request.orderBizDate = "20080501";
            
            l_request.amount = "‚P‚Q‚R‚S‚T‚U‚V‚W‚X‚O";
              
            
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03090, l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T025()
    {
        final String STR_METHOD_NAME = "testValidate_T025";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "1";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = "123456789";
            l_request.deliveryDate = "20080808";
            l_request.orderBizDate = "20080501";
            l_request.amount = "12345.123";
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T026()
    {
        final String STR_METHOD_NAME = "testValidate_T026";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "2";
            l_request.productCode = null;
            l_request.batTypeCode = null;
            l_request.orderNo = "123456789";
            l_request.deliveryDate = null;
            l_request.orderBizDate = null;
            l_request.amount = null;
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_T027()
    {
        final String STR_METHOD_NAME = "testValidate_T027";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.serviceDiv = "1234";
            l_request.tradingType = "3";
            l_request.productCode = "fengHT12";
            l_request.batTypeCode = "123";
            l_request.orderNo = null;
            l_request.deliveryDate = null;
            l_request.orderBizDate = null;
            l_request.amount = null;
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
