head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.VirtualServerInformationParams;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifoadmin.data.IfoCsvFileFormatParams;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoFailureOrderInTroubleDownloadRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoFailureOrderInTroubleDownloadResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplTest.class);

    public WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplTest(String name)
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

    private String[] CsvColumnModel =
    {"市場", "銘柄コード", "注文種別", "注文識別", "売/買", "元注文値段", "値段", 
    "数量", "執条", "信用", "空売", "自/委", "安/裁", "数条", "数約",
    "ｻﾎﾟｰﾄﾒﾝﾊﾞｰ", "任意設定項目", "社内処理番号", "取引ID", "ストップ銘柄",
    "トリガー値段", "ストップ条件", "残注文数量", "被管理サブ参加者コード",
    "被管理ユーザID", "管理サブ参加者コード", "管理ユーザID"};
    
    /*
     * Test method for 'webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecuteCase1()
    {
        final String STR_METHOD_NAME = "testExecuteCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            initDataCase8();

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            WEB3AdminIfoFailureOrderInTroubleDownloadResponse l_response = 
                (WEB3AdminIfoFailureOrderInTroubleDownloadResponse)l_impl.execute(l_request);
            assertEquals("7", l_response.downloadFileList.length+"");
            
            assertEquals("追加ダウンロードが必要な件数は0件です。" +
                    "以下の注文は、市場に既に発注されている可能性があります。" +
                    "市場端末より市場にて有効であるかを確認し、無効の場合のみ再発注を行ってください。" +
                    "社内処理項目：00000000000000000001、00000000000000000001、00000000000000000005、00000000000000000004", l_response.downloadFileList[0]);
            
            assertEquals("市場,銘柄コード,注文種別,注文識別,売/買,元注文値段,値段," +
                    "数量,執条,信用,空売,自/委,安/裁,数条,数約," +
                    "ｻﾎﾟｰﾄﾒﾝﾊﾞｰ,任意設定項目,社内処理番号,取引ID,ストップ銘柄," +
                    "トリガー値段,ストップ条件,残注文数量,被管理サブ参加者コード," +
                    "被管理ユーザID,管理サブ参加者コード,管理ユーザID",l_response.downloadFileList[1]);
        }
        catch(Exception l_ex)
        {
            l_ex.getMessage();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase2()
    {
        final String STR_METHOD_NAME = "testExecuteCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoManualLapseRunRequest l_request = new WEB3AdminIfoManualLapseRunRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();

            l_impl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            l_ex.getMessage();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase3()
    {
        final String STR_METHOD_NAME = "testExecuteCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();

            l_impl.execute(null);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            l_ex.getMessage();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl.getDownloadFile(WEB3AdminIfoFailureOrderInTroubleDownloadRequest)'
     */
    public void testGetDownloadFileCase1()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {  
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
            
            l_request.corpCode = "aaa";
            l_request.orderType = "1";

            l_impl.getDownloadFile(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03205, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadFileCase2()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setTransactionCategory("C0305");
            l_AdminPermissionParams.setPermissionLevel("770");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            l_impl.getDownloadFile(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadFileCase3()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setTransactionCategory("C0306");
            l_AdminPermissionParams.setPermissionLevel("770");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            l_AdministratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
            
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            l_impl.getDownloadFile(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01380, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadFileCase4()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setTransactionCategory("C0306");
            l_AdminPermissionParams.setPermissionLevel("770");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            l_AdministratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
            
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            l_impl.getDownloadFile(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03206, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadFileCase5()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setTransactionCategory("C0306");
            l_AdminPermissionParams.setPermissionLevel("770");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            l_AdministratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
            
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("00000000000000000001");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI803");
            l_FotypeOrderAllParams2.setCancelDiv("0");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            l_impl.getDownloadFile(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03203, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadFileCase6()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setTransactionCategory("C0306");
            l_AdminPermissionParams.setPermissionLevel("770");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            l_AdministratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
            
            //新規
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("00000000000000000002");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI803");
            l_FotypeOrderAllParams1.setCancelDiv("0");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("00000000000000000001");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI803");
            l_FotypeOrderAllParams2.setCancelDiv("0");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            l_impl.getDownloadFile(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03207, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadFileCase7()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setTransactionCategory("C0306");
            l_AdminPermissionParams.setPermissionLevel("770");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            l_AdministratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
            VirtualServerInformationParams l_VirtualServerInformationParams  = new VirtualServerInformationParams();
            l_VirtualServerInformationParams.setVirtualServerNumberJsoes("101");
            l_VirtualServerInformationParams.setInstitutionCode("0D");
            l_VirtualServerInformationParams.setFrontOrderExchangeCode("2");
            l_VirtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
            l_VirtualServerInformationParams.setVirtualServerNumberMarket("01");
            l_VirtualServerInformationParams.setCreatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            l_VirtualServerInformationParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_VirtualServerInformationParams);
            VirtualServerInformationParams l_VirtualServerInformationParams1  = new VirtualServerInformationParams();
            l_VirtualServerInformationParams1.setVirtualServerNumberJsoes("100");
            l_VirtualServerInformationParams1.setInstitutionCode("0D");
            l_VirtualServerInformationParams1.setFrontOrderExchangeCode("2");
            l_VirtualServerInformationParams1.setProductType(ProductTypeEnum.IFO);
            l_VirtualServerInformationParams1.setVirtualServerNumberMarket("01");
            l_VirtualServerInformationParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            l_VirtualServerInformationParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_VirtualServerInformationParams1);
            
            //新規
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("00000000000000000002");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI803");
            l_FotypeOrderAllParams1.setCancelDiv("0");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            l_FotypeOrderAllParams1.setAmgSendTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("00000000000000000001");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI803");
            l_FotypeOrderAllParams2.setCancelDiv("0");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            l_FotypeOrderAllParams2.setAmgSendTime(WEB3DateUtility.getDate("2010/10/30 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            WEB3AdminIfoFailureOrderInTroubleDownloadResponse l_response = l_impl.getDownloadFile(l_request);
            
            assertEquals("3", l_response.downloadFileList.length+"");
            
            assertEquals("追加ダウンロードが必要な件数は0件です。", l_response.downloadFileList[0]);
            
            assertEquals("市場,銘柄コード,注文種別,注文識別,売/買,元注文値段,値段," +
                    "数量,執条,信用,空売,自/委,安/裁,数条,数約," +
                    "ｻﾎﾟｰﾄﾒﾝﾊﾞｰ,任意設定項目,社内処理番号,取引ID,ストップ銘柄," +
                    "トリガー値段,ストップ条件,残注文数量,被管理サブ参加者コード," +
                    "被管理ユーザID,管理サブ参加者コード,管理ユーザID",l_response.downloadFileList[1]);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDownloadFileCase8()
    {
        final String STR_METHOD_NAME = "testGetDownloadFileCase8()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
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
                
            WEB3AdminIfoFailureOrderInTroubleDownloadRequest l_request = new WEB3AdminIfoFailureOrderInTroubleDownloadRequest();
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
           
            initDataCase8();

            l_request.corpCode = "00000000000000000001";
            l_request.orderType = "1";

            WEB3AdminIfoFailureOrderInTroubleDownloadResponse l_response = l_impl.getDownloadFile(l_request);
            
            assertEquals("7", l_response.downloadFileList.length+"");
            
            assertEquals("追加ダウンロードが必要な件数は0件です。" +
                    "以下の注文は、市場に既に発注されている可能性があります。" +
                    "市場端末より市場にて有効であるかを確認し、無効の場合のみ再発注を行ってください。" +
                    "社内処理項目：00000000000000000001、00000000000000000001、00000000000000000005、00000000000000000004", l_response.downloadFileList[0]);
            
            assertEquals("市場,銘柄コード,注文種別,注文識別,売/買,元注文値段,値段," +
                    "数量,執条,信用,空売,自/委,安/裁,数条,数約," +
                    "ｻﾎﾟｰﾄﾒﾝﾊﾞｰ,任意設定項目,社内処理番号,取引ID,ストップ銘柄," +
                    "トリガー値段,ストップ条件,残注文数量,被管理サブ参加者コード," +
                    "被管理ユーザID,管理サブ参加者コード,管理ユーザID",l_response.downloadFileList[1]);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void initDataCase8()
    {
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode("0D");
            l_AdminPermissionParams.setTransactionCategory("C0306");
            l_AdminPermissionParams.setPermissionLevel("770");
            l_AdminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_AdministratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_AdministratorTypeParams.setInstitutionCode("0D");
            l_AdministratorTypeParams.setPermissionLevel("770");
            l_AdministratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_AdministratorTypeParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(33L);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(VirtualServerInformationParams.TYPE);
            VirtualServerInformationParams l_VirtualServerInformationParams  = new VirtualServerInformationParams();
            l_VirtualServerInformationParams.setVirtualServerNumberJsoes("101");
            l_VirtualServerInformationParams.setInstitutionCode("0D");
            l_VirtualServerInformationParams.setFrontOrderExchangeCode("2");
            l_VirtualServerInformationParams.setProductType(ProductTypeEnum.IFO);
            l_VirtualServerInformationParams.setVirtualServerNumberMarket("01");
            l_VirtualServerInformationParams.setCreatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            l_VirtualServerInformationParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_VirtualServerInformationParams);
            VirtualServerInformationParams l_VirtualServerInformationParams1  = new VirtualServerInformationParams();
            l_VirtualServerInformationParams1.setVirtualServerNumberJsoes("100");
            l_VirtualServerInformationParams1.setInstitutionCode("0D");
            l_VirtualServerInformationParams1.setFrontOrderExchangeCode("2");
            l_VirtualServerInformationParams1.setProductType(ProductTypeEnum.IFO);
            l_VirtualServerInformationParams1.setVirtualServerNumberMarket("01");
            l_VirtualServerInformationParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            l_VirtualServerInformationParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_VirtualServerInformationParams1);
            
            //新規
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("00000000000000000001");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/09/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI803");
            l_FotypeOrderAllParams1.setCancelDiv("0");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");

            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("00000000000000000001");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI803");
            l_FotypeOrderAllParams2.setCancelDiv("0");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            l_FotypeOrderAllParams2.setAmgSendTime(WEB3DateUtility.getDate("2010/09/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams3.setCorpCode("00000000000000000001");
            l_FotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/09 00:00:15",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams3.setRequestCode("EI803");
            l_FotypeOrderAllParams3.setCancelDiv("0");
            l_FotypeOrderAllParams3.setStatus("1");
            l_FotypeOrderAllParams3.setInstitutionCode("0D");
            l_FotypeOrderAllParams3.setAccountId(333812512203L);
            l_FotypeOrderAllParams3.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams3);
            
            //取消
            HostFotypeOrderAllParams l_FotypeOrderAllParams7 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams7.setCorpCode("00000000000000000005");
            l_FotypeOrderAllParams7.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams7.setRequestCode("EI802");
            l_FotypeOrderAllParams7.setCancelDiv("1");
            l_FotypeOrderAllParams7.setStatus("1");
            l_FotypeOrderAllParams7.setInstitutionCode("0D");
            l_FotypeOrderAllParams7.setAccountId(333812512203L);
            l_FotypeOrderAllParams7.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams7);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams3 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams3.setAccountId(333812512203L);
            l_IfoOrderUnitParams3.setOrderRequestNumber("00");
            l_IfoOrderUnitParams3.setOrderUnitId(1001L);
            l_IfoOrderUnitParams3.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams3.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams3);
            
            //訂正
            HostFotypeOrderAllParams l_FotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams4.setCorpCode("00000000000000000001");
            l_FotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams4.setRequestCode("EI804");
            l_FotypeOrderAllParams4.setCancelDiv("0");
            l_FotypeOrderAllParams4.setStatus("1");
            l_FotypeOrderAllParams4.setInstitutionCode("0D");
            l_FotypeOrderAllParams4.setAccountId(333812512203L);
            l_FotypeOrderAllParams4.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams4);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setAccountId(333812512203L);
            l_IfoOrderUnitParams2.setOrderRequestNumber("01");
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams2.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);

            HostFotypeOrderAllParams l_FotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams5.setCorpCode("00000000000000000003");
            l_FotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams5.setRequestCode("EI804");
            l_FotypeOrderAllParams5.setCancelDiv("0");
            l_FotypeOrderAllParams5.setStatus("1");
            l_FotypeOrderAllParams5.setInstitutionCode("0D");
            l_FotypeOrderAllParams5.setAccountId(333812512203L);
            l_FotypeOrderAllParams5.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams5);

            HostFotypeOrderAllParams l_FotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams6.setCorpCode("00000000000000000004");
            l_FotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams6.setRequestCode("EI804");
            l_FotypeOrderAllParams6.setCancelDiv("0");
            l_FotypeOrderAllParams6.setStatus("1");
            l_FotypeOrderAllParams6.setInstitutionCode("0D");
            l_FotypeOrderAllParams6.setAccountId(333812512203L);
            l_FotypeOrderAllParams6.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams6);
            
            TestDBUtility.deleteAll(IfoCsvFileFormatParams.TYPE);
            for (int i = 0; i < this.CsvColumnModel.length; i++)
            {
                IfoCsvFileFormatParams l_IfoCsvFileFormatParams =
                    new IfoCsvFileFormatParams();
                l_IfoCsvFileFormatParams.setInstitutionCode("0D");
                l_IfoCsvFileFormatParams.setColumnNumber(i);
                l_IfoCsvFileFormatParams.setColumnLabel(this.CsvColumnModel[i]);
                l_IfoCsvFileFormatParams.setCreatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
                l_IfoCsvFileFormatParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20101011", "yyyyMMdd"));
                TestDBUtility.insertWithDel(l_IfoCsvFileFormatParams);
            }
        }
        catch (Exception l_ex)
        {
            
        }
    }

    /*
     * Test method for 'webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl.getNotOrdered(WEB3GentradeInstitution, Date, String)'
     */
    public void testGetNotOrderedCase1()
    {
        final String STR_METHOD_NAME = "testGetNotOrderedCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
   
            //新規
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("002");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI803");
            l_FotypeOrderAllParams1.setCancelDiv("0");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("001");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI803");
            l_FotypeOrderAllParams2.setCancelDiv("0");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams3.setCorpCode("001");
            l_FotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams3.setRequestCode("EI803");
            l_FotypeOrderAllParams3.setCancelDiv("0");
            l_FotypeOrderAllParams3.setStatus("1");
            l_FotypeOrderAllParams3.setInstitutionCode("0D");
            l_FotypeOrderAllParams3.setAccountId(333812512203L);
            l_FotypeOrderAllParams3.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams3);
            
            //訂正
            HostFotypeOrderAllParams l_FotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams4.setCorpCode("001");
            l_FotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams4.setRequestCode("EI804");
            l_FotypeOrderAllParams4.setCancelDiv("0");
            l_FotypeOrderAllParams4.setStatus("1");
            l_FotypeOrderAllParams4.setInstitutionCode("0D");
            l_FotypeOrderAllParams4.setAccountId(333812512203L);
            l_FotypeOrderAllParams4.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams4);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setAccountId(333812512203L);
            l_IfoOrderUnitParams2.setOrderRequestNumber("01");
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams2.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);

            HostFotypeOrderAllParams l_FotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams5.setCorpCode("003");
            l_FotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams5.setRequestCode("EI804");
            l_FotypeOrderAllParams5.setCancelDiv("0");
            l_FotypeOrderAllParams5.setStatus("1");
            l_FotypeOrderAllParams5.setInstitutionCode("0D");
            l_FotypeOrderAllParams5.setAccountId(333812512203L);
            l_FotypeOrderAllParams5.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams5);

            HostFotypeOrderAllParams l_FotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams6.setCorpCode("004");
            l_FotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams6.setRequestCode("EI804");
            l_FotypeOrderAllParams6.setCancelDiv("0");
            l_FotypeOrderAllParams6.setStatus("1");
            l_FotypeOrderAllParams6.setInstitutionCode("0D");
            l_FotypeOrderAllParams6.setAccountId(333812512203L);
            l_FotypeOrderAllParams6.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams6);

            
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            Date l_datBaseTime = WEB3DateUtility.getDate("2010/10/10 00:00:10", WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            HostFotypeOrderAllRow[] l_notOrderedAlls = l_impl.getNotOrdered(l_institution, l_datBaseTime, "001");
            assertEquals("3", l_notOrderedAlls.length+"");
            assertEquals("002", l_notOrderedAlls[0].getCorpCode());
            assertEquals("004", l_notOrderedAlls[1].getCorpCode());
            assertEquals("003", l_notOrderedAlls[2].getCorpCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetNotOrderedCase2()
    {
        final String STR_METHOD_NAME = "testGetNotOrderedCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
   
            //取消
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("001");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI802");
            l_FotypeOrderAllParams1.setCancelDiv("1");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("002");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI802");
            l_FotypeOrderAllParams2.setCancelDiv("1");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams3.setCorpCode("003");
            l_FotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:15",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams3.setRequestCode("EI802");
            l_FotypeOrderAllParams3.setCancelDiv("1");
            l_FotypeOrderAllParams3.setStatus("1");
            l_FotypeOrderAllParams3.setInstitutionCode("0D");
            l_FotypeOrderAllParams3.setAccountId(333812512203L);
            l_FotypeOrderAllParams3.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams3);
            
            //訂正
            HostFotypeOrderAllParams l_FotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams4.setCorpCode("004");
            l_FotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:15",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams4.setRequestCode("EI804");
            l_FotypeOrderAllParams4.setCancelDiv("0");
            l_FotypeOrderAllParams4.setStatus("1");
            l_FotypeOrderAllParams4.setInstitutionCode("0D");
            l_FotypeOrderAllParams4.setAccountId(333812512203L);
            l_FotypeOrderAllParams4.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams4);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setAccountId(333812512203L);
            l_IfoOrderUnitParams2.setOrderRequestNumber("01");
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams2.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);

            HostFotypeOrderAllParams l_FotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams5.setCorpCode("005");
            l_FotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams5.setRequestCode("EI804");
            l_FotypeOrderAllParams5.setCancelDiv("0");
            l_FotypeOrderAllParams5.setStatus("1");
            l_FotypeOrderAllParams5.setInstitutionCode("0D");
            l_FotypeOrderAllParams5.setAccountId(333812512203L);
            l_FotypeOrderAllParams5.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams5);

            HostFotypeOrderAllParams l_FotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams6.setCorpCode("001");
            l_FotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams6.setRequestCode("EI804");
            l_FotypeOrderAllParams6.setCancelDiv("0");
            l_FotypeOrderAllParams6.setStatus("1");
            l_FotypeOrderAllParams6.setInstitutionCode("0D");
            l_FotypeOrderAllParams6.setAccountId(333812512203L);
            l_FotypeOrderAllParams6.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams6);

            
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            Date l_datBaseTime = WEB3DateUtility.getDate("2010/10/10 00:00:10", WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            HostFotypeOrderAllRow[] l_notOrderedAlls = l_impl.getNotOrdered(l_institution, l_datBaseTime, "001");
            assertEquals("4", l_notOrderedAlls.length+"");
            assertEquals("003", l_notOrderedAlls[0].getCorpCode());
            assertEquals("002", l_notOrderedAlls[1].getCorpCode());
            assertEquals("004", l_notOrderedAlls[2].getCorpCode());
            assertEquals("005", l_notOrderedAlls[3].getCorpCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNotOrderedCase3()
    {
        final String STR_METHOD_NAME = "testGetNotOrderedCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
   
            //取消
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("001");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:11",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI802");
            l_FotypeOrderAllParams1.setCancelDiv("1");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("002");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI802");
            l_FotypeOrderAllParams2.setCancelDiv("1");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams3.setCorpCode("003");
            l_FotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:15",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams3.setRequestCode("EI802");
            l_FotypeOrderAllParams3.setCancelDiv("1");
            l_FotypeOrderAllParams3.setStatus("1");
            l_FotypeOrderAllParams3.setInstitutionCode("0D");
            l_FotypeOrderAllParams3.setAccountId(333812512203L);
            l_FotypeOrderAllParams3.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams3);
            
            //新規
            HostFotypeOrderAllParams l_FotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams4.setCorpCode("004");
            l_FotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams4.setRequestCode("EI803");
            l_FotypeOrderAllParams4.setCancelDiv("0");
            l_FotypeOrderAllParams4.setStatus("1");
            l_FotypeOrderAllParams4.setInstitutionCode("0D");
            l_FotypeOrderAllParams4.setAccountId(333812512203L);
            l_FotypeOrderAllParams4.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams4);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setAccountId(333812512203L);
            l_IfoOrderUnitParams2.setOrderRequestNumber("01");
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams2.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);

            HostFotypeOrderAllParams l_FotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams5.setCorpCode("005");
            l_FotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams5.setRequestCode("EI803");
            l_FotypeOrderAllParams5.setCancelDiv("0");
            l_FotypeOrderAllParams5.setStatus("1");
            l_FotypeOrderAllParams5.setInstitutionCode("0D");
            l_FotypeOrderAllParams5.setAccountId(333812512203L);
            l_FotypeOrderAllParams5.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams5);

            HostFotypeOrderAllParams l_FotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams6.setCorpCode("006");
            l_FotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:15",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams6.setRequestCode("EI803");
            l_FotypeOrderAllParams6.setCancelDiv("0");
            l_FotypeOrderAllParams6.setStatus("1");
            l_FotypeOrderAllParams6.setInstitutionCode("0D");
            l_FotypeOrderAllParams6.setAccountId(333812512203L);
            l_FotypeOrderAllParams6.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams6);

            
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            Date l_datBaseTime = WEB3DateUtility.getDate("2010/10/10 00:00:10", WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            HostFotypeOrderAllRow[] l_notOrderedAlls = l_impl.getNotOrdered(l_institution, l_datBaseTime, "001");
            assertEquals("6", l_notOrderedAlls.length+"");
            assertEquals("004", l_notOrderedAlls[0].getCorpCode());
            assertEquals("001", l_notOrderedAlls[1].getCorpCode());
            assertEquals("006", l_notOrderedAlls[2].getCorpCode());
            assertEquals("003", l_notOrderedAlls[3].getCorpCode());
            assertEquals("002", l_notOrderedAlls[4].getCorpCode());
            assertEquals("005", l_notOrderedAlls[5].getCorpCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNotOrderedCase4()
    {
        final String STR_METHOD_NAME = "testGetNotOrderedCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
   
            //取消
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("001");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI802");
            l_FotypeOrderAllParams1.setCancelDiv("1");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("001");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI802");
            l_FotypeOrderAllParams2.setCancelDiv("1");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams3.setCorpCode("001");
            l_FotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams3.setRequestCode("EI802");
            l_FotypeOrderAllParams3.setCancelDiv("1");
            l_FotypeOrderAllParams3.setStatus("1");
            l_FotypeOrderAllParams3.setInstitutionCode("0D");
            l_FotypeOrderAllParams3.setAccountId(333812512203L);
            l_FotypeOrderAllParams3.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams3);
            
            //新規
            HostFotypeOrderAllParams l_FotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams4.setCorpCode("001");
            l_FotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams4.setRequestCode("EI803");
            l_FotypeOrderAllParams4.setCancelDiv("0");
            l_FotypeOrderAllParams4.setStatus("1");
            l_FotypeOrderAllParams4.setInstitutionCode("0D");
            l_FotypeOrderAllParams4.setAccountId(333812512203L);
            l_FotypeOrderAllParams4.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams4);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setAccountId(333812512203L);
            l_IfoOrderUnitParams2.setOrderRequestNumber("01");
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams2.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);

            HostFotypeOrderAllParams l_FotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams5.setCorpCode("001");
            l_FotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams5.setRequestCode("EI803");
            l_FotypeOrderAllParams5.setCancelDiv("0");
            l_FotypeOrderAllParams5.setStatus("1");
            l_FotypeOrderAllParams5.setInstitutionCode("0D");
            l_FotypeOrderAllParams5.setAccountId(333812512203L);
            l_FotypeOrderAllParams5.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams5);

            HostFotypeOrderAllParams l_FotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams6.setCorpCode("001");
            l_FotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams6.setRequestCode("EI803");
            l_FotypeOrderAllParams6.setCancelDiv("0");
            l_FotypeOrderAllParams6.setStatus("1");
            l_FotypeOrderAllParams6.setInstitutionCode("0D");
            l_FotypeOrderAllParams6.setAccountId(333812512203L);
            l_FotypeOrderAllParams6.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams6);

            //訂正
            HostFotypeOrderAllParams l_FotypeOrderAllParams7 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams7.setCorpCode("004");
            l_FotypeOrderAllParams7.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:15",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams7.setRequestCode("EI804");
            l_FotypeOrderAllParams7.setCancelDiv("0");
            l_FotypeOrderAllParams7.setStatus("1");
            l_FotypeOrderAllParams7.setInstitutionCode("0D");
            l_FotypeOrderAllParams7.setAccountId(333812512203L);
            l_FotypeOrderAllParams7.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams7);
            
            IfoOrderUnitParams l_IfoOrderUnitParams3 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams3.setAccountId(333812512203L);
            l_IfoOrderUnitParams3.setOrderRequestNumber("01");
            l_IfoOrderUnitParams3.setOrderUnitId(1002L);
            l_IfoOrderUnitParams3.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams3.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams3);

            HostFotypeOrderAllParams l_FotypeOrderAllParams8 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams8.setCorpCode("005");
            l_FotypeOrderAllParams8.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams8.setRequestCode("EI804");
            l_FotypeOrderAllParams8.setCancelDiv("0");
            l_FotypeOrderAllParams8.setStatus("1");
            l_FotypeOrderAllParams8.setInstitutionCode("0D");
            l_FotypeOrderAllParams8.setAccountId(333812512203L);
            l_FotypeOrderAllParams8.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams8);

            HostFotypeOrderAllParams l_FotypeOrderAllParams9 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams9.setCorpCode("006");
            l_FotypeOrderAllParams9.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/20 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams9.setRequestCode("EI804");
            l_FotypeOrderAllParams9.setCancelDiv("0");
            l_FotypeOrderAllParams9.setStatus("1");
            l_FotypeOrderAllParams9.setInstitutionCode("0D");
            l_FotypeOrderAllParams9.setAccountId(333812512203L);
            l_FotypeOrderAllParams9.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams9);
            
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            Date l_datBaseTime = WEB3DateUtility.getDate("2010/10/10 00:00:10", WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            HostFotypeOrderAllRow[] l_notOrderedAlls = l_impl.getNotOrdered(l_institution, l_datBaseTime, "001");
            assertEquals("3", l_notOrderedAlls.length+"");
            assertEquals("004", l_notOrderedAlls[0].getCorpCode());
            assertEquals("006", l_notOrderedAlls[1].getCorpCode());
            assertEquals("005", l_notOrderedAlls[2].getCorpCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNotOrderedCase5()
    {
        final String STR_METHOD_NAME = "testGetNotOrderedCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
   
            //取消
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("004");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:30",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI802");
            l_FotypeOrderAllParams1.setCancelDiv("1");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("005");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:12",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI802");
            l_FotypeOrderAllParams2.setCancelDiv("1");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams3.setCorpCode("006");
            l_FotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/11 00:00:09",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams3.setRequestCode("EI802");
            l_FotypeOrderAllParams3.setCancelDiv("1");
            l_FotypeOrderAllParams3.setStatus("1");
            l_FotypeOrderAllParams3.setInstitutionCode("0D");
            l_FotypeOrderAllParams3.setAccountId(333812512203L);
            l_FotypeOrderAllParams3.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams3);
            
            //新規
            HostFotypeOrderAllParams l_FotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams4.setCorpCode("001");
            l_FotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:11",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams4.setRequestCode("EI803");
            l_FotypeOrderAllParams4.setCancelDiv("0");
            l_FotypeOrderAllParams4.setStatus("1");
            l_FotypeOrderAllParams4.setInstitutionCode("0D");
            l_FotypeOrderAllParams4.setAccountId(333812512203L);
            l_FotypeOrderAllParams4.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams4);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setAccountId(333812512203L);
            l_IfoOrderUnitParams2.setOrderRequestNumber("01");
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams2.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);

            HostFotypeOrderAllParams l_FotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams5.setCorpCode("002");
            l_FotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams5.setRequestCode("EI803");
            l_FotypeOrderAllParams5.setCancelDiv("0");
            l_FotypeOrderAllParams5.setStatus("1");
            l_FotypeOrderAllParams5.setInstitutionCode("0D");
            l_FotypeOrderAllParams5.setAccountId(333812512203L);
            l_FotypeOrderAllParams5.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams5);

            HostFotypeOrderAllParams l_FotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams6.setCorpCode("003");
            l_FotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams6.setRequestCode("EI803");
            l_FotypeOrderAllParams6.setCancelDiv("0");
            l_FotypeOrderAllParams6.setStatus("1");
            l_FotypeOrderAllParams6.setInstitutionCode("0D");
            l_FotypeOrderAllParams6.setAccountId(333812512203L);
            l_FotypeOrderAllParams6.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams6);

            //訂正
            HostFotypeOrderAllParams l_FotypeOrderAllParams7 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams7.setCorpCode("007");
            l_FotypeOrderAllParams7.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:15",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams7.setRequestCode("EI804");
            l_FotypeOrderAllParams7.setCancelDiv("0");
            l_FotypeOrderAllParams7.setStatus("1");
            l_FotypeOrderAllParams7.setInstitutionCode("0D");
            l_FotypeOrderAllParams7.setAccountId(333812512203L);
            l_FotypeOrderAllParams7.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams7);
            
            IfoOrderUnitParams l_IfoOrderUnitParams3 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams3.setAccountId(333812512203L);
            l_IfoOrderUnitParams3.setOrderRequestNumber("01");
            l_IfoOrderUnitParams3.setOrderUnitId(1002L);
            l_IfoOrderUnitParams3.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams3.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams3);

            HostFotypeOrderAllParams l_FotypeOrderAllParams8 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams8.setCorpCode("008");
            l_FotypeOrderAllParams8.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/30 00:00:20",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams8.setRequestCode("EI804");
            l_FotypeOrderAllParams8.setCancelDiv("0");
            l_FotypeOrderAllParams8.setStatus("1");
            l_FotypeOrderAllParams8.setInstitutionCode("0D");
            l_FotypeOrderAllParams8.setAccountId(333812512203L);
            l_FotypeOrderAllParams8.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams8);

            HostFotypeOrderAllParams l_FotypeOrderAllParams9 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams9.setCorpCode("009");
            l_FotypeOrderAllParams9.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams9.setRequestCode("EI804");
            l_FotypeOrderAllParams9.setCancelDiv("0");
            l_FotypeOrderAllParams9.setStatus("1");
            l_FotypeOrderAllParams9.setInstitutionCode("0D");
            l_FotypeOrderAllParams9.setAccountId(333812512203L);
            l_FotypeOrderAllParams9.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams9);
            
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            Date l_datBaseTime = WEB3DateUtility.getDate("2010/10/10 00:00:10", WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            HostFotypeOrderAllRow[] l_notOrderedAlls = l_impl.getNotOrdered(l_institution, l_datBaseTime, "001");
            assertEquals("9", l_notOrderedAlls.length+"");
            assertEquals("002", l_notOrderedAlls[0].getCorpCode());
            assertEquals("001", l_notOrderedAlls[1].getCorpCode());
            assertEquals("005", l_notOrderedAlls[2].getCorpCode());
            assertEquals("003", l_notOrderedAlls[3].getCorpCode());
            assertEquals("004", l_notOrderedAlls[4].getCorpCode());
            assertEquals("006", l_notOrderedAlls[5].getCorpCode());
            assertEquals("009", l_notOrderedAlls[6].getCorpCode());
            assertEquals("007", l_notOrderedAlls[7].getCorpCode());
            assertEquals("008", l_notOrderedAlls[8].getCorpCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNotOrderedCase6()
    {
        final String STR_METHOD_NAME = "testGetNotOrderedCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
   
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            Date l_datBaseTime = WEB3DateUtility.getDate("2010/10/10 00:00:10", WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            HostFotypeOrderAllRow[] l_notOrderedAlls = l_impl.getNotOrdered(l_institution, l_datBaseTime, "001");
            assertEquals("0", l_notOrderedAlls.length+"");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNotOrderedCase7()
    {
        final String STR_METHOD_NAME = "testGetNotOrderedCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl l_impl = new WEB3AdminIfoFailureOrderInTroubleDownloadServiceImpl();
   
            //取消
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            HostFotypeOrderAllParams l_FotypeOrderAllParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams1.setCorpCode("001");
            l_FotypeOrderAllParams1.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams1.setRequestCode("EI802");
            l_FotypeOrderAllParams1.setCancelDiv("1");
            l_FotypeOrderAllParams1.setStatus("1");
            l_FotypeOrderAllParams1.setInstitutionCode("0D");
            l_FotypeOrderAllParams1.setAccountId(333812512203L);
            l_FotypeOrderAllParams1.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_IfoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams1.setAccountId(333812512203L);
            l_IfoOrderUnitParams1.setOrderRequestNumber("00");
            l_IfoOrderUnitParams1.setOrderUnitId(1001L);
            l_IfoOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams1.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams1);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams2 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams2.setCorpCode("001");
            l_FotypeOrderAllParams2.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams2.setRequestCode("EI802");
            l_FotypeOrderAllParams2.setCancelDiv("1");
            l_FotypeOrderAllParams2.setStatus("1");
            l_FotypeOrderAllParams2.setInstitutionCode("0D");
            l_FotypeOrderAllParams2.setAccountId(333812512203L);
            l_FotypeOrderAllParams2.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams2);
            
            HostFotypeOrderAllParams l_FotypeOrderAllParams3 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams3.setCorpCode("001");
            l_FotypeOrderAllParams3.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams3.setRequestCode("EI802");
            l_FotypeOrderAllParams3.setCancelDiv("1");
            l_FotypeOrderAllParams3.setStatus("1");
            l_FotypeOrderAllParams3.setInstitutionCode("0D");
            l_FotypeOrderAllParams3.setAccountId(333812512203L);
            l_FotypeOrderAllParams3.setOrderRequestNumber("00");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams3);
            
            //新規
            HostFotypeOrderAllParams l_FotypeOrderAllParams4 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams4.setCorpCode("001");
            l_FotypeOrderAllParams4.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams4.setRequestCode("EI803");
            l_FotypeOrderAllParams4.setCancelDiv("0");
            l_FotypeOrderAllParams4.setStatus("1");
            l_FotypeOrderAllParams4.setInstitutionCode("0D");
            l_FotypeOrderAllParams4.setAccountId(333812512203L);
            l_FotypeOrderAllParams4.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams4);
            
            IfoOrderUnitParams l_IfoOrderUnitParams2 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams2.setAccountId(333812512203L);
            l_IfoOrderUnitParams2.setOrderRequestNumber("01");
            l_IfoOrderUnitParams2.setOrderUnitId(1002L);
            l_IfoOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams2.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams2);

            HostFotypeOrderAllParams l_FotypeOrderAllParams5 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams5.setCorpCode("001");
            l_FotypeOrderAllParams5.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams5.setRequestCode("EI803");
            l_FotypeOrderAllParams5.setCancelDiv("0");
            l_FotypeOrderAllParams5.setStatus("1");
            l_FotypeOrderAllParams5.setInstitutionCode("0D");
            l_FotypeOrderAllParams5.setAccountId(333812512203L);
            l_FotypeOrderAllParams5.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams5);

            HostFotypeOrderAllParams l_FotypeOrderAllParams6 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams6.setCorpCode("001");
            l_FotypeOrderAllParams6.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams6.setRequestCode("EI803");
            l_FotypeOrderAllParams6.setCancelDiv("0");
            l_FotypeOrderAllParams6.setStatus("1");
            l_FotypeOrderAllParams6.setInstitutionCode("0D");
            l_FotypeOrderAllParams6.setAccountId(333812512203L);
            l_FotypeOrderAllParams6.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams6);

            //訂正
            HostFotypeOrderAllParams l_FotypeOrderAllParams7 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams7.setCorpCode("001");
            l_FotypeOrderAllParams7.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams7.setRequestCode("EI804");
            l_FotypeOrderAllParams7.setCancelDiv("0");
            l_FotypeOrderAllParams7.setStatus("1");
            l_FotypeOrderAllParams7.setInstitutionCode("0D");
            l_FotypeOrderAllParams7.setAccountId(333812512203L);
            l_FotypeOrderAllParams7.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams7);
            
            IfoOrderUnitParams l_IfoOrderUnitParams3 = TestDBUtility.getIfoOrderUnitRow();
            l_IfoOrderUnitParams3.setAccountId(333812512203L);
            l_IfoOrderUnitParams3.setOrderRequestNumber("01");
            l_IfoOrderUnitParams3.setOrderUnitId(1002L);
            l_IfoOrderUnitParams3.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_IfoOrderUnitParams3.setRequestType("2");
            TestDBUtility.insertWithDel(l_IfoOrderUnitParams3);

            HostFotypeOrderAllParams l_FotypeOrderAllParams8 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams8.setCorpCode("001");
            l_FotypeOrderAllParams8.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams8.setRequestCode("EI804");
            l_FotypeOrderAllParams8.setCancelDiv("0");
            l_FotypeOrderAllParams8.setStatus("1");
            l_FotypeOrderAllParams8.setInstitutionCode("0D");
            l_FotypeOrderAllParams8.setAccountId(333812512203L);
            l_FotypeOrderAllParams8.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams8);

            HostFotypeOrderAllParams l_FotypeOrderAllParams9 = TestDBUtility.getHostFotypeOrderAllRow();
            l_FotypeOrderAllParams9.setCorpCode("001");
            l_FotypeOrderAllParams9.setReceivedDateTime(WEB3DateUtility.getDate("2010/10/10 00:00:10",
                    WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS));
            l_FotypeOrderAllParams9.setRequestCode("EI804");
            l_FotypeOrderAllParams9.setCancelDiv("0");
            l_FotypeOrderAllParams9.setStatus("1");
            l_FotypeOrderAllParams9.setInstitutionCode("0D");
            l_FotypeOrderAllParams9.setAccountId(333812512203L);
            l_FotypeOrderAllParams9.setOrderRequestNumber("01");
            TestDBUtility.insertWithDel(l_FotypeOrderAllParams9);
            
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(11L);
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            Date l_datBaseTime = WEB3DateUtility.getDate("2010/10/10 00:00:10", WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            HostFotypeOrderAllRow[] l_notOrderedAlls = l_impl.getNotOrdered(l_institution, l_datBaseTime, "001");
            assertEquals("0", l_notOrderedAlls.length+"");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
