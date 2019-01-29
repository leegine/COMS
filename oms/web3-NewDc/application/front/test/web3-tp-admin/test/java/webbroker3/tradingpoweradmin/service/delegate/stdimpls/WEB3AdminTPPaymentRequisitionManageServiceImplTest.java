head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionManageServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityParams;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityRow;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginRow;
//import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageDetailUnit;
//import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchRequest;
//import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


public class WEB3AdminTPPaymentRequisitionManageServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionManageServiceImplTest.class);
    
    public WEB3AdminTPPaymentRequisitionManageServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /**
//     * get入金請求管理顧客一覧<現物顧客>
//     */
//    public void test_getPaymentRequisitionManageAccountListEquity_0001()
//    {
//        final String STR_METHOD_NAME = "test_getPaymentRequisitionManageAccountListEquity_0001()";
//        log.entering(STR_METHOD_NAME);
//        PaymentRequisitionEquityParams l_requisitionParams = new PaymentRequisitionEquityParams();
//        l_requisitionParams.setDayTradeRestraint5(428128D);
//        l_requisitionParams.setAccountId(1234567891234567L);
//        l_requisitionParams.setTradingStop("1");
//        l_requisitionParams.setIfoOpenPositionStop("1");
//        l_requisitionParams.setPaymentStop("2");
//        l_requisitionParams.setOtherTradingStop("2");
//        WEB3AdminTPPaymentRequisitionManageServiceImpl l_service = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
//        try
//        {
//            WEB3AdminTPPaymentRequisitionManageUnit l_unit =
//                l_service.getPaymentRequisitionManageAccountListEquity(l_requisitionParams);
//            
//            WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails = l_unit.manageDetails;
//            
//            assertEquals("428128", manageDetails[5].dayTradeRestraint + "");
//        }
//        catch (Exception e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * get入金請求管理顧客一覧<信用顧客>
//     */
//    public void test_getPaymentRequisitionManageAccountListMargin_0001()
//    {
//        final String STR_METHOD_NAME = "test_getPaymentRequisitionManageAccountListMargin_0001()";
//        log.entering(STR_METHOD_NAME);
//        PaymentRequisitionMarginParams l_requisitionParams = new PaymentRequisitionMarginParams();
//        l_requisitionParams.setDayTradeRestraint5(428128D);
//        l_requisitionParams.setAccountId(1234567891234567L);
//        l_requisitionParams.setTradingStop("1");
//        l_requisitionParams.setIfoOpenPositionStop("1");
//        l_requisitionParams.setPaymentStop("2");
//        l_requisitionParams.setOtherTradingStop("2");
//        l_requisitionParams.setMarginOpenPositionStop("12");
//        WEB3AdminTPPaymentRequisitionManageServiceImpl l_service = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
//        try
//        {
//            WEB3AdminTPPaymentRequisitionManageUnit l_unit =
//                l_service.getPaymentRequisitionManageAccountListMargin(l_requisitionParams);
//            
//            WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails = l_unit.manageDetails;
//            
//            assertEquals("428128", manageDetails[5].dayTradeRestraint + "");
//        }
//        catch (Exception e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * get入金請求管理顧客履歴<現物顧客>
//     */
//    public void test_getPaymentRequisitionManageAccountHistoryEquity_0001()
//    {
//        final String STR_METHOD_NAME = "test_getPaymentRequisitionManageAccountHistoryEquity_0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        PaymentRequisitionEquityParams l_requisitionParams = new PaymentRequisitionEquityParams();
//        l_requisitionParams.setDayTradeRestraint5(428128D);
//        l_requisitionParams.setAccountId(1234567891234567L);
//        l_requisitionParams.setTradingStop("1");
//        l_requisitionParams.setIfoOpenPositionStop("1");
//        l_requisitionParams.setPaymentStop("2");
//        l_requisitionParams.setOtherTradingStop("2");
//        WEB3AdminTPPaymentRequisitionManageServiceImpl l_service = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
//        try
//        {
//            WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit =
//                l_service.getPaymentRequisitionManageAccountHistoryEquity(l_requisitionParams);
//            
//            WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails = l_unit.manageDetails;
//            
//            assertEquals("428128", manageDetails[5].dayTradeRestraint + "");
//        }
//        catch (Exception e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * get入金請求管理顧客履歴<信用顧客>
//     */
//    public void test_getPaymentRequisitionManageAccountHistoryMargin_0001()
//    {
//        final String STR_METHOD_NAME = "test_getPaymentRequisitionManageAccountHistoryMargin_0001()";
//        log.entering(STR_METHOD_NAME);
//        PaymentRequisitionMarginParams l_requisitionParams = new PaymentRequisitionMarginParams();
//        l_requisitionParams.setDayTradeRestraint5(428128D);
//        l_requisitionParams.setAccountId(1234567891234567L);
//        l_requisitionParams.setTradingStop("1");
//        l_requisitionParams.setIfoOpenPositionStop("1");
//        l_requisitionParams.setPaymentStop("2");
//        l_requisitionParams.setOtherTradingStop("2");
//        l_requisitionParams.setMarginOpenPositionStop("12");
//        WEB3AdminTPPaymentRequisitionManageServiceImpl l_service = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
//        try
//        {
//            WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit =
//                l_service.getPaymentRequisitionManageAccountHistoryMargin(l_requisitionParams);
//            
//            WEB3AdminTPPaymentRequisitionManageDetailUnit[] manageDetails = l_unit.manageDetails;
//            
//            assertEquals("428128", manageDetails[5].dayTradeRestraint + "");
//        }
//        catch (Exception e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    /*
     * 部店コード != null 顧客コード != null 扱者コード != null
     * ページ内表示行数 <= 0 || 要求ページ番号 < 0
     */
    public void testGetPaymentRequisitionManageParamsListEquity_C0001()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListEquity_C0001()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "0";
            l_request.pageSize = "0";
            l_request.institutionCode = "0D";
            l_request.branchCode = "31";
            l_request.accountCode = "32";
            l_request.traderCode = "123";
            
            TestDBUtility.deleteAll(PaymentRequisitionEquityRow.TYPE);
            PaymentRequisitionEquityParams l_paymentRequisitionEquityParams = new PaymentRequisitionEquityParams();
            l_paymentRequisitionEquityParams.setCalcResultEquityId(111111);
            l_paymentRequisitionEquityParams.setAccountId(222222);
            l_paymentRequisitionEquityParams.setFamilyName("beijing");
            l_paymentRequisitionEquityParams.setAccountAttribute("1");
            l_paymentRequisitionEquityParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionEquityParams.setTradingStop("0");
            l_paymentRequisitionEquityParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionEquityParams.setPaymentStop("0");
            l_paymentRequisitionEquityParams.setOtherTradingStop("0");
            l_paymentRequisitionEquityParams.setDayTradeRestraint0(11);
            l_paymentRequisitionEquityParams.setDayTradeRestraint1(12);
            l_paymentRequisitionEquityParams.setDayTradeRestraint2(13);
            l_paymentRequisitionEquityParams.setDayTradeRestraint3(14);
            l_paymentRequisitionEquityParams.setDayTradeRestraint4(15);
            l_paymentRequisitionEquityParams.setOtherRestraint0(21);
            l_paymentRequisitionEquityParams.setOtherRestraint1(22);
            l_paymentRequisitionEquityParams.setOtherRestraint2(23);
            l_paymentRequisitionEquityParams.setOtherRestraint3(24);
            l_paymentRequisitionEquityParams.setOtherRestraint4(25);
            l_paymentRequisitionEquityParams.setOtherRestraint5(26);
            l_paymentRequisitionEquityParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionEquityParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionEquityParams.setInstitutionCode("0D");
            l_paymentRequisitionEquityParams.setBranchCode("31");
            l_paymentRequisitionEquityParams.setAccountCode("32");
            l_paymentRequisitionEquityParams.setSonarTraderCode("123");
            TestDBUtility.insertWithDel(l_paymentRequisitionEquityParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListEquity(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 部店コード == null 顧客コード == null 扱者コード == null
     * (ページ内表示行数 <= 0 || 要求ページ番号 < 0)以外的場合
     */
    public void testGetPaymentRequisitionManageParamsListEquity_C0002()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListEquity_C0002()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.institutionCode = "0D";
            
            TestDBUtility.deleteAll(PaymentRequisitionEquityRow.TYPE);
            PaymentRequisitionEquityParams l_paymentRequisitionEquityParams = new PaymentRequisitionEquityParams();
            l_paymentRequisitionEquityParams.setCalcResultEquityId(111111);
            l_paymentRequisitionEquityParams.setAccountId(222222);
            l_paymentRequisitionEquityParams.setFamilyName("beijing");
            l_paymentRequisitionEquityParams.setAccountAttribute("1");
            l_paymentRequisitionEquityParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionEquityParams.setTradingStop("0");
            l_paymentRequisitionEquityParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionEquityParams.setPaymentStop("0");
            l_paymentRequisitionEquityParams.setOtherTradingStop("0");
            l_paymentRequisitionEquityParams.setDayTradeRestraint0(11);
            l_paymentRequisitionEquityParams.setDayTradeRestraint1(12);
            l_paymentRequisitionEquityParams.setDayTradeRestraint2(13);
            l_paymentRequisitionEquityParams.setDayTradeRestraint3(14);
            l_paymentRequisitionEquityParams.setDayTradeRestraint4(15);
            l_paymentRequisitionEquityParams.setOtherRestraint0(21);
            l_paymentRequisitionEquityParams.setOtherRestraint1(22);
            l_paymentRequisitionEquityParams.setOtherRestraint2(23);
            l_paymentRequisitionEquityParams.setOtherRestraint3(24);
            l_paymentRequisitionEquityParams.setOtherRestraint4(25);
            l_paymentRequisitionEquityParams.setOtherRestraint5(26);
            l_paymentRequisitionEquityParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionEquityParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionEquityParams.setInstitutionCode("0D");
            l_paymentRequisitionEquityParams.setCashBalance0(-1);
            TestDBUtility.insertWithDel(l_paymentRequisitionEquityParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListEquity(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 部店コード != null 顧客コード != null 扱者コード != null
     * ページ内表示行数 <= 0 || 要求ページ番号 < 0
     * リクエスト.摘要 = 3
     */
    public void testGetPaymentRequisitionManageParamsListMargin_C0001()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListMargin_C0001()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "0";
            l_request.pageSize = "0";
            l_request.institutionCode = "0D";
            l_request.branchCode = "31";
            l_request.accountCode = "32";
            l_request.traderCode = "123";
            l_request.summary = "3";
            
            TestDBUtility.deleteAll(PaymentRequisitionMarginRow.TYPE);
            PaymentRequisitionMarginParams l_paymentRequisitionMarginParams = new PaymentRequisitionMarginParams();
            l_paymentRequisitionMarginParams.setCalcResultMarginId(111111);
            l_paymentRequisitionMarginParams.setAccountId(222222);
            l_paymentRequisitionMarginParams.setFamilyName("beijing");
            l_paymentRequisitionMarginParams.setAccountAttribute("1");
            l_paymentRequisitionMarginParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setTradingStop("0");
            l_paymentRequisitionMarginParams.setMarginOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setPaymentStop("0");
            l_paymentRequisitionMarginParams.setOtherTradingStop("0");
            l_paymentRequisitionMarginParams.setDayTradeRestraint0(11);
            l_paymentRequisitionMarginParams.setDayTradeRestraint1(12);
            l_paymentRequisitionMarginParams.setDayTradeRestraint2(13);
            l_paymentRequisitionMarginParams.setDayTradeRestraint3(14);
            l_paymentRequisitionMarginParams.setDayTradeRestraint4(15);
            l_paymentRequisitionMarginParams.setOtherRestraint0(21);
            l_paymentRequisitionMarginParams.setOtherRestraint1(22);
            l_paymentRequisitionMarginParams.setOtherRestraint2(23);
            l_paymentRequisitionMarginParams.setOtherRestraint3(24);
            l_paymentRequisitionMarginParams.setOtherRestraint4(25);
            l_paymentRequisitionMarginParams.setOtherRestraint5(26);
            l_paymentRequisitionMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setInstitutionCode("0D");
            l_paymentRequisitionMarginParams.setBranchCode("31");
            l_paymentRequisitionMarginParams.setAccountCode("32");
            l_paymentRequisitionMarginParams.setSonarTraderCode("123");
            TestDBUtility.insertWithDel(l_paymentRequisitionMarginParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 部店コード != null 顧客コード != null 扱者コード != null
     * ページ内表示行数 <= 0 || 要求ページ番号 < 0
     * リクエスト.摘要 = 1
     * リクエスト.摘要日数 = 3
     */
    public void testGetPaymentRequisitionManageParamsListMargin_C0002()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListMargin_C0002()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "0";
            l_request.pageSize = "0";
            l_request.institutionCode = "0D";
            l_request.branchCode = "31";
            l_request.accountCode = "32";
            l_request.traderCode = "123";
            l_request.summary = "1";
            l_request.summaryDays = "3";
            
            TestDBUtility.deleteAll(PaymentRequisitionMarginRow.TYPE);
            PaymentRequisitionMarginParams l_paymentRequisitionMarginParams = new PaymentRequisitionMarginParams();
            l_paymentRequisitionMarginParams.setCalcResultMarginId(111111);
            l_paymentRequisitionMarginParams.setAccountId(222222);
            l_paymentRequisitionMarginParams.setFamilyName("beijing");
            l_paymentRequisitionMarginParams.setAccountAttribute("1");
            l_paymentRequisitionMarginParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setTradingStop("0");
            l_paymentRequisitionMarginParams.setMarginOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setPaymentStop("0");
            l_paymentRequisitionMarginParams.setOtherTradingStop("0");
            l_paymentRequisitionMarginParams.setDayTradeRestraint0(11);
            l_paymentRequisitionMarginParams.setDayTradeRestraint1(12);
            l_paymentRequisitionMarginParams.setDayTradeRestraint2(13);
            l_paymentRequisitionMarginParams.setDayTradeRestraint3(14);
            l_paymentRequisitionMarginParams.setDayTradeRestraint4(15);
            l_paymentRequisitionMarginParams.setOtherRestraint0(21);
            l_paymentRequisitionMarginParams.setOtherRestraint1(22);
            l_paymentRequisitionMarginParams.setOtherRestraint2(23);
            l_paymentRequisitionMarginParams.setOtherRestraint3(24);
            l_paymentRequisitionMarginParams.setOtherRestraint4(25);
            l_paymentRequisitionMarginParams.setOtherRestraint5(26);
            l_paymentRequisitionMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setInstitutionCode("0D");
            l_paymentRequisitionMarginParams.setBranchCode("31");
            l_paymentRequisitionMarginParams.setAccountCode("32");
            l_paymentRequisitionMarginParams.setSonarTraderCode("123");
            l_paymentRequisitionMarginParams.setBreak20elapsedDays("3");
            TestDBUtility.insertWithDel(l_paymentRequisitionMarginParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 部店コード != null 顧客コード != null 扱者コード != null
     * ページ内表示行数 <= 0 || 要求ページ番号 < 0
     * リクエスト.摘要 = 1
     * リクエスト.摘要日数 = 0
     */
    public void testGetPaymentRequisitionManageParamsListMargin_C0003()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListMargin_C0003()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "0";
            l_request.pageSize = "0";
            l_request.institutionCode = "0D";
            l_request.branchCode = "31";
            l_request.accountCode = "32";
            l_request.traderCode = "123";
            l_request.summary = "1";
            l_request.summaryDays = "0";
            
            TestDBUtility.deleteAll(PaymentRequisitionMarginRow.TYPE);
            PaymentRequisitionMarginParams l_paymentRequisitionMarginParams = new PaymentRequisitionMarginParams();
            l_paymentRequisitionMarginParams.setCalcResultMarginId(111111);
            l_paymentRequisitionMarginParams.setAccountId(222222);
            l_paymentRequisitionMarginParams.setFamilyName("beijing");
            l_paymentRequisitionMarginParams.setAccountAttribute("1");
            l_paymentRequisitionMarginParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setTradingStop("0");
            l_paymentRequisitionMarginParams.setMarginOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setPaymentStop("0");
            l_paymentRequisitionMarginParams.setOtherTradingStop("0");
            l_paymentRequisitionMarginParams.setDayTradeRestraint0(11);
            l_paymentRequisitionMarginParams.setDayTradeRestraint1(12);
            l_paymentRequisitionMarginParams.setDayTradeRestraint2(13);
            l_paymentRequisitionMarginParams.setDayTradeRestraint3(14);
            l_paymentRequisitionMarginParams.setDayTradeRestraint4(15);
            l_paymentRequisitionMarginParams.setOtherRestraint0(21);
            l_paymentRequisitionMarginParams.setOtherRestraint1(22);
            l_paymentRequisitionMarginParams.setOtherRestraint2(23);
            l_paymentRequisitionMarginParams.setOtherRestraint3(24);
            l_paymentRequisitionMarginParams.setOtherRestraint4(25);
            l_paymentRequisitionMarginParams.setOtherRestraint5(26);
            l_paymentRequisitionMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setInstitutionCode("0D");
            l_paymentRequisitionMarginParams.setBranchCode("31");
            l_paymentRequisitionMarginParams.setAccountCode("32");
            l_paymentRequisitionMarginParams.setSonarTraderCode("123");
            l_paymentRequisitionMarginParams.setBreak20elapsedDays("3");
            TestDBUtility.insertWithDel(l_paymentRequisitionMarginParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 部店コード != null 顧客コード != null 扱者コード != null
     * ページ内表示行数 <= 0 || 要求ページ番号 < 0
     * リクエスト.摘要 = 1
     * リクエスト.摘要日数 = 2
     */
    public void testGetPaymentRequisitionManageParamsListMargin_C0004()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListMargin_C0004()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "0";
            l_request.pageSize = "0";
            l_request.institutionCode = "0D";
            l_request.branchCode = "31";
            l_request.accountCode = "32";
            l_request.traderCode = "123";
            l_request.summary = "1";
            l_request.summaryDays = "2";
            
            TestDBUtility.deleteAll(PaymentRequisitionMarginRow.TYPE);
            PaymentRequisitionMarginParams l_paymentRequisitionMarginParams = new PaymentRequisitionMarginParams();
            l_paymentRequisitionMarginParams.setCalcResultMarginId(111111);
            l_paymentRequisitionMarginParams.setAccountId(222222);
            l_paymentRequisitionMarginParams.setFamilyName("beijing");
            l_paymentRequisitionMarginParams.setAccountAttribute("1");
            l_paymentRequisitionMarginParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setTradingStop("0");
            l_paymentRequisitionMarginParams.setMarginOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setPaymentStop("0");
            l_paymentRequisitionMarginParams.setOtherTradingStop("0");
            l_paymentRequisitionMarginParams.setDayTradeRestraint0(11);
            l_paymentRequisitionMarginParams.setDayTradeRestraint1(12);
            l_paymentRequisitionMarginParams.setDayTradeRestraint2(13);
            l_paymentRequisitionMarginParams.setDayTradeRestraint3(14);
            l_paymentRequisitionMarginParams.setDayTradeRestraint4(15);
            l_paymentRequisitionMarginParams.setOtherRestraint0(21);
            l_paymentRequisitionMarginParams.setOtherRestraint1(22);
            l_paymentRequisitionMarginParams.setOtherRestraint2(23);
            l_paymentRequisitionMarginParams.setOtherRestraint3(24);
            l_paymentRequisitionMarginParams.setOtherRestraint4(25);
            l_paymentRequisitionMarginParams.setOtherRestraint5(26);
            l_paymentRequisitionMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setInstitutionCode("0D");
            l_paymentRequisitionMarginParams.setBranchCode("31");
            l_paymentRequisitionMarginParams.setAccountCode("32");
            l_paymentRequisitionMarginParams.setSonarTraderCode("123");
            l_paymentRequisitionMarginParams.setBreak20elapsedDays("2");
            TestDBUtility.insertWithDel(l_paymentRequisitionMarginParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 部店コード == null 顧客コード == null 扱者コード == null
     * (ページ内表示行数 <= 0 || 要求ページ番号 < 0)以外的場合
     * リクエスト.摘要 = 2
     * リクエスト.摘要日数 = 8
     */
    public void testGetPaymentRequisitionManageParamsListMargin_C0005()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListMargin_C0005()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.institutionCode = "0D";
            l_request.summary = "2";
            l_request.summaryDays = "8";
            
            TestDBUtility.deleteAll(PaymentRequisitionMarginRow.TYPE);
            PaymentRequisitionMarginParams l_paymentRequisitionMarginParams = new PaymentRequisitionMarginParams();
            l_paymentRequisitionMarginParams.setCalcResultMarginId(111111);
            l_paymentRequisitionMarginParams.setAccountId(222222);
            l_paymentRequisitionMarginParams.setFamilyName("beijing");
            l_paymentRequisitionMarginParams.setAccountAttribute("1");
            l_paymentRequisitionMarginParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setTradingStop("0");
            l_paymentRequisitionMarginParams.setMarginOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setPaymentStop("0");
            l_paymentRequisitionMarginParams.setOtherTradingStop("0");
            l_paymentRequisitionMarginParams.setDayTradeRestraint0(11);
            l_paymentRequisitionMarginParams.setDayTradeRestraint1(12);
            l_paymentRequisitionMarginParams.setDayTradeRestraint2(13);
            l_paymentRequisitionMarginParams.setDayTradeRestraint3(14);
            l_paymentRequisitionMarginParams.setDayTradeRestraint4(15);
            l_paymentRequisitionMarginParams.setOtherRestraint0(21);
            l_paymentRequisitionMarginParams.setOtherRestraint1(22);
            l_paymentRequisitionMarginParams.setOtherRestraint2(23);
            l_paymentRequisitionMarginParams.setOtherRestraint3(24);
            l_paymentRequisitionMarginParams.setOtherRestraint4(25);
            l_paymentRequisitionMarginParams.setOtherRestraint5(26);
            l_paymentRequisitionMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setInstitutionCode("0D");
            l_paymentRequisitionMarginParams.setBreak30elapsedDays("8");
            l_paymentRequisitionMarginParams.setCashBalance0(-1);
            TestDBUtility.insertWithDel(l_paymentRequisitionMarginParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 部店コード == null 顧客コード == null 扱者コード == null
     * (ページ内表示行数 <= 0 || 要求ページ番号 < 0)以外的場合
     * リクエスト.摘要 = 2
     * リクエスト.摘要日数 = 0
     */
    public void testGetPaymentRequisitionManageParamsListMargin_C0006()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListMargin_C0006()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.institutionCode = "0D";
            l_request.summary = "2";
            l_request.summaryDays = "0";
            
            TestDBUtility.deleteAll(PaymentRequisitionMarginRow.TYPE);
            PaymentRequisitionMarginParams l_paymentRequisitionMarginParams = new PaymentRequisitionMarginParams();
            l_paymentRequisitionMarginParams.setCalcResultMarginId(111111);
            l_paymentRequisitionMarginParams.setAccountId(222222);
            l_paymentRequisitionMarginParams.setFamilyName("beijing");
            l_paymentRequisitionMarginParams.setAccountAttribute("1");
            l_paymentRequisitionMarginParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setTradingStop("0");
            l_paymentRequisitionMarginParams.setMarginOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setPaymentStop("0");
            l_paymentRequisitionMarginParams.setOtherTradingStop("0");
            l_paymentRequisitionMarginParams.setDayTradeRestraint0(11);
            l_paymentRequisitionMarginParams.setDayTradeRestraint1(12);
            l_paymentRequisitionMarginParams.setDayTradeRestraint2(13);
            l_paymentRequisitionMarginParams.setDayTradeRestraint3(14);
            l_paymentRequisitionMarginParams.setDayTradeRestraint4(15);
            l_paymentRequisitionMarginParams.setOtherRestraint0(21);
            l_paymentRequisitionMarginParams.setOtherRestraint1(22);
            l_paymentRequisitionMarginParams.setOtherRestraint2(23);
            l_paymentRequisitionMarginParams.setOtherRestraint3(24);
            l_paymentRequisitionMarginParams.setOtherRestraint4(25);
            l_paymentRequisitionMarginParams.setOtherRestraint5(26);
            l_paymentRequisitionMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setInstitutionCode("0D");
            l_paymentRequisitionMarginParams.setBreak30elapsedDays("8");
            l_paymentRequisitionMarginParams.setCashBalance0(-1);
            TestDBUtility.insertWithDel(l_paymentRequisitionMarginParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 部店コード == null 顧客コード == null 扱者コード == null
     * (ページ内表示行数 <= 0 || 要求ページ番号 < 0)以外的場合
     * リクエスト.摘要 = 2
     * リクエスト.摘要日数 = 2
     */
    public void testGetPaymentRequisitionManageParamsListMargin_C0007()
    {
        final String STR_METHOD_NAME = "testGetPaymentRequisitionManageParamsListMargin_C0007()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20080710","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            
            WEB3AdminTPPaymentRequisitionManageSearchRequest l_request =
                new WEB3AdminTPPaymentRequisitionManageSearchRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.institutionCode = "0D";
            l_request.summary = "2";
            l_request.summaryDays = "0";
            
            TestDBUtility.deleteAll(PaymentRequisitionMarginRow.TYPE);
            PaymentRequisitionMarginParams l_paymentRequisitionMarginParams = new PaymentRequisitionMarginParams();
            l_paymentRequisitionMarginParams.setCalcResultMarginId(111111);
            l_paymentRequisitionMarginParams.setAccountId(222222);
            l_paymentRequisitionMarginParams.setFamilyName("beijing");
            l_paymentRequisitionMarginParams.setAccountAttribute("1");
            l_paymentRequisitionMarginParams.setCalcDate(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setTradingStop("0");
            l_paymentRequisitionMarginParams.setMarginOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setIfoOpenPositionStop("0");
            l_paymentRequisitionMarginParams.setPaymentStop("0");
            l_paymentRequisitionMarginParams.setOtherTradingStop("0");
            l_paymentRequisitionMarginParams.setDayTradeRestraint0(11);
            l_paymentRequisitionMarginParams.setDayTradeRestraint1(12);
            l_paymentRequisitionMarginParams.setDayTradeRestraint2(13);
            l_paymentRequisitionMarginParams.setDayTradeRestraint3(14);
            l_paymentRequisitionMarginParams.setDayTradeRestraint4(15);
            l_paymentRequisitionMarginParams.setOtherRestraint0(21);
            l_paymentRequisitionMarginParams.setOtherRestraint1(22);
            l_paymentRequisitionMarginParams.setOtherRestraint2(23);
            l_paymentRequisitionMarginParams.setOtherRestraint3(24);
            l_paymentRequisitionMarginParams.setOtherRestraint4(25);
            l_paymentRequisitionMarginParams.setOtherRestraint5(26);
            l_paymentRequisitionMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080710","yyyyMMdd"));
            l_paymentRequisitionMarginParams.setInstitutionCode("0D");
            l_paymentRequisitionMarginParams.setBreak30elapsedDays("2");
            l_paymentRequisitionMarginParams.setCashBalance0(-1);
            TestDBUtility.insertWithDel(l_paymentRequisitionMarginParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3AdminTPPaymentRequisitionManageServiceImpl l_impl = new WEB3AdminTPPaymentRequisitionManageServiceImpl();
            ListPage l_returnListPage = l_impl.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);
            
            assertEquals(1, l_returnListPage.totalSize());
            assertEquals(1, l_returnListPage.totalPages());
            assertEquals(0, l_returnListPage.pageNumber());
            assertEquals(1, l_returnListPage.pageSize());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
