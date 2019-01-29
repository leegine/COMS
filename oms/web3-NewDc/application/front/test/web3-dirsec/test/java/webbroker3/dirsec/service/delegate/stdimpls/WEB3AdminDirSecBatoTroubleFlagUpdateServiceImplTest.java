head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecBatoTroubleFlagUpdateServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.dirsec.data.SubmitTriggerInfoParams;
import webbroker3.dirsec.message.WEB3AdminDirSecBatoPreferenceRecordDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueSortKey;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BatoInstBranchPrefParams;
import webbroker3.gentrade.data.BatoInstBranchPrefRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecBatoTroubleFlagUpdateServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecBatoTroubleFlagUpdateServiceImplTest.class);

    WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl;

    public WEB3AdminDirSecBatoTroubleFlagUpdateServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testExecute_Case001()
    {
        final String STR_METHOD_NAME = "testExecute_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testExecute_Case002()
    {
        final String STR_METHOD_NAME = "testExecute_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(new WEB3AdminFrontRouteChangeSelectRequest());
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_Case003()
    {
        final String STR_METHOD_NAME = "testExecute_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);
            
            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode =new String[]{"624","381"};
 
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams.setHashField1("112");
            l_batoInstBranchPrefParams.setHashField2("113");
            l_batoInstBranchPrefParams.setSystemFailureFlag("0");
            l_batoInstBranchPrefParams.setLastUpdater("xudan");
            l_batoInstBranchPrefParams.setCreatedTimestamp(new Date(20080215));
            l_batoInstBranchPrefParams.setLastUpdatedTimestamp(new Date(20080215));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams1 = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams1.setInstitutionCode("0D");
            l_batoInstBranchPrefParams1.setBranchCode("381");
            l_batoInstBranchPrefParams1.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams1.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams1.setHashField1("112");
            l_batoInstBranchPrefParams1.setHashField2("113");
            l_batoInstBranchPrefParams1.setSystemFailureFlag("1");
            l_batoInstBranchPrefParams1.setLastUpdater("xudan");
            l_batoInstBranchPrefParams1.setCreatedTimestamp(new Date(20080215));
            l_batoInstBranchPrefParams1.setLastUpdatedTimestamp(new Date(20080215));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams1);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams2 = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams2.setInstitutionCode("33");
            l_batoInstBranchPrefParams2.setBranchCode("381");
            l_batoInstBranchPrefParams2.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams2.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams2.setHashField1("112");
            l_batoInstBranchPrefParams2.setHashField2("113");
            l_batoInstBranchPrefParams2.setSystemFailureFlag("1");
            l_batoInstBranchPrefParams2.setLastUpdater("xudan");
            l_batoInstBranchPrefParams2.setCreatedTimestamp(new Date(20080215));
            l_batoInstBranchPrefParams2.setLastUpdatedTimestamp(new Date(20080215));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams2);
          
            WEB3AdminDirSecWorkingListResponse l_response =
                (WEB3AdminDirSecWorkingListResponse)l_impl.execute(l_request);
            
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_details =
                l_response.batoPreferenceRecord;
            assertEquals(2, l_details.length);
            
            assertEquals("381", l_details[0].branchCode);
            assertEquals("1", l_details[0].systemTroubleDiv);
            
            assertEquals("624", l_details[1].branchCode);
            assertEquals("0", l_details[1].systemTroubleDiv);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_Case004()
    {
        final String STR_METHOD_NAME = "testExecute_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[3];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "3";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "381";
            l_batoPreferenceRecord[1].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            l_batoPreferenceRecord[2] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[2].branchCode = "456";
            l_batoPreferenceRecord[2].systemTroubleDiv = "2";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //BatoInstBranchPrefRow
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams =
                this.getBatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            //22
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setSystemFailureFlag("2");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            //33
            l_batoInstBranchPrefParams.setBranchCode("456");
            l_batoInstBranchPrefParams.setSystemFailureFlag("3");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            WEB3AdminDirSecWorkingConfirmResponse l_response =
                (WEB3AdminDirSecWorkingConfirmResponse)l_impl.execute(l_request);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_Case005()
    {
        final String STR_METHOD_NAME = "testExecute_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminDirSecWorkingCompleteRequest l_request = 
                new WEB3AdminDirSecWorkingCompleteRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[3];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.password = "123456";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "381";
            l_batoPreferenceRecord[1].systemTroubleDiv = "2";
            
            l_batoPreferenceRecord[2] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[2].branchCode = "456";
            l_batoPreferenceRecord[2].systemTroubleDiv = "3";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            l_administratorParams.setAdministratorCode("123456");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //ValidateTradingPassword
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);

            //BatoInstBranchPrefRow
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams =
                this.getBatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setLastUpdater("321654");
            l_batoInstBranchPrefParams.setSystemFailureFlag("3");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);
            
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setLastUpdater("456789");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);
            
            l_batoInstBranchPrefParams.setBranchCode("456");
            l_batoInstBranchPrefParams.setLastUpdater("654987");
            l_batoInstBranchPrefParams.setSystemFailureFlag("2");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            WEB3AdminDirSecWorkingCompleteResponse l_response =
                (WEB3AdminDirSecWorkingCompleteResponse)l_impl.execute(l_request);

            List l_lisResult = l_queryProcessor.doFindAllQuery(BatoInstBranchPrefRow.TYPE);
            assertEquals(3, l_lisResult.size());
            assertEquals("123456", ((BatoInstBranchPrefRow)l_lisResult.get(0)).getLastUpdater());
            assertEquals("123456", ((BatoInstBranchPrefRow)l_lisResult.get(1)).getLastUpdater());
            assertEquals("123456", ((BatoInstBranchPrefRow)l_lisResult.get(2)).getLastUpdater());


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetListScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode = null;
            l_impl.getListScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testGetListScreen_Case002()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administrator.getLoginId()));

            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode =new String[]{"624","381"};
            l_impl.getListScreen(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_Case003()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode =new String[]{"624","381"};
            l_impl.getListScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_Case004()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);
            
            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode =new String[]{"624","381"};
            l_impl.getListScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_Case005()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);
            
            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode =new String[]{"624","381"};
 
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefParams.TYPE);
            
            l_impl.getListScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetListScreen_Case006()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);
            
            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode =new String[]{"624","381"};
 
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams.setHashField1("112");
            l_batoInstBranchPrefParams.setHashField2("113");
            l_batoInstBranchPrefParams.setSystemFailureFlag("0");
            l_batoInstBranchPrefParams.setLastUpdater("xudan");
            l_batoInstBranchPrefParams.setCreatedTimestamp(new Date(20080215));
            l_batoInstBranchPrefParams.setLastUpdatedTimestamp(new Date(20080215));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams1 = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams1.setInstitutionCode("0D");
            l_batoInstBranchPrefParams1.setBranchCode("381");
            l_batoInstBranchPrefParams1.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams1.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams1.setHashField1("112");
            l_batoInstBranchPrefParams1.setHashField2("113");
            l_batoInstBranchPrefParams1.setSystemFailureFlag("1");
            l_batoInstBranchPrefParams1.setLastUpdater("xudan");
            l_batoInstBranchPrefParams1.setCreatedTimestamp(new Date(20080215));
            l_batoInstBranchPrefParams1.setLastUpdatedTimestamp(new Date(20080215));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams1);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams2 = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams2.setInstitutionCode("33");
            l_batoInstBranchPrefParams2.setBranchCode("381");
            l_batoInstBranchPrefParams2.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams2.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams2.setHashField1("112");
            l_batoInstBranchPrefParams2.setHashField2("113");
            l_batoInstBranchPrefParams2.setSystemFailureFlag("1");
            l_batoInstBranchPrefParams2.setLastUpdater("xudan");
            l_batoInstBranchPrefParams2.setCreatedTimestamp(new Date(20080215));
            l_batoInstBranchPrefParams2.setLastUpdatedTimestamp(new Date(20080215));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams2);

            
            WEB3AdminDirSecWorkingListResponse l_response =
                l_impl.getListScreen(l_request);
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_details =
                l_response.batoPreferenceRecord;
            assertEquals(2, l_details.length);
            
            assertEquals("381", l_details[0].branchCode);
            assertEquals("1", l_details[0].systemTroubleDiv);
            
            assertEquals("624", l_details[1].branchCode);
            assertEquals("0", l_details[1].systemTroubleDiv);

        }
        
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeConfirmScreen_Case001()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            l_request.batoPreferenceRecord = null;
            l_impl.validateChangeConfirmScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03076, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeConfirmScreen_Case002()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0102");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            l_impl.validateChangeConfirmScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangeConfirmScreen_Case003()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            l_impl.validateChangeConfirmScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeConfirmScreen_Case004()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            l_impl.validateChangeConfirmScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangeConfirmScreen_Case005()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[3];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "381";
            l_batoPreferenceRecord[1].systemTroubleDiv = "1";
            
            l_batoPreferenceRecord[2] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[2].branchCode = "456";
            l_batoPreferenceRecord[2].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //BatoInstBranchPrefRow
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams =
                this.getBatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            //22
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            //22
            l_batoInstBranchPrefParams.setBranchCode("456");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            l_impl.validateChangeConfirmScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02680, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangeConfirmScreen_Case006()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[3];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "3";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "381";
            l_batoPreferenceRecord[1].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            l_batoPreferenceRecord[2] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[2].branchCode = "456";
            l_batoPreferenceRecord[2].systemTroubleDiv = "2";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //BatoInstBranchPrefRow
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams =
                this.getBatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            //22
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setSystemFailureFlag("2");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            //33
            l_batoInstBranchPrefParams.setBranchCode("456");
            l_batoInstBranchPrefParams.setSystemFailureFlag("3");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            WEB3AdminDirSecWorkingConfirmResponse l_response =
                l_impl.validateChangeConfirmScreen(l_request);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChangeCompleteScreen_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCompleteScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingCompleteRequest l_request = 
                new WEB3AdminDirSecWorkingCompleteRequest();
            l_request.batoPreferenceRecord = null;
            l_impl.submitChangeCompleteScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03076, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeCompleteScreen_Case002()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCompleteScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminDirSecWorkingCompleteRequest l_request = 
                new WEB3AdminDirSecWorkingCompleteRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.password = "123456";
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0102");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            l_impl.submitChangeCompleteScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeCompleteScreen_Case003()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCompleteScreen_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminDirSecWorkingCompleteRequest l_request = 
                new WEB3AdminDirSecWorkingCompleteRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.password = "123456";
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            l_impl.submitChangeCompleteScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00857, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChangeCompleteScreen_Case004()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCompleteScreen_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminDirSecWorkingCompleteRequest l_request = 
                new WEB3AdminDirSecWorkingCompleteRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.password = "123456";
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);
            
            //ValidateTradingPassword
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", false);
            l_impl.submitChangeCompleteScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeCompleteScreen_Case005()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCompleteScreen_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminDirSecWorkingCompleteRequest l_request = 
                new WEB3AdminDirSecWorkingCompleteRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[2];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.password = "123456";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "381";
            l_batoPreferenceRecord[1].systemTroubleDiv = "2";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            l_administratorParams.setAdministratorCode("123456");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //ValidateTradingPassword
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);

            //BatoInstBranchPrefRow
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams =
                this.getBatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setLastUpdater("321654");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);
            
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setLastUpdater("456789");
            l_batoInstBranchPrefParams.setSystemFailureFlag("2");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            l_impl.submitChangeCompleteScreen(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02680, l_ex.getErrorInfo());
           log.error(l_ex.getMessage(), l_ex);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeCompleteScreen_Case006()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCompleteScreen_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3AdminDirSecWorkingCompleteRequest l_request = 
                new WEB3AdminDirSecWorkingCompleteRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[3];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "624";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            l_request.password = "123456";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "381";
            l_batoPreferenceRecord[1].systemTroubleDiv = "2";
            
            l_batoPreferenceRecord[2] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[2].branchCode = "456";
            l_batoPreferenceRecord[2].systemTroubleDiv = "3";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setPermissionLevel("101");
            l_administratorParams.setAdministratorCode("123456");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionParams
            l_queryProcessor.doDeleteAllQuery(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("Z0101");
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeDao
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setDirAdminFlag(1);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            //ValidateTradingPassword
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);

            //BatoInstBranchPrefRow
            l_queryProcessor.doDeleteAllQuery(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams =
                this.getBatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("624");
            l_batoInstBranchPrefParams.setLastUpdater("321654");
            l_batoInstBranchPrefParams.setSystemFailureFlag("3");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);
            
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setLastUpdater("456789");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);
            
            l_batoInstBranchPrefParams.setBranchCode("456");
            l_batoInstBranchPrefParams.setLastUpdater("654987");
            l_batoInstBranchPrefParams.setSystemFailureFlag("2");
            l_queryProcessor.doInsertQuery(l_batoInstBranchPrefParams);

            WEB3AdminDirSecWorkingCompleteResponse l_response =
                l_impl.submitChangeCompleteScreen(l_request);

            List l_lisResult = l_queryProcessor.doFindAllQuery(BatoInstBranchPrefRow.TYPE);
            assertEquals(3, l_lisResult.size());
            assertEquals("123456", ((BatoInstBranchPrefRow)l_lisResult.get(0)).getLastUpdater());
            assertEquals("123456", ((BatoInstBranchPrefRow)l_lisResult.get(1)).getLastUpdater());
            assertEquals("123456", ((BatoInstBranchPrefRow)l_lisResult.get(2)).getLastUpdater());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryString_T001()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_T001";
        
        String[] l_strBranchCodes = new String[3];
        l_strBranchCodes[0] = "331";
        l_strBranchCodes[1] = "332";
        l_strBranchCodes[2] = "333";
        
        
        try
        {
            Object[] obj = new Object[]{l_strBranchCodes};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{String[].class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            String l_str = (String)me.invoke(l_impl,obj);
            assertEquals(" institution_code = ?  and branch_code in ( ?  , ?  , ?  ) ",l_str);
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryString_T002()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_T002";
        
        String[] l_strBranchCodes = new String[1];
        l_strBranchCodes[0] = "331";

        try
        {
            Object[] obj = new Object[]{l_strBranchCodes};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{String[].class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            String l_str = (String)me.invoke(l_impl,obj);

            assertEquals(" institution_code = ?  and branch_code in ( ?  ) ",l_str);
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryString_T003()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_T003";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strBranchCodes = new String[0];

        try
        {
            Object[] obj = new Object[]{l_strBranchCodes};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{String[].class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            String l_str = (String)me.invoke(l_impl,obj);

            assertEquals(" institution_code = ? ",l_str);

        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testcreateQueryDataContainer_T001()
    {
        final String STR_METHOD_NAME = "testcreateQueryDataContainer_T001";
        log.entering(STR_METHOD_NAME);
        
        
        String l_strInstitutionCode = "0D";
        String[] l_strBranchCodes= new String[2];
        
        l_strBranchCodes[0] = "381";
        l_strBranchCodes[1] = "382";
            
        try
        {
            
            Object[] obj = new Object[]{l_strInstitutionCode,l_strBranchCodes};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String[].class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            
            Object[] l_obj = (Object[])me.invoke(l_impl,obj);
            
            assertEquals(l_obj[0].toString(),"0D");
            assertEquals(l_obj[1].toString(),"381");
            assertEquals(l_obj[2].toString(),"382");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryDataContainer_T002()
    {
        final String STR_METHOD_NAME = "testcreateQueryDataContainer_T001";
        log.entering(STR_METHOD_NAME);
        
        
        String l_strInstitutionCode = "0D";
        String[] l_strBranchCodes= new String[]{};

        try
        {
            
            Object[] obj = new Object[]{l_strInstitutionCode,l_strBranchCodes};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String[].class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            
            Object[] l_obj = (Object[])me.invoke(l_impl,obj);
            
            assertEquals(l_obj[0].toString(),"0D");

            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateQueryDataContainer_T003()
    {
        final String STR_METHOD_NAME = "testcreateQueryDataContainer_T001";
        log.entering(STR_METHOD_NAME);
        
        
        String l_strInstitutionCode = "0D";
        String[] l_strBranchCodes= new String[1];
        
        l_strBranchCodes[0] = "381";
            
        try
        {
            
            Object[] obj = new Object[]{l_strInstitutionCode,l_strBranchCodes};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String[].class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            
            Object[] l_obj = (Object[])me.invoke(l_impl,obj);
            
            assertEquals(l_obj[0].toString(),"0D");
            assertEquals(l_obj[1].toString(),"381");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testcreateQuerySortCond_T001()
    {
        String STR_METHOD_NAME = "testcreateQuerySortCond_T001";
        
        log.entering(STR_METHOD_NAME);

        try
        {
            Object[] obj = new Object[]{};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQuerySortCond",
                new Class[]{});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            String l_str = (String)me.invoke(l_impl,obj);
            
            
            assertEquals(l_str," branch_code ");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testgetBatoPreferenceRecord_T001()
    {
        final String STR_METHOD_NAME = "testgetBatoPreferenceRecord_T001";
        log.entering(STR_METHOD_NAME);
        
        
        Object[] l_queryDataContainers = new Object[]{"0D","381"};
        String l_strQueryString = " institution_code = ?  and branch_code = ? ";
        String l_strQuerySortCond = null;

        try
        {
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = new BatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams.setHashField1("102");
            l_batoInstBranchPrefParams.setHashField2("103");
            l_batoInstBranchPrefParams.setSystemFailureFlag("0");
            l_batoInstBranchPrefParams.setLastUpdater("xd");
            l_batoInstBranchPrefParams.setCreatedTimestamp(new Date(20080423));
            l_batoInstBranchPrefParams.setLastUpdatedTimestamp(new Date(20080502));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);

            Object[] obj = new Object[]{l_queryDataContainers,l_strQueryString,l_strQuerySortCond};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "getBatoPreferenceRecord",
                new Class[]{Object[].class, String.class,String.class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            List l_lis = (List)me.invoke(l_impl,obj);

            assertEquals(l_lis.size(),1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testgetBatoPreferenceRecord_T002()
    {
        final String STR_METHOD_NAME = "testgetBatoPreferenceRecord_T002";
        log.entering(STR_METHOD_NAME);
        
        
        Object[] l_queryDataContainers = new Object[]{"0D","381"};
        String l_strQueryString = " institution_code = ?  and branch_code = ? ";
        String l_strQuerySortCond = " branch_code ";

        try
        {
            TestDBUtility.deleteAll(BatoInstBranchPrefParams.TYPE);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = new BatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("382");
            l_batoInstBranchPrefParams.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams.setHashField1("102");
            l_batoInstBranchPrefParams.setHashField2("103");
            l_batoInstBranchPrefParams.setSystemFailureFlag("0");
            l_batoInstBranchPrefParams.setLastUpdater("xd");
            l_batoInstBranchPrefParams.setCreatedTimestamp(new Date(20080423));
            l_batoInstBranchPrefParams.setLastUpdatedTimestamp(new Date(20080502));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);

            Object[] obj = new Object[]{l_queryDataContainers,l_strQueryString,l_strQuerySortCond};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "getBatoPreferenceRecord",
                new Class[]{Object[].class, String.class,String.class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            List l_lis = (List)me.invoke(l_impl,obj);

           fail();
        }
        catch (InvocationTargetException l_exc)
        {
            WEB3BusinessLayerException l_ex = (WEB3BusinessLayerException)l_exc.getTargetException();
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01037);
            log.error(l_ex.getMessage());
            
        }
        catch(Exception e)
        {
            fail();
            log.error(e.getMessage());
            
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateBatoPreferenceRecordDetail_T001()
    {
        final String STR_METHOD_NAME = "testcreateBatoPreferenceRecordDetail_T001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BatoInstBranchPrefParams.TYPE);
            
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = new BatoInstBranchPrefParams();
            l_batoInstBranchPrefParams.setInstitutionCode("0D");
            l_batoInstBranchPrefParams.setBranchCode("381");
            l_batoInstBranchPrefParams.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams.setHashField1("102");
            l_batoInstBranchPrefParams.setHashField2("103");
            l_batoInstBranchPrefParams.setSystemFailureFlag("0");
            l_batoInstBranchPrefParams.setLastUpdater("xd");
            l_batoInstBranchPrefParams.setCreatedTimestamp(new Date(20080423));
            l_batoInstBranchPrefParams.setLastUpdatedTimestamp(new Date(20080502));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);

            BatoInstBranchPrefParams l_batoInstBranchPrefParams2 = new BatoInstBranchPrefParams();
            l_batoInstBranchPrefParams2.setInstitutionCode("FF");
            l_batoInstBranchPrefParams2.setBranchCode("382");
            l_batoInstBranchPrefParams2.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams2.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams2.setHashField1("102");
            l_batoInstBranchPrefParams2.setHashField2("103");
            l_batoInstBranchPrefParams2.setSystemFailureFlag("1");
            l_batoInstBranchPrefParams2.setLastUpdater("xd");
            l_batoInstBranchPrefParams2.setCreatedTimestamp(new Date(20080423));
            l_batoInstBranchPrefParams2.setLastUpdatedTimestamp(new Date(20080502));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams2);
            
            
            List l_lis = new ArrayList();
            l_lis.add(l_batoInstBranchPrefParams);
            l_lis.add(l_batoInstBranchPrefParams2);


            Object[] obj = new Object[]{l_lis};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createBatoPreferenceRecordDetail",
                new Class[]{List.class});
            
            me.setAccessible(true);
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();

            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_adminDirSecBatoPreferenceRecordDetails=
                (WEB3AdminDirSecBatoPreferenceRecordDetail[])me.invoke(l_impl,obj);
            
            
            assertEquals(l_adminDirSecBatoPreferenceRecordDetails[0].branchCode,"381");
            assertEquals(l_adminDirSecBatoPreferenceRecordDetails[0].systemTroubleDiv,"0");

            assertEquals(l_adminDirSecBatoPreferenceRecordDetails[1].branchCode,"382");
            assertEquals(l_adminDirSecBatoPreferenceRecordDetails[1].systemTroubleDiv,"1");
           
        }
        catch(Exception e)
        {
            fail();
            log.error(e.getMessage());
            
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcreateBatoPreferenceRecordDetail_T002()
    {
        final String STR_METHOD_NAME = "testcreateBatoPreferenceRecordDetail_T002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BatoInstBranchPrefParams.TYPE);

            List l_lis = new ArrayList();

            Object[] obj = new Object[]{l_lis};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createBatoPreferenceRecordDetail",
                new Class[]{List.class});
            
            me.setAccessible(true);
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();

            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_adminDirSecBatoPreferenceRecordDetails=
                (WEB3AdminDirSecBatoPreferenceRecordDetail[])me.invoke(l_impl,obj);

            assertEquals(0, l_adminDirSecBatoPreferenceRecordDetails.length);
        }
        catch(Exception e)
        {
            fail();
            log.error(e.getMessage());
            
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcreateQueryString_NoParmas()
    {
        final String STR_METHOD_NAME ="testcreateQueryString_NoParmas";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Object[] obj = new Object[]{};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            String l_str = (String)me.invoke(l_impl,obj);
            assertEquals(" institution_code = ?  and branch_code = ? ",l_str);
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcreateQueryDataContainer()
    {
        final String STR_METHOD_NAME ="testcreateQueryDataContainer";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        String l_strBranchCode = "381";
        
        try
        {
            Object[] obj = new Object[]{l_strInstitutionCode, l_strBranchCode};
            Method me = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class});
            WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_impl = new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
            me.setAccessible(true);
            Object[] l_queryContainers = (Object[])me.invoke(l_impl,obj);
            
            
            assertEquals(l_queryContainers[0],"0D");
            assertEquals(l_queryContainers[1],"381");
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            fail();
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateBatoPreference_0001(){
        final String STR_METHOD_NAME = " testUpdateBatoPreference_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BatoInstBranchPrefParams.TYPE);

            BatoInstBranchPrefParams l_batoInstBranchPrefParams = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams.setInstitutionCode("100");
            l_batoInstBranchPrefParams.setBranchCode("101");
            l_batoInstBranchPrefParams.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams.setHashField1("102");
            l_batoInstBranchPrefParams.setHashField2("103");
            l_batoInstBranchPrefParams.setSystemFailureFlag("0");
            l_batoInstBranchPrefParams.setLastUpdater("xd");
            l_batoInstBranchPrefParams.setCreatedTimestamp(new Date(20080423));
            l_batoInstBranchPrefParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);

            BatoInstBranchPrefParams l_batoInstBranchPrefParams2 = new BatoInstBranchPrefParams();

            l_batoInstBranchPrefParams2.setInstitutionCode("110");
            l_batoInstBranchPrefParams2.setBranchCode("111");
            l_batoInstBranchPrefParams2.setUrl("http://sinocom.cn");
            l_batoInstBranchPrefParams2.setSoapUrl("http://yahoo.cn");
            l_batoInstBranchPrefParams2.setHashField1("112");
            l_batoInstBranchPrefParams2.setHashField2("113");
            l_batoInstBranchPrefParams2.setSystemFailureFlag("0");
            l_batoInstBranchPrefParams2.setLastUpdater("xudan");
            l_batoInstBranchPrefParams2.setCreatedTimestamp(new Date(20080215));
            l_batoInstBranchPrefParams2.setLastUpdatedTimestamp(new Date(20080215));
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams2);

        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            l_exE.printStackTrace();
            fail();
        }

        WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl l_adminDirSecBatoTroubleFlagUpdateServiceImpl =
            new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl();
        
        String l_strSystemTroubleDiv = "1";
        String l_strUpdateString = "institution_code = ? and branch_code = ? ";
        Object[] l_queryContainers = new Object[2];
        l_queryContainers[0] = "100";
        l_queryContainers[1] = "101";
        String l_strUpdaterCode = "fht";

        try
        {
            Method l_method = WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl.class.getDeclaredMethod(
                "updateBatoPreference",
                new Class[] {
                    String.class,
                    String.class,
                    Object[].class,
                    String.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_adminDirSecBatoTroubleFlagUpdateServiceImpl,
                new Object[] {
                    l_strSystemTroubleDiv,
                    l_strUpdateString,
                    l_queryContainers,
                    l_strUpdaterCode});
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisBatoInstBranchPrefParams =
                l_queryProcessor.doFindAllQuery(
                    BatoInstBranchPrefParams.TYPE,
                    l_strUpdateString,
                    l_queryContainers);

            assertEquals(1, l_lisBatoInstBranchPrefParams.size());

            BatoInstBranchPrefParams l_resultBatoInstBranchPrefParams =
                (BatoInstBranchPrefParams)l_lisBatoInstBranchPrefParams.get(0);

            assertEquals("1", l_resultBatoInstBranchPrefParams.getSystemFailureFlag());
            assertEquals("fht", l_resultBatoInstBranchPrefParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHH"),
                WEB3DateUtility.formatDate(l_resultBatoInstBranchPrefParams.getLastUpdatedTimestamp(),
                    "yyyyMMddHH"));

            List l_lisBatoInstBranchPrefParams1 =
                l_queryProcessor.doFindAllQuery(
                        BatoInstBranchPrefParams.TYPE,
                        l_strUpdateString,
                        new Object[]{"110","111"});

            assertEquals(1, l_lisBatoInstBranchPrefParams.size());

            BatoInstBranchPrefParams l_resultBatoInstBranchPrefParams1 =
                (BatoInstBranchPrefParams)l_lisBatoInstBranchPrefParams1.get(0);

            assertEquals("0", l_resultBatoInstBranchPrefParams1.getSystemFailureFlag());
            assertEquals("xudan", l_resultBatoInstBranchPrefParams1.getLastUpdater());
            assertEquals(WEB3DateUtility.formatDate(new Date(20080215), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_resultBatoInstBranchPrefParams1.getLastUpdatedTimestamp(),
                "yyyyMMdd"));
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        finally
        {
            try
            {
                TestDBUtility.deleteAll(BatoInstBranchPrefParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }

    }
    
    public BatoInstBranchPrefParams getBatoInstBranchPrefParams()
    {
        BatoInstBranchPrefParams l_params = new BatoInstBranchPrefParams();
        //証券会社コード    institution_code    VARCHAR2    3    NOT NULL
        l_params.setInstitutionCode("0D");
        //部店コード    branch_code    VARCHAR2    3    NOT NULL
        l_params.setBranchCode("624");
        //URL（一般接続用）    url    VARCHAR2    200    NOT NULL
        l_params.setUrl("jiddkkk");
        //URL（SOAP接続用）    soap_url    VARCHAR2    200    NOT NULL
        l_params.setSoapUrl("sadfsdafsda");
        //ハッシュ定数１    hash_field1    VARCHAR2    200    NOT NULL
        l_params.setHashField1("1234");
        //ハッシュ定数２    hash_field2    VARCHAR2    200    NOT NULL
        l_params.setHashField2("4567");
        //システム障害フラグ    system_failure_flag    VARCHAR2    1    NOT NULL
        l_params.setSystemFailureFlag("1");
        //更新者コード    last_updater    VARCHAR2    20    NULL
        l_params.setLastUpdater("jiddk");
        //作成日付    created_timestamp    DATE        NOT NULL
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp    DATE        NOT NULL
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
}
@
