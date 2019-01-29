head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSwitchOrderRouteServiceImplTest_updateIfoOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注先切替サービス実装クラス(WEB3AdminSwitchOrderRouteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History :
*/

package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3AdminSwitchOrderRouteServiceImplTest_updateIfoOrder extends
        TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminSwitchOrderRouteServiceImplTest_updateIfoOrder.class);

    /**
     * @@param arg0
     */
    public WEB3AdminSwitchOrderRouteServiceImplTest_updateIfoOrder(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    public void testUpdateIfoOrder_0001()
    {

        final String STR_METHOD_NAME = " testUpdateIfoOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(OrderSwitchingParams.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            l_exE.printStackTrace();
            fail();
        }

        // 発注先切替テーブル
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();

        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
 
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.account_id = 333812512246L;
            l_ifoOrderUnitParams.market_id = new Long(3303);
            l_ifoOrderUnitParams.future_option_div = "3";
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            HostFotypeOrderAllParams l_hostFotypeOrderAllParams =
                TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();

        // 証券会社コード
        String l_strInstitutioncode = "0D";
        // フロント発注取引所区分コード
        String l_strFrotExCode = "1";
        // 発注先情報
        WEB3AdminOrderRouteSwitchingInfo l_orderRouteSwitchingInfo =
            new WEB3AdminOrderRouteSwitchingInfo();

        l_orderRouteSwitchingInfo.marketCode = "SP";
        l_orderRouteSwitchingInfo.changedValidFlag = "1";
        l_orderRouteSwitchingInfo.productType = "6";

        // 切替前発注先切替
        WEB3GentradeOrderSwitching l_orderSwitching =
            new WEB3GentradeOrderSwitching(l_orderSwitchingParams);

        try
        {
            Method l_method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "updateIfoOrder",
                new Class[] {
                    String.class,
                    String.class,
                    WEB3AdminOrderRouteSwitchingInfo.class,
                    WEB3GentradeOrderSwitching.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_switchOrderRouteServiceImpl,
                new Object[] {
                    l_strInstitutioncode,
                    l_strFrotExCode,
                    l_orderRouteSwitchingInfo,
                    l_orderSwitching});
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lstIfoOrderUnitParams =
                l_queryProcessor.doFindAllQuery(IfoOrderUnitParams.TYPE);

            assertEquals(1, l_lstIfoOrderUnitParams.size());

            IfoOrderUnitParams l_resultIfoOrderUnitParams =
                (IfoOrderUnitParams)l_lstIfoOrderUnitParams.get(0);

            assertEquals(333812512246L, l_resultIfoOrderUnitParams.getAccountId());

            assertEquals("3", l_resultIfoOrderUnitParams.getFutureOptionDiv());
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(MarketParams.TYPE);
                TestDBUtility.deleteAll(OrderSwitchingParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }

    }

}
@
