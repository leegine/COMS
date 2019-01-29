head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundProductTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信銘柄クラス(WEB3MutualFundProductTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/12 徐大方 (中訊) 新規作成
Revesion History : 2007/04/12 キョウ再平 (中訊) モデル555対応
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 拡張投信銘柄クラス
 *
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundProductTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductTest.class);

    public WEB3MutualFundProductTest(String arg0)
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

    public void testIsForeignCurencyFundCase1()
    {
        final String STR_METHOD_NAME = "testIsForeignCurencyFundCase1()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setCurrencyCode("0");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
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
        boolean l_blnResult = l_mutualFundProduct.isForeignCurencyFund();
        assertTrue(l_blnResult);

        log.exiting(STR_METHOD_NAME);
    }

    public void testIsForeignCurencyFundCase2()
    {
        final String STR_METHOD_NAME = "testIsForeignCurencyFundCase2()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setCurrencyCode("T0");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
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
        boolean l_blnResult = l_mutualFundProduct.isForeignCurencyFund();
        assertFalse(l_blnResult);

        log.exiting(STR_METHOD_NAME);
    }

    public void testIsForeignCurencyFundCase3()
    {
        final String STR_METHOD_NAME = "testIsForeignCurencyFundCase3()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setCurrencyCode("0");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
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
        boolean l_blnResult = l_mutualFundProduct.isForeignCurencyFund();
        assertFalse(l_blnResult);

        log.exiting(STR_METHOD_NAME);
    }

    public void testIsFrgnMmfCase1()
    {
        final String STR_METHOD_NAME = "testIsFrgnMmfCase1()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
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
        boolean l_blnResult = l_mutualFundProduct.isFrgnMmf();
        assertTrue(l_blnResult);

        log.exiting(STR_METHOD_NAME);
    }

    public void testIsFrgnMmfCase2()
    {
        final String STR_METHOD_NAME = "testIsFrgnMmfCase2()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
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
        boolean l_blnResult = l_mutualFundProduct.isFrgnMmf();
        assertFalse(l_blnResult);

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFrgnMmfExchangeRateCase1()
    {
        final String STR_METHOD_NAME = "testGetFrgnMmfExchangeRateCase1()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setCurrencyCode("01");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
            FrgnMmfExchangeRateParams l_result = l_mutualFundProduct.getFrgnMmfExchangeRate();
            assertEquals("50", WEB3StringTypeUtility.formatNumber(l_result.getTtBuyingRate()));
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
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFrgnMmfExchangeRateCase2()
    {
        final String STR_METHOD_NAME = "testGetFrgnMmfExchangeRateCase2()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setCurrencyCode("02");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
            l_mutualFundProduct.getFrgnMmfExchangeRate();
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
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFrgnAmt0001()
    {
        final String STR_METHOD_NAME = "testGetFrgnAmt0001()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setFrgnNewBuyMinAmt(1234567890);
        l_mutualFundProductParams.setFrgnNewBuyUnitAmt(1234567891);
        l_mutualFundProductParams.setFrgnAddBuyMinAmt(1234567892);
        l_mutualFundProductParams.setFrgnAddBuyUnitAmt(1234567893);
        l_mutualFundProductParams.setFrgnSellMinAmt(1234567894);
        l_mutualFundProductParams.setFrgnSellUnitAmt(1234567895);
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN);
        l_mutualFundProductParams.setRecruitCommissionDiv("1");
        l_mutualFundProductParams.setOpenCloseDiv("1");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);
        try
        {
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
            long l_lngResult1 = l_mutualFundProduct.getFrgnNewBuyMinAmt();
            long l_lngResult2 = l_mutualFundProduct.getFrgnNewBuyUnitAmt();
            long l_lngResult3 = l_mutualFundProduct.getFrgnAddBuyMinAmt();
            long l_lngResult4 = l_mutualFundProduct.getFrgnAddBuyUnitAmt();
            long l_lngResult5 = l_mutualFundProduct.getFrgnSellMinAmt();
            long l_lngResult6 = l_mutualFundProduct.getFrgnSellUnitAmt();
            String l_strRecruitCommissionDiv = l_mutualFundProduct.getRecruitCommissionDiv();
            String l_strOpenCloseDiv = l_mutualFundProduct.getOpenCloseDiv();
            
            assertEquals(1234567890, l_lngResult1);
            assertEquals(1234567891, l_lngResult2);
            assertEquals(1234567892, l_lngResult3);
            assertEquals(1234567893, l_lngResult4);
            assertEquals(1234567894, l_lngResult5);
            assertEquals(1234567895, l_lngResult6);
            assertEquals("1", l_strRecruitCommissionDiv);
            assertEquals("1", l_strOpenCloseDiv);
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

        log.exiting(STR_METHOD_NAME);
    } 
}
@
