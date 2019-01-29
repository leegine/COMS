head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiServiceUseApplicationServiceImplTest_submitSrvAppliRegist.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3SrvRegiServiceUseApplicationServiceImplTest_submitSrvAppliRegist.java) 
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/16 崔遠鵬(中訊) 新規作成
 Revision History : 2007/07/25 金傑(中訊) モデル295
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.gentrade.data.SrvRegiApplicationRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiHistoryParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiServiceUseApplicationServiceImplTest_submitSrvAppliRegist extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3SrvRegiServiceUseApplicationServiceImplTest_submitSrvAppliRegist.class);

    WEB3SrvRegiServiceUseApplicationServiceImpl impl = new WEB3SrvRegiServiceUseApplicationServiceImplForTest();

    private boolean l_blnIsSetTrader = false;

    public WEB3SrvRegiServiceUseApplicationServiceImplTest_submitSrvAppliRegist(String arg0)
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

//    public void testSubmitSrvAppliRegist01()
//    {
//        final String STR_METHOD_NAME = "testSubmitSrvAppliRegist01()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        Calendar l_calendar =  Calendar.getInstance();
//        l_calendar.set(2007, 6-1, 14);
//        Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
//            "getServiceRegist",
//            new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
//                boolean.class},
//            null);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
//            "calcAppliEndDate",
//            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class,
//                long.class, String.class, String.class},
//            l_tsAppliyDate);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
//            "getInitializeAppliDiv",
//            new Class[] {String.class, String.class, String.class, String.class},
//            "1");
//
//        try
//        {
//            MainAccountParams l_mainAccountParams = new MainAccountParams();
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
//            l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            BranchParams l_branchParams = new BranchParams();
//            l_processor.doDeleteAllQuery(BranchRow.TYPE);
//            l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            SubAccountParams l_subAccountParams = new SubAccountParams();
//            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
//            l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
//            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
//            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
//            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
//
//            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
//            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
//            l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
//            l_srvRegiApplicationParams.setInstitutionCode("0D");
//            l_srvRegiApplicationParams.setBranchCode("381");
//            l_srvRegiApplicationParams.setAccountCode("2512246");
//            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);            
//
//            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
//            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
//            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
//            l_srvRegiSetupParams.setLotDiv("0");
//            l_srvRegiSetupParams.setFreeCoverageLength(1);
//            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
//
//            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
//            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
//            l_srvAppliAttributeParams.setInstitutionCode("0D");
//            l_srvAppliAttributeParams.setBranchCode("381");
//            l_srvAppliAttributeParams.setAccountCode("2512246");
//            l_srvAppliAttributeParams.setAppliAttribute("2");
//            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);
//
//            Method l_method = WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
//                "submitSrvAppliRegist",
//                new Class[]{WEB3GentradeSubAccount.class,
//                    String.class,
//                    Long.class,
//                    Double.class,
//                    Timestamp.class,
//                    Timestamp.class,
//                    Long.class,
//                    String.class,
//                    String.class,
//                    String.class});
//            l_method.setAccessible(true);
//
//            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(333812512246L, 33381251220301L);
//            WEB3GentradeSrvRegiApplication l_app = (WEB3GentradeSrvRegiApplication)l_method.invoke(
//                impl, 
//                new Object[]{
//                    l_gentradeSubAccount,
//                    "1234",
//                    new Long(0L),
//                    new Double(0.0),
//                    l_tsAppliyDate,
//                    l_tsAppliyDate,
//                    new Long(6L),
//                    "1",
//                    "",
//                    ""});
//
//            assertEquals(l_app.getInstitutionCode(), "0D");
//            assertEquals(l_app.getBranchCode(), "381");
//            assertEquals(l_app.getSrvDiv(), "1234");
//            assertEquals(l_app.getAccountCode(), "2512246");
//            assertEquals(l_app.getRegistId(), 124);
//            assertEquals(l_app.getEffectiveDiv(), "0");
//            assertEquals(l_app.getCancelDiv(), "0");
////            assertEquals(WEB3DateUtility.compareToDay(l_app.getAppliStartDate(),
////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
////            assertEquals(WEB3DateUtility.compareToDay(l_app.getAppliEndDate(),
////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
////            assertEquals(l_app.getOrderId(), new Long(6));
////            assertEquals(WEB3DateUtility.compareToDay(l_app.getAppliDate(),
////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
//            assertEquals(l_app.getPaymentDiv(), "0");
//            assertEquals(l_app.getAppliLotDiv(), "2");
//            assertEquals(l_app.getUseAmt(), new Double(0.0));
////            assertEquals(WEB3DateUtility.compareToDay(l_app.getPaymentDate(),
////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
//            assertNull(l_app.getCancelLimitDate());
//            assertEquals(l_app.getLastUpdater(), "251224");
//            assertEquals(l_app.getFreeSrvDiv(), "1");
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

