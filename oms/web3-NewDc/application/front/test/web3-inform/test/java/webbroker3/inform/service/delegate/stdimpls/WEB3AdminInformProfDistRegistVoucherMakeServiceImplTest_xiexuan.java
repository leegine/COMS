head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xiexuan.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.DirectDebitParams;
import webbroker3.inform.data.VariousInformPK;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpResponse;
import webbroker3.inform.message.WEB3InformDetailInfoUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xiexuan extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xiexuan.class);

    public WEB3AdminInformProfDistRegistVoucherMakeServiceImplTest_xiexuan(String arg0)
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
    }
    
    public void testGetDistStatusSearchScreen_0001()
    {
        final String STR_METHOD_NAME = " testGetDistStatusSearchScreen_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);

            Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();
            Date l_datPreviousBizDateExp = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);
            Date l_datPreviousDateExp = WEB3DateUtility.toDay(WEB3DateUtility.addDay(l_tsCurrentDate, -1));

            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistStatusSearchInputRequest l_request =
                new WEB3AdminInformProfDistStatusSearchInputRequest();
            WEB3AdminInformProfDistStatusSearchInputResponse l_response = l_impl.getRegistStatusSearchScreen(l_request);

            assertEquals(l_datPreviousBizDateExp, l_response.previousBizDate);
            assertEquals(l_datPreviousDateExp, l_response.previousDate);

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetDistStatusSearchScreen_0002()
    {
        final String STR_METHOD_NAME = " testGetDistStatusSearchScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistStatusSearchInputRequest l_request = null;
            WEB3AdminInformProfDistStatusSearchInputResponse l_response = l_impl.getRegistStatusSearchScreen(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetVoucherMakeInpScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetVoucherMakeInpScreen_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistVoucherMakeInpRequest l_request = null;
            WEB3AdminInformProfDistVoucherMakeInpResponse l_response = l_impl.getVoucherMakeInpScreen(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetVoucherMakeInpScreen_0002()
    {
        final String STR_METHOD_NAME = " testGetVoucherMakeInpScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            WEB3AdminInformProfDistVoucherMakeInpRequestForTest l_request =
                new WEB3AdminInformProfDistVoucherMakeInpRequestForTest();
            l_request.registDiv = "1";
            
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            
            WEB3AdminInformProfDistVoucherMakeInpResponse l_response =
                l_impl.getVoucherMakeInpScreen(l_request);
            
            assertEquals(WEB3AdminInformProfDistVoucherMakeInpResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    public void testGetVoucherMakeInpScreen_0003()
    {
        final String STR_METHOD_NAME = " testGetVoucherMakeInpScreen_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            WEB3AdminInformProfDistVoucherMakeInpRequestForTest l_request =
                new WEB3AdminInformProfDistVoucherMakeInpRequestForTest();
            l_request.registDiv = "3";
            
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            
            WEB3AdminInformProfDistVoucherMakeInpResponse l_response =
                l_impl.getVoucherMakeInpScreen(l_request);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetVoucherMakeInpScreen_0004()
    {
        final String STR_METHOD_NAME = " testGetVoucherMakeInpScreen_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            WEB3AdminInformProfDistVoucherMakeInpRequestForTest l_request =
                new WEB3AdminInformProfDistVoucherMakeInpRequestForTest();
            l_request.registDiv = "3";
            l_request.branchCode = "381";
            l_request.accountCode = "2450007";
            l_request.specifyDiv = "1";
            l_request.transferDiv = "1";

            DirectDebitParams l_directDebitParams = new DirectDebitParams();
            l_directDebitParams.setInstitutionCode("0D");
            l_directDebitParams.setBranchCode("381");
            l_directDebitParams.setAccountCode("2450007");
            l_directDebitParams.setDesignateDiv("1");
            l_directDebitParams.setTransferDiv("1");
            l_directDebitParams.setFinSaveDiv("2");
            
            TestDBUtility.deleteAll(l_directDebitParams.getRowType());
            TestDBUtility.insertWithDel(l_directDebitParams);
            
            
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            
            WEB3AdminInformProfDistVoucherMakeInpResponse l_response =
                l_impl.getVoucherMakeInpScreen(l_request);

            assertEquals("381", l_response.transferInfo.branchCode);
            assertEquals("245000", l_response.transferInfo.accountCode);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateVoucherMakeCnf_0001()
    {
        final String STR_METHOD_NAME = "testValidateVoucherMakeCnf_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistVoucherMakeCnfRequest l_request = null;
            WEB3AdminInformProfDistVoucherMakeCnfResponse l_response = l_impl.validateVoucherMakeCnf(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateVoucherMakeCnf_0002()
    {
        final String STR_METHOD_NAME = "testValidateVoucherMakeCnf_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", true, true);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            WEB3AdminInformProfDistVoucherMakeCnfRequestForTest l_request =
                new WEB3AdminInformProfDistVoucherMakeCnfRequestForTest();
            
            WEB3InformDetailInfoUnit l_unit = this.getInformDetailInfoUnit();
            l_unit.code3 = null;
            l_request.informInfoUnit = l_unit;
            
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistVoucherMakeCnfResponse l_response = l_impl.validateVoucherMakeCnf(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02788, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testValidateVoucherMakeCnf_0003()
    {
        final String STR_METHOD_NAME = "testValidateVoucherMakeCnf_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", true, true);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            WEB3AdminInformProfDistVoucherMakeCnfRequestForTest l_request =
                new WEB3AdminInformProfDistVoucherMakeCnfRequestForTest();
            
            WEB3InformDetailInfoUnit l_unit = this.getInformDetailInfoUnit();
            l_unit.code3 = null;
            l_unit.div5 = "5";
            l_request.informInfoUnit = l_unit;
            
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistVoucherMakeCnfResponse l_response = l_impl.validateVoucherMakeCnf(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02791, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitVoucherMakeCmp_0001()
    {
        final String STR_METHOD_NAME = "testSubmitVoucherMakeCmp_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistVoucherMakeCmpRequest l_request = null;
            WEB3AdminInformProfDistVoucherMakeCmpResponse l_response = l_impl.submitVoucherMakeCmp(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    public void testSubmitVoucherMakeCmp_0002()
//    {
//        final String STR_METHOD_NAME = "testSubmitVoucherMakeCmp_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(VariousInformParams.TYPE);
//            
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setAdministratorCode("33001");
//            l_administratorParams.setInstitutionCode("0D");
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//
//            WEB3AdministratorForMock.mockValidateAuthority(
//                l_expectAdministrator, "A0105", true, true);
//            
//            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
//
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            WEB3AdminInformProfDistVoucherMakeCmpRequestForTest l_request =
//                new WEB3AdminInformProfDistVoucherMakeCmpRequestForTest();
//            WEB3InformDetailInfoUnit l_unit = this.getInformDetailInfoUnit();
//            l_unit.div5 = "1";
//            
//            l_request.informInfoUnit = l_unit;
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                "getNewOrderRequestCode",
//                new Class[]{ String.class, String.class },
//                "2007060800009");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
//                "getNewNumber",
//                new Class[]{ String.class, String.class, ProductTypeEnum.class },
//                "12345");
//            
//            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
//                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
//            
//            WEB3AdminInformProfDistVoucherMakeCmpResponse l_response = l_impl.submitVoucherMakeCmp(l_request);
//            
//            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//            VariousInformPK l_variousInformPK = new VariousInformPK();
//            l_variousInformPK.institution_code = "60";
//            l_variousInformPK.branch_code = "624";
//            l_variousInformPK.inform_div = "1";
//            l_variousInformPK.request_number = "2007060800009";
//            VariousInformParams l_variousInformParams =
//                (VariousInformParams)l_queryProcessor.doFindByPrimaryKeyQuery(l_variousInformPK);
//            
//            assertEquals("12345", l_variousInformParams.getOrderRequestNumber());
//            assertEquals("33001", l_variousInformParams.getLastUpdater());
//            assertEquals("1", l_variousInformParams.getStatus());
//            assertEquals("GI823", l_variousInformParams.getRequestCode());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
//    
//    public void testSubmitVoucherMakeCmp_0003()
//    {
//        final String STR_METHOD_NAME = "testSubmitVoucherMakeCmp_0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(VariousInformParams.TYPE);
//            
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setAdministratorCode("33001");
//            l_administratorParams.setInstitutionCode("0D");
//            l_administratorParams.setBranchCode("381");
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
//
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
//
//            WEB3AdministratorForMock.mockValidateAuthority(
//                l_expectAdministrator, "A0105", true, true);
//            
//            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
//
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            WEB3AdminInformProfDistVoucherMakeCmpRequestForTest l_request =
//                new WEB3AdminInformProfDistVoucherMakeCmpRequestForTest();
//            WEB3InformDetailInfoUnit l_unit = this.getInformDetailInfoUnit();
//            l_unit.div5 = "2";
//            
//            l_request.informInfoUnit = l_unit;
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                "getNewOrderRequestCode",
//                new Class[]{ String.class, String.class },
//                "2007060800009");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
//                "getNewNumber",
//                new Class[]{ String.class, String.class, ProductTypeEnum.class },
//                "12345");
//            
//            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
//                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
//            
//            WEB3AdminInformProfDistVoucherMakeCmpResponse l_response = l_impl.submitVoucherMakeCmp(l_request);
//            
//            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
//            VariousInformPK l_variousInformPK = new VariousInformPK();
//            l_variousInformPK.institution_code = "60";
//            l_variousInformPK.branch_code = "624";
//            l_variousInformPK.inform_div = "1";
//            l_variousInformPK.request_number = "2007060800009";
//            VariousInformParams l_variousInformParams =
//                (VariousInformParams)l_queryProcessor.doFindByPrimaryKeyQuery(l_variousInformPK);
//            
//            assertEquals("12345", l_variousInformParams.getOrderRequestNumber());
//            assertEquals("33001", l_variousInformParams.getLastUpdater());
//            assertEquals("1", l_variousInformParams.getStatus());
//            assertEquals("GI828", l_variousInformParams.getRequestCode());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(TEST_END + STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
    
    public void testGetTransferAccountInfoRefScreen_0001()
    {
        final String STR_METHOD_NAME = "testSubmitVoucherMakeCmp_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();
            WEB3AdminInformProfDistVoucherInfoRefRequest l_request = null;
            WEB3AdminInformProfDistVoucherInfoRefResponse l_response = l_impl.getTransferAccountInfoRefScreen(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetTransferAccountInfoRefScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetTransferAccountInfoRefScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);
            
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminInformProfDistVoucherInfoRefRequestForTest l_request =
                new WEB3AdminInformProfDistVoucherInfoRefRequestForTest();
            
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();

            WEB3AdminInformProfDistVoucherInfoRefResponse l_response =
                l_impl.getTransferAccountInfoRefScreen(l_request);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetTransferAccountInfoRefScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetTransferAccountInfoRefScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdministratorForMock.mockValidateAuthority(
                l_expectAdministrator, "A0105", false, true);
            
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            WEB3AdminInformProfDistVoucherInfoRefRequestForTest l_request =
                new WEB3AdminInformProfDistVoucherInfoRefRequestForTest();
            l_request.branchCode = "381";
            l_request.requestNumber = "331";
            l_request.informType = "1";
            
            VariousInformParams l_variousInformParams = new VariousInformParams();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("331");
            l_variousInformParams.setBranchCode("381");
            l_variousInformParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_variousInformParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            TestDBUtility.deleteAll(l_variousInformParams.getRowType());
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3AdminInformProfDistRegistVoucherMakeServiceImpl l_impl =
                new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl();

            WEB3AdminInformProfDistVoucherInfoRefResponse l_response =
                l_impl.getTransferAccountInfoRefScreen(l_request);
            
            assertEquals(l_response.informInfoUnit.branchCode, "381");
            assertEquals(l_response.informInfoUnit.institutionCode, "0D");
            assertEquals(l_response.informInfoUnit.requestNumber, "331");
            assertEquals(l_response.informInfoUnit.informType, "1");
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    
    private WEB3InformDetailInfoUnit getInformDetailInfoUnit()
    {
        WEB3InformDetailInfoUnit l_unit = new
        WEB3InformDetailInfoUnit();
        l_unit.informType = "1";
        l_unit.branchCode = "624";
        l_unit.accountNumber = "123";
        l_unit.institutionCode = "60";
        l_unit.num1 = "1";
        l_unit.num2 = "2";
        l_unit.num3 = "3";
        l_unit.num4 = "4";
        l_unit.num5 = "5";
        l_unit.num6 = "6";
        l_unit.num7 = "7";
        l_unit.num8 = "8";
        l_unit.num9 = "9";
        l_unit.num10 = "10";
        l_unit.num11 = "11";
        l_unit.num12 = "12";
        l_unit.num13 = "13";
        l_unit.num14 = "14";
        l_unit.num15 = "15";
        l_unit.num16 = "16";
        l_unit.num17 = "17";
        l_unit.num18 = "18";
        l_unit.num19 = "19";
        l_unit.num20 = "20";
        l_unit.num21 = "21";
        l_unit.num22 = "22";
        l_unit.num23 = "23";
        l_unit.num24 = "24";
        l_unit.num25 = "25";
        l_unit.num26 = "26";
        l_unit.num27 = "27";
        l_unit.num28 = "28";
        l_unit.num29 = "29";
        l_unit.num30 = "30";
        l_unit.div4 = "4";
        l_unit.div2 = "4";
        l_unit.div3 = "2";
        l_unit.div5 = "1";
        l_unit.code1 = "1234567";
        l_unit.code3 = "2";
        l_unit.code4 = "3";
        
        return l_unit;
    }
    
    public class WEB3AdminInformProfDistVoucherInfoRefRequestForTest extends WEB3AdminInformProfDistVoucherInfoRefRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    public class WEB3AdminInformProfDistVoucherMakeInpRequestForTest extends WEB3AdminInformProfDistVoucherMakeInpRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    public class WEB3AdminInformProfDistVoucherMakeCnfRequestForTest extends WEB3AdminInformProfDistVoucherMakeCnfRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    public class WEB3AdminInformProfDistVoucherMakeCmpRequestForTest extends WEB3AdminInformProfDistVoucherMakeCmpRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }

}
@
