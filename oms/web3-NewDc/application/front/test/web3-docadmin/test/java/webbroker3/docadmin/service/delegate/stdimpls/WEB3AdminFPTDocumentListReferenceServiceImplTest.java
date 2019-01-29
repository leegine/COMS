head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocumentListReferenceServiceImplTest.java;


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
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.docadmin.define.WEB3AdminFPTSortKeyItemDef;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSortKey;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentUpdateInfoUnit;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDocumentListReferenceServiceImplTest extends
        TestBaseForMock {

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceServiceImplTest.class);

    
    public WEB3AdminFPTDocumentListReferenceServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3AdminFPTDocumentListReferenceServiceImpl l_impl = 
        new WEB3AdminFPTDocumentListReferenceServiceImpl();
    
    WEB3AdminFPTDocumentListReferenceServiceImplForTest l_implForTest = 
        new WEB3AdminFPTDocumentListReferenceServiceImplForTest();
    
    
    /*
     * Test method for 'webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute_T001() 
    {
        final String STR_METHOD_NAME = "testExecute()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListSearchInputRequest l_request = null;
        
        try
        {

            l_implForTest.execute(l_request);
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
        final String STR_METHOD_NAME = "testExecute()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListSearchInputRequest l_request = 
            new WEB3AdminFPTDocumentListSearchInputRequest();
        
        try
        {

            WEB3AdminFPTDocumentListSearchInputResponse l_response = 
                (WEB3AdminFPTDocumentListSearchInputResponse) l_implForTest.execute(l_request);
            

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
        final String STR_METHOD_NAME = "testExecute()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentListReferenceRequest l_request = 
            new WEB3AdminFPTDocumentListReferenceRequest();
        
        try
        {

            WEB3AdminFPTDocumentListReferenceResponse l_response =
                (WEB3AdminFPTDocumentListReferenceResponse)l_implForTest.execute(l_request);
            
            assertEquals(l_response.documentCategoryList[0].batoProductCode,"1012222");
            assertEquals(l_response.documentCategoryList[0].documentCategory,"101");
            assertEquals(l_response.documentCategoryList[0].documentCategoryName,"abc");
            assertEquals(l_response.documentCategoryList[0].documentDiv,"1");
            assertEquals(l_response.documentCategoryList[0].documentNumber,"2222");
            
            
            
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
        final String STR_METHOD_NAME = "testExecute()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request = 
            new WEB3AdminFPTDocumentUpdateCompleteRequest();
        
        try
        {

            l_implForTest.execute(l_request);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    

    /*
     * Test method for 'webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImpl.getDocumentReferenceSearchInput(WEB3AdminFPTDocumentListSearchInputRequest)'
     */
    //########################################################################################################
    public void testGetDocumentReferenceSearchInput() 
    {
        final String STR_METHOD_NAME = "testGetDocumentReferenceSearchInput()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminFPTDocumentListSearchInputRequest l_request = 
                new WEB3AdminFPTDocumentListSearchInputRequest();
            
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
            l_docDivManagementParams.setDocumentName("そこです");
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
            
            
            WEB3AdminFPTDocumentListSearchInputResponse l_response =
                (WEB3AdminFPTDocumentListSearchInputResponse) l_impl.getDocumentReferenceSearchInput(l_request);
            
            assertEquals(l_response.documentDivList[0].docuCheckDiv,"1");
            assertEquals(l_response.documentDivList[0].documentNames,"そこです");
            assertEquals(l_response.documentDivList[0].documentCategoryName,"fenght");
            assertEquals(l_response.documentDivList[0].documentDiv,"1");
            
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
            
        }

        log.exiting(STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.docadmin.service.delegate.stdimpls.WEB3AdminFPTDocumentListReferenceServiceImpl.getDocumentReferenceList(WEB3AdminFPTDocumentListReferenceRequest)'
     */
    public void testGetDocumentReferenceList_T001() 
    {
        final String STR_METHOD_NAME = "testGetDocumentReferenceList_T001()";
        log.entering(STR_METHOD_NAME);
        
        
        
        WEB3AdminFPTDocumentListReferenceRequest l_request = 
            new WEB3AdminFPTDocumentListReferenceRequest();
        
        WEB3FPTDocumentDivAdminInfoUnit[] documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];

        documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
        
        documentDivList[0].documentDiv = "1";
        documentDivList[0].documentNames="aaa";
        documentDivList[0].docuCheckDiv="3";
        documentDivList[0].documentCategory="123";
        documentDivList[0].documentCategoryName="abc";

        l_request.documentDivList = documentDivList;
        
        WEB3AdminFPTSortKey[] l_sortKeys = new WEB3AdminFPTSortKey[1];
        l_sortKeys[0] = new WEB3AdminFPTSortKey();
        l_sortKeys[0].keyItem = WEB3AdminFPTSortKeyItemDef.VALID_FLAG;
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
        
        l_request.sortKeys = l_sortKeys;
        
        
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
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_UPLOAD);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //DocDivManagementParams
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("そこです");
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
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            

            WEB3AdminFPTDocumentListReferenceResponse l_response = 
                (WEB3AdminFPTDocumentListReferenceResponse)l_impl.getDocumentReferenceList(l_request);

            assertEquals(l_response.documentCategoryList[0].batoProductCode,"1234567");
            assertEquals(l_response.documentCategoryList[0].documentCategory,"123");
            assertEquals(l_response.documentCategoryList[0].documentCategoryName,"fenght");
            assertEquals(l_response.documentCategoryList[0].documentNumber,"4567");

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
            
        }

        log.exiting(STR_METHOD_NAME);

    }

    
    public void testGetDocumentReferenceList_T002() 
    {
        final String STR_METHOD_NAME = "testGetDocumentReferenceList_T002()";
        log.entering(STR_METHOD_NAME);
        
        
        
        WEB3AdminFPTDocumentListReferenceRequest l_request = 
            new WEB3AdminFPTDocumentListReferenceRequest();
        
        WEB3FPTDocumentDivAdminInfoUnit[] documentDivList = new WEB3FPTDocumentDivAdminInfoUnit[1];

        documentDivList[0] = new WEB3FPTDocumentDivAdminInfoUnit();
        
        documentDivList[0].documentDiv = "1";
        documentDivList[0].documentNames="aaa";
        documentDivList[0].docuCheckDiv="3";
        documentDivList[0].documentCategory="222";
        documentDivList[0].documentCategoryName="abc";

        l_request.documentDivList = documentDivList;
        
        WEB3AdminFPTSortKey[] l_sortKeys = new WEB3AdminFPTSortKey[1];
        l_sortKeys[0] = new WEB3AdminFPTSortKey();
        l_sortKeys[0].keyItem = WEB3AdminFPTSortKeyItemDef.VALID_FLAG;
        l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
        
        l_request.sortKeys = l_sortKeys;
        
        
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
            
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("0");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_UPLOAD);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //DocDivManagementParams
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("そこです");
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
            l_docCategoryParams.setDocumentCateName("fenght");
            TestDBUtility.insertWithDel(l_docCategoryParams);

            //BatoProductManagementRow
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            

            WEB3AdminFPTDocumentListReferenceResponse l_response = 
                (WEB3AdminFPTDocumentListReferenceResponse)l_impl.getDocumentReferenceList(l_request);

           fail();
           
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    
    public void testcreateDocumentList_T001()
    {
        final String STR_METHOD_NAME = "testcreateDocumentList_T001()";
        log.entering(STR_METHOD_NAME);
        

        try
        {

            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
  
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
         
            Object[] l_obj =  new Object[]{"0D"};

            List l_lisBatoProductManagementRows = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisBatoProductManagementRows =
                l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    "institution_code = ? ",
                    null,
                    null,
                    l_obj);
            
 
         Object[] obj = {l_lisBatoProductManagementRows};
         Method method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                 "createDocumentList",
                new Class[]{List.class});
         method.setAccessible(true);

         WEB3FPTDocumentUpdateInfoUnit[] l_DocumentUpdateInfoUnit = 
             (WEB3FPTDocumentUpdateInfoUnit[])method.invoke(l_impl, obj);
         

         assertEquals(l_DocumentUpdateInfoUnit[0].batoProductCode,"1234567");
         
         assertEquals(l_DocumentUpdateInfoUnit[0].documentCategory,"123");
         assertEquals(l_DocumentUpdateInfoUnit[0].documentNumber,"4567");
         assertEquals(l_DocumentUpdateInfoUnit[0].documentDiv,"1");
         
         
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        

        log.exiting(STR_METHOD_NAME);
        
        
    }
    
    public void testcreateDocumentList_T002()
    {
        final String STR_METHOD_NAME = "testcreateDocumentList_T002()";
        log.entering(STR_METHOD_NAME);
        

        try
        {

            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
  
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("1");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
         
            
            l_batoProductManagementParams.setDocumentDiv("2");
            l_batoProductManagementParams.setBatoProductCode("1239999");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_batoProductManagementParams.setDocumentDiv("3");
            l_batoProductManagementParams.setBatoProductCode("1238888");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            
            
            
            Object[] l_obj =  new Object[]{"0D"};

            List l_lisBatoProductManagementRows = null;
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisBatoProductManagementRows =
                l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    "institution_code = ? ",
                    null,
                    null,
                    l_obj);
            
 
         Object[] obj = {l_lisBatoProductManagementRows};
         Method method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                 "createDocumentList",
                new Class[]{List.class});
         method.setAccessible(true);

         WEB3FPTDocumentUpdateInfoUnit[] l_DocumentUpdateInfoUnit = 
             (WEB3FPTDocumentUpdateInfoUnit[])method.invoke(l_impl, obj);
         

         assertEquals(l_DocumentUpdateInfoUnit[0].batoProductCode,"1234567");
         assertEquals(l_DocumentUpdateInfoUnit[0].documentCategory,"123");
         assertEquals(l_DocumentUpdateInfoUnit[0].documentNumber,"4567");
         assertEquals(l_DocumentUpdateInfoUnit[0].documentDiv,"1");
         
         assertEquals(l_DocumentUpdateInfoUnit[1].batoProductCode,"1239999");
         assertEquals(l_DocumentUpdateInfoUnit[1].documentCategory,"123");
         assertEquals(l_DocumentUpdateInfoUnit[1].documentNumber,"9999");
         assertEquals(l_DocumentUpdateInfoUnit[1].documentDiv,"2");
         
         assertEquals(l_DocumentUpdateInfoUnit[2].batoProductCode,"1238888");
         assertEquals(l_DocumentUpdateInfoUnit[2].documentCategory,"123");
         assertEquals(l_DocumentUpdateInfoUnit[2].documentNumber,"8888");
         assertEquals(l_DocumentUpdateInfoUnit[2].documentDiv,"3");
         
         assertEquals(l_DocumentUpdateInfoUnit[2].documentCategoryName,"fenght");
         
         
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        

        log.exiting(STR_METHOD_NAME);
        
        
    }
    
    
    
    public void testcreateQueryString_T001()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_T001()";
        log.entering(STR_METHOD_NAME);
        
       try
       {

           WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists = new WEB3FPTDocumentDivAdminInfoUnit[1];
     
           l_documentDivLists[0] = new WEB3FPTDocumentDivAdminInfoUnit();
           l_documentDivLists[0].documentDiv = "1";

           /**
            * (書面名称)<BR>
            */
           l_documentDivLists[0].documentNames="aaa";
           /**
            * (書面チェック区分)<BR>
            * 1：IPO<BR>
            * 2：投信<BR>
            * 3：金商法@<BR>
            */
           l_documentDivLists[0].docuCheckDiv="3";
           /**
            * (書面種類コード)<BR>
            */
           l_documentDivLists[0].documentCategory="123";
           /**
            * (書面種類名称)<BR>
            */
           l_documentDivLists[0].documentCategoryName="abc";

           
           //createQueryString(WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists)
         
 
        Object[] obj = {l_documentDivLists};
        Method method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
               new Class[]{WEB3FPTDocumentDivAdminInfoUnit[].class});
        method.setAccessible(true);
       
        String l_createQueryString =
            (String)method.invoke(l_impl, obj);
        
        assertEquals(" institution_code = ?  and branch_code = ?  and document_div = ? and bato_product_code like ? || '%' ",
                l_createQueryString);
        

       }
       catch(Exception l_ex)
       {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
       }
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testcreateQueryString_T002()
    {
        final String STR_METHOD_NAME = "testcreateQueryString_T002()";
        log.entering(STR_METHOD_NAME);
        
       try
       {

           WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists = new WEB3FPTDocumentDivAdminInfoUnit[3];
     
           l_documentDivLists[0] = new WEB3FPTDocumentDivAdminInfoUnit();
           l_documentDivLists[1] = new WEB3FPTDocumentDivAdminInfoUnit();
           l_documentDivLists[2] = new WEB3FPTDocumentDivAdminInfoUnit();
           
           //createQueryString(WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists)
         
 
        Object[] obj = {l_documentDivLists};
        Method method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
               new Class[]{WEB3FPTDocumentDivAdminInfoUnit[].class});
        method.setAccessible(true);
       
        String l_createQueryString =
            (String)method.invoke(l_impl, obj);

        assertEquals(" institution_code = ?  and branch_code = ?  and ((document_div = ? and bato_product_code like ? || '%') " +
                " or (document_div = ? and bato_product_code like ? || '%') " +
                " or (document_div = ? and bato_product_code like ? || '%') )",
                l_createQueryString);
        

       }
       catch(Exception l_ex)
       {
           log.error(STR_METHOD_NAME, l_ex);
           log.exiting(STR_METHOD_NAME);
           fail();
       }
        log.exiting(STR_METHOD_NAME);

    }
    
    
