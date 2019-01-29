head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMLoginRejectIPManagementServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminTMLoginRejectIPManagementServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/25 安陽(中訊) 新規作成
*/
package webbroker3.trademanagement.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.LoginRejectIpParams;
import webbroker3.gentrade.data.LoginRejectIpRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlReferenceUnit;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlSortKey;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCommonRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

public class WEB3AdminTMLoginRejectIPManagementServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginRejectIPManagementServiceImplTest.class);

    WEB3AdminTMLoginRejectIPManagementServiceImpl l_serviceImpl = null;

    public WEB3AdminTMLoginRejectIPManagementServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_serviceImpl = new WEB3AdminTMLoginRejectIPManagementServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        l_serviceImpl = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.execute(WEB3GenRequest)'
     */
    
    //(execute) 
    //パラメータ値不正
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = null;
            
            l_serviceImpl.execute(l_request);
            
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
    
    //(execute) 
    //○管理者・ログイン拒否IP一覧リクエストの場合
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlListRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("getListScreen", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(execute) 
    //○管理者・ログイン拒否IP登録リクエストの場合
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlRegistInputRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("getRegistScreen", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(execute) 
    //○管理者・ログイン拒否IP登録確認リクエストの場合
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("validateRegist", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(execute) 
    //○管理者・ログイン拒否IP登録完了リクエストの場合
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("submitRegist", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    } 
    
    //(execute) 
    //○管理者・ログイン拒否IP登録情報変更リクエストの場合
    public void testExecute_C0006()
    {
        final String STR_METHOD_NAME = "testExecute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("getChangedScreen", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(execute) 
    //○管理者・ログイン拒否IP登録情報変更確認リクエストの場合
    public void testExecute_C0007()
    {
        final String STR_METHOD_NAME = "testExecute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("validateChange", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    } 
    
    //(execute) 
    //○管理者・ログイン拒否IP登録情報変更完了リクエストの場合
    public void testExecute_C0008()
    {
        final String STR_METHOD_NAME = "testExecute_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("submitChange", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(execute) 
    //○管理者・ログイン拒否IP登録情報削除確認リクエストの場合
    public void testExecute_C0009()
    {
        final String STR_METHOD_NAME = "testExecute_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("validateDelete", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(execute) 
    //○管理者・ログイン拒否IP登録情報削除完了リクエストの場合
    public void testExecute_C0010()
    {
        final String STR_METHOD_NAME = "testExecute_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            
            WEB3AdminTMLoginRejectIPManagementServiceImplForTest l_serviceImplForTest =
                new WEB3AdminTMLoginRejectIPManagementServiceImplForTest();
            
            WEB3GenResponse l_response = l_serviceImplForTest.execute(l_request);
            
            assertEquals("submitDelete", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(execute) 
    //パラメータタイプ不正。
    public void testExecute_C0011()
    {
        final String STR_METHOD_NAME = "testExecute_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTraderAdminIPControlUpdateCommonRequest();
            
            l_serviceImpl.execute(l_request);
            
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
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.getListScreen(WEB3AdminTraderAdminIPControlListRequest)'
     */
    //(get一覧画面)
    //validate( )
    public void testGetListScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            
            l_request.pageSize = null;
            
            l_serviceImpl.getListScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get一覧画面)
    //ログイン拒否IPテーブル検索結果が0件の場合
    public void testGetListScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C1301", false, true);
            
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            
            l_request.pageSize = "2";
            l_request.pageIndex = "2";
            l_request.sortKeys = new WEB3AdminTraderAdminIPControlSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminTraderAdminIPControlSortKey();
            l_request.sortKeys[0].keyItem = "ipAddress";
            l_request.sortKeys[0].ascDesc = "A";
            
            l_serviceImpl.getListScreen(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //
    public void testGetListScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C1301", false, true);
            
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            
            l_request.pageSize = "2";
            l_request.pageIndex = "1";
            l_request.sortKeys = new WEB3AdminTraderAdminIPControlSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminTraderAdminIPControlSortKey();
            l_request.sortKeys[0].keyItem = "ipAddress";
            l_request.sortKeys[0].ascDesc = "A";
            
            WEB3AdminTraderAdminIPControlListResponse l_response = l_serviceImpl.getListScreen(l_request);

            //(*)レスポンスデータに以下の通りプロパティをセットする。
            //　@レスポンスデータ.表示ページ番号    ＝　@pageNumber()の戻り値
            assertEquals("1", l_response.pageIndex);
            //　@レスポンスデータ.総ページ数      ＝　@totalPages()の戻り値
            assertEquals("1", l_response.totalPages);
            //　@レスポンスデータ.総レコード数     ＝　@totalSize()の戻り値
            assertEquals("1", l_response.totalRecords);
            //　@レスポンスデータ.ログイン拒否IP一覧 ＝　@ログイン拒否IP情報の配列
            assertEquals("1", String.valueOf(l_response.ipControlList.length));
            WEB3AdminTraderAdminIPControlReferenceUnit l_unit = l_response.ipControlList[0];
            assertEquals("5001", l_unit.denyLoginID);
            assertEquals("403", l_unit.updaterCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //ログイン拒否IPテーブル検索結果が3件の場合
    public void testGetListScreen_C0004()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            l_loginRejectIpParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            LoginRejectIpParams l_loginRejectIpParams1 = this.getLoginRejectIpRow();
            l_loginRejectIpParams1.setLoginRejectId(5002L);
            l_loginRejectIpParams1.setUpdatedDiv("0");
            l_loginRejectIpParams1.setIpAddress("10.0.0.1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams1);
            LoginRejectIpParams l_loginRejectIpParams2 = this.getLoginRejectIpRow();
            l_loginRejectIpParams2.setLoginRejectId(5003L);
            l_loginRejectIpParams2.setIpAddress("10.0.0.2");
            l_loginRejectIpParams2.setAppliEndTimestamp(
                    new Timestamp(WEB3DateUtility.getDate("20080817221100", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams2);
            
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C1301", false, true);
            
            WEB3AdminTraderAdminIPControlListRequest l_request =
                new WEB3AdminTraderAdminIPControlListRequest();
            
            l_request.pageSize = "4";
            l_request.pageIndex = "1";
            l_request.sortKeys = new WEB3AdminTraderAdminIPControlSortKey[1];
            l_request.sortKeys[0] = new WEB3AdminTraderAdminIPControlSortKey();
            l_request.sortKeys[0].keyItem = "ipAddress";
            l_request.sortKeys[0].ascDesc = "A";
            
            WEB3AdminTraderAdminIPControlListResponse l_response = l_serviceImpl.getListScreen(l_request);

            //(*)レスポンスデータに以下の通りプロパティをセットする。
            //　@レスポンスデータ.表示ページ番号    ＝　@pageNumber()の戻り値
            assertEquals("1", l_response.pageIndex);
            //　@レスポンスデータ.総ページ数      ＝　@totalPages()の戻り値
            assertEquals("1", l_response.totalPages);
            //　@レスポンスデータ.総レコード数     ＝　@totalSize()の戻り値
            assertEquals("3", l_response.totalRecords);
            //　@レスポンスデータ.ログイン拒否IP一覧 ＝　@ログイン拒否IP情報の配列
            assertEquals("3", String.valueOf(l_response.ipControlList.length));
            WEB3AdminTraderAdminIPControlReferenceUnit l_unit = l_response.ipControlList[0];
            assertEquals("5001", l_unit.denyLoginID);
            assertEquals("403", l_unit.updaterCode);
            WEB3AdminTraderAdminIPControlReferenceUnit l_unit1 = l_response.ipControlList[1];
            assertEquals("5002", l_unit1.denyLoginID);
            assertNull(l_unit1.updaterCode);
            WEB3AdminTraderAdminIPControlReferenceUnit l_unit2 = l_response.ipControlList[2];
            assertEquals("5003", l_unit2.denyLoginID);
            assertEquals("403", l_unit.updaterCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.getRegistScreen(WEB3AdminTraderAdminIPControlRegistInputRequest)'
     */
    //get登録画面
    public void testGetRegistScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetRegistScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdminTraderAdminIPControlRegistInputRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistInputRequest();
            
            l_serviceImpl.getRegistScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetRegistScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetRegistScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlRegistInputRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistInputRequest();
            
            l_serviceImpl.getRegistScreen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.validateRegist(WEB3AdminTraderAdminIPControlRegistConfirmRequest)'
     */
    public void testValidateRegist_C0001()
    {
        final String STR_METHOD_NAME = "testValidateRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            
            l_serviceImpl.validateRegist(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03122, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateRegist_C0002()
    {
        final String STR_METHOD_NAME = "testValidateRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            
            l_request.ipAddress = "10.0.0.0";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");

            l_serviceImpl.validateRegist(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateRegist_C0003()
    {
        final String STR_METHOD_NAME = "testValidateRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistConfirmRequest();
            
            l_request.ipAddress = "10.0.0.0";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.validateRegist(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.submitRegist(WEB3AdminTraderAdminIPControlRegistCompleteRequest)'
     */
    public void testSubmitRegist_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            
            l_serviceImpl.submitRegist(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03122, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitRegist_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            
            l_request.ipAddress = "10.0.0.0";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.submitRegist(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //取引パスワードが正しいかのチェックを行う。
    public void testSubmitRegist_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            l_request.ipAddress = "10.0.0.0";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.submitRegist(l_request);
            
        }
        catch (WEB3BaseRuntimeException l_ex)
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
    
    //正常
    public void testSubmitRegist_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitRegist_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateTradingPassword("132", true);
      
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            l_request.ipAddress = "10.0.0.0";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.submitRegist(l_request);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lis = l_processor.doFindAllQuery(LoginRejectIpParams.TYPE);
            
            LoginRejectIpRow l_params= (LoginRejectIpRow)l_lis.get(0);
            
            assertEquals("0D", l_params.getInstitutionCode());
            assertEquals("2", l_params.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.getChangedScreen(WEB3AdminTraderAdminIPControlUpdateInputRequest)'
     */
    public void testGetChangedScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetChangedScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            
            l_serviceImpl.getChangedScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03116, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangedScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetChangedScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            
            l_request.denyLoginID = "5001";
            
            l_serviceImpl.getChangedScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetChangedScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetChangedScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateTradingPassword("132", true);
      
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateInputRequest();
            l_request.denyLoginID = "5001";
            
            WEB3AdminTraderAdminIPControlUpdateInputResponse l_response =
                l_serviceImpl.getChangedScreen(l_request);
            
            assertEquals("5001",l_response.beforeUpdateInfo.denyLoginID);
            assertEquals("0",l_response.beforeUpdateInfo.status);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.validateChange(WEB3AdminTraderAdminIPControlUpdateConfirmRequest)'
     */
    public void testValidateChange_C0001()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            
            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03116, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateChange_C0002()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            l_request.denyLoginID = "5001";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //validate変更有無
    public void testValidateChange_C0003()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateTradingPassword("132", true);
      
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            l_request.denyLoginID = "5001";
            l_request.status = "0";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03132, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //
    public void testValidateChange_C0004()
    {
        final String STR_METHOD_NAME = "testValidateChange_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateTradingPassword("132", true);
      
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateConfirmRequest();
            l_request.denyLoginID = "5001";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response =
                l_serviceImpl.validateChange(l_request);
            
            assertEquals("5001",l_response.beforeUpdateInfo.denyLoginID);
            assertEquals("0",l_response.beforeUpdateInfo.status);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.submitChange(WEB3AdminTraderAdminIPControlUpdateCompleteRequest)'
     */
    public void testSubmitChange_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            
            l_serviceImpl.submitChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03116, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitChange_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            l_request.denyLoginID = "5001";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.submitChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //validate取引パスワード
    public void testSubmitChange_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            l_request.denyLoginID = "5001";
            l_request.status = "0";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.submitChange(l_request);
        }
        catch (WEB3BaseRuntimeException l_ex)
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
    
    //validate変更有無
    public void testSubmitChange_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateTradingPassword("132", true);
      
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            l_request.denyLoginID = "5001";
            l_request.status = "0";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            l_serviceImpl.submitChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03132, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitChange_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            l_loginRejectIpParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateTradingPassword("132", true);
      
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlUpdateCompleteRequest();
            l_request.denyLoginID = "5001";
            l_request.status = "2";
            l_request.startDate = WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss");
            l_request.endDate = WEB3DateUtility.getDate("21090917221100", "yyyyMMddHHmmss");
            
            WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response =
                l_serviceImpl.submitChange(l_request);
            
            assertEquals("5001",l_response.beforeUpdateInfo.denyLoginID);
            assertEquals("0",l_response.beforeUpdateInfo.status);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lis = l_processor.doFindAllQuery(LoginRejectIpParams.TYPE);
            
            LoginRejectIpRow l_params= (LoginRejectIpRow)l_lis.get(0);
            
            assertEquals("0D", l_params.getInstitutionCode());
            assertEquals("2", l_params.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.validateDelete(WEB3AdminTraderAdminIPControlDeleteConfirmRequest)'
     */
    public void testValidateDelete_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            
            l_serviceImpl.validateDelete(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03116, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateDelete_C0002()
    {
        final String STR_METHOD_NAME = "testValidateDelete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            l_request.denyLoginID = "5001";
            
            l_serviceImpl.validateDelete(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateDelete_C0003()
    {
        final String STR_METHOD_NAME = "testValidateDelete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            l_loginRejectIpParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteConfirmRequest();
            l_request.denyLoginID = "5001";
            
            WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response =
                l_serviceImpl.validateDelete(l_request);
            
            assertEquals("5001",l_response.beforeUpdateInfo.denyLoginID);
            assertEquals("0",l_response.beforeUpdateInfo.status);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl.submitDelete(WEB3AdminTraderAdminIPControlDeleteCompleteRequest)'
     */
    public void testSubmitDelete_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            
            l_serviceImpl.submitDelete(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03116, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitDelete_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            l_request.denyLoginID = "5001";
            
            l_serviceImpl.submitDelete(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitDelete_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
      
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            l_request.denyLoginID = "5001";
            
            l_serviceImpl.submitDelete(l_request);
        }
        catch (WEB3BaseRuntimeException l_ex)
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
    
    public void testSubmitDelete_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpParams.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            l_loginRejectIpParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setPermissionLevel("123");
            l_AdminPermissionParams.setTransactionCategory("C1301");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateTradingPassword("132", true);
      
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlDeleteCompleteRequest();
            l_request.denyLoginID = "5001";
            
            WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response =
                l_serviceImpl.submitDelete(l_request);
            
            assertEquals("5001",l_response.beforeUpdateInfo.denyLoginID);
            assertEquals("0",l_response.beforeUpdateInfo.status);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lis = l_processor.doFindAllQuery(LoginRejectIpParams.TYPE);
            
            LoginRejectIpRow l_params= (LoginRejectIpRow)l_lis.get(0);
            
            assertEquals("0D", l_params.getInstitutionCode());
            assertEquals("1", l_params.getStatus());
            assertEquals("1", l_params.getUpdatedDiv());
            assertEquals("123456789", l_params.getLastUpdater());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(create検索ソート条件)
    //1個元素
    public void testCreateQuerySortCond_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQuerySortCond_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "createQuerySortCond", 
                    new Class[]{WEB3AdminTraderAdminIPControlSortKey[].class});
            
            l_method.setAccessible(true);
            
            WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys =
                new WEB3AdminTraderAdminIPControlSortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminIPControlSortKey();
            l_sortKeys[0].keyItem = "startDate";
            l_sortKeys[0].ascDesc = "A";
            
            String l_strQuerySortCond = (String)l_method.invoke(l_serviceImpl, new Object[]{l_sortKeys});
            assertEquals(" appli_start_timestamp Asc", l_strQuerySortCond);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(create検索ソート条件)
    //3個元素
    public void testCreateQuerySortCond_C0002()
    {
        final String STR_METHOD_NAME = "testCreateQuerySortCond_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "createQuerySortCond", 
                    new Class[]{WEB3AdminTraderAdminIPControlSortKey[].class});
            
            l_method.setAccessible(true);
            
            WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys =
                new WEB3AdminTraderAdminIPControlSortKey[3];
            
            l_sortKeys[0] = new WEB3AdminTraderAdminIPControlSortKey();
            l_sortKeys[0].keyItem = "status";
            l_sortKeys[0].ascDesc = "D";
            
            l_sortKeys[1] = new WEB3AdminTraderAdminIPControlSortKey();
            l_sortKeys[1].keyItem = "startDate";
            l_sortKeys[1].ascDesc = "A";
            
            l_sortKeys[2] = new WEB3AdminTraderAdminIPControlSortKey();
            l_sortKeys[2].keyItem = "ipAddress";
            l_sortKeys[2].ascDesc = "D";
            
            String l_strQuerySortCond = (String)l_method.invoke(l_serviceImpl, new Object[]{l_sortKeys});
            assertEquals(" status Desc, appli_start_timestamp Asc," +
                " NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') ||" +
                " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
                " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') ||" +
                " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
                " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') ||" +
                " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')Desc" ,
                l_strQuerySortCond);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //(getログイン拒否IP一覧)
    //検索結果が0件の場合
    public void testGetLoginRejectIPList_C0001()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(5000L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setInstitutionCode("3H");
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("2");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPList", 
                    new Class[]{String.class, String.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            String l_strQuerySortCond = " ip_address Asc";
            String l_strInstitutionCode = "3H";
            String l_strPageSize = "2";
            String l_strPageIndex = "1";

            l_method.invoke(l_serviceImpl,
                new Object[]{l_strQuerySortCond, l_strInstitutionCode, l_strPageSize, l_strPageIndex});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    //(getログイン拒否IP一覧)
    //7+2
    public void testGetLoginRejectIPList_C0002()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(1001L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(1002L);
            l_loginRejectIpParams.setInstitutionCode("3H");
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.4");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("2");
            l_loginRejectIpParams.setIpAddress("10.0.0.2");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080601223344", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5003L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.9");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5004L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.4");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5005L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("2");
            l_loginRejectIpParams.setIpAddress("10.0.0.6");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080901223344", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5006L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.0");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080519142800", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5007L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.4");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080927211022", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);

            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPList", 
                    new Class[]{String.class, String.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            String l_strQuerySortCond = " status Desc, ip_address Asc, appli_start_timestamp Desc";
            String l_strInstitutionCode = "0D";
            String l_strPageSize = "10";
            String l_strPageIndex = "1";

            List l_lisLoginRejectIpRows = (List)l_method.invoke(l_serviceImpl,
                new Object[]{l_strQuerySortCond, l_strInstitutionCode, l_strPageSize, l_strPageIndex});
            
            assertEquals(7, l_lisLoginRejectIpRows.size());
            assertEquals(5002, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(0)).getLoginRejectId());
            assertEquals(5005, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(1)).getLoginRejectId());
            assertEquals(5006, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(2)).getLoginRejectId());
            assertEquals(5007, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(3)).getLoginRejectId());
            assertEquals(5004, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(4)).getLoginRejectId());
            assertEquals(5001, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(5)).getLoginRejectId());
            assertEquals(5003, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(6)).getLoginRejectId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    //(getログイン拒否IP一覧)
    //7+2
    public void testGetLoginRejectIPList_C0003()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPList_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(1001L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(1002L);
            l_loginRejectIpParams.setInstitutionCode("3H");
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.4");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("2");
            l_loginRejectIpParams.setIpAddress("10.0.0.2");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080601223344", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5003L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.9");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5004L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.4");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5005L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("2");
            l_loginRejectIpParams.setIpAddress("10.0.0.6");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080901223344", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5006L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.0");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080519142800", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5007L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setIpAddress("10.0.0.4");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080927211022", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);

            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPList", 
                    new Class[]{String.class, String.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            String l_strQuerySortCond = " status Desc, ip_address Asc, appli_start_timestamp Desc";
            String l_strInstitutionCode = "0D";
            String l_strPageSize = "3";
            String l_strPageIndex = "2";

            List l_lisLoginRejectIpRows = (List)l_method.invoke(l_serviceImpl,
                new Object[]{l_strQuerySortCond, l_strInstitutionCode, l_strPageSize, l_strPageIndex});
            
            assertEquals(3, l_lisLoginRejectIpRows.size());
            assertEquals(5007, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(0)).getLoginRejectId());
            assertEquals(5004, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(1)).getLoginRejectId());
            assertEquals(5001, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(2)).getLoginRejectId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    
    public void testGetLoginRejectIPList_C0004()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPList_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(1001L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(1002L);
            l_loginRejectIpParams.setInstitutionCode("3H");
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setInstitutionCode("0D");
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setIpAddress("10.0.0.10");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setIpAddress("10.0.0.9");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5003L);
            l_loginRejectIpParams.setIpAddress("10.0.0.1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);


            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPList", 
                    new Class[]{String.class, String.class, String.class, String.class});
            
            l_method.setAccessible(true);
            
            String l_strQuerySortCond =
                " NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS, '.', 1, 1) -1), 3, '0'), '000') ||" +
                " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
                " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') ||" +
                " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
                " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') ||" +
                " NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000')Asc";
            String l_strInstitutionCode = "0D";
            String l_strPageSize = "10";
            String l_strPageIndex = "1";

            List l_lisLoginRejectIpRows = (List)l_method.invoke(l_serviceImpl,
                new Object[]{l_strQuerySortCond, l_strInstitutionCode, l_strPageSize, l_strPageIndex});
            
            assertEquals(3, l_lisLoginRejectIpRows.size());
            assertEquals(5003, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(0)).getLoginRejectId());
            assertEquals(5002, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(1)).getLoginRejectId());
            assertEquals(5001, ((LoginRejectIpRow)l_lisLoginRejectIpRows.get(2)).getLoginRejectId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    
    
    //(getログイン拒否IP行)
    //
    public void testGetLoginRejectIPParams_C0001()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPParams_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(5000L);
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setStatus("2");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            TestDBUtility.commit();
            
            TestDBUtility.beginTransaction();
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPParams", 
                    new Class[]{String.class, boolean.class});
            
            l_method.setAccessible(true);
            
            String l_strDenyLoginID = "5000";
            
            boolean l_blnLockFlag = true;
      
            l_loginRejectIpParams =
                (LoginRejectIpParams)l_method.invoke(
                    l_serviceImpl, new Object[]{l_strDenyLoginID, new Boolean(l_blnLockFlag)});
           
            assertEquals(true, TestDBUtility.isTableLockedSuccessful(LoginRejectIpRow.TYPE));
            
            assertEquals(5000, l_loginRejectIpParams.getLoginRejectId());
            assertEquals("0", l_loginRejectIpParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }

    //(getログイン拒否IP行)
    //
    public void testGetLoginRejectIPParams_C0002()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPParams_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(5000L);
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setStatus("2");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPParams", 
                    new Class[]{String.class, boolean.class});
            
            l_method.setAccessible(true);
            
            String l_strDenyLoginID = "5001";
            
            boolean l_blnLockFlag = true;
      
            l_loginRejectIpParams =
                (LoginRejectIpParams)l_method.invoke(
                    l_serviceImpl, new Object[]{l_strDenyLoginID, new Boolean(l_blnLockFlag)});
            
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(getログイン拒否IP行)
    public void testGetLoginRejectIPParams_C0003()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPParams_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(5000L);
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setStatus("2");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            TestDBUtility.commit();
            
            TestDBUtility.beginTransaction();
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPParams", 
                    new Class[]{String.class, boolean.class});
            
            l_method.setAccessible(true);
            
            String l_strDenyLoginID = "5002";
            
            boolean l_blnLockFlag = false;
      
            l_loginRejectIpParams =
                (LoginRejectIpParams)l_method.invoke(
                    l_serviceImpl, new Object[]{l_strDenyLoginID, new Boolean(l_blnLockFlag)});
            
            assertEquals(false, TestDBUtility.isTableLockedSuccessful(LoginRejectIpRow.TYPE));
            
            assertEquals(5002, l_loginRejectIpParams.getLoginRejectId());
            assertEquals("2", l_loginRejectIpParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    //(getログイン拒否IP行)
    public void testGetLoginRejectIPParams_C0004()
    {
        final String STR_METHOD_NAME = "testGetLoginRejectIPParams_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            
            l_loginRejectIpParams.setLoginRejectId(5000L);
            l_loginRejectIpParams.setStatus("0");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setStatus("1");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5002L);
            l_loginRejectIpParams.setStatus("2");
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "getLoginRejectIPParams", 
                    new Class[]{String.class, boolean.class});
            
            l_method.setAccessible(true);
            
            String l_strDenyLoginID = "5003";
            
            boolean l_blnLockFlag = false;
      
            l_loginRejectIpParams =
                (LoginRejectIpParams)l_method.invoke(
                    l_serviceImpl, new Object[]{l_strDenyLoginID, new Boolean(l_blnLockFlag)});
            
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    //(createログイン拒否IP情報)
    //(引数)ログイン拒否IP行.更新区分 == "0"(デーモン)の場合
    public void testCreateLoginRejectIPList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateLoginRejectIPList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "createLoginRejectIPList", 
                    new Class[]{LoginRejectIpParams.class});
            
            l_method.setAccessible(true);
      
            LoginRejectIpParams l_loginRejectIPParams = this.getLoginRejectIpRow();
            l_loginRejectIPParams.setUpdatedDiv("0");//デーモン
            
            WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit = 
                (WEB3AdminTraderAdminIPControlReferenceUnit)l_method.invoke(
                    l_serviceImpl, new Object[]{l_loginRejectIPParams});
            assertEquals("5001", l_adminIPControlReferenceUnit.denyLoginID);
            assertEquals("10.0.0.0", l_adminIPControlReferenceUnit.ipAddress);
            assertEquals("0", l_adminIPControlReferenceUnit.status);
            assertEquals("20080808201122",
                WEB3DateUtility.formatDate(l_adminIPControlReferenceUnit.startDate, "yyyyMMddHHmmss"));
            assertEquals("20080917221100",
                WEB3DateUtility.formatDate(l_adminIPControlReferenceUnit.endDate, "yyyyMMddHHmmss"));        
            assertEquals("1", l_adminIPControlReferenceUnit.registDiv);
            assertEquals("0", l_adminIPControlReferenceUnit.updateDiv);
            assertEquals(null, l_adminIPControlReferenceUnit.updaterCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);        
    }
    
    //(createログイン拒否IP情報)
    //(引数)ログイン拒否IP行.更新区分 == "1"(管理者)の場合
    public void testCreateLoginRejectIPList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateLoginRejectIPList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "createLoginRejectIPList", 
                    new Class[]{LoginRejectIpParams.class});
            
            l_method.setAccessible(true);
      
            LoginRejectIpParams l_loginRejectIPParams = this.getLoginRejectIpRow();
            l_loginRejectIPParams.setUpdatedDiv("1");//デーモン
            
            WEB3AdminTraderAdminIPControlReferenceUnit l_adminIPControlReferenceUnit = 
                (WEB3AdminTraderAdminIPControlReferenceUnit)l_method.invoke(
                    l_serviceImpl, new Object[]{l_loginRejectIPParams});
            
            assertEquals("5001", l_adminIPControlReferenceUnit.denyLoginID);
            assertEquals("10.0.0.0", l_adminIPControlReferenceUnit.ipAddress);
            assertEquals("0", l_adminIPControlReferenceUnit.status);
            assertEquals("20080808201122",
                WEB3DateUtility.formatDate(l_adminIPControlReferenceUnit.startDate, "yyyyMMddHHmmss"));
            assertEquals("20080917221100",
                WEB3DateUtility.formatDate(l_adminIPControlReferenceUnit.endDate, "yyyyMMddHHmmss"));        
            assertEquals("1", l_adminIPControlReferenceUnit.registDiv);
            assertEquals("1", l_adminIPControlReferenceUnit.updateDiv);
            assertEquals("403", l_adminIPControlReferenceUnit.updaterCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate変更有無)
    //例外をthrowする
    public void testValidateIsChange_C0001()
    {
        final String STR_METHOD_NAME = "testValidateIsChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "validateIsChange", 
                    new Class[]{LoginRejectIpParams.class, String.class, Date.class});
            
            l_method.setAccessible(true);
      
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setAppliEndTimestamp(
                    new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime()));
            
            String l_strStatus = "0";
            
            Date l_datEndDate =
                new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime());
            
            l_method.invoke(l_serviceImpl, new Object[]{l_loginRejectIpParams,l_strStatus,l_datEndDate});
            
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03132,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate変更有無)
    //正常完了  (引数)ログイン拒否IP行.ステータス != (引数)ステータス
    public void testValidateIsChange_C0002()
    {
        final String STR_METHOD_NAME = "testValidateIsChange_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "validateIsChange", 
                    new Class[]{LoginRejectIpParams.class, String.class, Date.class});
            
            l_method.setAccessible(true);
      
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            l_loginRejectIpParams.setStatus("1");
            l_loginRejectIpParams.setAppliEndTimestamp(
                    new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime()));
            
            String l_strStatus = "2";
            
            Date l_datEndDate =
                new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime());
            
            l_method.invoke(l_serviceImpl, new Object[]{l_loginRejectIpParams,l_strStatus,l_datEndDate});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(validate変更有無)
    //正常完了  (引数)ログイン拒否IP行.ステータス != (引数)ステータス
    public void testValidateIsChange_C0003()
    {
        final String STR_METHOD_NAME = "testValidateIsChange_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "validateIsChange", 
                    new Class[]{LoginRejectIpParams.class, String.class, Date.class});
            
            l_method.setAccessible(true);
      
            LoginRejectIpParams l_loginRejectIpParams = this.getLoginRejectIpRow();
            l_loginRejectIpParams.setStatus("2");
            l_loginRejectIpParams.setAppliEndTimestamp(
                    new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime()));
            
            String l_strStatus = "2";
            
            Date l_datEndDate =
                new Timestamp(WEB3DateUtility.getDate("20120917221100", "yyyyMMddHHmmss").getTime());
            
            l_method.invoke(l_serviceImpl, new Object[]{l_loginRejectIpParams,l_strStatus,l_datEndDate});
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(saveログイン拒否IP)
    public void testSaveLoginRejectIP_C0001()
    {
        final String STR_METHOD_NAME = "testSaveLoginRejectIP_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {}, 
                new Timestamp(WEB3DateUtility.getDate("20081010112233", "yyyyMMddHHmmss").getTime()));
            
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "saveLoginRejectIP", 
                    new Class[]{
                        WEB3AdminTraderAdminIPControlRegistCompleteRequest.class, 
                        String.class, 
                        String.class});
            
            l_method.setAccessible(true);
      
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request =
                new WEB3AdminTraderAdminIPControlRegistCompleteRequest();
            l_request.ipAddress = "127.0.0.0";
            l_request.status = "2";
            l_request.startDate =
                new Timestamp(WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss").getTime());
            l_request.endDate =
                new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime());
            
            String l_strInstitutionCode = "3H";
            
            String l_strAdministratorCode = "506";
            
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            
            l_method.invoke(l_serviceImpl,
                new Object[]{l_request,l_strInstitutionCode,l_strAdministratorCode});
            
            List l_lisLoginRejectIpRows =
                Processors.getDefaultProcessor().doFindAllQuery(LoginRejectIpRow.TYPE);
            
            assertEquals(1, l_lisLoginRejectIpRows.size());
            
            LoginRejectIpRow l_loginRejectIpRow =
                (LoginRejectIpRow)l_lisLoginRejectIpRows.get(0);
            
            assertEquals("3H", l_loginRejectIpRow.getInstitutionCode());
            assertEquals("127.0.0.0", l_loginRejectIpRow.getIpAddress());
            assertEquals("2", l_loginRejectIpRow.getStatus());
            assertEquals("20080808201122",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getAppliStartTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080917221100",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getAppliEndTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("1", l_loginRejectIpRow.getRegistDiv());
            assertEquals("1", l_loginRejectIpRow.getUpdatedDiv());
            assertEquals("506", l_loginRejectIpRow.getLastUpdater());
            assertEquals("20081010112233",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20081010112233",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(updateログイン拒否IP)
    public void testUpdateLoginRejectIP_C0001()
    {
        final String STR_METHOD_NAME = "testUpdateLoginRejectIP_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {}, 
                new Timestamp(WEB3DateUtility.getDate("20081010112233", "yyyyMMddHHmmss").getTime()));
            
            Method l_method =
                WEB3AdminTMLoginRejectIPManagementServiceImpl.class.getDeclaredMethod(
                    "updateLoginRejectIP", 
                    new Class[]{ String.class, Map.class});
            
            l_method.setAccessible(true);
      
            String l_strLoginRejectId = "5001";
            
            Map l_mapUpdateContents = new HashMap();
            l_mapUpdateContents.put("institution_code", "3H");
            l_mapUpdateContents.put("ip_address", "127.0.0.0");
            l_mapUpdateContents.put("status", "2");
            l_mapUpdateContents.put("appli_start_timestamp",
                new Timestamp(WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss").getTime()));
            l_mapUpdateContents.put("appli_end_timestamp",
                new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime()));
            l_mapUpdateContents.put("regist_div", "0");
            l_mapUpdateContents.put("updated_div", "0");
            l_mapUpdateContents.put("last_updater", "506");
            l_mapUpdateContents.put("created_timestamp",
                new Timestamp(WEB3DateUtility.getDate("20081010112233", "yyyyMMddHHmmss").getTime()));
            l_mapUpdateContents.put("last_updated_timestamp",
                new Timestamp(WEB3DateUtility.getDate("20081010112233", "yyyyMMddHHmmss").getTime()));

            
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            LoginRejectIpParams l_loginRejectIpParams = new LoginRejectIpParams();
            l_loginRejectIpParams.setLoginRejectId(5001L);
            l_loginRejectIpParams.setInstitutionCode("0D");
            l_loginRejectIpParams.setIpAddress("10.0.0.0");
            l_loginRejectIpParams.setStatus("0");
            l_loginRejectIpParams.setAppliStartTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080512142800", "yyyyMMddHHmmss").getTime()));
            l_loginRejectIpParams.setAppliEndTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080519142800", "yyyyMMddHHmmss").getTime()));
            l_loginRejectIpParams.setRegistDiv("1");
            l_loginRejectIpParams.setUpdatedDiv("1");
            l_loginRejectIpParams.setLastUpdater("403");
            l_loginRejectIpParams.setCreatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20110401001122", "yyyyMMddHHmmss").getTime()));
            l_loginRejectIpParams.setLastUpdatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20110401001122", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_loginRejectIpParams.setLoginRejectId(5005L);
            TestDBUtility.insertWithDel(l_loginRejectIpParams);
            
            l_method.invoke(l_serviceImpl,
                new Object[]{l_strLoginRejectId,l_mapUpdateContents});
            
            List l_lisLoginRejectIpRows =
                Processors.getDefaultProcessor().doFindAllQuery(LoginRejectIpRow.TYPE);
            
            assertEquals(2, l_lisLoginRejectIpRows.size());
            
            LoginRejectIpRow l_loginRejectIpRow =
                (LoginRejectIpRow)l_lisLoginRejectIpRows.get(0);
            assertEquals(5001, l_loginRejectIpRow.getLoginRejectId());
            assertEquals("3H", l_loginRejectIpRow.getInstitutionCode());
            assertEquals("127.0.0.0", l_loginRejectIpRow.getIpAddress());
            assertEquals("2", l_loginRejectIpRow.getStatus());
            assertEquals("20080808201122",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getAppliStartTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080917221100",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getAppliEndTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("0", l_loginRejectIpRow.getRegistDiv());
            assertEquals("0", l_loginRejectIpRow.getUpdatedDiv());
            assertEquals("506", l_loginRejectIpRow.getLastUpdater());
            assertEquals("20081010112233",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20081010112233",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
            
            l_loginRejectIpRow =
                (LoginRejectIpRow)l_lisLoginRejectIpRows.get(1);
            assertEquals(5005, l_loginRejectIpRow.getLoginRejectId());
            assertEquals("0D", l_loginRejectIpRow.getInstitutionCode());
            assertEquals("10.0.0.0", l_loginRejectIpRow.getIpAddress());
            assertEquals("0", l_loginRejectIpRow.getStatus());
            assertEquals("20080512142800",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getAppliStartTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080519142800",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getAppliEndTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("1", l_loginRejectIpRow.getRegistDiv());
            assertEquals("1", l_loginRejectIpRow.getUpdatedDiv());
            assertEquals("403", l_loginRejectIpRow.getLastUpdater());
            assertEquals("20110401001122",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20110401001122",
                WEB3DateUtility.formatDate(l_loginRejectIpRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
            TestDBUtility.deleteAll(LoginRejectIpRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private LoginRejectIpParams getLoginRejectIpRow()
    {
        LoginRejectIpParams l_loginRejectIpParams = new LoginRejectIpParams();
        
        l_loginRejectIpParams.setLoginRejectId(5001L);
        l_loginRejectIpParams.setInstitutionCode("0D");
        l_loginRejectIpParams.setIpAddress("10.0.0.0");
        l_loginRejectIpParams.setStatus("0");
        l_loginRejectIpParams.setAppliStartTimestamp(
            new Timestamp(WEB3DateUtility.getDate("20080808201122", "yyyyMMddHHmmss").getTime()));
        l_loginRejectIpParams.setAppliEndTimestamp(
            new Timestamp(WEB3DateUtility.getDate("20080917221100", "yyyyMMddHHmmss").getTime()));
        l_loginRejectIpParams.setRegistDiv("1");
        l_loginRejectIpParams.setUpdatedDiv("1");
        l_loginRejectIpParams.setLastUpdater("403");
        l_loginRejectIpParams.setCreatedTimestamp(
            new Timestamp(WEB3DateUtility.getDate("20081010112233", "yyyyMMddHHmmss").getTime()));
        l_loginRejectIpParams.setLastUpdatedTimestamp(
            new Timestamp(WEB3DateUtility.getDate("20081010112233", "yyyyMMddHHmmss").getTime()));
        
        return l_loginRejectIpParams;
    }
    
    private AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();

        l_administratorParams.setAdministratorId(110120119L);
        l_administratorParams.setAdministratorCode("123456789");
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setInstitutionId(33);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setLoginId(3338111123L);
        l_administratorParams.setPermissionLevel("123");

        return l_administratorParams;
    }
    
    private class WEB3AdminTMLoginRejectIPManagementServiceImplForTest extends WEB3AdminTMLoginRejectIPManagementServiceImpl
    {
        protected WEB3AdminTraderAdminIPControlListResponse getListScreen(
            WEB3AdminTraderAdminIPControlListRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlListResponse l_response =
                new WEB3AdminTraderAdminIPControlListResponse();
            l_response.errorMessage = "getListScreen";
            return l_response;
        }
        
        protected WEB3AdminTraderAdminIPControlRegistInputResponse getRegistScreen(
            WEB3AdminTraderAdminIPControlRegistInputRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlRegistInputResponse l_response =
                new WEB3AdminTraderAdminIPControlRegistInputResponse();
            l_response.errorMessage = "getRegistScreen";
            return l_response;
        }
        
        protected WEB3AdminTraderAdminIPControlRegistConfirmResponse validateRegist(
            WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response =
                new WEB3AdminTraderAdminIPControlRegistConfirmResponse();
            l_response.errorMessage = "validateRegist";
            return l_response;
        }
        
        protected WEB3AdminTraderAdminIPControlRegistCompleteResponse submitRegist(
            WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response =
                new WEB3AdminTraderAdminIPControlRegistCompleteResponse();
            l_response.errorMessage = "submitRegist";
            return l_response;
        }
        
        protected WEB3AdminTraderAdminIPControlUpdateInputResponse getChangedScreen(
            WEB3AdminTraderAdminIPControlUpdateInputRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlUpdateInputResponse l_response =
                new WEB3AdminTraderAdminIPControlUpdateInputResponse();
            l_response.errorMessage = "getChangedScreen";
            return l_response;
        }
        
        protected WEB3AdminTraderAdminIPControlUpdateConfirmResponse validateChange(
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response =
                new WEB3AdminTraderAdminIPControlUpdateConfirmResponse();
            l_response.errorMessage = "validateChange";
            return l_response;
        }

        protected WEB3AdminTraderAdminIPControlUpdateCompleteResponse submitChange(
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response =
                new WEB3AdminTraderAdminIPControlUpdateCompleteResponse();
            l_response.errorMessage = "submitChange";
            return l_response;
        }
        
        protected WEB3AdminTraderAdminIPControlDeleteConfirmResponse validateDelete(
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response =
                new WEB3AdminTraderAdminIPControlDeleteConfirmResponse();
            l_response.errorMessage = "validateDelete";
            return l_response;
        }
        
        protected WEB3AdminTraderAdminIPControlDeleteCompleteResponse submitDelete(
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response =
                new WEB3AdminTraderAdminIPControlDeleteCompleteResponse();
            l_response.errorMessage = "submitDelete";
            return l_response;
        }
        
    }

}
@
