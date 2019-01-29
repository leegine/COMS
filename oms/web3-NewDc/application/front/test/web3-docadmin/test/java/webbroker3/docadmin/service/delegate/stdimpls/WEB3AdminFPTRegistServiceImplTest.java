head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputResponse;
import webbroker3.docadmin.message.WEB3FPTBatoProductCodeAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentCategoryDetailsInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTRegistServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTRegistServiceImplTest.class);

    WEB3AdminFPTRegistServiceImpl l_impl = new WEB3AdminFPTRegistServiceImpl();
   
    public WEB3AdminFPTRegistServiceImplTest(String arg0)
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

    public void testExecute_T01()
    {
        final String STR_METHOD_NAME = "testExecute_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_T02()
    {
        final String STR_METHOD_NAME = "testExecute_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(new WEB3AccTransChangeFromIfoDepositCompleteRequest());
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_T03()
    {
        final String STR_METHOD_NAME = "testExecute_T03()";
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
            TestDBUtility.insertWithDel(l_docParams);
            

//          DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            WEB3AdminFPTRegistInputRequest l_request = new WEB3AdminFPTRegistInputRequest();
            
            WEB3AdminFPTRegistInputResponse l_response =
                (WEB3AdminFPTRegistInputResponse)l_impl.execute(l_request);
            
            
            assertEquals(1, l_response.documentCategoryList.length);
            
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_T04()
    {
        final String STR_METHOD_NAME = "testExecute_T04()";
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
            
            WEB3AdminFPTRegistConfirmResponse l_response =
                (WEB3AdminFPTRegistConfirmResponse)l_impl.execute(l_request);
            
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

    public void testExecute_T05()
    {
        final String STR_METHOD_NAME = "testExecute_T05()";
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

            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.password = "654321";
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            WEB3AdministratorForMock.mockValidateTradingPassword("654321", true);

            l_impl.execute(l_request);

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
    
    public void testGetInputScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T01()";
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
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
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
    
    public void testGetInputScreen_T02()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("G0102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminFPTRegistInputRequest l_request = new WEB3AdminFPTRegistInputRequest();
            l_impl.getInputScreen(l_request);
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
    
    public void testGetInputScreen_T03()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T03()";
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
            TestDBUtility.insertWithDel(l_docParams);
            
            //DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            WEB3AdminFPTRegistInputRequest l_request = new WEB3AdminFPTRegistInputRequest();
            WEB3AdminFPTRegistInputResponse l_response = l_impl.getInputScreen(l_request);
            
            assertEquals(1, l_response.documentCategoryList.length);

            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangedScreen_T01()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();
            l_request.branchCode = null;
            l_impl.validateChangedScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangedScreen_T02()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T02()";
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
            
            l_impl.validateChangedScreen(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
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

    public void testValidateChangedScreen_T03()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("G0102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "11";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            l_impl.validateChangedScreen(l_request);
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

    public void testValidateChangedScreen_T04()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T04()";
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
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_request.branchCode = "789";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "11";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            l_impl.validateChangedScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangedScreen_T05()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T05()";
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
            
            l_request.branchCode = "789";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "11";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            l_impl.validateChangedScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangedScreen_T06()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
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

            
//          DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            //DocDeliveryManagementRow.TYPE
            //TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            
//            DocDeliveryManagementParams l_docDeliveryManagementParams =  this.getDocDeliveryManagementParams();
//
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
//            
            
            
            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            

            WEB3AdminFPTRegistConfirmResponse l_response =
                l_impl.validateChangedScreen(l_request);
            

            assertEquals("", l_response.productName);
            assertEquals("jiddk", l_response.acceptName);
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangedScreen_T07()
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
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

            
//          DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            //DocDeliveryManagementRow.TYPE
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            
            DocDeliveryManagementParams l_docDeliveryManagementParams =  this.getDocDeliveryManagementParams();

            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            
            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = new Date("2008/01/31");
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            

            WEB3AdminFPTRegistConfirmResponse l_response =
                l_impl.validateChangedScreen(l_request);
            

           fail();
           
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02950, l_ex.getErrorInfo());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testCreateQueryString_T001(){
        
        final String STR_METHOD_NAME = "testCreateQueryString_T001()";
        log.entering(STR_METHOD_NAME);
        
        
        WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();
        
        l_request.branchCode = "624";
        l_request.acceptCode = "123456";

        l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
        l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
        
        
        try
        {
            Method l_method = WEB3AdminFPTRegistServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3GenRequest.class});
            l_method.setAccessible(true);
            
            String l_strQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});
            

            assertEquals(" account_id = ? and ( (document_div = ?  and document_category = ?  and product_code in ( ? )) ) and delivery_date = ? ", l_strQueryString);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        log.exiting(STR_METHOD_NAME);
        
 
    }
    
    
    public void testCreateQueryDataContainer_T001(){
        
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_T001()";
        log.entering(STR_METHOD_NAME);
        
        Long l_lngAccountId = new  Long(1234L);
        
        WEB3AdminFPTRegistConfirmRequest l_request = new WEB3AdminFPTRegistConfirmRequest();
        
        l_request.branchCode = "624";
        l_request.acceptCode = "123456";

        l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
        l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
        
        
        try
        {
            Method l_method = WEB3AdminFPTRegistServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{long.class,WEB3GenRequest.class});
            l_method.setAccessible(true);
            
            Object[] l_object = (Object[])l_method.invoke(l_impl, new Object[]{l_lngAccountId,l_request});
            

            assertEquals("1234", String.valueOf(l_object[0]));
            assertEquals("1", l_object[1]);
            assertEquals("321", l_object[2]);
            assertEquals("1234", l_object[3]);
            assertEquals(GtlUtils.getSystemTimestamp(), l_object[4]);

            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }


        log.exiting(STR_METHOD_NAME);

    }
    
 public void testCreateQueryDataContainer_T002(){
        
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_T002()";
        log.entering(STR_METHOD_NAME);
        
        Long l_lngAccountId = new  Long(1234L);
        
        WEB3AdminFPTRegistCompleteRequest l_request = new WEB3AdminFPTRegistCompleteRequest();
        
        l_request.branchCode = "624";
        l_request.acceptCode = "123456";

        l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
        l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
        
        
        try
        {
            Method l_method = WEB3AdminFPTRegistServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{long.class,WEB3GenRequest.class});
            l_method.setAccessible(true);
            
            Object[] l_object = (Object[])l_method.invoke(l_impl, new Object[]{l_lngAccountId,l_request});
            

            assertEquals("1234", String.valueOf(l_object[0]));
            assertEquals("1", l_object[1]);
            assertEquals("321", l_object[2]);
            assertEquals("1234", l_object[3]);
            assertEquals(GtlUtils.getSystemTimestamp(), l_object[4]);

            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }


        log.exiting(STR_METHOD_NAME);

    }
    

    
    public void testSubmitChangedScreen_T01()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistCompleteRequest l_request = new WEB3AdminFPTRegistCompleteRequest();
            l_impl.submitChangedScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChangedScreen_T02()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T02()";
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
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            
            l_impl.submitChangedScreen(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
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
    
    
    public void testSubmitChangedScreen_T03()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFPTRegistCompleteRequest l_request = new WEB3AdminFPTRegistCompleteRequest();
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("G0102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            l_impl.submitChangedScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testSubmitChangedScreen_T04()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T04()";
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
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_request.branchCode = "789";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            l_impl.submitChangedScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testSubmitChangedScreen_T05()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
//            Services.overrideService(OpLoginSecurityService.class,
//                new OpLoginSecurityServiceImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            Map l_map = new HashMap();
            l_map.put("TRADING_PWD_ENV", "0");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
                
            
            WEB3AdminFPTRegistCompleteRequest l_request = new WEB3AdminFPTRegistCompleteRequest();
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);


            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_mainAccountParams.setGivenName(null);
            l_mainAccountParams.setFamilyName("jiddk");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
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
            

            
//          DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            //DocDeliveryManagementRow.TYPE
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams =  this.getDocDeliveryManagementParams();
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            
            
            l_request.branchCode = "624";
            l_request.acceptCode = "123456";

            l_request.docuDeliDate = new Date("2008/01/31");
            
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            l_impl.submitChangedScreen(l_request);
            
            
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02950, l_ex.getErrorInfo());
            //assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangedScreen_T06()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T06()";
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
            
            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
//            l_request.productCode = "1001";
//            l_request.documentDiv = "1";
//            l_request.docuCheckDiv = "4";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.password = "654321";
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            
            WEB3AdministratorForMock.mockValidateTradingPassword("654321", true);
            
            l_impl.submitChangedScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01987, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitChangedScreen_T07()
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T07()";
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
            
            
            //DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            //DocDeliveryManagementRow.TYPE
           
            
//            DocDeliveryManagementParams l_docDeliveryManagementParams =  this.getDocDeliveryManagementParams();
//            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
//            
            
            
            l_request.branchCode = "624";
            l_request.acceptCode = "123456";
            l_request.docuDeliDate = GtlUtils.getSystemTimestamp();
            l_request.password = "654321";
            l_request.documentCategoryList = this.getDocumentCategoryDetailsInfoUnitLists();
            
            WEB3AdministratorForMock.mockValidateTradingPassword("654321", true);

            l_impl.submitChangedScreen(l_request);

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
        l_params.setDocumentName("fenght");
        //更新者コード  last_updater      VARCHAR2   20   Null
        //作成日付  created_timestamp   DATE      NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付  last_updated_timestamp      DATE      NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_params.setDocumentCategory("123");
     
        
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
    
    public DocDeliveryManagementParams getDocDeliveryManagementParams(){
        
        DocDeliveryManagementParams l_docDeliveryManagementParams = new DocDeliveryManagementParams();
        
        l_docDeliveryManagementParams.setInstitutionCode("0D");
        l_docDeliveryManagementParams.setBranchCode("624");
        l_docDeliveryManagementParams.setAccountCode("123456");
        l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.TRUE);
        
        l_docDeliveryManagementParams.setAccountId(333812512246L);
        l_docDeliveryManagementParams.setDocumentDiv("1");
        l_docDeliveryManagementParams.setDocumentCategory("321");
        l_docDeliveryManagementParams.setProductCode("1234");
        
        l_docDeliveryManagementParams.setDeliveryDate(new Date("2008/01/31"));
        
        
        l_docDeliveryManagementParams.setCreatedTimestamp(new Date("2008/01/31"));
        l_docDeliveryManagementParams.setLastUpdatedTimestamp(new Date("2008/01/31"));
        
        return l_docDeliveryManagementParams;
        
    }
}
@
