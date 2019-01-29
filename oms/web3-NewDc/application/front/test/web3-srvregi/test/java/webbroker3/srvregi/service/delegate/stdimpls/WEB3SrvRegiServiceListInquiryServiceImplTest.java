head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.52.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiServiceListInquiryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3SrvRegiServiceListInquiryServiceImplTest_execute.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/13 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiChargeRow;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.srvregi.message.WEB3SrvRegiNoLotteryGroup;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiServiceListInquiryServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3SrvRegiServiceListInquiryServiceImplTest.class);

    WEB3SrvRegiServiceListInquiryServiceImpl impl = new WEB3SrvRegiServiceListInquiryServiceImpl();

    public WEB3SrvRegiServiceListInquiryServiceImplTest(String arg0)
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

    public void testExecute01()
    {
        final String STR_METHOD_NAME = "testExecute01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getServiceRegist",
            new Class[] {String.class, String.class, String.class, String.class, long.class, boolean.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "1");
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            l_orderAcceptStatusParams.setOrderAcceptStatus("7");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setOfferingDiv("1");
            l_srvRegiMasterParams.setSrvStatus("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setSupplyDiv("1");
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(SrvRegiCommCondRow.TYPE);
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);

            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setInstitutionCode("0D");
            l_srvAppliAttributeParams.setBranchCode("381");
            l_srvAppliAttributeParams.setAccountCode("2512246");
            l_srvAppliAttributeParams.setAppliAttribute("1");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("3");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            WEB3SrvRegiReferenceResponse l_response = (WEB3SrvRegiReferenceResponse)impl.execute(new WEB3SrvRegiReferenceRequest());

            WEB3SrvRegiNoLotteryGroup l_group = l_response.noLotList[0];
//            assertEquals(l_group.serviceDiv, "1234");
//            assertEquals(l_group.serviceName, "SrvName");
//            assertEquals(l_group.serviceStatus, "1");
//            assertEquals(l_group.applyAbleSet, "0");
//            assertEquals(l_group.explainURL, "url");
//            assertNull(l_group.registDate);
//            assertNull(l_group.useLimitDate);
//            assertEquals(l_group.elePigeonDiv, false);
//            assertEquals(l_group.continuationDiv, false);
//            assertEquals(l_group.useAbleDiv, false);
//            assertEquals(l_group.chargeInfo.length, 0);
//            assertNull(l_group.trialDiv);
//            assertNull(l_group.trialPeriod);
//            assertEquals(l_group.applyAbleDiv, false);
//            assertEquals(l_group.noChargeAbleDiv, false);
//            assertEquals(l_group.applyAttributeDiv, "1");
//            assertEquals(l_group.freeAttributeApplyDiv, "1");
//
//            l_calendar.set(2007, 6-1, 7);
//            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            assertEquals(WEB3DateUtility.compareToDay(l_group.applyAttributePeriodFrom, l_tsAppliyDate), 0);
//
//            l_calendar.set(2007, 6-1, 16);
//            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            assertEquals(WEB3DateUtility.compareToDay(l_group.applyAttributePeriodTo, l_tsAppliyDate), 0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    /**
    サービス利用サービス一覧照会処理を行う
    提供形式＝2:(無料) && getサービス申込属性情報()＝nullの場合
     */
    public void testExecuteCase0001()
    {
        final String STR_METHOD_NAME = "testExecuteCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getServiceRegist",
            new Class[] {String.class, String.class, String.class, String.class, long.class, boolean.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "1");
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            l_orderAcceptStatusParams.setOrderAcceptStatus("7");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setOfferingDiv("1");
            l_srvRegiMasterParams.setSrvStatus("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setSupplyDiv("2");
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(SrvRegiCommCondRow.TYPE);
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("3");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            WEB3SrvRegiReferenceResponse l_response = (WEB3SrvRegiReferenceResponse)impl.execute(new WEB3SrvRegiReferenceRequest());

            assertEquals(l_response.noLotList.length, 0);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
    サービス利用サービス一覧照会処理を行う
    is手数料条件()=falseの場合
     */
    public void testExecuteCase0002()
    {
        final String STR_METHOD_NAME = "testExecuteCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getServiceRegist",
            new Class[] {String.class, String.class, String.class, String.class, long.class, boolean.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "1");
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            l_orderAcceptStatusParams.setOrderAcceptStatus("7");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setOfferingDiv("1");
            l_srvRegiMasterParams.setSrvStatus("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setSupplyDiv("2");
            l_srvRegiSetupParams.setFreeCoverageLength(2);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_processor.doDeleteAllQuery(SrvRegiCommCondRow.TYPE);
            l_processor.doDeleteAllQuery(SrvRegiChargeRow.TYPE);

            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setInstitutionCode("0D");
            l_srvAppliAttributeParams.setBranchCode("381");
            l_srvAppliAttributeParams.setAccountCode("2512246");
            l_srvAppliAttributeParams.setAppliAttribute("1");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("3");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            WEB3SrvRegiReferenceResponse l_response = (WEB3SrvRegiReferenceResponse)impl.execute(new WEB3SrvRegiReferenceRequest());
            
            WEB3SrvRegiNoLotteryGroup l_group = l_response.noLotList[0];
            assertEquals(l_group.serviceDiv, "1234");
            assertEquals(l_group.serviceName, "SrvName");
            assertEquals(l_group.serviceStatus, "1");
            assertEquals(l_group.applyAbleSet, "0");
            assertEquals(l_group.explainURL, "url");
            assertEquals(l_group.elePigeonDiv, false);
            assertEquals(l_group.continuationDiv, false);
            assertEquals(l_group.useAbleDiv, false);
            assertEquals(l_group.chargeInfo.length, 0);
            assertEquals(l_group.applyAbleDiv, false);
            assertEquals(l_group.noChargeAbleDiv, false);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
