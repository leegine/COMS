head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataDownloadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadRequest;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataDownloadServiceImplTest extends
    TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminSrvRegiAccountDataDownloadServiceImplTest.class);
    
    WEB3AdminSrvRegiAccountDataDownloadServiceImpl l_impl = 
        new WEB3AdminSrvRegiAccountDataDownloadServiceImpl();
    
    public WEB3AdminSrvRegiAccountDataDownloadServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    protected void setUp() throws Exception
    {
        Services.overrideService(OpLoginSecurityService.class,
                new OpLoginSecurityServiceImplForMock());
        super.setUp();
    }

    public class WEB3AdminSrvRegiDownloadRequestForMock extends WEB3AdminSrvRegiDownloadRequest
    {
        public void validate() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " validateForMock()";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);
        }
        
    }
    
    public void testgetDownloadFile01()
    {
        final String STR_METHOD_NAME = "testgetDownloadFile01()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminSrvRegiDownloadRequestForMock l_requestForMock = new WEB3AdminSrvRegiDownloadRequestForMock();
        String[] l_strBranchCode = new String[1];

        l_strBranchCode[0] = "901";
        try
        {
            //SrvRegiApplicationParams
            SrvRegiApplicationParams[] l_appParams = new SrvRegiApplicationParams[1];
            SrvRegiApplicationParams l_srvParams = TestDBUtility.getSrvRegiApplicationParams();
            l_srvParams.setAccountCode("7624900");
            l_appParams[0] = l_srvParams;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceRegistLists",
                new Class[]
                          {
                              String.class , String[].class ,
                              String.class , String.class , String.class, String.class,
                              Timestamp.class ,Timestamp.class , Timestamp.class,
                              Timestamp.class, WEB3SrvRegiSortKey[].class},
                              l_appParams);
            
            
            //admin
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDelAndCommit(l_adminRow);
            
            //sysPreference
            TestDBUtility.deleteAllAndCommit(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_sysPreferenceParams = TestDBUtility.getSystemPreferencesRow();
            l_sysPreferenceParams.setName("DL_REC_COUNT_SERVICE_ACCOUNT_DATA");
            l_sysPreferenceParams.setValue("0");
            TestDBUtility.insertWithDelAndCommit(l_sysPreferenceParams);
            
            //mainAcc
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAcc = TestDBUtility.getMainAccountRow();
            l_mainAcc.setInstitutionCode("0D");
            l_mainAcc.setBranchCode("381");
            l_mainAcc.setAccountCode("7624900");
            TestDBUtility.insertWithDelAndCommit(l_mainAcc);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, 
                "C0602", 
                false, 
                true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_expectAdministrator, 
                l_strBranchCode,
                true);
            l_impl.getDownloadFile(l_requestForMock);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("WEB3BaseException l_ex == " + l_ex);
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01957.error_code, l_ex.getErrorInfo().getErrorCode());
        }
        catch(Exception l_ex)
        {
            l_ex.printStackTrace();
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        } 
    }
    
    public void testgetDownloadFile02()
    {
        final String STR_METHOD_NAME = "testgetDownloadFile02()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminSrvRegiDownloadRequestForMock l_requestForMock = new WEB3AdminSrvRegiDownloadRequestForMock();
        String[] l_strBranchCode = new String[1];

        l_strBranchCode[0] = "901";
        try
        {
            //SrvRegiApplicationParams
            SrvRegiApplicationParams[] l_appParams = new SrvRegiApplicationParams[1];
            SrvRegiApplicationParams l_srvParams = TestDBUtility.getSrvRegiApplicationParams();
            l_srvParams.setAccountCode("7624900");
            l_appParams[0] = l_srvParams;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                "getServiceRegistLists",
                new Class[]
                          {
                              String.class , String[].class ,
                              String.class , String.class , String.class, String.class,
                              Timestamp.class ,Timestamp.class , Timestamp.class,
                              Timestamp.class, WEB3SrvRegiSortKey[].class},
                              l_appParams);
            
            //admin
            TestDBUtility.deleteAllAndCommit(AdministratorParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDelAndCommit(l_adminRow);
            
            //sysPreference
            TestDBUtility.deleteAllAndCommit(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_sysPreferenceParams = TestDBUtility.getSystemPreferencesRow();
            l_sysPreferenceParams.setName("DL_REC_COUNT_SERVICE_ACCOUNT_DATA");
            l_sysPreferenceParams.setValue("2");
            TestDBUtility.insertWithDelAndCommit(l_sysPreferenceParams);
            
            //mainAcc
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAcc = TestDBUtility.getMainAccountRow();
            l_mainAcc.setInstitutionCode("0D");
            l_mainAcc.setBranchCode("381");
            l_mainAcc.setAccountCode("7624900");
            TestDBUtility.insertWithDelAndCommit(l_mainAcc);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, 
                "C0602", 
                false, 
                true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                l_expectAdministrator, 
                l_strBranchCode,
                true);
            l_impl.getDownloadFile(l_requestForMock);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("WEB3BaseException l_ex == " + l_ex);
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            l_ex.printStackTrace();
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        } 
    }
}
@
