head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.26.06.01.39;	author liu-lei;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	3004db65fa72760;
filename	WEB3DocumentDeliverHistoryRegistServiceImplTest.java;

1.1
date	2011.04.07.01.59.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DocumentDeliverHistoryRegistServiceImplTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3DocumentDeliverHistoryRegistHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/22 陸文靜（中訊）新規作成
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.sql.Date;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.BatoBranchProductPrefParams;
import webbroker3.gentrade.data.BatoInstBranchPrefParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3DocumentDeliverHistoryRegistServiceImplTest extends TestBaseForMock
{    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3DocumentDeliverHistoryRegistServiceImplTest.class);
    public WEB3DocumentDeliverHistoryRegistServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    protected void setUp() throws Exception
    {
        super.setUp();
    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        WEB3DocumentDeliverHistoryRegistRequest l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));

                TestDBUtility.deleteAll(TraderRow.TYPE);
                TraderParams l_traderParams = TestDBUtility.getTraderRow();
                TestDBUtility.insertWithDel(l_traderParams);
                l_request.productCode = new String[]{"aa"};
                l_request.typeCode = "b";
                l_documentDeliverHistoryRegistServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        WEB3DocumentDeliverHistoryRegistRequest l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));

                TestDBUtility.deleteAll(TraderRow.TYPE);
                TraderParams l_traderParams = TestDBUtility.getTraderRow();
                TestDBUtility.insertWithDel(l_traderParams);
            l_request.eleBatoCheckFlg = false;
            l_request.productCode = new String[]{"aa"};
            l_request.typeCode = "b";
            l_documentDeliverHistoryRegistServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImplForTest l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImplForTest();
        WEB3DocumentDeliverHistoryRegistRequest l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
          
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            l_request.eleBatoCheckFlg = true;
            l_request.productCode = new String[]{"aaa"};
            l_request.typeCode = "100";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            WEB3GentradeProspectusResult l_result = new WEB3GentradeProspectusResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_result);
            l_result.checkResult = "0";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = TestDBUtility.getDocDivManagementRow();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentCheckDiv("4");
            l_docDivManagementParams.setDocumentNumber("0");
            l_docDivManagementParams.setDocumentCategory("aaa");
            l_docDivManagementParams.setDocumentDiv("100");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            l_documentDeliverHistoryRegistServiceImpl.execute(l_request);
           
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
   }
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImplForTest l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImplForTest();
        WEB3DocumentDeliverHistoryRegistRequest l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
          
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            l_request.eleBatoCheckFlg = true;
            l_request.productCode = new String[]{"aaa"};
            l_request.typeCode = "100";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            WEB3GentradeProspectusResult l_result = new WEB3GentradeProspectusResult();
            l_result.checkResult = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_result);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = TestDBUtility.getDocDivManagementRow();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentCheckDiv("4");
            l_docDivManagementParams.setDocumentNumber("0");
            l_docDivManagementParams.setDocumentCategory("aaa");
            l_docDivManagementParams.setDocumentDiv("100");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            l_documentDeliverHistoryRegistServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02940,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
   }
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImplForTest l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImplForTest();
        WEB3DocumentDeliverHistoryRegistRequest l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
          
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            l_request.eleBatoCheckFlg = true;
            l_request.productCode = new String[]{"aaa"};
           
            l_request.typeCode = "100";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            WEB3GentradeProspectusResult l_result = new WEB3GentradeProspectusResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_result);
            l_result.checkResult = "2";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = TestDBUtility.getDocDivManagementRow();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentCheckDiv("4");
            l_docDivManagementParams.setDocumentNumber("0");
            l_docDivManagementParams.setDocumentCategory("aaa");
            l_docDivManagementParams.setDocumentDiv("100");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            l_documentDeliverHistoryRegistServiceImpl.execute(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
   }
    public void testExecute_C0006()
    {
        final String STR_METHOD_NAME = "testExecute_C0006()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImplForTest l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImplForTest();
        WEB3DocumentDeliverHistoryRegistRequest l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
          
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            l_request.eleBatoCheckFlg = true;
            l_request.productCode = new String[]{"aaa"};
            l_request.typeCode = "100";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            WEB3BusinessLayerException l_Exception=new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01984,null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_Exception);
           
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            l_documentDeliverHistoryRegistServiceImpl.execute(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01984,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
   }
    public void testExecute_C0007()
    {
        final String STR_METHOD_NAME = "testExecute_C0007()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImplForTest l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImplForTest();
        WEB3DocumentDeliverHistoryRegistRequest l_request =
            new WEB3DocumentDeliverHistoryRegistRequest();
        try
        {
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
          
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            l_request.eleBatoCheckFlg = true;
            l_request.productCode = new String[]{"aaa"};
            l_request.typeCode = "100";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            WEB3GentradeProspectusResult l_result = new WEB3GentradeProspectusResult();
            l_result.checkResult = "0";

            WEB3BaseException l_Exception=new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00250,null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_Exception);
           
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
 
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = TestDBUtility.getDocDivManagementRow();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentCheckDiv("4");
            l_docDivManagementParams.setDocumentNumber("0");
            l_docDivManagementParams.setDocumentCategory("aaa");
            l_docDivManagementParams.setDocumentDiv("100");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            l_documentDeliverHistoryRegistServiceImpl.execute(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
   }
    public void testSaveDeliveryHistory_C0001()
    {
        final String STR_METHOD_NAME = "testSaveDeliveryHistory_C0001()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_documentDiv = new String("100");
        String l_productCode = new String("0D");

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
  
            l_documentDeliverHistoryRegistServiceImpl.saveDeliveryHistory(l_documentDiv,l_productCode);
            
            assertTrue(true);

             log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
   }
    public void testSaveDeliveryHistory_C0002()
    {
        final String STR_METHOD_NAME = "testSaveDeliveryHistory_C0002()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_documentDiv = new String("100");
        String l_productCode = new String("12345");

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementRow.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
  
            l_documentDeliverHistoryRegistServiceImpl.saveDeliveryHistory(l_documentDiv,l_productCode);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
   }
    public void testIsFinancialProductsExchangeLawDocument_C0001()
    {
        final String STR_METHOD_NAME = "testIsFinancialProductsExchangeLawDocument_C0001()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_documentDiv = new String("100");
        String l_strDocumentType = new String("a");
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = TestDBUtility.getDocDivManagementRow();
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            boolean l_boolean =
                l_documentDeliverHistoryRegistServiceImpl.isFinancialProductsExchangeLawDocument(l_documentDiv,l_strDocumentType);
            assertEquals(false, l_boolean);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }   
    }
    public void testIsFinancialProductsExchangeLawDocument_C0002()
    {
        final String STR_METHOD_NAME = "testIsFinancialProductsExchangeLawDocument_C0002()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_documentDiv = new String("100");
        String l_strDocumentType = new String("1");
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = TestDBUtility.getDocDivManagementRow();
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            boolean l_boolean =
                l_documentDeliverHistoryRegistServiceImpl.isFinancialProductsExchangeLawDocument(l_documentDiv,l_strDocumentType);
            assertEquals(true, l_boolean);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        } 
    }
    public void testIsFinancialProductsExchangeLawDocument_C0003()
    {
        final String STR_METHOD_NAME = "testIsFinancialProductsExchangeLawDocument_C0003()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_documentDiv = new String("10");
        String l_strDocumentType = new String("1");
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = TestDBUtility.getDocDivManagementRow();
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            boolean l_boolean =
                l_documentDeliverHistoryRegistServiceImpl.isFinancialProductsExchangeLawDocument(l_documentDiv,l_strDocumentType);
            assertEquals(false, l_boolean);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        } 
    }

    public void testValidateBatoProductCode_C0001()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductCode_C0001()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_strDocumentDivision = new String("aa");
        String l_strBatoProductCode = new String("s");
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            BatoProductManagementParams l_batoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_documentDeliverHistoryRegistServiceImpl.validateBatoProductCode(l_strDocumentDivision,l_strBatoProductCode);
           
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
        
    }

    public void testValidateBatoProductCode_C0002()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductCode_C0002()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_strDocumentDivision = new String("100");
        String l_strBatoProductCode = new String("0000000");
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            BatoProductManagementParams l_batoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_documentDeliverHistoryRegistServiceImpl.validateBatoProductCode(l_strDocumentDivision,l_strBatoProductCode);
           
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    public void testValidateBatoProductCode_C0003()
    {
        final String STR_METHOD_NAME = "testValidateBatoProductCode_C0003()";
        log.entering(STR_METHOD_NAME);
        WEB3DocumentDeliverHistoryRegistServiceImpl l_documentDeliverHistoryRegistServiceImpl =
            new WEB3DocumentDeliverHistoryRegistServiceImpl();
        String l_strDocumentDivision = new String("100");
        String l_strBatoProductCode = new String("0000000");
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            BatoProductManagementParams l_batoProductManagementParams = TestDBUtility.getBatoProductManagementRow();
            l_batoProductManagementParams.setValidFlag("1");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_documentDeliverHistoryRegistServiceImpl.validateBatoProductCode(l_strDocumentDivision,l_strBatoProductCode);
           
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03004,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
    }
    public class WEB3DocumentDeliverHistoryRegistServiceImplForTest extends WEB3DocumentDeliverHistoryRegistServiceImpl
    {
        protected void validateBatoProductCode(String l_strDocumentDivision, String l_strBatoProductCode)
            throws WEB3BaseException
        {
            return;
        }

        protected void saveDeliveryHistory(String l_documentDiv, String l_productCode)
        throws WEB3BaseException
        {
            return;
        }

        protected boolean isFinancialProductsExchangeLawDocument(String l_documentDiv, String l_strDocumentType)
        throws WEB3BaseException
        {
        return true;
        }
    }

}
@


1.1
log
@*** empty log message ***
@
text
@a11 1
import com.adventnet.snmp.beans.DataException;
@

