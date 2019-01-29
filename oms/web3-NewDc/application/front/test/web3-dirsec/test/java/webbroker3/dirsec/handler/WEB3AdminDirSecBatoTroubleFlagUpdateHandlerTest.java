head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecBatoTroubleFlagUpdateHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.handler;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecBatoPreferenceRecordDetail;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecBatoTroubleFlagUpdateService;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BatoInstBranchPrefParams;
import webbroker3.gentrade.data.BatoInstBranchPrefRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecBatoTroubleFlagUpdateHandlerTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecBatoTroubleFlagUpdateHandlerTest.class);

    WEB3AdminDirSecBatoTroubleFlagUpdateHandler l_handler;

    public WEB3AdminDirSecBatoTroubleFlagUpdateHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3AdminDirSecBatoTroubleFlagUpdateHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetListScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            WEB3AdminDirSecWorkingListResponse l_response = l_handler.getListScreen(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
                    new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_Case002()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode = null;
            WEB3AdminDirSecWorkingListResponse l_response = l_handler.getListScreen(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.BUSINESS_ERROR_01429);
        }
        catch (Exception l_ex)
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

            WEB3AdminDirSecWorkingListResponse l_response = l_handler.getListScreen(l_request);
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

    public void testValidateChangeConfirmScreen_Case001()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecWorkingConfirmResponse l_response = l_handler.validateChangeConfirmScreen(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
                    new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangeConfirmScreen_Case002()
    {
        final String STR_METHOD_NAME = "testValidateChangeConfirmScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            l_request.batoPreferenceRecord = null;
            WEB3AdminDirSecWorkingConfirmResponse l_response = l_handler.validateChangeConfirmScreen(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.BUSINESS_ERROR_03076);
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

            WEB3AdminDirSecWorkingConfirmResponse l_response = l_handler.validateChangeConfirmScreen(l_request);
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
            Services.unregisterService(WEB3AdminDirSecBatoTroubleFlagUpdateService.class);
            WEB3AdminDirSecWorkingCompleteRequest l_request =
                new WEB3AdminDirSecWorkingCompleteRequest();
            WEB3AdminDirSecWorkingCompleteResponse l_response = l_handler.submitChangeCompleteScreen(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
                    new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeCompleteScreen_Case002()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCompleteScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingCompleteRequest l_request =
                new WEB3AdminDirSecWorkingCompleteRequest();
            l_request.batoPreferenceRecord = null;
            WEB3AdminDirSecWorkingCompleteResponse l_response = l_handler.submitChangeCompleteScreen(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.BUSINESS_ERROR_03076);
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

            WEB3AdminDirSecWorkingCompleteResponse l_response = l_handler.submitChangeCompleteScreen(l_request);

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
