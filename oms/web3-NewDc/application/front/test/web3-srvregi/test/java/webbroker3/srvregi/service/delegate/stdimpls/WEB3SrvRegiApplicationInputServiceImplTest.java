head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiApplicationInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3SrvRegiApplicationInputServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/11 金傑 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiCommCondParams;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiDealingCommParams;
import webbroker3.srvregi.data.SrvRegiDealingCommRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiConsentRequest;
import webbroker3.srvregi.message.WEB3SrvRegiConsentResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiApplicationInputServiceImplTest extends TestBaseForMock
{

    private WEB3SrvRegiApplicationInputServiceImpl l_serviceImpl = null;
    
    private WEB3SrvRegiConsentRequest l_request = null;
    
    private WEB3SrvRegiConsentResponse l_response = null;
    
    private WEB3SrvRegiApplyInputRequest l_requestForInput = null;
    
    private SubAccountParams l_subAccountParams = null;
    
    private WEB3GentradeSubAccount l_subAccountForMock = null;
    
    private WEB3GentradeMainAccount l_mainAccountForMock = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3SrvRegiApplicationInputServiceImplTest.class);

    public WEB3SrvRegiApplicationInputServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3SrvRegiApplicationInputServiceImplForTest();
        this.l_request = new WEB3SrvRegiConsentRequestForTest();
        this.l_requestForInput = new WEB3SrvRegiApplyInputRequestForTest();
        this.initData();
        this.setMockMethod();
    }
    
    protected void tearDown() throws Exception
    {
//        super.checkMethodValue();
        super.tearDown();
        this.l_serviceImpl = null;
        this.l_request = null;
        this.l_subAccountParams = null;
        this.l_subAccountForMock = null;
        this.l_mainAccountForMock = null;
        
    }
    
    /**
     * リクエストデータ.無料属性申込区分 = '1' の場合 並且
     * getサービス申込属性情報() == null の場合
     *
     */
    public void testDocScreenRequest_C0001()
    {
        final String STR_METHOD_NAME = "testDocScreenRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.freeAttributeApplyDiv = "1";
            this.l_request.serviceDiv = "1234";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSrvDiv("1234");
            l_srvRegiSetupParams.setSupplyDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);