//    private Object[] createQueryDataContainer(String l_strInstitutionCode,
//            String l_strBranchCode,
//            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists)
//        {
    
    
    public void testcreateQueryDataContainer_T001()
    {
        final String STR_METHOD_NAME = "testcreateQueryDataContainer_T001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists = new WEB3FPTDocumentDivAdminInfoUnit[1];
            
            l_documentDivLists[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            
            
            l_documentDivLists[0].documentDiv = "1";
            l_documentDivLists[0].documentNames="aaa";
            l_documentDivLists[0].docuCheckDiv="3";
            l_documentDivLists[0].documentCategory="123";
            l_documentDivLists[0].documentCategoryName="abc";


            Object[] obj = {"0D","381",l_documentDivLists};
            
            Method method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                    "createQueryDataContainer",
                   new Class[]{String.class,String.class,WEB3FPTDocumentDivAdminInfoUnit[].class});
            method.setAccessible(true);
            
            Object[] l_queryDataContainers = (Object[])method.invoke(l_impl,obj);
            
            
            assertEquals(4, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("381", l_queryDataContainers[1]);
            assertEquals("1", l_queryDataContainers[2]);
            assertEquals("123", l_queryDataContainers[3]);

        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testcreateQueryDataContainer_T002()
    {
        final String STR_METHOD_NAME = "testcreateQueryDataContainer_T002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists = new WEB3FPTDocumentDivAdminInfoUnit[2];
            
            l_documentDivLists[0] = new WEB3FPTDocumentDivAdminInfoUnit();
            l_documentDivLists[1] = new WEB3FPTDocumentDivAdminInfoUnit();
           
            
            l_documentDivLists[0].documentDiv = "1";
            l_documentDivLists[0].documentNames="aaa";
            l_documentDivLists[0].docuCheckDiv="3";
            l_documentDivLists[0].documentCategory="123";
            l_documentDivLists[0].documentCategoryName="abc";

            
            l_documentDivLists[1].documentDiv = "2";
            l_documentDivLists[1].documentCategory="456";
            

            

            Object[] obj = {"0D","381",l_documentDivLists};
            
            Method method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                    "createQueryDataContainer",
                   new Class[]{String.class,String.class,WEB3FPTDocumentDivAdminInfoUnit[].class});
            method.setAccessible(true);
            
            Object[] l_queryDataContainers = (Object[])method.invoke(l_impl,obj);
            
            
            assertEquals(6, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("381", l_queryDataContainers[1]);
            assertEquals("1", l_queryDataContainers[2]);
            assertEquals("123", l_queryDataContainers[3]);
            
            assertEquals("2", l_queryDataContainers[4]);
            assertEquals("456", l_queryDataContainers[5]);
            
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testcreateSortKeys_T001()
    {
        
        final String STR_METHOD_NAME = "testcreateSortKeys_T001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            WEB3AdminFPTSortKey[] l_sortKeys = new WEB3AdminFPTSortKey[1];
            
            l_sortKeys[0] = new WEB3AdminFPTSortKey();
            l_sortKeys[0].keyItem ="";
            l_sortKeys[0].ascDesc ="";
            

            Method l_method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKeys",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            
            
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_sortKeys});

            assertEquals(" ", l_strSortKey);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateSortKeys_T002()
    {
        
        final String STR_METHOD_NAME = "testcreateSortKeys_T002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            WEB3AdminFPTSortKey[] l_sortKeys = new WEB3AdminFPTSortKey[1];
            
            l_sortKeys[0] = new WEB3AdminFPTSortKey();
            l_sortKeys[0].keyItem = WEB3AdminFPTSortKeyItemDef.VALID_FLAG;
            l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            

            Method l_method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKeys",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);

            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_sortKeys});

            assertEquals(" valid_flag ASC  ", l_strSortKey);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testcreateSortKeys_T003()
    {
        
        final String STR_METHOD_NAME = "testcreateSortKeys_T003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            WEB3AdminFPTSortKey[] l_sortKeys = new WEB3AdminFPTSortKey[2];
            
            l_sortKeys[0] = new WEB3AdminFPTSortKey();
            l_sortKeys[0].keyItem = WEB3AdminFPTSortKeyItemDef.VALID_FLAG;
            l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            
            
            l_sortKeys[1] = new WEB3AdminFPTSortKey();
            l_sortKeys[1].keyItem = WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.DESC;
            

            Method l_method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKeys",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            
            
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_sortKeys});

            assertEquals(" valid_flag ASC  ,  substr(bato_product_code,1,3) DESC, substr(bato_product_code,4,4) ASC  ", l_strSortKey);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testcreateSortKeys_T004()
    {
        
        final String STR_METHOD_NAME = "testcreateSortKeys_T004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            WEB3AdminFPTSortKey[] l_sortKeys = new WEB3AdminFPTSortKey[2];
            
            l_sortKeys[0] = new WEB3AdminFPTSortKey();
            l_sortKeys[0].keyItem = WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE;
            l_sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            
            
            l_sortKeys[1] = new WEB3AdminFPTSortKey();
            l_sortKeys[1].keyItem = WEB3AdminFPTSortKeyItemDef.VALID_FLAG;
            l_sortKeys[1].ascDesc = WEB3AscDescDef.DESC;
            

            Method l_method = WEB3AdminFPTDocumentListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKeys",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            
            
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_sortKeys});

            assertEquals(" substr(bato_product_code,1,3) ASC, substr(bato_product_code,4,4) DESC  ,  valid_flag DESC  ", l_strSortKey);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    
    //################################################################################################################
    public class WEB3AdminFPTDocumentListReferenceServiceImplForTest extends WEB3AdminFPTDocumentListReferenceServiceImpl 
    {
        
        protected WEB3AdminFPTDocumentListSearchInputResponse getDocumentReferenceSearchInput(
                WEB3AdminFPTDocumentListSearchInputRequest l_request) throws WEB3BaseException
            {
                WEB3FPTDocumentDivAdminInfoUnit[] l_fptDocumentDivAdminInfoUnits = 
                    new WEB3FPTDocumentDivAdminInfoUnit[1];
                
                l_fptDocumentDivAdminInfoUnits[0] = new WEB3FPTDocumentDivAdminInfoUnit();
                
                l_fptDocumentDivAdminInfoUnits[0].documentDiv = "1";

                /**
                 * (書面名称)<BR>
                 * 書面名称<BR>
                 */
                l_fptDocumentDivAdminInfoUnits[0].documentNames="aaa";
                /**
                 * (書面チェック区分)<BR>
                 * 書面チェック区分<BR>
                 * <BR>
                 * 1：IPO<BR>
                 * 2：投信<BR>
                 * 3：金商法@<BR>
                 */
                l_fptDocumentDivAdminInfoUnits[0].docuCheckDiv="3";
                /**
                 * (書面種類コード)<BR>
                 * 書面種類コード<BR>
                 */
                l_fptDocumentDivAdminInfoUnits[0].documentCategory="123";
                /**
                 * (書面種類名称)<BR>
                 * 書面種類名称<BR>
                 */
                l_fptDocumentDivAdminInfoUnits[0].documentCategoryName="abc";

                
    
                WEB3AdminFPTDocumentListSearchInputResponse l_response =
                    (WEB3AdminFPTDocumentListSearchInputResponse) l_request.createResponse();
    
                //プロパティセット
                l_response.documentDivList = l_fptDocumentDivAdminInfoUnits;
                return l_response;
                        
            }
        
        protected WEB3AdminFPTDocumentListReferenceResponse getDocumentReferenceList(
                WEB3AdminFPTDocumentListReferenceRequest l_request) throws WEB3BaseException
            {
                WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit[1];
                
                l_documentUpdateInfoUnit[0] = new WEB3FPTDocumentUpdateInfoUnit();
                
                /**
                 * (書面区分)<BR>
                 * 書面区分<BR>
                 */
                l_documentUpdateInfoUnit[0].documentDiv ="1";

                /**
                 * (書面種類コード)<BR>
                 * 書面種類コード<BR>
                 */
                l_documentUpdateInfoUnit[0].documentCategory="101";

                /**
                 * (書面通番)<BR>
                 * 書面通番<BR>
                 */
                l_documentUpdateInfoUnit[0].documentNumber="2222";

                /**
                 * (有効区分)<BR>
                 * 有効区分<BR>
                 * <BR>
                 * 0：valid<BR>
                 * 1：invalid<BR>
                 */
                l_documentUpdateInfoUnit[0].validFlag="0";

                /**
                 * (摘要)<BR>
                 * 摘要<BR>
                 */
                l_documentUpdateInfoUnit[0].remarks="摘要摘要";

                /**
                 * (書面種類名称)<BR>
                 * 書面種類名称<BR>
                 */
                l_documentUpdateInfoUnit[0].documentCategoryName="abc";

                /**
                 * (電子鳩銘柄コード)<BR>
                 * 電子鳩銘柄コード<BR>
                 */
                l_documentUpdateInfoUnit[0].batoProductCode="1012222";
                
                WEB3AdminFPTDocumentListReferenceResponse l_response =
                    (WEB3AdminFPTDocumentListReferenceResponse) l_request.createResponse();
    
                //プロパティセット
                l_response.documentCategoryList = l_documentUpdateInfoUnit;
                
                return l_response;
            
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
