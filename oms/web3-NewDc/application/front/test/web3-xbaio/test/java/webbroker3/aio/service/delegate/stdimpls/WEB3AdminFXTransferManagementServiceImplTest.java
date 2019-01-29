head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXTransferManagementServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替管理サービスImplのテスククラス(WEB3AdminFXTransferManagementServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/10 周墨洋 (中訊) 新規作成 仕様変更・モデルNo.254,モデルNo.255
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListResponse;
import webbroker3.aio.message.WEB3FXSearchConditionUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FxTransStatusOperationDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX振替管理サービスImpl)<BR>
 * FX振替管理サービスImplのテスククラス<BR>
 * <BR>
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementServiceImplTest extends
        TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementServiceImplTest.class);

    /**
     * FX振替管理サービスImpl
     */
    WEB3AdminFXTransferManagementServiceImpl l_adminFXTransferManagementServiceImpl = null;

    /**
     * @@param arg0
     */
    public WEB3AdminFXTransferManagementServiceImplTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        Services.overrideService(OpLoginSecurityService.class, new OpLoginSecurityServiceImplForMock());
        Services.overrideService(OpLoginAdminService.class, new OpLoginAdminServiceImplForMock());
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testsubmitCancel_T001()
    {
        final String STR_METHOD_NAME = "testsubmitCancel_T001";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXTransferManagementServiceImpl l_impl = new WEB3AdminFXTransferManagementServiceImpl();
        
        WEB3AdminFXTransferCancelCompleteRequest l_request = new WEB3AdminFXTransferCancelCompleteRequest();
        
        WEB3FXSearchConditionUnit[] fxSearchConditionList = new WEB3FXSearchConditionUnit[1];
        
        fxSearchConditionList[0] = new WEB3FXSearchConditionUnit();
        fxSearchConditionList[0].branchCode = "381";
        fxSearchConditionList[0].requestNumber = "1980003";
        
            
        l_request.fxSearchConditionList = fxSearchConditionList;
        
        
        
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0DD");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
    
            
            
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            //          AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);


            //TestDBUtility.insertWithDel(l_adminPermissionParams);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            
            
            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_gftTransferStatusParams.setBranchCode("381");
            
            l_gftTransferStatusParams.setTransferStatusDiv("2");
            l_gftTransferStatusParams.setOrderRequestNumber("1980003");
            l_gftTransferStatusParams.setResultCode(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801);
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setOperationDiv("01");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));

            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
           
        
            WEB3AdminFXTransferCancelCompleteResponse l_response = 
                l_impl.submitCancel(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987,l_ex.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
        
        
    }
    public void testsubmitCancel_T002()
    {
        final String STR_METHOD_NAME = "testsubmitCancel_T002";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXTransferManagementServiceImpl l_impl = new WEB3AdminFXTransferManagementServiceImpl();
        
        WEB3AdminFXTransferCancelCompleteRequest l_request = new WEB3AdminFXTransferCancelCompleteRequest();
        
        WEB3FXSearchConditionUnit[] fxSearchConditionList = new WEB3FXSearchConditionUnit[1];
        
        fxSearchConditionList[0] = new WEB3FXSearchConditionUnit();
        fxSearchConditionList[0].branchCode = "381";
        fxSearchConditionList[0].requestNumber = "1980003";
        
            
        l_request.fxSearchConditionList = fxSearchConditionList;
        
        
        
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0DD");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
    
            
            
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            //          AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FX_TRANSFER_MANAGE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);


            //TestDBUtility.insertWithDel(l_adminPermissionParams);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            
            
            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();

            l_gftTransferStatusParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_gftTransferStatusParams.setBranchCode("381");
            
            l_gftTransferStatusParams.setTransferStatusDiv("0");
            l_gftTransferStatusParams.setOrderRequestNumber("1980003");
            l_gftTransferStatusParams.setResultCode(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990);
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setOperationDiv("01");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

        
            WEB3AdminFXTransferCancelCompleteResponse l_response = 
                l_impl.submitCancel(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987,l_ex.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);

    }
    
 
    
    /**
     * testGetListScreen_case0001
     */
    public void testGetListScreen_case0001()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {       
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0402");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000801");
            
            l_gftTransferStatusParams.setFxSystemCode("1");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImplForTest();
            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferListRequest();

            String[] l_strBranchCodeList = new String[]{l_mainAccountParams.getBranchCode()};
            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
            l_adminFXTransferListRequest.pageIndex = "1";
            l_adminFXTransferListRequest.pageSize = "10";
            l_adminFXTransferListRequest.statusDiv = "1";

            l_adminFXTransferListRequest.fxSystemCode = "1";
            
            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);

            assertNotNull(l_adminFXTransferListResponse);
            assertEquals(1, l_adminFXTransferListResponse.fxTransferDetailList.length);
            assertTrue(!l_adminFXTransferListResponse.fxTransferDetailList[0].selectableFlag);
            assertEquals(l_adminFXTransferListResponse.fxTransferDetailList[0].fxSystemCode,"1");
            assertEquals(l_adminFXTransferListResponse.accountCode,null);
            assertEquals(l_adminFXTransferListResponse.statusDiv,"1");
            
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(l_exBE.getMessage(), l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    public void testGetListScreen_case0002()
//    {
//        final String STR_METHOD_NAME = " testGetListScreen_case0002()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            //AdministratorParams
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//
//            WEB3Administrator l_administrator =
//                new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//
//            // AdminPermissionParams
//            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminPermissionParams.setTransactionCategory("B0402");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            //AdministratorTypeParams
//            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
//            AdministratorTypeParams l_administratorTypeParams =
//                TestDBUtility.getAdministratorTypeRow();
//            l_administratorTypeParams.setInstitutionCode("0D");
//            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.insertWithDel(l_administratorTypeParams);
//
//            // GftTransferStatusParams
//            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
//            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
//            l_gftTransferStatusParams.setInstitutionCode("0D");
//            l_gftTransferStatusParams.setBranchCode("624");
//            l_gftTransferStatusParams.setAccountCode("123456");
//            l_gftTransferStatusParams.setReceiveTime("20070913101112");
//            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
//            l_gftTransferStatusParams.setTransferStatusDiv("2");
//            l_gftTransferStatusParams.setResultCode("00000990");
//            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
//
//            // MainAccountParams
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setBranchCode("624");
//            l_mainAccountParams.setAccountCode("123456");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            l_adminFXTransferManagementServiceImpl =
//                new WEB3AdminFXTransferManagementServiceImplForTest();
//            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
//                new WEB3AdminFXTransferListRequest();
//
//            String[] l_strBranchCodeList = new String[]{l_mainAccountParams.getBranchCode()};
//            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
//            l_adminFXTransferListRequest.pageIndex = "1";
//            l_adminFXTransferListRequest.pageSize = "10";
//            l_adminFXTransferListRequest.statusDiv = "1";
//
//            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
//                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);
//
//            assertNotNull(l_adminFXTransferListResponse);
//            assertEquals(1, l_adminFXTransferListResponse.fxTransferDetailList.length);
//            assertTrue(l_adminFXTransferListResponse.fxTransferDetailList[0].selectableFlag);
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            log.error(l_exBE.getMessage(), l_exBE);
//            fail();
//        }
//        catch (Exception l_exE)
//        {
//            log.error(l_exE.getMessage(), l_exE);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                TestDBUtility.deleteAll(AdministratorParams.TYPE);
//                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//                TestDBUtility.deleteAll(MainAccountParams.TYPE);
//                TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
//            }
//            catch (Exception l_exE)
//            {
//                log.error(l_exE.getMessage(), l_exE);
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }

    public void testGetListScreen_C0001()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {       
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0402");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000801");
            
            l_gftTransferStatusParams.setFxSystemCode("1");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImplForTest();
            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferListRequest();

            String[] l_strBranchCodeList = new String[]{l_mainAccountParams.getBranchCode()};
            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
            l_adminFXTransferListRequest.pageIndex = "1";
            l_adminFXTransferListRequest.pageSize = "10";
            l_adminFXTransferListRequest.statusDiv = "1";

            l_adminFXTransferListRequest.fxSystemCode = "1";
            l_adminFXTransferListRequest.transferDate = null;

            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);

            assertNotNull(l_adminFXTransferListResponse);
            assertNull(l_adminFXTransferListResponse.fxTotalDepositToGuaranty);
            assertNull(l_adminFXTransferListResponse.fxTotalGuarantyToDeposit);
            assertNull(l_adminFXTransferListResponse.fxTransferTotal);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
