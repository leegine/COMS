head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeFromIfoDepositServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3AccTransChangeFromIfoDepositServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/16 ë∑ç^ç] (íÜêu) êVãKçÏê¨
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeInstitutionForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeFromIfoDepositServiceImplTest extends TestBaseForMock
{
    private WEB3AccTransChangeFromIfoDepositCompleteResponse l_response = null;
    private WEB3AccTransChangeFromIfoDepositCompleteRequest l_request = null;
    private WEB3AccTransChangeFromIfoDepositServiceImpl l_service = null;
    
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AccTransChangeFromIfoDepositServiceImplTest.class);

    public WEB3AccTransChangeFromIfoDepositServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_request = new WEB3AccTransChangeFromIfoDepositCompleteRequestForTest();
        l_service = new WEB3AccTransChangeFromIfoDepositServiceImplForTest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_service = null;
        this.l_request = null;
    }
    
    public void testSubmitOrder_0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        Date l_datpreBizDate = null;
        try
        {
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            
            l_request.checkDate = l_datpreBizDate;
            l_request.changeAmt = "100";
            this.initData();
            
            WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc",
                new Class[]{ WEB3GentradeSubAccount.class },
                l_ifoDepositCalc);
            
            String l_strNewNumber = null;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },
                l_strNewNumber);
            
            long l_lngProductId = 10;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(l_lngProductId));
            
            long l_accountId = 333812512203L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(l_accountId));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                "submitMarginTransfer",
                new Class[]{ WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class},
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));

            
            this.l_response = this.l_service.submitOrder(this.l_request);
            fail();
               
        }
        catch(WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            log.error(l_web3MockObjectRuntimeException.getMessage(), l_web3MockObjectRuntimeException);
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                    "submitMarginTransfer",
                    new Class[]{ WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class});
            
            assertEquals(WEB3GentradeMainAccountForMock.class, ((
                    WEB3GentradeMainAccount)l_paramsValue4.getFirstCalled()[0]).getClass());
            
            Date l_resultDate = (Date)l_paramsValue4.getFirstCalled()[1];
            assertEquals(0, WEB3DateUtility.compareToDay(l_datpreBizDate,l_resultDate));
            assertEquals("100.0", ((Double)l_paramsValue4.getFirstCalled()[2]).toString());
            assertNull(l_paramsValue4.getFirstCalled()[3]);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]{ WEB3GentradeSubAccount.class });
            assertEquals(WEB3GentradeSubAccount.class, l_paramsValue1.getFirstCalled()[0].getClass());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[]{ String.class, String.class, ProductTypeEnum.class });
            assertEquals("0D", (l_paramsValue2.getFirstCalled()[0]).toString());
            assertEquals("381", (l_paramsValue2.getFirstCalled()[1]).toString());
            assertEquals("5:CASH", (l_paramsValue2.getFirstCalled()[2]).toString());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "getProductId",
                    new Class[]{ Institution.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, l_paramsValue3.getFirstCalled()[0].getClass());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * ÅyWEB3ÅzÅyì¸èoã‡ÅzíPëÃÉeÉXÉgédólåìïÒçêèëNO:1094
     */
    public void testValidateOrder_0001()
    {
    	WEB3AccTransChangeFromIfoDepositConfirmRequest r =
    		new WEB3AccTransChangeFromIfoDepositConfirmRequest();
    	r.changeAmt = "80";
    	WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
        WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalcForTest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc",
                new Class[]{ WEB3GentradeSubAccount.class },
                l_ifoDepositCalc);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[]{ WEB3GentradeSubAccount.class,Date.class },
                new Double(50));
    	try{
    		
    	    SystemPreferencesParams  l_sysPrePar = TestDBUtility.getSystemPreferencesRow();
    	    TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
    	    //ã∆ñ±ì˙ït:20090317
    	    l_sysPrePar.setValue("20090317");
    	    TestDBUtility.insertWithDel(l_sysPrePar);

    		WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20090315", "yyyyMMdd"));
    		WEB3AccTransChangeFromIfoDepositConfirmResponse response = l_service.validateOrder(r);
    		assertEquals("120",response.aftIfoDeposit);
        	
        	
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		fail();
    	}

    }
    
    
    /**
     * 
     * ÅyWEB3ÅzÅyì¸èoã‡ÅzíPëÃÉeÉXÉgédólåìïÒçêèëNO:1095
     */
    public void testValidateOrder_0002()
    {
    	WEB3AccTransChangeFromIfoDepositConfirmRequest r =
    		new WEB3AccTransChangeFromIfoDepositConfirmRequest();
    	r.changeAmt = "80";
    	WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
        WEB3IfoDepositCalc l_ifoDepositCalc = new WEB3IfoDepositCalcForTest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                "getIfoDepositCalc",
                new Class[]{ WEB3GentradeSubAccount.class },
                l_ifoDepositCalc);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getPaymentTradingPower",
                new Class[]{ WEB3GentradeSubAccount.class,Date.class },
                new Double(50));
    	try{
    		
    	    SystemPreferencesParams  l_sysPrePar = TestDBUtility.getSystemPreferencesRow();
    	    TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
    	    //ã∆ñ±ì˙ït:20090317
    	    l_sysPrePar.setValue("20090317");
    	    TestDBUtility.insertWithDel(l_sysPrePar);

    		WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20090317", "yyyyMMdd"));
    		WEB3AccTransChangeFromIfoDepositConfirmResponse response = l_service.validateOrder(r);
    		assertEquals("20",response.aftIfoDeposit);
        	
        	
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		fail();
    	}

    }   
    
    public class WEB3AccTransChangeFromIfoDepositCompleteRequestForTest extends
        WEB3AccTransChangeFromIfoDepositCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            log.debug("WEB3AccTransChangeFromIfoDepositCompleteRequestForTest.validate()");
        }
    }
    
    public class WEB3AccTransChangeFromIfoDepositServiceImplForTest extends 
        WEB3AccTransChangeFromIfoDepositServiceImpl
    {
        public SubAccount getSubAccount(SubAccountTypeEnum l_subAccountType) throws WEB3SystemLayerException
        {
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            
            return l_subAccount;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return new TraderForTest();
        }
    }
    
    private class TraderForTest implements Trader
    {

        public long getTraderId()
        {
            
            return 0;
        }

        public String getTraderCode()
        {
            
            return null;
        }

        public long getLoginId()
        {
            
            return 0;
        }

        public TraderTypeEnum getTraderType()
        {
            
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            
            return null;
        }

        public Branch getBranch()
        {
            
            return null;
        }

        public Institution getInstitution()
        {
            
            return null;
        }

        public Object getDataSourceObject()
        {
            
            return null;
        }
        
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //TradingTimeRow
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
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
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("000000");
            l_tradingTimeParams1.setEndTime("235959");
            l_tradingTimeParams1.setSubmitMarketTrigger("0");
            l_tradingTimeParams1.setEnableOrder("0");
            l_tradingTimeParams1.setBizdateCalcParameter("1");
            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("ì‡ì°Å@@élòY");
            l_mainAccountParams.setFamilyNameAlt1("≈≤ƒ≥ º€≥");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("ìåãûìs");
            l_mainAccountParams.setAddressLine2("ç]ìåãÊ");
            l_mainAccountParams.setAddressLine3("ê[êÏÇT");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            l_mainAccountParams.setTransferCount(17);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchIndexDealtCondRow
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = new BranchIndexDealtCondParams();
            l_branchIndexDealtCondParams.setTargetProductCode("0005");
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            l_branchIndexDealtCondParams.setBranchCode("381");
            l_branchIndexDealtCondParams.setMarketCode("33");
            l_branchIndexDealtCondParams.setFutureOptionDiv("1");
            l_branchIndexDealtCondParams.setOpenContLimit(1234556L);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit0(14D);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit0Red(11D);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit1(11D);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit1Red(11D);
            l_branchIndexDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams.setLastUpdatedDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = new BranchIndexDealtCondParams();
            l_branchIndexDealtCondParams1.setTargetProductCode("0005");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("624");
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            l_branchIndexDealtCondParams1.setOpenContLimit(1234556L);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit0(14D);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit0Red(11D);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit1(11D);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit1Red(11D);
            l_branchIndexDealtCondParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams1.setLastUpdatedDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams1.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            
            //TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setAssetEvaluationDiv("1");
            l_tradingpowerCalcConditionParam.setBranchId(1235562L);
            l_tradingpowerCalcConditionParam.setCalcConditionId(4521365L);
            l_tradingpowerCalcConditionParam.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_tradingpowerCalcConditionParam.setIfoOpenPositionStop("1");
            l_tradingpowerCalcConditionParam.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_tradingpowerCalcConditionParam.setMarginOpenPositionStop("1");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParam.setPaymentStop("1");
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(20);
            l_tradingpowerCalcConditionParam.setTradingStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //BranchPreferencesRow
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_branchPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_branchPreferencesParams.setName("triggerorder.wlimitorder.check.order.cond.price");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1002);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3IfoDepositCalcForTest extends WEB3IfoDepositCalc
    {
        public double calcIfoDepositTransferableAmount()
        {
            return 100;
        }
        
        public double calcIfoDepositBalance(int l_intReservedDate)
        {
        	if(l_intReservedDate == 0)
        	{
        		return 100.0;
        	}
        	else if(l_intReservedDate == 1)
        	{
        		return 200.0;
        	}
        	return 0;
        }
    }
}
@
