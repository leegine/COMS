head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOpenContractOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP新規建注文サービスImplTest(WEB3OptionOpenContractOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/20 張騰宇 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapter;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOpenContractOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractOrderServiceImplTest.class);

    private IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;
    private WEB3OptionOpenContractOrderRequestAdapter l_requestAdapter = null;
    private WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
    private WEB3GentradeSubAccount l_subAccount = null;
    private WEB3OptionOpenContractOrderServiceImpl l_service = null;
    
    public WEB3OptionOpenContractOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        
        String l_str = "10";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getSessionProperty",
            new Class[] {String.class},
            l_str);
        
        
        NewOrderValidationResult l_validationResult = new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOpenContractOrder",
            new Class[] {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
            l_validationResult);
        
        WEB3IfoEstimateDeliveryAmountCalcResult l_result =
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "calcEstimateDeliveryAmount",
                new Class[] {WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                double.class, SideEnum.class, boolean.class, boolean.class},
                l_result);
        
        WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
        l_tradingPowerResult.setResultFlg(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                        Object[].class, OrderTypeEnum.class,boolean.class },
                l_tradingPowerResult);
        
        insertDB();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";
    public class WEB3OptionsOpenMarginConfirmRequestForMock 
        extends WEB3OptionsOpenMarginConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3OptionsOpenMarginConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    public class WEB3OptionsOpenMarginCompleteRequestForMock 
        extends WEB3OptionsOpenMarginCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3OptionsOpenMarginCompleteRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
   
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl.validateOrder(WEB3OptionsOpenMarginConfirmRequest)'
     */
    public void testValidateOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateOrderCase1()";
        log.entering(STR_METHOD_NAME);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setProductCode("0005");
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setFutureOptionDiv("2");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("0D");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = "20070619";
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //TradingTimeRow
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TradingTimeParams l_tradingTimeParams1 = new TradingTimeParams();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0006");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("000000");
            l_tradingTimeParams1.setEndTime("235959");
            l_tradingTimeParams1.setSubmitMarketTrigger("1");//"0"
            l_tradingTimeParams1.setEnableOrder("0");
            l_tradingTimeParams1.setBizdateCalcParameter("1");
            l_tradingTimeParams1.setSessionType(null);//
            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TradingTimeParams l_tradingTimeParams2 = new TradingTimeParams();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("0");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0007");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams2.setSubmitMarketTrigger("0");
            l_tradingTimeParams2.setEnableOrder("0");
            l_tradingTimeParams2.setBizdateCalcParameter("1");
            l_tradingTimeParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);

            //BranchIndexDealtCondRow
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = new BranchIndexDealtCondParams();
            l_branchIndexDealtCondParams.setTargetProductCode("0005");
            l_branchIndexDealtCondParams.setInstitutionCode("77");
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
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            //
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            Date date = ca.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            
            WEB3OptionClientRequestServiceForMock l_impl = new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsOpenMarginConfirmRequestForMock l_request =
                new WEB3OptionsOpenMarginConfirmRequestForMock();
            l_request.limitPrice = "1000";
            l_request.opOrderQuantity = "100";
            l_request.opProductCode = "0005";
            l_request.marketCode = "0";
            
            //WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"005","006"},"2");
            
            WEB3OptionsOpenMarginConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals(WEB3GentradeTradingTimeManagement.getOrderBizDate(), l_response.expirationDate);
        }
        catch (WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[] {IfoOrderManagerPersistenceEventInterceptor.class});
            assertNull(((WEB3IfoOpenContractUpdateInterceptor)l_paramsValue6.getFirstCalled()[0]).getSessionType());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * l_request.opProductCode = null
     * l_request.opProductType ="C"
     * 
     */
    public void testValidateOrderCase2()
    {
        final String STR_METHOD_NAME = "testValidateOrderCase2()";
        log.entering(STR_METHOD_NAME);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setProductCode("0005");
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setFutureOptionDiv("2");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("0D");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = "20070619";
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //TradingTimeRow
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TradingTimeParams l_tradingTimeParams1 = new TradingTimeParams();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0006");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("000000");
            l_tradingTimeParams1.setEndTime("235959");
            l_tradingTimeParams1.setSubmitMarketTrigger("1");//"0"
            l_tradingTimeParams1.setEnableOrder("0");
            l_tradingTimeParams1.setBizdateCalcParameter("1");
            l_tradingTimeParams1.setSessionType(null);//
            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TradingTimeParams l_tradingTimeParams2 = new TradingTimeParams();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("0");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0007");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams2.setSubmitMarketTrigger("0");
            l_tradingTimeParams2.setEnableOrder("0");
            l_tradingTimeParams2.setBizdateCalcParameter("1");
            l_tradingTimeParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);

            //BranchIndexDealtCondRow
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = new BranchIndexDealtCondParams();
            l_branchIndexDealtCondParams.setTargetProductCode("0005");
            l_branchIndexDealtCondParams.setInstitutionCode("77");
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
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            //
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            Date date = ca.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            
            WEB3OptionClientRequestServiceForMock l_impl = new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsOpenMarginConfirmRequestForMock l_request =
                new WEB3OptionsOpenMarginConfirmRequestForMock();
            l_request.limitPrice = "1000";
            l_request.opOrderQuantity = "100";
            //l_request.opProductCode = "0005";
            l_request.opProductCode = null;
            l_request.marketCode = "0";
            l_request.opProductType="C";//"P"
            l_request.strikePrice="100";
            l_request.contractType = "1";
            l_request.orderPriceDiv = "0";
            l_request.orderCondType="2";
            l_request.wLimitOrderPriceDiv ="1";
            l_request.wLimitPrice = "670";
            l_request.orderCondType = "1";//"2"
            l_request.stopOrderCondPrice ="680";
            l_request.wlimitOrderCondPrice= "700";
            //l_request.delivaryMonth= "199912";

            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class },
                    l_ifoProductImpl);
            
            //WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"005","006"},"2");

            l_impl.validateOrder(l_request);
            
            //參數驗證
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class });
//            assertEquals(WEB3GentradeInstitutionForMock.class, ((Institution) l_paramsValue1.getFirstCalled()[0]).getClass());
            assertNull(l_paramsValue1.getFirstCalled()[1]);
            assertNull(l_paramsValue1.getFirstCalled()[2]);
            assertEquals( IfoDerivativeTypeEnum.CALL_OPTIONS, (IfoDerivativeTypeEnum)l_paramsValue1.getFirstCalled()[3]);
            assertEquals( "100.0", l_paramsValue1.getFirstCalled()[4].toString());
            assertEquals( "000",(String)l_paramsValue1.getFirstCalled()[5]);
            assertEquals( "0",(String)l_paramsValue1.getFirstCalled()[6]);

        }
        catch (WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[] {IfoOrderManagerPersistenceEventInterceptor.class});
            assertNull(((WEB3IfoOpenContractUpdateInterceptor)l_paramsValue6.getFirstCalled()[0]).getSessionType());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * l_request.opProductCode = null
     * l_request.opProductType = "P"
     */
    public void testValidateOrderCase3()
    {
        final String STR_METHOD_NAME = "testValidateOrderCase3()";
        log.entering(STR_METHOD_NAME);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setProductCode("0005");
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setFutureOptionDiv("2");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("0D");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = "20070619";
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //TradingTimeRow
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TradingTimeParams l_tradingTimeParams1 = new TradingTimeParams();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0006");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("000000");
            l_tradingTimeParams1.setEndTime("235959");
            l_tradingTimeParams1.setSubmitMarketTrigger("1");//"0"
            l_tradingTimeParams1.setEnableOrder("0");
            l_tradingTimeParams1.setBizdateCalcParameter("1");
            l_tradingTimeParams1.setSessionType(null);//
            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TradingTimeParams l_tradingTimeParams2 = new TradingTimeParams();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("0");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0007");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams2.setSubmitMarketTrigger("0");
            l_tradingTimeParams2.setEnableOrder("0");
            l_tradingTimeParams2.setBizdateCalcParameter("1");
            l_tradingTimeParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);

            //BranchIndexDealtCondRow
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = new BranchIndexDealtCondParams();
            l_branchIndexDealtCondParams.setTargetProductCode("0005");
            l_branchIndexDealtCondParams.setInstitutionCode("77");
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
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            //
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            Date date = ca.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            
            WEB3OptionClientRequestServiceForMock l_impl = new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsOpenMarginConfirmRequestForMock l_request =
                new WEB3OptionsOpenMarginConfirmRequestForMock();
            l_request.limitPrice = "1000";
            l_request.opOrderQuantity = "100";
            //l_request.opProductCode = "0005";
            l_request.opProductCode = null;
            l_request.marketCode = "0";
            l_request.opProductType="P";//"C"
            l_request.strikePrice="100";
            l_request.contractType = "1";
            l_request.orderPriceDiv = "0";
            l_request.orderCondType="2";
            l_request.wLimitOrderPriceDiv ="1";
            l_request.wLimitPrice = "670";
            l_request.orderCondType = "2";//"1"
            l_request.stopOrderCondPrice ="680";
            l_request.wlimitOrderCondPrice= "700";

            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class },
                    l_ifoProductImpl);
            
            //WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"005","006"},"2");

            l_impl.validateOrder(l_request);
            
            //參數驗證
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class });
//            assertEquals(WEB3GentradeInstitutionForMock.class, ((Institution) l_paramsValue1.getFirstCalled()[0]).getClass());
            assertNull(l_paramsValue1.getFirstCalled()[1]);
            assertNull(l_paramsValue1.getFirstCalled()[2]);
            assertEquals( IfoDerivativeTypeEnum.PUT_OPTIONS, (IfoDerivativeTypeEnum)l_paramsValue1.getFirstCalled()[3]);
            assertEquals( "100.0", l_paramsValue1.getFirstCalled()[4].toString());
            assertEquals( "000",(String)l_paramsValue1.getFirstCalled()[5]);
            assertEquals( "0",(String)l_paramsValue1.getFirstCalled()[6]);

        }
        catch (WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[] {IfoOrderManagerPersistenceEventInterceptor.class});
            assertNull(((WEB3IfoOpenContractUpdateInterceptor)l_paramsValue6.getFirstCalled()[0]).getSessionType());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl.submitOrder(WEB3OptionsOpenMarginCompleteRequest)'
     */
    public void testSubmitOrderCase1()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase1()";
        log.entering(STR_METHOD_NAME);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setProductCode("0005");
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setFutureOptionDiv("2");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setStrikePrice(0.0);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setSplitType("000");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("0D");
        
        OrderSwitchingParams l_orderSwitchParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchParams.setInstitutionCode("0D");
        l_orderSwitchParams.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchParams.setMarketCode("0");
        l_orderSwitchParams.setFrontOrderSystemCode("9");
        l_orderSwitchParams.setValidFlag("1");
        l_orderSwitchParams.setSubmitOrderRouteDiv("0");
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("03");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = "20070620";;
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            TestDBUtility.insertWithDel(l_orderSwitchParams);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            
            Date date = ca.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            WEB3OptionClientRequestServiceForMock l_impl = new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsOpenMarginCompleteRequestForMock l_request =
                new WEB3OptionsOpenMarginCompleteRequestForMock();
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setTradingTimeType("03");
            l_clendarContext.setMarketCode("0");
            l_request.limitPrice = "1000";
            l_request.opOrderQuantity = "100";
            l_request.opProductCode = "0005";
            l_request.marketCode = "0";
            l_impl.submitOrder(l_request);
        }
        catch (WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[] {IfoOrderManagerPersistenceEventInterceptor.class});
            assertEquals(((WEB3IfoOpenContractUpdateInterceptor)l_paramsValue6.getFirstCalled()[0]).getSessionType(),"1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     *
     */
    public void testValidateTradingPower_C0001()
    {
        final String STR_METHOD_NAME = "testValidateTradingPower_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionOpenContractOrderServiceImpl l_service = new WEB3OptionOpenContractOrderServiceImpl();
            WEB3IfoOpenContractOrderSpec l_orderSpec[] = new WEB3IfoOpenContractOrderSpec[1];

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());

//            String l_strInstitutionCode, 
//            Trader l_trader, 
//            boolean l_blnIsBuyToOpenOrder, 
//            String l_strMarket, 
//            WEB3IfoProductImpl l_product, 
//            double l_dblQuantity, 
//            double l_dblLimitPrice, 
//            IfoOrderExecutionConditionType l_executionConditionType, 
//            Date l_datExpirationDate, 
//            String l_strOrderCond, 
//            double l_dblStopOrderBasePrice,
//            double l_dblWLimitPriceChange,
//            IfoOrderExecutionConditionType l_wLimitExecCondType,
//            String l_strExpirationDateType, 
//            Long l_lngFirstOrderUnitId,
//            boolean l_blnEveningSessionCarryoverFlag)

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            l_orderSpec[0] = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
               "0D",
               new TraderForTest(),
               true,
               "01",
               new WEB3IfoProductImplForTest(TestDBUtility.getIfoProductRow()),
                0,
                0,
                null,
                new Date(),
                null,
                0,
                0,
                null,
                null,
                null,
                true);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            boolean l_bln = true;
            l_tradingPowerResult.setResultFlg(l_bln);
            l_tradingPowerResult.setTradingPower(12345);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tradingPowerResult);

            WEB3TPTradingPowerResult l_result =l_service.validateTradingPower(
                new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow()),
                new Object[1],
                l_orderSpec,
                true);
           assertEquals(12345,l_result.getTradingPower(),0);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateTradingPower_C0002()
    {
        final String STR_METHOD_NAME = "testValidateTradingPower_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionOpenContractOrderServiceImpl l_service = new WEB3OptionOpenContractOrderServiceImpl();
            WEB3IfoOpenContractOrderSpec l_orderSpec[] = new WEB3IfoOpenContractOrderSpec[1];

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());

