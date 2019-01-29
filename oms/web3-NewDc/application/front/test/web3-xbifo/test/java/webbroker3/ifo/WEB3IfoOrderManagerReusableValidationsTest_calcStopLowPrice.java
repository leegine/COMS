head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderManagerReusableValidationsTest_calcStopLowPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP発注審査個別チェックテスト(WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/27 崔遠鵬 (中訊) 新規作成
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoOrderManagerReusableValidationsTest_calcStopLowPrice extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderManagerReusableValidationsTest_calcStopLowPrice.class);

    WEB3IfoOrderManagerReusableValidations l_WEB3IfoOrderManagerReusableValidations =
        new WEB3IfoOrderManagerReusableValidations();

    public WEB3IfoOrderManagerReusableValidationsTest_calcStopLowPrice(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCalcStopLowPrice01()
    {
        final String STR_METHOD_NAME = "testCalcStopLowPrice01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult = l_WEB3IfoOrderManagerReusableValidations
                .calcStopLowPrice(2D, 3D, l_ifoProduct);

            //比較
            assertEquals(new Double(0D), new Double(l_dblResult));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcStopLowPrice02()
    {
        final String STR_METHOD_NAME = "testCalcStopLowPrice02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult = l_WEB3IfoOrderManagerReusableValidations
                .calcStopLowPrice(2D, 2D, l_ifoProduct);

            //比較
            assertEquals(new Double(0D), new Double(l_dblResult));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcStopLowPrice03()
    {
        final String STR_METHOD_NAME = "testCalcStopLowPrice03()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getTickValue", 
            new Class[] {WEB3IfoProductImpl.class, double.class},
            new Double(1D));

        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = new IfoProductParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_IfoProductParams.getRowType());
            l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(l_productParams.getRowType());
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult = l_WEB3IfoOrderManagerReusableValidations
                .calcStopLowPrice(2D, 1D, l_ifoProduct);

            //比較
            assertEquals(new Double(1D), new Double(l_dblResult));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcStopLowPrice_C0004()
    {
        final String STR_METHOD_NAME = "testCalcStopLowPrice_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getTickValue", 
            new Class[] {WEB3IfoProductImpl.class, double.class},
            new Double(7.6D));

        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = new IfoProductParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_IfoProductParams.getRowType());
            l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(l_productParams.getRowType());
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult =
                l_WEB3IfoOrderManagerReusableValidations.calcStopLowPrice(7.9d, 1.3d, l_ifoProduct);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue", 
                new Class[] {WEB3IfoProductImpl.class, double.class});
            
            assertEquals(new Double(6.6d), l_paramsValue.getFirstCalled()[1]);
            //比較
            assertEquals(7.6, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcStopLowPrice_C0005()
    {
        final String STR_METHOD_NAME = "testCalcStopLowPrice_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getTickValue", 
            new Class[] {WEB3IfoProductImpl.class, double.class},
            new Double(13.2D));

        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = new IfoProductParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_IfoProductParams.getRowType());
            l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(l_productParams.getRowType());
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult =
                l_WEB3IfoOrderManagerReusableValidations.calcStopLowPrice(7.9d, 1.3d, l_ifoProduct);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue", 
                new Class[] {WEB3IfoProductImpl.class, double.class});
            
            assertEquals(new Double(6.6d), l_paramsValue.getFirstCalled()[1]);
            //比較
            assertEquals(13.2d, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcStopLowPrice_C0006()
    {
        final String STR_METHOD_NAME = "testCalcStopLowPrice_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getTickValue", 
            new Class[] {WEB3IfoProductImpl.class, double.class},
            new Double(6.1D));

        try
        {
            //データベースへデータをインサート
            IfoProductParams l_IfoProductParams = new IfoProductParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_IfoProductParams.getRowType());
            l_IfoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_IfoProductParams);

            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(l_productParams.getRowType());
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(l_tradedProductParams.getRowType());
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //実際メソッドをコール
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(1006160060005L);
            double l_dblResult =
                l_WEB3IfoOrderManagerReusableValidations.calcStopLowPrice(7.9d, 1.3d, l_ifoProduct);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getTickValue", 
                new Class[] {WEB3IfoProductImpl.class, double.class});
            
            assertEquals(new Double(6.6d), l_paramsValue.getFirstCalled()[1]);
            //比較
            assertEquals(6.1d, l_dblResult, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
