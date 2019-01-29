head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 安陽(中訊) 新規作成
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionCommonRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplTest.class);

    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl l_serviceImpl = null;

    public WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_serviceImpl = new WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        l_serviceImpl = null;
        super.tearDown();
    }

    //(execute)
    //パラメータ値不正
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = null;
            
            l_serviceImpl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(execute) 
    //パラメータタイプ不正。
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GenRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
            
            l_serviceImpl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索入力)
    //validate権限 throws
    public void testGetPaymentRequisitionCustomerSearchInput_C0001()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchInput_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);

            WEB3AdminTPPaymentRequisitionInputRequest l_request =
                new WEB3AdminTPPaymentRequisitionInputRequest();

            l_serviceImpl.execute(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索入力)
    //正常終了
    public void testGetPaymentRequisitionCustomerSearchInput_C0002()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchInput_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionInputRequest l_request =
                new WEB3AdminTPPaymentRequisitionInputRequest();
      

            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminTPPaymentRequisitionInputResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索一覧)
    //validate( ) throws
    public void testGetPaymentRequisitionCustomerSearchList_C0001()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Method l_method =
                WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "getPaymentRequisitionCustomerSearchList", 
                    new Class[]{WEB3AdminTPPaymentRequisitionListRequest.class});
            
            l_method.setAccessible(true);
            
            WEB3AdminTPPaymentRequisitionListRequest l_request =
                new WEB3AdminTPPaymentRequisitionListRequest();
      
            l_method.invoke(l_serviceImpl, new Object[]{l_request});

            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03140,
                ((WEB3BusinessLayerException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索一覧)
    //validate権限  throws
    public void testGetPaymentRequisitionCustomerSearchList_C0002()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            
            WEB3AdminTPPaymentRequisitionListRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionListRequestForTest();

            l_serviceImpl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索一覧) 
    //get入金請求顧客情報Params一覧  throws
    public void testGetPaymentRequisitionCustomerSearchList_C0003()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchList_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);
            
            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);

            WEB3AdminTPPaymentRequisitionListRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionListRequestForTest();
            l_request.pageIndex = "1";
            l_request.pageSize = "1";

            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索一覧)
    //正常終了
    public void testGetPaymentRequisitionCustomerSearchList_C0004()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchList_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {}, 
                new Timestamp(WEB3DateUtility.getDate("20080809112233", "yyyyMMddHHmmss").getTime())); 
            
            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams =
                TestDBUtility.getPaymentRequisitMngParams();
            
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setAccountId(5001);
            l_paymentRequisitMngParams.setAccountCode("1234563");
            l_paymentRequisitMngParams.setFamilyName("an");
            l_paymentRequisitMngParams.setBranchCode("995");
            l_paymentRequisitMngParams.setAccountAttribute("2");
            l_paymentRequisitMngParams.setLackAccountBalance(500);
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("2008/08/08","yyyy/MM/dd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            
            l_paymentRequisitMngParams.setAccountId(5002);
            l_paymentRequisitMngParams.setAccountCode("1234565");
            l_paymentRequisitMngParams.setFamilyName("jet");
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            l_paymentRequisitMngParams.setAccountId(5003);
            l_paymentRequisitMngParams.setAccountCode("1234562");
            l_paymentRequisitMngParams.setFamilyName("liu");
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            l_paymentRequisitMngParams.setAccountId(5004);
            l_paymentRequisitMngParams.setAccountCode("1234566");
            l_paymentRequisitMngParams.setFamilyName("li");
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            
            l_paymentRequisitMngParams.setAccountId(5005);
            l_paymentRequisitMngParams.setAccountCode("1234561");
            l_paymentRequisitMngParams.setFamilyName("wang");
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            
            l_paymentRequisitMngParams.setAccountId(5006);
            l_paymentRequisitMngParams.setAccountCode("1234564");
            l_paymentRequisitMngParams.setFamilyName("zhang");
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            
            
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionListRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionListRequestForTest();
            
            l_request.branchCode = "995";
            l_request.accountCode = "123456";
            l_request.customerAttribute = "1";
            l_request.claimReason = "2";
            l_request.days = "5";
            l_request.pageSize = "2";
            l_request.pageIndex = "4";

            WEB3AdminTPPaymentRequisitionListResponse l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3DateUtility.getDate("20080808", "yyyyMMdd"), l_response.calcDate);
            assertEquals("2", l_response.claimReason);
            assertEquals("5", l_response.days);
            assertEquals("3", l_response.totalPages);
            assertEquals("3", l_response.pageIndex);
            assertEquals("6", l_response.totalRecords);
            
            assertEquals(2, l_response.paymentRequisitionListUnit.length);
            assertEquals("jet", l_response.paymentRequisitionListUnit[0].accountName);
            assertEquals("li", l_response.paymentRequisitionListUnit[1].accountName);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ※レコードが０件の場合は「前営業日」をセットする。
     */
    public void testGetPaymentRequisitionCustomerSearchList_C0005()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchList_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {}, 
                new Timestamp(WEB3DateUtility.getDate("20080811112233", "yyyyMMddHHmmss").getTime())); 
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    WEB3DateUtility.getDate("20080811", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            
            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionListRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionListRequestForTest();
            
            l_request.branchCode = "995";
            l_request.accountCode = "123456";
            l_request.customerAttribute = "1";
            l_request.claimReason = "2";
            l_request.days = "5";
            l_request.pageSize = "2";
            l_request.pageIndex = "4";

            WEB3AdminTPPaymentRequisitionListResponse l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_serviceImpl.execute(l_request);
            
            assertEquals(new Timestamp(WEB3DateUtility.getDate("20080808", "yyyyMMdd").getTime()), l_response.calcDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索詳細)
    //validate() throws
    public void testGetPaymentRequisitionCustomerSearchDetail_C0001()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        { 
            WEB3AdminTPPaymentRequisitionDetailRequest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequest();
     
            l_serviceImpl.execute(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索詳細)
    //2 
    public void testGetPaymentRequisitionCustomerSearchDetail_C0002()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("2000/11/22","yyyy/MM/dd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("2001/11/22","yyyy/MM/dd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("2002/11/22","yyyy/MM/dd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("2003/11/22","yyyy/MM/dd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("2004/11/22","yyyy/MM/dd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("2005/11/22","yyyy/MM/dd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 500.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 501.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 502.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 503.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 504.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 505.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("0");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("2008/08/08 112233","yyyy/MM/dd HHmmss"));
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals(WEB3DateUtility.getDate("20080808", "yyyyMMdd"), l_response.calcDate);
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("0", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("1", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals(WEB3DateUtility.getDate("20001122", "yyyyMMdd"),
                l_response.shortfallGenerationInfo.closeDate0);
            assertEquals(WEB3DateUtility.getDate("20011122", "yyyyMMdd"),
                l_response.shortfallGenerationInfo.closeDate1);
            assertEquals(WEB3DateUtility.getDate("20021122", "yyyyMMdd"),
                l_response.shortfallGenerationInfo.closeDate2);
            assertEquals(WEB3DateUtility.getDate("20031122", "yyyyMMdd"),
                l_response.shortfallGenerationInfo.closeDate3);
            assertEquals(WEB3DateUtility.getDate("20041122", "yyyyMMdd"),
                l_response.shortfallGenerationInfo.closeDate4);
            assertEquals(WEB3DateUtility.getDate("20051122", "yyyyMMdd"),
                l_response.shortfallGenerationInfo.closeDate5);
            assertEquals("500", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("501", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("502", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("503", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("504", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("505", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);
            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索詳細)
    //0  all0
    public void testGetPaymentRequisitionCustomerSearchDetail_C0003()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 0.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("0");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("0", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("0", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);
            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(get入金請求顧客検索詳細)
    //0  not all0
    public void testGetPaymentRequisitionCustomerSearchDetail_C0004()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 500.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 0.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("0");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("0", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("1", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("500", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);
            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetPaymentRequisitionCustomerSearchDetail_C0005()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 500.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 501.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 502.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 503.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 504.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 505.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("1");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("1", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("1", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("500", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("501", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("502", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("503", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("504", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("505", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);
            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索詳細)
    //1  all0
    public void testGetPaymentRequisitionCustomerSearchDetail_C0006()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 0.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("1");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("1", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("0", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);
            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(get入金請求顧客検索詳細)
    //1  not all0
    public void testGetPaymentRequisitionCustomerSearchDetail_C0007()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 500.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 0.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("1");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("1", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("1", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("500", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);
            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索詳細)
    //get入金請求顧客詳細情報().不足金発生状況 = 2(信用)の場合
    //get入金請求顧客詳細情報().追証発生情報.追証情報が第一水準追証情報のインスタンスの場合
    public void testGetPaymentRequisitionCustomerSearchDetail_C0008()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 500.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 501.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 502.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 503.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 504.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 505.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;
            
            
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo =
                new WEB3TPFirstAdddepositInfo();
            l_firstAdddepositInfo.firstDepositPassDay = 55;
            l_firstAdddepositInfo.firstDepositPassDayValid = 555;
            l_firstAdddepositInfo.firstDepositOccurredDate = WEB3DateUtility.getDate("2008/10/10 223344","yyyy/MM/dd HHmmss");
            l_firstAdddepositInfo.firstMarginDepositRate = 11.23;
            l_firstAdddepositInfo.firstDepositRate = 12;
            l_firstAdddepositInfo.firstDepositAmount = 13;
            l_firstAdddepositInfo.firstSettlement = 14;
            l_firstAdddepositInfo.firstMarginDepositInDe = 15;
            l_firstAdddepositInfo.firstMarginDepositInDeExpect = 16;
            l_firstAdddepositInfo.firstSettledContract = 17;
            l_firstAdddepositInfo.firstUncancelAmt = 18;
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt = 19;

            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
            
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("2");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setAdddepositGenerationInfo(l_adddepositGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("2", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("2", l_response.shortfallGenerationStateDiv);
            assertEquals("1", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("500", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("501", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("502", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("503", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("504", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("505", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

//            assertEquals(null, l_response.firstAdditionalInfo);
            assertEquals("55", l_response.firstAdditionalInfo.firstDepositPassDay);
            assertEquals("555", l_response.firstAdditionalInfo.firstDepositPassDayValid);
            assertEquals("20081010", 
                WEB3DateUtility.formatDate(l_response.firstAdditionalInfo.firstDepositOccurredDate, "yyyyMMdd"));
            assertEquals("11.23", l_response.firstAdditionalInfo.firstMarginDepositRate);
            assertEquals("12", l_response.firstAdditionalInfo.firstDepositRate);
            assertEquals("13", l_response.firstAdditionalInfo.firstDepositAmount);
            assertEquals("14", l_response.firstAdditionalInfo.firstSettlement);
            assertEquals("15", l_response.firstAdditionalInfo.firstMarginDepositInDe);
            assertEquals("16", l_response.firstAdditionalInfo.firstMarginDepositInDeExpect);
            assertEquals("17", l_response.firstAdditionalInfo.firstSettledContract);
            assertEquals("18", l_response.firstAdditionalInfo.firstUncancelAmt);
            assertEquals("19", l_response.firstAdditionalInfo.firstUncancelSettleRequiredAmt);

            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(get入金請求顧客検索詳細)
    //get入金請求顧客詳細情報().不足金発生状況 = 2(信用)の場合
    //get入金請求顧客詳細情報().追証発生情報.追証情報が第二水準追証情報のインスタンスの場合
    public void testGetPaymentRequisitionCustomerSearchDetail_C0009()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 500.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 501.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 502.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 503.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 504.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 505.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;

            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo=
                new WEB3TPSecondAdddepositInfo();
            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("200801011010","yyyyMMddHHmm");
            l_secondAdddepositInfo.secondCloseDate1 = WEB3DateUtility.getDate("200802021010","yyyyMMddHHmm");
            l_secondAdddepositInfo.secondCloseDateExpect = WEB3DateUtility.getDate("20080303101055","yyyyMMddHHmmss");
            l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("2008/04/04 11:22:33","yyyy/MM/dd HH:mm:ss");
            l_secondAdddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.getDate("2008/05/05","yyyy/MM/dd");
            l_secondAdddepositInfo.secondDepositOccurredDateExpect = WEB3DateUtility.getDate("2008/06/06","yyyy/MM/dd");
            l_secondAdddepositInfo.secondDepositRate = 1002;
            l_secondAdddepositInfo.secondDepositBackRate = 1003;
            l_secondAdddepositInfo.secondMarginDepositRate2 = 1004.01;
            l_secondAdddepositInfo.secondMarginDepositRate1 = 1005.20;
            l_secondAdddepositInfo.secondMarginDepositRateExpect = 1006.56;
            l_secondAdddepositInfo.secondDepositNonPay = 1007;
            l_secondAdddepositInfo.secondDeposit2 = 1008;
            l_secondAdddepositInfo.secondDeposit1 = 1009;
            l_secondAdddepositInfo.secondSettlementNonPay = 1010;
            l_secondAdddepositInfo.secondSettlement2 = 1011;
            l_secondAdddepositInfo.secondSettlement1 = 1012;
            l_secondAdddepositInfo.secondMarginDepositInDe = 1013;
            l_secondAdddepositInfo.secondMarginDepositInDeExpect = 1014;
            l_secondAdddepositInfo.secondSettledContract = 1015;
            l_secondAdddepositInfo.secondUncancelAmtNonPay = 1016;
            l_secondAdddepositInfo.secondUncancelAmt2 = 1017;
            l_secondAdddepositInfo.secondUncancelAmt1 = 1018;
            l_secondAdddepositInfo.secondUncancelAmtExpect = 1019;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 1020;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 1021;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1 = 1022;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect = 1023;
            

            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("2");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setAdddepositGenerationInfo(l_adddepositGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("2", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("2", l_response.shortfallGenerationStateDiv);
            assertEquals("2", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122",
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("500", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("501", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("502", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("503", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("504", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("505", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);

//            assertEquals(null, l_response.secondAdditionalInfo); 
            assertEquals(WEB3DateUtility.getDate("2008/01/01 10:10","yyyy/MM/dd HH:mm"),
                l_response.secondAdditionalInfo.closeDate2);
            assertEquals(WEB3DateUtility.getDate("2008/02/02 10:10","yyyy/MM/dd HH:mm"),
                l_response.secondAdditionalInfo.closeDate1);
            assertEquals(WEB3DateUtility.getDate("2008/03/03 10:10","yyyy/MM/dd HH:mm"),
                l_response.secondAdditionalInfo.closeDateExpect);
            assertEquals(WEB3DateUtility.getDate("20080404","yyyyMMdd"),
                l_response.secondAdditionalInfo.secondDepositOccurredDate2);
            assertEquals(WEB3DateUtility.getDate("20080505","yyyyMMdd"), 
                l_response.secondAdditionalInfo.secondDepositOccurredDate1);
            assertEquals(WEB3DateUtility.getDate("20080606","yyyyMMdd"), 
                l_response.secondAdditionalInfo.secondDepositOccurredDateExpect);
            assertEquals("1002", l_response.secondAdditionalInfo.secondDepositRate);
            assertEquals("1003", l_response.secondAdditionalInfo.secondDepositBackRate);
            assertEquals("1004.01", l_response.secondAdditionalInfo.secondMarginDepositRate2);
            assertEquals("1005.2", l_response.secondAdditionalInfo.secondMarginDepositRate1);
            assertEquals("1006.56", l_response.secondAdditionalInfo.secondMarginDepositRateExpect);
            assertEquals("1007", l_response.secondAdditionalInfo.secondDepositNonPay);
            assertEquals("1008", l_response.secondAdditionalInfo.secondDeposit2);
            assertEquals("1009", l_response.secondAdditionalInfo.secondDeposit1);
            assertEquals("1010", l_response.secondAdditionalInfo.secondSettlementNonPay);
            assertEquals("1011", l_response.secondAdditionalInfo.secondSettlement2);
            assertEquals("1012", l_response.secondAdditionalInfo.secondSettlement1);
            assertEquals("1013", l_response.secondAdditionalInfo.secondMarginDepositInDe);
            assertEquals("1014", l_response.secondAdditionalInfo.secondMarginDepositInDeExpect);
            assertEquals("1015", l_response.secondAdditionalInfo.secondSettledContract);
            assertEquals("1016", l_response.secondAdditionalInfo.secondUncancelAmtNonPay);
            assertEquals("1017", l_response.secondAdditionalInfo.secondUncancelAmt2);
            assertEquals("1018", l_response.secondAdditionalInfo.secondUncancelAmt1);
            assertEquals("1019", l_response.secondAdditionalInfo.secondUncancelAmtExpect);
            assertEquals("1020", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay);
            assertEquals("1021", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmt2);
            assertEquals("1022", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmt1);
            assertEquals("1023", l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //get入金請求顧客詳細情報().入金請求顧客詳細情報.追証発生情報.追証情報 == null 
    public void testGetPaymentRequisitionCustomerSearchDetail_C0010()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 0.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;

            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setAdddepositInfo(null);
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("2");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setAdddepositGenerationInfo(l_adddepositGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("2", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("0", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122",
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);

            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //get入金請求顧客詳細情報().入金請求顧客詳細情報.追証発生情報 == null 
    public void testGetPaymentRequisitionCustomerSearchDetail_C0011()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
                new WEB3TPShortfallOccurInfo();
            l_shortfallGenerationInfo.debitAmount = 66.0;
            l_shortfallGenerationInfo.specialDebitAmount = 666.0;
            l_shortfallGenerationInfo.depositAutoTransferDivFlag = false;
            l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.getDate("20001122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.getDate("20011122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.getDate("20021122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.getDate("20031122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.getDate("20041122","yyyyMMdd");
            l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.getDate("20051122","yyyyMMdd");
            l_shortfallGenerationInfo.requiredPayAmt0 = 500.0;
            l_shortfallGenerationInfo.requiredPayAmt1 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt2 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt3 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt4 = 0.0;
            l_shortfallGenerationInfo.requiredPayAmt5 = 0.0;
            l_shortfallGenerationInfo.adjustedAmt0 = 100.0;
            l_shortfallGenerationInfo.adjustedAmt1 = 101.0;
            l_shortfallGenerationInfo.dayTradeRestraint0 = 200.0;
            l_shortfallGenerationInfo.dayTradeRestraint1 = 201.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit0 = 300.0;
            l_shortfallGenerationInfo.transferFromMarginDeposit1 = 301.0;

            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setAdddepositInfo(null);
            
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                new WEB3TPPaymentRequisitionAccountDetailInfo();
            l_paymentRequisitionAccountDetailInfo.setShortfallGenerationInfo(l_shortfallGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setAccountAttribute("2");
            l_paymentRequisitionAccountDetailInfo.setCalcDate(WEB3DateUtility.getDate("20080808","yyyyMMdd"));
            l_paymentRequisitionAccountDetailInfo.setAdddepositGenerationInfo(l_adddepositGenerationInfo);
            l_paymentRequisitionAccountDetailInfo.setDepositAutoTransferDivFlag(true);
            
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                "getPaymentRequisitionAccountDetailInfo",
                new Class[] {MainAccount.class}, 
                l_paymentRequisitionAccountDetailInfo);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234567890L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("995");
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setFamilyName("鮎川");
            l_mainAccountParams.setGivenName("まどか");
            l_mainAccountParams.setSonarTraderCode("506");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(1234567890L);

            l_tradingpowerCalcConditionParam.setTradingStop("3");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("4");
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("5");
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("6");
            l_tradingpowerCalcConditionParam.setPaymentStop("7");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("8");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            

            AdministratorParams l_administratorRow = this.getAdministratorRow();
            l_administratorRow.setInstitutionCode("0D");
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "A0201", false, true);

            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();
            l_request.branchCode = "995";
            l_request.accountCode = "123456";

            WEB3GenResponse l_genResponse = l_serviceImpl.execute(l_request);

            assertEquals(WEB3AdminTPPaymentRequisitionDetailResponse.class, l_genResponse.getClass());
            WEB3AdminTPPaymentRequisitionDetailResponse l_response=
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_genResponse;
            
            assertEquals("20080808", WEB3DateUtility.formatDate(l_response.calcDate, "yyyyMMdd"));
            assertEquals("995", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("鮎川", l_response.accountName);
            assertEquals("506", l_response.traderCode);
            assertEquals("2", l_response.attribute);
            assertEquals("3", l_response.tradeStopDiv);
            assertEquals("4", l_response.additionalDepositStop);
            assertEquals("5", l_response.marginOpenPositionStop);
            assertEquals("6", l_response.ifoOpenPositionStop);
            assertEquals("7", l_response.paymentStop);
            assertEquals("8", l_response.otherTradingStop);
            assertEquals("66", l_response.debitAmount);
            assertEquals("666", l_response.specialDebitAmount);
            assertEquals(true, l_response.autoTransferAfterJudgeFlag);
            assertEquals("2", l_response.shortfallGenerationStateDiv);
            assertEquals("0", l_response.additionalGenerationStateDiv);
            
            assertEquals("20001122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate0, "yyyyMMdd"));
            assertEquals("20011122",
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate1, "yyyyMMdd"));
            assertEquals("20021122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate2, "yyyyMMdd"));
            assertEquals("20031122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate3, "yyyyMMdd"));
            assertEquals("20041122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate4, "yyyyMMdd"));
            assertEquals("20051122", 
                WEB3DateUtility.formatDate(l_response.shortfallGenerationInfo.closeDate5, "yyyyMMdd"));
            assertEquals("500", l_response.shortfallGenerationInfo.requiredPayAmt0);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt1);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt2);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt3);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt4);
            assertEquals("0", l_response.shortfallGenerationInfo.requiredPayAmt5);
            assertEquals("100", l_response.shortfallGenerationInfo.adjustedAmt0);
            assertEquals("101", l_response.shortfallGenerationInfo.adjustedAmt1);
            assertEquals("200", l_response.shortfallGenerationInfo.dayTradeRestraint0);
            assertEquals("201", l_response.shortfallGenerationInfo.dayTradeRestraint1);
            assertEquals("300", l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
            assertEquals("301", l_response.shortfallGenerationInfo.transferFromMarginDeposit1);

            assertEquals(null, l_response.firstAdditionalInfo);

            assertEquals(null, l_response.secondAdditionalInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //
    public void testGetPaymentRequisitionCustomerSearchDetail_C0012()
    {
        final String STR_METHOD_NAME =
            "testGetPaymentRequisitionCustomerSearchDetail_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            
            WEB3AdminTPPaymentRequisitionDetailRequestForTest l_request =
                new WEB3AdminTPPaymentRequisitionDetailRequestForTest();

            l_serviceImpl.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3AdminTPPaymentRequisitionListRequestForTest
        extends WEB3AdminTPPaymentRequisitionListRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    
    }
    
    private class WEB3AdminTPPaymentRequisitionDetailRequestForTest
        extends WEB3AdminTPPaymentRequisitionDetailRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    
    }

    private AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();

        l_administratorParams.setAdministratorId(110120119L);
        l_administratorParams.setAdministratorCode("123456789");
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setInstitutionId(33);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setLoginId(3338111123L);
        l_administratorParams.setPermissionLevel("123");

        return l_administratorParams;
    }


        
        //正常結束
        public void testGetTradingPowerCalcConditionParams_C001()
        {
            final String STR_METHOD_NAME = "testGetTradingPowerCalcConditionParams_C001";
            log.entering(STR_METHOD_NAME);
            try
            {
                Long l_accountId = new Long(1234);
                TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
                TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                    TestDBUtility.getTradingpowerCalcConditionRow();
                l_tradingpowerCalcConditionParams.setAccountId(1234);
                TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
                Method method =
                    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "getTradingPowerCalcConditionParams",
                    new Class[]{Long.class});
                method.setAccessible(true);
                Object object[] = {l_accountId};
                TradingpowerCalcConditionParams l_return =
                    (TradingpowerCalcConditionParams)method.invoke(l_serviceImpl, object);
                assertEquals(1234, l_return.getAccountId());
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        //返回空輸組，數組長度為0
        public void testCreatePaymentRequisitionListUnit_C001()
        {
            final String STR_METHOD_NAME = "testCreatePaymentRequisitionListUnit_C001";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                PaymentRequisitMngParams[] l_paymentRequisitMngParams = new PaymentRequisitMngParams[0];
                Object object[] = {l_paymentRequisitMngParams};
                Method method =
                    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "createPaymentRequisitionListUnit",
                    new Class[]{PaymentRequisitMngParams[].class});
                method.setAccessible(true);
                WEB3AdminTPPaymentRequisitionListUnit[] l_return =
                    (WEB3AdminTPPaymentRequisitionListUnit[])method.invoke(l_serviceImpl, object);
                assertEquals(0, l_return.length);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        //返回正正確的入金請求顧客検索一覧ユニットの配列
        public void testCreatePaymentRequisitionListUnit_C002()
        {
            final String STR_METHOD_NAME = "testCreatePaymentRequisitionListUnit_C002";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                PaymentRequisitMngParams[] l_paymentRequisitMngParams = new PaymentRequisitMngParams[1];
                
                l_paymentRequisitMngParams[0] = new PaymentRequisitMngParams();
                l_paymentRequisitMngParams[0].setAccountCode("1234") ;
                l_paymentRequisitMngParams[0].setBranchCode("102") ;
                l_paymentRequisitMngParams[0].setFamilyName("zsj");
                l_paymentRequisitMngParams[0].setTraderCode("0025");
                l_paymentRequisitMngParams[0].setAccountAttribute("01");
                l_paymentRequisitMngParams[0].setDebitAmount(1000);                       
                l_paymentRequisitMngParams[0].setSpecialDebitAmount(200);    
                l_paymentRequisitMngParams[0].setLackAccountBalance(250);    
                l_paymentRequisitMngParams[0].setFirstDepositAmount(300);    
                l_paymentRequisitMngParams[0].setFirstDepositPassDay(10);    
                l_paymentRequisitMngParams[0].setSecondDeposit1(1);    
                l_paymentRequisitMngParams[0].setSecondDeposit2(2);    
                l_paymentRequisitMngParams[0].setSecondDepositNonPay(25);    
                
                Object object[] = {l_paymentRequisitMngParams};
                Method method =
                    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "createPaymentRequisitionListUnit",
                    new Class[]{PaymentRequisitMngParams[].class});
                method.setAccessible(true);
                WEB3AdminTPPaymentRequisitionListUnit[] 
                    l_return = (WEB3AdminTPPaymentRequisitionListUnit[])method.
                        invoke(l_serviceImpl, object);
                
                assertEquals(1, l_return.length);
                assertEquals("1234", l_return[0].accountCode);
                assertEquals("102", l_return[0].branchCode);
                assertEquals("zsj", l_return[0].accountName);
                assertEquals("0025", l_return[0].traderCode);
                assertEquals("01", l_return[0].attribute);
                assertEquals("1000", l_return[0].debitAmount);
                assertEquals("200", l_return[0].specialDebitAmount);
                assertEquals("250", l_return[0].requiredPayAmt);
                assertEquals("300", l_return[0].firstDepositAmount);
                assertEquals("10", l_return[0].firstDepositPassDay);
                assertEquals("1", l_return[0].secondDeposit1);
                assertEquals("2", l_return[0].secondDeposit2);
                assertEquals("25", l_return[0].secondDepositNonPay);
                
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage());
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        //返回正確的入金請求顧客検索一覧ユニットの配列
        public void testCreatePaymentRequisitionListUnit_C003()
        {
            final String STR_METHOD_NAME = "testCreatePaymentRequisitionListUnit_C003";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                PaymentRequisitMngParams[] l_paymentRequisitMngParams = new PaymentRequisitMngParams[2];
                l_paymentRequisitMngParams[0] = new PaymentRequisitMngParams();
                l_paymentRequisitMngParams[1] = new PaymentRequisitMngParams();
                
                l_paymentRequisitMngParams[0].setAccountCode("3421") ;
                l_paymentRequisitMngParams[0].setBranchCode("102") ;
                l_paymentRequisitMngParams[0].setFamilyName("zwj");
                l_paymentRequisitMngParams[0].setTraderCode("0025");
                l_paymentRequisitMngParams[0].setAccountAttribute("01");
                l_paymentRequisitMngParams[0].setDebitAmount(1000);                       
                l_paymentRequisitMngParams[0].setSpecialDebitAmount(200);    
                l_paymentRequisitMngParams[0].setLackAccountBalance(250);    
                l_paymentRequisitMngParams[0].setFirstDepositAmount(300);    
//                l_paymentRequisitMngParams[0].setFirstDepositPassDay(10);    
                l_paymentRequisitMngParams[0].setSecondDeposit1(1);    
                l_paymentRequisitMngParams[0].setSecondDeposit2(2);    
                l_paymentRequisitMngParams[0].setSecondDepositNonPay(25); 
                
                l_paymentRequisitMngParams[1].setAccountCode("1234") ;
                l_paymentRequisitMngParams[1].setBranchCode("102") ;
                l_paymentRequisitMngParams[1].setFamilyName("zsj");
                l_paymentRequisitMngParams[1].setTraderCode("0025");
                l_paymentRequisitMngParams[1].setAccountAttribute("02");
                l_paymentRequisitMngParams[1].setDebitAmount(1000);                       
                l_paymentRequisitMngParams[1].setSpecialDebitAmount(200);    
                l_paymentRequisitMngParams[1].setLackAccountBalance(250);    
                l_paymentRequisitMngParams[1].setFirstDepositAmount(300);    
                l_paymentRequisitMngParams[1].setFirstDepositPassDay(10);    
                l_paymentRequisitMngParams[1].setSecondDeposit1(1);    
                l_paymentRequisitMngParams[1].setSecondDeposit2(2);    
                l_paymentRequisitMngParams[1].setSecondDepositNonPay(25);    
                
                Object[] object = {l_paymentRequisitMngParams};
                Method method =
                    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "createPaymentRequisitionListUnit",
                    new Class[]{PaymentRequisitMngParams[].class});
                method.setAccessible(true);
                WEB3AdminTPPaymentRequisitionListUnit[] l_return =
                    (WEB3AdminTPPaymentRequisitionListUnit[])method.invoke(l_serviceImpl, object);
                
                assertEquals(2, l_return.length);
                assertEquals("3421", l_return[0].accountCode);
                assertEquals("102", l_return[0].branchCode);
                assertEquals("zwj", l_return[0].accountName);
                assertEquals("0025", l_return[0].traderCode);
                assertEquals("01", l_return[0].attribute);
                assertEquals("1000", l_return[0].debitAmount);
                assertEquals("200", l_return[0].specialDebitAmount);
                assertEquals("250", l_return[0].requiredPayAmt);
                assertEquals("300", l_return[0].firstDepositAmount);
//                assertEquals("10", l_return[0].firstDepositPassDay);
                assertNull(l_return[0].firstDepositPassDay);
                assertEquals("1", l_return[0].secondDeposit1);
                assertEquals("2", l_return[0].secondDeposit2);
                assertEquals("25", l_return[0].secondDepositNonPay);
                
                assertEquals("1234", l_return[1].accountCode);
                assertEquals("102", l_return[1].branchCode);
                assertEquals("zsj", l_return[1].accountName);
                assertEquals("0025", l_return[1].traderCode);
                assertEquals("02", l_return[1].attribute);
                assertEquals("1000", l_return[1].debitAmount);
                assertEquals("200", l_return[1].specialDebitAmount);
                assertEquals("250", l_return[1].requiredPayAmt);
                assertEquals("300", l_return[1].firstDepositAmount);
                assertEquals("10", l_return[1].firstDepositPassDay);
                assertEquals("1", l_return[1].secondDeposit1);
                assertEquals("2", l_return[1].secondDeposit2);
                assertEquals("25", l_return[1].secondDepositNonPay);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage());
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        /**
         * リクエスト.顧客コードが"null以外"の場合 かつ
         *  リクエスト.扱者コードが"null以外"の場合 かつ
         * リクエスト.顧客属性が"0"（現物）の場合　@かつ
         *  リクエスト.請求事由が"1"（立替金/特別立替金）の場合
         */
        public void testGetPaymentRequisitMngParamsList_C001()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C001";
            log.entering(STR_METHOD_NAME);
            try
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    new Timestamp(l_date.getTime()));
                
                WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
                l_request.accountCode = "10010";
                l_request.traderCode = "0025";
                l_request.customerAttribute = "0";
                l_request.claimReason = "1";
                l_request.branchCode = "102";
                TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
                PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
                l_PaymentRequisitMngParams.setDebitAmount(10); 
                l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
                TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
                
                TestDBUtility.deleteAll(AdministratorRow.TYPE);
                AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
                l_administratorParams.setLoginId(0);
                TestDBUtility.insertWithDel(l_administratorParams);
                
                LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                        "getLoginInfo",
                        new Class[] {},
                        l_loginInfoImplForMock);
                
                Object object[] = {l_request};

                Method method =
                    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "getPaymentRequisitMngParamsList",
                    new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
                method.setAccessible(true);
                List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
                PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
                for (int i = 0; i < l_return.size(); i++)
                {
                    l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                    l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
                }
                
                assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
                assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
                assertEquals("0", l_paymentRequisitMngParams[0].getAccountAttribute());
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        /**
         * リクエスト.顧客コードが"null以外"の場合 かつ
         *  リクエスト.扱者コードが"null以外"の場合 かつ
         * リクエスト.顧客属性が"1"（信用）の場合つ
         *  リクエスト.請求事由が"2"（不足金（当日））の場合の場合
         */
        public void testGetPaymentRequisitMngParamsList_C002()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C002";
            log.entering(STR_METHOD_NAME);
            try
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    new Timestamp(l_date.getTime()));
                
                WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
                l_request.accountCode = "10010";
                l_request.traderCode = "0025";
                l_request.customerAttribute = "1";
                l_request.claimReason = "2";
                l_request.branchCode = "102";
                TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
                PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
                l_PaymentRequisitMngParams.setDebitAmount(10); 
                l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
                l_PaymentRequisitMngParams.setAccountAttribute("2");
                l_PaymentRequisitMngParams.setLackAccountBalance(10);
                TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
                
                TestDBUtility.deleteAll(AdministratorRow.TYPE);
                AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
                l_administratorParams.setLoginId(0);
                TestDBUtility.insertWithDel(l_administratorParams);
                
                LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                        "getLoginInfo",
                        new Class[] {},
                        l_loginInfoImplForMock);
                
                Object object[] = {l_request};

                Method method =
                    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "getPaymentRequisitMngParamsList",
                    new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
                method.setAccessible(true);
                List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
                PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
                for (int i = 0; i < l_return.size(); i++)
                {
                    l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                    l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
                }
                
                assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
                assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
                assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
                assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        /**
         リクエスト.顧客コードが"null以外"の場合 かつ
         リクエスト.扱者コードが"null以外"の場合 かつ
         リクエスト.顧客属性が"1"（信用）の場合 
         入金請求管理テーブル.顧客属性 = "2"(信用)
         リクエスト.請求事由が"3"（第一水準追証）の場合 
         a)リクエスト.日数が"8"(第一水準指定最大日数)の場合
         */
        public void testGetPaymentRequisitMngParamsList_C003()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C003";
            log.entering(STR_METHOD_NAME);
            try
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    new Timestamp(l_date.getTime()));
                
                WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
                l_request.accountCode = null;
                l_request.traderCode = "0025";
                l_request.customerAttribute = "1";
                l_request.claimReason = "3";
                l_request.branchCode = "102";
                l_request.days = "8";
                TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
                PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
                l_PaymentRequisitMngParams.setDebitAmount(10); 
                l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
                l_PaymentRequisitMngParams.setAccountAttribute("2");
                l_PaymentRequisitMngParams.setLackAccountBalance(10);
                l_PaymentRequisitMngParams.setFirstDepositPassDay(8);
                TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
                
                PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
                l_PaymentRequisitMngParams1.setDebitAmount(10); 
                l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
                l_PaymentRequisitMngParams1.setAccountAttribute("2");
                l_PaymentRequisitMngParams1.setLackAccountBalance(10);
                l_PaymentRequisitMngParams1.setAccountCode("10011");
                l_PaymentRequisitMngParams1.setFirstDepositPassDay(9);
                l_PaymentRequisitMngParams1.setAccountId(102051);
                TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
                
                TestDBUtility.deleteAll(AdministratorRow.TYPE);
                AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
                l_administratorParams.setLoginId(0);
                TestDBUtility.insertWithDel(l_administratorParams);
                
                LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                        "getLoginInfo",
                        new Class[] {},
                        l_loginInfoImplForMock);
                
                Object object[] = {l_request};

                Method method =
                    WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                    "getPaymentRequisitMngParamsList",
                    new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
                method.setAccessible(true);
                List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
                PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
                for (int i = 0; i < l_return.size(); i++)
                {
                    l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                    l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
                }
                
                assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
                assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
                assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
                assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
                assertEquals(8, l_paymentRequisitMngParams[0].getFirstDepositPassDay(), 0);
                
                assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
                assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
                assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
                assertEquals(10, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
                assertEquals(9, l_paymentRequisitMngParams[1].getFirstDepositPassDay(), 0);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        /**
        リクエスト.顧客コードが"null以外"の場合 かつ
        リクエスト.扱者コードが"null以外"の場合 かつ
        リクエスト.顧客属性が"1"（信用）の場合 
        入金請求管理テーブル.顧客属性 = "2"(信用)
        リクエスト.請求事由が"3"（第一水準追証）の場合 
        b)リクエスト.日数が"0"(摘要日数すべて)の場合 
          入金請求管理テーブル.第一水準追証経過日数 > リクエスト.日数
        */
       public void testGetPaymentRequisitMngParamsList_C004()
       {
           final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C004";
           log.entering(STR_METHOD_NAME);
           try
           {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "3";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setLackAccountBalance(10);
               l_PaymentRequisitMngParams.setFirstDepositPassDay(8);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setLackAccountBalance(10);
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setFirstDepositPassDay(9);
               l_PaymentRequisitMngParams1.setAccountId(102051);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};

               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
               assertEquals(8, l_paymentRequisitMngParams[0].getFirstDepositPassDay(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(10, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
               assertEquals(9, l_paymentRequisitMngParams[1].getFirstDepositPassDay(), 0);
           }
           catch(Exception l_ex)
           {
               log.exiting(STR_METHOD_NAME);
               fail();
           }
           log.exiting(STR_METHOD_NAME);
       }
       
       /**
       リクエスト.顧客コードが"null以外"の場合 かつ
       リクエスト.扱者コードが"null以外"の場合 かつ
       リクエスト.顧客属性が"1"（信用）の場合 
       入金請求管理テーブル.顧客属性 = "2"(信用)
       リクエスト.請求事由が"3"（第一水準追証）の場合 
       b)リクエスト.日数が"0"(摘要日数すべて)の場合 
         入金請求管理テーブル.第一水準追証経過日数 = リクエスト.日数
       */
      public void testGetPaymentRequisitMngParamsList_C005()
      {
          final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C005";
          log.entering(STR_METHOD_NAME);
          try
          {
              TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
              Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
              TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                  "getBizDate",
                  new Class[] {},
                  new Timestamp(l_date.getTime()));
              
              WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
              l_request.accountCode = null;
              l_request.traderCode = "0025";
              l_request.customerAttribute = "1";
              l_request.claimReason = "3";
              l_request.branchCode = "102";
              l_request.days = "2";
              TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
              PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
              l_PaymentRequisitMngParams.setDebitAmount(10); 
              l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
              l_PaymentRequisitMngParams.setAccountAttribute("2");
              l_PaymentRequisitMngParams.setLackAccountBalance(10);
              l_PaymentRequisitMngParams.setFirstDepositPassDay(2);
              TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
              
              PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
              l_PaymentRequisitMngParams1.setDebitAmount(10); 
              l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
              l_PaymentRequisitMngParams1.setAccountAttribute("2");
              l_PaymentRequisitMngParams1.setLackAccountBalance(10);
              l_PaymentRequisitMngParams1.setAccountCode("10011");
              l_PaymentRequisitMngParams1.setFirstDepositPassDay(2);
              l_PaymentRequisitMngParams1.setAccountId(102051);
              TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
              
              TestDBUtility.deleteAll(AdministratorRow.TYPE);
              AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
              l_administratorParams.setLoginId(0);
              TestDBUtility.insertWithDel(l_administratorParams);
              
              LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
              TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                      "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                      "getLoginInfo",
                      new Class[] {},
                      l_loginInfoImplForMock);
              
              Object object[] = {l_request};

              Method method =
                  WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                  "getPaymentRequisitMngParamsList",
                  new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
              method.setAccessible(true);
              List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
              PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
              for (int i = 0; i < l_return.size(); i++)
              {
                  l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                  l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
              }
              
              assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
              assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
              assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
              assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
              assertEquals(2, l_paymentRequisitMngParams[0].getFirstDepositPassDay(), 0);
              
              assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
              assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
              assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
              assertEquals(10, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
              assertEquals(2, l_paymentRequisitMngParams[1].getFirstDepositPassDay(), 0);
          }
          catch(Exception l_ex)
          {
              log.exiting(STR_METHOD_NAME);
              fail();
          }
          log.exiting(STR_METHOD_NAME);
      }
      
      /**
    リクエスト.顧客コードが"null以外"の場合 かつ
    リクエスト.扱者コードが"null以外"の場合 かつ
    リクエスト.顧客属性が"1"（信用）の場合 
    入金請求管理テーブル.顧客属性 = "2"(信用)
    リクエスト.請求事由が"4"（第二水準追証）の場合 
    リクエスト.日数が"3"(第二水準指定最大日数)の場合 
    　@　@　@　@　@　@入金請求管理テーブル.第二水準追証未入金 > 0 
      */
     public void testGetPaymentRequisitMngParamsList_C006()
     {
         final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C006";
         log.entering(STR_METHOD_NAME);
         try
         {
             TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
             Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
             TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                 "getBizDate",
                 new Class[] {},
                 new Timestamp(l_date.getTime()));
             
             WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
             l_request.accountCode = null;
             l_request.traderCode = "0025";
             l_request.customerAttribute = "1";
             l_request.claimReason = "4";
             l_request.branchCode = "102";
             l_request.days = "3";
             TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
             PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
             l_PaymentRequisitMngParams.setDebitAmount(10); 
             l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
             l_PaymentRequisitMngParams.setAccountAttribute("2");
             l_PaymentRequisitMngParams.setLackAccountBalance(10);
             l_PaymentRequisitMngParams.setSecondDepositNonPay(5);
             TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
             
             PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
             l_PaymentRequisitMngParams1.setDebitAmount(10); 
             l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
             l_PaymentRequisitMngParams1.setAccountAttribute("2");
             l_PaymentRequisitMngParams1.setLackAccountBalance(10);
             l_PaymentRequisitMngParams1.setAccountCode("10011");
             l_PaymentRequisitMngParams1.setAccountId(102051);
             l_PaymentRequisitMngParams1.setSecondDepositNonPay(6);
             TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
             
             TestDBUtility.deleteAll(AdministratorRow.TYPE);
             AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
             l_administratorParams.setLoginId(0);
             TestDBUtility.insertWithDel(l_administratorParams);
             
             LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
             TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                     "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                     "getLoginInfo",
                     new Class[] {},
                     l_loginInfoImplForMock);
             
             Object object[] = {l_request};

             Method method =
                 WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                 "getPaymentRequisitMngParamsList",
                 new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
             method.setAccessible(true);
             List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
             PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
             for (int i = 0; i < l_return.size(); i++)
             {
                 l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                 l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
             }
             
             assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
             assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
             assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
             assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
             assertEquals(5, l_paymentRequisitMngParams[0].getSecondDepositNonPay(), 0);
             
             assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
             assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
             assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
             assertEquals(10, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
             assertEquals(6, l_paymentRequisitMngParams[1].getSecondDepositNonPay(), 0);
         }
         catch(Exception l_ex)
         {
             log.exiting(STR_METHOD_NAME);
             fail();
         }
         log.exiting(STR_METHOD_NAME);
     }
     
     /**
    リクエスト.顧客コードが"null以外"の場合 かつ
    リクエスト.扱者コードが"null以外"の場合 かつ
    リクエスト.顧客属性が"1"（信用）の場合 
    入金請求管理テーブル.顧客属性 = "2"(信用)
    リクエスト.請求事由が"4"（第二水準追証）の場合  
    リクエスト.日数が"2"の場合 
    　@　@　@　@　@　@入金請求管理テーブル.第二水準追証請求(2) > 0 
     */
    public void testGetPaymentRequisitMngParamsList_C007()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C007";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
            l_request.accountCode = null;
            l_request.traderCode = "0025";
            l_request.customerAttribute = "1";
            l_request.claimReason = "4";
            l_request.branchCode = "102";
            l_request.days = "2";
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setDebitAmount(10); 
            l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
            l_PaymentRequisitMngParams.setAccountAttribute("2");
            l_PaymentRequisitMngParams.setLackAccountBalance(10);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(5);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams1.setDebitAmount(10); 
            l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
            l_PaymentRequisitMngParams1.setAccountAttribute("2");
            l_PaymentRequisitMngParams1.setLackAccountBalance(10);
            l_PaymentRequisitMngParams1.setAccountCode("10011");
            l_PaymentRequisitMngParams1.setAccountId(102051);
            l_PaymentRequisitMngParams1.setSecondDepositNonPay(6);
            l_PaymentRequisitMngParams1.setSecondDeposit2(2);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
            
            Object object[] = {l_request};

            Method method =
                WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                "getPaymentRequisitMngParamsList",
                new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
            method.setAccessible(true);
            List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
            PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
            for (int i = 0; i < l_return.size(); i++)
            {
                l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
            }
            
            assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
            assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
            assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
            assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
            assertEquals(5, l_paymentRequisitMngParams[0].getSecondDepositNonPay(), 0);
            assertEquals(1, l_paymentRequisitMngParams[0].getSecondDeposit2(), 0);
            
            assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
            assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
            assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
            assertEquals(10, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
            assertEquals(2, l_paymentRequisitMngParams[1].getSecondDeposit2(), 0);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
        /**
        リクエスト.顧客コードが"null以外"の場合 かつ
        リクエスト.扱者コードが"null以外"の場合 かつ
        リクエスト.顧客属性が"1"（信用）の場合 
        入金請求管理テーブル.顧客属性 = "2"(信用)
        リクエスト.請求事由が"4"（第二水準追証）の場合  
        リクエスト.日数が"1"の場合 
    　@  入金請求管理テーブル.第二水準追証請求(1) > 0 
        */
    public void testGetPaymentRequisitMngParamsList_C008()
    {
       final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C008";
       log.entering(STR_METHOD_NAME);
       try
       {
           TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
           Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
               "getBizDate",
               new Class[] {},
               new Timestamp(l_date.getTime()));
           
           WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
           l_request.accountCode = null;
           l_request.traderCode = "0025";
           l_request.customerAttribute = "1";
           l_request.claimReason = "4";
           l_request.branchCode = "102";
           l_request.days = "1";
           TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
           PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
           l_PaymentRequisitMngParams.setDebitAmount(10); 
           l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
           l_PaymentRequisitMngParams.setAccountAttribute("2");
           l_PaymentRequisitMngParams.setLackAccountBalance(10);
           l_PaymentRequisitMngParams.setSecondDepositNonPay(5);
           l_PaymentRequisitMngParams.setSecondDeposit1(1);
           TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
           
           PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
           l_PaymentRequisitMngParams1.setDebitAmount(10); 
           l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
           l_PaymentRequisitMngParams1.setAccountAttribute("2");
           l_PaymentRequisitMngParams1.setLackAccountBalance(10);
           l_PaymentRequisitMngParams1.setAccountCode("10011");
           l_PaymentRequisitMngParams1.setAccountId(102051);
           l_PaymentRequisitMngParams1.setSecondDepositNonPay(6);
           l_PaymentRequisitMngParams1.setSecondDeposit1(2);
           TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
           
           TestDBUtility.deleteAll(AdministratorRow.TYPE);
           AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           l_administratorParams.setLoginId(0);
           TestDBUtility.insertWithDel(l_administratorParams);
           
           LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                   "getLoginInfo",
                   new Class[] {},
                   l_loginInfoImplForMock);
           
           Object object[] = {l_request};

           Method method =
               WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
               "getPaymentRequisitMngParamsList",
               new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
           method.setAccessible(true);
           List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
           PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
           for (int i = 0; i < l_return.size(); i++)
           {
               l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
               l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
           }
           
           assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
           assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
           assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
           assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
           assertEquals(5, l_paymentRequisitMngParams[0].getSecondDepositNonPay(), 0);
           assertEquals(1, l_paymentRequisitMngParams[0].getSecondDeposit1(), 0);
           
           assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
           assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
           assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
           assertEquals(10, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
           assertEquals(2, l_paymentRequisitMngParams[1].getSecondDeposit1(), 0);
       }
       catch(Exception l_ex)
       {
           log.exiting(STR_METHOD_NAME);
           fail();
       }
       log.exiting(STR_METHOD_NAME);
    }

         /**
          リクエスト.顧客コードが"null以外"の場合 かつ
          リクエスト.扱者コードが"null以外"の場合 かつ
          リクエスト.顧客属性が"1"（信用）の場合 
          入金請求管理テーブル.顧客属性 = "2"(信用)
          リクエスト.請求事由が"4"（第二水準追証）の場合  
          リクエスト.日数が"0"(摘要日数すべて)の場合 
          入金請求管理テーブル.第二水準追証未入金 > 0 
          */
        public void testGetPaymentRequisitMngParamsList_C009()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C009";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "4";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setLackAccountBalance(10);
               l_PaymentRequisitMngParams.setSecondDepositNonPay(5);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setLackAccountBalance(10);
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setSecondDepositNonPay(6);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
               assertEquals(5, l_paymentRequisitMngParams[0].getSecondDepositNonPay(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(10, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }

        /**
         リクエスト.顧客コードが"null以外"の場合 かつ
         リクエスト.扱者コードが"null以外"の場合 かつ
         リクエスト.顧客属性が"1"（信用）の場合 
         入金請求管理テーブル.顧客属性 = "2"(信用)
         リクエスト.請求事由が"4"（第二水準追証）の場合  
         リクエスト.日数が"0"(摘要日数すべて)の場合  
         入金請求管理テーブル.第二水準追証請求(2) > 0
         */
       public void testGetPaymentRequisitMngParamsList_C0010()
       {
           final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C0010";
           log.entering(STR_METHOD_NAME);
           try
           {
              TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
              Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
              TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                  "getBizDate",
                  new Class[] {},
                  new Timestamp(l_date.getTime()));
              
              WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
              l_request.accountCode = null;
              l_request.traderCode = "0025";
              l_request.customerAttribute = "1";
              l_request.claimReason = "4";
              l_request.branchCode = "102";
              l_request.days = "0";
              TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
              PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
              l_PaymentRequisitMngParams.setDebitAmount(10); 
              l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
              l_PaymentRequisitMngParams.setAccountAttribute("2");
              l_PaymentRequisitMngParams.setLackAccountBalance(10);
              l_PaymentRequisitMngParams.setSecondDeposit2(5);
              TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
              
              PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
              l_PaymentRequisitMngParams1.setDebitAmount(10); 
              l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
              l_PaymentRequisitMngParams1.setAccountAttribute("2");
              l_PaymentRequisitMngParams1.setLackAccountBalance(10);
              l_PaymentRequisitMngParams1.setAccountCode("10011");
              l_PaymentRequisitMngParams1.setAccountId(102051);
              l_PaymentRequisitMngParams1.setSecondDeposit2(6);
              TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
              
              TestDBUtility.deleteAll(AdministratorRow.TYPE);
              AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
              l_administratorParams.setLoginId(0);
              TestDBUtility.insertWithDel(l_administratorParams);
              
              LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
              TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                      "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                      "getLoginInfo",
                      new Class[] {},
                      l_loginInfoImplForMock);
              
              Object object[] = {l_request};
           
              Method method =
                  WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                  "getPaymentRequisitMngParamsList",
                  new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
              method.setAccessible(true);
              List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
              PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
              for (int i = 0; i < l_return.size(); i++)
              {
                  l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                  l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
              }
              
              assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
              assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
              assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
              assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
              assertEquals(5, l_paymentRequisitMngParams[0].getSecondDeposit2(), 0);
              
              assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
              assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
              assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
              assertEquals(6, l_paymentRequisitMngParams[1].getSecondDeposit2(), 0);
           }
           catch(Exception l_ex)
           {
              log.exiting(STR_METHOD_NAME);
              fail();
           }
           log.exiting(STR_METHOD_NAME);
       }
       
       /**
       リクエスト.顧客コードが"null以外"の場合 かつ
       リクエスト.扱者コードが"null以外"の場合 かつ
       リクエスト.顧客属性が"1"（信用）の場合 
       入金請求管理テーブル.顧客属性 = "2"(信用)
       リクエスト.請求事由が"4"（第二水準追証）の場合  
       リクエスト.日数が"0"(摘要日数すべて)の場合 
       入金請求管理テーブル.第二水準追証請求(1) > 0 
       */
     public void testGetPaymentRequisitMngParamsList_C0011()
     {
         final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C0011";
         log.entering(STR_METHOD_NAME);
         try
         {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
            l_request.accountCode = null;
            l_request.traderCode = "0025";
            l_request.customerAttribute = "1";
            l_request.claimReason = "4";
            l_request.branchCode = "102";
            l_request.days = "0";
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setDebitAmount(10); 
            l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
            l_PaymentRequisitMngParams.setAccountAttribute("2");
            l_PaymentRequisitMngParams.setLackAccountBalance(10);
            l_PaymentRequisitMngParams.setSecondDeposit1(5);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams1.setDebitAmount(10); 
            l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
            l_PaymentRequisitMngParams1.setAccountAttribute("2");
            l_PaymentRequisitMngParams1.setLackAccountBalance(10);
            l_PaymentRequisitMngParams1.setAccountCode("10011");
            l_PaymentRequisitMngParams1.setAccountId(102051);
            l_PaymentRequisitMngParams1.setSecondDeposit1(6);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
            
            Object object[] = {l_request};
         
            Method method =
                WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                "getPaymentRequisitMngParamsList",
                new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
            method.setAccessible(true);
            List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
            PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
            for (int i = 0; i < l_return.size(); i++)
            {
                l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
            }
            
            assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
            assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
            assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
            assertEquals(10, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
            assertEquals(5, l_paymentRequisitMngParams[0].getSecondDeposit1(), 0);
            
            assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
            assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
            assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
            assertEquals(6, l_paymentRequisitMngParams[1].getSecondDeposit1(), 0);
         }
         catch(Exception l_ex)
         {
            log.exiting(STR_METHOD_NAME);
            fail();
         }
         log.exiting(STR_METHOD_NAME);
     }
        //リクエスト.顧客コードが"null以外"の場合 かつ
        //リクエスト.扱者コードが"null以外"の場合 かつ
        //リクエスト.顧客属性が"1"（信用）の場合 
        //入金請求管理テーブル.顧客属性 = "2"(信用)
        //リクエスト.請求事由"5"（指定なし）が選択された場合  
        //入金請求管理テーブル.立替金 > 0  
        public void testGetPaymentRequisitMngParamsList_C0012()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C0012";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setDebitAmount(20);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setDebitAmount(21);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(20, l_paymentRequisitMngParams[0].getDebitAmount(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(21, l_paymentRequisitMngParams[1].getDebitAmount(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
//        リクエスト.顧客コードが"null以外"の場合 かつ
//        リクエスト.扱者コードが"null以外"の場合 かつ
//        リクエスト.顧客属性が"1"（信用）の場合 
//        入金請求管理テーブル.顧客属性 = "2"(信用)
//        リクエスト.請求事由"5"（指定なし）が選択された場合  
//        入金請求管理テーブル.特別立替金 > 0  
        public void testGetPaymentRequisitMngParamsList_C013()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C013";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setSpecialDebitAmount(20);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setSpecialDebitAmount(21);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(20, l_paymentRequisitMngParams[0].getSpecialDebitAmount(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(21, l_paymentRequisitMngParams[1].getSpecialDebitAmount(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
//        リクエスト.顧客コードが"null以外"の場合 かつ
//        リクエスト.扱者コードが"null以外"の場合 かつ
//        リクエスト.顧客属性が"1"（信用）の場合 
//        入金請求管理テーブル.顧客属性 = "2"(信用)
//        リクエスト.請求事由"5"（指定なし）が選択された場合  
//        入金請求管理テーブル.預り金不足額(T+0) > 0 
        
        public void testGetPaymentRequisitMngParamsList_C014()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C014";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setLackAccountBalance(20);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setLackAccountBalance(21);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(20, l_paymentRequisitMngParams[0].getLackAccountBalance(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(21, l_paymentRequisitMngParams[1].getLackAccountBalance(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        
//        リクエスト.顧客コードが"null以外"の場合 かつ
//        リクエスト.扱者コードが"null以外"の場合 かつ
//        リクエスト.顧客属性が"1"（信用）の場合 
//        入金請求管理テーブル.顧客属性 = "2"(信用)
//        リクエスト.請求事由"5"（指定なし）が選択された場合  
//        　@入金請求管理テーブル.第一水準追証経過日数 > 0
        public void testGetPaymentRequisitMngParamsList_C015()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C015";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setFirstDepositPassDay(20);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setFirstDepositPassDay(21);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(20, l_paymentRequisitMngParams[0].getFirstDepositPassDay(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(21, l_paymentRequisitMngParams[1].getFirstDepositPassDay(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        
//        リクエスト.顧客コードが"null以外"の場合 かつ
//        リクエスト.扱者コードが"null以外"の場合 かつ
//        リクエスト.顧客属性が"1"（信用）の場合 
//        入金請求管理テーブル.顧客属性 = "2"(信用)
//        リクエスト.請求事由"5"（指定なし）が選択された場合  
//        入金請求管理テーブル.第二水準追証未入金 > 0 
        public void testGetPaymentRequisitMngParamsList_C016()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C016";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setSecondDepositNonPay(20);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setSecondDepositNonPay(21);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(20, l_paymentRequisitMngParams[0].getSecondDepositNonPay(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(21, l_paymentRequisitMngParams[1].getSecondDepositNonPay(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
//        リクエスト.顧客コードが"null以外"の場合 かつ
//        リクエスト.扱者コードが"null以外"の場合 かつ
//        リクエスト.顧客属性が"1"（信用）の場合 
//        入金請求管理テーブル.顧客属性 = "2"(信用)
//        リクエスト.請求事由"5"（指定なし）が選択された場合  
//        入金請求管理テーブル.第二水準追証請求(2) > 0 
        public void testGetPaymentRequisitMngParamsList_C017()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C017";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setSecondDeposit2(20);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setSecondDeposit2(21);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(20, l_paymentRequisitMngParams[0].getSecondDeposit2(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(21, l_paymentRequisitMngParams[1].getSecondDeposit2(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        
//        リクエスト.顧客コードが"null以外"の場合 かつ
//        リクエスト.扱者コードが"null以外"の場合 かつ
//        リクエスト.顧客属性が"1"（信用）の場合 
//        入金請求管理テーブル.顧客属性 = "2"(信用)
//        リクエスト.請求事由"5"（指定なし）が選択された場合  
//        入金請求管理テーブル.第二水準追証請求(1) > 0 
        public void testGetPaymentRequisitMngParamsList_C018()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C018";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams.setDebitAmount(10); 
               l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams.setAccountAttribute("2");
               l_PaymentRequisitMngParams.setSecondDeposit1(20);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
               
               PaymentRequisitMngParams l_PaymentRequisitMngParams1 = TestDBUtility.getPaymentRequisitMngParams();
               l_PaymentRequisitMngParams1.setDebitAmount(10); 
               l_PaymentRequisitMngParams1.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
               l_PaymentRequisitMngParams1.setAccountAttribute("2");
               l_PaymentRequisitMngParams1.setAccountCode("10011");
               l_PaymentRequisitMngParams1.setAccountId(102051);
               l_PaymentRequisitMngParams1.setSecondDeposit1(21);
               TestDBUtility.insertWithDel(l_PaymentRequisitMngParams1);
               
               TestDBUtility.deleteAll(AdministratorRow.TYPE);
               AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
               l_administratorParams.setLoginId(0);
               TestDBUtility.insertWithDel(l_administratorParams);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);
               PaymentRequisitMngParams l_paymentRequisitMngParams[] = new PaymentRequisitMngParams[l_return.size()];
               for (int i = 0; i < l_return.size(); i++)
               {
                   l_paymentRequisitMngParams[i] = new PaymentRequisitMngParams();
                   l_paymentRequisitMngParams[i] = (PaymentRequisitMngParams)l_return.get(i);
               }
               
               assertEquals("10010", l_paymentRequisitMngParams[0].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[0].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[0].getAccountAttribute());
               assertEquals(20, l_paymentRequisitMngParams[0].getSecondDeposit1(), 0);
               
               assertEquals("10011", l_paymentRequisitMngParams[1].getAccountCode());
               assertEquals("0025", l_paymentRequisitMngParams[1].getTraderCode());
               assertEquals("2", l_paymentRequisitMngParams[1].getAccountAttribute());
               assertEquals(21, l_paymentRequisitMngParams[1].getSecondDeposit1(), 0);
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        //檢索不到数据
        public void testGetPaymentRequisitMngParamsList_C019()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitMngParamsList_C019";
            log.entering(STR_METHOD_NAME);
            try
            {
               TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
               Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                   "getBizDate",
                   new Class[] {},
                   new Timestamp(l_date.getTime()));
               
               WEB3AdminTPPaymentRequisitionCommonRequest l_request = new WEB3AdminTPPaymentRequisitionCommonRequest();
               l_request.accountCode = null;
               l_request.traderCode = "0025";
               l_request.customerAttribute = "1";
               l_request.claimReason = "5";
               l_request.branchCode = "102";
               l_request.days = "0";
               TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
               
               LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
               TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                       "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                       "getLoginInfo",
                       new Class[] {},
                       l_loginInfoImplForMock);
               
               Object object[] = {l_request};
            
               Method method =
                   WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                   "getPaymentRequisitMngParamsList",
                   new Class[]{WEB3AdminTPPaymentRequisitionCommonRequest.class});
               method.setAccessible(true);
               List l_return = (ArrayList)method.invoke(l_serviceImpl, object);

               assertEquals(0, l_return.size());
            }
            catch(Exception l_ex)
            {
               log.exiting(STR_METHOD_NAME);
               fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        //valiate()方法@抛出異常
        public void testGetPaymentRequisitionCustomerSearchDownLoad_C001()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDownLoad_C001";
            log.entering(STR_METHOD_NAME);
            
            try
            {
                WEB3AdminTPPaymentRequisitionDownLoadRequest l_request =
                    new WEB3AdminTPPaymentRequisitionDownLoadRequest();
                l_request.customerAttribute = null;
                Object object[] = {l_request};
                Method method = WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                "getPaymentRequisitionCustomerSearchDownLoad",
                new Class[]{WEB3AdminTPPaymentRequisitionDownLoadRequest.class});
                method.setAccessible(true);
                method.invoke(l_serviceImpl, object);
                fail();
            }
            catch(InvocationTargetException l_ex)
            {
                log.error("", l_ex);
                log.exiting(STR_METHOD_NAME);
                assertEquals(WEB3BusinessLayerException.class,
                    l_ex.getTargetException().getClass());
                WEB3BusinessLayerException l_targetException =
                    (WEB3BusinessLayerException)l_ex.getTargetException();
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03140,
                    l_targetException.getErrorInfo());
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            log.exiting(STR_METHOD_NAME);
        }
        
        //valiate權限()方法@抛出?常
        public void testGetPaymentRequisitionCustomerSearchDownLoad_C002()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDownLoad_C001";
            log.entering(STR_METHOD_NAME);
            try
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminTPPaymentRequisitionDownLoadRequest l_request =
                    new WEB3AdminTPPaymentRequisitionDownLoadRequest();
                l_request.customerAttribute = "0";
                l_request.claimReason = "3";
                l_request.days = "4";
                l_request.branchCode = "102";
                l_request.accountCode = "100100";
                l_request.traderCode = "00250";
                
                TestDBUtility.deleteAll(AdministratorRow.TYPE);
                AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
                l_administratorParams.setLoginId(0);
                TestDBUtility.insertWithDel(l_administratorParams);
                TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
                
                LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
                
                Object object[] = {l_request};
                Method method = WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                "getPaymentRequisitionCustomerSearchDownLoad",
                new Class[]{WEB3AdminTPPaymentRequisitionDownLoadRequest.class});
                method.setAccessible(true);
                method.invoke(l_serviceImpl, object);
                fail();
            }
            catch(InvocationTargetException l_ex)
            {
                log.error("", l_ex);
                log.exiting(STR_METHOD_NAME);
                assertEquals(WEB3BusinessLayerException.class,
                    l_ex.getTargetException().getClass());
                WEB3BusinessLayerException l_targetException =
                    (WEB3BusinessLayerException)l_ex.getTargetException();
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056,
                    l_targetException.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }
        
        
        //正常結束
        public void testGetPaymentRequisitionCustomerSearchDownLoad_C003()
        {
            final String STR_METHOD_NAME = "testGetPaymentRequisitionCustomerSearchDownLoad_C003";
            log.entering(STR_METHOD_NAME);
            try
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                Date l_date = WEB3DateUtility.getDate("2004071600000", "yyyyMMddHHmmss");
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    new Timestamp(l_date.getTime()));
                
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                WEB3AdminTPPaymentRequisitionDownLoadRequest l_request =
                    new WEB3AdminTPPaymentRequisitionDownLoadRequest();
                l_request.customerAttribute = "0";
                l_request.claimReason = "1";
                l_request.branchCode = "102";
                l_request.accountCode = "100100";
                l_request.traderCode = "00250";
                l_request.days = "0";
                
                TestDBUtility.deleteAll(AdministratorRow.TYPE);
                AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
                l_administratorParams.setLoginId(0);
                TestDBUtility.insertWithDel(l_administratorParams);
                TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
                
                TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
                AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
                l_adminPermissionRow.setInstitutionCode("0D");
                l_adminPermissionRow.setTransactionCategory("A0201");
                l_adminPermissionRow.setPermissionLevel("331");
                TestDBUtility.insertWithDel(l_adminPermissionRow);
                
                TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
                PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
                l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040715", "yyyyMMdd"));
                l_PaymentRequisitMngParams.setAccountCode("100100");
                l_PaymentRequisitMngParams.setTraderCode("00250");
                l_PaymentRequisitMngParams.setBranchCode("102");
                l_PaymentRequisitMngParams.setDebitAmount(5);
                l_PaymentRequisitMngParams.setAccountAttribute("0");
                l_PaymentRequisitMngParams.setSpecialDebitAmount(10);
                l_PaymentRequisitMngParams.setLackAccountBalance(2);
//                l_PaymentRequisitMngParams.setFirstDepositPassDay(3);
                l_PaymentRequisitMngParams.setFirstDepositPassDay(null);
                l_PaymentRequisitMngParams.setSecondDeposit1(4);
                l_PaymentRequisitMngParams.setSecondDeposit2(6);
                l_PaymentRequisitMngParams.setSecondDepositNonPay(7);
                l_PaymentRequisitMngParams.setFirstDepositAmount(8);
                TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
                
                String[] l_strCsvFileLines = new String[3];
                
                l_strCsvFileLines[0] = " ";
                l_strCsvFileLines[1] =
                    "部店コード,顧客コード,顧客名,扱者コード,属性,立替金,特別立替金,必要入金額,30％割れ追証金額," +
                    "30％割れ追証経過日数,20％割れ追証請求（1）,20％割れ追証請求（2）,20％割れ追証未入金";
                l_strCsvFileLines[2] = "102,100100,jack,00250,現物（前金制）,5,10,2,8,,4,6,7";
                                  
                LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
                
                Object object[] = {l_request};
                Method method = WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl.class.getDeclaredMethod(
                "getPaymentRequisitionCustomerSearchDownLoad",
                new Class[]{WEB3AdminTPPaymentRequisitionDownLoadRequest.class});
                method.setAccessible(true);
                WEB3AdminTPPaymentRequisitionDownLoadResponse l_response = 
                    (WEB3AdminTPPaymentRequisitionDownLoadResponse)method.invoke(l_serviceImpl, object);
                assertEquals(l_response.currentDate, GtlUtils.getTradingSystem().getSystemTimestamp());
                assertEquals(l_strCsvFileLines[0], l_response.downloadFile[0]);
                assertEquals(l_strCsvFileLines[1], l_response.downloadFile[1]);
                assertEquals(l_strCsvFileLines[2], l_response.downloadFile[2]);
            }
            catch (Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }

            log.exiting(STR_METHOD_NAME);
        }
    
}
@
