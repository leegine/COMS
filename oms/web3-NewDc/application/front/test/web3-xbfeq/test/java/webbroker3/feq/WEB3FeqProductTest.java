head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqProductTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqProductTest extends TestBaseForMock {
    
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductTest.class);
   
    
    public WEB3FeqProductTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.feq.WEB3FeqProduct.getCurrency()'
     */
    
    
    
    
    
    public void testGetCurrency_T001() 
    {
        final String STR_METHOD_NAME = "testGetCurrency_T001()";
        log.entering(STR_METHOD_NAME);
        
        
       
        WEB3GentradeCurrency l_gentradeCurrency = null;
        
      
        
        try
        {
            
            
            
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            
            l_feqProductParams.setProductId(l_productParams.getProductId());
            l_feqProductParams.setInstitutionCode("0D");
            l_feqProductParams.setCurrencyCode("123");
            TestDBUtility.insertWithDel(l_feqProductParams);

            
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams  = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("123");
            l_genCurrencyParams.setCurrentSellRate(1.0D);
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            
            WEB3FeqProduct l_WEB3FeqProduct = new WEB3FeqProduct(l_feqProductParams);
            
            
            
            
            l_gentradeCurrency = l_WEB3FeqProduct.getCurrency();
            
 
            
            assertEquals("123",l_gentradeCurrency.getCurrencyCode());
            
            
            
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage() ,l_ex);
            fail();
        }
        
        
        
        
        
        log.exiting(STR_METHOD_NAME);
        
        
        
    }

}
@