//
//    public void testGetListScreen_C0002()
//    {
//        final String STR_METHOD_NAME = " testGetListScreen_case0002()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {       
//            //AdministratorParams
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setLoginId(0);
//            l_administratorParams.setInstitutionId(123);
//            TestDBUtility.insertWithDel(l_administratorParams);
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(123);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            LoginInfo l_loginInfo = new LoginInfoImpl();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getLoginInfo",
//                new Class[] {},
//                l_loginInfo);
//
//            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams =
//            	TestDBUtility.getInstitutionPreferencesRow();
//            l_institutionPreferencesParams.setInstitutionId(123);
//            l_institutionPreferencesParams.setName("fx.cfd.summary.calculation.div");
//            l_institutionPreferencesParams.setNameSerialNo(2);
//            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
//
//            WEB3Administrator l_administrator =
//                new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//
//            // AdminPermissionParams
//            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminPermissionParams.setTransactionCategory("B0402");
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//
//            //AdministratorTypeParams
//            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
//            AdministratorTypeParams l_administratorTypeParams =
//                TestDBUtility.getAdministratorTypeRow();
//            l_administratorTypeParams.setInstitutionCode("0D");
//            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.insertWithDel(l_administratorTypeParams);
//
//            // GftTransferStatusParams
//            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
//            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
//            l_gftTransferStatusParams.setInstitutionCode("0D");
//            l_gftTransferStatusParams.setBranchCode("624");
//            l_gftTransferStatusParams.setAccountCode("123456");
//            l_gftTransferStatusParams.setReceiveTime("20070913101112");
//            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
//            l_gftTransferStatusParams.setTransferStatusDiv("1");
//            l_gftTransferStatusParams.setResultCode("00000801");
//            
//            l_gftTransferStatusParams.setFxSystemCode("1");
//            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
//
//            // MainAccountParams
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setBranchCode("624");
//            l_mainAccountParams.setAccountCode("123456");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            l_adminFXTransferManagementServiceImpl =
//                new WEB3AdminFXTransferManagementServiceImplForTest();
//            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
//                new WEB3AdminFXTransferListRequest();
//
//            String[] l_strBranchCodeList = new String[]{l_mainAccountParams.getBranchCode()};
//            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
//            l_adminFXTransferListRequest.pageIndex = "1";
//            l_adminFXTransferListRequest.pageSize = "10";
//            l_adminFXTransferListRequest.statusDiv = "1";
//
//            l_adminFXTransferListRequest.fxSystemCode = "1";
//            l_adminFXTransferListRequest.transferDate =
//            	WEB3DateUtility.getDate("20080710", "yyyymmdd");
//
//            l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);
//
//            fail();
////            assertNotNull(l_adminFXTransferListResponse);
////            assertNull(l_adminFXTransferListResponse.fxTotalDepositToGuaranty);
////            assertNull(l_adminFXTransferListResponse.fxTotalGuarantyToDeposit);
////            assertNull(l_adminFXTransferListResponse.fxTransferTotal);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }

    public void testGetListScreen_C0003()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case0003()";
        log.entering(STR_METHOD_NAME);

        try
        {       
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
            	TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(123);
            l_institutionPreferencesParams.setName("fx.cfd.summary.calculation.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0402");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000801");
            
            l_gftTransferStatusParams.setFxSystemCode("1");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImplForTest();
            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferListRequest();

            String[] l_strBranchCodeList = new String[]{l_mainAccountParams.getBranchCode()};
            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
            l_adminFXTransferListRequest.pageIndex = "1";
            l_adminFXTransferListRequest.pageSize = "10";
            l_adminFXTransferListRequest.statusDiv = "1";

            l_adminFXTransferListRequest.fxSystemCode = "1";
            l_adminFXTransferListRequest.transferDate =
            	WEB3DateUtility.getDate("20080710", "yyyymmdd");

            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);

            assertNotNull(l_adminFXTransferListResponse);
            assertNull(l_adminFXTransferListResponse.fxTotalDepositToGuaranty);
            assertNull(l_adminFXTransferListResponse.fxTotalGuarantyToDeposit);
            assertNull(l_adminFXTransferListResponse.fxTransferTotal);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_C0004()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case0004()";
        log.entering(STR_METHOD_NAME);

        try
        {       
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
            	TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(123);
            l_institutionPreferencesParams.setName("fx.cfd.summary.calculation.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0402");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000801");
            l_gftTransferStatusParams.setSendRcvDiv("2");
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImplForTest();
            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferListRequest();

            String[] l_strBranchCodeList = new String[]{"624"};
            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
            l_adminFXTransferListRequest.accountCode = null;
            l_adminFXTransferListRequest.fxTransferDiv = null;
            l_adminFXTransferListRequest.receiptDateFrom = null;
            l_adminFXTransferListRequest.receiptDateTo = null;
            l_adminFXTransferListRequest.statusDiv = "1";
            l_adminFXTransferListRequest.fxSystemCode = null;
            l_adminFXTransferListRequest.pageIndex = "1";
            l_adminFXTransferListRequest.pageSize = "10";
            l_adminFXTransferListRequest.transferDate =
            	WEB3DateUtility.getDate("20080710", "yyyymmdd");

            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);

            assertNotNull(l_adminFXTransferListResponse);
            assertNull(l_adminFXTransferListResponse.fxTotalDepositToGuaranty);
            assertNull(l_adminFXTransferListResponse.fxTotalGuarantyToDeposit);
            assertNull(l_adminFXTransferListResponse.fxTransferTotal);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_C0005()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {       
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
            	TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(123);
            l_institutionPreferencesParams.setName("fx.cfd.summary.calculation.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0402");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusRow.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setOrderRequestNumber("01");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000000");
            l_gftTransferStatusParams.setSendRcvDiv("2");
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setOperationDiv("03");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setTransferDate("20080110");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000000");
            l_gftTransferStatusParams.setOrderRequestNumber("02");
            l_gftTransferStatusParams.setSendRcvDiv("2");
            l_gftTransferStatusParams.setTransferDate("20080110");
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setOperationDiv("03");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImplForTest();
            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferListRequest();

            String[] l_strBranchCodeList = new String[]{"624"};
            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
            l_adminFXTransferListRequest.accountCode = null;
            l_adminFXTransferListRequest.fxTransferDiv = null;
            l_adminFXTransferListRequest.receiptDateFrom = null;
            l_adminFXTransferListRequest.receiptDateTo = null;
            l_adminFXTransferListRequest.statusDiv = "1";
            l_adminFXTransferListRequest.fxSystemCode = null;
            l_adminFXTransferListRequest.pageIndex = "1";
            l_adminFXTransferListRequest.pageSize = "10";
            l_adminFXTransferListRequest.transferDate =
            	WEB3DateUtility.getDate("20080710", "yyyymmdd");

            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);

            assertNotNull(l_adminFXTransferListResponse);
            assertEquals(l_adminFXTransferListResponse.fxTotalDepositToGuaranty, "0");
            assertEquals(l_adminFXTransferListResponse.fxTotalGuarantyToDeposit, "0");
            assertEquals(l_adminFXTransferListResponse.fxTransferTotal, "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_C0006()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {       
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
            	TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(123);
            l_institutionPreferencesParams.setName("fx.cfd.summary.calculation.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0402");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000000");
            l_gftTransferStatusParams.setSendRcvDiv("2");
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setOperationDiv("01");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setOrderRequestNumber("01");
            l_gftTransferStatusParams.setAmount(100);
            l_gftTransferStatusParams.setTransferDate("20080110");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000000");
            l_gftTransferStatusParams.setSendRcvDiv("2");
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setOrderRequestNumber("02");
            l_gftTransferStatusParams.setOperationDiv("01");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setAmount(100);
            l_gftTransferStatusParams.setTransferDate("20080110");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImplForTest();
            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferListRequest();

            String[] l_strBranchCodeList = new String[]{"624"};
            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
            l_adminFXTransferListRequest.accountCode = null;
            l_adminFXTransferListRequest.fxTransferDiv = null;
            l_adminFXTransferListRequest.receiptDateFrom = null;
            l_adminFXTransferListRequest.receiptDateTo = null;
            l_adminFXTransferListRequest.statusDiv = "1";
            l_adminFXTransferListRequest.fxSystemCode = null;
            l_adminFXTransferListRequest.pageIndex = "1";
            l_adminFXTransferListRequest.pageSize = "10";
            l_adminFXTransferListRequest.transferDate =
            	WEB3DateUtility.getDate("20080710", "yyyymmdd");

            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);

            assertNotNull(l_adminFXTransferListResponse);
            assertEquals(l_adminFXTransferListResponse.fxTotalDepositToGuaranty, "200");
            assertEquals(l_adminFXTransferListResponse.fxTotalGuarantyToDeposit, "0");
            assertEquals(l_adminFXTransferListResponse.fxTransferTotal, "-200");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_C0007()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {       
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            l_administratorParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
            	TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(123);
            l_institutionPreferencesParams.setName("fx.cfd.summary.calculation.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            // AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0402");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            // GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setOrderRequestNumber("01");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000000");
            l_gftTransferStatusParams.setSendRcvDiv("2");
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setOperationDiv("02");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setAmount(100);
            l_gftTransferStatusParams.setTransferDate("20080110");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("624");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setResultCode("00000000");
            l_gftTransferStatusParams.setSendRcvDiv("2");
            l_gftTransferStatusParams.setFxSystemCode("1");
            l_gftTransferStatusParams.setOrderRequestNumber("02");
            l_gftTransferStatusParams.setOperationDiv("02");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setAmount(100);
            l_gftTransferStatusParams.setTransferDate("20080110");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);

            // MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImplForTest();
            WEB3AdminFXTransferListRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferListRequest();

            String[] l_strBranchCodeList = new String[]{"624"};
            l_adminFXTransferListRequest.branchCodeList = l_strBranchCodeList;
            l_adminFXTransferListRequest.accountCode = null;
            l_adminFXTransferListRequest.fxTransferDiv = null;
            l_adminFXTransferListRequest.receiptDateFrom = null;
            l_adminFXTransferListRequest.receiptDateTo = null;
            l_adminFXTransferListRequest.statusDiv = "1";
            l_adminFXTransferListRequest.fxSystemCode = null;
            l_adminFXTransferListRequest.pageIndex = "1";
            l_adminFXTransferListRequest.pageSize = "10";
            l_adminFXTransferListRequest.transferDate =
            	WEB3DateUtility.getDate("20080710", "yyyymmdd");

            WEB3AdminFXTransferListResponse l_adminFXTransferListResponse =
                l_adminFXTransferManagementServiceImpl.getListScreen(l_adminFXTransferListRequest);

            assertNotNull(l_adminFXTransferListResponse);
            assertEquals(l_adminFXTransferListResponse.fxTotalDepositToGuaranty, "0");
            assertEquals(l_adminFXTransferListResponse.fxTotalGuarantyToDeposit, "200");
            assertEquals(l_adminFXTransferListResponse.fxTransferTotal, "200");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    public void testcreateQueryString()
    {
        final String STR_METHOD_NAME = "testcreateQueryString()";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminFXTransferManagementServiceImpl l_impl = new WEB3AdminFXTransferManagementServiceImpl();

        String[] l_strBranchCodes = new String[]{"381","624"};
        String l_strAccountCode = "1234567";
        String l_strFxTransferDiv = "1";
        Date l_datReceiptDateFrom = new Date(20080101);
        Date l_datReceiptDateTo = new Date(20080130);
        String l_strTransferDate = "20080101";
        String l_strStatusDiv = "1";
        String l_strFxSystemCode = "123";

                
        String l_strQueryString = 
            l_impl.createQueryString(
            l_strBranchCodes,
            l_strAccountCode,
            l_strFxTransferDiv,
            l_datReceiptDateFrom,
            l_datReceiptDateTo,
            l_strTransferDate,
            l_strStatusDiv,
            l_strFxSystemCode);

        
        assertEquals(l_strQueryString," institution_code = ?  and ( branch_code = ?  or branch_code = ?  )  and substr(account_code, 0, 6) = ?  and operation_div = ? " +
                " and to_char(created_timestamp, 'YYYYMMDD') >= ?  and to_char(created_timestamp, 'YYYYMMDD') <= ?  and transfer_date = ?  and transfer_status_div = ?  and fx_system_code = ? ");

        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcreateQueryContainer()
    {
        final String STR_METHOD_NAME = "testcreateQueryContainer()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXTransferManagementServiceImpl l_impl = new WEB3AdminFXTransferManagementServiceImpl();

        
        String l_strInstitutionCode = "0D";
        
        String[] l_strBranchCodes = new String[]{"381","624"};
        String l_strAccountCode = "1234567";
        String l_strFxTransferDiv = "1";
        Date l_datReceiptDateFrom = new Date(20080101);
        Date l_datReceiptDateTo = new Date(20080130);
        String l_strTransferDate = "20080101";
        String l_strStatusDiv = "1";
        String l_strFxSystemCode = "123";
        
        
        Object[] l_obj = l_impl.createQueryContainer(
                l_strInstitutionCode,
                l_strBranchCodes,
                l_strAccountCode, 
                l_strFxTransferDiv,
                l_datReceiptDateFrom,
                l_datReceiptDateTo,
                l_strTransferDate, 
                l_strStatusDiv,
                l_strFxSystemCode);
        
        
        assertEquals(l_obj[0].toString(),"0D");
        assertEquals(l_obj[1].toString(),"381");
        assertEquals(l_obj[2].toString(),"624");
        assertEquals(l_obj[3].toString(),"123456");
        assertEquals(l_obj[4].toString(),WEB3FxTransStatusOperationDivDef.FROM_FX);
        assertEquals(l_obj[5].toString(),WEB3DateUtility.formatDate(l_datReceiptDateFrom, "yyyyMMdd"));
        assertEquals(l_obj[6].toString(),WEB3DateUtility.formatDate(l_datReceiptDateTo, "yyyyMMdd"));
        assertEquals(l_obj[7].toString(),"20080101");
        assertEquals(l_obj[8].toString(),WEB3TransferStatusDivDef.PROCESS_COMPLETE);
        assertEquals(l_obj[9].toString(),"123");

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCancel_case0001()
    {
        final String STR_METHOD_NAME = "testValidateCancel_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFXTransferCancelConfirmRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferCancelConfirmRequest();

            WEB3FXSearchConditionUnit l_fxSearchConditionUnit =
                new WEB3FXSearchConditionUnit();
            l_fxSearchConditionUnit.branchCode = "011";
            l_fxSearchConditionUnit.requestNumber = "123";
            WEB3FXSearchConditionUnit[] l_fxSearchConditionUnitList =
                new WEB3FXSearchConditionUnit[]{l_fxSearchConditionUnit};
    
            l_adminFXTransferListRequest.fxSearchConditionList =  l_fxSearchConditionUnitList;
            
            //      AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
    
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionRow =
                TestDBUtility.getAdminPermissionRow();
            
            l_adminPermissionRow.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionRow.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionRow.setTransactionCategory("B0402");
            l_adminPermissionRow.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            //          GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("011");
            l_gftTransferStatusParams.setOrderRequestNumber("123");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("2");
            l_gftTransferStatusParams.setResultCode("00000990");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBankAccountRegi("002");
            l_mainAccountParams.setAccountCode("12315");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImpl();
            
            WEB3AdminFXTransferCancelConfirmResponse l_reponse =
                l_adminFXTransferManagementServiceImpl.validateCancel(l_adminFXTransferListRequest);
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCancel_case0002()
    {
        final String STR_METHOD_NAME = "testValidateCancel_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFXTransferCancelConfirmRequest l_adminFXTransferListRequest =
                new WEB3AdminFXTransferCancelConfirmRequest();

            WEB3FXSearchConditionUnit l_fxSearchConditionUnit =
                new WEB3FXSearchConditionUnit();
            l_fxSearchConditionUnit.branchCode = "011";
            l_fxSearchConditionUnit.requestNumber = "123";
            WEB3FXSearchConditionUnit[] l_fxSearchConditionUnitList =
                new WEB3FXSearchConditionUnit[]{l_fxSearchConditionUnit};
    
            l_adminFXTransferListRequest.fxSearchConditionList =  l_fxSearchConditionUnitList;
            
            //      AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
    
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionRow =
                TestDBUtility.getAdminPermissionRow();
            
            l_adminPermissionRow.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionRow.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionRow.setTransactionCategory("B0402");
            l_adminPermissionRow.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            //          GftTransferStatusParams
            TestDBUtility.deleteAll(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("011");
            l_gftTransferStatusParams.setOrderRequestNumber("123");
            l_gftTransferStatusParams.setAccountCode("123456");
            l_gftTransferStatusParams.setReceiveTime("20070913101112");
            l_gftTransferStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20010101", "yyyyMMdd"));
            l_gftTransferStatusParams.setTransferStatusDiv("0");
            l_gftTransferStatusParams.setResultCode("00000801");
            TestDBUtility.insertWithDel(l_gftTransferStatusParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBankAccountRegi("002");
            l_mainAccountParams.setAccountCode("12315");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_adminFXTransferManagementServiceImpl =
                new WEB3AdminFXTransferManagementServiceImpl();
            
            WEB3AdminFXTransferCancelConfirmResponse l_reponse =
                l_adminFXTransferManagementServiceImpl.validateCancel(l_adminFXTransferListRequest);
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    class WEB3AdminFXTransferManagementServiceImplForTest extends WEB3AdminFXTransferManagementServiceImpl
    {
        protected String createQueryString(String[] l_strBranchCodes,
            String l_strAccountCode, String l_strFxTransferDiv,
            Date l_datReceiptDateFrom, Date l_datReceiptDateTo,
            String l_strTransferDate, String l_strStatusDiv)
        {
            return " institution_code = ? ";
        }
        
        protected Object[] createQueryContainer(String l_strInstitutionCode,
            String[] l_strBranchCodes,
            String l_strAccountCode, String l_strFxTransferDiv,
            Date l_datReceiptDateFrom, Date l_datReceiptDateTo,
            String l_strTransferDate, String l_strStatusDiv)
        {
            return new String[]{"0D"};
        }
    }
}
@
