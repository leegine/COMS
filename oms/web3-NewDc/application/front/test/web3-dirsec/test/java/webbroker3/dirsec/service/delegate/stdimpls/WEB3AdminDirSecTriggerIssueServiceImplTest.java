head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecTriggerIssueServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.dirsec.data.SubmitTriggerInfoParams;
import webbroker3.dirsec.define.WEB3AdminDirSecSortKeyItemDef;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueRecordDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueSortKey;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsParams;
import webbroker3.mqgateway.stdimpls.data.MqMessageIdMappingsRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecTriggerIssueServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueServiceImplTest.class);
    WEB3AdminDirSecTriggerIssueServiceImpl l_impl =
        new WEB3AdminDirSecTriggerIssueServiceImpl();
    public WEB3AdminDirSecTriggerIssueServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestDBUtility.deleteAll(MqMessageIdMappingsRow.TYPE);
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
            
            //SubmitTriggerInfoParams
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "request_code";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            WEB3AdminDirSecTriggerIssueListResponse l_response =
                (WEB3AdminDirSecTriggerIssueListResponse)l_impl.execute(l_request);
            assertEquals(0, l_response.triggerIssueInfo.length);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
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
            
            WEB3AdminDirSecTriggerIssueInputRequest l_request =
                new WEB3AdminDirSecTriggerIssueInputRequest();
            WEB3AdminDirSecTriggerIssueInputResponse l_response =
                (WEB3AdminDirSecTriggerIssueInputResponse)l_impl.execute(l_request);
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
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            
            WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
                new WEB3AdminDirSecTriggerIssueConfirmRequest();
            
            WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
            
            triggerIssueInfo.dataCode = "1";
            triggerIssueInfo.reissuePossibleFlag = "2";
            triggerIssueInfo.shellName="123";
            triggerIssueInfo.userData="4";
            triggerIssueInfo.triggerName = "5";

            l_request.triggerIssueInfo = triggerIssueInfo;
 
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                    true,
                    true);
            
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
           
            SubmitTriggerInfoParams l_SubmitTriggerInfoParams = new SubmitTriggerInfoParams();
            TestDBUtility.deleteAll(SubmitTriggerInfoParams.TYPE);
            l_SubmitTriggerInfoParams.setAccountStart(1);
            l_SubmitTriggerInfoParams.setAccountEnd(2);
            l_SubmitTriggerInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080423","yyyyMMdd"));
            l_SubmitTriggerInfoParams.setEnableSubmitTriggerFlag("7");
            l_SubmitTriggerInfoParams.setIdNo("4");
            l_SubmitTriggerInfoParams.setInstitutionCode("0D");
            l_SubmitTriggerInfoParams.setJobId("123");
            l_SubmitTriggerInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_SubmitTriggerInfoParams.setProductHandlingDiv("1");
            l_SubmitTriggerInfoParams.setRequestCode("1");
            l_SubmitTriggerInfoParams.setTradingTimeType("2");
            l_SubmitTriggerInfoParams.setTriggerId("2");//l_response.triggerIssueInfo.triggerName
            l_SubmitTriggerInfoParams.setUserData("200802");
            TestDBUtility.insertWithDel(l_SubmitTriggerInfoParams);
            
            MqMessageIdMappingsParams l_MqMessageIdMappingsParams = new MqMessageIdMappingsParams();
            TestDBUtility.deleteAll(MqMessageIdMappingsParams.TYPE);
            l_MqMessageIdMappingsParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_MqMessageIdMappingsParams.setDataCode("1");
            l_MqMessageIdMappingsParams.setInstitutionCode("0D");
            l_MqMessageIdMappingsParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_MqMessageIdMappingsParams.setMessageId("123");
            TestDBUtility.insertWithDel(l_MqMessageIdMappingsParams);
            
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_TradingTimeParams = new TradingTimeParams();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("381");
            l_TradingTimeParams.setMarketCode("09");
            l_TradingTimeParams.setTradingTimeType("2");
            l_TradingTimeParams.setProductCode("1");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setStartTime("160000");
            
            l_TradingTimeParams.setEndTime("160059");
            
            
            l_TradingTimeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_TradingTimeParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_TradingTimeParams);

            WEB3AdminDirSecTriggerIssueConfirmResponse l_response = 
                (WEB3AdminDirSecTriggerIssueConfirmResponse)l_impl.execute(l_request);
            
            assertEquals(l_response.triggerIssueInfo.dataCode,"1");
            assertEquals(l_response.triggerIssueInfo.reissuePossibleFlag,"7");
            assertEquals(l_response.triggerIssueInfo.shellName,"123");
            assertEquals(l_response.triggerIssueInfo.triggerName,"2");

            assertEquals(l_response.messageWarning[0],null);
            assertEquals(l_response.messageWarning[1],"ただいまの時間はMQトリガーを発行できない可能性があります");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_Case006()
    {
        final String STR_METHOD_NAME = "testExecute_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecTriggerIssueServiceImpl l_serviceImpl = new WEB3AdminDirSecTriggerIssueServiceImplForTest();

            WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
                new WEB3AdminDirSecTriggerIssueCompleteRequest();

            l_serviceImpl.execute(l_request);

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTriggerIssueListScreenDisplay_Case001()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = null;
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "request_code";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            l_impl.getTriggerIssueListScreenDisplay(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTriggerIssueListScreenDisplay_Case002()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_Case002()";
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

            //WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "request_code";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            l_impl.getTriggerIssueListScreenDisplay(l_request);
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

    public void testGetTriggerIssueListScreenDisplay_Case003()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_Case003()";
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
            
            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "request_code";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            l_impl.getTriggerIssueListScreenDisplay(l_request);
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

    public void testGetTriggerIssueListScreenDisplay_Case004()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_Case004()";
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
            
            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "request_code";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            l_impl.getTriggerIssueListScreenDisplay(l_request);
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

    public void testGetTriggerIssueListScreenDisplay_Case005()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_Case005()";
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
            
            //SubmitTriggerInfoParams
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "request_code";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            WEB3AdminDirSecTriggerIssueListResponse l_response =
                l_impl.getTriggerIssueListScreenDisplay(l_request);
            assertEquals(0, l_response.triggerIssueInfo.length);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTriggerIssueListScreenDisplay_Case006()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_Case006()";
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
            
            //SubmitTriggerInfoParams
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            SubmitTriggerInfoParams l_submitTriggerInfoParams =
                this.getSubmitTriggerInfoRow();
            l_submitTriggerInfoParams.setProductHandlingDiv("0");
            l_queryProcessor.doInsertQuery(l_submitTriggerInfoParams);
            
            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "dataCode";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            WEB3AdminDirSecTriggerIssueListResponse l_response =
                l_impl.getTriggerIssueListScreenDisplay(l_request);
            WEB3AdminDirSecTriggerIssueRecordDetail[] l_details =
                l_response.triggerIssueInfo;
            assertEquals(1, l_details.length);
            assertEquals("123456", l_details[0].shellName);
            assertEquals("1001", l_details[0].dataCode);
            assertEquals("1", l_details[0].reissuePossibleFlag);
            assertEquals("123456789", l_details[0].triggerName);
            assertEquals("200804", l_details[0].userData);

            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTriggerIssueListScreenDisplay_Case007()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueListScreenDisplay_Case007()";
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
            
            //SubmitTriggerInfoParams
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            SubmitTriggerInfoParams l_submitTriggerInfoParams =
                this.getSubmitTriggerInfoRow();
            l_submitTriggerInfoParams.setProductHandlingDiv("0");
            l_queryProcessor.doInsertQuery(l_submitTriggerInfoParams);

            l_submitTriggerInfoParams.setRequestCode("1003");
            l_submitTriggerInfoParams.setJobId("456789");
            l_submitTriggerInfoParams.setTriggerId("tom");
            l_queryProcessor.doInsertQuery(l_submitTriggerInfoParams);
           
            l_submitTriggerInfoParams.setRequestCode("1002");
            l_submitTriggerInfoParams.setJobId("321456");
            l_submitTriggerInfoParams.setTriggerId("jiddk");
            l_queryProcessor.doInsertQuery(l_submitTriggerInfoParams);
            
            WEB3AdminDirSecTriggerIssueListRequest l_request =
                new WEB3AdminDirSecTriggerIssueListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
                new WEB3AdminDirSecTriggerIssueSortKey[1];
            l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
            l_sortKeys[0].keyItem = "dataCode";
            l_sortKeys[0].ascDesc = "D";
            l_request.sortKeys = l_sortKeys;
            WEB3AdminDirSecTriggerIssueListResponse l_response =
                l_impl.getTriggerIssueListScreenDisplay(l_request);
            WEB3AdminDirSecTriggerIssueRecordDetail[] l_details =
                l_response.triggerIssueInfo;
            assertEquals(3, l_details.length);

            assertEquals("456789", l_details[0].shellName);
            assertEquals("1003", l_details[0].dataCode);
            assertEquals("1", l_details[0].reissuePossibleFlag);
            assertEquals("tom", l_details[0].triggerName);
            assertEquals("200804", l_details[0].userData);

            assertEquals("321456", l_details[1].shellName);
            assertEquals("1002", l_details[1].dataCode);
            assertEquals("1", l_details[1].reissuePossibleFlag);
            assertEquals("jiddk", l_details[1].triggerName);
            assertEquals("200804", l_details[1].userData);

            assertEquals("123456", l_details[2].shellName);
            assertEquals("1001", l_details[2].dataCode);
            assertEquals("1", l_details[2].reissuePossibleFlag);
            assertEquals("123456789", l_details[2].triggerName);
            assertEquals("200804", l_details[2].userData);

            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTriggerIssueInputScreenDisplay_Case001()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_Case001()";
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
            
            WEB3AdminDirSecTriggerIssueInputRequest l_request =
                new WEB3AdminDirSecTriggerIssueInputRequest();
                l_impl.getTriggerIssueInputScreenDisplay(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTriggerIssueInputScreenDisplay_Case002()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_Case002()";
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
            
            WEB3AdminDirSecTriggerIssueInputRequest l_request =
                new WEB3AdminDirSecTriggerIssueInputRequest();
                l_impl.getTriggerIssueInputScreenDisplay(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
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

    public void testGetTriggerIssueInputScreenDisplay_Case003()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_Case003()";
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
            
            WEB3AdminDirSecTriggerIssueInputRequest l_request =
                new WEB3AdminDirSecTriggerIssueInputRequest();
                l_impl.getTriggerIssueInputScreenDisplay(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
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
    
    public void testGetTriggerIssueInputScreenDisplay_Case004()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueInputScreenDisplay_Case004()";
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
            
            WEB3AdminDirSecTriggerIssueInputRequest l_request =
                new WEB3AdminDirSecTriggerIssueInputRequest();
            WEB3AdminDirSecTriggerIssueInputResponse l_response =
                l_impl.getTriggerIssueInputScreenDisplay(l_request);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTriggerIssueInfoList_Case001()
    {
        final String STR_METHOD_NAME = "testCreateTriggerIssueInfoList_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_listValue = new Object[]{new ArrayList()};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createTriggerIssueInfoList",
                new Class[]{List.class});
            l_method.setAccessible(true);
            WEB3AdminDirSecTriggerIssueRecordDetail[] l_recordDetails =
                (WEB3AdminDirSecTriggerIssueRecordDetail[])l_method.invoke(l_impl, l_listValue);
            assertEquals(0, l_recordDetails.length);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTriggerIssueInfoList_Case002()
    {
        final String STR_METHOD_NAME = "testCreateTriggerIssueInfoList_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            List l_lisObjectValue = new ArrayList();
            SubmitTriggerInfoParams l_ubmitTriggerInfoParams = this.getSubmitTriggerInfoRow();
            l_queryProcessor.doInsertQuery(l_ubmitTriggerInfoParams);
            l_lisObjectValue.add(l_ubmitTriggerInfoParams);

            Object[] l_listValue = new Object[]{l_lisObjectValue};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createTriggerIssueInfoList",
                new Class[]{List.class});
            l_method.setAccessible(true);
            WEB3AdminDirSecTriggerIssueRecordDetail[] l_recordDetails =
                (WEB3AdminDirSecTriggerIssueRecordDetail[])l_method.invoke(l_impl, l_listValue);
            assertEquals(1, l_recordDetails.length);
            WEB3AdminDirSecTriggerIssueRecordDetail l_recordDetail =
                l_recordDetails[0];
            assertEquals("123456", l_recordDetail.shellName);
            assertEquals("123456789", l_recordDetail.triggerName);
            assertEquals("1", l_recordDetail.reissuePossibleFlag);
            assertEquals("200804", l_recordDetail.userData);
            assertEquals("1001", l_recordDetail.dataCode);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTriggerIssueInfoList_Case003()
    {
        final String STR_METHOD_NAME = "testCreateTriggerIssueInfoList_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            List l_lisObjectValue = new ArrayList();
            SubmitTriggerInfoParams l_ubmitTriggerInfoParams1 = this.getSubmitTriggerInfoRow();
            l_queryProcessor.doInsertQuery(l_ubmitTriggerInfoParams1);
            l_lisObjectValue.add(l_ubmitTriggerInfoParams1);
            
            SubmitTriggerInfoParams l_ubmitTriggerInfoParams2 = this.getSubmitTriggerInfoRow();
            l_ubmitTriggerInfoParams2.setRequestCode("1002");
            l_ubmitTriggerInfoParams2.setJobId("321654");
            l_ubmitTriggerInfoParams2.setTriggerId("jiddk");
            l_queryProcessor.doInsertQuery(l_ubmitTriggerInfoParams2);
            l_lisObjectValue.add(l_ubmitTriggerInfoParams2);

            SubmitTriggerInfoParams l_ubmitTriggerInfoParams3 = this.getSubmitTriggerInfoRow();
            l_ubmitTriggerInfoParams3.setRequestCode("1003");
            l_ubmitTriggerInfoParams3.setJobId("456789");
            l_ubmitTriggerInfoParams3.setTriggerId("tom");
            l_queryProcessor.doInsertQuery(l_ubmitTriggerInfoParams3);
            l_lisObjectValue.add(l_ubmitTriggerInfoParams3);

            Object[] l_listValue = new Object[]{l_lisObjectValue};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createTriggerIssueInfoList",
                new Class[]{List.class});
            l_method.setAccessible(true);
            WEB3AdminDirSecTriggerIssueRecordDetail[] l_recordDetails =
                (WEB3AdminDirSecTriggerIssueRecordDetail[])l_method.invoke(l_impl, l_listValue);
            assertEquals(3, l_recordDetails.length);
            WEB3AdminDirSecTriggerIssueRecordDetail l_recordDetail =
                l_recordDetails[0];
            assertEquals("123456", l_recordDetail.shellName);
            assertEquals("123456789", l_recordDetail.triggerName);
            assertEquals("1", l_recordDetail.reissuePossibleFlag);
            assertEquals("200804", l_recordDetail.userData);
            assertEquals("1001", l_recordDetail.dataCode);
            
            l_recordDetail = l_recordDetails[1];
            assertEquals("321654", l_recordDetail.shellName);
            assertEquals("jiddk", l_recordDetail.triggerName);
            assertEquals("1", l_recordDetail.reissuePossibleFlag);
            assertEquals("200804", l_recordDetail.userData);
            assertEquals("1002", l_recordDetail.dataCode);
            
            l_recordDetail = l_recordDetails[2];
            assertEquals("456789", l_recordDetail.shellName);
            assertEquals("tom", l_recordDetail.triggerName);
            assertEquals("1", l_recordDetail.reissuePossibleFlag);
            assertEquals("200804", l_recordDetail.userData);
            assertEquals("1003", l_recordDetail.dataCode);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testTriggerIssueInfoTableReQuery_Case001()
    {
        final String STR_METHOD_NAME = "testTriggerIssueInfoTableReQuery_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            
            Object[] l_listValue = new Object[]{"0D", "1001", "123456"};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "triggerIssueInfoTableReQuery",
                new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            ArrayList l_lisResult = (ArrayList)l_method.invoke(l_impl, l_listValue);
            assertEquals(0, l_lisResult.size());

            Method l_methodTrade = l_impl.getClass().getDeclaredMethod(
                "getTradingTimeType",
                null);
            l_methodTrade.setAccessible(true);
            assertNull(l_methodTrade.invoke(l_impl, null));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testTriggerIssueInfoTableReQuery_Case002()
    {
        final String STR_METHOD_NAME = "testTriggerIssueInfoTableReQuery_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            SubmitTriggerInfoParams l_submitTriggerInfoParams =
                this.getSubmitTriggerInfoRow();
            l_queryProcessor.doInsertQuery(l_submitTriggerInfoParams);
            
            Object[] l_listValue = new Object[]{"0D", "1001", "123456"};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "triggerIssueInfoTableReQuery",
                new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            ArrayList l_lisResult = (ArrayList)l_method.invoke(l_impl, l_listValue);
            assertEquals(1 , l_lisResult.size());
            
            Method l_methodTrade = l_impl.getClass().getDeclaredMethod(
                "getTradingTimeType",
                null);
            l_methodTrade.setAccessible(true);

            assertEquals("01", l_methodTrade.invoke(l_impl, null));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testTriggerIssueInfoTableReQuery_Case003()
    {
        final String STR_METHOD_NAME = "testTriggerIssueInfoTableReQuery_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(SubmitTriggerInfoParams.TYPE);
            SubmitTriggerInfoParams l_submitTriggerInfoParams =
                this.getSubmitTriggerInfoRow();
            l_submitTriggerInfoParams.setTradingTimeType(null);
            l_queryProcessor.doInsertQuery(l_submitTriggerInfoParams);
            
            Object[] l_listValue = new Object[]{"0D", "1001", "123456"};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "triggerIssueInfoTableReQuery",
                new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            ArrayList l_lisResult = (ArrayList)l_method.invoke(l_impl, l_listValue);
            assertEquals(1 , l_lisResult.size());
            
            Method l_methodTrade = l_impl.getClass().getDeclaredMethod(
                "getTradingTimeType",
                null);
            l_methodTrade.setAccessible(true);

            assertNull(l_methodTrade.invoke(l_impl, null));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryString_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strTableName = "submit_trigger_info";
            Object[] l_listValue = new Object[]{l_strTableName};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryString",
                new Class[]{String.class});
            l_method.setAccessible(true);
            String l_strReturn = (String)l_method.invoke(l_impl, l_listValue);
            assertEquals("institution_code = ? and product_handling_div = ?", l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_Case002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strTableName = "MQ_MESSAGE_ID_MAPPINGS";
            Object[] l_listValue = new Object[]{l_strTableName};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryString",
                new Class[]{String.class});
            l_method.setAccessible(true);
            String l_strReturn = (String)l_method.invoke(l_impl, l_listValue);
            assertEquals("institution_code = ? and data_code = ?" , l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_Case003()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strTableName = "trading_time";
            Object[] l_listValue = new Object[]{l_strTableName};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryString",
                new Class[]{String.class});
            l_method.setAccessible(true);
            String l_strReturn = (String)l_method.invoke(l_impl, l_listValue);
            String l_strNeed = "institution_code = ? and branch_code = ? and trading_time_type = ? and biz_date_type = ? and " +
                "start_time <= ? and end_time >= ? and submit_market_trigger = ?";
            assertEquals(l_strNeed, l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecTriggerIssueRecordDetail l_detail =
                new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_detail.dataCode = "1001";
            String l_strTableName = "submit_trigger_info";
            String l_strInstitionCode = "0D";
            Object[] l_listValue = new Object[]
                {l_strTableName,
                 l_strInstitionCode,
                 l_detail};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class, WEB3AdminDirSecTriggerIssueRecordDetail.class});
            l_method.setAccessible(true);
            Object[] l_returnValue = (Object[])l_method.invoke(l_impl, l_listValue);
            assertEquals(2, l_returnValue.length);
            assertEquals("0D", l_returnValue[0]);
            assertEquals("0", l_returnValue[1]);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_Case002()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecTriggerIssueRecordDetail l_detail =
                new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_detail.dataCode = "1001";
            String l_strTableName = "MQ_MESSAGE_ID_MAPPINGS";
            String l_strInstitionCode = "0D";
            Object[] l_listValue = new Object[]
                {l_strTableName,
                 l_strInstitionCode,
                 l_detail};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class, WEB3AdminDirSecTriggerIssueRecordDetail.class});
            l_method.setAccessible(true);
            Object[] l_returnValue = (Object[])l_method.invoke(l_impl, l_listValue);
            assertEquals(2, l_returnValue.length);
            assertEquals("0D", l_returnValue[0]);
            assertEquals("1001", l_returnValue[1]);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_Case003()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecTriggerIssueRecordDetail l_detail =
                new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_detail.dataCode = "1001";
            String l_strTableName = "trading_time";
            String l_strInstitionCode = "0D";
            Object[] l_listValue = new Object[]
                {l_strTableName,
                 l_strInstitionCode,
                 l_detail};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class, WEB3AdminDirSecTriggerIssueRecordDetail.class});
            l_method.setAccessible(true);
            Object l_returnValue = l_method.invoke(l_impl, l_listValue);
            assertNull(l_returnValue);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_Case004()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecTriggerIssueRecordDetail l_detail =
                new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_detail.dataCode = "1001";
            String l_strTableName = "trading_time";
            String l_strInstitionCode = "0D";

            Method l_methodSet = l_impl.getClass().getDeclaredMethod(
                "setTradingTimeType",
                new Class[]{String.class});
            l_methodSet.setAccessible(true);
            l_methodSet.invoke(l_impl, new Object[]{"11"});

            Object[] l_listValue = new Object[]
                {l_strTableName,
                 l_strInstitionCode,
                 l_detail};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class, WEB3AdminDirSecTriggerIssueRecordDetail.class});
            l_method.setAccessible(true);
            Object[] l_returnValue = (Object[])l_method.invoke(l_impl, l_listValue);
            assertEquals(7, l_returnValue.length);
            assertEquals("0D", l_returnValue[0]);
            assertEquals("624", l_returnValue[1]);
            assertEquals("11", l_returnValue[2]);
            String l_strBizDateType =
                WEB3GentradeTradingTimeManagement.getBizDateType(GtlUtils.getSystemTimestamp());
            assertEquals(l_strBizDateType, l_returnValue[3]);
            assertEquals(WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(), "HHmmss"), l_returnValue[4]);
            assertEquals(WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(), "HHmmss"), l_returnValue[5]);
            assertEquals("1", l_returnValue[6]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_Case005()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecTriggerIssueRecordDetail l_detail =
                new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_detail.dataCode = "1001";
            String l_strTableName = "";
            String l_strInstitionCode = "0D";

            Object[] l_listValue = new Object[]
                {l_strTableName,
                 l_strInstitionCode,
                 l_detail};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class, WEB3AdminDirSecTriggerIssueRecordDetail.class});
            l_method.setAccessible(true);
            Object[] l_returnValue = (Object[])l_method.invoke(l_impl, l_listValue);
            assertEquals(1, l_returnValue.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsTradingTimeTableQuery_Case001()
    {
        final String STR_METHOD_NAME = "testIsTradingTimeTableQuery_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method l_method2 = l_impl.getClass().getDeclaredMethod(
                "isTradingTimeTableQuery",
                new Class[]{String.class, Object[].class});
            l_method2.setAccessible(true);

            String l_strNeed = "institution_code = ? and branch_code = ? and trading_time_type = ? and biz_date_type = ? and " +
            "not(start_time <= ? and end_time >= ? and submit_market_trigger = ?)";

            List l_lisValue = (List)l_method2.invoke(
                l_impl, new Object[]{l_strNeed, null});
            assertEquals(0, l_lisValue.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsTradingTimeTableQuery_Case002()
    {
        final String STR_METHOD_NAME = "testIsTradingTimeTableQuery_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecTriggerIssueRecordDetail l_detail =
                new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_detail.dataCode = "1001";
            String l_strTableName = "trading_time";
            String l_strInstitionCode = "0D";

            String l_strBizDateType =
                WEB3GentradeTradingTimeManagement.getBizDateType(GtlUtils.getSystemTimestamp());
            
            //TradingTimeParams
            l_queryProcessor.doDeleteAllQuery(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("624");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType(l_strBizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("000000");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_queryProcessor.doInsertQuery(l_tradingTimeParams);
            
            Method l_methodSet = l_impl.getClass().getDeclaredMethod(
                "setTradingTimeType",
                new Class[]{String.class});
            l_methodSet.setAccessible(true);
            l_methodSet.invoke(l_impl, new Object[]{"11"});

            Object[] l_listValue = new Object[]
                {l_strTableName,
                 l_strInstitionCode,
                 l_detail};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class, WEB3AdminDirSecTriggerIssueRecordDetail.class});
            l_method.setAccessible(true);
            Object[] l_returnValue = (Object[])l_method.invoke(l_impl, l_listValue);
            
            Method l_method2 = l_impl.getClass().getDeclaredMethod(
                "isTradingTimeTableQuery",
                new Class[]{String.class, Object[].class});
            l_method2.setAccessible(true);

            String l_strNeed = "institution_code = ? and branch_code = ? and trading_time_type = ? and biz_date_type = ? and " +
            "not(start_time <= ? and end_time >= ? and submit_market_trigger = ?)";

            List l_lisValue = (List)l_method2.invoke(
                l_impl, new Object[]{l_strNeed, l_returnValue});
            assertEquals(1, l_lisValue.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsTradingTimeTableQuery_Case003()
    {
        final String STR_METHOD_NAME = "testIsTradingTimeTableQuery_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            WEB3AdminDirSecTriggerIssueRecordDetail l_detail =
                new WEB3AdminDirSecTriggerIssueRecordDetail();
            l_detail.dataCode = "1001";
            String l_strTableName = "trading_time";
            String l_strInstitionCode = "0D";

            String l_strBizDateType =
                WEB3GentradeTradingTimeManagement.getBizDateType(GtlUtils.getSystemTimestamp());
            
            //TradingTimeParams
            l_queryProcessor.doDeleteAllQuery(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("624");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType(l_strBizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_queryProcessor.doInsertQuery(l_tradingTimeParams);
            
            Method l_methodSet = l_impl.getClass().getDeclaredMethod(
                "setTradingTimeType",
                new Class[]{String.class});
            l_methodSet.setAccessible(true);
            l_methodSet.invoke(l_impl, new Object[]{"11"});

            Object[] l_listValue = new Object[]
                {l_strTableName,
                 l_strInstitionCode,
                 l_detail};
            Method l_method = l_impl.getClass().getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, String.class, WEB3AdminDirSecTriggerIssueRecordDetail.class});
            l_method.setAccessible(true);
            Object[] l_returnValue = (Object[])l_method.invoke(l_impl, l_listValue);
            
            Method l_method2 = l_impl.getClass().getDeclaredMethod(
                "isTradingTimeTableQuery",
                new Class[]{String.class, Object[].class});
            l_method2.setAccessible(true);

            String l_strNeed = "institution_code = ? and branch_code = ? and trading_time_type = ? and biz_date_type = ? and " +
            "not(start_time <= ? and end_time >= ? and submit_market_trigger = ?)";

            List l_lisValue = (List)l_method2.invoke(
                l_impl, new Object[]{l_strNeed, l_returnValue});
            assertEquals(0, l_lisValue.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public SubmitTriggerInfoParams getSubmitTriggerInfoRow()
    {
        SubmitTriggerInfoParams l_params =
            new SubmitTriggerInfoParams();
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //データコード    request_code    VARCHAR2    6    NotNull
        l_params.setRequestCode("1001");
        //ユーザーデータ    user_data    VARCHAR2    6    Null
        l_params.setUserData("200804");
        //シェル名称    job_id    VARCHAR2    16    NotNull
        l_params.setJobId("123456");
        //識別No    id_no    VARCHAR2    2    NotNull
        l_params.setIdNo("01");
        //トリガー名称    trigger_id    VARCHAR2    44    NotNull
        l_params.setTriggerId("123456789");
        //顧客開始番号    account_start    NUMBER    15    NotNull
        l_params.setAccountStart(10001);
        //顧客終了番号    account_end    NUMBER    15    NotNull
        l_params.setAccountEnd(11000);
        //受付時間区分    trading_time_type    VARCHAR2    2    Null
        l_params.setTradingTimeType("01");
        //再発行可能フラグ    enable_submit_trigger_flag    VARCHAR2    1    NotNull
        l_params.setEnableSubmitTriggerFlag("1");
        //商品取扱区分    product_handling_div    VARCHAR2    1    NotNull
        l_params.setProductHandlingDiv("0");
        //更新日時    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
    
    public void testValidateTriggerIssueConfirmScreenDisplay_T001() 
    {
        
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_T001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
        
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();
        
        WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
        
        triggerIssueInfo.dataCode = "1";
        triggerIssueInfo.reissuePossibleFlag = "2";
        triggerIssueInfo.shellName="123";
        triggerIssueInfo.userData="4";
        triggerIssueInfo.triggerName = "5";

        l_request.triggerIssueInfo = triggerIssueInfo;

        try
        {
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                    true,
                    true);
            
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, false);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
//            SubmitTriggerInfoParams l_SubmitTriggerInfoParams = new SubmitTriggerInfoParams();
//            TestDBUtility.deleteAll(SubmitTriggerInfoParams.TYPE);
//            l_SubmitTriggerInfoParams.setAccountStart("1");
//            l_SubmitTriggerInfoParams.setAccountEnd("2");
//            l_SubmitTriggerInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080423","yyyyMMdd"));
//            l_SubmitTriggerInfoParams.setEnableSubmitTriggerFlag("1");
//            l_SubmitTriggerInfoParams.setIdNo("4");
//            l_SubmitTriggerInfoParams.setInstitutionCode("0D");
//            l_SubmitTriggerInfoParams.setJobId("123");
//            l_SubmitTriggerInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
//            l_SubmitTriggerInfoParams.setProductHandlingDiv("1");
//            l_SubmitTriggerInfoParams.setRequestCode("1");
//            l_SubmitTriggerInfoParams.setTradingTimeType("2");
//            l_SubmitTriggerInfoParams.setTriggerId("2");
//            l_SubmitTriggerInfoParams.setUserData("200802");
//            TestDBUtility.insertWithDel(l_SubmitTriggerInfoParams);
            l_impl.validateTriggerIssueConfirmScreenDisplay(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00857);
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testValidateTriggerIssueConfirmScreenDisplay_T002()
    {
        
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_T002";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
        
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();
        
        WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
        
        triggerIssueInfo.dataCode = "1";
        triggerIssueInfo.reissuePossibleFlag = "2";
        triggerIssueInfo.shellName="123";
        triggerIssueInfo.userData="4";
        triggerIssueInfo.triggerName = "5";

        l_request.triggerIssueInfo = triggerIssueInfo;

        try
        {
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                    true,
                    true);
            
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
            

            
            SubmitTriggerInfoParams l_SubmitTriggerInfoParams = new SubmitTriggerInfoParams();
            TestDBUtility.deleteAll(SubmitTriggerInfoParams.TYPE);
            l_SubmitTriggerInfoParams.setAccountStart(1);
            l_SubmitTriggerInfoParams.setAccountEnd(2);
            l_SubmitTriggerInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080423","yyyyMMdd"));
            l_SubmitTriggerInfoParams.setEnableSubmitTriggerFlag("1");
            l_SubmitTriggerInfoParams.setIdNo("4");
            l_SubmitTriggerInfoParams.setInstitutionCode("0D");
            l_SubmitTriggerInfoParams.setJobId("123");
            l_SubmitTriggerInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_SubmitTriggerInfoParams.setProductHandlingDiv("1");
            l_SubmitTriggerInfoParams.setRequestCode("1");
            l_SubmitTriggerInfoParams.setTradingTimeType("2");
            l_SubmitTriggerInfoParams.setTriggerId("2");
            l_SubmitTriggerInfoParams.setUserData("200802");
            TestDBUtility.insertWithDel(l_SubmitTriggerInfoParams);
            
            l_impl.validateTriggerIssueConfirmScreenDisplay(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03070);
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testValidateTriggerIssueConfirmScreenDisplay_T003() 
    {
        
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_T003";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
        
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();
        
        WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
        
        triggerIssueInfo.dataCode = "1";
        triggerIssueInfo.reissuePossibleFlag = "2";
        triggerIssueInfo.shellName="123";
        triggerIssueInfo.userData="4";
        triggerIssueInfo.triggerName = "5";

        l_request.triggerIssueInfo = triggerIssueInfo;

        try
        {
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                    true,
                    true);
            
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
           
            SubmitTriggerInfoParams l_SubmitTriggerInfoParams = new SubmitTriggerInfoParams();
            TestDBUtility.deleteAll(SubmitTriggerInfoParams.TYPE);
            l_SubmitTriggerInfoParams.setAccountStart(1);
            l_SubmitTriggerInfoParams.setAccountEnd(2);
            l_SubmitTriggerInfoParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080423","yyyyMMdd"));
            l_SubmitTriggerInfoParams.setEnableSubmitTriggerFlag("7");
            l_SubmitTriggerInfoParams.setIdNo("4");
            l_SubmitTriggerInfoParams.setInstitutionCode("0D");
            l_SubmitTriggerInfoParams.setJobId("123");
            l_SubmitTriggerInfoParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_SubmitTriggerInfoParams.setProductHandlingDiv("1");
            l_SubmitTriggerInfoParams.setRequestCode("1");
            l_SubmitTriggerInfoParams.setTradingTimeType("2");
            l_SubmitTriggerInfoParams.setTriggerId("2");//l_response.triggerIssueInfo.triggerName
            l_SubmitTriggerInfoParams.setUserData("200802");
            TestDBUtility.insertWithDel(l_SubmitTriggerInfoParams);
            
            MqMessageIdMappingsParams l_MqMessageIdMappingsParams = new MqMessageIdMappingsParams();
            TestDBUtility.deleteAll(MqMessageIdMappingsParams.TYPE);
            l_MqMessageIdMappingsParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_MqMessageIdMappingsParams.setDataCode("1");
            l_MqMessageIdMappingsParams.setInstitutionCode("0D");
            l_MqMessageIdMappingsParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_MqMessageIdMappingsParams.setMessageId("123");
            TestDBUtility.insertWithDel(l_MqMessageIdMappingsParams);
            
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_TradingTimeParams = new TradingTimeParams();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("381");
            l_TradingTimeParams.setMarketCode("09");
            l_TradingTimeParams.setTradingTimeType("2");
            l_TradingTimeParams.setProductCode("1");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setStartTime("160000");
            
            l_TradingTimeParams.setEndTime("160059");
            
            
            l_TradingTimeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            l_TradingTimeParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_TradingTimeParams);

            WEB3AdminDirSecTriggerIssueConfirmResponse l_response = 
                l_impl.validateTriggerIssueConfirmScreenDisplay(l_request);
            
            assertEquals(l_response.triggerIssueInfo.dataCode,"1");
            assertEquals(l_response.triggerIssueInfo.reissuePossibleFlag,"7");
            assertEquals(l_response.triggerIssueInfo.shellName,"123");
            assertEquals(l_response.triggerIssueInfo.triggerName,"2");

            assertEquals(l_response.messageWarning[0],null);
            assertEquals(l_response.messageWarning[1],"ただいまの時間はMQトリガーを発行できない可能性があります");
            
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testValidateTriggerIssueConfirmScreenDisplay_T004() 
    {
        
        final String STR_METHOD_NAME = "testValidateTriggerIssueConfirmScreenDisplay_T004";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
        
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request =
            new WEB3AdminDirSecTriggerIssueConfirmRequest();
        
        WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
        
        triggerIssueInfo.dataCode = "1";
        triggerIssueInfo.reissuePossibleFlag = "2";
        triggerIssueInfo.shellName="123";
        triggerIssueInfo.userData="4";
        triggerIssueInfo.triggerName = "5";

        l_request.triggerIssueInfo = null;

        try
        {
            l_impl.validateTriggerIssueConfirmScreenDisplay(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02837);
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    /*
     * Test method for 'webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecTriggerIssueServiceImpl.submitTriggerIssueCompleteScreenDisplay(WEB3AdminDirSecTriggerIssueCompleteRequest)'
     */
    public void testSubmitTriggerIssueCompleteScreenDisplay_T001()
    {
       final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_T001";
       log.entering(STR_METHOD_NAME);
       
       
       WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
       
       WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
           new WEB3AdminDirSecTriggerIssueCompleteRequest();
       
       WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
       
       triggerIssueInfo.dataCode = "1";
       triggerIssueInfo.reissuePossibleFlag = "2";
       triggerIssueInfo.shellName="3";
       triggerIssueInfo.userData="4";
       triggerIssueInfo.triggerName = "5";
       
       l_request.password = "123";
       l_request.triggerIssueInfo = triggerIssueInfo;
 
       
       try
       {
//           MOCK_MANAGER.setIsMockUsed(true);
//           LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
//           
//           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//               "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
//               "OpLoginSecurityServiceImpl",
//               "getLoginInfo",
//               new Class[] {},
//               l_loginInfoImpl);
//           
//           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                   "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                   "getLoginId",
//                   new Class[] {},
//                   new Long(33381330003L));
//
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
                   "send",
                   new Class[] {WEB3MQMessageSpec.class},
                   null);
		   
           TestDBUtility.deleteAll(AdministratorParams.TYPE);
           AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           l_administratorParams.setLoginId(33381330003L);
           l_administratorParams.setBranchCode("381");
           l_administratorParams.setInstitutionCode("0D");
           l_administratorParams.setPermissionLevel("0");
           TestDBUtility.insertWithDel(l_administratorParams);
           WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
           
           WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
           
           WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                   WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                   true,
                   true);
           
           WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
           WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
           
           //AdminPermissionParams
           
           TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
           AdminPermissionParams l_adminPermissionParams =
               TestDBUtility.getAdminPermissionRow();
           l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
           l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
           l_adminPermissionParams.setTransactionCategory("Z0101");
           l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
           TestDBUtility.insertWithDel(l_adminPermissionParams);
           
           //AdministratorTypeParams
           TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
           AdministratorTypeParams l_adminTypeParams =
               TestDBUtility.getAdministratorTypeRow();
           l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
           l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
           l_adminTypeParams.setDirAdminFlag(1);
           TestDBUtility.insertWithDel(l_adminTypeParams);
           
           WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
           
           MqMessageIdMappingsParams l_MqMessageIdMappingsParams = new MqMessageIdMappingsParams();
           TestDBUtility.deleteAll(MqMessageIdMappingsParams.TYPE);
           l_MqMessageIdMappingsParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
           l_MqMessageIdMappingsParams.setDataCode("1");
           l_MqMessageIdMappingsParams.setInstitutionCode("0D");
           l_MqMessageIdMappingsParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
           l_MqMessageIdMappingsParams.setMessageId("123");
           TestDBUtility.insertWithDel(l_MqMessageIdMappingsParams);
           
           TestDBUtility.commit();
           MpdsSettingsParams l_MpdsSettingsParams = new MpdsSettingsParams();
           l_MpdsSettingsParams.setSettingCateg("db.cluster.sid");
           l_MpdsSettingsParams.setSettingName("123");
           l_MpdsSettingsParams.setSettingValue("1");
           TestDBUtility.deleteAll(MpdsSettingsParams.TYPE);
           TestDBUtility.insertWithDel(l_MpdsSettingsParams);
                      
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "com.fitechlabs.xtrade.plugin.util.rac.stdimpl.RoundRobinBasedMultiPoolDataSourceImpl",
               "getJndiName",
               new Class[] {},"123");

           l_impl.submitTriggerIssueCompleteScreenDisplay(l_request);

       }
       catch (WEB3BaseException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           fail();
       }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTriggerIssueCompleteScreenDisplay_T002()
    {
       final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_T002";
       log.entering(STR_METHOD_NAME);
       
       
       WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
       
       WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
           new WEB3AdminDirSecTriggerIssueCompleteRequest();
       
       WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
       
       triggerIssueInfo.dataCode = "1";
       triggerIssueInfo.reissuePossibleFlag = "2";
       triggerIssueInfo.shellName="3";
       triggerIssueInfo.userData="4";
       triggerIssueInfo.triggerName = "5";
       
       l_request.password = "123";
       l_request.triggerIssueInfo = triggerIssueInfo;
 
       
       try
       {

           TestDBUtility.deleteAll(AdministratorParams.TYPE);
           AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           l_administratorParams.setLoginId(33381330003L);
           l_administratorParams.setBranchCode("381");
           l_administratorParams.setInstitutionCode("0D");
           l_administratorParams.setPermissionLevel("0");
           TestDBUtility.insertWithDel(l_administratorParams);
           
           WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
           
           WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
           
           WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                   WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                   true,
                   true);
           
           WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, false);
           
           
           WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

           l_impl.submitTriggerIssueCompleteScreenDisplay(l_request);
           fail();
           
           
       }
       catch (WEB3BaseException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00857);

       }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitTriggerIssueCompleteScreenDisplay_T004()
    {
       final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_T004";
       log.entering(STR_METHOD_NAME);
       
       
       WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
       
       WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
           new WEB3AdminDirSecTriggerIssueCompleteRequest();
       
       WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
       
       triggerIssueInfo.dataCode = "1";
       triggerIssueInfo.reissuePossibleFlag = "2";
       triggerIssueInfo.shellName="3";
       triggerIssueInfo.userData="4";
       triggerIssueInfo.triggerName = "5";
       
       l_request.password = "123";
       l_request.triggerIssueInfo = triggerIssueInfo;
 
       
       try
       {
//           MOCK_MANAGER.setIsMockUsed(true);
//           LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
//           
//           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//               "com.fitechlabs.xtrade.plugin.security.oplogin.impl." +
//               "OpLoginSecurityServiceImpl",
//               "getLoginInfo",
//               new Class[] {},
//               l_loginInfoImpl);
//           
//           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                   "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                   "getLoginId",
//                   new Class[] {},
//                   new Long(33381330003L));
//
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
                   "send",
                   new Class[] {WEB3MQMessageSpec.class},
                   null);
           
           TestDBUtility.deleteAll(AdministratorParams.TYPE);
           AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           l_administratorParams.setLoginId(33381330003L);
           l_administratorParams.setBranchCode("381");
           l_administratorParams.setInstitutionCode("0D");
           l_administratorParams.setPermissionLevel("0");
           TestDBUtility.insertWithDel(l_administratorParams);
           WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
           
           WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
           
           WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                   WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                   true,
                   true);
           
           WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
           WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
           
           //AdminPermissionParams
           
           TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
           AdminPermissionParams l_adminPermissionParams =
               TestDBUtility.getAdminPermissionRow();
           l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
           l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
           l_adminPermissionParams.setTransactionCategory("Z0102");
           l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
           TestDBUtility.insertWithDel(l_adminPermissionParams);
           
           //AdministratorTypeParams
           TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
           AdministratorTypeParams l_adminTypeParams =
               TestDBUtility.getAdministratorTypeRow();
           l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
           l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
           l_adminTypeParams.setDirAdminFlag(1);
           TestDBUtility.insertWithDel(l_adminTypeParams);
           
           WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
           
           MqMessageIdMappingsParams l_MqMessageIdMappingsParams = new MqMessageIdMappingsParams();
           TestDBUtility.deleteAll(MqMessageIdMappingsParams.TYPE);
           l_MqMessageIdMappingsParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
           l_MqMessageIdMappingsParams.setDataCode("1");
           l_MqMessageIdMappingsParams.setInstitutionCode("0D");
           l_MqMessageIdMappingsParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
           l_MqMessageIdMappingsParams.setMessageId("123");
           TestDBUtility.insertWithDel(l_MqMessageIdMappingsParams);
           
           WEB3AdminDirSecTriggerIssueCompleteResponse l_response = 
           l_impl.submitTriggerIssueCompleteScreenDisplay(l_request);

       }
       catch (WEB3BusinessLayerException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01056);
       }
       catch (WEB3BaseException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           fail();
       }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitTriggerIssueCompleteScreenDisplay_T005()
    {
       final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_T005";
       log.entering(STR_METHOD_NAME);
       
       
       WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
       
       WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
           new WEB3AdminDirSecTriggerIssueCompleteRequest();
       
       WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();

       l_request.triggerIssueInfo = null;
 
       
       try
       {
           
           WEB3AdminDirSecTriggerIssueCompleteResponse l_response = 
           l_impl.submitTriggerIssueCompleteScreenDisplay(l_request);

       }
       catch (WEB3BusinessLayerException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02837);
       }
       catch (WEB3BaseException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           fail();
       }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitTriggerIssueCompleteScreenDisplay_T003()
    {
       final String STR_METHOD_NAME = "testSubmitTriggerIssueCompleteScreenDisplay_T003";
       log.entering(STR_METHOD_NAME);
       
       
       WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
       
       WEB3AdminDirSecTriggerIssueCompleteRequest l_request =
           new WEB3AdminDirSecTriggerIssueCompleteRequest();
       
       WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo = new WEB3AdminDirSecTriggerIssueRecordDetail();
       
       triggerIssueInfo.dataCode = "1";
       triggerIssueInfo.reissuePossibleFlag = "2";
       triggerIssueInfo.shellName="3";
       triggerIssueInfo.userData="4";
       triggerIssueInfo.triggerName = "5";
       
       l_request.password = "123";
       l_request.triggerIssueInfo = triggerIssueInfo;
 
       
       try
       {

           TestDBUtility.deleteAll(AdministratorParams.TYPE);
           AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           l_administratorParams.setLoginId(33381330003L);
           l_administratorParams.setBranchCode("381");
           l_administratorParams.setInstitutionCode("0D");
           l_administratorParams.setPermissionLevel("0");
           TestDBUtility.insertWithDel(l_administratorParams);
           
           WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
           
           WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
           
           WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                   WEB3TransactionCategoryDef.HOST_STATUS_UPDATE,
                   true,
                   true);
           
           WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
           WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
           
           TestDBUtility.deleteAll(MqMessageIdMappingsParams.TYPE);
           
           l_impl.submitTriggerIssueCompleteScreenDisplay(l_request);
           fail();
           
           
       }
       catch (WEB3BaseException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03070);

       }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    
    
    
    
    
    public void testcreateWarningMessage_T001()
    {
        final String STR_METHOD_NAME = "testcreateWarningMessage_T001";
        log.entering(STR_METHOD_NAME);
        
        List l_lisA = new ArrayList();
        List l_lisB = new ArrayList();

        l_lisB.add("3");
        try
        {
            Object[] obj = new Object[]{l_lisA, l_lisB};
            
            Method me = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "createWarningMessage",
                new Class[]{List.class,List.class});
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            me.setAccessible(true);
           
            
            String[] l_str = (String[])me.invoke(l_impl,obj);
            
            assertEquals(l_str[0],"トリガー発行情報テーブルに入力したデータコードが存在しません");
            assertNull(l_str[1]);
        
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
            
        } 
    }
    
    public void testcreateWarningMessage_T002()
    {
        final String STR_METHOD_NAME = "testcreateWarningMessage_T002";
        log.entering(STR_METHOD_NAME);
        
        
        List l_lisA = new ArrayList();
        List l_lisB = new ArrayList();
        
        l_lisA.add("1");

        try
        {
            Object[] obj = new Object[]{l_lisA, l_lisB};
            
            Method me = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "createWarningMessage",
                new Class[]{List.class,List.class});
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            me.setAccessible(true);
           
            
            String[] l_str = (String[])me.invoke(l_impl,obj);
            
          
            assertNull(l_str[0]);
            assertEquals(l_str[1],"ただいまの時間はMQトリガーを発行できない可能性があります");
            
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
            
        } 
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateTriggerIssueRecordDetail()
    {
        final String STR_METHOD_NAME = "testcreateTriggerIssueRecordDetail";
        log.entering(STR_METHOD_NAME);
        
        List l_lisA = new ArrayList();
        
        SubmitTriggerInfoParams l_submitTriggerInfoParams = new SubmitTriggerInfoParams();
        
        l_submitTriggerInfoParams.setJobId("1");
        l_submitTriggerInfoParams.setTriggerId("2");
        l_submitTriggerInfoParams.setEnableSubmitTriggerFlag("3");
        l_submitTriggerInfoParams.setUserData("20080101");
        l_submitTriggerInfoParams.setRequestCode("q");

        l_lisA.add(l_submitTriggerInfoParams);
        
        
        try
        {
            Object[] obj = new Object[]{l_lisA};
            
            Method me = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "createTriggerIssueRecordDetail",
                new Class[]{List.class});
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            me.setAccessible(true);
           
            
            WEB3AdminDirSecTriggerIssueRecordDetail  l_RecordDetail =
                (WEB3AdminDirSecTriggerIssueRecordDetail)me.invoke(l_impl,obj);
 
            assertEquals(l_RecordDetail.shellName,"1");
            assertEquals(l_RecordDetail.triggerName,"2");
            assertEquals(l_RecordDetail.reissuePossibleFlag,"3");
            assertEquals(l_RecordDetail.userData,"20080101");
            assertEquals(l_RecordDetail.dataCode,"q");
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
            
        } 
        log.exiting(STR_METHOD_NAME);
    }
    
   
    
    public void testcreateSortKey_T001()
    {
        final String STR_METHOD_NAME = "testcreateSortKey_T001";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
            new WEB3AdminDirSecTriggerIssueSortKey[2];

        l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
        
        
        l_sortKeys[0].keyItem = WEB3AdminDirSecSortKeyItemDef.DATA_CODE;
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
        
        
        l_sortKeys[1] = new WEB3AdminDirSecTriggerIssueSortKey();
        l_sortKeys[1].keyItem = WEB3AdminDirSecSortKeyItemDef.REISSUE_POSSIBLE_FLAG;
        l_sortKeys[1].ascDesc = WEB3AscDescDef.DESC;
        
        
        try
        {
            Object[] obj = new Object[]{l_sortKeys};
            
            Method me = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminDirSecTriggerIssueSortKey[].class});
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            me.setAccessible(true);

            String l_str = (String)me.invoke(l_impl,obj);

            assertEquals(l_str," request_code  ASC , enable_submit_trigger_flag  DESC ");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcreateSortKey_T002()
    {
        final String STR_METHOD_NAME = "testcreateSortKey_T002";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
            new WEB3AdminDirSecTriggerIssueSortKey[1];

        l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
        
        
        l_sortKeys[0].keyItem = WEB3AdminDirSecSortKeyItemDef.DATA_CODE;
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
        
        
        try
        {
            Object[] obj = new Object[]{l_sortKeys};
            
            Method me = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminDirSecTriggerIssueSortKey[].class});
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            me.setAccessible(true);

            String l_str = (String)me.invoke(l_impl,obj);

            assertEquals(l_str," request_code  ASC ");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcreateSortKey_T003()
    {
        final String STR_METHOD_NAME = "testcreateSortKey_T003";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
            new WEB3AdminDirSecTriggerIssueSortKey[0];
        
        
        try
        {
            Object[] obj = new Object[]{l_sortKeys};
            
            Method me = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminDirSecTriggerIssueSortKey[].class});
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            me.setAccessible(true);

            String l_str = (String)me.invoke(l_impl,obj);

            assertEquals(l_str,"");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testcreateSortKey_T004()
    {
        final String STR_METHOD_NAME = "testcreateSortKey_T004";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminDirSecTriggerIssueSortKey[] l_sortKeys =
            new WEB3AdminDirSecTriggerIssueSortKey[1];

        l_sortKeys[0] = new WEB3AdminDirSecTriggerIssueSortKey();
        
        
        l_sortKeys[0].keyItem = "dataCodes";
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
        
        
        try
        {
            Object[] obj = new Object[]{l_sortKeys};
            
            Method me = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminDirSecTriggerIssueSortKey[].class});
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            me.setAccessible(true);

            String l_str = (String)me.invoke(l_impl,obj);

            assertEquals(l_str,"");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            
        }
        log.exiting(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testsetTradingTimeType_getTradingTimeType()
    {
        
        try
        {
            Object[] obj = new Object[]{"fenght"};
            
            Method meset = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                "setTradingTimeType",
                new Class[]{String.class});
            
            Method meget = WEB3AdminDirSecTriggerIssueServiceImpl.class.getDeclaredMethod(
                    "getTradingTimeType",
                    null);
            
            WEB3AdminDirSecTriggerIssueServiceImpl l_impl = new WEB3AdminDirSecTriggerIssueServiceImpl();
            meset.setAccessible(true);
            meget.setAccessible(true);
            meset.invoke(l_impl,obj);

            String l_str = (String)meget.invoke(l_impl,null);

            
            assertEquals(l_str,"fenght");

            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
            
        }
        
        
    }
    
    public class WEB3AdminDirSecTriggerIssueServiceImplForTest extends WEB3AdminDirSecTriggerIssueServiceImpl
    {
        protected WEB3AdminDirSecTriggerIssueCompleteResponse submitTriggerIssueCompleteScreenDisplay(
            WEB3AdminDirSecTriggerIssueCompleteRequest l_request) throws WEB3BaseException
        {
            return null;
        }
        
        
    }

}
@
