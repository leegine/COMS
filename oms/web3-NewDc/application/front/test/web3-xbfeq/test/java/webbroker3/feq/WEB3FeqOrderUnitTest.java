head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderUnitTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderUnitTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderUnitTest.class);
   
    
    public WEB3FeqOrderUnitTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.feq.WEB3FeqOrderUnit.getCurrency()'
     */
    public void testGetCurrency() 
    {
        final String STR_METHOD_NAME = "testGetCurrency()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCurrency l_gentradeCurrency = null;

       try
       {
           
           TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
           FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
           
           
           l_feqOrderUnitParams.setProductId(1001L);
           l_feqOrderUnitParams.setOrderUnitId(1234L);
           
           TestDBUtility.insertWithDel(l_feqOrderUnitParams);

           TestDBUtility.deleteAll(ProductParams.TYPE);
           ProductParams l_productParams = TestDBUtility.getProductRow();
           l_productParams.setProductId(1001L);
           l_productParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_productParams);

           //FeqProductParams
           TestDBUtility.deleteAll(FeqProductParams.TYPE);
           FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
           
           l_feqProductParams.setProductId(l_productParams.getProductId());
           l_feqProductParams.setInstitutionCode("0D");
           l_feqProductParams.setCurrencyCode("ABC");

           TestDBUtility.insertWithDel(l_feqProductParams);

           TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
           GenCurrencyParams l_genCurrencyParams  = TestDBUtility.getGenCurrencyRow();
           l_genCurrencyParams.setInstitutionCode("0D");
           l_genCurrencyParams.setCurrencyCode("ABC");
           l_genCurrencyParams.setCurrentSellRate(1.0D);
           TestDBUtility.insertWithDel(l_genCurrencyParams);
           
           //InstitutionParams
           TestDBUtility.deleteAll(InstitutionParams.TYPE);
           InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
           l_institutionParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_institutionParams);

           WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);

           l_gentradeCurrency=  l_feqOrderUnit.getCurrency();

           assertEquals("ABC",l_gentradeCurrency.getCurrencyCode());
       }
       catch (Exception l_ex)
       {
           log.error( l_ex.getMessage(),l_ex);
           fail();
       }

        log.exiting(STR_METHOD_NAME);

    }
    public void testGetExecStatusDiv_C0001()
    {
        final String STR_METHOD_NAME = "testGetExecStatusDiv_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();

            l_feqOrderUnitParams.setConfirmedQuantity(120);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");
            
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);

            String l_strExecStatusDiv =  l_feqOrderUnit.getExecStatusDiv();

            assertEquals("1", l_strExecStatusDiv);
        }
        catch (Exception l_ex)
        {
            log.error( l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetExecStatusDiv_C0002()
    {
        final String STR_METHOD_NAME = "testGetExecStatusDiv_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();

            l_feqOrderUnitParams.setConfirmedQuantity(120);
            l_feqOrderUnitParams.setExecutedQuantity(120);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");
            
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);

            String l_strExecStatusDiv =  l_feqOrderUnit.getExecStatusDiv();

            assertEquals("2", l_strExecStatusDiv);
        }
        catch (Exception l_ex)
        {
            log.error( l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetExecStatusDiv_C0003()
    {
        final String STR_METHOD_NAME = "testGetExecStatusDiv_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();

            l_feqOrderUnitParams.setConfirmedQuantity(120);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);

            String l_strExecStatusDiv =  l_feqOrderUnit.getExecStatusDiv();

            assertEquals("3", l_strExecStatusDiv);
        }
        catch (Exception l_ex)
        {
            log.error( l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetExecStatusDiv_C0004()
    {
        final String STR_METHOD_NAME = "testGetExecStatusDiv_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();

            l_feqOrderUnitParams.setConfirmedQuantity(120);
            l_feqOrderUnitParams.setExecutedQuantity(120);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);

            String l_strExecStatusDiv =  l_feqOrderUnit.getExecStatusDiv();

            assertEquals("4", l_strExecStatusDiv);
        }
        catch (Exception l_ex)
        {
            log.error( l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetExecStatusDiv_C0005()
    {
        final String STR_METHOD_NAME = "testGetExecStatusDiv_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();

            l_feqOrderUnitParams.setTemporaryExecutionFlag("5");

            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);

            String l_strExecStatusDiv =  l_feqOrderUnit.getExecStatusDiv();

            assertEquals("0", l_strExecStatusDiv);
        }
        catch (Exception l_ex)
        {
            log.error( l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
