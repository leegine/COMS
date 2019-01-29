head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTRegistHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.handler;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputResponse;
import webbroker3.docadmin.message.WEB3FPTBatoProductCodeAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentCategoryDetailsInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTRegistService;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTRegistServiceImpl;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTRegistServiceImplTest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTRegistHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTRegistServiceImplTest.class);

    WEB3AdminFPTRegistHandler l_handler = new WEB3AdminFPTRegistHandler();
    
    public WEB3AdminFPTRegistHandlerTest(String arg0)
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
        this.deleteDB();
    }

    public void testGetRegistInput_T01()
    {
        final String STR_METHOD_NAME = "testGetRegistInput_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistInputRequest l_request = new WEB3AdminFPTRegistInputRequest();
            Services.unregisterService(WEB3AdminFPTRegistService.class);
            WEB3AdminFPTRegistInputResponse l_response =
                l_handler.getRegistInput(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminFPTRegistService.class,
                    new WEB3AdminFPTRegistServiceImpl());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetRegistInput_T02()
    {
        final String STR_METHOD_NAME = "testGetRegistInput_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            WEB3AdminFPTRegistInputRequest l_request = new WEB3AdminFPTRegistInputRequest();
            WEB3AdminFPTRegistInputResponse l_response =
                l_handler.getRegistInput(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetRegistInput_T03()
    {
        final String STR_METHOD_NAME = "testGetRegistInput_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("G0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //DocDivManagementParams
            DocDivManagementParams l_docParams = this.getDocDivManagementRow();
            l_docParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docParams.setDocumentCategory("111");
            TestDBUtility.insertWithDel(l_docParams);

            WEB3AdminFPTRegistInputRequest l_request = new WEB3AdminFPTRegistInputRequest();
            WEB3AdminFPTRegistInputResponse l_response = l_handler.getRegistInput(l_request);
//            assertEquals(1, l_response.documentDivList.length);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetRegistConfirm_T01()
    {
        final String STR_METHOD_NAME = "testGetRegistConfirm_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();
            Services.unregisterService(WEB3AdminFPTRegistService.class);
            WEB3AdminFPTRegistConfirmResponse l_response =
                l_handler.getRegistConfirm(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminFPTRegistService.class,
                    new WEB3AdminFPTRegistServiceImpl());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetRegistConfirm_T02()
    {
        final String STR_METHOD_NAME = "testGetRegistConfirm_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "11";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();

            WEB3AdminFPTRegistConfirmResponse l_response =
                l_handler.getRegistConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetRegistConfirm_T03()
    {
        final String STR_METHOD_NAME = "testGetRegistConfirm_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("G0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_mainAccountParams.setGivenName(null);
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();

            WEB3AdminFPTRegistConfirmResponse l_response =
                l_handler.getRegistConfirm(l_request);
            assertEquals("", l_response.productName);
            assertEquals("jiddk", l_response.acceptName);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetRegistComplete_T01()
    {
        final String STR_METHOD_NAME = "testGetRegistComplete_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistCompleteRequest l_request = new WEB3AdminFPTRegistCompleteRequest();
            Services.unregisterService(WEB3AdminFPTRegistService.class);
            WEB3AdminFPTRegistCompleteResponse l_response =
                l_handler.getRegistComplete(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                Services.registerService(
                    WEB3AdminFPTRegistService.class,
                    new WEB3AdminFPTRegistServiceImpl());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetRegistComplete_T02()
    {
        final String STR_METHOD_NAME = "testGetRegistComplete_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistCompleteRequest l_request = new WEB3AdminFPTRegistCompleteRequest();
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "11";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();

            WEB3AdminFPTRegistCompleteResponse l_response =
                l_handler.getRegistComplete(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetRegistComplete_T03()
    {
        final String STR_METHOD_NAME = "testGetRegistComplete_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistCompleteRequest l_request = new WEB3AdminFPTRegistCompleteRequest();
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("G0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_mainAccountParams.setGivenName(null);
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.password = "654321";
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();

            WEB3AdministratorForMock.mockValidateTradingPassword("654321", true);

            l_handler.getRegistComplete(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(DocDeliveryManagementParams.TYPE);
            assertEquals(1, l_lisResult.size());
            DocDeliveryManagementRow l_managementRow = (DocDeliveryManagementRow)l_lisResult.get(0);
            assertEquals(l_mainAccountParams.getAccountId(), l_managementRow.getAccountId());
            assertEquals(l_administratorParams.getInstitutionCode(), l_managementRow.getInstitutionCode());
            assertEquals("624", l_managementRow.getBranchCode());
            assertEquals("123456", l_managementRow.getAccountCode());
            assertEquals("1", l_managementRow.getDocumentDiv());
            assertEquals("1234", l_managementRow.getProductCode());
            assertEquals(BooleanEnum.FALSE, l_managementRow.getDeleteFlag());
            assertEquals(l_administratorParams.getAdministratorCode(), l_managementRow.getLastUpdater());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void deleteDB()
    {
        try
        {
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            
            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            //DocDivManagementParams
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            
            //DocDeliveryManagementParams
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
        }
        catch(Exception l_ex)
        {
            fail();
        }
        
        
    }

    public DocDivManagementParams getDocDivManagementRow()
    {
        DocDivManagementParams l_params = new DocDivManagementParams();
        //証券会社コード  institution_code     VARCHAR2   3   NotNull
        l_params.setInstitutionCode("0D");
        //部店コード  branch_code    VARCHAR2   3   NotNull
        l_params.setBranchCode("624");
        //書面区分  document_div    VARCHAR2   3   NotNull
        l_params.setDocumentDiv("1");
        //書面チェック区分  document_check_div      VARCHAR2   2   NotNull
        l_params.setDocumentCheckDiv("2");
        //書面通番  document_number     VARCHAR2   2   NotNull
        l_params.setDocumentNumber("3");
        //書面名称  document_name   VARCHAR2   40   Null
        //更新者コード  last_updater      VARCHAR2   20   Null
        //作成日付  created_timestamp   DATE      NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付  last_updated_timestamp      DATE      NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
    public  WEB3FPTDocumentCategoryDetailsInfoUnit[] getDocumentCategoryDetailsInfoUnitLists(){


        WEB3FPTDocumentCategoryDetailsInfoUnit[] l_fptDCategoryDetailsInfoUnits =
            new WEB3FPTDocumentCategoryDetailsInfoUnit[1];
        
        
        WEB3FPTDocumentCategoryDetailsInfoUnit l_WEB3FPTDocumentCategoryDetailsInfoUnit = 
            new WEB3FPTDocumentCategoryDetailsInfoUnit();
        
        
        WEB3FPTDocumentDivAdminInfoUnit l_fptDocumentDivAdminInfoUnit = 
            new WEB3FPTDocumentDivAdminInfoUnit();
        
        l_fptDocumentDivAdminInfoUnit.documentDiv = "1";
        l_fptDocumentDivAdminInfoUnit.docuCheckDiv = "3" ;
        l_fptDocumentDivAdminInfoUnit.documentCategory ="321";
        
        
        
        WEB3FPTBatoProductCodeAdminInfoUnit[] l_fptBatoProductCodeAdminInfoUnit = 
            new WEB3FPTBatoProductCodeAdminInfoUnit[1];
        
        WEB3FPTBatoProductCodeAdminInfoUnit l_BatoProductCodeAdminInfoUnit = 
            new WEB3FPTBatoProductCodeAdminInfoUnit();

        
        l_BatoProductCodeAdminInfoUnit.batoProductCode="1234";
        l_BatoProductCodeAdminInfoUnit.validFlag = "0";
        
        l_fptBatoProductCodeAdminInfoUnit[0] = l_BatoProductCodeAdminInfoUnit;
        
        
        // 書面区分管理情報
        l_WEB3FPTDocumentCategoryDetailsInfoUnit.documentDivList =l_fptDocumentDivAdminInfoUnit;
        // 電子鳩銘柄コード管理情報
        l_WEB3FPTDocumentCategoryDetailsInfoUnit.batoProductCodeAdminInfo = l_fptBatoProductCodeAdminInfoUnit;

        
        l_fptDCategoryDetailsInfoUnits[0] = l_WEB3FPTDocumentCategoryDetailsInfoUnit;
        
        
        return l_fptDCategoryDetailsInfoUnits;
        
    }
}
@
