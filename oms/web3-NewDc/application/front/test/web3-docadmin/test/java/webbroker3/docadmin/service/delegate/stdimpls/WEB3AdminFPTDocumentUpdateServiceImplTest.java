head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentUpdateServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.define.WEB3AdminFPTProcessFlagDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentUpdateInfoUnit;
import webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImplTest.LoginInfoImplTest;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDocumentUpdateServiceImplTest extends TestBaseForMock {

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateServiceImplTest.class);

    
    public WEB3AdminFPTDocumentUpdateServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3AdminFPTDocumentUpdateServiceImpl l_impl = 
        new WEB3AdminFPTDocumentUpdateServiceImpl();
    
    WEB3AdminFPTDocumentUpdateServiceImplForTest l_implFortest =
        new WEB3AdminFPTDocumentUpdateServiceImplForTest();
    
    
    /*
     * Test method for 'webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute_T001() 
    {
        final String STR_METHOD_NAME = "testExecute_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenRequest l_request = null;
        
        try
        {
            
            l_impl.execute(l_request);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            
            log.error(l_ex.getErrorMessage(),l_ex);
            
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    public void testExecute_T002() 
    {
        final String STR_METHOD_NAME = "testExecute_T002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateInputRequest l_request = new WEB3AdminFPTDocumentUpdateInputRequest();
     
        try
        {
            
            WEB3AdminFPTDocumentUpdateInputResponse  l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse) l_implFortest.execute(l_request);
            
            assertEquals(l_response.documentDivList[0].documentDiv , "1");
            assertEquals(l_response.documentDivList[0].documentNames,"aaa");
            assertEquals(l_response.documentDivList[0].docuCheckDiv,"3");
            assertEquals(l_response.documentDivList[0].documentCategory,"123");
            assertEquals(l_response.documentDivList[0].documentCategoryName,"abc");
        }
        catch (WEB3BaseException l_ex)
        {

            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        
        
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testExecute_T003() 
    {
        final String STR_METHOD_NAME = "testExecute_T003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateConfirmRequest l_request =
            new WEB3AdminFPTDocumentUpdateConfirmRequest();
        
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        l_documentUpdateInfoUnit[0] = new WEB3FPTDocumentUpdateInfoUnit();
        l_documentUpdateInfoUnit[0].documentDiv ="1";
        l_documentUpdateInfoUnit[0].documentCategory="101";
        l_documentUpdateInfoUnit[0].documentNumber="2222";
        l_documentUpdateInfoUnit[0].validFlag="0";
        l_documentUpdateInfoUnit[0].remarks="摘要摘要";
        l_documentUpdateInfoUnit[0].documentCategoryName="abc";
        l_documentUpdateInfoUnit[0].batoProductCode="1012222";
        
       l_request.documentUpdateList = l_documentUpdateInfoUnit;
       
       l_request.updateProcessFlag = WEB3AdminFPTProcessFlagDivDef.INSERT;
       
        try
        {
            
            WEB3AdminFPTDocumentUpdateConfirmResponse  l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse) l_implFortest.execute(l_request);
            
            assertEquals(l_response.batoProductCode,l_request.documentUpdateList[0].documentCategory + l_request.documentUpdateList[0].documentNumber);
           
        }
        catch (WEB3BaseException l_ex)
        {

            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testExecute_T004() 
    {
        final String STR_METHOD_NAME = "testExecute_T004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request = new WEB3AdminFPTDocumentUpdateCompleteRequest();
        
        
        
        try
        {
            WEB3AdminFPTDocumentUpdateCompleteResponse l_response =
            (WEB3AdminFPTDocumentUpdateCompleteResponse)l_implFortest.execute(l_request);

            assertNull(l_response);
            
            
        }
        catch (WEB3BaseException l_ex)
        {

            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }

    public void testExecute_T005() 
    {
        final String STR_METHOD_NAME = "testExecute_T005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListSearchInputRequest l_request = 
            new WEB3AdminFPTDocumentListSearchInputRequest();

        try
        {
                l_implFortest.execute(l_request);
                fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /*
     * Test method for 'webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImpl.getInputScreen(WEB3AdminFPTDocumentUpdateInputRequest)'
     */
    public void testGetInputScreen_T001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateInputRequest l_request = 
            new WEB3AdminFPTDocumentUpdateInputRequest();
        
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1001));

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_UPLOAD);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("fenght");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("123");
        
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
           
            //DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("feng_h_t");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            WEB3AdminFPTDocumentUpdateInputResponse l_response =
                l_impl.getInputScreen(l_request);
            
            
            assertEquals(l_response.documentDivList[0].docuCheckDiv,"1");
            assertEquals(l_response.documentDivList[0].documentCategory,"123");
            assertEquals(l_response.documentDivList[0].documentCategoryName,"feng_h_t");
            assertEquals(l_response.documentDivList[0].documentDiv,"1");
            assertEquals(l_response.documentDivList[0].documentNames,"fenght");
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImpl.validateChangedScreen(WEB3AdminFPTDocumentUpdateConfirmRequest)'
     */
    public void testValidateChangedScreen_T001() 
    {
        final String STR_METHOD_NAME = "testValidateChangedScreen_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateConfirmRequest l_request = 
            new WEB3AdminFPTDocumentUpdateConfirmRequest();
        
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        l_documentUpdateInfoUnit[0] = new WEB3FPTDocumentUpdateInfoUnit();
        l_documentUpdateInfoUnit[0].documentDiv ="1";
        l_documentUpdateInfoUnit[0].documentCategory="101";
        l_documentUpdateInfoUnit[0].documentNumber="2222";
        l_documentUpdateInfoUnit[0].validFlag="0";
        l_documentUpdateInfoUnit[0].remarks="摘要摘要";
        l_documentUpdateInfoUnit[0].documentCategoryName="abc";
        l_documentUpdateInfoUnit[0].batoProductCode="1012222";
        
       l_request.documentUpdateList = l_documentUpdateInfoUnit;
       
       l_request.updateProcessFlag = WEB3AdminFPTProcessFlagDivDef.INSERT;
       
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1001));

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_UPLOAD);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("fenght");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("123");
        
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
           
            //DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("feng_h_t");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("9991111");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            
            
            WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
                l_impl.validateChangedScreen(l_request);
        

            assertEquals(l_response.batoProductCode,"1012222");
            
        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        
        
        
        
        log.exiting(STR_METHOD_NAME);
        

    }

    /*
     * Test method for 'webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentUpdateServiceImpl.submitChangedScreen(WEB3AdminFPTDocumentUpdateCompleteRequest)'
     */
    //insert
    public void testSubmitChangedScreen_T001() 
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request = 
            new WEB3AdminFPTDocumentUpdateCompleteRequest();
        
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        l_documentUpdateInfoUnit[0] = new WEB3FPTDocumentUpdateInfoUnit();
        l_documentUpdateInfoUnit[0].documentDiv ="1";
        l_documentUpdateInfoUnit[0].documentCategory="101";
        l_documentUpdateInfoUnit[0].documentNumber="2222";
        l_documentUpdateInfoUnit[0].validFlag="0";
        l_documentUpdateInfoUnit[0].remarks="摘要摘要";
        l_documentUpdateInfoUnit[0].documentCategoryName="abc";
        l_documentUpdateInfoUnit[0].batoProductCode="1012222";
        
       l_request.documentUpdateList = l_documentUpdateInfoUnit;
       
       l_request.updateProcessFlag = WEB3AdminFPTProcessFlagDivDef.INSERT;
       l_request.password = "101";
       
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1001));

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();

            TestDBUtility.insertWithDel(l_BranchParams);
            
            
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_UPLOAD);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("fenght");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("123");
        
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
           
            //DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("feng_h_t");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow.TYPE
