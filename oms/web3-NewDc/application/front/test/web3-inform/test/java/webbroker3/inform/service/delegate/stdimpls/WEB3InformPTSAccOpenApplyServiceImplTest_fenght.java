head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformPTSAccOpenApplyServiceImplTest_fenght.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeOrderValidatorForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.message.WEB3InformPTSTradeAgreementUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformPTSAccOpenApplyServiceImplTest_fenght extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyServiceImplTest_fenght.class);
    static boolean isUser = false;
    
    public WEB3InformPTSAccOpenApplyServiceImplTest_fenght(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    WEB3InformPTSAccOpenApplyServiceImpl l_impl = 
        new WEB3InformPTSAccOpenApplyServiceImpl();
    
    
    
    /*
     * Test method for 'webbroker3.inform.service.delegate.stdimpls.WEB3InformPTSAccOpenApplyServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute_T001() 
    {
        final String STR_METHOD_NAME = "testExecute_T001";
        
        log.entering(STR_METHOD_NAME);
        
       try
       {
           
           WEB3GenRequest l_request= null;
           
           l_impl.execute(l_request);
           
           fail();
           
       }
       catch (WEB3BaseException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
       }

        log.exiting(STR_METHOD_NAME);
        
    }

    public void testExecute_T002() 
    {
        final String STR_METHOD_NAME = "testExecute_T002";
        
        log.entering(STR_METHOD_NAME);
        
        
        WEB3InformPTSAccOpenApplyServiceImplForTest l_impl = 
            new WEB3InformPTSAccOpenApplyServiceImplForTest();
        
       try
       {
           
           WEB3InformPTSAccOpenApplyInpRequest l_request = 
               new WEB3InformPTSAccOpenApplyInpRequest();

           WEB3InformPTSAccOpenApplyInpResponse l_response = 
               (WEB3InformPTSAccOpenApplyInpResponse)l_impl.execute(l_request);
           
           assertEquals("accountName",l_response.accountName);
           
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
        final String STR_METHOD_NAME = "testExecute_T003";
        
        log.entering(STR_METHOD_NAME);
        
        
        WEB3InformPTSAccOpenApplyServiceImplForTest l_impl = 
            new WEB3InformPTSAccOpenApplyServiceImplForTest();
        
       try
       {
           isUser = true;
           WEB3InformPTSAccOpenApplyCnfRequest l_request = 
               new WEB3InformPTSAccOpenApplyCnfRequest();

           WEB3InformPTSAccOpenApplyCnfResponse l_response = 
               (WEB3InformPTSAccOpenApplyCnfResponse)l_impl.execute(l_request);
           
           assertEquals("1001",l_response.productCode[0]);
           assertEquals("1002",l_response.productCode[1]);
           isUser = false;
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
        final String STR_METHOD_NAME = "testExecute_T004";
        
        log.entering(STR_METHOD_NAME);
        
        
        WEB3InformPTSAccOpenApplyServiceImplForTest l_impl = 
            new WEB3InformPTSAccOpenApplyServiceImplForTest();
        
       try
       {
           
           WEB3InformPTSAccOpenApplyCmpRequest l_request = 
               new WEB3InformPTSAccOpenApplyCmpRequest();

         l_impl.execute(l_request);

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
        final String STR_METHOD_NAME = "testExecute_T005";
        
        log.entering(STR_METHOD_NAME);

       try
       {
           
           WEB3AdminInformAccSwElecDeliApplySrcRequest l_request = 
               new WEB3AdminInformAccSwElecDeliApplySrcRequest();

         l_impl.execute(l_request);
         fail();
       }
       catch(WEB3SystemLayerException l_ex)
       {
           assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
       }
       catch (WEB3BaseException l_ex)
       {
           log.error(l_ex.getErrorMessage(),l_ex);
           fail();
       }
        log.exiting(STR_METHOD_NAME);

    }

    public void testValidateApply_T01()
    {
        final String STR_METHDO_NAME = "testValidateApply_T01()";
        log.entering(STR_METHDO_NAME);
        try
        {
            
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            l_request.informType = null;
            l_impl.validateApply(l_request);

            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01817, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHDO_NAME);
    }
    
    public void testValidateApply_T02()
    {
        final String STR_METHDO_NAME = "testValidateApply_T02()";
        log.entering(STR_METHDO_NAME);
        try
        {
            WEB3InformPTSAccOpenApplyServiceImplForTest l_implForTest =
                new WEB3InformPTSAccOpenApplyServiceImplForTest();
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList =
                new WEB3InformPTSTradeAgreementUnit[3];
            ptsTradeAgreementList[0] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[0].questionNumber = "1";
            ptsTradeAgreementList[0].questionAnswer = "12";

            ptsTradeAgreementList[1] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[1].questionNumber = "1";
            ptsTradeAgreementList[1].questionAnswer = "23";
            
            ptsTradeAgreementList[2] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[2].questionNumber = "1";
            ptsTradeAgreementList[2].questionAnswer = "23";
            l_request.ptsTradeAgreementList = ptsTradeAgreementList;
            l_request.informType = "2";
            l_request.ptsAccOpenDiv = "1";
            
            l_request.batoCheckFlag = true;
            l_request.typeCode = "1";
            l_request.productCode = new String[]{"2"};

            ProcessingResult l_ProcessingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);

            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            l_implForTest.validateApply(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHDO_NAME);
    }

    public void testValidateApply_T03()
    {
        final String STR_METHOD_NAME = "testValidateApply_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isUser = false;
            WEB3InformPTSAccOpenApplyServiceImplForTest l_implForTest =
                new WEB3InformPTSAccOpenApplyServiceImplForTest();
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList =
                new WEB3InformPTSTradeAgreementUnit[3];
            ptsTradeAgreementList[0] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[0].questionNumber = "1";
            ptsTradeAgreementList[0].questionAnswer = "12";

            ptsTradeAgreementList[1] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[1].questionNumber = "1";
            ptsTradeAgreementList[1].questionAnswer = "23";
            
            ptsTradeAgreementList[2] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[2].questionNumber = "1";
            ptsTradeAgreementList[2].questionAnswer = "23";
            l_request.ptsTradeAgreementList = ptsTradeAgreementList;
            l_request.informType = "2";
            l_request.ptsAccOpenDiv = "1";
            
            l_request.batoCheckFlag = true;
            l_request.typeCode = "1";
            l_request.productCode = new String[]{"2"};

            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);

            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_implForTest.validateApply(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03024, l_ex.getErrorInfo());
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail(); 
        }
 
        log.exiting(STR_METHOD_NAME); 
    }
    
    public void testValidateApply_T04()
    {
        final String STR_METHOD_NAME = "testValidateApply_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isUser = false;
            WEB3InformPTSAccOpenApplyServiceImplForTest l_implForTest =
                new WEB3InformPTSAccOpenApplyServiceImplForTest();
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList =
                new WEB3InformPTSTradeAgreementUnit[3];
            ptsTradeAgreementList[0] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[0].questionNumber = "1";
            ptsTradeAgreementList[0].questionAnswer = "1";

            ptsTradeAgreementList[1] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[1].questionNumber = "1";
            ptsTradeAgreementList[1].questionAnswer = "1";
            
            ptsTradeAgreementList[2] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[2].questionNumber = "1";
            ptsTradeAgreementList[2].questionAnswer = "1";
            l_request.ptsTradeAgreementList = ptsTradeAgreementList;
            l_request.informType = "2";
            l_request.ptsAccOpenDiv = "1";
            
            l_request.batoCheckFlag = true;
            l_request.typeCode = "1";
            l_request.productCode = new String[]{"2"};

            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);

            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setPtsAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_variousInformParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_variousInformParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("2007128", "yyyyMMdd"));
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("0");
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_implForTest.validateApply(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03025, l_ex.getErrorInfo());
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail(); 
        }
 
        log.exiting(STR_METHOD_NAME); 
    }
    
    public void testValidateApply_T05()
    {
        final String STR_METHOD_NAME = "testValidateApply_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isUser = false;
            WEB3InformPTSAccOpenApplyServiceImplForTest l_implForTest =
                new WEB3InformPTSAccOpenApplyServiceImplForTest();
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList =
                new WEB3InformPTSTradeAgreementUnit[3];
            ptsTradeAgreementList[0] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[0].questionNumber = "1";
            ptsTradeAgreementList[0].questionAnswer = "1";

            ptsTradeAgreementList[1] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[1].questionNumber = "1";
            ptsTradeAgreementList[1].questionAnswer = "2";
            
            ptsTradeAgreementList[2] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[2].questionNumber = "1";
            ptsTradeAgreementList[2].questionAnswer = "1";
            l_request.ptsTradeAgreementList = ptsTradeAgreementList;
            l_request.informType = "2";
            l_request.ptsAccOpenDiv = "1";
            
            l_request.batoCheckFlag = true;
            l_request.typeCode = "1";
            l_request.productCode = new String[]{"2"};

            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);

            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setPtsAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_variousInformParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_variousInformParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("2");
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_implForTest.validateApply(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03026, l_ex.getErrorInfo());
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail(); 
        }
 
        log.exiting(STR_METHOD_NAME); 
    }

    public void testValidateApply_T06()
    {
        final String STR_METHOD_NAME = "testValidateApply_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isUser = false;
            WEB3InformPTSAccOpenApplyServiceImplForTest l_implForTest =
                new WEB3InformPTSAccOpenApplyServiceImplForTest();
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList =
                new WEB3InformPTSTradeAgreementUnit[3];
            ptsTradeAgreementList[0] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[0].questionNumber = "1";
            ptsTradeAgreementList[0].questionAnswer = "1";

            ptsTradeAgreementList[1] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[1].questionNumber = "1";
            ptsTradeAgreementList[1].questionAnswer = "1";
            
            ptsTradeAgreementList[2] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[2].questionNumber = "1";
            ptsTradeAgreementList[2].questionAnswer = "1";
            l_request.ptsTradeAgreementList = ptsTradeAgreementList;
            l_request.informType = "2";
            l_request.ptsAccOpenDiv = "1";
            
            l_request.batoCheckFlag = true;
            l_request.typeCode = "1";
            l_request.productCode = new String[]{"1002"};

            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);

            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setPtsAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_variousInformParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_variousInformParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("2");
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3GentradeProspectusResult l_prospectusResult =
                new WEB3GentradeProspectusResult();
            l_prospectusResult.checkResult = "0";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_prospectusResult);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3InformPTSAccOpenApplyCnfResponse l_response =
                l_implForTest.validateApply(l_request);
            assertNull(l_response.productCode);
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail(); 
        }
 
        log.exiting(STR_METHOD_NAME); 
    }
    
    public void testValidateApply_T07()
    {
        final String STR_METHOD_NAME = "testValidateApply_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isUser = false;
            WEB3InformPTSAccOpenApplyServiceImplForTest l_implForTest =
                new WEB3InformPTSAccOpenApplyServiceImplForTest();
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList =
                new WEB3InformPTSTradeAgreementUnit[3];
            ptsTradeAgreementList[0] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[0].questionNumber = "1";
            ptsTradeAgreementList[0].questionAnswer = "1";

            ptsTradeAgreementList[1] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[1].questionNumber = "1";
            ptsTradeAgreementList[1].questionAnswer = "1";
            
            ptsTradeAgreementList[2] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[2].questionNumber = "1";
            ptsTradeAgreementList[2].questionAnswer = "1";
            l_request.ptsTradeAgreementList = ptsTradeAgreementList;
            l_request.informType = "2";
            l_request.ptsAccOpenDiv = "1";
            
            l_request.batoCheckFlag = false;
            l_request.typeCode = "1";
            l_request.productCode = new String[]{"1002"};

            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);

            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setPtsAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_variousInformParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_variousInformParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("2");
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3GentradeProspectusResult l_prospectusResult =
                new WEB3GentradeProspectusResult();
            l_prospectusResult.checkResult = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_prospectusResult);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3InformPTSAccOpenApplyCnfResponse l_response =
                l_implForTest.validateApply(l_request);
            assertNull(l_response.productCode);
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail(); 
        }
 
        log.exiting(STR_METHOD_NAME); 
    }
    
    public void testValidateApply_T08()
    {
        final String STR_METHOD_NAME = "testValidateApply_T08()";
        log.entering(STR_METHOD_NAME);
        try
        {
            isUser = false;
            WEB3InformPTSAccOpenApplyServiceImplForTest l_implForTest =
                new WEB3InformPTSAccOpenApplyServiceImplForTest();
            WEB3InformPTSAccOpenApplyCnfRequest l_request =
                new WEB3InformPTSAccOpenApplyCnfRequest();
            WEB3InformPTSTradeAgreementUnit[] ptsTradeAgreementList =
                new WEB3InformPTSTradeAgreementUnit[3];
            ptsTradeAgreementList[0] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[0].questionNumber = "1";
            ptsTradeAgreementList[0].questionAnswer = "1";

            ptsTradeAgreementList[1] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[1].questionNumber = "1";
            ptsTradeAgreementList[1].questionAnswer = "1";
            
            ptsTradeAgreementList[2] = new WEB3InformPTSTradeAgreementUnit();
            ptsTradeAgreementList[2].questionNumber = "1";
            ptsTradeAgreementList[2].questionAnswer = "1";
            l_request.ptsTradeAgreementList = ptsTradeAgreementList;
            l_request.informType = "2";
            l_request.ptsAccOpenDiv = "1";
            
            l_request.batoCheckFlag = true;
            l_request.typeCode = "1";
            l_request.productCode = new String[]{"1002"};

            ProcessingResult l_ProcessingResult =
                ProcessingResult.newSuccessResultInstance();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);

            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setPtsAccOpenDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //VariousInformParams
            TestDBUtility.deleteAll(VariousInformParams.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_variousInformParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_variousInformParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071208", "yyyyMMdd"));
            l_variousInformParams.setExtDiv1("1");
            l_variousInformParams.setExtDiv2("2");
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3GentradeProspectusResult l_prospectusResult =
                new WEB3GentradeProspectusResult();
            l_prospectusResult.checkResult = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_prospectusResult);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3InformPTSAccOpenApplyCnfResponse l_response =
                l_implForTest.validateApply(l_request);
            assertEquals("1002", l_response.productCode[0]);
        }
        catch(Exception  l_ex)
        {
            log.error(l_ex.getMessage());
            fail(); 
        }
 
        log.exiting(STR_METHOD_NAME); 
    }
    
    public void testvalidateOrder_T001()
    {
        final String STR_METHOD_NAME = "testvalidateOrder_T001";
        log.entering(STR_METHOD_NAME);

        try 
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);  

            Object[] obj = {l_subAccount};
            Method method = WEB3InformPTSAccOpenApplyServiceImpl.class.getDeclaredMethod(
                  "validateOrder", 
                  new Class[]{SubAccount.class});
            method.setAccessible(true); 
            method.invoke(l_impl, obj);

        } catch (Exception l_ex) 
        {
           log.error(l_ex.getMessage(),l_ex);
           fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testvalidateOrder_T002()
    {
        final String STR_METHOD_NAME = "testvalidateOrder_T002";
        log.entering(STR_METHOD_NAME);

        try 
        {
            ProcessingResult l_ProcessingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3GentradeOrderValidatorForMock l_orderalidateor =
                new WEB3GentradeOrderValidatorForMock();
            l_finApp.overrideCommonOrderValidator(l_orderalidateor);
            
            OrderValidationResult l_result = new OrderValidationResult(l_ProcessingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_result);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setMarginGenAccOpenDiv(
                WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);  

            Object[] obj = {l_subAccount};
            Method method = WEB3InformPTSAccOpenApplyServiceImpl.class.getDeclaredMethod(
                  "validateOrder", 
                  new Class[]{SubAccount.class});
            method.setAccessible(true); 
            method.invoke(l_impl, obj);
            
            fail();
        }
      
        catch (InvocationTargetException l_exc)
        {
            log.error(l_exc.getMessage(), l_exc);
            WEB3BusinessLayerException l_ex =
                (WEB3BusinessLayerException)l_exc.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals("", l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetQuestion_T01()
    {
        final String STR_METHOD_NAME = "testGetQuestion_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //QuestionParams
            TestDBUtility.deleteAll(QuestionParams.TYPE);
            Method method = WEB3InformPTSAccOpenApplyServiceImpl.class.getDeclaredMethod(
                "getQuestion",
                new Class[]{String.class, String.class});
            Object[] obj = new Object[]{"0D", "624"};
            method.setAccessible(true); 
            Object result = method.invoke(l_impl, obj);
            assertNull(result);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetQuestion_T02()
    {
        final String STR_METHOD_NAME = "testGetQuestion_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //QuestionParams
            TestDBUtility.deleteAll(QuestionParams.TYPE);
            QuestionParams l_questionParams =
                this.getQuestionParams();
            l_questionParams.setQuestionDiv("0003");
            l_questionParams.setQuestionNo("00");
            TestDBUtility.insertWithDel(l_questionParams);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_questionParams.setQuestionNo("02");
            l_processor.doInsertQuery(l_questionParams);
            
            l_questionParams.setQuestionNo("01");
            l_processor.doInsertQuery(l_questionParams);
            
            Method method = WEB3InformPTSAccOpenApplyServiceImpl.class.getDeclaredMethod(
                "getQuestion",
                new Class[]{String.class, String.class});
            Object[] obj = new Object[]{"0D", "624"};
            method.setAccessible(true); 
            Object[] result = (Object[])method.invoke(l_impl, obj);
            assertEquals(3, result.length);
            assertEquals("00", ((QuestionParams)result[0]).getQuestionNo());
            assertEquals("01", ((QuestionParams)result[1]).getQuestionNo());
            assertEquals("02", ((QuestionParams)result[2]).getQuestionNo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidatePTSAccOpenQuestion_T01()
    {
        final String STR_METHOD_NAME = "testValidatePTSAccOpenQuestion_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method method = l_impl.getClass().getDeclaredMethod(
                "validatePTSAccOpenQuestion",
                new Class[]{WEB3InformPTSTradeAgreementUnit[].class});
            
            WEB3InformPTSTradeAgreementUnit[] l_unit =
                new WEB3InformPTSTradeAgreementUnit[2];
            l_unit[0] = new WEB3InformPTSTradeAgreementUnit();
            l_unit[0].questionAnswer = "1";

            l_unit[1] = new WEB3InformPTSTradeAgreementUnit();
            l_unit[1].questionAnswer = "1";
            Object[] obj = new Object[]{l_unit};
            method.setAccessible(true); 
            method.invoke(l_impl, obj);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidatePTSAccOpenQuestion_T02()
    {
        final String STR_METHOD_NAME = "testValidatePTSAccOpenQuestion_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method method = l_impl.getClass().getDeclaredMethod(
                "validatePTSAccOpenQuestion",
                new Class[]{WEB3InformPTSTradeAgreementUnit[].class});
            
            WEB3InformPTSTradeAgreementUnit[] l_unit =
                new WEB3InformPTSTradeAgreementUnit[2];
            l_unit[0] = new WEB3InformPTSTradeAgreementUnit();
            l_unit[0].questionAnswer = "1";

            l_unit[1] = new WEB3InformPTSTradeAgreementUnit();
            l_unit[1].questionAnswer = "0";
            Object[] obj = new Object[]{l_unit};
            method.setAccessible(true); 
            method.invoke(l_impl, obj);
            fail();
        }
        catch(InvocationTargetException l_ex)
        {
            WEB3BusinessLayerException l_bEx =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(l_bEx.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03026);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidatePTSTradingDocReadHistory_T01()
    {
        final String STR_METHOD_NAME = "testValidatePTSTradingDocReadHistory_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Method method = l_impl.getClass().getDeclaredMethod(
                "validatePTSTradingDocReadHistory",
                new Class[]{String.class, String[].class});

            Object[] obj = new Object[]{"001", new String[0]};
            method.setAccessible(true); 
            Object[] result = (Object[])method.invoke(l_impl, obj);
            assertNull(result);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidatePTSTradingDocReadHistory_T02()
    {
        final String STR_METHOD_NAME = "testValidatePTSTradingDocReadHistory_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeProspectusResult l_prospectusResult =
                new WEB3GentradeProspectusResult();
            l_prospectusResult.checkResult = "1";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_prospectusResult);

            Method method = l_impl.getClass().getDeclaredMethod(
                "validatePTSTradingDocReadHistory",
                new Class[]{String.class, String[].class});

            Object[] obj = new Object[]{"001",
                new String[]{"1001", "1002", "1003"}};
            method.setAccessible(true); 
            Object[] result = (Object[])method.invoke(l_impl, obj);
            assertEquals(3, result.length);
            assertEquals("1001", (String)result[0]);
            assertEquals("1002", (String)result[1]);
            assertEquals("1003", (String)result[2]);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidatePTSTradingDocReadHistory_T03()
    {
        final String STR_METHOD_NAME = "testValidatePTSTradingDocReadHistory_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeProspectusResult l_prospectusResult =
                new WEB3GentradeProspectusResult();
            l_prospectusResult.checkResult = "2";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_prospectusResult);

            Method method = l_impl.getClass().getDeclaredMethod(
                "validatePTSTradingDocReadHistory",
                new Class[]{String.class, String[].class});

            Object[] obj = new Object[]{"001",
                new String[]{"1001", "1002", "1003"}};
            method.setAccessible(true); 
            Object result = method.invoke(l_impl, obj);
            assertNull(result);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public QuestionParams getQuestionParams()
    {
        QuestionParams l_params = new QuestionParams();
        //証券会社コード   institution_code VARCHAR2  3   NotNull      
        l_params.setInstitutionCode("0D");
        //部店コード     branch_code VARCHAR2  3   NotNull   
        l_params.setBranchCode("624");
        //質問区分  question_div VARCHAR2  4   NotNull   
        l_params.setQuestionDiv("1");
        //質問番号  question_no VARCHAR2  3   NotNull
        l_params.setQuestionNo("2");
        //質問内容  question VARCHAR2  1000   NotNull    
        l_params.setQuestion("jiddk");
        //更新者コード    last_updater VARCHAR2  20   Null  
        l_params.setLastUpdater("1001");
        //作成日付  created_timestamp DATE     NotNull  
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付  last_updated_timestamp  DATE     NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    
    class WEB3InformPTSAccOpenApplyServiceImplForTest extends WEB3InformPTSAccOpenApplyServiceImpl
    {
        
        protected WEB3InformPTSAccOpenApplyInpResponse getInputScreen(WEB3InformPTSAccOpenApplyInpRequest l_request)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getInputScreen(WEB3InformPTSAccOpenApplyInpRequest)";
            log.entering(STR_METHOD_NAME);
            //レスポンスデータを生成する
            WEB3InformPTSAccOpenApplyInpResponse l_response =
                (WEB3InformPTSAccOpenApplyInpResponse)l_request.createResponse();

            l_response.accountName = "accountName";
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        protected WEB3InformPTSAccOpenApplyCnfResponse validateApply(WEB3InformPTSAccOpenApplyCnfRequest l_request)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "validateApply(WEB3InformPTSAccOpenApplyCnfRequest)";
            log.entering(STR_METHOD_NAME);
            if (isUser)
            {
                WEB3InformPTSAccOpenApplyCnfResponse l_response =
                    (WEB3InformPTSAccOpenApplyCnfResponse)l_request.createResponse();
               
                l_response.productCode = new String[]{"1001","1002"};
        
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            else
            {
                return super.validateApply(l_request);
            }
        }
        
        protected WEB3InformPTSAccOpenApplyCmpResponse submitApply(WEB3InformPTSAccOpenApplyCmpRequest l_request)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "submitApply(WEB3InformPTSAccOpenApplyCmpRequest)";
            log.entering(STR_METHOD_NAME);
    
          
            WEB3InformPTSAccOpenApplyCmpResponse l_response =
                (WEB3InformPTSAccOpenApplyCmpResponse)l_request.createResponse();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getSubAccount()";
            log.entering(STR_METHOD_NAME);
            WEB3GentradeSubAccount l_subAccount = null;
          
                
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(1001L);
            
            l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
  
            
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
     
        }
        
    }
}
@
