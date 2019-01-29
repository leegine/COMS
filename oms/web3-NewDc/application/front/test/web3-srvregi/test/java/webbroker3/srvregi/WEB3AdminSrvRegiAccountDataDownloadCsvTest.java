head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataDownloadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataDownloadCsvTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminSrvRegiAccountDataDownloadCsvTest.class);
    
    WEB3AdminSrvRegiAccountDataDownloadCsv l_csv = new WEB3AdminSrvRegiAccountDataDownloadCsv();
    
    public WEB3AdminSrvRegiAccountDataDownloadCsvTest(String arg0)
    {
        super(arg0);
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

    public void testSetPaymentPower01()
    {
        final String STR_METHOD_NAME = "testSetPaymentPower01()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        l_csv.addRow();
        WEB3GentradeCsvColumnModel l_csvModel = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_POWER_LABEL,
            12,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        try
        {
            //mainAcc
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccRow = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccRow);
            
            //subAcc
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccRow = TestDBUtility.getSubAccountRow();
            l_subAccRow.setAccountId(333812512246L);
            l_subAccRow.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccRow);
            
            //institution
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("33");
            TestDBUtility.insertWithDel(l_insParams);
            
            //branch
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            Double l_dblTradingPower = new Double(10000);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                l_dblTradingPower);
            
            l_csv.setPaymentPower(
                0, 
                l_mainAccRow.getInstitutionCode(),
                l_mainAccRow.getBranchCode(),
                l_mainAccRow.getAccountCode()
                );
            Object l_obj = l_csv.getValue(0, l_csvModel);
            log.debug("class TYPE =========== == " + l_obj.getClass());
            log.debug("CSVgetValue === " + l_csv.getValue(0, l_csvModel));
            assertEquals(new String("10000"), l_csv.getValue(0, l_csvModel));
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("WEB3BaseException l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetPaymentPower02()
    {
        final String STR_METHOD_NAME = "testSetPaymentPower02()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        l_csv.addRow();
        WEB3GentradeCsvColumnModel l_csvModel = new WEB3GentradeCsvColumnModel(
            WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_POWER_LABEL,
            12,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        try
        {
            //mainAcc
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccRow = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccRow);
            
            //subAcc
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccRow = TestDBUtility.getSubAccountRow();
            l_subAccRow.setAccountId(333812512246L);
            l_subAccRow.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccRow);
            
            //institution
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("33");
            TestDBUtility.insertWithDel(l_insParams);
            
            //branch
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3SystemLayerException l_sle = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80007, 
                "testSetPaymentPower02");
            
            Double l_dblTradingPower = new Double(10000);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                l_sle);
            
            l_csv.setPaymentPower(
                0, 
                l_mainAccRow.getInstitutionCode(),
                l_mainAccRow.getBranchCode(),
                l_mainAccRow.getAccountCode()
                );
            log.debug("CSVgetValue === " + l_csv.getValue(0, l_csvModel));
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("WEB3BaseException l_ex == " + l_ex);
            assertEquals(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003.getErrorCode(), 
                l_ex.getErrorInfo().getErrorCode());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