//            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//            l_srvAppliAttributeParams.setInstitutionCode("1D");
//            l_srvAppliAttributeParams.setBranchCode("381");
//            l_srvAppliAttributeParams.setAccountCode("2512246");
//            l_srvAppliAttributeParams.setSrvDiv("1234");
//            l_srvAppliAttributeParams.setAppliAttribute("2");
//            l_srvAppliAttributeParams.setAppliStartDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//            l_srvAppliAttributeParams.setAppliEndDate(WEB3DateUtility.getDate("20070616","yyyyMMdd"));
//            l_srvAppliAttributeParams.setLastUpdater("0213");
//            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            
//            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
            this.l_subAccountParams.setInstitutionCode("2D");
            TestDBUtility.insertWithDel(this.l_subAccountParams);
            
            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    this.l_mainAccountForMock);
            
            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateSubAccountForTrading", 
                    new Class[] { SubAccount.class });

           assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
           
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02805,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * リクエストデータ.無料属性申込区分 = '1' の場合 並且
     * getサービス申込属性情報() != null の場合 並且
     * getサービス申込属性情報().申込属性区分 == '2'(申込不可) の場合
     *
     */
    public void testDocScreenRequest_C0002()
    {
        final String STR_METHOD_NAME = "testDocScreenRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512246L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          
          TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
          SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
          l_srvRegiSetupParams.setInstitutionCode("1D");
          l_srvRegiSetupParams.setSrvDiv("1234");
          l_srvRegiSetupParams.setSupplyDiv("1");
          TestDBUtility.insertWithDel(l_srvRegiSetupParams);
          
          TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
          
            
            this.l_request.freeAttributeApplyDiv = "1";
            this.l_request.serviceDiv = "1234";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
            this.l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(this.l_subAccountParams);
            
            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    this.l_mainAccountForMock);
            
            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateSubAccountForTrading", 
                    new Class[] { SubAccount.class });

           assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02805,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     * リクエストデータ.無料属性申込区分 = '1' の場合 並且
     * getサービス申込属性情報() != null の場合 並且
     * get無料対象期間 == null
     *
     */
    public void testDocScreenRequest_C0003()
    {
        final String STR_METHOD_NAME = "testDocScreenRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
          TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
          SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
          l_srvAppliAttributeParams.setInstitutionCode("1D");
          l_srvAppliAttributeParams.setBranchCode("381");
          l_srvAppliAttributeParams.setAccountCode("2512246");
          l_srvAppliAttributeParams.setSrvDiv("1234");
          l_srvAppliAttributeParams.setAppliAttribute("1");
//          l_srvAppliAttributeParams.setAppliStartDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//          l_srvAppliAttributeParams.setAppliEndDate(WEB3DateUtility.getDate("20070616","yyyyMMdd"));
          l_srvAppliAttributeParams.setLastUpdater("0213");
          l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//          l_srvAppliAttributeParams.set
          
          TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
          
            
          SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();       
          l_srvRegiMasterParams.setInstitutionCode("1D");
          l_srvRegiMasterParams.setSrvStatus("2");
          TestDBUtility.insertWithDel(l_srvRegiMasterParams);
          
          TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
          SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
          l_srvRegiSetupParams.setInstitutionCode("1D");
          l_srvRegiSetupParams.setSrvDiv("1234");
          l_srvRegiSetupParams.setSupplyDiv("1");
          TestDBUtility.insertWithDel(l_srvRegiSetupParams);
          
            this.l_request.freeAttributeApplyDiv = "1";
            this.l_request.serviceDiv = "1234";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
            this.l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(this.l_subAccountParams);
            
            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    this.l_mainAccountForMock);
            
            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateSubAccountForTrading", 
                    new Class[] { SubAccount.class });

           assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02806,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * リクエストデータ.無料属性申込区分 = '1' の場合 並且
     * getサービス申込属性情報() != null の場合 並且
     * get無料対象期間 != null
     *
     */
    public void testDocScreenRequest_C0004()
    {
        final String STR_METHOD_NAME = "testDocScreenRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
          TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
          SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
          l_srvAppliAttributeParams.setInstitutionCode("1D");
          l_srvAppliAttributeParams.setBranchCode("381");
          l_srvAppliAttributeParams.setAccountCode("2512246");
          l_srvAppliAttributeParams.setSrvDiv("1234");
          l_srvAppliAttributeParams.setAppliAttribute("1");
//          l_srvAppliAttributeParams.setAppliStartDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
//          l_srvAppliAttributeParams.setAppliEndDate(WEB3DateUtility.getDate("20070616","yyyyMMdd"));
          l_srvAppliAttributeParams.setLastUpdater("0213");
          l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          
          TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
          
          SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
          l_srvRegiSetupParams.setInstitutionCode("1D");
          l_srvRegiSetupParams.setSrvDiv("1234");
          l_srvRegiSetupParams.setSupplyDiv("1");
          l_srvRegiSetupParams.setFreeCoverageLength(1);
          TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            this.l_request.freeAttributeApplyDiv = "1";
            this.l_request.serviceDiv = "1234";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
            this.l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(this.l_subAccountParams);
            
            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    this.l_mainAccountForMock);
            
            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateSubAccountForTrading", 
                    new Class[] { SubAccount.class });

           assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());

            
            
            assertNotNull(this.l_response);
            assertEquals("1",this.l_response.applyAttributeDiv);
//            assertEquals(0,WEB3DateUtility.compareToDay(
//                this.l_response.applyAttributePeriodFrom,WEB3DateUtility.getDate("20070607","yyyyMMdd")));
//            assertEquals(0,WEB3DateUtility.compareToDay(
//                    this.l_response.applyAttributePeriodTo,WEB3DateUtility.getDate("20070616","yyyyMMdd")));
            assertEquals("1",l_response.freeAttributeApplyDiv);

        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
