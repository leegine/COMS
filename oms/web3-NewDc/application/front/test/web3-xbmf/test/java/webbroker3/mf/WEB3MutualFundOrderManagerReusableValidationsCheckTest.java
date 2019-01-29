head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundOrderManagerReusableValidationsCheckTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託発注審査個別チェッククラス(WEB3MutualFundOrderManagerReusableValidationsCheckTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/13 徐大方 (中訊) 新規作成
*/
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFundOrderManagerReusableValidationsCheckTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundOrderManagerReusableValidationsCheckTest.class);

    public WEB3MutualFundOrderManagerReusableValidationsCheckTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
        TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateFrgnMmfDoubleOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateFrgnMmfDoubleOrderCase1()";
        log.entering(STR_METHOD_NAME);

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setProductId(32768);
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);

        try
        {
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            ProductParams l_productparams  =  TestDBUtility.getProductRow();
            l_productparams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productparams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualFundOrderManagerReusableValidationsCheck l_check =
            new WEB3MutualFundOrderManagerReusableValidationsCheck();
        SubAccountImpl l_subAccunt = null;
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            l_subAccunt = new SubAccountImpl(333812512203L,33381251220301L);
            l_mfProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        Date l_datBizDate = null;
        try
        {
            l_check.validateFrgnMmfDoubleOrder(
                l_subAccunt,
                l_mfProduct,
                l_datBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateFrgnMmfDoubleOrderCase2()
    {
        final String STR_METHOD_NAME = "testValidateFrgnMmfDoubleOrderCase2()";
        log.entering(STR_METHOD_NAME);

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

        try
        {
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            ProductParams l_productparams  =  TestDBUtility.getProductRow();
            l_productparams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productparams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualFundOrderManagerReusableValidationsCheck l_check =
            new WEB3MutualFundOrderManagerReusableValidationsCheck();
        SubAccountImpl l_subAccunt = null;
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            l_subAccunt = new SubAccountImpl(333812512203L,33381251220301L);
            l_mfProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        Date l_datBizDate = null;
        try
        {
            l_check.validateFrgnMmfDoubleOrder(
                l_subAccunt,
                l_mfProduct,
                l_datBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateFrgnMmfDoubleOrderCase3()
    {
        final String STR_METHOD_NAME = "testValidateFrgnMmfDoubleOrderCase3()";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = WEB3DateUtility.getDate("20070213", "yyyyMMdd");
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setProductId(32768L);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setProductId(0100000L);
        l_mutualFundOrderUnitParams.setAccountId(333812512203L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        l_mutualFundOrderUnitParams.setBizDate("20070213");
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(32768L);

        try
        {
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_productParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualFundOrderManagerReusableValidationsCheck l_check =
            new WEB3MutualFundOrderManagerReusableValidationsCheck();
        SubAccountImpl l_subAccunt = null;
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            l_subAccunt = new SubAccountImpl(333812512203L,33381251220301L);
            l_mfProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        try
        {
            l_check.validateFrgnMmfDoubleOrder(
                l_subAccunt,
                l_mfProduct,
                l_datBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02733, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateFrgnMmfDoubleOrderCase4()
    {
        final String STR_METHOD_NAME = "testValidateFrgnMmfDoubleOrderCase4()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualFundOrderManagerReusableValidationsCheck l_check =
            new WEB3MutualFundOrderManagerReusableValidationsCheck();
        WEB3MutualFundProduct l_mfProduct = null;
        SubAccountImpl l_subAccunt = null;
        try
        {
            l_mfProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        Date l_datBizDate = null;
        try
        {
            l_check.validateFrgnMmfDoubleOrder(
                l_subAccunt,
                l_mfProduct,
                l_datBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateFrgnMmfDoubleOrderCase5()
    {
        final String STR_METHOD_NAME = "testValidateFrgnMmfDoubleOrderCase5()";
        log.entering(STR_METHOD_NAME);

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();

        try
        {
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualFundOrderManagerReusableValidationsCheck l_check =
            new WEB3MutualFundOrderManagerReusableValidationsCheck();
        SubAccountImpl l_subAccunt = null;
        WEB3MutualFundProduct l_mfProduct = null;
        try
        {
            l_subAccunt = new SubAccountImpl(333812512203L,33381251220301L);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        Date l_datBizDate = null;
        try
        {
            l_check.validateFrgnMmfDoubleOrder(
                l_subAccunt,
                l_mfProduct,
                l_datBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
