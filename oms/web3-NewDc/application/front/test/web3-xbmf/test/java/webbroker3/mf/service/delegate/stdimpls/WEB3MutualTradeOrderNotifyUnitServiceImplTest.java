head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualTradeOrderNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualTradeOrderNotifyUnitServiceImplTest extends TestBaseForMock
{
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualTradeOrderNotifyUnitServiceImplTest.class);
    public WEB3MutualTradeOrderNotifyUnitServiceImplTest(String arg0)
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

    WEB3MutualTradeOrderNotifyUnitServiceImpl l_impl =
        new WEB3MutualTradeOrderNotifyUnitServiceImpl();

    public void testNotifyTradeOrderNotify1()
    {
        final String STR_METHOD_NAME = "testNotifyTradeOrderNotify1()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setName("mf.available.datacode");
            l_branchPreferencesParams.setValue("4");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            HostXbmfOrderNotifyParams l_orderNotifyQueueParams = new HostXbmfOrderNotifyParams();
            l_orderNotifyQueueParams.setBranchCode("381");
            l_orderNotifyQueueParams.setInstitutionCode("0D");
            l_orderNotifyQueueParams.setAccountCode("123");
            l_impl.notifyTradeOrderNotify(l_orderNotifyQueueParams);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
