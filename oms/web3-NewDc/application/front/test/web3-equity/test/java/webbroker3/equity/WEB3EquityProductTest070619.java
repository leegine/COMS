head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityProductTest070619.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityProductTest070619 extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityProductTest070619.class);

    public WEB3EquityProductTest070619(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_getRightCondOrderEndDay_C0001()
    {
        final String STR_METHOD_NAME = " test_getRightCondOrderEndDay_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setYearlyBooksClosingDate(
                WEB3DateUtility.getDate("20070622","yyyyMMdd"));
            l_eqtypeProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(1001L);
            Date l_datRightCondOrderEndDay = 
                l_equityProduct.getRightCondOrderEndDay();
            int l_intCompareToDay = WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate("20070619","yyyyMMdd"), l_datRightCondOrderEndDay);
            assertEquals(0, l_intCompareToDay);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    public void test_getDevidendRightDate_C0001()
    {
        final String STR_METHOD_NAME = " test_getDevidendRightDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setYearlyBooksClosingDate(
                WEB3DateUtility.getDate("20090910","yyyyMMdd"));
            l_eqtypeProductParams.setProductId(1001L);
            
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(1001L);
            Date l_datRightCondOrderEndDay = 
                l_equityProduct.getDevidendRightDate();
            int l_intCompareToDay = WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate("20090909","yyyyMMdd"), l_datRightCondOrderEndDay);
            assertEquals(1, l_intCompareToDay);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