//            String l_strInstitutionCode, 
//            Trader l_trader, 
//            boolean l_blnIsBuyToOpenOrder, 
//            String l_strMarket, 
//            WEB3IfoProductImpl l_product, 
//            double l_dblQuantity, 
//            double l_dblLimitPrice, 
//            IfoOrderExecutionConditionType l_executionConditionType, 
//            Date l_datExpirationDate, 
//            String l_strOrderCond, 
//            double l_dblStopOrderBasePrice,
//            double l_dblWLimitPriceChange,
//            IfoOrderExecutionConditionType l_wLimitExecCondType,
//            String l_strExpirationDateType, 
//            Long l_lngFirstOrderUnitId,
//            boolean l_blnEveningSessionCarryoverFlag)

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoProductRow());
            l_orderSpec[0] = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
               "0D",
               new TraderForTest(),
               true,
               "01",
               new WEB3IfoProductImplForTest(TestDBUtility.getIfoProductRow()),
                0,
                0,
                null,
                new Date(),
                null,
                0,
                0,
                null,
                null,
                null,
                true);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            boolean l_bln = false;
            l_tradingPowerResult.setResultFlg(l_bln);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_tradingPowerResult);

            l_service.validateTradingPower(
                new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow()),
                new Object[1],
                l_orderSpec,
                true);
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01306,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitOpenContractOrder_C0001()
    {
        final String STR_METHOD_NAME = "testsubmitOpenContractOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_service = new WEB3OptionOpenContractOrderServiceImpl();
            TraderForTest l_trader = new TraderForTest();

            this.l_ifoOpenContractOrderSpec = new IfoOpenContractOrderSpecForTest(
                new TraderForTest(),
                true,
                "ab",
                IfoDerivativeTypeEnum.FUTURES,
                "cd",
                1001,
                "ef",
                1002,
                1003,
                new IfoOrderExecutionConditionType(0, STR_METHOD_NAME),
                new Date(),
                new TaxTypeEnum(0, STR_METHOD_NAME));
            long l_lngOrderId = 2001;
            String l_strTradingPassword = "Password";
            this.l_requestAdapter = WEB3OptionOpenContractOrderRequestAdapter.create(new WEB3OptionsOpenMarginConfirmRequest());
            this.l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            this.l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "submitOpenContractOrder", 
                    new Class[]{ SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));

            this.l_service.submitOpenContractOrder(l_subAccount,l_ifoOpenContractOrderSpec,l_lngOrderId,l_strTradingPassword,l_requestAdapter,l_estimateDeliveryAmountCalcResult);
            assertTrue(true);
 
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testsubmitOpenContractOrder_C0002()
    {
        final String STR_METHOD_NAME = "testsubmitOpenContractOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TraderForTest l_trader = new TraderForTest();
            this.l_service = new WEB3OptionOpenContractOrderServiceImpl();
            this.l_ifoOpenContractOrderSpec = new IfoOpenContractOrderSpecForTest(
                new TraderForTest(),
                true,
                "ab",
                IfoDerivativeTypeEnum.FUTURES,
                "cd",
                1001,
                "ef",
                1002,
                1003,
                new IfoOrderExecutionConditionType(0, STR_METHOD_NAME),
                new Date(),
                new TaxTypeEnum(0, STR_METHOD_NAME));
            long l_lngOrderId = 2001;
            String l_strTradingPassword = "Password";
            this.l_requestAdapter = WEB3OptionOpenContractOrderRequestAdapter.create(new WEB3OptionsOpenMarginConfirmRequest());
            this.l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            this.l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "submitOpenContractOrder", 
                    new Class[]{ SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(new ErrorInfo())));

            this.l_service.submitOpenContractOrder(l_subAccount,l_ifoOpenContractOrderSpec,l_lngOrderId,l_strTradingPassword,l_requestAdapter,l_estimateDeliveryAmountCalcResult);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private void setExpectedDate(Date l_expectDate,String l_sessionType)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("03");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType(l_sessionType);
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, new Timestamp(l_expectDate.getTime()));   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    private class WEB3OptionClientRequestServiceForMock extends WEB3OptionOpenContractOrderServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "getSubAccount()";
            log.entering(STR_METHOD_NAME);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_subAccountParams);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
    }
    
    public void insertDB()
    {
        try
        {
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
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
            return null;
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
    
    private class WEB3IfoProductImplForTest extends WEB3IfoProductImpl
    {

        public WEB3IfoProductImplForTest(IfoProductRow l_row) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_row);
            // TODO Auto-generated constructor stub
        }
        
    }
    
    private class IfoOpenContractOrderSpecForTest extends IfoOpenContractOrderSpec
    {

        protected IfoOpenContractOrderSpecForTest(
            Trader arg0, boolean arg1, String arg2, IfoDerivativeTypeEnum arg3, String arg4, double arg5, String arg6, double arg7, double arg8, IfoOrderExecutionConditionType arg9, Date arg10, TaxTypeEnum arg11)
        {
            super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11);
            // TODO Auto-generated constructor stub
        }
        
    }
}
@