//    /**
//     * リクエストデータ.無料属性申込区分 == null の場合 並且
//     * getサービス申込属性情報().申込属性区分 == 無料対象の場合
//     *
//     */
//    public void testDocScreenRequest_C0005()
//    {
//        final String STR_METHOD_NAME = "testDocScreenRequest_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//          TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
//          SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//          l_srvAppliAttributeParams.setInstitutionCode("1D");
//          l_srvAppliAttributeParams.setBranchCode("381");
//          l_srvAppliAttributeParams.setAccountCode("2512246");
//          l_srvAppliAttributeParams.setSrvDiv("1234");
//          l_srvAppliAttributeParams.setAppliAttribute("1");
////          l_srvAppliAttributeParams.setAppliStartDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
////          l_srvAppliAttributeParams.setAppliEndDate(WEB3DateUtility.getDate("20070616","yyyyMMdd"));
//          l_srvAppliAttributeParams.setLastUpdater("0213");
//          l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//          l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//          
//          TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//          
//            
//          SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
//          l_srvRegiSetupParams.setInstitutionCode("1D");
//          l_srvRegiSetupParams.setSrvDiv("1234");
//          l_srvRegiSetupParams.setSupplyDiv("1");
//          l_srvRegiSetupParams.setFreeCoverageLength(1);
//          TestDBUtility.insertWithDel(l_srvRegiSetupParams);
//          
//            this.l_request.serviceDiv = "1234";
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
//            this.l_subAccountParams.setInstitutionCode("1D");
//            TestDBUtility.insertWithDel(this.l_subAccountParams);
//            
//            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
//            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
//                    "getMainAccount", 
//                    new Class[]{},
//                    this.l_mainAccountForMock);
//            
//            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);
//            fail();
//
//        }
//        catch(WEB3BaseException l_web3BaseException)
//        {
//            log.error("", l_web3BaseException);
//            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
//                    "validateSubAccountForTrading", 
//                    new Class[] { SubAccount.class });
//
//           assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02807,l_web3BaseException.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
    
//    /**
//     * リクエストデータ.無料属性申込区分 == null の場合 並且
//     * getサービス申込属性情報().申込属性区分 == 申込不可の場合
//     *
//     */
//    public void testDocScreenRequest_C0006()
//    {
//        final String STR_METHOD_NAME = "testDocScreenRequest_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//          TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
//          SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//          l_srvAppliAttributeParams.setInstitutionCode("1D");
//          l_srvAppliAttributeParams.setBranchCode("381");
//          l_srvAppliAttributeParams.setAccountCode("2512246");
//          l_srvAppliAttributeParams.setSrvDiv("1234");
//          l_srvAppliAttributeParams.setAppliAttribute("2");
////          l_srvAppliAttributeParams.setAppliStartDate(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
////          l_srvAppliAttributeParams.setAppliEndDate(WEB3DateUtility.getDate("20070616","yyyyMMdd"));
//          l_srvAppliAttributeParams.setLastUpdater("0213");
//          l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//          l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//          
//          TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//          
//            
//
//            this.l_request.serviceDiv = "1234";
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
//            this.l_subAccountParams.setInstitutionCode("1D");
//            TestDBUtility.insertWithDel(this.l_subAccountParams);
//            
//            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
//            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
//                    "getMainAccount", 
//                    new Class[]{},
//                    this.l_mainAccountForMock);
//            
//            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);
//            fail();
//
//        }
//        catch(WEB3BaseException l_web3BaseException)
//        {
//            log.error("", l_web3BaseException);
//            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
//                    "validateSubAccountForTrading", 
//                    new Class[] { SubAccount.class });
//
//           assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02807,l_web3BaseException.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }

    /**
     *
     */
    public void testDocScreenRequest_C0007()
    {
        final String STR_METHOD_NAME = "testDocScreenRequest_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams.setInstitutionCode("1D");
            l_srvAppliAttributeParams.setBranchCode("381");
            l_srvAppliAttributeParams.setAccountCode("2512246");
            l_srvAppliAttributeParams.setSrvDiv("1234");
            l_srvAppliAttributeParams.setAppliAttribute("1");
            l_srvAppliAttributeParams.setLastUpdater("0213");
            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSrvDiv("1234");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("1D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            this.l_request.freeAttributeApplyDiv = "1";
            this.l_request.serviceDiv = "1234";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
            this.l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(this.l_subAccountParams);

            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getMainAccount",
                    new Class[]{},
                    this.l_mainAccountForMock);

            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);

