head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IpoOrderManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告マネージャ(WEB3IpoOrderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 孟亜南(中訊) モデル  No.174,176
*/
package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3IpoStopDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IpoOrderManagerImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderManagerImplTest.class);
    
    public WEB3IpoOrderManagerImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("1");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setSessionType("4");

        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.insertWithDel(l_tradingTimeParams);
        
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        l_context.setInstitutionCode("0D");
        l_context.setMarketCode("SP");
        l_context.setBranchCode("381");
        l_context.setProductCode("0");
        l_context.setBizDateType("1");
        l_context.setTradingTimeType("26");
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext",
            l_context);
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,2);
        l_calendar.set(Calendar.DAY_OF_MONTH,14);
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
        Timestamp l_tsBizDate1 = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate1);
        
        CalendarParams l_calendarParams = new CalendarParams();
        l_calendarParams.setHoliday(l_tsBizDate1);
        l_calendarParams.setBizDateType("1");
        l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        TestDBUtility.deleteAll(CalendarRow.TYPE);
        TestDBUtility.insertWithDel(l_calendarParams);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 取得したレコード.プリファ@レンスの値 != 1：チェックする の場合
     */
    public void test_isPayAmountCheck_0001()
    {
        final String STR_METHOD_NAME = "test_isPayAmountCheck_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
//            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
//            l_branchPreferencesParams.setBranchId(10L);
//            l_branchPreferencesParams.setName("ipo.offer.tradingpower.check");
//            l_branchPreferencesParams.setNameSerialNo(1);
//            l_branchPreferencesParams.setValue("123");
//            l_branchPreferencesParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_branchPreferencesParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
        }
        catch (WEB3BaseException e1)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = new WEB3IpoOrderManagerImpl();
        
        try
        {
            boolean l_dbl = l_ipoOrderManagerImpl.isPayAmountCheck(10L);
            assertFalse(l_dbl);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 取得したレコード.プリファ@レンスの値 = 1：チェックする の場合
     */
    public void test_isPayAmountCheck_0002()
    {
        final String STR_METHOD_NAME = "test_isPayAmountCheck_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams.setBranchId(10L);
            l_branchPreferencesParams.setName("ipo.offer.tradingpower.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchPreferencesParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
        }
        catch (WEB3BaseException e1)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = new WEB3IpoOrderManagerImpl();
        
        try
        {
            boolean l_dbl = l_ipoOrderManagerImpl.isPayAmountCheck(10L);
            assertTrue(l_dbl);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate購入申込
     */
    public void test_validateOffer_0001() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "test_validateOffer_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getOtherTradingPowerForCheck",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class },
                    new Double(-1));
            
//            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
//            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
//            l_branchPreferencesParams.setBranchId(10L);
//            l_branchPreferencesParams.setName("ipo.offer.tradingpower.check");
//            l_branchPreferencesParams.setNameSerialNo(1);
//            l_branchPreferencesParams.setValue("1");
//            l_branchPreferencesParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_branchPreferencesParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(321);
            l_subAccountParams.setSubAccountId(256);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(321);
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams.setBranchId(123L);
            l_branchPreferencesParams.setName("ipo.offer.tradingpower.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchPreferencesParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(10L);
            l_ipoProductParams.setInstitutionCode("1");   
            l_ipoProductParams.setProductCode("2");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("1");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setPublicPrice(2.36);
            l_ipoProductParams.setPublicOfferingDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_ipoProductParams.setOfferStartDatetime(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_ipoProductParams.setLotSize(5);
            l_ipoProductParams.setIpoStop(WEB3IpoStopDef.DEFAULT);
            
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            
            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
            l_ipoOrderParams.setIpoOrderId(10L);
            l_ipoOrderParams.setIpoProductId(10L);  
            l_ipoOrderParams.setBranchId(123);
            l_ipoOrderParams.setAccountId(321);
            l_ipoOrderParams.setSubAccountId(256);
            l_ipoOrderParams.setLastOrderActionSerialNo(1);
            l_ipoOrderParams.setQuantity(3);
            l_ipoOrderParams.setLimitPrice(5);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.ELECTION);
            l_ipoOrderParams.setLotResultRetry(WEB3LotResultRetryDef.ELECTION);
            l_ipoOrderParams.setElectedQuantity(10);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
        }
        catch (WEB3BaseException e1)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = new WEB3IpoOrderManagerImpl();
        
        WEB3IpoOrderImpl l_ipoOrder;
        try
        {
            l_ipoOrder = new WEB3IpoOrderImpl(10L);
            WEB3IpoChangeOrderSpec l_ipoChangeOrderSpec = new WEB3IpoChangeOrderSpec(null,10,5,TaxTypeEnum.NORMAL);
//            ChangeOrderSpec l_offerSpec = new ChangeOrderSpec(10L);
            OrderValidationResult l_orderValidationResult = l_ipoOrderManagerImpl.validateOffer(l_ipoOrder.getSubAccount(),l_ipoChangeOrderSpec);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02878,l_orderValidationResult.getProcessingResult().getErrorInfo());
        }
        catch (DataFindException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
