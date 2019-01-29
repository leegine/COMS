head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminForcedSettleSortKeyUnitTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminForcedSettleSortKeyUnitTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminForcedSettleSortKeyUnitTest.class);

    public WEB3AdminForcedSettleSortKeyUnitTest(String arg0)
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
    
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = null;
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }

    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "branchCode";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }

    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "accountCode";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "forcedSettleReason";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "marketCode";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "productCode";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "taxType";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "contractType";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "repaymentDiv";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "repaymentTimeLimit";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "openDate";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0012()
    {
        String STR_METHOD_NAME = ".testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "closeDate";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0013()
    {
        String STR_METHOD_NAME = ".testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "createDate";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0014()
    {
        String STR_METHOD_NAME = ".testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "approveDate";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0015()
    {
        String STR_METHOD_NAME = ".testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "contractQuantity";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0016()
    {
        String STR_METHOD_NAME = ".testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "contractPrice";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0017()
    {
        String STR_METHOD_NAME = ".testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "contractExecPrice";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0018()
    {
        String STR_METHOD_NAME = ".testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "orderQuantity";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0019()
    {
        String STR_METHOD_NAME = ".testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "marginCollateralRate";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0020()
    {
        String STR_METHOD_NAME = ".testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "approveState";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0021()
    {
        String STR_METHOD_NAME = ".testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "abc";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0022()
    {
        String STR_METHOD_NAME = ".testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "approveState";
        l_unit.ascDesc = null;
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0023()
    {
        String STR_METHOD_NAME = ".testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "approveState";
        l_unit.ascDesc = "A";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0024()
    {
        String STR_METHOD_NAME = ".testValidate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "approveState";
        l_unit.ascDesc = "D";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
    
    public void testValidate_0025()
    {
        String STR_METHOD_NAME = ".testValidate_0025()";
        log.entering(TEST_START + STR_METHOD_NAME);
      
        WEB3AdminForcedSettleSortKeyUnit l_unit = new WEB3AdminForcedSettleSortKeyUnit();
        l_unit.keyItem = "approveState";
        l_unit.ascDesc = "abc";
        
        try
        {
            l_unit.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME); 
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }        
    }
}
@
