head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerServiceImplTest3.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 取引余力サービスImplテスト（WEB3TPTradingPowerServiceImplTest3.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/08 トウ鋒鋼 (中訊) 新規作成
 Revision History : 2007/08/08 トウ鋒鋼 (中訊) モデルNo.166
 */
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCashBalanceFrgnParams;
import webbroker3.tradingpower.data.TpCashBalanceFrgnRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTradingPowerServiceImplTest3 extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImplTest3.class);

    WEB3TPTradingPowerServiceImpl l_tradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();

    public WEB3TPTradingPowerServiceImplTest3(String name)
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

    /**
     * get外貨残高情報
     * 引数.補助口座がnullの場合
     *
     */
    public void testGetForeignPositionContract_C0001()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionContract_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeSubAccount l_subAccount = null;
            String l_strCurrencyCode = "A0";

            WEB3ForeignPositionContract l_foreignPositionContract =
                l_tradingPowerServiceImpl.getForeignPositionContract(l_subAccount, l_strCurrencyCode);
            
            fail();

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_web3BaseException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get外貨残高情報
     * 外貨残高情報を取得がnullの場合
     *
     */
    public void testGetForeignPositionContract_C0002()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionContract_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceFrgnRow.TYPE);
            String l_strCurrencyCode = "A0";
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                        l_subAccountParams.account_id,
                        l_subAccountParams.sub_account_id);

            WEB3ForeignPositionContract l_foreignPositionContract =
                l_tradingPowerServiceImpl.getForeignPositionContract(l_subAccount, l_strCurrencyCode);
            
            assertNull(l_foreignPositionContract.tpCashBalanceFrgnParams);
        }

        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get外貨残高情報
     * 外貨残高情報を取得!= nullの場合
     *
     */
    public void testGetForeignPositionContract_C0003()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionContract_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
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
            
            String l_strCurrencyCode = "A0";
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                        l_subAccountParams.account_id,
                        l_subAccountParams.sub_account_id);

            WEB3ForeignPositionContract l_foreignPositionContract =
                l_tradingPowerServiceImpl.getForeignPositionContract(l_subAccount, l_strCurrencyCode);
            
            assertEquals(9000,l_foreignPositionContract.tpCashBalanceFrgnParams.getTpCashBalanceFrgnId());
            assertEquals(333812512203L,l_foreignPositionContract.tpCashBalanceFrgnParams.getAccountId());
            assertEquals(33381251220301L,l_foreignPositionContract.tpCashBalanceFrgnParams.getSubAccountId());
            assertEquals("A0",l_foreignPositionContract.tpCashBalanceFrgnParams.getCurrencyCode());
            assertEquals(1000,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn0(), 0);
            assertEquals(1001,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn1(), 0);
            assertEquals(1002,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn2(), 0);
            assertEquals(1003,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn3(), 0);
            assertEquals(1004,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn4(), 0);
            assertEquals(1005,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn5(), 0);
            assertEquals(WEB3DateUtility.getDate("20070801", "yyyyMMdd"), 
                    l_foreignPositionContract.tpCashBalanceFrgnParams.getCreatedTimestamp());
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"), 
                    l_foreignPositionContract.tpCashBalanceFrgnParams.getLastUpdatedTimestamp());
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
