head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderAcceptUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.sun.mail.imap.Utility;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEqtypeOrderAcceptRow;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3EquityOrderAcceptUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptUnitServiceImplTest.class);
    WEB3EquityOrderAcceptUnitServiceImpl impl =
        new WEB3EquityOrderAcceptUnitServiceImpl();

    public WEB3EquityOrderAcceptUnitServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderAcceptUnitServiceImpl.notifyOrderAccept(HostEqtypeOrderAcceptParams)'
     */
    public void testNotifyOrderAccept_case1()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_case1()";
        log.entering(STR_METHOD_NAME);

        try
        {

            EqTypeMarketResponseReceiverCallbackServiceImplForMock l_forMock =
                new EqTypeMarketResponseReceiverCallbackServiceImplForMock();
            
            HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams =
                this.getHostEqtypeOrderAcceptRow();
            
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(63101);
            l_branchParams.setInstitutionCode("6D");
            l_branchParams.setInstitutionId(63);
            l_branchParams.setBranchCode("101");

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("6D");
            l_mainAccountParams.setInstitutionId(63L);
            l_mainAccountParams.setBranchId(63101);
            
            
            TestDBUtility.deleteAllAndCommit(HostEqtypeOrderAcceptRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAcceptParams);
            TestDBUtility.insertWithDelAndCommit(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {MarketResponseMessage.class},
                ProcessingResult.SUCCESS_RESULT);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            impl.notifyOrderAccept(l_hostEqtypeOrderAcceptParams);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testNotifyOrderAccept_case2()
    {
        final String STR_METHOD_NAME = "testNotifyOrderAccept_case2()";
        log.entering(STR_METHOD_NAME);

        try
        {

            EqTypeMarketResponseReceiverCallbackServiceImplForMock l_forMock =
                new EqTypeMarketResponseReceiverCallbackServiceImplForMock();
            
            HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams =
                this.getHostEqtypeOrderAcceptRow();
            
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(63101);
            l_branchParams.setInstitutionCode("6D");
            l_branchParams.setInstitutionId(63);
            l_branchParams.setBranchCode("101");

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("6D");
            l_mainAccountParams.setInstitutionId(63L);
            l_mainAccountParams.setBranchId(63101);
            
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("6D");
            
            
            TestDBUtility.deleteAllAndCommit(HostEqtypeOrderAcceptRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAcceptParams);
            TestDBUtility.insertWithDelAndCommit(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {MarketResponseMessage.class},
                ProcessingResult.SUCCESS_RESULT);

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            impl.notifyOrderAccept(l_hostEqtypeOrderAcceptParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public static HostEqtypeOrderAcceptParams getHostEqtypeOrderAcceptRow()
    {
        HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams =
            new HostEqtypeOrderAcceptParams();
        l_hostEqtypeOrderAcceptParams.setRequestCode("AI80A");
        l_hostEqtypeOrderAcceptParams.setInstitutionCode("6D");
        l_hostEqtypeOrderAcceptParams.setBranchCode("101");
        l_hostEqtypeOrderAcceptParams.setAccountCode("1000010");
        l_hostEqtypeOrderAcceptParams.setTraderCode(null);
        l_hostEqtypeOrderAcceptParams.setOrderRequestNumber("000022001");
        l_hostEqtypeOrderAcceptParams.setAcceptStatus("2");
//        l_hostEqtypeOrderAcceptParams.setErrorMessage();
        l_hostEqtypeOrderAcceptParams.setSubmitOrderRouteDiv("0");
//        l_hostEqtypeOrderAcceptParams.setVirtualServerNumberMarket();
//        l_hostEqtypeOrderAcceptParams.setNoticeType();
//        l_hostEqtypeOrderAcceptParams.setNoticeNumber();
//        l_hostEqtypeOrderAcceptParams.setAcceptNumber();
        l_hostEqtypeOrderAcceptParams.setStatus("0");

        l_hostEqtypeOrderAcceptParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));

        l_hostEqtypeOrderAcceptParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        return l_hostEqtypeOrderAcceptParams;
    }
}
@
