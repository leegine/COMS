head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountListChangeInquiryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountListChangeInquiryServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/21 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceResponse;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountListChangeInquiryServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountListChangeInquiryServiceImplTest.class);

    WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl impl = new WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl();

    public WEB3AdminSrvRegiAccountListChangeInquiryServiceImplTest(String arg0)
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

    public void testExecute01()
    {
        final String STR_METHOD_NAME = "testExecute01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        try
        {
            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            WEB3AdminSrvRegiCustomerReferenceRequest l_request = new WEB3AdminSrvRegiCustomerReferenceRequestForTest();
            l_request.branchCode = new String[]{""};
            l_request.serviceDiv = "1234";

            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.keyItem = "appliAttribute";
            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[]{l_sortKey};
            l_request.sortKeys =  l_sortKeys;

            l_request.applyLotteryDiv = "70";

            impl.execute(l_request);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00995, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExecute02()
    {
        final String STR_METHOD_NAME = "testExecute02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        try
        {
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams.setBranchCode("1");
            l_srvAppliAttributeParams.setAppliAttribute("1");
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_srvAppliAttributeParams.setAppliStartDate(l_tsAppliyDate);
            l_calendar.set(2007, 6-1, 15);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_srvAppliAttributeParams.setAppliEndDate(l_tsAppliyDate);
            l_calendar.set(2007, 6-1, 16);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_srvAppliAttributeParams.setLastUpdatedTimestamp(l_tsAppliyDate);
            l_srvAppliAttributeParams.setLastUpdater("abc");
            SrvAppliAttributeParams[] l_srvAppliAttributeParamses = new SrvAppliAttributeParams[]{
                l_srvAppliAttributeParams};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceAttributeLists",
                new Class[] {String.class, String[].class, String.class, String.class, String.class,
                    Timestamp.class, WEB3SrvRegiSortKey[].class},
                l_srvAppliAttributeParamses);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            WEB3GentradeMainAccount l_gentradeMainAccount =  new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class},
                l_gentradeMainAccount);

            AdministratorParams l_administratorParams = new AdministratorParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            WEB3AdminSrvRegiCustomerReferenceRequest l_request = new WEB3AdminSrvRegiCustomerReferenceRequestForTest();
            l_request.branchCode = new String[]{""};
            l_request.serviceDiv = "1234";

            WEB3SrvRegiSortKey l_sortKey = new WEB3SrvRegiSortKey();
            l_sortKey.keyItem = "appliAttribute";
            WEB3SrvRegiSortKey[] l_sortKeys = new WEB3SrvRegiSortKey[]{l_sortKey};
            l_request.sortKeys =  l_sortKeys;

            l_request.accountCode = null;
            l_request.trialStartFrom = null;
            l_request.trialStartTo = null;
            l_request.applyDateFrom = null;
            l_request.applyDateTo = null;
            l_request.applyLotteryDiv = "7";
            l_request.pageSize = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3AdminSrvRegiCustomerReferenceResponse l_response = (WEB3AdminSrvRegiCustomerReferenceResponse)impl.
                execute(l_request);
            assertEquals(l_response.totalPages, "1");
            assertEquals(l_response.totalRecords, "1");
            assertEquals(l_response.pageIndex, "1");
            WEB3AdminSrvRegiCustomerReferenceGroup l_group = l_response.customerList[0];
            assertEquals(l_group.branchCode, "1");
            assertEquals(l_group.accountCode, "251224");
            assertEquals(l_group.applyLotteryDiv, "7");
            assertEquals(WEB3DateUtility.compareToDay(l_group.trialStartDate, WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
            assertEquals(WEB3DateUtility.compareToDay(l_group.trialEndDate, WEB3DateUtility.getDate("20070615", "yyyyMMdd")), 0);
            assertEquals(WEB3DateUtility.compareToDay(l_group.lastUpdateTime, WEB3DateUtility.getDate("20070616", "yyyyMMdd")), 0);
            assertEquals(l_group.lastUpdater, "abc");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    class WEB3AdminSrvRegiCustomerReferenceRequestForTest extends WEB3AdminSrvRegiCustomerReferenceRequest
    {
        public void validate()
        {
            return;
        }
    }
}
@