//            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
//                    "validateSubAccountForTrading",
//                    new Class[] { SubAccount.class });

            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                l_web3BaseException.getErrorInfo());
            log.error("", l_web3BaseException);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        finally
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    /**
     *
     */
    public void testDocScreenRequest_C0008()
    {
        final String STR_METHOD_NAME = "testDocScreenRequest_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_srvAppliAttributeParams.setInstitutionCode("1D");
            l_srvAppliAttributeParams.setBranchCode("381");
            l_srvAppliAttributeParams.setAccountCode("2512246");
            l_srvAppliAttributeParams.setSrvDiv("1");
            l_srvAppliAttributeParams.setAppliAttribute("1");
            l_srvAppliAttributeParams.setLastUpdater("0213");
            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSrvDiv("1");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("1D");
            l_srvRegiMasterParams.setSrvDiv("1");
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(123L);
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setId("12345");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setInstitutionCode("1D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("2512246");
            l_otherOrgInfoAdminParams.setLastUpdater("a");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

//            TestDBUtility.deleteAll(SrvRegiApplicationParams.TYPE);

            this.l_request.freeAttributeApplyDiv = "1";
            this.l_request.serviceDiv = "1";
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            this.l_subAccountParams = TestDBUtility.getSubAccountRow();
            this.l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(this.l_subAccountParams);

            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_mainAccountForMock = new WEB3GentradeMainAccountForMock(333812512246L);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getMainAccount",
                    new Class[]{},
                    this.l_mainAccountForMock);

            this.l_response = l_serviceImpl.docScreenRequest(this.l_request);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateSubAccountForTrading",
                    new Class[] { SubAccount.class });

           assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());

            assertNotNull(this.l_response);
            assertEquals("1",this.l_response.applyAttributeDiv);
            assertEquals("1",l_response.freeAttributeApplyDiv);
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get提供形式( )=null であり、かつリクエストデータ.申込種別区分="無料申込"の場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_01179
     */
    public void testuseAppliInputRequest_C0001()
    {
        final String STR_METHOD_NAME = "testuseAppliInputRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSupplyDiv(null);
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_requestForInput.serviceDiv = "1234";
            this.l_requestForInput.applyKindDiv = "3";
            this.l_serviceImpl.useAppliInputRequest(this.l_requestForInput);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex); 
          WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
          "webbroker3.gentrade.WEB3GentradeOrderValidator", 
          "validateSubAccountForTrading", 
          new Class[] { SubAccount.class });

          assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01179,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常返回
     */
    public void testuseAppliInputRequest_C0002()
    {
        final String STR_METHOD_NAME = "testuseAppliInputRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
            
            // SrvRegiDealingCommRow 売買手数料累計テーブル
            TestDBUtility.deleteAll(SrvRegiDealingCommRow.TYPE);
            SrvRegiDealingCommParams l_srvRegiDealingCommParams = new SrvRegiDealingCommParams();
            l_srvRegiDealingCommParams.setInstitutionCode("1D");
            l_srvRegiDealingCommParams.setBranchCode("381");
            l_srvRegiDealingCommParams.setAccountCode("2512246");
            l_srvRegiDealingCommParams.setOrderAccProduct("21");
            
            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_datSystemDate );
            l_cal.add(Calendar.MONTH, -1);

            Date l_dat = l_cal.getTime();
            
            l_srvRegiDealingCommParams.setAccumulateTerm(WEB3DateUtility.formatDate(l_dat, "yyyyMM"));
            l_srvRegiDealingCommParams.setCommAmt(125);
            l_srvRegiDealingCommParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvRegiDealingCommParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_srvRegiDealingCommParams);
            
            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_requestForInput.serviceDiv = "1234";
            this.l_requestForInput.applyKindDiv = "2";
            WEB3SrvRegiApplyInputResponse l_response = this.l_serviceImpl.useAppliInputRequest(this.l_requestForInput);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", 
                    "validateSubAccountForTrading", 
                    new Class[] { SubAccount.class });

            assertEquals(333812512203L,((WEB3GentradeSubAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
            assertEquals("0",l_response.lotteryDiv);
            assertEquals("SrvName",l_response.serviceName);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex); 
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常返回
     */
    public void testuseAppliInputRequest_C0003()
    {
        final String STR_METHOD_NAME = "testuseAppliInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();       
            l_srvRegiMasterParams.setInstitutionCode("1D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setInstitutionCode("1D");
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            // SrvRegiDealingCommRow 売買手数料累計テーブル
            TestDBUtility.deleteAll(SrvRegiDealingCommRow.TYPE);
            SrvRegiDealingCommParams l_srvRegiDealingCommParams = new SrvRegiDealingCommParams();
            l_srvRegiDealingCommParams.setInstitutionCode("1D");
            l_srvRegiDealingCommParams.setBranchCode("381");
            l_srvRegiDealingCommParams.setAccountCode("2512246");
            l_srvRegiDealingCommParams.setOrderAccProduct("21");

            Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
            Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_datSystemDate );
            l_cal.add(Calendar.MONTH, -1);

            Date l_dat = l_cal.getTime();

            l_srvRegiDealingCommParams.setAccumulateTerm(WEB3DateUtility.formatDate(l_dat, "yyyyMM"));
            l_srvRegiDealingCommParams.setCommAmt(125);
            l_srvRegiDealingCommParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvRegiDealingCommParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_srvRegiDealingCommParams);

            this.l_subAccountForMock = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            this.l_requestForInput.serviceDiv = "1234";
            this.l_requestForInput.applyKindDiv = "2";
            WEB3SrvRegiApplyInputResponse l_response = this.l_serviceImpl.useAppliInputRequest(this.l_requestForInput);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateSubAccountForTrading",
                    new Class[] { SubAccount.class });

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            l_mainAccountParams.setBranchId(33381L);
//            l_mainAccountParams.setInstitutionCode("1D");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            

            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();       
            l_srvRegiMasterParams.setInstitutionCode("1D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus("2");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
            
            
            // SrvRegiCommCondRow
            TestDBUtility.deleteAll(SrvRegiCommCondRow.TYPE);
            SrvRegiCommCondParams l_srvRegiCommCondParams = TestDBUtility.getSrvRegiCommCondRow();
            l_srvRegiCommCondParams.setInstitutionCode("1D");
            l_srvRegiCommCondParams.setOrderAccProduct("21");
            l_srvRegiCommCondParams.setSrvDiv("1234");
            TestDBUtility.insertWithDel(l_srvRegiCommCondParams);
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateSubAccountForTrading",
                    new Class[] {SubAccount.class},
                    l_orderValidationResult);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private class WEB3SrvRegiConsentRequestForTest extends WEB3SrvRegiConsentRequest
    {
        public void validate() throws WEB3BaseException 
        {
            log.debug("WEB3SrvRegiConsentRequestForTest.validate()");
        }
    }
    
    private class WEB3SrvRegiApplyInputRequestForTest extends WEB3SrvRegiApplyInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            log.debug("WEB3SrvRegiApplyInputRequestForTest.validate()");
        }
    }
    
    private class WEB3SrvRegiApplicationInputServiceImplForTest extends WEB3SrvRegiApplicationInputServiceImpl
    {
        public SubAccount getSubAccount(SubAccountTypeEnum l_subAccountType) throws WEB3SystemLayerException
        {
            return l_subAccountForMock;
        }
    }

}
@
