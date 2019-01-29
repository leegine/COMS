head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTUploadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロードサービスImplのテストクラス(WEB3AdminFPTUploadServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/11 周墨洋 (中訊) 新規作成 モデル No.012,No.014
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeAttributeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.define.WEB3FPTStatusDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementHistPK;
import webbroker3.gentrade.data.DocDeliveryManagementHistParams;
import webbroker3.gentrade.data.DocDeliveryManagementPK;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧アップロードサービスImpl)<BR>
 * 管理者金商法@交付閲覧アップロードサービスImplのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminFPTUploadServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadServiceImplTest.class);

    /**
     * 管理者金商法@交付閲覧アップロードサービスImpl
     */
    WEB3AdminFPTUploadServiceImpl l_impl = null;

    /**
     * @@param arg0
     */
    public WEB3AdminFPTUploadServiceImplTest(String arg0)
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
     * getUploadScreen
     */
    public void testGetUploadScreen_case0001()
    {
        final String STR_METHOD_NAME = " testGetUploadScreen_case0001()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.ACC_OPEN);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdminFPTUploadInputRequest l_adminFPTUploadInputRequest =
                new WEB3AdminFPTUploadInputRequest();

            l_impl.getUploadScreen(l_adminFPTUploadInputRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * getUploadScreen
     */
    public void testGetUploadScreen_case0002()
    {
        final String STR_METHOD_NAME = " testGetUploadScreen_case0002()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);

            WEB3AdminFPTUploadInputRequest l_adminFPTUploadInputRequest =
                new WEB3AdminFPTUploadInputRequest();

            l_impl.getUploadScreen(l_adminFPTUploadInputRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * getUploadScreen
     */
    public void testGetUploadScreen_case0003()
    {
        final String STR_METHOD_NAME = " testGetUploadScreen_case0003()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorUploadParams l_dministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_dministratorUploadParams.setBranchCode("381");

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdminFPTUploadInputRequest l_adminFPTUploadInputRequest =
                new WEB3AdminFPTUploadInputRequest();

            WEB3AdminFPTUploadInputResponse l_adminFPTUploadInputresponse =
                l_impl.getUploadScreen(l_adminFPTUploadInputRequest);

            assertNull(l_adminFPTUploadInputresponse.uploadHistoryUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * getUploadScreen
     */
    public void testGetUploadScreen_case0004()
    {
        final String STR_METHOD_NAME = " testGetUploadScreen_case0004()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();
        
        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorUploadParams l_dministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_dministratorUploadParams.setInstitutionCode("0D");
        l_dministratorUploadParams.setBranchCode("381");
        l_dministratorUploadParams.setUploadFileId("金商法@交付閲覧アップロード");
        l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_dministratorUploadParams.setUploadKey(0L);
        l_dministratorUploadParams.setUploadCount(99);
        l_dministratorUploadParams.setMessageCode("748");
        l_dministratorUploadParams.setNote2("2");
        l_dministratorUploadParams.setUploadStartTimestamp(
            WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
        l_dministratorUploadParams.setUploadEndTimestamp(null);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdminFPTUploadInputRequest l_adminFPTUploadInputRequest =
                new WEB3AdminFPTUploadInputRequest();

            WEB3AdminFPTUploadInputResponse l_adminFPTUploadInputresponse =
                l_impl.getUploadScreen(l_adminFPTUploadInputRequest);

            assertNotNull(l_adminFPTUploadInputresponse.uploadHistoryUnit);
            assertEquals("1", l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadStateDiv);
            assertEquals("99", l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadNumber);
            assertEquals(
                WEB3DateUtility.getDate("20071210", "yyyyMMdd"),
                l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadStartDate);
            assertEquals(null, l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadEndDate);
            assertEquals("748", l_adminFPTUploadInputresponse.uploadHistoryUnit.fptErrorId);
            assertEquals("2", l_adminFPTUploadInputresponse.uploadHistoryUnit.statusDiv);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }
    }

    /**
     * getUploadScreen
     */
    public void testGetUploadScreen_case0005()
    {
        final String STR_METHOD_NAME = " testGetUploadScreen_case0005()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        
        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorUploadParams l_dministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_dministratorUploadParams.setInstitutionCode("0D");
        l_dministratorUploadParams.setBranchCode("381");
        l_dministratorUploadParams.setUploadFileId("金商法@交付閲覧アップロード");
        l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_dministratorUploadParams.setUploadKey(0L);
        l_dministratorUploadParams.setUploadCount(99);
        l_dministratorUploadParams.setMessageCode("748");
        l_dministratorUploadParams.setNote2("2");
        l_dministratorUploadParams.setUploadStartTimestamp(
            WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
        l_dministratorUploadParams.setUploadEndTimestamp(
            WEB3DateUtility.getDate("20071211", "yyyyMMdd"));

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdminFPTUploadInputRequest l_adminFPTUploadInputRequest =
                new WEB3AdminFPTUploadInputRequest();

            WEB3AdminFPTUploadInputResponse l_adminFPTUploadInputresponse =
                l_impl.getUploadScreen(l_adminFPTUploadInputRequest);

            assertNotNull(l_adminFPTUploadInputresponse.uploadHistoryUnit);
            assertEquals("2", l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadStateDiv);
            assertEquals("99", l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadNumber);
            assertEquals(
                WEB3DateUtility.getDate("20071210", "yyyyMMdd"),
                l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadStartDate);
            assertEquals(
                WEB3DateUtility.getDate("20071211", "yyyyMMdd"),
                l_adminFPTUploadInputresponse.uploadHistoryUnit.uploadEndDate);
            assertEquals("748", l_adminFPTUploadInputresponse.uploadHistoryUnit.fptErrorId);
            assertEquals("2", l_adminFPTUploadInputresponse.uploadHistoryUnit.statusDiv);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testUndoUploadFile_case0001()
    {
        final String STR_METHOD_NAME = " testUndoUploadFile_case0001()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        try
        {
            WEB3AdminFPTUploadCancelRequest l_adminFPTUploadCancelRequest =
                new WEB3AdminFPTUploadCancelRequest();

            l_adminFPTUploadCancelRequest.uploadId = null;
            l_impl.undoUploadFile(l_adminFPTUploadCancelRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00973, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);                
        }
    }

    /**
     * undoUploadFile
     */
    public void testUndoUploadFile_case0002()
    {
        final String STR_METHOD_NAME = " testUndoUploadFile_case0002()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorUploadParams l_dministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_dministratorUploadParams.setAdministratorUploadId(101L);
        l_dministratorUploadParams.setInstitutionCode("0D");
        l_dministratorUploadParams.setBranchCode("381");
        l_dministratorUploadParams.setUploadFileId("金商法@交付閲覧アップロード");
        l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_dministratorUploadParams.setUploadKey(0L);
        l_dministratorUploadParams.setUploadCount(99);
        l_dministratorUploadParams.setMessageCode("748");
        l_dministratorUploadParams.setNote2("2");
        l_dministratorUploadParams.setUploadStartTimestamp(
            WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
        l_dministratorUploadParams.setUploadEndTimestamp(
            WEB3DateUtility.getDate("20071211", "yyyyMMdd"));

        AdministratorUploadTempParams l_dministratorUploadTempParams =
            TestDBUtility.getAdministratorUploadTempRow();
        l_dministratorUploadTempParams.setAdministratorUploadId(101L);

        try
        {
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);

            TestDBUtility.insertWithDel(l_dministratorUploadParams);
            TestDBUtility.insertWithDel(l_dministratorUploadTempParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadParamsBefore.size());
            assertEquals(
                101,
                ((AdministratorUploadParams)
                    l_lisAdministratorUploadParamsBefore.get(0)).getAdministratorUploadId());

            List l_lisAdministratorUploadTempParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadTempParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadTempParamsBefore.size());
            assertEquals(
                101,
                ((AdministratorUploadTempParams)
                    l_lisAdministratorUploadTempParamsBefore.get(0)).getAdministratorUploadId());

            WEB3AdminFPTUploadCancelRequest l_adminFPTUploadCancelRequest =
                new WEB3AdminFPTUploadCancelRequest();
            l_adminFPTUploadCancelRequest.uploadId = "101";

            WEB3AdminFPTUploadCancelResponse l_adminFPTUploadCancelResponse =
                l_impl.undoUploadFile(l_adminFPTUploadCancelRequest);
            assertNotNull(l_adminFPTUploadCancelResponse);

            l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadParamsBefore.size());
            assertEquals(
                101,
                ((AdministratorUploadParams)
                    l_lisAdministratorUploadParamsBefore.get(0)).getAdministratorUploadId());

            l_lisAdministratorUploadTempParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadTempParams.TYPE);
            assertEquals(0, l_lisAdministratorUploadTempParamsBefore.size());

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }     
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0001()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0001()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        try
        {
            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = null;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00976, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);                
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0002()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0002()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.ACC_OPEN);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[] {""};
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0003()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0003()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorUploadParams l_dministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_dministratorUploadParams.setBranchCode("381");

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[] {""};
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0004()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0004()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorUploadParams l_dministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_dministratorUploadParams.setInstitutionCode("0D");
        l_dministratorUploadParams.setBranchCode("381");
        l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_dministratorUploadParams.setUploadEndTimestamp(null);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[] {""};
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01969, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0005()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0005()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[501];
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02418, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0006()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0006()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(0, l_lisAdministratorUploadParamsBefore.size());

            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[] {"381,2512246,1111111,20071212,SSS"};
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01993, l_ex.getErrorInfo());
            assertEquals(
                "1,381,2512246,1111111,20071212,SSS",
                l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisAdministratorUploadParamsAfter =
                    l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
                assertEquals(1, l_lisAdministratorUploadParamsAfter.size());
                AdministratorUploadParams l_resultAdministratorUploadParams =
                    (AdministratorUploadParams)l_lisAdministratorUploadParamsAfter.get(0);
                assertEquals(null, l_resultAdministratorUploadParams.getNote1());
                assertEquals(WEB3FPTStatusDivDef.LOGIN, l_resultAdministratorUploadParams.getNote2());
                assertEquals(null , l_resultAdministratorUploadParams.getNote3());
                assertNotNull(l_resultAdministratorUploadParams.getAdministratorUploadId() + "");

                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0007()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0007()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(0, l_lisAdministratorUploadParamsBefore.size());

            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[] {"381,251224,1111111,20071212"};
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
            assertEquals("1,381,251224,1111111,20071212", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisAdministratorUploadParamsAfter =
                    l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
                assertEquals(1, l_lisAdministratorUploadParamsAfter.size());
                AdministratorUploadParams l_resultAdministratorUploadParams =
                    (AdministratorUploadParams)l_lisAdministratorUploadParamsAfter.get(0);
                assertEquals(null, l_resultAdministratorUploadParams.getNote1());
                assertEquals("0", l_resultAdministratorUploadParams.getNote2());
                assertEquals(null , l_resultAdministratorUploadParams.getNote3());
                assertNotNull(l_resultAdministratorUploadParams.getAdministratorUploadId() + "");

                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
                TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0008()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0008()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
        l_docCategoryManagementParams.setBranchCode("381");
        l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        l_docCategoryManagementParams.setInstitutionCode("0D");
        l_docCategoryManagementParams.setDocumentCategory("111");
        l_docCategoryManagementParams.setDocumentCateName("test");

        BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
        l_batoProductManagementParams.setInstitutionCode("0D");
        l_batoProductManagementParams.setBranchCode("381");
        l_batoProductManagementParams.setBatoProductCode("1111111");
        l_batoProductManagementParams.setDocumentDiv("010");
        l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_batoProductManagementParams.setValidFlag("0");

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("0");

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(0, l_lisAdministratorUploadParamsBefore.size());

            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[] {"381,251224,1111111,20071212"};
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            WEB3AdminFPTUploadConfirmResponse l_adminFPTUploadConfirmResponse =
                l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            assertNotNull(l_adminFPTUploadConfirmResponse);
            assertEquals("1", l_adminFPTUploadConfirmResponse.uploadNumber);
            assertNotNull(l_adminFPTUploadConfirmResponse.uploadId);

            List l_lisAdministratorUploadParamsAfter =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadParamsAfter.size());
            AdministratorUploadParams l_resultAdministratorUploadParams =
                (AdministratorUploadParams)l_lisAdministratorUploadParamsAfter.get(0);
            assertEquals(null, l_resultAdministratorUploadParams.getNote1());
            assertEquals(WEB3FPTStatusDivDef.LOGIN, l_resultAdministratorUploadParams.getNote2());
            assertEquals(null , l_resultAdministratorUploadParams.getNote3());
            assertNotNull(l_resultAdministratorUploadParams.getAdministratorUploadId() + "");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
                TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0009()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0009()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
        l_docCategoryManagementParams.setBranchCode("381");
        l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
        l_docCategoryManagementParams.setInstitutionCode("0D");
        l_docCategoryManagementParams.setDocumentCategory("111");
        l_docCategoryManagementParams.setDocumentCateName("test");

        BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
        l_batoProductManagementParams.setInstitutionCode("0D");
        l_batoProductManagementParams.setBranchCode("381");
        l_batoProductManagementParams.setBatoProductCode("1111111");
        l_batoProductManagementParams.setDocumentDiv("010");
        l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_batoProductManagementParams.setValidFlag("0");

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("0");

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("227532");
            l_mainAccountParams.setAccountId(333812275322L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(0, l_lisAdministratorUploadParamsBefore.size());

            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[3];
            l_adminFPTUploadConfirmRequest.uploadFile[0] = "381,251224,1111111,20071212";
            l_adminFPTUploadConfirmRequest.uploadFile[1] = "";
            l_adminFPTUploadConfirmRequest.uploadFile[2] = "381,227532,1111111,20071213";
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            WEB3AdminFPTUploadConfirmResponse l_adminFPTUploadConfirmResponse =
                l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            assertNotNull(l_adminFPTUploadConfirmResponse);
            assertEquals("2", l_adminFPTUploadConfirmResponse.uploadNumber);
            assertNotNull(l_adminFPTUploadConfirmResponse.uploadId);

            List l_lisAdministratorUploadParamsAfter =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadParamsAfter.size());
            AdministratorUploadParams l_resultAdministratorUploadParams =
                (AdministratorUploadParams)l_lisAdministratorUploadParamsAfter.get(0);
            assertEquals(null, l_resultAdministratorUploadParams.getNote1());
            assertEquals(WEB3FPTStatusDivDef.LOGIN, l_resultAdministratorUploadParams.getNote2());
            assertEquals(null , l_resultAdministratorUploadParams.getNote3());
            assertNotNull(l_resultAdministratorUploadParams.getAdministratorUploadId() + "");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
                TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    /**
     * undoUploadFile
     */
    public void testValidateUploadFile_case0010()
    {
        final String STR_METHOD_NAME = " testValidateUploadFile_case0010()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.FPT_HISTORY_INQUIRY);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        AdministratorTypeParams l_administratorTypeParams =
            TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("0");

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(0, l_lisAdministratorUploadParamsBefore.size());

            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[2];
            l_adminFPTUploadConfirmRequest.uploadFile[0] = "381,251224,1111111,20071212";
            l_adminFPTUploadConfirmRequest.uploadFile[1] = "381,251224,1111111,20071212";
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.validateUploadFile(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00517, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisAdministratorUploadParamsAfter =
                    l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
                assertEquals(1, l_lisAdministratorUploadParamsAfter.size());
                AdministratorUploadParams l_resultAdministratorUploadParams =
                    (AdministratorUploadParams)l_lisAdministratorUploadParamsAfter.get(0);
                assertEquals(null, l_resultAdministratorUploadParams.getNote1());
                assertEquals("0", l_resultAdministratorUploadParams.getNote2());
                assertEquals(null , l_resultAdministratorUploadParams.getNote3());
                assertNotNull(l_resultAdministratorUploadParams.getAdministratorUploadId() + "");

                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
                TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    }

    public void testSubmitUploadFile_0001()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00973, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitUploadFile_0002()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitUploadFile_0003()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            l_clendarContext.setBranchCode(null);

            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitUploadFile_0004()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitUploadFile_0005()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(0);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(0);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(0);
            l_loginTypeAttributeParams1.setAttributeName("TRADING_PWD_ENV");
            l_loginTypeAttributeParams1.setAttributeValue("0");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setAdministratorUploadId(11111111L);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            l_request.password = "111";
            l_request.uploadId = "22222222";
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01969, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitUploadFile_0006()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(0);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(0);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(0);
            l_loginTypeAttributeParams1.setAttributeName("TRADING_PWD_ENV");
            l_loginTypeAttributeParams1.setAttributeValue("0");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

//            AdministratorUploadTempParams l_administratorUploadTempParams =
//                TestDBUtility.getAdministratorUploadTempRow();
//            l_administratorUploadTempParams.setAdministratorUploadId(11111111L);
//            l_administratorUploadTempParams.setLineNumber(0);
//            l_administratorUploadTempParams.setCsvLineValue("381, 1234567, 20070606, 1");
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setAdministratorUploadId(11111111L);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            TestDBUtility.commit();
            
            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            l_request.password = "111";
            l_request.uploadId = "11111111";
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitUploadFile_0007()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("123");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("123");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("382");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(0);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(0);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(0);
            l_loginTypeAttributeParams1.setAttributeName("TRADING_PWD_ENV");
            l_loginTypeAttributeParams1.setAttributeValue("0");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams.setLineNumber(0);
            l_administratorUploadTempParams.setCsvLineValue(" ");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue("382,1234567,1111111,20070606");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setAdministratorUploadId(11111111L);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            TestDBUtility.commit();
            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            l_request.password = "111";
            l_request.uploadId = "11111111";
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02995, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    
    public void testSubmitUploadFile_0008()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setAccountId(333812512246L);
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1111111");
            l_docDeliveryManagementParams.setDeliveryDate(
                WEB3DateUtility.getDate("20070606", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDocumentCategory("111");
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(0);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(0);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(0);
            l_loginTypeAttributeParams1.setAttributeName("TRADING_PWD_ENV");
            l_loginTypeAttributeParams1.setAttributeValue("0");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams.setLineNumber(0);
            l_administratorUploadTempParams.setCsvLineValue(" ");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue("381,1234567,1111111,20070606");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setAdministratorUploadId(11111111L);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            TestDBUtility.commit();
            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "0";
            l_request.password = "111";
            l_request.uploadId = "11111111";
            impl.submitUploadFile(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02962, l_ex.getErrorInfo());
            assertEquals("1,381,1234567,1111111,20070606", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitUploadFile_0009()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//            l_docDeliveryManagementParams.setAccountId(333812512246L);
//            l_docDeliveryManagementParams.setDocumentDiv("010");
//            l_docDeliveryManagementParams.setProductCode("0000000");
//            l_docDeliveryManagementParams.setDeliveryDate(
//                WEB3DateUtility.getDate("20070606", "yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(0);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(0);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(0);
            l_loginTypeAttributeParams1.setAttributeName("TRADING_PWD_ENV");
            l_loginTypeAttributeParams1.setAttributeValue("0");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams.setLineNumber(0);
            l_administratorUploadTempParams.setCsvLineValue(" ");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue("381,1234567,1111111,20070606");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setAdministratorUploadId(11111111L);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            TestDBUtility.commit();
            
            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            WEB3AdminFPTUploadCompleteResponse l_response = null;
            l_request.statusDiv = "0";
            l_request.password = "111";
            l_request.uploadId = "11111111";
            l_response = impl.submitUploadFile(l_request);
            
            DocDeliveryManagementPK l_pk = new DocDeliveryManagementPK(
                333812512246L,
                "010",
                "1111111",
                new Timestamp(WEB3DateUtility.getDate("20070606", "yyyyMMdd").getTime()),
                "111");
            DocDeliveryManagementParams l_docDeliveryManagementParams =
                (DocDeliveryManagementParams)l_queryProcessor.doFindByPrimaryKeyQuery(l_pk);
            
            assertEquals("0D", l_docDeliveryManagementParams.getInstitutionCode());
            assertEquals("381", l_docDeliveryManagementParams.getBranchCode());
            assertEquals(0, l_docDeliveryManagementParams.getDeleteFlag().intValue());
            assertEquals("330001", l_docDeliveryManagementParams.getLastUpdater());
            assertTrue(
                Math.abs(GtlUtils.getSystemTimestamp().getTime() - l_docDeliveryManagementParams.getCreatedTimestamp().getTime()) < 1000);
            assertTrue(
                Math.abs(GtlUtils.getSystemTimestamp().getTime() - l_docDeliveryManagementParams.getLastUpdatedTimestamp().getTime()) < 1000);
            
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            ProductTypeEnum l_productTypeEnum = ProductTypeEnum.OTHER;

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ
            l_sbWhere.append(" and upload_end_timestamp is not null "); //アップロード終了日時

            Object[] l_objAdministratorUploadWhere =
                { l_strInstitutionCode, //証券会社コード
                l_strBranchCode, //部店ID
                l_productTypeEnum //銘柄タイプ
            };
            
            List l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
            System.out.println(l_lisRecords.size());
            assertTrue(l_lisRecords.size() == 1);
            
            StringBuffer l_sb = new StringBuffer();
            l_sb.append(" ADMINISTRATOR_UPLOAD_ID = ? ");
            Object[] l_objs = new Object[1];
            l_objs[0] = new Long(11111111L);
            List l_listTemp = l_queryProcessor.doFindAllQuery(
                AdministratorUploadTempParams.TYPE,
                l_sb.toString(),
                null,
                null,
                l_objs);
            assertTrue(l_listTemp.size() == 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitUploadFile_0010()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//            l_docDeliveryManagementParams.setAccountId(333812512246L);
//            l_docDeliveryManagementParams.setDocumentDiv("010");
//            l_docDeliveryManagementParams.setProductCode("0000000");
//            l_docDeliveryManagementParams.setDeliveryDate(
//                WEB3DateUtility.getDate("20070606", "yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(0);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(0);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(0);
            l_loginTypeAttributeParams1.setAttributeName("TRADING_PWD_ENV");
            l_loginTypeAttributeParams1.setAttributeValue("0");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams.setLineNumber(0);
            l_administratorUploadTempParams.setCsvLineValue(" ");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue("381,1234567,1111111,20070606");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setAdministratorUploadId(11111111L);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode(l_clendarContext.getBranchCode());
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            TestDBUtility.commit();
            
            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            l_request.password = "111";
            l_request.uploadId = "11111111";
            impl.submitUploadFile(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02963, l_ex.getErrorInfo());
            assertEquals("1,381,1234567,1111111,20070606", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitUploadFile_0011()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile_0011()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            TestDBUtility.deleteAll(DocDeliveryManagementHistParams.TYPE);
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setAccountId(333812512246L);
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1111111");
            l_docDeliveryManagementParams.setLastUpdater("2222222");
            l_docDeliveryManagementParams.setDeliveryDate(
                WEB3DateUtility.getDate("20070606", "yyyyMMdd"));
            l_docDeliveryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607", "yyyyMMdd"));
            l_docDeliveryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070608", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDocumentCategory("111");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            TestDBUtility.deleteAll(LoginTypeAttributeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(0);
            l_loginTypeParams.setTypeName("loginTypeName");
            TestDBUtility.insertWithDel(l_loginTypeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setAccountTypeId(1);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            
            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams.setTypeId(0);
            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
            l_loginTypeAttributeParams.setAttributeValue("1");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
            LoginTypeAttributeParams l_loginTypeAttributeParams1 = new LoginTypeAttributeParams();
            l_loginTypeAttributeParams1.setTypeId(0);
            l_loginTypeAttributeParams1.setAttributeName("TRADING_PWD_ENV");
            l_loginTypeAttributeParams1.setAttributeValue("0");
            TestDBUtility.insertWithDel(l_loginTypeAttributeParams1);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);

            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0103");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams.setLineNumber(0);
            l_administratorUploadTempParams.setCsvLineValue(" ");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(11111111L);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue("381,1234567,1111111,20070606");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            AdministratorUploadParams l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setUploadEndTimestamp(null);
            l_administratorUploadParams.setAdministratorUploadId(11111111L);
            l_administratorUploadParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            TestDBUtility.deleteAll(l_administratorUploadParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorUploadParams);

            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            l_request.password = "111";
            l_request.uploadId = "11111111";
            impl.submitUploadFile(l_request);
            
            DocDeliveryManagementHistPK l_DocDeliveryManagementHistPK =
                new DocDeliveryManagementHistPK(
                    333812512246L,
                    "010",
                    "1111111",
                    new Timestamp(WEB3DateUtility.getDate("20070606", "yyyyMMdd").getTime()),
                    new Timestamp(WEB3DateUtility.getDate("20070607", "yyyyMMdd").getTime()),
                    "111");
            
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            DocDeliveryManagementHistParams l_docDeliveryManagementHistParams =
                (DocDeliveryManagementHistParams)l_queryProcessor.doFindByPrimaryKeyQuery(l_DocDeliveryManagementHistPK);
            assertEquals("0D", l_docDeliveryManagementHistParams.getInstitutionCode());
            assertEquals("381", l_docDeliveryManagementHistParams.getBranchCode());
            assertEquals(0, l_docDeliveryManagementHistParams.getDeleteFlag().intValue());
            assertEquals("2222222", l_docDeliveryManagementHistParams.getLastUpdater());
            assertEquals(
                (new Timestamp(WEB3DateUtility.getDate("20070608", "yyyyMMdd").getTime()).getTime()),
                l_docDeliveryManagementHistParams.getLastUpdatedTimestamp().getTime());
            assertEquals("330001", l_docDeliveryManagementHistParams.getDeleteUser());
            assertTrue(
                Math.abs(GtlUtils.getSystemTimestamp().getTime() - l_docDeliveryManagementHistParams.getDeleteTimestamp().getTime()) < 1000);
        
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            ProductTypeEnum l_productTypeEnum = ProductTypeEnum.OTHER;

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ
            l_sbWhere.append(" and upload_end_timestamp is not null "); //アップロード終了日時

            Object[] l_objAdministratorUploadWhere =
                { l_strInstitutionCode, //証券会社コード
                l_strBranchCode, //部店ID
                l_productTypeEnum //銘柄タイプ
            };
            
            List l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
            System.out.println(l_lisRecords.size());
            assertTrue(l_lisRecords.size() == 1);
            
            StringBuffer l_sb = new StringBuffer();
            l_sb.append(" ADMINISTRATOR_UPLOAD_ID = ? ");
            Object[] l_objs = new Object[1];
            l_objs[0] = new Long(11111111L);
            List l_listTemp = l_queryProcessor.doFindAllQuery(
                AdministratorUploadTempParams.TYPE,
                l_sb.toString(),
                null,
                null,
                l_objs);
            assertTrue(l_listTemp.size() == 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
//        WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();

        try
        {
            impl.execute(null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(STR_METHOD_NAME);

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.ACC_OPEN);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
            "OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(1001));
        
        WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
        WEB3AdminFPTUploadInputRequest l_request = new WEB3AdminFPTUploadInputRequest();

        try
        {
            impl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(1001L);
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setPermissionLevel("0");

        AdminPermissionParams l_adminPermissionParams =
            TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("0");
        l_adminPermissionParams.setTransactionCategory(
            WEB3TransactionCategoryDef.ACC_OPEN);
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
                "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            WEB3AdminFPTUploadConfirmRequest l_adminFPTUploadConfirmRequest =
                new WEB3AdminFPTUploadConfirmRequest();

            l_adminFPTUploadConfirmRequest.uploadFile = new String[] {""};
            l_adminFPTUploadConfirmRequest.statusDiv = WEB3FPTStatusDivDef.LOGIN;
            l_impl.execute(l_adminFPTUploadConfirmRequest);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);                
            }
        }
    
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = " testExecute_0004()";
        log.entering(STR_METHOD_NAME);

        l_impl = new WEB3AdminFPTUploadServiceImpl();

        AdministratorUploadParams l_dministratorUploadParams =
            TestDBUtility.getAdministratorUploadRow();
        l_dministratorUploadParams.setAdministratorUploadId(101L);
        l_dministratorUploadParams.setInstitutionCode("0D");
        l_dministratorUploadParams.setBranchCode("381");
        l_dministratorUploadParams.setUploadFileId("金商法@交付閲覧アップロード");
        l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
        l_dministratorUploadParams.setUploadKey(0L);
        l_dministratorUploadParams.setUploadCount(99);
        l_dministratorUploadParams.setMessageCode("748");
        l_dministratorUploadParams.setNote2("2");
        l_dministratorUploadParams.setUploadStartTimestamp(
            WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
        l_dministratorUploadParams.setUploadEndTimestamp(
            WEB3DateUtility.getDate("20071211", "yyyyMMdd"));

        AdministratorUploadTempParams l_dministratorUploadTempParams =
            TestDBUtility.getAdministratorUploadTempRow();
        l_dministratorUploadTempParams.setAdministratorUploadId(101L);

        try
        {
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);

            TestDBUtility.insertWithDel(l_dministratorUploadParams);
            TestDBUtility.insertWithDel(l_dministratorUploadTempParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadParamsBefore.size());
            assertEquals(
                101,
                ((AdministratorUploadParams)
                    l_lisAdministratorUploadParamsBefore.get(0)).getAdministratorUploadId());

            List l_lisAdministratorUploadTempParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadTempParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadTempParamsBefore.size());
            assertEquals(
                101,
                ((AdministratorUploadTempParams)
                    l_lisAdministratorUploadTempParamsBefore.get(0)).getAdministratorUploadId());

            WEB3AdminFPTUploadCancelRequest l_adminFPTUploadCancelRequest =
                new WEB3AdminFPTUploadCancelRequest();
            l_adminFPTUploadCancelRequest.uploadId = "101";

            WEB3GenResponse l_response =
                l_impl.execute(l_adminFPTUploadCancelRequest);
            assertNotNull(l_response);

            l_lisAdministratorUploadParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadParams.TYPE);
            assertEquals(1, l_lisAdministratorUploadParamsBefore.size());
            assertEquals(
                101,
                ((AdministratorUploadParams)
                    l_lisAdministratorUploadParamsBefore.get(0)).getAdministratorUploadId());

            l_lisAdministratorUploadTempParamsBefore =
                l_queryProcessor.doFindAllQuery(AdministratorUploadTempParams.TYPE);
            assertEquals(0, l_lisAdministratorUploadTempParamsBefore.size());

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
                TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(STR_METHOD_NAME, l_exE);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }     
        }
    }
    
    public void testExecute_0005()
    {
        final String STR_METHOD_NAME = "testExecute_0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.deleteAll(l_administratorParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(admin);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
            WEB3AdminFPTUploadCompleteRequest l_request = new WEB3AdminFPTUploadCompleteRequest();
            l_request.uploadId = "123";
            l_request.statusDiv = "1";
            impl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0006()
    {
        final String STR_METHOD_NAME = "testExecute_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadServiceImpl impl = new WEB3AdminFPTUploadServiceImpl();
        WEB3AdminAccOpenInspectListRequest l_request = new WEB3AdminAccOpenInspectListRequest();

        try
        {
            impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
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