////    public void testSubmitSrvAppliRegist02()
////    {
////        final String STR_METHOD_NAME = "testSubmitSrvAppliRegist02()";
////        log.entering(TEST_START + STR_METHOD_NAME);
////
////        Calendar l_calendar =  Calendar.getInstance();
////        l_calendar.set(2007, 6-1, 14);
////        Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
////        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
////            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
////            "getServiceRegist",
////            new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
////                boolean.class},
////            null);
////        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
////            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
////            "calcAppliEndDate",
////            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class,
////                long.class, String.class},
////            l_tsAppliyDate);
////        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
////            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
////            "getInitializeAppliDiv",
////            new Class[] {String.class, String.class, String.class, String.class},
////            "1");
////        try
////        {
////            MainAccountParams l_mainAccountParams = new MainAccountParams();
////            QueryProcessor l_processor = Processors.getDefaultProcessor();
////            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
////            l_mainAccountParams = TestDBUtility.getMainAccountRow();
////            TestDBUtility.insertWithDel(l_mainAccountParams);
////
////            BranchParams l_branchParams = new BranchParams();
////            l_processor.doDeleteAllQuery(BranchRow.TYPE);
////            l_branchParams = TestDBUtility.getBranchRow();
////            TestDBUtility.insertWithDel(l_branchParams);
////
////            SubAccountParams l_subAccountParams = new SubAccountParams();
////            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
////            l_subAccountParams = TestDBUtility.getSubAccountRow();
////            l_subAccountParams.setAccountId(333812512246L);
////            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
////            TestDBUtility.insertWithDel(l_subAccountParams);
////
////            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
////            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
////            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
////            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
////
////            SrvRegiApplicationParams l_srvRegiApplicationParams = new SrvRegiApplicationParams();
////            l_processor.doDeleteAllQuery(SrvRegiApplicationRow.TYPE);
////            l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
////            l_srvRegiApplicationParams.setInstitutionCode("0D");
////            l_srvRegiApplicationParams.setBranchCode("381");
////            l_srvRegiApplicationParams.setAccountCode("2512246");
////            TestDBUtility.insertWithDel(l_srvRegiApplicationParams);            
////
////            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
////            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
////            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
////            l_srvRegiSetupParams.setLotDiv("0");
////            l_srvRegiSetupParams.setFreeCoverageLength(1);
////            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
////
////            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
////
////            ThreadLocalSystemAttributesRegistry.setAttribute(
////                "xblocks.gtl.attributes.systemtimestamp",
////                l_tsAppliyDate);
////
////            Method l_method = WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
////                "submitSrvAppliRegist",
////                new Class[]{WEB3GentradeSubAccount.class,
////                    String.class,
////                    Long.class,
////                    Double.class,
////                    Timestamp.class,
////                    Timestamp.class,
////                    Long.class,
////                    String.class,
////                    String.class,
////                    String.class});
////            l_method.setAccessible(true);
////
////            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(333812512246L, 33381251220301L);
////            WEB3GentradeSrvRegiApplication l_app = (WEB3GentradeSrvRegiApplication)l_method.invoke(
////                impl, 
////                new Object[]{
////                    l_gentradeSubAccount,
////                    "1234",
////                    new Long(0L),
////                    new Double(0.0),
////                    l_tsAppliyDate,
////                    l_tsAppliyDate,
////                    new Long(6L),
////                    "1",
////                    "",
////                    ""});
////
////            assertEquals(l_app.getInstitutionCode(), "0D");
////            assertEquals(l_app.getBranchCode(), "381");
////            assertEquals(l_app.getSrvDiv(), "1234");
////            assertEquals(l_app.getAccountCode(), "2512246");
////            assertEquals(l_app.getRegistId(), 124);
////            assertEquals(l_app.getEffectiveDiv(), "0");
////            assertEquals(l_app.getCancelDiv(), "0");
//////            assertEquals(WEB3DateUtility.compareToDay(l_app.getAppliStartDate(),
//////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
//////            assertEquals(WEB3DateUtility.compareToDay(l_app.getAppliEndDate(),
//////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
////            assertEquals(l_app.getOrderId(), new Long(6));
//////            assertEquals(WEB3DateUtility.compareToDay(l_app.getAppliDate(),
//////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
////            assertEquals(l_app.getPaymentDiv(), "0");
////            assertEquals(l_app.getAppliLotDiv(), "2");
////            assertEquals(l_app.getUseAmt(), new Double(0.0));
//////            assertEquals(WEB3DateUtility.compareToDay(l_app.getPaymentDate(),
//////                WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
////            assertNull(l_app.getCancelLimitDate());
////            assertEquals(l_app.getLastUpdater(), "251224");
////            assertNull(l_app.getFreeSrvDiv());
////        }
////        catch (Exception l_ex)
////        {
////            log.debug(STR_METHOD_NAME,l_ex);
////            fail();
////        }
////        log.exiting(TEST_END + STR_METHOD_NAME);
////    }
//    
//    /**
//     * getサービス申込属性情報() == nullの場合
//     * 「updateサービス申込属性」操作不執行
//     * 
//     *
//     */
//    public void testSubmitSrvAppliRegist03()
//    {
//        final String STR_METHOD_NAME = "testSubmitSrvAppliRegist03()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            initData();
//            setMockMethod();
//            this.l_blnIsSetTrader = false;
//            
//            Calendar l_calendar = Calendar.getInstance();
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONTH,6);
//            l_calendar.set(Calendar.DAY_OF_MONTH,27);
//            l_calendar.set(Calendar.HOUR_OF_DAY,10);
//            l_calendar.set(Calendar.MINUTE,10);
//            l_calendar.set(Calendar.SECOND,20);
//
//            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
//
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
//            
//            TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
//            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//            l_srvAppliAttributeParams.setInstitutionCode("1D");
//            l_srvAppliAttributeParams.setBranchCode("381");
//            l_srvAppliAttributeParams.setAccountCode("1234567");
//            l_srvAppliAttributeParams.setSrvDiv("1234");
//            l_srvAppliAttributeParams.setAppliAttribute("2");
//            l_srvAppliAttributeParams.setLastUpdater("123");
//            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONTH,6);
//            l_calendar.set(Calendar.DAY_OF_MONTH,26);
//            l_calendar.set(Calendar.HOUR_OF_DAY,10);
//            l_calendar.set(Calendar.MINUTE,10);
//            l_calendar.set(Calendar.SECOND,20);
//            l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
//            l_srvAppliAttributeParams.setLastUpdatedTimestamp(l_tsBizDate);
//            l_srvAppliAttributeParams.setProcDiv("0");
//            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//            
//            Method l_method = WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
//                    "submitSrvAppliRegist",
//                    new Class[]{WEB3GentradeSubAccount.class,
//                        String.class,
//                        Long.class,
//                        Double.class,
//                        Timestamp.class,
//                        Timestamp.class,
//                        Long.class,
//                        String.class,
//                        String.class,
//                        String.class});
//                l_method.setAccessible(true);
//
//                l_calendar.set(2007, 6-1, 14);
//                Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//                
//                WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
//                WEB3GentradeSrvRegiApplication l_app = (WEB3GentradeSrvRegiApplication)l_method.invoke(
//                    impl, 
//                    new Object[]{
//                        l_gentradeSubAccount,
//                        "1234",
//                        new Long(0L),
//                        new Double(0.0),
//                        l_tsAppliyDate,
//                        l_tsAppliyDate,
//                        new Long(6L),
//                        "1",
//                        "",
//                        null});
//                
//                // 不更新數據庫
//                List l_updateResult = this.getSearchResult("1D","381","1234567","1234");
//                
//                assertNotNull(l_updateResult);
//                
//
//                assertEquals("123",((SrvAppliAttributeRow)l_updateResult.get(0)).getLastUpdater());
//
//                
////                assertEquals(0,WEB3DateUtility.compareToDay(((SrvAppliAttributeRow)l_updateResult.get(0)).getLastUpdatedTimestamp(),
////                        WEB3DateUtility.getDate("20070726", "yyyyMMdd")));
//                
//                assertEquals("0",((SrvAppliAttributeRow)l_updateResult.get(0)).getProcDiv());
//                
//
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * getサービス申込属性情報() != nullの場合
//     * 「updateサービス申込属性」操作執行並更新:
//     *  更新者コード:顧客コード="123456"
//     *  更新日付=20070727
//     *  処理区分 = 1
//     *
//     */
//    public void testSubmitSrvAppliRegist04()
//    {
//        final String STR_METHOD_NAME = "testSubmitSrvAppliRegist04()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            initData();
//            setMockMethod();
//            
//
//            Calendar l_calendar = Calendar.getInstance();
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONTH,6);
//            l_calendar.set(Calendar.DAY_OF_MONTH,27);
//            l_calendar.set(Calendar.HOUR_OF_DAY,10);
//            l_calendar.set(Calendar.MINUTE,10);
//            l_calendar.set(Calendar.SECOND,20);
//
//            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
//
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {},
//                l_tsBizDate);
//            
//            TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
//            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//            l_srvAppliAttributeParams.setInstitutionCode("0D");
//            l_srvAppliAttributeParams.setBranchCode("381");
//            l_srvAppliAttributeParams.setAccountCode("1234567");
//            l_srvAppliAttributeParams.setSrvDiv("1234");
//            l_srvAppliAttributeParams.setAppliAttribute("2");
//            l_srvAppliAttributeParams.setLastUpdater("123");
//            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONTH,6);
//            l_calendar.set(Calendar.DAY_OF_MONTH,26);
//            l_calendar.set(Calendar.HOUR_OF_DAY,10);
//            l_calendar.set(Calendar.MINUTE,10);
//            l_calendar.set(Calendar.SECOND,20);
//            l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
//            l_srvAppliAttributeParams.setLastUpdatedTimestamp(l_tsBizDate);
//            l_srvAppliAttributeParams.setProcDiv("0");
//            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//
//            Method l_method = WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
//                    "submitSrvAppliRegist",
//                    new Class[]{WEB3GentradeSubAccount.class,
//                        String.class,
//                        Long.class,
//                        Double.class,
//                        Timestamp.class,
//                        Timestamp.class,
//                        Long.class,
//                        String.class,
//                        String.class,
//                        String.class});
//                l_method.setAccessible(true);
//
//                l_calendar.set(2007, 6-1, 14);
//                Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//                
//                WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
//                WEB3GentradeSrvRegiApplication l_app = (WEB3GentradeSrvRegiApplication)l_method.invoke(
//                    impl, 
//                    new Object[]{
//                        l_gentradeSubAccount,
//                        "1234",
//                        new Long(0L),
//                        new Double(0.0),
//                        l_tsAppliyDate,
//                        l_tsAppliyDate,
//                        new Long(6L),
//                        "1",
//                        "",
//                        ""});
//                
//                List l_updateResult = this.getSearchResult("0D","381","1234567","1234");
//                
//                assertNotNull(l_updateResult);
//                
//                // 更新者コードを更新する
//                assertEquals("123",((SrvAppliAttributeRow)l_updateResult.get(0)).getLastUpdater());
//                // 更新日付を更新する
//                
////                assertEquals(0,WEB3DateUtility.compareToDay(((SrvAppliAttributeRow)l_updateResult.get(0)).getLastUpdatedTimestamp(),
////                        WEB3DateUtility.getDate("20070727", "yyyyMMdd")));
//                
//                // 処理区分を更新する
//                assertEquals("0",((SrvAppliAttributeRow)l_updateResult.get(0)).getProcDiv());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * getサービス申込属性情報() != nullの場合
//     * 「updateサービス申込属性」操作執行並更新:
//     *  更新者コード:扱者コード="7654321"
//     *  更新日付=20070727
//     *  処理区分 = 1
//     *
//     */
//    public void testSubmitSrvAppliRegist05()
//    {
//        final String STR_METHOD_NAME = "testSubmitSrvAppliRegist05()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            initData();
//            setMockMethod();
//            
//            this.l_blnIsSetTrader = true;
//            
//            Calendar l_calendar = Calendar.getInstance();
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONTH,6);
//            l_calendar.set(Calendar.DAY_OF_MONTH,27);
//            l_calendar.set(Calendar.HOUR_OF_DAY,10);
//            l_calendar.set(Calendar.MINUTE,10);
//            l_calendar.set(Calendar.SECOND,20);
//
//            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
//
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {},
//                l_tsBizDate);
//            
//            TestDBUtility.deleteAll(SrvAppliAttributeRow.TYPE);
//            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//            l_srvAppliAttributeParams.setInstitutionCode("0D");
//            l_srvAppliAttributeParams.setBranchCode("381");
//            l_srvAppliAttributeParams.setAccountCode("1234567");
//            l_srvAppliAttributeParams.setSrvDiv("1234");
//            l_srvAppliAttributeParams.setAppliAttribute("2");
//            l_srvAppliAttributeParams.setLastUpdater("123");
//            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONTH,6);
//            l_calendar.set(Calendar.DAY_OF_MONTH,26);
//            l_calendar.set(Calendar.HOUR_OF_DAY,10);
//            l_calendar.set(Calendar.MINUTE,10);
//            l_calendar.set(Calendar.SECOND,20);
//            l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
//            l_srvAppliAttributeParams.setLastUpdatedTimestamp(l_tsBizDate);
//            l_srvAppliAttributeParams.setProcDiv("0");
//            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//
//            Method l_method = WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
//                    "submitSrvAppliRegist",
//                    new Class[]{WEB3GentradeSubAccount.class,
//                        String.class,
//                        Long.class,
//                        Double.class,
//                        Timestamp.class,
//                        Timestamp.class,
//                        Long.class,
//                        String.class,
//                        String.class,
//                        String.class});
//                l_method.setAccessible(true);
//                
//                l_calendar =  Calendar.getInstance();
//                l_calendar.set(2007, 6-1, 14);
//                Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//                
//                WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
//                WEB3GentradeSrvRegiApplication l_app = (WEB3GentradeSrvRegiApplication)l_method.invoke(
//                    impl, 
//                    new Object[]{
//                        l_gentradeSubAccount,
//                        "1234",
//                        new Long(0L),
//                        new Double(0.0),
//                        l_tsAppliyDate,
//                        l_tsAppliyDate,
//                        new Long(6L),
//                        "1",
//                        "",
//                        ""});
//                
//                List l_updateResult = this.getSearchResult("0D","381","1234567","1234");
//                
//                assertNotNull(l_updateResult);
//                
//                // 更新者コードを更新する
//                assertEquals("123",((SrvAppliAttributeRow)l_updateResult.get(0)).getLastUpdater());
//                // 更新日付を更新する
//                
////                assertEquals(0,WEB3DateUtility.compareToDay(((SrvAppliAttributeRow)l_updateResult.get(0)).getLastUpdatedTimestamp(),
////                        WEB3DateUtility.getDate("20070727", "yyyyMMdd")));
//                
//                // 処理区分を更新する
//                assertEquals("0",((SrvAppliAttributeRow)l_updateResult.get(0)).getProcDiv());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    /**
     *
     */
    public void testValidateAppliRegist_case0001()
    {
        final String STR_METHOD_NAME = "testValidateAppliRegist_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.impl = new WEB3SrvRegiServiceUseApplicationServiceImpl();

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            l_institutionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            l_srvRegiMasterParams.setSrvDiv("1");
            l_srvRegiMasterParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams =
                TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
            l_srvRegiSetupParams.setSrvDiv("1");
            l_srvRegiSetupParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiSetupParams.setLotDiv("2");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            TestDBUtility.deleteAll(SrvAppliAttributeParams.TYPE);
            l_srvAppliAttributeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_srvAppliAttributeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_srvAppliAttributeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_srvAppliAttributeParams.setSrvDiv("1");
            l_srvAppliAttributeParams.setAppliAttribute("1");
            l_srvAppliAttributeParams.setLastUpdater("1");
            l_srvAppliAttributeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_srvAppliAttributeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

//            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
//                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
//            l_otherOrgInfoAdminParams.setSequenceNumber(123);
//            l_otherOrgInfoAdminParams.setSrvDiv("1");
//            l_otherOrgInfoAdminParams.setId("1");
//            l_otherOrgInfoAdminParams.setPassword("1");
//            l_otherOrgInfoAdminParams.setStatus("9");
//            l_otherOrgInfoAdminParams.setInstitutionCode(
//                l_mainAccountParams.getInstitutionCode());
//            l_otherOrgInfoAdminParams.setBranchCode(
//                l_mainAccountParams.getBranchCode());
//            l_otherOrgInfoAdminParams.setAccountCode(
//                l_mainAccountParams.getAccountCode());
//            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
//            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdater("1");
//            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
//            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3GentradeSubAccount l_gentradeSubAccount =
                new WEB3GentradeSubAccount(l_subAccountParams);
            String l_strSrvDiv = "1";
            Long l_srvUsePeriodId = null;
            Double l_biddingPrice = new Double("0");
            Timestamp l_tsAppliDate = new Timestamp(new Date().getTime());
            String l_strAppliTpyeDiv = "2";
            String l_strPassword = "1";

//            MOCK_MANAGER.setIsMockUsed(false);
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);

            Method l_method =
                WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
                    "validateAppliRegist",
                    new Class[] {
                        WEB3GentradeSubAccount.class,
                        String.class,
                        Long.class,
                        Double.class,
                        Timestamp.class,
                        String.class,
                        String.class});
            l_method.setAccessible(true);
            l_method.invoke(
                this.impl,
                new Object[] {
                    l_gentradeSubAccount,
                    l_strSrvDiv,
                    l_srvUsePeriodId,
                    l_biddingPrice,
                    l_tsAppliDate,
                    l_strAppliTpyeDiv,
                    l_strPassword});

            fail();
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.debug(STR_METHOD_NAME,l_exNSME);
            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.debug(STR_METHOD_NAME,l_exIAE);
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.debug(STR_METHOD_NAME,l_exITE);
            assertEquals(
                WEB3BusinessLayerException.class,
                l_exITE.getTargetException().getClass());
            assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                ((WEB3BusinessLayerException)l_exITE.getTargetException()).getErrorInfo());
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME,l_exBE);
            fail();
        }
        finally
        {
            log.entering(TEST_END + STR_METHOD_NAME);
        }
    }

  /**
   *
   */
  public void testSubmitSrvAppliRegist_case0006()
  {
      final String STR_METHOD_NAME = "testSubmitSrvAppliRegist_case0006()";
      log.entering(TEST_START + STR_METHOD_NAME);

      this.impl = new WEB3SrvRegiServiceUseApplicationServiceImpl();

      try
      {
          MainAccountParams l_mainAccountParams =
              TestDBUtility.getMainAccountRow();
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          TestDBUtility.insertWithDel(l_mainAccountParams);

          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          TestDBUtility.deleteAll(BranchParams.TYPE);
          l_branchParams.setBranchCode(
              l_mainAccountParams.getBranchCode());
          l_branchParams.setInstitutionCode(
              l_mainAccountParams.getInstitutionCode());
          TestDBUtility.insertWithDel(l_branchParams);

          SubAccountParams l_subAccountParams =
              TestDBUtility.getSubAccountRow();
          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
          l_subAccountParams.setBranchId(l_branchParams.getBranchId());
          l_subAccountParams.setSubAccountType(
              SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);

          SrvRegiMasterParams l_srvRegiMasterParams =
              TestDBUtility.getSrvRegiMasterRow();
          TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
          l_srvRegiMasterParams.setSrvDiv("1");
          l_srvRegiMasterParams.setInstitutionCode(
              l_mainAccountParams.getInstitutionCode());
          l_srvRegiMasterParams.setSrvStatus("2");
          l_srvRegiMasterParams.setSpecialProcessDiv("1");
          TestDBUtility.insertWithDel(l_srvRegiMasterParams);

          SrvRegiSetupParams l_srvRegiSetupParams =
              TestDBUtility.getSrvRegiSetupRow();
          TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
          l_srvRegiSetupParams.setSrvDiv("1");
          l_srvRegiSetupParams.setInstitutionCode(
              l_mainAccountParams.getInstitutionCode());
          l_srvRegiSetupParams.setLotDiv("2");
          TestDBUtility.insertWithDel(l_srvRegiSetupParams);

          TestDBUtility.deleteAll(SrvAppliAttributeParams.TYPE);

          OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
              new OtherOrgInfoAdminParams();
          TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
          l_otherOrgInfoAdminParams.setSequenceNumber(123);
          l_otherOrgInfoAdminParams.setSrvDiv("1");
          l_otherOrgInfoAdminParams.setId("1");
          l_otherOrgInfoAdminParams.setPassword("1");
          l_otherOrgInfoAdminParams.setStatus("9");
          l_otherOrgInfoAdminParams.setInstitutionCode(
              l_mainAccountParams.getInstitutionCode());
          l_otherOrgInfoAdminParams.setBranchCode(
              l_mainAccountParams.getBranchCode());
          l_otherOrgInfoAdminParams.setAccountCode(
              l_mainAccountParams.getAccountCode());
          l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
          l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
          l_otherOrgInfoAdminParams.setLastUpdater("1");
          l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
          l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
          TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

          MOCK_MANAGER.setIsMockUsed(true);
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
              "getLoginInfo",
              new Class[] {},
              new LoginInfoTest());

          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
              "getAccountId",
              new Class[] {},
              new Long(1001));

          WEB3GentradeSubAccount l_gentradeSubAccount =
              new WEB3GentradeSubAccount(l_subAccountParams);
          String l_strSrvDiv = "1";
          Long l_srvUsePeriodId = null;
          Double l_biddingPrice = new Double("0");
          Timestamp l_tsAppliDate = new Timestamp(new Date().getTime());
          Timestamp l_tsPaymentDate = new Timestamp(new Date().getTime());
          Long l_orderId = new Long(0);
          String l_strAppliTpyeDiv = "1";
          String l_strSpecialDiv = "1";
          String l_strFreeAttributeApplyDiv = "1";

          Method l_method =
              WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
                  "submitSrvAppliRegist",
                  new Class[] {
                      WEB3GentradeSubAccount.class,
                      String.class,
                      Long.class,
                      Double.class,
                      Timestamp.class,
                      Timestamp.class,
                      Long.class,
                      String.class,
                      String.class,
                      String.class});
          l_method.setAccessible(true);
          l_method.invoke(
              this.impl,
              new Object[] {
                  l_gentradeSubAccount,
                  l_strSrvDiv,
                  l_srvUsePeriodId,
                  l_biddingPrice,
                  l_tsAppliDate,
                  l_tsPaymentDate,
                  l_orderId,
                  l_strAppliTpyeDiv,
                  l_strSpecialDiv,
                  l_strFreeAttributeApplyDiv});

          fail();
      }
      catch (NoSuchMethodException l_exNSME)
      {
          log.debug(STR_METHOD_NAME,l_exNSME);
          fail();
      }
      catch (IllegalAccessException l_exIAE)
      {
          log.debug(STR_METHOD_NAME,l_exIAE);
          fail();
      }
      catch (InvocationTargetException l_exITE)
      {
          log.debug(STR_METHOD_NAME,l_exITE);
          assertEquals(
              WEB3BusinessLayerException.class,
              l_exITE.getTargetException().getClass());
          assertEquals(
              WEB3ErrorCatalog.BUSINESS_ERROR_02837,
              ((WEB3BusinessLayerException)l_exITE.getTargetException()).getErrorInfo());
      }
      catch (WEB3BaseException l_exBE)
      {
          log.debug(STR_METHOD_NAME,l_exBE);
          fail();
      }
      finally
      {
          log.entering(TEST_END + STR_METHOD_NAME);
      }
  }

    /**
     *
     */
    public void testSubmitSrvAppliRegist_case0007()
    {
        final String STR_METHOD_NAME = "testSubmitSrvAppliRegist_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.impl = new WEB3SrvRegiServiceUseApplicationServiceImpl();

        try
        {
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            l_branchParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_branchParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            l_srvRegiMasterParams.setSrvDiv("1");
            l_srvRegiMasterParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiMasterParams.setSrvStatus("2");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams =
                TestDBUtility.getSrvRegiSetupRow();
            TestDBUtility.deleteAll(SrvRegiSetupParams.TYPE);
            l_srvRegiSetupParams.setSrvDiv("1");
            l_srvRegiSetupParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvRegiSetupParams.setLotDiv("0");
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            SrvAppliAttributeParams l_srvAppliAttributeParams =
                TestDBUtility.getSrvAppliAttributeRow();
            TestDBUtility.deleteAll(SrvAppliAttributeParams.TYPE);
            l_srvAppliAttributeParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_srvAppliAttributeParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_srvAppliAttributeParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_srvAppliAttributeParams.setSrvDiv("1");
            l_srvAppliAttributeParams.setProcDiv("0");
            l_srvAppliAttributeParams.setAppliStartDate(null);
            l_srvAppliAttributeParams.setAppliEndDate(null);
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            SrvRegiHistoryParams l_srvRegiHistoryParams = new SrvRegiHistoryParams();
            TestDBUtility.deleteAll(SrvRegiHistoryParams.TYPE);
            l_srvRegiHistoryParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_srvRegiHistoryParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_srvRegiHistoryParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_srvRegiHistoryParams.setSrvDiv("1");
            l_srvRegiHistoryParams.setOrderRootDiv("1");
            l_srvRegiHistoryParams.setLastUpdater("1");
            l_srvRegiHistoryParams.setRegistDate(new Date());
            l_srvRegiHistoryParams.setCreatedTimestamp(new Date());
            l_srvRegiHistoryParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_srvRegiHistoryParams);

            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            l_otherOrgInfoAdminParams.setSequenceNumber(123);
            l_otherOrgInfoAdminParams.setSrvDiv("1");
            l_otherOrgInfoAdminParams.setId("1");
            l_otherOrgInfoAdminParams.setPassword("1");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setInstitutionCode(
                l_mainAccountParams.getInstitutionCode());
            l_otherOrgInfoAdminParams.setBranchCode(
                l_mainAccountParams.getBranchCode());
            l_otherOrgInfoAdminParams.setAccountCode(
                l_mainAccountParams.getAccountCode());
            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1");

            WEB3GentradeSubAccount l_gentradeSubAccount =
                new WEB3GentradeSubAccount(l_subAccountParams);
            String l_strSrvDiv = "1";
            Long l_srvUsePeriodId = null;
            Double l_biddingPrice = new Double("0");
            Timestamp l_tsAppliDate = new Timestamp(new Date().getTime());
            Timestamp l_tsPaymentDate = new Timestamp(new Date().getTime());
            Long l_orderId = new Long(0);
            String l_strAppliTpyeDiv = "1";
            String l_strSpecialDiv = "1";
            String l_strFreeAttributeApplyDiv = "1";

            Method l_method =
                WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
                    "submitSrvAppliRegist",
                    new Class[] {
                        WEB3GentradeSubAccount.class,
                        String.class,
                        Long.class,
                        Double.class,
                        Timestamp.class,
                        Timestamp.class,
                        Long.class,
                        String.class,
                        String.class,
                        String.class});
            l_method.setAccessible(true);
            l_method.invoke(
                this.impl,
                new Object[] {
                    l_gentradeSubAccount,
                    l_strSrvDiv,
                    l_srvUsePeriodId,
                    l_biddingPrice,
                    l_tsAppliDate,
                    l_tsPaymentDate,
                    l_orderId,
                    l_strAppliTpyeDiv,
                    l_strSpecialDiv,
                    l_strFreeAttributeApplyDiv});

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_list = l_processor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);
            assertEquals(1, l_list.size());
            OtherOrgInfoAdminParams l_resultOtherOrgInfoAdminParams =
                (OtherOrgInfoAdminParams)l_list.get(0);
            assertEquals("0D", l_resultOtherOrgInfoAdminParams.getInstitutionCode());
            assertEquals("381", l_resultOtherOrgInfoAdminParams.getBranchCode());
            assertEquals("2512246", l_resultOtherOrgInfoAdminParams.getAccountCode());
        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.debug(STR_METHOD_NAME,l_exNSME);
            fail();
        }
        catch (IllegalAccessException l_exIAE)
        {
            log.debug(STR_METHOD_NAME,l_exIAE);
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.debug(STR_METHOD_NAME,l_exITE);
//            assertEquals(
//                WEB3BusinessLayerException.class,
//                l_exITE.getTargetException().getClass());
//            assertEquals(
//                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
//                ((WEB3BusinessLayerException)l_exITE.getTargetException()).getErrorInfo());
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(STR_METHOD_NAME,l_exBE);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(STR_METHOD_NAME,l_exE);
            fail();
        }
        finally
        {
            log.entering(TEST_END + STR_METHOD_NAME);
        }
    }

