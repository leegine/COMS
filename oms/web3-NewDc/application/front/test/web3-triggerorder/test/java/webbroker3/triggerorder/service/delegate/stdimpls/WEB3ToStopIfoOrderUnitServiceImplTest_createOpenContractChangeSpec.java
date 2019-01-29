head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.19.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToStopIfoOrderUnitServiceImplTest_createOpenContractChangeSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3ToStopIfoOrderUnitServiceImplTest_createOpenContractChangeSpec.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/07/06 崔遠鵬(中訊)
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToStopIfoOrderUnitServiceImplTest_createOpenContractChangeSpec extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3ToStopIfoOrderUnitServiceImplTest_createOpenContractChangeSpec.class);

    public WEB3ToStopIfoOrderUnitServiceImplTest_createOpenContractChangeSpec(String arg0)
    {
        super(arg0);
    }

    WEB3ToStopIfoOrderUnitServiceImpl toStopIfoOrderUnitServiceImpl = new WEB3ToStopIfoOrderUnitServiceImpl();

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCreateOpenContractChangeSpec_C0001()
    {
        final String STR_METHOD_NAME = ".testCreateOpenContractChangeSpec_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isEveningSessionOrder", 
            new Class[] {IfoOrderUnit.class},
            Boolean.TRUE);
        try
        {
            // テーブルへデータをインサート
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(IfoOrderUnitRow.TYPE);
            l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20070706","yyyyMMdd"));
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            // 実際メソッドをコール
            IfoOrderUnit l_orderUnit = new IfoContractSettleOrderUnitImpl(1001L);
            WEB3IfoOpenContractChangeSpec l_spec = toStopIfoOrderUnitServiceImpl.createOpenContractChangeSpec(l_orderUnit);

            // 予想結果と実際結果の比較
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "isEveningSessionOrder", 
                new Class[] {IfoOrderUnit.class});
            assertEquals(l_paramsValue.getFirstCalled()[0], l_orderUnit);
            assertEquals(l_spec.getChangeExecCondType().intValue(), 1);
            assertEquals(WEB3DateUtility.compareToDay(l_spec.getChangeExpirationDate(), WEB3DateUtility.getDate("20070706", "yyyyMMdd")), 0);
            assertEquals(WEB3DateUtility.compareToDay(l_spec.getOrderBizDate(), WEB3DateUtility.getDate("20040101", "yyyyMMdd")), 0);
            assertEquals(l_spec.getOrderCond().toString(), "0");
            assertNull(l_spec.getOrderCondOperator());
            assertNull(l_spec.getStopOrderBasePriceType());
            assertEquals((new Double(l_spec.getStopOrderBasePrice())).toString(), "0.0");
            assertEquals((new Double(l_spec.getWLimitPriceChange())).toString(), "0.0");
            assertEquals(l_spec.getExpirationDateType(), "3");
            assertEquals(l_spec.getEveningSessionCarryoverFlag(), true);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
