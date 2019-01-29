head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DirAdminFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest extends TestBaseForMock
{

    public WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest(String arg0)
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

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest.class);

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

    public class WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1 extends
        WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl
    {

        /**
         * (getアップロード画面)<BR>
         * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ画面表示処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・getアップロード画面」参照。<BR>
         * @@param l_request - (l_request)<BR>
         * リクエストデータ<BR>
         * @@return WEB3AdminSrvRegiOtherOrgIdUploadInputResponse
         * @@throws WEB3BaseException
         * @@roseuid 47B941DD0332
         */
        public WEB3AdminSrvRegiOtherOrgIdUploadInputResponse getUploadScreen(
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getUploadScreen(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)";
            log.entering(STR_METHOD_NAME);

            //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ( )
            WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        /**
         * (validateアップロードファ@イル)<BR>
         * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・validateアップロードファ@イル」参照。<BR>
         * @@param l_request - (l_request)<BR>
         * リクエストデータ<BR>
         * @@return WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse
         * @@throws WEB3BaseException
         * @@roseuid 47B941DD0341
         */
        public WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse validateUploadFile(
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "validateUploadFile(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)";
            log.entering(STR_METHOD_NAME);

            //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ( )
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        /**
         * (submitアップロードファ@イル)<BR>
         * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・submitアップロードファ@イル」参照。<BR>
         * @@param l_request - (l_request)<BR>
         * リクエストデータ<BR>
         * @@return WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse
         * @@throws WEB3BaseException
         * @@roseuid 47B941DD0343
         */
        public WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse submitUploadFile(
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "submitUploadFile(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)";
            log.entering(STR_METHOD_NAME);

            //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ( )
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        /**
         * (undoアップロード)<BR>
         * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止処理を行う。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「(サービス利用)外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・undoアップロード」参照。<BR>
         * @@param l_request - (l_request)<BR>
         * リクエストデータ<BR>
         * @@return WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse
         * @@throws WEB3BaseException
         * @@roseuid 47B941DD0345
         */
        public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse undoUpload(
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "undoUpload(WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest)";
            log.entering(STR_METHOD_NAME);

            WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
    WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl l_impl = new WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl();

    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.execute(null);
            fail();
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

        try
        {
            WEB3AdminSrvRegiCustomerChangeInputRequest l_request =
                new WEB3AdminSrvRegiCustomerChangeInputRequest();
            l_impl.execute(l_request);
            fail();
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

    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1 l_impl =
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1();
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest();
            l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1 l_impl =
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1();
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0005()
    {
        final String STR_METHOD_NAME = "testExecute_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1 l_impl =
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1();
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_impl.execute(l_request);
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

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1 l_impl =
                new WEB3AdminSrvRegiOtherOrgIdUploadServiceImplTest1();
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUndoUpload_0001()
    {
        final String STR_METHOD_NAME = "testUndoUpload_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest();
            l_impl.undoUpload(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testUndoUpload_0002()
    {
        final String STR_METHOD_NAME = "testUndoUpload_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest();
            l_request.uploadId = "123";
            l_request.uploadDiv = "1";
            l_impl.undoUpload(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUploadScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_0001()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            l_impl.getUploadScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testGetUploadScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_0002()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            l_impl.getUploadScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testGetUploadScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_0003()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            assertNull(l_impl.getUploadScreen(l_request).uploadHistoryUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUploadScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_0004()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            l_administratorTypeParams.setDirAdminFlag(
                Integer.parseInt(WEB3DirAdminFlagDef.DIR_ADMINISTRATOR));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("381");
            l_dministratorUploadParams.setUploadFileId("4");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            AdministratorUploadParams l_dministratorUploadParams1 =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams1.setAdministratorUploadId(11);
            l_dministratorUploadParams1.setInstitutionCode("0D");
            l_dministratorUploadParams1.setBranchCode("381");
            l_dministratorUploadParams1.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams1.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams1.setUploadKey(0L);
            l_dministratorUploadParams1.setUploadCount(99);
            l_dministratorUploadParams1.setMessageCode("748");
            l_dministratorUploadParams1.setNote2("2");
            l_dministratorUploadParams1.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams1.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams1);

            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
                l_impl.getUploadScreen(l_request);
            assertEquals(l_response.uploadHistoryUnit.uploadStateDiv, "1");
            assertEquals(l_response.uploadHistoryUnit.uploadNumber, "99");
            assertEquals(
                WEB3DateUtility.toDay(l_response.uploadHistoryUnit.uploadStartDate),
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            assertNull(l_response.uploadHistoryUnit.uploadEndDate);
            assertEquals(l_response.uploadHistoryUnit.srvRegiErrorId, "748");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUploadScreen_0005()
    {
        final String STR_METHOD_NAME = "testGetUploadScreen_0005()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            l_administratorTypeParams.setDirAdminFlag(
                Integer.parseInt(WEB3DirAdminFlagDef.DIR_ADMINISTRATOR));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("381");
            l_dministratorUploadParams.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setUploadEndTimestamp(
                WEB3DateUtility.getDate("20080319", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            AdministratorUploadParams l_dministratorUploadParams1 =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams1.setAdministratorUploadId(11);
            l_dministratorUploadParams1.setInstitutionCode("0D");
            l_dministratorUploadParams1.setBranchCode("381");
            l_dministratorUploadParams1.setUploadFileId("4");
            l_dministratorUploadParams1.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams1.setUploadKey(0L);
            l_dministratorUploadParams1.setUploadCount(99);
            l_dministratorUploadParams1.setMessageCode("748");
            l_dministratorUploadParams1.setNote2("2");
            l_dministratorUploadParams1.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams1.setUploadEndTimestamp(
                WEB3DateUtility.getDate("20080319", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_dministratorUploadParams1);

            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadInputRequest();
            WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response =
                l_impl.getUploadScreen(l_request);
            assertEquals(l_response.uploadHistoryUnit.uploadStateDiv, "2");
            assertEquals(l_response.uploadHistoryUnit.uploadNumber, "99");
            assertEquals(
                WEB3DateUtility.toDay(l_response.uploadHistoryUnit.uploadStartDate),
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            assertEquals(
                WEB3DateUtility.toDay(l_response.uploadHistoryUnit.uploadEndDate),
                WEB3DateUtility.getDate("20080319", "yyyyMMdd"));
            assertEquals(l_response.uploadHistoryUnit.srvRegiErrorId, "748");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0001()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00758, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0002()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0002()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[1];
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testValidateUploadFile_0003()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0003()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[1];
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testValidateUploadFile_0004()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0004()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("123");
            l_dministratorUploadParams.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[]{"111111111111111111,11111111,11111111,1"};
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testValidateUploadFile_0005()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0005()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);

            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[]{"111111111111111111,11111111,11111111"};
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00992, l_ex.getErrorInfo());
            assertEquals("111111111111111111,11111111,11111111", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0006()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0006()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);

            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[]{"111111111111111111,11111111,11111111,2"};
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00890, l_ex.getErrorInfo());
            assertEquals("111111111111111111,11111111", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0007()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0007()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[]{
                "111111111111111111,11111111,11111111,1", "111111111111111111,11111111,11111111,1"};
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03051, l_ex.getErrorInfo());
            assertEquals("111111111111111111,11111111", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0008()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0008()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[]{"111111111111111111,12345678,1,0D,123,111111,20080317,20080318"};
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
            assertEquals("111111111111111111,12345678", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0009()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0009()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[]{"111111111111111111,12345678,1,0D,381,111111,20080317,20080318"};
            l_impl.validateUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
            assertEquals("111111111111111111,12345678", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateUploadFile_0010()
    {
        final String STR_METHOD_NAME = "testValidateUploadFile_0010()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(111111111111111111L);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();
            l_request.serviceDiv = "12";
            l_request.lines = new String[]{"","111111111111111111,12345678,1,0D,381,111111,20080317,20080318"};
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response =
                l_impl.validateUploadFile(l_request);
            assertEquals(l_response.lineCount, "1");
            assertEquals(l_response.uploadDiv, "1");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitUploadFile_0001()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00758, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitUploadFile_0002()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0002()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.uploadId = "123";
            l_request.uploadDiv = "0";

            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testsubmitUploadFile_0003()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0003()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.uploadId = "123";
            l_request.uploadDiv = "0";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testsubmitUploadFile_0004()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0004()";
        log.entering(STR_METHOD_NAME);

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

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("123");
            l_dministratorUploadParams.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.uploadId = "123";
            l_request.uploadDiv = "0";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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

    public void testsubmitUploadFile_0005()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0005()";
        log.entering(STR_METHOD_NAME);

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

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "checkPassword",
//                new Class[] {String.class},
//                Boolean.FALSE);
            
           
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("123");
            l_dministratorUploadParams.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setAdministratorUploadId(123);
            l_dministratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            WEB3Administrator l_tAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("123",false);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_tAdministrator);
            
            
            
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.uploadId = "123";
            l_request.uploadDiv = "0";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitUploadFile_0006()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0006()";
        log.entering(STR_METHOD_NAME);

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

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "checkPassword",
//                new Class[] {String.class},
//                Boolean.TRUE);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("1111");
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("17");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            l_administratorTypeParams.setDirAdminFlag(Integer.parseInt(WEB3DirAdminFlagDef.DIR_ADMINISTRATOR));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("123");
            l_dministratorUploadParams.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setAdministratorUploadId(123);
            l_dministratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(123);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue("111111111111111111,11111111,11111111,1");
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams1);

            l_administratorUploadTempParams1.setAdministratorUploadId(123);
            l_administratorUploadTempParams1.setLineNumber(2);
            l_administratorUploadTempParams1.setCsvLineValue("111111111111111111,11111111,11111111,1");
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams1);
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            WEB3Administrator l_tAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateTradingPassword("123",true);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_tAdministrator);
            
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.uploadId = "123";
            l_request.uploadDiv = "0";
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl());
            l_impl.submitUploadFile(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitUploadFile_0007()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0007()";
        log.entering(STR_METHOD_NAME);

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

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "checkPassword",
//                new Class[] {String.class},
//                Boolean.TRUE);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("1111");
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("123");
            l_dministratorUploadParams.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setAdministratorUploadId(123);
            l_dministratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(123);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue(
                "111111111111111111,12345678,1,0D,123,111111,20080317,20080318");
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams1);

            l_administratorUploadTempParams1.setAdministratorUploadId(123);
            l_administratorUploadTempParams1.setLineNumber(2);
            l_administratorUploadTempParams1.setCsvLineValue(
                "111111111111111111,12345678,1,0D,123,111111,20080317,20080318");
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams1);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(111111111111111111L);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3Administrator l_tAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateTradingPassword("123",true);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_tAdministrator);
            
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.uploadId = "123";
            l_request.uploadDiv = "1";
            l_impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
            assertEquals("111111111111111111,12345678", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitUploadFile_0008()
    {
        final String STR_METHOD_NAME = "testsubmitUploadFile_0008()";
        log.entering(STR_METHOD_NAME);

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

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "checkPassword",
//                new Class[] {String.class},
//                Boolean.TRUE);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderCode("1111");
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setTransactionCategory(
                WEB3TransactionCategoryDef.SRVREGI_OTHERORG);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("17");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("0");
            l_administratorTypeParams.setDirAdminFlag(Integer.parseInt(WEB3DirAdminFlagDef.DIR_ADMINISTRATOR));
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_dministratorUploadParams =
                TestDBUtility.getAdministratorUploadRow();
            l_dministratorUploadParams.setInstitutionCode("0D");
            l_dministratorUploadParams.setBranchCode("123");
            l_dministratorUploadParams.setUploadFileId("外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ");
            l_dministratorUploadParams.setProductType(ProductTypeEnum.OTHER);
            l_dministratorUploadParams.setUploadKey(0L);
            l_dministratorUploadParams.setUploadCount(99);
            l_dministratorUploadParams.setMessageCode("748");
            l_dministratorUploadParams.setNote2("2");
            l_dministratorUploadParams.setUploadStartTimestamp(
                WEB3DateUtility.getDate("20080318", "yyyyMMdd"));
            l_dministratorUploadParams.setAdministratorUploadId(123);
            l_dministratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDel(l_dministratorUploadParams);

            TestDBUtility.deleteAllAndCommit(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams1 =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams1.setAdministratorUploadId(123);
            l_administratorUploadTempParams1.setLineNumber(1);
            l_administratorUploadTempParams1.setCsvLineValue(
                "111111111111111111,12345678,1,0D,381,111111,20080317,20080318");
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams1);

            l_administratorUploadTempParams1.setAdministratorUploadId(123);
            l_administratorUploadTempParams1.setLineNumber(2);
            l_administratorUploadTempParams1.setCsvLineValue(
                "111111111111111111,12345678,1,0D,381,111111,20080317,20080318");
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams1);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(111111111111111111L);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("17");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            WEB3Administrator l_tAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockValidateTradingPassword("123",true);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_tAdministrator);
            

            
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request =
                new WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest();
            l_request.serviceDiv = "12";
            l_request.uploadId = "123";
            l_request.uploadDiv = "1";
            Services.unregisterService(WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class);
            Services.registerService(WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
                new WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl());
            l_impl.submitUploadFile(l_request);
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