//            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
//            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
//            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
//            l_batoProductManagementParams.setDocumentDiv("1");
//            l_batoProductManagementParams.setBatoProductCode("1234");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            
            l_impl.submitChangedScreen(l_request);
            
            
            Object[] l_obj =  new Object[]{"0D","381","1012222","1"};
            List l_lisBatoProductManagementRows = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisBatoProductManagementRows =
                l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    "institution_code = ? and BRANCH_CODE = ? and BATO_PRODUCT_CODE = ? and DOCUMENT_DIV =?",
                    null,
                    null,
                    l_obj);
            
            assertEquals(l_lisBatoProductManagementRows.size(),1);
              
            
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
        

    }
    
    //upadte
    public void testSubmitChangedScreen_T002() 
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request = 
            new WEB3AdminFPTDocumentUpdateCompleteRequest();
        
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        l_documentUpdateInfoUnit[0] = new WEB3FPTDocumentUpdateInfoUnit();
        l_documentUpdateInfoUnit[0].documentDiv ="1";
        l_documentUpdateInfoUnit[0].documentCategory="101";
        l_documentUpdateInfoUnit[0].documentNumber="2222";
        l_documentUpdateInfoUnit[0].validFlag="1";
        l_documentUpdateInfoUnit[0].remarks="摘要摘要";
        l_documentUpdateInfoUnit[0].documentCategoryName="abc";
        l_documentUpdateInfoUnit[0].batoProductCode="1012222";
        
       l_request.documentUpdateList = l_documentUpdateInfoUnit;
       
       l_request.updateProcessFlag = WEB3AdminFPTProcessFlagDivDef.UPDATE;
       l_request.password = "101";
       
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1001));

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setAdministratorCode("330001");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_UPLOAD);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("fenght");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("123");
        
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
           
            //DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("feng_h_t");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1012222");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            
            l_impl.submitChangedScreen(l_request);
            
            
            Object[] l_obj =  new Object[]{"0D","381","1012222","1"};
            List l_lisBatoProductManagementRows = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisBatoProductManagementRows =
                l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    "institution_code = ? and BRANCH_CODE = ? and BATO_PRODUCT_CODE = ? and DOCUMENT_DIV =?",
                    null,
                    null,
                    l_obj);
            
            
            BatoProductManagementRow l_BatoProductManagementRow =
                (BatoProductManagementRow)l_lisBatoProductManagementRows.get(0);
            
            
            assertEquals(l_BatoProductManagementRow.getLastUpdater(),l_administratorParams.getAdministratorCode());
            
            assertEquals(l_lisBatoProductManagementRows.size(),1);
            
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
        

    }
    
    
    //delete
    public void testSubmitChangedScreen_T003() 
    {
        final String STR_METHOD_NAME = "testSubmitChangedScreen_T003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request = 
            new WEB3AdminFPTDocumentUpdateCompleteRequest();
        
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit[1];
        
        l_documentUpdateInfoUnit[0] = new WEB3FPTDocumentUpdateInfoUnit();
        l_documentUpdateInfoUnit[0].documentDiv ="1";
        l_documentUpdateInfoUnit[0].documentCategory="101";
        l_documentUpdateInfoUnit[0].documentNumber="2222";
        l_documentUpdateInfoUnit[0].validFlag="1";
        l_documentUpdateInfoUnit[0].remarks="摘要摘要";
        l_documentUpdateInfoUnit[0].documentCategoryName="abc";
        l_documentUpdateInfoUnit[0].batoProductCode="1012222";
        
       l_request.documentUpdateList = l_documentUpdateInfoUnit;
       
       l_request.updateProcessFlag = WEB3AdminFPTProcessFlagDivDef.DELETE;
       l_request.password = "123";
       
        
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1001));

            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_UPLOAD);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("fenght");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("123");
        
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
           
            //DocCategoryManagementRow.TYPE,
            DocCategoryManagementParams l_docCategoryParams = new DocCategoryManagementParams();
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            l_docCategoryParams.setBranchCode(l_administratorParams.getBranchCode());
            l_docCategoryParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_docCategoryParams.setDocumentCategory("123");
            l_docCategoryParams.setDocumentCateName("feng_h_t");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1012222");
            l_batoProductManagementParams.setValidFlag("1");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            
            l_impl.submitChangedScreen(l_request);
            
            
            Object[] l_obj =  new Object[]{"1012222","1"};
            List l_lisBatoProductManagementRows = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisBatoProductManagementRows =
                l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    " BATO_PRODUCT_CODE = ? and DOCUMENT_DIV =?",
                    null,
                    null,
                    l_obj);
            
            assertEquals(l_lisBatoProductManagementRows.size(),0);
            
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        
        

    }
    
    //#########################################################################################################33
    
    public class WEB3AdminFPTDocumentUpdateServiceImplForTest extends WEB3AdminFPTDocumentUpdateServiceImpl
    {
        
        protected WEB3AdminFPTDocumentUpdateInputResponse getInputScreen(
                WEB3AdminFPTDocumentUpdateInputRequest l_request) throws WEB3BaseException
            {
            
//          createResponse( )
            WEB3AdminFPTDocumentUpdateInputResponse l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();

            
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists = new WEB3FPTDocumentDivAdminInfoUnit[1];
            
            l_documentDivLists[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_documentDivLists[0].documentDiv = "1";
            l_documentDivLists[0].documentNames="aaa";
            l_documentDivLists[0].docuCheckDiv="3";
            l_documentDivLists[0].documentCategory="123";
            l_documentDivLists[0].documentCategoryName="abc";
            
            
            //プロパティセット
            l_response.documentDivList = l_documentDivLists;

            return l_response;
            
            }
        
        protected WEB3AdminFPTDocumentUpdateConfirmResponse validateChangedScreen(
            WEB3AdminFPTDocumentUpdateConfirmRequest l_request) throws WEB3BaseException
            {
                WEB3AdminFPTDocumentUpdateConfirmResponse l_response =
                    (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();
    
                
                
                if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(l_request.updateProcessFlag))
                {
                    l_response.batoProductCode = l_request.documentUpdateList[0].documentCategory +
                        l_request.documentUpdateList[0].documentNumber;
                }

                return l_response;

            }
        
        
        protected WEB3AdminFPTDocumentUpdateCompleteResponse submitChangedScreen(
                WEB3AdminFPTDocumentUpdateCompleteRequest l_request) throws WEB3BaseException
            {
                
                return null;
            }

    }
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
    

}
@
