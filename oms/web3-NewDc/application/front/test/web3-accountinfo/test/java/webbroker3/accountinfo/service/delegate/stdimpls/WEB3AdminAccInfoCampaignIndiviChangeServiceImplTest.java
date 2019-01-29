head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoCampaignIndiviChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAccInfoCampaignIndiviChangeServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/7  齊珂(中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import test.util.TestDBUtility;
import webbroker3.accountinfo.WEB3AdminAccInfoCampaignSearchCondition;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

public class WEB3AdminAccInfoCampaignIndiviChangeServiceImplTest extends
    TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviChangeServiceImplTest.class);
    
    public WEB3AdminAccInfoCampaignIndiviChangeServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public class WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock 
        extends WEB3AdminAccInfoCampaignIndiviConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public class WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock 
    extends WEB3AdminAccInfoCampaignIndiviCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }

    public void testGetInputScreen601()
    {
        final String STR_METHOD_NAME = "testGetInputScreen601()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response = null;
        
        try
        {
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviInputRequest l_request = 
                new WEB3AdminAccInfoCampaignIndiviInputRequest();
            
            l_request.updateFlag = "2";
            
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02721,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testGetInputScreen601>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreen602()
    {
        final String STR_METHOD_NAME = "testGetInputScreen602()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response = null;
        
        try
        {
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviInputRequest l_request = 
                new WEB3AdminAccInfoCampaignIndiviInputRequest();
            
            l_request.updateFlag = "0";
            
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, false);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testGetInputScreen602>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen603()
    {
        final String STR_METHOD_NAME = "testGetInputScreen603()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviInputRequest l_request = 
                new WEB3AdminAccInfoCampaignIndiviInputRequest();
            
            l_request.updateFlag = "1";
            l_request.campaignId = "1";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", false);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testGetInputScreen603>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen604()
    {
        final String STR_METHOD_NAME = "testGetInputScreen604()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviInputRequest l_request = 
                new WEB3AdminAccInfoCampaignIndiviInputRequest();
            
            l_request.updateFlag = "1";
            l_request.campaignId = "1";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse) l_service.execute(l_request);
            
            assertEquals("0", l_response.commissionCampaignInfo.branchCode);
        }
        catch (WEB3BaseException l_ex)
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

    
    public void testValidateChange605()
    {
        final String STR_METHOD_NAME = "testValidateChange605()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
            
            l_request.updateFlag = "3";
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02710,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidateChange605>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChange606()
    {
        final String STR_METHOD_NAME = "testValidateChange606()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            l_request.updateFlag = "0";
            
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, false);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidateChange606>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChange607()
    {
        final String STR_METHOD_NAME = "testValidateChange607()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "1";
            l_request.commissionCampaignInfo = l_info2;
            
            l_request.updateFlag = "1";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", false);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidateChange607>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    

    
    public void testValidateChange608()
    {
        final String STR_METHOD_NAME = "testValidateChange608()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "1";
            
            WEB3AccInfoCampaignInfo[] l_infos = new WEB3AccInfoCampaignInfo[1];
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "1";
            l_info2.institutionCode = "1";
            l_info2.accountCode = "1";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyy/MM/dd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            l_request.updateFlag = "0";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02726,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidateChange608>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    

    
    public void testValidateChange609()
    {
        final String STR_METHOD_NAME = "testValidateChange609()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {           
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "1";
                       
            WEB3AccInfoCampaignInfo[] l_infos = new WEB3AccInfoCampaignInfo[1];
            l_infos[0] = l_info;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                Boolean.FALSE);
            
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "1";
            l_info2.institutionCode = "1";
            l_info2.accountCode = "1";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyyMMdd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            l_request.updateFlag = "1";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02723,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testValidateChange609>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    

    
    public void testValidateChange610()
    {
        final String STR_METHOD_NAME = "testValidateChange610()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {           
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "1";
                       
            WEB3AccInfoCampaignInfo[] l_infos = new WEB3AccInfoCampaignInfo[1];
            l_infos[0] = l_info;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                Boolean.TRUE);
            
            String l_str = "alertFlag";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                l_str);
            
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "1";
            l_info2.institutionCode = "1";
            l_info2.accountCode = "1";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyy/MM/dd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            l_request.updateFlag = "1";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            assertEquals(l_str, l_response.alertFlag);
            assertNull(l_response.commissionCampaignInfo.campaignId);
            assertNull(l_response.commissionCampaignInfo.campaignName);
            assertNull(l_response.commissionCampaignInfo.institutionCode);
            assertNull(l_response.commissionCampaignInfo.branchCode);
            assertNull(l_response.commissionCampaignInfo.accountCode);
            assertNull(l_response.commissionCampaignInfo.itemCode);
            assertNull(l_response.commissionCampaignInfo.targetPeriodFrom);
            assertNull(l_response.commissionCampaignInfo.targetPeriodTo);
            assertNull(l_response.commissionCampaignInfo.collectRate);
            assertNull(l_response.commissionCampaignInfo.registType);
            assertNull(l_response.commissionCampaignInfo.deleteFlag);
            assertNull(l_response.commissionCampaignInfo.transactionDiv);
            assertNull(l_response.commissionCampaignInfo.registrant);
            assertNull(l_response.commissionCampaignInfo.registDate);
            assertNull(l_response.commissionCampaignInfo.updateDate);
            assertNull(l_response.commissionCampaignInfo.accopenPassPeriodMonth);
            assertNull(l_response.commissionCampaignInfo.accopenPassPeriodDay);
            assertNull(l_response.commissionCampaignInfo.traderCode);
            assertNull(l_response.commissionCampaignInfo.accountOpenDiv);
            assertNull(l_response.commissionCampaignInfo.accountOpenDateFrom);
            assertNull(l_response.commissionCampaignInfo.accountOpenDateTo);
        }
        catch (WEB3BaseException l_ex)
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
    
    public void testValidateChange611()
    {
        final String STR_METHOD_NAME = "testValidateChange611()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {           
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "1";
                       
            WEB3AccInfoCampaignInfo[] l_infos = new WEB3AccInfoCampaignInfo[1];
            l_infos[0] = l_info;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                Boolean.TRUE);
            
            String l_str = "alertFlag";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                l_str);
            
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "381";
            l_info2.institutionCode = "0D";
            l_info2.accountCode = "2512246";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyyMMdd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            l_request.updateFlag = "0";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            assertEquals("内藤　@四郎", l_response.commissionCampaignInfo.accountName);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChange612()
    {
        final String STR_METHOD_NAME = "testValidateChange612()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {           
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "1";
                       
            WEB3AccInfoCampaignInfo[] l_infos = new WEB3AccInfoCampaignInfo[1];
            l_infos[0] = l_info;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                Boolean.TRUE);
            
            String l_str = "alertFlag";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                l_str);
            
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "381";
            l_info2.institutionCode = "0D";
            l_info2.accountCode = "2512246";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyyMMdd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            l_request.updateFlag = "1";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            assertNull(l_response.commissionCampaignInfo.accountName);
        }
        catch (WEB3BaseException l_ex)
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
    
    
    public void testValidateChange613()
    {
        final String STR_METHOD_NAME = "testValidateChange613()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        
        try
        {           
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "1";
                       
            WEB3AccInfoCampaignInfo[] l_infos = new WEB3AccInfoCampaignInfo[1];
            l_infos[0] = l_info;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                Boolean.TRUE);
            
            String l_str = "alertFlag";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                l_str);
            
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviConfirmRequestForMock();
            
            
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "381";
            l_info2.institutionCode = "0D";
            l_info2.accountCode = "2512246";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyyMMdd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            l_request.updateFlag = "2";
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse) l_service.execute(l_request);
            assertEquals(l_info.branchCode, l_response.commissionCampaignInfo.branchCode);
        }
        catch (WEB3BaseException l_ex)
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
    
    public void testSubmitChange614()
    {
        final String STR_METHOD_NAME = "testSubmitChange614()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        
        try
        {
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request = 
                new WEB3AdminAccInfoCampaignIndiviCompleteRequest();
            
            l_request.updateFlag = "3";
            
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02710,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testSubmitChange614>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange615()
    {
        final String STR_METHOD_NAME = "testSubmitChange615()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        
        try
        {
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock();
            
            l_request.updateFlag = "0";
            
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, false);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testSubmitChange615>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange616()
    {
        final String STR_METHOD_NAME = "testSubmitChange616()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock();
            
            l_request.updateFlag = "3";
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "01";
            
            l_request.commissionCampaignInfo = l_info2;
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", false);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testSubmitChange616>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    

    
    public void testSubmitChange617()
    {
        final String STR_METHOD_NAME = "testSubmitChange617()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            WEB3AccInfoCampaignInfo[] l_infos = new WEB3AccInfoCampaignInfo[1];
                                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
            
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock();
            
            l_request.updateFlag = "0";
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "381";
            l_info2.institutionCode = "0D";
            l_info2.accountCode = "2512246";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyy/MM/dd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);

            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02726,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testSubmitChange617>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChange618()
    {
        final String STR_METHOD_NAME = "testSubmitChange618()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            WEB3AccInfoCampaignInfo[] l_infos = null;
                                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
            
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock();
            
            l_request.updateFlag = "1";
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "381";
            l_info2.institutionCode = "0D";
            l_info2.accountCode = "2512246";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyyMMdd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);

            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                Boolean.FALSE);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse) l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02723,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>testSubmitChange618>>>>>>>>>>>>>>>test pass !!");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testSubmitChange619()
    {
        final String STR_METHOD_NAME = "testSubmitChange619()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        
        try
        {
            WEB3AccInfoCampaignInfo l_info = new WEB3AccInfoCampaignInfo();
            l_info.branchCode = "0";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getCampaignCondition",
                new Class[] {String.class},
                l_info);
            
            WEB3AccInfoCampaignInfo[] l_infos = null;
                                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "getSameCampaignCondition",
                new Class[] {WEB3AdminAccInfoCampaignSearchCondition.class},
                l_infos);
            
            String l_str = "alertFlag";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "validateTargetPeriod",
                new Class[] {WEB3AccInfoCampaignInfo.class,String.class},
                l_str);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "deleteCampaignCondition",
                new Class[] {String.class, String.class},
                null);
                
            WEB3AdminAccInfoCampaignIndiviChangeService l_service = 
                (WEB3AdminAccInfoCampaignIndiviChangeService) Services.getService(
                    WEB3AdminAccInfoCampaignIndiviChangeService.class);
            
            WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock l_request = 
                new WEB3AdminAccInfoCampaignIndiviCompleteRequestForMock();
            
            l_request.updateFlag = "2";
            WEB3AccInfoCampaignInfo l_info2 = new WEB3AccInfoCampaignInfo();
            l_info2.branchCode = "381";
            l_info2.institutionCode = "0D";
            l_info2.accountCode = "2512246";
            l_info2.targetPeriodFrom = WEB3DateUtility.getDate("2007/02/07", "yyyyMMdd");
            
            l_request.commissionCampaignInfo = l_info2;
            
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
                   
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0903", true, true);
            
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administratorSet, "0", true);

            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon",
                "isChangeInfo",
                new Class[] {WEB3AccInfoCampaignInfo.class},
                Boolean.TRUE);
            
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse) l_service.execute(l_request);
            assertEquals(l_str, l_response.alertFlag);
        }
        catch (WEB3BaseException l_ex)
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
}
@