//    /**
//     *
//     */
//    public void testSubmitOtherOrgInfoAdmint_case0001()
//    {
//        final String STR_METHOD_NAME = "testSubmitOtherOrgInfoAdmint_case01()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.impl = new WEB3SrvRegiServiceUseApplicationServiceImpl();
//
//        try
//        {
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
//            l_branchParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
//            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
//                new OtherOrgInfoAdminParams();
//            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
//            l_otherOrgInfoAdminParams.setSequenceNumber(123);
//            l_otherOrgInfoAdminParams.setSrvDiv("1");
//            l_otherOrgInfoAdminParams.setId("1");
//            l_otherOrgInfoAdminParams.setPassword("1");
//            l_otherOrgInfoAdminParams.setStatus("9");
//            l_otherOrgInfoAdminParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
//            l_otherOrgInfoAdminParams.setBranchCode(l_mainAccountParams.getBranchCode());
//            l_otherOrgInfoAdminParams.setAccountCode(l_mainAccountParams.getAccountCode());
//            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
//            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdater("1");
//            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
//            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
//
//            WEB3GentradeSubAccount l_gentradeSubAccount =
//                new WEB3GentradeSubAccount(l_subAccountParams);
//            String l_strSrvDiv = "1";
//            Timestamp l_tsTrialEndDate = new Timestamp(new Date().getTime());
//            boolean l_blnCreateDiv = true;
//
//            Method l_method =
//                WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
//                    "submitOtherOrgInfoAdmint",
//                    new Class[] {
//                        WEB3GentradeSubAccount.class,
//                        String.class,
//                        Timestamp.class,
//                        boolean.class});
//            l_method.setAccessible(true);
//            l_method.invoke(
//                this.impl,
//                new Object[] {
//                    l_gentradeSubAccount,
//                    l_strSrvDiv,
//                    l_tsTrialEndDate,
//                    new Boolean(l_blnCreateDiv)});
//
//            fail();
//        }
//        catch (NoSuchMethodException l_exNSME)
//        {
//            log.debug(STR_METHOD_NAME,l_exNSME);
//            fail();
//        }
//        catch (IllegalAccessException l_exIAE)
//        {
//            log.debug(STR_METHOD_NAME,l_exIAE);
//            fail();
//        }
//        catch (InvocationTargetException l_exITE)
//        {
//            log.debug(STR_METHOD_NAME,l_exITE);
//            assertEquals(
//                IllegalSessionStateException.class,
//                l_exITE.getTargetException().getClass());
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            log.debug(STR_METHOD_NAME,l_exBE);
//            fail();
//        }
//        finally
//        {
//            log.entering(TEST_END + STR_METHOD_NAME);
//        }
//    }
//
//    /**
//     *
//     */
//    public void testSubmitOtherOrgInfoAdmint_case0002()
//    {
//        final String STR_METHOD_NAME = "testSubmitOtherOrgInfoAdmint_case01()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        this.impl = new WEB3SrvRegiServiceUseApplicationServiceImpl();
//
//        try
//        {
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
//            l_branchParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
//            l_subAccountParams.setBranchId(l_branchParams.getBranchId());
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
//                new OtherOrgInfoAdminParams();
//            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
//            l_otherOrgInfoAdminParams.setSequenceNumber(123);
//            l_otherOrgInfoAdminParams.setSrvDiv("1");
//            l_otherOrgInfoAdminParams.setId("1");
//            l_otherOrgInfoAdminParams.setPassword("1");
//            l_otherOrgInfoAdminParams.setStatus("0");
//            l_otherOrgInfoAdminParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
//            l_otherOrgInfoAdminParams.setBranchCode(l_mainAccountParams.getBranchCode());
//            l_otherOrgInfoAdminParams.setAccountCode(l_mainAccountParams.getAccountCode());
//            l_otherOrgInfoAdminParams.setAppliStartDate(new Date());
//            l_otherOrgInfoAdminParams.setAppliEndDate(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdater("1");
//            l_otherOrgInfoAdminParams.setCreatedTimestamp(new Date());
//            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(new Date());
//            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
//
//            WEB3GentradeSubAccount l_gentradeSubAccount =
//                new WEB3GentradeSubAccount(l_subAccountParams);
//            String l_strSrvDiv = "1";
//            Timestamp l_tsTrialEndDate = new Timestamp(new Date().getTime());
//            boolean l_blnCreateDiv = false;
//
//            Method l_method =
//                WEB3SrvRegiServiceUseApplicationServiceImpl.class.getDeclaredMethod(
//                    "submitOtherOrgInfoAdmint",
//                    new Class[] {
//                        WEB3GentradeSubAccount.class,
//                        String.class,
//                        Timestamp.class,
//                        boolean.class});
//            l_method.setAccessible(true);
//            l_method.invoke(
//                this.impl,
//                new Object[] {
//                    l_gentradeSubAccount,
//                    l_strSrvDiv,
//                    l_tsTrialEndDate,
//                    new Boolean(l_blnCreateDiv)});
//
//            fail();
//        }
//        catch (NoSuchMethodException l_exNSME)
//        {
//            log.debug(STR_METHOD_NAME,l_exNSME);
//            fail();
//        }
//        catch (IllegalAccessException l_exIAE)
//        {
//            log.debug(STR_METHOD_NAME,l_exIAE);
//            fail();
//        }
//        catch (InvocationTargetException l_exITE)
//        {
//            log.debug(STR_METHOD_NAME,l_exITE);
//            assertEquals(
//                IllegalSessionStateException.class,
//                l_exITE.getTargetException().getClass());
//        }
//        catch (WEB3BaseException l_exBE)
//        {
//            log.debug(STR_METHOD_NAME,l_exBE);
//            fail();
//        }
//        finally
//        {
//            log.entering(TEST_END + STR_METHOD_NAME);
//        }
//    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {  
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(SrvRegiMasterRow.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(SrvRegiApplicationRow.TYPE);
            SrvRegiApplicationParams l_srvRegiApplicationParams = TestDBUtility.getSrvRegiApplicationRow();
            l_srvRegiApplicationParams.setInstitutionCode("0D");
            l_srvRegiApplicationParams.setBranchCode("381");
            l_srvRegiApplicationParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_srvRegiApplicationParams); 
            
            TestDBUtility.deleteAll(SrvRegiSetupRow.TYPE);
            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setLotDiv("0");
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
                new WEB3GentradeSrvRegiApplication(TestDBUtility.getSrvRegiApplicationParams());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getServiceRegist",
                    new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
                        boolean.class},
                        l_gentradeSrvRegiApplication);
            
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "calcAppliEndDate",
                    new Class[] {String.class, String.class, String.class, String.class, Timestamp.class,
                        long.class, String.class},
                    l_tsAppliyDate);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
                    "getInitializeAppliDiv",
                    new Class[] {String.class, String.class, String.class, String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "institutionID");
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getInstitution",
                    new Class[] {long.class},
                    l_institution);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getBranch",
                    new Class[] {long.class},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
                    "updateSrvApplyAttribute", new Class[]
                    { String.class, String.class, String.class, String.class },
                    new Integer(1));
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private List getSearchResult(String l_strInstitutionCode,String l_strBranchCode,String l_strMainAccountCode,String l_strServiceDiv)
    {
        final String STR_METHOD_NAME = "getSearchResult()";
        log.entering(TEST_START + STR_METHOD_NAME);
        List lisSearchResult = null;
        try
        {
            List l_lisWheres = new ArrayList();
            l_lisWheres.add(l_strInstitutionCode);
            l_lisWheres.add(l_strBranchCode);
            l_lisWheres.add(l_strMainAccountCode);
            l_lisWheres.add(l_strServiceDiv);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and　@branch_code = ? ");
            l_sbWhere.append(" and　@account_code = ? ");
            l_sbWhere.append(" and　@srv_div = ? ");
            
            Object[] l_objWheres = new Object[l_lisWheres.size()];
            l_lisWheres.toArray(l_objWheres);
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            lisSearchResult = l_queryProcesser.doFindAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
        return lisSearchResult;
    }

    class WEB3SrvRegiServiceUseApplicationServiceImplForTest extends WEB3SrvRegiServiceUseApplicationServiceImpl
    {
        public Trader getTrader()
        {
            if(l_blnIsSetTrader)
            {
                return new TraderForTest();
            }
            return null;
        }

        public String getLoginChannel()
        {
            return null;
        }
        
        public Integer updateSrvApplyAttribute(String l_strInstitutionCode, String l_strBranchCode,
                String l_strMainAccountCode, String l_strServiceDiv) throws WEB3BaseException
        {
            return null;
        }
    }
    
    private class WEB3GentradeSubAccountForTest extends WEB3GentradeSubAccount
    {

        public WEB3GentradeSubAccountForTest(SubAccountRow l_subAcctRow)
        {
            super(l_subAcctRow);
        }
        
        public Institution getInstitution()
        {
            WEB3GentradeInstitution l_institution = null;
            try
            {
                l_institution = new WEB3GentradeInstitution("0D");
                return l_institution;
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_institution;
        }
        
        public MainAccount getMainAccount()
        {
            WEB3GentradeMainAccount l_mainAccountForTest = null;
            try
            {
                l_mainAccountForTest= new WEB3GentradeMainAccountForTest(333812512246L);
                return l_mainAccountForTest;
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_mainAccountForTest;
        }
    }
    
    private class WEB3GentradeMainAccountForTest extends WEB3GentradeMainAccount
    {

        public WEB3GentradeMainAccountForTest(long l_lngAccountLd) throws DataQueryException, DataNetworkException
        {
            super(l_lngAccountLd);
        }
        
        public Branch getBranch()
        {
            WEB3GentradeBranch l_branch = null;
            try
            {
                l_branch = new WEB3GentradeBranch(33381);
                return l_branch;
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_branch;
        }
        
        public String getAccountCode()
        {
            return "1234567";
        }
        
    }
    
    private class TraderForTest implements Trader
    {

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getTraderCode()
        {
            // TODO Auto-generated method stub
            return "7654321";
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public TraderTypeEnum getTraderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Branch getBranch()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

    public class LoginInfoTest extends LoginInfoImpl
    {
        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }
    }

}
@
