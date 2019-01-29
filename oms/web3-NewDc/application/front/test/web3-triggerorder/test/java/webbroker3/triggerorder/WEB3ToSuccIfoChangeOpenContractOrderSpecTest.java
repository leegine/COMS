head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccIfoChangeOpenContractOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP予約新規建注文訂正内容テスト(WEB3ToSuccIfoChangeOpenContractOrderSpecTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/28 崔遠鵬 (中訊) 新規作成
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP予約新規建注文訂正内容テスト)<BR>
 * @@author 崔遠鵬
 * @@version 1.0
 */
public class WEB3ToSuccIfoChangeOpenContractOrderSpecTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoChangeOpenContractOrderSpecTest.class);

    public WEB3ToSuccIfoChangeOpenContractOrderSpecTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCreateIfoChangeOpenContractOrderSpec_C001()
    {
        final String STR_METHOD_NAME = " testCreateIfoChangeSettleContractOrderSpec_C001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(2);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            WEB3GentradeTrader l_trader = new WEB3GentradeTrader(3338111123L, false);

            WEB3ToSuccIfoChangeOpenContractOrderSpec l_spec =
                WEB3ToSuccIfoChangeOpenContractOrderSpec.createIfoChangeOpenContractOrderSpec(
                    1L,
                    2.0d,
                    3.0d,
                    4.0d,
                    5.0d,
                    WEB3DateUtility.getDate("20080301", "yyyyMMdd"),
                    l_trader,
                    new Double(6.0d),
                    "2");

            assertEquals(l_spec.getModifiedEstimatedPrice(), 4.0, 0);
            assertEquals(l_spec.getModifiedCalcUnitPrice(), 5.0, 0);
            assertEquals(WEB3DateUtility.compareToDay(l_spec.getModifiedExpirationDate(),WEB3DateUtility.getDate(
                "20080301", "yyyyMMdd")), 0);
            assertEquals(l_spec.getTrader().getTraderId(), 3338111123L);
            assertEquals(l_spec.getModifiedPriceAdjustValue().doubleValue(), 6.0d, 0);
            assertEquals(l_spec.getFirstOrderUnitId().longValue(), 0L);
            assertTrue(l_spec.getEveningSessionCarryOverFlag());
            assertEquals(l_spec.getExpirationDateType(), "2");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetSetMethod_C001()
    {
        final String STR_METHOD_NAME = " testGetSetMethod_C001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3ToSuccIfoChangeOpenContractOrderSpec l_spec =
                new WEB3ToSuccIfoChangeOpenContractOrderSpec(
                    0L, 0L, 0.0d, 0.0d);

            l_spec.setModifiedEstimatedPrice(1.0d);
            l_spec.setModifiedCalcUnitPrice(2.0d);
            l_spec.setModifiedExpirationDate(WEB3DateUtility.getDate("20080301", "yyyyMMdd"));
            l_spec.setTrader(new WEB3GentradeTrader(3338111123L, false));
            l_spec.setModifiedPriceAdjustValue(new Double(3.0d));
            l_spec.setFirstOrderUnitId(new Long(4L));
            l_spec.setEveningSessionCarryOverFlag(true);
            l_spec.setExpirationDateType("5");

            assertEquals(l_spec.getModifiedEstimatedPrice(), 1.0d, 0);
            assertEquals(l_spec.getModifiedCalcUnitPrice(), 2.0d, 0);
            assertEquals(WEB3DateUtility.compareToDay(
                l_spec.getModifiedExpirationDate(), WEB3DateUtility.getDate("20080301", "yyyyMMdd")), 0);
            assertEquals(l_spec.getTrader().getTraderId(), 3338111123L);
            assertEquals(l_spec.getModifiedPriceAdjustValue().doubleValue(), 3.0d, 0);
            assertEquals(l_spec.getFirstOrderUnitId().longValue(), 4L);
            assertTrue(l_spec.getEveningSessionCarryOverFlag());
            assertEquals(l_spec.getExpirationDateType(), "5");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
