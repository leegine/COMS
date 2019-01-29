head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.04.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAPMngForcedStartServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者下り処理強制起動サービスImpl(WEB3AdminDirSecAPMngForcedStartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/07/23  劉剣 (中訊) 新規作成モデル 132
Revesion History : 2008/07/30  劉剣 (中訊) モデル 136
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.data.ApManagementParams;
import webbroker3.dirsec.data.ApManagementRow;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartSortKey;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAPMngForcedStartServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecAPMngForcedStartServiceImplTest.class);

    private WEB3AdminDirSecAPMngForcedStartServiceImpl l_serviceImpl = null;

    public WEB3AdminDirSecAPMngForcedStartServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3AdminDirSecAPMngForcedStartServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * パラメータタイプ不正。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = null;
            this.l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * リクエストデータ instanceof WEB3AdminDirSecAPMngListRequest
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartServiceImpl l_impl =
                new WEB3AdminDirSecAPMngForcedStartServiceImplForTest();

            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();

            WEB3GenResponse l_response = l_impl.execute(l_request);

            assertEquals(WEB3AdminDirSecAPMngListResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * リクエストデータ instanceof WEB3AdminDirSecAPMngForcedStartInpRequest
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartServiceImpl l_impl =
                new WEB3AdminDirSecAPMngForcedStartServiceImplForTest();

            WEB3AdminDirSecAPMngForcedStartInpRequest l_request = new WEB3AdminDirSecAPMngForcedStartInpRequest();

            WEB3GenResponse l_response = l_impl.execute(l_request);

            assertEquals(WEB3AdminDirSecAPMngForcedStartInpResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * リクエストデータ instanceof WEB3AdminDirSecAPMngForcedStartCnfRequest
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartServiceImpl l_impl =
                new WEB3AdminDirSecAPMngForcedStartServiceImplForTest();

            WEB3AdminDirSecAPMngForcedStartCnfRequest l_request = new WEB3AdminDirSecAPMngForcedStartCnfRequest();

            WEB3GenResponse l_response = l_impl.execute(l_request);

            assertEquals(WEB3AdminDirSecAPMngForcedStartCnfResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * リクエストデータ instanceof WEB3AdminDirSecAPMngForcedStartCmpRequest
     */
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartServiceImpl l_impl =
                new WEB3AdminDirSecAPMngForcedStartServiceImplForTest();

            WEB3AdminDirSecAPMngForcedStartCmpRequest l_request = new WEB3AdminDirSecAPMngForcedStartCmpRequest();

            WEB3GenResponse l_response = l_impl.execute(l_request);

            assertEquals(WEB3AdminDirSecAPMngForcedStartCmpResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * パラメータタイプ不正。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testExecute_C0006()
    {
        final String STR_METHOD_NAME = "testExecute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartServiceImpl l_impl =
                new WEB3AdminDirSecAPMngForcedStartServiceImplForTest();

            WEB3AdminDirSecAioOrderUnitTableSearchResultRequest l_request =
                new WEB3AdminDirSecAioOrderUnitTableSearchResultRequest();

            l_impl.execute(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
     * ?出：WEB3ErrorCatalog.BUSINESS_ERROR_00857
     */
    public void testGetAPMngList_C0001()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(0);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            l_request.pageSize = "1";
            l_request.pageIndex = "1";
            l_request.sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_request.sortKeys[0].keyItem = "requestCode";
            l_request.sortKeys[0].ascDesc = "A";

            this.l_serviceImpl.getAPMngList(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * レコードが取得できない場合、例外をthrowする。
     * ?出：WEB3ErrorCatalog.BUSINESS_ERROR_00398
     */
    public void testGetAPMngList_C0002()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);

            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            l_request.pageSize = "1";
            l_request.pageIndex = "1";
            l_request.sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_request.sortKeys[0].keyItem = "requestCode";
            l_request.sortKeys[0].ascDesc = "A";

            this.l_serviceImpl.getAPMngList(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testGetAPMngList_C0003()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams l_apManagementParams = TestDBUtility.getApManagementRow();
            TestDBUtility.insertWithDel(l_apManagementParams);

            ApManagementParams l_apManagementParams1 = TestDBUtility.getApManagementRow();
            l_apManagementParams1.setPtype("2");
            l_apManagementParams1.setRequestCode("2");
            TestDBUtility.insertWithDel(l_apManagementParams1);

            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            l_request.pageSize = "10";
            l_request.pageIndex = "1";
            l_request.sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_request.sortKeys[0].keyItem = "requestCode";
            l_request.sortKeys[0].ascDesc = "A";

            WEB3AdminDirSecAPMngListResponse l_response = this.l_serviceImpl.getAPMngList(l_request);

            assertEquals("1", l_response.totalPages);
            assertEquals("2", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.apMngInfoList[0].requestCode);
            assertEquals("2", l_response.apMngInfoList[1].requestCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * ページ内表示行数の入力が不正です。
     * ?出：WEB3ErrorCatalog.BUSINESS_ERROR_00091
     */
    public void testGetAPMngList_C0004()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();

            this.l_serviceImpl.getAPMngList(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 管理者権限チェックエラー。
     * ?出：WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testGetAPMngList_C0005()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            l_request.pageSize = "10";
            l_request.pageIndex = "1";
            l_request.sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_request.sortKeys[0].keyItem = "requestCode";
            l_request.sortKeys[0].ascDesc = "A";

            this.l_serviceImpl.getAPMngList(l_request);

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

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validate()
    public void testGetAPMngForcedStartInp_C0001()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartInpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_cmpRequest.pType= null;
            l_serviceImpl.getAPMngForcedStartInp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03105, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    // validate権限(機@能カテゴリコード : String, is更新 : boolean)
    public void testGetAPMngForcedStartInp_C0002()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartInpRequest l_inpRequest =
                new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_inpRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(adminPermissionParams);

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.getAPMngForcedStartInp(l_inpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
    public void testGetAPMngForcedStartInp_C0003()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartInpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_cmpRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(2);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(false));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.getAPMngForcedStartInp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validateAP下り処理管理()失敗
    //レコードが取得できない場合、例外をthrowする。
    //BUSINESS_ERROR_00398
    public void testGetAPMngForcedStartInp_C0004()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartInpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_cmpRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams apManagementParams = TestDBUtility.getApManagementRow();
            TestDBUtility.insertWithDel(apManagementParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(true));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.getAPMngForcedStartInp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //normal case
    public void testGetAPMngForcedStartInp_C0005()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartInpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_cmpRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams apManagementParams = TestDBUtility.getApManagementRow();
            apManagementParams.setPtype("1111");
            TestDBUtility.insertWithDel(apManagementParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(true));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.getAPMngForcedStartInp(l_cmpRequest);
            assertTrue(true);
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validate()
    public void testValidateAPMngForcedStartCnf_C0001()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_cnfRequest =
                new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_cnfRequest.pType= null;
            l_serviceImpl.validateAPMngForcedStartCnf(l_cnfRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03105, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validate権限(機@能カテゴリコード : String, is更新 : boolean)
    public void testValidateAPMngForcedStartCnf_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_cnfRequest =
                new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_cnfRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(adminPermissionParams);

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.validateAPMngForcedStartCnf(l_cnfRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
    public void testValidateAPMngForcedStartCnf_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_cnfRequest =
                new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_cnfRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(2);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(false));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.validateAPMngForcedStartCnf(l_cnfRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validateAP下り処理管理()失敗
    //レコードが取得できない場合、例外をthrowする。
    //BUSINESS_ERROR_00398
    public void testValidateAPMngForcedStartCnf_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_cnfRequest =
                new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_cnfRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams apManagementParams = TestDBUtility.getApManagementRow();
            TestDBUtility.insertWithDel(apManagementParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(true));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.validateAPMngForcedStartCnf(l_cnfRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //normal case
    public void testValidateAPMngForcedStartCnf_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_cnfRequest =
                new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_cnfRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams apManagementParams = TestDBUtility.getApManagementRow();
            apManagementParams.setPtype("1111");
            TestDBUtility.insertWithDel(apManagementParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(true));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.validateAPMngForcedStartCnf(l_cnfRequest);
            assertTrue(true);
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validate()
    public void testSubmitAPMngForcedStartCmp_C0001()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_cmpRequest.pType= null;
            l_serviceImpl.submitAPMngForcedStartCmp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03105, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validate権限(機@能カテゴリコード : String, is更新 : boolean)
    public void testSubmitAPMngForcedStartCmp_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_cmpRequest.pType= "1111";
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(adminPermissionParams);
            
            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.submitAPMngForcedStartCmp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //DIR管理者以外の場合（isDIR管理者()==false）例外をスローする。
    public void testSubmitAPMngForcedStartCmp_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_cmpRequest.pType= "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(2);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(false));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            l_serviceImpl.submitAPMngForcedStartCmp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validate取引パスワード(パスワード : String)
    //パスワード不存在
    public void testSubmitAPMngForcedStartCmp_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_cmpRequest.pType= "1111";
            l_cmpRequest.password = "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams apManagementParams = TestDBUtility.getApManagementRow();
            TestDBUtility.insertWithDel(apManagementParams);
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(true));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            Map map = new HashMap();
            map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginTypeAttributes",
                    new Class[] {long.class},
                    map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "checkPassword",
                    new Class[] {String.class},
                    new Boolean( false));

            l_serviceImpl.submitAPMngForcedStartCmp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //validateAP下り処理管理()失敗
    //レコードが取得できない場合、例外をthrowする。
    //BUSINESS_ERROR_00398
    public void testSubmitAPMngForcedStartCmp_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_cmpRequest.pType= "1111";
            l_cmpRequest.password = "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams apManagementParams = TestDBUtility.getApManagementRow();
            TestDBUtility.insertWithDel(apManagementParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(true));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            Map map = new HashMap();
            map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginTypeAttributes",
                    new Class[] {long.class},
                    map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "checkPassword",
                    new Class[] {String.class},
                    new Boolean(true));

            l_serviceImpl.submitAPMngForcedStartCmp(l_cmpRequest);
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //normal case
    public void testSubmitAPMngForcedStartCmp_C0006()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_cmpRequest =
                new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_cmpRequest.pType= "1111";
            l_cmpRequest.password = "1111";

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(administratorParams);

            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            administratorTypeParams.setInstitutionCode("0D");
            administratorTypeParams.setPermissionLevel("331");
            administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(administratorTypeParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            adminPermissionParams.setPermissionLevel("331");
            adminPermissionParams.setTransactionCategory("Z0101");
            adminPermissionParams.setInstitutionCode("0D");
            adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(adminPermissionParams);

            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams apManagementParams = TestDBUtility.getApManagementRow();
            apManagementParams.setPtype("1111");
            TestDBUtility.insertWithDel(apManagementParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3Administrator",
                    "isDirAdministrator",
                    new Class[] {},
                    new Boolean(true));

            LoginInfoForTest l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]{},
                    l_loginInfo);

            Map map = new HashMap();
            map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginTypeAttributes",
                    new Class[] {long.class},
                    map);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "checkPassword",
                    new Class[] {String.class},
                    new Boolean(true));

            l_serviceImpl.submitAPMngForcedStartCmp(l_cmpRequest);
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * case合并
     */
    public void testCreateAPMngManageSortCondition_C0001()
    {
        final String STR_METHOD_NAME = "testCreateAPMngManageSortCondition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartSortKey[] l_sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[4];
            l_sortKeys[0] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_sortKeys[0].keyItem = "requestCode";
            l_sortKeys[0].ascDesc = "A";
            l_sortKeys[1] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_sortKeys[1].keyItem = "pType";
            l_sortKeys[1].ascDesc = "A";
            l_sortKeys[2] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_sortKeys[2].keyItem = "apName";
            l_sortKeys[2].ascDesc = "D";
            l_sortKeys[3] = new WEB3AdminDirSecAPMngForcedStartSortKey();
            l_sortKeys[3].keyItem = "abc";
            l_sortKeys[3].ascDesc = "D";

            Method l_method = WEB3AdminDirSecAPMngForcedStartServiceImpl.class.getDeclaredMethod(
                "createAPMngManageSortCondition",
                new Class[]{WEB3AdminDirSecAPMngForcedStartSortKey[].class});

            l_method.setAccessible(true);

            Object[] l_obj = {l_sortKeys};

            String l_strSortString = (String)l_method.invoke(l_serviceImpl, l_obj);

            assertEquals("request_code asc,ptype asc,ap_name desc", l_strSortString);
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * レコードが取得できない場合、例外をthrowする。
     * ?出:WEB3ErrorCatalog.BUSINESS_ERROR_00398
     */
    public void testValidateAPMngManage_C0001()
    {
        final String STR_METHOD_NAME = "testValidateAPMngManage_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Method l_method = WEB3AdminDirSecAPMngForcedStartServiceImpl.class.getDeclaredMethod(
                "validateAPMngManage",
                new Class[]{String.class});

            l_method.setAccessible(true);

            Object[] l_obj = {"2"};

            l_method.invoke(l_serviceImpl, l_obj);

            fail();
        } 
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);

            WEB3BusinessLayerException l_targetException =
                (WEB3BusinessLayerException)l_ex.getTargetException();

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_targetException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testValidateAPMngManage_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAPMngManage_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ApManagementRow.TYPE);
            ApManagementParams l_apManagementParams = TestDBUtility.getApManagementRow();
            l_apManagementParams.setApName("abc");
            TestDBUtility.insertWithDel(l_apManagementParams);

            Method l_method = WEB3AdminDirSecAPMngForcedStartServiceImpl.class.getDeclaredMethod(
                "validateAPMngManage",
                new Class[]{String.class});

            l_method.setAccessible(true);

            Object[] l_obj = {"1"};

            l_method.invoke(l_serviceImpl, l_obj);

            assertTrue(true);
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3AdminDirSecAPMngForcedStartServiceImplForTest extends WEB3AdminDirSecAPMngForcedStartServiceImpl
    {
        protected WEB3AdminDirSecAPMngListResponse getAPMngList(WEB3AdminDirSecAPMngListRequest l_request)
        {
            return new WEB3AdminDirSecAPMngListResponse();
        }
        protected WEB3AdminDirSecAPMngForcedStartInpResponse getAPMngForcedStartInp(
                WEB3AdminDirSecAPMngForcedStartInpRequest l_request)
        {
            return new WEB3AdminDirSecAPMngForcedStartInpResponse();
        }
        protected WEB3AdminDirSecAPMngForcedStartCnfResponse validateAPMngForcedStartCnf(
                WEB3AdminDirSecAPMngForcedStartCnfRequest l_request)
        {
            return new WEB3AdminDirSecAPMngForcedStartCnfResponse();
        }
        protected WEB3AdminDirSecAPMngForcedStartCmpResponse submitAPMngForcedStartCmp(
                WEB3AdminDirSecAPMngForcedStartCmpRequest l_request)
        {
            return new WEB3AdminDirSecAPMngForcedStartCmpResponse();
        }
    }

    private class LoginInfoForTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 33381330003L;
        }

        public long getLoginTypeId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getUsername()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getInitialPassword()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isDisabled()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Set getReachableAccountIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLoginIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLogins()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Map getAttributes()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean hasFailedLogin()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public int getFailureCount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        
    }
        

}
@
