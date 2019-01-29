head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ForeignPositionContractTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 外貨残高情報Test（WEB3ForeignPositionContractTest.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/08 トウ鋒鋼 (中訊) 新規作成
 Revision History : 2007/08/20 トウ鋒鋼 仕様変更モデル165
 */

package webbroker3.tradingpower;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCashBalanceFrgnParams;
import webbroker3.tradingpower.data.TpCashBalanceFrgnRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ForeignPositionContractTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ForeignPositionContractTest.class);

    WEB3ForeignPositionContract l_foreignPositionContract = new WEB3ForeignPositionContract();

    public WEB3ForeignPositionContractTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetForeignPositionBalance_C0001()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_foreignPositionContract.setTpCashBalanceFrgnParams(null);

            int l_intSpecifiedPoint = 0;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertNull(l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetForeignPositionBalance_C0002()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            l_foreignPositionContract.setTpCashBalanceFrgnParams(l_tpCashBalanceFrgnParams);

            int l_intSpecifiedPoint = 0;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertEquals(new Double(1000), l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetForeignPositionBalance_C0003()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            l_foreignPositionContract.setTpCashBalanceFrgnParams(l_tpCashBalanceFrgnParams);

            int l_intSpecifiedPoint = 1;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertEquals(new Double(1001), l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetForeignPositionBalance_C0004()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            l_foreignPositionContract.setTpCashBalanceFrgnParams(l_tpCashBalanceFrgnParams);

            int l_intSpecifiedPoint = 2;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertEquals(new Double(1002), l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetForeignPositionBalance_C0005()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            l_foreignPositionContract.setTpCashBalanceFrgnParams(l_tpCashBalanceFrgnParams);

            int l_intSpecifiedPoint = 3;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertEquals(new Double(1003), l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetForeignPositionBalance_C0006()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            l_foreignPositionContract.setTpCashBalanceFrgnParams(l_tpCashBalanceFrgnParams);

            int l_intSpecifiedPoint = 4;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertEquals(new Double(1004), l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetForeignPositionBalance_C0007()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0007";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            l_foreignPositionContract.setTpCashBalanceFrgnParams(l_tpCashBalanceFrgnParams);

            int l_intSpecifiedPoint = 5;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertEquals(new Double(1005), l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetForeignPositionBalance_C0008()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionBalance_C0008";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            l_foreignPositionContract.setTpCashBalanceFrgnParams(l_tpCashBalanceFrgnParams);

            int l_intSpecifiedPoint = 6;
            Double l_dblaccountBalance =
                l_foreignPositionContract.getForeignPositionBalance(l_intSpecifiedPoint);

            assertEquals(new Double(0), l_dblaccountBalance);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 顧客勘定残高（マスタ情報）（外貨）テーブルを取得
     * 検索結果 == null または 検索結果.size() == 0 の場合
     *
     */
    public void testCreateForeignPositionContract_C0001()
    {
        final String STR_METHOD_NAME = "testCreateForeignPositionContract_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TpCashBalanceFrgnRow.TYPE);

            WEB3ForeignPositionContract l_result =
                WEB3ForeignPositionContract.createForeignPositionContract(333812512203L, "A0");

            assertNull(l_result.tpCashBalanceFrgnParams);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客勘定残高（マスタ情報）（外貨）テーブルを取得
     * 以外 の場合
     *
     */
    public void testCreateForeignPositionContract_C0002()
    {
        final String STR_METHOD_NAME = "testCreateForeignPositionContract_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TpCashBalanceFrgnRow.TYPE);
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();

            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));

            TestDBUtility.insertWithDel(l_tpCashBalanceFrgnParams);

            WEB3ForeignPositionContract l_result =
                WEB3ForeignPositionContract.createForeignPositionContract(333812512203L, "A0");

            assertEquals(9000, l_result.tpCashBalanceFrgnParams.getTpCashBalanceFrgnId());
            assertEquals(333812512203L, l_result.tpCashBalanceFrgnParams.getAccountId());
            assertEquals(33381251220301L, l_result.tpCashBalanceFrgnParams.getSubAccountId());
            assertEquals("A0", l_result.tpCashBalanceFrgnParams.getCurrencyCode());
            assertEquals(1000, l_result.tpCashBalanceFrgnParams.getCashBalanceFrgn0(), 0);
            assertEquals(1001, l_result.tpCashBalanceFrgnParams.getCashBalanceFrgn1(), 0);
            assertEquals(1002, l_result.tpCashBalanceFrgnParams.getCashBalanceFrgn2(), 0);
            assertEquals(1003, l_result.tpCashBalanceFrgnParams.getCashBalanceFrgn3(), 0);
            assertEquals(1004, l_result.tpCashBalanceFrgnParams.getCashBalanceFrgn4(), 0);
            assertEquals(1005, l_result.tpCashBalanceFrgnParams.getCashBalanceFrgn5(), 0);
            assertEquals(WEB3DateUtility.getDate("20070801", "yyyyMMdd"),
                    l_result.tpCashBalanceFrgnParams.getCreatedTimestamp());
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"),
                    l_result.tpCashBalanceFrgnParams.getLastUpdatedTimestamp());
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
