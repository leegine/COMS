head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioNewOrderSpecTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TraderImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioNewOrderSpecTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioNewOrderSpecTest.class);

    public WEB3AioNewOrderSpecTest(String arg0)
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

    /*
     * １）スーパークラスのメソッドをコールする。
     * ２）決済機@関IDの値をセットする。
     * ３）注文IDの値をセットする。
     * ４）摘要コードの値をセットする。
     * ５）摘要名の値をセットする。
     */
    public void testWEB3AioNewOrderSpec_C0001()
    {
        final String STR_METHOD_NAME = "testWEB3AioNewOrderSpec_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(123);
            l_traderParams.setTraderId(123);
            l_traderParams.setBranchCode("123");
            l_traderParams.setInstitutionCode("120");
            TestDBUtility.insertWithDel(l_traderParams);
    
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("120");
            l_institutionParams.setInstitutionId(120);
            TestDBUtility.insertWithDel(l_institutionParams);

            Trader l_trader = new TraderImpl(123, true);

            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.BOND_BUY;

            AssetTransferTypeEnum l_assetTransferTypeEnum = AssetTransferTypeEnum.CASH_OUT;

            long l_lngProductId = 120;

            double l_dblNetAmount = 1200;

            String l_strDescription = "testWEB3AioNewOrderSpec_C0001 Description";

            Date l_datEstTransferDate = WEB3DateUtility.getDate("20090320", "yyyyMMdd");

            String l_strPaySchemeId = "1001";

            Long l_orderId = new Long(100);

            String l_strRemarkCode = "1002";

            String l_strRemarkName = "4001";

            WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
                l_trader,
                l_orderTypeEnum,
                l_assetTransferTypeEnum,
                l_lngProductId,
                l_dblNetAmount,
                l_strDescription,
                l_datEstTransferDate,
                l_strPaySchemeId,
                l_orderId,
                l_strRemarkCode,
                l_strRemarkName);

            Trader l_traderFromAioNewOrderSpec =
                l_aioNewOrderSpec.getTrader();
            assertEquals(l_traderFromAioNewOrderSpec.getTraderCode(), l_trader.getTraderCode());
            assertEquals(l_traderFromAioNewOrderSpec.getAlt1NameDetails().getFamilyName(), l_trader.getAlt1NameDetails().getFamilyName());
            assertEquals(l_traderFromAioNewOrderSpec.getAlt1NameDetails().getGivenName(), l_trader.getAlt1NameDetails().getGivenName());
            assertEquals(l_traderFromAioNewOrderSpec.getAlt1NameDetails().getMiddleName(), l_trader.getAlt1NameDetails().getMiddleName());
            assertEquals(l_traderFromAioNewOrderSpec.getAlt2NameDetails().getFamilyName(), l_trader.getAlt2NameDetails().getFamilyName());
            assertEquals(l_traderFromAioNewOrderSpec.getAlt2NameDetails().getGivenName(), l_trader.getAlt2NameDetails().getGivenName());
            assertEquals(l_traderFromAioNewOrderSpec.getAlt2NameDetails().getMiddleName(), l_trader.getAlt2NameDetails().getMiddleName());
            assertEquals(l_traderFromAioNewOrderSpec.getLoginId(), l_trader.getLoginId());
            assertEquals(l_traderFromAioNewOrderSpec.getNameDetails().getFamilyName(), l_trader.getNameDetails().getFamilyName());
            assertEquals(l_traderFromAioNewOrderSpec.getNameDetails().getGivenName(), l_trader.getNameDetails().getGivenName());
            assertEquals(l_traderFromAioNewOrderSpec.getNameDetails().getMiddleName(), l_trader.getNameDetails().getMiddleName());
            assertEquals(l_traderFromAioNewOrderSpec.getTraderId(), l_trader.getTraderId());
            assertEquals(l_traderFromAioNewOrderSpec.getTraderType(), l_trader.getTraderType());

            assertEquals(OrderTypeEnum.BOND_BUY, l_aioNewOrderSpec.getOrderTypeEnum());
            assertEquals(AssetTransferTypeEnum.CASH_OUT, l_aioNewOrderSpec.getAssetTransferTypeEnum());
            assertEquals(120, l_aioNewOrderSpec.getProductId());
            assertTrue(GtlUtils.Double.isEqual(1200, l_aioNewOrderSpec.getQuantity()));
            assertEquals("testWEB3AioNewOrderSpec_C0001 Description", l_aioNewOrderSpec.getDescription());
            int l_intCompareResult = WEB3DateUtility.compareToDay(
                l_datEstTransferDate,
                l_aioNewOrderSpec.getEstimatedTransferDate());
            assertEquals(0, l_intCompareResult);
            assertEquals("1001", l_aioNewOrderSpec.getPaySchemeId());
            assertEquals(new Long(100), l_aioNewOrderSpec.getOrderId());
            assertEquals("1002", l_aioNewOrderSpec.getRemarkCode());
            assertEquals("4001", l_aioNewOrderSpec.getRemarkName());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
