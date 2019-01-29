head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3PvInfoPrivateInformationDetailServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PvInfoPrivateInformationDetailServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27 崔遠鵬 新規作成
                   2007/02/27 崔遠鵬 修正
                   2007/08/02 鄧鋒鋼 NO.086
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailRequest;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3PvInfoPrivateInformationDetailServiceImplTest extends TestBaseForMock
{
    private WEB3PvInfoPrivateInformationDetailServiceImpl l_pvInfoPrivateInformationDetailServiceImpl =
        new WEB3PvInfoPrivateInformationDetailServiceImplForTest();

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationDetailServiceImplTest.class); 


    
    public WEB3PvInfoPrivateInformationDetailServiceImplTest(String name)
    {
        super(name);
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

    public void testGetDetailedScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetDetailedScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[]{},
            new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.pvinfo.WEB3PvInfoDataManagerImpl",
            "getCommProductCodes",
            new Class[]{long.class},
            null);
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("00");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //データベースへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
            l_displayContentsParams.setDisplayContentsId(0L);
            l_displayContentsParams.setInstitutionCode("0D");
            l_displayContentsParams.setBranchCode("381");
            l_displayContentsParams.setConditionNo("1051");
            l_displayContentsParams.setDisplayTitle("0");
            l_displayContentsParams.setDisplayMessage("0");
            l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
            l_displayContentsParams.setEffectiveFlag("0");
            l_displayContentsParams.setDisplayDevice("0");
            l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_displayContentsParams);

            CommCampaignAccHistoryParams l_commCampaignAccHistoryParams =new CommCampaignAccHistoryParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_commCampaignAccHistoryParams.getRowType());

            SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
            l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());
            l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            //実際メソッドをコール
            WEB3PvInfoPrivateInformationDetailRequest l_pvInfoPrivateInformationDetailRequest =
                new WEB3PvInfoPrivateInformationDetailRequestForTest();
            l_pvInfoPrivateInformationDetailRequest.displayContentsId = "0";
            WEB3PvInfoPrivateInformationDetailResponse l_pvInfoPrivateInformationDetailResponse =
                l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(
                    l_pvInfoPrivateInformationDetailRequest);
            //比較
            assertNull(l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDetailedScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetDetailedScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[]{},
            new Long(333812512246L));
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("00");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //データベースへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
            l_displayContentsParams.setDisplayContentsId(0L);
            l_displayContentsParams.setInstitutionCode("0D");
            l_displayContentsParams.setBranchCode("381");
            l_displayContentsParams.setConditionNo("1051");
            l_displayContentsParams.setDisplayTitle("0");
            l_displayContentsParams.setDisplayMessage("0");
            l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
            l_displayContentsParams.setEffectiveFlag("0");
            l_displayContentsParams.setDisplayDevice("0");
            l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_displayContentsParams);

            CommCampaignAccHistoryParams l_commCampaignAccHistoryParams1 =new CommCampaignAccHistoryParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_commCampaignAccHistoryParams1.getRowType());

            l_commCampaignAccHistoryParams1 = TestDBUtility.getCommCampaignAccHistoryRow();
            l_commCampaignAccHistoryParams1.setAccountId(333812512246L);
            l_commCampaignAccHistoryParams1.setCommCampaignName("asdf101");
            l_commCampaignAccHistoryParams1.setAppliStartDate(WEB3DateUtility.getDate("20070207","yyyyMMdd"));
            l_commCampaignAccHistoryParams1.setAppliEndDate(WEB3DateUtility.getDate("20070206","yyyyMMdd"));
            l_commCampaignAccHistoryParams1.setValidDiv("1");
            l_commCampaignAccHistoryParams1.setRegistType("1");
            l_commCampaignAccHistoryParams1.setAccountChargeRatio(51.123000D);
            TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams1);

            CommCampaignAccHistoryParams l_commCampaignAccHistoryParams2 =TestDBUtility.getCommCampaignAccHistoryRow();
            l_commCampaignAccHistoryParams2.setAccountId(333812512246L);
            l_commCampaignAccHistoryParams2.setCampaignId(10010010019L);
            l_commCampaignAccHistoryParams2.setAppliStartDate(WEB3DateUtility.getDate("20070207","yyyyMMdd"));
            l_commCampaignAccHistoryParams2.setAppliEndDate(WEB3DateUtility.getDate("20070206","yyyyMMdd"));
            l_commCampaignAccHistoryParams2.setCommCampaignName("asdf102");
            l_commCampaignAccHistoryParams2.setValidDiv("1");
            l_commCampaignAccHistoryParams2.setRegistType("1");
            l_commCampaignAccHistoryParams2.setAccountChargeRatio(51.123000D);
            TestDBUtility.insertWithDel(l_commCampaignAccHistoryParams2);

            SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
            l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());
            l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            CommCampaignProductMstParams l_commCampaignProductMstParams1 = new CommCampaignProductMstParams();
            l_processor.doDeleteAllQuery(l_commCampaignProductMstParams1.getRowType());
            l_commCampaignProductMstParams1 = TestDBUtility.getCommCampaignProductMstRow();
            l_commCampaignProductMstParams1.setCampaignId(10010010018L);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams1);

            CommCampaignProductMstParams l_commCampaignProductMstParams2 = TestDBUtility.getCommCampaignProductMstRow();
            l_commCampaignProductMstParams2.setCampaignId(10010010019L);
            TestDBUtility.insertWithDel(l_commCampaignProductMstParams2);

            //実際メソッドをコール
            WEB3PvInfoPrivateInformationDetailRequest l_pvInfoPrivateInformationDetailRequest =
                new WEB3PvInfoPrivateInformationDetailRequestForTest();
            l_pvInfoPrivateInformationDetailRequest.displayContentsId = "0";
            WEB3PvInfoPrivateInformationDetailResponse l_pvInfoPrivateInformationDetailResponse =
                l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(
                    l_pvInfoPrivateInformationDetailRequest);
            assertEquals(2,l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits.length);
            //比較
            assertEquals("asdf101", l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[0].campaignName);
            assertEquals("10", l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[0].commodityCodeList[0]);
            assertEquals("48.877", l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[0].discountRate);
            Calendar l_canlendar = Calendar.getInstance();
            l_canlendar.set(Calendar.YEAR, 2007);
            l_canlendar.set(Calendar.MONTH, 1);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 7);
            Timestamp l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            assertEquals(0, WEB3DateUtility.compareToDay(l_timestamp, l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[0].applyStartDate));
            l_canlendar.set(Calendar.DAY_OF_MONTH, 6);
            l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            assertEquals(0, WEB3DateUtility.compareToDay(l_timestamp, l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[0].applyEndDate));
            
            //比較
            assertEquals("asdf102", l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[1].campaignName);
            assertEquals("10", l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[1].commodityCodeList[0]);
            assertEquals("48.877", l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[1].discountRate);
            l_canlendar.set(Calendar.DAY_OF_MONTH, 7);
            l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            assertEquals(0, WEB3DateUtility.compareToDay(l_timestamp, l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[1].applyStartDate));
            l_canlendar.set(Calendar.DAY_OF_MONTH, 6);
            l_timestamp = new Timestamp(l_canlendar.getTimeInMillis());
            assertEquals(0, WEB3DateUtility.compareToDay(l_timestamp, l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits[1].applyEndDate));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetDetailedScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetDetailedScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[]{},
            new Long(333812512246L));
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("00");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //データベースへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
            l_displayContentsParams.setDisplayContentsId(0L);
            l_displayContentsParams.setInstitutionCode("0D");
            l_displayContentsParams.setBranchCode("381");
            l_displayContentsParams.setConditionNo("1050");
            l_displayContentsParams.setDisplayTitle("0");
            l_displayContentsParams.setDisplayMessage("0");
            l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
            l_displayContentsParams.setEffectiveFlag("0");
            l_displayContentsParams.setDisplayDevice("0");
            l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_displayContentsParams);

            //実際メソッドをコール
            WEB3PvInfoPrivateInformationDetailRequest l_pvInfoPrivateInformationDetailRequest =
                new WEB3PvInfoPrivateInformationDetailRequestForTest();
            l_pvInfoPrivateInformationDetailRequest.displayContentsId = "0";
            WEB3PvInfoPrivateInformationDetailResponse l_pvInfoPrivateInformationDetailResponse =
                l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(
                    l_pvInfoPrivateInformationDetailRequest);
            //比較
            assertNull(l_pvInfoPrivateInformationDetailResponse.commissionCampaignUnits);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

  public void testGetDetailedScreen_C0004()
  {
//      final String STR_METHOD_NAME = "testGetDetailedScreen_C0004()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//          "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//          "getAccountId",
//          new Class[]{},
//          new Long(333812512246L));
//     
//
//      WEB3TPAccountBalanceInfo l_accountBalanceInfo = new WEB3TPAccountBalanceInfo();
//      l_accountBalanceInfo.accountBalancePaymentStatus = "1000";
//      l_accountBalanceInfo.accountBalanceStatus1 = "10001";
//      l_accountBalanceInfo.accountBalanceStatus2 = "10002";
//      l_accountBalanceInfo.accountBalanceStatus3 = "10003";
//      l_accountBalanceInfo.accountBalanceStatus4 = "10004";
//
//      TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//              "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
//              "getDepositInfo", new Class[]
//              { MainAccount.class },
//              l_accountBalanceInfo);
//      try
//      {
//          //スタティックメソッドの準備
//          WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//          l_clendarContext.setOrderAcceptTransaction("07");
//          l_clendarContext.setInstitutionCode("0D");
//          l_clendarContext.setBranchCode("381");
//          l_clendarContext.setTradingTimeType("00");
//          l_clendarContext.setOrderAcceptProduct("01");
//          l_clendarContext.setOrderAcceptTransaction("07");
//          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//
//          //データベースへデータをインサート
//          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//          TestDBUtility.insertWithDel(l_mainAccountParams);
//
//          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//          l_subAccountParams.setAccountId(333812512246L);
//          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//          TestDBUtility.insertWithDel(l_subAccountParams);
//
//          OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//          l_orderAcceptStatusParams.setOrderAccTransaction("07");
//          TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//          DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
//          l_displayContentsParams.setDisplayContentsId(0L);
//          l_displayContentsParams.setInstitutionCode("0D");
//          l_displayContentsParams.setBranchCode("381");
//          l_displayContentsParams.setConditionNo("1052");
//          l_displayContentsParams.setDisplayTitle("0");
//          l_displayContentsParams.setDisplayMessage("0");
//          l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
//          l_displayContentsParams.setEffectiveFlag("0");
//          l_displayContentsParams.setDisplayDevice("0");
//          l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//          l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//          TestDBUtility.insertWithDel(l_displayContentsParams);
//
//          CommCampaignAccHistoryParams l_commCampaignAccHistoryParams =new CommCampaignAccHistoryParams();
//          QueryProcessor l_processor = Processors.getDefaultProcessor();
//          l_processor.doDeleteAllQuery(l_commCampaignAccHistoryParams.getRowType());
//
//          SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
//          l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());
//          l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
//          TestDBUtility.insertWithDel(l_systemPreferencesParams);
//
//          //実際メソッドをコール
//          WEB3PvInfoPrivateInformationDetailRequest l_pvInfoPrivateInformationDetailRequest =
//              new WEB3PvInfoPrivateInformationDetailRequestForTest();
//          l_pvInfoPrivateInformationDetailRequest.displayContentsId = "0";
//          WEB3PvInfoPrivateInformationDetailResponse l_response =
//              l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(
//                  l_pvInfoPrivateInformationDetailRequest);
//
//          //比較
////          assertEquals("1000",l_response.accountBalanceUnit.accountBalancePaymentStatus);
////          assertEquals("10001",l_response.accountBalanceUnit.accountBalanceStatus1);
////          assertEquals("10002",l_response.accountBalanceUnit.accountBalanceStatus2);
////          assertEquals("10003",l_response.accountBalanceUnit.accountBalanceStatus3);
////          assertEquals("10004",l_response.accountBalanceUnit.accountBalanceStatus4);
//      }
//      catch (Exception l_ex)
//      {
//          log.error(STR_METHOD_NAME, l_ex);
//          fail();
//      }
//      log.exiting(TEST_END + STR_METHOD_NAME);
  }

  public void testGetDetailedScreen_C0005()
  {
//      final String STR_METHOD_NAME = "testGetDetailedScreen_C0005()";
//      log.entering(TEST_START + STR_METHOD_NAME);
//
//      TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//          "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//          "getAccountId",
//          new Class[]{},
//          new Long(333812512246L));
//
//      WEB3TPSecondMarginInfo l_secondMarginInfo = new WEB3TPSecondMarginInfo();
//      
//      l_secondMarginInfo.secondMarginDemandStatus1 = "2001";
//      l_secondMarginInfo.secondMarginDemandStatus2 = "2002";
//     
//      TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//              "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
//              "getSecondLevelAddInfo", new Class[]
//              { MainAccount.class },
//              l_secondMarginInfo);
//
//      try
//      {
//          //スタティックメソッドの準備
//          WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//          l_clendarContext.setOrderAcceptTransaction("07");
//          l_clendarContext.setInstitutionCode("0D");
//          l_clendarContext.setBranchCode("381");
//          l_clendarContext.setTradingTimeType("00");
//          l_clendarContext.setOrderAcceptProduct("01");
//          l_clendarContext.setOrderAcceptTransaction("07");
//          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//
//          //データベースへデータをインサート
//          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//          TestDBUtility.insertWithDel(l_mainAccountParams);
//
//          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//          l_subAccountParams.setAccountId(333812512246L);
//          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//          TestDBUtility.insertWithDel(l_subAccountParams);
//
//          OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//          l_orderAcceptStatusParams.setOrderAccTransaction("07");
//          TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//          DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
//          l_displayContentsParams.setDisplayContentsId(0L);
//          l_displayContentsParams.setInstitutionCode("0D");
//          l_displayContentsParams.setBranchCode("381");
//          l_displayContentsParams.setConditionNo("1053");
//          l_displayContentsParams.setDisplayTitle("0");
//          l_displayContentsParams.setDisplayMessage("0");
//          l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
//          l_displayContentsParams.setEffectiveFlag("0");
//          l_displayContentsParams.setDisplayDevice("0");
//          l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//          l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//          TestDBUtility.insertWithDel(l_displayContentsParams);
//
//          CommCampaignAccHistoryParams l_commCampaignAccHistoryParams =new CommCampaignAccHistoryParams();
//          QueryProcessor l_processor = Processors.getDefaultProcessor();
//          l_processor.doDeleteAllQuery(l_commCampaignAccHistoryParams.getRowType());
//
//          SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
//          l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());
//          l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
//          TestDBUtility.insertWithDel(l_systemPreferencesParams);
//
//          //実際メソッドをコール
//          WEB3PvInfoPrivateInformationDetailRequest l_pvInfoPrivateInformationDetailRequest =
//              new WEB3PvInfoPrivateInformationDetailRequestForTest();
//          l_pvInfoPrivateInformationDetailRequest.displayContentsId = "0";
//          WEB3PvInfoPrivateInformationDetailResponse l_response =
//              l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(
//                  l_pvInfoPrivateInformationDetailRequest);
//          
//          WEB3MockObjectParamsValue l_paramsValue = 
//              TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                      "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
//                      "getSecondLevelAddInfo", 
//                      new Class[]{ MainAccount.class });
//          
//          //參數驗證
//          assertEquals(333812512246L, ((MainAccount)l_paramsValue.getFirstCalled()[0]).getAccountId());
//
//          
//          //比較
////          assertEquals("2001",l_response.additionalDepositUnit.level2AdditionalDepositStatus1);
////          assertEquals("2002",l_response.additionalDepositUnit.level2AdditionalDepositStatus2);
//      }
//      catch (Exception l_ex)
//      {
//          log.error(STR_METHOD_NAME, l_ex);
//          fail();
//      }
//      log.exiting(TEST_END + STR_METHOD_NAME);
  }
  
    public void testCreateInfoAccountBalanceUnit_case0001()
    {}

    public void testCreateInfoAdditionalDepositUnitcase_0001()
    {}


    private class WEB3PvInfoPrivateInformationDetailRequestForTest extends WEB3PvInfoPrivateInformationDetailRequest
    {
        public void validate() throws WEB3BusinessLayerException
        {
            return;
        }
        
//        protected  WEB3PvInfoAccountBalanceUnit createInfoAccountBalanceUnit(WEB3TPAccountBalanceInfo l_AccountBalanceInfo)
//        {
//        		WEB3PvInfoAccountBalanceUnit l_infoAccountBalanceUnit = new WEB3PvInfoAccountBalanceUnit();
//            	l_infoAccountBalanceUnit.accountBalancePaymentStatus = l_AccountBalanceInfo.accountBalancePaymentStatus;
//            	l_infoAccountBalanceUnit.accountBalanceStatus1 = l_AccountBalanceInfo.accountBalanceStatus1;
//            	l_infoAccountBalanceUnit.accountBalanceStatus2 = l_AccountBalanceInfo.accountBalanceStatus2;
//            	l_infoAccountBalanceUnit.accountBalanceStatus3 = l_AccountBalanceInfo.accountBalanceStatus3;
//            	l_infoAccountBalanceUnit.accountBalanceStatus4 = l_AccountBalanceInfo.accountBalanceStatus4;
//            	return l_infoAccountBalanceUnit;
//        }
//        
//        protected WEB3PvInfoAdditionalDepositUnit createInfoAdditionalDepositUnit(WEB3TPSecondMarginInfo l_secondMarginInfo)
//        {
//        	WEB3PvInfoAdditionalDepositUnit l_infoAdditionalDepositUnit = new WEB3PvInfoAdditionalDepositUnit();
//        	l_infoAdditionalDepositUnit.level2AdditionalDepositStatus1 = l_secondMarginInfo.secondMarginDemandStatus1;
//        	l_infoAdditionalDepositUnit.level2AdditionalDepositStatus2 = l_secondMarginInfo.secondMarginDemandStatus2;
//        	return l_infoAdditionalDepositUnit;
//        }
    }

    private class WEB3PvInfoPrivateInformationDetailServiceImplForTest extends WEB3PvInfoPrivateInformationDetailServiceImpl
    {
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
    }

    /*
     * 表示内容Params.表示条件番号 == "1054
     */
    public void testGetDetailedScreen_C0006()
    {
       final String STR_METHOD_NAME = "testGetDetailedScreen_C0006()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
         //スタティックメソッドの準備
         WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
         l_clendarContext.setOrderAcceptTransaction("07");
         l_clendarContext.setInstitutionCode("0D");
         l_clendarContext.setBranchCode("381");
         l_clendarContext.setTradingTimeType("00");
         l_clendarContext.setOrderAcceptProduct("01");
         l_clendarContext.setOrderAcceptTransaction("07");
         ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

         //データベースへデータをインサート
         MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
         TestDBUtility.insertWithDel(l_mainAccountParams);

         SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
         l_subAccountParams.setAccountId(333812512246L);
         l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
         TestDBUtility.insertWithDel(l_subAccountParams);

         OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
         l_orderAcceptStatusParams.setOrderAccTransaction("07");
         TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

         DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
         l_displayContentsParams.setDisplayContentsId(0L);
         l_displayContentsParams.setInstitutionCode("0D");
         l_displayContentsParams.setBranchCode("381");
         l_displayContentsParams.setConditionNo("1054");
         l_displayContentsParams.setDisplayTitle("0");
         l_displayContentsParams.setDisplayMessage("0");
         l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
         l_displayContentsParams.setEffectiveFlag("0");
         l_displayContentsParams.setDisplayDevice("0");
         l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
         l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
         TestDBUtility.insertWithDel(l_displayContentsParams);

         CommCampaignAccHistoryParams l_commCampaignAccHistoryParams =new CommCampaignAccHistoryParams();
         QueryProcessor l_processor = Processors.getDefaultProcessor();
         l_processor.doDeleteAllQuery(l_commCampaignAccHistoryParams.getRowType());

         SystemPreferencesParams l_systemPreferencesParams = new SystemPreferencesParams();
         l_processor.doDeleteAllQuery(l_systemPreferencesParams.getRowType());
         l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
         TestDBUtility.insertWithDel(l_systemPreferencesParams);

         WEB3TPShortfallOccurInfo l_tPShortfallGenerationInfo = new WEB3TPShortfallOccurInfo();
         //プロパティセット
         //不足金発生表示情報オブジェクトにプロパティをセットする。


         //保証金自動振替後判定フラグ = get不足金発生情報()の戻り値.保証金自動振替後判定フラグ
        l_tPShortfallGenerationInfo.depositAutoTransferDivFlag=true;

         //期日(T+0) = get不足金発生情報()の戻り値.期日(T+0)
        l_tPShortfallGenerationInfo.closeDate0 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+1) = get不足金発生情報()の戻り値.期日(T+1)
        l_tPShortfallGenerationInfo.closeDate1 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+2) = get不足金発生情報()の戻り値.期日(T+2)
        l_tPShortfallGenerationInfo.closeDate2 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+3) = get不足金発生情報()の戻り値.期日(T+3)
         l_tPShortfallGenerationInfo.closeDate3 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+4) = get不足金発生情報()の戻り値.期日(T+4)
         l_tPShortfallGenerationInfo.closeDate4 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+5) = get不足金発生情報()の戻り値.期日(T+5)
         l_tPShortfallGenerationInfo.closeDate5 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //必要入金額(T+0) = get不足金発生情報()の戻り値.必要入金額(T+0)
         l_tPShortfallGenerationInfo.requiredPayAmt0 = 1.0;

         //必要入金額(T+1) = get不足金発生情報()の戻り値.必要入金額(T+1)
         l_tPShortfallGenerationInfo.requiredPayAmt1 = 1.0;

         //必要入金額(T+2) = get不足金発生情報()の戻り値.必要入金額(T+2)
         l_tPShortfallGenerationInfo.requiredPayAmt2 = 1.0;

         //必要入金額(T+3) = get不足金発生情報()の戻り値.必要入金額(T+3)
         l_tPShortfallGenerationInfo.requiredPayAmt3  = 1.0;

         //必要入金額(T+4) = get不足金発生情報()の戻り値.必要入金額(T+4)
         l_tPShortfallGenerationInfo.requiredPayAmt4  = 1.0;

         //必要入金額(T+5) = get不足金発生情報()の戻り値.必要入金額(T+5)
         l_tPShortfallGenerationInfo.requiredPayAmt5  = 1.0;

         //精算額(T+0) = get不足金発生情報()の戻り値.精算額(T+0)
         l_tPShortfallGenerationInfo.adjustedAmt0  = 1.0;

         //精算額(T+1) = get不足金発生情報()の戻り値.精算額(T+1)
         l_tPShortfallGenerationInfo.adjustedAmt1  = 1.0;

         //日計り拘束金(T+0) = get不足金発生情報()の戻り値.日計り拘束金(T+0)
         l_tPShortfallGenerationInfo.dayTradeRestraint0  = 1.0;

         //日計り拘束金(T+1) = get不足金発生情報()の戻り値.日計り拘束金(T+1)
         l_tPShortfallGenerationInfo.dayTradeRestraint1 = 1.0;

         //保証金からの振替額(T+0) = get不足金発生情報()の戻り値.保証金からの振替額(T+0)
         l_tPShortfallGenerationInfo.transferFromMarginDeposit0  = 1.0;

         //保証金からの振替額(T+1) = get不足金発生情報()の戻り値.保証金からの振替額(T+1)
         l_tPShortfallGenerationInfo.transferFromMarginDeposit1  = 1.0;
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                 "getShortfallGenerationInfo",
 				new Class[] { MainAccount.class },
 				l_tPShortfallGenerationInfo);

         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                 "getAccountId",
                 new Class[] {},
                 new Long(333812512246L)); 

         WEB3PvInfoPrivateInformationDetailServiceImpl l_impl= new WEB3PvInfoPrivateInformationDetailServiceImpl();
         //実際メソッドをコール
         WEB3PvInfoPrivateInformationDetailRequest l_request = new WEB3PvInfoPrivateInformationDetailRequest();
         l_request.displayContentsId = "0";

         WEB3PvInfoPrivateInformationDetailResponse l_response =
             l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(l_request);
         assertEquals("0",l_response.displayContentsId);
         assertTrue(l_response.shortfallGenerationInfo.autoTransferAfterJudgeFlag);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate0);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate1);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate2);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate3);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate4);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate5);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt0);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt1);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt2);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt3);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt4);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt5);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.adjustedAmt0);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.adjustedAmt1);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.dayTradeRestraint0);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.dayTradeRestraint1);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
       }
       catch (Exception l_ex)
       {
         log.error(STR_METHOD_NAME, l_ex);
         fail();
       }
      log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 表示内容Params.表示条件番号 == "1055
     */
    public void testGetDetailedScreen_C0007()
    {
       final String STR_METHOD_NAME = "testGetDetailedScreen_C0007()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {

           DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
           l_displayContentsParams.setDisplayContentsId(0L);
           l_displayContentsParams.setInstitutionCode("0D");
           l_displayContentsParams.setBranchCode("381");
           l_displayContentsParams.setConditionNo("1055");
           l_displayContentsParams.setDisplayTitle("0");
           l_displayContentsParams.setDisplayMessage("0");
           l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
           l_displayContentsParams.setEffectiveFlag("0");
           l_displayContentsParams.setDisplayDevice("0");
           l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
           l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
           TestDBUtility.insertWithDel(l_displayContentsParams);

         WEB3TPShortfallOccurInfo l_tPShortfallGenerationInfo = new WEB3TPShortfallOccurInfo();
         //プロパティセット
         //不足金発生表示情報オブジェクトにプロパティをセットする。
         //保証金自動振替後判定フラグ = get不足金発生情報()の戻り値.保証金自動振替後判定フラグ
        l_tPShortfallGenerationInfo.depositAutoTransferDivFlag=true;

         //期日(T+0) = get不足金発生情報()の戻り値.期日(T+0)
        l_tPShortfallGenerationInfo.closeDate0 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+1) = get不足金発生情報()の戻り値.期日(T+1)
        l_tPShortfallGenerationInfo.closeDate1 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+2) = get不足金発生情報()の戻り値.期日(T+2)
        l_tPShortfallGenerationInfo.closeDate2 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+3) = get不足金発生情報()の戻り値.期日(T+3)
         l_tPShortfallGenerationInfo.closeDate3 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+4) = get不足金発生情報()の戻り値.期日(T+4)
         l_tPShortfallGenerationInfo.closeDate4 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //期日(T+5) = get不足金発生情報()の戻り値.期日(T+5)
         l_tPShortfallGenerationInfo.closeDate5 = WEB3DateUtility.toDay(new Date(2007,8,4));

         //必要入金額(T+0) = get不足金発生情報()の戻り値.必要入金額(T+0)
         l_tPShortfallGenerationInfo.requiredPayAmt0 = 1.0;

         //必要入金額(T+1) = get不足金発生情報()の戻り値.必要入金額(T+1)
         l_tPShortfallGenerationInfo.requiredPayAmt1 = 1.0;

         //必要入金額(T+2) = get不足金発生情報()の戻り値.必要入金額(T+2)
         l_tPShortfallGenerationInfo.requiredPayAmt2 = 1.0;

         //必要入金額(T+3) = get不足金発生情報()の戻り値.必要入金額(T+3)
         l_tPShortfallGenerationInfo.requiredPayAmt3  = 1.0;

         //必要入金額(T+4) = get不足金発生情報()の戻り値.必要入金額(T+4)
         l_tPShortfallGenerationInfo.requiredPayAmt4  = 1.0;

         //必要入金額(T+5) = get不足金発生情報()の戻り値.必要入金額(T+5)
         l_tPShortfallGenerationInfo.requiredPayAmt5  = 1.0;

         //精算額(T+0) = get不足金発生情報()の戻り値.精算額(T+0)
         l_tPShortfallGenerationInfo.adjustedAmt0  = 1.0;

         //精算額(T+1) = get不足金発生情報()の戻り値.精算額(T+1)
         l_tPShortfallGenerationInfo.adjustedAmt1  = 1.0;

         //日計り拘束金(T+0) = get不足金発生情報()の戻り値.日計り拘束金(T+0)
         l_tPShortfallGenerationInfo.dayTradeRestraint0  = 1.0;

         //日計り拘束金(T+1) = get不足金発生情報()の戻り値.日計り拘束金(T+1)
         l_tPShortfallGenerationInfo.dayTradeRestraint1 = 1.0;

         //保証金からの振替額(T+0) = get不足金発生情報()の戻り値.保証金からの振替額(T+0)
         l_tPShortfallGenerationInfo.transferFromMarginDeposit0  = 1.0;

         //保証金からの振替額(T+1) = get不足金発生情報()の戻り値.保証金からの振替額(T+1)
         l_tPShortfallGenerationInfo.transferFromMarginDeposit1  = 1.0;
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                 "getShortfallGenerationInfo",
 				new Class[] { MainAccount.class },
 				l_tPShortfallGenerationInfo);

         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                 "getAccountId",
                 new Class[] {},
                 new Long(333812512246L)); 

         WEB3PvInfoPrivateInformationDetailServiceImpl l_impl= new WEB3PvInfoPrivateInformationDetailServiceImpl();
         //実際メソッドをコール
         WEB3PvInfoPrivateInformationDetailRequest l_request = new WEB3PvInfoPrivateInformationDetailRequest();
         l_request.displayContentsId = "0";

         WEB3PvInfoPrivateInformationDetailResponse l_response =
             l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(l_request);
         assertEquals("0",l_response.displayContentsId);
         assertTrue(l_response.shortfallGenerationInfo.autoTransferAfterJudgeFlag);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate0);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate1);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate2);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate3);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate4);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.shortfallGenerationInfo.closeDate5);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt0);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt1);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt2);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt3);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt4);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.requiredPayAmt5);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.adjustedAmt0);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.adjustedAmt1);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.dayTradeRestraint0);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.dayTradeRestraint1);
         
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.shortfallGenerationInfo.transferFromMarginDeposit0);
       }
       catch (Exception l_ex)
       {
         log.error(STR_METHOD_NAME, l_ex);
         fail();
       }
      log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 表示内容Params.表示条件番号 == "1056
     */
    public void testGetDetailedScreen_C0008()
    {
       final String STR_METHOD_NAME = "testGetDetailedScreen_C0008()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           TestDBUtility.deleteAll(MainAccountRow.TYPE);
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setAccountId(333812512246L);
           TestDBUtility.insertWithDel(l_mainAccountParams);
           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(333812512246L);
           l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
           TestDBUtility.insertWithDel(l_subAccountParams);

       	   TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
           TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
       	   TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

           WEB3TPShortfallOccurInfo l_shortfallGenerationInfo = new WEB3TPShortfallOccurInfo();
           l_shortfallGenerationInfo.depositAutoTransferDivFlag = true;
           l_shortfallGenerationInfo.debitAmount = 1.0;
           l_shortfallGenerationInfo.specialDebitAmount = 1.0;

           l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.toDay(new Date(2007,6,5));
           
           l_shortfallGenerationInfo.requiredPayAmt0 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt1 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt2 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt3 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt4 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt5 = 1.0;
           
           l_shortfallGenerationInfo.adjustedAmt0 = 1.0;
           l_shortfallGenerationInfo.adjustedAmt1 = 2.0;
           
           l_shortfallGenerationInfo.dayTradeRestraint0 = 1.0;
           l_shortfallGenerationInfo.dayTradeRestraint1 = 1.0;
           
           l_shortfallGenerationInfo.transferFromMarginDeposit0 = 1.0;
           l_shortfallGenerationInfo.transferFromMarginDeposit1 = 1.0;
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                   "getShortfallGenerationInfo",
   				new Class[] { MainAccount.class },
   				l_shortfallGenerationInfo);
           
          
           
           WEB3TPFirstAdddepositInfo l_irstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
           l_irstAdddepositInfo.firstDepositPassDay = 1;

           l_irstAdddepositInfo.firstDepositPassDayValid = 1;

           l_irstAdddepositInfo.firstDepositOccurredDate = WEB3DateUtility.toDay(new Date(2007,8,4));

           l_irstAdddepositInfo.firstMarginDepositRate = 1.0;

           l_irstAdddepositInfo.firstDepositRate =1.0;
           
           l_irstAdddepositInfo.firstDepositAmount = 1.0;
           
           l_irstAdddepositInfo.firstSettlement = 1.0;

           l_irstAdddepositInfo.firstMarginDepositInDe = 1.0;

           l_irstAdddepositInfo.firstMarginDepositInDeExpect = 1.0;

           l_irstAdddepositInfo.firstSettledContract = 1.0;

           l_irstAdddepositInfo.firstUncancelAmt = 1.0;

           l_irstAdddepositInfo.firstUncancelSettleRequiredAmt = 1.0;

           WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
           l_adddepositGenerationInfo.setAdddepositInfo(l_irstAdddepositInfo);
           l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                   "getAdddepositGenerationInfo",
   				new Class[] { MainAccount.class },
   				l_adddepositGenerationInfo);

           DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
           l_displayContentsParams.setDisplayContentsId(0L);
           l_displayContentsParams.setInstitutionCode("0D");
           l_displayContentsParams.setBranchCode("381");
           l_displayContentsParams.setConditionNo("1056");
           l_displayContentsParams.setDisplayTitle("0");
           l_displayContentsParams.setDisplayMessage("0");
           l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
           l_displayContentsParams.setEffectiveFlag("0");
           l_displayContentsParams.setDisplayDevice("0");
           l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
           l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
           TestDBUtility.insertWithDel(l_displayContentsParams);

         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                 "getAccountId",
                 new Class[] {},
                 new Long(333812512246L)); 

         WEB3PvInfoPrivateInformationDetailServiceImpl l_impl= new WEB3PvInfoPrivateInformationDetailServiceImpl();
         //実際メソッドをコール
         WEB3PvInfoPrivateInformationDetailRequest l_request = new WEB3PvInfoPrivateInformationDetailRequest();
         l_request.displayContentsId = "0";

         WEB3PvInfoPrivateInformationDetailResponse l_response =
             l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(l_request);

         assertEquals("0",l_response.displayContentsId);
         assertEquals(WEB3StringTypeUtility.formatNumber(1),l_response.firstAdditionalInfo.firstDepositPassDay);
         assertEquals(WEB3StringTypeUtility.formatNumber(1),l_response.firstAdditionalInfo.firstDepositPassDayValid);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,8,4)),l_response.firstAdditionalInfo.firstDepositOccurredDate);

         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstMarginDepositRate);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstDepositRate);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstDepositAmount);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstSettlement);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstMarginDepositInDeExpect);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstSettledContract);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstUncancelAmt);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.firstAdditionalInfo.firstUncancelSettleRequiredAmt);

       }
       catch (Exception l_ex)
       {
         log.error(STR_METHOD_NAME, l_ex);
         fail();
       }
      log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 表示内容Params.表示条件番号 == "1057
     */
    public void testGetDetailedScreen_C0009()
    {
       final String STR_METHOD_NAME = "testGetDetailedScreen_C0009()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           TestDBUtility.deleteAll(MainAccountRow.TYPE);
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setAccountId(333812512246L);
           TestDBUtility.insertWithDel(l_mainAccountParams);
           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(333812512246L);
           l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
           TestDBUtility.insertWithDel(l_subAccountParams);

       	   TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
           TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
       	   TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

           WEB3TPShortfallOccurInfo l_shortfallGenerationInfo = new WEB3TPShortfallOccurInfo();
           l_shortfallGenerationInfo.depositAutoTransferDivFlag = true;
           l_shortfallGenerationInfo.debitAmount = 1.0;
           l_shortfallGenerationInfo.specialDebitAmount = 1.0;

           l_shortfallGenerationInfo.closeDate0 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate1 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate2 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate3 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate4 = WEB3DateUtility.toDay(new Date(2007,6,5));
           l_shortfallGenerationInfo.closeDate5 = WEB3DateUtility.toDay(new Date(2007,6,5));
           
           l_shortfallGenerationInfo.requiredPayAmt0 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt1 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt2 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt3 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt4 = 1.0;
           l_shortfallGenerationInfo.requiredPayAmt5 = 1.0;
           
           l_shortfallGenerationInfo.adjustedAmt0 = 1.0;
           l_shortfallGenerationInfo.adjustedAmt1 = 2.0;
           
           l_shortfallGenerationInfo.dayTradeRestraint0 = 1.0;
           l_shortfallGenerationInfo.dayTradeRestraint1 = 1.0;
           
           l_shortfallGenerationInfo.transferFromMarginDeposit0 = 1.0;
           l_shortfallGenerationInfo.transferFromMarginDeposit1 = 1.0;
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                   "getShortfallGenerationInfo",
   				new Class[] { MainAccount.class },
   				l_shortfallGenerationInfo);

           WEB3TPSecondAdddepositInfo l_secondAdddepositInfo  = new WEB3TPSecondAdddepositInfo();
           
           l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.toDay(new Date(2007,5,4));

           l_secondAdddepositInfo.secondCloseDate1 = WEB3DateUtility.toDay(new Date(2007,5,4));

           l_secondAdddepositInfo.secondCloseDateExpect = WEB3DateUtility.toDay(new Date(2007,5,4));

           l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.toDay(new Date(2007,5,4));

           l_secondAdddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.toDay(new Date(2007,5,4));

           l_secondAdddepositInfo.secondDepositOccurredDateExpect = WEB3DateUtility.toDay(new Date(2007,5,4));

           l_secondAdddepositInfo.secondDepositRate = 1.0;

           l_secondAdddepositInfo.secondDepositBackRate = 1.0;

           l_secondAdddepositInfo.secondMarginDepositRate2 = 1.0;

           l_secondAdddepositInfo.secondMarginDepositRate1 = 1.0;

           l_secondAdddepositInfo.secondMarginDepositRateExpect = 1.0;

           l_secondAdddepositInfo.secondDepositNonPay = 1.0;

           l_secondAdddepositInfo.secondDeposit2 = 1.0;

           l_secondAdddepositInfo.secondDeposit1 = 1.0;

           l_secondAdddepositInfo.secondSettlementNonPay = 1.0;

           l_secondAdddepositInfo.secondSettlement2 = 1.0;

           l_secondAdddepositInfo.secondSettlement1 = 1.0;

           l_secondAdddepositInfo.secondMarginDepositInDe = 1.0;

           l_secondAdddepositInfo.secondMarginDepositInDeExpect = 1.0;

           l_secondAdddepositInfo.secondSettledContract = 1.0;

           l_secondAdddepositInfo.secondUncancelAmtNonPay = 1.0;

           l_secondAdddepositInfo.secondUncancelAmt2 = 1.0;

           l_secondAdddepositInfo.secondUncancelAmt1 = 1.0;

           l_secondAdddepositInfo.secondUncancelAmtExpect = 1.0;

           l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 1.0;

           l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 1.0;

           l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1 = 1.0;

           l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect = 1.0;

           WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
           l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
           l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                   "getAdddepositGenerationInfo",
   				new Class[] { MainAccount.class },
   				l_adddepositGenerationInfo);

           DisplayContentsParams l_displayContentsParams = new DisplayContentsParams();
           l_displayContentsParams.setDisplayContentsId(0L);
           l_displayContentsParams.setInstitutionCode("0D");
           l_displayContentsParams.setBranchCode("381");
           l_displayContentsParams.setConditionNo("1057");
           l_displayContentsParams.setDisplayTitle("0");
           l_displayContentsParams.setDisplayMessage("0");
           l_displayContentsParams.setLastUpdateTimeDisplayFlag("0");
           l_displayContentsParams.setEffectiveFlag("0");
           l_displayContentsParams.setDisplayDevice("0");
           l_displayContentsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
           l_displayContentsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
           TestDBUtility.insertWithDel(l_displayContentsParams);

         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                 "getAccountId",
                 new Class[] {},
                 new Long(333812512246L)); 

         WEB3PvInfoPrivateInformationDetailServiceImpl l_impl= new WEB3PvInfoPrivateInformationDetailServiceImpl();
         //実際メソッドをコール
         WEB3PvInfoPrivateInformationDetailRequest l_request = new WEB3PvInfoPrivateInformationDetailRequest();
         l_request.displayContentsId = "0";

         WEB3PvInfoPrivateInformationDetailResponse l_response =
             l_pvInfoPrivateInformationDetailServiceImpl.getDetailedScreen(l_request);

         assertEquals("0",l_response.displayContentsId);
         assertEquals("0",l_response.displayTitle);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,5,4)),l_response.secondAdditionalInfo.secondCloseDate2);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,5,4)),l_response.secondAdditionalInfo.secondCloseDate1);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,5,4)),l_response.secondAdditionalInfo.secondCloseDateExpect);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,5,4)),l_response.secondAdditionalInfo.secondDepositOccurredDate2);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,5,4)),l_response.secondAdditionalInfo.secondDepositOccurredDate1);
         assertEquals(WEB3DateUtility.toDay(new Date(2007,5,4)),l_response.secondAdditionalInfo.secondDepositOccurredDateExpect);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondDepositRate);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondDepositBackRate);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondMarginDepositRate2);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondMarginDepositRate1);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondMarginDepositRateExpect);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondDepositNonPay);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondDeposit2);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondDeposit1);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondSettlementNonPay);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondSettlement2);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondSettlement1);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondMarginDepositInDe);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondMarginDepositInDeExpect);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondSettledContract);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelAmtNonPay);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelAmt2);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelAmt1);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelAmtExpect);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmt2);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmt1);
         assertEquals(WEB3StringTypeUtility.formatNumber(1.0),l_response.secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect);
       }
       catch (Exception l_ex)
       {
         log.error(STR_METHOD_NAME, l_ex);
         fail();
       }
      log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
