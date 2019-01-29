head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyDataDelServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenApplyDataDelServiceImplTest extends
        TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelServiceImplTest.class);

    WEB3AdminAccOpenApplyDataDelServiceImpl l_impl =
         new WEB3AdminAccOpenApplyDataDelServiceImpl();
    public WEB3AdminAccOpenApplyDataDelServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetSearchScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorRow
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            WEB3AdminAccOpenApplyDataDelSearchRequest l_request = 
                new WEB3AdminAccOpenApplyDataDelSearchRequest();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123456L));
            
            l_impl.getSearchScreen(l_request);
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
    
    public void testGetSearchScreen_Case002()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorRow

            WEB3AdminAccOpenApplyDataDelSearchRequest l_request = 
                new WEB3AdminAccOpenApplyDataDelSearchRequest();
            
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

            l_impl.getSearchScreen(l_request);
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
    
    public void testGetSearchScreen_Case003()
    {
        final String STR_METHOD_NAME = "testGetSearchScreen_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorRow

            WEB3AdminAccOpenApplyDataDelSearchRequest l_request = 
                new WEB3AdminAccOpenApplyDataDelSearchRequest();
            WEB3AdminAccOpenApplyDataDelSearchResponse l_response;
            
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_response = l_impl.getSearchScreen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateDelete_Case001()
    {
        final String STR_METHOD_NAME = "testValidateDelete_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();
            l_request.requestNumber = "";
            
            l_impl.validateDelete(l_request);

            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateDelete_Case002()
    {
        final String STR_METHOD_NAME = "testValidateDelete_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();
            l_request.requestNumber = "44";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123L));
            //AdministratorDao
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            l_impl.validateDelete(l_request);

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
    
    public void testValidateDelete_Case003()
    {
        final String STR_METHOD_NAME = "testValidateDelete_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();
            l_request.requestNumber = "44";
            
            //WEB3Administrator
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            
            l_impl.validateDelete(l_request);

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
    
    public void testValidateDelete_Case004()
    {
        final String STR_METHOD_NAME = "testValidateDelete_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();
            l_request.requestNumber = "44";
            
            //WEB3Administrator
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            
            
            l_impl.validateDelete(l_request);

            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01318, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateDelete_Case005()
    {
        final String STR_METHOD_NAME = "testValidateDelete_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();
            l_request.requestNumber = "44";
            
            //WEB3Administrator
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountOpenDate(GtlUtils.getSystemTimestamp());
            l_expAccountOpenParams.setAccOpenRequestNumber(l_request.requestNumber);
            l_expAccountOpenParams.setInstitutionCode(l_administrator.getInstitutionCode());
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            l_impl.validateDelete(l_request);

            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03142, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateDelete_Case006()
    {
        final String STR_METHOD_NAME = "testValidateDelete_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();
            l_request.requestNumber = "44";
            
            //WEB3Administrator
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountOpenDate(null);
            l_expAccountOpenParams.setAccOpenRequestNumber(l_request.requestNumber);
            l_expAccountOpenParams.setInstitutionCode(l_administrator.getInstitutionCode());
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            //AccOpenVoucherStatusRow
            TestDBUtility.deleteAll(AccOpenVoucherStatusRow.TYPE);
            
            WEB3AdminAccOpenApplyDataDelCnfResponse l_response = l_impl.validateDelete(l_request);
            assertEquals(l_expAccountOpenParams.getFamilyName(), l_response.accountFamilyName);
            assertEquals(l_expAccountOpenParams.getGivenName(), l_response.accountName);
            assertEquals(l_expAccountOpenParams.getFamilyNameAlt1(),l_response.accountFamilyNameKana);
            assertEquals(l_expAccountOpenParams.getGivenNameAlt1(), l_response.accountNameKana);
            assertEquals(l_expAccountOpenParams.getAddressLine1(), l_response.address1);
            assertEquals(l_expAccountOpenParams.getAddressLine2(), l_response.address2);
            assertEquals(l_expAccountOpenParams.getAddressLine3(), l_response.address3);
            assertEquals(l_expAccountOpenParams.getAddressLine1Kana(), l_response.addressKana1);
            assertEquals(l_expAccountOpenParams.getAddressLine2Kana(), l_response.addressKana2);
            assertEquals(l_expAccountOpenParams.getAddressLine3Kana(), l_response.addressKana3);
            assertEquals(l_expAccountOpenParams.getTelephone(), l_response.telephone);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitDelete_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "";
            
            l_impl.submitDelete(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitDelete_Case002()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_Case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "44";
            l_request.checkFlag = "1";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123L));
            //AdministratorDao
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            l_impl.submitDelete(l_request);
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
    
    public void testSubmitDelete_Case003()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_Case003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "44";
            l_request.checkFlag = "1";
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            
            l_impl.submitDelete(l_request);
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
    
    public void testSubmitDelete_Case004()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_Case004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "44";
            l_request.checkFlag = "1";
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(l_request.password, false);

            l_impl.submitDelete(l_request);
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
    
    public void testSubmitDelete_Case005()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_Case005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "44";
            l_request.checkFlag = "1";
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(l_request.password, true);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            
            l_impl.submitDelete(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01318, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitDelete_Case006()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_Case006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "44";
            l_request.checkFlag = "1";
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(l_request.password, true);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_expAccountOpenParams.setAccOpenRequestNumber(l_request.requestNumber);
            l_expAccountOpenParams.setAccountOpenDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            l_impl.submitDelete(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03142, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitDelete_Case007()
    {
        final String STR_METHOD_NAME = "testSubmitDelete_Case007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "44";
            l_request.checkFlag = "1";
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(l_request.password, true);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_expAccountOpenParams.setAccOpenRequestNumber(l_request.requestNumber);
            l_expAccountOpenParams.setAccountOpenDate(null);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            TestDBUtility.deleteAll(AccOpenVoucherStatusRow.TYPE);
            
            WEB3AdminAccOpenApplyDataDelCmpResponse l_response = l_impl.submitDelete(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = null;
            String l_strQueryString = null;
            Object[] l_objQueryDataContainer = null;

            l_strQueryString = "institution_code = ? and acc_open_request_number = ?";
            l_objQueryDataContainer = new Object[]{
                    l_administrator.getInstitutionCode(),
                    l_request.requestNumber};

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer
            );
            
            assertEquals(0, l_lisRecords.size());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_Case001()
    {
        final String STR_METHOD_NAME = "testExecute_Case001()";
        log.entering(STR_METHOD_NAME);
        WEB3GenRequest l_request = null;
        
        try
        {
            l_impl.execute(l_request);
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
            l_impl.execute(new WEB3AdminAccOpenApplyUploadCancelRequest());
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
            //AdministratorRow

            WEB3AdminAccOpenApplyDataDelSearchRequest l_request = 
                new WEB3AdminAccOpenApplyDataDelSearchRequest();
            
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3AdminAccOpenApplyDataDelSearchResponse l_response = 
                (WEB3AdminAccOpenApplyDataDelSearchResponse)l_impl.execute(l_request);
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

            WEB3AdminAccOpenApplyDataDelCnfRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCnfRequest();
            l_request.requestNumber = "44";
            
            //WEB3Administrator
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setAccountOpenDate(null);
            l_expAccountOpenParams.setAccOpenRequestNumber(l_request.requestNumber);
            l_expAccountOpenParams.setInstitutionCode(l_administrator.getInstitutionCode());
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            //AccOpenVoucherStatusRow
            TestDBUtility.deleteAll(AccOpenVoucherStatusRow.TYPE);
            
            
            WEB3AdminAccOpenApplyDataDelCnfResponse l_response =
                (WEB3AdminAccOpenApplyDataDelCnfResponse)l_impl.execute(l_request);
            assertEquals(l_expAccountOpenParams.getFamilyName(), l_response.accountFamilyName);
            assertEquals(l_expAccountOpenParams.getGivenName(), l_response.accountName);
            assertEquals(l_expAccountOpenParams.getFamilyNameAlt1(),l_response.accountFamilyNameKana);
            assertEquals(l_expAccountOpenParams.getGivenNameAlt1(), l_response.accountNameKana);
            assertEquals(l_expAccountOpenParams.getAddressLine1(), l_response.address1);
            assertEquals(l_expAccountOpenParams.getAddressLine2(), l_response.address2);
            assertEquals(l_expAccountOpenParams.getAddressLine3(), l_response.address3);
            assertEquals(l_expAccountOpenParams.getAddressLine1Kana(), l_response.addressKana1);
            assertEquals(l_expAccountOpenParams.getAddressLine2Kana(), l_response.addressKana2);
            assertEquals(l_expAccountOpenParams.getAddressLine3Kana(), l_response.addressKana3);
            assertEquals(l_expAccountOpenParams.getTelephone(), l_response.telephone);

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

            WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
                new WEB3AdminAccOpenApplyDataDelCmpRequest();
            l_request.requestNumber = "44";
            l_request.checkFlag = "1";
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("A0404");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(l_request.password, true);
            
            //ExpAccountOpenDao
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_expAccountOpenParams.setAccOpenRequestNumber(l_request.requestNumber);
            l_expAccountOpenParams.setAccountOpenDate(null);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            TestDBUtility.deleteAll(AccOpenVoucherStatusRow.TYPE);
            
            WEB3AdminAccOpenApplyDataDelCmpResponse l_response =
                (WEB3AdminAccOpenApplyDataDelCmpResponse)l_impl.execute(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = null;
            String l_strQueryString = null;
            Object[] l_objQueryDataContainer = null;

            l_strQueryString = "institution_code = ? and acc_open_request_number = ?";
            l_objQueryDataContainer = new Object[]{
                    l_administrator.getInstitutionCode(),
                    l_request.requestNumber};

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer
            );
            
            assertEquals(0, l_lisRecords.size());

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
