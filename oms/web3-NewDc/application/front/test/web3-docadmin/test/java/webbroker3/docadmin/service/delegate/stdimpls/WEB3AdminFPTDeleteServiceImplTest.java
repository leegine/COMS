head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDeleteServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3FPTHistoryInfoUnit;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3AdminFPTDeleteServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteServiceImplTest.class);

    /**
     * 管理者金商法@交付閲覧削除サービスImpl
     */
    WEB3AdminFPTDeleteServiceImpl l_impl = null;
    /**
     *
     */
    public WEB3AdminFPTDeleteServiceImplTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        //AdminPermissionParams
        TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
        AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("331");
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
        l_adminPermissionParams.setTransactionCategory("G0102");
        TestDBUtility.insertWithDel(l_adminPermissionParams);
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
    }

    /**
     *
     */
    public void testValidateChangedScreen_case0001()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_case0001()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");

        try
        {
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3AdminFPTDeleteConfirmRequest l_request =
            new WEB3AdminFPTDeleteConfirmRequest();

        WEB3FPTHistoryInfoUnit l_fptHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();
        l_fptHistoryInfoUnit.acceptCode = "";
        l_fptHistoryInfoUnit.acceptName = "";
        l_fptHistoryInfoUnit.branchCode = l_administratorParams.getBranchCode();
        l_fptHistoryInfoUnit.deleteFlg = "";
        l_fptHistoryInfoUnit.documentDiv = "";
        l_fptHistoryInfoUnit.documentNames = "";
        l_fptHistoryInfoUnit.productName = "";
        l_fptHistoryInfoUnit.updaterCode = "";
        l_fptHistoryInfoUnit.docuDeliDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.createDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.updateTimeStamp =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.batoProductCode = "123";
        l_fptHistoryInfoUnit.documentCategory = "987";

        l_request.financialProductTradeDeleteRow = l_fptHistoryInfoUnit;

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            l_impl.validateChangedScreen(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02952, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateChangedScreen_case0002()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_case0002()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");

        DocDeliveryManagementParams l_docDeliveryManagementParams =
            new DocDeliveryManagementParams();
        l_docDeliveryManagementParams.setAccountId(1234567L);
        l_docDeliveryManagementParams.setInstitutionCode("0D");
        l_docDeliveryManagementParams.setBranchCode("381");
        l_docDeliveryManagementParams.setAccountCode("123456");
        l_docDeliveryManagementParams.setDocumentDiv("0");
        l_docDeliveryManagementParams.setProductCode("456");
        l_docDeliveryManagementParams.setDeliveryDate(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
        l_docDeliveryManagementParams.setLastUpdater("");
        l_docDeliveryManagementParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setDocumentCategory("987");

        try
        {
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3AdminFPTDeleteConfirmRequest l_request =
            new WEB3AdminFPTDeleteConfirmRequest();

        WEB3FPTHistoryInfoUnit l_fptHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();
        l_fptHistoryInfoUnit.acceptCode = "123456";
        l_fptHistoryInfoUnit.acceptName = "瓜田";
        l_fptHistoryInfoUnit.branchCode = l_administratorParams.getBranchCode();
        l_fptHistoryInfoUnit.deleteFlg = "1";
        l_fptHistoryInfoUnit.documentDiv = "0";
        l_fptHistoryInfoUnit.documentNames = "aa";
        l_fptHistoryInfoUnit.productName = "wa";
        l_fptHistoryInfoUnit.updaterCode = "7654321";
        l_fptHistoryInfoUnit.docuDeliDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.createDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.updateTimeStamp =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.batoProductCode = "456";
        l_fptHistoryInfoUnit.documentCategory = "987";

        l_request.financialProductTradeDeleteRow = l_fptHistoryInfoUnit;

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            l_impl.validateChangedScreen(l_request);

            log.exiting(STR_METHOD_NAME);
            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testSubmitChangedScreen_case0001()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_case0001()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");

        try
        {
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3AdminFPTDeleteCompleteRequest l_request =
            new WEB3AdminFPTDeleteCompleteRequest();

        WEB3FPTHistoryInfoUnit l_fptHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();
        l_fptHistoryInfoUnit.acceptCode = "";
        l_fptHistoryInfoUnit.acceptName = "";
        l_fptHistoryInfoUnit.branchCode = l_administratorParams.getBranchCode();
        l_fptHistoryInfoUnit.deleteFlg = "";
        l_fptHistoryInfoUnit.documentDiv = "";
        l_fptHistoryInfoUnit.documentNames = "";
        l_fptHistoryInfoUnit.productName = "";
        l_fptHistoryInfoUnit.updaterCode = "";
        l_fptHistoryInfoUnit.docuDeliDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.createDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.updateTimeStamp =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.batoProductCode = "123";
        l_fptHistoryInfoUnit.documentCategory = "987";

        l_request.financialProductTradeDeleteRow = l_fptHistoryInfoUnit;
        l_request.password = "123";

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            l_impl.submitChangedScreen(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02952, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
                TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testSubmitChangedScreen_case0002()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_case0002()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");

        DocDeliveryManagementParams l_docDeliveryManagementParams =
            new DocDeliveryManagementParams();
        l_docDeliveryManagementParams.setAccountId(1234567L);
        l_docDeliveryManagementParams.setInstitutionCode("0D");
        l_docDeliveryManagementParams.setBranchCode("381");
        l_docDeliveryManagementParams.setAccountCode("123456");
        l_docDeliveryManagementParams.setDocumentDiv("0");
        l_docDeliveryManagementParams.setProductCode("456");
        l_docDeliveryManagementParams.setDeliveryDate(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
        l_docDeliveryManagementParams.setLastUpdater("bb");
        l_docDeliveryManagementParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setDocumentCategory("987");

        try
        {
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3AdminFPTDeleteCompleteRequest l_request =
            new WEB3AdminFPTDeleteCompleteRequest();

        WEB3FPTHistoryInfoUnit l_fptHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();
        l_fptHistoryInfoUnit.acceptCode = "123456";
        l_fptHistoryInfoUnit.acceptName = "瓜田";
        l_fptHistoryInfoUnit.branchCode = l_administratorParams.getBranchCode();
        l_fptHistoryInfoUnit.deleteFlg = "1";
        l_fptHistoryInfoUnit.documentDiv = "0";
        l_fptHistoryInfoUnit.documentNames = "aa";
        l_fptHistoryInfoUnit.productName = "wa";
        l_fptHistoryInfoUnit.updaterCode = "7654321";
        l_fptHistoryInfoUnit.docuDeliDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.createDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.updateTimeStamp =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.batoProductCode = "456";
        l_fptHistoryInfoUnit.documentCategory = "987";

        l_request.financialProductTradeDeleteRow = l_fptHistoryInfoUnit;
        l_request.password = "123";

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            l_impl.submitChangedScreen(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisDocDeliveryManagement =
                l_queryProcessor.doFindAllQuery(DocDeliveryManagementParams.TYPE);
            assertEquals(0, l_lisDocDeliveryManagement.size());

            List l_lisDocDeliveryManagementHist =
                l_queryProcessor.doFindAllQuery(DocDeliveryManagementHistParams.TYPE);
            assertEquals(1, l_lisDocDeliveryManagementHist.size());

            DocDeliveryManagementHistParams l_actualDocDeliveryManagementHistParams =
                (DocDeliveryManagementHistParams)l_lisDocDeliveryManagementHist.get(0);
            assertEquals(1234567L, l_actualDocDeliveryManagementHistParams.getAccountId());
            assertEquals("0D", l_actualDocDeliveryManagementHistParams.getInstitutionCode());
            assertEquals("381", l_actualDocDeliveryManagementHistParams.getBranchCode());
            assertEquals("123456", l_actualDocDeliveryManagementHistParams.getAccountCode());
            assertEquals("0", l_actualDocDeliveryManagementHistParams.getDocumentDiv());
            assertEquals("456", l_actualDocDeliveryManagementHistParams.getProductCode());
            assertEquals(WEB3DateUtility.getDate("20071107", "yyyyMMdd"),
                l_actualDocDeliveryManagementHistParams.getDeliveryDate());
            assertEquals("1",
                l_actualDocDeliveryManagementHistParams.getDeleteFlag().intValue() + "");
            assertEquals("bb", l_actualDocDeliveryManagementHistParams.getLastUpdater());
            assertEquals(WEB3DateUtility.getDate("20071107", "yyyyMMdd"),
                l_actualDocDeliveryManagementHistParams.getCreatedTimestamp());
            assertEquals(WEB3DateUtility.getDate("20071107", "yyyyMMdd"),
                l_actualDocDeliveryManagementHistParams.getLastUpdatedTimestamp());
            assertEquals("330001", l_actualDocDeliveryManagementHistParams.getDeleteUser());
            assertNotNull(l_actualDocDeliveryManagementHistParams);

            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
                TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0001()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        WEB3AdminFPTDeleteConfirmRequest l_request = null;

        try
        {
            l_impl.execute(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testExecute_case0002()
    {
        final String STR_METHOD_NAME = "testExecute_case0002()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        WEB3AdminFPTRegistConfirmRequest l_request =
            new WEB3AdminFPTRegistConfirmRequest();

        try
        {
            l_impl.execute(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testExecute_case0003()
    {
        final String STR_METHOD_NAME = "testExecute_case0003()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");

        try
        {
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3AdminFPTDeleteConfirmRequest l_request =
            new WEB3AdminFPTDeleteConfirmRequest();

        WEB3FPTHistoryInfoUnit l_fptHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();
        l_fptHistoryInfoUnit.acceptCode = "";
        l_fptHistoryInfoUnit.acceptName = "";
        l_fptHistoryInfoUnit.branchCode = l_administratorParams.getBranchCode();
        l_fptHistoryInfoUnit.deleteFlg = "";
        l_fptHistoryInfoUnit.documentDiv = "";
        l_fptHistoryInfoUnit.documentNames = "";
        l_fptHistoryInfoUnit.productName = "";
        l_fptHistoryInfoUnit.updaterCode = "";
        l_fptHistoryInfoUnit.docuDeliDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.createDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.updateTimeStamp =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.batoProductCode = "123";
        l_fptHistoryInfoUnit.documentCategory = "987";

        l_request.financialProductTradeDeleteRow = l_fptHistoryInfoUnit;

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            l_impl.execute(l_request);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02952, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0004()
    {
        final String STR_METHOD_NAME = "testExecute_case0004()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("331");

        DocDeliveryManagementParams l_docDeliveryManagementParams =
            new DocDeliveryManagementParams();
        l_docDeliveryManagementParams.setAccountId(1234567L);
        l_docDeliveryManagementParams.setInstitutionCode("0D");
        l_docDeliveryManagementParams.setBranchCode("381");
        l_docDeliveryManagementParams.setAccountCode("123456");
        l_docDeliveryManagementParams.setDocumentDiv("0");
        l_docDeliveryManagementParams.setProductCode("456");
        l_docDeliveryManagementParams.setDeliveryDate(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
        l_docDeliveryManagementParams.setLastUpdater("bb");
        l_docDeliveryManagementParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20071107", "yyyyMMdd"));
        l_docDeliveryManagementParams.setDocumentCategory("987");

        try
        {
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3AdminFPTDeleteCompleteRequest l_request =
            new WEB3AdminFPTDeleteCompleteRequest();

        WEB3FPTHistoryInfoUnit l_fptHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();
        l_fptHistoryInfoUnit.acceptCode = "123456";
        l_fptHistoryInfoUnit.acceptName = "瓜田";
        l_fptHistoryInfoUnit.branchCode = l_administratorParams.getBranchCode();
        l_fptHistoryInfoUnit.deleteFlg = "1";
        l_fptHistoryInfoUnit.documentDiv = "0";
        l_fptHistoryInfoUnit.documentNames = "aa";
        l_fptHistoryInfoUnit.productName = "wa";
        l_fptHistoryInfoUnit.updaterCode = "7654321";
        l_fptHistoryInfoUnit.docuDeliDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.createDate =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.updateTimeStamp =
            WEB3DateUtility.getDate("20071107", "yyyyMMdd");
        l_fptHistoryInfoUnit.batoProductCode = "456";
        l_fptHistoryInfoUnit.documentCategory = "987";

        l_request.financialProductTradeDeleteRow = l_fptHistoryInfoUnit;
        l_request.password = "123";

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            l_impl.execute(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisDocDeliveryManagement =
                l_queryProcessor.doFindAllQuery(DocDeliveryManagementParams.TYPE);
            assertEquals(0, l_lisDocDeliveryManagement.size());

            List l_lisDocDeliveryManagementHist =
                l_queryProcessor.doFindAllQuery(DocDeliveryManagementHistParams.TYPE);
            assertEquals(1, l_lisDocDeliveryManagementHist.size());

            DocDeliveryManagementHistParams l_actualDocDeliveryManagementHistParams =
                (DocDeliveryManagementHistParams)l_lisDocDeliveryManagementHist.get(0);
            assertEquals(1234567L, l_actualDocDeliveryManagementHistParams.getAccountId());
            assertEquals("0D", l_actualDocDeliveryManagementHistParams.getInstitutionCode());
            assertEquals("381", l_actualDocDeliveryManagementHistParams.getBranchCode());
            assertEquals("123456", l_actualDocDeliveryManagementHistParams.getAccountCode());
            assertEquals("0", l_actualDocDeliveryManagementHistParams.getDocumentDiv());
            assertEquals("456", l_actualDocDeliveryManagementHistParams.getProductCode());
            assertEquals(WEB3DateUtility.getDate("20071107", "yyyyMMdd"),
                l_actualDocDeliveryManagementHistParams.getDeliveryDate());
            assertEquals("1",
                l_actualDocDeliveryManagementHistParams.getDeleteFlag().intValue() + "");
            assertEquals("bb", l_actualDocDeliveryManagementHistParams.getLastUpdater());
            assertEquals(WEB3DateUtility.getDate("20071107", "yyyyMMdd"),
                l_actualDocDeliveryManagementHistParams.getCreatedTimestamp());
            assertEquals(WEB3DateUtility.getDate("20071107", "yyyyMMdd"),
                l_actualDocDeliveryManagementHistParams.getLastUpdatedTimestamp());
            assertEquals("330001", l_actualDocDeliveryManagementHistParams.getDeleteUser());
            assertNotNull(l_actualDocDeliveryManagementHistParams);

            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
//                TestDBUtility.deleteAll(AdministratorParams.TYPE);
//                TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//                TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testCreateQueryString_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0004()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        try
        {
            Method l_method =
                WEB3AdminFPTDeleteServiceImpl.class.getDeclaredMethod(
                    "createQueryString",
                    new Class[] {});
            l_method.setAccessible(true);
            String l_strActual = (String)l_method.invoke(l_impl, new Object[] {});

            String l_strExpected =
                " institution_code = ? "
                + " and branch_code = ? "
                + " and account_code like ? || '%' "
                + " and document_div = ? "
                + " and product_code = ? "
                + " and delivery_date　@=　@? "
                + " and document_category　@=　@? ";
            assertEquals(l_strExpected, l_strActual);

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     */
    public void testCreateQueryDataContainer_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0004()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTDeleteServiceImpl();

        try
        {
            String l_strInstitutionCode = "0D";
            WEB3AdminFPTDeleteConfirmRequest l_request =
                new WEB3AdminFPTDeleteConfirmRequest();
            l_request.financialProductTradeDeleteRow = new WEB3FPTHistoryInfoUnit();
            l_request.financialProductTradeDeleteRow.branchCode = "123";
            l_request.financialProductTradeDeleteRow.acceptCode = "1234567";
            l_request.financialProductTradeDeleteRow.documentDiv = "1";
            l_request.financialProductTradeDeleteRow.batoProductCode = "543";
            l_request.financialProductTradeDeleteRow.docuDeliDate =
                WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            l_request.financialProductTradeDeleteRow.documentCategory = "2";

            Method l_method =
                WEB3AdminFPTDeleteServiceImpl.class.getDeclaredMethod(
                    "createQueryDataContainer",
                    new Class[] {String.class, WEB3GenRequest.class});
            l_method.setAccessible(true);
            Object[] l_actualQueryDataContainers =
                (Object[])l_method.invoke(
                    l_impl, new Object[] {l_strInstitutionCode, l_request});

            assertEquals(7, l_actualQueryDataContainers.length);
            assertEquals("0D", l_actualQueryDataContainers[0]);
            assertEquals("123", l_actualQueryDataContainers[1]);
            assertEquals("1234567", l_actualQueryDataContainers[2]);
            assertEquals("1", l_actualQueryDataContainers[3]);
            assertEquals("543", l_actualQueryDataContainers[4]);
            assertEquals(
                WEB3DateUtility.getDate("20080130", "yyyyMMdd"),
                l_actualQueryDataContainers[5]);
            assertEquals("2", l_actualQueryDataContainers[6]);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
    }

    /**
     *
     */
    public class LoginInfoImplTest extends LoginInfoImpl
    {
        /**
         * @@return long
         */
        public long getLoginId()
        {
            return 1001L;
        }
    }

}
@
