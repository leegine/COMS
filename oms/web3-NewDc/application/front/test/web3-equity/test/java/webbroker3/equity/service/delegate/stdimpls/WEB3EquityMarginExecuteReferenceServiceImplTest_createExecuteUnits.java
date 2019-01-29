head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest_createExecuteUnits.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3EquityMarginExecuteReferenceServiceImplTest_createExecuteUnits.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/18　@崔遠鵬(中訊)
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.equity.message.WEB3EquityMarginExecuteUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginExecuteReferenceServiceImplTest_createExecuteUnits extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3EquityMarginExecuteReferenceServiceImplTest_createExecuteUnits.class);

    WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl = 
        new WEB3EquityMarginExecuteReferenceServiceImpl();

    public WEB3EquityMarginExecuteReferenceServiceImplTest_createExecuteUnits(String arg0)
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

    public void test_createExecuteUnits_C0001()
    {
        final String STR_METHOD_NAME = ".test_createExecuteUnits_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // テーブルへデータをインサート
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_eqtypeOrderExecutionParams.getRowType());

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(5566778899L);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.0d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(5566778899L);

            WEB3EquityMarginExecuteUnit[] l_equityMarginExecuteUnit =
                l_equityMarginExecuteReferenceServiceImpl.createExecuteUnits(l_eqTypeOrderUnit);

            // 予想結果と実際結果の比較
            assertEquals(0, l_equityMarginExecuteUnit.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_createExecuteUnits_C0002()
    {
        final String STR_METHOD_NAME = ".test_createExecuteUnits_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // テーブルへデータをインサート
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_eqtypeOrderExecutionParams.getRowType());
            l_eqtypeOrderExecutionParams.setExecTimestamp(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(5566778899L);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.0d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(5566778899L);

            WEB3EquityMarginExecuteUnit[] l_equityMarginExecuteUnit =
                l_equityMarginExecuteReferenceServiceImpl.createExecuteUnits(l_eqTypeOrderUnit);

            // 予想結果と実際結果の比較
            assertEquals(1, l_equityMarginExecuteUnit.length);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040924","yyyyMMdd"),
                l_equityMarginExecuteUnit[0].executionTimestamp));
            assertEquals("0", l_equityMarginExecuteUnit[0].execQuantity);
            assertEquals("0.1", l_equityMarginExecuteUnit[0].execPrice);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_createExecuteUnits_C0003()
    {
        final String STR_METHOD_NAME = ".test_createExecuteUnits_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // テーブルへデータをインサート
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_eqtypeOrderExecutionParams.getRowType());
            l_eqtypeOrderExecutionParams.setExecTimestamp(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(841208L);
            l_eqtypeOrderExecutionParams.setExecQuantity(0.2D);
            l_eqtypeOrderExecutionParams.setExecPrice(0.3D);
            l_eqtypeOrderExecutionParams.setExecTimestamp(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(5566778899L);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0.0d);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            // 実際メソッドをコール
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(5566778899L);

            WEB3EquityMarginExecuteUnit[] l_equityMarginExecuteUnit =
                l_equityMarginExecuteReferenceServiceImpl.createExecuteUnits(l_eqTypeOrderUnit);

            // 予想結果と実際結果の比較
            assertEquals(2, l_equityMarginExecuteUnit.length);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040924","yyyyMMdd"),
                l_equityMarginExecuteUnit[0].executionTimestamp));
            assertEquals("0", l_equityMarginExecuteUnit[0].execQuantity);
            assertEquals("0.1", l_equityMarginExecuteUnit[0].execPrice);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20040924","yyyyMMdd"),
                l_equityMarginExecuteUnit[1].executionTimestamp));
            assertEquals("0.2", l_equityMarginExecuteUnit[1].execQuantity);
            assertEquals("0.3", l_equityMarginExecuteUnit[1].execPrice);
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
